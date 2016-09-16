package co.gov.ideam.sirh.directorio.view;

import co.gov.ideam.sirh.directorio.model.ActOrganizaciones;
import co.gov.ideam.sirh.directorio.model.ActOrganizacionesPropiedades;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Categoria;
import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.view.ParametrosDelegate;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
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
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectManyCheckbox;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.input.RichSelectOneRadio;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;

public class DetalleOrganizacionBean extends StandarBean{
    private RichForm f2;
    private RichDocument d2;
    private RichPanelStretchLayout psl1;
    private RichPanelGroupLayout pgl1;
    private RichPanelBox pb_detalle;
    private RichSpacer s1;
    private RichCommandLink cl1;
    private RichPanelFormLayout pfl1;
    private RichInputText it_nombre;
    
    private ActOrganizaciones organizacion;
    private RichSpacer s2;
    private RichInputText it_direccion;
    private RichInputText it_telefono;
    private List listaDepartamentos;
    private List listaMunicipios;
    private List listaNaturaleza;
    private List listaTipoOrganizacion;
    private List listaGestion;
    private List listaInvestigacion;
    private List listaAreas;
    private List listaTipoFuente;
    private List listaGestionSeleccionadas;
    private List listaInvestigacionSeleccionadas;
    private List listaAreasSeleccionadas;
    private List listaFuentesSeleccionadas;
    
    private RichSelectOneChoice soc_depto;
    private UISelectItems si1;
    private RichSelectOneChoice soc_municipio;
    private UISelectItems si2;
    private RichSelectOneRadio sor_naturaleza;
    private UISelectItems si3;
    private RichSelectOneRadio sor_tipo_org;
    private UISelectItems si4;
    private RichSpacer s3;
    private RichSpacer s4;
    private RichOutputText ot1;
    private RichSpacer s5;
    private RichSelectManyCheckbox smc_gestion;
    private UISelectItems si5;
    private RichPanelGroupLayout pgl2;
    private RichSelectManyCheckbox smc_investigacion;
    private UISelectItems si6;
    private RichInputText it_otro;
    private RichSelectManyCheckbox smc_areas;
    private UISelectItems si7;
    private RichSelectManyCheckbox smc_tipo_fuente;
    private UISelectItems si8;
    private RichInputText it_pagina_web;
    private RichPanelGroupLayout pgl3;
    private RichCommandButton cb_aceptar;
    private RichSpacer s6;
    private RichCommandButton cb_cancelar;
    private RichSpacer s7;
    private RichCommandButton cb_borrar;
    private RichSpacer s8;
    private String accion;
    private RichPanelGroupLayout pgl4;
    private RichPopup p_borrar;
    private RichDialog d_borrar;
    private RichOutputText ot2;
    private RichPanelGroupLayout pgl5;
    private RichCommandButton cb_si_borrar;
    private RichSpacer s9;
    private RichCommandButton cb_no_borrar;

    public DetalleOrganizacionBean(){
        FacesUtils.setActiveBean("detalleOrganizacion",
                                 "detalleOrganizacion",
                                 DirectorioDelegate.class);        
        this.load();
    }
    
    public void load(){
        try{
            ParametrosDelegate pd = ParametrosDelegate.getInstance();
            
            this.organizacion = 
                (ActOrganizaciones)FacesUtils.getFromSession("organizacionSeleccionada");
            
            listaDepartamentos = this.cargarDivipola(null);
            listaNaturaleza = this.cargarParametro( Categoria.NATURALEZA_ORGANIZACIONES );
            listaTipoOrganizacion = this.cargarParametro( Categoria.TIPO_ORGANIZACION );
            listaGestion = this.cargarParametro( Categoria.GESTION );
            listaInvestigacion = this.cargarParametro( Categoria.INVESTIGACION );
            listaAreas = this.cargarParametro( Categoria.AREAS_TRABAJO );
            listaTipoFuente = this.cargarParametro( Categoria.TIPO_FUENTE_HIDRICA );
            
            if(this.organizacion.getDivipolaDepto()!=null &&
               this.organizacion.getDivipolaMunicipio()!=null){
                this.listaMunicipios = this.cargarDivipola( this.organizacion.getDivipolaDepto().getId() );
            }
            
            this.listaGestionSeleccionadas =  new ArrayList();
            this.listaInvestigacionSeleccionadas =  new ArrayList();;
            this.listaAreasSeleccionadas =  new ArrayList();;
            this.listaFuentesSeleccionadas =  new ArrayList();;
            
            
            if(this.organizacion.getPropiedades() != null){
                Iterator<ActOrganizacionesPropiedades> it = 
                    this.organizacion.getPropiedades().iterator();
                while(it.hasNext()){
                    ActOrganizacionesPropiedades propiedad = it.next();
                    Lista lista = pd.getLista( propiedad.getIdLista().intValue() );
                    
                    if(propiedad.getIdCategoria().longValue() == Categoria.GESTION){
                        this.listaGestionSeleccionadas.add(lista);
                    }else if(propiedad.getIdCategoria().longValue() == Categoria.INVESTIGACION){
                        this.listaInvestigacionSeleccionadas.add(lista);
                    }else if(propiedad.getIdCategoria().longValue() == Categoria.AREAS_TRABAJO){
                        this.listaAreasSeleccionadas.add(lista);
                    }else if(propiedad.getIdCategoria().longValue() == Categoria.TIPO_FUENTE_HIDRICA){
                        this.listaFuentesSeleccionadas.add(lista);
                    }
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

    public void setPb_detalle(RichPanelBox pb1) {
        this.pb_detalle = pb1;
    }

    public RichPanelBox getPb_detalle() {
        return pb_detalle;
    }

    public void setS1(RichSpacer s1) {
        this.s1 = s1;
    }

    public RichSpacer getS1() {
        return s1;
    }

    public void setCl1(RichCommandLink cl1) {
        this.cl1 = cl1;
    }

    public RichCommandLink getCl1() {
        return cl1;
    }

    public void setPfl1(RichPanelFormLayout pfl1) {
        this.pfl1 = pfl1;
    }

    public RichPanelFormLayout getPfl1() {
        return pfl1;
    }

    public void setIt_nombre(RichInputText it1) {
        this.it_nombre = it1;
    }

    public RichInputText getIt_nombre() {
        return it_nombre;
    }

    public ActOrganizaciones getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(ActOrganizaciones organizacion) {
        this.organizacion = organizacion;
    }

    public void setS2(RichSpacer s2) {
        this.s2 = s2;
    }

    public RichSpacer getS2() {
        return s2;
    }

    public void setIt_direccion(RichInputText it1) {
        this.it_direccion = it1;
    }

    public RichInputText getIt_direccion() {
        return it_direccion;
    }

    public void setIt_telefono(RichInputText it2) {
        this.it_telefono = it2;
    }

    public RichInputText getIt_telefono() {
        return it_telefono;
    }

    public List getListaDepartamentos() {
        return listaDepartamentos;
    }

    public void setListaDepartamentos(List listaDepartamentos) {
        this.listaDepartamentos = listaDepartamentos;
    }

    public List getListaMunicipios() {
        return listaMunicipios;
    }

    public void setListaMunicipios(List listaMunicipios) {
        this.listaMunicipios = listaMunicipios;
    }

    public void setSoc_depto(RichSelectOneChoice soc1) {
        this.soc_depto = soc1;
    }

    public RichSelectOneChoice getSoc_depto() {
        return soc_depto;
    }

    public void setSi1(UISelectItems si1) {
        this.si1 = si1;
    }

    public UISelectItems getSi1() {
        return si1;
    }

    public void setSoc_municipio(RichSelectOneChoice soc2) {
        this.soc_municipio = soc2;
    }

    public RichSelectOneChoice getSoc_municipio() {
        return soc_municipio;
    }

    public void setSi2(UISelectItems si2) {
        this.si2 = si2;
    }

    public UISelectItems getSi2() {
        return si2;
    }

    public List getListaNaturaleza() {
        return listaNaturaleza;
    }

    public void setListaNaturaleza(List listaNaturaleza) {
        this.listaNaturaleza = listaNaturaleza;
    }

    public List getListaTipoOrganizacion() {
        return listaTipoOrganizacion;
    }

    public void setListaTipoOrganizacion(List listaTipoOrganizacion) {
        this.listaTipoOrganizacion = listaTipoOrganizacion;
    }

    public void setSor_naturaleza(RichSelectOneRadio sor1) {
        this.sor_naturaleza = sor1;
    }

    public RichSelectOneRadio getSor_naturaleza() {
        return sor_naturaleza;
    }

    public void setSi3(UISelectItems si3) {
        this.si3 = si3;
    }

    public UISelectItems getSi3() {
        return si3;
    }

    public void setSor_tipo_org(RichSelectOneRadio sor2) {
        this.sor_tipo_org = sor2;
    }

    public RichSelectOneRadio getSor_tipo_org() {
        return sor_tipo_org;
    }

    public void setSi4(UISelectItems si4) {
        this.si4 = si4;
    }

    public UISelectItems getSi4() {
        return si4;
    }

    public void setS3(RichSpacer s3) {
        this.s3 = s3;
    }

    public RichSpacer getS3() {
        return s3;
    }

    public void setS4(RichSpacer s4) {
        this.s4 = s4;
    }

    public RichSpacer getS4() {
        return s4;
    }

    public void setOt1(RichOutputText ot1) {
        this.ot1 = ot1;
    }

    public RichOutputText getOt1() {
        return ot1;
    }

    public void setS5(RichSpacer s5) {
        this.s5 = s5;
    }

    public RichSpacer getS5() {
        return s5;
    }

    public List getListaGestion() {
        return listaGestion;
    }

    public void setListaGestion(List listaGestion) {
        this.listaGestion = listaGestion;
    }

    public List getListaInvestigacion() {
        return listaInvestigacion;
    }

    public void setListaInvestigacion(List listaInvestigacion) {
        this.listaInvestigacion = listaInvestigacion;
    }

    public List getListaAreas() {
        return listaAreas;
    }

    public void setListaAreas(List listaAreas) {
        this.listaAreas = listaAreas;
    }

    public List getListaTipoFuente() {
        return listaTipoFuente;
    }

    public void setListaTipoFuente(List listaTipoFuente) {
        this.listaTipoFuente = listaTipoFuente;
    }

    public void setSmc_gestion(RichSelectManyCheckbox smc1) {
        this.smc_gestion = smc1;
    }

    public RichSelectManyCheckbox getSmc_gestion() {
        return smc_gestion;
    }

    public void setSi5(UISelectItems si5) {
        this.si5 = si5;
    }

    public UISelectItems getSi5() {
        return si5;
    }

    public void setPgl2(RichPanelGroupLayout pgl2) {
        this.pgl2 = pgl2;
    }

    public RichPanelGroupLayout getPgl2() {
        return pgl2;
    }

    public void setSmc_investigacion(RichSelectManyCheckbox smc2) {
        this.smc_investigacion = smc2;
    }

    public RichSelectManyCheckbox getSmc_investigacion() {
        return smc_investigacion;
    }

    public void setSi6(UISelectItems si6) {
        this.si6 = si6;
    }

    public UISelectItems getSi6() {
        return si6;
    }

    public void setIt_otro(RichInputText it1) {
        this.it_otro = it1;
    }

    public RichInputText getIt_otro() {
        return it_otro;
    }

    public void setSmc_areas(RichSelectManyCheckbox smc3) {
        this.smc_areas = smc3;
    }

    public RichSelectManyCheckbox getSmc_areas() {
        return smc_areas;
    }

    public void setSi7(UISelectItems si7) {
        this.si7 = si7;
    }

    public UISelectItems getSi7() {
        return si7;
    }

    public void setSmc_tipo_fuente(RichSelectManyCheckbox smc4) {
        this.smc_tipo_fuente = smc4;
    }

    public RichSelectManyCheckbox getSmc_tipo_fuente() {
        return smc_tipo_fuente;
    }

    public void setSi8(UISelectItems si8) {
        this.si8 = si8;
    }

    public UISelectItems getSi8() {
        return si8;
    }

    public void setIt_pagina_web(RichInputText it2) {
        this.it_pagina_web = it2;
    }

    public RichInputText getIt_pagina_web() {
        return it_pagina_web;
    }

    public void setPgl3(RichPanelGroupLayout pgl3) {
        this.pgl3 = pgl3;
    }

    public RichPanelGroupLayout getPgl3() {
        return pgl3;
    }

    public void setCb_aceptar(RichCommandButton cb1) {
        this.cb_aceptar = cb1;
    }

    public RichCommandButton getCb_aceptar() {
        return cb_aceptar;
    }

    public void setS6(RichSpacer s6) {
        this.s6 = s6;
    }

    public RichSpacer getS6() {
        return s6;
    }

    public void setCb_cancelar(RichCommandButton cb2) {
        this.cb_cancelar = cb2;
    }

    public RichCommandButton getCb_cancelar() {
        return cb_cancelar;
    }

    public void soc_depto_valueChangeListener(ValueChangeEvent valueChangeEvent) {
        this.listaMunicipios = new ArrayList();
        Divipola depto = (Divipola)valueChangeEvent.getNewValue();
        if(depto!=null){
            try{
                this.listaMunicipios = this.cargarDivipola( depto.getId() );
            }catch(IdeamException e){
                showMessage(e);
            }
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(soc_municipio);
    }

    public void setS7(RichSpacer s7) {
        this.s7 = s7;
    }

    public RichSpacer getS7() {
        return s7;
    }

    public void cb_aceptar_actionListener(ActionEvent actionEvent) {
        // Validar los datos
        boolean errores = false;
        if(it_nombre.getValue()==null || it_nombre.getValue().toString().length()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_nombre);
            errores = true;
        }
        if(it_direccion.getValue()==null || it_direccion.getValue().toString().length()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_direccion);
            errores = true;
        }
        if(it_telefono.getValue()==null || it_telefono.getValue().toString().length()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_telefono);
            errores = true;
        }
        if(soc_depto.getValue()==null){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,soc_depto);
            errores = true;
        }
        if(soc_municipio.getValue()==null){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,soc_municipio);
            errores = true;
        }
        if(sor_naturaleza.getValue()==null){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,sor_naturaleza);
            errores = true;
        }
        if(sor_tipo_org.getValue()==null){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,sor_tipo_org);
            errores = true;
        }
        if(smc_gestion.getValue()==null || ((ArrayList)smc_gestion.getValue()).size()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,smc_gestion);
            errores = true;            
        }

        if(smc_investigacion.getValue()==null || ((ArrayList)smc_investigacion.getValue()).size()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,smc_investigacion);
            errores = true;            
        }

        if(smc_areas.getValue()==null || ((ArrayList)smc_areas.getValue()).size()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,smc_areas);
            errores = true;            
        }

        if(smc_tipo_fuente.getValue()==null || ((ArrayList)smc_tipo_fuente.getValue()).size()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,smc_tipo_fuente);
            errores = true;            
        }
                
        if(errores){
            return;
        }
        try{
            DirectorioDelegate dd = DirectorioDelegate.getInstance();
            this.organizacion.setDepto( ((Divipola)soc_depto.getValue()).getId().longValue() );
            this.organizacion.setMunicipio( ((Divipola)soc_municipio.getValue()).getId().longValue() );
            this.organizacion.setNaturaleza( ((Lista)sor_naturaleza.getValue()).getCodigo().longValue() );
            this.organizacion.setTipoOrganizacion( ((Lista)sor_tipo_org.getValue()).getCodigo().longValue() );

            this.organizacion.setPropiedades( new ArrayList<ActOrganizacionesPropiedades>() );
            this.addPropiedad((ArrayList)smc_gestion.getValue(), Categoria.GESTION);
            this.addPropiedad((ArrayList)smc_investigacion.getValue(), Categoria.INVESTIGACION);
            this.addPropiedad((ArrayList)smc_areas.getValue(), Categoria.AREAS_TRABAJO);
            this.addPropiedad((ArrayList)smc_tipo_fuente.getValue(), Categoria.TIPO_FUENTE_HIDRICA);
            
            // Si esta seleccionado Gestion Otro se exige it_otro
            if (this.organizacion.getPropiedades()!=null){
                Iterator<ActOrganizacionesPropiedades> it = 
                    this.organizacion.getPropiedades().iterator();
                boolean contieneGestionOtro = false;
                while(it.hasNext()){
                    ActOrganizacionesPropiedades prop = it.next();
                    if (prop.getIdLista().longValue() == 687L){                        
                        contieneGestionOtro = true;
                        if(it_otro.getValue()==null || it_otro.getValue().toString().length()==0){
                            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_otro);
                            return;
                        }
                        break;
                    }
                }
                if(!contieneGestionOtro){
                    this.organizacion.setDetalleGestionOtro(null);
                }
            }
            ActOrganizaciones actualizada = 
                dd.updateOrganizacion( this.organizacion );
            this.organizacion.setId( actualizada.getId() );
            
            String params[] = {"de la Organización"};
            showMessage(getText("mensaje_registro_exitoso",
                                params), FacesMessage.SEVERITY_INFO);
            
            AdfFacesContext.getCurrentInstance().addPartialTarget(pb_detalle);
        }catch(IdeamException e){
            showMessage(e);
        }
    }
    
    private void addPropiedad(ArrayList lista, Long categoria){
        UsuarioConectadoTO usuarioConectado = 
            (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

        Autoridades autoridadAmbiental = usuarioConectado.getUsuario().getAutoridadAmbiental();
        
        Iterator it = lista.iterator();
        while(it.hasNext()){
            Lista data = (Lista)it.next();
            ActOrganizacionesPropiedades propiedad = 
                new ActOrganizacionesPropiedades();
            propiedad.setCodigoAutoridad( autoridadAmbiental.getId().longValue() );
            propiedad.setIdCategoria( categoria );
            propiedad.setIdLista( data.getCodigo().longValue() );
            this.organizacion.addPropiedad( propiedad );            
        }
    }

    public List getListaGestionSeleccionadas() {
        return listaGestionSeleccionadas;
    }

    public void setListaGestionSeleccionadas(List listaGestionSeleccionadas) {
        this.listaGestionSeleccionadas = listaGestionSeleccionadas;
    }

    public List getListaInvestigacionSeleccionadas() {
        return listaInvestigacionSeleccionadas;
    }

    public void setListaInvestigacionSeleccionadas(List listaInvestigacionSeleccionadas) {
        this.listaInvestigacionSeleccionadas = listaInvestigacionSeleccionadas;
    }

    public List getListaAreasSeleccionadas() {
        return listaAreasSeleccionadas;
    }

    public void setListaAreasSeleccionadas(List listaAreasSeleccionadas) {
        this.listaAreasSeleccionadas = listaAreasSeleccionadas;
    }

    public List getListaFuentesSeleccionadas() {
        return listaFuentesSeleccionadas;
    }

    public void setListaFuentesSeleccionadas(List listaFuentesSeleccionadas) {
        this.listaFuentesSeleccionadas = listaFuentesSeleccionadas;
    }

    public void setCb_borrar(RichCommandButton cb1) {
        this.cb_borrar = cb1;
    }

    public RichCommandButton getCb_borrar() {
        return cb_borrar;
    }

    public void setS8(RichSpacer s8) {
        this.s8 = s8;
    }

    public RichSpacer getS8() {
        return s8;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public void cb_borrar_actionListener(ActionEvent actionEvent) {
        showPopup( p_borrar, true );
    }

    public void setPgl4(RichPanelGroupLayout pgl4) {
        this.pgl4 = pgl4;
    }

    public RichPanelGroupLayout getPgl4() {
        return pgl4;
    }

    public void setP_borrar(RichPopup p1) {
        this.p_borrar = p1;
    }

    public RichPopup getP_borrar() {
        return p_borrar;
    }

    public void setD_borrar(RichDialog d1) {
        this.d_borrar = d1;
    }

    public RichDialog getD_borrar() {
        return d_borrar;
    }

    public void setOt2(RichOutputText ot2) {
        this.ot2 = ot2;
    }

    public RichOutputText getOt2() {
        return ot2;
    }

    public void setPgl5(RichPanelGroupLayout pgl5) {
        this.pgl5 = pgl5;
    }

    public RichPanelGroupLayout getPgl5() {
        return pgl5;
    }

    public void setCb_si_borrar(RichCommandButton cb1) {
        this.cb_si_borrar = cb1;
    }

    public RichCommandButton getCb_si_borrar() {
        return cb_si_borrar;
    }

    public void setS9(RichSpacer s9) {
        this.s9 = s9;
    }

    public RichSpacer getS9() {
        return s9;
    }

    public void setCb_no_borrar(RichCommandButton cb2) {
        this.cb_no_borrar = cb2;
    }

    public RichCommandButton getCb_no_borrar() {
        return cb_no_borrar;
    }

    public void cb_si_borrar_actionListener(ActionEvent actionEvent) {
        this.accion = "";
        try{
            DirectorioDelegate dd = DirectorioDelegate.getInstance();
            dd.deleteOrganizacion( this.organizacion );
            
            String params[] = {"de la Organización"};
            showMessage(getText("mensaje_registro_exitoso",
                                params), FacesMessage.SEVERITY_INFO);
            
            this.accion = "organizaciones";
        }catch(IdeamException e){
            showMessage(e);
        }
    }

    public void cb_no_borrar_actionListener(ActionEvent actionEvent) {
        showPopup(p_borrar, false);
    }
}
