package ucab.dsw.dtos;

import java.util.List;

public class MuestraDto extends DtoBase{


  private EncuestadoDto encuestado;

  private List<EncuestadoDto> encuestados;

  private SolicitudEstudioDto solicitudEstudio;

  private String estado;

  public MuestraDto(long id) throws Exception {
    super(id);
  }

  public MuestraDto() {
  }

  public EncuestadoDto getEncuestado() {
    return encuestado;
  }

  public void setEncuestado(EncuestadoDto encuestado) {
    this.encuestado = encuestado;
  }

  public SolicitudEstudioDto getSolicitudEstudio() {
    return solicitudEstudio;
  }

  public void setSolicitudEstudio(SolicitudEstudioDto solicitudEstudio) {
    this.solicitudEstudio = solicitudEstudio;
  }

  public List<EncuestadoDto> getEncuestados() {
    return encuestados;
  }

  public void setEncuestados(List<EncuestadoDto> encuestados) {
    this.encuestados = encuestados;
  }

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }
}
