import org.junit.Assert;
import org.junit.Test;
import ucab.dsw.dtos.EncuestadoDto;
import ucab.dsw.dtos.MuestraDto;
import ucab.dsw.servicio.muestra.ServicioMuestra;

import javax.ws.rs.core.Response;

public class ServicioMuestraTest {

  @Test
  public void AddManualMuestraTest() throws Exception {

    ServicioMuestra servicioMuestra = new ServicioMuestra();

    MuestraDto muestraDto = new MuestraDto();

    EncuestadoDto encuestadoDto = new EncuestadoDto(52);

    muestraDto.setEncuestado(encuestadoDto);

    Response resultado = servicioMuestra.addManualMuestra(133, muestraDto);

    Assert.assertEquals(resultado.getStatus(), 200);
  }
}
