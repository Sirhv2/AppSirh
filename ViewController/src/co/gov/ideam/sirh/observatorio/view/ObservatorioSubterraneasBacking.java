package co.gov.ideam.sirh.observatorio.view;

import co.gov.ideam.sirh.seguridad.view.SeguridadDelegate;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarDashBoard;

import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.layout.RichDecorativeBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelDashboard;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.output.RichImage;
import oracle.adf.view.rich.component.rich.output.RichSpacer;

public class ObservatorioSubterraneasBacking extends StandarDashBoard{
    private RichForm form1;
    private RichDocument document1;
    private RichPanelStretchLayout panelStretchLayout1;
    private RichDecorativeBox decorativeBox1;
    private RichPanelDashboard dashBoard;
    private RichPanelBox pbOferta;
    private RichPanelBox pbDemanda;
    private RichPanelBox pbCalidad;
    private RichPanelBox pbGestion;
    private RichPanelBox pbRiesgo;
    private RichImage image1;
    private RichImage image2;
    private RichImage image3;
    private RichImage image4;
    private RichImage image5;
    private RichPanelGroupLayout panelGroupLayout1;
    private RichPanelGroupLayout panelGroupLayout2;
    private RichPanelGroupLayout panelGroupLayout3;
    private RichPanelGroupLayout panelGroupLayout4;
    private RichPanelGroupLayout panelGroupLayout5;
    private RichCommandLink commandLink1;
    private RichCommandLink commandLink2;
    private RichCommandLink commandLink3;
    private RichCommandLink commandLink4;
    private RichCommandLink commandLink5;
    private RichCommandLink commandLink6;
    private RichCommandLink commandLink7;
    private RichCommandLink commandLink8;
    private RichCommandLink commandLink9;
    private RichCommandLink commandLink10;
    private RichInputText it1;
    private RichInputText it2;
    private RichInputText it3;
    private RichInputText it4;
    private RichInputText it5;
    private RichPanelBox panelBox1;
    private RichPanelGroupLayout panelGroupLayout6;
    private RichInputText textoinicio;
    private RichSpacer spacer1;

    public ObservatorioSubterraneasBacking() {
        FacesUtils.setActiveBean("observatorioSubterraneasBacking", "observatorioSubterraneasBacking",
                                 ObservatorioSubterraneasBacking.class);
        FacesUtils.removeFromSession("OrigenNavegacionUsuario");
        FacesUtils.setInSession("OrigenNavegacionUsuario","observatorio");
    }
    
    
    public void setForm1(RichForm form1) {
        this.form1 = form1;
    }

    public RichForm getForm1() {
        return form1;
    }

    public void setDocument1(RichDocument document1) {
        this.document1 = document1;
    }

    public RichDocument getDocument1() {
        return document1;
    }

    public void setPanelStretchLayout1(RichPanelStretchLayout panelStretchLayout1) {
        this.panelStretchLayout1 = panelStretchLayout1;
    }

    public RichPanelStretchLayout getPanelStretchLayout1() {
        return panelStretchLayout1;
    }

    public void setDecorativeBox1(RichDecorativeBox decorativeBox1) {
        this.decorativeBox1 = decorativeBox1;
    }

    public RichDecorativeBox getDecorativeBox1() {
        return decorativeBox1;
    }

    public void setDashBoard(RichPanelDashboard dashBoard) {
        this.dashBoard = dashBoard;
    }

    public RichPanelDashboard getDashBoard() {
        return dashBoard;
    }

    public void setPbOferta(RichPanelBox panelBox1) {
        this.pbOferta = panelBox1;
    }

    public RichPanelBox getPbOferta() {
        return pbOferta;
    }

    public void setPbDemanda(RichPanelBox panelBox1) {
        this.pbDemanda = panelBox1;
    }

    public RichPanelBox getPbDemanda() {
        return pbDemanda;
    }

    public void setPbCalidad(RichPanelBox panelBox1) {
        this.pbCalidad = panelBox1;
    }

    public RichPanelBox getPbCalidad() {
        return pbCalidad;
    }

    public void setPbGestion(RichPanelBox panelBox1) {
        this.pbGestion = panelBox1;
    }

    public RichPanelBox getPbGestion() {
        return pbGestion;
    }

    public void setPbRiesgo(RichPanelBox panelBox1) {
        this.pbRiesgo = panelBox1;
    }

    public RichPanelBox getPbRiesgo() {
        return pbRiesgo;
    }

    public void setImage1(RichImage image1) {
        this.image1 = image1;
    }

    public RichImage getImage1() {
        return image1;
    }

    public void setImage2(RichImage image2) {
        this.image2 = image2;
    }

    public RichImage getImage2() {
        return image2;
    }

    public void setImage3(RichImage image3) {
        this.image3 = image3;
    }

    public RichImage getImage3() {
        return image3;
    }

    public void setImage4(RichImage image4) {
        this.image4 = image4;
    }

    public RichImage getImage4() {
        return image4;
    }

    public void setImage5(RichImage image5) {
        this.image5 = image5;
    }

    public RichImage getImage5() {
        return image5;
    }

    public void setPanelGroupLayout1(RichPanelGroupLayout panelGroupLayout1) {
        this.panelGroupLayout1 = panelGroupLayout1;
    }

    public RichPanelGroupLayout getPanelGroupLayout1() {
        return panelGroupLayout1;
    }

    public void setPanelGroupLayout2(RichPanelGroupLayout panelGroupLayout2) {
        this.panelGroupLayout2 = panelGroupLayout2;
    }

    public RichPanelGroupLayout getPanelGroupLayout2() {
        return panelGroupLayout2;
    }

    public void setPanelGroupLayout3(RichPanelGroupLayout panelGroupLayout3) {
        this.panelGroupLayout3 = panelGroupLayout3;
    }

    public RichPanelGroupLayout getPanelGroupLayout3() {
        return panelGroupLayout3;
    }

    public void setPanelGroupLayout4(RichPanelGroupLayout panelGroupLayout4) {
        this.panelGroupLayout4 = panelGroupLayout4;
    }

    public RichPanelGroupLayout getPanelGroupLayout4() {
        return panelGroupLayout4;
    }

    public void setPanelGroupLayout5(RichPanelGroupLayout panelGroupLayout5) {
        this.panelGroupLayout5 = panelGroupLayout5;
    }

    public RichPanelGroupLayout getPanelGroupLayout5() {
        return panelGroupLayout5;
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

    public void setCommandLink3(RichCommandLink commandLink3) {
        this.commandLink3 = commandLink3;
    }

    public RichCommandLink getCommandLink3() {
        return commandLink3;
    }

    public void setCommandLink4(RichCommandLink commandLink4) {
        this.commandLink4 = commandLink4;
    }

    public RichCommandLink getCommandLink4() {
        return commandLink4;
    }

    public void setCommandLink5(RichCommandLink commandLink5) {
        this.commandLink5 = commandLink5;
    }

    public RichCommandLink getCommandLink5() {
        return commandLink5;
    }

  public void setCommandLink6(RichCommandLink commandLink6) {
      this.commandLink6 = commandLink6;
  }

  public RichCommandLink getCommandLink6() {
      return commandLink6;
  }
  
  public void setCommandLink7(RichCommandLink commandLink7) {
      this.commandLink7 = commandLink7;
  }

  public RichCommandLink getCommandLink7() {
      return commandLink7;
  }
  
  public void setCommandLink8(RichCommandLink commandLink8) {
      this.commandLink8 = commandLink8;
  }

  public RichCommandLink getCommandLink8() {
      return commandLink8;
  }
  public void setCommandLink9(RichCommandLink commandLink9) {
      this.commandLink9 = commandLink9;
  }

  public RichCommandLink getCommandLink9() {
      return commandLink9;
  }
  
  public void setCommandLink10(RichCommandLink commandLink10) {
    this.commandLink10 = commandLink10;
  }

  public RichCommandLink getCommandLink10() {
    return commandLink10;
  }

    public void setIt1(RichInputText outputText1) {
        this.it1 = outputText1;
    }

    public RichInputText getIt1() {
        return it1;
    }

    public void setIt2(RichInputText outputText2) {
        this.it2 = outputText2;
    }

    public RichInputText getIt2() {
        return it2;
    }

    public void setIt3(RichInputText outputText3) {
        this.it3 = outputText3;
    }

    public RichInputText getIt3() {
        return it3;
    }

    public void setIt4(RichInputText outputText4) {
        this.it4 = outputText4;
    }

    public RichInputText getIt4() {
        return it4;
    }

    public void setIt5(RichInputText outputText5) {
        this.it5 = outputText5;
    }

    public RichInputText getIt5() {
        return it5;
    }


    public void setPanelBox1(RichPanelBox panelBox1) {
        this.panelBox1 = panelBox1;
    }

    public RichPanelBox getPanelBox1() {
        return panelBox1;
    }

    public void setPanelGroupLayout6(RichPanelGroupLayout panelGroupLayout6) {
        this.panelGroupLayout6 = panelGroupLayout6;
    }

    public RichPanelGroupLayout getPanelGroupLayout6() {
        return panelGroupLayout6;
    }

    public void setTextoinicio(RichInputText inputText1) {
        this.textoinicio = inputText1;
    }

    public RichInputText getTextoinicio() {
        return textoinicio;
    }


    public void setSpacer1(RichSpacer spacer1) {
        this.spacer1 = spacer1;
    }

    public RichSpacer getSpacer1() {
        return spacer1;
    }


}
