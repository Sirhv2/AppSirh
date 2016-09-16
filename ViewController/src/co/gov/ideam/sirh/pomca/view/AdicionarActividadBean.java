package co.gov.ideam.sirh.pomca.view;

import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.pomca.model.Actividad;
import co.gov.ideam.sirh.pomca.model.Cuenca;
import co.gov.ideam.sirh.pomca.model.Pomca;
import co.gov.ideam.sirh.pomca.model.Programa;
import co.gov.ideam.sirh.pomca.model.Proyecto;

import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;

import co.gov.ideam.sirh.view.StandarBean;

import java.math.BigDecimal;

import java.util.Date;

import javax.faces.application.FacesMessage;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.output.RichImage;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichSpacer;

import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;

public class AdicionarActividadBean {

    private Cuenca cuenca;
    private Pomca pomca;
    private Programa programa;
    private Proyecto proyecto;
    private Actividad actividad;


    private RichForm form1;
    private RichDocument document1;
    private RichPanelStretchLayout panelStretchLayout1;
    private RichPanelGroupLayout panelGroupLayout1;
    private RichPanelBox panelBox1;
    private RichPanelStretchLayout panelStretchLayout2;
    private RichPanelGroupLayout panelGroupLayout2;
    private RichPanelFormLayout panelFormLayout1;
    private RichInputText it_nombreFuente;
    private RichInputText it_nombre_programa;
    private RichInputText it_descripcion;
    private RichInputDate id_fechaInicio;
    private RichInputDate id_fechaVigencia;
    private RichInputText it_presupuestoAsignado;
    private RichInputText it_porcCumplimiento;
    private RichInputText it_porcEjecucion;
    private RichPopup p_registro_exitoso;
    private RichDialog d_registro_exitoso;
    private RichPanelGroupLayout pgl14;
    private RichCommandButton cbAceptar;
    private RichPanelStretchLayout psl7;
    private RichOutputText ot8;
    private RichImage i1;
    private RichPanelGroupLayout pgl2;
    private RichPanelGroupLayout pgl3;
    private RichCommandButton cb_cancelar;
    private RichSpacer s1;
    private RichCommandButton cbNext1;
    private RichInputText it_nombre_proyecto;
    private RichInputText it_nombre;

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

    public void setPanelBox1(RichPanelBox panelBox1) {
        this.panelBox1 = panelBox1;
    }

    public RichPanelBox getPanelBox1() {
        return panelBox1;
    }

    public void setPanelStretchLayout2(RichPanelStretchLayout panelStretchLayout2) {
        this.panelStretchLayout2 = panelStretchLayout2;
    }

    public RichPanelStretchLayout getPanelStretchLayout2() {
        return panelStretchLayout2;
    }

    public void setPanelGroupLayout2(RichPanelGroupLayout panelGroupLayout2) {
        this.panelGroupLayout2 = panelGroupLayout2;
    }

    public RichPanelGroupLayout getPanelGroupLayout2() {
        return panelGroupLayout2;
    }

    public void setPanelFormLayout1(RichPanelFormLayout panelFormLayout1) {
        this.panelFormLayout1 = panelFormLayout1;
    }

    public RichPanelFormLayout getPanelFormLayout1() {
        return panelFormLayout1;
    }

    public void setIt_nombreFuente(RichInputText it_nombreFuente) {
        this.it_nombreFuente = it_nombreFuente;
    }

    public RichInputText getIt_nombreFuente() {
        return it_nombreFuente;
    }

    public void setIt_nombre_programa(RichInputText it_nombre) {
        this.it_nombre_programa = it_nombre;
    }

    public RichInputText getIt_nombre_programa() {
        return it_nombre_programa;
    }

    public void setIt_descripcion(RichInputText it_descripcion) {
        this.it_descripcion = it_descripcion;
    }

    public RichInputText getIt_descripcion() {
        return it_descripcion;
    }

    public void setId_fechaInicio(RichInputDate id_fechaInicio) {
        this.id_fechaInicio = id_fechaInicio;
    }

    public RichInputDate getId_fechaInicio() {
        return id_fechaInicio;
    }

    public void setId_fechaVigencia(RichInputDate id_fechaVigencia) {
        this.id_fechaVigencia = id_fechaVigencia;
    }

    public RichInputDate getId_fechaVigencia() {
        return id_fechaVigencia;
    }

    public void setIt_presupuestoAsignado(RichInputText it_presupuestoAsignado) {
        this.it_presupuestoAsignado = it_presupuestoAsignado;
    }

    public RichInputText getIt_presupuestoAsignado() {
        return it_presupuestoAsignado;
    }

    public void setIt_porcCumplimiento(RichInputText it_porcCumplimiento) {
        this.it_porcCumplimiento = it_porcCumplimiento;
    }

    public RichInputText getIt_porcCumplimiento() {
        return it_porcCumplimiento;
    }

    public void setIt_porcEjecucion(RichInputText it_porcEjecucion) {
        this.it_porcEjecucion = it_porcEjecucion;
    }

    public RichInputText getIt_porcEjecucion() {
        return it_porcEjecucion;
    }

    public void setP_registro_exitoso(RichPopup p_registro_exitoso) {
        this.p_registro_exitoso = p_registro_exitoso;
    }

    public RichPopup getP_registro_exitoso() {
        return p_registro_exitoso;
    }

    public void setD_registro_exitoso(RichDialog d_registro_exitoso) {
        this.d_registro_exitoso = d_registro_exitoso;
    }

    public RichDialog getD_registro_exitoso() {
        return d_registro_exitoso;
    }

    public void setPgl14(RichPanelGroupLayout pgl14) {
        this.pgl14 = pgl14;
    }

    public RichPanelGroupLayout getPgl14() {
        return pgl14;
    }

    public void setCbAceptar(RichCommandButton cbAceptar) {
        this.cbAceptar = cbAceptar;
    }

    public RichCommandButton getCbAceptar() {
        return cbAceptar;
    }

    public void setPsl7(RichPanelStretchLayout psl7) {
        this.psl7 = psl7;
    }

    public RichPanelStretchLayout getPsl7() {
        return psl7;
    }

    public void setOt8(RichOutputText ot8) {
        this.ot8 = ot8;
    }

    public RichOutputText getOt8() {
        return ot8;
    }

    public void setI1(RichImage i1) {
        this.i1 = i1;
    }

    public RichImage getI1() {
        return i1;
    }

    public void setPgl2(RichPanelGroupLayout pgl2) {
        this.pgl2 = pgl2;
    }

    public RichPanelGroupLayout getPgl2() {
        return pgl2;
    }

    public void setPgl3(RichPanelGroupLayout pgl3) {
        this.pgl3 = pgl3;
    }

    public RichPanelGroupLayout getPgl3() {
        return pgl3;
    }

    public void setCb_cancelar(RichCommandButton cb_cancelar) {
        this.cb_cancelar = cb_cancelar;
    }

    public RichCommandButton getCb_cancelar() {
        return cb_cancelar;
    }

    public void setS1(RichSpacer s1) {
        this.s1 = s1;
    }

    public RichSpacer getS1() {
        return s1;
    }

    public void setCbNext1(RichCommandButton cbNext1) {
        this.cbNext1 = cbNext1;
    }

    public RichCommandButton getCbNext1() {
        return cbNext1;
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

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setIt_nombre_proyecto(RichInputText inputText1) {
        this.it_nombre_proyecto = inputText1;
    }

    public RichInputText getIt_nombre_proyecto() {
        return it_nombre_proyecto;
    }

    public void setIt_nombre(RichInputText inputText2) {
        this.it_nombre = inputText2;
    }

    public RichInputText getIt_nombre() {
        return it_nombre;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    public Programa getPrograma() {
        return programa;
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

    public AdicionarActividadBean() {
        FacesUtils.setActiveBean("AdicionarActividadBean", "Adicionar Actividad",
                                 PomcaDelegate.class);
        load();

    }

    private void load() {
        try {

            this.cuenca =
                    (Cuenca)FacesUtils.getFromSession("cuencaSeleccionada");

            this.programa =
                    (Programa)FacesUtils.getFromSession("programaSeleccionado");

            this.proyecto =
                    (Proyecto)FacesUtils.getFromSession("proyectoSeleccionado");


            UsuarioConectadoTO usuarioConectado =
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

            Autoridades autoridadAmbiental = usuarioConectado.getUsuario().getAutoridadAmbiental();

            PomcaDelegate pd = PomcaDelegate.getInstance();

            actividad = new Actividad();
            actividad.setIdProyecto(proyecto.getId());
            actividad.setCodigoAutoridad(autoridadAmbiental.getId().longValue());
            actividad.setNombre("");
            actividad.setDescripcion("");
            //actividad.setFechaInicio(new Timestamp());
            //actividad.setFechaVigencia(new Timestamp());
            actividad.setPorcCumplimiento(0D);
            actividad.setPorcEjecucion(0D);
            actividad.setPresupuestoAsignado(new BigDecimal("0"));
                            
                
    
        } catch (Exception e) {
            showMessage(e.getMessage());
        }
    }
    
    public String cb_cancelar_action() {
        return "editarProyecto";
    }


    public String cbNext1_action() {

        if (this.it_nombre_programa.getValue() == null) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        this.it_nombre_programa);
            return "";
        }

        if (this.it_nombre_programa.getValue().toString().length() == 0) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        this.it_nombre_programa);
            return "";
        }

        if (this.id_fechaInicio.getValue() == null) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        this.id_fechaInicio);
            return "";
        }

        if (this.id_fechaVigencia.getValue() == null) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        this.id_fechaVigencia);
            return "";
        }

        Date fi = (Date)this.id_fechaInicio.getValue();
        Date fv = (Date)this.id_fechaVigencia.getValue();

        if (fi.compareTo(fv) > 0) {
            showMessage("FECHA_ERRADA", FacesMessage.SEVERITY_ERROR,
                        this.id_fechaVigencia);
            return "";

        }
        
        Date fi_p = new Date ( proyecto.getFechaInicio().getTime());
        Date ff_p = new Date ( proyecto.getFechaVigencia().getTime());
        
        if (fi.compareTo(fi_p)<0 ||  fi.compareTo(ff_p) > 0 ) {
            showMessage("RANGO_FECHA_ERRADO_PROY",FacesMessage.SEVERITY_ERROR,this.id_fechaInicio);       
            return "";
        }

        if (fv.compareTo(fi_p)< 0 ||  fv.compareTo(ff_p) > 0 ) {
            showMessage("RANGO_FECHA_ERRADO_PROY",FacesMessage.SEVERITY_ERROR,this.id_fechaVigencia);       
            return "";
        }


        this.guardarActividad();

       return "";
       
    }

    public void guardarActividad() {
        try {

            UsuarioConectadoTO usuarioConectado =
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

            actividad.setCodigoAutoridad(usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue());
            PomcaDelegate pd = PomcaDelegate.getInstance();

            pd.addActividad(actividad);
            showPopup(p_registro_exitoso, true);

            FacesUtils.removeManagedBeanFromSession("AdicionarActividadBean");
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


            FacesUtils.removeManagedBeanFromSession("AdicionarPomcaBean");


        } catch (IdeamException e) {
            showMessage(e);
        }
    }
}
