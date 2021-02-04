package ucab.dsw.servicio.usuario;

import ucab.dsw.dtos.UsuarioDto;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

public interface IServicioEmpleado {

  public Response addUser(@HeaderParam("authorization") String token, UsuarioDto usuarioDto);

  public Response getUsers();

  public Response getSolicitudesPendientes(@PathParam("id") long id);
}
