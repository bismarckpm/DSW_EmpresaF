import io.jsonwebtoken.Claims;
import org.junit.Assert;
import org.junit.Test;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.servicio.autenticacion.ServicioAutenticacion;


public class ServicioAutenticacionTest {

  @Test
  public void generateToken(){
    ServicioAutenticacion servicio = new ServicioAutenticacion();

    UsuarioDto usuarioDto = new UsuarioDto();
    usuarioDto.setNombreUsuario( "pruebajj454" );
    usuarioDto.setContrasena("12345");

    String resultado = servicio.login(usuarioDto);
    Assert.assertNotNull(resultado);
  }

  @Test
  public void decodeToken(){
    ServicioAutenticacion servicio = new ServicioAutenticacion();
    String token = "eyJhbGciOiJIUzI1NiJ9.eyJub21icmVVc3VhcmlvIjoiZGFuaWVsIiwiaWF0IjoxNjA2OTYzOTAwLCJleHAiOjE2MDY5NjQyMDB9.Dsb-F8W1LMyfDU8zeB5hOsLd1lLbaM-XuL6waz1Uxu4";
    Claims resultado = servicio.decodeToken(token);
    Assert.assertNotNull(resultado);
  }
}
