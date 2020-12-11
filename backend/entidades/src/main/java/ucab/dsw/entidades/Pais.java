package ucab.dsw.entidades;

import javax.persistence.*;

@Entity
@Table(name = "pais")
public class Pais{

  @Id
  @Column( name = "codigo_pais" )
  @GeneratedValue( strategy = GenerationType.IDENTITY )
  private long _id;

  @Column( name = "nombre_pais")
  private String _nombre;

  public Pais(long _id) {
    this._id = _id;
  }

  public Pais() {

  }

  public long get_id() {
    return _id;
  }

  public void set_id(long _id) {
    this._id = _id;
  }

  public String get_nombre() {
    return _nombre;
  }

  public void set_nombre(String _nombre) {
    this._nombre = _nombre;
  }
}
