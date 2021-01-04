package ucab.dsw.accesodatos;

import ucab.dsw.entidades.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class DaoEncuesta extends Dao<Encuesta>{
  private EntityManager _em;
  static DaoHandler _handler = new DaoHandler();


  public DaoEncuesta( )
  {
    super( _handler );
    this._em= _handler.getSession();
  }

  public List<Encuesta> getEncuestasBySubcategoria(Subcategoria subcategoria){
    try {
      TypedQuery<Encuesta> encuestas = this._em.createNamedQuery("getEncuestasBySubcategoria", Encuesta.class);
      encuestas.setParameter("subcategoria", subcategoria).getResultList();

      List<Encuesta> resultado = encuestas.getResultList();
      return resultado;
    }
    catch (Exception ex){
      return null;
    }
  }
}
