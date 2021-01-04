package ucab.dsw.servicio.lugar;

import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoParroquia;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Parroquia;
import ucab.dsw.servicio.AplicacionBase;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Clase para gestionar las parroquias
 *
 */
@Path( "/parroquia" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioParroquia extends AplicacionBase {


  /**
   * Metodo para Obtener todas las parroquias. Accedido mediante
   * /parroquia/getall con el metodo GET
   *
   *
   * @return JSON success: {code, estado, parroquias};
   * error: {mensaje, estado, code}
   *
   */
  @GET
  @Path("/getall")
  public Response getParroquias() {

    List<Parroquia> parroquias;
    JsonObject data;

    try {

      DaoParroquia daoParroquia = new DaoParroquia();
      parroquias = daoParroquia.findAll(Parroquia.class);

      JsonArrayBuilder parroquiasArray = Json.createArrayBuilder();

      for(Parroquia parroquia: parroquias){
        JsonObject parro = Json.createObjectBuilder()
          .add("nombreParroquia", parroquia.get_nombreParroquia())
          .add("idParroquia", parroquia.get_id())
          .add("nombreMunicipio", parroquia.get_municipio().get_nombreMunicipio())
          .add("idMunicipio", parroquia.get_municipio().get_id())
          .add("nombrePais", parroquia.get_municipio().get_pais().get_nombre())
          .add("idPais", parroquia.get_municipio().get_pais().get_id())
          .build();

        parroquiasArray.add(parro);
      }
      data = Json.createObjectBuilder()
        .add("estado", 200)
        .add("estado", "success")
        .add("parroquias", parroquiasArray).build();

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
