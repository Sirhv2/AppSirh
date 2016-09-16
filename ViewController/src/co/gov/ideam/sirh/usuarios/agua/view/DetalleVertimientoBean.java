package co.gov.ideam.sirh.usuarios.agua.view;

import co.gov.ideam.sirh.auditoria.model.Auditoria;
import co.gov.ideam.sirh.auditoria.view.AuditoriasDelegate;
import co.gov.ideam.sirh.calidad.model.PuntoMonitoreo;
import co.gov.ideam.sirh.calidad.view.CalidadDelegate;
import co.gov.ideam.sirh.fuentes.model.CriteriosBusquedaTO;
import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.fuentes.model.FnttTramo;
import co.gov.ideam.sirh.fuentes.view.FuenteDelegate;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.model.PartZonificListas;
import co.gov.ideam.sirh.parametros.view.ParametrosDelegate;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.usuarios.agua.model.Captacion;
import co.gov.ideam.sirh.usuarios.agua.model.Concesion;
import co.gov.ideam.sirh.usuarios.agua.model.PermisoVertimiento;
import co.gov.ideam.sirh.usuarios.agua.model.Predio;
import co.gov.ideam.sirh.usuarios.agua.model.PuntoVertimiento;
import co.gov.ideam.sirh.usuarios.agua.model.PuntoVertimientoTratamiento;
import co.gov.ideam.sirh.usuarios.agua.model.RurtCaptacionComponentes;
import co.gov.ideam.sirh.usuarios.agua.model.UsuarioAgua;
import co.gov.ideam.sirh.util.Constantes;
import co.gov.ideam.sirh.util.ConstantesParametros;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;

import co.gov.ideam.sirh.view.StandarBean;

import co.gov.ideam.sirh.view.util.SpecialTreeModel;
import co.gov.ideam.sirh.view.util.TreeNode;

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
import oracle.adf.view.rich.component.rich.data.RichTree;
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
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.nav.RichCommandMenuItem;
import oracle.adf.view.rich.component.rich.output.RichImage;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.component.rich.input.RichSelectManyListbox;

import oracle.adf.view.rich.component.rich.output.RichActiveOutputText;
import oracle.adf.view.rich.component.rich.output.RichOutputLabel;

import org.apache.myfaces.trinidad.model.TreeModel;


public class DetalleVertimientoBean extends StandarBean{

    //Objeto al cual se le están realizando cambios
    private PuntoVertimiento vertimiento;
    //listado de departamentos desde categorias.
    private List listaDepartamentos;
    //listado de municipios de un departamento.
    private List listaMunicipios;
    //listado de areas hidrologicos.
    private List listaArea;
    //listado de zonas de una area.
    private List listaZona;
    //listado de subzonas de una zona.
    private List listaSubzona;
    //lista de tipos de fuente en categoria.
    private List listaTiposFuente;
    //Lista de fuentes registradas.
    private List listaFuentes;
    //Lista de tramos de una fuente.
    private List listaTramos;
    //lista de tipos de vertimiento en categoria.
    private List listaTipoVertimiento;
    //Lista de tipos de centros problados en categorias.
    private List listaTipoCentroPoblado;
    //lista de sistemas de referencia en categorias.
    private List listaSistemasReferencia;
    //Lista de tipos de flujo en categorias.
    private List listaTipoFlujo;
    //Lista de tipos de pretartamiento en categorias.
    private List listaPretratamiento;
    //Lista de tratamiento primario en categorias.
    private List listaPrimario;
    //Lista de tratamiento secundario en categorias.
    private List listaSecundario;
    //Lista de tratamiento terciario en categorias.
    private List listaTerciario;
    //Lista de otros tratamientos en categorias.
    private List listaOtro;
    //Lista de días del mes
    private List listaDias;
    //Lista de tipos de pretartamiento asociado.
    private List listaPretratamientoAsociado;
    //Lista de tratamiento primario asociado.
    private List listaPrimarioAsociado;
    //Lista de tratamiento secundario asociado.
    private List listaSecundarioAsociado;
    //Lista de tratamiento terciario asociado.
    private List listaTerciarioAsociado;
    //Lista de otros tratamientos asociado.
    private List listaOtroAsociado;

    private UsuarioAgua usuario;
    private Predio predio;
    private PermisoVertimiento permiso;

    private RichForm form1;
    private RichDocument document1;
    private RichPanelStretchLayout panelStretchLayout1;
    private RichPanelSplitter panelSplitter1;
    private RichPanelStretchLayout panelStretchLayout2;
    private RichPanelStretchLayout panelStretchLayout3;
    private RichPanelGroupLayout panelGroupLayout1;
    private RichPanelBox panelBox1;
    private RichPanelGroupLayout panelGroupLayout2;
    private RichPanelGroupLayout panelGroupLayout3;
    private RichDecorativeBox decorativeBox1;
    private RichDecorativeBox decorativeBox2;
    private RichPanelFormLayout panelFormLayout1;
    private RichPanelFormLayout panelFormLayout2;
    private RichDecorativeBox decorativeBox3;
    private RichDecorativeBox decorativeBox4;
    private RichDecorativeBox decorativeBox5;
    private RichPanelFormLayout panelFormLayout3;
    private RichPanelFormLayout panelFormLayout5;
    private RichPanelGroupLayout panelGroupLayout4;
    private RichPanelGroupLayout panelGroupLayout5;
    private RichSelectOneChoice socTipoVertimiento;
    private UISelectItems siTipoVertimiento;
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
    private RichSelectOneChoice socDepartamento;
    private UISelectItems siDepartamento;
    private RichSelectOneChoice socMunicipio;
    private UISelectItems siMunicipio;
    private RichSelectOneChoice socTipoCP;
    private UISelectItems siTipoCP;
    private RichInputText itNombreCentroPoblado;
    private RichCommandButton cbPrevUbicacion;
    private RichSpacer spacer5;
    private RichCommandButton cbNextUbicacion;
    private RichSpacer spacer6;
    private RichPanelFormLayout panelFormLayout41;
    private RichSelectOneChoice socSistemaReferencia;
    private UISelectItems siSistemaReferencia;
    private RichSpacer spacer13;
    private RichInputText itGradosLat;
    private RichInputText itMinutosLat;
    private RichInputText itSegundosLat;
    private RichInputText itAltitud;
    private RichInputText itDescripcionAcceso;
    private RichSelectOneChoice socTipoFlujo;
    private UISelectItems siTipoFlujo;
    private RichInputText itTiempoDescarga;
    private RichInputText itCaudalDisegno;
    private RichSelectOneChoice socFrecuencia;
    private UISelectItems siFrecuencia;
    private RichPanelGroupLayout panelGroupLayout10;
    private RichPanelGroupLayout panelGroupLayout11;
    private RichPanelGroupLayout panelGroupLayout12;
    private RichSpacer spacer15;
    private RichOutputText outputText7;
    private RichCommandButton cbAceptar;
    private RichSpacer spacer8;
    private RichCommandButton cbBorrar;
    private RichSpacer spacer12;
    private RichSpacer spacer14;
    private RichOutputText outputText3;
    private RichSpacer spacer17;
    private RichOutputText outputText4;
    private RichSpacer spacer18;
    private RichOutputText outputText5;
    private RichSpacer spacer19;
    private RichOutputText outputText6;
    private RichPanelGroupLayout panelGroupLayout15;
    private RichPanelGroupLayout panelGroupLayout16;
    private RichPanelGroupLayout panelGroupLayout17;
    private RichPanelGroupLayout panelGroupLayout18;
    private RichPanelGroupLayout panelGroupLayout19;
    private RichPanelGroupLayout panelGroupLayout20;
    //borrar y guardar
    private RichOutputText ot_confirmacion_borrar;
    private RichPanelFormLayout panelFormLayout4;
    private RichSelectManyListbox smlbPretratamiento;
    private UISelectItems siPretratamiento;
    private RichSelectManyListbox smlbPrimario;
    private UISelectItems siPrimario;
    private RichSelectManyListbox smlbSecundario;
    private UISelectItems siSecundario;
    private RichSelectManyListbox smlbTerciario;
    private UISelectItems siTerciario;
    private RichSelectManyListbox smlbOtro;
    private UISelectItems siOtro;
    private RichPopup pborrar;
    private RichPopup popup2;
    private RichDialog d_borrar;
    private RichPanelGroupLayout panelGroupLayout6;
    private RichOutputText otBorrar1;
    private RichSpacer spacer1;
    private RichOutputText otBorrar2;
    private RichPanelGroupLayout panelGroupLayout7;
    private RichCommandButton cb_si_borrar;
    private RichSpacer spacer2;
    private RichCommandButton cb_no_borrar;
    private RichDialog d_registro_exitoso;
    private RichPanelGroupLayout panelGroupLayout8;
    private RichPanelStretchLayout panelStretchLayout4;
    private RichCommandButton cbAceptarDi;
    private RichImage i1;
    private RichOutputText ot8;
    private RichPanelGroupLayout panelGroupLayout9;

    //miga pan
    private RichSpacer spacer20;
    private RichSpacer spacer21;
    private RichSpacer spacer22;
    private RichSpacer spacer23;
    private RichSpacer spacer24;
    private RichSpacer spacer25;
    private RichCommandLink clTodos;
    private RichCommandLink clUsuario;
    private RichCommandLink clPredio;
    private RichCommandLink clPermiso;
    private RichCommandLink clVertimientos;
    private RichOutputText ot11;
    private RichPanelGroupLayout panelGroupLayout50;

    private List<PuntoMonitoreo> listaPuntosMonitoreo;
    private PuntoMonitoreo puntoMonitoreo;
    private TreeModel puntosMonitoreoTreeModel;
    private RichPanelBox panelBox2;
    private RichPanelCollection panelCollection1;
    private RichTree tree1;
    private RichCommandLink clink;
    private RichMenu menu1;
    private RichCommandMenuItem cmi_adicionar;
    private String accionAdicionar;
    private RichSpacer spacer3;
    private RichSpacer spacer4;
    private RichOutputLabel outputLabel1;
    private RichPanelFormLayout panelFormLayout6;
    private RichSpacer spacer7;
    private RichOutputLabel outputLabel2;
    private RichInputText it_grad_long;
    private RichInputText it_min_long;
    private RichInputText it_seg_long;
    private RichPanelFormLayout panelFormLayout7;
    private RichSpacer spacer9;
    private RichActiveOutputText activeOutputText1;
    private RichSpacer spacer10;
    private RichSpacer spacer11;
    private RichSpacer spacer16;
    private RichSpacer spacer26;
    private RichSelectOneChoice socTipoFuente;
    private UISelectItems siTipoFuente;


    public DetalleVertimientoBean(){
        FacesUtils.setActiveBean("detalleVertimientoBean", "Detalle Vertimiento",
                                 UsuariosAguaDelegate.class);
        FacesUtils.removeManagedBeanFromSession("PuntosMonitoreoVertTreeHandler");

        this.load();
    }

    public void load(){
        try{
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
            ParametrosDelegate pd = ParametrosDelegate.getInstance();

            Object objUsuario = FacesUtils.getFromSession("usuarioSeleccionado");
            if(objUsuario instanceof UsuarioAgua){
                this.setUsuario((UsuarioAgua)objUsuario);
            }else{
                Long codigoUsuario = (Long)FacesUtils.getFromSession("usuarioSeleccionado");
                this.setUsuario(uad.getUsuarioAgua(codigoUsuario));
                FacesUtils.setInSession("usuarioSeleccionado", usuario);
            }

            Object objPredio = FacesUtils.getFromSession("predioSeleccionado");
            if(objPredio instanceof Predio){
                this.setPredio((Predio)FacesUtils.getFromSession("predioSeleccionado"));
            }else{
                Long codigoPredio = (Long)FacesUtils.getFromSession("predioSeleccionado");
                this.setPredio(uad.getPredio( codigoPredio ));
                FacesUtils.setInSession("predioSeleccionado", predio);
            }

            Object objPermiso = FacesUtils.getFromSession("permisoSeleccionado");
            if(objPermiso instanceof PermisoVertimiento){
                this.permiso = (PermisoVertimiento)FacesUtils.getFromSession("permisoSeleccionado");
            }else{
                Long codigoPermiso = (Long)FacesUtils.getFromSession("permisoSeleccionado");
                this.permiso = uad.getPermisoVertimiento(codigoPermiso);
                FacesUtils.setInSession("permisoSeleccionado", this.permiso);
            }

            Object obj = FacesUtils.getFromSession("vertimientoSeleccionado");
            if(obj instanceof PuntoVertimiento){
            //  System.out.println("+++++++++++++++++++++++++++ 2");
                this.vertimiento = (PuntoVertimiento)obj;
            }else{
                Long codigo = (Long)FacesUtils.getFromSession("vertimientoSeleccionado");
                this.vertimiento = uad.getVertimiento(codigo);
            }
           //CDONCEL
            if (this.vertimiento == null){
              //System.out.println("+++++++++++++++++++++++++++ 3");
              this.vertimiento = uad.getVertimientobyPerm(this.permiso);
            }
          // FIN CDONCEL
            UsuarioConectadoTO usuarioConectado =
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

            this.listaDepartamentos = this.cargarDivipola(null);
            this.listaArea = this.cargarZonificacion(null);
            this.listaTiposFuente = this.cargarParametro(ConstantesParametros.
                                                   CATEGORIA_TIPO_FUENTE);
            this.listaTipoCentroPoblado = this.cargarParametro(ConstantesParametros.
                                                   CATEGORIA_TIPO_CENTRO_POBLADO);
            this.listaSistemasReferencia = this.cargarParametro(ConstantesParametros.
                                                           CATEGORIA_SISTEMA_REFERENCIA);
            this.listaTipoVertimiento = this.cargarParametro(ConstantesParametros.
                                                   CATEGORIA_TIPO_VERTIMIENTO);
            this.listaTipoFlujo = this.cargarParametro(ConstantesParametros.
                                                   CATEGORIA_TIPO_FLUJO_VERTIMIENTO);
            this.listaPretratamiento = this.cargarParametro(ConstantesParametros.
                                                   CATEGORIA_PRETRATAMIENTO);
            this.listaPrimario = this.cargarParametro(ConstantesParametros.
                                                   CATEGORIA_TRATAMIENTO_PRIMARIO);
            this.listaSecundario = this.cargarParametro(ConstantesParametros.
                                                   CATEGORIA_TRATAMIENTO_SECUNDARIO);
            this.listaTerciario = this.cargarParametro(ConstantesParametros.
                                                   CATEGORIA_TRATAMIENTO_TERCIARIO);
            this.listaOtro = this.cargarParametro(ConstantesParametros.
                                                   CATEGORIA_TRATAMIENTO_OTRO);
            System.out.println("***************** V E R T I  1 : " + this.vertimiento.getId() );
            this.vertimiento.setListaPretratamiento(uad.getTratamientosByPuntoVertimientoCategoria(
                                            this.vertimiento.getId(),
                                            ConstantesParametros.CATEGORIA_PRETRATAMIENTO));
          
            this.vertimiento.setListaPrimario(uad.getTratamientosByPuntoVertimientoCategoria(
                                            this.vertimiento.getId(),
                                            ConstantesParametros.CATEGORIA_TRATAMIENTO_PRIMARIO));
            this.vertimiento.setListaSecundario(uad.getTratamientosByPuntoVertimientoCategoria(
                                            this.vertimiento.getId(),
                                            ConstantesParametros.CATEGORIA_TRATAMIENTO_SECUNDARIO));
            this.vertimiento.setListaTerciario(uad.getTratamientosByPuntoVertimientoCategoria(
                                            this.vertimiento.getId(),
                                            ConstantesParametros.CATEGORIA_TRATAMIENTO_TERCIARIO));
            this.vertimiento.setListaOtro(uad.getTratamientosByPuntoVertimientoCategoria(
                                            this.vertimiento.getId(),
                                            ConstantesParametros.CATEGORIA_TRATAMIENTO_OTRO));

            this.listaPretratamientoAsociado = new ArrayList();
            if(this.vertimiento.getListaPretratamiento()!=null){
                Iterator it = this.vertimiento.getListaPretratamiento().iterator();
                while(it.hasNext()){
                    PuntoVertimientoTratamiento tratamiento = (PuntoVertimientoTratamiento)it.next();
                    Lista lista = pd.getLista(tratamiento.getIdTratamiento());
                    this.listaPretratamientoAsociado.add(lista);
                }
            }
            this.listaPrimarioAsociado = new ArrayList();
            if(this.vertimiento.getListaPrimario()!=null){
                Iterator it = this.vertimiento.getListaPrimario().iterator();
                while(it.hasNext()){
                    PuntoVertimientoTratamiento tratamiento = (PuntoVertimientoTratamiento)it.next();
                    Lista lista = pd.getLista(tratamiento.getIdTratamiento());
                    this.listaPrimarioAsociado.add(lista);
                }
            }
            this.listaSecundarioAsociado = new ArrayList();
            if(this.vertimiento.getListaSecundario()!=null){
                Iterator it = this.vertimiento.getListaSecundario().iterator();
                while(it.hasNext()){
                    PuntoVertimientoTratamiento tratamiento = (PuntoVertimientoTratamiento)it.next();
                    Lista lista = pd.getLista(tratamiento.getIdTratamiento());
                    this.listaSecundarioAsociado.add(lista);
                }
            }
            this.listaTerciarioAsociado = new ArrayList();
            if(this.vertimiento.getListaTerciario()!=null){
                Iterator it = this.vertimiento.getListaTerciario().iterator();
                while(it.hasNext()){
                    PuntoVertimientoTratamiento tratamiento = (PuntoVertimientoTratamiento)it.next();
                    Lista lista = pd.getLista(tratamiento.getIdTratamiento());
                    this.listaTerciarioAsociado.add(lista);
                }
            }
            this.listaOtroAsociado = new ArrayList();
            if(this.vertimiento.getListaOtro()!=null){
                Iterator it = this.vertimiento.getListaOtro().iterator();
                while(it.hasNext()){
                    PuntoVertimientoTratamiento tratamiento = (PuntoVertimientoTratamiento)it.next();
                    Lista lista = pd.getLista(tratamiento.getIdTratamiento());
                    this.listaOtroAsociado.add(lista);
                }
            }


            this.listaDias = this.cargarDiasMes();

            this.listaMunicipios = new ArrayList();
            this.listaZona = new ArrayList();
            this.listaSubzona = new ArrayList();
            this.listaFuentes =  new ArrayList();
            this.listaTramos =  new ArrayList();

            if(this.vertimiento.getIdDepartamento()!=null){
                this.listaMunicipios = this.cargarDivipola(this.vertimiento.getIdDepartamento().longValue());
                this.vertimiento.setObjDepartamento(pd.getDivipolaById(this.vertimiento.getIdDepartamento().longValue()));
            }
            if(this.vertimiento.getIdMunicipio()!=null){
                this.vertimiento.setObjMunicipio(pd.getDivipolaById(this.vertimiento.getIdMunicipio().longValue()));
            }
            if(this.vertimiento.getIdTipoCentroPoblado()!=null){
                this.vertimiento.setObjTipoCentroPoblado(pd.getLista(this.vertimiento.getIdTipoCentroPoblado()));
            }
            if(this.vertimiento.getSistemaReferencia()!=null){
                this.vertimiento.setObjSistemaReferencia(pd.getLista(this.vertimiento.getSistemaReferencia()));
            }
            if(this.vertimiento.getTipoVertimiento()!=null){
                this.vertimiento.setObjTipoVertimiento(pd.getLista(this.vertimiento.getTipoVertimiento()));
            }
            if(this.vertimiento.getTipoFlujo()!=null){
                this.vertimiento.setObjTipoFlujo(pd.getLista(this.vertimiento.getTipoFlujo()));
            }
            if(this.vertimiento.getIdTramo()!=null ){
                this.listaZona = this.cargarZonificacion(this.vertimiento.getIdTramo().getIdArea().getId());
                this.listaSubzona = this.cargarZonificacion(this.vertimiento.getIdTramo().getIdZona().getId());
                this.listaFuentes = this.getListaFuentes(this.vertimiento.getIdTramo().getIdSubzona().getId()) ;
                this.listaTramos = this.getListaTramos(this.vertimiento.getIdTramo().getIdFuente().getId().intValue());
                this.vertimiento.setObjArea(this.vertimiento.getIdTramo().getIdArea());
                this.vertimiento.setObjZona(this.vertimiento.getIdTramo().getIdZona());
                this.vertimiento.setObjSubzona(this.vertimiento.getIdTramo().getIdSubzona());
            }


	    CalidadDelegate cald = CalidadDelegate.getInstance();

            //Construir el arbol.
            List listaNodos = new ArrayList();

            listaPuntosMonitoreo  = cald.getPuntosMonitoreoVert(this.vertimiento);

            this.vertimiento.setListaPuntosMonitoreoVert(listaPuntosMonitoreo);

            TreeNode nodoPuntos = new TreeNode(getText("MODULO_PUNTOS"),
                                               "PuntosMonitoreoVert");
            nodoPuntos.setData("PuntosMonitoreoVert");
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


    public void soc_area_valueChangeListener(ValueChangeEvent event) throws IdeamException{
        Object area = event.getNewValue();
        this.listaZona = new ArrayList();
        this.listaSubzona = new ArrayList();
        this.listaFuentes =  new ArrayList();
        this.listaTramos =  new ArrayList();
        try{
            if(area!=null){
                this.listaZona = this.cargarZonificacion(((PartZonificListas)area).getId());
            }
        }catch(IdeamException e){
            showMessage(e);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.soc_zona);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.soc_subzona);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.socFuente);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.socTramo);
    }


    public void soc_zona_valueChangeListener(ValueChangeEvent event) throws IdeamException{
        Object zona = event.getNewValue();
        this.listaSubzona = new ArrayList();
        this.listaFuentes =  new ArrayList();
        this.listaTramos =  new ArrayList();
        try{
            if(zona!=null){
                this.listaSubzona = this.cargarZonificacion(((PartZonificListas)zona).getId());
            }
        }catch(IdeamException e){
            showMessage(e);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.soc_subzona);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.socFuente);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.socTramo);
    }

    public void soc_subzona_valueChangeListener(ValueChangeEvent event) throws IdeamException{
        Object subzona = event.getNewValue();
        this.listaFuentes =  new ArrayList();
        this.listaTramos =  new ArrayList();;
        try{
            if(subzona!=null ){
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
        }catch(IdeamException e){
            showMessage(e);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.socFuente);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.socTramo);
    }
    
    public void socTipoFuente_valueChangeListener(ValueChangeEvent event) {
        Object tipoFuente = event.getNewValue();
        this.listaFuentes =  new ArrayList();
        this.listaTramos =  new ArrayList();
        try{
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
            if(fuente!=null){
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
        String mensaje = this.vertimiento.getGradosLat()+"\u00B0 " +
                         this.vertimiento.getMinutosLat() + "\'\' " +
                         this.vertimiento.getSegundosLat()+"\'" ;

        String params[] = {mensaje};
        String texto = getText("VRT_BORRAR", params);
        this.otBorrar1.setValue(texto);
        this.otBorrar2.setValue("");

        UsuarioConectadoTO usuarioConectado =
            (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

        Autoridades autoridadAmbiental = usuarioConectado.getUsuario().getAutoridadAmbiental();
        if(autoridadAmbiental==null ||
                        autoridadAmbiental.getId().intValue() == Constantes.IDEAM){
            showMessage(getText("NO_EXISTE_AUTORIDAD_AMBIENTAL"),
                                        FacesMessage.SEVERITY_ERROR);
        }else if (this.listaPuntosMonitoreo!=null
                        && this.listaPuntosMonitoreo.size()>0){
            showMessage(getText("VRT_SI_PUNTOS"));
        }else{
            AdfFacesContext.getCurrentInstance().addPartialTarget(otBorrar1);
            AdfFacesContext.getCurrentInstance().addPartialTarget(otBorrar2);
            AdfFacesContext.getCurrentInstance().addPartialTarget(pborrar);
            showPopup(pborrar, true);
        }
    }

    public void cb_no_borrar_actionListener(ActionEvent actionEvent) {
        showPopup(pborrar, false);
    }

    public void cb_si_borrar_actionListener(ActionEvent actionEvent) {
        try{
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
            
            //borrar el vertimiento
            uad.borrarVertimiento(this.vertimiento);
            
            try{
                
                /** 
                 * Estas variables son necesarias para llenar los datos intrinsecos de la auditoria.
                 */
                 UsuarioConectadoTO usuarioConectado = (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
                
                Auditoria auditoria = new Auditoria();
                auditoria.setIdUsuario(usuarioConectado.getUsuario().getCodigo());
                auditoria.setOperacion("BORRAR");
                auditoria.setObjeto("VERTIMIENTOS");
                auditoria.setMetodo(Thread.currentThread().getStackTrace()[1].getMethodName());
                auditoria.setClase(this.getClass().getName());
                //Objeto Afectado (usuario modificado, eliminado o agregado) 
                auditoria.setIdObjeto(vertimiento.getId());
               
                AuditoriasDelegate audDelegate = AuditoriasDelegate.getInstance();
                audDelegate.setAuditoria(auditoria);
                
            }catch(Exception e){
                System.out.println("No se pudo Almacenar la auditoria: ---------------------------------- ");
                System.out.println(e.getMessage()+"------------------------------------------------------ ");
            }

            showMessage(getText("VRT_ELIMINADA"));
        }catch(IdeamException e){
            showMessage(e);
        }
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

        if(this.socFuente.getValue()==null){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.socFuente);
            continuar = false;
        }

        if(this.socTramo.getValue()==null){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.socTramo);
            continuar = false;
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

        //caracteristicas
        if(this.socTipoFlujo.getValue()==null){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.socTipoFlujo);
            continuar = false;
        }
        if(this.itTiempoDescarga.getValue()==null){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.itTiempoDescarga);
            continuar = false;
        }
        if(this.socFrecuencia.getValue()==null){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.socFrecuencia);
            continuar = false;
        }
        if(this.itCaudalDisegno.getValue()==null){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.itCaudalDisegno);
            continuar = false;
        }

        //validaciones de georeferenciación
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
            
            
            
            try{
                
                /** 
                 * Estas variables son necesarias para llenar los datos intrinsecos de la auditoria.
                 */
                // UsuarioConectadoTO usuarioConectado = (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
                
                Auditoria auditoria = new Auditoria();
                auditoria.setIdUsuario(usuarioConectado.getUsuario().getCodigo());
                auditoria.setOperacion("MODIFICAR");
                auditoria.setObjeto("VERTIMIENTOS");
                auditoria.setMetodo(Thread.currentThread().getStackTrace()[1].getMethodName());
                auditoria.setClase(this.getClass().getName());
                //Objeto Afectado (usuario modificado, eliminado o agregado) 
                auditoria.setIdObjeto( this.vertimiento.getId());
               
                AuditoriasDelegate audDelegate = AuditoriasDelegate.getInstance();
                audDelegate.setAuditoria(auditoria);
                
            }catch(Exception e){
                System.out.println("No se pudo Almacenar la auditoria: ---------------------------------- ");
                System.out.println(e.getMessage()+"------------------------------------------------------ ");
            }


            
            
            showPopup(popup2, true);
        }
    }

    public void save(){
        try{
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();

            this.vertimiento.setIdFuente((FnttFuente)this.socFuente.getValue());
            this.vertimiento.setIdTramo((FnttTramo)this.socTramo.getValue());
            this.vertimiento.setTipoVertimiento(((Lista)this.socTipoVertimiento.getValue()).getCodigo());

            this.vertimiento.setIdDepartamento(((Divipola)this.socDepartamento.getValue()).getId().intValue());
            this.vertimiento.setIdMunicipio(((Divipola)this.socMunicipio.getValue()).getId().intValue());
            this.vertimiento.setIdTipoCentroPoblado(((Lista)this.socTipoCP.getValue()).getCodigo());
            this.vertimiento.setNombreCentroPoblado((this.itNombreCentroPoblado.getValue()!=null)?this.itNombreCentroPoblado.getValue().toString():null);

            this.vertimiento.setTipoFlujo(((Lista)this.socTipoFlujo.getValue()).getCodigo());
            this.vertimiento.setFrecuencia(Integer.parseInt(this.socFrecuencia.getValue().toString()));
            this.vertimiento.setTiempoDescarga(Double.parseDouble(this.itTiempoDescarga.getValue().toString()));
            this.vertimiento.setCaudalDisegno(Double.parseDouble(this.itCaudalDisegno.getValue().toString()));

            this.vertimiento.setGradosLat(Integer.parseInt(this.itGradosLat.getValue().toString()));
            this.vertimiento.setMinutosLat(Integer.parseInt(this.itMinutosLat.getValue().toString()));
            this.vertimiento.setSegundosLat(Double.parseDouble(this.itSegundosLat.getValue().toString()));


            this.vertimiento.setGradosLong(Integer.parseInt(this.it_grad_long.getValue().toString()));
            this.vertimiento.setMinutosLong(Integer.parseInt(this.it_min_long.getValue().toString()));
            this.vertimiento.setSegundosLong(Double.parseDouble(this.it_seg_long.getValue().toString()));

            this.vertimiento.setAltitud(Double.parseDouble(this.itAltitud.getValue().toString()));
            this.vertimiento.setSistemaReferencia(((Lista)this.socSistemaReferencia.getValue()).getCodigo());
            this.vertimiento.setDescripcionAcceso(this.itDescripcionAcceso.getValue().toString());

            //borrar los tratamientos asociados
            if(this.vertimiento.getListaPretratamiento()!=null){
                for(PuntoVertimientoTratamiento tratamiento : this.vertimiento.getListaPretratamiento()){
                    uad.deletePuntoTratamiento(tratamiento);
                }
                this.vertimiento.getListaPretratamiento().clear();
            }
            if(this.vertimiento.getListaPrimario()!=null){
                for(PuntoVertimientoTratamiento tratamiento : this.vertimiento.getListaPrimario()){
                    uad.deletePuntoTratamiento(tratamiento);
                }
                this.vertimiento.getListaPrimario().clear();
            }
            if(this.vertimiento.getListaSecundario()!=null){
                for(PuntoVertimientoTratamiento tratamiento : this.vertimiento.getListaSecundario()){
                    uad.deletePuntoTratamiento(tratamiento);
                }
                this.vertimiento.getListaSecundario().clear();
            }
            if(this.vertimiento.getListaTerciario()!=null){
                for(PuntoVertimientoTratamiento tratamiento : this.vertimiento.getListaTerciario()){
                    uad.deletePuntoTratamiento(tratamiento);
                }
                this.vertimiento.getListaTerciario().clear();
            }
            if(this.vertimiento.getListaOtro()!=null){
                for(PuntoVertimientoTratamiento tratamiento : this.vertimiento.getListaOtro()){
                    uad.deletePuntoTratamiento(tratamiento);
                }
                this.vertimiento.getListaOtro().clear();
            }


            //relacionar los tratamientos asociados
            UsuarioConectadoTO usuarioConectado =
            (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
            List<PuntoVertimientoTratamiento> listTratamientos = new ArrayList<PuntoVertimientoTratamiento>();
            if(this.smlbPretratamiento.getValue()!=null){
                Iterator it= ((List)this.smlbPretratamiento.getValue()).iterator();
                while(it.hasNext()){
                    Lista lis = (Lista)it.next();
                    PuntoVertimientoTratamiento nuevo = new PuntoVertimientoTratamiento();
                    nuevo.setIdPuntoVertimiento(this.vertimiento.getId());
                    nuevo.setIdTratamiento(lis.getCodigo());
                    nuevo.setIdCategoria(lis.getCategoria().getCodigo().intValue());
                    nuevo.setCodigoAutoridad(new Long(usuarioConectado.getUsuario().getAutoridadAmbiental().getId()));
                    listTratamientos.add(nuevo);
                }
            }
            if(this.smlbPrimario.getValue()!=null){
                Iterator it= ((List)this.smlbPrimario.getValue()).iterator();
                while(it.hasNext()){
                    Lista lis = (Lista)it.next();
                    PuntoVertimientoTratamiento nuevo = new PuntoVertimientoTratamiento();
                    nuevo.setIdPuntoVertimiento(this.vertimiento.getId());
                    nuevo.setIdTratamiento(lis.getCodigo());
                    nuevo.setIdCategoria(lis.getCategoria().getCodigo().intValue());
                    nuevo.setCodigoAutoridad(new Long(usuarioConectado.getUsuario().getAutoridadAmbiental().getId()));
                    listTratamientos.add(nuevo);
                }
            }
            if(this.smlbSecundario.getValue()!=null){
                Iterator it= ((List)this.smlbSecundario.getValue()).iterator();
                while(it.hasNext()){
                    Lista lis = (Lista)it.next();
                    PuntoVertimientoTratamiento nuevo = new PuntoVertimientoTratamiento();
                    nuevo.setIdPuntoVertimiento(this.vertimiento.getId());
                    nuevo.setIdTratamiento(lis.getCodigo());
                    nuevo.setIdCategoria(lis.getCategoria().getCodigo().intValue());
                    nuevo.setCodigoAutoridad(new Long(usuarioConectado.getUsuario().getAutoridadAmbiental().getId()));
                    listTratamientos.add(nuevo);
                }
            }
            if(this.smlbTerciario.getValue()!=null){
                Iterator it= ((List)this.smlbTerciario.getValue()).iterator();
                while(it.hasNext()){
                    Lista lis = (Lista)it.next();
                    PuntoVertimientoTratamiento nuevo = new PuntoVertimientoTratamiento();
                    nuevo.setIdPuntoVertimiento(this.vertimiento.getId());
                    nuevo.setIdTratamiento(lis.getCodigo());
                    nuevo.setIdCategoria(lis.getCategoria().getCodigo().intValue());
                    nuevo.setCodigoAutoridad(new Long(usuarioConectado.getUsuario().getAutoridadAmbiental().getId()));
                    listTratamientos.add(nuevo);
                }
            }
            if(this.smlbOtro.getValue()!=null){
                Iterator it= ((List)this.smlbOtro.getValue()).iterator();
                while(it.hasNext()){
                    Lista lis = (Lista)it.next();
                    PuntoVertimientoTratamiento nuevo = new PuntoVertimientoTratamiento();
                    nuevo.setIdPuntoVertimiento(this.vertimiento.getId());
                    nuevo.setIdTratamiento(lis.getCodigo());
                    nuevo.setIdCategoria(lis.getCategoria().getCodigo().intValue());
                    nuevo.setCodigoAutoridad(new Long(usuarioConectado.getUsuario().getAutoridadAmbiental().getId()));
                    listTratamientos.add(nuevo);
                }
            }

            if(!listTratamientos.isEmpty()){
                this.vertimiento.setListTratamientos(new ArrayList());
                this.vertimiento.setListTratamientos(listTratamientos);
                /*for(PuntoVertimientoTratamiento tratamiento : listTratamientos){
                    uad.createPuntoTratamiento(tratamiento);
                }*/
            }
            
            uad.updateVertimiento(this.vertimiento);

        }catch(IdeamException e){
            showMessage(e);
        }
    }

    public void cmi_adicionar_puntoMonitoreo_actionListener(ActionEvent actionEvent) {
        FacesUtils.setInSession("vertimientoSeleccionado", this.vertimiento);
        accionAdicionar = "adicionarPuntoVert";
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

    public void setVertimiento(PuntoVertimiento vertimiento) {
        this.vertimiento = vertimiento;
    }

    public PuntoVertimiento getVertimiento() {
        return vertimiento;
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
    
    public void setListaTiposFuente(List listaTiposFuente) {
        this.listaTiposFuente = listaTiposFuente;
    }

    public List getListaTiposFuente() {
        return listaTiposFuente;
    }

    public void setListaSubzona(List listaSubzona) {
        this.listaSubzona = listaSubzona;
    }

    public List getListaSubzona() {
        return listaSubzona;
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

    public void setListaTipoVertimiento(List listaTipoVertimiento) {
        this.listaTipoVertimiento = listaTipoVertimiento;
    }

    public List getListaTipoVertimiento() {
        return listaTipoVertimiento;
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

    public void setListaTipoFlujo(List listaTipoFlujo) {
        this.listaTipoFlujo = listaTipoFlujo;
    }

    public List getListaTipoFlujo() {
        return listaTipoFlujo;
    }

    public void setListaPretratamiento(List listaPretratamiento) {
        this.listaPretratamiento = listaPretratamiento;
    }

    public List getListaPretratamiento() {
        return listaPretratamiento;
    }

    public void setListaPrimario(List listaPrimario) {
        this.listaPrimario = listaPrimario;
    }

    public List getListaPrimario() {
        return listaPrimario;
    }

    public void setListaSecundario(List listaSecundario) {
        this.listaSecundario = listaSecundario;
    }

    public List getListaSecundario() {
        return listaSecundario;
    }

    public void setListaTerciario(List listaTerciario) {
        this.listaTerciario = listaTerciario;
    }

    public List getListaTerciario() {
        return listaTerciario;
    }

    public void setListaOtro(List listaOtro) {
        this.listaOtro = listaOtro;
    }

    public List getListaOtro() {
        return listaOtro;
    }

    public void setListaDias(List listaDias) {
        this.listaDias = listaDias;
    }

    public List getListaDias() {
        return listaDias;
    }

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

    public void setPermiso(PermisoVertimiento permiso) {
        this.permiso = permiso;
    }

    public PermisoVertimiento getPermiso() {
        return permiso;
    }

    public void setListaPretratamientoAsociado(List listaPretratamientoAsociado) {
        this.listaPretratamientoAsociado = listaPretratamientoAsociado;
    }

    public List getListaPretratamientoAsociado() {
        return listaPretratamientoAsociado;
    }

    public void setListaPrimarioAsociado(List listaPrimarioAsociado) {
        this.listaPrimarioAsociado = listaPrimarioAsociado;
    }

    public List getListaPrimarioAsociado() {
        return listaPrimarioAsociado;
    }

    public void setListaSecundarioAsociado(List listaSecundarioAsociado) {
        this.listaSecundarioAsociado = listaSecundarioAsociado;
    }

    public List getListaSecundarioAsociado() {
        return listaSecundarioAsociado;
    }

    public void setListaTerciarioAsociado(List listaTerciarioAsociado) {
        this.listaTerciarioAsociado = listaTerciarioAsociado;
    }

    public List getListaTerciarioAsociado() {
        return listaTerciarioAsociado;
    }

    public void setListaOtroAsociado(List listaOtroAsociado) {
        this.listaOtroAsociado = listaOtroAsociado;
    }

    public List getListaOtroAsociado() {
        return listaOtroAsociado;
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

    public void setPanelStretchLayout2(RichPanelStretchLayout panelStretchLayout2) {
        this.panelStretchLayout2 = panelStretchLayout2;
    }

    public RichPanelStretchLayout getPanelStretchLayout2() {
        return panelStretchLayout2;
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

    public void setDecorativeBox1(RichDecorativeBox decorativeBox1) {
        this.decorativeBox1 = decorativeBox1;
    }

    public RichDecorativeBox getDecorativeBox1() {
        return decorativeBox1;
    }

    public void setDecorativeBox2(RichDecorativeBox decorativeBox2) {
        this.decorativeBox2 = decorativeBox2;
    }

    public RichDecorativeBox getDecorativeBox2() {
        return decorativeBox2;
    }

    public void setPanelFormLayout1(RichPanelFormLayout panelFormLayout1) {
        this.panelFormLayout1 = panelFormLayout1;
    }

    public RichPanelFormLayout getPanelFormLayout1() {
        return panelFormLayout1;
    }

    public void setPanelFormLayout2(RichPanelFormLayout panelFormLayout2) {
        this.panelFormLayout2 = panelFormLayout2;
    }

    public RichPanelFormLayout getPanelFormLayout2() {
        return panelFormLayout2;
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


    public void setPanelFormLayout3(RichPanelFormLayout panelFormLayout3) {
        this.panelFormLayout3 = panelFormLayout3;
    }

    public RichPanelFormLayout getPanelFormLayout3() {
        return panelFormLayout3;
    }


    public void setPanelFormLayout5(RichPanelFormLayout panelFormLayout5) {
        this.panelFormLayout5 = panelFormLayout5;
    }

    public RichPanelFormLayout getPanelFormLayout5() {
        return panelFormLayout5;
    }

    public void setPanelGroupLayout4(RichPanelGroupLayout panelGroupLayout4) {
        this.panelGroupLayout4 = panelGroupLayout4;
    }

    public RichPanelGroupLayout getPanelGroupLayout4() {
        return panelGroupLayout4;
    }

    public void setPanelGroupLayout5(RichPanelGroupLayout panelGroupLayout5) {
        this.panelGroupLayout5 = panelGroupLayout5;
    }

    public RichPanelGroupLayout getPanelGroupLayout5() {
        return panelGroupLayout5;
    }

    public void setCbAceptar(RichCommandButton cbAceptar) {
        this.cbAceptar = cbAceptar;
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

    public void setCbBorrar(RichCommandButton cbBorrar) {
        this.cbBorrar = cbBorrar;
    }

    public RichCommandButton getCbBorrar() {
        return cbBorrar;
    }

    public void setSocTipoVertimiento(RichSelectOneChoice socTipoVertimiento) {
        this.socTipoVertimiento = socTipoVertimiento;
    }

    public RichSelectOneChoice getSocTipoVertimiento() {
        return socTipoVertimiento;
    }

    public void setSiTipoVertimiento(UISelectItems siTipoVertimiento) {
        this.siTipoVertimiento = siTipoVertimiento;
    }

    public UISelectItems getSiTipoVertimiento() {
        return siTipoVertimiento;
    }

    public void setSoc_tipo_fuente(RichSelectOneChoice soc_tipo_fuente) {
        this.soc_tipo_fuente = soc_tipo_fuente;
    }

    public RichSelectOneChoice getSoc_tipo_fuente() {
        return soc_tipo_fuente;
    }

    public void setSi_tipo_fuente(UISelectItems si_tipo_fuente) {
        this.si_tipo_fuente = si_tipo_fuente;
    }

    public UISelectItems getSi_tipo_fuente() {
        return si_tipo_fuente;
    }

    public void setSoc_area(RichSelectOneChoice soc_area) {
        this.soc_area = soc_area;
    }

    public RichSelectOneChoice getSoc_area() {
        return soc_area;
    }

    public void setSi_area(UISelectItems si_area) {
        this.si_area = si_area;
    }

    public UISelectItems getSi_area() {
        return si_area;
    }

    public void setSoc_zona(RichSelectOneChoice soc_zona) {
        this.soc_zona = soc_zona;
    }

    public RichSelectOneChoice getSoc_zona() {
        return soc_zona;
    }

    public void setSi_zona(UISelectItems si_zona) {
        this.si_zona = si_zona;
    }

    public UISelectItems getSi_zona() {
        return si_zona;
    }

    public void setSoc_subzona(RichSelectOneChoice soc_subzona) {
        this.soc_subzona = soc_subzona;
    }

    public RichSelectOneChoice getSoc_subzona() {
        return soc_subzona;
    }

    public void setSi_subzona(UISelectItems si_subzona) {
        this.si_subzona = si_subzona;
    }

    public UISelectItems getSi_subzona() {
        return si_subzona;
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

    public void setCbPrevUbicacion(RichCommandButton cbPrevUbicacion) {
        this.cbPrevUbicacion = cbPrevUbicacion;
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

    public void setCbNextUbicacion(RichCommandButton cbNextUbicacion) {
        this.cbNextUbicacion = cbNextUbicacion;
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



    public void setItDescripcionAcceso(RichInputText itDescripcionAcceso) {
        this.itDescripcionAcceso = itDescripcionAcceso;
    }

    public RichInputText getItDescripcionAcceso() {
        return itDescripcionAcceso;
    }

    public void setSocTipoFlujo(RichSelectOneChoice socTipoFlujo) {
        this.socTipoFlujo = socTipoFlujo;
    }

    public RichSelectOneChoice getSocTipoFlujo() {
        return socTipoFlujo;
    }

    public void setSiTipoFlujo(UISelectItems siTipoFlujo) {
        this.siTipoFlujo = siTipoFlujo;
    }

    public UISelectItems getSiTipoFlujo() {
        return siTipoFlujo;
    }

    public void setItTiempoDescarga(RichInputText itTiempoDescarga) {
        this.itTiempoDescarga = itTiempoDescarga;
    }

    public RichInputText getItTiempoDescarga() {
        return itTiempoDescarga;
    }

    public void setItCaudalDisegno(RichInputText itCaudalDisegno) {
        this.itCaudalDisegno = itCaudalDisegno;
    }

    public RichInputText getItCaudalDisegno() {
        return itCaudalDisegno;
    }

    public void setSocFrecuencia(RichSelectOneChoice socFrecuencia) {
        this.socFrecuencia = socFrecuencia;
    }

    public RichSelectOneChoice getSocFrecuencia() {
        return socFrecuencia;
    }

    public void setSiFrecuencia(UISelectItems siFrecuencia) {
        this.siFrecuencia = siFrecuencia;
    }

    public UISelectItems getSiFrecuencia() {
        return siFrecuencia;
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

    public void setPanelGroupLayout12(RichPanelGroupLayout panelGroupLayout12) {
        this.panelGroupLayout12 = panelGroupLayout12;
    }

    public RichPanelGroupLayout getPanelGroupLayout12() {
        return panelGroupLayout12;
    }


    public void setSpacer15(RichSpacer spacer15) {
        this.spacer15 = spacer15;
    }

    public RichSpacer getSpacer15() {
        return spacer15;
    }


    public void setSpacer12(RichSpacer spacer12) {
        this.spacer12 = spacer12;
    }

    public RichSpacer getSpacer12() {
        return spacer12;
    }

    public void setOutputText3(RichOutputText outputText3) {
        this.outputText3 = outputText3;
    }

    public RichOutputText getOutputText3() {
        return outputText3;
    }

    public void setSpacer17(RichSpacer spacer17) {
        this.spacer17 = spacer17;
    }

    public RichSpacer getSpacer17() {
        return spacer17;
    }

    public void setOutputText4(RichOutputText outputText4) {
        this.outputText4 = outputText4;
    }

    public RichOutputText getOutputText4() {
        return outputText4;
    }

    public void setSpacer18(RichSpacer spacer18) {
        this.spacer18 = spacer18;
    }

    public RichSpacer getSpacer18() {
        return spacer18;
    }

    public void setOutputText5(RichOutputText outputText5) {
        this.outputText5 = outputText5;
    }

    public RichOutputText getOutputText5() {
        return outputText5;
    }

    public void setSpacer19(RichSpacer spacer19) {
        this.spacer19 = spacer19;
    }

    public RichSpacer getSpacer19() {
        return spacer19;
    }

    public void setOutputText6(RichOutputText outputText6) {
        this.outputText6 = outputText6;
    }

    public RichOutputText getOutputText6() {
        return outputText6;
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

    public void setPanelGroupLayout17(RichPanelGroupLayout panelGroupLayout17) {
        this.panelGroupLayout17 = panelGroupLayout17;
    }

    public RichPanelGroupLayout getPanelGroupLayout17() {
        return panelGroupLayout17;
    }

    public void setPanelGroupLayout18(RichPanelGroupLayout panelGroupLayout18) {
        this.panelGroupLayout18 = panelGroupLayout18;
    }

    public RichPanelGroupLayout getPanelGroupLayout18() {
        return panelGroupLayout18;
    }


    public void setSpacer14(RichSpacer spacer14) {
        this.spacer14 = spacer14;
    }

    public RichSpacer getSpacer14() {
        return spacer14;
    }

    public void setOutputText7(RichOutputText outputText7) {
        this.outputText7 = outputText7;
    }

    public RichOutputText getOutputText7() {
        return outputText7;
    }

    public void setPanelGroupLayout19(RichPanelGroupLayout panelGroupLayout19) {
        this.panelGroupLayout19 = panelGroupLayout19;
    }

    public RichPanelGroupLayout getPanelGroupLayout19() {
        return panelGroupLayout19;
    }


    public void setOt_confirmacion_borrar(RichOutputText ot_confirmacion_borrar) {
        this.ot_confirmacion_borrar = ot_confirmacion_borrar;
    }

    public RichOutputText getOt_confirmacion_borrar() {
        return ot_confirmacion_borrar;
    }


    public void setPanelFormLayout4(RichPanelFormLayout panelFormLayout4) {
        this.panelFormLayout4 = panelFormLayout4;
    }

    public RichPanelFormLayout getPanelFormLayout4() {
        return panelFormLayout4;
    }

    public void setSmlbPretratamiento(RichSelectManyListbox selectOneChoice1) {
        this.smlbPretratamiento = selectOneChoice1;
    }

    public RichSelectManyListbox getSmlbPretratamiento() {
        return smlbPretratamiento;
    }

    public void setSiPretratamiento(UISelectItems selectItems1) {
        this.siPretratamiento = selectItems1;
    }

    public UISelectItems getSiPretratamiento() {
        return siPretratamiento;
    }

    public void setPanelFormLayout41(RichPanelFormLayout panelFormLayout41) {
        this.panelFormLayout41 = panelFormLayout41;
    }

    public RichPanelFormLayout getPanelFormLayout41() {
        return panelFormLayout41;
    }

    public void setSmlbPrimario(RichSelectManyListbox selectOneChoice2) {
        this.smlbPrimario = selectOneChoice2;
    }

    public RichSelectManyListbox getSmlbPrimario() {
        return smlbPrimario;
    }

    public void setSiPrimario(UISelectItems selectItems2) {
        this.siPrimario = selectItems2;
    }

    public UISelectItems getSiPrimario() {
        return siPrimario;
    }

    public void setSmlbSecundario(RichSelectManyListbox selectOneChoice3) {
        this.smlbSecundario = selectOneChoice3;
    }

    public RichSelectManyListbox getSmlbSecundario() {
        return smlbSecundario;
    }

    public void setSiSecundario(UISelectItems selectItems3) {
        this.siSecundario = selectItems3;
    }

    public UISelectItems getSiSecundario() {
        return siSecundario;
    }

    public void setSmlbTerciario(RichSelectManyListbox selectOneChoice4) {
        this.smlbTerciario = selectOneChoice4;
    }

    public RichSelectManyListbox getSmlbTerciario() {
        return smlbTerciario;
    }

    public void setSiTerciario(UISelectItems selectItems4) {
        this.siTerciario = selectItems4;
    }

    public UISelectItems getSiTerciario() {
        return siTerciario;
    }

    public void setSmlbOtro(RichSelectManyListbox selectOneChoice5) {
        this.smlbOtro = selectOneChoice5;
    }

    public RichSelectManyListbox getSmlbOtro() {
        return smlbOtro;
    }

    public void setSiOtro(UISelectItems selectItems5) {
        this.siOtro = selectItems5;
    }

    public UISelectItems getSiOtro() {
        return siOtro;
    }

    public void setPborrar(RichPopup popup1) {
        this.pborrar = popup1;
    }

    public RichPopup getPborrar() {
        return pborrar;
    }

    public void setPopup2(RichPopup popup2) {
        this.popup2 = popup2;
    }

    public RichPopup getPopup2() {
        return popup2;
    }

    public void setD_borrar(RichDialog dialog1) {
        this.d_borrar = dialog1;
    }

    public RichDialog getD_borrar() {
        return d_borrar;
    }

    public void setPanelGroupLayout6(RichPanelGroupLayout panelGroupLayout6) {
        this.panelGroupLayout6 = panelGroupLayout6;
    }

    public RichPanelGroupLayout getPanelGroupLayout6() {
        return panelGroupLayout6;
    }

    public void setOtBorrar1(RichOutputText outputText1) {
        this.otBorrar1 = outputText1;
    }

    public RichOutputText getOtBorrar1() {
        return otBorrar1;
    }

    public void setSpacer1(RichSpacer spacer1) {
        this.spacer1 = spacer1;
    }

    public RichSpacer getSpacer1() {
        return spacer1;
    }

    public void setOtBorrar2(RichOutputText outputText8) {
        this.otBorrar2 = outputText8;
    }

    public RichOutputText getOtBorrar2() {
        return otBorrar2;
    }

    public void setPanelGroupLayout7(RichPanelGroupLayout panelGroupLayout7) {
        this.panelGroupLayout7 = panelGroupLayout7;
    }

    public RichPanelGroupLayout getPanelGroupLayout7() {
        return panelGroupLayout7;
    }

    public void setCb_si_borrar(RichCommandButton commandButton1) {
        this.cb_si_borrar = commandButton1;
    }

    public RichCommandButton getCb_si_borrar() {
        return cb_si_borrar;
    }

    public void setSpacer2(RichSpacer spacer2) {
        this.spacer2 = spacer2;
    }

    public RichSpacer getSpacer2() {
        return spacer2;
    }

    public void setCb_no_borrar(RichCommandButton commandButton2) {
        this.cb_no_borrar = commandButton2;
    }

    public RichCommandButton getCb_no_borrar() {
        return cb_no_borrar;
    }

    public void setD_registro_exitoso(RichDialog dialog1) {
        this.d_registro_exitoso = dialog1;
    }

    public RichDialog getD_registro_exitoso() {
        return d_registro_exitoso;
    }

    public void setPanelGroupLayout8(RichPanelGroupLayout panelGroupLayout8) {
        this.panelGroupLayout8 = panelGroupLayout8;
    }

    public RichPanelGroupLayout getPanelGroupLayout8() {
        return panelGroupLayout8;
    }

    public void setPanelStretchLayout4(RichPanelStretchLayout panelStretchLayout4) {
        this.panelStretchLayout4 = panelStretchLayout4;
    }

    public RichPanelStretchLayout getPanelStretchLayout4() {
        return panelStretchLayout4;
    }

    public void setCbAceptarDi(RichCommandButton commandButton1) {
        this.cbAceptarDi = commandButton1;
    }

    public RichCommandButton getCbAceptarDi() {
        return cbAceptarDi;
    }

    public void setI1(RichImage image1) {
        this.i1 = image1;
    }

    public RichImage getI1() {
        return i1;
    }

    public void setOt8(RichOutputText outputText1) {
        this.ot8 = outputText1;
    }

    public RichOutputText getOt8() {
        return ot8;
    }

    public void setPanelGroupLayout9(RichPanelGroupLayout panelGroupLayout9) {
        this.panelGroupLayout9 = panelGroupLayout9;
    }

    public RichPanelGroupLayout getPanelGroupLayout9() {
        return panelGroupLayout9;
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

    public void setClTodos(RichCommandLink clTodos) {
        this.clTodos = clTodos;
    }

    public RichCommandLink getClTodos() {
        return clTodos;
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

    public void setClPermiso(RichCommandLink clPermiso) {
        this.clPermiso = clPermiso;
    }

    public RichCommandLink getClPermiso() {
        return clPermiso;
    }

    public void setClVertimientos(RichCommandLink clVertimientos) {
        this.clVertimientos = clVertimientos;
    }

    public RichCommandLink getClVertimientos() {
        return clVertimientos;
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

    public void setPanelBox2(RichPanelBox panelBox2) {
        this.panelBox2 = panelBox2;
    }

    public RichPanelBox getPanelBox2() {
        return panelBox2;
    }

    public void setPanelCollection1(RichPanelCollection panelCollection1) {
        this.panelCollection1 = panelCollection1;
    }

    public RichPanelCollection getPanelCollection1() {
        return panelCollection1;
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

    public void setMenu1(RichMenu menu1) {
        this.menu1 = menu1;
    }

    public RichMenu getMenu1() {
        return menu1;
    }

    public void setCmi_adicionar(RichCommandMenuItem cmi_adicionar) {
        this.cmi_adicionar = cmi_adicionar;
    }

    public RichCommandMenuItem getCmi_adicionar() {
        return cmi_adicionar;
    }

    public void setPanelGroupLayout20(RichPanelGroupLayout panelGroupLayout20) {
        this.panelGroupLayout20 = panelGroupLayout20;
    }

    public RichPanelGroupLayout getPanelGroupLayout20() {
        return panelGroupLayout20;
    }

    public void setAccionAdicionar(String accionAdicionar) {
        this.accionAdicionar = accionAdicionar;
    }

    public String getAccionAdicionar() {
        return accionAdicionar;
    }

    public void setOt11(RichOutputText ot11) {
        this.ot11 = ot11;
    }

    public RichOutputText getOt11() {
        return ot11;
    }


    public void setPanelGroupLayout50(RichPanelGroupLayout panelGroupLayout50) {
        this.panelGroupLayout50 = panelGroupLayout50;
    }

    public RichPanelGroupLayout getPanelGroupLayout50() {
        return panelGroupLayout50;
    }

    public void setSpacer3(RichSpacer spacer3) {
        this.spacer3 = spacer3;
    }

    public RichSpacer getSpacer3() {
        return spacer3;
    }

    public void setSpacer4(RichSpacer spacer4) {
        this.spacer4 = spacer4;
    }

    public RichSpacer getSpacer4() {
        return spacer4;
    }

    public void setOutputLabel1(RichOutputLabel outputLabel1) {
        this.outputLabel1 = outputLabel1;
    }

    public RichOutputLabel getOutputLabel1() {
        return outputLabel1;
    }

    public void setPanelFormLayout6(RichPanelFormLayout panelFormLayout6) {
        this.panelFormLayout6 = panelFormLayout6;
    }

    public RichPanelFormLayout getPanelFormLayout6() {
        return panelFormLayout6;
    }

    public void setSpacer7(RichSpacer spacer7) {
        this.spacer7 = spacer7;
    }

    public RichSpacer getSpacer7() {
        return spacer7;
    }

    public void setOutputLabel2(RichOutputLabel outputLabel2) {
        this.outputLabel2 = outputLabel2;
    }

    public RichOutputLabel getOutputLabel2() {
        return outputLabel2;
    }





    public void setIt_min_long(RichInputText it_min_long) {
        this.it_min_long = it_min_long;
    }

    public RichInputText getIt_min_long() {
        return it_min_long;
    }

    public void setIt_seg_long(RichInputText it_seg_long) {
        this.it_seg_long = it_seg_long;
    }

    public RichInputText getIt_seg_long() {
        return it_seg_long;
    }

    public void setPanelFormLayout7(RichPanelFormLayout panelFormLayout7) {
        this.panelFormLayout7 = panelFormLayout7;
    }

    public RichPanelFormLayout getPanelFormLayout7() {
        return panelFormLayout7;
    }

    public void setSpacer9(RichSpacer spacer9) {
        this.spacer9 = spacer9;
    }

    public RichSpacer getSpacer9() {
        return spacer9;
    }

    public void setActiveOutputText1(RichActiveOutputText activeOutputText1) {
        this.activeOutputText1 = activeOutputText1;
    }

    public RichActiveOutputText getActiveOutputText1() {
        return activeOutputText1;
    }

    public void setItAltitud(RichInputText itAltitud) {
        this.itAltitud = itAltitud;
    }

    public RichInputText getItAltitud() {
        return itAltitud;
    }

    public void setIt_grad_long(RichInputText it_grad_long) {
        this.it_grad_long = it_grad_long;
    }

    public RichInputText getIt_grad_long() {
        return it_grad_long;
    }

    public void setSpacer10(RichSpacer spacer10) {
        this.spacer10 = spacer10;
    }

    public RichSpacer getSpacer10() {
        return spacer10;
    }

    public void setSpacer11(RichSpacer spacer11) {
        this.spacer11 = spacer11;
    }

    public RichSpacer getSpacer11() {
        return spacer11;
    }

    public void setSpacer16(RichSpacer spacer16) {
        this.spacer16 = spacer16;
    }

    public RichSpacer getSpacer16() {
        return spacer16;
    }

    public void setSpacer26(RichSpacer spacer26) {
        this.spacer26 = spacer26;
    }

    public RichSpacer getSpacer26() {
        return spacer26;
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

}
