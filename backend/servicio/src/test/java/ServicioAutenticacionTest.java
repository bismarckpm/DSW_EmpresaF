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
    usuarioDto.setNombreUsuario( "pruebaEncuestadoAgains" );
    usuarioDto.setContrasena("12345");

    Response resultado = servicio.login(usuarioDto);
    Assert.assertEquals(resultado.getStatus(), 200);
  }

  @Test
  public void decodeToken(){
    ServicioAutenticacion servicio = new ServicioAutenticacion();
    String token = "eyJhbGciOiJIUzI1NiJ9.eyJub21icmVVc3VhcmlvIjoicHJ1ZWJhamo0NTQiLCJpYXQiOjE2MDcxMDg3NjcsImV4cCI6MTYwNzEwOTA2N30.Lg49lCDf59apkhaNqjbtoVuFsXZIBGjF__SiM6gniWM";
    Response resultado = servicio.decodeToken(token);
    Assert.assertEquals(resultado.getStatus(), 200);
  }
}
