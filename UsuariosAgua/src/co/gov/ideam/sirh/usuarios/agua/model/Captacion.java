package co.gov.ideam.sirh.usuarios.agua.model;

import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.fuentes.model.FnttTramo;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.model.PartZonificListas;
import co.gov.ideam.sirh.util.Constantes;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.SequenceGenerator;

@Entity
@NamedQueries({
    @NamedQuery(name = "RurtCaptacion.findAll", query = "SELECT r FROM Captacion r"),
    @NamedQuery(name = "RurtCaptacion.findByDepartamento", query = "SELECT r FROM Captacion r WHERE r.idDepartamento = :departamento"),
    @NamedQuery(name = "RurtCaptacion.findByMunicipio", query = "SELECT r FROM Captacion r WHERE r.idMunicipio = :municipio"),
    @NamedQuery(name = "RurtCaptacion.findByTipoCentroPoblado", query = "SELECT r FROM Captacion r WHERE r.idTipoCentroPoblado = :tipoCentroPoblado"),
    @NamedQuery(name = "RurtCaptacion.findByNombreCentroPoblado", query = "SELECT r FROM Captacion r WHERE r.nombreCentroPoblado = :nombreCentroPoblado"),
    @NamedQuery(name = "RurtCaptacion.findByOfertaHidricaTotal", query = "SELECT r FROM Captacion r WHERE r.ofertaHidricaTotal = :ofertaHidricaTotal"),
    @NamedQuery(name = "RurtCaptacion.findByOfertaDisponible", query = "SELECT r FROM Captacion r WHERE r.ofertaDisponible = :ofertaDisponible"),
    @NamedQuery(name = "RurtCaptacion.findByTieneMacroMedicion", query = "SELECT r FROM Captacion r WHERE r.tieneMacroMedicion = :tieneMacroMedicion"),
    @NamedQuery(name = "RurtCaptacion.findByEstadoCaptacion", query = "SELECT r FROM Captacion r WHERE r.estadoCaptacion = :estadoCaptacion"),
    @NamedQuery(name = "RurtCaptacion.findByCaudalDisegno", query = "SELECT r FROM Captacion r WHERE r.caudalDisegno = :caudalDisegno"),
    @NamedQuery(name = "RurtCaptacion.findByTieneServidumbre", query = "SELECT r FROM Captacion r WHERE r.tieneServidumbre = :tieneServidumbre"),
    @NamedQuery(name = "RurtCaptacion.findBySistemaReferencia", query = "SELECT r FROM Captacion r WHERE r.sistemaReferencia = :sistemaReferencia"),
    @NamedQuery(name = "RurtCaptacion.findByDescripcionAcceso", query = "SELECT r FROM Captacion r WHERE r.descripcionAcceso = :descripcionAcceso"),
    @NamedQuery(name = "RurtCaptacion.findById", query = "SELECT r FROM Captacion r WHERE r.id = :id"),
    @NamedQuery(name = "RurtCaptacion.findByTipoFuenteCaptacion", query = "SELECT r FROM Captacion r WHERE r.tipoFuenteCaptacion = :tipoFuenteCaptacion"),
    @NamedQuery(name = "RurtCaptacion.findByProvinciaHidrogeologica", query = "SELECT r FROM Captacion r WHERE r.provinciaHidrogeologica = :provinciaHidrogeologica"),
    @NamedQuery(name = "RurtCaptacion.findByUnidadHidrogeologica", query = "SELECT r FROM Captacion r WHERE r.unidadHidrogeologica = :unidadHidrogeologica"),
    @NamedQuery(name = "RurtCaptacion.findByTipoPunto", query = "SELECT r FROM Captacion r WHERE r.tipoPunto = :tipoPunto"),
    @NamedQuery(name = "RurtCaptacion.findByMetodoExtraccion", query = "SELECT r FROM Captacion r WHERE r.metodoExtraccion = :metodoExtraccion"),
    @NamedQuery(name = "RurtCaptacion.findByTipoCaptacion", query = "SELECT r FROM Captacion r WHERE r.tipoCaptacion = :tipoCaptacion"),
    @NamedQuery(name = "RurtCaptacion.findByAreaCaptacion", query = "SELECT r FROM Captacion r WHERE r.areaCaptacion = :areaCaptacion"),
    @NamedQuery(name = "RurtCaptacion.findAllByConcesion", query = "SELECT r FROM Captacion r WHERE r.idConcesion.codigo = :idConcesion"),
    @NamedQuery(name = "RurtCaptacion.findAllByConcesionAutoridad", query = "SELECT r FROM Captacion r WHERE r.idConcesion.codigo = :idConcesion "),    
    @NamedQuery(name = "RurtCaptacion.totalCaudalTramo", query = "SELECT sum(r.caudalDisegno) FROM Captacion r WHERE r.idTramo.id = :codigoTramo and r.idFuente.id = :codigoFuente and r.tipoFuenteCaptacion = :tipoFuente "),
    @NamedQuery(name = "RurtCaptacion.findByFuente", query = "SELECT r FROM Captacion r WHERE r.idFuente.id = :idFuente"),
    @NamedQuery(name = "RurtCaptacion.findByTramo", query = "SELECT r FROM Captacion r WHERE r.idTramo.id = :idTramo"),
    @NamedQuery(name = "RurtCaptacion.findByIdPunto", query = "SELECT r FROM Captacion r WHERE r.identificadorPunto = :idPunto"),
    @NamedQuery(name = "RurtCaptacion.findBySinConcesion", query = "SELECT r FROM Captacion r WHERE r.idConcesion.no_valida=1 and r.idConcesion.codigoPredio= :idPredio") //Pilar

})

@Table(name = "rurt_captacion")
public class Captacion implements Serializable {
    
    @GenericGenerator(name = "captaciones_generator", 
                      strategy = "co.gov.ideam.sirh.usuarios.agua.model.IdeamSirhGenerator",
                      parameters = {@Parameter(name = "AutoridadAmbientalId", value = "codigoAutoridad"),
                                    @Parameter(name = "Codigo", value = "codigo"),
                                    @Parameter(name = SequenceGenerator.SEQUENCE,  value= "SEQ_CAPTACIONES")})
    
    @Id
    @Column(name="id", nullable = false)
    //@GeneratedValue(generator = "captaciones_generator") 
    private Long codigo;


    @Column(name = "departamento")
    private Integer idDepartamento;

    @Column(name = "municipio")
    private Integer idMunicipio;

    @Column(name = "tipo_centro_poblado")
    private Integer idTipoCentroPoblado;

    @Column(name = "nombre_centro_poblado")
    private String nombreCentroPoblado;

    @Column(name = "oferta_hidrica_total")
    private Double ofertaHidricaTotal;

    @Column(name = "oferta_disponible")
    private Double ofertaDisponible;
    
    @Column(name = "continuidad")
    private String continuidad;

    @Column(name = "estado_capatacion")
    private Integer estadoCaptacion;

    @Column(name = "caudal_disegno")
    private Double caudalDisegno;
    
    @Column(name = "caudal_vertimiento")
    private Double caudalVertimiento;

    @Column(name = "sistema_referencia")
    private Integer sistemaReferencia;

    @Column(name = "grados_lat", nullable = false)
    private Integer gradosLat;
    @Column(name = "minutos_lat", nullable = false)
    private Integer minutosLat;
    @Column(name = "segundos_lat", nullable = false)
    private Double segundosLat;
    @Column(name = "grados_long", nullable = false)
    private Integer gradosLong;
    @Column(name = "minutos_long", nullable = false)
    private Integer minutosLong;
    @Column(name = "segundos_long", nullable = false)
    private Double segundosLong;
    @Column(name = "altitud", nullable = false)
    private Double altitud;

    @Column(name = "descripcion_acceso")
    private String descripcionAcceso;

    @Column(name = "provincia_hidrogeologica")
    private Integer provinciaHidrogeologica;
    
    @Column(name = "unidad_hidrogeologica")
    private String unidadHidrogeologica;
    
    @Column(name = "tipo_punto")
    private Integer tipoPunto;
    
    @Column(name = "identificador_punto")
    private String identificadorPunto;

    @Column(name = "metodo_extraccion")
    private Integer metodoExtraccion;
    
    @Column(name = "tipo_captacion")
    private Integer tipoCaptacion;

    @Column(name = "area_captacion")
    private Double areaCaptacion;

    @Column(name = "tipo_fuente_captacion")
    private Integer tipoFuenteCaptacion;
    
    @Column(name = "tiene_macro_medicion")
    private Integer tieneMacroMedicion;
    
    @Column(name = "tiene_servidumbre")
    private Integer tieneServidumbre;
    
    @JoinColumn(name = "id_area", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private PartZonificListas idArea;
  
    @JoinColumn(name = "id_zona", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private PartZonificListas idZona;
    
    @JoinColumn(name = "id_subzona", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private PartZonificListas idSubzona;
    
    @JoinColumn(name = "id_concesion", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Concesion idConcesion;
    
    @JoinColumn(name = "id_tramo", referencedColumnName = "id")
    @ManyToOne
    private FnttTramo idTramo;
    
    @JoinColumn(name = "id_fuente", referencedColumnName = "id")
    @ManyToOne
    private FnttFuente idFuente;
    
    ////////////////////////
    @Transient
    private List<RurtCaptacionUso> rurtCaptacionUsoList;
    @Transient
    private List<RurtCaptacionComponentes> rurtCaptacionComponentesList;
    @Transient
    private List<SubttFunias> subttFuniasList;
    @Transient
    private Long codigoAutoridad;
    @Transient
    private Divipola objDepartamento;
    @Transient
    private Divipola objMunicipio;
    @Transient
    private Lista objTipoCentroPoblado;
    @Transient
    private Lista objEstadoCaptacion;
    @Transient
    private Lista objSistemaReferencia;
    @Transient
    private Lista objProvinciaHidrogeologica;
    @Transient
    private Lista objTipoPunto;
    @Transient
    private Lista objMetodoExtraccion;
    @Transient
    private Lista objTipoCaptacion;
    @Transient
    private Lista objTipoFuenteCaptacion;
    @Transient
    private ConcesionTO concesionVista;
    
    @Transient
    private Boolean tieneMacroMedicionAux;
    @Transient
    private Boolean tieneServidumbreAux;
    
    @Transient
    private Autoridades autoridad;
    
    //Lisbeth 
    @Transient
    private List listaPuntosMonitoreoCapt;
    
    @Transient
    private String nombreDpto;
    @Transient
    private String nombreMcpo;
     
    //Pilar
    @Column(name = "red_monitoreo")
    private Integer red_monitoreo;
    @Transient
    private Boolean red_monitoreoAux;
    
    //cDoncel
    @Column(name = "observacion_captacion")
    private String observacion;

    
    public Captacion() {
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setIdDepartamento(Integer idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public Integer getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdMunicipio(Integer idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public Integer getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdTipoCentroPoblado(Integer idTipoCentroPoblado) {
        this.idTipoCentroPoblado = idTipoCentroPoblado;
    }

    public Integer getIdTipoCentroPoblado() {
        return idTipoCentroPoblado;
    }

    public void setNombreCentroPoblado(String nombreCentroPoblado) {
        this.nombreCentroPoblado = nombreCentroPoblado;
    }

    public String getNombreCentroPoblado() {
        return nombreCentroPoblado;
    }

    public void setOfertaHidricaTotal(Double ofertaHidricaTotal) {
        this.ofertaHidricaTotal = ofertaHidricaTotal;
    }

    public Double getOfertaHidricaTotal() {
        return ofertaHidricaTotal;
    }

    public void setOfertaDisponible(Double ofertaDisponible) {
        this.ofertaDisponible = ofertaDisponible;
    }

    public Double getOfertaDisponible() {
        return ofertaDisponible;
    }
    
    public void setContinuidad(String continuidad) {
        this.continuidad = continuidad;
    }

    public String getContinuidad() {
        return continuidad;
    }

    public void setTieneMacroMedicion(Integer tieneMacroMedicion) {
        this.tieneMacroMedicion = tieneMacroMedicion;
    }

    public Integer getTieneMacroMedicion() {
        return tieneMacroMedicion;
    }

    public void setEstadoCaptacion(Integer estadoCaptacion) {
        this.estadoCaptacion = estadoCaptacion;
    }

    public Integer getEstadoCaptacion() {
        return estadoCaptacion;
    }

    public void setCaudalDisegno(Double caudalDisegno) {
        this.caudalDisegno = caudalDisegno;
    }

    public Double getCaudalDisegno() {
        return caudalDisegno;
    }
    
    public void setCaudalVertimiento(Double caudalVertimiento) {
        this.caudalVertimiento = caudalVertimiento;
    }

    public Double getCaudalVertimiento() {
        return caudalVertimiento;
    }
    
    public void setTieneServidumbre(Integer tieneServidumbre) {
        this.tieneServidumbre = tieneServidumbre;
    }

    public Integer getTieneServidumbre() {
        return tieneServidumbre;
    }

    public void setSistemaReferencia(Integer sistemaReferencia) {
        this.sistemaReferencia = sistemaReferencia;
    }

    public Integer getSistemaReferencia() {
        return sistemaReferencia;
    }

    public void setDescripcionAcceso(String descripcionAcceso) {
        this.descripcionAcceso = descripcionAcceso;
    }

    public String getDescripcionAcceso() {
        return descripcionAcceso;
    }

    public void setTipoFuenteCaptacion(Integer tipoFuenteCaptacion) {
        this.tipoFuenteCaptacion = tipoFuenteCaptacion;
    }

    public Integer getTipoFuenteCaptacion() {
        return tipoFuenteCaptacion;
    }

    public void setProvinciaHidrogeologica(Integer provinciaHidrogeologica) {
        this.provinciaHidrogeologica = provinciaHidrogeologica;
    }

    public Integer getProvinciaHidrogeologica() {
        return provinciaHidrogeologica;
    }

    public void setUnidadHidrogeologica(String unidadHidrogeologica) {
        this.unidadHidrogeologica = unidadHidrogeologica;
    }

    public String getUnidadHidrogeologica() {
        return unidadHidrogeologica;
    }

    public void setTipoPunto(Integer tipoPunto) {
        this.tipoPunto = tipoPunto;
    }

    public Integer getTipoPunto() {
        return tipoPunto;
    }

    public void setMetodoExtraccion(Integer metodoExtraccion) {
        this.metodoExtraccion = metodoExtraccion;
    }

    public Integer getMetodoExtraccion() {
        return metodoExtraccion;
    }

    public void setTipoCaptacion(Integer tipoCaptacion) {
        this.tipoCaptacion = tipoCaptacion;
    }

    public Integer getTipoCaptacion() {
        return tipoCaptacion;
    }

    public void setAreaCaptacion(Double areaCaptacion) {
        this.areaCaptacion = areaCaptacion;
    }

    public Double getAreaCaptacion() {
        return areaCaptacion;
    }

    public void setIdArea(PartZonificListas idArea) {
        this.idArea = idArea;
    }

    public PartZonificListas getIdArea() {
        return idArea;
    }

    public void setIdZona(PartZonificListas idZona) {
        this.idZona = idZona;
    }

    public PartZonificListas getIdZona() {
        return idZona;
    }

    public void setIdSubzona(PartZonificListas idSubzona) {
        this.idSubzona = idSubzona;
    }

    public PartZonificListas getIdSubzona() {
        return idSubzona;
    }

    public void setIdConcesion(Concesion idConcesion) {
        this.idConcesion = idConcesion;
    }

    public Concesion getIdConcesion() {
        return idConcesion;
    }

    public void setIdTramo(FnttTramo idTramo) {
        this.idTramo = idTramo;
    }

    public FnttTramo getIdTramo() {
        return idTramo;
    }

    public void setIdFuente(FnttFuente idFuente) {
        this.idFuente = idFuente;
    }

    public FnttFuente getIdFuente() {
        return idFuente;
    }

    public void setRurtCaptacionUsoList(List<RurtCaptacionUso> rurtCaptacionUsoList) {
        this.rurtCaptacionUsoList = rurtCaptacionUsoList;
    }

    public List<RurtCaptacionUso> getRurtCaptacionUsoList() {
        return rurtCaptacionUsoList;
    }

    public void setRurtCaptacionComponentesList(List<RurtCaptacionComponentes> rurtCaptacionComponentesList) {
        this.rurtCaptacionComponentesList = rurtCaptacionComponentesList;
    }

    public List<RurtCaptacionComponentes> getRurtCaptacionComponentesList() {
        return rurtCaptacionComponentesList;
    }


    //atributo transient
    public Long getCodigoAutoridad() {
        return codigoAutoridad;
    }

    public void setCodigoAutoridad(Long codigoAutoridad) {
        this.codigoAutoridad = codigoAutoridad;
    }
    
    public void setObjDepartamento(Divipola objDepartamento) {
        this.objDepartamento = objDepartamento;
    }

    public Divipola getObjDepartamento() {
        return objDepartamento;
    }

    public void setObjMunicipio(Divipola objMunicipio) {
        this.objMunicipio = objMunicipio;
    }

    public Divipola getObjMunicipio() {
        return objMunicipio;
    }

    public void setObjTipoCentroPoblado(Lista objTipoCentroPoblado) {
        this.objTipoCentroPoblado = objTipoCentroPoblado;
    }

    public Lista getObjTipoCentroPoblado() {
        return objTipoCentroPoblado;
    }

    public void setObjEstadoCaptacion(Lista objEstadoCaptacion) {
        this.objEstadoCaptacion = objEstadoCaptacion;
    }

    public Lista getObjEstadoCaptacion() {
        return objEstadoCaptacion;
    }

    public void setObjSistemaReferencia(Lista objSistemaReferencia) {
        this.objSistemaReferencia = objSistemaReferencia;
    }

    public Lista getObjSistemaReferencia() {
        return objSistemaReferencia;
    }

    public void setObjProvinciaHidrogeologica(Lista objProvinciaHidrogeologica) {
        this.objProvinciaHidrogeologica = objProvinciaHidrogeologica;
    }

    public Lista getObjProvinciaHidrogeologica() {
        return objProvinciaHidrogeologica;
    }

    public void setObjTipoPunto(Lista objTipoPunto) {
        this.objTipoPunto = objTipoPunto;
    }

    public Lista getObjTipoPunto() {
        return objTipoPunto;
    }
    
    public void setIdentificadorPunto(String identificadorPunto) {
        this.identificadorPunto = identificadorPunto;
    }

    public String getIdentificadorPunto() {
        return identificadorPunto;
    }

    public void setObjMetodoExtraccion(Lista objMetodoExtraccion) {
        this.objMetodoExtraccion = objMetodoExtraccion;
    }

    public Lista getObjMetodoExtraccion() {
        return objMetodoExtraccion;
    }

    public void setObjTipoCaptacion(Lista objTipoCaptacion) {
        this.objTipoCaptacion = objTipoCaptacion;
    }

    public Lista getObjTipoCaptacion() {
        return objTipoCaptacion;
    }

    public void setObjTipoFuenteCaptacion(Lista objTipoFuenteCaptacion) {
        this.objTipoFuenteCaptacion = objTipoFuenteCaptacion;
    }

    public Lista getObjTipoFuenteCaptacion() {
        return objTipoFuenteCaptacion;
    }

    public void setConcesionVista(ConcesionTO concesionVista) {
        this.concesionVista = concesionVista;
    }

    public ConcesionTO getConcesionVista() {
        return concesionVista;
    }

    public void setGradosLat(Integer gradosLat) {
        this.gradosLat = gradosLat;
    }

    public Integer getGradosLat() {
        return gradosLat;
    }

    public void setMinutosLat(Integer minutosLat) {
        this.minutosLat = minutosLat;
    }

    public Integer getMinutosLat() {
        return minutosLat;
    }

    public void setSegundosLat(Double segundosLat) {
        this.segundosLat = segundosLat;
    }

    public Double getSegundosLat() {
        return segundosLat;
    }

    public void setGradosLong(Integer gradosLong) {
        this.gradosLong = gradosLong;
    }

    public Integer getGradosLong() {
        return gradosLong;
    }

    public void setMinutosLong(Integer minutosLong) {
        this.minutosLong = minutosLong;
    }

    public Integer getMinutosLong() {
        return minutosLong;
    }

    public void setSegundosLong(Double segundosLong) {
        this.segundosLong = segundosLong;
    }

    public Double getSegundosLong() {
        return segundosLong;
    }

    public void setAltitud(Double altitud) {
        this.altitud = altitud;
    }

    public Double getAltitud() {
        return altitud;
    }

    public void setAutoridad(Autoridades autoridad) {
        this.autoridad = autoridad;
    }

    public Autoridades getAutoridad() {
        return autoridad;
    }
    
    public void setTieneMacroMedicionAux(Boolean tieneMacroMedicionAux) {
        this.tieneMacroMedicionAux = tieneMacroMedicionAux;
        if(tieneMacroMedicionAux==true){
            this.tieneMacroMedicion = Constantes.TRUE_TO_INTEGER;
        }else{
            this.tieneMacroMedicion = Constantes.FALSE_TO_INTEGER;
        }
    }
    
    public void setSubttFuniasList(List<SubttFunias> subttFuniasList) {
        this.subttFuniasList = subttFuniasList;
    }

    public List<SubttFunias> getSubttFuniasList() {
        return subttFuniasList;
    }

    public Boolean getTieneMacroMedicionAux() {

        if (this.tieneMacroMedicion==null) {
            return false;
        } else {
            return ((this.tieneMacroMedicion.intValue() == Constantes.TRUE_TO_INTEGER)
                    ? true : false);
        }

    }

    public void setTieneServidumbreAux(Boolean tieneServidumbreAux) {
        this.tieneServidumbreAux = tieneServidumbreAux;
        if(tieneServidumbreAux==true){
            this.tieneServidumbre = Constantes.TRUE_TO_INTEGER;
        }else{
            this.tieneServidumbre = Constantes.FALSE_TO_INTEGER;
        }
    }

    public Boolean getTieneServidumbreAux() {
        if(this.tieneServidumbre==null){
            return false;
        }else{
            return ((this.tieneServidumbre.intValue() == Constantes.TRUE_TO_INTEGER)
                    ? true : false);
        }
    }


    public void setRed_monitoreo(Integer red_monitoreo) {
        this.red_monitoreo = red_monitoreo;
    }

    public Integer getRed_monitoreo() {
        return red_monitoreo;
    }

    public void setRed_monitoreoAux(Boolean red_monitoreoAux) {
        this.red_monitoreoAux = red_monitoreoAux;
        if(red_monitoreoAux==true){
            this.red_monitoreo = Constantes.TRUE_TO_INTEGER;
        }else{
            this.red_monitoreo = Constantes.FALSE_TO_INTEGER;
        }
    }

    public Boolean getRed_monitoreoAux() {
        if (this.red_monitoreo==null) {
            return false;
        } else {
            return ((this.red_monitoreo.intValue() == Constantes.TRUE_TO_INTEGER)
                    ? true : false);
        }
    }

    public void setListaPuntosMonitoreoCapt(List listaPuntosMonitoreoCapt) {
        this.listaPuntosMonitoreoCapt = listaPuntosMonitoreoCapt;
    }

    public List getListaPuntosMonitoreoCapt() {
        return listaPuntosMonitoreoCapt;
    }

    public void setNombreDpto(String nombreDpto) {
        this.nombreDpto = nombreDpto;
    }

    public String getNombreDpto() {
        return nombreDpto;
    }

    public void setNombreMcpo(String nombreMcpo) {
        this.nombreMcpo = nombreMcpo;
    }

    public String getNombreMcpo() {
        return nombreMcpo;
    }


  public String getObservacion() {
    return observacion;
  }

  public void setObservacion(String observacion) {
    this.observacion = observacion;
  }
}

