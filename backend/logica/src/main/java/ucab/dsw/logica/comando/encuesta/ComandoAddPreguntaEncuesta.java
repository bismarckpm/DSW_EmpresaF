package ucab.dsw.logica.comando.encuesta;

import ucab.dsw.accesodatos.DaoEncuesta;
import ucab.dsw.accesodatos.DaoPregunta;
import ucab.dsw.accesodatos.DaoPreguntaEncuesta;
import ucab.dsw.dtos.PreguntaDto;
import ucab.dsw.dtos.PreguntaEncuestaDto;
import ucab.dsw.entidades.Encuesta;
import ucab.dsw.entidades.Pregunta;
import ucab.dsw.entidades.PreguntaEncuesta;
import ucab.dsw.excepciones.LimiteExcepcion;
import ucab.dsw.excepciones.ProblemaExcepcion;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.excepciones.SolicitudPendienteExcepcion;
import ucab.dsw.logica.comando.ComandoBase;
import ucab.dsw.logica.exepcionhandler.ManejadorExcepcion;
import ucab.dsw.logica.fabrica.Fabrica;
import ucab.sw.mapper.encuesta.MapperEncuesta;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;
import java.lang.reflect.InvocationTargetException;

public class ComandoAddPreguntaEncuesta implements ComandoBase {

  private long idEncuesta;
  private PreguntaDto preguntaDto;
  private PreguntaEncuestaDto preguntaEncuestaDto;

  public ComandoAddPreguntaEncuesta(long idEncuesta, PreguntaDto preguntaDto) {
    this.idEncuesta = idEncuesta;
    this.preguntaDto = preguntaDto;
  }

  public void execute() throws Exception {

    try{

      DaoEncuesta daoEncuesta = Fabrica.crear(DaoEncuesta.class);
      Encuesta encuesta = daoEncuesta.find(this.idEncuesta, Encuesta.class);

      PreguntaEncuesta resultado = null;
      for(PreguntaDto pregunta: preguntaDto.getPreguntas()) {

        DaoPregunta daoPregunta = Fabrica.crear(DaoPregunta.class);
        Pregunta question = daoPregunta.find(pregunta.getId(), Pregunta.class);

        PreguntaEncuesta preguntaEncuesta = MapperEncuesta.MapPreguntaEncuestaDtoToEntityAdd(question, encuesta);

        DaoPreguntaEncuesta daoPreguntaEncuesta = Fabrica.crear(DaoPreguntaEncuesta.class);
        resultado = daoPreguntaEncuesta.insert(preguntaEncuesta);

      }

      this.preguntaEncuestaDto = MapperEncuesta.MapEntityToPreguntaEncuestaDto(resultado);

    }catch (javax.persistence.PersistenceException ex) {

      throw new ProblemaExcepcion("Hay opciones que ya se encuentran añadidas", ex.getMessage());

    } catch (Exception ex){
      throw ex;
    }

  }

  public JsonObject getResultado(){

    try{

      JsonObject  data = Json.createObjectBuilder()
        .add("idUltPreguntaEncuesta", this.preguntaEncuestaDto.getId())
        .add("estado", "success")
        .add("code", 200)
        .build();

      return  data;

    }catch (Exception ex){
      throw ex;
    }

  }

}
