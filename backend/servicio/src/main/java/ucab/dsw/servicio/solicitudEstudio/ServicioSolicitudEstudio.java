package ucab.dsw.servicio.solicitudEstudio;

import ucab.dsw.accesodatos.*;
import ucab.dsw.dtos.SolicitudEstudioDto;
import ucab.dsw.entidades.*;
import ucab.dsw.excepciones.LimiteExcepcion;
import ucab.dsw.excepciones.ProblemaExcepcion;
import ucab.dsw.excepciones.SolicitudPendienteExcepcion;
import ucab.dsw.logica.comando.autenticacion.ComandoDecode;
import ucab.dsw.logica.comando.solicitudestudio.ComandoAddSolicitud;
import ucab.dsw.logica.comando.solicitudestudio.ComandoGetSolicitudes;
import ucab.dsw.logica.exepcionhandler.ManejadorExcepcion;
import ucab.dsw.logica.fabrica.Fabrica;
import ucab.dsw.servicio.AplicacionBase;
import ucab.dsw.servicio.muestra.ServicioMuestra;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

/**
 * Clase para gestionar las solicitudes de estudios
 *
 */
@Path( "/solicitud" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioSolicitudEstudio extends AplicacionBase {


  /**
   * Metodo para agregar una solicitud de estudio Accedido mediante /solicitud/add con el
   * metodo POST
   *
   * @param solicitudEstudioDto DTO de la solicitud de estudio
   * @return JSON success: {solicitud, estado, code}; error: {mensaje, estado,
   * code}
   */
  @POST
  @Path("/add")
  public Response addSolicitud(@HeaderParam("authorization") String token, SolicitudEstudioDto solicitudEstudioDto){

    try {

      ComandoDecode comandoDecode = Fabrica.crearComandoSeguridad(ComandoDecode.class, token);
      comandoDecode.execute();

      ComandoAddSolicitud comandoAddSolicitud = Fabrica.crearComandoConDto(ComandoAddSolicitud.class, solicitudEstudioDto);
      comandoAddSolicitud.execute();

      return Response.ok().entity(comandoAddSolicitud.getResultado()).build();

    }catch (ProblemaExcepcion ex){

      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return  Response.status(400).entity(manejadorExcepcion.getMensajeError(ex.getMensaje_soporte(), ex.getMensaje(), "error", 400)).build();

    }
    catch (Exception ex){

      String mensaje = "Ha ocurrido un error en el servidor";
      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return  Response.status(500).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 500)).build();
    }

  }


  /**
   * Metodo para obtener todas las solicitudes. Accedido mediante /solicitud/getall mediante el
   * metodo GET
   *
   *
   * @return JSON success: {solicitudes, estado, code}; error: {estado,
   * code}
   */
  @GET
  @Path("/getall")
  public Response getSolicitudes(@HeaderParam("authorization") String token) {

    try {

      ComandoDecode comandoDecode = Fabrica.crearComandoSeguridad(ComandoDecode.class, token);
      comandoDecode.execute();

      ComandoGetSolicitudes comandoGetSolicitudes = Fabrica.crear(ComandoGetSolicitudes.class);
      comandoGetSolicitudes.execute();

      return Response.ok().entity(comandoGetSolicitudes.getResultado()).build();

    } catch (Exception ex) {

      String mensaje = "Ha ocurrido un error en el servidor";
      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return Response.status(500).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 500)).build();

    }

  }

}
