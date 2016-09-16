package co.gov.ideam.sirh.calidad.red.ideam.view;

import co.gov.ideam.sirh.red.ideam.model.FqMuestras;
import co.gov.ideam.sirh.red.ideam.model.SirhvMuestrasFq;
import co.gov.ideam.sirh.red.ideam.model.SirhvPuntoMonitoreoFq;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;

import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichMenu;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.nav.RichCommandMenuItem;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
import oracle.adf.view.rich.component.rich.output.RichSpacer;

import org.apache.myfaces.trinidad.event.SelectionEvent;

public class MuestrasBacking extends Muestras{
    private RichForm form1;
    private RichDocument document1;
    private RichPanelStretchLayout panelStretchLayout1;
    private RichPanelGroupLayout panelGroupLayout1;
    private RichSpacer spacer1;
    private RichCommandLink commandLink1;
    private RichSpacer spacer2;
    private RichCommandLink commandLink2;
    private RichSpacer spacer3;
    private RichOutputText outputText1;
    private RichPanelGroupLayout panelGroupLayout2;
    private RichPanelCollection panelCollection1;
    private RichMenu menu1;
    private RichCommandMenuItem cmiConsultar;
    private RichTable tresult;
    private RichSpacer spacer4;
    private RichCommandLink commandLink3;
    private RichSpacer spacer5;
    private RichCommandLink commandLink4;

    public MuestrasBacking() {
        FacesUtils.setActiveBean("muestrasBean", "Muestras FQ Ideam",
                                 RedMonitoreoDelegate.class);
        this.load();
    }
    
    public void load() {
        try{ 
            
            this.getPuntoMonitoreo();

            this.cargarMuestras();
            
        } catch (IdeamException e) {
            showMessage(e);
        }
    }
    
    public void tresult_selectionListener(SelectionEvent selectionEvent) {
        RichTable tresult1 = (RichTable)selectionEvent.getSource();
        this.muestraSeleccionada = (FqMuestras) tresult1.getSelectedRowData();
        
        FacesUtils.removeFromSession("muestraIdeamSeleccionada");
        FacesUtils.setInSession("muestraIdeamSeleccionada",  this.muestraSeleccionada);
    }
    
    public void cmiConsultar_actionListener(ActionEvent actionEvent) {
        this.accion = "";
        if (this.muestraSeleccionada == null) {
            showMessage(getText("seleccionar_registro"),
                        FacesMessage.SEVERITY_ERROR);
            return;
        }
        this.accion = "detalleMuestraIdeam";
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

    public void setPanelCollection1(RichPanelCollection panelCollection1) {
        this.panelCollection1 = panelCollection1;
    }

    public RichPanelCollection getPanelCollection1() {
        return panelCollection1;
    }

    public void setMenu1(RichMenu menu1) {
        this.menu1 = menu1;
    }

    public RichMenu getMenu1() {
        return menu1;
    }

    public void setCmiConsultar(RichCommandMenuItem commandMenuItem1) {
        this.cmiConsultar = commandMenuItem1;
    }

    public RichCommandMenuItem getCmiConsultar() {
        return cmiConsultar;
    }

    public void setTresult(RichTable table1) {
        this.tresult = table1;
    }

    public RichTable getTresult() {
        return tresult;
    }

    public void setSpacer4(RichSpacer spacer4) {
        this.spacer4 = spacer4;
    }

    public RichSpacer getSpacer4() {
        return spacer4;
    }

    public void setCommandLink3(RichCommandLink commandLink3) {
        this.commandLink3 = commandLink3;
    }

    public RichCommandLink getCommandLink3() {
        return commandLink3;
    }

    public void setSpacer5(RichSpacer spacer5) {
        this.spacer5 = spacer5;
    }

    public RichSpacer getSpacer5() {
        return spacer5;
    }

    public void setCommandLink4(RichCommandLink commandLink4) {
        this.commandLink4 = commandLink4;
    }

    public RichCommandLink getCommandLink4() {
        return commandLink4;
    }
}
