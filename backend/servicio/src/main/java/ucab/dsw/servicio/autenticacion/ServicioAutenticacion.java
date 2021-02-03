package ucab.dsw.servicio.autenticacion;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.directorioactivo.DirectorioActivo;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.autenticacion.Autenticacion;
import ucab.dsw.entidades.SolicitudEstudio;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.excepciones.EstadoExcepcion;
import ucab.dsw.logica.comando.autenticacion.ComandoDecode;
import ucab.dsw.logica.comando.autenticacion.ComandoLogin;
import ucab.dsw.logica.exepcionhandler.ManejadorExcepcion;
import ucab.dsw.logica.fabrica.Fabrica;
import ucab.dsw.servicio.AplicacionBase;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Clase para gestionar la autenticacion de usuario
 *
 */
@Path( "/auth" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioAutenticacion extends AplicacionBase {

  /**
   * Metodo para acceder al sistema con credenciales y generar token /auth/login con el
   * metodo POST
   *
   * @param usuarioDto DTO del usuario
   * @return JSON success: {token, id, rol, estado, code}; error: {mensaje, estado,
   * code}
   */
  @POST
  @Path("/login")
  public Response login(UsuarioDto usuarioDto){

    try {

      ComandoLogin comandoLogin = Fabrica.crearComandoConDto(ComandoLogin.class, usuarioDto);
      comandoLogin.execute();

      return Response.ok().entity(comandoLogin.getResultado()).build();

    }catch (EstadoExcepcion ex){

      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return  Response.status(400).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), ex.getMessage(), "error", 400)).build();

    } catch (NullPointerException ex){

      String mensaje = "Usuario y/o clave incorrecta";

      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return  Response.status(400).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 400)).build();

    }catch (Exception ex){

      String mensaje = "Ha ocurrido un error en el servidor";
      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return  Response.status(500).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 500)).build();

    }

  }

  /**
   * Metodo para decodificar el token de un usuario autenticado /auth/decode con el
   * metodo GET
   *
   * @param token token del usuario autenticado
   * @return JSON success: {resultado}; error: {mensaje, estado,
   * code}
   */
  @GET
  @Path("/decode")
  public Response decodeToken(String token){

    try{

      ComandoDecode comandoDecode = new ComandoDecode(token);
      comandoDecode.execute();

      return Response.ok().entity(comandoDecode.getResultado()).build();

    }catch (MalformedJwtException ex){

      String mensaje = "El token enviado es incorrecto";

      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return  Response.status(400).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 400)).build();

    }
    catch (ExpiredJwtException ex){

      String mensaje = "El token enviado ha expirado";

      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return  Response.status(400).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 400)).build();

    }
    catch (SignatureException ex){
      String mensaje = "El token no es valido y podria ser peligroso";

      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return  Response.status(400).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 400)).build();

    }
    catch (Exception ex){

      String mensaje = "Ha ocurrido un error en el servidor";
      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return  Response.status(500).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 500)).build();

    }

  }
}
