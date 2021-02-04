package ucab.dsw.servicio.usuario;

import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.accesodatos.DaoSolicitudEstudio;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.directorioactivo.DirectorioActivo;
import ucab.dsw.dtos.EstudioDto;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.entidades.SolicitudEstudio;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.logica.comando.estudio.ComandoActivarEstudio;
import ucab.dsw.logica.comando.estudio.ComandoFinalizarEstudio;
import ucab.dsw.logica.comando.estudio.ComandoGetEstudioByAnalista;
import ucab.dsw.logica.comando.solicitudestudio.ComandoGetSolicitudesPendientesAdmin;
import ucab.dsw.logica.comando.solicitudestudio.ComandoGetSolicitudesPendientesAnalis;
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
import java.util.Date;
import java.util.List;

/**
 * Clase para gestionar los usuarios analistas
 *
 */
@Path( "/analista" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioAnalista extends AplicacionBase implements IServicioEmpleado{

  /**
   * Metodo para agregar un analista. Accedido mediante /analista/add con el
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

      ComandoAddAnalista comandoAddAnalista = Fabrica.crearComandoConDto(ComandoAddAnalista.class, usuarioDto);
      comandoAddAnalista.execute();

      return Response.ok().entity(comandoAddAnalista.getResultado()).build();

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
   * Metodo para obtener todos los usuarios. Accedido mediante /analista/getall con el
   * metodo GET
   *
   * @return JSON success: {usuarios, code, estado}; error: {mensaje, estado,
   * code}
   */
  @GET
  @Path("/getall")
  public Response getUsers() {

    try {

      ComandoGetAnalistas comandoGetAnalistas = Fabrica.crear(ComandoGetAnalistas.class);
      comandoGetAnalistas.execute();

      return Response.ok().entity(comandoGetAnalistas.getResultado()).build();

    } catch (Exception ex) {

      String mensaje = "Ha ocurrido un error en el servidor";
      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return Response.status(500).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 500)).build();

    }

  }

  /**
   * Metodo para obtener un usuario analista.
   * Accedido mediante /analista/getuser/{usuarioAnalistaId}
   * con el metodo GET
   *
   * @param id Id del usuario analista
   * @return JSON success: {estado, code, id, nombreUsuario, estadoUsuario}; error: {estado,
   * code}
   */
  @GET
  @Path("getuser/{usuarioAnalistaId}")
  public Response getUserById(@PathParam("usuarioAnalistaId") long id){

    try{

      ComandoGetAnalista comandoGetAnalista = Fabrica.crearComandoConId(ComandoGetAnalista.class, id);
      comandoGetAnalista.execute();

      return  Response.ok().entity(comandoGetAnalista.getResultado()).build();

    }catch (Exception ex){

      String mensaje = "Ha ocurrido un error en el servidor";
      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return  Response.status(500).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 500)).build();

    }

  }

  /**
   * Metodo para actualizar (cambiar pwd) un usuario analista.
   * Accedido mediante /analista/update({usuarioAnalistaId} con el
   * metodo PUT
   *
   * @param usuarioDto DTO del usuario
   * @param id Id del usuario analista
   * @return JSON success: {estado, code}; error: {mensaje, estado,
   * code}
   */
  @PUT
  @Path("/update/{usuarioAnalistaId}")
  public Response changePassword(@PathParam("usuarioAnalistaId") long id, UsuarioDto usuarioDto){

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
   * Metodo para inactivar un usuario analista.
   * Accedido mediante /analista/disable/({usuarioAnalistaId} con el
   * metodo PUT
   *
   * @param id Id del usuario analista
   * @return JSON success: {usuario, estado, code}; error: {mensaje, estado,
   * code}
   */
  @PUT
  @Path("/disable/{usuarioAnalistaId}")
  public Response disableUser(@PathParam("usuarioAnalistaId") long id) {

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
   * Metodo para activar un usuario analista.
   * Accedido mediante /analista/enable/({usuarioAnalistaId} con el
   * metodo PUT
   *
   * @param id Id del usuario analista
   * @return JSON success: {usuario, estado, code}; error: {mensaje, estado,
   * code}
   */
  @PUT
  @Path("/enable/{usuarioAnalistaId}")
  public Response enableUser(@PathParam("usuarioAnalistaId") long id) {

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
   * Metodo para obtener un las solicitudes pendientes de un usuario analista.
   * Accedido mediante /analista/getsolicitudespendientes/{usuarioAnalistaId}
   * con el metodo GET
   *
   * @param id Id del usuario analista
   * @return JSON success: {estado, code, id, solicitudes}; error: {estado, mensaje
   * code}
   */
  @Path("/getsolicitudespendientes/{usuarioAnalistaId}")
  @GET
  public Response getSolicitudesPendientes(@PathParam("usuarioAnalistaId") long id){

    try {

      ComandoGetSolicitudesPendientesAnalis comandoGetSolicitudesPendientes = Fabrica.crearComandoConId(ComandoGetSolicitudesPendientesAnalis.class, id);
      comandoGetSolicitudesPendientes.execute();

      return Response.ok().entity(comandoGetSolicitudesPendientes.getResultado()).build();

    }catch (Exception ex){

      String mensaje = "Ha ocurrido un error en el servidor";
      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return  Response.status(500).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 500)).build();

    }

  }

  /**
   * Metodo para activar un estudio dada una solicitud de estudio.
   * Accedido mediante /analista/activarestudio/({solicitudId} con el
   * metodo PUT
   *
   * @param id Id de la solicitud
   *
   * @return JSON success: {idSolicitud, estadoSolicitud, estado, code}; error: {estado,
   * code}
   */
  @Path("/activarestudio/{solicitudId}")
  @PUT
  public Response activarEstudio(@PathParam("solicitudId") long id){

    try {

      ComandoActivarEstudio comandoActivarEstudio = Fabrica.crearComandoConId(ComandoActivarEstudio.class, id);
      comandoActivarEstudio.execute();

      return Response.ok().entity(comandoActivarEstudio.getResultado()).build();

    }
    catch (Exception ex){

      String mensaje = "Ha ocurrido un error en el servidor";
      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return  Response.status(500).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 500)).build();

    }

  }

  /**
   * Metodo para obtener los estudios asignados a un usuario analista.
   * Accedido mediante /analista/obtenerestudios/{analistaId}
   * con el metodo GET
   *
   * @param id Id del usuario analista
   * @return JSON success: {estado, code, id, estudios}; error: {estado, mensaje
   * code}
   */
  @Path("/obtenerestudios/{analistaId}")
  @GET
  public Response obtenerEstudiosByAnalista(@PathParam("analistaId") long id){

    try {

      ComandoGetEstudioByAnalista comandoGetEstudioByAnalista = Fabrica.crearComandoConId(ComandoGetEstudioByAnalista.class, id);
      comandoGetEstudioByAnalista.execute();

      return Response.ok().entity(comandoGetEstudioByAnalista.getResultado()).build();

    }catch (NullPointerException ex){

      String mensaje = "No hay estudios";

      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return  Response.status(400).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 400)).build();

    }catch (Exception ex){

      String mensaje = "Ha ocurrido un error en el servidor";
      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return  Response.status(500).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 500)).build();

    }

  }

  /**
   * Metodo para finalizar un estudio asignado a un usuario analista.
   * Accedido mediante /analista/finalizar/{estudioId}
   * con el metodo PUT
   *
   * @param estudioId Id del estudio
   * @return JSON success: {estudioCulminado, estado, code}; error: {estado, mensaje
   * code}
   */
  @Path("/finalizar/{estudioId}")
  @PUT
  public Response finalizarEstudio(@PathParam("estudioId") long estudioId, EstudioDto estudioDto){

    try{

      ComandoFinalizarEstudio comandoFinalizarEstudio = Fabrica.crearComandoConAmbos(ComandoFinalizarEstudio.class, estudioId, estudioDto);
      comandoFinalizarEstudio.execute();

      return Response.ok().entity(comandoFinalizarEstudio.getResultado()).build();

    }catch (Exception ex){

      String mensaje = "Ha ocurrido un error en el servidor";
      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return  Response.status(500).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 500)).build();

    }

  }

}
