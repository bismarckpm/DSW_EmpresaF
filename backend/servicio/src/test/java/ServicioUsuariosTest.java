import org.junit.Assert;
import org.junit.Test;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.Telefono;
import ucab.dsw.servicio.muestra.ServicioMuestra;
import ucab.dsw.servicio.usuario.ServicioAnalista;
import ucab.dsw.servicio.usuario.ServicioCliente;
import ucab.dsw.servicio.usuario.ServicioEncuestado;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class ServicioUsuariosTest {

  @Test
  public void addUserClienteTest(){

    ServicioCliente servicio = new ServicioCliente();
    UsuarioDto usuarioDto = new UsuarioDto();
    ClienteDto clienteDto = new ClienteDto();

    clienteDto.setNombre("Prueba Diaz");
    usuarioDto.setClienteDto(clienteDto);
    usuarioDto.setNombreUsuario("pruebarepetida245@gmail.com");
    usuarioDto.setContrasena("12345");

    Response resultado = servicio.addUser("222", usuarioDto);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("usuario"));

  }

  @Test
  public void updateUserClienteTest() throws Exception {

    ServicioCliente servicio = new ServicioCliente();
    UsuarioDto usuarioDto = new UsuarioDto();

    usuarioDto.setNombreUsuario("pruebarepetida245@gmail.com");
    usuarioDto.setContrasena("5678");

    ClienteDto clienteDto = new ClienteDto(133);
    clienteDto.setNombre("Prueba Peña");

    usuarioDto.setClienteDto(clienteDto);

    Response resultado = servicio.updateUser(166, usuarioDto);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("usuario"));

  }

  @Test
  public void addUserEncuestadoTest(){

    try {

      ServicioEncuestado servicio = new ServicioEncuestado();
      UsuarioDto usuarioDto = new UsuarioDto();
      EncuestadoDto encuestadoDto = new EncuestadoDto();

      encuestadoDto.setNumeroIdentificacion("15267");
      encuestadoDto.setPrimerNombre("PruebaRol");
      encuestadoDto.setPrimerApellido("Prueba");
      encuestadoDto.setSegundoApellido("Diaz");
      encuestadoDto.setDireccionComplemento("Alta vista");

      encuestadoDto.setFechaNacimiento("06-11-2020");

      encuestadoDto.setGenero("masculino");
      encuestadoDto.setEstadoCivil("Casado");
      encuestadoDto.setOcupacion("Carpintero");

      List<Telefono> telefonos = new ArrayList<>();
      Telefono telefono = new Telefono("0414", "1548889");
      Telefono telefono2 = new Telefono("0414", "2669090");
      telefonos.add(telefono);
      telefonos.add(telefono2);
      encuestadoDto.setTelefonos(telefonos);

      ParroquiaDto parroquiaDto = new ParroquiaDto(1);
      encuestadoDto.setParroquia(parroquiaDto);

      NivelEstudioDto nivelEstudioDto = new NivelEstudioDto(6);
      encuestadoDto.setNivelEstudio(nivelEstudioDto);

      NivelSocioeconomicoDto nivelSocioeconomico = new NivelSocioeconomicoDto(4);
      encuestadoDto.setNivelSocioeconomico(nivelSocioeconomico);

      usuarioDto.setEncuestadoDto(encuestadoDto);
      usuarioDto.setNombreUsuario("cdyy0p");
      usuarioDto.setContrasena("12345");

      Response resultado = servicio.addUser("222", usuarioDto);
      JsonObject respuesta = (JsonObject) resultado.getEntity();

      Assert.assertNotNull(respuesta.get("usuario"));

    }
    catch (Exception ex){
      ex.getMessage();
    }

  }

  @Test
  public void updateUserEncuestadoTest(){

    try {

      ServicioEncuestado servicio = new ServicioEncuestado();
      UsuarioDto usuarioDto = new UsuarioDto();
      EncuestadoDto encuestadoDto = new EncuestadoDto();

      encuestadoDto.setNumeroIdentificacion("1526788");
      encuestadoDto.setPrimerNombre("PruebaRoler");
      encuestadoDto.setPrimerApellido("Pruebapp");
      encuestadoDto.setEstadoCivil("soltero");
      encuestadoDto.setGenero("masculino");
      encuestadoDto.setOcupacion("Ingeniero");

      ParroquiaDto parroquiaDto = new ParroquiaDto(1);
      encuestadoDto.setParroquia(parroquiaDto);

      usuarioDto.setEncuestadoDto(encuestadoDto);
      usuarioDto.setNombreUsuario("maria25@gmail.com");
      usuarioDto.setContrasena("12345");

      Response resultado = servicio.updateUser(155, usuarioDto);
      JsonObject respuesta = (JsonObject) resultado.getEntity();

      Assert.assertNotNull(respuesta.get("usuario"));

    }
    catch (Exception ex){
      ex.getMessage();
    }

  }

  @Test
  public void getClientsTest(){

    ServicioCliente servicio = new ServicioCliente();

    Response resultado = servicio.getUsers();
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("usuarios"));

  }

  @Test
  public void getClientTest(){

    ServicioCliente servicio = new ServicioCliente();

    Response resultado = servicio.getUserById(166);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("id"));

  }

  @Test
  public void getEncuestadosTest(){

    ServicioEncuestado servicio = new ServicioEncuestado();

    Response resultado = servicio.getUsers();
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("usuarios"));

  }

  @Test
  public void getEncuestadoTest(){

    ServicioEncuestado servicio = new ServicioEncuestado();

    Response resultado = servicio.getUserById(154);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("idUsuario"));

  }

  @Test
  public void desactivarUserTest(){

    ServicioAnalista servicioAnalista= new ServicioAnalista();
    Response resultado = servicioAnalista.disableUser(155);

    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("usuario"));

  }

  @Test
  public void activarUserTest(){

    ServicioAnalista servicioAnalista= new ServicioAnalista();
    Response resultado = servicioAnalista.enableUser(155);

    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("usuario"));

  }

  @Test
  public void getEstudiosRealizablesByEncuestadoTest(){

    ServicioEncuestado servicioEncuestado = new ServicioEncuestado();

    Response resultado = servicioEncuestado.getEstudiosRealizables(155);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotEquals(respuesta.get("estudios").toString().length(), 2);

  }

  @Test
  public void getSolicitudesByClienteTest(){

    ServicioCliente servicioCliente = new ServicioCliente();

    Response resultado = servicioCliente.getSolicitudes(153);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotEquals(respuesta.get("solicitudes").toString().length(), 2);

  }

}
