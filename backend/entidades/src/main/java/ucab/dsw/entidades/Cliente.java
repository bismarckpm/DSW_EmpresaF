package ucab.dsw.entidades;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cliente")
public class Cliente extends EntidadBase
{

  @Id
  @Column( name = "codigo_cliente" )
  @GeneratedValue( strategy = GenerationType.IDENTITY )
  private long _id;

  @Column(name = "nombre")
  private String _nombre;

  @Column(name = "estado")
  private String _estado;

  public Cliente(long _id) {
    this._id = _id;
  }

  public Cliente() {

  }

  public long get_id() {
    return _id;
  }

  public void set_id(long _id) {
    this._id = _id;
  }

  public String getNombre() {
    return _nombre;
  }

  public void setNombre(String nombre) {
    this._nombre = nombre;
  }

  public String get_nombre() {
    return _nombre;
  }

  public void set_nombre(String _nombre) {
    this._nombre = _nombre;
  }

  public String get_estado() {
    return _estado;
  }

  public void set_estado(String _estado) {
    this._estado = _estado;
  }
}
