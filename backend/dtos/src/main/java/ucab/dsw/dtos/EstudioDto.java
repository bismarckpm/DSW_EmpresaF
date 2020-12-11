package ucab.dsw.dtos;

import java.util.Date;

public class EstudioDto extends  DtoBase{

  private String estado;

  private String resultado;

  private Date fechaInicio;

  private Date fechaFin;

  private EncuestadoDto encuesta;

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

  public EncuestadoDto getEncuesta() {
    return encuesta;
  }

  public void setEncuesta(EncuestadoDto encuesta) {
    this.encuesta = encuesta;
  }
}
