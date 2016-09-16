package co.gov.ideam.sirh.publicaciones.view;


import co.gov.ideam.sirh.calidad.view.JSReportesDelegate;
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

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import javax.faces.model.SelectItem;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputFile;
import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;

import oracle.adf.view.rich.context.AdfFacesContext;

import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.model.UploadedFile;


/********/


/*******/


public class PublicacionesBean extends StandarBean {
  //Pilar
    private String accion;
    private UploadedFile uploadedFile;
    private RichTable t_publicaciones;
    private List<Publicacion> listaPublicaciones;
    private Publicacion publicacionSeleccionada;
    private String nombreIn;
    private RichInputText nombreInText;
    
    private RichInputFile if_dcoumento;
    private List tiposRecursoList;
    private RichPopup popupExito;
    private List<SelectItem> tipoRecursoSelectItem;
    private RichSelectOneChoice tipoRecursoBinding;
     private Integer tipoRecursoFilter;
     private String ahFilter;
    private List<SelectItem> ahSelectItem;
    private List ahList;
  private RichSelectOneChoice ahBinding;
  
  private String sistemaAcuiferoFilter;
  private List<SelectItem> sistemaAcuiferoSelectItem;
  private List sistemaAcuiferoList;
  private RichSelectOneChoice sistemaAcuiferoBinding;
  
    public PublicacionesBean() {
        FacesUtils.removeFromSession("publicacionSeleccionada");
        FacesUtils.setActiveBean("publicaciones", "publicaciones",  PublicacionesDelegate.class);
        this.load();
    }

    public void load() {
        this.publicacionSeleccionada = new Publicacion();
      tiposRecursoList = new ArrayList();
      tipoRecursoSelectItem = new ArrayList<SelectItem>();
      ahList = new ArrayList();
      ahSelectItem = new ArrayList<SelectItem>();
      sistemaAcuiferoList = new ArrayList();
      sistemaAcuiferoSelectItem = new ArrayList<SelectItem>();
        try {
            PublicacionesDelegate publicad = PublicacionesDelegate.getInstance();
            List<Publicacion> publicacionesExistentes = publicad.getAllPomtPublicaciones();
            this.listaPublicaciones = publicacionesExistentes;         
           
          for (int j = 0; j < this.listaPublicaciones.size(); j++) {       
            
         //   System.out.println("Da");
            //Listado Provincia
            if (!tiposRecursoList.contains(listaPublicaciones.get(j).getLTipoRecurso().getValor())) {
              tiposRecursoList.add(listaPublicaciones.get(j).getLTipoRecurso().getValor());
              SelectItem nuevo = new SelectItem();
              nuevo.setValue(listaPublicaciones.get(j).getLTipoRecurso().getCodigo());
              nuevo.setLabel(listaPublicaciones.get(j).getLTipoRecurso().getValor());
              tipoRecursoSelectItem.add(nuevo);
            }         

            if (!ahList.contains(listaPublicaciones.get(j).getAh())) {
              ahList.add(listaPublicaciones.get(j).getAh());
              ahSelectItem.add(new SelectItem(listaPublicaciones.get(j).getAh()));
            }
            
            if (!sistemaAcuiferoList.contains(listaPublicaciones.get(j).getSistemaAcuifero())) {
              sistemaAcuiferoList.add(listaPublicaciones.get(j).getSistemaAcuifero());
              sistemaAcuiferoSelectItem.add(new SelectItem(listaPublicaciones.get(j).getSistemaAcuifero()));
            }
          }
            //this.tiposRecurso = cargarParametro(Categoria.TIPO_RECURSO_PUBLICACION);
        } catch (IdeamException e) {
            showMessage(e);
        }  
        
    }
    
  public void tipoRecurso_valueChangeListener(ValueChangeEvent valueChangeEvent) {
    Object tipoRecurso = valueChangeEvent.getNewValue();
    //System.out.println("area_valueChangeListener ->" + tipoRecurso);
    if (tipoRecurso != null) {
      this.tipoRecursoFilter = Integer.parseInt(tipoRecurso.toString());
    //  criterios.setProvincia(null);
    } else {
      this.tipoRecursoFilter = null ;
    }
  //  provincia = null;
    //AdfFacesContext.getCurrentInstance().addPartialTarget(this.provincia);
  }
  
  public void ah_valueChangeListener(ValueChangeEvent valueChangeEvent) {
    Object ah = valueChangeEvent.getNewValue();
   // System.out.println("area_valueChangeListener ->" + ah);
    if (ah != null) {
      this.ahFilter = ah.toString();
    //  criterios.setProvincia(null);
    } else {
      this.ahFilter = null ;
    }
  //  provincia = null;
    //AdfFacesContext.getCurrentInstance().addPartialTarget(this.provincia);
  }
  
  public void sistemaAcuifero_valueChangeListener(ValueChangeEvent valueChangeEvent) {
    Object sistemaAcuifero = valueChangeEvent.getNewValue();
    System.out.println("area_valueChangeListener ->" + sistemaAcuifero);
    if (sistemaAcuifero != null) {
      this.sistemaAcuiferoFilter= sistemaAcuifero.toString();
    //  criterios.setProvincia(null);
    } else {
      this.sistemaAcuiferoFilter= null ;
    }
  //  provincia = null;
    //AdfFacesContext.getCurrentInstance().addPartialTarget(this.provincia);
  }
  
  public void cmdBuscar_actionListener(ActionEvent actionEvent) {
   
    try {
      this.listaPublicaciones.clear();   
      PublicacionesDelegate publicad = PublicacionesDelegate.getInstance();
      
      if ( this.sistemaAcuiferoFilter != null){
      this.listaPublicaciones =   publicad.getAllPomtPublicacionBySistemaAcuifero(this.sistemaAcuiferoFilter);
              
        }
      
      else if (this.tipoRecursoFilter != null){
        
          this.listaPublicaciones =   publicad.getAllPomtPublicacionByTipoRecurso(this.tipoRecursoFilter);   
        }
      
      else if (this.ahFilter != null){        
      this.listaPublicaciones =   publicad.getAllPomtPublicacionByAH(this.ahFilter);
      
      }
     
    } catch (IdeamException e) {
      showMessage(e);
    }
  }
  
 /* public void cmdBuscar2_actionListener(ActionEvent actionEvent) {
   
    try {
      this.listaPublicaciones.clear();   
      PublicacionesDelegate publicad = PublicacionesDelegate.getInstance();
    
      this.listaPublicaciones =   publicad.getAllPomtPublicacionByAH(this.ahFilter);   
   
     
    } catch (IdeamException e) {
      showMessage(e);
    }
  }
    
  public void cmdBuscar3_actionListener(ActionEvent actionEvent) {
   
    try {
      this.listaPublicaciones.clear();   
      PublicacionesDelegate publicad = PublicacionesDelegate.getInstance();
      System.out.println(" this.sistemaAcuiferoFilter -> " + this.sistemaAcuiferoFilter);
    
      this.listaPublicaciones =   publicad.getAllPomtPublicacionBySistemaAcuifero(this.sistemaAcuiferoFilter);  
   
     
    } catch (IdeamException e) {
      showMessage(e);
    }
  } */
    
    public void findByNombre(ActionEvent actionEvent) throws IdeamException {
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
  
/*
 * Atiende opcion de menu desde la tabla de publicaciones
 */
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

  public void setTiposRecursoList(List tiposRecursoList) {
    this.tiposRecursoList = tiposRecursoList;
  }

  public List getTiposRecursoList() {
    return tiposRecursoList;
  }

  public void setTipoRecursoSelectItem(List<SelectItem> tipoRecursoSelectItem) {
    this.tipoRecursoSelectItem = tipoRecursoSelectItem;
  }

  public List<SelectItem> getTipoRecursoSelectItem() {
    return tipoRecursoSelectItem;
  }

  public void setTipoRecursoBinding(RichSelectOneChoice tipoRecursoBinding) {
    this.tipoRecursoBinding = tipoRecursoBinding;
  }

  public RichSelectOneChoice getTipoRecursoBinding() {
    return tipoRecursoBinding;
  }


  public void setTipoRecursoFilter(Integer tipoRecursoFilter) {
    this.tipoRecursoFilter = tipoRecursoFilter;
  }

  public Integer getTipoRecursoFilter() {
    return tipoRecursoFilter;
  }


  public void setAhFilter(String ahFilter) {
    this.ahFilter = ahFilter;
  }

  public String getAhFilter() {
    return ahFilter;
  }

  public void setAhSelectItem(List<SelectItem> ahSelectItem) {
    this.ahSelectItem = ahSelectItem;
  }

  public List<SelectItem> getAhSelectItem() {
    return ahSelectItem;
  }

  public void setAhList(List ahList) {
    this.ahList = ahList;
  }

  public List getAhList() {
    return ahList;
  }


  public void setAhBinding(RichSelectOneChoice ahBinding) {
    this.ahBinding = ahBinding;
  }

  public RichSelectOneChoice getAhBinding() {
    return ahBinding;
  }


  public void setSistemaAcuiferoFilter(String sistemaAcuiferoFilter) {
    this.sistemaAcuiferoFilter = sistemaAcuiferoFilter;
  }

  public String getSistemaAcuiferoFilter() {
    return sistemaAcuiferoFilter;
  }

  public void setSistemaAcuiferoSelectItem(List<SelectItem> sistemaAcuiferoSelectItem) {
    this.sistemaAcuiferoSelectItem = sistemaAcuiferoSelectItem;
  }

  public List<SelectItem> getSistemaAcuiferoSelectItem() {
    return sistemaAcuiferoSelectItem;
  }

  public void setSistemaAcuiferoList(List sistemaAcuiferoList) {
    this.sistemaAcuiferoList = sistemaAcuiferoList;
  }

  public List getSistemaAcuiferoList() {
    return sistemaAcuiferoList;
  }

  public void setSistemaAcuiferoBinding(RichSelectOneChoice sistemaAcuiferoBinding) {
    this.sistemaAcuiferoBinding = sistemaAcuiferoBinding;
  }

  public RichSelectOneChoice getSistemaAcuiferoBinding() {
    return sistemaAcuiferoBinding;
  }
}
