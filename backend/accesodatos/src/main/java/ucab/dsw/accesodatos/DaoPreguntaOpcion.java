package ucab.dsw.accesodatos;

import ucab.dsw.entidades.PreguntaOpcion;

import javax.persistence.EntityManager;

public class DaoPreguntaOpcion extends Dao<PreguntaOpcion>{
  private EntityManager _em;
  static DaoHandler _handler = new DaoHandler();


  public DaoPreguntaOpcion( )
  {
    super( _handler );
  }
}
