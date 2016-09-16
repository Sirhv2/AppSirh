package co.gov.ideam.sirh.estadisticas.view;



import co.gov.ideam.sirh.view.StandarBean;
import javax.faces.event.ActionEvent;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.output.RichImage;
import oracle.adf.view.rich.component.rich.output.RichSpacer;


public class EnlacesTramitesBean extends StandarBean{
    private RichPanelStretchLayout panelStretchLayout1;
    private RichCommandLink clink1;
    private RichPanelGroupLayout panelGroupLayout1;
    private RichCommandLink clink2;
    private RichPanelFormLayout panelFormLayout2;
    private RichPanelFormLayout panelFormLayout3;
    private RichImage imgLink1;
    private RichImage imgLink2;
    private String accionLink1;
    private RichImage image1;
    private RichSpacer spacer1;
    private RichSpacer spacer2;

    public EnlacesTramitesBean(){
      
         
    }


    public void clink1_actionListener(ActionEvent actionEvent) {
       accionLink1= "dashBoardConcesionesAnio";
    }
    
    
    public void clink2_actionListener(ActionEvent actionEvent) {
        accionLink1= "dashBoardPermisosAnio"; 
    }
    
    
  
 
    public void setPanelStretchLayout1(RichPanelStretchLayout panelStretchLayout1) {
        this.panelStretchLayout1 = panelStretchLayout1;
    }

    public RichPanelStretchLayout getPanelStretchLayout1() {
        return panelStretchLayout1;
    }



    public void setClink1(RichCommandLink clink1) {
        this.clink1 = clink1;
    }

    public RichCommandLink getClink1() {
        return clink1;
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

    public void setClink2(RichCommandLink commandLink1) {
        this.clink2 = commandLink1;
    }

    public RichCommandLink getClink2() {
        return clink2;
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


    public void setImage1(RichImage image1) {
        this.image1 = image1;
    }

    public RichImage getImage1() {
        return image1;
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
}
