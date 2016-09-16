package co.gov.ideam.sirh.pomca.view;

import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.pomca.model.Actividad;
import co.gov.ideam.sirh.pomca.model.Cuenca;
import co.gov.ideam.sirh.pomca.model.Indicador;
import co.gov.ideam.sirh.pomca.model.Pomca;
import co.gov.ideam.sirh.pomca.model.Programa;
import co.gov.ideam.sirh.pomca.model.Proyecto;

import co.gov.ideam.sirh.reportes.view.ReporteDelegate;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.usuarios.agua.model.UsuarioAgua;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UISelectItems;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.output.RichActiveOutputText;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichSpacer;

import oracle.adf.view.rich.context.AdfFacesContext;

import org.apache.log4j.Logger;

public class SeguimientoPomcaBean extends StandarBean {

    static Logger logger = Logger.getLogger(RegistrarGestionBean.class);

    private Cuenca cuenca;
    private Pomca pomca;
    private Programa programa;
    private Proyecto proyecto;
    private Actividad actividad;

    private Programa programaSeleccionado;
    private Proyecto proyectoSeleccionado;
    private Actividad actividadSeleccionado;

    private List listaProgramas;
    private List listaProyectos;
    private List listaActividades;

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
    private RichSelectOneChoice lc_programas;
    private UISelectItems itemsProgramas;
    private RichSelectOneChoice cl_proyectos;
    private UISelectItems itemsProyectos;
    private RichSelectOneChoice cl_actividades;
    private UISelectItems itemsActividades;
    private RichCommandButton cb_actualizar;
    private RichPanelGroupLayout panelGroupLayout2;
    private RichSpacer spacer1;
    private RichCommandLink cl_todasFuentes;
    private RichSpacer spacer2;
    private RichOutputText ot1;
    private RichSelectBooleanCheckbox cb_consolidar;

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

    public void setLc_programas(RichSelectOneChoice lc_programas) {
        this.lc_programas = lc_programas;
    }

    public RichSelectOneChoice getLc_programas() {
        return lc_programas;
    }

    public void setItemsProgramas(UISelectItems itemsProgramas) {
        this.itemsProgramas = itemsProgramas;
    }

    public UISelectItems getItemsProgramas() {
        return itemsProgramas;
    }

    public void setCl_proyectos(RichSelectOneChoice cl_proyectos) {
        this.cl_proyectos = cl_proyectos;
    }

    public RichSelectOneChoice getCl_proyectos() {
        return cl_proyectos;
    }

    public void setItemsProyectos(UISelectItems itemsProyectos) {
        this.itemsProyectos = itemsProyectos;
    }

    public UISelectItems getItemsProyectos() {
        return itemsProyectos;
    }

    public void setCl_actividades(RichSelectOneChoice cl_actividades) {
        this.cl_actividades = cl_actividades;
    }

    public RichSelectOneChoice getCl_actividades() {
        return cl_actividades;
    }

    public void setItemsActividades(UISelectItems itemsActividades) {
        this.itemsActividades = itemsActividades;
    }

    public UISelectItems getItemsActividades() {
        return itemsActividades;
    }


    public void setCb_actualizar(RichCommandButton cb_actualizar) {
        this.cb_actualizar = cb_actualizar;
    }

    public RichCommandButton getCb_actualizar() {
        return cb_actualizar;
    }

    public void setPanelGroupLayout2(RichPanelGroupLayout panelGroupLayout2) {
        this.panelGroupLayout2 = panelGroupLayout2;
    }

    public RichPanelGroupLayout getPanelGroupLayout2() {
        return panelGroupLayout2;
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

    public void setOt1(RichOutputText ot1) {
        this.ot1 = ot1;
    }

    public RichOutputText getOt1() {
        return ot1;
    }

    public SeguimientoPomcaBean() {

        FacesUtils.setActiveBean("SeguimientoPomcaBean", "Seguimiento Pomca",
                                 PomcaDelegate.class);
        load();

    }

    private void load() {

        try {

            logger.info("Se ingreso a load");
            System.out.println("Se ingreso a load de SeguimientoPomcaBean");

            cuenca =
                    (Cuenca)FacesUtils.getFromSession("cuencaSeleccionada");

            UsuarioConectadoTO usuarioConectado =
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

            Autoridades autoridadAmbiental =
                usuarioConectado.getUsuario().getAutoridadAmbiental();

            PomcaDelegate pd = PomcaDelegate.getInstance();
            pomca = pd.getPomca(cuenca.getId());

            if (pomca == null) {
                listaProgramas = new ArrayList();
                showMessage("No hay POMCA definido para la fuente seleccionada");
                return;

            }
            List lProg = pd.getProgramas(pomca);

            listaProgramas = new ArrayList();
            if (lProg != null) {

                Programa progTodos = new Programa();
                progTodos.setNombre("TODOS");
                progTodos.setId(0L);

                SelectItem todos =
                    new SelectItem(progTodos, progTodos.getNombre());
                listaProgramas.add(todos);

                Iterator it = lProg.iterator();
                while (it.hasNext()) {
                    Programa prog = (Programa)it.next();
                    SelectItem si = new SelectItem(prog, prog.getNombre());
                    listaProgramas.add(si);
                }
            }


            listaProyectos = new ArrayList();
            listaActividades = new ArrayList();

            logger.info("Paso load OK");
            System.out.println("Paso load OK SeguimientoPomcaBean");


        } catch (Exception e) {
            showMessage(e.getMessage());
        }

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

    public void setProgramaSeleccionado(Programa programaSeleccionado) {
        this.programaSeleccionado = programaSeleccionado;
    }

    public Programa getProgramaSeleccionado() {
        return programaSeleccionado;
    }

    public void setProyectoSeleccionado(Proyecto proyectoSeleccionado) {
        this.proyectoSeleccionado = proyectoSeleccionado;
    }

    public Proyecto getProyectoSeleccionado() {
        return proyectoSeleccionado;
    }

    public void setActividadSeleccionado(Actividad actividadSeleccionado) {
        this.actividadSeleccionado = actividadSeleccionado;
    }

    public Actividad getActividadSeleccionado() {
        return actividadSeleccionado;
    }

    public void setListaProgramas(List listaProgramas) {
        this.listaProgramas = listaProgramas;
    }

    public List getListaProgramas() {
        return listaProgramas;
    }

    public void setListaProyectos(List listaProyectos) {
        this.listaProyectos = listaProyectos;
    }

    public List getListaProyectos() {
        return listaProyectos;
    }

    public void setListaActividades(List listaActividades) {
        this.listaActividades = listaActividades;
    }

    public List getListaActividades() {
        return listaActividades;
    }

    public String cl_todasFuentes_action() {
        FacesUtils.removeManagedBeanFromSession("SeguimientoPomcaBean");
        FacesUtils.removeFromSession("fuenteSeleccionada");
        return "cuencas";
    }

    public void cb_actualizar_actionListener(ActionEvent actionEvent) {
        try {

            logger.info("Ingresa a cb_actualizar_action");

            if (programaSeleccionado == null) {
                showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                            this.lc_programas);
                return;

            }

            if (proyectoSeleccionado == null) {
                showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                            this.cl_proyectos);
                return;

            }


            if (actividadSeleccionado == null) {
                showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                            this.cl_actividades);
                return;

            }

            this.cuenca =
                    (Cuenca)FacesUtils.getFromSession("cuencaSeleccionada");

            HashMap parametros = new HashMap();
            parametros.put("p_modulo", "POMCA");
            parametros.put("p_titulo", "Registro de Gestion");
            parametros.put("p_id_cuenca", cuenca.getId());
            parametros.put("p_id_programa", programaSeleccionado.getId());
            parametros.put("p_id_proyecto", proyectoSeleccionado.getId());
            parametros.put("p_id_actividad", actividadSeleccionado.getId());

            if (!(Boolean)cb_consolidar.getValue()) {

                
                if (programaSeleccionado.getNombre().equals("TODOS"))
                    generateReport("gestionPomcaDetalladaPrg.jasper",
                                   parametros, ReporteDelegate.PDF);

                else if (proyectoSeleccionado.getNombre().equals("TODOS"))
                    generateReport("gestionPomcaDetalladaPro.jasper",
                                   parametros, ReporteDelegate.PDF);
                else if (actividadSeleccionado.getNombre().equals("TODOS"))
                    generateReport("gestionPomcaDetalladaAct.jasper",
                                   parametros, ReporteDelegate.PDF);
                else
                    generateReport("gestionPomcaDetallada.jasper", parametros,
                                   ReporteDelegate.PDF);

            }

            if ((Boolean)cb_consolidar.getValue()) {
                
               
                if (programaSeleccionado.getNombre().equals("TODOS"))
                    generateReport("gestionPomcaConsolidadoPrg.jasper",
                                   parametros, ReporteDelegate.PDF);

                else if (proyectoSeleccionado.getNombre().equals("TODOS"))
                    generateReport("gestionPomcaConsolidadoPro.jasper",
                                   parametros, ReporteDelegate.PDF);
                else if (actividadSeleccionado.getNombre().equals("TODOS"))
                    generateReport("gestionPomcaConsolidadoAct.jasper",
                                   parametros, ReporteDelegate.PDF);
                else
                    generateReport("gestionPomcaConsolidado.jasper", parametros,
                                   ReporteDelegate.PDF);

            }

        } catch (Exception e) {
            showMessage(e.getMessage());
        }

        return;
    }


    public void lc_programa_valueChangeListener(ValueChangeEvent event) throws IdeamException {

        logger.info("Se ingreso a lc_programa_valueChangeListener");

        Programa prog = null;


        if (event.getNewValue() == null)
            prog = null;
        else {

            prog = (Programa)event.getNewValue();
            programaSeleccionado = prog;


            if (prog.getNombre().equals("TODOS")) {


                listaProyectos = new ArrayList();
                listaActividades = new ArrayList();

                Proyecto proyTodos = new Proyecto();
                proyTodos.setNombre("TODOS");
                proyTodos.setId(0L);

                SelectItem todosProy =
                    new SelectItem(proyTodos, proyTodos.getNombre());
                listaProyectos.add(todosProy);

                Actividad actTodos = new Actividad();
                actTodos.setNombre("TODOS");
                actTodos.setId(0L);

                SelectItem todosAct =
                    new SelectItem(actTodos, actTodos.getNombre());
                listaActividades.add(todosAct);


                AdfFacesContext.getCurrentInstance().addPartialTarget(this.cl_proyectos);
                AdfFacesContext.getCurrentInstance().addPartialTarget(this.cl_actividades);
                return;
            }
        }


        if (prog == null) {

            programaSeleccionado = prog;

            listaProyectos = new ArrayList();
            listaActividades = new ArrayList();


            AdfFacesContext.getCurrentInstance().addPartialTarget(this.cl_proyectos);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.cl_actividades);

            return;


        }

        this.cuenca =
                (Cuenca)FacesUtils.getFromSession("cuencaSeleccionada");

        UsuarioConectadoTO usuarioConectado =
            (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

        Autoridades autoridadAmbiental =
            usuarioConectado.getUsuario().getAutoridadAmbiental();

        PomcaDelegate pd = PomcaDelegate.getInstance();
        pomca = pd.getPomca(cuenca.getId());

        List lProy = pd.getProyectos(prog);

        listaProyectos = new ArrayList();
        if (lProy != null) {

            Proyecto proyTodos = new Proyecto();
            proyTodos.setNombre("TODOS");
            proyTodos.setId(0L);

            SelectItem todosProy =
                new SelectItem(proyTodos, proyTodos.getNombre());
            listaProyectos.add(todosProy);

            Iterator it = lProy.iterator();
            while (it.hasNext()) {
                Proyecto proy = (Proyecto)it.next();
                SelectItem si = new SelectItem(proy, proy.getNombre());
                listaProyectos.add(si);
            }
        }

        listaActividades = new ArrayList();

        AdfFacesContext.getCurrentInstance().addPartialTarget(this.cl_proyectos);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.cl_actividades);


        logger.info("Paso OK lc_programa_valueChangeListener");

    }

    public void lc_proyectos_valueChangeListener(ValueChangeEvent event) throws IdeamException {

        logger.info("Se ingreso a lc_proyectos_valueChangeListener");

        Proyecto proy = null;

        if (event.getNewValue() == null)
            proy = null;
        else {
            proy = (Proyecto)event.getNewValue();
            proyectoSeleccionado = proy;


            if (proy.getNombre().equals("TODOS")) {


                listaActividades = new ArrayList();

                Actividad actTodos = new Actividad();
                actTodos.setNombre("TODOS");
                actTodos.setId(0L);

                SelectItem todosAct =
                    new SelectItem(actTodos, actTodos.getNombre());
                listaActividades.add(todosAct);

                AdfFacesContext.getCurrentInstance().addPartialTarget(this.cl_actividades);
                return;
            } else {
            }
        }


        if (proy == null) {
            proyectoSeleccionado = proy;
            listaActividades = new ArrayList();
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.cl_actividades);
            return;

        }

        this.cuenca =
                (Cuenca)FacesUtils.getFromSession("cuencaSeleccionada");

        UsuarioConectadoTO usuarioConectado =
            (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

        Autoridades autoridadAmbiental =
            usuarioConectado.getUsuario().getAutoridadAmbiental();

        PomcaDelegate pd = PomcaDelegate.getInstance();
        pomca = pd.getPomca(cuenca.getId());

        List lAct = pd.getActividades(proy);

        listaActividades = new ArrayList();
        if (lAct != null) {

            Actividad actTodos = new Actividad();
            actTodos.setNombre("TODOS");
            actTodos.setId(0L);

            SelectItem todosAct =
                new SelectItem(actTodos, actTodos.getNombre());
            listaActividades.add(todosAct);

            Iterator it = lAct.iterator();
            while (it.hasNext()) {
                Actividad act = (Actividad)it.next();
                SelectItem si = new SelectItem(act, act.getNombre());
                listaActividades.add(si);
            }
        }


        AdfFacesContext.getCurrentInstance().addPartialTarget(this.cl_actividades);


        logger.info("Paso OK lc_proyectos_valueChangeListener");

    }


    public void lc_actividades_valueChangeListener(ValueChangeEvent event) throws IdeamException {

        logger.info("Se ingreso a lc_actividades_valueChangeListener");

        Actividad act = null;

        if (event.getNewValue() == null)
            act = null;
        else {
            act = (Actividad)event.getNewValue();
        }


        actividadSeleccionado = act;

        logger.info("Paso OK lc_actividades_valueChangeListener");

    }


    public void setCb_consolidar(RichSelectBooleanCheckbox selectBooleanCheckbox1) {
        this.cb_consolidar = selectBooleanCheckbox1;
    }

    public RichSelectBooleanCheckbox getCb_consolidar() {
        return cb_consolidar;
    }
}
