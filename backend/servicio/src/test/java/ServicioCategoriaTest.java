import org.junit.Assert;
import org.junit.Test;
import ucab.dsw.dtos.CategoriaDto;
import ucab.dsw.servicio.categoria.ServicioCategoria;


import javax.ws.rs.core.Response;

public class ServicioCategoriaTest {

  @Test
  public void addCategoriaTest(){
    ServicioCategoria servicio = new ServicioCategoria();
    CategoriaDto categoriaDto = new CategoriaDto();

    categoriaDto.setNombreCategoria("perfumes");

    Response resultado = servicio.addCategoria(categoriaDto);

    Assert.assertEquals(resultado.getStatus(), 200);
  }

  @Test
  public void getAllCategoriasTest(){
    ServicioCategoria servicioCategoria = new ServicioCategoria();

    Response resultado = servicioCategoria.getCategories();

    Assert.assertEquals(resultado.getStatus(), 200);

  }
}
