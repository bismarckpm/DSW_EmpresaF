import org.junit.Assert;
import org.junit.Test;
import ucab.dsw.servicio.usuario.ServicioAnalista;

import javax.ws.rs.core.Response;

public class ServicioAnalistaTest {

  @Test
  public void getSolicitudesPendientesByAnalista(){
    ServicioAnalista servicioAnalista = new ServicioAnalista();

    Response resultado = servicioAnalista.getSolicitudesPendientes(88);
    Assert.assertEquals(resultado.getStatus(), 200);
  }

  @Test
  public void activarEstudioByAnalistaTest(){

    ServicioAnalista servicio = new ServicioAnalista();

    Response resultado = servicio.activarEstudio(41);
    Assert.assertEquals(resultado.getStatus(), 200);

  }
}
