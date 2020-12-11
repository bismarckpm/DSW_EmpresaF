package ucab.dsw.servicio.usuario;

import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.directorioactivo.DirectorioActivo;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.entidades.Cliente;
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
public class ServicioCliente extends AplicacionBase implements IServicioUsuario {

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

}
