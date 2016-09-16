package co.gov.ideam.sirh.dashboard.view;

//import co.gov.ideam.sirh.calidad.model.AcuiferosPOJO;
import co.gov.ideam.sirh.calidad.model.AcuiferosPOJO2;
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
import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.model.Parametro;
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

public class DashBoardJSAcuiferosBean extends StandarDashBoard {

  private RichTable tableDatosGrafico;
 
  private List<AcuiferosPOJO2> listaComponente;
  private List<Object[]> listaDatosGraf;
  private UIGraph lineJSGraph;
  JSReportesDelegate reportesDelegate;
  private RichSelectOneChoice soc_filtro_tipo_id;
 // private List
  private List provinciaList;
  private List areaList;
  private RichSelectOneChoice provincia;
  private RichSelectOneChoice area;
  private List<SelectItem> provincias;
  private List<SelectItem> areas;
  private CriteriosBusquedaExternasJSTO criterios = new CriteriosBusquedaExternasJSTO();
  
  public DashBoardJSAcuiferosBean() throws IdeamException {
    this.load();
    }
  
  public  void refreshPage(ActionEvent actionEvent){
    this.listaComponente.clear();
    this.area = null;
    this.provincia= null;
     this.load();
     
  }
  public void load(){
    try{
      provinciaList = new ArrayList();
      areaList = new ArrayList();
      provincias = new ArrayList<SelectItem>();
      areas = new ArrayList<SelectItem>();
      reportesDelegate = JSReportesDelegate.getInstance();
      this.listaComponente = reportesDelegate.getConsultaAcuiferos();
      System.out.println("DashBoardAcuiferos -> " + listaComponente.size());
        for (int j = 0; j < this.listaComponente.size(); j++) {       
          //Listado Provincia
          if (!provinciaList.contains(listaComponente.get(j).getProvinciaHidroge())) {
            provinciaList.add(listaComponente.get(j).getProvinciaHidroge());
            provincias.add(new SelectItem(listaComponente.get(j).getProvinciaHidroge()));
          }
          
          if (!areaList.contains(listaComponente.get(j).getArea_hidrografica())) {
            areaList.add(listaComponente.get(j).getArea_hidrografica());
            areas.add(new SelectItem(listaComponente.get(j).getArea_hidrografica()));
          }

        }
    } catch(IdeamException ex){
      System.out.println("Error en load JSAcuiferosBean ->" + ex.getMessage());
      }
    }
  
  public void provincia_valueChangeListener(ValueChangeEvent valueChangeEvent) {
    Object provincia = valueChangeEvent.getNewValue();
    System.out.println("provincia_valueChangeListener ->");
    if (provincia != null) {
      criterios.setProvincia((String)provincia);
     // criterios.setArea(null);
    } else {
      criterios.setProvincia(null);
    }
    
    
   // area = null;
    
  //  AdfFacesContext.getCurrentInstance().addPartialTarget(this.area);
  }
  
  public void area_valueChangeListener(ValueChangeEvent valueChangeEvent) {
    Object area = valueChangeEvent.getNewValue();
    System.out.println("area_valueChangeListener ->");
    if (area != null) {
      criterios.setArea((String)area);
    //  criterios.setProvincia(null);
    } else {
      criterios.setArea(null);
    }
  //  provincia = null;
    //AdfFacesContext.getCurrentInstance().addPartialTarget(this.provincia);
  }
  
  
  public void cmdBuscar_actionListener(ActionEvent actionEvent) {
   
    try {

      this.reportesDelegate = JSReportesDelegate.getInstance();
      this.listaComponente.clear();       
      this.listaComponente =   reportesDelegate.getAcuiferosFiltros(criterios);    
     AdfFacesContext.getCurrentInstance().addPartialTarget(this.tableDatosGrafico);
     
    } catch (IdeamException e) {
      showMessage(e);
    }
  }
  
  /*public void cmdBuscar2_actionListener(ActionEvent actionEvent) {
   
    try {

      this.reportesDelegate = JSReportesDelegate.getInstance();
     

      this.listaComponente.clear();    
      this.criterios.setProvincia(null);
    
      this.listaComponente =   reportesDelegate.getAcuiferosFiltros(criterios);
    
     AdfFacesContext.getCurrentInstance().addPartialTarget(this.tableDatosGrafico);
    
    } catch (IdeamException e) {
      showMessage(e);
    }
  }*/
  
  public void salidaExcel_actionListener(ActionEvent actionEvent) {
  
          try {
              HashMap parametros = new HashMap();
              parametros.put("p_modulo", "Fichas Acuiferos");
              parametros.put("p_titulo", "Ficha acuiferos");
              this.generateReport("Listadoacuifero.jasper", this.listaComponente,
                                  parametros, ReporteDelegate.EXCEL);
          } catch (IdeamException e) {
              System.out.println("Fallo el descargue de  Excel ");
          }
          
  }
  
  
  public void cmi_imprimir_actionListener(ActionEvent actionEvent) {
      if (tableDatosGrafico.getSelectedRowData()==null){
          showMessage(getText("seleccionar_registro"),
                      FacesMessage.SEVERITY_ERROR);
          return;
      }
      AcuiferosPOJO2 acuiferos = (AcuiferosPOJO2)tableDatosGrafico.getSelectedRowData();
      
      try {
          HashMap parametros = new HashMap();
          parametros.put("p_modulo","Registro de Usuarios del Agua");
          parametros.put("p_titulo","Información del Usuario "); 
         // System.out.println("ID : " + acuiferos.getPlantillaID().toString());
          parametros.put("contexto", FacesUtils.getRealPath("/imgs/imagenes_acuiferos")) ;
          parametros.put("plantilla",acuiferos.getPlantillaID().toString()); 
               this.generateReport("ficha_acuifero.jasper", parametros, ReporteDelegate.PDF);
         
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


 
  public void setListaComponente(List<AcuiferosPOJO2> listaComponente) {
    this.listaComponente = listaComponente;
  }

  public List<AcuiferosPOJO2> getListaComponente() {
    return listaComponente;
  }

  public void setSoc_filtro_tipo_id(RichSelectOneChoice soc_filtro_tipo_id) {
    this.soc_filtro_tipo_id = soc_filtro_tipo_id;
  }

  public RichSelectOneChoice getSoc_filtro_tipo_id() {
    return soc_filtro_tipo_id;
  }


  public void setProvinciaList(List provinciaList) {
    this.provinciaList = provinciaList;
  }

  public List getProvinciaList() {
    return provinciaList;
  }

  public void setProvincia(RichSelectOneChoice provincia) {
    this.provincia = provincia;
  }

  public RichSelectOneChoice getProvincia() {
    return provincia;
  }


  public void setProvincias(List<SelectItem> provincias) {
    this.provincias = provincias;
  }

  public List<SelectItem> getProvincias() {
    return provincias;
  }


  public void setArea(RichSelectOneChoice area) {
    this.area = area;
  }

  public RichSelectOneChoice getArea() {
    return area;
  }


  public void setAreas(List<SelectItem> areas) {
    this.areas = areas;
  }

  public List<SelectItem> getAreas() {
    return areas;
  }


  public void setAreaList(List areaList) {
    this.areaList = areaList;
  }

  public List getAreaList() {
    return areaList;
  }
}
