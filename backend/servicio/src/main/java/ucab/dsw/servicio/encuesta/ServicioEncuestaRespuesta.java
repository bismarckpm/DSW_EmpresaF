package ucab.dsw.servicio.encuesta;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ucab.dsw.accesodatos.DaoEncuesta;
import ucab.dsw.accesodatos.DaoEncuestado;
import ucab.dsw.accesodatos.DaoOpcion;
import ucab.dsw.accesodatos.DaoPregunta;
import ucab.dsw.accesodatos.DaoPreguntaEncuesta;
import ucab.dsw.accesodatos.DaoRespuesta;
import ucab.dsw.accesodatos.DaoRespuestaOpcion;
import ucab.dsw.dtos.OpcionDto;
import ucab.dsw.dtos.PreguntaDto;
import ucab.dsw.dtos.RespuestaDto;
import ucab.dsw.entidades.Encuesta;
import ucab.dsw.entidades.Encuestado;
import ucab.dsw.entidades.Opcion;
import ucab.dsw.entidades.Pregunta;
import ucab.dsw.entidades.PreguntaEncuesta;
import ucab.dsw.entidades.Respuesta;
import ucab.dsw.entidades.RespuestaOpcion;

/**
 * Clase para gestionar las respuestas de una pregunta de una encuesta
 *
 */
@Path("/encuestas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ServicioEncuestaRespuesta {

    /**
     * Metodo para agregar una respuesta a una pregunta de una encuesta Accedido
     * mediante /encuestas/{id}/preguntas/{id}/respuestas} con el metodo POST
     *
     * Las preguntas deben estar previamente creadas
     *
     * @param _idEncuesta id de la encuesta
     * @param _idPregunta id de la pregunta
     * @param respuestaDto Lista de DTOs de las respuestas
     * @return JSON success: {data, estado, code}; error: {mensaje, estado,
     * code}
     */
    @POST
    @Path("/{idEncuesta}/preguntas/{idPregunta}/respuesta")
    public Response addRespuesta(
            @PathParam("idEncuesta") long _idEncuesta,
            @PathParam("idPregunta") long _idPregunta,
            RespuestaDto respuestaDto) {

        JsonObject data;
        try {

            Respuesta respuesta = new Respuesta();
            DaoRespuesta daoRespuestas = new DaoRespuesta();

            DaoEncuesta daoEncuesta = new DaoEncuesta();
            DaoPregunta daoPregunta = new DaoPregunta();

            Encuesta encuesta = null;
            Pregunta pregunta = null;

            try {
                encuesta = daoEncuesta.find(_idEncuesta, Encuesta.class);
            } catch (Exception e) {
                throw new Exception("La encuesta no existe");
            }

            try {
                pregunta = daoPregunta.find(_idPregunta, Pregunta.class);
            } catch (Exception e) {
                throw new Exception("La pregunta no existe");
            }

            DaoPreguntaEncuesta daoPreguntaEncuesta = new DaoPreguntaEncuesta();
            List<PreguntaEncuesta> preguntasEncuestas = daoPreguntaEncuesta.findAll(PreguntaEncuesta.class);

            PreguntaEncuesta preguntaEncuesta = null;
            boolean hasPregunta = false;

            for (PreguntaEncuesta pre_enc : preguntasEncuestas) {
                if (encuesta.get_id() == pre_enc.get_encuesta().get_id() && pregunta.get_id() == pre_enc.get_pregunta().get_id()) {
                    preguntaEncuesta = pre_enc;
                    hasPregunta = true;
                    break;
                }
            }

            if (!hasPregunta) {
                throw new Exception("La pregunta no esta asociada a la encuesta");
            }

            DaoEncuestado daoEncuestado = new DaoEncuestado();
            Encuestado encuestado = null;

            try {
                encuestado = daoEncuestado.find(respuestaDto.getEncuestado().getId(), Encuestado.class);
            } catch (Exception e) {
                throw new Exception("El encuestado no existe");
            }

            Date fecha = new Date();

//            validar opciones
            List<OpcionDto> opcionesDto = respuestaDto.getOpciones();
            List<Opcion> opciones = new ArrayList<>();
            DaoRespuestaOpcion daoRespuestaOpcion = new DaoRespuestaOpcion();
            DaoOpcion daoOpcion = new DaoOpcion();

            for (OpcionDto opcionDto : opcionesDto) {
                Opcion opcion = null;

                try {
                    opcion = daoOpcion.find(opcionDto.getId(), Opcion.class);
                } catch (Exception e) {
                    throw new Exception("Id de opción: " + opcionDto.getId() + " no existe");
                }

                opciones.add(opcion);
            }

            respuesta.set_fecha(fecha);
            respuesta.set_descripcion(respuestaDto.getDescripcion());
            respuesta.set_rango(respuestaDto.getRango());
            respuesta.set_encuestado(encuestado);
            respuesta.set_preguntaEncuesta(preguntaEncuesta);

            respuesta = daoRespuestas.insert(respuesta);

            for (Opcion opcion : opciones) {

                RespuestaOpcion respuestaOpcion = new RespuestaOpcion();
                respuestaOpcion.set_opcion(opcion);
                respuestaOpcion.set_respuesta(respuesta);

                daoRespuestaOpcion.insert(respuestaOpcion);
            }

            data = Json.createObjectBuilder()
                    .add("data", respuesta.get_id())
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
