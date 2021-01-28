package ucab.dsw.logica.comando.usuario;

import ucab.dsw.accesodatos.Dao;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.dtos.CategoriaDto;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.excepciones.LimiteExcepcion;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.excepciones.RangoExcepcion;
import ucab.dsw.excepciones.SolicitudPendienteExcepcion;
import ucab.dsw.logica.comando.ComandoBase;
import ucab.dsw.logica.fabrica.Fabrica;
import ucab.sw.mapper.categoria.MapperCategoria;
import ucab.sw.mapper.usuario.MapperUsuario;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class ComandoGetAdministradores implements ComandoBase {

  private JsonArrayBuilder usuariosDtos= Json.createArrayBuilder();

  public void execute() throws LimiteExcepcion, SolicitudPendienteExcepcion, PruebaExcepcion, InstantiationException, IllegalAccessException, InvocationTargetException, RangoExcepcion {

    try {

      DaoUsuario daoUsuario = Fabrica.crear(DaoUsuario.class);
      List<Usuario> usuarios = daoUsuario.findAll(Usuario.class);

      for(Usuario usuario:usuarios){

        if(usuario.get_rol().equals("administrador")) {

          JsonObject users = Json.createObjectBuilder()
            .add("id", usuario.get_id())
            .add("nombreUsuario", usuario.get_nombreUsuario())
            .add("estado", usuario.get_estado())
            .build();

          usuariosDtos.add(users);
        }

      }

    }catch (Exception ex){
      throw ex;
    }

  }

  public JsonObject getResultado(){

    try {

      JsonObject data = Json.createObjectBuilder()
        .add("code", 200)
        .add("estado", "success")
        .add("usuarios", usuariosDtos).build();

      return data;

    }catch (Exception ex){
      throw ex;
    }

  }

}
