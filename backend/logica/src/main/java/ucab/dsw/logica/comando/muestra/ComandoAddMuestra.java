package ucab.dsw.logica.comando.muestra;

import ucab.dsw.accesodatos.DaoMuestra;
import ucab.dsw.entidades.Encuestado;
import ucab.dsw.entidades.Muestra;
import ucab.dsw.entidades.SolicitudEstudio;
import ucab.dsw.logica.comando.ComandoBase;
import ucab.dsw.logica.fabrica.Fabrica;
import ucab.sw.mapper.muestra.MapperMuestra;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ComandoAddMuestra implements ComandoBase {

  private List<Encuestado> encuestados;
  private SolicitudEstudio solicitudEstudio;

  public ComandoAddMuestra(List<Encuestado> encuestados, SolicitudEstudio solicitudEstudio) {
    this.encuestados = encuestados;
    this.solicitudEstudio = solicitudEstudio;
  }

  public void execute() throws Exception {

    try {

      DaoMuestra daoMuestra = Fabrica.crear(DaoMuestra.class);

      for(Encuestado encuestado:this.encuestados){

        DateFormat fecha = new SimpleDateFormat(("dd-MM-yyyy"));
        String fechaConvertido = fecha.format(encuestado.get_fechaNacimiento());

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate fechaParse = LocalDate.parse(fechaConvertido, fmt);

        Period periodo = Period.between(fechaParse, LocalDate.now());

        if(this.solicitudEstudio.get_edadfinal() != null){

          if(periodo.getYears() >= this.solicitudEstudio.get_edadInicial() && periodo.getYears() <= this.solicitudEstudio.get_edadfinal()) {

            Muestra muestra = MapperMuestra.MappMuestraToEntityAdd(encuestado, this.solicitudEstudio);
            daoMuestra.insert(muestra);

          }

        }else{

          if(periodo.getYears() >= this.solicitudEstudio.get_edadInicial()) {

            Muestra muestra = MapperMuestra.MappMuestraToEntityAdd(encuestado, this.solicitudEstudio);
            daoMuestra.insert(muestra);

          }

        }

      }

    }catch (Exception ex){
      throw ex;
    }

  }

}
