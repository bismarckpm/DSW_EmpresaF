package ucab.dsw.servicio.usuario;

import ucab.dsw.dtos.UsuarioDto;

import javax.ws.rs.core.Response;

public interface IServicioUsuario {

  public Response addUser(UsuarioDto usuarioDto);

  public Response getUsers();
}
