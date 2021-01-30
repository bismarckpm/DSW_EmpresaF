package ucab.dsw.logica.comando.usuario;

import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.directorioactivo.DirectorioActivo;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.excepciones.LimiteExcepcion;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.excepciones.RangoExcepcion;
import ucab.dsw.excepciones.SolicitudPendienteExcepcion;
import ucab.dsw.logica.comando.ComandoBase;
import ucab.dsw.logica.fabrica.Fabrica;
import ucab.sw.mapper.usuario.MapperUsuario;

import javax.json.Json;
import javax.json.JsonObject;
import javax.naming.NamingException;
import java.lang.reflect.InvocationTargetException;

public class ComandoUpdateCliente implements ComandoBase {

  private long idUsuario;
  private UsuarioDto usuarioDto;

  public ComandoUpdateCliente(long idUsuario, UsuarioDto usuarioDto) {
    this.idUsuario = idUsuario;
    this.usuarioDto = usuarioDto;
  }

  public void execute() throws LimiteExcepcion, SolicitudPendienteExcepcion, PruebaExcepcion, InstantiationException, IllegalAccessException, InvocationTargetException, RangoExcepcion, NamingException {

    try {

      DaoUsuario daoUsuario = Fabrica.crear(DaoUsuario.class);
      Usuario usuario = daoUsuario.find(this.idUsuario, Usuario.class);

      Usuario usuarioPorActualizar = MapperUsuario.MapUsuarioClienDtoToEntityUpdate(this.usuarioDto, usuario);
      Usuario resultado = daoUsuario.update(usuarioPorActualizar);

      if(this.usuarioDto.getContrasena() != null){

        DirectorioActivo directorioActivo = new DirectorioActivo();
        directorioActivo.changePassword(this.usuarioDto);

      }

      this.usuarioDto = MapperUsuario.MapEntityToUsuarioDto(resultado);

    }catch (Exception ex){
      throw ex;
    }

  }

  public JsonObject getResultado(){

    try {

      JsonObject data = Json.createObjectBuilder().add("usuario", this.usuarioDto.getId())
        .add("estado", "success")
        .add("code", 200)
        .build();

      return data;

    }catch (Exception ex){
      throw ex;
    }

  }

}
