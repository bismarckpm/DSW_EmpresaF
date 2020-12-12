package ucab.dsw.servicio.categoria;

import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.dtos.CategoriaDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.servicio.AplicacionBase;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path( "/categoria" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioCategoria extends AplicacionBase {

  @POST
  @Path("/add")
  public Response addCategoria(CategoriaDto categoriaDto){
    JsonObject data;
    try {

      Categoria categoria = new Categoria();
      categoria.set_nombreCategoria(categoriaDto.getNombreCategoria());

      DaoCategoria daoCategoria = new DaoCategoria();
      Categoria categoriaAgregada = daoCategoria.insert(categoria);

      categoriaDto.setId(categoriaAgregada.get_id());

      data = Json.createObjectBuilder().add("categoria", categoriaDto.getId())
        .add("estado", "success")
        .add("code", 200)
        .build();

    }
    catch (javax.persistence.PersistenceException ex){
        String mensaje = "Esta categoría ya se encuentra añadida";
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

  @GET
  @Path("/getall")
  public Response getCategories() {

    List<Categoria> categorias = null;
    JsonObject data = null;

    try {

      DaoCategoria dao = new DaoCategoria();
      categorias = dao.findAll(Categoria.class);

      JsonArrayBuilder categoriasArray = Json.createArrayBuilder();

      for(Categoria category: categorias){
        JsonObject categories = Json.createObjectBuilder()
          .add("id", category.get_id())
          .add("nombreCategoria", category.get_nombreCategoria())
          .build();

        categoriasArray.add(categories);
      }
      data = Json.createObjectBuilder()
        .add("estado", 200)
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

}
