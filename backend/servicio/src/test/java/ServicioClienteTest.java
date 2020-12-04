import org.junit.Assert;
import org.junit.Test;
import ucab.dsw.dtos.ClienteDto;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.servicio.usuario.ServicioCliente;

import javax.ws.rs.core.Response;

public class ServicioClienteTest {

  @Test
  public void addUserClientTest(){
    ServicioCliente servicio = new ServicioCliente();
    UsuarioDto usuarioDto = new UsuarioDto();
    ClienteDto clienteDto = new ClienteDto();

    clienteDto.setNombre("PruebaJhonsonje");
    usuarioDto.setClienteDto(clienteDto);
    usuarioDto.setNombreUsuario("prueba254235d");
    usuarioDto.setContrasena("12345");

    UsuarioDto resultado = servicio.addUser(usuarioDto);

    Assert.assertNotEquals(resultado, 0);
  }
}
