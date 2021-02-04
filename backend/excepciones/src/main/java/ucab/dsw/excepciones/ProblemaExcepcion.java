package ucab.dsw.excepciones;

public class ProblemaExcepcion extends Exception{

  private String mensaje;
  private String mensaje_soporte;

  public ProblemaExcepcion(String mensaje, String mensaje_soporte) {

    super();
    this.mensaje = mensaje;
    this.mensaje_soporte = mensaje_soporte;

  }

  public String getMensaje() {
    return mensaje;
  }

  public void setMensaje(String mensaje) {
    this.mensaje = mensaje;
  }

  public String getMensaje_soporte() {
    return mensaje_soporte;
  }

  public void setMensaje_soporte(String mensaje_soporte) {
    this.mensaje_soporte = mensaje_soporte;
  }
}
