package ucab.dsw.servicio.usuario;

import ucab.dsw.accesodatos.DaoCliente;
import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.accesodatos.DaoSolicitudEstudio;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.directorioactivo.DirectorioActivo;
import ucab.dsw.dtos.SolicitudEstudioDto;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.entidades.Cliente;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.entidades.SolicitudEstudio;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.logica.comando.solicitudestudio.ComandoGetSolicitudesPendientes;
import ucab.dsw.logica.comando.usuario.*;
import ucab.dsw.logica.exepcionhandler.ManejadorExcepcion;
import ucab.dsw.logica.fabrica.Fabrica;
import ucab.dsw.servicio.AplicacionBase;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Clase para gestionar los usuarios administradores
 *
 */
@Path( "/administrador" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioAdministrador extends AplicacionBase implements IServicioEmpleado {

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
  public Response addUser(UsuarioDto usuarioDto) {

    try {

      ComandoAddAdministrador comandoAddAdministrador = Fabrica.crearComandoConDto(ComandoAddAdministrador.class, usuarioDto);
      comandoAddAdministrador.execute();

      return  Response.ok().entity(comandoAddAdministrador.getResultado()).build();

    }
    catch (javax.persistence.PersistenceException ex){

      String mensaje = "El usuario ya se encuentra registrado en el sistema";
      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return  Response.status(400).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 400)).build();

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
  public Response getUsers() {

    try {

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
  public Response getUserById(@PathParam("usuarioAdministradorId") long id){

    try{

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
  public Response changePassword(@PathParam("usuarioAdministradorId") long id, UsuarioDto usuarioDto){

    try{

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
  public Response disableUser(@PathParam("usuarioAdministradorId") long id) {

    try{

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
  public Response enableUser(@PathParam("usuarioAdministradorId") long id) {

    try{

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
  public Response getSolicitudesPendientes(@PathParam("usuarioAdministradorId") long id){

    try {

      ComandoGetSolicitudesPendientes comandoGetSolicitudesPendientes = Fabrica.crearComandoConId(ComandoGetSolicitudesPendientes.class, id);
      comandoGetSolicitudesPendientes.execute();

      return Response.ok().entity(comandoGetSolicitudesPendientes.getResultado()).build();

    }catch (Exception ex){

      String mensaje = "Ha ocurrido un error en el servidor";
      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return  Response.status(500).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 500)).build();

    }

  }

}
