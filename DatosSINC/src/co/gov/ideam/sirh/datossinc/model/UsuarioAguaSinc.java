package co.gov.ideam.sirh.datossinc.model;

import co.gov.ideam.sirh.parametros.model.ActividadCIIU;
import co.gov.ideam.sirh.parametros.model.Autoridades;
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
  @NamedQuery(name = "UsuarioAguaSinc.findAllUsers", query = "select o from UsuarioAguaSinc o"),
  @NamedQuery(name = "UsuarioAguaSinc.findAll", query = "select o from UsuarioAguaSinc o where o.codigoAutoridad = :codigoAutoridad"),
  @NamedQuery(name = "UsuarioAguaSinc.find", query = "select o from UsuarioAguaSinc o where o.codigo = :codigo"),
  @NamedQuery(name = "UsuarioAguaSinc.findByDocumento", query = "select o from UsuarioAguaSinc o where o.codigoTipoDocumento = :codigoTipoDocumento and o.numeroDocumento = :numeroDocumento and o.codigoAutoridad = :codigoAutoridad")
})
@Table(name = "rurt_usuarios_agua_sinc")
public class UsuarioAguaSinc implements Serializable{      
    
    public static final int CORPORACION = 1;
    public static final int MUNICIPIO = 2;
    public static final int JURIDICA_PRIVADA = 3;
    public static final int JURIDICA_PUBLICA = 4;
    public static final int NATURAL = 5;
    public static final int ACUEDUCTO = 136;
    public static final int MEGAPROYECTO = 956;
    
    
    @Id
    //@GeneratedValue(generator = "usuarios_generator")        
    @Column(name="id", nullable = false)    
    private Long codigo;    
    @Column(name="id_autoridad", nullable = false)
    private Long codigoAutoridad;    
    @Column(name="nombre", nullable = true, length = 200)
    private String nombre;
    @Column(name="apellido", nullable = true, length = 200)
    private String apellido;
    @Column(name="tipo_documento")
    private Integer codigoTipoDocumento;
    @Column(name="num_documento", length = 100)
    private String numeroDocumento;
    @Column(name="id_divipola_municipio")
    private Long codigoMunicipio;
    @Column(name="id_divipola_depto")
    private Long codigoDepartamento;    
    @Column(name="direccion", length = 300)
    private String direccion;
    @Column(name="telefono", length = 100)
    private String telefono;
    @Column(name="email", length = 200)
    private String email;
    @Column(name="es_representante_legal")
    private Integer esRepresentanteLegal;
    @Column(name="razon_social", length = 200)
    private String razonSocial;    
    @Column(name="tipo_persona", length = 200)
    private Integer codigoTipoPersona;
    @Column(name="actividad_ciiu")
    private Long codigoActividadCiiu;
    @Transient
    private Lista tipoDocumento;
    @Transient
    private Divipola municipio;
    @Transient
    private Divipola departamento;
    @Transient
    private Lista tipoUsuario;
    @Transient
    private Autoridades autoridadAmbiental;
    @Transient
    private ActividadCIIU actividadCiiu;

    @Transient
    private String erroresValidacion;
    @Transient
    private Boolean validado = null;
    
    
    //** Lisbeth Integracion datos 
    @Column(name="origen")
    private String origen;    
    @Column(name="id_origen")
    private String id_origen;
    @Column(name="trasmitido")
    private Integer trasmitido;
    
    
    
    public UsuarioAguaSinc() {
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

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public Long getCodigoAutoridad() {
        return codigoAutoridad;
    }

    public void setCodigoAutoridad(Long codigoAutoridad) {
        this.codigoAutoridad = codigoAutoridad;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Lista getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(Lista tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
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

    public Divipola getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Divipola municipio) {
        this.municipio = municipio;
    }

    public Divipola getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Divipola departamento) {
        this.departamento = departamento;
    }

    public Lista getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(Lista tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Autoridades getAutoridadAmbiental() {
        return autoridadAmbiental;
    }

    public void setAutoridadAmbiental(Autoridades autoridadAmbiental) {
        this.autoridadAmbiental = autoridadAmbiental;
    }

    public Integer getCodigoTipoPersona() {
        return codigoTipoPersona;
    }

    public void setCodigoTipoPersona(Integer codigoTipoPersona) {
        this.codigoTipoPersona = codigoTipoPersona;
    }

    public ActividadCIIU getActividadCiiu() {
        return actividadCiiu;
    }

    public void setActividadCiiu(ActividadCIIU actividadCiiu) {
        this.actividadCiiu = actividadCiiu;
    }

    public Long getCodigoActividadCiiu() {
        return codigoActividadCiiu;
    }

    public void setCodigoActividadCiiu(Long codigoActividadCiiu) {
        this.codigoActividadCiiu = codigoActividadCiiu;
    }

    public Integer getEsRepresentanteLegal() {
        return esRepresentanteLegal;
    }

    public void setEsRepresentanteLegal(Integer esRepresentanteLegal) {
        this.esRepresentanteLegal = esRepresentanteLegal;
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
