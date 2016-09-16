package co.gov.ideam.sirh.demanda.view;

import co.gov.ideam.sirh.datos.referencia.view.DatosReferenciaDelegate;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.util.ConstantesOferta;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;

import javax.faces.application.FacesMessage;
import javax.faces.component.UISelectItems;

import javax.faces.event.ActionEvent;

import oracle.adf.view.faces.bi.component.graph.UIGraph;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichMenu;
import oracle.adf.view.rich.component.rich.RichPanelWindow;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.nav.RichCommandMenuItem;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;

public class ConsultaEstratificadaBacking extends ConsultaEstratificada{
    
    private RichForm form1;
    private RichDocument document1;
    private RichPanelStretchLayout panelStretchLayout1;
    private RichPanelSplitter panelSplitter1;
    private RichPanelGroupLayout panelGroupLayout1;
    private RichSpacer spacer1;
    private RichCommandLink commandLink1;
    private RichSpacer spacer2;
    private RichOutputText outputText1;
    private RichPanelFormLayout panelFormLayout1;
    private RichSelectOneChoice socVariable;
    private UISelectItems selectItems1;
    private RichInputText itAgno;
    private RichSelectOneChoice socClase;
    private UISelectItems selectItems2;
    private RichCommandButton cmdBuscar;
    private RichSpacer spacer3;
    private RichCommandLink commandLink2;
    private RichPanelCollection panelCollection1;
    private RichTable table1;
    private RichMenu menu1;
    private RichCommandMenuItem commandMenuItem1;
    private RichCommandMenuItem commandMenuItem2;
    private RichPopup popGrafica;
    private RichPanelGroupLayout panelGroupLayout2;
    private RichPanelGroupLayout panelGroupLayout3;
    private RichPanelWindow panelWindow1;
    private RichPanelGroupLayout panelGroupLayout4;
    private UIGraph barGraph1;
    private RichPanelGroupLayout panelGroupLayout5;
    private RichPanelGroupLayout panelGroupLayout6;

    public ConsultaEstratificadaBacking() {
        FacesUtils.setActiveBean("consultaEstratificadaBean", "Consulta Estratificada",
                                 DatosReferenciaDelegate.class);
        this.load();
    }
    
    public void load() {
        try{            
            this.loadParametros();
        } catch (IdeamException e) {
            showMessage(e);
        }
    }
    
    
    public void cmdBuscar_actionListener(ActionEvent actionEvent) {
        try{
            if(this.socVariable.getValue() == null){
                showMessage("Debe seleccionar una variable",
                            FacesMessage.SEVERITY_ERROR);
                return;
            }
            
            if(this.itAgno.getValue() == null){
                showMessage("Debe seleccionar un año",
                            FacesMessage.SEVERITY_ERROR);
                return;
            }
            
            String variable = this.socVariable.getValue().toString();
            Integer agno = Integer.parseInt(this.itAgno.getValue().toString());
            String clase = (this.socClase.getValue()!=null)
                                    ?((Lista)this.socClase.getValue()).getCodigo().toString()
                                    :"0";
            
            this.cargarDatos(variable, agno, clase);
            
            this.graficar();
    
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelCollection1);
            
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

    public void setPanelSplitter1(RichPanelSplitter panelSplitter1) {
        this.panelSplitter1 = panelSplitter1;
    }

    public RichPanelSplitter getPanelSplitter1() {
        return panelSplitter1;
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

    public void setPanelFormLayout1(RichPanelFormLayout panelFormLayout1) {
        this.panelFormLayout1 = panelFormLayout1;
    }

    public RichPanelFormLayout getPanelFormLayout1() {
        return panelFormLayout1;
    }

    public void setSocVariable(RichSelectOneChoice selectOneChoice1) {
        this.socVariable = selectOneChoice1;
    }

    public RichSelectOneChoice getSocVariable() {
        return socVariable;
    }

    public void setSelectItems1(UISelectItems selectItems1) {
        this.selectItems1 = selectItems1;
    }

    public UISelectItems getSelectItems1() {
        return selectItems1;
    }

    public void setItAgno(RichInputText inputText1) {
        this.itAgno = inputText1;
    }

    public RichInputText getItAgno() {
        return itAgno;
    }

    public void setSocClase(RichSelectOneChoice selectOneChoice2) {
        this.socClase = selectOneChoice2;
    }

    public RichSelectOneChoice getSocClase() {
        return socClase;
    }

    public void setSelectItems2(UISelectItems selectItems2) {
        this.selectItems2 = selectItems2;
    }

    public UISelectItems getSelectItems2() {
        return selectItems2;
    }

    public void setCmdBuscar(RichCommandButton commandButton1) {
        this.cmdBuscar = commandButton1;
    }

    public RichCommandButton getCmdBuscar() {
        return cmdBuscar;
    }

    public void setSpacer3(RichSpacer spacer3) {
        this.spacer3 = spacer3;
    }

    public RichSpacer getSpacer3() {
        return spacer3;
    }

    public void setCommandLink2(RichCommandLink commandLink2) {
        this.commandLink2 = commandLink2;
    }

    public RichCommandLink getCommandLink2() {
        return commandLink2;
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

    public void setMenu1(RichMenu menu1) {
        this.menu1 = menu1;
    }

    public RichMenu getMenu1() {
        return menu1;
    }

    public void setCommandMenuItem1(RichCommandMenuItem commandMenuItem1) {
        this.commandMenuItem1 = commandMenuItem1;
    }

    public RichCommandMenuItem getCommandMenuItem1() {
        return commandMenuItem1;
    }

    public void setCommandMenuItem2(RichCommandMenuItem commandMenuItem2) {
        this.commandMenuItem2 = commandMenuItem2;
    }

    public RichCommandMenuItem getCommandMenuItem2() {
        return commandMenuItem2;
    }


    public void setPopGrafica(RichPopup popup1) {
        this.popGrafica = popup1;
    }

    public RichPopup getPopGrafica() {
        return popGrafica;
    }

    public void setPanelGroupLayout2(RichPanelGroupLayout panelGroupLayout2) {
        this.panelGroupLayout2 = panelGroupLayout2;
    }

    public RichPanelGroupLayout getPanelGroupLayout2() {
        return panelGroupLayout2;
    }

    public void setPanelGroupLayout3(RichPanelGroupLayout panelGroupLayout3) {
        this.panelGroupLayout3 = panelGroupLayout3;
    }

    public RichPanelGroupLayout getPanelGroupLayout3() {
        return panelGroupLayout3;
    }

    public void setPanelWindow1(RichPanelWindow panelWindow1) {
        this.panelWindow1 = panelWindow1;
    }

    public RichPanelWindow getPanelWindow1() {
        return panelWindow1;
    }

    public void setPanelGroupLayout4(RichPanelGroupLayout panelGroupLayout4) {
        this.panelGroupLayout4 = panelGroupLayout4;
    }

    public RichPanelGroupLayout getPanelGroupLayout4() {
        return panelGroupLayout4;
    }

    public void setBarGraph1(UIGraph barGraph1) {
        this.barGraph1 = barGraph1;
    }

    public UIGraph getBarGraph1() {
        return barGraph1;
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
}
