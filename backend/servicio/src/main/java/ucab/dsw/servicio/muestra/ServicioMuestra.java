package ucab.dsw.servicio.muestra;

import ucab.dsw.accesodatos.DaoMuestra;
import ucab.dsw.entidades.Encuestado;
import ucab.dsw.entidades.Muestra;
import ucab.dsw.entidades.SolicitudEstudio;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ServicioMuestra {


  public void addMuestra(List<Encuestado> encuestados, SolicitudEstudio solicitudEstudio){


    DaoMuestra daoMuestra = new DaoMuestra();

    for(Encuestado encuestado:encuestados){

      DateFormat fecha = new SimpleDateFormat(("dd-MM-yyyy"));
      String fechaConvertido = fecha.format(encuestado.get_fechaNacimiento());

      DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
      LocalDate fechaParse = LocalDate.parse(fechaConvertido, fmt);

      Period periodo = Period.between(fechaParse, LocalDate.now());

      if(solicitudEstudio.get_edadfinal() != null){

        if(periodo.getYears() >= solicitudEstudio.get_edadInicial() && periodo.getYears() <= solicitudEstudio.get_edadfinal()) {
          Muestra muestra = new Muestra();
          muestra.set_encuestado(encuestado);
          muestra.set_solicitudEstudio(solicitudEstudio);
          daoMuestra.insert(muestra);
        }

      }else{

        if(periodo.getYears() >= solicitudEstudio.get_edadInicial()) {
          Muestra muestra = new Muestra();
          muestra.set_encuestado(encuestado);
          muestra.set_solicitudEstudio(solicitudEstudio);
          daoMuestra.insert(muestra);
        }
      }
    }
  }
}
