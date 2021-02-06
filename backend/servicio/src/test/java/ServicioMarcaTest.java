import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.autenticacion.Autenticacion;
import ucab.dsw.dtos.MarcaDto;
import ucab.dsw.dtos.SubcategoriaDto;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.servicio.marca.ServicioMarca;

import javax.json.JsonObject;
import javax.ws.rs.core.Response;

public class ServicioMarcaTest {

  private String token;

  @Before
  public void generateToken() throws PruebaExcepcion {

    Autenticacion autenticacion = new Autenticacion();

    UsuarioDto usuarioDto = new UsuarioDto();
    usuarioDto.setId((long) 4);
    usuarioDto.setNombreUsuario("administrador1@gmail.com");
    usuarioDto.setContrasena("12345");

    DaoUsuario daoUsuario = new DaoUsuario();
    Usuario usuario = daoUsuario.find((long) 4, Usuario.class);

    this.token = autenticacion.generateToken(usuarioDto);
    usuario.set_token(this.token);
    daoUsuario.update(usuario);

  }

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

    Response resultado = servicio.addMarca(this.token, marcaDto);
    JsonObject respuesta =  (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("marca"));

  }

  @Test
  public void getMarcaById(){

    ServicioMarca servicioMarca = new ServicioMarca();

    Response resultado = servicioMarca.getMarcaById(7);
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
  public void updateMarcaTest(){

    ServicioMarca servicioMarca = new ServicioMarca();
    MarcaDto marcaDto = new MarcaDto();

    marcaDto.setNombreMarca("Pepsi");
    marcaDto.setTipoMarca("Botella");
    marcaDto.setCapacidad(1.0);
    marcaDto.setUnidad("Litro");

    Response resultado = servicioMarca.updateMarca(this.token,7,marcaDto);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("marca"));

  }

  @Test
  public void desactivarMarcaTest(){

    ServicioMarca servicioMarca = new ServicioMarca();

    Response resultado = servicioMarca.disableMarca(this.token, 7);
    JsonObject respuesta = (JsonObject) resultado.getEntity();
    System.out.println(respuesta);

    Assert.assertNotNull(respuesta.get("marca"));

  }

  @Test
  public void activarMarcaTest(){

    ServicioMarca servicioMarca = new ServicioMarca();

    Response resultado = servicioMarca.enableMarca(this.token, 7);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("marca"));

  }

}
