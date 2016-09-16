package co.gov.ideam.sirh.dashboard.view;

import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.seguridad.view.SeguridadDelegate;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;

import co.gov.ideam.sirh.view.StandarDashBoard;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.fragment.RichDynamicDeclarativeComponent;
import oracle.adf.view.rich.component.rich.layout.RichPanelBorderLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelDashboard;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichCommandImageLink;
import oracle.adf.view.rich.component.rich.nav.RichCommandNavigationItem;
import oracle.adf.view.rich.component.rich.nav.RichNavigationPane;
import oracle.adf.view.rich.component.rich.output.RichActiveOutputText;
import oracle.adf.view.rich.component.rich.output.RichImage;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichSpacer;

import oracle.adf.view.rich.context.AdfFacesContext;

import org.apache.myfaces.trinidad.component.UIXIterator;
import org.apache.myfaces.trinidad.component.UIXSwitcher;
import org.apache.myfaces.trinidad.context.RequestContext;

public class DashBoardBean extends StandarDashBoard {
    private RichPanelSplitter ps1;
    private RichDocument d1;
    private RichForm f1;
    private RichForm f2;
    private RichPanelGroupLayout pgl1;
    private RichPanelGroupLayout sideBar;
    private RichPanelStretchLayout psl1;
    private RichPanelDashboard dashboard;
    private RichPanelBox pbnaturaleza;
    private UIXSwitcher sideSwitch;

    private RichPanelGroupLayout pgl3;
    private RichOutputText ot5;
    private RichPanelGroupLayout pgl4;
    private RichOutputText ot6;
    private UIXSwitcher centerSwitcher;
    private RichPanelStretchLayout psl2;

    private List listaPorlets;
    private List listaCategorias;
    private String maximizedKey;
    private String _maximizedPanelUrl;
    private String maximizedTitle;    

    private UIXIterator i1;
    private RichPanelBorderLayout pbl1;
    private RichPanelGroupLayout pgl2;
    private RichPanelGroupLayout pgl5;
    private RichCommandImageLink cil3;
    private RichCommandImageLink cil4;
    private RichOutputText ot7;
    private RichSpacer s1;
    private RichDynamicDeclarativeComponent dc1;
    private RichPanelGroupLayout pgl6;
    private RichNavigationPane np1;
    private RichCommandNavigationItem cni1;
    private RichSpacer s2;
    private RichActiveOutputText aot1;
    private RichPanelBox pb2;
    private RichDynamicDeclarativeComponent dc3;
    private RichDynamicDeclarativeComponent dc4;
    private RichPanelBox pb3;
    private RichDynamicDeclarativeComponent dc5;
    private RichDynamicDeclarativeComponent dc6;
    private RichPanelBox pb4;
    private RichDynamicDeclarativeComponent dc7;
    private RichDynamicDeclarativeComponent dc8;
    private RichPanelBox pb5;
    private RichDynamicDeclarativeComponent dc9;
    private RichDynamicDeclarativeComponent dc10;
    private RichPanelStretchLayout psl3;
    private RichPanelBox pb_max;
    private RichDynamicDeclarativeComponent dc11;
    private RichDynamicDeclarativeComponent dc12;
    private RichDynamicDeclarativeComponent dc13;
    private RichDynamicDeclarativeComponent dc14;
    private RichPanelBox panelBoxCaptaciones;
    private RichPanelBox panelBoxVertimientos;
    private RichPanelBox panelBoxVert;
    private RichPanelBorderLayout pbl2;
    private RichPopup p_error;
    private RichSpacer s5;
    private RichCommandButton cb1;
    private RichSpacer s4;
    private RichDynamicDeclarativeComponent dc21;
    private RichDynamicDeclarativeComponent dc22;
 
    private RichDynamicDeclarativeComponent dc23;
    private RichDynamicDeclarativeComponent dc24;
    private RichDynamicDeclarativeComponent dc26;
    private RichDynamicDeclarativeComponent dc27;
    private RichDynamicDeclarativeComponent dc28;
    private RichDynamicDeclarativeComponent dc29;
    private RichDynamicDeclarativeComponent dc30;
    private RichDynamicDeclarativeComponent dc31;
    private RichDynamicDeclarativeComponent dc32;
    private RichDynamicDeclarativeComponent dc33;
    private RichDynamicDeclarativeComponent dc34;
    private RichDynamicDeclarativeComponent dc35;
    private RichDynamicDeclarativeComponent dc36;
    private RichDynamicDeclarativeComponent dc37;
    private RichDynamicDeclarativeComponent dc38;
    private RichDynamicDeclarativeComponent dc39;
    private RichDynamicDeclarativeComponent dc40;
    private RichDynamicDeclarativeComponent dc41;
    private RichDynamicDeclarativeComponent dc42;
    private RichDynamicDeclarativeComponent dc43;
    private RichDynamicDeclarativeComponent dc44;
    private RichDynamicDeclarativeComponent dc45;
    private RichDynamicDeclarativeComponent dc46;
    private RichDynamicDeclarativeComponent dc47;
    
    private RichDynamicDeclarativeComponent dc48;
    private RichDynamicDeclarativeComponent dc49;
    
    private RichDynamicDeclarativeComponent dc50;
    private RichDynamicDeclarativeComponent dc51;
    
    private RichDynamicDeclarativeComponent dc52;
      private RichDynamicDeclarativeComponent dc53;
    
    
    private RichDynamicDeclarativeComponent dc54;
    private RichDynamicDeclarativeComponent dc55;
    private RichDynamicDeclarativeComponent dc56;
    private RichDynamicDeclarativeComponent dc57;  
      
    private RichDynamicDeclarativeComponent dc58;
    private RichDynamicDeclarativeComponent dc59; 
    
    private RichDynamicDeclarativeComponent dc60;
    private RichDynamicDeclarativeComponent dc61;  
    
    private RichDynamicDeclarativeComponent dc62;
    private RichDynamicDeclarativeComponent dc63; 
    
    //private RichDynamicDeclarativeComponent dc64;
    //private RichDynamicDeclarativeComponent dc65;
    
   // private RichDynamicDeclarativeComponent dc66;
   // private RichDynamicDeclarativeComponent dc67;
    
    private RichDynamicDeclarativeComponent dc70;
    private RichDynamicDeclarativeComponent dc71;

    private RichPanelBox enlacesTramites;
    
    
    private RichPanelBox usosCaptaciones;
    private RichPanelBox muestrasAnio;
    private RichPanelBox muestrasTipo;
    private RichPanelBox boxParametros;
    private RichPanelBox boxCaudales;
    private RichPanelBox pbPrediosPorDpto;
    private RichPanelBox pbCapFuentes;
    private RichPanelBox boxCaudalesVert;

    private RichPanelBox boxCumplimiento;
    private RichPanelBox boxEjecucion;
    
    private RichPanelBox boxPomca;
    private RichPanelBox boxAlerta;

    private RichPanelBox  boxFuentes;
    
    private RichImage i2;
    private RichPanelBox pbFormacion;
    private RichPanelBox pbInvestigacion;
    private RichPanelBox pbGestion;
    private RichOutputText outputText1;
    private RichOutputText nota;

    //private RichPanelBox  boxOferta;
    
    //private RichPanelBox  boxDemanda;

    public DashBoardBean() {
        this.listaCategorias = super.getListaCategorias();
        this.listaPorlets = super.getListaPorlets();
        
        FacesUtils.removeManagedBeanFromSession("naturalezaUsuarios");
        FacesUtils.removeManagedBeanFromSession("usoAguaXActividad");
        FacesUtils.removeManagedBeanFromSession("ubicacionUsuarios");
        FacesUtils.removeManagedBeanFromSession("vigenciaConcesiones");
        FacesUtils.removeManagedBeanFromSession("vigenciaPermisosVertimiento");
        FacesUtils.removeManagedBeanFromSession("aguaConcesionada");
        FacesUtils.removeManagedBeanFromSession("aguaVertida");
        FacesUtils.removeManagedBeanFromSession("CaptacionesFuenteBean");//Pilar
        FacesUtils.removeManagedBeanFromSession("UsosAguaBean");//Pilar
        FacesUtils.removeManagedBeanFromSession("PrediosPorDepartamentoBean");//Pilar
        FacesUtils.removeManagedBeanFromSession("CaptacionTipoFuenteBean");//Pilar
        FacesUtils.removeManagedBeanFromSession("enlacesParametros");
        FacesUtils.removeManagedBeanFromSession("muestrasPorTipo");
        FacesUtils.removeManagedBeanFromSession("muestrasPorAnio");
        FacesUtils.removeManagedBeanFromSession("caudalesConcesionados");
        FacesUtils.removeManagedBeanFromSession("formacionEspecialistas");
        FacesUtils.removeManagedBeanFromSession("gestionEspecialistas");
        FacesUtils.removeManagedBeanFromSession("investigacionGestion");
        FacesUtils.removeManagedBeanFromSession("enlacesAlertas");
		FacesUtils.removeManagedBeanFromSession("AlertaCalidadBean");
        FacesUtils.removeManagedBeanFromSession("AlertContBean");
        FacesUtils.removeManagedBeanFromSession("AlertDispBean");
        // OCGA
        FacesUtils.removeManagedBeanFromSession("BuenasPracticasPorDepartamentoBean");//Pilar
        
/*
 * LIS
 * */
        
        FacesUtils.removeManagedBeanFromSession("dashBoardCaudalesVert");
        FacesUtils.removeManagedBeanFromSession("VertimientosTipoBean");
        FacesUtils.removeManagedBeanFromSession("VertimientosFuenteBean");  
        
        FacesUtils.removeManagedBeanFromSession("enlacesTramites");
        FacesUtils.removeManagedBeanFromSession("dashBoardConcesionesAnio");
        FacesUtils.removeManagedBeanFromSession("dashBoardPermisosAnio");  
        
        
        
        
        /* HUGO C*/
        FacesUtils.removeManagedBeanFromSession("CumplimientoPorProgramaBean");
        FacesUtils.removeManagedBeanFromSession("EjecucionPorProgramaBean");
        FacesUtils.removeManagedBeanFromSession("DashBoardPomcaGrafico1Bean");
        FacesUtils.removeManagedBeanFromSession("DashBoardPomcaGrafico3Bean");
        FacesUtils.removeManagedBeanFromSession("EnlacesAlertas");
        FacesUtils.removeManagedBeanFromSession("AlertDisp");
        
       /* HUGO C*/

        FacesUtils.setActiveBean("dashBoard", "DashBoard",
                                 DashBoardBean.class);
        this.load();
    }

    public void load() {
      
        
        maximizedKey = "empty";
        FacesUtils.setInSession("OrigenNavegacionUsuario","dashboard");
        FacesUtils.setInSession("showDashBoard", new Boolean(Boolean.TRUE));
                
        UsuarioConectadoTO  usuario = 
            (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");  
        if(usuario!=null ){
         System.out.println("------------------UsuarioConectado:"+usuario.getUsuario().
                                                       getAutoridadAmbiental().
                                                       getId().
                                                       longValue());
          
        }
        if(usuario!=null && usuario.getUsuario().isRequiereCambioClave()==1){            
            showMessage(getText("OBLIGA_CAMBIO_CLAVE"), FacesMessage.SEVERITY_WARN);
        }                      
    }

    public void refresh(){
       
        List paneles = this.dashboard.getChildren();
        if(paneles!=null){
            Iterator it = paneles.iterator();
            while(it.hasNext()){
                UIComponent comp = (UIComponent)it.next();
                if(comp instanceof RichPanelBox && 
                   ((RichPanelBox)comp).isRendered()  ){
                    AdfFacesContext.getCurrentInstance().addPartialTarget(comp);
                }
            }
        }
    }
    public void restore(ActionEvent e) {
        
        _maximizedPanelUrl = null;

        UIXSwitcher switcher = this.centerSwitcher;
        switcher.setFacetName("restored");
        RequestContext rc = RequestContext.getCurrentInstance();
        rc.addPartialTarget(switcher);

        // Redraw the side bar so that we can draw update the maximized icons:
        rc.addPartialTarget(_getSideBarContainer());        
    }

    public void maximize(ActionEvent e) {
        
        UIComponent eventComponent = e.getComponent();
        while (eventComponent != null &&
               !(eventComponent instanceof RichPanelBox)) {
            eventComponent = eventComponent.getParent();
        }
        RichPanelBox panelToMaximize = (RichPanelBox)eventComponent;
        _maximizedPanelUrl = super.getUrlFromPanelBox(panelToMaximize);
        PorletDashBoard porlet = super.getPorletFromUrl(_maximizedPanelUrl);
        maximizedTitle = porlet.getNombre();

        UIXSwitcher switcher = this.centerSwitcher;
        switcher.setFacetName("maximized");

        RequestContext rc = RequestContext.getCurrentInstance();
        rc.addPartialTarget(switcher);
        rc.addPartialTarget(pb_max);

        // Redraw the side bar so that we can draw update the maximized icons:
        rc.addPartialTarget(_getSideBarContainer());
    }

    public void minimize(ActionEvent e) {
        UIComponent eventComponent = e.getComponent();
        while (eventComponent != null &&
               !(eventComponent instanceof RichPanelBox)) {
            eventComponent = eventComponent.getParent();
        }
        if (eventComponent != null) {
            _minimize(eventComponent);
        }
    }


    public void showPresetItems(ActionEvent e) {
        
        UIComponent eventComponent = e.getComponent();
        RichCommandNavigationItem item =
            (RichCommandNavigationItem)eventComponent;
        String nombre = item.getText();

        FacesContext context = FacesContext.getCurrentInstance();

        // Remove all items:
        int deleteIndex = 0;
        List<UIComponent> children = _getDashboard().getChildren();
        for (UIComponent child : children) {
            child.setRendered(false);
            _getDashboard().prepareOptimizedEncodingOfDeletedChild(context,
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
        Iterator it = listaCategorias.iterator();
        while (it.hasNext()) {
            Category categoria = (Category)it.next();
            if (categoria.getNombre().equals(nombre)) {
                categoriaSeleccionada = categoria;
                Iterator itPorlet = categoria.getPorletList().iterator();
                while (itPorlet.hasNext()) {
                    PorletDashBoard porlet = (PorletDashBoard)itPorlet.next();
                    int pos = listaPorlets.indexOf(porlet);
                    PorletDashBoard porletFromList =
                        (PorletDashBoard)listaPorlets.get(pos);
                    porletFromList.setRendered(false);
                }
                break;
            }
        }

        int insertIndex = 0;
        children = _getDashboard().getChildren();
        for (UIComponent child : children) {
            UIComponent componente = child;
            while (componente != null &&
                   !(componente instanceof RichPanelBox)) {
                componente = componente.getParent();
            }
            if (componente != null) {
                String url = getUrlFromPanelBox((RichPanelBox)child);
                String portletId = getPortletId((RichPanelBox)child);
                if (categoriaSeleccionada.containsPorlet(url, portletId)) {
                    child.setRendered(true);
                    _getDashboard().prepareOptimizedEncodingOfInsertedChild(context,
                                                                            insertIndex);
                    insertIndex++;
                    AdfFacesContext.getCurrentInstance().addPartialTarget(child);
                }
            }
            /*          if (child.isRendered()){
            // Only count rendered children since that's all that the panelDashboard can see:
            insertIndex++;
          } */

        }
        //AdfFacesContext.getCurrentInstance().addPartialTarget(dashboard);
        RequestContext rc = RequestContext.getCurrentInstance();
        rc.addPartialTarget(_getSideBarContainer());
    }

    public void sideBarShow(ActionEvent e) {
       
        if (_maximizedPanelUrl == null) {
            // Show non-maximized:
            String url =
                (String)e.getComponent().getAttributes().get("urlPorlet");

            int insertIndex = 0;
            List<UIComponent> children = dashboard.getChildren();
            for (UIComponent child : children) {
                if (child instanceof RichPanelBox) {
                    String urlPanel =
                        this.getUrlFromPanelBox((RichPanelBox)child);
                    
                    if (urlPanel != null && urlPanel.equals(url)) {
                        child.setRendered(true);
                        dashboard.prepareOptimizedEncodingOfInsertedChild(FacesContext.getCurrentInstance(),
                                                                          insertIndex);
                        PorletDashBoard porlet = this.getPorletFromUrl(url);
                        porlet.setRendered(false);
                        AdfFacesContext.getCurrentInstance().addPartialTarget(child);
                        break;
                    }
                    if (child.isRendered()) {
                        // Only count rendered children since that's all that the panelDashboard can see:
                        insertIndex++;
                    }
                }
            }

            // Add the side bar as a partial target since we need to redraw the state of the side bar item
            // that corresponds to the inserted item:
            RequestContext rc = RequestContext.getCurrentInstance();
            rc.addPartialTarget(_getSideBarContainer());
        } else {
            // Show maximized:
            sideBarMaximize(e);
        }
    }

    public int getIndexIfRendered() {
       
        int index = 0;
        for (UIComponent child : _getDashboard().getChildren()) {
            if (child.isRendered()) {
                index++;
            }
        }
        return index;
    }

    public void sideBarMaximize(ActionEvent e) {
       
        String url = (String)e.getComponent().getAttributes().get("urlPorlet");
        _maximizedPanelUrl = url;
        PorletDashBoard porletToMaximize = this.getPorletFromUrl(url);
        this.pb_max.setText(porletToMaximize.getNombre());
        UIXSwitcher switcher = centerSwitcher;
        switcher.setFacetName("maximized");
        RequestContext rc = RequestContext.getCurrentInstance();
        rc.addPartialTarget(switcher);

        // Redraw the side bar so that we can draw update the maximized icons:
        rc.addPartialTarget(_getSideBarContainer());
        rc.addPartialTarget(pb_max);
    }

    private void _minimize(UIComponent panelBoxToMinimize) {
        // Make this panelBox non-rendered:
        if (_maximizedPanelUrl == null) {
            panelBoxToMinimize.setRendered(false);
            int deleteIndex = 0;
            RichPanelDashboard dashboard = _getDashboard();
            List<UIComponent> children = dashboard.getChildren();
            for (UIComponent child : children) {
                if (child.equals(panelBoxToMinimize)) {
                    dashboard.prepareOptimizedEncodingOfDeletedChild(FacesContext.getCurrentInstance(),
                                                                     deleteIndex);

                    // Encontrar la url del panelBox para ubicar el Porlet de la lista
                    List lista = panelBoxToMinimize.getChildren();
                    Iterator it = lista.iterator();
                    while (it.hasNext()) {
                        Object comp = it.next();
                        if (comp != null &&
                            comp instanceof RichDynamicDeclarativeComponent) {
                            RichDynamicDeclarativeComponent declarative =
                                (RichDynamicDeclarativeComponent)comp;
                            String url = declarative.getViewId();
                            if (url != null) {
                                PorletDashBoard porlet =
                                    this.getPorletFromUrl(url);
                                porlet.setRendered(true);
                            }
                        }
                    }
                    break;
                }

                if (child.isRendered()) {
                    // Only count rendered children since that's all that the panelDashboard can see:
                    deleteIndex++;
                }
            }
        }

        RequestContext rc = RequestContext.getCurrentInstance();
        if (_maximizedPanelUrl != null) {
            // Exit maximized mode:
            _maximizedPanelUrl = null;

            UIXSwitcher switcher = centerSwitcher;
            switcher.setFacetName("restored");
            rc.addPartialTarget(switcher);
        }

        // Redraw the side bar so that we can update the colors of the opened items:
        rc.addPartialTarget(_getSideBarContainer());
    }

    private RichPanelDashboard _getDashboard() {
        return this.dashboard;
    }

    private UIComponent _getSideBarContainer() {
        return this.sideBar;
    }

    private UIXSwitcher _getSwitcher() {
        return this.centerSwitcher;
    }

    public void setPs1(RichPanelSplitter ps1) {
        this.ps1 = ps1;
    }

    public RichPanelSplitter getPs1() {
        return ps1;
    }

    public void setD1(RichDocument d1) {
        this.d1 = d1;
    }

    public RichDocument getD1() {
        return d1;
    }

    public void setF1(RichForm f1) {
        this.f1 = f1;
    }

    public RichForm getF1() {
        return f1;
    }

    public void setPgl1(RichPanelGroupLayout pgl1) {
        this.pgl1 = pgl1;
    }

    public RichPanelGroupLayout getPgl1() {
        return pgl1;
    }

    public void setSideBar(RichPanelGroupLayout pgl2) {
        this.sideBar = pgl2;
    }

    public RichPanelGroupLayout getSideBar() {
        return sideBar;
    }

    public void setPsl1(RichPanelStretchLayout psl1) {
        this.psl1 = psl1;
    }

    public RichPanelStretchLayout getPsl1() {
        return psl1;
    }

    public void setDashboard(RichPanelDashboard pd1) {
        this.dashboard = pd1;
    }

    public RichPanelDashboard getDashboard() {
        return dashboard;
    }

    public void setPbnaturaleza(RichPanelBox pb1) {
        this.pbnaturaleza = pb1;
    }

    public RichPanelBox getPbnaturaleza() {
        return pbnaturaleza;
    }

    public void setSideSwitch(UIXSwitcher s1) {
        this.sideSwitch = s1;
    }

    public UIXSwitcher getSideSwitch() {
        return sideSwitch;
    }

    public void setPgl3(RichPanelGroupLayout pgl3) {
        this.pgl3 = pgl3;
    }

    public RichPanelGroupLayout getPgl3() {
        return pgl3;
    }

    public void setOt5(RichOutputText ot5) {
        this.ot5 = ot5;
    }

    public RichOutputText getOt5() {
        return ot5;
    }

    public void setPgl4(RichPanelGroupLayout pgl4) {
        this.pgl4 = pgl4;
    }

    public RichPanelGroupLayout getPgl4() {
        return pgl4;
    }

    public void setOt6(RichOutputText ot6) {
        this.ot6 = ot6;
    }

    public RichOutputText getOt6() {
        return ot6;
    }

    public void setCenterSwitcher(UIXSwitcher s1) {
        this.centerSwitcher = s1;
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

    public List getListaPorlets() {
        return listaPorlets;
    }

    public void setListaPorlets(List listaPorlets) {
        this.listaPorlets = listaPorlets;
    }

    public void setI1(UIXIterator i1) {
        this.i1 = i1;
    }

    public UIXIterator getI1() {
        return i1;
    }

    public void setPbl1(RichPanelBorderLayout pbl1) {
        this.pbl1 = pbl1;
    }

    public RichPanelBorderLayout getPbl1() {
        return pbl1;
    }

    public void setPgl2(RichPanelGroupLayout pgl2) {
        this.pgl2 = pgl2;
    }

    public RichPanelGroupLayout getPgl2() {
        return pgl2;
    }

    public void setPgl5(RichPanelGroupLayout pgl5) {
        this.pgl5 = pgl5;
    }

    public RichPanelGroupLayout getPgl5() {
        return pgl5;
    }

    public void setCil3(RichCommandImageLink cil3) {
        this.cil3 = cil3;
    }

    public RichCommandImageLink getCil3() {
        return cil3;
    }

    public void setCil4(RichCommandImageLink cil4) {
        this.cil4 = cil4;
    }

    public RichCommandImageLink getCil4() {
        return cil4;
    }

    public String getMaximizedKey() {
        return maximizedKey;
    }

    public void setMaximizedKey(String maximizedKey) {
        this.maximizedKey = maximizedKey;
    }

    public void setOt7(RichOutputText ot7) {
        this.ot7 = ot7;
    }

    public RichOutputText getOt7() {
        return ot7;
    }

    public void setS1(RichSpacer s1) {
        this.s1 = s1;
    }

    public RichSpacer getS1() {
        return s1;
    }


    public void setDc1(RichDynamicDeclarativeComponent dc1) {
        this.dc1 = dc1;
    }

    public RichDynamicDeclarativeComponent getDc1() {
        return dc1;
    }

    public void setPgl6(RichPanelGroupLayout pgl6) {
        this.pgl6 = pgl6;
    }

    public RichPanelGroupLayout getPgl6() {
        return pgl6;
    }

    public void setNp1(RichNavigationPane np1) {
        this.np1 = np1;
    }

    public RichNavigationPane getNp1() {
        return np1;
    }

    public void setCni1(RichCommandNavigationItem cni1) {
        this.cni1 = cni1;
    }

    public RichCommandNavigationItem getCni1() {
        return cni1;
    }

    public void setS2(RichSpacer s2) {
        this.s2 = s2;
    }

    public RichSpacer getS2() {
        return s2;
    }

    public void setAot1(RichActiveOutputText aot1) {
        this.aot1 = aot1;
    }

    public RichActiveOutputText getAot1() {
        return aot1;
    }

    public void setPb2(RichPanelBox pb2) {
        this.pb2 = pb2;
    }

    public RichPanelBox getPb2() {
        return pb2;
    }

    public void setDc3(RichDynamicDeclarativeComponent dc3) {
        this.dc3 = dc3;
    }

    public RichDynamicDeclarativeComponent getDc3() {
        return dc3;
    }

    public void setDc4(RichDynamicDeclarativeComponent dc4) {
        this.dc4 = dc4;
    }

    public RichDynamicDeclarativeComponent getDc4() {
        return dc4;
    }

    public void setPb3(RichPanelBox pb3) {
        this.pb3 = pb3;
    }

    public RichPanelBox getPb3() {
        return pb3;
    }

    public void setDc5(RichDynamicDeclarativeComponent dc5) {
        this.dc5 = dc5;
    }

    public RichDynamicDeclarativeComponent getDc5() {
        return dc5;
    }

    public void setDc6(RichDynamicDeclarativeComponent dc6) {
        this.dc6 = dc6;
    }

    public RichDynamicDeclarativeComponent getDc6() {
        return dc6;
    }

    public void setPb4(RichPanelBox pb4) {
        this.pb4 = pb4;
    }

    public RichPanelBox getPb4() {
        return pb4;
    }

    public void setDc7(RichDynamicDeclarativeComponent dc7) {
        this.dc7 = dc7;
    }

    public RichDynamicDeclarativeComponent getDc7() {
        return dc7;
    }

    public void setDc8(RichDynamicDeclarativeComponent dc8) {
        this.dc8 = dc8;
    }

    public RichDynamicDeclarativeComponent getDc8() {
        return dc8;
    }

    public void setPb5(RichPanelBox pb5) {
        this.pb5 = pb5;
    }

    public RichPanelBox getPb5() {
        return pb5;
    }

    public void setDc9(RichDynamicDeclarativeComponent dc9) {
        this.dc9 = dc9;
    }

    public RichDynamicDeclarativeComponent getDc9() {
        return dc9;
    }

    public void setDc10(RichDynamicDeclarativeComponent dc10) {
        this.dc10 = dc10;
    }

    public RichDynamicDeclarativeComponent getDc10() {
        return dc10;
    }

    public List getListaCategorias() {
        return listaCategorias;
    }

    public void setListaCategorias(List listaCategorias) {
        this.listaCategorias = listaCategorias;
    }

    public void setPsl3(RichPanelStretchLayout psl3) {
        this.psl3 = psl3;
    }

    public RichPanelStretchLayout getPsl3() {
        return psl3;
    }

    public void setPb_max(RichPanelBox pb6) {
        this.pb_max = pb6;
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

    public String getMaximizedTitle() {
        return maximizedTitle;
    }

    public void setMaximizedTitle(String maximizedTitle) {
        this.maximizedTitle = maximizedTitle;
    }

    public String getMaximizedPanelUrl() {
        return _maximizedPanelUrl;
    }

    public void setMaximizedPanelUrl(String maximizedPanelUrl) {
        _maximizedPanelUrl = maximizedPanelUrl;
    }


    public void setDc12(RichDynamicDeclarativeComponent dc12) {
        this.dc12 = dc12;
    }

    public RichDynamicDeclarativeComponent getDc12() {
        return dc12;
    }


    public RichForm getF2() {
        return f2;
    }

    public void setF2(RichForm f2) {
        this.f2 = f2;
    }

    public void setPanelBoxCaptaciones(RichPanelBox panelBox1) {
        this.panelBoxCaptaciones = panelBox1;
    }

    public RichPanelBox getPanelBoxCaptaciones() {
        return panelBoxCaptaciones;
    }

    public void setDc13(RichDynamicDeclarativeComponent dc13) {
        this.dc13 = dc13;
    }

    public RichDynamicDeclarativeComponent getDc13() {
        return dc13;
    }

    public void setDc14(RichDynamicDeclarativeComponent dc14) {
        this.dc14 = dc14;
    }

    public RichDynamicDeclarativeComponent getDc14() {
        return dc14;
    }


    public void setPbl2(RichPanelBorderLayout pbl2) {
        this.pbl2 = pbl2;
    }

    public RichPanelBorderLayout getPbl2() {
        return pbl2;
    }


    public void setP_error(RichPopup p1) {
        this.p_error = p1;
    }

    public RichPopup getP_error() {
        return p_error;
    }


    public void setS5(RichSpacer s5) {
        this.s5 = s5;
    }

    public RichSpacer getS5() {
        return s5;
    }

    public String getForward() {
        // Add event code here...
        return null;
    }


    public void setCb1(RichCommandButton cb1) {
        this.cb1 = cb1;
    }

    public RichCommandButton getCb1() {
        return cb1;
    }

    public void setS4(RichSpacer s4) {
        this.s4 = s4;
    }

    public RichSpacer getS4() {
        return s4;
    }

    public void setDc21(RichDynamicDeclarativeComponent dc21) {
        this.dc21 = dc21;
    }

    public RichDynamicDeclarativeComponent getDc21() {
        return dc21;
    }

    public void setDc22(RichDynamicDeclarativeComponent dc22) {
        this.dc22 = dc22;
    }

    public RichDynamicDeclarativeComponent getDc22() {
        return dc22;
    }


    public void setDc23(RichDynamicDeclarativeComponent dc23) {
        this.dc23 = dc23;
    }

    public RichDynamicDeclarativeComponent getDc23() {
        return dc23;
    }

    public void setDc24(RichDynamicDeclarativeComponent dc24) {
        this.dc24 = dc24;
    }

    public RichDynamicDeclarativeComponent getDc24() {
        return dc24;
    }



    public void setUsosCaptaciones(RichPanelBox panelBox1) {
        this.usosCaptaciones = panelBox1;
    }

    public RichPanelBox getUsosCaptaciones() {
        return usosCaptaciones;
    }

    public void setDc26(RichDynamicDeclarativeComponent dc26) {
        this.dc26 = dc26;
    }

    public RichDynamicDeclarativeComponent getDc26() {
        return dc26;
    }

    public void setDc27(RichDynamicDeclarativeComponent dc27) {
        this.dc27 = dc27;
    }

    public RichDynamicDeclarativeComponent getDc27() {
        return dc27;
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

    public void setMuestrasTipo(RichPanelBox muestrasTipo) {
        this.muestrasTipo = muestrasTipo;
    }

    public RichPanelBox getMuestrasTipo() {
        return muestrasTipo;
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

    public void setBoxParametros(RichPanelBox boxParametros) {
        this.boxParametros = boxParametros;
    }

    public RichPanelBox getBoxParametros() {
        return boxParametros;
    }

    public void setMuestrasAnio(RichPanelBox muestrasAnio) {
        this.muestrasAnio = muestrasAnio;
    }

    public RichPanelBox getMuestrasAnio() {
        return muestrasAnio;
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

    public void setBoxCaudales(RichPanelBox boxCaudales) {
        this.boxCaudales = boxCaudales;
    }

    public RichPanelBox getBoxCaudales() {
        return boxCaudales;
    }

    public void setPbPrediosPorDpto(RichPanelBox panelBox1) {
        this.pbPrediosPorDpto = panelBox1;
    }

    public RichPanelBox getPbPrediosPorDpto() {
        return pbPrediosPorDpto;
    }

    public void setDc36(RichDynamicDeclarativeComponent dc36) {
        this.dc36 = dc36;
    }

    public RichDynamicDeclarativeComponent getDc36() {
        return dc36;
    }

    public void setDc37(RichDynamicDeclarativeComponent dc37) {
        this.dc37 = dc37;
    }

    public RichDynamicDeclarativeComponent getDc37() {
        return dc37;
    }

    public void setDc38(RichDynamicDeclarativeComponent dc38) {
        this.dc38 = dc38;
    }

    public RichDynamicDeclarativeComponent getDc38() {
        return dc38;
    }

    public void setDc39(RichDynamicDeclarativeComponent dc39) {
        this.dc39 = dc39;
    }

    public RichDynamicDeclarativeComponent getDc39() {
        return dc39;
    }

    public void setDc40(RichDynamicDeclarativeComponent dc40) {
        this.dc40 = dc40;
    }

    public RichDynamicDeclarativeComponent getDc40() {
        return dc40;
    }

    public void setDc41(RichDynamicDeclarativeComponent dc41) {
        this.dc41 = dc41;
    }

    public RichDynamicDeclarativeComponent getDc41() {
        return dc41;
    }

    public void setPbCapFuentes(RichPanelBox pbCapFuentes) {
        this.pbCapFuentes = pbCapFuentes;
    }

    public RichPanelBox getPbCapFuentes() {
        return pbCapFuentes;
    }


    public void setDc42(RichDynamicDeclarativeComponent dc42) {
        this.dc42 = dc42;
    }

    public RichDynamicDeclarativeComponent getDc42() {
        return dc42;
    }

    public void setDc43(RichDynamicDeclarativeComponent dc43) {
        this.dc43 = dc43;
    }

    public RichDynamicDeclarativeComponent getDc43() {
        return dc43;
    }

    public void setDc44(RichDynamicDeclarativeComponent dc44) {
        this.dc44 = dc44;
    }

    public RichDynamicDeclarativeComponent getDc44() {
        return dc44;
    }

    public void setDc45(RichDynamicDeclarativeComponent dc45) {
        this.dc45 = dc45;
    }

    public RichDynamicDeclarativeComponent getDc45() {
        return dc45;
    }

    public void setBoxCumplimiento(RichPanelBox boxCumplimiento) {
        this.boxCumplimiento = boxCumplimiento;
    }

    public RichPanelBox getBoxCumplimiento() {
        return boxCumplimiento;
    }

    public void setBoxEjecucion(RichPanelBox boxEjecucion) {
        this.boxEjecucion = boxEjecucion;
    }

    public RichPanelBox getBoxEjecucion() {
        return boxEjecucion;
    }

    public void setPbFormacion(RichPanelBox pb1) {
        this.pbFormacion = pb1;
    }

    public RichPanelBox getPbFormacion() {
        return pbFormacion;
    }

    public RichDynamicDeclarativeComponent getDc46() {
        return dc46;
    }

    public void setDc46(RichDynamicDeclarativeComponent dc46) {
        this.dc46 = dc46;
    }

    public RichDynamicDeclarativeComponent getDc47() {
        return dc47;
    }

    public void setDc47(RichDynamicDeclarativeComponent dc47) {
        this.dc47 = dc47;
    }

    public void setPbInvestigacion(RichPanelBox pb1) {
        this.pbInvestigacion = pb1;
    }

    public RichPanelBox getPbInvestigacion() {
        return pbInvestigacion;
    }

    public RichDynamicDeclarativeComponent getDc48() {
        return dc48;
    }

    public void setDc48(RichDynamicDeclarativeComponent dc48) {
        this.dc48 = dc48;
    }

    public RichDynamicDeclarativeComponent getDc49() {
        return dc49;
    }

    public void setDc49(RichDynamicDeclarativeComponent dc49) {
        this.dc49 = dc49;
    }

    public RichDynamicDeclarativeComponent getDc50() {
        return dc50;
    }

    public void setDc50(RichDynamicDeclarativeComponent dc50) {
        this.dc50 = dc50;
    }

    public RichDynamicDeclarativeComponent getDc51() {
        return dc51;
    }

    public void setDc51(RichDynamicDeclarativeComponent dc51) {
        this.dc51 = dc51;
    }

    public void setPbGestion(RichPanelBox pb1) {
        this.pbGestion = pb1;
    }

    public RichPanelBox getPbGestion() {
        return pbGestion;
    }

    public void setOutputText1(RichOutputText outputText1) {
        this.outputText1 = outputText1;
    }

    public RichOutputText getOutputText1() {
        return outputText1;
    }

    public void setDc52(RichDynamicDeclarativeComponent dc52) {
        this.dc52 = dc52;
    }

    public RichDynamicDeclarativeComponent getDc52() {
        return dc52;
    }

    public void setDc53(RichDynamicDeclarativeComponent dc53) {
        this.dc53 = dc53;
    }

    public RichDynamicDeclarativeComponent getDc53() {
        return dc53;
    }

    public void setBoxPomca(RichPanelBox boxPomca) {
        this.boxPomca = boxPomca;
    }

    public RichPanelBox getBoxPomca() {
        return boxPomca;
    }

    public void setDc54(RichDynamicDeclarativeComponent dc54) {
        this.dc54 = dc54;
    }

    public RichDynamicDeclarativeComponent getDc54() {
        return dc54;
    }

    public void setDc55(RichDynamicDeclarativeComponent dc55) {
        this.dc55 = dc55;
    }

    public RichDynamicDeclarativeComponent getDc55() {
        return dc55;
    }

    public void setI2(RichImage i2) {
        this.i2 = i2;
    }

    public RichImage getI2() {
        return i2;
    }

    public void setBoxFuentes(RichPanelBox boxFuentes) {
        this.boxFuentes = boxFuentes;
    }

    public RichPanelBox getBoxFuentes() {
        return boxFuentes;
    }

    public void setPanelBoxVertimientos(RichPanelBox panelBoxVertimientos) {
        this.panelBoxVertimientos = panelBoxVertimientos;
    }

    public RichPanelBox getPanelBoxVertimientos() {
        return panelBoxVertimientos;
    }

    public void setDc56(RichDynamicDeclarativeComponent dc56) {
        this.dc56 = dc56;
    }

    public RichDynamicDeclarativeComponent getDc56() {
        return dc56;
    }

    public void setDc57(RichDynamicDeclarativeComponent dc57) {
        this.dc57 = dc57;
    }

    public RichDynamicDeclarativeComponent getDc57() {
        return dc57;
    }

    public void setPanelBoxVert(RichPanelBox panelBoxVert) {
        this.panelBoxVert = panelBoxVert;
    }

    public RichPanelBox getPanelBoxVert() {
        return panelBoxVert;
    }

    public void setDc58(RichDynamicDeclarativeComponent dc58) {
        this.dc58 = dc58;
    }

    public RichDynamicDeclarativeComponent getDc58() {
        return dc58;
    }

    public void setDc59(RichDynamicDeclarativeComponent dc59) {
        this.dc59 = dc59;
    }

    public RichDynamicDeclarativeComponent getDc59() {
        return dc59;
    }

    public void setDc60(RichDynamicDeclarativeComponent dc60) {
        this.dc60 = dc60;
    }

    public RichDynamicDeclarativeComponent getDc60() {
        return dc60;
    }

    public void setDc61(RichDynamicDeclarativeComponent dc61) {
        this.dc61 = dc61;
    }

    public RichDynamicDeclarativeComponent getDc61() {
        return dc61;
    }

    public void setBoxCaudalesVert(RichPanelBox boxCaudalesVert) {
        this.boxCaudalesVert = boxCaudalesVert;
    }

    public RichPanelBox getBoxCaudalesVert() {
        return boxCaudalesVert;
    }

    public void setDc62(RichDynamicDeclarativeComponent dc62) {
        this.dc62 = dc62;
    }

    public RichDynamicDeclarativeComponent getDc62() {
        return dc62;
    }

    public void setDc63(RichDynamicDeclarativeComponent dc63) {
        this.dc63 = dc63;
    }

    public RichDynamicDeclarativeComponent getDc63() {
        return dc63;
    }

    public void setEnlacesTramites(RichPanelBox enlacesTramites) {
        this.enlacesTramites = enlacesTramites;
    }
    
    

    public RichPanelBox getEnlacesTramites() {
        return enlacesTramites;
    }
        /*
    public void setDc64(RichDynamicDeclarativeComponent dc64) {
        this.dc64 = dc64;
    }

    public RichDynamicDeclarativeComponent getDc64() {
        return dc64;
    }

    public void setDc65(RichDynamicDeclarativeComponent dc65) {
        this.dc65 = dc65;
    }

    public RichDynamicDeclarativeComponent getDc65() {
        return dc65;
    }

    public void setBoxOferta(RichPanelBox boxOferta) {
        this.boxOferta = boxOferta;
    }

    public RichPanelBox getBoxOferta() {
        return boxOferta;
    }

   public void setBoxDemanda(RichPanelBox boxDemanda) {
        this.boxDemanda = boxDemanda;
    }

    public RichPanelBox getBoxDemanda() {
        return boxDemanda;
    }


    public void setDc66(RichDynamicDeclarativeComponent dc66) {
        this.dc66 = dc66;
    }

    public RichDynamicDeclarativeComponent getDc66() {
        return dc66;
    }

    public void setDc67(RichDynamicDeclarativeComponent dc67) {
        this.dc67 = dc67;
    }

    public RichDynamicDeclarativeComponent getDc67() {
        return dc67;
    }*/

    public void setDc70(RichDynamicDeclarativeComponent dc70) {
        this.dc70 = dc70;
    }

    public RichDynamicDeclarativeComponent getDc70() {
        return dc70;
    }

    public void setDc71(RichDynamicDeclarativeComponent dc71) {
        this.dc71 = dc71;
    }

    public RichDynamicDeclarativeComponent getDc71() {
        return dc71;
    }

    public void setBoxAlerta(RichPanelBox boxAlerta) {
        this.boxAlerta = boxAlerta;
    }

    public RichPanelBox getBoxAlerta() {
        return boxAlerta;
    }

    public void setNota(RichOutputText outputText2) {
        this.nota = outputText2;
    }

    public RichOutputText getNota() {
        return nota;
    }


}
