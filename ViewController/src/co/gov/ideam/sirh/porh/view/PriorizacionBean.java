package co.gov.ideam.sirh.porh.view;

import co.gov.ideam.sirh.calidad.view.CalidadDelegate;
import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.fuentes.model.FnttTramo;
import co.gov.ideam.sirh.fuentes.view.FuenteDelegate;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Categoria;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.view.ParametrosDelegate;
import co.gov.ideam.sirh.porh.model.PorhOfertaDemanda;
import co.gov.ideam.sirh.porh.model.PorhPlanes;
import co.gov.ideam.sirh.porh.model.PorhPriorizacion;
import co.gov.ideam.sirh.porh.model.PorhTramoTO;
import co.gov.ideam.sirh.porh.model.PorvMetas;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.usuarios.agua.view.UsuariosAguaDelegate;
import co.gov.ideam.sirh.util.Constantes;
import co.gov.ideam.sirh.util.ExportTypes;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;

import co.gov.ideam.sirh.view.util.ArchivoCargadoTO;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputFile;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectManyCheckbox;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.nav.RichCommandMenuItem;
import oracle.adf.view.rich.component.rich.output.RichMessage;
import oracle.adf.view.rich.component.rich.output.RichOutputLabel;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.adf.view.rich.event.DialogEvent;

import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.model.UploadedFile;

public class PriorizacionBean extends StandarBean {
  private RichForm f2;
  private RichDocument d2;
  private RichPanelStretchLayout psl_total;
  private RichPanelFormLayout pfl1;

  private FnttFuente fuente;
  private String esCompartida;
  private String tieneTramos;
  private List listaPrioridades;
  private List listaPrioridadesSeleccionadas;
  private RichPanelFormLayout pfl2;
  private RichInputText it_nombre;
  private RichInputText it_tipo;
  private RichInputText it_descripcion;
  private RichInputText it_compartida;
  private RichInputText it_tiene_tramos;
  private RichSelectManyCheckbox smc_criterios;
  private UISelectItems si1;
  private RichInputText it_numero_acto;
  private RichInputDate id_fecha_expedicion;
  private RichInputDate id_fecha_vigencia;
  private RichInputText it_desc_plan;
  private RichInputText it_otros_criterios_priorizacion;
  private RichInputFile if_dcoumento;
  private RichPanelGroupLayout pgl1;
  private RichCommandButton cb_aceptar;
  private RichSpacer s2;
  private RichCommandButton cb_cancelar;
  private RichSpacer s3;

  private PorhPlanes planOrdenamiento;
  private UploadedFile uploadedFile;
  private ArchivoCargadoTO archivoCargado;
  private RichMessage m2;
  private RichPanelGroupLayout pgl_archivo;
  private RichSpacer s4;
  private RichCommandButton cb_borrar_docto;
  private RichCommandLink cl1;
  private RichPanelGroupLayout pgl3;
  private RichPopup p1;
  private RichDialog d1;
  private RichOutputText ot1;
  private RichCommandButton cb_regresar;
  private RichPanelCollection pc1;

  private List<PorhTramoTO> listaTramos;
  private String accion;
  private FnttTramo tramoSeleccionado;

  private RichTable t_tramos;
  private RichPanelGroupLayout pgl2;
  private RichOutputLabel ol1;
  private RichSpacer s6;
  private RichSpacer s7;
  private RichMenu m_menu;
  private RichCommandMenuItem cmi_puntosMonitoreo;
  private RichCommandMenuItem cmi_usos;
  private RichCommandMenuItem cmi_razones_prohibicion;
  private RichCommandButton cb_listar_calidad;
  private RichSpacer s5;
  private RichSpacer s8;
  private RichCommandMenuItem cmi2_adicionar_avance;
  private RichCommandMenuItem cmi_indicadores;
  private RichPanelStretchLayout psl1;
  private RichSpacer s9;
  private RichSpacer s10;
  private RichPanelGroupLayout pgl4;
  private RichSpacer s11;
  private RichCommandLink cl4;
  private RichSpacer s12;
  private RichSpacer s14;
  private RichPanelBox pb1;
  private RichCommandButton cb_borrar;
  private RichSpacer s1;
  private RichPopup p_borrar;
  private RichDialog d_borrar;
  private RichOutputText ot2;
  private RichPanelGroupLayout pgl5;
  private RichCommandButton cb_si_borrar;
  private RichSpacer s13;
  private RichCommandButton cb_no_borrar;
  private RichSpacer s15;
  private RichCommandButton cb_reporte;
  private RichCommandMenuItem cmi_conflicto;
  private RichCommandMenuItem cmi_afluentes;
  private RichCommandMenuItem cmi_riesgos;
  
  public PriorizacionBean() {
    FacesUtils.setActiveBean("priorizacion", "priorizacion",
                             UsuariosAguaDelegate.class);
    this.load();
  }

  public void load() {
    this.fuente = (FnttFuente)FacesUtils.getFromSession("fuenteSeleccionada");
    this.esCompartida = "No";
    this.tieneTramos = "No";
    if (this.fuente != null) {
      this.esCompartida =
          this.fuente.getEsCompartidaAux().booleanValue() ? "Si" : "No";
      this.tieneTramos =
          this.fuente.getTramosRelacionados().booleanValue() ? "Si" : "No";
    }
    this.listaPrioridadesSeleccionadas = new ArrayList();
    try {
      this.listaPrioridades =
          this.cargarParametro(Categoria.PRIORIZACION_PORH);
    } catch (IdeamException e) {
      showMessage(e);
    }
    try {
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

      PorhDelegate pd = PorhDelegate.getInstance();
      PorhPlanes existePlan =
        pd.getPlanOrdenamiento(this.fuente.getId(), usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue());
      if (existePlan != null) {
        this.planOrdenamiento = existePlan;
        ParametrosDelegate pard = ParametrosDelegate.getInstance();
        if (existePlan != null && existePlan.getListaPriorizacion() != null) {
          Iterator it = existePlan.getListaPriorizacion().iterator();
          while (it.hasNext()) {
            PorhPriorizacion p = (PorhPriorizacion)it.next();
            Lista lista = pard.getLista(p.getId_lista());
            this.listaPrioridadesSeleccionadas.add(lista);
          }
        }
      } else {
        this.planOrdenamiento = new PorhPlanes();
        this.planOrdenamiento.setCodigoFuente(this.fuente.getId());
        this.planOrdenamiento.setCodigoAutoridad(usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue());
      }

      System.out.print("Codigo" + this.planOrdenamiento.getCodigo());

      // Cargar los tramos
      FuenteDelegate fd = FuenteDelegate.getInstance();
      List<FnttTramo> listaTramosFuente= fd.getAllTramosByFuente(this.fuente);
      listaTramos =  new ArrayList();
      PorhTramoTO porhTramoTO;
      CalidadDelegate cd = CalidadDelegate.getInstance(); 
      String sqlUsuarioConcesion = "";
      List<Object[]> usuarioConcesion = new ArrayList();
      String sqlUsuarioVertimiento = "";
      List<Object[]> usuarioVertimiento = new ArrayList();
      for (FnttTramo tramoIterado:listaTramosFuente){
        porhTramoTO = new PorhTramoTO();
        porhTramoTO.setTramo(tramoIterado);
        sqlUsuarioConcesion = " SELECT R.ID FROM SIRHV2.RURT_CONCESIONES R Where ID in ( SELECT R.ID_CONCESION FROM SIRHV2.RURT_CAPTACION R Where ID_TRAMO = "+tramoIterado.getId() +" )" ;
        usuarioConcesion = cd.ejecutaQueryNativo(sqlUsuarioConcesion);
        porhTramoTO.setUsuariosConcesionAguas(usuarioConcesion.size()); 
        sqlUsuarioVertimiento = " SELECT R.ID FROM SIRHV2.RURT_PUNTO_VERTIMIENTO R Where ID_TRAMO = " + tramoIterado.getId() ;
        usuarioVertimiento = cd.ejecutaQueryNativo(sqlUsuarioVertimiento);
        porhTramoTO.setUsuariosVertimiento(usuarioVertimiento.size()); 
        
        listaTramos.add(porhTramoTO);
      }

    } catch (IdeamException e) {
      showMessage(e);
    }
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

  public void setPsl_total(RichPanelStretchLayout psl1) {
    this.psl_total = psl1;
  }

  public RichPanelStretchLayout getPsl_total() {
    return psl_total;
  }

  public void setPfl1(RichPanelFormLayout pfl1) {
    this.pfl1 = pfl1;
  }

  public RichPanelFormLayout getPfl1() {
    return pfl1;
  }


  public FnttFuente getFuente() {
    return fuente;
  }

  public void setFuente(FnttFuente fuente) {
    this.fuente = fuente;
  }

  public void setPfl2(RichPanelFormLayout pfl2) {
    this.pfl2 = pfl2;
  }

  public RichPanelFormLayout getPfl2() {
    return pfl2;
  }

  public void setIt_nombre(RichInputText it1) {
    this.it_nombre = it1;
  }

  public RichInputText getIt_nombre() {
    return it_nombre;
  }

  public void setIt_tipo(RichInputText it2) {
    this.it_tipo = it2;
  }

  public RichInputText getIt_tipo() {
    return it_tipo;
  }

  public void setIt_descripcion(RichInputText it3) {
    this.it_descripcion = it3;
  }

  public RichInputText getIt_descripcion() {
    return it_descripcion;
  }


  public String getEsCompartida() {
    return esCompartida;
  }

  public void setEsCompartida(String esCompartida) {
    this.esCompartida = esCompartida;
  }

  public String getTieneTramos() {
    return tieneTramos;
  }

  public void setTieneTramos(String tieneTramos) {
    this.tieneTramos = tieneTramos;
  }

  public void setIt_compartida(RichInputText it1) {
    this.it_compartida = it1;
  }

  public RichInputText getIt_compartida() {
    return it_compartida;
  }

  public void setIt_tiene_tramos(RichInputText it1) {
    this.it_tiene_tramos = it1;
  }

  public RichInputText getIt_tiene_tramos() {
    return it_tiene_tramos;
  }

  public List getListaPrioridades() {
    return listaPrioridades;
  }

  public void setListaPrioridades(List listaPrioridades) {
    this.listaPrioridades = listaPrioridades;
  }

  public void setSmc_criterios(RichSelectManyCheckbox smc1) {
    this.smc_criterios = smc1;
  }

  public RichSelectManyCheckbox getSmc_criterios() {
    return smc_criterios;
  }

  public void setSi1(UISelectItems si1) {
    this.si1 = si1;
  }

  public UISelectItems getSi1() {
    return si1;
  }

  public void setIt_numero_acto(RichInputText it1) {
    this.it_numero_acto = it1;
  }

  public RichInputText getIt_numero_acto() {
    return it_numero_acto;
  }

  public void setId_fecha_expedicion(RichInputDate id1) {
    this.id_fecha_expedicion = id1;
  }

  public RichInputDate getId_fecha_expedicion() {
    return id_fecha_expedicion;
  }

  public void setId_fecha_vigencia(RichInputDate id2) {
    this.id_fecha_vigencia = id2;
  }

  public RichInputDate getId_fecha_vigencia() {
    return id_fecha_vigencia;
  }

  public void setIf_dcoumento(RichInputFile if1) {
    this.if_dcoumento = if1;
  }

  public RichInputFile getIf_dcoumento() {
    return if_dcoumento;
  }


  public void setPgl1(RichPanelGroupLayout pgl1) {
    this.pgl1 = pgl1;
  }

  public RichPanelGroupLayout getPgl1() {
    return pgl1;
  }

  public void setCb_aceptar(RichCommandButton cb1) {
    this.cb_aceptar = cb1;
  }

  public RichCommandButton getCb_aceptar() {
    return cb_aceptar;
  }

  public void setS2(RichSpacer s2) {
    this.s2 = s2;
  }

  public RichSpacer getS2() {
    return s2;
  }

  public void setCb_cancelar(RichCommandButton cb2) {
    this.cb_cancelar = cb2;
  }

  public RichCommandButton getCb_cancelar() {
    return cb_cancelar;
  }

  public void setS3(RichSpacer s3) {
    this.s3 = s3;
  }

  public RichSpacer getS3() {
    return s3;
  }

  public PorhPlanes getPlanOrdenamiento() {
    return planOrdenamiento;
  }

  public void setPlanOrdenamiento(PorhPlanes planOrdenamiento) {
    this.planOrdenamiento = planOrdenamiento;
  }

  public void cb_aceptar_actionListener(ActionEvent actionEvent) {
    boolean continuar = true;
    if (this.smc_criterios.getValue() == null ||
        ((ArrayList)this.smc_criterios.getValue()).size() == 0) {
      showMessage("obligatorio", FacesMessage.SEVERITY_ERROR, smc_criterios);
      continuar = false;
    }
    if (this.it_numero_acto.getValue() == null ||
        this.it_numero_acto.getValue().toString().length() == 0) {
      showMessage("obligatorio", FacesMessage.SEVERITY_ERROR, it_numero_acto);
      continuar = false;
    }
    if (id_fecha_expedicion.getValue() == null) {
      showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                  id_fecha_expedicion);
      continuar = false;
    }
    if (id_fecha_vigencia.getValue() == null) {
      showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                  id_fecha_vigencia);
      continuar = false;
    }
    if (!continuar) {
      return;
    }
    if (archivoCargado != null && this.planOrdenamiento.getArchivo() == null) {
      try {
        this.planOrdenamiento.setArchivo(archivoCargado.getContent());
      } catch (IdeamException e) {
        showMessage(e);
        continuar = false;
      }
    }
    UsuarioConectadoTO usuarioConectado =
      (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

    Autoridades autoridadAmbiental =
      usuarioConectado.getUsuario().getAutoridadAmbiental();

    try {

      PorhDelegate pd = PorhDelegate.getInstance();

      // Validar si existe otro plan de ordenamiento con la misma resolucion
      PorhPlanes existe =
        pd.getPlanOrdenamiento(this.planOrdenamiento.getNumeroActo(),
                               autoridadAmbiental);
      if (existe != null) {
        if (this.planOrdenamiento.getCodigo() == null ||
            this.planOrdenamiento.getCodigo().longValue() == 0) {
          showMessage("EXISTE_PORH", FacesMessage.SEVERITY_ERROR,
                      it_numero_acto);
          return;
        }

        if (this.planOrdenamiento.getCodigo() != null &&
            this.planOrdenamiento.getCodigo().longValue() > 0 &&
            this.planOrdenamiento.getCodigo().longValue() !=
            existe.getCodigo().longValue()) {
          showMessage("EXISTE_PORH", FacesMessage.SEVERITY_ERROR,
                      it_numero_acto);
          return;

        }
      }
      this.planOrdenamiento.setCodigoAutoridad(new Long(autoridadAmbiental.getId()));
      this.planOrdenamiento.setCodigoFuente(this.fuente.getId());
      PorhPlanes actualizado = pd.updatePlan(this.planOrdenamiento);

      ArrayList lista = new ArrayList();
      Iterator it = ((ArrayList)this.smc_criterios.getValue()).iterator();
      while (it.hasNext()) {
        Lista prioridad = (Lista)it.next();
        PorhPriorizacion p = new PorhPriorizacion();
        p.setPrioridad(prioridad);
        p.setCodigoPlan(actualizado.getCodigo());
        lista.add(p);
      }
      actualizado.setListaPriorizacion(lista);
      System.out.println("----------------------actualizado num acto" +
                         actualizado.getNumeroActo());
      planOrdenamiento = pd.updatePriorizacion(actualizado);
      System.out.println("----------------------planOrdenamiento num acto" +
                         planOrdenamiento.getNumeroActo());
      AdfFacesContext.getCurrentInstance().addPartialTarget(psl_total);
      String params[] = { "del Plan de Ordenamiento" };
      showMessage(getText("mensaje_registro_exitoso", params),
                  FacesMessage.SEVERITY_INFO);

    } catch (IdeamException e) {
      showMessage(e);
    }
  }

  public List getListaPrioridadesSeleccionadas() {
    return listaPrioridadesSeleccionadas;
  }

  public void setListaPrioridadesSeleccionadas(List listaPrioridadesSeleccionadas) {
    this.listaPrioridadesSeleccionadas = listaPrioridadesSeleccionadas;
  }

  public UploadedFile getUploadedFile() {
    return uploadedFile;
  }

  public void setUploadedFile(UploadedFile uploadedFile) {
    this.uploadedFile = uploadedFile;
    if (uploadedFile != null) {
      ArchivoCargadoTO ac = new ArchivoCargadoTO();
      ac.setFile(new File(uploadedFile.getFilename()));
      ac.setFileName(uploadedFile.getFilename());
      try {
        ac.setInputStream(uploadedFile.getInputStream());
      } catch (IOException e) {
        archivoCargado = null;
        showMessage(new IdeamException(e));
      }
      ac.setSize(uploadedFile.getLength());
      ac.setType(uploadedFile.getContentType());
      archivoCargado = ac;
    }
  }

  public ArchivoCargadoTO getArchivoCargado() {
    return archivoCargado;
  }

  public void setArchivoCargado(ArchivoCargadoTO archivoCargado) {
    this.archivoCargado = archivoCargado;
  }

  public void setM2(RichMessage m2) {
    this.m2 = m2;
  }

  public RichMessage getM2() {
    return m2;
  }

  public void if_archivo_valueChangeListener(ValueChangeEvent valueChangeEvent) {
    UploadedFile file = (UploadedFile)valueChangeEvent.getNewValue();
    if (file != null) {
      String tipoArchivo = file.getContentType();
      if (!tipoArchivo.endsWith("pdf")) {
        String params[] = { file.getFilename(), "PDF" };
        showMessage("tipo_archivo_incorrecto", params,
                    FacesMessage.SEVERITY_ERROR, if_dcoumento);
        this.if_dcoumento.resetValue();
        this.setUploadedFile(null);
        this.archivoCargado = null;
        return;
      }
      this.setUploadedFile(file);
    }
  }

  public void setPgl_archivo(RichPanelGroupLayout pgl2) {
    this.pgl_archivo = pgl2;
  }

  public RichPanelGroupLayout getPgl_archivo() {
    return pgl_archivo;
  }

  public void setS4(RichSpacer s4) {
    this.s4 = s4;
  }

  public RichSpacer getS4() {
    return s4;
  }

  public void setCb_borrar_docto(RichCommandButton cb1) {
    this.cb_borrar_docto = cb1;
  }

  public RichCommandButton getCb_borrar_docto() {
    return cb_borrar_docto;
  }

  public void setCl1(RichCommandLink cl1) {
    this.cl1 = cl1;
  }

  public RichCommandLink getCl1() {
    return cl1;
  }

  public void cl_documento_actionListener(ActionEvent actionEvent) {
    showReport(this.planOrdenamiento.getArchivo());
  }

  public void setPgl3(RichPanelGroupLayout pgl3) {
    this.pgl3 = pgl3;
  }

  public RichPanelGroupLayout getPgl3() {
    return pgl3;
  }

  public void setP1(RichPopup p1) {
    this.p1 = p1;
  }

  public RichPopup getP1() {
    return p1;
  }

  public void setD1(RichDialog d1) {
    this.d1 = d1;
  }

  public RichDialog getD1() {
    return d1;
  }

  public void setOt1(RichOutputText ot1) {
    this.ot1 = ot1;
  }

  public RichOutputText getOt1() {
    return ot1;
  }

  public void cb_borrar_docto_actionListener(ActionEvent actionEvent) {
    showPopup(p1, true);
  }

  public void d_borrar_docto_dialogListener(DialogEvent dialogEvent) {
    this.planOrdenamiento.setArchivo(null);
    AdfFacesContext.getCurrentInstance().addPartialTarget(this.if_dcoumento);
    AdfFacesContext.getCurrentInstance().addPartialTarget(this.pgl_archivo);
  }

  public void setCb_regresar(RichCommandButton cb1) {
    this.cb_regresar = cb1;
  }

  public RichCommandButton getCb_regresar() {
    return cb_regresar;
  }


  public void setPc1(RichPanelCollection pc1) {
    this.pc1 = pc1;
  }

  public RichPanelCollection getPc1() {
    return pc1;
  }


  public void setT_tramos(RichTable t1) {
    this.t_tramos = t1;
  }

  public RichTable getT_tramos() {
    return t_tramos;
  }

  public void setPgl2(RichPanelGroupLayout pgl2) {
    this.pgl2 = pgl2;
  }

  public RichPanelGroupLayout getPgl2() {
    return pgl2;
  }

  public void setOl1(RichOutputLabel ol1) {
    this.ol1 = ol1;
  }

  public RichOutputLabel getOl1() {
    return ol1;
  }

  public void setS6(RichSpacer s6) {
    this.s6 = s6;
  }

  public RichSpacer getS6() {
    return s6;
  }

  public void setS7(RichSpacer s7) {
    this.s7 = s7;
  }

  public RichSpacer getS7() {
    return s7;
  }

  public void setM_menu(RichMenu m3) {
    this.m_menu = m3;
  }

  public RichMenu getM_menu() {
    return m_menu;
  }


  public void cmi_puntosMonitoreo_actionListener(ActionEvent actionEvent) {
    setAccion("");

    if (this.planOrdenamiento.getCodigo() == null) {
      showMessage(getText("Primero debe guardar el registro"),
                  FacesMessage.SEVERITY_ERROR);
      return;
    }
    if (this.tramoSeleccionado == null) {
      showMessage(getText("seleccionar_registro"),
                  FacesMessage.SEVERITY_ERROR);
      return;
    }
    FacesUtils.setInSession("planSeleccionado", this.planOrdenamiento);
    FacesUtils.setInSession("tramoSeleccionado", this.tramoSeleccionado);
    accion = "puntosMonitoreoTramo";
  }

  public void cmi_usos_actionListener(ActionEvent actionEvent) {
    setAccion("");
    if (this.tramoSeleccionado == null) {
      showMessage(getText("seleccionar_registro"),
                  FacesMessage.SEVERITY_ERROR);
      return;
    }

    FacesUtils.setInSession("tramoSeleccionado", this.tramoSeleccionado);
    FacesUtils.setInSession("planSeleccionado", this.planOrdenamiento);
    accion = "usosPermitidos";
  }

  public void cmi_prohibicion_actionListener(ActionEvent actionEvent) {
    setAccion("");
    if (this.tramoSeleccionado == null) {
      showMessage(getText("seleccionar_registro"),
                  FacesMessage.SEVERITY_ERROR);
      return;
    }

    FacesUtils.setInSession("tramoSeleccionado", this.tramoSeleccionado);
    FacesUtils.setInSession("planSeleccionado", this.planOrdenamiento);
    accion = "prohibiciones";
  }

  public void cmi_metas_actionListener(ActionEvent actionEvent) {
    setAccion("");
    if (this.tramoSeleccionado == null) {
      showMessage(getText("seleccionar_registro"),
                  FacesMessage.SEVERITY_ERROR);
      return;
    }

    FacesUtils.setInSession("tramoSeleccionado", this.tramoSeleccionado);
    FacesUtils.setInSession("planSeleccionado", this.planOrdenamiento);
    accion = "metasDescontaminacion";
  }

  public void cmi_indicadores_actionListener(ActionEvent actionEvent) {
    setAccion("");
    if (this.tramoSeleccionado == null) {
      showMessage(getText("seleccionar_registro"),
                  FacesMessage.SEVERITY_ERROR);
      return;
    }

    FacesUtils.setInSession("tramoSeleccionado", this.tramoSeleccionado);
    FacesUtils.setInSession("planSeleccionado", this.planOrdenamiento);
    accion = "indicadores";
  }

  public void cmi_conflicto_actionListener(ActionEvent actionEvent) {
    FacesUtils.setInSession("tramoSeleccionado", this.tramoSeleccionado);
    FacesUtils.setInSession("planSeleccionado", this.planOrdenamiento);
    accion = "adicionarConflictoTramo";
  }
  
  public void cmi_afluentes_actionListener(ActionEvent actionEvent) {
    FacesUtils.setInSession("tramoSeleccionado", this.tramoSeleccionado);
    accion = "afluentesTramo";
  }
  
  public void cmi_riesgos_actionListener(ActionEvent actionEvent) {
    FacesUtils.setInSession("tramoSeleccionado", this.tramoSeleccionado);
    FacesUtils.setInSession("fuenteSeleccionada", this.fuente);
    accion = "riesgosTramo";
  }
  
  public void t_tramos_selectionListener(SelectionEvent selectionEvent) {
    RichTable t_tramos = (RichTable)selectionEvent.getSource();
    this.tramoSeleccionado = ((PorhTramoTO)t_tramos.getSelectedRowData()).getTramo();
  }

  public FnttTramo getTramoSeleccionado() {
    return tramoSeleccionado;
  }

  public void setTramoSeleccionado(FnttTramo tramoSeleccionado) {
    this.tramoSeleccionado = tramoSeleccionado;
  }

  public String getAccion() {
    return accion;
  }

  public void setAccion(String accion) {
    this.accion = accion;
  }

  public void setCmi_usos(RichCommandMenuItem cmi2) {
    this.cmi_usos = cmi2;
  }

  public RichCommandMenuItem getCmi_usos() {
    return cmi_usos;
  }

  public void setCmi_razones_prohibicion(RichCommandMenuItem cmi2) {
    this.cmi_razones_prohibicion = cmi2;
  }

  public RichCommandMenuItem getCmi_razones_prohibicion() {
    return cmi_razones_prohibicion;
  }

  public void setCb_listar_calidad(RichCommandButton cb1) {
    this.cb_listar_calidad = cb1;
  }

  public RichCommandButton getCb_listar_calidad() {
    return cb_listar_calidad;
  }

  public void setS5(RichSpacer s5) {
    this.s5 = s5;
  }

  public RichSpacer getS5() {
    return s5;
  }

  public void setS8(RichSpacer s8) {
    this.s8 = s8;
  }

  public RichSpacer getS8() {
    return s8;
  }

  public void cb_listar_puntos_actionListener(ActionEvent actionEvent) {
    HashMap parametros = new HashMap();
    parametros.put("p_id_fuente", this.fuente.getId());
    parametros.put("p_modulo", "Plan de Ordenamiento del Recurso Hídrico");
    parametros.put("p_titulo",
                   "Tramos y Puntos de Monitoreo de la Fuente " + this.fuente.getNombre());
    try {
      this.generateReport("fuentesPorh.jasper", parametros,
                          ExportTypes.EXPORT_EXCEL);
    } catch (IdeamException e) {
      showMessage(e);
    }
  }

  public void setCmi2_adicionar_avance(RichCommandMenuItem cmi2) {
    this.cmi2_adicionar_avance = cmi2;
  }

  public RichCommandMenuItem getCmi2_adicionar_avance() {
    return cmi2_adicionar_avance;
  }

  public void setCmi_indicadores(RichCommandMenuItem cmi2) {
    this.cmi_indicadores = cmi2;
  }

  public RichCommandMenuItem getCmi_indicadores() {
    return cmi_indicadores;
  }

  public void setPsl1(RichPanelStretchLayout psl1) {
    this.psl1 = psl1;
  }

  public RichPanelStretchLayout getPsl1() {
    return psl1;
  }

  public void setS9(RichSpacer s9) {
    this.s9 = s9;
  }

  public RichSpacer getS9() {
    return s9;
  }

  public void setS10(RichSpacer s10) {
    this.s10 = s10;
  }

  public RichSpacer getS10() {
    return s10;
  }

  public void setPgl4(RichPanelGroupLayout pgl4) {
    this.pgl4 = pgl4;
  }

  public RichPanelGroupLayout getPgl4() {
    return pgl4;
  }

  public void setS11(RichSpacer s11) {
    this.s11 = s11;
  }

  public RichSpacer getS11() {
    return s11;
  }

  public void setCl4(RichCommandLink cl4) {
    this.cl4 = cl4;
  }

  public RichCommandLink getCl4() {
    return cl4;
  }

  public void setS12(RichSpacer s12) {
    this.s12 = s12;
  }

  public RichSpacer getS12() {
    return s12;
  }


  public void setS14(RichSpacer s14) {
    this.s14 = s14;
  }

  public RichSpacer getS14() {
    return s14;
  }

  public void setPb1(RichPanelBox pb1) {
    this.pb1 = pb1;
  }

  public RichPanelBox getPb1() {
    return pb1;
  }

  public void setCb_borrar(RichCommandButton cb1) {
    this.cb_borrar = cb1;
  }

  public RichCommandButton getCb_borrar() {
    return cb_borrar;
  }

  public void setS1(RichSpacer s1) {
    this.s1 = s1;
  }

  public RichSpacer getS1() {
    return s1;
  }

  public void setP_borrar(RichPopup p2) {
    this.p_borrar = p2;
  }

  public RichPopup getP_borrar() {
    return p_borrar;
  }

  public void setD_borrar(RichDialog d3) {
    this.d_borrar = d3;
  }

  public RichDialog getD_borrar() {
    return d_borrar;
  }

  public void setOt2(RichOutputText ot2) {
    this.ot2 = ot2;
  }

  public RichOutputText getOt2() {
    return ot2;
  }

  public void cb_borrar_actionListener(ActionEvent actionEvent) {
    showPopup(p_borrar, true);
  }

  public void no_borrar_porh_actionListener(ActionEvent actionEvent) {
    showPopup(p_borrar, false);
  }

  public void si_borrar_porh_actionListener(ActionEvent actionEvent) {
    accion = "";
    try {
      PorhDelegate pd = PorhDelegate.getInstance();
      pd.deletePorh(planOrdenamiento);
      showMessage("Se borró el Plan de Ordenamiento en forma Exitosa",
                  FacesMessage.SEVERITY_INFO);
      accion = "fuentes";
    } catch (IdeamException e) {
      showMessage(e);
    }
  }

  public void setPgl5(RichPanelGroupLayout pgl5) {
    this.pgl5 = pgl5;
  }

  public RichPanelGroupLayout getPgl5() {
    return pgl5;
  }

  public void setCb_si_borrar(RichCommandButton cb1) {
    this.cb_si_borrar = cb1;
  }

  public RichCommandButton getCb_si_borrar() {
    return cb_si_borrar;
  }

  public void setS13(RichSpacer s13) {
    this.s13 = s13;
  }

  public RichSpacer getS13() {
    return s13;
  }

  public void setCb_no_borrar(RichCommandButton cb2) {
    this.cb_no_borrar = cb2;
  }

  public RichCommandButton getCb_no_borrar() {
    return cb_no_borrar;
  }

  public void setS15(RichSpacer s15) {
    this.s15 = s15;
  }

  public RichSpacer getS15() {
    return s15;
  }

  public void setCb_reporte(RichCommandButton cb1) {
    this.cb_reporte = cb1;
  }

  public RichCommandButton getCb_reporte() {
    return cb_reporte;
  }

  public void cb_imprimir_actionListener(ActionEvent actionEvent) {
    try {
      this.planOrdenamiento.setCriteriosSeleccionados(null);
      Iterator it = ((ArrayList)this.smc_criterios.getValue()).iterator();
      while (it.hasNext()) {
        Lista prioridad = (Lista)it.next();
        this.planOrdenamiento.addCriterio(prioridad);
      }

      PorhDelegate pd = PorhDelegate.getInstance();

      List datos = new ArrayList();
      datos.add(this.planOrdenamiento);

      HashMap parametros = new HashMap();
      parametros.put("p_modulo", "Plan de Ordenamiento del Recurso Hídrico");
      parametros.put("p_titulo",
                     "Reporte del Plan de Ordenamiento " + this.fuente.getNombre());
      List tramosReporte = new ArrayList();
      if (this.listaTramos != null) {
        it = this.listaTramos.iterator();
        while (it.hasNext()) {
          //FnttTramo t = (FnttTramo)it.next();

          PorhTramoTO porhTramo = (PorhTramoTO)it.next();

          PorhOfertaDemanda ofertaDemanda =
            pd.getOfertaDemanda(this.fuente.getId(), porhTramo.getTramo().getId());
          if (ofertaDemanda != null) {
            List listaOferta = new ArrayList();
            listaOferta.add(ofertaDemanda);
            porhTramo.setOfertaDemanda(listaOferta);
          }

          porhTramo.setProhibicionesVertimiento(pd.getAllProhibiciones(porhTramo.getTramo()));
          porhTramo.setListaUsos(pd.getParametros(porhTramo.getTramo()));
          List lista = pd.getAllMetas(porhTramo.getTramo());
          Iterator itM = lista.iterator();
          while (itM.hasNext()) {
            PorvMetas meta = (PorvMetas)itM.next();
            meta.setListaAvanceMetas(pd.getAvanceMetas(meta.getId()));
          }
          porhTramo.setMetasDescontaminacion(lista);


          tramosReporte.add(porhTramo);
          System.out.println("se adiciono el tramo " +
                             porhTramo.getTramo().getNombre());

        }
      }
      parametros.put("p_tramos", tramosReporte);


      this.generateReport("porh.jasper", datos, parametros,
                          ExportTypes.EXPORT_PDF);
    } catch (IdeamException e) {
      showMessage(e);
    }
  }

  public void setIt_desc_plan(RichInputText it_desc_plan) {
    this.it_desc_plan = it_desc_plan;
  }

  public RichInputText getIt_desc_plan() {
    return it_desc_plan;
  }

  public void setIt_otros_criterios_priorizacion(RichInputText it_otros_criterios_priorizacion) {
    this.it_otros_criterios_priorizacion = it_otros_criterios_priorizacion;
  }

  public RichInputText getIt_otros_criterios_priorizacion() {
    return it_otros_criterios_priorizacion;
  }

  public void setCmi_puntosMonitoreo(RichCommandMenuItem cmi_puntosMonitoreo) {
    this.cmi_puntosMonitoreo = cmi_puntosMonitoreo;
  }

  public RichCommandMenuItem getCmi_puntosMonitoreo() {
    return cmi_puntosMonitoreo;
  }

  public void setCmi_conflicto(RichCommandMenuItem cmi_conflicto) {
    this.cmi_conflicto = cmi_conflicto;
  }

  public RichCommandMenuItem getCmi_conflicto() {
    return cmi_conflicto;
  }

  public void setListaTramos(List<PorhTramoTO> listaTramos) {
    this.listaTramos = listaTramos;
  }

  public List<PorhTramoTO> getListaTramos() {
    return listaTramos;
  }

  public void setCmi_afluentes(RichCommandMenuItem cmi_afluentes) {
    this.cmi_afluentes = cmi_afluentes;
  }

  public RichCommandMenuItem getCmi_afluentes() {
    return cmi_afluentes;
  }

  public void setCmi_riesgos(RichCommandMenuItem cmi_riesgos) {
    this.cmi_riesgos = cmi_riesgos;
  }

  public RichCommandMenuItem getCmi_riesgos() {
    return cmi_riesgos;
  }
}
