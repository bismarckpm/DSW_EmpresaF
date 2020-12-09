package ucab.dsw.dtos;

import java.util.Date;

public class RespuestaDto extends DtoBase{
    // Constructors
    
    public RespuestaDto() {
    }
    
    public RespuestaDto(long id) throws Exception {
      super(id);
    }
    
    
    // Properties 
    
    private Date fecha;
    
    private String descripcion;
    
    private float rango;
    
    private PreguntaEncuestaDto preguntaEncuesta;
    
    private EncuestadoDto encuestado;
    
    private OpcionDto opcion;
    
    
    // Getters & Setters

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

    public float getRango() {
        return rango;
    }

    public void setRango(float rango) {
        this.rango = rango;
    }

    public PreguntaEncuestaDto getPreguntaEncuesta() {
        return preguntaEncuesta;
    }

    public void setPreguntaEncuesta(PreguntaEncuestaDto preguntaEncuesta) {
        this.preguntaEncuesta = preguntaEncuesta;
    }

    public EncuestadoDto getEncuestado() {
        return encuestado;
    }

    public void setEncuestado(EncuestadoDto encuestado) {
        this.encuestado = encuestado;
    }

    public OpcionDto getOpcion() {
        return opcion;
    }

    public void setOpcion(OpcionDto opcion) {
        this.opcion = opcion;
    }
    
    
}
