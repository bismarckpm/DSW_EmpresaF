package ucab.dsw.servicio.encuesta;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
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
import ucab.dsw.accesodatos.DaoOpcion;
import ucab.dsw.accesodatos.DaoPregunta;
import ucab.dsw.accesodatos.DaoPreguntaEncuesta;
import ucab.dsw.accesodatos.DaoPreguntaOpcion;
import ucab.dsw.dtos.PreguntaDto;
import ucab.dsw.dtos.PreguntaEncuestaDto;
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

    /**
     * Metodo para Obtener todas las preguntas que no tiene una encuesta
     * Accedido mediante /encuestas/{id}/preguntas-agregables con el metodo GET
     *
     * @param _idEncuesta id de la encuesta
     *
     * @return JSON success: {data, code, estado}; error: {mensaje, estado,
     * code}
     *
     */
    @GET
    @Path("/{id}/preguntas-agregables")
    public Response getPreguntasAgregables(@PathParam("id") long _idEncuesta) {
        JsonObject data;
        try {

            DaoEncuesta daoEncuesta = new DaoEncuesta();
            Encuesta encuesta = null;

            try {
                encuesta = daoEncuesta.find(_idEncuesta, Encuesta.class);
            } catch (Exception e) {
                throw new Exception("La encuesta con id: " + _idEncuesta + " no existe.");
            }

            DaoPregunta daoPregunta = new DaoPregunta();
            DaoPreguntaEncuesta daoPreguntaEncuesta = new DaoPreguntaEncuesta();

            List<Pregunta> preguntasList = daoPregunta.findAll(Pregunta.class);
            List<PreguntaEncuesta> preguntasEncuestasList = daoPreguntaEncuesta.findAll(PreguntaEncuesta.class);

            for (Iterator<Pregunta> iterator = preguntasList.iterator(); iterator.hasNext();) {
                Pregunta pregunta = iterator.next();
                for (PreguntaEncuesta preguntaEncuesta : preguntasEncuestasList) {
                    boolean isPregunta = pregunta.get_id() == preguntaEncuesta.get_pregunta().get_id();
                    boolean isEncuesta = encuesta.get_id() == preguntaEncuesta.get_encuesta().get_id();

                    if (isPregunta && isEncuesta) {
                        iterator.remove();
                    }
                }
            }

            JsonArrayBuilder preguntasJson = Json.createArrayBuilder();

            for (Pregunta pregunta : preguntasList) {
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
