package ucab.dsw.logica.comando.autenticacion;

import io.jsonwebtoken.Claims;
import ucab.dsw.autenticacion.Autenticacion;
import ucab.dsw.logica.comando.ComandoBase;
import ucab.dsw.logica.fabrica.Fabrica;

public class ComandoDecode implements ComandoBase {

  private Claims resultado;
  private String token;

  public ComandoDecode(String token) {
    this.token = token;
  }

  public void execute() throws Exception {

    try {

      Autenticacion autenticacion = Fabrica.crear(Autenticacion.class);
      this.resultado = autenticacion.decode(this.token);

    }catch (Exception ex){
      throw ex;
    }

  }

  public Claims getResultado(){

     try {

       return resultado;

     }catch (Exception ex){
       throw ex;
     }

  }

}
