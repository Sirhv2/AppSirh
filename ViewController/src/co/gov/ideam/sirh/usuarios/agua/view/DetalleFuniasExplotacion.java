package co.gov.ideam.sirh.usuarios.agua.view;

import co.gov.ideam.sirh.oferta.model.SiovEstaciones;
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
import oracle.adf.view.rich.component.rich.RichMenu;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputFile;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.nav.RichCommandMenuItem;
import oracle.adf.view.rich.component.rich.output.RichImage;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
import oracle.adf.view.rich.component.rich.output.RichSpacer;

import oracle.adf.view.rich.context.AdfFacesContext;

import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.model.UploadedFile;

public class DetalleFuniasExplotacion extends StandarBean{
    
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
    private List listaMetodoExplotacion;
    //listado desde los parametros de categorias
    private List listaTipoEnergia;
    
    private boolean archivosRelacionados;
    private boolean fileBombeo;
    private boolean filePozo;
    private boolean fileFiltro;
    
    
    private UploadedFile uploadedFile;
    private UploadedFile uploadedFile1;
    private UploadedFile uploadedFile2;
    private ArchivoCargadoTO archivoCargado;
    private List<ArchivoCargadoTO> archivosCargados;
    
    private SubttFuniasDocumentos documentoSeleccionado;
    //la siguiente variable se creo para evitar inconsistencias, con la vista, 
    //en la lista de documentos en el objeto funias cuando se remueve un objeto 
    //de la lista.
    private List listaArchivosRelacionados;

    
    
    private RichForm form1;
    private RichDocument document1;
    private RichPanelStretchLayout panelStretchLayout1;
    private RichPanelSplitter panelSplitter1;
    private RichPanelGroupLayout panelGroupLayout1;
    private RichPanelBox pnlbDatos;
    private RichPanelGroupLayout panelGroupLayout2;
    private RichPanelFormLayout panelFormLayout1;
    private RichPanelGroupLayout panelGroupLayout3;
    private RichPanelCollection panelCollection1;
    private RichMenu menu1;
    private RichCommandMenuItem cmiVer;
    private RichCommandMenuItem cmiBorrar;
    private RichTable table1;
    private RichPanelGroupLayout panelGroupLayout4;
    private RichSpacer spacer1;
    private RichCommandLink commandLink1;
    private RichSpacer spacer2;
    private RichCommandLink commandLink2;
    private RichSpacer spacer3;
    private RichCommandLink commandLink3;
    private RichSpacer spacer4;
    private RichCommandLink commandLink4;
    private RichSpacer spacer5;
    private RichCommandLink commandLink5;
    private RichSpacer spacer6;
    private RichCommandLink commandLink6;
    private RichSpacer spacer7;
    private RichOutputText outputText3;
    private RichPanelGroupLayout panelGroupLayout5;
    private RichPanelGroupLayout panelGroupLayout6;
    private RichSpacer spacer8;
    private RichCommandButton cbAceptar;
    private RichSpacer spacer9;
    private RichCommandButton cbBorrar;
    private RichPanelGroupLayout panelGroupLayout7;
    private RichPopup p_borrar_docto;
    private RichPopup popup_borrar;
    private RichPopup p_registro_exitoso;
    private RichDialog d_borrar_docto;
    private RichDialog d_borrar;
    private RichDialog d_registro_exitoso;
    private RichOutputText ot100;
    private RichCommandButton cbAceptarDi;
    private RichPanelStretchLayout panelStretchLayout2;
    private RichOutputText ot8;
    private RichImage i1;
    private RichPanelGroupLayout panelGroupLayout8;
    private RichPanelGroupLayout panelGroupLayout9;
    private RichCommandButton cb_si_borrar;
    private RichSpacer spacer10;
    private RichCommandButton cb_no_borrar;
    private RichOutputText ot_borrar_1;
    private RichSpacer spacer11;
    private RichOutputText ot_borrar_2;
    private RichSpacer spacer12;
    private RichOutputText otBasicos;
    private RichSelectOneChoice socMetodoExplotacion;
    private UISelectItems siMetodoExplotacion;
    private RichSelectOneChoice socTipoEnergia;
    private UISelectItems siTipoEnergia;
    private RichSpacer spacer13;
    private RichOutputText otTuberiaExplotacion;
    private RichInputText itDiametro;
    private RichInputText itMaterial;
    private RichInputText itLongitud;
    private RichSpacer spacer14;
    private RichOutputText otBomba;
    private RichInputText itClasebomba;
    private RichInputText itModeloBomba;
    private RichInputText itPotenciaBomba;
    private RichInputText itProfundidadSuccion;
    private RichOutputText otDocumentos;
    private RichSpacer spacer15;
    private RichInputFile ifDocumentoBombeo;
    private RichInputFile ifDocumentoFiltros;
    private RichInputFile ifDocumentoPozo;
    private RichSpacer spacer16;
    private RichSpacer spacer17;
    private RichSpacer spacer18;
    private RichPanelGroupLayout panelGroupLayout10;



    public DetalleFuniasExplotacion(){
        FacesUtils.setActiveBean("detalleFuniasExplotacion",
                                 "Detalle de Funias", 
                                 UsuariosAguaDelegate.class);
        this.load();
    }
    
    public void load(){
        try{
            
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
            ParametrosDelegate pd = ParametrosDelegate.getInstance(); 
            
            Object obj = FacesUtils.getFromSession("funiasSeleccionado");//la concesion desde la cual se inicia.
            if(obj instanceof SubttFunias){
                this.funias = (SubttFunias)obj;
            }else {                
                Long codigo = (Long)FacesUtils.getFromSession("funiasSeleccionado");
                if(codigo != null ){
                    this.funias = uad.getFunias(codigo);                      
                    FacesUtils.setInSession("funiasSeleccionado", this.funias);
                }
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

            this.listaTiposFunias = 
                this.cargarParametro(ConstantesParametros.
                                                   CATEGORIA_FUNIAS_SECCIONES);
            
            this.listaMetodoExplotacion = 
                this.cargarParametro(ConstantesParametros.
                                                   CATEGORIA_METODO_EXTRACCION);
            
            this.listaTipoEnergia = 
                this.cargarParametro(ConstantesParametros.
                                                   CATEGORIA_FUNIAS_TIPO_ENERGIA);
            if (this.funias != null && this.funias.getId() != null &&
                    this.funias.getId() > 0) {
                List documentosAsociados =
                    uad.getFilesByFunias(this.funias.getId());
                if (documentosAsociados != null &&
                        !documentosAsociados.isEmpty()) {
                    this.funias.setSubttFuniasDocumentosList(documentosAsociados);
                    
                    this.listaArchivosRelacionados = null;
                    if(this.funias.getSubttFuniasDocumentosList()!=null){
                        this.listaArchivosRelacionados = new ArrayList();
                        for(SubttFuniasDocumentos documento : this.funias.getSubttFuniasDocumentosList()){
                            if(!this.listaArchivosRelacionados.contains(documento)){
                                System.out.println(":::::::::::::LOAD DOCUMENTO ADICIONADO: "+documento);
                                this.listaArchivosRelacionados.add(documento);
                            }
                        }
                    }
                }

                this.archivosRelacionados =
                        ((this.funias.getSubttFuniasDocumentosList() != null) ?
                         this.funias.getSubttFuniasDocumentosList().size() >
                         0 : false);

                if (this.funias.getMetodoExplotacion() != null) {
                    this.funias.setObjMetodoExplotacion(pd.getLista(new Integer(this.funias.getMetodoExplotacion())));
                }

                if (this.funias.getTipoEnergia() != null) {
                    this.funias.setObjTipoEnergia(pd.getLista(new Integer(this.funias.getTipoEnergia())));
                }
            }else{
                this.funias = new SubttFunias();    
            }
            
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
    
    public void ifDocumentoBombeo_valueChangeListener(ValueChangeEvent valueChangeEvent) {
        System.out.println("================================FUNIAS AFORO ifDocumentoBOMBEO FILE NEW: ");
        UploadedFile fileOld = (UploadedFile)valueChangeEvent.getOldValue();
        UploadedFile fileNew = (UploadedFile)valueChangeEvent.getNewValue();
        if (fileNew!=null){
            String tipoArchivo = fileNew.getContentType();
            if (!tipoArchivo.endsWith("pdf")){
                String params[] = { fileNew.getFilename(), "PDF" };
                showMessage("tipo_archivo_incorrecto",
                            params,
                            FacesMessage.SEVERITY_ERROR,
                            ifDocumentoBombeo);
                this.ifDocumentoBombeo.resetValue();
                this.uploadedFile=null;
                this.archivoCargado=null;
                return;
            }
            this.cargarArchivo(fileNew, fileOld, ConstantesCaptaciones.ARCHIVO_TIPO_BOMBEO);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.ifDocumentoBombeo);
            //AdfFacesContext.getCurrentInstance().addPartialTarget(this.pnlbDatos);
        }
    }
    
    public void ifDocumentoFiltro_valueChangeListener(ValueChangeEvent valueChangeEvent) {
        System.out.println("================================FUNIAS AFORO ifDocumentoFILTRO FILE NEW: ");
        UploadedFile fileOld = (UploadedFile)valueChangeEvent.getOldValue();
        UploadedFile fileNew = (UploadedFile)valueChangeEvent.getNewValue();
        if (fileNew!=null){
            String tipoArchivo = fileNew.getContentType();
            if (!tipoArchivo.endsWith("pdf")){
                String params[] = { fileNew.getFilename(), "PDF" };
                showMessage("tipo_archivo_incorrecto",
                            params,
                            FacesMessage.SEVERITY_ERROR,
                            ifDocumentoFiltros);
                this.ifDocumentoFiltros.resetValue();
                this.uploadedFile1=null;
                this.archivoCargado=null;
                return;
            }
            this.cargarArchivo(fileNew, fileOld, ConstantesCaptaciones.ARCHIVO_TIPO_FILTRO);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.ifDocumentoFiltros);
            //AdfFacesContext.getCurrentInstance().addPartialTarget(this.pnlbDatos);
        }
    }
    
    public void ifDocumentoFoto_valueChangeListener(ValueChangeEvent valueChangeEvent) {
        System.out.println("================================FUNIAS AFORO ifDocumentoFOTO FILE NEW: ");
        UploadedFile fileOld = (UploadedFile)valueChangeEvent.getOldValue();
        UploadedFile fileNew = (UploadedFile)valueChangeEvent.getNewValue();
        if (fileNew!=null){
            String tipoArchivo = fileNew.getContentType();
            if (!tipoArchivo.endsWith("pdf")){
                String params[] = { fileNew.getFilename(), "PDF" };
                showMessage("tipo_archivo_incorrecto",
                            params,
                            FacesMessage.SEVERITY_ERROR,
                            ifDocumentoPozo);
                this.ifDocumentoPozo.resetValue();
                this.uploadedFile2=null;
                this.archivoCargado=null;
                return;
            }
            this.cargarArchivo(fileNew, fileOld, ConstantesCaptaciones.ARCHIVO_TIPO_POZO);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.ifDocumentoPozo);
            //AdfFacesContext.getCurrentInstance().addPartialTarget(this.pnlbDatos);
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
        
        SubttFuniasDocumentos docSeleccionado = (SubttFuniasDocumentos)this.table1.getSelectedRowData();
        this.documentoSeleccionado = docSeleccionado;
        
        if (this.documentoSeleccionado==null){
            showMessage(getText("seleccionar_registro"),
                        FacesMessage.SEVERITY_ERROR);
            return;
        }
        showReport(this.documentoSeleccionado.getDocumento());
        
        this.documentoSeleccionado = null;
    }

    public void cb_borrar_docto_actionListener(ActionEvent actionEvent) {
        //showPopup(p_borrar_docto, true);
        System.out.println(":::::::::::::BORRAR ARCHIVO");
        
        
        SubttFuniasDocumentos docSeleccionado = (SubttFuniasDocumentos)this.table1.getSelectedRowData();
        
        System.out.println(":::::::::::::BORRAR ARCHIVO: " + docSeleccionado);
        this.documentoSeleccionado = docSeleccionado;
        System.out.println(":::::::::::::BORRAR ARCHIVO: " + this.documentoSeleccionado);
        
        if (this.documentoSeleccionado==null){
            showMessage(getText("seleccionar_registro"),
                        FacesMessage.SEVERITY_ERROR);
            return;
        }
        
        System.out.println(":::::::::::::BORRAR ARCHIVO: " + this.documentoSeleccionado);
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
        }catch(Exception e){
            
        }
        
        this.documentoSeleccionado = null;
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.ifDocumentoBombeo);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.ifDocumentoFiltros);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.ifDocumentoPozo);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.table1);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelCollection1);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.pnlbDatos);   
    }
    
    /*
    public void table_selectionListener(SelectionEvent selectionEvent) {
        System.out.println(":::::::::::::::::::::: VAR ARCHIVO 1: "+this.documentoSeleccionado);
        this.documentoSeleccionado = null;
        RichTable t_aux = (RichTable)selectionEvent.getSource();
        System.out.println("::::::::::::::::::::::ARCHIVO SEL: "+(SubttFuniasDocumentos)t_aux.getSelectedRowData());
        
        this.documentoSeleccionado = (SubttFuniasDocumentos)t_aux.getSelectedRowData();
        
        System.out.println(":::::::::::::::::::::: VAR ARCHIVO 2: "+this.documentoSeleccionado);
    }
    */
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
        
        if(this.socMetodoExplotacion.getValue()==null){
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR, 
                        this.socMetodoExplotacion);       
            continuar = false;
        }
        
        if(this.socTipoEnergia.getValue()==null){
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR, 
                        this.socTipoEnergia);       
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
               
            

            ///////////Explotacion
            this.funias.setMetodoExplotacion((this.socMetodoExplotacion.getValue()!=null)
                                    ? ((Lista)this.socMetodoExplotacion.getValue()).getCodigo()
                                    : null);
            this.funias.setTipoEnergia((this.socTipoEnergia.getValue()!=null)
                                    ? ((Lista)this.socTipoEnergia.getValue()).getCodigo()
                                    : null);
            this.funias.setDiametro((this.itDiametro.getValue()!=null)
                                ? Double.parseDouble(""+this.itDiametro.getValue())
                                : null);
            this.funias.setLongitud((this.itLongitud.getValue()!=null)
                                ? Double.parseDouble(""+this.itLongitud.getValue())
                                : null);
            this.funias.setMaterial((this.itMaterial.getValue()!=null)
                               ?this.itMaterial.getValue().toString()
                               :null);
            this.funias.setBombaClase((this.itClasebomba.getValue()!=null)
                               ?this.itClasebomba.getValue().toString()
                               :null);
            this.funias.setBombaTipo((this.itModeloBomba.getValue()!=null)
                               ?this.itModeloBomba.getValue().toString()
                               :null);//modelo de bomba
            this.funias.setBombaPotencia((this.itPotenciaBomba.getValue()!=null)
                               ?new Double(this.itPotenciaBomba.getValue().toString())
                               :null);
            this.funias.setBombaProfundidadSuccion((this.itProfundidadSuccion.getValue()!=null)
                               ?new Double(this.itProfundidadSuccion.getValue().toString())
                               :null);



            //documento
            if(this.archivosCargados != null){
                try{
                    System.out.println("==============ARCHIVOS EN BEAN: "+this.archivosCargados.size());
                    if(this.funias.getSubttFuniasDocumentosList() == null){
                        this.funias.setSubttFuniasDocumentosList(new ArrayList()); 
                    }
                    for(ArchivoCargadoTO archivo: this.archivosCargados){
                        SubttFuniasDocumentos doc = new SubttFuniasDocumentos();
                        doc.setDocumento(archivo.getContent());
                        doc.setIdFunias(this.funias.getId());
                        doc.setCodigoAutoridad(usuarioConectado.getUsuario().
                                       getAutoridadAmbiental().getId().longValue());
                        doc.setTipo(archivo.getTipoCarga());
                        this.funias.getSubttFuniasDocumentosList().add(doc);
                        
                    }
                }catch(IdeamException e){
                    showMessage(e);
                }
            }


            /////////

            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
            
            if(this.funias.getId()!=null && this.funias.getId()>0){
                this.funias = uad.updateFunias(this.funias);
            }else{
                this.funias.setCodigoAutoridad(usuarioConectado.getUsuario().
                                       getAutoridadAmbiental().getId().longValue());
                this.funias.setIdCaptacion(this.captacion.getCodigo());
                this.funias.setTipoFunias(ConstantesParametros.LISTA_EXPLOTACION.intValue());

                this.funias = uad.createFunias(funias);    
            }

        }catch(IdeamException e){
        showMessage(e);
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

    public void setPanelGroupLayout1(RichPanelGroupLayout panelGroupLayout1) {
        this.panelGroupLayout1 = panelGroupLayout1;
    }

    public RichPanelGroupLayout getPanelGroupLayout1() {
        return panelGroupLayout1;
    }

    public void setPnlbDatos(RichPanelBox panelBox1) {
        this.pnlbDatos = panelBox1;
    }

    public RichPanelBox getPnlbDatos() {
        return pnlbDatos;
    }

    public void setPanelGroupLayout2(RichPanelGroupLayout panelGroupLayout2) {
        this.panelGroupLayout2 = panelGroupLayout2;
    }

    public RichPanelGroupLayout getPanelGroupLayout2() {
        return panelGroupLayout2;
    }

    public void setPanelFormLayout1(RichPanelFormLayout panelFormLayout1) {
        this.panelFormLayout1 = panelFormLayout1;
    }

    public RichPanelFormLayout getPanelFormLayout1() {
        return panelFormLayout1;
    }

    public void setPanelGroupLayout3(RichPanelGroupLayout panelGroupLayout3) {
        this.panelGroupLayout3 = panelGroupLayout3;
    }

    public RichPanelGroupLayout getPanelGroupLayout3() {
        return panelGroupLayout3;
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

    public void setTable1(RichTable table1) {
        this.table1 = table1;
    }

    public RichTable getTable1() {
        return table1;
    }

    public void setPanelGroupLayout4(RichPanelGroupLayout panelGroupLayout4) {
        this.panelGroupLayout4 = panelGroupLayout4;
    }

    public RichPanelGroupLayout getPanelGroupLayout4() {
        return panelGroupLayout4;
    }

    public void setSpacer1(RichSpacer spacer1) {
        this.spacer1 = spacer1;
    }

    public RichSpacer getSpacer1() {
        return spacer1;
    }

    public void setCommandLink1(RichCommandLink commandLink1) {
        this.commandLink1 = commandLink1;
    }

    public RichCommandLink getCommandLink1() {
        return commandLink1;
    }

    public void setSpacer2(RichSpacer spacer2) {
        this.spacer2 = spacer2;
    }

    public RichSpacer getSpacer2() {
        return spacer2;
    }

    public void setCommandLink2(RichCommandLink commandLink2) {
        this.commandLink2 = commandLink2;
    }

    public RichCommandLink getCommandLink2() {
        return commandLink2;
    }

    public void setSpacer3(RichSpacer spacer3) {
        this.spacer3 = spacer3;
    }

    public RichSpacer getSpacer3() {
        return spacer3;
    }

    public void setCommandLink3(RichCommandLink commandLink3) {
        this.commandLink3 = commandLink3;
    }

    public RichCommandLink getCommandLink3() {
        return commandLink3;
    }

    public void setSpacer4(RichSpacer spacer4) {
        this.spacer4 = spacer4;
    }

    public RichSpacer getSpacer4() {
        return spacer4;
    }

    public void setCommandLink4(RichCommandLink commandLink4) {
        this.commandLink4 = commandLink4;
    }

    public RichCommandLink getCommandLink4() {
        return commandLink4;
    }

    public void setSpacer5(RichSpacer spacer5) {
        this.spacer5 = spacer5;
    }

    public RichSpacer getSpacer5() {
        return spacer5;
    }

    public void setCommandLink5(RichCommandLink commandLink5) {
        this.commandLink5 = commandLink5;
    }

    public RichCommandLink getCommandLink5() {
        return commandLink5;
    }

    public void setSpacer6(RichSpacer spacer6) {
        this.spacer6 = spacer6;
    }

    public RichSpacer getSpacer6() {
        return spacer6;
    }

    public void setCommandLink6(RichCommandLink commandLink6) {
        this.commandLink6 = commandLink6;
    }

    public RichCommandLink getCommandLink6() {
        return commandLink6;
    }

    public void setSpacer7(RichSpacer spacer7) {
        this.spacer7 = spacer7;
    }

    public RichSpacer getSpacer7() {
        return spacer7;
    }

    public void setOutputText3(RichOutputText outputText3) {
        this.outputText3 = outputText3;
    }

    public RichOutputText getOutputText3() {
        return outputText3;
    }


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

    public void setListaMetodoExplotacion(List listaMetodoExplotacion) {
        this.listaMetodoExplotacion = listaMetodoExplotacion;
    }

    public List getListaMetodoExplotacion() {
        return listaMetodoExplotacion;
    }

    public void setListaTipoEnergia(List listaTipoEnergia) {
        this.listaTipoEnergia = listaTipoEnergia;
    }

    public List getListaTipoEnergia() {
        return listaTipoEnergia;
    }

    public void setArchivosRelacionados(boolean archivosRelacionados) {
        this.archivosRelacionados = archivosRelacionados;
    }

    public boolean isArchivosRelacionados() {
        return archivosRelacionados;
    }

    public void setFileBombeo(boolean fileBombeo) {
        this.fileBombeo = fileBombeo;
    }

    public boolean isFileBombeo() {
        this.fileBombeo = false;
        if(this.funias.getSubttFuniasDocumentosList()!=null){
            for(SubttFuniasDocumentos documento: this.funias.getSubttFuniasDocumentosList()){
                if(documento.getTipo().equalsIgnoreCase(ConstantesCaptaciones.ARCHIVO_TIPO_BOMBEO)){
                    this.fileBombeo = true;
                }
            }
        }
        return fileBombeo;
    }

    public void setFilePozo(boolean filePozo) {
        this.filePozo = filePozo;
    }

    public boolean isFilePozo() {
        this.filePozo = false;
        if(this.funias.getSubttFuniasDocumentosList()!=null){
            for(SubttFuniasDocumentos documento: this.funias.getSubttFuniasDocumentosList()){
                if(documento.getTipo().equalsIgnoreCase(ConstantesCaptaciones.ARCHIVO_TIPO_POZO)){
                    this.filePozo = true;
                }
            }
        }
        return filePozo;
    }

    public void setFileFiltro(boolean fileFiltro) {
        this.fileFiltro = fileFiltro;
    }

    public boolean isFileFiltro() {
        this.fileFiltro = false;
        if(this.funias.getSubttFuniasDocumentosList()!=null){
            for(SubttFuniasDocumentos documento: this.funias.getSubttFuniasDocumentosList()){
                if(documento.getTipo().equalsIgnoreCase(ConstantesCaptaciones.ARCHIVO_TIPO_FILTRO)){
                    this.fileFiltro = true;
                }
            }
        }
        return fileFiltro;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile1(UploadedFile uploadedFile1) {
        this.uploadedFile1 = uploadedFile1;
    }

    public UploadedFile getUploadedFile1() {
        return uploadedFile1;
    }

    public void setUploadedFile2(UploadedFile uploadedFile2) {
        this.uploadedFile2 = uploadedFile2;
    }

    public UploadedFile getUploadedFile2() {
        return uploadedFile2;
    }

    public void setArchivoCargado(ArchivoCargadoTO archivoCargado) {
        this.archivoCargado = archivoCargado;
    }

    public ArchivoCargadoTO getArchivoCargado() {
        return archivoCargado;
    }

    public void setArchivosCargados(List<ArchivoCargadoTO> archivosCargados) {
        this.archivosCargados = archivosCargados;
    }

    public List<ArchivoCargadoTO> getArchivosCargados() {
        return archivosCargados;
    }

    public void setDocumentoSeleccionado(SubttFuniasDocumentos documentoSeleccionado) {
        this.documentoSeleccionado = documentoSeleccionado;
    }

    public SubttFuniasDocumentos getDocumentoSeleccionado() {
        return documentoSeleccionado;
    }

    public void setListaArchivosRelacionados(List listaArchivosRelacionados) {
        this.listaArchivosRelacionados = listaArchivosRelacionados;
    }

    public List getListaArchivosRelacionados() {
        return this.listaArchivosRelacionados;
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

    public void setSpacer8(RichSpacer spacer8) {
        this.spacer8 = spacer8;
    }

    public RichSpacer getSpacer8() {
        return spacer8;
    }

    public void setCbAceptar(RichCommandButton commandButton1) {
        this.cbAceptar = commandButton1;
    }

    public RichCommandButton getCbAceptar() {
        return cbAceptar;
    }

    public void setSpacer9(RichSpacer spacer9) {
        this.spacer9 = spacer9;
    }

    public RichSpacer getSpacer9() {
        return spacer9;
    }

    public void setCbBorrar(RichCommandButton commandButton2) {
        this.cbBorrar = commandButton2;
    }

    public RichCommandButton getCbBorrar() {
        return cbBorrar;
    }

    public void setPanelGroupLayout7(RichPanelGroupLayout panelGroupLayout7) {
        this.panelGroupLayout7 = panelGroupLayout7;
    }

    public RichPanelGroupLayout getPanelGroupLayout7() {
        return panelGroupLayout7;
    }

    public void setP_borrar_docto(RichPopup popup1) {
        this.p_borrar_docto = popup1;
    }

    public RichPopup getP_borrar_docto() {
        return p_borrar_docto;
    }

    public void setPopup_borrar(RichPopup popup1) {
        this.popup_borrar = popup1;
    }

    public RichPopup getPopup_borrar() {
        return popup_borrar;
    }

    public void setP_registro_exitoso(RichPopup popup1) {
        this.p_registro_exitoso = popup1;
    }

    public RichPopup getP_registro_exitoso() {
        return p_registro_exitoso;
    }

    public void setD_borrar_docto(RichDialog dialog1) {
        this.d_borrar_docto = dialog1;
    }

    public RichDialog getD_borrar_docto() {
        return d_borrar_docto;
    }

    public void setD_borrar(RichDialog dialog2) {
        this.d_borrar = dialog2;
    }

    public RichDialog getD_borrar() {
        return d_borrar;
    }

    public void setD_registro_exitoso(RichDialog dialog3) {
        this.d_registro_exitoso = dialog3;
    }

    public RichDialog getD_registro_exitoso() {
        return d_registro_exitoso;
    }

    public void setOt100(RichOutputText outputText4) {
        this.ot100 = outputText4;
    }

    public RichOutputText getOt100() {
        return ot100;
    }

    public void setCbAceptarDi(RichCommandButton commandButton1) {
        this.cbAceptarDi = commandButton1;
    }

    public RichCommandButton getCbAceptarDi() {
        return cbAceptarDi;
    }

    public void setPanelStretchLayout2(RichPanelStretchLayout panelStretchLayout2) {
        this.panelStretchLayout2 = panelStretchLayout2;
    }

    public RichPanelStretchLayout getPanelStretchLayout2() {
        return panelStretchLayout2;
    }

    public void setOt8(RichOutputText outputText4) {
        this.ot8 = outputText4;
    }

    public RichOutputText getOt8() {
        return ot8;
    }

    public void setI1(RichImage image1) {
        this.i1 = image1;
    }

    public RichImage getI1() {
        return i1;
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

    public void setCb_si_borrar(RichCommandButton commandButton1) {
        this.cb_si_borrar = commandButton1;
    }

    public RichCommandButton getCb_si_borrar() {
        return cb_si_borrar;
    }

    public void setSpacer10(RichSpacer spacer10) {
        this.spacer10 = spacer10;
    }

    public RichSpacer getSpacer10() {
        return spacer10;
    }


    public void setCb_no_borrar(RichCommandButton commandButton2) {
        this.cb_no_borrar = commandButton2;
    }

    public RichCommandButton getCb_no_borrar() {
        return cb_no_borrar;
    }

    public void setOt_borrar_1(RichOutputText outputText4) {
        this.ot_borrar_1 = outputText4;
    }

    public RichOutputText getOt_borrar_1() {
        return ot_borrar_1;
    }

    public void setSpacer11(RichSpacer spacer11) {
        this.spacer11 = spacer11;
    }

    public RichSpacer getSpacer11() {
        return spacer11;
    }

    public void setOt_borrar_2(RichOutputText outputText5) {
        this.ot_borrar_2 = outputText5;
    }

    public RichOutputText getOt_borrar_2() {
        return ot_borrar_2;
    }


    public void setSpacer12(RichSpacer spacer12) {
        this.spacer12 = spacer12;
    }

    public RichSpacer getSpacer12() {
        return spacer12;
    }

    public void setOtBasicos(RichOutputText outputText5) {
        this.otBasicos = outputText5;
    }

    public RichOutputText getOtBasicos() {
        return otBasicos;
    }

    public void setSocMetodoExplotacion(RichSelectOneChoice selectOneChoice1) {
        this.socMetodoExplotacion = selectOneChoice1;
    }

    public RichSelectOneChoice getSocMetodoExplotacion() {
        return socMetodoExplotacion;
    }

    public void setSiMetodoExplotacion(UISelectItems selectItems1) {
        this.siMetodoExplotacion = selectItems1;
    }

    public UISelectItems getSiMetodoExplotacion() {
        return siMetodoExplotacion;
    }

    public void setSocTipoEnergia(RichSelectOneChoice selectOneChoice2) {
        this.socTipoEnergia = selectOneChoice2;
    }

    public RichSelectOneChoice getSocTipoEnergia() {
        return socTipoEnergia;
    }

    public void setSiTipoEnergia(UISelectItems selectItems2) {
        this.siTipoEnergia = selectItems2;
    }

    public UISelectItems getSiTipoEnergia() {
        return siTipoEnergia;
    }

    public void setSpacer13(RichSpacer spacer13) {
        this.spacer13 = spacer13;
    }

    public RichSpacer getSpacer13() {
        return spacer13;
    }

    public void setOtTuberiaExplotacion(RichOutputText outputText6) {
        this.otTuberiaExplotacion = outputText6;
    }

    public RichOutputText getOtTuberiaExplotacion() {
        return otTuberiaExplotacion;
    }

    public void setItDiametro(RichInputText inputText1) {
        this.itDiametro = inputText1;
    }

    public RichInputText getItDiametro() {
        return itDiametro;
    }

    public void setItMaterial(RichInputText inputText2) {
        this.itMaterial = inputText2;
    }

    public RichInputText getItMaterial() {
        return itMaterial;
    }

    public void setItLongitud(RichInputText inputText3) {
        this.itLongitud = inputText3;
    }

    public RichInputText getItLongitud() {
        return itLongitud;
    }

    public void setSpacer14(RichSpacer spacer14) {
        this.spacer14 = spacer14;
    }

    public RichSpacer getSpacer14() {
        return spacer14;
    }

    public void setOtBomba(RichOutputText outputText7) {
        this.otBomba = outputText7;
    }

    public RichOutputText getOtBomba() {
        return otBomba;
    }

    public void setItClasebomba(RichInputText inputText1) {
        this.itClasebomba = inputText1;
    }

    public RichInputText getItClasebomba() {
        return itClasebomba;
    }

    public void setItModeloBomba(RichInputText inputText2) {
        this.itModeloBomba = inputText2;
    }

    public RichInputText getItModeloBomba() {
        return itModeloBomba;
    }

    public void setItPotenciaBomba(RichInputText inputText3) {
        this.itPotenciaBomba = inputText3;
    }

    public RichInputText getItPotenciaBomba() {
        return itPotenciaBomba;
    }

    public void setItProfundidadSuccion(RichInputText inputText4) {
        this.itProfundidadSuccion = inputText4;
    }

    public RichInputText getItProfundidadSuccion() {
        return itProfundidadSuccion;
    }

    public void setOtDocumentos(RichOutputText outputText5) {
        this.otDocumentos = outputText5;
    }

    public RichOutputText getOtDocumentos() {
        return otDocumentos;
    }

    public void setSpacer15(RichSpacer spacer15) {
        this.spacer15 = spacer15;
    }

    public RichSpacer getSpacer15() {
        return spacer15;
    }

    public void setIfDocumentoBombeo(RichInputFile inputFile1) {
        this.ifDocumentoBombeo = inputFile1;
    }

    public RichInputFile getIfDocumentoBombeo() {
        return ifDocumentoBombeo;
    }

    public void setIfDocumentoFiltros(RichInputFile inputFile2) {
        this.ifDocumentoFiltros = inputFile2;
    }

    public RichInputFile getIfDocumentoFiltros() {
        return ifDocumentoFiltros;
    }

    public void setIfDocumentoPozo(RichInputFile inputFile3) {
        this.ifDocumentoPozo = inputFile3;
    }

    public RichInputFile getIfDocumentoPozo() {
        return ifDocumentoPozo;
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

    public void setPanelGroupLayout10(RichPanelGroupLayout panelGroupLayout10) {
        this.panelGroupLayout10 = panelGroupLayout10;
    }

    public RichPanelGroupLayout getPanelGroupLayout10() {
        return panelGroupLayout10;
    }

}
