package co.gov.ideam.sirh.fuentes.view;

import co.gov.ideam.sirh.fuentes.model.FnttBuenasPracticas;
import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.fuentes.model.FnttTramo;
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

import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichMenu;
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

import utils.system;

public class BuenasPracticasBean extends StandarBean {

    private FnttFuente fuente;
    private List listaBuenasPracticas;
    private FnttBuenasPracticas buenaPracticaSeleccionado;
    private String accion;

    private RichForm f2;
    private RichDocument d2;
    private RichPanelStretchLayout psl_total;
    private RichPanelFormLayout pfl1;


    private RichPanelFormLayout pfl2;
    private RichInputText it_nombre;
    private RichSelectManyCheckbox smc_criterios;
    private UISelectItems si1;
    private RichInputText it_numero_acto;
    private RichInputDate id_fecha_expedicion;
    private RichInputDate id_fecha_vigencia;
    private RichInputText it_desc_plan;
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
    private RichCommandButton cb_regresar;
    private RichPanelCollection pc1;


    private RichTable t_tramos;
    private RichPanelGroupLayout pgl2;
    private RichOutputLabel ol1;
    private RichSpacer s6;
    private RichSpacer s7;
    private RichMenu m_menu;
    private RichCommandMenuItem cmi_porh;
    private RichCommandMenuItem cmi_usos;
    private RichCommandMenuItem cmi_razones_prohibicion;
    private RichCommandButton cb_listar_calidad;
    private RichSpacer s5;
    private RichSpacer s8;
    private RichCommandMenuItem cmi2_adicionar_avance;
    private RichCommandMenuItem cmi_indicadores;
    private RichPanelStretchLayout psl1;
    private RichPanelGroupLayout pgl4;
    private RichCommandLink cl4;
    private RichPanelBox pb1;
    private RichCommandButton cb_borrar;
    private RichSpacer s1;
    private RichSpacer s15;
    private RichCommandButton cb_reporte;
    private RichPanelCollection panelCollection1;
    private RichMenu menu1;
    private RichCommandMenuItem commandMenuItem1;
    private RichCommandMenuItem commandMenuItem2;
    private RichTable t_practicas;
    private RichOutputText outputText1;
    private RichSpacer spacer8;

    public BuenasPracticasBean() {
        FacesUtils.setActiveBean("practica", "practica",
                                 BuenasPracticasBean.class);
        FacesUtils.removeManagedBeanFromSession("EditarBuenaPracticaBean");
        this.load();
    }

    public void load() {
        try {

            this.listaBuenasPracticas = new ArrayList();

            this.fuente =
                    (FnttFuente)FacesUtils.getFromSession("fuenteSeleccionada");

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

            FuenteDelegate fd = FuenteDelegate.getInstance();

            listaBuenasPracticas = fd.getBuenasPracticasXFuente(fuente.getId());
            
           
        } catch (Exception e) {
            showMessage(e.getMessage());
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

    public void setCmi_porh(RichCommandMenuItem cmi2) {
        this.cmi_porh = cmi2;
    }

    public RichCommandMenuItem getCmi_porh() {
        return cmi_porh;
    }

    public void cmi_editar_actionListener(ActionEvent actionEvent) {
        
        accion = "";
        
        if (this.buenaPracticaSeleccionado == null) {
            showMessage(getText("seleccionar_registro"),
                        FacesMessage.SEVERITY_ERROR);
            return;
        }
        
        FacesUtils.setInSession("practicaSeleccionado",
                                this.buenaPracticaSeleccionado);
        
        accion = "editarBuenaPractica";
        
    }

   
   public void t_practicas_selectionListener(SelectionEvent selectionEvent) {
        RichTable t_practicas = (RichTable)selectionEvent.getSource();
        this.buenaPracticaSeleccionado = (FnttBuenasPracticas)t_practicas.getSelectedRowData();
    }

    public FnttBuenasPracticas getbuenaPracticaSeleccionado() {
        return buenaPracticaSeleccionado;
    }

    public void setbuenaPracticaSeleccionado(FnttBuenasPracticas buenaPracticaSeleccionado) {
        this.buenaPracticaSeleccionado = buenaPracticaSeleccionado;
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
                       "Tramos y Puntos de Monitoreo de la Fuente " +
                       this.fuente.getNombre());
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


    public void setPgl4(RichPanelGroupLayout pgl4) {
        this.pgl4 = pgl4;
    }

    public RichPanelGroupLayout getPgl4() {
        return pgl4;
    }


    public void setCl4(RichCommandLink cl4) {
        this.cl4 = cl4;
    }

    public RichCommandLink getCl4() {
        return cl4;
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


    public void si_borrar_porh_actionListener(ActionEvent actionEvent) {
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
    }

    public void setPanelCollection1(RichPanelCollection panelCollection1) {
        this.panelCollection1 = panelCollection1;
    }

    public RichPanelCollection getPanelCollection1() {
        return panelCollection1;
    }

    public void setMenu1(RichMenu menu1) {
        this.menu1 = menu1;
    }

    public RichMenu getMenu1() {
        return menu1;
    }

    public void setCommandMenuItem1(RichCommandMenuItem commandMenuItem1) {
        this.commandMenuItem1 = commandMenuItem1;
    }

    public RichCommandMenuItem getCommandMenuItem1() {
        return commandMenuItem1;
    }

    public void setCommandMenuItem2(RichCommandMenuItem commandMenuItem2) {
        this.commandMenuItem2 = commandMenuItem2;
    }

    public RichCommandMenuItem getCommandMenuItem2() {
        return commandMenuItem2;
    }


    public void setT_practicas(RichTable t_fuentes) {
        this.t_practicas = t_fuentes;
    }

    public RichTable getT_practicas() {
        return t_practicas;
    }

    public List getlistaBuenasPracticas() {
        
        System.out.println("Da lista "+ listaBuenasPracticas.size());
        return listaBuenasPracticas;
    }

    public void setOutputText1(RichOutputText outputText1) {
        this.outputText1 = outputText1;
    }

    public RichOutputText getOutputText1() {
        return outputText1;
    }

    public void setSpacer8(RichSpacer spacer8) {
        this.spacer8 = spacer8;
    }

    public RichSpacer getSpacer8() {
        return spacer8;
    }
}
