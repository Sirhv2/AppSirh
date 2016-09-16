package co.gov.ideam.sirh.dashboard.view;

import co.gov.ideam.sirh.calidad.model.DatosGrafico;
import co.gov.ideam.sirh.calidad.model.NormaLimites;
import co.gov.ideam.sirh.calidad.model.PuntoMonitoreo;
import co.gov.ideam.sirh.calidad.view.CalidadDelegate;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.seguridad.model.PerfilVO;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.seguridad.view.SeguridadDelegate;
import co.gov.ideam.sirh.util.ConstantesCalidad;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;

import co.gov.ideam.sirh.view.StandarBean;

import co.gov.ideam.sirh.view.StandarDashBoard;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UISelectItems;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.faces.bi.component.graph.UIGraph;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.output.RichActiveOutputText;
import oracle.adf.view.rich.component.rich.output.RichOutputLabel;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;

public class GraficoNormaLimitesBean extends StandarDashBoard{
    private RichForm form1;
    private RichDocument document1;
    private RichPanelStretchLayout panelStretchLayout1;
    private RichPanelSplitter panelSplitter1;
    private RichPanelGroupLayout panelGroupLayout1;
    private RichPanelFormLayout panelFormLayout1;
    private RichPanelGroupLayout panelGroupLayout11;
    private RichPanelFormLayout panelFormLayout11;
    private RichPanelBox panelBox1;
    private RichPanelFormLayout panelFormLayout2;
    private RichSelectOneChoice sc_puntos;
    private UISelectItems selectpuntos;
    private List listaPuntos;
    
    private List<Object[]> listObjectGrafico;

    private PuntoMonitoreo pm;
    private Long puntoId;
    private Long parametroId;
    private String nombreParametro;
    private RichPanelFormLayout panelFormGrafico;

    private NormaLimites  normaLimites;

    private RichOutputLabel otNota;
    private RichSpacer spacer3;
    private RichPanelGroupLayout panelGroupLayout2;

 
    private RichActiveOutputText datos;
    private RichActiveOutputText dunidad;
    private RichActiveOutputText dparamtro;
    UsuarioConectadoTO usuarioConectado;
    private RichPanelSplitter panelSplitter2;
    private RichPanelGroupLayout panelGroupLayout3;
    private RichPanelSplitter panelSplitter11;
    private RichPanelGroupLayout panelGroupLayout111;
    private RichPanelFormLayout panelFormLayout111;
    private RichSelectOneChoice sc_puntos1;
    private UISelectItems selectpuntos1;
    private RichPanelFormLayout panelFormLayout21;
    private RichSpacer spacer31;
    private RichOutputLabel otNota1;
    private RichPanelGroupLayout panelGroupLayout21;
    private RichPanelFormLayout panelFormGrafico1;
    private RichActiveOutputText datos1;
    private RichActiveOutputText dparamtro1;
    private RichActiveOutputText dunidad1;
    private UIGraph lineGraph;
    private RichPanelFormLayout panelFormLayout3;
    private RichCommandLink commandLinkVolver;

    public GraficoNormaLimitesBean(){
        FacesUtils.setActiveBean("graficoNormaLimites",
                                 "GraficoNormaLimitesBean", GraficoNormaLimitesBean.class);
       
       this.load();        
    }
    

    
    
    public void load(){
    
    
        usuarioConectado = new UsuarioConectadoTO();
        try{
              usuarioConectado = 
                    (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
                
        }catch(Exception e){
            System.out.println("++++++++++++++++e"+e);
        }
        try{
            
          CalidadDelegate cld = CalidadDelegate.getInstance();
          
          Object obj = FacesUtils.getFromSession("limiteSeleccionado");
           if(obj instanceof NormaLimites){
                     this.normaLimites = (NormaLimites)obj;
                     Long codigoLim=  this.normaLimites.getId();
                     this.normaLimites = cld.getNormaLimitesId(codigoLim); 
          }
            
        
            if(usuarioConectado==null){
               
                this.listaPuntos = this.getListaPuntosMediciones(null);
                
                } else {   
                
                SeguridadDelegate seg = SeguridadDelegate.getInstance(); 
                PerfilVO pp= new PerfilVO();
                pp=   seg.getPerfilUsuario(usuarioConectado.getUsuario().getCodigo());
                    
                if (pp.getCodigo()==ConstantesCalidad.PERFIL_CONSULTA){
                    this.listaPuntos = this.getListaPuntosMediciones(null);
                      
                }else{ 
               
                       this.listaPuntos = this.getListaPuntosMediciones( usuarioConectado.getUsuario().
                                                               getAutoridadAmbiental().
                                                               getId().
                                                               longValue());
                        this.parametroId=this.normaLimites.getIdParametro().longValue();
                        this.nombreParametro= this.normaLimites.getListaParametros().getValor();
                
                     } 
              
                }    
              
    
           
      }catch(IdeamException e){
          showMessage(e);
      }
    }



    //ChangeListener  PuntoMonitoreo
    public void sc_punto_valueChangeListener(ValueChangeEvent event) {
            Object puntoid = event.getNewValue();
            this.puntoId=((PuntoMonitoreo)puntoid).getId();
            this.graficar();
         
        }
    
    
    public void graficar(){
           this.panelFormGrafico1.setVisible(true);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelFormGrafico1);   
            getListObjectGrafico();
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.lineGraph);   
       
            
        
        }
    
    
        
    public List<Object[]> getListObjectGrafico() {
        System.out.println("---------------construye getListObjectGrafico");
        try{
         
          listObjectGrafico = new ArrayList<Object[]>();
          CalidadDelegate cald = CalidadDelegate.getInstance();
             
          List<Object[]> lDatosGrafico = cald.getPararametroFechaPuntoM(this.puntoId, this.parametroId);
          List<Object[]> lDatosGrafico2 = lDatosGrafico;
            
          List<Lista>  lUnidad = cald.getUnidadPararametro(this.parametroId, usuarioConectado.getUsuario().
                                                               getAutoridadAmbiental().
                                                               getId().
                                                               longValue());
          NormaLimites nl = cald.getNormaLimitesId(this.normaLimites.getId()) ;
          String nombrePar="Parámetro: "+this.nombreParametro.toString();   
            
          Lista l= lUnidad.get(0);
            this.dparamtro.setValue(nombrePar.toString());
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.dparamtro);   
            
            this.dunidad.setValue("Unidad: "+l.getValor().toString());
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.dunidad);   
            
              for (int j = 0; j < lDatosGrafico.size(); j++) {
               Object[] dat = lDatosGrafico.get(j);
                DatosGrafico d=new DatosGrafico();
                d.setFecha((String) dat[0]);
                d.setCantidad(new Double(dat[1].toString()));
                      Object[] obj1= { d.getFecha()," Medición de Calidad "  , d.getCantidad() };
                      listObjectGrafico.add( obj1);
                }
              
                for (int j = 0; j < lDatosGrafico2.size(); j++) {
                 Object[] dat = lDatosGrafico2.get(j);
                  DatosGrafico d=new DatosGrafico();
                  d.setFecha((String) dat[0]);
                  d.setCantidad(nl.getValor());
                        Object[] obj1= { d.getFecha()," (Límite de Calidad) Norma"  ,d.getCantidad() };
                        listObjectGrafico.add( obj1);
                  }
              
          
             
        } catch (Exception e) {System.out.print("error:" + e);}
          return listObjectGrafico;
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


    public void setPanelGroupLayout11(RichPanelGroupLayout panelGroupLayout11) {
        this.panelGroupLayout11 = panelGroupLayout11;
    }

    public RichPanelGroupLayout getPanelGroupLayout11() {
        return panelGroupLayout11;
    }

    public void setPanelFormLayout11(RichPanelFormLayout panelFormLayout11) {
        this.panelFormLayout11 = panelFormLayout11;
    }

    public RichPanelFormLayout getPanelFormLayout11() {
        return panelFormLayout11;
    }

    public void setPanelBox1(RichPanelBox panelBox1) {
        this.panelBox1 = panelBox1;
    }

    public RichPanelBox getPanelBox1() {
        return panelBox1;
    }


    public void setPanelFormLayout2(RichPanelFormLayout panelFormLayout2) {
        this.panelFormLayout2 = panelFormLayout2;
    }

    public RichPanelFormLayout getPanelFormLayout2() {
        return panelFormLayout2;
    }

    public void setSc_puntos(RichSelectOneChoice sc_puntos) {
        this.sc_puntos = sc_puntos;
    }

    public RichSelectOneChoice getSc_puntos() {
        return sc_puntos;
    }

    public void setSelectpuntos(UISelectItems selectpuntos) {
        this.selectpuntos = selectpuntos;
    }

    public UISelectItems getSelectpuntos() {
        return selectpuntos;
    }


    public void setListaPuntos(List listaPuntos) {
        this.listaPuntos = listaPuntos;
    }

    public List getListaPuntos() {
        return listaPuntos;
    }

    public void setListObjectGrafico(List<Object[]> listObjectGrafico) {
        this.listObjectGrafico = listObjectGrafico;
    }
    

    public void setPm(PuntoMonitoreo pm) {
        this.pm = pm;
    }

    public PuntoMonitoreo getPm() {
        return pm;
    }

    public void setPuntoId(Long puntoId) {
        this.puntoId = puntoId;
    }

    public Long getPuntoId() {
        return puntoId;
    }

    public void setParametroId(Long parametroId) {
        this.parametroId = parametroId;
    }

    public Long getParametroId() {
        return parametroId;
    }

    public void setPanelFormGrafico(RichPanelFormLayout panelFormGrafico) {
        this.panelFormGrafico = panelFormGrafico;
    }

    public RichPanelFormLayout getPanelFormGrafico() {
        return panelFormGrafico;
    }

    public void setOtNota(RichOutputLabel otNota) {
        this.otNota = otNota;
    }

    public RichOutputLabel getOtNota() {
        return otNota;
    }

    public void setSpacer3(RichSpacer spacer3) {
        this.spacer3 = spacer3;
    }

    public RichSpacer getSpacer3() {
        return spacer3;
    }

    public void setPanelGroupLayout2(RichPanelGroupLayout panelGroupLayout2) {
        this.panelGroupLayout2 = panelGroupLayout2;
    }

    public RichPanelGroupLayout getPanelGroupLayout2() {
        return panelGroupLayout2;
    }




    public void setNombreParametro(String nombreParametro) {
        this.nombreParametro = nombreParametro;
    }

    public String getNombreParametro() {
        return nombreParametro;
    }

    public void setNormaLimites(NormaLimites normaLimites) {
        this.normaLimites = normaLimites;
    }

    public NormaLimites getNormaLimites() {
        return normaLimites;
    }

    public void setDatos(RichActiveOutputText activeOutputText1) {
        this.datos = activeOutputText1;
    }

    public RichActiveOutputText getDatos() {
        return datos;
    }

    public void setDunidad(RichActiveOutputText activeOutputText1) {
        this.dunidad = activeOutputText1;
    }

    public RichActiveOutputText getDunidad() {
        return dunidad;
    }

    public void setDparamtro(RichActiveOutputText activeOutputText1) {
        this.dparamtro = activeOutputText1;
    }

    public RichActiveOutputText getDparamtro() {
        return dparamtro;
    }

    public void setPanelSplitter2(RichPanelSplitter panelSplitter2) {
        this.panelSplitter2 = panelSplitter2;
    }

    public RichPanelSplitter getPanelSplitter2() {
        return panelSplitter2;
    }

    public void setPanelGroupLayout3(RichPanelGroupLayout panelGroupLayout3) {
        this.panelGroupLayout3 = panelGroupLayout3;
    }

    public RichPanelGroupLayout getPanelGroupLayout3() {
        return panelGroupLayout3;
    }

    public void setPanelSplitter11(RichPanelSplitter panelSplitter11) {
        this.panelSplitter11 = panelSplitter11;
    }

    public RichPanelSplitter getPanelSplitter11() {
        return panelSplitter11;
    }

    public void setPanelGroupLayout111(RichPanelGroupLayout panelGroupLayout111) {
        this.panelGroupLayout111 = panelGroupLayout111;
    }

    public RichPanelGroupLayout getPanelGroupLayout111() {
        return panelGroupLayout111;
    }

    public void setPanelFormLayout111(RichPanelFormLayout panelFormLayout111) {
        this.panelFormLayout111 = panelFormLayout111;
    }

    public RichPanelFormLayout getPanelFormLayout111() {
        return panelFormLayout111;
    }

    public void setSc_puntos1(RichSelectOneChoice sc_puntos1) {
        this.sc_puntos1 = sc_puntos1;
    }

    public RichSelectOneChoice getSc_puntos1() {
        return sc_puntos1;
    }

    public void setSelectpuntos1(UISelectItems selectpuntos1) {
        this.selectpuntos1 = selectpuntos1;
    }

    public UISelectItems getSelectpuntos1() {
        return selectpuntos1;
    }

    public void setPanelFormLayout21(RichPanelFormLayout panelFormLayout21) {
        this.panelFormLayout21 = panelFormLayout21;
    }

    public RichPanelFormLayout getPanelFormLayout21() {
        return panelFormLayout21;
    }

    public void setSpacer31(RichSpacer spacer31) {
        this.spacer31 = spacer31;
    }

    public RichSpacer getSpacer31() {
        return spacer31;
    }

    public void setOtNota1(RichOutputLabel otNota1) {
        this.otNota1 = otNota1;
    }

    public RichOutputLabel getOtNota1() {
        return otNota1;
    }

    public void setPanelGroupLayout21(RichPanelGroupLayout panelGroupLayout21) {
        this.panelGroupLayout21 = panelGroupLayout21;
    }

    public RichPanelGroupLayout getPanelGroupLayout21() {
        return panelGroupLayout21;
    }

    public void setPanelFormGrafico1(RichPanelFormLayout panelFormGrafico1) {
        this.panelFormGrafico1 = panelFormGrafico1;
    }

    public RichPanelFormLayout getPanelFormGrafico1() {
        return panelFormGrafico1;
    }

    public void setDatos1(RichActiveOutputText datos1) {
        this.datos1 = datos1;
    }

    public RichActiveOutputText getDatos1() {
        return datos1;
    }

    public void setDparamtro1(RichActiveOutputText dparamtro1) {
        this.dparamtro1 = dparamtro1;
    }

    public RichActiveOutputText getDparamtro1() {
        return dparamtro1;
    }

    public void setDunidad1(RichActiveOutputText dunidad1) {
        this.dunidad1 = dunidad1;
    }

    public RichActiveOutputText getDunidad1() {
        return dunidad1;
    }

    public void setLineGraph(UIGraph lineGraph111) {
        this.lineGraph = lineGraph111;
    }

    public UIGraph getLineGraph() {
        return lineGraph;
    }


    public void setPanelFormLayout3(RichPanelFormLayout panelFormLayout3) {
        this.panelFormLayout3 = panelFormLayout3;
    }

    public RichPanelFormLayout getPanelFormLayout3() {
        return panelFormLayout3;
    }


    public void setCommandLinkVolver(RichCommandLink commandLink1) {
        this.commandLinkVolver = commandLink1;
    }

    public RichCommandLink getCommandLinkVolver() {
        return commandLinkVolver;
    }
}
