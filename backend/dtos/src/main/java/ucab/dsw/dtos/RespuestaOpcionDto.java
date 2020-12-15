package ucab.dsw.dtos;

public class RespuestaOpcionDto extends DtoBase{
    
    RespuestaDto respuesta;
    
    RespuestaDto opcion;
    
    public RespuestaOpcionDto(){
        
    }

    public RespuestaDto getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(RespuestaDto respuesta) {
        this.respuesta = respuesta;
    }

    public RespuestaDto getOpcion() {
        return opcion;
    }

    public void setOpcion(RespuestaDto opcion) {
        this.opcion = opcion;
    }
    
}
