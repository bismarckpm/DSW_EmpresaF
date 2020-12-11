package ucab.dsw.entidades;


import javax.persistence.*;

@Entity
@Table(name= "muestra")
@NamedQueries({
  @NamedQuery(name = "getEstudiosRealizablesByEncuestado", query = "select m._solicitudEstudio from Muestra m where m._encuestado =:encuestado")
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
}
