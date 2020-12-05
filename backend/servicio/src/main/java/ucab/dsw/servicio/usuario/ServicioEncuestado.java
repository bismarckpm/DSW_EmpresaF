package ucab.dsw.servicio.usuario;

import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.directorioactivo.DirectorioActivo;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.entidades.Encuestado;
import ucab.dsw.entidades.NivelEstudio;
import ucab.dsw.entidades.Parroquia;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.servicio.AplicacionBase;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path( "/encuestado" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioEncuestado extends AplicacionBase implements IServicioUsuario{


  @POST
  @Path("/add")
  public Response addUser(UsuarioDto usuarioDto) {
    JsonObject data=null;

    try{

    Encuestado encuestado = new Encuestado();
    encuestado.set_numeroIdentificacion(usuarioDto.getEncuestadoDto().getNumeroIdentificacion());
    encuestado.set_primerNombre(usuarioDto.getEncuestadoDto().getPrimerNombre());
    encuestado.set_segundoNombre(usuarioDto.getEncuestadoDto().getSegundoNombre());
    encuestado.set_primerApellido(usuarioDto.getEncuestadoDto().getPrimerApellido());
    encuestado.set_segundoApellido(usuarioDto.getEncuestadoDto().getSegundoApellido());
    encuestado.set_direccionComplemento(usuarioDto.getEncuestadoDto().getDireccionComplemento());
    encuestado.set_genero(usuarioDto.getEncuestadoDto().getGenero());
    encuestado.set_fechaNacimiento(usuarioDto.getEncuestadoDto().getFechaNacimiento());
    encuestado.set_estadoCivil(usuarioDto.getEncuestadoDto().getEstadoCivil());
    encuestado.set_ocupacion(usuarioDto.getEncuestadoDto().getOcupacion());

      Parroquia parroquia = new Parroquia(usuarioDto.getEncuestadoDto().getParroquia().getId());
      encuestado.set_parroquia(parroquia);

      NivelEstudio nivelEstudio = new NivelEstudio(usuarioDto.getEncuestadoDto().getNivelEstudio().getId());
      encuestado.set_nivelEstudio(nivelEstudio);

    DaoUsuario daoUsuario = new DaoUsuario();
    Usuario usuario = new Usuario();
    usuario.set_nombreUsuario(usuarioDto.getNombreUsuario());
    usuario.set_estado("Activo");
    usuario.set_encuestado(encuestado);

    Usuario usuarioAgregado = daoUsuario.insert(usuario);
    usuarioDto.setId(usuarioAgregado.get_id());

    DirectorioActivo ldap = new DirectorioActivo();
    ldap.addEntryToLdap(usuarioDto);

    data = Json.createObjectBuilder().add("usuario", usuarioDto.getId())
      .add("estado", "success")
      .add("code", 200)
      .build();

  }
  catch (Exception ex){
    data = Json.createObjectBuilder().add("mensaje", ex.getMessage())
      .add("estado", "error")
      .add("code", 400)
      .build();
    return  Response.ok().entity(data).build();
  }

    System.out.println(data);
    return  Response.ok().entity(data).build();
  }
}
