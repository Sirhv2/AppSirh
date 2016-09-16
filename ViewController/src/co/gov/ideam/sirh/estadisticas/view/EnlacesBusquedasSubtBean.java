package co.gov.ideam.sirh.estadisticas.view;

import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;

import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.output.RichImage;
import oracle.adf.view.rich.component.rich.output.RichSpacer;

public class EnlacesBusquedasSubtBean extends StandarBean {
  private RichPanelStretchLayout panelStretchLayout1;

  private RichCommandLink clink3;
  private RichPanelGroupLayout panelGroupLayout1;
  private RichPanelFormLayout panelFormLayout2;
  private RichPanelFormLayout panelFormLayout3;
  private RichPanelFormLayout panelFormGraf3;
  private RichPanelFormLayout panelFormLayout4;
  private RichPanelFormLayout panelFormLayout5;

  private RichImage imgLink1;
  private RichImage imgLink2;
  private RichImage imgLink4;
  private RichImage imgLink5;
  private String accionLink1;

  private RichImage image1;

  private RichSpacer spacer1;
  private RichSpacer spacer2;
  private RichSpacer spacer3;
  private RichSpacer spacer4;

  public EnlacesBusquedasSubtBean() {


  }


  public void clink3_actionListener(ActionEvent actionEvent) {
    Integer i = 3;
    FacesUtils.setInSession("tipoBusqueda", i);
    accionLink1 = "busqFuentesZonif";
  }

  public void setPanelStretchLayout1(RichPanelStretchLayout panelStretchLayout1) {
    this.panelStretchLayout1 = panelStretchLayout1;
  }

  public RichPanelStretchLayout getPanelStretchLayout1() {
    return panelStretchLayout1;
  }


  public void setPanelGroupLayout1(RichPanelGroupLayout panelGroupLayout1) {
    this.panelGroupLayout1 = panelGroupLayout1;
  }

  public RichPanelGroupLayout getPanelGroupLayout1() {
    return panelGroupLayout1;
  }

  public void setPanelFormLayout2(RichPanelFormLayout panelFormLayout2) {
    this.panelFormLayout2 = panelFormLayout2;
  }

  public RichPanelFormLayout getPanelFormLayout2() {
    return panelFormLayout2;
  }

  public void setPanelFormLayout3(RichPanelFormLayout panelFormLayout3) {
    this.panelFormLayout3 = panelFormLayout3;
  }

  public RichPanelFormLayout getPanelFormLayout3() {
    return panelFormLayout3;
  }


  public void setImgLink1(RichImage imgLink1) {
    this.imgLink1 = imgLink1;
  }

  public RichImage getImgLink1() {
    return imgLink1;
  }

  public void setImgLink2(RichImage imgLink2) {
    this.imgLink2 = imgLink2;
  }

  public RichImage getImgLink2() {
    return imgLink2;
  }

  public void setAccionLink1(String accionLink1) {
    this.accionLink1 = accionLink1;
  }

  public String getAccionLink1() {
    return accionLink1;
  }

  public void setPanelFormGraf3(RichPanelFormLayout panelFormLayout1) {
    this.panelFormGraf3 = panelFormLayout1;
  }

  public RichPanelFormLayout getPanelFormGraf3() {
    return panelFormGraf3;
  }

  public void setImage1(RichImage image1) {
    this.image1 = image1;
  }

  public RichImage getImage1() {
    return image1;
  }

  public void setClink3(RichCommandLink commandLink1) {
    this.clink3 = commandLink1;
  }

  public RichCommandLink getClink3() {
    return clink3;
  }

  public void setSpacer1(RichSpacer spacer1) {
    this.spacer1 = spacer1;
  }

  public RichSpacer getSpacer1() {
    return spacer1;
  }

  public void setSpacer2(RichSpacer spacer2) {
    this.spacer2 = spacer2;
  }

  public RichSpacer getSpacer2() {
    return spacer2;
  }



  public void setPanelFormLayout4(RichPanelFormLayout panelFormLayout4) {
    this.panelFormLayout4 = panelFormLayout4;
  }

  public RichPanelFormLayout getPanelFormLayout4() {
    return panelFormLayout4;
  }

  public void setPanelFormLayout5(RichPanelFormLayout panelFormLayout5) {
    this.panelFormLayout5 = panelFormLayout5;
  }

  public RichPanelFormLayout getPanelFormLayout5() {
    return panelFormLayout5;
  }

  public void setImgLink4(RichImage imgLink4) {
    this.imgLink4 = imgLink4;
  }

  public RichImage getImgLink4() {
    return imgLink4;
  }

  public void setImgLink5(RichImage imgLink5) {
    this.imgLink5 = imgLink5;
  }

  public RichImage getImgLink5() {
    return imgLink5;
  }

  public void setSpacer3(RichSpacer spacer3) {
    this.spacer3 = spacer3;
  }

  public RichSpacer getSpacer3() {
    return spacer3;
  }

  public void setSpacer4(RichSpacer spacer4) {
    this.spacer4 = spacer4;
  }

  public RichSpacer getSpacer4() {
    return spacer4;
  }
}
