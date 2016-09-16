package co.gov.ideam.sirh.porh.view;

import co.gov.ideam.sirh.calidad.model.PuntoMonitoreo;
import co.gov.ideam.sirh.calidad.view.CalidadDelegate;
import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.fuentes.model.FnttTramo;
import co.gov.ideam.sirh.fuentes.model.FnttTramoAfluente;
import co.gov.ideam.sirh.fuentes.view.FuenteDelegate;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.usuarios.agua.view.UsuariosAguaDelegate;
import co.gov.ideam.sirh.util.Constantes;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UISelectItems;
import javax.faces.event.ActionEvent;

import javax.swing.JPanel;

import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichMenu;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichSelectManyListbox;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.nav.RichCommandMenuItem;
import oracle.adf.view.rich.component.rich.output.RichOutputLabel;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
import oracle.adf.view.rich.component.rich.output.RichSpacer;

import org.apache.myfaces.trinidad.event.SelectionEvent;

public class TramoAfluentes extends StandarBean {


  private FnttTramo tramo;
  private RichPopup p_registro_exitoso;
  private RichPanelGroupLayout pgl3;
  private RichCommandButton cbAceptar;
  //Afluentes
  private RichCommandMenuItem cmi_usos_calidad;
  private RichCommandMenuItem cmi_conflicto;
  //Cambio 1 - Fuentes relacionadas
  private RichSpacer spacerFuentesRelacionadas;
  private RichPanelBox panelBoxFuentesRelacionadas;
  private RichPanelGroupLayout panelGroupLayoutFuentesRelacionadas;
  private RichPanelFormLayout panelFormLayoutFuentesRelacionadas;
  private RichSelectManyListbox smlFuentes;
  private List listaFuentesDisponibles;
  private List<FnttFuente> listaFuentesDisponiblesSeleccionadas;
  private UISelectItems selectItemsFuentesDisponibles;
  private RichSpacer spacerFuentesRelacionadasFinal;
  protected UsuarioConectadoTO usuarioConectado;
  //Fin Cambio 1 - Fuentes relacionadas


  
  private RichDocument documentPuntosMonitoreo;
  private RichForm formPuntosMonitoreo;
  private RichPanelStretchLayout panelStretchLayoutPuntosMonitoreo;
  private RichPanelBox panelBoxPuntosMonitoreo;
  private RichPanelGroupLayout panelGroupLayoutPuntosMonitoreo;
  private RichPanelFormLayout panelFormLayoutPuntosMonitoreo;
  private RichSpacer spacerPuntosMonitoreo;
  
//Miga de pan
  private RichPanelGroupLayout pgl4;
  private RichCommandLink commandLink1;
  private RichCommandLink commandLink2;


  public TramoAfluentes() {
    FacesUtils.setActiveBean("tramoAfluentes", "tramoAfluentes",
                             UsuariosAguaDelegate.class);
    this.load();
  }

  public void usuarioConectado() {
    this.usuarioConectado =
        (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
  }


  public void load() {
    try {
      usuarioConectado();
      tramo =
        (FnttTramo)FacesUtils.getFromSession("tramoSeleccionado");

      //afluentes
      //Cambio 1 - Cargamos la lista de fuentes relacionadas
      cargarFuentes();
      cargarAfluentes();

    } catch (IdeamException e) {
      showMessage(e);
    }
  }

  public void cargarFuentes() throws IdeamException {
    this.listaFuentesDisponibles = new ArrayList();
    FuenteDelegate fd = FuenteDelegate.getInstance();
    this.listaFuentesDisponibles =
        this.getListaFuentes(this.usuarioConectado.getUsuario().getAutoridadAmbiental());

  }

  public void cargarAfluentes() throws IdeamException {
    this.listaFuentesDisponiblesSeleccionadas = new ArrayList();

    FuenteDelegate fd = FuenteDelegate.getInstance();
    List<FnttTramoAfluente> asociadas = fd.getAllAfluentesByTramo(this.tramo);
    if (asociadas != null && !asociadas.isEmpty()) {
      for (FnttTramoAfluente afluente : asociadas) {
        FnttFuente fuente = fd.getFuente(afluente.getIdFuente());
        this.listaFuentesDisponiblesSeleccionadas.add(fuente);
      }
    }

  }

  //Fin Cambio 1 - Cargamos la lista de fuentes relacionadas


  public void save() {
    try {

      //Cambio 1 - Cargamos la lista de fuentes relacionadas
      ArrayList lista = new ArrayList();
      Iterator it = ((ArrayList)this.smlFuentes.getValue()).iterator();
      while (it.hasNext()) {
        FnttFuente fnttFuenteIterada = (FnttFuente)it.next();
        FnttTramoAfluente fta = new FnttTramoAfluente();
        fta.setFuente(fnttFuenteIterada);
        fta.setTramo(this.tramo);
        fta.setIdFuente(fnttFuenteIterada.getId());
        fta.setIdTramo(this.tramo.getId());
        lista.add(fta);
      }

      FuenteDelegate fd = FuenteDelegate.getInstance();
      
      List<FnttTramoAfluente> asociadas =
      fd.getAllAfluentesByTramo(this.tramo);
      fd.deleteArray(asociadas.toArray());
      fd.saveOrUpdateArray(lista.toArray());
      //Fin - Cambio 1 - Cargamos la lista de fuentes relacionadas

      String params[] = { "de los afluentes" };
      showMessage(getText("mensaje_registro_exitoso", params),
                  FacesMessage.SEVERITY_INFO);
    } catch (IdeamException e) {
      showMessage(e);
    }
  }
  
  
  public void cbAceptar_actionListener(ActionEvent actionEvent) {
          boolean continuar = true;
         save(); 
  }


  public void setTramo(FnttTramo tramo) {
    this.tramo = tramo;
  }

  public FnttTramo getTramo() {
    return tramo;
  }

  public void setP_registro_exitoso(RichPopup p_registro_exitoso) {
    this.p_registro_exitoso = p_registro_exitoso;
  }

  public RichPopup getP_registro_exitoso() {
    return p_registro_exitoso;
  }

  public void setCmi_usos_calidad(RichCommandMenuItem cmi_usos_calidad) {
    this.cmi_usos_calidad = cmi_usos_calidad;
  }

  public RichCommandMenuItem getCmi_usos_calidad() {
    return cmi_usos_calidad;
  }

  public void setCmi_conflicto(RichCommandMenuItem cmi_conflicto) {
    this.cmi_conflicto = cmi_conflicto;
  }

  public RichCommandMenuItem getCmi_conflicto() {
    return cmi_conflicto;
  }

  public void setSpacerFuentesRelacionadas(RichSpacer spacerFuentesRelacionadas) {
    this.spacerFuentesRelacionadas = spacerFuentesRelacionadas;
  }

  public RichSpacer getSpacerFuentesRelacionadas() {
    return spacerFuentesRelacionadas;
  }

  public void setPanelBoxFuentesRelacionadas(RichPanelBox panelBoxFuentesRelacionadas) {
    this.panelBoxFuentesRelacionadas = panelBoxFuentesRelacionadas;
  }

  public RichPanelBox getPanelBoxFuentesRelacionadas() {
    return panelBoxFuentesRelacionadas;
  }

  public void setPanelGroupLayoutFuentesRelacionadas(RichPanelGroupLayout panelGroupLayoutFuentesRelacionadas) {
    this.panelGroupLayoutFuentesRelacionadas = panelGroupLayoutFuentesRelacionadas;
  }

  public RichPanelGroupLayout getPanelGroupLayoutFuentesRelacionadas() {
    return panelGroupLayoutFuentesRelacionadas;
  }

  public void setPanelFormLayoutFuentesRelacionadas(RichPanelFormLayout panelFormLayoutFuentesRelacionadas) {
    this.panelFormLayoutFuentesRelacionadas = panelFormLayoutFuentesRelacionadas;
  }

  public RichPanelFormLayout getPanelFormLayoutFuentesRelacionadas() {
    return panelFormLayoutFuentesRelacionadas;
  }

  public void setSmlFuentes(RichSelectManyListbox smlFuentes) {
    this.smlFuentes = smlFuentes;
  }

  public RichSelectManyListbox getSmlFuentes() {
    return smlFuentes;
  }

  public void setListaFuentesDisponibles(List listaFuentesDisponibles) {
    this.listaFuentesDisponibles = listaFuentesDisponibles;
  }

  public List getListaFuentesDisponibles() {
    return listaFuentesDisponibles;
  }

  public void setListaFuentesDisponiblesSeleccionadas(List<FnttFuente> listaFuentesDisponiblesSeleccionadas) {
    this.listaFuentesDisponiblesSeleccionadas = listaFuentesDisponiblesSeleccionadas;
  }

  public List<FnttFuente> getListaFuentesDisponiblesSeleccionadas() {
    return listaFuentesDisponiblesSeleccionadas;
  }

  public void setSelectItemsFuentesDisponibles(UISelectItems selectItemsFuentesDisponibles) {
    this.selectItemsFuentesDisponibles = selectItemsFuentesDisponibles;
  }

  public UISelectItems getSelectItemsFuentesDisponibles() {
    return selectItemsFuentesDisponibles;
  }

  public void setSpacerFuentesRelacionadasFinal(RichSpacer spacerFuentesRelacionadasFinal) {
    this.spacerFuentesRelacionadasFinal = spacerFuentesRelacionadasFinal;
  }

  public RichSpacer getSpacerFuentesRelacionadasFinal() {
    return spacerFuentesRelacionadasFinal;
  }

  public void setUsuarioConectado(UsuarioConectadoTO usuarioConectado) {
    this.usuarioConectado = usuarioConectado;
  }

  public UsuarioConectadoTO getUsuarioConectado() {
    return usuarioConectado;
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

  public void setPgl3(RichPanelGroupLayout pgl3) {
    this.pgl3 = pgl3;
  }

  public RichPanelGroupLayout getPgl3() {
    return pgl3;
  }

  public void setCbAceptar(RichCommandButton cbAceptar) {
    this.cbAceptar = cbAceptar;
  }

  public RichCommandButton getCbAceptar() {
    return cbAceptar;
  }

  public void setPgl4(RichPanelGroupLayout pgl4) {
    this.pgl4 = pgl4;
  }

  public RichPanelGroupLayout getPgl4() {
    return pgl4;
  }

  public void setCommandLink1(RichCommandLink commandLink1) {
    this.commandLink1 = commandLink1;
  }

  public RichCommandLink getCommandLink1() {
    return commandLink1;
  }

  public void setCommandLink2(RichCommandLink commandLink2) {
    this.commandLink2 = commandLink2;
  }

  public RichCommandLink getCommandLink2() {
    return commandLink2;
  }
}
