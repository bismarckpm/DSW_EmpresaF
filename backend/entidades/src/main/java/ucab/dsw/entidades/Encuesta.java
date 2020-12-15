package ucab.dsw.entidades;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.ArrayList;

@Entity
@Table(name = "encuesta")
public class Encuesta extends EntidadBase{

    @Id
    @Column(name = "codigo_encuesta")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long _id;

    @ManyToOne
    @JoinColumn(name = "fk_subcategoria")
    private Subcategoria _subcategoria;

    @OneToMany(mappedBy = "_encuesta", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Estudio> _estudios;
    
    @OneToMany(mappedBy = "_encuesta", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<PreguntaEncuesta> _preguntasEncuestas;

    public Encuesta(long _id) {
        this._id = _id;
    }

    public Encuesta() {
    }

    @Override
    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public List<Estudio> get_estudios() {
        return _estudios;
    }

    public void set_estudios(List<Estudio> _estudios) {
        this._estudios = _estudios;
    }
    
    public Subcategoria get_subcategoria() {
        return _subcategoria;
    }

    public void set_subcategoria(Subcategoria _subcategoria) {
        this._subcategoria = _subcategoria;
    }

    public List<PreguntaEncuesta> get_preguntasEncuestas() {
        return _preguntasEncuestas;
    }

    public void set_preguntasEncuestas(List<PreguntaEncuesta> _preguntasEncuestas) {
        this._preguntasEncuestas = _preguntasEncuestas;
    }
    
    public void add_pregunta(Pregunta _pregunta){
        PreguntaEncuesta preguntaEncuesta = new PreguntaEncuesta();
        preguntaEncuesta.set_encuesta(this);
        preguntaEncuesta.set_pregunta(_pregunta);
        this._preguntasEncuestas.add(preguntaEncuesta);
    }

    public List<Pregunta> getPreguntas() {
        return get_preguntasEncuestas()
                .stream()
                .map(preguntaEncuesta -> preguntaEncuesta.get_pregunta())
                .collect(Collectors.toList());
    }

    public JsonObject toJson() {
        JsonObject encuestaJson = JsonObject.EMPTY_JSON_OBJECT;
        try {
            
            List<Pregunta> preguntasList = getPreguntas();
            JsonArrayBuilder preguntasJson = Json.createArrayBuilder();
            
            for (Pregunta pregunta : preguntasList) {
                preguntasJson.add(pregunta.toJson());
            }

            encuestaJson = Json.createObjectBuilder()
                    .add("idEncuesta", get_id())
                    .add("subcategoria", get_subcategoria().get_nombreSubcategoria())
                    .add("preguntas", preguntasJson)
                    .build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return encuestaJson;
    }
    
}
