package co.gov.ideam.sirh.fuentes.view;


import co.gov.ideam.sirh.fuentes.model.FnttTrmAforo;
import co.gov.ideam.sirh.auditoria.model.Auditoria;
import co.gov.ideam.sirh.auditoria.view.AuditoriasDelegate;
import co.gov.ideam.sirh.calidad.model.Muestra;
import co.gov.ideam.sirh.calidad.model.PuntoMonitoreo;
import co.gov.ideam.sirh.calidad.view.CalidadDelegate;
import co.gov.ideam.sirh.fuentes.model.FnttFuenteTramoMunicipio;
import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.fuentes.model.FnttTramo;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.model.PartZonificListas;
import co.gov.ideam.sirh.parametros.view.ParametrosDelegate;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.usuarios.agua.model.Captacion;
import co.gov.ideam.sirh.usuarios.agua.view.UsuariosAguaDelegate;
import co.gov.ideam.sirh.util.Constantes;
import co.gov.ideam.sirh.util.ConstantesParametros;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;

import co.gov.ideam.sirh.view.util.SpecialTreeModel;
import co.gov.ideam.sirh.view.util.TreeNode;

import java.math.BigDecimal;

import java.text.DateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UISelectItems;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichMenu;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.data.RichTree;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanRadio;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichDecorativeBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.nav.RichCommandMenuItem;
import oracle.adf.view.rich.component.rich.output.RichImage;
import oracle.adf.view.rich.component.rich.output.RichOutputLabel;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
import oracle.adf.view.rich.component.rich.output.RichSpacer;

import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.adf.view.rich.event.DialogEvent;

import org.apache.myfaces.trinidad.component.UIXGroup;
import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.model.TreeModel;

public class DetalleTramoBean extends StandarBean{
    private RichForm formAF1;
    private RichDocument documentAF1;
    private RichPanelStretchLayout psl1;
    private RichPanelStretchLayout psl2;
    private RichPanelStretchLayout psl3;
    private RichPanelStretchLayout psl7;
    
    private RichPanelGroupLayout pgl1;
    private RichPanelGroupLayout pgl2;
    private RichPanelGroupLayout pgl3;
    private RichPanelGroupLayout pgl4;
    private RichPanelGroupLayout pgl14;
    private RichPanelGroupLayout pgl18;
    private RichPanelGroupLayout pgl19;
    
    private RichPanelFormLayout pfl1;
    private RichPanelFormLayout pfl2;
    
    private RichPanelBox pb1;
    private RichPanelBox pb2;
    private RichPanelBox pb_tramos;
    
    private RichPanelSplitter ps1;
    
    private RichPanelCollection pc1;
    
    private RichCommandButton cbAceptar;
    private RichCommandButton cbAceptarDi;
    private RichCommandButton cbBorrar;
    private RichCommandButton cb_si_borrar;
    private RichCommandButton cb_no_borrar;
            
    private RichSpacer s1;
    private RichSpacer s2;
    private RichSpacer s3;
    private RichSpacer s4;
    private RichSpacer s15;
    private RichSpacer s16;
    
    private RichSelectBooleanRadio sbr_si;
    private RichSelectBooleanRadio sbr_no;
    
    private RichSelectOneChoice selectOneChoice1;
    private UISelectItems selectItems1;
    private RichSelectOneChoice selectOneChoice2;
    private UISelectItems selectItems2;
    private RichSelectOneChoice selectOneChoice3;
    private UISelectItems selectItems3;
    private RichSelectOneChoice selectOneChoice7;
    private UISelectItems selectItems7;
    
    private RichInputText it_nombre;
    private RichInputText it_descripcion;

    private RichMenu menu;
    
    private RichCommandMenuItem cmiAddTramo;
    
    private RichTree t1;
    
    private RichCommandLink cl1;
    private RichCommandLink cl2;
    
    private RichOutputText ot1;
    
    private UIXGroup g1;
    
    private RichPopup popup_borrar;
    private RichPopup p_registro_exitoso;
    
    private RichDialog d_registro_exitoso;
    private RichDialog d_borrar;
    
    private RichOutputText ot_confirmacion_borrar;
    private RichOutputText ot8;
    private RichOutputText ot_borrar_1;
    private RichOutputText ot_borrar_2;
    
    private RichImage i1;
    
    private TreeModel tramosTreeModel;
    
    private RichPanelGroupLayout panelGroupLayout1;
    private RichPanelGroupLayout panelGroupLayout2;
    private RichDecorativeBox decorativeBox2;
    
    private RichDecorativeBox decorativeBox1;
    private RichPanelFormLayout panelFormLayout1;
    private RichInputText inputText1;
    private RichInputText inputText2;
    private RichInputText inputText3;
    private RichInputText inputText4;
    private RichInputText inputText5;
    private RichInputText inputText6;
    private RichInputText inputText7;
    private RichInputText inputText8;
    private RichOutputText outputText1;
    private RichOutputText outputText3;
    private RichPanelGroupLayout panelGroupLayout4;
    private RichSpacer spacer2;
    private RichPanelGroupLayout panelGroupLayout5;
    private RichSpacer spacer3;
    private RichPanelStretchLayout panelStretchLayout1;
    private RichInputText it_longitud;
    private RichInputText it_oferta;
    private RichInputText itObservacion;
    private RichSelectOneChoice selectOneChoice4;
    private UISelectItems selectItems4;
    private RichSelectOneChoice selectOneChoice5;
    private UISelectItems selectItems5;

    private FnttTramo tramo;
    private FnttTrmAforo aforo;
    
    private List listaArea;
    private List listaZona;
    private List listaSubzona;
    private List tiposFlujo;;
    private List sistemasReferencia;
    private List listaDepartamentos;
    private List listaMunicipios;
    private List municipiosRelacionados;
    private Divipola municipioRelacionado;
    
    private RichCommandLink commandLink1;
    private RichSpacer spacer4;
    private RichCommandLink commandLink2;
    private RichPanelGroupLayout panelGroupLayout6;
    private RichInputText inputText11;
    private RichInputText inputText21;
    private RichInputText inputText31;
    private RichInputText inputText41;
    private RichPanelGroupLayout panelGroupLayout8;
    private RichDecorativeBox decorativeBox3;
    private RichPanelFormLayout panelFormLayout3;
    private RichSelectOneChoice selectOneChoice6;
    private UISelectItems selectItems6;
    private RichSelectOneChoice selectOneChoice8;
    private UISelectItems selectItems8;
    private RichCommandButton commandButton1;
    private RichPanelCollection panelCollection1;
    private RichTable t_relacion;
    private RichMenu menu1;
    private RichCommandMenuItem commandMenuItem1;
    private RichPopup popup1;
    private RichDialog dialog1;
    private RichOutputText outputText5;
    private RichPanelGroupLayout panelGroupLayout3;
    private RichPopup popup11;
    private RichDialog dialog11;
    private RichOutputText outputText7;
    private RichSpacer spacer6;
    private RichOutputText outputText6;
    private RichPanelGroupLayout panelGroupLayout9;
    private RichPanelGroupLayout panelGroupLayout10;
    private RichPanelFormLayout panelFormLayout31;

    private RichPanelGroupLayout panelGroupLayout11;
    private RichPanelGroupLayout panelGroupLayout12;
    private RichPanelFormLayout panelFormLayout4;
    
    //CDONCEL    
    private RichPopup aforo_registro_exitoso;
    private RichDialog d_aforo_registro_exitoso;
    private RichDecorativeBox decorativeBoxAforo;
    private RichPanelGroupLayout panelGroupLayoutAforo;
    private RichSpacer spacerAforo;
    private RichOutputText outputTextAforo;
    private RichPanelGroupLayout panelGroupLayout2Aforo;    
    private RichPanelFormLayout panelFormLayoutAforo;
    private RichSpacer spacer2Aforo;
    private RichOutputLabel outputLabelAforo;//PTO_LATITUD
    private RichInputText inputTextAforo;//TRM_GRADOS
    private RichInputText inputText2Aforo; //TRM_MINUTOS
    private RichInputText inputText3Aforo;// TRM_SEGUNDOS
    private RichPanelFormLayout panelFormLayout2Aforo;
    private RichSpacer spacer3Aforo;
    private RichOutputLabel outputLabel2Aforo;//PTO_LONGITUD
    private RichInputText inputText4Aforo;// TRM_GRADOS
    private RichInputText inputText5Aforo; //TRM_MINUTOS
    private RichInputText inputText6Aforo;// TRM_SEGUNDOS
    private RichInputDate fechaAforo;
    private RichInputText inputText7Aforo;// CAUDAL
    private Boolean flagAforo = false;    
    private RichPanelFormLayout panelFormLayout3Aforo;
    private RichSpacer spacer4Aforo;
    private List<FnttTrmAforo> lsAforos;
    private RichPanelFormLayout panelFormLayout4Aforo;
    private RichSpacer spacer5Aforo;
    private RichTable tableAforo;
    

  private RichPopup aforo_borrardo_exitoso;

  private RichDialog d_aforo_borrado_exitoso;
  private RichPanelGroupLayout pgaforoPop;
  private RichPanelGroupLayout pgaforoPop2;
  private RichCommandButton cbAceptarAforo;
  private RichCommandButton cbAceptarAforo2;
  private RichPanelStretchLayout psAforo;
  private RichPanelStretchLayout psAforo2;
  private RichOutputText otAforoPop;
  private RichOutputText otAforoPop2;
  private RichImage imAforoPop;
  private RichImage imAforoPop2;
  private String aceptarActionPop;
  private String aceptarActionPop2;
    //FIN CDONCEL
    
    private List listaPuntosMonitoreo;
    private PuntoMonitoreo puntoMonitoreo;
    private TreeModel puntosMonitoreoTreeModel;
    private RichPanelBox panelBox1;
    private RichPanelGroupLayout panelGroupLayout13;
    private RichPanelCollection panelCollection2;
    private RichTree tree1;
    private RichCommandLink clink;
    private RichSpacer spacer7;
    private RichMenu menu2;
    private RichCommandMenuItem cmi_adicionar;
    private RichCommandMenuItem cmi_conflicto;
  	private RichCommandMenuItem cmi_objetivo_calidad;
    private RichCommandMenuItem cmi_metas;
    private String accionAdicionar;
    private RichSpacer spacer8;
    private RichPanelFormLayout panelFormLayout5;
    private RichSpacer spacer5;
    private RichOutputLabel outputLabel1;
    private RichSpacer spacer9;
    private RichOutputLabel outputLabel2;
    private RichPanelFormLayout panelFormLayout6;
    private RichSpacer spacer10;
    private RichOutputLabel outputLabel3;
    private RichPanelFormLayout panelFormLayout7;
    private RichSpacer spacer11;
    private RichOutputLabel outputLabel4;
    private RichPanelFormLayout panelFormLayout2;
    private RichSpacer spacer1;
    private RichOutputLabel outputLabel5;
    
    private RichInputText it_grad_long_pi;
    private RichInputText it_minutos_long_pi;
    private RichInputText it_segundos_long_pi;
    
    private RichInputText it_grados_long_pf;
    private RichInputText it_minutos_long_pf;
    private RichInputText it_segundos_long_pf;

    
    
    private RichPanelFormLayout panelFormLayout8;
    private RichSpacer spacer12;
    private RichOutputLabel outputLabel6;
    private RichPanelFormLayout panelFormLayout9;
    private RichPanelFormLayout panelFormLayout10;
    private RichPanelFormLayout panelFormLayout11;
    private RichPanelFormLayout panelFormLayout12;

    public DetalleTramoBean(){
        FacesUtils.setActiveBean("detalleTramoBean", "Detalle Tramo",
                                 FuenteDelegate.class);        
        FacesUtils.removeManagedBeanFromSession("tramosBean");
        FacesUtils.removeManagedBeanFromSession("detalleFuenteBean");
        FacesUtils.removeManagedBeanFromSession("FuentesBean");
        this.load();
    }
    
    public void load(){
        try{
            FuenteDelegate fd = FuenteDelegate.getInstance();
            ParametrosDelegate pd = ParametrosDelegate.getInstance(); 
            
            Object obj = FacesUtils.getFromSession("tramoSeleccionado");
            
            
            if(obj instanceof FnttTramo){
                this.tramo = (FnttTramo)obj;
                this.tramo = fd.getTramo(this.tramo.getId());    
            }else{                
                Long codigo = (Long)FacesUtils.getFromSession("tramoSeleccionado");                                            
                this.tramo = fd.getTramo(codigo);                
            }
            //carga el tramo con sus municipios.  
            //aforo = new FnttTrmAforo();
            FnttTramo trm = fd.getTramoWithMunicipios(this.tramo.getId());
            if(trm != null){//si el tramo tiene municipios relacionados
                this.tramo = trm;
                
                if(this.tramo.getFnttFuenteTramoMunicipioList()!=null && 
                        this.tramo.getFnttFuenteTramoMunicipioList().size()>0){
                    this.municipiosRelacionados = new ArrayList();
                    for(FnttFuenteTramoMunicipio ftm : this.tramo.getFnttFuenteTramoMunicipioList()){
                        Divipola mun = (Divipola)pd.getDivipolaById(new Long(ftm.getFntFuenteTramoMunicipioPK().getIdMunicipio()));
                        this.municipiosRelacionados.add(mun);
                    }
                }
                this.tramo.getFnttFuenteTramoMunicipioList().clear();//borra los municipios relacionados al tramo.
            }
            
            UsuarioConectadoTO usuarioConectado = 
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
            
            this.listaDepartamentos = this.cargarDivipola(null);
            this.listaArea = this.cargarZonificacion(null);
            this.listaZona = new ArrayList();
            this.listaSubzona = new ArrayList();
            
            if(this.tramo.getIdArea()!=null){
                this.listaZona = (List<PartZonificListas>)this.cargarZonificacion(this.tramo.getIdArea().getId());
            }
            if(this.tramo.getIdZona()!=null){
                this.listaSubzona = (List<PartZonificListas>)this.cargarZonificacion(this.tramo.getIdZona().getId());
            }            
            
            this.tiposFlujo = this.cargarParametro(ConstantesParametros.CATEGORIA_TIPO_FLUJO);
            this.sistemasReferencia = this.cargarParametro(ConstantesParametros.CATEGORIA_SISTEMA_REFERENCIA);
           
           
           
           
            
            //Construir el arbol.
            List listaNodos = new ArrayList(); 
            CalidadDelegate cald = CalidadDelegate.getInstance();
            
            listaPuntosMonitoreo  = cald.getPuntosMonitoreo(this.tramo);
                
            this.tramo.setListaPuntosMonitoreo(listaPuntosMonitoreo);
            
            TreeNode nodoPuntos = new TreeNode(getText("MODULO_PUNTOS"), 
                                               "PuntosMonitoreo");
            nodoPuntos.setData("PuntosMonitoreo");
            listaNodos.add(nodoPuntos);
            
            
            if (listaPuntosMonitoreo!=null) {
                Iterator it = listaPuntosMonitoreo.iterator();
                while(it.hasNext()){
                    PuntoMonitoreo puntoM = (PuntoMonitoreo)it.next();  
                    
                    TreeNode nodoPunto = new TreeNode( puntoM.getNombre().toString().toUpperCase(),
                                                       "detallepunto", 
                                                       true,
                                                       false);                   
                    nodoPunto.setData(puntoM);            
                    nodoPuntos.getChildren().add(nodoPunto);   
                }
            }  else{
                    TreeNode nodoPunto = new TreeNode(getText("NO_HAY_REGISTROS"), 
                                                          "punto",
                                                          false,
                                                          true);                                           
                    nodoPuntos.getChildren().add(nodoPunto);
                } 
            puntosMonitoreoTreeModel = new SpecialTreeModel(listaNodos, "children");
               
          
        }catch(IdeamException e){
            showMessage(e);
        }
    }
    
    
    
    public void clink_actionListener(ActionEvent actionEvent) {
            String nombreParametro = 
                (String)actionEvent.getComponent().getAttributes().get("nombreParametro");                
            Object data = 
                actionEvent.getComponent().getAttributes().get("valorParametro");        
            if(nombreParametro!=null && data != null){
                FacesUtils.setInSession(nombreParametro, data);
            }
        }

    public void selectOneChoice1_valueChangeListener(ValueChangeEvent event)
            throws IdeamException{
        
        Object area = event.getNewValue();
        this.listaZona = new ArrayList();
        this.listaSubzona = new ArrayList();
        try{
            if(area!=null)
                this.listaZona = this.cargarZonificacion(((PartZonificListas)area).getId());
        }catch(IdeamException e){            
            showMessage(e);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(selectOneChoice2);
        AdfFacesContext.getCurrentInstance().addPartialTarget(selectOneChoice3);
    }
    
    public void selectOneChoice2_valueChangeListener(ValueChangeEvent event)
            throws IdeamException{
        
        Object zona = event.getNewValue();
        this.listaSubzona = new ArrayList();
        try{
            if(zona!=null){
                this.listaSubzona = this.cargarZonificacion(((PartZonificListas)zona).getId());
            }
        }catch(IdeamException e){            
            showMessage(e);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(selectOneChoice3);
    }   
    
    public void cbAceptar_actionListener(ActionEvent actionEvent) {
            boolean continuar = true;
            
        UsuarioConectadoTO usuarioConectado = 
            (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
        
        Autoridades autoridadAmbiental = usuarioConectado.getUsuario().getAutoridadAmbiental();
        if(autoridadAmbiental==null ||
                autoridadAmbiental.getId().intValue() == Constantes.IDEAM){
            showMessage(getText("NO_EXISTE_AUTORIDAD_AMBIENTAL"),
                        FacesMessage.SEVERITY_ERROR); 
            continuar = false;
        }
            
        if(this.it_nombre.getValue()==null || 
           this.it_nombre.getValue().toString().length()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.it_nombre);       
            continuar = false;
        } 
        
        //cDoncel
        if(this.itObservacion.getValue()==null || this.itObservacion.getValue().toString().equals("")){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.itObservacion);       
            continuar = false;
          }
    
        if(this.it_oferta.getValue()==null || 
           this.it_oferta.getValue().toString().length()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.it_oferta);       
            continuar = false;
        } 
        if(this.selectOneChoice4.getValue()==null ){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.selectOneChoice4);       
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
        
        if(this.selectOneChoice3.getValue()==null){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.selectOneChoice3);       
            continuar = false;
        }
        
        if(!this.tramo.getIdFuente().isEsSubterranea()){
            if(this.it_longitud.getValue()==null || 
               this.it_longitud.getValue().toString().length()==0){
                showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.it_longitud);       
                continuar = false;
            }
            
            if(this.selectOneChoice5.getValue()==null ){
                showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.selectOneChoice5);       
                continuar = false;
            }
            
            if(this.inputText1.getValue()==null || 
               this.inputText1.getValue().toString().length()==0){
                showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.inputText1);       
                continuar = false;
            }   
            
            if(this.inputText2.getValue()==null || 
               this.inputText2.getValue().toString().length()==0){
                showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.inputText2);       
                continuar = false;
            }  
            
            if(this.inputText3.getValue()==null || 
               this.inputText3.getValue().toString().length()==0){
                showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.inputText3);       
                continuar = false;
            }  
            
            if(this.inputText4.getValue()==null || 
               this.inputText4.getValue().toString().length()==0){
                showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.inputText4);       
                continuar = false;
            }  
            if(this.inputText5.getValue()==null || 
               this.inputText5.getValue().toString().length()==0){
                showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.inputText5);       
                continuar = false;
            }   
            
            if(this.inputText6.getValue()==null || 
               this.inputText6.getValue().toString().length()==0){
                showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.inputText6);       
                continuar = false;
            }  
            
            if(this.inputText7.getValue()==null || 
               this.inputText7.getValue().toString().length()==0){
                showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.inputText7);       
                continuar = false;
            }  
            
            if(this.inputText8.getValue()==null || 
               this.inputText8.getValue().toString().length()==0){
                showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.inputText8);       
                continuar = false;
            }  
        }
        if(continuar){   
            this.save(); 
        }          
    }
    
    public void save(){
        try{
            
            this.tramo.setNombre(this.it_nombre.getValue().toString().toUpperCase());
            if(this.it_descripcion!=null && this.it_descripcion.getValue()!=null){
                this.tramo.setDescripcion(this.it_descripcion.getValue().toString());
            }
            
            this.tramo.setLongitud(new BigDecimal(Double.parseDouble(this.it_longitud.getValue().toString())));
            this.tramo.setOfertaHidricaTotal(new BigDecimal(Double.parseDouble(this.it_oferta.getValue().toString())));
            this.tramo.setTipoFlujo((Lista)this.selectOneChoice4.getValue());
            this.tramo.setIdTipoFlujo(tramo.getTipoFlujo().getCodigo());//Pilar
            this.tramo.setSistemaReferencia((Lista)this.selectOneChoice5.getValue());
            this.tramo.setIdArea((PartZonificListas)this.selectOneChoice1.getValue());
            this.tramo.setIdZona((PartZonificListas)this.selectOneChoice2.getValue());
            this.tramo.setIdSubzona((PartZonificListas)this.selectOneChoice3.getValue());

            this.tramo.setGradosPi(Integer.parseInt(this.inputText1.getValue().toString()));
            this.tramo.setMinutosPi(Integer.parseInt(this.inputText2.getValue().toString()));
            this.tramo.setSegundosPi(new BigDecimal(Double.parseDouble(this.inputText3.getValue().toString())));
            this.tramo.setAltitudPi(new BigDecimal(Double.parseDouble(this.inputText4.getValue().toString())));
            
            this.tramo.setGradosPf(Integer.parseInt(this.inputText5.getValue().toString()));
            this.tramo.setMinutosPf(Integer.parseInt(this.inputText6.getValue().toString()));
            this.tramo.setSegundosPf(new BigDecimal(Double.parseDouble(this.inputText7.getValue().toString())));
            this.tramo.setAltitudPf(new BigDecimal(Double.parseDouble(this.inputText8.getValue().toString())));

             this.tramo.setGradosLongPi(Integer.parseInt(this.it_grad_long_pi.getValue().toString()));
             this.tramo.setMinutosLongPi(Integer.parseInt(this.it_minutos_long_pi.getValue().toString()));
             this.tramo.setSegundosLongPi(new BigDecimal(Double.parseDouble(this.it_segundos_long_pi.getValue().toString())));
       
             this.tramo.setGradosLongPf(Integer.parseInt(this.it_grados_long_pf.getValue().toString()));
             this.tramo.setMinutosLongPf(Integer.parseInt(this.it_minutos_long_pf.getValue().toString()));
             this.tramo.setSegundosLongPf(new BigDecimal(Double.parseDouble(this.it_segundos_long_pf.getValue().toString())));
            
            
            
            //this.tramo.getFntFuenteTramoMunicipioList().clear();//limpia la lista de municipios relacionados
            //adiciona los municipios.
            if(this.municipiosRelacionados!=null && this.municipiosRelacionados.size()>0){
                
                List <FnttFuenteTramoMunicipio> relacionados = new ArrayList<FnttFuenteTramoMunicipio>();
                
                Iterator it = this.municipiosRelacionados.iterator();
                while(it.hasNext()){
                    Divipola pola = (Divipola) it.next();
                    FnttFuenteTramoMunicipio relacionado = 
                        new FnttFuenteTramoMunicipio(this.tramo.getIdFuente().getId(), 
                                                     this.tramo.getId(), 
                                                     pola.getId().intValue(),
                                                     pola.getPadreId().intValue());
                    relacionados.add(relacionado);
                }
                this.tramo.setFnttFuenteTramoMunicipioList(relacionados);
            }
            
            FuenteDelegate fd = FuenteDelegate.getInstance();
            
            //Valida si ya existe un tramo con ese nombre en la fuente, 
            //creado por la misma autoridad.
            
            UsuarioConectadoTO usuarioConectado = 
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
            
            FnttTramo existe = fd.getTramo(this.tramo.getNombre(),this.tramo.getIdFuente(), 
                                           usuarioConectado.getUsuario().getAutoridadAmbiental());
            if(existe!=null && existe.getId().longValue() !=
                               this.tramo.getId().longValue() ){
                showMessage(getText("TRM_EXISTE"), FacesMessage.SEVERITY_ERROR);
                return;                       
            }
            
            fd.updateTramo(this.tramo);
            
            try{
                
                /** 
                 * Estas variables son necesarias para llenar los datos intrinsecos de la auditoria.
                 */
                // UsuarioConectadoTO usuarioConectado = (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
                
                Auditoria auditoria = new Auditoria();
                auditoria.setIdUsuario(usuarioConectado.getUsuario().getCodigo());
                auditoria.setOperacion("MODIFICAR");
                auditoria.setObjeto("TRAMOS");
                auditoria.setMetodo(Thread.currentThread().getStackTrace()[1].getMethodName());
                auditoria.setClase(this.getClass().getName());
                //Objeto Afectado (usuario modificado, eliminado o agregado) 
                auditoria.setIdObjeto(this.tramo.getId());
               
                AuditoriasDelegate audDelegate = AuditoriasDelegate.getInstance();
                audDelegate.setAuditoria(auditoria);
                
            }catch(Exception e){
                System.out.println("No se pudo Almacenar la auditoria: ---------------------------------- ");
            System.out.println(e.getMessage()+"------------------------------------------------------ ");
            }

            
            
            
            
            
            
            
            
            showPopup(p_registro_exitoso, true);
        }catch(IdeamException e){
            showMessage(e);
        }
    }   
    
    
    public void cbBorrar_actionListener(ActionEvent actionEvent) {
        String nombre = this.tramo.getNombre();
        
        String params[] = {nombre};
        String texto = getText("borrar_tramo", params);
        ot_borrar_1.setValue(texto);
        ot_borrar_2.setValue(nombre);
        
        try{
            CalidadDelegate cld = CalidadDelegate.getInstance();
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
            
            List<PuntoMonitoreo> pto = cld.getPuntosMonitoreo(this.tramo);
                
            List<Captacion> captacionesTramo = 
                uad.getCaptacionByTramo(this.tramo.getId());
                
            List<Captacion> vertimientosTramo = 
                  uad.getVertimientoByTramo(this.tramo.getId());
            
            UsuarioConectadoTO usuarioConectado = 
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
            
            Autoridades autoridadAmbiental = usuarioConectado.getUsuario().getAutoridadAmbiental();
            if(autoridadAmbiental==null ||
                    autoridadAmbiental.getId().intValue() == Constantes.IDEAM){
                showMessage(getText("NO_EXISTE_AUTORIDAD_AMBIENTAL"),
                            FacesMessage.SEVERITY_ERROR); 
            }else if (pto!=null && pto.size()>0){
                showMessage(getText("TRM_PTOS_RELACIONADOS"));
            }else if(captacionesTramo!=null && !captacionesTramo.isEmpty()){
                showMessage(getText("TRM_SI_CAPTACIONES"));
            }else if(vertimientosTramo!=null && !vertimientosTramo.isEmpty()){
                showMessage(getText("TRM_SI_VERTIMIENTOS"));
            }else{
                AdfFacesContext.getCurrentInstance().addPartialTarget(popup_borrar);
                showPopup(popup_borrar, true);
            }  
        }catch(IdeamException e){
            showMessage(e.getMessage());
        }    
    }
    
    public void cb_no_borrar_actionListener(ActionEvent actionEvent) {
        showPopup(popup_borrar, false);
    }
    
    public void cb_si_borrar_actionListener(ActionEvent actionEvent) {
        try{
            FuenteDelegate fd = FuenteDelegate.getInstance();
            fd.deleteTramo(this.tramo);
            
            try{
                
                /** 
                 * Estas variables son necesarias para llenar los datos intrinsecos de la auditoria.
                 */
                 UsuarioConectadoTO usuarioConectado = (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
                
                Auditoria auditoria = new Auditoria();
                auditoria.setIdUsuario(usuarioConectado.getUsuario().getCodigo());
                auditoria.setOperacion("BORRAR");
                auditoria.setObjeto("TRAMOS");
                auditoria.setMetodo(Thread.currentThread().getStackTrace()[1].getMethodName());
                auditoria.setClase(this.getClass().getName());
                //Objeto Afectado (usuario modificado, eliminado o agregado) 
                auditoria.setIdObjeto(this.tramo.getId());
               
                AuditoriasDelegate audDelegate = AuditoriasDelegate.getInstance();
                audDelegate.setAuditoria(auditoria);
                
            }catch(Exception e){
                System.out.println("No se pudo Almacenar la auditoria: ---------------------------------- ");
                System.out.println(e.getMessage()+"------------------------------------------------------ ");
            }
            
            
            
            
            
            
            
            showMessage(getText("tramo_eliminado"));
        }catch(IdeamException e){
            showMessage(e);
        }
    }
    
    //CDONCEL
    public void preparaAforo(){
      try{
        aforo = new FnttTrmAforo();
        setFlagAforo(true);
        FuenteDelegate fd = FuenteDelegate.getInstance();
        lsAforos = new ArrayList<FnttTrmAforo>();
        lsAforos.addAll(fd.findAforos(this.tramo));
       // System.out.println("Size lsaforos: " + lsAforos.size());
        //this.load();
        
        //return null;
        }catch(Exception e){
            System.err.println(e.getMessage());
            //return null;
        }}
    
    public void finalizarAforo(){
      setFlagAforo(false);
      }
        
  public void adicionarAforo(){
      try{
          this.aforo.setGradosLat(Integer.parseInt(this.inputTextAforo.getValue().toString()));
          this.aforo.setMinutosLat(Integer.parseInt(this.inputText2Aforo.getValue().toString()));
          this.aforo.setSegundosLat(new BigDecimal(this.inputText3Aforo.getValue().toString()));  
          this.aforo.setGradosLong(Integer.parseInt(this.inputText4Aforo.getValue().toString()));
          this.aforo.setMinutosLong(Integer.parseInt(this.inputText5Aforo.getValue().toString()));
          this.aforo.setSegundosLong(new BigDecimal(this.inputText6Aforo.getValue().toString()));
          this.aforo.setCaudal(Double.parseDouble(this.inputText7Aforo.getValue().toString()));
          this.aforo.setTramoId(this.tramo);
        //System.out.println("++++++++++++++++++++ Aforo +++++++++++++++++++++++++++");
        //System.out.println(aforo.getGradosLat() + " - " + aforo.getGradosLong() + " - " + aforo.getMinutosLat() + " - " + aforo.getMinutosLong());
    
    FuenteDelegate fd = FuenteDelegate.getInstance();
    fd.updateAforo(this.aforo);
    //this.preparaAforo();    
    this.finalizarAforo();   
    showPopup(aforo_registro_exitoso, true);
  }catch(Exception e){
      System.err.println(e.getMessage());
      //return null;
  }
      
      }
  public void borrarAforo(){
      try{
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest req = (HttpServletRequest)context.getExternalContext().getRequest();
        String param = req.getParameter("caudalid");
        System.out.println("*****************++pARAM: " + param);
            FuenteDelegate fd = FuenteDelegate.getInstance();
            fd.borrarAforo(param);
            this.finalizarAforo(); 
            showPopup(aforo_borrardo_exitoso, true);
          }catch(Exception e){
              System.err.println(e.getMessage());
              //return null;
          }
              
              }
  
  public void cb_aceptar_actionListener(ActionEvent actionEvent) {
      this.setAceptarActionPop("detalleTramo");
  }
  
  //FIN CDONCEL
  
    
    public void cmi_adicionar_puntoMonitoreo_actionListener(ActionEvent actionEvent) {
        FacesUtils.setInSession("tramoSeleccionado", this.tramo);
        accionAdicionar = "adicionarpunto";
    }

  public void cmi_conflicto_puntoMonitoreo_actionListener(ActionEvent actionEvent) {
      FacesUtils.removeFromSession("planSeleccionado");
      FacesUtils.setInSession("tramoSeleccionado", this.tramo);
      accionAdicionar = "adicionarConflictoTramo";
  }
      
  public void cmi_objetivo_calidad_actionListener(ActionEvent actionEvent) {
      FacesUtils.removeFromSession("planSeleccionado");
      FacesUtils.setInSession("tramoSeleccionado", this.tramo);
      accionAdicionar = "adicionarObjetivoCalidad";
  }
  
  public void cmi_metas_actionListener(ActionEvent actionEvent) {
      FacesUtils.removeFromSession("planSeleccionado");
      FacesUtils.setInSession("tramoSeleccionado", this.tramo);
      accionAdicionar = "metasDescontaminacion";
  }
  
  public void setFormAF1(RichForm formAF1) {
        this.formAF1 = formAF1;
    }

    public RichForm getFormAF1() {
        return formAF1;
    }

    public void setDocumentAF1(RichDocument documentAF1) {
        this.documentAF1 = documentAF1;
    }

    public RichDocument getDocumentAF1() {
        return documentAF1;
    }

    public void setPsl1(RichPanelStretchLayout psl1) {
        this.psl1 = psl1;
    }

    public RichPanelStretchLayout getPsl1() {
        return psl1;
    }

    public void setPsl2(RichPanelStretchLayout psl2) {
        this.psl2 = psl2;
    }

    public RichPanelStretchLayout getPsl2() {
        return psl2;
    }

    public void setPsl3(RichPanelStretchLayout psl3) {
        this.psl3 = psl3;
    }

    public RichPanelStretchLayout getPsl3() {
        return psl3;
    }

    public void setPgl1(RichPanelGroupLayout pgl1) {
        this.pgl1 = pgl1;
    }

    public RichPanelGroupLayout getPgl1() {
        return pgl1;
    }

    public void setPgl2(RichPanelGroupLayout pgl2) {
        this.pgl2 = pgl2;
    }

    public RichPanelGroupLayout getPgl2() {
        return pgl2;
    }

    public void setPgl3(RichPanelGroupLayout pgl3) {
        this.pgl3 = pgl3;
    }

    public RichPanelGroupLayout getPgl3() {
        return pgl3;
    }

    public void setPfl1(RichPanelFormLayout pfl1) {
        this.pfl1 = pfl1;
    }

    public RichPanelFormLayout getPfl1() {
        return pfl1;
    }

    public void setPfl2(RichPanelFormLayout pfl2) {
        this.pfl2 = pfl2;
    }

    public RichPanelFormLayout getPfl2() {
        return pfl2;
    }

    public void setPb1(RichPanelBox pb1) {
        this.pb1 = pb1;
    }

    public RichPanelBox getPb1() {
        return pb1;
    }

    public void setPb2(RichPanelBox pb2) {
        this.pb2 = pb2;
    }

    public RichPanelBox getPb2() {
        return pb2;
    }

    public void setPs1(RichPanelSplitter ps1) {
        this.ps1 = ps1;
    }

    public RichPanelSplitter getPs1() {
        return ps1;
    }

    public void setCbAceptar(RichCommandButton cbAceptar) {
        this.cbAceptar = cbAceptar;
    }

    public RichCommandButton getCbAceptar() {
        return cbAceptar;
    }

    public void setCbBorrar(RichCommandButton cbBorrar) {
        this.cbBorrar = cbBorrar;
    }

    public RichCommandButton getCbBorrar() {
        return cbBorrar;
    }

    public void setS1(RichSpacer s1) {
        this.s1 = s1;
    }

    public RichSpacer getS1() {
        return s1;
    }

    public void setS2(RichSpacer s2) {
        this.s2 = s2;
    }

    public RichSpacer getS2() {
        return s2;
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

    public void setSelectOneChoice7(RichSelectOneChoice selectOneChoice7) {
        this.selectOneChoice7 = selectOneChoice7;
    }

    public RichSelectOneChoice getSelectOneChoice7() {
        return selectOneChoice7;
    }

    public void setSelectItems7(UISelectItems selectItems7) {
        this.selectItems7 = selectItems7;
    }

    public UISelectItems getSelectItems7() {
        return selectItems7;
    }

    public void setIt_nombre(RichInputText it_nombre) {
        this.it_nombre = it_nombre;
    }

    public RichInputText getIt_nombre() {
        return it_nombre;
    }

    public void setIt_descripcion(RichInputText it_descripcion) {
        this.it_descripcion = it_descripcion;
    }

    public RichInputText getIt_descripcion() {
        return it_descripcion;
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


    public void setSbr_si(RichSelectBooleanRadio sbr_si) {
        this.sbr_si = sbr_si;
    }

    public RichSelectBooleanRadio getSbr_si() {
        return sbr_si;
    }

    public void setSbr_no(RichSelectBooleanRadio sbr_no) {
        this.sbr_no = sbr_no;
    }

    public RichSelectBooleanRadio getSbr_no() {
        return sbr_no;
    }

    public void setPb_tramos(RichPanelBox pb_tramos) {
        this.pb_tramos = pb_tramos;
    }

    public RichPanelBox getPb_tramos() {
        return pb_tramos;
    }

    public void setPc1(RichPanelCollection pc1) {
        this.pc1 = pc1;
    }

    public RichPanelCollection getPc1() {
        return pc1;
    }

    public void setCmiAddTramo(RichCommandMenuItem cmiAddTramo) {
        this.cmiAddTramo = cmiAddTramo;
    }

    public RichCommandMenuItem getCmiAddTramo() {
        return cmiAddTramo;
    }

    public void setT1(RichTree t1) {
        this.t1 = t1;
    }

    public RichTree getT1() {
        return t1;
    }

    public void setCl1(RichCommandLink cl1) {
        this.cl1 = cl1;
    }

    public RichCommandLink getCl1() {
        return cl1;
    }

    public void setPgl4(RichPanelGroupLayout pgl4) {
        this.pgl4 = pgl4;
    }

    public RichPanelGroupLayout getPgl4() {
        return pgl4;
    }

    public void setS3(RichSpacer s3) {
        this.s3 = s3;
    }

    public RichSpacer getS3() {
        return s3;
    }

    public void setS4(RichSpacer s4) {
        this.s4 = s4;
    }

    public RichSpacer getS4() {
        return s4;
    }

    public void setOt1(RichOutputText ot1) {
        this.ot1 = ot1;
    }

    public RichOutputText getOt1() {
        return ot1;
    }

    public void setMenu(RichMenu menu) {
        this.menu = menu;
    }

    public RichMenu getMenu() {
        return menu;
    }

    public void setCl2(RichCommandLink cl2) {
        this.cl2 = cl2;
    }

    public RichCommandLink getCl2() {
        return cl2;
    }

    public void setPsl7(RichPanelStretchLayout psl7) {
        this.psl7 = psl7;
    }

    public RichPanelStretchLayout getPsl7() {
        return psl7;
    }

    public void setPgl14(RichPanelGroupLayout pgl14) {
        this.pgl14 = pgl14;
    }

    public RichPanelGroupLayout getPgl14() {
        return pgl14;
    }

    public void setCbAceptarDi(RichCommandButton cbAceptarDi) {
        this.cbAceptarDi = cbAceptarDi;
    }

    public RichCommandButton getCbAceptarDi() {
        return cbAceptarDi;
    }

    public void setG1(UIXGroup g1) {
        this.g1 = g1;
    }

    public UIXGroup getG1() {
        return g1;
    }

    public void setPopup_borrar(RichPopup popup_borrar) {
        this.popup_borrar = popup_borrar;
    }

    public RichPopup getPopup_borrar() {
        return popup_borrar;
    }

    public void setP_registro_exitoso(RichPopup p_registro_exitoso) {
        this.p_registro_exitoso = p_registro_exitoso;
    }

    public RichPopup getP_registro_exitoso() {
        return p_registro_exitoso;
    }

    public void setD_registro_exitoso(RichDialog d_registro_exitoso) {
        this.d_registro_exitoso = d_registro_exitoso;
    }

    public RichDialog getD_registro_exitoso() {
        return d_registro_exitoso;
    }

    public void setD_borrar(RichDialog d_borrar) {
        this.d_borrar = d_borrar;
    }

    public RichDialog getD_borrar() {
        return d_borrar;
    }

    public void setOt_confirmacion_borrar(RichOutputText ot_confirmacion_borrar) {
        this.ot_confirmacion_borrar = ot_confirmacion_borrar;
    }

    public RichOutputText getOt_confirmacion_borrar() {
        return ot_confirmacion_borrar;
    }

    public void setOt8(RichOutputText ot8) {
        this.ot8 = ot8;
    }

    public RichOutputText getOt8() {
        return ot8;
    }

    public void setI1(RichImage i1) {
        this.i1 = i1;
    }

    public RichImage getI1() {
        return i1;
    }

    public void setPgl18(RichPanelGroupLayout pgl18) {
        this.pgl18 = pgl18;
    }

    public RichPanelGroupLayout getPgl18() {
        return pgl18;
    }

    public void setPgl19(RichPanelGroupLayout pgl19) {
        this.pgl19 = pgl19;
    }

    public RichPanelGroupLayout getPgl19() {
        return pgl19;
    }

    public void setCb_si_borrar(RichCommandButton cb_si_borrar) {
        this.cb_si_borrar = cb_si_borrar;
    }

    public RichCommandButton getCb_si_borrar() {
        return cb_si_borrar;
    }

    public void setCb_no_borrar(RichCommandButton cb_no_borrar) {
        this.cb_no_borrar = cb_no_borrar;
    }

    public RichCommandButton getCb_no_borrar() {
        return cb_no_borrar;
    }

    public void setS15(RichSpacer s15) {
        this.s15 = s15;
    }

    public RichSpacer getS15() {
        return s15;
    }

    public void setOt_borrar_1(RichOutputText ot_borrar_1) {
        this.ot_borrar_1 = ot_borrar_1;
    }

    public RichOutputText getOt_borrar_1() {
        return ot_borrar_1;
    }

    public void setOt_borrar_2(RichOutputText ot_borrar_2) {
        this.ot_borrar_2 = ot_borrar_2;
    }

    public RichOutputText getOt_borrar_2() {
        return ot_borrar_2;
    }

    public void setS16(RichSpacer s16) {
        this.s16 = s16;
    }

    public RichSpacer getS16() {
        return s16;
    }

    public void setTramosTreeModel(TreeModel tramosTreeModel) {
        this.tramosTreeModel = tramosTreeModel;
    }

    public TreeModel getTramosTreeModel() {
        return tramosTreeModel;
    }

    public void setTramo(FnttTramo tramo) {
        this.tramo = tramo;
    }

    public FnttTramo getTramo() {
        return tramo;
    }

    public void setTiposFlujo(List tiposFlujo) {
        this.tiposFlujo = tiposFlujo;
    }

    public List getTiposFlujo() {
        return tiposFlujo;
    }

    public void setSistemasReferencia(List sistemasReferencia) {
        this.sistemasReferencia = sistemasReferencia;
    }

    public List getSistemasReferencia() {
        return sistemasReferencia;
    }


    public void setPanelGroupLayout1(RichPanelGroupLayout panelGroupLayout1) {
        this.panelGroupLayout1 = panelGroupLayout1;
    }

    public RichPanelGroupLayout getPanelGroupLayout1() {
        return panelGroupLayout1;
    }

    public void setPanelGroupLayout2(RichPanelGroupLayout panelGroupLayout2) {
        this.panelGroupLayout2 = panelGroupLayout2;
    }

    public RichPanelGroupLayout getPanelGroupLayout2() {
        return panelGroupLayout2;
    }

    public void setDecorativeBox2(RichDecorativeBox decorativeBox2) {
        this.decorativeBox2 = decorativeBox2;
    }

    public RichDecorativeBox getDecorativeBox2() {
        return decorativeBox2;
    }

    public void setDecorativeBox1(RichDecorativeBox decorativeBox1) {
        this.decorativeBox1 = decorativeBox1;
    }

    public RichDecorativeBox getDecorativeBox1() {
        return decorativeBox1;
    }

    public void setPanelFormLayout1(RichPanelFormLayout panelFormLayout1) {
        this.panelFormLayout1 = panelFormLayout1;
    }

    public RichPanelFormLayout getPanelFormLayout1() {
        return panelFormLayout1;
    }

    public void setInputText1(RichInputText inputText1) {
        this.inputText1 = inputText1;
    }

    public RichInputText getInputText1() {
        return inputText1;
    }

    public void setInputText2(RichInputText inputText2) {
        this.inputText2 = inputText2;
    }

    public RichInputText getInputText2() {
        return inputText2;
    }

    public void setInputText3(RichInputText inputText3) {
        this.inputText3 = inputText3;
    }

    public RichInputText getInputText3() {
        return inputText3;
    }

    public void setInputText4(RichInputText inputText4) {
        this.inputText4 = inputText4;
    }

    public RichInputText getInputText4() {
        return inputText4;
    }


    public void setInputText5(RichInputText inputText5) {
        this.inputText5 = inputText5;
    }

    public RichInputText getInputText5() {
        return inputText5;
    }

    public void setInputText6(RichInputText inputText6) {
        this.inputText6 = inputText6;
    }

    public RichInputText getInputText6() {
        return inputText6;
    }

    public void setInputText7(RichInputText inputText7) {
        this.inputText7 = inputText7;
    }

    public RichInputText getInputText7() {
        return inputText7;
    }

    public void setInputText8(RichInputText inputText8) {
        this.inputText8 = inputText8;
    }

    public RichInputText getInputText8() {
        return inputText8;
    }

    public void setOutputText1(RichOutputText outputText1) {
        this.outputText1 = outputText1;
    }

    public RichOutputText getOutputText1() {
        return outputText1;
    }


    public void setOutputText3(RichOutputText outputText3) {
        this.outputText3 = outputText3;
    }

    public RichOutputText getOutputText3() {
        return outputText3;
    }


    public void setPanelGroupLayout4(RichPanelGroupLayout panelGroupLayout4) {
        this.panelGroupLayout4 = panelGroupLayout4;
    }

    public RichPanelGroupLayout getPanelGroupLayout4() {
        return panelGroupLayout4;
    }

    public void setSpacer2(RichSpacer spacer2) {
        this.spacer2 = spacer2;
    }

    public RichSpacer getSpacer2() {
        return spacer2;
    }

    public void setPanelGroupLayout5(RichPanelGroupLayout panelGroupLayout5) {
        this.panelGroupLayout5 = panelGroupLayout5;
    }

    public RichPanelGroupLayout getPanelGroupLayout5() {
        return panelGroupLayout5;
    }

    public void setSpacer3(RichSpacer spacer3) {
        this.spacer3 = spacer3;
    }

    public RichSpacer getSpacer3() {
        return spacer3;
    }


    public void setPanelStretchLayout1(RichPanelStretchLayout panelStretchLayout1) {
        this.panelStretchLayout1 = panelStretchLayout1;
    }

    public RichPanelStretchLayout getPanelStretchLayout1() {
        return panelStretchLayout1;
    }

    public void setIt_longitud(RichInputText inputText9) {
        this.it_longitud = inputText9;
    }

    public RichInputText getIt_longitud() {
        return it_longitud;
    }

    public void setIt_oferta(RichInputText inputText10) {
        this.it_oferta = inputText10;
    }

    public RichInputText getIt_oferta() {
        return it_oferta;
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

    public void setCommandLink1(RichCommandLink commandLink1) {
        this.commandLink1 = commandLink1;
    }

    public RichCommandLink getCommandLink1() {
        return commandLink1;
    }

    public void setSpacer4(RichSpacer spacer4) {
        this.spacer4 = spacer4;
    }

    public RichSpacer getSpacer4() {
        return spacer4;
    }

    public void setCommandLink2(RichCommandLink commandLink2) {
        this.commandLink2 = commandLink2;
    }

    public RichCommandLink getCommandLink2() {
        return commandLink2;
    }

    public void setPanelGroupLayout6(RichPanelGroupLayout panelGroupLayout6) {
        this.panelGroupLayout6 = panelGroupLayout6;
    }

    public RichPanelGroupLayout getPanelGroupLayout6() {
        return panelGroupLayout6;
    }

    public void setInputText11(RichInputText inputText11) {
        this.inputText11 = inputText11;
    }

    public RichInputText getInputText11() {
        return inputText11;
    }

    public void setInputText21(RichInputText inputText21) {
        this.inputText21 = inputText21;
    }

    public RichInputText getInputText21() {
        return inputText21;
    }

    public void setInputText31(RichInputText inputText31) {
        this.inputText31 = inputText31;
    }

    public RichInputText getInputText31() {
        return inputText31;
    }

    public void setInputText41(RichInputText inputText41) {
        this.inputText41 = inputText41;
    }

    public RichInputText getInputText41() {
        return inputText41;
    }


    public void setPanelGroupLayout8(RichPanelGroupLayout panelGroupLayout8) {
        this.panelGroupLayout8 = panelGroupLayout8;
    }

    public RichPanelGroupLayout getPanelGroupLayout8() {
        return panelGroupLayout8;
    }


    public void setDecorativeBox3(RichDecorativeBox decorativeBox3) {
        this.decorativeBox3 = decorativeBox3;
    }

    public RichDecorativeBox getDecorativeBox3() {
        return decorativeBox3;
    }


    public void setPanelFormLayout3(RichPanelFormLayout panelFormLayout3) {
        this.panelFormLayout3 = panelFormLayout3;
    }

    public RichPanelFormLayout getPanelFormLayout3() {
        return panelFormLayout3;
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

    public void setSelectOneChoice8(RichSelectOneChoice selectOneChoice8) {
        this.selectOneChoice8 = selectOneChoice8;
    }

    public RichSelectOneChoice getSelectOneChoice8() {
        return selectOneChoice8;
    }

    public void setSelectItems8(UISelectItems selectItems8) {
        this.selectItems8 = selectItems8;
    }

    public UISelectItems getSelectItems8() {
        return selectItems8;
    }

    public void setCommandButton1(RichCommandButton commandButton1) {
        this.commandButton1 = commandButton1;
    }

    public RichCommandButton getCommandButton1() {
        return commandButton1;
    }

    public void setPanelCollection1(RichPanelCollection panelCollection1) {
        this.panelCollection1 = panelCollection1;
    }

    public RichPanelCollection getPanelCollection1() {
        return panelCollection1;
    }

    public void setT_relacion(RichTable table1) {
        this.t_relacion = table1;
    }

    public RichTable getT_relacion() {
        return t_relacion;
    }

    public void setMenu1(RichMenu menu1) {
        this.menu1 = menu1;
    }

    public RichMenu getMenu1() {
        return menu1;
    }

    public void setCommandMenuItem1(RichCommandMenuItem commandMenuItem1) {
        this.commandMenuItem1 = commandMenuItem1;
    }

    public RichCommandMenuItem getCommandMenuItem1() {
        return commandMenuItem1;
    }

    public void selectOneChoice6_valueChangeListener(ValueChangeEvent event) {
        
        Object departamento = event.getNewValue();
        this.listaMunicipios = new ArrayList();
        try{
            if(departamento!=null)
                this.listaMunicipios = this.cargarDivipola(((Divipola)departamento).getId());
        }catch(IdeamException e){            
            showMessage(e);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(selectOneChoice8);
    }

    public void commandButton1_actionListener(ActionEvent actionEvent) {
        boolean continuar = true;
        if(this.selectOneChoice8.getValue()==null){
            continuar = false;
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.selectOneChoice8);
        }
        
        if(continuar){
            if(this.municipiosRelacionados==null){
                this.municipiosRelacionados=new ArrayList();    
            }
            if(!this.municipiosRelacionados.contains((Divipola)this.selectOneChoice8.getValue())){
                this.municipiosRelacionados.add((Divipola)this.selectOneChoice8.getValue());
            }
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.t_relacion);
        }
    }

    public void commandMenuItem1_actionListener(ActionEvent actionEvent) {
        if (this.municipioRelacionado == null){
            showMessage(getText("seleccionar_registro"),
                        FacesMessage.SEVERITY_ERROR);
            return;
        }
        String mensaje = StandarBean.getText("municipio_relacion_borrar") + 
                         this.municipioRelacionado.getNombre() + " ?";
        this.outputText7.setValue(mensaje);
        this.mostrarOcultarPopup(this.popup1, true);   
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.popup1);
    }

    public void t_relacion_selectionListener(SelectionEvent selectionEvent) {
        RichTable t_municipio = (RichTable)selectionEvent.getSource();
        this.municipioRelacionado = (Divipola)t_municipio.getSelectedRowData();
    }

    public void dialog1_dialogListener(DialogEvent dialogEvent) {
        if(this.municipiosRelacionados!=null && 
                this.municipiosRelacionados.contains(this.municipioRelacionado)){
            List auxiliar = new ArrayList();
            Iterator it = this.municipiosRelacionados.iterator();
            while (it.hasNext()){
                Divipola pola = (Divipola)it.next();
                if(pola != this.municipioRelacionado){
                    auxiliar.add(pola);
                }
            }
            
            this.municipiosRelacionados.clear();
            this.municipiosRelacionados = auxiliar;
            
        }
        t_relacion.setSelectedRowKeys(null);
        this.municipioRelacionado=null;
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.t_relacion);
    }


    public void setPopup1(RichPopup popup1) {
        this.popup1 = popup1;
    }

    public RichPopup getPopup1() {
        return popup1;
    }

    public void setDialog1(RichDialog dialog1) {
        this.dialog1 = dialog1;
    }

    public RichDialog getDialog1() {
        return dialog1;
    }


    public void setOutputText5(RichOutputText outputText5) {
        this.outputText5 = outputText5;
    }

    public RichOutputText getOutputText5() {
        return outputText5;
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

    public void setMunicipiosRelacionados(List municipiosRelacionados) {
        this.municipiosRelacionados = municipiosRelacionados;
    }

    public List getMunicipiosRelacionados() {
        return municipiosRelacionados;
    }

    public void setMunicipioRelacionado(Divipola municipioRelacionado) {
        this.municipioRelacionado = municipioRelacionado;
    }

    public Divipola getMunicipioRelacionado() {
        return municipioRelacionado;
    }

    public void setPanelGroupLayout3(RichPanelGroupLayout panelGroupLayout3) {
        this.panelGroupLayout3 = panelGroupLayout3;
    }

    public RichPanelGroupLayout getPanelGroupLayout3() {
        return panelGroupLayout3;
    }

    public void setPopup11(RichPopup popup11) {
        this.popup11 = popup11;
    }

    public RichPopup getPopup11() {
        return popup11;
    }

    public void setDialog11(RichDialog dialog11) {
        this.dialog11 = dialog11;
    }

    public RichDialog getDialog11() {
        return dialog11;
    }

    public void setOutputText7(RichOutputText outputText51) {
        this.outputText7 = outputText51;
    }

    public RichOutputText getOutputText7() {
        return outputText7;
    }

    public void setSpacer6(RichSpacer spacer6) {
        this.spacer6 = spacer6;
    }

    public RichSpacer getSpacer6() {
        return spacer6;
    }

    public void setOutputText6(RichOutputText outputText6) {
        this.outputText6 = outputText6;
    }

    public RichOutputText getOutputText6() {
        return outputText6;
    }

    public void setPanelGroupLayout9(RichPanelGroupLayout panelGroupLayout9) {
        this.panelGroupLayout9 = panelGroupLayout9;
    }

    public RichPanelGroupLayout getPanelGroupLayout9() {
        return panelGroupLayout9;
    }

    public void setPanelGroupLayout10(RichPanelGroupLayout panelGroupLayout10) {
        this.panelGroupLayout10 = panelGroupLayout10;
    }

    public RichPanelGroupLayout getPanelGroupLayout10() {
        return panelGroupLayout10;
    }

    public void setPanelFormLayout31(RichPanelFormLayout panelFormLayout31) {
        this.panelFormLayout31 = panelFormLayout31;
    }

    public RichPanelFormLayout getPanelFormLayout31() {
        return panelFormLayout31;
    }

    public void setPanelGroupLayout11(RichPanelGroupLayout panelGroupLayout11) {
        this.panelGroupLayout11 = panelGroupLayout11;
    }

    public RichPanelGroupLayout getPanelGroupLayout11() {
        return panelGroupLayout11;
    }

    public void setPanelGroupLayout12(RichPanelGroupLayout panelGroupLayout12) {
        this.panelGroupLayout12 = panelGroupLayout12;
    }

    public RichPanelGroupLayout getPanelGroupLayout12() {
        return panelGroupLayout12;
    }

    public void setPanelFormLayout4(RichPanelFormLayout panelFormLayout4) {
        this.panelFormLayout4 = panelFormLayout4;
    }

    public RichPanelFormLayout getPanelFormLayout4() {
        return panelFormLayout4;
    }

    public void setListaPuntosMonitoreo(List listaPuntosMonitoreo) {
        this.listaPuntosMonitoreo = listaPuntosMonitoreo;
    }

    public List getListaPuntosMonitoreo() {
        return listaPuntosMonitoreo;
    }

    public void setPuntoMonitoreo(PuntoMonitoreo puntoMonitoreo) {
        this.puntoMonitoreo = puntoMonitoreo;
    }

    public PuntoMonitoreo getPuntoMonitoreo() {
        return puntoMonitoreo;
    }

    public void setPuntosMonitoreoTreeModel(TreeModel puntosMonitoreoTreeModel) {
        this.puntosMonitoreoTreeModel = puntosMonitoreoTreeModel;
    }

    public TreeModel getPuntosMonitoreoTreeModel() {
        return puntosMonitoreoTreeModel;
    }

    public void setPanelBox1(RichPanelBox panelBox1) {
        this.panelBox1 = panelBox1;
    }

    public RichPanelBox getPanelBox1() {
        return panelBox1;
    }

    public void setPanelGroupLayout13(RichPanelGroupLayout panelGroupLayout13) {
        this.panelGroupLayout13 = panelGroupLayout13;
    }

    public RichPanelGroupLayout getPanelGroupLayout13() {
        return panelGroupLayout13;
    }

    public void setPanelCollection2(RichPanelCollection panelCollection2) {
        this.panelCollection2 = panelCollection2;
    }

    public RichPanelCollection getPanelCollection2() {
        return panelCollection2;
    }

    public void setTree1(RichTree tree1) {
        this.tree1 = tree1;
    }

    public RichTree getTree1() {
        return tree1;
    }


    public void setClink(RichCommandLink clink) {
        this.clink = clink;
    }

    public RichCommandLink getClink() {
        return clink;
    }

    public void setSpacer7(RichSpacer spacer7) {
        this.spacer7 = spacer7;
    }

    public RichSpacer getSpacer7() {
        return spacer7;
    }

    public void setMenu2(RichMenu menu2) {
        this.menu2 = menu2;
    }

    public RichMenu getMenu2() {
        return menu2;
    }

    public void setCmi_adicionar(RichCommandMenuItem commandMenuItem2) {
        this.cmi_adicionar = commandMenuItem2;
    }

    public RichCommandMenuItem getCmi_adicionar() {
        return cmi_adicionar;
    }

    public void setAccionAdicionar(String accionAdicionar) {
        this.accionAdicionar = accionAdicionar;
    }

    public String getAccionAdicionar() {
        return accionAdicionar;
    }

    public void setSpacer8(RichSpacer spacer8) {
        this.spacer8 = spacer8;
    }

    public RichSpacer getSpacer8() {
        return spacer8;
    }


    public void setPanelFormLayout5(RichPanelFormLayout panelFormLayout5) {
        this.panelFormLayout5 = panelFormLayout5;
    }

    public RichPanelFormLayout getPanelFormLayout5() {
        return panelFormLayout5;
    }

    public void setSpacer5(RichSpacer spacer5) {
        this.spacer5 = spacer5;
    }

    public RichSpacer getSpacer5() {
        return spacer5;
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

    public void setOutputLabel2(RichOutputLabel outputLabel2) {
        this.outputLabel2 = outputLabel2;
    }

    public RichOutputLabel getOutputLabel2() {
        return outputLabel2;
    }

    public void setPanelFormLayout6(RichPanelFormLayout panelFormLayout6) {
        this.panelFormLayout6 = panelFormLayout6;
    }

    public RichPanelFormLayout getPanelFormLayout6() {
        return panelFormLayout6;
    }

    public void setSpacer10(RichSpacer spacer10) {
        this.spacer10 = spacer10;
    }

    public RichSpacer getSpacer10() {
        return spacer10;
    }

    public void setOutputLabel3(RichOutputLabel outputLabel3) {
        this.outputLabel3 = outputLabel3;
    }

    public RichOutputLabel getOutputLabel3() {
        return outputLabel3;
    }

    public void setPanelFormLayout7(RichPanelFormLayout panelFormLayout7) {
        this.panelFormLayout7 = panelFormLayout7;
    }

    public RichPanelFormLayout getPanelFormLayout7() {
        return panelFormLayout7;
    }

    public void setSpacer11(RichSpacer spacer11) {
        this.spacer11 = spacer11;
    }

    public RichSpacer getSpacer11() {
        return spacer11;
    }

    public void setOutputLabel4(RichOutputLabel outputLabel4) {
        this.outputLabel4 = outputLabel4;
    }

    public RichOutputLabel getOutputLabel4() {
        return outputLabel4;
    }

    public void setPanelFormLayout2(RichPanelFormLayout panelFormLayout2) {
        this.panelFormLayout2 = panelFormLayout2;
    }

    public RichPanelFormLayout getPanelFormLayout2() {
        return panelFormLayout2;
    }

    public void setSpacer1(RichSpacer spacer1) {
        this.spacer1 = spacer1;
    }

    public RichSpacer getSpacer1() {
        return spacer1;
    }

    public void setOutputLabel5(RichOutputLabel outputLabel5) {
        this.outputLabel5 = outputLabel5;
    }

    public RichOutputLabel getOutputLabel5() {
        return outputLabel5;
    }

    public void setIt_grad_long_pi(RichInputText it_grad_long_pi) {
        this.it_grad_long_pi = it_grad_long_pi;
    }

    public RichInputText getIt_grad_long_pi() {
        return it_grad_long_pi;
    }

    public void setIt_minutos_long_pi(RichInputText it_minutos_long_pi) {
        this.it_minutos_long_pi = it_minutos_long_pi;
    }

    public RichInputText getIt_minutos_long_pi() {
        return it_minutos_long_pi;
    }

    public void setIt_segundos_long_pi(RichInputText it_segundos_long_pi) {
        this.it_segundos_long_pi = it_segundos_long_pi;
    }

    public RichInputText getIt_segundos_long_pi() {
        return it_segundos_long_pi;
    }

    public void setIt_grados_long_pf(RichInputText it_grados_long_pf) {
        this.it_grados_long_pf = it_grados_long_pf;
    }

    public RichInputText getIt_grados_long_pf() {
        return it_grados_long_pf;
    }

    public void setIt_minutos_long_pf(RichInputText it_minutos_long_pf) {
        this.it_minutos_long_pf = it_minutos_long_pf;
    }

    public RichInputText getIt_minutos_long_pf() {
        return it_minutos_long_pf;
    }

    public void setIt_segundos_long_pf(RichInputText it_segundos_long_pf) {
        this.it_segundos_long_pf = it_segundos_long_pf;
    }

    public RichInputText getIt_segundos_long_pf() {
        return it_segundos_long_pf;
    }

    public void setPanelFormLayout8(RichPanelFormLayout panelFormLayout8) {
        this.panelFormLayout8 = panelFormLayout8;
    }

    public RichPanelFormLayout getPanelFormLayout8() {
        return panelFormLayout8;
    }

    public void setSpacer12(RichSpacer spacer12) {
        this.spacer12 = spacer12;
    }

    public RichSpacer getSpacer12() {
        return spacer12;
    }

    public void setOutputLabel6(RichOutputLabel outputLabel6) {
        this.outputLabel6 = outputLabel6;
    }

    public RichOutputLabel getOutputLabel6() {
        return outputLabel6;
    }

    public void setPanelFormLayout9(RichPanelFormLayout panelFormLayout9) {
        this.panelFormLayout9 = panelFormLayout9;
    }

    public RichPanelFormLayout getPanelFormLayout9() {
        return panelFormLayout9;
    }


    public void setPanelFormLayout10(RichPanelFormLayout panelFormLayout10) {
        this.panelFormLayout10 = panelFormLayout10;
    }

    public RichPanelFormLayout getPanelFormLayout10() {
        return panelFormLayout10;
    }

  public RichInputText getItObservacion() {
    return itObservacion;
  }

  public void setItObservacion(RichInputText itObservacion) {
    this.itObservacion = itObservacion;
  }

  public RichDecorativeBox getDecorativeBoxAforo() {
    return decorativeBoxAforo;
  }

  public void setDecorativeBoxAforo(RichDecorativeBox decorativeBoxAforo) {
    this.decorativeBoxAforo = decorativeBoxAforo;
  }

  public RichPanelGroupLayout getPanelGroupLayoutAforo() {
    return panelGroupLayoutAforo;
  }

  public void setPanelGroupLayoutAforo(RichPanelGroupLayout panelGroupLayoutAforo) {
    this.panelGroupLayoutAforo = panelGroupLayoutAforo;
  }

  public RichSpacer getSpacerAforo() {
    return spacerAforo;
  }

  public void setSpacerAforo(RichSpacer spacerAforo) {
    this.spacerAforo = spacerAforo;
  }

  public RichOutputText getOutputTextAforo() {
    return outputTextAforo;
  }

  public void setOutputTextAforo(RichOutputText outputTextAforo) {
    this.outputTextAforo = outputTextAforo;
  }

  public RichPanelGroupLayout getPanelGroupLayout2Aforo() {
    return panelGroupLayout2Aforo;
  }

  public void setPanelGroupLayout2Aforo(RichPanelGroupLayout panelGroupLayout2Aforo) {
    this.panelGroupLayout2Aforo = panelGroupLayout2Aforo;
  }

  public RichPanelFormLayout getPanelFormLayoutAforo() {
    return panelFormLayoutAforo;
  }

  public void setPanelFormLayoutAforo(RichPanelFormLayout panelFormLayoutAforo) {
    this.panelFormLayoutAforo = panelFormLayoutAforo;
  }

  public RichSpacer getSpacer2Aforo() {
    return spacer2Aforo;
  }

  public void setSpacer2Aforo(RichSpacer spacer2Aforo) {
    this.spacer2Aforo = spacer2Aforo;
  }

  public RichOutputLabel getOutputLabelAforo() {
    return outputLabelAforo;
  }

  public void setOutputLabelAforo(RichOutputLabel outputLabelAforo) {
    this.outputLabelAforo = outputLabelAforo;
  }

  public RichInputText getInputTextAforo() {
    return inputTextAforo;
  }

  public void setInputTextAforo(RichInputText inputTextAforo) {
    this.inputTextAforo = inputTextAforo;
  }

  public RichInputText getInputText2Aforo() {
    return inputText2Aforo;
  }

  public void setInputText2Aforo(RichInputText inputText2Aforo) {
    this.inputText2Aforo = inputText2Aforo;
  }

  public RichInputText getInputText3Aforo() {
    return inputText3Aforo;
  }

  public void setInputText3Aforo(RichInputText inputText3Aforo) {
    this.inputText3Aforo = inputText3Aforo;
  }

  public RichPanelFormLayout getPanelFormLayout2Aforo() {
    return panelFormLayout2Aforo;
  }

  public void setPanelFormLayout2Aforo(RichPanelFormLayout panelFormLayout2Aforo) {
    this.panelFormLayout2Aforo = panelFormLayout2Aforo;
  }

  public RichSpacer getSpacer3Aforo() {
    return spacer3Aforo;
  }

  public void setSpacer3Aforo(RichSpacer spacer3Aforo) {
    this.spacer3Aforo = spacer3Aforo;
  }

  public RichOutputLabel getOutputLabel2Aforo() {
    return outputLabel2Aforo;
  }

  public void setOutputLabel2Aforo(RichOutputLabel outputLabel2Aforo) {
    this.outputLabel2Aforo = outputLabel2Aforo;
  }

  public RichInputText getInputText4Aforo() {
    return inputText4Aforo;
  }

  public void setInputText4Aforo(RichInputText inputText4Aforo) {
    this.inputText4Aforo = inputText4Aforo;
  }

  public RichInputText getInputText5Aforo() {
    return inputText5Aforo;
  }

  public void setInputText5Aforo(RichInputText inputText5Aforo) {
    this.inputText5Aforo = inputText5Aforo;
  }

  public RichInputText getInputText6Aforo() {
    return inputText6Aforo;
  }

  public void setInputText6Aforo(RichInputText inputText6Aforo) {
    this.inputText6Aforo = inputText6Aforo;
  }

  public FnttTrmAforo getAforo() {
    return aforo;
  }

  public void setAforo(FnttTrmAforo aforo) {
    this.aforo = aforo;
  }

  public RichInputDate getFechaAforo() {
    return fechaAforo;
  }

  public void setFechaAforo(RichInputDate fechaAforo) {
    this.fechaAforo = fechaAforo;
  }

  public RichInputText getInputText7Aforo() {
    return inputText7Aforo;
  }

  public void setInputText7Aforo(RichInputText inputText7Aforo) {
    this.inputText7Aforo = inputText7Aforo;
  }

  public Boolean getFlagAforo() {
    return flagAforo;
  }

  public void setFlagAforo(Boolean flagAforo) {
    this.flagAforo = flagAforo;
  }

  public RichPanelFormLayout getPanelFormLayout3Aforo() {
    return panelFormLayout3Aforo;
  }

  public void setPanelFormLayout3Aforo(RichPanelFormLayout panelFormLayout3Aforo) {
    this.panelFormLayout3Aforo = panelFormLayout3Aforo;
  }

  public RichSpacer getSpacer4Aforo() {
    return spacer4Aforo;
  }

  public void setSpacer4Aforo(RichSpacer spacer4Aforo) {
    this.spacer4Aforo = spacer4Aforo;
  }

  public List<FnttTrmAforo> getLsAforos() {
    return lsAforos;
  }

  public void setLsAforos(List<FnttTrmAforo> lsAforos) {
    this.lsAforos = lsAforos;
  }

  public RichPanelFormLayout getPanelFormLayout4Aforo() {
    return panelFormLayout4Aforo;
  }

  public void setPanelFormLayout4Aforo(RichPanelFormLayout panelFormLayout4Aforo) {
    this.panelFormLayout4Aforo = panelFormLayout4Aforo;
  }

  public RichSpacer getSpacer5Aforo() {
    return spacer5Aforo;
  }

  public void setSpacer5Aforo(RichSpacer spacer5Aforo) {
    this.spacer5Aforo = spacer5Aforo;
  }

  public RichTable getTableAforo() {
    return tableAforo;
  }

  public void setTableAforo(RichTable tableAforo) {
    this.tableAforo = tableAforo;
  }

  public void setCmi_conflicto(RichCommandMenuItem cmi_conflicto) {
    this.cmi_conflicto = cmi_conflicto;
  }

  public RichCommandMenuItem getCmi_conflicto() {
    return cmi_conflicto;
  }

  public void setCmi_objetivo_calidad(RichCommandMenuItem cmi_objetivo_calidad) {
    this.cmi_objetivo_calidad = cmi_objetivo_calidad;
  }

  public RichCommandMenuItem getCmi_objetivo_calidad() {
    return cmi_objetivo_calidad;
  }

  public void setCmi_metas(RichCommandMenuItem cmi_metas) {
    this.cmi_metas = cmi_metas;
  }

  public RichCommandMenuItem getCmi_metas() {
    return cmi_metas;
  }


    public void setAforo_registro_exitoso(RichPopup aforo_registro_exitoso) {
        this.aforo_registro_exitoso = aforo_registro_exitoso;
    }

    public RichPopup getAforo_registro_exitoso() {
        return aforo_registro_exitoso;
    }


    public void setD_aforo_registro_exitoso(RichDialog d_aforo_registro_exitoso) {
        this.d_aforo_registro_exitoso = d_aforo_registro_exitoso;
    }

    public RichDialog getD_aforo_registro_exitoso() {
        return d_aforo_registro_exitoso;
    }


    public void setAforo_borrardo_exitoso(RichPopup aforo_borrardo_exitoso) {
        this.aforo_borrardo_exitoso = aforo_borrardo_exitoso;
    }

    public RichPopup getAforo_borrardo_exitoso() {
        return aforo_borrardo_exitoso;
    }

    public void setD_aforo_borrado_exitoso(RichDialog d_aforo_borrado_exitoso) {
        this.d_aforo_borrado_exitoso = d_aforo_borrado_exitoso;
    }

    public RichDialog getD_aforo_borrado_exitoso() {
        return d_aforo_borrado_exitoso;
    }

    public void setPgaforoPop(RichPanelGroupLayout pgaforoPop) {
        this.pgaforoPop = pgaforoPop;
    }

    public RichPanelGroupLayout getPgaforoPop() {
        return pgaforoPop;
    }

    public void setPgaforoPop2(RichPanelGroupLayout pgaforoPop2) {
        this.pgaforoPop2 = pgaforoPop2;
    }

    public RichPanelGroupLayout getPgaforoPop2() {
        return pgaforoPop2;
    }

    public void setCbAceptarAforo(RichCommandButton cbAceptarAforo) {
        this.cbAceptarAforo = cbAceptarAforo;
    }

    public RichCommandButton getCbAceptarAforo() {
        return cbAceptarAforo;
    }

    public void setCbAceptarAforo2(RichCommandButton cbAceptarAforo2) {
        this.cbAceptarAforo2 = cbAceptarAforo2;
    }

    public RichCommandButton getCbAceptarAforo2() {
        return cbAceptarAforo2;
    }

    public void setPsAforo(RichPanelStretchLayout psAforo) {
        this.psAforo = psAforo;
    }

    public RichPanelStretchLayout getPsAforo() {
        return psAforo;
    }

    public void setPsAforo2(RichPanelStretchLayout psAforo2) {
        this.psAforo2 = psAforo2;
    }

    public RichPanelStretchLayout getPsAforo2() {
        return psAforo2;
    }

    public void setOtAforoPop(RichOutputText otAforoPop) {
        this.otAforoPop = otAforoPop;
    }

    public RichOutputText getOtAforoPop() {
        return otAforoPop;
    }

    public void setOtAforoPop2(RichOutputText otAforoPop2) {
        this.otAforoPop2 = otAforoPop2;
    }

    public RichOutputText getOtAforoPop2() {
        return otAforoPop2;
    }

    public void setImAforoPop(RichImage imAforoPop) {
        this.imAforoPop = imAforoPop;
    }

    public RichImage getImAforoPop() {
        return imAforoPop;
    }

    public void setImAforoPop2(RichImage imAforoPop2) {
        this.imAforoPop2 = imAforoPop2;
    }

    public RichImage getImAforoPop2() {
        return imAforoPop2;
    }

    public void setAceptarActionPop(String aceptarActionPop) {
        this.aceptarActionPop = aceptarActionPop;
    }

    public String getAceptarActionPop() {
        return aceptarActionPop;
    }

    public void setAceptarActionPop2(String aceptarActionPop2) {
        this.aceptarActionPop2 = aceptarActionPop2;
    }

    public String getAceptarActionPop2() {
        return aceptarActionPop2;
    }

    public void setPanelFormLayout11(RichPanelFormLayout panelFormLayout11) {
        this.panelFormLayout11 = panelFormLayout11;
    }

    public RichPanelFormLayout getPanelFormLayout11() {
        return panelFormLayout11;
    }


    public void setPanelFormLayout12(RichPanelFormLayout panelFormLayout12) {
        this.panelFormLayout12 = panelFormLayout12;
    }

    public RichPanelFormLayout getPanelFormLayout12() {
        return panelFormLayout12;
    }
}
