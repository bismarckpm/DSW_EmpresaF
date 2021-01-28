import org.junit.Assert;
import org.junit.Test;
import ucab.dsw.dtos.EstudioDto;
import ucab.dsw.dtos.SolicitudEstudioDto;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.servicio.usuario.ServicioAdministrador;

import javax.json.JsonObject;
import javax.ws.rs.core.Response;

public class ServicioAdministradorTest {

  @Test
  public void addUsuarioAdministrador(){

    UsuarioDto usuarioDto = new UsuarioDto();
    usuarioDto.setNombreUsuario("prueba101@gmail.com");
    usuarioDto.setContrasena("1234");

    ServicioAdministrador servicioAdministrador = new ServicioAdministrador();

    Response resultado = servicioAdministrador.addUser(usuarioDto);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("usuario"));

  }

  @Test
  public void getUsuariosAdministradores(){

    ServicioAdministrador servicioAdministrador = new ServicioAdministrador();

    Response resultado = servicioAdministrador.getUsers();
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("usuarios"));

  }

  @Test
  public void getUsuarioAdministradorTest(){

    ServicioAdministrador servicioAdministrador = new ServicioAdministrador();

    Response resultado = servicioAdministrador.getUserById(6);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("id"));

  }

  @Test
  public void changePasswordTest(){

    ServicioAdministrador servicioAdministrador = new ServicioAdministrador();

    UsuarioDto usuarioDto = new UsuarioDto();
    usuarioDto.setNombreUsuario("pruebaañadirrr@gmail.com");
    usuarioDto.setContrasena("5678");

    Response resultado = servicioAdministrador.changePassword(158, usuarioDto);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("estado"));

  }

  @Test
  public void desactivarUserTest(){

    ServicioAdministrador servicioAdministrador = new ServicioAdministrador();
    Response resultado = servicioAdministrador.disableUser(156);

    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("usuario"));

  }

  @Test
  public void activarUserTest(){

    ServicioAdministrador servicioAdministrador = new ServicioAdministrador();
    Response resultado = servicioAdministrador.enableUser(156);

    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("usuario"));

  }

  @Test
  public void getSolicitudesPendientesByAdminTest(){

    ServicioAdministrador servicioAdministrador = new ServicioAdministrador();

    Response resultado = servicioAdministrador.getSolicitudesPendientes(4);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("solicitudes"));

  }

}
