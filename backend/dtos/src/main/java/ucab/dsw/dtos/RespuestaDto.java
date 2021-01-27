package ucab.dsw.dtos;

import java.util.Date;
import java.util.List;

public class RespuestaDto extends  DtoBase{

  private Date fecha;

  private String descripcion;

  private Integer rango;

  private EncuestadoDto encuestado;

  private PreguntaEncuestaDto preguntaEncuesta;

  private List<OpcionDto> opciones;

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

  public void setRango(Integer rango) {
    this.rango = rango;
  }

  public Integer getRango() {
    return rango;
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

  public List<OpcionDto> getOpciones() {
    return opciones;
  }

  public void setOpciones(List<OpcionDto> opciones) {
    this.opciones = opciones;
  }
}
