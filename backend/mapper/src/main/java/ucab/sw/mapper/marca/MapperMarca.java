package ucab.sw.mapper.marca;

import ucab.dsw.accesodatos.DaoSubcategoria;
import ucab.dsw.dtos.MarcaDto;
import ucab.dsw.dtos.SubcategoriaDto;
import ucab.dsw.entidades.Marca;
import ucab.dsw.entidades.Subcategoria;
import ucab.dsw.logica.fabrica.Fabrica;

public class MapperMarca {

  public static Marca MapMArcaDtoToEntityAdd(MarcaDto marcaDto){

    try{

      Marca marca = Fabrica.crear(Marca.class);
      marca.set_nombreMarca(marcaDto.getNombreMarca());
      marca.set_tipoMarca(marcaDto.getTipoMarca());
      marca.set_capacidad(marcaDto.getCapacidad());
      marca.set_unidad(marcaDto.getUnidad());
      marca.set_estado("activo");
      DaoSubcategoria daoSubcategoria = Fabrica.crear(DaoSubcategoria.class);
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
      MarcaDto marcaDto = Fabrica.crear(MarcaDto.class);
      marcaDto.setId(marca.get_id());
      marcaDto.setNombreMarca(marca.get_nombreMarca());
      marcaDto.setTipoMarca(marca.get_tipoMarca());
      marcaDto.setCapacidad(marca.get_capacidad());
      marcaDto.setUnidad(marca.get_unidad());
      marcaDto.setEstado(marca.get_estado());
      SubcategoriaDto subcategoriaDto = Fabrica.crear(SubcategoriaDto.class);
      subcategoriaDto.setNombreSubcategoria(marca.get_subcategoria().get_nombreSubcategoria());
      subcategoriaDto.setId(marca.get_subcategoria().get_id());
      marcaDto.setSubcategoria(subcategoriaDto);

      return marcaDto;

    }catch (Exception ex){
      ex.printStackTrace();
      return null;
    }

  }

  public static Marca MapMarcaDtoToEntityDisable(Marca marca){

    try{

      Marca mar = Fabrica.crear(Marca.class);
      marca.set_estado("inactivo");

      return mar;

    }catch (Exception ex){
      ex.printStackTrace();
      return null;
    }
  }

  public static Marca MapMarcaDtoToEntityEnabled(Marca marca){

    try{

      Marca mar = Fabrica.crear(Marca.class);
      marca.set_estado("activo");

      return mar;

    }catch (Exception ex){
      ex.printStackTrace();
      return null;
    }
  }

}
