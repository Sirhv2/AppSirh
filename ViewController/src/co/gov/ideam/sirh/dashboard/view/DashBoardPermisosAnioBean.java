package co.gov.ideam.sirh.dashboard.view;


import co.gov.ideam.sirh.calidad.view.CalidadDelegate;


import co.gov.ideam.sirh.fuentes.model.FnttFuente;

import co.gov.ideam.sirh.seguridad.model.PerfilVO;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.seguridad.view.SeguridadDelegate;
import co.gov.ideam.sirh.usuarios.agua.view.UsuariosAguaDelegate;
import co.gov.ideam.sirh.util.ConstantesCalidad;
import co.gov.ideam.sirh.util.IdeamException;

import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;

import co.gov.ideam.sirh.view.StandarDashBoard;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UISelectItems;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.faces.bi.component.graph.UIGraph;
import oracle.adf.view.faces.bi.model.GraphDataModel;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.output.RichOutputLabel;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;

public class DashBoardPermisosAnioBean  extends StandarDashBoard{
    private RichForm form1;
    private RichDocument document1;
    private RichPanelStretchLayout panelStretchLayout1;
    private RichPanelSplitter panelSplitter1;
    private RichPanelGroupLayout panelGroupLayout2;
    private RichPanelFormLayout panelFormLayout2;
    private RichCommandLink clink_inicio;
    private RichPanelGroupLayout panelGroupLayout3;
    private RichCommandLink clink_grafico1;
    private RichCommandLink clink_grafico2;
     
    private RichPanelBox panelBoxGraf1;
    private RichPanelSplitter panelSplitter2;
    private RichPanelFormLayout panelFormLayout12;
    
    
  
 
    private UIGraph barGraphPermisosAnio;
    private List<Object[]> listObject;

    private List listaFuentes;
    private List listaAnios;
    private GraphDataModel graphData;
    private String[] columnLabels = { "Permisos de Vertimiento" };
    private String[] seriesLabels;
    private Object[][] data2;
    private RichPanelGroupLayout panelGroupLayout1;
    private RichPanelFormLayout panelFormLayout1;
    private RichOutputLabel outputLabel1;
    private RichPanelGroupLayout panelGroupLayout4;
    private RichSelectOneChoice sc_anio;
    private UISelectItems selectItems21;
    private RichSpacer spacer3;
    private RichSpacer spacer4;
    private RichSpacer spacer5;
    private RichSpacer spacer6;
    private RichSpacer spacer7;
    private String redireccionar;

    public DashBoardPermisosAnioBean(){
        FacesUtils.setActiveBean("dashBoardPermisosAnio","DashBoardPermisosAnioBean", DashBoardPermisosAnioBean.class);
        FacesUtils.removeManagedBeanFromSession("dashBoardConcesionesAnio");
        this.load();        
    }
    

   
    
    public void load(){
       
            UsuarioConectadoTO usuarioConectado = new UsuarioConectadoTO();
            try{
                  usuarioConectado = 
                        (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
                    
            }catch(Exception e){
                System.out.println("++++++++++++++++e"+e);
            }
            try{  
                
            if(usuarioConectado==null){
            
                this.listaAnios = this.getListaAnioPermisos(null);
                } else {   
                
                SeguridadDelegate seg = SeguridadDelegate.getInstance(); 
                PerfilVO pp= new PerfilVO();
                pp=   seg.getPerfilUsuario(usuarioConectado.getUsuario().getCodigo());
                    
                if (pp.getCodigo()==ConstantesCalidad.PERFIL_CONSULTA){
                    this.listaAnios = this.getListaAnioPermisos(null);
                      
                }else{  
                    this.listaAnios = this.getListaAnioPermisos(usuarioConectado.getUsuario().
                                                       getAutoridadAmbiental().
                                                       getId().
                                                       longValue());
                }  
                } 
        
            
        }catch(IdeamException e){
            showMessage(e);
        }  
           
      
    }
    
    
    public void graficar( String anio){
            String datos[][] = null;
           
           
            UsuarioConectadoTO usuarioConectado = new UsuarioConectadoTO();
                try{
                      usuarioConectado = (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
                        
                }catch(Exception e){
                    System.out.println("++++++++++++++++e"+e);
                } 
                
            try{
            UsuariosAguaDelegate uus = UsuariosAguaDelegate.getInstance();
                
                if(anio.indexOf(".")!=-1){
                        anio = anio.substring(0,anio.indexOf("."));
                    }
            
            Integer aniofecha = Integer.parseInt(anio.toString());
            
          
                if(usuarioConectado==null){
                    datos = uus.getPermisosAnio(aniofecha, null);
                 
                    } else {   
                    
                    SeguridadDelegate seg = SeguridadDelegate.getInstance(); 
                    PerfilVO pp= new PerfilVO();
                    pp=   seg.getPerfilUsuario(usuarioConectado.getUsuario().getCodigo());
                        
                    if (pp.getCodigo()==ConstantesCalidad.PERFIL_CONSULTA){
                        datos = uus.getPermisosAnio(aniofecha, null);
                          
                    }else{   
                    
                  datos = uus.getPermisosAnio(  aniofecha,usuarioConectado.getUsuario().
                                                           getAutoridadAmbiental().
                                                           getId().
                                                           longValue());
                } 
            
                    }
            seriesLabels = new String[ datos[0].length ];
            data2 = new Object[1][ datos[0].length ];
            for(int i=0; i<datos[0].length; i++){  
                if(datos[1][i]!=null){
                    seriesLabels[i] = datos[0][i];   
                    data2[0][i] = new Integer(datos[1][i]);  
                }   
            }
            
                this.panelFormLayout1.setVisible(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelFormLayout1);  
                initGraphDataModel();
                AdfFacesContext.getCurrentInstance().addPartialTarget(this.barGraphPermisosAnio);   
       
            }catch(IdeamException e){
                showMessage(e);
            }
        
        }
    
   
          
     
    
    
    //ChangeListener  Año
    public void sc_anio_valueChangeListener(ValueChangeEvent event) {
          Object aniofecha = event.getNewValue();
          
          if(aniofecha!=null){
          String  anio=aniofecha.toString();
          
        
          this.graficar(anio);
              initGraphDataModel();
              AdfFacesContext.getCurrentInstance().addPartialTarget(this.barGraphPermisosAnio);   
              
          }
          else{
              
              
                  initGraphDataModel();
                  this.panelFormLayout1.setVisible(true);
                  AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelFormLayout1);   
                  
              
                  AdfFacesContext.getCurrentInstance().addPartialTarget(this.barGraphPermisosAnio);   
              
              }
        }
        
    
    public void initGraphDataModel() {
        oracle.dss.dataView.LocalXMLDataSource ds =
            new oracle.dss.dataView.LocalXMLDataSource(columnLabels, seriesLabels, data2);

        setGraphData(new oracle.adf.view.faces.bi.model.GraphDataModel());
        getGraphData().setDataSource(ds);
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


   

 

    public void setPanelSplitter2(RichPanelSplitter panelSplitter2) {
        this.panelSplitter2 = panelSplitter2;
    }

    public RichPanelSplitter getPanelSplitter2() {
        return panelSplitter2;
    }

  

    public void setListaFuentes(List listaFuentes) {
        this.listaFuentes = listaFuentes;
    }

    public List getListaFuentes() {
        return listaFuentes;
    }

  


    public void setListObject(List<Object[]> listObject) {
        this.listObject = listObject;
    }

    public List<Object[]> getListObject() {
        return listObject;
    }

    public void setGraphData(GraphDataModel graphData) {
        this.graphData = graphData;
    }

    public GraphDataModel getGraphData() {
        return graphData;
    }

    public void setColumnLabels(String[] columnLabels) {
        this.columnLabels = columnLabels;
    }

    public String[] getColumnLabels() {
        return columnLabels;
    }

    public void setSeriesLabels(String[] seriesLabels) {
        this.seriesLabels = seriesLabels;
    }

    public String[] getSeriesLabels() {
        return seriesLabels;
    }

    public void setData2(Object[][] data2) {
        this.data2 = data2;
    }

    public Object[][] getData2() {
        return data2;
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

    public void setClink_grafico2(RichCommandLink commandLink4) {
        this.clink_grafico2 = commandLink4;
    }

    public RichCommandLink getClink_grafico2() {
        return clink_grafico2;
    }



    public void setPanelGroupLayout3(RichPanelGroupLayout panelGroupLayout3) {
        this.panelGroupLayout3 = panelGroupLayout3;
    }

    public RichPanelGroupLayout getPanelGroupLayout3() {
        return panelGroupLayout3;
    }


    public void setPanelFormLayout12(RichPanelFormLayout panelFormLayout12) {
        this.panelFormLayout12 = panelFormLayout12;
    }

    public RichPanelFormLayout getPanelFormLayout12() {
        return panelFormLayout12;
    }


    public void setClink_inicio(RichCommandLink clink_inicio) {
        this.clink_inicio = clink_inicio;
    }

    public RichCommandLink getClink_inicio() {
        return clink_inicio;
    }




    public void setPanelGroupLayout1(RichPanelGroupLayout panelGroupLayout1) {
        this.panelGroupLayout1 = panelGroupLayout1;
    }

    public RichPanelGroupLayout getPanelGroupLayout1() {
        return panelGroupLayout1;
    }

    public void setPanelFormLayout1(RichPanelFormLayout panelFormLayout1) {
        this.panelFormLayout1 = panelFormLayout1;
    }

    public RichPanelFormLayout getPanelFormLayout1() {
        return panelFormLayout1;
    }


    public void setOutputLabel1(RichOutputLabel outputLabel1) {
        this.outputLabel1 = outputLabel1;
    }

    public RichOutputLabel getOutputLabel1() {
        return outputLabel1;
    }


    public void setPanelGroupLayout4(RichPanelGroupLayout panelGroupLayout4) {
        this.panelGroupLayout4 = panelGroupLayout4;
    }

    public RichPanelGroupLayout getPanelGroupLayout4() {
        return panelGroupLayout4;
    }


    public void setSc_anio(RichSelectOneChoice sc_anio) {
        this.sc_anio = sc_anio;
    }

    public RichSelectOneChoice getSc_anio() {
        return sc_anio;
    }

    public void setSelectItems21(UISelectItems selectItems21) {
        this.selectItems21 = selectItems21;
    }

    public UISelectItems getSelectItems21() {
        return selectItems21;
    }

    public void setListaAnios(List listaAnios) {
        this.listaAnios = listaAnios;
    }

    public List getListaAnios() {
        return listaAnios;
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

    public void setSpacer5(RichSpacer spacer5) {
        this.spacer5 = spacer5;
    }

    public RichSpacer getSpacer5() {
        return spacer5;
    }

    public void setSpacer6(RichSpacer spacer6) {
        this.spacer6 = spacer6;
    }

    public RichSpacer getSpacer6() {
        return spacer6;
    }

    public void setSpacer7(RichSpacer spacer7) {
        this.spacer7 = spacer7;
    }

    public RichSpacer getSpacer7() {
        return spacer7;
    }


    public void setPanelBoxGraf1(RichPanelBox panelBoxGraf1) {
        this.panelBoxGraf1 = panelBoxGraf1;
    }

    public RichPanelBox getPanelBoxGraf1() {
        return panelBoxGraf1;
    }

    public void setBarGraphPermisosAnio(UIGraph barGraphPermisosAnio) {
        this.barGraphPermisosAnio = barGraphPermisosAnio;
    }

    public UIGraph getBarGraphPermisosAnio() {
        return barGraphPermisosAnio;
    }

    public void redireccionarAOrigen(ActionEvent actionEvent) {
        
        String regla = (String)FacesUtils.getFromSession("OrigenNavegacionUsuario");
        System.out.println("regla de navegación:"+regla);
        if( regla.equals("observatorio"))
             redireccionar= "observatorio";
        else
           redireccionar= "dashBoard";
    }
    public void setRedireccionar(String redireccionar) {
        this.redireccionar = redireccionar;
    }

    public String getRedireccionar() {
        return redireccionar;
    }
}
