package co.gov.ideam.sirh.usuarios.agua.model;

import co.gov.ideam.sirh.parametros.model.ActividadCIIU;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.parametros.model.Lista;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.SequenceGenerator;
import org.hibernate.id.TableGenerator;

@Entity
@NamedQueries({ 
  @NamedQuery(name = "UsurioAguaTO.findAllUsers", query = "select o from UsuarioAguaTO o"),
  @NamedQuery(name = "UsurioAguaTO.findAll", query = "select o from UsuarioAguaTO o where o.codigoAutoridad = :codigoAutoridad"),
  @NamedQuery(name = "UsurioAguaTO.find", query = "select o from UsuarioAguaTO o where o.codigo = :codigo"),
  @NamedQuery(name = "UsurioAguaTO.findByDocumento", query = "select o from UsuarioAguaTO o where o.codigoTipoDocumento = :codigoTipoDocumento and o.numeroDocumento = :numeroDocumento and o.codigoAutoridad = :codigoAutoridad")
})
@Table(name = "rurv_usuarios")
public class UsuarioAguaTO implements Serializable{      
    
    public static final int CORPORACION = 1;
    public static final int MUNICIPIO = 2;
    public static final int JURIDICA_PRIVADA = 3;
    public static final int JURIDICA_PUBLICA = 4;
    public static final int NATURAL = 5;
    public static final int ACUEDUCTO = 136;
    public static final int MEGAPROYECTO = 956;
  @Id
   @Column(name="id", nullable = false)    
    private Long codigo;    
    @Column(name="nombre")
    private String autoridad;    
    @Column(name="usuario")
    private String nombre;
    @Column(name="apellido")
    private String apellido;
    @Column(name="tipo_documento")
    private String tipoDocumento;
    @Column(name="id_tipo_documento")
    private Integer codigoTipoDocumento;
    @Column(name="num_documento")
    private String numeroDocumento;
    @Column(name="municipio")
    private String municipio;
    @Column(name="id_divipola_municipio")
    private Integer id_divipola_municipio;
    
    @Column(name="departamento")
    private String departamento;  
    @Column(name="id_divipola_depto")
    private Integer id_divipola_depto;
    @Column(name="id_autoridad")
    private Long codigoAutoridad;
    @Column(name="direccion")
    private String direccion;
    @Column(name="telefono")
    private String telefono;
    @Column(name="email")
    private String email;
    @Column(name="codigo_tipo_persona")
    private Integer id_tipo_persona;
   
    
    @Column(name="ciu_descripcion")
    private String codigoActividadCiiu;
    
    @Transient
    private String erroresValidacion;
    @Transient
    private Boolean validado = null;
    
    @Transient
    private Integer num;
    
    
    public UsuarioAguaTO() {
        super();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido != null ? apellido : "";
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }


    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

   

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }


    public static int getCORPORACION() {
        return CORPORACION;
    }

    public static int getMUNICIPIO() {
        return MUNICIPIO;
    }

    public static int getJURIDICA_PRIVADA() {
        return JURIDICA_PRIVADA;
    }

    public static int getJURIDICA_PUBLICA() {
        return JURIDICA_PUBLICA;
    }

    public static int getNATURAL() {
        return NATURAL;
    }

    public static int getACUEDUCTO() {
        return ACUEDUCTO;
    }
    
    public static int getMEGAPROYECTO() {
        return MEGAPROYECTO;
    }
    public boolean isValid(){
        if(validado==null){
            boolean valido = true;        
            if(this.direccion==null || this.direccion.length()==0){
                this.setErroresValidacion("Se requiere  la dirección");            
                valido = false;
            }
            /*if(this.telefono==null || this.telefono.length()==0){
                this.setErroresValidacion("Se requiere el teléfono");
                valido = false;
            }
            if(this.email==null || this.email.length()==0){
                this.setErroresValidacion("Se requiere el email");
                valido = false;
            } */           
            validado = Boolean.valueOf(valido);
        }
        return validado.booleanValue();
    }

    public String getErroresValidacion() {
        return erroresValidacion;
    }

    public void setErroresValidacion(String erroresValidacion) {
        if (this.erroresValidacion!=null){
            this.erroresValidacion += "\n";
        }else{
            this.erroresValidacion = "";
        }
        this.erroresValidacion += erroresValidacion;
    }


    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setValidado(Boolean validado) {
        this.validado = validado;
    }

    public Boolean getValidado() {
        return validado;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setCodigoActividadCiiu(String codigoActividadCiiu) {
        this.codigoActividadCiiu = codigoActividadCiiu;
    }

    public String getCodigoActividadCiiu() {
        return codigoActividadCiiu;
    }

    public void setAutoridad(String autoridad) {
        this.autoridad = autoridad;
    }

    public String getAutoridad() {
        return autoridad;
    }


    public void setId_divipola_municipio(Integer id_divipola_municipio) {
        this.id_divipola_municipio = id_divipola_municipio;
    }

    public Integer getId_divipola_municipio() {
        return id_divipola_municipio;
    }

    public void setId_divipola_depto(Integer id_divipola_depto) {
        this.id_divipola_depto = id_divipola_depto;
    }

    public Integer getId_divipola_depto() {
        return id_divipola_depto;
    }

 

    public void setCodigoTipoDocumento(Integer codigoTipoDocumento) {
        this.codigoTipoDocumento = codigoTipoDocumento;
    }

    public Integer getCodigoTipoDocumento() {
        return codigoTipoDocumento;
    }

    public void setCodigoAutoridad(Long codigoAutoridad) {
        this.codigoAutoridad = codigoAutoridad;
    }

    public Long getCodigoAutoridad() {
        return codigoAutoridad;
    }

    public void setId_tipo_persona(Integer id_tipo_persona) {
        this.id_tipo_persona = id_tipo_persona;
    }

    public Integer getId_tipo_persona() {
        return id_tipo_persona;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getNum() {
        return num;
    }
}
