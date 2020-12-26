
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import ucab.dsw.accesodatos.DaoOpcion;
import ucab.dsw.dtos.EncuestadoDto;
import ucab.dsw.dtos.OpcionDto;
import ucab.dsw.dtos.PreguntaEncuestaDto;
import ucab.dsw.dtos.RespuestaDto;
import ucab.dsw.entidades.PreguntaEncuesta;
import ucab.dsw.servicio.encuesta.ServicioEncuestaRespuesta;

public class ServicioRespuestaTest {

   /* @Test
    public void t1_addRespuesta() throws Exception {
        RespuestaDto respuestaDto = new RespuestaDto();

        List<OpcionDto> opcionesDto = new ArrayList<>();

        OpcionDto op1 = new OpcionDto();
        op1.setId(1);
        opcionesDto.add(op1);

        EncuestadoDto encuestadoDto = new EncuestadoDto();
        encuestadoDto.setId(1);

        long idEncuesta = 2;
        long idPregunta = 3;

        PreguntaEncuestaDto preguntaEncuestaDto = new PreguntaEncuestaDto();
        preguntaEncuestaDto.setId(22);

        respuestaDto.setDescripcion("");
        respuestaDto.setOpciones(opcionesDto);
        respuestaDto.setEncuestado(encuestadoDto);
        respuestaDto.setPreguntaEncuesta(preguntaEncuestaDto);

        ServicioEncuestaRespuesta servicio = new ServicioEncuestaRespuesta();
        servicio.addRespuesta(idEncuesta);

    }*/

}
