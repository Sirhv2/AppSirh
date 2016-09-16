package co.gov.ideam.sirh.fuentes.view;

import co.gov.ideam.sirh.fuentes.model.CriteriosBusquedaTO;
import co.gov.ideam.sirh.fuentes.model.FnttFuente;

import co.gov.ideam.sirh.fuentes.model.FnttTramo;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.parametros.model.PartZonificListas;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.util.Constantes;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;

import co.gov.ideam.sirh.view.StandarBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UISelectItems;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichMenu;
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

import org.apache.myfaces.trinidad.event.SelectionEvent;

public class TramosBean extends StandarBean {
    
    private FnttTramo tramoSeleccionado;
    
    private FnttFuente fuente;
    
    private List listaTramos;
    private List listaArea;
    private List listaZona;
    private List listaSubzona;
    private List listaDepartamentos;
    private List listaMunicipios;
    private String accionAdicionar;
    
    private RichForm formF1;
    private RichDocument documentF1;
    private RichPanelStretchLayout pslf1;
    private RichPanelSplitter psf1;
    private RichPanelFormLayout pflf1;
    private RichSelectOneChoice selectOneChoice1;
    private UISelectItems selectItems1;
    private RichSelectOneChoice selectOneChoice2;
    private UISelectItems selectItems2;
    private RichSelectOneChoice selectOneChoice3;
    private UISelectItems selectItems3;
    private RichSelectOneChoice selectOneChoice4;
    private UISelectItems selectItems4;
    private RichSelectOneChoice selectOneChoice5;
    private UISelectItems selectItems5;
    private RichPanelGroupLayout panelGroupLayout1;
    private RichPanelCollection panelCollection1;
    private RichMenu menu1;
    private RichCommandMenuItem commandMenuItem1;
    private RichCommandMenuItem commandMenuItem2;
    private RichCommandButton commandButton1;
    private RichPopup popupFuente;
    private RichTable t_tramos;
    private RichPanelGroupLayout panelGroupLayout2;
    private RichSpacer spacer1;
    private RichCommandLink commandLink1;
    private RichSpacer spacer2;
    private RichCommandLink commandLink2;
    private RichSpacer spacer3;
    private RichOutputText outputText1;
    private RichInputText it_tramo;


    public TramosBean( ){
        FacesUtils.setActiveBean("TramoBean", "Tramos", FuenteDelegate.class);
        FacesUtils.removeManagedBeanFromSession("detalleTramoBean");
        FacesUtils.removeManagedBeanFromSession("adicionarTramoBean");
        this.load();
    }
    
    public void load(){
        try{
            FuenteDelegate fd = FuenteDelegate.getInstance();
            this.listaDepartamentos = this.cargarDivipola(null);
            this.listaArea = this.cargarZonificacion(null);
            this.listaZona = new ArrayList();
            this.listaSubzona = new ArrayList();
                        
            UsuarioConectadoTO usuarioConectado = 
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
            
            Object obj = FacesUtils.getFromSession("fuenteSeleccionada");//la fuente desde la cual se inicia.
            if(obj instanceof FnttFuente){
                this.fuente = (FnttFuente)obj;
            }else{                
                Long codigo = (Long)FacesUtils.getFromSession("fuenteSeleccionada");                                            
                this.fuente = fd.getFuente(codigo);                
            }
            
            //si es usuario del ideam
            /*if (usuarioConectado.getUsuario().getAutoridadAmbiental()==null ||
                usuarioConectado.getUsuario().getAutoridadAmbiental().getId() == null ||
                usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue() ==0 ||
                usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue() == Constantes.IDEAM){
                this.listaTramos = fd.getAllTramosByFuente(this.fuente);
            }else{//es un usuario de una car
                //listaFuentes = fd.getAllFuentes(usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue());
                this.listaTramos = fd.getAllTramosByFuente(this.fuente);
            }*/
            
            //El filtro de la autoridad ya viene desde la fuente
            this.listaTramos = fd.getAllTramosByFuente(this.fuente);
            
        }catch(IdeamException e){
            showMessage(e);
        }
    }

    public void selectOneChoice1_valueChangeListener(ValueChangeEvent event)
            throws IdeamException{
        
        Object area = event.getNewValue();
        this.listaZona = new ArrayList();
        try{
            if(area!=null)
                this.listaZona = this.cargarZonificacion(((PartZonificListas)area).getId());
        }catch(IdeamException e){            
            showMessage(e);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(selectOneChoice2);
    }
    
    public void selectOneChoice2_valueChangeListener(ValueChangeEvent event)
            throws IdeamException{
        
        Object zona = event.getNewValue();
        this.listaSubzona = new ArrayList();
        try{
            if(zona!=null)
                this.listaSubzona = this.cargarZonificacion(((PartZonificListas)zona).getId());
        }catch(IdeamException e){            
            showMessage(e);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(selectOneChoice3);
    }
    
    public void selectOneChoice4_valueChangeListener(ValueChangeEvent event) 
    throws IdeamException{
        Object departamento = event.getNewValue();
        this.listaMunicipios = new ArrayList();
        try{
            if(departamento!=null){
                this.listaMunicipios = this.cargarDivipola(((Divipola)departamento).getId());
            }
        }catch(IdeamException e){            
            showMessage(e);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(selectOneChoice5);
    }
             
    public String commandMenuItem1_action() {
        UsuarioConectadoTO usuarioConectado = 
            (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
        
        Autoridades autoridadAmbiental = usuarioConectado.getUsuario().getAutoridadAmbiental();
        if(autoridadAmbiental==null ||
           autoridadAmbiental.getId().intValue() == Constantes.IDEAM){
            showMessage(getText("NO_EXISTE_AUTORIDAD_AMBIENTAL"),
                        FacesMessage.SEVERITY_ERROR);   
            return "";
        }else {
            return accionAdicionar = "adicionarTramo";
        }
    }

    public void commandMenuItem2_actionListener(ActionEvent actionEvent) {
        accionAdicionar = "";
        if (this.tramoSeleccionado==null){
            showMessage(getText("seleccionar_registro"),
                        FacesMessage.SEVERITY_ERROR);
            return;
        }

        FacesUtils.setInSession("tramoSeleccionado", this.tramoSeleccionado);
        //FacesUtils.removeManagedBeanFromSession("UsuariosTreeHandler");
        //FacesUtils.removeManagedBeanFromSession("PrediosTreeHandler");
        accionAdicionar = "detalleTramo";
    }


    public void commandButton1_actionListener(ActionEvent actionEvent) {
        CriteriosBusquedaTO criterios = new CriteriosBusquedaTO();
        this.listaTramos = new ArrayList();
        if(this.selectOneChoice1.getValue()!=null){
            criterios.setArea(
                (PartZonificListas)this.selectOneChoice1.getValue());
        }
        if(this.selectOneChoice2.getValue()!=null){
            criterios.setZona(
                (PartZonificListas)this.selectOneChoice2.getValue());
        }
        if(this.selectOneChoice3.getValue()!=null){
            criterios.setSubzona(
                (PartZonificListas)this.selectOneChoice3.getValue());
        }
        if(this.selectOneChoice4.getValue()!=null){
            criterios.setDepartamento(
                (Divipola)this.selectOneChoice4.getValue());
        }
        if(this.selectOneChoice5.getValue()!=null){
            criterios.setMunicipio((Divipola)this.selectOneChoice5.getValue());
        }
        if(this.it_tramo.getValue()!=null){
            criterios.setNombreTramo(this.it_tramo.getValue().toString().toUpperCase());
        }
        
        criterios.setFuente(this.fuente);//siempre buscar los tramos de la fuente consultada.
        
        try{
            UsuarioConectadoTO usuarioConectado = 
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
            criterios.setAutoridad(usuarioConectado.getUsuario().getAutoridadAmbiental());
            
            FuenteDelegate fd = FuenteDelegate.getInstance();
            this.listaTramos = fd.getAllTramos(criterios);
            AdfFacesContext.getCurrentInstance().addPartialTarget(t_tramos);
        }catch(IdeamException e){
            showMessage(e);
        }
    }
    
    public void t_fuentes_selectionListener(SelectionEvent selectionEvent) {
        RichTable t_tramos1 = (RichTable)selectionEvent.getSource();
        this.tramoSeleccionado = (FnttTramo)t_tramos1.getSelectedRowData();
    }

    public void setFormF1(RichForm formF1) {
        this.formF1 = formF1;
    }

    public RichForm getFormF1() {
        return formF1;
    }

    public void setDocumentF1(RichDocument documentF1) {
        this.documentF1 = documentF1;
    }

    public RichDocument getDocumentF1() {
        return documentF1;
    }

    public void setPslf1(RichPanelStretchLayout pslf1) {
        this.pslf1 = pslf1;
    }

    public RichPanelStretchLayout getPslf1() {
        return pslf1;
    }

    public void setPsf1(RichPanelSplitter psf1) {
        this.psf1 = psf1;
    }

    public RichPanelSplitter getPsf1() {
        return psf1;
    }

    public void setPflf1(RichPanelFormLayout pflf1) {
        this.pflf1 = pflf1;
    }

    public RichPanelFormLayout getPflf1() {
        return pflf1;
    }

    public void setSelectOneChoice1(RichSelectOneChoice selectOneChoice1) {
        this.selectOneChoice1 = selectOneChoice1;
    }

    public RichSelectOneChoice getSelectOneChoice1() {
        return selectOneChoice1;
    }

    public void setSelectItems1(UISelectItems selectItems1) {
        this.selectItems1 = selectItems1;
    }

    public UISelectItems getSelectItems1() {
        return selectItems1;
    }

    public void setSelectOneChoice2(RichSelectOneChoice selectOneChoice2) {
        this.selectOneChoice2 = selectOneChoice2;
    }

    public RichSelectOneChoice getSelectOneChoice2() {
        return selectOneChoice2;
    }

    public void setSelectItems2(UISelectItems selectItems2) {
        this.selectItems2 = selectItems2;
    }

    public UISelectItems getSelectItems2() {
        return selectItems2;
    }

    public void setSelectOneChoice3(RichSelectOneChoice selectOneChoice3) {
        this.selectOneChoice3 = selectOneChoice3;
    }

    public RichSelectOneChoice getSelectOneChoice3() {
        return selectOneChoice3;
    }

    public void setSelectItems3(UISelectItems selectItems3) {
        this.selectItems3 = selectItems3;
    }

    public UISelectItems getSelectItems3() {
        return selectItems3;
    }

    public void setSelectOneChoice4(RichSelectOneChoice selectOneChoice4) {
        this.selectOneChoice4 = selectOneChoice4;
    }

    public RichSelectOneChoice getSelectOneChoice4() {
        return selectOneChoice4;
    }

    public void setSelectItems4(UISelectItems selectItems4) {
        this.selectItems4 = selectItems4;
    }

    public UISelectItems getSelectItems4() {
        return selectItems4;
    }

    public void setSelectOneChoice5(RichSelectOneChoice selectOneChoice5) {
        this.selectOneChoice5 = selectOneChoice5;
    }

    public RichSelectOneChoice getSelectOneChoice5() {
        return selectOneChoice5;
    }

    public void setSelectItems5(UISelectItems selectItems5) {
        this.selectItems5 = selectItems5;
    }

    public UISelectItems getSelectItems5() {
        return selectItems5;
    }

    public void setPanelGroupLayout1(RichPanelGroupLayout panelGroupLayout1) {
        this.panelGroupLayout1 = panelGroupLayout1;
    }

    public RichPanelGroupLayout getPanelGroupLayout1() {
        return panelGroupLayout1;
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

    public void setCommandButton1(RichCommandButton commandButton1) {
        this.commandButton1 = commandButton1;
    }

    public RichCommandButton getCommandButton1() {
        return commandButton1;
    }

    public void setPopupFuente(RichPopup popupFuente) {
        this.popupFuente = popupFuente;
    }

    public RichPopup getPopupFuente() {
        return popupFuente;
    }

    public void setT_tramos(RichTable t_tramos) {
        this.t_tramos = t_tramos;
    }

    public RichTable getT_tramos() {
        return t_tramos;
    }

    public void setTramoSeleccionado(FnttTramo tramoSeleccionado) {
        this.tramoSeleccionado = tramoSeleccionado;
    }

    public FnttTramo getTramoSeleccionado() {
        return tramoSeleccionado;
    }

    public void setFuente(FnttFuente fuente) {
        this.fuente = fuente;
    }

    public FnttFuente getFuente() {
        return fuente;
    }

    public void setListaTramos(List listaTramos) {
        this.listaTramos = listaTramos;
    }

    public List getListaTramos() {
        return listaTramos;
    }

    public void setListaArea(List listaArea) {
        this.listaArea = listaArea;
    }

    public List getListaArea() {
        return listaArea;
    }

    public void setListaZona(List listaZona) {
        this.listaZona = listaZona;
    }

    public List getListaZona() {
        return listaZona;
    }

    public void setListaSubzona(List listaSubzona) {
        this.listaSubzona = listaSubzona;
    }

    public List getListaSubzona() {
        return listaSubzona;
    }

    public void setAccionAdicionar(String accionAdicionar) {
        this.accionAdicionar = accionAdicionar;
    }

    public String getAccionAdicionar() {
        return accionAdicionar;
    }

    public void setPanelGroupLayout2(RichPanelGroupLayout panelGroupLayout2) {
        this.panelGroupLayout2 = panelGroupLayout2;
    }

    public RichPanelGroupLayout getPanelGroupLayout2() {
        return panelGroupLayout2;
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


    public void setListaDepartamentos(List listaDepartamentos) {
        this.listaDepartamentos = listaDepartamentos;
    }

    public List getListaDepartamentos() {
        return listaDepartamentos;
    }

    public void setListaMunicipios(List listaMunicipios) {
        this.listaMunicipios = listaMunicipios;
    }

    public List getListaMunicipios() {
        return listaMunicipios;
    }

    public void setIt_tramo(RichInputText inputText1) {
        this.it_tramo = inputText1;
    }

    public RichInputText getIt_tramo() {
        return it_tramo;
    }
}
