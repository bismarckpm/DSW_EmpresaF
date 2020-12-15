package ucab.dsw.entidades;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categoria")
public class Categoria extends EntidadBase {

  @Id
  @Column( name = "codigo_categoria" )
  @GeneratedValue( strategy = GenerationType.IDENTITY )
  private long _id;

  @Column(name = "nombre_categoria")
  private String _nombreCategoria;

  @Column(name = "estado")
  private String _estado;

  @OneToMany(mappedBy = "_categoria", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  private List<Subcategoria> _subcategorias;

  public Categoria(long _id) {
    this._id = _id;
  }

  public Categoria() {

  }

  public long get_id() {
    return _id;
  }

  public void set_id(long _id) {
    this._id = _id;
  }

  public String get_nombreCategoria() {
    return _nombreCategoria;
  }

  public void set_nombreCategoria(String _nombreCategoria) {
    this._nombreCategoria = _nombreCategoria;
  }

  public String get_estado() {
    return _estado;
  }

  public void set_estado(String _estado) {
    this._estado = _estado;

  }

  /* public List<Subcategoria> get_subcategorias() {
    return _subcategorias;
  }

  public void set_subcategorias(List<Subcategoria> _subcategorias) {
    this._subcategorias = _subcategorias;
  }*/
}
