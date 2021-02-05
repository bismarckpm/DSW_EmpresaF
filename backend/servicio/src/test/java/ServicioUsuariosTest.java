import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.autenticacion.Autenticacion;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.Telefono;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.excepciones.PruebaExcepcion;
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

  private String tokenAdmin;
  private String token;
  private String tokenEncuestado;

  @Before
  public void generateTokenAdmin() throws PruebaExcepcion {

    Autenticacion autenticacion = new Autenticacion();

    UsuarioDto usuarioDto = new UsuarioDto();
    usuarioDto.setId((long) 1);
    usuarioDto.setNombreUsuario("administrador1@gmail.com");
    usuarioDto.setContrasena("12345");

    DaoUsuario daoUsuario = new DaoUsuario();
    Usuario usuario = daoUsuario.find((long) 1, Usuario.class);

    this.tokenAdmin = autenticacion.generateToken(usuarioDto);
    usuario.set_token(this.tokenAdmin);
    daoUsuario.update(usuario);

  }

  @Before
  public void generateTokenEncuestado() throws PruebaExcepcion {

    Autenticacion autenticacion = new Autenticacion();

    UsuarioDto usuarioDto = new UsuarioDto();
    usuarioDto.setId((long) 183);
    usuarioDto.setNombreUsuario("token@gmail.com");
    usuarioDto.setContrasena("12345");

    DaoUsuario daoUsuario = new DaoUsuario();
    Usuario usuario = daoUsuario.find((long) 183, Usuario.class);

    this.tokenEncuestado = autenticacion.generateToken(usuarioDto);
    usuario.set_token(this.tokenEncuestado);
    daoUsuario.update(usuario);

  }

  @Before
  public void generateToken() throws PruebaExcepcion {

    Autenticacion autenticacion = new Autenticacion();

    UsuarioDto usuarioDto = new UsuarioDto();
    usuarioDto.setId((long) 153);
    usuarioDto.setNombreUsuario("lisbethdiaz@gmail.com");
    usuarioDto.setContrasena("12345");

    DaoUsuario daoUsuario = new DaoUsuario();
    Usuario usuario = daoUsuario.find((long) 153, Usuario.class);

    this.token = autenticacion.generateToken(usuarioDto);
    usuario.set_token(this.token);
    daoUsuario.update(usuario);

  }

  @Test
  public void addUserClienteTest(){

    ServicioCliente servicio = new ServicioCliente();
    UsuarioDto usuarioDto = new UsuarioDto();
    ClienteDto clienteDto = new ClienteDto();

    clienteDto.setNombre("Prueba Diaz Token");
    usuarioDto.setClienteDto(clienteDto);
    usuarioDto.setNombreUsuario("pruebarepetidatoken@gmail.com");
    usuarioDto.setContrasena("12345");

    Response resultado = servicio.addUser(this.tokenAdmin, usuarioDto);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("usuario"));

  }

  @Test
  public void updateUserClienteTest() throws Exception {

    ServicioCliente servicio = new ServicioCliente();
    UsuarioDto usuarioDto = new UsuarioDto();

    usuarioDto.setNombreUsuario("pruebarepetidatoken@gmail.com");
    usuarioDto.setContrasena("5678");

    ClienteDto clienteDto = new ClienteDto(133);
    clienteDto.setNombre("Prueba Token Token");

    usuarioDto.setClienteDto(clienteDto);

    Response resultado = servicio.updateUser(this.token, 166, usuarioDto);
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
      encuestadoDto.setPrimerNombre("Prueba Token");
      encuestadoDto.setPrimerApellido("Prueba");
      encuestadoDto.setSegundoApellido("Diaz Token");
      encuestadoDto.setDireccionComplemento("Alta vista");

      encuestadoDto.setFechaNacimiento("06-12-1998");

      encuestadoDto.setGenero("masculino");
      encuestadoDto.setEstadoCivil("Casado");
      encuestadoDto.setOcupacion("Tokenero");

      List<Telefono> telefonos = new ArrayList<>();
      Telefono telefono = new Telefono("0414", "1548859");
      Telefono telefono2 = new Telefono("0414", "2669590");
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
      usuarioDto.setNombreUsuario("token@gmail.com");
      usuarioDto.setContrasena("12345");

      Response resultado = servicio.addUser(usuarioDto);
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
      encuestadoDto.setPrimerNombre("PruebaTokenEn");
      encuestadoDto.setPrimerApellido("PruebaToken");
      encuestadoDto.setEstadoCivil("soltero");
      encuestadoDto.setGenero("masculino");
      encuestadoDto.setOcupacion("Ingeniero");

      ParroquiaDto parroquiaDto = new ParroquiaDto(1);
      encuestadoDto.setParroquia(parroquiaDto);

      usuarioDto.setEncuestadoDto(encuestadoDto);
      usuarioDto.setNombreUsuario("token@gmail.com");
      usuarioDto.setContrasena("12345");

      Response resultado = servicio.updateUser(this.tokenEncuestado,183, usuarioDto);
      JsonObject respuesta = (JsonObject) resultado.getEntity();
      System.out.println(respuesta);

      Assert.assertNotNull(respuesta.get("usuario"));

    }
    catch (Exception ex){
      ex.getMessage();
    }

  }

  @Test
  public void getClientsTest(){

    ServicioCliente servicio = new ServicioCliente();

    Response resultado = servicio.getUsers(this.tokenAdmin);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotEquals(respuesta.get("usuarios").toString().length(), 2);

  }

  @Test
  public void getClientTest(){

    ServicioCliente servicio = new ServicioCliente();

    Response resultado = servicio.getUserById(this.token,180);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("id"));

  }

  @Test
  public void getEncuestadosTest(){

    ServicioEncuestado servicio = new ServicioEncuestado();

    Response resultado = servicio.getUsers(this.tokenAdmin);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotEquals(respuesta.get("usuarios").toString().length(), 2);

  }

  @Test
  public void getEncuestadoTest(){

    ServicioEncuestado servicio = new ServicioEncuestado();

    Response resultado = servicio.getUserById(this.tokenAdmin, 154);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("idUsuario"));

  }

  @Test
  public void desactivarUserTest(){

    ServicioAnalista servicioAnalista= new ServicioAnalista();
    Response resultado = servicioAnalista.disableUser(this.tokenAdmin, 180);

    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("usuario"));

  }

  @Test
  public void activarUserTest(){

    ServicioAnalista servicioAnalista= new ServicioAnalista();
    Response resultado = servicioAnalista.enableUser(this.tokenAdmin, 180);

    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("usuario"));

  }

  @Test
  public void getEstudiosRealizablesByEncuestadoTest(){

    ServicioEncuestado servicioEncuestado = new ServicioEncuestado();

    Response resultado = servicioEncuestado.getEstudiosRealizables(this.tokenEncuestado, 152);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotEquals(respuesta.get("estudios").toString().length(), 2);

  }

  @Test
  public void getSolicitudesByClienteTest(){

    ServicioCliente servicioCliente = new ServicioCliente();

    Response resultado = servicioCliente.getSolicitudes(this.token,153);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotEquals(respuesta.get("solicitudes").toString().length(), 2);

  }

}
