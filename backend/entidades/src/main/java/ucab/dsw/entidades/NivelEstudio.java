package ucab.dsw.entidades;

import javax.persistence.*;

@Entity
@Table(name = "nivel_estudio")
public class NivelEstudio {

  @Id
  @Column( name = "codigo_nive_estu" )
  @GeneratedValue( strategy = GenerationType.IDENTITY )
  private long _id;

  @Column(name = "nombre")
  private String _nombre;

  public NivelEstudio(long _id) {
    this._id = _id;
  }

  public NivelEstudio() {

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
