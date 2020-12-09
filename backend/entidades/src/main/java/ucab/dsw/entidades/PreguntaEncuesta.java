package ucab.dsw.entidades;

import javax.persistence.*;

@Entity
@Table(name = "pregunta_encuesta")
public class PreguntaEncuesta {

  @Id
  @Column( name = "codigo_preg_encu" )
  @GeneratedValue( strategy = GenerationType.IDENTITY )
  private long _id;

  @ManyToOne
  @JoinColumn(name = "fk_pregunta")
  private Pregunta _pregunta;

  @ManyToOne
  @JoinColumn(name = "fk_encuesta")
  private Encuesta _encuesta;

  public PreguntaEncuesta(long _id) {
    this._id = _id;
  }

  public PreguntaEncuesta() {
  }

  public long get_id() {
    return _id;
  }

  public void set_id(long _id) {
    this._id = _id;
  }

  public Pregunta get_pregunta() {
    return _pregunta;
  }

  public void set_pregunta(Pregunta _pregunta) {
    this._pregunta = _pregunta;
  }

  public Encuesta get_encuesta() {
    return _encuesta;
  }

  public void set_encuesta(Encuesta _encuesta) {
    this._encuesta = _encuesta;
  }
}
