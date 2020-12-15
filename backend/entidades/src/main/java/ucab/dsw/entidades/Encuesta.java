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
    private List<PreguntaEncuesta> _preguntasEncuestas;

    public Encuesta(long _id) {
        this._id = _id;
    }

    public Encuesta() {
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
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

    public List<Pregunta> getPreguntas() {
        return get_preguntasEncuestas()
                .stream()
                .map(preguntaEncuesta -> preguntaEncuesta.get_pregunta())
                .collect(Collectors.toList());
    }

    public JsonObject toJson() {
        JsonObject encuestaJson = JsonObject.EMPTY_JSON_OBJECT;
        try {
            List<JsonObject> preguntasList = getPreguntas()
                    .stream()
                    .map(pregunta -> pregunta.toJson())
                    .collect(Collectors.toList());
            JsonArrayBuilder preguntasJson = Json.createArrayBuilder(preguntasList);

            encuestaJson = Json.createObjectBuilder()
                    .add("idEncuesta", get_id())
                    .add("subcategoria", get_subcategoria().get_nombreSubcategoria().toString())
                    .add("preguntas", preguntasJson)
                    .build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return encuestaJson;
    }
    
}
