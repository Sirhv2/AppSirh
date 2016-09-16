package co.gov.ideam.sirh.usuarios.agua.view;


import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.usuarios.agua.model.CaptacionTO;
import co.gov.ideam.sirh.usuarios.agua.model.Concesion;
import co.gov.ideam.sirh.usuarios.agua.model.CriteriosBusquedaTO;
import co.gov.ideam.sirh.usuarios.agua.model.Predio;
import co.gov.ideam.sirh.usuarios.agua.model.UsuarioAgua;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;
import java.util.ArrayList;
import java.util.List;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichMenu;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.nav.RichCommandMenuItem;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import javax.faces.component.UISelectItems;
import oracle.adf.view.rich.context.AdfFacesContext;
import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.component.rich.output.RichOutputText;

import org.apache.myfaces.trinidad.event.SelectionEvent;


public class CaptacionesConcesionBean extends StandarBean{
    private CaptacionTO captacionTOSeleccionada;
    private List listaDepartamentos;
    private List listaMunicipios;
    private UsuarioAgua usuario;
    private Predio predio;
    private Concesion concesion; 
    private List listaCaptaciones;
    private String detalleCaptacion;
    
    private RichForm form1;
    private RichDocument document1;
    private RichPanelStretchLayout panelStretchLayout1;
    private RichPanelGroupLayout panelGroupLayout1;
    private RichPanelGroupLayout panelGroupLayout2;
    private RichCommandLink clTodos;
    private RichSpacer spacer1;
    private RichPanelCollection panelCollection1;
    private RichMenu menu1;
    private RichCommandMenuItem addCaptacionMenuItem1;
    private RichCommandMenuItem editarCaptacionMenuItem2;  
    private RichTable t_captaciones;
    private RichSpacer spacer2;
    private RichCommandLink clUsuario;
    private RichSpacer spacer3;
    private RichCommandLink clPredio;
    private RichSpacer spacer4;
    private RichCommandLink clConcesiones;
    private RichSpacer spacer5;
    private RichCommandLink clConcesion;
    private RichSpacer spacer6;
    private RichOutputText otCaptaciones;
    private RichPanelSplitter panelSplitter1;
    private RichPanelFormLayout panelFormLayout1;
    private RichSelectOneChoice socFiltroDepartamento;
    private UISelectItems siFiltroDep;
    private RichSelectOneChoice socFiltroMun;
    private UISelectItems siFiltroMun;
    private RichCommandButton cmdBuscar;

    public CaptacionesConcesionBean(){
        FacesUtils.setActiveBean("captacionesConcesionBean", "Captaciones Concesion",
                                 UsuariosAguaDelegate.class);
        
        //FacesUtils.removeManagedBeanFromSession("UsoTreeHandler");
        this.load();
    }
        
    private void load(){        
        try{
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance(); 
            
            Object objUsuario = FacesUtils.getFromSession("usuarioSeleccionado");
            if(objUsuario instanceof UsuarioAgua){            
                this.setUsuario((UsuarioAgua)objUsuario);
            }else{                
                Long codigoUsuario = (Long)FacesUtils.getFromSession("usuarioSeleccionado");
                this.setUsuario(uad.getUsuarioAgua(codigoUsuario));  
                FacesUtils.setInSession("usuarioSeleccionado", usuario);
            }
            
            Object objPredio = FacesUtils.getFromSession("predioSeleccionado");
            if(objPredio instanceof Predio){
                this.setPredio((Predio)FacesUtils.getFromSession("predioSeleccionado"));        
            }else{
                Long codigoPredio = (Long)FacesUtils.getFromSession("predioSeleccionado");
                this.setPredio(uad.getPredio( codigoPredio ));
                FacesUtils.setInSession("predioSeleccionado", predio);
            }                    
            
            Object objConcesion = FacesUtils.getFromSession("concesionSeleccionada");        
            if(objConcesion instanceof Concesion){
                this.concesion = (Concesion)FacesUtils.getFromSession("concesionSeleccionada");        
            }else{
                Long codigoConcesion = (Long)FacesUtils.getFromSession("concesionSeleccionada");        
                this.concesion = uad.getConcesion(codigoConcesion);
                FacesUtils.setInSession("concesionSeleccionada", concesion);
            }
            
            this.listaDepartamentos = this.cargarDivipola(null);
            this.listaMunicipios = new ArrayList();
            
            CriteriosBusquedaTO criterios = new CriteriosBusquedaTO();
            UsuarioConectadoTO usuarioConectado = 
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
            criterios.setAutoridad(usuarioConectado.getUsuario().getAutoridadAmbiental());
            criterios.setConcesion(this.concesion);

            this.listaCaptaciones = uad.getAllCaptacionesBusqueda(criterios);//todas las captaciones
        }catch(IdeamException e){
            showMessage(e);
        }
    }
    
    public void t_captaciones_selectionListener(SelectionEvent event) { 
        RichTable tAux = (RichTable)event.getSource();
        this.captacionTOSeleccionada = (CaptacionTO)tAux.getSelectedRowData();
    }
    
    public void editarCaptacionMenuItem2_actionListener(ActionEvent actionEvent) {
        detalleCaptacion = "";
        if (this.captacionTOSeleccionada==null){
            showMessage(getText("seleccionar_registro"),
                        FacesMessage.SEVERITY_ERROR);
            return;
        }        
        FacesUtils.setInSession("captacionSeleccionada", this.captacionTOSeleccionada.getId());   
        FacesUtils.setInSession("paginaOrigen","listarCaptaciones");
        detalleCaptacion = "detalleCaptacion";
    }    
        
    
    public void socFiltroDepartamento_valueChangeListener(ValueChangeEvent event)
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
        AdfFacesContext.getCurrentInstance().addPartialTarget(socFiltroMun);
    }
    
    //Buscar
    public void cmdBuscar_actionListener(ActionEvent actionEvent) {
        CriteriosBusquedaTO criterios = new CriteriosBusquedaTO();
        this.listaCaptaciones = new ArrayList();
        int i = 0;
        if(this.socFiltroDepartamento.getValue()!=null){
            criterios.setDepartamento((Divipola)this.socFiltroDepartamento.getValue());
            i++;
        }
        if(this.socFiltroMun.getValue()!=null){
            criterios.setMunicipio((Divipola)this.socFiltroMun.getValue());
            i++;
        }
        
        try{
            UsuarioConectadoTO usuarioConectado = 
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
            criterios.setAutoridad(usuarioConectado.getUsuario().getAutoridadAmbiental());
            criterios.setConcesion(this.concesion);
            
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance(); 
            this.listaCaptaciones = uad.getAllCaptacionesBusqueda(criterios);//todas las captaciones
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.t_captaciones);//solo las captaciones de la autoridad
        }catch(IdeamException e){
            showMessage(e);
        }
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

    public void setUsuario(UsuarioAgua usuario) {
        this.usuario = usuario;
    }

    public UsuarioAgua getUsuario() {
        return usuario;
    }

    public void setPredio(Predio predio) {
        this.predio = predio;
    }

    public Predio getPredio() {
        return predio;
    }

    public void setConcesion(Concesion concesion) {
        this.concesion = concesion;
    }

    public Concesion getConcesion() {
        return concesion;
    }

    public void setListaCaptaciones(List listaCaptaciones) {
        this.listaCaptaciones = listaCaptaciones;
    }

    public List getListaCaptaciones() {
        return listaCaptaciones;
    }
    
    public void setDetalleCaptacion(String detalleCaptacion) {
        this.detalleCaptacion = detalleCaptacion;
    }

    public String getDetalleCaptacion() {
        return detalleCaptacion;
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

    public void setPanelGroupLayout2(RichPanelGroupLayout panelGroupLayout2) {
        this.panelGroupLayout2 = panelGroupLayout2;
    }

    public RichPanelGroupLayout getPanelGroupLayout2() {
        return panelGroupLayout2;
    }

    public void setClTodos(RichCommandLink clTodos) {
        this.clTodos = clTodos;
    }

    public RichCommandLink getClTodos() {
        return clTodos;
    }

    public void setSpacer1(RichSpacer spacer1) {
        this.spacer1 = spacer1;
    }

    public RichSpacer getSpacer1() {
        return spacer1;
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

    public void setAddCaptacionMenuItem1(RichCommandMenuItem addCaptacionMenuItem1) {
        this.addCaptacionMenuItem1 = addCaptacionMenuItem1;
    }

    public RichCommandMenuItem getAddCaptacionMenuItem1() {
        return addCaptacionMenuItem1;
    }

    public void setEditarCaptacionMenuItem2(RichCommandMenuItem editarCaptacionMenuItem2) {
        this.editarCaptacionMenuItem2 = editarCaptacionMenuItem2;
    }

    public RichCommandMenuItem getEditarCaptacionMenuItem2() {
        return editarCaptacionMenuItem2;
    }

    public void setT_captaciones(RichTable t_captaciones) {
        this.t_captaciones = t_captaciones;
    }

    public RichTable getT_captaciones() {
        return t_captaciones;
    }

    public void setSpacer2(RichSpacer spacer2) {
        this.spacer2 = spacer2;
    }

    public RichSpacer getSpacer2() {
        return spacer2;
    }

    public void setClUsuario(RichCommandLink clUsuario) {
        this.clUsuario = clUsuario;
    }

    public RichCommandLink getClUsuario() {
        return clUsuario;
    }

    public void setSpacer3(RichSpacer spacer3) {
        this.spacer3 = spacer3;
    }

    public RichSpacer getSpacer3() {
        return spacer3;
    }

    public void setClPredio(RichCommandLink clPredio) {
        this.clPredio = clPredio;
    }

    public RichCommandLink getClPredio() {
        return clPredio;
    }

    public void setSpacer4(RichSpacer spacer4) {
        this.spacer4 = spacer4;
    }

    public RichSpacer getSpacer4() {
        return spacer4;
    }

    public void setClConcesiones(RichCommandLink clConcesiones) {
        this.clConcesiones = clConcesiones;
    }

    public RichCommandLink getClConcesiones() {
        return clConcesiones;
    }

    public void setPanelSplitter1(RichPanelSplitter panelSplitter1) {
        this.panelSplitter1 = panelSplitter1;
    }

    public RichPanelSplitter getPanelSplitter1() {
        return panelSplitter1;
    }

    public void setPanelFormLayout1(RichPanelFormLayout panelFormLayout1) {
        this.panelFormLayout1 = panelFormLayout1;
    }

    public RichPanelFormLayout getPanelFormLayout1() {
        return panelFormLayout1;
    }

    public void setSocFiltroDepartamento(RichSelectOneChoice socFiltroDepartamento) {
        this.socFiltroDepartamento = socFiltroDepartamento;
    }

    public RichSelectOneChoice getSocFiltroDepartamento() {
        return socFiltroDepartamento;
    }

    public void setSiFiltroDep(UISelectItems siFiltroDep) {
        this.siFiltroDep = siFiltroDep;
    }

    public UISelectItems getSiFiltroDep() {
        return siFiltroDep;
    }

    public void setSocFiltroMun(RichSelectOneChoice socFiltroMun) {
        this.socFiltroMun = socFiltroMun;
    }

    public RichSelectOneChoice getSocFiltroMun() {
        return socFiltroMun;
    }

    public void setSiFiltroMun(UISelectItems siFiltroMun) {
        this.siFiltroMun = siFiltroMun;
    }

    public UISelectItems getSiFiltroMun() {
        return siFiltroMun;
    }

    public void setCmdBuscar(RichCommandButton cmdBuscar) {
        this.cmdBuscar = cmdBuscar;
    }

    public RichCommandButton getCmdBuscar() {
        return cmdBuscar;
    }

    public void setSpacer5(RichSpacer spacer5) {
        this.spacer5 = spacer5;
    }

    public RichSpacer getSpacer5() {
        return spacer5;
    }

    public void setClConcesion(RichCommandLink clConcesion) {
        this.clConcesion = clConcesion;
    }

    public RichCommandLink getClConcesion() {
        return clConcesion;
    }

    public void setSpacer6(RichSpacer spacer6) {
        this.spacer6 = spacer6;
    }

    public RichSpacer getSpacer6() {
        return spacer6;
    }

    public void setOtCaptaciones(RichOutputText otCaptaciones) {
        this.otCaptaciones = otCaptaciones;
    }

    public RichOutputText getOtCaptaciones() {
        return otCaptaciones;
    }
}
