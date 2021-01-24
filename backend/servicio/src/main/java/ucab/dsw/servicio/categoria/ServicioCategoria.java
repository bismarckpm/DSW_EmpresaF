package ucab.dsw.servicio.categoria;

import org.eclipse.persistence.exceptions.DatabaseException;
import ucab.dsw.dtos.CategoriaDto;
import ucab.dsw.logica.comando.activador.ComandoActivarCategoria;
import ucab.dsw.logica.comando.activador.ComandoDesactivarCategoria;
import ucab.dsw.logica.comando.categoria.ComandoAddCategoria;
import ucab.dsw.logica.comando.categoria.ComandoUpdateCategoria;
import ucab.dsw.logica.comando.categoria.ComandoGetCategoria;
import ucab.dsw.logica.comando.categoria.ComandoGetCategorias;
import ucab.dsw.logica.exepcionhandler.ManejadorExcepcion;
import ucab.dsw.logica.fabrica.Fabrica;
import ucab.dsw.servicio.AplicacionBase;

import javax.persistence.PersistenceException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Clase para gestionar las categorias
 *
 */

@Path( "/categoria" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioCategoria extends AplicacionBase {

  /**
   * Metodo para agregar una categoria Accedido mediante categoria/add/ con el
   * metodo POST
   *
   * @param categoriaDto DTO de la categoria
   * @return JSON success: {categoria, estado, code}; error: {mensaje, estado,
   * code}
   */
  @POST
  @Path("/add")
  public Response addCategoria(CategoriaDto categoriaDto){

    try {

      ComandoAddCategoria comandoAddCategoria = Fabrica.crearComandoConDto(ComandoAddCategoria.class, categoriaDto);
      comandoAddCategoria.execute();

      return Response.ok().entity(comandoAddCategoria.getResultado()).build();

    }
    catch (PersistenceException | DatabaseException  ex){

      String mensaje = "Esta categoria ya se encuentra agregada en el sistema";
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
   * Metodo para actualizar una categoria Accedido mediante categoria/update/{categoriaId} con el
   * metodo PUT
   *
   * @param id Id de la categoria
   * @param categoriaDto DTO de la categoria
   * @return JSON success: {categoria, estado, code}; error: {mensaje, estado,
   * code}
   */
  @PUT
  @Path("/update/{categoriaId}")
  public Response updateCategoria(@PathParam("categoriaId") long id, CategoriaDto categoriaDto){

    try{

      ComandoUpdateCategoria comandoUpdateCategoria = Fabrica.crearComandoConAmbos(ComandoUpdateCategoria.class, id, categoriaDto);
      comandoUpdateCategoria.execute();

      return Response.ok().entity(comandoUpdateCategoria.getResultado()).build();

    }catch (PersistenceException | DatabaseException  ex){

      String mensaje = "Esta categoria ya se encuentra agregada en el sistema";
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
   * Metodo para obtener una categoria Accedido mediante categoria/getcategoria/{categoriaId} con el
   * metodo GET
   *
   * @param id Id de la categoria
   * @return JSON success: {id, nombreCategoria, estadoCategoria, estado, code}; error: {estado,
   * code}
   */
  @GET
  @Path("/getcategoria/{categoriaId}")
  public Response getCategoriaById(@PathParam("categoriaId") long id){

    try{

      ComandoGetCategoria comandoGetCategoria = Fabrica.crearComandoConId(ComandoGetCategoria.class, id);
      comandoGetCategoria.execute();

      return Response.ok().entity(comandoGetCategoria.getResultado()).build();

    }catch (Exception ex){

      String mensaje = "Ha ocurrido un error en el servidor";
      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return  Response.status(500).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 500)).build();

    }

  }

  /**
   * Metodo para obtener todas las categorias Accedido mediante categoria/getall con el
   * metodo GET
   *
   *
   * @return JSON success: {categorias, estado, code}; error: {mensaje, estado,
   * code}
   */
  @GET
  @Path("/getall")
  public Response getCategories() {

    try {

      ComandoGetCategorias comandoGetCategorias = Fabrica.crear(ComandoGetCategorias.class);
      comandoGetCategorias.execute();

      return Response.ok().entity(comandoGetCategorias.getResultado()).build();

    } catch (Exception ex) {

      String mensaje = "Ha ocurrido un error en el servidor";
      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return Response.status(500).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 500)).build();
    }

  }

  /**
   * Metodo para inactivar una categoria Accedido mediante categoria/disable/{categoriaId} con el
   * metodo PUT
   *
   * @param id Id de la categoria
   * @return JSON success: {categoria, estado, code}; error: {mensaje, estado,
   * code}
   */
  @PUT
  @Path("/disable/{categoriaId}")
  public Response disableCategoria(@PathParam("categoriaId") long id) {

    try{
      ComandoDesactivarCategoria comandoDesactivarCategoria = Fabrica.crearComandoConId(ComandoDesactivarCategoria.class, id);
      comandoDesactivarCategoria.execute();

      return Response.ok().entity(comandoDesactivarCategoria.getResultado()).build();

    }catch (Exception ex){

      String mensaje = "Ha ocurrido un error en el servidor";
      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return Response.status(500).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 500 )).build();
    }

  }

  /**
   * Metodo para activar una categoria Accedido mediante categoria/enable/{categoriaId} con el
   * metodo PUT
   *
   * @param id Id de la categoria
   * @return JSON success: {categoria, estado, code}; error: {mensaje, estado,
   * code}
   */
  @PUT
  @Path("/enable/{categoriaId}")
  public Response enableCategoria(@PathParam("categoriaId") long id) {

    try{

      ComandoActivarCategoria comandoActivarCategoria = Fabrica.crearComandoConId(ComandoActivarCategoria.class, id);
      comandoActivarCategoria.execute();

      return Response.ok().entity(comandoActivarCategoria.getResultado()).build();

    }catch (Exception ex){

      String mensaje = "Ha ocurrido un error en el servidor";
      ManejadorExcepcion manejadorExcepcion = Fabrica.crear(ManejadorExcepcion.class);

      return Response.status(500).entity(manejadorExcepcion.getMensajeError(ex.getMessage(), mensaje, "error", 500 )).build();
    }

  }

}
