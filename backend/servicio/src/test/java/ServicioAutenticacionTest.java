import io.jsonwebtoken.Claims;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.autenticacion.Autenticacion;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.excepciones.ProblemaExcepcion;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.servicio.autenticacion.ServicioAutenticacion;

import javax.json.JsonObject;
import javax.ws.rs.core.Response;


public class ServicioAutenticacionTest {

  private String token;

  @Before
  public void generateToken() throws PruebaExcepcion {

    Autenticacion autenticacion = new Autenticacion();

    UsuarioDto usuarioDto = new UsuarioDto();
    usuarioDto.setId((long) 4);
    usuarioDto.setNombreUsuario("administrador1@gmail.com");
    usuarioDto.setContrasena("12345");

    DaoUsuario daoUsuario = new DaoUsuario();
    Usuario usuario = daoUsuario.find((long) 4, Usuario.class);

    this.token = autenticacion.generateToken(usuarioDto);
    usuario.set_token(this.token);
    daoUsuario.update(usuario);

  }


  @Test
  public void cleanToken(){

    ServicioAutenticacion servicio = new ServicioAutenticacion();

    Response resultado = servicio.cleanToken(this.token, 171);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("usuario"));

  }

}
