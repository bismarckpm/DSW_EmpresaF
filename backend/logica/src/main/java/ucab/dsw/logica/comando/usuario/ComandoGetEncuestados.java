package ucab.dsw.logica.comando.usuario;

import ucab.dsw.accesodatos.DaoTelefono;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.entidades.Telefono;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.excepciones.LimiteExcepcion;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.excepciones.RangoExcepcion;
import ucab.dsw.excepciones.SolicitudPendienteExcepcion;
import ucab.dsw.logica.comando.ComandoBase;
import ucab.dsw.logica.fabrica.Fabrica;
import ucab.sw.mapper.usuario.MapperUsuario;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.naming.NamingException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class ComandoGetEncuestados implements ComandoBase {

  private JsonArrayBuilder usuariosArray = Json.createArrayBuilder();

  public void execute() throws LimiteExcepcion, SolicitudPendienteExcepcion, PruebaExcepcion, InstantiationException, IllegalAccessException, InvocationTargetException, RangoExcepcion, NamingException {

    try{

      DaoUsuario daoUsuario = Fabrica.crear(DaoUsuario.class);
      List<Usuario> usuarios = daoUsuario.findAll(Usuario.class);

      JsonArrayBuilder telefonosArray = Json.createArrayBuilder();

      for(Usuario user: usuarios) {

        if(user.get_encuestado() != null) {

          DaoTelefono daoTelefono = Fabrica.crear(DaoTelefono.class);
          List<Telefono> telefonos = daoTelefono.findAll(Telefono.class);

          for(Telefono telefono:telefonos){

            if(telefono.get_encuestado().get_id() == user.get_encuestado().get_id()){

              JsonObject phones = Json.createObjectBuilder()
                .add("codigoArea", telefono.get_codigoArea())
                .add("numeroTelefono", telefono.get_numeroTelefono()).build();

              telefonosArray.add(phones);

            }

          }

          UsuarioDto usuarioDto = MapperUsuario.MapEntityToUsuarioDto(user);

          JsonObject users = Json.createObjectBuilder()
            .add("id", usuarioDto.getId())
            .add("nombreUsuario", usuarioDto.getNombreUsuario())
            .add("primer_nombre", user.get_encuestado().get_primerNombre())
            .add("primer_apellido", user.get_encuestado().get_primerApellido())
            .add("numero_de_identificacion", user.get_encuestado().get_numeroIdentificacion())
            .add("estado", usuarioDto.getEstado())
            .add("ocupacion", user.get_encuestado().get_ocupacion())
            .add("estadoCivil", user.get_encuestado().get_estadoCivil())
            .add("estado", user.get_estado())
            .add("idEncuestado", user.get_encuestado().get_id())
            .add("telefonos", telefonosArray)
            .build();

          usuariosArray.add(users);

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
        .add("usuarios", this.usuariosArray).build();

      return data;

    }catch (Exception ex){
      throw ex;
    }

  }

}
