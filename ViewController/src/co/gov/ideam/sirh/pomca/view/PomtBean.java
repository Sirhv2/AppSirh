package co.gov.ideam.sirh.pomca.view;

import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.fuentes.model.FnttTramo;
import co.gov.ideam.sirh.fuentes.view.FuenteDelegate;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Categoria;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.view.ParametrosDelegate;
import co.gov.ideam.sirh.pomca.model.PomtComisiones;
import co.gov.ideam.sirh.pomca.model.PomtJurisdiccion;
import co.gov.ideam.sirh.pomca.model.PomtPlanes;
import co.gov.ideam.sirh.porh.model.PorhPlanes;
import co.gov.ideam.sirh.porh.model.PorhPriorizacion;
import co.gov.ideam.sirh.porh.view.PorhDelegate;
import co.gov.ideam.sirh.porh.view.PriorizacionBean;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.usuarios.agua.model.Predio;
import co.gov.ideam.sirh.usuarios.agua.model.UsuarioAgua;
import co.gov.ideam.sirh.usuarios.agua.view.UsuariosAguaDelegate;
import co.gov.ideam.sirh.util.Constantes;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;

import co.gov.ideam.sirh.view.StandarBean;
import co.gov.ideam.sirh.view.util.ArchivoCargadoTO;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichMenu;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.data.RichTree;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputFile;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectManyCheckbox;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
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

public class PomtBean extends StandarBean {
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
    private RichInputFile if_dcoumento;
    private RichSpacer s1;
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

    private List<FnttTramo> listaTramos;
    private String accion;
    private FnttTramo tramoSeleccionado;

    private RichTable t_tramos;
    private RichPanelGroupLayout pgl2;
    private RichOutputLabel ol1;
    private RichSpacer s6;
    private RichSpacer s7;
    private RichMenu m_menu;
    private RichCommandMenuItem cmi_porh;

    public PomtBean() {
        FacesUtils.setActiveBean("priorizacion", "priorizacion",
                                 UsuariosAguaDelegate.class);
        this.load();
    }

    public void load() {
        this.fuente =
                (FnttFuente)FacesUtils.getFromSession("fuenteSeleccionada");
        this.esCompartida = "No";
        this.tieneTramos = "No";
        if (this.fuente != null) {
            this.esCompartida =
                    this.fuente.getEsCompartidaAux().booleanValue() ? "Si" :
                    "No";
            this.tieneTramos =
                    this.fuente.getTramosRelacionados().booleanValue() ? "Si" :
                    "No";
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
                if (existePlan != null &&
                    existePlan.getListaPriorizacion() != null) {
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

            // Cargar los tramos
            FuenteDelegate fd = FuenteDelegate.getInstance();
            this.listaTramos = fd.getAllTramosByFuente(this.fuente);

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

    public void setIt_desc_plan(RichInputText it2) {
        this.it_desc_plan = it2;
    }

    public RichInputText getIt_desc_plan() {
        return it_desc_plan;
    }

    public void setIf_dcoumento(RichInputFile if1) {
        this.if_dcoumento = if1;
    }

    public RichInputFile getIf_dcoumento() {
        return if_dcoumento;
    }


    public void setS1(RichSpacer s1) {
        this.s1 = s1;
    }

    public RichSpacer getS1() {
        return s1;
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
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        smc_criterios);
            continuar = false;
        }
        if (this.it_numero_acto.getValue() == null ||
            this.it_numero_acto.getValue().toString().length() == 0) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        it_numero_acto);
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
        if (archivoCargado != null &&
            this.planOrdenamiento.getArchivo() == null) {
            try {
                this.planOrdenamiento.setArchivo(archivoCargado.getContent());
            } catch (IdeamException e) {
                showMessage(e);
                continuar = false;
            }
        }
        try {
            ArrayList lista = new ArrayList();
            Iterator it =
                ((ArrayList)this.smc_criterios.getValue()).iterator();
            while (it.hasNext()) {
                Lista prioridad = (Lista)it.next();
                PorhPriorizacion p = new PorhPriorizacion();
                p.setPrioridad(prioridad);
                lista.add(p);
            }
            this.planOrdenamiento.setListaPriorizacion(lista);
            PorhDelegate pd = PorhDelegate.getInstance();
            PorhPlanes actualizado =
                pd.updatePriorizacion(this.planOrdenamiento);
            this.planOrdenamiento = actualizado;
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

    public List<FnttTramo> getListaTramos() {
        return listaTramos;
    }

    public void setListaTramos(List<FnttTramo> listaTramos) {
        this.listaTramos = listaTramos;
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

    public void setCmi_porh(RichCommandMenuItem cmi2) {
        this.cmi_porh = cmi2;
    }

    public RichCommandMenuItem getCmi_porh() {
        return cmi_porh;
    }

    public void cmi_porh_actionListener(ActionEvent actionEvent) {
        setAccion("");
        if (this.tramoSeleccionado == null) {
            showMessage(getText("seleccionar_registro"),
                        FacesMessage.SEVERITY_ERROR);
            return;
        }

        FacesUtils.setInSession("tramoSeleccionado", this.tramoSeleccionado);
        accion = "ofertaDemanda";
    }

    public void t_tramos_selectionListener(SelectionEvent selectionEvent) {
        RichTable t_tramos = (RichTable)selectionEvent.getSource();
        this.tramoSeleccionado = (FnttTramo)t_tramos.getSelectedRowData();
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
}
