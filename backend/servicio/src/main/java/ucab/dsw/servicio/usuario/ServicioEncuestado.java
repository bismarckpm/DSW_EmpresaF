package ucab.dsw.servicio.usuario;

import ucab.dsw.accesodatos.DaoTelefono;
import ucab.dsw.accesodatos.DaoUsuario;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;


@Path( "/encuestado" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioEncuestado extends AplicacionBase implements IServicioUsuario{


  @POST
  @Path("/add")
  public Response addUser(UsuarioDto usuarioDto) {
    JsonObject data;
    String rol = "encuestado";

    try{

    Encuestado encuestado = new Encuestado();
    encuestado.set_numeroIdentificacion(usuarioDto.getEncuestadoDto().getNumeroIdentificacion());
    encuestado.set_primerNombre(usuarioDto.getEncuestadoDto().getPrimerNombre());
    encuestado.set_segundoNombre(usuarioDto.getEncuestadoDto().getSegundoNombre());
    encuestado.set_primerApellido(usuarioDto.getEncuestadoDto().getPrimerApellido());
    encuestado.set_segundoApellido(usuarioDto.getEncuestadoDto().getSegundoApellido());
    encuestado.set_direccionComplemento(usuarioDto.getEncuestadoDto().getDireccionComplemento());
    encuestado.set_genero(usuarioDto.getEncuestadoDto().getGenero());

    /*List<Telefono> telefonos = usuarioDto.getEncuestadoDto().getTelefonos();
    encuestado.set_telefonos(telefonos);*/

      DateFormat fecha = new SimpleDateFormat("dd-MM-yyyy");

      encuestado.set_fechaNacimiento(fecha.parse(usuarioDto.getEncuestadoDto().getFechaNacimiento()));
    encuestado.set_estadoCivil(usuarioDto.getEncuestadoDto().getEstadoCivil());
    encuestado.set_ocupacion(usuarioDto.getEncuestadoDto().getOcupacion());



      Parroquia parroquia = new Parroquia(usuarioDto.getEncuestadoDto().getParroquia().getId());
      encuestado.set_parroquia(parroquia);

      NivelEstudio nivelEstudio = new NivelEstudio(usuarioDto.getEncuestadoDto().getNivelEstudio().getId());
      encuestado.set_nivelEstudio(nivelEstudio);

      NivelSocioeconomico nivelSocioeconomico = new NivelSocioeconomico(usuarioDto.getEncuestadoDto().getNivelSocioeconomico().getId());
      encuestado.set_nivelSocioeconomico(nivelSocioeconomico);

    DaoUsuario daoUsuario = new DaoUsuario();
    Usuario usuario = new Usuario();
    usuario.set_nombreUsuario(usuarioDto.getNombreUsuario());
    usuario.set_estado("Activo");
    usuario.set_rol(rol);
    usuario.set_encuestado(encuestado);

    Usuario usuarioAgregado = daoUsuario.insert(usuario);
    usuarioDto.setId(usuarioAgregado.get_id());

    List<Telefono> telefonos = usuarioDto.getEncuestadoDto().getTelefonos();
    DaoTelefono daoTelefono = new DaoTelefono();

    for(Telefono tlf: telefonos){
      Encuestado encuestadoAgregado = new Encuestado(usuarioAgregado.get_encuestado().get_id());
      tlf.set_encuestado(encuestadoAgregado);
      daoTelefono.insert(tlf);
    }

    DirectorioActivo ldap = new DirectorioActivo();
    ldap.addEntryToLdap(usuarioDto, rol);

    data = Json.createObjectBuilder().add("usuario", usuarioDto.getId())
      .add("estado", "success")
      .add("code", 200)
      .build();

  }catch (javax.persistence.PersistenceException ex){
      String mensaje = "Este usuario ya se encuentra registrado en el sistema";
      data = Json.createObjectBuilder().add("mensaje", mensaje)
        .add("estado", "error")
        .add("code", 400)
        .build();
      System.out.println(data);
      return  Response.ok().entity(data).build();
    }
  catch (Exception ex){
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

    List<Usuario> usuarios = null;
    JsonObject data = null;
    try {

      DaoUsuario dao = new DaoUsuario();
      usuarios = dao.findAll(Usuario.class);

      JsonArrayBuilder usuariosArray = Json.createArrayBuilder();

      for(Usuario user: usuarios) {
        if(user.get_encuestado() != null) {
          JsonObject users = Json.createObjectBuilder()
            .add("id", user.get_id())
            .add("nombreUsuario", user.get_nombreUsuario())
            .add("primer nombre", user.get_encuestado().get_primerNombre())
            .add("primer apellido", user.get_encuestado().get_primerApellido())
            .add("numero de identificacion", user.get_encuestado().get_numeroIdentificacion())
            .add("estado", user.get_estado())
            .build();

          usuariosArray.add(users);
        }
      }
      data = Json.createObjectBuilder()
        .add("estado", 200)
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

  @GET
  @Path("/getestudios/{usuarioEncuestadoId}")
  public Response getEstudiosRealizables(@PathParam("usuarioEncuestadoId") long usuarioEncuestadoId){

    DaoUsuario daoUsuario = new DaoUsuario();

    Usuario usuario = daoUsuario.find(usuarioEncuestadoId, Usuario.class);
    Encuestado encuestado = usuario.get_encuestado();
    return null;
  }
}
