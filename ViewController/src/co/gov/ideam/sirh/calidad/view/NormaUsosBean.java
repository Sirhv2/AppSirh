package co.gov.ideam.sirh.calidad.view;

import co.gov.ideam.sirh.calidad.model.Medicion;
import co.gov.ideam.sirh.calidad.model.Muestra;
import co.gov.ideam.sirh.calidad.model.NormaLimites;
import co.gov.ideam.sirh.calidad.model.NormaUsos;
import co.gov.ideam.sirh.calidad.model.PuntoMonitoreo;
import co.gov.ideam.sirh.calidad.model.PuntoMonitoreoTO;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.model.PartZonificListas;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.util.ConstantesCalidad;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;

import co.gov.ideam.sirh.view.StandarBean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UISelectItems;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichMenu;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichCommandMenuItem;
import oracle.adf.view.rich.component.rich.output.RichActiveOutputText;
import oracle.adf.view.rich.component.rich.output.RichImage;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;

import org.apache.myfaces.trinidad.event.SelectionEvent;

public class NormaUsosBean   extends StandarBean{
    private RichForm form1;
    private RichDocument document1;
    private RichPanelStretchLayout panelStretchLayout1;
    private RichPanelSplitter panelSplitter1;
    private RichPanelBox panelBoxNorma;
    private RichPanelStretchLayout panelStretchLayout2;
    private RichPanelCollection panelCollection1;
    private RichPanelBox panelBox2;
    private RichPanelStretchLayout panelStretchLayout3;
    private RichPanelCollection panelCollection2;
    private RichPanelGroupLayout panelGroupLayout1;
    private RichPanelFormLayout panelFormLayout1;
    private RichPanelStretchLayout panelLimites;
    private RichPanelCollection panelCollection21;
    private RichInputText it_nombreNorma;
    private RichPanelGroupLayout panelGroupLayout3;
    private RichPanelFormLayout panelFormLayout3;
    private RichSelectOneChoice seletPar;
    private UISelectItems selectItems1;
    private RichSelectOneChoice selectUnid;
    private UISelectItems selectItems3;
    private RichSelectOneChoice selectSigno;
    private UISelectItems selectItems4;
    private RichSelectOneChoice sc_usos;
    private UISelectItems selectItems5;
    private RichInputText it_valor;
    private RichCommandButton cb_guardar;
    private RichCommandButton cb_guardarNorma;
    private List listaParametro;
    private List listaUnidad;
    private List listaSigno;
    private List listaUsos;
    private List listaMediciones;
    private List listaRiesgo;
    
    private NormaUsos norma;
    private NormaLimites normaLimites;
    private List listadoNormas;
    private List listadoLimitesParametros;
    private RichTable tableUsos;
    private RichMenu menu1;
    private RichCommandMenuItem cmi_editNorma;
    private RichTable tableLimites;
    private RichPopup p_registro_lim;
    private RichDialog d_registro_lim;
    private RichPanelStretchLayout panelStretchLayout4;
    private RichActiveOutputText t_registro_lim;
    private RichImage image1;
    private RichPanelGroupLayout panelGroupLayout2;
    private RichCommandButton cb_aceptar_lim;
    private RichCommandMenuItem cmi_borrar;
    private RichPopup popup_borrar;
    private RichDialog d_confirmar_borrado;
    private RichPanelGroupLayout panelGroupLayout4;
    private RichActiveOutputText ot_borrar1;
    private RichSpacer spacer1;
    private RichActiveOutputText ot_borrar2;
    private RichPanelGroupLayout panelGroupLayout5;
    private RichCommandButton cb_si_borrar;
    private RichCommandButton cb_no_borrar;
    private RichSpacer spacer2;
    private RichCommandButton cb_editarNorma;
    private RichMenu menu2;
    private RichCommandMenuItem cmi_editarLimite;
    private RichCommandMenuItem cmi_borrarLimite;
    private RichPopup p_elim_lim;
    private RichDialog d_elim_lim;
    private RichPanelGroupLayout panelGroupLayout6;
    private RichActiveOutputText ac_borrar1;
    private RichActiveOutputText ac_borrar2;
    private RichSpacer spacer3;
    private RichPanelGroupLayout panelGroupLayout7;
    private RichCommandButton cb_si_borraLim;
    private RichCommandButton cb_no_borraLim;
    private RichSpacer spacer4;
    private RichCommandButton cb_actualizarLim;
    private RichInputText it_valor2;
    private RichInputText it_valorChar;
    private RichPanelFormLayout panelFormLayout2;
    private RichCommandButton cb_grafico;
    
    
    private String accionGraficar;
    private RichSelectOneChoice sc_riesgo;
    private UISelectItems selectItems2;

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

    public void setPanelBoxNorma(RichPanelBox panelBox1) {
        this.panelBoxNorma = panelBox1;
    }

    public RichPanelBox getPanelBoxNorma() {
        return panelBoxNorma;
    }

    public void setPanelStretchLayout2(RichPanelStretchLayout panelStretchLayout2) {
        this.panelStretchLayout2 = panelStretchLayout2;
    }

    public RichPanelStretchLayout getPanelStretchLayout2() {
        return panelStretchLayout2;
    }

    public void setPanelCollection1(RichPanelCollection panelCollection1) {
        this.panelCollection1 = panelCollection1;
    }

    public RichPanelCollection getPanelCollection1() {
        return panelCollection1;
    }

    public void setPanelBox2(RichPanelBox panelBox2) {
        this.panelBox2 = panelBox2;
    }

    public RichPanelBox getPanelBox2() {
        return panelBox2;
    }

    public void setPanelStretchLayout3(RichPanelStretchLayout panelStretchLayout3) {
        this.panelStretchLayout3 = panelStretchLayout3;
    }

    public RichPanelStretchLayout getPanelStretchLayout3() {
        return panelStretchLayout3;
    }

    public void setPanelCollection2(RichPanelCollection panelCollection2) {
        this.panelCollection2 = panelCollection2;
    }

    public RichPanelCollection getPanelCollection2() {
        return panelCollection2;
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


    public void setPanelLimites(RichPanelStretchLayout panelStretchLayout31) {
        this.panelLimites = panelStretchLayout31;
    }

    public RichPanelStretchLayout getPanelLimites() {
        return panelLimites;
    }

    public void setPanelCollection21(RichPanelCollection panelCollection21) {
        this.panelCollection21 = panelCollection21;
    }

    public RichPanelCollection getPanelCollection21() {
        return panelCollection21;
    }


    public void setIt_nombreNorma(RichInputText inputText1) {
        this.it_nombreNorma = inputText1;
    }

    public RichInputText getIt_nombreNorma() {
        return it_nombreNorma;
    }

    public void setPanelGroupLayout3(RichPanelGroupLayout panelGroupLayout3) {
        this.panelGroupLayout3 = panelGroupLayout3;
    }

    public RichPanelGroupLayout getPanelGroupLayout3() {
        return panelGroupLayout3;
    }

    public void setPanelFormLayout3(RichPanelFormLayout panelFormLayout3) {
        this.panelFormLayout3 = panelFormLayout3;
    }

    public RichPanelFormLayout getPanelFormLayout3() {
        return panelFormLayout3;
    }

    public void setSeletPar(RichSelectOneChoice selectOneChoice1) {
        this.seletPar = selectOneChoice1;
    }

    public RichSelectOneChoice getSeletPar() {
        return seletPar;
    }

    public void setSelectItems1(UISelectItems selectItems1) {
        this.selectItems1 = selectItems1;
    }

    public UISelectItems getSelectItems1() {
        return selectItems1;
    }


    public void setSelectUnid(RichSelectOneChoice selectOneChoice3) {
        this.selectUnid = selectOneChoice3;
    }

    public RichSelectOneChoice getSelectUnid() {
        return selectUnid;
    }

    public void setSelectItems3(UISelectItems selectItems3) {
        this.selectItems3 = selectItems3;
    }

    public UISelectItems getSelectItems3() {
        return selectItems3;
    }

    public void setSelectSigno(RichSelectOneChoice selectOneChoice4) {
        this.selectSigno = selectOneChoice4;
    }

    public RichSelectOneChoice getSelectSigno() {
        return selectSigno;
    }

    public void setSelectItems4(UISelectItems selectItems4) {
        this.selectItems4 = selectItems4;
    }

    public UISelectItems getSelectItems4() {
        return selectItems4;
    }

    public void setSc_usos(RichSelectOneChoice selectOneChoice5) {
        this.sc_usos = selectOneChoice5;
    }

    public RichSelectOneChoice getSc_usos() {
        return sc_usos;
    }

    public void setSelectItems5(UISelectItems selectItems5) {
        this.selectItems5 = selectItems5;
    }

    public UISelectItems getSelectItems5() {
        return selectItems5;
    }

    public void setIt_valor(RichInputText inputText2) {
        this.it_valor = inputText2;
    }

    public RichInputText getIt_valor() {
        return it_valor;
    }

    public void setCb_guardar(RichCommandButton commandButton1) {
        this.cb_guardar = commandButton1;
    }

    public RichCommandButton getCb_guardar() {
        return cb_guardar;
    }

    public void setCb_guardarNorma(RichCommandButton commandButton2) {
        this.cb_guardarNorma = commandButton2;
    }

    public RichCommandButton getCb_guardarNorma() {
        return cb_guardarNorma;
    }

    public void setListaParametro(List listaParametro) {
        this.listaParametro = listaParametro;
    }

    public List getListaParametro() {
        return listaParametro;
    }

    public void setListaUnidad(List listaUnidad) {
        this.listaUnidad = listaUnidad;
    }

    public List getListaUnidad() {
        return listaUnidad;
    }

    public void setListaSigno(List listaSigno) {
        this.listaSigno = listaSigno;
    }

    public List getListaSigno() {
        return listaSigno;
    }
    
    public void socUso_valueChangeListener(ValueChangeEvent valueChangeEvent) {
        Object uso = valueChangeEvent.getNewValue();
        try {
            if (uso != null) {
                Lista listaUsos = (Lista)this.sc_usos.getValue();
                
                if (listaUsos.getCodigo().intValue() == ConstantesCalidad.USO_DOMESTICO)
                    listaRiesgo =  this.getListaPorCategoria(ConstantesCalidad.NIVEL_RIESGO);
                else
                    listaRiesgo =  this.getListaPorCategoria(ConstantesCalidad.NIVEL_RIESGODEF);
                    
            }
        } catch (IdeamException e) {
            showMessage(e);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.sc_riesgo);
    }

    public NormaUsosBean(){
        FacesUtils.setActiveBean("NormaUsos","NormaUsos", CalidadDelegate.class);
        FacesUtils.removeManagedBeanFromSession("graficoNormaLimites");
        
        
       this.load();        
    }
    public void load(){
        
       
        
      try{      
       
        norma = new NormaUsos();
        normaLimites= new NormaLimites();
     
        CalidadDelegate cald = CalidadDelegate.getInstance();
        this.listaParametro= this.getListaPorCategoria(ConstantesCalidad.PARAMETROS_CALIDAD);
        this.listaUnidad= this.getListaPorCategoria(ConstantesCalidad.UNIDADES_CALIDAD);   
        this.listaSigno= this.getListaPorCategoria(ConstantesCalidad.SIGNOS);
        this.listaUsos= this.getListaPorCategoria(ConstantesCalidad.USOS_AGUA_NORMA);
        this.listaRiesgo =   new ArrayList();

        UsuarioConectadoTO usuarioConectado = (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
         
        listadoNormas  = cald.getNormaUsosXAutoridad(usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue());
              
           
      }catch(IdeamException e){
          showMessage(e);
      }
    }
    
    
    //pantalla de datos básicos Muestras
    public void cb_guardaNorma_actionListener(ActionEvent actionEvent) throws IdeamException{
        boolean continuar = true;
      
        if(this.sc_riesgo.getValue()==null){
              showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.sc_riesgo);       
              continuar = false;
          }

        if(this.sc_usos.getValue()==null){
              showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.sc_usos);       
              continuar = false;
          }
       
        if(this.it_nombreNorma.getValue()==null){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.it_nombreNorma);       
            continuar = false;
        }

        if(continuar){   
             this.saveNorma();   
           
        }
    }
    
    public void saveNorma(){
       try{
             CalidadDelegate cld = CalidadDelegate.getInstance();
             norma= new NormaUsos();
             
             norma.setNombreNorma(this.it_nombreNorma.getValue().toString());
             norma.setListaUsos((Lista)this.sc_usos.getValue());
             norma.setListaRiesgo((Lista)this.sc_riesgo.getValue()); 
           
             UsuarioConectadoTO usuarioConectado = (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
             norma.setCodigoAutoridad(usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue());
             norma.setUso(this.norma.getListaUsos().getCodigo());
             norma.setRiesgo(this.norma.getListaRiesgo().getCodigo());
            
             System.out.println("SaveNorma.riesgo-----------" + norma.getRiesgo()); 
             System.out.println("SaveNorma.uso-----------" + norma.getUso()); 
              
             NormaUsos norUsos =  cld.updateNormaUsos(this.norma);
             FacesUtils.setInSession("normaNueva", norUsos);
          
          
             listadoNormas  = cld.getNormaUsosXAutoridad(usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue());
              

            AdfFacesContext.getCurrentInstance().addPartialTarget(tableUsos);
           
           
           this.limpiaCampos();
           
           
           
          }catch(IdeamException e){
              showMessage(e);
          }
      }
    
    //pantalla de datos básicos Muestras
    public void cb_guardaLimitesNorma_actionListener(ActionEvent actionEvent) throws IdeamException{
    
        boolean continuar = true;
        
        if(this.seletPar.getValue()==null){
              showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.seletPar);       
              continuar = false;
          }
        
        if(this.selectSigno.getValue()==null){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.selectSigno);       
            continuar = false;
        }
        
        if(this.selectUnid.getValue()==null){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.selectUnid);       
            continuar = false;
        }
        
        
        if(this.selectSigno.getValue()!=null  && ( this.normaLimites.getListaSignos().getCodigo()!=582 &&  this.normaLimites.getListaSignos().getCodigo()!=583  ) ){
            
        if(this.it_valor.getValue()==null || 
                this.it_valor.getValue().toString().length()==0){
                continuar = false;
                showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.it_valor);
            } 
        
        }
        
        
        
        if(this.seletPar.getValue()!=null  && this.normaLimites.getListaSignos().getCodigo().equals(ConstantesCalidad.RANGO) ){
             if(this.it_valor2.getValue()==null || 
                    this.it_valor2.getValue().toString().length()==0){
                    continuar = false;
                    showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.it_valor2);
                } 
          
            }  
        
        if(this.seletPar.getValue()!=null  && this.normaLimites.getListaSignos().getCodigo().equals(ConstantesCalidad.VALOR_CHAR) ){
             if(this.it_valorChar.getValue()==null || 
                    this.it_valorChar.getValue().toString().length()==0){
                    continuar = false;
                    showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.it_valorChar);
                } 
          
            }
        
        
        
        
        
        if(continuar){   
            
            if (tableUsos.getSelectedRowData()==null){
                showMessage(getText("seleccionar_registro"),FacesMessage.SEVERITY_ERROR);
                return;
            }
            
            
            NormaUsos norma = (NormaUsos)tableUsos.getSelectedRowData();
            FacesUtils.setInSession("normaSeleccionada", norma);
             this.saveLimitesNorma();   
           
        }
    
    }
    
    
    public void cmi_editNorma_actionListener(ActionEvent actionEvent) {
        System.out.println("cmi_editNorma_actionListener-------------------------------------------");
        
        if (tableUsos.getSelectedRowData()==null){
            showMessage(getText("seleccionar_registro"),FacesMessage.SEVERITY_ERROR);
            return;
        }
        
        NormaUsos norma = (NormaUsos)tableUsos.getSelectedRowData();
        FacesUtils.setInSession("normaSeleccionada", norma);
        
        try{
            CalidadDelegate cld = CalidadDelegate.getInstance();
            Object obj = FacesUtils.getFromSession("normaSeleccionada");
             if(obj instanceof NormaUsos){
                       this.norma = (NormaUsos)obj;
                       Long codigoNorma=  this.norma.getId();
                       this.norma = cld.getNormaUsosId(codigoNorma); 
             }
            
            System.out.println("nnn------------------>norma"+this.norma.getId().intValue());
            
            
            this.cb_guardarNorma.setVisible(false);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.cb_guardarNorma); 
             
            this.cb_editarNorma.setVisible(true);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.cb_editarNorma); 
                        
            this.it_nombreNorma.setValue(this.norma.getNombreNorma());;
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_nombreNorma); 
            this.sc_usos.setValue(this.norma.getListaUsos());
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.sc_usos); 
            
            
            if (norma.getListaUsos().getCodigo().longValue() == ConstantesCalidad.USO_DOMESTICO)
                listaRiesgo =  this.getListaPorCategoria(ConstantesCalidad.NIVEL_RIESGO);
            else
                listaRiesgo =  this.getListaPorCategoria(ConstantesCalidad.NIVEL_RIESGODEF);
            
            this.sc_riesgo.setValue(this.norma.getListaRiesgo());
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.sc_riesgo); 
            
            this.sc_usos.setDisabled(true);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.sc_usos); 
            
            this.sc_riesgo.setDisabled(true);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.sc_riesgo); 
           
        }catch(IdeamException e){
            showMessage(e);
        }
       
    }
    
    
    public void cb_actualizarNorma_actionlistener(ActionEvent actionEvent) {
        
        boolean continuar = true;

        if(this.sc_riesgo.getValue()==null){
              showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.sc_riesgo);       
              continuar = false;
          }
        
        if(this.sc_usos.getValue()==null){
              showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.sc_usos);       
              continuar = false;
          }
        
        if(this.it_nombreNorma.getValue()==null){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.it_nombreNorma);       
            continuar = false;
        }
        if(continuar){   
              
        try{
           CalidadDelegate cld = CalidadDelegate.getInstance();
           Object obj = FacesUtils.getFromSession("normaSeleccionada");
         if(obj instanceof NormaUsos){
                   this.norma = (NormaUsos)obj;
                   Long codigoNorma=  this.norma.getId();
                   this.norma = cld.getNormaUsosId(codigoNorma); 
         }
            
            System.out.println("nnn-------nombreeeee----------->norma"+this.it_nombreNorma.getValue().toString());
            AdfFacesContext.getCurrentInstance().addPartialTarget(it_nombreNorma);
            this.norma.setNombreNorma(this.it_nombreNorma.getValue().toString().toUpperCase());
            System.out.println("nnn------------------>norma"+this.norma.getNombreNorma());
            this.norma.setListaUsos((Lista)this.sc_usos.getValue());
            this.norma.setListaRiesgo((Lista)this.sc_riesgo.getValue());
            
            norma= cld.updateNormaUsos(this.norma);
            this.limpiaCampos();
            
            UsuarioConectadoTO usuarioConectado = (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
            
            listadoNormas  = cld.getNormaUsosXAutoridad(usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue());
            
            AdfFacesContext.getCurrentInstance().addPartialTarget(tableUsos);
            
        }catch(IdeamException e){
            showMessage(e);
        }
        }  
         
         
    }
    
    
    public void limpiaCampos(){
        
        this.it_nombreNorma.setValue(null);
         AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_nombreNorma); 
         
                
         this.sc_usos.setDisabled(false);
         AdfFacesContext.getCurrentInstance().addPartialTarget(this.sc_usos);   
         
         this.sc_usos.setValue(null); 
         AdfFacesContext.getCurrentInstance().addPartialTarget(this.sc_usos); 
             
            this.sc_riesgo.setDisabled(false);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.sc_riesgo);   
            
            this.sc_riesgo.setValue(null);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.sc_riesgo);

            this.cb_guardarNorma.setVisible(true);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.cb_guardarNorma); 
             
            this.cb_editarNorma.setVisible(false);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.cb_editarNorma);
        
        
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelGroupLayout1);
             
        }

    
    
    public void saveLimitesNorma( ){
        
        System.out.println("eeee");
       try{
              normaLimites= new NormaLimites();
              Calendar miCalendario = Calendar.getInstance();
              Date eldia = miCalendario.getTime();
              CalidadDelegate cld = CalidadDelegate.getInstance();
           
              Object obj = FacesUtils.getFromSession("normaSeleccionada");
               if(obj instanceof NormaUsos){
                         this.norma = (NormaUsos)obj;
                         Long codigoNorma=  this.norma.getId();
                         this.norma = cld.getNormaUsosId(codigoNorma); 
               }
           
              System.out.println("eeee2");
             normaLimites.setFecha(eldia);
             normaLimites.setListaParametros((Lista)this.seletPar.getValue());
             normaLimites.setListaSignos((Lista)this.selectSigno.getValue());
             normaLimites.setListaUnidad((Lista)this.selectUnid.getValue());
             UsuarioConectadoTO usuarioConectado = (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
             normaLimites.setCodigoAutoridad(usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue());
             normaLimites.setIdParametro(normaLimites.getListaParametros().getCodigo());
             normaLimites.setIdSigno(normaLimites.getListaSignos().getCodigo());
             normaLimites.setIdUnidad(normaLimites.getListaUnidad().getCodigo());
             normaLimites.setIdNorma( this.norma.getId().intValue());
             normaLimites.setUso(this.norma.getUso());
             normaLimites.setRiesgo(this.norma.getRiesgo());
              
                  
              System.out.println("SIGNO----------"+this.normaLimites.getListaSignos().getCodigo().toString());
              
              if(this.normaLimites.getListaSignos().getCodigo().equals(ConstantesCalidad.RANGO)){
                  normaLimites.setValor(Double.parseDouble(this.it_valor.getValue().toString()));  
                  
                  System.out.println("this.it_valor2.getValue().toString() --------------------"+this.it_valor2.getValue().toString());
                  
                  
                  normaLimites.setValor2(Double.parseDouble(this.it_valor2.getValue().toString()));  
                  
              }else if(this.normaLimites.getListaSignos().getCodigo().equals(ConstantesCalidad.VALOR_CHAR)){
              
                  
                  System.out.println("this.it_valorChar.getValue().toString() ----------it_valorChar----------"+this.it_valorChar.getValue().toString());
                  
                  normaLimites.setValorChar(this.it_valorChar.getValue().toString());  
              }else {
                  
                      normaLimites.setValor(Double.parseDouble(this.it_valor.getValue().toString()));  
                       
                  }
                  
             normaLimites=   cld.updateNormaLimites(normaLimites);
             
             this.listarLimitesNorma();
        
             
          }catch(IdeamException e){
              showMessage(e);
          }
       
          showPopup(p_registro_lim, true);
      }



    public void tableUsos_selectionListener(SelectionEvent selectionEvent) {
   
        if (tableUsos.getSelectedRowData()==null){
            showMessage(getText("seleccionar_registro"),FacesMessage.SEVERITY_ERROR);
            return;
        }
        
        
        NormaUsos norma = (NormaUsos)tableUsos.getSelectedRowData();
        FacesUtils.setInSession("normaSeleccionada", norma);
        
        this.panelLimites.setVisible(true);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelLimites);      
        
        try{
            
               Calendar miCalendario = Calendar.getInstance();
               Date eldia = miCalendario.getTime();
               CalidadDelegate cld = CalidadDelegate.getInstance();
            
               Object obj = FacesUtils.getFromSession("normaSeleccionada");
                if(obj instanceof NormaUsos){
                          this.norma = (NormaUsos)obj;
                          Long codigoNorma=  this.norma.getId();
                          this.norma = cld.getNormaUsosId(codigoNorma); 
                }
              System.out.println("Normaaaaaaaaaaaaaaaa-------id ----->"+this.norma.getId());
              
              this.listarLimitesNorma();
            
        }catch(IdeamException e){
            showMessage(e);
        }
        
    }



    public void tableLimites_selectionListener(SelectionEvent selectionEvent) {
        System.out.println("*********************++entre tableLimites_selectionListener");
        
        if (tableLimites.getSelectedRowData()==null){
             showMessage(getText("seleccionar_registro"),FacesMessage.SEVERITY_ERROR);
             return;
         }
         
         NormaLimites lim = (NormaLimites)tableLimites.getSelectedRowData();
         FacesUtils.setInSession("limiteSeleccionado", lim);
          
        
        try{
            CalidadDelegate cld = CalidadDelegate.getInstance();
            Object obj = FacesUtils.getFromSession("limiteSeleccionado");
             if(obj instanceof NormaLimites){
                       this.normaLimites = (NormaLimites)obj;
                       Long codigoLim=  this.normaLimites.getId();
                       this.normaLimites = cld.getNormaLimitesId(codigoLim); 
            }
            UsuarioConectadoTO usuarioConectado = (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
          
             this.listaMediciones= cld.getListaMedicionsPorParametro( this.normaLimites.getIdParametro().longValue(),usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue() );
            System.out.println("*********************++entre this.listaMediciones.size()*******"+this.listaMediciones.size());
            
            if (this.listaMediciones.size()>0){
                
                this.cb_grafico.setVisible(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(this.cb_grafico);      
            }else{
                    this.cb_grafico.setVisible(false);
                    AdfFacesContext.getCurrentInstance().addPartialTarget(this.cb_grafico);   
                
                }
            
        }catch(IdeamException e){
            showMessage(e);
        }
        
    }

    public void graficar_actionListener(ActionEvent actionEvent) {
        
        System.out.println("*********************++entre graficar_actionListener");
        if (tableLimites.getSelectedRowData()==null){
             showMessage(getText("seleccionar_registro"),FacesMessage.SEVERITY_ERROR);
             return;
         }
         
         NormaLimites lim = (NormaLimites)tableLimites.getSelectedRowData();
         FacesUtils.setInSession("limiteSeleccionado", lim);
         accionGraficar = "graficarNorma";
          
    }

    public void cb_no_borrar_actionListener(ActionEvent actionEvent) {
        showPopup(popup_borrar, false);
    }
    
    public void cb_si_borrar_actionListener(ActionEvent actionEvent) {
        try{
            CalidadDelegate cld = CalidadDelegate.getInstance();
            cld.deleteNormaUsos(this.norma);
            showMessage(getText("NORMA_ELIMINADA"));
            UsuarioConectadoTO usuarioConectado = (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
            
            listadoNormas  = cld.getNormaUsosXAutoridad(usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue());
            AdfFacesContext.getCurrentInstance().addPartialTarget(tableUsos);
            
            
        }catch(IdeamException e){
            
            showMessage(e.getMessage());
        }
    }

    public void cmi_borrar_norma_actionListener(ActionEvent actionEvent) {
        
            if (tableUsos.getSelectedRowData()==null){
                showMessage(getText("seleccionar_registro"),FacesMessage.SEVERITY_ERROR);
                return;
            }
      
            
            NormaUsos norma = (NormaUsos)tableUsos.getSelectedRowData();
            FacesUtils.setInSession("normaSeleccionada", norma);
         try{ 
             
            CalidadDelegate cld = CalidadDelegate.getInstance();
                
            Object obj = FacesUtils.getFromSession("normaSeleccionada");
             if(obj instanceof NormaUsos){
                       this.norma = (NormaUsos)obj;
                       Long codigoNorma=  this.norma.getId();
                       this.norma = cld.getNormaUsosId(codigoNorma); 
             }
        
        String nombre  = this.norma.getNombreNorma();
        
        String params[] = {nombre};
        String texto = getText("NORMA_ELIMINAR", params);
        ot_borrar1.setValue(texto);
        ot_borrar2.setValue(nombre);
      
        List<NormaLimites> nl = cld.getNormaLimites(this.norma.getId().intValue(),this.norma.getUso(), this.norma.getRiesgo());
        
        if (nl!=null && nl.size()>0){
            showMessage(getText("LIMITES_ASOCIADOS"));
            
        }else{
            AdfFacesContext.getCurrentInstance().addPartialTarget(popup_borrar);
            showPopup(popup_borrar, true);
        }        
         
        
        
            }catch(IdeamException e){
                
                showMessage(e.getMessage());
            }
        
        
        
            
        }
    public void listarLimitesNorma(){
        
        this.seletPar.setValue(null);
        this.selectSigno.setValue(null);
        this.selectUnid.setValue(null);
        this.it_valor.setValue(null);
        
        
        this.it_valor2.setValue(null);
        this.it_valorChar.setValue(null);
        
        
        this.it_valor2.setVisible(false);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_valor2); 
      
        this.it_valorChar.setVisible(false);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_valorChar);   
        
        
        this.cb_guardar.setVisible(true);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.cb_guardar); 
         
        this.cb_actualizarLim.setVisible(false);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.cb_actualizarLim); 
                  
        
        
        try{
        CalidadDelegate cld = CalidadDelegate.getInstance();
        Object obj = FacesUtils.getFromSession("normaSeleccionada");
              
              if(obj instanceof NormaUsos){
                   this.norma = (NormaUsos)obj;
                   Long codigoNorma=  this.norma.getId();
                   this.norma = cld.getNormaUsosId(codigoNorma); 
              }
        
              this.listadoLimitesParametros= new ArrayList();
              this.listadoLimitesParametros =  cld.getNormaLimites(this.norma.getId().intValue(), this.norma.getUso(), this.norma.getRiesgo());
         }catch(IdeamException e){
              showMessage(e);
       }
        AdfFacesContext.getCurrentInstance().addPartialTarget(tableLimites);
        
       
    }
    
    
    public void cmi_borrarLimite_actionListener(ActionEvent actionEvent) {
       if (tableLimites.getSelectedRowData()==null){
            showMessage(getText("seleccionar_registro"),FacesMessage.SEVERITY_ERROR);
            return;
        }
        
        NormaLimites lim = (NormaLimites)tableLimites.getSelectedRowData();
        FacesUtils.setInSession("limiteSeleccionado", lim);
       
        try{
         
        CalidadDelegate cld = CalidadDelegate.getInstance();
        Object obj = FacesUtils.getFromSession("limiteSeleccionado");
         if(obj instanceof NormaLimites){
                   this.normaLimites = (NormaLimites)obj;
                   Long codigoLim=  this.normaLimites.getId();
                   this.normaLimites = cld.getNormaLimitesId(codigoLim); 
        }
        
        String nombre  = this.normaLimites.getListaParametros().getValor();
        String params[] = {nombre};
        String texto = getText("LIMITE_ELIMINAR", params);
        ac_borrar1.setValue(texto);
        ac_borrar2.setValue(nombre);
            
        AdfFacesContext.getCurrentInstance().addPartialTarget(p_elim_lim);
        showPopup(p_elim_lim, true);
        }catch(IdeamException e){
            showMessage(e.getMessage());
        }
     }
    
    
    public void cb_si_borrarLim_actionListener(ActionEvent actionEvent) {
         try{
                CalidadDelegate cld = CalidadDelegate.getInstance();
                cld.deleteNormaLimites(this.normaLimites);
                showMessage(getText("LIMITE_ELIMINADO"));
                this.listadoLimitesParametros =  cld.getNormaLimites(this.norma.getId().intValue(), this.norma.getUso(), this.norma.getRiesgo());
                AdfFacesContext.getCurrentInstance().addPartialTarget(tableUsos);
        }catch(IdeamException e){
        showMessage(e);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(tableLimites);
    }

    public void cb_no_borrarLim_actionListener(ActionEvent actionEvent) {
         showPopup(p_elim_lim, false);
    }
    
    
    

    public void cmi_editarLimite_actionListener(ActionEvent actionEvent) {
        System.out.println("cmi_editarLimite_actionListener-------------------------------------------");
        if (tableLimites.getSelectedRowData()==null){
             showMessage(getText("seleccionar_registro"),FacesMessage.SEVERITY_ERROR);
             return;
         }
         
         NormaLimites lim = (NormaLimites)tableLimites.getSelectedRowData();
         FacesUtils.setInSession("limiteSeleccionado", lim);
        
         try{
          
         CalidadDelegate cld = CalidadDelegate.getInstance();
         Object obj = FacesUtils.getFromSession("limiteSeleccionado");
          if(obj instanceof NormaLimites){
                    this.normaLimites = (NormaLimites)obj;
                    Long codigoLim=  this.normaLimites.getId();
                    this.normaLimites = cld.getNormaLimitesId(codigoLim); 
         }
         
          
            this.cb_guardar.setVisible(false);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.cb_guardar); 
             
            this.cb_actualizarLim.setVisible(true);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.cb_actualizarLim); 
                      
            
            this.seletPar.setValue(this.normaLimites.getListaParametros());
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.seletPar); 
                  
            this.selectSigno.setValue(this.normaLimites.getListaSignos());
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.selectSigno); 
            
            this.selectUnid.setValue(this.normaLimites.getListaUnidad());
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.selectUnid); 
             
             
                        
             
            if(this.normaLimites.getListaSignos().getCodigo().equals(ConstantesCalidad.RANGO)){
               this.it_valor.setVisible(true);
               AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_valor); 
               this.it_valor.setValue(this.normaLimites.getValor());           
               AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_valor);
               this.it_valor2.setVisible(true);
               AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_valor2); 
               this.it_valor2.setValue(this.normaLimites.getValor2());
               AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_valor2); 
             }
            else if(this.normaLimites.getListaSignos().getCodigo().equals(ConstantesCalidad.VALOR_CHAR)){
             
             
               this.it_valor.setVisible(false);
               AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_valor); 
            
               this.it_valor2.setVisible(false);
               AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_valor2); 
                 
             
               this.it_valorChar.setVisible(true);
               AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_valorChar); 
               this.it_valorChar.setValue(this.normaLimites.getValorChar().toString());
               AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_valorChar); 
             }
             else{
                
                this.it_valor.setVisible(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_valor); 
                this.it_valor.setValue(this.normaLimites.getValor().toString());
                AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_valor); 
                
                this.it_valorChar.setVisible(false);
                AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_valorChar); 
                
               this.it_valor2.setVisible(false);
               AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_valor2); 
            }
             
                      
                 
           
           
        }catch(IdeamException e){
            showMessage(e);
        }
    }


    public void cb_actualizarLim_actionlistener(ActionEvent actionEvent) {
        boolean continuar = true;
        
        if(this.seletPar.getValue()==null){
              showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.seletPar);       
              continuar = false;
          }
        
        if(this.selectSigno.getValue()==null){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.selectSigno);       
            continuar = false;
        }
        
        if(this.selectUnid.getValue()==null){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.selectUnid);       
            continuar = false;
        }
        
        if(this.it_valor.getValue()==null){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.it_valor);       
            continuar = false;
        } 
        
        if(continuar){   
            
            try{
             
            CalidadDelegate cld = CalidadDelegate.getInstance();
            Object obj = FacesUtils.getFromSession("limiteSeleccionado");
             if(obj instanceof NormaLimites){
                       this.normaLimites = (NormaLimites)obj;
                       Long codigoLim=  this.normaLimites.getId();
                       this.normaLimites = cld.getNormaLimitesId(codigoLim); 
            }
            
            normaLimites.setListaParametros((Lista)this.seletPar.getValue());
            normaLimites.setListaSignos((Lista)this.selectSigno.getValue());
            normaLimites.setListaUnidad((Lista)this.selectUnid.getValue());
            UsuarioConectadoTO usuarioConectado = (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
            normaLimites.setCodigoAutoridad(usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue());
            normaLimites.setIdParametro(normaLimites.getListaParametros().getCodigo());
            normaLimites.setIdSigno(normaLimites.getListaSignos().getCodigo());
            normaLimites.setIdUnidad(normaLimites.getListaUnidad().getCodigo());
            normaLimites.setIdNorma( this.norma.getId().intValue());
            normaLimites.setUso(this.norma.getUso());
            normaLimites.setRiesgo(this.norma.getRiesgo());
                
                
            if(this.normaLimites.getListaSignos().getCodigo().equals(ConstantesCalidad.RANGO)){
                normaLimites.setValor(Double.parseDouble(this.it_valor.getValue().toString()));  
                normaLimites.setValor2(Double.parseDouble(this.it_valor2.getValue().toString()));  
                
           }  
            else if(this.normaLimites.getListaSignos().getCodigo().equals(ConstantesCalidad.VALOR_CHAR)){
                normaLimites.setValorChar(this.it_valorChar.getValue().toString());  
            }else {
                
                    normaLimites.setValor(Double.parseDouble(this.it_valor.getValue().toString()));  
                     
                }
                
                
                
                
                
            normaLimites=   cld.updateNormaLimites(normaLimites);
            
            this.listarLimitesNorma();
          
            
        }catch(IdeamException e){
            showMessage(e);
        }
        }  
         
    }




        public void signo_valueChangeListener(ValueChangeEvent valueChangeEvent) {
           System.out.println("bbbbbbbbbbbbbbbbbbbbb---------entree------------->>");
               valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
               normaLimites.setListaSignos((Lista)(this.selectSigno.getValue())); 
               
            System.out.println("bbbbbbbbbbbbbbbbbbbbb---------entree------------->>"+ normaLimites.getListaSignos().getValor());
            
               
                if(this.normaLimites.getListaSignos()!=null){
                
                 if(this.normaLimites.getListaSignos().getCodigo().equals(ConstantesCalidad.RANGO)){
                     
                        this.it_valor.setVisible(true);
                        AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_valor); 
                        this.it_valor2.setVisible(true);
                        AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_valor2); 
                        this.it_valorChar.setVisible(false);
                        AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_valorChar); 
                     
                }else if(this.normaLimites.getListaSignos().getCodigo().equals(ConstantesCalidad.VALOR_CHAR)) {
                         this.it_valorChar.setVisible(true);
                         AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_valorChar); 
                         this.it_valor.setVisible(false);
                         AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_valor); 
                         this.it_valor2.setVisible(false);
                         AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_valor2); 
                     }
                 
                 
                 else {
                         this.it_valor.setVisible(true);
                         AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_valor);
                         this.it_valorChar.setVisible(false);
                         AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_valorChar); 
                         this.it_valor2.setVisible(false);
                         AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_valor2); 
                     }
                }
        }   




    public void setListaUsos(List listaUsos) {
        this.listaUsos = listaUsos;
    }

    public List getListaUsos() {
        return listaUsos;
    }

    public void setNorma(NormaUsos norma) {
        this.norma = norma;
    }

    public NormaUsos getNorma() {
        return norma;
    }


    public void setListadoNormas(List listadoNormas) {
        this.listadoNormas = listadoNormas;
    }

    public List getListadoNormas() {
        return listadoNormas;
    }


    public void setTableUsos(RichTable table1) {
        this.tableUsos = table1;
    }

    public RichTable getTableUsos() {
        return tableUsos;
    }

    public void setMenu1(RichMenu menu1) {
        this.menu1 = menu1;
    }

    public RichMenu getMenu1() {
        return menu1;
    }

    public void setCmi_editNorma(RichCommandMenuItem commandMenuItem1) {
        this.cmi_editNorma = commandMenuItem1;
    }

    public RichCommandMenuItem getCmi_editNorma() {
        return cmi_editNorma;
    }

    public void setNormaLimites(NormaLimites normaLimites) {
        this.normaLimites = normaLimites;
    }

    public NormaLimites getNormaLimites() {
        return normaLimites;
    }

    public void setTableLimites(RichTable table1) {
        this.tableLimites = table1;
    }

    public RichTable getTableLimites() {
        return tableLimites;
    }

    public void setListadoLimitesParametros(List listadoLimitesParametros) {
        this.listadoLimitesParametros = listadoLimitesParametros;
    }

    public List getListadoLimitesParametros() {
        return listadoLimitesParametros;
    }

  

    public void setPanelStretchLayout4(RichPanelStretchLayout panelStretchLayout4) {
        this.panelStretchLayout4 = panelStretchLayout4;
    }

    public RichPanelStretchLayout getPanelStretchLayout4() {
        return panelStretchLayout4;
    }

   

    public void setImage1(RichImage image1) {
        this.image1 = image1;
    }

    public RichImage getImage1() {
        return image1;
    }


    public void setP_registro_lim(RichPopup p_registro_lim) {
        this.p_registro_lim = p_registro_lim;
    }

    public RichPopup getP_registro_lim() {
        return p_registro_lim;
    }

    public void setD_registro_lim(RichDialog d_registro_lim) {
        this.d_registro_lim = d_registro_lim;
    }

    public RichDialog getD_registro_lim() {
        return d_registro_lim;
    }

    public void setT_registro_lim(RichActiveOutputText t_registro_lim) {
        this.t_registro_lim = t_registro_lim;
    }

    public RichActiveOutputText getT_registro_lim() {
        return t_registro_lim;
    }

    public void setPanelGroupLayout2(RichPanelGroupLayout panelGroupLayout2) {
        this.panelGroupLayout2 = panelGroupLayout2;
    }

    public RichPanelGroupLayout getPanelGroupLayout2() {
        return panelGroupLayout2;
    }

    public void setCb_aceptar_lim(RichCommandButton cb_aceptar_lim) {
        this.cb_aceptar_lim = cb_aceptar_lim;
    }

    public RichCommandButton getCb_aceptar_lim() {
        return cb_aceptar_lim;
    }

    public void setCmi_borrar(RichCommandMenuItem commandMenuItem1) {
        this.cmi_borrar = commandMenuItem1;
    }

    public RichCommandMenuItem getCmi_borrar() {
        return cmi_borrar;
    }


    public void setPopup_borrar(RichPopup popup1) {
        this.popup_borrar = popup1;
    }

    public RichPopup getPopup_borrar() {
        return popup_borrar;
    }

    public void setD_confirmar_borrado(RichDialog dialog1) {
        this.d_confirmar_borrado = dialog1;
    }

    public RichDialog getD_confirmar_borrado() {
        return d_confirmar_borrado;
    }

    public void setPanelGroupLayout4(RichPanelGroupLayout panelGroupLayout4) {
        this.panelGroupLayout4 = panelGroupLayout4;
    }

    public RichPanelGroupLayout getPanelGroupLayout4() {
        return panelGroupLayout4;
    }

    public void setOt_borrar1(RichActiveOutputText activeOutputText1) {
        this.ot_borrar1 = activeOutputText1;
    }

    public RichActiveOutputText getOt_borrar1() {
        return ot_borrar1;
    }

    public void setSpacer1(RichSpacer spacer1) {
        this.spacer1 = spacer1;
    }

    public RichSpacer getSpacer1() {
        return spacer1;
    }

    public void setOt_borrar2(RichActiveOutputText activeOutputText2) {
        this.ot_borrar2 = activeOutputText2;
    }

    public RichActiveOutputText getOt_borrar2() {
        return ot_borrar2;
    }


    public void setPanelGroupLayout5(RichPanelGroupLayout panelGroupLayout5) {
        this.panelGroupLayout5 = panelGroupLayout5;
    }

    public RichPanelGroupLayout getPanelGroupLayout5() {
        return panelGroupLayout5;
    }

    public void setCb_si_borrar(RichCommandButton commandButton1) {
        this.cb_si_borrar = commandButton1;
    }

    public RichCommandButton getCb_si_borrar() {
        return cb_si_borrar;
    }

    public void setCb_no_borrar(RichCommandButton commandButton2) {
        this.cb_no_borrar = commandButton2;
    }

    public RichCommandButton getCb_no_borrar() {
        return cb_no_borrar;
    }

    public void setSpacer2(RichSpacer spacer2) {
        this.spacer2 = spacer2;
    }

    public RichSpacer getSpacer2() {
        return spacer2;
    }

    public void setCb_editarNorma(RichCommandButton commandButton1) {
        this.cb_editarNorma = commandButton1;
    }

    public RichCommandButton getCb_editarNorma() {
        return cb_editarNorma;
    }


    public void setMenu2(RichMenu menu2) {
        this.menu2 = menu2;
    }

    public RichMenu getMenu2() {
        return menu2;
    }

   

 


    public void setPanelGroupLayout6(RichPanelGroupLayout panelGroupLayout6) {
        this.panelGroupLayout6 = panelGroupLayout6;
    }

    public RichPanelGroupLayout getPanelGroupLayout6() {
        return panelGroupLayout6;
    }

    public void setAc_borrar1(RichActiveOutputText activeOutputText1) {
        this.ac_borrar1 = activeOutputText1;
    }

    public RichActiveOutputText getAc_borrar1() {
        return ac_borrar1;
    }

    public void setAc_borrar2(RichActiveOutputText activeOutputText2) {
        this.ac_borrar2 = activeOutputText2;
    }

    public RichActiveOutputText getAc_borrar2() {
        return ac_borrar2;
    }

    public void setSpacer3(RichSpacer spacer3) {
        this.spacer3 = spacer3;
    }

    public RichSpacer getSpacer3() {
        return spacer3;
    }

    public void setPanelGroupLayout7(RichPanelGroupLayout panelGroupLayout7) {
        this.panelGroupLayout7 = panelGroupLayout7;
    }

    public RichPanelGroupLayout getPanelGroupLayout7() {
        return panelGroupLayout7;
    }

    public void setCb_si_borraLim(RichCommandButton commandButton1) {
        this.cb_si_borraLim = commandButton1;
    }

    public RichCommandButton getCb_si_borraLim() {
        return cb_si_borraLim;
    }

    public void setCb_no_borraLim(RichCommandButton commandButton2) {
        this.cb_no_borraLim = commandButton2;
    }

    public RichCommandButton getCb_no_borraLim() {
        return cb_no_borraLim;
    }

    public void setSpacer4(RichSpacer spacer4) {
        this.spacer4 = spacer4;
    }

    public RichSpacer getSpacer4() {
        return spacer4;
    }


    public void setCmi_editarLimite(RichCommandMenuItem cmi_editarLimite) {
        this.cmi_editarLimite = cmi_editarLimite;
    }

    public RichCommandMenuItem getCmi_editarLimite() {
        return cmi_editarLimite;
    }

    public void setCmi_borrarLimite(RichCommandMenuItem cmi_borrarLimite) {
        this.cmi_borrarLimite = cmi_borrarLimite;
    }

    public RichCommandMenuItem getCmi_borrarLimite() {
        return cmi_borrarLimite;
    }

    public void setP_elim_lim(RichPopup p_elim_lim) {
        this.p_elim_lim = p_elim_lim;
    }

    public RichPopup getP_elim_lim() {
        return p_elim_lim;
    }

    public void setD_elim_lim(RichDialog d_elim_lim) {
        this.d_elim_lim = d_elim_lim;
    }

    public RichDialog getD_elim_lim() {
        return d_elim_lim;
    }

    public void setCb_actualizarLim(RichCommandButton commandButton1) {
        this.cb_actualizarLim = commandButton1;
    }

    public RichCommandButton getCb_actualizarLim() {
        return cb_actualizarLim;
    }


    public void setIt_valor2(RichInputText inputText1) {
        this.it_valor2 = inputText1;
    }

    public RichInputText getIt_valor2() {
        return it_valor2;
    }

    public void setIt_valorChar(RichInputText inputText1) {
        this.it_valorChar = inputText1;
    }

    public RichInputText getIt_valorChar() {
        return it_valorChar;
    }


    public void setPanelFormLayout2(RichPanelFormLayout panelFormLayout2) {
        this.panelFormLayout2 = panelFormLayout2;
    }

    public RichPanelFormLayout getPanelFormLayout2() {
        return panelFormLayout2;
    }

    public void setCb_grafico(RichCommandButton commandButton1) {
        this.cb_grafico = commandButton1;
    }

    public RichCommandButton getCb_grafico() {
        return cb_grafico;
    }

 

    public void setListaMediciones(List listaMediciones) {
        this.listaMediciones = listaMediciones;
    }

    public List getListaMediciones() {
        return listaMediciones;
    }

    public void setAccionGraficar(String accionGraficar) {
        this.accionGraficar = accionGraficar;
    }

    public String getAccionGraficar() {
        return accionGraficar;
    }

    public void setSc_riesgo(RichSelectOneChoice selectOneChoice1) {
        this.sc_riesgo = selectOneChoice1;
    }

    public RichSelectOneChoice getSc_riesgo() {
        return sc_riesgo;
    }

    public void setSelectItems2(UISelectItems selectItems2) {
        this.selectItems2 = selectItems2;
    }

    public UISelectItems getSelectItems2() {
        return selectItems2;
    }

    public void setListaRiesgo(List listaRiesgo) {
        this.listaRiesgo = listaRiesgo;
    }

    public List getListaRiesgo() {
        return listaRiesgo;
    }

}
