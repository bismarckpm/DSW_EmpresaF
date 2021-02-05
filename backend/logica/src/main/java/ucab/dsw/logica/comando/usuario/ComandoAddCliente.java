package ucab.dsw.logica.comando.usuario;

import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.directorioactivo.DirectorioActivo;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.excepciones.*;
import ucab.dsw.logica.comando.ComandoBase;
import ucab.dsw.logica.exepcionhandler.ManejadorExcepcion;
import ucab.dsw.logica.fabrica.Fabrica;
import ucab.sw.mapper.usuario.MapperUsuario;

import javax.json.Json;
import javax.json.JsonObject;
import javax.naming.NamingException;
import javax.ws.rs.core.Response;
import java.lang.reflect.InvocationTargetException;

public class ComandoAddCliente implements ComandoBase {

  private UsuarioDto usuarioDto;

  public ComandoAddCliente(UsuarioDto usuarioDto) {
    this.usuarioDto = usuarioDto;
  }

  public void execute() throws LimiteExcepcion, SolicitudPendienteExcepcion, PruebaExcepcion, InstantiationException, IllegalAccessException, InvocationTargetException, RangoExcepcion, NamingException, ProblemaExcepcion {

    try{

      DaoUsuario daoUsuario = Fabrica.crear(DaoUsuario.class);
      Usuario usuario = MapperUsuario.MapUsuarioClienteDtoToEntityAdd(this.usuarioDto);

      Usuario resultado = daoUsuario.insert(usuario);

      DirectorioActivo ldap = Fabrica.crear(DirectorioActivo.class);
      ldap.addEntryToLdap(this.usuarioDto, "cliente");

      this.usuarioDto = MapperUsuario.MapEntityToUsuarioDto(resultado);

    }catch (javax.persistence.PersistenceException ex){

      throw new ProblemaExcepcion("El usuario ya se encuentra registrado en el sistema", ex.getMessage());

    }catch (Exception ex){
      throw ex;
    }

  }

  public JsonObject getResultado(){

    try{

      JsonObject data = Json.createObjectBuilder().add("usuario", this.usuarioDto.getId())
        .add("estado", "success")
        .add("code", 200)
        .build();

      return data;

    }catch (Exception ex){
      throw ex;
    }

  }

}
