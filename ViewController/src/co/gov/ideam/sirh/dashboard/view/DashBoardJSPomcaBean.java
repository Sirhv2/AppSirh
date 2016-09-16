package co.gov.ideam.sirh.dashboard.view;

import co.gov.ideam.sirh.calidad.model.AcuiferosPOJO2;
import co.gov.ideam.sirh.calidad.model.CriteriosBusquedaMuestrasTO;
import co.gov.ideam.sirh.calidad.model.DatosGrafico;
import co.gov.ideam.sirh.calidad.model.DatosReporteCalidadParametrosPuntoPOJO;
import co.gov.ideam.sirh.calidad.model.JSDemandaPOJO;
import co.gov.ideam.sirh.calidad.model.JSMonitoreoPiezometricoPOJO;
import co.gov.ideam.sirh.calidad.model.JSPomcaPOJO;
import co.gov.ideam.sirh.calidad.model.JSreportePOJO;
import co.gov.ideam.sirh.calidad.model.PuntoMonitoreo;
import co.gov.ideam.sirh.calidad.view.CalidadDelegate;
import co.gov.ideam.sirh.calidad.view.FiltrosCalidad;
import co.gov.ideam.sirh.calidad.view.JSReportesDelegate;
import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.model.Parametro;
import co.gov.ideam.sirh.publicaciones.view.PublicacionesDelegate;
import co.gov.ideam.sirh.reportes.view.ReporteDelegate;
import co.gov.ideam.sirh.seguridad.model.PerfilVO;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.seguridad.view.SeguridadDelegate;
import co.gov.ideam.sirh.usuarios.agua.model.CriteriosBusquedaExternasJSTO;
import co.gov.ideam.sirh.usuarios.agua.model.UsuarioAguaTO;
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

public class DashBoardJSPomcaBean extends StandarDashBoard {

  private RichTable tableDatosGrafico;

  private List<JSPomcaPOJO> listaComponente;
  private List<Object[]> listaDatosGraf;
  private UIGraph lineJSGraph;
  JSReportesDelegate reportesDelegate;
  private RichSelectOneChoice soc_filtro_tipo_id;
  private RichSelectOneChoice autoridad;
  private List<SelectItem> autoridadSelectItems;   
   private List  autoridadList;
  private String sistemaAcuiferoFilter;
   
  private CriteriosBusquedaExternasJSTO criterios = new CriteriosBusquedaExternasJSTO();


  public DashBoardJSPomcaBean() throws IdeamException {
  System.out.print("Entra al constructor ");
  this.load();
  }
  public void load() throws IdeamException{
    
    this.autoridadSelectItems = new ArrayList<SelectItem>();
    this.autoridadList = new ArrayList();
   /* UsuarioConectadoTO usuarioConectado =    (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado"); 
    
    System.out.println("Antes del if   ");
    if(usuarioConectado != null  ){
      System.out.println("entro al if ");
    System.out.println("Autoridad ambiental -> pomca  "+  usuarioConectado.getUsuario().getAutoridadAmbiental().getId());
    } */  
    listaDatosGraf = new ArrayList<Object[]>();
    JSReportesDelegate reportesDelegate;
    reportesDelegate = JSReportesDelegate.getInstance();
    this.listaComponente = reportesDelegate.getConsultaPomca();
    
    for (int j = 0; j < this.listaComponente.size(); j++) {       
      
    //   System.out.println("Da");
      //Listado Provincia
      if (!autoridadList.contains(listaComponente.get(j).getAutor())) {
        autoridadList.add(listaComponente.get(j).getAutor());
        autoridadSelectItems.add(new SelectItem(listaComponente.get(j).getAutor()));
      }   
    } 
    
  }
  
  
  public void cmdBuscar_actionListener(ActionEvent actionEvent) {
   
    try {
      this.listaComponente.clear();   
      this.reportesDelegate = JSReportesDelegate.getInstance();        
      this.listaComponente =  reportesDelegate.getConsultaPomcaArea(this.getSistemaAcuiferoFilter());     
     
    } catch (IdeamException e) {
      showMessage(e);
    }
  }


  public void salidaExcel_actionListener(ActionEvent actionEvent) {

    try {
      HashMap parametros = new HashMap();
      parametros.put("p_modulo", "Pomca");
      parametros.put("p_titulo", "Pomca");
     
      this.generateReport("Listadopompca.jasper", this.listaComponente,                  
                          parametros, ReporteDelegate.EXCEL);
    } catch (IdeamException e) {
      System.out.println("Fallo el descargue de  Excel ");
    }

  }


  public void autoridad_valueChangeListener(ValueChangeEvent valueChangeEvent) {
    Object autoridad = valueChangeEvent.getNewValue();
    if (autoridad != null) {
     // System.out.println("provincia_valueChangeListener ->" + (String)provincia);
     this.setSistemaAcuiferoFilter((String)autoridad);
    } else {
     this.setSistemaAcuiferoFilter("");
    }   
    AdfFacesContext.getCurrentInstance().addPartialTarget(this.autoridad);
  }
  





  public void cmi_imprimir_actionListener(ActionEvent actionEvent) {
    if (tableDatosGrafico.getSelectedRowData() == null) {
      showMessage(getText("seleccionar_registro"),
                  FacesMessage.SEVERITY_ERROR);
      return;
    }
    JSPomcaPOJO pomca =
      (JSPomcaPOJO)tableDatosGrafico.getSelectedRowData();

    try {
      HashMap parametros = new HashMap();
      parametros.put("p_modulo", "Hoja Vida Pomca");
      parametros.put("p_titulo", "Hoja vida Pomca ");
    //  System.out.println("ID : " + pomca.getPomca_id().toString());
        parametros.put("contexto", FacesUtils.getRealPath("/imgs/imagenes_acuiferos")) ;
      parametros.put("ID_PO", pomca.getPomca_id().toString());
      this.generateReport("HojaVidPompca.jasper", parametros,
                          ReporteDelegate.PDF);

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


  

  public void setSoc_filtro_tipo_id(RichSelectOneChoice soc_filtro_tipo_id) {
    this.soc_filtro_tipo_id = soc_filtro_tipo_id;
  }

  public RichSelectOneChoice getSoc_filtro_tipo_id() {
    return soc_filtro_tipo_id;
  }

  public void setListaComponente(List<JSPomcaPOJO> listaComponente) {
    this.listaComponente = listaComponente;
  }

  public List<JSPomcaPOJO> getListaComponente() {
    return listaComponente;
  }


  public void setAutoridad(RichSelectOneChoice autoridad) {
    this.autoridad = autoridad;
  }

  public RichSelectOneChoice getAutoridad() {
    return autoridad;
  }


  public void setAutoridadSelectItems(List<SelectItem> autoridadSelectItems) {
    this.autoridadSelectItems = autoridadSelectItems;
  }

  public List<SelectItem> getAutoridadSelectItems() {
    return autoridadSelectItems;
  }

  public void setAutoridadList(List<ArrayList> autoridadList) {
    this.autoridadList = autoridadList;
  }

  public List<ArrayList> getAutoridadList() {
    return autoridadList;
  }

  public void setCriterios(CriteriosBusquedaExternasJSTO criterios) {
    this.criterios = criterios;
  }

  public CriteriosBusquedaExternasJSTO getCriterios() {
    return criterios;
  }


  public void setSistemaAcuiferoFilter(String sistemaAcuiferoFilter) {
    this.sistemaAcuiferoFilter = sistemaAcuiferoFilter;
  }

  public String getSistemaAcuiferoFilter() {
    return sistemaAcuiferoFilter;
  }
}
