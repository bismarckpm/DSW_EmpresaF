package ucab.dsw.servicio.subcategoria;

import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoSubcategoria;
import ucab.dsw.dtos.CategoriaDto;
import ucab.dsw.dtos.SubcategoriaDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Subcategoria;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.servicio.AplicacionBase;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path( "/subcategoria" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioSubcategoria extends AplicacionBase {

  @POST
  @Path("/add")
  public Response addCategoria(SubcategoriaDto subcategoriaDto){
    JsonObject data;

    try {

      Subcategoria subcategoria = new Subcategoria();
      subcategoria.set_nombreSubcategoria(subcategoriaDto.get_nombreSubcategoria());

      Categoria categoria = new Categoria(subcategoriaDto.getCategoria().getId());
      subcategoria.set_categoria(categoria);

      DaoSubcategoria daoSubcategoria = new DaoSubcategoria();
      Subcategoria subcategoriaAgregada = daoSubcategoria.insert(subcategoria);

      subcategoriaDto.setId(subcategoriaAgregada.get_id());

      data = Json.createObjectBuilder().add("subcategoria", subcategoriaDto.getId())
        .add("estado", "success")
        .add("code", 200)
        .build();

    }
    catch (javax.persistence.PersistenceException ex){
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
        JsonObject categories = Json.createObjectBuilder()
          .add("id", subcategory.get_id())
          .add("nombreSubcategoria", subcategory.get_nombreSubcategoria())
          .add("categoriaId", subcategory.get_categoria().get_id())
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
}
