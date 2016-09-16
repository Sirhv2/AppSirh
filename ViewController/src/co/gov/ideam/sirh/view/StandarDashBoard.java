package co.gov.ideam.sirh.view;

import co.gov.ideam.sirh.dashboard.view.Category;
import co.gov.ideam.sirh.dashboard.view.DashBoardCalidadBean;
import co.gov.ideam.sirh.dashboard.view.DashBoardCalidadGrafico2Bean;
import co.gov.ideam.sirh.dashboard.view.DashBoardCalidadGrafico3Bean;
import co.gov.ideam.sirh.dashboard.view.DashBoardCaudalesAutoridadVertBean;
import co.gov.ideam.sirh.dashboard.view.DashBoardCaudalesBean;
import co.gov.ideam.sirh.dashboard.view.DashBoardCaudalesBeanAutoridad;
import co.gov.ideam.sirh.dashboard.view.DashBoardCaudalesVertBean;
import co.gov.ideam.sirh.dashboard.view.DashBoardConcesionesAnioBean;
import co.gov.ideam.sirh.dashboard.view.DashBoardPermisosAnioBean;
import co.gov.ideam.sirh.dashboard.view.DashBoardPomcaGrafico1Bean;
import co.gov.ideam.sirh.dashboard.view.DashBoardPomcaGrafico3Bean;
import co.gov.ideam.sirh.dashboard.view.GraficoNormaLimitesBean;
import co.gov.ideam.sirh.dashboard.view.PorletDashBoard;
import co.gov.ideam.sirh.dashboard.view.PorletList;
import co.gov.ideam.sirh.observatorio.view.ObservatorioBacking;

import co.gov.ideam.sirh.observatorio.view.ObservatorioCalidadBacking;

import co.gov.ideam.sirh.observatorio.view.ObservatorioDemandaBaking;

import co.gov.ideam.sirh.observatorio.view.ObservatorioGestionBacking;

import co.gov.ideam.sirh.observatorio.view.ObservatorioRiesgoBacking;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.fragment.RichDynamicDeclarativeComponent;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.nav.RichCommandNavigationItem;
import oracle.adf.view.rich.context.AdfFacesContext;

import org.apache.myfaces.trinidad.context.RequestContext;

public class StandarDashBoard extends StandarBean{
    
    private static List listaPorlets;
    private static List listaCategorias;
    
    protected static List listaCategoriasDemanda;
    protected static List listaCategoriasCalidad;
    protected static List listaCategoriasGestion;
    protected static List listaCategoriasRiesgo;
    

    public StandarDashBoard() {    
        this.listaPorlets = new ArrayList<PorletDashBoard>();
        
        // Cargar los porlet de la lista estatica a la lista del Bean
        
        if (PorletList.getListaCategorias() != null ) {
            
            Iterator it = PorletList.getListaPorlets().iterator();
            while (it.hasNext()) {
                PorletDashBoard porlet = (PorletDashBoard)it.next();
                PorletDashBoard nuevaInstancia = new PorletDashBoard(porlet);
                this.listaPorlets.add(nuevaInstancia);
                
            }
        }
        
        this.listaCategorias = PorletList.getListaCategorias();
        
        this.listaCategoriasDemanda = PorletList.getListaCategoriasDemanda();
        this.listaCategoriasCalidad = PorletList.getListaCategoriasCalidad();
        this.listaCategoriasGestion = PorletList.getListaCategoriasGestion();
        this.listaCategoriasRiesgo = PorletList.getListaCategoriasRiesgo();
        
    }


    public void maximize(ActionEvent e) {
        Object bean = FacesUtils.getActiveBean();
        if(bean instanceof DashBoardBean){
            ((co.gov.ideam.sirh.dashboard.view.DashBoardBean) bean).maximize(e);
        }else if (bean instanceof ObservatorioBacking){
            ((ObservatorioBacking) bean).maximize(e);
        }else if (bean instanceof ObservatorioCalidadBacking){
            ((ObservatorioCalidadBacking) bean).maximize(e);
        }else if (bean instanceof ObservatorioDemandaBaking){
            ((ObservatorioDemandaBaking) bean).maximize(e);
        }else if (bean instanceof ObservatorioGestionBacking){
            ((ObservatorioGestionBacking) bean).maximize(e);
        }else if (bean instanceof ObservatorioRiesgoBacking){
            ((ObservatorioRiesgoBacking) bean).maximize(e);
        }else if (bean instanceof DashBoardCalidadBean){
            ((DashBoardCalidadBean) bean).maximize(e);
        }else if (bean instanceof DashBoardCalidadGrafico2Bean){
            ((DashBoardCalidadGrafico2Bean) bean).maximize(e);
        }else if (bean instanceof DashBoardCalidadGrafico3Bean){
            ((DashBoardCalidadGrafico3Bean) bean).maximize(e);
        }else if (bean instanceof DashBoardCaudalesAutoridadVertBean){
            ((DashBoardCaudalesAutoridadVertBean) bean).maximize(e);
        }else if (bean instanceof DashBoardCaudalesBean){
            ((DashBoardCaudalesBean) bean).maximize(e);
        }else if (bean instanceof DashBoardCaudalesBeanAutoridad){
            ((DashBoardCaudalesBeanAutoridad) bean).maximize(e);
        }else if (bean instanceof DashBoardCaudalesVertBean){
            ((DashBoardCaudalesVertBean) bean).maximize(e);
        }else if (bean instanceof DashBoardConcesionesAnioBean){
            ((DashBoardConcesionesAnioBean) bean).maximize(e);
        }else if (bean instanceof DashBoardPermisosAnioBean){
            ((DashBoardPermisosAnioBean) bean).maximize(e);
        }else if (bean instanceof DashBoardPomcaGrafico1Bean){
            ((DashBoardPomcaGrafico1Bean) bean).maximize(e);
        }else if (bean instanceof DashBoardPomcaGrafico3Bean){
            ((DashBoardPomcaGrafico3Bean) bean).maximize(e);
        }else if (bean instanceof GraficoNormaLimitesBean){
            ((GraficoNormaLimitesBean) bean).maximize(e);
        }
        
    }
    
    public void minimize(ActionEvent e) {
        Object bean = FacesUtils.getActiveBean();
        if(bean instanceof DashBoardBean){
            ((co.gov.ideam.sirh.dashboard.view.DashBoardBean) bean).minimize(e);
        }else if (bean instanceof ObservatorioBacking){
            ((ObservatorioBacking) bean).minimize(e);
        }else if (bean instanceof ObservatorioCalidadBacking){
            ((ObservatorioCalidadBacking) bean).minimize(e);
        }else if (bean instanceof ObservatorioDemandaBaking){            
            ((ObservatorioDemandaBaking) bean).minimize(e);
        }else if (bean instanceof ObservatorioGestionBacking){
            ((ObservatorioGestionBacking) bean).minimize(e);
        }else if (bean instanceof ObservatorioRiesgoBacking){
            ((ObservatorioRiesgoBacking) bean).minimize(e);
        }else if (bean instanceof DashBoardCalidadBean){
            ((DashBoardCalidadBean) bean).minimize(e);
        }else if (bean instanceof DashBoardCalidadGrafico2Bean){
            ((DashBoardCalidadGrafico2Bean) bean).minimize(e);
        }else if (bean instanceof DashBoardCalidadGrafico3Bean){
            ((DashBoardCalidadGrafico3Bean) bean).minimize(e);
        }else if (bean instanceof DashBoardCaudalesAutoridadVertBean){
            ((DashBoardCaudalesAutoridadVertBean) bean).minimize(e);
        }else if (bean instanceof DashBoardCaudalesBean){
            ((DashBoardCaudalesBean) bean).minimize(e);
        }else if (bean instanceof DashBoardCaudalesBeanAutoridad){
            ((DashBoardCaudalesBeanAutoridad) bean).minimize(e);
        }else if (bean instanceof DashBoardCaudalesVertBean){
            ((DashBoardCaudalesVertBean) bean).minimize(e);
        }else if (bean instanceof DashBoardConcesionesAnioBean){
            ((DashBoardConcesionesAnioBean) bean).minimize(e);
        }else if (bean instanceof DashBoardPermisosAnioBean){
            ((DashBoardPermisosAnioBean) bean).minimize(e);
        }else if (bean instanceof DashBoardPomcaGrafico1Bean){
            ((DashBoardPomcaGrafico1Bean) bean).minimize(e);
        }else if (bean instanceof DashBoardPomcaGrafico3Bean){
            ((DashBoardPomcaGrafico3Bean) bean).minimize(e);
        }else if (bean instanceof GraficoNormaLimitesBean){
            ((GraficoNormaLimitesBean) bean).minimize(e);
        }
    }
    
    public void restore(ActionEvent e) {
        Object bean = FacesUtils.getActiveBean();
        if(bean instanceof DashBoardBean){
            ((co.gov.ideam.sirh.dashboard.view.DashBoardBean) bean).restore(e);
        }else if (bean instanceof ObservatorioBacking){
            ((ObservatorioBacking) bean).restore(e);
        }else if (bean instanceof ObservatorioCalidadBacking){
            ((ObservatorioCalidadBacking) bean).restore(e);
        }else if (bean instanceof ObservatorioDemandaBaking){            
            ((ObservatorioDemandaBaking) bean).restore(e);
        }else if (bean instanceof ObservatorioGestionBacking){
            ((ObservatorioGestionBacking) bean).restore(e);
        }else if (bean instanceof ObservatorioRiesgoBacking){
            ((ObservatorioRiesgoBacking) bean).restore(e);
        }else if (bean instanceof DashBoardCalidadBean){
            ((DashBoardCalidadBean) bean).restore(e);
        }else if (bean instanceof DashBoardCalidadGrafico2Bean){
            ((DashBoardCalidadGrafico2Bean) bean).restore(e);
        }else if (bean instanceof DashBoardCalidadGrafico3Bean){
            ((DashBoardCalidadGrafico3Bean) bean).restore(e);
        }else if (bean instanceof DashBoardCaudalesAutoridadVertBean){
            ((DashBoardCaudalesAutoridadVertBean) bean).restore(e);
        }else if (bean instanceof DashBoardCaudalesBean){
            ((DashBoardCaudalesBean) bean).restore(e);
        }else if (bean instanceof DashBoardCaudalesBeanAutoridad){
            ((DashBoardCaudalesBeanAutoridad) bean).restore(e);
        }else if (bean instanceof DashBoardCaudalesVertBean){
            ((DashBoardCaudalesVertBean) bean).restore(e);
        }else if (bean instanceof DashBoardConcesionesAnioBean){
            ((DashBoardConcesionesAnioBean) bean).restore(e);
        }else if (bean instanceof DashBoardPermisosAnioBean){
            ((DashBoardPermisosAnioBean) bean).restore(e);
        }else if (bean instanceof DashBoardPomcaGrafico1Bean){
            ((DashBoardPomcaGrafico1Bean) bean).restore(e);
        }else if (bean instanceof DashBoardPomcaGrafico3Bean){
            ((DashBoardPomcaGrafico3Bean) bean).restore(e);
        }else if (bean instanceof GraficoNormaLimitesBean){
            ((GraficoNormaLimitesBean) bean).restore(e);
        }
    }
    
    protected String getUrlFromPanelBox(RichPanelBox panel) {
        
        // Encontrar la url del panelBox para ubicar el Porlet de la lista
        List lista = panel.getChildren();
        Iterator it = lista.iterator();
        while (it.hasNext()) {
            Object comp = it.next();
            if (comp != null &&
                comp instanceof RichDynamicDeclarativeComponent) {
                RichDynamicDeclarativeComponent declarative =
                    (RichDynamicDeclarativeComponent)comp;
                String url = declarative.getViewId();
                return url;
            }
        }
        return null;
    }
    
    protected String getPortletId(RichPanelBox panel) {
        
        // Encontrar la url del panelBox para ubicar el Porlet de la lista
        List lista = panel.getChildren();
        Iterator it = lista.iterator();
        while (it.hasNext()) {
            Object comp = it.next();
            if (comp != null &&
                comp instanceof RichDynamicDeclarativeComponent) {
                RichDynamicDeclarativeComponent declarative =
                    (RichDynamicDeclarativeComponent)comp;
                Object obj = declarative.getAttributes().get("PortletId");
                return (String)obj;
            }
        }
        return null;
    }
    
    
    
    protected PorletDashBoard getPorletFromUrl(String url) {
        
        PorletDashBoard pBuscar = new PorletDashBoard();
        pBuscar.setUrl(url);
        List porlets = PorletList.getListaPorlets();
        int pos = ((porlets != null) ? porlets.indexOf(pBuscar) : -1 );
        if (pos > -1) {
            PorletDashBoard pEncontrado =(PorletDashBoard)porlets.get(pos);
            return pEncontrado;
        }
        return null;
    }
    
    

    public List getListaPorlets() {
        
        return PorletList.getListaPorlets();
    }

    public List getListaCategorias() {
        return this.listaCategorias;
    }

}
