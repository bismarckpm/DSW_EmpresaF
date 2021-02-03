import io.jsonwebtoken.Claims;
import org.junit.Assert;
import org.junit.Test;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.servicio.autenticacion.ServicioAutenticacion;

import javax.json.JsonObject;
import javax.ws.rs.core.Response;


public class ServicioAutenticacionTest {

  @Test
  public void generateToken(){

    ServicioAutenticacion servicio = new ServicioAutenticacion();

    UsuarioDto usuarioDto = new UsuarioDto();
    usuarioDto.setNombreUsuario( "maria254567@gmail.com");
    usuarioDto.setContrasena("5678");

    Response resultado = servicio.login(usuarioDto);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("token"));

  }

  @Test
  public void decodeToken(){

    ServicioAutenticacion servicio = new ServicioAutenticacion();
    String token = "eyJhbGciOiJIUzI1NiJ9.eyJub21icmVVc3VhcmlvIjoibmV3MjJAZ21haWwuY29tIiwicm9sIjoiYW5hbGlzdGEiLCJpYXQiOjE2MTIyODgyNDYsImV4cCI6MTYxMjkzNjI0Nn0.XKO5BkdhtHaIjX7CNQLGzOKcmiiyeD8CVGow6cNDYEw";

    Response resultado = servicio.decodeToken(token);
    Claims respuesta = (Claims) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("nombreUsuario"));

  }

}
