package ucab.sw.mapper.usuario;

import ucab.dsw.dtos.CategoriaDto;
import ucab.dsw.dtos.ClienteDto;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Cliente;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.logica.fabrica.Fabrica;

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

}
