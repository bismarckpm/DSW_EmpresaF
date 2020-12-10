package ucab.dsw.accesodatos;

import ucab.dsw.entidades.Encuesta;
import ucab.dsw.entidades.Encuestado;
import ucab.dsw.entidades.SolicitudEstudio;
import ucab.dsw.entidades.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class DaoEncuesta extends Dao<Encuesta>{
  private EntityManager _em;
  static DaoHandler _handler = new DaoHandler();


  public DaoEncuesta( )
  {
    super( _handler );
  }
}
