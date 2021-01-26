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
import ucab.dsw.logica.comando.encuesta.ComandoAddPreguntaEncuesta;
import ucab.dsw.logica.comando.encuesta.ComandoGetPreguntasEncuesta;
import ucab.dsw.logica.exepcionhandler.ManejadorExcepcion;
import ucab.dsw.logica.fabrica.Fabrica;

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

        try {

          ComandoAddPreguntaEncuesta comandoAddPreguntaEncuesta = Fabrica.crearComandoConAmbos( ComandoAddPreguntaEncuesta.class, _idEncuesta, preguntaDto);
          comandoAddPreguntaEncuesta.execute();

          return Response.ok().entity(comandoAddPreguntaEncuesta.getResultado()).build();

        } catch (javax.persistence.PersistenceException ex) {

          String mensaje = "Hay opciones que ya se encuentran añadidas";
          ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

          return Response.status(400).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 400)).build();

        } catch (Exception ex) {

          String mensaje = "Ha ocurrido un error en el servidor";
          ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

          return Response.status(500).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 500)).build();

        }

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

      try {

        ComandoGetPreguntasEncuesta comandoGetPreguntasEncuesta = Fabrica.crearComandoConId(ComandoGetPreguntasEncuesta.class, _idEncuesta);
        comandoGetPreguntasEncuesta.execute();

        return Response.ok().entity(comandoGetPreguntasEncuesta.getResultado()).build();

      } catch (Exception ex) {

        String mensaje = "Ha ocurrido un error en el servidor";
        ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

        return Response.status(500).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 500)).build();

      }

    }

    /**
    * Metodo para Obtener las preguntas agregables a una encuesta previamente creada
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
