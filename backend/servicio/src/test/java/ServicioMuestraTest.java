import org.junit.Assert;
import org.junit.Test;
import ucab.dsw.dtos.EncuestadoDto;
import ucab.dsw.dtos.MuestraDto;
import ucab.dsw.servicio.muestra.ServicioMuestra;

import javax.json.JsonObject;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class ServicioMuestraTest {

  @Test
  public void AddManualMuestraTest() throws Exception {

    ServicioMuestra servicioMuestra = new ServicioMuestra();

    MuestraDto muestraDto = new MuestraDto();

    List<EncuestadoDto> encuestadosDto = new ArrayList<>();

    EncuestadoDto encuestadoDto = new EncuestadoDto(69);
    encuestadosDto.add(encuestadoDto);

    muestraDto.setEncuestados(encuestadosDto);

    Response resultado = servicioMuestra.addManualMuestra(179, muestraDto);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("solicitudEstudio"));

  }

  @Test
  public void getMuestra(){

    ServicioMuestra servicioMuestra = new ServicioMuestra();

    Response resultado = servicioMuestra.getMuestra(179);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotEquals(respuesta.get("encuestados").toString().length(), 2);

  }

  @Test
  public void getUsuariosAgregablesMuestraTest(){

    ServicioMuestra servicioMuestra = new ServicioMuestra();

    Response resultado = servicioMuestra.getUsuarioAgregable(179);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("encuestados"));

  }

}
