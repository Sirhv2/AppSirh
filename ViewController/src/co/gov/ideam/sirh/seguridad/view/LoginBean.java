package co.gov.ideam.sirh.seguridad.view;


import co.gov.ideam.sirh.mail.MailConfigurator;
import co.gov.ideam.sirh.parametros.model.TipoParametro;
import co.gov.ideam.sirh.parametros.view.ParametrosDelegate;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.seguridad.model.UsuarioVO;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.Principal;
import co.gov.ideam.sirh.view.StandarBean;

import java.util.HashMap;

import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichSpacer;

import org.apache.myfaces.trinidad.component.UIXGroup;


/**
 * Bean encargado de administrar el registro del usuario en el sistema
 */
public class LoginBean extends StandarBean {
    private RichForm f2;
    private RichDocument d2;
    private RichPanelStretchLayout psl1;
    private RichPanelFormLayout pfl2;
    private RichInputText itUsuario;
    private RichCommandButton cbAceptar;
    private RichInputText itClave;
    private RichSpacer s1;
    private RichSpacer s2;
    private UIXGroup g1;
    private RichPanelGroupLayout pgl1;
    private RichOutputText ot1;
    private RichOutputText ot2;
    private RichSpacer s3;
    private RichSpacer s4;

    private boolean showPopup;
    private UIXGroup g2;
    private RichPopup ppError;
    private RichDialog dialog;
    private RichOutputText ot3;

    private String forward;
    private RichOutputText ot4;
    private RichSpacer s5;
    private RichCommandButton cb_recordar_clave;
    private RichPanelGroupLayout pgl2;

    public LoginBean() {
        load();
    }

    public void load() {
        try {

            ParametrosDelegate pd = ParametrosDelegate.getInstance();
            TipoParametro tipoParametro = pd.getTipoParametro(TipoParametro.SERVIDOR_CORREO);
            
            String usuario = tipoParametro.getParametro("usuario").getValor();
            String password = tipoParametro.getParametro("password").getValor();
            String host = tipoParametro.getParametro("host").getValor();
            String puerto = tipoParametro.getParametro("puerto").getValor();

            MailConfigurator.setHost(host);
            MailConfigurator.setPassword(password);
            MailConfigurator.setPuerto(puerto);
            MailConfigurator.setUsuario(usuario);
            
            System.out.println("HCP Cargo parametros correo correcto");
            
        } catch (IdeamException e) {
            System.out.println("HCP Problemas en load de LoginBean "+ e.getMessage());
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


    public void setPsl1(RichPanelStretchLayout psl1) {
        this.psl1 = psl1;
    }

    public RichPanelStretchLayout getPsl1() {
        return psl1;
    }


    public void setPfl2(RichPanelFormLayout pfl2) {
        this.pfl2 = pfl2;
    }

    public RichPanelFormLayout getPfl2() {
        return pfl2;
    }

    public void setItUsuario(RichInputText it1) {
        this.itUsuario = it1;
    }

    public RichInputText getItUsuario() {
        return itUsuario;
    }

    public void setCbAceptar(RichCommandButton cb1) {
        this.cbAceptar = cb1;
    }

    public RichCommandButton getCbAceptar() {
        return cbAceptar;
    }

    public void setItClave(RichInputText it1) {
        this.itClave = it1;
    }

    public RichInputText getItClave() {
        return itClave;
    }

    public void setS1(RichSpacer s1) {
        this.s1 = s1;
    }

    public RichSpacer getS1() {
        return s1;
    }

    public void setS2(RichSpacer s2) {
        this.s2 = s2;
    }

    public RichSpacer getS2() {
        return s2;
    }


    public void setG1(UIXGroup g1) {
        this.g1 = g1;
    }

    public UIXGroup getG1() {
        return g1;
    }

    public void setPgl1(RichPanelGroupLayout pgl1) {
        this.pgl1 = pgl1;
    }

    public RichPanelGroupLayout getPgl1() {
        return pgl1;
    }

    public void setOt1(RichOutputText ot1) {
        this.ot1 = ot1;
    }

    public RichOutputText getOt1() {
        return ot1;
    }

    public void setOt2(RichOutputText ot2) {
        this.ot2 = ot2;
    }

    public RichOutputText getOt2() {
        return ot2;
    }

    public void setS3(RichSpacer s3) {
        this.s3 = s3;
    }

    public RichSpacer getS3() {
        return s3;
    }


    public void cbAceptar_actionListener(ActionEvent actionEvent) {
        SeguridadDelegate sd = null;
        this.setForward("");
        System.out.println("Hello entrando :v ************************************************************");
        try {
            if (itClave.getValue() == null ||
                itClave.getValue().toString().length() == 0) {
                itClave.setValid(false);
                showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                            this.itClave);
                return;
            }
            sd = SeguridadDelegate.getInstance();
            String usuario = this.itUsuario.getValue().toString();
            String clave = this.itClave.getValue().toString();
            if (clave.length() < 8) {
                itClave.setValid(false);
                showMessage("CLAVE_MINIMO_8", FacesMessage.SEVERITY_ERROR,
                            this.itClave);
                return;
            }
            boolean valido = false;
            String remoteIp = FacesUtils.getRemoteAddress();
            valido = sd.validateUser(usuario, clave);
            if (!valido) {
                RichPopup popup = this.getPpError(); // popup binding
                this.showPopup(popup, true);
                return;
            }
            Principal principal = new Principal();

            UsuarioConectadoTO usuarioConectado = sd.loginUser(usuario);
            usuarioConectado.setRemoteIP(remoteIp);
            FacesUtils.setManagedBeanValue("UsuarioConectado",
                                           usuarioConectado);
            FacesUtils.setInSession("usuarioConectado", usuarioConectado);

            HashMap opcionesAutorizadas =
                sd.getMenuAutorizado(usuarioConectado.getUsuario());

            //System.out.println ("opc from bean " + opcionesAutorizadas.size());

            UsuarioConectadoTO cargado =
                (UsuarioConectadoTO)FacesUtils.getManagedBeanValue("UsuarioConectado");
            cargado.setOpcionesAutorizadas(opcionesAutorizadas);

            // Cargar el Principal para mostrar el menu
            FacesUtils.setManagedBeanValue("principal", principal);
        } catch (IdeamException e) {
            this.showMessage(e);
            return;
        }
        this.setForward("principal");
    }

    public void setS4(RichSpacer s4) {
        this.s4 = s4;
    }

    public RichSpacer getS4() {
        return s4;
    }


    public boolean isShowPopup() {
        return showPopup;
    }

    public void setShowPopup(boolean showPopup) {
        this.showPopup = showPopup;
    }

    public void setG2(UIXGroup g2) {
        this.g2 = g2;
    }

    public UIXGroup getG2() {
        return g2;
    }

    public void setPpError(RichPopup p1) {
        this.ppError = p1;
    }

    public RichPopup getPpError() {
        return ppError;
    }


    public void setDialog(RichDialog d1) {
        this.dialog = d1;
    }

    public RichDialog getDialog() {
        return dialog;
    }

    public void setOt3(RichOutputText ot3) {
        this.ot3 = ot3;
    }

    public RichOutputText getOt3() {
        return ot3;
    }

    public String getForward() {
        return forward;
    }

    public void setForward(String forward) {
        this.forward = forward;
    }

    public void setOt4(RichOutputText ot4) {
        this.ot4 = ot4;
    }

    public RichOutputText getOt4() {
        return ot4;
    }

    public void setS5(RichSpacer s5) {
        this.s5 = s5;
    }

    public RichSpacer getS5() {
        return s5;
    }

    public void setCb_recordar_clave(RichCommandButton cb1) {
        this.cb_recordar_clave = cb1;
    }

    public RichCommandButton getCb_recordar_clave() {
        return cb_recordar_clave;
    }

    public void setPgl2(RichPanelGroupLayout pgl2) {
        this.pgl2 = pgl2;
    }

    public RichPanelGroupLayout getPgl2() {
        return pgl2;
    }


    public void cb_recordar_clave_actionListener(ActionEvent actionEvent) {
        String usuario = this.itUsuario.getValue().toString();
        try {

            if (MailConfigurator.getUsuario() == null) {
                // Consultar los parametros de correo y configurarlo
                ParametrosDelegate pd = ParametrosDelegate.getInstance();
                TipoParametro tipoParametro =
                    pd.getTipoParametro(TipoParametro.SERVIDOR_CORREO);
                String usuariop =
                    tipoParametro.getParametro("usuario").getValor();
                String password =
                    tipoParametro.getParametro("password").getValor();
                String host = tipoParametro.getParametro("host").getValor();
                String puerto =
                    tipoParametro.getParametro("puerto").getValor();
                MailConfigurator.setHost(host);
                MailConfigurator.setPassword(password);
                MailConfigurator.setPuerto(puerto);
                MailConfigurator.setUsuario(usuariop);
            }


            SeguridadDelegate sd = SeguridadDelegate.getInstance();
            UsuarioVO user = sd.getUsuario(usuario);
            if (user != null) {
                sd.restartPassword(user);
            }
            showMessage(getText("MAIL_CLAVE_ENVIADO"),
                        FacesMessage.SEVERITY_INFO);
        } catch (IdeamException e) {
            showMessage(e);
        }
    }
}
