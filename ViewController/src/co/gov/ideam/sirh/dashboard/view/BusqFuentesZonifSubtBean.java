package co.gov.ideam.sirh.dashboard.view;

import co.gov.ideam.sirh.calidad.model.PuntoMonitoreo;
import co.gov.ideam.sirh.calidad.model.PuntoMonitoreoTO;
import co.gov.ideam.sirh.fuentes.model.CriteriosBusquedaTO;
import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.fuentes.view.FuenteDelegate;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.model.PartZonificListas;
import co.gov.ideam.sirh.parametros.view.ParametrosDelegate;
import co.gov.ideam.sirh.pomca.view.PomtPlanesBean;
import co.gov.ideam.sirh.porh.model.PorhPlanes;
import co.gov.ideam.sirh.porh.view.PorhDelegate;
import co.gov.ideam.sirh.reportes.view.ReporteDelegate;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.seguridad.view.SeguridadDelegate;
import co.gov.ideam.sirh.usuarios.agua.model.ConsultasExtFuentesTO;
import co.gov.ideam.sirh.usuarios.agua.model.CriteriosBusquedaExternasTO;
import co.gov.ideam.sirh.usuarios.agua.model.UsuarioAgua;
import co.gov.ideam.sirh.usuarios.agua.view.UsuariosAguaDelegate;
import co.gov.ideam.sirh.util.Constantes;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UISelectItems;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichMenu;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectManyChoice;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.nav.RichCommandMenuItem;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;

import org.apache.myfaces.trinidad.event.SelectionEvent;

public class BusqFuentesZonifSubtBean extends StandarBean {

  private List listaFuentes;

  private List listaArea;
  private List listaZona;
  private List listaSubzona;
  private List listaAutoridades;
  private List listaSignos;
  private RichForm formF1;
  private RichDocument documentF1;
  private RichPanelStretchLayout pslf1;
  private RichPanelSplitter psf1;
  private RichPanelFormLayout pflf1;
  private RichSelectOneChoice selectOneChoice1;
  private UISelectItems selectItems1;
  private RichSelectOneChoice selectOneChoice2;
  private UISelectItems selectItems2;
  private RichSelectOneChoice selectOneChoice3;
  private UISelectItems selectItems3;
  private RichPanelGroupLayout panelGroupLayout1;
  private RichPanelCollection panelCollection1;
  private RichCommandButton commandButton1;
  private RichPopup popupFuente;
  private RichTable t_fuentes;
  private ConsultasExtFuentesTO fuenteSeleccionada;
  private RichInputText it_fuente;
  private RichPanelGroupLayout panelGroupLayout2;
  private RichSelectOneChoice selectAutoridad;
  private UISelectItems selectItems4;
  private RichInputText itCaudalCap;
  private RichSelectOneChoice selectSigno;
  private UISelectItems selectItems5;
  private RichInputText itCaudalVert;
  private RichPanelFormLayout panelFormLayout1;
  private RichCommandLink link1;
  private RichSpacer spacer2;
  private String accionLink1;
  private RichMenu menu1;
  private RichCommandMenuItem cmi_imprimirDetalle;
  private String redireccionar;

  public BusqFuentesZonifSubtBean() {
    FacesUtils.setActiveBean("busqFuentesZonif", "busqFuentesZonif",
                             SeguridadDelegate.class);
    FacesUtils.removeManagedBeanFromSession("busqFuentesZonif");
    this.load();
  }

  public void load() {

    itCaudalVert = new RichInputText();
    itCaudalCap = new RichInputText();
    it_fuente = new RichInputText();
    selectOneChoice1 = new RichSelectOneChoice();
    this.selectOneChoice2 = new RichSelectOneChoice();
    this.selectOneChoice3 = new RichSelectOneChoice();
    this.selectAutoridad = new RichSelectOneChoice();
    this.selectSigno = new RichSelectOneChoice();

    try {
      Integer tb = 3;

      if (tb == 3) { //Autoridad

        System.out.println("area--------entro autorid Subt------------");
        this.selectAutoridad.setVisible(true);

        this.selectOneChoice1.setVisible(false);
        this.selectOneChoice2.setVisible(false);
        this.selectOneChoice3.setVisible(false);

        this.selectAutoridad.setRendered(true);


        this.selectOneChoice1.setRendered(false);
        this.selectOneChoice2.setRendered(false);
        this.selectOneChoice3.setRendered(false);
        this.it_fuente.setRendered(false);
        this.itCaudalCap.setRendered(false);
        this.selectSigno.setRendered(false);
        this.itCaudalVert.setRendered(false);


        this.it_fuente.setVisible(false);
        this.itCaudalCap.setVisible(false);
        this.selectSigno.setVisible(false);
        this.itCaudalVert.setVisible(false);


      }

      this.listaArea = this.cargarZonificacionExt(null, Constantes.IDEAM);
      this.listaZona = new ArrayList();
      this.listaSubzona = new ArrayList();
      this.listaAutoridades = this.cargarListaAutoridades();
      this.listaSignos = this.cargarSigno();
      UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();


      listaFuentes = uad.getListaFuentesExternasSubt();
      System.out.println("area---------mmmmmmmmmmmmmmlis ---- mmmmmm------jjjjjj------------");


    } catch (IdeamException e) {
      showMessage(e);
    }
  }

  public void setSelectOneChoice1(RichSelectOneChoice selectOneChoice1) {
    this.selectOneChoice1 = selectOneChoice1;
  }

  public RichSelectOneChoice getSelectOneChoice1() {
    return selectOneChoice1;
  }

  public void setSelectItems1(UISelectItems selectItems1) {
    this.selectItems1 = selectItems1;
  }

  public UISelectItems getSelectItems1() {
    return selectItems1;
  }

  public void setFormF1(RichForm formF1) {
    this.formF1 = formF1;
  }

  public RichForm getFormF1() {
    return formF1;
  }

  public void setDocumentF1(RichDocument documentF1) {
    this.documentF1 = documentF1;
  }

  public RichDocument getDocumentF1() {
    return documentF1;
  }

  public void setPslf1(RichPanelStretchLayout pslf1) {
    this.pslf1 = pslf1;
  }

  public RichPanelStretchLayout getPslf1() {
    return pslf1;
  }

  public void setPsf1(RichPanelSplitter psf1) {
    this.psf1 = psf1;
  }

  public RichPanelSplitter getPsf1() {
    return psf1;
  }

  public void setPflf1(RichPanelFormLayout pflf1) {
    this.pflf1 = pflf1;
  }

  public RichPanelFormLayout getPflf1() {
    return pflf1;
  }

  public void setSelectOneChoice2(RichSelectOneChoice selectOneChoice2) {
    this.selectOneChoice2 = selectOneChoice2;
  }

  public RichSelectOneChoice getSelectOneChoice2() {
    return selectOneChoice2;
  }

  public void setSelectItems2(UISelectItems selectItems2) {
    this.selectItems2 = selectItems2;
  }

  public UISelectItems getSelectItems2() {
    return selectItems2;
  }

  public void setSelectOneChoice3(RichSelectOneChoice selectOneChoice3) {
    this.selectOneChoice3 = selectOneChoice3;
  }

  public RichSelectOneChoice getSelectOneChoice3() {
    return selectOneChoice3;
  }

  public void setSelectItems3(UISelectItems selectItems3) {
    this.selectItems3 = selectItems3;
  }

  public UISelectItems getSelectItems3() {
    return selectItems3;
  }


  public void setPanelGroupLayout1(RichPanelGroupLayout panelGroupLayout1) {
    this.panelGroupLayout1 = panelGroupLayout1;
  }

  public RichPanelGroupLayout getPanelGroupLayout1() {
    return panelGroupLayout1;
  }

  public void setPanelCollection1(RichPanelCollection panelCollection1) {
    this.panelCollection1 = panelCollection1;
  }

  public RichPanelCollection getPanelCollection1() {
    return panelCollection1;
  }

  public void setPopupFuente(RichPopup popupFuente) {
    this.popupFuente = popupFuente;
  }

  public RichPopup getPopupFuente() {
    return popupFuente;
  }

  public void selectOneChoice1_valueChangeListener(ValueChangeEvent event) throws IdeamException {

    Object area = event.getNewValue();
    this.listaZona = new ArrayList();
    try {
      if (area != null) {
        this.listaZona = new ArrayList();
        this.listaZona =
            this.cargarZonificacionExt(((PartZonificListas)area).getId(),
                                       Constantes.IDEAM);
      }
    } catch (IdeamException e) {
      showMessage(e);
    }
    AdfFacesContext.getCurrentInstance().addPartialTarget(selectOneChoice2);
  }

  public void selectOneChoice2_valueChangeListener(ValueChangeEvent event) throws IdeamException {

    Object zona = event.getNewValue();
    this.listaSubzona = new ArrayList();
    try {
      if (zona != null) {
        this.listaSubzona = new ArrayList();
        this.listaSubzona =
            this.cargarZonificacionExt(((PartZonificListas)zona).getId(),
                                       Constantes.IDEAM);
      }
    } catch (IdeamException e) {
      showMessage(e);
    }
    AdfFacesContext.getCurrentInstance().addPartialTarget(selectOneChoice3);
  }


  public void setCommandButton1(RichCommandButton commandButton1) {
    this.commandButton1 = commandButton1;
  }

  public RichCommandButton getCommandButton1() {
    return commandButton1;
  }

  public void setListaFuentes(List listaFuentes) {
    this.listaFuentes = listaFuentes;
  }

  public List getListaFuentes() {
    return listaFuentes;
  }


  public void setListaArea(List listaArea) {
    this.listaArea = listaArea;
  }

  public List getListaArea() {
    return listaArea;
  }

  public void setListaZona(List listaZona) {
    this.listaZona = listaZona;
  }

  public List getListaZona() {
    return listaZona;
  }

  public void setListaSubzona(List listaSubzona) {
    this.listaSubzona = listaSubzona;
  }

  public List getListaSubzona() {
    return listaSubzona;
  }


  //Buscar

  public void commandButton1_actionListener(ActionEvent actionEvent) throws IdeamException {
    //Busqueda
    CriteriosBusquedaExternasTO criterios = new CriteriosBusquedaExternasTO();
    this.listaFuentes = new ArrayList();
    int i = 0;


    if (this.selectOneChoice1.getValue() != null) {
      criterios.setArea((PartZonificListas)this.selectOneChoice1.getValue());
      i++;
    }
    if (this.selectOneChoice2.getValue() != null) {
      criterios.setZona((PartZonificListas)this.selectOneChoice2.getValue());
      i++;
    }
    if (this.selectOneChoice3.getValue() != null) {
      criterios.setSubzona((PartZonificListas)this.selectOneChoice3.getValue());
      i++;
    }

    if (this.selectAutoridad.getValue() != null) {
      criterios.setAutoridad((Autoridades)this.selectAutoridad.getValue());
      i++;
    }

    if (this.it_fuente.getValue() != null) {
      criterios.setNombreFuente(this.it_fuente.getValue().toString().toUpperCase());
      i++;
    }
    if (this.itCaudalCap.getValue() != null &&
        this.selectSigno.getValue() != null) {
      criterios.setCaudal(this.itCaudalCap.getValue().toString());
      criterios.setSigno(this.selectSigno.getValue().toString());
      criterios.setTipo_tramite("Concesiones/Captaciones");
      i++;
    }
    if (this.itCaudalVert.getValue() != null &&
        this.selectSigno.getValue() != null) {
      criterios.setCaudal(this.itCaudalVert.getValue().toString());
      criterios.setSigno(this.selectSigno.getValue().toString());
      criterios.setTipo_tramite("Permisos de Vertimientos/Vertimientos");
      i++;
    }
    try {

      UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();

      if (i == 0) { //solo presionó el botón buscar
        listaFuentes = uad.getListaFuentesExternas();

      } else { //realiza una busqueda
        this.listaFuentes =
            uad.getListaFuentesExternasSubt(criterios); //todas las fuentes

      }

      AdfFacesContext.getCurrentInstance().addPartialTarget(t_fuentes); //solo las fuentes de la autoridad
      i = 0;
    } catch (IdeamException e) {
      showMessage(e);
    }
  }


  public void cmiImprimir_actionListener(ActionEvent actionEvent) {
    // Add event code here...
    if (t_fuentes.getSelectedRowData() == null) {
      showMessage(getText("seleccionar_registro"),
                  FacesMessage.SEVERITY_ERROR);
      return;
    }
    ConsultasExtFuentesTO fuente =
      (ConsultasExtFuentesTO)t_fuentes.getSelectedRowData();
    try {
      HashMap parametros = new HashMap();
      parametros.put("p_modulo", "Detalle Fuente Hidríca");
      parametros.put("p_titulo", "Información de la Fuente");
      parametros.put("p_id_fuente", fuente.getId());
      this.generateReport("DetalleFuente.jasper", parametros,
                          ReporteDelegate.PDF);
    } catch (IdeamException e) {
      showMessage(e);
    }
  }


  public void setT_fuentes(RichTable table1) {
    this.t_fuentes = table1;
  }

  public RichTable getT_fuentes() {
    return t_fuentes;
  }

  public void t_fuentes_selectionListener(SelectionEvent selectionEvent) {
    RichTable t_fuentes1 = (RichTable)selectionEvent.getSource();
    this.fuenteSeleccionada =
        (ConsultasExtFuentesTO)t_fuentes1.getSelectedRowData();

    FacesUtils.removeFromSession("fuenteSeleccionada");
    FacesUtils.setInSession("fuenteSeleccionada", fuenteSeleccionada);
    System.out.println(">>>>>>>>> EVENTO FUENTE SELECCIONADA>>>>>>> " +
                       FacesUtils.getFromSession("fuenteSeleccionada"));

  }


  public void setIt_fuente(RichInputText inputText1) {
    this.it_fuente = inputText1;
  }

  public RichInputText getIt_fuente() {
    return it_fuente;
  }


  public void setPanelGroupLayout2(RichPanelGroupLayout panelGroupLayout2) {
    this.panelGroupLayout2 = panelGroupLayout2;
  }

  public RichPanelGroupLayout getPanelGroupLayout2() {
    return panelGroupLayout2;
  }


  public void setSelectAutoridad(RichSelectOneChoice selectOneChoice4) {
    this.selectAutoridad = selectOneChoice4;
  }

  public RichSelectOneChoice getSelectAutoridad() {
    return selectAutoridad;
  }

  public void setSelectItems4(UISelectItems selectItems4) {
    this.selectItems4 = selectItems4;
  }

  public UISelectItems getSelectItems4() {
    return selectItems4;
  }

  public void setListaAutoridades(List listaAutoridades) {
    this.listaAutoridades = listaAutoridades;
  }

  public List getListaAutoridades() {
    return listaAutoridades;
  }

  public void setItCaudalCap(RichInputText inputText1) {
    this.itCaudalCap = inputText1;
  }

  public RichInputText getItCaudalCap() {
    return itCaudalCap;
  }

  public void setSelectSigno(RichSelectOneChoice selectOneChoice4) {
    this.selectSigno = selectOneChoice4;
  }

  public RichSelectOneChoice getSelectSigno() {
    return selectSigno;
  }

  public void setSelectItems5(UISelectItems selectItems5) {
    this.selectItems5 = selectItems5;
  }

  public UISelectItems getSelectItems5() {
    return selectItems5;
  }

  public void setListaSignos(List listaSignos) {
    this.listaSignos = listaSignos;
  }

  public List getListaSignos() {
    return listaSignos;
  }

  public void setItCaudalVert(RichInputText inputText1) {
    this.itCaudalVert = inputText1;
  }

  public RichInputText getItCaudalVert() {
    return itCaudalVert;
  }

  public void setPanelFormLayout1(RichPanelFormLayout panelFormLayout1) {
    this.panelFormLayout1 = panelFormLayout1;
  }

  public RichPanelFormLayout getPanelFormLayout1() {
    return panelFormLayout1;
  }

  public void setLink1(RichCommandLink commandLink1) {
    this.link1 = commandLink1;
  }

  public RichCommandLink getLink1() {
    return link1;
  }

  public void setRedireccionar(String redireccionar) {
    this.redireccionar = redireccionar;
  }

  public String getRedireccionar() {
    return redireccionar;
  }

  public void redireccionarAOrigen(ActionEvent actionEvent) {

    String regla =
      (String)FacesUtils.getFromSession("OrigenNavegacionUsuario");
    System.out.println("regla de navegación:" + regla);
    if (regla.equals("observatorio"))
      redireccionar = "observatorio";
    else
      redireccionar = "dashBoard";
  }

  public void setSpacer2(RichSpacer spacer2) {
    this.spacer2 = spacer2;
  }

  public RichSpacer getSpacer2() {
    return spacer2;
  }


  public void setAccionLink1(String accionLink1) {
    this.accionLink1 = accionLink1;
  }

  public String getAccionLink1() {
    return accionLink1;
  }

  public void setMenu1(RichMenu menu1) {
    this.menu1 = menu1;
  }

  public RichMenu getMenu1() {
    return menu1;
  }

  public void setCmi_imprimirDetalle(RichCommandMenuItem commandMenuItem1) {
    this.cmi_imprimirDetalle = commandMenuItem1;
  }

  public RichCommandMenuItem getCmi_imprimirDetalle() {
    return cmi_imprimirDetalle;
  }

  public void setFuenteSeleccionada(ConsultasExtFuentesTO fuenteSeleccionada) {
    this.fuenteSeleccionada = fuenteSeleccionada;
  }

  public ConsultasExtFuentesTO getFuenteSeleccionada() {
    return fuenteSeleccionada;
  }
}
