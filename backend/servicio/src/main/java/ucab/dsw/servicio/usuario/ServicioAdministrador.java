package ucab.dsw.servicio.usuario;

import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.excepciones.ProblemaExcepcion;
import ucab.dsw.logica.comando.autenticacion.ComandoDecode;
import ucab.dsw.logica.comando.solicitudestudio.ComandoGetSolicitudesPendientesAdmin;
import ucab.dsw.logica.comando.usuario.*;
import ucab.dsw.logica.exepcionhandler.ManejadorExcepcion;
import ucab.dsw.logica.fabrica.Fabrica;
import ucab.dsw.servicio.AplicacionBase;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Clase para gestionar los usuarios administradores
 *
 */
@Path( "/administrador" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioAdministrador extends AplicacionBase {

  /**
   * Metodo para agregar un administrador. Accedido mediante /administrador/add con el
   * metodo POST
   *
   * @param usuarioDto DTO del usuario
   * @return JSON success: {usuario, estado, code}; error: {mensaje, estado,
   * code}
   */
  @POST
  @Path("/add")
  public Response addUser(@HeaderParam("authorization") String token, UsuarioDto usuarioDto) {

    try {

      ComandoDecode comandoDecode = Fabrica.crearComandoSeguridad(ComandoDecode.class, token);
      comandoDecode.execute();

      ComandoAddAdministrador comandoAddAdministrador = Fabrica.crearComandoConDto(ComandoAddAdministrador.class, usuarioDto);
      comandoAddAdministrador.execute();

      return  Response.ok().entity(comandoAddAdministrador.getResultado()).build();

    }
    catch (ProblemaExcepcion ex){

      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return  Response.status(400).entity(manejadorExcepcion.getMensajeError(ex.getMensaje_soporte(), ex.getMensaje(), "error", 400)).build();

    }
    catch ( Exception ex ){

      String mensaje = "Ha ocurrido un error en el servidor";
      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return  Response.status(500).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 500)).build();

    }

  }

  /**
   * Metodo para obtener todos los usuarios. Accedido mediante /administrador/getall con el
   * metodo GET
   *
   * @return JSON success: {usuarios, code, estado}; error: {mensaje, estado,
   * code}
   */
  @GET
  @Path("/getall")
  public Response getUsers(@HeaderParam("authorization") String token) {

    try {

      ComandoDecode comandoDecode = Fabrica.crearComandoSeguridad(ComandoDecode.class, token);
      comandoDecode.execute();

      ComandoGetAdministradores comandoGetAdministradores = Fabrica.crear(ComandoGetAdministradores.class);
      comandoGetAdministradores.execute();

      return Response.ok().entity(comandoGetAdministradores.getResultado()).build();

    } catch (Exception ex) {

      String mensaje = "Ha ocurrido un error en el servidor";
      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return Response.status(500).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 500)).build();

    }

  }

  /**
   * Metodo para obtener un usuario administrador.
   * Accedido mediante /administrador/getuser/{usuarioAdministradorId}
   * con el metodo GET
   *
   * @param id Id del usuario administrador
   * @return JSON success: {estado, code, id, nombreUsuario, estadoUsuario}; error: {estado,
   * code}
   */
  @GET
  @Path("getuser/{usuarioAdministradorId}")
  public Response getUserById(@HeaderParam("authorization") String token, @PathParam("usuarioAdministradorId") long id){

    try{

      ComandoDecode comandoDecode = Fabrica.crearComandoSeguridad(ComandoDecode.class, token);
      comandoDecode.execute();

      ComandoGetAdministrador comandoGetAdministrador = Fabrica.crearComandoConId(ComandoGetAdministrador.class, id);
      comandoGetAdministrador.execute();

      return  Response.ok().entity(comandoGetAdministrador.getResultado()).build();

    }catch (Exception ex){

      String mensaje = "Ha ocurrido un error en el servidor";
      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return  Response.status(500).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 500)).build();

    }

  }

  /**
   * Metodo para actualizar (cambiar pwd) un usuario administrador.
   * Accedido mediante /administrador/update({usuarioAdministradorId} con el
   * metodo PUT
   *
   * @param usuarioDto DTO del usuario
   * @param id Id del usuario administrador
   * @return JSON success: {estado, code}; error: {mensaje, estado,
   * code}
   */
  @PUT
  @Path("/update/{usuarioAdministradorId}")
  public Response changePassword(@HeaderParam("authorization") String token, @PathParam("usuarioAdministradorId") long id, UsuarioDto usuarioDto){

    try{

      ComandoDecode comandoDecode = Fabrica.crearComandoSeguridad(ComandoDecode.class, token);
      comandoDecode.execute();

      ComandoUpdatePassword comandoUpdatePassword = Fabrica.crearComandoConAmbos(ComandoUpdatePassword.class, id, usuarioDto);
      comandoUpdatePassword.execute();

      return Response.ok().entity(comandoUpdatePassword.getResultado()).build();

    }catch (Exception ex){

      String mensaje = "Ha ocurrido un error en el servidor";
      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return  Response.status(500).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 500)).build();

    }

  }

  /**
   * Metodo para inactivar un usuario administrador.
   * Accedido mediante /administrador/disable/({usuarioAdministradorId} con el
   * metodo PUT
   *
   * @param id Id del usuario administrador
   * @return JSON success: {usuario, estado, code}; error: {mensaje, estado,
   * code}
   */
  @PUT
  @Path("/disable/{usuarioAdministradorId}")
  public Response disableUser(@HeaderParam("authorization") String token, @PathParam("usuarioAdministradorId") long id) {

    try{

      ComandoDecode comandoDecode = Fabrica.crearComandoSeguridad(ComandoDecode.class, token);
      comandoDecode.execute();

      ComandoDesactivarUsuario comandoDesactivarUsuario = Fabrica.crearComandoConId(ComandoDesactivarUsuario.class, id);
      comandoDesactivarUsuario.execute();

      return Response.ok().entity(comandoDesactivarUsuario.getResultado()).build();

    }catch (Exception ex){

      String mensaje = "Ha ocurrido un error en el servidor";
      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return  Response.status(500).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 500)).build();

    }

  }

  /**
   * Metodo para activar un usuario administrador.
   * Accedido mediante /administrador/enable/({usuarioAdministradoId} con el
   * metodo PUT
   *
   * @param id Id del usuario administrador
   * @return JSON success: {usuario, estado, code}; error: {mensaje, estado,
   * code}
   */
  @PUT
  @Path("/enable/{usuarioAdministradorId}")
  public Response enableUser(@HeaderParam("authorization") String token, @PathParam("usuarioAdministradorId") long id) {

    try{

      ComandoDecode comandoDecode = Fabrica.crearComandoSeguridad(ComandoDecode.class, token);
      comandoDecode.execute();

      ComandoActivarUsuario comandoActivarUsuario = Fabrica.crearComandoConId(ComandoActivarUsuario.class, id);
      comandoActivarUsuario.execute();

      return Response.ok().entity(comandoActivarUsuario.getResultado()).build();

    }catch (Exception ex){

      String mensaje = "Ha ocurrido un error en el servidor";
      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return  Response.status(500).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 500)).build();

    }

  }

  /**
   * Metodo para obtener un las solicitudes pendientes de un usuario administrador.
   * Accedido mediante /administrador/getsolicitudespendientes/{usuarioAdministradorId}
   * con el metodo GET
   *
   * @param id Id del usuario administrador
   * @return JSON success: {estado, code, id, solicitudes}; error: {estado, mensaje
   * code}
   */
  @GET
  @Path("/getsolicitudespendientes/{usuarioAdministradorId}")
  public Response getSolicitudesPendientes(@HeaderParam("authorization") String token, @PathParam("usuarioAdministradorId") long id){

    try {

      ComandoDecode comandoDecode = Fabrica.crearComandoSeguridad(ComandoDecode.class, token);
      comandoDecode.execute();

      ComandoGetSolicitudesPendientesAdmin comandoGetSolicitudesPendientes = Fabrica.crearComandoConId(ComandoGetSolicitudesPendientesAdmin.class, id);
      comandoGetSolicitudesPendientes.execute();

      return Response.ok().entity(comandoGetSolicitudesPendientes.getResultado()).build();

    }catch (Exception ex){

      String mensaje = "Ha ocurrido un error en el servidor";
      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return  Response.status(500).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 500)).build();

    }

  }

}
