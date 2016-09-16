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
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;

import org.apache.myfaces.trinidad.component.UIXSwitcher;
import org.apache.myfaces.trinidad.context.RequestContext;

public class ObservatorioDemandaSubtBaking extends StandarDashBoard{
    
    
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
    
    private RichPanelBox pbnaturaleza;
    private RichDynamicDeclarativeComponent dc1;
                                           
    private RichPanelBox pbPrediosPorDpto;
    private RichDynamicDeclarativeComponent dc36;
    private RichDynamicDeclarativeComponent dc37;
    
    private RichPanelBox pbCapFuentes;
    private RichDynamicDeclarativeComponent dc40;
    private RichDynamicDeclarativeComponent dc41;
    
    private RichPanelBox pb3;
    private RichDynamicDeclarativeComponent dc6;
    private RichDynamicDeclarativeComponent dc5;
    
    private RichPanelBox pb4;
    private RichDynamicDeclarativeComponent dc7;
    private RichDynamicDeclarativeComponent dc8;
    
    private RichPanelBox pb5;
    private RichDynamicDeclarativeComponent dc9;
    private RichDynamicDeclarativeComponent dc10;
    
    private RichPanelBox panelBoxCaptaciones;
    private RichDynamicDeclarativeComponent dc13;
    private RichDynamicDeclarativeComponent dc14;
    
    private RichPanelBox enlacesTramites;
    private RichDynamicDeclarativeComponent dc62;
    private RichDynamicDeclarativeComponent dc63;
    
    private RichPanelBox panelBoxVertimientos;
    private RichDynamicDeclarativeComponent dc56;
    private RichDynamicDeclarativeComponent dc57;
    
    private RichPanelBox panelBoxVert;
    private RichDynamicDeclarativeComponent dc58;
    private RichDynamicDeclarativeComponent dc59;
    
    private RichPanelBox boxCaudalesVert;
    private RichDynamicDeclarativeComponent dc60;
    private RichDynamicDeclarativeComponent dc61;
    
    private RichPanelBox usosCaptaciones;
    private RichDynamicDeclarativeComponent dc26;
    private RichDynamicDeclarativeComponent dc27;
    
    private RichPanelBox boxCaudales;
    private RichDynamicDeclarativeComponent dc34;
    private RichDynamicDeclarativeComponent dc35;
    
    /* Se publicará en 2015
    private RichPanelBox boxDemanda;
    private RichDynamicDeclarativeComponent dc66;
    private RichDynamicDeclarativeComponent dc67;
  */
    
    private RichPanelBox boxBusquedas;
    private RichDynamicDeclarativeComponent dc68;
    private RichDynamicDeclarativeComponent dc69;
    
    private RichPanelGroupLayout panelGroupLayout1;
    private RichNavigationPane npItems;
    private RichCommandNavigationItem commandNavigationItem1;
    private RichCommandNavigationItem cni2;
    private RichOutputText outputTextNota;
    private RichSpacer spacer1;

    public ObservatorioDemandaSubtBaking() {      
        FacesUtils.setActiveBean("observatorioDemandaSubtBacking", "ObservatorioDemandaSubtBaking",
                                 ObservatorioDemandaBaking.class);
        FacesUtils.removeFromSession("OrigenNavegacionUsuario");
        FacesUtils.setInSession("OrigenNavegacionUsuario","observatorio");
    }
    
    
    public void minimize(ActionEvent e) {
        System.out.println("=======================================Observatorio demanda minimize");
        UIComponent eventComponent = e.getComponent();
        while (eventComponent != null &&
               !(eventComponent instanceof RichPanelBox)) {
            eventComponent = eventComponent.getParent();
            if(eventComponent instanceof RichPanelBox){
                eventComponent.setRendered(false);
            }
        }
        
        this.refresh();
        
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
        Iterator it = listaCategoriasDemanda.iterator();
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
        RequestContext rc = RequestContext.getCurrentInstance();
        rc.addPartialTarget(this.centerSwitcher);
        
    }
    
    
    public List getListaCategoriasDemanda() {
        return super.listaCategoriasDemanda;
    }
 

    private UIXSwitcher _getSwitcher() {
        return this.centerSwitcher;
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
    
    public String getMaximizedPanelUrl() {
        return _maximizedPanelUrl;
    }

    public void setMaximizedPanelUrl(String maximizedPanelUrl) {
        _maximizedPanelUrl = maximizedPanelUrl;
    }
    
    public String getMaximizedKey() {
        return maximizedKey;
    }

    public void setMaximizedKey(String maximizedKey) {
        this.maximizedKey = maximizedKey;
    }
    
    public String getMaximizedTitle() {
        return maximizedTitle;
    }

    public void setMaximizedTitle(String maximizedTitle) {
        this.maximizedTitle = maximizedTitle;
    }

    public void setPanelStretchLayout1(RichPanelStretchLayout panelStretchLayout1) {
        this.panelStretchLayout1 = panelStretchLayout1;
    }

    public RichPanelStretchLayout getPanelStretchLayout1() {
        return panelStretchLayout1;
    }

    public void setCenterSwitcher(UIXSwitcher switcher1) {
        this.centerSwitcher = switcher1;
    }

    public UIXSwitcher getCenterSwitcher() {
        return centerSwitcher;
    }

    public void setPsl2(RichPanelStretchLayout panelStretchLayout2) {
        this.psl2 = panelStretchLayout2;
    }

    public RichPanelStretchLayout getPsl2() {
        return psl2;
    }

    public void setPsl3(RichPanelStretchLayout panelStretchLayout3) {
        this.psl3 = panelStretchLayout3;
    }

    public RichPanelStretchLayout getPsl3() {
        return psl3;
    }

    public void setPb_max(RichPanelBox panelBox1) {
        this.pb_max = panelBox1;
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
    
    public void setDashboard(RichPanelDashboard pd1) {
        this.dashboard = pd1;
    }

    public RichPanelDashboard getDashboard() {
        return dashboard;
    }


    public void setPbnaturaleza(RichPanelBox pbnaturaleza) {
        this.pbnaturaleza = pbnaturaleza;
    }

    public RichPanelBox getPbnaturaleza() {
        return pbnaturaleza;
    }

    public void setDc1(RichDynamicDeclarativeComponent dc1) {
        this.dc1 = dc1;
    }

    public RichDynamicDeclarativeComponent getDc1() {
        return dc1;
    }

    public void setPbPrediosPorDpto(RichPanelBox pbPrediosPorDpto) {
        this.pbPrediosPorDpto = pbPrediosPorDpto;
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

    public void setPbCapFuentes(RichPanelBox pbCapFuentes) {
        this.pbCapFuentes = pbCapFuentes;
    }

    public RichPanelBox getPbCapFuentes() {
        return pbCapFuentes;
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

    public void setPb3(RichPanelBox pb3) {
        this.pb3 = pb3;
    }

    public RichPanelBox getPb3() {
        return pb3;
    }

    public void setDc6(RichDynamicDeclarativeComponent dc6) {
        this.dc6 = dc6;
    }

    public RichDynamicDeclarativeComponent getDc6() {
        return dc6;
    }

    public void setDc5(RichDynamicDeclarativeComponent dc5) {
        this.dc5 = dc5;
    }

    public RichDynamicDeclarativeComponent getDc5() {
        return dc5;
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

    public void setPanelBoxCaptaciones(RichPanelBox panelBoxCaptaciones) {
        this.panelBoxCaptaciones = panelBoxCaptaciones;
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

    public void setEnlacesTramites(RichPanelBox enlacesTramites) {
        this.enlacesTramites = enlacesTramites;
    }

    public RichPanelBox getEnlacesTramites() {
        return enlacesTramites;
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

    public void setBoxCaudalesVert(RichPanelBox boxCaudalesVert) {
        this.boxCaudalesVert = boxCaudalesVert;
    }

    public RichPanelBox getBoxCaudalesVert() {
        return boxCaudalesVert;
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

    public void setUsosCaptaciones(RichPanelBox usosCaptaciones) {
        this.usosCaptaciones = usosCaptaciones;
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

    public void setBoxCaudales(RichPanelBox boxCaudales) {
        this.boxCaudales = boxCaudales;
    }

    public RichPanelBox getBoxCaudales() {
        return boxCaudales;
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

    public void setPanelGroupLayout1(RichPanelGroupLayout panelGroupLayout1) {
        this.panelGroupLayout1 = panelGroupLayout1;
    }

    public RichPanelGroupLayout getPanelGroupLayout1() {
        return panelGroupLayout1;
    }

    public void setNpItems(RichNavigationPane navigationPane1) {
        this.npItems = navigationPane1;
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

    public void setCni2(RichCommandNavigationItem commandNavigationItem2) {
        this.cni2 = commandNavigationItem2;
    }

    public RichCommandNavigationItem getCni2() {
        return cni2;
    }
    /*
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
    }
*/
    public void setBoxBusquedas(RichPanelBox boxBusquedas) {
        this.boxBusquedas = boxBusquedas;
    }

    public RichPanelBox getBoxBusquedas() {
        return boxBusquedas;
    }

    public void setDc68(RichDynamicDeclarativeComponent dc68) {
        this.dc68 = dc68;
    }

    public RichDynamicDeclarativeComponent getDc68() {
        return dc68;
    }

    public void setDc69(RichDynamicDeclarativeComponent dc69) {
        this.dc69 = dc69;
    }

    public RichDynamicDeclarativeComponent getDc69() {
        return dc69;
    }

    public void setOutputTextNota(RichOutputText outputText1) {
        this.outputTextNota = outputText1;
    }

    public RichOutputText getOutputTextNota() {
        return outputTextNota;
    }

    public void setSpacer1(RichSpacer spacer1) {
        this.spacer1 = spacer1;
    }

    public RichSpacer getSpacer1() {
        return spacer1;
    }
}
