package co.gov.ideam.sirh.auditoria.model;
// HUGO 20141030

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name = "SIRH_NOV_TRANSMITIR")
@NamedQueries( { @NamedQuery(name = "NovTransmitir.findAll",      query = "SELECT p FROM NovTransmitir p")
               } 
             )


public class NovTransmitir implements Serializable {
    
    @Id 
    @Column(name = "FECHAREGISTRO")
    private Timestamp fecha;
    
    @Column(name = "SERVICENAME")
    private String serviceName;
    
    @Column(name = "METHODNAME")
    private String methodName;
    
    @Column(name = "DATA")
    private String data;
    

    public NovTransmitir() {
    }

    @PreUpdate
    @PrePersist
     public void darFechaActual() {
        this.setFecha(new Timestamp(System.currentTimeMillis()));
    }
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof NovTransmitir)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.ideam.sirh.auditoria.model.NovTransmitir[ methodName=" + getMethodName() +  " ]";
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
