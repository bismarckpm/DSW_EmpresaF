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

public class ComandoGetAnalista implements ComandoBase {

  private long idUsuario;
  private UsuarioDto usuarioDto;

  public ComandoGetAnalista(long idUsuario) {
    this.idUsuario = idUsuario;
  }

  public void execute() throws LimiteExcepcion, SolicitudPendienteExcepcion, PruebaExcepcion, InstantiationException, IllegalAccessException, InvocationTargetException, RangoExcepcion, NamingException {

    try {

      DaoUsuario daoUsuario = Fabrica.crear(DaoUsuario.class);
      Usuario resultado = daoUsuario.find(this.idUsuario, Usuario.class);

      this.usuarioDto = MapperUsuario.MapEntityToUsuarioDto(resultado);

    }catch (Exception ex){
      throw ex;
    }

  }

  public JsonObject getResultado(){

    try{

      JsonObject data = Json.createObjectBuilder()
        .add("estado", "success")
        .add("code", 200)
        .add("id", this.usuarioDto.getId())
        .add("nombreUsuario", this.usuarioDto.getNombreUsuario())
        .add("estadoUsuario", this.usuarioDto.getEstado())
        .build();

      return data;

    }catch (Exception ex){
      throw ex;
    }

  }

}
