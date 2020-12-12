package ucab.dsw.dtos;

import java.util.Date;

public class RespuestaDto extends  DtoBase{

  private Date fecha;

  private String descripcion;

  private String rango;

  private EncuestadoDto encuestado;

  private PreguntaEncuestaDto preguntaEncuesta;

  private OpcionDto opcion;

  public RespuestaDto(long id) throws Exception {
    super(id);
  }

  public RespuestaDto() {
  }

  public Date getFecha() {
    return fecha;
  }

  public void setFecha(Date fecha) {
    this.fecha = fecha;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public String getRango() {
    return rango;
  }

  public void setRango(String rango) {
    this.rango = rango;
  }

  public EncuestadoDto getEncuestado() {
    return encuestado;
  }

  public void setEncuestado(EncuestadoDto encuestado) {
    this.encuestado = encuestado;
  }

  public PreguntaEncuestaDto getPreguntaEncuesta() {
    return preguntaEncuesta;
  }

  public void setPreguntaEncuesta(PreguntaEncuestaDto preguntaEncuesta) {
    this.preguntaEncuesta = preguntaEncuesta;
  }

  public OpcionDto getOpcion() {
    return opcion;
  }

  public void setOpcion(OpcionDto opcion) {
    this.opcion = opcion;
  }
}
