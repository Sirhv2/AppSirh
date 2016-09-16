package co.gov.ideam.sirh.directorio.view;

import co.gov.ideam.sirh.directorio.model.CriterioBusquedaEspecialistaTO;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Categoria;
import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.parametros.model.Lista;
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

import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectManyListbox;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;

public class ConsultaBean extends StandarBean{
    private RichForm f2;
    private RichDocument d2;
    private RichPanelStretchLayout psl1;
    private RichPanelGroupLayout pgl2;
    private RichOutputText ot1;

    private List listaDepartamentos;
    private List listaMunicipios;
    private List listaGestion;
    private List listaInvestigacion;
    private String accion;
    
    private RichSelectManyListbox sml_deptos;
    private UISelectItems si2;
    private RichSelectManyListbox sml_municipios;
    private UISelectItems si1;
    private RichSelectManyListbox sml_investigacion;
    private UISelectItems si4;
    private RichSelectManyListbox sml_gestion;
    private UISelectItems si3;
    private RichPanelGroupLayout pgl3;
    private RichPanelSplitter ps1;
    private RichPanelSplitter ps2;
    private RichPanelBox pb1;
    private RichPanelBox pb2;
    private RichPanelBox pb3;
    private RichPanelBox pb4;
    private RichPanelGroupLayout pgl1;
    private RichPanelGroupLayout pgl4;
    private RichPanelGroupLayout pgl5;
    private RichPanelGroupLayout pgl6;
    private RichPanelGroupLayout pgl7;
    private RichSpacer s1;
    private RichSpacer s2;
    private RichPanelGroupLayout pgl8;
    private RichCommandButton cb_buscar;
    private RichSpacer s3;
    private RichCommandButton cb_reegresar;
    private RichPanelGroupLayout pgl9;
    private RichSpacer s4;
    private RichOutputText ot2;
    private RichSpacer s5;
    private RichPanelGroupLayout pgl10;
    private RichInputText it_nombre;
    private RichSpacer s6;

    public ConsultaBean(){
        FacesUtils.setActiveBean("consultaDirectorio",
                                 "consultaDirectorio",
                                 DirectorioDelegate.class);        
        this.load();
    }
    public void load(){
        try{
            this.setListaDepartamentos(this.cargarDivipola(null));
            this.setListaGestion(this.cargarParametro( Categoria.GESTION ));
            this.setListaInvestigacion(this.cargarParametro( Categoria.INVESTIGACION ));            
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

    public void setPgl2(RichPanelGroupLayout pgl2) {
        this.pgl2 = pgl2;
    }

    public RichPanelGroupLayout getPgl2() {
        return pgl2;
    }

    public void setOt1(RichOutputText ot1) {
        this.ot1 = ot1;
    }

    public RichOutputText getOt1() {
        return ot1;
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


    public void setSml_deptos(RichSelectManyListbox sml1) {
        this.sml_deptos = sml1;
    }

    public RichSelectManyListbox getSml_deptos() {
        return sml_deptos;
    }

    public void setSi2(UISelectItems si2) {
        this.si2 = si2;
    }

    public UISelectItems getSi2() {
        return si2;
    }

    public void setSml_municipios(RichSelectManyListbox sml2) {
        this.sml_municipios = sml2;
    }

    public RichSelectManyListbox getSml_municipios() {
        return sml_municipios;
    }

    public void setSi1(UISelectItems si1) {
        this.si1 = si1;
    }

    public UISelectItems getSi1() {
        return si1;
    }


    public void setSml_investigacion(RichSelectManyListbox sml3) {
        this.sml_investigacion = sml3;
    }

    public RichSelectManyListbox getSml_investigacion() {
        return sml_investigacion;
    }

    public void setSi4(UISelectItems si4) {
        this.si4 = si4;
    }

    public UISelectItems getSi4() {
        return si4;
    }


    public void setSml_gestion(RichSelectManyListbox sml4) {
        this.sml_gestion = sml4;
    }

    public RichSelectManyListbox getSml_gestion() {
        return sml_gestion;
    }

    public void setSi3(UISelectItems si3) {
        this.si3 = si3;
    }

    public UISelectItems getSi3() {
        return si3;
    }


    public void setPgl3(RichPanelGroupLayout pgl3) {
        this.pgl3 = pgl3;
    }

    public RichPanelGroupLayout getPgl3() {
        return pgl3;
    }

    public void setPs1(RichPanelSplitter ps1) {
        this.ps1 = ps1;
    }

    public RichPanelSplitter getPs1() {
        return ps1;
    }

    public void setPs2(RichPanelSplitter ps2) {
        this.ps2 = ps2;
    }

    public RichPanelSplitter getPs2() {
        return ps2;
    }

    public void setPb1(RichPanelBox pb1) {
        this.pb1 = pb1;
    }

    public RichPanelBox getPb1() {
        return pb1;
    }

    public void setPb2(RichPanelBox pb2) {
        this.pb2 = pb2;
    }

    public RichPanelBox getPb2() {
        return pb2;
    }

    public void setPb3(RichPanelBox pb3) {
        this.pb3 = pb3;
    }

    public RichPanelBox getPb3() {
        return pb3;
    }

    public void setPb4(RichPanelBox pb4) {
        this.pb4 = pb4;
    }

    public RichPanelBox getPb4() {
        return pb4;
    }

    public void setPgl1(RichPanelGroupLayout pgl1) {
        this.pgl1 = pgl1;
    }

    public RichPanelGroupLayout getPgl1() {
        return pgl1;
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

    public void setPgl6(RichPanelGroupLayout pgl6) {
        this.pgl6 = pgl6;
    }

    public RichPanelGroupLayout getPgl6() {
        return pgl6;
    }

    public void setPgl7(RichPanelGroupLayout pgl7) {
        this.pgl7 = pgl7;
    }

    public RichPanelGroupLayout getPgl7() {
        return pgl7;
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

    public void setPgl8(RichPanelGroupLayout pgl8) {
        this.pgl8 = pgl8;
    }

    public RichPanelGroupLayout getPgl8() {
        return pgl8;
    }

    public void setCb_buscar(RichCommandButton cb1) {
        this.cb_buscar = cb1;
    }

    public RichCommandButton getCb_buscar() {
        return cb_buscar;
    }

    public void setS3(RichSpacer s3) {
        this.s3 = s3;
    }

    public RichSpacer getS3() {
        return s3;
    }

    public void setCb_reegresar(RichCommandButton cb2) {
        this.cb_reegresar = cb2;
    }

    public RichCommandButton getCb_reegresar() {
        return cb_reegresar;
    }

    public void setPgl9(RichPanelGroupLayout pgl9) {
        this.pgl9 = pgl9;
    }

    public RichPanelGroupLayout getPgl9() {
        return pgl9;
    }

    public void setS4(RichSpacer s4) {
        this.s4 = s4;
    }

    public RichSpacer getS4() {
        return s4;
    }

    public void setOt2(RichOutputText ot2) {
        this.ot2 = ot2;
    }

    public RichOutputText getOt2() {
        return ot2;
    }

    public void setS5(RichSpacer s5) {
        this.s5 = s5;
    }

    public RichSpacer getS5() {
        return s5;
    }

    public void cb_buscar_actionListener(ActionEvent actionEvent) {
        accion = "";
        boolean existenfiltros = false;
        if(sml_deptos.getValue()!=null){
            existenfiltros = true;
        }
        if(sml_gestion.getValue()!=null){
            existenfiltros = true;
        }
        if(sml_investigacion.getValue()!=null){
            existenfiltros = true;
        }
        if(it_nombre.getValue()!=null && it_nombre.getValue().toString().length()>0){
            existenfiltros = true;
        }
        if(!existenfiltros){
            showMessage(getText("no_existen_criterios"),
                        FacesMessage.SEVERITY_ERROR);
            return;
        }
        try{
            CriterioBusquedaEspecialistaTO criterios = 
                new CriterioBusquedaEspecialistaTO();
            
            int totalCriterios = 0;
            
            List deptos = (List)sml_deptos.getValue();
            if(deptos!=null){
                Iterator<Divipola> itItem = deptos.iterator();
                while(itItem.hasNext()){                    
                    Divipola d = itItem.next();
                    criterios.addDepartamento( d );
                    totalCriterios++;
                }
            }
            
            List muns = (List)sml_municipios.getValue();
            if(muns!=null){
                Iterator<Divipola> itItem = muns.iterator();
                while(itItem.hasNext()){                    
                    Divipola d = itItem.next();
                    criterios.addMunicipio( d );
                    totalCriterios++;
                }
            }            
            
            List gest = (List)sml_gestion.getValue();
            if(gest!=null){
                Iterator<Lista> itItem = gest.iterator();
                while(itItem.hasNext()){                    
                    Lista d = itItem.next();
                    criterios.addGestion( d );
                    totalCriterios++;
                }
            }            

            List invs = (List)sml_investigacion.getValue();
            if(invs!=null){
                Iterator<Lista> itItem = invs.iterator();
                while(itItem.hasNext()){                    
                    Lista d = itItem.next();
                    criterios.addInvestigacion( d );
                    totalCriterios++;
                }
            }            
            
            if(it_nombre.getValue()!=null && it_nombre.getValue().toString().length()>0){
                criterios.setNombre( it_nombre.getValue().toString() );
                totalCriterios++;
            }
            
            if (totalCriterios>1000){
                showMessage("Se permite máximo 1000 criterios de consulta",
                            FacesMessage.SEVERITY_ERROR);
                return;
            }
            
            DirectorioDelegate dd = DirectorioDelegate.getInstance();
            List lista = dd.getAllEspecialistas(criterios);
            if (lista==null || lista.size()==0){
                showMessage("No existen registros que cumplan con los criterios de búsqueda seleccionados",
                            FacesMessage.SEVERITY_INFO);
                return;
            }
            FacesUtils.setInSession("filtroConsultaEspecialistas", lista);
            accion = "consultaEspecialistas";
        }catch(IdeamException e){
            showMessage(e);
        }
    }

    public void sml_deptos_valueChangeListener(ValueChangeEvent valueChangeEvent) {
        this.listaMunicipios = new ArrayList();
        
        try{
            if(valueChangeEvent.getNewValue()!=null){                
                ArrayList listaItems = (ArrayList)valueChangeEvent.getNewValue();
                Iterator<Divipola> itItem = listaItems.iterator();
                while(itItem.hasNext()){                    
                    Divipola d = (Divipola)itItem.next();
                    List listaM = this.cargarDivipola( d.getId() );
                    if(listaM!=null){
                        Iterator<SelectItem> itMun = listaM.iterator();
                        while(itMun.hasNext()){
                            SelectItem item = itMun.next();                            
                            Divipola m = (Divipola)item.getValue();
                            item.setLabel(d.getNombre() + " - "  + m.getNombre());                            
                            this.listaMunicipios.add(item);
                        }
                    }
                }
            }
        }catch(IdeamException e){
            showMessage(e);
        }        
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(sml_municipios);
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public void setPgl10(RichPanelGroupLayout pgl10) {
        this.pgl10 = pgl10;
    }

    public RichPanelGroupLayout getPgl10() {
        return pgl10;
    }

    public void setIt_nombre(RichInputText it1) {
        this.it_nombre = it1;
    }

    public RichInputText getIt_nombre() {
        return it_nombre;
    }

    public void setS6(RichSpacer s6) {
        this.s6 = s6;
    }

    public RichSpacer getS6() {
        return s6;
    }
}
