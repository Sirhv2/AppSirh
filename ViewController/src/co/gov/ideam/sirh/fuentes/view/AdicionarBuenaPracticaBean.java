package co.gov.ideam.sirh.fuentes.view;

import co.gov.ideam.sirh.auditoria.model.Auditoria;
import co.gov.ideam.sirh.auditoria.view.AuditoriasDelegate;

import co.gov.ideam.sirh.calidad.model.PuntoMonitoreo;
import co.gov.ideam.sirh.calidad.view.CalidadDelegate;
import co.gov.ideam.sirh.fuentes.model.FnttActores;
import co.gov.ideam.sirh.fuentes.model.FnttBuenasPracticas;
import co.gov.ideam.sirh.fuentes.model.FnttCategorias;
import co.gov.ideam.sirh.fuentes.model.FnttCostos;
import co.gov.ideam.sirh.fuentes.model.FnttFuente;

import co.gov.ideam.sirh.fuentes.model.FnttLogros;
import co.gov.ideam.sirh.fuentes.model.FnttLogrosPracticas;
import co.gov.ideam.sirh.fuentes.model.FnttMotivaciones;
import co.gov.ideam.sirh.fuentes.model.FnttMotivacionesPracticas;
import co.gov.ideam.sirh.fuentes.model.FnttPrincipios;
import co.gov.ideam.sirh.fuentes.model.FnttPrincipiosPracticas;
import co.gov.ideam.sirh.fuentes.model.FnttPrincipiosPracticasPK;
import co.gov.ideam.sirh.fuentes.model.FnttProyectosEducativos;
import co.gov.ideam.sirh.fuentes.model.FnttTramo;
import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.parametros.model.Lista;

import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;

import co.gov.ideam.sirh.util.ConstantesParametros;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;

import java.util.Map;

import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import javax.faces.model.SelectItem;

import javax.swing.text.Keymap;

import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichMenu;
import oracle.adf.view.rich.component.rich.RichPopup;

import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanRadio;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelTabbed;
import oracle.adf.view.rich.component.rich.layout.RichShowDetailItem;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.nav.RichCommandMenuItem;
import oracle.adf.view.rich.component.rich.output.RichImage;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;


public class AdicionarBuenaPracticaBean extends StandarBean {

    private static final String LOGRO_TIPO_BP = "BP";

    private FnttFuente fuente;
    private String accionAdicionar;
    private FnttBuenasPracticas practica;
    
    private List<SelectItem> sistemasReferencia=
        new ArrayList<SelectItem>();

    private Map<String, Boolean> principios =
        new LinkedHashMap<String, Boolean>();
    private Map<String, FnttPrincipios> principios2 =
        new LinkedHashMap<String, FnttPrincipios>();
    private List<FnttPrincipios> principiosList;

    private List<FnttCategorias> categoriasList;
    private List<SelectItem> categoriasSelectedItemList =
        new ArrayList<SelectItem>();

    private List<SelectItem> municipiosSelectedItemList =
        new ArrayList<SelectItem>();
    private List<SelectItem> departamentosSelectedItemList =
        new ArrayList<SelectItem>();

    private List<FnttProyectosEducativos> proyEducList;
    private List<SelectItem> proyEducSelectedItemList =
        new ArrayList<SelectItem>();

    private Map<String, Boolean> motivaciones =
        new LinkedHashMap<String, Boolean>();
    private Map<String, FnttMotivaciones> motivaciones2 =
        new LinkedHashMap<String, FnttMotivaciones>();
    private List<FnttMotivaciones> motivacionesList;

    private List<FnttActores> actoresList;
    private List<SelectItem> actoresSelectedItemList =
        new ArrayList<SelectItem>();

    private List<FnttCostos> costosList;
    private List<SelectItem> costosSelectedItemList =
        new ArrayList<SelectItem>();

    private Map<String, Boolean> logros = new LinkedHashMap<String, Boolean>();
    private Map<String, FnttLogros> logros2 =
        new LinkedHashMap<String, FnttLogros>();
    private List<FnttLogros> logrosList;

    private RichPopup p_registro_exitoso;

    private RichDialog d_registro_exitoso;
    private RichPanelGroupLayout panelGroupLayout1;
    private RichSelectOneChoice soc_municipio;
    
    public AdicionarBuenaPracticaBean() {
        init();
    }
    
    private void init(){
            FacesUtils.setActiveBean("AdicionarBuenaPracticaBean",
                                     "Adicionar Buena Practica",
                                     AdicionarBuenaPracticaBean.class);
            FacesUtils.removeManagedBeanFromSession("FuentesBean");
            
            if (this.principios !=null) this.principios.clear();
            if (this.principios2 !=null) this.principios2.clear();
            if (this.principiosList !=null) this.principiosList.clear();
            if (this.categoriasList !=null) this.categoriasList.clear();
            if (this.categoriasSelectedItemList !=null) this.categoriasSelectedItemList.clear();
            if (this.municipiosSelectedItemList !=null) this.municipiosSelectedItemList.clear();
            if (this.departamentosSelectedItemList !=null) this.departamentosSelectedItemList.clear();
            if (this.proyEducList !=null) this.proyEducList.clear();
            if (this.proyEducSelectedItemList !=null) this.proyEducSelectedItemList.clear();
            if (this.motivaciones !=null) this.motivaciones.clear();
            if (this.motivaciones2 !=null) this.motivaciones2.clear();
            if (this.motivacionesList !=null) this.motivacionesList.clear();
            if (this.actoresList !=null) this.actoresList.clear();
            if (this.actoresSelectedItemList !=null) this.actoresSelectedItemList.clear();
            if (this.costosList !=null) this.costosList.clear();
            if (this.costosSelectedItemList !=null) this.costosSelectedItemList.clear();
            if (this.logros !=null) this.logros.clear();
            if (this.logros2 !=null) this.logros2.clear();
            if (this.logrosList !=null) this.logrosList.clear();
            if (this.sistemasReferencia != null) this.sistemasReferencia.clear();
            
            this.load();
        }

    public void load() {
        try {

            this.fuente =
                    (FnttFuente)FacesUtils.getFromSession("fuenteSeleccionada");

            UsuarioConectadoTO usuarioConectado =
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

            FuenteDelegate fd = FuenteDelegate.getInstance();
            principiosList = fd.getPrincipiosBuenasPracticas();
            categoriasList = fd.getCategoriasBuenasPracticas();

            proyEducList = new ArrayList<FnttProyectosEducativos>();
            proyEducList.add(new FnttProyectosEducativos("Ninguno", -1L));
            proyEducList.addAll(fd.getProyectosEducativosBuenasPracticas());

            motivacionesList = fd.getMotivacionesBuenasPracticas();

            actoresList = fd.getActoresBuenasPracticas();

            costosList = fd.getCostosBuenasPracticas();
            logrosList = fd.getLogrosByTipo(LOGRO_TIPO_BP);

            for (FnttPrincipios f : principiosList) {
                this.principios.put(f.getPrincipio(), Boolean.FALSE);
                this.principios2.put(f.getPrincipio(), f);
                System.out.println(f.getPrincipio());
            }

            for (FnttMotivaciones f : motivacionesList) {
                this.motivaciones.put(f.getMotivacion(), Boolean.FALSE);
                this.motivaciones2.put(f.getMotivacion(), f);
                System.out.println(f.getMotivacion());
            }

            for (FnttCategorias f : categoriasList) {
                SelectItem si = new SelectItem(f, f.getCategoria());
                categoriasSelectedItemList.add(si);
            }


            for (FnttProyectosEducativos f : proyEducList) {
                SelectItem si = new SelectItem(f, f.getDescripcion());
                proyEducSelectedItemList.add(si);
            }


            for (FnttActores f : actoresList) {
                SelectItem si = new SelectItem(f, f.getActor());
                actoresSelectedItemList.add(si);
            }

            for (FnttCostos f : costosList) {
                SelectItem si = new SelectItem(f, f.getCosto());
                costosSelectedItemList.add(si);
            }

            for (FnttLogros f : logrosList) {
                this.logros.put(f.getLogro(), Boolean.FALSE);
                this.logros2.put(f.getLogro(), f);
                System.out.println(f.getLogro());
            }

            List<SelectItem> tmp3 = cargarParametro(ConstantesParametros.CATEGORIA_SISTEMA_REFERENCIA);
            for (SelectItem s: tmp3){
                this.sistemasReferencia.add(new SelectItem(((Lista)s.getValue()).getCodigo(),s.getLabel()));
            }
            
            List<SelectItem> tmp = cargarDivipola(null);
            for (SelectItem s: tmp){
                this.departamentosSelectedItemList.add(new SelectItem(((Divipola)s.getValue()).getId(),s.getLabel()));
            }

            practica = new FnttBuenasPracticas();

        } catch (IdeamException e) {
            showMessage(e);
        }
    }

    public void soc_Departamentos_valueChangeListener(ValueChangeEvent event) throws IdeamException {

        Object departamento = event.getNewValue();
        this.municipiosSelectedItemList = new ArrayList();
        try {
            if (departamento != null){
                List<SelectItem> tmp = cargarDivipola((Long)departamento);
                for (SelectItem s: tmp){
                    this.municipiosSelectedItemList.add(new SelectItem(((Divipola)s.getValue()).getId(),s.getLabel()));
                }
            }    
        } catch (IdeamException e) {
            showMessage(e);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(soc_municipio);
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

        boolean hasPrincipios = false;
        for (Map.Entry e : principios2.entrySet()) {
            if (principios.get(e.getKey())) {
                hasPrincipios = true;
                break;
            }
        }
        if (!hasPrincipios) {
            showMessage("Asegurese de haber diligenciado todas las pestañas.",
                        FacesMessage.SEVERITY_WARN);
            showMessage("* Debe seleccionar al menos un principio");
            return false;
        }

        if (this.practica.getFnttCategorias() == null) {
            showMessage("Asegurese de haber diligenciado todas las pestañas.",
                        FacesMessage.SEVERITY_WARN);
            showMessage("* Debe seleccionar categoria");
            return false;
        }
        if (this.practica.getFnttProyectosEducativos() == null) {
            showMessage("Asegurese de haber diligenciado todas las pestañas.",
                        FacesMessage.SEVERITY_WARN);
            showMessage("* Debe seleccionar Proyecto Educativo");
            return false;
        }
        if (this.practica.getFnttProyectosEducativos().getIdproyecto().longValue() ==
            9999L &&
            (this.practica.getOtroseducativo() == null || this.practica.getOtroseducativo().isEmpty())) {
            showMessage("Asegurese de haber diligenciado todas las pestañas.",
                        FacesMessage.SEVERITY_WARN);
            showMessage("* Debe ingresar OTRO Proyecto Educativo");
            return false;
        }

        boolean hasMotivacion = false;
        for (Map.Entry e : motivaciones2.entrySet()) {
            if (motivaciones.get(e.getKey())) {
                hasMotivacion = true;
                break;
            }
        }
        if (!hasMotivacion) {
            showMessage("Asegurese de haber diligenciado todas las pestañas.",
                        FacesMessage.SEVERITY_WARN);
            showMessage("* Debe seleccionar al menos una motivacion");
            return false;
        }

        if (this.practica.getFnttActores() == null) {
            showMessage("Asegurese de haber diligenciado todas las pestañas.",
                        FacesMessage.SEVERITY_WARN);
            showMessage("* Debe seleccionar Actor");
            return false;
        }
        if (this.practica.getFnttActores().getIdactor().longValue() == 9999L &&
            (this.practica.getOtrosactor() == null ||
             this.practica.getOtrosactor().isEmpty())) {
            showMessage("Asegurese de haber diligenciado todas las pestañas.",
                        FacesMessage.SEVERITY_WARN);
            showMessage("* Debe ingresar OTRO Actor");
            return false;
        }

        if (this.practica.getFnttCostos() == null) {
            showMessage("Asegurese de haber diligenciado todas las pestañas.",
                        FacesMessage.SEVERITY_WARN);
            showMessage("* Debe seleccionar Costo");
            return false;
        }
        boolean hasLogros = false;
        for (Map.Entry e : logros.entrySet()) {
            if (logros.get(e.getKey())) {
                hasLogros = true;
                break;
            }
        }
        if (!hasLogros) {
            showMessage("Asegurese de haber diligenciado todas las pestañas.",
                        FacesMessage.SEVERITY_WARN);
            showMessage("* Debe seleccionar al menos un logro");
            return false;
        }


        if (this.practica.getDificultad() == null ||
            this.practica.getDificultad().trim().isEmpty()) {
            showMessage("Asegurese de haber diligenciado todas las pestañas.",
                        FacesMessage.SEVERITY_WARN);
            showMessage("* Debe ingresar Dificultad");
            return false;
        }
        if (this.practica.getRecomendacion() == null ||
            this.practica.getRecomendacion().trim().isEmpty()) {
            showMessage("Asegurese de haber diligenciado todas las pestañas.",
                        FacesMessage.SEVERITY_WARN);
            showMessage("* Debe ingresar Recomendacion");
            return false;
        }


        return true;

    }

    public void guardar() {
        try {

            this.fuente =
                    (FnttFuente)FacesUtils.getFromSession("fuenteSeleccionada");

            UsuarioConectadoTO usuarioConectado =
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

            practica.setFnttFuente(this.fuente);
            practica.setFnttLogrosList(new LinkedHashSet<FnttLogros>());
            practica.setFnttMotivacionesList(new LinkedHashSet<FnttMotivaciones>());
            practica.setFnttPrincipiosList(new LinkedHashSet<FnttPrincipios>());
            
            for (Map.Entry e : logros.entrySet()) {
                if (logros.get(e.getKey())) {
                    practica.getFnttLogrosList().add(logros2.get(e.getKey()));
                }
            }

            for (Map.Entry e : motivaciones2.entrySet()) {
                if (motivaciones.get(e.getKey())) {
                    practica.getFnttMotivacionesList().add(motivaciones2.get(e.getKey()));
                }
            }

            for (Map.Entry e : principios2.entrySet()) {
                if (principios.get(e.getKey())) {
                    practica.getFnttPrincipiosList().add(principios2.get(e.getKey()));
                }
            }

            //TODO: calcular si se marcaron proyectos educativos para asignar el valor del siguiente campo.
            if (practica.getFnttProyectosEducativos() == null) {
                practica.setEducativo("NO");
            } else if (practica.getFnttProyectosEducativos().getIdproyecto().longValue() ==
                       -1L) {
                this.practica.setFnttProyectosEducativos(null);
                practica.setEducativo("NO");
            } else {
                practica.setEducativo("SI");
            }

            FuenteDelegate fd = FuenteDelegate.getInstance();
            practica = fd.updateBuenaPractica(practica);
            guardarAuditoria("GUARDAR", "CONFLICTO", getClass().getName(),
                             practica.getIdbuenapraactica());

            showPopup(p_registro_exitoso, true);


        } catch (IdeamException e) {
            showMessage(e);
        }
    }

    public void cb_aceptar_actionListener(ActionEvent actionEvent) {
        accionAdicionar = "editarBuenaPractica";

        FacesUtils.setInSession("practicaSeleccionado", this.practica);

    }
    
    public void lk_todasBuenasPracticas_actionListener(ActionEvent actionEvent) {
        accionAdicionar = "listarBuenasPracticas";
    }

    public String getAceptarAction() {
        // Add event code here...
        this.practica = null;
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

    public void setAccionAdicionar(String accionAdicionar) {
        this.accionAdicionar = accionAdicionar;
    }

    public String getAccionAdicionar() {
        return accionAdicionar;
    }


    public void setPractica(FnttBuenasPracticas practica) {
        this.practica = practica;
    }

    public FnttBuenasPracticas getPractica() {
        if (this.practica == null){
                init();
        }
        return practica;
    }


    public Object[] getmapKeysPrincipios() {
        return principios.keySet().toArray();
    }

    public Map<String, Boolean> getPrincipios() {
        return this.principios;
    }


    public void setCategoriasList(List<FnttCategorias> categoriasList) {
        this.categoriasList = categoriasList;
    }

    public List<FnttCategorias> getCategoriasList() {
        return categoriasList;
    }

    public void setCategoriasSelectedItemList(List<SelectItem> categoriasSelectedItemList) {
        this.categoriasSelectedItemList = categoriasSelectedItemList;
    }

    public List<SelectItem> getCategoriasSelectedItemList() {
        return categoriasSelectedItemList;
    }

    public void setProyEducSelectedItemList(List<SelectItem> proyEducSelectedItemList) {
        this.proyEducSelectedItemList = proyEducSelectedItemList;
    }

    public List<SelectItem> getProyEducSelectedItemList() {
        return proyEducSelectedItemList;
    }

    public Object[] getmapKeysMotivaciones() {
        return motivaciones.keySet().toArray();
    }

    public Map<String, Boolean> getMotivaciones() {
        return this.motivaciones;
    }

    public void setActoresSelectedItemList(List<SelectItem> actoresSelectedItemList) {
        this.actoresSelectedItemList = actoresSelectedItemList;
    }

    public List<SelectItem> getActoresSelectedItemList() {
        return actoresSelectedItemList;
    }

    public void setCostosSelectedItemList(List<SelectItem> costosSelectedItemList) {
        this.costosSelectedItemList = costosSelectedItemList;
    }

    public List<SelectItem> getCostosSelectedItemList() {
        return costosSelectedItemList;
    }

    public Object[] getmapKeysLogros() {
        return logros.keySet().toArray();
    }

    public Map<String, Boolean> getLogros() {
        return this.logros;
    }

    public void setMunicipiosSelectedItemList(List<SelectItem> municipiosSelectedItemList) {
        this.municipiosSelectedItemList = municipiosSelectedItemList;
    }

    public List<SelectItem> getMunicipiosSelectedItemList() {
        return municipiosSelectedItemList;
    }

    public void setDepartamentosSelectedItemList(List<SelectItem> departamentosSelectedItemList) {
        this.departamentosSelectedItemList = departamentosSelectedItemList;
    }

    public List<SelectItem> getDepartamentosSelectedItemList() {
        return departamentosSelectedItemList;
    }

    public void setSoc_municipio(RichSelectOneChoice soc_municipio) {
        this.soc_municipio = soc_municipio;
    }

    public RichSelectOneChoice getSoc_municipio() {
        return soc_municipio;
    }

    public void setSistemasReferencia(List<SelectItem> sistemasReferencia) {
        this.sistemasReferencia = sistemasReferencia;
    }

    public List<SelectItem> getSistemasReferencia() {
        return sistemasReferencia;
    }
    
}
