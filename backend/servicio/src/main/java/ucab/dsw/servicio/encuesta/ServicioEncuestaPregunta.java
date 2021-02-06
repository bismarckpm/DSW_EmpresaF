package ucab.dsw.servicio.encuesta;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import ucab.dsw.dtos.PreguntaDto;
import ucab.dsw.excepciones.ProblemaExcepcion;
import ucab.dsw.logica.comando.autenticacion.ComandoDecode;
import ucab.dsw.logica.comando.encuesta.ComandoAddPreguntaEncuesta;
import ucab.dsw.logica.comando.encuesta.ComandoGetPreguntasAgregables;
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
    public Response addPreguntaToEncuesta(@HeaderParam("authorization") String token, @PathParam("id") long _idEncuesta, PreguntaDto preguntaDto) {

        try {

          ComandoDecode comandoDecode = Fabrica.crearComandoSeguridad(ComandoDecode.class, token);
          comandoDecode.execute();

          ComandoAddPreguntaEncuesta comandoAddPreguntaEncuesta = Fabrica.crearComandoConAmbos( ComandoAddPreguntaEncuesta.class, _idEncuesta, preguntaDto);
          comandoAddPreguntaEncuesta.execute();

          return Response.ok().entity(comandoAddPreguntaEncuesta.getResultado()).build();

        } catch (ProblemaExcepcion ex){

          ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

          return  Response.status(400).entity(manejadorExcepcion.getMensajeError(ex.getMensaje_soporte(), ex.getMensaje(), "error", 400)).build();

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
    public Response getPreguntas(@HeaderParam("authorization") String token, @PathParam("id") long _idEncuesta) {

      try {

        ComandoDecode comandoDecode = Fabrica.crearComandoSeguridad(ComandoDecode.class, token);
        comandoDecode.execute();

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
    public Response getPreguntasAgregables(@HeaderParam("authorization") String token, @PathParam("id") long _idEncuesta){

      try{

        ComandoDecode comandoDecode = Fabrica.crearComandoSeguridad(ComandoDecode.class, token);
        comandoDecode.execute();

        ComandoGetPreguntasAgregables comandoGetPreguntasAgregables = Fabrica.crearComandoConId(ComandoGetPreguntasAgregables.class, _idEncuesta);
        comandoGetPreguntasAgregables.execute();

        return Response.ok().entity(comandoGetPreguntasAgregables.getResultado()).build();

      }catch (Exception ex){

        String mensaje = "Ha ocurrido un error en el servidor";
        ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

        return Response.status(500).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 500)).build();

      }

    }

}
