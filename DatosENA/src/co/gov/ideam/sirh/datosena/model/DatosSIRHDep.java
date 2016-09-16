package co.gov.ideam.sirh.datosena.model;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Lis
 */
@Entity
@Table(name = "cantidad_sirh_departamento_v")

@NamedQueries({
    @NamedQuery(name = "DatosSIRHDep.findAll", query = "SELECT e FROM DatosSIRHDep e")})
public class DatosSIRHDep implements Serializable {
   
    @Id
    @Column(name = "id_departamento")
    private Long id;
    
    @Column(name = "departamento")
    private String departamento;
    @Column(name = "cantidad_capt")
    private Integer cantidad_capt;
    @Column(name = "cantidad_vert")
    private Integer cantidad_vert;
    @Column(name = "fechaConsulta")
    private Calendar fechaConsulta;

    public DatosSIRHDep() {
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setCantidad_capt(Integer cantidad_capt) {
        this.cantidad_capt = cantidad_capt;
    }

    public Integer getCantidad_capt() {
        return cantidad_capt;
    }

    public void setCantidad_vert(Integer cantidad_vert) {
        this.cantidad_vert = cantidad_vert;
    }

    public Integer getCantidad_vert() {
        return cantidad_vert;
    }

    public void setFechaConsulta(Calendar fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

    public Calendar getFechaConsulta() {
        return fechaConsulta;
    }
}
