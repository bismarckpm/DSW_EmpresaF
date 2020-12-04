package ucab.dsw.servicio.autenticacion;

import io.jsonwebtoken.Claims;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.autenticacion.Autenticacion;
import ucab.dsw.servicio.AplicacionBase;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path( "/auth" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioAutenticacion extends AplicacionBase {

  @POST
  @Path("/login")
  public String login(UsuarioDto usuarioDto){
    String resultado;

    Autenticacion autenticacion = new Autenticacion();
    resultado = autenticacion.generateToken(usuarioDto);

    System.out.println(resultado);
    return resultado;
  }

  @GET
  @Path("/decode")
  public Claims decodeToken(String token){
    Autenticacion autenticacion = new Autenticacion();
    Claims resultado = autenticacion.decode(token);
    System.out.println(resultado);
    return resultado;
  }
}
