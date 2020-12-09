package ucab.dsw.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "pregunta")
public class Pregunta implements Serializable{
    
    // Constructors
    
    public Pregunta() {
    }
    
    public Pregunta(long id) {
        _id = id;
    }
    
    
    // Properties
    
    @Id
    @Column( name = "codigo_pregunta" )
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long _id;
    
    @Column(name = "descripcion_pregunta", nullable = false)
    private String _descripcion;
    
    @Column(name = "tipo_pregunta", nullable = false)
    private String _tipoPregunta;
    
    @Column(name = "max")
    private float _max;
    
    @Column(name = "min")
    private float _min;
    
    @OneToMany(mappedBy = "_pregunta", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<PreguntaEncuesta> _preguntaEncuesta;
    
    @OneToMany(mappedBy = "_pregunta", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<PreguntaOpcion> _preguntaOpcion;
    
    
    // Getters & Setters

    public long getId() {
        return _id;
    }

    public void setId(long _id) {
        this._id = _id;
    }

    public String getDescripcion() {
        return _descripcion;
    }

    public void setDescripcion(String _descripcion) {
        this._descripcion = _descripcion;
    }

    public String getTipoPregunta() {
        return _tipoPregunta;
    }

    public void setTipoPregunta(String _tipoPregunta) {
        this._tipoPregunta = _tipoPregunta;
    }

    public float getMax() {
        return _max;
    }

    public void setMax(float _max) {
        this._max = _max;
    }

    public float getMin() {
        return _min;
    }

    public void setMin(float _min) {
        this._min = _min;
    }

    public List<PreguntaEncuesta> getPreguntaEncuesta() {
        return _preguntaEncuesta;
    }

    public void setPreguntaEncuesta(List<PreguntaEncuesta> _preguntaEncuesta) {
        this._preguntaEncuesta = _preguntaEncuesta;
    }

    public List<PreguntaOpcion> getPreguntaOpcion() {
        return _preguntaOpcion;
    }

    public void setPreguntaOpcion(List<PreguntaOpcion> _preguntaOpcion) {
        this._preguntaOpcion = _preguntaOpcion;
    }
    
}
