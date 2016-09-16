package co.gov.ideam.sirh.calidad.view;

import co.gov.ideam.sirh.auditoria.model.Auditoria;
import co.gov.ideam.sirh.auditoria.view.AuditoriasDelegate;
import co.gov.ideam.sirh.calidad.model.Medicion;
import co.gov.ideam.sirh.calidad.model.Muestra;
import co.gov.ideam.sirh.calidad.model.PuntoMonitoreo;
import co.gov.ideam.sirh.calidad.model.PuntoMonitoreoTO;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.seguridad.model.PerfilVO;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.seguridad.view.SeguridadDelegate;
import co.gov.ideam.sirh.util.ConstantesCalidad;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;

import co.gov.ideam.sirh.view.StandarBean;

import java.util.ArrayList;

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
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.nav.RichCommandMenuItem;
import oracle.adf.view.rich.component.rich.output.RichActiveOutputText;
import oracle.adf.view.rich.component.rich.output.RichImage;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.PopupFetchEvent;


public class DetalleMuestraBean  extends StandarBean{
    private RichForm form1;
    private RichDocument document1;
    private RichPanelStretchLayout panelStretchLayout1;
    private RichPanelGroupLayout panelGroupLayout1;
    private RichPanelBox panelBox1;
    private RichPanelStretchLayout panelStretchLayout2;
    private RichPanelFormLayout panelFormLayout1;
    private RichSpacer spacer2;
    private RichActiveOutputText tituloPanelMuestra;
    private RichSelectOneChoice sc_tipoMuestra;
    private UISelectItems selectItems1;
    private RichSelectOneChoice sc_laboratorio;
    private UISelectItems selectItems2;
    private RichInputDate id_fecha;
    private RichInputText it_caudal;
    private RichInputText it_duracion;
    private RichInputText it_verticales;
    private RichInputText it_submuestras;
    private RichInputText it_intervaloTiempo;
    private RichActiveOutputText tituloMuestra;
    private Muestra muestra;
    private PuntoMonitoreo puntoM;
    private PuntoMonitoreoTO puntoMonitoreoTO;
    private Medicion medicion;
    private List listaTipoMuestra;
    private List listaLaboratorio;
    private List listaHora;
    private List listaMin;
    private List listaHorario;
    private List listaParametro;
    private List listaUnidad;
    private List listaSigno;
    private List listaMediciones;
    private RichPanelGroupLayout panelGroupLayout5;
    private RichSelectOneChoice sc_hora;
    private UISelectItems selectItems3;
    private RichSelectOneChoice sc_min;
    private UISelectItems selectItems4;
    private RichSelectOneChoice sc_horario;
    private UISelectItems selectItems5;
    private RichPanelGroupLayout pgdatosBasicos;
    private RichPanelFormLayout formDatosBasicos;
    private RichPanelGroupLayout panelGFecha;
    private RichPanelFormLayout formFecha;
    private RichPanelGroupLayout panelGTipoMuestra;
    private RichPanelFormLayout formTipoMuestra;
    private RichPanelGroupLayout panelGHora;
    private RichPanelFormLayout formHora;
    private RichSpacer spacer3;
    private RichSpacer spacer6;
    private RichSpacer spacer5;
    private RichPanelGroupLayout panelGroupLayout6;
    private RichPanelStretchLayout panelStretchLayout4;
    private RichPanelSplitter panelSplitter1;
    private RichPanelGroupLayout panelGroupLayout7;
    private RichSpacer spacer4;
    private RichActiveOutputText activeOutputText3;
    private RichSpacer spacer7;
    private RichPanelFormLayout panelFormLayout2;
    private RichSelectOneChoice sc_parametros;
    private UISelectItems selectItems6;
    private RichSelectOneChoice sc_unidad;
    private UISelectItems selectItems7;
    private RichSelectOneChoice sc_signo;
    private UISelectItems selectItems8;
    private RichInputText it_metodo;
    private RichInputText it_limite;
    private RichInputText it_valor;
    private RichSelectBooleanCheckbox check_acreditado;
    private RichActiveOutputText ot_parametro;
    private RichActiveOutputText ot_unidad;
    private RichActiveOutputText ot_signo;
    private RichActiveOutputText ot_valor;
    private RichActiveOutputText ot_metodo;
    private RichActiveOutputText ot_limite;
    private RichActiveOutputText ot_acreditado;
    private RichPanelCollection panelCollection1;
    private RichTable t_mediciones;
    private RichPanelGroupLayout panelGroupLayout8;
    private RichSpacer spacer8;
    private RichCommandButton cb_agregar;
    private RichPopup p_registro_act;
    private RichDialog d_registro_act;
    private RichPanelStretchLayout panelStretchLayout5;
    private RichActiveOutputText t_registro_act;
    private RichImage image2;
    private RichPanelGroupLayout panelGroupLayout9;
    private RichCommandButton cb_aceptar_muestra;
    private RichSpacer spacer9;
    private RichCommandLink commandLink1;
    private RichSpacer spacer10;
    private RichCommandLink commandLink2;
    private String visibleOtro=null;
    private String visibleCompuesta=null;
    private String visibleIntegrada=null;
    private RichPanelGroupLayout panelGroupParametros;
    private RichPanelGroupLayout panelGroupLayout3;
    private RichCommandButton cb_actualizar;
    private RichSpacer spacer1;
    private RichCommandButton cb_borrar;
    private RichMenu menu_opciones;
    private RichCommandMenuItem cmi_editar;
    private RichCommandMenuItem cmi_eliminar;
    private RichCommandButton cb_editar;
    private RichPanelBox panelBoxParametros;
    private RichPanelStretchLayout panelStretchLayout3;
    private RichSpacer spacer13;
    private RichPanelFormLayout panelFormLayout3;
    private RichPanelGroupLayout panelGroupLayout2;
    private RichSpacer spacer12;
    private RichActiveOutputText ot_valor2;
    private RichInputText it_valor2;
    private RichPopup p_actualizar_med;
    private RichDialog d_act_med;
    private RichPanelStretchLayout panelStretchLayout6;
    private RichActiveOutputText otext_msj_med;
    private RichImage image1;
    private RichPanelGroupLayout panelGroupLayout4;
    private RichCommandButton cb_acept_med;
    private RichPopup popup_borrar;
    private RichDialog d_confirmar_borrado;
    private RichPanelGroupLayout panelGroupLayout10;
    private RichActiveOutputText ot_borrar1;
    private RichSpacer spacer14;
    private RichActiveOutputText ot_borrar2;
    private RichPanelGroupLayout panelGroupLayout11;
    private RichCommandButton cb_si_borrar;
    private RichSpacer spacer15;
    private RichCommandButton cb_no_borrar;
    private RichPopup popup_borrar_med;
    private RichDialog d_borrar_med;
    private RichPanelGroupLayout panelGroupLayout12;
    private RichActiveOutputText ot_medborrar1;
    private RichSpacer spacer16;
    private RichActiveOutputText ot_medborrar2;
    private RichPanelGroupLayout panelGroupLayout13;
    private RichCommandButton cb_si_borrar_med;
    private RichSpacer spacer17;
    private RichCommandButton cb_no_borrar_med;
    private RichSpacer spacer18;
    private RichInputText it_nroMuestra;
    private RichActiveOutputText ot_char;
    private RichInputText it_char;
    private RichInputText it_otro_lab;
    private RichInputText it_tipoMuestraVert;
    private Long idvert;
    private Integer  nro;
    private List listaMuestras;
    
    public DetalleMuestraBean(){
       FacesUtils.setActiveBean("detalleMuestra","detalleMuestra", CalidadDelegate.class);
   
       this.load();        
    }
    
    
    public void load(){
    
               this.medicion = new Medicion();
               this.it_otro_lab= new RichInputText();
               it_tipoMuestraVert= new RichInputText(); 
               sc_tipoMuestra= new RichSelectOneChoice();
               
               
                UsuarioConectadoTO usuarioConectado = new UsuarioConectadoTO();
                try{
                    usuarioConectado = 
                                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

                    if(usuarioConectado==null){
                        SeguridadDelegate seg = SeguridadDelegate.getInstance(); 
                        PerfilVO pp= new PerfilVO();
                        pp=   seg.getPerfilUsuario(usuarioConectado.getUsuario().
                                                                 getAutoridadAmbiental().
                                                                 getId().
                                                                 longValue());
                        
                        System.out.println("----perfil----UsuarioConectadoTO"+   pp.getCodigo() +"------nombre "+pp.getNombre());
                        
                      //  if ()
                        
                    }     
                    }catch(Exception e){
                       System.out.println("++++++++++++++++e"+e);
                    }
                  try{      
                 
                 
                 
                 
                 
                 
                    CalidadDelegate cald = CalidadDelegate.getInstance();
                    Object obj = FacesUtils.getFromSession("puntoSeleccionado");
                        
                   
                   
                   
                   
                   
                   
                      
                      if(obj instanceof PuntoMonitoreo){
                             this.puntoM = (PuntoMonitoreo)obj;
                             Long codigoPunto=  this.puntoM.getId();
                             this.puntoM = cald.getPuntoMonitoreo(codigoPunto); 
                      }  else if(obj instanceof PuntoMonitoreoTO){
                             this.puntoMonitoreoTO = (PuntoMonitoreoTO)obj;
                             Long codigoPunto=  this.puntoMonitoreoTO.getId();
                             this.puntoM = cald.getPuntoMonitoreo(codigoPunto); 
                      }      
                      Object obj2 = FacesUtils.getFromSession("muestraSeleccionada");
                      
                      if(obj2 instanceof Muestra){
                           this.muestra = (Muestra)obj2;
                           Long codigo=  this.muestra.getId();
                           System.out.println("__PG CODIGO DE LA MUESTRA:"+codigo);
                           this.muestra = cald.getMuestra(codigo);  
                          
                      }
                    this.listaMediciones= cald.getMediciones(this.muestra);
                      System.out.println("__PG MEDICIONES DE LA MUESTRA listaMediciones:"+listaMediciones.size());
                    this.listaTipoMuestra = this.getListaPorCategoria(ConstantesCalidad.TIPO_MUESTRA);
                    this.listaLaboratorio = this.getListaPorCategoria(ConstantesCalidad.LABORATORIO);
                    this.listaParametro= this.getListaPorCategoria(ConstantesCalidad.PARAMETROS_CALIDAD);
                    this.listaUnidad= this.getListaPorCategoria(ConstantesCalidad.UNIDADES_CALIDAD);   
                    this.listaSigno= this.getListaPorCategoria(ConstantesCalidad.SIGNOS);
                    this.listaHorario= this.cargarHorario();
                    this.listaHora=this.cargarHoras();
                    this.listaMin= this.cargarMin();
                    
                    if(this.muestra.getIdTipoMuestra()!=null){
                         cambiaTipoMuestra();
                      }
                       
                  }catch(IdeamException e){
                      showMessage(e);
                  }
      
      
        
      
      
    }
    

    
    public void lab_valueChageListener(ValueChangeEvent valueChangeEvent) {
        
         
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        muestra.setLaboratorio((Lista)(this.sc_laboratorio.getValue())); 
        
        
         if(this.muestra.getLaboratorio()!=null){
             if(this.muestra.getLaboratorio().getCodigo().equals(ConstantesCalidad.OTRO_LAB)){
             
             
                 this.it_otro_lab.setVisible(true);
                 AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_otro_lab); 
              
              }  else{
                 
                     this.it_otro_lab.setVisible(false);
                     AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_otro_lab); 
                 
                    }  
        }
    }
    
    public void cambiaTipoMuestra(){
            if(this.muestra.getLaboratorio().getCodigo().equals(ConstantesCalidad.OTRO_LAB)){
               this.it_otro_lab.setValue(this.muestra.getOtroLaboratorio().toString());
                  visibleOtro ="true";
             
             }  else{
                    visibleOtro ="false";
                
                   }  
        
       
       
      if(this.muestra.getIdTipoMuestra()!=null){
           if(this.muestra.getIdTipoMuestra().equals(ConstantesCalidad.MUESTRA_SIMPLE)){
              visibleCompuesta ="false";
               visibleIntegrada ="false";
               this.it_tipoMuestraVert.setVisible(false);
               AdfFacesContext.getCurrentInstance().addPartialTarget(it_tipoMuestraVert);
           }           
          else  if(this.muestra.getIdTipoMuestra().equals(ConstantesCalidad.MUESTRA_INTEGRADA)){
                  visibleCompuesta ="false";
                  visibleIntegrada="true";
               this.it_tipoMuestraVert.setVisible(false);
               AdfFacesContext.getCurrentInstance().addPartialTarget(it_tipoMuestraVert);
           } 
          else  if(this.muestra.getIdTipoMuestra().equals(ConstantesCalidad.MUESTRA_COMPUESTA)){
                visibleCompuesta ="true";
                visibleIntegrada="false";
                this.it_tipoMuestraVert.setVisible(false);
                AdfFacesContext.getCurrentInstance().addPartialTarget(it_tipoMuestraVert);
                
            }
           
           else if(this.muestra.getIdTipoMuestra().equals(ConstantesCalidad.CARATERIZACION_INICIAL)){
                  
                   this.it_tipoMuestraVert.setValue(this.muestra.getTipoMuestra().getValor());
                   this.it_tipoMuestraVert.setVisible(true);
                   AdfFacesContext.getCurrentInstance().addPartialTarget(it_tipoMuestraVert);
                   
                   this.sc_tipoMuestra.setVisible(false);
                   AdfFacesContext.getCurrentInstance().addPartialTarget(sc_tipoMuestra);
                   
                   visibleCompuesta ="false";
                    visibleIntegrada ="false";
               
               }
       }
    }
    
    public void signo_valueChangeListener(ValueChangeEvent valueChangeEvent) {
       valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
       medicion.setSigno((Lista)(this.sc_signo.getValue())); 
        if(this.medicion.getSigno()!=null){
        
         if(this.medicion.getSigno().getCodigo().equals(ConstantesCalidad.RANGO)){
             
                this.ot_valor.setVisible(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(this.ot_valor); 
                this.it_valor.setVisible(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_valor); 
                this.ot_valor2.setVisible(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(this.ot_valor2); 
                this.it_valor2.setVisible(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_valor2); 
                this.it_char.setVisible(false);
                AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_char); 
                this.ot_char.setVisible(false);
                AdfFacesContext.getCurrentInstance().addPartialTarget(this.ot_char); 
                  
                  
                  
                  
        }else if(this.medicion.getSigno().getCodigo().equals(ConstantesCalidad.VALOR_CHAR)) {
         
                
                 this.it_char.setVisible(true);
                 AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_char); 
                 this.ot_char.setVisible(true);
                 AdfFacesContext.getCurrentInstance().addPartialTarget(this.ot_char); 
                 
         
                 
                  this.it_valor.setVisible(false);
                  AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_valor); 
                 this.ot_valor.setVisible(false);
                 AdfFacesContext.getCurrentInstance().addPartialTarget(this.ot_valor); 
                 
                  this.ot_valor2.setVisible(false);
                  AdfFacesContext.getCurrentInstance().addPartialTarget(this.ot_valor2); 
                  this.it_valor2.setVisible(false);
                  AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_valor2); 
             }
         
         
         else {
                 this.ot_valor.setVisible(true);
                 AdfFacesContext.getCurrentInstance().addPartialTarget(this.ot_valor); 
                 this.it_valor.setVisible(true);
                 AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_valor);
             
                 this.it_char.setVisible(false);
                 AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_char); 
                 this.ot_char.setVisible(false);
                 AdfFacesContext.getCurrentInstance().addPartialTarget(this.ot_char); 
                   
             
                  this.ot_valor2.setVisible(false);
                  AdfFacesContext.getCurrentInstance().addPartialTarget(this.ot_valor2); 
                  this.it_valor2.setVisible(false);
                  AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_valor2); 
             }
        }
        
        
    }
        
    
   
    public void actualizar_actionListener(ActionEvent actionEvent) throws IdeamException{
        boolean continuar = true;
       
    
      
      if(this.sc_laboratorio.getValue()==null){
          showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.sc_laboratorio);       
          continuar = false;
      }
      
        if(this.id_fecha.getValue()==null){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.id_fecha);       
            continuar = false;
        }
      
      
        if(this.sc_hora.getValue()==null){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.sc_hora);       
            continuar = false;
        }
        
     
        if(this.sc_min.getValue()==null){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.sc_min);       
            continuar = false;
        }      
     
      
      if(this.sc_horario.getValue()==null ){
          showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.sc_horario);       
          continuar = false;
      }
        
      if(this.it_caudal.getValue()==null ){
          showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.it_caudal);       
          continuar = false;
      }
     
        if(this.muestra.getTipoMuestra().getCodigo().equals(ConstantesCalidad.MUESTRA_INTEGRADA)){
              
                if(this.it_verticales.getValue()==null || 
                    this.it_verticales.getValue().toString().length()==0){
                    continuar = false;
                    showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.it_verticales);
                } 
               
            
            }
     else  if(this.muestra.getTipoMuestra().getCodigo().equals(ConstantesCalidad.MUESTRA_COMPUESTA)){
              
                if(this.it_duracion.getValue()==null || 
                    this.it_duracion.getValue().toString().length()==0){
                    continuar = false;
                    showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.it_duracion);
                } 
                if(this.it_intervaloTiempo.getValue()==null || 
                    this.it_intervaloTiempo.getValue().toString().length()==0){
                    continuar = false;
                    showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.it_intervaloTiempo);
                } 
                if(this.it_submuestras.getValue()==null || 
                    this.it_submuestras.getValue().toString().length()==0){
                    continuar = false;
                    showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.it_submuestras);
                } 
                
            
            }
     
        if(continuar){   
             this.saveMuestra();   
           
        }
    }
    
        

    public void saveMuestra(){
        
            try{
               CalidadDelegate cld = CalidadDelegate.getInstance();
               this.muestra.setIdLaboratorio(this.muestra.getLaboratorio().getCodigo());
                
                 if(this.muestra.getIdLaboratorio().equals(ConstantesCalidad.OTRO_LAB)){
                    muestra.setOtroLaboratorio(this.it_otro_lab.getValue().toString());
                 }
                 
               this.muestra.setFechaMuestreo(this.muestra.getFechaMuestreo());
               this.muestra.setCaudal(Double.parseDouble(this.it_caudal.getValue().toString()));
               //this.muestra.setNro_muestra(this.it_nroMuestra.getValue().toString());   --- Linea cambiada Cabril. 11/03/2015  
               this.muestra.setNro_muestra((this.it_nroMuestra.getValue()!=null)?(this.it_nroMuestra.getValue().toString()):null);     
                                  
                
               if(this.muestra.getTipoMuestra().getCodigo().equals(ConstantesCalidad.MUESTRA_INTEGRADA)){
               
               this.muestra.setNroVerticales(Integer.parseInt(this.it_verticales.getValue().toString()));
              
               }else  if(this.muestra.getTipoMuestra().getCodigo().equals(ConstantesCalidad.MUESTRA_COMPUESTA)){
                   
                 this.muestra.setDuracion(Double.parseDouble(this.it_duracion.getValue().toString()));  
                 this.muestra.setNroSubmuestras(Integer.parseInt(this.it_submuestras.getValue().toString()));
                 this.muestra.setIntervaloTiempo(Double.parseDouble(this.it_intervaloTiempo.getValue().toString()));  
                 
                   
               }
              
              
              Muestra mm =  cld.updateMuestra(this.muestra);
                
                 try{
                     
                     /** 
                      * Estas variables son necesarias para llenar los datos intrinsecos de la auditoria.
                      */
                     UsuarioConectadoTO usuarioConectado = (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
                     Auditoria auditoria = new Auditoria();
                     auditoria.setIdUsuario(usuarioConectado.getUsuario().getCodigo());
                     auditoria.setOperacion("MODIFICAR");
                     auditoria.setObjeto("MUESTRAS_CALIDAD");
                     auditoria.setMetodo(Thread.currentThread().getStackTrace()[1].getMethodName());
                     auditoria.setClase(this.getClass().getName());
                     //Objeto Afectado (usuario modificado, eliminado o agregado) 
                     auditoria.setIdObjeto(this.muestra.getId());
                    
                     AuditoriasDelegate audDelegate = AuditoriasDelegate.getInstance();
                     audDelegate.setAuditoria(auditoria);
                     
                 }catch(Exception e){
                     System.out.println("No se pudo Almacenar la auditoria: ---------------------------------- ");
                     System.out.println(e.getMessage()+"------------------------------------------------------ ");
                 }

                
                
                
              showPopup(p_registro_act, true);
                
             }catch(IdeamException e){
                 showMessage(e);
             }
         }
     
     
    public void adicionarMedicion_actionListener(ActionEvent actionEvent) throws IdeamException{
     
        this.validarDatos("add");
     
    }


    public void saveMedicion(){
             
            try{
              
               CalidadDelegate cld = CalidadDelegate.getInstance();
                    
               medicion = new Medicion();
               UsuarioConectadoTO usuarioConectado = (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
               
               medicion.setIdMuestra(this.muestra);
               medicion.setParametro((Lista)(this.sc_parametros.getValue())); 
               medicion.setSigno((Lista)(this.sc_signo.getValue())); 
               medicion.setUnidad((Lista)(this.sc_unidad.getValue())); 
               medicion.setIdParametro(this.medicion.getParametro().getCodigo());
               medicion.setIdSigno(this.medicion.getSigno().getCodigo());
               medicion.setIdUnidad(this.medicion.getUnidad().getCodigo());
               
               if(this.medicion.getSigno().getCodigo().equals(ConstantesCalidad.RANGO)){
               medicion.setValor(Double.parseDouble(this.it_valor.getValue().toString()));  
               medicion.setValor2(Double.parseDouble(this.it_valor2.getValue().toString()));  
               }  
               else if(this.medicion.getSigno().getCodigo().equals(ConstantesCalidad.VALOR_CHAR)){
                   medicion.setValorchar(this.it_char.getValue().toString());  
               }else {
                   
                       medicion.setValor(Double.parseDouble(this.it_valor.getValue().toString()));  
                       
                   }
                  medicion.setLimiteDeteccion((this.it_limite.getValue()!=null)? Double.parseDouble(this.it_limite.getValue().toString()):null);
                  medicion.setMetodoDeterminacion((this.it_metodo.getValue()!=null)? this.it_metodo.getValue().toString():null);

                 if(this.check_acreditado.isSelected()){
                  medicion.setEsAcreditado(1);
                 }else{
                  medicion.setEsAcreditado(0);
                 }
               medicion.setCodigoAutoridad(usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue());
               cld.updateMedicion(this.medicion);
               this.otext_msj_med.setValue(getText("PAR_REGISTRO_EXITOSO"));
               showPopup(p_actualizar_med, true);
              
             }catch(IdeamException e){
                 showMessage(e);
             }
         }
    
    
    public void cmi_editar_actionListener(ActionEvent actionEvent) {
      
        if (t_mediciones.getSelectedRowData()==null){
            showMessage(getText("seleccionar_registro"),
                        FacesMessage.SEVERITY_ERROR);
            return;
        }
        
     
        Medicion med = (Medicion)t_mediciones.getSelectedRowData();
        FacesUtils.setInSession("medicionSeleccionada", med);
        this.editarMedicion();
   }

        
    public void validarDatos(String tipo){
        
        boolean continuar= true;
        
            if(this.sc_parametros.getValue()==null || this.sc_parametros.getValue()==""  ){
                   showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.sc_parametros);       
                   continuar = false;
                }
            if(this.sc_unidad.getValue()==null || this.sc_unidad.getValue()==""){
                    showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.sc_unidad);       
                    continuar = false;
                }
            if(this.sc_signo.getValue()==null || this.sc_signo.getValue()==""  ){
                    showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.sc_signo);       
                    continuar = false;
                }
      
            
            
            if(this.sc_signo.getValue()!=null  && ( this.medicion.getSigno().getCodigo()!=582 &&  this.medicion.getSigno().getCodigo()!=583  ) ){
                
            if(this.it_valor.getValue()==null || 
                    this.it_valor.getValue().toString().length()==0){
                    continuar = false;
                    showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.it_valor);
                } 
            
            }
            
            
            
            if(this.sc_signo.getValue()!=null  && this.medicion.getSigno().getCodigo().equals(ConstantesCalidad.RANGO) ){
                 if(this.it_valor2.getValue()==null || 
                        this.it_valor2.getValue().toString().length()==0){
                        continuar = false;
                        showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.it_valor2);
                    } 
              
                }  
            
            if(this.sc_signo.getValue()!=null  && this.medicion.getSigno().getCodigo().equals(ConstantesCalidad.VALOR_CHAR) ){
                 if(this.it_char.getValue()==null || 
                        this.it_char.getValue().toString().length()==0){
                        continuar = false;
                        showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.it_char);
                    } 
              
                }
            
      
           
           
            if(continuar){   
            
                if(tipo.equals("edit")){
                       this.editarParametro();
                    }
            
                if(tipo.equals("add")){
                     this.saveMedicion();
                    }
                 
            }else{
                showMessage("No se puede editar los campos estan vacios");
                 }
        }
        
        
    public void editarMedicion(){
       try{
             CalidadDelegate cld = CalidadDelegate.getInstance();
             Object obj3 = FacesUtils.getFromSession("medicionSeleccionada");
    
                if(obj3 instanceof Medicion){
                    this.medicion = (Medicion)obj3;
                     Long codigo=  this.medicion.getId();
                     this.medicion = cld.getMedicion(codigo);  
                 }
            
            this.cb_agregar.setVisible(false);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.cb_agregar); 
             
            this.cb_editar.setVisible(true);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.cb_editar); 
            
            this.sc_parametros.setValue(this.medicion.getParametro());
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.sc_parametros); 
            
            this.sc_signo.setValue(this.medicion.getSigno());
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.sc_signo); 
             
            this.sc_unidad.setValue(this.medicion.getUnidad());
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.sc_unidad); 
             
             
            if(this.medicion.getSigno().getCodigo().equals(ConstantesCalidad.RANGO)){
               this.ot_valor.setVisible(true);
               AdfFacesContext.getCurrentInstance().addPartialTarget(this.ot_valor); 
               this.it_valor.setVisible(true);
               AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_valor); 
               this.it_valor.setValue(this.medicion.getValor().toString());
               AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_valor); 
            
               this.ot_valor2.setVisible(true);
               AdfFacesContext.getCurrentInstance().addPartialTarget(this.ot_valor2); 
               this.it_valor2.setVisible(true);
               AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_valor2); 
               this.it_valor2.setValue(this.medicion.getValor2().toString());
               AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_valor2); 
             }
            else if(this.medicion.getSigno().getCodigo().equals(ConstantesCalidad.VALOR_CHAR)){
             
               this.ot_valor.setVisible(false);
               AdfFacesContext.getCurrentInstance().addPartialTarget(this.ot_valor); 
               this.it_valor.setVisible(false);
               AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_valor); 
            
               this.ot_valor2.setVisible(false);
               AdfFacesContext.getCurrentInstance().addPartialTarget(this.ot_valor2); 
               this.it_valor2.setVisible(false);
               
               
               
               
               this.ot_char.setVisible(true);
               AdfFacesContext.getCurrentInstance().addPartialTarget(this.ot_char); 
               this.it_char.setVisible(true);
               AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_char); 
               this.it_char.setValue(this.medicion.getValorchar().toString());
               AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_char); 
             }
             else{
                this.ot_valor.setVisible(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(this.ot_valor); 
                this.it_valor.setVisible(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_valor); 
                this.it_valor.setValue(this.medicion.getValor().toString());
                AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_valor); 
                
                this.ot_char.setVisible(false);
                AdfFacesContext.getCurrentInstance().addPartialTarget(this.ot_char); 
                this.it_char.setVisible(false);
                AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_char); 
                
               this.ot_valor2.setVisible(false);
               AdfFacesContext.getCurrentInstance().addPartialTarget(this.ot_valor2); 
               this.it_valor2.setVisible(false);
               AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_valor2); 
            }
            
            if(this.medicion.getLimiteDeteccion()!=null){
            this.it_limite.setValue(this.medicion.getLimiteDeteccion().toString());
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_limite); 
            }
            if(this.medicion.getMetodoDeterminacion()!=null){
            this.it_metodo.setValue(this.medicion.getMetodoDeterminacion().toString());
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_metodo); 
            }
            this.check_acreditado.setValue(this.medicion.getEsAcreditado());
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.check_acreditado); 
        
        }catch(IdeamException e){
             showMessage(e);
        }      
            
  
    }
    
    
    public void aceptar_editar_actionListener(ActionEvent actionEvent) {
       
        this.limpiarcampos();
        this.listarMediciones();
    
    }
    
    public void param_editar_actionListener(ActionEvent actionEvent) {
     
        this.validarDatos("edit");
   
    }
     
    public void editarParametro() {
         
     try{
                    
        CalidadDelegate cld = CalidadDelegate.getInstance();
        medicion.setParametro((Lista)(this.sc_parametros.getValue())); 
        medicion.setSigno((Lista)(this.sc_signo.getValue())); 
        medicion.setUnidad((Lista)(this.sc_unidad.getValue())); 
        medicion.setIdParametro(this.medicion.getParametro().getCodigo());
        medicion.setIdSigno(this.medicion.getSigno().getCodigo());
        medicion.setIdUnidad(this.medicion.getUnidad().getCodigo());
        medicion.setValor(Double.parseDouble(this.it_valor.getValue().toString()));  
        
        if(this.medicion.getSigno().getCodigo().equals(ConstantesCalidad.RANGO)){
            medicion.setValor2(Double.parseDouble(this.it_valor2.getValue().toString()));  
        }  
         
       if(this.medicion.getSigno().getCodigo().equals(ConstantesCalidad.VALOR_CHAR)){
              medicion.setValorchar(this.it_char.getValue().toString());  
       }  
         if(this.it_limite.getValue()!=null){
        medicion.setLimiteDeteccion(Double.parseDouble(this.it_limite.getValue().toString()));  
         }
            if(this.it_metodo.getValue()!=null ){
         medicion.setMetodoDeterminacion(this.it_metodo.getValue().toString());
            }
            if(this.check_acreditado.isSelected()){
               medicion.setEsAcreditado(1);
            }else{
               medicion.setEsAcreditado(0);
            }
        cld.updateMedicion(this.medicion);
         
        this.otext_msj_med.setValue(getText("PAR_ACTUALIZAR_EXITOSO"));
        showPopup(p_actualizar_med, true);
            
        
        }catch(IdeamException e){
        showMessage(e);
        }
    }
    
    
      
    public void limpiarcampos(){
       
        this.sc_parametros.setValue(null);
        this.sc_signo.setValue(null);
        this.sc_unidad.setValue(null);
        this.it_valor.setValue(null);
        this.it_char.setValue(null);
        this.it_valor2.setValue(null);
        this.it_limite.setValue(null);
        this.it_metodo.setValue(null);
        this.check_acreditado.setValue(null);
        
        this.ot_valor.setVisible(true);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.ot_valor); 
        this.it_valor.setVisible(true);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_valor);
        
        
        this.it_char.setVisible(false);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_char); 
        this.ot_char.setVisible(false);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.ot_char); 
          
        
         this.ot_valor2.setVisible(false);
         AdfFacesContext.getCurrentInstance().addPartialTarget(this.ot_valor2); 
         this.it_valor2.setVisible(false);
         AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_valor2); 
             
      
        
        this.cb_agregar.setVisible(true);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.cb_agregar); 
         
        this.cb_editar.setVisible(false);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.cb_editar); 
        
     
        
        
    }  
    
    
    
  public void listarMediciones(){
      
      this.limpiarcampos();
      
      
      try{
            CalidadDelegate cld = CalidadDelegate.getInstance();
             
            this.listaMediciones= new ArrayList();
            this.listaMediciones = cld.getMediciones(this.muestra);
            AdfFacesContext.getCurrentInstance().addPartialTarget(t_mediciones);
        
    
    }catch(IdeamException e){
        showMessage(e);
    }
   
}
  
  
    
    public void borrarMuestra_actionListener(ActionEvent actionEvent) {
       
        String nombre ="Muestra "+  this.muestra.getTipoMuestra().getValor();
        
        String params[] = {nombre};
        String texto = getText("MST_ELIMINAR", params);
        ot_borrar1.setValue(texto);
        ot_borrar2.setValue(nombre);
        AdfFacesContext.getCurrentInstance().addPartialTarget(popup_borrar);
        showPopup(popup_borrar, true);
            
    
    }
    
    
    public void cb_no_borrar_actionListener(ActionEvent actionEvent) {
        showPopup(popup_borrar, false);
    }
    
    public void cb_si_borrar_actionListener(ActionEvent actionEvent) {
        try{
            CalidadDelegate cld = CalidadDelegate.getInstance();
            cld.deleteMuestra(this.muestra);
            
            try{
                
                /** 
                 * Estas variables son necesarias para llenar los datos intrinsecos de la auditoria.
                 */
                 UsuarioConectadoTO usuarioConectado = (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
                
                Auditoria auditoria = new Auditoria();
                auditoria.setIdUsuario(usuarioConectado.getUsuario().getCodigo());
                auditoria.setOperacion("BORRAR");
                auditoria.setObjeto("MUESTRAS_CALIDAD");
                auditoria.setMetodo(Thread.currentThread().getStackTrace()[1].getMethodName());
                auditoria.setClase(this.getClass().getName());
                //Objeto Afectado (usuario modificado, eliminado o agregado) 
                auditoria.setIdObjeto(this.muestra.getId());
               
                AuditoriasDelegate audDelegate = AuditoriasDelegate.getInstance();
                audDelegate.setAuditoria(auditoria);
                
            }catch(Exception e){
                System.out.println("No se pudo Almacenar la auditoria: ---------------------------------- ");
                System.out.println(e.getMessage()+"------------------------------------------------------ ");
            }
            
            showMessage(getText("MST_ELIMINADO"));
        }catch(IdeamException e){
            
            showMessage(e.getMessage());
        }
    }
    
    
    public void borrarParametro_actionListener(ActionEvent actionEvent) {
       
        if (t_mediciones.getSelectedRowData()==null){
            showMessage(getText("seleccionar_registro"),
                        FacesMessage.SEVERITY_ERROR);
            return;
        }
        
        
        Medicion med = (Medicion)t_mediciones.getSelectedRowData();
        FacesUtils.setInSession("medicionSeleccionada", med);
        
        this.medicion= med;
        String nombre = this.medicion.getParametro().getValor();
        
        String params[] = {nombre};
        String texto = getText("PAR_ELIMINAR", params);
        ot_medborrar1.setValue(texto);
        ot_medborrar2.setValue(nombre);
        AdfFacesContext.getCurrentInstance().addPartialTarget(popup_borrar_med);
        showPopup(popup_borrar_med, true);
      
    }
    
    
    
    
    public void cb_no_borrar_med_actionListener(ActionEvent actionEvent) {
        showPopup(popup_borrar_med, false);
    }
    
    public void cb_si_borrar_med_actionListener(ActionEvent actionEvent) {
        try{
            CalidadDelegate cld = CalidadDelegate.getInstance();
            cld.deleteMedicion(this.medicion);
            showMessage(getText("PAR_ELIMINADO"));
            
            this.limpiarcampos();
            this.listarMediciones();
        }catch(IdeamException e){
            
            showMessage(e.getMessage());
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

    public void setPanelGroupLayout1(RichPanelGroupLayout panelGroupLayout1) {
        this.panelGroupLayout1 = panelGroupLayout1;
    }

    public RichPanelGroupLayout getPanelGroupLayout1() {
        return panelGroupLayout1;
    }

    public void setPanelBox1(RichPanelBox panelBox1) {
        this.panelBox1 = panelBox1;
    }

    public RichPanelBox getPanelBox1() {
        return panelBox1;
    }

    public void setPanelStretchLayout2(RichPanelStretchLayout panelStretchLayout2) {
        this.panelStretchLayout2 = panelStretchLayout2;
    }

    public RichPanelStretchLayout getPanelStretchLayout2() {
        return panelStretchLayout2;
    }


    public void setPanelFormLayout1(RichPanelFormLayout panelFormLayout1) {
        this.panelFormLayout1 = panelFormLayout1;
    }

    public RichPanelFormLayout getPanelFormLayout1() {
        return panelFormLayout1;
    }

    public void setSpacer2(RichSpacer spacer2) {
        this.spacer2 = spacer2;
    }

    public RichSpacer getSpacer2() {
        return spacer2;
    }

    public void setTituloPanelMuestra(RichActiveOutputText tituloPanelMuestra) {
        this.tituloPanelMuestra = tituloPanelMuestra;
    }

    public RichActiveOutputText getTituloPanelMuestra() {
        return tituloPanelMuestra;
    }

    public void setSc_tipoMuestra(RichSelectOneChoice sc_tipoMuestra) {
        this.sc_tipoMuestra = sc_tipoMuestra;
    }

    public RichSelectOneChoice getSc_tipoMuestra() {
        return sc_tipoMuestra;
    }

    public void setSelectItems1(UISelectItems selectItems1) {
        this.selectItems1 = selectItems1;
    }

    public UISelectItems getSelectItems1() {
        return selectItems1;
    }

    public void setSc_laboratorio(RichSelectOneChoice sc_laboratorio) {
        this.sc_laboratorio = sc_laboratorio;
    }

    public RichSelectOneChoice getSc_laboratorio() {
        return sc_laboratorio;
    }

    public void setSelectItems2(UISelectItems selectItems2) {
        this.selectItems2 = selectItems2;
    }

    public UISelectItems getSelectItems2() {
        return selectItems2;
    }

    public void setId_fecha(RichInputDate id_fecha) {
        this.id_fecha = id_fecha;
    }

    public RichInputDate getId_fecha() {
        return id_fecha;
    }

    public void setIt_caudal(RichInputText it_caudal) {
        this.it_caudal = it_caudal;
    }

    public RichInputText getIt_caudal() {
        return it_caudal;
    }

    public void setIt_duracion(RichInputText it_duracion) {
        this.it_duracion = it_duracion;
    }

    public RichInputText getIt_duracion() {
        return it_duracion;
    }

    public void setIt_verticales(RichInputText it_verticales) {
        this.it_verticales = it_verticales;
    }

    public RichInputText getIt_verticales() {
        return it_verticales;
    }

    public void setIt_submuestras(RichInputText it_submuestras) {
        this.it_submuestras = it_submuestras;
    }

    public RichInputText getIt_submuestras() {
        return it_submuestras;
    }

    public void setIt_intervaloTiempo(RichInputText it_intervaloTiempo) {
        this.it_intervaloTiempo = it_intervaloTiempo;
    }

    public RichInputText getIt_intervaloTiempo() {
        return it_intervaloTiempo;
    }

    public void setTituloMuestra(RichActiveOutputText tituloMuestra) {
        this.tituloMuestra = tituloMuestra;
    }

    public RichActiveOutputText getTituloMuestra() {
        return tituloMuestra;
    }

    public void setMuestra(Muestra muestra) {
        this.muestra = muestra;
    }

    public Muestra getMuestra() {
        return muestra;
    }

    public void setPuntoM(PuntoMonitoreo puntoM) {
        this.puntoM = puntoM;
    }

    public PuntoMonitoreo getPuntoM() {
        return puntoM;
    }

    public void setMedicion(Medicion medicion) {
        this.medicion = medicion;
    }

    public Medicion getMedicion() {
        return medicion;
    }

    public void setListaTipoMuestra(List listaTipoMuestra) {
        this.listaTipoMuestra = listaTipoMuestra;
    }

    public List getListaTipoMuestra() {
        return listaTipoMuestra;
    }

    public void setListaLaboratorio(List listaLaboratorio) {
        this.listaLaboratorio = listaLaboratorio;
    }

    public List getListaLaboratorio() {
        return listaLaboratorio;
    }

    public void setListaHora(List listaHora) {
        this.listaHora = listaHora;
    }

    public List getListaHora() {
        return listaHora;
    }

    public void setListaMin(List listaMin) {
        this.listaMin = listaMin;
    }

    public List getListaMin() {
        return listaMin;
    }

    public void setListaHorario(List listaHorario) {
        this.listaHorario = listaHorario;
    }

    public List getListaHorario() {
        return listaHorario;
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

    public void setListaMediciones(List listaMediciones) {
        this.listaMediciones = listaMediciones;
    }

    public List getListaMediciones() {
        return listaMediciones;
    }

    public void setPanelGroupLayout5(RichPanelGroupLayout panelGroupLayout5) {
        this.panelGroupLayout5 = panelGroupLayout5;
    }

    public RichPanelGroupLayout getPanelGroupLayout5() {
        return panelGroupLayout5;
    }

    public void setSc_hora(RichSelectOneChoice sc_hora) {
        this.sc_hora = sc_hora;
    }

    public RichSelectOneChoice getSc_hora() {
        return sc_hora;
    }

    public void setSelectItems3(UISelectItems selectItems3) {
        this.selectItems3 = selectItems3;
    }

    public UISelectItems getSelectItems3() {
        return selectItems3;
    }

    public void setSc_min(RichSelectOneChoice sc_min) {
        this.sc_min = sc_min;
    }

    public RichSelectOneChoice getSc_min() {
        return sc_min;
    }

    public void setSelectItems4(UISelectItems selectItems4) {
        this.selectItems4 = selectItems4;
    }

    public UISelectItems getSelectItems4() {
        return selectItems4;
    }

    public void setSc_horario(RichSelectOneChoice sc_horario) {
        this.sc_horario = sc_horario;
    }

    public RichSelectOneChoice getSc_horario() {
        return sc_horario;
    }

    public void setSelectItems5(UISelectItems selectItems5) {
        this.selectItems5 = selectItems5;
    }

    public UISelectItems getSelectItems5() {
        return selectItems5;
    }

    public void setPgdatosBasicos(RichPanelGroupLayout pgdatosBasicos) {
        this.pgdatosBasicos = pgdatosBasicos;
    }

    public RichPanelGroupLayout getPgdatosBasicos() {
        return pgdatosBasicos;
    }

    public void setFormDatosBasicos(RichPanelFormLayout formDatosBasicos) {
        this.formDatosBasicos = formDatosBasicos;
    }

    public RichPanelFormLayout getFormDatosBasicos() {
        return formDatosBasicos;
    }

    public void setPanelGFecha(RichPanelGroupLayout panelGFecha) {
        this.panelGFecha = panelGFecha;
    }

    public RichPanelGroupLayout getPanelGFecha() {
        return panelGFecha;
    }

    public void setFormFecha(RichPanelFormLayout formFecha) {
        this.formFecha = formFecha;
    }

    public RichPanelFormLayout getFormFecha() {
        return formFecha;
    }

    public void setPanelGTipoMuestra(RichPanelGroupLayout panelGTipoMuestra) {
        this.panelGTipoMuestra = panelGTipoMuestra;
    }

    public RichPanelGroupLayout getPanelGTipoMuestra() {
        return panelGTipoMuestra;
    }

    public void setFormTipoMuestra(RichPanelFormLayout formTipoMuestra) {
        this.formTipoMuestra = formTipoMuestra;
    }

    public RichPanelFormLayout getFormTipoMuestra() {
        return formTipoMuestra;
    }

    public void setPanelGHora(RichPanelGroupLayout panelGHora) {
        this.panelGHora = panelGHora;
    }

    public RichPanelGroupLayout getPanelGHora() {
        return panelGHora;
    }

    public void setFormHora(RichPanelFormLayout formHora) {
        this.formHora = formHora;
    }

    public RichPanelFormLayout getFormHora() {
        return formHora;
    }

    public void setSpacer3(RichSpacer spacer3) {
        this.spacer3 = spacer3;
    }

    public RichSpacer getSpacer3() {
        return spacer3;
    }

    public void setSpacer6(RichSpacer spacer6) {
        this.spacer6 = spacer6;
    }

    public RichSpacer getSpacer6() {
        return spacer6;
    }

    public void setSpacer5(RichSpacer spacer5) {
        this.spacer5 = spacer5;
    }

    public RichSpacer getSpacer5() {
        return spacer5;
    }


    public void setPanelGroupLayout6(RichPanelGroupLayout panelGroupLayout6) {
        this.panelGroupLayout6 = panelGroupLayout6;
    }

    public RichPanelGroupLayout getPanelGroupLayout6() {
        return panelGroupLayout6;
    }


    public void setPanelStretchLayout4(RichPanelStretchLayout panelStretchLayout4) {
        this.panelStretchLayout4 = panelStretchLayout4;
    }

    public RichPanelStretchLayout getPanelStretchLayout4() {
        return panelStretchLayout4;
    }

    public void setPanelSplitter1(RichPanelSplitter panelSplitter1) {
        this.panelSplitter1 = panelSplitter1;
    }

    public RichPanelSplitter getPanelSplitter1() {
        return panelSplitter1;
    }

    public void setPanelGroupLayout7(RichPanelGroupLayout panelGroupLayout7) {
        this.panelGroupLayout7 = panelGroupLayout7;
    }

    public RichPanelGroupLayout getPanelGroupLayout7() {
        return panelGroupLayout7;
    }

    public void setSpacer4(RichSpacer spacer4) {
        this.spacer4 = spacer4;
    }

    public RichSpacer getSpacer4() {
        return spacer4;
    }

    public void setActiveOutputText3(RichActiveOutputText activeOutputText3) {
        this.activeOutputText3 = activeOutputText3;
    }

    public RichActiveOutputText getActiveOutputText3() {
        return activeOutputText3;
    }

    public void setSpacer7(RichSpacer spacer7) {
        this.spacer7 = spacer7;
    }

    public RichSpacer getSpacer7() {
        return spacer7;
    }

    public void setPanelFormLayout2(RichPanelFormLayout panelFormLayout2) {
        this.panelFormLayout2 = panelFormLayout2;
    }

    public RichPanelFormLayout getPanelFormLayout2() {
        return panelFormLayout2;
    }

    public void setSc_parametros(RichSelectOneChoice sc_parametros) {
        this.sc_parametros = sc_parametros;
    }

    public RichSelectOneChoice getSc_parametros() {
        return sc_parametros;
    }

    public void setSelectItems6(UISelectItems selectItems6) {
        this.selectItems6 = selectItems6;
    }

    public UISelectItems getSelectItems6() {
        return selectItems6;
    }

    public void setSc_unidad(RichSelectOneChoice sc_unidad) {
        this.sc_unidad = sc_unidad;
    }

    public RichSelectOneChoice getSc_unidad() {
        return sc_unidad;
    }

    public void setSelectItems7(UISelectItems selectItems7) {
        this.selectItems7 = selectItems7;
    }

    public UISelectItems getSelectItems7() {
        return selectItems7;
    }

    public void setSc_signo(RichSelectOneChoice sc_signo) {
        this.sc_signo = sc_signo;
    }

    public RichSelectOneChoice getSc_signo() {
        return sc_signo;
    }

    public void setSelectItems8(UISelectItems selectItems8) {
        this.selectItems8 = selectItems8;
    }

    public UISelectItems getSelectItems8() {
        return selectItems8;
    }

 



    public void setCheck_acreditado(RichSelectBooleanCheckbox check_acreditado) {
        this.check_acreditado = check_acreditado;
    }

    public RichSelectBooleanCheckbox getCheck_acreditado() {
        return check_acreditado;
    }

    public void setOt_parametro(RichActiveOutputText ot_parametro) {
        this.ot_parametro = ot_parametro;
    }

    public RichActiveOutputText getOt_parametro() {
        return ot_parametro;
    }

    public void setOt_unidad(RichActiveOutputText ot_unidad) {
        this.ot_unidad = ot_unidad;
    }

    public RichActiveOutputText getOt_unidad() {
        return ot_unidad;
    }

    public void setOt_signo(RichActiveOutputText ot_signo) {
        this.ot_signo = ot_signo;
    }

    public RichActiveOutputText getOt_signo() {
        return ot_signo;
    }

    public void setOt_valor(RichActiveOutputText ot_valor) {
        this.ot_valor = ot_valor;
    }

    public RichActiveOutputText getOt_valor() {
        return ot_valor;
    }

    public void setOt_metodo(RichActiveOutputText ot_metodo) {
        this.ot_metodo = ot_metodo;
    }

    public RichActiveOutputText getOt_metodo() {
        return ot_metodo;
    }

    public void setOt_limite(RichActiveOutputText ot_limite) {
        this.ot_limite = ot_limite;
    }

    public RichActiveOutputText getOt_limite() {
        return ot_limite;
    }

    public void setOt_acreditado(RichActiveOutputText ot_acreditado) {
        this.ot_acreditado = ot_acreditado;
    }

    public RichActiveOutputText getOt_acreditado() {
        return ot_acreditado;
    }

    public void setPanelCollection1(RichPanelCollection panelCollection1) {
        this.panelCollection1 = panelCollection1;
    }

    public RichPanelCollection getPanelCollection1() {
        return panelCollection1;
    }

    public void setT_mediciones(RichTable t_mediciones) {
        this.t_mediciones = t_mediciones;
    }

    public RichTable getT_mediciones() {
        return t_mediciones;
    }

    public void setPanelGroupLayout8(RichPanelGroupLayout panelGroupLayout8) {
        this.panelGroupLayout8 = panelGroupLayout8;
    }

    public RichPanelGroupLayout getPanelGroupLayout8() {
        return panelGroupLayout8;
    }

    public void setSpacer8(RichSpacer spacer8) {
        this.spacer8 = spacer8;
    }

    public RichSpacer getSpacer8() {
        return spacer8;
    }

    public void setCb_agregar(RichCommandButton cb_agregar) {
        this.cb_agregar = cb_agregar;
    }

    public RichCommandButton getCb_agregar() {
        return cb_agregar;
    }


    public void setD_registro_act(RichDialog d_registro_med) {
        this.d_registro_act = d_registro_med;
    }

    public RichDialog getD_registro_act() {
        return d_registro_act;
    }

    public void setPanelStretchLayout5(RichPanelStretchLayout panelStretchLayout5) {
        this.panelStretchLayout5 = panelStretchLayout5;
    }

    public RichPanelStretchLayout getPanelStretchLayout5() {
        return panelStretchLayout5;
    }

    public void setT_registro_act(RichActiveOutputText t_registro_med) {
        this.t_registro_act = t_registro_med;
    }

    public RichActiveOutputText getT_registro_act() {
        return t_registro_act;
    }

    public void setImage2(RichImage image2) {
        this.image2 = image2;
    }

    public RichImage getImage2() {
        return image2;
    }

    public void setPanelGroupLayout9(RichPanelGroupLayout panelGroupLayout9) {
        this.panelGroupLayout9 = panelGroupLayout9;
    }

    public RichPanelGroupLayout getPanelGroupLayout9() {
        return panelGroupLayout9;
    }

    public void setCb_aceptar_muestra(RichCommandButton cb_aceptar_medicion) {
        this.cb_aceptar_muestra = cb_aceptar_medicion;
    }

    public RichCommandButton getCb_aceptar_muestra() {
        return cb_aceptar_muestra;
    }

    public void setSpacer9(RichSpacer spacer9) {
        this.spacer9 = spacer9;
    }

    public RichSpacer getSpacer9() {
        return spacer9;
    }

    public void setCommandLink1(RichCommandLink commandLink1) {
        this.commandLink1 = commandLink1;
    }

    public RichCommandLink getCommandLink1() {
        return commandLink1;
    }

    public void setSpacer10(RichSpacer spacer10) {
        this.spacer10 = spacer10;
    }

    public RichSpacer getSpacer10() {
        return spacer10;
    }

    public void setCommandLink2(RichCommandLink commandLink2) {
        this.commandLink2 = commandLink2;
    }

    public RichCommandLink getCommandLink2() {
        return commandLink2;
    }

    public void setVisibleCompuesta(String visibleCompuesta) {
        this.visibleCompuesta = visibleCompuesta;
    }

    public String getVisibleCompuesta() {
        return visibleCompuesta;
    }

    public void setVisibleIntegrada(String visibleIntegrada) {
        this.visibleIntegrada = visibleIntegrada;
    }

    public String getVisibleIntegrada() {
        return visibleIntegrada;
    }

    public void setPanelGroupParametros(RichPanelGroupLayout panelGroupLayout2) {
        this.panelGroupParametros = panelGroupLayout2;
    }

    public RichPanelGroupLayout getPanelGroupParametros() {
        return panelGroupParametros;
    }


    public void setPanelGroupLayout3(RichPanelGroupLayout panelGroupLayout3) {
        this.panelGroupLayout3 = panelGroupLayout3;
    }

    public RichPanelGroupLayout getPanelGroupLayout3() {
        return panelGroupLayout3;
    }

    public void setCb_actualizar(RichCommandButton commandButton1) {
        this.cb_actualizar = commandButton1;
    }

    public RichCommandButton getCb_actualizar() {
        return cb_actualizar;
    }

    public void setSpacer1(RichSpacer spacer1) {
        this.spacer1 = spacer1;
    }

    public RichSpacer getSpacer1() {
        return spacer1;
    }


    public void setCb_borrar(RichCommandButton commandButton1) {
        this.cb_borrar = commandButton1;
    }

    public RichCommandButton getCb_borrar() {
        return cb_borrar;
    }

    public void setMenu_opciones(RichMenu menu1) {
        this.menu_opciones = menu1;
    }

    public RichMenu getMenu_opciones() {
        return menu_opciones;
    }

    public void setCmi_editar(RichCommandMenuItem commandMenuItem1) {
        this.cmi_editar = commandMenuItem1;
    }

    public RichCommandMenuItem getCmi_editar() {
        return cmi_editar;
    }

    public void setCmi_eliminar(RichCommandMenuItem commandMenuItem1) {
        this.cmi_eliminar = commandMenuItem1;
    }

    public RichCommandMenuItem getCmi_eliminar() {
        return cmi_eliminar;
    }


    public void setCb_editar(RichCommandButton commandButton1) {
        this.cb_editar = commandButton1;
    }

    public RichCommandButton getCb_editar() {
        return cb_editar;
    }


    public void setPanelBoxParametros(RichPanelBox panelBox2) {
        this.panelBoxParametros = panelBox2;
    }

    public RichPanelBox getPanelBoxParametros() {
        return panelBoxParametros;
    }

    public void setPanelStretchLayout3(RichPanelStretchLayout panelStretchLayout3) {
        this.panelStretchLayout3 = panelStretchLayout3;
    }

    public RichPanelStretchLayout getPanelStretchLayout3() {
        return panelStretchLayout3;
    }


    public void setSpacer13(RichSpacer spacer13) {
        this.spacer13 = spacer13;
    }

    public RichSpacer getSpacer13() {
        return spacer13;
    }

    public void setPanelFormLayout3(RichPanelFormLayout panelFormLayout3) {
        this.panelFormLayout3 = panelFormLayout3;
    }

    public RichPanelFormLayout getPanelFormLayout3() {
        return panelFormLayout3;
    }


    public void setPanelGroupLayout2(RichPanelGroupLayout panelGroupLayout2) {
        this.panelGroupLayout2 = panelGroupLayout2;
    }

    public RichPanelGroupLayout getPanelGroupLayout2() {
        return panelGroupLayout2;
    }

    public void setSpacer12(RichSpacer spacer12) {
        this.spacer12 = spacer12;
    }

    public RichSpacer getSpacer12() {
        return spacer12;
    }






 



    public void setPanelStretchLayout6(RichPanelStretchLayout panelStretchLayout6) {
        this.panelStretchLayout6 = panelStretchLayout6;
    }

    public RichPanelStretchLayout getPanelStretchLayout6() {
        return panelStretchLayout6;
    }

    public void setOtext_msj_med(RichActiveOutputText activeOutputText4) {
        this.otext_msj_med = activeOutputText4;
    }

    public RichActiveOutputText getOtext_msj_med() {
        return otext_msj_med;
    }

    public void setImage1(RichImage image1) {
        this.image1 = image1;
    }

    public RichImage getImage1() {
        return image1;
    }

    public void setPanelGroupLayout4(RichPanelGroupLayout panelGroupLayout4) {
        this.panelGroupLayout4 = panelGroupLayout4;
    }

    public RichPanelGroupLayout getPanelGroupLayout4() {
        return panelGroupLayout4;
    }

    public void setCb_acept_med(RichCommandButton commandButton1) {
        this.cb_acept_med = commandButton1;
    }

    public RichCommandButton getCb_acept_med() {
        return cb_acept_med;
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

    public void setPanelGroupLayout10(RichPanelGroupLayout panelGroupLayout10) {
        this.panelGroupLayout10 = panelGroupLayout10;
    }

    public RichPanelGroupLayout getPanelGroupLayout10() {
        return panelGroupLayout10;
    }

    public void setOt_borrar1(RichActiveOutputText activeOutputText4) {
        this.ot_borrar1 = activeOutputText4;
    }

    public RichActiveOutputText getOt_borrar1() {
        return ot_borrar1;
    }

    public void setSpacer14(RichSpacer spacer14) {
        this.spacer14 = spacer14;
    }

    public RichSpacer getSpacer14() {
        return spacer14;
    }

    public void setOt_borrar2(RichActiveOutputText activeOutputText4) {
        this.ot_borrar2 = activeOutputText4;
    }

    public RichActiveOutputText getOt_borrar2() {
        return ot_borrar2;
    }

    public void setPanelGroupLayout11(RichPanelGroupLayout panelGroupLayout11) {
        this.panelGroupLayout11 = panelGroupLayout11;
    }

    public RichPanelGroupLayout getPanelGroupLayout11() {
        return panelGroupLayout11;
    }

    public void setCb_si_borrar(RichCommandButton commandButton1) {
        this.cb_si_borrar = commandButton1;
    }

    public RichCommandButton getCb_si_borrar() {
        return cb_si_borrar;
    }

    public void setSpacer15(RichSpacer spacer15) {
        this.spacer15 = spacer15;
    }

    public RichSpacer getSpacer15() {
        return spacer15;
    }

    public void setCb_no_borrar(RichCommandButton commandButton2) {
        this.cb_no_borrar = commandButton2;
    }

    public RichCommandButton getCb_no_borrar() {
        return cb_no_borrar;
    }

    public void setPopup_borrar_med(RichPopup popup1) {
        this.popup_borrar_med = popup1;
    }

    public RichPopup getPopup_borrar_med() {
        return popup_borrar_med;
    }

    public void setD_borrar_med(RichDialog dialog1) {
        this.d_borrar_med = dialog1;
    }

    public RichDialog getD_borrar_med() {
        return d_borrar_med;
    }

    public void setPanelGroupLayout12(RichPanelGroupLayout panelGroupLayout12) {
        this.panelGroupLayout12 = panelGroupLayout12;
    }

    public RichPanelGroupLayout getPanelGroupLayout12() {
        return panelGroupLayout12;
    }

    public void setOt_medborrar1(RichActiveOutputText activeOutputText4) {
        this.ot_medborrar1 = activeOutputText4;
    }

    public RichActiveOutputText getOt_medborrar1() {
        return ot_medborrar1;
    }

    public void setSpacer16(RichSpacer spacer16) {
        this.spacer16 = spacer16;
    }

    public RichSpacer getSpacer16() {
        return spacer16;
    }

    public void setOt_medborrar2(RichActiveOutputText activeOutputText5) {
        this.ot_medborrar2 = activeOutputText5;
    }

    public RichActiveOutputText getOt_medborrar2() {
        return ot_medborrar2;
    }

    public void setPanelGroupLayout13(RichPanelGroupLayout panelGroupLayout13) {
        this.panelGroupLayout13 = panelGroupLayout13;
    }

    public RichPanelGroupLayout getPanelGroupLayout13() {
        return panelGroupLayout13;
    }

    public void setCb_si_borrar_med(RichCommandButton commandButton1) {
        this.cb_si_borrar_med = commandButton1;
    }

    public RichCommandButton getCb_si_borrar_med() {
        return cb_si_borrar_med;
    }

    public void setSpacer17(RichSpacer spacer17) {
        this.spacer17 = spacer17;
    }

    public RichSpacer getSpacer17() {
        return spacer17;
    }

    public void setCb_no_borrar_med(RichCommandButton commandButton2) {
        this.cb_no_borrar_med = commandButton2;
    }

    public RichCommandButton getCb_no_borrar_med() {
        return cb_no_borrar_med;
    }

    public void setSpacer18(RichSpacer spacer18) {
        this.spacer18 = spacer18;
    }

    public RichSpacer getSpacer18() {
        return spacer18;
    }

    public void setP_registro_act(RichPopup p_registro_act) {
        this.p_registro_act = p_registro_act;
    }

    public RichPopup getP_registro_act() {
        return p_registro_act;
    }

    public void setP_actualizar_med(RichPopup p_actualizar_med) {
        this.p_actualizar_med = p_actualizar_med;
    }

    public RichPopup getP_actualizar_med() {
        return p_actualizar_med;
    }

    public void p_registro_act_popupFetchListener(PopupFetchEvent popupFetchEvent) {
        // Add event code here...
    }

    public void setIt_nroMuestra(RichInputText inputText1) {
        this.it_nroMuestra = inputText1;
    }

    public RichInputText getIt_nroMuestra() {
        return it_nroMuestra;
    }

    public void setIt_metodo(RichInputText it_metodo) {
        this.it_metodo = it_metodo;
    }

    public RichInputText getIt_metodo() {
        return it_metodo;
    }

    public void setIt_limite(RichInputText it_limite) {
        this.it_limite = it_limite;
    }

    public RichInputText getIt_limite() {
        return it_limite;
    }

    public void setIt_valor(RichInputText it_valor) {
        this.it_valor = it_valor;
    }

    public RichInputText getIt_valor() {
        return it_valor;
    }

    public void setOt_valor2(RichActiveOutputText ot_valor2) {
        this.ot_valor2 = ot_valor2;
    }

    public RichActiveOutputText getOt_valor2() {
        return ot_valor2;
    }

    public void setIt_valor2(RichInputText it_valor2) {
        this.it_valor2 = it_valor2;
    }

    public RichInputText getIt_valor2() {
        return it_valor2;
    }

    public void setD_act_med(RichDialog d_act_med) {
        this.d_act_med = d_act_med;
    }

    public RichDialog getD_act_med() {
        return d_act_med;
    }

    public void setOt_char(RichActiveOutputText activeOutputText4) {
        this.ot_char = activeOutputText4;
    }

    public RichActiveOutputText getOt_char() {
        return ot_char;
    }

    public void setIt_char(RichInputText inputText1) {
        this.it_char = inputText1;
    }

    public RichInputText getIt_char() {
        return it_char;
    }


    public void setPuntoMonitoreoTO(PuntoMonitoreoTO puntoMonitoreoTO) {
        this.puntoMonitoreoTO = puntoMonitoreoTO;
    }

    public PuntoMonitoreoTO getPuntoMonitoreoTO() {
        return puntoMonitoreoTO;
    }

    public void setIt_otro_lab(RichInputText inputText1) {
        this.it_otro_lab = inputText1;
    }

    public RichInputText getIt_otro_lab() {
        return it_otro_lab;
    }

    public void setVisibleOtro(String visibleOtro) {
        this.visibleOtro = visibleOtro;
    }

    public String getVisibleOtro() {
        return visibleOtro;
    }

    public void setIt_tipoMuestraVert(RichInputText inputText1) {
        this.it_tipoMuestraVert = inputText1;
    }

    public RichInputText getIt_tipoMuestraVert() {
        return it_tipoMuestraVert;
    }

    public void setIdvert(Long idvert) {
        this.idvert = idvert;
    }

    public Long getIdvert() {
        return idvert;
    }


    public void setNro(Integer nro) {
        this.nro = nro;
    }

    public Integer getNro() {
        return nro;
    }

    public void setListaMuestras(List listaMuestras) {
        this.listaMuestras = listaMuestras;
    }

    public List getListaMuestras() {
        return listaMuestras;
    }
}//Fin
