import org.junit.Assert;
import org.junit.Test;
import ucab.dsw.servicio.usuario.ServicioAdministrador;

import javax.ws.rs.core.Response;

public class ServicioAdministradorTest {

  @Test
  public void getSolicitudesPendientesByAdmin(){
    ServicioAdministrador servicioAdministrador = new ServicioAdministrador();

    Response resultado = servicioAdministrador.getSolicitudesPendientes(82);
    Assert.assertEquals(resultado.getStatus(), 200);
  }
}
