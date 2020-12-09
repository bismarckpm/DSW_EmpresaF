package ucab.dsw.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

public class Opcion implements Serializable{
    
    // Constructors
    public Opcion() {
    }
    
    public Opcion(long id) {
        _id = id;
    }
    
    
    // Properties
    
    @Id
    @Column( name = "codigo_opcion" )
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long _id;
    
    @Column(name = "descripcion")
    private String _descripcion;
    
    @OneToMany(mappedBy = "_opcion", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<PreguntaOpcion> _preguntaOpcion;
    
    @OneToMany(mappedBy = "_opcion", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Respuesta> _respuestas;
    
    
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

    public List<PreguntaOpcion> getPreguntaOpcion() {
        return _preguntaOpcion;
    }

    public void setPreguntaOpcion(List<PreguntaOpcion> _preguntaOpcion) {
        this._preguntaOpcion = _preguntaOpcion;
    }

    public List<Respuesta> getRespuestas() {
        return _respuestas;
    }

    public void setRespuestas(List<Respuesta> _respuestas) {
        this._respuestas = _respuestas;
    }
    
}
