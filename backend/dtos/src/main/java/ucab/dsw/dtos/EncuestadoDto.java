package ucab.dsw.dtos;

import ucab.dsw.entidades.Telefono;

import java.util.Date;
import java.util.List;

public class EncuestadoDto extends DtoBase{

  private String numeroIdentificacion;

  private String primerNombre;

  private String segundoNombre;

  private String primerApellido;

  private String segundoApellido;

  private String direccionComplemento;

  private String fechaNacimiento;

  private String genero;

  private String estadoCivil;

  private String ocupacion;

  private ParroquiaDto parroquia;

  private NivelEstudioDto nivelEstudio;

  private NivelSocioeconomicoDto nivelSocioeconomico;

  private List<Telefono> telefonos;

  private List<MuestraDto> muestras;

  public EncuestadoDto(long id) throws Exception {
    super(id);
  }

  public EncuestadoDto() {
  }

  public String getNumeroIdentificacion() {
    return numeroIdentificacion;
  }

  public void setNumeroIdentificacion(String numeroIdentificacion) {
    this.numeroIdentificacion = numeroIdentificacion;
  }

  public String getPrimerNombre() {
    return primerNombre;
  }

  public void setPrimerNombre(String primerNombre) {
    this.primerNombre = primerNombre;
  }

  public String getSegundoNombre() {
    return segundoNombre;
  }

  public void setSegundoNombre(String segundoNombre) {
    this.segundoNombre = segundoNombre;
  }

  public String getPrimerApellido() {
    return primerApellido;
  }

  public void setPrimerApellido(String primerApellido) {
    this.primerApellido = primerApellido;
  }

  public String getSegundoApellido() {
    return segundoApellido;
  }

  public void setSegundoApellido(String segundoApellido) {
    this.segundoApellido = segundoApellido;
  }

  public String getDireccionComplemento() {
    return direccionComplemento;
  }

  public void setDireccionComplemento(String direccionComplemento) {
    this.direccionComplemento = direccionComplemento;
  }

  public String getFechaNacimiento() {
    return fechaNacimiento;
  }

  public void setFechaNacimiento(String fechaNacimiento) {
    this.fechaNacimiento = fechaNacimiento;
  }

  public String getGenero() {
    return genero;
  }

  public void setGenero(String genero) {
    this.genero = genero;
  }

  public String getEstadoCivil() {
    return estadoCivil;
  }

  public void setEstadoCivil(String estadoCivil) {
    this.estadoCivil = estadoCivil;
  }

  public String getOcupacion() {
    return ocupacion;
  }

  public void setOcupacion(String ocupacion) {
    this.ocupacion = ocupacion;
  }

  public ParroquiaDto getParroquia() {
    return parroquia;
  }

  public void setParroquia(ParroquiaDto parroquia) {
    this.parroquia = parroquia;
  }

  public NivelEstudioDto getNivelEstudio() {
    return nivelEstudio;
  }

  public void setNivelEstudio(NivelEstudioDto nivelEstudio) {
    this.nivelEstudio = nivelEstudio;
  }

  public NivelSocioeconomicoDto getNivelSocioeconomico() {
    return nivelSocioeconomico;
  }

  public void setNivelSocioeconomico(NivelSocioeconomicoDto nivelSocioeconomico) {
    this.nivelSocioeconomico = nivelSocioeconomico;
  }

  public List<Telefono> getTelefonos() {
    return telefonos;
  }

  public void setTelefonos(List<Telefono> telefonos) {
    this.telefonos = telefonos;
  }

  public List<MuestraDto> getMuestras() {
    return muestras;
  }

  public void setMuestras(List<MuestraDto> muestras) {
    this.muestras = muestras;
  }
}
