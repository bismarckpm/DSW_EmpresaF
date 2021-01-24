import org.junit.Assert;
import org.junit.Test;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.SolicitudEstudio;
import ucab.dsw.servicio.solicitudEstudio.ServicioSolicitudEstudio;
import ucab.dsw.servicio.usuario.ServicioAdministrador;

import javax.json.JsonObject;
import javax.ws.rs.core.Response;

public class ServicioSolicitudEstudioTest {

  @Test
  public void addSolicitudEstudioTest() throws Exception {

    ServicioSolicitudEstudio servicio = new ServicioSolicitudEstudio();
    SolicitudEstudioDto solicitudEstudioDto = new SolicitudEstudioDto();

    solicitudEstudioDto.setEdadInicial(15);
    solicitudEstudioDto.setEdadfinal(25);
    solicitudEstudioDto.setGenero("masculino");

    UsuarioDto usuariocliente = new UsuarioDto(153);
    solicitudEstudioDto.setCliente(usuariocliente);

    ParroquiaDto parroquia = new ParroquiaDto(1);
    solicitudEstudioDto.setParroquia(parroquia);

    SubcategoriaDto subcategoria = new SubcategoriaDto(30);
    solicitudEstudioDto.setSubcategoria(subcategoria);

    NivelSocioeconomicoDto nivelSocioeconomico = new NivelSocioeconomicoDto(9);
    solicitudEstudioDto.setNivelSocioeconomico(nivelSocioeconomico);

    Response resultado = servicio.addSolicitud(solicitudEstudioDto);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertEquals(respuesta.get("solicitud"), 0);

  }


  @Test
  public void getSolicitudesTest(){

    ServicioSolicitudEstudio servicio = new ServicioSolicitudEstudio();

    Response resultado = servicio.getSolicitudes();
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("solicitudes"));
  }
}


