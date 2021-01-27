package ucab.dsw.servicio.subcategoria;

import org.eclipse.persistence.exceptions.DatabaseException;
import ucab.dsw.accesodatos.Dao;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoMarca;
import ucab.dsw.accesodatos.DaoSubcategoria;
import ucab.dsw.dtos.CategoriaDto;
import ucab.dsw.dtos.SubcategoriaDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Marca;
import ucab.dsw.entidades.Subcategoria;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.servicio.AplicacionBase;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.persistence.PersistenceException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

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
    JsonObject data;

    try {



      Subcategoria subcategoria = new Subcategoria();
      subcategoria.set_nombreSubcategoria(subcategoriaDto.getNombreSubcategoria());
      subcategoria.set_estado("activo");

      DaoCategoria daoCategoria = new DaoCategoria();
      Categoria categoria = daoCategoria.find(subcategoriaDto.getCategoria().getId(), Categoria.class);

      subcategoria.set_categoria(categoria);

      DaoSubcategoria daoSubcategoria = new DaoSubcategoria();
      Subcategoria subcategoriaAgregada = daoSubcategoria.insert(subcategoria);

      subcategoriaDto.setId(subcategoriaAgregada.get_id());

      data = Json.createObjectBuilder().add("subcategoria", subcategoriaDto.getId())
        .add("estado", "success")
        .add("code", 200)
        .build();

    }
    catch (PersistenceException | DatabaseException ex){
      String mensaje = "Esta subcategoría ya se encuentra añadida";
      data = Json.createObjectBuilder().add("mensaje", mensaje)
        .add("estado", "error")
        .add("code", 400)
        .build();

      System.out.println(data);
      return  Response.ok().entity(data).build();
    }
    catch (PruebaExcepcion ex){
      data = Json.createObjectBuilder().add("mensaje", ex.getMessage())
        .add("estado", "error")
        .add("code", 400)
        .build();
      System.out.println(ex.getClass());
      return  Response.ok().entity(data).build();
    }

    System.out.println(data);
    return  Response.ok().entity(data).build();

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

    DaoSubcategoria daoSubcategoria = new DaoSubcategoria();
    JsonObject data;

    try{
      Subcategoria subcategoria = daoSubcategoria.find(id, Subcategoria.class);
      DaoCategoria daoCategoria = new DaoCategoria();
      Categoria categoria = daoCategoria.find(subcategoria.get_categoria().get_id(), Categoria.class);
      data = Json.createObjectBuilder()
        .add("estado", "success")
        .add("code", 200)
        .add("id", subcategoria.get_id())
        .add("nombreSubcategoria", subcategoria.get_nombreSubcategoria())
        .add("estadoSubcategoria", subcategoria.get_estado())
        .add("categoriaId", subcategoria.get_categoria().get_id())
        .add("categoriaNombre", categoria.get_nombreCategoria())
        .build();

      return Response.ok().entity(data).build();

    }catch (Exception ex){

      data = Json.createObjectBuilder()
        .add("estado", "error")
        .add("code", 400)
        .build();
    }

    return Response.ok().entity(data).build();
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

    JsonObject data;
    DaoSubcategoria daoSubcategoria = new DaoSubcategoria();

    try{
      Subcategoria subcategoria = daoSubcategoria.find(id, Subcategoria.class);

      subcategoria.set_nombreSubcategoria(subcategoriaDto.getNombreSubcategoria());

      DaoCategoria daoCategoria = new DaoCategoria();
      Categoria categoria = daoCategoria.find(subcategoriaDto.getCategoria().getId(), Categoria.class);
      subcategoria.set_categoria(categoria);

      Subcategoria resul = daoSubcategoria.update(subcategoria);

      data = Json.createObjectBuilder().add("subcategoria", resul.get_id())
        .add("estado", "success")
        .add("code", 200)
        .build();

    }catch (PersistenceException | DatabaseException  ex){
      String mensaje = "Esta subcategoría ya se encuentra añadida";
      data = Json.createObjectBuilder().add("mensaje", mensaje)
        .add("estado", "error")
        .add("code", 400)
        .build();

      System.out.println(data);
      return  Response.ok().entity(data).build();
    }
    catch (Exception ex){
      data = Json.createObjectBuilder().add("mensaje", ex.getMessage())
        .add("estado", "error")
        .add("code", 400)
        .build();

      System.out.println(data);
      return  Response.ok().entity(data).build();
    }

    return Response.ok().entity(data).build();
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

    List<Subcategoria> subcategorias;
    JsonObject data;

    try {

      DaoSubcategoria dao = new DaoSubcategoria();
      subcategorias = dao.findAll(Subcategoria.class);

      JsonArrayBuilder subcategoriasArray = Json.createArrayBuilder();

      for(Subcategoria subcategory: subcategorias){
        DaoCategoria daoCategoria = new DaoCategoria();
        Categoria categoria = daoCategoria.find(subcategory.get_categoria().get_id(), Categoria.class);
        JsonObject categories = Json.createObjectBuilder()
          .add("id", subcategory.get_id())
          .add("nombreSubcategoria", subcategory.get_nombreSubcategoria())
          .add("estado", subcategory.get_estado())
          .add("categoriaId", subcategory.get_categoria().get_id())
          .add("categoria", categoria.get_nombreCategoria())
          .build();

        subcategoriasArray.add(categories);
      }
      data = Json.createObjectBuilder()
        .add("estado", 200)
        .add("estado", "success")
        .add("subcategorias", subcategoriasArray).build();

    } catch (Exception ex) {

      data = Json.createObjectBuilder()
        .add("mensaje", ex.getMessage())
        .add("estado", "error")
        .add("code", 400)
        .build();

      System.out.println(data);
      return Response.ok().entity(data).build();
    }
    System.out.println(data);
    return Response.ok().entity(data).build();
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

    DaoSubcategoria daoSubcategoria = new DaoSubcategoria();
    DaoMarca daoMarca = new DaoMarca();
    JsonObject data;

    try{

      Subcategoria subcategoria = daoSubcategoria.find(id, Subcategoria.class);

      List<Marca> marcas = daoMarca.findAll(Marca.class);

      subcategoria.set_estado("inactivo");

      for (Marca marca:marcas){
        if(marca.get_subcategoria().get_id() == id){
          marca.set_estado("inactivo");
          daoMarca.update(marca);
        }
      }

      Subcategoria resul = daoSubcategoria.update(subcategoria);

      data = Json.createObjectBuilder().add("subcategoria", resul.get_id())
        .add("estado", "success")
        .add("code", 200)
        .build();


    }catch (Exception ex){

      data = Json.createObjectBuilder().add("mensaje", ex.getMessage())
        .add("estado", "success")
        .add("code", 200)
        .build();

      return Response.ok().entity(data).build();
    }

    return Response.ok().entity(data).build();
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

    DaoSubcategoria daoSubcategoria = new DaoSubcategoria();
    DaoMarca daoMarca = new DaoMarca();
    JsonObject data;

    try{

      Subcategoria subcategoria = daoSubcategoria.find(id, Subcategoria.class);

      List<Marca> marcas = daoMarca.findAll(Marca.class);

      subcategoria.set_estado("activo");

      for (Marca marca:marcas){
        if(marca.get_subcategoria().get_id() == id){
          marca.set_estado("activo");
          daoMarca.update(marca);
        }
      }

      Subcategoria resul = daoSubcategoria.update(subcategoria);

      data = Json.createObjectBuilder().add("subcategoria", resul.get_id())
        .add("estado", "success")
        .add("code", 200)
        .build();


    }catch (Exception ex){

      data = Json.createObjectBuilder().add("mensaje", ex.getMessage())
        .add("estado", "success")
        .add("code", 200)
        .build();

      return Response.ok().entity(data).build();
    }

    return Response.ok().entity(data).build();
  }
}
