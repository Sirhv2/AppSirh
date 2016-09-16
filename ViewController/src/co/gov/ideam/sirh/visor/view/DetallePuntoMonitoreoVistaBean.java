package co.gov.ideam.sirh.visor.view;


import co.gov.ideam.sirh.calidad.model.PuntoMonitoreo;
import co.gov.ideam.sirh.calidad.model.PuntoMonitoreoTO;
import co.gov.ideam.sirh.calidad.view.CalidadDelegate;

import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.fuentes.model.FnttTramo;


import co.gov.ideam.sirh.parametros.model.PartZonificListas;
import co.gov.ideam.sirh.parametros.view.ParametrosDelegate;



import co.gov.ideam.sirh.usuarios.agua.model.CaptacionTO;

import co.gov.ideam.sirh.usuarios.agua.model.PuntoVertimientoTO;
import co.gov.ideam.sirh.usuarios.agua.view.UsuariosAguaDelegate;

import co.gov.ideam.sirh.view.StandarBean;
import co.gov.ideam.sirh.util.ConstantesCalidad;

import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;


import java.util.ArrayList;

import java.util.List;


import javax.faces.component.UISelectItems;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichDecorativeBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.output.RichActiveOutputText;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;


import javax.faces.context.FacesContext;


import org.apache.myfaces.trinidad.model.TreeModel;

public class DetallePuntoMonitoreoVistaBean extends StandarBean{
  private RichForm form1;
  private RichDocument document1;
  private List listaDepartamentos;
  private List listaMunicipios;
  private List listaArea;
  private List listaZona;
  private List listaSubzona;
  private List listaFuentes;
  private List listaTramos;
  private List listaTipoPuntos;
  private List listaUbicacion;
  private List listaSistRef;
  private PuntoMonitoreo puntoMonitoreo;
  private PuntoMonitoreoTO puntoMonitoreoTO;
  private PuntoVertimientoTO vertimiento;
  private CaptacionTO captacion;
  private String accionAdicionar;
  private RichPanelStretchLayout panelStretchLayout1;
  private RichPanelSplitter panelSplitter1;
  private RichPanelStretchLayout panelStretchLayout2;
  
  private RichPanelGroupLayout panelGroupLayout1;
  private RichPanelBox panelBox1;
  private RichPanelFormLayout panelFormLayout1;
    private RichInputText tnombre;
  private RichSelectOneChoice selectOneChoice1;
  private UISelectItems selectItems1;
  private RichSelectOneChoice selectOneChoice2;
  private UISelectItems selectItems2;
    private RichSelectOneChoice selectOneChoice5;
  private UISelectItems selectItems5;
  private RichSelectOneChoice selectOneChoice6;
  private UISelectItems selectItems6;
  private RichSelectOneChoice selectOneChoice7;
  private UISelectItems selectItems7;
  private RichSelectOneChoice selectOneChoice8;
  private UISelectItems selectItems8;
  private RichSelectOneChoice selectOneChoice9;
  private UISelectItems selectItems9;
  private RichDecorativeBox decorativeBox1;
  private RichPanelFormLayout panelFormLayout2;
  private RichDecorativeBox decorativeBox2;
  private RichPanelFormLayout panelFormLayout3;
  private RichPanelGroupLayout panelGroupLayout2;
  private RichSpacer spacer3;
  private RichActiveOutputText activeOutputText2;
    private RichDecorativeBox decorativeBox4;
    private List listaMuestras;

    private TreeModel muestraTreeModel;
    private RichSpacer spacer14;
    private String visibleLabel=null;
    private String visibleCapLabel=null;
    private int puntoDeCaptacion=0;


    public DetallePuntoMonitoreoVistaBean() {
       FacesUtils.setActiveBean("detallePuntoMonitoreovista",
                                "Detalle Punto de Monitoreo ",
                                CalidadDelegate.class);   
       
       FacesUtils.removeManagedBeanFromSession("muestrasPunto");
       FacesUtils.removeManagedBeanFromSession("adicionarPuntosMonitoreo");
       FacesUtils.removeManagedBeanFromSession("MuestrasTreeHandler");
       this.load();
   }
   
   

   public void load(){
        
       try{
           CalidadDelegate cald = CalidadDelegate.getInstance();
           UsuariosAguaDelegate usu = UsuariosAguaDelegate.getInstance();
           java.util.Map params = null;
           params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
           String codigo = (String)FacesUtils.getFromSession("puntoVisor");
         if (codigo!=null&&params != null && params.get("punto") != null) { 
             codigo=params.get("punto").toString();
             System.out.println("codigo de punto recibido en detallePuntoMonitoreovista:"+codigo);
         }
        
       
           Long cod=  Long.parseLong(codigo);
           System.out.println(" solicitada desde Visor:"+cod);

         this.puntoMonitoreo = cald.getPuntoMonitoreo(cod); 
         System.out.println(" Consulta la BD:"+this.puntoMonitoreo.getId());
              FacesUtils.setInSession("puntoSeleccionado", this.puntoMonitoreo);
          System.out.println("------------------puntoMonitoreo cargado:"+this.puntoMonitoreo.getId());
          System.out.println("--------Prepara combos de formulario de Punto de monitoreo----------");
          
          
           this.listaUbicacion = this.getListaPorCategoria(ConstantesCalidad.UBICACION_PUNTO_MONITOREO);
           this.listaTipoPuntos = this.getListaPorCategoria(ConstantesCalidad.TIPOS_PUNTO_MONITOREO);
           this.listaSistRef=  this.getListaPorCategoria(ConstantesCalidad.SISTEMA_REFERENCIA);
           this.listaArea = this.cargarZonificacion(null);
           this.listaMunicipios = new ArrayList();
           this.listaZona = new ArrayList();
           this.listaSubzona = new ArrayList();
           this.listaFuentes = new ArrayList();
           this.listaTramos = new ArrayList();
           this.listaMuestras  = new ArrayList();
           
       if(this.puntoMonitoreo.getIdArea()!=null){
           this.listaZona = (List<PartZonificListas>)this.cargarZonificacion(this.puntoMonitoreo.getIdArea().getId());
       }
       if(this.puntoMonitoreo.getIdZona()!=null){
           this.listaSubzona = (List<PartZonificListas>)this.cargarZonificacion(this.puntoMonitoreo.getIdZona().getId());
       } 
        if(this.puntoMonitoreo.getIdSubzona()!=null){
             this.listaFuentes = (List<FnttFuente>)this.getListaFuentes(this.puntoMonitoreo.getIdSubzona().getId());
       } 
      
           
         if(this.puntoMonitoreo.getIdFuente()!=null){
              this.listaTramos = (List<FnttTramo>)this.getListaTramos(this.puntoMonitoreo.getIdFuente().getId().intValue());
          } 
           
     
     }catch(IdeamException e){
         showMessage(e);
     }
      
   }
 

  //ChangeListener Area
  public void selectOneChoice5_valueChangeListener(ValueChangeEvent event) {
    Object area = event.getNewValue();
    this.listaZona = new ArrayList();
  
    try{
        if(area!=null){
            this.listaZona = this.cargarZonificacion(((PartZonificListas)area).getId());
           
        }
    }catch(IdeamException e){            
        showMessage(e);
    }
    AdfFacesContext.getCurrentInstance().addPartialTarget(selectOneChoice6);
   

  }
  //ChangeListener Zona
  public void selectOneChoice6_valueChangeListener(ValueChangeEvent event) {
    Object zona = event.getNewValue();
    this.listaSubzona = new ArrayList();
  
    try{
        if(zona!=null){
            this.listaSubzona = this.cargarZonificacion(((PartZonificListas)zona).getId());
           
        }
    }catch(IdeamException e){            
        showMessage(e);
    }
    AdfFacesContext.getCurrentInstance().addPartialTarget(selectOneChoice7);

  }
  
  //ChangeListener Subzona
  public void selectOneChoice7_valueChangeListener(ValueChangeEvent event) {
    Object subzona = event.getNewValue();
    this.listaFuentes = new ArrayList();
  
    try{
        if(subzona!=null){
            this.listaFuentes = this.getListaFuentes(((PartZonificListas)subzona).getId());
           
        }
    }catch(IdeamException e){            
        showMessage(e);
    }
    AdfFacesContext.getCurrentInstance().addPartialTarget(selectOneChoice8);

  }

  //ChangeListener Fuentes
  public void selectOneChoice8_valueChangeListener(ValueChangeEvent event) {
    Object fuenteid = event.getNewValue();
    this.listaTramos = new ArrayList();
    try{
        if(fuenteid!=null){
            this.listaTramos = this.getListaTramos(((FnttFuente)fuenteid).getId().intValue());
           
        }
    }catch(IdeamException e){            
        showMessage(e);
    }
    AdfFacesContext.getCurrentInstance().addPartialTarget(selectOneChoice9);

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

  public void setListaTipoPuntos(List listaTipoPuntos) {
    this.listaTipoPuntos = listaTipoPuntos;
  }

  public List getListaTipoPuntos() {
    return listaTipoPuntos;
  }

  public void setListaUbicacion(List listaUbicacion) {
    this.listaUbicacion = listaUbicacion;
  }

  public List getListaUbicacion() {
    return listaUbicacion;
  }

  public void setListaSistRef(List listaSistRef) {
    this.listaSistRef = listaSistRef;
  }

  public List getListaSistRef() {
    return listaSistRef;
  }

  public void setPuntoMonitoreo(PuntoMonitoreo puntoMonitoreo) {
    this.puntoMonitoreo = puntoMonitoreo;
  }

  public PuntoMonitoreo getPuntoMonitoreo() {
    return puntoMonitoreo;
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

  public void setPanelStretchLayout2(RichPanelStretchLayout panelStretchLayout2) {
    this.panelStretchLayout2 = panelStretchLayout2;
  }

  public RichPanelStretchLayout getPanelStretchLayout2() {
    return panelStretchLayout2;
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

  public void setPanelFormLayout1(RichPanelFormLayout panelFormLayout1) {
    this.panelFormLayout1 = panelFormLayout1;
  }

  public RichPanelFormLayout getPanelFormLayout1() {
    return panelFormLayout1;
  }


    public void setTnombre(RichInputText inputText1) {
    this.tnombre = inputText1;
  }

  public RichInputText getTnombre() {
    return tnombre;
  }

  public void setSelectOneChoice1(RichSelectOneChoice selectOneChoice1) {
    this.selectOneChoice1 = selectOneChoice1;
  }

  public RichSelectOneChoice getSelectOneChoice1() {
    return selectOneChoice1;
  }

  public void setSelectItems1(UISelectItems selectItems1) {
    this.selectItems1 = selectItems1;
  }

  public UISelectItems getSelectItems1() {
    return selectItems1;
  }

  public void setSelectOneChoice2(RichSelectOneChoice selectOneChoice2) {
    this.selectOneChoice2 = selectOneChoice2;
  }

  public RichSelectOneChoice getSelectOneChoice2() {
    return selectOneChoice2;
  }

  public void setSelectItems2(UISelectItems selectItems2) {
    this.selectItems2 = selectItems2;
  }

  public UISelectItems getSelectItems2() {
    return selectItems2;
  }


    public void setSelectOneChoice5(RichSelectOneChoice selectOneChoice5) {
    this.selectOneChoice5 = selectOneChoice5;
  }

  public RichSelectOneChoice getSelectOneChoice5() {
    return selectOneChoice5;
  }

  public void setSelectItems5(UISelectItems selectItems5) {
    this.selectItems5 = selectItems5;
  }

  public UISelectItems getSelectItems5() {
    return selectItems5;
  }

  public void setSelectOneChoice6(RichSelectOneChoice selectOneChoice6) {
    this.selectOneChoice6 = selectOneChoice6;
  }

  public RichSelectOneChoice getSelectOneChoice6() {
    return selectOneChoice6;
  }

  public void setSelectItems6(UISelectItems selectItems6) {
    this.selectItems6 = selectItems6;
  }

  public UISelectItems getSelectItems6() {
    return selectItems6;
  }

  public void setSelectOneChoice7(RichSelectOneChoice selectOneChoice7) {
    this.selectOneChoice7 = selectOneChoice7;
  }

  public RichSelectOneChoice getSelectOneChoice7() {
    return selectOneChoice7;
  }

  public void setSelectItems7(UISelectItems selectItems7) {
    this.selectItems7 = selectItems7;
  }

  public UISelectItems getSelectItems7() {
    return selectItems7;
  }

  public void setSelectOneChoice8(RichSelectOneChoice selectOneChoice8) {
    this.selectOneChoice8 = selectOneChoice8;
  }

  public RichSelectOneChoice getSelectOneChoice8() {
    return selectOneChoice8;
  }

  public void setSelectItems8(UISelectItems selectItems8) {
    this.selectItems8 = selectItems8;
  }

  public UISelectItems getSelectItems8() {
    return selectItems8;
  }

  public void setSelectOneChoice9(RichSelectOneChoice selectOneChoice9) {
    this.selectOneChoice9 = selectOneChoice9;
  }

  public RichSelectOneChoice getSelectOneChoice9() {
    return selectOneChoice9;
  }

  public void setSelectItems9(UISelectItems selectItems9) {
    this.selectItems9 = selectItems9;
  }

  public UISelectItems getSelectItems9() {
    return selectItems9;
  }


  public void setDecorativeBox1(RichDecorativeBox decorativeBox1) {
    this.decorativeBox1 = decorativeBox1;
  }

  public RichDecorativeBox getDecorativeBox1() {
    return decorativeBox1;
  }

  public void setPanelFormLayout2(RichPanelFormLayout panelFormLayout2) {
    this.panelFormLayout2 = panelFormLayout2;
  }

  public RichPanelFormLayout getPanelFormLayout2() {
    return panelFormLayout2;
  }

  public void setDecorativeBox2(RichDecorativeBox decorativeBox2) {
    this.decorativeBox2 = decorativeBox2;
  }

  public RichDecorativeBox getDecorativeBox2() {
    return decorativeBox2;
  }

  public void setPanelFormLayout3(RichPanelFormLayout panelFormLayout3) {
    this.panelFormLayout3 = panelFormLayout3;
  }

  public RichPanelFormLayout getPanelFormLayout3() {
    return panelFormLayout3;
  }


    public void setPanelGroupLayout2(RichPanelGroupLayout panelGroupLayout2) {
    this.panelGroupLayout2 = panelGroupLayout2;
  }

  public RichPanelGroupLayout getPanelGroupLayout2() {
    return panelGroupLayout2;
  }

  public void setSpacer3(RichSpacer spacer3) {
    this.spacer3 = spacer3;
  }

  public RichSpacer getSpacer3() {
    return spacer3;
  }

  public void setActiveOutputText2(RichActiveOutputText activeOutputText2) {
    this.activeOutputText2 = activeOutputText2;
  }

  public RichActiveOutputText getActiveOutputText2() {
    return activeOutputText2;
  }


    public void setDecorativeBox4(RichDecorativeBox decorativeBox4) {
        this.decorativeBox4 = decorativeBox4;
    }

    public RichDecorativeBox getDecorativeBox4() {
        return decorativeBox4;
    }


    public void setListaMuestras(List listaMuestras) {
        this.listaMuestras = listaMuestras;
    }

    public List getListaMuestras() {
        return listaMuestras;
    }

    public void setMuestraTreeModel(TreeModel muestraTreeModel) {
        this.muestraTreeModel = muestraTreeModel;
    }

    public TreeModel getMuestraTreeModel() {
        return muestraTreeModel;
    }


    public void clink_actionListener(ActionEvent actionEvent) {
            String nombreParametro = 
                (String)actionEvent.getComponent().getAttributes().get("nombreParametro");                
            Object data = 
                actionEvent.getComponent().getAttributes().get("valorParametro");        
            if(nombreParametro!=null && data != null){
                FacesUtils.setInSession(nombreParametro, data);
            }
        }


    public void setAccionAdicionar(String accionAdicionar) {
        this.accionAdicionar = accionAdicionar;
    }

    public String getAccionAdicionar() {
        return accionAdicionar;
    }


    public void setSpacer14(RichSpacer spacer14) {
        this.spacer14 = spacer14;
    }

    public RichSpacer getSpacer14() {
        return spacer14;
    }


    public void setPuntoMonitoreoTO(PuntoMonitoreoTO puntoMonitoreoTO) {
        this.puntoMonitoreoTO = puntoMonitoreoTO;
    }

    public PuntoMonitoreoTO getPuntoMonitoreoTO() {
        return puntoMonitoreoTO;
    }


    public void setVisibleLabel(String visibleLabel) {
        this.visibleLabel = visibleLabel;
    }

    public String getVisibleLabel() {
        return visibleLabel;
    }

    public void setVertimiento(PuntoVertimientoTO vertimiento) {
        this.vertimiento = vertimiento;
    }

    public PuntoVertimientoTO getVertimiento() {
        return vertimiento;
    }


    public void setCaptacion(CaptacionTO captacion) {
        this.captacion = captacion;
    }

    public CaptacionTO getCaptacion() {
        return captacion;
    }

    public void setVisibleCapLabel(String visibleCapLabel) {
        this.visibleCapLabel = visibleCapLabel;
    }

    public String getVisibleCapLabel() {
        return visibleCapLabel;
    }


    public void setPuntoDeCaptacion(int puntoDeCaptacion) {
        this.puntoDeCaptacion = puntoDeCaptacion;
    }

    public int getPuntoDeCaptacion() {
        return puntoDeCaptacion;
    }


}//Fin
