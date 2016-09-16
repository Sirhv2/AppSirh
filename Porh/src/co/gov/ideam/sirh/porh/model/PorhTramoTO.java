package co.gov.ideam.sirh.porh.model;

import co.gov.ideam.sirh.fuentes.model.FnttTramo;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * Encapsula toda la informacion del tramo para moswtrarla en el reporte
 */
public class PorhTramoTO implements Serializable{
    
    private FnttTramo tramo;
    private List<PorhOfertaDemanda> ofertaDemanda;
    private List<PorhTramosProhibiciones> prohibicionesVertimiento;   
    private List listaUsos;
    private List<PorvMetas> metasDescontaminacion;
    private Integer usuariosConcesionAguas;
    private Integer usuariosVertimiento;
    
    public PorhTramoTO() {
        super();
    }

    public FnttTramo getTramo() {
        return tramo;
    }

    public void setTramo(FnttTramo tramo) {
        this.tramo = tramo;
    }

    public List<PorhTramosProhibiciones> getProhibicionesVertimiento() {
        return prohibicionesVertimiento;
    }

    public void setProhibicionesVertimiento(List<PorhTramosProhibiciones> prohibicionesVertimiento) {
        this.prohibicionesVertimiento = prohibicionesVertimiento;
    }

    public List getListaUsos() {
        return listaUsos;
    }

    public void setListaUsos(List listaUsos) {
        this.listaUsos = listaUsos;
    }

    public List<PorvMetas> getMetasDescontaminacion() {
        return metasDescontaminacion;
    }

    public void setMetasDescontaminacion(List<PorvMetas> metasDescontaminacion) {
        this.metasDescontaminacion = metasDescontaminacion;
    }

    public List<PorhOfertaDemanda> getOfertaDemanda() {
        return ofertaDemanda;
    }

    public void setOfertaDemanda(List<PorhOfertaDemanda> ofertaDemanda) {
        this.ofertaDemanda = ofertaDemanda;
    }

  public void setUsuariosConcesionAguas(Integer usuariosConcesionAguas) {
    this.usuariosConcesionAguas = usuariosConcesionAguas;
  }

  public Integer getUsuariosConcesionAguas() {
    return usuariosConcesionAguas;
  }

  public void setUsuariosVertimiento(Integer usuariosVertimiento) {
    this.usuariosVertimiento = usuariosVertimiento;
  }

  public Integer getUsuariosVertimiento() {
    return usuariosVertimiento;
  }
}
