import org.junit.Assert;
import org.junit.Test;
import ucab.dsw.dtos.EncuestaDto;
import ucab.dsw.dtos.EstudioDto;
import ucab.dsw.dtos.PreguntaDto;
import ucab.dsw.dtos.SubcategoriaDto;
import ucab.dsw.servicio.estudio.ServicioEstudio;

import javax.ws.rs.core.Response;
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

    List<PreguntaDto> preguntas = null;
    PreguntaDto preguntaDto = new PreguntaDto();
    preguntaDto.setDescripcionPregunta("Descripcion");
    preguntaDto.setTipoPregunta("Desarrollo");
    preguntaDto.setMax(0);
    preguntaDto.setMin(0);

    preguntas.add(preguntaDto);

    estudioDto.setPreguntas(preguntas);

    Response resultado = servicioEstudio.addEstudio(12, estudioDto);
    Assert.assertEquals(resultado.getStatus(), 200);
  }
}
