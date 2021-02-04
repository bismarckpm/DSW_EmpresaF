package ucab.dsw.logica.comando.autenticacion;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.autenticacion.Autenticacion;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.excepciones.ProblemaExcepcion;
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

      DaoUsuario daoUsuario = Fabrica.crear(DaoUsuario.class);
      Usuario usuario = daoUsuario.find(Long.valueOf(this.resultado.getSubject()), Usuario.class);

      if(!usuario.get_token().equals(this.token)){

        throw new ProblemaExcepcion("El token enviado es incorrecto, intente de nuevo", "el token no coincide en base de datos");

      }

    }catch (MalformedJwtException ex){

      throw new ProblemaExcepcion("El token enviado es incorrecto", ex.getMessage());

    }
    catch (ExpiredJwtException ex){

      throw new ProblemaExcepcion("El token enviado ha expirado", ex.getMessage());

    }
    catch (SignatureException ex){

      throw new ProblemaExcepcion("El token no es valido y podria ser peligroso", ex.getMessage());

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
