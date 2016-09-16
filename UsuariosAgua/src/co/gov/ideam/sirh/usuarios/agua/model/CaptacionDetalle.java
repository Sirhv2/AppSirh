package co.gov.ideam.sirh.usuarios.agua.model;

import java.math.BigDecimal;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
//import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pilar
 */
@Entity
@Table(name = "SIRH_CAPTACIONES_V2")

@NamedQueries({
  
    @NamedQuery(name = "CaptacionDetalle.findByIdAutoridad", query = "SELECT s FROM CaptacionDetalle s WHERE s.idAutoridad = :idAutoridad"),
    @NamedQuery(name = "CaptacionDetalle.findByNombreAutoridad", query = "SELECT s FROM CaptacionDetalle s WHERE s.nombreAutoridad = :nombreAutoridad")
    
    })
    
public class CaptacionDetalle implements Serializable {
  
   @Id
   @Column(name="id", nullable = false)
    private Long id;

    @Column(name = "usuario")
    private String usuario;

    @Column(name = "predio")
    private String predio;

    @Column(name = "cedula_catastral")
    private String cedulaCatastral;

    @Column(name = "num_expediente")
    private String numExpediente;

    @Column(name = "nombre_departamento")
    private String nombreDepartamento;

    @Column(name = "nombre_municipio")
    private String nombreMunicipio;

    @Column(name = "tipo_fuente")
    private String tipoFuente;

    @Column(name = "nombre_tipo_fuente")
    private String nombreTipoFuente;

    @Column(name = "fuente_capt")
    private String fuenteCapt;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "oferta_hidrica_total_capt")
    private BigDecimal ofertaHidricaTotalCapt;
    @Column(name = "oferta_disponible_capt")
    private BigDecimal ofertaDisponibleCapt;

    @Column(name = "tramo_capt")
    private String tramoCapt;
    @Column(name = "caudal_disegno_capt")
    private BigDecimal caudalDisegnoCapt;

    @Column(name = "num_res_caudal")
    private String numResCaudal;

    @Column(name = "nombre_sr")
    private String nombreSr;
    @Column(name = "grados_lat_capt")
    private Integer gradosLatCapt;
    @Column(name = "minutos_lat_capt")
    private Integer minutosLatCapt;
    @Column(name = "segundos_lat_capt")
    private Double segundosLatCapt;
    @Column(name = "grados_long_capt")
    private Integer gradosLongCapt;
    @Column(name = "minutos_long_capt")
    private Integer minutosLongCapt;
    @Column(name = "segundos_long_capt")
    private Double segundosLongCapt;
    @Column(name = "altitud_capt")
    private Double altitudCapt;

    @Column(name = "USOS_CAPTACION")
    private String usosCaptacion;
    
  @Column(name = "id_autoridad")
  private Integer idAutoridad;
  
  @Column(name = "NOMBRE_AUTORIDAD")
  private String nombreAutoridad;
    
    public CaptacionDetalle() {
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPredio() {
        return predio;
    }

    public void setPredio(String predio) {
        this.predio = predio;
    }

    public String getCedulaCatastral() {
        return cedulaCatastral;
    }

    public void setCedulaCatastral(String cedulaCatastral) {
        this.cedulaCatastral = cedulaCatastral;
    }

    public String getNumExpediente() {
        return numExpediente;
    }

    public void setNumExpediente(String numExpediente) {
        this.numExpediente = numExpediente;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    public String getNombreMunicipio() {
        return nombreMunicipio;
    }

    public void setNombreMunicipio(String nombreMunicipio) {
        this.nombreMunicipio = nombreMunicipio;
    }

    public String getTipoFuente() {
        return tipoFuente;
    }

    public void setTipoFuente(String tipoFuente) {
        this.tipoFuente = tipoFuente;
    }

    public String getNombreTipoFuente() {
        return nombreTipoFuente;
    }

    public void setNombreTipoFuente(String nombreTipoFuente) {
        this.nombreTipoFuente = nombreTipoFuente;
    }

    public String getFuenteCapt() {
        return fuenteCapt;
    }

    public void setFuenteCapt(String fuenteCapt) {
        this.fuenteCapt = fuenteCapt;
    }

    public BigDecimal getOfertaHidricaTotalCapt() {
        return ofertaHidricaTotalCapt;
    }

    public void setOfertaHidricaTotalCapt(BigDecimal ofertaHidricaTotalCapt) {
        this.ofertaHidricaTotalCapt = ofertaHidricaTotalCapt;
    }

    public BigDecimal getOfertaDisponibleCapt() {
        return ofertaDisponibleCapt;
    }

    public void setOfertaDisponibleCapt(BigDecimal ofertaDisponibleCapt) {
        this.ofertaDisponibleCapt = ofertaDisponibleCapt;
    }

    public String getTramoCapt() {
        return tramoCapt;
    }

    public void setTramoCapt(String tramoCapt) {
        this.tramoCapt = tramoCapt;
    }

    public BigDecimal getCaudalDisegnoCapt() {
        return caudalDisegnoCapt;
    }

    public void setCaudalDisegnoCapt(BigDecimal caudalDisegnoCapt) {
        this.caudalDisegnoCapt = caudalDisegnoCapt;
    }

    public String getNumResCaudal() {
        return numResCaudal;
    }

    public void setNumResCaudal(String numResCaudal) {
        this.numResCaudal = numResCaudal;
    }

    public String getNombreSr() {
        return nombreSr;
    }

    public void setNombreSr(String nombreSr) {
        this.nombreSr = nombreSr;
    }

    public Integer getGradosLatCapt() {
        return gradosLatCapt;
    }

    public void setGradosLatCapt(Integer gradosLatCapt) {
        this.gradosLatCapt = gradosLatCapt;
    }

    public Integer getMinutosLatCapt() {
        return minutosLatCapt;
    }

    public void setMinutosLatCapt(Integer minutosLatCapt) {
        this.minutosLatCapt = minutosLatCapt;
    }

    public Double getSegundosLatCapt() {
        return segundosLatCapt;
    }

    public void setSegundosLatCapt(Double segundosLatCapt) {
        this.segundosLatCapt = segundosLatCapt;
    }

    public Integer getGradosLongCapt() {
        return gradosLongCapt;
    }

    public void setGradosLongCapt(Integer gradosLongCapt) {
        this.gradosLongCapt = gradosLongCapt;
    }

    public Integer getMinutosLongCapt() {
        return minutosLongCapt;
    }

    public void setMinutosLongCapt(Integer minutosLongCapt) {
        this.minutosLongCapt = minutosLongCapt;
    }

    public Double getSegundosLongCapt() {
        return segundosLongCapt;
    }

    public void setSegundosLongCapt(Double segundosLongCapt) {
        this.segundosLongCapt = segundosLongCapt;
    }

    public Double getAltitudCapt() {
        return altitudCapt;
    }

    public void setAltitudCapt(Double altitudCapt) {
        this.altitudCapt = altitudCapt;
    }

    public String getUsosCaptacion() {
        return usosCaptacion;
    }

    public void setUsosCaptacion(String usosCaptacion) {
        this.usosCaptacion = usosCaptacion;
    }
    
  public Integer getIdAutoridad() {
      return idAutoridad;
  }

  public void setIdAutoridad(Integer idAutoridad) {
      this.idAutoridad = idAutoridad;
  }
  
  public String getNombreAutoridad() {
      return nombreAutoridad;
  }

  public void setNombreAutoridad(String nombreAutoridad) {
      this.nombreAutoridad = nombreAutoridad;
  }
    
    
  
}
