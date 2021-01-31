package ucab.dsw.servicio.subcategoria;

import org.eclipse.persistence.exceptions.DatabaseException;
import ucab.dsw.dtos.SubcategoriaDto;


import ucab.dsw.logica.comando.subcategoria.*;
import ucab.dsw.logica.exepcionhandler.ManejadorExcepcion;
import ucab.dsw.logica.fabrica.Fabrica;
import ucab.dsw.servicio.AplicacionBase;

import javax.persistence.PersistenceException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Clase para gestionar las subcategorias
 *
 */
@Path( "/subcategoria" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioSubcategoria extends AplicacionBase {

  /**
   * Metodo para agregar una subcategoria. Accedido mediante /subcategoria/add con el
   * metodo POST
   *
   * @param subcategoriaDto DTO de la subcategoria
   * @return JSON success: {subcategoria, estado, code}; error: {mensaje, estado,
   * code}
   */
  @POST
  @Path("/add")
  public Response addSubcategoria(SubcategoriaDto subcategoriaDto){

    try {

      ComandoAddSubcategoria comandoAddSubcategoria = Fabrica.crearComandoConDto(ComandoAddSubcategoria.class, subcategoriaDto);
      comandoAddSubcategoria.execute();


      return Response.ok().entity(comandoAddSubcategoria.getResultado()).build();

    }
    catch (PersistenceException | DatabaseException ex){

      String mensaje = "Esta subcategoria ya se encuentra agregada en el sistema";
      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return  Response.status(400).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 400)).build();

    }
    catch (Exception ex){

      String mensaje = "Ha ocurrido un error en el servidor";
      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return  Response.status(500).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 500)).build();

    }

  }

  /**
   * Metodo para obtener una subcategoria. Accedido mediante /subcategoria/getsubcategoria/{subcategoriaId}
   * con el metodo GET
   *
   * @param id Id de la subcategoria
   * @return JSON success: {estado, code, id, nombreSubcategoria, estadoSubcategoria, categoriaId, categoriaNombre}; error: {mensaje, estado,
   * code}
   */
  @GET
  @Path("/getsubcategoria/{subcategoriaId}")
  public Response getSubcategoriaById(@PathParam("subcategoriaId") long id){


    try{

      ComandoGetSubcategoria comandoGetSubcategoria = Fabrica.crearComandoConId(ComandoGetSubcategoria.class, id);
      comandoGetSubcategoria.execute();

      return Response.ok().entity(comandoGetSubcategoria.getResultado()).build();

    }catch (Exception ex){

      String mensaje = "Ha ocurrido un error en el servidor";
      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return  Response.status(500).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 500)).build();

    }


  }

  /**
   * Metodo para actualizar una subcategoria. Accedido mediante /subcategoria/update({subcategoriaId} con el
   * metodo PUT
   *
   * @param subcategoriaDto DTO de la subcategoria
   * @param id Id de la subcategoria
   * @return JSON success: {subcategoria, estado, code}; error: {mensaje, estado,
   * code}
   */
  @PUT
  @Path("/update/{subcategoriaId}")
  public Response updateSubcategoria(@PathParam("subcategoriaId") long id, SubcategoriaDto subcategoriaDto){


    try{

      ComandoUpdateSubcategoria comandoUpdateSubcategoria = Fabrica.crearComandoConAmbos(ComandoUpdateSubcategoria.class,id,subcategoriaDto);
      comandoUpdateSubcategoria.execute();

      return Response.ok().entity(comandoUpdateSubcategoria.getResultado()).build();

    }catch (PersistenceException | DatabaseException  ex){

      String mensaje = "Esta subcategoria ya se encuentra agregada en el sistema";
      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return  Response.status(400).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 400)).build();

    }
    catch (Exception ex){

      String mensaje = "Ha ocurrido un error en el servidor";
      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return  Response.status(500).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 500)).build();

    }


  }

  /**
   * Metodo para obtener todas las subcategorias. Accedido mediante /subcategoria/add con el
   * metodo GET
   *
   * @return JSON success: {subcategorias, estado, estado}; error: {mensaje, estado,
   * code}
   */
  @GET
  @Path("/getall")
  public Response getSubcategories() {

    try {

      ComandoGetSubcategorias comandoGetSubcategorias = Fabrica.crear(ComandoGetSubcategorias.class);
      comandoGetSubcategorias.execute();

      return Response.ok().entity(comandoGetSubcategorias.getResultado()).build();

    } catch (Exception ex) {

      String mensaje = "Ha ocurrido un error en el servidor";
      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return Response.status(500).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 500)).build();
    }

  }

  /**
   * Metodo para inactivar una subcategoria. Accedido mediante /subcategoria/disable/({subcategoriaId} con el
   * metodo PUT
   *
   * @param id Id de la subcategoria
   * @return JSON success: {subcategoria, estado, code}; error: {mensaje, estado,
   * code}
   */
  @PUT
  @Path("/disable/{subcategoriaId}")
  public Response disableSubcategoria(@PathParam("subcategoriaId") long id) {

    try{

      ComandoDesactivarSubcategoria comandoDesactivarSubcategoria = Fabrica.crearComandoConId(ComandoDesactivarSubcategoria.class,id);
      comandoDesactivarSubcategoria.execute();

      return Response.ok().entity(comandoDesactivarSubcategoria.getResultado()).build();


    }catch (Exception ex){

      String mensaje = "Ha ocurrido un error en el servidor";
      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return Response.status(500).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 500 )).build();

    }

  }

  /**
   * Metodo para activar una subcategoria. Accedido mediante /subcategoria/enable/({subcategoriaId} con el
   * metodo PUT
   *
   * @param id Id de la subcategoria
   * @return JSON success: {subcategoria, estado, code}; error: {mensaje, estado,
   * code}
   */
  @PUT
  @Path("/enable/{subcategoriaId}")
  public Response enableSubcategoria(@PathParam("subcategoriaId") long id) {

    try{

      ComandoActivarSubcategoria comandoActivarSubcategoria = Fabrica.crearComandoConId(ComandoActivarSubcategoria.class,id);
      comandoActivarSubcategoria.execute();

      return Response.ok().entity(comandoActivarSubcategoria.getResultado()).build();

    }catch (Exception ex){

      String mensaje = "Ha ocurrido un error en el servidor";
      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return Response.status(500).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 500 )).build();
    }

  }
}
