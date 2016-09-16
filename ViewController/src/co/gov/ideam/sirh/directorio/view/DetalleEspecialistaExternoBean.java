package co.gov.ideam.sirh.directorio.view;

import co.gov.ideam.sirh.calidad.model.PuntoMonitoreo;
import co.gov.ideam.sirh.directorio.model.ActEspecialistaFormacion;
import co.gov.ideam.sirh.directorio.model.ActEspecialistas;
import co.gov.ideam.sirh.directorio.model.ActEspecilistasPropiedades;
import co.gov.ideam.sirh.directorio.model.ActOrganizaciones;
import co.gov.ideam.sirh.directorio.model.ActOrganizacionesPropiedades;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Categoria;
import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.view.ParametrosDelegate;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.util.Constantes;
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
import javax.faces.model.SelectItem;

import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichMenu;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectItem;
import oracle.adf.view.rich.component.rich.input.RichSelectManyCheckbox;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.input.RichSelectOneRadio;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.nav.RichCommandMenuItem;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;

public class DetalleEspecialistaExternoBean extends StandarBean{
    private RichForm f2;
    private RichDocument d2;
    private RichPanelStretchLayout psl1;
    private RichPanelGroupLayout pgl2;
    private RichSpacer s2;
    private RichCommandLink cl2;
    private RichPanelBox pb1;
    private RichPanelGroupLayout pgl1;
    private RichPanelFormLayout pfl1;
    private RichSpacer s1;
    private RichSelectOneChoice soc_tipo_id;
    private UISelectItems si1;
    private RichInputText it_numero_id;
    private RichInputText it_nombre;
    private RichInputText it_direccion;
    private RichSelectOneChoice soc_depto;
    private UISelectItems si2;
    private RichSelectOneChoice soc_municipio;
    private UISelectItems si3;
    private RichInputText it_pagina;
    private RichInputText it_email;
    private RichSelectOneChoice soc_organizacion;
    private UISelectItems si4;
    private RichSelectOneRadio sor_red;
    private RichSelectItem si_organizacion;
    private RichSelectItem si_independiente;
   
    private List listaTipoId;
    private List listaDepartamentos;
    private List listaMunicipios;
    private List listaOrganizaciones;
    private ActEspecialistas especialista;
    private List listaGestion;
    private List listaInvestigacion;
    private List listaAreas;
    private List listaTipoFuente;
    private List listaGestionSeleccionadas;
    private List listaInvestigacionSeleccionadas;
    private List listaAreasSeleccionadas;
    private List listaFuentesSeleccionadas;
    private List<ActEspecialistaFormacion> listaFormacion;
    private List listaFormacionesTotales;
    private ActEspecialistaFormacion formacionSeleccionada;
    
    private RichInputText it_telefono;
    private RichPanelGroupLayout pgl3;
  
    private RichSpacer s3;
 
    private RichSpacer s4;
    private RichOutputText ot2;
    private RichSelectManyCheckbox smc_gestion;
    private UISelectItems si7;
    private RichInputText it_otro;
    private RichSelectManyCheckbox smc_investigacion;
    private UISelectItems si8;
    private RichSelectManyCheckbox smc_areas;
    private UISelectItems si9;
    private RichSelectManyCheckbox smc_tipo_fuente;
    private UISelectItems si10;
    private String redEspecialista;
   // private RichPanelCollection pc1;
    private RichTable t_formacion;
 
    private RichPanelGroupLayout pgl4;
    private RichPopup p_formacion;
    private RichDialog d_formacion;
    private RichSelectOneChoice soc_nivel_formacion;
    private UISelectItems si5;
    private RichInputText it_titulo;
    private RichPanelFormLayout pfl2;
    
    private RichSpacer s5;
    private RichOutputText ot4;

    private RichSpacer s6;
 
 
    private String accion;
   
   
   
    public DetalleEspecialistaExternoBean(){
        FacesUtils.setActiveBean("detalleEspecialistaExterno",
                                 "detalleEspecialistaExterno",
                                 DirectorioDelegate.class);        
        this.load();
    }
    
    public void load(){
        try{
            DirectorioDelegate dd = DirectorioDelegate.getInstance();
            ParametrosDelegate pd = ParametrosDelegate.getInstance();
            Object obj = FacesUtils.getFromSession("especialistaSeleccionado");
            
            if(obj instanceof ActEspecialistas){
                   this.especialista = (ActEspecialistas)obj;
                  Long codigo=  this.especialista.getId();
                  
                System.out.println("espe----hhhhhhhhhh--------id------ZZZZ"+   codigo);
         
                
                 
                   this.especialista = dd.getEspecialistaId(codigo);  
            }
            System.out.println("espe------------id------ZZZZ"+   this.especialista.getId());
            System.out.println("espe----------nomm-------ZZZZ"+   this.especialista.getNombre());
                 
         System.out.println("espe------------------ZZZZ"+   this.especialista.getDivipolaDepartamento().getId());
            listaTipoId = this.cargarParametro( Categoria.TIPO_DOCUMENTO );
            listaDepartamentos = this.cargarDivipola( null );
            
            this.listaOrganizaciones = new ArrayList();
            List<ActOrganizaciones> lista = dd.getAllOrganizaciones();
            Iterator<ActOrganizaciones> it = lista.iterator();
            while(it.hasNext()){
                ActOrganizaciones org = it.next();
                SelectItem item = new SelectItem(org,org.getNombre());
                this.listaOrganizaciones.add(item);
            }
            
            listaGestion = this.cargarParametro( Categoria.GESTION );
            listaInvestigacion = this.cargarParametro( Categoria.INVESTIGACION );
            listaAreas = this.cargarParametro( Categoria.AREAS_TRABAJO );
            listaTipoFuente = this.cargarParametro( Categoria.TIPO_FUENTE_HIDRICA );
            
            if(this.especialista.getDivipolaDepartamento().getId()!=null &&
               this.especialista.getDivipolaMunicipio().getId()!=null){
                this.listaMunicipios = this.cargarDivipola( this.especialista.getDivipolaDepartamento().getId() );
            }           
            this.listaGestionSeleccionadas =  new ArrayList();
            this.listaInvestigacionSeleccionadas =  new ArrayList();
            this.listaAreasSeleccionadas =  new ArrayList();
            this.listaFuentesSeleccionadas =  new ArrayList();
            
            if(this.especialista.getPropiedades() != null){
                Iterator<ActEspecilistasPropiedades> itP = 
                    this.especialista.getPropiedades().iterator();
                while(itP.hasNext()){
                    ActEspecilistasPropiedades propiedad = itP.next();
                    Lista listaP = pd.getLista( propiedad.getIdLista().intValue() );
                    
                    if(propiedad.getIdCategoria().longValue() == Categoria.GESTION){
                        this.listaGestionSeleccionadas.add(listaP);
                    }else if(propiedad.getIdCategoria().longValue() == Categoria.INVESTIGACION){
                        this.listaInvestigacionSeleccionadas.add(listaP);
                    }else if(propiedad.getIdCategoria().longValue() == Categoria.AREAS_TRABAJO){
                        this.listaAreasSeleccionadas.add(listaP);
                    }else if(propiedad.getIdCategoria().longValue() == Categoria.TIPO_FUENTE_HIDRICA){
                        this.listaFuentesSeleccionadas.add(listaP);
                    }
                }
            } 
            
            if(this.especialista.getOrganizacion()!=null){
                redEspecialista = "1";
            }else{
                redEspecialista = "2";
            }

            setListaFormacion(dd.getAllFormacion(this.especialista));
            listaFormacionesTotales = this.cargarParametro( Categoria.NIVEL_FORMACION );
            
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


    public void setPb1(RichPanelBox pb1) {
        this.pb1 = pb1;
    }

    public RichPanelBox getPb1() {
        return pb1;
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

    public void setS1(RichSpacer s1) {
        this.s1 = s1;
    }

    public RichSpacer getS1() {
        return s1;
    }

    public void setSoc_tipo_id(RichSelectOneChoice soc1) {
        this.soc_tipo_id = soc1;
    }

    public RichSelectOneChoice getSoc_tipo_id() {
        return soc_tipo_id;
    }

    public void setSi1(UISelectItems si1) {
        this.si1 = si1;
    }

    public UISelectItems getSi1() {
        return si1;
    }

    public void setIt_numero_id(RichInputText it1) {
        this.it_numero_id = it1;
    }

    public RichInputText getIt_numero_id() {
        return it_numero_id;
    }

    public void setIt_nombre(RichInputText it2) {
        this.it_nombre = it2;
    }

    public RichInputText getIt_nombre() {
        return it_nombre;
    }

    public void setIt_direccion(RichInputText it3) {
        this.it_direccion = it3;
    }

    public RichInputText getIt_direccion() {
        return it_direccion;
    }

    public void setSoc_depto(RichSelectOneChoice soc2) {
        this.soc_depto = soc2;
    }

    public RichSelectOneChoice getSoc_depto() {
        return soc_depto;
    }

    public void setSi2(UISelectItems si2) {
        this.si2 = si2;
    }

    public UISelectItems getSi2() {
        return si2;
    }

    public void setSoc_municipio(RichSelectOneChoice soc3) {
        this.soc_municipio = soc3;
    }

    public RichSelectOneChoice getSoc_municipio() {
        return soc_municipio;
    }

    public void setSi3(UISelectItems si3) {
        this.si3 = si3;
    }

    public UISelectItems getSi3() {
        return si3;
    }

    public void setIt_pagina(RichInputText it4) {
        this.it_pagina = it4;
    }

    public RichInputText getIt_pagina() {
        return it_pagina;
    }

    public void setIt_email(RichInputText it5) {
        this.it_email = it5;
    }

    public RichInputText getIt_email() {
        return it_email;
    }

    public void setSoc_organizacion(RichSelectOneChoice soc4) {
        this.soc_organizacion = soc4;
    }

    public RichSelectOneChoice getSoc_organizacion() {
        return soc_organizacion;
    }

    public void setSi4(UISelectItems si4) {
        this.si4 = si4;
    }

    public UISelectItems getSi4() {
        return si4;
    }

    public void setSor_red(RichSelectOneRadio sor1) {
        this.sor_red = sor1;
    }

    public RichSelectOneRadio getSor_red() {
        return sor_red;
    }

    public void setSi_organizacion(RichSelectItem si5) {
        this.si_organizacion = si5;
    }

    public RichSelectItem getSi_organizacion() {
        return si_organizacion;
    }

    public void setSi_independiente(RichSelectItem si6) {
        this.si_independiente = si6;
    }

    public RichSelectItem getSi_independiente() {
        return si_independiente;
    }

    public List getListaTipoId() {
        return listaTipoId;
    }

    public void setListaTipoId(List listaTipoId) {
        this.listaTipoId = listaTipoId;
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

    public List getListaOrganizaciones() {
        return listaOrganizaciones;
    }

    public void setListaOrganizaciones(List listaOrganizaciones) {
        this.listaOrganizaciones = listaOrganizaciones;
    }

    public ActEspecialistas getEspecialista() {
        return especialista;
    }

    public void setEspecialista(ActEspecialistas especialista) {
        this.especialista = especialista;
    }

    public void soc_depto_valueChangeListener(ValueChangeEvent valueChangeEvent) {
        Divipola depto = (Divipola)valueChangeEvent.getNewValue();
        this.listaMunicipios = new ArrayList();
        if(depto!=null){
            try{
                this.listaMunicipios = this.cargarDivipola( depto.getId() );
            }catch(IdeamException e){
                showMessage(e);
            }
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(soc_municipio);
    }



    public void setIt_telefono(RichInputText it1) {
        this.it_telefono = it1;
    }

    public RichInputText getIt_telefono() {
        return it_telefono;
    }

    public void setPgl3(RichPanelGroupLayout pgl3) {
        this.pgl3 = pgl3;
    }

    public RichPanelGroupLayout getPgl3() {
        return pgl3;
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

    public void setOt2(RichOutputText ot2) {
        this.ot2 = ot2;
    }

    public RichOutputText getOt2() {
        return ot2;
    }

    public void setSmc_gestion(RichSelectManyCheckbox smc1) {
        this.smc_gestion = smc1;
    }

    public RichSelectManyCheckbox getSmc_gestion() {
        return smc_gestion;
    }

    public void setSi7(UISelectItems si7) {
        this.si7 = si7;
    }

    public UISelectItems getSi7() {
        return si7;
    }

    public void setIt_otro(RichInputText it1) {
        this.it_otro = it1;
    }

    public RichInputText getIt_otro() {
        return it_otro;
    }

    public void setSmc_investigacion(RichSelectManyCheckbox smc2) {
        this.smc_investigacion = smc2;
    }

    public RichSelectManyCheckbox getSmc_investigacion() {
        return smc_investigacion;
    }

    public void setSi8(UISelectItems si8) {
        this.si8 = si8;
    }

    public UISelectItems getSi8() {
        return si8;
    }

    public void setSmc_areas(RichSelectManyCheckbox smc3) {
        this.smc_areas = smc3;
    }

    public RichSelectManyCheckbox getSmc_areas() {
        return smc_areas;
    }

    public void setSi9(UISelectItems si9) {
        this.si9 = si9;
    }

    public UISelectItems getSi9() {
        return si9;
    }

    public void setSmc_tipo_fuente(RichSelectManyCheckbox smc4) {
        this.smc_tipo_fuente = smc4;
    }

    public RichSelectManyCheckbox getSmc_tipo_fuente() {
        return smc_tipo_fuente;
    }

    public void setSi10(UISelectItems si10) {
        this.si10 = si10;
    }

    public UISelectItems getSi10() {
        return si10;
    }

    public String getRedEspecialista() {
        return redEspecialista;
    }

    public void setRedEspecialista(String redEspecialista) {
        this.redEspecialista = redEspecialista;
    }




    public List<ActEspecialistaFormacion> getListaFormacion() {
        return listaFormacion;
    }

    public void setListaFormacion(List<ActEspecialistaFormacion> listaFormacion) {
        this.listaFormacion = listaFormacion;
    }

    public void setT_formacion(RichTable t1) {
        this.t_formacion = t1;
    }

    public RichTable getT_formacion() {
        return t_formacion;
    }




    public void setPgl4(RichPanelGroupLayout pgl4) {
        this.pgl4 = pgl4;
    }

    public RichPanelGroupLayout getPgl4() {
        return pgl4;
    }

    public void setP_formacion(RichPopup p1) {
        this.p_formacion = p1;
    }

    public RichPopup getP_formacion() {
        return p_formacion;
    }

    public void setD_formacion(RichDialog d1) {
        this.d_formacion = d1;
    }

    public RichDialog getD_formacion() {
        return d_formacion;
    }

    public List getListaFormacionesTotales() {
        return listaFormacionesTotales;
    }

    public void setListaFormacionesTotales(List listaFormacionesTotales) {
        this.listaFormacionesTotales = listaFormacionesTotales;
    }

    public void setSoc_nivel_formacion(RichSelectOneChoice soc1) {
        this.soc_nivel_formacion = soc1;
    }

    public RichSelectOneChoice getSoc_nivel_formacion() {
        return soc_nivel_formacion;
    }

    public void setSi5(UISelectItems si5) {
        this.si5 = si5;
    }

    public UISelectItems getSi5() {
        return si5;
    }

    public void setIt_titulo(RichInputText it1) {
        this.it_titulo = it1;
    }

    public RichInputText getIt_titulo() {
        return it_titulo;
    }

    public void setPfl2(RichPanelFormLayout pfl2) {
        this.pfl2 = pfl2;
    }

    public RichPanelFormLayout getPfl2() {
        return pfl2;
    }

    public ActEspecialistaFormacion getFormacionSeleccionada() {
        return formacionSeleccionada;
    }

    public void setFormacionSeleccionada(ActEspecialistaFormacion formacionSeleccionada) {
        this.formacionSeleccionada = formacionSeleccionada;
    }

 


  

    public void d_formacion_dialogListener(DialogEvent dialogEvent) {
        boolean errores = false;
        if(soc_nivel_formacion.getValue()==null){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,soc_nivel_formacion);
            errores = true;            
        }
        if(it_titulo.getValue()==null || it_titulo.getValue().toString().length()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_titulo);
            errores = true;                        
        }
        if (errores){
            return;
        }
        if (!this.listaFormacion.contains(this.formacionSeleccionada)){
            this.listaFormacion.add(this.formacionSeleccionada);            
        }
        //this.formacionSeleccionada = null;
        AdfFacesContext.getCurrentInstance().addPartialTarget(t_formacion);
    }

    public void setS5(RichSpacer s5) {
        this.s5 = s5;
    }

    public RichSpacer getS5() {
        return s5;
    }

    public void setOt4(RichOutputText ot4) {
        this.ot4 = ot4;
    }

    public RichOutputText getOt4() {
        return ot4;
    }



    public void setS6(RichSpacer s6) {
        this.s6 = s6;
    }

    public RichSpacer getS6() {
        return s6;
    }





 
    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }


    public void setPgl2(RichPanelGroupLayout pgl2) {
        this.pgl2 = pgl2;
    }

    public RichPanelGroupLayout getPgl2() {
        return pgl2;
    }

    public void setCl2(RichCommandLink cl2) {
        this.cl2 = cl2;
    }

    public RichCommandLink getCl2() {
        return cl2;
    }

    public void setS2(RichSpacer s2) {
        this.s2 = s2;
    }

    public RichSpacer getS2() {
        return s2;
    }
}
