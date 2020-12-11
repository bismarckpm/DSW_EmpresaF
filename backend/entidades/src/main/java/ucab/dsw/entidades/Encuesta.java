package ucab.dsw.entidades;

import javax.persistence.*;

@Entity
@Table(name = "encuesta")
public class Encuesta {

  @Id
  @Column( name = "codigo_encuesta" )
  @GeneratedValue( strategy = GenerationType.IDENTITY )
  private long _id;

  @ManyToOne
  @JoinColumn(name = "fk_subcategoria")
  private Subcategoria _subcategoria;

  public Encuesta(long _id) {
    this._id = _id;
  }

  public Encuesta() {
  }

  public long get_id() {
    return _id;
  }

  public void set_id(long _id) {
    this._id = _id;
  }

  public Subcategoria get_subcategoria() {
    return _subcategoria;
  }

  public void set_subcategoria(Subcategoria _subcategoria) {
    this._subcategoria = _subcategoria;
  }
}
