import org.junit.Assert;
import org.junit.Test;
import ucab.dsw.dtos.CategoriaDto;
import ucab.dsw.dtos.SubcategoriaDto;
import ucab.dsw.servicio.categoria.ServicioCategoria;
import ucab.dsw.servicio.subcategoria.ServicioSubcategoria;

import javax.ws.rs.core.Response;

public class ServicioSubcategoriaTest {

  @Test
  public void addSubcategoriaTest() throws Exception {
    ServicioSubcategoria servicio = new ServicioSubcategoria();
    SubcategoriaDto subcategoriaDto = new SubcategoriaDto();

    subcategoriaDto.set_nombreSubcategoria("Prueba Subcategoria");

    CategoriaDto categoriaDto = new CategoriaDto(1);
    subcategoriaDto.setCategoria(categoriaDto);

    Response resultado = servicio.addCategoria(subcategoriaDto);

    Assert.assertEquals(resultado.getStatus(), 200);
  }
}
