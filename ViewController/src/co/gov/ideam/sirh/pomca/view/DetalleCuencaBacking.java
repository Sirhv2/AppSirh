package co.gov.ideam.sirh.pomca.view;

import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.usuarios.agua.view.AdicionarVertimientoBean;
import co.gov.ideam.sirh.usuarios.agua.view.UsuariosAguaDelegate;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UISelectItems;

import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectManyListbox;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;

public class DetalleCuencaBacking extends DetalleCuenca{
    private RichForm form1;
    private RichDocument document1;
    private RichPanelStretchLayout panelStretchLayout1;
    private RichPanelGroupLayout panelGroupLayout1;
    private RichSpacer spacer1;
    private RichCommandLink commandLink1;
    private RichSpacer spacer2;
    private RichOutputText outputText1;
    private RichPanelGroupLayout panelGroupLayout2;
    private RichPanelBox panelBox1;
    private RichPanelGroupLayout panelGroupLayout3;
    private RichPanelFormLayout panelFormLayout1;
    private RichPanelBox panelBox2;
    private RichPanelGroupLayout panelGroupLayout4;
    private RichPanelFormLayout panelFormLayout2;
    private RichInputText itNombre;
    private RichInputText itCodigo;
    private RichSelectOneChoice socFuentePpal;
    private UISelectItems selectItems1;
    private UISelectItems selectItems2;
    private RichSelectManyListbox smlFuentes;
    private RichSpacer spacer3;
    private RichSpacer spacer4;
    private RichPanelGroupLayout panelGroupLayout5;
    private RichSpacer spacer5;
    private RichCommandButton cmdSave;
    private RichSpacer spacer6;
    private RichSpacer spacer7;


    public DetalleCuencaBacking(){
        FacesUtils.setActiveBean("detalleCuencaBean",  "Detalle Cuenca",
                                 PomcaDelegate.class);
        this.load();
    }
    
    public void load(){
        try{
            this.usuarioConectado();
            this.getCuenca();
            this.cargarDetalleCuenca();
            this.getFuente();
            this.cargarFuentes();
            this.cargarAfluentes();
        } catch (IdeamException e) {
            showMessage(e);
        }
    }
    
    public String cmdSave_action() {
        String accion = "";
        try{
            boolean continuar = true;
            if(this.socFuentePpal.getValue()==null){
                showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.socFuentePpal);
                continuar = false;
            }
            
            if(continuar){
                String codigo = ((this.itCodigo.getValue()!=null)
                                ? this.itCodigo.getValue().toString()
                                : null);
                FnttFuente fuentePrincipal = (FnttFuente)this.socFuentePpal.getValue();
                List afluentes = (List)this.smlFuentes.getValue();
                
                this.save(codigo, fuentePrincipal, afluentes);
                
                accion = "cuencas";
            }
        } catch (IdeamException e) {
            showMessage(e);
        }
        return accion;
    }
    
    public void setForm1(RichForm form1) {
        this.form1 = form1;
    }

    public RichForm getForm1() {
        return form1;
    }

    public void setDocument1(RichDocument document1) {
        this.document1 = document1;
    }

    public RichDocument getDocument1() {
        return document1;
    }

    public void setPanelStretchLayout1(RichPanelStretchLayout panelStretchLayout1) {
        this.panelStretchLayout1 = panelStretchLayout1;
    }

    public RichPanelStretchLayout getPanelStretchLayout1() {
        return panelStretchLayout1;
    }

    public void setPanelGroupLayout1(RichPanelGroupLayout panelGroupLayout1) {
        this.panelGroupLayout1 = panelGroupLayout1;
    }

    public RichPanelGroupLayout getPanelGroupLayout1() {
        return panelGroupLayout1;
    }

    public void setSpacer1(RichSpacer spacer1) {
        this.spacer1 = spacer1;
    }

    public RichSpacer getSpacer1() {
        return spacer1;
    }

    public void setCommandLink1(RichCommandLink commandLink1) {
        this.commandLink1 = commandLink1;
    }

    public RichCommandLink getCommandLink1() {
        return commandLink1;
    }

    public void setSpacer2(RichSpacer spacer2) {
        this.spacer2 = spacer2;
    }

    public RichSpacer getSpacer2() {
        return spacer2;
    }

    public void setOutputText1(RichOutputText outputText1) {
        this.outputText1 = outputText1;
    }

    public RichOutputText getOutputText1() {
        return outputText1;
    }

    public void setPanelGroupLayout2(RichPanelGroupLayout panelGroupLayout2) {
        this.panelGroupLayout2 = panelGroupLayout2;
    }

    public RichPanelGroupLayout getPanelGroupLayout2() {
        return panelGroupLayout2;
    }

    public void setPanelBox1(RichPanelBox panelBox1) {
        this.panelBox1 = panelBox1;
    }

    public RichPanelBox getPanelBox1() {
        return panelBox1;
    }

    public void setPanelGroupLayout3(RichPanelGroupLayout panelGroupLayout3) {
        this.panelGroupLayout3 = panelGroupLayout3;
    }

    public RichPanelGroupLayout getPanelGroupLayout3() {
        return panelGroupLayout3;
    }

    public void setPanelFormLayout1(RichPanelFormLayout panelFormLayout1) {
        this.panelFormLayout1 = panelFormLayout1;
    }

    public RichPanelFormLayout getPanelFormLayout1() {
        return panelFormLayout1;
    }

    public void setPanelBox2(RichPanelBox panelBox2) {
        this.panelBox2 = panelBox2;
    }

    public RichPanelBox getPanelBox2() {
        return panelBox2;
    }

    public void setPanelGroupLayout4(RichPanelGroupLayout panelGroupLayout4) {
        this.panelGroupLayout4 = panelGroupLayout4;
    }

    public RichPanelGroupLayout getPanelGroupLayout4() {
        return panelGroupLayout4;
    }

    public void setPanelFormLayout2(RichPanelFormLayout panelFormLayout2) {
        this.panelFormLayout2 = panelFormLayout2;
    }

    public RichPanelFormLayout getPanelFormLayout2() {
        return panelFormLayout2;
    }

    public void setItNombre(RichInputText inputText1) {
        this.itNombre = inputText1;
    }

    public RichInputText getItNombre() {
        return itNombre;
    }

    public void setItCodigo(RichInputText inputText2) {
        this.itCodigo = inputText2;
    }

    public RichInputText getItCodigo() {
        return itCodigo;
    }

    public void setSocFuentePpal(RichSelectOneChoice selectOneChoice1) {
        this.socFuentePpal = selectOneChoice1;
    }

    public RichSelectOneChoice getSocFuentePpal() {
        return socFuentePpal;
    }

    public void setSelectItems1(UISelectItems selectItems1) {
        this.selectItems1 = selectItems1;
    }

    public UISelectItems getSelectItems1() {
        return selectItems1;
    }


    public void setSelectItems2(UISelectItems selectItems2) {
        this.selectItems2 = selectItems2;
    }

    public UISelectItems getSelectItems2() {
        return selectItems2;
    }

    public void setSmlFuentes(RichSelectManyListbox smcFuentes) {
        this.smlFuentes = smcFuentes;
    }

    public RichSelectManyListbox getSmlFuentes() {
        return smlFuentes;
    }

    public void setSpacer3(RichSpacer spacer3) {
        this.spacer3 = spacer3;
    }

    public RichSpacer getSpacer3() {
        return spacer3;
    }

    public void setSpacer4(RichSpacer spacer4) {
        this.spacer4 = spacer4;
    }

    public RichSpacer getSpacer4() {
        return spacer4;
    }

    public void setPanelGroupLayout5(RichPanelGroupLayout panelGroupLayout5) {
        this.panelGroupLayout5 = panelGroupLayout5;
    }

    public RichPanelGroupLayout getPanelGroupLayout5() {
        return panelGroupLayout5;
    }

    public void setSpacer5(RichSpacer spacer5) {
        this.spacer5 = spacer5;
    }

    public RichSpacer getSpacer5() {
        return spacer5;
    }

    public void setCmdSave(RichCommandButton commandButton1) {
        this.cmdSave = commandButton1;
    }

    public RichCommandButton getCmdSave() {
        return cmdSave;
    }

    public void setSpacer6(RichSpacer spacer6) {
        this.spacer6 = spacer6;
    }

    public RichSpacer getSpacer6() {
        return spacer6;
    }

    public void setSpacer7(RichSpacer spacer7) {
        this.spacer7 = spacer7;
    }

    public RichSpacer getSpacer7() {
        return spacer7;
    }

    
}
