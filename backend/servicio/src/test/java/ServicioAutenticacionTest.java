import io.jsonwebtoken.Claims;
import org.junit.Assert;
import org.junit.Test;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.excepciones.ProblemaExcepcion;
import ucab.dsw.servicio.autenticacion.ServicioAutenticacion;

import javax.json.JsonObject;
import javax.ws.rs.core.Response;


public class ServicioAutenticacionTest {

  @Test
  public void generateToken(){

    ServicioAutenticacion servicio = new ServicioAutenticacion();

    UsuarioDto usuarioDto = new UsuarioDto();
    usuarioDto.setNombreUsuario( "administrador1@gmail.com");
    usuarioDto.setContrasena("12345");

    Response resultado = servicio.login(usuarioDto);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("token"));

  }

  @Test
  public void cleanToken(){

    ServicioAutenticacion servicio = new ServicioAutenticacion();

    Response resultado = servicio.cleanToken(171);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("usuario"));

  }

}
