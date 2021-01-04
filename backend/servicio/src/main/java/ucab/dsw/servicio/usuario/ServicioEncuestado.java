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

/**
 * Clase para gestionar los usuarios encuestados
 *
 */
@Path( "/encuestado" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioEncuestado extends AplicacionBase implements IServicioUsuario{

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
    encuestado.set_estado("activo");


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
    usuario.set_estado("activo");
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

  /**
   * Metodo para obtener todos los usuarios. Accedido mediante /encuestado/getall con el
   * metodo GET
   *
   * @return JSON success: {usuarios, code, estado}; error: {mensaje, estado,
   * code}
   */
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
          DaoTelefono daoTelefono = new DaoTelefono();
          List<Telefono> telefonos = daoTelefono.findAll(Telefono.class);
          for(Telefono telefono:telefonos){
            if(telefono.get_encuestado().get_id() == user.get_encuestado().get_id()){
              JsonObject phones = Json.createObjectBuilder()
                .add("codigoArea", telefono.get_codigoArea())
                .add("numeroTelefono", telefono.get_numeroTelefono()).build();

              telefonosArray.add(phones);
            }

          }
          JsonObject users = Json.createObjectBuilder()
            .add("id", user.get_id())
            .add("nombreUsuario", user.get_nombreUsuario())
            .add("primer_nombre", user.get_encuestado().get_primerNombre())
            .add("primer_apellido", user.get_encuestado().get_primerApellido())
            .add("numero_de_identificacion", user.get_encuestado().get_numeroIdentificacion())
            .add("estado", user.get_estado())
            .add("ocupacion", user.get_encuestado().get_ocupacion())
            .add("estadoCivil", user.get_encuestado().get_estadoCivil())
            .add("estado", user.get_estado())
            .add("telefonos", telefonosArray)
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
  public Response getUserById(@PathParam("usuarioEncuestadoId") long id){

    DaoUsuario daoUsuario = new DaoUsuario();
    JsonObject data;

    try{
      Usuario user = daoUsuario.find(id, Usuario.class);
      System.out.println(user.get_id());

      JsonArrayBuilder telefonosArray = Json.createArrayBuilder();

      for(Telefono telefono: user.get_encuestado().get_telefonos()){
        JsonObject phones = Json.createObjectBuilder()
          .add("telefonoId", telefono.get_id())
          .add("codigoArea", telefono.get_codigoArea())
          .add("numeroTelefono", telefono.get_numeroTelefono()).build();

        telefonosArray.add(phones);
      }

      data = Json.createObjectBuilder()
        .add("idUsuario", user.get_id())
        .add("idEncuestado", user.get_encuestado().get_id())
        .add("nombreUsuario", user.get_nombreUsuario())
        .add("primer_nombre", user.get_encuestado().get_primerNombre())
        .add("primer_apellido", user.get_encuestado().get_primerApellido())
        .add("numero_de_identificacion", user.get_encuestado().get_numeroIdentificacion())
        .add("estado", user.get_estado())
        .add("genero", user.get_encuestado().get_genero())
        .add("parroquiaId", user.get_encuestado().get_parroquia().get_id())
        .add("parroquia", user.get_encuestado().get_parroquia().get_nombreParroquia())
        .add("ocupacion", user.get_encuestado().get_ocupacion())
        .add("estadoCivil", user.get_encuestado().get_estadoCivil())
        .add("telefonos", telefonosArray)
        .build();

      return Response.ok().entity(data).build();

    }catch (Exception ex){

      data = Json.createObjectBuilder()
        .add("mensaje", ex.getMessage())
        .add("estado", "error")
        .add("code", 400)
        .build();

      return Response.ok().entity(data).build();
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
      usuario.get_encuestado().set_genero(usuarioDto.getEncuestadoDto().getGenero());


      usuario.get_encuestado().set_estadoCivil(usuarioDto.getEncuestadoDto().getEstadoCivil());
      usuario.get_encuestado().set_ocupacion(usuarioDto.getEncuestadoDto().getOcupacion());

      DaoParroquia daoParroquia = new DaoParroquia();
      Parroquia parroquia = daoParroquia.find(usuarioDto.getEncuestadoDto().getParroquia().getId(), Parroquia.class);
      usuario.get_encuestado().set_parroquia(parroquia);


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
      System.out.println(data);
      return  Response.ok().entity(data).build();
    }

    System.out.println(data);
    return  Response.ok().entity(data).build();
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
  public Response getEstudiosRealizables(@PathParam("usuarioEncuestadoId") long usuarioEncuestadoId){

    JsonObject data = null;

    try {
      DaoUsuario daoUsuario = new DaoUsuario();

      Usuario usuario = daoUsuario.find(usuarioEncuestadoId, Usuario.class);

      Encuestado encuestado = usuario.get_encuestado();

      DaoMuestra daoMuestra = new DaoMuestra();
      List<SolicitudEstudio> solicitudes = daoMuestra.getEstudiosRealizablesByEncuestado(encuestado);

      JsonArrayBuilder estudioRealizableArray = Json.createArrayBuilder();


     for (SolicitudEstudio solicitud: solicitudes) {
        if (solicitud.get_estado().equals("procesado") || solicitud.get_estado().equals("ejecutando")) {
          DaoEstudio daoEstudio = new DaoEstudio();
          List<Estudio> estudios = daoEstudio.findAll(Estudio.class);

          for(Estudio estudio:estudios){

            if(solicitud.get_estudio().get_id() == estudio.get_id() && !estudio.get_estado().equals("culminado")){
              DaoEncuesta daoEncuesta = new DaoEncuesta();
              Encuesta encuesta = daoEncuesta.find(estudio.get_encuesta().get_id(), Encuesta.class);
              JsonObject estu = Json.createObjectBuilder()
                .add("estudioId", estudio.get_id() )
                .add("nombreEstudio", estudio.get_nombreEstudio())
                .add("encuestaId", encuesta.get_id())
                .build();

              estudioRealizableArray.add(estu);
            }
          }
        }
      }

      data = Json.createObjectBuilder()
        .add("code", 200)
        .add("estado", "sucess")
        .add("estudios", estudioRealizableArray).build();
    }
    catch (Exception ex){

      data = Json.createObjectBuilder()
        .add("code", 400)
        .add("estado", "error")
        .build();
    }

    System.out.println(data);
    return Response.ok().entity(data).build();
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
  public Response disableUser(@PathParam("usuarioEncuestadoId") long id) {

    DaoUsuario daoUsuario = new DaoUsuario();
    JsonObject data;

    try{
      Usuario usuario = daoUsuario.find(id, Usuario.class);

      DaoEncuestado daoEncuestado = new DaoEncuestado();
      Encuestado encuestado = daoEncuestado.find(usuario.get_encuestado().get_id(), Encuestado.class);

      encuestado.set_estado("inactivo");
      usuario.set_estado("inactivo");

      Usuario resul = daoUsuario.update(usuario);
      daoEncuestado.update(encuestado);

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
  public Response enableUser(@PathParam("usuarioEncuestadoId") long id) {

    DaoUsuario daoUsuario = new DaoUsuario();
    JsonObject data;

    try{
      Usuario usuario = daoUsuario.find(id, Usuario.class);

      DaoEncuestado daoEncuestado = new DaoEncuestado();
      Encuestado encuestado = daoEncuestado.find(usuario.get_encuestado().get_id(), Encuestado.class);

      encuestado.set_estado("activo");
      usuario.set_estado("activo");

      Usuario resul = daoUsuario.update(usuario);
      daoEncuestado.update(encuestado);

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
}
