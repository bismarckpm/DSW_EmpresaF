import org.junit.Assert;
import org.junit.Test;
import ucab.dsw.dtos.EncuestaDto;
import ucab.dsw.dtos.EstudioDto;
import ucab.dsw.servicio.estudio.ServicioEstudio;

import javax.ws.rs.core.Response;

public class ServicioEstudioTest {

  @Test
  public void addEstudioTest() throws Exception {
    ServicioEstudio servicioEstudio = new ServicioEstudio();

    EstudioDto estudioDto = new EstudioDto();
    EncuestaDto encuestaDto = new EncuestaDto(1);

    estudioDto.setEncuesta(encuestaDto);

    Response resultado = servicioEstudio.addEstudio(estudioDto);
    Assert.assertEquals(resultado.getStatus(), 200);
  }
}
