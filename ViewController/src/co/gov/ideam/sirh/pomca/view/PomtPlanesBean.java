package co.gov.ideam.sirh.pomca.view;


import co.gov.ideam.sirh.documentos.model.Archivo;
import co.gov.ideam.sirh.documentos.model.Nodo;
import co.gov.ideam.sirh.documentos.view.DocumentosBean;
import co.gov.ideam.sirh.documentos.view.DocumentosDelegate;
import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Categoria;
import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.view.ParametrosDelegate;
import co.gov.ideam.sirh.pomca.model.Cuenca;
import co.gov.ideam.sirh.pomca.model.DeterminanteAmbiental;
import co.gov.ideam.sirh.pomca.model.PomtArchivosXDeterminantes;
import co.gov.ideam.sirh.pomca.model.PomtArchivosXPlan;
import co.gov.ideam.sirh.pomca.model.PomtComisiones;
import co.gov.ideam.sirh.pomca.model.PomtComisionesTO;
import co.gov.ideam.sirh.pomca.model.PomtComunidadesEtnicas;
import co.gov.ideam.sirh.pomca.model.PomtInstrumentosPlanificacion;
import co.gov.ideam.sirh.pomca.model.PomtJurisdiccion;
import co.gov.ideam.sirh.pomca.model.PomtJurisdiccionesTO;
import co.gov.ideam.sirh.pomca.model.PomtPlanes;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.util.Constantes;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;

import co.gov.ideam.sirh.view.util.ArchivoCargadoTO;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import javax.imageio.ImageIO;

import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputFile;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichCommandMenuItem;
import oracle.adf.view.rich.component.rich.output.RichImage;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;

import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.model.UploadedFile;

import sun.misc.BASE64Encoder;


/********/


/*******/


public class PomtPlanesBean extends StandarBean {

    private RichDocument document1;
    private RichForm form1;
    private UploadedFile uploadedFile;
    private ArchivoCargadoTO archivoCargado;
    
    
    private RichCommandMenuItem commandMenuItem1;
    private RichCommandMenuItem commandMenuItem2;

    private Cuenca cuenca;
    private String accion;
    private RichPopup p1;
    private RichCommandButton cb_regresar;

    /* PLANES       *********************************/
    private PomtPlanes plan;
    private List<PomtPlanes> listaPlanes;
    /* JURISDICCIONES  ******************************/
    private RichTable t_jurisdiccion;
    private PomtJurisdiccion jurisdiccionSelected;

    /* COMISIONES      ******************************/
    private RichTable t_comision;
    private PomtComisiones comisionSelected;

    /* INSTRUMENTOS     *****************************/
    private RichTable t_instrumentos;
    private PomtInstrumentosPlanificacion instrumentosSelected;

    /* COMUNIDADES     *****************************/
    private RichTable t_comunidades;
    private PomtComunidadesEtnicas comunidadesSelected;

    //ZSDG
    private RichTable t_determinantes;
    private DeterminanteAmbiental determinantesSelected;
    private List componentesAfectados;
    private List recursosAfectados;
    private RichSelectOneChoice st_componente_det;
    private UISelectItems selectItemsCompD;
    private RichSelectOneChoice st_recurso_det;
    private UISelectItems selectItemsRecurD;
    private RichInputFile if_archivoDet;
   //ZSDG
 
    /* LISTAS Y PARAMETROS *************************/
    
    private List listaDepartamentos;
    private List listaMunicipios;
    private List listaAutoridades;

    private List listaDepartamentosCargados;
    private List listaMunicipiosCargados;

    private RichSelectOneChoice soc_depto;
    private UISelectItems si1;
    private RichSelectOneChoice soc_municipio;
    private RichSelectOneChoice soc_autoridad;
    private UISelectItems si2;
    private UISelectItems siAutoridades;
    private Divipola DepartamentoSelected;
    private Divipola MunicipioSelected;
    private Autoridades autoridadSelected;

    private List<PomtJurisdiccionesTO> jurisdiccionesXPlan;
    private List<PomtComisionesTO> comisionesXPlan;

    private RichSelectBooleanCheckbox faseElabAprestamChk;
    private RichSelectBooleanCheckbox faseElabDiagnostChk;
    private RichSelectBooleanCheckbox faseElabZonifChk;
    private RichSelectBooleanCheckbox faseElabFormulacChk;
    private RichSelectBooleanCheckbox faseElabEjecuChk;
    private RichSelectBooleanCheckbox faseElabSegChk;

    private RichSelectBooleanCheckbox procPreConsultaChk;
    private RichSelectBooleanCheckbox procConsultaChk;


    /**********************************/
    private RichPopup popup1;
    private RichPopup popupExito;
    private RichInputFile if_dcoumento;
 
    private RichInputFile if_mapaResultante;
    private RichInputFile if_infoZonif;
    private RichImage image1;
    
    private RichPanelFormLayout panelFormLayoutImagen;


    public PomtPlanesBean() throws IOException {
        System.out.println("XXXXXXXXXXXX CONSTRUCTOR  DE PomtPlanesBean     XXXXXXXXXXXXXXXXXXXXXXXXX ");
        FacesUtils.setActiveBean("planesBean", "Planes", PomcaDelegate.class);
        this.load();
    }

    public void load() throws IOException {
        System.out.println("XXXXXXXXXXXX LOAD DE PomtPlanesBean     XXXXXXXXXXXXXXXXXXXXXXXXX ");
       ///this.if_dcoumento.resetValue();
        try {
            Object obj = FacesUtils.getFromSession("cuencaSeleccionada");
            PomcaDelegate pomcad = PomcaDelegate.getInstance();
            if(obj instanceof Cuenca){
                this.cuenca=(Cuenca)obj;
                System.out.println(">>>>>> PomtPlanesBean CUENCA>" +this.cuenca.getId());
            }else
                    System.out.println(">>>>>> PomtPlanesBean NO VIENE UNA CUENCA>" );
            this.plan = new PomtPlanes();
            this.jurisdiccionSelected = new PomtJurisdiccion();
            this.comisionSelected = new PomtComisiones();
            this.instrumentosSelected = new PomtInstrumentosPlanificacion();
            this.comunidadesSelected = new PomtComunidadesEtnicas();
            this.determinantesSelected = new DeterminanteAmbiental();
            this.componentesAfectados = this.getListaPorCategoria(Categoria.COMPONENTE_AFECTADO_DET);//ZSDG
            this.recursosAfectados = this.getListaPorCategoria(Categoria.RECURSO_AFECTADO_DET);//ZSDG
            if(image1!=null)
              image1.setVisible(false);
            
                UsuarioConectadoTO usuarioConectado =  (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
                Autoridades autoridadAmbiental =  usuarioConectado.getUsuario().getAutoridadAmbiental();
                this.plan.setCodigoAutoridad(new Long(usuarioConectado.getUsuario().getAutoridadAmbiental().getId()));
                System.out.println(">>>>>>CODIGO AUTORIDAD AMBIENTAL>>>>>>>>>>" +
                                   this.plan.getCodigoAutoridad());
                if (autoridadAmbiental == null ||
                    autoridadAmbiental.getId().intValue() == Constantes.IDEAM) {
                    showMessage(getText("NO_EXISTE_AUTORIDAD_AMBIENTAL"),
                                FacesMessage.SEVERITY_ERROR);
                    return;
                }


            /****************************/
           
            
           
            
            this.listaAutoridades = this.cargarAutoridades();

            if (this.cuenca != null) {
                System.out.println(">>>>>>CUENCA>" +this.cuenca.getId());
                this.plan = pomcad.getPlanesByIdCuenca(this.cuenca.getId());
               
                if(this.plan !=null){
                    System.out.println(">>>>>>PLAN>" +this.plan.getId());
                    this.jurisdiccionesXPlan = new ArrayList();
                    this.procesarJurisdicciones(this);                    
                    this.comisionesXPlan = new ArrayList();                    
                    this.procesarComisiones(this);                    
                }

            } else {
                this.plan = new PomtPlanes();
            }
            
           
            FacesUtils.setInSession("plan", this.plan);


            //this.listaDepartamentos = this.cargarDivipolaSinRestriccion(null);
            this.listaDepartamentos = this.cargarDivipolaSinRestriccion(null);
            if (this.jurisdiccionSelected.getId_departamento() != null) {
                this.listaMunicipios =
                        this.cargarDivipolaSinRestriccion(this.jurisdiccionSelected.getId_departamento());

            } else {
                this.listaMunicipios = new ArrayList();
            }

            /****************************/

            if (this.plan == null) {
                this.plan = new PomtPlanes();
            }

            this.procesarFacesProyectoOut();
            //this.procesarComunidadesEtnicasOut();

        } catch (IdeamException e) {
            showMessage(e);
        }
    }
    public void cl_documento_actionListener(ActionEvent actionEvent) throws IdeamException {
        DocumentosDelegate docd = DocumentosDelegate.getInstance();   
        Nodo nodoFile = docd.buscarNodo(plan.getMapa_resultante_zonif());
        Archivo aux = docd.descargarArchivo(nodoFile);
        System.out.println("----------------------ARCHIVO A DESCARGAR:"+aux.getArchivo());
        showImage(aux.getArchivo());
    }

   /**
    * Este metodo permite enlazar el archivo cargado al "Plan" para este caso, en la tabla que relaciona 
    * 1 a 1 el modulo local con el modulo de archivos
    *
    */
    public void asociarArchivosPlan(Nodo nodo) throws IdeamException{
        //Amarra el archivo cargado al modulo o tabla o paquete que asocia el archivo con la appp
        PomcaDelegate pomcad = PomcaDelegate.getInstance();
        PomtArchivosXPlan archiPlan = new PomtArchivosXPlan();
        System.out.println("Se genera un PomtArchivosXPlan para le nodo:"+ nodo.getId());
        archiPlan.setId(nodo.getId());
        archiPlan.setFechaCreacion(nodo.getFechaCreacion());
        archiPlan.setPomtPlanes(this.plan);
        pomcad.updateArchivosXPlan(archiPlan);
    }

    /**
     * Este metodo es quien se encarga de recibir del componente el Byte[] y lo carga en el sistema
     * respondiendo con el ID del archivo para que sea cargado en el valor del atributo necesario del 
     * bean usado, se debe usar uno por cada componente de carga en el sistema.
     */
    public void if_archivo_valueChangeListener(ValueChangeEvent valueChangeEvent) throws IdeamException{
        UploadedFile file = (UploadedFile)valueChangeEvent.getNewValue();
        DocumentosBean dbean = new DocumentosBean();
       
        if (file!=null){
             this.setUploadedFile(file);

            //Llena el nodo, el archivo y develve la instancia propia del mismo., llevarle el id para que sepa que existe o no
            //con lo cual sobre escribe el archivo y no crea un nuevo archivo.
            Nodo nodo = dbean.setUploadedFilePlan(file, this.plan.getId_archivo(), this.plan.getCodigoAutoridad());
            System.out.println("Nodo asociado al Plan para archivo:"+ nodo.getDescripcion());
            //Nodo nodo = dbean.setUploadedFile(file, null);
            //Amarra el archivo cargado al modulo o tabla o paquete que asocia el archivo con la appp
            this.asociarArchivosPlan(nodo);
            image1.setVisible(true);
            this.plan.setId_archivo(nodo.getId());
            PomcaDelegate pomcad = PomcaDelegate.getInstance();
            pomcad.updatePomtPlanes(this.getPlan());
             
            AdfFacesContext.getCurrentInstance().addPartialTarget(panelFormLayoutImagen);
         }
    }
   
    /*********************************** MAPA RESULTANTE **************************************/
    public void if_mapaRes_valueChangeListener(ValueChangeEvent valueChangeEvent) throws IdeamException{
        UploadedFile file = (UploadedFile)valueChangeEvent.getNewValue();
        DocumentosBean dbean = new DocumentosBean();
        if (file!=null){
            Nodo nodo = dbean.setUploadedFile(file, this.plan.getMapa_resultante_zonif());
            this.asociarArchivosPlan(nodo);
            this.plan.setMapa_resultante_zonif(nodo.getId());
             PomcaDelegate pomcad = PomcaDelegate.getInstance();
             pomcad.updatePomtPlanes(this.getPlan());
         }
    }
    
    
    /*********************************** INFORME ZONIFICACION*************************************/
    public void if_infoZonif_valueChangeListener(ValueChangeEvent valueChangeEvent) throws IdeamException{
        UploadedFile file = (UploadedFile)valueChangeEvent.getNewValue();
        DocumentosBean dbean = new DocumentosBean();
        if (file!=null){
            Nodo nodo = dbean.setUploadedFile(file, this.plan.getInfo_proceso_zonif());
            this.asociarArchivosPlan(nodo);
            this.plan.setInfo_proceso_zonif(nodo.getId());
             PomcaDelegate pomcad = PomcaDelegate.getInstance();
             pomcad.updatePomtPlanes(this.getPlan());
         }
    }
    
    /******************************************************************************************/
  
    public void cb_borrar_plan_actionListener(ActionEvent actionEvent) {
        showPopup(popup1, true);
        
    }
     
    
    public void borrar_plan() throws IdeamException{
        PomcaDelegate pomcad = PomcaDelegate.getInstance();
        try{
            pomcad.removePomtPlan(this.plan);
            this.cuenca = (Cuenca)FacesUtils.getFromSession("cuencaSeleccionada");
            this.plan = new PomtPlanes();
            this.jurisdiccionSelected = new PomtJurisdiccion();
            this.comisionSelected = new PomtComisiones();
            this.instrumentosSelected = new PomtInstrumentosPlanificacion();
            this.comunidadesSelected = new PomtComunidadesEtnicas();
            this.determinantesSelected = new DeterminanteAmbiental();
            this.setAccion("cuencas");
        }catch(Exception e){
            System.out.println("No hay un plan que borrar.");  
            this.setAccion("cuencas");
        }
        
        showPopup(popupExito, true);
    }
    
    public void d_borrar_plan_dialogListener(DialogEvent dialogEvent) throws IdeamException {
        this.borrar_plan();   
    }
    
    
    public void borrar_actionListener(ActionEvent actionEvent) throws IdeamException{
        this.borrar_plan();
    }
    
    public void soc_depto_valueChangeListener(ValueChangeEvent valueChangeEvent) {
        Divipola depto = (Divipola)valueChangeEvent.getNewValue();
        try {
            if (depto != null) {
                this.listaMunicipios =
                        this.cargarDivipolaSinRestriccion(depto.getId());
            } else {
                this.listaMunicipios = new ArrayList();
            }
        } catch (IdeamException e) {
            showMessage(e);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(getSoc_municipio());
    }

    public void agregarJurisdiccion_actionListener(ActionEvent actionEvent) throws IdeamException{
        PomtJurisdiccion juris = new PomtJurisdiccion();
        juris.setId_departamento(this.getDepartamentoSelected().getId());
        juris.setId_municipio(this.getMunicipioSelected().getId());
        juris.setCodigoAutoridad(this.plan.getCodigoAutoridad());
        juris = this.plan.addPomtJurisdiccion(juris);
        
        PomcaDelegate pomcad = PomcaDelegate.getInstance();
        pomcad.updatePomtJurisdiccion(juris, this.plan.getCodigoAutoridad());
        procesarJurisdicciones(this); //Refrescar Lista de Jurisdicciones
        showPopup(popupExito, true);
    }

    public void agregarInstrumento_actionListener(ActionEvent actionEvent) throws IdeamException{
        PomtInstrumentosPlanificacion instru =
            new PomtInstrumentosPlanificacion();
        instru.setCodigoAutoridad(this.plan.getCodigoAutoridad());
        instru.setInstrumentoPlanificacion(this.instrumentosSelected.getInstrumentoPlanificacion());
        instru.setRecursoNaturalIdent(this.instrumentosSelected.getRecursoNaturalIdent());

        instru = this.plan.addPomtInstrumentosPlanificacion(instru);
        instru.setCodigoAutoridad(this.plan.getCodigoAutoridad());

            PomcaDelegate pomcad = PomcaDelegate.getInstance();
            pomcad.updatePomtInstrumentosPlanificacion(instru);
        showPopup(popupExito, true);
    }

    public void agregarComunidad_actionListener(ActionEvent actionEvent) throws IdeamException {

        this.procesarComunidadesEtnicasIn();

        PomtComunidadesEtnicas comunis = new PomtComunidadesEtnicas();
        comunis.setCodigoAutoridad(this.plan.getCodigoAutoridad());
        comunis.setNombreComunidad(this.comunidadesSelected.getNombreComunidad());
        comunis.setProcConsulta(this.comunidadesSelected.getProcConsulta());
        comunis.setProcPreConsulta(this.comunidadesSelected.getProcPreConsulta());
        comunis = this.plan.addPomtComunidadesEtnicas(comunis);
        
            PomcaDelegate pomcad = PomcaDelegate.getInstance();
            pomcad.updatePomtComunidadesEtnicas(comunis);
            //this.procesarComunidadesEtnicasOut();
         showPopup(popupExito, true);
    }
//ZSDG IN
    public void agregarDeterminante_actionListener(ActionEvent actionEvent) throws IdeamException {
     // this.procesarComunidadesEtnicasIn();
     System.out.println("entro al metodo agregar determinante");
      DeterminanteAmbiental determinante = new DeterminanteAmbiental();
      determinante.setCodigoAutoridad(this.plan.getCodigoAutoridad());
      System.out.println("nombre determinante " + this.determinantesSelected.getNombre());
      determinante.setNombre(this.determinantesSelected.getNombre());
      determinante.setDescripcion(this.determinantesSelected.getDescripcion());
      determinante.setIndicadordaId(this.determinantesSelected.getIndicadordaId());
      determinante.setComponenteAfectadoId(this.determinantesSelected.getComponenteAfectadoId());
      determinante.setRecursoAfectadoId(this.determinantesSelected.getRecursoAfectadoId());
      System.out.println("Archivo Agregar Selected Determinante ->" + this.determinantesSelected.getArchivo());
      determinante.setArchivo(this.determinantesSelected.getArchivo());    
      determinante = this.plan.addDeterminanteAmbiental(determinante);      
      PomcaDelegate pomcad = PomcaDelegate.getInstance();
      pomcad.updateDeterminanteAmbiental(determinante);
      //this.procesarComunidadesEtnicasOut();
       showPopup(popupExito, true);
  }
    
  public void eliminarDeterminantes_actionListener(ActionEvent actionEvent) {
      DeterminanteAmbiental determinantes = new DeterminanteAmbiental();
      RichTable tabla =
          (RichTable)actionEvent.getComponent().getParent().getParent();
      determinantes = (DeterminanteAmbiental)tabla.getSelectedRowData();
      determinantes = this.plan.removeDeterminanteAmbiental(determinantes);
      try {
          PomcaDelegate pomcad = PomcaDelegate.getInstance();
          pomcad.deleteDeterminante(determinantes);
      } catch (IdeamException e) {
          showMessage(e);
      }
  }
  
  public void setSt_componente_det(RichSelectOneChoice st_componente_det) {
      this.st_componente_det = st_componente_det;
  }

  public RichSelectOneChoice getSt_componente_det() {
      return st_componente_det;
  }
  
  public void setSt_recurso_det(RichSelectOneChoice st_recurso_det) {
    this.st_recurso_det = st_recurso_det;
  }

  public RichSelectOneChoice getSt_recurso_det() {
    return st_recurso_det;
  }

  public void setSelectItemsRecurD(UISelectItems selectItemsRecurD) {
    this.selectItemsRecurD = selectItemsRecurD;
  }

  public UISelectItems getSelectItemsRecurD() {
    return selectItemsRecurD;
  }
  
  public void sc_componente_valueChangeListener(ValueChangeEvent valueChangeEvent) {
    valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
    //this.determinantesSelected.getLComponentes()
    //this. determinantesSelected.setLComponentes((Lista)this.st_componente_det.getValue());
  }
  
  public void sc_recurso_valueChangeListener(ValueChangeEvent valueChangeEvent) {
    valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
    //this.determinantesSelected.getLComponentes()
    //this. determinantesSelected.setLComponentes((Lista)this.st_componente_det.getValue());
  }
  
  //ARCHIVOS
  /*********************************** ARCHIVO DETERMINANTE **************************************/
  public void if_archDet_valueChangeListener(ValueChangeEvent valueChangeEvent) throws IdeamException{
      UploadedFile file = (UploadedFile)valueChangeEvent.getNewValue();
      DocumentosBean dbean = new DocumentosBean();
      System.out.println("Entre a la carga del archivo ->" + file.getContentType());
      if (file!=null){
          Nodo nodo = dbean.setUploadedFile(file, this.determinantesSelected.getArchivo());
          this.asociarArchivosDeterminante(nodo);
          this.determinantesSelected.setArchivo(nodo.getId());
          PomcaDelegate pomcad = PomcaDelegate.getInstance();
          System.out.println("Archivo Selected Determinante ->" + this.determinantesSelected.getArchivo() + " id determinante : " + this.determinantesSelected.getId());
          pomcad.updateDeterminanteAmbiental(this.getDeterminantesSelected());
       }
  }
  
  public void asociarArchivosDeterminante(Nodo nodo) throws IdeamException{
      //Amarra el archivo cargado al modulo o tabla o paquete que asocia el archivo con la appp
      PomcaDelegate pomcad = PomcaDelegate.getInstance();
      PomtArchivosXDeterminantes archiDeterminante = new PomtArchivosXDeterminantes();
      System.out.println("Se genera un PomtArchivosXDeterminante para el nodo:"+ nodo.getId());
      archiDeterminante.setId(nodo.getId());
      archiDeterminante.setFechaCreacion(nodo.getFechaCreacion());
      archiDeterminante.setDeterminantes(this.determinantesSelected);
      pomcad.updateArchivosXDeterminante(archiDeterminante);
  }
  
      public void t_determinantes_selectionListener(SelectionEvent selectionEvent) {
          this.setT_determinantes((RichTable)selectionEvent.getSource());
          this.setDeterminantesSelected((DeterminanteAmbiental)this.getT_determinantes().getSelectedRowData());
          FacesUtils.setInSession("determinanteSeleccionada", this.getDeterminantesSelected());
      }

//ZSDG
    public void agregarComision_actionListener(ActionEvent actionEvent) throws IdeamException {

        PomtComisiones comi = new PomtComisiones();
        comi.setId_autoridad(this.autoridadSelected.getId());
        comi.setCodigoAutoridad(this.plan.getCodigoAutoridad());

        comi = this.plan.addPomtComisiones(comi);
       
            PomcaDelegate pomcad = PomcaDelegate.getInstance();
            pomcad.updatePomtComisiones(comi);
            procesarComisiones(this);
            //procesarJurisdicciones(this); //Refrescar Lista de Jurisdicciones
            showPopup(popupExito, true);
        
    }


    public void eliminarComision_actionListener(ActionEvent actionEvent) throws IdeamException {
        PomtComisiones comi = new PomtComisiones();
        PomtComisionesTO comiTO = new PomtComisionesTO();
        RichTable tabla =
            (RichTable)actionEvent.getComponent().getParent().getParent();
        comiTO = (PomtComisionesTO)tabla.getSelectedRowData();
        comi = comiTO.getComision();
        comi = this.plan.removePomtComisiones(comi);
        PomcaDelegate pomcad = PomcaDelegate.getInstance();
            pomcad.deletePomtComisiones(comi);
            this.procesarComisiones(this);
        
    }

    public void eliminarComunidades_actionListener(ActionEvent actionEvent) {
        PomtComunidadesEtnicas comunidades = new PomtComunidadesEtnicas();
        RichTable tabla =
            (RichTable)actionEvent.getComponent().getParent().getParent();
        comunidades = (PomtComunidadesEtnicas)tabla.getSelectedRowData();
        comunidades = this.plan.removePomtComunidadesEtnicas(comunidades);
        try {
            PomcaDelegate pomcad = PomcaDelegate.getInstance();
            pomcad.deletePomtComunidadesEtnicas(comunidades);
        } catch (IdeamException e) {
            showMessage(e);
        }
    }

    public void eliminarJurisdicciones_actionListener(ActionEvent actionEvent) throws IdeamException {
        PomtJurisdiccionesTO jurisTo = new PomtJurisdiccionesTO();
        PomtJurisdiccion jurisdiccion = new PomtJurisdiccion();
        RichTable tabla = (RichTable)actionEvent.getComponent().getParent().getParent();
        jurisTo = (PomtJurisdiccionesTO)tabla.getSelectedRowData();
        jurisdiccion = jurisTo.getJurisdiccion();
        jurisdiccion = this.plan.removePomtJurisdiccion(jurisdiccion);
        System.out.println("Minicipio a desvincular:"+jurisdiccion.getId());
        PomcaDelegate pomcad = PomcaDelegate.getInstance();
        pomcad.deletePomtJurisdiccion(jurisdiccion);
        this.procesarJurisdicciones(this);
        
    }

    public void eliminarInstrumentos_actionListener(ActionEvent actionEvent) throws IdeamException {
        PomtInstrumentosPlanificacion instrumento = new PomtInstrumentosPlanificacion();
        RichTable tabla = (RichTable)actionEvent.getComponent().getParent().getParent();
        instrumento = (PomtInstrumentosPlanificacion)tabla.getSelectedRowData();
        instrumento = this.plan.removePomtInstrumentosPlanificacion(instrumento);
       
        PomcaDelegate pomcad = PomcaDelegate.getInstance();
        pomcad.deletePomtInstrumentosPlanificacion(instrumento);
    }

    public void procesarComisiones(PomtPlanesBean mBean) throws IdeamException {

        if (mBean.getPlan() != null &&
            mBean.getPlan().getPomtComisionesList() != null &&
            mBean.getPlan().getPomtComisionesList().size() > 0) {
            this.comisionesXPlan = new ArrayList();
            for (PomtComisiones comi :
                 mBean.getPlan().getPomtComisionesList()) {
                PomtComisionesTO comiTO = new PomtComisionesTO();
                comiTO.setComision(comi);
                Autoridades aut =
                    this.cargarAutoridadById(comi.getId_autoridad());
                if (aut != null) {
                    comiTO.setNombreAutoridad(aut.getNombre());
                    comisionesXPlan.add(comiTO);
                }
            }
        }
    }


    public void procesarJurisdicciones(PomtPlanesBean mBean) throws IdeamException {
        List todoDiviPola = mBean.cargarDivipolaSinRestriccion(null);
        this.jurisdiccionesXPlan = new ArrayList();
        PomcaDelegate pomcad = PomcaDelegate.getInstance();
        PomtPlanes planux=pomcad.getPlanesByIdCuenca(this.cuenca.getId());
        for (PomtJurisdiccion juris : planux.getPomtJurisdiccionList()) {
            PomtJurisdiccionesTO aux = new PomtJurisdiccionesTO();
            aux.setJurisdiccion(juris);
            juris.setCodigoAutoridad(planux.getCodigoAutoridad());
            for (int j = 0; j < todoDiviPola.size(); j++) {
                SelectItem item = (SelectItem)todoDiviPola.get(j);
                Divipola div = (Divipola)item.getValue();
                if (div.getId().equals(juris.getId_departamento())) {
                    aux.setDepartamento(div.getNombre());
                    List municipios =
                        mBean.cargarDivipolaSinRestriccion(div.getId());
                    for (int x = 0; x < municipios.size(); x++) {
                        SelectItem mItem = (SelectItem)municipios.get(x);
                        Divipola mDiv = (Divipola)mItem.getValue();
                        if (mDiv.getId().equals(juris.getId_municipio())) {
                            aux.setMunicipio(mDiv.getNombre());
                        }
                    }
                }
            }
            this.jurisdiccionesXPlan.add(aux);

        }
    }

    public void procesarFacesProyectoOut() {
        this.faseElabAprestamChk = new RichSelectBooleanCheckbox();
        this.faseElabDiagnostChk = new RichSelectBooleanCheckbox();
        this.faseElabZonifChk = new RichSelectBooleanCheckbox();
        this.faseElabFormulacChk = new RichSelectBooleanCheckbox();
        this.faseElabEjecuChk = new RichSelectBooleanCheckbox();
        this.faseElabSegChk = new RichSelectBooleanCheckbox();

        if ((this.plan != null) && (this.plan.getFaseElabAprestam() != null) &&
            (this.plan.getFaseElabAprestam().equalsIgnoreCase("Si"))) {

            this.getFaseElabAprestamChk().setSelected(true);
        } else {
            this.getFaseElabAprestamChk().setSelected(false);
        }

        if ((this.plan != null) && (this.plan.getFaseElabDiagnost() != null) &&
            (this.plan.getFaseElabDiagnost().equalsIgnoreCase("Si"))) {

            this.getFaseElabDiagnostChk().setSelected(true);
        } else {
            this.getFaseElabDiagnostChk().setSelected(false);
        }

        if ((this.plan != null) && (this.plan.getFaseElabZonif() != null) &&
            (this.plan.getFaseElabZonif().equalsIgnoreCase("Si"))) {

            this.getFaseElabZonifChk().setSelected(true);
        } else {
            this.getFaseElabZonifChk().setSelected(false);
        }

        if ((this.plan != null) && (this.plan.getFaseElabFormulac() != null) &&
            (this.plan.getFaseElabFormulac().equalsIgnoreCase("Si"))) {

            this.getFaseElabFormulacChk().setSelected(true);
        } else {
            this.getFaseElabFormulacChk().setSelected(false);
        }

        if ((this.plan != null) && (this.plan.getFaseElabEjecu() != null) &&
            (this.plan.getFaseElabEjecu().equalsIgnoreCase("Si"))) {

            this.getFaseElabEjecuChk().setSelected(true);
        } else {
            this.getFaseElabEjecuChk().setSelected(false);
        }

        if ((this.plan != null) && (this.plan.getFaseElabSeg() != null) &&
            (this.plan.getFaseElabSeg().equalsIgnoreCase("Si"))) {

            this.getFaseElabSegChk().setSelected(true);
        } else {
            this.getFaseElabSegChk().setSelected(false);
        }

    }

    public void procesarComunidadesEtnicasOut() {
        this.procConsultaChk = new RichSelectBooleanCheckbox();
        this.procPreConsultaChk = new RichSelectBooleanCheckbox();

        if (this.comunidadesSelected != null) {
            if (this.comunidadesSelected.getProcConsulta().equalsIgnoreCase("Si")) {
                this.getProcConsultaChk().setSelected(true);
            } else {
                this.getProcConsultaChk().setSelected(false);
            }

            if (this.comunidadesSelected.getProcPreConsulta().equalsIgnoreCase("Si")) {
                this.getProcPreConsultaChk().setSelected(true);
            } else {
                this.getProcPreConsultaChk().setSelected(false);
            }
        }
    }

    public void procesarComunidadesEtnicasIn() {
        if (this.getComunidadesSelected().getProcConsulta().equalsIgnoreCase("true")) {
            this.getComunidadesSelected().setProcConsulta("Si");
        } else {
            this.getComunidadesSelected().setProcConsulta("No");
        }
        if (this.getComunidadesSelected().getProcPreConsulta().equalsIgnoreCase("true")) {
            this.getComunidadesSelected().setProcPreConsulta("Si");
        } else {
            this.getComunidadesSelected().setProcPreConsulta("No");
        }
    }

    public void procesarFacesProyectoIn() {
        if (this.getPlan().getFaseElabAprestam().equalsIgnoreCase("true")) {
            this.getPlan().setFaseElabAprestam("Si");
        } else {
            this.getPlan().setFaseElabAprestam("No");
        }
        if (this.getPlan().getFaseElabDiagnost().equalsIgnoreCase("true")) {
            this.getPlan().setFaseElabDiagnost("Si");
        } else {
            this.getPlan().setFaseElabDiagnost("No");
        }
        if (this.getPlan().getFaseElabZonif().equalsIgnoreCase("true")) {
            this.getPlan().setFaseElabZonif("Si");
        } else {
            this.getPlan().setFaseElabZonif("No");
        }
        if (this.getPlan().getFaseElabEjecu().equalsIgnoreCase("true")) {
            this.getPlan().setFaseElabEjecu("Si");
        } else {
            this.getPlan().setFaseElabEjecu("No");
        }
        if (this.getPlan().getFaseElabFormulac().equalsIgnoreCase("true")) {
            this.getPlan().setFaseElabFormulac("Si");
        } else {
            this.getPlan().setFaseElabFormulac("No");
        }
        if (this.getPlan().getFaseElabSeg().equalsIgnoreCase("true")) {
            this.getPlan().setFaseElabSeg("Si");
        } else {
            this.getPlan().setFaseElabSeg("No");
        }

    }
    
    public void cb_aceptar_actionListener(ActionEvent actionEvent) {
        boolean continuar = true;
        
        if (!continuar) {
            return;
        }
        
        try {
            PomcaDelegate pomcad = PomcaDelegate.getInstance();
            this.procesarFacesProyectoIn();


            this.cuenca = (Cuenca)FacesUtils.getFromSession("cuencaSeleccionada");
            this.getPlan().setId_cuenca(this.cuenca.getId());

			
            UsuarioConectadoTO usuarioConectado =
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
            this.plan.setCodigoAutoridad(new Long(usuarioConectado.getUsuario().getAutoridadAmbiental().getId()));

            pomcad.updatePomtPlanes(this.getPlan());
            this.plan = pomcad.getPlanesByIdCuenca(this.cuenca.getId());


            //Esto es para redesplegar bien los cheks en la pantalla
            this.procesarFacesProyectoOut();
            // this.procesarComunidadesEtnicasOut();

        } catch (Exception e) {
            showMessage(getText("plan_registro_existe_en_db") + " "+ e.getMessage(), FacesMessage.SEVERITY_ERROR);
            return;
        }
        
        System.out.println("Guardado el plan");
        showPopup(popupExito, true);
    }

    public void t_jurisdicciones_selectionListener(SelectionEvent selectionEvent) {
        this.setT_jurisdiccion((RichTable)selectionEvent.getSource());
        PomtJurisdiccionesTO aux =
            (PomtJurisdiccionesTO)getT_jurisdiccion().getSelectedRowData();
        this.setJurisdiccionSelected(aux.getJurisdiccion());
        FacesUtils.setInSession("jurisdiccionSeleccionada",  this.getJurisdiccionSelected());
    }

    public void t_comisiones_selectionListener(SelectionEvent selectionEvent) {
        this.setT_comision((RichTable)selectionEvent.getSource());
        PomtComisionesTO aux =
            (PomtComisionesTO)this.getT_comision().getSelectedRowData();
        this.setComisionSelected(aux.getComision());
        FacesUtils.setInSession("comisionSeleccionada", this.getComisionSelected());
    }

    public void t_instrumentos_selectionListener(SelectionEvent selectionEvent) {
        this.setT_instrumentos((RichTable)selectionEvent.getSource());
        this.setInstrumentosSelected((PomtInstrumentosPlanificacion)this.getT_instrumentos().getSelectedRowData());
        FacesUtils.setInSession("instrumentoSeleccionada", this.getInstrumentosSelected());
    }

    public void t_comunidades_selectionListener(SelectionEvent selectionEvent) {
        this.setT_comunidades((RichTable)selectionEvent.getSource());
        this.setComunidadesSelected((PomtComunidadesEtnicas)this.getT_comunidades().getSelectedRowData());
        FacesUtils.setInSession("comunidadSeleccionada", this.getComunidadesSelected());
    }

    public Autoridades cargarAutoridadById(Integer idAutoridad) throws IdeamException {
        ParametrosDelegate pd = ParametrosDelegate.getInstance();
        List listaDatos = pd.getAllAutoridades();
        if ((listaDatos != null) && listaDatos.size() > 0) {
            for (int i = 0; i < listaDatos.size(); i++) {
                Autoridades aux = new Autoridades();
                aux = (Autoridades)listaDatos.get(i);
                if (idAutoridad.equals(aux.getId())) {
                    return aux;
                }
            }
        }
        return null;
    }

    public List cargarAutoridades() throws IdeamException {
        ParametrosDelegate pd = ParametrosDelegate.getInstance();
        List listaItems = new ArrayList();
        List listaDatos = pd.getAllAutoridades();
        if ((listaDatos != null) && listaDatos.size() > 0) {
            for (int i = 0; i < listaDatos.size(); i++) {
                Autoridades aux = new Autoridades();
                aux = (Autoridades)listaDatos.get(i);
                SelectItem item = new SelectItem(aux, aux.getNombre());
                listaItems.add(item);
            }
        }
        return listaItems;
    }


    public String getaccion() {
        return accion;
    }

    public Cuenca getCuenca() {
        return cuenca;
    }

    public void setCuenca(Cuenca cuenca) {
        this.cuenca = cuenca;
    }

    public void cb_borrar_docto_actionListener(ActionEvent actionEvent) {
        showPopup(p1, true);
    }

    public void setCb_regresar(RichCommandButton cb1) {
        this.cb_regresar = cb1;
    }

    public RichCommandButton getCb_regresar() {
        return cb_regresar;
    }


    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    /*************************************************************/

    public RichDocument getDocument1() {
        return document1;
    }

    public void setDocument1(RichDocument document1) {
        this.document1 = document1;
    }

    public RichForm getForm1() {
        return form1;
    }

    public void setForm1(RichForm form1) {
        this.form1 = form1;
    }


    public RichCommandMenuItem getCommandMenuItem1() {
        return commandMenuItem1;
    }

    public void setCommandMenuItem1(RichCommandMenuItem commandMenuItem1) {
        this.commandMenuItem1 = commandMenuItem1;
    }

    public RichCommandMenuItem getCommandMenuItem2() {
        return commandMenuItem2;
    }

    public void setCommandMenuItem2(RichCommandMenuItem commandMenuItem2) {
        this.commandMenuItem2 = commandMenuItem2;
    }


    public PomtPlanes getPlan() {
        return plan;
    }

    public void setPlan(PomtPlanes plan) {
        this.plan = plan;
    }

    public PomtJurisdiccion getJurisdiccionSelected() {
        return jurisdiccionSelected;
    }

    public void setJurisdiccionSelected(PomtJurisdiccion jurisdiccionSelected) {
        this.jurisdiccionSelected = jurisdiccionSelected;
    }

    public RichTable getT_jurisdiccion() {
        return t_jurisdiccion;
    }

    public void setT_jurisdiccion(RichTable t_jurisdiccion) {
        this.t_jurisdiccion = t_jurisdiccion;
    }


    public RichTable getT_comision() {
        return t_comision;
    }

    public void setT_comision(RichTable t_comision) {
        this.t_comision = t_comision;
    }

    public PomtComisiones getComisionSelected() {
        return comisionSelected;
    }

    public void setComisionSelected(PomtComisiones comisionSelected) {
        this.comisionSelected = comisionSelected;
    }

    public RichTable getT_instrumentos() {
        return t_instrumentos;
    }

    public void setT_instrumentos(RichTable t_instrumentos) {
        this.t_instrumentos = t_instrumentos;
    }

    public PomtInstrumentosPlanificacion getInstrumentosSelected() {
        return instrumentosSelected;
    }

    public void setInstrumentosSelected(PomtInstrumentosPlanificacion instrumentosSelected) {
        this.instrumentosSelected = instrumentosSelected;
    }

    public RichTable getT_comunidades() {
        return t_comunidades;
    }

    public void setT_comunidades(RichTable t_comunidades) {
        this.t_comunidades = t_comunidades;
    }
  //ZSDG IN
  public RichTable getT_determinantes() {
      return t_determinantes;
  }

  public void setT_determinantes(RichTable t_determinantes) {
      this.t_determinantes= t_determinantes;
  }
  
  public DeterminanteAmbiental getDeterminantesSelected() {
      return determinantesSelected;
  }

  public void setDeterminantesSelected(DeterminanteAmbiental determinantesSelected) {
      this.determinantesSelected = determinantesSelected;
  }
  
  public void setComponentesAfectados(List componentesAfectados) {
    this.componentesAfectados = componentesAfectados;
  }

  public List getComponentesAfectados() {
    return componentesAfectados;
  }
  
  public void setRecursosAfectados(List recursosAfectados) {
    this.recursosAfectados = recursosAfectados;
  }

  public List getRecursosAfectados() {
    return recursosAfectados;
  }
  
  public void setSelectItemsCompD(UISelectItems selectItemsCompD) {
    this.selectItemsCompD = selectItemsCompD;
  }

  public UISelectItems getSelectItemsCompD() {
    return selectItemsCompD;
  }
  
  public RichInputFile getIf_mapaResultante() {
      return if_mapaResultante;
  }

  public void setIf_mapaResultante(RichInputFile if_mapaResultante) {
      this.if_mapaResultante = if_mapaResultante;
  }
  
  public void setIf_archivoDet(RichInputFile if_archivoDet) {
    this.if_archivoDet = if_archivoDet;
  }

  public RichInputFile getIf_archivoDet() {
    return if_archivoDet;
  }
  
  // ZSDG

    public PomtComunidadesEtnicas getComunidadesSelected() {
        return comunidadesSelected;
    }

    public void setComunidadesSelected(PomtComunidadesEtnicas comunidadesSelected) {
        this.comunidadesSelected = comunidadesSelected;
    }

    public List<PomtPlanes> getListaPlanes() {
        return listaPlanes;
    }

    public void setListaPlanes(List<PomtPlanes> listaPlanes) {
        this.listaPlanes = listaPlanes;
    }

    public List getListaDepartamentos() {
        return listaDepartamentos;
    }

    public void setListaDepartamentos(List listaDepartamentos) {
        this.listaDepartamentos = listaDepartamentos;
    }

    public List getListaMunicipios() {
        return listaMunicipios;
    }

    public void setListaMunicipios(List listaMunicipios) {
        this.listaMunicipios = listaMunicipios;
    }

    public RichSelectOneChoice getSoc_depto() {
        return soc_depto;
    }

    public void setSoc_depto(RichSelectOneChoice soc_depto) {
        this.soc_depto = soc_depto;
    }

    public UISelectItems getSi1() {
        return si1;
    }

    public void setSi1(UISelectItems si1) {
        this.si1 = si1;
    }

    public RichSelectOneChoice getSoc_municipio() {
        return soc_municipio;
    }

    public void setSoc_municipio(RichSelectOneChoice soc_municipio) {
        this.soc_municipio = soc_municipio;
    }

    public UISelectItems getSi2() {
        return si2;
    }

    public void setSi2(UISelectItems si2) {
        this.si2 = si2;
    }

    public Divipola getDepartamentoSelected() {
        return DepartamentoSelected;
    }

    public void setDepartamentoSelected(Divipola DepartamentoSelected) {
        this.DepartamentoSelected = DepartamentoSelected;
    }

    public Divipola getMunicipioSelected() {
        return MunicipioSelected;
    }

    public void setMunicipioSelected(Divipola MunicipioSelected) {
        this.MunicipioSelected = MunicipioSelected;
    }

    public List getListaDepartamentosCargados() {
        return listaDepartamentosCargados;
    }

    public void setListaDepartamentosCargados(List listaDepartamentosCargados) {
        this.listaDepartamentosCargados = listaDepartamentosCargados;
    }

    public List getListaMunicipiosCargados() {
        return listaMunicipiosCargados;
    }

    public void setListaMunicipiosCargados(List listaMunicipiosCargados) {
        this.listaMunicipiosCargados = listaMunicipiosCargados;
    }

    public List getListaAutoridades() {
        return listaAutoridades;
    }

    public void setListaAutoridades(List listaAutoridades) {
        this.listaAutoridades = listaAutoridades;
    }

    public RichSelectOneChoice getSoc_autoridad() {
        return soc_autoridad;
    }

    public void setSoc_autoridad(RichSelectOneChoice soc_autoridad) {
        this.soc_autoridad = soc_autoridad;
    }

    public Autoridades getAutoridadSelected() {
        return autoridadSelected;
    }

    public void setAutoridadSelected(Autoridades autoridadSelected) {
        this.autoridadSelected = autoridadSelected;
    }

    public UISelectItems getSiAutoridades() {
        return siAutoridades;
    }

    public void setSiAutoridades(UISelectItems siAutoridades) {
        this.siAutoridades = siAutoridades;
    }

    public List getJurisdiccionesXPlan() {
        return jurisdiccionesXPlan;
    }

    public void setJurisdiccionesXPlan(List jurisdiccionesXPlan) {
        this.jurisdiccionesXPlan = jurisdiccionesXPlan;
    }

    public RichSelectBooleanCheckbox getFaseElabAprestamChk() {
        return faseElabAprestamChk;
    }

    public void setFaseElabAprestamChk(RichSelectBooleanCheckbox faseElabAprestamChk) {
        this.faseElabAprestamChk = faseElabAprestamChk;
    }

    public RichSelectBooleanCheckbox getFaseElabDiagnostChk() {
        return faseElabDiagnostChk;
    }

    public void setFaseElabDiagnostChk(RichSelectBooleanCheckbox faseElabDiagnostChk) {
        this.faseElabDiagnostChk = faseElabDiagnostChk;
    }

    public RichSelectBooleanCheckbox getFaseElabZonifChk() {
        return faseElabZonifChk;
    }

    public void setFaseElabZonifChk(RichSelectBooleanCheckbox faseElabZonifChk) {
        this.faseElabZonifChk = faseElabZonifChk;
    }

    public RichSelectBooleanCheckbox getFaseElabFormulacChk() {
        return faseElabFormulacChk;
    }

    public void setFaseElabFormulacChk(RichSelectBooleanCheckbox faseElabFormulacChk) {
        this.faseElabFormulacChk = faseElabFormulacChk;
    }

    public RichSelectBooleanCheckbox getFaseElabEjecuChk() {
        return faseElabEjecuChk;
    }

    public void setFaseElabEjecuChk(RichSelectBooleanCheckbox faseElabEjecuChk) {
        this.faseElabEjecuChk = faseElabEjecuChk;
    }

    public RichSelectBooleanCheckbox getFaseElabSegChk() {
        return faseElabSegChk;
    }

    public void setFaseElabSegChk(RichSelectBooleanCheckbox faseElabSegChk) {
        this.faseElabSegChk = faseElabSegChk;
    }

    public RichSelectBooleanCheckbox getProcPreConsultaChk() {
        return procPreConsultaChk;
    }

    public void setProcPreConsultaChk(RichSelectBooleanCheckbox procPreConsultaChk) {
        this.procPreConsultaChk = procPreConsultaChk;
    }

    public RichSelectBooleanCheckbox getProcConsultaChk() {
        return procConsultaChk;
    }

    public void setProcConsultaChk(RichSelectBooleanCheckbox procConsultaChk) {
        this.procConsultaChk = procConsultaChk;
    }

    public List<PomtComisionesTO> getComisionesXPlan() {
        return comisionesXPlan;
    }

    public void setComisionesXPlan(List<PomtComisionesTO> comisionesXPlan) {
        this.comisionesXPlan = comisionesXPlan;
    }

    public RichPopup getPopup1() {
        return popup1;
    }

    public void setPopup1(RichPopup popup1) {
        this.popup1 = popup1;
    }
    
    public void setIf_dcoumento(RichInputFile if1) {
        this.if_dcoumento = if1;
    }

    public RichInputFile getIf_dcoumento() {
        return if_dcoumento;
    }

    public RichPopup getPopupExito() {
        return popupExito;
    }

    public void setPopupExito(RichPopup popupExito) {
        this.popupExito = popupExito;
    }


   

    public RichInputFile getIf_infoZonif() {
        return if_infoZonif;
    }

    public void setIf_infoZonif(RichInputFile if_infoZonif) {
        this.if_infoZonif = if_infoZonif;
    }


    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
       
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setImage1(RichImage image1) {
        this.image1 = image1;
    }

    public RichImage getImage1() {
        return image1;
    }

    public void setPanelFormLayoutImagen(RichPanelFormLayout panelFormLayoutImagen) {
        this.panelFormLayoutImagen = panelFormLayoutImagen;
    }

    public RichPanelFormLayout getPanelFormLayoutImagen() {
        return panelFormLayoutImagen;
    }


  
}
