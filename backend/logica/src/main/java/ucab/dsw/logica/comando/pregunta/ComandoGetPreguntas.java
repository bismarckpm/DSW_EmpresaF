package ucab.dsw.logica.comando.pregunta;

import ucab.dsw.accesodatos.DaoPregunta;
import ucab.dsw.accesodatos.DaoPreguntaOpcion;
import ucab.dsw.dtos.PreguntaDto;
import ucab.dsw.dtos.PreguntaOpcionDto;
import ucab.dsw.entidades.Pregunta;
import ucab.dsw.entidades.PreguntaOpcion;
import ucab.dsw.logica.comando.ComandoBase;
import ucab.dsw.logica.fabrica.Fabrica;
import ucab.sw.mapper.pregunta.MapperPregunta;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.List;

public class ComandoGetPreguntas implements ComandoBase {

  private JsonArrayBuilder preguntasArray = Json.createArrayBuilder();

  public void execute() throws Exception {

    try {

      DaoPregunta dao = Fabrica.crear(DaoPregunta.class);
      List<Pregunta> preguntas = dao.findAll(Pregunta.class);

      JsonArrayBuilder opcionesArray = Json.createArrayBuilder();

      for (Pregunta question : preguntas) {

        DaoPreguntaOpcion daoPreguntaOpcion = Fabrica.crear(DaoPreguntaOpcion.class);
        List<PreguntaOpcion> preguntaOpciones = daoPreguntaOpcion.findAll(PreguntaOpcion.class);

        for (PreguntaOpcion preguntaOpcion : preguntaOpciones) {

          if (question.get_id() == preguntaOpcion.get_pregunta().get_id()) {

            JsonObject JsonOptions = Json.createObjectBuilder()
              .add("opcionId", preguntaOpcion.get_opcion().get_id())
              .add("opcion", preguntaOpcion.get_opcion().get_descripcion()).build();

            opcionesArray.add(JsonOptions);

          }

        }

        PreguntaDto preguntaDto = MapperPregunta.MapEntityToPreguntaDto(question);

        JsonObject JsonQuestion = Json.createObjectBuilder()
          .add("preguntaId", preguntaDto.getId())
          .add("descripcionPregunta", preguntaDto.getDescripcionPregunta())
          .add("tipo", preguntaDto.getTipoPregunta())
          .add("min", preguntaDto.getMin())
          .add("max", preguntaDto.getMax())
          .add("opciones", opcionesArray)
          .build();

        preguntasArray.add(JsonQuestion);

      }

    }catch (Exception ex){
      throw ex;
    }

  }

  public JsonObject getResultado(){

    try {

      JsonObject data = Json.createObjectBuilder()
        .add("data", this.preguntasArray)
        .add("code", 200)
        .add("estado", "success")
        .build();

      return data;

    }catch (Exception ex){
      throw ex;
    }

  }

}
