package co.gov.ideam.sirh.pomca.view;


import co.gov.ideam.sirh.fuentes.model.FnttFuente;

import co.gov.ideam.sirh.fuentes.view.FuenteDelegate;
import co.gov.ideam.sirh.fuentes.view.FuentesBean;
import co.gov.ideam.sirh.pomca.model.Cuenca;

import co.gov.ideam.sirh.pomca.model.PomtDetalleCuenca;
import co.gov.ideam.sirh.porh.model.PorhPlanes;
import co.gov.ideam.sirh.porh.view.PorhDelegate;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.util.Constantes;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;

import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichMenu;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandMenuItem;
import oracle.adf.view.rich.component.rich.output.RichActiveOutputText;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
import oracle.adf.view.rich.component.rich.output.RichSpacer;

import org.apache.myfaces.trinidad.event.SelectionEvent;

public class CuencasBean extends StandarBean{
    private RichForm form1;
    private RichDocument document1;
    private RichPanelStretchLayout pslf1;
    private RichPanelSplitter psf1;
    private RichPanelCollection panelCollection1;
    private RichMenu menu1;
    private RichCommandMenuItem cm_pomca_pomt_plan;
    private RichTable t_cuencas;
    private RichPanelGroupLayout panelGroupLayout2;
    private RichActiveOutputText activeOutputText1;
    private RichSpacer spacer1;

    private List listaCuencas;
    private Cuenca cuencaSeleccionada;
    private RichPanelGroupLayout panelGroupLayout1;
    private RichMenu menu11;
    private RichCommandMenuItem cm_pomca_pomt_plan1;
    private RichTable t_cuencas1;
    private RichPanelBox panelBox1;
    private RichActiveOutputText activeOutputText2;
    private RichSpacer spacer2;
    private RichSpacer spacer3;
    private RichActiveOutputText activeOutputText3;
    private List listaCuencasAreas;
    private RichPanelCollection panelCollection2;
    private RichTable t_cuencasTodas;
    private RichCommandMenuItem cmiConsultar;


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


    public void setCm_pomca_pomt_plan(RichCommandMenuItem cm_pomca_pomt_plan) {
        this.cm_pomca_pomt_plan = cm_pomca_pomt_plan;
    }

    public RichCommandMenuItem getCm_pomca_pomt_plan() {
        return cm_pomca_pomt_plan;
    }

    public void setT_cuencas(RichTable t_fuentes) {
        this.t_cuencas = t_fuentes;
    }

    public RichTable getT_cuencas() {
        return t_cuencas;
    }

    public void setPanelGroupLayout2(RichPanelGroupLayout panelGroupLayout2) {
        this.panelGroupLayout2 = panelGroupLayout2;
    }

    public RichPanelGroupLayout getPanelGroupLayout2() {
        return panelGroupLayout2;
    }

    public void setActiveOutputText1(RichActiveOutputText activeOutputText1) {
        this.activeOutputText1 = activeOutputText1;
    }

    public RichActiveOutputText getActiveOutputText1() {
        return activeOutputText1;
    }

    public void setSpacer1(RichSpacer spacer1) {
        this.spacer1 = spacer1;
    }

    public RichSpacer getSpacer1() {
        return spacer1;
    }

    public String cm_pomca_pomt_plan_action() {
        // Add event code here...

        if (cuencaSeleccionada == null) {
            showMessage(getText("seleccionar_registro"),
                        FacesMessage.SEVERITY_ERROR);
            return null;
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>> cm_pomca_pomt_plan_action >>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<<" );
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>> cuenca:"+cuencaSeleccionada.getId() );
 

        return "establecerPomca";
    }

    public void setListaCuencas(List listaCuencas) {
        this.listaCuencas = listaCuencas;
    }

    public List getListaCuencas() {
        return listaCuencas;
    }

    public void setCuencaSeleccionada(Cuenca cuencaSeleccionada) {
        this.cuencaSeleccionada = cuencaSeleccionada;
    }

    public void t_cuencas_selectionListener(SelectionEvent selectionEvent) {
        // Add event code here...
        RichTable t_cuencas = (RichTable)selectionEvent.getSource();
        this.cuencaSeleccionada = (Cuenca)t_cuencas.getSelectedRowData();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>> t_cuencas_selectionListener  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<<" );
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>> cuenca:"+cuencaSeleccionada.getId() );
             
        FacesUtils.removeFromSession("cuencaSeleccionada");
        FacesUtils.removeFromSession("plan");
        FacesUtils.setInSession("cuencaSeleccionada", this.cuencaSeleccionada);

    }
    
    public CuencasBean() {
        FacesUtils.setActiveBean("CuencasBean", "Cuencas",
                                 PomcaDelegate.class);
        
        FacesUtils.removeManagedBeanFromSession("planesBean");
        FacesUtils.removeFromSession("cuencaSeleccionada");
        this.load();
    }

    public void load() {
        try {
            FacesUtils.removeManagedBeanFromSession("AdicionarPomcaBean");
           
            UsuarioConectadoTO usuarioConectado =
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

            PomcaDelegate pd = PomcaDelegate.getInstance();
            FuenteDelegate fd = FuenteDelegate.getInstance();//EDUIN ORTIZ
            
            //si es usuario del ideam
            if (usuarioConectado.getUsuario().getAutoridadAmbiental() ==
                null ||
                usuarioConectado.getUsuario().getAutoridadAmbiental().getId() ==
                null ||
                usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue() ==
                0 ||
                usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue() ==
                Constantes.IDEAM) {
                listaCuencas = pd.getAllCuencas();
                listaCuencasAreas= pd.getAllCuencasAreas();
            } else { //es un usuario de una car
                listaCuencas =
                        pd.getAllCuencas(usuarioConectado.getUsuario().getAutoridadAmbiental());
                listaCuencasAreas= pd.getAllCuencasAreas(usuarioConectado.getUsuario().getAutoridadAmbiental());
            }
            
            //EDUIN ORTIZ, Completar el objeto cuenca con el detalle de la cuenca.
            if(listaCuencasAreas!=null && !listaCuencasAreas.isEmpty()){
                for(Cuenca cuenca : (List<Cuenca>)listaCuencasAreas){
                    PomtDetalleCuenca detalle = pd.getDetalleCuenca(cuenca.getId());
                    FnttFuente fuente = new FnttFuente();
                    if(detalle == null){
                        detalle = new PomtDetalleCuenca();
                    }else if(detalle.getFuentePrincipal()!=null && detalle.getFuentePrincipal()>0){
                        fuente = fd.getFuente(detalle.getFuentePrincipal());
                    }
                    detalle.setFuente(fuente);
                    cuenca.setDetalleCuenca(detalle);
                }
            }
            ///FIN EO
            
        } catch (IdeamException e) {
            showMessage(e);
        }
    }

    public void setPanelGroupLayout1(RichPanelGroupLayout panelGroupLayout1) {
        this.panelGroupLayout1 = panelGroupLayout1;
    }

    public RichPanelGroupLayout getPanelGroupLayout1() {
        return panelGroupLayout1;
    }

    public void setMenu11(RichMenu menu11) {
        this.menu11 = menu11;
    }

    public RichMenu getMenu11() {
        return menu11;
    }

    public void setCm_pomca_pomt_plan1(RichCommandMenuItem cm_pomca_pomt_plan1) {
        this.cm_pomca_pomt_plan1 = cm_pomca_pomt_plan1;
    }

    public RichCommandMenuItem getCm_pomca_pomt_plan1() {
        return cm_pomca_pomt_plan1;
    }

    public void setT_cuencas1(RichTable t_cuencas1) {
        this.t_cuencas1 = t_cuencas1;
    }

    public RichTable getT_cuencas1() {
        return t_cuencas1;
    }


    public void setPanelBox1(RichPanelBox panelBox1) {
        this.panelBox1 = panelBox1;
    }

    public RichPanelBox getPanelBox1() {
        return panelBox1;
    }

    public void setActiveOutputText2(RichActiveOutputText activeOutputText2) {
        this.activeOutputText2 = activeOutputText2;
    }

    public RichActiveOutputText getActiveOutputText2() {
        return activeOutputText2;
    }

    public void setSpacer2(RichSpacer spacer2) {
        this.spacer2 = spacer2;
    }

    public RichSpacer getSpacer2() {
        return spacer2;
    }

    public void setSpacer3(RichSpacer spacer3) {
        this.spacer3 = spacer3;
    }

    public RichSpacer getSpacer3() {
        return spacer3;
    }

    public void setActiveOutputText3(RichActiveOutputText activeOutputText3) {
        this.activeOutputText3 = activeOutputText3;
    }

    public RichActiveOutputText getActiveOutputText3() {
        return activeOutputText3;
    }

    public Cuenca getCuencaSeleccionada() {
        return cuencaSeleccionada;
    }

    public void setListaCuencasAreas(List listaCuencasAreas) {
        this.listaCuencasAreas = listaCuencasAreas;
    }

    public List getListaCuencasAreas() {
        return listaCuencasAreas;
    }

    public void setPanelCollection2(RichPanelCollection panelCollection2) {
        this.panelCollection2 = panelCollection2;
    }

    public RichPanelCollection getPanelCollection2() {
        return panelCollection2;
    }

    public void setT_cuencasTodas(RichTable table1) {
        this.t_cuencasTodas = table1;
    }

    public RichTable getT_cuencasTodas() {
        return t_cuencasTodas;
    }

    public void setCmiConsultar(RichCommandMenuItem commandMenuItem1) {
        this.cmiConsultar = commandMenuItem1;
    }

    public RichCommandMenuItem getCmiConsultar() {
        return cmiConsultar;
    }

    public String cmiConsultar_action() {
        if (cuencaSeleccionada == null) {
            showMessage(getText("seleccionar_registro"),
                        FacesMessage.SEVERITY_ERROR);
            return null;
        }
        
        return "detallarCuenca";
    }
}
