package co.gov.ideam.sirh.pomca.view;

import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.pomca.model.Actividad;
import co.gov.ideam.sirh.pomca.model.Cuenca;
import co.gov.ideam.sirh.pomca.model.Gestion;
import co.gov.ideam.sirh.pomca.model.GestionIndicador;
import co.gov.ideam.sirh.pomca.model.Indicador;
import co.gov.ideam.sirh.pomca.model.Pomca;

import co.gov.ideam.sirh.pomca.model.Programa;
import co.gov.ideam.sirh.pomca.model.Proyecto;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;

import co.gov.ideam.sirh.view.StandarBean;

import java.math.BigDecimal;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import javax.faces.model.SelectItem;

import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichMenu;
import oracle.adf.view.rich.component.rich.RichMenuBar;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
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
import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;

public class RegistrarGestionBean  extends StandarBean  {

    static Logger logger = Logger.getLogger(RegistrarGestionBean.class);

    private Cuenca cuenca;
    private Pomca pomca;
    private Programa programa;
    private Proyecto proyecto;
    private Actividad actividad;
    private Indicador indicador;

    private Programa programaSeleccionado;
    private Proyecto proyectoSeleccionado;
    private Actividad actividadSeleccionado;
    private Indicador indicadorSeleccionado;

    private List listaProgramas;
    private List listaProyectos;
    private List listaActividades;
    private List listaIndicadores;

    private List<RegistroIndicador> listaGestion;

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
    private RichInputText it_nombrePomca;
    private RichInputDate id_fechaReporte;
    private RichInputText it_presupuesto;
    private RichCommandButton cb_actualizar;
    private RichPanelBox panelBox3;
    private RichPanelCollection panelCollection2;
    private RichTable t_indicadores;
    private RichPanelFormLayout panelFormLayout2;
    private RichActiveOutputText activeOutputText1;
    private RichInputText it_meta;
    private RichSelectOneChoice lc_programas;
    private UISelectItems itemsProgramas;
    private RichSelectOneChoice cl_proyectos;
    private UISelectItems itemsProyectos;
    private RichSelectOneChoice cl_actividades;
    private UISelectItems itemsActividades;
    private RichInputDate id_periodoInicial;
    private RichInputDate id_periodoFinal;
    private RichInputText it_observacion;
    private RichSelectOneChoice cl_indicadores;
    private UISelectItems itemsIndicadores;
    private RichCommandButton cb_agregarIndicador;
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
    private RichOutputText otInstrucc;

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


    public void setIt_nombrePomca(RichInputText it_nombrePomca) {
        this.it_nombrePomca = it_nombrePomca;
    }

    public RichInputText getIt_nombrePomca() {
        return it_nombrePomca;
    }


    public void setId_fechaReporte(RichInputDate id_fechaExpedicion) {
        this.id_fechaReporte = id_fechaExpedicion;
    }

    public RichInputDate getId_fechaReporte() {
        return id_fechaReporte;
    }


    public void setIt_presupuesto(RichInputText it_presupuesto) {
        this.it_presupuesto = it_presupuesto;
    }

    public RichInputText getIt_presupuesto() {
        return it_presupuesto;
    }


    public void setCb_actualizar(RichCommandButton cb_actualizar) {
        this.cb_actualizar = cb_actualizar;
    }

    public RichCommandButton getCb_actualizar() {
        return cb_actualizar;
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


    public void setT_indicadores(RichTable t_programas) {
        this.t_indicadores = t_programas;
    }

    public RichTable getT_indicadores() {
        return t_indicadores;
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

    /**
     * Muestra un mensaje permosnalizado segun los parametros recibidos y lo
     * asocia al componente
     * @param keyMsg
     * @param severity
     * @param uiComponent
     */
    protected void showMessage(String keyMsg, String[] params,
                               javax.faces.application.FacesMessage.Severity severity,
                               UIComponent uiComponent) {
        String mensaje = StandarBean.getText(keyMsg, params);
        FacesMessage msg = new FacesMessage(severity, mensaje, null);
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.addMessage(uiComponent.getClientId(ctx), msg);
    }

    /**
     * muestra el mensaje asoicado a la excepcion recibida como parametro
     * @param e
     */
    protected void showMessage(IdeamException e) {
        FacesContext fc = FacesContext.getCurrentInstance();
        FacesMessage message =
            new FacesMessage(e.getMessage(), e.getLocalizedMessage());
        message.setSeverity(FacesMessage.SEVERITY_FATAL);
        message.setSummary(e.getLocalizedMessage());
        fc.addMessage(null, message);
    }

    /**
     * Muestra el mensaje recibido como parametro
     * @param msg
     */
    protected void showMessage(String msg) {
        FacesContext fc = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage(msg);
        message.setSeverity(FacesMessage.SEVERITY_ERROR);
        fc.addMessage(null, message);
    }

    /**
     * Muestra un mensaje permosnalizado segun los parametros recibidos
     * @param msg
     * @param severity
     */
    protected void showMessage(String msg,
                               javax.faces.application.FacesMessage.Severity severity) {
        FacesContext fc = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage(msg);
        message.setSeverity(severity);
        fc.addMessage(null, message);
    }

    /**
     * Muestra un mensaje permosnalizado segun los parametros recibidos y lo
     * asocia al componente
     * @param keyMsg
     * @param severity
     * @param uiComponent
     */
    protected void showMessage(String keyMsg,
                               javax.faces.application.FacesMessage.Severity severity,
                               UIComponent uiComponent) {
        String mensaje = StandarBean.getText(keyMsg);
        FacesMessage msg = new FacesMessage(severity, mensaje, null);
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.addMessage(uiComponent.getClientId(ctx), msg);
    }

    /**
     * Muestra u oculta el popup recibido como parametro
     * @param popup
     * @param visible
     */
    protected void showPopup(RichPopup popup, boolean visible) {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            if (context != null && popup != null) {
                String popupId = popup.getClientId(context);
                if (popupId != null) {
                    StringBuilder script = new StringBuilder();
                    script.append("var popup = AdfPage.PAGE.findComponent('").append(popupId).append("'); ");
                    if (visible) {
                        script.append("if (!popup.isPopupVisible()) { ").append("popup.show();}");
                    } else {
                        script.append("if (popup.isPopupVisible()) { ").append("popup.hide();}");
                    }
                    ExtendedRenderKitService erks =
                        Service.getService(context.getRenderKit(),
                                           ExtendedRenderKitService.class);
                    erks.addScript(context, script.toString());
                }
            }
        } catch (Exception e) {
            this.showMessage(new IdeamException(e.getMessage(), e));
        }
    }

    public RegistrarGestionBean() {

        FacesUtils.setActiveBean("RegistrarGestionBean", "Registrar Gestion",
                                 PomcaDelegate.class);
        load();

    }

    private void load() {
        try {


            this.cuenca =
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
                Iterator it = lProg.iterator();
                while (it.hasNext()) {
                    Programa prog = (Programa)it.next();
                    SelectItem si = new SelectItem(prog, prog.getNombre());
                    listaProgramas.add(si);
                }
            }

            listaProyectos = new ArrayList();
            listaActividades = new ArrayList();
            listaIndicadores = new ArrayList();

            listaGestion = new ArrayList();
            
            logger.info("Paso load OK");

        } catch (Exception e) {
            showMessage(e.getMessage());
        }

    }


    public void lc_programa_valueChangeListener(ValueChangeEvent event) throws IdeamException {

        logger.info("Se ingreso a lc_programa_valueChangeListener");
        
        Programa prog = null;

        prog = (Programa)event.getNewValue();
        programaSeleccionado = prog;

        if (prog == null) {
            
            listaProyectos = new ArrayList();
            listaActividades = new ArrayList();
            listaIndicadores = new ArrayList();
            listaGestion = new ArrayList();

            cb_agregarIndicador.setDisabled(true);

            AdfFacesContext.getCurrentInstance().addPartialTarget(this.cl_proyectos);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.cl_actividades);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.cl_indicadores);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.t_indicadores);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.cb_agregarIndicador);
            

            
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
            Iterator it = lProy.iterator();
            while (it.hasNext()) {
                Proyecto proy = (Proyecto)it.next();
                SelectItem si = new SelectItem(proy, proy.getNombre());
                listaProyectos.add(si);
            }
        }

        listaActividades = new ArrayList();
        listaIndicadores = new ArrayList();
        listaGestion = new ArrayList();

        cb_agregarIndicador.setDisabled(true);

        AdfFacesContext.getCurrentInstance().addPartialTarget(this.cl_proyectos);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.cl_actividades);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.cl_indicadores);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.t_indicadores);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.cb_agregarIndicador);


        logger.info("Paso OK lc_programa_valueChangeListener");

    }

    public void lc_proyectos_valueChangeListener(ValueChangeEvent event) throws IdeamException {

        logger.info("Se ingreso a lc_proyectos_valueChangeListener");

        Proyecto proy = (Proyecto)event.getNewValue();
        proyectoSeleccionado = proy;

        
        if (proy == null) {
            listaActividades = new ArrayList();
            listaIndicadores = new ArrayList();
            listaGestion = new ArrayList();


            AdfFacesContext.getCurrentInstance().addPartialTarget(this.cl_actividades);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.cl_indicadores);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.t_indicadores);
            cb_agregarIndicador.setDisabled(true);

            AdfFacesContext.getCurrentInstance().addPartialTarget(this.t_indicadores);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.cb_agregarIndicador);
            

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
            Iterator it = lAct.iterator();
            while (it.hasNext()) {
                Actividad act = (Actividad)it.next();
                SelectItem si = new SelectItem(act, act.getNombre());
                listaActividades.add(si);
            }
        }

        listaIndicadores = new ArrayList();
        listaGestion = new ArrayList();

        AdfFacesContext.getCurrentInstance().addPartialTarget(this.cl_actividades);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.cl_indicadores);

        cb_agregarIndicador.setDisabled(true);

        AdfFacesContext.getCurrentInstance().addPartialTarget(this.t_indicadores);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.cb_agregarIndicador);

        logger.info("Paso OK lc_proyectos_valueChangeListener");
        
    }

    public void lc_actividades_valueChangeListener(ValueChangeEvent event) throws IdeamException {

        logger.info("Se ingreso a lc_actividades_valueChangeListener");

        Actividad act = (Actividad)event.getNewValue();
        actividadSeleccionado = act;

        if (act == null ) {
            listaIndicadores = new ArrayList();
            listaGestion = new ArrayList();
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.cl_indicadores);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.t_indicadores);

            cb_agregarIndicador.setDisabled(true);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.t_indicadores);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.cb_agregarIndicador);
            
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

        List lInd = pd.getIndicadores(act);

        listaIndicadores = new ArrayList();
        if (lInd != null) {
            Iterator it = lInd.iterator();
            while (it.hasNext()) {
                Indicador ind = (Indicador)it.next();
                SelectItem si = new SelectItem(ind, ind.getNombre());
                listaIndicadores.add(si);
            }
        }


        listaGestion = new ArrayList();

        AdfFacesContext.getCurrentInstance().addPartialTarget(this.cl_indicadores);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.t_indicadores);

        cb_agregarIndicador.setDisabled(true);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.t_indicadores);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.cb_agregarIndicador);

        logger.info("Paso OK lc_actividades_valueChangeListener");
        
    }

    public void lc_indicadores_valueChangeListener(ValueChangeEvent event) throws IdeamException {

        logger.info("Se ingreso a lc_indicadores_valueChangeListener");

        Indicador ind = (Indicador)event.getNewValue();
        indicadorSeleccionado = ind;

        cb_agregarIndicador.setDisabled(true);
        if (ind != null) {
            cb_agregarIndicador.setDisabled(false);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.cb_agregarIndicador);

        }
        logger.info("Paso OK lc_indicadores_valueChangeListener");


    }
    
    public void cb_agregarIndicador_actionListener(ActionEvent actionEvent) { 
        try {

            if ( programaSeleccionado != null &&
                proyectoSeleccionado != null &&
                actividadSeleccionado != null &&
                 indicadorSeleccionado != null ) {

                logger.info("Va a crear registro gestion");

                Long vlr = 0L;
                
                try {
                    vlr = new Long(this.it_meta.getValue().toString());
                }

                catch (Exception e) {
                    showMessage("Valor Errado",FacesMessage.SEVERITY_ERROR,this.it_presupuesto);       
                    return;
                    
                }

            
                RegistroIndicador reg = new RegistroIndicador(cuenca,
                                                              pomca, 
                                                              programaSeleccionado, 
                                                              proyectoSeleccionado, 
                                                              actividadSeleccionado, 
                                                              indicadorSeleccionado, vlr);
                
                validarIndicador(reg);
                listaGestion.add(reg);

                AdfFacesContext.getCurrentInstance().addPartialTarget(this.t_indicadores);

                logger.info("Se agrego ok");
                                                                
            }


        } catch (Exception e) {
            showMessage(e.getMessage());
        }
    }
   
    private void validarIndicador(RegistroIndicador reg) throws Exception {
    
    if (listaGestion != null) {
        Iterator it = listaGestion.iterator();
        while (it.hasNext()) {

        
            RegistroIndicador ind = (RegistroIndicador)it.next();
        
            if (ind.getIndicador().getId() == reg.getIndicador().getId())
                throw new Exception("Informacion de indicador ya registrada");
            
            
        }
    }
    
    }
    public void cb_Actualizar_actionListener(ActionEvent actionEvent) { 
        try {

            logger.info("Ingresa a cb_Actualizar_actionListener");

            if (listaGestion.size() == 0 ) {
                showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.t_indicadores);       
                return;
                
            }

            if (this.id_fechaReporte.getValue() == null) {
                showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.id_fechaReporte);       
                return;
            }

            
            if (this.id_periodoInicial.getValue() == null) {
                showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.id_periodoInicial);       
                return;
            }
            if (this.id_periodoFinal.getValue() == null) {
                showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.id_periodoFinal);       
                return;
            }

            Date fi =(Date) this.id_periodoInicial.getValue();
            Date fv =(Date) this.id_periodoFinal.getValue();
            
            if (fi.compareTo(fv) > 0) {
                showMessage("FECHA_ERRADA",FacesMessage.SEVERITY_ERROR,this.id_periodoFinal);       
                return;
                
            }

            Date fi_a = new Date ( actividadSeleccionado.getFechaInicio().getTime());
            Date ff_a = new Date ( actividadSeleccionado.getFechaVigencia().getTime());
            
            if (fi.compareTo(fi_a)<0 ||  fi.compareTo(ff_a) > 0 ) {
                showMessage("RANGO_FECHA_ERRADO_ACT",FacesMessage.SEVERITY_ERROR,this.id_periodoInicial);       
                return ;
            }

            if (fv.compareTo(fi_a)< 0 ||  fv.compareTo(ff_a) > 0 ) {
                showMessage("RANGO_FECHA_ERRADO_ACT",FacesMessage.SEVERITY_ERROR,this.id_periodoFinal);       
                return ;
            }

            
            if(this.it_observacion.getValue() == null ){
                showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.it_observacion);       
                return;
            }        

            if(this.it_presupuesto.getValue() == null ){
                showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.it_presupuesto);       
                return;
            }        


            try {
                Long vlr = new Long(this.it_presupuesto.getValue().toString());
            }

            catch (Exception e) {
                showMessage("Valor Errado",FacesMessage.SEVERITY_ERROR,this.it_presupuesto);       
                return;
                
            }

            logger.info("Paso validaciones");
            
            this.guardarGestion();

            String params[] = { "de la Gestion" };
            showMessage(getText("mensaje_registro_exitoso", params),
                        FacesMessage.SEVERITY_INFO);
            
            load();

            this.id_fechaReporte.setValue(null);
            this.id_periodoInicial.setValue(null);
            this.id_periodoFinal.setValue(null);
            this.it_observacion.setValue(null);
            this.it_presupuesto.setValue(null);
            this.it_meta.setValue(null);
            

        } catch (Exception e) {
            showMessage(e.getMessage());
        }
    }

    public void guardarGestion(){
        try{

            this.cuenca =
                    (Cuenca)FacesUtils.getFromSession("cuencaSeleccionada");

            UsuarioConectadoTO usuarioConectado = 
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");


            logger.info("Numero de la autoridad " + usuarioConectado.getUsuario().getAutoridadAmbiental().getId().toString());
            
            Gestion gestion = new Gestion();
            
            gestion.setCodigoAutoridad(usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue());
            gestion.setIdCuenca(cuenca.getId());
            gestion.setIdPrograma(programaSeleccionado.getId());
            gestion.setIdProyecto(proyectoSeleccionado.getId());
            gestion.setIdActividad(actividadSeleccionado.getId());
            gestion.setIdUsuario(usuarioConectado.getUsuario().getCodigo());
            
            Date fr = (Date)id_fechaReporte.getValue();
            Timestamp t_fr = new Timestamp(fr.getTime());
            gestion.setFechaReporte(t_fr);
            
            Date fi = (Date)id_periodoInicial.getValue();
            Timestamp t_fi = new Timestamp(fi.getTime());
            gestion.setFechaInicio(t_fi);

            Date ff = (Date)id_periodoFinal.getValue();
            Timestamp t_ff = new Timestamp(ff.getTime());
            gestion.setFechaVigencia(t_ff);
            
            gestion.setObservaciones(it_observacion.getValue().toString());

            gestion.setPresupuestoEjecutado(new BigDecimal(this.it_presupuesto.getValue().toString()));
            
            PomcaDelegate pd = PomcaDelegate.getInstance();

            logger.info("Va a grabar gestion");
          
            gestion = pd.addGestion(gestion);

            logger.info("Grabo gestion " + gestion.getId());
            
            
            listaIndicadores = new ArrayList();
            if (listaGestion != null) {
                Iterator it = listaGestion.iterator();
                while (it.hasNext()) {

                    logger.info("Va a grabar indicador gestion");

                    RegistroIndicador ind = (RegistroIndicador)it.next();
                    
                    GestionIndicador gestionIndicador =new GestionIndicador();
                    gestionIndicador.setCodigoAutoridad(usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue());
                    gestionIndicador.setIdGestion(gestion.getId());
                    gestionIndicador.setIdIndicador(ind.getIndicador().getId());
                    gestionIndicador.setValorIndicador(ind.getMetaPeriodo());
                    
                    gestionIndicador = pd.addGestionIndicador(gestionIndicador);

                    logger.info("Paso grabar indicador gestion");
                    
                }
            }
            
            //FacesUtils.removeManagedBeanFromSession("RegistrarGestionBean");
            
            
        }catch(IdeamException e){
            showMessage(e);
        }
    }

    public String cb_no_borrar_action() {
        showPopup(p_borrar, false);
        return null;
    }



    public String cb_si_borrar_action() {
        try {

            UsuarioConectadoTO usuarioConectado =
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

            Autoridades autoridadAmbiental =
                usuarioConectado.getUsuario().getAutoridadAmbiental();

            showMessage(getText("MENSAJE_BORRAR_PROGRAMA_EXITOSO"));

            FacesUtils.removeManagedBeanFromSession("RegistrarGestionBean");

            return "registrarGestion";
        } catch (Exception e) {
            showMessage(e.getMessage());
        }

        return null;
    }
    
    public String cl_todasFuentes_action() {
        
        FacesUtils.removeManagedBeanFromSession("RegistrarGestionBean");
        FacesUtils.removeFromSession("fuenteSeleccionada");
        return "cuencas";
    }


    public void setPanelFormLayout2(RichPanelFormLayout panelFormLayout2) {
        this.panelFormLayout2 = panelFormLayout2;
    }

    public RichPanelFormLayout getPanelFormLayout2() {
        return panelFormLayout2;
    }

    public void setActiveOutputText1(RichActiveOutputText activeOutputText1) {
        this.activeOutputText1 = activeOutputText1;
    }

    public RichActiveOutputText getActiveOutputText1() {
        return activeOutputText1;
    }

    public void setIt_meta(RichInputText inputText1) {
        this.it_meta = inputText1;
    }

    public RichInputText getIt_meta() {
        return it_meta;
    }


    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setListaProgramas(List listaProgramas) {
        this.listaProgramas = listaProgramas;
    }

    public List getListaProgramas() {
        return listaProgramas;
    }

    public void setLc_programas(RichSelectOneChoice selectOneChoice1) {
        this.lc_programas = selectOneChoice1;
    }

    public RichSelectOneChoice getLc_programas() {
        return lc_programas;
    }

    public void setItemsProgramas(UISelectItems selectItems1) {
        this.itemsProgramas = selectItems1;
    }

    public UISelectItems getItemsProgramas() {
        return itemsProgramas;
    }

    public void setListaProyectos(List listaProyectos) {
        this.listaProyectos = listaProyectos;
    }

    public List getListaProyectos() {
        return listaProyectos;
    }

    public void setCl_proyectos(RichSelectOneChoice selectOneChoice1) {
        this.cl_proyectos = selectOneChoice1;
    }

    public RichSelectOneChoice getCl_proyectos() {
        return cl_proyectos;
    }

    public void setItemsProyectos(UISelectItems selectItems1) {
        this.itemsProyectos = selectItems1;
    }

    public UISelectItems getItemsProyectos() {
        return itemsProyectos;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setCl_actividades(RichSelectOneChoice selectOneChoice1) {
        this.cl_actividades = selectOneChoice1;
    }

    public RichSelectOneChoice getCl_actividades() {
        return cl_actividades;
    }

    public void setItemsActividades(UISelectItems selectItems1) {
        this.itemsActividades = selectItems1;
    }

    public UISelectItems getItemsActividades() {
        return itemsActividades;
    }

    public static void setLogger(Logger logger) {
        RegistrarGestionBean.logger = logger;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setIndicador(Indicador indicador) {
        this.indicador = indicador;
    }

    public Indicador getIndicador() {
        return indicador;
    }

    public void setListaActividades(List listaActividades) {
        this.listaActividades = listaActividades;
    }

    public List getListaActividades() {
        return listaActividades;
    }

    public void setListaIndicadores(List listaIndicadores) {
        this.listaIndicadores = listaIndicadores;
    }

    public List getListaIndicadores() {
        return listaIndicadores;
    }

    public void setId_periodoInicial(RichInputDate inputDate2) {
        this.id_periodoInicial = inputDate2;
    }

    public RichInputDate getId_periodoInicial() {
        return id_periodoInicial;
    }

    public void setId_periodoFinal(RichInputDate inputDate3) {
        this.id_periodoFinal = inputDate3;
    }

    public RichInputDate getId_periodoFinal() {
        return id_periodoFinal;
    }

    public void setIt_observacion(RichInputText it_descripcionPomca) {
        this.it_observacion = it_descripcionPomca;
    }

    public RichInputText getIt_observacion() {
        return it_observacion;
    }

    public void setCl_indicadores(RichSelectOneChoice selectOneChoice1) {
        this.cl_indicadores = selectOneChoice1;
    }

    public RichSelectOneChoice getCl_indicadores() {
        return cl_indicadores;
    }

    public void setItemsIndicadores(UISelectItems selectItems1) {
        this.itemsIndicadores = selectItems1;
    }

    public UISelectItems getItemsIndicadores() {
        return itemsIndicadores;
    }

    public void setCb_agregarIndicador(RichCommandButton commandButton1) {
        this.cb_agregarIndicador = commandButton1;
    }

    public RichCommandButton getCb_agregarIndicador() {
        return cb_agregarIndicador;
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

    public void setIndicadorSeleccionado(Indicador indicadorSeleccionado) {
        this.indicadorSeleccionado = indicadorSeleccionado;
    }

    public Indicador getIndicadorSeleccionado() {
        return indicadorSeleccionado;
    }

    public void setListaGestion(List listaGestion) {
        this.listaGestion = listaGestion;
    }

    public List getListaGestion() {
        return listaGestion;
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

    public void setOtInstrucc(RichOutputText outputText1) {
        this.otInstrucc = outputText1;
    }

    public RichOutputText getOtInstrucc() {
        return otInstrucc;
    }
}
