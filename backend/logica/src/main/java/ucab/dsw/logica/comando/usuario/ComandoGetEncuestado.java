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
import java.util.List;


public class ComandoGetEncuestado implements ComandoBase {

  private long idUsuario;
  private UsuarioDto usuarioDto;
  private JsonArrayBuilder telefonosArray = Json.createArrayBuilder();

  public ComandoGetEncuestado(long idUsuario) {
    this.idUsuario = idUsuario;
  }

  public void execute() throws Exception {

    try {

      DaoUsuario daoUsuario = Fabrica.crear(DaoUsuario.class);
      Usuario user = daoUsuario.find(this.idUsuario, Usuario.class);

      DaoTelefono daoTelefono = Fabrica.crear(DaoTelefono.class);
      List<Telefono> telefonos = daoTelefono.findAll(Telefono.class);

      for(Telefono telefono:telefonos){

        if(telefono.get_encuestado().get_id() == user.get_encuestado().get_id()) {

          JsonObject phones = Json.createObjectBuilder()
            .add("telefonoId", telefono.get_id())
            .add("codigoArea", telefono.get_codigoArea())
            .add("numeroTelefono", telefono.get_numeroTelefono()).build();

          telefonosArray.add(phones);

        }

      }

      this.usuarioDto = MapperUsuario.MapEntityToUsuarioEncuestadoDto(user);

    }catch (Exception ex){
      throw ex;
    }

  }

  public JsonObject getResultado(){

    try{

      JsonObject data = Json.createObjectBuilder()
        .add("idUsuario", this.usuarioDto.getId())
        .add("idEncuestado", this.usuarioDto.getEncuestadoDto().getId())
        .add("nombreUsuario", this.usuarioDto.getNombreUsuario())
        .add("primer_nombre", this.usuarioDto.getEncuestadoDto().getPrimerNombre())
        .add("primer_apellido", this.usuarioDto.getEncuestadoDto().getPrimerApellido())
        .add("numero_de_identificacion", this.usuarioDto.getEncuestadoDto().getNumeroIdentificacion())
        .add("estado", this.usuarioDto.getEstado())
        .add("genero", this.usuarioDto.getEncuestadoDto().getGenero())
        .add("parroquiaId", this.usuarioDto.getEncuestadoDto().getParroquia().getId())
        .add("parroquia", this.usuarioDto.getEncuestadoDto().getParroquia().getNombreParroquia())
        .add("ocupacion", this.usuarioDto.getEncuestadoDto().getOcupacion())
        .add("estadoCivil", this.usuarioDto.getEncuestadoDto().getEstadoCivil())
        .add("telefonos", this.telefonosArray).build();

      return data;

    }catch (Exception ex){
      throw ex;
    }

  }

}
