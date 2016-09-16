package co.gov.ideam.sirh.calidad.red.ideam.view;

import co.gov.ideam.sirh.calidad.model.CriteriosBusquedaMuestrasTO;
import co.gov.ideam.sirh.calidad.model.PuntoMonitoreoTO;
import co.gov.ideam.sirh.calidad.view.CalidadDelegate;
import co.gov.ideam.sirh.red.ideam.model.DatosReporteIcaCroosTabIdeam;
import co.gov.ideam.sirh.red.ideam.model.SirhvPuntoMonitoreoFq;
import co.gov.ideam.sirh.reportes.view.ReporteDelegate;
import co.gov.ideam.sirh.util.IdeamException;

import co.gov.ideam.sirh.view.FacesUtils;

import co.gov.ideam.sirh.view.StandarBean;

import java.util.HashMap;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;

import javax.faces.model.SelectItem;

import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichMenu;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.nav.RichCommandMenuItem;
import oracle.adf.view.rich.component.rich.output.RichActiveOutputText;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;

import org.apache.myfaces.trinidad.event.SelectionEvent;

public class RedIdeamBacking extends RedIdeam{
    
    private RichForm form1;
    private RichDocument document1;
    private RichPanelStretchLayout panelStretchLayout1;
    private RichPanelSplitter panelSplitter1;
    private RichPanelCollection panelCollection1;
    private RichTable tresult;
    private RichMenu menu1;
    private RichCommandMenuItem cmiConsultar;
    private RichPanelGroupLayout panelGroupLayout1;
    private RichSpacer spacer1;
    private RichOutputText outputText6;
    private RichSpacer spacer2;
    private RichCommandLink commandLink1;
    private RichSpacer spacer3;
    private RichCommandLink commandLink2;
    private RichCommandMenuItem reporteIca;
    private RichPanelGroupLayout panelGroupLayout3;
    private RichSpacer spacer4;
    private RichCommandButton cb_buscar;
    private List listaParametrosIca;
    private List<DatosReporteIcaCroosTabIdeam> listaDatosIca;
    private RichActiveOutputText titRepIca;
    private RichPanelFormLayout panelFormLayout1;
    private RichPanelGroupLayout panelGroupLayout2;
    private RichPanelGroupLayout panelGroupLayout4;
    private RichSpacer spacer5;
    private RichSpacer spacer6;

    public RedIdeamBacking() {
        FacesUtils.setActiveBean("redIdeamBean", "Red Monitoreo IDEAM",
                                 RedMonitoreoDelegate.class);
        this.load();
    }
    
    public void load() {
        try{          
            this.loadParametros();
        } catch (IdeamException e) {
            showMessage(e);
        }
    }

    public void tresult_selectionListener(SelectionEvent selectionEvent) {
        RichTable tresult1 = (RichTable)selectionEvent.getSource();
        this.puntoSeleccionado = (SirhvPuntoMonitoreoFq) tresult1.getSelectedRowData();
        
        FacesUtils.removeFromSession("puntoIdeam");
        FacesUtils.setInSession("puntoIdeam",  this.puntoSeleccionado);
    }
    
    public void cmiConsultar_actionListener(ActionEvent actionEvent) {
        this.accion = "";
        if (this.puntoSeleccionado == null) {
            showMessage(getText("seleccionar_registro"),
                        FacesMessage.SEVERITY_ERROR);
            return;
        }
        this.accion = "detallePunto";
    }

    public void cb_buscar_actionListener(ActionEvent actionEvent) {
        CriteriosBusquedaMuestrasTO criterios = null;
        Object obj = FacesUtils.getManagedBeanValue("filtrosCalidadIdeam");        
        criterios = ((FiltrosCalidadIdeam) obj).getCriterios(); 
        try{            
            this.cargarDatosCriterios(criterios);            
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelCollection1);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.tresult);
                        
        } catch (IdeamException e) {
            showMessage(e);
        }        
    }
    
    public void reporteIca_actionListener(ActionEvent actionEvent) {
        try{            
            if (this.puntoSeleccionado == null) {
                showMessage(getText("seleccionar_registro"),
                            FacesMessage.SEVERITY_ERROR);
                return;
            }
            listaParametrosIca=cargarAliasParametrosIdeam(); 
            
            this.titRepIca.setVisible(true);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelFormLayout1);        
        
            CalidadDelegate cd = CalidadDelegate.getInstance();
            setListaDatosIca(cd.getDatosCroosTabIdeam(puntoSeleccionado.getId(), listaParametrosIca));

            HashMap parametros = new HashMap();
            parametros.put("p_modulo", "Observatorio del Agua - Componente Calidad");
            parametros.put("p_titulo", "Informe del Indice de Calidad del Agua - ICA 5, 6 y 7 variables del IDEAM");
            this.generateReport("Informe_ICA567_IDEAM.jasper", this.getListaDatosIca(),
                    parametros, ReporteDelegate.EXCEL);
            this.titRepIca.setVisible(false);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelFormLayout1);
            
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



    public void setPanelCollection1(RichPanelCollection panelCollection1) {
        this.panelCollection1 = panelCollection1;
    }

    public RichPanelCollection getPanelCollection1() {
        return panelCollection1;
    }

    public void setTresult(RichTable table1) {
        this.tresult = table1;
    }

    public RichTable getTresult() {
        return tresult;
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

    public void setOutputText6(RichOutputText outputText6) {
        this.outputText6 = outputText6;
    }

    public RichOutputText getOutputText6() {
        return outputText6;
    }


    public void setSpacer2(RichSpacer spacer2) {
        this.spacer2 = spacer2;
    }

    public RichSpacer getSpacer2() {
        return spacer2;
    }

    public void setCommandLink1(RichCommandLink commandLink1) {
        this.commandLink1 = commandLink1;
    }

    public RichCommandLink getCommandLink1() {
        return commandLink1;
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

    public void setReporteIca(RichCommandMenuItem reporteIca) {
        this.reporteIca = reporteIca;
    }

    public RichCommandMenuItem getReporteIca() {
        return reporteIca;
    }


    public void setPanelGroupLayout3(RichPanelGroupLayout panelGroupLayout3) {
        this.panelGroupLayout3 = panelGroupLayout3;
    }

    public RichPanelGroupLayout getPanelGroupLayout3() {
        return panelGroupLayout3;
    }
/*
    public void setItNombre1(RichInputText itNombre1) {
        this.itNombre1 = itNombre1;
    }

    public RichInputText getItNombre1() {
        return itNombre1;
    }

    public void setCmdBuscar1(RichCommandButton cmdBuscar1) {
        this.cmdBuscar1 = cmdBuscar1;
    }

    public RichCommandButton getCmdBuscar1() {
        return cmdBuscar1;
    }
*/
    public void setSpacer4(RichSpacer spacer4) {
        this.spacer4 = spacer4;
    }

    public RichSpacer getSpacer4() {
        return spacer4;
    }


    public void setCb_buscar(RichCommandButton commandButton1) {
        this.cb_buscar = commandButton1;
    }

    public RichCommandButton getCb_buscar() {
        return cb_buscar;
    }

   public List getListaParametrosIca() {
        return listaParametrosIca;
    }

    public void setListaParametrosIca(List listaParametrosIca) {
        this.listaParametrosIca = listaParametrosIca;
    }

    public List getListaDatosIca() {
        return listaDatosIca;
    }

    public void setListaDatosIca(List listaDatosIca) {
        this.listaDatosIca = listaDatosIca;
    }

    public void setTitRepIca(RichActiveOutputText activeOutputText1) {
        this.titRepIca = activeOutputText1;
    }

    public RichActiveOutputText getTitRepIca() {
        return titRepIca;
    }


    public void setPanelFormLayout1(RichPanelFormLayout panelFormLayout1) {
        this.panelFormLayout1 = panelFormLayout1;
    }

    public RichPanelFormLayout getPanelFormLayout1() {
        return panelFormLayout1;
    }


    public void setPanelGroupLayout2(RichPanelGroupLayout panelGroupLayout2) {
        this.panelGroupLayout2 = panelGroupLayout2;
    }

    public RichPanelGroupLayout getPanelGroupLayout2() {
        return panelGroupLayout2;
    }

    public void setPanelGroupLayout4(RichPanelGroupLayout panelGroupLayout4) {
        this.panelGroupLayout4 = panelGroupLayout4;
    }

    public RichPanelGroupLayout getPanelGroupLayout4() {
        return panelGroupLayout4;
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
}
