package co.gov.ideam.sirh.red.ideam.model;

import java.io.Serializable;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import javax.xml.bind.annotation.XmlRootElement;
@Entity
@Table(name = "fq_laboratorios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FqLaboratorios.findAll", query = "SELECT f FROM FqLaboratorios f"),
    @NamedQuery(name = "FqLaboratorios.findById", query = "SELECT f FROM FqLaboratorios f WHERE f.idLaboratorio = :idLaboratorio")
    })
public class FqLaboratorios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id_laboratorio", nullable = false)
    private Long idLaboratorio;
    @Column(name = "nombre_responsable")
    private String nombreResponsable;
    @Column(name = "usuario")
    private String usuario;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "consecutivo_laboratorio")
    private String consecutivoLaboratorio;
    @Column(name = "nombre_laboratorio")
    private String nombreLaboratorio;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public void setIdLaboratorio(Long idLaboratorio) {
        this.idLaboratorio = idLaboratorio;
    }

    public Long getIdLaboratorio() {
        return idLaboratorio;
    }

    public void setNombreResponsable(String nombreResponsable) {
        this.nombreResponsable = nombreResponsable;
    }

    public String getNombreResponsable() {
        return nombreResponsable;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setConsecutivoLaboratorio(String consecutivoLaboratorio) {
        this.consecutivoLaboratorio = consecutivoLaboratorio;
    }

    public String getConsecutivoLaboratorio() {
        return consecutivoLaboratorio;
    }

    public void setNombreLaboratorio(String nombreLaboratorio) {
        this.nombreLaboratorio = nombreLaboratorio;
    }

    public String getNombreLaboratorio() {
        return nombreLaboratorio;
    }
}
