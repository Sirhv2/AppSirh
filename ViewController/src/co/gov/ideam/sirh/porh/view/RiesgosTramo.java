package co.gov.ideam.sirh.porh.view;

import co.gov.ideam.sirh.calidad.model.PuntoMonitoreo;
import co.gov.ideam.sirh.calidad.view.CalidadDelegate;
import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.fuentes.model.FnttTramo;
import co.gov.ideam.sirh.fuentes.model.FnttTramoAfluente;
import co.gov.ideam.sirh.fuentes.model.FnttTramoRiesgos;
import co.gov.ideam.sirh.fuentes.view.FuenteDelegate;
import co.gov.ideam.sirh.parametros.model.Categoria;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.view.ParametrosDelegate;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.usuarios.agua.view.UsuariosAguaDelegate;
import co.gov.ideam.sirh.util.Constantes;
import co.gov.ideam.sirh.util.ConstantesCalidad;
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
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectManyListbox;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.nav.RichCommandMenuItem;
import oracle.adf.view.rich.component.rich.output.RichOutputLabel;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
import oracle.adf.view.rich.component.rich.output.RichSpacer;

import org.apache.myfaces.trinidad.event.SelectionEvent;

public class RiesgosTramo extends StandarBean {


  private FnttTramo tramo;
  private FnttTramoRiesgos tramoRiesgos;
  private RichPopup p_registro_exitoso;
  private RichPanelGroupLayout pgl3;
  private RichCommandButton cbAceptar;
  
  private RichCommandMenuItem cmi_usos_calidad;
  private RichCommandMenuItem cmi_conflicto;
  //Cambio 1 - Fuentes relacionadas
  private RichSpacer spacerFuentesRelacionadas;

  protected UsuarioConectadoTO usuarioConectado;
  //Fin Cambio 1 - Fuentes relacionadas

  private RichDocument documentPuntosMonitoreo;
  private RichForm formPuntosMonitoreo;
  private RichPanelStretchLayout panelStretchLayoutPuntosMonitoreo;
  private RichPanelBox panelBoxPuntosMonitoreo;
  private RichPanelGroupLayout panelGroupLayoutPuntosMonitoreo;
  private RichPanelFormLayout panelFormLayoutPuntosMonitoreo;
  private RichSpacer spacerPuntosMonitoreo;
  private RichOutputText ot8;
  
  //Riesgos
  private RichSelectOneChoice reduccionOferta;
  private UISelectItems selectItemsreduccionOferta;
  private List listareduccionOferta;
  
  private RichSelectOneChoice disponibilidad;
  private UISelectItems selectItemsDisponibilidad;
  private List listaDisponibilidad;
  
  //Miga de pan
  private RichPanelGroupLayout pgl4;
  private RichCommandLink commandLink1;
  private RichCommandLink commandLink2;


  public RiesgosTramo() {
    FacesUtils.setActiveBean("riesgosTramo", "riesgosTramo",
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
      tramo = (FnttTramo)FacesUtils.getFromSession("tramoSeleccionado");
      FnttFuente fuente =
        (FnttFuente)FacesUtils.getFromSession("fuenteSeleccionada");
      FuenteDelegate fd = FuenteDelegate.getInstance();
      disponibilidad = new RichSelectOneChoice();
      reduccionOferta = new RichSelectOneChoice();

      this.tramoRiesgos = fd.getTramoRiesgos(this.tramo.getId());  
      if(tramoRiesgos==null){
        tramoRiesgos =  new FnttTramoRiesgos();
        tramoRiesgos.setIdTramo(tramo.getId());
        tramoRiesgos.setIdFuente(fuente.getId());
      }else{
        Lista new1= new Lista();
        new1.setCodigo(tramoRiesgos.getRAsocDisponibilidad().intValue());
        new1.setValor("");
        tramoRiesgos.setListarAsocDisponibilidad(new1);
        this.disponibilidad.setValue(new1);
        Lista new2= new Lista();
        new2.setCodigo(tramoRiesgos.getRAsocReduccionOferta().intValue());
        new2.setValor("");
        tramoRiesgos.setListarAsocDisponibilidad(new2);
        this.reduccionOferta.setValue(new2);
        
      }

      //Inicio Sistema de coordenadas
      this.listareduccionOferta =
          this.getListaPorCategoria(Categoria.RIESGO_ASOCIADO_MEDICION);
      //Inicio Sistema de coordenadas
      this.listaDisponibilidad =
          this.getListaPorCategoria(Categoria.RIESGO_ASOCIADO_MEDICION);

    } catch (IdeamException e) {
      showMessage(e);
    }
  }


  public void save() {

    try {
      this.tramoRiesgos.setRAsocDisponibilidad(tramoRiesgos.getListarAsocDisponibilidad().getCodigo().longValue());
      this.tramoRiesgos.setRAsocReduccionOferta(tramoRiesgos.getListarAsocReduccionOferta().getCodigo().longValue());
      FuenteDelegate fd = FuenteDelegate.getInstance();
      fd.saveOrUpdate(tramoRiesgos);
      String params[] = { "de los riesgos" };
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

  public void setTramoRiesgos(FnttTramoRiesgos tramoRiesgos) {
    this.tramoRiesgos = tramoRiesgos;
  }

  public FnttTramoRiesgos getTramoRiesgos() {
    return tramoRiesgos;
  }

  public void setP_registro_exitoso(RichPopup p_registro_exitoso) {
    this.p_registro_exitoso = p_registro_exitoso;
  }

  public RichPopup getP_registro_exitoso() {
    return p_registro_exitoso;
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

  public void setOt8(RichOutputText ot8) {
    this.ot8 = ot8;
  }

  public RichOutputText getOt8() {
    return ot8;
  }

  public void setReduccionOferta(RichSelectOneChoice reduccionOferta) {
    this.reduccionOferta = reduccionOferta;
  }

  public RichSelectOneChoice getReduccionOferta() {
    return reduccionOferta;
  }

  public void setSelectItemsreduccionOferta(UISelectItems selectItemsreduccionOferta) {
    this.selectItemsreduccionOferta = selectItemsreduccionOferta;
  }

  public UISelectItems getSelectItemsreduccionOferta() {
    return selectItemsreduccionOferta;
  }

  public void setListareduccionOferta(List listareduccionOferta) {
    this.listareduccionOferta = listareduccionOferta;
  }

  public List getListareduccionOferta() {
    return listareduccionOferta;
  }

  public void setDisponibilidad(RichSelectOneChoice disponibilidad) {
    this.disponibilidad = disponibilidad;
  }

  public RichSelectOneChoice getDisponibilidad() {
    return disponibilidad;
  }

  public void setSelectItemsDisponibilidad(UISelectItems selectItemsDisponibilidad) {
    this.selectItemsDisponibilidad = selectItemsDisponibilidad;
  }

  public UISelectItems getSelectItemsDisponibilidad() {
    return selectItemsDisponibilidad;
  }

  public void setListaDisponibilidad(List listaDisponibilidad) {
    this.listaDisponibilidad = listaDisponibilidad;
  }

  public List getListaDisponibilidad() {
    return listaDisponibilidad;
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
