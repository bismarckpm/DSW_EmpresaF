
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

    private String preguntaBuscar;
    
    @Test
    public void t1_addPreguntaTipoRango() {

        ServicioPregunta servicioPregunta = new ServicioPregunta();

        PreguntaDto preguntaDto = new PreguntaDto();

        preguntaDto.setTipoPregunta("rango");
        preguntaDto.setDescripcionPregunta("¿Cantidad de vasos de agua?");
        preguntaDto.setMin(0);
        preguntaDto.setMax(10);

        Response resultado = servicioPregunta.addPregunta(preguntaDto);
       
        System.out.println(resultado.getEntity().toString());
        Assert.assertEquals(200, resultado.getStatus());

    }

    @Test
    public void t2_addPreguntaTipoMultiple() {

        ServicioPregunta servicioPregunta = new ServicioPregunta();

        PreguntaDto preguntaDto = new PreguntaDto();

        preguntaDto.setTipoPregunta("multiple");
        preguntaDto.setDescripcionPregunta("¿Cantidad de vasos de agua?");

        List<OpcionDto> opcionesDto = new ArrayList<>();

        OpcionDto op1 = new OpcionDto();
        op1.setDescripcion("1");
        opcionesDto.add(op1);

        OpcionDto op2 = new OpcionDto();
        op2.setDescripcion("8");
        opcionesDto.add(op2);

        preguntaDto.setOpciones(opcionesDto);

        Response resultado = servicioPregunta.addPregunta(preguntaDto);

        System.out.println(resultado.getEntity().toString());
        Assert.assertEquals(200, resultado.getStatus());

    }

    @Test
    public void t3_addPreguntaTipoSimple() {

        ServicioPregunta servicioPregunta = new ServicioPregunta();

        PreguntaDto preguntaDto = new PreguntaDto();

        preguntaDto.setTipoPregunta("simple");
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

        System.out.println(resultado.getEntity().toString());
        Assert.assertEquals(200, resultado.getStatus());

    }

    @Test
    public void t4_getAllPreguntasTest() {

        ServicioPregunta servicioPregunta = new ServicioPregunta();

        Response resultado = servicioPregunta.getPreguntas();
        System.out.println(resultado.getEntity().toString());
        Assert.assertEquals(200, resultado.getStatus());

    }

    @Test
    public void t5_getPreguntaTest() {
        ServicioPregunta servicioPregunta = new ServicioPregunta();

        Response resultado = servicioPregunta.getPregunta(44);
        System.out.println(resultado.getEntity().toString());
        Assert.assertEquals(200, resultado.getStatus());

    }
}
