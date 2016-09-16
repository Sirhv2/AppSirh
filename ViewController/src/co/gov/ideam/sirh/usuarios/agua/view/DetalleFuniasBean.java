package co.gov.ideam.sirh.usuarios.agua.view;

import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.view.ParametrosDelegate;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.usuarios.agua.model.Captacion;
import co.gov.ideam.sirh.usuarios.agua.model.Concesion;
import co.gov.ideam.sirh.usuarios.agua.model.Predio;
import co.gov.ideam.sirh.usuarios.agua.model.SubttFunias;
import co.gov.ideam.sirh.usuarios.agua.model.SubttFuniasDocumentos;
import co.gov.ideam.sirh.usuarios.agua.model.UsuarioAgua;
import co.gov.ideam.sirh.util.Constantes;
import co.gov.ideam.sirh.util.ConstantesCaptaciones;
import co.gov.ideam.sirh.util.ConstantesDemanda;
import co.gov.ideam.sirh.util.ConstantesParametros;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;
import co.gov.ideam.sirh.view.util.ArchivoCargadoTO;
import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import oracle.adf.view.rich.event.DialogEvent;
import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.input.RichInputFile;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.output.RichImage;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.component.rich.layout.RichDecorativeBox;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputFile;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanRadio;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import javax.faces.component.UISelectItems;


import oracle.adf.view.rich.component.rich.RichMenu;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.nav.RichCommandMenuItem;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;

import org.apache.myfaces.trinidad.model.UploadedFile;

import oracle.adf.view.rich.context.AdfFacesContext;

import org.apache.myfaces.trinidad.event.SelectionEvent;

public class DetalleFuniasBean extends StandarBean{
    //objeto padre.
    private Captacion captacion;
    //objeto seleccionado.
    private SubttFunias funias;
    //usuario en session
    private UsuarioAgua usuario;
    //predio en session
    private Predio predio;
    //concesion en session
    private Concesion concesion; 
    
    //listado de tipo de informacion del funias desde las categorias.
    private List listaTiposFunias;
    //Listado desde los parametros de categorias
    private List listaLocalizacionTopo;
    //listado desde los parametros de categorias
    private List listaGeoforma;
    //listado desde los parametros de categorias
    private List listaEstructura;
    //listado desde los parametros de categorias
    private List listaAcabado;
    //listado desde los parametros de categorias
    private List listaTipoRegistro;
    //listado desde las categorias
    private List listaMaterial;
    //listado desde las categorias
    private List listaMetodoConstruccion;
    //listado desde las categorias
    private List listaTratamiento;
    //listado desde las categorias
    private List listaFluidoConstruccion;
    
    
    //Nombre del tipo de funias seleccionado.
    private String tipoFunias;
    
    private boolean geologia;
    private boolean geofisica;
    private boolean construccion;
    private boolean diagnostico;
    private boolean explotacion;
    private boolean archivosRelacionados;    
    
    private UploadedFile uploadedFile;
    private ArchivoCargadoTO archivoCargado;
    private List<ArchivoCargadoTO> archivosCargados;
    
    private SubttFuniasDocumentos documentoSeleccionado;
    //la siguiente variable se creo para evitar inconsistencias, con la vista, 
    //en la lista de documentos en el objeto funias cuando se remueve un objeto 
    //de la lista.
    private List listaArchivosRelacionados;
    
    
    private RichForm form1;
    private RichDocument document1;
    private RichPanelGroupLayout panelGroupLayout1;

    private RichPanelStretchLayout panelStretchLayout1;
    private RichPanelSplitter panelSplitter1;
    private RichPanelBox panelBox1;

    private RichPanelGroupLayout panelGroupLayout4;
    private RichPanelFormLayout panelFormLayout1;
    private RichPanelBox panelBox2;

    private RichPanelGroupLayout panelGroupLayout11;
    private RichPanelFormLayout panelFormLayout6;
    private RichPanelBox panelBox3;

    private RichPanelGroupLayout panelGroupLayout12;
    private RichPanelFormLayout panelFormLayout7;

    private RichPanelBox panelBox5;

    private RichPanelGroupLayout panelGroupLayout14;
    private RichPanelFormLayout panelFormLayout9; 
    
    //
    private RichSelectOneChoice socLocTopografica;
    private UISelectItems siLocTopografica;
    private RichInputText itUnidadGeo;
    private RichInputText itLitologiaSuperficial;
    private RichSelectOneChoice socGeoforma;
    private UISelectItems siGeoforma;
    private RichSelectOneChoice socEstructura;
    private UISelectItems siEstructura;
    private RichInputText itNombreEstructura;
    private RichInputFile ifGeologia;
    private RichInputDate idFechaConstruccion;
    private RichPanelBox pnlFuentesContaminaDiagnostico;
    private RichPanelStretchLayout panelStretchLayout17;
    private RichPanelGroupLayout panelGroupLayout45;
    private RichPanelGroupLayout panelGroupLayout46;
    private RichCommandButton cmbFuentesContaminaExplotacion;
    private RichSpacer spacer15;
    private RichPanelGroupLayout panelGroupLayout47;
    private RichPanelFormLayout panelFormLayout15;
    private RichSelectOneChoice socTipoAcabado;
    private UISelectItems siTipoAcabado;
    private RichOutputText otDatosBasicosGeologia;
    private RichOutputText otCarGeomorfoGeologia;
    private RichOutputText otRegistroGeologia;
    private RichSpacer spacer16;
    private RichSpacer spacer17;
    private RichSpacer spacer18;
    private RichOutputText otDatosGeofisica;
    private RichSpacer spacer19;
    private RichInputDate idMedidaGeofisica;
    private RichInputText itEquipoGeofisica;
    private RichSelectOneChoice socTipoRegistroGeofisica;
    private UISelectItems siTipoRegistroGeofisica;
    private RichInputText itCompaniaEjeGeofisica;
    private RichInputText itResistLodoGeofisica;
    private RichInputText itTemperaturaLodo;
    private RichInputText itProfundidadRegGeofisica;
    private RichInputText itCalidadRegistroGeofisica;
    private RichOutputText otCapasGeofisica;
    private RichSpacer spacer20;
    private RichInputFile ifDocumentoGeofisica;
    private RichInputText itDiaIntConstruccion;
    private RichInputText itDiaExtConstruccion;
    private RichInputText itDiaPerfCostruccion;
    private RichSpacer spacer21;
    private RichOutputText outputText1;
    private RichSelectOneChoice socMaterialConstruccion;
    private UISelectItems siMaterialConstruccion;
    private RichSelectOneChoice socMetodoConstruccion;
    private UISelectItems siMetodoConstruccion;
    private RichSelectOneChoice socTratamientoConstruccion;
    private UISelectItems siTratamientoConstruccion;
    private RichSelectOneChoice socFluidoConstruccion;
    private UISelectItems siFluidoConstruccion;
    private RichInputText itCompaniaPerforadora;
    private RichSelectBooleanRadio sbrColmatadaSi;
    private RichSelectBooleanRadio sbrColmatadaNo;
    private RichSelectBooleanRadio sbrColapsadaSi;
    private RichSelectBooleanRadio sbrColapsadaNo;
    private RichSpacer spacer22;
    private RichOutputText outputText2;
    private RichInputText itProfEntubado;
    private RichInputText itProfPerforacion;
    private RichSpacer spacer23;
    private RichOutputText outputText3;
    private RichInputText itCantidadGravilla;
    private RichInputText itNivelGravilla;
    private RichInputText itDiametroPromedio;
    private RichSpacer spacer24;
    private RichOutputText outputText4;
    private RichInputText itCapacidadEmbalse;
    private RichSelectBooleanCheckbox sbcEmbalse;
    private RichSelectBooleanCheckbox sbcTanque;
    private RichInputText itCapacidadTanque;
    private RichSelectBooleanCheckbox sbcAlberca;
    private RichInputText itCapacidadAlberca;
    private RichSelectBooleanCheckbox sbcOtro;
    private RichInputText itCapacidadOtro;
    private RichSpacer spacer25;
    private RichOutputText outputText5;
    private RichInputFile ifDocumentoConstruccion;
    private RichSelectBooleanRadio sbrLetrinaSi;
    private RichSelectBooleanRadio sbrLetrinaNo;
    private RichInputText itDistanciaLetrina;
    private RichSelectBooleanRadio sbrCharcoSi;
    private RichSelectBooleanRadio sbrCharcoNo;
    private RichInputText itDistanciaCharco;
    private RichSelectBooleanRadio sbrCriaderoSi;
    private RichSelectBooleanRadio sbrCriaderoNo;
    private RichInputText itDistanciaCriadero;
    private RichSelectBooleanRadio sbrFiltracionSi;
    private RichSelectBooleanRadio sbrFiltracionNo;
    private RichInputText itDistanciaFiltracion;
    private RichSpacer spacer26;
    private RichOutputText outputText6;
    private RichSelectBooleanRadio sbrCubiertaSi;
    private RichSelectBooleanRadio sbrCubiertaNo;
    private RichSelectBooleanRadio sbrCementoSi;
    private RichSelectBooleanRadio sbrCementoNo;
    private RichSelectBooleanRadio sbrSelloSi;
    private RichSelectBooleanRadio sbrSelloNo;
    private RichSelectBooleanRadio sbrCercoSi;
    private RichSelectBooleanRadio sbrCercoNo;
    private RichSpacer spacer27;
    private RichOutputText outputText7;
    private RichSelectBooleanRadio sbrCampoInfiltracionSi;
    private RichSelectBooleanRadio sbrCampoInfiltracionNo;
    private RichInputText itDistanciaCampoInfiltracion;
    private RichSelectBooleanRadio sbrCementerioSi;
    private RichSelectBooleanRadio sbrCementerioNo;
    private RichInputText itDistanciaCementerio;
    private RichSelectBooleanRadio sbrEstacionServicioSi;
    private RichSelectBooleanRadio sbrEstacionServicioNo;
    private RichInputText itDistanciaEstacion;
    private RichSelectBooleanRadio sbrLagunasOxidacionSi;
    private RichSelectBooleanRadio sbrLagunaOxidacionNo;
    private RichInputText itDistanciaLaguna;
    private RichSelectBooleanRadio sbrLavaderosSi;
    private RichSelectBooleanRadio sbrLavaderosNo;
    private RichInputText itDistanciaLavaderos;
    private RichSelectBooleanRadio sbrSacrificioSi;
    private RichSelectBooleanRadio sbrSacrificioNo;
    private RichInputText itSacrificioDistancia;
    private RichSelectBooleanRadio sbrPozoSi;
    private RichSelectBooleanRadio sbrPozoNo;
    private RichInputText itPozoDistancia;
    private RichSpacer spacer28;
    private RichOutputText outputText8;
    private RichSelectBooleanRadio sbrResiduoAgricolaSi;
    private RichSelectBooleanRadio sbrResiduoAgricolaNo;
    private RichSelectBooleanRadio sbrResiduoDomesticoSi;
    private RichSelectBooleanRadio sbrResiduoDomesticoNo;
    private RichSelectBooleanRadio sbrResiduoGanaderiaSi;
    private RichSelectBooleanRadio sbrResiduoGanaderiaNo;
    private RichSelectBooleanRadio sbrResiduoHospitalarioSi;
    private RichSelectBooleanRadio sbrResiduoHospitalarioNo;
    private RichSelectBooleanRadio sbrResiduoIndustrialSi;
    private RichSelectBooleanRadio sbrResiduoIndustrialNo;
    private RichSelectBooleanRadio sbrResiduoMineroSi;
    private RichSelectBooleanRadio sbrResiduoMineroNo;
    private RichSpacer spacer29;
    private RichOutputText outputText9;
    private RichSelectBooleanRadio sbrBotaderoCieloAbiertoSi;
    private RichSelectBooleanRadio sbrBotaderoCieloAbiertoNo;
    private RichSelectBooleanRadio sbrCompostajeSi;
    private RichSelectBooleanRadio sbrCompostajeNo;
    private RichSelectBooleanRadio sbrIncineracionSi;
    private RichSelectBooleanRadio sbrIncineracionNo;
    private RichSelectBooleanRadio sbrReciclajeSi;
    private RichSelectBooleanRadio sbrReciclajeNo;
    
    //
    private RichSpacer s100;
    private RichSpacer s101;
    private RichSpacer s102;
    private RichSpacer s103;
    private RichSpacer s104;
    private RichSpacer s105;
    private RichPanelGroupLayout pgl_archivo1;
    private RichCommandLink cl_documento1;
    private RichCommandButton cb_borrar_docto1;
    private RichPanelGroupLayout pgl_archivo2;
    private RichCommandButton cb_borrar_docto2;
    private RichCommandLink cl_documento2;
    private RichPanelGroupLayout pgl_archivo3;
    private RichCommandLink cl_documento3;
    private RichCommandButton cb_borrar_docto3;
    private RichPopup p_borrar_docto;
    private RichDialog d_borrar_docto;
    private RichOutputText ot100;
    
    //
    private RichPopup popup_borrar;
    private RichPopup p_registro_exitoso;
    private RichDialog d_borrar;
    private RichDialog d_registro_exitoso;
    private RichPanelGroupLayout panelGroupLayout2;
    private RichPanelGroupLayout panelGroupLayout3;
    private RichCommandButton cb_si_borrar;
    private RichSpacer spacer1;
    private RichCommandButton cb_no_borrar;
    private RichOutputText ot_borrar_1;
    private RichSpacer spacer2;
    private RichOutputText ot_borrar_2;
    private RichCommandButton cbAceptarDi;
    private RichPanelStretchLayout panelStretchLayout3;
    private RichImage i1;
    private RichOutputText ot8;
    private RichDecorativeBox decorativeBox6;
    private RichPanelGroupLayout panelGroupLayout6;
    private RichPanelGroupLayout panelGroupLayout7;
    private RichCommandButton cbAceptar;
    private RichSpacer spacer43;
    private RichCommandButton cbBorrar;
    
    //miga pan
    private RichPanelGroupLayout panelGroupLayout8;
    private RichSpacer spacer50;
    private RichCommandLink commandLink1;
    private RichSpacer spacer51;
    private RichCommandLink commandLink2;
    private RichSpacer spacer52;
    private RichCommandLink commandLink3;
    private RichSpacer spacer53;
    private RichCommandLink commandLink4;
    private RichSpacer spacer54;
    private RichCommandLink commandLink5;
    private RichSpacer spacer55;
    private RichCommandLink commandLink6;
    private RichSpacer spacer56;
    private RichCommandLink commandLink7;
    private RichSpacer spacer57;
    private RichOutputText outputText26;
    private RichPanelGroupLayout panelGroupLayout5;
    private RichSpacer spacer5;
    private RichSpacer spacer6;
    private RichSpacer spacer7;
    private RichSpacer spacer9;
    private RichOutputText outputText10;
    private RichSpacer spacer10;
    private RichCommandLink cl_documento4;
    private RichSpacer spacer12;
    private RichCommandButton cb_borrar_docto4;


    private RichPanelCollection pcol1;
    private RichTable table1;
    private RichMenu menu1;
    private RichCommandMenuItem cmiVer;
    private RichCommandMenuItem cmiBorrar;
    private RichSpacer spacer31;
    private RichSpacer spacer32;

    public DetalleFuniasBean(){
        FacesUtils.setActiveBean("detalleFuniasBean",
                                 "Detalle de Funias", 
                                 UsuariosAguaDelegate.class);
        
        FacesUtils.removeManagedBeanFromSession("funiasCaptacionBean");
        FacesUtils.removeManagedBeanFromSession("UsosTreeHandler");
        this.load();
    }
    
    public void load(){
        try{
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
            ParametrosDelegate pd = ParametrosDelegate.getInstance(); 
            
            Object obj = FacesUtils.getFromSession("funiasSeleccionado");//la concesion desde la cual se inicia.
            if(obj instanceof SubttFunias){
                this.funias = (SubttFunias)obj;
            }else{                
                Long codigo = (Long)FacesUtils.getFromSession("funiasSeleccionado");                                            
                this.funias = uad.getFunias(codigo);    
                FacesUtils.setInSession("funiasSeleccionado", this.funias);
            }

            Object objCaptacion = FacesUtils.getFromSession("captacionSeleccionada");
            if(objCaptacion instanceof Captacion){
                this.captacion = (Captacion)objCaptacion;
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
            
            //funias de niveles redirecciona
            if(this.funias.getTipoFunias().longValue()==ConstantesParametros.LISTA_NIVEL){//es el funias de nivel
                FacesUtils.sendRedirect("adicionarFuniasNivel.jspx");
            } else if(this.funias.getTipoFunias().longValue()==ConstantesParametros.LISTA_EXPLOTACION){//es el funias de explotacion
                FacesUtils.sendRedirect("detalleFuniasExplotacion.jspx");
            } else if(this.funias.getTipoFunias().longValue()==ConstantesParametros.LISTA_ACCESO){//es el funias de acceso
                FacesUtils.sendRedirect("detalleFuniasAcceso.jspx");
            } else if(this.funias.getTipoFunias().longValue()==ConstantesParametros.LISTA_AFORO){//es el funias de aforo
                FacesUtils.sendRedirect("detalleFuniasAforo.jspx");
            } else if(this.funias.getTipoFunias().longValue()==ConstantesParametros.LISTA_MANANTIAL){//es el funias de manantial
                FacesUtils.sendRedirect("detalleFuniasManantial.jspx");
            }
            
            List documentosAsociados = uad.getFilesByFunias(this.funias.getId());
            if(documentosAsociados != null && !documentosAsociados.isEmpty()){
                this.funias.setSubttFuniasDocumentosList(documentosAsociados);
            }
            
            this.archivosRelacionados = 
                ((this.funias.getSubttFuniasDocumentosList()!=null)
                ? this.funias.getSubttFuniasDocumentosList().size()>0
                : false );
            
            this.listaTiposFunias = 
                this.cargarParametro(ConstantesParametros.
                                                   CATEGORIA_FUNIAS_SECCIONES);
            this.listaLocalizacionTopo = 
                this.cargarParametro(ConstantesParametros.
                                                   CATEGORIA_FUNIAS_LOCALIZACION_TOPOGRAFICA);
            
            this.listaGeoforma = this.cargarParametro(ConstantesParametros.
                                                   CATEGORIA_FUNIAS_GEOFORMA); 
            
            this.listaEstructura = this.cargarParametro(ConstantesParametros.
                                                   CATEGORIA_FUNIAS_ESTRUCTURA); 
            
            this.listaAcabado = 
                this.cargarParametro(ConstantesParametros.
                                                   CATEGORIA_FUNIAS_GEOFORMA);
            this.listaTipoRegistro = 
                this.cargarParametro(ConstantesParametros.
                                               CATEGORIA_TIPO_REGISTRO);
            
            this.listaMaterial = 
                this.cargarParametro(ConstantesParametros.
                                               CATEGORIA_MATERIAL_REVESTIM);
            
            this.listaMetodoConstruccion = 
                this.cargarParametro(ConstantesParametros.
                                               CATEGORIA_MET_CONSTRUCC);
            
            this.listaTratamiento = 
                this.cargarParametro(ConstantesParametros.
                                               CATEGORIA_FUNIAS_GEOFORMA);
            
            this.listaFluidoConstruccion = 
                this.cargarParametro(ConstantesParametros.
                                               CATEGORIA_FUNIAS_GEOFORMA);
            
            this.tipoFunias = pd.getLista(this.funias.getTipoFunias()).getValor();
            
            
            //listas seleccionadas en el registro
            if (this.funias.getEstructura()!=null){
                this.funias.setObjEstructura(pd.getLista(new Integer(this.funias.getEstructura())));
            }
            if(this.funias.getFluidoUsado()!=null){
                this.funias.setObjFluidoUsado(pd.getLista(new Integer(this.funias.getFluidoUsado())));
            }
            if(this.funias.getGeoforma()!=null){
                this.funias.setObjGeoforma(pd.getLista(new Integer(this.funias.getGeoforma())));
            }
            if(this.funias.getMaterialUsado()!=null){
                this.funias.setObjMaterialUsado(pd.getLista(new Integer(this.funias.getMaterialUsado())));
            }
            if(this.funias.getMetodoConstruccion()!=null){
                this.funias.setObjMetodoConstruccion(pd.getLista(new Integer(this.funias.getMetodoConstruccion())));
            }
            if(this.funias.getMetodoExplotacion()!=null){
                this.funias.setObjMetodoExplotacion(pd.getLista(new Integer(this.funias.getMetodoExplotacion())));
            }
            if(this.funias.getTipoAcabado()!=null){
                this.funias.setObjTipoAcabado(pd.getLista(new Integer(this.funias.getTipoAcabado())));
            }
            if(this.funias.getTipoEnergia()!=null){
                this.funias.setObjTipoEnergia(pd.getLista(new Integer(this.funias.getTipoEnergia())));
            }
            if(this.funias.getTipoRegistro()!=null){
                this.funias.setObjTipoRegistro(pd.getLista(new Integer(this.funias.getTipoRegistro())));
            }
            if(this.funias.getTratamientoEspecial()!=null){
                this.funias.setObjTratamientoEspecial(pd.getLista(new Integer(this.funias.getTratamientoEspecial())));
            }
            if(this.funias.getLocalizacionTopografica()!=null){
                this.funias.setObjLocalizacionTopografica(pd.getLista(new Integer(this.funias.getLocalizacionTopografica())));
            }
            
            //booleanos
            this.sbrColmatadaSi = new RichSelectBooleanRadio();
            this.sbrColmatadaNo = new RichSelectBooleanRadio();
            this.sbrColmatadaSi.setSelected(this.funias.isColmatadaAux());
            this.sbrColmatadaSi.setValue(this.funias.isColmatadaAux());
            this.sbrColmatadaNo.setSelected(!this.funias.isColmatadaAux());
            this.sbrColmatadaNo.setValue(!this.funias.isColmatadaAux());
            
            this.sbrColapsadaSi = new RichSelectBooleanRadio();
            this.sbrColapsadaNo = new RichSelectBooleanRadio();
            this.sbrColapsadaSi.setSelected(this.funias.isColapsadaAux());
            this.sbrColapsadaNo.setSelected(!this.funias.isColapsadaAux());
            this.sbrColapsadaSi.setValue(this.funias.isColapsadaAux());
            this.sbrColapsadaNo.setValue(!this.funias.isColapsadaAux());
            
            this.sbcEmbalse = new RichSelectBooleanCheckbox();
            this.sbcEmbalse.setSelected(this.funias.isEmbalseaAux());
            this.sbcEmbalse.setValue(this.funias.isEmbalseaAux());
            
            this.sbcTanque = new RichSelectBooleanCheckbox();
            this.sbcTanque.setSelected(this.funias.isTanqueAux());
            this.sbcTanque.setValue(this.funias.isTanqueAux());
           
            this.sbcAlberca = new RichSelectBooleanCheckbox();
            this.sbcAlberca.setSelected(this.funias.isAlbercaAux());
            this.sbcAlberca.setValue(this.funias.isAlbercaAux());
            
            this.sbcOtro = new RichSelectBooleanCheckbox();
            this.sbcOtro.setSelected(this.funias.isOtroAux());
            this.sbcOtro.setValue(this.funias.isOtroAux());
            
            this.sbrLetrinaSi = new RichSelectBooleanRadio();
            this.sbrLetrinaNo = new RichSelectBooleanRadio();
            this.sbrLetrinaSi.setSelected(this.funias.isLetrinaAux());
            this.sbrLetrinaNo.setSelected(!this.funias.isLetrinaAux());
            this.sbrLetrinaSi.setValue(this.funias.isLetrinaAux());
            this.sbrLetrinaNo.setValue(!this.funias.isLetrinaAux());
            
            
            this.sbrCharcoSi = new RichSelectBooleanRadio();
            this.sbrCharcoNo = new RichSelectBooleanRadio();
            this.sbrCharcoSi.setSelected(this.funias.isAguaEstancadaAux());
            this.sbrCharcoNo.setSelected(!this.funias.isAguaEstancadaAux());
            this.sbrCharcoSi.setValue(this.funias.isAguaEstancadaAux());
            this.sbrCharcoNo.setValue(!this.funias.isAguaEstancadaAux());
            
            this.sbrCriaderoSi = new RichSelectBooleanRadio();
            this.sbrCriaderoNo = new RichSelectBooleanRadio();
            this.sbrCriaderoSi.setSelected(this.funias.isCriaderoGanadoAux());
            this.sbrCriaderoNo.setSelected(!this.funias.isCriaderoGanadoAux());
            this.sbrCriaderoSi.setValue(this.funias.isCriaderoGanadoAux());
            this.sbrCriaderoNo.setValue(!this.funias.isCriaderoGanadoAux());
            
            this.sbrFiltracionSi = new RichSelectBooleanRadio();
            this.sbrFiltracionNo = new RichSelectBooleanRadio();
            this.sbrFiltracionSi.setSelected(this.funias.isFiltracionAguaAux());
            this.sbrFiltracionNo.setSelected(this.funias.isFiltracionAguaAux());
            this.sbrFiltracionSi.setValue(this.funias.isFiltracionAguaAux());
            this.sbrFiltracionNo.setValue(this.funias.isFiltracionAguaAux());
            
            this.sbrCubiertaSi = new RichSelectBooleanRadio();
            this.sbrCubiertaNo = new RichSelectBooleanRadio();
            this.sbrCubiertaSi.setSelected(this.funias.isCubiertaAdecuadaAux());
            this.sbrCubiertaNo.setSelected(!this.funias.isCubiertaAdecuadaAux());
            this.sbrCubiertaSi.setValue(this.funias.isCubiertaAdecuadaAux());
            this.sbrCubiertaNo.setValue(!this.funias.isCubiertaAdecuadaAux());
            
            this.sbrCementoSi = new RichSelectBooleanRadio();
            this.sbrCementoNo = new RichSelectBooleanRadio();
            this.sbrCementoSi.setSelected(this.funias.isPisoCementoAux());
            this.sbrCementoNo.setSelected(!this.funias.isPisoCementoAux());
            this.sbrCementoSi.setValue(this.funias.isPisoCementoAux());
            this.sbrCementoNo.setValue(!this.funias.isPisoCementoAux());
           
            this.sbrSelloSi = new RichSelectBooleanRadio();
            this.sbrSelloNo = new RichSelectBooleanRadio();
            this.sbrSelloSi.setSelected(this.funias.isSelloSanitarioAux());
            this.sbrSelloNo.setSelected(!this.funias.isSelloSanitarioAux());
            this.sbrSelloSi.setValue(this.funias.isSelloSanitarioAux());
            this.sbrSelloNo.setValue(!this.funias.isSelloSanitarioAux());
            
            this.sbrCercoSi = new RichSelectBooleanRadio();
            this.sbrCercoNo = new RichSelectBooleanRadio();
            this.sbrCercoSi.setSelected(this.funias.isCercoAdecuadoAux());
            this.sbrCercoNo.setSelected(!this.funias.isCercoAdecuadoAux());
            this.sbrCercoSi.setValue(this.funias.isCercoAdecuadoAux());
            this.sbrCercoNo.setValue(!this.funias.isCercoAdecuadoAux());
            
            this.sbrCampoInfiltracionSi = new RichSelectBooleanRadio();
            this.sbrCampoInfiltracionNo = new RichSelectBooleanRadio();
            this.sbrCampoInfiltracionSi.setSelected(this.funias.isCampoInfiltracionAux());
            this.sbrCampoInfiltracionNo.setSelected(!this.funias.isCampoInfiltracionAux());
            this.sbrCampoInfiltracionSi.setValue(this.funias.isCampoInfiltracionAux());
            this.sbrCampoInfiltracionNo.setValue(!this.funias.isCampoInfiltracionAux());
            
            this.sbrCementerioSi = new RichSelectBooleanRadio();
            this.sbrCementerioNo = new RichSelectBooleanRadio();
            this.sbrCementerioSi.setSelected(this.funias.isCementerioAux());
            this.sbrCementerioNo.setSelected(!this.funias.isCementerioAux());
            this.sbrCementerioSi.setValue(this.funias.isCementerioAux());
            this.sbrCementerioNo.setValue(!this.funias.isCementerioAux());
            
            this.sbrEstacionServicioSi = new RichSelectBooleanRadio();
            this.sbrEstacionServicioNo = new RichSelectBooleanRadio();
            this.sbrEstacionServicioSi.setSelected(this.funias.isEstacionServicioAux());
            this.sbrEstacionServicioNo.setSelected(!this.funias.isEstacionServicioAux());
            this.sbrEstacionServicioSi.setValue(this.funias.isEstacionServicioAux());
            this.sbrEstacionServicioNo.setValue(!this.funias.isEstacionServicioAux());
           
            this.sbrLagunasOxidacionSi = new RichSelectBooleanRadio();
            this.sbrLagunaOxidacionNo = new RichSelectBooleanRadio();
            this.sbrLagunasOxidacionSi.setSelected(this.funias.isLagunasOxidacionAux());
            this.sbrLagunaOxidacionNo.setSelected(!this.funias.isLagunasOxidacionAux());
            this.sbrLagunasOxidacionSi.setValue(this.funias.isLagunasOxidacionAux());
            this.sbrLagunaOxidacionNo.setValue(!this.funias.isLagunasOxidacionAux());
            
            this.sbrLavaderosSi = new RichSelectBooleanRadio();
            this.sbrLavaderosNo = new RichSelectBooleanRadio();
            this.sbrLavaderosSi.setSelected(this.funias.isLavaderoVehiculosAux());
            this.sbrLavaderosNo.setSelected(!this.funias.isLavaderoVehiculosAux());
            this.sbrLavaderosSi.setValue(this.funias.isLavaderoVehiculosAux());
            this.sbrLavaderosNo.setValue(!this.funias.isLavaderoVehiculosAux());
            
            this.sbrSacrificioSi = new RichSelectBooleanRadio();
            this.sbrSacrificioNo = new RichSelectBooleanRadio();
            this.sbrSacrificioSi.setSelected(this.funias.isPlantasSacrificioAux());
            this.sbrSacrificioNo.setSelected(!this.funias.isPlantasSacrificioAux());
            this.sbrSacrificioSi.setValue(this.funias.isPlantasSacrificioAux());
            this.sbrSacrificioNo.setValue(!this.funias.isPlantasSacrificioAux());
            
            this.sbrPozoSi = new RichSelectBooleanRadio();
            this.sbrPozoNo = new RichSelectBooleanRadio();
            this.sbrPozoSi.setSelected(this.funias.isPozosAbandonadosAux());
            this.sbrPozoNo.setSelected(!this.funias.isPozosAbandonadosAux());
            this.sbrPozoSi.setValue(this.funias.isPozosAbandonadosAux());
            this.sbrPozoNo.setValue(!this.funias.isPozosAbandonadosAux());
            
            this.sbrResiduoAgricolaSi = new RichSelectBooleanRadio();
            this.sbrResiduoAgricolaNo = new RichSelectBooleanRadio();
            this.sbrResiduoAgricolaSi.setSelected(this.funias.isResiduosAgricolasAux());
            this.sbrResiduoAgricolaNo.setSelected(!this.funias.isResiduosAgricolasAux());
            this.sbrResiduoAgricolaSi.setValue(this.funias.isResiduosAgricolasAux());
            this.sbrResiduoAgricolaNo.setValue(!this.funias.isResiduosAgricolasAux());
           
            this.sbrResiduoDomesticoSi = new RichSelectBooleanRadio();
            this.sbrResiduoDomesticoNo = new RichSelectBooleanRadio();
            this.sbrResiduoDomesticoSi.setSelected(this.funias.isResiduosDomesticoAux());
            this.sbrResiduoDomesticoNo.setSelected(!this.funias.isResiduosDomesticoAux());
            this.sbrResiduoDomesticoSi.setValue(this.funias.isResiduosDomesticoAux());
            this.sbrResiduoDomesticoNo.setValue(!this.funias.isResiduosDomesticoAux());
            
            this.sbrResiduoGanaderiaSi = new RichSelectBooleanRadio();
            this.sbrResiduoGanaderiaNo = new RichSelectBooleanRadio();
            this.sbrResiduoGanaderiaSi.setSelected(this.funias.isResiduosGanaderiaAux());
            this.sbrResiduoGanaderiaNo.setSelected(!this.funias.isResiduosGanaderiaAux());
            this.sbrResiduoGanaderiaSi.setValue(this.funias.isResiduosGanaderiaAux());
            this.sbrResiduoGanaderiaNo.setValue(!this.funias.isResiduosGanaderiaAux());
                        
            this.sbrResiduoHospitalarioSi = new RichSelectBooleanRadio();
            this.sbrResiduoHospitalarioNo = new RichSelectBooleanRadio();
            this.sbrResiduoHospitalarioSi.setSelected(this.funias.isResiduosHospitalariosAux());
            this.sbrResiduoHospitalarioNo.setSelected(!this.funias.isResiduosHospitalariosAux());
            this.sbrResiduoHospitalarioSi.setValue(this.funias.isResiduosHospitalariosAux());
            this.sbrResiduoHospitalarioNo.setValue(!this.funias.isResiduosHospitalariosAux());
            
            this.sbrResiduoIndustrialSi = new RichSelectBooleanRadio();
            this.sbrResiduoIndustrialNo = new RichSelectBooleanRadio();
            this.sbrResiduoIndustrialSi.setSelected(this.funias.isResiduosIndustrialesAux());
            this.sbrResiduoIndustrialNo.setSelected(!this.funias.isResiduosIndustrialesAux());
            this.sbrResiduoIndustrialSi.setValue(this.funias.isResiduosIndustrialesAux());
            this.sbrResiduoIndustrialNo.setValue(!this.funias.isResiduosIndustrialesAux());
            
            this.sbrResiduoMineroSi = new RichSelectBooleanRadio();
            this.sbrResiduoMineroNo = new RichSelectBooleanRadio();
            this.sbrResiduoMineroSi.setSelected(this.funias.isResiduosMinerosAux());
            this.sbrResiduoMineroNo.setSelected(!this.funias.isResiduosMinerosAux());
            this.sbrResiduoMineroSi.setValue(this.funias.isResiduosMinerosAux());
            this.sbrResiduoMineroNo.setValue(!this.funias.isResiduosMinerosAux());
            
            this.sbrBotaderoCieloAbiertoSi = new RichSelectBooleanRadio();
            this.sbrBotaderoCieloAbiertoNo = new RichSelectBooleanRadio();
            this.sbrBotaderoCieloAbiertoSi.setSelected(this.funias.isBotaceroAbiertoAux());
            this.sbrBotaderoCieloAbiertoNo.setSelected(!this.funias.isBotaceroAbiertoAux());
            this.sbrBotaderoCieloAbiertoSi.setValue(this.funias.isBotaceroAbiertoAux());
            this.sbrBotaderoCieloAbiertoNo.setValue(!this.funias.isBotaceroAbiertoAux());
           
            this.sbrCompostajeSi = new RichSelectBooleanRadio();
            this.sbrCompostajeNo = new RichSelectBooleanRadio();
            this.sbrCompostajeSi.setSelected(this.funias.isCompostajeAux());
            this.sbrCompostajeNo.setSelected(!this.funias.isCompostajeAux());
            this.sbrCompostajeSi.setValue(this.funias.isCompostajeAux());
            this.sbrCompostajeNo.setValue(!this.funias.isCompostajeAux());
            
            this.sbrIncineracionSi = new RichSelectBooleanRadio();
            this.sbrIncineracionNo = new RichSelectBooleanRadio();
            this.sbrIncineracionSi.setSelected(this.funias.isIncineracionAux());
            this.sbrIncineracionNo.setSelected(!this.funias.isIncineracionAux());
            this.sbrIncineracionSi.setValue(this.funias.isIncineracionAux());
            this.sbrIncineracionNo.setValue(!this.funias.isIncineracionAux());
            
            this.sbrReciclajeSi = new RichSelectBooleanRadio();
            this.sbrReciclajeNo = new RichSelectBooleanRadio();
            this.sbrReciclajeSi.setSelected(this.funias.isReciclajeAux());
            this.sbrReciclajeNo.setSelected(!this.funias.isReciclajeAux());
            this.sbrReciclajeSi.setValue(this.funias.isReciclajeAux());
            this.sbrReciclajeNo.setValue(!this.funias.isReciclajeAux());
                   
        }catch(IdeamException e){
            showMessage(e);
        }
    }
    
    public void cbBorrar_actionListener(ActionEvent actionEvent) {
        String nombre = ""+this.funias.getTipoFunias();
        String params[] = {nombre};
        String texto = getText("FUN_BORRAR", params);
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
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.popup_borrar);
            showPopup(this.popup_borrar, true);
        }
    }

    public void cb_si_borrar_actionListener(ActionEvent actionEvent) {
        try{
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
            uad.deleteFunias(this.funias);
            showMessage(getText("FUN_ELIMINADO"));
        }catch(IdeamException e){
            showMessage(e.getMessage());
        }
    }

    public void cb_no_borrar_actionListener(ActionEvent actionEvent) {
        showPopup(popup_borrar, false);
    }
        
    public void ifGeologia_valueChangeListener(ValueChangeEvent valueChangeEvent) {
        UploadedFile fileOld = (UploadedFile)valueChangeEvent.getOldValue();
        UploadedFile fileNew = (UploadedFile)valueChangeEvent.getNewValue();
        if (fileNew!=null){
            String tipoArchivo = fileNew.getContentType();
            if (!tipoArchivo.endsWith("pdf")){
                String params[] = { fileNew.getFilename(), "PDF" };
                showMessage("tipo_archivo_incorrecto",
                            params,
                            FacesMessage.SEVERITY_ERROR,
                            ifGeologia);
                this.ifGeologia.resetValue();
                this.uploadedFile=null;
                this.archivoCargado=null;
                return;
            }
            this.cargarArchivo(fileNew, fileOld, ConstantesCaptaciones.ARCHIVO_TIPO_REGISTRO_LITOLOGICO);
            this.setUploadedFile(fileNew);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.ifGeologia);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.pgl_archivo1);
        }
    }
    
    public void ifDocumentoConstruccion_valueChangeListener(ValueChangeEvent valueChangeEvent) {
        UploadedFile fileOld = (UploadedFile)valueChangeEvent.getOldValue();
        UploadedFile fileNew = (UploadedFile)valueChangeEvent.getNewValue();
        if (fileNew!=null){
            String tipoArchivo = fileNew.getContentType();
            if (!tipoArchivo.endsWith("pdf")){
                String params[] = { fileNew.getFilename(), "PDF" };
                showMessage("tipo_archivo_incorrecto",
                            params,
                            FacesMessage.SEVERITY_ERROR,
                            ifDocumentoConstruccion);
                this.ifDocumentoConstruccion.resetValue();
                this.uploadedFile=null;
                this.archivoCargado=null;
                return;
            }
            this.cargarArchivo(fileNew, fileOld, ConstantesCaptaciones.ARCHIVO_TIPO_DISENO);
            this.setUploadedFile(fileNew);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.ifDocumentoConstruccion);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.pgl_archivo2);

        }
    }

    public void ifDocumentoGeofisica_valueChangeListener(ValueChangeEvent valueChangeEvent) {
        UploadedFile fileOld = (UploadedFile)valueChangeEvent.getOldValue();
        UploadedFile fileNew = (UploadedFile)valueChangeEvent.getNewValue();
        if (fileNew!=null){
            String tipoArchivo = fileNew.getContentType();
            if (!tipoArchivo.endsWith("pdf")){
                String params[] = { fileNew.getFilename(), "PDF" };
                showMessage("tipo_archivo_incorrecto",
                            params,
                            FacesMessage.SEVERITY_ERROR,
                            ifDocumentoGeofisica);
                this.ifDocumentoGeofisica.resetValue();
                this.uploadedFile=null;
                this.archivoCargado=null;
                return;
            }
            this.cargarArchivo(fileNew, fileOld, ConstantesCaptaciones.ARCHIVO_TIPO_REGISTRO_GEOFISICO);
            this.setUploadedFile(fileNew);
            
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.ifDocumentoGeofisica);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.pgl_archivo3);
        }
    }
    
    
    public void cargarArchivo(UploadedFile uploadedFileNew, 
                              UploadedFile uploadedFileOld, String tipoCarga) {
        this.archivoCargado=null;
        
        System.out.println(":::::::::::::CARGAR ARCHIVOS 1: "+((this.archivosCargados!=null)?this.archivosCargados.size():0));
        if (uploadedFileOld!=null){
            ArchivoCargadoTO ac = new ArchivoCargadoTO();
            ac.setFile(new File(uploadedFileOld.getFilename()));
            ac.setFileName(uploadedFileOld.getFilename());
            try {
                ac.setInputStream(uploadedFileOld.getInputStream());
            } catch (IOException e) {
                this.archivoCargado=null;
                showMessage(new IdeamException(e));
            }
            ac.setSize(uploadedFileOld.getLength());
            ac.setType(uploadedFileOld.getContentType());
            try{
                if(this.archivosCargados!=null ){
                    System.out.println(":::::::::::::ENTRO REMOVER 1: "+ac);
                    System.out.println(":::::::::::::EXISTE: "+this.archivosCargados.contains(ac));
                   this.archivosCargados.remove(ac);
                }
            }catch(Exception e){
            }
        }
        
        System.out.println(":::::::::::::CARGAR ARCHIVOS 2: "+((this.archivosCargados!=null)?this.archivosCargados.size():0));
        if (uploadedFileNew!=null){
            ArchivoCargadoTO ac = new ArchivoCargadoTO();
            ac.setFile(new File(uploadedFileNew.getFilename()));
            ac.setFileName(uploadedFileNew.getFilename());
            try {
                ac.setInputStream(uploadedFileNew.getInputStream());
            } catch (IOException e) {
                this.archivoCargado=null;
                showMessage(new IdeamException(e));
            }
            ac.setSize(uploadedFileNew.getLength());
            ac.setType(uploadedFileNew.getContentType());
            ac.setTipoCarga(tipoCarga);
            this.archivoCargado = ac;
            if(this.archivosCargados==null){
                this.archivosCargados = new ArrayList();
            }
            
            this.archivosCargados.add(this.archivoCargado);
            System.out.println(":::::::::::::ARCHIVO: "+this.archivoCargado);
            System.out.println(":::::::::::::CARGAR ARCHIVOS 3: "+this.archivosCargados.size());
            
        }
    }
    
    public void cl_documento_actionListener(ActionEvent actionEvent) {
        if (this.documentoSeleccionado==null){
            showMessage(getText("seleccionar_registro"),
                        FacesMessage.SEVERITY_ERROR);
            return;
        }
        showReport(this.documentoSeleccionado.getDocumento());
    }

    public void cb_borrar_docto_actionListener(ActionEvent actionEvent) {
        //showPopup(p_borrar_docto, true);
        System.out.println(":::::::::::::BORRAR ARCHIVO");
        
        if (this.documentoSeleccionado==null){
            showMessage(getText("seleccionar_registro"),
                        FacesMessage.SEVERITY_ERROR);
            return;
        }
        
        System.out.println(":::::::::::::BORRAR ARCHIVO: "+this.documentoSeleccionado);
        try{
            System.out.println(":::::::::::::EXISTE: "+
                               this.funias.getSubttFuniasDocumentosList().contains(this.documentoSeleccionado));
            this.funias.getSubttFuniasDocumentosList().remove(this.documentoSeleccionado);
            
            this.archivosRelacionados = 
                ((this.funias.getSubttFuniasDocumentosList()!=null)
                ? this.funias.getSubttFuniasDocumentosList().size()>0
                : false );
            
            System.out.println(":::::::::::::::::::::: HAY ARCHIVOS: "+this.archivosRelacionados);
            System.out.println("::::::::::::::::::::::ARCHIVOS EN OBJETO: "+this.funias.getSubttFuniasDocumentosList());
        }catch(Exception e){
            
        }
        
        this.documentoSeleccionado = null;
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelStretchLayout1);
    }
    
    
    public void table_selectionListener(SelectionEvent selectionEvent) {
        System.out.println(":::::::::::::::::::::: VAR ARCHIVO 1: "+this.documentoSeleccionado);
        this.documentoSeleccionado = null;
        RichTable t_aux = (RichTable)selectionEvent.getSource();
        System.out.println("::::::::::::::::::::::ARCHIVO SEL: "+(SubttFuniasDocumentos)t_aux.getSelectedRowData());
        
        this.documentoSeleccionado = (SubttFuniasDocumentos)t_aux.getSelectedRowData();
        
        System.out.println(":::::::::::::::::::::: VAR ARCHIVO 2: "+this.documentoSeleccionado);
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

        if(continuar){
            this.save();
            showPopup(p_registro_exitoso, true);
        }
    }
    
    public void save(){
        try{
            UsuarioConectadoTO usuarioConectado = 
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
            
            this.funias.setIdCaptacion(this.captacion.getCodigo());

            ///////geologia
            funias.setLocalizacionTopografica((this.socLocTopografica.getValue()!=null)
                                              ? ((Lista)this.socLocTopografica.getValue()).getCodigo()
                                              : null);
            funias.setUnidadGeologica((this.itUnidadGeo.getValue()!=null)
                                      ?this.itUnidadGeo.getValue().toString()
                                      :null);
            funias.setLitologiaSuperficial((this.itLitologiaSuperficial.getValue()!=null)
                                           ?this.itLitologiaSuperficial.getValue().toString()
                                           :null);
            funias.setGeoforma((this.socGeoforma.getValue()!=null)
                                              ? ((Lista)this.socGeoforma.getValue()).getCodigo()
                                              : null);
            funias.setEstructura((this.socEstructura.getValue()!=null)
                                              ? ((Lista)this.socEstructura.getValue()).getCodigo()
                                              : null);
            funias.setNombreEstructura((this.itNombreEstructura.getValue()!=null)
                                       ?this.itNombreEstructura.getValue().toString()
                                       :null);

            //documento relacionado


            //////Construccion

            Calendar fecha = GregorianCalendar.getInstance();
            funias.setFechaConstruccion((this.idFechaConstruccion.getValue()!=null)
                                        ?(Date)this.idFechaConstruccion.getValue()
                                        :null);
            funias.setDiametroInterior((this.itDiaIntConstruccion.getValue()!=null)
                                       ? Double.parseDouble(""+this.itDiaIntConstruccion.getValue())
                                       : null);
            funias.setDiametroExterior((this.itDiaExtConstruccion.getValue()!=null)
                                       ? Double.parseDouble(""+this.itDiaExtConstruccion.getValue())
                                       : null);
            funias.setDiametroPerforacion((this.itDiaPerfCostruccion.getValue()!=null)
                                       ? Double.parseDouble(""+this.itDiaPerfCostruccion.getValue())
                                       : null);
            funias.setMaterialUsado((this.socMaterialConstruccion.getValue()!=null)
                                        ? ((Lista)this.socMaterialConstruccion.getValue()).getCodigo()
                                        : null);
            funias.setMetodoConstruccion((this.socMetodoConstruccion.getValue()!=null)
                                        ? ((Lista)this.socMetodoConstruccion.getValue()).getCodigo()
                                        : null);
            funias.setTratamientoEspecial(this.socTratamientoConstruccion!=null&&(this.socTratamientoConstruccion.getValue()!=null)
                                        ? ((Lista)this.socTratamientoConstruccion.getValue()).getCodigo()
                                        : null);
            funias.setFluidoUsado(this.socFluidoConstruccion!=null&&(this.socFluidoConstruccion.getValue()!=null)
                                        ? ((Lista)this.socFluidoConstruccion.getValue()).getCodigo()
                                        : null);
            funias.setCompaniaPerforadora((this.itCompaniaPerforadora.getValue()!=null)
                                          ?this.itCompaniaPerforadora.getValue().toString()
                                          :null);

            if(this.sbrColmatadaSi.isSelected()){
                funias.setEstaColmatado(Constantes.TRUE_TO_INTEGER);
            }else{
                funias.setEstaColmatado(Constantes.FALSE_TO_INTEGER);
            }

            if(this.sbrColapsadaSi.isSelected()){
                funias.setEstaColapsado(Constantes.TRUE_TO_INTEGER);
            }else{
                funias.setEstaColapsado(Constantes.FALSE_TO_INTEGER);
            }

            funias.setProfundidadEntubado((this.itProfEntubado.getValue()!=null)
                                       ? Double.parseDouble(""+this.itProfEntubado.getValue())
                                       : null);
            funias.setProfundidadPerforacion((this.itProfPerforacion.getValue()!=null)
                                       ? Double.parseDouble(""+this.itProfPerforacion.getValue())
                                       : null);
            funias.setCantidadGravilla((this.itCantidadGravilla.getValue()!=null)
                                       ? Double.parseDouble(""+this.itCantidadGravilla.getValue())
                                       : null);
            funias.setNivelEngravillado((this.itNivelGravilla.getValue()!=null)
                                       ? Double.parseDouble(""+this.itNivelGravilla.getValue())
                                       : null);
            funias.setDiametroPromedioGravilla((this.itDiametroPromedio.getValue()!=null)
                                       ? Double.parseDouble(""+this.itDiametroPromedio.getValue())
                                       : null);

            if(this.sbcEmbalse.isSelected()){
                funias.setEmbalse(Constantes.TRUE_TO_INTEGER);
            }else{
                funias.setEmbalse(Constantes.FALSE_TO_INTEGER);
            }
            funias.setCapacidadEmbalse((this.itCapacidadEmbalse.getValue()!=null)
                                       ? Double.parseDouble(""+this.itCapacidadEmbalse.getValue())
                                       : null);

            if(this.sbcTanque.isSelected()){
                funias.setTanque(Constantes.TRUE_TO_INTEGER);
            }else{
                funias.setTanque(Constantes.FALSE_TO_INTEGER);
            }
            funias.setCapacidadTanque((this.itCapacidadTanque.getValue()!=null)
                                       ? Double.parseDouble(""+this.itCapacidadTanque.getValue())
                                       : null);

            if(this.sbcAlberca.isSelected()){
                funias.setAlberca(Constantes.TRUE_TO_INTEGER);
            }else{
                funias.setAlberca(Constantes.FALSE_TO_INTEGER);
            }
            funias.setCapacidadAlberca((this.itCapacidadAlberca.getValue()!=null)
                                       ? Double.parseDouble(""+this.itCapacidadAlberca.getValue())
                                       : null);

            if(this.sbcOtro.isSelected()){
                funias.setOtro(Constantes.TRUE_TO_INTEGER);
            }else{
                funias.setOtro(Constantes.FALSE_TO_INTEGER);
            }
            funias.setCapacidadOtro((this.itCapacidadOtro.getValue()!=null)
                                    ? Double.parseDouble(""+this.itCapacidadOtro.getValue())
                                    : null);
            //documento relacionado

            ////////////Diagnóstico
            funias.setLetrinaCercana((this.sbrLetrinaSi.isSelected())
                                     ?Constantes.TRUE_TO_INTEGER
                                     :Constantes.FALSE_TO_INTEGER);
            funias.setDistanciaLetrina((this.itDistanciaLetrina.getValue()!=null)
                                    ? Double.parseDouble(""+this.itDistanciaLetrina.getValue())
                                    : null);
            funias.setAguaEstancada((this.sbrCharcoSi.isSelected())
                                     ?Constantes.TRUE_TO_INTEGER
                                     :Constantes.FALSE_TO_INTEGER);
            funias.setDistancia((this.itDistanciaCharco.getValue()!=null)
                                ? Double.parseDouble(""+this.itDistanciaCharco.getValue())
                                : null);
            funias.setCriaderoGanado((this.sbrCriaderoSi.isSelected())
                                     ?Constantes.TRUE_TO_INTEGER
                                     :Constantes.FALSE_TO_INTEGER);
            funias.setDistanciaCriadero((this.itDistanciaCriadero.getValue()!=null)
                                    ? Double.parseDouble(""+this.itDistanciaCriadero.getValue())
                                    : null);
            funias.setFiltracionAgua((this.sbrFiltracionSi.isSelected())
                                     ?Constantes.TRUE_TO_INTEGER
                                     :Constantes.FALSE_TO_INTEGER);
            funias.setDistanciaFiltracion((this.itDistanciaFiltracion.getValue()!=null)
                                ? Double.parseDouble(""+this.itDistanciaFiltracion.getValue())
                                : null);

            funias.setCubiertaAdecuada((this.sbrCubiertaSi.isSelected())
                                     ?Constantes.TRUE_TO_INTEGER
                                     :Constantes.FALSE_TO_INTEGER);
            funias.setPisoCemento((this.sbrCementoSi.isSelected())
                                     ?Constantes.TRUE_TO_INTEGER
                                     :Constantes.FALSE_TO_INTEGER);
            funias.setSelloSanitario((this.sbrSelloSi.isSelected())
                                     ?Constantes.TRUE_TO_INTEGER
                                     :Constantes.FALSE_TO_INTEGER);
            funias.setCercoAdecuado((this.sbrCercoSi.isSelected())
                                     ?Constantes.TRUE_TO_INTEGER
                                     :Constantes.FALSE_TO_INTEGER);

            funias.setCampoInfiltracion((this.sbrCampoInfiltracionSi.isSelected())
                                     ?Constantes.TRUE_TO_INTEGER
                                     :Constantes.FALSE_TO_INTEGER);
            funias.setDistanciaCampo((this.itDistanciaCampoInfiltracion.getValue()!=null)
                                ? Double.parseDouble(""+this.itDistanciaCampoInfiltracion.getValue())
                                : null);
            funias.setCementerio((this.sbrCementerioSi.isSelected())
                                     ?Constantes.TRUE_TO_INTEGER
                                     :Constantes.FALSE_TO_INTEGER);
            funias.setDistanciaCementerio((this.itDistanciaCementerio.getValue()!=null)
                                ? Double.parseDouble(""+this.itDistanciaCementerio.getValue())
                                : null);
            funias.setEstacionServicio((this.sbrEstacionServicioSi.isSelected())
                                     ?Constantes.TRUE_TO_INTEGER
                                     :Constantes.FALSE_TO_INTEGER);
            funias.setDistanciaEstacionServicio((this.itDistanciaEstacion.getValue()!=null)
                                ? Double.parseDouble(""+this.itDistanciaEstacion.getValue())
                                : null);
            funias.setLagunasOxidacion((this.sbrLagunasOxidacionSi.isSelected())
                                     ?Constantes.TRUE_TO_INTEGER
                                     :Constantes.FALSE_TO_INTEGER);
            funias.setDistanciaLagunas((this.itDistanciaLaguna.getValue()!=null)
                                ? Double.parseDouble(""+this.itDistanciaLaguna.getValue())
                                : null);
            funias.setLavaderoVehiculos((this.sbrLavaderosSi.isSelected())
                                     ?Constantes.TRUE_TO_INTEGER
                                     :Constantes.FALSE_TO_INTEGER);
            funias.setDistanciaLavadero((this.itDistanciaLavaderos.getValue()!=null)
                                ? Double.parseDouble(""+this.itDistanciaLavaderos.getValue())
                                : null);
            funias.setPlantasSacrificio((this.sbrSacrificioSi.isSelected())
                                     ?Constantes.TRUE_TO_INTEGER
                                     :Constantes.FALSE_TO_INTEGER);
            funias.setDistanciaPlantas((this.itSacrificioDistancia.getValue()!=null)
                                ? Double.parseDouble(""+this.itSacrificioDistancia.getValue())
                                : null);
            funias.setPozosAbandonados((this.sbrPozoSi.isSelected())
                                     ?Constantes.TRUE_TO_INTEGER
                                     :Constantes.FALSE_TO_INTEGER);
            funias.setDistanciaPozos((this.itPozoDistancia.getValue()!=null)
                                ? Double.parseDouble(""+this.itPozoDistancia.getValue())
                                : null);

            funias.setResiduosAgricolas((this.sbrResiduoAgricolaSi.isSelected())
                                     ?Constantes.TRUE_TO_INTEGER
                                     :Constantes.FALSE_TO_INTEGER);
            funias.setResiduosDomestico((this.sbrResiduoDomesticoSi.isSelected())
                                     ?Constantes.TRUE_TO_INTEGER
                                     :Constantes.FALSE_TO_INTEGER);
            funias.setResiduosGanaderia((this.sbrResiduoGanaderiaSi.isSelected())
                                     ?Constantes.TRUE_TO_INTEGER
                                     :Constantes.FALSE_TO_INTEGER);
            funias.setResiduosHospitalarios((this.sbrResiduoHospitalarioSi.isSelected())
                                     ?Constantes.TRUE_TO_INTEGER
                                     :Constantes.FALSE_TO_INTEGER);
            funias.setResiduosIndustriales((this.sbrResiduoIndustrialSi.isSelected())
                                     ?Constantes.TRUE_TO_INTEGER
                                     :Constantes.FALSE_TO_INTEGER);
            funias.setResiduosMineros((this.sbrResiduoMineroSi.isSelected())
                                     ?Constantes.TRUE_TO_INTEGER
                                     :Constantes.FALSE_TO_INTEGER);

            funias.setBotaceroAbierto((this.sbrBotaderoCieloAbiertoSi.isSelected())
                                     ?Constantes.TRUE_TO_INTEGER
                                     :Constantes.FALSE_TO_INTEGER);
            funias.setCompostaje((this.sbrCompostajeSi.isSelected())
                                     ?Constantes.TRUE_TO_INTEGER
                                     :Constantes.FALSE_TO_INTEGER);
            funias.setIncineracion((this.sbrIncineracionSi.isSelected())
                                     ?Constantes.TRUE_TO_INTEGER
                                     :Constantes.FALSE_TO_INTEGER);
            funias.setReciclaje((this.sbrReciclajeSi.isSelected())
                                     ?Constantes.TRUE_TO_INTEGER
                                     :Constantes.FALSE_TO_INTEGER);

            ///////////Geofisica
            Calendar fechaMed = GregorianCalendar.getInstance();
            funias.setFechaMedicion((this.idMedidaGeofisica.getValue()!=null)
                                    ?(Date)this.idMedidaGeofisica.getValue()
                                    :null);
            funias.setEquipoUsado((this.itEquipoGeofisica.getValue()!=null)
                                  ?this.itEquipoGeofisica.getValue().toString()
                                  :null);
            funias.setTipoRegistro((this.socTipoRegistroGeofisica.getValue()!=null)
                                ? ((Lista)this.socTipoRegistroGeofisica.getValue()).getCodigo()
                                : null);
            funias.setCompaniaEjecutora((this.itCompaniaEjeGeofisica.getValue()!=null)
                                        ?this.itCompaniaEjeGeofisica.getValue().toString()
                                        :null);
            funias.setResistividadLodo((this.itResistLodoGeofisica.getValue()!=null)
                                    ? Double.parseDouble(""+this.itResistLodoGeofisica.getValue())
                                    : null);
            funias.setTemperaturaLodo((this.itTemperaturaLodo.getValue()!=null)
                                    ? Double.parseDouble(""+this.itTemperaturaLodo.getValue())
                                    : null);
            funias.setProfundidadRegistro((this.itProfundidadRegGeofisica.getValue()!=null)
                                    ? Double.parseDouble(""+this.itProfundidadRegGeofisica.getValue())
                                    : null);
            funias.setCalidadRegistro((this.itCalidadRegistroGeofisica.getValue()!=null)
                                    ? Integer.parseInt(""+this.itCalidadRegistroGeofisica.getValue())
                                    : null);

            //documento
            if(this.archivosCargados != null){
                try{
                    System.out.println("==============ARCHIVOS EN BEAN: "+this.archivosCargados.size());
                    if(funias.getSubttFuniasDocumentosList() == null){
                        funias.setSubttFuniasDocumentosList(new ArrayList()); 
                    }
                    for(ArchivoCargadoTO archivo: this.archivosCargados){
                        SubttFuniasDocumentos doc = new SubttFuniasDocumentos();
                        doc.setDocumento(archivo.getContent());
                        doc.setIdFunias(funias.getId());
                        doc.setCodigoAutoridad(usuarioConectado.getUsuario().
                                       getAutoridadAmbiental().getId().longValue());
                        doc.setTipo(archivo.getTipoCarga());
                        funias.getSubttFuniasDocumentosList().add(doc);
                        
                    }
                }catch(IdeamException e){
                    showMessage(e);
                }
            }


            /////////

            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
            uad.updateFunias(this.funias);

        }catch(IdeamException e){
        showMessage(e);
        }
    }
    /////////////////////
    
    public void setCaptacion(Captacion captacion) {
        this.captacion = captacion;
    }

    public Captacion getCaptacion() {
        return captacion;
    }

    public void setFunias(SubttFunias funias) {
        this.funias = funias;
    }

    public SubttFunias getFunias() {
        return funias;
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

    public void setConcesion(Concesion concesion) {
        this.concesion = concesion;
    }

    public Concesion getConcesion() {
        return concesion;
    }
    
    public void setListaTiposFunias(List listaTiposFunias) {
        this.listaTiposFunias = listaTiposFunias;
    }

    public List getListaTiposFunias() {
        return listaTiposFunias;
    }

    public void setListaLocalizacionTopo(List listaLocalizacionTopo) {
        this.listaLocalizacionTopo = listaLocalizacionTopo;
    }

    public List getListaLocalizacionTopo() {
        return listaLocalizacionTopo;
    }

    public void setListaGeoforma(List listaGeoforma) {
        this.listaGeoforma = listaGeoforma;
    }

    public List getListaGeoforma() {
        return listaGeoforma;
    }

    public void setListaEstructura(List listaEstructura) {
        this.listaEstructura = listaEstructura;
    }

    public List getListaEstructura() {
        return listaEstructura;
    }
    
    public void setListaAcabado(List listaAcabado) {
        this.listaAcabado = listaAcabado;
    }

    public List getListaAcabado() {
        return listaAcabado;
    }

    public void setListaTipoRegistro(List listaTipoRegistro) {
        this.listaTipoRegistro = listaTipoRegistro;
    }

    public List getListaTipoRegistro() {
        return listaTipoRegistro;
    }

    public void setListaMaterial(List listaMaterial) {
        this.listaMaterial = listaMaterial;
    }

    public List getListaMaterial() {
        return listaMaterial;
    }

    public void setListaMetodoConstruccion(List listaMetodoConstruccion) {
        this.listaMetodoConstruccion = listaMetodoConstruccion;
    }

    public List getListaMetodoConstruccion() {
        return listaMetodoConstruccion;
    }

    public void setListaTratamiento(List listaTratamiento) {
        this.listaTratamiento = listaTratamiento;
    }

    public List getListaTratamiento() {
        return listaTratamiento;
    }

    public void setListaFluidoConstruccion(List listaFluidoConstruccion) {
        this.listaFluidoConstruccion = listaFluidoConstruccion;
    }

    public List getListaFluidoConstruccion() {
        return listaFluidoConstruccion;
    }

    public void setTipoFunias(String tipoFunias) {
        this.tipoFunias = tipoFunias;
    }

    public String getTipoFunias() {
        return tipoFunias;
    }
        
    public void setGeologia(boolean geologia) {
        this.geologia = geologia;
    }
    
    public boolean isGeologia() {
        if(this.funias.getTipoFunias().longValue() == 
                ConstantesParametros.LISTA_GEOLOGIA_GEOMORFOLOGIA){
            this.geologia = true;
        }else{
            this.geologia = false;    
        }
        return geologia;
    }

    public void setGeofisica(boolean geofisica) {
        this.geofisica = geofisica;
    }

    public boolean isGeofisica() {
        if(this.funias.getTipoFunias().longValue() == 
                ConstantesParametros.LISTA_GEOFISICA){
            this.geofisica = true;
        }else{
            this.geofisica = false;    
        }
        return geofisica;
    }

    public void setConstruccion(boolean construccion) {
        this.construccion = construccion;
    }

    public boolean isConstruccion() {
        if(this.funias.getTipoFunias().longValue() == 
                ConstantesParametros.LISTA_CONSTRUCCION_DISENO){
            this.construccion = true;
        }else{
            this.construccion = false;    
        }
        return construccion;
    }

    public void setDiagnostico(boolean diagnostico) {
        this.diagnostico = diagnostico;
    }

    public boolean isDiagnostico() {
        if(this.funias.getTipoFunias().longValue() == 
                ConstantesParametros.LISTA_DIAGNOSTICO_SANITARIO){
            this.diagnostico = true;
        }else{
            this.diagnostico = false;    
        }
        return diagnostico;
    }

    public void setExplotacion(boolean explotacion) {
        this.explotacion = explotacion;
    }

    public boolean isExplotacion() {
        if(this.funias.getTipoFunias().longValue() == 
                ConstantesParametros.LISTA_EXPLOTACION){
            this.explotacion = true;
        }else{
            this.explotacion = false;    
        }
        return explotacion;
    }
    
    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setArchivoCargado(ArchivoCargadoTO archivoCargado) {
        this.archivoCargado = archivoCargado;
    }

    public ArchivoCargadoTO getArchivoCargado() {
        return archivoCargado;
    }
    
    //////////////////////////////
    
    
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

    public void setIfGeologia(RichInputFile inputFile1) {
        this.ifGeologia = inputFile1;
    }

    public RichInputFile getIfGeologia() {
        return ifGeologia;
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


    public void setPanelBox1(RichPanelBox panelBox1) {
        this.panelBox1 = panelBox1;
    }

    public RichPanelBox getPanelBox1() {
        return panelBox1;
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
    
    public void setPanelBox2(RichPanelBox panelBox2) {
        this.panelBox2 = panelBox2;
    }

    public RichPanelBox getPanelBox2() {
        return panelBox2;
    }

    

    public void setPanelGroupLayout11(RichPanelGroupLayout panelGroupLayout11) {
        this.panelGroupLayout11 = panelGroupLayout11;
    }

    public RichPanelGroupLayout getPanelGroupLayout11() {
        return panelGroupLayout11;
    }

    public void setPanelFormLayout6(RichPanelFormLayout panelFormLayout6) {
        this.panelFormLayout6 = panelFormLayout6;
    }

    public RichPanelFormLayout getPanelFormLayout6() {
        return panelFormLayout6;
    }
    
    public void setPanelBox3(RichPanelBox panelBox3) {
        this.panelBox3 = panelBox3;
    }

    public RichPanelBox getPanelBox3() {
        return panelBox3;
    }

    

    public void setPanelGroupLayout12(RichPanelGroupLayout panelGroupLayout12) {
        this.panelGroupLayout12 = panelGroupLayout12;
    }

    public RichPanelGroupLayout getPanelGroupLayout12() {
        return panelGroupLayout12;
    }

    public void setPanelFormLayout7(RichPanelFormLayout panelFormLayout7) {
        this.panelFormLayout7 = panelFormLayout7;
    }

    public RichPanelFormLayout getPanelFormLayout7() {
        return panelFormLayout7;
    }


    public void setPanelBox5(RichPanelBox panelBox5) {
        this.panelBox5 = panelBox5;
    }

    public RichPanelBox getPanelBox5() {
        return panelBox5;
    }


    public void setPanelGroupLayout14(RichPanelGroupLayout panelGroupLayout14) {
        this.panelGroupLayout14 = panelGroupLayout14;
    }

    public RichPanelGroupLayout getPanelGroupLayout14() {
        return panelGroupLayout14;
    }

    public void setPanelFormLayout9(RichPanelFormLayout panelFormLayout9) {
        this.panelFormLayout9 = panelFormLayout9;
    }

    public RichPanelFormLayout getPanelFormLayout9() {
        return panelFormLayout9;
    }


    public void setSocLocTopografica(RichSelectOneChoice socLocTopografica) {
        this.socLocTopografica = socLocTopografica;
    }

    public RichSelectOneChoice getSocLocTopografica() {
        return socLocTopografica;
    }

    public void setSiLocTopografica(UISelectItems siLocTopografica) {
        this.siLocTopografica = siLocTopografica;
    }

    public UISelectItems getSiLocTopografica() {
        return siLocTopografica;
    }

    public void setItUnidadGeo(RichInputText itUnidadGeo) {
        this.itUnidadGeo = itUnidadGeo;
    }

    public RichInputText getItUnidadGeo() {
        return itUnidadGeo;
    }

    public void setItLitologiaSuperficial(RichInputText itLitologiaSuperficial) {
        this.itLitologiaSuperficial = itLitologiaSuperficial;
    }

    public RichInputText getItLitologiaSuperficial() {
        return itLitologiaSuperficial;
    }

    public void setSocGeoforma(RichSelectOneChoice socGeoforma) {
        this.socGeoforma = socGeoforma;
    }

    public RichSelectOneChoice getSocGeoforma() {
        return socGeoforma;
    }

    public void setSiGeoforma(UISelectItems siGeoforma) {
        this.siGeoforma = siGeoforma;
    }

    public UISelectItems getSiGeoforma() {
        return siGeoforma;
    }

    public void setSocEstructura(RichSelectOneChoice socEstructura) {
        this.socEstructura = socEstructura;
    }

    public RichSelectOneChoice getSocEstructura() {
        return socEstructura;
    }

    public void setSiEstructura(UISelectItems siEstructura) {
        this.siEstructura = siEstructura;
    }

    public UISelectItems getSiEstructura() {
        return siEstructura;
    }

    public void setItNombreEstructura(RichInputText itNombreEstructura) {
        this.itNombreEstructura = itNombreEstructura;
    }

    public RichInputText getItNombreEstructura() {
        return itNombreEstructura;
    }

    public void setIdFechaConstruccion(RichInputDate idFechaConstruccion) {
        this.idFechaConstruccion = idFechaConstruccion;
    }

    public RichInputDate getIdFechaConstruccion() {
        return idFechaConstruccion;
    }


    public void setPnlFuentesContaminaDiagnostico(RichPanelBox pnlFuentesContaminaDiagnostico) {
        this.pnlFuentesContaminaDiagnostico = pnlFuentesContaminaDiagnostico;
    }

    public RichPanelBox getPnlFuentesContaminaDiagnostico() {
        return pnlFuentesContaminaDiagnostico;
    }

    public void setPanelStretchLayout17(RichPanelStretchLayout panelStretchLayout17) {
        this.panelStretchLayout17 = panelStretchLayout17;
    }

    public RichPanelStretchLayout getPanelStretchLayout17() {
        return panelStretchLayout17;
    }

    public void setPanelGroupLayout45(RichPanelGroupLayout panelGroupLayout45) {
        this.panelGroupLayout45 = panelGroupLayout45;
    }

    public RichPanelGroupLayout getPanelGroupLayout45() {
        return panelGroupLayout45;
    }

    public void setPanelGroupLayout46(RichPanelGroupLayout panelGroupLayout46) {
        this.panelGroupLayout46 = panelGroupLayout46;
    }

    public RichPanelGroupLayout getPanelGroupLayout46() {
        return panelGroupLayout46;
    }

    public void setCmbFuentesContaminaExplotacion(RichCommandButton cmbFuentesContaminaExplotacion) {
        this.cmbFuentesContaminaExplotacion = cmbFuentesContaminaExplotacion;
    }

    public RichCommandButton getCmbFuentesContaminaExplotacion() {
        return cmbFuentesContaminaExplotacion;
    }

    public void setSpacer15(RichSpacer spacer15) {
        this.spacer15 = spacer15;
    }

    public RichSpacer getSpacer15() {
        return spacer15;
    }

    public void setPanelGroupLayout47(RichPanelGroupLayout panelGroupLayout47) {
        this.panelGroupLayout47 = panelGroupLayout47;
    }

    public RichPanelGroupLayout getPanelGroupLayout47() {
        return panelGroupLayout47;
    }

    public void setPanelFormLayout15(RichPanelFormLayout panelFormLayout15) {
        this.panelFormLayout15 = panelFormLayout15;
    }

    public RichPanelFormLayout getPanelFormLayout15() {
        return panelFormLayout15;
    }


    public void setSocTipoAcabado(RichSelectOneChoice socTipoAcabado) {
        this.socTipoAcabado = socTipoAcabado;
    }

    public RichSelectOneChoice getSocTipoAcabado() {
        return socTipoAcabado;
    }

    public void setSiTipoAcabado(UISelectItems siTipoAcabado) {
        this.siTipoAcabado = siTipoAcabado;
    }

    public UISelectItems getSiTipoAcabado() {
        return siTipoAcabado;
    }


    public void setOtDatosBasicosGeologia(RichOutputText otDatosBasicosGeologia) {
        this.otDatosBasicosGeologia = otDatosBasicosGeologia;
    }

    public RichOutputText getOtDatosBasicosGeologia() {
        return otDatosBasicosGeologia;
    }

    public void setOtCarGeomorfoGeologia(RichOutputText otCarGeomorfoGeologia) {
        this.otCarGeomorfoGeologia = otCarGeomorfoGeologia;
    }

    public RichOutputText getOtCarGeomorfoGeologia() {
        return otCarGeomorfoGeologia;
    }

    public void setOtRegistroGeologia(RichOutputText otRegistroGeologia) {
        this.otRegistroGeologia = otRegistroGeologia;
    }

    public RichOutputText getOtRegistroGeologia() {
        return otRegistroGeologia;
    }

    public void setSpacer16(RichSpacer spacer16) {
        this.spacer16 = spacer16;
    }

    public RichSpacer getSpacer16() {
        return spacer16;
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


    public void setOtDatosGeofisica(RichOutputText otDatosGeofisica) {
        this.otDatosGeofisica = otDatosGeofisica;
    }

    public RichOutputText getOtDatosGeofisica() {
        return otDatosGeofisica;
    }

    public void setSpacer19(RichSpacer spacer19) {
        this.spacer19 = spacer19;
    }

    public RichSpacer getSpacer19() {
        return spacer19;
    }

    public void setIdMedidaGeofisica(RichInputDate idMedidaGeofisica) {
        this.idMedidaGeofisica = idMedidaGeofisica;
    }

    public RichInputDate getIdMedidaGeofisica() {
        return idMedidaGeofisica;
    }

    public void setItEquipoGeofisica(RichInputText itEquipoGeofisica) {
        this.itEquipoGeofisica = itEquipoGeofisica;
    }

    public RichInputText getItEquipoGeofisica() {
        return itEquipoGeofisica;
    }

    public void setSocTipoRegistroGeofisica(RichSelectOneChoice socTipoRegistroGeofisica) {
        this.socTipoRegistroGeofisica = socTipoRegistroGeofisica;
    }

    public RichSelectOneChoice getSocTipoRegistroGeofisica() {
        return socTipoRegistroGeofisica;
    }

    public void setSiTipoRegistroGeofisica(UISelectItems siTipoRegistroGeofisica) {
        this.siTipoRegistroGeofisica = siTipoRegistroGeofisica;
    }

    public UISelectItems getSiTipoRegistroGeofisica() {
        return siTipoRegistroGeofisica;
    }

    public void setItCompaniaEjeGeofisica(RichInputText itCompaniaEjeGeofisica) {
        this.itCompaniaEjeGeofisica = itCompaniaEjeGeofisica;
    }

    public RichInputText getItCompaniaEjeGeofisica() {
        return itCompaniaEjeGeofisica;
    }

    public void setItResistLodoGeofisica(RichInputText itResistLodoGeofisica) {
        this.itResistLodoGeofisica = itResistLodoGeofisica;
    }

    public RichInputText getItResistLodoGeofisica() {
        return itResistLodoGeofisica;
    }

    public void setItTemperaturaLodo(RichInputText itTemperaturaLodo) {
        this.itTemperaturaLodo = itTemperaturaLodo;
    }

    public RichInputText getItTemperaturaLodo() {
        return itTemperaturaLodo;
    }

    public void setItProfundidadRegGeofisica(RichInputText itProfundidadRegGeofisica) {
        this.itProfundidadRegGeofisica = itProfundidadRegGeofisica;
    }

    public RichInputText getItProfundidadRegGeofisica() {
        return itProfundidadRegGeofisica;
    }

    public void setItCalidadRegistroGeofisica(RichInputText itCalidadRegistroGeofisica) {
        this.itCalidadRegistroGeofisica = itCalidadRegistroGeofisica;
    }

    public RichInputText getItCalidadRegistroGeofisica() {
        return itCalidadRegistroGeofisica;
    }

    public void setOtCapasGeofisica(RichOutputText otCapasGeofisica) {
        this.otCapasGeofisica = otCapasGeofisica;
    }

    public RichOutputText getOtCapasGeofisica() {
        return otCapasGeofisica;
    }

    public void setSpacer20(RichSpacer spacer20) {
        this.spacer20 = spacer20;
    }

    public RichSpacer getSpacer20() {
        return spacer20;
    }

    public void setIfDocumentoGeofisica(RichInputFile ifDocumentoGeofisica) {
        this.ifDocumentoGeofisica = ifDocumentoGeofisica;
    }

    public RichInputFile getIfDocumentoGeofisica() {
        return ifDocumentoGeofisica;
    }

    public void setItDiaIntConstruccion(RichInputText itDiaIntConstruccion) {
        this.itDiaIntConstruccion = itDiaIntConstruccion;
    }

    public RichInputText getItDiaIntConstruccion() {
        return itDiaIntConstruccion;
    }

    public void setItDiaExtConstruccion(RichInputText itDiaExtConstruccion) {
        this.itDiaExtConstruccion = itDiaExtConstruccion;
    }

    public RichInputText getItDiaExtConstruccion() {
        return itDiaExtConstruccion;
    }

    public void setItDiaPerfCostruccion(RichInputText itDiaPerfCostruccion) {
        this.itDiaPerfCostruccion = itDiaPerfCostruccion;
    }

    public RichInputText getItDiaPerfCostruccion() {
        return itDiaPerfCostruccion;
    }

    public void setSpacer21(RichSpacer spacer21) {
        this.spacer21 = spacer21;
    }

    public RichSpacer getSpacer21() {
        return spacer21;
    }

    public void setOutputText1(RichOutputText outputText1) {
        this.outputText1 = outputText1;
    }

    public RichOutputText getOutputText1() {
        return outputText1;
    }

    public void setSocMaterialConstruccion(RichSelectOneChoice socMaterialConstruccion) {
        this.socMaterialConstruccion = socMaterialConstruccion;
    }

    public RichSelectOneChoice getSocMaterialConstruccion() {
        return socMaterialConstruccion;
    }

    public void setSiMaterialConstruccion(UISelectItems siMaterialConstruccion) {
        this.siMaterialConstruccion = siMaterialConstruccion;
    }

    public UISelectItems getSiMaterialConstruccion() {
        return siMaterialConstruccion;
    }

    public void setSocMetodoConstruccion(RichSelectOneChoice socMetodoConstruccion) {
        this.socMetodoConstruccion = socMetodoConstruccion;
    }

    public RichSelectOneChoice getSocMetodoConstruccion() {
        return socMetodoConstruccion;
    }

    public void setSiMetodoConstruccion(UISelectItems siMetodoConstruccion) {
        this.siMetodoConstruccion = siMetodoConstruccion;
    }

    public UISelectItems getSiMetodoConstruccion() {
        return siMetodoConstruccion;
    }

    public void setSocTratamientoConstruccion(RichSelectOneChoice socTratamientoConstruccion) {
        this.socTratamientoConstruccion = socTratamientoConstruccion;
    }

    public RichSelectOneChoice getSocTratamientoConstruccion() {
        return socTratamientoConstruccion;
    }

    public void setSiTratamientoConstruccion(UISelectItems siTratamientoConstruccion) {
        this.siTratamientoConstruccion = siTratamientoConstruccion;
    }

    public UISelectItems getSiTratamientoConstruccion() {
        return siTratamientoConstruccion;
    }

    public void setSocFluidoConstruccion(RichSelectOneChoice socFluidoConstruccion) {
        this.socFluidoConstruccion = socFluidoConstruccion;
    }

    public RichSelectOneChoice getSocFluidoConstruccion() {
        return socFluidoConstruccion;
    }

    public void setSiFluidoConstruccion(UISelectItems siFluidoConstruccion) {
        this.siFluidoConstruccion = siFluidoConstruccion;
    }

    public UISelectItems getSiFluidoConstruccion() {
        return siFluidoConstruccion;
    }

    public void setItCompaniaPerforadora(RichInputText itCompaniaPerforadora) {
        this.itCompaniaPerforadora = itCompaniaPerforadora;
    }

    public RichInputText getItCompaniaPerforadora() {
        return itCompaniaPerforadora;
    }

    public void setSbrColmatadaSi(RichSelectBooleanRadio sbrColmatadaSi) {
        this.sbrColmatadaSi = sbrColmatadaSi;
    }

    public RichSelectBooleanRadio getSbrColmatadaSi() {
        return sbrColmatadaSi;
    }

    public void setSbrColmatadaNo(RichSelectBooleanRadio sbrColmatadaNo) {
        this.sbrColmatadaNo = sbrColmatadaNo;
    }

    public RichSelectBooleanRadio getSbrColmatadaNo() {
        return sbrColmatadaNo;
    }

    public void setSbrColapsadaSi(RichSelectBooleanRadio sbrColapsadaSi) {
        this.sbrColapsadaSi = sbrColapsadaSi;
    }

    public RichSelectBooleanRadio getSbrColapsadaSi() {
        return sbrColapsadaSi;
    }

    public void setSbrColapsadaNo(RichSelectBooleanRadio sbrColapsadaNo) {
        this.sbrColapsadaNo = sbrColapsadaNo;
    }

    public RichSelectBooleanRadio getSbrColapsadaNo() {
        return sbrColapsadaNo;
    }

    public void setSpacer22(RichSpacer spacer22) {
        this.spacer22 = spacer22;
    }

    public RichSpacer getSpacer22() {
        return spacer22;
    }

    public void setOutputText2(RichOutputText outputText2) {
        this.outputText2 = outputText2;
    }

    public RichOutputText getOutputText2() {
        return outputText2;
    }

    public void setItProfEntubado(RichInputText itProfEntubado) {
        this.itProfEntubado = itProfEntubado;
    }

    public RichInputText getItProfEntubado() {
        return itProfEntubado;
    }

    public void setItProfPerforacion(RichInputText itProfPerforacion) {
        this.itProfPerforacion = itProfPerforacion;
    }

    public RichInputText getItProfPerforacion() {
        return itProfPerforacion;
    }

    public void setSpacer23(RichSpacer spacer23) {
        this.spacer23 = spacer23;
    }

    public RichSpacer getSpacer23() {
        return spacer23;
    }

    public void setOutputText3(RichOutputText outputText3) {
        this.outputText3 = outputText3;
    }

    public RichOutputText getOutputText3() {
        return outputText3;
    }

    public void setItCantidadGravilla(RichInputText itCantidadGravilla) {
        this.itCantidadGravilla = itCantidadGravilla;
    }

    public RichInputText getItCantidadGravilla() {
        return itCantidadGravilla;
    }

    public void setItNivelGravilla(RichInputText itNivelGravilla) {
        this.itNivelGravilla = itNivelGravilla;
    }

    public RichInputText getItNivelGravilla() {
        return itNivelGravilla;
    }

    public void setItDiametroPromedio(RichInputText itDiametroPromedio) {
        this.itDiametroPromedio = itDiametroPromedio;
    }

    public RichInputText getItDiametroPromedio() {
        return itDiametroPromedio;
    }

    public void setSpacer24(RichSpacer spacer24) {
        this.spacer24 = spacer24;
    }

    public RichSpacer getSpacer24() {
        return spacer24;
    }

    public void setOutputText4(RichOutputText outputText4) {
        this.outputText4 = outputText4;
    }

    public RichOutputText getOutputText4() {
        return outputText4;
    }

    public void setItCapacidadEmbalse(RichInputText itCapacidadEmbalse) {
        this.itCapacidadEmbalse = itCapacidadEmbalse;
    }

    public RichInputText getItCapacidadEmbalse() {
        return itCapacidadEmbalse;
    }

    public void setSbcEmbalse(RichSelectBooleanCheckbox sbcEmbalse) {
        this.sbcEmbalse = sbcEmbalse;
    }

    public RichSelectBooleanCheckbox getSbcEmbalse() {
        return sbcEmbalse;
    }

    public void setSbcTanque(RichSelectBooleanCheckbox sbcTanque) {
        this.sbcTanque = sbcTanque;
    }

    public RichSelectBooleanCheckbox getSbcTanque() {
        return sbcTanque;
    }

    public void setItCapacidadTanque(RichInputText itCapacidadTanque) {
        this.itCapacidadTanque = itCapacidadTanque;
    }

    public RichInputText getItCapacidadTanque() {
        return itCapacidadTanque;
    }

    public void setSbcAlberca(RichSelectBooleanCheckbox sbcAlberca) {
        this.sbcAlberca = sbcAlberca;
    }

    public RichSelectBooleanCheckbox getSbcAlberca() {
        return sbcAlberca;
    }

    public void setItCapacidadAlberca(RichInputText itCapacidadAlberca) {
        this.itCapacidadAlberca = itCapacidadAlberca;
    }

    public RichInputText getItCapacidadAlberca() {
        return itCapacidadAlberca;
    }

    public void setSbcOtro(RichSelectBooleanCheckbox sbcOtro) {
        this.sbcOtro = sbcOtro;
    }

    public RichSelectBooleanCheckbox getSbcOtro() {
        return sbcOtro;
    }

    public void setItCapacidadOtro(RichInputText itCapacidadOtro) {
        this.itCapacidadOtro = itCapacidadOtro;
    }

    public RichInputText getItCapacidadOtro() {
        return itCapacidadOtro;
    }

    public void setSpacer25(RichSpacer spacer25) {
        this.spacer25 = spacer25;
    }

    public RichSpacer getSpacer25() {
        return spacer25;
    }

    public void setOutputText5(RichOutputText outputText5) {
        this.outputText5 = outputText5;
    }

    public RichOutputText getOutputText5() {
        return outputText5;
    }

    public void setIfDocumentoConstruccion(RichInputFile ifDocumentoConstruccion) {
        this.ifDocumentoConstruccion = ifDocumentoConstruccion;
    }

    public RichInputFile getIfDocumentoConstruccion() {
        return ifDocumentoConstruccion;
    }

    public void setSbrLetrinaSi(RichSelectBooleanRadio sbrLetrinaSi) {
        this.sbrLetrinaSi = sbrLetrinaSi;
    }

    public RichSelectBooleanRadio getSbrLetrinaSi() {
        return sbrLetrinaSi;
    }

    public void setSbrLetrinaNo(RichSelectBooleanRadio sbrLetrinaNo) {
        this.sbrLetrinaNo = sbrLetrinaNo;
    }

    public RichSelectBooleanRadio getSbrLetrinaNo() {
        return sbrLetrinaNo;
    }

    public void setItDistanciaLetrina(RichInputText itDistanciaLetrina) {
        this.itDistanciaLetrina = itDistanciaLetrina;
    }

    public RichInputText getItDistanciaLetrina() {
        return itDistanciaLetrina;
    }

    public void setSbrCharcoSi(RichSelectBooleanRadio sbrCharcoSi) {
        this.sbrCharcoSi = sbrCharcoSi;
    }

    public RichSelectBooleanRadio getSbrCharcoSi() {
        return sbrCharcoSi;
    }

    public void setSbrCharcoNo(RichSelectBooleanRadio sbrCharcoNo) {
        this.sbrCharcoNo = sbrCharcoNo;
    }

    public RichSelectBooleanRadio getSbrCharcoNo() {
        return sbrCharcoNo;
    }

    public void setItDistanciaCharco(RichInputText itDistanciaCharco) {
        this.itDistanciaCharco = itDistanciaCharco;
    }

    public RichInputText getItDistanciaCharco() {
        return itDistanciaCharco;
    }

    public void setSbrCriaderoSi(RichSelectBooleanRadio sbrCriaderoSi) {
        this.sbrCriaderoSi = sbrCriaderoSi;
    }

    public RichSelectBooleanRadio getSbrCriaderoSi() {
        return sbrCriaderoSi;
    }

    public void setSbrCriaderoNo(RichSelectBooleanRadio sbrCriaderoNo) {
        this.sbrCriaderoNo = sbrCriaderoNo;
    }

    public RichSelectBooleanRadio getSbrCriaderoNo() {
        return sbrCriaderoNo;
    }

    public void setItDistanciaCriadero(RichInputText itDistanciaCriadero) {
        this.itDistanciaCriadero = itDistanciaCriadero;
    }

    public RichInputText getItDistanciaCriadero() {
        return itDistanciaCriadero;
    }

    public void setSbrFiltracionSi(RichSelectBooleanRadio sbrFiltracionSi) {
        this.sbrFiltracionSi = sbrFiltracionSi;
    }

    public RichSelectBooleanRadio getSbrFiltracionSi() {
        return sbrFiltracionSi;
    }

    public void setSbrFiltracionNo(RichSelectBooleanRadio sbrFiltracionNo) {
        this.sbrFiltracionNo = sbrFiltracionNo;
    }

    public RichSelectBooleanRadio getSbrFiltracionNo() {
        return sbrFiltracionNo;
    }

    public void setItDistanciaFiltracion(RichInputText itDistanciaFiltracion) {
        this.itDistanciaFiltracion = itDistanciaFiltracion;
    }

    public RichInputText getItDistanciaFiltracion() {
        return itDistanciaFiltracion;
    }

    public void setSpacer26(RichSpacer spacer26) {
        this.spacer26 = spacer26;
    }

    public RichSpacer getSpacer26() {
        return spacer26;
    }

    public void setOutputText6(RichOutputText outputText6) {
        this.outputText6 = outputText6;
    }

    public RichOutputText getOutputText6() {
        return outputText6;
    }

    public void setSbrCubiertaSi(RichSelectBooleanRadio sbrCubiertaSi) {
        this.sbrCubiertaSi = sbrCubiertaSi;
    }

    public RichSelectBooleanRadio getSbrCubiertaSi() {
        return sbrCubiertaSi;
    }

    public void setSbrCubiertaNo(RichSelectBooleanRadio sbrCubiertaNo) {
        this.sbrCubiertaNo = sbrCubiertaNo;
    }

    public RichSelectBooleanRadio getSbrCubiertaNo() {
        return sbrCubiertaNo;
    }

    public void setSbrCementoSi(RichSelectBooleanRadio sbrCementoSi) {
        this.sbrCementoSi = sbrCementoSi;
    }

    public RichSelectBooleanRadio getSbrCementoSi() {
        return sbrCementoSi;
    }

    public void setSbrCementoNo(RichSelectBooleanRadio sbrCementoNo) {
        this.sbrCementoNo = sbrCementoNo;
    }

    public RichSelectBooleanRadio getSbrCementoNo() {
        return sbrCementoNo;
    }

    public void setSbrSelloSi(RichSelectBooleanRadio sbrSelloSi) {
        this.sbrSelloSi = sbrSelloSi;
    }

    public RichSelectBooleanRadio getSbrSelloSi() {
        return sbrSelloSi;
    }

    public void setSbrSelloNo(RichSelectBooleanRadio sbrSelloNo) {
        this.sbrSelloNo = sbrSelloNo;
    }

    public RichSelectBooleanRadio getSbrSelloNo() {
        return sbrSelloNo;
    }

    public void setSbrCercoSi(RichSelectBooleanRadio sbrCercoSi) {
        this.sbrCercoSi = sbrCercoSi;
    }

    public RichSelectBooleanRadio getSbrCercoSi() {
        return sbrCercoSi;
    }

    public void setSbrCercoNo(RichSelectBooleanRadio sbrCercoNo) {
        this.sbrCercoNo = sbrCercoNo;
    }

    public RichSelectBooleanRadio getSbrCercoNo() {
        return sbrCercoNo;
    }

    public void setSpacer27(RichSpacer spacer27) {
        this.spacer27 = spacer27;
    }

    public RichSpacer getSpacer27() {
        return spacer27;
    }

    public void setOutputText7(RichOutputText outputText7) {
        this.outputText7 = outputText7;
    }

    public RichOutputText getOutputText7() {
        return outputText7;
    }

    public void setSbrCampoInfiltracionSi(RichSelectBooleanRadio sbrCampoInfiltracionSi) {
        this.sbrCampoInfiltracionSi = sbrCampoInfiltracionSi;
    }

    public RichSelectBooleanRadio getSbrCampoInfiltracionSi() {
        return sbrCampoInfiltracionSi;
    }

    public void setSbrCampoInfiltracionNo(RichSelectBooleanRadio sbrCampoInfiltracionNo) {
        this.sbrCampoInfiltracionNo = sbrCampoInfiltracionNo;
    }

    public RichSelectBooleanRadio getSbrCampoInfiltracionNo() {
        return sbrCampoInfiltracionNo;
    }

    public void setItDistanciaCampoInfiltracion(RichInputText itDistanciaCampoInfiltracion) {
        this.itDistanciaCampoInfiltracion = itDistanciaCampoInfiltracion;
    }

    public RichInputText getItDistanciaCampoInfiltracion() {
        return itDistanciaCampoInfiltracion;
    }

    public void setSbrCementerioSi(RichSelectBooleanRadio sbrCementerioSi) {
        this.sbrCementerioSi = sbrCementerioSi;
    }

    public RichSelectBooleanRadio getSbrCementerioSi() {
        return sbrCementerioSi;
    }

    public void setSbrCementerioNo(RichSelectBooleanRadio sbrCementerioNo) {
        this.sbrCementerioNo = sbrCementerioNo;
    }

    public RichSelectBooleanRadio getSbrCementerioNo() {
        return sbrCementerioNo;
    }

    public void setItDistanciaCementerio(RichInputText itDistanciaCementerio) {
        this.itDistanciaCementerio = itDistanciaCementerio;
    }

    public RichInputText getItDistanciaCementerio() {
        return itDistanciaCementerio;
    }

    public void setSbrEstacionServicioSi(RichSelectBooleanRadio sbrEstacionServicioSi) {
        this.sbrEstacionServicioSi = sbrEstacionServicioSi;
    }

    public RichSelectBooleanRadio getSbrEstacionServicioSi() {
        return sbrEstacionServicioSi;
    }

    public void setSbrEstacionServicioNo(RichSelectBooleanRadio sbrEstacionServicioNo) {
        this.sbrEstacionServicioNo = sbrEstacionServicioNo;
    }

    public RichSelectBooleanRadio getSbrEstacionServicioNo() {
        return sbrEstacionServicioNo;
    }

    public void setItDistanciaEstacion(RichInputText itDistanciaEstacion) {
        this.itDistanciaEstacion = itDistanciaEstacion;
    }

    public RichInputText getItDistanciaEstacion() {
        return itDistanciaEstacion;
    }

    public void setSbrLagunasOxidacionSi(RichSelectBooleanRadio sbrLagunasOxidacionSi) {
        this.sbrLagunasOxidacionSi = sbrLagunasOxidacionSi;
    }

    public RichSelectBooleanRadio getSbrLagunasOxidacionSi() {
        return sbrLagunasOxidacionSi;
    }

    public void setSbrLagunaOxidacionNo(RichSelectBooleanRadio sbrLagunaOxidacionNo) {
        this.sbrLagunaOxidacionNo = sbrLagunaOxidacionNo;
    }

    public RichSelectBooleanRadio getSbrLagunaOxidacionNo() {
        return sbrLagunaOxidacionNo;
    }

    public void setItDistanciaLaguna(RichInputText itDistanciaLaguna) {
        this.itDistanciaLaguna = itDistanciaLaguna;
    }

    public RichInputText getItDistanciaLaguna() {
        return itDistanciaLaguna;
    }

    public void setSbrLavaderosSi(RichSelectBooleanRadio sbrLavaderosSi) {
        this.sbrLavaderosSi = sbrLavaderosSi;
    }

    public RichSelectBooleanRadio getSbrLavaderosSi() {
        return sbrLavaderosSi;
    }

    public void setSbrLavaderosNo(RichSelectBooleanRadio sbrLavaderosNo) {
        this.sbrLavaderosNo = sbrLavaderosNo;
    }

    public RichSelectBooleanRadio getSbrLavaderosNo() {
        return sbrLavaderosNo;
    }

    public void setItDistanciaLavaderos(RichInputText itDistanciaLavaderos) {
        this.itDistanciaLavaderos = itDistanciaLavaderos;
    }

    public RichInputText getItDistanciaLavaderos() {
        return itDistanciaLavaderos;
    }

    public void setSbrSacrificioSi(RichSelectBooleanRadio sbrSacrificioSi) {
        this.sbrSacrificioSi = sbrSacrificioSi;
    }

    public RichSelectBooleanRadio getSbrSacrificioSi() {
        return sbrSacrificioSi;
    }

    public void setSbrSacrificioNo(RichSelectBooleanRadio sbrSacrificioNo) {
        this.sbrSacrificioNo = sbrSacrificioNo;
    }

    public RichSelectBooleanRadio getSbrSacrificioNo() {
        return sbrSacrificioNo;
    }

    public void setItSacrificioDistancia(RichInputText itSacrificioDistancia) {
        this.itSacrificioDistancia = itSacrificioDistancia;
    }

    public RichInputText getItSacrificioDistancia() {
        return itSacrificioDistancia;
    }

    public void setSbrPozoSi(RichSelectBooleanRadio sbrPozoSi) {
        this.sbrPozoSi = sbrPozoSi;
    }

    public RichSelectBooleanRadio getSbrPozoSi() {
        return sbrPozoSi;
    }

    public void setSbrPozoNo(RichSelectBooleanRadio sbrPozoNo) {
        this.sbrPozoNo = sbrPozoNo;
    }

    public RichSelectBooleanRadio getSbrPozoNo() {
        return sbrPozoNo;
    }

    public void setItPozoDistancia(RichInputText itPozoDistancia) {
        this.itPozoDistancia = itPozoDistancia;
    }

    public RichInputText getItPozoDistancia() {
        return itPozoDistancia;
    }

    public void setSpacer28(RichSpacer spacer28) {
        this.spacer28 = spacer28;
    }

    public RichSpacer getSpacer28() {
        return spacer28;
    }

    public void setOutputText8(RichOutputText outputText8) {
        this.outputText8 = outputText8;
    }

    public RichOutputText getOutputText8() {
        return outputText8;
    }

    public void setSbrResiduoAgricolaSi(RichSelectBooleanRadio sbrResiduoAgricolaSi) {
        this.sbrResiduoAgricolaSi = sbrResiduoAgricolaSi;
    }

    public RichSelectBooleanRadio getSbrResiduoAgricolaSi() {
        return sbrResiduoAgricolaSi;
    }

    public void setSbrResiduoAgricolaNo(RichSelectBooleanRadio sbrResiduoAgricolaNo) {
        this.sbrResiduoAgricolaNo = sbrResiduoAgricolaNo;
    }

    public RichSelectBooleanRadio getSbrResiduoAgricolaNo() {
        return sbrResiduoAgricolaNo;
    }

    public void setSbrResiduoDomesticoSi(RichSelectBooleanRadio sbrResiduoDomesticoSi) {
        this.sbrResiduoDomesticoSi = sbrResiduoDomesticoSi;
    }

    public RichSelectBooleanRadio getSbrResiduoDomesticoSi() {
        return sbrResiduoDomesticoSi;
    }

    public void setSbrResiduoDomesticoNo(RichSelectBooleanRadio sbrResiduoDomesticoNo) {
        this.sbrResiduoDomesticoNo = sbrResiduoDomesticoNo;
    }

    public RichSelectBooleanRadio getSbrResiduoDomesticoNo() {
        return sbrResiduoDomesticoNo;
    }

    public void setSbrResiduoGanaderiaSi(RichSelectBooleanRadio sbrResiduoGanaderiaSi) {
        this.sbrResiduoGanaderiaSi = sbrResiduoGanaderiaSi;
    }

    public RichSelectBooleanRadio getSbrResiduoGanaderiaSi() {
        return sbrResiduoGanaderiaSi;
    }

    public void setSbrResiduoGanaderiaNo(RichSelectBooleanRadio sbrResiduoGanaderiaNo) {
        this.sbrResiduoGanaderiaNo = sbrResiduoGanaderiaNo;
    }

    public RichSelectBooleanRadio getSbrResiduoGanaderiaNo() {
        return sbrResiduoGanaderiaNo;
    }

    public void setSbrResiduoHospitalarioSi(RichSelectBooleanRadio sbrResiduoHospitalarioSi) {
        this.sbrResiduoHospitalarioSi = sbrResiduoHospitalarioSi;
    }

    public RichSelectBooleanRadio getSbrResiduoHospitalarioSi() {
        return sbrResiduoHospitalarioSi;
    }

    public void setSbrResiduoHospitalarioNo(RichSelectBooleanRadio sbrResiduoHospitalarioNo) {
        this.sbrResiduoHospitalarioNo = sbrResiduoHospitalarioNo;
    }

    public RichSelectBooleanRadio getSbrResiduoHospitalarioNo() {
        return sbrResiduoHospitalarioNo;
    }

    public void setSbrResiduoIndustrialSi(RichSelectBooleanRadio sbrResiduoIndustrialSi) {
        this.sbrResiduoIndustrialSi = sbrResiduoIndustrialSi;
    }

    public RichSelectBooleanRadio getSbrResiduoIndustrialSi() {
        return sbrResiduoIndustrialSi;
    }

    public void setSbrResiduoIndustrialNo(RichSelectBooleanRadio sbrResiduoIndustrialNo) {
        this.sbrResiduoIndustrialNo = sbrResiduoIndustrialNo;
    }

    public RichSelectBooleanRadio getSbrResiduoIndustrialNo() {
        return sbrResiduoIndustrialNo;
    }

    public void setSbrResiduoMineroSi(RichSelectBooleanRadio sbrResiduoMineroSi) {
        this.sbrResiduoMineroSi = sbrResiduoMineroSi;
    }

    public RichSelectBooleanRadio getSbrResiduoMineroSi() {
        return sbrResiduoMineroSi;
    }

    public void setSbrResiduoMineroNo(RichSelectBooleanRadio sbrResiduoMineroNo) {
        this.sbrResiduoMineroNo = sbrResiduoMineroNo;
    }

    public RichSelectBooleanRadio getSbrResiduoMineroNo() {
        return sbrResiduoMineroNo;
    }

    public void setSpacer29(RichSpacer spacer29) {
        this.spacer29 = spacer29;
    }

    public RichSpacer getSpacer29() {
        return spacer29;
    }

    public void setOutputText9(RichOutputText outputText9) {
        this.outputText9 = outputText9;
    }

    public RichOutputText getOutputText9() {
        return outputText9;
    }

    public void setSbrBotaderoCieloAbiertoSi(RichSelectBooleanRadio sbrBotaderoCieloAbiertoSi) {
        this.sbrBotaderoCieloAbiertoSi = sbrBotaderoCieloAbiertoSi;
    }

    public RichSelectBooleanRadio getSbrBotaderoCieloAbiertoSi() {
        return sbrBotaderoCieloAbiertoSi;
    }

    public void setSbrBotaderoCieloAbiertoNo(RichSelectBooleanRadio sbrBotaderoCieloAbiertoNo) {
        this.sbrBotaderoCieloAbiertoNo = sbrBotaderoCieloAbiertoNo;
    }

    public RichSelectBooleanRadio getSbrBotaderoCieloAbiertoNo() {
        return sbrBotaderoCieloAbiertoNo;
    }

    public void setSbrCompostajeSi(RichSelectBooleanRadio sbrCompostajeSi) {
        this.sbrCompostajeSi = sbrCompostajeSi;
    }

    public RichSelectBooleanRadio getSbrCompostajeSi() {
        return sbrCompostajeSi;
    }

    public void setSbrCompostajeNo(RichSelectBooleanRadio sbrCompostajeNo) {
        this.sbrCompostajeNo = sbrCompostajeNo;
    }

    public RichSelectBooleanRadio getSbrCompostajeNo() {
        return sbrCompostajeNo;
    }

    public void setSbrIncineracionSi(RichSelectBooleanRadio sbrIncineracionSi) {
        this.sbrIncineracionSi = sbrIncineracionSi;
    }

    public RichSelectBooleanRadio getSbrIncineracionSi() {
        return sbrIncineracionSi;
    }

    public void setSbrIncineracionNo(RichSelectBooleanRadio sbrIncineracionNo) {
        this.sbrIncineracionNo = sbrIncineracionNo;
    }

    public RichSelectBooleanRadio getSbrIncineracionNo() {
        return sbrIncineracionNo;
    }

    public void setSbrReciclajeSi(RichSelectBooleanRadio sbrReciclajeSi) {
        this.sbrReciclajeSi = sbrReciclajeSi;
    }

    public RichSelectBooleanRadio getSbrReciclajeSi() {
        return sbrReciclajeSi;
    }

    public void setSbrReciclajeNo(RichSelectBooleanRadio sbrReciclajeNo) {
        this.sbrReciclajeNo = sbrReciclajeNo;
    }

    public RichSelectBooleanRadio getSbrReciclajeNo() {
        return sbrReciclajeNo;
    }


    public void setS100(RichSpacer s100) {
        this.s100 = s100;
    }

    public RichSpacer getS100() {
        return s100;
    }

    public void setS101(RichSpacer s101) {
        this.s101 = s101;
    }

    public RichSpacer getS101() {
        return s101;
    }

    public void setS102(RichSpacer s102) {
        this.s102 = s102;
    }

    public RichSpacer getS102() {
        return s102;
    }

    public void setS103(RichSpacer s103) {
        this.s103 = s103;
    }

    public RichSpacer getS103() {
        return s103;
    }

    public void setS104(RichSpacer s104) {
        this.s104 = s104;
    }

    public RichSpacer getS104() {
        return s104;
    }

    public void setS105(RichSpacer s105) {
        this.s105 = s105;
    }

    public RichSpacer getS105() {
        return s105;
    }

    public void setPgl_archivo1(RichPanelGroupLayout pgl_archivo1) {
        this.pgl_archivo1 = pgl_archivo1;
    }

    public RichPanelGroupLayout getPgl_archivo1() {
        return pgl_archivo1;
    }

    public void setCl_documento1(RichCommandLink cl_documento1) {
        this.cl_documento1 = cl_documento1;
    }

    public RichCommandLink getCl_documento1() {
        return cl_documento1;
    }

    public void setCb_borrar_docto1(RichCommandButton cb_borrar_docto1) {
        this.cb_borrar_docto1 = cb_borrar_docto1;
    }

    public RichCommandButton getCb_borrar_docto1() {
        return cb_borrar_docto1;
    }

    public void setPgl_archivo2(RichPanelGroupLayout pgl_archivo2) {
        this.pgl_archivo2 = pgl_archivo2;
    }

    public RichPanelGroupLayout getPgl_archivo2() {
        return pgl_archivo2;
    }

    public void setCb_borrar_docto2(RichCommandButton cb_borrar_docto2) {
        this.cb_borrar_docto2 = cb_borrar_docto2;
    }

    public RichCommandButton getCb_borrar_docto2() {
        return cb_borrar_docto2;
    }

    public void setCl_documento2(RichCommandLink cl_documento2) {
        this.cl_documento2 = cl_documento2;
    }

    public RichCommandLink getCl_documento2() {
        return cl_documento2;
    }

    public void setPgl_archivo3(RichPanelGroupLayout pgl_archivo3) {
        this.pgl_archivo3 = pgl_archivo3;
    }

    public RichPanelGroupLayout getPgl_archivo3() {
        return pgl_archivo3;
    }

    public void setCl_documento3(RichCommandLink cl_documento3) {
        this.cl_documento3 = cl_documento3;
    }

    public RichCommandLink getCl_documento3() {
        return cl_documento3;
    }

    public void setCb_borrar_docto3(RichCommandButton cb_borrar_docto3) {
        this.cb_borrar_docto3 = cb_borrar_docto3;
    }

    public RichCommandButton getCb_borrar_docto3() {
        return cb_borrar_docto3;
    }

    public void setP_borrar_docto(RichPopup p_borrar_docto) {
        this.p_borrar_docto = p_borrar_docto;
    }

    public RichPopup getP_borrar_docto() {
        return p_borrar_docto;
    }

    public void setD_borrar_docto(RichDialog d_borrar_docto) {
        this.d_borrar_docto = d_borrar_docto;
    }

    public RichDialog getD_borrar_docto() {
        return d_borrar_docto;
    }

    public void setOt100(RichOutputText ot100) {
        this.ot100 = ot100;
    }

    public RichOutputText getOt100() {
        return ot100;
    }


    public void setPanelGroupLayout8(RichPanelGroupLayout panelGroupLayout8) {
        this.panelGroupLayout8 = panelGroupLayout8;
    }

    public RichPanelGroupLayout getPanelGroupLayout8() {
        return panelGroupLayout8;
    }

    public void setSpacer50(RichSpacer spacer50) {
        this.spacer50 = spacer50;
    }

    public RichSpacer getSpacer50() {
        return spacer50;
    }

    public void setCommandLink1(RichCommandLink commandLink1) {
        this.commandLink1 = commandLink1;
    }

    public RichCommandLink getCommandLink1() {
        return commandLink1;
    }

    public void setSpacer51(RichSpacer spacer51) {
        this.spacer51 = spacer51;
    }

    public RichSpacer getSpacer51() {
        return spacer51;
    }

    public void setCommandLink2(RichCommandLink commandLink2) {
        this.commandLink2 = commandLink2;
    }

    public RichCommandLink getCommandLink2() {
        return commandLink2;
    }

    public void setSpacer52(RichSpacer spacer52) {
        this.spacer52 = spacer52;
    }

    public RichSpacer getSpacer52() {
        return spacer52;
    }

    public void setCommandLink3(RichCommandLink commandLink3) {
        this.commandLink3 = commandLink3;
    }

    public RichCommandLink getCommandLink3() {
        return commandLink3;
    }

    public void setSpacer53(RichSpacer spacer53) {
        this.spacer53 = spacer53;
    }

    public RichSpacer getSpacer53() {
        return spacer53;
    }

    public void setCommandLink4(RichCommandLink commandLink4) {
        this.commandLink4 = commandLink4;
    }

    public RichCommandLink getCommandLink4() {
        return commandLink4;
    }

    public void setSpacer54(RichSpacer spacer54) {
        this.spacer54 = spacer54;
    }

    public RichSpacer getSpacer54() {
        return spacer54;
    }

    public void setCommandLink5(RichCommandLink commandLink5) {
        this.commandLink5 = commandLink5;
    }

    public RichCommandLink getCommandLink5() {
        return commandLink5;
    }

    public void setSpacer55(RichSpacer spacer55) {
        this.spacer55 = spacer55;
    }

    public RichSpacer getSpacer55() {
        return spacer55;
    }

    public void setCommandLink6(RichCommandLink commandLink6) {
        this.commandLink6 = commandLink6;
    }

    public RichCommandLink getCommandLink6() {
        return commandLink6;
    }

    public void setSpacer56(RichSpacer spacer56) {
        this.spacer56 = spacer56;
    }

    public RichSpacer getSpacer56() {
        return spacer56;
    }

    public void setCommandLink7(RichCommandLink commandLink7) {
        this.commandLink7 = commandLink7;
    }

    public RichCommandLink getCommandLink7() {
        return commandLink7;
    }

    public void setSpacer57(RichSpacer spacer57) {
        this.spacer57 = spacer57;
    }

    public RichSpacer getSpacer57() {
        return spacer57;
    }

    public void setOutputText26(RichOutputText outputText26) {
        this.outputText26 = outputText26;
    }

    public RichOutputText getOutputText26() {
        return outputText26;
    }


    public void setPanelGroupLayout1(RichPanelGroupLayout panelGroupLayout1) {
        this.panelGroupLayout1 = panelGroupLayout1;
    }

    public RichPanelGroupLayout getPanelGroupLayout1() {
        return panelGroupLayout1;
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

    public void setD_borrar(RichDialog d_borrar) {
        this.d_borrar = d_borrar;
    }

    public RichDialog getD_borrar() {
        return d_borrar;
    }

    public void setD_registro_exitoso(RichDialog d_registro_exitoso) {
        this.d_registro_exitoso = d_registro_exitoso;
    }

    public RichDialog getD_registro_exitoso() {
        return d_registro_exitoso;
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

    public void setCb_si_borrar(RichCommandButton cb_si_borrar) {
        this.cb_si_borrar = cb_si_borrar;
    }

    public RichCommandButton getCb_si_borrar() {
        return cb_si_borrar;
    }

    public void setSpacer1(RichSpacer spacer1) {
        this.spacer1 = spacer1;
    }

    public RichSpacer getSpacer1() {
        return spacer1;
    }

    public void setCb_no_borrar(RichCommandButton cb_no_borrar) {
        this.cb_no_borrar = cb_no_borrar;
    }

    public RichCommandButton getCb_no_borrar() {
        return cb_no_borrar;
    }

    public void setOt_borrar_1(RichOutputText ot_borrar_1) {
        this.ot_borrar_1 = ot_borrar_1;
    }

    public RichOutputText getOt_borrar_1() {
        return ot_borrar_1;
    }

    public void setSpacer2(RichSpacer spacer2) {
        this.spacer2 = spacer2;
    }

    public RichSpacer getSpacer2() {
        return spacer2;
    }

    public void setOt_borrar_2(RichOutputText ot_borrar_2) {
        this.ot_borrar_2 = ot_borrar_2;
    }

    public RichOutputText getOt_borrar_2() {
        return ot_borrar_2;
    }

    public void setCbAceptarDi(RichCommandButton cbAceptarDi) {
        this.cbAceptarDi = cbAceptarDi;
    }

    public RichCommandButton getCbAceptarDi() {
        return cbAceptarDi;
    }

    public void setPanelStretchLayout3(RichPanelStretchLayout panelStretchLayout3) {
        this.panelStretchLayout3 = panelStretchLayout3;
    }

    public RichPanelStretchLayout getPanelStretchLayout3() {
        return panelStretchLayout3;
    }

    public void setI1(RichImage i1) {
        this.i1 = i1;
    }

    public RichImage getI1() {
        return i1;
    }

    public void setOt8(RichOutputText ot8) {
        this.ot8 = ot8;
    }

    public RichOutputText getOt8() {
        return ot8;
    }

    public void setDecorativeBox6(RichDecorativeBox decorativeBox6) {
        this.decorativeBox6 = decorativeBox6;
    }

    public RichDecorativeBox getDecorativeBox6() {
        return decorativeBox6;
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

    public void setCbAceptar(RichCommandButton cbAceptar) {
        this.cbAceptar = cbAceptar;
    }

    public RichCommandButton getCbAceptar() {
        return cbAceptar;
    }

    public void setSpacer43(RichSpacer spacer43) {
        this.spacer43 = spacer43;
    }

    public RichSpacer getSpacer43() {
        return spacer43;
    }

    public void setCbBorrar(RichCommandButton cbBorrar) {
        this.cbBorrar = cbBorrar;
    }

    public RichCommandButton getCbBorrar() {
        return cbBorrar;
    }

    public void setPanelGroupLayout5(RichPanelGroupLayout panelGroupLayout5) {
        this.panelGroupLayout5 = panelGroupLayout5;
    }

    public RichPanelGroupLayout getPanelGroupLayout5() {
        return panelGroupLayout5;
    }


    public void setSpacer5(RichSpacer spacer5) {
        this.spacer5 = spacer5;
    }

    public RichSpacer getSpacer5() {
        return spacer5;
    }

    public void setSpacer6(RichSpacer spacer6) {
        this.spacer6 = spacer6;
    }

    public RichSpacer getSpacer6() {
        return spacer6;
    }

    public void setSpacer7(RichSpacer spacer7) {
        this.spacer7 = spacer7;
    }

    public RichSpacer getSpacer7() {
        return spacer7;
    }


    public void setSpacer9(RichSpacer spacer9) {
        this.spacer9 = spacer9;
    }

    public RichSpacer getSpacer9() {
        return spacer9;
    }

    public void setOutputText10(RichOutputText outputText10) {
        this.outputText10 = outputText10;
    }

    public RichOutputText getOutputText10() {
        return outputText10;
    }


    public void setSpacer10(RichSpacer spacer10) {
        this.spacer10 = spacer10;
    }

    public RichSpacer getSpacer10() {
        return spacer10;
    }


    public void setCl_documento4(RichCommandLink commandLink8) {
        this.cl_documento4 = commandLink8;
    }

    public RichCommandLink getCl_documento4() {
        return cl_documento4;
    }


    public void setSpacer12(RichSpacer spacer12) {
        this.spacer12 = spacer12;
    }

    public RichSpacer getSpacer12() {
        return spacer12;
    }

    public void setCb_borrar_docto4(RichCommandButton commandButton1) {
        this.cb_borrar_docto4 = commandButton1;
    }

    public RichCommandButton getCb_borrar_docto4() {
        return cb_borrar_docto4;
    }

    public void setArchivosCargados(List<ArchivoCargadoTO> archivosCargados) {
        this.archivosCargados = archivosCargados;
    }

    public List<ArchivoCargadoTO> getArchivosCargados() {
        return archivosCargados;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public void setPcol1(RichPanelCollection pcol1) {
        this.pcol1 = pcol1;
    }

    public RichPanelCollection getPcol1() {
        return pcol1;
    }

    public void setTable1(RichTable table1) {
        this.table1 = table1;
    }

    public RichTable getTable1() {
        return table1;
    }

    public void setMenu1(RichMenu menu1) {
        this.menu1 = menu1;
    }

    public RichMenu getMenu1() {
        return menu1;
    }

    public void setCmiVer(RichCommandMenuItem commandMenuItem1) {
        this.cmiVer = commandMenuItem1;
    }

    public RichCommandMenuItem getCmiVer() {
        return cmiVer;
    }

    public void setCmiBorrar(RichCommandMenuItem commandMenuItem2) {
        this.cmiBorrar = commandMenuItem2;
    }

    public RichCommandMenuItem getCmiBorrar() {
        return cmiBorrar;
    }

    public void setArchivosRelacionados(boolean archivosRelacionados) {
        this.archivosRelacionados = archivosRelacionados;
    }

    public boolean isArchivosRelacionados() {
        return archivosRelacionados;
    }

    public void setSpacer31(RichSpacer spacer31) {
        this.spacer31 = spacer31;
    }

    public RichSpacer getSpacer31() {
        return spacer31;
    }

    public void setSpacer32(RichSpacer spacer32) {
        this.spacer32 = spacer32;
    }

    public RichSpacer getSpacer32() {
        return spacer32;
    }

    

    public void setListaArchivosRelacionados(List listaArchivosRelacionados) {
        this.listaArchivosRelacionados = listaArchivosRelacionados;
    }

    public List getListaArchivosRelacionados() {
        this.listaArchivosRelacionados = null;
        if(this.funias.getSubttFuniasDocumentosList()!=null){
            this.listaArchivosRelacionados = new ArrayList();
            for(SubttFuniasDocumentos documento : this.funias.getSubttFuniasDocumentosList()){
                if(!this.listaArchivosRelacionados.contains(documento)){
                    System.out.println(":::::::::::::DOCUMENTO ADICIONADO: "+documento);
                    this.listaArchivosRelacionados.add(documento);
                }
            }
        }
        return listaArchivosRelacionados;
    }


}
