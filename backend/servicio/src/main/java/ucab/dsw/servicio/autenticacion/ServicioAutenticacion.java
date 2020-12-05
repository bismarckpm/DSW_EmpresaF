package ucab.dsw.servicio.autenticacion;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.autenticacion.Autenticacion;
import ucab.dsw.servicio.AplicacionBase;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path( "/auth" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioAutenticacion extends AplicacionBase {

  @POST
  @Path("/login")
  public Response login(UsuarioDto usuarioDto){
    String resultado;
    JsonObject data;

    try {
      Autenticacion autenticacion = new Autenticacion();
      resultado = autenticacion.generateToken(usuarioDto);

      data = Json.createObjectBuilder()
        .add("token", resultado)
        .add("estado", "success")
        .add("code", 200)
        .build();
    }
    catch (NullPointerException ex){
      String mensaje = "Usuario y/o clave incorrecta";

      data = Json.createObjectBuilder()
        .add("error", mensaje)
        .add("estado", "error")
        .add("code", 400)
        .build();

      System.out.println(data);
      return Response.ok().entity(data).build();
    }

    System.out.println(data);
    return Response.ok().entity(data).build();

  }

  @GET
  @Path("/decode")
  public Response decodeToken(String token){
    Claims resultado;
    JsonObject data = null;
    try{

      Autenticacion autenticacion = new Autenticacion();
      resultado = autenticacion.decode(token);

    }catch (MalformedJwtException ex){
      String mensaje = "El token enviado es incorrecto";

      data= Json.createObjectBuilder().add("error", mensaje)
        .add("estado", "error")
        .add("code", 400)
        .build();

      System.out.println(data);
      return Response.ok().entity(data).build();

    }
    catch (ExpiredJwtException ex){
      String mensaje = "El token enviado ha expirado";

      data= Json.createObjectBuilder().add("error", mensaje)
        .add("estado", "error")
        .add("code", 400)
        .build();

      System.out.println(data);
      return Response.ok().entity(data).build();

    }
    catch (SignatureException ex){
      String mensaje = "El token no es valido y podria ser peligroso";

      data= Json.createObjectBuilder().add("error", mensaje)
        .add("estado", "error")
        .add("code", 400)
        .build();

      System.out.println(data);
      return Response.ok().entity(data).build();
    }

    System.out.println(resultado);
    return Response.ok().entity(resultado).build();
  }
}
