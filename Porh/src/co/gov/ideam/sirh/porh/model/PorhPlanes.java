package co.gov.ideam.sirh.porh.model;

import co.gov.ideam.sirh.fuentes.model.FnttFuente;

import co.gov.ideam.sirh.parametros.model.Lista;

import java.io.Serializable;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.SequenceGenerator;
/**
 * Encapcula la informacion correspondiente a un plan de ordenamiento de una fuente
 * en una jurisdiccion
 */
@Entity
@NamedQueries({
  @NamedQuery(name = "PorhPlanes.findAll", query = "select o from PorhPlanes o"),
  @NamedQuery(name = "PorhPlanes.findByAutoridad", query = "select o from PorhPlanes o where o.codigoFuente = :codigoFuente and o.codigoAutoridad = :codigoAutoridad"),
  @NamedQuery(name = "PorhPlanes.find", query = "select o from PorhPlanes o where o.codigo = :codigo"),
  @NamedQuery(name = "PorhPlanes.findByResolucion", query = "select o from PorhPlanes o where o.numeroActo = :numeroActo and o.codigoAutoridad = :codigoAutoridad")
})
@Table(name = "porh_planes")
public class PorhPlanes implements Serializable {
    @Id
    @Column(name="id", nullable = false)
    private Long codigo;    
    @Column(name="archivo")
    private byte[] archivo;
    @Column(name="descripcion")
    private String descripcion;
    @Column(name="otros_criterios")
    private String otrosCriterios;
    @Column(name="fecha_expedicion", nullable = false)
    private Timestamp fechaExpedicion;
    @Column(name="fecha_vigencia", nullable = false)
    private Timestamp fechaVigencia;
    @Column(name="id_autoridad", nullable = false)
    private Long codigoAutoridad;
    @Column(name="id_fuente", nullable = false)
    private Long codigoFuente;
    @Column(name="numero_acto", nullable = false)
    private String numeroActo;    
    @Transient
    private List<PorhPriorizacion> listaPriorizacion;
    @Transient
    private List<Lista> criteriosSeleccionados;
    
    public PorhPlanes() {
    }

    public PorhPlanes(byte[] archivo, String descripcion,
                      Timestamp fechaExpedicion, Timestamp fechaVigencia,
                      Long codigo, Long codigoAutoridad, Long codigoFuente,
                      String numeroActo,String otrosCriterios) {
        this.archivo = archivo;
        this.descripcion = descripcion;
        this.fechaExpedicion = fechaExpedicion;
        this.fechaVigencia = fechaVigencia;
        this.codigo = codigo;
        this.codigoAutoridad = codigoAutoridad;
        this.codigoFuente= codigoFuente;
        this.numeroActo = numeroActo;
        this.otrosCriterios = otrosCriterios;
    }

    public byte[] getArchivo() {
        return archivo;
    }

    public void setArchivo(byte[] archivo) {
        this.archivo = archivo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getOtrosCriterios() {
        return otrosCriterios;
    }

    public void setOtrosCriterios(String otrosCriterios) {
        this.otrosCriterios = otrosCriterios;
    }
    
    public Timestamp getFechaExpedicion() {
        return fechaExpedicion;
    }

    public void setFechaExpedicion(Timestamp fecha_expedicion) {
        this.fechaExpedicion = fecha_expedicion;
    }

    public Timestamp getFechaVigencia() {
        return fechaVigencia;
    }

    public void setFechaVigencia(Timestamp fecha_vigencia) {
        this.fechaVigencia = fecha_vigencia;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long id) {
        this.codigo = id;
    }

    public Long getCodigoAutoridad() {
        return codigoAutoridad;
    }

    public void setCodigoAutoridad(Long codigoAutoridad) {
        this.codigoAutoridad = codigoAutoridad;
    }

    public Long getCodigoFuente() {
        return codigoFuente;
    }

    public void setCodigoFuente(Long codigoFuente) {
        this.codigoFuente = codigoFuente;
    }

    public String getNumeroActo() {
        return numeroActo;
    }

    public void setNumeroActo(String numeroActo) {
        this.numeroActo = numeroActo;
    }

    public List<PorhPriorizacion> getListaPriorizacion() {
        return listaPriorizacion;
    }

    public void setListaPriorizacion(List<PorhPriorizacion> listaPriorizacion) {
        this.listaPriorizacion = listaPriorizacion;
    }

    public List<Lista> getCriteriosSeleccionados() {
        return criteriosSeleccionados;
    }

    public void setCriteriosSeleccionados(List<Lista> criteriosSeleccionados) {
        this.criteriosSeleccionados = criteriosSeleccionados;
    }
    
    public void addCriterio(Lista criterio){
        if(this.criteriosSeleccionados == null){
            this.criteriosSeleccionados = new ArrayList<Lista>();
        }
        this.criteriosSeleccionados.add(criterio);
    }
}
