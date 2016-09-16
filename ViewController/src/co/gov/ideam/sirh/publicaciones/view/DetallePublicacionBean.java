package co.gov.ideam.sirh.publicaciones.view;


import co.gov.ideam.sirh.documentos.model.Nodo;
import co.gov.ideam.sirh.documentos.view.DocumentosBean;
import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Categoria;
import co.gov.ideam.sirh.publicaciones.model.ArchivosXPublicacion;
import co.gov.ideam.sirh.publicaciones.model.Publicacion;

import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;

import java.io.IOException;
import java.io.OutputStream;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputFile;
import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;

import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.model.UploadedFile;



public class DetallePublicacionBean extends StandarBean {
    private FnttFuente fuente;
    private String accion;
    private UploadedFile uploadedFile;
    private RichTable t_publicaciones;
    private List<Publicacion> listaPublicaciones;
    private Publicacion publicacionSeleccionada;
    private String nombreIn;
    private RichInputText nombreInText;
    private RichSelectOneChoice sc_tipoRecurso;
    private UISelectItems selectItems1;
    private RichInputFile if_dcoumento;
    private List tiposRecurso;
    private RichPopup popupExito;

    public DetallePublicacionBean() {
        
        FacesUtils.setActiveBean("detallePublicacion", "detalle de Publicacion",  PublicacionesDelegate.class);
        this.load();
    }

    public void load() {
        sc_tipoRecurso= new RichSelectOneChoice();
        System.out.println("---entra al load de DetallePublicacionBean----");
        try {
            this.if_dcoumento.resetValue();
            if_dcoumento.setSubmittedValue(null);
        }catch(Exception e){}    
        this.publicacionSeleccionada = (Publicacion)FacesUtils.getFromSession("publicacionSeleccionada");
        //System.out.println("Publicacion a mostrar:"+this.publicacionSeleccionada.getNombrePublicacion());

        try {
            this.tiposRecurso = this.cargarParametro(Categoria.TIPO_RECURSO_PUBLICACION);
        } catch (IdeamException e) {
            showMessage(e);
        } 
    
        
    }
    
    public void asociarArchivosPublicacion(Nodo nodo) throws IdeamException{
        //Amarra el archivo cargado al modulo o tabla o paquete que asocia el archivo con la appp
        PublicacionesDelegate pomcad = PublicacionesDelegate.getInstance();
        ArchivosXPublicacion archiPub = new ArchivosXPublicacion();
        archiPub.setId(nodo.getId());
        archiPub.setFechaCreacion(nodo.getFechaCreacion());
        archiPub.setPublicacion(this.publicacionSeleccionada);
        System.out.println("<<<<<<<<<<<< EL ID DEL ARCHIPUB "+archiPub.getId()+" - "+archiPub.getPublicacion().getId());
       // this.publicacionSeleccionada = pomcad.setPublicacion(this.getPublicacionSeleccionada());
        pomcad.updateArchivosXPublicacion(archiPub);
       
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
            String tipoArchivo = file.getContentType();
            if (!tipoArchivo.endsWith("pdf")){
                String params[] = { file.getFilename(), "PDF" };
                showMessage("tipo_archivo_incorrecto",
                            params,
                            FacesMessage.SEVERITY_ERROR,
                            if_dcoumento);
                this.if_dcoumento.resetValue();
                this.setUploadedFile(null);
                return;
            }else{
                Nodo nodo = dbean.setUploadedFile(file, this.publicacionSeleccionada.getIdArchivo());
                this.publicacionSeleccionada.setIdArchivo(nodo.getId());
                System.out.println(">>>>>> DESPUES DE CARGAR EL ARCHIVO, EL ID DE ESTE ES: "+this.publicacionSeleccionada.getId());
                this.asociarArchivosPublicacion(nodo);
            }
            this.setUploadedFile(file);
        }
    }
    
    public void findByNombre(ActionEvent actionEvent) throws IdeamException {
        this.findByNombre();
    }

    public void pub_find_name_valueChangeListener(ValueChangeEvent valueChangeEvent) throws IdeamException {
        this.findByNombre();
    } 

    public void findByNombre() throws IdeamException {
       //String filtroNombre = this.getNombreIn();
       String filtroNombre = (String)this.nombreInText.getValue();
       Publicacion pub = new Publicacion();
       pub.setNombrePublicacion(filtroNombre);
       PublicacionesDelegate pomcad = PublicacionesDelegate.getInstance();
       
       if(this.getNombreIn() != null && !this.getNombreIn().equalsIgnoreCase("") && this.getNombreIn()!=""){
           System.out.println(">>>>> PUBLICACIONES: 1");
           List<Publicacion> publicacionesExistentes =  pomcad.getAllPomtPublicacionByNombre(pub);
           this.listaPublicaciones = publicacionesExistentes;
       }else{
           System.out.println(">>>>> PUBLICACIONES: 2");
           List<Publicacion> publicacionesExistentes =  pomcad.getAllPomtPublicaciones();
           this.listaPublicaciones = publicacionesExistentes;
       }
       
    }

    public void cb_aceptar_actionListener(ActionEvent actionEvent) throws IdeamException {
        boolean continuar = true;
        if (this.getPublicacionSeleccionada().getNombrePublicacion() == null ||
            this.getPublicacionSeleccionada().getNombrePublicacion().length() == 0) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR);
            continuar = false;
        }
        if (continuar) {
            PublicacionesDelegate pubdeg = PublicacionesDelegate.getInstance();
            pubdeg.updatePomtPublicacion(this.publicacionSeleccionada); 
            FacesUtils.removeFromSession("publicacionSeleccionada");
            this.listaPublicaciones = pubdeg.getAllPomtPublicaciones();
            showPopup(popupExito, true);
        }
    }
    
    public void eliminarPublicaciones_actionListener(ActionEvent actionEvent) throws IdeamException {
        try{
        Publicacion pub = new Publicacion();
        pub = (Publicacion) this.getPublicacionSeleccionada();
        PublicacionesDelegate publicad = PublicacionesDelegate.getInstance();
        publicad.removePomtPublicacion(pub);
        this.listaPublicaciones = publicad.getAllPomtPublicaciones();
        this.publicacionSeleccionada = new Publicacion();
        this.setAccion("verPublicaciones");
        showPopup(popupExito, true);
        }catch(Exception e){
            showMessage(e.getMessage()+" - "+e.getLocalizedMessage(), FacesMessage.SEVERITY_ERROR);
            return;   
        }
    }

  

    public void commandMenuItem2_actionListener(ActionEvent actionEvent) { //editar
        this.publicacionSeleccionada = (Publicacion)FacesUtils.getFromSession("publicacionSeleccionada");
        if (this.getPublicacionSeleccionada() == null) {
            this.publicacionSeleccionada = new Publicacion();
            showMessage(getText("seleccionar_registro"), FacesMessage.SEVERITY_ERROR);
            accion = "adicionarPublicacion";
        } else {
            System.out.println(">>>>>>>PUBLICACION>>>>>>>" +
                               this.getPublicacionSeleccionada().getNombrePublicacion() +
                               " " +
                               this.getPublicacionSeleccionada().getAutor() +
                               " " +
                               this.getPublicacionSeleccionada().getAnio());
            FacesUtils.setInSession("publicacionSeleccionada", this.getPublicacionSeleccionada());

            accion = "detallePublicacion";
        }
    }


    public String getaccion() {
        return accion;
    }

    public FnttFuente getFuente() {
        return fuente;
    }

    public void setFuente(FnttFuente fuente) {
        this.fuente = fuente;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }


    /*************************************************************/

    public void t_publicaciones_selectionListener(SelectionEvent selectionEvent) {
        this.publicacionSeleccionada = new Publicacion();
        this.setT_publicaciones((RichTable)selectionEvent.getSource());
        this.setPublicacionSeleccionada((Publicacion)getT_publicaciones().getSelectedRowData());
        
        System.out.println(">>>>>>>>>>OOOOOO>>>>>>>>>>" +
                                 this.getPublicacionSeleccionada().getNombrePublicacion() +
                           " " + this.getPublicacionSeleccionada().getAutor() +
                           " " + this.getPublicacionSeleccionada().getAnio() +
                           " " + this.getPublicacionSeleccionada().getId());

        FacesUtils.setInSession("publicacionSeleccionada", this.getPublicacionSeleccionada());
    }

    public RichTable getT_publicaciones() {
        return t_publicaciones;
    }

    public void setT_publicaciones(RichTable t_publicaciones) {
        this.t_publicaciones = t_publicaciones;
    }

    public List<Publicacion> getListaPublicaciones() {
        return listaPublicaciones;
    }

    public void setListaPublicacions(List<Publicacion> listaPublicaciones) {
        this.listaPublicaciones = listaPublicaciones;
    }

    public Publicacion getPublicacionSeleccionada() {
        return publicacionSeleccionada;
    }

    public void setPublicacionSeleccionada(Publicacion publicacionSeleccionada) {
        this.publicacionSeleccionada = publicacionSeleccionada;
    }

    public String getNombreIn() {
        return nombreIn;
    }

    public void setNombreIn(String nombreIn) {
        this.nombreIn = nombreIn;
    }

    public RichInputText getNombreInText() {
        return nombreInText;
    }

    public void setNombreInText(RichInputText nombreInText) {
        this.nombreInText = nombreInText;
    }

    public RichInputFile getIf_dcoumento() {
        return if_dcoumento;
    }

    public void setIf_dcoumento(RichInputFile if_dcoumento) {
        this.if_dcoumento = if_dcoumento;
    }

    public List getTiposRecurso() {
        return tiposRecurso;
    }

    public void setTiposRecurso(List tiposRecurso) {
        this.tiposRecurso = tiposRecurso;
    }
    public RichPopup getPopupExito() {
        return popupExito;
    }

    public void setPopupExito(RichPopup popupExito) {
        this.popupExito = popupExito;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setSc_tipoRecurso(RichSelectOneChoice sc_tipoRecurso) {
        this.sc_tipoRecurso = sc_tipoRecurso;
    }

    public RichSelectOneChoice getSc_tipoRecurso() {
        return sc_tipoRecurso;
    }

    public void setSelectItems1(UISelectItems selectItems1) {
        this.selectItems1 = selectItems1;
    }

    public UISelectItems getSelectItems1() {
        return selectItems1;
    }
}
