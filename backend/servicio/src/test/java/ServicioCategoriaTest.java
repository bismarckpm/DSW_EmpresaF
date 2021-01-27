import org.junit.Assert;
import org.junit.Test;
import ucab.dsw.dtos.CategoriaDto;
import ucab.dsw.servicio.categoria.ServicioCategoria;


import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;
import java.lang.reflect.InvocationTargetException;

public class ServicioCategoriaTest {

  @Test
  public void addCategoriaTest(){

    ServicioCategoria servicio = new ServicioCategoria();
    CategoriaDto categoriaDto = new CategoriaDto();

    categoriaDto.setNombreCategoria("perfumes");

    Response resultado = servicio.addCategoria(categoriaDto);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotEquals(respuesta.get("categoria"), 0);

  }

  @Test
  public void updateCategoriaTest(){

    ServicioCategoria servicioCategoria = new ServicioCategoria();
    CategoriaDto categoriaDto = new CategoriaDto();
    categoriaDto.setNombreCategoria("embutidos actualizado");

    Response resultado = servicioCategoria.updateCategoria(59, categoriaDto);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotEquals(respuesta.get("categoria"), 0);

  }

  @Test
  public void getCategoriaById(){

    ServicioCategoria servicioCategoria = new ServicioCategoria();

    Response resultado = servicioCategoria.getCategoriaById(59);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("id"));

  }

  @Test
  public void getAllCategoriasTest(){

    ServicioCategoria servicioCategoria = new ServicioCategoria();

    Response resultado = servicioCategoria.getCategories();
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("categorias"));

  }

  @Test
  public void desactivarCategoriaTest(){

    ServicioCategoria servicioCategoria = new ServicioCategoria();

    Response resultado = servicioCategoria.disableCategoria(59);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("categoria"));

  }

  @Test
  public void activarCategoriaTest(){

    ServicioCategoria servicioCategoria = new ServicioCategoria();

    Response resultado = servicioCategoria.enableCategoria(59);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("categoria"));

  }

}
