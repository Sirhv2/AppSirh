package co.gov.ideam.sirh.pomca.view;

import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.pomca.model.Actividad;
import co.gov.ideam.sirh.pomca.model.Cuenca;
import co.gov.ideam.sirh.pomca.model.Indicador;
import co.gov.ideam.sirh.pomca.model.Pomca;
import co.gov.ideam.sirh.pomca.model.Programa;
import co.gov.ideam.sirh.pomca.model.Proyecto;

import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.util.Constantes;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;

import java.util.List;

import javax.faces.application.FacesMessage;

import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichMenu;
import oracle.adf.view.rich.component.rich.RichMenuBar;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.nav.RichCommandMenuItem;
import oracle.adf.view.rich.component.rich.output.RichActiveOutputText;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
import oracle.adf.view.rich.component.rich.output.RichSpacer;

import oracle.adf.view.rich.context.AdfFacesContext;

import org.apache.log4j.Logger;
import org.apache.myfaces.trinidad.event.SelectionEvent;

public class ListarIndicadoresBean  extends StandarBean {
    
    static Logger logger = Logger.getLogger(ListarIndicadoresBean.class);

    private Cuenca cuenca;
    private Pomca pomca;
    private Programa programa;
    private Proyecto proyecto;
    private Actividad actividad;

    private List listaIndicadores;
    private Indicador indicadorSeleccionado;

    private RichForm form1;
    private RichDocument document1;
    private RichPanelStretchLayout panelStretchLayout1;
    private RichPanelSplitter panelSplitter1;
    private RichPanelStretchLayout panelStretchLayout2;
    private RichPanelGroupLayout panelGroupLayout1;
    private RichPopup p_borrar;
    private RichDialog d_borrar;
    private RichPanelGroupLayout pgl18;
    private RichOutputText ot_borrar_1;
    private RichPanelGroupLayout pgl19;
    private RichCommandButton cb_si_borrar;
    private RichSpacer s16;
    private RichCommandButton cb_no_borrar;
    private RichPanelBox panelBox1;
    private RichPanelFormLayout panelFormLayout1;
    private RichActiveOutputText tituloPanelMuestra;
    private RichInputText it_nombrePomca;
    private RichInputText inputText2;
    private RichPanelBox panelBox3;
    private RichPanelCollection panelCollection2;
    private RichMenu menu2;
    private RichCommandMenuItem cm_adicionar;
    private RichCommandMenuItem cm_editar;
    private RichCommandMenuItem cm_borrar;
    private RichTable t_actividades;
    private RichInputText inputText1;
    private RichInputText inputText3;
    private RichPanelGroupLayout panelGroupLayout2;
    private RichPanelGroupLayout panelGroupLayout4;
    private RichSpacer spacer1;
    private RichCommandLink cl_todasFuentes;
    private RichSpacer spacer2;
    private RichCommandLink commandLink1;
    private RichSpacer spacer4;
    private RichOutputText ot1;
    private RichSpacer spacer3;
    private RichPanelGroupLayout panelGroupLayout3;
    private RichMenuBar plan_menu;
    private RichMenu menu5;
    private RichCommandMenuItem plan_menu_fases_aprestam;
    private RichCommandMenuItem plan_menu_fases_diagnost;
    private RichCommandMenuItem plan_menu_fases_zonif;
    private RichCommandMenuItem plan_menu_fases_formulac;
    private RichCommandMenuItem plan_menu_fases_ejecu;
    private RichCommandMenuItem plan_menu_fases_seg;
    private RichCommandLink commandLink2;
    private RichSpacer spacer5;

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

    public void setP_borrar(RichPopup p_borrar) {
        this.p_borrar = p_borrar;
    }

    public RichPopup getP_borrar() {
        return p_borrar;
    }

    public void setD_borrar(RichDialog d_borrar) {
        this.d_borrar = d_borrar;
    }

    public RichDialog getD_borrar() {
        return d_borrar;
    }

    public void setPgl18(RichPanelGroupLayout pgl18) {
        this.pgl18 = pgl18;
    }

    public RichPanelGroupLayout getPgl18() {
        return pgl18;
    }

    public void setOt_borrar_1(RichOutputText ot_borrar_1) {
        this.ot_borrar_1 = ot_borrar_1;
    }

    public RichOutputText getOt_borrar_1() {
        return ot_borrar_1;
    }

    public void setPgl19(RichPanelGroupLayout pgl19) {
        this.pgl19 = pgl19;
    }

    public RichPanelGroupLayout getPgl19() {
        return pgl19;
    }

    public void setCb_si_borrar(RichCommandButton cb_si_borrar) {
        this.cb_si_borrar = cb_si_borrar;
    }

    public RichCommandButton getCb_si_borrar() {
        return cb_si_borrar;
    }

    public void setS16(RichSpacer s16) {
        this.s16 = s16;
    }

    public RichSpacer getS16() {
        return s16;
    }

    public void setCb_no_borrar(RichCommandButton cb_no_borrar) {
        this.cb_no_borrar = cb_no_borrar;
    }

    public RichCommandButton getCb_no_borrar() {
        return cb_no_borrar;
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

    public void setTituloPanelMuestra(RichActiveOutputText tituloPanelMuestra) {
        this.tituloPanelMuestra = tituloPanelMuestra;
    }

    public RichActiveOutputText getTituloPanelMuestra() {
        return tituloPanelMuestra;
    }

    public void setIt_nombrePomca(RichInputText it_nombrePomca) {
        this.it_nombrePomca = it_nombrePomca;
    }

    public RichInputText getIt_nombrePomca() {
        return it_nombrePomca;
    }

    public void setInputText2(RichInputText inputText2) {
        this.inputText2 = inputText2;
    }

    public RichInputText getInputText2() {
        return inputText2;
    }

    public void setPanelBox3(RichPanelBox panelBox3) {
        this.panelBox3 = panelBox3;
    }

    public RichPanelBox getPanelBox3() {
        return panelBox3;
    }

    public void setPanelCollection2(RichPanelCollection panelCollection2) {
        this.panelCollection2 = panelCollection2;
    }

    public RichPanelCollection getPanelCollection2() {
        return panelCollection2;
    }

    public void setMenu2(RichMenu menu2) {
        this.menu2 = menu2;
    }

    public RichMenu getMenu2() {
        return menu2;
    }

    public void setCm_adicionar(RichCommandMenuItem cm_adicionar) {
        this.cm_adicionar = cm_adicionar;
    }

    public RichCommandMenuItem getCm_adicionar() {
        return cm_adicionar;
    }

    public void setCm_editar(RichCommandMenuItem cm_editar) {
        this.cm_editar = cm_editar;
    }

    public RichCommandMenuItem getCm_editar() {
        return cm_editar;
    }

    public void setCm_borrar(RichCommandMenuItem cm_borrar) {
        this.cm_borrar = cm_borrar;
    }

    public RichCommandMenuItem getCm_borrar() {
        return cm_borrar;
    }

    public void setT_actividades(RichTable t_actividades) {
        this.t_actividades = t_actividades;
    }

    public RichTable getT_actividades() {
        return t_actividades;
    }


    public void setCuenca(Cuenca cuenca) {
        this.cuenca = cuenca;
    }

    public Cuenca getCuenca() {
        return cuenca;
    }

    public void setPomca(Pomca pomca) {
        this.pomca = pomca;
    }

    public Pomca getPomca() {
        return pomca;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setListaIndicadores(List listaIndicadores) {
        this.listaIndicadores = listaIndicadores;
    }

    public List getListaIndicadores() {
        return listaIndicadores;
    }

    public void setIndicadorSeleccionado(Indicador indicadorSeleccionado) {
        this.indicadorSeleccionado = indicadorSeleccionado;
    }

    public Indicador getIndicadorSeleccionado() {
        return indicadorSeleccionado;
    }

    public void setInputText1(RichInputText inputText1) {
        this.inputText1 = inputText1;
    }

    public RichInputText getInputText1() {
        return inputText1;
    }

    public void setInputText3(RichInputText inputText3) {
        this.inputText3 = inputText3;
    }

    public RichInputText getInputText3() {
        return inputText3;
    }

    public String cl_todasFuentes_action() {
        FacesUtils.removeManagedBeanFromSession("ListarIndicadoresBean");
        FacesUtils.removeFromSession("indicadorSeleccionado");
        FacesUtils.removeManagedBeanFromSession("AdicionarPomcaBean");
        FacesUtils.removeManagedBeanFromSession("EditarProyectoBean");
        return "cuencas";
    }

    public String cl_establecerPomca_action() {
        FacesUtils.removeManagedBeanFromSession("ListarIndicadoresBean");
        FacesUtils.removeFromSession("indicadorSeleccionado");
        FacesUtils.removeManagedBeanFromSession("AdicionarPomcaBean");
        FacesUtils.removeManagedBeanFromSession("EditarProyectoBean");

        return "adicionarPomca";
    }

    public String cm_adicionar_action() {
        
        UsuarioConectadoTO usuarioConectado =
            (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

        Autoridades autoridadAmbiental =
            usuarioConectado.getUsuario().getAutoridadAmbiental();
        
        
        if (autoridadAmbiental == null ||
            autoridadAmbiental.getId().intValue() == Constantes.IDEAM) {
            showMessage(getText("NO_EXISTE_AUTORIDAD_AMBIENTAL"),
                        FacesMessage.SEVERITY_ERROR);
            return "";
        }
        
        return "adicionarIndicador";    
        
    }

    public String cm_editar_action() {
        this.indicadorSeleccionado =
                (Indicador)FacesUtils.getFromSession("indicadorSeleccionado");

        
        if (indicadorSeleccionado == null) {
            showMessage(getText("seleccionar_registro"),
                        FacesMessage.SEVERITY_ERROR);
            return "";
        }

        FacesUtils.removeManagedBeanFromSession("ListarIndicadoresBean");
        FacesUtils.removeFromSession("indicadorSeleccionado");
        FacesUtils.setInSession("indicadorSeleccionado", indicadorSeleccionado);


        return "editarIndicador";
    }

    public String cm_borrar_action() {
        this.indicadorSeleccionado =
                (Indicador)FacesUtils.getFromSession("indicadorSeleccionado");

        if (indicadorSeleccionado == null) {
            showMessage(getText("seleccionar_registro"),
                        FacesMessage.SEVERITY_ERROR);
            return "";
        }

        String params[] = { indicadorSeleccionado.getNombre() };
        String texto = getText("MENSAJE_BORRAR_INDICADOR", params);
        ot_borrar_1.setValue(texto);

        AdfFacesContext.getCurrentInstance().addPartialTarget(p_borrar);
        showPopup(p_borrar, true);

        return null;
    }

    public void t_actividades_selectionListener(SelectionEvent selectionEvent) {
        try {

            RichTable t_act = (RichTable)selectionEvent.getSource();
            this.indicadorSeleccionado = (Indicador)t_act.getSelectedRowData();
            
            FacesUtils.removeFromSession("indicadorSeleccionado");
            FacesUtils.setInSession("indicadorSeleccionado",
                                    indicadorSeleccionado);
            
        } catch (Exception e) {
            showMessage(e.getMessage());
        }
    }

    public String cb_si_borrar_action() {
        try {

            this.indicadorSeleccionado =
                    (Indicador)FacesUtils.getFromSession("indicadorSeleccionado");

            UsuarioConectadoTO usuarioConectado =
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

            Autoridades autoridadAmbiental =
                usuarioConectado.getUsuario().getAutoridadAmbiental();

            indicadorSeleccionado.setCodigoAutoridad(autoridadAmbiental.getId().longValue());

            PomcaDelegate pd = PomcaDelegate.getInstance();
            pd.deleteIndicador(indicadorSeleccionado);
          

            FacesUtils.removeManagedBeanFromSession("ListarIndicadoresBean");
            FacesUtils.removeFromSession("indicadorSeleccionado");

            FacesUtils.removeManagedBeanFromSession("IndicadoresTreeHandler");
            FacesUtils.removeManagedBeanFromSession("EditarProyectoBean");
            
            
            this.proyecto =
                    (Proyecto)FacesUtils.getFromSession("proyectoSeleccionado");

            proyecto = pd.getProyecto(proyecto.getId());
            
            FacesUtils.removeFromSession("proyectoSeleccionado");
            FacesUtils.setInSession("proyectoSeleccionado", proyecto);

            this.programa =
                    (Programa)FacesUtils.getFromSession("programaSeleccionado");

            programa = pd.getPrograma(programa.getId());
            
            FacesUtils.removeFromSession("programaSeleccionado");
            FacesUtils.setInSession("programaSeleccionado", programa);


            this.actividad =
                    (Actividad)FacesUtils.getFromSession("actividadSeleccionada");

            actividad = pd.getActividad(actividad.getId());
            
            FacesUtils.removeFromSession("actividadSeleccionada");
            FacesUtils.setInSession("actividadSeleccionada", actividad);


            FacesUtils.removeManagedBeanFromSession("AdicionarPomcaBean");

            showMessage(getText("MENSAJE_BORRAR_INDICADOR_EXITOSO"),FacesMessage.SEVERITY_INFO);       

            return "listarIndicadores";
            
        } catch (Exception e) {
            showMessage(e.getMessage());
        }

        return null;

    }

    public String cb_no_borrar_action() {
        showPopup(p_borrar, false);
        return null;
    }

    public ListarIndicadoresBean() {

        FacesUtils.setActiveBean("ListarIndicadoresBean", "Listar INDICADORES",
                                 PomcaDelegate.class);
        load();

    }

    private void load() {

        try {

            this.cuenca =
                    (Cuenca)FacesUtils.getFromSession("cuencaSeleccionada");

            PomcaDelegate pd = PomcaDelegate.getInstance();
            pomca = pd.getPomca(cuenca.getId());

            this.programa =
                    (Programa)FacesUtils.getFromSession("programaSeleccionado");

            this.proyecto =
                    (Proyecto)FacesUtils.getFromSession("proyectoSeleccionado");

            this.actividad =
                    (Actividad)FacesUtils.getFromSession("actividadSeleccionada");

            UsuarioConectadoTO usuarioConectado =
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

            Autoridades autoridadAmbiental =
                usuarioConectado.getUsuario().getAutoridadAmbiental();

            listaIndicadores = pd.getIndicadores(actividad);


        } catch (Exception e) {
            showMessage(e.getMessage());
        }


    }


    public String cl_editarProyecto_action() {
        FacesUtils.removeManagedBeanFromSession("ListarIndicadoresBean");
        FacesUtils.removeFromSession("indicadorSeleccionado");
        FacesUtils.removeManagedBeanFromSession("AdicionarPomcaBean");
        FacesUtils.removeManagedBeanFromSession("EditarProyectoBean");

        return "editarProyecto";

    }


    public void setPanelGroupLayout2(RichPanelGroupLayout panelGroupLayout2) {
        this.panelGroupLayout2 = panelGroupLayout2;
    }

    public RichPanelGroupLayout getPanelGroupLayout2() {
        return panelGroupLayout2;
    }

    public void setPanelGroupLayout4(RichPanelGroupLayout panelGroupLayout4) {
        this.panelGroupLayout4 = panelGroupLayout4;
    }

    public RichPanelGroupLayout getPanelGroupLayout4() {
        return panelGroupLayout4;
    }

    public void setSpacer1(RichSpacer spacer1) {
        this.spacer1 = spacer1;
    }

    public RichSpacer getSpacer1() {
        return spacer1;
    }

    public void setCl_todasFuentes(RichCommandLink cl_todasFuentes) {
        this.cl_todasFuentes = cl_todasFuentes;
    }

    public RichCommandLink getCl_todasFuentes() {
        return cl_todasFuentes;
    }

    public void setSpacer2(RichSpacer spacer2) {
        this.spacer2 = spacer2;
    }

    public RichSpacer getSpacer2() {
        return spacer2;
    }

    public void setCommandLink1(RichCommandLink commandLink1) {
        this.commandLink1 = commandLink1;
    }

    public RichCommandLink getCommandLink1() {
        return commandLink1;
    }

    public void setSpacer4(RichSpacer spacer4) {
        this.spacer4 = spacer4;
    }

    public RichSpacer getSpacer4() {
        return spacer4;
    }

    public void setOt1(RichOutputText ot1) {
        this.ot1 = ot1;
    }

    public RichOutputText getOt1() {
        return ot1;
    }

    public void setSpacer3(RichSpacer spacer3) {
        this.spacer3 = spacer3;
    }

    public RichSpacer getSpacer3() {
        return spacer3;
    }

    public void setPanelGroupLayout3(RichPanelGroupLayout panelGroupLayout3) {
        this.panelGroupLayout3 = panelGroupLayout3;
    }

    public RichPanelGroupLayout getPanelGroupLayout3() {
        return panelGroupLayout3;
    }

    public void setPlan_menu(RichMenuBar plan_menu) {
        this.plan_menu = plan_menu;
    }

    public RichMenuBar getPlan_menu() {
        return plan_menu;
    }

    public void setMenu5(RichMenu menu5) {
        this.menu5 = menu5;
    }

    public RichMenu getMenu5() {
        return menu5;
    }

    public void setPlan_menu_fases_aprestam(RichCommandMenuItem plan_menu_fases_aprestam) {
        this.plan_menu_fases_aprestam = plan_menu_fases_aprestam;
    }

    public RichCommandMenuItem getPlan_menu_fases_aprestam() {
        return plan_menu_fases_aprestam;
    }

    public void setPlan_menu_fases_diagnost(RichCommandMenuItem plan_menu_fases_diagnost) {
        this.plan_menu_fases_diagnost = plan_menu_fases_diagnost;
    }

    public RichCommandMenuItem getPlan_menu_fases_diagnost() {
        return plan_menu_fases_diagnost;
    }

    public void setPlan_menu_fases_zonif(RichCommandMenuItem plan_menu_fases_zonif) {
        this.plan_menu_fases_zonif = plan_menu_fases_zonif;
    }

    public RichCommandMenuItem getPlan_menu_fases_zonif() {
        return plan_menu_fases_zonif;
    }

    public void setPlan_menu_fases_formulac(RichCommandMenuItem plan_menu_fases_formulac) {
        this.plan_menu_fases_formulac = plan_menu_fases_formulac;
    }

    public RichCommandMenuItem getPlan_menu_fases_formulac() {
        return plan_menu_fases_formulac;
    }

    public void setPlan_menu_fases_ejecu(RichCommandMenuItem plan_menu_fases_ejecu) {
        this.plan_menu_fases_ejecu = plan_menu_fases_ejecu;
    }

    public RichCommandMenuItem getPlan_menu_fases_ejecu() {
        return plan_menu_fases_ejecu;
    }

    public void setPlan_menu_fases_seg(RichCommandMenuItem plan_menu_fases_seg) {
        this.plan_menu_fases_seg = plan_menu_fases_seg;
    }

    public RichCommandMenuItem getPlan_menu_fases_seg() {
        return plan_menu_fases_seg;
    }

    public void setCommandLink2(RichCommandLink commandLink2) {
        this.commandLink2 = commandLink2;
    }

    public RichCommandLink getCommandLink2() {
        return commandLink2;
    }

    public void setSpacer5(RichSpacer spacer5) {
        this.spacer5 = spacer5;
    }

    public RichSpacer getSpacer5() {
        return spacer5;
    }
}
