package co.gov.ideam.sirh.publicaciones.view;


import co.gov.ideam.sirh.documentos.model.Nodo;
import co.gov.ideam.sirh.documentos.view.DocumentosBean;
import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Categoria;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.publicaciones.model.ArchivosXPublicacion;
import co.gov.ideam.sirh.publicaciones.model.Publicacion;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.util.Constantes;
import co.gov.ideam.sirh.util.ConstantesCalidad;
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


/********/


/*******/


public class AdicionarPublicacionBean extends StandarBean {
    private FnttFuente fuente;
    private String accion;
    private UISelectItems selectItemsTipoP;
    private RichSelectOneChoice sc_tipoRecurso;
    private RichTable t_publicaciones;
    private List<Publicacion> listaPublicaciones;
    private Publicacion publicacionSeleccionada;
    private String nombreIn;
    private RichInputText nombreInText;
    
    private RichInputFile if_dcoumento;
    private List tiposRecurso;


    public AdicionarPublicacionBean() {
        FacesUtils.removeFromSession("publicacionSeleccionada");
        FacesUtils.setActiveBean("publicaciones", "publicaciones",  PublicacionesDelegate.class);
        FacesUtils.removeManagedBeanFromSession("PublicacionesBean");
        this.load();
    }

    public void load() {
        FacesUtils.removeFromSession("publicacionSeleccionada");
        this.publicacionSeleccionada= new Publicacion();
        try {
            UsuarioConectadoTO usuarioConectado = (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
            Autoridades autoridadAmbiental = usuarioConectado.getUsuario().getAutoridadAmbiental();

            if (autoridadAmbiental == null ||
                autoridadAmbiental.getId().intValue() == Constantes.IDEAM) {
                showMessage(getText("NO_EXISTE_AUTORIDAD_AMBIENTAL"), FacesMessage.SEVERITY_ERROR);
                return;
            }
            this.tiposRecurso = this.getListaPorCategoria(Categoria.TIPO_RECURSO_PUBLICACION);

        } catch (IdeamException e) {
            showMessage(e);
        }   
    }
    

    
  
    
    
    public void sc_tipo_valueChangeListener(ValueChangeEvent valueChangeEvent) {
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        this.publicacionSeleccionada.setLTipoRecurso((Lista)this.sc_tipoRecurso.getValue());
    }

   

    public void cb_aceptar_actionListener(ActionEvent actionEvent) throws IdeamException {
        System.out.println("------------Recibe datos en cb_aceptar_actionListener-----------------");
        boolean continuar = true;
        System.out.println("alamacena la publicaicon:"+publicacionSeleccionada.getNombrePublicacion());
        PublicacionesDelegate pomcad = PublicacionesDelegate.getInstance();
        if (this.publicacionSeleccionada.getNombrePublicacion() == null ||
            this.publicacionSeleccionada.getNombrePublicacion().length() == 0) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR);
            continuar = false;
        }
        if (continuar) {
            this.publicacionSeleccionada.setNombrePublicacion(publicacionSeleccionada.getNombrePublicacion().toUpperCase());
            System.out.println("Tipo recurso:"+this.publicacionSeleccionada.getLTipoRecurso().getCodigo());
            this.publicacionSeleccionada.setTipoRecurso(this.publicacionSeleccionada.getLTipoRecurso().getCodigo());
            this.publicacionSeleccionada = pomcad.updatePomtPublicacion(this.getPublicacionSeleccionada());
            
            System.out.println("sE SLMSCENA CORRECTAMENTE:"+publicacionSeleccionada.getId());
            FacesUtils.setInSession("publicacionSeleccionada", this.publicacionSeleccionada);
            this.setAccion("");
            try {
                this.listaPublicaciones = pomcad.getAllPomtPublicaciones();
                this.setAccion("detallePublicacion");
            } catch (IdeamException e) {
                showMessage(e);
            }
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

    public void setSelectItemsTipoP(UISelectItems selectItemsTipoP) {
        this.selectItemsTipoP = selectItemsTipoP;
    }

    public UISelectItems getSelectItemsTipoP() {
        return selectItemsTipoP;
    }


    public void setSc_tipoRecurso(RichSelectOneChoice sc_tipoRecurso) {
        this.sc_tipoRecurso = sc_tipoRecurso;
    }

    public RichSelectOneChoice getSc_tipoRecurso() {
        return sc_tipoRecurso;
    }
}
