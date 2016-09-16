package co.gov.ideam.sirh.usuarios.agua.model;

import co.gov.ideam.sirh.parametros.model.Lista;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "rurt_captacion_uso")
public class RurtCaptacionUsoPOJO implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
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

    public RurtCaptacionUsoPOJO() {
    }

    public RurtCaptacionUsoPOJO(Long id) {
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
 
}
