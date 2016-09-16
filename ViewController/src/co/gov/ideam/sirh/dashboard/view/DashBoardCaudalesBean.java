package co.gov.ideam.sirh.dashboard.view;


import co.gov.ideam.sirh.archivos.model.CellTO;
import co.gov.ideam.sirh.archivos.model.ColumnTO;
import co.gov.ideam.sirh.archivos.model.RowTO;
import co.gov.ideam.sirh.calidad.view.CalidadDelegate;


import co.gov.ideam.sirh.fuentes.model.FnttFuente;

import co.gov.ideam.sirh.fuentes.view.FuenteDelegate;
import co.gov.ideam.sirh.porh.model.PorhPlanes;
import co.gov.ideam.sirh.porh.view.PorhDelegate;
import co.gov.ideam.sirh.seguridad.model.PerfilVO;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.seguridad.view.SeguridadDelegate;
import co.gov.ideam.sirh.usuarios.agua.model.CaudalesConcesionadoDetalleTO;
import co.gov.ideam.sirh.usuarios.agua.view.UsuariosAguaDelegate;
import co.gov.ideam.sirh.util.Constantes;
import co.gov.ideam.sirh.util.ConstantesCalidad;
import co.gov.ideam.sirh.util.IdeamException;

import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;

import co.gov.ideam.sirh.view.StandarDashBoard;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.faces.bi.model.GraphDataModel;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.data.RichColumn;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.output.RichActiveOutputText;
import oracle.adf.view.rich.component.rich.output.RichOutputLabel;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;

public class DashBoardCaudalesBean  extends StandarDashBoard{
    private RichForm form1;
    private RichDocument document1;
    private RichPanelStretchLayout panelStretchLayout1;
    private RichPanelSplitter panelSplitter1;
    private RichPanelGroupLayout panelGroupLayout2;
    private RichPanelFormLayout panelFormLayout2;
    private RichCommandLink clink_inicio;
    private RichPanelGroupLayout panelGroupLayout3;
    private RichCommandLink clink_grafico1;

    private RichPanelBox panelBoxCalidadGraf1;

    private List listaCaudales;
    private List listaCaudalesDetalle;
    private RichOutputLabel outputLabel1;
    private RichSpacer spacer4;
    private RichSpacer spacer5;
    private RichPanelCollection panelCollection1;
    private RichTable t_caudales;
    private RichTable t_caudales_detalle;
    private RichActiveOutputText activeOutputText1;
    private RichSpacer spacer1;
    private RichSpacer spacer2;
    private RichActiveOutputText activeOutputText2;
    private RichSpacer spacer3;
    private RichPanelCollection panelCollection2;
    private RichTable table1;
    private String redireccionar;

    public DashBoardCaudalesBean(){
        FacesUtils.removeManagedBeanFromSession("dashBoard");
        
        FacesUtils.setActiveBean("dashBoardCaudales", "DashBoardCaudalesBean",
                                 DashBoardCaudalesBean.class);
       this.load();        
    }

    public void load(){
         if(t_caudales != null){                                    
             for(int i=0; i<t_caudales.getChildCount(); i++){                
                 t_caudales.getChildren().remove(i);                
             }
             AdfFacesContext.getCurrentInstance().addPartialTarget(t_caudales);
         }  
             
         listaCaudales= new ArrayList();
         try{
             UsuarioConectadoTO usuarioConectado = 
                 (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
             
                 UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
                 if(usuarioConectado==null){
                     listaCaudales= uad.getListaCaudalesConcesionados(null);
                    
                     } else {   
                     
                     SeguridadDelegate seg = SeguridadDelegate.getInstance(); 
                     PerfilVO pp= new PerfilVO();
                     pp=   seg.getPerfilUsuario(usuarioConectado.getUsuario().getCodigo());
                         
                     if (pp.getCodigo()==ConstantesCalidad.PERFIL_CONSULTA){
                         listaCaudales= uad.getListaCaudalesConcesionados(null);
                              
                     }else{  
                    listaCaudales= uad.getListaCaudalesConcesionados(usuarioConectado.getUsuario().
                                                            getAutoridadAmbiental().
                                                            getId().
                                                            longValue());
                     
                  
                 } 
                     }
                
             }catch(IdeamException e){
             showMessage(e);
             } 
         }
    

    
        
   
 
    public static ValueExpression resolveExpression(String attributeName) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application app = facesContext.getApplication();
        ExpressionFactory elFactory = app.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        ValueExpression valueExp =
        elFactory.createValueExpression(elContext, attributeName, Object.class);
        return valueExp;
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

    public void setPanelSplitter1(RichPanelSplitter panelSplitter1) {
        this.panelSplitter1 = panelSplitter1;
    }

    public RichPanelSplitter getPanelSplitter1() {
        return panelSplitter1;
    }


    public void setPanelGroupLayout2(RichPanelGroupLayout panelGroupLayout2) {
        this.panelGroupLayout2 = panelGroupLayout2;
    }

    public RichPanelGroupLayout getPanelGroupLayout2() {
        return panelGroupLayout2;
    }

    public void setPanelFormLayout2(RichPanelFormLayout panelFormLayout2) {
        this.panelFormLayout2 = panelFormLayout2;
    }

    public RichPanelFormLayout getPanelFormLayout2() {
        return panelFormLayout2;
    }

    public void setClink_grafico1(RichCommandLink commandLink2) {
        this.clink_grafico1 = commandLink2;
    }

    public RichCommandLink getClink_grafico1() {
        return clink_grafico1;
    }


    public void setPanelGroupLayout3(RichPanelGroupLayout panelGroupLayout3) {
        this.panelGroupLayout3 = panelGroupLayout3;
    }

    public RichPanelGroupLayout getPanelGroupLayout3() {
        return panelGroupLayout3;
    }


    public void setClink_inicio(RichCommandLink clink_inicio) {
        this.clink_inicio = clink_inicio;
    }

    public RichCommandLink getClink_inicio() {
        return clink_inicio;
    }



    public void setPanelBoxCalidadGraf1(RichPanelBox panelBoxCalidad1) {
        this.panelBoxCalidadGraf1 = panelBoxCalidad1;
    }

    public RichPanelBox getPanelBoxCalidadGraf1() {
        return panelBoxCalidadGraf1;
    }


    public void setOutputLabel1(RichOutputLabel outputLabel1) {
        this.outputLabel1 = outputLabel1;
    }

    public RichOutputLabel getOutputLabel1() {
        return outputLabel1;
    }


    public void setSpacer4(RichSpacer spacer4) {
        this.spacer4 = spacer4;
    }

    public RichSpacer getSpacer4() {
        return spacer4;
    }

    public void setSpacer5(RichSpacer spacer5) {
        this.spacer5 = spacer5;
    }

    public RichSpacer getSpacer5() {
        return spacer5;
    }


    public void setPanelCollection1(RichPanelCollection panelCollection1) {
        this.panelCollection1 = panelCollection1;
    }

    public RichPanelCollection getPanelCollection1() {
        return panelCollection1;
    }

  
    public void setListaCaudales(List listaCaudales) {
        this.listaCaudales = listaCaudales;
    }

    public List getListaCaudales() {
        return listaCaudales;
    }

    public void setT_caudales(RichTable t_caudales) {
        this.t_caudales = t_caudales;
    }

    public RichTable getT_caudales() {
        return t_caudales;
    }

    public void setActiveOutputText1(RichActiveOutputText activeOutputText1) {
        this.activeOutputText1 = activeOutputText1;
    }

    public RichActiveOutputText getActiveOutputText1() {
        return activeOutputText1;
    }

    public void setSpacer1(RichSpacer spacer1) {
        this.spacer1 = spacer1;
    }

    public RichSpacer getSpacer1() {
        return spacer1;
    }

    public void setListaCaudalesDetalle(List listaCaudalesDetalle) {
        this.listaCaudalesDetalle = listaCaudalesDetalle;
    }

    public List getListaCaudalesDetalle() {
        return listaCaudalesDetalle;
    }

    public void setT_caudales_detalle(RichTable t_caudales_detalle) {
        this.t_caudales_detalle = t_caudales_detalle;
    }

    public RichTable getT_caudales_detalle() {
        return t_caudales_detalle;
    }

    public void setSpacer2(RichSpacer spacer2) {
        this.spacer2 = spacer2;
    }

    public RichSpacer getSpacer2() {
        return spacer2;
    }

    public void setActiveOutputText2(RichActiveOutputText activeOutputText2) {
        this.activeOutputText2 = activeOutputText2;
    }

    public RichActiveOutputText getActiveOutputText2() {
        return activeOutputText2;
    }

    public void setSpacer3(RichSpacer spacer3) {
        this.spacer3 = spacer3;
    }

    public RichSpacer getSpacer3() {
        return spacer3;
    }

    public void setPanelCollection2(RichPanelCollection panelCollection2) {
        this.panelCollection2 = panelCollection2;
    }

    public RichPanelCollection getPanelCollection2() {
        return panelCollection2;
    }

    public void setTable1(RichTable table1) {
        this.table1 = table1;
    }

    public RichTable getTable1() {
        return table1;
    }
    

    public void setRedireccionar(String redireccionar) {
        this.redireccionar = redireccionar;
    }

    public String getRedireccionar() {
        return redireccionar;
    }
    
    public void redireccionarAOrigen(ActionEvent actionEvent) {
        
        String regla = (String)FacesUtils.getFromSession("OrigenNavegacionUsuario");
        System.out.println("regla de navegación:"+regla);
        if( regla.equals("observatorio"))
             redireccionar= "observatorio";
        else
           redireccionar= "dashBoard";
    }
}
