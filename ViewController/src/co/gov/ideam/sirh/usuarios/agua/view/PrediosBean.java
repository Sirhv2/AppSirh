package co.gov.ideam.sirh.usuarios.agua.view;

import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Categoria;
import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.view.ParametrosDelegate;
import co.gov.ideam.sirh.seguridad.model.PerfilVO;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.seguridad.view.SeguridadDelegate;
import co.gov.ideam.sirh.usuarios.agua.model.CriteriosBusquedaTO;
import co.gov.ideam.sirh.usuarios.agua.model.PrediosTO;

import co.gov.ideam.sirh.util.Constantes;
import co.gov.ideam.sirh.util.ConstantesCalidad;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UISelectItems;

import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichMenu;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichCommandMenuItem;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
import oracle.adf.view.rich.context.AdfFacesContext;


public class PrediosBean extends StandarBean{
    private RichForm f2;
    private RichDocument d2;
    private RichPanelSplitter ps1;
    
    private List listaTipoSuelo;
    private List listaDepartamentos;
    private List listaMunicipios;
    private List listaPredios;
    private Lista ubicacionSeleccionada;
    
    private RichSelectOneChoice soc_ubicacion;
    private UISelectItems si1;
    private RichPanelFormLayout pfl1;
    private RichCommandButton cb_buscar;
    private RichPanelCollection pc1;
    private RichTable t_predios;
    private RichPanelFormLayout pfl11;
    private RichSelectOneChoice soc_ubicacion1;
    private UISelectItems si11;
    private RichCommandButton cb_buscar1;
    private RichPanelCollection pc11;
    private RichTable t_predios1;
    private RichPanelStretchLayout panelStretchLayout1;
    private RichPanelGroupLayout panelGroupLayout1;
    private RichOutputText outputText1titulo;
    private RichPanelGroupLayout pgl1;
    private RichPopup p_errores;
    private RichDialog d_errores;
    private RichSelectOneChoice soc_autoridad_id;
    private UISelectItems si1Autoridad;
    private RichOutputText ot_errores;
    private RichInputText it_errores;
    private RichMenu m2;
    private RichCommandMenuItem cmi2;
    private RichInputText it_filtro_nombre;
    private RichInputText it_filtro_cedCatastral;
    private RichSelectOneChoice soc_filtro_dpto;
    private UISelectItems selectItems1;
    private RichSelectOneChoice soc_filtro_mun;
    private UISelectItems selectItems2;
    private String visiblePerfil="true";
    private List listaAutoridades;

    public PrediosBean(){
        FacesUtils.setActiveBean("predios",
                                 "predios",
                                 UsuariosAguaDelegate.class);
        this.load();
    }
    
    private void load(){
        try{
            UsuarioConectadoTO usuarioConectado = 
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
            
            if(FacesUtils.getFromSession("FiltroUsuariosDashboard")!=null){
                String ubicacion = 
                    (String)FacesUtils.getFromSession("FiltroUsuariosDashboard");                    
                ParametrosDelegate pd = ParametrosDelegate.getInstance();
                setUbicacionSeleccionada(pd.getListaByDescripcion(ubicacion, Categoria.CLASIFICACION_SUELO));                
                FacesUtils.removeFromSession("FiltroUsuariosDashboard");                
            }
            
            listaTipoSuelo = this.cargarParametro(Categoria.CLASIFICACION_SUELO);
            this.listaDepartamentos = this.cargarDivipola(null);
            this.listaMunicipios = new ArrayList();
            
            
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
            SeguridadDelegate seg = SeguridadDelegate.getInstance(); 
            PerfilVO pp=  seg.getPerfilUsuario(usuarioConectado.getUsuario().getCodigo());
           
            if(getUbicacionSeleccionada() != null){
                CriteriosBusquedaTO criterios = new CriteriosBusquedaTO();
                criterios.setClasificacionSuelo(getUbicacionSeleccionada());
                listaPredios = uad.getAllPredios(criterios,
                                                 usuarioConectado.getUsuario().getAutoridadAmbiental());
            }else{
                if (pp.getCodigo()==ConstantesCalidad.PERFIL_CONSULTA){//perfil de revision
                    this.visiblePerfil="false";
                    this.listaAutoridades= this.cargarAutoridades();
                    this.listaPredios= new ArrayList();
                   
                }else{
                       this.visiblePerfil="true";
                        listaPredios = uad.getAllPredios(null,
                                                         usuarioConectado.getUsuario().getAutoridadAmbiental());
                    } 
            }
            
            //Cabril 04/03/2015
            if (listaPredios != null) {
                
                Integer i=1;
                
                Iterator<PrediosTO> it = listaPredios.iterator();
                while (it.hasNext()) {
                    PrediosTO usu = it.next();
                    usu.setNum(i);
                    i++;
                } 
            }
            
            
        }catch(IdeamException e){
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

    public void setPs1(RichPanelSplitter ps1) {
        this.ps1 = ps1;
    }

    public RichPanelSplitter getPs1() {
        return ps1;
    }


    public List getListaTipoSuelo() {
        return listaTipoSuelo;
    }

    public void setListaTipoSuelo(List listaTipoSuelo) {
        this.listaTipoSuelo = listaTipoSuelo;
    }

    public void setSoc_ubicacion(RichSelectOneChoice soc1) {
        this.soc_ubicacion = soc1;
    }

    public RichSelectOneChoice getSoc_ubicacion() {
        return soc_ubicacion;
    }

    public void setSi1(UISelectItems si1) {
        this.si1 = si1;
    }

    public UISelectItems getSi1() {
        return si1;
    }

    public void setCb_buscar(RichCommandButton cb1) {
        this.cb_buscar = cb1;
    }

    public RichCommandButton getCb_buscar() {
        return cb_buscar;
    }

    public void setPc1(RichPanelCollection pc1) {
        this.pc1 = pc1;
    }

    public RichPanelCollection getPc1() {
        return pc1;
    }

    public List getListaPredios() {
        return listaPredios;
    }

    public void setListaPredios(List listaPredios) {
        this.listaPredios = listaPredios;
    }

    public void setT_predios(RichTable t1) {
        this.t_predios = t1;
    }

    public RichTable getT_predios() {
        return t_predios;
    }

    public void cb_buscar_actionListener(ActionEvent actionEvent) throws IdeamException {
        buscar();

    }
    public void buscar() throws IdeamException {
        CriteriosBusquedaTO criterios = new CriteriosBusquedaTO();
        if(this.soc_autoridad_id.getValue()!=null){
            ParametrosDelegate pd = ParametrosDelegate.getInstance();
            Autoridades aut=(Autoridades)this.soc_autoridad_id.getValue();
            System.out.println("Autoridad seleccioanda por rol MADS:"+aut.getId());
            Autoridades au=pd.getAutoridad(aut.getId());
            criterios.setAutoridad(au);
                  
        }
        if(this.it_filtro_nombre.getValue()!=null &&
           this.it_filtro_nombre.getValue().toString().length()>0){
            criterios.setNombres(it_filtro_nombre.getValue().toString());       
        }
        
        if(this.it_filtro_cedCatastral.getValue()!=null &&
           this.it_filtro_cedCatastral.getValue().toString().length()>0){
            criterios.setCedCatastral(it_filtro_cedCatastral.getValue().toString());       
        }
        
        if(this.soc_ubicacion1.getValue()!=null){
            criterios.setClasificacionSuelo((Lista)this.soc_ubicacion1.getValue());
        }

        if(this.soc_filtro_dpto.getValue()!=null){
            criterios.setDepartamento((Divipola)this.soc_filtro_dpto.getValue());
        }
        if(this.soc_filtro_mun.getValue()!=null){
           criterios.setMunicipio( (Divipola)this.soc_filtro_mun.getValue());
        }
        try{
            UsuarioConectadoTO usuarioConectado = (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
            SeguridadDelegate seg = SeguridadDelegate.getInstance(); 
            PerfilVO pp=  seg.getPerfilUsuario(usuarioConectado.getUsuario().getCodigo());
           
            if (usuarioConectado.getUsuario().getAutoridadAmbiental()==null ||
                usuarioConectado.getUsuario().getAutoridadAmbiental().getId() == null ||
                usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue() ==0 ||
                usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue() == Constantes.IDEAM ||
                pp.getCodigo()==ConstantesCalidad.PERFIL_CONSULTA){
                    
                        listaPredios = uad.getAllPredios(criterios);
            }else{ 
                    this.listaPredios= new ArrayList();
                    this.listaPredios = uad.getAllPrediosAut(criterios, usuarioConectado.getUsuario().
                                                                   getAutoridadAmbiental().
                                                                   getId().
                                                                   longValue());
            }
            AdfFacesContext.getCurrentInstance().addPartialTarget(t_predios1);
        }catch(IdeamException e){
            showMessage(e);
        }
    }

    public Lista getUbicacionSeleccionada() {
        return ubicacionSeleccionada;
    }

    public void setUbicacionSeleccionada(Lista ubicacionSeleccionada) {
        this.ubicacionSeleccionada = ubicacionSeleccionada;
    }
    public void usuario_actionListener(ActionEvent actionEvent) {
        Long codigoUsuario = 
            (Long)actionEvent.getComponent().getAttributes().get("codigoUsuario");                
        FacesUtils.setInSession("usuarioSeleccionado", codigoUsuario);
    }    
    public void predio_actionListener(ActionEvent actionEvent) {
        Long codigoUsuario = 
            (Long)actionEvent.getComponent().getAttributes().get("codigoUsuario");                
        Long codigoPredio = 
            (Long)actionEvent.getComponent().getAttributes().get("codigoPredio");                
        FacesUtils.setInSession("usuarioSeleccionado", codigoUsuario);
        FacesUtils.setInSession("predioSeleccionado", codigoPredio);
    }


    public void setPfl11(RichPanelFormLayout pfl11) {
        this.pfl11 = pfl11;
    }

    public RichPanelFormLayout getPfl11() {
        return pfl11;
    }

    public void setSoc_ubicacion1(RichSelectOneChoice soc_ubicacion1) {
        this.soc_ubicacion1 = soc_ubicacion1;
    }

    public RichSelectOneChoice getSoc_ubicacion1() {
        return soc_ubicacion1;
    }

    public void setSi11(UISelectItems si11) {
        this.si11 = si11;
    }

    public UISelectItems getSi11() {
        return si11;
    }

    public void setCb_buscar1(RichCommandButton cb_buscar1) {
        this.cb_buscar1 = cb_buscar1;
    }

    public RichCommandButton getCb_buscar1() {
        return cb_buscar1;
    }

    public void setPc11(RichPanelCollection pc11) {
        this.pc11 = pc11;
    }

    public RichPanelCollection getPc11() {
        return pc11;
    }

    public void setT_predios1(RichTable t_predios1) {
        this.t_predios1 = t_predios1;
    }

    public RichTable getT_predios1() {
        return t_predios1;
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

    public void setOutputText1titulo(RichOutputText outputText1) {
        this.outputText1titulo = outputText1;
    }

    public RichOutputText getOutputText1titulo() {
        return outputText1titulo;
    }

    public void setPgl1(RichPanelGroupLayout pgl1) {
        this.pgl1 = pgl1;
    }

    public RichPanelGroupLayout getPgl1() {
        return pgl1;
    }

    public void setP_errores(RichPopup p1) {
        this.p_errores = p1;
    }

    public RichPopup getP_errores() {
        return p_errores;
    }

    public void setD_errores(RichDialog d1) {
        this.d_errores = d1;
    }

    public RichDialog getD_errores() {
        return d_errores;
    }

    public void setPfl1(RichPanelFormLayout pfl1) {
        this.pfl1 = pfl1;
    }

    public RichPanelFormLayout getPfl1() {
        return pfl1;
    }

    public void setOt_errores(RichOutputText ot1) {
        this.ot_errores = ot1;
    }

    public RichOutputText getOt_errores() {
        return ot_errores;
    }

    public void setIt_errores(RichInputText it1) {
        this.it_errores = it1;
    }

    public RichInputText getIt_errores() {
        return it_errores;
    }
    public void cmi_errores_actionListener(ActionEvent actionEvent) {
        if (t_predios1.getSelectedRowData()==null){
            showMessage(getText("seleccionar_registro"),
                        FacesMessage.SEVERITY_ERROR);
            return;
        }
        PrediosTO predio = (PrediosTO)t_predios1.getSelectedRowData();
        if(predio.isValid()){
            showMessage("El Predio " + predio.getNombre() + " no tiene errores de Validación", FacesMessage.SEVERITY_INFO);
            return;
        }
        this.it_errores.setValue(predio.getErroresValidacion());
        this.ot_errores.setValue( "Erroes de Validacion para el Predio " + 
                                  predio.getNombre());
        AdfFacesContext.getCurrentInstance().addPartialTarget(p_errores);
        showPopup(p_errores, true);
    }

    public void setM2(RichMenu m2) {
        this.m2 = m2;
    }

    public RichMenu getM2() {
        return m2;
    }

    public void setCmi2(RichCommandMenuItem cmi2) {
        this.cmi2 = cmi2;
    }

    public RichCommandMenuItem getCmi2() {
        return cmi2;
    }

    public void setIt_filtro_nombre(RichInputText inputText1) {
        this.it_filtro_nombre = inputText1;
    }

    public RichInputText getIt_filtro_nombre() {
        return it_filtro_nombre;
    }

    public void setIt_filtro_cedCatastral(RichInputText inputText2) {
        this.it_filtro_cedCatastral = inputText2;
    }

    public RichInputText getIt_filtro_cedCatastral() {
        return it_filtro_cedCatastral;
    }

    public void setSoc_filtro_dpto(RichSelectOneChoice selectOneChoice1) {
        this.soc_filtro_dpto = selectOneChoice1;
    }

    public RichSelectOneChoice getSoc_filtro_dpto() {
        return soc_filtro_dpto;
    }

    public void setSelectItems1(UISelectItems selectItems1) {
        this.selectItems1 = selectItems1;
    }

    public UISelectItems getSelectItems1() {
        return selectItems1;
    }

    public void setSoc_filtro_mun(RichSelectOneChoice selectOneChoice2) {
        this.soc_filtro_mun = selectOneChoice2;
    }
    public void soc_filtro_porAtoridad(ValueChangeEvent valueChangeEvent) throws IdeamException {
        Object autoridadSeleccionada = valueChangeEvent.getNewValue();
        buscar();
    }

    public RichSelectOneChoice getSoc_filtro_mun() {
        return soc_filtro_mun;
    }

    public void setSelectItems2(UISelectItems selectItems2) {
        this.selectItems2 = selectItems2;
    }

    public UISelectItems getSelectItems2() {
        return selectItems2;
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
    
    
    public void soc_filtro_depto_valueChangeListener(ValueChangeEvent valueChangeEvent) {
        Object depto = valueChangeEvent.getNewValue();
        this.listaMunicipios = new ArrayList();
        try{
            if(depto!=null){
                this.listaMunicipios = 
                    cargarDivipola(((Divipola)depto).getId());
            }
        }catch(IdeamException e){            
            showMessage(e);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(soc_filtro_mun);
    }

    public void setSoc_autoridad_id(RichSelectOneChoice soc_autoridad_id) {
        this.soc_autoridad_id = soc_autoridad_id;
    }

    public RichSelectOneChoice getSoc_autoridad_id() {
        return soc_autoridad_id;
    }

    public void setVisiblePerfil(String visiblePerfil) {
        this.visiblePerfil = visiblePerfil;
    }

    public String getVisiblePerfil() {
        return visiblePerfil;
    }

    public void setSi1Autoridad(UISelectItems si1Autoridad) {
        this.si1Autoridad = si1Autoridad;
    }

    public UISelectItems getSi1Autoridad() {
        return si1Autoridad;
    }

    public void setListaAutoridades(List listaAutoridades) {
        this.listaAutoridades = listaAutoridades;
    }

    public List getListaAutoridades() {
        return listaAutoridades;
    }
}
