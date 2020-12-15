package ucab.dsw.dtos;

import java.util.Date;

public class EstudioDto extends  DtoBase{

  private String estado;

  private String nombreEstudio;

  private String resultado;

  private Date fechaInicio;

  private Date fechaFin;

  private EncuestaDto encuesta;

  private SolicitudEstudioDto solicitudEstudio;

  public EstudioDto(long id) throws Exception {
    super(id);
  }

  public EstudioDto() {
  }

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  public String getNombreEstudio() {
    return nombreEstudio;
  }

  public void setNombreEstudio(String nombreEstudio) {
    this.nombreEstudio = nombreEstudio;
  }

  public String getResultado() {
    return resultado;
  }

  public void setResultado(String resultado) {
    this.resultado = resultado;
  }

  public Date getFechaInicio() {
    return fechaInicio;
  }

  public void setFechaInicio(Date fechaInicio) {
    this.fechaInicio = fechaInicio;
  }

  public Date getFechaFin() {
    return fechaFin;
  }

  public void setFechaFin(Date fechaFin) {
    this.fechaFin = fechaFin;
  }

  public EncuestaDto getEncuesta() {
    return encuesta;
  }

  public void setEncuesta(EncuestaDto encuesta) {
    this.encuesta = encuesta;
  }

  public SolicitudEstudioDto getSolicitudEstudio() {
    return solicitudEstudio;
  }

  public void setSolicitudEstudio(SolicitudEstudioDto solicitudEstudio) {
    this.solicitudEstudio = solicitudEstudio;
  }
}
