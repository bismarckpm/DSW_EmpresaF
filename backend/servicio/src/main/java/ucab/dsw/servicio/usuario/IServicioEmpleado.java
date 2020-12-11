package ucab.dsw.servicio.usuario;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

public interface IServicioEmpleado {

  public Response getSolicitudesPendientes(@PathParam("id") long id);
}
