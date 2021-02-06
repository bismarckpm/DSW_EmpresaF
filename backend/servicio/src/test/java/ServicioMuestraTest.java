import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.autenticacion.Autenticacion;
import ucab.dsw.dtos.EncuestadoDto;
import ucab.dsw.dtos.MuestraDto;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.servicio.muestra.ServicioMuestra;

import javax.json.JsonObject;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class ServicioMuestraTest {

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
  public void AddManualMuestraTest() throws Exception {

    ServicioMuestra servicioMuestra = new ServicioMuestra();

    MuestraDto muestraDto = new MuestraDto();

    List<EncuestadoDto> encuestadosDto = new ArrayList<>();

    EncuestadoDto encuestadoDto = new EncuestadoDto(64);
    encuestadosDto.add(encuestadoDto);

    muestraDto.setEncuestados(encuestadosDto);

    Response resultado = servicioMuestra.addManualMuestra(this.token, 190, muestraDto);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("solicitudEstudio"));

  }

  @Test
  public void getMuestra(){

    ServicioMuestra servicioMuestra = new ServicioMuestra();

    Response resultado = servicioMuestra.getMuestra(this.token, 190);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("encuestados"));

  }

  @Test
  public void getUsuariosAgregablesMuestraTest(){

    ServicioMuestra servicioMuestra = new ServicioMuestra();

    Response resultado = servicioMuestra.getUsuarioAgregable(this.token, 190);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("encuestados"));

  }

}
