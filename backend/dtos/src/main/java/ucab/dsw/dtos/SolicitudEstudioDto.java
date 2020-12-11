package ucab.dsw.dtos;

public class SolicitudEstudioDto extends DtoBase{

  private String estado;

  private Integer edadInicial;

  private Integer edadfinal;

  private String genero;

  private ParroquiaDto parroquia;

  private MarcaDto marca;

  private EstudioDto estudio;

  private UsuarioDto cliente;

  private UsuarioDto analista;

  private UsuarioDto administrador;

  private NivelSocioeconomicoDto nivelSocioeconomico;

  public SolicitudEstudioDto(long id) throws Exception {
    super(id);
  }

  public SolicitudEstudioDto() {
  }

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  public Integer getEdadInicial() {
    return edadInicial;
  }

  public void setEdadInicial(Integer edadInicial) {
    this.edadInicial = edadInicial;
  }

  public Integer getEdadfinal() {
    return edadfinal;
  }

  public void setEdadfinal(Integer edadfinal) {
    this.edadfinal = edadfinal;
  }

  public String getGenero() {
    return genero;
  }

  public void setGenero(String genero) {
    this.genero = genero;
  }

  public ParroquiaDto getParroquia() {
    return parroquia;
  }

  public void setParroquia(ParroquiaDto parroquia) {
    this.parroquia = parroquia;
  }

  public MarcaDto getMarca() {
    return marca;
  }

  public void setMarca(MarcaDto marca) {
    this.marca = marca;
  }

  public EstudioDto getEstudio() {
    return estudio;
  }

  public void setEstudio(EstudioDto estudio) {
    this.estudio = estudio;
  }

  public UsuarioDto getCliente() {
    return cliente;
  }

  public void setCliente(UsuarioDto cliente) {
    this.cliente = cliente;
  }

  public UsuarioDto getAnalista() {
    return analista;
  }

  public void setAnalista(UsuarioDto analista) {
    this.analista = analista;
  }

  public UsuarioDto getAdministrador() {
    return administrador;
  }

  public void setAdministrador(UsuarioDto administrador) {
    this.administrador = administrador;
  }

  public NivelSocioeconomicoDto getNivelSocioeconomico() {
    return nivelSocioeconomico;
  }

  public void setNivelSocioeconomico(NivelSocioeconomicoDto nivelSocioeconomico) {
    this.nivelSocioeconomico = nivelSocioeconomico;
  }
}
