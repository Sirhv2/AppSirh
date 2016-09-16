package co.gov.ideam.sirh.directorio.view;

import co.gov.ideam.sirh.directorio.model.ActOrganizaciones;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.util.Constantes;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;

import java.util.List;

import javax.faces.application.FacesMessage;

import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichMenu;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandMenuItem;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;

public class ListaOrganizacionesBean extends StandarBean{
    private RichForm f2;
    private RichDocument d2;
    private RichPanelStretchLayout psl1;
    private RichPanelGroupLayout pgl2;
    private RichOutputText ot1;
    private RichPanelSplitter ps1;
    private RichPanelCollection pc1;
    private RichTable t_organizaciones;
    
    private List<ActOrganizaciones> listaOrganizaciones;
    private String accion;
    
    private RichMenu m_opciones;
    private RichCommandMenuItem cmi_adicionar;
    private RichCommandMenuItem cmi_editar;

    public ListaOrganizacionesBean(){
        FacesUtils.setActiveBean("organizaciones",
                                 "organizaciones",
                                 DirectorioDelegate.class);        
        this.load();
    }
    
    public void load(){
        try{
            
            UsuarioConectadoTO usuarioConectado = 
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

            Autoridades autoridadAmbiental = usuarioConectado.getUsuario().getAutoridadAmbiental();
            
            if(autoridadAmbiental==null ||
               autoridadAmbiental.getId().intValue() == Constantes.IDEAM){
                showMessage(getText("NO_EXISTE_AUTORIDAD_AMBIENTAL"),
                            FacesMessage.SEVERITY_ERROR);       
                return;
            }
                        
            DirectorioDelegate dd = DirectorioDelegate.getInstance();
            this.setListaOrganizaciones(dd.getAllOrganizaciones(autoridadAmbiental));
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

    public void setPsl1(RichPanelStretchLayout psl1) {
        this.psl1 = psl1;
    }

    public RichPanelStretchLayout getPsl1() {
        return psl1;
    }


    public void setPgl2(RichPanelGroupLayout pgl2) {
        this.pgl2 = pgl2;
    }

    public RichPanelGroupLayout getPgl2() {
        return pgl2;
    }

    public void setOt1(RichOutputText ot1) {
        this.ot1 = ot1;
    }

    public RichOutputText getOt1() {
        return ot1;
    }

    public void setPs1(RichPanelSplitter ps1) {
        this.ps1 = ps1;
    }

    public RichPanelSplitter getPs1() {
        return ps1;
    }

    public void setPc1(RichPanelCollection pc1) {
        this.pc1 = pc1;
    }

    public RichPanelCollection getPc1() {
        return pc1;
    }

    public void setT_organizaciones(RichTable t1) {
        this.t_organizaciones = t1;
    }

    public RichTable getT_organizaciones() {
        return t_organizaciones;
    }

    public List<ActOrganizaciones> getListaOrganizaciones() {
        return listaOrganizaciones;
    }

    public void setListaOrganizaciones(List<ActOrganizaciones> listaOrganizaciones) {
        this.listaOrganizaciones = listaOrganizaciones;
    }

    public void setM_opciones(RichMenu m2) {
        this.m_opciones = m2;
    }

    public RichMenu getM_opciones() {
        return m_opciones;
    }


    public void cmi_adicionar_actionListener(ActionEvent actionEvent) {
        UsuarioConectadoTO usuarioConectado = 
            (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

        Autoridades autoridadAmbiental = usuarioConectado.getUsuario().getAutoridadAmbiental();
        
        ActOrganizaciones organizacion = new ActOrganizaciones();
        organizacion.setCodigoAutoridad( autoridadAmbiental.getId().longValue() );        
        
        FacesUtils.setInSession("organizacionSeleccionada", organizacion);
    }

    public void cmi_editar_actionListener(ActionEvent actionEvent) {
        setAccion("");
        if(t_organizaciones.getSelectedRowData()==null){
            showMessage(getText("seleccionar_registro"),
                        FacesMessage.SEVERITY_ERROR);            
            return;
        }
        ActOrganizaciones organizacion = 
            (ActOrganizaciones)t_organizaciones.getSelectedRowData();
        FacesUtils.setInSession("organizacionSeleccionada", organizacion);
        setAccion("detalleOrganizacion");
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public void setCmi_adicionar(RichCommandMenuItem cmi2) {
        this.cmi_adicionar = cmi2;
    }

    public RichCommandMenuItem getCmi_adicionar() {
        return cmi_adicionar;
    }

    public void setCmi_editar(RichCommandMenuItem cmi3) {
        this.cmi_editar = cmi3;
    }

    public RichCommandMenuItem getCmi_editar() {
        return cmi_editar;
    }
}
