package co.gov.ideam.sirh.fuentes.view;

import co.gov.ideam.sirh.auditoria.model.Auditoria;
import co.gov.ideam.sirh.auditoria.view.AuditoriasDelegate;

import co.gov.ideam.sirh.calidad.model.PuntoMonitoreo;
import co.gov.ideam.sirh.calidad.view.CalidadDelegate;
import co.gov.ideam.sirh.fuentes.model.ActorConflicto;
import co.gov.ideam.sirh.fuentes.model.FnttActividadesEconomicas;
import co.gov.ideam.sirh.fuentes.model.FnttConflicto;
import co.gov.ideam.sirh.fuentes.model.FnttFuente;

import co.gov.ideam.sirh.fuentes.model.FnttLogros;
import co.gov.ideam.sirh.fuentes.model.FnttTramo;
import co.gov.ideam.sirh.fuentes.model.OrigenConflicto;
import co.gov.ideam.sirh.fuentes.model.PoblacionConflicto;
import co.gov.ideam.sirh.fuentes.model.TipoConflicto;
import co.gov.ideam.sirh.parametros.model.Categoria;
import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.parametros.model.Lista;

import co.gov.ideam.sirh.parametros.view.ParametrosDelegate;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;

import co.gov.ideam.sirh.usuarios.agua.model.PuntoVertimientoTratamiento;
import co.gov.ideam.sirh.util.ConstantesParametros;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import javax.faces.model.SelectItem;

import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.context.AdfFacesContext;

import org.apache.myfaces.trinidad.event.SelectionEvent;


public class ActividadLogroConflictoBean extends StandarBean {

    private static final String LOGRO_TIPO_CF = "CF";

    private FnttFuente fuente;
    private String accionAdicionar;
    private FnttConflicto conflicto;

    private List<FnttLogros> logrosList;
    private List<SelectItem> logrosSelectedItemList =
        new ArrayList<SelectItem>();
    private List<FnttLogros> selectedLogrosList = new ArrayList<FnttLogros>();

    private List<FnttActividadesEconomicas> actividadesEconomicasList;
    private List<SelectItem> actividadesEconomicasSelectedItemList =
        new ArrayList<SelectItem>();
    private List<FnttActividadesEconomicas> selectedActividadesEconomicasList =
        new ArrayList<FnttActividadesEconomicas>();


    private RichPopup p_registro_exitoso;
    private RichDialog d_registro_exitoso;


    public ActividadLogroConflictoBean() {
        FacesUtils.setActiveBean("actividadLogroConflictoBean",
                                 "Actividad Logro Conflicto",
                                 ActividadLogroConflictoBean.class);
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

            FuenteDelegate fd = FuenteDelegate.getInstance();

            conflicto = fd.initializeFnttConflicto(conflicto);

            this.logrosList = fd.getLogrosByTipo(LOGRO_TIPO_CF);

            for (FnttLogros f : logrosList) {
                this.logrosSelectedItemList.add(new SelectItem(f,
                                                               f.getLogro()));
                for (FnttLogros f2 : this.conflicto.getFnttLogrosList()) {
                    if (f2.getIdlogro().longValue() ==
                        f.getIdlogro().longValue()) {
                        this.selectedLogrosList.add(f);
                    }
                }
            }

            this.actividadesEconomicasList = fd.getActividadesEconomicas();

            for (FnttActividadesEconomicas f : actividadesEconomicasList) {
                this.actividadesEconomicasSelectedItemList.add(new SelectItem(f,
                                                                              f.getActividad()));
                for (FnttActividadesEconomicas f2 :
                     this.conflicto.getFnttActividadesEconomicasList()) {
                    if (f2.getIdactividad().longValue() ==
                        f.getIdactividad().longValue()) {
                        this.selectedActividadesEconomicasList.add(f);
                    }
                }
            }

        } catch (IdeamException e) {
            showMessage(e);
        }
    }


    public void cbNext1_actionListener(ActionEvent actionEvent) throws IdeamException {
        try {

            accionAdicionar = "";

            if (validarForma()) {
                guardar();
            }

        } catch (IdeamException e) {
            showMessage(e);
        } catch (Exception e) {
            showMessage(e.getMessage());
        }
    }

    public boolean validarForma() throws Exception {

        if (this.selectedActividadesEconomicasList.isEmpty()) {
            showMessage("Asegurese de haber diligenciado todas las pestañas.",
                        FacesMessage.SEVERITY_WARN);
            showMessage("* Debe seleccionar al menos un logro");
            return false;
        }
        for (FnttActividadesEconomicas e :
             this.selectedActividadesEconomicasList) {
            if (e.getIdactividad().longValue() > 9990L &&
                (this.conflicto.getActividadEconomicaOtro() == null ||
                 this.conflicto.getActividadEconomicaOtro().isEmpty())) {
                showMessage("Asegurese de haber diligenciado todas las pestañas.",
                            FacesMessage.SEVERITY_WARN);
                showMessage("* Debe ingresar OTRO Actividad económica");
                return false;
            }
        }


        if (this.selectedLogrosList.isEmpty()) {
            showMessage("Asegurese de haber diligenciado todas las pestañas.",
                        FacesMessage.SEVERITY_WARN);
            showMessage("* Debe seleccionar al menos un logro");
            return false;
        }
        for (FnttLogros e :
             this.selectedLogrosList) {
            if (e.getIdlogro().longValue() > 9990L &&
                (this.conflicto.getLogroOtro() == null ||
                 this.conflicto.getLogroOtro().isEmpty())) {
                showMessage("Asegurese de haber diligenciado todas las pestañas.",
                            FacesMessage.SEVERITY_WARN);
                showMessage("* Debe ingresar OTRO Actividad económica");
                return false;
            }
        }

        return true;
    }


    public void guardar() {
        try {

            this.fuente =
                    (FnttFuente)FacesUtils.getFromSession("fuenteSeleccionada");

            UsuarioConectadoTO usuarioConectado =
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

            FuenteDelegate fd = FuenteDelegate.getInstance();

            this.conflicto.setFnttLogrosList(new HashSet<FnttLogros>(this.selectedLogrosList));
            this.conflicto.setFnttActividadesEconomicasList(new HashSet<FnttActividadesEconomicas>(this.selectedActividadesEconomicasList));


            conflicto = fd.updateConflicto(conflicto);
            guardarAuditoria("GUARDAR", "CONFLICTO", getClass().getName(),
                             conflicto.getId());


            showPopup(p_registro_exitoso, true);

        } catch (IdeamException e) {
            showMessage(e);
        }
    }


    public void cb_aceptar_actionListener(ActionEvent actionEvent) {
        accionAdicionar = "actoresConflicto";
    }

    public void lk_todosConflictos_actionListener(ActionEvent actionEvent) {
        accionAdicionar = "listarConflictos";
    }

    public String getAceptarAction() {
        return accionAdicionar;
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

    public void setConflicto(FnttConflicto conflicto) {
        this.conflicto = conflicto;
    }

    public FnttConflicto getConflicto() {
        return conflicto;
    }

    public void setLogrosSelectedItemList(List<SelectItem> logrosSelectedItemList) {
        this.logrosSelectedItemList = logrosSelectedItemList;
    }

    public List<SelectItem> getLogrosSelectedItemList() {
        return logrosSelectedItemList;
    }

    public void setSelectedLogrosList(List<FnttLogros> selectedLogrosList) {
        this.selectedLogrosList = selectedLogrosList;
    }

    public List<FnttLogros> getSelectedLogrosList() {
        return selectedLogrosList;
    }


    public void setActividadesEconomicasSelectedItemList(List<SelectItem> actividadesEconomicasSelectedItemList) {
        this.actividadesEconomicasSelectedItemList =
                actividadesEconomicasSelectedItemList;
    }

    public List<SelectItem> getActividadesEconomicasSelectedItemList() {
        return actividadesEconomicasSelectedItemList;
    }

    public void setSelectedActividadesEconomicasList(List<FnttActividadesEconomicas> selectedActividadesEconomicasList) {
        this.selectedActividadesEconomicasList =
                selectedActividadesEconomicasList;
    }

    public List<FnttActividadesEconomicas> getSelectedActividadesEconomicasList() {
        return selectedActividadesEconomicasList;
    }
}
