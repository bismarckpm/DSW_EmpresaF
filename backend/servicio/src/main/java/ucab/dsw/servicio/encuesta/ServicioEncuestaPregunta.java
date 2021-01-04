package ucab.dsw.servicio.encuesta;

import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ucab.dsw.accesodatos.*;
import ucab.dsw.dtos.PreguntaDto;
import ucab.dsw.dtos.PreguntaEncuestaDto;
import ucab.dsw.entidades.*;

/**
 * Clase para gestionar las preguntas de una encuesta
 *
 */
@Path("/encuestas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ServicioEncuestaPregunta {

    /**
     * Metodo para agregar preguntas a una encuesta Accedido mediante
     * /encuestas/{id}/pregunta} con el metodo POST
     *
     * Las preguntas deben estar previamente creadas
     *
     * @param _idEncuesta id de la encuesta
     * @param preguntaDto DTO de la pregunta
     * @return JSON success: {data, estado, code}; error: {mensaje, estado,
     * code}
     */
    @POST
    @Path("/{id}/pregunta")
    public Response addPreguntaToEncuesta(@PathParam("id") long _idEncuesta, PreguntaDto preguntaDto) {
        JsonObject data;

        try {

            DaoEncuesta daoEncuesta = new DaoEncuesta();
            Encuesta encuesta = daoEncuesta.find(_idEncuesta, Encuesta.class);

            for(PreguntaDto pregunta: preguntaDto.getPreguntas()) {

              DaoPregunta daoPregunta = new DaoPregunta();
              Pregunta question = daoPregunta.find(pregunta.getId(), Pregunta.class);

              encuesta.add_pregunta(question);

              PreguntaEncuesta preguntaEncuesta = new PreguntaEncuesta();
              preguntaEncuesta.set_encuesta(encuesta);
              preguntaEncuesta.set_pregunta(question);

              DaoPreguntaEncuesta daoPreguntaEncuesta = new DaoPreguntaEncuesta();
              daoPreguntaEncuesta.insert(preguntaEncuesta);
            }

            data = Json.createObjectBuilder()
                    .add("estado", "success")
                    .add("code", 200)
                    .build();

        } catch (javax.persistence.PersistenceException ex) {
            String mensaje = "Estas opciones ya se encuentran añadidas";
            data = Json.createObjectBuilder()
                    .add("mensaje", mensaje)
                    .add("estado", "error")
                    .add("code", 400)
                    .build();
            return Response.status(400).entity(data).build();
        } catch (Exception ex) {
            data = Json.createObjectBuilder().add("mensaje", ex.getMessage())
                    .add("estado", "error")
                    .add("code", 400)
                    .build();
            return Response.status(400).entity(data).build();
        }

        return Response.status(200).entity(data).build();

    }

    /**
     * Metodo para Obtener todas las preguntas de un encuesta especifica
     * Accedido mediante /encuestas/{id}/preguntas con el metodo GET
     *
     * @param _idEncuesta id de la encuesta
     *
     * @return JSON success: {data, code, estado}; error: {mensaje, estado,
     * code}
     *
     */
    @GET
    @Path("/{id}/preguntas")
    public Response getPreguntas(@PathParam("id") long _idEncuesta) {
      List<Pregunta> preguntas ;
      JsonObject data = null;
      try {

        DaoPreguntaEncuesta daoPreguntaEncuesta = new DaoPreguntaEncuesta();

        DaoEncuesta daoEncuesta = new DaoEncuesta();
        Encuesta encuesta = daoEncuesta.find(_idEncuesta, Encuesta.class);

        preguntas = daoPreguntaEncuesta.getPreguntasByEncuesta(encuesta);

        JsonArrayBuilder preguntasJson = Json.createArrayBuilder();
        JsonArrayBuilder opcionesJson = Json.createArrayBuilder();

        for (Pregunta pregunta : preguntas) {
          DaoPregunta daoPregunta = new DaoPregunta();
          Pregunta question = daoPregunta.find(pregunta.get_id(), Pregunta.class);

          for(Opcion opcion:question.getOpciones()) {

              DaoOpcion daoOpcion = new DaoOpcion();
              Opcion opc = daoOpcion.find(opcion.get_id(), Opcion.class);

              JsonObject option = Json.createObjectBuilder()
                .add("idPregunta", question.get_id())
                .add("idOpcion", opc.get_id())
                .add("descripcion", opc.get_descripcion()).build();

              opcionesJson.add(option);
            }


          JsonObject quest = Json.createObjectBuilder()
            .add("idPregunta", question.get_id())
            .add("descripcion", question.get_descripcionPregunta())
            .add("tipo", question.get_tipoPregunta())
            .add("min", question.get_min())
            .add("max", question.get_max())
            .add("opciones", opcionesJson).build();

          preguntasJson.add(quest);
        }

        data = Json.createObjectBuilder()
          .add("data", preguntasJson)
          .add("estado", "success")
          .add("code", 200)
          .build();

      } catch (javax.persistence.PersistenceException ex) {
        String mensaje = "Estas opciones ya se encuentran añadidas";
        data = Json.createObjectBuilder()
          .add("mensaje", mensaje)
          .add("estado", "error")
          .add("code", 400)
          .build();
        return Response.status(400).entity(data).build();
      } catch (Exception ex) {
       ex.printStackTrace();
      }

      return Response.status(200).entity(data).build();

    }

  /**
   * Metodo para Obtener las preguntas agregables a una encuesta
   * Accedido mediante /encuestas/{id}/preguntasagregables con el metodo GET
   *
   * @param _idEncuesta id de la encuesta
   *
   * @return JSON success: {preguntas, code, estado}; error: {mensaje, estado,
   * code}
   *
   */
    @GET
    @Path("/{id}/preguntasagregables")
    public Response getPreguntasAgregables(@PathParam("id") long _idEncuesta){

      JsonObject data;
      DaoEncuesta daoEncuesta = new DaoEncuesta();
      Encuesta encuesta;

      DaoPregunta daoPregunta = new DaoPregunta();
      List<Pregunta> preguntas = daoPregunta.findAll(Pregunta.class);

      DaoPreguntaEncuesta daoPreguntaEncuesta = new DaoPreguntaEncuesta();

      JsonArrayBuilder preguntaArray = Json.createArrayBuilder();

      try{
        encuesta = daoEncuesta.find(_idEncuesta, Encuesta.class);

        for(Pregunta preguntaList: preguntas){
          Pregunta preguntaAgregada = daoPreguntaEncuesta.getPreguntaAgregable(preguntaList, encuesta);
            if(preguntaAgregada ==  null){
              JsonObject question = Json.createObjectBuilder()
                .add("preguntaId", preguntaList.get_id())
                .add("descripcionPregunta", preguntaList.get_descripcionPregunta())
                .add("tipoPregunta", preguntaList.get_tipoPregunta()).build();

              preguntaArray.add(question);
          }
        }
        data = Json.createObjectBuilder()
          .add("estado", "success")
          .add("code", 200)
          .add("preguntas", preguntaArray).build();

        return Response.ok().entity(data).build();

      }catch (Exception ex){

        data = Json.createObjectBuilder()
          .add("estado", "error")
          .add("code", 400)
          .add("mensaje", ex.getMessage()).build();

        return Response.ok().entity(data).build();
      }
    }

}
