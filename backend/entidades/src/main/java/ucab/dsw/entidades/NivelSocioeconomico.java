package ucab.dsw.entidades;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "nivel_socioeconomico")
public class NivelSocioeconomico {

  @Id
  @Column( name = "codigo_nivel_socio" )
  @GeneratedValue( strategy = GenerationType.IDENTITY )
  private long _id;

  @Column(name = "tipo")
  private String _tipo;

  /*@OneToMany(mappedBy = "_nivelSocioeconomico", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  private List<Encuestado> _encuestados;*/

  public NivelSocioeconomico(long _id) {
    this._id = _id;
  }

  public NivelSocioeconomico() {
  }

  public long get_id() {
    return _id;
  }

  public void set_id(long _id) {
    this._id = _id;
  }

  public String getTipo() {
    return _tipo;
  }

  public void setTipo(String _tipo) {
    this._tipo = _tipo;
  }
}
