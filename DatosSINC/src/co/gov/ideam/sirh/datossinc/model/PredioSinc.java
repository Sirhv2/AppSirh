package co.gov.ideam.sirh.datossinc.model;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.parametros.model.Lista;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@NamedQueries({
  @NamedQuery(name = "PredioSinc.findAll", query = "select o from PredioSinc o where o.codigoUsuario = :codigoUsuario order by o.nombre"),
  @NamedQuery(name = "PredioSinc.find", query = "select o from PredioSinc o where o.codigo = :codigo"),
  @NamedQuery(name = "PredioSinc.findByCedula", query = "select o from PredioSinc o where upper(o.cedulaCatastral) = :cedulaCatastral"),
  @NamedQuery(name = "PredioSinc.findByCedulaUsuarioAutoridad", query = "select o from PredioSinc o where upper(o.cedulaCatastral) = :cedulaCatastral and codigoUsuario = :codigoUsuario and codigoAutoridad = :codigoAutoridad")
 
})
@Table(name = "rurt_predios_sinc")
public class PredioSinc implements Serializable{
 
    @Id
    //@GeneratedValue(generator = "predios_generator")        
    @Column(name="id", nullable = false)    
    private Long codigo;
    @Column(name="id_usuario", nullable = false)    
    private Long codigoUsuario;
    @Column(name="nombre_predio", nullable = true)    
    private String nombre;
    @Column(name="tipo_tenenecia", nullable = true)    
    private Long codigoTipoTenencia;
    @Column(name="cedula_catastral", nullable = true)    
    private String cedulaCatastral;
    @Column(name="matricula_catastral", nullable = true)    
    private String matriculaCatastral;
    @Column(name="direccion", nullable = true)    
    private String direccion;
    @Column(name="tipo_suelo", nullable = true)    
    private Long codigoTipoSuelo;
    @Column(name="id_divipola_municipio", nullable = true)    
    private Long codigoMunicipio;
    @Column(name="id_divipola_depto", nullable = true)    
    private Long codigoDepartamento;
    @Column(name="sistema_referencia", nullable = true)    
    private Long codigoSistemaReferencia;    
    @Column(name="lat_grados", nullable = true)    
    private Integer gradosLatitud;
    @Column(name="lat_minutos", nullable = true)    
    private Integer minutosLatitud;
    @Column(name="lat_segundos", nullable = true)    
    private Double segundosLatitud;
    @Column(name="area", nullable = true)    
    private Double area;
    @Column(name="nombre_centro_poblado", nullable = true)    
    private String nombreCentroPoblado;
    @Column(name="altitud", nullable = true)    
    private Double altitud;
    @Column(name="observaciones")
    private String observaciones;    
    @Column(name="tipo_centro_poblado", nullable = true)    
    private Long codigoTipoCentroPoblado;
    
    @Column(name="long_grados", nullable = true)    
    private Integer gradosLongitud;
    @Column(name="long_minutos", nullable = true)    
    private Integer minutosLongitud;
    @Column(name="long_segundos", nullable = true)    
    private Double segundosLongitud;
    
    @Transient
    private Divipola municipio;
    @Transient
    private Divipola departamento;
    @Transient
    private Lista tipoSuelo;    
    @Transient
    private Lista tipoCentroPoblado;
    @Transient
    private Autoridades autoridadAmbiental;
    @Transient
    private Long codigoAutoridadAmbiental;
    @Transient
    private Lista sistemaReferencia;
    @Transient
    private List listaConcesiones;
    @Transient
    private List listaPermisos;
    //pilar
    @Transient
    private List listaCaptacionesSinConc;
    
    //** Lisbeth Integracion datos 
    @Column(name="origen")
    private String origen;    
    @Column(name="id_origen")
    private String id_origen;
    @Column(name="trasmitido")
    private Integer trasmitido;
    
    
    public PredioSinc() {
        super();
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Long getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Long codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getCodigoTipoTenencia() {
        return codigoTipoTenencia;
    }

    public void setCodigoTipoTenencia(Long codigoTipoTenencia) {
        this.codigoTipoTenencia = codigoTipoTenencia;
    }

    public String getCedulaCatastral() {
        return cedulaCatastral;
    }

    public void setCedulaCatastral(String cedulaCatastral) {
        this.cedulaCatastral = cedulaCatastral;
    }

    public String getMatriculaCatastral() {
        return matriculaCatastral;
    }

    public void setMatriculaCatastral(String matriculaCatastral) {
        this.matriculaCatastral = matriculaCatastral;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Long getCodigoTipoSuelo() {
        return codigoTipoSuelo;
    }

    public void setCodigoTipoSuelo(Long codigoTipoSuelo) {
        this.codigoTipoSuelo = codigoTipoSuelo;
    }

    public Long getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(Long codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public Long getCodigoDepartamento() {
        return codigoDepartamento;
    }

    public void setCodigoDepartamento(Long codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
    }

    public Autoridades getAutoridadAmbiental() {
        return autoridadAmbiental;
    }

    public void setAutoridadAmbiental(Autoridades autoridadAmbiental) {
        this.autoridadAmbiental = autoridadAmbiental;
        if(this.autoridadAmbiental!=null){
            this.codigoAutoridadAmbiental = 
                this.autoridadAmbiental.getId().longValue();
        }
    }

    public Long getCodigoAutoridadAmbiental() {
        return codigoAutoridadAmbiental;
    }

    public Divipola getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Divipola municipio) {
        this.municipio = municipio;
        if(this.municipio!=null){
            this.codigoMunicipio = this.municipio.getId();
        }else{
            this.codigoMunicipio = null;
        }
    }

    public Divipola getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Divipola departamento) {
        this.departamento = departamento;
        if(this.departamento!=null){
            this.codigoDepartamento = this.departamento.getId();
        }else{
            this.codigoDepartamento=null;
        }
            
    }

    public Lista getTipoSuelo() {
        return tipoSuelo;
    }

    public void setTipoSuelo(Lista tipoSuelo) {
        this.tipoSuelo = tipoSuelo;
        if(this.tipoSuelo!=null){
            this.codigoTipoSuelo = this.tipoSuelo.getCodigo().longValue();
        }else{
            this.codigoTipoSuelo = null;
        }
        
    }

    public Lista getSistemaReferencia() {
        return sistemaReferencia;
    }

    public void setSistemaReferencia(Lista sistemaReferencia) {
        this.sistemaReferencia = sistemaReferencia;
        if(this.sistemaReferencia!=null){
            this.codigoSistemaReferencia = this.sistemaReferencia.getCodigo().longValue();
        }else{
            this.codigoSistemaReferencia = null;
        }
    }

    public Long getCodigoSistemaReferencia() {
        return codigoSistemaReferencia;
    }

    public void setCodigoSistemaReferencia(Long codigoSistemaReferencia) {
        this.codigoSistemaReferencia = codigoSistemaReferencia;
    }
    public String getNombreCentroPoblado() {
        return nombreCentroPoblado != null ? nombreCentroPoblado : "";
    }


  

    public void setNombreCentroPoblado(String nombreCentroPoblado) {
        this.nombreCentroPoblado = nombreCentroPoblado;
    }

    public List getListaConcesiones() {
        return listaConcesiones;
    }

    public void setListaConcesiones(List listaConcesiones) {
        this.listaConcesiones = listaConcesiones;
    }

    public List getListaPermisos() {
        return listaPermisos;
    }

    public void setListaPermisos(List listaPermisos) {
        this.listaPermisos = listaPermisos;
    }

    public Double getAltitud() {
        return altitud;
    }

    public void setAltitud(Double altitud) {
        this.altitud = altitud;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Long getCodigoTipoCentroPoblado() {        
        return codigoTipoCentroPoblado;
    }

    public void setCodigoTipoCentroPoblado(Long codigoTipoCentroPoblado) {
        this.codigoTipoCentroPoblado = codigoTipoCentroPoblado;
    }

    public Lista getTipoCentroPoblado() {
        return tipoCentroPoblado;
    }

    public void setTipoCentroPoblado(Lista tipoCentroPoblado) {
        this.tipoCentroPoblado = tipoCentroPoblado;
    }

    public Integer getGradosLongitud() {
        return gradosLongitud;
    }

    public void setGradosLongitud(Integer gradosLongitud) {
        this.gradosLongitud = gradosLongitud;
    }

    public Integer getMinutosLongitud() {
        return minutosLongitud;
    }

    public void setMinutosLongitud(Integer minutosLongitud) {
        this.minutosLongitud = minutosLongitud;
    }

    public Double getSegundosLongitud() {
        return segundosLongitud;
    }

    public void setSegundosLongitud(Double segundosLongitud) {
        this.segundosLongitud = segundosLongitud;
    }

    public Integer getGradosLatitud() {
        return gradosLatitud;
    }

    public void setGradosLatitud(Integer gradosLatitud) {
        this.gradosLatitud = gradosLatitud;
    }

    public Integer getMinutosLatitud() {
        return minutosLatitud;
    }

    public void setMinutosLatitud(Integer minutosLatitud) {
        this.minutosLatitud = minutosLatitud;
    }

    public Double getSegundosLatitud() {
        return segundosLatitud;
    }

    public void setSegundosLatitud(Double segundosLatitud) {
        this.segundosLatitud = segundosLatitud;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Double getArea() {
        return area;
    }

    public void setCodigoAutoridadAmbiental(Long codigoAutoridadAmbiental) {
        this.codigoAutoridadAmbiental = codigoAutoridadAmbiental;
    }


    public void setListaCaptacionesSinConc(List listaCaptacionesSinConc) {
        this.listaCaptacionesSinConc = listaCaptacionesSinConc;
    }

    public List getListaCaptacionesSinConc() {
        return listaCaptacionesSinConc;
    }

    public String getNombreCentroPoblado1() {
        return nombreCentroPoblado;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getOrigen() {
        return origen;
    }

    public void setId_origen(String id_origen) {
        this.id_origen = id_origen;
    }

    public String getId_origen() {
        return id_origen;
    }

    public void setTrasmitido(Integer trasmitido) {
        this.trasmitido = trasmitido;
    }

    public Integer getTrasmitido() {
        return trasmitido;
    }
}
