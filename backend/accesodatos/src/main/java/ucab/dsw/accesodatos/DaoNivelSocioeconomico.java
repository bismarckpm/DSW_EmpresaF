package ucab.dsw.accesodatos;

import ucab.dsw.entidades.NivelSocioeconomico;

import javax.persistence.EntityManager;

public class DaoNivelSocioeconomico extends  Dao<NivelSocioeconomico>{

  private EntityManager _em;
  static DaoHandler _handler = new DaoHandler();


  public DaoNivelSocioeconomico( )
  {
    super( _handler );
  }
}
