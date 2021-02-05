import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.autenticacion.Autenticacion;
import ucab.dsw.dtos.EstudioDto;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.servicio.usuario.ServicioAdministrador;
import ucab.dsw.servicio.usuario.ServicioAnalista;

import javax.json.JsonObject;
import javax.ws.rs.core.Response;

public class ServicioAnalistaTest {

  private String token;

  @Before
  public void generateToken() throws PruebaExcepcion {

    Autenticacion autenticacion = new Autenticacion();

    UsuarioDto usuarioDto = new UsuarioDto();
    usuarioDto.setId((long) 1);
    usuarioDto.setNombreUsuario("analista1@gmail.com");
    usuarioDto.setContrasena("12345");

    DaoUsuario daoUsuario = new DaoUsuario();
    Usuario usuario = daoUsuario.find((long) 1, Usuario.class);

    this.token = autenticacion.generateToken(usuarioDto);
    usuario.set_token(this.token);
    daoUsuario.update(usuario);

  }

  @Test
  public void addUserTest(){

    UsuarioDto usuarioDto = new UsuarioDto();
    usuarioDto.setNombreUsuario("prueba202token@gmail.com");
    usuarioDto.setContrasena("1234");

    ServicioAnalista servicioAnalista = new ServicioAnalista();

    Response resultado = servicioAnalista.addUser(this.token, usuarioDto);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("usuario"));

  }

  @Test
  public void getUsersAnalistasTest(){

    ServicioAnalista servicioAnalista = new ServicioAnalista();

    Response resultado = servicioAnalista.getUsers(this.token);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotEquals(respuesta.get("usuarios").toString().length(), 2);

  }


  @Test
  public void changePasswordTest(){

    ServicioAnalista servicioAnalista = new ServicioAnalista();

    UsuarioDto usuarioDto = new UsuarioDto();
    usuarioDto.setNombreUsuario("prueba202token@gmail.com");
    usuarioDto.setContrasena("12345");

    Response resultado = servicioAnalista.changePassword(this.token, 177, usuarioDto);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("estado"));

  }

  @Test
  public void desactivarUserTest(){

    ServicioAnalista servicioAnalista= new ServicioAnalista();
    Response resultado = servicioAnalista.disableUser(this.token,177);

    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("usuario"));

  }

  @Test
  public void activarUserTest(){

    ServicioAnalista servicioAnalista = new ServicioAnalista();
    Response resultado = servicioAnalista.enableUser(this.token, 177);

    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("usuario"));

  }

  @Test
  public void getSolicitudesPendientesByAnalista(){

    ServicioAnalista servicioAnalista = new ServicioAnalista();

    Response resultado = servicioAnalista.getSolicitudesPendientes(this.token,2);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotEquals(respuesta.get("solicitudes").toString().length(), 2);

  }


  @Test
  public void activarEstudioByAnalistaTest(){

    ServicioAnalista servicio = new ServicioAnalista();

    Response resultado = servicio.activarEstudio(this.token, 154);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("idSolicitud"));

  }

  @Test
  public void obtenerEstudiosByAnalistaTest(){

    ServicioAnalista servicio = new ServicioAnalista();

    Response resultado = servicio.obtenerEstudiosByAnalista(this.token,2);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotEquals(respuesta.get("estudios").toString().length(), 2);

  }

  @Test
  public void finalizarEstudioTest(){

    ServicioAnalista servicio = new ServicioAnalista();

    EstudioDto estudioDto = new EstudioDto();
    estudioDto.setResultado("Estudiesaso");

    Response resultado = servicio.finalizarEstudio(this.token,21, estudioDto);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("estudioCulminado"));

  }

}
