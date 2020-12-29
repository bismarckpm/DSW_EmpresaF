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
     * Metodo para agregar unas pregunta a una encuesta Accedido mediante
     * /encuestas/{id}/preguntas} con el metodo POST
     *
     * Las preguntas deben estar previamente creadas
     *
     * @param _idEncuesta id de la encuesta
     * @param preguntasDto Lista de DTOs de las preguntas
     * @return JSON success: {data, estado, code}; error: {mensaje, estado,
     * code}
     */
    @POST
    @Path("/{id}/preguntas")
    public Response addPreguntasToEncuesta(@PathParam("id") long _idEncuesta, List<PreguntaDto> preguntasDto) {
        JsonObject data;
        try {
            DaoPreguntaEncuesta daoPreguntaEncuesta = new DaoPreguntaEncuesta();

            DaoEncuesta daoEncuesta = new DaoEncuesta();
            Encuesta encuesta = daoEncuesta.find(_idEncuesta, Encuesta.class);

            List<Long> preguntasEncuestas = new ArrayList<>();

            for (PreguntaDto preguntaDto : preguntasDto) {
                Pregunta pregunta = new Pregunta();
                pregunta.set_id(preguntaDto.getId());

                PreguntaEncuesta preguntaEncuesta = new PreguntaEncuesta();

                preguntaEncuesta.set_encuesta(encuesta);
                preguntaEncuesta.set_pregunta(pregunta);

                preguntaEncuesta = daoPreguntaEncuesta.insert(preguntaEncuesta);

                preguntasEncuestas.add(preguntaEncuesta.get_id());

            }

            JsonArrayBuilder preguntasEncuestasJson = Json.createArrayBuilder(preguntasEncuestas);

            data = Json.createObjectBuilder()
                    .add("data", preguntasEncuestasJson)
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
     * Metodo para agregar una pregunta a una encuesta Accedido mediante
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

            Pregunta pregunta = new Pregunta(preguntaDto.getId());

            encuesta.add_pregunta(pregunta);

            PreguntaEncuesta preguntaEncuesta = new PreguntaEncuesta();
            preguntaEncuesta.set_encuesta(encuesta);
            preguntaEncuesta.set_pregunta(pregunta);

            DaoPreguntaEncuesta daoPreguntaEncuesta = new DaoPreguntaEncuesta();
            preguntaEncuesta = daoPreguntaEncuesta.insert(preguntaEncuesta);

            data = Json.createObjectBuilder()
                    .add("data", preguntaEncuesta.get_id())
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
      List<Pregunta> preguntas = null;
      JsonObject data = null;
      try {

        DaoPreguntaEncuesta daoPreguntaEncuesta = new DaoPreguntaEncuesta();

        DaoEncuesta daoEncuesta = new DaoEncuesta();
        Encuesta encuesta = daoEncuesta.find(_idEncuesta, Encuesta.class);

        preguntas = daoPreguntaEncuesta.getPreguntasByEncuesta(encuesta);

        JsonArrayBuilder preguntasJson = Json.createArrayBuilder();

        for (Pregunta pregunta : preguntas) {
          DaoPregunta daoPregunta = new DaoPregunta();
          Pregunta question = daoPregunta.find(pregunta.get_id(), Pregunta.class);
          preguntasJson.add(question.toJson());
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

}
