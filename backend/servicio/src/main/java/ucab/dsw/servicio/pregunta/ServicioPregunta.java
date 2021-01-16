package ucab.dsw.servicio.pregunta;

import java.math.BigDecimal;
import java.util.ArrayList;

import ucab.dsw.accesodatos.*;
import ucab.dsw.dtos.PreguntaDto;
import ucab.dsw.entidades.*;
import ucab.dsw.excepciones.LimiteExcepcion;
import ucab.dsw.servicio.AplicacionBase;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import ucab.dsw.dtos.OpcionDto;

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

            if(preguntaDto.getMin() > preguntaDto.getMax()){
              throw new LimiteExcepcion("El limite superior no puede ser menor al limite inferior");
            }else {
              pregunta.set_min(preguntaDto.getMin());
              pregunta.set_max(preguntaDto.getMax());
            }

            pregunta.set_tipoPregunta(preguntaDto.getTipoPregunta());

            DaoPregunta daoPregunta = new DaoPregunta();
            Pregunta preguntaAgregada = daoPregunta.insert(pregunta);

          if(preguntaDto.getTipoPregunta().equals("desarrollo")){
            DaoOpcion daoOpcion = new DaoOpcion();
            Integer id = 8;
            Opcion opcion = daoOpcion.find(id.longValue(), Opcion.class);

            DaoPreguntaOpcion daoPreguntaOpcion = new DaoPreguntaOpcion();
            PreguntaOpcion preguntaOpcion = new PreguntaOpcion();
            preguntaOpcion.set_pregunta(preguntaAgregada);
            preguntaOpcion.set_opcion(opcion);

            daoPreguntaOpcion.insert(preguntaOpcion);
          }

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
            JsonArrayBuilder opcionesArray = Json.createArrayBuilder();

           /* for (Pregunta question : preguntas) {
                JsonObject JsonQuestion = question.toJson();
                preguntasArray.add(JsonQuestion);
            }*/

            for (Pregunta question : preguntas) {
                DaoPreguntaOpcion daoPreguntaOpcion = new DaoPreguntaOpcion();
                List<PreguntaOpcion> preguntaOpciones = daoPreguntaOpcion.findAll(PreguntaOpcion.class);

                for(PreguntaOpcion preguntaOpcion:preguntaOpciones){
                  if(question.get_id() == preguntaOpcion.get_pregunta().get_id()){

                    JsonObject JsonOptions = Json.createObjectBuilder()
                      .add("opcionId", preguntaOpcion.get_opcion().get_id())
                      .add("opcion", preguntaOpcion.get_opcion().get_descripcion()).build();

                    opcionesArray.add(JsonOptions);
                  }
                }

              JsonObject JsonQuestion = Json.createObjectBuilder()
                .add("preguntaId", question.get_id())
                .add("descripcionPregunta", question.get_descripcionPregunta())
                .add("tipo", question.get_tipoPregunta())
                .add("min", question.get_min())
                .add("max", question.get_max())
                .add("opciones", opcionesArray)
                .build();

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
   * Metodo para Obtener las preguntas sugeridas para una encuesta. Accedido mediante
   * /preguntas/{idEncuesta}/sugerencias con el metodo GET
   *
   * @param _idEncuesta identificador de la encuesta
   * @return JSON success: {preguntas, estado, code};
   * error: {mensaje, estado, code}
   *
   */
    @GET
    @Path("/{idEncuesta}/sugerencias")
    public Response getPreguntasSugeridas(@PathParam("idEncuesta") long _idEncuesta){

    JsonObject data;
    DaoEncuesta daoEncuesta = new DaoEncuesta();
    Encuesta encuesta;
    List<Encuesta> encuestas;

    DaoSubcategoria daoSubcategoria = new DaoSubcategoria();
    Subcategoria subcategoria;

    JsonArrayBuilder preguntasArray = Json.createArrayBuilder();
    ArrayList<String> arrayListPregunta = new ArrayList<>();

    try {
      encuesta = daoEncuesta.find(_idEncuesta, Encuesta.class);
      subcategoria = daoSubcategoria.find(encuesta.get_subcategoria().get_id(), Subcategoria.class);

      encuestas = daoEncuesta.getEncuestasBySubcategoria(subcategoria);

      for (Encuesta encuest: encuestas){
        for(Pregunta pregunta: encuest.getPreguntas()) {
          if(!arrayListPregunta.contains(pregunta.get_descripcionPregunta())){
            JsonObject question = Json.createObjectBuilder()
              .add("preguntaId", pregunta.get_id())
              .add("subcategoria", subcategoria.get_nombreSubcategoria())
              .add("descripcionPregunta", pregunta.get_descripcionPregunta())
              .add("tipoPregunta", pregunta.get_tipoPregunta()).build();
            preguntasArray.add(question);
            arrayListPregunta.add(pregunta.get_descripcionPregunta());
          }
        }
      }
      data = Json.createObjectBuilder()
        .add("preguntas", preguntasArray)
        .add("estado", "success")
        .add("code", 200)
        .build();

      return Response.ok().entity(data).build();

    }catch (Exception ex){
      data = Json.createObjectBuilder()
        .add("mensaje", ex.getMessage())
        .add("estado", "error")
        .add("code", 400)
        .build();

      return Response.ok().entity(data).build();
    }
  }

}
