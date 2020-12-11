package ucab.dsw.dtos;

import javax.persistence.*;

public class MunicipioDto extends DtoBase{

  private String _nombreMunicipio;

  private PaisDto _pais;

  public MunicipioDto(long id) throws Exception {
    super(id);
  }

  public MunicipioDto() {
  }

  public String get_nombreMunicipio() {
    return _nombreMunicipio;
  }

  public void set_nombreMunicipio(String _nombreMunicipio) {
    this._nombreMunicipio = _nombreMunicipio;
  }

  public PaisDto get_pais() {
    return _pais;
  }

  public void set_pais(PaisDto _pais) {
    this._pais = _pais;
  }
}
