package ucab.dsw.servicio.pregunta;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import ucab.dsw.accesodatos.DaoOpcion;
import ucab.dsw.accesodatos.DaoPreguntaOpcion;
import ucab.dsw.dtos.OpcionDto;
import ucab.dsw.entidades.Opcion;
import ucab.dsw.entidades.PreguntaOpcion;

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
            pregunta.set_descripcionPregunta(preguntaDto.getDescripcionPregunta());
            pregunta.set_min(preguntaDto.getMin());
            pregunta.set_max(preguntaDto.getMax());
            pregunta.set_tipoPregunta(preguntaDto.getTipoPregunta());

            DaoPregunta daoPregunta = new DaoPregunta();
            Pregunta preguntaAgregada = daoPregunta.insert(pregunta);

            preguntaDto.setId(preguntaAgregada.get_id());

            List<OpcionDto> opcionesDtos = preguntaDto.getOpciones();
            List<Opcion> opciones = new ArrayList<>();

            if (opcionesDtos != null) {
                for (OpcionDto opcionDto : opcionesDtos) {
                    DaoOpcion dao = new DaoOpcion();
                    Opcion op = new Opcion();

                    op.set_descripcion(opcionDto.getDescripcion());
                    op = dao.insert(op);

                    opciones.add(op);
                    opcionDto.setId(op.get_id());
                }

                DaoPreguntaOpcion daoPreguntaOpcion = new DaoPreguntaOpcion();
                for (Opcion opcion : opciones) {
                    PreguntaOpcion preguntaOpcion = new PreguntaOpcion();
                    preguntaOpcion.set_opcion(opcion);
                    preguntaOpcion.set_pregunta(preguntaAgregada);

                    daoPreguntaOpcion.insert(preguntaOpcion);
                }
                
                preguntaDto.setOpciones(opcionesDtos);
            }


            data = Json.createObjectBuilder()
                    .add("data", preguntaDto.getId())
                    .add("estado", "success")
                    .add("code", 200)
                    .build();

        } catch (javax.persistence.PersistenceException ex) {
            String mensaje = "Esta pregunta ya se encuentra añadida";
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
                JsonObject JsonQuestion = question.toJson();
                preguntasArray.add(JsonQuestion);
            }
            data = Json.createObjectBuilder()
                    .add("data", preguntasArray)
                    .add("code", 200)
                    .add("estado", "success")
                    .build();

        } catch (Exception ex) {

            data = Json.createObjectBuilder()
                    .add("mensaje", ex.getMessage())
                    .add("estado", "error")
                    .add("code", 400)
                    .build();
            return Response.ok().entity(data).build();
        }
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

            JsonObject JsonPregunta = pregunta.toJson();

            data = Json.createObjectBuilder()
                    .add("data", JsonPregunta)
                    .add("estado", "success")
                    .add("code", 200)
                    .build();
        } catch (Exception ex) {

            data = Json.createObjectBuilder()
                    .add("mensaje", ex.getMessage())
                    .add("estado", "error")
                    .add("code", 400)
                    .build();
            return Response.ok().entity(data).build();
        }
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
//    @PUT
//    @Path("/{id}")
//    public Response updatePregunta(@PathParam("id") long _id, PreguntaDto preguntaDto) {
//
//        Pregunta pregunta = null;
//        JsonObject data = null;
//
//        try {
//
//            DaoPregunta dao = new DaoPregunta();
//            pregunta = dao.find(_id, Pregunta.class);
//
//            pregunta.set_descripcionPregunta(preguntaDto.getDescripcionPregunta());
//            pregunta.set_tipoPregunta(preguntaDto.getTipoPregunta());
//            pregunta.set_min(preguntaDto.getMin());
//            pregunta.set_max(preguntaDto.getMax());
//            pregunta = dao.update(pregunta);
//
//            JsonObject JsonPregunta = pregunta.toJson();
//
//            data = Json.createObjectBuilder().add("data", JsonPregunta)
//                    .add("estado", "success")
//                    .add("code", 200)
//                    .build();
//
//        } catch (Exception ex) {
//
//            data = Json.createObjectBuilder()
//                    .add("mensaje", ex.getMessage())
//                    .add("estado", "error")
//                    .add("code", 400)
//                    .build();
//            return Response.ok().entity(data).build();
//        }
//        return Response.ok().entity(data).build();
//    }

}