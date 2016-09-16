package co.gov.ideam.sirh.usuarios.agua.view;

import co.gov.ideam.sirh.auditoria.model.Auditoria;
import co.gov.ideam.sirh.auditoria.view.AuditoriasDelegate;
import co.gov.ideam.sirh.fuentes.model.CriteriosBusquedaTO;
import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.fuentes.model.FnttTramo;
import co.gov.ideam.sirh.fuentes.view.FuenteDelegate;
import co.gov.ideam.sirh.fuentes.view.FuentesBean;
import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.model.PartZonificListas;
import co.gov.ideam.sirh.parametros.view.ParametrosDelegate;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.usuarios.agua.model.Captacion;
import co.gov.ideam.sirh.usuarios.agua.model.Concesion;
import co.gov.ideam.sirh.usuarios.agua.model.Predio;
import co.gov.ideam.sirh.usuarios.agua.model.RurtCaptacionComponentes;
import co.gov.ideam.sirh.util.Constantes;
import co.gov.ideam.sirh.util.ConstantesParametros;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import javax.persistence.Column;

import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanRadio;
import oracle.adf.view.rich.component.rich.input.RichSelectManyCheckbox;
import oracle.adf.view.rich.component.rich.input.RichSelectManyChoice;
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

public class AdicionarCaptacionBean extends StandarBean {
    
    //padre
    private Concesion concesion;
    //padre no valido, para cpataciones ilegale. Pilar
    private Concesion concesionnoValido;
    //objeto a crear.
    private Captacion nuevaCaptacion;
    //listado de departamentos desde categorias.
    private List listaDepartamentos;
    //listado de municipios de un departamento.
    private List listaMunicipios;
    //listado de areas hidrologicos.  Usada en el paso 1.
    private List listaArea;
    //listado de zonas de una area. Usada en el paso 1.
    private List listaZona;
    //listado de subzonas de una zona. Usada en el paso 1.
    private List listaSubzona;
    //lista de tipos de fuente captacion en categoria.
    private List listaTipos;
    //lista de tipos de fuente en categoria.
    private List listaTiposFuente;
    //Lista de fuentes.
    private List listaFuentes;
    //Listado de tramos de una fuente.
    private List listaTramos;
    //listado de areas hidrologicos. Usada en el paso 2.
    private List listaAreaPunto;
    //listado de areas hidrologicos. Usada en el paso 2.
    private List listaZonaPunto;
    //listado de areas hidrologicos. Usada en el paso 2.
    private List listaSubzonaPunto;
    //Lista de provincias hidrogeologicas
    private List listaProvincias;
    //Lista de tipos de centros problados en categorias.
    private List listaTipoCentroPoblado;
    private List listaTipoCapta;
    //lista de sistemas de referencia en categorias.
    private List listaSistemasReferencia;
    //Lista de tipos de punto en subterráneas.
    private List listaTipoPunto;
    //Lista de tipos de captación en lluvias.
    private List listaTipoCaptacion;
    //Lista de estados de captación.
    private List listaEstadoCaptacion;
    //Lista continuidad del caudal
    private List listaContinuidad;
    //Lista de componentes en agua subterranea y superficial.
    private List listaComponentes;
    //Lista de los metodos de extracción
    private List listaMetodoExtraccion;
    //atributo para saber si es fuente superficial
    private boolean superficial;
    //atributo para saber si es fuente subterranea
    private boolean subterranea;
    //atributo para saber si es fuente lluvia
    private boolean lluvia;
    //atributo para saber si fuente mineral
    private boolean mineral;
    //atributo para saber si es fuente servida
    private boolean servida;
    //mensaje seteado en el panelbox2
    private String mensajePanel2;
    //mensaje seteado en el panelbox3
    private String mensajePanel3;
    //mensaje seteado en el panelbox4
    private String mensajePanel4;
    //mensaje seteado en el panelbox5
    private String mensajePanel5;
    //mensaje seteado en el panelbox6
    private String mensajePanel6;
    
    private RichForm form1;
    private RichDocument document1;
    private RichForm form2;
    private RichDocument document2;
    private RichPanelStretchLayout panelStretchLayout1;
    private RichPanelGroupLayout panelGroupLayout1;
    private RichPanelBox panelBox1;
    private RichPanelStretchLayout panelStretchLayout2;
    private RichPanelGroupLayout panelGroupLayout2;
    private RichPanelGroupLayout panelGroupLayout3;
    private RichCommandButton cbNext1;
    private RichSpacer spacer1;
    private RichPanelFormLayout panelFormLayout1;
    private RichSelectOneChoice soc_tipo_fuente;
    private UISelectItems si_tipo_fuente;
    private RichSelectOneChoice soc_area;
    private UISelectItems si_area;
    private RichSelectOneChoice soc_zona;
    private UISelectItems si_zona;
    private RichSelectOneChoice soc_subzona;
    private UISelectItems si_subzona;
    private RichSelectOneChoice socFuente;
    private UISelectItems siFuentes;
    private RichSelectOneChoice socTramo;
    private UISelectItems siTramos;
    private RichPanelStretchLayout panelStretchLayout3;
    private RichPanelGroupLayout panelGroupLayout4;
    private RichPanelBox panelBox2;
    private RichPanelBox panelBox3;
    private RichPanelBox panelBox4;
    private RichPanelBox panelBox5;
    private RichPanelBox panelBox6;
    private RichPanelStretchLayout panelStretchLayout4;
    private RichPanelGroupLayout panelGroupLayout5;
    private RichPanelGroupLayout panelGroupLayout6;
    private RichCommandButton cbNextDatosBasicos;
    private RichPanelFormLayout panelFormLayout2;
    private RichSpacer spacer2;
    private RichOutputText outputText1;
    private RichSpacer spacer3;
    private RichPanelGroupLayout panelGroupLayout7;
    private RichSpacer spacer4;
    private RichSelectOneChoice socUnidadHidro;
    private UISelectItems siUnidadHidro;
    private RichPanelStretchLayout panelStretchLayout5;
    private RichPanelStretchLayout panelStretchLayout6;
    private RichPanelStretchLayout panelStretchLayout7;
    private RichPanelStretchLayout panelStretchLayout8;
    private RichPanelGroupLayout panelGroupLayout8;
    private RichPanelGroupLayout panelGroupLayout9;
    private RichCommandButton cbPrevUbicacion;
    private RichSpacer spacer5;
    private RichCommandButton cbNextUbicacion;
    private RichSpacer spacer6;
    private RichPanelGroupLayout panelGroupLayout10;
    private RichPanelFormLayout panelFormLayout3;
    private RichSelectOneChoice socDepartamento;
    private UISelectItems siDepartamento;
    private RichSelectOneChoice socMunicipio;
    private UISelectItems siMunicipio;
    private RichSelectOneChoice socTipoCP;
    private UISelectItems siTipoCP;
    private RichInputText itNombreCentroPoblado;
    private RichPanelGroupLayout panelGroupLayout11;
    private RichPanelGroupLayout panelGroupLayout12;
    private RichPanelGroupLayout panelGroupLayout13;
    private RichPanelGroupLayout panelGroupLayout14;
    private RichPanelGroupLayout panelGroupLayout15;
    private RichPanelGroupLayout panelGroupLayout16;
    private RichCommandButton cbPrevComponentes;
    private RichSpacer spacer7;
    private RichCommandButton cbNextComponentes;
    private RichSpacer spacer8;
    private RichCommandButton cbPrevDescripcion;
    private RichSpacer spacer9;
    private RichCommandButton cbNextDescripcion;
    private RichSpacer spacer10;
    private RichCommandButton cbPrevGeoreferencia;
    private RichSpacer spacer11;
    private RichCommandButton cbNextGeoreferencia;
    private RichSpacer spacer12;
    private RichPanelGroupLayout panelGroupLayout17;
    private RichPanelFormLayout panelFormLayout4;
    private RichPanelGroupLayout panelGroupLayout18;
    private RichPanelFormLayout panelFormLayout5;
    private RichPanelGroupLayout panelGroupLayout19;
    private RichPanelGroupLayout panelGroupLayout20;
    private RichPanelFormLayout panelFormLayout6;
    private RichPanelGroupLayout panelGroupLayout21;
    private RichSelectOneChoice socSistemaReferencia;
    private UISelectItems siSistemaReferencia;
    private RichSpacer spacer13;
    private RichPanelFormLayout panelFormLayout7;
    private RichInputText itGradosLat;
    private RichInputText itMinutosLat;
    private RichInputText itSegundosLat;
    private RichInputText itAltitud;
    private RichSelectOneChoice socTipoPunto;
    private UISelectItems siTipoPunto;
    private RichSelectOneChoice socTipoCaptacion;
    private UISelectItems siTipoCaptacion;
    private RichInputText itOfertaEstimada;
    private RichInputText itOferta;
    private RichInputText itAreaCaptacion;
    private RichInputText itCaudalVer;
    private RichInputText itDescripcionAcceso;
    private RichSelectBooleanRadio sbrMacromedicion1;
    private RichSelectBooleanRadio sbrMacroMedicion2;
    //Pilar
    private RichSelectBooleanRadio sbrPerteneceRed1;
    private RichSelectBooleanRadio sbrPerteneceRed2;
    private RichSelectOneChoice socEstadoCaptacion;
    private UISelectItems siEstadoCaptacion;
    private RichSelectOneChoice socContinuidad;
    private UISelectItems siContinuidad;
    private RichInputText itCaudalDisegno;
    private RichSelectBooleanRadio sbrServidumbre1;
    private RichSelectBooleanRadio sbrServidumbre2;
    private RichSpacer spacer15;
    private RichSpacer spacer16;
    private RichSelectManyCheckbox smcComponentes;
    private UISelectItems siComponentes;
    private RichInputText itOfertaTotal;
    private RichInputText itOfertaDisponible;
    private RichInputText itObservacion;
    private RichInputText itOfertaSubterranea;
    private RichSelectOneChoice socMetodoExtraccion;
    private UISelectItems siMetodoExtraccion;
    private RichOutputText outputText3;
    private RichInputText inputText1;
    private RichPopup p_registro_exitoso;
    private RichDialog d_registro_exitoso;
    private RichCommandButton cbAceptar;
    private RichPanelGroupLayout pgl14;
    private RichPanelStretchLayout psl7;
    private RichOutputText ot8;
    private RichImage i1;
    private RichPanelFormLayout panelFormLayout8;
    private RichPanelFormLayout panelFormLayout9;
    private RichActiveOutputText activeOutputText1;
    private RichSpacer spacer14;
    private RichSpacer spacer17;
    private RichOutputLabel outputLabel1;
    private RichInputText itGradosLong;
    private RichInputText itMinutosLong;
    private RichInputText itSegundosLong;
    private RichSpacer spacer18;
    private RichOutputLabel outputLabel2;
    private RichInputText itIdentificadorPuntoSubt;
    private RichSelectOneChoice socTipoFuente;
    private UISelectItems siTipoFuente;
    private boolean mostrarTramo=false;
    private RichSpacer spacer19;
    private RichSelectOneChoice select_capt;
    private UISelectItems selectItems1;
    private String aceptarAction;
    public AdicionarCaptacionBean(){
        FacesUtils.setActiveBean("adicionarCaptacionBean",
                                 "Adicionar Captación", 
                                 UsuariosAguaDelegate.class);
        
        //FacesUtils.removeManagedBeanFromSession("adicionarFuenteBean");
        this.load();
    }
    public void load(){
        
      
        try{
            Object paginaOrigen = FacesUtils.getFromSession("paginaOrigen");
            if(paginaOrigen!=null&&paginaOrigen.toString().equals("detallePredio")){
                System.out.println("ESTA CAPTACION SE CREARÁ CON CNCESION NO VALIDA");
            }else{//Pilar
                UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
                Object obj = FacesUtils.getFromSession("concesionSeleccionada");//la concesion desde la cual se inicia.
                if(obj instanceof Concesion){
                    this.concesion = (Concesion)obj;
                }else{                
                    Long codigo = (Long)FacesUtils.getFromSession("concesionSeleccionada");                                            
                    this.concesion = uad.getConcesion(codigo);                
                }
            }  
                
            this.listaDepartamentos = this.cargarDivipola(null);
            this.listaArea = this.cargarZonificacion(null);
            this.listaTipos = this.cargarParametro(ConstantesParametros.
                                                   CATEGORIA_TIPO_FUENTE_CAPTA);
            this.listaTipoCapta = this.cargarParametro(ConstantesParametros.
                                                   CATEGORIA_TIPO_CAPTA);
            this.listaTiposFuente = this.cargarParametro(ConstantesParametros.
                                                   CATEGORIA_TIPO_FUENTE);
            this.listaTipoCentroPoblado = this.cargarParametro(ConstantesParametros.
                                                   CATEGORIA_TIPO_CENTRO_POBLADO);
            this.listaProvincias = this.cargarParametro(ConstantesParametros.
                                                   CATEGORIA_PROVINCIA_HIDROGEOLOGICA);
            this.listaSistemasReferencia = this.cargarParametro(ConstantesParametros.
                                                           CATEGORIA_SISTEMA_REFERENCIA);
            this.listaTipoPunto = this.cargarParametro(ConstantesParametros.
                                                   CATEGORIA_TIPO_PUNTO);
            this.listaTipoCaptacion = this.cargarParametro(ConstantesParametros.
                                                   CATEGORIA_TIPO_CAPTACION);
            this.listaEstadoCaptacion = this.cargarParametro(ConstantesParametros.
                                                   CATEGORIA_ESTADO_CAPTACION);
            
            this.listaMetodoExtraccion = this.cargarParametro(ConstantesParametros.
                                                   CATEGORIA_METODO_EXTRACCION);
            
            this.listaContinuidad = this.cargarContinuidad();
            
            this.listaMunicipios = new ArrayList();
            this.listaZona = new ArrayList();
            this.listaSubzona = new ArrayList();
            this.listaFuentes =  new ArrayList();
            this.listaTramos =  new ArrayList();
            
            this.listaAreaPunto = this.listaArea;
            this.listaZonaPunto = new ArrayList();
            this.listaSubzonaPunto = new ArrayList();
            
            
        }catch(IdeamException e){
            showMessage(e);
        }
    }
    
    
    //paso 1
    
    public void soc_tipo_fuente_valueChangeListener(ValueChangeEvent event) throws IdeamException{
        Object tipoFuente = event.getNewValue();
        this.listaZona = new ArrayList();
        this.listaSubzona = new ArrayList();
        this.listaFuentes =  new ArrayList();
        this.listaTramos =  new ArrayList();
        System.out.println("Tipo de fuente seleccioanda:"+((Lista)tipoFuente).getCodigo().longValue());
        if(tipoFuente==null 
           || ((Lista)tipoFuente).getCodigo().longValue()==ConstantesParametros.LISTA_AGUAS_LLUVIAS
           || ((Lista)tipoFuente).getCodigo().longValue()==ConstantesParametros.LISTA_AGUAS_MINERALES
            || ((Lista)tipoFuente).getCodigo().longValue()==ConstantesParametros.LISTA_AGUAS_SERVIDAS
        ){
            this.socTipoFuente.setVisible(false);
            this.socFuente.setVisible(false);
            this.socTramo.setVisible(false);
           
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.socFuente);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.socTramo);
           AdfFacesContext.getCurrentInstance().addPartialTarget(this.soc_area);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.soc_zona);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.soc_subzona);
        }else if(((Lista)tipoFuente).getCodigo().longValue()==
                 ConstantesParametros.LISTA_AGUAS_SUBTERRANEAS){
            System.out.println("Oculta el tramo");
            this.socTramo.setVisible(false);
            this.mostrarTramo=false;
            //debe consultar las fuentes subterraneas
            try{
                ParametrosDelegate pd = ParametrosDelegate.getInstance();
                Lista fs = pd.getLista(ConstantesParametros.
                                       LISTA_FUENTE_SUBTERRANEA.intValue());
               
                
                CriteriosBusquedaTO criterios = new CriteriosBusquedaTO();
                UsuarioConectadoTO usuarioConectado = 
                    (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
                
                criterios.setAutoridad(usuarioConectado.getUsuario().getAutoridadAmbiental());
                criterios.setTipoFuente(fs);
                
                this.listaFuentes = this.cargarFuentes(criterios);
                this.socFuente.setLabel("Sistema acuífero");
                AdfFacesContext.getCurrentInstance().addPartialTarget(this.socFuente);
            }catch(Exception e){
                throw new IdeamException();
            }
           
            this.socFuente.setVisible(true);
            //this.socTramo.setVisible(true);
            this.socTipoFuente.setVisible(false);//revisar esto
            
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.socFuente);
            //AdfFacesContext.getCurrentInstance().addPartialTarget(this.socTramo);
 
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.form2);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.soc_area);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.soc_zona);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.soc_subzona);
        }else {//superficial
            
            this.mostrarTramo=true;
            this.socTipoFuente.setVisible(true);
            this.socFuente.setLabel("Fuente");
            this.socFuente.setVisible(true);
            this.socTramo.setVisible(true);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.socFuente);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.socTramo);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.form2);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.soc_area);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.soc_zona);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.soc_subzona);
        }
        
        if(((Lista)tipoFuente).getCodigo().longValue()==ConstantesParametros.LISTA_AGUAS_SUPERFICIALES){
            this.mensajePanel2=""+getText("CPT_SUPERFICIAL")+" "+getText("CPT_DATOS");
            this.mensajePanel3=""+getText("CPT_SUPERFICIAL")+" "+getText("CPT_UBICACION");
            this.mensajePanel4=""+getText("CPT_SUPERFICIAL")+" "+getText("CPT_COMPONENTES_SA");
            this.mensajePanel5=""+getText("CPT_SUPERFICIAL")+" "+getText("CPT_DESCRIPCION");
            this.mensajePanel6=""+getText("CPT_SUPERFICIAL")+" "+getText("CPT_GEOREFERENCIACION");
        }else if(((Lista)tipoFuente).getCodigo().longValue()==ConstantesParametros.LISTA_AGUAS_SUBTERRANEAS ){
            this.mensajePanel2=""+getText("CPT_SUBTERRANEA")+" "+getText("CPT_DATOS");
            this.mensajePanel3=""+getText("CPT_SUBTERRANEA")+" "+getText("CPT_UBICACION");
            this.mensajePanel4=""+getText("CPT_SUBTERRANEA")+" "+getText("CPT_COMPONENTES_SA");
            this.mensajePanel5=""+getText("CPT_SUBTERRANEA")+" "+getText("CPT_DESCRIPCION");
            this.mensajePanel6=""+getText("CPT_SUBTERRANEA")+" "+getText("CPT_GEOREFERENCIACION");
        }else if(((Lista)tipoFuente).getCodigo().longValue()==ConstantesParametros.LISTA_AGUAS_LLUVIAS){
            this.mensajePanel2=""+getText("CPT_LLUVIAS")+" "+getText("CPT_DATOS");
            this.mensajePanel3=""+getText("CPT_LLUVIAS")+" "+getText("CPT_UBICACION");
            this.mensajePanel4=""+getText("CPT_LLUVIAS")+" "+getText("CPT_COMPONENTES_SA");
            this.mensajePanel5=""+getText("CPT_LLUVIAS")+" "+getText("CPT_DESCRIPCION");
            this.mensajePanel6=""+getText("CPT_LLUVIAS")+" "+getText("CPT_GEOREFERENCIACION");
        }else if(((Lista)tipoFuente).getCodigo().longValue()==ConstantesParametros.LISTA_AGUAS_MINERALES){
            this.mensajePanel2=""+getText("CPT_MINERALES")+" "+getText("CPT_DATOS");
            this.mensajePanel3=""+getText("CPT_MINERALES")+" "+getText("CPT_UBICACION");
            this.mensajePanel4=""+getText("CPT_MINERALES")+" "+getText("CPT_COMPONENTES_SA");
            this.mensajePanel5=""+getText("CPT_MINERALES")+" "+getText("CPT_DESCRIPCION");
            this.mensajePanel6=""+getText("CPT_MINERALES")+" "+getText("CPT_GEOREFERENCIACION");
        }else if(((Lista)tipoFuente).getCodigo().longValue()==ConstantesParametros.LISTA_AGUAS_SERVIDAS){
            this.mensajePanel2=""+getText("CPT_SERVIDAS")+" "+getText("CPT_DATOS");
            this.mensajePanel3=""+getText("CPT_SERVIDAS")+" "+getText("CPT_UBICACION");
            this.mensajePanel4=""+getText("CPT_SERVIDAS")+" "+getText("CPT_COMPONENTES_SA");
            this.mensajePanel5=""+getText("CPT_SERVIDAS")+" "+getText("CPT_DESCRIPCION");
            this.mensajePanel6=""+getText("CPT_SERVIDAS")+" "+getText("CPT_GEOREFERENCIACION");
        }   
    }
    
    
    public void soc_area_valueChangeListener(ValueChangeEvent event) throws IdeamException{
        Object area = event.getNewValue();
        this.listaZona = new ArrayList();
        this.listaSubzona = new ArrayList();
        
        if(((Lista)this.soc_tipo_fuente.getValue()).getCodigo().longValue()!=
                ConstantesParametros.LISTA_AGUAS_SUBTERRANEAS){            
            this.listaFuentes =  new ArrayList();
            this.listaTramos =  new ArrayList();
        }
        try{
            if(area!=null){
                this.listaZona = this.cargarZonificacion(((PartZonificListas)area).getId());
            }
        }catch(IdeamException e){            
            showMessage(e);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.soc_zona);
    }


    public void soc_zona_valueChangeListener(ValueChangeEvent event) throws IdeamException{
        Object zona = event.getNewValue();
        this.listaSubzona = new ArrayList();
        if(((Lista)this.soc_tipo_fuente.getValue()).getCodigo().longValue()!=
                ConstantesParametros.LISTA_AGUAS_SUBTERRANEAS){            
            this.listaFuentes =  new ArrayList();
            this.listaTramos =  new ArrayList();
        }
        
        try{
            if(zona!=null){
                this.listaSubzona = this.cargarZonificacion(((PartZonificListas)zona).getId());
            }
        }catch(IdeamException e){            
            showMessage(e);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.soc_subzona);
    }
    
    public void soc_subzona_valueChangeListener(ValueChangeEvent event) throws IdeamException{
        Object subzona = event.getNewValue();
        CriteriosBusquedaTO criterios = new CriteriosBusquedaTO();
        System.out.println("-----CAMBIO DE SUBZONA---------");
        try{
           /* if(((Lista)this.soc_tipo_fuente.getValue()).getCodigo().longValue()!=
                    ConstantesParametros.LISTA_AGUAS_SUBTERRANEAS
                || ((Lista)this.soc_tipo_fuente.getValue()).getCodigo().longValue()!=
                                ConstantesParametros.LISTA_AGUAS_MINERALES){            
                this.listaFuentes =  new ArrayList();
                this.listaTramos =  new ArrayList();
            }else if(((Lista)this.soc_tipo_fuente.getValue()).getCodigo().longValue()==
               ConstantesParametros.LISTA_AGUAS_SUBTERRANEAS  ){
                   ParametrosDelegate pd = ParametrosDelegate.getInstance();
                   Lista fs = pd.getLista(ConstantesParametros.
                                          LISTA_FUENTE_SUBTERRANEA.intValue());
                   criterios.setTipoFuente(fs);
                   this.listaFuentes = this.cargarFuentes(criterios); 
               }else*/ if(((Lista)this.soc_tipo_fuente.getValue()).getCodigo().
                        longValue()==ConstantesParametros.LISTA_AGUAS_SUPERFICIALES){
                   
                    UsuarioConectadoTO usuarioConectado = 
                        (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
                    
                    criterios.setAutoridad(usuarioConectado.getUsuario().getAutoridadAmbiental());
                    
                    if(this.soc_area.getValue()!=null){
                        criterios.setArea((PartZonificListas)this.soc_area.getValue());
                    }
                    if(this.soc_zona.getValue()!=null){
                        criterios.setZona((PartZonificListas)this.soc_zona.getValue());
                    }
                    if(this.soc_subzona.getValue()!=null){
                        criterios.setSubzona((PartZonificListas)this.soc_subzona.getValue());
                    }
                    
                    this.listaFuentes = this.cargarFuentes(criterios);
                    AdfFacesContext.getCurrentInstance().addPartialTarget(this.socFuente);
                }
            
        }catch(IdeamException e){            
            showMessage(e);
        }
        
    }
    
    public void socTipoFuente_valueChangeListener(ValueChangeEvent event) {
        Object tipoFuente = event.getNewValue();
        if(((Lista)this.soc_tipo_fuente.getValue()).getCodigo().longValue()!=
                ConstantesParametros.LISTA_AGUAS_SUBTERRANEAS){            
            this.listaFuentes =  new ArrayList();
            this.listaTramos =  new ArrayList();
        }
        try{
            if(((Lista)this.soc_tipo_fuente.getValue()).getCodigo().
                    longValue()==ConstantesParametros.LISTA_AGUAS_SUPERFICIALES ){
                
                CriteriosBusquedaTO criterios = new CriteriosBusquedaTO();
                UsuarioConectadoTO usuarioConectado = 
                    (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
                
                criterios.setAutoridad(usuarioConectado.getUsuario().
                                       getAutoridadAmbiental());
                
                if(this.soc_area.getValue()!=null){
                    criterios.setArea((PartZonificListas)this.soc_area.getValue());
                }
                if(this.soc_zona.getValue()!=null){
                    criterios.setZona((PartZonificListas)this.soc_zona.getValue());
                }
                if(this.soc_subzona.getValue()!=null){
                    criterios.setSubzona((PartZonificListas)this.soc_subzona.getValue());
                }
                if(this.socTipoFuente.getValue()!=null){
                    criterios.setTipoFuente((Lista)this.socTipoFuente.getValue());
                }
                
                this.listaFuentes = this.cargarFuentes(criterios);
            }
        }catch(IdeamException e){            
            showMessage(e);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.socFuente);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.socTramo);
    }
    
    public void socFuente_valueChangeListener(ValueChangeEvent event)throws IdeamException {
        Object fuente = event.getNewValue();
        this.listaTramos = new ArrayList();
        try{
            
            if(fuente!=null && this.soc_tipo_fuente.getValue()!=null){
               if(((Lista)this.soc_tipo_fuente.getValue()).getCodigo().
                  longValue()==ConstantesParametros.LISTA_AGUAS_SUPERFICIALES
                       // || ((Lista)this.soc_tipo_fuente.getValue()).getCodigo().
                         //  longValue()==ConstantesParametros.LISTA_AGUAS_SUBTERRANEAS
               ){
                   System.out.print("HCP Entro a socFuente_valueChangeListener");
                   CriteriosBusquedaTO criterios = new CriteriosBusquedaTO();
                   UsuarioConectadoTO usuarioConectado = 
                       (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
                   
                   criterios.setAutoridad(usuarioConectado.getUsuario().getAutoridadAmbiental());
                   
                   if(this.soc_area.getValue()!=null){
                       criterios.setArea((PartZonificListas)this.soc_area.getValue());
                   }
                   if(this.soc_zona.getValue()!=null){
                       criterios.setZona((PartZonificListas)this.soc_zona.getValue());
                   }
                   if(this.soc_subzona.getValue()!=null){
                       criterios.setSubzona((PartZonificListas)this.soc_subzona.getValue());
                   }
                   if(this.socFuente.getValue()!=null){
                        criterios.setFuente((FnttFuente)this.socFuente.getValue());
                    }
                    FuenteDelegate fd = FuenteDelegate.getInstance();
                    this.listaTramos = this.cargarTramos(criterios);
               }
            }
        }catch(IdeamException e){            
            showMessage(e);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.socTramo);
    } 
    
    public void cbNextDatosBasicos_actionListener(ActionEvent actionEvent) {
        String outcome = "";
        boolean continuar = true;
        System.out.println("cbNextDatosBasicos_actionListener");
        if(this.soc_tipo_fuente.getValue()!=null){
           if( ((Lista)this.soc_tipo_fuente.getValue()).getCodigo().
                    longValue()==ConstantesParametros.LISTA_AGUAS_SUBTERRANEAS ||
                    ((Lista)this.soc_tipo_fuente.getValue()).getCodigo().
                    longValue()==ConstantesParametros.LISTA_AGUAS_SERVIDAS ||
                    ((Lista)this.soc_tipo_fuente.getValue()).getCodigo().
                    longValue()==ConstantesParametros.LISTA_AGUAS_MINERALES ||
                    ((Lista)this.soc_tipo_fuente.getValue()).getCodigo().
                    longValue()==ConstantesParametros.LISTA_AGUAS_LLUVIAS ||
                   ((Lista)this.soc_tipo_fuente.getValue()).getCodigo().
                   longValue()==ConstantesParametros.LISTA_AGUAS_SUPERFICIALES
           ){
                
                if(this.soc_area.getValue()==null){
                    showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.soc_area);       
                    continuar = false;
                }
                if(this.soc_zona.getValue()==null){
                    showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.soc_zona);       
                    continuar = false;
                }
                if(this.soc_subzona.getValue()==null){
                    showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.soc_subzona);       
                    continuar = false;
                }
            }
           
            if(//((Lista)this.soc_tipo_fuente.getValue()).getCodigo().
                 //   longValue()==ConstantesParametros.LISTA_AGUAS_SUBTERRANEAS ||
                ((Lista)this.soc_tipo_fuente.getValue()).getCodigo().
                longValue()==ConstantesParametros.LISTA_AGUAS_SUPERFICIALES
            ){
                
                if(this.socFuente.getValue()==null){
                    showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.socFuente);       
                    continuar = false;
                }
                if(this.socTramo.getValue()==null){
                    showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.socTramo);       
                    continuar = false;
                }
            }
           
           
        }else{
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.soc_tipo_fuente);
            continuar = false;
        }
        
        if(continuar){
            this.panelBox2.setVisible(false);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelBox2);
            this.panelBox3.setVisible(true);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelBox3);
        }
    }
    
    
    //Paso 3
    
    public void socDepartamento_valueChangeListener(ValueChangeEvent event) throws IdeamException{
        Object depto = event.getNewValue();
        this.listaMunicipios = new ArrayList();
        try{
            if(depto!=null){
                this.listaMunicipios = this.cargarDivipola(((Divipola)depto).getId());
            }
        }catch(IdeamException e){            
            showMessage(e);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.socMunicipio);
    }
    
    public void cbNextUbicacion_actionListener(ActionEvent actionEvent) {
        boolean continuar = true;

        if(this.socDepartamento.getValue()==null){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.socDepartamento);       
            continuar = false;
        }
        if(this.socMunicipio.getValue()==null){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.socMunicipio);       
            continuar = false;
        }
        if(this.socTipoCP.getValue()==null){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.socTipoCP);       
            continuar = false;
        }
        
        
        if(this.soc_tipo_fuente.getValue()!=null){
            if(((Lista)this.soc_tipo_fuente.getValue()).getCodigo().longValue()==ConstantesParametros.LISTA_AGUAS_LLUVIAS){
                if(this.socTipoCaptacion.getValue()==null){
                    showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.socTipoCaptacion);       
                    continuar = false;
                }
            }
            
            if(((Lista)this.soc_tipo_fuente.getValue()).getCodigo().longValue()==ConstantesParametros.LISTA_AGUAS_SUBTERRANEAS){
                if(this.itIdentificadorPuntoSubt.getValue()==null){
                    showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.itIdentificadorPuntoSubt);       
                    continuar = false;
                }
            }
        }else{
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.soc_tipo_fuente);       
            continuar = false;    
        }
        
        if(continuar){
            
            if(((Lista)this.soc_tipo_fuente.getValue()).getCodigo().longValue()==ConstantesParametros.LISTA_AGUAS_SUPERFICIALES){
                //primero cargar los componentes
                try{
                    this.listaComponentes = this.cargarParametro(ConstantesParametros.
                                                       CATEGORIA_COMPONENTES_SUPERFICIAL);
                    /*if(listaComponentes!=null){
                        List<UIComponent> child = this.panelFormLayout4.getChildren();
                        
                    }*/
                }catch(IdeamException e){
                    showMessage(e);
                }
                 
                this.panelBox3.setVisible(false);
                AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelBox3);
                this.panelBox4.setVisible(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelBox4);
            }else if(((Lista)this.soc_tipo_fuente.getValue()).getCodigo().longValue()==ConstantesParametros.LISTA_AGUAS_SUBTERRANEAS){
                //primero cargar los componentes
                try{
                    this.listaComponentes = this.cargarParametro(ConstantesParametros.
                                                       CATEGORIA_COMPONENTES_SUBTERRANEA);
                }catch(IdeamException e){
                    showMessage(e);
                }
            
                this.panelBox3.setVisible(false);
                AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelBox3);
                this.panelBox4.setVisible(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelBox4);
            }else{
                this.panelBox3.setVisible(false);
                AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelBox3);
                this.panelBox5.setVisible(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelBox5);
            }
        }
    }
    
    public void cbPrevUbicacion_actionListener(ActionEvent actionEvent) {
        boolean continuar = true;
        
        if(continuar){
            this.panelBox3.setVisible(false);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelBox3);
            this.panelBox2.setVisible(true);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelBox2);

        }
    }
    
    
    //Paso 4
    

    
    public void cbNextComponentes_actionListener(ActionEvent actionEvent) {
        boolean continuar = true;
        System.out.println("cbNextComponentes_actionListener");
        System.out.println("cbNextComponentes_actionListener continuar:"+continuar);
        /*if(this.soc_tipo_fuente.getValue()!=null && 
           ((Lista)this.soc_tipo_fuente.getValue()).getCodigo().
                 longValue()==ConstantesParametros.LISTA_AGUAS_SUBTERRANEAS){
            if(this.itOfertaTotal==null || 
               this.itOfertaTotal.getValue()==null){
                showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.itOfertaTotal);       
                continuar = false;
            }
        }
        System.out.println("cbNextComponentes_actionListener continuar:"+continuar);
        */
        if(continuar){
            this.panelBox4.setVisible(false);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelBox4);
            this.panelBox5.setVisible(true);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelBox5);

        }
    }
    
    public void cbPrevComponentes_actionListener(ActionEvent actionEvent) {
        boolean continuar = true;
        
        if(continuar){
            this.panelBox4.setVisible(false);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelBox4);
            this.panelBox3.setVisible(true);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelBox3);

        }
    }
    
    
    //Paso 5
    
    
    
    public void cbNextDescripcion_actionListener(ActionEvent actionEvent) {
        boolean continuar = true;
        System.out.println("cbNextDescripcion_actionListener");
        if(continuar){
            this.panelBox5.setVisible(false);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelBox5);
            this.panelBox6.setVisible(true);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelBox6);

        }
    }
    
    public void cbPrevDescripcion_actionListener(ActionEvent actionEvent) {
        boolean continuar = true;
        
        if(continuar){
            if(((Lista)this.soc_tipo_fuente.getValue()).getCodigo().longValue()==ConstantesParametros.LISTA_AGUAS_SUPERFICIALES ||
                    ((Lista)this.soc_tipo_fuente.getValue()).getCodigo().longValue()==ConstantesParametros.LISTA_AGUAS_SUBTERRANEAS){
                
                this.panelBox5.setVisible(false);
                AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelBox5);
                this.panelBox4.setVisible(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelBox4);
            }else{
                this.panelBox5.setVisible(false);
                AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelBox5);
                this.panelBox3.setVisible(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelBox3);
            }

        }
    }
    
    //Paso 6
    
    public void cbNextGeoreferencia_actionListener(ActionEvent actionEvent) {
        boolean continuar = true;

        //validaciones
        if(this.socSistemaReferencia.getValue()==null ){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.socSistemaReferencia);       
            continuar = false;
        }
        if(this.itGradosLat.getValue()==null || 
           this.itGradosLat.getValue().toString().length()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.itGradosLat);       
            continuar = false;
        }   
        
        if(this.itMinutosLat.getValue()==null || 
           this.itMinutosLat.getValue().toString().length()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.itMinutosLat);       
            continuar = false;
        }  
        
        if(this.itSegundosLat.getValue()==null || 
           this.itSegundosLat.getValue().toString().length()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.itSegundosLat);       
            continuar = false;
        }  
        
        if(this.itGradosLong.getValue()==null || 
           this.itGradosLong.getValue().toString().length()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.itGradosLong);       
            continuar = false;
        }   
        
        if(this.itMinutosLong.getValue()==null || 
           this.itMinutosLong.getValue().toString().length()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.itMinutosLong);       
            continuar = false;
        }  
        
        if(this.itSegundosLong.getValue()==null || 
           this.itSegundosLong.getValue().toString().length()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.itSegundosLong);       
            continuar = false;
        }  
        
        if(this.itAltitud.getValue()==null || 
           this.itAltitud.getValue().toString().length()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.itAltitud);       
            continuar = false;
        }  
        
        if(this.itDescripcionAcceso.getValue()==null || 
           this.itDescripcionAcceso.getValue().toString().length()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.itDescripcionAcceso);       
            continuar = false;
        } 
        
        if(continuar){     
            this.save();
            
        }       
    }
    
    public void cbPrevGeoreferencia_actionListener(ActionEvent actionEvent) {
        boolean continuar = true;
        
        if(continuar){
            this.panelBox6.setVisible(false);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelBox6);
            this.panelBox5.setVisible(true);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelBox5);

        }
    }
    
    
    //
    
    
    public void save(){
        try{
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
            this.nuevaCaptacion = new Captacion();
            UsuarioConectadoTO usuarioConectado = 
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
                       
            this.nuevaCaptacion.setCodigoAutoridad(usuarioConectado.getUsuario().
                                   getAutoridadAmbiental().getId().longValue());
            Object paginaOrigen = FacesUtils.getFromSession("paginaOrigen");
            if(paginaOrigen!=null&&paginaOrigen.toString().equals("detallePredio")){
                Object objPredio = FacesUtils.getFromSession("predioConCaptacionesSinConc");
                
                if(objPredio!=null&&objPredio instanceof Predio){
                    Predio predio=(Predio)objPredio;
                    System.out.println("ESTA CAPTACION SE CREARÁ CON CNCESION NO VALIDA al predio:"+predio.getCodigo());
                    concesionnoValido= new Concesion();
                    List<Captacion>listaCapSinConc= uad.getCaptacionesSinConcesion(predio);
                    concesionnoValido.setNumeroExpediente("Exp_NoValido_"+predio.getCodigo()+"_"+listaCapSinConc.size());
                    concesionnoValido.setResolucionCaudal("Res_NoValido_"+predio.getCodigo()+"_"+listaCapSinConc.size());
                    concesionnoValido.setNo_valida(1);
                    
                    concesionnoValido.setCodigoPredio(predio.getCodigo());
                    System.out.println("+++++++++++++++++++++++++listaCapSinConc:"+listaCapSinConc.size());
                    concesionnoValido.setCodigoAutoridadAmbiental(usuarioConectado.getUsuario().
                                   getAutoridadAmbiental().getId().longValue());      
                    concesionnoValido = uad.registrarConcesion(this.concesionnoValido);
                    
                    this.nuevaCaptacion.setIdConcesion(this.concesionnoValido);
                }
            }else{
                this.nuevaCaptacion.setIdConcesion(this.concesion);
            }
            
            //Pilar. Se mueve. this.nuevaCaptacion = new Captacion();
            
            //UsuarioConectadoTO usuarioConectado = 
            //    (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
                       
            //this.nuevaCaptacion.setCodigoAutoridad(usuarioConectado.getUsuario().
            //                       getAutoridadAmbiental().getId().longValue());
            this.nuevaCaptacion.setTipoFuenteCaptacion((this.soc_tipo_fuente.getValue()!=null)?((Lista)this.soc_tipo_fuente.getValue()).getCodigo():null);
            this.nuevaCaptacion.setAreaCaptacion((this.itAreaCaptacion.getValue()!=null)?Double.parseDouble(this.itAreaCaptacion.getValue().toString()):null);
            this.nuevaCaptacion.setCaudalDisegno((this.itCaudalDisegno.getValue()!=null)?Double.parseDouble(this.itCaudalDisegno.getValue().toString()):null);
            
            this.nuevaCaptacion.setContinuidad((this.socContinuidad.getValue()!=null)?this.socContinuidad.getValue().toString():null);
            this.nuevaCaptacion.setDescripcionAcceso(this.itDescripcionAcceso.getValue().toString());
            this.nuevaCaptacion.setEstadoCaptacion((this.socEstadoCaptacion.getValue()!=null)?((Lista)this.socEstadoCaptacion.getValue()).getCodigo():null);
            
            this.nuevaCaptacion.setGradosLat(Integer.parseInt(this.itGradosLat.getValue().toString()));
            this.nuevaCaptacion.setMinutosLat(Integer.parseInt(this.itMinutosLat.getValue().toString()));
            this.nuevaCaptacion.setSegundosLat(Double.parseDouble(this.itSegundosLat.getValue().toString()));
            
            this.nuevaCaptacion.setGradosLong(Integer.parseInt(this.itGradosLong.getValue().toString()));
            this.nuevaCaptacion.setMinutosLong(Integer.parseInt(this.itMinutosLong.getValue().toString()));
            this.nuevaCaptacion.setSegundosLong(Double.parseDouble(this.itSegundosLong.getValue().toString()));
            
            this.nuevaCaptacion.setAltitud(Double.parseDouble(this.itAltitud.getValue().toString()));
            this.nuevaCaptacion.setSistemaReferencia(((Lista)this.socSistemaReferencia.getValue()).getCodigo());
            
            this.nuevaCaptacion.setIdArea((this.soc_area.getValue()!=null)?(PartZonificListas)this.soc_area.getValue():null);
            this.nuevaCaptacion.setIdZona((this.soc_zona.getValue()!=null)?(PartZonificListas)this.soc_zona.getValue():null);
            this.nuevaCaptacion.setIdSubzona((this.soc_subzona.getValue()!=null)?(PartZonificListas)this.soc_subzona.getValue():null);
            this.nuevaCaptacion.setIdFuente((this.socFuente.getValue()!=null)?(FnttFuente)this.socFuente.getValue():null);
            // si es agua subterranea se registra por defecto el unico tramo de la fuente
            if(((Lista)this.soc_tipo_fuente.getValue()).getCodigo().longValue()==ConstantesParametros.LISTA_AGUAS_SUBTERRANEAS){
                FuenteDelegate fad = FuenteDelegate.getInstance();
                FnttFuente f=(FnttFuente)this.socFuente.getValue();
                System.out.println("guardando captacion, a fuente:"+f.getId());
                List<FnttTramo> lTr=fad.getListaTramos(f.getId().intValue());
                System.out.println("guardando captacion, a tramo:"+lTr.get(0).getNombre());
                this.nuevaCaptacion.setIdTramo(lTr.get(0));
            }else    
                this.nuevaCaptacion.setIdTramo((this.socTramo.getValue()!=null)?(FnttTramo)this.socTramo.getValue():null);
            
            //Pilar. Se mueve. this.nuevaCaptacion.setIdConcesion(this.concesion);
            this.nuevaCaptacion.setIdDepartamento((this.socDepartamento.getValue()!=null)?((Divipola)this.socDepartamento.getValue()).getId().intValue():null);
            this.nuevaCaptacion.setIdMunicipio((this.socMunicipio.getValue()!=null)?((Divipola)this.socMunicipio.getValue()).getId().intValue():null);
            this.nuevaCaptacion.setIdTipoCentroPoblado((this.socTipoCP.getValue()!=null)?((Lista)this.socTipoCP.getValue()).getCodigo():null);
            
            this.nuevaCaptacion.setMetodoExtraccion((this.socMetodoExtraccion.getValue()!=null)?((Lista)this.socMetodoExtraccion.getValue()).getCodigo():null);
            
            this.nuevaCaptacion.setNombreCentroPoblado((this.itNombreCentroPoblado.getValue()!=null)?this.itNombreCentroPoblado.getValue().toString():null);
            if(nuevaCaptacion.getIdFuente()!=null && nuevaCaptacion.getIdFuente().getId_provinciahidro()!=null)
             this.nuevaCaptacion.setProvinciaHidrogeologica(nuevaCaptacion.getIdFuente().getId_provinciahidro().getCodigo());
            if(nuevaCaptacion.getIdFuente()!=null && nuevaCaptacion.getIdFuente().getUnidadhidro()!=null)
             this.nuevaCaptacion.setUnidadHidrogeologica(nuevaCaptacion.getIdFuente().getUnidadhidro());
            
            this.nuevaCaptacion.setTipoCaptacion((this.socTipoCaptacion.getValue()!=null)?((Lista)this.socTipoCaptacion.getValue()).getCodigo():null);
            this.nuevaCaptacion.setTipoPunto((this.socTipoPunto.getValue()!=null)?((Lista)this.socTipoPunto.getValue()).getCodigo():null);
            this.nuevaCaptacion.setIdentificadorPunto((this.itIdentificadorPuntoSubt.getValue()!=null)?this.itIdentificadorPuntoSubt.getValue().toString():null);
            
            this.nuevaCaptacion.setCaudalVertimiento((this.itCaudalVer.getValue()!=null)?Double.parseDouble(this.itCaudalVer.getValue().toString()):null);
            
            
            Double ofertaTotal = null;
            Double ofertaDisponible = null;
            if(this.nuevaCaptacion.getTipoFuenteCaptacion().longValue()==
                    ConstantesParametros.LISTA_AGUAS_SUPERFICIALES ||
                    this.nuevaCaptacion.getTipoFuenteCaptacion().longValue()==
                    ConstantesParametros.LISTA_AGUAS_SUBTERRANEAS 
            ){
                ofertaDisponible = (this.itOfertaDisponible.getValue()!=null)?Double.parseDouble(this.itOfertaDisponible.getValue().toString()):null;
                ofertaTotal = (this.itOfertaTotal.getValue()!=null)?Double.parseDouble(this.itOfertaTotal.getValue().toString()):null;
                this.nuevaCaptacion.setTipoCaptacion((this.select_capt.getValue()!=null)?((Lista)this.select_capt.getValue()).getCodigo():null);
                
                
              
            }else if(
                    this.nuevaCaptacion.getTipoFuenteCaptacion().longValue()==
                    ConstantesParametros.LISTA_AGUAS_LLUVIAS 
            ){
                ofertaDisponible = (this.itOfertaEstimada.getValue()!=null)?Double.parseDouble(this.itOfertaEstimada.getValue().toString()):null;
                ofertaTotal = (this.itOferta.getValue()!=null)?Double.parseDouble(this.itOferta.getValue().toString()):null;
            }
            
            
            this.nuevaCaptacion.setOfertaDisponible(ofertaDisponible);
            this.nuevaCaptacion.setOfertaHidricaTotal(ofertaTotal);
            this.nuevaCaptacion.setObservacion((this.itObservacion.getValue()!=null)?this.itObservacion.getValue().toString():null);
            if(this.sbrPerteneceRed1.isSelected()){
                this.nuevaCaptacion.setRed_monitoreoAux(true);
            }else{
                this.nuevaCaptacion.setRed_monitoreoAux(false);
            }
            if(this.sbrMacromedicion1.isSelected()){
                this.nuevaCaptacion.setTieneMacroMedicionAux(true);
            }else{
                this.nuevaCaptacion.setTieneMacroMedicionAux(false);
            }
            
            if(this.sbrServidumbre1.isSelected()){
                this.nuevaCaptacion.setTieneServidumbreAux(true);
            }else{
                this.nuevaCaptacion.setTieneServidumbreAux(false);
            }
            
           // Pilar. Se mueve arriba. UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
            if(this.smcComponentes.getValue()!=null){
                this.nuevaCaptacion.setRurtCaptacionComponentesList(new ArrayList());
                Iterator it= ((List)this.smcComponentes.getValue()).iterator();
                while(it.hasNext()){
                    Lista lis = (Lista)it.next();
                    RurtCaptacionComponentes nuevo = new RurtCaptacionComponentes();
                    nuevo.setIdCaptacion(this.nuevaCaptacion.getCodigo());
                    nuevo.setIdComponente(lis.getCodigo());
                    nuevo.setCodigoAutoridad(this.nuevaCaptacion.getCodigoAutoridad());
                    this.nuevaCaptacion.getRurtCaptacionComponentesList().add(nuevo);
                    //uad.createCaptacionComponente(nuevo);
                }
            }
            
            this.nuevaCaptacion=uad.createCaptacion(this.nuevaCaptacion);
            FacesUtils.setInSession("captacionSeleccionada", this.nuevaCaptacion); 
            
            try{
                
                /** 
                 * Estas variables son necesarias para llenar los datos intrinsecos de la auditoria.
                 */
                // UsuarioConectadoTO usuarioConectado = (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
                
                Auditoria auditoria = new Auditoria();
                auditoria.setIdUsuario(usuarioConectado.getUsuario().getCodigo());
                auditoria.setOperacion("GUARDAR");
                auditoria.setObjeto("CAPTACION");
                auditoria.setMetodo(Thread.currentThread().getStackTrace()[1].getMethodName());
                auditoria.setClase(this.getClass().getName());
                //Objeto Afectado (usuario modificado, eliminado o agregado) 
                auditoria.setIdObjeto(new Long(this.nuevaCaptacion.getCodigo()));
               
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
    
    
    public void setNuevaCaptacion(Captacion nuevaCaptacion) {
        this.nuevaCaptacion = nuevaCaptacion;
    }

    public Captacion getNuevaCaptacion() {
        return nuevaCaptacion;
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

    public void setListaTipos(List listaTipos) {
        this.listaTipos = listaTipos;
    }

    public List getListaTipos() {
        return listaTipos;
    }
    
    public void setListaTiposFuente(List listaTiposFuente) {
        this.listaTiposFuente = listaTiposFuente;
    }

    public List getListaTiposFuente() {
        return listaTiposFuente;
    }
    
    public void setListaAreaPunto(List listaAreaPunto) {
        this.listaAreaPunto = listaAreaPunto;
    }

    public List getListaAreaPunto() {
        return listaAreaPunto;
    }

    public void setListaZonaPunto(List listaZonaPunto) {
        this.listaZonaPunto = listaZonaPunto;
    }

    public List getListaZonaPunto() {
        return listaZonaPunto;
    }

    public void setListaSubzonaPunto(List listaSubzonaPunto) {
        this.listaSubzonaPunto = listaSubzonaPunto;
    }

    public List getListaSubzonaPunto() {
        return listaSubzonaPunto;
    }
    
    public void setListaTipoCentroPoblado(List listaTipoCentroPoblado) {
        this.listaTipoCentroPoblado = listaTipoCentroPoblado;
    }

    public List getListaTipoCentroPoblado() {
        return listaTipoCentroPoblado;
    }
    
    public void setListaSistemasReferencia(List listaSistemasReferencia) {
        this.listaSistemasReferencia = listaSistemasReferencia;
    }

    public List getListaSistemasReferencia() {
        return listaSistemasReferencia;
    }
    
    public void setListaTipoPunto(List listaTipoPunto) {
        this.listaTipoPunto = listaTipoPunto;
    }

    public List getListaTipoPunto() {
        return listaTipoPunto;
    }

    public void setListaTipoCaptacion(List listaTipoCaptacion) {
        this.listaTipoCaptacion = listaTipoCaptacion;
    }

    public List getListaTipoCaptacion() {
        return listaTipoCaptacion;
    }
    
    public void setListaEstadoCaptacion(List listaEstadoCaptacion) {
        this.listaEstadoCaptacion = listaEstadoCaptacion;
    }

    public List getListaEstadoCaptacion() {
        return listaEstadoCaptacion;
    }
    
    public void setListaContinuidad(List listaContinuidad) {
        this.listaContinuidad = listaContinuidad;
    }

    public List getListaContinuidad() {
        return listaContinuidad;
    }
    
    public void setListaComponentes(List listaComponentes) {
        this.listaComponentes = listaComponentes;
    }

    public List getListaComponentes() {
        return listaComponentes;
    }
    
    public void setListaMetodoExtraccion(List listaMetodoExtraccion) {
        this.listaMetodoExtraccion = listaMetodoExtraccion;
    }

    public List getListaMetodoExtraccion() {
        return listaMetodoExtraccion;
    }

    public boolean isSuperficial() {
        if( this.soc_tipo_fuente.getValue()!=null &&
                ((Lista)this.soc_tipo_fuente.getValue()).getCodigo().longValue()==
                ConstantesParametros.LISTA_AGUAS_SUPERFICIALES){
            return true;
        }else{
            return false;    
        }
        //return superficial;
    }

    public boolean isSubterranea() {
        if(  this.soc_tipo_fuente.getValue()!=null &&
                ((Lista)this.soc_tipo_fuente.getValue()).getCodigo().longValue()==
                ConstantesParametros.LISTA_AGUAS_SUBTERRANEAS){
            return true;
        }else{
            return false;    
        }
        //return subterranea;
    }

    public boolean isLluvia() {
        if(  this.soc_tipo_fuente.getValue()!=null &&
                ((Lista)this.soc_tipo_fuente.getValue()).getCodigo().longValue()==
                ConstantesParametros.LISTA_AGUAS_LLUVIAS){
            return true;
        }else{
            return false;    
        }
        //return lluvia;
    }


    public boolean isMineral() {
        if(  this.soc_tipo_fuente.getValue()!=null &&
                ((Lista)this.soc_tipo_fuente.getValue()).getCodigo().longValue()==
                ConstantesParametros.LISTA_AGUAS_MINERALES){
            return true;
        }else{
            return false;    
        }
        //return mineral;
    }

    public boolean isServida() {
        if(  this.soc_tipo_fuente.getValue()!=null &&
                ((Lista)this.soc_tipo_fuente.getValue()).getCodigo().longValue()==
                ConstantesParametros.LISTA_AGUAS_SERVIDAS){
            return true;
        }else{
            return false;    
        }
        //return servida;
    }
    

    public void setMensajePanel2(String mensajePanel2) {
        this.mensajePanel2 = mensajePanel2;
    }

    public String getMensajePanel2() {
        return mensajePanel2;
    }

    public void setMensajePanel3(String mensajePanel3) {
        this.mensajePanel3 = mensajePanel3;
    }

    public String getMensajePanel3() {
        return mensajePanel3;
    }

    public void setMensajePanel4(String mensajePanel4) {
        this.mensajePanel4 = mensajePanel4;
    }

    public String getMensajePanel4() {
        return mensajePanel4;
    }

    public void setMensajePanel5(String mensajePanel5) {
        this.mensajePanel5 = mensajePanel5;
    }

    public String getMensajePanel5() {
        return mensajePanel5;
    }

    public void setMensajePanel6(String mensajePanel6) {
        this.mensajePanel6 = mensajePanel6;
    }

    public String getMensajePanel6() {
        return mensajePanel6;
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

    public void setCbNext1(RichCommandButton commandButton1) {
        this.cbNext1 = commandButton1;
    }

    public RichCommandButton getCbNext1() {
        return cbNext1;
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

    public void setSoc_tipo_fuente(RichSelectOneChoice selectOneChoice1) {
        this.soc_tipo_fuente = selectOneChoice1;
    }

    public RichSelectOneChoice getSoc_tipo_fuente() {
        return soc_tipo_fuente;
    }

    public void setSi_tipo_fuente(UISelectItems selectItems1) {
        this.si_tipo_fuente = selectItems1;
    }

    public UISelectItems getSi_tipo_fuente() {
        return si_tipo_fuente;
    }

    public void setSoc_area(RichSelectOneChoice selectOneChoice1) {
        this.soc_area = selectOneChoice1;
    }

    public RichSelectOneChoice getSoc_area() {
        return soc_area;
    }

    public void setSi_area(UISelectItems selectItems1) {
        this.si_area = selectItems1;
    }

    public UISelectItems getSi_area() {
        return si_area;
    }

    public void setSoc_zona(RichSelectOneChoice selectOneChoice1) {
        this.soc_zona = selectOneChoice1;
    }

    public RichSelectOneChoice getSoc_zona() {
        return soc_zona;
    }

    public void setSi_zona(UISelectItems selectItems1) {
        this.si_zona = selectItems1;
    }

    public UISelectItems getSi_zona() {
        return si_zona;
    }

    public void setSoc_subzona(RichSelectOneChoice selectOneChoice1) {
        this.soc_subzona = selectOneChoice1;
    }

    public RichSelectOneChoice getSoc_subzona() {
        return soc_subzona;
    }

    public void setSi_subzona(UISelectItems selectItems1) {
        this.si_subzona = selectItems1;
    }

    public UISelectItems getSi_subzona() {
        return si_subzona;
    }

    public void setSocFuente(RichSelectOneChoice selectOneChoice1) {
        this.socFuente = selectOneChoice1;
    }

    public RichSelectOneChoice getSocFuente() {
        return socFuente;
    }

    public void setSiFuentes(UISelectItems selectItems1) {
        this.siFuentes = selectItems1;
    }

    public UISelectItems getSiFuentes() {
        return siFuentes;
    }

    public void setSocTramo(RichSelectOneChoice selectOneChoice1) {
        this.socTramo = selectOneChoice1;
    }

    public RichSelectOneChoice getSocTramo() {
        return socTramo;
    }

    public void setSiTramos(UISelectItems selectItems1) {
        this.siTramos = selectItems1;
    }

    public UISelectItems getSiTramos() {
        return siTramos;
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
    
    public void setListaProvincias(List listaProvincias) {
        this.listaProvincias = listaProvincias;
    }

    public List getListaProvincias() {
        return listaProvincias;
    }

    public void setForm2(RichForm form2) {
        this.form2 = form2;
    }

    public RichForm getForm2() {
        return form2;
    }

    public void setDocument2(RichDocument document2) {
        this.document2 = document2;
    }

    public RichDocument getDocument2() {
        return document2;
    }

    public void setPanelStretchLayout3(RichPanelStretchLayout panelStretchLayout3) {
        this.panelStretchLayout3 = panelStretchLayout3;
    }

    public RichPanelStretchLayout getPanelStretchLayout3() {
        return panelStretchLayout3;
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

    public void setPanelBox3(RichPanelBox panelBox3) {
        this.panelBox3 = panelBox3;
    }

    public RichPanelBox getPanelBox3() {
        return panelBox3;
    }

    public void setPanelBox4(RichPanelBox panelBox4) {
        this.panelBox4 = panelBox4;
    }

    public RichPanelBox getPanelBox4() {
        return panelBox4;
    }

    public void setPanelBox5(RichPanelBox panelBox5) {
        this.panelBox5 = panelBox5;
    }

    public RichPanelBox getPanelBox5() {
        return panelBox5;
    }

    public void setPanelBox6(RichPanelBox panelBox6) {
        this.panelBox6 = panelBox6;
    }

    public RichPanelBox getPanelBox6() {
        return panelBox6;
    }

    public void setPanelStretchLayout4(RichPanelStretchLayout panelStretchLayout4) {
        this.panelStretchLayout4 = panelStretchLayout4;
    }

    public RichPanelStretchLayout getPanelStretchLayout4() {
        return panelStretchLayout4;
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

    public void setCbNextDatosBasicos(RichCommandButton commandButton1) {
        this.cbNextDatosBasicos = commandButton1;
    }

    public RichCommandButton getCbNextDatosBasicos() {
        return cbNextDatosBasicos;
    }

    public void setPanelFormLayout2(RichPanelFormLayout panelFormLayout2) {
        this.panelFormLayout2 = panelFormLayout2;
    }

    public RichPanelFormLayout getPanelFormLayout2() {
        return panelFormLayout2;
    }

    public void setSpacer2(RichSpacer spacer2) {
        this.spacer2 = spacer2;
    }

    public RichSpacer getSpacer2() {
        return spacer2;
    }

    public void setOutputText1(RichOutputText outputText1) {
        this.outputText1 = outputText1;
    }

    public RichOutputText getOutputText1() {
        return outputText1;
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


    public void setSpacer4(RichSpacer spacer4) {
        this.spacer4 = spacer4;
    }

    public RichSpacer getSpacer4() {
        return spacer4;
    }


    public void setSocUnidadHidro(RichSelectOneChoice selectManyChoice1) {
        this.socUnidadHidro = selectManyChoice1;
    }

    public RichSelectOneChoice getSocUnidadHidro() {
        return socUnidadHidro;
    }

    public void setSiUnidadHidro(UISelectItems selectItems2) {
        this.siUnidadHidro = selectItems2;
    }

    public UISelectItems getSiUnidadHidro() {
        return siUnidadHidro;
    }


    public void setPanelStretchLayout5(RichPanelStretchLayout panelStretchLayout5) {
        this.panelStretchLayout5 = panelStretchLayout5;
    }

    public RichPanelStretchLayout getPanelStretchLayout5() {
        return panelStretchLayout5;
    }

    public void setPanelStretchLayout6(RichPanelStretchLayout panelStretchLayout6) {
        this.panelStretchLayout6 = panelStretchLayout6;
    }

    public RichPanelStretchLayout getPanelStretchLayout6() {
        return panelStretchLayout6;
    }

    public void setPanelStretchLayout7(RichPanelStretchLayout panelStretchLayout7) {
        this.panelStretchLayout7 = panelStretchLayout7;
    }

    public RichPanelStretchLayout getPanelStretchLayout7() {
        return panelStretchLayout7;
    }

    public void setPanelStretchLayout8(RichPanelStretchLayout panelStretchLayout8) {
        this.panelStretchLayout8 = panelStretchLayout8;
    }

    public RichPanelStretchLayout getPanelStretchLayout8() {
        return panelStretchLayout8;
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

    public void setCbPrevUbicacion(RichCommandButton commandButton1) {
        this.cbPrevUbicacion = commandButton1;
    }

    public RichCommandButton getCbPrevUbicacion() {
        return cbPrevUbicacion;
    }

    public void setSpacer5(RichSpacer spacer5) {
        this.spacer5 = spacer5;
    }

    public RichSpacer getSpacer5() {
        return spacer5;
    }

    public void setCbNextUbicacion(RichCommandButton commandButton1) {
        this.cbNextUbicacion = commandButton1;
    }

    public RichCommandButton getCbNextUbicacion() {
        return cbNextUbicacion;
    }

    public void setSpacer6(RichSpacer spacer6) {
        this.spacer6 = spacer6;
    }

    public RichSpacer getSpacer6() {
        return spacer6;
    }

    public void setPanelGroupLayout10(RichPanelGroupLayout panelGroupLayout10) {
        this.panelGroupLayout10 = panelGroupLayout10;
    }

    public RichPanelGroupLayout getPanelGroupLayout10() {
        return panelGroupLayout10;
    }

    public void setPanelFormLayout3(RichPanelFormLayout panelFormLayout3) {
        this.panelFormLayout3 = panelFormLayout3;
    }

    public RichPanelFormLayout getPanelFormLayout3() {
        return panelFormLayout3;
    }

    public void setSocDepartamento(RichSelectOneChoice selectOneChoice1) {
        this.socDepartamento = selectOneChoice1;
    }

    public RichSelectOneChoice getSocDepartamento() {
        return socDepartamento;
    }

    public void setSiDepartamento(UISelectItems selectItems1) {
        this.siDepartamento = selectItems1;
    }

    public UISelectItems getSiDepartamento() {
        return siDepartamento;
    }

    public void setSocMunicipio(RichSelectOneChoice selectOneChoice1) {
        this.socMunicipio = selectOneChoice1;
    }

    public RichSelectOneChoice getSocMunicipio() {
        return socMunicipio;
    }

    public void setSiMunicipio(UISelectItems selectItems1) {
        this.siMunicipio = selectItems1;
    }

    public UISelectItems getSiMunicipio() {
        return siMunicipio;
    }

    public void setSocTipoCP(RichSelectOneChoice selectOneChoice1) {
        this.socTipoCP = selectOneChoice1;
    }

    public RichSelectOneChoice getSocTipoCP() {
        return socTipoCP;
    }

    public void setSiTipoCP(UISelectItems selectItems1) {
        this.siTipoCP = selectItems1;
    }

    public UISelectItems getSiTipoCP() {
        return siTipoCP;
    }


    public void setItNombreCentroPoblado(RichInputText inputText1) {
        this.itNombreCentroPoblado = inputText1;
    }

    public RichInputText getItNombreCentroPoblado() {
        return itNombreCentroPoblado;
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

    public void setPanelGroupLayout13(RichPanelGroupLayout panelGroupLayout13) {
        this.panelGroupLayout13 = panelGroupLayout13;
    }

    public RichPanelGroupLayout getPanelGroupLayout13() {
        return panelGroupLayout13;
    }

    public void setPanelGroupLayout14(RichPanelGroupLayout panelGroupLayout14) {
        this.panelGroupLayout14 = panelGroupLayout14;
    }

    public RichPanelGroupLayout getPanelGroupLayout14() {
        return panelGroupLayout14;
    }

    public void setPanelGroupLayout15(RichPanelGroupLayout panelGroupLayout15) {
        this.panelGroupLayout15 = panelGroupLayout15;
    }

    public RichPanelGroupLayout getPanelGroupLayout15() {
        return panelGroupLayout15;
    }

    public void setPanelGroupLayout16(RichPanelGroupLayout panelGroupLayout16) {
        this.panelGroupLayout16 = panelGroupLayout16;
    }

    public RichPanelGroupLayout getPanelGroupLayout16() {
        return panelGroupLayout16;
    }

    public void setCbPrevComponentes(RichCommandButton commandButton1) {
        this.cbPrevComponentes = commandButton1;
    }

    public RichCommandButton getCbPrevComponentes() {
        return cbPrevComponentes;
    }

    public void setSpacer7(RichSpacer spacer7) {
        this.spacer7 = spacer7;
    }

    public RichSpacer getSpacer7() {
        return spacer7;
    }

    public void setCbNextComponentes(RichCommandButton commandButton2) {
        this.cbNextComponentes = commandButton2;
    }

    public RichCommandButton getCbNextComponentes() {
        return cbNextComponentes;
    }

    public void setSpacer8(RichSpacer spacer8) {
        this.spacer8 = spacer8;
    }

    public RichSpacer getSpacer8() {
        return spacer8;
    }

    public void setCbPrevDescripcion(RichCommandButton commandButton1) {
        this.cbPrevDescripcion = commandButton1;
    }

    public RichCommandButton getCbPrevDescripcion() {
        return cbPrevDescripcion;
    }

    public void setSpacer9(RichSpacer spacer9) {
        this.spacer9 = spacer9;
    }

    public RichSpacer getSpacer9() {
        return spacer9;
    }

    public void setCbNextDescripcion(RichCommandButton commandButton1) {
        this.cbNextDescripcion = commandButton1;
    }

    public RichCommandButton getCbNextDescripcion() {
        return cbNextDescripcion;
    }

    public void setSpacer10(RichSpacer spacer10) {
        this.spacer10 = spacer10;
    }

    public RichSpacer getSpacer10() {
        return spacer10;
    }

    public void setCbPrevGeoreferencia(RichCommandButton commandButton1) {
        this.cbPrevGeoreferencia = commandButton1;
    }

    public RichCommandButton getCbPrevGeoreferencia() {
        return cbPrevGeoreferencia;
    }

    public void setSpacer11(RichSpacer spacer11) {
        this.spacer11 = spacer11;
    }

    public RichSpacer getSpacer11() {
        return spacer11;
    }


    public void setCbNextGeoreferencia(RichCommandButton commandButton1) {
        this.cbNextGeoreferencia = commandButton1;
    }

    public RichCommandButton getCbNextGeoreferencia() {
        return cbNextGeoreferencia;
    }

    public void setSpacer12(RichSpacer spacer12) {
        this.spacer12 = spacer12;
    }

    public RichSpacer getSpacer12() {
        return spacer12;
    }

    public void setPanelGroupLayout17(RichPanelGroupLayout panelGroupLayout17) {
        this.panelGroupLayout17 = panelGroupLayout17;
    }

    public RichPanelGroupLayout getPanelGroupLayout17() {
        return panelGroupLayout17;
    }

    public void setPanelFormLayout4(RichPanelFormLayout panelFormLayout4) {
        this.panelFormLayout4 = panelFormLayout4;
    }

    public RichPanelFormLayout getPanelFormLayout4() {
        return panelFormLayout4;
    }

    public void setPanelGroupLayout18(RichPanelGroupLayout panelGroupLayout18) {
        this.panelGroupLayout18 = panelGroupLayout18;
    }

    public RichPanelGroupLayout getPanelGroupLayout18() {
        return panelGroupLayout18;
    }

    public void setPanelFormLayout5(RichPanelFormLayout panelFormLayout5) {
        this.panelFormLayout5 = panelFormLayout5;
    }

    public RichPanelFormLayout getPanelFormLayout5() {
        return panelFormLayout5;
    }

    public void setPanelGroupLayout19(RichPanelGroupLayout panelGroupLayout19) {
        this.panelGroupLayout19 = panelGroupLayout19;
    }

    public RichPanelGroupLayout getPanelGroupLayout19() {
        return panelGroupLayout19;
    }


    public void setPanelGroupLayout20(RichPanelGroupLayout panelGroupLayout20) {
        this.panelGroupLayout20 = panelGroupLayout20;
    }

    public RichPanelGroupLayout getPanelGroupLayout20() {
        return panelGroupLayout20;
    }

    public void setPanelFormLayout6(RichPanelFormLayout panelFormLayout6) {
        this.panelFormLayout6 = panelFormLayout6;
    }

    public RichPanelFormLayout getPanelFormLayout6() {
        return panelFormLayout6;
    }

    public void setPanelGroupLayout21(RichPanelGroupLayout panelGroupLayout21) {
        this.panelGroupLayout21 = panelGroupLayout21;
    }

    public RichPanelGroupLayout getPanelGroupLayout21() {
        return panelGroupLayout21;
    }


    public void setSocSistemaReferencia(RichSelectOneChoice selectOneChoice1) {
        this.socSistemaReferencia = selectOneChoice1;
    }

    public RichSelectOneChoice getSocSistemaReferencia() {
        return socSistemaReferencia;
    }

    public void setSiSistemaReferencia(UISelectItems selectItems1) {
        this.siSistemaReferencia = selectItems1;
    }

    public UISelectItems getSiSistemaReferencia() {
        return siSistemaReferencia;
    }

    public void setSpacer13(RichSpacer spacer13) {
        this.spacer13 = spacer13;
    }

    public RichSpacer getSpacer13() {
        return spacer13;
    }


    public void setPanelFormLayout7(RichPanelFormLayout panelFormLayout7) {
        this.panelFormLayout7 = panelFormLayout7;
    }

    public RichPanelFormLayout getPanelFormLayout7() {
        return panelFormLayout7;
    }


    public void setItGradosLat(RichInputText inputText1) {
        this.itGradosLat = inputText1;
    }

    public RichInputText getItGradosLat() {
        return itGradosLat;
    }

    public void setItMinutosLat(RichInputText inputText2) {
        this.itMinutosLat = inputText2;
    }

    public RichInputText getItMinutosLat() {
        return itMinutosLat;
    }

    public void setItSegundosLat(RichInputText inputText3) {
        this.itSegundosLat = inputText3;
    }

    public RichInputText getItSegundosLat() {
        return itSegundosLat;
    }

    public void setItAltitud(RichInputText inputText4) {
        this.itAltitud = inputText4;
    }

    public RichInputText getItAltitud() {
        return itAltitud;
    }


    public void setSocTipoPunto(RichSelectOneChoice selectOneChoice1) {
        this.socTipoPunto = selectOneChoice1;
    }

    public RichSelectOneChoice getSocTipoPunto() {
        return socTipoPunto;
    }

    public void setSiTipoPunto(UISelectItems selectItems1) {
        this.siTipoPunto = selectItems1;
    }

    public UISelectItems getSiTipoPunto() {
        return siTipoPunto;
    }

    public void setSocTipoCaptacion(RichSelectOneChoice selectOneChoice1) {
        this.socTipoCaptacion = selectOneChoice1;
    }

    public RichSelectOneChoice getSocTipoCaptacion() {
        return socTipoCaptacion;
    }

    public void setSiTipoCaptacion(UISelectItems selectItems1) {
        this.siTipoCaptacion = selectItems1;
    }

    public UISelectItems getSiTipoCaptacion() {
        return siTipoCaptacion;
    }

    public void setItOfertaEstimada(RichInputText inputText1) {
        this.itOfertaEstimada = inputText1;
    }

    public RichInputText getItOfertaEstimada() {
        return itOfertaEstimada;
    }

    public void setItOferta(RichInputText inputText1) {
        this.itOferta = inputText1;
    }

    public RichInputText getItOferta() {
        return itOferta;
    }

    public void setItAreaCaptacion(RichInputText inputText1) {
        this.itAreaCaptacion = inputText1;
    }

    public RichInputText getItAreaCaptacion() {
        return itAreaCaptacion;
    }

    public void setItCaudalVer(RichInputText inputText1) {
        this.itCaudalVer = inputText1;
    }

    public RichInputText getItCaudalVer() {
        return itCaudalVer;
    }


    public void setItDescripcionAcceso(RichInputText inputText1) {
        this.itDescripcionAcceso = inputText1;
    }

    public RichInputText getItDescripcionAcceso() {
        return itDescripcionAcceso;
    }

    public void setSbrMacromedicion1(RichSelectBooleanRadio selectBooleanRadio1) {
        this.sbrMacromedicion1 = selectBooleanRadio1;
    }

    public RichSelectBooleanRadio getSbrMacromedicion1() {
        return sbrMacromedicion1;
    }

    public void setSbrMacroMedicion2(RichSelectBooleanRadio selectBooleanRadio1) {
        this.sbrMacroMedicion2 = selectBooleanRadio1;
    }

    public RichSelectBooleanRadio getSbrMacroMedicion2() {
        return sbrMacroMedicion2;
    }

    public void setSocEstadoCaptacion(RichSelectOneChoice selectOneChoice1) {
        this.socEstadoCaptacion = selectOneChoice1;
    }

    public RichSelectOneChoice getSocEstadoCaptacion() {
        return socEstadoCaptacion;
    }

    public void setSiEstadoCaptacion(UISelectItems selectItems1) {
        this.siEstadoCaptacion = selectItems1;
    }

    public UISelectItems getSiEstadoCaptacion() {
        return siEstadoCaptacion;
    }

    public void setSocContinuidad(RichSelectOneChoice selectOneChoice1) {
        this.socContinuidad = selectOneChoice1;
    }

    public RichSelectOneChoice getSocContinuidad() {
        return socContinuidad;
    }

    public void setSiContinuidad(UISelectItems selectItems1) {
        this.siContinuidad = selectItems1;
    }

    public UISelectItems getSiContinuidad() {
        return siContinuidad;
    }


    public void setItCaudalDisegno(RichInputText inputText1) {
        this.itCaudalDisegno = inputText1;
    }

    public RichInputText getItCaudalDisegno() {
        return itCaudalDisegno;
    }

    public void setSbrServidumbre1(RichSelectBooleanRadio selectBooleanRadio2) {
        this.sbrServidumbre1 = selectBooleanRadio2;
    }

    public RichSelectBooleanRadio getSbrServidumbre1() {
        return sbrServidumbre1;
    }

    public void setSbrServidumbre2(RichSelectBooleanRadio selectBooleanRadio1) {
        this.sbrServidumbre2 = selectBooleanRadio1;
    }

    public RichSelectBooleanRadio getSbrServidumbre2() {
        return sbrServidumbre2;
    }

    public void setSpacer15(RichSpacer spacer15) {
        this.spacer15 = spacer15;
    }

    public RichSpacer getSpacer15() {
        return spacer15;
    }

    public void setSpacer16(RichSpacer spacer16) {
        this.spacer16 = spacer16;
    }

    public RichSpacer getSpacer16() {
        return spacer16;
    }

    public void setSmcComponentes(RichSelectManyCheckbox selectManyCheckbox1) {
        this.smcComponentes = selectManyCheckbox1;
    }

    public RichSelectManyCheckbox getSmcComponentes() {
        return smcComponentes;
    }

    public void setSiComponentes(UISelectItems selectItems1) {
        this.siComponentes = selectItems1;
    }

    public UISelectItems getSiComponentes() {
        return siComponentes;
    }


    public void setItOfertaTotal(RichInputText inputText1) {
        this.itOfertaTotal = inputText1;
    }

    public RichInputText getItOfertaTotal() {
        return itOfertaTotal;
    }

    public void setItOfertaDisponible(RichInputText inputText1) {
        this.itOfertaDisponible = inputText1;
    }

    public RichInputText getItOfertaDisponible() {
        return itOfertaDisponible;
    }

    public void setItOfertaSubterranea(RichInputText inputText1) {
        this.itOfertaSubterranea = inputText1;
    }

    public RichInputText getItOfertaSubterranea() {
        return itOfertaSubterranea;
    }

    public void setSocMetodoExtraccion(RichSelectOneChoice selectOneChoice1) {
        this.socMetodoExtraccion = selectOneChoice1;
    }

    public RichSelectOneChoice getSocMetodoExtraccion() {
        return socMetodoExtraccion;
    }

    public void setSiMetodoExtraccion(UISelectItems selectItems1) {
        this.siMetodoExtraccion = selectItems1;
    }

    public UISelectItems getSiMetodoExtraccion() {
        return siMetodoExtraccion;
    }


    public void setOutputText3(RichOutputText outputText3) {
        this.outputText3 = outputText3;
    }

    public RichOutputText getOutputText3() {
        return outputText3;
    }

    public void setInputText1(RichInputText inputText1) {
        this.inputText1 = inputText1;
    }

    public RichInputText getInputText1() {
        return inputText1;
    }

    public void setConcesion(Concesion concesion) {
        this.concesion = concesion;
    }

    public Concesion getConcesion() {
        return concesion;
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

    public void setCbAceptar(RichCommandButton cbAceptar) {
        this.cbAceptar = cbAceptar;
    }

    public RichCommandButton getCbAceptar() {
        return cbAceptar;
    }

    public void setPgl14(RichPanelGroupLayout pgl14) {
        this.pgl14 = pgl14;
    }


    public void cb_aceptar_actionListener(ActionEvent actionEvent) {
        this.setAceptarAction("detalleCaptacion");
    }

    
    

    public String getAceptarAction() {
        return aceptarAction;
    }

    public void setAceptarAction(String aceptarAction) {
        this.aceptarAction = aceptarAction;
    }
    public RichPanelGroupLayout getPgl14() {
        return pgl14;
    }

    public void setPsl7(RichPanelStretchLayout psl7) {
        this.psl7 = psl7;
    }

    public RichPanelStretchLayout getPsl7() {
        return psl7;
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

    public void setPanelFormLayout8(RichPanelFormLayout panelFormLayout8) {
        this.panelFormLayout8 = panelFormLayout8;
    }

    public RichPanelFormLayout getPanelFormLayout8() {
        return panelFormLayout8;
    }

    public void setPanelFormLayout9(RichPanelFormLayout panelFormLayout9) {
        this.panelFormLayout9 = panelFormLayout9;
    }

    public RichPanelFormLayout getPanelFormLayout9() {
        return panelFormLayout9;
    }


    public void setActiveOutputText1(RichActiveOutputText activeOutputText1) {
        this.activeOutputText1 = activeOutputText1;
    }

    public RichActiveOutputText getActiveOutputText1() {
        return activeOutputText1;
    }

    public void setSpacer14(RichSpacer spacer14) {
        this.spacer14 = spacer14;
    }

    public RichSpacer getSpacer14() {
        return spacer14;
    }

    public void setSpacer17(RichSpacer spacer17) {
        this.spacer17 = spacer17;
    }

    public RichSpacer getSpacer17() {
        return spacer17;
    }

    public void setOutputLabel1(RichOutputLabel outputLabel1) {
        this.outputLabel1 = outputLabel1;
    }

    public RichOutputLabel getOutputLabel1() {
        return outputLabel1;
    }

    public void setItGradosLong(RichInputText inputText2) {
        this.itGradosLong = inputText2;
    }

    public RichInputText getItGradosLong() {
        return itGradosLong;
    }

    public void setItMinutosLong(RichInputText inputText3) {
        this.itMinutosLong = inputText3;
    }

    public RichInputText getItMinutosLong() {
        return itMinutosLong;
    }

    public void setItSegundosLong(RichInputText inputText4) {
        this.itSegundosLong = inputText4;
    }

    public RichInputText getItSegundosLong() {
        return itSegundosLong;
    }

    public void setSpacer18(RichSpacer spacer18) {
        this.spacer18 = spacer18;
    }

    public RichSpacer getSpacer18() {
        return spacer18;
    }

    public void setOutputLabel2(RichOutputLabel outputLabel2) {
        this.outputLabel2 = outputLabel2;
    }

    public RichOutputLabel getOutputLabel2() {
        return outputLabel2;
    }


    public void setItIdentificadorPuntoSubt(RichInputText inputText2) {
        this.itIdentificadorPuntoSubt = inputText2;
    }

    public RichInputText getItIdentificadorPuntoSubt() {
        return itIdentificadorPuntoSubt;
    }

    public void setSocTipoFuente(RichSelectOneChoice selectOneChoice1) {
        this.socTipoFuente = selectOneChoice1;
    }

    public RichSelectOneChoice getSocTipoFuente() {
        return socTipoFuente;
    }

    public void setSiTipoFuente(UISelectItems selectItems1) {
        this.siTipoFuente = selectItems1;
    }

    public UISelectItems getSiTipoFuente() {
        return siTipoFuente;
    }


    public void setMostrarTramo(boolean mostrarTramo) {
        this.mostrarTramo = mostrarTramo;
    }

    public boolean isMostrarTramo() {
        return mostrarTramo;
    }

    public void setSbrPerteneceRed1(RichSelectBooleanRadio sbrPerteneceRed1) {
        this.sbrPerteneceRed1 = sbrPerteneceRed1;
    }

    public RichSelectBooleanRadio getSbrPerteneceRed1() {
        return sbrPerteneceRed1;
    }

    public void setSbrPerteneceRed2(RichSelectBooleanRadio sbrPerteneceRed2) {
        this.sbrPerteneceRed2 = sbrPerteneceRed2;
    }

    public RichSelectBooleanRadio getSbrPerteneceRed2() {
        return sbrPerteneceRed2;
    }

    public void setSpacer19(RichSpacer spacer19) {
        this.spacer19 = spacer19;
    }

    public RichSpacer getSpacer19() {
        return spacer19;
    }

    public void setSelect_capt(RichSelectOneChoice selectOneChoice1) {
        this.select_capt = selectOneChoice1;
    }

    public RichSelectOneChoice getSelect_capt() {
        return select_capt;
    }

    public void setSelectItems1(UISelectItems selectItems1) {
        this.selectItems1 = selectItems1;
    }

    public UISelectItems getSelectItems1() {
        return selectItems1;
    }

    public void setListaTipoCapta(List listaTipoCapta) {
        this.listaTipoCapta = listaTipoCapta;
    }

    public List getListaTipoCapta() {
        return listaTipoCapta;
    }

  public RichInputText getItObservacion() {
    return itObservacion;
  }

  public void setItObservacion(RichInputText itObservacion) {
    this.itObservacion = itObservacion;
  }
}
