package co.gov.ideam.sirh.usuarios.agua.view;

import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.usuarios.agua.model.Captacion;
import co.gov.ideam.sirh.usuarios.agua.model.SubttFunias;
import co.gov.ideam.sirh.usuarios.agua.model.SubttFuniasDocumentos;
import co.gov.ideam.sirh.util.Constantes;
import co.gov.ideam.sirh.util.ConstantesCaptaciones;
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

import javax.faces.application.FacesMessage;
import javax.faces.component.UISelectItems;

import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputFile;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanRadio;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.layout.RichShowDetail;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.output.RichImage;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;

import org.apache.myfaces.trinidad.model.UploadedFile;

public class AdicionarFuniasBean extends StandarBean{
    
    private String actionView;
    //objeto padre.
    private Captacion captacion;
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
    
    private UploadedFile uploadedFile;
    private ArchivoCargadoTO archivoCargado;
    
    private List<ArchivoCargadoTO> archivosCargados;
    
    private RichForm form1;
    private RichDocument document1;
    private RichPanelStretchLayout panelStretchLayout1;
    private RichPanelGroupLayout panelGroupLayout1;
    private RichPanelBox pnlTipoFunias;
    private RichPanelStretchLayout panelStretchLayout2;
    private RichPanelGroupLayout panelGroupLayout2;
    private RichPanelGroupLayout panelGroupLayout3;
    private RichCommandButton cmbNextTipoFunias;
    private RichSpacer spacer1;
    private RichPanelGroupLayout panelGroupLayout4;
    private RichPanelFormLayout panelFormLayout1;
    private RichSelectOneChoice socTipoFunias;
    private UISelectItems siTipoFunias;
    private RichPanelBox pnlGeologia;
    private RichPanelBox pnlDatosConstruccion;
    private RichPanelBox pnlGeofisica;
    private RichPanelBox pnlDatosDiagnostico;
    private RichPanelStretchLayout panelStretchLayout3;
    private RichPanelStretchLayout panelStretchLayout6;
    private RichPanelStretchLayout panelStretchLayout12;
    private RichPanelStretchLayout panelStretchLayout13;
    private RichPanelStretchLayout panelStretchLayout14;
    private RichPanelGroupLayout panelGroupLayout5;
    private RichPanelGroupLayout panelGroupLayout6;
    private RichPanelGroupLayout panelGroupLayout11;
    private RichPanelGroupLayout panelGroupLayout12;
    private RichPanelGroupLayout panelGroupLayout23;
    private RichPanelGroupLayout panelGroupLayout24;
    private RichPanelGroupLayout panelGroupLayout27;
    private RichPanelGroupLayout panelGroupLayout28;
    private RichCommandButton cmbNextGeologia;
    private RichCommandButton cmbDatosConstruccion;
    private RichSpacer spacer2;
    private RichSpacer spacer5;
    private RichCommandButton cmbGeofisica;
    private RichSpacer spacer11;
    private RichCommandButton cmbDatosDiagnostico;
    private RichSpacer spacer13;
    private RichPanelGroupLayout panelGroupLayout31;
    private RichPanelFormLayout panelFormLayout2;
    private RichPanelGroupLayout panelGroupLayout34;
    private RichPanelFormLayout panelFormLayout5;
    private RichPanelGroupLayout panelGroupLayout40;
    private RichPanelFormLayout panelFormLayout11;
    private RichPanelGroupLayout panelGroupLayout42;
    private RichPanelFormLayout panelFormLayout13;
    private RichPopup p_registro_exitoso;
    private RichDialog d_registro_exitoso;
    private RichPanelGroupLayout panelGroupLayout44;
    private RichCommandButton cbAceptar;
    private RichPanelStretchLayout panelStretchLayout16;
    private RichImage image1;
    private RichOutputText ot8;
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
    private RichOutputText outputText12;


    public AdicionarFuniasBean(){
        FacesUtils.setActiveBean("adicionarFuniasBean",
                                 "Adicionar Funias", 
                                 UsuariosAguaDelegate.class);
        
        //FacesUtils.removeManagedBeanFromSession("adicionarFuenteBean");
        this.load();
    }
    
    public void load(){
        try{
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
            Object objCaptacion = FacesUtils.getFromSession("captacionSeleccionada");
            if(objCaptacion instanceof Captacion){
                this.captacion = (Captacion)objCaptacion;
            }else{                
                Long codigo = (Long)FacesUtils.getFromSession("captacionSeleccionada");                                            
                this.captacion = uad.getCaptacion(codigo);                
            }
            
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
            
        }catch(IdeamException e){
            showMessage(e);
        }
    }
    
    
    /////////////////
    
    public void cmbNextTipoFunias_actionListener(ActionEvent actionEvent) {
        this.actionView = "";
        boolean continuar = true;
        try{
            if(this.socTipoFunias.getValue()!=null){
                UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
                tipoFunias = ((Lista)this.socTipoFunias.getValue()).getValor();

                if( ((Lista)this.socTipoFunias.getValue()).getCodigo().longValue()==
                        ConstantesParametros.LISTA_GEOLOGIA_GEOMORFOLOGIA){
                    
                    SubttFunias existeFunias = 
                        uad.getFuniasByCaptacionTipoFunias(this.captacion.getCodigo(),
                                                       ConstantesParametros.LISTA_GEOLOGIA_GEOMORFOLOGIA.intValue());
                    if(existeFunias != null){
                        showMessage("Ya existe un funias del mismo tipo.");
                        continuar = false;
                        return;
                    }
                    
                    this.pnlTipoFunias.setVisible(false);
                    AdfFacesContext.getCurrentInstance().addPartialTarget(this.pnlTipoFunias);
                    this.pnlGeologia.setVisible(true);
                    AdfFacesContext.getCurrentInstance().addPartialTarget(this.pnlGeologia);
                }else if( ((Lista)this.socTipoFunias.getValue()).getCodigo().longValue()==
                        ConstantesParametros.LISTA_CONSTRUCCION_DISENO){
                    
                    SubttFunias existeFunias = 
                        uad.getFuniasByCaptacionTipoFunias(this.captacion.getCodigo(),
                                                       ConstantesParametros.LISTA_CONSTRUCCION_DISENO.intValue());
                    if(existeFunias != null){
                        showMessage("Ya existe un funias del mismo tipo.");
                        continuar = false;
                        return;
                    }
                    
                    this.pnlTipoFunias.setVisible(false);
                    AdfFacesContext.getCurrentInstance().addPartialTarget(this.pnlTipoFunias);
                    this.pnlDatosConstruccion.setVisible(true);
                    AdfFacesContext.getCurrentInstance().addPartialTarget(this.pnlDatosConstruccion);
                }else if( ((Lista)this.socTipoFunias.getValue()).getCodigo().longValue()==
                        ConstantesParametros.LISTA_GEOFISICA){
                    
                    SubttFunias existeFunias = 
                        uad.getFuniasByCaptacionTipoFunias(this.captacion.getCodigo(),
                                                       ConstantesParametros.LISTA_GEOFISICA.intValue());
                    if(existeFunias != null){
                        showMessage("Ya existe un funias del mismo tipo.");
                        continuar = false;
                        return;
                    }
                    
                    this.pnlTipoFunias.setVisible(false);
                    AdfFacesContext.getCurrentInstance().addPartialTarget(this.pnlTipoFunias);
                    this.pnlGeofisica.setVisible(true);
                    AdfFacesContext.getCurrentInstance().addPartialTarget(this.pnlGeofisica);
                }else if( ((Lista)this.socTipoFunias.getValue()).getCodigo().longValue()==
                        ConstantesParametros.LISTA_EXPLOTACION){
                    
                    SubttFunias existeFunias = 
                        uad.getFuniasByCaptacionTipoFunias(this.captacion.getCodigo(),
                                                       ConstantesParametros.LISTA_EXPLOTACION.intValue());
                    if(existeFunias != null){
                        showMessage("Ya existe un funias del mismo tipo.");
                        continuar = false;
                        return;
                    }

                    //this.actionView="adicionarExplotacion";
                    FacesUtils.sendRedirect("detalleFuniasExplotacion.jspx");
                    
                }else if( ((Lista)this.socTipoFunias.getValue()).getCodigo().longValue()==
                        ConstantesParametros.LISTA_DIAGNOSTICO_SANITARIO){
                    
                    SubttFunias existeFunias = 
                        uad.getFuniasByCaptacionTipoFunias(this.captacion.getCodigo(),
                                                       ConstantesParametros.LISTA_DIAGNOSTICO_SANITARIO.intValue());
                    if(existeFunias != null){
                        showMessage("Ya existe un funias del mismo tipo.");
                        continuar = false;
                        return;
                    }
                    
                    this.pnlTipoFunias.setVisible(false);
                    AdfFacesContext.getCurrentInstance().addPartialTarget(this.pnlTipoFunias);
                    this.pnlDatosDiagnostico.setVisible(true);
                    AdfFacesContext.getCurrentInstance().addPartialTarget(this.pnlDatosDiagnostico);
                }else if(((Lista)this.socTipoFunias.getValue()).getCodigo().longValue()==
                        ConstantesParametros.LISTA_NIVEL){
                
                    SubttFunias existeFunias = 
                        uad.getFuniasByCaptacionTipoFunias(this.captacion.getCodigo(),
                                                       ConstantesParametros.LISTA_NIVEL.intValue());
                    if(existeFunias != null){
                        showMessage("Ya existe un funias del mismo tipo.");
                        continuar = false;
                        return;
                    }
                
                    //debe guardar el funias y enviar a la pagina de niveles.
                    SubttFunias funias = this.save();
                    if(funias != null){//si logró guardar el funias de nivel.
                        FacesUtils.setInSession("funiasSeleccionado", funias);
                        this.actionView="adicionarNivel";
                    }
                } else if (((Lista)this.socTipoFunias.getValue()).getCodigo().longValue() ==
                           ConstantesParametros.LISTA_ACCESO) { //es el funias de acceso

                    SubttFunias existeFunias =
                        uad.getFuniasByCaptacionTipoFunias(this.captacion.getCodigo(),
                                                           ConstantesParametros.LISTA_ACCESO.intValue());
                    if (existeFunias != null) {
                        showMessage("Ya existe un funias del mismo tipo.");
                        continuar = false;
                        return;
                    }

                    FacesUtils.sendRedirect("detalleFuniasAcceso.jspx");

                } else if (((Lista)this.socTipoFunias.getValue()).getCodigo().longValue() ==
                            ConstantesParametros.LISTA_AFORO) { //es el funias de aforo

                    SubttFunias existeFunias =
                        uad.getFuniasByCaptacionTipoFunias(this.captacion.getCodigo(),
                                                           ConstantesParametros.LISTA_AFORO.intValue());
                    if (existeFunias != null) {
                        showMessage("Ya existe un funias del mismo tipo.");
                        continuar = false;
                        return;
                    }

                    FacesUtils.sendRedirect("detalleFuniasAforo.jspx");

                } else if (((Lista)this.socTipoFunias.getValue()).getCodigo().longValue() ==
                           ConstantesParametros.LISTA_MANANTIAL) { //es el funias de manantial

                    SubttFunias existeFunias =
                        uad.getFuniasByCaptacionTipoFunias(this.captacion.getCodigo(),
                                                           ConstantesParametros.LISTA_MANANTIAL.intValue());
                    if (existeFunias != null) {
                        showMessage("Ya existe un funias del mismo tipo.");
                        continuar = false;
                        return;
                    }

                    FacesUtils.sendRedirect("detalleFuniasManantial.jspx");

                } else {
                    continuar = false;
                }
                
                
                
                
                this.uploadedFile = null;//si ha seleccionado un archivo, lo borra.
            }else{
                showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.socTipoFunias);
                continuar = false;
            }
        }catch(IdeamException e){
            showMessage(e);
        }    
    }
    
    //geologia
    public void cmbNextGeologia_actionListener(ActionEvent actionEvent) {
        //validaciones
        
        SubttFunias funias = this.save();
        
        showPopup(p_registro_exitoso, true);
        
    }
    
    
    //Construccion
    
    public void cmbDatosConstruccion_actionListener(ActionEvent actionEvent) {
        //validaciones   
        
        SubttFunias funias = this.save();
        
        showPopup(p_registro_exitoso, true);
    }
    
    
    public void cmbDisenoConstruccion_actionListener(ActionEvent actionEvent) {
        // validaciones
        
        SubttFunias funias = this.save();
        
        showPopup(p_registro_exitoso, true);
    }
    
    
    //Explotacion
    public void cmbGeofisica_actionListener(ActionEvent actionEvent) {
        //validaciones
        
        SubttFunias funias = this.save();
        
        showPopup(p_registro_exitoso, true);
        
    }
    
    //Diagnostico sanitario
    public void cmbDatosDiagnostico_actionListener(ActionEvent actionEvent) {
        //validaciones
        
        SubttFunias funias = this.save();
        
        showPopup(p_registro_exitoso, true);
    }

    public void cmbFuentesContaminaExplotacion_actionListener(ActionEvent actionEvent) {
        //validaciones
        
        SubttFunias funias = this.save();
        
        showPopup(p_registro_exitoso, true);
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
                this.uploadedFile = null;
                this.archivoCargado=null;
                return;
            }
            this.cargarArchivo(fileNew, fileOld, ConstantesCaptaciones.ARCHIVO_TIPO_REGISTRO_LITOLOGICO);
            this.setUploadedFile(fileNew);
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
                this.uploadedFile = null;
                this.archivoCargado=null;
                return;
            }
            this.cargarArchivo(fileNew, fileOld, ConstantesCaptaciones.ARCHIVO_TIPO_DISENO);
            this.setUploadedFile(fileNew);
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
                this.uploadedFile = null;
                this.archivoCargado=null;
                return;
            }
            this.cargarArchivo(fileNew, fileOld, ConstantesCaptaciones.ARCHIVO_TIPO_REGISTRO_GEOFISICO);
            this.setUploadedFile(fileNew);
        }
    }
    
    public void cargarArchivo(UploadedFile uploadedFileNew, 
                              UploadedFile uploadedFileOld, String tipoCarga) {
        this.archivoCargado=null;
        
        //System.out.println(":::::::::::::CARGAR ARCHIVOS 1: "+((this.archivosCargados!=null)?this.archivosCargados.size():0));
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
                    //System.out.println(":::::::::::::ENTRO REMOVER 1: "+ac);
                    //System.out.println(":::::::::::::EXISTE: "+this.archivosCargados.contains(ac));
                   this.archivosCargados.remove(ac);
                }
            }catch(Exception e){
            }
        }
        
        //System.out.println(":::::::::::::CARGAR ARCHIVOS 2: "+((this.archivosCargados!=null)?this.archivosCargados.size():0));
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
            //System.out.println(":::::::::::::ARCHIVO: "+this.archivoCargado);
            //System.out.println(":::::::::::::CARGAR ARCHIVOS 3: "+this.archivosCargados.size());
            
        }
    }
    
    public SubttFunias save(){
        SubttFunias funias = new SubttFunias();
        try{
            UsuarioConectadoTO usuarioConectado = 
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
                       
            funias.setCodigoAutoridad(usuarioConectado.getUsuario().
                                   getAutoridadAmbiental().getId().longValue());
            funias.setIdCaptacion(this.captacion.getCodigo());
            funias.setTipoFunias((this.socTipoFunias.getValue()!=null) 
                                ? ((Lista)this.socTipoFunias.getValue()).getCodigo() 
                                : null);
            
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
                    funias.setSubttFuniasDocumentosList(new ArrayList());
                    System.out.println("==============ARCHIVOS EN BEAN: "+this.archivosCargados.size());
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
            
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
            funias = uad.createFunias(funias);
            
             
        }catch(IdeamException e){
            funias = null;
            showMessage(e);
        }
        return funias;
    }
    
    ///////////////
    
    public void setCaptacion(Captacion captacion) {
        this.captacion = captacion;
    }

    public Captacion getCaptacion() {
        return captacion;
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
    /////
    
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

    public void setPnlTipoFunias(RichPanelBox panelBox1) {
        this.pnlTipoFunias = panelBox1;
    }

    public RichPanelBox getPnlTipoFunias() {
        return pnlTipoFunias;
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

    public void setCmbNextTipoFunias(RichCommandButton commandButton1) {
        this.cmbNextTipoFunias = commandButton1;
    }

    public RichCommandButton getCmbNextTipoFunias() {
        return cmbNextTipoFunias;
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

    public void setSocTipoFunias(RichSelectOneChoice selectOneChoice1) {
        this.socTipoFunias = selectOneChoice1;
    }

    public RichSelectOneChoice getSocTipoFunias() {
        return socTipoFunias;
    }

    public void setSiTipoFunias(UISelectItems selectItems1) {
        this.siTipoFunias = selectItems1;
    }

    public UISelectItems getSiTipoFunias() {
        return siTipoFunias;
    }


    public void setPnlGeologia(RichPanelBox panelBox2) {
        this.pnlGeologia = panelBox2;
    }

    public RichPanelBox getPnlGeologia() {
        return pnlGeologia;
    }


    public void setPnlDatosConstruccion(RichPanelBox panelBox1) {
        this.pnlDatosConstruccion = panelBox1;
    }

    public RichPanelBox getPnlDatosConstruccion() {
        return pnlDatosConstruccion;
    }


    public void setPnlGeofisica(RichPanelBox panelBox1) {
        this.pnlGeofisica = panelBox1;
    }

    public RichPanelBox getPnlGeofisica() {
        return pnlGeofisica;
    }


    public void setPnlDatosDiagnostico(RichPanelBox panelBox1) {
        this.pnlDatosDiagnostico = panelBox1;
    }

    public RichPanelBox getPnlDatosDiagnostico() {
        return pnlDatosDiagnostico;
    }


    public void setPanelStretchLayout3(RichPanelStretchLayout panelStretchLayout3) {
        this.panelStretchLayout3 = panelStretchLayout3;
    }

    public RichPanelStretchLayout getPanelStretchLayout3() {
        return panelStretchLayout3;
    }


    public void setPanelStretchLayout6(RichPanelStretchLayout panelStretchLayout6) {
        this.panelStretchLayout6 = panelStretchLayout6;
    }

    public RichPanelStretchLayout getPanelStretchLayout6() {
        return panelStretchLayout6;
    }


    public void setPanelStretchLayout12(RichPanelStretchLayout panelStretchLayout12) {
        this.panelStretchLayout12 = panelStretchLayout12;
    }

    public RichPanelStretchLayout getPanelStretchLayout12() {
        return panelStretchLayout12;
    }

    public void setPanelStretchLayout13(RichPanelStretchLayout panelStretchLayout13) {
        this.panelStretchLayout13 = panelStretchLayout13;
    }

    public RichPanelStretchLayout getPanelStretchLayout13() {
        return panelStretchLayout13;
    }

    public void setPanelStretchLayout14(RichPanelStretchLayout panelStretchLayout14) {
        this.panelStretchLayout14 = panelStretchLayout14;
    }

    public RichPanelStretchLayout getPanelStretchLayout14() {
        return panelStretchLayout14;
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


    public void setPanelGroupLayout23(RichPanelGroupLayout panelGroupLayout23) {
        this.panelGroupLayout23 = panelGroupLayout23;
    }

    public RichPanelGroupLayout getPanelGroupLayout23() {
        return panelGroupLayout23;
    }

    public void setPanelGroupLayout24(RichPanelGroupLayout panelGroupLayout24) {
        this.panelGroupLayout24 = panelGroupLayout24;
    }

    public RichPanelGroupLayout getPanelGroupLayout24() {
        return panelGroupLayout24;
    }


    public void setPanelGroupLayout27(RichPanelGroupLayout panelGroupLayout27) {
        this.panelGroupLayout27 = panelGroupLayout27;
    }

    public RichPanelGroupLayout getPanelGroupLayout27() {
        return panelGroupLayout27;
    }

    public void setPanelGroupLayout28(RichPanelGroupLayout panelGroupLayout28) {
        this.panelGroupLayout28 = panelGroupLayout28;
    }

    public RichPanelGroupLayout getPanelGroupLayout28() {
        return panelGroupLayout28;
    }


    public void setCmbNextGeologia(RichCommandButton commandButton1) {
        this.cmbNextGeologia = commandButton1;
    }

    public RichCommandButton getCmbNextGeologia() {
        return cmbNextGeologia;
    }


    public void setCmbDatosConstruccion(RichCommandButton commandButton2) {
        this.cmbDatosConstruccion = commandButton2;
    }

    public RichCommandButton getCmbDatosConstruccion() {
        return cmbDatosConstruccion;
    }

    public void setSpacer2(RichSpacer spacer2) {
        this.spacer2 = spacer2;
    }

    public RichSpacer getSpacer2() {
        return spacer2;
    }


    public void setSpacer5(RichSpacer spacer5) {
        this.spacer5 = spacer5;
    }

    public RichSpacer getSpacer5() {
        return spacer5;
    }


    public void setCmbGeofisica(RichCommandButton commandButton8) {
        this.cmbGeofisica = commandButton8;
    }

    public RichCommandButton getCmbGeofisica() {
        return cmbGeofisica;
    }

    public void setSpacer11(RichSpacer spacer11) {
        this.spacer11 = spacer11;
    }

    public RichSpacer getSpacer11() {
        return spacer11;
    }


    public void setCmbDatosDiagnostico(RichCommandButton commandButton10) {
        this.cmbDatosDiagnostico = commandButton10;
    }

    public RichCommandButton getCmbDatosDiagnostico() {
        return cmbDatosDiagnostico;
    }

    public void setSpacer13(RichSpacer spacer13) {
        this.spacer13 = spacer13;
    }

    public RichSpacer getSpacer13() {
        return spacer13;
    }


    public void setPanelGroupLayout31(RichPanelGroupLayout panelGroupLayout31) {
        this.panelGroupLayout31 = panelGroupLayout31;
    }

    public RichPanelGroupLayout getPanelGroupLayout31() {
        return panelGroupLayout31;
    }

    public void setPanelFormLayout2(RichPanelFormLayout panelFormLayout2) {
        this.panelFormLayout2 = panelFormLayout2;
    }

    public RichPanelFormLayout getPanelFormLayout2() {
        return panelFormLayout2;
    }


    public void setPanelGroupLayout34(RichPanelGroupLayout panelGroupLayout34) {
        this.panelGroupLayout34 = panelGroupLayout34;
    }

    public RichPanelGroupLayout getPanelGroupLayout34() {
        return panelGroupLayout34;
    }

    public void setPanelFormLayout5(RichPanelFormLayout panelFormLayout5) {
        this.panelFormLayout5 = panelFormLayout5;
    }

    public RichPanelFormLayout getPanelFormLayout5() {
        return panelFormLayout5;
    }


    public void setPanelGroupLayout40(RichPanelGroupLayout panelGroupLayout40) {
        this.panelGroupLayout40 = panelGroupLayout40;
    }

    public RichPanelGroupLayout getPanelGroupLayout40() {
        return panelGroupLayout40;
    }

    public void setPanelFormLayout11(RichPanelFormLayout panelFormLayout11) {
        this.panelFormLayout11 = panelFormLayout11;
    }

    public RichPanelFormLayout getPanelFormLayout11() {
        return panelFormLayout11;
    }


    public void setPanelGroupLayout42(RichPanelGroupLayout panelGroupLayout42) {
        this.panelGroupLayout42 = panelGroupLayout42;
    }

    public RichPanelGroupLayout getPanelGroupLayout42() {
        return panelGroupLayout42;
    }

    public void setPanelFormLayout13(RichPanelFormLayout panelFormLayout13) {
        this.panelFormLayout13 = panelFormLayout13;
    }

    public RichPanelFormLayout getPanelFormLayout13() {
        return panelFormLayout13;
    }


    public void setP_registro_exitoso(RichPopup popup1) {
        this.p_registro_exitoso = popup1;
    }

    public RichPopup getP_registro_exitoso() {
        return p_registro_exitoso;
    }

    public void setD_registro_exitoso(RichDialog dialog1) {
        this.d_registro_exitoso = dialog1;
    }

    public RichDialog getD_registro_exitoso() {
        return d_registro_exitoso;
    }

    public void setPanelGroupLayout44(RichPanelGroupLayout panelGroupLayout44) {
        this.panelGroupLayout44 = panelGroupLayout44;
    }

    public RichPanelGroupLayout getPanelGroupLayout44() {
        return panelGroupLayout44;
    }

    public void setCbAceptar(RichCommandButton commandButton1) {
        this.cbAceptar = commandButton1;
    }

    public RichCommandButton getCbAceptar() {
        return cbAceptar;
    }

    public void setPanelStretchLayout16(RichPanelStretchLayout panelStretchLayout16) {
        this.panelStretchLayout16 = panelStretchLayout16;
    }

    public RichPanelStretchLayout getPanelStretchLayout16() {
        return panelStretchLayout16;
    }

    public void setImage1(RichImage image1) {
        this.image1 = image1;
    }

    public RichImage getImage1() {
        return image1;
    }

    public void setOt8(RichOutputText outputText1) {
        this.ot8 = outputText1;
    }

    public RichOutputText getOt8() {
        return ot8;
    }


    public void setSocLocTopografica(RichSelectOneChoice selectOneChoice1) {
        this.socLocTopografica = selectOneChoice1;
    }

    public RichSelectOneChoice getSocLocTopografica() {
        return socLocTopografica;
    }

    public void setSiLocTopografica(UISelectItems selectItems1) {
        this.siLocTopografica = selectItems1;
    }

    public UISelectItems getSiLocTopografica() {
        return siLocTopografica;
    }

    public void setItUnidadGeo(RichInputText inputText1) {
        this.itUnidadGeo = inputText1;
    }

    public RichInputText getItUnidadGeo() {
        return itUnidadGeo;
    }

    public void setItLitologiaSuperficial(RichInputText inputText1) {
        this.itLitologiaSuperficial = inputText1;
    }

    public RichInputText getItLitologiaSuperficial() {
        return itLitologiaSuperficial;
    }

    public void setSocGeoforma(RichSelectOneChoice selectOneChoice1) {
        this.socGeoforma = selectOneChoice1;
    }

    public RichSelectOneChoice getSocGeoforma() {
        return socGeoforma;
    }

    public void setSiGeoforma(UISelectItems selectItems1) {
        this.siGeoforma = selectItems1;
    }

    public UISelectItems getSiGeoforma() {
        return siGeoforma;
    }

    public void setSocEstructura(RichSelectOneChoice selectOneChoice1) {
        this.socEstructura = selectOneChoice1;
    }

    public RichSelectOneChoice getSocEstructura() {
        return socEstructura;
    }

    public void setSiEstructura(UISelectItems selectItems1) {
        this.siEstructura = selectItems1;
    }

    public UISelectItems getSiEstructura() {
        return siEstructura;
    }

    public void setItNombreEstructura(RichInputText inputText1) {
        this.itNombreEstructura = inputText1;
    }

    public RichInputText getItNombreEstructura() {
        return itNombreEstructura;
    }

    public void setIfGeologia(RichInputFile inputFile1) {
        this.ifGeologia = inputFile1;
    }

    public RichInputFile getIfGeologia() {
        return ifGeologia;
    }

    public void setIdFechaConstruccion(RichInputDate inputDate1) {
        this.idFechaConstruccion = inputDate1;
    }

    public RichInputDate getIdFechaConstruccion() {
        return idFechaConstruccion;
    }


    public void setSocTipoAcabado(RichSelectOneChoice selectOneChoice1) {
        this.socTipoAcabado = selectOneChoice1;
    }

    public RichSelectOneChoice getSocTipoAcabado() {
        return socTipoAcabado;
    }

    public void setSiTipoAcabado(UISelectItems selectItems1) {
        this.siTipoAcabado = selectItems1;
    }

    public UISelectItems getSiTipoAcabado() {
        return siTipoAcabado;
    }


    public void setOtDatosBasicosGeologia(RichOutputText outputText1) {
        this.otDatosBasicosGeologia = outputText1;
    }

    public RichOutputText getOtDatosBasicosGeologia() {
        return otDatosBasicosGeologia;
    }

    public void setOtCarGeomorfoGeologia(RichOutputText outputText1) {
        this.otCarGeomorfoGeologia = outputText1;
    }

    public RichOutputText getOtCarGeomorfoGeologia() {
        return otCarGeomorfoGeologia;
    }

    public void setOtRegistroGeologia(RichOutputText outputText1) {
        this.otRegistroGeologia = outputText1;
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


    public void setOtDatosGeofisica(RichOutputText outputText1) {
        this.otDatosGeofisica = outputText1;
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

    public void setIdMedidaGeofisica(RichInputDate inputDate1) {
        this.idMedidaGeofisica = inputDate1;
    }

    public RichInputDate getIdMedidaGeofisica() {
        return idMedidaGeofisica;
    }

    public void setItEquipoGeofisica(RichInputText inputText1) {
        this.itEquipoGeofisica = inputText1;
    }

    public RichInputText getItEquipoGeofisica() {
        return itEquipoGeofisica;
    }

    public void setSocTipoRegistroGeofisica(RichSelectOneChoice selectOneChoice1) {
        this.socTipoRegistroGeofisica = selectOneChoice1;
    }

    public RichSelectOneChoice getSocTipoRegistroGeofisica() {
        return socTipoRegistroGeofisica;
    }

    public void setSiTipoRegistroGeofisica(UISelectItems selectItems1) {
        this.siTipoRegistroGeofisica = selectItems1;
    }

    public UISelectItems getSiTipoRegistroGeofisica() {
        return siTipoRegistroGeofisica;
    }


    public void setItCompaniaEjeGeofisica(RichInputText inputText1) {
        this.itCompaniaEjeGeofisica = inputText1;
    }

    public RichInputText getItCompaniaEjeGeofisica() {
        return itCompaniaEjeGeofisica;
    }

    public void setItResistLodoGeofisica(RichInputText inputText1) {
        this.itResistLodoGeofisica = inputText1;
    }

    public RichInputText getItResistLodoGeofisica() {
        return itResistLodoGeofisica;
    }

    public void setItTemperaturaLodo(RichInputText inputText1) {
        this.itTemperaturaLodo = inputText1;
    }

    public RichInputText getItTemperaturaLodo() {
        return itTemperaturaLodo;
    }

    public void setItProfundidadRegGeofisica(RichInputText inputText1) {
        this.itProfundidadRegGeofisica = inputText1;
    }

    public RichInputText getItProfundidadRegGeofisica() {
        return itProfundidadRegGeofisica;
    }

    public void setItCalidadRegistroGeofisica(RichInputText inputText1) {
        this.itCalidadRegistroGeofisica = inputText1;
    }

    public RichInputText getItCalidadRegistroGeofisica() {
        return itCalidadRegistroGeofisica;
    }

    public void setOtCapasGeofisica(RichOutputText outputText1) {
        this.otCapasGeofisica = outputText1;
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

    public void setIfDocumentoGeofisica(RichInputFile inputFile1) {
        this.ifDocumentoGeofisica = inputFile1;
    }

    public RichInputFile getIfDocumentoGeofisica() {
        return ifDocumentoGeofisica;
    }

    public void setItDiaIntConstruccion(RichInputText inputText1) {
        this.itDiaIntConstruccion = inputText1;
    }

    public RichInputText getItDiaIntConstruccion() {
        return itDiaIntConstruccion;
    }

    public void setItDiaExtConstruccion(RichInputText inputText1) {
        this.itDiaExtConstruccion = inputText1;
    }

    public RichInputText getItDiaExtConstruccion() {
        return itDiaExtConstruccion;
    }

    public void setItDiaPerfCostruccion(RichInputText inputText1) {
        this.itDiaPerfCostruccion = inputText1;
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

    public void setSocMaterialConstruccion(RichSelectOneChoice selectOneChoice1) {
        this.socMaterialConstruccion = selectOneChoice1;
    }

    public RichSelectOneChoice getSocMaterialConstruccion() {
        return socMaterialConstruccion;
    }

    public void setSiMaterialConstruccion(UISelectItems selectItems1) {
        this.siMaterialConstruccion = selectItems1;
    }

    public UISelectItems getSiMaterialConstruccion() {
        return siMaterialConstruccion;
    }


    public void setSocMetodoConstruccion(RichSelectOneChoice selectOneChoice1) {
        this.socMetodoConstruccion = selectOneChoice1;
    }

    public RichSelectOneChoice getSocMetodoConstruccion() {
        return socMetodoConstruccion;
    }

    public void setSiMetodoConstruccion(UISelectItems selectItems1) {
        this.siMetodoConstruccion = selectItems1;
    }

    public UISelectItems getSiMetodoConstruccion() {
        return siMetodoConstruccion;
    }


    public void setSocTratamientoConstruccion(RichSelectOneChoice selectOneChoice1) {
        this.socTratamientoConstruccion = selectOneChoice1;
    }

    public RichSelectOneChoice getSocTratamientoConstruccion() {
        return socTratamientoConstruccion;
    }

    public void setSiTratamientoConstruccion(UISelectItems selectItems1) {
        this.siTratamientoConstruccion = selectItems1;
    }

    public UISelectItems getSiTratamientoConstruccion() {
        return siTratamientoConstruccion;
    }


    public void setSocFluidoConstruccion(RichSelectOneChoice selectOneChoice1) {
        this.socFluidoConstruccion = selectOneChoice1;
    }

    public RichSelectOneChoice getSocFluidoConstruccion() {
        return socFluidoConstruccion;
    }

    public void setSiFluidoConstruccion(UISelectItems selectItems1) {
        this.siFluidoConstruccion = selectItems1;
    }

    public UISelectItems getSiFluidoConstruccion() {
        return siFluidoConstruccion;
    }

    public void setItCompaniaPerforadora(RichInputText inputText1) {
        this.itCompaniaPerforadora = inputText1;
    }

    public RichInputText getItCompaniaPerforadora() {
        return itCompaniaPerforadora;
    }

    public void setSbrColmatadaSi(RichSelectBooleanRadio selectBooleanRadio1) {
        this.sbrColmatadaSi = selectBooleanRadio1;
    }

    public RichSelectBooleanRadio getSbrColmatadaSi() {
        return sbrColmatadaSi;
    }

    public void setSbrColmatadaNo(RichSelectBooleanRadio selectBooleanRadio2) {
        this.sbrColmatadaNo = selectBooleanRadio2;
    }

    public RichSelectBooleanRadio getSbrColmatadaNo() {
        return sbrColmatadaNo;
    }


    public void setSbrColapsadaSi(RichSelectBooleanRadio selectBooleanRadio1) {
        this.sbrColapsadaSi = selectBooleanRadio1;
    }

    public RichSelectBooleanRadio getSbrColapsadaSi() {
        return sbrColapsadaSi;
    }

    public void setSbrColapsadaNo(RichSelectBooleanRadio selectBooleanRadio1) {
        this.sbrColapsadaNo = selectBooleanRadio1;
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

    public void setItProfEntubado(RichInputText inputText1) {
        this.itProfEntubado = inputText1;
    }

    public RichInputText getItProfEntubado() {
        return itProfEntubado;
    }

    public void setItProfPerforacion(RichInputText inputText1) {
        this.itProfPerforacion = inputText1;
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

    public void setItCantidadGravilla(RichInputText inputText1) {
        this.itCantidadGravilla = inputText1;
    }

    public RichInputText getItCantidadGravilla() {
        return itCantidadGravilla;
    }

    public void setItNivelGravilla(RichInputText inputText1) {
        this.itNivelGravilla = inputText1;
    }

    public RichInputText getItNivelGravilla() {
        return itNivelGravilla;
    }

    public void setItDiametroPromedio(RichInputText inputText1) {
        this.itDiametroPromedio = inputText1;
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

    public void setItCapacidadEmbalse(RichInputText inputText1) {
        this.itCapacidadEmbalse = inputText1;
    }

    public RichInputText getItCapacidadEmbalse() {
        return itCapacidadEmbalse;
    }

    public void setSbcEmbalse(RichSelectBooleanCheckbox selectBooleanCheckbox1) {
        this.sbcEmbalse = selectBooleanCheckbox1;
    }

    public RichSelectBooleanCheckbox getSbcEmbalse() {
        return sbcEmbalse;
    }

    public void setSbcTanque(RichSelectBooleanCheckbox selectBooleanCheckbox1) {
        this.sbcTanque = selectBooleanCheckbox1;
    }

    public RichSelectBooleanCheckbox getSbcTanque() {
        return sbcTanque;
    }

    public void setItCapacidadTanque(RichInputText inputText1) {
        this.itCapacidadTanque = inputText1;
    }

    public RichInputText getItCapacidadTanque() {
        return itCapacidadTanque;
    }

    public void setSbcAlberca(RichSelectBooleanCheckbox selectBooleanCheckbox1) {
        this.sbcAlberca = selectBooleanCheckbox1;
    }

    public RichSelectBooleanCheckbox getSbcAlberca() {
        return sbcAlberca;
    }

    public void setItCapacidadAlberca(RichInputText inputText1) {
        this.itCapacidadAlberca = inputText1;
    }

    public RichInputText getItCapacidadAlberca() {
        return itCapacidadAlberca;
    }

    public void setSbcOtro(RichSelectBooleanCheckbox selectBooleanCheckbox1) {
        this.sbcOtro = selectBooleanCheckbox1;
    }

    public RichSelectBooleanCheckbox getSbcOtro() {
        return sbcOtro;
    }

    public void setItCapacidadOtro(RichInputText inputText1) {
        this.itCapacidadOtro = inputText1;
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

    public void setIfDocumentoConstruccion(RichInputFile inputFile1) {
        this.ifDocumentoConstruccion = inputFile1;
    }

    public RichInputFile getIfDocumentoConstruccion() {
        return ifDocumentoConstruccion;
    }

    public void setSbrLetrinaSi(RichSelectBooleanRadio selectBooleanRadio1) {
        this.sbrLetrinaSi = selectBooleanRadio1;
    }

    public RichSelectBooleanRadio getSbrLetrinaSi() {
        return sbrLetrinaSi;
    }

    public void setSbrLetrinaNo(RichSelectBooleanRadio selectBooleanRadio2) {
        this.sbrLetrinaNo = selectBooleanRadio2;
    }

    public RichSelectBooleanRadio getSbrLetrinaNo() {
        return sbrLetrinaNo;
    }

    public void setItDistanciaLetrina(RichInputText inputText1) {
        this.itDistanciaLetrina = inputText1;
    }

    public RichInputText getItDistanciaLetrina() {
        return itDistanciaLetrina;
    }

    public void setSbrCharcoSi(RichSelectBooleanRadio selectBooleanRadio3) {
        this.sbrCharcoSi = selectBooleanRadio3;
    }

    public RichSelectBooleanRadio getSbrCharcoSi() {
        return sbrCharcoSi;
    }

    public void setSbrCharcoNo(RichSelectBooleanRadio selectBooleanRadio4) {
        this.sbrCharcoNo = selectBooleanRadio4;
    }

    public RichSelectBooleanRadio getSbrCharcoNo() {
        return sbrCharcoNo;
    }

    public void setItDistanciaCharco(RichInputText inputText1) {
        this.itDistanciaCharco = inputText1;
    }

    public RichInputText getItDistanciaCharco() {
        return itDistanciaCharco;
    }

    public void setSbrCriaderoSi(RichSelectBooleanRadio selectBooleanRadio1) {
        this.sbrCriaderoSi = selectBooleanRadio1;
    }

    public RichSelectBooleanRadio getSbrCriaderoSi() {
        return sbrCriaderoSi;
    }

    public void setSbrCriaderoNo(RichSelectBooleanRadio selectBooleanRadio2) {
        this.sbrCriaderoNo = selectBooleanRadio2;
    }

    public RichSelectBooleanRadio getSbrCriaderoNo() {
        return sbrCriaderoNo;
    }

    public void setItDistanciaCriadero(RichInputText inputText1) {
        this.itDistanciaCriadero = inputText1;
    }

    public RichInputText getItDistanciaCriadero() {
        return itDistanciaCriadero;
    }

    public void setSbrFiltracionSi(RichSelectBooleanRadio selectBooleanRadio1) {
        this.sbrFiltracionSi = selectBooleanRadio1;
    }

    public RichSelectBooleanRadio getSbrFiltracionSi() {
        return sbrFiltracionSi;
    }

    public void setSbrFiltracionNo(RichSelectBooleanRadio selectBooleanRadio2) {
        this.sbrFiltracionNo = selectBooleanRadio2;
    }

    public RichSelectBooleanRadio getSbrFiltracionNo() {
        return sbrFiltracionNo;
    }

    public void setItDistanciaFiltracion(RichInputText inputText1) {
        this.itDistanciaFiltracion = inputText1;
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

    public void setSbrCubiertaSi(RichSelectBooleanRadio selectBooleanRadio1) {
        this.sbrCubiertaSi = selectBooleanRadio1;
    }

    public RichSelectBooleanRadio getSbrCubiertaSi() {
        return sbrCubiertaSi;
    }

    public void setSbrCubiertaNo(RichSelectBooleanRadio selectBooleanRadio2) {
        this.sbrCubiertaNo = selectBooleanRadio2;
    }

    public RichSelectBooleanRadio getSbrCubiertaNo() {
        return sbrCubiertaNo;
    }

    public void setSbrCementoSi(RichSelectBooleanRadio selectBooleanRadio3) {
        this.sbrCementoSi = selectBooleanRadio3;
    }

    public RichSelectBooleanRadio getSbrCementoSi() {
        return sbrCementoSi;
    }

    public void setSbrCementoNo(RichSelectBooleanRadio selectBooleanRadio4) {
        this.sbrCementoNo = selectBooleanRadio4;
    }

    public RichSelectBooleanRadio getSbrCementoNo() {
        return sbrCementoNo;
    }


    public void setSbrSelloSi(RichSelectBooleanRadio selectBooleanRadio6) {
        this.sbrSelloSi = selectBooleanRadio6;
    }

    public RichSelectBooleanRadio getSbrSelloSi() {
        return sbrSelloSi;
    }

    public void setSbrSelloNo(RichSelectBooleanRadio selectBooleanRadio7) {
        this.sbrSelloNo = selectBooleanRadio7;
    }

    public RichSelectBooleanRadio getSbrSelloNo() {
        return sbrSelloNo;
    }

    public void setSbrCercoSi(RichSelectBooleanRadio selectBooleanRadio5) {
        this.sbrCercoSi = selectBooleanRadio5;
    }

    public RichSelectBooleanRadio getSbrCercoSi() {
        return sbrCercoSi;
    }

    public void setSbrCercoNo(RichSelectBooleanRadio selectBooleanRadio8) {
        this.sbrCercoNo = selectBooleanRadio8;
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

    public void setSbrCampoInfiltracionSi(RichSelectBooleanRadio selectBooleanRadio1) {
        this.sbrCampoInfiltracionSi = selectBooleanRadio1;
    }

    public RichSelectBooleanRadio getSbrCampoInfiltracionSi() {
        return sbrCampoInfiltracionSi;
    }

    public void setSbrCampoInfiltracionNo(RichSelectBooleanRadio selectBooleanRadio2) {
        this.sbrCampoInfiltracionNo = selectBooleanRadio2;
    }

    public RichSelectBooleanRadio getSbrCampoInfiltracionNo() {
        return sbrCampoInfiltracionNo;
    }

    public void setItDistanciaCampoInfiltracion(RichInputText inputText1) {
        this.itDistanciaCampoInfiltracion = inputText1;
    }

    public RichInputText getItDistanciaCampoInfiltracion() {
        return itDistanciaCampoInfiltracion;
    }


    public void setSbrCementerioSi(RichSelectBooleanRadio selectBooleanRadio3) {
        this.sbrCementerioSi = selectBooleanRadio3;
    }

    public RichSelectBooleanRadio getSbrCementerioSi() {
        return sbrCementerioSi;
    }

    public void setSbrCementerioNo(RichSelectBooleanRadio selectBooleanRadio4) {
        this.sbrCementerioNo = selectBooleanRadio4;
    }

    public RichSelectBooleanRadio getSbrCementerioNo() {
        return sbrCementerioNo;
    }

    public void setItDistanciaCementerio(RichInputText inputText2) {
        this.itDistanciaCementerio = inputText2;
    }

    public RichInputText getItDistanciaCementerio() {
        return itDistanciaCementerio;
    }

    public void setSbrEstacionServicioSi(RichSelectBooleanRadio selectBooleanRadio1) {
        this.sbrEstacionServicioSi = selectBooleanRadio1;
    }

    public RichSelectBooleanRadio getSbrEstacionServicioSi() {
        return sbrEstacionServicioSi;
    }

    public void setSbrEstacionServicioNo(RichSelectBooleanRadio selectBooleanRadio2) {
        this.sbrEstacionServicioNo = selectBooleanRadio2;
    }

    public RichSelectBooleanRadio getSbrEstacionServicioNo() {
        return sbrEstacionServicioNo;
    }

    public void setItDistanciaEstacion(RichInputText inputText1) {
        this.itDistanciaEstacion = inputText1;
    }

    public RichInputText getItDistanciaEstacion() {
        return itDistanciaEstacion;
    }

    public void setSbrLagunasOxidacionSi(RichSelectBooleanRadio selectBooleanRadio3) {
        this.sbrLagunasOxidacionSi = selectBooleanRadio3;
    }

    public RichSelectBooleanRadio getSbrLagunasOxidacionSi() {
        return sbrLagunasOxidacionSi;
    }

    public void setSbrLagunaOxidacionNo(RichSelectBooleanRadio selectBooleanRadio4) {
        this.sbrLagunaOxidacionNo = selectBooleanRadio4;
    }

    public RichSelectBooleanRadio getSbrLagunaOxidacionNo() {
        return sbrLagunaOxidacionNo;
    }

    public void setItDistanciaLaguna(RichInputText inputText2) {
        this.itDistanciaLaguna = inputText2;
    }

    public RichInputText getItDistanciaLaguna() {
        return itDistanciaLaguna;
    }

    public void setSbrLavaderosSi(RichSelectBooleanRadio selectBooleanRadio1) {
        this.sbrLavaderosSi = selectBooleanRadio1;
    }

    public RichSelectBooleanRadio getSbrLavaderosSi() {
        return sbrLavaderosSi;
    }

    public void setSbrLavaderosNo(RichSelectBooleanRadio selectBooleanRadio2) {
        this.sbrLavaderosNo = selectBooleanRadio2;
    }

    public RichSelectBooleanRadio getSbrLavaderosNo() {
        return sbrLavaderosNo;
    }

    public void setItDistanciaLavaderos(RichInputText inputText1) {
        this.itDistanciaLavaderos = inputText1;
    }

    public RichInputText getItDistanciaLavaderos() {
        return itDistanciaLavaderos;
    }

    public void setSbrSacrificioSi(RichSelectBooleanRadio selectBooleanRadio3) {
        this.sbrSacrificioSi = selectBooleanRadio3;
    }

    public RichSelectBooleanRadio getSbrSacrificioSi() {
        return sbrSacrificioSi;
    }

    public void setSbrSacrificioNo(RichSelectBooleanRadio selectBooleanRadio4) {
        this.sbrSacrificioNo = selectBooleanRadio4;
    }

    public RichSelectBooleanRadio getSbrSacrificioNo() {
        return sbrSacrificioNo;
    }

    public void setItSacrificioDistancia(RichInputText inputText2) {
        this.itSacrificioDistancia = inputText2;
    }

    public RichInputText getItSacrificioDistancia() {
        return itSacrificioDistancia;
    }

    public void setSbrPozoSi(RichSelectBooleanRadio selectBooleanRadio5) {
        this.sbrPozoSi = selectBooleanRadio5;
    }

    public RichSelectBooleanRadio getSbrPozoSi() {
        return sbrPozoSi;
    }

    public void setSbrPozoNo(RichSelectBooleanRadio selectBooleanRadio6) {
        this.sbrPozoNo = selectBooleanRadio6;
    }

    public RichSelectBooleanRadio getSbrPozoNo() {
        return sbrPozoNo;
    }

    public void setItPozoDistancia(RichInputText inputText3) {
        this.itPozoDistancia = inputText3;
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

    public void setSbrResiduoAgricolaSi(RichSelectBooleanRadio selectBooleanRadio1) {
        this.sbrResiduoAgricolaSi = selectBooleanRadio1;
    }

    public RichSelectBooleanRadio getSbrResiduoAgricolaSi() {
        return sbrResiduoAgricolaSi;
    }

    public void setSbrResiduoAgricolaNo(RichSelectBooleanRadio selectBooleanRadio1) {
        this.sbrResiduoAgricolaNo = selectBooleanRadio1;
    }

    public RichSelectBooleanRadio getSbrResiduoAgricolaNo() {
        return sbrResiduoAgricolaNo;
    }

    public void setSbrResiduoDomesticoSi(RichSelectBooleanRadio selectBooleanRadio1) {
        this.sbrResiduoDomesticoSi = selectBooleanRadio1;
    }

    public RichSelectBooleanRadio getSbrResiduoDomesticoSi() {
        return sbrResiduoDomesticoSi;
    }

    public void setSbrResiduoDomesticoNo(RichSelectBooleanRadio selectBooleanRadio2) {
        this.sbrResiduoDomesticoNo = selectBooleanRadio2;
    }

    public RichSelectBooleanRadio getSbrResiduoDomesticoNo() {
        return sbrResiduoDomesticoNo;
    }

    public void setSbrResiduoGanaderiaSi(RichSelectBooleanRadio selectBooleanRadio3) {
        this.sbrResiduoGanaderiaSi = selectBooleanRadio3;
    }

    public RichSelectBooleanRadio getSbrResiduoGanaderiaSi() {
        return sbrResiduoGanaderiaSi;
    }

    public void setSbrResiduoGanaderiaNo(RichSelectBooleanRadio selectBooleanRadio4) {
        this.sbrResiduoGanaderiaNo = selectBooleanRadio4;
    }

    public RichSelectBooleanRadio getSbrResiduoGanaderiaNo() {
        return sbrResiduoGanaderiaNo;
    }

    public void setSbrResiduoHospitalarioSi(RichSelectBooleanRadio selectBooleanRadio5) {
        this.sbrResiduoHospitalarioSi = selectBooleanRadio5;
    }

    public RichSelectBooleanRadio getSbrResiduoHospitalarioSi() {
        return sbrResiduoHospitalarioSi;
    }

    public void setSbrResiduoHospitalarioNo(RichSelectBooleanRadio selectBooleanRadio6) {
        this.sbrResiduoHospitalarioNo = selectBooleanRadio6;
    }

    public RichSelectBooleanRadio getSbrResiduoHospitalarioNo() {
        return sbrResiduoHospitalarioNo;
    }

    public void setSbrResiduoIndustrialSi(RichSelectBooleanRadio selectBooleanRadio7) {
        this.sbrResiduoIndustrialSi = selectBooleanRadio7;
    }

    public RichSelectBooleanRadio getSbrResiduoIndustrialSi() {
        return sbrResiduoIndustrialSi;
    }

    public void setSbrResiduoIndustrialNo(RichSelectBooleanRadio selectBooleanRadio8) {
        this.sbrResiduoIndustrialNo = selectBooleanRadio8;
    }

    public RichSelectBooleanRadio getSbrResiduoIndustrialNo() {
        return sbrResiduoIndustrialNo;
    }

    public void setSbrResiduoMineroSi(RichSelectBooleanRadio selectBooleanRadio1) {
        this.sbrResiduoMineroSi = selectBooleanRadio1;
    }

    public RichSelectBooleanRadio getSbrResiduoMineroSi() {
        return sbrResiduoMineroSi;
    }

    public void setSbrResiduoMineroNo(RichSelectBooleanRadio selectBooleanRadio2) {
        this.sbrResiduoMineroNo = selectBooleanRadio2;
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

    public void setSbrBotaderoCieloAbiertoSi(RichSelectBooleanRadio selectBooleanRadio1) {
        this.sbrBotaderoCieloAbiertoSi = selectBooleanRadio1;
    }

    public RichSelectBooleanRadio getSbrBotaderoCieloAbiertoSi() {
        return sbrBotaderoCieloAbiertoSi;
    }

    public void setSbrBotaderoCieloAbiertoNo(RichSelectBooleanRadio selectBooleanRadio2) {
        this.sbrBotaderoCieloAbiertoNo = selectBooleanRadio2;
    }

    public RichSelectBooleanRadio getSbrBotaderoCieloAbiertoNo() {
        return sbrBotaderoCieloAbiertoNo;
    }

    public void setSbrCompostajeSi(RichSelectBooleanRadio selectBooleanRadio3) {
        this.sbrCompostajeSi = selectBooleanRadio3;
    }

    public RichSelectBooleanRadio getSbrCompostajeSi() {
        return sbrCompostajeSi;
    }

    public void setSbrCompostajeNo(RichSelectBooleanRadio selectBooleanRadio4) {
        this.sbrCompostajeNo = selectBooleanRadio4;
    }

    public RichSelectBooleanRadio getSbrCompostajeNo() {
        return sbrCompostajeNo;
    }

    public void setSbrIncineracionSi(RichSelectBooleanRadio selectBooleanRadio5) {
        this.sbrIncineracionSi = selectBooleanRadio5;
    }

    public RichSelectBooleanRadio getSbrIncineracionSi() {
        return sbrIncineracionSi;
    }

    public void setSbrIncineracionNo(RichSelectBooleanRadio selectBooleanRadio6) {
        this.sbrIncineracionNo = selectBooleanRadio6;
    }

    public RichSelectBooleanRadio getSbrIncineracionNo() {
        return sbrIncineracionNo;
    }

    public void setSbrReciclajeSi(RichSelectBooleanRadio selectBooleanRadio7) {
        this.sbrReciclajeSi = selectBooleanRadio7;
    }

    public RichSelectBooleanRadio getSbrReciclajeSi() {
        return sbrReciclajeSi;
    }

    public void setSbrReciclajeNo(RichSelectBooleanRadio selectBooleanRadio8) {
        this.sbrReciclajeNo = selectBooleanRadio8;
    }

    public RichSelectBooleanRadio getSbrReciclajeNo() {
        return sbrReciclajeNo;
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public ArchivoCargadoTO getArchivoCargado() {
        return archivoCargado;
    }

    public void setArchivoCargado(ArchivoCargadoTO archivoCargado) {
        this.archivoCargado = archivoCargado;
    }


    public void setActionView(String actionView) {
        this.actionView = actionView;
    }

    public String getActionView() {
        return actionView;
    }


    public void setOutputText12(RichOutputText outputText12) {
        this.outputText12 = outputText12;
    }

    public RichOutputText getOutputText12() {
        return outputText12;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }


}
