package ucab.dsw.logica.comando.encuesta;

import ucab.dsw.accesodatos.*;
import ucab.dsw.dtos.ArrayRespuestaDto;
import ucab.dsw.dtos.BaseRespuestaDto;
import ucab.dsw.dtos.OpcionDto;
import ucab.dsw.entidades.*;
import ucab.dsw.excepciones.*;
import ucab.dsw.logica.comando.ComandoBase;
import ucab.dsw.logica.exepcionhandler.ManejadorExcepcion;
import ucab.dsw.logica.fabrica.Fabrica;
import ucab.sw.mapper.encuesta.MapperEncuesta;
import ucab.sw.mapper.muestra.MapperMuestra;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

public class ComandoAddRespuestaEncuesta implements ComandoBase {

  private long idEncuesta;
  private BaseRespuestaDto baseRespuestaDto;
  private Respuesta ultPreguntaRespuesta;

  public ComandoAddRespuestaEncuesta(long idEncuesta, BaseRespuestaDto baseRespuestaDto) {
    this.idEncuesta = idEncuesta;
    this.baseRespuestaDto = baseRespuestaDto;
  }

  public void execute() throws Exception{

    try {

      DaoEncuesta daoEncuesta = Fabrica.crear(DaoEncuesta.class);
      Encuesta encuesta = daoEncuesta.find(this.idEncuesta, Encuesta.class);

      Encuestado encuestado = null;

      DaoMuestra daoMuestra = Fabrica.crear(DaoMuestra.class);

      for(ArrayRespuestaDto respuesta: this.baseRespuestaDto.getRespuestas()){

        DaoPregunta daoPregunta = Fabrica.crear(DaoPregunta.class);
        Pregunta pregunta = daoPregunta.find(respuesta.getPregunta().getId(), Pregunta.class);

        DaoEncuestado daoEncuestado = Fabrica.crear(DaoEncuestado.class);
        encuestado = daoEncuestado.find(respuesta.getEncuestado().getId(), Encuestado.class);

        Date fecha = Fabrica.crear(Date.class);

        Integer rango;
        if( (respuesta.getRango() < pregunta.get_min() ) || (respuesta.getRango() > pregunta.get_max())){

          throw new RangoExcepcion("Rango seleccionado fuera de los límites de maximo y mínimo");

        }else {

          rango = respuesta.getRango();

        }

        DaoPreguntaEncuesta daoPreguntaEncuesta = Fabrica.crear(DaoPreguntaEncuesta.class);
        List<PreguntaEncuesta> preguntaEncuestas = daoPreguntaEncuesta.findAll(PreguntaEncuesta.class);

        Respuesta respuestaAgregada = null;

        for (PreguntaEncuesta preguntaEncuesta : preguntaEncuestas) {

          if (preguntaEncuesta.get_encuesta().get_id() == encuesta.get_id() && preguntaEncuesta.get_pregunta().get_id() == pregunta.get_id()) {

            respuestaAgregada = MapperEncuesta.MapRespuestaEncuestaDtoToEntityAdd(respuesta, fecha, rango, encuestado, preguntaEncuesta);

          }

        }

        DaoRespuesta daoRespuesta = Fabrica.crear(DaoRespuesta.class);
        this.ultPreguntaRespuesta = daoRespuesta.insert(respuestaAgregada);

        if(respuesta.getOpciones()!=null) {

          for (OpcionDto opcionDto : respuesta.getOpciones()) {

            DaoOpcion daoOpcion = Fabrica.crear(DaoOpcion.class);
            Opcion opcion = daoOpcion.find(opcionDto.getId(), Opcion.class);

            RespuestaOpcion respuestaOpcion = MapperEncuesta.MapRespuestaOpcionToEntity(opcion, respuestaAgregada);

            DaoRespuestaOpcion daoRespuestaOpcion = Fabrica.crear(DaoRespuestaOpcion.class);
            daoRespuestaOpcion.insert(respuestaOpcion);

          }

        }

      }

      DaoEstudio dao = Fabrica.crear(DaoEstudio.class);
      Estudio est = dao.getEstudioByEncuesta(encuesta);

      for(SolicitudEstudio solicitudEstudio: est.get_solicitudesEstudio()){

        List<Muestra> muestras = daoMuestra.findAll(Muestra.class);

        for (Muestra muestra:muestras) {

          if (solicitudEstudio.get_id() == muestra.get_solicitudEstudio().get_id() && encuestado.get_id() == muestra.get_encuestado().get_id()){

            Muestra muestraActualizada = MapperMuestra.MappMuestraToEntity(muestra);
            daoMuestra.update(muestraActualizada);

          }

        }

      }

    }catch (RangoExcepcion ex){

      throw new ProblemaExcepcion(ex.getMessage(), "La respuesta posiblemente está fuera del rango permitido");

    }catch (Exception ex){
      throw  ex;
    }

  }

  public JsonObject getResultado(){

    try {

      JsonObject data = Json.createObjectBuilder()
        .add("estado", "success")
        .add("code", 200)
        .add("ultPreguntaRespuesta", this.ultPreguntaRespuesta.get_id()).build();

      return data;

    }catch (Exception ex){
      throw ex;
    }

  }

}
