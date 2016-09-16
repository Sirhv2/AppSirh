package co.gov.ideam.sirh.dashboard.view;

import co.gov.ideam.sirh.calidad.model.DatosGrafico;
import co.gov.ideam.sirh.calidad.view.CalidadDelegate;
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

public class AlertDispBean extends StandarBean {

    private List listaAreas;
    private List listaZonas;
    private List listaSubzonas;

    private PartZonificListas areaActual = null;
    private PartZonificListas zonaActual = null;
    private PartZonificListas subzonaActual = null;

    private List listaAlertas;
    private List listaAlertasDet;
    private List<Object[]> listObjectGrafico;

    private boolean grafica1visible=false;
    private boolean tabla1visible=false;

    private RichForm form1;
    private RichDocument document1;
    private RichPanelStretchLayout panelStretchLayout1;
    private RichPanelSplitter panelSplitter1;
    private RichPanelGroupLayout panelGroupLayout2;
    private RichPanelFormLayout panelFormLayout2;
    private RichPanelFormLayout panelFormGrafico;

    private RichSpacer spacer5;
    private RichCommandLink clink_grafico1;
    private RichSpacer spacer6;
    private RichPanelGroupLayout panelGroupLayout3;
    private RichPanelBox panelBoxCalidadGraf1;
    private RichPanelSplitter panelSplitter2;
    private RichPanelGroupLayout panelGroupLayout4;
    private RichPanelFormLayout panelFormLayout12;
    private RichSelectOneChoice sc_areas;
    private UISelectItems selectItems11;
    private RichPanelGroupLayout panelGroupLayout1;
    private RichSelectOneChoice sc_zonas;
    private UISelectItems selectItems2;
    private RichSelectOneChoice sc_subzonas;
    private UISelectItems selectItems1;
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
    private RichCommandLink commandLink1;
    private RichSpacer spacer1;
    private RichCommandLink commandLink2;
    private RichSpacer spacer2;
    private RichCommandLink commandLink3;
    private RichSpacer spacer3;
    private RichCommandLink commandLink4;
    
    private RichPanelFormLayout panelFormLayout1;
    private RichPanelFormLayout panelFormLayout3;

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

    public void setSc_areas(RichSelectOneChoice sc_fuentes) {
        this.sc_areas = sc_fuentes;
    }

    public RichSelectOneChoice getSc_areas() {
        return sc_areas;
    }

    public void setSelectItems11(UISelectItems selectItems11) {
        this.selectItems11 = selectItems11;
    }

    public UISelectItems getSelectItems11() {
        return selectItems11;
    }

    public void setPanelGroupLayout1(RichPanelGroupLayout panelGroupLayout1) {
        this.panelGroupLayout1 = panelGroupLayout1;
    }

    public RichPanelGroupLayout getPanelGroupLayout1() {
        return panelGroupLayout1;
    }


    public void setListaAreas(List listaAreas) {
        this.listaAreas = listaAreas;
    }

    public List getListaAreas() {
        return listaAreas;
    }

    public void setListaZonas(List listaZonas) {
        this.listaZonas = listaZonas;
    }

    public List getListaZonas() {
        return listaZonas;
    }

    public void setListaSubzonas(List listaSubzonas) {
        this.listaSubzonas = listaSubzonas;
    }

    public List getListaSubzonas() {
        return listaSubzonas;
    }

    public void setSc_zonas(RichSelectOneChoice selectOneChoice1) {
        this.sc_zonas = selectOneChoice1;
    }

    public RichSelectOneChoice getSc_zonas() {
        return sc_zonas;
    }

    public void setSelectItems2(UISelectItems selectItems1) {
        this.selectItems2 = selectItems1;
    }

    public UISelectItems getSelectItems2() {
        return selectItems2;
    }


    public void setSc_subzonas(RichSelectOneChoice selectOneChoice2) {
        this.sc_subzonas = selectOneChoice2;
    }

    public RichSelectOneChoice getSc_subzonas() {
        return sc_subzonas;
    }

    public void setSelectItems1(UISelectItems selectItems1) {
        this.selectItems1 = selectItems1;
    }

    public UISelectItems getSelectItems1() {
        return selectItems1;
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


    public void setListObjectGrafico(List<Object[]> listObjectGrafico) {
        this.listObjectGrafico = listObjectGrafico;
    }

    public List<Object[]> getListObjectGrafico() {
        return listObjectGrafico;
    }

    public AlertDispBean() {
        
        
        FacesUtils.setActiveBean("AlertDispBean","AlertDispBean", AlertDispBean.class);
        FacesUtils.removeManagedBeanFromSession("AlertaCalidadBean");
        FacesUtils.removeManagedBeanFromSession("AlertContBean");

        grafica1visible=false;
        tabla1visible=false;

        // Si ya se cargo la pagina no ejecutar nuevamente
        if (listaAreas != null)
            return;
        
        this.load();
    }

    public void load() {

        try {

            // Carga lista de areas
            listaAreas = cargarZonificacion(null);
            
            
            listaZonas = new ArrayList();
            listaSubzonas = new ArrayList();

            listObjectGrafico = new ArrayList<Object[]>();

        } catch (IdeamException e) {
            showMessage(e);
        }

    }

    public void sc_areas_valueChangeListener(ValueChangeEvent event) {
        try {

            grafica1visible=false;
            tabla1visible=false;

            PartZonificListas area = (PartZonificListas)event.getNewValue();
            areaActual = area;

            this.listaZonas = this.cargarZonificacion(area.getId());
            this.sc_zonas.setValue(this.listaZonas);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.sc_zonas);

            this.listaSubzonas = new ArrayList();
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.sc_subzonas);

            this.panelFormGrafico.setVisible(false);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelFormGrafico);
            
            this.panelFormLayout1.setVisible(false);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelFormLayout1);
            
            this.panelFormLayout3.setVisible(false);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelFormLayout3);
            
            

        } catch (IdeamException e) {
            showMessage(e);
        }

    }

    public void sc_zonas_valueChangeListener(ValueChangeEvent event) {
        try {

            grafica1visible=false;
            tabla1visible=false;

            PartZonificListas zona = (PartZonificListas)event.getNewValue();
            zonaActual = zona;

            this.listaSubzonas = this.cargarZonificacion(zona.getId());
            this.sc_subzonas.setValue(this.listaSubzonas);

            AdfFacesContext.getCurrentInstance().addPartialTarget(this.sc_subzonas);

            this.panelFormGrafico.setVisible(false);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelFormGrafico);

        } catch (IdeamException e) {
            showMessage(e);
        }

    }

    public void sc_subzonas_valueChangeListener(ValueChangeEvent event) {
        try {



            PartZonificListas subzona = (PartZonificListas)event.getNewValue();
            subzonaActual = subzona;

            grafica1visible=false;
            tabla1visible=true;
            
            if (subzona != null)
               graficarTabla1();

        } catch (IdeamException e) {
            showMessage(e);
        }

    }

    public void graficarTabla1() throws IdeamException {

        System.out.println("Selecciono area:" + areaActual.getId() + ":" +
                           areaActual.getValor());
        System.out.println("Selecciono zona:" + zonaActual.getId() + ":" +
                           zonaActual.getValor());
        System.out.println("Selecciono subzona:" + subzonaActual.getId() +
                           ":" + subzonaActual.getValor());


        this.aot_area.setValue( areaActual.getValor() + " - " + zonaActual.getValor() + " - "+ subzonaActual.getValor());

        this.panelFormGrafico.setVisible(true);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelFormGrafico);
        this.tableAl.setVisible(true);
        AdfFacesContext.getCurrentInstance().addPartialTarget(tableAl);

        FuenteDelegate fd = FuenteDelegate.getInstance();

        listaAlertas =
                fd.getListaAlertasDisponibilidad(areaActual.getId(),
                                                 zonaActual.getId(),
                                                 subzonaActual.getId());
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.tableAl);
        
        tableDe.setVisible(false);
        AdfFacesContext.getCurrentInstance().addPartialTarget(tableDe);
        
        listObjectGrafico = new ArrayList<Object[]>();
        AdfFacesContext.getCurrentInstance().addPartialTarget(grafico);
        
        
    }

    public void detalle_actionListener(ActionEvent actionEvent) {
        try {
            Long idTramo =
                (Long)actionEvent.getComponent().getAttributes().get("idTramo");

            Integer idFuente =
                (Integer)actionEvent.getComponent().getAttributes().get("idFuente");

            String nomFuente =
                (String)actionEvent.getComponent().getAttributes().get("nomFuente");

            String nomTramo =
                (String)actionEvent.getComponent().getAttributes().get("nomTramo");

            String alerta =
                (String)actionEvent.getComponent().getAttributes().get("alerta");
            
            //Double oferta = (Double)actionEvent.getComponent().getAttributes().get("oferta");
            //Double demanda = (Double)actionEvent.getComponent().getAttributes().get("demanda");
            
            //aot_nomFuenteGrafico.setValue(nomFuente);
            aot_nomFuente.setValue(nomFuente + " - " + nomTramo);
            
            //aot_oferta.setValue("Valor oferta disponible:" + oferta + " Valor total demanda:" + demanda);
            
            grafica1visible=true;
            tabla1visible=true;
            
            graficarGrafica1(new Long(idFuente.toString()));
            graficarTabla2(idTramo);

  
            
            this.panelFormLayout1.setVisible(true);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelFormLayout1);
            
            this.panelFormLayout3.setVisible(true);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelFormLayout3);
        } catch (IdeamException e) {
            showMessage(e);
        }

    }

    public void graficarGrafica1(Long idFuente) throws IdeamException {
        
        listObjectGrafico = new ArrayList<Object[]>();
        grafica1visible=true;
        FuenteDelegate fd = FuenteDelegate.getInstance();

        List<Object[]> lDatosGrafico =fd.getListaOfertaXFuenteTramo(idFuente);
        
        if (lDatosGrafico == null)
            return;
        

       
        for (int j = 0; j < lDatosGrafico.size(); j++) {
            Object[] dat = lDatosGrafico.get(j);

            String nomTramo = (String)dat[0];
            Double oferta = new Double(dat[1].toString());
            
            Object[] punto = { nomTramo, "Oferta fuente" , oferta };
            listObjectGrafico.add(punto);

        }

        for (int j = 0; j < lDatosGrafico.size(); j++) {
            Object[] dat = lDatosGrafico.get(j);

            String nomTramo = (String)dat[0];
            Double demanda = new Double(dat[2].toString());
            
            Object[] punto = { nomTramo, "Demanda fuente" , demanda };
            listObjectGrafico.add(punto);

        }
        
        if (lDatosGrafico.size() == 1 ) {

            String nomTramo = (String)" ";
            Double oferta = new Double("0");
            
            Object[] punto = { nomTramo, "Oferta fuente" , oferta };
            listObjectGrafico.add(punto);
            
            Double demanda = new Double("0");
            
            Object[] puntoD = { nomTramo, "Demanda fuente" , demanda };
            listObjectGrafico.add(puntoD);
        }

    }
    
    public void graficarTabla2(Long idTramo) throws IdeamException {
        
        
        listaAlertasDet = new ArrayList();

        this.tableDe.setVisible(true);
        AdfFacesContext.getCurrentInstance().addPartialTarget(tableDe);

        FuenteDelegate fd = FuenteDelegate.getInstance();

        this.listaAlertasDet = fd.getListaAlertasDisponibilidadDet(idTramo);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.tableDe);

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

    public void setGrafica1visible(boolean grafica1visible) {
        this.grafica1visible = grafica1visible;
    }

    public boolean isGrafica1visible() {
        return grafica1visible;
    }

    public void setPanelFormLayout1(RichPanelFormLayout panelFormLayout1) {
        this.panelFormLayout1 = panelFormLayout1;
    }

    public RichPanelFormLayout getPanelFormLayout1() {
        return panelFormLayout1;
    }

    public void setPanelFormLayout3(RichPanelFormLayout panelFormLayout3) {
        this.panelFormLayout3 = panelFormLayout3;
    }

    public RichPanelFormLayout getPanelFormLayout3() {
        return panelFormLayout3;
    }


    public void setTabla1visible(boolean tabla1visible) {
        this.tabla1visible = tabla1visible;
    }

    public boolean isTabla1visible() {
        return tabla1visible;
    }
}
