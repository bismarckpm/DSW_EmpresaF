package ucab.dsw.servicio.autenticacion;

import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.excepciones.ProblemaExcepcion;
import ucab.dsw.logica.comando.autenticacion.ComandoCleanToken;
import ucab.dsw.logica.comando.autenticacion.ComandoDecode;
import ucab.dsw.logica.comando.autenticacion.ComandoLogin;
import ucab.dsw.logica.exepcionhandler.ManejadorExcepcion;
import ucab.dsw.logica.fabrica.Fabrica;
import ucab.dsw.servicio.AplicacionBase;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Clase para gestionar la autenticacion de usuario
 *
 */
@Path( "/auth" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioAutenticacion extends AplicacionBase {

  /**
   * Metodo para acceder al sistema con credenciales y generar token /auth/login con el
   * metodo POST
   *
   * @param usuarioDto DTO del usuario
   * @return JSON success: {token, id, rol, estado, code}; error: {mensaje, estado,
   * code}
   */
  @POST
  @Path("/login")
  public Response login(UsuarioDto usuarioDto){

    try {

      ComandoLogin comandoLogin = Fabrica.crearComandoConDto(ComandoLogin.class, usuarioDto);
      comandoLogin.execute();

      return Response.ok().entity(comandoLogin.getResultado()).build();

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
   * Metodo para borrar el token de un usuario autenticado /auth/logout/idUsuariologueado con el
   * metodo PUT
   *
   * @param idUsuario id del usuario autenticado
   * @return JSON success: {usuario, estado, code}; error: {mensaje, estado,
   * code}
   */
  @PUT
  @Path("/logout/{idUsuariologueado}")
  public Response cleanToken(@HeaderParam("authorization") String token, @PathParam("idUsuariologueado") long idUsuario){

    try {

      ComandoDecode comandoDecode = Fabrica.crearComandoSeguridad(ComandoDecode.class, token);
      comandoDecode.execute();

      ComandoCleanToken comandoCleanToken = Fabrica.crearComandoConId(ComandoCleanToken.class, idUsuario);
      comandoCleanToken.execute();

      return Response.ok().entity(comandoCleanToken.getResultado()).build();

    }catch (Exception ex){

      String mensaje = "Ha ocurrido un error en el servidor";
      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return  Response.status(500).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 500)).build();

    }

  }

}
