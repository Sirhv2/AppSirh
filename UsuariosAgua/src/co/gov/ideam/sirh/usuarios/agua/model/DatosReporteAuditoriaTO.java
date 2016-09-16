package co.gov.ideam.sirh.usuarios.agua.model;

import java.io.Serializable;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DatosReporteAuditoriaTO implements Serializable{
   
   
    @Id
    @Column(name="id")     
    private Long codigoUsuario;
    @Column(name="nombre")    
    private String nombre;
    @Column(name="apellido")    
    private String apellido;
    @Column(name="operacion")    
    private String operacion;
    @Column(name="usu_codigo")  
    private String usu_codigo;  
    @Column(name="usu_login")      
    private String usu_login;   
    @Column(name="USU_EMAIL")      
    private String usu_email;  
    @Column(name="id_aut")      
    private String id_aut;  
    
    @Column(name="sigla")      
    private String sigla;       
    @Column(name="fecha")      
    private Calendar fecha;             
 
   
    
    public DatosReporteAuditoriaTO() {
        super();
    
    
    }


   

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getApellido() {
        return apellido;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setUsu_codigo(String usu_codigo) {
        this.usu_codigo = usu_codigo;
    }

    public String getUsu_codigo() {
        return usu_codigo;
    }

    public void setUsu_login(String usu_login) {
        this.usu_login = usu_login;
    }

    public String getUsu_login() {
        return usu_login;
    }

 

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getSigla() {
        return sigla;
    }

 

    public void setUsu_email(String usu_email) {
        this.usu_email = usu_email;
    }

    public String getUsu_email() {
        return usu_email;
    }

    public void setCodigoUsuario(Long codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public Long getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setId_aut(String id_aut) {
        this.id_aut = id_aut;
    }

    public String getId_aut() {
        return id_aut;
    }


    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    public Calendar getFecha() {
        return fecha;
    }
}
