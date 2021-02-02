package ucab.dsw.logica.comando.usuario;

import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.directorioactivo.DirectorioActivo;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.logica.comando.ComandoBase;
import ucab.dsw.logica.fabrica.Fabrica;
import ucab.sw.mapper.usuario.MapperUsuario;

import javax.json.Json;
import javax.json.JsonObject;

public class ComandoUpdateEncuestado implements ComandoBase {

  private long idUsuario;
  private UsuarioDto usuarioDto;

  public ComandoUpdateEncuestado(long idUsuario, UsuarioDto usuarioDto) {
    this.idUsuario = idUsuario;
    this.usuarioDto = usuarioDto;
  }

  public void execute() throws Exception {

    try {

      DaoUsuario daoUsuario = Fabrica.crear(DaoUsuario.class);
      Usuario usuario = daoUsuario.find(this.idUsuario, Usuario.class);

      Usuario usuarioPorActualizar = MapperUsuario.MapUsuarioEncuestadoDtoToEntityUpdate(this.usuarioDto, usuario);
      Usuario resultado = daoUsuario.update(usuarioPorActualizar);

      if(this.usuarioDto.getContrasena()!=null){
        DirectorioActivo directorioActivo = new DirectorioActivo();
        directorioActivo.changePassword(this.usuarioDto);
      }

      this.usuarioDto = MapperUsuario.MapEntityToUsuarioEncuestadoDto(resultado);

    }catch (Exception ex){
      throw ex;
    }

  }

  public JsonObject getResultado(){

    try{

      JsonObject data = Json.createObjectBuilder()
        .add("usuario", this.usuarioDto.getId())
        .add("estado", "success")
        .add("code", 200)
        .build();

      return data;

    }catch (Exception ex){
      throw ex;
    }

  }

}
