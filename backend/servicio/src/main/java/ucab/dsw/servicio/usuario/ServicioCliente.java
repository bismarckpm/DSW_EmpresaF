package ucab.dsw.servicio.usuario;

import ucab.dsw.accesodatos.DaoCliente;
import ucab.dsw.accesodatos.DaoEncuestado;
import ucab.dsw.accesodatos.DaoSolicitudEstudio;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.directorioactivo.DirectorioActivo;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.entidades.Cliente;
import ucab.dsw.entidades.Encuestado;
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

@Path( "/cliente" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioCliente extends AplicacionBase implements IServicioUsuario{

  @POST
  @Path("/add")
  public Response addUser(UsuarioDto usuarioDto){
    JsonObject data;
    String rol = "cliente";
    try {

      Cliente cliente = new Cliente();
      cliente.setNombre(usuarioDto.getClienteDto().getNombre());

      DaoUsuario daoUsuario = new DaoUsuario();
      Usuario usuario = new Usuario();
      usuario.set_nombreUsuario(usuarioDto.getNombreUsuario());
      usuario.set_estado("Activo");
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

  @PUT
  @Path("/update/{usuarioClienteId}")
  public Response updateUser(@PathParam("usuarioClienteId") long id, UsuarioDto usuarioDto){

    JsonObject data;
    DaoUsuario daoUsuario = new DaoUsuario();

    try{

      Usuario usuario = daoUsuario.find(id, Usuario.class);

      usuario.get_cliente().setNombre(usuarioDto.getClienteDto().getNombre());

      Usuario resul = daoUsuario.update(usuario);

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

  @GET
  @Path("/getsolicitudes/{usuarioClienteId}")
  public Response getSolicitudes(@PathParam("usuarioClienteId") long id){

    DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();
    JsonObject data;

    try{
      List<SolicitudEstudio> solicitudes = daoSolicitudEstudio.findAll(SolicitudEstudio.class);
      JsonArrayBuilder solicitudesArray = Json.createArrayBuilder();

      for(SolicitudEstudio solicitud:solicitudes){
        if(solicitud.get_edadfinal() == null) {
          solicitud.set_edadfinal(0);
        }
        if(solicitud.get_cliente().get_id() == id){
          JsonObject solis = Json.createObjectBuilder().
            add("id", solicitud.get_id()).
            add("edadInicial", solicitud.get_edadInicial()).
            add("edadFinal", solicitud.get_edadfinal()).
            add("genero", solicitud.get_genero()).
            add("estado", solicitud.get_estado()).
            add("cliente", solicitud.get_cliente().get_nombreUsuario()).
            add("marca", solicitud.get_marca().get_nombreMarca()).
            add("tipoMarca", solicitud.get_marca().get_tipoMarca()).
            add("capacidadMarca", solicitud.get_marca().get_capacidad()).
            add("unidadMarca", solicitud.get_marca().get_unidad()).
            add("unidadSubcategoria", solicitud.get_marca().get_subcategoria().get_nombreSubcategoria()).
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
        .add("code", 200)
        .add("estado", "success").build();

        return Response.ok().entity(data).build();
    }

    return Response.ok().entity(data).build();
  }

}
