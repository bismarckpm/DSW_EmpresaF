import org.junit.Test;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.servicio.usuario.ServicioRecuperacion;

public class RecuperacionContrasenaTest {

  @Test
  public void recuperacionContrasena(){
    ServicioRecuperacion servicioRecuperacion = new ServicioRecuperacion();

    UsuarioDto usuarioDto = new UsuarioDto();
    usuarioDto.setNombreUsuario("PMCRr79fiom@gmail.com");

    servicioRecuperacion.recuperacionContrasena(usuarioDto);
  }
}
