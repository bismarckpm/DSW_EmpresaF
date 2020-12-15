package ucab.dsw.entidades;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "respuesta")
public class Respuesta extends EntidadBase{

  @Id
  @Column( name = "codigo_respuesta" )
  @GeneratedValue( strategy = GenerationType.IDENTITY )
  private long _id;

  @Column(name = "fecha")
  private Date _fecha;

  @Column(name = "descripcion")
  private String _descripcion;

  @Column(name = "rango")
  private String _rango;

  @ManyToOne
  @JoinColumn(name = "fk_encuestado")
  private Encuestado _encuestado;

  @ManyToOne
  @JoinColumn(name = "fk_pregunta_encuesta")
  private PreguntaEncuesta _preguntaEncuesta;

  @ManyToOne
  @JoinColumn(name = "fk_opcion")
  private Opcion _opcion;
  
  public Respuesta(){
      
  }

  public Respuesta(long _id) {
    this._id = _id;
  }

  public long get_id() {
    return _id;
  }

  public void set_id(long _id) {
    this._id = _id;
  }

  public Date get_fecha() {
    return _fecha;
  }

  public void set_fecha(Date _fecha) {
    this._fecha = _fecha;
  }

  public String get_descripcion() {
    return _descripcion;
  }

  public void set_descripcion(String _descripcion) {
    this._descripcion = _descripcion;
  }

  public String get_rango() {
    return _rango;
  }

  public void set_rango(String _rango) {
    this._rango = _rango;
  }

  public Encuestado get_encuestado() {
    return _encuestado;
  }

  public void set_encuestado(Encuestado _encuestado) {
    this._encuestado = _encuestado;
  }

  public PreguntaEncuesta get_preguntaEncuesta() {
    return _preguntaEncuesta;
  }

  public void set_preguntaEncuesta(PreguntaEncuesta _preguntaEncuesta) {
    this._preguntaEncuesta = _preguntaEncuesta;
  }

  public Opcion get_opcion() {
    return _opcion;
  }

  public void set_opcion(Opcion _opcion) {
    this._opcion = _opcion;
  }
}
