package co.gov.ideam.sirh.usuarios.agua.view;

import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.model.PartZonificListas;
import co.gov.ideam.sirh.reportes.view.ReporteDelegate;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.usuarios.agua.model.CriteriosBusquedaTO;

import co.gov.ideam.sirh.usuarios.agua.model.PuntoVertimiento;
import co.gov.ideam.sirh.usuarios.agua.model.PuntoVertimientoTO;


import co.gov.ideam.sirh.util.ConstantesParametros;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;

import co.gov.ideam.sirh.view.StandarBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.faces.component.UISelectItems;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

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
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;

public class VertimientosBean extends StandarBean{
    private PuntoVertimiento vertimientoSeleccionado;
    private List listaDepartamentos;
    private List listaMunicipios;
    private List listaVertimientos;
    private String detalleVertimiento;
    private List listaArea;
    private List listaZona;
    private List listaSubzona;
    private List listaFuentes;
    
    private RichForm form1;
    private RichDocument document1;
    private RichPanelStretchLayout panelStretchLayout1;
    private RichPanelGroupLayout panelGroupLayout1;
    private RichPanelGroupLayout panelGroupLayout2;
    private RichPanelCollection panelCollection1;
    private RichMenu menu1;
    private RichCommandMenuItem cmi_imprimir;
    private RichTable t_vertimientos;
    private RichPanelSplitter panelSplitter1;
    private RichPanelFormLayout panelFormLayout1;
    private RichSelectOneChoice socFiltroDepartamento;
    private UISelectItems siFiltroDep;
    private RichSelectOneChoice socFiltroMun;
    private UISelectItems siFiltroMun;
    private RichCommandButton cmdBuscar;
    private RichSpacer spacer5;
    private RichCommandLink commandLink1;
    private RichSelectOneChoice selectOneChoiceArea;
    private UISelectItems selectItems2Area;
    private RichSelectOneChoice selectOneChoiceZona;
    private UISelectItems selectItems3Zona;
    private RichSelectOneChoice selectOneChoiceSubzona;
    private UISelectItems selectItems4Subzona;
    private RichSelectOneChoice selectOneChoice1Fuente;
    private UISelectItems selectItems2;
    private RichSpacer spacer7;
    private RichSpacer spacer6;
    private RichPanelGroupLayout panelGroupLayout3;
    private RichOutputText outputText1;
    private RichSelectOneChoice selectOneChoice1Tipo;
    private UISelectItems selectItems1;
    private List listaTipoVertimiento;
    
    
    
    
    public VertimientosBean(){
        FacesUtils.setActiveBean("vertimientosBean", "Vertimientos general",
                                 UsuariosAguaDelegate.class);
        
        this.load();
    }    
        
    private void load(){        
        try{
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance(); 
            
            
            
            this.listaDepartamentos = this.cargarDivipola(null);
            this.listaMunicipios = new ArrayList();
            this.listaArea = this.cargarZonificacion(null);

            this.listaZona = new ArrayList();
            this.listaSubzona = new ArrayList();
            this.listaFuentes = new ArrayList();

            CriteriosBusquedaTO criterios = new CriteriosBusquedaTO();
            UsuarioConectadoTO usuarioConectado = 
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
            criterios.setAutoridad(usuarioConectado.getUsuario().getAutoridadAmbiental());
            
            this.listaTipoVertimiento = this.cargarParametro(ConstantesParametros.
                                                   CATEGORIA_TIPO_VERTIMIENTO);
            this.listaVertimientos = uad.getAllVertimientosBusqueda(criterios);//todos los puntos de vertimiento desde la vista.
        
            
            //Cabril 04/03/2015
            if (listaVertimientos != null) {
                
                Integer i=1;
                
                Iterator<PuntoVertimientoTO> it = listaVertimientos.iterator();
                while (it.hasNext()) {
                    PuntoVertimientoTO usu = it.next();
                    usu.setNum(i);
                    i++;
                } 
            }
        
        
        
        
        }catch(IdeamException e){
            showMessage(e);
        }
    }
    ////////////////
    
    public void predio_actionListener(ActionEvent actionEvent) {
        Long codigoUsuario = 
            (Long)actionEvent.getComponent().getAttributes().get("codigoUsuario");                
        Long codigoPredio = 
            (Long)actionEvent.getComponent().getAttributes().get("codigoPredio");                
        FacesUtils.setInSession("usuarioSeleccionado", codigoUsuario);
        FacesUtils.setInSession("predioSeleccionado", codigoPredio);
    }    

    public void usuario_actionListener(ActionEvent actionEvent) {
        Long codigoUsuario = 
            (Long)actionEvent.getComponent().getAttributes().get("codigoUsuario");                
        FacesUtils.setInSession("usuarioSeleccionado", codigoUsuario);
    }    
    
    public void permiso_actionListener(ActionEvent actionEvent) {
        Long codigoUsuario = 
            (Long)actionEvent.getComponent().getAttributes().get("codigoUsuario");                
        Long codigoPredio = 
            (Long)actionEvent.getComponent().getAttributes().get("codigoPredio");                
        Long codigo = 
            (Long)actionEvent.getComponent().getAttributes().get("codigo");                        
            
        FacesUtils.setInSession("permisoSeleccionado", codigo);
        FacesUtils.setInSession("usuarioSeleccionado", codigoUsuario);
        FacesUtils.setInSession("predioSeleccionado", codigoPredio);
        FacesUtils.setInSession("paginaOrigen", "vertimientos");
    }
    
    public void vertimiento_actionListener(ActionEvent actionEvent) {
        Long codigoVertimiento = 
            (Long)actionEvent.getComponent().getAttributes().get("codigoVertimiento"); 
        Long codigoUsuario = 
            (Long)actionEvent.getComponent().getAttributes().get("codigoUsuario");                
        Long codigoPredio = 
            (Long)actionEvent.getComponent().getAttributes().get("codigoPredio");                
        Long codigo = 
            (Long)actionEvent.getComponent().getAttributes().get("codigo");  
        
        FacesUtils.setInSession("vertimientoSeleccionado", codigoVertimiento);
        FacesUtils.setInSession("permisoSeleccionado", codigo);
        FacesUtils.setInSession("usuarioSeleccionado", codigoUsuario);
        FacesUtils.setInSession("predioSeleccionado", codigoPredio);
        FacesUtils.setInSession("paginaOrigen", "vertimientos");
    }
    
    public void cmi_imprimir_actionListener(ActionEvent actionEvent) {
        try {
            HashMap parametros = new HashMap();
            parametros.put("p_modulo","Registro de Usuarios del Agua");
            parametros.put("p_titulo","Vertimientos");                        
            this.generateReport("Vertimientos.jasper", this.listaVertimientos,
                                parametros,  ReporteDelegate.EXCEL);
        } catch (IdeamException e) {
            showMessage(e);
        }        
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
    
    
    public void selectOneChoiceArea_valueChangeListener(ValueChangeEvent valueChangeEvent) {
        Object area = valueChangeEvent.getNewValue();
        this.listaZona = new ArrayList();
        try {
            if (area != null) {
                this.listaZona =
                        this.cargarZonificacion(((PartZonificListas)area).getId());
            }
        } catch (IdeamException e) {
            showMessage(e);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(selectOneChoiceZona);
    }

    public void selectOneChoiceZona_valueChangeListener(ValueChangeEvent valueChangeEvent) {
        Object zona = valueChangeEvent.getNewValue();
        this.listaSubzona = new ArrayList();
        try {
            if (zona != null) {
                this.listaSubzona =
                        this.cargarZonificacion(((PartZonificListas)zona).getId());
            }
        } catch (IdeamException e) {
            showMessage(e);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(selectOneChoiceSubzona);
    }

    public void selectOneChoiceSubzona_valueChangeListener(ValueChangeEvent valueChangeEvent) {
        Object subzona = valueChangeEvent.getNewValue();
        this.listaFuentes = new ArrayList();
        try {
            if (subzona != null) {
                co.gov.ideam.sirh.fuentes.model.CriteriosBusquedaTO criterios =
                    new co.gov.ideam.sirh.fuentes.model.CriteriosBusquedaTO();
                UsuarioConectadoTO usuarioConectado =
                    (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

                criterios.setAutoridad(usuarioConectado.getUsuario().getAutoridadAmbiental());
                criterios.setSubzona(((PartZonificListas)subzona));
                this.listaFuentes = this.cargarFuentes(criterios);
            }
        } catch (IdeamException e) {
            showMessage(e);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(selectOneChoice1Fuente);

    }
    
    //Buscar
    public void cmdBuscar_actionListener(ActionEvent actionEvent) {
        CriteriosBusquedaTO criterios = new CriteriosBusquedaTO();
        this.listaVertimientos = new ArrayList();
        int i = 0;
        if(this.socFiltroDepartamento.getValue()!=null){
            criterios.setDepartamento((Divipola)this.socFiltroDepartamento.getValue());
            i++;
        }
        if(this.socFiltroMun.getValue()!=null){
            criterios.setMunicipio((Divipola)this.socFiltroMun.getValue());
            i++;
        }
        if (this.selectOneChoiceArea.getValue() != null) {
            criterios.setArea((PartZonificListas)selectOneChoiceArea.getValue());
            i++;
        } 
        if (this.selectOneChoiceZona.getValue() != null) {
            criterios.setZona((PartZonificListas)selectOneChoiceZona.getValue());
            i++;
        } 
        if (this.selectOneChoiceSubzona.getValue() != null) {
            criterios.setSubzona( (PartZonificListas)selectOneChoiceSubzona.getValue());
            i++;
        } 
        if (this.selectOneChoice1Fuente.getValue() != null) {
            FnttFuente f = (FnttFuente)selectOneChoice1Fuente.getValue();
            criterios.setIdFuente( new Integer(f.getId().intValue()));
            i++;
        } 
        
        
        if (this.selectOneChoice1Tipo.getValue() != null) {
            criterios.setTipoVertimiento((Lista)selectOneChoice1Tipo.getValue());
            i++;
        } 
        
        
        
        try{
            UsuarioConectadoTO usuarioConectado = 
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
            criterios.setAutoridad(usuarioConectado.getUsuario().getAutoridadAmbiental());
            
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance(); 
            this.listaVertimientos = uad.getAllVertimientosBusqueda(criterios);
            
            //Cabril 04/03/2015
            if (listaVertimientos != null) {
                
                Integer j=1;
                
                Iterator<PuntoVertimientoTO> it = listaVertimientos.iterator();
                while (it.hasNext()) {
                    PuntoVertimientoTO usu = it.next();
                    usu.setNum(j);
                   j++;
                } 
            }
            
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.t_vertimientos);//solo las captaciones de la autoridad
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

    public void setListaVertimientos(List listaVertimientos) {
        this.listaVertimientos = listaVertimientos;
    }

    public List getListaVertimientos() {
        return listaVertimientos;
    }

    public void setDetalleVertimiento(String detalleVertimiento) {
        this.detalleVertimiento = detalleVertimiento;
    }

    public String getDetalleVertimiento() {
        return detalleVertimiento;
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

    public void setListaFuentes(List listaFuentes) {
        this.listaFuentes = listaFuentes;
    }

    public List getListaFuentes() {
        return listaFuentes;
    }
    
    ////
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

    public void setCmi_imprimir(RichCommandMenuItem cmi_imprimir) {
        this.cmi_imprimir = cmi_imprimir;
    }

    public RichCommandMenuItem getCmi_imprimir() {
        return cmi_imprimir;
    }

    public void setT_vertimientos(RichTable t_vertimientos) {
        this.t_vertimientos = t_vertimientos;
    }

    public RichTable getT_vertimientos() {
        return t_vertimientos;
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


    public void setCommandLink1(RichCommandLink commandLink1) {
        this.commandLink1 = commandLink1;
    }

    public RichCommandLink getCommandLink1() {
        return commandLink1;
    }

    public void setSelectOneChoiceArea(RichSelectOneChoice selectOneChoiceArea) {
        this.selectOneChoiceArea = selectOneChoiceArea;
    }

    public RichSelectOneChoice getSelectOneChoiceArea() {
        return selectOneChoiceArea;
    }

    public void setSelectItems2Area(UISelectItems selectItems2Area) {
        this.selectItems2Area = selectItems2Area;
    }

    public UISelectItems getSelectItems2Area() {
        return selectItems2Area;
    }

    public void setSelectOneChoiceZona(RichSelectOneChoice selectOneChoiceZona) {
        this.selectOneChoiceZona = selectOneChoiceZona;
    }

    public RichSelectOneChoice getSelectOneChoiceZona() {
        return selectOneChoiceZona;
    }

    public void setSelectItems3Zona(UISelectItems selectItems3Zona) {
        this.selectItems3Zona = selectItems3Zona;
    }

    public UISelectItems getSelectItems3Zona() {
        return selectItems3Zona;
    }

    public void setSelectOneChoiceSubzona(RichSelectOneChoice selectOneChoiceSubzona) {
        this.selectOneChoiceSubzona = selectOneChoiceSubzona;
    }

    public RichSelectOneChoice getSelectOneChoiceSubzona() {
        return selectOneChoiceSubzona;
    }

    public void setSelectItems4Subzona(UISelectItems selectItems4Subzona) {
        this.selectItems4Subzona = selectItems4Subzona;
    }

    public UISelectItems getSelectItems4Subzona() {
        return selectItems4Subzona;
    }

    public void setSelectOneChoice1Fuente(RichSelectOneChoice selectOneChoice1Fuente) {
        this.selectOneChoice1Fuente = selectOneChoice1Fuente;
    }

    public RichSelectOneChoice getSelectOneChoice1Fuente() {
        return selectOneChoice1Fuente;
    }

    public void setSelectItems2(UISelectItems selectItems2) {
        this.selectItems2 = selectItems2;
    }

    public UISelectItems getSelectItems2() {
        return selectItems2;
    }

    public void setSpacer7(RichSpacer spacer7) {
        this.spacer7 = spacer7;
    }

    public RichSpacer getSpacer7() {
        return spacer7;
    }

    public void setSpacer6(RichSpacer spacer6) {
        this.spacer6 = spacer6;
    }

    public RichSpacer getSpacer6() {
        return spacer6;
    }

    public void setPanelGroupLayout3(RichPanelGroupLayout panelGroupLayout3) {
        this.panelGroupLayout3 = panelGroupLayout3;
    }

    public RichPanelGroupLayout getPanelGroupLayout3() {
        return panelGroupLayout3;
    }

    public void setOutputText1(RichOutputText outputText1) {
        this.outputText1 = outputText1;
    }

    public RichOutputText getOutputText1() {
        return outputText1;
    }

    public void setSelectOneChoice1Tipo(RichSelectOneChoice selectOneChoice1) {
        this.selectOneChoice1Tipo = selectOneChoice1;
    }

    public RichSelectOneChoice getSelectOneChoice1Tipo() {
        return selectOneChoice1Tipo;
    }

    public void setSelectItems1(UISelectItems selectItems1) {
        this.selectItems1 = selectItems1;
    }

    public UISelectItems getSelectItems1() {
        return selectItems1;
    }

    public void setVertimientoSeleccionado(PuntoVertimiento vertimientoSeleccionado) {
        this.vertimientoSeleccionado = vertimientoSeleccionado;
    }

    public PuntoVertimiento getVertimientoSeleccionado() {
        return vertimientoSeleccionado;
    }

    public void setListaTipoVertimiento(List listaTipoVertimiento) {
        this.listaTipoVertimiento = listaTipoVertimiento;
    }

    public List getListaTipoVertimiento() {
        return listaTipoVertimiento;
    }
}
