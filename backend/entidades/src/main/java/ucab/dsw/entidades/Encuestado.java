package ucab.dsw.entidades;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "encuestado")
@NamedQueries({
  @NamedQuery(name = "getUsersMuestraByGenero", query = "select e from Encuestado e where e._parroquia=:parroquiaId and e._nivelSocioeconomico=:nivelId and e._genero=:genero"),
  @NamedQuery(name = "getUsersMuestra", query = "select e from Encuestado e where e._parroquia=:parroquiaId and e._nivelSocioeconomico=:nivelId")
})
public class Encuestado extends EntidadBase{

  @Id
  @Column( name = "codigo_encuestado" )
  @GeneratedValue( strategy = GenerationType.IDENTITY )
  private long _id;

  @Column( name = "numero_identificacion")
  private String _numeroIdentificacion;

  @Column(name = "primer_nombre")
  private String _primerNombre;

  @Column(name = "segundo_nombre")
  private String _segundoNombre;

  @Column(name = "primer_apellido")
  private String _primerApellido;

  @Column(name = "segundo_apellido")
  private String _segundoApellido;

  @Column(name = "direccion_complemento")
  private String _direccionComplemento;

  @Column(name = "fecha_nacimiento")
  @Temporal(value = TemporalType.DATE)
  private Date _fechaNacimiento;

  @Column(name = "genero")
  private String _genero;

  @Column(name = "estado_civil")
  private String _estadoCivil;

  @Column(name = "ocupacion")
  private String _ocupacion;

  @Column(name = "estado")
  private String _estado;


  @ManyToOne
  @JoinColumn( name = "fk_parroquia")
  private Parroquia _parroquia;

  @ManyToOne
  @JoinColumn( name = "fk_nivel_estudio")
  private NivelEstudio _nivelEstudio;

  @OneToOne(mappedBy = "_encuestado")
  private Usuario _usuario;

  @OneToMany(mappedBy = "_encuestado", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  private List<Muestra> _muestras;

  @ManyToOne
  @JoinColumn( name = "fk_nivel_socio")
  private NivelSocioeconomico _nivelSocioeconomico;

  @OneToMany(mappedBy = "_encuestado", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  private List<Telefono> _telefonos;


  public Encuestado(long _id) {
    this._id = _id;
  }

  public Encuestado() {
  }

  public long get_id() {
    return _id;
  }

  public void set_id(long _id) {
    this._id = _id;
  }

  public String get_numeroIdentificacion() {
    return _numeroIdentificacion;
  }

  public void set_numeroIdentificacion(String _numeroIdentificacion) {
    this._numeroIdentificacion = _numeroIdentificacion;
  }

  public String get_primerNombre() {
    return _primerNombre;
  }

  public void set_primerNombre(String _primerNombre) {
    this._primerNombre = _primerNombre;
  }

  public String get_segundoNombre() {
    return _segundoNombre;
  }

  public void set_segundoNombre(String _segundoNombre) {
    this._segundoNombre = _segundoNombre;
  }

  public String get_primerApellido() {
    return _primerApellido;
  }

  public void set_primerApellido(String _primerApellido) {
    this._primerApellido = _primerApellido;
  }

  public String get_segundoApellido() {
    return _segundoApellido;
  }

  public void set_segundoApellido(String _segundoApellido) {
    this._segundoApellido = _segundoApellido;
  }

  public String get_direccionComplemento() {
    return _direccionComplemento;
  }

  public void set_direccionComplemento(String _direccionComplemento) {
    this._direccionComplemento = _direccionComplemento;
  }

  public Date get_fechaNacimiento() {
    return _fechaNacimiento;
  }

  public void set_fechaNacimiento(Date _fechaNacimiento) {
    this._fechaNacimiento = _fechaNacimiento;
  }

  public String get_genero() {
    return _genero;
  }

  public void set_genero(String _genero) {
    this._genero = _genero;
  }

  public String get_estadoCivil() {
    return _estadoCivil;
  }

  public void set_estadoCivil(String _estadoCivil) {
    this._estadoCivil = _estadoCivil;
  }

  public String get_ocupacion() {
    return _ocupacion;
  }

  public void set_ocupacion(String _ocupacion) {
    this._ocupacion = _ocupacion;
  }

  public String get_estado() {
    return _estado;
  }

  public void set_estado(String _estado) {
    this._estado = _estado;
  }

  public Parroquia get_parroquia() {
    return _parroquia;
  }

  public void set_parroquia(Parroquia _parroquia) {
    this._parroquia = _parroquia;
  }

  public NivelEstudio get_nivelEstudio() {
    return _nivelEstudio;
  }

  public void set_nivelEstudio(NivelEstudio _nivelEstudio) {
    this._nivelEstudio = _nivelEstudio;
  }

  public List<Muestra> get_muestras() {
    return _muestras;
  }

  public void set_muestras(List<Muestra> _muestras) {
    this._muestras = _muestras;
  }

  public Usuario get_usuario() {
    return _usuario;
  }

  public void set_usuario(Usuario _usuario) {
    this._usuario = _usuario;
  }

  public NivelSocioeconomico get_nivelSocioeconomico() {
    return _nivelSocioeconomico;
  }

  public void set_nivelSocioeconomico(NivelSocioeconomico _nivelSocioeconomico) {
    this._nivelSocioeconomico = _nivelSocioeconomico;
  }

  public List<Telefono> get_telefonos() {
    return _telefonos;
  }

  public void set_telefonos(List<Telefono> _telefonos) {
    this._telefonos = _telefonos;
  }
}
