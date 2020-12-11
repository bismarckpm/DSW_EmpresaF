package ucab.dsw.accesodatos;

import ucab.dsw.entidades.Municipio;

import javax.persistence.EntityManager;

public class DaoMunicipio extends Dao<Municipio>{
  private EntityManager _em;
  static DaoHandler _handler = new DaoHandler();


  public DaoMunicipio( )
  {
    super( _handler );
  }
}
