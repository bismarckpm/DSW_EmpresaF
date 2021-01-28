package ucab.dsw.logica.comando.encuesta;

import ucab.dsw.accesodatos.DaoEncuesta;
import ucab.dsw.accesodatos.DaoPregunta;
import ucab.dsw.accesodatos.DaoPreguntaEncuesta;
import ucab.dsw.dtos.PreguntaDto;
import ucab.dsw.entidades.Encuesta;
import ucab.dsw.entidades.Pregunta;
import ucab.dsw.excepciones.LimiteExcepcion;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.excepciones.SolicitudPendienteExcepcion;
import ucab.dsw.logica.comando.ComandoBase;
import ucab.dsw.logica.fabrica.Fabrica;
import ucab.sw.mapper.encuesta.MapperEncuesta;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class ComandoGetPreguntasAgregables implements ComandoBase {

  private long idEncuesta;
  private JsonArrayBuilder preguntaArray = Json.createArrayBuilder();

  public ComandoGetPreguntasAgregables(long idEncuesta) {
    this.idEncuesta = idEncuesta;
  }

  public void execute() throws LimiteExcepcion, SolicitudPendienteExcepcion, PruebaExcepcion, InstantiationException, IllegalAccessException, InvocationTargetException {

    try{

      DaoEncuesta daoEncuesta = Fabrica.crear(DaoEncuesta.class);
      Encuesta encuesta = daoEncuesta.find(this.idEncuesta, Encuesta.class );

      DaoPregunta daoPregunta = Fabrica.crear(DaoPregunta.class);
      List<Pregunta> preguntas = daoPregunta.findAll(Pregunta.class);

      DaoPreguntaEncuesta daoPreguntaEncuesta = Fabrica.crear(DaoPreguntaEncuesta.class);

      for(Pregunta preguntaList: preguntas){

        Pregunta preguntasAgregada  = daoPreguntaEncuesta.getPreguntaAgregable(preguntaList, encuesta);

          if (preguntasAgregada == null ) {

            PreguntaDto preguntaDto = MapperEncuesta.MapEntityToPreguntaInEncuestaDto(preguntaList);

            JsonObject question = Json.createObjectBuilder()
              .add("preguntaId", preguntaDto.getId())
              .add("descripcionPregunta", preguntaDto.getDescripcionPregunta())
              .add("tipoPregunta", preguntaDto.getTipoPregunta()).build();

            preguntaArray.add(question);

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
        .add("preguntas", this.preguntaArray).build();

      return data;

    }catch (Exception ex){
      throw ex;
    }

  }

}
