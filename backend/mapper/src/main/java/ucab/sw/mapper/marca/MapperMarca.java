package ucab.sw.mapper.marca;

import ucab.dsw.accesodatos.DaoSubcategoria;
import ucab.dsw.dtos.MarcaDto;
import ucab.dsw.dtos.SubcategoriaDto;
import ucab.dsw.entidades.Marca;
import ucab.dsw.entidades.Subcategoria;


public class MapperMarca {

  public static Marca MapMArcaDtoToEntityAdd(MarcaDto marcaDto){

    try{

      Marca marca = new Marca();
      marca.set_nombreMarca(marcaDto.getNombreMarca());
      marca.set_tipoMarca(marcaDto.getTipoMarca());
      marca.set_capacidad(marcaDto.getCapacidad());
      marca.set_unidad(marcaDto.getUnidad());
      marca.set_estado("activo");
      DaoSubcategoria daoSubcategoria = new DaoSubcategoria();
      Subcategoria subcategoria = daoSubcategoria.find(marcaDto.getSubcategoria().getId(),Subcategoria.class);
      marca.set_subcategoria(subcategoria);


      return marca;

    }catch (Exception ex){
      ex.printStackTrace();
      return null;
    }

  }

  public  static Marca MapMarcaDtoToEntityUpdate(MarcaDto marcaDto, Marca marcaPorActualizar){

    try{

      marcaPorActualizar.set_nombreMarca(marcaDto.getNombreMarca());
      marcaPorActualizar.set_tipoMarca(marcaDto.getTipoMarca());
      marcaPorActualizar.set_unidad(marcaDto.getUnidad());
      marcaPorActualizar.set_capacidad(marcaDto.getCapacidad());

      return marcaPorActualizar;

    }catch (Exception ex){
      ex.printStackTrace();
      return null;
    }

  }

  public static MarcaDto MapEntityToMarcaDto(Marca marca){

    try{
      MarcaDto marcaDto = new MarcaDto();
      marcaDto.setId(marca.get_id());
      marcaDto.setNombreMarca(marca.get_nombreMarca());
      marcaDto.setTipoMarca(marca.get_tipoMarca());
      marcaDto.setCapacidad(marca.get_capacidad());
      marcaDto.setUnidad(marca.get_unidad());
      marcaDto.setEstado(marca.get_estado());
      SubcategoriaDto subcategoriaDto = new SubcategoriaDto();
      subcategoriaDto.setNombreSubcategoria(marca.get_subcategoria().get_nombreSubcategoria());
      subcategoriaDto.setId(marca.get_subcategoria().get_id());
      marcaDto.setSubcategoria(subcategoriaDto);

      return marcaDto;

    }catch (Exception ex){
      ex.printStackTrace();
      return null;
    }

  }

}
