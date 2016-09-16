package co.gov.ideam.sirh.visor.view;


import co.gov.ideam.sirh.calidad.red.ideam.view.DetallePunto;
import co.gov.ideam.sirh.calidad.red.ideam.view.RedMonitoreoDelegate;

import co.gov.ideam.sirh.calidad.view.CalidadDelegate;
import co.gov.ideam.sirh.usuarios.agua.view.UsuariosAguaDelegate;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;


import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.data.RichTree;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.layout.RichDecorativeBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.output.RichActiveOutputText;

import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichSpacer;

public class DetallePuntoIDEAMBean extends DetallePunto{
    private RichForm form1;
    private RichDocument document1;
    private RichPanelStretchLayout panelStretchLayout1;
    private RichPanelGroupLayout panelGroupLayout1;
    private RichSpacer spacer1;
    private RichCommandLink commandLink1;
    private RichSpacer spacer2;
    private RichOutputText outputText1;
    private RichPanelSplitter panelSplitter1;
    private RichPanelStretchLayout panelStretchLayout2;
    private RichPanelGroupLayout panelGroupLayout2;
    private RichPanelBox panelBox1;
    private RichPanelStretchLayout panelStretchLayout3;
    private RichPanelBox panelBox2;
    private RichTree t1;
    private RichDecorativeBox decorativeBox1;
    private RichDecorativeBox decorativeBox2;
    private RichPanelGroupLayout panelGroupLayout3;
    private RichSpacer spacer3;
    private RichPanelGroupLayout panelGroupLayout4;
    private RichSpacer spacer4;
    private RichActiveOutputText activeOutputText1;
    private RichActiveOutputText activeOutputText2;
    private RichSpacer spacer5;
    private RichSpacer spacer6;
    private RichPanelFormLayout panelFormLayout1;
    private RichPanelFormLayout panelFormLayout2;
    private RichPanelGroupLayout panelGroupLayout5;
    private RichPanelGroupLayout panelGroupLayout6;
    private RichPanelGroupLayout panelGroupLayout7;
    private RichCommandLink clink;
    private RichInputText outputText2;
    private RichInputText outputText3;
    private RichInputText outputText4;
    private RichInputText outputText5;
    private RichInputText outputText6;
    private RichInputText outputText7;
    private RichInputText outputText8;
    private RichInputText outputText9;
    private RichInputText outputText10;
    private RichInputText outputText11;
    private RichInputText inputText1;
    private RichSpacer spacer7;
    private RichCommandLink commandLink2;
    private RichSpacer spacer8;
    private RichCommandLink commandLink3;

    public DetallePuntoIDEAMBean() {
        FacesUtils.setActiveBean("DetallePuntoIDEAMBean", "Punto Monitoreo Ideam",
                                 RedMonitoreoDelegate.class);
        
      
        this.load();
    }

    public void load() {
        try {
        
            java.util.Map params = null;
            params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            String codigo = (String)FacesUtils.getFromSession("puntoIDEAMVisor");
            if (codigo!=null&&params != null && params.get("puntoIdeam") != null) {
              codigo=params.get("puntoIdeam").toString();
              System.out.println("codigo de punto recibido en detallePuntoIDEAM:"+codigo);
            }
            
            
            Long cod=  Long.parseLong(codigo);
            System.out.println(" solicitada desde Visor:"+cod);
            FacesUtils.setInSession("puntoIdeam", cod);
            this.getPuntoMonitoreo();
             
           

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

    public void setOutputText1(RichOutputText outputText1) {
        this.outputText1 = outputText1;
    }

    public RichOutputText getOutputText1() {
        return outputText1;
    }

    public void setPanelSplitter1(RichPanelSplitter panelSplitter1) {
        this.panelSplitter1 = panelSplitter1;
    }

    public RichPanelSplitter getPanelSplitter1() {
        return panelSplitter1;
    }

    public void setPanelStretchLayout2(RichPanelStretchLayout panelStretchLayout2) {
        this.panelStretchLayout2 = panelStretchLayout2;
    }

    public RichPanelStretchLayout getPanelStretchLayout2() {
        return panelStretchLayout2;
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


    public void setPanelStretchLayout3(RichPanelStretchLayout panelStretchLayout3) {
        this.panelStretchLayout3 = panelStretchLayout3;
    }

    public RichPanelStretchLayout getPanelStretchLayout3() {
        return panelStretchLayout3;
    }

    public void setPanelBox2(RichPanelBox panelBox2) {
        this.panelBox2 = panelBox2;
    }

    public RichPanelBox getPanelBox2() {
        return panelBox2;
    }


    public void setT1(RichTree tree1) {
        this.t1 = tree1;
    }

    public RichTree getT1() {
        return t1;
    }

    public void setDecorativeBox1(RichDecorativeBox decorativeBox1) {
        this.decorativeBox1 = decorativeBox1;
    }

    public RichDecorativeBox getDecorativeBox1() {
        return decorativeBox1;
    }

    public void setDecorativeBox2(RichDecorativeBox decorativeBox2) {
        this.decorativeBox2 = decorativeBox2;
    }

    public RichDecorativeBox getDecorativeBox2() {
        return decorativeBox2;
    }

    public void setPanelGroupLayout3(RichPanelGroupLayout panelGroupLayout3) {
        this.panelGroupLayout3 = panelGroupLayout3;
    }

    public RichPanelGroupLayout getPanelGroupLayout3() {
        return panelGroupLayout3;
    }

    public void setSpacer3(RichSpacer spacer3) {
        this.spacer3 = spacer3;
    }

    public RichSpacer getSpacer3() {
        return spacer3;
    }

    public void setPanelGroupLayout4(RichPanelGroupLayout panelGroupLayout4) {
        this.panelGroupLayout4 = panelGroupLayout4;
    }

    public RichPanelGroupLayout getPanelGroupLayout4() {
        return panelGroupLayout4;
    }

    public void setSpacer4(RichSpacer spacer4) {
        this.spacer4 = spacer4;
    }

    public RichSpacer getSpacer4() {
        return spacer4;
    }

    public void setActiveOutputText1(RichActiveOutputText activeOutputText1) {
        this.activeOutputText1 = activeOutputText1;
    }

    public RichActiveOutputText getActiveOutputText1() {
        return activeOutputText1;
    }

    public void setActiveOutputText2(RichActiveOutputText activeOutputText2) {
        this.activeOutputText2 = activeOutputText2;
    }

    public RichActiveOutputText getActiveOutputText2() {
        return activeOutputText2;
    }

    public void setSpacer5(RichSpacer spacer5) {
        this.spacer5 = spacer5;
    }

    public RichSpacer getSpacer5() {
        return spacer5;
    }

    public void setSpacer6(RichSpacer spacer6) {
        this.spacer6 = spacer6;
    }

    public RichSpacer getSpacer6() {
        return spacer6;
    }

    public void setPanelFormLayout1(RichPanelFormLayout panelFormLayout1) {
        this.panelFormLayout1 = panelFormLayout1;
    }

    public RichPanelFormLayout getPanelFormLayout1() {
        return panelFormLayout1;
    }

    public void setPanelFormLayout2(RichPanelFormLayout panelFormLayout2) {
        this.panelFormLayout2 = panelFormLayout2;
    }

    public RichPanelFormLayout getPanelFormLayout2() {
        return panelFormLayout2;
    }

    public void setPanelGroupLayout5(RichPanelGroupLayout panelGroupLayout5) {
        this.panelGroupLayout5 = panelGroupLayout5;
    }

    public RichPanelGroupLayout getPanelGroupLayout5() {
        return panelGroupLayout5;
    }

    public void setPanelGroupLayout6(RichPanelGroupLayout panelGroupLayout6) {
        this.panelGroupLayout6 = panelGroupLayout6;
    }

    public RichPanelGroupLayout getPanelGroupLayout6() {
        return panelGroupLayout6;
    }

    public void setPanelGroupLayout7(RichPanelGroupLayout panelGroupLayout7) {
        this.panelGroupLayout7 = panelGroupLayout7;
    }

    public RichPanelGroupLayout getPanelGroupLayout7() {
        return panelGroupLayout7;
    }

    public void setClink(RichCommandLink clink) {
        this.clink = clink;
    }

    public RichCommandLink getClink() {
        return clink;
    }

    public void setOutputText2(RichInputText outputText2) {
        this.outputText2 = outputText2;
    }

    public RichInputText getOutputText2() {
        return outputText2;
    }

    public void setOutputText3(RichInputText outputText3) {
        this.outputText3 = outputText3;
    }

    public RichInputText getOutputText3() {
        return outputText3;
    }

    public void setOutputText4(RichInputText outputText4) {
        this.outputText4 = outputText4;
    }

    public RichInputText getOutputText4() {
        return outputText4;
    }

    public void setOutputText5(RichInputText outputText5) {
        this.outputText5 = outputText5;
    }

    public RichInputText getOutputText5() {
        return outputText5;
    }

    public void setOutputText6(RichInputText outputText6) {
        this.outputText6 = outputText6;
    }

    public RichInputText getOutputText6() {
        return outputText6;
    }

    public void setOutputText7(RichInputText outputText7) {
        this.outputText7 = outputText7;
    }

    public RichInputText getOutputText7() {
        return outputText7;
    }

    public void setOutputText8(RichInputText outputText8) {
        this.outputText8 = outputText8;
    }

    public RichInputText getOutputText8() {
        return outputText8;
    }

    public void setOutputText9(RichInputText outputText9) {
        this.outputText9 = outputText9;
    }

    public RichInputText getOutputText9() {
        return outputText9;
    }

    public void setOutputText10(RichInputText outputText10) {
        this.outputText10 = outputText10;
    }

    public RichInputText getOutputText10() {
        return outputText10;
    }

    public void setOutputText11(RichInputText outputText11) {
        this.outputText11 = outputText11;
    }

    public RichInputText getOutputText11() {
        return outputText11;
    }

    public void setInputText1(RichInputText inputText1) {
        this.inputText1 = inputText1;
    }

    public RichInputText getInputText1() {
        return inputText1;
    }

    public void setSpacer7(RichSpacer spacer7) {
        this.spacer7 = spacer7;
    }

    public RichSpacer getSpacer7() {
        return spacer7;
    }

    public void setCommandLink2(RichCommandLink commandLink2) {
        this.commandLink2 = commandLink2;
    }

    public RichCommandLink getCommandLink2() {
        return commandLink2;
    }

    public void setSpacer8(RichSpacer spacer8) {
        this.spacer8 = spacer8;
    }

    public RichSpacer getSpacer8() {
        return spacer8;
    }

    public void setCommandLink3(RichCommandLink commandLink3) {
        this.commandLink3 = commandLink3;
    }

    public RichCommandLink getCommandLink3() {
        return commandLink3;
    }
}
