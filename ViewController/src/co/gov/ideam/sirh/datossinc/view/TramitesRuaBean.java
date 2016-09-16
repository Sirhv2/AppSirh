package co.gov.ideam.sirh.datossinc.view;

import co.gov.ideam.sirh.datossinc.model.ConcesionSinc;
import co.gov.ideam.sirh.datossinc.model.PermisoVertimientoSinc;
import co.gov.ideam.sirh.datossinc.model.PredioSinc;
import co.gov.ideam.sirh.datossinc.model.RepresentanteSinc;
import co.gov.ideam.sirh.datossinc.model.TramitesTO;
import co.gov.ideam.sirh.datossinc.model.UsuarioAguaSinc;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.reportes.view.ReporteDelegate;
import co.gov.ideam.sirh.seguridad.model.PerfilVO;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.seguridad.view.SeguridadDelegate;
import co.gov.ideam.sirh.usuarios.agua.model.Concesion;
import co.gov.ideam.sirh.usuarios.agua.model.Predio;
import co.gov.ideam.sirh.usuarios.agua.view.UsuariosAguaDelegate;
import co.gov.ideam.sirh.util.Constantes;
import co.gov.ideam.sirh.util.ConstantesCalidad;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichMenu;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectItem;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichCommandMenuItem;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import org.apache.myfaces.trinidad.component.UIXGroup;

public class TramitesRuaBean extends StandarBean {
    private RichForm f2;
    private RichDocument d2;
    private RichPanelCollection pc1;
    private List listaTramitesRua;
    private String numeroExpediente;
    private RichPanelSplitter ps1;
    private RichPanelFormLayout pfl1;
    private RichCommandButton cb_aprobar;
    private RichMenu m2;
    private RichCommandMenuItem cmi_imprimir;
    private RichSpacer s1;
    private RichSelectOneChoice soc1;
    private RichSelectItem si1;
    private RichSelectItem si2;
    private RichSelectItem si3;
    private Concesion estados;
    private UsuarioAguaSinc usuRua;
    private RepresentanteSinc repRua;
    private PredioSinc prdRua;
    private ConcesionSinc conRua;
    private PermisoVertimientoSinc pvRua;
    private RichPanelGroupLayout panelGroupLayout1;
    private RichInputText it_numExp1;
    private RichSpacer s11;
    private RichSelectOneChoice soc11;
    private RichSelectItem si11;
    private RichSelectItem si21;
    private RichSelectItem si31;
    private RichCommandButton cb_buscar1;
    private RichPanelStretchLayout panelStretchLayout1;
    private UIXGroup group1;
    private RichPanelGroupLayout panelGroupLayout11;
    private RichSelectOneChoice soc_estado;
    private RichSelectItem si111;
    private RichSelectItem si211;
    private RichSelectItem si311;
    private RichPanelCollection pc11;
    private RichMenu m21;
    private RichCommandMenuItem cmi_imprimir1;
    private RichTable t_tramitesRua;
    private RichPanelGroupLayout panelGroupLayout2;
    private RichOutputText outputText1;

    private RichPanelGroupLayout pgl1;

    private String resolucionCaudal;
    private String fInicio;
    private String fFin;
    private String visiblePerfil = "true";
    private List listaAutoridades;
    private Autoridades autoridadRolRevision = null;

    public TramitesRuaBean() {
        FacesUtils.setActiveBean("tramitesRua", "tramitesRua",
                                 DatosSincDelegate.class);
        this.load();
    }


    public void load() {
        try {

            UsuarioConectadoTO usuarioConectado =
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

            DatosSincDelegate uad = DatosSincDelegate.getInstance();

            SeguridadDelegate seg = SeguridadDelegate.getInstance();
            PerfilVO pp = new PerfilVO();
            pp =
 seg.getPerfilUsuario(usuarioConectado.getUsuario().getCodigo());

            System.out.println("----------Perfil del usuario autenticado:" +
                               pp.getCodigo());
            if (usuarioConectado.getUsuario().getAutoridadAmbiental() ==
                null ||
                usuarioConectado.getUsuario().getAutoridadAmbiental().getId() ==
                null ||
                usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue() ==
                0 ||
                usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue() ==
                Constantes.IDEAM ||
                pp.getCodigo() == ConstantesCalidad.PERFIL_CONSULTA) {
                this.listaTramitesRua = new ArrayList();
                this.visiblePerfil = "false";
                this.listaAutoridades = this.cargarAutoridades();

                listaTramitesRua = uad.consultarDatosRUA();

            } else {
                this.visiblePerfil = "true";
                System.out.println("---------ffffff usuarioConectado:" +
                                   usuarioConectado.getUsuario().getAutoridadAmbiental().getId());

                this.listaTramitesRua = new ArrayList();

                listaTramitesRua =
                        uad.consultarDatosRUAxAut(usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue());

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


    public void setPc1(RichPanelCollection pc1) {
        this.pc1 = pc1;
    }

    public RichPanelCollection getPc1() {
        return pc1;
    }


  


 
    public String getNumeroExpediente() {
        return numeroExpediente;
    }

    public void setNumeroExpediente(String numeroExpediente) {
        this.numeroExpediente = numeroExpediente;
    }


    public void setPs1(RichPanelSplitter ps1) {
        this.ps1 = ps1;
    }

    public RichPanelSplitter getPs1() {
        return ps1;
    }

  

    public void concesion_actionListener(ActionEvent actionEvent) {
        Long codigoUsuario =
            (Long)actionEvent.getComponent().getAttributes().get("codigoUsuario");
        Long codigoPredio =
            (Long)actionEvent.getComponent().getAttributes().get("codigoPredio");
        Long codigo =
            (Long)actionEvent.getComponent().getAttributes().get("codigo");

        FacesUtils.setInSession("concesionSeleccionada", codigo);
        FacesUtils.setInSession("usuarioSeleccionado", codigoUsuario);
        FacesUtils.setInSession("predioSeleccionado", codigoPredio);
        FacesUtils.setInSession("paginaOrigen", "concesiones");
    }

    public void concesion_novedad_actionListener(ActionEvent actionEvent) {
        String numeroExpediente =
            (String)actionEvent.getComponent().getAttributes().get("numeroExpediente");
        try {
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
            Concesion novedad = uad.getConcesion(numeroExpediente);
            if (novedad != null) {
                FacesUtils.setInSession("concesionSeleccionada",
                                        novedad.getCodigo());
                Predio p = uad.getPredio(novedad.getCodigoPredio());
                FacesUtils.setInSession("predioSeleccionado", p);
                FacesUtils.setInSession("usuarioSeleccionado",
                                        p.getCodigoUsuario());
            }
        } catch (IdeamException e) {
            showMessage(e);
        }
        FacesUtils.setInSession("paginaOrigen", "concesiones");
    }

    public void setM2(RichMenu m2) {
        this.m2 = m2;
    }

    public RichMenu getM2() {
        return m2;
    }

    public void setCmi_imprimir(RichCommandMenuItem cmi2) {
        this.cmi_imprimir = cmi2;
    }

    public RichCommandMenuItem getCmi_imprimir() {
        return cmi_imprimir;
    }

    public void cmi_imprimir_actionListener(ActionEvent actionEvent) {
        try {
            HashMap parametros = new HashMap();
            parametros.put("p_modulo", "Registro de Usuarios del Agua");
            parametros.put("p_titulo", " Tramites Rua");
            this.generateReport("listaTramitesRua.jasper",
                                this.listaTramitesRua, parametros,
                                ReporteDelegate.EXCEL);
        } catch (IdeamException e) {
            showMessage(e);
        }
    }

    public void cb_aprobar_actionListener(ActionEvent actionEvent) {
        if (t_tramitesRua.getSelectedRowData() == null) {
            showMessage(getText("seleccionar_registro"),
                        FacesMessage.SEVERITY_ERROR);
            return;
        }

        TramitesTO tm = (TramitesTO)t_tramitesRua.getSelectedRowData();
        FacesUtils.setInSession("tramiteSeleccionado", tm);
        guardar();
    }


    public void guardar() {
        try {
            DatosSincDelegate ruad = DatosSincDelegate.getInstance();
            UsuariosAguaDelegate usud = UsuariosAguaDelegate.getInstance();
            String tipoTramite = "";
            Long idRegistro =0L;
            Object obj = FacesUtils.getFromSession("tramiteSeleccionado");
            TramitesTO tt = new TramitesTO();
            if (obj instanceof TramitesTO) {
                tt = (TramitesTO)obj;
                Long codusu = tt.getCodigoUsuario();
                this.usuRua = ruad.consultarUsuarioRua(codusu);
                
                if (this.usuRua.getCodigoTipoPersona()!=5){
                System.out.println("--UUUUUUUUUUUU-----------------this.usuRua getRazonSocial--" +this.usuRua.getRazonSocial());
                      
                this.repRua = ruad.consultarRepresentanteRua(codusu);
                System.out.println("--RRRRRRRRRRRRRR-----------------this.repRua--" +this.repRua.getApellidos());
                }
                Long codprd = tt.getCodigoPredio();
                this.prdRua = ruad.consultarPredioRua(codprd);
                System.out.println("--PPPPPPPPPPPPP-----------------this.prdRua--" +this.prdRua.getNombre());
                
                tipoTramite = tt.getTipoTramite();
                if (tipoTramite.equals("CO")) {
                    Long codconc = tt.getCodigo();
                    this.conRua = ruad.consultarConcesionRua(codconc);
                    System.out.println("--CCCCCCCCCCCC-----------------this.conRua--" +this.conRua.getNumeroExpediente());
                     idRegistro =
                        usud.registrarUsuarioPredioRUACO(this.usuRua, this.repRua,
                                                       this.prdRua, this.conRua
                                                      );
                   
                } else if (tipoTramite.equals("PV")) {
                    Long codpv = tt.getCodigo();
                    this.pvRua = ruad.consultarPermisoVertimientoRua(codpv);
                    System.out.println("--VVVVVVVVV-----------------this.pvRua--" +this.pvRua.getNumeroExpediente());
                    
                     idRegistro =
                        usud.registrarUsuarioPredioRUAPV(this.usuRua, this.repRua,
                                                       this.prdRua, this.pvRua
                                                      );
                }

              
System.out.println("--ffffffffffffffffff-----------------idRegistro--" + idRegistro);
                
            }


        } catch (Exception e) {
            System.out.println("--ERROR AL CARGAR lista sincronizada vital--" + e);
        }


  

    }


    public void setS1(RichSpacer s1) {
        this.s1 = s1;
    }

    public RichSpacer getS1() {
        return s1;
    }


    public void setSoc1(RichSelectOneChoice soc1) {
        this.soc1 = soc1;
    }

    public RichSelectOneChoice getSoc1() {
        return soc1;
    }

    public void setSi1(RichSelectItem si1) {
        this.si1 = si1;
    }

    public RichSelectItem getSi1() {
        return si1;
    }

    public void setSi2(RichSelectItem si2) {
        this.si2 = si2;
    }

    public RichSelectItem getSi2() {
        return si2;
    }

    public void setSi3(RichSelectItem si3) {
        this.si3 = si3;
    }

    public RichSelectItem getSi3() {
        return si3;
    }

    public Concesion getEstados() {
        return estados;
    }

    public void setEstados(Concesion estados) {
        this.estados = estados;
    }

    public void setPanelGroupLayout1(RichPanelGroupLayout panelGroupLayout1) {
        this.panelGroupLayout1 = panelGroupLayout1;
    }

    public RichPanelGroupLayout getPanelGroupLayout1() {
        return panelGroupLayout1;
    }

    public void setIt_numExp1(RichInputText it_numExp1) {
        this.it_numExp1 = it_numExp1;
    }

    public RichInputText getIt_numExp1() {
        return it_numExp1;
    }

    public void setS11(RichSpacer s11) {
        this.s11 = s11;
    }

    public RichSpacer getS11() {
        return s11;
    }

    public void setSoc11(RichSelectOneChoice soc11) {
        this.soc11 = soc11;
    }

    public RichSelectOneChoice getSoc11() {
        return soc11;
    }

    public void setSi11(RichSelectItem si11) {
        this.si11 = si11;
    }

    public RichSelectItem getSi11() {
        return si11;
    }

    public void setSi21(RichSelectItem si21) {
        this.si21 = si21;
    }

    public RichSelectItem getSi21() {
        return si21;
    }

    public void setSi31(RichSelectItem si31) {
        this.si31 = si31;
    }

    public RichSelectItem getSi31() {
        return si31;
    }

    public void setCb_buscar1(RichCommandButton cb_buscar1) {
        this.cb_buscar1 = cb_buscar1;
    }

    public RichCommandButton getCb_buscar1() {
        return cb_buscar1;
    }

    public void setPanelStretchLayout1(RichPanelStretchLayout panelStretchLayout1) {
        this.panelStretchLayout1 = panelStretchLayout1;
    }

    public RichPanelStretchLayout getPanelStretchLayout1() {
        return panelStretchLayout1;
    }

    public void setGroup1(UIXGroup group1) {
        this.group1 = group1;
    }

    public UIXGroup getGroup1() {
        return group1;
    }

    public void setPanelGroupLayout11(RichPanelGroupLayout panelGroupLayout11) {
        this.panelGroupLayout11 = panelGroupLayout11;
    }

    public RichPanelGroupLayout getPanelGroupLayout11() {
        return panelGroupLayout11;
    }


    public void setSoc_estado(RichSelectOneChoice soc111) {
        this.soc_estado = soc111;
    }

    public RichSelectOneChoice getSoc_estado() {
        return soc_estado;
    }

    public void setSi111(RichSelectItem si111) {
        this.si111 = si111;
    }

    public RichSelectItem getSi111() {
        return si111;
    }

    public void setSi211(RichSelectItem si211) {
        this.si211 = si211;
    }

    public RichSelectItem getSi211() {
        return si211;
    }

    public void setSi311(RichSelectItem si311) {
        this.si311 = si311;
    }

    public RichSelectItem getSi311() {
        return si311;
    }


    public void setPc11(RichPanelCollection pc11) {
        this.pc11 = pc11;
    }

    public RichPanelCollection getPc11() {
        return pc11;
    }

    public void setM21(RichMenu m21) {
        this.m21 = m21;
    }

    public RichMenu getM21() {
        return m21;
    }

    public void setCmi_imprimir1(RichCommandMenuItem cmi_imprimir1) {
        this.cmi_imprimir1 = cmi_imprimir1;
    }

    public RichCommandMenuItem getCmi_imprimir1() {
        return cmi_imprimir1;
    }


    public void setPanelGroupLayout2(RichPanelGroupLayout panelGroupLayout2) {
        this.panelGroupLayout2 = panelGroupLayout2;
    }

    public RichPanelGroupLayout getPanelGroupLayout2() {
        return panelGroupLayout2;
    }

    public void setOutputText1(RichOutputText outputText1) {
        this.outputText1 = outputText1;
    }

    public RichOutputText getOutputText1() {
        return outputText1;
    }


    public void setPgl1(RichPanelGroupLayout pgl1) {
        this.pgl1 = pgl1;
    }

    public RichPanelGroupLayout getPgl1() {
        return pgl1;
    }


    public void setPfl1(RichPanelFormLayout pfl1) {
        this.pfl1 = pfl1;
    }

    public RichPanelFormLayout getPfl1() {
        return pfl1;
    }


    public void setResolucionCaudal(String resolucionCaudal) {
        this.resolucionCaudal = resolucionCaudal;
    }

    public String getResolucionCaudal() {
        return resolucionCaudal;
    }

    public void setFInicio(String fInicio) {
        this.fInicio = fInicio;
    }

    public String getFInicio() {
        return fInicio;
    }

    public void setFFin(String fFin) {
        this.fFin = fFin;
    }

    public String getFFin() {
        return fFin;
    }


   
    public void setVisiblePerfil(String visiblePerfil) {
        this.visiblePerfil = visiblePerfil;
    }

    public String getVisiblePerfil() {
        return visiblePerfil;
    }


    public void setListaAutoridades(List listaAutoridades) {
        this.listaAutoridades = listaAutoridades;
    }

    public List getListaAutoridades() {
        return listaAutoridades;
    }


    public void setAutoridadRolRevision(Autoridades autoridadRolRevision) {
        this.autoridadRolRevision = autoridadRolRevision;
    }

    public Autoridades getAutoridadRolRevision() {
        return autoridadRolRevision;
    }

    public void setListaTramitesRua(List listaTramitesRua) {
        this.listaTramitesRua = listaTramitesRua;
    }

    public List getListaTramitesRua() {
        return listaTramitesRua;
    }

    public void setT_tramitesRua(RichTable t_tramitesRua) {
        this.t_tramitesRua = t_tramitesRua;
    }

    public RichTable getT_tramitesRua() {
        return t_tramitesRua;
    }


    public void setCb_aprobar(RichCommandButton cb_aprobar) {
        this.cb_aprobar = cb_aprobar;
    }

    public RichCommandButton getCb_aprobar() {
        return cb_aprobar;
    }
}
