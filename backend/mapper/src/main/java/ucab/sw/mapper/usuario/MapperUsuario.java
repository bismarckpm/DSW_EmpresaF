package ucab.sw.mapper.usuario;

import ucab.dsw.accesodatos.DaoNivelEstudio;
import ucab.dsw.accesodatos.DaoNivelSocioeconomico;
import ucab.dsw.accesodatos.DaoParroquia;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.*;
import ucab.dsw.logica.fabrica.Fabrica;

import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class MapperUsuario {

  public static Usuario MapUsuarioAdminDtoToEntityAdd(UsuarioDto usuarioDto){

    try {

      Usuario usuario = Fabrica.crear(Usuario.class);
      usuario.set_nombreUsuario(usuarioDto.getNombreUsuario());
      usuario.set_estado("activo");

      String rol = "administrador";
      usuario.set_rol(rol);


      return usuario;

    }catch (Exception ex){
      ex.printStackTrace();
      return null;
    }

  }

  public static Usuario MapUsuarioClienDtoToEntityUpdate(UsuarioDto usuarioDto, Usuario usuarioPorActualizar){

    try {

      usuarioPorActualizar.get_cliente().setNombre(usuarioDto.getClienteDto().getNombre());

      return usuarioPorActualizar;

    }catch (Exception ex){
      ex.printStackTrace();
      return null;
    }

  }

  public static Usuario MapUsuarioClienteDtoToEntityAdd(UsuarioDto usuarioDto){

    try {

      Cliente cliente = Fabrica.crear(Cliente.class);
      cliente.setNombre(usuarioDto.getClienteDto().getNombre());
      cliente.set_estado("activo");

      Usuario usuario = Fabrica.crear(Usuario.class);
      usuario.set_nombreUsuario(usuarioDto.getNombreUsuario());
      usuario.set_estado("activo");

      String rol = "cliente";
      usuario.set_rol(rol);

      usuario.set_cliente(cliente);


      return usuario;

    }catch (Exception ex){
      ex.printStackTrace();
      return null;
    }

  }

  public static Usuario MapUsuarioEncuestadoDtoToEntityAdd(UsuarioDto usuarioDto){

    try {

      Encuestado encuestado = Fabrica.crear(Encuestado.class);
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

      Usuario usuario = Fabrica.crear(Usuario.class);
      usuario.set_nombreUsuario(usuarioDto.getNombreUsuario());
      usuario.set_estado("activo");

      String rol = "encuestado";
      usuario.set_rol(rol);

      usuario.set_encuestado(encuestado);

      return usuario;

    }catch (Exception ex){
      ex.printStackTrace();
      return null;
    }

  }

  public static Usuario MapUsuarioEncuestadoDtoToEntityUpdate(UsuarioDto usuarioDto, Usuario usuarioPorActualizar){

    try {

      usuarioPorActualizar.set_nombreUsuario(usuarioDto.getNombreUsuario());
      usuarioPorActualizar.get_encuestado().set_numeroIdentificacion(usuarioDto.getEncuestadoDto().getNumeroIdentificacion());
      usuarioPorActualizar.get_encuestado().set_primerNombre(usuarioDto.getEncuestadoDto().getPrimerNombre());
      usuarioPorActualizar.get_encuestado().set_segundoNombre(usuarioDto.getEncuestadoDto().getSegundoNombre());
      usuarioPorActualizar.get_encuestado().set_primerApellido(usuarioDto.getEncuestadoDto().getPrimerApellido());
      usuarioPorActualizar.get_encuestado().set_segundoApellido(usuarioDto.getEncuestadoDto().getSegundoApellido());
      usuarioPorActualizar.get_encuestado().set_genero(usuarioDto.getEncuestadoDto().getGenero());


      usuarioPorActualizar.get_encuestado().set_estadoCivil(usuarioDto.getEncuestadoDto().getEstadoCivil());
      usuarioPorActualizar.get_encuestado().set_ocupacion(usuarioDto.getEncuestadoDto().getOcupacion());

      DaoParroquia daoParroquia = new DaoParroquia();
      Parroquia parroquia = daoParroquia.find(usuarioDto.getEncuestadoDto().getParroquia().getId(), Parroquia.class);
      usuarioPorActualizar.get_encuestado().set_parroquia(parroquia);

      return usuarioPorActualizar;

    }catch (Exception ex){
      ex.printStackTrace();
      return null;
    }

  }

  public static Usuario MapUsuarioAnalisDtoToEntityAdd(UsuarioDto usuarioDto){

    try {

      Usuario usuario = Fabrica.crear(Usuario.class);
      usuario.set_nombreUsuario(usuarioDto.getNombreUsuario());
      usuario.set_estado("activo");

      String rol = "analista";
      usuario.set_rol(rol);


      return usuario;

    }catch (Exception ex){
      ex.printStackTrace();
      return null;
    }

  }

  public static UsuarioDto MapEntityToUsuarioDto(Usuario usuario){

    try{

      UsuarioDto usuarioDto = Fabrica.crear(UsuarioDto.class);
      usuarioDto.setId(usuario.get_id());
      usuarioDto.setNombreUsuario(usuario.get_nombreUsuario());
      usuarioDto.setEstado(usuario.get_estado());

      return usuarioDto;

    }catch (Exception ex){
      ex.printStackTrace();
      return null;
    }

  }

  public static UsuarioDto MapEntityToUsuarioClienteDto(Usuario usuario){

    try{

      UsuarioDto usuarioDto = Fabrica.crear(UsuarioDto.class);
      usuarioDto.setId(usuario.get_id());
      usuarioDto.setNombreUsuario(usuario.get_nombreUsuario());
      usuarioDto.setEstado(usuario.get_estado());

      ClienteDto clienteDto = Fabrica.crear(ClienteDto.class);
      clienteDto.setNombre(usuario.get_cliente().getNombre());

      usuarioDto.setClienteDto(clienteDto);

      return usuarioDto;

    }catch (Exception ex){
      ex.printStackTrace();
      return null;
    }

  }

  public  static UsuarioDto MapEntityToUsuarioEncuestadoDto (Usuario usuario) throws Exception {

    try {

      UsuarioDto usuarioDto = Fabrica.crear(UsuarioDto.class);
      usuarioDto.setId(usuario.get_id());
      usuarioDto.setNombreUsuario(usuario.get_nombreUsuario());
      usuarioDto.setEstado(usuario.get_estado());

      EncuestadoDto encuestadoDto = Fabrica.crear(EncuestadoDto.class);
      encuestadoDto.setId(usuario.get_encuestado().get_id());
      encuestadoDto.setPrimerNombre(usuario.get_encuestado().get_primerNombre());
      encuestadoDto.setPrimerApellido(usuario.get_encuestado().get_primerApellido());
      encuestadoDto.setNumeroIdentificacion(usuario.get_encuestado().get_numeroIdentificacion());
      encuestadoDto.setGenero(usuario.get_encuestado().get_genero());

      ParroquiaDto parroquiaDto = Fabrica.crear(ParroquiaDto.class);
      parroquiaDto.setId(usuario.get_encuestado().get_parroquia().get_id());
      parroquiaDto.setNombreParroquia(usuario.get_encuestado().get_parroquia().get_nombreParroquia());
      encuestadoDto.setParroquia(parroquiaDto);

      encuestadoDto.setOcupacion(usuario.get_encuestado().get_ocupacion());
      encuestadoDto.setOcupacion(usuario.get_encuestado().get_ocupacion());
      encuestadoDto.setEstadoCivil(usuario.get_encuestado().get_estadoCivil());

      usuarioDto.setEncuestadoDto(encuestadoDto);

      return usuarioDto;

    }catch (Exception ex){
      throw ex;
    }

  }

  public  static EncuestadoDto MapEntityToEncuestadoDto (Encuestado encuestado) throws Exception {

    try {

      EncuestadoDto encuestadoDto = Fabrica.crear(EncuestadoDto.class);
      encuestadoDto.setId(encuestado.get_id());
      encuestadoDto.setPrimerNombre(encuestado.get_primerNombre());
      encuestadoDto.setPrimerApellido(encuestado.get_primerApellido());
      encuestadoDto.setGenero(encuestado.get_genero());
      encuestadoDto.setEstadoCivil(encuestado.get_estadoCivil());

      return encuestadoDto;

    }catch (Exception ex){
      throw ex;
    }

  }

}
