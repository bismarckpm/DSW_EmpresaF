package ucab.dsw.servicio.categoria;

import org.eclipse.persistence.exceptions.DatabaseException;
import ucab.dsw.accesodatos.*;
import ucab.dsw.dtos.CategoriaDto;
import ucab.dsw.entidades.*;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.servicio.AplicacionBase;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.persistence.PersistenceException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

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
    JsonObject data;
    try {

      Categoria categoria = new Categoria();
      categoria.set_nombreCategoria(categoriaDto.getNombreCategoria());
      categoria.set_estado("activo");

      DaoCategoria daoCategoria = new DaoCategoria();
      Categoria categoriaAgregada = daoCategoria.insert(categoria);

      data = Json.createObjectBuilder().add("categoria", categoriaAgregada.get_id())
        .add("estado", "success")
        .add("code", 200)
        .build();

    }
    catch (PersistenceException | DatabaseException  ex){
        String mensaje = "Esta categoría ya se encuentra añadida";
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

    System.out.println(data);
    return  Response.ok().entity(data).build();

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

    JsonObject data;
    DaoCategoria daoCategoria = new DaoCategoria();

    try{
      Categoria categoria = daoCategoria.find(id, Categoria.class);

      categoria.set_nombreCategoria(categoriaDto.getNombreCategoria());

      Categoria resul = daoCategoria.update(categoria);

      data = Json.createObjectBuilder().add("categoria", resul.get_id())
        .add("estado", "success")
        .add("code", 200)
        .build();

    }catch (PersistenceException | DatabaseException  ex){
      String mensaje = "Esta categoría ya se encuentra añadida";
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

    DaoCategoria daoCategoria = new DaoCategoria();
    JsonObject data;

    try{
      Categoria categoria = daoCategoria.find(id, Categoria.class);

      data = Json.createObjectBuilder()
        .add("estado", "success")
        .add("code", 200)
        .add("id", categoria.get_id())
        .add("nombreCategoria", categoria.get_nombreCategoria())
        .add("estadoCategoria", categoria.get_estado())
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

    List<Categoria> categorias;
    JsonObject data;

    try {

      DaoCategoria dao = new DaoCategoria();
      categorias = dao.findAll(Categoria.class);

      JsonArrayBuilder categoriasArray = Json.createArrayBuilder();

      for(Categoria category: categorias){
        JsonObject categories = Json.createObjectBuilder()
          .add("id", category.get_id())
          .add("nombreCategoria", category.get_nombreCategoria())
          .add("estado", category.get_estado())
          .build();

        categoriasArray.add(categories);
      }
      data = Json.createObjectBuilder()
        .add("code", 200)
        .add("estado", "success")
        .add("categorias", categoriasArray).build();

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

    DaoCategoria daoCategoria = new DaoCategoria();
    DaoSubcategoria daoSubcategoria = new DaoSubcategoria();
    DaoMarca daoMarca = new DaoMarca();
    JsonObject data;

    try{
      Categoria categoria = daoCategoria.find(id, Categoria.class);

      List<Subcategoria> subcategorias = daoSubcategoria.findAll(Subcategoria.class);

      List<Marca> marcas = daoMarca.findAll(Marca.class);

      categoria.set_estado("inactivo");

      for(Subcategoria subcategoria:subcategorias){

        if(subcategoria.get_categoria().get_id() == id){
          subcategoria.set_estado("inactivo");
          daoSubcategoria.update(subcategoria);

          for (Marca marca:marcas){

            if(marca.get_subcategoria().get_id() == subcategoria.get_id()){
              marca.set_estado("inactivo");
              daoMarca.update(marca);

            }
          }
        }
      }

      Categoria resul = daoCategoria.update(categoria);

      data = Json.createObjectBuilder().add("categoria", resul.get_id())
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

    DaoCategoria daoCategoria = new DaoCategoria();
    DaoSubcategoria daoSubcategoria = new DaoSubcategoria();
    DaoMarca daoMarca = new DaoMarca();
    JsonObject data;

    try{
      Categoria categoria = daoCategoria.find(id, Categoria.class);

      List<Subcategoria> subcategorias = daoSubcategoria.findAll(Subcategoria.class);

      List<Marca> marcas = daoMarca.findAll(Marca.class);

      categoria.set_estado("activo");

      for(Subcategoria subcategoria:subcategorias){

        if(subcategoria.get_categoria().get_id() == id){
          subcategoria.set_estado("activo");
          daoSubcategoria.update(subcategoria);

          for (Marca marca:marcas){

            if(marca.get_subcategoria().get_id() == subcategoria.get_id()){
              marca.set_estado("activo");
              daoMarca.update(marca);

            }
          }
        }
      }

      Categoria resul = daoCategoria.update(categoria);

      data = Json.createObjectBuilder().add("categoria", resul.get_id())
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
