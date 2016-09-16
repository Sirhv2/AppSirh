package co.gov.ideam.sirh.dashboard.view;

import co.gov.ideam.sirh.calidad.model.CriteriosBusquedaMuestrasTO;
import co.gov.ideam.sirh.calidad.model.DatosGrafico;
import co.gov.ideam.sirh.calidad.model.DatosReporteCalidadParametrosPuntoPOJO;
import co.gov.ideam.sirh.calidad.model.JSDemandaPOJO;
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

public class DashBoardJSDemanda3Bean extends StandarDashBoard {

  private RichTable tableDatosGrafico;
  private List<JSreportePOJO> listaDatos;
  private List<JSDemandaPOJO> listaComponente;
  private List<Object[]> listaDatosGraf;
  private UIGraph lineJSGraph;
  JSReportesDelegate reportesDelegate;
  private List<SelectItem> autoridadItems;
  private List<SelectItem> provinciaItems;
  private List<Autoridades> autoridList;
  private List<Lista> provinciaList;
  private RichSelectOneChoice autoridad;
  private RichSelectOneChoice provincia;
  private CriteriosBusquedaExternasJSTO criterios = new CriteriosBusquedaExternasJSTO();


  public DashBoardJSDemanda3Bean() throws IdeamException{
   this.load(); 
  }
  
  public void load(){
    try{
      listaDatosGraf = new ArrayList<Object[]>();
      this.autoridList = new ArrayList<Autoridades>();
      this.provinciaList = new ArrayList<Lista>();
      this.autoridadItems = new ArrayList<SelectItem>();
      this.provinciaItems = new ArrayList<SelectItem>();
      reportesDelegate = JSReportesDelegate.getInstance();
      this.listaComponente = reportesDelegate.getComponenteDemanda3();
      this.autoridList = this.getAllAutoridades();
      for(int i = 0; i < this.autoridList.size();i++){
        SelectItem nuevo = new SelectItem();
        nuevo.setValue(this.autoridList.get(i).getId().toString());
        nuevo.setLabel(this.autoridList.get(i).getNombre());
        this.autoridadItems.add(nuevo);
      }
      
      this.provinciaList = getAllProvincias();
      for(int i = 0; i < this.provinciaList.size();i++){
        SelectItem nuevo = new SelectItem();
        nuevo.setValue(this.provinciaList.get(i).getCodigo().toString());
        nuevo.setLabel(this.provinciaList.get(i).getValor());
        this.provinciaItems.add(nuevo);
      }
      for (int j = 0; j < this.listaComponente.size(); j++) {
        listaDatosGraf.add(new Object[]{this.listaComponente.get(j).getValor(),this.listaComponente.get(j).getValor(),Double.valueOf(this.listaComponente.get(j).getCaptaciones())}); 
      }
    }catch(IdeamException ex){
      System.out.println("Error en load JSDemanda3 -> " + ex.getMessage());
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
    
    AdfFacesContext.getCurrentInstance().addPartialTarget(this.autoridad);
  }
  
  public void provincia_valueChangeListener(ValueChangeEvent valueChangeEvent) {
    Object provincia = valueChangeEvent.getNewValue();
    if (provincia != null) {
      System.out.println("provincia_valueChangeListener ->" + (String)provincia);
      criterios.setProvinciaId(Integer.valueOf((String)provincia));
    } else {
      criterios.setProvinciaId(null);
    }
    
    AdfFacesContext.getCurrentInstance().addPartialTarget(this.provincia);
  }
  
  protected List<Autoridades> getAllAutoridades() throws IdeamException {
    ParametrosDelegate pd = ParametrosDelegate.getInstance();
    return pd.getAllAutoridades();
  }
  
  protected List<Lista> getAllProvincias() throws IdeamException {
    ParametrosDelegate pd = ParametrosDelegate.getInstance();
    return pd.getAllListaByCategoria(new Long(21));
  }
  
  public void cmdBuscar1_actionListener(ActionEvent actionEvent) {
   
    try {
      this.reportesDelegate = JSReportesDelegate.getInstance();
      this.listaComponente.clear();
      this.listaDatosGraf.clear();     
      
      this.listaComponente =   reportesDelegate.getComponenteDemanda3Filtros(criterios);
      for (int j = 0; j < this.listaComponente.size(); j++) {
        listaDatosGraf.add(new Object[]{this.listaComponente.get(j).getValor(),this.listaComponente.get(j).getValor(),Double.valueOf(this.listaComponente.get(j).getCaptaciones())}); 
      }
     AdfFacesContext.getCurrentInstance().addPartialTarget(this.tableDatosGrafico);
     AdfFacesContext.getCurrentInstance().addPartialTarget(this.lineJSGraph);

    } catch (IdeamException e) {
      showMessage(e);
    }
  }  
  
 /* public void cmdBuscar2_actionListener(ActionEvent actionEvent) {
   
    try {
      System.out.println("cmdBuscar2_actionListener -> ");
      this.reportesDelegate = JSReportesDelegate.getInstance();
      this.listaComponente.clear();
      this.listaDatosGraf.clear();
      criterios.setAutoridad(null);
      this.listaComponente =   reportesDelegate.getComponenteDemanda3Filtros(criterios);
      for (int j = 0; j < this.listaComponente.size(); j++) {
        listaDatosGraf.add(new Object[]{this.listaComponente.get(j).getValor(),this.listaComponente.get(j).getValor(),Double.valueOf(this.listaComponente.get(j).getCaptaciones())}); 
      }
     AdfFacesContext.getCurrentInstance().addPartialTarget(this.tableDatosGrafico);
     AdfFacesContext.getCurrentInstance().addPartialTarget(this.lineJSGraph);

    } catch (IdeamException e) {
      showMessage(e);
    }
  } */ 

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


  public void setListaComponente(List<JSDemandaPOJO> listaComponente) {
    this.listaComponente = listaComponente;
  }

  public List<JSDemandaPOJO> getListaComponente() {
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


  public void setProvinciaList(List<Lista> provinciaList) {
    this.provinciaList = provinciaList;
  }

  public List<Lista> getProvinciaList() {
    return provinciaList;
  }
  
  public void setProvinciaItems(List<SelectItem> provinciaItems) {
    this.provinciaItems = provinciaItems;
  }

  public List<SelectItem> getProvinciaItems() {
    return provinciaItems;
  }

  public void setProvincia(RichSelectOneChoice provincia) {
    this.provincia = provincia;
  }

  public RichSelectOneChoice getProvincia() {
    return provincia;
  }
}
