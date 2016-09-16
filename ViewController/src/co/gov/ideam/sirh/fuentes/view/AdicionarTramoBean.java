package co.gov.ideam.sirh.fuentes.view;

import co.gov.ideam.sirh.auditoria.model.Auditoria;
import co.gov.ideam.sirh.auditoria.view.AuditoriasDelegate;
import co.gov.ideam.sirh.fuentes.model.FnttFuenteTramoMunicipio;
import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.fuentes.model.FnttTramo;
import co.gov.ideam.sirh.parametros.model.Divipola;

import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.model.PartZonificListas;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.util.ConstantesParametros;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;

import co.gov.ideam.sirh.view.StandarBean;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UISelectItems;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichMenu;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanRadio;
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
import oracle.adf.view.rich.component.rich.output.RichOutputLabel;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
import oracle.adf.view.rich.component.rich.output.RichSpacer;

import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;

import org.apache.myfaces.trinidad.event.AttributeChangeEvent;
import org.apache.myfaces.trinidad.event.SelectionEvent;

public class AdicionarTramoBean  extends StandarBean{
    private RichForm formAF1;
    private RichDocument documentAF1;
    
    private RichPanelStretchLayout psl1;
    private RichPanelStretchLayout psl2;
    private RichPanelStretchLayout psl3;
    private RichPanelStretchLayout psl7;
    private RichPanelStretchLayout psl10;

    private RichPanelFormLayout pfl1;
    private RichPanelFormLayout pfl2;

    private RichPanelGroupLayout pgl1;
    private RichPanelGroupLayout pgl2;
    private RichPanelGroupLayout pgl3;
    private RichPanelGroupLayout pgl4;
    private RichPanelGroupLayout pgl5;
    private RichPanelGroupLayout pgl14;
    private RichPanelGroupLayout pgl20;
    private RichPanelGroupLayout pgl21;

    private RichPanelBox pb1;
    private RichPanelBox pb2;
    private RichPanelBox pb4;
    
    private RichPanelSplitter ps1;
    
    private RichPanelCollection panelCollection1;
    
    private RichCommandButton cbNext1;
    private RichCommandButton cbNext2;
    private RichCommandButton cbNextPI;
    private RichCommandButton cbRelacion;
    private RichCommandButton cbPrev;
    private RichCommandButton cbPrevPI;
    private RichCommandButton cbAceptar;
    
    private RichSpacer s1;
    private RichSpacer s2;
    private RichSpacer s3;
    private RichSpacer s5;
    private RichSpacer s6;

    private RichSelectOneChoice selectOneChoice1;
    private UISelectItems selectItems1;
    private RichSelectOneChoice selectOneChoice2;
    private UISelectItems selectItems2;
    private RichSelectOneChoice selectOneChoice3;
    private UISelectItems selectItems3;
    private RichSelectOneChoice selectOneChoice4;
    private UISelectItems selectItems4;
    private RichSelectOneChoice selectOneChoice5;
    private UISelectItems selectItems5;
    private RichSelectOneChoice selectOneChoice7;
    private UISelectItems selectItems7;
    private RichSelectOneChoice selectOneChoice8;
    private UISelectItems selectItems8;
    
    private RichInputText it_nombre;
    private RichInputText it_descripcion;
    private RichInputText it_longitud;
    private RichInputText it_oferta;
    private RichInputText it_Observacion;
    private RichInputText it_grados_pi;
    private RichInputText it_minutos_pi;
    private RichInputText it_segundos_pi;
    private RichInputText it_altitud_pi;
    private RichInputText it_grados_pf;
    private RichInputText it_minutos_pf;
    private RichInputText it_segundos_pf;
    private RichInputText it_altitud_pf;
    
    private RichSelectBooleanRadio sbr_si;
    private RichSelectBooleanRadio sbr_no;
    
    private RichTable t_relacion;
    
    private RichMenu menu1;
    
    private RichCommandMenuItem commandMenuItem1;
    
    private RichOutputText ot_confirmacion_borrar;
    private RichOutputText ot8;


    private RichImage i1;
    private RichPopup popup_borrar;
    private RichPopup p_registro_exitoso;
    
    private RichDialog d_registro_exitoso;
    private RichDialog d_borrar;
    private RichDialog d_detalle;
    
    private Divipola municipioRelacionado;
    private List listaArea;
    private List listaZona;
    private List listaSubzona;
    private List listaDepartamentos;
    private List listaMunicipios;
    private List municipiosRelacionados;
    private List tiposFlujo;;
    private List sistemasReferencia;
    private FnttFuente fuente;

    private RichPanelGroupLayout panelGroupLayout2;

    private RichSpacer spacer4;
    private RichPanelFormLayout panelFormLayout1;
    private RichPanelGroupLayout panelGroupLayout1;
    private RichPanelFormLayout panelFormLayout2;
    private RichSpacer spacer1;
    private RichPanelGroupLayout panelGroupLayout5;
    private RichPanelFormLayout panelFormLayout3;
    private RichSpacer spacer3;
    private RichOutputLabel outputLabel1;
    private RichPanelFormLayout panelFormLayout4;
    private RichSpacer spacer6;
    private RichOutputLabel outputLabel2;
    private RichInputText it_grad_long_pi;
    private RichInputText it_minutos_long_pi;
    private RichInputText it_segundos_long_pi;
    private RichPanelFormLayout panelFormLayout5;
    private RichSpacer spacer7;
    private RichOutputLabel outputLabel3;
    private RichPanelFormLayout panelFormLayout6;
    private RichOutputLabel outputLabel4;
    private RichPanelFormLayout panelFormLayout7;
    private RichSpacer spacer2;
    private RichOutputLabel outputLabel5;
    private RichPanelFormLayout panelFormLayout8;
    private RichSpacer spacer5;
    private RichOutputLabel outputLabel6;
    private RichInputText it_grados_long_pf;
    private RichInputText it_minutos_long_pf;
    private RichInputText it_segundos_long_pf;
    private RichPanelFormLayout panelFormLayout9;
    private RichSpacer spacer8;
    private RichActiveOutputText activeOutputText1;
    private RichSpacer spacer9;
    private RichActiveOutputText activeOutputText2;
    private RichSpacer spacer10;
    private RichPopup popup_borrar1;
    private RichDialog d_borrar1;
    private RichOutputText ot_confirmacion_borrar1;
    private RichPanelGroupLayout panelGroupLayout3;
    private RichActiveOutputText activeOutputText3;
    private RichSpacer spacer11;
    private RichSpacer spacer12;
    private String aceptarAction;


    public AdicionarTramoBean(){
        FacesUtils.setActiveBean("adicionarTramoBean", "Adicionar Tramo",
                                 FuenteDelegate.class); 
        FacesUtils.removeManagedBeanFromSession("TramosTreeHandler");
        FacesUtils.removeManagedBeanFromSession("tramosBean");
        FacesUtils.removeManagedBeanFromSession("detalleFuenteBean");
        FacesUtils.removeManagedBeanFromSession("FuentesBean");
        this.load();
    }
    
    public void load(){
        try{
            
            FuenteDelegate fd = FuenteDelegate.getInstance();
            
            UsuarioConectadoTO usuarioConectado = 
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
            
            this.listaDepartamentos = this.cargarDivipola(null);
            this.listaArea = this.cargarZonificacion(null);
            this.listaZona = new ArrayList();
            this.listaSubzona = new ArrayList();

            this.tiposFlujo = this.cargarParametro(ConstantesParametros.CATEGORIA_TIPO_FLUJO);
            this.sistemasReferencia = this.cargarParametro(ConstantesParametros.CATEGORIA_SISTEMA_REFERENCIA);
  
            Object obj = FacesUtils.getFromSession("fuenteSeleccionada");//la fuente desde la cual se inicia.
            if(obj instanceof FnttFuente){
                this.fuente = (FnttFuente)obj;
            }else{                
                Long codigo = (Long)FacesUtils.getFromSession("fuenteSeleccionada");                                            
                this.fuente = fd.getFuente(codigo);                
            }
            
        }catch(IdeamException e){
            showMessage(e);
        }
    }
    
    public void commandMenuItem1_actionListener(ActionEvent actionEvent) {
        if (this.municipioRelacionado == null){
            showMessage(getText("seleccionar_registro"),
                        FacesMessage.SEVERITY_ERROR);
            return;
        }
        String mensaje = this.getText("municipio_relacion_borrar") + 
                         this.municipioRelacionado.getNombre() + " ?";
        this.ot_confirmacion_borrar.setValue(mensaje);
        this.mostrarOcultarPopup(this.popup_borrar, true);   
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.popup_borrar);
    }
    
    public void t_relacion_selectionListener(SelectionEvent selectionEvent) {
        RichTable t_municipio = (RichTable)selectionEvent.getSource();
        this.municipioRelacionado = (Divipola)t_municipio.getSelectedRowData();
    }
    
    public void d_borrar_dialogListener(DialogEvent dialogEvent) throws IdeamException{
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
    
    //pantalla de datos básicos
    public void cbNext1_actionListener(ActionEvent actionEvent) throws IdeamException{
        boolean continuar = true;
        // Validar que se haya seleccionado tipo y numero de id
        if(this.it_nombre.getValue()==null || 
           this.it_nombre.getValue().toString().length()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.it_nombre);       
            continuar = false;
        }  
        
        if(!this.fuente.isEsSubterranea()){
            if(this.it_longitud.getValue()==null || 
               this.it_longitud.getValue().toString().length()==0){
                showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.it_longitud);       
                continuar = false;
            }    
        }
        
        if(this.it_oferta.getValue()==null || 
           this.it_oferta.getValue().toString().length()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.it_oferta);       
            continuar = false;
        } 
        if(this.selectOneChoice7.getValue()==null ){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.selectOneChoice7);       
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
        
        if(continuar){    
            FuenteDelegate fd = FuenteDelegate.getInstance();
            //Valida si ya existe un tramo con ese nombre en la fuente, 
            //creado por la misma autoridad.
            UsuarioConectadoTO usuarioConectado = 
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
            FnttTramo existe = fd.getTramo(this.it_nombre.getValue().toString(),
                                           this.fuente, 
                                           usuarioConectado.getUsuario().
                                           getAutoridadAmbiental());
            if(existe!=null ){
                showMessage(getText("TRM_EXISTE"), FacesMessage.SEVERITY_ERROR);
                return;                       
            }
            
            this.pb1.setVisible(false);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.pb1);   
            
            if(this.fuente.isEsSubterranea()){
                this.pb4.setVisible(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(this.pb4);
            }else{         
                this.pb2.setVisible(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(this.pb2);
            }
        }
    }
    
    
    public void cbNextPI_actionListener(ActionEvent actionEvent) throws IdeamException{
        boolean continuar = true;
        // Validar que se haya seleccionado tipo y numero de id
        
        if(this.selectOneChoice8.getValue()==null ){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.selectOneChoice8);       
            continuar = false;
        }
        if(this.it_grados_pi.getValue()==null || 
           this.it_grados_pi.getValue().toString().length()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.it_grados_pi);       
            continuar = false;
        }   
        
        if(this.it_minutos_pi.getValue()==null || 
           this.it_minutos_pi.getValue().toString().length()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.it_minutos_pi);       
            continuar = false;
        }  
        
        if(this.it_segundos_pi.getValue()==null || 
           this.it_segundos_pi.getValue().toString().length()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.it_segundos_pi);       
            continuar = false;
        }  
        
        if(this.it_altitud_pi.getValue()==null || 
           this.it_altitud_pi.getValue().toString().length()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.it_altitud_pi);       
            continuar = false;
        }  
        
        if(this.it_grados_pf.getValue()==null || 
           this.it_grados_pf.getValue().toString().length()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.it_grados_pf);       
            continuar = false;
        }   
        
        if(this.it_minutos_pf.getValue()==null || 
           this.it_minutos_pf.getValue().toString().length()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.it_minutos_pf);       
            continuar = false;
        }  
        
        if(this.it_segundos_pf.getValue()==null || 
           this.it_segundos_pf.getValue().toString().length()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.it_segundos_pf);       
            continuar = false;
        }  
        
        if(this.it_altitud_pf.getValue()==null || 
           this.it_altitud_pf.getValue().toString().length()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.it_altitud_pf);       
            continuar = false;
        } 
        
        if(continuar){            
            this.pb2.setVisible(false);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.pb1);            
            this.pb4.setVisible(true);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.pb2);
        }
    }
    
    //pantalla de relacion de municipios, es la ultima, se guarda el tramo
    public void cbNext2_actionListener(ActionEvent actionEvent) throws IdeamException{
        boolean continuar = true;
        if(continuar){   
            this.save();   
        }   
    }
       
    //puntos
    public void cbPrevPI_actionListener(ActionEvent actionEvent) throws IdeamException{       
        this.pb2.setVisible(false);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.pb2);  
        this.pb1.setVisible(true);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.pb1);   
    }
    
    //municipios
    public void cbPrev_actionListener(ActionEvent actionEvent) throws IdeamException{   
        this.pb4.setVisible(false);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.pb4); 
        
        if(this.fuente.isEsSubterranea()){
            this.pb1.setVisible(true);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.pb1);   
        }else{
            this.pb2.setVisible(true);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.pb2);   
        }
    }
    
    public void cbRelacion_actionListener(ActionEvent actionEvent) throws IdeamException{
        boolean continuar = true;
        if(this.selectOneChoice5.getValue()==null){
            continuar = false;
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.selectOneChoice5);
        }
        
        if(continuar){
            if(this.municipiosRelacionados==null){
                this.municipiosRelacionados=new ArrayList();    
            }
            if(!this.municipiosRelacionados.contains((Divipola)this.selectOneChoice5.getValue())){
                this.municipiosRelacionados.add((Divipola)this.selectOneChoice5.getValue());
            }
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.t_relacion);
        }
    }
    
    public void selectOneChoice1_valueChangeListener(ValueChangeEvent event)
            throws IdeamException{
        
        Object area = event.getNewValue();
        this.listaZona = new ArrayList();
        try{
            if(area!=null)
                this.listaZona = this.cargarZonificacion(((PartZonificListas)area).getId());
        }catch(IdeamException e){            
            showMessage(e);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(selectOneChoice2);
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
    
    public void selectOneChoice4_valueChangeListener(ValueChangeEvent event)
            throws IdeamException{
        
        Object departamento = event.getNewValue();
        this.listaMunicipios = new ArrayList();
        try{
            if(departamento!=null)
                this.listaMunicipios = this.cargarDivipola(((Divipola)departamento).getId());
        }catch(IdeamException e){            
            showMessage(e);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(selectOneChoice5);
    }

    public void save(){
        try{
            FuenteDelegate fd = FuenteDelegate.getInstance();
            
            FnttTramo tramoNuevo = new FnttTramo();
            tramoNuevo.setNombre(this.it_nombre.getValue().toString().toUpperCase());
            tramoNuevo.setDescripcion((this.it_descripcion.getValue()!=null)?this.it_descripcion.getValue().toString():null);
            
            tramoNuevo.setLongitud((this.it_longitud.getValue()!=null)
                                   ?new BigDecimal(Double.parseDouble(this.it_longitud.getValue().toString()))
                                   :new BigDecimal(0L));
            tramoNuevo.setOfertaHidricaTotal(new BigDecimal(Double.parseDouble(this.it_oferta.getValue().toString())));
            
            tramoNuevo.setTipoFlujo((Lista)this.selectOneChoice7.getValue());
            tramoNuevo.setIdTipoFlujo(tramoNuevo.getTipoFlujo().getCodigo());//Pilar
            tramoNuevo.setSistemaReferencia((Lista)this.selectOneChoice8.getValue());
            tramoNuevo.setIdArea((PartZonificListas)this.selectOneChoice1.getValue());
            tramoNuevo.setIdZona((PartZonificListas)this.selectOneChoice2.getValue());
            tramoNuevo.setIdSubzona((PartZonificListas)this.selectOneChoice3.getValue());

            tramoNuevo.setGradosPi((this.it_grados_pi.getValue()!=null)
                                   ?Integer.parseInt(this.it_grados_pi.getValue().toString())
                                   :0);
            tramoNuevo.setMinutosPi((this.it_minutos_pi.getValue()!=null)
                                    ?Integer.parseInt(this.it_minutos_pi.getValue().toString())
                                    :0);
            tramoNuevo.setSegundosPi((this.it_segundos_pi.getValue()!=null)
                                     ?new BigDecimal(Double.parseDouble(this.it_segundos_pi.getValue().toString()))
                                     :new BigDecimal(0L));
            tramoNuevo.setAltitudPi((this.it_altitud_pi.getValue()!=null)
                                    ?new BigDecimal(Double.parseDouble(this.it_altitud_pi.getValue().toString()))
                                    :new BigDecimal(0L));
            
            tramoNuevo.setGradosLongPi((this.it_grad_long_pi.getValue()!=null)
                                       ?Integer.parseInt(this.it_grad_long_pi.getValue().toString())
                                       :0);
            tramoNuevo.setMinutosLongPi((this.it_minutos_long_pi.getValue()!=null)
                                        ?Integer.parseInt(this.it_minutos_long_pi.getValue().toString())
                                        :0);
            tramoNuevo.setSegundosLongPi((this.it_segundos_long_pi.getValue()!=null)
                                         ?new BigDecimal(Double.parseDouble(this.it_segundos_long_pi.getValue().toString()))
                                         :new BigDecimal(0L));
            
        
            tramoNuevo.setGradosPf((this.it_grados_pf.getValue()!=null)
                                   ?Integer.parseInt(this.it_grados_pf.getValue().toString())
                                   :0);
            tramoNuevo.setMinutosPf((this.it_minutos_pf.getValue()!=null)
                                    ?Integer.parseInt(this.it_minutos_pf.getValue().toString())
                                    :0);
            tramoNuevo.setSegundosPf((this.it_segundos_pf.getValue()!=null)
                                     ?new BigDecimal(Double.parseDouble(this.it_segundos_pf.getValue().toString()))
                                     :new BigDecimal(0L));
            tramoNuevo.setAltitudPf((this.it_altitud_pf.getValue()!=null)
                                    ?new BigDecimal(Double.parseDouble(this.it_altitud_pf.getValue().toString()))
                                    :new BigDecimal(0L));
           
           
            tramoNuevo.setGradosLongPf((this.it_grados_long_pf.getValue()!=null)
                                       ?Integer.parseInt(this.it_grados_long_pf.getValue().toString())
                                       :0);
            tramoNuevo.setMinutosLongPf((this.it_minutos_long_pf.getValue()!=null)
                                        ?Integer.parseInt(this.it_minutos_long_pf.getValue().toString())
                                        :0);
            tramoNuevo.setSegundosLongPf((this.it_segundos_long_pf.getValue()!=null)
                                         ?new BigDecimal(Double.parseDouble(this.it_segundos_long_pf.getValue().toString()))
                                         :new BigDecimal(0L));
            
           
           
           
            UsuarioConectadoTO usuarioConectado = 
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
            
            
            tramoNuevo.setCodigoAutoridad(new Long(usuarioConectado.getUsuario().getAutoridadAmbiental().getId()));
            tramoNuevo.setIdFuente(this.fuente);
            
            //tramoNuevo = fd.updateTramo(tramoNuevo);
            
            
            //adiciona los municipios.
            if(this.municipiosRelacionados!=null && this.municipiosRelacionados.size()>0){
                List <FnttFuenteTramoMunicipio> relacionados = new ArrayList<FnttFuenteTramoMunicipio>();
                
                Iterator it = this.municipiosRelacionados.iterator();
                while(it.hasNext()){
                    Divipola pola = (Divipola) it.next();
                    FnttFuenteTramoMunicipio relacionado = new FnttFuenteTramoMunicipio(this.fuente.getId(), 
                                                                                        tramoNuevo.getId(), 
                                                                                        pola.getId().intValue(), 
                                                                                        pola.getPadreId().intValue());
                    relacionados.add(relacionado);
                }
                tramoNuevo.setFnttFuenteTramoMunicipioList(relacionados);
                
                //tramoNuevo = fd.updateTramo(tramoNuevo);//actualiza el tramo con sus municipios.
            }
            
            tramoNuevo = fd.updateTramo(tramoNuevo);//actualiza el tramo con sus municipios.


            try{
                
                /** 
                 * Estas variables son necesarias para llenar los datos intrinsecos de la auditoria.
                 */
                // UsuarioConectadoTO usuarioConectado = (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
                
                Auditoria auditoria = new Auditoria();
                auditoria.setIdUsuario(usuarioConectado.getUsuario().getCodigo());
                auditoria.setOperacion("GUARDAR");
                auditoria.setObjeto("TRAMOS");
                auditoria.setMetodo(Thread.currentThread().getStackTrace()[1].getMethodName());
                auditoria.setClase(this.getClass().getName());
                //Objeto Afectado (usuario modificado, eliminado o agregado) 
                auditoria.setIdObjeto(tramoNuevo.getId());
               
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

    public void  setFormAF1(RichForm formAF1) {
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

    public void setD_detalle(RichDialog d_detalle) {
        this.d_detalle = d_detalle;
    }

    public RichDialog getD_detalle() {
        return d_detalle;
    }

    public void setPsl2(RichPanelStretchLayout psl2) {
        this.psl2 = psl2;
    }

    public RichPanelStretchLayout getPsl2() {
        return psl2;
    }

    public void setPfl2(RichPanelFormLayout pfl2) {
        this.pfl2 = pfl2;
    }

    public RichPanelFormLayout getPfl2() {
        return pfl2;
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


    public void setPsl1(RichPanelStretchLayout psl1) {
        this.psl1 = psl1;
    }

    public RichPanelStretchLayout getPsl1() {
        return psl1;
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

    public void setPb1(RichPanelBox pb1) {
        this.pb1 = pb1;
    }

    public RichPanelBox getPb1() {
        return pb1;
    }

    public void setCbNext1(RichCommandButton cbNext1) {
        this.cbNext1 = cbNext1;
    }

    public RichCommandButton getCbNext1() {
        return cbNext1;
    }


    public void setS1(RichSpacer s1) {
        this.s1 = s1;
    }

    public RichSpacer getS1() {
        return s1;
    }

    public void setPgl3(RichPanelGroupLayout pgl3) {
        this.pgl3 = pgl3;
    }

    public RichPanelGroupLayout getPgl3() {
        return pgl3;
    }

    public void setPsl3(RichPanelStretchLayout psl3) {
        this.psl3 = psl3;
    }

    public RichPanelStretchLayout getPsl3() {
        return psl3;
    }

    public void setPfl1(RichPanelFormLayout pfl1) {
        this.pfl1 = pfl1;
    }

    public RichPanelFormLayout getPfl1() {
        return pfl1;
    }

    public void setPgl4(RichPanelGroupLayout pgl4) {
        this.pgl4 = pgl4;
    }

    public RichPanelGroupLayout getPgl4() {
        return pgl4;
    }

    public void setPgl5(RichPanelGroupLayout pgl5) {
        this.pgl5 = pgl5;
    }

    public RichPanelGroupLayout getPgl5() {
        return pgl5;
    }

    public void setPb2(RichPanelBox pb2) {
        this.pb2 = pb2;
    }

    public RichPanelBox getPb2() {
        return pb2;
    }

    public void setCbNext2(RichCommandButton cbNext2) {
        this.cbNext2 = cbNext2;
    }

    public RichCommandButton getCbNext2() {
        return cbNext2;
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


    public void setCbRelacion(RichCommandButton cbRelacion) {
        this.cbRelacion = cbRelacion;
    }

    public RichCommandButton getCbRelacion() {
        return cbRelacion;
    }


    public void setPs1(RichPanelSplitter ps1) {
        this.ps1 = ps1;
    }

    public RichPanelSplitter getPs1() {
        return ps1;
    }

    public void setPanelCollection1(RichPanelCollection panelCollection1) {
        this.panelCollection1 = panelCollection1;
    }

    public RichPanelCollection getPanelCollection1() {
        return panelCollection1;
    }

    public void setT_relacion(RichTable t_relacion) {
        this.t_relacion = t_relacion;
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

    public void setMunicipioRelacionado(Divipola municipioRelacionado) {
        this.municipioRelacionado = municipioRelacionado;
    }

    public Divipola getMunicipioRelacionado() {
        return municipioRelacionado;
    }

    public void setOt_confirmacion_borrar(RichOutputText ot_confirmacion_borrar) {
        this.ot_confirmacion_borrar = ot_confirmacion_borrar;
    }

    public RichOutputText getOt_confirmacion_borrar() {
        return ot_confirmacion_borrar;
    }

    public void setPopup_borrar(RichPopup popup_borrar) {
        this.popup_borrar = popup_borrar;
    }

    public RichPopup getPopup_borrar() {
        return popup_borrar;
    }


    public void setD_borrar(RichDialog d_borrar) {
        this.d_borrar = d_borrar;
    }

    public RichDialog getD_borrar() {
        return d_borrar;
    }

    public void setCbPrev(RichCommandButton cbPrev) {
        this.cbPrev = cbPrev;
    }

    public RichCommandButton getCbPrev() {
        return cbPrev;
    }

    public void setS3(RichSpacer s3) {
        this.s3 = s3;
    }

    public RichSpacer getS3() {
        return s3;
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

    public void setCbAceptar(RichCommandButton cbAceptar) {
        this.cbAceptar = cbAceptar;
    }

    public RichCommandButton getCbAceptar() {
        return cbAceptar;
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

    public void setPsl10(RichPanelStretchLayout psl10) {
        this.psl10 = psl10;
    }

    public RichPanelStretchLayout getPsl10() {
        return psl10;
    }


    public void setPgl20(RichPanelGroupLayout pgl20) {
        this.pgl20 = pgl20;
    }

    public RichPanelGroupLayout getPgl20() {
        return pgl20;
    }

    public void setPgl21(RichPanelGroupLayout pgl21) {
        this.pgl21 = pgl21;
    }

    public RichPanelGroupLayout getPgl21() {
        return pgl21;
    }


    public void setPb4(RichPanelBox pb4) {
        this.pb4 = pb4;
    }

    public RichPanelBox getPb4() {
        return pb4;
    }

    public void setCbPrevPI(RichCommandButton cbPrevPI) {
        this.cbPrevPI = cbPrevPI;
    }

    public RichCommandButton getCbPrevPI() {
        return cbPrevPI;
    }


    public void setS5(RichSpacer s5) {
        this.s5 = s5;
    }

    public RichSpacer getS5() {
        return s5;
    }

    public void setS6(RichSpacer s6) {
        this.s6 = s6;
    }

    public RichSpacer getS6() {
        return s6;
    }


    public void setCbNextPI(RichCommandButton cbNextPI) {
        this.cbNextPI = cbNextPI;
    }

    public RichCommandButton getCbNextPI() {
        return cbNextPI;
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

    public void setIt_longitud(RichInputText it_longitud) {
        this.it_longitud = it_longitud;
    }

    public RichInputText getIt_longitud() {
        return it_longitud;
    }

    public void setIt_oferta(RichInputText it_oferta) {
        this.it_oferta = it_oferta;
    }

    public RichInputText getIt_oferta() {
        return it_oferta;
    }


    public void setIt_grados_pi(RichInputText inputText1) {
        this.it_grados_pi = inputText1;
    }

    public RichInputText getIt_grados_pi() {
        return it_grados_pi;
    }

    public void setIt_minutos_pi(RichInputText inputText2) {
        this.it_minutos_pi = inputText2;
    }

    public RichInputText getIt_minutos_pi() {
        return it_minutos_pi;
    }

    public void setIt_segundos_pi(RichInputText inputText3) {
        this.it_segundos_pi = inputText3;
    }

    public RichInputText getIt_segundos_pi() {
        return it_segundos_pi;
    }

    public void setIt_altitud_pi(RichInputText inputText4) {
        this.it_altitud_pi = inputText4;
    }

    public RichInputText getIt_altitud_pi() {
        return it_altitud_pi;
    }

    public void setIt_grados_pf(RichInputText inputText1) {
        this.it_grados_pf = inputText1;
    }

    public RichInputText getIt_grados_pf() {
        return it_grados_pf;
    }

    public void setIt_minutos_pf(RichInputText inputText1) {
        this.it_minutos_pf = inputText1;
    }

    public RichInputText getIt_minutos_pf() {
        return it_minutos_pf;
    }

    public void setIt_segundos_pf(RichInputText inputText1) {
        this.it_segundos_pf = inputText1;
    }

    public RichInputText getIt_segundos_pf() {
        return it_segundos_pf;
    }

    public void setIt_altitud_pf(RichInputText inputText1) {
        this.it_altitud_pf = inputText1;
    }

    public RichInputText getIt_altitud_pf() {
        return it_altitud_pf;
    }


    public void setFuente(FnttFuente fuente) {
        this.fuente = fuente;
    }

    public FnttFuente getFuente() {
        return fuente;
    }

    public void setPanelGroupLayout2(RichPanelGroupLayout panelGroupLayout2) {
        this.panelGroupLayout2 = panelGroupLayout2;
    }

    public RichPanelGroupLayout getPanelGroupLayout2() {
        return panelGroupLayout2;
    }

  
   

    public void setSpacer4(RichSpacer spacer4) {
        this.spacer4 = spacer4;
    }

    public RichSpacer getSpacer4() {
        return spacer4;
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


    public void setPanelGroupLayout5(RichPanelGroupLayout panelGroupLayout5) {
        this.panelGroupLayout5 = panelGroupLayout5;
    }

    public RichPanelGroupLayout getPanelGroupLayout5() {
        return panelGroupLayout5;
    }

    public void setPanelFormLayout3(RichPanelFormLayout panelFormLayout3) {
        this.panelFormLayout3 = panelFormLayout3;
    }

    public RichPanelFormLayout getPanelFormLayout3() {
        return panelFormLayout3;
    }

    public void setSpacer3(RichSpacer spacer3) {
        this.spacer3 = spacer3;
    }

    public RichSpacer getSpacer3() {
        return spacer3;
    }

    public void setOutputLabel1(RichOutputLabel outputLabel1) {
        this.outputLabel1 = outputLabel1;
    }

    public RichOutputLabel getOutputLabel1() {
        return outputLabel1;
    }

    public void setPanelFormLayout4(RichPanelFormLayout panelFormLayout4) {
        this.panelFormLayout4 = panelFormLayout4;
    }

    public RichPanelFormLayout getPanelFormLayout4() {
        return panelFormLayout4;
    }

    public void setSpacer6(RichSpacer spacer6) {
        this.spacer6 = spacer6;
    }

    public RichSpacer getSpacer6() {
        return spacer6;
    }

    public void setOutputLabel2(RichOutputLabel outputLabel2) {
        this.outputLabel2 = outputLabel2;
    }

    public RichOutputLabel getOutputLabel2() {
        return outputLabel2;
    }

    public void setIt_grad_long_pi(RichInputText inputText1) {
        this.it_grad_long_pi = inputText1;
    }

    public RichInputText getIt_grad_long_pi() {
        return it_grad_long_pi;
    }

    public void setIt_minutos_long_pi(RichInputText inputText1) {
        this.it_minutos_long_pi = inputText1;
    }

    public RichInputText getIt_minutos_long_pi() {
        return it_minutos_long_pi;
    }

    public void setIt_segundos_long_pi(RichInputText inputText1) {
        this.it_segundos_long_pi = inputText1;
    }

    public RichInputText getIt_segundos_long_pi() {
        return it_segundos_long_pi;
    }

    public void setPanelFormLayout5(RichPanelFormLayout panelFormLayout5) {
        this.panelFormLayout5 = panelFormLayout5;
    }

    public RichPanelFormLayout getPanelFormLayout5() {
        return panelFormLayout5;
    }

    public void setSpacer7(RichSpacer spacer7) {
        this.spacer7 = spacer7;
    }

    public RichSpacer getSpacer7() {
        return spacer7;
    }

    public void setOutputLabel3(RichOutputLabel outputLabel3) {
        this.outputLabel3 = outputLabel3;
    }

    public RichOutputLabel getOutputLabel3() {
        return outputLabel3;
    }

    public void setPanelFormLayout6(RichPanelFormLayout panelFormLayout6) {
        this.panelFormLayout6 = panelFormLayout6;
    }

    public RichPanelFormLayout getPanelFormLayout6() {
        return panelFormLayout6;
    }


    public void setOutputLabel4(RichOutputLabel outputLabel4) {
        this.outputLabel4 = outputLabel4;
    }

    public RichOutputLabel getOutputLabel4() {
        return outputLabel4;
    }

    public void setPanelFormLayout7(RichPanelFormLayout panelFormLayout7) {
        this.panelFormLayout7 = panelFormLayout7;
    }

    public RichPanelFormLayout getPanelFormLayout7() {
        return panelFormLayout7;
    }

    public void setSpacer2(RichSpacer spacer2) {
        this.spacer2 = spacer2;
    }

    public RichSpacer getSpacer2() {
        return spacer2;
    }

    public void setOutputLabel5(RichOutputLabel outputLabel5) {
        this.outputLabel5 = outputLabel5;
    }

    public RichOutputLabel getOutputLabel5() {
        return outputLabel5;
    }


    public void setPanelFormLayout8(RichPanelFormLayout panelFormLayout8) {
        this.panelFormLayout8 = panelFormLayout8;
    }

    public RichPanelFormLayout getPanelFormLayout8() {
        return panelFormLayout8;
    }

    public void setSpacer5(RichSpacer spacer5) {
        this.spacer5 = spacer5;
    }

    public RichSpacer getSpacer5() {
        return spacer5;
    }

    public void setOutputLabel6(RichOutputLabel outputLabel6) {
        this.outputLabel6 = outputLabel6;
    }

    public RichOutputLabel getOutputLabel6() {
        return outputLabel6;
    }

    public void setIt_grados_long_pf(RichInputText inputText1) {
        this.it_grados_long_pf = inputText1;
    }

    public RichInputText getIt_grados_long_pf() {
        return it_grados_long_pf;
    }

    public void setIt_minutos_long_pf(RichInputText inputText2) {
        this.it_minutos_long_pf = inputText2;
    }

    public RichInputText getIt_minutos_long_pf() {
        return it_minutos_long_pf;
    }

    public void setIt_segundos_long_pf(RichInputText inputText3) {
        this.it_segundos_long_pf = inputText3;
    }

    public RichInputText getIt_segundos_long_pf() {
        return it_segundos_long_pf;
    }

    public void setPanelFormLayout9(RichPanelFormLayout panelFormLayout9) {
        this.panelFormLayout9 = panelFormLayout9;
    }

    public RichPanelFormLayout getPanelFormLayout9() {
        return panelFormLayout9;
    }

    public void setSpacer8(RichSpacer spacer8) {
        this.spacer8 = spacer8;
    }

    public RichSpacer getSpacer8() {
        return spacer8;
    }

    public void setActiveOutputText1(RichActiveOutputText activeOutputText1) {
        this.activeOutputText1 = activeOutputText1;
    }

    public RichActiveOutputText getActiveOutputText1() {
        return activeOutputText1;
    }

    public void setSpacer9(RichSpacer spacer9) {
        this.spacer9 = spacer9;
    }

    public RichSpacer getSpacer9() {
        return spacer9;
    }

    public void setActiveOutputText2(RichActiveOutputText activeOutputText2) {
        this.activeOutputText2 = activeOutputText2;
    }

    public RichActiveOutputText getActiveOutputText2() {
        return activeOutputText2;
    }

    public void setSpacer10(RichSpacer spacer10) {
        this.spacer10 = spacer10;
    }

    public RichSpacer getSpacer10() {
        return spacer10;
    }


    public void setPopup_borrar1(RichPopup popup_borrar1) {
        this.popup_borrar1 = popup_borrar1;
    }

    public RichPopup getPopup_borrar1() {
        return popup_borrar1;
    }

    public void setD_borrar1(RichDialog d_borrar1) {
        this.d_borrar1 = d_borrar1;
    }

    public RichDialog getD_borrar1() {
        return d_borrar1;
    }

    public void setOt_confirmacion_borrar1(RichOutputText ot_confirmacion_borrar1) {
        this.ot_confirmacion_borrar1 = ot_confirmacion_borrar1;
    }

    public RichOutputText getOt_confirmacion_borrar1() {
        return ot_confirmacion_borrar1;
    }

    public void setPanelGroupLayout3(RichPanelGroupLayout panelGroupLayout3) {
        this.panelGroupLayout3 = panelGroupLayout3;
    }

    public RichPanelGroupLayout getPanelGroupLayout3() {
        return panelGroupLayout3;
    }

    public void setActiveOutputText3(RichActiveOutputText activeOutputText3) {
        this.activeOutputText3 = activeOutputText3;
    }

    public RichActiveOutputText getActiveOutputText3() {
        return activeOutputText3;
    }

    public void setSpacer11(RichSpacer spacer11) {
        this.spacer11 = spacer11;
    }

    public RichSpacer getSpacer11() {
        return spacer11;
    }

    public void setSpacer12(RichSpacer spacer12) {
        this.spacer12 = spacer12;
    }

    public RichSpacer getSpacer12() {
        return spacer12;
    }
    
    public void cb_aceptar_actionListener(ActionEvent actionEvent) {
        this.setAceptarAction("detalleFuente");
    }

    
    

    public String getAceptarAction() {
        return aceptarAction;
    }

    public void setAceptarAction(String aceptarAction) {
        this.aceptarAction = aceptarAction;
    }


    public void setIt_Observacion(RichInputText it_Observacion) {
        this.it_Observacion = it_Observacion;
    }

    public RichInputText getIt_Observacion() {
        return it_Observacion;
    }
}
