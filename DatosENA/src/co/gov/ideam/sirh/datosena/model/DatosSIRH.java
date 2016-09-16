
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
@Table(name = "datos_sirh_v")

@NamedQueries({
    @NamedQuery(name = "DatosSIRH.findAll", query = "SELECT e FROM DatosSIRH e")})
public class DatosSIRH implements Serializable {
   
    @Id
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "usuarios")
    private Integer usuarios;
    @Column(name = "fuentes")
    private Integer fuentes;
    @Column(name = "permisos_vertimientos")
    private Integer permisos_vertimientos;
    @Column(name = "concesiones")
    private Integer concesiones;
    
    @Column(name = "captaciones")
    private Integer captaciones;
    @Column(name = "vertimientos")
    private Integer vertimientos;
    @Column(name = "fechaConsulta")
    private Calendar fechaConsulta;

    public DatosSIRH() {
    }


    public void setUsuarios(Integer usuarios) {
        this.usuarios = usuarios;
    }

    public Integer getUsuarios() {
        return usuarios;
    }

    public void setFuentes(Integer fuentes) {
        this.fuentes = fuentes;
    }

    public Integer getFuentes() {
        return fuentes;
    }

    public void setPermisos_vertimientos(Integer permisos_vertimientos) {
        this.permisos_vertimientos = permisos_vertimientos;
    }

    public Integer getPermisos_vertimientos() {
        return permisos_vertimientos;
    }

    public void setConcesiones(Integer concesiones) {
        this.concesiones = concesiones;
    }

    public Integer getConcesiones() {
        return concesiones;
    }

    public void setFechaConsulta(Calendar fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

    public Calendar getFechaConsulta() {
        return fechaConsulta;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setCaptaciones(Integer captaciones) {
        this.captaciones = captaciones;
    }

    public Integer getCaptaciones() {
        return captaciones;
    }

    public void setVertimientos(Integer vertimientos) {
        this.vertimientos = vertimientos;
    }

    public Integer getVertimientos() {
        return vertimientos;
    }
}
