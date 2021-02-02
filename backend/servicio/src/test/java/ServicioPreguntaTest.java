
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.MultivaluedMap;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.Response;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.http.util.EntityUtils;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import ucab.dsw.dtos.OpcionDto;
import ucab.dsw.dtos.PreguntaDto;
import ucab.dsw.servicio.pregunta.ServicioPregunta;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ServicioPreguntaTest {


    @Test
    public void addPreguntaTest() {

        ServicioPregunta servicioPregunta = new ServicioPregunta();

        PreguntaDto preguntaDto = new PreguntaDto();

        preguntaDto.setTipoPregunta("simple");
        preguntaDto.setMin(0);
        preguntaDto.setMax(0);
        preguntaDto.setDescripcionPregunta("¿Equipo de futbol?");

        List<OpcionDto> opcionesDto = new ArrayList<>();

        OpcionDto op1 = new OpcionDto();
        op1.setDescripcion("Barcelona");
        opcionesDto.add(op1);

        OpcionDto op2 = new OpcionDto();
        op2.setDescripcion("Madrid");
        opcionesDto.add(op2);

        preguntaDto.setOpciones(opcionesDto);

        Response resultado = servicioPregunta.addPregunta(preguntaDto);
        JsonObject respuesta = (JsonObject) resultado.getEntity();

        Assert.assertNotNull(respuesta.get("data"));

    }

    @Test
    public void getPreguntasTest() {

        ServicioPregunta servicioPregunta = new ServicioPregunta();

        Response resultado = servicioPregunta.getPreguntas();
        JsonObject respuesta = (JsonObject) resultado.getEntity();

        Assert.assertNotEquals(respuesta.get("data").toString().length(), 2);

    }

    @Test
    public void getPreguntasSugeridas(){

      ServicioPregunta servicioPregunta = new ServicioPregunta();

      Response resultado = servicioPregunta.getPreguntasSugeridas(179);
      JsonObject respuesta = (JsonObject) resultado.getEntity();

      Assert.assertNotEquals(respuesta.get("preguntas").toString().length(), 2);

    }

}
