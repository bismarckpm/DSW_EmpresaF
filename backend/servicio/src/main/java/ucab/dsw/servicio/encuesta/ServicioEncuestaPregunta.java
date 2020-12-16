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
import ucab.dsw.accesodatos.DaoEncuesta;
import ucab.dsw.accesodatos.DaoPreguntaEncuesta;
import ucab.dsw.dtos.PreguntaDto;
import ucab.dsw.entidades.PreguntaEncuesta;
import ucab.dsw.entidades.Encuesta;
import ucab.dsw.entidades.Pregunta;

/**
 * Clase para gestionar las preguntas de una encuesta
 *
 */
@Path("/encuestas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ServicioEncuestaPregunta {

    /**
     * Metodo para agregar una pregunta a una encuesta Accedido mediante
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

            DaoEncuesta daoEncuesta = new DaoEncuesta();
            Encuesta encuesta = daoEncuesta.find(_idEncuesta, Encuesta.class);

            for (PreguntaDto preguntaDto : preguntasDto) {
                Pregunta pregunta = new Pregunta();
                pregunta.set_id(preguntaDto.getId());
                encuesta.add_pregunta(pregunta);
            }

            encuesta = daoEncuesta.update(encuesta);

            data = Json.createObjectBuilder()
                    .add("data", encuesta.get_id())
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
        JsonObject data;
        try {

            DaoEncuesta daoEncuesta = new DaoEncuesta();
            Encuesta encuesta = daoEncuesta.find(_idEncuesta, Encuesta.class);

            preguntas = encuesta.getPreguntas();

            JsonArrayBuilder preguntasJson = Json.createArrayBuilder();

            for (Pregunta pregunta : preguntas) {
                preguntasJson.add(pregunta.toJson());
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
            data = Json.createObjectBuilder().add("mensaje", ex.getMessage())
                    .add("estado", "error")
                    .add("code", 400)
                    .build();
            return Response.status(400).entity(data).build();
        }

        return Response.status(200).entity(data).build();

    }

}