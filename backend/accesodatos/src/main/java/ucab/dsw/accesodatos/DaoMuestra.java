package ucab.dsw.accesodatos;

import ucab.dsw.entidades.Encuestado;
import ucab.dsw.entidades.Muestra;
import ucab.dsw.entidades.SolicitudEstudio;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class DaoMuestra extends Dao<Muestra>{

  private EntityManager _em;
  static DaoHandler _handler = new DaoHandler();


  public DaoMuestra( )
  {
    super( _handler );
    this._em= _handler.getSession();
  }

  public List<SolicitudEstudio> getEstudiosRealizablesByEncuestado(Encuestado encuestado){

    try {
      TypedQuery<SolicitudEstudio> solicitudes = this._em.createNamedQuery("getEstudiosRealizablesByEncuestado", SolicitudEstudio.class);
      solicitudes.setParameter("encuestado", encuestado).getResultList();

      List<SolicitudEstudio> resultado = solicitudes.getResultList();
      return resultado;
    }
    catch (Exception ex){
      return null;
    }
  }
}
