package co.gov.ideam.sirh.fuentes.view;

import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.model.PartZonificListas;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.fuentes.model.CriteriosBusquedaTO;
import co.gov.ideam.sirh.parametros.view.ParametrosDelegate;
import co.gov.ideam.sirh.pomca.view.PomtPlanesBean;
import co.gov.ideam.sirh.porh.model.PorhPlanes;
import co.gov.ideam.sirh.porh.view.PorhDelegate;
import co.gov.ideam.sirh.reportes.view.ReporteDelegate;
import co.gov.ideam.sirh.seguridad.model.PerfilVO;
import co.gov.ideam.sirh.seguridad.view.SeguridadDelegate;
import co.gov.ideam.sirh.usuarios.agua.model.UsuarioAgua;
import co.gov.ideam.sirh.usuarios.agua.view.UsuariosAguaDelegate;
import co.gov.ideam.sirh.util.Constantes;
import co.gov.ideam.sirh.util.ConstantesCalidad;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;
import co.gov.ideam.sirh.util.IdeamException;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UISelectItems;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import javax.persistence.Transient;

import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichMenu;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectManyChoice;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichCommandMenuItem;
import oracle.adf.view.rich.component.rich.output.RichActiveOutputText;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;

import org.apache.myfaces.trinidad.event.SelectionEvent;

public class FuentesBean extends StandarBean {

    private List listaFuentes;
    private List listaFuentesDetalle;
    private List listaDepartamentos;
    private List listaMunicipios;
    private List listaArea;
    private List listaZona;
    private List listaSubzona;
    private String accionAdicionar;

    private RichForm formF1;
    private RichDocument documentF1;
    private RichPanelStretchLayout pslf1;
    private RichPanelSplitter psf1;
    private RichPanelFormLayout pflf1;
    private RichSelectOneChoice selectOneChoice1;
    private UISelectItems selectItems1;
    private RichSelectOneChoice selectOneChoice2;
    private UISelectItems selectItems2;
    private RichSelectOneChoice selectOneChoice3;
    private UISelectItems selectItems3;
    private RichSelectOneChoice selectOneChoice4;
    private UISelectItems selectItems4;
    private RichSelectOneChoice selectOneChoice5;
    private UISelectItems selectItems5;
    private RichPanelGroupLayout panelGroupLayout1;
    private RichPanelCollection panelCollection1;
    private RichMenu menu1;
    private RichCommandMenuItem commandMenuItem1;
    private RichCommandMenuItem commandMenuItem2;
    private RichCommandButton commandButton1;
    private RichPopup popupFuente;
    private RichTable t_fuentes;
    private FnttFuente fuenteSeleccionada;
    private RichInputText it_fuente;
    private RichCommandMenuItem cmi_porh;
    private RichPanelGroupLayout panelGroupLayout2;
    private RichActiveOutputText activeOutputText1;
    private RichSpacer spacer1;
    private RichCommandMenuItem cmi_pomca;
    private RichCommandMenuItem cm_pomca;
    private RichCommandMenuItem cm_registrarGestion;

    private String visiblePerfil="true";
    private List listaAutoridades;
    private RichSelectOneChoice soc_autoridad_id;
    private UISelectItems si1Autoridad;
    private RichCommandMenuItem cmi_ImprimirListado;
    private RichCommandMenuItem cmi_establecerConflicto;
    private RichCommandMenuItem cmi_regBuenaPractica;
    private RichCommandMenuItem cmi_ImprimirReporteTramos; 

    public FuentesBean() {
        FacesUtils.setActiveBean("FuentesBean", "Fuentes",
                                 FuenteDelegate.class);
        FacesUtils.removeManagedBeanFromSession("tramosBean");
        FacesUtils.removeManagedBeanFromSession("detalleFuenteBean");
        FacesUtils.removeManagedBeanFromSession("adicionarFuenteBean");
        this.load();
    }

    public void load() {
        try {
            this.listaDepartamentos = this.cargarDivipola(null);
            this.listaArea = this.cargarZonificacion(null);
            this.listaMunicipios = new ArrayList();
            this.listaZona = new ArrayList();
            this.listaSubzona = new ArrayList();

            UsuarioConectadoTO usuarioConectado =
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

            FuenteDelegate fd = FuenteDelegate.getInstance();
            SeguridadDelegate seg = SeguridadDelegate.getInstance(); 
            PerfilVO pp=  seg.getPerfilUsuario(usuarioConectado.getUsuario().getCodigo());
            //si es usuario del ideam
            if (usuarioConectado.getUsuario().getAutoridadAmbiental() ==
                null ||
                usuarioConectado.getUsuario().getAutoridadAmbiental().getId() ==
                null ||
                usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue() ==
                0 ||
                usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue() ==
                Constantes.IDEAM||
                pp.getCodigo()==ConstantesCalidad.PERFIL_CONSULTA){//perfil de revision
                      this.visiblePerfil="false";
                      this.listaAutoridades= this.cargarAutoridades();
                      listaFuentes = new ArrayList();
                      listaFuentesDetalle= new ArrayList();
                  }else{
                            this.visiblePerfil="true";
                            listaFuentes = fd.getAllFuentes(usuarioConectado.getUsuario().getAutoridadAmbiental());
                            
             //listaFuentesDetalle= new ArrayList();
               listaFuentesDetalle= fd.getDetalleTramosRelacionados(usuarioConectado.getUsuario().getAutoridadAmbiental());
                            
           } 
            
            
            if (listaFuentes != null) {
                
                Integer i=1;
                PorhDelegate pd = PorhDelegate.getInstance();
                Iterator<FnttFuente> it = listaFuentes.iterator();
                while (it.hasNext()) {
                    FnttFuente fte = it.next();
                    fte.setNum(i);
                    PorhPlanes porh =
                        pd.getPlanOrdenamiento(fte.getId(), fte.getCodigoAutoridad());
                    if (porh != null) {
                        fte.setPorh(porh);
                    }i++;
                } 
            }
        } catch (IdeamException e) {
            showMessage(e);
        }
    }

    public void setSelectOneChoice1(RichSelectOneChoice selectOneChoice1) {
        this.selectOneChoice1 = selectOneChoice1;
    }

    public RichSelectOneChoice getSelectOneChoice1() {
        return selectOneChoice1;
    }

    public void setSelectItems1(UISelectItems selectItems1) {
        this.selectItems1 = selectItems1;
    }

    public UISelectItems getSelectItems1() {
        return selectItems1;
    }

    public void setFormF1(RichForm formF1) {
        this.formF1 = formF1;
    }

    public RichForm getFormF1() {
        return formF1;
    }

    public void setDocumentF1(RichDocument documentF1) {
        this.documentF1 = documentF1;
    }

    public RichDocument getDocumentF1() {
        return documentF1;
    }

    public void setPslf1(RichPanelStretchLayout pslf1) {
        this.pslf1 = pslf1;
    }

    public RichPanelStretchLayout getPslf1() {
        return pslf1;
    }

    public void setPsf1(RichPanelSplitter psf1) {
        this.psf1 = psf1;
    }

    public RichPanelSplitter getPsf1() {
        return psf1;
    }

    public void setPflf1(RichPanelFormLayout pflf1) {
        this.pflf1 = pflf1;
    }

    public RichPanelFormLayout getPflf1() {
        return pflf1;
    }

    public void setSelectOneChoice2(RichSelectOneChoice selectOneChoice2) {
        this.selectOneChoice2 = selectOneChoice2;
    }

    public RichSelectOneChoice getSelectOneChoice2() {
        return selectOneChoice2;
    }

    public void setSelectItems2(UISelectItems selectItems2) {
        this.selectItems2 = selectItems2;
    }

    public UISelectItems getSelectItems2() {
        return selectItems2;
    }

    public void setSelectOneChoice3(RichSelectOneChoice selectOneChoice3) {
        this.selectOneChoice3 = selectOneChoice3;
    }

    public RichSelectOneChoice getSelectOneChoice3() {
        return selectOneChoice3;
    }

    public void setSelectItems3(UISelectItems selectItems3) {
        this.selectItems3 = selectItems3;
    }

    public UISelectItems getSelectItems3() {
        return selectItems3;
    }

    public void setSelectOneChoice4(RichSelectOneChoice selectOneChoice4) {
        this.selectOneChoice4 = selectOneChoice4;
    }

    public RichSelectOneChoice getSelectOneChoice4() {
        return selectOneChoice4;
    }

    public void setSelectItems4(UISelectItems selectItems4) {
        this.selectItems4 = selectItems4;
    }

    public UISelectItems getSelectItems4() {
        return selectItems4;
    }

    public void setSelectOneChoice5(RichSelectOneChoice selectOneChoice5) {
        this.selectOneChoice5 = selectOneChoice5;
    }

    public RichSelectOneChoice getSelectOneChoice5() {
        return selectOneChoice5;
    }

    public void setSelectItems5(UISelectItems selectItems5) {
        this.selectItems5 = selectItems5;
    }

    public UISelectItems getSelectItems5() {
        return selectItems5;
    }

    public void setPanelGroupLayout1(RichPanelGroupLayout panelGroupLayout1) {
        this.panelGroupLayout1 = panelGroupLayout1;
    }

    public RichPanelGroupLayout getPanelGroupLayout1() {
        return panelGroupLayout1;
    }

    public void setPanelCollection1(RichPanelCollection panelCollection1) {
        this.panelCollection1 = panelCollection1;
    }

    public RichPanelCollection getPanelCollection1() {
        return panelCollection1;
    }

    public void setPopupFuente(RichPopup popupFuente) {
        this.popupFuente = popupFuente;
    }

    public RichPopup getPopupFuente() {
        return popupFuente;
    }

    public void selectOneChoice1_valueChangeListener(ValueChangeEvent event) throws IdeamException {

        Object area = event.getNewValue();
        this.listaZona = new ArrayList();
        try {
            if (area != null) {
                this.listaZona =
                        this.cargarZonificacion(((PartZonificListas)area).getId());
            }
        } catch (IdeamException e) {
            showMessage(e);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(selectOneChoice2);
    }

    public void selectOneChoice2_valueChangeListener(ValueChangeEvent event) throws IdeamException {

        Object zona = event.getNewValue();
        this.listaSubzona = new ArrayList();
        try {
            if (zona != null) {
                this.listaSubzona =
                        this.cargarZonificacion(((PartZonificListas)zona).getId());
            }
        } catch (IdeamException e) {
            showMessage(e);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(selectOneChoice3);
    }

    public void selectOneChoice4_valueChangeListener(ValueChangeEvent event) throws IdeamException {

        Object departamento = event.getNewValue();
        this.listaMunicipios = new ArrayList();
        try {
            if (departamento != null) {
                this.listaMunicipios =
                        this.cargarDivipola(((Divipola)departamento).getId());
            }
        } catch (IdeamException e) {
            showMessage(e);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(selectOneChoice5);
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

    public void setCommandMenuItem2(RichCommandMenuItem commandMenuItem2) {
        this.commandMenuItem2 = commandMenuItem2;
    }

    public RichCommandMenuItem getCommandMenuItem2() {
        return commandMenuItem2;
    }

    public void setCommandButton1(RichCommandButton commandButton1) {
        this.commandButton1 = commandButton1;
    }

    public RichCommandButton getCommandButton1() {
        return commandButton1;
    }

    public void setListaFuentes(List listaFuentes) {
        this.listaFuentes = listaFuentes;
    }

    public List getListaFuentes() {
        return listaFuentes;
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

    public void setListaArea(List listaArea) {
        this.listaArea = listaArea;
    }

    public List getListaArea() {
        return listaArea;
    }

    public void setListaZona(List listaZona) {
        this.listaZona = listaZona;
    }

    public List getListaZona() {
        return listaZona;
    }

    public void setListaSubzona(List listaSubzona) {
        this.listaSubzona = listaSubzona;
    }

    public List getListaSubzona() {
        return listaSubzona;
    }

    public void setAccionAdicionar(String accionAdicionar) {
        this.accionAdicionar = accionAdicionar;
    }

    public String getAccionAdicionar() {
        return accionAdicionar;
    }

    public String commandMenuItem1_action() { //adicionar
        UsuarioConectadoTO usuarioConectado =
            (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

        Autoridades autoridadAmbiental =
            usuarioConectado.getUsuario().getAutoridadAmbiental();
        if (autoridadAmbiental == null ||
            autoridadAmbiental.getId().intValue() == Constantes.IDEAM) {
            showMessage(getText("NO_EXISTE_AUTORIDAD_AMBIENTAL"),
                        FacesMessage.SEVERITY_ERROR);
            return "";
        } else {
            return accionAdicionar = "adicionarFuente";
        }
    }

    public void cmiImprimirListado_actionListener(ActionEvent actionEvent) {
        try {
            HashMap parametros = new HashMap();
            parametros.put("p_modulo", "Fuentes");
            parametros.put("p_titulo", "Fuentes Hidricas");
            this.generateReport("ListadoFuentes.jasper", this.listaFuentes,
                                parametros, ReporteDelegate.EXCEL);
        } catch (IdeamException e) {
            showMessage(e);
        }
        }
    
    /*---daln--25112015-*/
    public void cmiImprimirReporteTramos_actionListener(ActionEvent actionEvent) {
        try {
            HashMap parametros = new HashMap();
            parametros.put("p_modulo", "Fuentes");
            parametros.put("p_titulo", "Fuentes Hidricas");
            this.generateReport("ListadoFuentesTramo.jasper", this.listaFuentesDetalle,
                                parametros, ReporteDelegate.EXCEL);
        } catch (IdeamException e) {
            showMessage(e);
        }
        }
  /*---daln---*/
    
    
    public String cmEstablecerConflicto_actionListener(ActionEvent actionEvent) {

        accionAdicionar = "";

        if (this.fuenteSeleccionada == null) {
            showMessage(getText("seleccionar_registro"),
                        FacesMessage.SEVERITY_ERROR);
            return "";
        }

        accionAdicionar = "listarConflictos";

        return accionAdicionar; 
    }

    public String cmRegistraBuenaPractica_actionListener(ActionEvent actionEvent) {

        accionAdicionar = "";

        if (this.fuenteSeleccionada == null) {
            showMessage(getText("seleccionar_registro"),
                        FacesMessage.SEVERITY_ERROR);
            return "";
        }

        accionAdicionar = "listarBuenasPracticas";

        return accionAdicionar; 
    }


    public void commandMenuItem2_actionListener(ActionEvent actionEvent) { //editar
        accionAdicionar = "";
        if (this.fuenteSeleccionada == null) {
            showMessage(getText("seleccionar_registro"),
                        FacesMessage.SEVERITY_ERROR);
            return;
        }

        FacesUtils.setInSession("fuenteSeleccionada", this.fuenteSeleccionada);
        FacesUtils.removeManagedBeanFromSession("TramosTreeHandler");
        accionAdicionar = "detalleFuente";
    }

    //Buscar

    public void commandButton1_actionListener(ActionEvent actionEvent) throws IdeamException {
        buscar();
    }
    public void buscar() throws IdeamException {
        try {
            CriteriosBusquedaTO criterios = new CriteriosBusquedaTO();
            this.listaFuentes = new ArrayList();
            int i = 0;
            UsuarioConectadoTO usuarioConectado =
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
            if(this.soc_autoridad_id.getValue()!=null){
                ParametrosDelegate pd = ParametrosDelegate.getInstance();
                Autoridades aut=(Autoridades)this.soc_autoridad_id.getValue();
                System.out.println("Autoridad seleccioanda por rol MADS:"+aut.getId());
                Autoridades au=pd.getAutoridad(aut.getId());
                criterios.setAutoridad(au);
                i++; 
            }
            if (this.selectOneChoice1.getValue() != null) {
                criterios.setArea((PartZonificListas)this.selectOneChoice1.getValue());
                i++;
            }
            if (this.selectOneChoice2.getValue() != null) {
                criterios.setZona((PartZonificListas)this.selectOneChoice2.getValue());
                i++;
            }
            if (this.selectOneChoice3.getValue() != null) {
                criterios.setSubzona((PartZonificListas)this.selectOneChoice3.getValue());
                i++;
            }
            if (this.selectOneChoice4.getValue() != null) {
                criterios.setDepartamento((Divipola)this.selectOneChoice4.getValue());
                i++;
            }
            if (this.selectOneChoice5.getValue() != null) {
                criterios.setMunicipio((Divipola)this.selectOneChoice5.getValue());
                i++;
            }
            if (this.it_fuente.getValue() != null) {
                criterios.setNombreFuente(this.it_fuente.getValue().toString().toUpperCase());
                i++;
            }


            FuenteDelegate fd = FuenteDelegate.getInstance();
            SeguridadDelegate seg = SeguridadDelegate.getInstance(); 
            PerfilVO pp=  seg.getPerfilUsuario(usuarioConectado.getUsuario().getCodigo());
           
            if (usuarioConectado.getUsuario().getAutoridadAmbiental()==null ||
                usuarioConectado.getUsuario().getAutoridadAmbiental().getId() == null ||
                usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue() ==0 ||
                usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue() == Constantes.IDEAM ||
                pp.getCodigo()==ConstantesCalidad.PERFIL_CONSULTA){
                System.out.println("consulta por rol auditor");
                listaFuentes = fd.getAllFuentes(criterios);
            }else  if(i==0) {   //es un usuario de una car
                System.out.println("i=0");
                 listaFuentes =
                        fd.getAllFuentes(usuarioConectado.getUsuario().getAutoridadAmbiental());
            
            } else { //realiza una busqueda
                 System.out.println("consulta por criterios del usuario");
                 if(pp.getCodigo()!=ConstantesCalidad.PERFIL_CONSULTA)
                    criterios.setAutoridad(usuarioConectado.getUsuario().getAutoridadAmbiental());
                 this.listaFuentes =
                        fd.getAllFuentes(criterios); //todas las fuentes
            }



            if (listaFuentes != null) {
                i=1;
                PorhDelegate pd = PorhDelegate.getInstance();
                Iterator<FnttFuente> it = listaFuentes.iterator();
                while (it.hasNext()) {
                    FnttFuente fte = it.next();
                    fte.setNum(i);
                    PorhPlanes porh =
                        pd.getPlanOrdenamiento(fte.getId(), fte.getCodigoAutoridad());
                    if (porh != null) {
                        fte.setPorh(porh);
                    }i++;
                } 
            }




            AdfFacesContext.getCurrentInstance().addPartialTarget(t_fuentes); //solo las fuentes de la autoridad
        } catch (IdeamException e) {
            showMessage(e);
        }
        
    }
    public void setT_fuentes(RichTable table1) {
        this.t_fuentes = table1;
    }

    public RichTable getT_fuentes() {
        return t_fuentes;
    }

    public void t_fuentes_selectionListener(SelectionEvent selectionEvent) {
        RichTable t_fuentes1 = (RichTable)selectionEvent.getSource();
        this.fuenteSeleccionada = (FnttFuente)t_fuentes1.getSelectedRowData();
        
        FacesUtils.removeFromSession("fuenteSeleccionada");
        FacesUtils.setInSession("fuenteSeleccionada",  fuenteSeleccionada);
        System.out.println(">>>>>>>>> EVENTO FUENTE SELECCIONADA>>>>>>> "+FacesUtils.getFromSession("fuenteSeleccionada"));

    }


    public void setFuenteSeleccionada(FnttFuente fuenteSeleccionada) {
        this.fuenteSeleccionada = fuenteSeleccionada;
    }

    public FnttFuente getFuenteSeleccionada() {
        return fuenteSeleccionada;
    }

    public void setIt_fuente(RichInputText inputText1) {
        this.it_fuente = inputText1;
    }

    public RichInputText getIt_fuente() {
        return it_fuente;
    }

    public void setCmi_porh(RichCommandMenuItem cmi2) {
        this.cmi_porh = cmi2;
    }

    public RichCommandMenuItem getCmi_porh() {
        return cmi_porh;
    }

    public void cmi_porh_actionListener(ActionEvent actionEvent) {
        accionAdicionar = "";
        if (this.fuenteSeleccionada == null) {
            showMessage(getText("seleccionar_registro"),
                        FacesMessage.SEVERITY_ERROR);
            return;
        }

        FacesUtils.setInSession("fuenteSeleccionada", this.fuenteSeleccionada);
        FacesUtils.removeManagedBeanFromSession("TramosTreeHandler");
        accionAdicionar = "establecerPorh";
    }

    public void setPanelGroupLayout2(RichPanelGroupLayout panelGroupLayout2) {
        this.panelGroupLayout2 = panelGroupLayout2;
    }

    public RichPanelGroupLayout getPanelGroupLayout2() {
        return panelGroupLayout2;
    }

    public void setActiveOutputText1(RichActiveOutputText activeOutputText1) {
        this.activeOutputText1 = activeOutputText1;
    }

    public RichActiveOutputText getActiveOutputText1() {
        return activeOutputText1;
    }

    public void setSpacer1(RichSpacer spacer1) {
        this.spacer1 = spacer1;
    }

    public RichSpacer getSpacer1() {
        return spacer1;
    }

    public void setCmi_pomca(RichCommandMenuItem commandMenuItem3) {
        this.cmi_pomca = commandMenuItem3;
    }

    public RichCommandMenuItem getCmi_pomca() {
        return cmi_pomca;
    }

   


    public void cmi_pomt_publicaciones_actionListener(ActionEvent actionEvent) {
        accionAdicionar = "";
        if (this.fuenteSeleccionada == null) {
            showMessage(getText("seleccionar_registro"),
                        FacesMessage.SEVERITY_ERROR);
            return;
        }

        FacesUtils.setInSession("fuenteSeleccionada", this.fuenteSeleccionada);
        FacesUtils.removeManagedBeanFromSession("TramosTreeHandler");
        accionAdicionar = "publicaciones";
    }

   /* public void cmi_pomt_planes_actionListener(ActionEvent actionEvent) throws IdeamException {
        System.out.println("JS >>>>>>>>> FUENTE SELECCIONADA>>>>>>> "+FacesUtils.getFromSession("fuenteSeleccionada"));
        
        accionAdicionar = "";
        if (this.fuenteSeleccionada == null) {
            showMessage(getText("seleccionar_registro"), FacesMessage.SEVERITY_ERROR);
            return;
        }
        FacesUtils.setInSession("fuenteSeleccionada", this.fuenteSeleccionada);
        //PomtPlanesBean planBean = (PomtPlanesBean)FacesUtils.getManagedBeanValue("planesBean");
        //planBean.load();
        accionAdicionar = "adicionarPlan";
       
    }*/

    public void setCm_pomca(RichCommandMenuItem cm_pomca) {
        this.cm_pomca = cm_pomca;
    }

    public RichCommandMenuItem getCm_pomca() {
        return cm_pomca;
    }


    public void setVisiblePerfil(String visiblePerfil) {
        this.visiblePerfil = visiblePerfil;
    }

    public String getVisiblePerfil() {
        return visiblePerfil;
    }

    public void setCm_registrarGestion(RichCommandMenuItem cm_registrarGestion) {
        this.cm_registrarGestion = cm_registrarGestion;
    }

    public RichCommandMenuItem getCm_registrarGestion() {
        return cm_registrarGestion;
    }

    public void setListaAutoridades(List listaAutoridades) {
        this.listaAutoridades = listaAutoridades;
    }

    public List getListaAutoridades() {
        return listaAutoridades;
    }

    public void setSoc_autoridad_id(RichSelectOneChoice soc_autoridad_id) {
        this.soc_autoridad_id = soc_autoridad_id;
    }

    public RichSelectOneChoice getSoc_autoridad_id() {
        return soc_autoridad_id;
    }

    public void soc_filtro_porAtoridad(ValueChangeEvent valueChangeEvent) throws IdeamException {
        Object autoridadSeleccionada = valueChangeEvent.getNewValue();
        buscar();
    }
    public void setSi1Autoridad(UISelectItems si1Autoridad) {
        this.si1Autoridad = si1Autoridad;
    }

    public UISelectItems getSi1Autoridad() {
        return si1Autoridad;
    }

    public void setCmi_ImprimirListado(RichCommandMenuItem commandMenuItem3) {
        this.cmi_ImprimirListado = commandMenuItem3;
    }

    public RichCommandMenuItem getCmi_ImprimirListado() {
        return cmi_ImprimirListado;
    }
    public void setCmi_establecerConflicto(RichCommandMenuItem commandMenuItem3) {
        this.cmi_establecerConflicto = commandMenuItem3;
    }

    public RichCommandMenuItem getCmi_establecerConflicto() {
        return cmi_establecerConflicto;
    }

    public void setCmi_regBuenaPractica(RichCommandMenuItem cmi_regBuenaPractica) {
        this.cmi_regBuenaPractica = cmi_regBuenaPractica;
    }

    public RichCommandMenuItem getCmi_regBuenaPractica() {
        return cmi_regBuenaPractica;
    }
 public void setCmi_ImprimirReporteTramos(RichCommandMenuItem commandMenuItem3) {
  this.cmi_ImprimirReporteTramos = commandMenuItem3;
  }
  public RichCommandMenuItem getCmi_ImprimirReporteTramos() {
  return cmi_ImprimirReporteTramos;
  }

  public void setListaFuentesDetalle(List listaFuentesDetalle) {
    this.listaFuentesDetalle = listaFuentesDetalle;
  }

  public List getListaFuentesDetalle() {
    return listaFuentesDetalle;
  }
}
