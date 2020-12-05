import org.junit.Assert;
import org.junit.Test;
import ucab.dsw.dtos.*;
import ucab.dsw.servicio.usuario.ServicioCliente;
import ucab.dsw.servicio.usuario.ServicioEncuestado;

import javax.ws.rs.core.Response;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ServicioUsuariosTest {

  @Test
  public void addUserClienteTest(){
    ServicioCliente servicio = new ServicioCliente();
    UsuarioDto usuarioDto = new UsuarioDto();
    ClienteDto clienteDto = new ClienteDto();

    clienteDto.setNombre("pruebarepetida");
    usuarioDto.setClienteDto(clienteDto);
    usuarioDto.setNombreUsuario("pruebajjuser");
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
      encuestadoDto.setPrimerNombre("PruebaAdicionEncuestado");
      encuestadoDto.setPrimerApellido("Prueba");
      encuestadoDto.setDireccionComplemento("Alta vista");

      DateFormat fecha = new SimpleDateFormat("dd-MM-yyyy");
      encuestadoDto.setFechaNacimiento(fecha.parse("06-11-2020"));

      encuestadoDto.setGenero("masculino");
      encuestadoDto.setEstadoCivil("Casado");
      encuestadoDto.setOcupacion("Carpintero");

      ParroquiaDto parroquiaDto = new ParroquiaDto(1);
      encuestadoDto.setParroquia(parroquiaDto);

      NivelEstudioDto nivelEstudioDto = new NivelEstudioDto(1);
      encuestadoDto.setNivelEstudio(nivelEstudioDto);

      usuarioDto.setEncuestadoDto(encuestadoDto);
      usuarioDto.setNombreUsuario("pruebaEncuestadoAgains");
      usuarioDto.setContrasena("12345");

      Response resultado = servicio.addUser(usuarioDto);

      Assert.assertEquals(resultado.getStatus(), 200);
    }
    catch (Exception ex){
      ex.getMessage();
    }
  }
}
