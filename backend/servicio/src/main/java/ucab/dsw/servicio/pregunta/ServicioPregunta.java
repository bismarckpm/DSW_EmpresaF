package ucab.dsw.servicio.pregunta;

import ucab.dsw.accesodatos.DaoPregunta;
import ucab.dsw.dtos.PreguntaDto;
import ucab.dsw.entidades.Pregunta;
import ucab.dsw.servicio.AplicacionBase;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Clase para gestionar las preguntas
 *
 */
@Path("/preguntas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ServicioPregunta extends AplicacionBase {

    /**
     * Metodo para agregar una pregunta Accedido mediante /preguntas/ con el
     * metodo POST
     *
     * @param preguntaDto DTO de la pregunta
     * @return JSON success: {pregunta, estado, code}; error: {mensaje, estado,
     * code}
     */
    @POST
    @Path("/")
    public Response addPregunta(PreguntaDto preguntaDto) {
        JsonObject data;
        try {

            Pregunta pregunta = new Pregunta();
            pregunta.setDescripcion(preguntaDto.getDescripcion());
            pregunta.setMin(preguntaDto.getMin());
            pregunta.setMax(preguntaDto.getMax());
            pregunta.setTipoPregunta(preguntaDto.getTipoPregunta());

            DaoPregunta daoPregunta = new DaoPregunta();
            Pregunta preguntaAgregada = daoPregunta.insert(pregunta);

            preguntaDto.setId(preguntaAgregada.getId());

            data = Json.createObjectBuilder().add("pregunta", preguntaDto.getId())
                    .add("estado", "success")
                    .add("code", 200)
                    .build();

        } catch (javax.persistence.PersistenceException ex) {
            String mensaje = "Esta pregunta ya se encuentra añadida";
            data = Json.createObjectBuilder().add("mensaje", mensaje)
                    .add("estado", "error")
                    .add("code", 400)
                    .build();

            System.out.println(data);
            return Response.ok().entity(data).build();
        } catch (Exception ex) {
            data = Json.createObjectBuilder().add("mensaje", ex.getMessage())
                    .add("estado", "error")
                    .add("code", 400)
                    .build();

            System.out.println(data);
            return Response.ok().entity(data).build();
        }

        System.out.println(data);
        return Response.ok().entity(data).build();

    }

    /**
     * Metodo para Obtener todas las preguntas Accedido. mediante /preguntas/
     * con el metodo GET
     *
     * @return JSON success: {code, estado, preguntas}; error: {mensaje, estado,
     * code}
     *
     */
    @GET
    @Path("/")
    public Response getPreguntas() {

        List<Pregunta> preguntas = null;
        JsonObject data;

        try {

            DaoPregunta dao = new DaoPregunta();
            preguntas = dao.findAll(Pregunta.class);

            JsonArrayBuilder preguntasArray = Json.createArrayBuilder();

            for (Pregunta question : preguntas) {
                JsonObject JsonQuestion = Json.createObjectBuilder()
                        .add("id", question.getId())
                        .add("descripcion", question.getDescripcion())
                        .add("tipo", question.getTipoPregunta())
                        .add("min", question.getMin())
                        .add("max", question.getMax())
                        .build();

                preguntasArray.add(JsonQuestion);
            }
            data = Json.createObjectBuilder()
                    .add("code", 200)
                    .add("estado", "success")
                    .add("preguntas", preguntasArray).build();

        } catch (Exception ex) {

            data = Json.createObjectBuilder()
                    .add("mensaje", ex.getMessage())
                    .add("estado", "error")
                    .add("code", 400)
                    .build();

            System.out.println(data);
            return Response.ok().entity(data).build();
        }
        System.out.println(data);
        return Response.ok().entity(data).build();
    }

    /**
     * Metodo para Obtener una pregunta especifica. Accedido mediante
     * /preguntas/{id} con el metodo GET
     *
     * @param _id identificador de la pregunta solicitada
     * @return JSON success: {id, descripcion, tipo, min, max, estado, code};
     * error: {mensaje, estado, code}
     *
     */
    @GET
    @Path("/{id}")
    public Response getPregunta(@PathParam("id") long _id) {

        Pregunta pregunta = null;
        JsonObject data;

        try {
            DaoPregunta dao = new DaoPregunta();
            pregunta = dao.find(_id, Pregunta.class);

            JsonObject JsonPregunta = Json.createObjectBuilder()
                    .add("id", pregunta.getId())
                    .add("descripcion", pregunta.getDescripcion())
                    .add("tipo", pregunta.getTipoPregunta())
                    .add("min", pregunta.getMin())
                    .add("max", pregunta.getMax())
                    .add("estado", "success")
                    .add("code", 200)
                    .build();

            data = Json.createObjectBuilder().add("data", JsonPregunta)
                    .add("estado", "success")
                    .add("code", 200)
                    .build();

        } catch (Exception ex) {

            data = Json.createObjectBuilder()
                    .add("mensaje", ex.getMessage())
                    .add("estado", "error")
                    .add("code", 400)
                    .build();

            System.out.println(data);
            return Response.ok().entity(data).build();
        }
        System.out.println(data);
        return Response.ok().entity(data).build();
    }

    /**
     * Metodo para Obtener una pregunta especifica. Accedido mediante
     * /preguntas/{id} con el metodo GET
     *
     * @param _id identificador de la pregunta solicitada
     * @param preguntaDto DTO de la pregunta
     * @return JSON success: {id, descripcion, tipo, min, max}; error: {mensaje,
     * estado, code}
     *
     */
    @PUT
    @Path("/{id}")
    public Response updatePregunta(@PathParam("id") long _id, PreguntaDto preguntaDto) {

        Pregunta pregunta = null;
        JsonObject data = null;

        try {

            DaoPregunta dao = new DaoPregunta();
            pregunta = dao.find(_id, Pregunta.class);

            pregunta.setDescripcion(preguntaDto.getDescripcion());
            pregunta.setTipoPregunta(preguntaDto.getTipoPregunta());
            pregunta.setMin(preguntaDto.getMin());
            pregunta.setMax(preguntaDto.getMax());
            pregunta = dao.update(pregunta);

            JsonObject JsonPregunta = Json.createObjectBuilder()
                    .add("id", pregunta.getId())
                    .add("descripcion", pregunta.getDescripcion())
                    .add("tipo", pregunta.getTipoPregunta())
                    .add("min", pregunta.getMin())
                    .add("max", pregunta.getMax())
                    .build();

            data = Json.createObjectBuilder().add("data", JsonPregunta)
                    .add("estado", "success")
                    .add("code", 200)
                    .build();

        } catch (Exception ex) {

            data = Json.createObjectBuilder()
                    .add("mensaje", ex.getMessage())
                    .add("estado", "error")
                    .add("code", 400)
                    .build();

            System.out.println(data);
            return Response.ok().entity(data).build();
        }
        System.out.println(data);
        return Response.ok().entity(data).build();
    }

}
