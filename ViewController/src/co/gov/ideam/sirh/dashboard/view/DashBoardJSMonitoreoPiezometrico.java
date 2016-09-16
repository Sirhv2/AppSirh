package co.gov.ideam.sirh.dashboard.view;

import co.gov.ideam.sirh.calidad.model.CriteriosBusquedaMuestrasTO;
import co.gov.ideam.sirh.calidad.model.DatosGrafico;
import co.gov.ideam.sirh.calidad.model.DatosReporteCalidadParametrosPuntoPOJO;
import co.gov.ideam.sirh.calidad.model.JSDemandaPOJO;
import co.gov.ideam.sirh.calidad.model.JSMonitoreoPiezometricoPOJO;
import co.gov.ideam.sirh.calidad.model.JSreportePOJO;
import co.gov.ideam.sirh.calidad.model.PuntoMonitoreo;
import co.gov.ideam.sirh.calidad.view.CalidadDelegate;

import co.gov.ideam.sirh.calidad.view.FiltrosCalidad;
import co.gov.ideam.sirh.calidad.view.JSReportesDelegate;
import co.gov.ideam.sirh.fuentes.model.CriteriosBusquedaTO;
import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.model.Parametro;
import co.gov.ideam.sirh.parametros.model.PartZonificListas;
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
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import javax.faces.model.SelectItem;

import oracle.adf.view.faces.bi.component.graph.UIGraph;

import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichMenu;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
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

public class DashBoardJSMonitoreoPiezometrico extends StandarDashBoard {

  private RichTable tableDatosGrafico;
  private List<JSMonitoreoPiezometricoPOJO> listaDatos;
  private List<JSMonitoreoPiezometricoPOJO> listaComponente;
  private List<Object[]> listaDatosGraf;
  private UIGraph lineJSGraph;
  JSReportesDelegate reportesDelegate;
  private List<SelectItem> acuiferos;
  private List<SelectItem> idPuntos;
  private List acuiferosList;
  private List idPuntosList;
  private RichSelectOneChoice acuifero;
  private RichSelectOneChoice idPunto;
  private CriteriosBusquedaExternasJSTO criterios = 
    new CriteriosBusquedaExternasJSTO();


  public DashBoardJSMonitoreoPiezometrico() throws IdeamException {
    this.load();
    
    
  }
 
 
  public  void refreshPage(ActionEvent actionEvent){
    this.listaComponente.clear();
    this.acuifero = null;
    this.idPunto= null;
     this.load();
     
  }
  public void load() {
    try {
      
      this.reportesDelegate = JSReportesDelegate.getInstance();
      listaDatosGraf = new ArrayList<Object[]>();
      acuiferosList = new ArrayList();
      idPuntosList = new ArrayList();
      acuiferos = new ArrayList<SelectItem>();
      idPuntos = new ArrayList<SelectItem>();     
        System.out.println("entro al if del null ");
        this.listaComponente = reportesDelegate.getMonitoreoPiezometrico();
      
      System.out.println("ListaComponente valor load " +
                         this.listaComponente.size());
      for (int j = 0; j < this.listaComponente.size(); j++) {
        listaDatosGraf.add(new Object[] { this.listaComponente.get(j).getFecha(),
                                          this.listaComponente.get(j).getIdentificadorPunto(),
                                          this.listaComponente.get(j).getNivelPiezometrico() });

        //Listado Acuiferos
        if (!acuiferosList.contains(listaComponente.get(j).getAcuifero())) {
          acuiferosList.add(listaComponente.get(j).getAcuifero());
          acuiferos.add(new SelectItem(listaComponente.get(j).getAcuifero()));
        }

      }
    } catch (IdeamException e) {
      System.out.println("Error load Monitoreo Piezometrico >" +
                         e.getMessage());
    }
  }


  public void salidaExcel_actionListener(ActionEvent actionEvent) {

    try {
      HashMap parametros = new HashMap();
      parametros.put("p_modulo", "Monitoreo Piezometrico ");
      parametros.put("p_titulo", "Monitoreo Piezometrico");
      this.generateReport("Listadoexporte2.jasper", this.listaComponente,
                          parametros, ReporteDelegate.EXCEL);
    } catch (IdeamException e) {
      System.out.println("Fallo el descargue de  Excel ");
    }

  }
  
  public void acuiferos_valueChangeListener(ValueChangeEvent valueChangeEvent) {
    Object acuifero = valueChangeEvent.getNewValue();   
    if (acuifero != null) {
      criterios.setAcuifero((String)acuifero);
      criterios.setIdPunto(null);
    } else {
      criterios.setAcuifero(null);
      
    }
    try {
      listaComponente.clear();
      idPuntos.clear();
      idPuntosList.clear();

      this.reportesDelegate = JSReportesDelegate.getInstance();
      listaComponente =
          this.reportesDelegate.getMonitoreoPiezometricoFiltros(criterios);
    } catch (IdeamException ie) {
      System.out.println("Error en el acuiferos_value -> " + ie.getMessage());
    }
    for (int i = 0; i < listaComponente.size(); i++) {
      //listado tipo fuentes
      if (!idPuntosList.contains(listaComponente.get(i).getIdentificadorPunto())) {
        idPuntosList.add(listaComponente.get(i).getIdentificadorPunto());
        idPuntos.add(new SelectItem(listaComponente.get(i).getIdentificadorPunto()));
      }
    }
    this.idPunto.setDisabled(false);
    AdfFacesContext.getCurrentInstance().addPartialTarget(this.idPunto);
  }
  
  public void idPunto_valueChangeListener(ValueChangeEvent valueChangeEvent) {
    Object idPunto = valueChangeEvent.getNewValue();
   
    if (idPunto != null) {
      criterios.setIdPunto((String)idPunto);
    } else {
      criterios.setIdPunto(null);
    }
  }

  public void cmdBuscar_actionListener(ActionEvent actionEvent) {
   
    try {

      this.reportesDelegate = JSReportesDelegate.getInstance();
     

      this.listaComponente.clear();
      this.listaDatosGraf.clear();
      
      this.listaComponente =   reportesDelegate.getMonitoreoPiezometricoFiltros(criterios);

     
      for (int j = 0; j < this.listaComponente.size(); j++) {
        listaDatosGraf.add(new Object[] { this.listaComponente.get(j).getFecha(),
                                          this.listaComponente.get(j).getIdentificadorPunto(),
                                          this.listaComponente.get(j).getNivelPiezometrico() });
        System.out.println(j + " > " +
                           this.listaComponente.get(j).getIdentificadorPunto());
      }
     AdfFacesContext.getCurrentInstance().addPartialTarget(this.tableDatosGrafico);
     AdfFacesContext.getCurrentInstance().addPartialTarget(this.lineJSGraph);

      //}

    } catch (IdeamException e) {
      showMessage(e);
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


  public void setLineJSGraph(UIGraph lineJSGraph) {
    this.lineJSGraph = lineJSGraph;
  }

  public UIGraph getLineJSGraph() {
    return lineJSGraph;
  }


  public void setListaComponente(List<JSMonitoreoPiezometricoPOJO> listaComponente) {
    this.listaComponente = listaComponente;
  }

  public List<JSMonitoreoPiezometricoPOJO> getListaComponente() {
   
    return listaComponente;
  }

  public void setListaDatos(List<JSMonitoreoPiezometricoPOJO> listaDatos) {
    this.listaDatos = listaDatos;
  }

  public List<JSMonitoreoPiezometricoPOJO> getListaDatos() {
    return listaDatos;
  }


  public void setAcuiferos(List acuiferos) {
    this.acuiferos = acuiferos;
  }

  public List getAcuiferos() {
    return acuiferos;
  }

  public void setAcuifero(RichSelectOneChoice acuifero) {
    this.acuifero = acuifero;
  }

  public RichSelectOneChoice getAcuifero() {
    return acuifero;
  }

  public void setIdPuntos(List<SelectItem> idPuntos) {
    this.idPuntos = idPuntos;
  }

  public List<SelectItem> getIdPuntos() {
    return idPuntos;
  }

  public void setIdPunto(RichSelectOneChoice idPunto) {
    this.idPunto = idPunto;
  }

  public RichSelectOneChoice getIdPunto() {
    return idPunto;
  }
  
  
}
