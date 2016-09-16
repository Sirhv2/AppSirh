package co.gov.ideam.sirh.dashboard.view;

import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.pomca.model.Cuenca;
import co.gov.ideam.sirh.pomca.model.Pomca;
import co.gov.ideam.sirh.pomca.model.Programa;
import co.gov.ideam.sirh.pomca.view.PomcaDelegate;
import co.gov.ideam.sirh.seguridad.model.PerfilVO;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.seguridad.view.SeguridadDelegate;
import co.gov.ideam.sirh.util.ConstantesCalidad;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;

import co.gov.ideam.sirh.view.StandarBean;

import co.gov.ideam.sirh.view.StandarDashBoard;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.component.UISelectItems;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import oracle.adf.view.faces.bi.component.graph.UIGraph;
import oracle.adf.view.faces.bi.model.GraphDataModel;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;

import org.apache.log4j.Logger;

public class DashBoardPomcaGrafico3Bean extends StandarDashBoard{

    static Logger logger = Logger.getLogger(DashBoardPomcaGrafico3Bean.class);
    
    private List listaCuencas;
    private GraphDataModel graphData;
    private String[] columnLabels = { "Cuencas" };
    private String[] seriesLabels;
    private Object[][] data2;

    private RichForm form1;
    private RichDocument document1;
    private RichPanelStretchLayout panelStretchLayout1;
    private RichPanelSplitter panelSplitter1;
    private RichPanelGroupLayout panelGroupLayout2;
    private RichPanelFormLayout panelFormLayout2;
    private RichSpacer spacer4;
    private RichCommandLink clink_inicio;
    private RichSpacer spacer5;
    private RichCommandLink clink_grafico1;
    private RichSpacer spacer6;
    private RichCommandLink clink_grafico2;
    private RichPanelGroupLayout panelGroupLayout3;
    private RichPanelBox panelBoxCalidadGraf1;
    private RichPanelSplitter panelSplitter2;
    private RichPanelGroupLayout panelGroupLayout4;
    private RichPanelFormLayout panelFormLayout12;
    private RichSpacer spacer1;
    private RichSelectOneChoice sc_fuentes;
    private UISelectItems selectItems11;
    private RichPanelGroupLayout panelGroupLayout1;
    private RichPanelFormLayout panelFormLayout1;
    private UIGraph barGraphEjecucionCuencas;
    private String redireccionar;
    
    public DashBoardPomcaGrafico3Bean(){
        FacesUtils.setActiveBean("dashBoardPomcaGrafico3Bean",
                                 "DashBoardPomcaGrafico3Bean", DashBoardPomcaGrafico3Bean.class);    
        this.load();
    }


    public void setRedireccionar(String redireccionar) {
        this.redireccionar = redireccionar;
    }

    public String getRedireccionar() {
        return redireccionar;
    }
    public void redireccionarAOrigen(ActionEvent actionEvent) {
        
        String regla = (String)FacesUtils.getFromSession("OrigenNavegacionUsuario");
        System.out.println("regla de navegación:"+regla);
        if( regla.equals("observatorio"))
             redireccionar= "observatorio";
        else
           redireccionar= "dashBoard";
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

    public void setClink_grafico2(RichCommandLink clink_grafico2) {
        this.clink_grafico2 = clink_grafico2;
    }

    public RichCommandLink getClink_grafico2() {
        return clink_grafico2;
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

    public void setSpacer1(RichSpacer spacer1) {
        this.spacer1 = spacer1;
    }

    public RichSpacer getSpacer1() {
        return spacer1;
    }

    public void setSc_fuentes(RichSelectOneChoice sc_fuentes) {
        this.sc_fuentes = sc_fuentes;
    }

    public RichSelectOneChoice getSc_fuentes() {
        return sc_fuentes;
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

    public void setPanelFormLayout1(RichPanelFormLayout panelFormLayout1) {
        this.panelFormLayout1 = panelFormLayout1;
    }

    public RichPanelFormLayout getPanelFormLayout1() {
        return panelFormLayout1;
    }

    public void setBarGraphEjecucionCuencas(UIGraph barGraphMuestrasFuente) {
        this.barGraphEjecucionCuencas = barGraphMuestrasFuente;
    }

    public UIGraph getBarGraphEjecucionCuencas() {
        return barGraphEjecucionCuencas;
    }

    public void setListaCuencas(List listaCuencas) {
        this.listaCuencas = listaCuencas;
    }

    public List getListaCuencas() {
        return listaCuencas;
    }

    public void setGraphData(GraphDataModel graphData) {
        this.graphData = graphData;
    }

    public GraphDataModel getGraphData() {
        return graphData;
    }

    public void setColumnLabels(String[] columnLabels) {
        this.columnLabels = columnLabels;
    }

    public String[] getColumnLabels() {
        return columnLabels;
    }

    public void setSeriesLabels(String[] seriesLabels) {
        this.seriesLabels = seriesLabels;
    }

    public String[] getSeriesLabels() {
        return seriesLabels;
    }

    public void setData2(Object[][] data2) {
        this.data2 = data2;
    }

    public Object[][] getData2() {
        return data2;
    }


    public void load() {

        logger.info("Entro a load de DashBoardPomcaGrafico1Bean");

        UsuarioConectadoTO usuarioConectado = new UsuarioConectadoTO();
        try {
            usuarioConectado =
                    (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

        } catch (Exception e) {
            usuarioConectado = null;

        }
        try {

            if (usuarioConectado == null) {

                this.listaCuencas = this.getListaCuencas(null);
                } else {   
                
                SeguridadDelegate seg = SeguridadDelegate.getInstance(); 
                PerfilVO pp= new PerfilVO();
                pp=   seg.getPerfilUsuario(usuarioConectado.getUsuario().getCodigo());
                    
                if (pp.getCodigo()==ConstantesCalidad.PERFIL_CONSULTA){
                    this.listaCuencas = this.getListaCuencas(null);
                      
                }else{  

                this.listaCuencas =
                        this.getListaCuencas(usuarioConectado.getUsuario().getAutoridadAmbiental());
            }
                }
        } catch (IdeamException e) {
            showMessage(e);
        }


    }

    protected List getListaCuencas(Autoridades autoridad) throws IdeamException {

        logger.info("Entro a cargar lista getListaCuencas");

        PomcaDelegate pd = PomcaDelegate.getInstance();
        List lista = new ArrayList(); //carga el selectItem.
        List result = null;

        if (autoridad == null) {
            logger.info("Autoridad sin definir");
            result = pd.getAllCuencas();
        } else {
            logger.info("Autoridad DEFINIDA ");
            result = pd.getAllCuencas(autoridad);
        }
        if (result != null) {
            Iterator it = result.iterator();
            while (it.hasNext()) {
                Cuenca c = (Cuenca)it.next();
                logger.info("Agrego a lista " + c.getNombre());
                SelectItem si = new SelectItem(c, c.getNombre());
                lista.add(si);
            }
        }
        return lista;
    }

    public void graficar(Long idCuenca) throws IdeamException {
        
        logger.info("entro a graficar");
        

        try {

            PomcaDelegate pd = PomcaDelegate.getInstance();

            Pomca pomca = pd.getPomca(idCuenca);
            
            if (pomca == null ) {
                logger.info("No hay pomca para la cuenca");
                seriesLabels = null;
                data2 = null;

                this.panelFormLayout1.setVisible(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelFormLayout1);
                initGraphDataModel();
                AdfFacesContext.getCurrentInstance().addPartialTarget(this.barGraphEjecucionCuencas);
                return;
            }
            
            List listaProgramas = pd.getProgramas(pomca);

            logger.info("Hay programas " + listaProgramas.size());
            
            seriesLabels = new String[listaProgramas.size()];
            data2 = new Object[1][listaProgramas.size()];

            if (listaProgramas != null) {
                int i = 0;
                Iterator it = listaProgramas.iterator();
                while (it.hasNext()) {
                    Programa programa = (Programa)it.next();
                    
                    logger.info("Agrega serie :" + programa.getNombre());
                    logger.info("Dato :" + programa.getPorcCumplimiento());
                    
                    seriesLabels[i] = programa.getNombre();
                    data2[0][i] = new Double(programa.getPorcCumplimiento());
                    
                    i++;
                }
            }


            this.panelFormLayout1.setVisible(true);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelFormLayout1);
            initGraphDataModel();
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.barGraphEjecucionCuencas);
            
        } catch (IdeamException e) {
            showMessage(e);
        }

    }

    public void initGraphDataModel() {
        oracle.dss.dataView.LocalXMLDataSource ds =
            new oracle.dss.dataView.LocalXMLDataSource(columnLabels,
                                                       seriesLabels, data2);

        setGraphData(new oracle.adf.view.faces.bi.model.GraphDataModel());
        getGraphData().setDataSource(ds);
    }

    public void sc_cuenca_valueChangeListener(ValueChangeEvent event) {
        try {
            logger.info("entro ha value change");

            Cuenca cuenca = null;

            cuenca = (Cuenca)event.getNewValue();
            
            logger.info("Selecciono cuenca :" + cuenca.getNombre());
            
            graficar(cuenca.getId());

            initGraphDataModel();
            
            logger.info("Cargo datos");
            
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.barGraphEjecucionCuencas);
            
            
        } catch (IdeamException e) {
            logger.info("Error " + e.getMessage());
        }

    }

}
