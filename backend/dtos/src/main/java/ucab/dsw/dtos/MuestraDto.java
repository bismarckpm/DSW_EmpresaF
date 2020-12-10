package ucab.dsw.dtos;

public class MuestraDto extends DtoBase{


  private EncuestadoDto encuestado;


  private SolicitudEstudioDto solicitudEstudio;

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
}
