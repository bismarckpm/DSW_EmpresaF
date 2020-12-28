package ucab.dsw.accesodatos;

import ucab.dsw.entidades.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class DaoRespuestaOpcion extends Dao<RespuestaOpcion> {

  private EntityManager _em;
  static DaoHandler _handler = new DaoHandler();


  public DaoRespuestaOpcion( )
  {
    super( _handler );
    this._em=_handler.getSession();
  }

  public Integer contRespuesta(Opcion opcion){
    try{
      Integer conteo = ((Number)this._em.createNamedQuery("contRespuesta").setParameter("opcion", opcion).getSingleResult()).intValue();

      return conteo;
    }
    catch (Exception ex){
      ex.printStackTrace();
      return  null;
    }
  }
}
