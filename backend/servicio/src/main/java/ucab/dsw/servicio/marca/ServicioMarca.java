package ucab.dsw.servicio.marca;

import ucab.dsw.dtos.MarcaDto;

import ucab.dsw.excepciones.ProblemaExcepcion;
import ucab.dsw.logica.comando.autenticacion.ComandoDecode;
import ucab.dsw.logica.comando.marca.*;
import ucab.dsw.logica.exepcionhandler.ManejadorExcepcion;
import ucab.dsw.logica.fabrica.Fabrica;
import ucab.dsw.servicio.AplicacionBase;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Clase para gestionar las marcas
 *
 */
@Path( "/marca" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioMarca extends AplicacionBase {

  /**
   * Metodo para agregar una marca Accedido mediante /marca/add con el
   * metodo POST
   *
   * @param marcaDto DTO de la marca
   * @return JSON success: {marca, estado, code}; error: {mensaje, estado,
   * code}
   */
  @POST
  @Path("/add")
  public Response addMarca (@HeaderParam("authorization") String token, MarcaDto marcaDto){

    try {

      ComandoDecode comandoDecode = Fabrica.crearComandoSeguridad(ComandoDecode.class, token);
      comandoDecode.execute();

      ComandoAddMarca comandoAddMarca = Fabrica.crearComandoConDto(ComandoAddMarca.class,marcaDto);
      comandoAddMarca.execute();

      return  Response.ok().entity(comandoAddMarca.getResultado()).build();

    }
    catch (ProblemaExcepcion ex){

      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return  Response.status(400).entity(manejadorExcepcion.getMensajeError(ex.getMensaje_soporte(), ex.getMensaje(), "error", 400)).build();

    }
    catch (Exception ex){

      String mensaje = "Ha ocurrido un error en el servidor";
      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return  Response.status(500).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 500)).build();

    }


  }

  /**
   * Metodo para actualizar una marca Accedido mediante /marca/update/{marcaId} con el
   * metodo PUT
   *
   * @param id Id de la marca
   * @param marcaDto DTO de la marca
   * @return JSON success: {marca, estado, code}; error: {mensaje, estado,
   * code}
   */
  @PUT
  @Path("/update/{marcaId}")
  public Response updateMarca(@HeaderParam("authorization") String token, @PathParam("marcaId") long id, MarcaDto marcaDto){


    try{

      ComandoDecode comandoDecode = Fabrica.crearComandoSeguridad(ComandoDecode.class, token);
      comandoDecode.execute();

      ComandoUpdateMarca comandoUpdateMarca = Fabrica.crearComandoConAmbos(ComandoUpdateMarca.class,id,marcaDto);
      comandoUpdateMarca.execute();

      return  Response.ok().entity(comandoUpdateMarca.getResultado()).build();

    }catch (ProblemaExcepcion ex){

      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return  Response.status(400).entity(manejadorExcepcion.getMensajeError(ex.getMensaje_soporte(), ex.getMensaje(), "error", 400)).build();

    }
    catch (Exception ex){

      String mensaje = "Ha ocurrido un error en el servidor";
      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return  Response.status(500).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 500)).build();

    }

  }

  /**
   * Metodo para obtener una marca Accedido mediante /marca/getmarca/{marcaId} con el
   * metodo GET
   *
   * @param id Id de la marca
   *
   * @return JSON success: {data, estado, code}; error: {estado,
   * code}
   */
  @GET
  @Path("/getmarca/{marcaId}")
  public Response getMarcaById(@PathParam("marcaId") long id){


    try{

      ComandoGetMarca comandoGetMarca = Fabrica.crearComandoConId(ComandoGetMarca.class, id);
      comandoGetMarca.execute();

      return Response.ok().entity(comandoGetMarca.getResultado()).build();

    }catch (Exception ex){

      String mensaje = "Ha ocurrido un error en el servidor";
      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return  Response.status(500).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 500)).build();

    }

  }

  /**
   * Metodo para obtener todas las marcas Accedido mediante /marca/getall con el
   * metodo GET
   *
   *
   * @return JSON success: {code, estado, marcas}; error: {mensaje, estado,
   * code}
   */
  @GET
  @Path("/getall")
  public Response getMarcas() {

    try {

      ComandoGetMarcas comandoGetMarcas = Fabrica.crear(ComandoGetMarcas.class);
      comandoGetMarcas.execute();

      return Response.ok().entity(comandoGetMarcas.getResultado()).build();

    } catch (Exception ex) {

      String mensaje = "Ha ocurrido un error en el servidor";
      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return Response.status(500).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 500)).build();
    }

  }

  /**
   * Metodo para inactivar una marca Accedido mediante /marca/disable/{marcaId} con el
   * metodo PUT
   *
   * @param id Id de la marca
   *
   * @return JSON success: {marca, estado, code}; error: {mensaje, estado,
   * code}
   */
  @PUT
  @Path("/disable/{marcaId}")
  public Response disableMarca(@HeaderParam("authorization") String token, @PathParam("marcaId") long id) {


    try{

      ComandoDecode comandoDecode = Fabrica.crearComandoSeguridad(ComandoDecode.class, token);
      comandoDecode.execute();

      ComandoDesactivarMarca comandoDesactivarMarca = Fabrica.crearComandoConId(ComandoDesactivarMarca.class,id);
      comandoDesactivarMarca.execute();

      return Response.ok().entity(comandoDesactivarMarca.getResultado()).build();


    }catch (Exception ex){

      String mensaje = "Ha ocurrido un error en el servidor";
      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return Response.status(500).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 500 )).build();

    }


  }

  /**
   * Metodo para activar una marca Accedido mediante /marca/enable/{marcaId} con el
   * metodo PUT
   *
   * @param id Id de la marca
   *
   * @return JSON success: {marca, estado, code}; error: {mensaje, estado,
   * code}
   */
  @PUT
  @Path("/enable/{marcaId}")
  public Response enableMarca(@HeaderParam("authorization") String token, @PathParam("marcaId") long id) {


    try{

      ComandoDecode comandoDecode = Fabrica.crearComandoSeguridad(ComandoDecode.class, token);
      comandoDecode.execute();

      ComandoActivarMarca comandoActivarMarca = Fabrica.crearComandoConId(ComandoActivarMarca.class,id);
      comandoActivarMarca.execute();

      return Response.ok().entity(comandoActivarMarca.getResultado()).build();

    }catch (Exception ex){

      String mensaje = "Ha ocurrido un error en el servidor";
      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return Response.status(500).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 500 )).build();
    }

  }

}
