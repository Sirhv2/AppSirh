package co.gov.ideam.sirh.seguridad.model;


import co.gov.ideam.sirh.parametros.model.Autoridades;

import co.gov.ideam.sirh.parametros.model.Lista;

import java.io.Serializable;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


@Entity(name = "SIRH_USUARIOS")

/**
 * Representa un usuario registrado en el sistema
 */
public class UsuarioVO implements Serializable{    
    @TableGenerator(
            name="GENERAL_TABLE_GENERATOR", 
            table = "GENERATOR_TABLE", 
            pkColumnName = "SEQ_NAME",
            valueColumnName="SEQ_COUNT",
            pkColumnValue = "SEQ_USUARIO", 
            allocationSize = 1            
        )
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "GENERAL_TABLE_GENERATOR")
    @Column(name="usu_codigo")
    private long codigo;
    @Column(name="usu_login")
    private String login;
    @Column(name="usu_nombres")
    private String nombres;
    @Column(name="usu_apellidos")
    private String apellidos;
    @Column(name="usu_email")
    private String email;
    @Column(name="USU_TIPODOCUMENTO")
    private Integer codtipoDocumento;
    @Column(name="USU_NRODOCUMENTO")
    private String nroDocumento;
    @Column(name="usu_estado")
    private Integer activo;
    @Transient
    private boolean activoAux;
    @Column(name="usu_fecha_ingreso_exitoso")
    private Calendar ultimoIngresoExitoso;
    @Column(name="usu_fecha_ingreso_no_exitoso")
    private Calendar ultimoIngresoNoExitoso;    
    @Column(name="USU_PASSWORD")
    private String password;
    @Column(name="USU_REQUIERE_CAMBIO_CLAVE")
    private Integer requiereCambioClave;
    @Transient
    private List perfilesAsociados;    
    @Column(name="usu_autoridad")
    private Integer codigoAutoridadAmbiental;
    @Transient
    private Autoridades autoridadAmbiental;

    // Cambio Hugo Cendales 20160908
    @Column(name="USU_FECHA_CREACION")
    private Calendar fechaCreacion;
    @Column(name="USU_FECHA_MODIFICACION")
    private Calendar fechaModificacion;
    @Column(name="USU_USUARIO_MODIFICACION")
    private long usuarioModificacion;
    @Column(name="USU_USUARIO_CREACION")
    private long usuarioCreacion;
    @Column(name="USU_ESTADO_REGISTRO")
    private Integer estadoRegistro;

    @Transient
    private Lista listaTipoDoc;

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer isActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public Calendar getUltimoIngresoExitoso() {
        return ultimoIngresoExitoso;
    }

    public void setUltimoIngresoExitoso(Calendar ultimoIngresoExitoso) {
        this.ultimoIngresoExitoso = ultimoIngresoExitoso;
    }

    public Calendar getUltimoIngresoNoExitoso() {
        return ultimoIngresoNoExitoso;
    }

    public void setUltimoIngresoNoExitoso(Calendar ultimoIngresoNoExitoso) {
        this.ultimoIngresoNoExitoso = ultimoIngresoNoExitoso;
    }

    public List getPerfilesAsociados() {
        return perfilesAsociados;
    }

    public void setPerfilesAsociados(List perfilesAsociados) {
        this.perfilesAsociados = perfilesAsociados;
    }
    /**
     * Adiciona un nuevo perfil a la lista de perfiles del usuario
     * @param perfil
     */
    public void addPerfil(PerfilVO perfil){
        if (this.perfilesAsociados==null){
            this.perfilesAsociados = new ArrayList();
        }
        this.perfilesAsociados.add(perfil);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer isRequiereCambioClave() {
        return requiereCambioClave;
    }

    public void setRequiereCambioClave(Integer requiereCambioClave) {
        this.requiereCambioClave = requiereCambioClave;
        this.fechaModificacion = Calendar.getInstance();
    }

    public Integer getCodigoAutoridadAmbiental() {
        if (this.autoridadAmbiental!=null){
            return this.autoridadAmbiental.getId().intValue();
        }else{
            return codigoAutoridadAmbiental;
        }
    }

    public void setCodigoAutoridadAmbiental(Integer codigoAutoridadAmbiental) {
        this.codigoAutoridadAmbiental = codigoAutoridadAmbiental;
        this.fechaModificacion = Calendar.getInstance();
    }

    public Autoridades getAutoridadAmbiental() {
        return autoridadAmbiental;
    }

    public void setAutoridadAmbiental(Autoridades autoridadAmbiental) {
        this.autoridadAmbiental = autoridadAmbiental;
        if(this.autoridadAmbiental!=null){
            this.codigoAutoridadAmbiental = this.autoridadAmbiental.getId().intValue();
        }else{
            this.codigoAutoridadAmbiental = null;
        }
    }

    public void setActivoAux(boolean activoAux) {
        this.activoAux = activoAux;
    }

    public boolean isActivoAux() {
        if(activo==1)
            activoAux=true;
        return activoAux;
    }


    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public String getNroDocumento() {
        return nroDocumento;
    }

    public Integer getCodigoAutoridadAmbiental1() {
        return codigoAutoridadAmbiental;
    }



    public Lista getListaTipoDoc() {
        return listaTipoDoc;
    }
    
    public void setListaTipoDoc(Lista listaTipoDoc) {
        
        this.listaTipoDoc = listaTipoDoc;
        if(this.listaTipoDoc!=null){
            this.codtipoDocumento = this.listaTipoDoc.getCodigo().intValue();
        }else{
            this.codtipoDocumento = null;
        }
    }

    public void setCodtipoDocumento(Integer codtipoDocumento) {
        this.codtipoDocumento = codtipoDocumento;
    }

    public Integer getCodtipoDocumento() {
        return codtipoDocumento;
    }

    public void setFechaCreacion(Calendar fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Calendar getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaModificacion(Calendar fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Calendar getFechaModificacion() {
        return fechaModificacion;
    }

    public void setUsuarioModificacion(Integer usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
    }

    public long getUsuarioModificacion() {
        return usuarioModificacion;
    }

    public void setUsuarioCreacion(Integer usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public long getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setEstadoRegistro(Integer estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    public Integer getEstadoRegistro() {
        return estadoRegistro;
    }
}
