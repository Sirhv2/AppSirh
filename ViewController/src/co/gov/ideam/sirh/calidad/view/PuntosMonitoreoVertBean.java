package co.gov.ideam.sirh.calidad.view;

import co.gov.ideam.sirh.calidad.model.CriteriosBusquedaPuntos;
import co.gov.ideam.sirh.calidad.model.PuntoMonitoreo;
import co.gov.ideam.sirh.fuentes.model.FnttTramo;
import co.gov.ideam.sirh.fuentes.view.FuenteDelegate;
import co.gov.ideam.sirh.parametros.model.Autoridades;

import co.gov.ideam.sirh.util.ConstantesCalidad;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.model.PartZonificListas;
import co.gov.ideam.sirh.parametros.view.ParametrosDelegate;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;

import co.gov.ideam.sirh.usuarios.agua.model.PuntoVertimiento;
import co.gov.ideam.sirh.usuarios.agua.view.UsuariosAguaDelegate;
import co.gov.ideam.sirh.util.Constantes;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;

import co.gov.ideam.sirh.view.StandarBean;

import java.util.ArrayList;



import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.component.rich.RichMenu;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.nav.RichCommandMenuItem;
import oracle.adf.view.rich.component.rich.output.RichActiveOutputText;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;


public class PuntosMonitoreoVertBean  extends StandarBean{
  
  private RichForm form1;
  private RichDocument document1;
  private RichPanelStretchLayout panelStretchLayout1;
  private RichPanelGroupLayout panelGroupLayout1;
  private RichPanelCollection panelCollection1;
  private List listaPuntosMonitoreo;
  private RichTable t_puntosMonitoreo;
  private RichMenu m_archivo;
  private RichCommandMenuItem cmi_adicionar;
  private RichCommandMenuItem cmi_editar;
  private String accionAdicionar;
  private RichSelectOneChoice tipo_punto;
  private RichCommandButton cb_filtrar;
  private RichInputText filtro_nombre;
  private List listaTipoPuntos;
  private RichPanelGroupLayout panelGroupLayout2;
  private RichActiveOutputText titulo;
  private RichSpacer spacer1;
  private PuntoVertimiento vertimiento;
    private RichPanelGroupLayout panelGroupLayout3;
    private RichCommandLink clinkVert;
    private RichSpacer spacer3;

    public PuntosMonitoreoVertBean(){
       FacesUtils.setActiveBean("puntosMonitoreoVert",
                                "puntosMonitoreoVert",
                                CalidadDelegate.class);
       FacesUtils.removeManagedBeanFromSession("muestrasPunto");
       FacesUtils.removeManagedBeanFromSession("detallePuntoMonitoreo");
       FacesUtils.removeManagedBeanFromSession("adicionarPuntosMonitoreo");
       FacesUtils.removeManagedBeanFromSession("MuestrasTreeHandler");
       FacesUtils.removeManagedBeanFromSession("PuntosMonitoreoTreeHandler");
       
     
       this.load();        
   }
   public void load(){
    
       try{      
         
         this.listaTipoPuntos = this.getListaPorCategoria(ConstantesCalidad.TIPOS_PUNTO_MONITOREO);
        
           CalidadDelegate cald = CalidadDelegate.getInstance();
           UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance(); 
           
           Object obj = FacesUtils.getFromSession("vertimientoSeleccionado");
           if(obj instanceof PuntoVertimiento){
               this.vertimiento = (PuntoVertimiento)obj;
           }else{
               Long codigo = (Long)FacesUtils.getFromSession("vertimientoSeleccionado");
               this.vertimiento = uad.getVertimiento(codigo);
               
               System.out.println("vertimiento--------punto-------------"+codigo);
           }

            listaPuntosMonitoreo = cald.getPuntosMonitoreoVert(this.vertimiento);
         
           System.out.println("vertimiento----------lista-----------"+listaPuntosMonitoreo.size());    
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

  public void setPanelGroupLayout1(RichPanelGroupLayout panelGroupLayout1) {
    this.panelGroupLayout1 = panelGroupLayout1;
  }

  public RichPanelGroupLayout getPanelGroupLayout1() {
    return panelGroupLayout1;
  }

  public void setPanelCollection1(RichPanelCollection panelCollection1) {
    this.panelCollection1 = panelCollection1;
  }

  public RichPanelCollection getPanelCollection1() {
    return panelCollection1;
  }


  public void setListaPuntosMonitoreo(List listaPuntosMonitoreo) {
    this.listaPuntosMonitoreo = listaPuntosMonitoreo;
  }

  public List getListaPuntosMonitoreo() {
    return listaPuntosMonitoreo;
  }

  public void setT_puntosMonitoreo(RichTable t_puntosMonitoreo) {
    this.t_puntosMonitoreo = t_puntosMonitoreo;
  }

  public RichTable getT_puntosMonitoreo() {
    return t_puntosMonitoreo;
  }

  public void setCmi_adicionar(RichCommandMenuItem cmi_adicionar) {
    this.cmi_adicionar = cmi_adicionar;
  }

  public RichCommandMenuItem getCmi_adicionar() {
    return cmi_adicionar;
  }

  public void setCmi_editar(RichCommandMenuItem cmi_editar) {
    this.cmi_editar = cmi_editar;
  }

  public RichCommandMenuItem getCmi_editar() {
    return cmi_editar;
  }

  public void setArchivo(RichMenu m_archivo) {
    this.m_archivo = m_archivo;
  }

  public RichMenu getArchivo() {
    return m_archivo;
  }
  
  
  public void cmi_adicionar_actionListener(ActionEvent actionEvent) {
     accionAdicionar = "adicionarPuntoVert";
    
  }
  
  public String getAccionAdicionar() {
      return accionAdicionar;
  }

  public void setAccionAdicionar(String accionAdicionar) {
      this.accionAdicionar = accionAdicionar;
  }

  public void setTipo_punto(RichSelectOneChoice tipo_punto) {
    this.tipo_punto = tipo_punto;
  }

  public RichSelectOneChoice getTipo_punto() {
    return tipo_punto;
  }

  public void setCb_filtrar(RichCommandButton cb_filtrar) {
    this.cb_filtrar = cb_filtrar;
  }

  public RichCommandButton getCb_filtrar() {
    return cb_filtrar;
  }
  

  public void setListaTipoPuntos(List listaTipoPuntos) {
    this.listaTipoPuntos = listaTipoPuntos;
  }

  public List getListaTipoPuntos() {
    return listaTipoPuntos;
  }

  public void setFiltro_nombre(RichInputText filtro_nombre) {
    this.filtro_nombre = filtro_nombre;
  }

  public RichInputText getFiltro_nombre() {
    return filtro_nombre;
  }
  
  
  public void cb_filtrar_actionListener(ActionEvent actionEvent) {
      CriteriosBusquedaPuntos criterios = new CriteriosBusquedaPuntos();
      if(this.tipo_punto.getValue()!=null){
          criterios.setListatipoPunto(
                  (Lista)tipo_punto.getValue());
      }
  
    if(this.filtro_nombre.getValue()!=null &&
       this.filtro_nombre.getValue().toString().length()>0){
        criterios.setNombre(
            filtro_nombre.getValue().toString());       
    }
    

      
      try{
         
          CalidadDelegate cald = CalidadDelegate.getInstance();
          
          this.listaPuntosMonitoreo= new ArrayList();
          this.listaPuntosMonitoreo = cald.getPuntosMonitoreoVert(criterios, this.vertimiento.getId());
          
      
        AdfFacesContext.getCurrentInstance().addPartialTarget(t_puntosMonitoreo);
      }catch(IdeamException e){
          showMessage(e);
      }
      }
      



    public void cmi_editar_actionListener(ActionEvent actionEvent) {
      accionAdicionar = "";
      if (t_puntosMonitoreo.getSelectedRowData()==null){
          showMessage(getText("seleccionar_registro"),
                      FacesMessage.SEVERITY_ERROR);
          return;
      }
      
     
      PuntoMonitoreo pm = (PuntoMonitoreo)t_puntosMonitoreo.getSelectedRowData();
      FacesUtils.setInSession("puntoSeleccionado", pm);
      accionAdicionar = "detallepunto";
  }


    public void setPanelGroupLayout2(RichPanelGroupLayout panelGroupLayout2) {
        this.panelGroupLayout2 = panelGroupLayout2;
    }

    public RichPanelGroupLayout getPanelGroupLayout2() {
        return panelGroupLayout2;
    }

    public void setTitulo(RichActiveOutputText activeOutputText1) {
        this.titulo = activeOutputText1;
    }

    public RichActiveOutputText getTitulo() {
        return titulo;
    }

    public void setSpacer1(RichSpacer spacer1) {
        this.spacer1 = spacer1;
    }

    public RichSpacer getSpacer1() {
        return spacer1;
    }


    public void setPanelGroupLayout3(RichPanelGroupLayout panelGroupLayout3) {
        this.panelGroupLayout3 = panelGroupLayout3;
    }

    public RichPanelGroupLayout getPanelGroupLayout3() {
        return panelGroupLayout3;
    }


    public void setSpacer3(RichSpacer spacer3) {
        this.spacer3 = spacer3;
    }

    public RichSpacer getSpacer3() {
        return spacer3;
    }


    public void setClinkVert(RichCommandLink clinkTramo) {
        this.clinkVert = clinkTramo;
    }

    public RichCommandLink getClinkVert() {
        return clinkVert;
    }


    public void setVertimiento(PuntoVertimiento vertimiento) {
        this.vertimiento = vertimiento;
    }

    public PuntoVertimiento getVertimiento() {
        return vertimiento;
    }
}
