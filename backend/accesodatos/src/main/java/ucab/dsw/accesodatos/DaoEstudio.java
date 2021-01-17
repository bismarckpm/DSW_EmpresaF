package ucab.dsw.accesodatos;

import ucab.dsw.entidades.Encuesta;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.entidades.Muestra;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class DaoEstudio extends  Dao<Estudio>{

  private EntityManager _em;
  static DaoHandler _handler = new DaoHandler();



  public DaoEstudio( )
  {
    super( _handler );
    this._em= _handler.getSession();
  }

  public Estudio getEstudioByEncuesta(Encuesta encuesta){
    try{
      TypedQuery<Estudio> estudio = this._em.createNamedQuery("getEstudioByEncuesta", Estudio.class);
      estudio.setParameter("encuesta", encuesta);

      Estudio resultado = estudio.getSingleResult();
      return resultado;
    }
    catch (Exception ex){
      return null;
    }
  }
}
