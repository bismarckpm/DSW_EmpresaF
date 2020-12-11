import org.junit.Assert;
import org.junit.Test;
import ucab.dsw.dtos.EstudioDto;
import ucab.dsw.dtos.SolicitudEstudioDto;
import ucab.dsw.servicio.usuario.ServicioAdministrador;

import javax.ws.rs.core.Response;

public class ServicioAdministradorTest {

  @Test
  public void getSolicitudesPendientesByAdmin(){
    ServicioAdministrador servicioAdministrador = new ServicioAdministrador();

    Response resultado = servicioAdministrador.getSolicitudesPendientes(82);
    Assert.assertEquals(resultado.getStatus(), 200);
  }

  @Test
  public void asignarEstudioASolicitud() throws Exception {
    ServicioAdministrador servicioAdministrador = new ServicioAdministrador();

    EstudioDto estudioDto = new EstudioDto(1);
    SolicitudEstudioDto solicitudEstudioDto = new SolicitudEstudioDto();
    solicitudEstudioDto.setEstudio(estudioDto);

    Response resultado = servicioAdministrador.asignarEstudioASolicitud(54,solicitudEstudioDto);

    Assert.assertEquals(resultado.getStatus(), 200);

  }
}
