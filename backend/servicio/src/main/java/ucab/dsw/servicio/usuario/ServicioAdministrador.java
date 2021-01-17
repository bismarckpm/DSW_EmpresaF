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
    JsonObject data;
    String rol = "administrador";
    try {

      DaoUsuario daoUsuario = new DaoUsuario();
      Usuario usuario = new Usuario();
      usuario.set_nombreUsuario(usuarioDto.getNombreUsuario());
      usuario.set_estado("activo");
      usuario.set_rol(rol);

      Usuario usuarioAgregado = daoUsuario.insert(usuario);

      DirectorioActivo ldap = new DirectorioActivo();
      ldap.addEntryToLdap(usuarioDto, rol);

      data = Json.createObjectBuilder().add("usuario", usuarioAgregado.get_id())
        .add("estado", "success")
        .add("code", 200)
        .build();

    }
    catch (javax.persistence.PersistenceException ex){
      String mensaje = "El usuario ya se encuentra registrado en el sistema";
      data = Json.createObjectBuilder().add("mensaje", mensaje)
        .add("estado", "error")
        .add("code", 400)
        .build();

      System.out.println(data);
      return  Response.ok().entity(data).build();
    }
    catch ( Exception ex ){
      data = Json.createObjectBuilder().add("mensaje", ex.getMessage())
        .add("estado", "error")
        .add("code", 400)
        .build();

      System.out.println(data);
      return  Response.ok().entity(data).build();
    }

    System.out.println(data);
    return  Response.ok().entity(data).build();

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
    List<Usuario> usuarios ;
    JsonObject data;
    try {

      DaoUsuario dao = new DaoUsuario();
      usuarios = dao.findAll(Usuario.class);

      JsonArrayBuilder usuariosArray = Json.createArrayBuilder();

      for(Usuario user: usuarios) {
        if(user.get_rol().equals("administrador")) {
          JsonObject users = Json.createObjectBuilder()
            .add("id", user.get_id())
            .add("nombreUsuario", user.get_nombreUsuario())
            .add("estado", user.get_estado())
            .build();

          usuariosArray.add(users);
        }
      }
      data = Json.createObjectBuilder()
        .add("code", 200)
        .add("estado", "success")
        .add("usuarios", usuariosArray).build();


    } catch (Exception ex) {

      data = Json.createObjectBuilder()
        .add("mensaje", ex.getMessage())
        .add("estado", "error")
        .add("code", 400)
        .build();

      System.out.println(data);
      return Response.ok().entity(data).build();
    }
    System.out.println(data);
    return Response.ok().entity(data).build();
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

    DaoUsuario daoUsuario = new DaoUsuario();
    JsonObject data;

    try{
      Usuario usuario = daoUsuario.find(id, Usuario.class);

      data = Json.createObjectBuilder()
        .add("estado", "success")
        .add("code", 200)
        .add("id", usuario.get_id())
        .add("nombreUsuario", usuario.get_nombreUsuario())
        .add("estadoUsuario", usuario.get_estado())
        .build();

    }catch (Exception ex){

      data = Json.createObjectBuilder()
        .add("estado", "error")
        .add("code", 400)
        .build();
      return Response.ok().entity(data).build();

    }

    return Response.ok().entity(data).build();
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

    JsonObject data;

    try{

      if(usuarioDto.getContrasena () !=null){
        DirectorioActivo directorioActivo = new DirectorioActivo();
        directorioActivo.changePassword(usuarioDto);
      }

      data = Json.createObjectBuilder()
        .add("estado", "success")
        .add("code", 200)
        .build();

    }catch (Exception ex){

      data = Json.createObjectBuilder().add("mensaje", ex.getMessage())
        .add("estado", "error")
        .add("code", 400)
        .build();

      return Response.ok().entity(data).build();
    }

    return Response.ok().entity(data).build();
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

    DaoUsuario daoUsuario = new DaoUsuario();
    JsonObject data;

    try{

      Usuario usuario = daoUsuario.find(id, Usuario.class);
      usuario.set_estado("inactivo");

      Usuario resul = daoUsuario.update(usuario);

      data = Json.createObjectBuilder().add("usuario", resul.get_id())
        .add("estado", "success")
        .add("code", 200)
        .build();


    }catch (Exception ex){

      data = Json.createObjectBuilder().add("mensaje", ex.getMessage())
        .add("estado", "success")
        .add("code", 200)
        .build();

      return Response.ok().entity(data).build();
    }

    return Response.ok().entity(data).build();
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

    DaoUsuario daoUsuario = new DaoUsuario();
    JsonObject data;

    try{

      Usuario usuario = daoUsuario.find(id, Usuario.class);
      usuario.set_estado("activo");

      Usuario resul = daoUsuario.update(usuario);

      data = Json.createObjectBuilder().add("usuario", resul.get_id())
        .add("estado", "success")
        .add("code", 200)
        .build();


    }catch (Exception ex){

      data = Json.createObjectBuilder().add("mensaje", ex.getMessage())
        .add("estado", "success")
        .add("code", 200)
        .build();

      return Response.ok().entity(data).build();
    }

    return Response.ok().entity(data).build();
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
    List<SolicitudEstudio> solicitudesPendientes;
    JsonObject data;

    try {

      DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();
      solicitudesPendientes = daoSolicitudEstudio.findAll(SolicitudEstudio.class);

      JsonArrayBuilder solicitudesArray = Json.createArrayBuilder();

      for(SolicitudEstudio solicitudes:solicitudesPendientes) {
        if (solicitudes.get_administrador() != null && solicitudes.get_administrador().get_id() == id && solicitudes.get_estado().equals("solicitado")) {
          JsonObject solicitudesEstudios = Json.createObjectBuilder().
            add("id", solicitudes.get_id()).
            add("edadInicial", solicitudes.get_edadInicial()).
            add("edadFinal", solicitudes.get_edadfinal()).
            add("genero", solicitudes.get_genero()).
            add("estado", solicitudes.get_estado()).
            add("cliente", solicitudes.get_cliente().get_nombreUsuario()).
            add("subcategoria", solicitudes.get_subcategoria().get_nombreSubcategoria()).
            add("nivelSocioeconomico", solicitudes.get_nivelSocioeconomico().getTipo()).
            add("parroquia", solicitudes.get_parroquia().get_nombreParroquia()).build();

          solicitudesArray.add(solicitudesEstudios);
        }
      }

          data = Json.createObjectBuilder()
            .add("code", 200)
            .add("estado", "success")
            .add("id", id)
            .add("solicitudes", solicitudesArray).build();

    }catch (Exception ex){
      data = Json.createObjectBuilder()
        .add("code", 200)
        .add("estado", "success")
        .add("mensaje", ex.getMessage()).build();

      System.out.println(data);
      return Response.ok().entity(data).build();

    }

    System.out.println(data);
    return Response.ok().entity(data).build();
  }


  /**
   * Metodo para asignar un estudio a una solicitud de estudio.
   * Accedido mediante /administrador/asignarsolicitud/({idSolicitud} con el
   * metodo PUT
   *
   * @param id Id de la solicitud
   * @param solicitudEstudioDto DTO de la solicitud de estudio
   * @return JSON success: {id, estado, code}; error: {estado,
   * code}
   */
  @PUT
  @Path("/asignarsolicitud/{idSolicitud}")
  public Response asignarEstudioASolicitud(@PathParam("idSolicitud") long id, SolicitudEstudioDto solicitudEstudioDto){
    JsonObject data;

    try{
      DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();
      SolicitudEstudio solicitudEstudio = daoSolicitudEstudio.find(id, SolicitudEstudio.class);

      DaoEstudio daoEstudio = new DaoEstudio();
      Estudio estudio = daoEstudio.find(solicitudEstudioDto.getEstudio().getId(), Estudio.class);
      solicitudEstudio.set_estudio(estudio);
      solicitudEstudio.set_estado("procesado");

      int random = 2;
      Usuario analista = new Usuario(random);
      solicitudEstudio.set_analista(analista);
      SolicitudEstudio resultado = daoSolicitudEstudio.update(solicitudEstudio);

      data = Json.createObjectBuilder().
        add("id", resultado.get_id())
        .add("estado", "success")
        .add("code", 200).build();

      System.out.println(data);
      return Response.ok().entity(data).build();

    }catch (Exception ex){
      data = Json.createObjectBuilder()
        .add("estado", "error")
        .add("code", 400).build();

      System.out.println(data);
      return Response.ok().entity(data).build();
    }
  }
}
