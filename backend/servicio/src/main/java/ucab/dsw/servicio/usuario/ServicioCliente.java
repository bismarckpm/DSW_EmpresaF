package ucab.dsw.servicio.usuario;

import ucab.dsw.accesodatos.*;
import ucab.dsw.directorioactivo.DirectorioActivo;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.entidades.*;
import ucab.dsw.logica.comando.solicitudestudio.ComandoGetSolicitudesByCliente;
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
 * Clase para gestionar los usuarios clientes
 *
 */
@Path( "/cliente" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioCliente extends AplicacionBase implements IServicioUsuario{

  /**
   * Metodo para agregar un cliente. Accedido mediante /cliente/add con el
   * metodo POST
   *
   * @param usuarioDto DTO del usuario
   * @return JSON success: {usuario, estado, code}; error: {mensaje, estado,
   * code}
   */
  @POST
  @Path("/add")
  public Response addUser(@HeaderParam("authorization") String token, UsuarioDto usuarioDto){

    try {

      ComandoAddCliente comandoAddCliente = Fabrica.crearComandoConDto(ComandoAddCliente.class, usuarioDto);
      comandoAddCliente.execute();

      return Response.ok().entity(comandoAddCliente.getResultado()).build();

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
   * Metodo para actualizar un cliente.
   * Accedido mediante /cliente/update({usuarioClienteId} con el
   * metodo PUT
   *
   * @param usuarioDto DTO del usuario
   * @param id Id del usuario analista
   * @return JSON success: {usuario, estado, code}; error: {mensaje, estado,
   * code}
   */
  @PUT
  @Path("/update/{usuarioClienteId}")
  public Response updateUser(@PathParam("usuarioClienteId") long id, UsuarioDto usuarioDto){

    try{

      ComandoUpdateCliente comandoUpdateCliente = Fabrica.crearComandoConAmbos(ComandoUpdateCliente.class, id, usuarioDto);
      comandoUpdateCliente.execute();

      return Response.ok().entity(comandoUpdateCliente.getResultado()).build();

    }catch (Exception ex){

      String mensaje = "Ha ocurrido un error en el servidor";
      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return  Response.status(500).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 500)).build();

    }

  }

  /**
   * Metodo para obtener todos los usuarios clientes. Accedido mediante /cliente/getall con el
   * metodo GET
   *
   * @return JSON success: {usuarios, code, estado}; error: {mensaje, estado,
   * code}
   */
  @GET
  @Path("/getall")
  public Response getUsers() {

    try {

      ComandoGetClientes comandoGetClientes = Fabrica.crear(ComandoGetClientes.class);
      comandoGetClientes.execute();

      return Response.ok().entity(comandoGetClientes.getResultado()).build();

    } catch (Exception ex) {

      String mensaje = "Ha ocurrido un error en el servidor";
      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return  Response.status(500).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 500)).build();

    }

  }

  /**
   * Metodo para obtener un usuario cliente.
   * Accedido mediante /cliente/getuser/{usuarioClienteId}
   * con el metodo GET
   *
   * @param id Id del usuario cliente
   * @return JSON success: {estado, code, id, nombreUsuario, nombre}; error: {estado,
   * code}
   */
  @GET
  @Path("getuser/{usuarioClienteId}")
  public Response getUserById(@PathParam("usuarioClienteId") long id){

    try{

      ComandoGetCliente comandoGetCliente = Fabrica.crearComandoConId(ComandoGetCliente.class, id);
      comandoGetCliente.execute();

      return Response.ok().entity(comandoGetCliente.getResultado()).build();

    }catch (Exception ex){

      String mensaje = "Ha ocurrido un error en el servidor";
      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return  Response.status(500).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 500)).build();

    }

  }

  /**
   * Metodo para inactivar un usuario cliente.
   * Accedido mediante /cliente/disable/({usuarioClienteId} con el
   * metodo PUT
   *
   * @param id Id del usuario cliente
   * @return JSON success: {usuario, estado, code}; error: {mensaje, estado,
   * code}
   */
  @PUT
  @Path("/disable/{usuarioClienteId}")
  public Response disableUser(@PathParam("usuarioClienteId") long id) {

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
   * Metodo para inactivar un usuario cliente.
   * Accedido mediante /cliente/enable/({usuarioClienteId} con el
   * metodo PUT
   *
   * @param id Id del usuario cliente
   * @return JSON success: {usuario, estado, code}; error: {mensaje, estado,
   * code}
   */
  @PUT
  @Path("/enable/{usuarioClienteId}")
  public Response enableUser(@PathParam("usuarioClienteId") long id) {

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
   * Metodo para obtener las solicitudes creadas por un usuario cliente.
   * Accedido mediante /cliente/getsolicitudes/({usuarioClienteId} con el
   * metodo GET
   *
   * @param id Id del usuario cliente
   * @return JSON success: {solicitudes, estado, code}; error: {mensaje, estado,
   * code}
   */
  @GET
  @Path("/getsolicitudes/{usuarioClienteId}")
  public Response getSolicitudes(@PathParam("usuarioClienteId") long id){

    try{

      ComandoGetSolicitudesByCliente comandoGetSolicitudesByCliente = Fabrica.crearComandoConId(ComandoGetSolicitudesByCliente.class, id);
      comandoGetSolicitudesByCliente.execute();

      return Response.ok().entity(comandoGetSolicitudesByCliente.getResultado()).build();

    }catch (Exception ex){

      String mensaje = "Ha ocurrido un error en el servidor";
      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return  Response.status(500).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 500)).build();

    }

  }

}
