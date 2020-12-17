
import java.util.ArrayList;
import java.util.List;
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
    public void t1_addPreguntasToEncuesta() throws Exception {
        ServicioEncuestaPregunta servicioEncuestaPregunta;
        servicioEncuestaPregunta = new ServicioEncuestaPregunta();

        EncuestaDto encuestaDto = new EncuestaDto();
        encuestaDto.setId(2);

        List<PreguntaDto> preguntas = new ArrayList<>();

        PreguntaDto preguntaUno = new PreguntaDto(49);
        PreguntaDto preguntaDos = new PreguntaDto(51);
        PreguntaDto preguntaTres = new PreguntaDto(50);

        preguntas.add(preguntaUno);
        preguntas.add(preguntaDos);
        preguntas.add(preguntaTres);

        Response resultado = servicioEncuestaPregunta
                .addPreguntasToEncuesta(encuestaDto.getId(), preguntas);

        System.out.println(resultado.getEntity().toString());

        Assert.assertEquals(200, resultado.getStatus());
    }
    
    @Test
    public void t1_addPreguntaToEncuesta() throws Exception {
        ServicioEncuestaPregunta servicioEncuestaPregunta;
        servicioEncuestaPregunta = new ServicioEncuestaPregunta();

        EncuestaDto encuestaDto = new EncuestaDto();
        encuestaDto.setId(2);

        PreguntaDto preguntaUno = new PreguntaDto(1);

        Response resultado = servicioEncuestaPregunta
                .addPreguntaToEncuesta(encuestaDto.getId(), preguntaUno);

        System.out.println(resultado.getEntity().toString());

        Assert.assertEquals(200, resultado.getStatus());
    }

    @Test
    public void t2_getPreguntas() throws Exception {
        ServicioEncuestaPregunta servicioEncuestaPregunta;
        servicioEncuestaPregunta = new ServicioEncuestaPregunta();

        EncuestaDto encuestaDto = new EncuestaDto();
        encuestaDto.setId(2);

        Response resultado = servicioEncuestaPregunta.getPreguntas(encuestaDto.getId());

        System.out.println(resultado.getEntity().toString());

        Assert.assertEquals(200, resultado.getStatus());
    }

}
