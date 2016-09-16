package co.gov.ideam.sirh.usuarios.agua.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@NamedQueries({
  @NamedQuery(name = "PrediosTO.findAll", query = "select o from PrediosTO o where o.codigoAutoridad =:codigoAutoridad"),
  @NamedQuery(name = "PrediosTO.findBySuelo", query = "select o from PrediosTO o where o.tipoSuelo = :tipoSuelo and o.codigoAutoridad = :codigoAutoridad"),
  @NamedQuery(name = "PrediosTO.findByCedulaUsuarioAutoridad", query = "select o from PrediosTO o where upper(o.cedulaCatastral) = :cedulaCatastral and numDocumento = :numDocumento and codigoAutoridad = :codigoAutoridad")
})
@Table(name = "rurv_predios")
public class PrediosTO implements Serializable {
    @Id
    @Column(name="id", nullable = false)    
    private Long codigo;
    @Column(name="id_usuario", nullable = false)    
    private Long codigoUsuario;
    @Column(name="num_documento", nullable = false)    
    private String numDocumento;
    @Column(name="nombre_predio", nullable = true)    
    private String nombre;
    @Column(name="nombre_centro_poblado", nullable = true)    
    private String nombreCentroPoblado;    
    @Column(name="cedula_catastral", nullable = true)    
    private String cedulaCatastral;
    @Column(name="matricula_catastral", nullable = true)    
    private String matriculaCatastral;
    @Column(name="direccion", nullable = true)    
    private String direccion;    
    @Column(name="valor", nullable = true)    
    private String tipoSuelo;    
    @Column(name="departamento", nullable = true)    
    private String departamento;
    @Column(name="municipio", nullable = true)    
    private String municipio;    
    @Column(name="georeferenciacion_lat", nullable = true)    
    private String latitud;  
    @Column(name="georeferenciacion_long", nullable = true)    
    private String longitud;      
    @Column(name="altitud", nullable = true)    
    private Double altitud;
    @Column(name="nombre_usuario", nullable = true)    
    private String nombreUsuario;      
    @Column(name="id_autoridad", nullable = false)    
    private Long codigoAutoridad;
    @Column(name="total_concesiones", nullable = false)    
    private Long totalConcesiones;
    @Column(name="total_permisos_vertimiento", nullable = false)    
    private Long totalPermisosVertimiento;
    @Transient
    private Integer num;
    
    @Transient
    private String erroresValidacion;
    @Transient
    private Boolean validado = null;        
    
    
    public PrediosTO() {
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

    public String getNombreCentroPoblado() {
        return nombreCentroPoblado;
    }

    public void setNombreCentroPoblado(String nombreCentroPoblado) {
        this.nombreCentroPoblado = nombreCentroPoblado;
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

    public String getTipoSuelo() {
        return tipoSuelo;
    }

    public void setTipoSuelo(String tipoSuelo) {
        this.tipoSuelo = tipoSuelo;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public Double getAltitud() {
        return altitud;
    }

    public void setAltitud(Double altitud) {
        this.altitud = altitud;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Long getCodigoAutoridad() {
        return codigoAutoridad;
    }

    public void setCodigoAutoridad(Long codigoAutoridad) {
        this.codigoAutoridad = codigoAutoridad;
    }
    public boolean isValid(){
        if(validado==null){
            boolean valido = true;        
            if(this.matriculaCatastral==null || this.matriculaCatastral.length()==0){
                this.setErroresValidacion("El Predio no tiene matricula catastral");            
                valido = false;
            }
            if(this.tipoSuelo==null){
                this.setErroresValidacion("El Predio no tiene Clasificación del Suelo");
                valido = false;
            }
            if(this.latitud==null || 
               this.latitud.length()==0 || 
               this.longitud==null || 
               this.longitud.length()==0){
                this.setErroresValidacion("El Predio no tiene coordenadas");
                valido = false;
            }
            if(this.totalConcesiones.longValue()==0 && this.totalPermisosVertimiento.longValue()==0){
                this.setErroresValidacion("El Predio no tiene actos administrativos asociados (Concesiones o permisos de vertimiento)");
                valido = false;
            }
            validado = Boolean.valueOf(valido);
        }
        return validado.booleanValue();
    }

    public String getErroresValidacion() {
        return erroresValidacion;
    }

    public void setErroresValidacion(String erroresValidacion) {
        this.erroresValidacion = erroresValidacion;
    }

    public Long getTotalConcesiones() {
        return totalConcesiones;
    }

    public void setTotalConcesiones(Long totalConcesiones) {
        this.totalConcesiones = totalConcesiones;
    }

    public Long getTotalPermisosVertimiento() {
        return totalPermisosVertimiento;
    }

    public void setTotalPermisosVertimiento(Long totalPermisosVertimiento) {
        this.totalPermisosVertimiento = totalPermisosVertimiento;
    }


    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    public String getNumDocumento() {
        return numDocumento;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getNum() {
        return num;
    }
}
