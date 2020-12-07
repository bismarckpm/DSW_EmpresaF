package ucab.dsw.entidades;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "estudio")
public class Estudio {

  @Id
  @Column( name = "codigo_estudio" )
  @GeneratedValue( strategy = GenerationType.IDENTITY )
  private long _id;

  @Column(name = "estado")
  private String _estado;

  @Column(name = "resultado")
  private String _resultado;

  @Column(name = "fecha_inicio")
  private Date _fechaInicio;

  @Column(name = "fecha_fin")
  private Date _fechaFin;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn( name = "fk_encuesta")
  private Encuesta _encuesta;

  @OneToMany(mappedBy = "_estudio", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  private List<Muestra> _muestras;

  public Estudio() {
  }

  public Estudio(long _id) {
    this._id = _id;
  }

  public long get_id() {
    return _id;
  }

  public void set_id(long _id) {
    this._id = _id;
  }

  public String get_estado() {
    return _estado;
  }

  public void set_estado(String _estado) {
    this._estado = _estado;
  }

  public String get_resultado() {
    return _resultado;
  }

  public void set_resultado(String _resultado) {
    this._resultado = _resultado;
  }

  public Date get_fechaInicio() {
    return _fechaInicio;
  }

  public void set_fechaInicio(Date _fechaInicio) {
    this._fechaInicio = _fechaInicio;
  }

  public Date get_fechaFin() {
    return _fechaFin;
  }

  public void set_fechaFin(Date _fechaFin) {
    this._fechaFin = _fechaFin;
  }

  public Encuesta get_encuesta() {
    return _encuesta;
  }

  public void set_encuesta(Encuesta _encuesta) {
    this._encuesta = _encuesta;
  }

  public List<Muestra> get_muestras() {
    return _muestras;
  }

  public void set_muestras(List<Muestra> _muestras) {
    this._muestras = _muestras;
  }
}
