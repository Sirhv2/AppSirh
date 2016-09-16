package co.gov.ideam.sirh.dashboard.view;

import co.gov.ideam.sirh.calidad.model.CriteriosBusquedaMuestrasTO;
import co.gov.ideam.sirh.calidad.model.DatosGrafico;
import co.gov.ideam.sirh.calidad.model.DatosReporteCalidadParametrosPuntoPOJO;
import co.gov.ideam.sirh.calidad.model.JSCalidadParametrosPOJO;
import co.gov.ideam.sirh.calidad.model.PuntoMonitoreo;
import co.gov.ideam.sirh.calidad.view.CalidadDelegate;

import co.gov.ideam.sirh.calidad.view.FiltrosCalidad;
import co.gov.ideam.sirh.calidad.view.JSReportesDelegate;
import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.model.Parametro;
import co.gov.ideam.sirh.reportes.view.ReporteDelegate;
import co.gov.ideam.sirh.seguridad.model.PerfilVO;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.seguridad.view.SeguridadDelegate;
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
import co.gov.ideam.sirh.usuarios.agua.model.CriteriosBusquedaExternasJSTO;
import javax.faces.model.SelectItem;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;

import oracle.adf.view.faces.bi.component.graph.UIGraph;

import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichMenu;
import oracle.adf.view.rich.component.rich.data.RichTable;
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

public class DashBoardCalidadSubtParametrosBean extends StandarDashBoard {

  private RichTable tableDatosGrafico;
  private List<JSCalidadParametrosPOJO> listaComponente;
  private List<Object[]> listaDatosGraf;
  private UIGraph lineJSGraph;
  JSReportesDelegate reportesDelegate;
  private List<SelectItem> provinciasItems;
    private RichSelectOneChoice provincia;
    private List provinciaList;
    private List<SelectItem> acuiferoItems;
    private RichSelectOneChoice acuifero;
    private List acuiferoList;
    private CriteriosBusquedaExternasJSTO criterios = new CriteriosBusquedaExternasJSTO();


  public DashBoardCalidadSubtParametrosBean() throws IdeamException {
    this.load();
  }
  
  public void load() throws IdeamException {
        listaDatosGraf = new ArrayList<Object[]>();
        reportesDelegate = JSReportesDelegate.getInstance();
        this.provinciasItems = new ArrayList<SelectItem>();
        this.provinciaList = new ArrayList();
        this.acuiferoItems = new ArrayList<SelectItem>();
        this.acuiferoList = new ArrayList();
        this.listaComponente = reportesDelegate.getComponenteCalidadXParametros();
        for (int j = 0; j < this.listaComponente.size(); j++) {
          listaDatosGraf.add(new Object[] { this.listaComponente.get(j).getAcuifero(),
                                            this.listaComponente.get(j).getParametroCalidad(),
                                            Double.valueOf(this.listaComponente.get(j).getValor())});
          //Lista de Provincias
          if (!provinciaList.contains(listaComponente.get(j).getProvHidro())){
            provinciaList.add(listaComponente.get(j).getProvHidro());
            provinciasItems.add(new SelectItem(listaComponente.get(j).getProvHidro()));
          }
          
          //Lista de Acuiferos
          if (!acuiferoList.contains(listaComponente.get(j).getAcuifero())){
            acuiferoList.add(listaComponente.get(j).getAcuifero());
            acuiferoItems.add(new SelectItem(listaComponente.get(j).getAcuifero()));
          }
        }
      }
  
  public void salidaExcel_actionListener(ActionEvent actionEvent) {

    try {
      HashMap parametros = new HashMap();
      parametros.put("p_modulo", "Calidad Parametros ");
      parametros.put("p_titulo", "Calidad Parametros");
      this.generateReport("Listadoexporte.jasper", this.listaComponente,
                          parametros, ReporteDelegate.EXCEL);
    } catch (IdeamException e) {
      System.out.println("Fallo el descargue de  Excel ");
    }

  }
  
  public void provincia_valueChangeListener(ValueChangeEvent valueChangeEvent) {
    Object provincia = valueChangeEvent.getNewValue();
    
    if (provincia != null) {
      criterios.setProvincia((String)provincia);
      System.out.println("provincia_valueChangeListener ->" + (String)provincia);
    } else {
      criterios.setProvincia(null);
    }
  }
  
  public void acuifero_valueChangeListener(ValueChangeEvent valueChangeEvent) {
    System.out.println("acuifero_valueChangeListener ->");
    Object acuifero = valueChangeEvent.getNewValue();
    
    if (acuifero != null) {
      criterios.setAcuifero((String)acuifero);
      System.out.println("acuifero_valueChangeListener ->" + (String)acuifero);
    } else {
      criterios.setAcuifero(null);
    }
  }
  
  public void cmdBuscar_actionListener(ActionEvent actionEvent) {
   
    try {      
      this.reportesDelegate = JSReportesDelegate.getInstance();
      this.listaComponente.clear();      
      this.listaComponente =   reportesDelegate.getComponenteCalidadXParametrosFiltros(criterios);
    
     AdfFacesContext.getCurrentInstance().addPartialTarget(this.tableDatosGrafico);
     
    } catch (IdeamException e) {
      showMessage(e);
    }
  }
  
 /* public void cmdBuscar2_actionListener(ActionEvent actionEvent) {
    try {
      this.reportesDelegate = JSReportesDelegate.getInstance();
      this.listaComponente.clear();
      this.criterios.setProvincia(null);
      this.listaComponente = reportesDelegate.getComponenteCalidadXParametrosFiltros(criterios);
     //AdfFacesContext.getCurrentInstance().addPartialTarget(this.tableDatosGrafico);
    } catch (IdeamException e) {
      showMessage(e.getMessage());
    }
  }*/

  public void setProvinciasItems(List<SelectItem> provinciasItems) {
    this.provinciasItems = provinciasItems;
  }

  public List<SelectItem> getProvinciasItems() {
    return provinciasItems;
  }

  public void setProvincia(RichSelectOneChoice provincia) {
    this.provincia = provincia;
  }

  public RichSelectOneChoice getProvincia() {
    return provincia;
  }

  public void setProvinciaList(List provinciaList) {
    this.provinciaList = provinciaList;
  }

  public List getProvinciaList() {
    return provinciaList;
  }

  public void setAcuiferoItems(List<SelectItem> acuiferoItems) {
    this.acuiferoItems = acuiferoItems;
  }

  public List<SelectItem> getAcuiferoItems() {
    return acuiferoItems;
  }

  public void setAcuifero(RichSelectOneChoice acuifero) {
    this.acuifero = acuifero;
  }

  public RichSelectOneChoice getAcuifero() {
    return acuifero;
  }

  public void setAcuiferoList(List acuiferoList) {
    this.acuiferoList = acuiferoList;
  }

  public List getAcuiferoList() {
    return acuiferoList;
  }

  public void setCriterios(CriteriosBusquedaExternasJSTO criterios) {
    this.criterios = criterios;
  }

  public CriteriosBusquedaExternasJSTO getCriterios() {
    return criterios;
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


  public void setLineJSGraph(UIGraph lineJSGraph) {
    this.lineJSGraph = lineJSGraph;
  }

  public UIGraph getLineJSGraph() {
    return lineJSGraph;
  }


  public void setListaComponente(List<JSCalidadParametrosPOJO> listaComponente) {
    this.listaComponente = listaComponente;
  }

  public List<JSCalidadParametrosPOJO> getListaComponente() {
    return listaComponente;
  }



}
