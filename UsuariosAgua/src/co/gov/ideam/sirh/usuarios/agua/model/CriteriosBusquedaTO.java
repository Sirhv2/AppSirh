package co.gov.ideam.sirh.usuarios.agua.model;

import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.parametros.model.Lista;

import co.gov.ideam.sirh.parametros.model.PartZonificListas;

import java.io.Serializable;

public class CriteriosBusquedaTO implements Serializable{
    
    private Lista tipoIdentificacion;
    private String numeroIdentificacion;
    private String nombres;
    private Integer idFuente;
    private String apellidos;
    private Divipola departamento;
    private Divipola municipio;
    private Lista naturalezaUsuario;
    private Lista uso;
    private Lista clasificacionSuelo;
    private Autoridades autoridad;//eduin ortiz.
    private Concesion concesion;//eduin ortiz
    private PermisoVertimiento permiso;//eduin ortiz
    private PartZonificListas area;
    private PartZonificListas zona;
    private PartZonificListas subzona;
    private String cedCatastral;
    private Lista tipoVertimiento;
    private Lista otroTipoUso;
    private Lista provinciahidro;
    private String fInicio;
    private String fFin;
    
                     
    public CriteriosBusquedaTO() {
        super();
    }

    public Lista getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(Lista tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Divipola getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Divipola departamento) {
        this.departamento = departamento;
    }

    public Divipola getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Divipola municipio) {
        this.municipio = municipio;
    }

    public Lista getNaturalezaUsuario() {
        return naturalezaUsuario;
    }

    public void setNaturalezaUsuario(Lista naturalezaUsuario) {
        this.naturalezaUsuario = naturalezaUsuario;
    }

    public void setAutoridad(Autoridades autoridad) {
        this.autoridad = autoridad;
    }

    public Autoridades getAutoridad() {
        return autoridad;
    }

    public void setConcesion(Concesion concesion) {
        this.concesion = concesion;
    }

    public Concesion getConcesion() {
        return concesion;
    }

    public void setPermiso(PermisoVertimiento permiso) {
        this.permiso = permiso;
    }

    public PermisoVertimiento getPermiso() {
        return permiso;
    }

    public Lista getClasificacionSuelo() {
        return clasificacionSuelo;
    }

    public void setClasificacionSuelo(Lista clasificacionSuelo) {
        this.clasificacionSuelo = clasificacionSuelo;
    }

    public void setUso(Lista uso) {
        this.uso = uso;
    }

    public Lista getUso() {
        return uso;
    }

    public void setIdFuente(Integer idFuente) {
        this.idFuente = idFuente;
    }

    public Integer getIdFuente() {
        return idFuente;
    }


    public void setArea(PartZonificListas area) {
        this.area = area;
    }

    public PartZonificListas getArea() {
        return area;
    }

    public void setZona(PartZonificListas zona) {
        this.zona = zona;
    }

    public PartZonificListas getZona() {
        return zona;
    }

    public void setSubzona(PartZonificListas subzona) {
        this.subzona = subzona;
    }

    public PartZonificListas getSubzona() {
        return subzona;
    }

    public void setCedCatastral(String cedCatastral) {
        this.cedCatastral = cedCatastral;
    }

    public String getCedCatastral() {
        return cedCatastral;
    }

    public void setTipoVertimiento(Lista tipoVertimiento) {
        this.tipoVertimiento = tipoVertimiento;
    }

    public Lista getTipoVertimiento() {
        return tipoVertimiento;
    }

    public void setOtroTipoUso(Lista otroTipoUso) {
        this.otroTipoUso = otroTipoUso;
    }

    public Lista getOtroTipoUso() {
        return otroTipoUso;
    }

    public void setProvinciahidro(Lista provinciahidro) {
        this.provinciahidro = provinciahidro;
    }

    public Lista getProvinciahidro() {
        return provinciahidro;
    }

    public void setFInicio(String fInicio) {
        this.fInicio = fInicio;
    }

    public String getFInicio() {
        return fInicio;
    }

    public void setFFin(String fFin) {
        this.fFin = fFin;
    }

    public String getFFin() {
        return fFin;
    }
}
