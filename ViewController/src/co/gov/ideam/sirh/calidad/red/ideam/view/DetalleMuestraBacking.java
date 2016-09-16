package co.gov.ideam.sirh.calidad.red.ideam.view;

import co.gov.ideam.sirh.red.ideam.model.FqMuestras;
import co.gov.ideam.sirh.reportes.view.ReporteDelegate;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.util.SpecialTreeModel;
import co.gov.ideam.sirh.view.util.TreeNode;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;

public class DetalleMuestraBacking extends DetalleMuestra{
    private RichForm form1;
    private RichDocument document1;
    private RichPanelStretchLayout panelStretchLayout1;
    private RichPanelGroupLayout panelGroupLayout1;
    private RichSpacer spacer1;
    private RichCommandLink commandLink1;
    private RichSpacer spacer2;
    private RichCommandLink commandLink2;
    private RichSpacer spacer3;
    private RichCommandLink commandLink3;
    private RichSpacer spacer4;
    private RichOutputText outputText1;
    private RichPanelGroupLayout panelGroupLayout2;
    private RichPanelBox panelBox1;
    private RichSpacer spacer5;
    private RichPanelBox panelBox2;
    private RichPanelGroupLayout panelGroupLayout3;
    private RichPanelFormLayout panelFormLayout1;
    private RichInputText inputText1;
    private RichInputText inputText2;
    private RichInputText inputText3;
    private RichInputText inputText4;
    private RichPanelGroupLayout panelGroupLayout4;
    private RichPanelCollection panelCollection1;
    private RichTable table1;
    private RichSpacer spacer6;
    private RichCommandLink commandLink4;
    private RichSpacer spacer7;
    private RichCommandLink commandLink5;
    private RichInputText psod;
    private RichInputText ica;
    private RichOutputText mensajeNoIca;
    private RichSpacer spacer9;


    public DetalleMuestraBacking() {
        FacesUtils.setActiveBean("detalleMuestraBean", "Punto Monitoreo Ideam",
                                 RedMonitoreoDelegate.class);
        this.load();
    }

    public void load() {
        try {
            UsuarioConectadoTO usuarioConectado =
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

            this.getPuntoMonitoreo();
            
            this.getMuestra();
            
            this.getLaboratorio();

            this.cargarAnalisis();

            

        } catch (IdeamException e) {
            showMessage(e);
        }
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

    public void setCommandLink2(RichCommandLink commandLink2) {
        this.commandLink2 = commandLink2;
    }

    public RichCommandLink getCommandLink2() {
        return commandLink2;
    }

    public void setSpacer3(RichSpacer spacer3) {
        this.spacer3 = spacer3;
    }

    public RichSpacer getSpacer3() {
        return spacer3;
    }

    public void setCommandLink3(RichCommandLink commandLink3) {
        this.commandLink3 = commandLink3;
    }

    public RichCommandLink getCommandLink3() {
        return commandLink3;
    }

    public void setSpacer4(RichSpacer spacer4) {
        this.spacer4 = spacer4;
    }

    public RichSpacer getSpacer4() {
        return spacer4;
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

    public void setSpacer5(RichSpacer spacer5) {
        this.spacer5 = spacer5;
    }

    public RichSpacer getSpacer5() {
        return spacer5;
    }

    public void setPanelBox2(RichPanelBox panelBox2) {
        this.panelBox2 = panelBox2;
    }

    public RichPanelBox getPanelBox2() {
        return panelBox2;
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

    public void setInputText1(RichInputText inputText1) {
        this.inputText1 = inputText1;
    }

    public RichInputText getInputText1() {
        return inputText1;
    }

    public void setInputText2(RichInputText inputText2) {
        this.inputText2 = inputText2;
    }

    public RichInputText getInputText2() {
        return inputText2;
    }

    public void setInputText3(RichInputText inputText3) {
        this.inputText3 = inputText3;
    }

    public RichInputText getInputText3() {
        return inputText3;
    }

    public void setInputText4(RichInputText inputText4) {
        this.inputText4 = inputText4;
    }

    public RichInputText getInputText4() {
        return inputText4;
    }

    public void setPanelGroupLayout4(RichPanelGroupLayout panelGroupLayout4) {
        this.panelGroupLayout4 = panelGroupLayout4;
    }

    public RichPanelGroupLayout getPanelGroupLayout4() {
        return panelGroupLayout4;
    }

    public void setPanelCollection1(RichPanelCollection panelCollection1) {
        this.panelCollection1 = panelCollection1;
    }

    public RichPanelCollection getPanelCollection1() {
        return panelCollection1;
    }

    public void setTable1(RichTable table1) {
        this.table1 = table1;
    }

    public RichTable getTable1() {
        return table1;
    }

    public void setSpacer6(RichSpacer spacer6) {
        this.spacer6 = spacer6;
    }

    public RichSpacer getSpacer6() {
        return spacer6;
    }

    public void setCommandLink4(RichCommandLink commandLink4) {
        this.commandLink4 = commandLink4;
    }

    public RichCommandLink getCommandLink4() {
        return commandLink4;
    }

    public void setSpacer7(RichSpacer spacer7) {
        this.spacer7 = spacer7;
    }

    public RichSpacer getSpacer7() {
        return spacer7;
    }

    public void setCommandLink5(RichCommandLink commandLink5) {
        this.commandLink5 = commandLink5;
    }

    public RichCommandLink getCommandLink5() {
        return commandLink5;
    }


    public void setPsod(RichInputText inputText5) {
        this.psod = inputText5;
    }

    public RichInputText getPsod() {
        return psod;
    }

    public void setIca(RichInputText inputText6) {
        this.ica = inputText6;
    }

    public RichInputText getIca() {
        return ica;
    }

    public void setMensajeNoIca(RichOutputText outputText2) {
        this.mensajeNoIca = outputText2;
    }

    public RichOutputText getMensajeNoIca() {
        return mensajeNoIca;
    }

    public void setSpacer9(RichSpacer spacer9) {
        this.spacer9 = spacer9;
    }

    public RichSpacer getSpacer9() {
        return spacer9;
    }
}
