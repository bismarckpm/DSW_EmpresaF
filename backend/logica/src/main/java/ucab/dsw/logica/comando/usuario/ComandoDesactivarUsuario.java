package ucab.dsw.logica.comando.usuario;

import ucab.dsw.accesodatos.DaoUsuario;
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

public class ComandoDesactivarUsuario implements ComandoBase {

  private long usuarioId;
  private UsuarioDto usuarioDto;

  public ComandoDesactivarUsuario(long usuarioId) {
    this.usuarioId = usuarioId;
  }

  public void execute() throws LimiteExcepcion, SolicitudPendienteExcepcion, PruebaExcepcion, InstantiationException, IllegalAccessException, InvocationTargetException, RangoExcepcion, NamingException {

    try {

      DaoUsuario daoUsuario = Fabrica.crear(DaoUsuario.class);
      Usuario usuario = daoUsuario.find(this.usuarioId, Usuario.class);

      usuario.set_estado("inactivo");

      if(usuario.get_encuestado() !=null) {

        usuario.get_encuestado().set_estado("activo");

      }else if(usuario.get_cliente() !=null){

        usuario.get_cliente().set_estado("activo");

      }

      Usuario resultado = daoUsuario.update(usuario);
      this.usuarioDto = MapperUsuario.MapEntityToUsuarioDto(resultado);

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
