package ucab.dsw.entidades;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "subcategoria")
public class Subcategoria extends EntidadBase{

  @Id
  @Column( name = "codigo_subcategoria" )
  @GeneratedValue( strategy = GenerationType.IDENTITY )
  private long _id;

  @Column(name = "nombre_subcategoria")
  private String _nombreSubcategoria;

  @Column(name = "estado")
  private String _estado;

  @ManyToOne
  @JoinColumn(name = "fk_categoria")
  private Categoria _categoria;

  @OneToMany(mappedBy = "_subcategoria", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  private List<Marca> _marcas;

  @OneToMany(mappedBy = "_subcategoria", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  private List<Encuesta> _encuestas;

  @OneToMany(mappedBy = "_subcategoria", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  private List<SolicitudEstudio> _solicitudesEstudio;

  public Subcategoria(long _id) {
    this._id = _id;
  }

  public Subcategoria() {

  }

  public long get_id() {
    return _id;
  }

  public void set_id(long _id) {
    this._id = _id;
  }

  public String get_nombreSubcategoria() {
    return _nombreSubcategoria;
  }

  public void set_nombreSubcategoria(String _nombreSubcategoria) {
    this._nombreSubcategoria = _nombreSubcategoria;
  }

  public String get_estado() {
    return _estado;
  }

  public void set_estado(String _estado) {
    this._estado = _estado;
  }

  public Categoria get_categoria() {
    return _categoria;
  }

  public void set_categoria(Categoria _categoria) {
    this._categoria = _categoria;
  }

  public List<Marca> get_marcas() {
    return _marcas;
  }

  public void set_marcas(List<Marca> _marcas) {
    this._marcas = _marcas;
  }

  public List<Encuesta> get_encuestas() {
    return _encuestas;
  }

  public void set_encuestas(List<Encuesta> _encuestas) {
    this._encuestas = _encuestas;
  }

  public List<SolicitudEstudio> get_solicitudesEstudio() {
    return _solicitudesEstudio;
  }

  public void set_solicitudesEstudio(List<SolicitudEstudio> _solicitudesEstudio) {
    this._solicitudesEstudio = _solicitudesEstudio;
  }
}
