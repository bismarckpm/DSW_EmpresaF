package ucab.dsw.logica.comando.marca;

import ucab.dsw.accesodatos.DaoMarca;
import ucab.dsw.dtos.MarcaDto;
import ucab.dsw.entidades.Marca;
import ucab.dsw.logica.comando.ComandoBase;
import ucab.dsw.logica.fabrica.Fabrica;
import ucab.sw.mapper.marca.MapperMarca;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.List;

public class ComandoGetMarcas implements  ComandoBase{

  private JsonArrayBuilder marcasDtos = Json.createArrayBuilder();

  public ComandoGetMarcas(){
  }

  public void execute(){

    try{

      DaoMarca daoMarca = Fabrica.crear(DaoMarca.class);
      List<Marca> marcas = daoMarca.findAll(Marca.class);

      for(Marca marca:marcas){

        MarcaDto marcaDto = MapperMarca.MapEntityToMarcaDto(marca);

        JsonObject ma = Json.createObjectBuilder()
          .add("id", marcaDto.getId())
          .add("nombreMarca", marcaDto.getNombreMarca())
          .add("tipoMarca", marcaDto.getTipoMarca())
          .add("capacidad", marcaDto.getCapacidad())
          .add("unidad", marcaDto.getUnidad())
          .add("estado", marcaDto.getEstado())
          .add("subcategoriaId", marcaDto.getSubcategoria().getId())
          .add("subcategoriaNombre", marcaDto.getSubcategoria().getNombreSubcategoria())
          .build();

        marcasDtos.add(ma);

      }

    }catch (Exception ex){
      throw ex;
    }

  }

  public JsonObject getResultado(){

    try{

      JsonObject data = Json.createObjectBuilder()
        .add("code", 200)
        .add("estado", "success")
        .add("marcas", this.marcasDtos)
        .build();

      return data;

    }catch (Exception ex){
      throw ex;
    }

  }

}
