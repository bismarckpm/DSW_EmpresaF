package ucab.dsw.entidades;


import javax.persistence.*;

@Entity
@Table(name= "muestra")
@NamedQueries({
  @NamedQuery(name = "getEstudiosRealizablesByEncuestado", query = "select m._solicitudEstudio from Muestra m where m._encuestado =:encuestado and m._estado='pendiente'"),
  @NamedQuery(name = "getEncuestadosMuestraBySolicitud", query = "select m._encuestado from Muestra m where m._solicitudEstudio =:solicitud"),
  @NamedQuery(name = "getMuestraBySolicitudAndEncuestado", query = "select m from Muestra m where m._solicitudEstudio =:solicitud and m._encuestado =:encuestado"),
  @NamedQuery(name = "getEncuestadoAgregable", query = "select m._encuestado from Muestra m where m._encuestado =:encuestado and m._solicitudEstudio =:solicitudEstudio"),
  @NamedQuery(name = "getEstadoByMuestra", query = "select m._estado from Muestra m where m._encuestado =:encuestado and m._solicitudEstudio =:solicitudEstudio")
})
public class Muestra {

  @Id
  @Column( name = "codigo_muestra" )
  @GeneratedValue( strategy = GenerationType.IDENTITY )
  private long _id;

  @ManyToOne
  @JoinColumn(name = "fk_encuestado")
  private Encuestado _encuestado;

  @ManyToOne
  @JoinColumn(name = "fk_solicitud_estudio")
  private SolicitudEstudio _solicitudEstudio;

  @Column(name = "estado")
  private String _estado;

  public Muestra(long _id) {
    this._id = _id;
  }

  public Muestra() {
  }

  public long get_id() {
    return _id;
  }

  public void set_id(long _id) {
    this._id = _id;
  }

  public Encuestado get_encuestado() {
    return _encuestado;
  }

  public void set_encuestado(Encuestado _encuestado) {
    this._encuestado = _encuestado;
  }

  public SolicitudEstudio get_solicitudEstudio() {
    return _solicitudEstudio;
  }

  public void set_solicitudEstudio(SolicitudEstudio _solicitudEstudio) {
    this._solicitudEstudio = _solicitudEstudio;
  }

  public String get_estado() {
    return _estado;
  }

  public void set_estado(String _estado) {
    this._estado = _estado;
  }
}
