import org.junit.Assert;
import org.junit.Test;
import ucab.dsw.dtos.EncuestaDto;
import ucab.dsw.dtos.EstudioDto;
import ucab.dsw.dtos.PreguntaDto;
import ucab.dsw.dtos.SubcategoriaDto;
import ucab.dsw.servicio.categoria.ServicioCategoria;
import ucab.dsw.servicio.estudio.ServicioEstudio;

import javax.json.JsonObject;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

public class ServicioEstudioTest {

  @Test
  public void addEstudioTest() throws Exception {

    ServicioEstudio servicioEstudio = new ServicioEstudio();

    EstudioDto estudioDto = new EstudioDto();

    estudioDto.setNombreEstudio("Estudio prueba");

    EncuestaDto encuestaDto = new EncuestaDto();
    encuestaDto.setNombreEncuesta("Encuesta prueba");

    SubcategoriaDto subcategoriaDto = new SubcategoriaDto(30);
    encuestaDto.setSubcategoria(subcategoriaDto);

    estudioDto.setEncuesta(encuestaDto);

    PreguntaDto preguntaDto = new PreguntaDto();
    preguntaDto.setDescripcionPregunta("Descripcion");
    preguntaDto.setTipoPregunta("desarrollo");
    preguntaDto.setMax(0);
    preguntaDto.setMin(0);
    preguntaDto.setOpciones(null);

    PreguntaDto preguntaDto2 = new PreguntaDto(21);

    List<PreguntaDto> preguntas =  Arrays.asList(preguntaDto, preguntaDto2);

    estudioDto.setPreguntas(preguntas);

    Response resultado = servicioEstudio.addEstudio(178, estudioDto);
    JsonObject respuesta = (JsonObject) resultado.getEntity();
    System.out.println(respuesta);

    Assert.assertNotNull(respuesta.get("estudioId"));

  }

  @Test
  public void getEstudioByIdTest() {

    ServicioEstudio servicioEstudio = new ServicioEstudio();

    Response resultado = servicioEstudio.getEstudioById(21);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("nombreEstudio"));

  }

  @Test
  public void getAllEstudiosTest(){

    ServicioEstudio servicioEstudio = new ServicioEstudio();

    Response resultado = servicioEstudio.getEstudios();
    JsonObject respuesta = (JsonObject) resultado.getEntity();
    System.out.println(respuesta);

    Assert.assertNotNull(respuesta.get("estudios"));

  }
}
