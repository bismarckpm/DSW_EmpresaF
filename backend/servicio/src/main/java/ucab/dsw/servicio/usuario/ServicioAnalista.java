package ucab.dsw.servicio.usuario;

import ucab.dsw.accesodatos.DaoSolicitudEstudio;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.directorioactivo.DirectorioActivo;
import ucab.dsw.dtos.UsuarioDto;
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
  public Response addUser(UsuarioDto usuarioDto) {
    JsonObject data;
    String rol = "analista";
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
   * Metodo para obtener todos los usuarios. Accedido mediante /analista/getall con el
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
        if(user.get_rol().equals("analista")) {
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
    List<SolicitudEstudio> solicitudesPendientes;
    JsonObject data;

    try {

      DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();
      solicitudesPendientes = daoSolicitudEstudio.findAll(SolicitudEstudio.class);

      JsonArrayBuilder solicitudesArray = Json.createArrayBuilder();

      for(SolicitudEstudio solicitudes:solicitudesPendientes) {
        if (solicitudes.get_analista() != null && solicitudes.get_analista().get_id() == id && solicitudes.get_estado().equals("solicitado")) {
          JsonObject solicitudesEstudios = Json.createObjectBuilder().
            add("id", solicitudes.get_id()).
            add("edadInicial", solicitudes.get_edadfinal()).
            add("edadFinal", solicitudes.get_edadfinal()).
            add("genero", solicitudes.get_genero()).
            add("estado", solicitudes.get_estado()).
            add("cliente", solicitudes.get_cliente().get_nombreUsuario()).
            add("marca", solicitudes.get_marca().get_nombreMarca()).
            add("tipoMarca", solicitudes.get_marca().get_tipoMarca()).
            add("capacidadMarca", solicitudes.get_marca().get_capacidad()).
            add("unidadMarca", solicitudes.get_marca().get_unidad()).
            add("unidadSubcategoria", solicitudes.get_marca().get_subcategoria().get_nombreSubcategoria()).
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

    }catch (NullPointerException ex){
      data = Json.createObjectBuilder()
        .add("code", 200)
        .add("estado", "success")
        .add("mensaje", "No hay solicitudes pendientes").build();

      System.out.println(data);
      return Response.ok().entity(data).build();

    } catch (Exception ex){
      data = Json.createObjectBuilder()
        .add("code", 400)
        .add("estado", "error").build();

      return Response.ok().entity(data).build();
    }

    System.out.println(data);
    return Response.ok().entity(data).build();
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

    JsonObject data;

    try {
      SolicitudEstudio solicitud;
      DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();
      solicitud = daoSolicitudEstudio.find(id, SolicitudEstudio.class);

     List<SolicitudEstudio> solicitudesEstudio = daoSolicitudEstudio.getSolicitudesByCaracteristicas(solicitud);

     for(SolicitudEstudio solicitudes:solicitudesEstudio){
        if(solicitudes.get_analista() == solicitud.get_analista() &&
          (solicitudes.get_estado().equals("procesado") || solicitudes.get_estado().equals("ejecutando") || solicitudes.get_estado().equals("culminado"))){
            Estudio estudio = new Estudio(solicitudes.get_estudio().get_id());
            solicitud.set_estudio(estudio);
            solicitud.set_estado("procesado");
            break;
        }
      }

      SolicitudEstudio resultado = daoSolicitudEstudio.update(solicitud);

      System.out.println(solicitud.get_id());

      data = Json.createObjectBuilder()
        .add("code", 200)
        .add("estado", "success")
        .add("idSolicitud", id)
        .add("estadoSolicitud", resultado.get_estado()).build();

    }
    catch (Exception ex){
      data = Json.createObjectBuilder()
        .add("code", 400)
        .add("estado", "error").build();

      return Response.ok().entity(data).build();
    }

    System.out.println(data);
    return Response.ok().entity(data).build();
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

    DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();
    JsonObject data;
    try {
      List<SolicitudEstudio> solicitudEstudios = daoSolicitudEstudio.findAll(SolicitudEstudio.class);

      JsonArrayBuilder estudiosArray = Json.createArrayBuilder();

      for (SolicitudEstudio solicitudes : solicitudEstudios) {
       if(solicitudes.get_estudio() != null){
          if(solicitudes.get_analista().get_id() == id) {
            JsonObject estudio = Json.createObjectBuilder()
              .add("id", solicitudes.get_estudio().get_estado())
              .add("nombreEstudio", solicitudes.get_estudio().get_nombreEstudio())
              .add("encuestaId", solicitudes.get_estudio().get_encuesta().get_id()).build();

            estudiosArray.add(estudio);
          }
        }
      }

      data = Json.createObjectBuilder()
        .add("estado", "success")
        .add("code", 200)
        .add("estudios",estudiosArray)
        .build();

      return Response.ok().entity(data).build();

    }catch (NullPointerException ex){
      data = Json.createObjectBuilder()
        .add("estado", "success")
        .add("code", 200)
        .add("mensaje","No hay estudios")
        .build();

      return Response.ok().entity(data).build();

    }catch (Exception ex){

      data = Json.createObjectBuilder()
        .add("estado", "error")
        .add("code", 400).build();

      return Response.ok().entity(data).build();
    }

  }

}
