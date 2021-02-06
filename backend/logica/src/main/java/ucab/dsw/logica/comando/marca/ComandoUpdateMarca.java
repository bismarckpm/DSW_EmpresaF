package ucab.dsw.logica.comando.marca;

import org.eclipse.persistence.exceptions.DatabaseException;
import ucab.dsw.accesodatos.DaoMarca;
import ucab.dsw.dtos.MarcaDto;
import ucab.dsw.entidades.Marca;
import ucab.dsw.excepciones.ProblemaExcepcion;
import ucab.dsw.logica.comando.ComandoBase;
import ucab.dsw.logica.fabrica.Fabrica;
import ucab.sw.mapper.marca.MapperMarca;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.PersistenceException;

public class ComandoUpdateMarca implements ComandoBase{

  private long idMarca;
  private MarcaDto marcaDto;

  public ComandoUpdateMarca(long idMarca, MarcaDto marcaDto){
    this.idMarca = idMarca;
    this.marcaDto = marcaDto;
  }

  public void execute() throws Exception {

    try{

      DaoMarca daoMarca = Fabrica.crear(DaoMarca.class);
      Marca marca = daoMarca.find(this.idMarca, Marca.class);

      Marca marcaPorActualizar = MapperMarca.MapMarcaDtoToEntityUpdate(this.marcaDto, marca);
      Marca resultado = daoMarca.update((marcaPorActualizar));

      this.marcaDto = MapperMarca.MapEntityToMarcaDto(resultado);

    }catch (PersistenceException | DatabaseException ex){

      throw new ProblemaExcepcion("Esta marca ya se encuentra agregada en el sistema",ex.getMessage());

    } catch (Exception ex){
      throw ex;
    }

  }

  public JsonObject getResultado(){

    try{

      JsonObject data = Json.createObjectBuilder()
        .add("marca", marcaDto.getId())
        .add("estado", "success")
        .add("code", 200)
        .build();

      return data;

    }catch (Exception ex){
      throw ex;
    }

  }

}
