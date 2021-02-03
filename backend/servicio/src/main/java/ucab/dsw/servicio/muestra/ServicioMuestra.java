package ucab.dsw.servicio.muestra;

import ucab.dsw.accesodatos.*;
import ucab.dsw.dtos.EncuestadoDto;
import ucab.dsw.dtos.MuestraDto;
import ucab.dsw.entidades.Encuestado;
import ucab.dsw.entidades.Muestra;
import ucab.dsw.entidades.SolicitudEstudio;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.logica.comando.muestra.ComandoAddMuestra;
import ucab.dsw.logica.comando.muestra.ComandoAddMuestraManual;
import ucab.dsw.logica.comando.muestra.ComandoGetMuestra;
import ucab.dsw.logica.comando.muestra.ComandoGetUsuarioAgregable;
import ucab.dsw.logica.exepcionhandler.ManejadorExcepcion;
import ucab.dsw.logica.fabrica.Fabrica;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Clase para gestionar la muestra
 *
 */
@Path("/muestra")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ServicioMuestra {

  /**
   * Metodo para añadir usuarios encuestados a la muestra de una solicitud de estudio de forma automatica
   * al crearse una solicitud de estudio
   *
   * @param encuestados lista de encuestados
   * @param solicitudEstudio Solicitud de estudio
   *
   */
  public void addMuestra(List<Encuestado> encuestados, SolicitudEstudio solicitudEstudio){

    try {

      ComandoAddMuestra comandoAddMuestra = new ComandoAddMuestra(encuestados, solicitudEstudio);
      comandoAddMuestra.execute();

    }catch (Exception ex){
      ex.printStackTrace();
    }

  }

  /**
   * Metodo para agregar usuarios a la muestra de una soicitud de estudio
   * Accedido mediante /muestra/add/{idSolicitudEstudio} con el
   * metodo POST
   *
   * @param idSolicitudEstudio Id de la solicitud de estudio
   * @param muestraDto DTO de la muestra
   * @return JSON success: {code, estado, solicitudEstudio}; error: {estado,
   * code}
   */
  @POST
  @Path("/add/{idSolicitudEstudio}")
  public Response addManualMuestra(@PathParam("idSolicitudEstudio") long idSolicitudEstudio, MuestraDto muestraDto){

    try{

      ComandoAddMuestraManual comandoAddMuestraManual = Fabrica.crearComandoConAmbos(ComandoAddMuestraManual.class, idSolicitudEstudio, muestraDto);
      comandoAddMuestraManual.execute();

      return Response.ok().entity(comandoAddMuestraManual.getResultado()).build();

    }catch (Exception ex){

      String mensaje = "Ha ocurrido un error en el servidor";
      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return  Response.status(500).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 500)).build();

    }

  }


  /**
   * Metodo para obtener la muestra dada una solicitud de estudio
   * Accedido mediante /muestra/getmuestra/{solicitudId} con el
   * metodo GET
   *
   * @param solicitudId Id de la solicitud de estudio
   *
   * @return JSON success: {code, estado, encuestados}; error: {estado,
   * code}
   */
  @GET
  @Path("/getmuestra/{solicitudId}")
  public Response getMuestra(@PathParam("solicitudId") long solicitudId){

    try {

      ComandoGetMuestra comandoGetMuestra = Fabrica.crearComandoConId(ComandoGetMuestra.class, solicitudId);
      comandoGetMuestra.execute();

      return Response.ok().entity(comandoGetMuestra.getResultado()).build();

    }
    catch (Exception ex){

      String mensaje = "Ha ocurrido un error en el servidor";
      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return  Response.status(500).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 500)).build();

    }

  }

  /**
   * Metodo para Obtener los usuarios encuestados agregables a una muestra
   * Accedido mediante /muestra/usuarioagregable/solicitudId con el metodo GET
   *
   * @param solicitudId id de la solicitud de estudio
   *
   * @return JSON success: {encuestados, code, estado}; error: {mensaje, estado,
   * code}
   *
   */
  @GET
  @Path("/usuarioagregable/{solicitudId}")
  public Response getUsuarioAgregable(@PathParam("solicitudId") long solicitudId){


    try{

      ComandoGetUsuarioAgregable comandoGetUsuarioAgregable = Fabrica.crearComandoConId(ComandoGetUsuarioAgregable.class, solicitudId);
      comandoGetUsuarioAgregable.execute();

      return Response.ok().entity(comandoGetUsuarioAgregable.getResultado()).build();

    }catch (Exception ex){

      String mensaje = "Ha ocurrido un error en el servidor";
      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return  Response.status(500).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 500)).build();

    }

  }

}
