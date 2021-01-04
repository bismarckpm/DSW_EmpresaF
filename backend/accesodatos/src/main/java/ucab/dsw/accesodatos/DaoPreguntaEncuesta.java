package ucab.dsw.accesodatos;

import ucab.dsw.entidades.Encuesta;
import ucab.dsw.entidades.Encuestado;
import ucab.dsw.entidades.Pregunta;
import ucab.dsw.entidades.PreguntaEncuesta;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class DaoPreguntaEncuesta extends  Dao<PreguntaEncuesta>{

  private EntityManager _em;
  static DaoHandler _handler = new DaoHandler();


  public DaoPreguntaEncuesta( )
  {
    super( _handler );
    this._em=_handler.getSession();
  }

  public List<Pregunta> getPreguntasByEncuesta(Encuesta encuesta){
    try {
      TypedQuery<Pregunta> preguntas = this._em.createNamedQuery("getPreguntasByEncuesta", Pregunta.class);
      preguntas.setParameter("encuesta", encuesta).getResultList();

      List<Pregunta> resultado = preguntas.getResultList();
      return resultado;
    }
    catch (Exception ex){
      ex.printStackTrace();
      return  null;
    }
  }

  public List<PreguntaEncuesta> getPreguntasEncuestaByEncuestaId(Encuesta encuesta){
    try {
      TypedQuery<PreguntaEncuesta> preguntaEncuesta = this._em.createNamedQuery("getPreguntasEncuestaByEncuestaId", PreguntaEncuesta.class);
      preguntaEncuesta.setParameter("encuesta", encuesta).getResultList();

      List<PreguntaEncuesta> resultado = preguntaEncuesta.getResultList();
      return resultado;
    }
    catch (Exception ex){
      ex.printStackTrace();
      return  null;
    }
  }

  public Pregunta getPreguntaAgregable(Pregunta pregunta, Encuesta encuesta){
    try {
      TypedQuery<Pregunta> preguntaEncuesta = this._em.createNamedQuery("getPreguntaAgregable", Pregunta.class);
      preguntaEncuesta.setParameter("pregunta", pregunta);
      preguntaEncuesta.setParameter("encuesta", encuesta);

      Pregunta resultado = preguntaEncuesta.getSingleResult();
      return resultado;
    }
    catch (Exception ex){
      ex.printStackTrace();
      return  null;
    }
  }
}


