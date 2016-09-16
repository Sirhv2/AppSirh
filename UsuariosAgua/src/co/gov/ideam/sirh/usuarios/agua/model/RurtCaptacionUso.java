package co.gov.ideam.sirh.usuarios.agua.model;

import co.gov.ideam.sirh.parametros.model.Lista;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.SequenceGenerator;

/**
 *
 * @author MI EQUIPO
 */
@Entity
@Table(name = "rurt_captacion_uso")
@NamedQueries({
    @NamedQuery(name = "RurtCaptacionUso.findAll", query = "SELECT r FROM RurtCaptacionUso r"),
    @NamedQuery(name = "RurtCaptacionUso.findByCaudalAsignado", query = "SELECT r FROM RurtCaptacionUso r WHERE r.caudalAsignado = :caudalAsignado"),
    @NamedQuery(name = "RurtCaptacionUso.findByCantidadPersonasPermanentes", query = "SELECT r FROM RurtCaptacionUso r WHERE r.cantidadPersonasPermanentes = :cantidadPersonasPermanentes"),
    @NamedQuery(name = "RurtCaptacionUso.findByCantidadPersonasTransitorias", query = "SELECT r FROM RurtCaptacionUso r WHERE r.cantidadPersonasTransitorias = :cantidadPersonasTransitorias"),
    @NamedQuery(name = "RurtCaptacionUso.findByAprovechamiento", query = "SELECT r FROM RurtCaptacionUso r WHERE r.aprovechamiento = :aprovechamiento"),
    @NamedQuery(name = "RurtCaptacionUso.findByTipoUso", query = "SELECT r FROM RurtCaptacionUso r WHERE r.tipoUso = :tipoUso"),
    @NamedQuery(name = "RurtCaptacionUso.findByDetalleUso", query = "SELECT r FROM RurtCaptacionUso r WHERE r.detalleUso = :detalleUso"),
    @NamedQuery(name = "RurtCaptacionUso.findByOtro", query = "SELECT r FROM RurtCaptacionUso r WHERE r.otro = :otro"),
    @NamedQuery(name = "RurtCaptacionUso.findByCantidadAnimales", query = "SELECT r FROM RurtCaptacionUso r WHERE r.cantidadAnimales = :cantidadAnimales"),
    @NamedQuery(name = "RurtCaptacionUso.findByProduccion", query = "SELECT r FROM RurtCaptacionUso r WHERE r.produccion = :produccion"),
    @NamedQuery(name = "RurtCaptacionUso.findByEficiencia", query = "SELECT r FROM RurtCaptacionUso r WHERE r.eficiencia = :eficiencia"),
    @NamedQuery(name = "RurtCaptacionUso.findByArea", query = "SELECT r FROM RurtCaptacionUso r WHERE r.area = :area"),
    @NamedQuery(name = "RurtCaptacionUso.findById", query = "SELECT r FROM RurtCaptacionUso r WHERE r.id = :id"),
    @NamedQuery(name = "RurtCaptacionUso.findByDescripcion", query = "SELECT r FROM RurtCaptacionUso r WHERE r.descripcion = :descripcion"),
    @NamedQuery(name = "RurtCaptacionUso.findByCaptacion", query = "SELECT r FROM RurtCaptacionUso r WHERE r.idCaptacion = :id")
})
public class RurtCaptacionUso implements Serializable {
    
    @GenericGenerator(name = "usos_generator", 
                      strategy = "co.gov.ideam.sirh.usuarios.agua.model.IdeamSirhGenerator",
                      parameters = {@Parameter(name = "AutoridadAmbientalId", value = "codigoAutoridad"),
                                    @Parameter(name = "Codigo", value = "id"),
                                    @Parameter(name = SequenceGenerator.SEQUENCE,  value= "SEQ_USOS_CAPTACION")})
    
    @Id
    @Column(name = "id", nullable = false)
    //@GeneratedValue(generator = "usos_generator")
    private Long id;
    @Column(name = "caudal_asignado")
    private Double caudalAsignado;
    @Column(name = "cantidad_personas_permanentes")
    private Integer cantidadPersonasPermanentes;
    @Column(name = "cantidad_personas_transitorias")
    private Integer cantidadPersonasTransitorias;
    @Column(name = "aprovechamiento")
    private Double aprovechamiento;
    @Column(name = "tipo_uso")
    private Integer tipoUso;
    @Column(name = "detalle_uso")
    private Integer detalleUso;
    @Column(name = "otro")
    private String otro;
    @Column(name = "cantidad_animales")
    private Long cantidadAnimales;
    @Column(name = "produccion")
    private Double produccion;
    @Column(name = "eficiencia")
    private Double eficiencia;
    @Column(name = "area")
    private Double area;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "id_captacion")
    private Long idCaptacion;
    
    @Transient
    private Long codigoAutoridad;
    @Transient
    private Lista objOtroUso;


    @Transient
    private Lista nombreUso;
  
    public RurtCaptacionUso() {
    }

    public RurtCaptacionUso(Long id) {
        this.id = id;
    }

    public Double getCaudalAsignado() {
        return caudalAsignado;
    }

    public void setCaudalAsignado(Double caudalAsignado) {
        this.caudalAsignado = caudalAsignado;
    }

    public Integer getCantidadPersonasPermanentes() {
        return cantidadPersonasPermanentes;
    }

    public void setCantidadPersonasPermanentes(Integer cantidadPersonasPermanentes) {
        this.cantidadPersonasPermanentes = cantidadPersonasPermanentes;
    }

    public Integer getCantidadPersonasTransitorias() {
        return cantidadPersonasTransitorias;
    }

    public void setCantidadPersonasTransitorias(Integer cantidadPersonasTransitorias) {
        this.cantidadPersonasTransitorias = cantidadPersonasTransitorias;
    }

    public Double getAprovechamiento() {
        return aprovechamiento;
    }

    public void setAprovechamiento(Double aprovechamiento) {
        this.aprovechamiento = aprovechamiento;
    }

    public Integer getTipoUso() {
        return tipoUso;
    }

    public void setTipoUso(Integer tipoUso) {
        this.tipoUso = tipoUso;
    }

    public Integer getDetalleUso() {
        return detalleUso;
    }

    public void setDetalleUso(Integer detalleUso) {
        this.detalleUso = detalleUso;
    }

    public String getOtro() {
        return otro;
    }

    public void setOtro(String otro) {
        this.otro = otro;
    }

    public Long getCantidadAnimales() {
        return cantidadAnimales;
    }

    public void setCantidadAnimales(Long cantidadAnimales) {
        this.cantidadAnimales = cantidadAnimales;
    }

    public Double getProduccion() {
        return produccion;
    }

    public void setProduccion(Double produccion) {
        this.produccion = produccion;
    }

    public Double getEficiencia() {
        return eficiencia;
    }

    public void setEficiencia(Double eficiencia) {
        this.eficiencia = eficiencia;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getIdCaptacion() {
        return idCaptacion;
    }

    public void setIdCaptacion(Long idCaptacion) {
        this.idCaptacion = idCaptacion;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
    
    //atributo transient
    public Long getCodigoAutoridad() {
        return codigoAutoridad;
    }

    public void setCodigoAutoridad(Long codigoAutoridad) {
        this.codigoAutoridad = codigoAutoridad;
    }
    
    public void setObjOtroUso(Lista objOtroUso) {
        this.objOtroUso = objOtroUso;
    }

    public Lista getObjOtroUso() {
        return objOtroUso;
    }
    //

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RurtCaptacionUso)) {
            return false;
        }
        RurtCaptacionUso other = (RurtCaptacionUso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.ideam.sirh.fuentes.model.RurtCaptacionUso[ id=" + id + " ]";
    }


    public void setNombreUso(Lista nombreUso) {
        this.nombreUso = nombreUso;
    }

    public Lista getNombreUso() {
        return nombreUso;
    }
}
