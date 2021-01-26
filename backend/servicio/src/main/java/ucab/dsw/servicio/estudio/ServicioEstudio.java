package ucab.dsw.servicio.estudio;

import ucab.dsw.accesodatos.*;
import ucab.dsw.dtos.EstudioDto;
import ucab.dsw.dtos.OpcionDto;
import ucab.dsw.dtos.PreguntaDto;
import ucab.dsw.entidades.*;
import ucab.dsw.excepciones.LimiteExcepcion;
import ucab.dsw.logica.comando.categoria.ComandoGetCategorias;
import ucab.dsw.logica.comando.estudio.ComandoAddEstudio;
import ucab.dsw.logica.comando.estudio.ComandoGetEstudio;
import ucab.dsw.logica.comando.estudio.ComandoGetEstudios;
import ucab.dsw.logica.exepcionhandler.ManejadorExcepcion;
import ucab.dsw.logica.fabrica.Fabrica;
import ucab.dsw.servicio.AplicacionBase;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Clase para gestionar los estudios
 *
 */
@Path( "/estudio" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioEstudio extends AplicacionBase {

  /**
   * Metodo para agregar un estudio y asignarlo a una solicitud Accedido mediante estudio/add/{solicitudEstudioId} con el
   * metodo POST
   *
   * @param estudioDto DTO del estudio
   * @return JSON success: {estudioId, estado, code}; error: {mensaje, estado,
   * code}
   */
  @POST
  @Path("/add/{solicitudEstudioId}")
  public Response addEstudio (@PathParam("solicitudEstudioId") long solicitudId, EstudioDto estudioDto){

    try {

      ComandoAddEstudio comandoAddEstudio = Fabrica.crearComandoConAmbos(ComandoAddEstudio.class, solicitudId, estudioDto);
      comandoAddEstudio.execute();

      return Response.ok().entity(comandoAddEstudio.getResultado()).build();

    }catch (javax.persistence.PersistenceException ex){

      String mensaje = "Este estudio y/o encuesta ya se encuentra registrado";
      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return  Response.status(400).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 400)).build();

    }catch (LimiteExcepcion ex){

      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return  Response.status(400).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), ex.getMessage(), "error", 400)).build();

    }catch (Exception ex){

      String mensaje = "Ha ocurrido un error en el servidor";
      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return  Response.status(500).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 500)).build();

    }

  }

  /**
   * Metodo para obtener un estudio Accedido mediante estudio/getestudio/{estudioId} con el
   * metodo GET
   *
   * @param id Id del estudio
   * @return JSON success: {id, nombreEstudio, encuestaId, estado, code}; error: {estado,
   * code}
   */
  @GET
  @Path("/getestudio/{estudioId}")
  public Response getEstudioById(@PathParam("estudioId") long id){

    try{

      ComandoGetEstudio comandoGetEstudio = Fabrica.crearComandoConId(ComandoGetEstudio.class, id);
      comandoGetEstudio.execute();

      return Response.ok().entity(comandoGetEstudio.getResultado()).build();

    }catch (Exception ex){

      String mensaje = "Ha ocurrido un error en el servidor";
      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return  Response.status(500).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 500)).build();

    }

  }


  /**
   * Metodo para obtener todos los estudios Accedido mediante estudio/getall con el
   * metodo GET
   *
   *
   * @return JSON success: {estudios, estado, code}; error: {estado,
   * code}
   */
  @GET
  @Path("/getall")
  public Response getEstudios(){

    try {

      ComandoGetEstudios comandoGetEstudios = Fabrica.crear(ComandoGetEstudios.class);
      comandoGetEstudios.execute();

      return Response.ok().entity(comandoGetEstudios.getResultado()).build();

    } catch (Exception ex) {

      String mensaje = "Ha ocurrido un error en el servidor";
      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return Response.status(500).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 500)).build();

    }

  }

}
