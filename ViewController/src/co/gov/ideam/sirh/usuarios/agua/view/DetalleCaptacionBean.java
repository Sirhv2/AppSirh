package co.gov.ideam.sirh.usuarios.agua.view;

import co.gov.ideam.sirh.auditoria.model.Auditoria;
import co.gov.ideam.sirh.auditoria.view.AuditoriasDelegate;
import co.gov.ideam.sirh.calidad.model.PuntoMonitoreo;
import co.gov.ideam.sirh.calidad.view.CalidadDelegate;
import co.gov.ideam.sirh.fuentes.model.CriteriosBusquedaTO;
import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.fuentes.model.FnttTramo;

import co.gov.ideam.sirh.fuentes.model.FnttTrmAforo;
import co.gov.ideam.sirh.fuentes.view.FuenteDelegate;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.model.PartZonificListas;
import co.gov.ideam.sirh.parametros.view.ParametrosDelegate;

import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.usuarios.agua.model.Captacion;
import co.gov.ideam.sirh.usuarios.agua.model.Concesion;
import co.gov.ideam.sirh.usuarios.agua.model.Predio;
import co.gov.ideam.sirh.usuarios.agua.model.RurtCapAforo;
import co.gov.ideam.sirh.usuarios.agua.model.RurtCaptacionComponentes;
import co.gov.ideam.sirh.usuarios.agua.model.RurtCaptacionUso;
import co.gov.ideam.sirh.usuarios.agua.model.SubttFunias;
import co.gov.ideam.sirh.usuarios.agua.model.UsuarioAgua;
import co.gov.ideam.sirh.util.Constantes;
import co.gov.ideam.sirh.util.ConstantesParametros;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;
import co.gov.ideam.sirh.view.util.SpecialTreeModel;
import co.gov.ideam.sirh.view.util.TreeNode;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import javax.servlet.http.HttpServletRequest;

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
import oracle.adf.view.rich.component.rich.input.RichSelectManyCheckbox;
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
import oracle.adf.view.rich.component.rich.output.RichActiveOutputText;
import oracle.adf.view.rich.component.rich.output.RichImage;
import oracle.adf.view.rich.component.rich.output.RichOutputLabel;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;

import org.apache.myfaces.trinidad.model.TreeModel;

public class DetalleCaptacionBean  extends StandarBean{
    //usuario en session
    private UsuarioAgua usuario;
    //predio en session
    private Predio predio;
    //concesion en session
    private Concesion concesion;
    //Objeto al cual se le están realizando cambios
    private Captacion captacion;
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
    //lista de tipos de fuente en categoria.
    private List listaTiposFuente;
    //lista de tipos de fuente en categoria.
    private List listaTipos;
    //Lista de fuentes.
    private List listaFuentes;
    //Listado de tramos de una fuente.
    private List listaTramos;
    //Lista de provincias hidrogeologicas
    private List listaProvincias;
    //Lista de tipos de centros problados en categorias.
    private List listaTipoCentroPoblado;
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
    //Lista de componentes asociados a la captación.
    private List listaComponentesAsociados;
    //Lista de los metodos de extracción
    private List listaMetodoExtraccion;
    //atributo para saber si es fuente superficial
    private Boolean superficial;
    //atributo para saber si es fuente subterranea
    private Boolean subterranea;
    //atributo para saber si es fuente lluvia
    private Boolean lluvia;
    //atributo para saber si fuente mineral
    private Boolean mineral;
    //atributo para saber si es fuente servida
    private Boolean servida;
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

    private List<PuntoMonitoreo> listaPuntosMonitoreo;
    private RichForm form1;
    private RichDocument document1;
    private RichPanelStretchLayout panelStretchLayout1;
    private RichPanelSplitter panelSplitter1;
    private RichPanelStretchLayout panelStretchLayout3;
    private RichPanelGroupLayout panelGroupLayout1;
    private RichPanelBox panelBox1;
    private RichPopup popup_borrar;
    private RichPopup p_registro_exitoso;
    private RichPanelGroupLayout panelGroupLayout2;
    private RichPanelGroupLayout panelGroupLayout3;
    private RichDecorativeBox decorativeBox2;
    private RichDecorativeBox decorativeBox3;
    private RichDecorativeBox decorativeBox4;
    private RichDecorativeBox decorativeBox5;
    private RichDecorativeBox decorativeBox6;
    private RichPanelFormLayout panelFormLayout2;
    private RichPanelFormLayout panelFormLayout3;
    private RichPanelFormLayout panelFormLayout4;
    private RichPanelFormLayout panelFormLayout5;
    private RichSpacer spacer2;
    private RichPanelGroupLayout panelGroupLayout5;
    private RichOutputText outputText2;
    private RichPanelGroupLayout panelGroupLayout6;
    private RichOutputText outputText3;
    private RichSpacer spacer4;
    private RichPanelGroupLayout panelGroupLayout7;
    private RichOutputText outputText4;
    private RichSpacer spacer5;
    private RichPanelGroupLayout panelGroupLayout8;
    private RichOutputText outputText5;
    private RichSpacer spacer6;
    private RichPanelGroupLayout panelGroupLayout9;
    private RichOutputText outputText6;
    private RichDecorativeBox decorativeBox7;
    private RichPanelGroupLayout panelGroupLayout10;
    private RichPanelGroupLayout panelGroupLayout11;
    private RichSpacer spacer7;
    private RichCommandButton cbAceptar;
    private RichSpacer spacer8;
    private RichCommandButton cbBorrar;

    private UISelectItems si_tipo_fuente;

    private RichSelectOneChoice soc_area;
    private UISelectItems siArea;
    private RichSelectOneChoice soc_zona;
    private UISelectItems siZona;
    private RichSelectOneChoice soc_subzona;
    private UISelectItems siSubzona;
    private RichSelectOneChoice socFuente;
    private UISelectItems siFuentes;
    private RichSelectOneChoice socTramo;
    private UISelectItems siTramos;
    private RichSelectOneChoice socUnidadHidro;
    private UISelectItems siUnidadHidro;

    private RichSelectOneChoice socDepartamento;
    private UISelectItems siDepartamento;
    private RichSelectOneChoice socMunicipio;
    private UISelectItems siMunicipio;
    private RichSelectOneChoice socTipoCP;
    private UISelectItems siTipoCP;
    private RichInputText itNombreCentroPoblado;

    private RichSelectOneChoice socTipoPunto;
    private UISelectItems siTipoPunto;
    private RichSelectOneChoice socTipoCaptacion;
    private UISelectItems siTipoCaptacion;
    private RichInputText itOfertaEstimada;
    private RichInputText itOferta;
    private RichInputText itAreaCaptacion;
    private RichInputText itCaudalVer;

    private RichInputText itDescripcionAcceso;
    private RichSelectBooleanRadio sbrMacroMedicion1;
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

    private RichSelectManyCheckbox smcComponentes;
    private UISelectItems siComponentes;
    private RichInputText itOfertaTotal;
    private RichInputText itOfertaDisponible;
    private RichInputText itOfertaSubterranea;
    private RichSelectOneChoice socMetodoExtraccion;
    private UISelectItems siMetodoExtraccion;
    private RichInputText itObservacion;

    private RichPanelGroupLayout panelGroupLayout19;
    private RichPanelGroupLayout panelGroupLayout20;
    private RichPanelGroupLayout panelGroupLayout21;
    private RichPanelGroupLayout panelGroupLayout29;
    private RichSelectOneChoice socSistemaReferencia;
    private UISelectItems siSistemaReferencia;
    private RichSpacer spacer13;

    private RichPanelFormLayout panelFormLayout16;//estos son para sistema de referencia
    private RichPanelFormLayout panelFormLayout17;//punto inicial
    private RichInputText itGradosLat;
    private RichInputText itMinutosLat;
    private RichInputText itSegundosLat;
    private RichInputText itAltitud;

    private RichSpacer spacer15;
    private RichSpacer spacer16;

    ///////Borrar y guardar
    private RichPanelGroupLayout pgl14;
    private RichPanelGroupLayout pgl18;
    private RichPanelGroupLayout pgl19;

    private RichPanelStretchLayout psl7;

    private RichSpacer s15;
    private RichSpacer s16;

    private RichCommandButton cb_si_borrar;
    private RichCommandButton cb_no_borrar;
    private RichCommandButton cbAceptarDi;

    private RichDialog d_registro_exitoso;
    private RichDialog d_borrar;

    private RichOutputText ot_confirmacion_borrar;
    private RichOutputText ot_borrar_1;
    private RichOutputText ot_borrar_2;
    private RichOutputText ot8;
    private RichOutputText ot11;

    private RichSpacer spacer20;
    private RichSpacer spacer21;
    private RichSpacer spacer22;
    private RichSpacer spacer23;
    private RichSpacer spacer24;
    private RichSpacer spacer25;
    private RichCommandLink clUsuario;
    private RichCommandLink clPredio;
    private RichCommandLink clConcesiones;
    private RichCommandLink clTodos;
    private RichCommandLink clConcesion;
    private RichCommandLink clCaptaciones;


    private RichImage i1;
    private RichPanelBox pbUsos;
    private RichPanelCollection panelCollection1;
    private RichMenu menu1;
    private RichCommandMenuItem cmiAddUso;
    private RichTree tree1;
    //////
    private RichCommandLink cl2;
    private TreeModel usosTreeModel;
    private TreeModel puntosMonitoreoTreeModel;
    private RichPanelFormLayout panelFormLayout1;
    private RichPanelFormLayout panelFormLayout6;
    private RichActiveOutputText activeOutputText1;
    private RichSpacer spacer1;
    private RichSpacer spacer9;
    private RichOutputLabel outputLabel1;
    private RichInputText itGradosLong;
    private RichInputText itMinutosLong;
    private RichInputText itSegundosLong;
    private RichSpacer spacer10;
    private RichOutputLabel outputLabel2;
    private RichMenu mfunias;
    private RichCommandMenuItem cmiAddFunias;
    private RichCommandMenuItem cmiListFunias;
    private RichPanelCollection pc2;
    private RichOutputText otTipoFuente;
    private RichSpacer spacer11;
    private RichInputText itIdentificadorPuntoSubt;
    private RichCommandMenuItem cmiPuntoMonitoreo;
    private RichSelectOneChoice socTipoFuente;
    private UISelectItems siTipoFuente;
    private RichPanelCollection pnlCol1;
    private RichMenu menu11;
    private RichCommandMenuItem cmiAddUso1;
    private RichCommandMenuItem cmiAddFunias1;
    private RichCommandMenuItem cmiListFunias1;
    private RichTree tree11;
    private RichCommandLink cl21;
    private RichPanelStretchLayout panelStretchLayout2;
    private RichCommandMenuItem cmi_puntoCalidad;
    private String etiquetaFuenteSupSub="Fuente";
    private List listaTipoCapta;
    private String visibleMenu=null;
   
    private RichSelectOneChoice select_capt;
    private UISelectItems selectItemscapt;
    
    //CDONCEL
    private Boolean flagAforo = false;
    private RurtCapAforo aforo;  
    private RichDecorativeBox decorativeBoxAforo;
    private RichPanelGroupLayout panelGroupLayoutAforo;
    private RichSpacer spacerAforo;
    private RichOutputText outputTextAforo;
    private RichPanelGroupLayout panelGroupLayout2Aforo;    
    private RichPanelFormLayout panelFormLayout1Aforo;
    private RichSpacer spacer2Aforo;
    private RichTable tableAforo;
    private List<RurtCapAforo> lsAforo;
    private RichSpacer spacerAf;
    private RichPopup aforo_registro_exitoso;
    private RichPopup aforo_borrardo_exitoso;
    private RichDialog d_aforo_registro_exitoso;
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
    

    public DetalleCaptacionBean(){
        FacesUtils.setActiveBean("detalleCaptacionBean", "Detalle Captación",
                                 UsuariosAguaDelegate.class);
        //FacesUtils.removeManagedBeanFromSession("UsoTreeHandler");
        FacesUtils.removeManagedBeanFromSession("captacion");
        FacesUtils.removeManagedBeanFromSession("adicionarCaptacionBean");
        FacesUtils.removeManagedBeanFromSession("UsosTreeHandler");
        this.load();
    }

    public void load(){
        try{
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
            ParametrosDelegate pd = ParametrosDelegate.getInstance();
            FuenteDelegate fd = FuenteDelegate.getInstance();
            Object obj = FacesUtils.getFromSession("captacionSeleccionada");
            if(obj instanceof Captacion){
                this.captacion = (Captacion)obj;
            }else{
                Long codigo = (Long)FacesUtils.getFromSession("captacionSeleccionada");
                this.captacion = uad.getCaptacion(codigo);
                FacesUtils.setInSession("captacionSeleccionada", this.captacion);
            }

            Object objUsuario = FacesUtils.getFromSession("usuarioSeleccionado");
            if(objUsuario instanceof UsuarioAgua){
                this.setUsuario((UsuarioAgua)objUsuario);
            }else{
                Long codigoUsuario = (Long)FacesUtils.getFromSession("usuarioSeleccionado");
                this.setUsuario(uad.getUsuarioAgua(codigoUsuario));
                FacesUtils.setInSession("usuarioSeleccionado", this.usuario);
            }

            Object objPredio = FacesUtils.getFromSession("predioSeleccionado");
            if(objPredio instanceof Predio){
                this.setPredio((Predio)FacesUtils.getFromSession("predioSeleccionado"));
            }else{
                Long codigoPredio = (Long)FacesUtils.getFromSession("predioSeleccionado");
                this.setPredio(uad.getPredio( codigoPredio ));
                FacesUtils.setInSession("predioSeleccionado", this.predio);
            }

            Object objConcesion = FacesUtils.getFromSession("concesionSeleccionada");
            if(objConcesion instanceof Concesion){
                this.concesion = (Concesion)FacesUtils.getFromSession("concesionSeleccionada");
            }else{
                Long codigoConcesion = (Long)FacesUtils.getFromSession("concesionSeleccionada");
                this.concesion = uad.getConcesion(codigoConcesion);
                FacesUtils.setInSession("concesionSeleccionada", this.concesion);
            }

            this.captacion.setRurtCaptacionComponentesList(uad.getComponentesByCaptacion(this.captacion.getCodigo()));
            this.listaComponentesAsociados = new ArrayList();
            if(this.captacion.getRurtCaptacionComponentesList()!=null){
                Iterator it = this.captacion.getRurtCaptacionComponentesList().iterator();
                while(it.hasNext()){
                    RurtCaptacionComponentes com = (RurtCaptacionComponentes)it.next();
                    Lista lista = pd.getLista(com.getIdComponente());
                    this.listaComponentesAsociados.add(lista);
                }
            }

            UsuarioConectadoTO usuarioConectado =
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

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

            if(this.captacion.getTipoFuenteCaptacion().longValue()==
                    ConstantesParametros.LISTA_AGUAS_SUPERFICIALES){
                this.listaComponentes = this.cargarParametro(ConstantesParametros.
                                                   CATEGORIA_COMPONENTES_SUPERFICIAL);
            }else if(this.captacion.getTipoFuenteCaptacion().longValue()==
                     ConstantesParametros.LISTA_AGUAS_SUBTERRANEAS){
                this.listaComponentes = this.cargarParametro(ConstantesParametros.
                                                   CATEGORIA_COMPONENTES_SUBTERRANEA);
                
            }

            this.listaMunicipios = new ArrayList();
            this.listaZona = new ArrayList();
            this.listaSubzona = new ArrayList();
            this.listaFuentes =  new ArrayList();
            this.listaTramos =  new ArrayList();

            if(this.captacion.getIdArea()!=null){
                this.listaZona = (List<PartZonificListas>)this.cargarZonificacion(this.captacion.getIdArea().getId());
            }
            if(this.captacion.getIdZona()!=null){
                this.listaSubzona = (List<PartZonificListas>)this.cargarZonificacion(this.captacion.getIdZona().getId());
            }
            if(this.captacion.getIdDepartamento()!=null){
                this.listaMunicipios = this.cargarDivipola(this.captacion.getIdDepartamento().longValue());
                this.captacion.setObjDepartamento(pd.getDivipolaById(this.captacion.getIdDepartamento().longValue()));
            }

            if(this.captacion.getTipoFuenteCaptacion().longValue()==
                    ConstantesParametros.LISTA_AGUAS_SUPERFICIALES ){

                visibleMenu="true";
               
                CriteriosBusquedaTO criterios = new CriteriosBusquedaTO();
                criterios.setAutoridad(usuarioConectado.
                                       getUsuario().getAutoridadAmbiental());
                if(this.captacion.getIdArea()!=null){
                    criterios.setArea(this.captacion.getIdArea());
                }
                if(this.captacion.getIdZona()!=null){
                    criterios.setZona(this.captacion.getIdZona());
                }
                if(this.captacion.getIdSubzona()!=null){
                    criterios.setSubzona(this.captacion.getIdSubzona());
                }

                this.listaFuentes = this.cargarFuentes(criterios);

                criterios.setFuente(this.captacion.getIdFuente());
                this.listaTramos = this.cargarTramos(criterios);

            } else if(this.captacion.getTipoFuenteCaptacion().longValue()==
                    ConstantesParametros.LISTA_AGUAS_SUBTERRANEAS){
                this.setEtiquetaFuenteSupSub("Sistema Acuífero");
                Lista fs = pd.getLista(ConstantesParametros.
                                       LISTA_FUENTE_SUBTERRANEA.intValue());


                CriteriosBusquedaTO criterios = new CriteriosBusquedaTO();

                criterios.setAutoridad(usuarioConectado.getUsuario().getAutoridadAmbiental());
                criterios.setTipoFuente(fs);

                this.listaFuentes = this.cargarFuentes(criterios);

                criterios.setFuente(this.captacion.getIdFuente());

                this.listaTramos = this.cargarTramos(criterios);
                
                
                visibleMenu="true";
                
            }



            if(this.captacion.getIdMunicipio()!=null){
                this.captacion.setObjMunicipio(pd.getDivipolaById(this.captacion.
                                                               getIdMunicipio().longValue()));
            }

            if(this.captacion.getIdTipoCentroPoblado()!=null){
                this.captacion.setObjTipoCentroPoblado(pd.getLista(this.captacion.
                                                                   getIdTipoCentroPoblado()));
            }

            if(this.captacion.getEstadoCaptacion()!=null){
                this.captacion.setObjEstadoCaptacion(pd.getLista(this.captacion.
                                                                 getEstadoCaptacion()));
            }

            if(this.captacion.getSistemaReferencia()!=null){
                this.captacion.setObjSistemaReferencia(pd.getLista(this.captacion.
                                                                   getSistemaReferencia()));
            }

            if(this.captacion.getProvinciaHidrogeologica()!=null){
                this.captacion.setObjProvinciaHidrogeologica(pd.getLista(this.captacion.
                                                                         getProvinciaHidrogeologica()));
            }

            if(this.captacion.getTipoPunto()!=null){
                this.captacion.setObjTipoPunto(pd.getLista(this.captacion.
                                                           getTipoPunto()));
            }

            if(this.captacion.getMetodoExtraccion()!=null){
                this.captacion.setObjMetodoExtraccion(pd.getLista(this.captacion.
                                                                  getMetodoExtraccion()));
            }

            if(this.captacion.getTipoCaptacion()!=null){
                this.captacion.setObjTipoCaptacion(pd.getLista(this.captacion.
                                                               getTipoCaptacion()));
            }

            if(this.captacion.getTipoFuenteCaptacion()!=null){
                this.captacion.setObjTipoFuenteCaptacion(pd.getLista(this.captacion.
                                                                     getTipoFuenteCaptacion()));
            }

            if(this.sbrMacroMedicion1 == null){
               this.sbrMacroMedicion1 = new RichSelectBooleanRadio();
            }
            if(this.sbrMacroMedicion2 == null){
               this.sbrMacroMedicion2 = new RichSelectBooleanRadio();
            }
            

           
            if(this.sbrPerteneceRed1 == null){
               this.sbrPerteneceRed1 = new RichSelectBooleanRadio();
            }
    
            if(this.sbrPerteneceRed2 == null){
               this.sbrPerteneceRed2 = new RichSelectBooleanRadio();
            }
            
            if(this.captacion!=null){
                this.sbrMacroMedicion1.setSelected(this.captacion.
                                                   getTieneMacroMedicionAux());
                this.sbrMacroMedicion2.setSelected(!this.captacion.
                                                   getTieneMacroMedicionAux());
                this.sbrMacroMedicion1.setValue(this.captacion.
                                                getTieneMacroMedicionAux());
                this.sbrMacroMedicion2.setValue(!this.captacion.
                                                getTieneMacroMedicionAux());
                this.sbrPerteneceRed1.setSelected(this.captacion.
                                                   getRed_monitoreoAux());
                this.sbrPerteneceRed2.setSelected(!this.captacion.
                                                   getRed_monitoreoAux());
                this.sbrPerteneceRed1.setValue(this.captacion.
                                                getRed_monitoreoAux());
                this.sbrPerteneceRed2.setValue(!this.captacion.
                                                getRed_monitoreoAux());
            }

            if(this.sbrServidumbre1 == null){
               this.sbrServidumbre1 = new RichSelectBooleanRadio();
            }
            if(this.sbrServidumbre2 == null){
               this.sbrServidumbre2 = new RichSelectBooleanRadio();
            }

            if(this.captacion!=null){
                this.sbrServidumbre1.setSelected(this.captacion.getTieneServidumbreAux());
                this.sbrServidumbre2.setSelected(!this.captacion.getTieneServidumbreAux());
                this.sbrServidumbre1.setValue(this.captacion.getTieneServidumbreAux());
                this.sbrServidumbre2.setValue(!this.captacion.getTieneServidumbreAux());
            }


            this.otTipoFuente = new RichOutputText();
            if(this.captacion.getObjTipoFuenteCaptacion()!=null){
                this.otTipoFuente.setValue(this.getCaptacion().
                                           getObjTipoFuenteCaptacion().getValor());
            }

            //Construir el arbol.
            List listaNodos = new ArrayList();

            //crea el grupo de usos
            TreeNode nodoUsos = new TreeNode(getText("USO_MODULO"), "Usos");
            nodoUsos.setData("Usos");
            listaNodos.add(nodoUsos);//adiciona el grupo
            this.captacion.setRurtCaptacionUsoList(uad.getUsos(this.captacion));
            if(this.captacion.getRurtCaptacionUsoList()!=null){
                for(RurtCaptacionUso uso : this.captacion.getRurtCaptacionUsoList()){
                    Lista lista = pd.getLista(uso.getTipoUso());
                    TreeNode nodoUso = new TreeNode(getText("USO_USO") + " " +
                                                        lista.getValor(),
                                                       "Uso", true, false);
                    nodoUso.setData(uso);
                    nodoUsos.getChildren().add(nodoUso);
                }
            }else{
                TreeNode nodoUso = new TreeNode(getText("NO_HAY_REGISTROS"),
                                                    "Uso", false, true);
                nodoUsos.getChildren().add(nodoUso);
            }


            //Informacion del funias
            if(this.getSubterranea()){
                TreeNode nodoFunias = new TreeNode(getText("FUNIAS_MODULO"), "Funias");
                nodoFunias.setData("Funias");
                listaNodos.add(nodoFunias);//adiciona el grupo
                this.captacion.setSubttFuniasList(uad.getFuniasByCaptacion(this.captacion));
                if(this.captacion.getSubttFuniasList()!=null){
                    for(SubttFunias funia : this.captacion.getSubttFuniasList()){
                        Lista lista = pd.getLista(funia.getTipoFunias());
                        TreeNode nodoFunia = new TreeNode(getText("FUNIAS_MODULO") + " " +
                                                            lista.getValor(),
                                                           "Funias", true, false);
                        nodoFunia.setData(funia);
                        nodoFunias.getChildren().add(nodoFunia);
                    }
                }
            }


            usosTreeModel = new SpecialTreeModel(listaNodos, "children");


            if(this.captacion.getTipoFuenteCaptacion().longValue()==
                    ConstantesParametros.LISTA_AGUAS_SUPERFICIALES || this.captacion.getTipoFuenteCaptacion().longValue()==
                    ConstantesParametros.LISTA_AGUAS_SUBTERRANEAS ){
           
           
            //Construir el arbol puntos.
          
            CalidadDelegate cald = CalidadDelegate.getInstance();
            listaPuntosMonitoreo  = cald.getPuntosMonitoreoCaptacion(this.captacion);

            this.captacion.setListaPuntosMonitoreoCapt(listaPuntosMonitoreo);
                
                

            TreeNode nodoPuntos = new TreeNode("Calidad",
                                               "PuntosMonitoreoCapt");
            nodoPuntos.setData("PuntosMonitoreoCapt");
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
       }

        }catch(IdeamException e){
            showMessage(e);
        }
    }

    ////////////////////////////////////

    public void cl2_actionListener(ActionEvent actionEvent) {
        String nombreParametro =
            (String)actionEvent.getComponent().getAttributes().get("nombreParametro");
        Object data =
            actionEvent.getComponent().getAttributes().get("valorParametro");
        if(nombreParametro!=null && data != null){
            FacesUtils.setInSession(nombreParametro, data);
        }
    }

    public void soc_area_valueChangeListener(ValueChangeEvent event) throws IdeamException{
        Object area = event.getNewValue();
        this.listaZona = new ArrayList();
        this.listaSubzona = new ArrayList();
        if(this.captacion.getTipoFuenteCaptacion().longValue()!=
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
        if(this.captacion.getTipoFuenteCaptacion().longValue()!=
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
        if(this.captacion.getTipoFuenteCaptacion().longValue()!=
                    ConstantesParametros.LISTA_AGUAS_SUBTERRANEAS){
            this.listaFuentes =  new ArrayList();
            this.listaTramos =  new ArrayList();
        }
        try{
            if(subzona!=null && this.captacion.getTipoFuenteCaptacion()!=null){
               if(this.captacion.getTipoFuenteCaptacion().longValue()==
                        ConstantesParametros.LISTA_AGUAS_SUPERFICIALES){

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

                    this.listaFuentes = this.cargarFuentes(criterios);
                }
            }
        }catch(IdeamException e){
            showMessage(e);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.socFuente);
    }

    public void socTipoFuente_valueChangeListener(ValueChangeEvent event) {
        Object tipoFuente = event.getNewValue();
        if(this.captacion.getTipoFuenteCaptacion().longValue()!=
                    ConstantesParametros.LISTA_AGUAS_SUBTERRANEAS){
            this.listaFuentes =  new ArrayList();
            this.listaTramos =  new ArrayList();
        }
        try{
            if(this.captacion.getTipoFuenteCaptacion().
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
            if(fuente!=null && this.captacion.getTipoFuenteCaptacion()!=null){
               if(this.captacion.getTipoFuenteCaptacion().longValue()==ConstantesParametros.LISTA_AGUAS_SUPERFICIALES
                        || this.captacion.getTipoFuenteCaptacion().longValue()==ConstantesParametros.LISTA_AGUAS_SUBTERRANEAS){
                   CriteriosBusquedaTO criterios = new CriteriosBusquedaTO();
                   UsuarioConectadoTO usuarioConectado =
                       (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

                   criterios.setAutoridad(usuarioConectado.getUsuario().getAutoridadAmbiental());
                   if(this.captacion.getTipoFuenteCaptacion().longValue()==ConstantesParametros.LISTA_AGUAS_SUPERFICIALES){
                       if(this.soc_area.getValue()!=null){
                           criterios.setArea((PartZonificListas)this.soc_area.getValue());
                       }
                       if(this.soc_zona.getValue()!=null){
                           criterios.setZona((PartZonificListas)this.soc_zona.getValue());
                       }
                       if(this.soc_subzona.getValue()!=null){
                           criterios.setSubzona((PartZonificListas)this.soc_subzona.getValue());
                       }
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


    public void cbBorrar_actionListener(ActionEvent actionEvent) {
        String mensaje = this.captacion.getGradosLat()+"\u00B0 " +
                         this.captacion.getMinutosLat() + "\'\' " +
                         this.captacion.getSegundosLat()+"\'" ;

        String params[] = {mensaje};
        String texto = getText("CPT_BORRAR", params);
        ot_borrar_1.setValue(texto);
        ot_borrar_2.setValue("");

        UsuarioConectadoTO usuarioConectado =
            (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

        Autoridades autoridadAmbiental = usuarioConectado.getUsuario().getAutoridadAmbiental();
        if(autoridadAmbiental==null ||
                autoridadAmbiental.getId().intValue() == Constantes.IDEAM){
            showMessage(getText("NO_EXISTE_AUTORIDAD_AMBIENTAL"),
                        FacesMessage.SEVERITY_ERROR);
        }else{
            if(this.captacion.getRurtCaptacionUsoList()!=null){
                this.ot_borrar_2.setValue(getText("CPT_SI_USOS"));
            }
            AdfFacesContext.getCurrentInstance().addPartialTarget(ot_borrar_1);
            AdfFacesContext.getCurrentInstance().addPartialTarget(ot_borrar_2);
            AdfFacesContext.getCurrentInstance().addPartialTarget(popup_borrar);
            showPopup(popup_borrar, true);
        }
    }
    public void cb_guardarAceptar_actionListener(ActionEvent actionEvent) {
        showPopup(p_registro_exitoso, false);
    }

    public void cb_no_borrar_actionListener(ActionEvent actionEvent) {
        showPopup(popup_borrar, false);
    }

    public void cb_si_borrar_actionListener(ActionEvent actionEvent) {
        try{
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
            uad.borrarCaptacion(this.captacion);
            
            
            try{
                
                /** 
                 * Estas variables son necesarias para llenar los datos intrinsecos de la auditoria.
                 */
                 UsuarioConectadoTO usuarioConectado = (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
                
                Auditoria auditoria = new Auditoria();
                auditoria.setIdUsuario(usuarioConectado.getUsuario().getCodigo());
                auditoria.setOperacion("BORRAR");
                auditoria.setObjeto("CAPTACION");
                auditoria.setMetodo(Thread.currentThread().getStackTrace()[1].getMethodName());
                auditoria.setClase(this.getClass().getName());
                //Objeto Afectado (usuario modificado, eliminado o agregado) 
                auditoria.setIdObjeto(this.captacion.getCodigo());
               
                AuditoriasDelegate audDelegate = AuditoriasDelegate.getInstance();
                audDelegate.setAuditoria(auditoria);
                
            }catch(Exception e){
                System.out.println("No se pudo Almacenar la auditoria: ---------------------------------- ");
                System.out.println(e.getMessage()+"------------------------------------------------------ ");
            }
            
            
            
            
            
            showMessage(getText("CPT_ELIMINADA"));
        }catch(IdeamException e){
            showMessage(e);
        }
    }

    public void cbAceptar_actionListener(ActionEvent actionEvent) {
        System.out.println("-----------DETALLE CAPTACION BEAN-cbAceptar_actionListener------------------------------------------ ");
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

        //realizar validaciones correspondientes


        //validaciones datos básicos

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
       // modificacion Dic 2014, para fuentes subterraneas no se solicita el tramo
        if(this.captacion.getTipoFuenteCaptacion().longValue()==
                ConstantesParametros.LISTA_AGUAS_SUBTERRANEAS){

            if(this.socFuente.getValue()==null){
                showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.socFuente);
                continuar = false;
            }
            
        }
        if( this.captacion.getTipoFuenteCaptacion().longValue()==
                ConstantesParametros.LISTA_AGUAS_SUPERFICIALES){

            if(this.socFuente.getValue()==null){
                showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.socFuente);
                continuar = false;
            }
            if(this.socTramo.getValue()==null){
                showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.socTramo);
                continuar = false;
            }
        }

     

        //validaciones de ubicación
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

        if(this.captacion.getTipoFuenteCaptacion().longValue()==ConstantesParametros.LISTA_AGUAS_LLUVIAS){
            if(this.socTipoCaptacion.getValue()==null){
                showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.socTipoCaptacion);
                continuar = false;
            }
        }

        if(this.captacion.getTipoFuenteCaptacion().longValue()==ConstantesParametros.LISTA_AGUAS_SUBTERRANEAS){
            if(this.itIdentificadorPuntoSubt.getValue()==null){
                showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.itIdentificadorPuntoSubt);
                continuar = false;
            }
        }

        //validaciones de georeferenciación
        System.out.println("-----pregunta el sistema de referenica----"+continuar);
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
        System.out.println("-----pregunta por la itSegundosLong----"+continuar);
        if(this.itSegundosLong.getValue()==null ||
           this.itSegundosLong.getValue().toString().length()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.itSegundosLong);
            continuar = false;
        }
        System.out.println("-----pregunta por la altitud----"+continuar);
        if(this.itAltitud.getValue()==null ||
           this.itAltitud.getValue().toString().length()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.itAltitud);
            continuar = false;
        }
        System.out.println("-----pregunta por itDescripcionAcceso----"+continuar);
        if(this.itDescripcionAcceso.getValue()==null ||
           this.itDescripcionAcceso.getValue().toString().length()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.itDescripcionAcceso);
            continuar = false;
        }
        System.out.println("----continuar:"+continuar+"----");
        ///////

        if(continuar){
            this.save();
            
            try{
                
                /** 
                 * Estas variables son necesarias para llenar los datos intrinsecos de la auditoria.
                 */
                // UsuarioConectadoTO usuarioConectado = (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
                
                Auditoria auditoria = new Auditoria();
                auditoria.setIdUsuario(usuarioConectado.getUsuario().getCodigo());
                auditoria.setOperacion("MODIFICAR");
                auditoria.setObjeto("CAPTACION");
                auditoria.setMetodo(Thread.currentThread().getStackTrace()[1].getMethodName());
                auditoria.setClase(this.getClass().getName());
                //Objeto Afectado (usuario modificado, eliminado o agregado) 
                auditoria.setIdObjeto(new Long(this.captacion.getCodigo()));
               
                AuditoriasDelegate audDelegate = AuditoriasDelegate.getInstance();
                audDelegate.setAuditoria(auditoria);
                
            }catch(Exception e){
                System.out.println("No se pudo Almacenar la auditoria: ---------------------------------- ");
                System.out.println(e.getMessage()+"------------------------------------------------------ ");
            }
            
            
            
            
            
            showPopup(p_registro_exitoso, true);
        }
    }

    public void save(){
        try{
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
            FuenteDelegate fd=FuenteDelegate.getInstance();
            this.captacion.setAreaCaptacion((this.itAreaCaptacion.getValue()!=null)?Double.parseDouble(this.itAreaCaptacion.getValue().toString()):null);
            this.captacion.setCaudalDisegno((this.itCaudalDisegno.getValue()!=null)?Double.parseDouble(this.itCaudalDisegno.getValue().toString()):null);

            this.captacion.setContinuidad((this.socContinuidad.getValue()!=null)?this.socContinuidad.getValue().toString():null);
            this.captacion.setDescripcionAcceso(this.itDescripcionAcceso.getValue().toString());
            this.captacion.setEstadoCaptacion((this.socEstadoCaptacion.getValue()!=null)?((Lista)this.socEstadoCaptacion.getValue()).getCodigo():null);
            System.out.println("----save setGradosLat----");
            this.captacion.setGradosLat(Integer.parseInt(this.itGradosLat.getValue().toString()));
            this.captacion.setMinutosLat(Integer.parseInt(this.itMinutosLat.getValue().toString()));
            this.captacion.setSegundosLat(Double.parseDouble(this.itSegundosLat.getValue().toString()));
            this.captacion.setGradosLong(Integer.parseInt(this.itGradosLong.getValue().toString()));
            this.captacion.setMinutosLong(Integer.parseInt(this.itMinutosLong.getValue().toString()));
            this.captacion.setSegundosLong(Double.parseDouble(this.itSegundosLong.getValue().toString()));

            this.captacion.setAltitud(Double.parseDouble(this.itAltitud.getValue().toString()));
            this.captacion.setSistemaReferencia(((Lista)this.socSistemaReferencia.getValue()).getCodigo());
            System.out.println("----save setSistemaReferencia----");
            this.captacion.setIdArea((this.soc_area.getValue()!=null)?(PartZonificListas)this.soc_area.getValue():null);
            this.captacion.setIdZona((this.soc_zona.getValue()!=null)?(PartZonificListas)this.soc_zona.getValue():null);
            this.captacion.setIdSubzona((this.soc_subzona.getValue()!=null)?(PartZonificListas)this.soc_subzona.getValue():null);
            this.captacion.setIdFuente((this.socFuente.getValue()!=null)?(FnttFuente)this.socFuente.getValue():null);
            if( this.captacion.getTipoFuenteCaptacion().longValue()==
                    ConstantesParametros.LISTA_AGUAS_SUBTERRANEAS  ){
                 List lTrSub=fd.getAllTramosByFuente(this.captacion.getIdFuente());
                 if(lTrSub.size()>0){
                     FnttTramo tm=(FnttTramo)lTrSub.get(0);
                     this.captacion.setIdTramo(tm);
                 }
            }
            System.out.println("----save setIdDepartamento----");
            this.captacion.setIdDepartamento((this.socDepartamento.getValue()!=null)?((Divipola)this.socDepartamento.getValue()).getId().intValue():null);
            this.captacion.setIdMunicipio((this.socMunicipio.getValue()!=null)?((Divipola)this.socMunicipio.getValue()).getId().intValue():null);
            this.captacion.setIdTipoCentroPoblado((this.socTipoCP.getValue()!=null)?((Lista)this.socTipoCP.getValue()).getCodigo():null);

            this.captacion.setMetodoExtraccion((this.socMetodoExtraccion.getValue()!=null)?((Lista)this.socMetodoExtraccion.getValue()).getCodigo():null);
            System.out.println("----save setMetodoExtraccion----");
            this.captacion.setNombreCentroPoblado((this.itNombreCentroPoblado.getValue()!=null)?this.itNombreCentroPoblado.getValue().toString():null);
            this.captacion.setTipoCaptacion((this.socTipoCaptacion.getValue()!=null)?((Lista)this.socTipoCaptacion.getValue()).getCodigo():null);
            this.captacion.setTipoPunto((this.socTipoPunto.getValue()!=null)?((Lista)this.socTipoPunto.getValue()).getCodigo():null);
           
              
            this.captacion.setIdentificadorPunto((this.itIdentificadorPuntoSubt.getValue()!=null)?this.itIdentificadorPuntoSubt.getValue().toString():null);
            this.captacion.setCaudalVertimiento((this.itCaudalVer.getValue()!=null)?Double.parseDouble(this.itCaudalVer.getValue().toString()):null);


            Double ofertaTotal = null;
            Double ofertaDisponible = null;
            if(this.captacion.getTipoFuenteCaptacion().longValue()==
                    ConstantesParametros.LISTA_AGUAS_SUPERFICIALES ||
                    this.captacion.getTipoFuenteCaptacion().longValue()==
                    ConstantesParametros.LISTA_AGUAS_SUBTERRANEAS
            ){
                ofertaDisponible = (this.itOfertaDisponible.getValue()!=null)?Double.parseDouble(this.itOfertaDisponible.getValue().toString()):null;
                ofertaTotal = (this.itOfertaTotal.getValue()!=null)?Double.parseDouble(this.itOfertaTotal.getValue().toString()):null;
                this.captacion.setTipoCaptacion((this.select_capt.getValue()!=null)?((Lista)this.select_capt.getValue()).getCodigo():null);
                this.captacion.setObservacion((this.itObservacion.getValue()!=null)?this.itObservacion.getValue().toString():null);
              
                
            }else if(this.captacion.getTipoFuenteCaptacion().longValue()==
                    ConstantesParametros.LISTA_AGUAS_MINERALES ||
                    this.captacion.getTipoFuenteCaptacion().longValue()==
                    ConstantesParametros.LISTA_AGUAS_LLUVIAS
            ){
                ofertaDisponible = (this.itOfertaEstimada.getValue()!=null)?Double.parseDouble(this.itOfertaEstimada.getValue().toString()):null;
                ofertaTotal = (this.itOferta.getValue()!=null)?Double.parseDouble(this.itOferta.getValue().toString()):null;
            }
            if(this.sbrMacroMedicion1.isSelected()){
                this.captacion.setTieneMacroMedicionAux(true);
            }else{
                this.captacion.setTieneMacroMedicionAux(false);
            }

            if(this.sbrServidumbre1.isSelected()){
                this.captacion.setTieneServidumbreAux(true);
            }else{
                this.captacion.setTieneServidumbreAux(false);
            }

            

            uad.updateCaptacion(this.captacion);
            
            //borrar la lista de componentes asociados
            if(this.captacion.getRurtCaptacionComponentesList()!=null){
                for(RurtCaptacionComponentes componente : this.captacion.getRurtCaptacionComponentesList()){
                    uad.deleteCaptacionComponente(componente);
                }
            }

            //adicionar la lista de componentes
            if(this.smcComponentes.getValue()!=null){
               Iterator it= ((List)this.smcComponentes.getValue()).iterator();
               while(it.hasNext()){
                   Lista lis = (Lista)it.next();
                   RurtCaptacionComponentes nuevo = new RurtCaptacionComponentes();
                   nuevo.setIdCaptacion(this.captacion.getCodigo());
                   nuevo.setIdComponente(lis.getCodigo());
                   UsuarioConectadoTO usuarioConectado =
                   (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
                   nuevo.setCodigoAutoridad(new Long(usuarioConectado.getUsuario().getAutoridadAmbiental().getId()));
                   uad.createCaptacionComponente(nuevo);
               }
            }
        }catch(IdeamException e){
            showMessage(e);
        }
    }





    /////////////////////////////////////

    public void setUsuario(UsuarioAgua usuario) {
        this.usuario = usuario;
    }

    public UsuarioAgua getUsuario() {
        return usuario;
    }

    public void setPredio(Predio predio) {
        this.predio = predio;
    }

    public Predio getPredio() {
        return predio;
    }

    public void setConcesion(Concesion concesion) {
        this.concesion = concesion;
    }

    public Concesion getConcesion() {
        return concesion;
    }

    public void setCaptacion(Captacion captacion) {
        this.captacion = captacion;
    }

    public Captacion getCaptacion() {
        return captacion;
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

    public void setListaComponentesAsociados(List listaComponentesAsociados) {
        this.listaComponentesAsociados = listaComponentesAsociados;
    }

    public List getListaComponentesAsociados() {
        return listaComponentesAsociados;
    }

    public void setSuperficial(Boolean superficial) {
        this.superficial = superficial;
    }

    public Boolean getSuperficial() {
        if( this.captacion.getTipoFuenteCaptacion().longValue()==ConstantesParametros.LISTA_AGUAS_SUPERFICIALES){
            return true;
        }else{
            return false;
        }
        //return superficial;
    }

    public void setSubterranea(Boolean subterranea) {
        this.subterranea = subterranea;
    }

    public Boolean getSubterranea() {
        if(this.captacion.getTipoFuenteCaptacion().longValue()==ConstantesParametros.LISTA_AGUAS_SUBTERRANEAS){
            return true;
        }else{
            return false;
        }
        //return subterranea;
    }

    public void setLluvia(Boolean lluvia) {
        this.lluvia = lluvia;
    }

    public Boolean getLluvia() {
        if(this.captacion.getTipoFuenteCaptacion().longValue()==ConstantesParametros.LISTA_AGUAS_LLUVIAS){
            return true;
        }else{
            return false;
        }
        //return lluvia;
    }

    public void setMineral(Boolean mineral) {
        this.mineral = mineral;
    }

    public Boolean getMineral() {
        if(this.captacion.getTipoFuenteCaptacion().longValue()==ConstantesParametros.LISTA_AGUAS_MINERALES){
            return true;
        }else{
            return false;
        }
        //return mineral;
    }

    public void setServida(Boolean servida) {
        this.servida = servida;
    }

    public Boolean getServida() {
        if(this.captacion.getTipoFuenteCaptacion().longValue()==ConstantesParametros.LISTA_AGUAS_SERVIDAS){
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

    public void setPanelSplitter1(RichPanelSplitter panelSplitter1) {
        this.panelSplitter1 = panelSplitter1;
    }

    public RichPanelSplitter getPanelSplitter1() {
        return panelSplitter1;
    }


    public void setPanelStretchLayout3(RichPanelStretchLayout panelStretchLayout3) {
        this.panelStretchLayout3 = panelStretchLayout3;
    }

    public RichPanelStretchLayout getPanelStretchLayout3() {
        return panelStretchLayout3;
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

    public void setPopup_borrar(RichPopup popup1) {
        this.popup_borrar = popup1;
    }

    public RichPopup getPopup_borrar() {
        return popup_borrar;
    }

    public void setP_registro_exitoso(RichPopup popup2) {
        this.p_registro_exitoso = popup2;
    }

    public RichPopup getP_registro_exitoso() {
        return p_registro_exitoso;
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


    public void setDecorativeBox2(RichDecorativeBox decorativeBox2) {
        this.decorativeBox2 = decorativeBox2;
    }

    public RichDecorativeBox getDecorativeBox2() {
        return decorativeBox2;
    }

    public void setDecorativeBox3(RichDecorativeBox decorativeBox3) {
        this.decorativeBox3 = decorativeBox3;
    }

    public RichDecorativeBox getDecorativeBox3() {
        return decorativeBox3;
    }

    public void setDecorativeBox4(RichDecorativeBox decorativeBox4) {
        this.decorativeBox4 = decorativeBox4;
    }

    public RichDecorativeBox getDecorativeBox4() {
        return decorativeBox4;
    }

    public void setDecorativeBox5(RichDecorativeBox decorativeBox5) {
        this.decorativeBox5 = decorativeBox5;
    }

    public RichDecorativeBox getDecorativeBox5() {
        return decorativeBox5;
    }

    public void setDecorativeBox6(RichDecorativeBox decorativeBox6) {
        this.decorativeBox6 = decorativeBox6;
    }

    public RichDecorativeBox getDecorativeBox6() {
        return decorativeBox6;
    }


    public void setPanelFormLayout2(RichPanelFormLayout panelFormLayout2) {
        this.panelFormLayout2 = panelFormLayout2;
    }

    public RichPanelFormLayout getPanelFormLayout2() {
        return panelFormLayout2;
    }

    public void setPanelFormLayout3(RichPanelFormLayout panelFormLayout3) {
        this.panelFormLayout3 = panelFormLayout3;
    }

    public RichPanelFormLayout getPanelFormLayout3() {
        return panelFormLayout3;
    }

    public void setPanelFormLayout4(RichPanelFormLayout panelFormLayout4) {
        this.panelFormLayout4 = panelFormLayout4;
    }

    public RichPanelFormLayout getPanelFormLayout4() {
        return panelFormLayout4;
    }

    public void setPanelFormLayout5(RichPanelFormLayout panelFormLayout5) {
        this.panelFormLayout5 = panelFormLayout5;
    }

    public RichPanelFormLayout getPanelFormLayout5() {
        return panelFormLayout5;
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

    public void setOutputText2(RichOutputText outputText2) {
        this.outputText2 = outputText2;
    }

    public RichOutputText getOutputText2() {
        return outputText2;
    }


    public void setPanelGroupLayout6(RichPanelGroupLayout panelGroupLayout6) {
        this.panelGroupLayout6 = panelGroupLayout6;
    }

    public RichPanelGroupLayout getPanelGroupLayout6() {
        return panelGroupLayout6;
    }

    public void setOutputText3(RichOutputText outputText3) {
        this.outputText3 = outputText3;
    }

    public RichOutputText getOutputText3() {
        return outputText3;
    }

    public void setSpacer4(RichSpacer spacer4) {
        this.spacer4 = spacer4;
    }

    public RichSpacer getSpacer4() {
        return spacer4;
    }

    public void setPanelGroupLayout7(RichPanelGroupLayout panelGroupLayout7) {
        this.panelGroupLayout7 = panelGroupLayout7;
    }

    public RichPanelGroupLayout getPanelGroupLayout7() {
        return panelGroupLayout7;
    }

    public void setOutputText4(RichOutputText outputText4) {
        this.outputText4 = outputText4;
    }

    public RichOutputText getOutputText4() {
        return outputText4;
    }

    public void setSpacer5(RichSpacer spacer5) {
        this.spacer5 = spacer5;
    }

    public RichSpacer getSpacer5() {
        return spacer5;
    }

    public void setPanelGroupLayout8(RichPanelGroupLayout panelGroupLayout8) {
        this.panelGroupLayout8 = panelGroupLayout8;
    }

    public RichPanelGroupLayout getPanelGroupLayout8() {
        return panelGroupLayout8;
    }

    public void setOutputText5(RichOutputText outputText5) {
        this.outputText5 = outputText5;
    }

    public RichOutputText getOutputText5() {
        return outputText5;
    }

    public void setSpacer6(RichSpacer spacer6) {
        this.spacer6 = spacer6;
    }

    public RichSpacer getSpacer6() {
        return spacer6;
    }

    public void setPanelGroupLayout9(RichPanelGroupLayout panelGroupLayout9) {
        this.panelGroupLayout9 = panelGroupLayout9;
    }

    public RichPanelGroupLayout getPanelGroupLayout9() {
        return panelGroupLayout9;
    }

    public void setOutputText6(RichOutputText outputText6) {
        this.outputText6 = outputText6;
    }

    public RichOutputText getOutputText6() {
        return outputText6;
    }

    public void setDecorativeBox7(RichDecorativeBox decorativeBox7) {
        this.decorativeBox7 = decorativeBox7;
    }

    public RichDecorativeBox getDecorativeBox7() {
        return decorativeBox7;
    }

    public void setPanelGroupLayout10(RichPanelGroupLayout panelGroupLayout10) {
        this.panelGroupLayout10 = panelGroupLayout10;
    }

    public RichPanelGroupLayout getPanelGroupLayout10() {
        return panelGroupLayout10;
    }

    public void setPanelGroupLayout11(RichPanelGroupLayout panelGroupLayout11) {
        this.panelGroupLayout11 = panelGroupLayout11;
    }

    public RichPanelGroupLayout getPanelGroupLayout11() {
        return panelGroupLayout11;
    }

    public void setSpacer7(RichSpacer spacer7) {
        this.spacer7 = spacer7;
    }

    public RichSpacer getSpacer7() {
        return spacer7;
    }

    public void setCbAceptar(RichCommandButton commandButton1) {
        this.cbAceptar = commandButton1;
    }

    public RichCommandButton getCbAceptar() {
        return cbAceptar;
    }

    public void setSpacer8(RichSpacer spacer8) {
        this.spacer8 = spacer8;
    }

    public RichSpacer getSpacer8() {
        return spacer8;
    }

    public void setCbBorrar(RichCommandButton commandButton2) {
        this.cbBorrar = commandButton2;
    }

    public RichCommandButton getCbBorrar() {
        return cbBorrar;
    }

    public void setSi_tipo_fuente(UISelectItems si_tipo_fuente) {
        this.si_tipo_fuente = si_tipo_fuente;
    }

    public UISelectItems getSi_tipo_fuente() {
        return si_tipo_fuente;
    }


    public void setSocUnidadHidro(RichSelectOneChoice socUnidadHidro) {
        this.socUnidadHidro = socUnidadHidro;
    }

    public RichSelectOneChoice getSocUnidadHidro() {
        return socUnidadHidro;
    }

    public void setSiUnidadHidro(UISelectItems siUnidadHidro) {
        this.siUnidadHidro = siUnidadHidro;
    }

    public UISelectItems getSiUnidadHidro() {
        return siUnidadHidro;
    }

    public void setSocDepartamento(RichSelectOneChoice socDepartamento) {
        this.socDepartamento = socDepartamento;
    }

    public RichSelectOneChoice getSocDepartamento() {
        return socDepartamento;
    }

    public void setSiDepartamento(UISelectItems siDepartamento) {
        this.siDepartamento = siDepartamento;
    }

    public UISelectItems getSiDepartamento() {
        return siDepartamento;
    }

    public void setSocMunicipio(RichSelectOneChoice socMunicipio) {
        this.socMunicipio = socMunicipio;
    }

    public RichSelectOneChoice getSocMunicipio() {
        return socMunicipio;
    }

    public void setSiMunicipio(UISelectItems siMunicipio) {
        this.siMunicipio = siMunicipio;
    }

    public UISelectItems getSiMunicipio() {
        return siMunicipio;
    }

    public void setSocTipoCP(RichSelectOneChoice socTipoCP) {
        this.socTipoCP = socTipoCP;
    }

    public RichSelectOneChoice getSocTipoCP() {
        return socTipoCP;
    }

    public void setSiTipoCP(UISelectItems siTipoCP) {
        this.siTipoCP = siTipoCP;
    }

    public UISelectItems getSiTipoCP() {
        return siTipoCP;
    }

    public void setItNombreCentroPoblado(RichInputText itNombreCentroPoblado) {
        this.itNombreCentroPoblado = itNombreCentroPoblado;
    }

    public RichInputText getItNombreCentroPoblado() {
        return itNombreCentroPoblado;
    }


    public void setSocTipoPunto(RichSelectOneChoice socTipoPunto) {
        this.socTipoPunto = socTipoPunto;
    }

    public RichSelectOneChoice getSocTipoPunto() {
        return socTipoPunto;
    }

    public void setSiTipoPunto(UISelectItems siTipoPunto) {
        this.siTipoPunto = siTipoPunto;
    }

    public UISelectItems getSiTipoPunto() {
        return siTipoPunto;
    }

    public void setSocTipoCaptacion(RichSelectOneChoice socTipoCaptacion) {
        this.socTipoCaptacion = socTipoCaptacion;
    }

    public RichSelectOneChoice getSocTipoCaptacion() {
        return socTipoCaptacion;
    }

    public void setSiTipoCaptacion(UISelectItems siTipoCaptacion) {
        this.siTipoCaptacion = siTipoCaptacion;
    }

    public UISelectItems getSiTipoCaptacion() {
        return siTipoCaptacion;
    }

    public void setItOfertaEstimada(RichInputText itOfertaEstimada) {
        this.itOfertaEstimada = itOfertaEstimada;
    }

    public RichInputText getItOfertaEstimada() {
        return itOfertaEstimada;
    }

    public void setItOferta(RichInputText itOferta) {
        this.itOferta = itOferta;
    }

    public RichInputText getItOferta() {
        return itOferta;
    }

    public void setItAreaCaptacion(RichInputText itAreaCaptacion) {
        this.itAreaCaptacion = itAreaCaptacion;
    }

    public RichInputText getItAreaCaptacion() {
        return itAreaCaptacion;
    }

    public void setItCaudalVer(RichInputText itCaudalVer) {
        this.itCaudalVer = itCaudalVer;
    }

    public RichInputText getItCaudalVer() {
        return itCaudalVer;
    }

    public void setItDescripcionAcceso(RichInputText itDescripcionAcceso) {
        this.itDescripcionAcceso = itDescripcionAcceso;
    }

    public RichInputText getItDescripcionAcceso() {
        return itDescripcionAcceso;
    }

    public void setSbrMacroMedicion1(RichSelectBooleanRadio sbrMacromedicion1) {
        this.sbrMacroMedicion1 = sbrMacromedicion1;
    }

    public RichSelectBooleanRadio getSbrMacroMedicion1() {
        return sbrMacroMedicion1;
    }

    public void setSbrMacroMedicion2(RichSelectBooleanRadio sbrMacroMedicion2) {
        this.sbrMacroMedicion2 = sbrMacroMedicion2;
    }

    public RichSelectBooleanRadio getSbrMacroMedicion2() {
        return sbrMacroMedicion2;
    }

    public void setSocEstadoCaptacion(RichSelectOneChoice socEstadoCaptacion) {
        this.socEstadoCaptacion = socEstadoCaptacion;
    }

    public RichSelectOneChoice getSocEstadoCaptacion() {
        return socEstadoCaptacion;
    }

    public void setSiEstadoCaptacion(UISelectItems siEstadoCaptacion) {
        this.siEstadoCaptacion = siEstadoCaptacion;
    }

    public UISelectItems getSiEstadoCaptacion() {
        return siEstadoCaptacion;
    }

    public void setSocContinuidad(RichSelectOneChoice socContinuidad) {
        this.socContinuidad = socContinuidad;
    }

    public RichSelectOneChoice getSocContinuidad() {
        return socContinuidad;
    }

    public void setSiContinuidad(UISelectItems siContinuidad) {
        this.siContinuidad = siContinuidad;
    }

    public UISelectItems getSiContinuidad() {
        return siContinuidad;
    }

    public void setItCaudalDisegno(RichInputText itCaudalDisegno) {
        this.itCaudalDisegno = itCaudalDisegno;
    }

    public RichInputText getItCaudalDisegno() {
        return itCaudalDisegno;
    }

    public void setSbrServidumbre1(RichSelectBooleanRadio sbrServidumbre1) {
        this.sbrServidumbre1 = sbrServidumbre1;
    }

    public RichSelectBooleanRadio getSbrServidumbre1() {
        return sbrServidumbre1;
    }

    public void setSbrServidumbre2(RichSelectBooleanRadio sbrServidumbre2) {
        this.sbrServidumbre2 = sbrServidumbre2;
    }

    public RichSelectBooleanRadio getSbrServidumbre2() {
        return sbrServidumbre2;
    }

    public void setSmcComponentes(RichSelectManyCheckbox smcComponentes) {
        this.smcComponentes = smcComponentes;
    }

    public RichSelectManyCheckbox getSmcComponentes() {
        return smcComponentes;
    }

    public void setSiComponentes(UISelectItems siComponentes) {
        this.siComponentes = siComponentes;
    }

    public UISelectItems getSiComponentes() {
        return siComponentes;
    }

    public void setItOfertaTotal(RichInputText itOfertaTotal) {
        this.itOfertaTotal = itOfertaTotal;
    }

    public RichInputText getItOfertaTotal() {
        return itOfertaTotal;
    }

    public void setItOfertaDisponible(RichInputText itOfertaDisponible) {
        this.itOfertaDisponible = itOfertaDisponible;
    }

    public RichInputText getItOfertaDisponible() {
        return itOfertaDisponible;
    }

    public void setItOfertaSubterranea(RichInputText itOfertaSubterranea) {
        this.itOfertaSubterranea = itOfertaSubterranea;
    }

    public RichInputText getItOfertaSubterranea() {
        return itOfertaSubterranea;
    }

    public void setSocMetodoExtraccion(RichSelectOneChoice socMetodoExtraccion) {
        this.socMetodoExtraccion = socMetodoExtraccion;
    }

    public RichSelectOneChoice getSocMetodoExtraccion() {
        return socMetodoExtraccion;
    }

    public void setSiMetodoExtraccion(UISelectItems siMetodoExtraccion) {
        this.siMetodoExtraccion = siMetodoExtraccion;
    }

    public UISelectItems getSiMetodoExtraccion() {
        return siMetodoExtraccion;
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

    public void setPanelGroupLayout21(RichPanelGroupLayout panelGroupLayout21) {
        this.panelGroupLayout21 = panelGroupLayout21;
    }

    public RichPanelGroupLayout getPanelGroupLayout21() {
        return panelGroupLayout21;
    }


    public void setSocSistemaReferencia(RichSelectOneChoice socSistemaReferencia) {
        this.socSistemaReferencia = socSistemaReferencia;
    }

    public RichSelectOneChoice getSocSistemaReferencia() {
        return socSistemaReferencia;
    }

    public void setSiSistemaReferencia(UISelectItems siSistemaReferencia) {
        this.siSistemaReferencia = siSistemaReferencia;
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

    public void setPanelFormLayout16(RichPanelFormLayout panelFormLayout16) {
        this.panelFormLayout16 = panelFormLayout16;
    }

    public RichPanelFormLayout getPanelFormLayout16() {
        return panelFormLayout16;
    }

    public void setPanelFormLayout17(RichPanelFormLayout panelFormLayout17) {
        this.panelFormLayout17 = panelFormLayout17;
    }

    public RichPanelFormLayout getPanelFormLayout17() {
        return panelFormLayout17;
    }

    public void setItGradosLat(RichInputText itGradosPi) {
        this.itGradosLat = itGradosPi;
    }

    public RichInputText getItGradosLat() {
        return itGradosLat;
    }

    public void setItMinutosLat(RichInputText itMinutosPi) {
        this.itMinutosLat = itMinutosPi;
    }

    public RichInputText getItMinutosLat() {
        return itMinutosLat;
    }

    public void setItSegundosLat(RichInputText itSegundosPi) {
        this.itSegundosLat = itSegundosPi;
    }

    public RichInputText getItSegundosLat() {
        return itSegundosLat;
    }

    public void setItAltitud(RichInputText itAltitudPi) {
        this.itAltitud = itAltitudPi;
    }

    public RichInputText getItAltitud() {
        return itAltitud;
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

    public void setPgl14(RichPanelGroupLayout pgl14) {
        this.pgl14 = pgl14;
    }

    public RichPanelGroupLayout getPgl14() {
        return pgl14;
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

    public void setPsl7(RichPanelStretchLayout psl7) {
        this.psl7 = psl7;
    }

    public RichPanelStretchLayout getPsl7() {
        return psl7;
    }

    public void setS15(RichSpacer s15) {
        this.s15 = s15;
    }

    public RichSpacer getS15() {
        return s15;
    }

    public void setS16(RichSpacer s16) {
        this.s16 = s16;
    }

    public RichSpacer getS16() {
        return s16;
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

    public void setCbAceptarDi(RichCommandButton cbAceptarDi) {
        this.cbAceptarDi = cbAceptarDi;
    }

    public RichCommandButton getCbAceptarDi() {
        return cbAceptarDi;
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


    public void setSoc_area(RichSelectOneChoice soc_area) {
        this.soc_area = soc_area;
    }

    public RichSelectOneChoice getSoc_area() {
        return soc_area;
    }

    public void setSiArea(UISelectItems siArea) {
        this.siArea = siArea;
    }

    public UISelectItems getSiArea() {
        return siArea;
    }

    public void setSoc_zona(RichSelectOneChoice soc_zona) {
        this.soc_zona = soc_zona;
    }

    public RichSelectOneChoice getSoc_zona() {
        return soc_zona;
    }

    public void setSiZona(UISelectItems siZona) {
        this.siZona = siZona;
    }

    public UISelectItems getSiZona() {
        return siZona;
    }

    public void setSoc_subzona(RichSelectOneChoice soc_subzona) {
        this.soc_subzona = soc_subzona;
    }

    public RichSelectOneChoice getSoc_subzona() {
        return soc_subzona;
    }

    public void setSiSubzona(UISelectItems siSubzona) {
        this.siSubzona = siSubzona;
    }

    public UISelectItems getSiSubzona() {
        return siSubzona;
    }

    public void setSocFuente(RichSelectOneChoice socFuente) {
        this.socFuente = socFuente;
    }

    public RichSelectOneChoice getSocFuente() {
        return socFuente;
    }

    public void setSiFuentes(UISelectItems siFuentes) {
        this.siFuentes = siFuentes;
    }

    public UISelectItems getSiFuentes() {
        return siFuentes;
    }

    public void setSocTramo(RichSelectOneChoice socTramo) {
        this.socTramo = socTramo;
    }

    public RichSelectOneChoice getSocTramo() {
        return socTramo;
    }

    public void setSiTramos(UISelectItems siTramos) {
        this.siTramos = siTramos;
    }

    public UISelectItems getSiTramos() {
        return siTramos;
    }

    public void setPanelGroupLayout29(RichPanelGroupLayout panelGroupLayout29) {
        this.panelGroupLayout29 = panelGroupLayout29;
    }

    public RichPanelGroupLayout getPanelGroupLayout29() {
        return panelGroupLayout29;
    }

    public void setSpacer20(RichSpacer spacer20) {
        this.spacer20 = spacer20;
    }

    public RichSpacer getSpacer20() {
        return spacer20;
    }

    public void setSpacer21(RichSpacer spacer21) {
        this.spacer21 = spacer21;
    }

    public RichSpacer getSpacer21() {
        return spacer21;
    }

    public void setSpacer22(RichSpacer spacer22) {
        this.spacer22 = spacer22;
    }

    public RichSpacer getSpacer22() {
        return spacer22;
    }

    public void setSpacer23(RichSpacer spacer23) {
        this.spacer23 = spacer23;
    }

    public RichSpacer getSpacer23() {
        return spacer23;
    }

    public void setSpacer24(RichSpacer spacer24) {
        this.spacer24 = spacer24;
    }

    public RichSpacer getSpacer24() {
        return spacer24;
    }

    public void setSpacer25(RichSpacer spacer25) {
        this.spacer25 = spacer25;
    }

    public RichSpacer getSpacer25() {
        return spacer25;
    }

    public void setClUsuario(RichCommandLink clUsuario) {
        this.clUsuario = clUsuario;
    }

    public RichCommandLink getClUsuario() {
        return clUsuario;
    }

    public void setClPredio(RichCommandLink clPredio) {
        this.clPredio = clPredio;
    }

    public RichCommandLink getClPredio() {
        return clPredio;
    }

    public void setClConcesiones(RichCommandLink clConcesiones) {
        this.clConcesiones = clConcesiones;
    }

    public RichCommandLink getClConcesiones() {
        return clConcesiones;
    }

    public void setClTodos(RichCommandLink clTodos) {
        this.clTodos = clTodos;
    }

    public RichCommandLink getClTodos() {
        return clTodos;
    }

    public void setClConcesion(RichCommandLink clConcesion) {
        this.clConcesion = clConcesion;
    }

    public RichCommandLink getClConcesion() {
        return clConcesion;
    }

    public void setClCaptaciones(RichCommandLink clCaptaciones) {
        this.clCaptaciones = clCaptaciones;
    }

    public RichCommandLink getClCaptaciones() {
        return clCaptaciones;
    }

    public void setOt11(RichOutputText ot11) {
        this.ot11 = ot11;
    }

    public RichOutputText getOt11() {
        return ot11;
    }


    public void setPbUsos(RichPanelBox panelBox2) {
        this.pbUsos = panelBox2;
    }

    public RichPanelBox getPbUsos() {
        return pbUsos;
    }

    public void setPanelCollection1(RichPanelCollection panelCollection1) {
        this.panelCollection1 = panelCollection1;
    }

    public RichPanelCollection getPanelCollection1() {
        return panelCollection1;
    }

    public void setMenu1(RichMenu menu1) {
        this.menu1 = menu1;
    }

    public RichMenu getMenu1() {
        return menu1;
    }

    public void setCmiAddUso(RichCommandMenuItem commandMenuItem1) {
        this.cmiAddUso = commandMenuItem1;
    }

    public RichCommandMenuItem getCmiAddUso() {
        return cmiAddUso;
    }

    public void setTree1(RichTree tree1) {
        this.tree1 = tree1;
    }

    public RichTree getTree1() {
        return tree1;
    }

    public void setCl2(RichCommandLink cl2) {
        this.cl2 = cl2;
    }

    public RichCommandLink getCl2() {
        return cl2;
    }

    public void setUsosTreeModel(TreeModel usosTreeModel) {
        this.usosTreeModel = usosTreeModel;
    }

    public TreeModel getUsosTreeModel() {
        return usosTreeModel;
    }

    public void setPanelFormLayout1(RichPanelFormLayout panelFormLayout1) {
        this.panelFormLayout1 = panelFormLayout1;
    }

    public RichPanelFormLayout getPanelFormLayout1() {
        return panelFormLayout1;
    }

    public void setPanelFormLayout6(RichPanelFormLayout panelFormLayout6) {
        this.panelFormLayout6 = panelFormLayout6;
    }

    public RichPanelFormLayout getPanelFormLayout6() {
        return panelFormLayout6;
    }


    public void setActiveOutputText1(RichActiveOutputText activeOutputText1) {
        this.activeOutputText1 = activeOutputText1;
    }

    public RichActiveOutputText getActiveOutputText1() {
        return activeOutputText1;
    }

    public void setSpacer1(RichSpacer spacer1) {
        this.spacer1 = spacer1;
    }

    public RichSpacer getSpacer1() {
        return spacer1;
    }

    public void setSpacer9(RichSpacer spacer9) {
        this.spacer9 = spacer9;
    }

    public RichSpacer getSpacer9() {
        return spacer9;
    }

    public void setOutputLabel1(RichOutputLabel outputLabel1) {
        this.outputLabel1 = outputLabel1;
    }

    public RichOutputLabel getOutputLabel1() {
        return outputLabel1;
    }

    public void setItGradosLong(RichInputText inputText1) {
        this.itGradosLong = inputText1;
    }

    public RichInputText getItGradosLong() {
        return itGradosLong;
    }

    public void setItMinutosLong(RichInputText inputText2) {
        this.itMinutosLong = inputText2;
    }

    public RichInputText getItMinutosLong() {
        return itMinutosLong;
    }

    public void setItSegundosLong(RichInputText inputText3) {
        this.itSegundosLong = inputText3;
    }

    public RichInputText getItSegundosLong() {
        return itSegundosLong;
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


    public void setMfunias(RichMenu menu2) {
        this.mfunias = menu2;
    }

    public RichMenu getMfunias() {
        return mfunias;
    }

    public void setCmiAddFunias(RichCommandMenuItem commandMenuItem1) {
        this.cmiAddFunias = commandMenuItem1;
    }

    public RichCommandMenuItem getCmiAddFunias() {
        return cmiAddFunias;
    }

    public void setCmiListFunias(RichCommandMenuItem commandMenuItem2) {
        this.cmiListFunias = commandMenuItem2;
    }

    public RichCommandMenuItem getCmiListFunias() {
        return cmiListFunias;
    }

    public void setPc2(RichPanelCollection panelCollection2) {
        this.pc2 = panelCollection2;
    }

    public RichPanelCollection getPc2() {
        return pc2;
    }

    public void setOtTipoFuente(RichOutputText outputText1) {
        this.otTipoFuente = outputText1;
    }

    public RichOutputText getOtTipoFuente() {
        return otTipoFuente;
    }

    public void setSpacer11(RichSpacer spacer11) {
        this.spacer11 = spacer11;
    }

    public RichSpacer getSpacer11() {
        return spacer11;
    }

    public void setItIdentificadorPuntoSubt(RichInputText inputText1) {
        this.itIdentificadorPuntoSubt = inputText1;
    }

    public RichInputText getItIdentificadorPuntoSubt() {
        return itIdentificadorPuntoSubt;
    }

    public void setCmiPuntoMonitoreo(RichCommandMenuItem commandMenuItem1) {
        this.cmiPuntoMonitoreo = commandMenuItem1;
    }

    public RichCommandMenuItem getCmiPuntoMonitoreo() {
        return cmiPuntoMonitoreo;
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


    public void setPnlCol1(RichPanelCollection pnlCol1) {
        this.pnlCol1 = pnlCol1;
    }

    public RichPanelCollection getPnlCol1() {
        return pnlCol1;
    }

    public void setMenu11(RichMenu menu11) {
        this.menu11 = menu11;
    }

    public RichMenu getMenu11() {
        return menu11;
    }

    public void setCmiAddUso1(RichCommandMenuItem cmiAddUso1) {
        this.cmiAddUso1 = cmiAddUso1;
    }

    public RichCommandMenuItem getCmiAddUso1() {
        return cmiAddUso1;
    }

    public void setCmiAddFunias1(RichCommandMenuItem cmiAddFunias1) {
        this.cmiAddFunias1 = cmiAddFunias1;
    }

    public RichCommandMenuItem getCmiAddFunias1() {
        return cmiAddFunias1;
    }

    public void setCmiListFunias1(RichCommandMenuItem cmiListFunias1) {
        this.cmiListFunias1 = cmiListFunias1;
    }

    public RichCommandMenuItem getCmiListFunias1() {
        return cmiListFunias1;
    }

    public void setTree11(RichTree tree11) {
        this.tree11 = tree11;
    }

    public RichTree getTree11() {
        return tree11;
    }

    public void setCl21(RichCommandLink cl21) {
        this.cl21 = cl21;
    }

    public RichCommandLink getCl21() {
        return cl21;
    }

    public void setPanelStretchLayout2(RichPanelStretchLayout panelStretchLayout2) {
        this.panelStretchLayout2 = panelStretchLayout2;
    }

    public RichPanelStretchLayout getPanelStretchLayout2() {
        return panelStretchLayout2;
    }


    public void setListaPuntosMonitoreo(List<PuntoMonitoreo> listaPuntosMonitoreo) {
        this.listaPuntosMonitoreo = listaPuntosMonitoreo;
    }

    public List<PuntoMonitoreo> getListaPuntosMonitoreo() {
        return listaPuntosMonitoreo;
    }

    public void setPuntosMonitoreoTreeModel(TreeModel puntosMonitoreoTreeModel) {
        this.puntosMonitoreoTreeModel = puntosMonitoreoTreeModel;
    }

    public TreeModel getPuntosMonitoreoTreeModel() {
        return puntosMonitoreoTreeModel;
    }

    public void setCmi_puntoCalidad(RichCommandMenuItem commandMenuItem1) {
        this.cmi_puntoCalidad = commandMenuItem1;
    }

    public RichCommandMenuItem getCmi_puntoCalidad() {
        return cmi_puntoCalidad;
    }

    public void setVisibleMenu(String visibleMenu) {
        this.visibleMenu = visibleMenu;
    }

    public String getVisibleMenu() {
        return visibleMenu;
    }


    public void setEtiquetaFuenteSupSub(String etiquetaFuenteSupSub) {
        this.etiquetaFuenteSupSub = etiquetaFuenteSupSub;
    }

    public String getEtiquetaFuenteSupSub() {
        return etiquetaFuenteSupSub;
    }

    public void setSelect_capt(RichSelectOneChoice select_capt) {
        this.select_capt = select_capt;
    }

    public RichSelectOneChoice getSelect_capt() {
        return select_capt;
    }

    public void setSelectItemscapt(UISelectItems selectItemscapt) {
        this.selectItemscapt = selectItemscapt;
    }

    public UISelectItems getSelectItemscapt() {
        return selectItemscapt;
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
  
  //CDONCEL
  public String prepareAforo() {
      try{
          UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
          lsAforo = new ArrayList<RurtCapAforo>();
          System.out.println("LSAFORO: " + this.captacion.getCodigo());
          flagAforo = true;
          aforo = new RurtCapAforo();
          
          lsAforo.addAll(uad.getLsAforos(this.captacion));    
  
  }catch(IdeamException e){
        System.err.println("ERROR enn DetalleCaptacionBean..." + e.getCause()  + " - " +
                           e.getMessage() + " - " + e.getLocalizedMessage());
      }
      return null;
  }
  
  public void adicionarAforo(){
      try{
          UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
          this.aforo.setCaptacionId(this.captacion);
          System.out.println("++++++++++++++++++++ Aforo +++++++++++++++++++++++++++");
          System.out.println(aforo.getCaudal() + " - " + aforo.getFecha() + " - " + aforo.getObservacion() + " - " + aforo.getCaptacionId().getCodigo());
          uad.updateAforo(this.aforo);
        finalizarAforo();
        showPopup(aforo_registro_exitoso, true);    
      }catch(Exception e){
          System.err.println(e.getMessage());
      //return null;
    }
  }
  
  public void finalizarAforo(){
      aforo = null;
      setFlagAforo(false);
    }
  
  public void borrarAforo(){
      try{
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest req = (HttpServletRequest)context.getExternalContext().getRequest();
        String param = req.getParameter("aforoid");
        System.out.println("*****************++pARAM: " + param);
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
            uad.borrarAforo(param);
            this.finalizarAforo(); 
            showPopup(aforo_borrardo_exitoso, true);
          }catch(Exception e){
              System.err.println(e.getMessage());
              //return null;
          }
              
              }
  
  public void cb_aceptar_actionListener(ActionEvent actionEvent) {
      this.setAceptarActionPop("detalleCaptacion");
  }
  
  
  public Boolean getFlagAforo() {
    return flagAforo;
  }

  public void setFlagAforo(Boolean flagAforo) {
    this.flagAforo = flagAforo;
  }

  public RurtCapAforo getAforo() {
    return aforo;
  }

  public void setAforo(RurtCapAforo aforo) {
    this.aforo = aforo;
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

  public RichPanelFormLayout getPanelFormLayout1Aforo() {
    return panelFormLayout1Aforo;
  }

  public void setPanelFormLayout1Aforo(RichPanelFormLayout panelFormLayout1Aforo) {
    this.panelFormLayout1Aforo = panelFormLayout1Aforo;
  }

  public RichSpacer getSpacer2Aforo() {
    return spacer2Aforo;
  }

  public void setSpacer2Aforo(RichSpacer spacer2Aforo) {
    this.spacer2Aforo = spacer2Aforo;
  }

  public RichTable getTableAforo() {
    return tableAforo;
  }

  public void setTableAforo(RichTable tableAforo) {
    this.tableAforo = tableAforo;
  }

  public List<RurtCapAforo> getLsAforo() {
    return lsAforo;
  }

  public void setLsAforo(List<RurtCapAforo> lsAforo) {
    this.lsAforo = lsAforo;
  }

  public RichSpacer getSpacerAf() {
    return spacerAf;
  }

  public void setSpacerAf(RichSpacer spacerAf) {
    this.spacerAf = spacerAf;
  }

  public RichPopup getAforo_registro_exitoso() {
    return aforo_registro_exitoso;
  }

  public void setAforo_registro_exitoso(RichPopup aforo_registro_exitoso) {
    this.aforo_registro_exitoso = aforo_registro_exitoso;
  }

  public RichPopup getAforo_borrardo_exitoso() {
    return aforo_borrardo_exitoso;
  }

  public void setAforo_borrardo_exitoso(RichPopup aforo_borrardo_exitoso) {
    this.aforo_borrardo_exitoso = aforo_borrardo_exitoso;
  }

  public RichDialog getD_aforo_registro_exitoso() {
    return d_aforo_registro_exitoso;
  }

  public void setD_aforo_registro_exitoso(RichDialog d_aforo_registro_exitoso) {
    this.d_aforo_registro_exitoso = d_aforo_registro_exitoso;
  }

  public RichDialog getD_aforo_borrado_exitoso() {
    return d_aforo_borrado_exitoso;
  }

  public void setD_aforo_borrado_exitoso(RichDialog d_aforo_borrado_exitoso) {
    this.d_aforo_borrado_exitoso = d_aforo_borrado_exitoso;
  }

  public RichPanelGroupLayout getPgaforoPop() {
    return pgaforoPop;
  }

  public void setPgaforoPop(RichPanelGroupLayout pgaforoPop) {
    this.pgaforoPop = pgaforoPop;
  }

  public RichCommandButton getCbAceptarAforo() {
    return cbAceptarAforo;
  }

  public void setCbAceptarAforo(RichCommandButton cbAceptarAforo) {
    this.cbAceptarAforo = cbAceptarAforo;
  }

  public RichPanelStretchLayout getPsAforo() {
    return psAforo;
  }

  public void setPsAforo(RichPanelStretchLayout psAforo) {
    this.psAforo = psAforo;
  }

  public RichOutputText getOtAforoPop() {
    return otAforoPop;
  }

  public void setOtAforoPop(RichOutputText otAforoPop) {
    this.otAforoPop = otAforoPop;
  }


  public RichImage getImAforoPop() {
    return imAforoPop;
  }

  public void setImAforoPop(RichImage imAforoPop) {
    this.imAforoPop = imAforoPop;
  }

  public String getAceptarActionPop() {
    return aceptarActionPop;
  }

  public void setAceptarActionPop(String aceptarActionPop) {
    this.aceptarActionPop = aceptarActionPop;
  }

  public RichPanelGroupLayout getPgaforoPop2() {
    return pgaforoPop2;
  }

  public void setPgaforoPop2(RichPanelGroupLayout pgaforoPop2) {
    this.pgaforoPop2 = pgaforoPop2;
  }

  public RichCommandButton getCbAceptarAforo2() {
    return cbAceptarAforo2;
  }

  public void setCbAceptarAforo2(RichCommandButton cbAceptarAforo2) {
    this.cbAceptarAforo2 = cbAceptarAforo2;
  }

  public RichPanelStretchLayout getPsAforo2() {
    return psAforo2;
  }

  public void setPsAforo2(RichPanelStretchLayout psAforo2) {
    this.psAforo2 = psAforo2;
  }

  public RichOutputText getOtAforoPop2() {
    return otAforoPop2;
  }

  public void setOtAforoPop2(RichOutputText otAforoPop2) {
    this.otAforoPop2 = otAforoPop2;
  }

  public RichImage getImAforoPop2() {
    return imAforoPop2;
  }

  public void setImAforoPop2(RichImage imAforoPop2) {
    this.imAforoPop2 = imAforoPop2;
  }

  public String getAceptarActionPop2() {
    return aceptarActionPop2;
  }

  public void setAceptarActionPop2(String aceptarActionPop2) {
    this.aceptarActionPop2 = aceptarActionPop2;
  }
}
