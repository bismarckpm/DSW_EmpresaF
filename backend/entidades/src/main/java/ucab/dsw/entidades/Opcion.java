package ucab.dsw.entidades;

import java.util.ArrayList;
import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;
import javax.json.Json;
import javax.json.JsonObject;

@Entity
@Table(name = "opcion")
public class Opcion extends EntidadBase {

    @Id
    @Column(name = "codigo_opcion")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long _id;

    @Column(name = "descripcion")
    private String _descripcion;

    @OneToMany(mappedBy = "_opcion", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<PreguntaOpcion> _preguntasOpciones;

    @OneToMany(mappedBy = "_opcion", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<RespuestaOpcion> _respuestasOpciones;

    public Opcion(long _id) {
        this._id = _id;
    }

    public Opcion() {
    }

    @Override
    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String get_descripcion() {
        return _descripcion;
    }

    public void set_descripcion(String _descripcion) {
        this._descripcion = _descripcion;
    }

    public List<PreguntaOpcion> get_preguntasOpciones() {
        return _preguntasOpciones;
    }

    public void set_preguntasOpciones(List<PreguntaOpcion> _preguntasOpciones) {
        this._preguntasOpciones = _preguntasOpciones;
    }

    //
    public List<Pregunta> getPreguntas() {
        return get_preguntasOpciones()
                .stream()
                .map(preguntaOpcion -> preguntaOpcion.get_pregunta())
                .collect(Collectors.toList());
    }

    public void setPreguntas(List<Pregunta> preguntas) {
        List<PreguntaOpcion> preguntasOpciones = new ArrayList<>();

        for (Pregunta pregunta : preguntas) {
            PreguntaOpcion preguntaOpcion = new PreguntaOpcion();
            preguntaOpcion.set_pregunta(pregunta);
            preguntaOpcion.set_opcion(this);

            preguntasOpciones.add(preguntaOpcion);

        }
        set_preguntasOpciones(preguntasOpciones);
    }

    public List<RespuestaOpcion> get_respuestasOpciones() {
        return _respuestasOpciones;
    }

    public void set_respuestasOpciones(List<RespuestaOpcion> _respuestasOpciones) {
        this._respuestasOpciones = _respuestasOpciones;
    }

    public List<Respuesta> get_respuestas() {
        return get_respuestasOpciones()
                .stream()
                .map(respuestaOpcion -> respuestaOpcion.get_respuesta())
                .collect(Collectors.toList());
    }

    public JsonObject toJson() {
        JsonObject preguntaJson = null;
        try {
            preguntaJson = Json.createObjectBuilder()
                    .add("idOpcion", get_id())
                    .add("descripcion", get_descripcion())
                    //                    .add("preguntas", Json.createArrayBuilder(getPreguntas()))
                    .build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return preguntaJson;
    }
}
