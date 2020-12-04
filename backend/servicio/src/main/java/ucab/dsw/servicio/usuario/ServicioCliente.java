package ucab.dsw.servicio.usuario;

import ucab.dsw.accesodatos.DaoCliente;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.directorioactivo.DirectorioActivo;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.entidades.Cliente;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.servicio.AplicacionBase;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path( "/client" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioCliente extends AplicacionBase implements IServicioUsuario {

@POST
@Path("/add")
  public UsuarioDto addUser(UsuarioDto usuarioDto){
    UsuarioDto resultado = new UsuarioDto();
    try {

      DaoCliente daoCliente = new DaoCliente();
      Cliente cliente = new Cliente();
      cliente.setNombre(usuarioDto.getClienteDto().getNombre());
      Cliente resul = daoCliente.insert(cliente);
      Cliente clienteAgregado = new Cliente(resul.get_id());

      DaoUsuario daoUsuario = new DaoUsuario();
      Usuario usuario = new Usuario();
      usuario.set_nombreUsuario(usuarioDto.getNombreUsuario());
      usuario.set_estado("Activo");

      usuario.set_cliente(clienteAgregado);
      Usuario usuarioAgregado = daoUsuario.insert(usuario);

      DirectorioActivo ldap = new DirectorioActivo();
      ldap.addEntryToLdap( usuarioDto );

      resultado.setId(usuarioAgregado.get_id());

    } catch (Exception ex) {
      ex.getMessage();
    }

    return  resultado;

}
}
