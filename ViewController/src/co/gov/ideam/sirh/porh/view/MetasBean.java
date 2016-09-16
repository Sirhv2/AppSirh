package co.gov.ideam.sirh.porh.view;

import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.fuentes.model.FnttTramo;
import co.gov.ideam.sirh.fuentes.view.FuenteDelegate;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Categoria;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.view.ParametrosDelegate;
import co.gov.ideam.sirh.porh.model.PorhAvanceMetas;
import co.gov.ideam.sirh.porh.model.PorhMetas;
import co.gov.ideam.sirh.porh.model.PorhParametrosTO;
import co.gov.ideam.sirh.porh.model.PorhPlanes;
import co.gov.ideam.sirh.porh.model.PorvMetas;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.usuarios.agua.view.UsuariosAguaDelegate;
import co.gov.ideam.sirh.util.Constantes;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;

import java.math.BigDecimal;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;

import javax.faces.component.UISelectItems;

import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
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
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.adf.view.rich.event.DialogEvent;

import org.apache.myfaces.trinidad.event.SelectionEvent;

public class MetasBean extends StandarBean {
    private RichForm f2;
    private RichDocument d2;
    private RichPanelStretchLayout psl1;
    private RichPanelGroupLayout pgl2;
    private RichSpacer s1;
    private RichCommandLink cl4;
    private RichSpacer s2;
    private RichCommandLink cl5;

    private FnttTramo tramo;
    private String nombreFuente;
    private String nombreTramo;
    private PorhPlanes planOrdenamiento;
    private PorvMetas meta;
    private List listaParametros;
    private List listaUnidades;
    private RichPanelSplitter ps1;
    private RichPanelBox pb_metas;
    private RichPanelStretchLayout psl2;
    private RichPanelCollection pc1;
    private List<PorvMetas> listaMetas;
    private List<PorhAvanceMetas> listaAvances;
    private PorhAvanceMetas avanceSeleccionado;

    private RichTable t_metas;
    private RichPanelFormLayout pfl1;
    private RichSelectOneChoice soc_parametro;
    private UISelectItems si1;
    private RichSelectOneChoice soc_unidad;
    private UISelectItems si2;
    private RichInputText it_valor_actual;
    private RichInputText it_valor_meta;
    private RichPanelGroupLayout pgl1;
    private RichPanelGroupLayout pgl3;
    private RichCommandButton cb_adicionar;
    private RichInputDate id_fecha;
    private RichSpacer s5;
    private RichPanelBox pb_avance;
    private RichPanelStretchLayout psl_derecho;
    private RichPanelFormLayout pfl_avance;
    private RichInputDate id_fecha_avance;
    private RichInputText it_valor_avance;
    private RichSpacer s4;
    private RichOutputText ot_unidad_avance;
    private RichPanelGroupLayout pgl4;
    private RichPanelGroupLayout pgl5;
    private RichCommandButton cb_adiconar_avance;
    private RichSpacer s6;
    private RichPanelCollection pc2;
    private RichTable t_avance;
    private RichSpacer s3;
    private RichCommandButton cb_borrar_avance;
    private RichPanelGroupLayout pgl6;
    private RichPopup p_borrar_avance;
    private RichDialog d_borrar_avance;
    private RichOutputText ot8;
    private RichPopup p_borrar_meta;
    private RichDialog d_borrar_meta;
    private RichOutputText ot9;
    private RichSpacer s7;
    private RichCommandButton cb_borrar_meta;
    private RichPanelGroupLayout panelGroupLayout1;
    private RichPanelFormLayout panelFormLayout1;
    private RichSelectOneChoice soc_parametro1;
    private UISelectItems si11;
    private RichInputText it_valor_actual1;
    private RichInputDate id_fecha1;
    private RichSelectOneChoice soc_unidad1;
    private UISelectItems si21;
    private RichInputText it_valor_meta1;
    private RichSpacer s51;
    private RichPanelGroupLayout pgl11;
    private RichPanelGroupLayout pgl31;
    private RichCommandButton cb_adicionar1;
    private RichSpacer s71;
    private RichCommandButton cb_borrar_meta1;
    private RichInputText tactAdm;
    private RichInputDate dvigIni;
    private RichInputDate dfecExp;
    private RichInputDate dvigFin;
    private RichCommandButton bsalvar;

    public MetasBean() {
        FacesUtils.setActiveBean("metas", "metas", PorhDelegate.class);
        this.load();
    }

    public void load() {
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

        setTramo((FnttTramo)FacesUtils.getFromSession("tramoSeleccionado"));
        FnttFuente fuente =
            (FnttFuente)FacesUtils.getFromSession("fuenteSeleccionada");

        setNombreFuente(fuente.getNombre());
        setNombreTramo(getTramo().getNombre());

        setPlanOrdenamiento((PorhPlanes)FacesUtils.getFromSession("planSeleccionado"));

        try {
            PorhDelegate pd = PorhDelegate.getInstance();
            setListaParametros(pd.getParametros(this.getTramo()));
            listaParametros = this.cargarParametro(Categoria.PARAMETROS);
            setListaUnidades(this.cargarParametro(Categoria.UNIDADES));

            listaMetas = pd.getAllMetas(this.tramo);
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

    public void setPsl1(RichPanelStretchLayout psl1) {
        this.psl1 = psl1;
    }

    public RichPanelStretchLayout getPsl1() {
        return psl1;
    }

    public void setPgl2(RichPanelGroupLayout pgl2) {
        this.pgl2 = pgl2;
    }

    public RichPanelGroupLayout getPgl2() {
        return pgl2;
    }

    public void setS1(RichSpacer s1) {
        this.s1 = s1;
    }

    public RichSpacer getS1() {
        return s1;
    }

    public void setCl4(RichCommandLink cl4) {
        this.cl4 = cl4;
    }

    public RichCommandLink getCl4() {
        return cl4;
    }

    public void setS2(RichSpacer s2) {
        this.s2 = s2;
    }

    public RichSpacer getS2() {
        return s2;
    }

    public void setCl5(RichCommandLink cl5) {
        this.cl5 = cl5;
    }

    public RichCommandLink getCl5() {
        return cl5;
    }


    public FnttTramo getTramo() {
        return tramo;
    }

    public void setTramo(FnttTramo tramo) {
        this.tramo = tramo;
    }

    public String getNombreFuente() {
        return nombreFuente;
    }

    public void setNombreFuente(String nombreFuente) {
        this.nombreFuente = nombreFuente;
    }

    public String getNombreTramo() {
        return nombreTramo;
    }

    public void setNombreTramo(String nombreTramo) {
        this.nombreTramo = nombreTramo;
    }

    public PorhPlanes getPlanOrdenamiento() {
        return planOrdenamiento;
    }

    public void setPlanOrdenamiento(PorhPlanes planOrdenamiento) {
        this.planOrdenamiento = planOrdenamiento;
    }

    public List<PorhParametrosTO> getListaParametros() {
        return listaParametros;
    }

    public void setListaParametros(List<PorhParametrosTO> listaParametros) {
        this.listaParametros = listaParametros;
    }

    public void setPs1(RichPanelSplitter ps1) {
        this.ps1 = ps1;
    }

    public RichPanelSplitter getPs1() {
        return ps1;
    }

    public void setPb_metas(RichPanelBox pb1) {
        this.pb_metas = pb1;
    }

    public RichPanelBox getPb_metas() {
        return pb_metas;
    }

    public void setPsl2(RichPanelStretchLayout psl2) {
        this.psl2 = psl2;
    }

    public RichPanelStretchLayout getPsl2() {
        return psl2;
    }

    public void setPc1(RichPanelCollection pc1) {
        this.pc1 = pc1;
    }

    public RichPanelCollection getPc1() {
        return pc1;
    }

    public List<PorvMetas> getListaMetas() {
        return listaMetas;
    }

    public void setListaMetas(List<PorvMetas> listaMetas) {
        this.listaMetas = listaMetas;
    }

    public void setT_metas(RichTable t1) {
        this.t_metas = t1;
    }

    public RichTable getT_metas() {
        return t_metas;
    }

    public void setPfl1(RichPanelFormLayout pfl1) {
        this.pfl1 = pfl1;
    }

    public RichPanelFormLayout getPfl1() {
        return pfl1;
    }

    public List getListaUnidades() {
        return listaUnidades;
    }

    public void setListaUnidades(List listaUnidades) {
        this.listaUnidades = listaUnidades;
    }

    public void setSoc_parametro(RichSelectOneChoice soc1) {
        this.soc_parametro = soc1;
    }

    public RichSelectOneChoice getSoc_parametro() {
        return soc_parametro;
    }

    public void setSi1(UISelectItems si1) {
        this.si1 = si1;
    }

    public UISelectItems getSi1() {
        return si1;
    }

    public void setSoc_unidad(RichSelectOneChoice soc2) {
        this.soc_unidad = soc2;
    }

    public RichSelectOneChoice getSoc_unidad() {
        return soc_unidad;
    }

    public void setSi2(UISelectItems si2) {
        this.si2 = si2;
    }

    public UISelectItems getSi2() {
        return si2;
    }

    public void setIt_valor_actual(RichInputText it1) {
        this.it_valor_actual = it1;
    }

    public RichInputText getIt_valor_actual() {
        return it_valor_actual;
    }

    public void setIt_valor_meta(RichInputText it2) {
        this.it_valor_meta = it2;
    }

    public RichInputText getIt_valor_meta() {
        return it_valor_meta;
    }

    public void setPgl1(RichPanelGroupLayout pgl1) {
        this.pgl1 = pgl1;
    }

    public RichPanelGroupLayout getPgl1() {
        return pgl1;
    }


    public void setPgl3(RichPanelGroupLayout pgl3) {
        this.pgl3 = pgl3;
    }

    public RichPanelGroupLayout getPgl3() {
        return pgl3;
    }

    public void setCb_adicionar(RichCommandButton cb1) {
        this.cb_adicionar = cb1;
    }

    public RichCommandButton getCb_adicionar() {
        return cb_adicionar;
    }


    public void setId_fecha(RichInputDate id1) {
        this.id_fecha = id1;
    }

    public RichInputDate getId_fecha() {
        return id_fecha;
    }

    public void setS5(RichSpacer s5) {
        this.s5 = s5;
    }

    public RichSpacer getS5() {
        return s5;
    }

    public void cb_adicionarAct_actionListener(ActionEvent actionEvent) {
        try {
            if (tactAdm.getValue() == null) {
                showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                            tactAdm);
                return;
            }

            if (dfecExp.getValue() == null) {
                showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                            dfecExp);
                return;
            }

            if (dvigIni.getValue() == null) {
                showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                            dvigIni);
                return;
            }

            if (dvigFin.getValue() == null) {
                showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                            dvigFin);
                return;
            }

            UsuarioConectadoTO usuarioConectado =
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

            Autoridades autoridadAmbiental =
                usuarioConectado.getUsuario().getAutoridadAmbiental();

            setTramo((FnttTramo)FacesUtils.getFromSession("tramoSeleccionado"));

            FuenteDelegate fd = FuenteDelegate.getInstance();
            
            tramo.setActoAdm(tactAdm.getValue().toString());
            tramo.setFechaExp((Date)dfecExp.getValue());
            tramo.setVigenciaIni((Date)dvigIni.getValue());
            tramo.setVigenciaFin((Date)dvigFin.getValue());
            
            fd.updateTramo(tramo);
            
            FacesUtils.setInSession("tramoSeleccionado",tramo );

            String params[] = { "de la meta global" };
            showMessage(getText("mensaje_registro_exitoso", params),
                        FacesMessage.SEVERITY_INFO);


        } catch (IdeamException e) {
            showMessage(e);
        }
    }

    public void cb_adicionar_actionListener(ActionEvent actionEvent) {
        boolean errores = false;
        if (soc_parametro.getValue() == null) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        soc_parametro);
            errores = true;
        }
        if (soc_unidad.getValue() == null) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        soc_unidad);
            errores = true;
        }
        if (it_valor_actual.getValue() == null ||
            it_valor_actual.getValue().toString().length() == 0) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        it_valor_actual);
            errores = true;
        }
        if (it_valor_meta.getValue() == null ||
            it_valor_meta.getValue().toString().length() == 0) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        it_valor_meta);
            errores = true;
        }
        if (id_fecha.getValue() == null) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR, id_fecha);
            errores = true;
        }
        if (errores) {
            return;
        }
        Lista parametro = (Lista)soc_parametro.getValue();
        try {
            UsuarioConectadoTO usuarioConectado =
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

            Autoridades autoridadAmbiental =
                usuarioConectado.getUsuario().getAutoridadAmbiental();
            PorhDelegate pd = PorhDelegate.getInstance();

            PorhMetas metaEdicion = null;
            if (this.getMeta() == null) {
                // Verificar si el parametro ya e encuentra relacionado con el tramo
                if (listaMetas != null) {
                    Iterator<PorvMetas> it = listaMetas.iterator();
                    while (it.hasNext()) {
                        PorvMetas m = it.next();
                        if (m.getCodigoParametro().longValue() ==
                            parametro.getCodigo().longValue()) {
                            showMessage("PARAMETRO_YA_ESTA_RELACIONADO",
                                        FacesMessage.SEVERITY_ERROR,
                                        soc_parametro);
                            return;
                        }
                    }
                }

                metaEdicion = new PorhMetas();
                metaEdicion.setCodigoAutoridad(autoridadAmbiental.getId().longValue());
                metaEdicion.setIdParametro(parametro.getCodigo().longValue());
                metaEdicion.setIdPlan(this.planOrdenamiento == null ? null :
                                      this.planOrdenamiento.getCodigo());
                metaEdicion.setIdTramo(this.tramo.getId());
            } else {
                metaEdicion = pd.getMeta(this.getMeta().getId());
                if (metaEdicion == null) {
                    showMessage("Error consultando Meta con ID " +
                                getMeta().getId(),
                                FacesMessage.SEVERITY_ERROR);
                    return;
                }
            }
            metaEdicion.setFecha((Date)id_fecha.getValue());
            Lista unidad = (Lista)soc_unidad.getValue();
            metaEdicion.setIdUnidad(unidad.getCodigo().longValue());

            metaEdicion.setValorActual(((BigDecimal)it_valor_actual.getValue()).doubleValue());
            metaEdicion.setValorMeta(((BigDecimal)it_valor_meta.getValue()).doubleValue());

            pd.updateMeta(metaEdicion);

            String params[] = { "de la Meta" };
            showMessage(getText("mensaje_registro_exitoso", params),
                        FacesMessage.SEVERITY_INFO);

            listaMetas = pd.getAllMetas(this.tramo);
            soc_parametro.setValue(null);
            soc_unidad.setValue(null);
            it_valor_actual.setValue(null);
            it_valor_meta.setValue(null);
            id_fecha.setValue(null);
            soc_parametro.setDisabled(false);
            cb_adicionar.setText("Adicionar");
            t_metas.setSelectedRowKeys(null);
            this.meta = null;
            AdfFacesContext.getCurrentInstance().addPartialTarget(pb_metas);
        } catch (IdeamException e) {
            showMessage(e);
        }
    }

    public void t_metas_selectionListener(SelectionEvent selectionEvent) {
        this.setMeta((PorvMetas)t_metas.getSelectedRowData());
        this.listaAvances = new ArrayList();
        if (getMeta() != null) {
            Lista p = new Lista();
            p.setCodigo(getMeta().getCodigoParametro().intValue());

            Lista u = new Lista();
            u.setCodigo(getMeta().getCodigoUnidad().intValue());

            soc_parametro.setValue(p);
            soc_unidad.setValue(u);

            it_valor_actual.setValue(getMeta().getValorActual());
            it_valor_meta.setValue(getMeta().getValorMeta());
            id_fecha.setValue(getMeta().getFecha());
            cb_adicionar.setText("Actualizar");
            soc_parametro.setDisabled(true);
            try {
                PorhDelegate pd = PorhDelegate.getInstance();
                listaAvances = pd.getAvanceMetas(this.getMeta().getId());

                ParametrosDelegate pad = ParametrosDelegate.getInstance();
                Lista _p =
                    pad.getLista(getMeta().getCodigoParametro().intValue());

                String titulo = getText("AVANCE_EN_METAS_DEL_PAR_U00E1M");
                titulo += " " + _p.getValor();
                pb_avance.setText(titulo);

                Lista _u =
                    pad.getLista(getMeta().getCodigoUnidad().intValue());
                ot_unidad_avance.setValue(_u.getValor());
            } catch (IdeamException e) {
                showMessage(e);
            }
        } else {
            soc_parametro.setValue(null);
            soc_unidad.setValue(null);
            it_valor_actual.setValue(null);
            it_valor_meta.setValue(null);
            id_fecha.setValue(null);
            soc_parametro.setDisabled(false);
            cb_adicionar.setText("Adicionar");
            t_avance.setSelectedRowKeys(null);
            ot_unidad_avance.setValue("");
        }

        id_fecha_avance.setValue(null);
        it_valor_avance.setValue(null);
        AdfFacesContext.getCurrentInstance().addPartialTarget(pb_metas);
        AdfFacesContext.getCurrentInstance().addPartialTarget(pb_avance);
    }

    public void setPb_avance(RichPanelBox pb1) {
        this.pb_avance = pb1;
    }

    public RichPanelBox getPb_avance() {
        return pb_avance;
    }

    public void setPsl_derecho(RichPanelStretchLayout psl3) {
        this.psl_derecho = psl3;
    }

    public RichPanelStretchLayout getPsl_derecho() {
        return psl_derecho;
    }

    public void setPfl_avance(RichPanelFormLayout pfl2) {
        this.pfl_avance = pfl2;
    }

    public RichPanelFormLayout getPfl_avance() {
        return pfl_avance;
    }

    public void setId_fecha_avance(RichInputDate id1) {
        this.id_fecha_avance = id1;
    }

    public RichInputDate getId_fecha_avance() {
        return id_fecha_avance;
    }

    public void setIt_valor_avance(RichInputText it1) {
        this.it_valor_avance = it1;
    }

    public RichInputText getIt_valor_avance() {
        return it_valor_avance;
    }

    public void setS4(RichSpacer s4) {
        this.s4 = s4;
    }

    public RichSpacer getS4() {
        return s4;
    }

    public void setOt_unidad_avance(RichOutputText ot5) {
        this.ot_unidad_avance = ot5;
    }

    public RichOutputText getOt_unidad_avance() {
        return ot_unidad_avance;
    }

    public void setPgl4(RichPanelGroupLayout pgl4) {
        this.pgl4 = pgl4;
    }

    public RichPanelGroupLayout getPgl4() {
        return pgl4;
    }


    public void setPgl5(RichPanelGroupLayout pgl5) {
        this.pgl5 = pgl5;
    }

    public RichPanelGroupLayout getPgl5() {
        return pgl5;
    }

    public void setCb_adiconar_avance(RichCommandButton cb1) {
        this.cb_adiconar_avance = cb1;
    }

    public RichCommandButton getCb_adiconar_avance() {
        return cb_adiconar_avance;
    }

    public void setS6(RichSpacer s6) {
        this.s6 = s6;
    }

    public RichSpacer getS6() {
        return s6;
    }

    public List<PorhAvanceMetas> getListaAvances() {
        return listaAvances;
    }

    public void setListaAvances(List<PorhAvanceMetas> listaAvances) {
        this.listaAvances = listaAvances;
    }

    public void setPc2(RichPanelCollection pc2) {
        this.pc2 = pc2;
    }

    public RichPanelCollection getPc2() {
        return pc2;
    }

    public void setT_avance(RichTable t1) {
        this.t_avance = t1;
    }

    public RichTable getT_avance() {
        return t_avance;
    }

    public void cb_adiconar_avance_actionListener(ActionEvent actionEvent) {
        boolean errores = false;
        if (id_fecha_avance.getValue() == null) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        id_fecha_avance);
            errores = true;
        } else {
            // Validar que la fecha sea menor a la fecha del sistema
            Calendar hoy = GregorianCalendar.getInstance();
            Date fecha = (Date)id_fecha_avance.getValue();
            if (fecha.getTime() > hoy.getTime().getTime()) {
                showMessage("FECHA_MENOR_HOY", FacesMessage.SEVERITY_ERROR,
                            id_fecha_avance);
                errores = true;
            }
        }
        if (it_valor_avance.getValue() == null ||
            it_valor_avance.getValue().toString().length() == 0) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        it_valor_avance);
            errores = true;
        }
        if (errores) {
            return;
        }
        try {
            PorhDelegate pd = PorhDelegate.getInstance();

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

            PorhAvanceMetas avance = null;
            if (this.getAvanceSeleccionado() == null) {
                avance = new PorhAvanceMetas();
                avance.setIdMeta(this.meta.getId());
                avance.setCodigoAutoridad(autoridadAmbiental.getId().longValue());
            } else {
                avance = this.getAvanceSeleccionado();
            }

            avance.setFecha_muestra((Date)id_fecha_avance.getValue());
            avance.setValor(((BigDecimal)it_valor_avance.getValue()).doubleValue());

            Iterator<PorhAvanceMetas> it = this.listaAvances.iterator();
            while (it.hasNext()) {
                PorhAvanceMetas m = it.next();
                if (m.getId() != null && m.getId().longValue() > 0 &&
                    avance.getId() == null &&
                    m.getFecha_muestra().getTime() == avance.getFecha_muestra().getTime() &&
                    m.getValor().doubleValue() ==
                    avance.getValor().doubleValue()) {
                    showMessage("Ya existe una muestra para esta fecha y valor",
                                FacesMessage.SEVERITY_ERROR);
                    return;
                }
            }

            pd.updateAvanceMeta(avance);
            this.listaAvances = pd.getAvanceMetas(this.meta.getId());

            String params[] = { "del Avance" };
            showMessage(getText("mensaje_registro_exitoso", params),
                        FacesMessage.SEVERITY_INFO);

            this.setAvanceSeleccionado(null);
            id_fecha_avance.setValue(null);
            it_valor_avance.setValue(null);
            cb_adiconar_avance.setText("Adicionar");
            t_avance.setSelectedRowKeys(null);
            AdfFacesContext.getCurrentInstance().addPartialTarget(pb_avance);
        } catch (IdeamException e) {
            showMessage(e);
        }
    }

    public PorvMetas getMeta() {
        return meta;
    }

    public void setMeta(PorvMetas meta) {
        this.meta = meta;
    }

    public void t_avance_selectionListener(SelectionEvent selectionEvent) {
        setAvanceSeleccionado((PorhAvanceMetas)t_avance.getSelectedRowData());
        if (getAvanceSeleccionado() != null) {
            id_fecha_avance.setValue(getAvanceSeleccionado().getFecha_muestra());
            it_valor_avance.setValue(getAvanceSeleccionado().getValor());

            try {
                ParametrosDelegate pad = ParametrosDelegate.getInstance();
                Lista _u =
                    pad.getLista(getMeta().getCodigoUnidad().intValue());
                ot_unidad_avance.setValue(_u.getValor());
            } catch (IdeamException e) {
                showMessage(e);
            }
            cb_adiconar_avance.setText("Actualizar");
        } else {
            id_fecha_avance.setValue(null);
            it_valor_avance.setValue(null);
            ot_unidad_avance.setValue("");
            cb_adiconar_avance.setText("Adicionar");
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(pfl_avance);
    }

    public void setS3(RichSpacer s3) {
        this.s3 = s3;
    }

    public RichSpacer getS3() {
        return s3;
    }

    public void setCb_borrar_avance(RichCommandButton cb1) {
        this.cb_borrar_avance = cb1;
    }

    public RichCommandButton getCb_borrar_avance() {
        return cb_borrar_avance;
    }

    public void setPgl6(RichPanelGroupLayout pgl6) {
        this.pgl6 = pgl6;
    }

    public RichPanelGroupLayout getPgl6() {
        return pgl6;
    }

    public void setP_borrar_avance(RichPopup p1) {
        this.p_borrar_avance = p1;
    }

    public RichPopup getP_borrar_avance() {
        return p_borrar_avance;
    }

    public void setD_borrar_avance(RichDialog d1) {
        this.d_borrar_avance = d1;
    }

    public RichDialog getD_borrar_avance() {
        return d_borrar_avance;
    }

    public void setOt8(RichOutputText ot8) {
        this.ot8 = ot8;
    }

    public RichOutputText getOt8() {
        return ot8;
    }

    public void setP_borrar_meta(RichPopup p1) {
        this.p_borrar_meta = p1;
    }

    public RichPopup getP_borrar_meta() {
        return p_borrar_meta;
    }

    public void setD_borrar_meta(RichDialog d1) {
        this.d_borrar_meta = d1;
    }

    public RichDialog getD_borrar_meta() {
        return d_borrar_meta;
    }

    public void setOt9(RichOutputText ot9) {
        this.ot9 = ot9;
    }

    public RichOutputText getOt9() {
        return ot9;
    }

    public PorhAvanceMetas getAvanceSeleccionado() {
        return avanceSeleccionado;
    }

    public void setAvanceSeleccionado(PorhAvanceMetas avanceSeleccionado) {
        this.avanceSeleccionado = avanceSeleccionado;
    }

    public void cb_borrar_avance_actionListener(ActionEvent actionEvent) {
        showPopup(p_borrar_avance, true);
    }

    public void setS7(RichSpacer s7) {
        this.s7 = s7;
    }

    public RichSpacer getS7() {
        return s7;
    }

    public void setCb_borrar_meta(RichCommandButton cb1) {
        this.cb_borrar_meta = cb1;
    }

    public RichCommandButton getCb_borrar_meta() {
        return cb_borrar_meta;
    }

    public void cb_borrar_meta_actionListener(ActionEvent actionEvent) {
        showPopup(p_borrar_meta, true);
    }

    public void d_borrar_avance_dialogListener(DialogEvent dialogEvent) {
        try {
            PorhDelegate pd = PorhDelegate.getInstance();
            pd.deleteAvanceMeta(this.avanceSeleccionado);

            this.listaAvances = pd.getAvanceMetas(this.meta.getId());

            String params[] = { "del Avance" };
            showMessage(getText("mensaje_registro_exitoso", params),
                        FacesMessage.SEVERITY_INFO);

            this.setAvanceSeleccionado(null);
            id_fecha_avance.setValue(null);
            it_valor_avance.setValue(null);
            cb_adiconar_avance.setText("Adicionar");
            t_avance.setSelectedRowKeys(null);
            AdfFacesContext.getCurrentInstance().addPartialTarget(pb_avance);

        } catch (IdeamException e) {
            showMessage(e);
        }
    }

    public void d_borrar_meta_dialogListener(DialogEvent dialogEvent) {
        try {
            PorhDelegate pd = PorhDelegate.getInstance();
            pd.deleteMeta(this.meta);

            String params[] = { "de la Meta" };
            showMessage(getText("mensaje_registro_exitoso", params),
                        FacesMessage.SEVERITY_INFO);

            listaMetas = pd.getAllMetas(this.tramo);
            this.listaAvances = pd.getAvanceMetas(this.meta.getId());

            soc_parametro.setValue(null);
            soc_unidad.setValue(null);
            it_valor_actual.setValue(null);
            it_valor_meta.setValue(null);
            id_fecha.setValue(null);
            soc_parametro.setDisabled(false);
            cb_adicionar.setText("Adicionar");
            t_metas.setSelectedRowKeys(null);
            this.meta = null;
            AdfFacesContext.getCurrentInstance().addPartialTarget(pb_metas);
            AdfFacesContext.getCurrentInstance().addPartialTarget(psl_derecho);
        } catch (IdeamException e) {
            showMessage(e);
        }
    }

    public void setPanelGroupLayout1(RichPanelGroupLayout panelGroupLayout1) {
        this.panelGroupLayout1 = panelGroupLayout1;
    }

    public RichPanelGroupLayout getPanelGroupLayout1() {
        return panelGroupLayout1;
    }

    public void setPanelFormLayout1(RichPanelFormLayout panelFormLayout1) {
        this.panelFormLayout1 = panelFormLayout1;
    }

    public RichPanelFormLayout getPanelFormLayout1() {
        return panelFormLayout1;
    }

    public void setSoc_parametro1(RichSelectOneChoice soc_parametro1) {
        this.soc_parametro1 = soc_parametro1;
    }

    public RichSelectOneChoice getSoc_parametro1() {
        return soc_parametro1;
    }

    public void setSi11(UISelectItems si11) {
        this.si11 = si11;
    }

    public UISelectItems getSi11() {
        return si11;
    }

    public void setIt_valor_actual1(RichInputText it_valor_actual1) {
        this.it_valor_actual1 = it_valor_actual1;
    }

    public RichInputText getIt_valor_actual1() {
        return it_valor_actual1;
    }

    public void setId_fecha1(RichInputDate id_fecha1) {
        this.id_fecha1 = id_fecha1;
    }

    public RichInputDate getId_fecha1() {
        return id_fecha1;
    }

    public void setSoc_unidad1(RichSelectOneChoice soc_unidad1) {
        this.soc_unidad1 = soc_unidad1;
    }

    public RichSelectOneChoice getSoc_unidad1() {
        return soc_unidad1;
    }

    public void setSi21(UISelectItems si21) {
        this.si21 = si21;
    }

    public UISelectItems getSi21() {
        return si21;
    }

    public void setIt_valor_meta1(RichInputText it_valor_meta1) {
        this.it_valor_meta1 = it_valor_meta1;
    }

    public RichInputText getIt_valor_meta1() {
        return it_valor_meta1;
    }

    public void setS51(RichSpacer s51) {
        this.s51 = s51;
    }

    public RichSpacer getS51() {
        return s51;
    }

    public void setPgl11(RichPanelGroupLayout pgl11) {
        this.pgl11 = pgl11;
    }

    public RichPanelGroupLayout getPgl11() {
        return pgl11;
    }

    public void setPgl31(RichPanelGroupLayout pgl31) {
        this.pgl31 = pgl31;
    }

    public RichPanelGroupLayout getPgl31() {
        return pgl31;
    }

    public void setCb_adicionar1(RichCommandButton cb_adicionar1) {
        this.cb_adicionar1 = cb_adicionar1;
    }

    public RichCommandButton getCb_adicionar1() {
        return cb_adicionar1;
    }

    public void setS71(RichSpacer s71) {
        this.s71 = s71;
    }

    public RichSpacer getS71() {
        return s71;
    }

    public void setCb_borrar_meta1(RichCommandButton cb_borrar_meta1) {
        this.cb_borrar_meta1 = cb_borrar_meta1;
    }

    public RichCommandButton getCb_borrar_meta1() {
        return cb_borrar_meta1;
    }

    public void setTactAdm(RichInputText tactAdm) {
        this.tactAdm = tactAdm;
    }

    public RichInputText getTactAdm() {
        return tactAdm;
    }

    public void setDvigIni(RichInputDate dvigIni) {
        this.dvigIni = dvigIni;
    }

    public RichInputDate getDvigIni() {
        return dvigIni;
    }

    public void setDfecExp(RichInputDate dfecExp) {
        this.dfecExp = dfecExp;
    }

    public RichInputDate getDfecExp() {
        return dfecExp;
    }

    public void setDvigFin(RichInputDate dvigFin) {
        this.dvigFin = dvigFin;
    }

    public RichInputDate getDvigFin() {
        return dvigFin;
    }

    public void setBsalvar(RichCommandButton commandButton1) {
        this.bsalvar = commandButton1;
    }

    public RichCommandButton getBsalvar() {
        return bsalvar;
    }
}
