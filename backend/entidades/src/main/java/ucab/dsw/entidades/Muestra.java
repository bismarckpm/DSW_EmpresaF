package ucab.dsw.entidades;


import javax.persistence.*;

@Entity
@Table(name= "muestra")
public class Muestra {

  @Id
  @Column( name = "codigo_muestra" )
  @GeneratedValue( strategy = GenerationType.IDENTITY )
  private long _id;

  @ManyToOne
  @JoinColumn(name = "fk_usuario")
  private Usuario _encuestado;

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

  public Usuario get_encuestado() {
    return _encuestado;
  }

  public void set_encuestado(Usuario _encuestado) {
    this._encuestado = _encuestado;
  }

  public SolicitudEstudio get_solicitudEstudio() { 
    return _solicitudEstudio;
  }

  public void set_solicitudEstudio(SolicitudEstudio _solicitudEstudio) {
    this._solicitudEstudio = _solicitudEstudio;
  }
}
