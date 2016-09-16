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

import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.nav.RichCommandNavigationItem;
import oracle.adf.view.rich.component.rich.nav.RichNavigationPane;

import oracle.adf.view.rich.context.AdfFacesContext;

import org.apache.myfaces.trinidad.component.UIXSwitcher;
import org.apache.myfaces.trinidad.context.RequestContext;

public class ObservatorioGestionBacking extends StandarDashBoard{
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


    private RichPanelBox pbFormacion;
    private RichDynamicDeclarativeComponent dc46;
    private RichDynamicDeclarativeComponent dc47;
    
    private RichPanelBox pbInvestigacion;
    private RichDynamicDeclarativeComponent dc48;
    private RichDynamicDeclarativeComponent dc49;
    
    private RichPanelBox boxPomca;
    private RichDynamicDeclarativeComponent dc52;
    private RichDynamicDeclarativeComponent dc53;
    
    private RichPanelBox boxPorh;
    private RichCommandLink commandLinkPorhObservatorio1;
    private RichCommandLink commandLinkPorhObservatorio2;
    private RichCommandLink commandLinkPorhObservatorio3;
    private RichCommandLink commandLinkPorhObservatorio4;
    private RichDynamicDeclarativeComponent dctoolbarporh;
    
    private RichPanelBox pbGestion;
    private RichDynamicDeclarativeComponent dc50;
    private RichDynamicDeclarativeComponent dc51;
    
    private RichPanelBox boxFuentes;
    private RichDynamicDeclarativeComponent dc54;
    private RichDynamicDeclarativeComponent dc55;

    private RichPanelBox conflictosTipo;
    private RichDynamicDeclarativeComponent dc56;
    private RichDynamicDeclarativeComponent dc57;
    
    private RichPanelBox conflictosXFuente;
    private RichDynamicDeclarativeComponent dc58;
    private RichDynamicDeclarativeComponent dc59;
    
    private RichPanelGroupLayout panelGroupLayout1;
    private RichNavigationPane npItems;
    private RichCommandNavigationItem commandNavigationItem1;
    private RichCommandNavigationItem commandNavigationItem2;

    public ObservatorioGestionBacking() {
        FacesUtils.setActiveBean("observatorioGestionBacking", "ObservatorioGestionBacking",
                                 ObservatorioGestionBacking.class);
        FacesUtils.removeFromSession("OrigenNavegacionUsuario");
        FacesUtils.setInSession("OrigenNavegacionUsuario","observatorio");
    }
    
    public void minimize(ActionEvent e) {
        System.out.println("=======================================Observatorio gestion minimize");
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
        System.out.println("=======================================Observatorio demanda restore all");
        UIComponent eventComponent = e.getComponent();
        FacesContext context = FacesContext.getCurrentInstance();

        List<UIComponent> children = this.dashboard.getChildren();
        for (UIComponent child : children) {
            child.setRendered(true);
            AdfFacesContext.getCurrentInstance().addPartialTarget(child);
        }
        this.dashboard.prepareOptimizedEncodingOfInsertedChild(context);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.dashboard);

        this.refresh();
        
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
        Iterator it = listaCategoriasGestion.iterator();
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
    
    public List getListaCategoriasGestion() {
        return super.listaCategoriasGestion;
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

    public void setPbFormacion(RichPanelBox pbFormacion) {
        this.pbFormacion = pbFormacion;
    }

    public RichPanelBox getPbFormacion() {
        return pbFormacion;
    }

    public void setDc46(RichDynamicDeclarativeComponent dc46) {
        this.dc46 = dc46;
    }

    public RichDynamicDeclarativeComponent getDc46() {
        return dc46;
    }

    public void setDc47(RichDynamicDeclarativeComponent dc47) {
        this.dc47 = dc47;
    }

    public RichDynamicDeclarativeComponent getDc47() {
        return dc47;
    }

    public void setPbInvestigacion(RichPanelBox pbInvestigacion) {
        this.pbInvestigacion = pbInvestigacion;
    }

    public RichPanelBox getPbInvestigacion() {
        return pbInvestigacion;
    }

    public void setDc48(RichDynamicDeclarativeComponent dc48) {
        this.dc48 = dc48;
    }

    public RichDynamicDeclarativeComponent getDc48() {
        return dc48;
    }

    public void setDc49(RichDynamicDeclarativeComponent dc49) {
        this.dc49 = dc49;
    }

    public RichDynamicDeclarativeComponent getDc49() {
        return dc49;
    }

    public void setBoxPomca(RichPanelBox boxPomca) {
        this.boxPomca = boxPomca;
    }

    public RichPanelBox getBoxPomca() {
        return boxPomca;
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

    public void setPbGestion(RichPanelBox pbGestion) {
        this.pbGestion = pbGestion;
    }

    public RichPanelBox getPbGestion() {
        return pbGestion;
    }

    public void setDc50(RichDynamicDeclarativeComponent dc50) {
        this.dc50 = dc50;
    }

    public RichDynamicDeclarativeComponent getDc50() {
        return dc50;
    }

    public void setDc51(RichDynamicDeclarativeComponent dc51) {
        this.dc51 = dc51;
    }

    public RichDynamicDeclarativeComponent getDc51() {
        return dc51;
    }

    public void setBoxFuentes(RichPanelBox boxFuentes) {
        this.boxFuentes = boxFuentes;
    }

    public RichPanelBox getBoxFuentes() {
        return boxFuentes;
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

    public void setConflictosTipo(RichPanelBox conflictosTipo) {
        this.conflictosTipo = conflictosTipo;
    }

    public RichPanelBox getConflictosTipo() {
        return conflictosTipo;
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

    public void setConflictosXFuente(RichPanelBox conflictosXFuente) {
        this.conflictosXFuente = conflictosXFuente;
    }

    public RichPanelBox getConflictosXFuente() {
        return conflictosXFuente;
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

  public void setBoxPorh(RichPanelBox boxPorh) {
    this.boxPorh = boxPorh;
  }

  public RichPanelBox getBoxPorh() {
    return boxPorh;
  }

  public void setCommandLinkPorhObservatorio1(RichCommandLink commandLinkPorhObservatorio1) {
    this.commandLinkPorhObservatorio1 = commandLinkPorhObservatorio1;
  }

  public RichCommandLink getCommandLinkPorhObservatorio1() {
    return commandLinkPorhObservatorio1;
  }

  public void setDctoolbarporh(RichDynamicDeclarativeComponent dctoolbarporh) {
    this.dctoolbarporh = dctoolbarporh;
  }

  public RichDynamicDeclarativeComponent getDctoolbarporh() {
    return dctoolbarporh;
  }

  public void setCommandLinkPorhObservatorio2(RichCommandLink commandLinkPorhObservatorio2) {
    this.commandLinkPorhObservatorio2 = commandLinkPorhObservatorio2;
  }

  public RichCommandLink getCommandLinkPorhObservatorio2() {
    return commandLinkPorhObservatorio2;
  }

  public void setCommandLinkPorhObservatorio3(RichCommandLink commandLinkPorhObservatorio3) {
    this.commandLinkPorhObservatorio3 = commandLinkPorhObservatorio3;
  }

  public RichCommandLink getCommandLinkPorhObservatorio3() {
    return commandLinkPorhObservatorio3;
  }

  public void setCommandLinkPorhObservatorio4(RichCommandLink commandLinkPorhObservatorio4) {
    this.commandLinkPorhObservatorio4 = commandLinkPorhObservatorio4;
  }

  public RichCommandLink getCommandLinkPorhObservatorio4() {
    return commandLinkPorhObservatorio4;
  }
}
