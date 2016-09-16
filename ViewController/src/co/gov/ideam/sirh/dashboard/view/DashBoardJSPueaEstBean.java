package co.gov.ideam.sirh.dashboard.view;

import co.gov.ideam.sirh.calidad.model.CriteriosBusquedaMuestrasTO;
import co.gov.ideam.sirh.calidad.model.DatosGrafico;
import co.gov.ideam.sirh.calidad.model.DatosReporteCalidadParametrosPuntoPOJO;
import co.gov.ideam.sirh.calidad.model.JSDemandaPOJO;
import co.gov.ideam.sirh.calidad.model.JSPueaPOJO;
import co.gov.ideam.sirh.calidad.model.JSreportePOJO;
import co.gov.ideam.sirh.calidad.model.PuntoMonitoreo;
import co.gov.ideam.sirh.calidad.view.CalidadDelegate;

import co.gov.ideam.sirh.calidad.view.FiltrosCalidad;
import co.gov.ideam.sirh.calidad.view.JSReportesDelegate;
import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.model.Parametro;
import co.gov.ideam.sirh.parametros.view.ParametrosDelegate;
import co.gov.ideam.sirh.reportes.view.ReporteDelegate;
import co.gov.ideam.sirh.seguridad.model.PerfilVO;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.seguridad.view.SeguridadDelegate;
import co.gov.ideam.sirh.usuarios.agua.model.CriteriosBusquedaExternasJSTO;
import co.gov.ideam.sirh.util.ConstantesCalidad;
import co.gov.ideam.sirh.util.IdeamException;

import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;

import co.gov.ideam.sirh.view.StandarDashBoard;

import java.sql.Date;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import javax.faces.model.SelectItem;

import oracle.adf.view.faces.bi.component.graph.UIGraph;

import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichMenu;
import oracle.adf.view.rich.component.rich.data.RichTable;
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

public class DashBoardJSPueaEstBean extends StandarDashBoard {

  private RichTable tableDatosGrafico;
  private List<JSreportePOJO> listaDatos;
  private List<JSPueaPOJO> listaComponente;
  private List<Object[]> listaDatosGraf;
  private UIGraph lineJSGraph;
  JSReportesDelegate reportesDelegate;
  private List<SelectItem> autoridadItems;
  private List<Autoridades> autoridList;
  private RichSelectOneChoice autoridad;
  private CriteriosBusquedaExternasJSTO criterios = new CriteriosBusquedaExternasJSTO();


  public DashBoardJSPueaEstBean() throws IdeamException{
   this.load(); 
   
  }
  
    public  void refreshPage(ActionEvent actionEvent){
      this.listaComponente.clear();
      this.autoridad = null;     
       this.load();
       
    }
  
  public void load(){
    try{
      listaDatosGraf = new ArrayList<Object[]>();
      this.autoridList = new ArrayList<Autoridades>();
      this.autoridadItems = new ArrayList<SelectItem>();
      reportesDelegate = JSReportesDelegate.getInstance();
      this.listaComponente = reportesDelegate.getConsultaPueaEst();
      this.autoridList = this.getAllAutoridades();
      for(int i = 0; i < this.autoridList.size();i++){
        SelectItem nuevo = new SelectItem();
        nuevo.setValue(this.autoridList.get(i).getId().toString());
        nuevo.setLabel(this.autoridList.get(i).getSigla());
        this.autoridadItems.add(nuevo);
      }
      for (int j = 0; j < this.listaComponente.size(); j++) {
        listaDatosGraf.add(new Object[]{this.listaComponente.get(j).getDescripcion(),this.listaComponente.get(j).getDescripcion(),Double.valueOf(this.listaComponente.get(j).getCantidad())}); 
      }
    }catch(IdeamException ex){
      System.out.println("Error en load JSPueaEstBean -> " + ex.getMessage());
      }
    }
  
  public void autoridad_valueChangeListener(ValueChangeEvent valueChangeEvent) {
    Object autoridad = valueChangeEvent.getNewValue();
    if (autoridad != null) {
      System.out.println("autoridad_valueChangeListener ->" + (String)autoridad);
      criterios.setAutoridad(Integer.valueOf((String)autoridad));
    } else {
      criterios.setAutoridad(null);
    }
    
    //AdfFacesContext.getCurrentInstance().addPartialTarget(this.autoridad);
  }
  
  protected List<Autoridades> getAllAutoridades() throws IdeamException {
    ParametrosDelegate pd = ParametrosDelegate.getInstance();
    return pd.getAllAutoridades();
  }
  
  public void cmdBuscar1_actionListener(ActionEvent actionEvent) {
   
    try {
      this.reportesDelegate = JSReportesDelegate.getInstance();
      this.listaComponente.clear();
      this.listaDatosGraf.clear();     
      System.out.println(" listaComponente ->" + listaComponente.size());
      this.listaComponente =   reportesDelegate.getConsultaPueaEstCriterios(criterios);
      System.out.println(" listaComponente ->" + listaComponente.size());
      for (int j = 0; j < this.listaComponente.size(); j++) {
        listaDatosGraf.add(new Object[]{this.listaComponente.get(j).getDescripcion(),this.listaComponente.get(j).getDescripcion(),Double.valueOf(this.listaComponente.get(j).getCantidad())}); 
      }
    
     AdfFacesContext.getCurrentInstance().addPartialTarget(this.tableDatosGrafico);
     AdfFacesContext.getCurrentInstance().addPartialTarget(this.lineJSGraph);

    } catch (IdeamException e) {
      showMessage(e);
    }
  } 

  public void salidaExcel_actionListener(ActionEvent actionEvent) {

    try {
      HashMap parametros = new HashMap();
      parametros.put("p_modulo", "Estados PUEAA por Corporación ");
      parametros.put("p_titulo", "Estados PUEAA por Corporación");
      this.generateReport("ListadoPueXCorporacion.jasper", this.listaComponente,
                          parametros, ReporteDelegate.EXCEL);
    } catch (IdeamException e) {
      System.out.println("Fallo el descargue de  Excel ");
    }

  }
  public RichTable getTableDatosGrafico() {
    return tableDatosGrafico;
  }

  public void setTableDatosGrafico(RichTable tableDatosGrafico) {
    this.tableDatosGrafico = tableDatosGrafico;
  }


  public void setListaDatosGraf(List<Object[]> listaDatosGraf) {
    this.listaDatosGraf = listaDatosGraf;
  }

  public List<Object[]> getListaDatosGraf() {
    return listaDatosGraf;
  }

  public void setListaDatos(List<JSreportePOJO> listaDatos) {
    this.listaDatos = listaDatos;
  }

  public List<JSreportePOJO> getListaDatos() {
    return listaDatos;
  }


  public void setLineJSGraph(UIGraph lineJSGraph) {
    this.lineJSGraph = lineJSGraph;
  }

  public UIGraph getLineJSGraph() {
    return lineJSGraph;
  }


  public void setListaComponente(List<JSPueaPOJO> listaComponente) {
    this.listaComponente = listaComponente;
  }

  public List<JSPueaPOJO> getListaComponente() {
    return listaComponente;
  }


  public void setAutoridadItems(List<SelectItem> autoridadItems) {
    this.autoridadItems = autoridadItems;
  }

  public List<SelectItem> getAutoridadItems() {
    return autoridadItems;
  }

  public void setAutoridList(List<Autoridades> autoridList) {
    this.autoridList = autoridList;
  }

  public List<Autoridades> getAutoridList() {
    return autoridList;
  }

  public void setAutoridad(RichSelectOneChoice autoridad) {
    this.autoridad = autoridad;
  }

  public RichSelectOneChoice getAutoridad() {
    return autoridad;
  }
}
