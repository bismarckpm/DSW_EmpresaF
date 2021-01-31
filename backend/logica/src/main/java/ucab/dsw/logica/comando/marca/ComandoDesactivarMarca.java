package ucab.dsw.logica.comando.marca;


import ucab.dsw.accesodatos.DaoMarca;
import ucab.dsw.dtos.MarcaDto;
import ucab.dsw.entidades.Marca;
import ucab.dsw.logica.comando.ComandoBase;
import ucab.dsw.logica.fabrica.Fabrica;
import ucab.sw.mapper.marca.MapperMarca;

import javax.json.Json;
import javax.json.JsonObject;

public class ComandoDesactivarMarca implements ComandoBase{

  private  long idMarca;
  private MarcaDto marcaDto;

  public ComandoDesactivarMarca(long idMarca){this.idMarca = idMarca;}

  public  void execute(){

    try{

      DaoMarca daoMarca = Fabrica.crear(DaoMarca.class);
      Marca marca = daoMarca.find(this.idMarca, Marca.class);
      marca.set_estado("inactivo");

      Marca resultado = daoMarca.update(marca);
      this.marcaDto = MapperMarca.MapEntityToMarcaDto(resultado);

    }catch (Exception ex){
      throw ex;
    }

  }

  public JsonObject getResultado(){

    try{

      JsonObject data = Json.createObjectBuilder()
        .add("subcategoria", this.marcaDto.getId())
        .add("estado", "success")
        .add("code", 200)
        .build();

      return data;

    }catch (Exception ex){
      throw ex;
    }

  }

}
