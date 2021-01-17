import org.junit.Assert;
import org.junit.Test;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.SolicitudEstudio;
import ucab.dsw.servicio.solicitudEstudio.ServicioSolicitudEstudio;
import ucab.dsw.servicio.usuario.ServicioAdministrador;

import javax.ws.rs.core.Response;

public class ServicioSolicitudEstudioTest {

  @Test
  public void addSolicitudEstudioTest() throws Exception {
    ServicioSolicitudEstudio servicio = new ServicioSolicitudEstudio();
    SolicitudEstudioDto solicitudEstudioDto = new SolicitudEstudioDto();

    solicitudEstudioDto.setEdadInicial(19);
    solicitudEstudioDto.setEdadfinal(35);
    solicitudEstudioDto.setGenero("femenino");

    UsuarioDto usuariocliente = new UsuarioDto(80);
    solicitudEstudioDto.setCliente(usuariocliente);

    ParroquiaDto parroquia = new ParroquiaDto(1);
    solicitudEstudioDto.setParroquia(parroquia);

    SubcategoriaDto subcategoria = new SubcategoriaDto(1);
    solicitudEstudioDto.setSubcategoria(subcategoria);

    NivelSocioeconomicoDto nivelSocioeconomico = new NivelSocioeconomicoDto(1);
    solicitudEstudioDto.setNivelSocioeconomico(nivelSocioeconomico);

    Response resultado = servicio.addSolicitud(solicitudEstudioDto);

    Assert.assertEquals(resultado.getStatus(), 200);
  }


  /*@Test
  public void getSolicitudByIdTest(){
    ServicioSolicitudEstudio servicio = new ServicioSolicitudEstudio();

    Response resultado = servicio.getSolicitudById(1);

    Assert.assertEquals(resultado.getStatus(), 200);
  }*/
}


