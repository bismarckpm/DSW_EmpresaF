import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.autenticacion.Autenticacion;
import ucab.dsw.dtos.EstudioDto;
import ucab.dsw.dtos.SolicitudEstudioDto;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.servicio.usuario.ServicioAdministrador;

import javax.json.JsonObject;
import javax.ws.rs.core.Response;

public class ServicioAdministradorTest {

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
  public void addUsuarioAdministrador(){

    UsuarioDto usuarioDto = new UsuarioDto();
    usuarioDto.setNombreUsuario("pre@gmail.com");
    usuarioDto.setContrasena("12345");

    ServicioAdministrador servicioAdministrador = new ServicioAdministrador();

    Response resultado = servicioAdministrador.addUser(this.token, usuarioDto);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("usuario"));

  }

  @Test
  public void getUsuariosAdministradores(){

    ServicioAdministrador servicioAdministrador = new ServicioAdministrador();

    Response resultado = servicioAdministrador.getUsers(this.token);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("usuarios"));

  }

  @Test
  public void getUsuarioAdministradorTest(){

    ServicioAdministrador servicioAdministrador = new ServicioAdministrador();

    Response resultado = servicioAdministrador.getUserById(this.token, 6);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("id"));

  }

  @Test
  public void changePasswordTest(){

    ServicioAdministrador servicioAdministrador = new ServicioAdministrador();

    UsuarioDto usuarioDto = new UsuarioDto();
    usuarioDto.setNombreUsuario("pruebaañadirrr@gmail.com");
    usuarioDto.setContrasena("5678");

    Response resultado = servicioAdministrador.changePassword(this.token, 158, usuarioDto);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("estado"));

  }

  @Test
  public void desactivarUserTest(){

    ServicioAdministrador servicioAdministrador = new ServicioAdministrador();
    Response resultado = servicioAdministrador.disableUser(this.token, 156);

    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("usuario"));

  }

  @Test
  public void activarUserTest(){

    ServicioAdministrador servicioAdministrador = new ServicioAdministrador();
    Response resultado = servicioAdministrador.enableUser(this.token, 156);

    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("usuario"));

  }

  @Test
  public void getSolicitudesPendientesByAdminTest(){

    ServicioAdministrador servicioAdministrador = new ServicioAdministrador();

    Response resultado = servicioAdministrador.getSolicitudesPendientes(this.token, 4);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("solicitudes"));

  }

}
