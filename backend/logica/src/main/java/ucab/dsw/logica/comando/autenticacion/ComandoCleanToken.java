package ucab.dsw.logica.comando.autenticacion;

import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.logica.comando.ComandoBase;
import ucab.dsw.logica.fabrica.Fabrica;

import javax.json.Json;
import javax.json.JsonObject;

public class ComandoCleanToken implements ComandoBase {

  private long idUsuario;

  public ComandoCleanToken(long idUsuario) {
    this.idUsuario = idUsuario;
  }

  public void execute() throws Exception {

    try {

      DaoUsuario daoUsuario = Fabrica.crear(DaoUsuario.class);
      Usuario usuario = daoUsuario.find(this.idUsuario, Usuario.class);

      usuario.set_token(null);
      daoUsuario.update(usuario);

    }catch (Exception ex){
        throw ex;
    }

  }

  public JsonObject getResultado(){

    try {

      JsonObject data = Json.createObjectBuilder()
        .add("usuario", this.idUsuario)
        .add("estado", "success")
        .add("code", 200)
        .build();

      return data;

    }catch (Exception ex){
      throw ex;
    }

  }

}
