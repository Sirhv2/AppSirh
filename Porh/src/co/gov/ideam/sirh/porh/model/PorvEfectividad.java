package co.gov.ideam.sirh.porh.model;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
  @NamedQuery(name = "PorvEfectividad.findAllByParametroFecha", query = "select o from PorvEfectividad o where o.idParametro = :codigoParametro and o.fechaMuestreo <= :fechaLimite and o.idFuente = :codigoFuente and o.idTramo = :codigoTramo order by o.fechaMuestreo ASC")
})
@Table(name = "PORV_EFECTIVIDAD")
public class PorvEfectividad implements Serializable {
    @Column(name="FECHA_MUESTREO")
    private Date fechaMuestreo;
    @Id
    @Column(name="ID_MEDICION", nullable = false)
    private Long idMedicion;
    @Column(length = 200)
    private String parametro;
    private Float valor;
    @Column(name="ID_PARAMETRO", nullable = false)
    private Long idParametro;
    @Column(name="ID_PUNTO", nullable = false)
    private Long idPunto;
    @Column(name="NOMBRE_PUNTO", nullable = false)
    private String nombrePunto;
    @Column(name="ID_FUENTE", nullable = false)
    private Long idFuente;
    @Column(name="ID_TRAMO", nullable = false)
    private Long idTramo;
    
    

    public PorvEfectividad() {
    }

    public PorvEfectividad(Date fechaMuestreo, Long idMedicion,
                           String parametro, Float valor) {
        this.fechaMuestreo = fechaMuestreo;
        this.idMedicion = idMedicion;
        this.parametro = parametro;
        this.valor = valor;
    }

    public Date getFechaMuestreo() {
        return fechaMuestreo;
    }

    public void setFechaMuestreo(Date fechaMuestreo) {
        this.fechaMuestreo = fechaMuestreo;
    }

    public Long getIdMedicion() {
        return idMedicion;
    }

    public void setIdMedicion(Long idMedicion) {
        this.idMedicion = idMedicion;
    }

    public String getParametro() {
        return parametro;
    }

    public void setParametro(String parametro) {
        this.parametro = parametro;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public Long getIdParametro() {
        return idParametro;
    }

    public void setIdParametro(Long idParametro) {
        this.idParametro = idParametro;
    }

    public Long getIdPunto() {
        return idPunto;
    }

    public void setIdPunto(Long idPunto) {
        this.idPunto = idPunto;
    }

    public String getNombrePunto() {
        return nombrePunto;
    }

    public void setNombrePunto(String nombrePunto) {
        this.nombrePunto = nombrePunto;
    }

    public Long getIdFuente() {
        return idFuente;
    }

    public void setIdFuente(Long idFuente) {
        this.idFuente = idFuente;
    }

    public Long getIdTramo() {
        return idTramo;
    }

    public void setIdTramo(Long idTramo) {
        this.idTramo = idTramo;
    }
}
