package ucab.dsw.entidades;

import javax.persistence.*;

@Entity
@Table(name = "municipio")
public class Municipio {

  @Id
  @Column( name = "codigo_municipio" )
  @GeneratedValue( strategy = GenerationType.IDENTITY )
  private long _id;

  @Column( name = "nombre_municipio")
  private String _nombreMunicipio;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn( name = "fk_pais")
  private Pais _pais;

  public Municipio(long _id) {
    this._id = _id;
  }

  public Municipio() {

  }

  public long get_id() {
    return _id;
  }

  public void set_id(long _id) {
    this._id = _id;
  }

  public String get_nombreMunicipio() {
    return _nombreMunicipio;
  }

  public void set_nombreMunicipio(String _nombreMunicipio) {
    this._nombreMunicipio = _nombreMunicipio;
  }

  public Pais get_pais() {
    return _pais;
  }

  public void set_pais(Pais _pais) {
    this._pais = _pais;
  }
}
