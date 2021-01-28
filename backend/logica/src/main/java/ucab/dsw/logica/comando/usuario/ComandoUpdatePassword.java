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

import javax.json.Json;
import javax.json.JsonObject;
import javax.naming.NamingException;
import java.lang.reflect.InvocationTargetException;

public class ComandoUpdatePassword implements ComandoBase {

  private long usuarioId;
  private UsuarioDto usuarioDto;

  public ComandoUpdatePassword(long usuarioId, UsuarioDto usuarioDto) {
    this.usuarioId = usuarioId;
    this.usuarioDto = usuarioDto;
  }

  public void execute() throws LimiteExcepcion, SolicitudPendienteExcepcion, PruebaExcepcion, InstantiationException, IllegalAccessException, InvocationTargetException, RangoExcepcion, NamingException {

    try {

      DaoUsuario daoUsuario = Fabrica.crear(DaoUsuario.class);
      daoUsuario.find(this.usuarioId, Usuario.class);

      if(this.usuarioDto.getContrasena () !=null){

        DirectorioActivo directorioActivo = new DirectorioActivo();
        directorioActivo.changePassword(this.usuarioDto);

      }

    }catch (Exception ex){
      throw ex;
    }

  }

  public JsonObject getResultado(){

    try {

      JsonObject data = Json.createObjectBuilder()
        .add("estado", "success")
        .add("code", 200)
        .build();

      return data;

    }catch (Exception ex){
      throw ex;
    }

  }

}
