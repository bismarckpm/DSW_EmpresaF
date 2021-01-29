package ucab.dsw.logica.comando.estudio;

import ucab.dsw.accesodatos.DaoEncuesta;
import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.accesodatos.DaoSolicitudEstudio;
import ucab.dsw.dtos.EncuestaDto;
import ucab.dsw.dtos.EstudioDto;
import ucab.dsw.entidades.Encuesta;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.entidades.SolicitudEstudio;
import ucab.dsw.excepciones.LimiteExcepcion;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.excepciones.RangoExcepcion;
import ucab.dsw.excepciones.SolicitudPendienteExcepcion;
import ucab.dsw.logica.comando.ComandoBase;
import ucab.dsw.logica.fabrica.Fabrica;
import ucab.sw.mapper.encuesta.MapperEncuesta;
import ucab.sw.mapper.estudio.MapperEstudio;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.naming.NamingException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class ComandoGetEstudioByAnalista implements ComandoBase {

  private long analistaId;
  private JsonArrayBuilder estudiosArray = Json.createArrayBuilder();

  public ComandoGetEstudioByAnalista(long analistaId) {
    this.analistaId = analistaId;
  }

  public void execute() throws LimiteExcepcion, SolicitudPendienteExcepcion, PruebaExcepcion, InstantiationException, IllegalAccessException, InvocationTargetException, RangoExcepcion, NamingException {

    String resultadoEstudio;

    try{

      DaoSolicitudEstudio daoSolicitudEstudio = Fabrica.crear(DaoSolicitudEstudio.class);
      List<SolicitudEstudio> solicitudEstudios = daoSolicitudEstudio.findAll(SolicitudEstudio.class);

      for (SolicitudEstudio solicitudes : solicitudEstudios) {

        if(solicitudes.get_estudio() != null){

          if(solicitudes.get_analista().get_id() == this.analistaId) {

            DaoEstudio daoEstudio = Fabrica.crear(DaoEstudio.class);
            Estudio estu = daoEstudio.find(solicitudes.get_estudio().get_id(), Estudio.class);

            if(estu.get_resultado() != null){

              resultadoEstudio = estu.get_resultado();

            }else{

              resultadoEstudio = "Sin resultado";

            }

            DaoEncuesta daoEncuesta = Fabrica.crear(DaoEncuesta.class);
            Encuesta encuesta = daoEncuesta.find(solicitudes.get_estudio().get_encuesta().get_id(), Encuesta.class);

            EncuestaDto encuestaDto = MapperEncuesta.MapEntityToEncuestaDto(encuesta);

            EstudioDto estudioDto = MapperEstudio.MapEntityToEstudioDtoGet(estu, encuestaDto);

            JsonObject estudio = Json.createObjectBuilder()
              .add("estado", estudioDto.getEstado())
              .add("id", solicitudes.get_id())
              .add("nombreEstudio", estudioDto.getNombreEstudio())
              .add("resultadoEstudio", resultadoEstudio)
              .add("encuestaId", estudioDto.getEncuesta().getId()).build();

            estudiosArray.add(estudio);

          }

        }

      }

    }catch (Exception ex){
      throw ex;
    }

  }

  public JsonObject getResultado(){

    try{

      JsonObject data = Json.createObjectBuilder()
        .add("estado", "success")
        .add("code", 200)
        .add("estudios",this.estudiosArray)
        .build();

      return data;

    }catch (Exception ex){
      throw ex;
    }

  }

}
