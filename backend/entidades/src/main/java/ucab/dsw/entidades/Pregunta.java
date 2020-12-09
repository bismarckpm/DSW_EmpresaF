package ucab.dsw.entidades;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "pregunta")
public class Pregunta {

  @Id
  @Column( name = "codigo_pregunta" )
  @GeneratedValue( strategy = GenerationType.IDENTITY )
  private long _id;

  @Column(name = "descripcion_pregunta")
  private String _descripcionPregunta;

  @Column(name = "tipo_pregunta")
  private String _tipoPregunta;

  @Column(name = "max")
  private Integer _max;

  @Column(name = "min")
  private Integer _min;

  @OneToMany(mappedBy = "_pregunta", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  private List<PreguntaEncuesta> _preguntasEncuestas;

  @OneToMany(mappedBy = "_pregunta", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  private List<PreguntaOpcion> _preguntasOpciones;

  public Pregunta(long _id) {
    this._id = _id;
  }

  public Pregunta() {
  }

  public long get_id() {
    return _id;
  }

  public void set_id(long _id) {
    this._id = _id;
  }

  public String get_descripcionPregunta() {
    return _descripcionPregunta;
  }

  public void set_descripcionPregunta(String _descripcionPregunta) {
    this._descripcionPregunta = _descripcionPregunta;
  }

  public String get_tipoPregunta() {
    return _tipoPregunta;
  }

  public void set_tipoPregunta(String _tipoPregunta) {
    this._tipoPregunta = _tipoPregunta;
  }

  public Integer get_max() {
    return _max;
  }

  public void set_max(Integer _max) {
    this._max = _max;
  }

  public Integer get_min() {
    return _min;
  }

  public void set_min(Integer _min) {
    this._min = _min;
  }

  public List<PreguntaEncuesta> get_preguntasEncuestas() {
    return _preguntasEncuestas;
  }

  public void set_preguntasEncuestas(List<PreguntaEncuesta> _preguntasEncuestas) {
    this._preguntasEncuestas = _preguntasEncuestas;
  }

  public List<PreguntaOpcion> get_preguntasOpciones() {
    return _preguntasOpciones;
  }

  public void set_preguntasOpciones(List<PreguntaOpcion> _preguntasOpciones) {
    this._preguntasOpciones = _preguntasOpciones;
  }
}
