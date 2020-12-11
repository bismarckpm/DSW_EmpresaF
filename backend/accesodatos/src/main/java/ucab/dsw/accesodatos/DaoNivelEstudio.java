package ucab.dsw.accesodatos;

import ucab.dsw.entidades.NivelEstudio;

import javax.persistence.EntityManager;

public class DaoNivelEstudio extends Dao<NivelEstudio>{
  private EntityManager _em;
  static DaoHandler _handler = new DaoHandler();


  public DaoNivelEstudio( )
  {
    super( _handler );
  }
}
