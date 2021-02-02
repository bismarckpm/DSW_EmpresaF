import org.junit.Assert;
import org.junit.Test;
import ucab.dsw.dtos.MarcaDto;
import ucab.dsw.dtos.SubcategoriaDto;
import ucab.dsw.servicio.marca.ServicioMarca;
import ucab.dsw.servicio.subcategoria.ServicioSubcategoria;

import javax.json.JsonObject;
import javax.ws.rs.core.Response;

public class ServicioMarcaTest {

  @Test
  public void addMarcaTest() throws Exception {
    ServicioMarca servicio = new ServicioMarca();
    MarcaDto marcaDto = new MarcaDto();

    marcaDto.setNombreMarca("Pepsi");
    marcaDto.setTipoMarca("Botella");
    marcaDto.setCapacidad(2.0);
    marcaDto.setUnidad("Litros");

    SubcategoriaDto subcategoriaDto = new SubcategoriaDto(30);
    marcaDto.setSubcategoria(subcategoriaDto);

    Response resultado = servicio.addMarca(marcaDto);
    JsonObject respuesta =  (JsonObject) resultado.getEntity();

    Assert.assertNotEquals(respuesta.get("marca"), 0);
  }

  @Test
  public void updateMarcaTest(){

    ServicioMarca servicioMarca = new ServicioMarca();
    MarcaDto marcaDto = new MarcaDto();

    marcaDto.setNombreMarca("Pepsi");
    marcaDto.setTipoMarca("Botella");
    marcaDto.setCapacidad(1.0);
    marcaDto.setUnidad("Litro");

    Response resultado = servicioMarca.updateMarca(10,marcaDto);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotEquals(respuesta.get("marca"), 0);

  }

  @Test
  public void getMarcaById(){

    ServicioMarca servicioMarca = new ServicioMarca();

    Response resultado = servicioMarca.getMarcaById(10);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("id"));
  }

  @Test
  public void getAllMarcasTest(){
    ServicioMarca servicioMarca = new ServicioMarca();

    Response resultado = servicioMarca.getMarcas();
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("marcas"));

  }

  @Test
  public void desactivarMarcaTest(){

    ServicioMarca servicioMarca = new ServicioMarca();

    Response resultado = servicioMarca.disableMarca(10);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("marca"));

  }

  @Test
  public void activarMarcaTest(){

    ServicioMarca servicioMarca = new ServicioMarca();

    Response resultado = servicioMarca.enableMarca(10);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("marca"));

  }
}
