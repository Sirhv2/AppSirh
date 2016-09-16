package co.gov.ideam.sirh.auditoria.model;

import java.io.Serializable;

import java.sql.Date;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "sirh_auditoria")
@NamedQueries( { @NamedQuery(name = "Auditoria.findAll",      query = "SELECT p FROM Auditoria p")
             ,   @NamedQuery(name = "Auditoria.findById",     query = "SELECT p FROM Auditoria p WHERE p.idUsuario = :idUsuario")
             ,   @NamedQuery(name = "Auditoria.findByObjeto", query = "SELECT p FROM Auditoria p WHERE upper(p.objeto) like upper(:objeto) ")
             } )


public class Auditoria implements Serializable {
    
    @Id 
    @Column(name = "fecha")
    private Timestamp fecha;
    @Column(name = "id_usuario")
    private Long idUsuario;
    @Column(name = "id_objeto")
    private Long idObjeto;
    @Column(name = "objeto")
    private String objeto;
    @Column(name = "operacion")
    private String operacion;
    @Column(name = "clase")
    private String clase;
    @Column(name = "metodo")
    private String metodo;

    public Auditoria() {
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdObjeto() {
        return idObjeto;
    }

    public void setIdObjeto(Long idObjeto) {
        this.idObjeto = idObjeto;
    }

    public String getObjeto() {
        return objeto;
    }

    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public Timestamp getFecha() {
        return fecha;
        //return new Timestamp(System.currentTimeMillis());
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
        //this.fecha = new Timestamp(System.currentTimeMillis());
    }
    
    @PreUpdate
    @PrePersist
     public void darFechaActual() {
        this.setFecha(new Timestamp(System.currentTimeMillis()));
    }
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getIdUsuario() != null ? getIdUsuario().hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Auditoria)) {
            return false;
        }
        Auditoria other = (Auditoria)object;
        if ((this.getIdUsuario() == null && other.getIdUsuario() != null) ||
            (this.getIdUsuario() != null && !this.getIdUsuario().equals(other.getIdUsuario()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.ideam.sirh.auditoria.model.Auditoria[ idUsuario=" + getIdUsuario() +  " ]";
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }
}
