package ucab.dsw.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Respuesta implements Serializable{
    
    // Constructors
    
    public Respuesta() {
    }
    
    public Respuesta(long id) {
        _id = id;
    }
    
    // Properties
    
    @Id
    @Column( name = "codigo_respuesta" )
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long _id;
    
    @Column(name = "fecha", nullable = false)
    private Date _fecha;
    
    @Column(name = "descripcion")
    private String _descripcion;
    
    @Column(name = "rango")
    private float _rango;
    
    @ManyToOne
    @JoinColumn(name = "fk_pregunta_encuesta", nullable = false)
    private PreguntaEncuesta _preguntaEncuesta;
    
    @ManyToOne
    @JoinColumn(name = "fk_encuestado", nullable = false)
    private Encuestado _encuestado;
    
    @ManyToOne
    @JoinColumn(name = "fk_opcion")
    private Opcion _opcion;

    
    // Getters & Setters
    
    public long getId() {
        return _id;
    }

    public void setId(long _id) {
        this._id = _id;
    }

    public Date getFecha() {
        return _fecha;
    }

    public void setFecha(Date _fecha) {
        this._fecha = _fecha;
    }

    public String getDescripcion() {
        return _descripcion;
    }

    public void setDescripcion(String _descripcion) {
        this._descripcion = _descripcion;
    }

    public float getRango() {
        return _rango;
    }

    public void setRango(float _rango) {
        this._rango = _rango;
    }

    public PreguntaEncuesta getPreguntaEncuesta() {
        return _preguntaEncuesta;
    }

    public void setPreguntaEncuesta(PreguntaEncuesta _preguntaEncuesta) {
        this._preguntaEncuesta = _preguntaEncuesta;
    }

    public Encuestado getEncuestado() {
        return _encuestado;
    }

    public void setEncuestado(Encuestado _encuestado) {
        this._encuestado = _encuestado;
    }

    public Opcion getOpcion() {
        return _opcion;
    }

    public void setOpcion(Opcion _opcion) {
        this._opcion = _opcion;
    }
    
}
