package co.gov.ideam.sirh.calidad.view;

import co.gov.ideam.sirh.auditoria.model.Auditoria;
import co.gov.ideam.sirh.auditoria.view.AuditoriasDelegate;
import co.gov.ideam.sirh.calidad.model.Medicion;
import co.gov.ideam.sirh.calidad.model.Muestra;
import co.gov.ideam.sirh.calidad.model.PuntoMonitoreo;
import co.gov.ideam.sirh.calidad.model.PuntoMonitoreoTO;
import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.fuentes.model.FnttTramo;
import co.gov.ideam.sirh.parametros.model.Categoria;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.model.PartZonificListas;
import co.gov.ideam.sirh.seguridad.model.PerfilVO;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.seguridad.view.SeguridadDelegate;
import co.gov.ideam.sirh.usuarios.agua.model.UsuarioAgua;
import co.gov.ideam.sirh.usuarios.agua.view.UsuariosAguaDelegate;
import co.gov.ideam.sirh.util.ConstantesCalidad;
import co.gov.ideam.sirh.util.IdeamException;

import co.gov.ideam.sirh.view.FacesUtils;

import co.gov.ideam.sirh.view.StandarBean;

import java.text.DateFormat;

import java.text.SimpleDateFormat;

import java.util.ArrayList;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UISelectItems;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import javax.faces.model.SelectItem;

import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
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
import oracle.adf.view.rich.component.rich.output.RichActiveOutputText;
import oracle.adf.view.rich.component.rich.output.RichImage;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;

import org.apache.myfaces.trinidad.event.AttributeChangeEvent;

public class AdicionarMuestraBean  extends StandarBean{
    private RichForm form1;
    private RichDocument document1;
    private RichPanelStretchLayout panelStretchLayout1;
    private RichPanelGroupLayout panelGroupLayout1;
    private RichPanelBox panelBox1;
    private RichPanelStretchLayout panelStretchLayout2;
    private RichPanelGroupLayout panelGroupLayout2;
    private RichPanelGroupLayout panelGroupLayout3;
    private RichCommandButton cb_next;
    private RichSpacer spacer1;
    private RichPanelFormLayout panelFormLayout1;
     private RichPanelFormLayout panelFormLayout3;
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
    private RichPanelBox panelParametros;
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
    private RichPopup p_registro_med;
    private RichDialog d_registro_med;
    private RichPanelStretchLayout panelStretchLayout5;
    private RichActiveOutputText t_registro_med;
    private RichImage image2;
    private RichPanelGroupLayout panelGroupLayout9;
    private RichCommandButton cb_aceptar_medicion;
    private RichSpacer spacer9;
    private RichCommandLink commandLink1;
    private RichSpacer spacer10;
    private RichCommandLink commandLink2;
    private RichPanelGroupLayout panelGroupLayout10;

    private RichPanelGroupLayout panelGroupLayout4;
    private RichPanelFormLayout panelFormLayout21;

    private UISelectItems selectItems61;
   
    private UISelectItems selectItems71;
   
    private UISelectItems selectItems81;
   
   
    private RichPanelGroupLayout panelGroupLayout81;
    private RichSpacer spacer81;
   
    private RichPanelGroupLayout panelGroupLayout101;
    private RichPanelCollection panelCollection11;
   
    private RichPanelGroupLayout panelGroupLayout11;
    private RichCommandButton cb_finalizar;
    private RichSpacer spacer11;
    private RichActiveOutputText ot_valor2;
    private RichInputText it_valor2;
    private RichInputText it_nroMuestra;
    private RichInputText it_tipoMuestraVert;
    private List listaMuestras;
    private Long idvert;
    private int  nro;
    private RichInputText it_char;
    private RichActiveOutputText ot_char;
    private PuntoMonitoreoTO puntoMonitoreoTO;
    private RichInputText it_otro_lab;
    
    private String aceptarAction;
  
    public AdicionarMuestraBean(){
       FacesUtils.setActiveBean("adicionarMuestra","adicionarMuestra", CalidadDelegate.class);
       
      
       this.load();        
    }
    public void load(){
    
      try{      
        muestra = new Muestra();
        medicion = new Medicion();
        it_tipoMuestraVert= new RichInputText(); 
        sc_tipoMuestra= new  RichSelectOneChoice();
        panelGFecha= new RichPanelGroupLayout();
        panelGHora= new RichPanelGroupLayout();
        it_caudal= new RichInputText(); 
        
        
          CalidadDelegate cald = CalidadDelegate.getInstance();
          
        this.listaTipoMuestra = this.getListaPorCategoria(ConstantesCalidad.TIPO_MUESTRA);
          
        
        this.listaLaboratorio = this.getListaPorCategoria(ConstantesCalidad.LABORATORIO);
        this.listaParametro= this.getListaPorCategoria(ConstantesCalidad.PARAMETROS_CALIDAD);
        this.listaUnidad= this.getListaPorCategoria(ConstantesCalidad.UNIDADES_CALIDAD);   
        this.listaSigno= this.getListaPorCategoria(ConstantesCalidad.SIGNOS);
        this.listaHorario= this.cargarHorario();
        this.listaHora=this.cargarHoras();
        this.listaMin= this.cargarMin();
  
  
          
     
          
        Object obj = FacesUtils.getFromSession("puntoSeleccionado");
          
          if(obj instanceof PuntoMonitoreo){
              this.puntoM = (PuntoMonitoreo)obj;
               Long codigoPunto=  this.puntoM.getId();
               this.puntoM = cald.getPuntoMonitoreo(codigoPunto);  
          } else if(obj instanceof PuntoMonitoreoTO){
                   this.puntoMonitoreoTO = (PuntoMonitoreoTO)obj;
                   Long codigoPunto=  this.puntoMonitoreoTO.getId();
                   this.puntoM = cald.getPuntoMonitoreo(codigoPunto); 
            }      
          listaMuestras = cald.getMuestras(this.puntoM);
          int  nro=listaMuestras.size();
          
          if(this.puntoM.getIdVertimiento()!=null && nro==0){
               this.idvert=this.puntoM.getIdVertimiento().getId();
              this.vertimientoMuestra();
              
              }else
            {
                    //No hay vertimiento
                    this.panelGFecha.setVisible(false);
                    AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelGFecha);   
                    this.panelGHora.setVisible(false);
                    AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelGHora);  
                    this.it_caudal.setVisible(false);
                    AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_caudal); 
                    this.it_tipoMuestraVert.setVisible(false);
                    AdfFacesContext.getCurrentInstance().addPartialTarget(it_tipoMuestraVert);
                    this.sc_tipoMuestra.setVisible(true);
                    AdfFacesContext.getCurrentInstance().addPartialTarget(sc_tipoMuestra);
                
                }
           
      }catch(IdeamException e){
          showMessage(e);
      }
    }
    
    
    

    
    
    public void vertimientoMuestra(){
     
     try{     
            if(  this.idvert!=null ){
              
                CalidadDelegate cald = CalidadDelegate.getInstance();
                  this.muestra.setIdTipoMuestra(135);
                  Muestra tpv= new Muestra();
                  tpv= cald.loadMuestra(135);
                  this.muestra.setTipoMuestra(tpv.getTipoMuestra());
                  this.it_tipoMuestraVert.setVisible(true);
                  AdfFacesContext.getCurrentInstance().addPartialTarget(it_tipoMuestraVert);
                 
            }
            
            
            this.it_tipoMuestraVert.setValue(this.muestra.getTipoMuestra().getValor());
            AdfFacesContext.getCurrentInstance().addPartialTarget(it_tipoMuestraVert);
            this.panelGFecha.setVisible(true);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelGFecha);   
            this.panelGHora.setVisible(true);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelGHora);  
            this.it_caudal.setVisible(true);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_caudal); 
            this.sc_tipoMuestra.setVisible(false);
            AdfFacesContext.getCurrentInstance().addPartialTarget(sc_tipoMuestra);
              
        }catch(IdeamException e){
            showMessage(e);
        }
    }
    
    
    //ChangeListener Tipo Muestra
    public void sc_tipoMuestra_valueChangeListener(ValueChangeEvent valueChangeEvent) {
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        this.cambiaTipoMuestra();
    }
    
    
    public void cambiaTipoMuestra(){
         
        this.muestra.setTipoMuestra((Lista)this.sc_tipoMuestra.getValue());
         
         
         
       if(this.muestra.getTipoMuestra()!=null){
           String  nombreMuestra= "Muestra "+this.muestra.getTipoMuestra().getValor();
           this.tituloMuestra.setValue(nombreMuestra);
             
          if(this.muestra.getTipoMuestra().getCodigo().equals(ConstantesCalidad.MUESTRA_SIMPLE)){
               this.panelGFecha.setVisible(true);
               AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelGFecha);   
               this.panelGHora.setVisible(true);
               AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelGHora);  
                      
               this.it_caudal.setVisible(true);
               AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_caudal); 
               
               
               this.it_duracion.setVisible(false);
               AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_duracion); 
               this.it_intervaloTiempo.setVisible(false);
               AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_intervaloTiempo); 
               this.it_submuestras.setVisible(false);
               AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_submuestras); 
               this.it_verticales.setVisible(false);
               AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_verticales); 
                   
               
               }
          else  if(this.muestra.getTipoMuestra().getCodigo().equals(ConstantesCalidad.MUESTRA_INTEGRADA)){
                  this.panelGFecha.setVisible(true);
                  AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelGFecha);   
                  this.panelGHora.setVisible(true);
                  AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelGHora);  
                  AdfFacesContext.getCurrentInstance().addPartialTarget(this.id_fecha);   
                  this.it_duracion.setVisible(false);
                  AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_duracion); 
                  this.it_intervaloTiempo.setVisible(false);
                  AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_intervaloTiempo); 
                  this.it_submuestras.setVisible(false);
                  AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_submuestras); 
                  this.it_verticales.setVisible(true);
                  AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_verticales); 
                  this.it_caudal.setVisible(true);
                  AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_caudal); 
                     
              } 
          else  if(this.muestra.getTipoMuestra().getCodigo().equals(ConstantesCalidad.MUESTRA_COMPUESTA)){
                  this.panelGFecha.setVisible(true);
                  AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelGFecha);   
                  this.panelGHora.setVisible(true);
                  AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelGHora);   
                  this.id_fecha.setVisible(true);
                  AdfFacesContext.getCurrentInstance().addPartialTarget(this.id_fecha);   
                  this.it_duracion.setVisible(true);
                  AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_duracion); 
                  this.it_intervaloTiempo.setVisible(true);
                  AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_intervaloTiempo); 
                  this.it_submuestras.setVisible(true);
                  AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_submuestras); 
                  this.it_verticales.setVisible(false);
                  AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_verticales); 
                  this.it_caudal.setVisible(true);
                  AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_caudal);   
                     
              }
          
        
               
            }
      
    }
    
    
    
    //pantalla de datos básicos Muestras
    public void cb_next_actionListener(ActionEvent actionEvent) throws IdeamException{
        boolean continuar = true;
      
         if(nro!=0){
          if(this.sc_tipoMuestra.getValue()==null){
              showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.sc_tipoMuestra);       
              continuar = false;
          }
       
         }
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
    
        

    
    public void cambiaPanel(){
        this.panelBox1.setVisible(false);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelBox1);            
        this.panelParametros.setVisible(true);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelParametros);
          
    }

       public void saveMuestra(){
         try{
               CalidadDelegate cld = CalidadDelegate.getInstance();
               UsuarioConectadoTO usuarioConectado = (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
               muestra.setIdTipoMuestra(this.muestra.getTipoMuestra().getCodigo());
               muestra.setIdLaboratorio(this.muestra.getLaboratorio().getCodigo());
               if(this.muestra.getIdLaboratorio().equals(ConstantesCalidad.OTRO_LAB)){
                   muestra.setOtroLaboratorio(this.it_otro_lab.getValue().toString());
                }
               muestra.setCodigoAutoridad(usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue());
               muestra.setIdPuntoMonitoreo(this.puntoM);
               Muestra mm =  cld.updateMuestra(this.muestra);
             
                 try{
                     
                     /** 
                      * Estas variables son necesarias para llenar los datos intrinsecos de la auditoria.
                      */
                     
                     Auditoria auditoria = new Auditoria();
                     auditoria.setIdUsuario(usuarioConectado.getUsuario().getCodigo());
                     auditoria.setOperacion("GUARDAR");
                     auditoria.setObjeto("MUESTRAS_CALIDAD");
                     auditoria.setMetodo(Thread.currentThread().getStackTrace()[1].getMethodName());
                     auditoria.setClase(this.getClass().getName());
                     //Objeto Afectado (usuario modificado, eliminado o agregado) 
                     auditoria.setIdObjeto(new Long(mm.getId()));
                    
                     AuditoriasDelegate audDelegate = AuditoriasDelegate.getInstance();
                     audDelegate.setAuditoria(auditoria);
                     
                 }catch(Exception e){
                     System.out.println("No se pudo Almacenar la auditoria: ---------------------------------- ");
                     System.out.println(e.getMessage()+"------------------------------------------------------ ");
                 }

             
             
               FacesUtils.setInSession("muestraNueva", mm);
               this.cambiaPanel();
           
             }catch(IdeamException e){
                 showMessage(e);
             }
         }
     
     
    public void adicionarMuestra_actionListener(ActionEvent actionEvent) throws IdeamException{
      
        boolean continuar = true;
        
        if(this.sc_parametros.getValue()==null ){
               showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.sc_parametros);       
               continuar = false;
            }
        if(this.sc_unidad.getValue()==null ){
                showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.sc_unidad);       
                continuar = false;
            }
        if(this.sc_signo.getValue()==null ){
                showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.sc_signo);       
                continuar = false;
            }
        
        
        if(this.sc_signo.getValue()!=null  && ( this.medicion.getSigno().getCodigo()!=ConstantesCalidad.RANGO &&  this.medicion.getSigno().getCodigo()!=ConstantesCalidad.VALOR_CHAR  ) ){
            
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
        
        if(this.sc_signo.getValue()!=null  && this.medicion.getSigno().getCodigo().equals(ConstantesCalidad.VALOR_CHAR)  ){
             if(this.it_char.getValue()==null || 
                    this.it_char.getValue().toString().length()==0){
                    continuar = false;
                    showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.it_char);
                } 
          
            }
      
       
        if(continuar){   
            this.saveMedicion(); 
        }else{
            showMessage("No se puede agregar los campos estan vacios");
             }
    }


    public void saveMedicion(){
          try{
               CalidadDelegate cld = CalidadDelegate.getInstance();
               Object obj = FacesUtils.getFromSession("muestraNueva");
               if(obj instanceof Muestra){
                     this.muestra = (Muestra)obj;
                      Long codigo=  this.muestra.getId();
                      this.muestra = cld.getMuestra(codigo);  
                }
               UsuarioConectadoTO usuarioConectado = (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
               medicion.setIdMuestra(this.muestra);
               medicion.setIdParametro(this.medicion.getParametro().getCodigo());
               medicion.setIdSigno(this.medicion.getSigno().getCodigo());
               medicion.setIdUnidad(this.medicion.getUnidad().getCodigo());
               medicion.setLimiteDeteccion((this.it_limite.getValue()!=null)? Double.parseDouble(this.it_limite.getValue().toString()):null);
               medicion.setMetodoDeterminacion((this.it_metodo.getValue()!=null)? this.it_metodo.getValue().toString():null);
  
                
               if(this.medicion.getSigno().getCodigo().equals(ConstantesCalidad.RANGO)){
                 medicion.setValor2(Double.parseDouble(this.it_valor2.getValue().toString()));  
               }  
                 if(this.medicion.getSigno().getCodigo().equals(ConstantesCalidad.VALOR_CHAR)){
                   medicion.setValorchar(this.it_char.getValue().toString());  
                 }  
              
              
              if(this.check_acreditado.isSelected()){
                medicion.setEsAcreditado(1);
                  }
              else{
                medicion.setEsAcreditado(0);
                  }
              medicion.setCodigoAutoridad(usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue());
               cld.updateMedicion(this.medicion);
               this.listarMediciones();
              
             }catch(IdeamException e){
                 showMessage(e);
             }
         }
    
    
    
    
    public void lab_valueChageListener(ValueChangeEvent valueChangeEvent) {
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        muestra.setLaboratorio((Lista)(this.sc_laboratorio.getValue())); 
        
        
         if(this.muestra.getLaboratorio()!=null){
             if(this.muestra.getLaboratorio().getCodigo().equals(ConstantesCalidad.OTRO_LAB)){
             
             
                  System.out.println("entroooo"+this.muestra.getLaboratorio().getCodigo());
                 this.it_otro_lab.setVisible(true);
                 AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_otro_lab); 
              
              }  else{
                 
                     this.it_otro_lab.setVisible(false);
                     AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_otro_lab); 
                 
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
                  
                  
        }else if(this.medicion.getSigno().getCodigo().equals(ConstantesCalidad.VALOR_CHAR)){
              
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
        
  
    
    
  public void listarMediciones(){
    
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
        
        try{
            CalidadDelegate cld = CalidadDelegate.getInstance();
            this.listaMediciones= new ArrayList();
            this.listaMediciones = cld.getMediciones(this.muestra);
           
           }catch(IdeamException e){
            showMessage(e);
     }
    AdfFacesContext.getCurrentInstance().addPartialTarget(t_mediciones);
    showPopup(p_registro_med, true);
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

    public void setCb_next(RichCommandButton cb_next) {
        this.cb_next = cb_next;
    }

    public RichCommandButton getCb_next() {
        return cb_next;
    }

    public void setSpacer1(RichSpacer spacer1) {
        this.spacer1 = spacer1;
    }

    public RichSpacer getSpacer1() {
        return spacer1;
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


    public void setPanelParametros(RichPanelBox panelParametros) {
        this.panelParametros = panelParametros;
    }

    public RichPanelBox getPanelParametros() {
        return panelParametros;
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

    public void setP_registro_med(RichPopup p_registro_med) {
        this.p_registro_med = p_registro_med;
    }

    public RichPopup getP_registro_med() {
        return p_registro_med;
    }

    public void setD_registro_med(RichDialog d_registro_med) {
        this.d_registro_med = d_registro_med;
    }

    public RichDialog getD_registro_med() {
        return d_registro_med;
    }

    public void setPanelStretchLayout5(RichPanelStretchLayout panelStretchLayout5) {
        this.panelStretchLayout5 = panelStretchLayout5;
    }

    public RichPanelStretchLayout getPanelStretchLayout5() {
        return panelStretchLayout5;
    }

    public void setT_registro_med(RichActiveOutputText t_registro_med) {
        this.t_registro_med = t_registro_med;
    }

    public RichActiveOutputText getT_registro_med() {
        return t_registro_med;
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

    public void setCb_aceptar_medicion(RichCommandButton cb_aceptar_medicion) {
        this.cb_aceptar_medicion = cb_aceptar_medicion;
    }

    public RichCommandButton getCb_aceptar_medicion() {
        return cb_aceptar_medicion;
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


    public void setPanelGroupLayout10(RichPanelGroupLayout panelGroupLayout10) {
        this.panelGroupLayout10 = panelGroupLayout10;
    }

    public RichPanelGroupLayout getPanelGroupLayout10() {
        return panelGroupLayout10;
    }

 


    public void setPanelGroupLayout4(RichPanelGroupLayout panelGroupLayout4) {
        this.panelGroupLayout4 = panelGroupLayout4;
    }

    public RichPanelGroupLayout getPanelGroupLayout4() {
        return panelGroupLayout4;
    }

    public void setPanelFormLayout21(RichPanelFormLayout panelFormLayout21) {
        this.panelFormLayout21 = panelFormLayout21;
    }

    public RichPanelFormLayout getPanelFormLayout21() {
        return panelFormLayout21;
    }

  

    public void setSelectItems61(UISelectItems selectItems61) {
        this.selectItems61 = selectItems61;
    }

    public UISelectItems getSelectItems61() {
        return selectItems61;
    }

   

  

    public void setSelectItems71(UISelectItems selectItems71) {
        this.selectItems71 = selectItems71;
    }

    public UISelectItems getSelectItems71() {
        return selectItems71;
    }

   

    public void setSelectItems81(UISelectItems selectItems81) {
        this.selectItems81 = selectItems81;
    }

    public UISelectItems getSelectItems81() {
        return selectItems81;
    }

   

   
   

    public void setPanelGroupLayout81(RichPanelGroupLayout panelGroupLayout81) {
        this.panelGroupLayout81 = panelGroupLayout81;
    }

    public RichPanelGroupLayout getPanelGroupLayout81() {
        return panelGroupLayout81;
    }

    public void setSpacer81(RichSpacer spacer81) {
        this.spacer81 = spacer81;
    }

    public RichSpacer getSpacer81() {
        return spacer81;
    }


    public void setPanelGroupLayout101(RichPanelGroupLayout panelGroupLayout101) {
        this.panelGroupLayout101 = panelGroupLayout101;
    }

    public RichPanelGroupLayout getPanelGroupLayout101() {
        return panelGroupLayout101;
    }

    public void setPanelCollection11(RichPanelCollection panelCollection11) {
        this.panelCollection11 = panelCollection11;
    }

    public RichPanelCollection getPanelCollection11() {
        return panelCollection11;
    }



    public void setPanelGroupLayout11(RichPanelGroupLayout panelGroupLayout11) {
        this.panelGroupLayout11 = panelGroupLayout11;
    }

    public RichPanelGroupLayout getPanelGroupLayout11() {
        return panelGroupLayout11;
    }

      
      
    public void cb_aceptar_actionListener(ActionEvent actionEvent) {
        this.setAceptarAction("detallepunto");
    }

    
    

    public String getAceptarAction() {
        return aceptarAction;
    }

    public void setAceptarAction(String aceptarAction) {
        this.aceptarAction = aceptarAction;
    }

    public void setSpacer11(RichSpacer spacer11) {
        this.spacer11 = spacer11;
    }

    public RichSpacer getSpacer11() {
        return spacer11;
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

    public void setIt_nroMuestra(RichInputText it_nroMuestra) {
        this.it_nroMuestra = it_nroMuestra;
    }

    public RichInputText getIt_nroMuestra() {
        return it_nroMuestra;
    }

    public void setCb_finalizar(RichCommandButton cb_finalizar) {
        this.cb_finalizar = cb_finalizar;
    }

    public RichCommandButton getCb_finalizar() {
        return cb_finalizar;
    }

    public void setIt_tipoMuestraVert(RichInputText inputText1) {
        this.it_tipoMuestraVert = inputText1;
    }

    public RichInputText getIt_tipoMuestraVert() {
        return it_tipoMuestraVert;
    }

    public void setListaMuestras(List listaMuestras) {
        this.listaMuestras = listaMuestras;
    }

    public List getListaMuestras() {
        return listaMuestras;
    }

    public void setIdvert(Long idvert) {
        this.idvert = idvert;
    }

    public Long getIdvert() {
        return idvert;
    }

    public void setNro(int nro) {
        this.nro = nro;
    }

    public int getNro() {
        return nro;
    }

    public void setPanelFormLayout3(RichPanelFormLayout panelFormLayout3) {
        this.panelFormLayout3 = panelFormLayout3;
    }

    public RichPanelFormLayout getPanelFormLayout3() {
        return panelFormLayout3;
    }

    public void setIt_char(RichInputText inputText1) {
        this.it_char = inputText1;
    }

    public RichInputText getIt_char() {
        return it_char;
    }

    public void setOt_char(RichActiveOutputText activeOutputText4) {
        this.ot_char = activeOutputText4;
    }

    public RichActiveOutputText getOt_char() {
        return ot_char;
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

   
}//Fin
