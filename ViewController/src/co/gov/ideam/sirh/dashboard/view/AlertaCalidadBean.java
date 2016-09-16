package co.gov.ideam.sirh.dashboard.view;

import co.gov.ideam.sirh.calidad.model.DatosGrafico;
import co.gov.ideam.sirh.calidad.model.PuntoMonitoreo;
import co.gov.ideam.sirh.calidad.view.CalidadDelegate;
import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.fuentes.view.FuenteDelegate;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.model.PartZonificListas;

import co.gov.ideam.sirh.parametros.view.ParametrosDelegate;
import co.gov.ideam.sirh.pomca.view.PomcaDelegate;
import co.gov.ideam.sirh.seguridad.model.PerfilVO;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.seguridad.view.SeguridadDelegate;
import co.gov.ideam.sirh.usuarios.agua.view.UsuariosAguaDelegate;
import co.gov.ideam.sirh.util.ConstantesCalidad;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;

import co.gov.ideam.sirh.view.StandarBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UISelectItems;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import javax.faces.model.SelectItem;

import oracle.adf.view.faces.bi.component.graph.UIGraph;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.output.RichActiveOutputText;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;

public class AlertaCalidadBean extends StandarBean {

    private List listaFuentesIca;
    
    private FnttFuente fuenteActual = null;
    private PuntoMonitoreo puntoActual;
    

    private List listaAlertas;
    private List listaAlertasDet;
    private List<Object[]> listObjectGrafico;


    private RichForm form1;
    private RichDocument document1;
    private RichPanelStretchLayout panelStretchLayout1;
    private RichPanelSplitter panelSplitter1;
    private RichPanelGroupLayout panelGroupLayout2;
    private RichPanelFormLayout panelFormLayout2;
    private RichPanelFormLayout panelFormGrafico;
    
    private RichSpacer spacer4;
    private RichCommandLink clink_inicio;
    private RichSpacer spacer5;
    private RichCommandLink clink_grafico1;
    private RichSpacer spacer6;
    private RichPanelGroupLayout panelGroupLayout3;
    private RichPanelBox panelBoxCalidadGraf1;
    private RichPanelSplitter panelSplitter2;
    private RichPanelGroupLayout panelGroupLayout4;
    private RichPanelFormLayout panelFormLayout12;
    private RichPanelGroupLayout panelGroupLayout1;


    private RichActiveOutputText aot_pto;
    private RichActiveOutputText datos;
    private RichActiveOutputText aot_parametro;
    private RichActiveOutputText datos1;
    private RichActiveOutputText aot_area;
    private RichTable tableAl;
    private RichTable tableDe;
    private RichActiveOutputText aot_nomFuente;

    private RichActiveOutputText aot_oferta;
    private RichActiveOutputText activeOutputText1;
    private UIGraph grafico;
    private RichActiveOutputText activeOutputText2;
    private RichActiveOutputText aot_nomFuenteGrafico;
    private RichActiveOutputText aot_adv1;
    private RichPanelStretchLayout panelStretchLayout2;
    private RichPanelGroupLayout panelGroupLayout8;
    private RichPanelGroupLayout panelGroupLayout9;
    private RichPanelGroupLayout panelGroupLayout10;
    private RichCommandLink commandLink1;
    private RichSpacer spacer1;
    private RichCommandLink commandLink2;
    private RichSpacer spacer2;
    private RichCommandLink commandLink3;
    private RichSpacer spacer3;
    private RichCommandLink commandLink4;
    private RichSelectOneChoice selectOneChoice1;
    private UISelectItems selectFuentes;

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

    public void setPanelSplitter1(RichPanelSplitter panelSplitter1) {
        this.panelSplitter1 = panelSplitter1;
    }

    public RichPanelSplitter getPanelSplitter1() {
        return panelSplitter1;
    }

    public void setPanelGroupLayout2(RichPanelGroupLayout panelGroupLayout2) {
        this.panelGroupLayout2 = panelGroupLayout2;
    }

    public RichPanelGroupLayout getPanelGroupLayout2() {
        return panelGroupLayout2;
    }

    public void setPanelFormLayout2(RichPanelFormLayout panelFormLayout2) {
        this.panelFormLayout2 = panelFormLayout2;
    }

    public RichPanelFormLayout getPanelFormLayout2() {
        return panelFormLayout2;
    }

    public void setSpacer4(RichSpacer spacer4) {
        this.spacer4 = spacer4;
    }

    public RichSpacer getSpacer4() {
        return spacer4;
    }

    public void setClink_inicio(RichCommandLink clink_inicio) {
        this.clink_inicio = clink_inicio;
    }

    public RichCommandLink getClink_inicio() {
        return clink_inicio;
    }

    public void setSpacer5(RichSpacer spacer5) {
        this.spacer5 = spacer5;
    }

    public RichSpacer getSpacer5() {
        return spacer5;
    }

    public void setClink_grafico1(RichCommandLink clink_grafico1) {
        this.clink_grafico1 = clink_grafico1;
    }

    public RichCommandLink getClink_grafico1() {
        return clink_grafico1;
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

    public void setPanelBoxCalidadGraf1(RichPanelBox panelBoxCalidadGraf1) {
        this.panelBoxCalidadGraf1 = panelBoxCalidadGraf1;
    }

    public RichPanelBox getPanelBoxCalidadGraf1() {
        return panelBoxCalidadGraf1;
    }

    public void setPanelSplitter2(RichPanelSplitter panelSplitter2) {
        this.panelSplitter2 = panelSplitter2;
    }

    public RichPanelSplitter getPanelSplitter2() {
        return panelSplitter2;
    }

    public void setPanelGroupLayout4(RichPanelGroupLayout panelGroupLayout4) {
        this.panelGroupLayout4 = panelGroupLayout4;
    }

    public RichPanelGroupLayout getPanelGroupLayout4() {
        return panelGroupLayout4;
    }

    public void setPanelFormLayout12(RichPanelFormLayout panelFormLayout12) {
        this.panelFormLayout12 = panelFormLayout12;
    }

    public RichPanelFormLayout getPanelFormLayout12() {
        return panelFormLayout12;
    }


    public void setPanelGroupLayout1(RichPanelGroupLayout panelGroupLayout1) {
        this.panelGroupLayout1 = panelGroupLayout1;
    }

    public RichPanelGroupLayout getPanelGroupLayout1() {
        return panelGroupLayout1;
    }



    public void setListaAlertas(List listaAlertas) {
        this.listaAlertas = listaAlertas;
    }

    public List getListaAlertas() {
        return listaAlertas;
    }


    public void setListaAlertasDet(List listaAlertasDet) {
        this.listaAlertasDet = listaAlertasDet;
    }

    public List getListaAlertasDet() {
        return listaAlertasDet;
    }

    public void setAot_pto(RichActiveOutputText aot_pto) {
        this.aot_pto = aot_pto;
    }

    public RichActiveOutputText getAot_pto() {
        return aot_pto;
    }

    public void setDatos(RichActiveOutputText datos) {
        this.datos = datos;
    }

    public RichActiveOutputText getDatos() {
        return datos;
    }

    public void setAot_parametro(RichActiveOutputText aot_parametro) {
        this.aot_parametro = aot_parametro;
    }

    public RichActiveOutputText getAot_parametro() {
        return aot_parametro;
    }


    public void setDatos1(RichActiveOutputText datos1) {
        this.datos1 = datos1;
    }

    public RichActiveOutputText getDatos1() {
        return datos1;
    }

    public void setAot_area(RichActiveOutputText aot_pto1) {
        this.aot_area = aot_pto1;
    }

    public RichActiveOutputText getAot_area() {
        return aot_area;
    }


    public void setPanelFormGrafico(RichPanelFormLayout panelFormGrafico) {
        this.panelFormGrafico = panelFormGrafico;
    }

    public RichPanelFormLayout getPanelFormGrafico() {
        return panelFormGrafico;
    }

    public void setTableAl(RichTable tableAl) {
        this.tableAl = tableAl;
    }

    public RichTable getTableAl() {
        return tableAl;
    }

    public void setTableDe(RichTable tableDe) {
        this.tableDe = tableDe;
    }

    public RichTable getTableDe() {
        return tableDe;
    }

    public void setAot_nomFuente(RichActiveOutputText activeOutputText2) {
        this.aot_nomFuente = activeOutputText2;
    }

    public RichActiveOutputText getAot_nomFuente() {
        return aot_nomFuente;
    }

    public void setActiveOutputText1(RichActiveOutputText activeOutputText1) {
        this.activeOutputText1 = activeOutputText1;
    }

    public RichActiveOutputText getActiveOutputText1() {
        return activeOutputText1;
    }

    public void setAot_oferta(RichActiveOutputText aot_oferta) {
        this.aot_oferta = aot_oferta;
    }

    public RichActiveOutputText getAot_oferta() {
        return aot_oferta;
    }


    public void setGrafico(UIGraph lineGraph1) {
        this.grafico = lineGraph1;
    }

    public UIGraph getGrafico() {
        return grafico;
    }

    public void setActiveOutputText2(RichActiveOutputText activeOutputText2) {
        this.activeOutputText2 = activeOutputText2;
    }

    public RichActiveOutputText getActiveOutputText2() {
        return activeOutputText2;
    }

    public void setAot_nomFuenteGrafico(RichActiveOutputText activeOutputText3) {
        this.aot_nomFuenteGrafico = activeOutputText3;
    }

    public RichActiveOutputText getAot_nomFuenteGrafico() {
        return aot_nomFuenteGrafico;
    }

    public void setListObjectGrafico(List<Object[]> listObjectGrafico) {
        this.listObjectGrafico = listObjectGrafico;
    }

    public List<Object[]> getListObjectGrafico() {
        return listObjectGrafico;
    }

    public AlertaCalidadBean() {
        
        FacesUtils.setActiveBean("AlertaCalidadBean","AlertaCalidadBean", AlertaCalidadBean.class);
        FacesUtils.removeManagedBeanFromSession("AlertContBean");
        FacesUtils.removeManagedBeanFromSession("AlertDispBean");
        
        // Si ya se cargo la pagina no ejecutar nuevamente
        if (listaFuentesIca != null)
            return;
        
        this.load();
    }

    public void load() {

        try {
            listaFuentesIca = this.getListaFuentesMuestras(null);
            listObjectGrafico = new ArrayList<Object[]>();

        } catch (IdeamException e) {
            showMessage(e);
        }

    }
    
 
    public void sc_fuenteIca_valueChangeListener(ValueChangeEvent event) {
        try {

            Object fuente = event.getNewValue();

            if (fuente != null) {
                fuenteActual = (FnttFuente)fuente;
                graficarTablaFuente(fuenteActual.getId());
                
            } else {
                fuenteActual = null;
            }


        } catch (Exception e) {
            showMessage(e.getMessage());
        }

    }

    public void graficarTablaFuente(Long idFuente) throws IdeamException {


        this.aot_area.setValue(fuenteActual.getNombre());

        this.panelFormGrafico.setVisible(true);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelFormGrafico);
        
        this.tableAl.setVisible(true);
        AdfFacesContext.getCurrentInstance().addPartialTarget(tableAl);

        FuenteDelegate fd = FuenteDelegate.getInstance();


        listaAlertas =
                 fd.getListaAlertasIcaFuente(idFuente);
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.tableAl);
        
        tableDe.setVisible(false);
        AdfFacesContext.getCurrentInstance().addPartialTarget(tableDe);
        
        listObjectGrafico = new ArrayList<Object[]>();
        AdfFacesContext.getCurrentInstance().addPartialTarget(grafico);
        
        
    }



    public void detalle_actionListener(ActionEvent actionEvent) {
        try {
            
            Long idMuestra =
                (Long)actionEvent.getComponent().getAttributes().get("idMuestra");

            String nomFuente =
                (String)actionEvent.getComponent().getAttributes().get("nomFuente");

            String nomTramo =
                (String)actionEvent.getComponent().getAttributes().get("nomTramo");

            String nomPunto =
                (String)actionEvent.getComponent().getAttributes().get("nomPunto");

            aot_nomFuenteGrafico.setValue(nomTramo + " - " + nomPunto);
            aot_nomFuente.setValue(nomFuente + " - " + nomTramo);
            
            graficarGrafica1(idMuestra);
            graficarTabla2(idMuestra);

        } catch (IdeamException e) {
            showMessage(e);
        }

    }

    public void graficarGrafica1(Long idMuestra) throws IdeamException {
        
        listObjectGrafico = new ArrayList<Object[]>();
        
        FuenteDelegate fd = FuenteDelegate.getInstance();

        List<Object[]> lDatosGrafico =fd.getListaMuestrasIca(idMuestra);
        
        if (lDatosGrafico == null)
            return;
        
        if (lDatosGrafico.size() < 2 )
            return;

       
        for (int j = 0; j < lDatosGrafico.size(); j++) {
            Object[] dat = lDatosGrafico.get(j);

            String fechaMuestra = (String)dat[1];
            Double ica = new Double(dat[2].toString());
            
            Object[] punto = { fechaMuestra, "Nivel ICA" , ica };
            listObjectGrafico.add(punto);

        }

    }
    
    public void graficarTabla2(Long idMuestra) throws IdeamException {
        
        
        listaAlertasDet = new ArrayList();

        this.tableDe.setVisible(true);
        AdfFacesContext.getCurrentInstance().addPartialTarget(tableDe);

        FuenteDelegate fd = FuenteDelegate.getInstance();

        this.listaAlertasDet = fd.getListaMuestrasIcaDet(idMuestra);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.tableDe);

    }

    public void setAot_adv1(RichActiveOutputText activeOutputText3) {
        this.aot_adv1 = activeOutputText3;
    }

    public RichActiveOutputText getAot_adv1() {
        return aot_adv1;
    }


    public void setPanelStretchLayout2(RichPanelStretchLayout panelStretchLayout2) {
        this.panelStretchLayout2 = panelStretchLayout2;
    }

    public RichPanelStretchLayout getPanelStretchLayout2() {
        return panelStretchLayout2;
    }

    public void setPanelGroupLayout8(RichPanelGroupLayout panelGroupLayout8) {
        this.panelGroupLayout8 = panelGroupLayout8;
    }

    public RichPanelGroupLayout getPanelGroupLayout8() {
        return panelGroupLayout8;
    }

    public void setPanelGroupLayout9(RichPanelGroupLayout panelGroupLayout9) {
        this.panelGroupLayout9 = panelGroupLayout9;
    }

    public RichPanelGroupLayout getPanelGroupLayout9() {
        return panelGroupLayout9;
    }

    public void setPanelGroupLayout10(RichPanelGroupLayout panelGroupLayout10) {
        this.panelGroupLayout10 = panelGroupLayout10;
    }

    public RichPanelGroupLayout getPanelGroupLayout10() {
        return panelGroupLayout10;
    }

    public void setCommandLink1(RichCommandLink commandLink1) {
        this.commandLink1 = commandLink1;
    }

    public RichCommandLink getCommandLink1() {
        return commandLink1;
    }

    public void setSpacer1(RichSpacer spacer1) {
        this.spacer1 = spacer1;
    }

    public RichSpacer getSpacer1() {
        return spacer1;
    }

    public void setCommandLink2(RichCommandLink commandLink2) {
        this.commandLink2 = commandLink2;
    }

    public RichCommandLink getCommandLink2() {
        return commandLink2;
    }

    public void setFuenteActual(FnttFuente fuenteActual) {
        this.fuenteActual = fuenteActual;
    }

    public FnttFuente getFuenteActual() {
        return fuenteActual;
    }


    public void setSpacer2(RichSpacer spacer2) {
        this.spacer2 = spacer2;
    }

    public RichSpacer getSpacer2() {
        return spacer2;
    }

    public void setCommandLink3(RichCommandLink commandLink3) {
        this.commandLink3 = commandLink3;
    }

    public RichCommandLink getCommandLink3() {
        return commandLink3;
    }

    public void setSpacer3(RichSpacer spacer3) {
        this.spacer3 = spacer3;
    }

    public RichSpacer getSpacer3() {
        return spacer3;
    }

    public void setCommandLink4(RichCommandLink commandLink4) {
        this.commandLink4 = commandLink4;
    }

    public RichCommandLink getCommandLink4() {
        return commandLink4;
    }


    public void setSelectOneChoice1(RichSelectOneChoice selectOneChoice1) {
        this.selectOneChoice1 = selectOneChoice1;
    }

    public RichSelectOneChoice getSelectOneChoice1() {
        return selectOneChoice1;
    }

    public void setSelectFuentes(UISelectItems selectFuentes) {
        this.selectFuentes = selectFuentes;
    }

    public UISelectItems getSelectFuentes() {
        return selectFuentes;
    }

    public void setListaFuentesIca(List listaFuentesIca) {
        this.listaFuentesIca = listaFuentesIca;
    }

    public List getListaFuentesIca() {
        return listaFuentesIca;
    }


}
