import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.autenticacion.Autenticacion;
import ucab.dsw.dtos.CategoriaDto;
import ucab.dsw.dtos.SubcategoriaDto;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.servicio.categoria.ServicioCategoria;
import ucab.dsw.servicio.subcategoria.ServicioSubcategoria;

import javax.json.JsonObject;
import javax.ws.rs.core.Response;

public class ServicioSubcategoriaTest {

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
  public void addSubcategoriaTest() throws Exception {

    ServicioSubcategoria servicio = new ServicioSubcategoria();
    SubcategoriaDto subcategoriaDto = new SubcategoriaDto();

    subcategoriaDto.setNombreSubcategoria("Piña");

    CategoriaDto categoriaDto = new CategoriaDto(59);
    subcategoriaDto.setCategoria(categoriaDto);

    Response resultado = servicio.addSubcategoria(this.token, subcategoriaDto);
    JsonObject respuesta =  (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("subcategoria"));

  }

  @Test
  public void updateSubcategoriaTest(){

    ServicioSubcategoria servicioSubcategoria = new ServicioSubcategoria();
    SubcategoriaDto subcategoriaDto = new SubcategoriaDto();
    subcategoriaDto.setNombreSubcategoria("Manzana");

    Response resultado = servicioSubcategoria.updateSubcategoria(this.token, 31,subcategoriaDto);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("subcategoria"));

  }

  @Test
  public void getSubcategoriaById(){

    ServicioSubcategoria servicioSubcategoria = new ServicioSubcategoria();

    Response resultado = servicioSubcategoria.getSubcategoriaById(31);
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

    Response resultado = servicioSubcategoria.disableSubcategoria(this.token, 30);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("subcategoria"));

  }

  @Test
  public void activarSubcategoriaTest(){

    ServicioSubcategoria servicioSubcategoria = new ServicioSubcategoria();

    Response resultado = servicioSubcategoria.enableSubcategoria(this.token, 30);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("subcategoria"));

  }

}
