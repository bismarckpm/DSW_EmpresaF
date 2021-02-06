
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.autenticacion.Autenticacion;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.servicio.encuesta.ServicioEncuestaRespuesta;

import javax.json.JsonObject;
import javax.ws.rs.core.Response;

public class ServicioEncuestaRespuestaTest {

  private String token;

  @Before
  public void generateToken() throws PruebaExcepcion {

    Autenticacion autenticacion = new Autenticacion();

    UsuarioDto usuarioDto = new UsuarioDto();
    usuarioDto.setId((long) 154);
    usuarioDto.setNombreUsuario("hileryguedezpp25@gmail.com");
    usuarioDto.setContrasena("12345");

    DaoUsuario daoUsuario = new DaoUsuario();
    Usuario usuario = daoUsuario.find((long) 154, Usuario.class);

    this.token = autenticacion.generateToken(usuarioDto);
    usuario.set_token(this.token);
    daoUsuario.update(usuario);

  }

    @Test
    public void addRespuestaTest() throws Exception {

        BaseRespuestaDto baseRespuestaDto = new BaseRespuestaDto();

        List<ArrayRespuestaDto> arrayRespuestaDtos = new ArrayList<>();

        ArrayRespuestaDto arrayRespuestaDto = new ArrayRespuestaDto();

        PreguntaDto preguntaDto = new PreguntaDto(24);
        String descripcion = "todo good";
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

        Response resultado = servicioEncuestaRespuesta.addRespuesta(this.token, 19, baseRespuestaDto);
        JsonObject respuesta = (JsonObject) resultado.getEntity();

        Assert.assertNotNull(respuesta.get("ultPreguntaRespuesta"));

    }

    @Test
    public void getRespuestasByEncuestaTest(){

      ServicioEncuestaRespuesta servicioEncuestaRespuesta = new ServicioEncuestaRespuesta();

      Response resultado = servicioEncuestaRespuesta.getRespuestaByEncuesta(this.token, 19);
      JsonObject respuesta = (JsonObject) resultado.getEntity();

      Assert.assertNotNull(respuesta.get("respuestas"));

    }

}
