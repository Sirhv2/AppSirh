package co.gov.ideam.sirh.calidad.view;

import co.gov.ideam.sirh.calidad.model.CriteriosBusquedaPuntos;
import co.gov.ideam.sirh.calidad.model.Muestra;
import co.gov.ideam.sirh.calidad.model.PuntoMonitoreo;
import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Categoria;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.model.PartZonificListas;
import co.gov.ideam.sirh.reportes.view.ReporteDelegate;
import co.gov.ideam.sirh.seguridad.model.PerfilVO;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.seguridad.view.SeguridadDelegate;
import co.gov.ideam.sirh.util.Constantes;
import co.gov.ideam.sirh.util.ConstantesCalidad;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;

import java.util.ArrayList;

import java.util.List;
import co.gov.ideam.sirh.util.IdeamException;

import java.text.DateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.faces.application.FacesMessage;
import javax.faces.component.UISelectItems;

import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichMenu;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.nav.RichCommandMenuItem;
import oracle.adf.view.rich.component.rich.output.RichActiveOutputText;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;

public class MuestrasBean extends StandarBean{
    private RichForm form1;
    private RichDocument document1;
    private RichPanelStretchLayout panelStretchLayout1;
    private RichPanelSplitter panelSplitter1;
    private RichPanelFormLayout panelFormLayout1;
    private RichPanelGroupLayout panelGroupLayout1;
    private RichSelectOneChoice tipoMuestra;
    private UISelectItems selectItems2;
    private RichSpacer spacer1;
    private RichCommandButton cb_filtrar;
    private RichPanelCollection panelCollection1;
    private RichMenu m_archivo;
    private List listaTipoMuestra;
    private List listaPuntos;
    private List listaMuestras;
    
    
    private RichTable t_muestras;
    private RichInputDate fmuestra;
    private RichSelectOneChoice sc_puntos;
    private UISelectItems selectItems1;
    private RichPanelGroupLayout panelGroupLayout2;
    private RichSpacer spacer2;
    private RichActiveOutputText activeOutputText1;
    Muestra muestra;
    PuntoMonitoreo puntom;
    private RichInputText it_nroMuestra;
    private RichCommandMenuItem commandMenuItem1;

    public MuestrasBean(){
         FacesUtils.setActiveBean("muestras",
                                  "muestras", CalidadDelegate.class);
         
       
         
         this.load();        
     }
     public void load(){
         
         try{      
           
           this.listaTipoMuestra = this.getListaPorCategoria(ConstantesCalidad.TIPO_MUESTRA);
         
             UsuarioConectadoTO usuarioConectado = 
                 (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
             
           CalidadDelegate cald = CalidadDelegate.getInstance();
           if (usuarioConectado.getUsuario().getAutoridadAmbiental()==null ||
               usuarioConectado.getUsuario().getAutoridadAmbiental().getId() == null ||
               usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue() ==0 ||
               usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue() == Constantes.IDEAM){
                
                 listaMuestras = cald.getAllMuestras();
                 this.listaPuntos=  this.getListaPuntosMuestras();
           } else {   
                           SeguridadDelegate seg = SeguridadDelegate.getInstance(); 
                           PerfilVO pp= new PerfilVO();
                           pp=   seg.getPerfilUsuario(usuarioConectado.getUsuario().getCodigo());
                               
                           if (pp.getCodigo()==ConstantesCalidad.PERFIL_CONSULTA){
                                  listaMuestras = cald.getAllMuestras();
                                  this.listaPuntos=  this.getListaPuntosMuestras();
                          }else{  
                                  listaMuestras = cald.getAllMuestras(usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue() );
                                  this.listaPuntos=  this.getListaPuntosMuestras(usuarioConectado.getUsuario().
                                                                        getAutoridadAmbiental().getId().longValue() );
                                }
                 }
         }catch(IdeamException e){
             showMessage(e);
         }
     }

    public void detalleMuestra_actionListener(ActionEvent actionEvent) {
        try{    
     
       Long codigo = 
           (Long)actionEvent.getComponent().getAttributes().get("codigo");   
           CalidadDelegate cald = CalidadDelegate.getInstance();
           this.muestra = cald.getMuestra(codigo);  
           FacesUtils.setInSession("muestraSeleccionada",muestra);
         
           Long codigoPto = this.muestra.getIdPuntoMonitoreo().getId();
           this.puntom = cald.getPuntoMonitoreo(codigoPto);  
           FacesUtils.setInSession("puntoSeleccionado",puntom); 
         
        }catch(IdeamException e){
            showMessage(e);
        }
         
         
    } 

    public void cb_filtrar_actionListener(ActionEvent actionEvent) {
        CriteriosBusquedaPuntos criterios = new CriteriosBusquedaPuntos();
        if(this.tipoMuestra.getValue()!=null){
            criterios.setTipoMuestra(
                    (Lista)tipoMuestra.getValue());
        }
    
      if(this.fmuestra.getValue()!=null &&
         this.fmuestra.getValue().toString().length()>0){
          Date fecham =(Date)this.fmuestra.getValue();
          DateFormat df = DateFormat.getDateInstance();
          String fechamuestra = df.format(fecham);
          if(fechamuestra.length()==9){
             String fechaCompleta = "0"+fechamuestra;
              criterios.setFechaMuestra(fechaCompleta);  
          }else{
              criterios.setFechaMuestra(fechamuestra);  
          }
 }
        if(this.sc_puntos.getValue()!=null){
            criterios.setListapuntos(
                    (PuntoMonitoreo)sc_puntos.getValue());
        }
        if(this.it_nroMuestra.getValue()!=null &&
           this.it_nroMuestra.getValue().toString().length()>0){
            criterios.setNroMuestra(
                it_nroMuestra.getValue().toString());       
        }
 
        try{
            UsuarioConectadoTO usuarioConectado = 
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
                CalidadDelegate cald = CalidadDelegate.getInstance();
           if (usuarioConectado.getUsuario().getAutoridadAmbiental()==null ||
                usuarioConectado.getUsuario().getAutoridadAmbiental().getId() == null ||
                usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue() ==0 ||
                usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue() == Constantes.IDEAM){
               this.listaMuestras = cald.getMuestrasVista(criterios);
                } else {   
                                SeguridadDelegate seg = SeguridadDelegate.getInstance(); 
                                PerfilVO pp= new PerfilVO();
                                pp=   seg.getPerfilUsuario(usuarioConectado.getUsuario().getCodigo());
                                    
                                if (pp.getCodigo()==ConstantesCalidad.PERFIL_CONSULTA){
                                   this.listaMuestras = cald.getMuestrasVista(criterios);
                               }else{ 
            this.listaMuestras= new ArrayList();
              
            this.listaMuestras = cald.getMuestrasVista(criterios, usuarioConectado.getUsuario().
                                                                     getAutoridadAmbiental().
                                                                     getId().
                                                                     longValue());
             }
         }
          AdfFacesContext.getCurrentInstance().addPartialTarget(t_muestras);
        }catch(IdeamException e){
            showMessage(e);
        }
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

    public void setPanelFormLayout1(RichPanelFormLayout panelFormLayout1) {
        this.panelFormLayout1 = panelFormLayout1;
    }

    public RichPanelFormLayout getPanelFormLayout1() {
        return panelFormLayout1;
    }

    public void setPanelGroupLayout1(RichPanelGroupLayout panelGroupLayout1) {
        this.panelGroupLayout1 = panelGroupLayout1;
    }

    public RichPanelGroupLayout getPanelGroupLayout1() {
        return panelGroupLayout1;
    }


    public void setTipoMuestra(RichSelectOneChoice selectOneChoice1) {
        this.tipoMuestra = selectOneChoice1;
    }

    public RichSelectOneChoice getTipoMuestra() {
        return tipoMuestra;
    }

    public void setSelectItems2(UISelectItems selectItems2) {
        this.selectItems2 = selectItems2;
    }

    public UISelectItems getSelectItems2() {
        return selectItems2;
    }

    public void setSpacer1(RichSpacer spacer1) {
        this.spacer1 = spacer1;
    }

    public RichSpacer getSpacer1() {
        return spacer1;
    }

    public void setCb_filtrar(RichCommandButton commandButton1) {
        this.cb_filtrar = commandButton1;
    }

    public RichCommandButton getCb_filtrar() {
        return cb_filtrar;
    }


    public void setPanelCollection1(RichPanelCollection panelCollection1) {
        this.panelCollection1 = panelCollection1;
    }

    public RichPanelCollection getPanelCollection1() {
        return panelCollection1;
    }

    public void setM_archivo(RichMenu menu1) {
        this.m_archivo = menu1;
    }

    public RichMenu getM_archivo() {
        return m_archivo;
    }


    public void setListaTipoMuestra(List listaTipoMuestra) {
        this.listaTipoMuestra = listaTipoMuestra;
    }

    public List getListaTipoMuestra() {
        return listaTipoMuestra;
    }

    public void setListaPuntos(List listaPuntos) {
        this.listaPuntos = listaPuntos;
    }

    public List getListaPuntos() {
        return listaPuntos;
    }

    public void setListaMuestras(List listaMuestras) {
        this.listaMuestras = listaMuestras;
    }

    public List getListaMuestras() {
        return listaMuestras;
    }


    public void setT_muestras(RichTable t_muestras) {
        this.t_muestras = t_muestras;
    }

    public RichTable getT_muestras() {
        return t_muestras;
    }

    public void setFmuestra(RichInputDate inputDate1) {
        this.fmuestra = inputDate1;
    }

    public RichInputDate getFmuestra() {
        return fmuestra;
    }

    public void setSc_puntos(RichSelectOneChoice selectOneChoice1) {
        this.sc_puntos = selectOneChoice1;
    }

    public RichSelectOneChoice getSc_puntos() {
        return sc_puntos;
    }

    public void setSelectItems1(UISelectItems selectItems1) {
        this.selectItems1 = selectItems1;
    }

    public UISelectItems getSelectItems1() {
        return selectItems1;
    }

    public void setPanelGroupLayout2(RichPanelGroupLayout panelGroupLayout2) {
        this.panelGroupLayout2 = panelGroupLayout2;
    }

    public RichPanelGroupLayout getPanelGroupLayout2() {
        return panelGroupLayout2;
    }

    public void setSpacer2(RichSpacer spacer2) {
        this.spacer2 = spacer2;
    }

    public RichSpacer getSpacer2() {
        return spacer2;
    }

    public void setActiveOutputText1(RichActiveOutputText activeOutputText1) {
        this.activeOutputText1 = activeOutputText1;
    }

    public RichActiveOutputText getActiveOutputText1() {
        return activeOutputText1;
    }

  

    public void setIt_nroMuestra(RichInputText inputText1) {
        this.it_nroMuestra = inputText1;
    }

    public RichInputText getIt_nroMuestra() {
        return it_nroMuestra;
    }

    public void setMuestra(Muestra muestra) {
        this.muestra = muestra;
    }

    public Muestra getMuestra() {
        return muestra;
    }

    public void setPuntom(PuntoMonitoreo puntom) {
        this.puntom = puntom;
    }

    public PuntoMonitoreo getPuntom() {
        return puntom;
    }

    public void setCommandMenuItem1(RichCommandMenuItem commandMenuItem1) {
        this.commandMenuItem1 = commandMenuItem1;
    }

    public RichCommandMenuItem getCommandMenuItem1() {
        return commandMenuItem1;
    }

    public void cmi_imprimir_actionListener(ActionEvent actionEvent) {
        try {
            HashMap parametros = new HashMap();
            parametros.put("p_modulo", "Calidad del Agua");
            parametros.put("p_titulo", "Muestras");
            this.generateReport("ListadoMuestras.jasper", this.listaMuestras,
                                parametros, ReporteDelegate.EXCEL);
        } catch (IdeamException e) {
            showMessage(e);
        }
    }
}
