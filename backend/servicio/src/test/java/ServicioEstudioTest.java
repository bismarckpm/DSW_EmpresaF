import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.autenticacion.Autenticacion;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.servicio.categoria.ServicioCategoria;
import ucab.dsw.servicio.estudio.ServicioEstudio;

import javax.json.JsonObject;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

public class ServicioEstudioTest {

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
  public void addEstudioTest() throws Exception {

    ServicioEstudio servicioEstudio = new ServicioEstudio();

    EstudioDto estudioDto = new EstudioDto();

    estudioDto.setNombreEstudio("Estudio prueba Con Tken");

    EncuestaDto encuestaDto = new EncuestaDto();
    encuestaDto.setNombreEncuesta("Encuesta prueba con token");

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

    Response resultado = servicioEstudio.addEstudio(this.token, 189, estudioDto);
    JsonObject respuesta = (JsonObject) resultado.getEntity();
    System.out.println(respuesta);

    Assert.assertNotNull(respuesta.get("estudioId"));

  }

  @Test
  public void getEstudioByIdTest() {

    ServicioEstudio servicioEstudio = new ServicioEstudio();

    Response resultado = servicioEstudio.getEstudioById(this.token, 27);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("nombreEstudio"));

  }

  @Test
  public void getAllEstudiosTest(){

    ServicioEstudio servicioEstudio = new ServicioEstudio();

    Response resultado = servicioEstudio.getEstudios(this.token);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotEquals(respuesta.get("estudios").toString().length(), 2);

  }

}
