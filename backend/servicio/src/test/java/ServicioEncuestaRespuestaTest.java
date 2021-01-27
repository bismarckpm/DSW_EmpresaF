
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import ucab.dsw.dtos.*;
import ucab.dsw.servicio.encuesta.ServicioEncuestaRespuesta;

import javax.json.JsonObject;
import javax.ws.rs.core.Response;

public class ServicioEncuestaRespuestaTest {

    @Test
    public void addRespuestaTest() throws Exception {

        BaseRespuestaDto baseRespuestaDto = new BaseRespuestaDto();

        List<ArrayRespuestaDto> arrayRespuestaDtos = new ArrayList<>();

        ArrayRespuestaDto arrayRespuestaDto = new ArrayRespuestaDto();

        PreguntaDto preguntaDto = new PreguntaDto(24);
        String descripcion = "Si me gusta";
        Integer rango = 0;
        EncuestadoDto encuestadoDto = new EncuestadoDto(64);

        List<OpcionDto> opcionesDto = null;

        arrayRespuestaDto.setPregunta(preguntaDto);
        arrayRespuestaDto.setDescripcion(descripcion);
        arrayRespuestaDto.setRango(rango);
        arrayRespuestaDto.setEncuestado(encuestadoDto);
        arrayRespuestaDto.setOpciones(opcionesDto);

        arrayRespuestaDtos.add(arrayRespuestaDto);

        baseRespuestaDto.setRespuestas(arrayRespuestaDtos);

        ServicioEncuestaRespuesta servicioEncuestaRespuesta = new ServicioEncuestaRespuesta();

        Response resultado = servicioEncuestaRespuesta.addRespuesta(12, baseRespuestaDto);
        JsonObject respuesta = (JsonObject) resultado.getEntity();

        Assert.assertNotNull(respuesta.get("ultPreguntaRespuesta"));

    }

    @Test
    public void getRespuestasByEncuestaTest(){

      ServicioEncuestaRespuesta servicioEncuestaRespuesta = new ServicioEncuestaRespuesta();

      Response resultado = servicioEncuestaRespuesta.getRespuestaByEncuesta(12);
      JsonObject respuesta = (JsonObject) resultado.getEntity();

      Assert.assertNotNull(respuesta.get("respuestas"));

    }

}
