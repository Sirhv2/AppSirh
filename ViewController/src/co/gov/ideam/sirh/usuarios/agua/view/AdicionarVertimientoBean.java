package co.gov.ideam.sirh.usuarios.agua.view;

import co.gov.ideam.sirh.auditoria.model.Auditoria;
import co.gov.ideam.sirh.auditoria.view.AuditoriasDelegate;
import co.gov.ideam.sirh.fuentes.model.CriteriosBusquedaTO;
import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.fuentes.model.FnttTramo;
import co.gov.ideam.sirh.fuentes.view.FuenteDelegate;
import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.model.PartZonificListas;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;

import co.gov.ideam.sirh.usuarios.agua.model.Captacion;
import co.gov.ideam.sirh.usuarios.agua.model.Concesion;
import co.gov.ideam.sirh.usuarios.agua.model.PermisoVertimiento;

import co.gov.ideam.sirh.usuarios.agua.model.Predio;
import co.gov.ideam.sirh.usuarios.agua.model.PuntoVertimiento;
import co.gov.ideam.sirh.usuarios.agua.model.PuntoVertimientoTratamiento;

import co.gov.ideam.sirh.util.ConstantesParametros;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;

import co.gov.ideam.sirh.view.StandarBean;

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
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.output.RichImage;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.component.rich.input.RichSelectManyListbox;
import oracle.adf.view.rich.component.rich.output.RichActiveOutputText;
import oracle.adf.view.rich.component.rich.output.RichOutputLabel;

public class AdicionarVertimientoBean extends StandarBean{
    //padre del punto de vertimiento
    private PermisoVertimiento permiso;
    //CDONCEL VERTIMIENTO NO VALIDO
    //padre del punto de vertimiento
    private PermisoVertimiento permisoNv;
    // FINN CDONCEL
    //Punto de vertimiento a crear.
    private PuntoVertimiento nuevoVertimiento;
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

    private RichForm form1;
    private RichDocument document1;
    private RichPanelStretchLayout panelStretchLayout1;
    private RichPanelGroupLayout panelGroupLayout1;
    private RichPanelBox panelBox1;
    private RichPanelBox panelBox2;
    private RichPanelBox panelBox3;
    private RichPanelBox panelBox4;
    private RichPanelStretchLayout panelStretchLayout2;
    private RichPanelGroupLayout panelGroupLayout2;
    private RichPanelGroupLayout panelGroupLayout3;
    private RichCommandButton cbNextDatosBasicos;
    private RichSpacer spacer1;
    private RichSpacer spacer8;
    private RichPanelGroupLayout panelGroupLayout4;
    private RichPanelFormLayout panelFormLayout1;
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
    private RichPanelStretchLayout panelStretchLayout3;
    private RichPanelGroupLayout panelGroupLayout5;
    private RichPanelGroupLayout panelGroupLayout6;
    private RichPanelGroupLayout panelGroupLayout7;
    private RichPanelFormLayout panelFormLayout2;
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
    private RichPanelBox panelBox5;
    private RichPanelStretchLayout panelStretchLayout4;
    private RichPanelGroupLayout panelGroupLayout8;
    private RichPanelGroupLayout panelGroupLayout9;
    private RichPanelGroupLayout panelGroupLayout10;
    private RichPanelFormLayout panelFormLayout3;
    private RichCommandButton cbPrevGeoreferencia;
    private RichSpacer spacer11;
    private RichCommandButton cbNextGeoreferencia;
    private RichSpacer spacer12;
    private RichPanelGroupLayout panelGroupLayout11;
    private RichPanelGroupLayout panelGroupLayout13;
    private RichPanelFormLayout panelFormLayout4;
    private RichSelectOneChoice socSistemaReferencia;
    private UISelectItems siSistemaReferencia;
    private RichSpacer spacer13;
    private RichOutputText outputText2;
    private RichInputText itGradosLat;
    private RichInputText itMinutosLat;
    private RichInputText itSegundosLat;
    private RichInputText itAltitud;
    private RichInputText itDescripcionAcceso;
    private RichSpacer spacer10;
    private RichSpacer spacer15;
    private RichSpacer spacer16;
    private RichPanelStretchLayout panelStretchLayout5;
    private RichPanelStretchLayout panelStretchLayout6;
    private RichPanelGroupLayout panelGroupLayout14;
    private RichPanelGroupLayout panelGroupLayout15;
    private RichPanelGroupLayout panelGroupLayout16;
    private RichPanelGroupLayout panelGroupLayout17;
    private RichCommandButton cbPrevCaracteristicas;
    private RichSpacer spacer2;
    private RichCommandButton cbNextCaracteristicas;
    private RichSpacer spacer3;
    private RichCommandButton cbPrevTratamiento;
    private RichSpacer spacer4;
    private RichCommandButton cbNextTratamiento;
    private RichSpacer spacer7;
    private RichPanelGroupLayout panelGroupLayout18;
    private RichPanelFormLayout panelFormLayout5;
    private RichSelectOneChoice socTipoFlujo;
    private UISelectItems siTipoFlujo;
    private RichInputText itTiempoDescarga;
    private RichInputText itCaudalDisegno;
    private RichSelectOneChoice socFrecuencia;
    private UISelectItems siFrecuencia;
    private RichPanelGroupLayout panelGroupLayout19;
    private RichPanelFormLayout panelFormLayout6;
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
    private RichPopup p_registro_exitoso;
    private RichDialog d_registro_exitoso;
    private RichCommandButton cbAceptar;
    private RichPanelGroupLayout pgl14;
    private RichPanelStretchLayout psl7;
    private RichOutputText ot8;
    private RichImage i1;
    private RichPanelGroupLayout panelGroupLayout20;
    private RichPanelFormLayout   panelFormLayout7;
    
    private RichSpacer spacer9;
    private RichOutputLabel outputLabel1;
    private RichOutputLabel outputLabel2;
    private RichPanelFormLayout panelFormLayout8;
    private RichInputText it_grad_long; 
    private RichInputText it_min_long; 
    private RichInputText it_seg_long;
    private RichSpacer spacer14;
    private RichActiveOutputText activeOutputText1;
    private RichSpacer spacer17;
    private RichSpacer spacer18;
    private RichActiveOutputText activeOutputText2;
    private RichSpacer spacer19;
    private RichSpacer spacer20;
    private RichActiveOutputText activeOutputText3;
    private RichSpacer spacer21;
    private RichSpacer spacer22;
    private RichActiveOutputText activeOutputText4;
    private RichSpacer spacer23;
    private RichActiveOutputText activeOutputText5;
    private RichSpacer spacer24;
    private RichSpacer spacer25;
    private RichActiveOutputText activeOutputText6;
    private RichSpacer spacer26;
    private RichPanelFormLayout panelFormLayout9;
    private RichSelectOneChoice socTipoFuente;
    private UISelectItems siTipoFuente;

    public AdicionarVertimientoBean(){
        FacesUtils.setActiveBean("adicionarVertimientoBean",
                                 "Adicionar Vertimiento",
                                 UsuariosAguaDelegate.class);

        this.load();
    }
    public void load(){
        try{
          //CDONCEL
      Object paginaOrigen = FacesUtils.getFromSession("paginaOrigen");
          if(paginaOrigen!=null&&paginaOrigen.toString().equals("detallePredio")){
              System.out.println("ESTE VERTIMIENTO SE CREARÁ CON CNCESION NO VALIDA");
          }else{//FIN CDONCEL
          
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
            Object obj = FacesUtils.getFromSession("permisoSeleccionado");//el permiso que contiene los vertimientos..

            if(obj instanceof PermisoVertimiento){
                this.permiso = (PermisoVertimiento)obj;
            }else{
                Long codigo = (Long)FacesUtils.getFromSession("permisoSeleccionado");
                this.permiso = uad.getPermisoVertimiento(codigo);
            }
          }
            this.listaDepartamentos = this.cargarDivipola(null);
            this.listaArea = this.cargarZonificacion(null);
            this.listaTipoCentroPoblado = this.cargarParametro(ConstantesParametros.
                                                   CATEGORIA_TIPO_CENTRO_POBLADO);
            this.listaSistemasReferencia = this.cargarParametro(ConstantesParametros.
                                                           CATEGORIA_SISTEMA_REFERENCIA);
            listaTipoVertimiento = this.cargarParametro(ConstantesParametros.
                                                   CATEGORIA_TIPO_VERTIMIENTO);
            listaTipoFlujo = this.cargarParametro(ConstantesParametros.
                                                   CATEGORIA_TIPO_FLUJO_VERTIMIENTO);
            listaPretratamiento = this.cargarParametro(ConstantesParametros.
                                                   CATEGORIA_PRETRATAMIENTO);
            listaPrimario = this.cargarParametro(ConstantesParametros.
                                                   CATEGORIA_TRATAMIENTO_PRIMARIO);
            listaSecundario = this.cargarParametro(ConstantesParametros.
                                                   CATEGORIA_TRATAMIENTO_SECUNDARIO);
            listaTerciario = this.cargarParametro(ConstantesParametros.
                                                   CATEGORIA_TRATAMIENTO_TERCIARIO);
            listaOtro = this.cargarParametro(ConstantesParametros.
                                                   CATEGORIA_TRATAMIENTO_OTRO);
            this.listaTiposFuente = this.cargarParametro(ConstantesParametros.
                                                   CATEGORIA_TIPO_FUENTE);
            listaDias = this.cargarDiasMes();

            this.listaMunicipios = new ArrayList();
            this.listaZona = new ArrayList();
            this.listaSubzona = new ArrayList();
            this.listaFuentes =  new ArrayList();
            this.listaTramos =  new ArrayList();

        }catch(IdeamException e){
            showMessage(e);
        }
    }

////////////////////

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

    public void cbNextDatosBasicos_actionListener(ActionEvent actionEvent) {
        String outcome = "";
        boolean continuar = true;

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
        if(this.socTramo.getValue()==null){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.socTramo);
            continuar = false;
        }
        if(this.socTipoVertimiento.getValue()==null){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.socTipoVertimiento);
            continuar = false;
        }

        if(continuar){
            this.panelBox1.setVisible(false);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelBox1);
            this.panelBox2.setVisible(true);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelBox2);
        }
    }

    //Paso 2

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

        if(continuar){
            this.panelBox2.setVisible(false);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelBox2);
            this.panelBox3.setVisible(true);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelBox3);
        }
    }

    public void cbPrevUbicacion_actionListener(ActionEvent actionEvent) {
        boolean continuar = true;

        if(continuar){
            this.panelBox2.setVisible(false);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelBox2);
            this.panelBox1.setVisible(true);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelBox1);

        }
    }

    //paso 3

    public void cbNextCaracteristicas_actionListener(ActionEvent actionEvent) {
        boolean continuar = true;

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

        if(continuar){
            this.panelBox3.setVisible(false);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelBox3);
            this.panelBox4.setVisible(true);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelBox4);
        }
    }

    public void cbPrevCaracteristicas_actionListener(ActionEvent actionEvent) {
        boolean continuar = true;

        if(continuar){
            this.panelBox3.setVisible(false);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelBox3);
            this.panelBox2.setVisible(true);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelBox2);

        }
    }

    //paso 4

    public void cbNextTratamiento_actionListener(ActionEvent actionEvent) {
        boolean continuar = true;

        if(continuar){
            this.panelBox4.setVisible(false);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelBox4);
            this.panelBox5.setVisible(true);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelBox5);
        }
    }

    public void cbPrevTratamiento_actionListener(ActionEvent actionEvent) {
        boolean continuar = true;

        if(continuar){
            this.panelBox4.setVisible(false);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelBox4);
            this.panelBox3.setVisible(true);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelBox3);
        }
    }


    //paso final

    public void cbPrevGeoreferencia_actionListener(ActionEvent actionEvent) {
        boolean continuar = true;

        if(continuar){
            this.panelBox5.setVisible(false);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelBox5);
            this.panelBox4.setVisible(true);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelBox4);

        }
    }

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


        if(this.it_grad_long.getValue()==null ||
           this.it_grad_long.getValue().toString().length()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.it_grad_long);
            continuar = false;
        }
        
        if(this.it_min_long.getValue()==null ||
           this.it_min_long.getValue().toString().length()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.it_min_long);
            continuar = false;
        }
        
        if(this.it_seg_long.getValue()==null ||
           this.it_seg_long.getValue().toString().length()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.it_seg_long);
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


    public void save(){
        try{
            this.nuevoVertimiento = new PuntoVertimiento();
          UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
            UsuarioConectadoTO usuarioConectado =
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
      
            this.nuevoVertimiento.setCodigoAutoridad(usuarioConectado.getUsuario().
                                   getAutoridadAmbiental().getId().longValue());
          //CDONCEL  
          Object paginaOrigen = FacesUtils.getFromSession("paginaOrigen");
          if(paginaOrigen!=null&&paginaOrigen.toString().equals("detallePredio")){
              Object objPredio = FacesUtils.getFromSession("predioConVertimientoSinConc");
              
              if(objPredio!=null&&objPredio instanceof Predio){
                  Predio predio=(Predio)objPredio;
                  System.out.println("ESTE VERTIMIENTO SE CREARÁ CON PERMISO NO VALIDO al predio:"+predio.getCodigo());
                  permisoNv= new PermisoVertimiento();
                  permisoNv.setNumeroExpediente("Exp_"+predio.getCodigo());
                  //permisoNv.s
                  //concesionnoValido.setResolucionCaudal("Res_NoValido_"+predio.getCodigo()+"_"+listaCapSinConc.size());
                  permisoNv.setNo_valida(1);
                  
                  permisoNv.setCodigoPredio(predio.getCodigo());
                   permisoNv.setCodigoAutoridadAmbiental(usuarioConectado.getUsuario().
                                 getAutoridadAmbiental().getId().longValue());      
                  permisoNv = uad.registrarPermiso(this.permisoNv);
                  
                  this.nuevoVertimiento.setIdPermisoVertimiento(this.permisoNv);
              }
          }else
              this.nuevoVertimiento.setIdPermisoVertimiento(this.permiso);
          
            this.nuevoVertimiento.setIdFuente((FnttFuente)this.socFuente.getValue());
            this.nuevoVertimiento.setIdTramo((FnttTramo)this.socTramo.getValue());
            this.nuevoVertimiento.setTipoVertimiento(((Lista)this.socTipoVertimiento.getValue()).getCodigo());

            this.nuevoVertimiento.setIdDepartamento(((Divipola)this.socDepartamento.getValue()).getId().intValue());
            this.nuevoVertimiento.setIdMunicipio(((Divipola)this.socMunicipio.getValue()).getId().intValue());
            this.nuevoVertimiento.setIdTipoCentroPoblado(((Lista)this.socTipoCP.getValue()).getCodigo());
            this.nuevoVertimiento.setNombreCentroPoblado((this.itNombreCentroPoblado.getValue()!=null)?this.itNombreCentroPoblado.getValue().toString():null);

            this.nuevoVertimiento.setTipoFlujo(((Lista)this.socTipoFlujo.getValue()).getCodigo());
            this.nuevoVertimiento.setFrecuencia(Integer.parseInt(this.socFrecuencia.getValue().toString()));
            this.nuevoVertimiento.setTiempoDescarga(Double.parseDouble(this.itTiempoDescarga.getValue().toString()));
            this.nuevoVertimiento.setCaudalDisegno(Double.parseDouble(this.itCaudalDisegno.getValue().toString()));

            this.nuevoVertimiento.setGradosLat(Integer.parseInt(this.itGradosLat.getValue().toString()));
            this.nuevoVertimiento.setMinutosLat(Integer.parseInt(this.itMinutosLat.getValue().toString()));
            this.nuevoVertimiento.setSegundosLat(Double.parseDouble(this.itSegundosLat.getValue().toString()));
            
            this.nuevoVertimiento.setGradosLong(Integer.parseInt(this.it_grad_long.getValue().toString()));
            this.nuevoVertimiento.setMinutosLong(Integer.parseInt(this.it_min_long.getValue().toString()));
            this.nuevoVertimiento.setSegundosLong(Double.parseDouble(this.it_seg_long.getValue().toString()));
            
            
            this.nuevoVertimiento.setAltitud(Double.parseDouble(this.itAltitud.getValue().toString()));
            this.nuevoVertimiento.setSistemaReferencia(((Lista)this.socSistemaReferencia.getValue()).getCodigo());
            this.nuevoVertimiento.setDescripcionAcceso(this.itDescripcionAcceso.getValue().toString());

            

            //relacionar los sistemas de tratamiento
            List<PuntoVertimientoTratamiento> listTratamientos = new ArrayList<PuntoVertimientoTratamiento>();
            if(this.smlbPretratamiento.getValue()!=null){
                Iterator it= ((List)this.smlbPretratamiento.getValue()).iterator();
                while(it.hasNext()){
                    Lista lis = (Lista)it.next();
                    PuntoVertimientoTratamiento nuevo = new PuntoVertimientoTratamiento();
                    nuevo.setIdPuntoVertimiento(this.nuevoVertimiento.getId());
                    nuevo.setIdTratamiento(lis.getCodigo());
                    nuevo.setIdCategoria(lis.getCategoria().getCodigo().intValue());
                    nuevo.setCodigoAutoridad(this.nuevoVertimiento.getCodigoAutoridad());
                    listTratamientos.add(nuevo);
                }
            }
            if(this.smlbPrimario.getValue()!=null){
                Iterator it= ((List)this.smlbPrimario.getValue()).iterator();
                while(it.hasNext()){
                    Lista lis = (Lista)it.next();
                    PuntoVertimientoTratamiento nuevo = new PuntoVertimientoTratamiento();
                    nuevo.setIdPuntoVertimiento(this.nuevoVertimiento.getId());
                    nuevo.setIdTratamiento(lis.getCodigo());
                    nuevo.setIdCategoria(lis.getCategoria().getCodigo().intValue());
                    nuevo.setCodigoAutoridad(this.nuevoVertimiento.getCodigoAutoridad());
                    listTratamientos.add(nuevo);
                }
            }
            if(this.smlbSecundario.getValue()!=null){
                Iterator it= ((List)this.smlbSecundario.getValue()).iterator();
                while(it.hasNext()){
                    Lista lis = (Lista)it.next();
                    PuntoVertimientoTratamiento nuevo = new PuntoVertimientoTratamiento();
                    nuevo.setIdPuntoVertimiento(this.nuevoVertimiento.getId());
                    nuevo.setIdTratamiento(lis.getCodigo());
                    nuevo.setIdCategoria(lis.getCategoria().getCodigo().intValue());
                    nuevo.setCodigoAutoridad(this.nuevoVertimiento.getCodigoAutoridad());
                    listTratamientos.add(nuevo);
                }
            }
            if(this.smlbTerciario.getValue()!=null){
                Iterator it= ((List)this.smlbTerciario.getValue()).iterator();
                while(it.hasNext()){
                    Lista lis = (Lista)it.next();
                    PuntoVertimientoTratamiento nuevo = new PuntoVertimientoTratamiento();
                    nuevo.setIdPuntoVertimiento(this.nuevoVertimiento.getId());
                    nuevo.setIdTratamiento(lis.getCodigo());
                    nuevo.setIdCategoria(lis.getCategoria().getCodigo().intValue());
                    nuevo.setCodigoAutoridad(this.nuevoVertimiento.getCodigoAutoridad());
                    listTratamientos.add(nuevo);
                }
            }
            if(this.smlbOtro.getValue()!=null){
                Iterator it= ((List)this.smlbOtro.getValue()).iterator();
                while(it.hasNext()){
                    Lista lis = (Lista)it.next();
                    PuntoVertimientoTratamiento nuevo = new PuntoVertimientoTratamiento();
                    nuevo.setIdPuntoVertimiento(this.nuevoVertimiento.getId());
                    nuevo.setIdTratamiento(lis.getCodigo());
                    nuevo.setIdCategoria(lis.getCategoria().getCodigo().intValue());
                    nuevo.setCodigoAutoridad(this.nuevoVertimiento.getCodigoAutoridad());
                    listTratamientos.add(nuevo);
                }
            }

            if(!listTratamientos.isEmpty()){
                this.nuevoVertimiento.setListTratamientos(listTratamientos);
                /*for(PuntoVertimientoTratamiento tratamiento : listTratamientos){
                    uad.createPuntoTratamiento(tratamiento);
                }*/
            }
            
            
           
            this.nuevoVertimiento = uad.createVertimiento(this.nuevoVertimiento);


            try{
                
                /** 
                 * Estas variables son necesarias para llenar los datos intrinsecos de la auditoria.
                 */
                
                Auditoria auditoria = new Auditoria();
                auditoria.setIdUsuario(usuarioConectado.getUsuario().getCodigo());
                auditoria.setOperacion("GUARDAR");
                auditoria.setObjeto("VERTIMIENTOS");
                auditoria.setMetodo(Thread.currentThread().getStackTrace()[1].getMethodName());
                auditoria.setClase(this.getClass().getName());
                //Objeto Afectado (usuario modificado, eliminado o agregado) 
                auditoria.setIdObjeto(new Long(this.nuevoVertimiento.getId()));
               
                AuditoriasDelegate audDelegate = AuditoriasDelegate.getInstance();
                audDelegate.setAuditoria(auditoria);
                
            }catch(Exception e){
                System.out.println("No se pudo Almacenar la auditoria: ---------------------------------- ");
                System.out.println(e.getMessage()+"------------------------------------------------------ ");
            }


            showPopup(this.p_registro_exitoso, true);

        }catch(IdeamException e){
            showMessage(e);
        }

    }

//////////////////

    public void setPermiso(PermisoVertimiento permiso) {
        this.permiso = permiso;
    }

    public PermisoVertimiento getPermiso() {
        return permiso;
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

    public void setCbNextDatosBasicos(RichCommandButton commandButton1) {
        this.cbNextDatosBasicos = commandButton1;
    }

    public RichCommandButton getCbNextDatosBasicos() {
        return cbNextDatosBasicos;
    }

    public void setSpacer1(RichSpacer spacer1) {
        this.spacer1 = spacer1;
    }

    public RichSpacer getSpacer1() {
        return spacer1;
    }

    public void setPanelGroupLayout4(RichPanelGroupLayout panelGroupLayout4) {
        this.panelGroupLayout4 = panelGroupLayout4;
    }

    public RichPanelGroupLayout getPanelGroupLayout4() {
        return panelGroupLayout4;
    }

    public void setPanelFormLayout1(RichPanelFormLayout panelFormLayout1) {
        this.panelFormLayout1 = panelFormLayout1;
    }

    public RichPanelFormLayout getPanelFormLayout1() {
        return panelFormLayout1;
    }

    public void setSocFuente(RichSelectOneChoice selectOneChoice1) {
        this.socFuente = selectOneChoice1;
    }

    public RichSelectOneChoice getSocFuente() {
        return socFuente;
    }

    public void setSocTramo(RichSelectOneChoice selectOneChoice2) {
        this.socTramo = selectOneChoice2;
    }

    public RichSelectOneChoice getSocTramo() {
        return socTramo;
    }

    public void setSocTipoVertimiento(RichSelectOneChoice selectOneChoice3) {
        this.socTipoVertimiento = selectOneChoice3;
    }

    public RichSelectOneChoice getSocTipoVertimiento() {
        return socTipoVertimiento;
    }

    public void setSiTipoVertimiento(UISelectItems selectItems3) {
        this.siTipoVertimiento = selectItems3;
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

    public void setSiFuentes(UISelectItems siFuentes) {
        this.siFuentes = siFuentes;
    }

    public UISelectItems getSiFuentes() {
        return siFuentes;
    }

    public void setSiTramos(UISelectItems siTramos) {
        this.siTramos = siTramos;
    }

    public UISelectItems getSiTramos() {
        return siTramos;
    }

    public void setPanelStretchLayout3(RichPanelStretchLayout panelStretchLayout3) {
        this.panelStretchLayout3 = panelStretchLayout3;
    }

    public RichPanelStretchLayout getPanelStretchLayout3() {
        return panelStretchLayout3;
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

    public void setPanelGroupLayout7(RichPanelGroupLayout panelGroupLayout7) {
        this.panelGroupLayout7 = panelGroupLayout7;
    }

    public RichPanelGroupLayout getPanelGroupLayout7() {
        return panelGroupLayout7;
    }

    public void setPanelFormLayout2(RichPanelFormLayout panelFormLayout2) {
        this.panelFormLayout2 = panelFormLayout2;
    }

    public RichPanelFormLayout getPanelFormLayout2() {
        return panelFormLayout2;
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

    public void setPanelBox5(RichPanelBox panelBox5) {
        this.panelBox5 = panelBox5;
    }

    public RichPanelBox getPanelBox5() {
        return panelBox5;
    }

    public void setPanelStretchLayout4(RichPanelStretchLayout panelStretchLayout4) {
        this.panelStretchLayout4 = panelStretchLayout4;
    }

    public RichPanelStretchLayout getPanelStretchLayout4() {
        return panelStretchLayout4;
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

    public void setPanelGroupLayout11(RichPanelGroupLayout panelGroupLayout11) {
        this.panelGroupLayout11 = panelGroupLayout11;
    }

    public RichPanelGroupLayout getPanelGroupLayout11() {
        return panelGroupLayout11;
    }


    public void setPanelGroupLayout13(RichPanelGroupLayout panelGroupLayout13) {
        this.panelGroupLayout13 = panelGroupLayout13;
    }

    public RichPanelGroupLayout getPanelGroupLayout13() {
        return panelGroupLayout13;
    }

    public void setPanelFormLayout4(RichPanelFormLayout panelFormLayout4) {
        this.panelFormLayout4 = panelFormLayout4;
    }

    public RichPanelFormLayout getPanelFormLayout4() {
        return panelFormLayout4;
    }

    public void setCbPrevGeoreferencia(RichCommandButton cbPrevGeoreferencia) {
        this.cbPrevGeoreferencia = cbPrevGeoreferencia;
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

    public void setCbNextGeoreferencia(RichCommandButton cbNextGeoreferencia) {
        this.cbNextGeoreferencia = cbNextGeoreferencia;
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

    public void setOutputText2(RichOutputText outputText2) {
        this.outputText2 = outputText2;
    }

    public RichOutputText getOutputText2() {
        return outputText2;
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

    public void setPanelGroupLayout17(RichPanelGroupLayout panelGroupLayout17) {
        this.panelGroupLayout17 = panelGroupLayout17;
    }

    public RichPanelGroupLayout getPanelGroupLayout17() {
        return panelGroupLayout17;
    }

    public void setCbPrevCaracteristicas(RichCommandButton commandButton1) {
        this.cbPrevCaracteristicas = commandButton1;
    }

    public RichCommandButton getCbPrevCaracteristicas() {
        return cbPrevCaracteristicas;
    }

    public void setSpacer2(RichSpacer spacer2) {
        this.spacer2 = spacer2;
    }

    public RichSpacer getSpacer2() {
        return spacer2;
    }

    public void setCbNextCaracteristicas(RichCommandButton commandButton2) {
        this.cbNextCaracteristicas = commandButton2;
    }

    public RichCommandButton getCbNextCaracteristicas() {
        return cbNextCaracteristicas;
    }

    public void setSpacer3(RichSpacer spacer3) {
        this.spacer3 = spacer3;
    }

    public RichSpacer getSpacer3() {
        return spacer3;
    }

    public void setCbPrevTratamiento(RichCommandButton commandButton3) {
        this.cbPrevTratamiento = commandButton3;
    }

    public RichCommandButton getCbPrevTratamiento() {
        return cbPrevTratamiento;
    }

    public void setSpacer4(RichSpacer spacer4) {
        this.spacer4 = spacer4;
    }

    public RichSpacer getSpacer4() {
        return spacer4;
    }

    public void setCbNextTratamiento(RichCommandButton commandButton4) {
        this.cbNextTratamiento = commandButton4;
    }

    public RichCommandButton getCbNextTratamiento() {
        return cbNextTratamiento;
    }

    public void setSpacer7(RichSpacer spacer7) {
        this.spacer7 = spacer7;
    }

    public RichSpacer getSpacer7() {
        return spacer7;
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

    public void setSocTipoFlujo(RichSelectOneChoice selectOneChoice1) {
        this.socTipoFlujo = selectOneChoice1;
    }

    public RichSelectOneChoice getSocTipoFlujo() {
        return socTipoFlujo;
    }

    public void setSiTipoFlujo(UISelectItems selectItems1) {
        this.siTipoFlujo = selectItems1;
    }

    public UISelectItems getSiTipoFlujo() {
        return siTipoFlujo;
    }


    public void setItTiempoDescarga(RichInputText inputText2) {
        this.itTiempoDescarga = inputText2;
    }

    public RichInputText getItTiempoDescarga() {
        return itTiempoDescarga;
    }

    public void setItCaudalDisegno(RichInputText inputText3) {
        this.itCaudalDisegno = inputText3;
    }

    public RichInputText getItCaudalDisegno() {
        return itCaudalDisegno;
    }

    public void setSocFrecuencia(RichSelectOneChoice selectOneChoice1) {
        this.socFrecuencia = selectOneChoice1;
    }

    public RichSelectOneChoice getSocFrecuencia() {
        return socFrecuencia;
    }

    public void setSiFrecuencia(UISelectItems selectItems1) {
        this.siFrecuencia = selectItems1;
    }

    public UISelectItems getSiFrecuencia() {
        return siFrecuencia;
    }

    public void setPanelGroupLayout19(RichPanelGroupLayout panelGroupLayout19) {
        this.panelGroupLayout19 = panelGroupLayout19;
    }

    public RichPanelGroupLayout getPanelGroupLayout19() {
        return panelGroupLayout19;
    }

    public void setPanelFormLayout6(RichPanelFormLayout panelFormLayout6) {
        this.panelFormLayout6 = panelFormLayout6;
    }

    public RichPanelFormLayout getPanelFormLayout6() {
        return panelFormLayout6;
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

    public void setSmlbPrimario(RichSelectManyListbox selectOneChoice1) {
        this.smlbPrimario = selectOneChoice1;
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

    public void setSmlbSecundario(RichSelectManyListbox selectOneChoice1) {
        this.smlbSecundario = selectOneChoice1;
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

    public void setSmlbTerciario(RichSelectManyListbox selectOneChoice1) {
        this.smlbTerciario = selectOneChoice1;
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

    public void setSmlbOtro(RichSelectManyListbox selectOneChoice1) {
        this.smlbOtro = selectOneChoice1;
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

    public void setNuevoVertimiento(PuntoVertimiento nuevoVertimiento) {
        this.nuevoVertimiento = nuevoVertimiento;
    }

    public PuntoVertimiento getNuevoVertimiento() {
        return nuevoVertimiento;
    }

    public void setSpacer8(RichSpacer spacer8) {
        this.spacer8 = spacer8;
    }

    public RichSpacer getSpacer8() {
        return spacer8;
    }

    public void setOutputLabel1(RichOutputLabel outputLabel1) {
        this.outputLabel1 = outputLabel1;
    }

    public RichOutputLabel getOutputLabel1() {
        return outputLabel1;
    }

    public void setItAltitud(RichInputText itAltitud) {
        this.itAltitud = itAltitud;
    }

    public RichInputText getItAltitud() {
        return itAltitud;
    }

    public void setPanelGroupLayout20(RichPanelGroupLayout panelGroupLayout20) {
        this.panelGroupLayout20 = panelGroupLayout20;
    }

    public RichPanelGroupLayout getPanelGroupLayout20() {
        return panelGroupLayout20;
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

    public void setOutputLabel2(RichOutputLabel outputLabel2) {
        this.outputLabel2 = outputLabel2;
    }

    public RichOutputLabel getOutputLabel2() {
        return outputLabel2;
    }

    public void setPanelFormLayout8(RichPanelFormLayout panelFormLayout8) {
        this.panelFormLayout8 = panelFormLayout8;
    }

    public RichPanelFormLayout getPanelFormLayout8() {
        return panelFormLayout8;
    }

    public void setIt_grad_long(RichInputText it_grad_long) {
        this.it_grad_long = it_grad_long;
    }

    public RichInputText getIt_grad_long() {
        return it_grad_long;
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

    public void setSpacer10(RichSpacer spacer10) {
        this.spacer10 = spacer10;
    }

    public RichSpacer getSpacer10() {
        return spacer10;
    }

    public void setSpacer14(RichSpacer spacer14) {
        this.spacer14 = spacer14;
    }

    public RichSpacer getSpacer14() {
        return spacer14;
    }

    public void setActiveOutputText1(RichActiveOutputText activeOutputText1) {
        this.activeOutputText1 = activeOutputText1;
    }

    public RichActiveOutputText getActiveOutputText1() {
        return activeOutputText1;
    }

    public void setSpacer17(RichSpacer spacer17) {
        this.spacer17 = spacer17;
    }

    public RichSpacer getSpacer17() {
        return spacer17;
    }

    public void setSpacer18(RichSpacer spacer18) {
        this.spacer18 = spacer18;
    }

    public RichSpacer getSpacer18() {
        return spacer18;
    }

    public void setActiveOutputText2(RichActiveOutputText activeOutputText2) {
        this.activeOutputText2 = activeOutputText2;
    }

    public RichActiveOutputText getActiveOutputText2() {
        return activeOutputText2;
    }

    public void setSpacer19(RichSpacer spacer19) {
        this.spacer19 = spacer19;
    }

    public RichSpacer getSpacer19() {
        return spacer19;
    }

    public void setSpacer20(RichSpacer spacer20) {
        this.spacer20 = spacer20;
    }

    public RichSpacer getSpacer20() {
        return spacer20;
    }

    public void setActiveOutputText3(RichActiveOutputText activeOutputText3) {
        this.activeOutputText3 = activeOutputText3;
    }

    public RichActiveOutputText getActiveOutputText3() {
        return activeOutputText3;
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

    public void setActiveOutputText4(RichActiveOutputText activeOutputText4) {
        this.activeOutputText4 = activeOutputText4;
    }

    public RichActiveOutputText getActiveOutputText4() {
        return activeOutputText4;
    }

    public void setSpacer23(RichSpacer spacer23) {
        this.spacer23 = spacer23;
    }

    public RichSpacer getSpacer23() {
        return spacer23;
    }

    public void setActiveOutputText5(RichActiveOutputText activeOutputText5) {
        this.activeOutputText5 = activeOutputText5;
    }

    public RichActiveOutputText getActiveOutputText5() {
        return activeOutputText5;
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

    public void setActiveOutputText6(RichActiveOutputText activeOutputText6) {
        this.activeOutputText6 = activeOutputText6;
    }

    public RichActiveOutputText getActiveOutputText6() {
        return activeOutputText6;
    }

    public void setSpacer26(RichSpacer spacer26) {
        this.spacer26 = spacer26;
    }

    public RichSpacer getSpacer26() {
        return spacer26;
    }

    public void setPanelFormLayout9(RichPanelFormLayout panelFormLayout9) {
        this.panelFormLayout9 = panelFormLayout9;
    }

    public RichPanelFormLayout getPanelFormLayout9() {
        return panelFormLayout9;
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

  public PermisoVertimiento getPermisoNv() {
    return permisoNv;
  }

  public void setPermisoNv(PermisoVertimiento permisoNv) {
    this.permisoNv = permisoNv;
  }
}
