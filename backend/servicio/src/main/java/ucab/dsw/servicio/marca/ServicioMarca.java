package ucab.dsw.servicio.marca;

import ucab.dsw.accesodatos.DaoMarca;
import ucab.dsw.accesodatos.DaoSubcategoria;
import ucab.dsw.dtos.MarcaDto;
import ucab.dsw.entidades.Marca;
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

@Path( "/marca" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioMarca extends AplicacionBase {

  @POST
  @Path("/add")
  public Response addMarca (MarcaDto marcaDto){
    JsonObject data;
    try {

      Marca marca = new Marca();
      marca.set_nombreMarca(marcaDto.getNombreMarca());
      marca.set_tipoMarca(marcaDto.getTipoMarca());
      marca.set_capacidad(marcaDto.getCapacidad());
      marca.set_unidad(marcaDto.getUnidad());

      Subcategoria subcategoria = new Subcategoria(marcaDto.getSubcategoria().getId());
      marca.set_subcategoria(subcategoria);

      DaoMarca daoMarca = new DaoMarca();
      Marca marcaAgregada = daoMarca.insert(marca);

      marcaDto.setId(marcaAgregada.get_id());

      data = Json.createObjectBuilder().add("marca", marcaDto.getId())
        .add("estado", "success")
        .add("code", 200)
        .build();

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
  public Response getMarcas() {

    List<Marca> marcas;
    JsonObject data;

    try {

      DaoMarca dao = new DaoMarca();
      marcas = dao.findAll(Marca.class);

      JsonArrayBuilder marcasArray = Json.createArrayBuilder();

      for(Marca marca: marcas){
        JsonObject ma = Json.createObjectBuilder()
          .add("id", marca.get_id())
          .add("nombreMarca", marca.get_nombreMarca())
          .add("tipoMarca", marca.get_tipoMarca())
          .add("capacidad", marca.get_capacidad())
          .add("unidad", marca.get_unidad())
          .add("subcategoriaId", marca.get_subcategoria().get_id())
          .build();

        marcasArray.add(ma);
      }
      data = Json.createObjectBuilder()
        .add("estado", 200)
        .add("estado", "success")
        .add("marcas", marcasArray).build();

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
