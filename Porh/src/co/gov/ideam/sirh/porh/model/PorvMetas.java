package co.gov.ideam.sirh.porh.model;

import java.io.Serializable;

import java.util.Date;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@NamedQueries({
  @NamedQuery(name = "PorvMetas.findAllByTramo", query = "select o from PorvMetas o where o.idTramo = :codigoTramo")
})
@Table(name = "PORV_METAS")
public class PorvMetas implements Serializable {
    @Column(nullable = false)
    private Date fecha;
    @Id
    @Column(nullable = false)        
    private Long id;
    @Column(length = 200)
    private String parametro;
    @Column(nullable = false, length = 200)
    private String tramo;
    @Column(length = 200)
    private String unidad;
    @Column(name="VALOR_ACTUAL", nullable = false)
    private Double valorActual;
    @Column(name="VALOR_META", nullable = false)
    private Double valorMeta;
    @Column(nullable = false)        
    private Long idTramo;
    @Column(nullable = false)        
    private Long codigoParametro;
    @Column(nullable = false)        
    private Long codigoUnidad;
    @Transient
    private List<PorhAvanceMetas> listaAvanceMetas;
    
    public PorvMetas() {
    }

    public PorvMetas(Date fecha, Long id, String parametro, String tramo,
                     String unidad, Double valorActual, Double valorMeta) {
        this.fecha = fecha;
        this.id = id;
        this.parametro = parametro;
        this.tramo = tramo;
        this.unidad = unidad;
        this.valorActual = valorActual;
        this.valorMeta = valorMeta;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParametro() {
        return parametro;
    }

    public void setParametro(String parametro) {
        this.parametro = parametro;
    }

    public String getTramo() {
        return tramo;
    }

    public void setTramo(String tramo) {
        this.tramo = tramo;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public Double getValorActual() {
        return valorActual;
    }

    public void setValorActual(Double valorActual) {
        this.valorActual = valorActual;
    }

    public Double getValorMeta() {
        return valorMeta;
    }

    public void setValorMeta(Double valorMeta) {
        this.valorMeta = valorMeta;
    }

    public Long getIdTramo() {
        return idTramo;
    }

    public void setIdTramo(Long idTramo) {
        this.idTramo = idTramo;
    }

    public Long getCodigoParametro() {
        return codigoParametro;
    }

    public void setCodigoParametro(Long codigoParametro) {
        this.codigoParametro = codigoParametro;
    }

    public Long getCodigoUnidad() {
        return codigoUnidad;
    }

    public void setCodigoUnidad(Long codigoUnidad) {
        this.codigoUnidad = codigoUnidad;
    }

    public List<PorhAvanceMetas> getListaAvanceMetas() {
        return listaAvanceMetas;
    }

    public void setListaAvanceMetas(List<PorhAvanceMetas> listaAvanceMetas) {
        this.listaAvanceMetas = listaAvanceMetas;
    }
}
