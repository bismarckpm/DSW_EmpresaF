package ucab.dsw.entidades;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "parroquia")
public class Parroquia {

  @Id
  @Column( name = "codigo_parroquia" )
  @GeneratedValue( strategy = GenerationType.IDENTITY )
  private long _id;

  @Column( name = "nombre_parroquia")
  private String _nombreParroquia;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn( name = "fk_muncipio")
  private Municipio _municipio;

  @OneToMany( mappedBy = "_parroquia", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST )
  private List<Encuestado> _encuestados;

  public Parroquia(long _id) {
    this._id = _id;
  }

  public Parroquia() {

  }

  public long get_id() {
    return _id;
  }

  public void set_id(long _id) {
    this._id = _id;
  }

  public String get_nombreParroquia() {
    return _nombreParroquia;
  }

  public void set_nombreParroquia(String _nombreParroquia) {
    this._nombreParroquia = _nombreParroquia;
  }

  public Municipio get_municipio() {
    return _municipio;
  }

  public void set_municipio(Municipio _municipio) {
    this._municipio = _municipio;
  }

  public List<Encuestado> get_encuestados() {
    return _encuestados;
  }

  public void set_encuestados(List<Encuestado> _encuestados) {
    this._encuestados = _encuestados;
  }
}
