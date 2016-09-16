package co.gov.ideam.sirh.pueaa.view;

import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.pueaa.PueaaDelegate;
import co.gov.ideam.sirh.pueaa.model.PueatAdmon;
import co.gov.ideam.sirh.pueaa.model.PueatProyectos;
import co.gov.ideam.sirh.pueaa.model.PueatSeguimiento;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.usuarios.agua.model.UsuarioAgua;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanRadio;
import oracle.adf.view.rich.component.rich.input.RichSelectOneRadio;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;

public class AdicionarAdmon extends StandarBean {
   
    private RichForm f1;
    private RichDocument d1;
    private RichPanelGroupLayout panelGroupLayoutGeneral;
    private RichPanelFormLayout panelFormLayoutMetas;
    private RichPanelFormLayout panelFormLayoutMetas2;
    private RichPanelFormLayout panelFormLayoutMetas3;
    private RichPanelFormLayout panelFormLayoutMetas4;
    private RichPanelFormLayout panelFormLayoutMetas5;
    private RichPanelFormLayout pfl1;
    
    private RichCommandButton cbGuardar;
    UsuarioConectadoTO usuarioConectado;
    Autoridades autoridadAmbiental;
    PueaaDelegate pueaD;

    private RichSelectBooleanRadio sbrPr1Si;
    private RichSelectBooleanRadio sbrPr1No;

    private RichSelectBooleanRadio sbrPr2Si;
    private RichSelectBooleanRadio sbrPr2No;

    private RichSelectBooleanRadio sbrPr3Si;
    private RichSelectBooleanRadio sbrPr3No;

    private RichSelectBooleanRadio sbrPr4Si;
    private RichSelectBooleanRadio sbrPr4No;

    private RichSelectBooleanRadio sbrPr5Si;
    private RichSelectBooleanRadio sbrPr5No;

    private RichSelectBooleanRadio sbrPr6Si;
    private RichSelectBooleanRadio sbrPr6No;

    public AdicionarAdmon() {
       usuarioConectado =
               (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
       autoridadAmbiental =
               usuarioConectado.getUsuario().getAutoridadAmbiental();
         
   }


    public void cbGuardar_actionListener(ActionEvent actionEvent) throws IdeamException {
       try {
        pueaD = PueaaDelegate.getInstance();
        
        PueatAdmon pueatAdmon1 = new PueatAdmon();
        pueatAdmon1.setIdUsuario(new Long(autoridadAmbiental.getId()));
        pueatAdmon1.setPregunta("P1");
        pueatAdmon1.setRespuesta(sbrPr1Si.getValue().toString());
        pueaD.persistPueatAdmon(pueatAdmon1);
        
        PueatAdmon pueatAdmon2 = new PueatAdmon();
        pueatAdmon2.setIdUsuario(new Long(autoridadAmbiental.getId()));
        pueatAdmon2.setPregunta("P2");
        pueatAdmon2.setRespuesta(sbrPr2Si.getValue().toString());
        pueaD.persistPueatAdmon(pueatAdmon2);    
        
        PueatAdmon pueatAdmon3 = new PueatAdmon();
        pueatAdmon3.setIdUsuario(new Long(autoridadAmbiental.getId()));
        pueatAdmon3.setPregunta("P3");
        pueatAdmon3.setRespuesta(sbrPr3Si.getValue().toString());
        pueaD.persistPueatAdmon(pueatAdmon3);    
        
        PueatAdmon pueatAdmon4 = new PueatAdmon();
        pueatAdmon4.setIdUsuario(new Long(autoridadAmbiental.getId()));
        pueatAdmon4.setPregunta("P4");
        pueatAdmon4.setRespuesta(sbrPr4Si.getValue().toString());
        pueaD.persistPueatAdmon(pueatAdmon4);    
        
        PueatAdmon pueatAdmon5 = new PueatAdmon();
        pueatAdmon5.setIdUsuario(new Long(autoridadAmbiental.getId()));
        pueatAdmon5.setPregunta("P5");
        pueatAdmon5.setRespuesta(sbrPr5Si.getValue().toString());
        pueaD.persistPueatAdmon(pueatAdmon5);    
        
        PueatAdmon pueatAdmon6 = new PueatAdmon();
        pueatAdmon6.setIdUsuario(new Long(autoridadAmbiental.getId()));
        pueatAdmon6.setPregunta("P6");
        pueatAdmon6.setRespuesta(sbrPr6Si.getValue().toString());
        pueaD.persistPueatAdmon(pueatAdmon6);    
        
        String params[] = { "de la encuesta" };
        showMessage(getText("mensaje_registro_exitoso", params),
                        FacesMessage.SEVERITY_INFO);
           
        } catch (IdeamException e) {
        
            showMessage(e);
        }
    }


    public void setF1(RichForm f1) {
        this.f1 = f1;
    }

    public RichForm getF1() {
        return f1;
    }

    public void setD1(RichDocument d1) {
        this.d1 = d1;
    }

    public RichDocument getD1() {
        return d1;
    }

    public void setPanelGroupLayoutGeneral(RichPanelGroupLayout panelGroupLayoutGeneral) {
        this.panelGroupLayoutGeneral = panelGroupLayoutGeneral;
    }

    public RichPanelGroupLayout getPanelGroupLayoutGeneral() {
        return panelGroupLayoutGeneral;
    }

    public void setPanelFormLayoutMetas(RichPanelFormLayout panelFormLayoutMetas) {
        this.panelFormLayoutMetas = panelFormLayoutMetas;
    }

    public RichPanelFormLayout getPanelFormLayoutMetas() {
        return panelFormLayoutMetas;
    }

    public void setPanelFormLayoutMetas2(RichPanelFormLayout panelFormLayoutMetas2) {
        this.panelFormLayoutMetas2 = panelFormLayoutMetas2;
    }

    public RichPanelFormLayout getPanelFormLayoutMetas2() {
        return panelFormLayoutMetas2;
    }

    public void setPanelFormLayoutMetas3(RichPanelFormLayout panelFormLayoutMetas3) {
        this.panelFormLayoutMetas3 = panelFormLayoutMetas3;
    }

    public RichPanelFormLayout getPanelFormLayoutMetas3() {
        return panelFormLayoutMetas3;
    }

    public void setPanelFormLayoutMetas4(RichPanelFormLayout panelFormLayoutMetas4) {
        this.panelFormLayoutMetas4 = panelFormLayoutMetas4;
    }

    public RichPanelFormLayout getPanelFormLayoutMetas4() {
        return panelFormLayoutMetas4;
    }

    public void setPanelFormLayoutMetas5(RichPanelFormLayout panelFormLayoutMetas5) {
        this.panelFormLayoutMetas5 = panelFormLayoutMetas5;
    }

    public RichPanelFormLayout getPanelFormLayoutMetas5() {
        return panelFormLayoutMetas5;
    }

    public void setPfl1(RichPanelFormLayout pfl1) {
        this.pfl1 = pfl1;
    }

    public RichPanelFormLayout getPfl1() {
        return pfl1;
    }

    public void setUsuarioConectado(UsuarioConectadoTO usuarioConectado) {
        this.usuarioConectado = usuarioConectado;
    }

    public UsuarioConectadoTO getUsuarioConectado() {
        return usuarioConectado;
    }

    public void setAutoridadAmbiental(Autoridades autoridadAmbiental) {
        this.autoridadAmbiental = autoridadAmbiental;
    }

    public Autoridades getAutoridadAmbiental() {
        return autoridadAmbiental;
    }

    public void setPueaD(PueaaDelegate pueaD) {
        this.pueaD = pueaD;
    }

    public PueaaDelegate getPueaD() {
        return pueaD;
    }

    public void setCbGuardar(RichCommandButton cbGuardar) {
        this.cbGuardar = cbGuardar;
    }

    public RichCommandButton getCbGuardar() {
        return cbGuardar;
    }

    public void setSbrPr1Si(RichSelectBooleanRadio sbrPr1Si) {
        this.sbrPr1Si = sbrPr1Si;
    }

    public RichSelectBooleanRadio getSbrPr1Si() {
        return sbrPr1Si;
    }

    public void setSbrPr1No(RichSelectBooleanRadio sbrPr1No) {
        this.sbrPr1No = sbrPr1No;
    }

    public RichSelectBooleanRadio getSbrPr1No() {
        return sbrPr1No;
    }

    public void setSbrPr2Si(RichSelectBooleanRadio sbrPr2Si) {
        this.sbrPr2Si = sbrPr2Si;
    }

    public RichSelectBooleanRadio getSbrPr2Si() {
        return sbrPr2Si;
    }

    public void setSbrPr2No(RichSelectBooleanRadio sbrPr2No) {
        this.sbrPr2No = sbrPr2No;
    }

    public RichSelectBooleanRadio getSbrPr2No() {
        return sbrPr2No;
    }

    public void setSbrPr3Si(RichSelectBooleanRadio sbrPr3Si) {
        this.sbrPr3Si = sbrPr3Si;
    }

    public RichSelectBooleanRadio getSbrPr3Si() {
        return sbrPr3Si;
    }

    public void setSbrPr3No(RichSelectBooleanRadio sbrPr3No) {
        this.sbrPr3No = sbrPr3No;
    }

    public RichSelectBooleanRadio getSbrPr3No() {
        return sbrPr3No;
    }

    public void setSbrPr4Si(RichSelectBooleanRadio sbrPr4Si) {
        this.sbrPr4Si = sbrPr4Si;
    }

    public RichSelectBooleanRadio getSbrPr4Si() {
        return sbrPr4Si;
    }

    public void setSbrPr4No(RichSelectBooleanRadio sbrPr4No) {
        this.sbrPr4No = sbrPr4No;
    }

    public RichSelectBooleanRadio getSbrPr4No() {
        return sbrPr4No;
    }

    public void setSbrPr5Si(RichSelectBooleanRadio sbrPr5Si) {
        this.sbrPr5Si = sbrPr5Si;
    }

    public RichSelectBooleanRadio getSbrPr5Si() {
        return sbrPr5Si;
    }

    public void setSbrPr5No(RichSelectBooleanRadio sbrPr5No) {
        this.sbrPr5No = sbrPr5No;
    }

    public RichSelectBooleanRadio getSbrPr5No() {
        return sbrPr5No;
    }

    public void setSbrPr6Si(RichSelectBooleanRadio sbrPr6Si) {
        this.sbrPr6Si = sbrPr6Si;
    }

    public RichSelectBooleanRadio getSbrPr6Si() {
        return sbrPr6Si;
    }

    public void setSbrPr6No(RichSelectBooleanRadio sbrPr6No) {
        this.sbrPr6No = sbrPr6No;
    }

    public RichSelectBooleanRadio getSbrPr6No() {
        return sbrPr6No;
    }
}
