package co.gov.ideam.sirh.datossinc.model;

import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.parametros.model.Lista;
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
  @NamedQuery(name = "RepresentanteSinc.find", query = "select o from RepresentanteSinc o where o.codigo = :codigo"),
  @NamedQuery(name = "RepresentanteSinc.findByUsuario", query = "select o from RepresentanteSinc o where o.codigoUsuario = :codigoUsuario")
})
@Table(name = "rurt_representantes_sinc")
public class RepresentanteSinc implements Serializable{
  
    @Id
    //@GeneratedValue(generator = "representantes_generator")        
    @Column(name="id", nullable = false)    
    private Long codigo;
    @Column(name="id_usuario", nullable = false)    
    private Long codigoUsuario;
    @Column(name="tipo_id", nullable = false)    
    private Integer codigoTipoDocumento;
    @Column(name="numero_documento", nullable = false)    
    private String numeroDocumento;
    @Column(name="nombres", nullable = false)    
    private String nombres;
    @Column(name="apellidos", nullable = false)    
    private String apellidos;
    @Column(name="direccion", nullable = false)    
    private String direccion;
    @Column(name="id_divipola_depto", nullable = false)    
    private Long codigoDepartamento;
    @Column(name="id_divipola_municipio", nullable = false)    
    private Long codigoMunicipio;
    @Column(name="telefono", nullable = true)    
    private String telefono;
    @Column(name="email", nullable = true)    
    private String email;
  
    
    @Transient
    private Lista tipoDocumento;
    @Transient
    private Divipola departamento;
    @Transient
    private Divipola municipio;
    @Transient
    private Long codigoAutoridadAmbiental;
      
      
    //** Lisbeth Integracion datos 
    @Column(name="origen")
    private String origen;    
    @Column(name="id_origen")
    private String id_origen;
    @Column(name="trasmitido")
    private Integer trasmitido;
    
      
    public RepresentanteSinc() {
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

    public Integer getCodigoTipoDocumento() {
        return codigoTipoDocumento;
    }

    public void setCodigoTipoDocumento(Integer codigoTipoDocumento) {
        this.codigoTipoDocumento = codigoTipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Long getCodigoDepartamento() {
        return codigoDepartamento;
    }

    public void setCodigoDepartamento(Long codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
    }

    public Long getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(Long codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Lista getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(Lista tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
        this.codigoTipoDocumento = this.tipoDocumento.getCodigo();
    }

    public Divipola getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Divipola departamento) {
        this.departamento = departamento;
        this.codigoDepartamento = this.departamento.getId();
    }

    public Divipola getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Divipola municipio) {
        this.municipio = municipio;
        if(this.municipio!=null){
            this.codigoMunicipio = this.municipio.getId();
        }
    }

    public Long getCodigoAutoridadAmbiental() {
        return codigoAutoridadAmbiental;
    }

    public void setCodigoAutoridadAmbiental(Long codigoAutoridadAmbiental) {
        this.codigoAutoridadAmbiental = codigoAutoridadAmbiental;
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
