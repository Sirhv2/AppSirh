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

public class DashBoardJSBean extends StandarDashBoard {

  private RichTable tableDatosGrafico;
  private List<JSreportePOJO> listaDatos;
  private List<JSDemandaPOJO> listaComponente;
  private List<Object[]> listaDatosGraf;
  private UIGraph lineJSGraph;
  JSReportesDelegate reportesDelegate;


  public DashBoardJSBean() throws IdeamException{
    listaDatosGraf = new ArrayList<Object[]>();    
    JSReportesDelegate reportesDelegate;
        reportesDelegate = JSReportesDelegate.getInstance();
        this.listaComponente = reportesDelegate.getComponenteDemanda();       
        System.out.println("ListaComponente valor " + this.listaComponente.get(0).getAcuifero()); 
    for (int j = 0; j < this.listaComponente.size(); j++) {
      System.out.println(">>>>>>>>Entre for " + j);     
     // Object[] obj1 = {listaDatos.get(j).getDescripcion(),listaDatos.get(j).getCantidad(), listaDatos.get(j).getCantidad()};
    // Object[] obj1 = {listaDatos.get(j).getDescripcion(),listaDatos.get(j).getCantidad(), listaDatos.get(j).getCantidad()};
      listaDatosGraf.add(new Object[]{this.listaComponente.get(j).getAcuifero(),this.listaComponente.get(j).getSigla(),Double.valueOf(this.listaComponente.get(j).getCaptaciones())});
     // System.out.println(listaDatosGraf.get(j));
    
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


  public void setListaComponente(List<JSDemandaPOJO> listaComponente) {
    this.listaComponente = listaComponente;
  }

  public List<JSDemandaPOJO> getListaComponente() {
    System.out.println("ListaComponente valor en el get  " + listaComponente.get(0).getAcuifero()); 
    return listaComponente;
  }
  
}
