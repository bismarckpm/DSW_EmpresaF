package ucab.dsw.servicio.usuario;

import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.excepciones.ProblemaExcepcion;
import ucab.dsw.logica.comando.autenticacion.ComandoDecode;
import ucab.dsw.logica.comando.estudio.ComandoGetEstudiosRelizablesByEncuestado;
import ucab.dsw.logica.comando.usuario.*;
import ucab.dsw.logica.exepcionhandler.ManejadorExcepcion;
import ucab.dsw.logica.fabrica.Fabrica;
import ucab.dsw.servicio.AplicacionBase;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * Clase para gestionar los usuarios encuestados
 *
 */
@Path( "/encuestado" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioEncuestado extends AplicacionBase{

  /**
   * Metodo para agregar un cliente. Accedido mediante /encuestado/add con el
   * metodo POST
   *
   * @param usuarioDto DTO del usuario
   * @return JSON success: {usuario, estado, code}; error: {mensaje, estado,
   * code}
   */
  @POST
  @Path("/add")
  public Response addUser(UsuarioDto usuarioDto) {

    try{

      ComandoAddEncuestado comandoAddEncuestado = Fabrica.crearComandoConDto(ComandoAddEncuestado.class, usuarioDto);
      comandoAddEncuestado.execute();

      return Response.ok().entity(comandoAddEncuestado.getResultado()).build();

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
   * Metodo para obtener todos los usuarios. Accedido mediante /encuestado/getall con el
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

      ComandoGetEncuestados comandoGetEncuestados = Fabrica.crear(ComandoGetEncuestados.class);
      comandoGetEncuestados.execute();

      return Response.ok().entity(comandoGetEncuestados.getResultado()).build();

    } catch (Exception ex) {

      String mensaje = "Ha ocurrido un error en el servidor";
      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return  Response.status(500).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 500)).build();

    }

  }

  /**
   * Metodo para obtener un usuario encuestado.
   * Accedido mediante /encuestado/getuser/{usuarioEncuestadoId}
   * con el metodo GET
   *
   * @param id Id del usuario encuestado
   * @return JSON success: {estado, code, data}; error: {estado,
   * code}
   */
  @GET
  @Path("getuser/{usuarioEncuestadoId}")
  public Response getUserById(@HeaderParam("authorization") String token, @PathParam("usuarioEncuestadoId") long id){

    try{

      ComandoDecode comandoDecode = Fabrica.crearComandoSeguridad(ComandoDecode.class, token);
      comandoDecode.execute();

      ComandoGetEncuestado comandoGetEncuestado = Fabrica.crearComandoConId(ComandoGetEncuestado.class, id);
      comandoGetEncuestado.execute();

      return Response.ok().entity(comandoGetEncuestado.getResultado()).build();

    }catch (Exception ex){

      String mensaje = "Ha ocurrido un error en el servidor";
      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return  Response.status(500).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 500)).build();

    }

  }

  /**
   * Metodo para actualizar un usuario encuestado.
   * Accedido mediante /encuestado/update({usuarioEncuestadoId} con el
   * metodo PUT
   *
   * @param usuarioDto DTO del usuario
   * @param id Id del usuario encuestado
   * @return JSON success: {usuario, estado, code}; error: {mensaje, estado,
   * code}
   */
  @PUT
  @Path("/update/{usuarioEncuestadoid}")
  public Response updateUser(@HeaderParam("authorization") String token, @PathParam("usuarioEncuestadoid") long id, UsuarioDto usuarioDto) {

    try {

      ComandoDecode comandoDecode = Fabrica.crearComandoSeguridad(ComandoDecode.class, token);
      comandoDecode.execute();

      ComandoUpdateEncuestado comandoUpdateEncuestado = Fabrica.crearComandoConAmbos(ComandoUpdateEncuestado.class, id, usuarioDto);
      comandoUpdateEncuestado.execute();

      return Response.ok().entity(comandoUpdateEncuestado.getResultado()).build();

    }catch (Exception ex){

      String mensaje = "Ha ocurrido un error en el servidor";
      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return  Response.status(500).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 500)).build();

    }

  }

  /**
   * Metodo para obtener estudios realizables para un usuario encuestado.
   * Accedido mediante /encuestado/getestudios/{usuarioEncuestadoId}
   * con el metodo GET
   *
   * @param usuarioEncuestadoId Id del usuario encuestado
   * @return JSON success: {estado, code, estudios}; error: {estado,
   * code}
   */
  @GET
  @Path("/getestudios/{usuarioEncuestadoId}")
  public Response getEstudiosRealizables(@HeaderParam("authorization") String token, @PathParam("usuarioEncuestadoId") long usuarioEncuestadoId){

    try {

      ComandoDecode comandoDecode = Fabrica.crearComandoSeguridad(ComandoDecode.class, token);
      comandoDecode.execute();

      ComandoGetEstudiosRelizablesByEncuestado comandoGetEstudiosRelizablesByEncuestado = Fabrica.crearComandoConId(ComandoGetEstudiosRelizablesByEncuestado.class, usuarioEncuestadoId);
      comandoGetEstudiosRelizablesByEncuestado.execute();

      return Response.ok().entity(comandoGetEstudiosRelizablesByEncuestado.getResultado()).build();

    }
    catch (Exception ex){

      String mensaje = "Ha ocurrido un error en el servidor";
      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return  Response.status(500).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 500)).build();

    }

  }

  /**
   * Metodo para inactivar un usuario encuestado.
   * Accedido mediante /encuestado/disable/({usuarioEncuestadoId} con el
   * metodo PUT
   *
   * @param id Id del usuario encuestado
   * @return JSON success: {usuario, estado, code}; error: {mensaje, estado,
   * code}
   */
  @PUT
  @Path("/disable/{usuarioEncuestadoId}")
  public Response disableUser(@HeaderParam("authorization") String token, @PathParam("usuarioEncuestadoId") long id) {

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
   * Metodo para inactivar un usuario encuestado.
   * Accedido mediante /encuestado/enable/({usuarioEncuestadoId} con el
   * metodo PUT
   *
   * @param id Id del usuario encuestado
   * @return JSON success: {usuario, estado, code}; error: {mensaje, estado,
   * code}
   */
  @PUT
  @Path("/enable/{usuarioEncuestadoId}")
  public Response enableUser(@HeaderParam("authorization") String token, @PathParam("usuarioEncuestadoId") long id) {

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

}
