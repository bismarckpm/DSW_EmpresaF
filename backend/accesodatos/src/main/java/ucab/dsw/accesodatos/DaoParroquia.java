package ucab.dsw.accesodatos;

import ucab.dsw.entidades.Parroquia;

import javax.persistence.EntityManager;

public class DaoParroquia extends Dao<Parroquia>{
  private EntityManager _em;
  static DaoHandler _handler = new DaoHandler();


  public DaoParroquia( )
  {
    super( _handler );
  }
}
