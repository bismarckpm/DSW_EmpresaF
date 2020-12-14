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


      DateFormat fecha = new SimpleDateFormat("dd-MM-yyyy");

      encuestado.set_fechaNacimiento(fecha.parse(usuarioDto.getEncuestadoDto().getFechaNacimiento()));
    encuestado.set_estadoCivil(usuarioDto.getEncuestadoDto().getEstadoCivil());
    encuestado.set_ocupacion(usuarioDto.getEncuestadoDto().getOcupacion());

      DaoParroquia daoParroquia = new DaoParroquia();
      Parroquia parroquia = daoParroquia.find(usuarioDto.getEncuestadoDto().getParroquia().getId(), Parroquia.class);
      encuestado.set_parroquia(parroquia);

      DaoNivelEstudio dao = new DaoNivelEstudio();
      NivelEstudio nivelEstudio = dao.find(usuarioDto.getEncuestadoDto().getNivelEstudio().getId(), NivelEstudio.class);
      encuestado.set_nivelEstudio(nivelEstudio);

      DaoNivelSocioeconomico daoNivelSocioeconomico = new DaoNivelSocioeconomico();
      NivelSocioeconomico nivelSocioeconomico = daoNivelSocioeconomico.find(usuarioDto.getEncuestadoDto().getNivelSocioeconomico().getId(), NivelSocioeconomico.class);
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
      JsonArrayBuilder telefonosArray = Json.createArrayBuilder();

      for(Usuario user: usuarios) {
        if(user.get_encuestado() != null) {
          for(Telefono telefono:user.get_encuestado().get_telefonos()){
            JsonObject phones = Json.createObjectBuilder()
              .add("codigoArea", telefono.get_codigoArea())
              .add("numeroTelefono", telefono.get_numeroTelefono()).build();

            telefonosArray.add(phones);
          }
          JsonObject users = Json.createObjectBuilder()
            .add("id", user.get_id())
            .add("nombreUsuario", user.get_nombreUsuario())
            .add("primer nombre", user.get_encuestado().get_primerNombre())
            .add("primer apellido", user.get_encuestado().get_primerApellido())
            .add("numero de identificacion", user.get_encuestado().get_numeroIdentificacion())
            .add("estado", user.get_estado())
            .add("ocupacion", user.get_encuestado().get_ocupacion())
            .add("estadoCivil", user.get_encuestado().get_estadoCivil())
            .add("telefonos", telefonosArray)
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

  @PUT
  @Path("/update/{usuarioEncuestadoid}")
  public Response updateUser(@PathParam("usuarioEncuestadoid") long id, UsuarioDto usuarioDto) {

    JsonObject data;
    DaoUsuario daoUsuario = new DaoUsuario();

    try {

      Usuario usuario = daoUsuario.find(id, Usuario.class);

      usuarioDto.setNombreUsuario(usuario.get_nombreUsuario());

      usuario.get_encuestado().set_numeroIdentificacion(usuarioDto.getEncuestadoDto().getNumeroIdentificacion());
      usuario.get_encuestado().set_primerNombre(usuarioDto.getEncuestadoDto().getPrimerNombre());
      usuario.get_encuestado().set_segundoNombre(usuarioDto.getEncuestadoDto().getSegundoNombre());
      usuario.get_encuestado().set_primerApellido(usuarioDto.getEncuestadoDto().getPrimerApellido());
      usuario.get_encuestado().set_segundoApellido(usuarioDto.getEncuestadoDto().getSegundoApellido());
      usuario.get_encuestado().set_direccionComplemento(usuarioDto.getEncuestadoDto().getDireccionComplemento());
      usuario.get_encuestado().set_genero(usuarioDto.getEncuestadoDto().getGenero());


      DateFormat fecha = new SimpleDateFormat("dd-MM-yyyy");

      usuario.get_encuestado().set_fechaNacimiento(fecha.parse(usuarioDto.getEncuestadoDto().getFechaNacimiento()));
      usuario.get_encuestado().set_estadoCivil(usuarioDto.getEncuestadoDto().getEstadoCivil());
      usuario.get_encuestado().set_ocupacion(usuarioDto.getEncuestadoDto().getOcupacion());

      DaoParroquia daoParroquia = new DaoParroquia();
      Parroquia parroquia = daoParroquia.find(usuarioDto.getEncuestadoDto().getParroquia().getId(), Parroquia.class);
      usuario.get_encuestado().set_parroquia(parroquia);

      DaoNivelEstudio dao = new DaoNivelEstudio();
      NivelEstudio nivelEstudio = dao.find(usuarioDto.getEncuestadoDto().getNivelEstudio().getId(), NivelEstudio.class);
      usuario.get_encuestado().set_nivelEstudio(nivelEstudio);

      DaoNivelSocioeconomico daoNivelSocioeconomico = new DaoNivelSocioeconomico();
      NivelSocioeconomico nivelSocioeconomico = daoNivelSocioeconomico.find(usuarioDto.getEncuestadoDto().getNivelSocioeconomico().getId(), NivelSocioeconomico.class);
      usuario.get_encuestado().set_nivelSocioeconomico(nivelSocioeconomico);

      Usuario resul = daoUsuario.update(usuario);

      DirectorioActivo directorioActivo = new DirectorioActivo();
      directorioActivo.changePassword(usuarioDto);

      data = Json.createObjectBuilder().add("usuario", resul.get_id())
        .add("estado", "success")
        .add("code", 200)
        .build();


    }catch (Exception ex){
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
  @Path("/getestudios/{usuarioEncuestadoId}")
  public Response getEstudiosRealizables(@PathParam("usuarioEncuestadoId") long usuarioEncuestadoId){

    JsonObject data;
    List<SolicitudEstudio> solicitudes;

    try {
      DaoUsuario daoUsuario = new DaoUsuario();

      Usuario usuario = daoUsuario.find(usuarioEncuestadoId, Usuario.class);
      Encuestado encuestado = usuario.get_encuestado();

      DaoMuestra daoMuestra = new DaoMuestra();
      solicitudes = daoMuestra.getEstudiosRealizablesByEncuestado(encuestado);

      JsonArrayBuilder solicitudesArray = Json.createArrayBuilder();

      for (SolicitudEstudio solicitud: solicitudes) {
        if (solicitud.get_estado().equals("procesado") || solicitud.get_estado().equals("ejecutando")) {
          JsonObject soli = Json.createObjectBuilder()
            .add("estudioId", solicitud.get_estudio().get_id())
            .add("encuestaId", solicitud.get_estudio().get_encuesta().get_id())
            .build();

          solicitudesArray.add(soli);
        }
      }

      data = Json.createObjectBuilder()
        .add("code", 200)
        .add("estado", "success")
        .add("solicitudes", solicitudesArray).build();
    }
    catch (Exception ex){

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
  @Path("/getmuestra/{solicitudId}")
  public Response getMuestra(@PathParam("solicitudId") long solicitudId){

    JsonObject data;

    try {
      DaoMuestra daoMuestra = new DaoMuestra();

      DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();
      SolicitudEstudio solicitudEstudio = daoSolicitudEstudio.find(solicitudId, SolicitudEstudio.class);

      List<Encuestado> encuestadosMuestra = daoMuestra.getEncuestadosMuestraBySolicitud(solicitudEstudio);

      JsonArrayBuilder encuestadosArray = Json.createArrayBuilder();

      for (Encuestado encuestado : encuestadosMuestra) {
        JsonObject encu = Json.createObjectBuilder()
          .add("encuestadoId", encuestado.get_id())
          .add("encuestadoNombre", encuestado.get_primerNombre())
          .add("encuestadoApellido", encuestado.get_primerApellido())
          .build();

        encuestadosArray.add(encu);
      }

      data = Json.createObjectBuilder()
        .add("code", 200)
        .add("estado", "success")
        .add("solicitudes", encuestadosArray).build();

    }
    catch (Exception ex){
      data = Json.createObjectBuilder()
        .add("code", 400)
        .add("estado", "error").build();

      System.out.println(data);
      return Response.ok().entity(data).build();
    }

    System.out.println(data);
    return Response.ok().entity(data).build();
  }


  public Response disableUser(UsuarioDto usuarioDto) {
    return null;
  }
}