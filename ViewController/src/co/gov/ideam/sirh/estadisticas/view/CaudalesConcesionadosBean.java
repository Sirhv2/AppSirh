package co.gov.ideam.sirh.estadisticas.view;


import co.gov.ideam.sirh.seguridad.model.PerfilVO;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.seguridad.view.SeguridadDelegate;
import co.gov.ideam.sirh.util.ConstantesCalidad;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;
import javax.faces.event.ActionEvent;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.output.RichImage;


public class CaudalesConcesionadosBean extends StandarBean{
    private RichPanelStretchLayout panelStretchLayout1;
    private RichCommandLink clink1;
    private RichPanelGroupLayout panelGroupLayout1;
    private RichPanelFormLayout panelFormLayout2;
    private RichImage imgLink1;
    private String accionLink1;


    public CaudalesConcesionadosBean(){
      
         
    }


    public void clink1_actionListener(ActionEvent actionEvent) {
      
        UsuarioConectadoTO usuarioConectado = new UsuarioConectadoTO();
        try{
              usuarioConectado = 
                    (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
       
            if(usuarioConectado==null){
             accionLink1= "dashBoardCaudales";
            }else{   
                       SeguridadDelegate seg = SeguridadDelegate.getInstance(); 
                        PerfilVO pp= new PerfilVO();
                        pp=   seg.getPerfilUsuario(usuarioConectado.getUsuario().getCodigo());
                            
                    if (pp.getCodigo()==ConstantesCalidad.PERFIL_CONSULTA){
                           accionLink1= "dashBoardCaudales";
                    }else{
                           accionLink1= "dashBoardCaudalesAutoridad";
                         }
                 } 
        }catch(Exception e){
            System.out.println("++++++++++++++++e"+e);
        }
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


    public void setImgLink1(RichImage imgLink1) {
        this.imgLink1 = imgLink1;
    }

    public RichImage getImgLink1() {
        return imgLink1;
    }



    public void setAccionLink1(String accionLink1) {
        this.accionLink1 = accionLink1;
    }

    public String getAccionLink1() {
        return accionLink1;
    }


}
