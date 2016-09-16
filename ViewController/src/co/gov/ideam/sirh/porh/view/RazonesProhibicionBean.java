package co.gov.ideam.sirh.porh.view;

import co.gov.ideam.sirh.calidad.model.PuntoMonitoreo;
import co.gov.ideam.sirh.calidad.view.CalidadDelegate;
import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.fuentes.model.FnttTramo;
import co.gov.ideam.sirh.fuentes.model.FnttTramoAfluente;
import co.gov.ideam.sirh.fuentes.view.FuenteDelegate;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Categoria;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.view.ParametrosDelegate;
import co.gov.ideam.sirh.porh.model.PorhPlanes;
import co.gov.ideam.sirh.porh.model.PorhPriorizacion;
import co.gov.ideam.sirh.porh.model.PorhTramosProhiPMonitoreo;
import co.gov.ideam.sirh.porh.model.PorhTramosProhibiciones;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.usuarios.agua.view.UsuariosAguaDelegate;
import co.gov.ideam.sirh.util.Constantes;
import co.gov.ideam.sirh.util.ConstantesCalidad;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;

import java.util.Date;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import java.util.List;

import javax.faces.application.FacesMessage;

import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectManyCheckbox;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.adf.view.rich.event.DialogEvent;

import org.apache.myfaces.trinidad.event.SelectionEvent;

public class RazonesProhibicionBean extends StandarBean {
  private RichForm f2;
  private RichDocument d2;
  private RichPanelStretchLayout psl1;

  private PorhPlanes planOrdenamiento;
  private FnttTramo tramo;
  private String nombreFuente;
  private String nombreTramo;
  private List listaProhibiciones;

  private RichPanelBox pb1;
  private RichPanelStretchLayout psl2;

  private RichPanelCollection pc1;
  private RichInputText it_sector;
  private RichInputText it_justificacion;
  private RichInputText it_longitud_prohibicion;
  private RichInputDate id_fecha_inicial;
  private RichInputDate id_fecha_final;
  private RichPanelGroupLayout pgl1;
  private RichCommandButton cb_actualizar;
  private RichPanelGroupLayout pgl3;
  private RichSpacer s4;
  private RichCommandLink cl1;
  private RichSpacer s5;
  private RichCommandLink cl2;
  private RichSpacer s6;
  private RichPanelGroupLayout pgl2;
  private RichSpacer s1;
  private RichTable t_prohibiciones;
  private RichSpacer s2;
  private RichSpacer s3;
  private RichCommandButton cb_borrar;
  private RichCommandButton cb_limpiar;

  private PorhTramosProhibiciones prohibicionSeleccionada;
  private RichPanelGroupLayout pgl4;
  private RichPopup p_borrar;
  private RichDialog d_borrar;
  private RichOutputText ot5;
  //puntos monitoreo
  private RichSelectManyCheckbox smc_puntosMonitoreo;
  private List listaPuntosMonitoreoSeleccionadas;
  private List listaPuntosMonitoreo;
  private UISelectItems si1;

  //Inicio Componentes de coordenadas
  private RichSelectOneChoice sist_referencia_inicio;
  private UISelectItems selectItems13_inicio;
  private RichInputText gra_lat_inicio;
  private RichInputText min_lat_inicio;
  private RichInputText seg_lat_inicio;
  private RichInputText gra_long_inicio;
  private RichInputText min_long_inicio;
  private RichInputText seg_long_inicio;
  private RichInputText t_altitud_inicio;
  private RichInputText t_descripcion_inicio;
  private List listaSistRef_inicio;

  private RichSelectOneChoice sist_referencia_fin;
  private UISelectItems selectItems13_fin;
  private RichInputText gra_lat_fin;
  private RichInputText min_lat_fin;
  private RichInputText seg_lat_fin;
  private RichInputText gra_long_fin;
  private RichInputText min_long_fin;
  private RichInputText seg_long_fin;
  private RichInputText t_altitud_fin;
  private RichInputText t_descripcion_fin;
  private List listaSistRef_fin;
  private RichPanelGroupLayout panelGroupLayout1;
  private RichPanelSplitter panelSplitter0;
  private RichPanelSplitter panelSplitter1;
  //Fin componentes coordenadas
  
  //Inicio componentes listas prohibicion
  private RichSelectOneChoice selectOneChoiceProhibicion;
  private UISelectItems selectItemsProhibicion;
  private List listaProhibicion;
  private RichSelectOneChoice selectOneChoiceProhibicionTipo;
  private UISelectItems selectItemsProhibicionTipo;
  private List listaProhibicionTipo;
  private RichPanelFormLayout panelFormLayoutProhibicion;
  //Fin componentes listas prohibicion
  
  private final Integer CONSTANTE_USO = 45;
  private final Integer CONSTANTE_VERTIMIENTO = 47;

  public RazonesProhibicionBean() {
    FacesUtils.setActiveBean("razonesProhibicion", "razonesProhibicion",
                             UsuariosAguaDelegate.class);
    this.load();
  }

  public void load() {
    UsuarioConectadoTO usuarioConectado =
      (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

    Autoridades autoridadAmbiental =
      usuarioConectado.getUsuario().getAutoridadAmbiental();

    if (autoridadAmbiental == null ||
        autoridadAmbiental.getId().intValue() == Constantes.IDEAM) {
      showMessage(getText("NO_EXISTE_AUTORIDAD_AMBIENTAL"),
                  FacesMessage.SEVERITY_ERROR);
      return;
    }

    this.tramo = (FnttTramo)FacesUtils.getFromSession("tramoSeleccionado");
    FnttFuente fuente =
      (FnttFuente)FacesUtils.getFromSession("fuenteSeleccionada");

    setNombreFuente(fuente.getNombre());
    setNombreTramo(this.tramo.getNombre());
    setPlanOrdenamiento((PorhPlanes)FacesUtils.getFromSession("planSeleccionado"));

    this.prohibicionSeleccionada = new PorhTramosProhibiciones();
    if (prohibicionSeleccionada.getPorhId() == null ||
        prohibicionSeleccionada.getPorhId().longValue() == 0) {
      prohibicionSeleccionada.setPorhId(planOrdenamiento.getCodigo());
    }
    prohibicionSeleccionada.setTramo_id(this.tramo.getId());
    prohibicionSeleccionada.setCodigoAutoridad(autoridadAmbiental.getId().longValue());


    try {
      PorhDelegate pd = PorhDelegate.getInstance();
      listaProhibiciones = pd.getAllProhibiciones(this.tramo);
      
      //lista prohibicion
      this.listaProhibicion = new ArrayList();
      //Categoria Usos
      SelectItem itemUso = new SelectItem(CONSTANTE_USO, "Uso");
      //Categoria Vertimientos
      SelectItem itemVertimiento = new SelectItem(CONSTANTE_VERTIMIENTO, "Vertimiento");
      listaProhibicion.add(itemUso);     
      listaProhibicion.add(itemVertimiento);   
      
      //Inicio Sistema de coordenadas
      this.listaSistRef_inicio =
          this.getListaPorCategoria(ConstantesCalidad.SISTEMA_REFERENCIA);
      this.listaSistRef_fin =
          this.getListaPorCategoria(ConstantesCalidad.SISTEMA_REFERENCIA);
      //Fin Sistema de coordenadas


      this.listaPuntosMonitoreoSeleccionadas = new ArrayList();
      try {
        CalidadDelegate cald = CalidadDelegate.getInstance();
        List<PuntoMonitoreo> puntosMonitoreo = cald.getPuntosMonitoreo(tramo);


        List listaDatos = new ArrayList();
        for (PuntoMonitoreo puntoMonitoreoIterado : puntosMonitoreo) {
          SelectItem item =
            new SelectItem(puntoMonitoreoIterado.getId(), puntoMonitoreoIterado.getNombre());
          listaDatos.add(item);
        }

        listaPuntosMonitoreo = listaDatos;

        this.listaPuntosMonitoreoSeleccionadas = new ArrayList();

      } catch (IdeamException e) {
        showMessage(e);
      }
    } catch (IdeamException e) {
      showMessage(e);
    }
  }
  
  public void valueChangeListenerProhibicion(ValueChangeEvent event)
          throws IdeamException{
    event.getComponent().processUpdates(FacesContext.getCurrentInstance());
    if(prohibicionSeleccionada.getProhibicion()!=null){
      if(prohibicionSeleccionada.getProhibicion().intValue() == CONSTANTE_USO){
        selectOneChoiceProhibicionTipo.setLabel("Tipo de uso");
        ParametrosDelegate pard = ParametrosDelegate.getInstance();  
        listaProhibicionTipo = new ArrayList();
                
        Categoria categoria = pard.getCategoria(Categoria.USOS_AGUA);
        if(categoria!=null && categoria.getLista()!=null){
            List listaCategoria = categoria.getLista();
            Iterator it = listaCategoria.iterator();
            while(it.hasNext()){
                Lista l = (Lista)it.next();
                if(!l.getValor().equalsIgnoreCase("Otro")){
                    SelectItem item = new SelectItem(l, l.getValor());                    
                    listaProhibicionTipo.add(item);                
                }
            }
        }
        
        categoria = pard.getCategoria(Categoria.OTROS_USOS);
        if(categoria!=null && categoria.getLista()!=null){
            List listaCategoria = categoria.getLista();
            Iterator it = listaCategoria.iterator();
            while(it.hasNext()){
                Lista l = (Lista)it.next();                    
                SelectItem item = new SelectItem(l, l.getValor());                    
                listaProhibicionTipo.add(item);                                    
            }
        }
                    
        Collections.sort( listaProhibicionTipo, new UsosComparator() );
      }
        if(prohibicionSeleccionada.getProhibicion().intValue() == CONSTANTE_VERTIMIENTO){
        selectOneChoiceProhibicionTipo.setLabel("Tipo de vertimiento");
        this.listaProhibicionTipo =
            this.cargarParametro(prohibicionSeleccionada.getProhibicion().longValue());
      }

    }
    
    AdfFacesContext.getCurrentInstance().addPartialTarget(panelFormLayoutProhibicion);
        
  }

  public void cb_borrar_actionListener(ActionEvent actionEvent) {
    showPopup(p_borrar, true);
  }

  public void d_borrar_dialogListener(DialogEvent dialogEvent) {
    try {
      PorhDelegate pd = PorhDelegate.getInstance();
      pd.deleteRazonProhibicion(prohibicionSeleccionada);
      listaProhibiciones = pd.getAllProhibiciones(tramo);
      AdfFacesContext.getCurrentInstance().addPartialTarget(t_prohibiciones);

      String params[] = { "de la Razon de Probibicion" };
      showMessage(getText("mensaje_registro_exitoso", params),
                  FacesMessage.SEVERITY_INFO);

    } catch (IdeamException e) {
      showMessage(e);
    }
  }

  public void cb_limpiar_actionListener(ActionEvent actionEvent) {
    limpiar();
  }
  
  public void limpiar(){
    this.prohibicionSeleccionada = new PorhTramosProhibiciones();
    it_sector.setValue(null);
    it_justificacion.setValue(null);
    it_longitud_prohibicion.setValue(null);
    id_fecha_inicial.setValue(null);
    id_fecha_final.setValue(null);
    sist_referencia_inicio.setValue(null);
    gra_lat_inicio.setValue(null);
    min_lat_inicio.setValue(null);
    seg_lat_inicio.setValue(null);
    gra_long_inicio.setValue(null);
    min_long_inicio.setValue(null);
    seg_long_inicio.setValue(null);
    t_altitud_inicio.setValue(null);
    t_descripcion_inicio.setValue(null);
    sist_referencia_fin.setValue(null);
    gra_lat_fin.setValue(null);
    min_lat_fin.setValue(null);
    seg_lat_fin.setValue(null);
    gra_long_fin.setValue(null);
    min_long_fin.setValue(null);
    seg_long_fin.setValue(null);
    t_altitud_fin.setValue(null);
    t_descripcion_fin.setValue(null);
    selectOneChoiceProhibicionTipo.setValue(null);
    listaProhibicionTipo.clear();
    listaPuntosMonitoreoSeleccionadas.clear();
  }

  /*en este metodo solo se valida el sistema de coordenadas */

  public void cb_actualizar_actionListener(ActionEvent actionEvent) throws IdeamException {
    boolean continuar = true;

    if (this.sist_referencia_inicio.getValue() == null) {
      showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                  this.sist_referencia_inicio);
      continuar = false;
    }
    if (this.gra_lat_inicio.getValue() == null ||
        this.gra_lat_inicio.getValue().toString().length() == 0) {
      showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                  this.gra_lat_inicio);
      continuar = false;
    }

    if (this.min_lat_inicio.getValue() == null ||
        this.min_lat_inicio.getValue().toString().length() == 0) {
      showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                  this.min_lat_inicio);
      continuar = false;
    }

    if (this.seg_lat_inicio.getValue() == null ||
        this.seg_lat_inicio.getValue().toString().length() == 0) {
      showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                  this.seg_lat_inicio);
      continuar = false;
    }


    if (this.gra_long_inicio.getValue() == null ||
        this.gra_long_inicio.getValue().toString().length() == 0) {
      showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                  this.gra_long_inicio);
      continuar = false;
    }

    if (this.min_long_inicio.getValue() == null ||
        this.min_long_inicio.getValue().toString().length() == 0) {
      showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                  this.min_long_inicio);
      continuar = false;
    }

    if (this.seg_long_inicio.getValue() == null ||
        this.seg_long_inicio.getValue().toString().length() == 0) {
      showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                  this.seg_long_inicio);
      continuar = false;
    }

    if (this.t_altitud_inicio.getValue() == null ||
        this.t_altitud_inicio.getValue().toString().length() == 0) {
      showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                  this.t_altitud_inicio);
      continuar = false;
    }


    if (this.sist_referencia_fin.getValue() == null) {
      showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                  this.sist_referencia_fin);
      continuar = false;
    }
    if (this.gra_lat_fin.getValue() == null ||
        this.gra_lat_fin.getValue().toString().length() == 0) {
      showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                  this.gra_lat_fin);
      continuar = false;
    }

    if (this.min_lat_fin.getValue() == null ||
        this.min_lat_fin.getValue().toString().length() == 0) {
      showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                  this.min_lat_fin);
      continuar = false;
    }

    if (this.seg_lat_fin.getValue() == null ||
        this.seg_lat_fin.getValue().toString().length() == 0) {
      showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                  this.seg_lat_fin);
      continuar = false;
    }


    if (this.gra_long_fin.getValue() == null ||
        this.gra_long_fin.getValue().toString().length() == 0) {
      showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                  this.gra_long_fin);
      continuar = false;
    }

    if (this.min_long_fin.getValue() == null ||
        this.min_long_fin.getValue().toString().length() == 0) {
      showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                  this.min_long_fin);
      continuar = false;
    }

    if (this.seg_long_fin.getValue() == null ||
        this.seg_long_fin.getValue().toString().length() == 0) {
      showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                  this.seg_long_fin);
      continuar = false;
    }

    if (this.t_altitud_fin.getValue() == null ||
        this.t_altitud_fin.getValue().toString().length() == 0) {
      showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                  this.t_altitud_fin);
      continuar = false;
    }
    if (this.smc_puntosMonitoreo.getValue() == null ||
        ((ArrayList)this.smc_puntosMonitoreo.getValue()).size() == 0) {
      showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                  smc_puntosMonitoreo);
      continuar = false;
    }

    if (continuar) {
      this.save();
    }
  }

  public void save() {
    boolean errores = false;
    if (it_sector.getValue() == null ||
        it_sector.getValue().toString().length() == 0) {
      showMessage("obligatorio", FacesMessage.SEVERITY_ERROR, it_sector);
      errores = true;
    }
    if (it_justificacion.getValue() == null ||
        it_justificacion.getValue().toString().length() == 0) {
      showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                  it_justificacion);
      errores = true;
    }
    if (it_longitud_prohibicion.getValue() == null ||
        it_longitud_prohibicion.getValue().toString().length() == 0) {
      showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                  it_longitud_prohibicion);
      errores = true;
    }
    if (id_fecha_inicial.getValue() == null) {
      showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                  id_fecha_inicial);
      errores = true;
    }
    if (id_fecha_final.getValue() == null) {
      showMessage("obligatorio", FacesMessage.SEVERITY_ERROR, id_fecha_final);
      errores = true;
    }
    if (errores) {
      return;
    }
    UsuarioConectadoTO usuarioConectado =
      (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

    Autoridades autoridadAmbiental =
      usuarioConectado.getUsuario().getAutoridadAmbiental();

    try {
      PorhDelegate pd = PorhDelegate.getInstance();

      System.out.println("----------PROHIBICIONES DEL TRAMO:" +
                         this.tramo.getId());
      prohibicionSeleccionada.setSector(it_sector.getValue().toString());
      prohibicionSeleccionada.setJustificacion(it_justificacion.getValue().toString());
      prohibicionSeleccionada.setLongitud_prohibicion(Double.valueOf(it_longitud_prohibicion.getValue().toString()));
      prohibicionSeleccionada.setFecha_inicial((Date)id_fecha_inicial.getValue());
      prohibicionSeleccionada.setFecha_final((Date)id_fecha_final.getValue());
      prohibicionSeleccionada.setSistemaRef_inicio(prohibicionSeleccionada.getListaSistemaRef_inicio().getCodigo());
      prohibicionSeleccionada.setSistemaRef_fin(prohibicionSeleccionada.getListaSistemaRef_fin().getCodigo());
      
      Lista itemProhibicionTipo = (Lista)selectOneChoiceProhibicionTipo.getValue();
      prohibicionSeleccionada.setProhibicion_tipo(itemProhibicionTipo.getCodigo());
      
      
      prohibicionSeleccionada = pd.updateTramoProhibicion(prohibicionSeleccionada);

      listaProhibiciones = pd.getAllProhibiciones(tramo);
      AdfFacesContext.getCurrentInstance().addPartialTarget(t_prohibiciones);

      String params[] = { "de la Razón de Prohibición" };
      showMessage(getText("mensaje_registro_exitoso", params),
                  FacesMessage.SEVERITY_INFO);
      FuenteDelegate fd = FuenteDelegate.getInstance();
      
      if (prohibicionSeleccionada.getId() != null) {
        List<PorhTramosProhiPMonitoreo> puntosAsociados =
          pd.getPorhTramosProhiPMonitoreo(prohibicionSeleccionada.getId().longValue());

        fd.deleteArray(puntosAsociados.toArray());
      }
      List<PorhTramosProhiPMonitoreo> puntosAsociadosSeleccionados =
        new ArrayList();
      CalidadDelegate cald = CalidadDelegate.getInstance();
      for (Object objIte : listaPuntosMonitoreoSeleccionadas) {
       // SelectItem itemIter = (SelectItem)objIte;
        PorhTramosProhiPMonitoreo nuevoPorhTramosProhiPMonitoreo =
          new PorhTramosProhiPMonitoreo();
        nuevoPorhTramosProhiPMonitoreo.setIdPuntosMonitoreo((Long)objIte);
        nuevoPorhTramosProhiPMonitoreo.setPuntosMonitoreo(cald.getPuntoMonitoreo((Long)objIte));
        nuevoPorhTramosProhiPMonitoreo.setTramosProhibiciones(prohibicionSeleccionada);
        if (prohibicionSeleccionada.getId() != null) {
          nuevoPorhTramosProhiPMonitoreo.setIdTramosProhibiciones(prohibicionSeleccionada.getId().longValue());
        }
        puntosAsociadosSeleccionados.add(nuevoPorhTramosProhiPMonitoreo);
      }

      fd.saveOrUpdateArray(puntosAsociadosSeleccionados.toArray());

      limpiar();
      AdfFacesContext.getCurrentInstance().addPartialTarget(panelGroupLayout1);

    } catch (IdeamException e) {
      showMessage(e);
    }
  }

  public void t_prohibiciones_selectionListener(SelectionEvent selectionEvent) {
    prohibicionSeleccionada =
        (PorhTramosProhibiciones)t_prohibiciones.getSelectedRowData();
    if (prohibicionSeleccionada.getId() != null) {
      it_sector.setValue(prohibicionSeleccionada.getSector());
      it_justificacion.setValue(prohibicionSeleccionada.getJustificacion());
      it_longitud_prohibicion.setValue(prohibicionSeleccionada.getLongitud_prohibicion());
      id_fecha_inicial.setValue(prohibicionSeleccionada.getFecha_inicial());
      id_fecha_final.setValue(prohibicionSeleccionada.getFecha_final());
      cb_actualizar.setText("Actualizar");
      sist_referencia_inicio.setValue(prohibicionSeleccionada.getSistemaRef_inicio());
      gra_lat_inicio.setValue(prohibicionSeleccionada.getLatitud_grados_inicio());
      min_lat_inicio.setValue(prohibicionSeleccionada.getLatitud_minutos_inicio());
      seg_lat_inicio.setValue(prohibicionSeleccionada.getLatitud_segundos_inicio());
      gra_long_inicio.setValue(prohibicionSeleccionada.getLongitud_grados_inicio());
      min_long_inicio.setValue(prohibicionSeleccionada.getLongitud_minutos_inicio());
      seg_long_inicio.setValue(prohibicionSeleccionada.getLongitud_segundos_inicio());
      t_altitud_inicio.setValue(prohibicionSeleccionada.getAltitud_inicio());
      t_descripcion_inicio.setValue(prohibicionSeleccionada.getDescripcion_acceso_inicio());

      sist_referencia_fin.setValue(prohibicionSeleccionada.getSistemaRef_fin());
      gra_lat_fin.setValue(prohibicionSeleccionada.getLatitud_grados_fin());
      min_lat_fin.setValue(prohibicionSeleccionada.getLatitud_minutos_fin());
      seg_lat_fin.setValue(prohibicionSeleccionada.getLatitud_segundos_fin());
      gra_long_fin.setValue(prohibicionSeleccionada.getLongitud_grados_fin());
      min_long_fin.setValue(prohibicionSeleccionada.getLongitud_minutos_fin());
      seg_long_fin.setValue(prohibicionSeleccionada.getLongitud_segundos_fin());
      t_altitud_fin.setValue(prohibicionSeleccionada.getAltitud_fin());
      t_descripcion_fin.setValue(prohibicionSeleccionada.getDescripcion_acceso_fin());

      Lista new1= new Lista();
      new1.setCodigo(prohibicionSeleccionada.getSistemaRef_inicio());
      new1.setValor("");
      prohibicionSeleccionada.setListaSistemaRef_inicio(new1);
      sist_referencia_inicio.setValue(new1);

      Lista new2= new Lista();
      new2.setCodigo(prohibicionSeleccionada.getSistemaRef_fin());
      new2.setValor("");
      prohibicionSeleccionada.setListaSistemaRef_fin(new2);
      sist_referencia_fin.setValue(new2);
      
      Lista prohibicionTipo= new Lista();
      prohibicionTipo.setCodigo(prohibicionSeleccionada.getProhibicion_tipo());
      prohibicionTipo.setValor("");
      
      selectOneChoiceProhibicionTipo.setValue(prohibicionTipo);

      try {
        PorhDelegate pd = PorhDelegate.getInstance();
        List listaDatosSeleccionados = new ArrayList();
        List<PorhTramosProhiPMonitoreo> listaPorhTramosProhiPMonitoreoSeleccionados =
          pd.getPorhTramosProhiPMonitoreo(prohibicionSeleccionada.getId().longValue());
          
        for (PorhTramosProhiPMonitoreo porhTramosProhiPMonitoreoIterado :
             listaPorhTramosProhiPMonitoreoSeleccionados) {

          listaDatosSeleccionados.add(porhTramosProhiPMonitoreoIterado.getIdPuntosMonitoreo());
        }
        this.listaPuntosMonitoreoSeleccionadas.add(listaDatosSeleccionados);
        smc_puntosMonitoreo.setValue(listaDatosSeleccionados);
        
      } catch (IdeamException e) {
        showMessage(e);
      }

    } else {
      it_sector.setValue(null);
      it_justificacion.setValue(null);
      it_longitud_prohibicion.setValue(null);
      id_fecha_inicial.setValue(null);
      id_fecha_final.setValue(null);
      cb_actualizar.setText("Adicionar");
    }
    AdfFacesContext.getCurrentInstance().addPartialTarget(panelGroupLayout1);
  }

  public void setF2(RichForm f2) {
    this.f2 = f2;
  }

  public RichForm getF2() {
    return f2;
  }

  public void setD2(RichDocument d2) {
    this.d2 = d2;
  }

  public RichDocument getD2() {
    return d2;
  }

  public void setPsl1(RichPanelStretchLayout psl1) {
    this.psl1 = psl1;
  }

  public RichPanelStretchLayout getPsl1() {
    return psl1;
  }

  public void setPb1(RichPanelBox pb1) {
    this.pb1 = pb1;
  }

  public RichPanelBox getPb1() {
    return pb1;
  }

  public void setPsl2(RichPanelStretchLayout psl2) {
    this.psl2 = psl2;
  }

  public RichPanelStretchLayout getPsl2() {
    return psl2;
  }

  public void setPc1(RichPanelCollection pc1) {
    this.pc1 = pc1;
  }

  public RichPanelCollection getPc1() {
    return pc1;
  }

  public void setIt_sector(RichInputText it6) {
    this.it_sector = it6;
  }

  public RichInputText getIt_sector() {
    return it_sector;
  }

  public void setIt_justificacion(RichInputText it7) {
    this.it_justificacion = it7;
  }

  public RichInputText getIt_justificacion() {
    return it_justificacion;
  }

  public void setId_fecha_inicial(RichInputDate id1) {
    this.id_fecha_inicial = id1;
  }

  public RichInputDate getId_fecha_inicial() {
    return id_fecha_inicial;
  }

  public void setId_fecha_final(RichInputDate id2) {
    this.id_fecha_final = id2;
  }

  public RichInputDate getId_fecha_final() {
    return id_fecha_final;
  }

  public void setPgl1(RichPanelGroupLayout pgl1) {
    this.pgl1 = pgl1;
  }

  public RichPanelGroupLayout getPgl1() {
    return pgl1;
  }

  public void setCb_actualizar(RichCommandButton cb1) {
    this.cb_actualizar = cb1;
  }

  public RichCommandButton getCb_actualizar() {
    return cb_actualizar;
  }

  public void setPgl3(RichPanelGroupLayout pgl3) {
    this.pgl3 = pgl3;
  }

  public RichPanelGroupLayout getPgl3() {
    return pgl3;
  }

  public void setS4(RichSpacer s4) {
    this.s4 = s4;
  }

  public RichSpacer getS4() {
    return s4;
  }

  public void setCl1(RichCommandLink cl1) {
    this.cl1 = cl1;
  }

  public RichCommandLink getCl1() {
    return cl1;
  }

  public void setS5(RichSpacer s5) {
    this.s5 = s5;
  }

  public RichSpacer getS5() {
    return s5;
  }

  public void setCl2(RichCommandLink cl2) {
    this.cl2 = cl2;
  }

  public RichCommandLink getCl2() {
    return cl2;
  }

  public void setS6(RichSpacer s6) {
    this.s6 = s6;
  }

  public RichSpacer getS6() {
    return s6;
  }


  public String getNombreFuente() {
    return nombreFuente;
  }

  public void setNombreFuente(String nombreFuente) {
    this.nombreFuente = nombreFuente;
  }

  public String getNombreTramo() {
    return nombreTramo;
  }

  public void setNombreTramo(String nombreTramo) {
    this.nombreTramo = nombreTramo;
  }

  public List getListaProhibiciones() {
    return listaProhibiciones;
  }

  public void setListaProhibiciones(List listaProhibiciones) {
    this.listaProhibiciones = listaProhibiciones;
  }

  public void setPgl2(RichPanelGroupLayout pgl2) {
    this.pgl2 = pgl2;
  }

  public RichPanelGroupLayout getPgl2() {
    return pgl2;
  }

  public void setS1(RichSpacer s1) {
    this.s1 = s1;
  }

  public RichSpacer getS1() {
    return s1;
  }

  public void setT_prohibiciones(RichTable t1) {
    this.t_prohibiciones = t1;
  }

  public RichTable getT_prohibiciones() {
    return t_prohibiciones;
  }
  
  public void setS2(RichSpacer s2) {
    this.s2 = s2;
  }

  public RichSpacer getS2() {
    return s2;
  }

  public PorhPlanes getPlanOrdenamiento() {
    return planOrdenamiento;
  }

  public void setPlanOrdenamiento(PorhPlanes planOrdenamiento) {
    this.planOrdenamiento = planOrdenamiento;
  }

  public void setS3(RichSpacer s3) {
    this.s3 = s3;
  }

  public RichSpacer getS3() {
    return s3;
  }

  public void setCb_borrar(RichCommandButton cb1) {
    this.cb_borrar = cb1;
  }

  public RichCommandButton getCb_borrar() {
    return cb_borrar;
  }

  public PorhTramosProhibiciones getProhibicionSeleccionada() {
    return prohibicionSeleccionada;
  }

  public void setProhibicionSeleccionada(PorhTramosProhibiciones prohibicionSeleccionada) {
    this.prohibicionSeleccionada = prohibicionSeleccionada;
  }

  public void setPgl4(RichPanelGroupLayout pgl4) {
    this.pgl4 = pgl4;
  }

  public RichPanelGroupLayout getPgl4() {
    return pgl4;
  }

  public void setP_borrar(RichPopup p1) {
    this.p_borrar = p1;
  }

  public RichPopup getP_borrar() {
    return p_borrar;
  }

  public void setD_borrar(RichDialog d1) {
    this.d_borrar = d1;
  }

  public RichDialog getD_borrar() {
    return d_borrar;
  }

  public void setOt5(RichOutputText ot5) {
    this.ot5 = ot5;
  }

  public RichOutputText getOt5() {
    return ot5;
  }

  public void setTramo(FnttTramo tramo) {
    this.tramo = tramo;
  }

  public FnttTramo getTramo() {
    return tramo;
  }

  public void setSist_referencia_inicio(RichSelectOneChoice sist_referencia_inicio) {
    this.sist_referencia_inicio = sist_referencia_inicio;
  }

  public RichSelectOneChoice getSist_referencia_inicio() {
    return sist_referencia_inicio;
  }

  public void setSelectItems13_inicio(UISelectItems selectItems13_inicio) {
    this.selectItems13_inicio = selectItems13_inicio;
  }

  public UISelectItems getSelectItems13_inicio() {
    return selectItems13_inicio;
  }

  public void setGra_lat_inicio(RichInputText gra_lat_inicio) {
    this.gra_lat_inicio = gra_lat_inicio;
  }

  public RichInputText getGra_lat_inicio() {
    return gra_lat_inicio;
  }

  public void setMin_lat_inicio(RichInputText min_lat_inicio) {
    this.min_lat_inicio = min_lat_inicio;
  }

  public RichInputText getMin_lat_inicio() {
    return min_lat_inicio;
  }

  public void setSeg_lat_inicio(RichInputText seg_lat_inicio) {
    this.seg_lat_inicio = seg_lat_inicio;
  }

  public RichInputText getSeg_lat_inicio() {
    return seg_lat_inicio;
  }

  public void setGra_long_inicio(RichInputText gra_long_inicio) {
    this.gra_long_inicio = gra_long_inicio;
  }

  public RichInputText getGra_long_inicio() {
    return gra_long_inicio;
  }

  public void setMin_long_inicio(RichInputText min_long_inicio) {
    this.min_long_inicio = min_long_inicio;
  }

  public RichInputText getMin_long_inicio() {
    return min_long_inicio;
  }

  public void setSeg_long_inicio(RichInputText seg_long_inicio) {
    this.seg_long_inicio = seg_long_inicio;
  }

  public RichInputText getSeg_long_inicio() {
    return seg_long_inicio;
  }

  public void setT_altitud_inicio(RichInputText t_altitud_inicio) {
    this.t_altitud_inicio = t_altitud_inicio;
  }

  public RichInputText getT_altitud_inicio() {
    return t_altitud_inicio;
  }

  public void setT_descripcion_inicio(RichInputText t_descripcion_inicio) {
    this.t_descripcion_inicio = t_descripcion_inicio;
  }

  public RichInputText getT_descripcion_inicio() {
    return t_descripcion_inicio;
  }

  public void setListaSistRef_inicio(List listaSistRef_inicio) {
    this.listaSistRef_inicio = listaSistRef_inicio;
  }

  public List getListaSistRef_inicio() {
    return listaSistRef_inicio;
  }

  public void setSist_referencia_fin(RichSelectOneChoice sist_referencia_fin) {
    this.sist_referencia_fin = sist_referencia_fin;
  }

  public RichSelectOneChoice getSist_referencia_fin() {
    return sist_referencia_fin;
  }

  public void setSelectItems13_fin(UISelectItems selectItems13_fin) {
    this.selectItems13_fin = selectItems13_fin;
  }

  public UISelectItems getSelectItems13_fin() {
    return selectItems13_fin;
  }

  public void setGra_lat_fin(RichInputText gra_lat_fin) {
    this.gra_lat_fin = gra_lat_fin;
  }

  public RichInputText getGra_lat_fin() {
    return gra_lat_fin;
  }

  public void setMin_lat_fin(RichInputText min_lat_fin) {
    this.min_lat_fin = min_lat_fin;
  }

  public RichInputText getMin_lat_fin() {
    return min_lat_fin;
  }

  public void setSeg_lat_fin(RichInputText seg_lat_fin) {
    this.seg_lat_fin = seg_lat_fin;
  }

  public RichInputText getSeg_lat_fin() {
    return seg_lat_fin;
  }

  public void setGra_long_fin(RichInputText gra_long_fin) {
    this.gra_long_fin = gra_long_fin;
  }

  public RichInputText getGra_long_fin() {
    return gra_long_fin;
  }

  public void setMin_long_fin(RichInputText min_long_fin) {
    this.min_long_fin = min_long_fin;
  }

  public RichInputText getMin_long_fin() {
    return min_long_fin;
  }

  public void setSeg_long_fin(RichInputText seg_long_fin) {
    this.seg_long_fin = seg_long_fin;
  }

  public RichInputText getSeg_long_fin() {
    return seg_long_fin;
  }

  public void setT_altitud_fin(RichInputText t_altitud_fin) {
    this.t_altitud_fin = t_altitud_fin;
  }

  public RichInputText getT_altitud_fin() {
    return t_altitud_fin;
  }

  public void setT_descripcion_fin(RichInputText t_descripcion_fin) {
    this.t_descripcion_fin = t_descripcion_fin;
  }

  public RichInputText getT_descripcion_fin() {
    return t_descripcion_fin;
  }

  public void setListaSistRef_fin(List listaSistRef_fin) {
    this.listaSistRef_fin = listaSistRef_fin;
  }

  public List getListaSistRef_fin() {
    return listaSistRef_fin;
  }

  public void setPanelGroupLayout1(RichPanelGroupLayout panelGroupLayout1) {
    this.panelGroupLayout1 = panelGroupLayout1;
  }

  public RichPanelGroupLayout getPanelGroupLayout1() {
    return panelGroupLayout1;
  }

  public void setPanelSplitter1(RichPanelSplitter panelSplitter1) {
    this.panelSplitter1 = panelSplitter1;
  }

  public RichPanelSplitter getPanelSplitter1() {
    return panelSplitter1;
  }

  public void setCb_limpiar(RichCommandButton cb_limpiar) {
    this.cb_limpiar = cb_limpiar;
  }

  public RichCommandButton getCb_limpiar() {
    return cb_limpiar;
  }

  public void setSmc_puntosMonitoreo(RichSelectManyCheckbox smc_puntosMonitoreo) {
    this.smc_puntosMonitoreo = smc_puntosMonitoreo;
  }

  public RichSelectManyCheckbox getSmc_puntosMonitoreo() {
    return smc_puntosMonitoreo;
  }

  public void setSi1(UISelectItems si1) {
    this.si1 = si1;
  }

  public UISelectItems getSi1() {
    return si1;
  }

  public void setListaPuntosMonitoreoSeleccionadas(List listaPuntosMonitoreoSeleccionadas) {
    this.listaPuntosMonitoreoSeleccionadas = listaPuntosMonitoreoSeleccionadas;
  }

  public List getListaPuntosMonitoreoSeleccionadas() {
    return listaPuntosMonitoreoSeleccionadas;
  }

  public void setListaPuntosMonitoreo(List listaPuntosMonitoreo) {
    this.listaPuntosMonitoreo = listaPuntosMonitoreo;
  }

  public List getListaPuntosMonitoreo() {
    return listaPuntosMonitoreo;
  }

  public void setPanelSplitter0(RichPanelSplitter panelSplitter0) {
    this.panelSplitter0 = panelSplitter0;
  }

  public RichPanelSplitter getPanelSplitter0() {
    return panelSplitter0;
  }

  public void setListaProhibicionTipo(List listaProhibicionTipo) {
    this.listaProhibicionTipo = listaProhibicionTipo;
  }

  public List getListaProhibicionTipo() {
    return listaProhibicionTipo;
  }

  public void setSelectOneChoiceProhibicion(RichSelectOneChoice selectOneChoiceProhibicion) {
    this.selectOneChoiceProhibicion = selectOneChoiceProhibicion;
  }

  public RichSelectOneChoice getSelectOneChoiceProhibicion() {
    return selectOneChoiceProhibicion;
  }

  public void setSelectItemsProhibicion(UISelectItems selectItemsProhibicion) {
    this.selectItemsProhibicion = selectItemsProhibicion;
  }

  public UISelectItems getSelectItemsProhibicion() {
    return selectItemsProhibicion;
  }

  public void setListaProhibicion(List listaProhibicion) {
    this.listaProhibicion = listaProhibicion;
  }

  public List getListaProhibicion() {
    return listaProhibicion;
  }

  public void setSelectOneChoiceProhibicionTipo(RichSelectOneChoice selectOneChoiceProhibicionTipo) {
    this.selectOneChoiceProhibicionTipo = selectOneChoiceProhibicionTipo;
  }

  public RichSelectOneChoice getSelectOneChoiceProhibicionTipo() {
    return selectOneChoiceProhibicionTipo;
  }

  public void setSelectItemsProhibicionTipo(UISelectItems selectItemsProhibicionTipo) {
    this.selectItemsProhibicionTipo = selectItemsProhibicionTipo;
  }

  public UISelectItems getSelectItemsProhibicionTipo() {
    return selectItemsProhibicionTipo;
  }

  public void setPanelFormLayoutProhibicion(RichPanelFormLayout panelFormLayoutProhibicion) {
    this.panelFormLayoutProhibicion = panelFormLayoutProhibicion;
  }

  public RichPanelFormLayout getPanelFormLayoutProhibicion() {
    return panelFormLayoutProhibicion;
  }

  public void setIt_longitud_prohibicion(RichInputText it_longitud_prohibicion) {
    this.it_longitud_prohibicion = it_longitud_prohibicion;
  }

  public RichInputText getIt_longitud_prohibicion() {
    return it_longitud_prohibicion;
  }
}
