import org.junit.Assert;
import org.junit.Test;
import ucab.dsw.dtos.EstudioDto;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.servicio.usuario.ServicioAdministrador;
import ucab.dsw.servicio.usuario.ServicioAnalista;

import javax.json.JsonObject;
import javax.ws.rs.core.Response;

public class ServicioAnalistaTest {

  @Test
  public void addUserTest(){

    UsuarioDto usuarioDto = new UsuarioDto();
    usuarioDto.setNombreUsuario("prueba202@gmail.com");
    usuarioDto.setContrasena("1234");

    ServicioAnalista servicioAnalista = new ServicioAnalista();

    Response resultado = servicioAnalista.addUser(usuarioDto);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("usuario"));

  }

  @Test
  public void getUsersAnalistasTest(){

    ServicioAnalista servicioAnalista = new ServicioAnalista();

    Response resultado = servicioAnalista.getUsers();
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("usuarios"));

  }

  @Test
  public void getUsuarioAnalistaTest(){

    ServicioAnalista servicioAnalista = new ServicioAnalista();

    Response resultado = servicioAnalista.getUserById(2);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("id"));

  }

  @Test
  public void changePasswordTest(){

    ServicioAnalista servicioAnalista = new ServicioAnalista();

    UsuarioDto usuarioDto = new UsuarioDto();
    usuarioDto.setNombreUsuario("new22@gmail.com");
    usuarioDto.setContrasena("1234");

    Response resultado = servicioAnalista.changePassword(161, usuarioDto);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("estado"));

  }

  @Test
  public void desactivarUserTest(){

    ServicioAnalista servicioAnalista= new ServicioAnalista();
    Response resultado = servicioAnalista.disableUser(161);

    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("usuario"));

  }

  @Test
  public void activarUserTest(){

    ServicioAnalista servicioAnalista = new ServicioAnalista();
    Response resultado = servicioAnalista.enableUser(161);

    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("usuario"));

  }

  @Test
  public void getSolicitudesPendientesByAnalista(){

    ServicioAnalista servicioAnalista = new ServicioAnalista();

    Response resultado = servicioAnalista.getSolicitudesPendientes(2);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("solicitudes"));

  }


  @Test
  public void activarEstudioByAnalistaTest(){

    ServicioAnalista servicio = new ServicioAnalista();

    Response resultado = servicio.activarEstudio(154);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("idSolicitud"));

  }

  @Test
  public void obtenerEstudiosByAnalistaTest(){

    ServicioAnalista servicio = new ServicioAnalista();

    Response resultado = servicio.obtenerEstudiosByAnalista(2);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotEquals(respuesta.get("estudios").toString().length(), 2);

  }

  @Test
  public void finalizarEstudioTest(){

    ServicioAnalista servicio = new ServicioAnalista();

    EstudioDto estudioDto = new EstudioDto();
    estudioDto.setResultado("Estudiesaso");

    Response resultado = servicio.finalizarEstudio(21, estudioDto);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("estudioCulminado"));

  }

}
