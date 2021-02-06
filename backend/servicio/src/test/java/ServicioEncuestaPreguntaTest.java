
import java.util.ArrayList;
import java.util.List;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.autenticacion.Autenticacion;
import ucab.dsw.dtos.EncuestaDto;
import ucab.dsw.dtos.PreguntaDto;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.servicio.encuesta.ServicioEncuestaPregunta;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ServicioEncuestaPreguntaTest {

  private String token;

  @Before
  public void generateToken() throws PruebaExcepcion {

    Autenticacion autenticacion = new Autenticacion();

    UsuarioDto usuarioDto = new UsuarioDto();
    usuarioDto.setId((long) 4);
    usuarioDto.setNombreUsuario("administrador1@gmail.com");
    usuarioDto.setContrasena("12345");

    DaoUsuario daoUsuario = new DaoUsuario();
    Usuario usuario = daoUsuario.find((long) 4, Usuario.class);

    this.token = autenticacion.generateToken(usuarioDto);
    usuario.set_token(this.token);
    daoUsuario.update(usuario);

  }

    @Test
    public void addPreguntasToEncuestaTest() throws Exception {

        ServicioEncuestaPregunta servicioEncuestaPregunta;
        servicioEncuestaPregunta = new ServicioEncuestaPregunta();

        EncuestaDto encuestaDto = new EncuestaDto();
        encuestaDto.setId(19);

        List<PreguntaDto> preguntas = new ArrayList<>();

        PreguntaDto preguntaUno = new PreguntaDto(24);

        preguntas.add(preguntaUno);

        PreguntaDto preguntaDto = new PreguntaDto();
        preguntaDto.setPreguntas(preguntas);

        Response resultado = servicioEncuestaPregunta.addPreguntaToEncuesta(this.token, encuestaDto.getId(), preguntaDto);
        JsonObject respuesta = (JsonObject) resultado.getEntity();

        Assert.assertNotNull(respuesta.get("idUltPreguntaEncuesta"));

    }


    @Test
    public void getPreguntasByEncuestaTest() throws Exception {

        ServicioEncuestaPregunta servicioEncuestaPregunta = new ServicioEncuestaPregunta();

        EncuestaDto encuestaDto = new EncuestaDto();
        encuestaDto.setId(19);

        Response resultado = servicioEncuestaPregunta.getPreguntas(this.token, encuestaDto.getId());
        JsonObject respuesta = (JsonObject) resultado.getEntity();

        Assert.assertNotNull(respuesta.get("data"));

    }


    @Test
  public void getPreguntasAgregablesTest(){

      ServicioEncuestaPregunta servicioEncuestaPregunta = new ServicioEncuestaPregunta();

      Response resultado = servicioEncuestaPregunta.getPreguntasAgregables(this.token, 19);
      JsonObject respuesta = (JsonObject) resultado.getEntity();

      Assert.assertNotNull(respuesta.get("preguntas"));

    }

}
