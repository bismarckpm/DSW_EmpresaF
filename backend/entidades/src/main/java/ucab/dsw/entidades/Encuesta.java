package ucab.dsw.entidades;

import javax.persistence.*;
import java.util.List;

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

  @OneToMany(mappedBy = "_encuesta", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  private List<PreguntaEncuesta> _preguntasEncuestas;

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

  public List<PreguntaEncuesta> get_preguntasEncuestas() {
    return _preguntasEncuestas;
  }

  public void set_preguntasEncuestas(List<PreguntaEncuesta> _preguntasEncuestas) {
    this._preguntasEncuestas = _preguntasEncuestas;
  }

}
