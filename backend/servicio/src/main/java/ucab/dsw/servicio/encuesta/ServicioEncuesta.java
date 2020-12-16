package ucab.dsw.servicio.encuesta;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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
import ucab.dsw.accesodatos.DaoPregunta;
import ucab.dsw.accesodatos.DaoPreguntaEncuesta;
import ucab.dsw.accesodatos.DaoPreguntaOpcion;
import ucab.dsw.dtos.EncuestaDto;
import ucab.dsw.entidades.Encuesta;
import ucab.dsw.entidades.Pregunta;
import ucab.dsw.entidades.PreguntaEncuesta;
import ucab.dsw.entidades.Subcategoria;

/**
 * Clase para gestionar las encuestas
 *
 */
@Path("/encuestas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ServicioEncuesta {

    /**
     * Metodo para agregar una encuesta Accedido mediante /encuestas/ con el
     * metodo POST
     *
     * SubCaterogia debe estar previamente creada
     *
     * @param encuestaDto DTO de la encuesta
     * @return JSON success: {pregunta, estado, code}; error: {mensaje, estado,
     * code}
     */
    @POST
    @Path("/")
    public Response addEncuesta(EncuestaDto encuestaDto) {
        JsonObject data;
        try {

            Encuesta encuesta = new Encuesta();
            Subcategoria subcategoria = new Subcategoria(encuestaDto.getSubcategoria().getId());

            encuesta.set_subcategoria(subcategoria);

            DaoEncuesta dao = new DaoEncuesta();
            Encuesta encuestaAgregada = dao.insert(encuesta);

            encuestaDto.setId(encuestaAgregada.get_id());

            data = Json.createObjectBuilder()
                    .add("data", encuestaDto.getId())
                    .add("estado", "success")
                    .add("code", 200)
                    .build();

        } catch (javax.persistence.PersistenceException ex) {
            String mensaje = "Esta encuesta ya se encuentra añadida";
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
     * Metodo para Obtener todas las encuestas Accedido. mediante /encuestas/
     * con el metodo GET
     *
     * @return JSON success: {data, code, estado}; error: {mensaje, estado,
     * code}
     *
     */
    @GET
    @Path("/")
    public Response getEncuestas() {

        List<Encuesta> encuestas = null;
        JsonObject data;

        try {

            DaoEncuesta dao = new DaoEncuesta();
            encuestas = dao.findAll(Encuesta.class);


            JsonArrayBuilder encuestasArray = Json.createArrayBuilder();
            JsonArrayBuilder preguntasArray = Json.createArrayBuilder();

            for (Encuesta encuesta : encuestas) {
              DaoPreguntaEncuesta daoPreguntaEncuesta = new DaoPreguntaEncuesta();
              List<PreguntaEncuesta> preguntaEncuestas = daoPreguntaEncuesta.findAll(PreguntaEncuesta.class);

              for(PreguntaEncuesta preguntaEncuesta:preguntaEncuestas){
                if(encuesta.get_id() == preguntaEncuesta.get_encuesta().get_id()){

                  JsonObject JsonQuestions = Json.createObjectBuilder()
                    .add("preguntaId", preguntaEncuesta.get_pregunta().get_id())
                    .add("descripcionPregunta", preguntaEncuesta.get_pregunta().get_descripcionPregunta())
                    .add("tipoPregunta", preguntaEncuesta.get_pregunta().get_tipoPregunta())
                    .add("max", preguntaEncuesta.get_pregunta().get_max())
                    .add("min", preguntaEncuesta.get_pregunta().get_min())
                    .build();

                  preguntasArray.add(JsonQuestions);
                }
              }

              JsonObject JsonEncuesta = Json.createObjectBuilder()
                .add("encuestaId", encuesta.get_id())
                .add("subcategoriaId", encuesta.get_subcategoria().get_id())
                .add("subcategoria", encuesta.get_subcategoria().get_nombreSubcategoria())
                .add("preguntas", preguntasArray)
                .build();

              encuestasArray.add(JsonEncuesta);
            }

            data = Json.createObjectBuilder()
                    .add("encuestas", encuestasArray)
                    .add("code", 200)
                    .add("estado", "success")
                    .build();

        } catch (Exception ex) {

            data = Json.createObjectBuilder()
                    .add("mensaje", ex.getMessage())
                    .add("estado", "error")
                    .add("code", 400)
                    .build();
            return Response.status(400).entity(data).build();
        }
        return Response.ok().entity(data).build();
    }


    /**
     * Metodo para Obtener una encuesta en especifica Accedido mediante /encuestas/{id}
     * con el metodo GET
     *
     * @param _id id de la encuesta
     *
     * @return JSON success: {data, code, estado}; error: {mensaje, estado,
     * code}
     *
     */
    @GET
    @Path("/{id}")
    public Response getEncuesta(@PathParam("id") long _id) {

        Encuesta encuesta = null;
        JsonObject data;

        try {

            DaoEncuesta dao = new DaoEncuesta();
            encuesta = dao.find(_id, Encuesta.class);

            JsonObject encuestaJson = encuesta.toJson();

            data = Json.createObjectBuilder()
                    .add("data", encuestaJson)
                    .add("code", 200)
                    .add("estado", "success")
                    .build();

        } catch (Exception ex) {

            data = Json.createObjectBuilder()
                    .add("mensaje", ex.getMessage())
                    .add("estado", "error")
                    .add("code", 400)
                    .build();
            return Response.status(400).entity(data).build();
        }
        return Response.ok().entity(data).build();
    }

}
