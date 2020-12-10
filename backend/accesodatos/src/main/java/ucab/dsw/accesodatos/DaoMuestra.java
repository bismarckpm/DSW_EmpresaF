package ucab.dsw.accesodatos;

import ucab.dsw.entidades.Muestra;

import javax.persistence.EntityManager;

public class DaoMuestra extends Dao<Muestra>{

  private EntityManager _em;
  static DaoHandler _handler = new DaoHandler();


  public DaoMuestra( )
  {
    super( _handler );
  }
}
