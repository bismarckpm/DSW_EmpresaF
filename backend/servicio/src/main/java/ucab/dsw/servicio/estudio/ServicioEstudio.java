package ucab.dsw.servicio.estudio;

import ucab.dsw.dtos.EstudioDto;
import ucab.dsw.excepciones.ProblemaExcepcion;
import ucab.dsw.logica.comando.autenticacion.ComandoDecode;
import ucab.dsw.logica.comando.estudio.ComandoAddEstudio;
import ucab.dsw.logica.comando.estudio.ComandoGetEstudio;
import ucab.dsw.logica.comando.estudio.ComandoGetEstudios;
import ucab.dsw.logica.exepcionhandler.ManejadorExcepcion;
import ucab.dsw.logica.fabrica.Fabrica;
import ucab.dsw.servicio.AplicacionBase;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * Clase para gestionar los estudios
 *
 */
@Path( "/estudio" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioEstudio extends AplicacionBase {

  /**
   * Metodo para agregar un estudio y asignarlo a una solicitud Accedido mediante estudio/add/{solicitudEstudioId} con el
   * metodo POST
   *
   * @param estudioDto DTO del estudio
   * @return JSON success: {estudioId, estado, code}; error: {mensaje, estado,
   * code}
   */
  @POST
  @Path("/add/{solicitudEstudioId}")
  public Response addEstudio (@HeaderParam("authorization") String token, @PathParam("solicitudEstudioId") long solicitudId, EstudioDto estudioDto){

    try {

      ComandoDecode comandoDecode = Fabrica.crearComandoSeguridad(ComandoDecode.class, token);
      comandoDecode.execute();

      ComandoAddEstudio comandoAddEstudio = Fabrica.crearComandoConAmbos(ComandoAddEstudio.class, solicitudId, estudioDto);
      comandoAddEstudio.execute();

      return Response.ok().entity(comandoAddEstudio.getResultado()).build();

    }
    catch (ProblemaExcepcion ex){

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
   * Metodo para obtener un estudio Accedido mediante estudio/getestudio/{estudioId} con el
   * metodo GET
   *
   * @param id Id del estudio
   * @return JSON success: {id, nombreEstudio, encuestaId, estado, code}; error: {estado,
   * code}
   */
  @GET
  @Path("/getestudio/{estudioId}")
  public Response getEstudioById(@HeaderParam("authorization") String token, @PathParam("estudioId") long id){

    try{

      ComandoDecode comandoDecode = Fabrica.crearComandoSeguridad(ComandoDecode.class, token);
      comandoDecode.execute();

      ComandoGetEstudio comandoGetEstudio = Fabrica.crearComandoConId(ComandoGetEstudio.class, id);
      comandoGetEstudio.execute();

      return Response.ok().entity(comandoGetEstudio.getResultado()).build();

    }catch (Exception ex){

      String mensaje = "Ha ocurrido un error en el servidor";
      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return  Response.status(500).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 500)).build();

    }

  }


  /**
   * Metodo para obtener todos los estudios Accedido mediante estudio/getall con el
   * metodo GET
   *
   *
   * @return JSON success: {estudios, estado, code}; error: {estado,
   * code}
   */
  @GET
  @Path("/getall")
  public Response getEstudios(@HeaderParam("authorization") String token){

    try {

      ComandoDecode comandoDecode = Fabrica.crearComandoSeguridad(ComandoDecode.class, token);
      comandoDecode.execute();

      ComandoGetEstudios comandoGetEstudios = Fabrica.crear(ComandoGetEstudios.class);
      comandoGetEstudios.execute();

      return Response.ok().entity(comandoGetEstudios.getResultado()).build();

    } catch (Exception ex) {

      String mensaje = "Ha ocurrido un error en el servidor";
      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return Response.status(500).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 500)).build();

    }

  }

}
