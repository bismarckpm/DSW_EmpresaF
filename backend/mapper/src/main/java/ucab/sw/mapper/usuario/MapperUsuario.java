package ucab.sw.mapper.usuario;

import ucab.dsw.dtos.CategoriaDto;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.logica.fabrica.Fabrica;

public class MapperUsuario {

  public static Usuario MapUsuarioDtoToEntityAdd(UsuarioDto usuarioDto){

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

}
