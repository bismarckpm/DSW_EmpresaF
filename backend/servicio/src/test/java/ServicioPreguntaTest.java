
import java.util.ArrayList;
import java.util.List;
import javax.json.JsonObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Response;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.autenticacion.Autenticacion;
import ucab.dsw.dtos.OpcionDto;
import ucab.dsw.dtos.PreguntaDto;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.servicio.pregunta.ServicioPregunta;

public class ServicioPreguntaTest {


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

        Response resultado = servicioPregunta.addPregunta(this.token, preguntaDto);
        JsonObject respuesta = (JsonObject) resultado.getEntity();

        Assert.assertNotNull(respuesta.get("data"));

    }

    @Test
    public void getPreguntasTest() {

        ServicioPregunta servicioPregunta = new ServicioPregunta();

        Response resultado = servicioPregunta.getPreguntas(this.token);
        JsonObject respuesta = (JsonObject) resultado.getEntity();

        Assert.assertNotEquals(respuesta.get("data").toString().length(), 2);

    }

    @Test
    public void getPreguntasSugeridas(){

      ServicioPregunta servicioPregunta = new ServicioPregunta();

      Response resultado = servicioPregunta.getPreguntasSugeridas(this.token, 179);
      JsonObject respuesta = (JsonObject) resultado.getEntity();

      Assert.assertNotEquals(respuesta.get("preguntas").toString().length(), 2);

    }

}
