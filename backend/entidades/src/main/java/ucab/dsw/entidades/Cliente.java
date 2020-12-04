package ucab.dsw.entidades;

import javax.persistence.*;

@Entity
@Table(name = "cliente")
public class Cliente extends EntidadBase
{
  @Column(name = "nombre")
  private String _nombre;

  @OneToOne(fetch = FetchType.LAZY, mappedBy = "_cliente")
  private Usuario _usuario;

  public Cliente(long id) {
    super(id);
  }

  public Cliente() {

  }

  public String getNombre() {
    return _nombre;
  }

  public void setNombre(String nombre) {
    this._nombre = nombre;
  }

  public Usuario getUsuario() {
    return _usuario;
  }

  public void setUsuario(Usuario usuario) {
    this._usuario = usuario;
  }

}
