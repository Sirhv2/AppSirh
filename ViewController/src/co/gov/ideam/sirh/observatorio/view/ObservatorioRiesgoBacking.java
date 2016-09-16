package co.gov.ideam.sirh.observatorio.view;

import co.gov.ideam.sirh.dashboard.view.Category;
import co.gov.ideam.sirh.dashboard.view.PorletDashBoard;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarDashBoard;

import java.util.Iterator;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.fragment.RichDynamicDeclarativeComponent;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelDashboard;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;

import oracle.adf.view.rich.component.rich.nav.RichCommandNavigationItem;
import oracle.adf.view.rich.component.rich.nav.RichNavigationPane;

import oracle.adf.view.rich.context.AdfFacesContext;

import org.apache.myfaces.trinidad.component.UIXSwitcher;
import org.apache.myfaces.trinidad.context.RequestContext;

public class ObservatorioRiesgoBacking extends StandarDashBoard{
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
    
    private RichPanelGroupLayout panelGroupLayout1;
    private RichNavigationPane npItems;
    private RichCommandNavigationItem commandNavigationItem1;
    private RichCommandNavigationItem commandNavigationItem2;


    private RichPanelBox boxAlerta;
    private RichDynamicDeclarativeComponent dc1;
    private RichDynamicDeclarativeComponent dc2;
    
    public ObservatorioRiesgoBacking() {
        FacesUtils.setActiveBean("observatorioRiesgoBacking", "ObservatorioRiesgoBacking",
                                 ObservatorioRiesgoBacking.class);
    }
    
    public void minimize(ActionEvent e) {
        System.out.println("=======================================Observatorio riesgo minimize");
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
        maximizedTitle = porlet.getNombre();
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
        Iterator it = listaCategoriasRiesgo.iterator();
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

        }

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
    
    public List getListaCategoriasRiesgo() {
        return super.listaCategoriasRiesgo;
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

    public void setBoxAlerta(RichPanelBox boxAlerta) {
        this.boxAlerta = boxAlerta;
    }

    public RichPanelBox getBoxAlerta() {
        return boxAlerta;
    }

    public void setDc1(RichDynamicDeclarativeComponent dc1) {
        this.dc1 = dc1;
    }

    public RichDynamicDeclarativeComponent getDc1() {
        return dc1;
    }

    public void setDc2(RichDynamicDeclarativeComponent dc2) {
        this.dc2 = dc2;
    }

    public RichDynamicDeclarativeComponent getDc2() {
        return dc2;
    }
}
