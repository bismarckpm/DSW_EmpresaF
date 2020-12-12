import org.junit.Assert;
import org.junit.Test;
import ucab.dsw.dtos.MarcaDto;
import ucab.dsw.dtos.SubcategoriaDto;
import ucab.dsw.servicio.marca.ServicioMarca;

import javax.ws.rs.core.Response;

public class ServicioMarcaTest {

  @Test
  public void addMarcaTest() throws Exception {
    ServicioMarca servicio = new ServicioMarca();
    MarcaDto marcaDto = new MarcaDto();

    marcaDto.setNombreMarca("Malta");
    marcaDto.setTipoMarca("Botella");
    marcaDto.setCapacidad(500.0);
    marcaDto.setUnidad("5");

    SubcategoriaDto subcategoriaDto = new SubcategoriaDto(1);
    marcaDto.setSubcategoria(subcategoriaDto);

    Response resultado = servicio.addMarca(marcaDto);

    Assert.assertEquals(resultado.getStatus(), 200);
  }
}
