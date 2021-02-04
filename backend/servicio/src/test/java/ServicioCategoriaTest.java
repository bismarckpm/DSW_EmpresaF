import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.autenticacion.Autenticacion;
import ucab.dsw.dtos.CategoriaDto;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.servicio.categoria.ServicioCategoria;

import javax.json.JsonObject;
import javax.ws.rs.core.Response;

public class ServicioCategoriaTest {

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
  public void addCategoriaTest(){

    ServicioCategoria servicio = new ServicioCategoria();
    CategoriaDto categoriaDto = new CategoriaDto();

    categoriaDto.setNombreCategoria("perfumesitos 10");

    Response resultado = servicio.addCategoria(this.token, categoriaDto);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("categoria"));

  }

  @Test
  public void updateCategoriaTest(){

    ServicioCategoria servicioCategoria = new ServicioCategoria();
    CategoriaDto categoriaDto = new CategoriaDto();
    categoriaDto.setNombreCategoria("perfumes actualizado");

    Response resultado = servicioCategoria.updateCategoria(this.token, 85, categoriaDto);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotEquals(respuesta.get("categoria"), 0);

  }

  @Test
  public void getCategoriaById(){

    ServicioCategoria servicioCategoria = new ServicioCategoria();

    Response resultado = servicioCategoria.getCategoriaById(84);
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

    Response resultado = servicioCategoria.disableCategoria(this.token, 84);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("categoria"));

  }

  @Test
  public void activarCategoriaTest(){

    ServicioCategoria servicioCategoria = new ServicioCategoria();

    Response resultado = servicioCategoria.enableCategoria(this.token, 84);
    JsonObject respuesta = (JsonObject) resultado.getEntity();

    Assert.assertNotNull(respuesta.get("categoria"));

  }

}
