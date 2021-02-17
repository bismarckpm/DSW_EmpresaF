package ucab.dsw.accesodatos;

import ucab.dsw.entidades.Encuesta;
import ucab.dsw.entidades.Opcion;
import ucab.dsw.entidades.Pregunta;
import ucab.dsw.entidades.PreguntaOpcion;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class DaoPreguntaOpcion extends Dao<PreguntaOpcion>{
  private EntityManager _em;
  static DaoHandler _handler = new DaoHandler();


  public DaoPreguntaOpcion( )
  {
    super( _handler );
    this._em= _handler.getSession();
  }

  public List<Opcion> findOptionsByQuestion(Pregunta pregunta){

    try {
      TypedQuery<Opcion> opciones = this._em.createNamedQuery("findOptionsByQuestion", Opcion.class);
      opciones.setParameter("pregunta", pregunta).getResultList();

      List<Opcion> resultado = opciones.getResultList();
      return resultado;
    }
    catch (Exception ex){
      return null;
    }

  }

}
