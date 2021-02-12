import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.autenticacion.Autenticacion;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.SolicitudEstudio;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.servicio.solicitudEstudio.ServicioSolicitudEstudio;
import ucab.dsw.servicio.usuario.ServicioAdministrador;

import javax.json.JsonObject;
import javax.ws.rs.core.Response;

public class ServicioSolicitudEstudioTest {

  private String token;

  @Before
  public void generateToken() throws PruebaExcepcion {

    Autenticacion autenticacion = new Autenticacion();

    UsuarioDto usuarioDto = new UsuarioDto();
    usuarioDto.setId((long) 4);
    usuarioDto.setNombreUsuario("analista1@gmail.com");
    usuarioDto.setContrasena("12345");

    DaoUsuario daoUsuario = new DaoUsuario();
    Usuario usuario = daoUsuario.find((long) 4, Usuario.class);

    this.token = autenticacion.generateToken(usuarioDto);
    usuario.set_token(this.token);
    daoUsuario.update(usuario);

  }

  @Test
  public void addSolicitudEstudioTest() throws Exception {

    ServicioSolicitudEstudio servicio = new ServicioSolicitudEstudio();
    SolicitudEstudioDto solicitudEstudioDto = new SolicitudEstudioDto();

    solicitudEstudioDto.setEdadInicial(14);
    solicitudEstudioDto.setEdadfinal(16);
    solicitudEstudioDto.setGenero("masculino");

    UsuarioDto usuariocliente = new UsuarioDto(153);
    solicitudEstudioDto.setCliente(usuariocliente);

    ParroquiaDto parroquia = new ParroquiaDto(1);
    solicitudEstudioDto.setParroquia(parroquia);

    SubcategoriaDto subcategoria = new SubcategoriaDto(30);
    solicitudEstudioDto.setSubcategoria(subcategoria);

    NivelSocioeconomicoDto nivelSocioeconomico = new NivelSocioeconomicoDto(4);
    solicitudEstudioDto.setNivelSocioeconomico(nivelSocioeconomico);

    Response resultado = servicio.addSolicitud(this.token, solicitudEstudioDto);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("solicitud"));

  }


  @Test
  public void getSolicitudesTest(){

    ServicioSolicitudEstudio servicio = new ServicioSolicitudEstudio();

    Response resultado = servicio.getSolicitudes(this.token);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotEquals(respuesta.get("solicitudes").toString().length(), 2);
  }

}


