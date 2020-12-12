package ucab.dsw.entidades;

import javax.persistence.*;

@Entity
@Table(name = "pregunta_opcion")
public class PreguntaOpcion {

  @Id
  @Column( name = "codigo_pregunta_opcion" )
  @GeneratedValue( strategy = GenerationType.IDENTITY )
  private long _id;

  @ManyToOne
  @JoinColumn(name = "fk_pregunta")
  private Pregunta _pregunta;

  @ManyToOne
  @JoinColumn(name = "fk_opcion")
  private Opcion _opcion;

  public PreguntaOpcion(long _id) {
    this._id = _id;
  }

  public PreguntaOpcion() {
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

  public Opcion get_opcion() {
    return _opcion;
  }

  public void set_opcion(Opcion _opcion) {
    this._opcion = _opcion;
  }
}
