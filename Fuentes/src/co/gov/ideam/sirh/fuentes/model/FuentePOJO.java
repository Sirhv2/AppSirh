package co.gov.ideam.sirh.fuentes.model;


import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "fntt_fuente")
public class FuentePOJO implements Serializable {
    
    @Id
    @Column(name = "id", nullable = false)     
    private Long id;
    
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "es_compartida")
    private Integer esCompartida;
    @Column(name = "codigo_cuencaaa")
    private String codigoCuencaAA;
    @Column(name = "id_tipo_fuente", nullable = false)
    private Integer idTipoFuente;
    @Column(name = "cod_autoridad", nullable = false)
    private Long codAutoridad;
    
    @Transient
    private Long codigoAutoridad;

    
    public FuentePOJO() {
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setEsCompartida(Integer esCompartida) {
        this.esCompartida = esCompartida;
    }

    public Integer getEsCompartida() {
        return esCompartida;
    }

    public void setCodigoCuencaAA(String codigoCuencaAA) {
        this.codigoCuencaAA = codigoCuencaAA;
    }

    public String getCodigoCuencaAA() {
        return codigoCuencaAA;
    }

    public void setIdTipoFuente(Integer idTipoFuente) {
        this.idTipoFuente = idTipoFuente;
    }

    public Integer getIdTipoFuente() {
        return idTipoFuente;
    }

    public void setCodAutoridad(Long codAutoridad) {
        this.codAutoridad = codAutoridad;
    }

    public Long getCodAutoridad() {
        return codAutoridad;
    }


    public void setCodigoAutoridad(Long codigoAutoridad) {
        this.codigoAutoridad = codigoAutoridad;
    }

    public Long getCodigoAutoridad() {
        return codigoAutoridad;
    }
}
