package co.gov.ideam.sirh.calidad.view;


import co.gov.ideam.sirh.auditoria.model.Auditoria;
import co.gov.ideam.sirh.auditoria.view.AuditoriasDelegate;
import co.gov.ideam.sirh.calidad.model.PuntoMonitoreo;
import co.gov.ideam.sirh.calidad.model.PuntoMonitoreoTO;
import co.gov.ideam.sirh.fuentes.model.FnttFuente;

import co.gov.ideam.sirh.fuentes.model.FnttTramo;

import co.gov.ideam.sirh.fuentes.view.FuenteDelegate;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Divipola;

import co.gov.ideam.sirh.parametros.model.PartZonificListas;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;

import co.gov.ideam.sirh.usuarios.agua.view.UsuariosAguaDelegate;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.util.ConstantesCalidad;

import co.gov.ideam.sirh.view.StandarBean;



import java.util.ArrayList;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UISelectItems;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.output.RichActiveOutputText;
import oracle.adf.view.rich.component.rich.output.RichImage;
import oracle.adf.view.rich.component.rich.output.RichOutputLabel;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;


public class AdicionarPuntosMonitoreoBean extends StandarBean{
  private RichForm form1;
  private RichDocument document1;
  private List listaDepartamentos;
  private List listaMunicipios;
  private List listaArea;
  private List listaZona;
  private List listaSubzona;
  private List listaFuentes;
  private List listaTramos;
  private List listaTipoPuntos;
  private List listaUbicacion;
  private List listaSistRef;
  private String aceptarAction;
  private RichSelectOneChoice selectOneChoice1;
  private UISelectItems selectItems1;
  private RichSelectOneChoice selectOneChoice3;
  private UISelectItems selectItems3;
  private RichSelectOneChoice selectOneChoice4;
  private UISelectItems selectItems4;
  private RichSelectOneChoice selectOneChoice5;
  private UISelectItems selectItems5;
  private RichSelectOneChoice selectOneChoice10;
  private UISelectItems selectItems10;
  private RichSelectOneChoice list_tipoPto;
  private UISelectItems selectItems81;
  private RichSelectOneChoice list_ubiPto;
  private UISelectItems selectItems91;
  private RichPanelGroupLayout panelGroupLayout1;
  private RichPanelBox panelBox1;
  private RichPanelFormLayout panelFormLayout1;
  private RichPanelStretchLayout panelStretchLayout1;
  private RichPanelGroupLayout panelGroupLayout2;
  private RichPanelGroupLayout panelGroupLayout3;
  private RichPanelStretchLayout panelStretchLayout11;
  private RichPanelGroupLayout panelGroupLayout21;
  private RichPanelGroupLayout panelGroupLayout31;
  private RichPanelFormLayout panelFormLayout11;
  private RichPanelGroupLayout panelGroupLayout4;
  private RichPanelBox panelBox2;
  private RichPanelStretchLayout panelStretchLayout2;
  private RichPanelGroupLayout panelGroupLayout5;
  private RichPanelGroupLayout panelGroupLayout6;
  private RichCommandButton cb_next1;
  private RichCommandButton cb_ant1;
  private RichCommandButton cb_next2;
  private RichSpacer spacer1;
  private RichSpacer spacer2;
    private RichPanelFormLayout panelFormLayout2;
  private RichSelectOneChoice sist_referencia;
  private UISelectItems selectItems13;
  private RichInputText gra_lat;
  private RichInputText min_lat;
  private RichInputText seg_lat;
  private RichInputText gra_long;
  private RichInputText min_long;
  private RichInputText seg_long;
  private RichOutputText text_lat;
  private RichOutputText text_long;
  private RichInputText t_altitud;
  private RichInputText t_descripcion;
  private RichInputText t_nombreP;
  private RichSelectOneChoice selectOneChoice2;
  private UISelectItems selectItems2;
  private RichSelectOneChoice selectOneChoice6;
  private UISelectItems selectItems6;
  private RichPopup p_resgistro_punto;
  private RichDialog d_registro_exitoso;
  private RichPanelStretchLayout panelStretchLayout3;
  private RichActiveOutputText t_registro_punto;
  private RichImage image1;
  private RichPanelGroupLayout panelGroupLayout7;
  private RichCommandButton aceptar_punto;
  private PuntoMonitoreo puntoMononitoreo;
  private Divipola departamentoSeleccionado;
  private Divipola municipioSeleccionado;
    private RichPanelGroupLayout panelGroupLayout8;

    private RichPanelGroupLayout panelGroupLayout9;
    private RichPanelFormLayout panelFormLayout3;
    private RichPanelGroupLayout panelGroupLayout12;
    private RichSpacer spacer6;
    private RichActiveOutputText ot_datos;
    private RichActiveOutputText ot_titulo;
    private RichActiveOutputText ot_titulo3;
    private FnttTramo tramo;
    private RichInputText it_fuente;
    private RichInputText it_tramo;
    private RichInputText it_subzona;
    private RichInputText it_zona;
    private RichInputText it_area;
    private RichSpacer spacer8;
    private RichSpacer spacer3;
    private RichPanelGroupLayout panelGroupLayout13;
    private RichPanelFormLayout panelFormLayout6;
    private RichSpacer spacer4;
    private RichSpacer spacer5;
    private RichActiveOutputText activeOutputText6;
    private RichSpacer spacer7;
    private RichOutputLabel outputLabel1;
    private RichSpacer spacer9;
    private RichPanelFormLayout panelFormLayout4;
    private RichSpacer spacer10;
    private RichOutputLabel outputLabel2;
    private RichSpacer spacer11;


    public AdicionarPuntosMonitoreoBean(){
       FacesUtils.setActiveBean("adicionarPuntosMonitoreo",
                                "AdicionarPuntosdeMonitoreo",
                                CalidadDelegate.class);
       FacesUtils.removeManagedBeanFromSession("muestrasPunto");
       FacesUtils.removeManagedBeanFromSession("detallePuntoMonitoreo");
       FacesUtils.removeManagedBeanFromSession("MuestrasTreeHandler");
       FacesUtils.removeManagedBeanFromSession("PuntosMonitoreoTreeHandler");
       
   
       this.load();        
   }
  public void load(){
    
      try{      
        puntoMononitoreo = new PuntoMonitoreo();
        selectOneChoice4 = new RichSelectOneChoice();
        selectOneChoice5= new RichSelectOneChoice();  
        selectOneChoice3 = new RichSelectOneChoice();  
        selectOneChoice10 = new RichSelectOneChoice();
        selectOneChoice6 = new RichSelectOneChoice();
        it_fuente = new   RichInputText();
        it_tramo = new   RichInputText();    
        it_subzona = new   RichInputText();   
        it_zona= new   RichInputText();   
        it_area= new   RichInputText();   
          
        this.listaTipoPuntos = this.getListaPorCategoria(ConstantesCalidad.TIPOS_PUNTO_MONITOREO);
        this.listaUbicacion = this.getListaPorCategoria(ConstantesCalidad.UBICACION_PUNTO_MONITOREO);
      
        this.listaSistRef=  this.getListaPorCategoria(ConstantesCalidad.SISTEMA_REFERENCIA);
  
        this.listaDepartamentos = this.cargarDivipola(null);
        this.listaArea = this.cargarZonificacion(null);
        this.listaMunicipios = new ArrayList();
        this.listaZona = new ArrayList();
        this.listaSubzona = new ArrayList();
        this.listaFuentes = new ArrayList();
        this.listaTramos = new ArrayList();
        
        
        System.out.println("adii");
          this.tramo = new FnttTramo();
        
          Object obj = FacesUtils.getFromSession("tramoSeleccionado");
        
          FuenteDelegate fd = FuenteDelegate.getInstance();
          
          if(obj instanceof FnttTramo){
              this.tramo = (FnttTramo)obj;
              Long codigo =  this.tramo.getId();      
              
              System.out.println("adii  cod  " +codigo);
              this.tramo = fd.getTramo(codigo);                
          }
     
          if(this.tramo.getId()!=null){
              
              System.out.println("adii  cod  " +this.tramo.getId());
              this.deshabilitaListas(this.tramo);
           
          }else{
                  this.it_fuente.setVisible(false);
                  this.it_tramo.setVisible(false);
                  this.it_subzona.setVisible(false);
                  this.it_zona.setVisible(false);
                  this.it_area.setVisible(false);
               }
    }catch(IdeamException e){
          showMessage(e);
      }
  }




  public void deshabilitaListas(FnttTramo tramo){
      
            if(this.tramo.getId()!=null){
               
                this.puntoMononitoreo.setIdFuente(this.tramo.getIdFuente());
                this.puntoMononitoreo.setIdTramo(this.tramo);
                this.puntoMononitoreo.setIdArea(this.tramo.getIdArea());
                this.puntoMononitoreo.setIdZona(this.tramo.getIdZona());
                this.puntoMononitoreo.setIdSubzona(this.tramo.getIdSubzona());
                
               
                this.selectOneChoice3.setVisible(false);
                AdfFacesContext.getCurrentInstance().addPartialTarget(selectOneChoice3);
                this.selectOneChoice4.setVisible(false);
                AdfFacesContext.getCurrentInstance().addPartialTarget(selectOneChoice4);
                this.selectOneChoice5.setVisible(false);
                AdfFacesContext.getCurrentInstance().addPartialTarget(selectOneChoice5);
                
                
                
                this.selectOneChoice10.setVisible(false);
                AdfFacesContext.getCurrentInstance().addPartialTarget(selectOneChoice10);
                this.selectOneChoice6.setVisible(false);
                AdfFacesContext.getCurrentInstance().addPartialTarget(selectOneChoice6);
               
                this.it_area.setVisible(true);
                this.it_area.setValue(this.puntoMononitoreo.getIdArea().getValor());
                AdfFacesContext.getCurrentInstance().addPartialTarget(it_area);
               
               
                this.it_zona.setVisible(true);
                this.it_zona.setValue(this.puntoMononitoreo.getIdZona().getValor());
                AdfFacesContext.getCurrentInstance().addPartialTarget(it_zona);
                
                
                this.it_subzona.setVisible(true);
                this.it_subzona.setValue(this.puntoMononitoreo.getIdSubzona().getValor());
                AdfFacesContext.getCurrentInstance().addPartialTarget(it_subzona);
                
                this.it_fuente.setVisible(true);
                this.it_fuente.setValue(this.puntoMononitoreo.getIdFuente().getNombre());
                AdfFacesContext.getCurrentInstance().addPartialTarget(it_fuente);
                
                
                
               
                this.it_tramo.setVisible(true);
                this.it_tramo.setValue(this.puntoMononitoreo.getIdTramo().getNombre());
                AdfFacesContext.getCurrentInstance().addPartialTarget(it_tramo);
                
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


  public void setListaTipoPuntos(List listaTipoPuntos) {
    this.listaTipoPuntos = listaTipoPuntos;
  }

  public List getListaTipoPuntos() {
    return listaTipoPuntos;
  }

  public void setListaUbicacion(List listaUbicacion) {
    this.listaUbicacion = listaUbicacion;
  }

  public List getListaUbicacion() {
    return listaUbicacion;
  }


  public void setSelectOneChoice1(RichSelectOneChoice selectOneChoice1) {
    this.selectOneChoice1 = selectOneChoice1;
  }

  public RichSelectOneChoice getSelectOneChoice1() {
    return selectOneChoice1;
  }

  public void setSelectItems1(UISelectItems selectItems1) {
    this.selectItems1 = selectItems1;
  }

  public UISelectItems getSelectItems1() {
    return selectItems1;
  }


  //ChangeListener Departamentos
  public void selectOneChoice1_valueChangeListener(ValueChangeEvent event) throws IdeamException{
    Object departamento = event.getNewValue();
    this.listaMunicipios = new ArrayList();
   
    try{
        if(departamento!=null){
            this.listaMunicipios = this.cargarDivipola(((Divipola)departamento).getId());
           
        }
    }catch(IdeamException e){            
        showMessage(e);
    }
    AdfFacesContext.getCurrentInstance().addPartialTarget(selectOneChoice2);
    
  }

  //ChangeListener Area
  public void selectOneChoice3_valueChangeListener(ValueChangeEvent event) {
    Object area = event.getNewValue();
    this.listaZona = new ArrayList();
  
    try{
        if(area!=null){
            this.listaZona = this.cargarZonificacion(((PartZonificListas)area).getId());
           
        }
    }catch(IdeamException e){            
        showMessage(e);
    }
    AdfFacesContext.getCurrentInstance().addPartialTarget(selectOneChoice4);
   

  }
  //ChangeListener Zona
  public void selectOneChoice4_valueChangeListener(ValueChangeEvent event) {
    Object zona = event.getNewValue();
    this.listaSubzona = new ArrayList();
 
    try{
        if(zona!=null){
            this.listaSubzona = this.cargarZonificacion(((PartZonificListas)zona).getId());
           
        }
    }catch(IdeamException e){            
        showMessage(e);
    }
    AdfFacesContext.getCurrentInstance().addPartialTarget(selectOneChoice5);

  }
  
  //ChangeListener Subzona
  public void selectOneChoice5_valueChangeListener(ValueChangeEvent event) {
    Object subzona = event.getNewValue();
    this.listaFuentes = new ArrayList();
  
    try{
        if(subzona!=null){
            this.listaFuentes = this.getListaFuentes(((PartZonificListas)subzona).getId());
           
        }
    }catch(IdeamException e){            
        showMessage(e);
    }
    AdfFacesContext.getCurrentInstance().addPartialTarget(selectOneChoice10);

  }

  //ChangeListener Fuentes
  public void selectOneChoice10_valueChangeListener(ValueChangeEvent event) {
    Object fuenteid = event.getNewValue();
    this.listaTramos = new ArrayList();
    try{
        if(fuenteid!=null){
            FnttFuente f=(FnttFuente)fuenteid;
            System.out.println("fuente a consultar:"+f.getId());
            this.listaTramos = this.getListaTramos(f.getId().intValue());
           
        }
    }catch(IdeamException e){            
        showMessage(e);
    }
    AdfFacesContext.getCurrentInstance().addPartialTarget(selectOneChoice6);

  }
  
  
  
  //pantalla de datos básicos puntos monitoreo
  public void cb_next1_actionListener(ActionEvent actionEvent) throws IdeamException{
      boolean continuar = true;
      
  
    if(this.t_nombreP.getValue()==null || 
        this.t_nombreP.getValue().toString().length()==0){
        continuar = false;
        showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.t_nombreP);
    }
 
 
    if(this.list_tipoPto.getValue()==null){
        showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.list_tipoPto);       
        continuar = false;
    }
    
    if(this.list_ubiPto.getValue()==null){
        showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.list_ubiPto);       
        continuar = false;
    }
      if(this.selectOneChoice1.getValue()==null){
          showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.selectOneChoice1);       
          continuar = false;
      }
      
      
      if(this.selectOneChoice2.getValue()==null){
          showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.selectOneChoice2);       
          continuar = false;
      }
      
    
    if(this.tramo.getId()!=null){
    
            if(this.it_area.getValue()==null || 
                this.it_area.getValue().toString().length()==0){
                continuar = false;
                showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.it_area);
            }
            
            if(this.it_zona.getValue()==null || 
                this.it_zona.getValue().toString().length()==0){
                continuar = false;
                showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.it_zona);
            }
            
            if(this.it_subzona.getValue()==null || 
                this.it_subzona.getValue().toString().length()==0){
                continuar = false;
                showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.it_subzona);
            }
            
            if(this.it_fuente.getValue()==null || 
                this.it_fuente.getValue().toString().length()==0){
                continuar = false;
                showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.it_fuente);
            }
            
            if(this.it_tramo.getValue()==null || 
                this.it_tramo.getValue().toString().length()==0){
                continuar = false;
                showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.it_tramo);
            }
            
    
        }else{
    
          if(this.selectOneChoice3.getValue()==null){
              showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.selectOneChoice3);       
              continuar = false;
          }      
       
        
        if(this.selectOneChoice4.getValue()==null ){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.selectOneChoice4);       
            continuar = false;
        }
          
        
        
        if(this.selectOneChoice5.getValue()==null ){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.selectOneChoice5);       
            continuar = false;
        }
       
        if(this.selectOneChoice10.getValue()==null ){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.selectOneChoice10);       
            continuar = false;
        }
          
       
        if(this.selectOneChoice6.getValue()==null ){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.selectOneChoice6);       
            continuar = false;
        }
    
    }
      if(continuar){    
         this.panelBox1.setVisible(false);
          AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelBox1);            
          this.panelBox2.setVisible(true);
          AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelBox2);
      }
  }
  
  public void cb_ant1_actionListener(ActionEvent actionEvent) throws IdeamException{       
      this.setDepartamentoSeleccionado((Divipola)this.selectOneChoice1.getValue());
      this.setMunicipioSeleccionado((Divipola)this.selectOneChoice2.getValue());  
    this.panelBox2.setVisible(false);
    AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelBox2);
    this.panelBox1.setVisible(true);
    AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelBox1);   
  }
  
  
  public void cb_next2_actionListener(ActionEvent actionEvent) throws IdeamException{
      boolean continuar = true;
    
      if(this.sist_referencia.getValue()==null ){
          showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.sist_referencia);       
          continuar = false;
      }
      if(this.gra_lat.getValue()==null || 
         this.gra_lat.getValue().toString().length()==0){
          showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.gra_lat);       
          continuar = false;
      }   
      
      if(this.min_lat.getValue()==null || 
         this.min_lat.getValue().toString().length()==0){
          showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.min_lat);       
          continuar = false;
      }  
      
      if(this.seg_lat.getValue()==null || 
         this.seg_lat.getValue().toString().length()==0){
          showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.seg_lat);       
          continuar = false;
      }
   
      
    if(this.gra_long.getValue()==null || 
       this.gra_long.getValue().toString().length()==0){
        showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.gra_long);       
        continuar = false;
    }   
    
    if(this.min_long.getValue()==null || 
       this.min_long.getValue().toString().length()==0){
        showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.min_long);       
        continuar = false;
    }  
    
    

      
    if(this.seg_long.getValue()==null || 
       this.seg_long.getValue().toString().length()==0){
        showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.seg_long);       
        continuar = false;
    }  
      
      if(this.t_altitud.getValue()==null || 
         this.t_altitud.getValue().toString().length()==0){
          showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.t_altitud);       
          continuar = false;
      }  
      
     if(continuar){   
         
           
            this.save();   
            
            
        } 
  }
  
  
  public void save(){
      
 
           try{
              CalidadDelegate cld = CalidadDelegate.getInstance();
              UsuarioConectadoTO usuarioConectado = (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
               puntoMononitoreo.setNombre(this.puntoMononitoreo.getNombre().toUpperCase());
               puntoMononitoreo.setIdTipo_punto(this.puntoMononitoreo.getTipoPunto().getCodigo());
               puntoMononitoreo.setIdubicacion(this.puntoMononitoreo.getUbicacion().getCodigo());
               puntoMononitoreo.setIdDepartamento(this.puntoMononitoreo.getDepartamento().getId());
               puntoMononitoreo.setIdmunicipio(this.puntoMononitoreo.getMunicipio().getId());
               
               if(this.tramo.getId()==null){
               
               puntoMononitoreo.setIdFuente((FnttFuente)this.selectOneChoice10.getValue());
               puntoMononitoreo.setIdTramo((FnttTramo)this.selectOneChoice6.getValue());
               puntoMononitoreo.setIdArea((PartZonificListas)this.selectOneChoice3.getValue());
               puntoMononitoreo.setIdZona((PartZonificListas)this.selectOneChoice4.getValue());
               puntoMononitoreo.setIdSubzona((PartZonificListas)this.selectOneChoice5.getValue());
               
               }else{
                       this.puntoMononitoreo.setIdFuente(this.tramo.getIdFuente());
                       this.puntoMononitoreo.setIdTramo(this.tramo);
                       this.puntoMononitoreo.setIdArea(this.tramo.getIdArea());
                       this.puntoMononitoreo.setIdZona(this.tramo.getIdZona());
                       this.puntoMononitoreo.setIdSubzona(this.tramo.getIdSubzona());
                   
                   }
               
               
               
               puntoMononitoreo.setIdSist_ref(this.puntoMononitoreo.getSistemaRef().getCodigo());
               puntoMononitoreo.setCodigoAutoridad(usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue());
               PuntoMonitoreo pp=     cld.updatePuntoMonitoreo(this.puntoMononitoreo);
               System.out.println("punto almacenado-------------------->"+pp.getId());
               pp= cld.getPuntoMonitoreo(pp.getId());
               System.out.println("puntos-------------------->"+pp.getNombre());
               FacesUtils.setInSession("puntoSeleccionado",pp);
                 
               try{
                   
                   /** 
                    * Estas variables son necesarias para llenar los datos intrinsecos de la auditoria.
                    */
                   
                   Auditoria auditoria = new Auditoria();
                   auditoria.setIdUsuario(usuarioConectado.getUsuario().getCodigo());
                   auditoria.setOperacion("GUARDAR");
                   auditoria.setObjeto("PUNT0SMONITOREO_CALIDAD");
                   auditoria.setMetodo(Thread.currentThread().getStackTrace()[1].getMethodName());
                   auditoria.setClase(this.getClass().getName());
                   //Objeto Afectado (usuario modificado, eliminado o agregado) 
                   auditoria.setIdObjeto(new Long(pp.getId()));
                  
                   AuditoriasDelegate audDelegate = AuditoriasDelegate.getInstance();
                   audDelegate.setAuditoria(auditoria);
                   
               }catch(Exception e){
                   System.out.println("No se pudo Almacenar la auditoria: ---------------------------------- ");
                   System.out.println(e.getMessage()+"------------------------------------------------------ ");
               } 
                 
                 
                 
                 
              showPopup(p_resgistro_punto, true);
           }catch(IdeamException e){
               showMessage(e);
           }
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
  public void setListaDepartamentos(List listaDepartamentos) {
    this.listaDepartamentos = listaDepartamentos;
  }

  public List getListaDepartamentos() {
    return listaDepartamentos;
  }

  public void setListaMunicipios(List listaMunicipios) {
    this.listaMunicipios = listaMunicipios;
  }

  public List getListaMunicipios() {
    return listaMunicipios;
  }

  public void setListaArea(List listaArea) {
    this.listaArea = listaArea;
  }

  public List getListaArea() {
    return listaArea;
  }

  public void setListaZona(List listaZona) {
    this.listaZona = listaZona;
  }

  public List getListaZona() {
    return listaZona;
  }

  public void setListaSubzona(List listaSubzona) {
    this.listaSubzona = listaSubzona;
  }

  public List getListaSubzona() {
    return listaSubzona;
  }

  public void setSelectOneChoice3(RichSelectOneChoice selectOneChoice3) {
    this.selectOneChoice3 = selectOneChoice3;
  }

  public RichSelectOneChoice getSelectOneChoice3() {
    return selectOneChoice3;
  }

  public void setSelectItems3(UISelectItems selectItems3) {
    this.selectItems3 = selectItems3;
  }

  public UISelectItems getSelectItems3() {
    return selectItems3;
  }

  public void setSelectOneChoice4(RichSelectOneChoice selectOneChoice4) {
    this.selectOneChoice4 = selectOneChoice4;
  }

  public RichSelectOneChoice getSelectOneChoice4() {
    return selectOneChoice4;
  }

  public void setSelectItems4(UISelectItems selectItems4) {
    this.selectItems4 = selectItems4;
  }

  public UISelectItems getSelectItems4() {
    return selectItems4;
  }

  public void setSelectOneChoice5(RichSelectOneChoice selectOneChoice5) {
    this.selectOneChoice5 = selectOneChoice5;
  }

  public RichSelectOneChoice getSelectOneChoice5() {
    return selectOneChoice5;
  }

  public void setSelectItems5(UISelectItems selectItems5) {
    this.selectItems5 = selectItems5;
  }

  public UISelectItems getSelectItems5() {
    return selectItems5;
  }

  public void setListaFuentes(List listaFuentes) {
    this.listaFuentes = listaFuentes;
  }

  public List getListaFuentes() {
    return listaFuentes;
  }

  public void setListaTramos(List listaTramos) {
    this.listaTramos = listaTramos;
  }

  public List getListaTramos() {
    return listaTramos;
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

  public void setPanelFormLayout1(RichPanelFormLayout panelFormLayout1) {
    this.panelFormLayout1 = panelFormLayout1;
  }

  public RichPanelFormLayout getPanelFormLayout1() {
    return panelFormLayout1;
  }

  public void setPanelStretchLayout1(RichPanelStretchLayout panelStretchLayout1) {
    this.panelStretchLayout1 = panelStretchLayout1;
  }

  public RichPanelStretchLayout getPanelStretchLayout1() {
    return panelStretchLayout1;
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

  public void setSpacer1(RichSpacer spacer1) {
    this.spacer1 = spacer1;
  }

  public RichSpacer getSpacer1() {
    return spacer1;
  }

  public void setSelectOneChoice10(RichSelectOneChoice selectOneChoice10) {
    this.selectOneChoice10 = selectOneChoice10;
  }

  public RichSelectOneChoice getSelectOneChoice10() {
    return selectOneChoice10;
  }

  public void setSelectItems10(UISelectItems selectItems10) {
    this.selectItems10 = selectItems10;
  }

  public UISelectItems getSelectItems10() {
    return selectItems10;
  }


  public void setPanelStretchLayout11(RichPanelStretchLayout panelStretchLayout11) {
    this.panelStretchLayout11 = panelStretchLayout11;
  }

  public RichPanelStretchLayout getPanelStretchLayout11() {
    return panelStretchLayout11;
  }

  public void setPanelGroupLayout21(RichPanelGroupLayout panelGroupLayout21) {
    this.panelGroupLayout21 = panelGroupLayout21;
  }

  public RichPanelGroupLayout getPanelGroupLayout21() {
    return panelGroupLayout21;
  }

  public void setPanelGroupLayout31(RichPanelGroupLayout panelGroupLayout31) {
    this.panelGroupLayout31 = panelGroupLayout31;
  }

  public RichPanelGroupLayout getPanelGroupLayout31() {
    return panelGroupLayout31;
  }

  public void setCb_next1(RichCommandButton cb_next_paso_11) {
    this.cb_next1 = cb_next_paso_11;
  }

  public RichCommandButton getCb_next1() {
    return cb_next1;
  }


  public void setPanelFormLayout11(RichPanelFormLayout panelFormLayout11) {
    this.panelFormLayout11 = panelFormLayout11;
  }

  public RichPanelFormLayout getPanelFormLayout11() {
    return panelFormLayout11;
  }


  public void setList_tipoPto(RichSelectOneChoice selectOneChoice81) {
    this.list_tipoPto = selectOneChoice81;
  }

  public RichSelectOneChoice getList_tipoPto() {
    return list_tipoPto;
  }

  public void setSelectItems81(UISelectItems selectItems81) {
    this.selectItems81 = selectItems81;
  }

  public UISelectItems getSelectItems81() {
    return selectItems81;
  }

  public void setList_ubiPto(RichSelectOneChoice selectOneChoice91) {
    this.list_ubiPto = selectOneChoice91;
  }

  public RichSelectOneChoice getList_ubiPto() {
    return list_ubiPto;
  }

  public void setSelectItems91(UISelectItems selectItems91) {
    this.selectItems91 = selectItems91;
  }

  public UISelectItems getSelectItems91() {
    return selectItems91;
  }

  public void setPanelGroupLayout4(RichPanelGroupLayout panelGroupLayout4) {
    this.panelGroupLayout4 = panelGroupLayout4;
  }

  public RichPanelGroupLayout getPanelGroupLayout4() {
    return panelGroupLayout4;
  }

  public void setPanelBox2(RichPanelBox panelBox2) {
    this.panelBox2 = panelBox2;
  }

  public RichPanelBox getPanelBox2() {
    return panelBox2;
  }

  public void setPanelStretchLayout2(RichPanelStretchLayout panelStretchLayout2) {
    this.panelStretchLayout2 = panelStretchLayout2;
  }

  public RichPanelStretchLayout getPanelStretchLayout2() {
    return panelStretchLayout2;
  }

  public void setPanelGroupLayout5(RichPanelGroupLayout panelGroupLayout5) {
    this.panelGroupLayout5 = panelGroupLayout5;
  }

  public RichPanelGroupLayout getPanelGroupLayout5() {
    return panelGroupLayout5;
  }

  public void setPanelGroupLayout6(RichPanelGroupLayout panelGroupLayout6) {
    this.panelGroupLayout6 = panelGroupLayout6;
  }

  public RichPanelGroupLayout getPanelGroupLayout6() {
    return panelGroupLayout6;
  }

  public void setCb_ant1(RichCommandButton commandButton1) {
    this.cb_ant1 = commandButton1;
  }

  public RichCommandButton getCb_ant1() {
    return cb_ant1;
  }

  public void setCb_next2(RichCommandButton commandButton2) {
    this.cb_next2 = commandButton2;
  }

  public RichCommandButton getCb_next2() {
    return cb_next2;
  }

  public void setSpacer2(RichSpacer spacer2) {
    this.spacer2 = spacer2;
  }

  public RichSpacer getSpacer2() {
    return spacer2;
  }


    public void setPanelFormLayout2(RichPanelFormLayout panelFormLayout2) {
    this.panelFormLayout2 = panelFormLayout2;
  }

  public RichPanelFormLayout getPanelFormLayout2() {
    return panelFormLayout2;
  }

  public void setSist_referencia(RichSelectOneChoice selectOneChoice13) {
    this.sist_referencia = selectOneChoice13;
  }

  public RichSelectOneChoice getSist_referencia() {
    return sist_referencia;
  }

  public void setSelectItems13(UISelectItems selectItems13) {
    this.selectItems13 = selectItems13;
  }

  public UISelectItems getSelectItems13() {
    return selectItems13;
  }

  public void setGra_lat(RichInputText inputText2) {
    this.gra_lat = inputText2;
  }

  public RichInputText getGra_lat() {
    return gra_lat;
  }

  public void setMin_lat(RichInputText inputText12) {
    this.min_lat = inputText12;
  }

  public RichInputText getMin_lat() {
    return min_lat;
  }

  public void setSeg_lat(RichInputText inputText13) {
    this.seg_lat = inputText13;
  }

  public RichInputText getSeg_lat() {
    return seg_lat;
  }

  public void setGra_long(RichInputText inputText14) {
    this.gra_long = inputText14;
  }

  public RichInputText getGra_long() {
    return gra_long;
  }

  public void setMin_long(RichInputText inputText15) {
    this.min_long = inputText15;
  }

  public RichInputText getMin_long() {
    return min_long;
  }

  public void setSeg_long(RichInputText inputText16) {
    this.seg_long = inputText16;
  }

  public RichInputText getSeg_long() {
    return seg_long;
  }

  public void setText_lat(RichOutputText outputText1) {
    this.text_lat = outputText1;
  }

  public RichOutputText getText_lat() {
    return text_lat;
  }

  public void setText_long(RichOutputText outputText2) {
    this.text_long = outputText2;
  }

  public RichOutputText getText_long() {
    return text_long;
  }

  public void setT_altitud(RichInputText inputText17) {
    this.t_altitud = inputText17;
  }

  public RichInputText getT_altitud() {
    return t_altitud;
  }

  public void setT_descripcion(RichInputText inputText18) {
    this.t_descripcion = inputText18;
  }

  public RichInputText getT_descripcion() {
    return t_descripcion;
  }

  public void setListaSistRef(List listaSistRef) {
    this.listaSistRef = listaSistRef;
  }

  public List getListaSistRef() {
    return listaSistRef;
  }

  public void setT_nombreP(RichInputText inputText1) {
    this.t_nombreP = inputText1;
  }

  public RichInputText getT_nombreP() {
    return t_nombreP;
  }

  public void setSelectOneChoice2(RichSelectOneChoice selectOneChoice2) {
    this.selectOneChoice2 = selectOneChoice2;
  }

  public RichSelectOneChoice getSelectOneChoice2() {
    return selectOneChoice2;
  }

  public void setSelectItems2(UISelectItems selectItems2) {
    this.selectItems2 = selectItems2;
  }

  public UISelectItems getSelectItems2() {
    return selectItems2;
  }

  public void setSelectOneChoice6(RichSelectOneChoice selectOneChoice6) {
    this.selectOneChoice6 = selectOneChoice6;
  }

  public RichSelectOneChoice getSelectOneChoice6() {
    return selectOneChoice6;
  }

  public void setSelectItems6(UISelectItems selectItems6) {
    this.selectItems6 = selectItems6;
  }

  public UISelectItems getSelectItems6() {
    return selectItems6;
  }

  public void setP_resgistro_punto(RichPopup popup1) {
    this.p_resgistro_punto = popup1;
  }

  public RichPopup getP_resgistro_punto() {
    return p_resgistro_punto;
  }

  public void setD_registro_exitoso(RichDialog dialog1) {
    this.d_registro_exitoso = dialog1;
  }

  public RichDialog getD_registro_exitoso() {
    return d_registro_exitoso;
  }

  public void setPanelStretchLayout3(RichPanelStretchLayout panelStretchLayout3) {
    this.panelStretchLayout3 = panelStretchLayout3;
  }

  public RichPanelStretchLayout getPanelStretchLayout3() {
    return panelStretchLayout3;
  }

  public void setT_registro_punto(RichActiveOutputText activeOutputText1) {
    this.t_registro_punto = activeOutputText1;
  }

  public RichActiveOutputText getT_registro_punto() {
    return t_registro_punto;
  }

  public void setImage1(RichImage image1) {
    this.image1 = image1;
  }

  public RichImage getImage1() {
    return image1;
  }

  public void setPanelGroupLayout7(RichPanelGroupLayout panelGroupLayout7) {
    this.panelGroupLayout7 = panelGroupLayout7;
  }

  public RichPanelGroupLayout getPanelGroupLayout7() {
    return panelGroupLayout7;
  }

  public void setAceptar_punto(RichCommandButton commandButton1) {
    this.aceptar_punto = commandButton1;
  }

  public RichCommandButton getAceptar_punto() {
    return aceptar_punto;
  }

  public void setMunicipioSeleccionado(Divipola municipioSeleccionado) {
    this.municipioSeleccionado = municipioSeleccionado;
  }

  public Divipola getMunicipioSeleccionado() {
    return municipioSeleccionado;
  }

  public void setPuntoMononitoreo(PuntoMonitoreo puntoMononitoreo) {
    this.puntoMononitoreo = puntoMononitoreo;
  }

  public PuntoMonitoreo getPuntoMononitoreo() {
    return puntoMononitoreo;
  }

    public void setDepartamentoSeleccionado(Divipola departamentoSeleccionado) {
        this.departamentoSeleccionado = departamentoSeleccionado;
    }

    public Divipola getDepartamentoSeleccionado() {
        return departamentoSeleccionado;
    }

    public void setPanelGroupLayout8(RichPanelGroupLayout panelGroupLayout8) {
        this.panelGroupLayout8 = panelGroupLayout8;
    }

    public RichPanelGroupLayout getPanelGroupLayout8() {
        return panelGroupLayout8;
    }




  


   

   

    public void setPanelGroupLayout9(RichPanelGroupLayout panelGroupLayout9) {
        this.panelGroupLayout9 = panelGroupLayout9;
    }

    public RichPanelGroupLayout getPanelGroupLayout9() {
        return panelGroupLayout9;
    }

    public void setPanelFormLayout3(RichPanelFormLayout panelFormLayout3) {
        this.panelFormLayout3 = panelFormLayout3;
    }

    public RichPanelFormLayout getPanelFormLayout3() {
        return panelFormLayout3;
    }


    public void setPanelGroupLayout12(RichPanelGroupLayout panelGroupLayout12) {
        this.panelGroupLayout12 = panelGroupLayout12;
    }

    public RichPanelGroupLayout getPanelGroupLayout12() {
        return panelGroupLayout12;
    }

    public void setSpacer6(RichSpacer spacer6) {
        this.spacer6 = spacer6;
    }

    public RichSpacer getSpacer6() {
        return spacer6;
    }

    public void setOt_datos(RichActiveOutputText activeOutputText1) {
        this.ot_datos = activeOutputText1;
    }

    public RichActiveOutputText getOt_datos() {
        return ot_datos;
    }

    public void setOt_titulo(RichActiveOutputText activeOutputText2) {
        this.ot_titulo = activeOutputText2;
    }

    public RichActiveOutputText getOt_titulo() {
        return ot_titulo;
    }


    public void setOt_titulo3(RichActiveOutputText activeOutputText3) {
        this.ot_titulo3 = activeOutputText3;
    }

    public RichActiveOutputText getOt_titulo3() {
        return ot_titulo3;
    }

    public void setTramo(FnttTramo tramo) {
        this.tramo = tramo;
    }

    public FnttTramo getTramo() {
        return tramo;
    }


    public void setIt_fuente(RichInputText it_fuente) {
        this.it_fuente = it_fuente;
    }

    public RichInputText getIt_fuente() {
        return it_fuente;
    }

    public void setIt_tramo(RichInputText it_tramo) {
        this.it_tramo = it_tramo;
    }

    public RichInputText getIt_tramo() {
        return it_tramo;
    }

    public void setIt_subzona(RichInputText inputText1) {
        this.it_subzona = inputText1;
    }

    public RichInputText getIt_subzona() {
        return it_subzona;
    }

    public void setIt_zona(RichInputText inputText2) {
        this.it_zona = inputText2;
    }

    public RichInputText getIt_zona() {
        return it_zona;
    }

    public void setIt_area(RichInputText inputText3) {
        this.it_area = inputText3;
    }

    public RichInputText getIt_area() {
        return it_area;
    }

    public void setSpacer8(RichSpacer spacer8) {
        this.spacer8 = spacer8;
    }

    public RichSpacer getSpacer8() {
        return spacer8;
    }

    public void setSpacer3(RichSpacer spacer3) {
        this.spacer3 = spacer3;
    }

    public RichSpacer getSpacer3() {
        return spacer3;
    }


    public void setPanelGroupLayout13(RichPanelGroupLayout panelGroupLayout13) {
        this.panelGroupLayout13 = panelGroupLayout13;
    }

    public RichPanelGroupLayout getPanelGroupLayout13() {
        return panelGroupLayout13;
    }

    public void setPanelFormLayout6(RichPanelFormLayout panelFormLayout6) {
        this.panelFormLayout6 = panelFormLayout6;
    }

    public RichPanelFormLayout getPanelFormLayout6() {
        return panelFormLayout6;
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


    public void setActiveOutputText6(RichActiveOutputText activeOutputText6) {
        this.activeOutputText6 = activeOutputText6;
    }

    public RichActiveOutputText getActiveOutputText6() {
        return activeOutputText6;
    }


    public void setSpacer7(RichSpacer spacer7) {
        this.spacer7 = spacer7;
    }

    public RichSpacer getSpacer7() {
        return spacer7;
    }

    public void setOutputLabel1(RichOutputLabel outputLabel1) {
        this.outputLabel1 = outputLabel1;
    }

    public RichOutputLabel getOutputLabel1() {
        return outputLabel1;
    }

    public void setSpacer9(RichSpacer spacer9) {
        this.spacer9 = spacer9;
    }

    public RichSpacer getSpacer9() {
        return spacer9;
    }

    public void setPanelFormLayout4(RichPanelFormLayout panelFormLayout4) {
        this.panelFormLayout4 = panelFormLayout4;
    }

    public RichPanelFormLayout getPanelFormLayout4() {
        return panelFormLayout4;
    }

    public void setSpacer10(RichSpacer spacer10) {
        this.spacer10 = spacer10;
    }

    public RichSpacer getSpacer10() {
        return spacer10;
    }

    public void setOutputLabel2(RichOutputLabel outputLabel2) {
        this.outputLabel2 = outputLabel2;
    }

    public RichOutputLabel getOutputLabel2() {
        return outputLabel2;
    }

    public void setSpacer11(RichSpacer spacer11) {
        this.spacer11 = spacer11;
    }

    public RichSpacer getSpacer11() {
        return spacer11;
    }
}
