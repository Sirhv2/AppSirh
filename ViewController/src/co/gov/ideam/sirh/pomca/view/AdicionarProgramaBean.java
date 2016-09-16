package co.gov.ideam.sirh.pomca.view;

import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.fuentes.view.FuenteDelegate;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.pomca.model.Cuenca;
import co.gov.ideam.sirh.pomca.model.Pomca;

import co.gov.ideam.sirh.pomca.model.Programa;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;

import co.gov.ideam.sirh.view.StandarBean;

import java.math.BigDecimal;

import java.sql.Timestamp;

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

public class AdicionarProgramaBean {
    
    private Cuenca cuenca;
    private Programa programa;
    private Pomca pomca;

    private RichForm form1;
    private RichDocument document1;
    private RichPanelStretchLayout panelStretchLayout1;
    private RichPanelGroupLayout panelGroupLayout1;
    private RichPanelBox panelBox1;
    private RichPopup p_registro_exitoso;
    private RichDialog d_registro_exitoso;
    private RichPanelGroupLayout pgl14;
    private RichCommandButton cbAceptar;
    private RichPanelStretchLayout psl7;
    private RichOutputText ot8;
    private RichImage i1;
    private RichPanelStretchLayout panelStretchLayout2;
    private RichInputText it_nombre;
    private RichPanelGroupLayout panelGroupLayout2;
    private RichInputText it_descripcion;
    private RichInputText it_presupuestoAsignado;
    private RichInputText it_porcCumplimiento;
    private RichInputText it_porcEjecucion;
    private RichInputDate id_fechaInicio;
    private RichInputDate id_fechaVigencia;
    private RichPanelFormLayout panelFormLayout1;
    private RichPanelGroupLayout pgl2;
    private RichPanelGroupLayout pgl3;
    private RichCommandButton cbNext1;
    private RichSpacer s1;
    private RichCommandButton cb_cancelar;
    private RichInputText it_nombreFuente;

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

    public AdicionarProgramaBean() {
        FacesUtils.setActiveBean("adicionarProgramaBean", "Adicionar Programa",
                                 PomcaDelegate.class);
        load();

    }

    private void load() {
        try {
            this.cuenca =
                    (Cuenca)FacesUtils.getFromSession("cuencaSeleccionada");

            UsuarioConectadoTO usuarioConectado =
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

            Autoridades autoridadAmbiental = usuarioConectado.getUsuario().getAutoridadAmbiental();

            PomcaDelegate pd = PomcaDelegate.getInstance();

            if (pd == null) {
                programa = null;
                System.out.println("pomca delegate vacio");
            } else {
                System.out.println("Va a ubicar POMCA");
                pomca = pd.getPomca(cuenca.getId());
            }
            
            if (pomca == null) {

                pomca = new Pomca();
               
                //pomca.setFechaExpedicion(new Timestamp());
                pomca.setPorcCumplimiento(0D);
                pomca.setPorcEjecucion(0D);
                pomca.setPresupuestoAsignado(new BigDecimal("0"));
                pomca.setIdCuenca(cuenca.getId());
                pomca.setCodigoAutoridad(autoridadAmbiental.getId().longValue());
            }
    
            programa = new Programa();
            programa.setIdPomca(pomca.getId());
            programa.setCodigoAutoridad(autoridadAmbiental.getId().longValue());
            programa.setNombre("");
            programa.setDescripcion("");
            //programa.setFechaInicio(new Timestamp());
            //programa.setFechaVigencia(new Timestamp());
            programa.setPorcCumplimiento(0D);
            programa.setPorcEjecucion(0D);
            programa.setPresupuestoAsignado(new BigDecimal("0"));
                            
                
    
        } catch (Exception e) {
            showMessage(e.getMessage());
        }


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

    public void setPanelStretchLayout2(RichPanelStretchLayout panelStretchLayout2) {
        this.panelStretchLayout2 = panelStretchLayout2;
    }

    public RichPanelStretchLayout getPanelStretchLayout2() {
        return panelStretchLayout2;
    }

    public void setIt_nombre(RichInputText inputText1) {
        this.it_nombre = inputText1;
    }

    public RichInputText getIt_nombre() {
        return it_nombre;
    }

    public void setCuenca(Cuenca cuenca) {
        this.cuenca = cuenca;
    }

    public Cuenca getCuenca() {
        return cuenca;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setPomca(Pomca pomca) {
        this.pomca = pomca;
    }

    public Pomca getPomca() {
        return pomca;
    }

    public void setPanelGroupLayout2(RichPanelGroupLayout panelGroupLayout2) {
        this.panelGroupLayout2 = panelGroupLayout2;
    }

    public RichPanelGroupLayout getPanelGroupLayout2() {
        return panelGroupLayout2;
    }

    public void setIt_descripcion(RichInputText inputText1) {
        this.it_descripcion = inputText1;
    }

    public RichInputText getIt_descripcion() {
        return it_descripcion;
    }

    public void setIt_presupuestoAsignado(RichInputText inputText1) {
        this.it_presupuestoAsignado = inputText1;
    }

    public RichInputText getIt_presupuestoAsignado() {
        return it_presupuestoAsignado;
    }

    public void setIt_porcCumplimiento(RichInputText inputText1) {
        this.it_porcCumplimiento = inputText1;
    }

    public RichInputText getIt_porcCumplimiento() {
        return it_porcCumplimiento;
    }

    public void setIt_porcEjecucion(RichInputText inputText1) {
        this.it_porcEjecucion = inputText1;
    }

    public RichInputText getIt_porcEjecucion() {
        return it_porcEjecucion;
    }

    public void setId_fechaInicio(RichInputDate inputDate1) {
        this.id_fechaInicio = inputDate1;
    }

    public RichInputDate getId_fechaInicio() {
        return id_fechaInicio;
    }

    public void setId_fechaVigencia(RichInputDate inputDate1) {
        this.id_fechaVigencia = inputDate1;
    }

    public RichInputDate getId_fechaVigencia() {
        return id_fechaVigencia;
    }

    public void setPanelFormLayout1(RichPanelFormLayout panelFormLayout1) {
        this.panelFormLayout1 = panelFormLayout1;
    }

    public RichPanelFormLayout getPanelFormLayout1() {
        return panelFormLayout1;
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

    public void setCbNext1(RichCommandButton cbNext1) {
        this.cbNext1 = cbNext1;
    }

    public RichCommandButton getCbNext1() {
        return cbNext1;
    }

    public void setS1(RichSpacer s1) {
        this.s1 = s1;
    }

    public RichSpacer getS1() {
        return s1;
    }

    public void setCb_cancelar(RichCommandButton commandButton1) {
        this.cb_cancelar = commandButton1;
    }

    public RichCommandButton getCb_cancelar() {
        return cb_cancelar;
    }

    public String cb_cancelar_action() {
        return "adicionarPomca";
    }

    public void setIt_nombreFuente(RichInputText inputText1) {
        this.it_nombreFuente = inputText1;
    }

    public RichInputText getIt_nombreFuente() {
        return it_nombreFuente;
    }

    public void cbNext1_action() {
        
        if(this.it_nombre.getValue() == null ) {
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.it_nombre);       
            return;
        }        
        
        if(this.it_nombre.getValue().toString().length() == 0 ) {
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.it_nombre);       
            return;
        }        
        
        if(this.id_fechaInicio.getValue() == null ) {
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.id_fechaInicio);       
            return;
        }        

        if(this.id_fechaVigencia.getValue() == null ) {
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.id_fechaVigencia);       
            return;
        }        
        
        Date fi =(Date) this.id_fechaInicio.getValue();
        Date fv =(Date) this.id_fechaVigencia.getValue();
        
        if (fi.compareTo(fv) >= 0) {
            showMessage("FECHA_ERRADA",FacesMessage.SEVERITY_ERROR,this.id_fechaVigencia);       
            return;
            
        }
        
        this.guardarPrograma();
        
    }
    
    public void guardarPrograma(){
        try{

            UsuarioConectadoTO usuarioConectado = 
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

            programa.setCodigoAutoridad(usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue());
            programa.setNombre(it_nombre.getValue().toString().toUpperCase());
            PomcaDelegate pd = PomcaDelegate.getInstance();
          
            pd.addPrograma(programa);
            showPopup(p_registro_exitoso, true);
            
            FacesUtils.removeManagedBeanFromSession("AdicionarPomcaBean");
            FacesUtils.removeManagedBeanFromSession("ProyectosTreeHandler");

        }catch(IdeamException e){
            showMessage(e);
        }
    }

}
