package ucab.dsw.entidades;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "solicitud_estudio")
@NamedQueries({
  @NamedQuery(name = "getSolicitudesByCaracteristicas", query = "select sol from SolicitudEstudio sol where sol._cliente =:clienteId and sol._marca=:marcaId and  sol._edadInicial =:edadInicial and sol._edadfinal =:edadFinal and sol._parroquia=:parroquiaId and sol._nivelSocioeconomico =:nivelSocioeconomico and sol._genero=:genero and sol._estado<>'solicitado'"),
  @NamedQuery(name = "getSolicitudesPendientesByAdmin", query = "select sol from SolicitudEstudio sol where sol._administrador =:administradorId")
})
public class SolicitudEstudio extends EntidadBase {

  @Id
  @Column( name = "codigo_soli_estu" )
  @GeneratedValue( strategy = GenerationType.IDENTITY )
  private long _id;

  @Column(name = "estado")
  private String _estado;

  @Column(name = "edad_inicial")
  private Integer _edadInicial;

  @Column(name = "edad_final")
  private Integer _edadfinal;

  @Column(name = "genero")
  private String _genero;

  @ManyToOne
  @JoinColumn(name = "fk_parroquia")
  private Parroquia _parroquia;

  @ManyToOne
  @JoinColumn(name = "fk_marca")
  private Marca _marca;

  @ManyToOne
  @JoinColumn(name = "fk_estudio")
  private Estudio _estudio;

  @ManyToOne
  @JoinColumn(name = "fk_cliente")
  private Usuario _cliente;

  @ManyToOne
  @JoinColumn(name = "fk_analista")
  private Usuario _analista;

  @ManyToOne
  @JoinColumn(name = "fk_administrador")
  private Usuario _administrador;

  @ManyToOne
  @JoinColumn(name = "fk_nivel_socio")
  private NivelSocioeconomico _nivelSocioeconomico;

  @OneToMany(mappedBy = "_solicitudEstudio", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  private List<Muestra> _muestras;

  public SolicitudEstudio(long _id) {
    this._id = _id;
  }

  public SolicitudEstudio() {
  }

  public long get_id() {
    return _id;
  }

  public void set_id(long _id) {
    this._id = _id;
  }

  public String get_estado() {
    return _estado;
  }

  public void set_estado(String _estado) {
    this._estado = _estado;
  }

  public Estudio get_estudio() {
    return _estudio;
  }

  public void set_estudio(Estudio _estudio) {
    this._estudio = _estudio;
  }


  public Usuario get_analista() {
    return _analista;
  }

  public void set_analista(Usuario _analista) {
    this._analista = _analista;
  }

  public Usuario get_administrador() {
    return _administrador;
  }

  public void set_administrador(Usuario _administrador) {
    this._administrador = _administrador;
  }

  public Integer get_edadInicial() {
    return _edadInicial;
  }

  public void set_edadInicial(Integer _edadInicial) {
    this._edadInicial = _edadInicial;
  }

  public Integer get_edadfinal() {
    return _edadfinal;
  }

  public void set_edadfinal(Integer _edadfinal) {
    this._edadfinal = _edadfinal;
  }

  public String get_genero() {
    return _genero;
  }

  public void set_genero(String _genero) {
    this._genero = _genero;
  }

  public Parroquia get_parroquia() {
    return _parroquia;
  }

  public void set_parroquia(Parroquia _parroquia) {
    this._parroquia = _parroquia;
  }

  public Marca get_marca() {
    return _marca;
  }

  public void set_marca(Marca _marca) {
    this._marca = _marca;
  }

  public Usuario get_cliente() {
    return _cliente;
  }

  public void set_cliente(Usuario _cliente) {
    this._cliente = _cliente;
  }

  public NivelSocioeconomico get_nivelSocioeconomico() {
    return _nivelSocioeconomico;
  }

  public void set_nivelSocioeconomico(NivelSocioeconomico _nivelSocioeconomico) {
    this._nivelSocioeconomico = _nivelSocioeconomico;
  }

  public List<Muestra> get_muestras() {
    return _muestras;
  }

  public void set_muestras(List<Muestra> _muestras) {
    this._muestras = _muestras;
  }
}
