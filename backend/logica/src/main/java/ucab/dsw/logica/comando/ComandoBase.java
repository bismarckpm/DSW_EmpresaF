package ucab.dsw.logica.comando;

import ucab.dsw.excepciones.LimiteExcepcion;
import ucab.dsw.excepciones.SolicitudPendienteExcepcion;

public interface ComandoBase {

  void execute() throws LimiteExcepcion, SolicitudPendienteExcepcion;

}
