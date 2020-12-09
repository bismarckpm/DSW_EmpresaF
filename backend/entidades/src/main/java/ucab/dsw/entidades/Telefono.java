package ucab.dsw.entidades;

import javax.persistence.*;

@Entity
@Table(name = "telefono")
public class Telefono {

  @Id
  @Column( name = "codigo_telefono" )
  @GeneratedValue( strategy = GenerationType.IDENTITY )
  private long _id;

  @Column(name = "codigo_area")
  private String _codigoArea;

  @Column(name = "numero_telefono")
  private String _numeroTelefono;

  @ManyToOne
  @JoinColumn(name = "fk_encuestado")
  private Encuestado _encuestado;

  public Telefono(long _id) {
    this._id = _id;
  }

  public Telefono() {
  }

  public Telefono(String _codigoArea, String _numeroTelefono) {
    this._codigoArea = _codigoArea;
    this._numeroTelefono = _numeroTelefono;
  }

  public long get_id() {
    return _id;
  }

  public void set_id(long _id) {
    this._id = _id;
  }

  public String get_codigoArea() {
    return _codigoArea;
  }

  public void set_codigoArea(String _codigoArea) {
    this._codigoArea = _codigoArea;
  }

  public String get_numeroTelefono() {
    return _numeroTelefono;
  }

  public void set_numeroTelefono(String _numeroTelefono) {
    this._numeroTelefono = _numeroTelefono;
  }

  public Encuestado get_encuestado() {
    return _encuestado;
  }

  public void set_encuestado(Encuestado _encuestado) {
    this._encuestado = _encuestado;
  }
}
