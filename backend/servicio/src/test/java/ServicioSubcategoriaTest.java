import org.junit.Assert;
import org.junit.Test;
import ucab.dsw.dtos.CategoriaDto;
import ucab.dsw.dtos.SubcategoriaDto;
import ucab.dsw.servicio.subcategoria.ServicioSubcategoria;

import javax.json.JsonObject;
import javax.ws.rs.core.Response;

public class ServicioSubcategoriaTest {

  @Test
  public void addSubcategoriaTest() throws Exception {
    ServicioSubcategoria servicio = new ServicioSubcategoria();
    SubcategoriaDto subcategoriaDto = new SubcategoriaDto();

    subcategoriaDto.setNombreSubcategoria("Piña");

    CategoriaDto categoriaDto = new CategoriaDto(59);
    subcategoriaDto.setCategoria(categoriaDto);

    Response resultado = servicio.addSubcategoria(subcategoriaDto);
    JsonObject respuesta =  (JsonObject) resultado.getEntity();

    Assert.assertNotEquals(respuesta.get("subcategoria"), 0);
  }

  @Test
  public void updateSubcategoriaTest(){

    ServicioSubcategoria servicioSubcategoria = new ServicioSubcategoria();
    SubcategoriaDto subcategoriaDto = new SubcategoriaDto();
    subcategoriaDto.setNombreSubcategoria("Manzana");

    Response resultado = servicioSubcategoria.updateSubcategoria(38,subcategoriaDto);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotEquals(respuesta.get("subcategoria"), 0);

  }

  @Test
  public void getSubcategoriaById(){

    ServicioSubcategoria servicioSubcategoria = new ServicioSubcategoria();

    Response resultado = servicioSubcategoria.getSubcategoriaById(38);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("id"));

  }

  @Test
  public void getAllSubcategoriasTest(){
    ServicioSubcategoria servicioSubcategoria = new ServicioSubcategoria();

    Response resultado = servicioSubcategoria.getSubcategories();
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("subcategorias"));

  }

  @Test
  public void desactivarSubcategoriaTest(){

    ServicioSubcategoria servicioSubcategoria = new ServicioSubcategoria();

    Response resultado = servicioSubcategoria.disableSubcategoria(30);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("subcategoria"));

  }

  @Test
  public void activarSubcategoriaTest(){

    ServicioSubcategoria servicioSubcategoria = new ServicioSubcategoria();

    Response resultado = servicioSubcategoria.enableSubcategoria(30);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("subcategoria"));

  }
}
