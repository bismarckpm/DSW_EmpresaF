
import javax.ws.rs.core.Response;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import ucab.dsw.dtos.EncuestaDto;
import ucab.dsw.dtos.SubcategoriaDto;
import ucab.dsw.servicio.encuesta.ServicioEncuesta;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ServicioEncuestaTest {
    
    @Test
    public void t1_addEncuesta() throws Exception {
        
        ServicioEncuesta servicioEncuesta = new ServicioEncuesta();
        
        EncuestaDto encuestaDto = new EncuestaDto();
        SubcategoriaDto subcategoriaDto = new SubcategoriaDto(6);
        
        encuestaDto.setSubcategoria(subcategoriaDto);
        
        Response resultado = servicioEncuesta.addEncuesta(encuestaDto);
        
        System.out.println(resultado.getEntity().toString());
        Assert.assertEquals(200, resultado.getStatus());
        
    }
    
    @Test
    public void t2_getAllEncuestas() throws Exception {
        
        ServicioEncuesta servicioEncuesta = new ServicioEncuesta();
        
        Response resultado = servicioEncuesta.getEncuestas();
        
        System.out.println(resultado.getEntity().toString());
        
        Assert.assertEquals(200, resultado.getStatus());
        
    }
    
    @Test
    public void t3_getEncuesta() throws Exception {
        
        ServicioEncuesta servicioEncuesta = new ServicioEncuesta();
        
        Response resultado = servicioEncuesta.getEncuesta(2);
        
        System.out.println(resultado.getEntity().toString());
        
        Assert.assertEquals(200, resultado.getStatus());
        
    }
}
