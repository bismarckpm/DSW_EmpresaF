package ucab.dsw.logica.comando.encuesta;

import ucab.dsw.accesodatos.*;
import ucab.dsw.dtos.PreguntaDto;
import ucab.dsw.entidades.Encuesta;
import ucab.dsw.entidades.Opcion;
import ucab.dsw.entidades.Pregunta;
import ucab.dsw.entidades.PreguntaOpcion;
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

public class ComandoGetPreguntasEncuesta implements ComandoBase {

  private long idEncuesta;
  private  JsonArrayBuilder preguntasJson = Json.createArrayBuilder();

  public ComandoGetPreguntasEncuesta(long idEncuesta) {
    this.idEncuesta = idEncuesta;
  }

  public void execute() throws LimiteExcepcion, SolicitudPendienteExcepcion, PruebaExcepcion, InstantiationException, IllegalAccessException, InvocationTargetException {

    try{

      DaoPreguntaEncuesta daoPreguntaEncuesta = Fabrica.crear(DaoPreguntaEncuesta.class);

      DaoEncuesta daoEncuesta = Fabrica.crear(DaoEncuesta.class);
      Encuesta encuesta = daoEncuesta.find(this.idEncuesta, Encuesta.class);

      List<Pregunta> preguntas = daoPreguntaEncuesta.getPreguntasByEncuesta(encuesta);

      JsonArrayBuilder opcionesJson = Json.createArrayBuilder();

      for (Pregunta pregunta : preguntas) {

        DaoPregunta daoPregunta = Fabrica.crear(DaoPregunta.class);
        Pregunta question = daoPregunta.find(pregunta.get_id(), Pregunta.class);

        DaoPreguntaOpcion daoPreguntaOpcion = Fabrica.crear(DaoPreguntaOpcion.class);
        List<PreguntaOpcion> preguntaOpciones = daoPreguntaOpcion.findAll(PreguntaOpcion.class);

        for (PreguntaOpcion preguntaOpcion : preguntaOpciones) {

          if (preguntaOpcion.get_pregunta().get_id() == question.get_id()) {

            DaoOpcion daoOpcion = Fabrica.crear(DaoOpcion.class);
            Opcion opcion = daoOpcion.find(preguntaOpcion.get_opcion().get_id(), Opcion.class);

            JsonObject option = Json.createObjectBuilder()
              .add("idPregunta", question.get_id())
              .add("idOpcion", opcion.get_id())
              .add("descripcion", opcion.get_descripcion()).build();

            opcionesJson.add(option);

          }

        }

        PreguntaDto preguntaDto = MapperEncuesta.MapEntityToPreguntaInEncuestaDto(question);

        JsonObject quest = Json.createObjectBuilder()
          .add("idPregunta", preguntaDto.getId())
          .add("descripcion", preguntaDto.getDescripcionPregunta())
          .add("tipo", preguntaDto.getTipoPregunta())
          .add("min", preguntaDto.getMin())
          .add("max", preguntaDto.getMax())
          .add("opciones", opcionesJson).build();

        preguntasJson.add(quest);

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
        .add("data", this.preguntasJson)
        .build();

      return data;

    }catch (Exception ex){
      throw ex;
    }
  }

}
