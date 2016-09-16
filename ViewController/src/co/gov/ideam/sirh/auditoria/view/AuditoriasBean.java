package co.gov.ideam.sirh.auditoria.view;


import co.gov.ideam.sirh.auditoria.model.Auditoria;
import co.gov.ideam.sirh.documentos.model.Nodo;
import co.gov.ideam.sirh.documentos.view.DocumentosBean;
import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Categoria;
import co.gov.ideam.sirh.publicaciones.model.ArchivosXPublicacion;
import co.gov.ideam.sirh.publicaciones.model.Publicacion;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.util.Constantes;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;

import java.io.IOException;
import java.io.OutputStream;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputFile;
import oracle.adf.view.rich.component.rich.input.RichInputText;

import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.model.UploadedFile;


/********/


/*******/


public class AuditoriasBean extends StandarBean {
    private FnttFuente fuente;
    private String accion;

    private RichTable t_auditorias;
    private List<Auditoria> listaAuditorias;
    private Auditoria auditoriaSeleccionada;
    private String nombreIn;
    private RichInputText nombreInText;
    
    private RichInputFile if_dcoumento;
    private List tiposRecurso;
    private RichPopup popupExito;

    public AuditoriasBean() {
        FacesUtils.setActiveBean("auditorias", "auditorias",  AuditoriasDelegate.class);
        this.load();
    }

    public void load() {
        this.auditoriaSeleccionada = new Auditoria();
        this.setAccion("");
        this.fuente = (FnttFuente)FacesUtils.getFromSession("fuenteSeleccionada");

        try {
            UsuarioConectadoTO usuarioConectado = (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
            Autoridades autoridadAmbiental = usuarioConectado.getUsuario().getAutoridadAmbiental();

            if (autoridadAmbiental == null ||
                autoridadAmbiental.getId().intValue() == Constantes.IDEAM) {
                showMessage(getText("NO_EXISTE_AUTORIDAD_AMBIENTAL"),
                            FacesMessage.SEVERITY_ERROR);
                return;
            }

            /****************************/
            AuditoriasDelegate publicad = AuditoriasDelegate.getInstance();
            List<Auditoria> auditoriasExistentes = publicad.getAllAuditorias();
            this.listaAuditorias = auditoriasExistentes;
            /****************************/


            this.tiposRecurso = cargarParametro(Categoria.TIPO_RECURSO_PUBLICACION);
            

        } catch (IdeamException e) {
            showMessage(e);
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
       Auditoria audit = new Auditoria();
       audit.setIdUsuario(new Long(filtroNombre));
       AuditoriasDelegate auditod = AuditoriasDelegate.getInstance();
       
       if(this.getNombreIn() != null && !this.getNombreIn().equalsIgnoreCase("") && this.getNombreIn()!=""){
           System.out.println(">>>>> AUDITORIAS: 1");
           List<Auditoria> auditoriasExistentes =  auditod.getAllAuditoriasByObjeto(audit);
           this.listaAuditorias = auditoriasExistentes;
       }else{
           System.out.println(">>>>> AUDITORIAS: 2");
           List<Auditoria> auditoriasExistentes =  auditod.getAllAuditorias();
           this.listaAuditorias = auditoriasExistentes;
       }
       
    }

    public void cb_aceptar_actionListener(ActionEvent actionEvent) throws IdeamException {
        boolean continuar = true;
        if (this.getAuditoriaSeleccionada().getClase() == null ||
            this.getAuditoriaSeleccionada().getMetodo().length() == 0) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR);
            continuar = false;
        }
        if (!continuar) {
            return;
        }
        this.setAccion("");
        try {
            AuditoriasDelegate auditod = AuditoriasDelegate.getInstance();
            this.auditoriaSeleccionada = auditod.setAuditoria(this.getAuditoriaSeleccionada());
            this.listaAuditorias = auditod.getAllAuditorias();
            if(this.getAuditoriaSeleccionada().getClase() != null){
                this.setAccion("auditorias");
            }else{
                this.setAccion("auditorias");
            }
        } catch (IdeamException e) {
            showMessage(e);
        }
        showPopup(popupExito, true);
    }
    
    public void eliminarAuditorias_actionListener(ActionEvent actionEvent) throws IdeamException {
        try{
        Auditoria pub = new Auditoria();
        pub = (Auditoria) this.getAuditoriaSeleccionada();
        AuditoriasDelegate publicad = AuditoriasDelegate.getInstance();
        publicad.removeAuditoria(pub);
        this.listaAuditorias = publicad.getAllAuditorias();
        this.auditoriaSeleccionada = new Auditoria();
        this.setAccion("verPublicaciones");
        showPopup(popupExito, true);
        }catch(Exception e){
            showMessage(e.getMessage()+" - "+e.getLocalizedMessage(), FacesMessage.SEVERITY_ERROR);
            return;   
        }
    }


    public String commandMenuItem1_action() { //adicionar
        FacesUtils.setInSession("auditoriaSeleccionada", null);
        this.auditoriaSeleccionada = new Auditoria();
        this.auditoriaSeleccionada.setOperacion(null);
        return accion = "adicionarPublicacion";

    }

    public void commandMenuItem2_actionListener(ActionEvent actionEvent) { //editar
        this.auditoriaSeleccionada = (Auditoria)FacesUtils.getFromSession("auditoriaSeleccionada");
        if (this.getAuditoriaSeleccionada() == null) {
            this.auditoriaSeleccionada = new Auditoria();
            showMessage(getText("seleccionar_registro"), FacesMessage.SEVERITY_ERROR);
            accion = "adicionarPublicacion";
        } else {
            System.out.println(">>>>>>>AUDITORIA>>>>>>>" +
                               this.getAuditoriaSeleccionada().getClase() +
                               " " +
                               this.getAuditoriaSeleccionada().getMetodo() +
                               " " +
                               this.getAuditoriaSeleccionada().getIdUsuario());
            FacesUtils.setInSession("auditoriaSeleccionada", this.getAuditoriaSeleccionada());

            accion = "auditorias";
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

    public void t_auditorias_selectionListener(SelectionEvent selectionEvent) {
        this.auditoriaSeleccionada = new Auditoria();
        this.setT_auditorias((RichTable)selectionEvent.getSource());
        this.setAuditoriaSeleccionada((Auditoria)getT_auditorias().getSelectedRowData());
        
        

        System.out.println(">>>>>>>>>>OOOOOO>>>>>>>>>>" +
                           this.getAuditoriaSeleccionada().getOperacion() +
                           " " + this.getAuditoriaSeleccionada().getIdUsuario() +
                           " " + this.getAuditoriaSeleccionada().getClase() +
                           " " + this.getAuditoriaSeleccionada().getObjeto());

        FacesUtils.setInSession("auditoriaSeleccionada", this.getAuditoriaSeleccionada());

    }

    public RichTable getT_auditorias() {
        return t_auditorias;
    }

    public void setT_auditorias(RichTable t_auditorias) {
        this.t_auditorias = t_auditorias;
    }

    public List<Auditoria> getListaAuditorias() {
        return listaAuditorias;
    }

    public void setListaAuditorias(List<Auditoria> listaAuditorias) {
        this.listaAuditorias = listaAuditorias;
    }

    public Auditoria getAuditoriaSeleccionada() {
        return auditoriaSeleccionada;
    }

    public void setAuditoriaSeleccionada(Auditoria auditoriaSeleccionada) {
        this.auditoriaSeleccionada = auditoriaSeleccionada;
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
}
