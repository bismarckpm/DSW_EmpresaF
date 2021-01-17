package ucab.dsw.entidades;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "marca")
public class Marca extends EntidadBase {

  @Id
  @Column( name = "codigo_marca" )
  @GeneratedValue( strategy = GenerationType.IDENTITY )
  private long _id;

  @Column(name = "nombre_marca")
  private String _nombreMarca;

  @Column(name = "tipo_marca")
  private String _tipoMarca;

  @Column(name = "capacidad")
  private Double _capacidad;

  @Column(name = "unidad")
  private String _unidad;

  @Column(name = "estado")
  private String _estado;

  @ManyToOne
  @JoinColumn(name = "fk_subcategoria")
  private Subcategoria _subcategoria;

  public Marca(long _id) {
    this._id = _id;
  }

  public Marca() {

  }

  public long get_id() {
    return _id;
  }

  public void set_id(long _id) {
    this._id = _id;
  }

  public String get_nombreMarca() {
    return _nombreMarca;
  }

  public void set_nombreMarca(String _nombreMarca) {
    this._nombreMarca = _nombreMarca;
  }

  public String get_tipoMarca() {
    return _tipoMarca;
  }

  public void set_tipoMarca(String _tipoMarca) {
    this._tipoMarca = _tipoMarca;
  }

  public Double get_capacidad() {
    return _capacidad;
  }

  public void set_capacidad(Double _capacidad) {
    this._capacidad = _capacidad;
  }

  public String get_unidad() {
    return _unidad;
  }

  public void set_unidad(String _unidad) {
    this._unidad = _unidad;
  }

  public Subcategoria get_subcategoria() {
    return _subcategoria;
  }

  public void set_subcategoria(Subcategoria _subcategoria) {
    this._subcategoria = _subcategoria;
  }

  public String get_estado() {
    return _estado;
  }

  public void set_estado(String _estado) {
    this._estado = _estado;
  }
}
