package ucab.dsw.servicio.encuesta;

import java.util.List;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ucab.dsw.accesodatos.*;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.*;
import ucab.dsw.excepciones.ProblemaExcepcion;
import ucab.dsw.excepciones.RangoExcepcion;
import ucab.dsw.logica.comando.autenticacion.ComandoDecode;
import ucab.dsw.logica.comando.encuesta.ComandoAddRespuestaEncuesta;
import ucab.dsw.logica.comando.encuesta.ComandoGetRespuestasEncuesta;
import ucab.dsw.logica.exepcionhandler.ManejadorExcepcion;
import ucab.dsw.logica.fabrica.Fabrica;

/**
 * Clase para gestionar las respuestas de una pregunta de una encuesta
 *
 */
@Path("/encuestas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class  ServicioEncuestaRespuesta {

  /**
   * Metodo para agregar las respuestas de una encuesta
   * Accedido mediante /encuestas/respuestas/{idEncuesta} con el metodo POST
   *
   * @param idEncuesta id de la encuesta
   * @param baseDto DTO base para recibir un arreglo de respuestas
   *
   * @return JSON success: {estado, mensaje, code}; error: {mensaje, estado,
   * code}
   *
   */
    @POST
    @Path("/respuesta/{idEncuesta}")
    public Response addRespuesta(@HeaderParam("authorization") String token, @PathParam("idEncuesta") long idEncuesta, BaseRespuestaDto baseDto){

      try{

        ComandoDecode comandoDecode = Fabrica.crearComandoSeguridad(ComandoDecode.class, token);
        comandoDecode.execute();

        ComandoAddRespuestaEncuesta comandoAddRespuestaEncuesta = Fabrica.crearComandoConAmbos(ComandoAddRespuestaEncuesta.class, idEncuesta, baseDto);
        comandoAddRespuestaEncuesta.execute();

        return Response.ok().entity(comandoAddRespuestaEncuesta.getResultado()).build();

      }catch (ProblemaExcepcion ex){

        ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

        return  Response.status(400).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), ex.getMessage(), "error", 400)).build();

      }
      catch (Exception ex){

        String mensaje = "Ha ocurrido un error en el servidor";
        ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

        return  Response.status(500).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 500)).build();

      }
    }

  /**
   * Metodo para obtener las respuestas de una encuesta
   * Accedido mediante /encuestas/respuesta/{idEncuesta} con el metodo GET
   *
   * @param idEncuesta id de la encuesta
   *
   *
   * @return JSON success: {code, estado, respuestas}; error: {mensaje, estado,
   * code}
   *
   */
    @GET
    @Path("/respuesta/{idEncuesta}")
    public Response getRespuestaByEncuesta(@HeaderParam("authorization") String token, @PathParam("idEncuesta") long idEncuesta){

      try{

        ComandoDecode comandoDecode = Fabrica.crearComandoSeguridad(ComandoDecode.class, token);
        comandoDecode.execute();

        ComandoGetRespuestasEncuesta comandoGetRespuestasEncuesta = Fabrica.crearComandoConId(ComandoGetRespuestasEncuesta.class, idEncuesta);
        comandoGetRespuestasEncuesta.execute();

        return Response.ok().entity(comandoGetRespuestasEncuesta.getResultado()).build();

      }catch (Exception ex){

        String mensaje = "Ha ocurrido un error en el servidor";
        ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

        return Response.status(500).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 500)).build();

      }

    }
}
