package co.gov.ideam.sirh.porh.view;

import co.gov.ideam.sirh.calidad.model.PuntoMonitoreo;
import co.gov.ideam.sirh.calidad.view.CalidadDelegate;
import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.fuentes.model.FnttTramo;
import co.gov.ideam.sirh.usuarios.agua.view.UsuariosAguaDelegate;
import co.gov.ideam.sirh.util.Constantes;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;

import javax.swing.JPanel;

import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichMenu;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandMenuItem;
import oracle.adf.view.rich.component.rich.output.RichOutputLabel;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
import oracle.adf.view.rich.component.rich.output.RichSpacer;

import org.apache.myfaces.trinidad.event.SelectionEvent;

public class PuntosMonitoreoBean extends StandarBean {


  //PuntosMonitoreo

  private RichDocument documentPuntosMonitoreo;
  private RichForm formPuntosMonitoreo;
  private RichPanelStretchLayout panelStretchLayoutPuntosMonitoreo;
  private RichPanelBox panelBoxPuntosMonitoreo;
  private RichPanelGroupLayout panelGroupLayoutPuntosMonitoreo;
  private RichPanelFormLayout panelFormLayoutPuntosMonitoreo;
  private RichSpacer spacerPuntosMonitoreo;
  private RichOutputLabel outputLabelPuntosMonitoreo;
  private RichPanelCollection panelCollectionPuntosMonitoreo;
  private RichMenu menuPuntosMonitoreo;
  private RichCommandMenuItem cmi_oferta;
  private RichCommandMenuItem cmi_demanda;
  private RichCommandMenuItem cmi_indices_calidad_cantidad_hidrica;
  private RichCommandMenuItem cmi_indicadores;
  private String accion;
  private List<PuntoMonitoreo> listaPuntosMonitoreo;
  private RichTable tablePuntosMonitoreo;
  private PuntoMonitoreo puntoMonitoreoSeleccionado;
  private  FnttTramo tramo ;


  public PuntosMonitoreoBean() {
    FacesUtils.setActiveBean("puntosMonitoreo", "puntosMonitoreo",
                             UsuariosAguaDelegate.class);
    this.load();
  }

  public void load() {
    try {
     tramo =
        (FnttTramo)FacesUtils.getFromSession("tramoSeleccionado");
      FnttFuente fuente =
        (FnttFuente)FacesUtils.getFromSession("fuenteSeleccionada");
      CalidadDelegate cald = CalidadDelegate.getInstance();
      this.listaPuntosMonitoreo = cald.getPuntosMonitoreo(tramo);
    } catch (IdeamException e) {
      showMessage(e);
    }
  }
  
  
    public void cmi_oferta_actionListener(ActionEvent actionEvent) {
        setAccion("");
        if (this.puntoMonitoreoSeleccionado==null){
            showMessage(getText("seleccionar_registro"),
                        FacesMessage.SEVERITY_ERROR);
            return;
        }
      FacesUtils.setInSession("puntoMonitoreoSeleccionado", this.puntoMonitoreoSeleccionado);    
      FacesUtils.setInSession("renderCaseOfertaDemandaIndices", "Oferta"); 
      
      accion = "ofertaDemanda";
    }
    
    public void cmi_demanda_actionListener(ActionEvent actionEvent) {
        setAccion("");
        if (this.puntoMonitoreoSeleccionado==null){
            showMessage(getText("seleccionar_registro"),
                        FacesMessage.SEVERITY_ERROR);
            return;
        }
      FacesUtils.setInSession("renderCaseOfertaDemandaIndices", "Demanda"); 
      FacesUtils.setInSession("puntoMonitoreoSeleccionado", this.puntoMonitoreoSeleccionado);                
      accion = "ofertaDemanda";
    }
    
  public void cmi_indices_actionListener(ActionEvent actionEvent) {
      setAccion("");
      if (this.puntoMonitoreoSeleccionado==null){
          showMessage(getText("seleccionar_registro"),
                      FacesMessage.SEVERITY_ERROR);
          return;
      }
    FacesUtils.setInSession("renderCaseOfertaDemandaIndices", "Indices"); 
    FacesUtils.setInSession("puntoMonitoreoSeleccionado", this.puntoMonitoreoSeleccionado);                
    accion = "ofertaDemanda";
  }
  
      
    
    public void cmi_indicadores_actionListener(ActionEvent actionEvent) {
        setAccion("");
        if (this.puntoMonitoreoSeleccionado==null){
            showMessage(getText("seleccionar_registro"),
                        FacesMessage.SEVERITY_ERROR);
            return;
        }

      FacesUtils.setInSession("puntoMonitoreoSeleccionado", this.puntoMonitoreoSeleccionado);        
        accion = "indicadores";
    }   
    
  public void tablePuntosMonitoreoSelectionListener(SelectionEvent selectionEvent) {
      RichTable t_tramos = (RichTable)selectionEvent.getSource();
      this.puntoMonitoreoSeleccionado = (PuntoMonitoreo)t_tramos.getSelectedRowData();
  }

  public void setDocumentPuntosMonitoreo(RichDocument documentPuntosMonitoreo) {
    this.documentPuntosMonitoreo = documentPuntosMonitoreo;
  }

  public RichDocument getDocumentPuntosMonitoreo() {
    return documentPuntosMonitoreo;
  }

  public void setFormPuntosMonitoreo(RichForm formPuntosMonitoreo) {
    this.formPuntosMonitoreo = formPuntosMonitoreo;
  }

  public RichForm getFormPuntosMonitoreo() {
    return formPuntosMonitoreo;
  }

  public void setPanelStretchLayoutPuntosMonitoreo(RichPanelStretchLayout panelStretchLayoutPuntosMonitoreo) {
    this.panelStretchLayoutPuntosMonitoreo = panelStretchLayoutPuntosMonitoreo;
  }

  public RichPanelStretchLayout getPanelStretchLayoutPuntosMonitoreo() {
    return panelStretchLayoutPuntosMonitoreo;
  }

  public void setPanelBoxPuntosMonitoreo(RichPanelBox panelBoxPuntosMonitoreo) {
    this.panelBoxPuntosMonitoreo = panelBoxPuntosMonitoreo;
  }

  public RichPanelBox getPanelBoxPuntosMonitoreo() {
    return panelBoxPuntosMonitoreo;
  }

  public void setPanelGroupLayoutPuntosMonitoreo(RichPanelGroupLayout panelGroupLayoutPuntosMonitoreo) {
    this.panelGroupLayoutPuntosMonitoreo = panelGroupLayoutPuntosMonitoreo;
  }

  public RichPanelGroupLayout getPanelGroupLayoutPuntosMonitoreo() {
    return panelGroupLayoutPuntosMonitoreo;
  }

  public void setPanelFormLayoutPuntosMonitoreo(RichPanelFormLayout panelFormLayoutPuntosMonitoreo) {
    this.panelFormLayoutPuntosMonitoreo = panelFormLayoutPuntosMonitoreo;
  }

  public RichPanelFormLayout getPanelFormLayoutPuntosMonitoreo() {
    return panelFormLayoutPuntosMonitoreo;
  }

  public void setSpacerPuntosMonitoreo(RichSpacer spacerPuntosMonitoreo) {
    this.spacerPuntosMonitoreo = spacerPuntosMonitoreo;
  }

  public RichSpacer getSpacerPuntosMonitoreo() {
    return spacerPuntosMonitoreo;
  }

  public void setOutputLabelPuntosMonitoreo(RichOutputLabel outputLabelPuntosMonitoreo) {
    this.outputLabelPuntosMonitoreo = outputLabelPuntosMonitoreo;
  }

  public RichOutputLabel getOutputLabelPuntosMonitoreo() {
    return outputLabelPuntosMonitoreo;
  }

  public void setPanelCollectionPuntosMonitoreo(RichPanelCollection panelCollectionPuntosMonitoreo) {
    this.panelCollectionPuntosMonitoreo = panelCollectionPuntosMonitoreo;
  }

  public RichPanelCollection getPanelCollectionPuntosMonitoreo() {
    return panelCollectionPuntosMonitoreo;
  }

  public void setCmi_indicadores(RichCommandMenuItem cmi_indicadores) {
    this.cmi_indicadores = cmi_indicadores;
  }

  public RichCommandMenuItem getCmi_indicadores() {
    return cmi_indicadores;
  }

  public void setAccion(String accion) {
    this.accion = accion;
  }

  public String getAccion() {
    return accion;
  }

  public void setListaPuntosMonitoreo(List<PuntoMonitoreo> listaPuntosMonitoreo) {
    this.listaPuntosMonitoreo = listaPuntosMonitoreo;
  }

  public List<PuntoMonitoreo> getListaPuntosMonitoreo() {
    return listaPuntosMonitoreo;
  }

  public void setTablePuntosMonitoreo(RichTable tablePuntosMonitoreo) {
    this.tablePuntosMonitoreo = tablePuntosMonitoreo;
  }

  public RichTable getTablePuntosMonitoreo() {
    return tablePuntosMonitoreo;
  }

  public void setPuntoMonitoreoSeleccionado(PuntoMonitoreo puntoMonitoreoSeleccionado) {
    this.puntoMonitoreoSeleccionado = puntoMonitoreoSeleccionado;
  }

  public PuntoMonitoreo getPuntoMonitoreoSeleccionado() {
    return puntoMonitoreoSeleccionado;
  }

  public void setMenuPuntosMonitoreo(RichMenu menuPuntosMonitoreo) {
    this.menuPuntosMonitoreo = menuPuntosMonitoreo;
  }

  public RichMenu getMenuPuntosMonitoreo() {
    return menuPuntosMonitoreo;
  }

  public void setCmi_oferta(RichCommandMenuItem cmi_oferta) {
    this.cmi_oferta = cmi_oferta;
  }

  public RichCommandMenuItem getCmi_oferta() {
    return cmi_oferta;
  }

  public void setCmi_demanda(RichCommandMenuItem cmi_demanda) {
    this.cmi_demanda = cmi_demanda;
  }

  public RichCommandMenuItem getCmi_demanda() {
    return cmi_demanda;
  }


  public void setCmi_indices_calidad_cantidad_hidrica(RichCommandMenuItem cmi_indices_calidad_cantidad_hidrica) {
    this.cmi_indices_calidad_cantidad_hidrica = cmi_indices_calidad_cantidad_hidrica;
  }

  public RichCommandMenuItem getCmi_indices_calidad_cantidad_hidrica() {
    return cmi_indices_calidad_cantidad_hidrica;
  }


    public void setTramo(FnttTramo tramo) {
        this.tramo = tramo;
    }

    public FnttTramo getTramo() {
        return tramo;
    }
}

