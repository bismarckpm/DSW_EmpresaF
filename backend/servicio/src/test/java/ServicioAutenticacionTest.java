import io.jsonwebtoken.Claims;
import org.junit.Assert;
import org.junit.Test;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.servicio.autenticacion.ServicioAutenticacion;

import javax.ws.rs.core.Response;


public class ServicioAutenticacionTest {

  @Test
  public void generateToken(){
    ServicioAutenticacion servicio = new ServicioAutenticacion();

    UsuarioDto usuarioDto = new UsuarioDto();
    usuarioDto.setNombreUsuario( "pruebarepetida245@gmail.com");
    usuarioDto.setContrasena("5678");

    Response resultado = servicio.login(usuarioDto);
    Assert.assertEquals(resultado.getStatus(), 200);
  }

  @Test
  public void decodeToken(){
    ServicioAutenticacion servicio = new ServicioAutenticacion();
    String token = "eyJhbGciOiJIUzI1NiJ9.eyJub21icmVVc3VhcmlvIjoiYmlzbWFyY2twbTIiLCJyb2wiOiJhZG1pbmlzdHJhZG9yIiwiaWF0IjoxNjA3MjE0MjYxLCJleHAiOjE2MDcyMTQ1NjF9.EdDKei-HmliSNMKstuvksw7EPAOMGziaWASmD5UXJJE";
    Response resultado = servicio.decodeToken(token);
    Assert.assertEquals(resultado.getStatus(), 200);
  }
}
