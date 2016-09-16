package co.gov.ideam.sirh.observatorio.view;

import co.gov.ideam.sirh.calidad.model.CriteriosBusquedaMuestrasTO;
import co.gov.ideam.sirh.calidad.model.CriteriosBusquedaPuntos;
import co.gov.ideam.sirh.calidad.model.CriteriosBusquedaPuntosTO;
import co.gov.ideam.sirh.calidad.model.PuntoMonitoreo;
import co.gov.ideam.sirh.calidad.model.PuntoMonitoreoTO;
import co.gov.ideam.sirh.calidad.red.ideam.view.FiltrosCalidadIdeam;
import co.gov.ideam.sirh.calidad.view.CalidadDelegate;
import co.gov.ideam.sirh.calidad.view.FiltrosCalidad;
import co.gov.ideam.sirh.dashboard.view.Category;
import co.gov.ideam.sirh.dashboard.view.DashBoardCalidadBean;
import co.gov.ideam.sirh.dashboard.view.DashBoardCalidadGrafico2Bean;
import co.gov.ideam.sirh.dashboard.view.DashBoardCalidadGrafico3Bean;
import co.gov.ideam.sirh.dashboard.view.PorletDashBoard;
import co.gov.ideam.sirh.dashboard.view.PorletList;
import co.gov.ideam.sirh.estadisticas.view.MuestrasPorAnioBean;
import co.gov.ideam.sirh.estadisticas.view.MuestrasPorAnioIdeamBean;
import co.gov.ideam.sirh.estadisticas.view.MuestrasPorTipoBean;
import co.gov.ideam.sirh.estadisticas.view.MuestrasPorTipoIdeamBean;
import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.model.PartZonificListas;
//import co.gov.ideam.sirh.parametros.view.AutoridadesBean;
import co.gov.ideam.sirh.parametros.view.ParametrosDelegate;
import co.gov.ideam.sirh.seguridad.model.PerfilVO;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.seguridad.view.SeguridadDelegate;
import co.gov.ideam.sirh.util.Constantes;
import co.gov.ideam.sirh.util.ConstantesCalidad;
import co.gov.ideam.sirh.util.ConstantesParametros;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarDashBoard;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import javax.faces.model.SelectItem;

import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.fragment.RichDynamicDeclarativeComponent;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelDashboard;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;

import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichCommandNavigationItem;
import oracle.adf.view.rich.component.rich.nav.RichNavigationPane;

import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;

import org.apache.myfaces.trinidad.component.UIXSwitcher;
import org.apache.myfaces.trinidad.context.RequestContext;

public class ObservatorioCalidadBacking extends StandarDashBoard{
    private List listaPorlets;
    private List listaCategorias;
    private String maximizedKey;
    private String _maximizedPanelUrl;
    private String maximizedTitle;    
    
    private RichForm form1;
    private RichDocument document1;
    private RichPanelStretchLayout panelStretchLayout1;
    private UIXSwitcher centerSwitcher;
    private RichPanelStretchLayout psl2;
    private RichPanelStretchLayout psl3;
    
    private RichPanelDashboard dashboard;
    private RichPanelBox pb_max;
    
    private RichDynamicDeclarativeComponent dc11;
    private RichDynamicDeclarativeComponent dc12;
    
    private RichPanelBox muestrasTipo;
    private RichDynamicDeclarativeComponent dc28;
    private RichDynamicDeclarativeComponent dc29;
    
    private RichPanelBox muestrasAnio;
    private RichDynamicDeclarativeComponent dc30;
    private RichDynamicDeclarativeComponent dc31;
    
    private RichPanelBox boxParametros;
    private RichDynamicDeclarativeComponent dc32;
    private RichDynamicDeclarativeComponent dc33;
    
    private RichPanelBox boxRedIdeam;
    private RichDynamicDeclarativeComponent dc34;
    private RichDynamicDeclarativeComponent dc35;
    
    private RichPanelBox muestrasTipoIdeam;
    private RichDynamicDeclarativeComponent dc36;
    private RichDynamicDeclarativeComponent dc37;
    
    private RichPanelBox muestrasAnioIdeam;
    private RichDynamicDeclarativeComponent dc38;
    private RichDynamicDeclarativeComponent dc39;
    
    private RichPanelGroupLayout panelGroupLayout1;
    private RichNavigationPane npItems;
    private RichCommandNavigationItem commandNavigationItem1;
    private RichCommandNavigationItem commandNavigationItem2;
    private RichCommandButton cb_buscar;
    private RichSpacer spacer1;
    private RichPanelGroupLayout panelGroupLayout2;
    private RichSpacer spacer2;
    private RichPanelGroupLayout panelGroupLayout3;


    public ObservatorioCalidadBacking() {      
        FacesUtils.setActiveBean("observatorioCalidadBacking", "ObservatorioCalidadBacking",
                                 ObservatorioCalidadBacking.class);
        FacesUtils.removeFromSession("OrigenNavegacionUsuario");
        FacesUtils.setInSession("OrigenNavegacionUsuario","observatorio");
         
        this.load();        
        }
        
     public void load(){          
         System.out.println("-----------CARGANDO ObservatorioCalidadBacking--------"); 
        Object objFiltros = FacesUtils.getManagedBeanValue("filtrosCalidad");
        ((FiltrosCalidad) objFiltros).setObligatorioFuente(false);
        ((FiltrosCalidad) objFiltros).setVerListaParametros(false);  
        ((FiltrosCalidad) objFiltros).setObligatorioParametro(false);   
        ((FiltrosCalidad) objFiltros).setDisabledListaPuntosMonitoreo(false);          
        ((FiltrosCalidad) objFiltros).setObligatorioPuntoMonitoreo(false);
        ((FiltrosCalidad) objFiltros).setVerFechaInicial(false);                    
        ((FiltrosCalidad) objFiltros).setVerFechaFinal(false);
        ((FiltrosCalidad) objFiltros).setObligatorioAnio(false);    
        ((FiltrosCalidad) objFiltros).setVerListaAnios(true); 
        ((FiltrosCalidad) objFiltros).limpiarFiltros();
        
        

    }
    
    public void cb_buscar_actionListener(ActionEvent actionEvent) {
        Object obj = FacesUtils.getManagedBeanValue("filtrosCalidad");
        CriteriosBusquedaMuestrasTO criterios = null;
        
        if(obj!=null){
          criterios = ((FiltrosCalidad) obj).getCriterios();   
            if (criterios==null){
                showMessage("Debe seleccionar una Autoridad Ambiental o un Departamento", FacesMessage.SEVERITY_ERROR);                
                return ;
            }else{
                // validar que se haya seleccionado una Autoridad Ambiental o Departamento
                if(criterios.getAutoridad()==null && criterios.getDepartamento()==null){
                    showMessage("Debe seleccionar una Autoridad Ambiental o Departamento", FacesMessage.SEVERITY_ERROR);
                    return ;
                }                                        
            }                
        }         
        
        if(criterios!=null){                             
            if(criterios.getAutoridad()!=null){      
                if(criterios.getAutoridad().getId()!= 1){  
                    this.muestrasTipo.setRendered(true);
                    this.muestrasAnio.setRendered(true);
                    this.muestrasTipoIdeam.setRendered(false);
                    this.muestrasAnioIdeam.setRendered(false);
                    this.actualizarGraficasAutoridadesAmbientales();                   
                }else{                
                    this.muestrasTipoIdeam.setRendered(true);
                    this.muestrasAnioIdeam.setRendered(true);
                    this.muestrasTipo.setRendered(false);
                    this.muestrasAnio.setRendered(false);
                    this.actualizarGraficasIdeam();
                }                
            }else{ 
                   this.actualizarGraficasAutoridadesAmbientales();
                   this.actualizarGraficasIdeam();      
            }               
        }
         
        FacesContext context = FacesContext.getCurrentInstance();
        this.dashboard.prepareOptimizedEncodingOfInsertedChild(context);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.dashboard);

        this.refresh();       
    }
   
    public void actualizarGraficasAutoridadesAmbientales(){
        Object obj = FacesUtils.getManagedBeanValue("filtrosCalidad");
        CriteriosBusquedaMuestrasTO criterios = null;
        
        if(obj!=null){
            criterios = ((FiltrosCalidad) obj).getCriterios(); 
        }  
        try{   
// Actualiza Graficas Autoridades Ambientales  
            CalidadDelegate cd1 = CalidadDelegate.getInstance();
            String[][] datos1 = cd1.getCantidadTipoMuestrasCriterios(criterios);
            obj = FacesUtils.getManagedBeanValue("muestrasPorTipo");
            if(obj!=null){
                MuestrasPorTipoBean beanGrafico1 = (MuestrasPorTipoBean)obj;
                beanGrafico1.actualizarDatos(datos1);               
            }
            
            CalidadDelegate cd2 = CalidadDelegate.getInstance(); 
            String[][] datos2 = cd2.getCantidadMuestrasAnioCriterios(criterios);               
            obj = FacesUtils.getManagedBeanValue("muestrasPorAnio");
            if(obj!=null){
                MuestrasPorAnioBean beanGrafico2 = (MuestrasPorAnioBean)obj;
                beanGrafico2.actualizarDatos(datos2);   
            }
        }catch(IdeamException e){
            showMessage(e);
        }
    }
    
    public void actualizarGraficasIdeam(){
        Object obj = FacesUtils.getManagedBeanValue("filtrosCalidad");
        CriteriosBusquedaMuestrasTO criterios = null;
        
        if(obj!=null){
            criterios = ((FiltrosCalidad) obj).getCriterios(); 
        }  
        try{   
// Actualiza Graficas IDEAM                    
           CalidadDelegate cd3 = CalidadDelegate.getInstance();
           String[][] datos3 = cd3.getCantidadTipoMuestrasCriteriosIdeam(criterios);
           obj = FacesUtils.getManagedBeanValue("muestrasPorTipoIdeam");
           if(obj!=null){
               MuestrasPorTipoIdeamBean beanGrafico3 = (MuestrasPorTipoIdeamBean)obj;
               beanGrafico3.actualizarDatos(datos3);               
           }
           
           CalidadDelegate cd4 = CalidadDelegate.getInstance(); 
           String[][] datos4 = cd4.getCantidadMuestrasAnioCriteriosIdeam(criterios);               
           obj = FacesUtils.getManagedBeanValue("muestrasPorAnioIdeam");
           if(obj!=null){
               MuestrasPorAnioIdeamBean beanGrafico4 = (MuestrasPorAnioIdeamBean)obj;
               beanGrafico4.actualizarDatos(datos4); 
           }   
        }catch(IdeamException e){
            showMessage(e);
        }
    }
    public void minimize(ActionEvent e) {
        System.out.println("=======================================Observatorio calidad minimize");
        UIComponent eventComponent = e.getComponent();
        while (eventComponent != null &&
               !(eventComponent instanceof RichPanelBox)) {
            eventComponent = eventComponent.getParent();
            if(eventComponent instanceof RichPanelBox){
                eventComponent.setRendered(false);
            }
        }
        
        RequestContext rc = RequestContext.getCurrentInstance();
        rc.addPartialTarget(this.dashboard);
        
    }
    
    public void restore(ActionEvent e) {
        
        _maximizedPanelUrl = null;

        UIXSwitcher switcher = this.centerSwitcher;
        switcher.setFacetName("restored");
        RequestContext rc = RequestContext.getCurrentInstance();
        rc.addPartialTarget(switcher);
        rc.addPartialTarget(this.dashboard);
        // Redraw the side bar so that we can draw update the maximized icons:
        //rc.addPartialTarget(_getSideBarContainer());        
    }
    
    public void restaurarTodo(ActionEvent e) {
        System.out.println("=======================================Observatorio calidad restore all");
        UIComponent eventComponent = e.getComponent();
        FacesContext context = FacesContext.getCurrentInstance();
        
        List<UIComponent> children = this.dashboard.getChildren();
        for (UIComponent child : children) {
            if (child.getId().equalsIgnoreCase("boxParametros") ||
                child.getId().equalsIgnoreCase("boxRed")){
                child.setRendered(false);
            }else{
                child.setRendered(true);
            }
            AdfFacesContext.getCurrentInstance().addPartialTarget(child);
        }
        this.dashboard.prepareOptimizedEncodingOfInsertedChild(context);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.dashboard);

        this.refresh();
        
        Object obj = FacesUtils.getManagedBeanValue("muestrasPorAnio");
        if(obj!=null){
            MuestrasPorAnioBean beanGrafico = (MuestrasPorAnioBean)obj;
            beanGrafico.graficarTodo();
        }

        obj = FacesUtils.getManagedBeanValue("muestrasPorAnioIdeam");
        if(obj!=null){
            MuestrasPorAnioIdeamBean beanGrafico = (MuestrasPorAnioIdeamBean)obj;
            beanGrafico.graficarTodo();
        }

        obj = FacesUtils.getManagedBeanValue("muestrasPorTipo");
        if(obj!=null){
            MuestrasPorTipoBean beanGrafico = (MuestrasPorTipoBean)obj;
            beanGrafico.graficarTodo();
        }
        
        obj = FacesUtils.getManagedBeanValue("muestrasPorTipoIdeam");
        if(obj!=null){
            MuestrasPorTipoIdeamBean beanGrafico = (MuestrasPorTipoIdeamBean)obj;
            beanGrafico.graficarTodo();
        }        
           
        obj = FacesUtils.getManagedBeanValue("filtrosCalidad");
        if(obj!=null){
            FiltrosCalidad filtrosBean = (FiltrosCalidad)obj;
            filtrosBean.limpiarFiltros();
        }        
           
    }
    
    public void maximize(ActionEvent e) {
        System.out.println("=======================================Observatorio demanda maximize");
        
        UIComponent eventComponent = e.getComponent();
        while (eventComponent != null &&
               !(eventComponent instanceof RichPanelBox)) {
            eventComponent = eventComponent.getParent();
        }
        
        ///
        RichPanelBox panelToMaximize = (RichPanelBox)eventComponent;
        _maximizedPanelUrl = super.getUrlFromPanelBox(panelToMaximize);
        PorletDashBoard porlet = super.getPorletFromUrl(_maximizedPanelUrl);
        if(porlet!=null){
            maximizedTitle = porlet.getNombre();
        }
        ///
        
        UIXSwitcher switcher = this.centerSwitcher;
        switcher.setFacetName("maximized");

        RequestContext rc = RequestContext.getCurrentInstance();
        rc.addPartialTarget(switcher);
        rc.addPartialTarget(pb_max);

        // Redraw the side bar so that we can draw update the maximized icons:
        //rc.addPartialTarget(_getSideBarContainer());
    }
    
    public int getIndexIfRendered() {
       
        int index = 0;
        for (UIComponent child : this.dashboard.getChildren()) {
            if (child.isRendered()) {
                index++;
            }
            
        }
        return index;
    }
    
    public void showPresetItems(ActionEvent e) {
        
        UIComponent eventComponent = e.getComponent();
        RichCommandNavigationItem item =
            (RichCommandNavigationItem)eventComponent;
        String nombre = item.getText();

        FacesContext context = FacesContext.getCurrentInstance();

        // Remove all items:
        int deleteIndex = 0;
        List<UIComponent> children = this.dashboard.getChildren();
        for (UIComponent child : children) {
            if (child.getId().equalsIgnoreCase("boxParametros") ||
                child.getId().equalsIgnoreCase("boxRed")){
                child.setRendered(true);
            }else{
                child.setRendered(false);
            }
            AdfFacesContext.getCurrentInstance().addPartialTarget(child);
        }
        this.dashboard.prepareOptimizedEncodingOfInsertedChild(context);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.dashboard);
                
        
        /*for (UIComponent child : children) {
            child.setRendered(false);
            this.dashboard.prepareOptimizedEncodingOfDeletedChild(context,
                                                                   deleteIndex);
            deleteIndex++;

            UIComponent componente = child;
            while (componente != null &&
                   !(componente instanceof RichPanelBox)) {
                componente = componente.getParent();
            }
            if (eventComponent != null) {
                String url = getUrlFromPanelBox((RichPanelBox)child);
                PorletDashBoard porlet = this.getPorletFromUrl(url);
                if(porlet!=null){
                    porlet.setRendered(true);
                }
            }
        }

        Category categoriaSeleccionada = null;
        Iterator it = listaCategoriasCalidad.iterator();
        while (it.hasNext()) {
            Category categoria = (Category)it.next();
            if (categoria.getNombre().equals(nombre)) {
                categoriaSeleccionada = categoria;
                Iterator itPorlet = categoria.getPorletList().iterator();
                while (itPorlet.hasNext()) {
                    PorletDashBoard porlet = (PorletDashBoard)itPorlet.next();
                    int pos = (super.getListaPorlets()).indexOf(porlet);
                    PorletDashBoard porletFromList =
                        (PorletDashBoard)(super.getListaPorlets()).get(pos);
                    porletFromList.setRendered(false);
                }
                break;
            }
        }

        int insertIndex = 0;
        children = this.dashboard.getChildren();
        for (UIComponent child : children) {
            UIComponent componente = child;
            while (componente != null &&
                   !(componente instanceof RichPanelBox)) {
                componente = componente.getParent();
            }
            if (componente != null) {
                String url = getUrlFromPanelBox((RichPanelBox)child);
                if (categoriaSeleccionada.containsPorlet(url)) {
                    child.setRendered(true);
                    this.dashboard.prepareOptimizedEncodingOfInsertedChild(context,
                                                                           insertIndex);
                    insertIndex++;
                    AdfFacesContext.getCurrentInstance().addPartialTarget(child);
                    AdfFacesContext.getCurrentInstance().addPartialTarget(this.dashboard);
                }
            }

        }*/

        this.refresh();

    }

    public void refresh() {

        List paneles = this.dashboard.getChildren();
        if (paneles != null) {
            Iterator it = paneles.iterator();
            while (it.hasNext()) {
                UIComponent comp = (UIComponent)it.next();
                if (comp instanceof RichPanelBox &&
                    ((RichPanelBox)comp).isRendered()) {
                    AdfFacesContext.getCurrentInstance().addPartialTarget(comp);
                }
            }
        }
        RequestContext rc = RequestContext.getCurrentInstance();
        rc.addPartialTarget(this.centerSwitcher);

    }

    public List getListaCategoriasCalidad() {
        return super.listaCategoriasCalidad;
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

    public void setListaPorlets(List listaPorlets) {
        this.listaPorlets = listaPorlets;
    }

    public List getListaPorlets() {
        return listaPorlets;
    }

    public void setListaCategorias(List listaCategorias) {
        this.listaCategorias = listaCategorias;
    }

    public List getListaCategorias() {
        return listaCategorias;
    }

    public void setMaximizedKey(String maximizedKey) {
        this.maximizedKey = maximizedKey;
    }

    public String getMaximizedKey() {
        return maximizedKey;
    }

    public void setMaximizedPanelUrl(String _maximizedPanelUrl) {
        this._maximizedPanelUrl = _maximizedPanelUrl;
    }

    public String getMaximizedPanelUrl() {
        return _maximizedPanelUrl;
    }

    public void setMaximizedTitle(String maximizedTitle) {
        this.maximizedTitle = maximizedTitle;
    }

    public String getMaximizedTitle() {
        return maximizedTitle;
    }

    public void setPanelStretchLayout1(RichPanelStretchLayout panelStretchLayout1) {
        this.panelStretchLayout1 = panelStretchLayout1;
    }

    public RichPanelStretchLayout getPanelStretchLayout1() {
        return panelStretchLayout1;
    }

    public void setCenterSwitcher(UIXSwitcher centerSwitcher) {
        this.centerSwitcher = centerSwitcher;
    }

    public UIXSwitcher getCenterSwitcher() {
        return centerSwitcher;
    }

    public void setPsl2(RichPanelStretchLayout psl2) {
        this.psl2 = psl2;
    }

    public RichPanelStretchLayout getPsl2() {
        return psl2;
    }

    public void setPsl3(RichPanelStretchLayout psl3) {
        this.psl3 = psl3;
    }

    public RichPanelStretchLayout getPsl3() {
        return psl3;
    }

    public void setDashboard(RichPanelDashboard dashboard) {
        this.dashboard = dashboard;
    }

    public RichPanelDashboard getDashboard() {
        return dashboard;
    }

    public void setPb_max(RichPanelBox pb_max) {
        this.pb_max = pb_max;
    }

    public RichPanelBox getPb_max() {
        return pb_max;
    }

    public void setDc11(RichDynamicDeclarativeComponent dc11) {
        this.dc11 = dc11;
    }

    public RichDynamicDeclarativeComponent getDc11() {
        return dc11;
    }

    public void setDc12(RichDynamicDeclarativeComponent dc12) {
        this.dc12 = dc12;
    }

    public RichDynamicDeclarativeComponent getDc12() {
        return dc12;
    }

    public void setMuestrasTipo(RichPanelBox muestrasTipo) {
        this.muestrasTipo = muestrasTipo;
    }

    public RichPanelBox getMuestrasTipo() {
        return muestrasTipo;
    }

    public void setDc28(RichDynamicDeclarativeComponent dc28) {
        this.dc28 = dc28;
    }

    public RichDynamicDeclarativeComponent getDc28() {
        return dc28;
    }

    public void setDc29(RichDynamicDeclarativeComponent dc29) {
        this.dc29 = dc29;
    }

    public RichDynamicDeclarativeComponent getDc29() {
        return dc29;
    }

    public void setMuestrasAnio(RichPanelBox muestrasAnio) {
        this.muestrasAnio = muestrasAnio;
    }

    public RichPanelBox getMuestrasAnio() {
        return muestrasAnio;
    }

    public void setDc30(RichDynamicDeclarativeComponent dc30) {
        this.dc30 = dc30;
    }

    public RichDynamicDeclarativeComponent getDc30() {
        return dc30;
    }

    public void setDc31(RichDynamicDeclarativeComponent dc31) {
        this.dc31 = dc31;
    }

    public RichDynamicDeclarativeComponent getDc31() {
        return dc31;
    }

    public void setBoxParametros(RichPanelBox boxParametros) {
        this.boxParametros = boxParametros;
    }

    public RichPanelBox getBoxParametros() {
        return boxParametros;
    }

    public void setDc32(RichDynamicDeclarativeComponent dc32) {
        this.dc32 = dc32;
    }

    public RichDynamicDeclarativeComponent getDc32() {
        return dc32;
    }

    public void setDc33(RichDynamicDeclarativeComponent dc33) {
        this.dc33 = dc33;
    }

    public RichDynamicDeclarativeComponent getDc33() {
        return dc33;
    }

    public void setPanelGroupLayout1(RichPanelGroupLayout panelGroupLayout1) {
        this.panelGroupLayout1 = panelGroupLayout1;
    }

    public RichPanelGroupLayout getPanelGroupLayout1() {
        return panelGroupLayout1;
    }

    public void setNpItems(RichNavigationPane npItems) {
        this.npItems = npItems;
    }

    public RichNavigationPane getNpItems() {
        return npItems;
    }

    public void setCommandNavigationItem1(RichCommandNavigationItem commandNavigationItem1) {
        this.commandNavigationItem1 = commandNavigationItem1;
    }

    public RichCommandNavigationItem getCommandNavigationItem1() {
        return commandNavigationItem1;
    }

    public void setCommandNavigationItem2(RichCommandNavigationItem commandNavigationItem2) {
        this.commandNavigationItem2 = commandNavigationItem2;
    }

    public RichCommandNavigationItem getCommandNavigationItem2() {
        return commandNavigationItem2;
    }


    public void setBoxRedIdeam(RichPanelBox boxRedIdeam) {
        this.boxRedIdeam = boxRedIdeam;
    }

    public RichPanelBox getBoxRedIdeam() {
        return boxRedIdeam;
    }

    public void setDc34(RichDynamicDeclarativeComponent dc34) {
        this.dc34 = dc34;
    }

    public RichDynamicDeclarativeComponent getDc34() {
        return dc34;
    }

    public void setDc35(RichDynamicDeclarativeComponent dc35) {
        this.dc35 = dc35;
    }

    public RichDynamicDeclarativeComponent getDc35() {
        return dc35;
    }

    public void setCb_buscar(RichCommandButton cb_buscar) {
        this.cb_buscar = cb_buscar;
    }

    public RichCommandButton getCb_buscar() {
        return cb_buscar;
    }


    public void setSpacer1(RichSpacer spacer1) {
        this.spacer1 = spacer1;
    }

    public RichSpacer getSpacer1() {
        return spacer1;
    }

    public void setPanelGroupLayout2(RichPanelGroupLayout panelGroupLayout2) {
        this.panelGroupLayout2 = panelGroupLayout2;
    }

    public RichPanelGroupLayout getPanelGroupLayout2() {
        return panelGroupLayout2;
    }

    public void setSpacer2(RichSpacer spacer2) {
        this.spacer2 = spacer2;
    }

    public RichSpacer getSpacer2() {
        return spacer2;
    }

    public RichPanelBox getMuestrasTipoIdeam() {
        return muestrasTipoIdeam;
    }

    public void setMuestrasTipoIdeam(RichPanelBox muestrasTipoIdeam) {
        this.muestrasTipoIdeam = muestrasTipoIdeam;
    }

    public RichDynamicDeclarativeComponent getDc36() {
        return dc36;
    }

    public void setDc36(RichDynamicDeclarativeComponent dc36) {
        this.dc36 = dc36;
    }

    public RichDynamicDeclarativeComponent getDc37() {
        return dc37;
    }

    public void setDc37(RichDynamicDeclarativeComponent dc37) {
        this.dc37 = dc37;
    }

    public RichPanelBox getMuestrasAnioIdeam() {
        return muestrasAnioIdeam;
    }

    public void setMuestrasAnioIdeam(RichPanelBox muestrasAnioIdeam) {
        this.muestrasAnioIdeam = muestrasAnioIdeam;
    }

    public RichDynamicDeclarativeComponent getDc38() {
        return dc38;
    }

    public void setDc38(RichDynamicDeclarativeComponent dc38) {
        this.dc38 = dc38;
    }

    public RichDynamicDeclarativeComponent getDc39() {
        return dc39;
    }

    public void setDc39(RichDynamicDeclarativeComponent dc39) {
        this.dc39 = dc39;
    }

    public void setPanelGroupLayout3(RichPanelGroupLayout panelGroupLayout3) {
        this.panelGroupLayout3 = panelGroupLayout3;
    }

    public RichPanelGroupLayout getPanelGroupLayout3() {
        return panelGroupLayout3;
    }
}
