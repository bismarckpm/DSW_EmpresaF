package ucab.dsw.dtos;

import java.util.Date;

public class EncuestadoDto extends DtoBase{

  private String numeroIdentificacion;

  private String primerNombre;

  private String segundoNombre;

  private String primerApellido;

  private String segundoApellido;

  private String direccionComplemento;

  private Date fechaNacimiento;

  private String genero;

  private String estadoCivil;

  private String ocupacion;

  private ParroquiaDto parroquia;

  private NivelEstudioDto nivelEstudio;

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

  public Date getFechaNacimiento() {
    return fechaNacimiento;
  }

  public void setFechaNacimiento(Date fechaNacimiento) {
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
}
