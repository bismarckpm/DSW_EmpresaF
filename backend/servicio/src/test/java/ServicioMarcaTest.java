import org.junit.Assert;
import org.junit.Test;
import ucab.dsw.dtos.MarcaDto;
import ucab.dsw.dtos.SubcategoriaDto;
import ucab.dsw.servicio.marca.ServicioMarca;
import ucab.dsw.servicio.subcategoria.ServicioSubcategoria;

import javax.ws.rs.core.Response;

public class ServicioMarcaTest {

  @Test
  public void addMarcaTest() throws Exception {
    ServicioMarca servicio = new ServicioMarca();
    MarcaDto marcaDto = new MarcaDto();

    marcaDto.setNombreMarca("Primor");
    marcaDto.setTipoMarca("Solido");
    marcaDto.setCapacidad(500.0);
    marcaDto.setUnidad("100");

    SubcategoriaDto subcategoriaDto = new SubcategoriaDto(1);
    marcaDto.setSubcategoria(subcategoriaDto);

    Response resultado = servicio.addMarca(marcaDto);

    Assert.assertEquals(resultado.getStatus(), 200);
  }

  @Test
  public void getAllMarcasTest(){
    ServicioMarca servicioMarca = new ServicioMarca();

    Response resultado = servicioMarca.getMarcas();

    Assert.assertEquals(resultado.getStatus(), 200);

  }
}
