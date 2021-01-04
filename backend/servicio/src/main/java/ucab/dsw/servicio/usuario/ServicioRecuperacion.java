package ucab.dsw.servicio.usuario;

import ucab.dsw.directorioactivo.DirectorioActivo;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.servicio.AplicacionBase;
import ucab.dsw.servicio.recuperacion.RecuperacionEmail;
import javax.json.Json;
import javax.json.JsonObject;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.SecureRandom;
import java.util.Random;

/**
 * Clase para gestionar la recuperacion de contraseña
 *
 */
@Path( "/recuperacion" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioRecuperacion extends AplicacionBase {


  /**
   * Metodo para agregar un cliente. Accedido mediante /recuperacion/recuperarcontrasena con el
   * metodo POST
   *
   * @param usuario DTO del usuario
   * @return JSON success: {estado, code}; error: {mensaje, estado,
   * code}
   */
  @POST
  @Path("/recuperarcontrasena")
  public Response recuperacionContrasena(UsuarioDto usuario){

     RecuperacionEmail recuperacionEmail = new RecuperacionEmail();
     JsonObject data = null;

    String[] symbols = {"0", "1", "-", "*", "%", "$", "a", "b", "c"};
    int length = 10;
    Random random;
    String password;

    try {

      random = SecureRandom.getInstanceStrong();
      StringBuilder sb = new StringBuilder(length);

      for (int i = 0; i < length; i++){
        int indexRandom = random.nextInt ( symbols.length );
        sb.append( symbols[indexRandom] );
      }

      password = sb.toString();
      System.out.println(password);
      usuario.setContrasena(password);

      DirectorioActivo directorioActivo = new DirectorioActivo();
      directorioActivo.changePassword(usuario);

      recuperacionEmail.enviarMail(usuario.getNombreUsuario(), "Recuperacion de contraseña", "Su nueva contraseña es: " + password);

      data = Json.createObjectBuilder()
        .add("estado", "success")
        .add("code", 200)
        .build();

    }catch (NamingException ex){
      data = Json.createObjectBuilder()
        .add("estado", "error")
        .add("code", 400)
        .add("mensaje", "El usuario no existe")
        .build();
    }
    catch (Exception ex){
      data = Json.createObjectBuilder()
        .add("estado", "error")
        .add("code", 400)
        .build();
    }

    System.out.println(data);
    return Response.ok().entity(data).build();

  }
}
