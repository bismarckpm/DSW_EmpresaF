import org.junit.Assert;
import org.junit.Test;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.Telefono;
import ucab.dsw.servicio.usuario.ServicioCliente;
import ucab.dsw.servicio.usuario.ServicioEncuestado;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class ServicioUsuariosTest {

  @Test
  public void addUserClienteTest(){
    ServicioCliente servicio = new ServicioCliente();
    UsuarioDto usuarioDto = new UsuarioDto();
    ClienteDto clienteDto = new ClienteDto();

    clienteDto.setNombre("pruebarepetida245");
    usuarioDto.setClienteDto(clienteDto);
    usuarioDto.setNombreUsuario("pruebajjo245");
    usuarioDto.setContrasena("12345");

    Response resultado = servicio.addUser(usuarioDto);

    Assert.assertEquals(resultado.getStatus(), 200);
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

      NivelEstudioDto nivelEstudioDto = new NivelEstudioDto(1);
      encuestadoDto.setNivelEstudio(nivelEstudioDto);

      NivelSocioeconomicoDto nivelSocioeconomico = new NivelSocioeconomicoDto(1);
      encuestadoDto.setNivelSocioeconomico(nivelSocioeconomico);

      usuarioDto.setEncuestadoDto(encuestadoDto);
      usuarioDto.setNombreUsuario("cdyy0p");
      usuarioDto.setContrasena("12345");

      Response resultado = servicio.addUser(usuarioDto);

      Assert.assertEquals(resultado.getStatus(), 200);
    }
    catch (Exception ex){
      ex.getMessage();
    }
  }

  @Test
  public void getClientTest(){

    ServicioCliente servicio = new ServicioCliente();
    Response resultado = servicio.getUsers();

    Assert.assertEquals(resultado.getStatus(), 200);
  }

  @Test
  public void getEncuestadoTest(){

    ServicioEncuestado servicio = new ServicioEncuestado();
    Response resultado = servicio.getUsers();

    Assert.assertEquals(resultado.getStatus(), 200);
  }

  @Test
  public void getEstudiosRealizablesByEncuestadoTest(){

    ServicioEncuestado servicioEncuestado = new ServicioEncuestado();
    Response resultado = servicioEncuestado.getEstudiosRealizables(92);

    Assert.assertEquals(resultado.getStatus(), 200);
  }

  @Test
  public void getMuestra(){

    ServicioEncuestado servicioEncuestado = new ServicioEncuestado();
    Response resultado = servicioEncuestado.getMuestra(78);

    Assert.assertEquals(resultado.getStatus(), 200);
  }
}
