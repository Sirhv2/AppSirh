package co.gov.ideam.sirh.usuarios.agua.model;

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
 @NamedQueries({
                @NamedQuery(name = "ConsultarPermisosV.findAll", query = "SELECT c FROM ConsultarPermisosV c") ,
                @NamedQuery(name = "ConsultarPermisosV.findByTipopermiso", query = "SELECT c FROM ConsultarPermisosV c WHERE c.tipopermiso = :tipopermiso"),
                @NamedQuery(name = "ConsultarPermisosV.findByEstadoinfotecnica", query = "SELECT c FROM ConsultarPermisosV c WHERE c.estadoinfotecnica = :estadoinfotecnica"),
                @NamedQuery(name = "ConsultarPermisosV.findByInfotecnica", query = "SELECT c FROM ConsultarPermisosV c WHERE c.infotecnica = :infotecnica"),
                @NamedQuery(name = "ConsultarPermisosV.findByIdAutoridad", query = "SELECT c FROM ConsultarPermisosV c WHERE c.idAutoridad = :idAutoridad"),
                @NamedQuery(name = "ConsultarPermisosV.findByAutoridad", query = "SELECT c FROM ConsultarPermisosV c WHERE c.autoridad = :autoridad")})
@Table(name = "consultar_permisos_v")

public class ConsultarPermisosV implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id    
    @Column(name = "ID")
    private Long id;
    @Column(name = "CODIGOTIPOIDENTIFICACION")
    private Long codigotipoidentificacion;
    @Column(name = "IDENTIFICACIONPERSONA")
    private String identificacionpersona;
    @Column(name = "TIPOPERSONA")
    private Long tipopersona;
    @Column(name = "PRIMERNOMBRE")
    private String primernombre;
    @Column(name = "PRIMERAPELLIDO")
    private String primerapellido;
    @Column(name = "RAZONSOCIAL")
    private String razonsocial;
    @Column(name = "NOMBREPREDIO")
    private String nombrepredio;
    @Column(name = "CEDULACATASTRAL")
    private String cedulacatastral;
    @Column(name = "MATRICULAINMOBILIARIA")
    private String matriculainmobiliaria;
    @Column(name = "IDMUNICIPIOPREDIO")
    private Long idmunicipiopredio;
    @Column(name = "IDDEPTOPREDIO")
    private Long iddeptopredio;
    @Column(name = "DIRECCIONPREDIO")
    private String direccionpredio;
    @Column(name = "NUMEXPEDIENTE")
    private String numexpediente;
    @Column(name = "NUMRESOLUCION")
    private String numresolucion;
    @Column(name = "FECHAEXPEDICION")
    private Calendar fechaexpedicion;
    @Column(name = "FECHAVENCIMIENTO")
    private Calendar fechavencimiento;
    @Column(name = "CANTIDADRECURSOOTORGADO")
    private Double cantidadrecursootorgado;
    @Column(name = "TIPOPERMISO")
    private String tipopermiso;
    @Column(name = "ESTADOINFOTECNICA")
    private String estadoinfotecnica;
    @Column(name = "INFOTECNICA")
    private String infotecnica;
    @Column(name = "ID_AUTORIDAD")
    private Long idAutoridad;
    @Column(name = "AUTORIDAD")
    private String autoridad;

    public ConsultarPermisosV() {
    }


    public String getIdentificacionpersona() {
        return identificacionpersona;
    }

    public void setIdentificacionpersona(String identificacionpersona) {
        this.identificacionpersona = identificacionpersona;
    }


    public String getPrimernombre() {
        return primernombre;
    }

    public void setPrimernombre(String primernombre) {
        this.primernombre = primernombre;
    }

    public String getPrimerapellido() {
        return primerapellido;
    }

    public void setPrimerapellido(String primerapellido) {
        this.primerapellido = primerapellido;
    }

    public String getRazonsocial() {
        return razonsocial;
    }

    public void setRazonsocial(String razonsocial) {
        this.razonsocial = razonsocial;
    }

    public String getNombrepredio() {
        return nombrepredio;
    }

    public void setNombrepredio(String nombrepredio) {
        this.nombrepredio = nombrepredio;
    }

    public String getCedulacatastral() {
        return cedulacatastral;
    }

    public void setCedulacatastral(String cedulacatastral) {
        this.cedulacatastral = cedulacatastral;
    }

    public String getMatriculainmobiliaria() {
        return matriculainmobiliaria;
    }

    public void setMatriculainmobiliaria(String matriculainmobiliaria) {
        this.matriculainmobiliaria = matriculainmobiliaria;
    }


    public String getDireccionpredio() {
        return direccionpredio;
    }

    public void setDireccionpredio(String direccionpredio) {
        this.direccionpredio = direccionpredio;
    }

    public String getNumexpediente() {
        return numexpediente;
    }

    public void setNumexpediente(String numexpediente) {
        this.numexpediente = numexpediente;
    }

    public String getNumresolucion() {
        return numresolucion;
    }

    public void setNumresolucion(String numresolucion) {
        this.numresolucion = numresolucion;
    }


    public Double getCantidadrecursootorgado() {
        return cantidadrecursootorgado;
    }

    public void setCantidadrecursootorgado(Double cantidadrecursootorgado) {
        this.cantidadrecursootorgado = cantidadrecursootorgado;
    }

    public String getTipopermiso() {
        return tipopermiso;
    }

    public void setTipopermiso(String tipopermiso) {
        this.tipopermiso = tipopermiso;
    }

    public String getEstadoinfotecnica() {
        return estadoinfotecnica;
    }

    public void setEstadoinfotecnica(String estadoinfotecnica) {
        this.estadoinfotecnica = estadoinfotecnica;
    }

    public String getInfotecnica() {
        return infotecnica;
    }

    public void setInfotecnica(String infotecnica) {
        this.infotecnica = infotecnica;
    }

  

  
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setCodigotipoidentificacion(Long codigotipoidentificacion) {
        this.codigotipoidentificacion = codigotipoidentificacion;
    }

    public Long getCodigotipoidentificacion() {
        return codigotipoidentificacion;
    }

    public void setTipopersona(Long tipopersona) {
        this.tipopersona = tipopersona;
    }

    public Long getTipopersona() {
        return tipopersona;
    }

    public void setIdmunicipiopredio(Long idmunicipiopredio) {
        this.idmunicipiopredio = idmunicipiopredio;
    }

    public Long getIdmunicipiopredio() {
        return idmunicipiopredio;
    }

    public void setIddeptopredio(Long iddeptopredio) {
        this.iddeptopredio = iddeptopredio;
    }

    public Long getIddeptopredio() {
        return iddeptopredio;
    }

    public void setFechaexpedicion(Calendar fechaexpedicion) {
        this.fechaexpedicion = fechaexpedicion;
    }

    public Calendar getFechaexpedicion() {
        return fechaexpedicion;
    }

    public void setFechavencimiento(Calendar fechavencimiento) {
        this.fechavencimiento = fechavencimiento;
    }

    public Calendar getFechavencimiento() {
        return fechavencimiento;
    }

    public void setIdAutoridad(Long idAutoridad) {
        this.idAutoridad = idAutoridad;
    }

    public Long getIdAutoridad() {
        return idAutoridad;
    }

    public void setAutoridad(String autoridad) {
        this.autoridad = autoridad;
    }

    public String getAutoridad() {
        return autoridad;
    }
}
