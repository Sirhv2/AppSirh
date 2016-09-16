package co.gov.ideam.sirh.usuarios.agua.model;

import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.model.PartZonificListas;

import java.io.Serializable;

public class CriteriosBusquedaExternasJSTO implements Serializable {

  //private Lista tipoIdentificacion;
  private String acuifero;
  private String idPunto;
  private String provincia;
  private String area;
  private Integer autoridad;
  private Integer provinciaId;
  /*private Integer idFuente;
  private String apellidos;
  private Divipola departamento;
  private Divipola municipio;
  private Lista naturalezaUsuario;
  private Lista uso;
  private Lista clasificacionSuelo;
  private Autoridades autoridad; //eduin ortiz.
  private Concesion concesion; //eduin ortiz
  private PermisoVertimiento permiso; //eduin ortiz
  private PartZonificListas area;
  private PartZonificListas zona;
  private PartZonificListas subzona;
  private String cedCatastral;
  private Lista tipoVertimiento;
  private Lista otroTipoUso;
  private String caudal;
  private String signo;
  private String tipo_tramite;*/

  public CriteriosBusquedaExternasJSTO() {
    super();
  }


  public void setAcuifero(String acuifero) {
    this.acuifero = acuifero;
  }

  public String getAcuifero() {
    return acuifero;
  }


  public void setIdPunto(String idPunto) {
    this.idPunto = idPunto;
  }

  public String getIdPunto() {
    return idPunto;
  }

  public void setProvincia(String provincia) {
    this.provincia = provincia;
  }

  public String getProvincia() {
    return provincia;
  }


  public void setArea(String area) {
    this.area = area;
  }

  public String getArea() {
    return area;
}


  public void setAutoridad(Integer autoridad) {
    this.autoridad = autoridad;
  }

  public Integer getAutoridad() {
    return autoridad;
  }


  public void setProvinciaId(Integer provinciaId) {
    this.provinciaId = provinciaId;
  }

  public Integer getProvinciaId() {
    return provinciaId;
  }
}
