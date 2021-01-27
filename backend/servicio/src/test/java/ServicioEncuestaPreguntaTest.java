
import java.util.ArrayList;
import java.util.List;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import ucab.dsw.dtos.EncuestaDto;
import ucab.dsw.dtos.PreguntaDto;
import ucab.dsw.servicio.encuesta.ServicioEncuestaPregunta;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ServicioEncuestaPreguntaTest {

    @Test
    public void addPreguntasToEncuestaTest() throws Exception {

        ServicioEncuestaPregunta servicioEncuestaPregunta;
        servicioEncuestaPregunta = new ServicioEncuestaPregunta();

        EncuestaDto encuestaDto = new EncuestaDto();
        encuestaDto.setId(9);

        List<PreguntaDto> preguntas = new ArrayList<>();

        PreguntaDto preguntaUno = new PreguntaDto(24);

        preguntas.add(preguntaUno);

        PreguntaDto preguntaDto = new PreguntaDto();
        preguntaDto.setPreguntas(preguntas);

        Response resultado = servicioEncuestaPregunta.addPreguntaToEncuesta(encuestaDto.getId(), preguntaDto);
        JsonObject respuesta = (JsonObject) resultado.getEntity();

        Assert.assertNotNull(respuesta.get("idUltPreguntaEncuesta"));

    }


    @Test
    public void getPreguntasByEncuestaTest() throws Exception {

        ServicioEncuestaPregunta servicioEncuestaPregunta = new ServicioEncuestaPregunta();

        EncuestaDto encuestaDto = new EncuestaDto();
        encuestaDto.setId(9);

        Response resultado = servicioEncuestaPregunta.getPreguntas(encuestaDto.getId());
        JsonObject respuesta = (JsonObject) resultado.getEntity();

        Assert.assertNotNull(respuesta.get("data"));

    }


    @Test
  public void getPreguntasAgregablesTest(){

      ServicioEncuestaPregunta servicioEncuestaPregunta = new ServicioEncuestaPregunta();

      Response resultado = servicioEncuestaPregunta.getPreguntasAgregables(9);
      JsonObject respuesta = (JsonObject) resultado.getEntity();

      Assert.assertNotNull(respuesta.get("preguntas"));

    }

}
