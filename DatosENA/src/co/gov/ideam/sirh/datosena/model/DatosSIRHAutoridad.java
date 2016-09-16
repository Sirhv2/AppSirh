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
@Table(name = "datosSirh_Autoridad_v")

@NamedQueries({
    @NamedQuery(name = "DatosSIRHAutoridad.findById", query = "SELECT e FROM DatosSIRHAutoridad e WHERE e.id = :id"),
    @NamedQuery(name = "DatosSIRHAutoridad.findSigla", query = "SELECT e FROM DatosSIRHAutoridad e WHERE e.sigla = :sigla"),
    @NamedQuery(name = "DatosSIRHAutoridad.findAll", query = "SELECT e FROM DatosSIRHAutoridad e")})
public class DatosSIRHAutoridad implements Serializable {
   
    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "sigla")
    private String sigla;
    @Column(name = "usuarios")
    private Integer usuarios;
    @Column(name = "fuentes")
    private Integer fuentes;
    
    @Column(name = "concesiones")
    private Integer concesiones;
    @Column(name = "captaciones")
    private Integer captaciones;
    @Column(name = "permisovertimiento")
    private Integer permisovertimiento;
    @Column(name = "vertimientos")
    private Integer vertimientos;
    @Column(name = "puntosmonitoreo")
    private Integer puntosmonitoreo;
    @Column(name = "mediciones")
    private Integer mediciones;
    @Column(name = "fechaConsulta")
    private Calendar fechaConsulta;


    public DatosSIRHAutoridad() {
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

    public void setPermisovertimiento(Integer permisovertimiento) {
        this.permisovertimiento = permisovertimiento;
    }

    public Integer getPermisovertimiento() {
        return permisovertimiento;
    }

    public void setPuntosmonitoreo(Integer puntosmonitoreo) {
        this.puntosmonitoreo = puntosmonitoreo;
    }

    public Integer getPuntosmonitoreo() {
        return puntosmonitoreo;
    }

    public void setMediciones(Integer mediciones) {
        this.mediciones = mediciones;
    }

    public Integer getMediciones() {
        return mediciones;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getSigla() {
        return sigla;
    }


}
