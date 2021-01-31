package ucab.dsw.logica.comando.marca;

import ucab.dsw.entidades.Marca;
import ucab.dsw.accesodatos.DaoMarca;
import ucab.dsw.dtos.MarcaDto;
import ucab.dsw.logica.comando.ComandoBase;
import ucab.dsw.logica.fabrica.Fabrica;
import ucab.sw.mapper.marca.MapperMarca;

import javax.json.Json;
import javax.json.JsonObject;

public class ComandoGetMarca implements ComandoBase{

  private  long idMarca;
  private MarcaDto marcaDto;

  public ComandoGetMarca(long idMarca){this.idMarca = idMarca;}

  public void  execute(){

    try{

      DaoMarca daoMarca = Fabrica.crear(DaoMarca.class);
      Marca resultado = daoMarca.find(this.idMarca,Marca.class);

      this.marcaDto = MapperMarca.MapEntityToMarcaDto(resultado);

    }catch (Exception ex){
      throw ex;
    }

  }

  public JsonObject getResultado(){

    try{

      JsonObject data = Json.createObjectBuilder()
        .add("estado", "success")
        .add("code", 200)
        .add("id", this.marcaDto.getId())
        .add("nombreMarca", this.marcaDto.getNombreMarca())
        .add("estadoMarca", this.marcaDto.getEstado())
        .add("tipoMarca", this.marcaDto.getTipoMarca())
        .add("capacidad", this.marcaDto.getCapacidad())
        .add("unidad", this.marcaDto.getUnidad())
        .add("subcategoriaId", this.marcaDto.getSubcategoria().getId())
        .add("subcategoriaNombre", this.marcaDto.getSubcategoria().getNombreSubcategoria())
        .build();

      return data;

    }catch (Exception ex){
      throw ex;
    }

  }

}
