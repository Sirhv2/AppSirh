package co.gov.ideam.sirh.webservices.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
  @NamedQuery(name = "Sentencia.findAll", query = "select o from Sentencia o"),
  @NamedQuery(name = "Sentencia.find", query = "select o from Sentencia o where o.ws_id = :codigo")
})
@Table(name = "sirh_sentencias_ws")
public class Sentencia implements Serializable {
    @Id
    @Column(name="ws_id", nullable = false)
    private Long ws_id;
    @Column(name="ws_sentencia")
    private String ws_sentencia;
    @Column(name="ws_descripcion")
    private String ws_descripcion;
    public Sentencia() {
    }

    public Sentencia(Long ws_id, String ws_sentencia) {
        this.ws_id = ws_id;
        this.ws_sentencia = ws_sentencia;
    }

    public Long getWs_id() {
        return ws_id;
    }

    public void setWs_id(Long ws_id) {
        this.ws_id = ws_id;
    }

    public String getWs_sentencia() {
        return ws_sentencia;
    }

    public void setWs_sentencia(String ws_sentencia) {
        this.ws_sentencia = ws_sentencia;
    }

    public String getWs_descripcion() {
        return ws_descripcion;
    }

    public void setWs_descripcion(String ws_descripcion) {
        this.ws_descripcion = ws_descripcion;
    }
}
