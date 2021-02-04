package ucab.dsw.logica.comando.autenticacion;

import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.autenticacion.Autenticacion;
import ucab.dsw.directorioactivo.DirectorioActivo;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.excepciones.EstadoExcepcion;
import ucab.dsw.logica.comando.ComandoBase;
import ucab.dsw.logica.fabrica.Fabrica;

import javax.json.Json;
import javax.json.JsonObject;
import java.util.List;

public class ComandoLogin implements ComandoBase {

  private UsuarioDto usuarioDto;
  private String resultado;
  private Long usuarioId;
  private DirectorioActivo directorioActivo = Fabrica.crear(DirectorioActivo.class);

  public ComandoLogin(UsuarioDto usuarioDto) {
    this.usuarioDto = usuarioDto;
  }

  public void execute() throws Exception {

    try {

      DaoUsuario dao = Fabrica.crear(DaoUsuario.class);
      List<Usuario> usuarios = dao.findAll(Usuario.class);

      for(Usuario users:usuarios){

        if(users.get_nombreUsuario().equals(usuarioDto.getNombreUsuario())){

          this.usuarioId = users.get_id();

          if(users.get_estado().equals("inactivo")){

            throw  new EstadoExcepcion("Este usuario se encuentra inactivo");

          }

        }

      }

      Autenticacion autenticacion = Fabrica.crear(Autenticacion.class);
      this.usuarioDto.setId(this.usuarioId);
      this.resultado = autenticacion.generateToken(this.usuarioDto);

      Usuario usuario = dao.find(this.usuarioId, Usuario.class);
      usuario.set_token(this.resultado);
      dao.update(usuario);

    }catch (Exception ex){
      throw ex;
    }

  }

  public JsonObject getResultado(){

    try {

      JsonObject data = Json.createObjectBuilder()
        .add("token", this.resultado)
        .add("id", this.usuarioId)
        .add("rol", directorioActivo.getEntry(usuarioDto))
        .add("estado", "success")
        .add("code", 200)
        .build();

      return data;

    }catch (Exception ex){
      throw ex;
    }

  }

}
