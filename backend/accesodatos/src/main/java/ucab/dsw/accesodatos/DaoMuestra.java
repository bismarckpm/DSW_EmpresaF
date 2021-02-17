package ucab.dsw.accesodatos;

import ucab.dsw.entidades.*;

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

  public List<Encuestado> getEncuestadosMuestraBySolicitud(SolicitudEstudio solicitudEstudio){

    try{
      TypedQuery<Encuestado> encuestados = this._em.createNamedQuery("getEncuestadosMuestraBySolicitud", Encuestado.class);
      encuestados.setParameter("solicitud", solicitudEstudio);

      List<Encuestado> resultado = encuestados.getResultList();
      return resultado;
    }
    catch (Exception ex){
      return null;
    }
  }

  public String getEstadoByMuestra(Encuestado encuestado, SolicitudEstudio solicitudEstudio){
    try {
      TypedQuery<String> muestraEstado = this._em.createNamedQuery("getEstadoByMuestra", String.class);
      muestraEstado.setParameter("encuestado", encuestado);
      muestraEstado.setParameter("solicitudEstudio", solicitudEstudio);

      String resultado = muestraEstado.getSingleResult();
      return resultado;
    }
    catch (Exception ex){
      return null;
    }
  }


  public Encuestado getEncuestadoAgregable(Encuestado encuestado, SolicitudEstudio solicitudEstudio){
    try {
      TypedQuery<Encuestado> encuestadoAgregable = this._em.createNamedQuery("getEncuestadoAgregable", Encuestado.class);
      encuestadoAgregable.setParameter("encuestado", encuestado);
      encuestadoAgregable.setParameter("solicitudEstudio", solicitudEstudio);

      Encuestado resultado = encuestadoAgregable.getSingleResult();
      return resultado;
    }
    catch (Exception ex){
      return null;
    }
  }

}
