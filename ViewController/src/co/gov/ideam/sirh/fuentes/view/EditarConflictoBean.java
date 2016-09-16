package co.gov.ideam.sirh.fuentes.view;

import co.gov.ideam.sirh.auditoria.model.Auditoria;
import co.gov.ideam.sirh.auditoria.view.AuditoriasDelegate;

import co.gov.ideam.sirh.calidad.model.PuntoMonitoreo;
import co.gov.ideam.sirh.calidad.view.CalidadDelegate;
import co.gov.ideam.sirh.fuentes.model.FnttConflicto;
import co.gov.ideam.sirh.fuentes.model.FnttFuente;

import co.gov.ideam.sirh.fuentes.model.FnttTramo;
import co.gov.ideam.sirh.parametros.model.Categoria;
import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.parametros.model.Lista;

import co.gov.ideam.sirh.parametros.view.ParametrosDelegate;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;

import co.gov.ideam.sirh.util.ConstantesParametros;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
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

import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanRadio;
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
import oracle.adf.view.rich.component.rich.output.RichImage;
import oracle.adf.view.rich.component.rich.output.RichOutputLabel;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;


public class EditarConflictoBean extends StandarBean {

    private List listaTramos;
    private FnttFuente fuente;
    private String accionAdicionar;
    private List sistemasReferencia;
    private List listaDepartamentos;
    private List listaMunicipios;
    private FnttConflicto conflicto;
    private Boolean estadoAbiertoAct;
    private Boolean estadoCerradoAct;

    private Lista sistemaRefAct;
    private FnttTramo tramoAct;
    private Divipola deptoAct;
    private Divipola municAct;

    private RichForm formAF1;
    private RichDocument documentAF1;

    private RichPanelStretchLayout psl1;
    private RichPanelStretchLayout psl2;
    private RichPanelStretchLayout psl7;

    private RichPanelFormLayout pfl1;
    private RichPanelFormLayout pfl2;

    private RichPanelGroupLayout pgl1;
    private RichPanelGroupLayout pgl2;
    private RichPanelGroupLayout pgl3;
    private RichPanelGroupLayout pgl14;

    private RichPanelBox pb1;

    private RichPanelSplitter ps1;

    private RichPanelCollection panelCollection1;

    private RichCommandButton cbNext1;
    private RichCommandButton cbRelacion;
    private RichCommandButton cbAceptar;

    private RichSpacer s1;

    private RichSelectOneChoice soc_Tramos;
    private UISelectItems selectItems7;

    private RichInputText it_nombre;
    private RichInputText it_descripcion;


    private RichMenu menu1;
    private RichCommandMenuItem commandMenuItem1;


    private RichOutputText ot8;
    private RichDialog d_detalle;


    private RichImage i1;

    private RichPopup p_registro_exitoso;

    private RichDialog d_registro_exitoso;

    private RichPanelFormLayout pfl21;
    private RichInputText it_nombre1;
    private RichPanelGroupLayout pgl4;
    private RichCommandLink cl4;
    private RichInputText it_Radicado;
    private RichInputDate id_fecha_vigencia;
    private RichInputDate id_fechaCierre;
    private RichSelectBooleanRadio sbrAbierto;
    private RichSelectBooleanRadio sbrCerrado;
    private RichPanelGroupLayout panelGroupLayout3;
    private RichSelectOneChoice soc_Tramos1;
    private UISelectItems selectItems71;
    private RichInputText it_nombre2;
    private RichInputText it_Radicado1;
    private RichInputDate id_fecha_vigencia1;
    private RichInputText it_descripcion1;
    private RichSelectBooleanRadio sbrAbierto1;
    private RichSelectBooleanRadio sbrCerrado1;
    private RichInputDate id_fechaCierre1;
    private RichPanelGroupLayout panelGroupLayout2;
    private RichPanelGroupLayout panelGroupLayout1;
    private RichPanelFormLayout panelFormLayout2;
    private RichSpacer spacer1;
    private RichActiveOutputText activeOutputText2;
  private RichSelectOneChoice soc_sistema;
    private UISelectItems selectItems8;
    private RichPanelGroupLayout panelGroupLayout5;
    private RichPanelFormLayout panelFormLayout5;
  private RichOutputLabel outputLabel3;
    private RichPanelFormLayout panelFormLayout3;
    private RichSpacer spacer3;
    private RichOutputLabel outputLabel1;
    private RichInputText it_grados_pi;
    private RichInputText it_minutos_pi;
    private RichInputText it_segundos_pi;
    private RichPanelFormLayout panelFormLayout4;
    private RichSpacer spacer6;
    private RichOutputLabel outputLabel2;
    private RichInputText it_grad_long_pi;
    private RichInputText it_minutos_long_pi;
    private RichInputText it_segundos_long_pi;
    private RichPanelFormLayout panelFormLayout6;
    private RichInputText it_altitud_pi;
    private RichPanelFormLayout pfl22;
    private RichSelectOneChoice soc_departamento;
    private UISelectItems selectItems4;
    private RichSelectOneChoice soc_municipio;
    private UISelectItems selectItems5;
    private RichCommandLink commandLink1;
    private RichPanelGroupLayout panelGroupLayout6;
    private RichPanelGroupLayout panelGroupLayout7;
    private RichSpacer spacer5;
    private RichSpacer spacer8;
    private RichSpacer spacer9;
    private RichPanelGroupLayout panelGroupLayout8;
    private RichMenuBar menuBar1;
    private RichMenu menu3;
    private RichCommandMenuItem commandMenuItem11;
    private RichCommandMenuItem commandMenuItem2;
    private RichCommandMenuItem commandMenuItem3;
    private RichCommandMenuItem commandMenuItem4;
    private RichCommandMenuItem commandMenuItem5;
    private RichOutputText outputText1;


    public EditarConflictoBean() {
        FacesUtils.setActiveBean("editarConflictoBean", "Editar Conflicto",
                                 EditarConflictoBean.class);
        FacesUtils.removeManagedBeanFromSession("FuentesBean");
        this.load();
    }

    public void load() {
        try {

            fuente =
                    (FnttFuente)FacesUtils.getFromSession("fuenteSeleccionada");
            conflicto =
                    (FnttConflicto)FacesUtils.getFromSession("conflictoSeleccionado");

            UsuarioConectadoTO usuarioConectado =
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

            listaTramos = getListaTramos(fuente.getId().intValue());

            FnttTramo t = new FnttTramo();
            t.setId(0L);

            SelectItem si = new SelectItem(t, "SIN TRAMO ESPECIFICO");
            listaTramos.add(si);

            sistemasReferencia =
                    cargarParametro(ConstantesParametros.CATEGORIA_SISTEMA_REFERENCIA);
            listaDepartamentos = cargarDivipola(null);
            listaMunicipios =
                    cargarDivipola(new Long(conflicto.getIdDepartamento()));

            Iterator it = sistemasReferencia.iterator();
            while (it.hasNext()) {
                SelectItem sr = (SelectItem)it.next();
                if (conflicto.getSistemaReferencia() != null)
                    if (((Lista)sr.getValue()).getCodigo().compareTo(conflicto.getSistemaReferencia().intValue()) == 0)
                        sistemaRefAct = (Lista)sr.getValue();
            }


            it = listaTramos.iterator();
            while (it.hasNext()) {
                SelectItem tr = (SelectItem)it.next();

                if (((FnttTramo)tr.getValue()).getId().compareTo(conflicto.getIdTramo()) == 0) {
                    tramoAct = (FnttTramo)tr.getValue();
                }
            }


            it = listaDepartamentos.iterator();
            while (it.hasNext()) {
                SelectItem dp = (SelectItem)it.next();
                if (((Divipola)dp.getValue()).getId().compareTo(new Long(conflicto.getIdDepartamento())) == 0)
                    deptoAct = ((Divipola)dp.getValue());
            }


            it = listaMunicipios.iterator();
            while (it.hasNext()) {
                SelectItem mu = (SelectItem)it.next();
                if (((Divipola)mu.getValue()).getId().compareTo(new Long(conflicto.getIdMunicipio())) == 0)
                    municAct = (Divipola)mu.getValue();
            }

            estadoAbiertoAct = false;

            if (conflicto.getEstadoConflicto() == 0)
                estadoAbiertoAct = true;

            estadoCerradoAct = !estadoAbiertoAct;

        } catch (IdeamException e) {
            showMessage(e);
        }
    }

    public void soc_Tramos_valueChangeListener(ValueChangeEvent event) throws IdeamException {

        FnttTramo tramo = (FnttTramo)event.getNewValue();

        if (tramo == null || tramo.getId() == 0) {
            soc_sistema.setValue(null);

            it_grados_pi.setValue(null);
            it_minutos_pi.setValue(null);
            it_segundos_pi.setValue(null);

            it_grad_long_pi.setValue(null);
            it_minutos_long_pi.setValue(null);
            it_segundos_long_pi.setValue(null);

            it_altitud_pi.setValue(null);

        } else {
            soc_sistema.setValue(tramo.getSistemaReferencia());

            it_grados_pi.setValue(tramo.getGradosPi());
            it_minutos_pi.setValue(tramo.getMinutosPi());
            it_segundos_pi.setValue(tramo.getSegundosPi());

            it_grad_long_pi.setValue(tramo.getGradosLongPi());
            it_minutos_long_pi.setValue(tramo.getMinutosLongPi());
            it_segundos_long_pi.setValue(tramo.getSegundosLongPi());

            it_altitud_pi.setValue(tramo.getAltitudPi());

        }

        AdfFacesContext.getCurrentInstance().addPartialTarget(soc_sistema);

        AdfFacesContext.getCurrentInstance().addPartialTarget(it_grados_pi);
        AdfFacesContext.getCurrentInstance().addPartialTarget(it_minutos_pi);
        AdfFacesContext.getCurrentInstance().addPartialTarget(it_segundos_pi);

        AdfFacesContext.getCurrentInstance().addPartialTarget(it_grad_long_pi);
        AdfFacesContext.getCurrentInstance().addPartialTarget(it_minutos_long_pi);
        AdfFacesContext.getCurrentInstance().addPartialTarget(it_segundos_long_pi);

        AdfFacesContext.getCurrentInstance().addPartialTarget(it_altitud_pi);

    }

    public void soc_Departamentos_valueChangeListener(ValueChangeEvent event) throws IdeamException {

        Object departamento = event.getNewValue();
        this.listaMunicipios = new ArrayList();
        try {
            if (departamento != null)
                this.listaMunicipios =
                        cargarDivipola(((Divipola)departamento).getId());
        } catch (IdeamException e) {
            showMessage(e);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(soc_municipio);
    }

    public void soc_Estado_valueChangeListener(ValueChangeEvent event) throws IdeamException {

        id_fechaCierre1.setVisible(false);

        if (sbrAbierto1.getValue().toString().equals("false"))
            id_fechaCierre1.setVisible(true);

        AdfFacesContext.getCurrentInstance().addPartialTarget(id_fechaCierre1);
    }

    public void cbNext1_actionListener(ActionEvent actionEvent) throws IdeamException {
        try {

            accionAdicionar = "";

            validarForma();
            guardarConflicto();

        } catch (IdeamException e) {
            showMessage(e);
        } catch (Exception e) {
            showMessage(e.getMessage());
        }
    }

    public void validarForma() throws Exception {

        if (soc_Tramos1.getValue() == null)
            throw new Exception("Seleccione un tramo o indique que no hay tramo");

        if (it_nombre2.getValue() == null)
            throw new Exception("Ingree nombre del conflicto");

        FnttTramo tramo = (FnttTramo)soc_Tramos1.getValue();

        if (tramo.getId() != 0L) {
            if (soc_sistema.getValue() == null)
                throw new Exception("Seleccione sistema de referencia");
        }
        if (soc_departamento.getValue() == null)
            throw new Exception("Seleccione departamento");

        if (soc_municipio.getValue() == null)
            throw new Exception("Seleccione municipio");

    }

    public void guardarConflicto() {
        try {

            this.fuente =
                    (FnttFuente)FacesUtils.getFromSession("fuenteSeleccionada");

            UsuarioConectadoTO usuarioConectado =
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

            FnttTramo tramo = (FnttTramo)soc_Tramos1.getValue();
            Lista sistemaRef = (Lista)soc_sistema.getValue();
            Divipola departamento = (Divipola)soc_departamento.getValue();
            Divipola municipio = (Divipola)soc_municipio.getValue();

            conflicto.setIdFuente(fuente.getId());

            conflicto.setIdTramo(tramo.getId());

            if (sistemaRef == null )
                conflicto.setSistemaReferencia(null);
            else
                conflicto.setSistemaReferencia(new Long(sistemaRef.getCodigo()));

            conflicto.setIdDepartamento(departamento.getId().intValue());
            conflicto.setIdMunicipio(municipio.getId().intValue());

            conflicto.setEstadoConflicto(0);
            if (sbrAbierto1.getValue().toString().equals("false"))
                conflicto.setEstadoConflicto(1);

            conflicto.setCodAutoridad(usuarioConectado.getUsuario().getAutoridadAmbiental().getId());

            FuenteDelegate fd = FuenteDelegate.getInstance();
            conflicto = fd.updateConflicto(conflicto);
            guardarAuditoria("GUARDAR", "CONFLICTO", getClass().getName(),
                             conflicto.getId());

            showPopup(p_registro_exitoso, true);

        } catch (IdeamException e) {
            showMessage(e);
        }
    }

    public void cb_aceptar_actionListener(ActionEvent actionEvent) {
        accionAdicionar = "listarConflictos";
    }

    public void lk_todosConflictos_actionListener(ActionEvent actionEvent) {
        System.out.println("INVOCA TODOS CONFLICTOS");
        accionAdicionar = "listarConflictos";
    }

    public String getAceptarAction() {
        System.out.println("INVOCA TODOS CONFLICTOS ACCION");
        return accionAdicionar;
    }

    public void setFormAF1(RichForm formAF1) {
        this.formAF1 = formAF1;
    }

    public RichForm getFormAF1() {
        return formAF1;
    }

    public void setDocumentAF1(RichDocument documentAF1) {
        this.documentAF1 = documentAF1;
    }

    public RichDocument getDocumentAF1() {
        return documentAF1;
    }

    public void setD_detalle(RichDialog d_detalle) {
        this.d_detalle = d_detalle;
    }

    public RichDialog getD_detalle() {
        return d_detalle;
    }

    public void setPsl2(RichPanelStretchLayout psl2) {
        this.psl2 = psl2;
    }

    public RichPanelStretchLayout getPsl2() {
        return psl2;
    }

    public void setPfl2(RichPanelFormLayout pfl2) {
        this.pfl2 = pfl2;
    }

    public RichPanelFormLayout getPfl2() {
        return pfl2;
    }


    public void setIt_nombre(RichInputText it_nombre) {
        this.it_nombre = it_nombre;
    }

    public RichInputText getIt_nombre() {
        return it_nombre;
    }

    public void setIt_descripcion(RichInputText it_descripcion) {
        this.it_descripcion = it_descripcion;
    }

    public RichInputText getIt_descripcion() {
        return it_descripcion;
    }


    public void setPsl1(RichPanelStretchLayout psl1) {
        this.psl1 = psl1;
    }

    public RichPanelStretchLayout getPsl1() {
        return psl1;
    }

    public void setPgl1(RichPanelGroupLayout pgl1) {
        this.pgl1 = pgl1;
    }

    public RichPanelGroupLayout getPgl1() {
        return pgl1;
    }

    public void setPgl2(RichPanelGroupLayout pgl2) {
        this.pgl2 = pgl2;
    }

    public RichPanelGroupLayout getPgl2() {
        return pgl2;
    }

    public void setPb1(RichPanelBox pb1) {
        this.pb1 = pb1;
    }

    public RichPanelBox getPb1() {
        return pb1;
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

    public void setPgl3(RichPanelGroupLayout pgl3) {
        this.pgl3 = pgl3;
    }

    public RichPanelGroupLayout getPgl3() {
        return pgl3;
    }


    public void setPfl1(RichPanelFormLayout pfl1) {
        this.pfl1 = pfl1;
    }

    public RichPanelFormLayout getPfl1() {
        return pfl1;
    }

    public void setSoc_Tramos(RichSelectOneChoice selectOneChoice7) {
        this.soc_Tramos = selectOneChoice7;
    }

    public RichSelectOneChoice getSoc_Tramos() {
        return soc_Tramos;
    }

    public void setSelectItems7(UISelectItems selectItems7) {
        this.selectItems7 = selectItems7;
    }

    public UISelectItems getSelectItems7() {
        return selectItems7;
    }


    public void setCbRelacion(RichCommandButton cbRelacion) {
        this.cbRelacion = cbRelacion;
    }

    public RichCommandButton getCbRelacion() {
        return cbRelacion;
    }


    public void setPs1(RichPanelSplitter ps1) {
        this.ps1 = ps1;
    }

    public RichPanelSplitter getPs1() {
        return ps1;
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

    public void setPsl7(RichPanelStretchLayout psl7) {
        this.psl7 = psl7;
    }

    public RichPanelStretchLayout getPsl7() {
        return psl7;
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


    public void setFuente(FnttFuente fuente) {
        this.fuente = fuente;
    }

    public FnttFuente getFuente() {
        return fuente;
    }


    public void setPfl21(RichPanelFormLayout pfl21) {
        this.pfl21 = pfl21;
    }

    public RichPanelFormLayout getPfl21() {
        return pfl21;
    }

    public void setIt_nombre1(RichInputText it_nombre1) {
        this.it_nombre1 = it_nombre1;
    }

    public RichInputText getIt_nombre1() {
        return it_nombre1;
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

    public void setIt_Radicado(RichInputText inputText1) {
        this.it_Radicado = inputText1;
    }

    public RichInputText getIt_Radicado() {
        return it_Radicado;
    }

    public void setId_fecha_vigencia(RichInputDate id_fecha_vigencia) {
        this.id_fecha_vigencia = id_fecha_vigencia;
    }

    public RichInputDate getId_fecha_vigencia() {
        return id_fecha_vigencia;
    }


    public void setId_fechaCierre(RichInputDate inputDate1) {
        this.id_fechaCierre = inputDate1;
    }

    public RichInputDate getId_fechaCierre() {
        return id_fechaCierre;
    }

    public void setListaTramos(List listaTramos) {
        this.listaTramos = listaTramos;
    }

    public List getListaTramos() {
        return listaTramos;
    }

    public void setSbrAbierto(RichSelectBooleanRadio sbr1) {
        this.sbrAbierto = sbr1;
    }

    public RichSelectBooleanRadio getSbrAbierto() {
        return sbrAbierto;
    }

    public void setSbrCerrado(RichSelectBooleanRadio sbr2) {
        this.sbrCerrado = sbr2;
    }

    public RichSelectBooleanRadio getSbrCerrado() {
        return sbrCerrado;
    }

    public void setAccionAdicionar(String accionAdicionar) {
        this.accionAdicionar = accionAdicionar;
    }

    public String getAccionAdicionar() {
        return accionAdicionar;
    }

    public void setPanelGroupLayout3(RichPanelGroupLayout panelGroupLayout3) {
        this.panelGroupLayout3 = panelGroupLayout3;
    }

    public RichPanelGroupLayout getPanelGroupLayout3() {
        return panelGroupLayout3;
    }

    public void setSoc_Tramos1(RichSelectOneChoice soc_Tramos1) {
        this.soc_Tramos1 = soc_Tramos1;
    }

    public RichSelectOneChoice getSoc_Tramos1() {
        return soc_Tramos1;
    }

    public void setSelectItems71(UISelectItems selectItems71) {
        this.selectItems71 = selectItems71;
    }

    public UISelectItems getSelectItems71() {
        return selectItems71;
    }

    public void setIt_nombre2(RichInputText it_nombre2) {
        this.it_nombre2 = it_nombre2;
    }

    public RichInputText getIt_nombre2() {
        return it_nombre2;
    }

    public void setIt_Radicado1(RichInputText it_Radicado1) {
        this.it_Radicado1 = it_Radicado1;
    }

    public RichInputText getIt_Radicado1() {
        return it_Radicado1;
    }

    public void setId_fecha_vigencia1(RichInputDate id_fecha_vigencia1) {
        this.id_fecha_vigencia1 = id_fecha_vigencia1;
    }

    public RichInputDate getId_fecha_vigencia1() {
        return id_fecha_vigencia1;
    }

    public void setIt_descripcion1(RichInputText it_descripcion1) {
        this.it_descripcion1 = it_descripcion1;
    }

    public RichInputText getIt_descripcion1() {
        return it_descripcion1;
    }

    public void setSbrAbierto1(RichSelectBooleanRadio sbrAbierto1) {
        this.sbrAbierto1 = sbrAbierto1;
    }

    public RichSelectBooleanRadio getSbrAbierto1() {
        return sbrAbierto1;
    }

    public void setSbrCerrado1(RichSelectBooleanRadio sbrCerrado1) {
        this.sbrCerrado1 = sbrCerrado1;
    }

    public RichSelectBooleanRadio getSbrCerrado1() {
        return sbrCerrado1;
    }

    public void setId_fechaCierre1(RichInputDate id_fechaCierre1) {
        this.id_fechaCierre1 = id_fechaCierre1;
    }

    public RichInputDate getId_fechaCierre1() {
        return id_fechaCierre1;
    }

    public void setPanelGroupLayout2(RichPanelGroupLayout panelGroupLayout2) {
        this.panelGroupLayout2 = panelGroupLayout2;
    }

    public RichPanelGroupLayout getPanelGroupLayout2() {
        return panelGroupLayout2;
    }

    public void setPanelGroupLayout1(RichPanelGroupLayout panelGroupLayout1) {
        this.panelGroupLayout1 = panelGroupLayout1;
    }

    public RichPanelGroupLayout getPanelGroupLayout1() {
        return panelGroupLayout1;
    }

    public void setPanelFormLayout2(RichPanelFormLayout panelFormLayout2) {
        this.panelFormLayout2 = panelFormLayout2;
    }

    public RichPanelFormLayout getPanelFormLayout2() {
        return panelFormLayout2;
    }

    public void setSpacer1(RichSpacer spacer1) {
        this.spacer1 = spacer1;
    }

    public RichSpacer getSpacer1() {
        return spacer1;
    }

    public void setActiveOutputText2(RichActiveOutputText activeOutputText2) {
        this.activeOutputText2 = activeOutputText2;
    }

    public RichActiveOutputText getActiveOutputText2() {
        return activeOutputText2;
    }


  public void setSoc_sistema(RichSelectOneChoice selectOneChoice8) {
        this.soc_sistema = selectOneChoice8;
    }

    public RichSelectOneChoice getSoc_sistema() {
        return soc_sistema;
    }

    public void setSelectItems8(UISelectItems selectItems8) {
        this.selectItems8 = selectItems8;
    }

    public UISelectItems getSelectItems8() {
        return selectItems8;
    }

    public void setPanelGroupLayout5(RichPanelGroupLayout panelGroupLayout5) {
        this.panelGroupLayout5 = panelGroupLayout5;
    }

    public RichPanelGroupLayout getPanelGroupLayout5() {
        return panelGroupLayout5;
    }

    public void setPanelFormLayout5(RichPanelFormLayout panelFormLayout5) {
        this.panelFormLayout5 = panelFormLayout5;
    }

    public RichPanelFormLayout getPanelFormLayout5() {
        return panelFormLayout5;
    }


  public void setOutputLabel3(RichOutputLabel outputLabel3) {
        this.outputLabel3 = outputLabel3;
    }

    public RichOutputLabel getOutputLabel3() {
        return outputLabel3;
    }

    public void setPanelFormLayout3(RichPanelFormLayout panelFormLayout3) {
        this.panelFormLayout3 = panelFormLayout3;
    }

    public RichPanelFormLayout getPanelFormLayout3() {
        return panelFormLayout3;
    }

    public void setSpacer3(RichSpacer spacer3) {
        this.spacer3 = spacer3;
    }

    public RichSpacer getSpacer3() {
        return spacer3;
    }

    public void setOutputLabel1(RichOutputLabel outputLabel1) {
        this.outputLabel1 = outputLabel1;
    }

    public RichOutputLabel getOutputLabel1() {
        return outputLabel1;
    }

    public void setIt_grados_pi(RichInputText it_grados_pi) {
        this.it_grados_pi = it_grados_pi;
    }

    public RichInputText getIt_grados_pi() {
        return it_grados_pi;
    }

    public void setIt_minutos_pi(RichInputText it_minutos_pi) {
        this.it_minutos_pi = it_minutos_pi;
    }

    public RichInputText getIt_minutos_pi() {
        return it_minutos_pi;
    }

    public void setIt_segundos_pi(RichInputText it_segundos_pi) {
        this.it_segundos_pi = it_segundos_pi;
    }

    public RichInputText getIt_segundos_pi() {
        return it_segundos_pi;
    }

    public void setPanelFormLayout4(RichPanelFormLayout panelFormLayout4) {
        this.panelFormLayout4 = panelFormLayout4;
    }

    public RichPanelFormLayout getPanelFormLayout4() {
        return panelFormLayout4;
    }

    public void setSpacer6(RichSpacer spacer6) {
        this.spacer6 = spacer6;
    }

    public RichSpacer getSpacer6() {
        return spacer6;
    }

    public void setOutputLabel2(RichOutputLabel outputLabel2) {
        this.outputLabel2 = outputLabel2;
    }

    public RichOutputLabel getOutputLabel2() {
        return outputLabel2;
    }

    public void setIt_grad_long_pi(RichInputText it_grad_long_pi) {
        this.it_grad_long_pi = it_grad_long_pi;
    }

    public RichInputText getIt_grad_long_pi() {
        return it_grad_long_pi;
    }

    public void setIt_minutos_long_pi(RichInputText it_minutos_long_pi) {
        this.it_minutos_long_pi = it_minutos_long_pi;
    }

    public RichInputText getIt_minutos_long_pi() {
        return it_minutos_long_pi;
    }

    public void setIt_segundos_long_pi(RichInputText it_segundos_long_pi) {
        this.it_segundos_long_pi = it_segundos_long_pi;
    }

    public RichInputText getIt_segundos_long_pi() {
        return it_segundos_long_pi;
    }

    public void setPanelFormLayout6(RichPanelFormLayout panelFormLayout6) {
        this.panelFormLayout6 = panelFormLayout6;
    }

    public RichPanelFormLayout getPanelFormLayout6() {
        return panelFormLayout6;
    }

    public void setIt_altitud_pi(RichInputText it_altitud_pi) {
        this.it_altitud_pi = it_altitud_pi;
    }

    public RichInputText getIt_altitud_pi() {
        return it_altitud_pi;
    }


    public void setSistemasReferencia(List sistemasReferencia) {
        this.sistemasReferencia = sistemasReferencia;
    }

    public List getSistemasReferencia() {
        return sistemasReferencia;
    }

    public void setPfl22(RichPanelFormLayout pfl22) {
        this.pfl22 = pfl22;
    }

    public RichPanelFormLayout getPfl22() {
        return pfl22;
    }

    public void setSoc_departamento(RichSelectOneChoice selectOneChoice4) {
        this.soc_departamento = selectOneChoice4;
    }

    public RichSelectOneChoice getSoc_departamento() {
        return soc_departamento;
    }

    public void setSelectItems4(UISelectItems selectItems4) {
        this.selectItems4 = selectItems4;
    }

    public UISelectItems getSelectItems4() {
        return selectItems4;
    }

    public void setSoc_municipio(RichSelectOneChoice selectOneChoice5) {
        this.soc_municipio = selectOneChoice5;
    }

    public RichSelectOneChoice getSoc_municipio() {
        return soc_municipio;
    }

    public void setSelectItems5(UISelectItems selectItems5) {
        this.selectItems5 = selectItems5;
    }

    public UISelectItems getSelectItems5() {
        return selectItems5;
    }


    public void setListaDepartamentos(List listaDepartamentos) {
        this.listaDepartamentos = listaDepartamentos;
    }

    public List getListaDepartamentos() {
        return listaDepartamentos;
    }

    public void setListaMunicipios(List listaMunicipios) {
        this.listaMunicipios = listaMunicipios;
    }

    public List getListaMunicipios() {
        return listaMunicipios;
    }

    public void setConflicto(FnttConflicto conflicto) {
        this.conflicto = conflicto;
    }

    public FnttConflicto getConflicto() {
        return conflicto;
    }

    public void setSistemaRefAct(Lista sistemaRefAct) {
        this.sistemaRefAct = sistemaRefAct;
    }

    public Lista getSistemaRefAct() {
        return sistemaRefAct;
    }

    public void setTramoAct(FnttTramo tramoAct) {
        this.tramoAct = tramoAct;
    }

    public FnttTramo getTramoAct() {
        return tramoAct;
    }

    public void setDeptoAct(Divipola deptoAct) {
        this.deptoAct = deptoAct;
    }

    public Divipola getDeptoAct() {
        return deptoAct;
    }

    public void setMunicAct(Divipola municAct) {
        this.municAct = municAct;
    }

    public Divipola getMunicAct() {
        return municAct;
    }

    public void setEstadoAbiertoAct(Boolean estadoAbiertoAct) {
        this.estadoAbiertoAct = estadoAbiertoAct;
    }

    public Boolean getEstadoAbiertoAct() {
        return estadoAbiertoAct;
    }

    public void setEstadoCerradoAct(Boolean estadoCerradoAct) {
        this.estadoCerradoAct = estadoCerradoAct;
    }

    public Boolean getEstadoCerradoAct() {
        return estadoCerradoAct;
    }

    public void setCommandLink1(RichCommandLink commandLink1) {
        this.commandLink1 = commandLink1;
    }

    public RichCommandLink getCommandLink1() {
        return commandLink1;
    }


    public void setPanelGroupLayout6(RichPanelGroupLayout panelGroupLayout6) {
        this.panelGroupLayout6 = panelGroupLayout6;
    }

    public RichPanelGroupLayout getPanelGroupLayout6() {
        return panelGroupLayout6;
    }

    public void setPanelGroupLayout7(RichPanelGroupLayout panelGroupLayout7) {
        this.panelGroupLayout7 = panelGroupLayout7;
    }

    public RichPanelGroupLayout getPanelGroupLayout7() {
        return panelGroupLayout7;
    }


    public void setSpacer5(RichSpacer spacer5) {
        this.spacer5 = spacer5;
    }

    public RichSpacer getSpacer5() {
        return spacer5;
    }


    public void setSpacer8(RichSpacer spacer8) {
        this.spacer8 = spacer8;
    }

    public RichSpacer getSpacer8() {
        return spacer8;
    }


    public void setSpacer9(RichSpacer spacer9) {
        this.spacer9 = spacer9;
    }

    public RichSpacer getSpacer9() {
        return spacer9;
    }

    public void setPanelGroupLayout8(RichPanelGroupLayout panelGroupLayout8) {
        this.panelGroupLayout8 = panelGroupLayout8;
    }

    public RichPanelGroupLayout getPanelGroupLayout8() {
        return panelGroupLayout8;
    }

    public void setMenuBar1(RichMenuBar menuBar1) {
        this.menuBar1 = menuBar1;
    }

    public RichMenuBar getMenuBar1() {
        return menuBar1;
    }

    public void setMenu3(RichMenu menu3) {
        this.menu3 = menu3;
    }

    public RichMenu getMenu3() {
        return menu3;
    }

    public void setCommandMenuItem11(RichCommandMenuItem commandMenuItem11) {
        this.commandMenuItem11 = commandMenuItem11;
    }

    public RichCommandMenuItem getCommandMenuItem11() {
        return commandMenuItem11;
    }

    public void setCommandMenuItem2(RichCommandMenuItem commandMenuItem2) {
        this.commandMenuItem2 = commandMenuItem2;
    }

    public RichCommandMenuItem getCommandMenuItem2() {
        return commandMenuItem2;
    }

    public void setCommandMenuItem3(RichCommandMenuItem commandMenuItem3) {
        this.commandMenuItem3 = commandMenuItem3;
    }

    public RichCommandMenuItem getCommandMenuItem3() {
        return commandMenuItem3;
    }

    public void setCommandMenuItem4(RichCommandMenuItem commandMenuItem4) {
        this.commandMenuItem4 = commandMenuItem4;
    }

    public RichCommandMenuItem getCommandMenuItem4() {
        return commandMenuItem4;
    }

    public void setCommandMenuItem5(RichCommandMenuItem commandMenuItem5) {
        this.commandMenuItem5 = commandMenuItem5;
    }

    public RichCommandMenuItem getCommandMenuItem5() {
        return commandMenuItem5;
    }


    public void setOutputText1(RichOutputText outputText1) {
        this.outputText1 = outputText1;
    }

    public RichOutputText getOutputText1() {
        return outputText1;
    }


}
