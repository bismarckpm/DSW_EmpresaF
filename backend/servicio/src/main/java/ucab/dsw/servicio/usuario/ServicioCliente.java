package ucab.dsw.servicio.usuario;

import ucab.dsw.accesodatos.*;
import ucab.dsw.directorioactivo.DirectorioActivo;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.entidades.*;
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
  public Response addUser(UsuarioDto usuarioDto){
    JsonObject data;
    String rol = "cliente";
    try {

      Cliente cliente = new Cliente();
      cliente.setNombre(usuarioDto.getClienteDto().getNombre());
      cliente.set_estado("activo");

      DaoUsuario daoUsuario = new DaoUsuario();
      Usuario usuario = new Usuario();
      usuario.set_nombreUsuario(usuarioDto.getNombreUsuario());
      usuario.set_estado("activo");
      usuario.set_rol(rol);
      usuario.set_cliente(cliente);

      Usuario usuarioAgregado = daoUsuario.insert(usuario);
      usuarioDto.setId(usuarioAgregado.get_id());

      DirectorioActivo ldap = new DirectorioActivo();
      ldap.addEntryToLdap(usuarioDto, rol);

      data = Json.createObjectBuilder().add("usuario", usuarioDto.getId())
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

    JsonObject data;
    DaoUsuario daoUsuario = new DaoUsuario();

    try{

      Usuario usuario = daoUsuario.find(id, Usuario.class);

      usuario.get_cliente().setNombre(usuarioDto.getClienteDto().getNombre());

      Usuario resul = daoUsuario.update(usuario);

      if(usuarioDto.getContrasena()!=null){
        DirectorioActivo directorioActivo = new DirectorioActivo();
        directorioActivo.changePassword(usuarioDto);
      }

      data = Json.createObjectBuilder().add("usuario", resul.get_id())
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
   * Metodo para obtener todos los usuarios clientes. Accedido mediante /cliente/getall con el
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
        if(user.get_cliente() != null) {
          JsonObject users = Json.createObjectBuilder()
            .add("id", user.get_id())
            .add("nombreUsuario", user.get_nombreUsuario())
            .add("nombre", user.get_cliente().getNombre())
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

    DaoUsuario daoUsuario = new DaoUsuario();
    JsonObject data;

    try{
      Usuario usuario = daoUsuario.find(id, Usuario.class);

        data = Json.createObjectBuilder()
        .add("estado", "success")
        .add("code", 200)
        .add("id", usuario.get_id())
        .add("nombreUsuario", usuario.get_nombreUsuario())
        .add("nombre", usuario.get_cliente().getNombre())
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

    DaoUsuario daoUsuario = new DaoUsuario();
    JsonObject data;

    try{
      Usuario usuario = daoUsuario.find(id, Usuario.class);

      DaoCliente daoCliente = new DaoCliente();
      Cliente cliente = daoCliente.find(usuario.get_cliente().get_id(), Cliente.class);

      cliente.set_estado("inactivo");
      usuario.set_estado("inactivo");

      Usuario resul = daoUsuario.update(usuario);
      daoCliente.update(cliente);

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

    DaoUsuario daoUsuario = new DaoUsuario();
    JsonObject data;

    try{
      Usuario usuario = daoUsuario.find(id, Usuario.class);

      DaoCliente daoCliente = new DaoCliente();
      Cliente cliente = daoCliente.find(usuario.get_cliente().get_id(), Cliente.class);

      cliente.set_estado("activo");
      usuario.set_estado("activo");

      Usuario resul = daoUsuario.update(usuario);
      daoCliente.update(cliente);

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

    DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();
    JsonObject data;
    String resultadoEstudio;
    Encuesta encuesta = null;
    Estudio estudio = null;
    long estudioId = 0;
    long encuestaId = 0;

    try{
      List<SolicitudEstudio> solicitudes = daoSolicitudEstudio.findAll(SolicitudEstudio.class);
      JsonArrayBuilder solicitudesArray = Json.createArrayBuilder();

      for(SolicitudEstudio solicitud:solicitudes){
        if(solicitud.get_edadfinal() == null) {
          solicitud.set_edadfinal(0);
        }
        if(solicitud.get_cliente().get_id() == id){

          if(solicitud.get_estudio()!=null && solicitud.get_estudio().get_resultado()!=null) {
            DaoEstudio daoEstudio = new DaoEstudio();
            estudio = daoEstudio.find(solicitud.get_estudio().get_id(), Estudio.class);

            DaoEncuesta daoEncuesta = new DaoEncuesta();
            encuesta = daoEncuesta.find(estudio.get_encuesta().get_id(), Encuesta.class);

            estudioId = estudio.get_id();
            encuestaId = encuesta.get_id();
            resultadoEstudio = estudio.get_resultado();

          }else {
            estudioId = 0;
            encuestaId = 0;
            resultadoEstudio = "Sin resultados hasta el momento";
          }
          JsonObject solis = Json.createObjectBuilder().
            add("id", solicitud.get_id()).
            add("estudioId", estudioId).
            add("resultadoEstudio", resultadoEstudio).
            add("encuestaId", encuestaId).
            add("edadInicial", solicitud.get_edadInicial()).
            add("edadFinal", solicitud.get_edadfinal()).
            add("genero", solicitud.get_genero()).
            add("estado", solicitud.get_estado()).
            add("cliente", solicitud.get_cliente().get_nombreUsuario()).
            add("subcategoria", solicitud.get_subcategoria().get_nombreSubcategoria()).
            add("nivelSocioeconomico", solicitud.get_nivelSocioeconomico().getTipo()).
            add("parroquia", solicitud.get_parroquia().get_nombreParroquia()).build();

          solicitudesArray.add(solis);
        }
      }

      data = Json.createObjectBuilder()
        .add("code", 200)
        .add("estado", "success")
        .add("solicitudes", solicitudesArray).build();

    }catch (Exception ex){

      data = Json.createObjectBuilder()
        .add("code", 400)
        .add("estado", "error")
        .add("mensaje", ex.getMessage()).build();

      return Response.status(400).entity(data).build();
    }

    return Response.ok().entity(data).build();
  }

}
