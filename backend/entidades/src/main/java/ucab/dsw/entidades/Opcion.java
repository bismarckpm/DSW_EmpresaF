package ucab.dsw.entidades;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "opcion")
public class Opcion {

  @Id
  @Column( name = "codigo_opcion" )
  @GeneratedValue( strategy = GenerationType.IDENTITY )
  private long _id;

  @Column(name = "descripcion")
  private String _descripcion;

  @OneToMany(mappedBy = "_opcion", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  private List<PreguntaOpcion> _preguntasOpciones;

  public Opcion(long _id) {
    this._id = _id;
  }

  public Opcion() {
  }

  public long get_id() {
    return _id;
  }

  public void set_id(long _id) {
    this._id = _id;
  }

  public String get_descripcion() {
    return _descripcion;
  }

  public void set_descripcion(String _descripcion) {
    this._descripcion = _descripcion;
  }

  public List<PreguntaOpcion> get_preguntasOpciones() {
    return _preguntasOpciones;
  }

  public void set_preguntasOpciones(List<PreguntaOpcion> _preguntasOpciones) {
    this._preguntasOpciones = _preguntasOpciones;
  }
}
