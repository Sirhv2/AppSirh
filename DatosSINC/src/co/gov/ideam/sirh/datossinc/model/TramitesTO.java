package co.gov.ideam.sirh.datossinc.model;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * Utilizado para cargar datos desde la vista y mostrar una lista de todas las
 * tramites registrados en el sistema de rua
 */
 @Entity
 @NamedQueries({
   @NamedQuery(name = "TramitesTO.findAll", query = "select o from TramitesTO o"),
   @NamedQuery(name = "TramitesTO.findByExpediente", query = "select o from TramitesTO o where upper(o.numeroExpediente) like :numeroExpediente"),
   @NamedQuery(name = "TramitesTO.findByAutoridad", query = "select o from TramitesTO o where o.codigoAutoridad =:codigoAutoridad")

 })
 @Table(name = "rurt_tramites_rua_v")
public class TramitesTO implements Serializable{
    @Id    
    @Column(name = "id", nullable = false)
    private Long codigo;
    @Column(name = "num_expediente", nullable = false)
    private String numeroExpediente;
    @Column(name = "num_res_caudal", nullable = false)
    private String resolucionCaudal;
    @Column(name = "caudal", nullable = false)
    private Double caudal;
    @Column(name = "fecha_inicio", nullable = false)
    private Calendar fecha_inicio;
    @Column(name = "fecha_fin", nullable = false)
    private Calendar fecha_fin;
   
 
    @Column(name = "id_predio", nullable = true)
    private Long codigoPredio;
    @Column(name = "predio", nullable = true)
    private String nombrePredio;
    @Column(name = "usuario", nullable = true)
    private String nombreUsuario;
    @Column(name = "id_usuario", nullable = true)
    private Long codigoUsuario;
    @Column(name = "id_autoridad", nullable = true)
    private Long codigoAutoridad;
    @Column(name = "autoridad", nullable = true)
    private String nombreAutoridad;
    @Column(name = "aprobado_trasmitido", nullable = true)
    private Integer aprobadoTrasmitido;
 
    @Column(name = "tipo_tramite")
    private String tipoTramite;
  
    @Column(name = "aprobado")
    private String aprobado;
    @Column(name = "origen")
    private String origen;
    

    public TramitesTO() {
        super();
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNumeroExpediente() {
        return numeroExpediente;
    }

    public void setNumeroExpediente(String numeroExpediente) {
        this.numeroExpediente = numeroExpediente;
    }

    public String getResolucionCaudal() {
        return resolucionCaudal;
    }

    public void setResolucionCaudal(String resolucionCaudal) {
        this.resolucionCaudal = resolucionCaudal;
    }

  

    public Long getCodigoPredio() {
        return codigoPredio;
    }

    public void setCodigoPredio(Long codigoPredio) {
        this.codigoPredio = codigoPredio;
    }

    public String getNombrePredio() {
        return nombrePredio;
    }

    public void setNombrePredio(String nombrePredio) {
        this.nombrePredio = nombrePredio;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Long getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Long codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public Long getCodigoAutoridad() {
        return codigoAutoridad;
    }

    public void setCodigoAutoridad(Long codigoAutoridad) {
        this.codigoAutoridad = codigoAutoridad;
    }

    public void setFecha_inicio(Calendar fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Calendar getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_fin(Calendar fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public Calendar getFecha_fin() {
        return fecha_fin;
    }


    public void setNombreAutoridad(String nombreAutoridad) {
        this.nombreAutoridad = nombreAutoridad;
    }

    public String getNombreAutoridad() {
        return nombreAutoridad;
    }

    public void setAprobadoTrasmitido(Integer aprobadoTrasmitido) {
        this.aprobadoTrasmitido = aprobadoTrasmitido;
    }

    public Integer getAprobadoTrasmitido() {
        return aprobadoTrasmitido;
    }

    public void setTipoTramite(String tipoTramite) {
        this.tipoTramite = tipoTramite;
    }

    public String getTipoTramite() {
        return tipoTramite;
    }

    public void setCaudal(Double caudal) {
        this.caudal = caudal;
    }

    public Double getCaudal() {
        return caudal;
    }

    public void setAprobado(String aprobado) {
        this.aprobado = aprobado;
    }

    public String getAprobado() {
        return aprobado;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getOrigen() {
        return origen;
    }
}
