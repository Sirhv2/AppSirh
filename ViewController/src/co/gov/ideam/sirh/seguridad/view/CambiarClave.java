package co.gov.ideam.sirh.seguridad.view;

import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.seguridad.model.UsuarioVO;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;

import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.output.RichImage;
import oracle.adf.view.rich.component.rich.output.RichSpacer;

public class CambiarClave extends StandarBean{
    private RichForm f2;
    private RichDocument d2;
    private RichPanelGroupLayout pgl1;
    private RichPanelStretchLayout psl1;
    private RichImage i1;
    private RichPanelFormLayout pfl1;
    private RichInputText it_usuario;
    private RichInputText it_clave;
    private RichInputText it_confirma_clave;
    private RichPanelGroupLayout pgl2;
    private RichCommandButton cb_aceptar;
    private RichSpacer s1;
    private RichCommandButton cb_cancelar;
    
    private String nombreUsuario="";
    private String action="";
    private RichInputText it_clave_actual;
    private RichSpacer s2;

    public CambiarClave(){
        FacesUtils.setActiveBean("cambiarClave",
                                 "Cambiar Clave",
                                 SeguridadDelegate.class);
        load();
    }    

    public void load(){
        try{
            SeguridadDelegate sd = SeguridadDelegate.getInstance();
            UsuarioConectadoTO usuarioConectado = 
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
            UsuarioVO usuario = sd.getUsuario(usuarioConectado.getUsuario().getLogin());
            setNombreUsuario(usuario.getNombres() + " " + usuario.getApellidos());
        }catch(IdeamException e){
            showMessage(e);
        }
    }
    public void setF2(RichForm f2) {
        this.f2 = f2;
    }

    public RichForm getF2() {
        return f2;
    }

    public void setD2(RichDocument d2) {
        this.d2 = d2;
    }

    public RichDocument getD2() {
        return d2;
    }

    public void setPgl1(RichPanelGroupLayout pgl1) {
        this.pgl1 = pgl1;
    }

    public RichPanelGroupLayout getPgl1() {
        return pgl1;
    }


    public void setPsl1(RichPanelStretchLayout psl1) {
        this.psl1 = psl1;
    }

    public RichPanelStretchLayout getPsl1() {
        return psl1;
    }

    public void setI1(RichImage i1) {
        this.i1 = i1;
    }

    public RichImage getI1() {
        return i1;
    }

    public void setPfl1(RichPanelFormLayout pfl1) {
        this.pfl1 = pfl1;
    }

    public RichPanelFormLayout getPfl1() {
        return pfl1;
    }

    public void setIt_usuario(RichInputText it1) {
        this.it_usuario = it1;
    }

    public RichInputText getIt_usuario() {
        return it_usuario;
    }

    public void setIt_clave(RichInputText it2) {
        this.it_clave = it2;
    }

    public RichInputText getIt_clave() {
        return it_clave;
    }

    public void setIt_confirma_clave(RichInputText it3) {
        this.it_confirma_clave = it3;
    }

    public RichInputText getIt_confirma_clave() {
        return it_confirma_clave;
    }

    public void setPgl2(RichPanelGroupLayout pgl2) {
        this.pgl2 = pgl2;
    }

    public RichPanelGroupLayout getPgl2() {
        return pgl2;
    }

    public void setCb_aceptar(RichCommandButton cb1) {
        this.cb_aceptar = cb1;
    }

    public RichCommandButton getCb_aceptar() {
        return cb_aceptar;
    }

    public void setS1(RichSpacer s1) {
        this.s1 = s1;
    }

    public RichSpacer getS1() {
        return s1;
    }

    public void setCb_cancelar(RichCommandButton cb2) {
        this.cb_cancelar = cb2;
    }

    public RichCommandButton getCb_cancelar() {
        return cb_cancelar;
    }

    public void cb_aceptar_actionListener(ActionEvent actionEvent) {   
        boolean continuar = true;
        if(it_clave_actual.getValue()==null || it_clave_actual.getValue().toString().length()==0){
            it_clave_actual.setValid(false);
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.it_clave_actual);
            continuar = false;
        }
        if(it_clave.getValue()==null || it_clave.getValue().toString().length()==0){
            it_clave.setValid(false);
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.it_clave);
            continuar = false;
        }
        if(it_confirma_clave.getValue()==null || it_confirma_clave.getValue().toString().length()==0){
            it_confirma_clave.setValid(false);
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.it_confirma_clave);
            continuar = false;
        }
        
        if(!continuar){
            return;
        }
        action = "";
        try{
            SeguridadDelegate sd = SeguridadDelegate.getInstance();
            
            // Validar la clave actual
            if(it_clave_actual.getValue().toString().length()<8){
                it_clave_actual.setValid(false);
                showMessage("CLAVE_MINIMO_8",FacesMessage.SEVERITY_ERROR,this.it_clave_actual);
                return;
            }
            // Validar que las dos claves sean iguales
            if (!it_clave.getValue().toString().equals(it_confirma_clave.getValue().toString())){
                it_clave.setValid(false);
                showMessage("CLAVE_NO_COINCIDE",FacesMessage.SEVERITY_ERROR,this.it_clave);
                return;
            }
            if(it_clave.getValue().toString().length()<8){
                it_clave.setValid(false);
                showMessage("CLAVE_MINIMO_8",FacesMessage.SEVERITY_ERROR,this.it_clave);
                return;
            }        
            UsuarioConectadoTO usuarioConectado = 
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");            
            boolean valido = sd.validateUser(usuarioConectado.getUsuario().getLogin(),
                            it_clave_actual.getValue().toString());
            if(!valido){
                it_clave_actual.setValid(false);
                showMessage("CLAVE_INCORRECTA",FacesMessage.SEVERITY_ERROR,this.it_clave_actual);
                return;
            }
            
            UsuarioVO usuario = sd.getUsuario(usuarioConectado.getUsuario().getLogin());
            sd.savePassword(usuario, it_clave.getValue().toString());                        
            showMessage(getText("CLAVE_MODIFICADA"), FacesMessage.SEVERITY_INFO);            
            action="";
        }catch(IdeamException e){
            showMessage(e);
        }
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setIt_clave_actual(RichInputText it1) {
        this.it_clave_actual = it1;
    }

    public RichInputText getIt_clave_actual() {
        return it_clave_actual;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setS2(RichSpacer s2) {
        this.s2 = s2;
    }

    public RichSpacer getS2() {
        return s2;
    }
}
