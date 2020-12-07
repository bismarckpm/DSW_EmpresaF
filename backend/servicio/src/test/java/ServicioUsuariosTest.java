import org.junit.Assert;
import org.junit.Test;
import ucab.dsw.dtos.*;
import ucab.dsw.servicio.usuario.ServicioCliente;
import ucab.dsw.servicio.usuario.ServicioEncuestado;

import javax.ws.rs.core.Response;

public class ServicioUsuariosTest {

  @Test
  public void addUserClienteTest(){
    ServicioCliente servicio = new ServicioCliente();
    UsuarioDto usuarioDto = new UsuarioDto();
    ClienteDto clienteDto = new ClienteDto();

    clienteDto.setNombre("pruebarepetida2");
    usuarioDto.setClienteDto(clienteDto);
    usuarioDto.setNombreUsuario("pruebajjo");
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

      ParroquiaDto parroquiaDto = new ParroquiaDto(1);
      encuestadoDto.setParroquia(parroquiaDto);

      NivelEstudioDto nivelEstudioDto = new NivelEstudioDto(1);
      encuestadoDto.setNivelEstudio(nivelEstudioDto);

      usuarioDto.setEncuestadoDto(encuestadoDto);
      usuarioDto.setNombreUsuario("pruerolle00");
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
}
