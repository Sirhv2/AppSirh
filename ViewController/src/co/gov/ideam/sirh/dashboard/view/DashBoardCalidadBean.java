package co.gov.ideam.sirh.dashboard.view;


import co.gov.ideam.sirh.calidad.model.CriteriosBusquedaMuestrasTO;
import co.gov.ideam.sirh.calidad.model.CriteriosBusquedaPuntosTO;
import co.gov.ideam.sirh.calidad.model.DatosReporteCalidadPOJO;
import co.gov.ideam.sirh.calidad.model.DatosReporteCalidadParametrosPuntoPOJO;
import co.gov.ideam.sirh.calidad.model.PuntoMonitoreo;
import co.gov.ideam.sirh.calidad.view.CalidadDelegate;


import co.gov.ideam.sirh.calidad.view.FiltrosCalidad;
import co.gov.ideam.sirh.estadisticas.view.MuestrasPorAnioBean;
import co.gov.ideam.sirh.estadisticas.view.MuestrasPorTipoBean;
import co.gov.ideam.sirh.fuentes.model.FnttFuente;

import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.model.PartZonificListas;
import co.gov.ideam.sirh.parametros.view.ParametrosDelegate;
import co.gov.ideam.sirh.reportes.view.ReporteDelegate;
import co.gov.ideam.sirh.seguridad.model.PerfilVO;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.seguridad.view.SeguridadDelegate;
import co.gov.ideam.sirh.util.ConstantesCalidad;
import co.gov.ideam.sirh.util.IdeamException;

import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;

import co.gov.ideam.sirh.view.StandarDashBoard;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UISelectItems;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import javax.faces.model.SelectItem;

import oracle.adf.view.faces.bi.component.graph.UIGraph;
import oracle.adf.view.faces.bi.model.GraphDataModel;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichMenu;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.fragment.RichDynamicDeclarativeComponent;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.nav.RichCommandMenuItem;
import oracle.adf.view.rich.component.rich.output.RichActiveOutputText;
import oracle.adf.view.rich.component.rich.output.RichOutputLabel;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;


public class DashBoardCalidadBean  extends StandarDashBoard{
    private RichForm form1;
    private RichDocument document1;
    private RichPanelStretchLayout panelStretchLayout1;
    private RichPanelSplitter panelSplitter1;
    private RichPanelGroupLayout panelGroupLayout2;
    private RichPanelFormLayout panelFormLayout2;
    private RichCommandLink clink_inicio;
    private RichPanelGroupLayout panelGroupLayout3;
    private RichCommandLink clink_grafico1;
    private RichCommandLink clink_grafico2;
     
    private RichPanelBox panelBoxCalidadGraf1;
    private RichPanelFormLayout panelFormLayout12;

    private UIGraph barGraphMuestrasFuente;
    private List<Object[]> listObject;
    private List listaDatos;
    private List<Object[]> listObjectGrafico;
    private GraphDataModel graphData;
    private String[] columnLabels = { "Muestras" };
    private String[] seriesLabels;
    private Object[][] data2;
    private RichPanelGroupLayout graficaTabla;
    private RichPanelFormLayout panelFormGrafico;
    private RichOutputLabel outputLabel1;
    private RichPanelGroupLayout panelGroupLayout4;
    private RichSelectOneChoice sc_fuentes;
    private UISelectItems selectItems11;
    private RichSelectOneChoice sc_anio;
    private UISelectItems selectItems21;

    private RichSpacer spacer4;
    private RichSpacer spacer5;
    private RichSpacer spacer6;
    private RichSpacer spacer7;

    private RichCommandLink cink3;
    private String redireccionar;

    private RichPanelGroupLayout panelGroupLayout6;
    private RichPanelCollection panelCollection1;
    private RichMenu menu1;
    private RichCommandMenuItem salidaExcel;
    private RichCommandButton cb_buscar;
    private RichSpacer spacer8;
    private RichSpacer spacer10;
    private RichSpacer spacer11;
    private RichPanelGroupLayout panelGroupLayout7;
    private RichTable tableDatosGrafico1;
    private RichSpacer spacer1;
    private RichPanelSplitter panelSplitter3;
    private RichActiveOutputText ot_fuente;
    private RichActiveOutputText datos;
    private RichSpacer spacer2;
    private RichPanelGroupLayout panelGroupLayout1;
    private RichPanelBox panelBox1;
    private RichDynamicDeclarativeComponent dc33;
    private Integer numOrigen;


    public DashBoardCalidadBean(){
        FacesUtils.setActiveBean("dashBoardCalidad","DashBoardCalidadBean", DashBoardCalidadBean.class);
        FacesUtils.removeManagedBeanFromSession("dashBoardCalidadGrafico2");
        FacesUtils.removeManagedBeanFromSession("dashBoardCalidadGrafico3");
        numOrigen=new Integer("1");
        FacesUtils.removeManagedBeanFromSession("numOrigen");
        FacesUtils.setInSession("numOrigen",numOrigen);
       this.load();        
    }
    
    public void load(){ 
        definirFiltros();
        
    }
    public void definirFiltros(){
        try{        
            System.out.println("---------------DEFINICION INICIAL DE FILTROS DashBoardCalidadBean-----------");
            
            Object objFiltros = FacesUtils.getManagedBeanValue("filtrosCalidad");
            ((FiltrosCalidad) objFiltros).setObligatorioFuente(true);        
            ((FiltrosCalidad) objFiltros).setVerListaParametros(false);          
            ((FiltrosCalidad) objFiltros).setObligatorioParametro(false);   
            ((FiltrosCalidad) objFiltros).setDisabledListaPuntosMonitoreo(true);          
            ((FiltrosCalidad) objFiltros).setObligatorioPuntoMonitoreo(false); 
            ((FiltrosCalidad) objFiltros).setVerListaAnios(true);
            ((FiltrosCalidad) objFiltros).setObligatorioAnio(true);
            ((FiltrosCalidad) objFiltros).setVerFechaInicial(false);                    
            ((FiltrosCalidad) objFiltros).setVerFechaFinal(false);                  
           
             
            ((FiltrosCalidad) objFiltros).setNullListaAnios();
            AdfFacesContext.getCurrentInstance().addPartialTarget( ((FiltrosCalidad) objFiltros).getFc_panelGroupLayout1() );
        }catch(Exception e){
            System.out.println( "error refrescando filtrosCalidad.jsff:"+e);
        }
    }

    
    
   public void cb_buscar_actionListener(ActionEvent actionEvent) {  
       Boolean tieneDatosObjGrafica;
       tieneDatosObjGrafica = getListObjectGrafico();
    
      if(tieneDatosObjGrafica){
System.out.println("listaObjGrafica.size()= TRUE ");   
           this.panelFormGrafico.setVisible(true);
           AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelFormGrafico);  
           this.tableDatosGrafico1.setVisible(true);
           AdfFacesContext.getCurrentInstance().addPartialTarget(tableDatosGrafico1);
           initGraphDataModel();
           AdfFacesContext.getCurrentInstance().addPartialTarget(this.barGraphMuestrasFuente);  
      }else {
System.out.println("listaObjGrafica.size()= FALSE ");  
          this.panelFormGrafico.setVisible(false);
          AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelFormGrafico); 
          this.tableDatosGrafico1.setVisible(false);
          AdfFacesContext.getCurrentInstance().addPartialTarget(tableDatosGrafico1);
          AdfFacesContext.getCurrentInstance().addPartialTarget(this.barGraphMuestrasFuente);  
          }
   }


    public Boolean getListObjectGrafico() { 
//        listObjectGrafico = new ArrayList<Object[]>(); 
        Boolean listObjectGrafico = false;
        this.listaDatos = new ArrayList();
    
        try{    
            Object obj = FacesUtils.getManagedBeanValue("filtrosCalidad");        
            CriteriosBusquedaMuestrasTO criterios = null;
            if(obj!=null){
                criterios = ((FiltrosCalidad) obj).getCriterios(); 
                if (criterios==null){
                    showMessage("Debe seleccionar una Fuente", FacesMessage.SEVERITY_ERROR);
                    return listObjectGrafico;

                }else{
                    // validar que se haya seleccionado una fuente
                    if(criterios.getFuente()==null){
                        showMessage("Debe seleccionar una Fuente", FacesMessage.SEVERITY_ERROR);
                        return listObjectGrafico;
                    } 
                }    
            }     
            if(criterios.getAnio()!=null)
                System.out.println("--------------******criterios.getAnio():"+criterios.getAnio());
            CalidadDelegate cd = CalidadDelegate.getInstance();
            
            String[][] datos = cd.getNroMuestrasPorAnioFuente(criterios, ((FiltrosCalidad) obj).getIdAutoridadFuentePunto());                         
            
            seriesLabels = new String[ datos[0].length ];
            data2 = new Object[1][ datos[0].length ];
            for(int i=0; i<datos[0].length; i++){                
                seriesLabels[i] = datos[0][i];                
                data2[0][i] = new Integer(datos[1][i]);                         
            
            }    
            this.listaDatos = cd.getNroMuestrasPorAnioFuenteDatos(criterios, ((FiltrosCalidad) obj).getIdAutoridadFuentePunto());
            if (listaDatos != null) {                             
                  Integer i=1;                  
                  Iterator<DatosReporteCalidadPOJO> it = listaDatos.iterator();
                  while (it.hasNext()) {
                      DatosReporteCalidadPOJO reg = it.next();
                      reg.setNum(i);
                      i++;
                  } 
            DatosReporteCalidadPOJO reg1 = (DatosReporteCalidadPOJO)this.listaDatos.get(0);            
            this.ot_fuente.setValue(reg1.getFuente().toString());
    //            this.ot_parametro.setValue("Parámetro: "+  reg1.getP .getParametro() + " Unidad: "+ reg1.getUnidad().toString());
            listObjectGrafico = true;
              }
                        
        }catch(IdeamException e){
            showMessage(e);
            }
                
        return listObjectGrafico; 

    } 
    
    public void salidaExcel_actionListener(ActionEvent actionEvent) {
        try {
            HashMap parametros = new HashMap();
            parametros.put("p_modulo", "Observatorio del Agua - Componente Calidad");
            parametros.put("p_titulo", "Número de muestras en el año sobre una fuente hídrica");
            this.generateReport("ListadoCalidad_1.jasper", this.listaDatos,
                                parametros, ReporteDelegate.EXCEL);
        } catch (IdeamException e) {
            showMessage(e);
        }    
    }
   
    
    public void initGraphDataModel() {
        oracle.dss.dataView.LocalXMLDataSource ds =
            new oracle.dss.dataView.LocalXMLDataSource(columnLabels, seriesLabels, data2);

        setGraphData(new oracle.adf.view.faces.bi.model.GraphDataModel());
        getGraphData().setDataSource(ds);
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


    public void setBarGraphMuestrasFuente(UIGraph barGraphMuestrasFuente) {
        this.barGraphMuestrasFuente = barGraphMuestrasFuente;
    }

    public UIGraph getBarGraphMuestrasFuente() {
        return barGraphMuestrasFuente;
    }

    public void setListObject(List<Object[]> listObject) {
        this.listObject = listObject;
    }

    public List<Object[]> getListObject() {
        return listObject;
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

   

    public void setClink_grafico2(RichCommandLink commandLink4) {
        this.clink_grafico2 = commandLink4;
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

    public void setPanelFormLayout12(RichPanelFormLayout panelFormLayout12) {
        this.panelFormLayout12 = panelFormLayout12;
    }

    public RichPanelFormLayout getPanelFormLayout12() {
        return panelFormLayout12;
    }

    public void setClink_inicio(RichCommandLink clink_inicio) {
        this.clink_inicio = clink_inicio;
    }

    public RichCommandLink getClink_inicio() {
        return clink_inicio;
    }

    public void setPanelBoxCalidadGraf1(RichPanelBox panelBoxCalidad1) {
        this.panelBoxCalidadGraf1 = panelBoxCalidad1;
    }

    public RichPanelBox getPanelBoxCalidadGraf1() {
        return panelBoxCalidadGraf1;
    }

    public void setGraficaTabla(RichPanelGroupLayout panelGroupLayout1) {
        this.graficaTabla = panelGroupLayout1;
    }

    public RichPanelGroupLayout getGraficaTabla() {
        return graficaTabla;
    }

    public void setPanelFormGrafico(RichPanelFormLayout panelFormLayout1) {
        this.panelFormGrafico = panelFormLayout1;
    }

    public RichPanelFormLayout getPanelFormGrafico() {
        return panelFormGrafico;
    }

    public void setOutputLabel1(RichOutputLabel outputLabel1) {
        this.outputLabel1 = outputLabel1;
    }

    public RichOutputLabel getOutputLabel1() {
        return outputLabel1;
    }

    public void setPanelGroupLayout4(RichPanelGroupLayout panelGroupLayout4) {
        this.panelGroupLayout4 = panelGroupLayout4;
    }

    public RichPanelGroupLayout getPanelGroupLayout4() {
        return panelGroupLayout4;
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

    public void setSc_anio(RichSelectOneChoice sc_anio) {
        this.sc_anio = sc_anio;
    }

    public RichSelectOneChoice getSc_anio() {
        return sc_anio;
    }

    public void setSelectItems21(UISelectItems selectItems21) {
        this.selectItems21 = selectItems21;
    }

    public UISelectItems getSelectItems21() {
        return selectItems21;
    }


/*
    public void setListaAnios(List listaAnios) {
        this.listaAnios = listaAnios;
    }

    public List getListaAnios() {
        return listaAnios;
    }

    public List getListaFuentesOLD() {
        return listaFuentesOLD;
    }

    public void setListaFuentesOLD(List listaFuentesOLD) {
        this.listaFuentesOLD = listaFuentesOLD;
    }

*/


    public void setSpacer4(RichSpacer spacer4) {
        this.spacer4 = spacer4;
    }

    public RichSpacer getSpacer4() {
        return spacer4;
    }

    public void setSpacer5(RichSpacer spacer5) {
        this.spacer5 = spacer5;
    }

    public RichSpacer getSpacer5() {
        return spacer5;
    }

    public void setSpacer6(RichSpacer spacer6) {
        this.spacer6 = spacer6;
    }

    public RichSpacer getSpacer6() {
        return spacer6;
    }

    public void setSpacer7(RichSpacer spacer7) {
        this.spacer7 = spacer7;
    }

    public RichSpacer getSpacer7() {
        return spacer7;
    }

    public void setCink3(RichCommandLink commandLink1) {
        this.cink3 = commandLink1;
    }

    public RichCommandLink getCink3() {
        return cink3;
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



    public void setPanelGroupLayout6(RichPanelGroupLayout panelGroupLayout6) {
        this.panelGroupLayout6 = panelGroupLayout6;
    }

    public RichPanelGroupLayout getPanelGroupLayout6() {
        return panelGroupLayout6;
    }
    
    public List getListaDatos() {
        return listaDatos;
    }
    
    public void setListaDatos(List listaDatos) {
        this.listaDatos = listaDatos;
    }

/*
    public List<DatosReporteCalidadPOJO> getListaDatos() {
        return listaDatos;
    }

    public void setListaDatos(List<DatosReporteCalidadPOJO> listaDatos) {
        this.listaDatos = listaDatos;
    }
*/
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

    public void setSalidaExcel(RichCommandMenuItem commandMenuItem1) {
        this.salidaExcel = commandMenuItem1;
    }

    public RichCommandMenuItem getSalidaExcel() {
        return salidaExcel;
    }

    public void setCb_buscar(RichCommandButton cb_buscar) {
        this.cb_buscar = cb_buscar;
    }

    public RichCommandButton getCb_buscar() {
        return cb_buscar;
    }

    public void setSpacer8(RichSpacer spacer8) {
        this.spacer8 = spacer8;
    }

    public RichSpacer getSpacer8() {
        return spacer8;
    }


    public void setSpacer10(RichSpacer spacer10) {
        this.spacer10 = spacer10;
    }

    public RichSpacer getSpacer10() {
        return spacer10;
    }

    public void setSpacer11(RichSpacer spacer11) {
        this.spacer11 = spacer11;
    }

    public RichSpacer getSpacer11() {
        return spacer11;
    }


    public void setPanelGroupLayout7(RichPanelGroupLayout panelGroupLayout7) {
        this.panelGroupLayout7 = panelGroupLayout7;
    }

    public RichPanelGroupLayout getPanelGroupLayout7() {
        return panelGroupLayout7;
    }


    public void setTableDatosGrafico1(RichTable table1) {
        this.tableDatosGrafico1 = table1;
    }

    public RichTable getTableDatosGrafico1() {
        return tableDatosGrafico1;
    }

    public void setSpacer1(RichSpacer spacer1) {
        this.spacer1 = spacer1;
    }

    public RichSpacer getSpacer1() {
        return spacer1;
    }

    public void setPanelSplitter3(RichPanelSplitter panelSplitter3) {
        this.panelSplitter3 = panelSplitter3;
    }

    public RichPanelSplitter getPanelSplitter3() {
        return panelSplitter3;
    }


    public void setOt_fuente(RichActiveOutputText ot_fuente) {
        this.ot_fuente = ot_fuente;
    }

    public RichActiveOutputText getOt_fuente() {
        return ot_fuente;
    }


    public void setDatos(RichActiveOutputText datos) {
        this.datos = datos;
    }

    public RichActiveOutputText getDatos() {
        return datos;
    }

    public void setSpacer2(RichSpacer spacer2) {
        this.spacer2 = spacer2;
    }

    public RichSpacer getSpacer2() {
        return spacer2;
    }

    public void setPanelGroupLayout1(RichPanelGroupLayout panelGroupLayout1) {
        this.panelGroupLayout1 = panelGroupLayout1;
    }

    public RichPanelGroupLayout getPanelGroupLayout1() {
        return panelGroupLayout1;
    }


    public void setPanelBox1(RichPanelBox panelBox1) {
        this.panelBox1 = panelBox1;
    }

    public RichPanelBox getPanelBox1() {
        return panelBox1;
    }


    public void setDc33(RichDynamicDeclarativeComponent dc33) {
        this.dc33 = dc33;
    }

    public RichDynamicDeclarativeComponent getDc33() {
        return dc33;
    }

    public void setClink_grafico1(RichCommandLink clink_grafico1) {
        this.clink_grafico1 = clink_grafico1;
    }

    public RichCommandLink getClink_grafico1() {
        return clink_grafico1;
    }
}
