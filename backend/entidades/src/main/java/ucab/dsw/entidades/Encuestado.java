package ucab.dsw.entidades;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "encuestado")
public class Encuestado extends EntidadBase{

  //Faltan atributos, getters y setters
  @OneToOne(fetch = FetchType.LAZY, mappedBy = "_encuestado")
  private Usuario usuario;

  public Encuestado(long id) {
    super(id);
  }

  public Encuestado() {
  }
}
