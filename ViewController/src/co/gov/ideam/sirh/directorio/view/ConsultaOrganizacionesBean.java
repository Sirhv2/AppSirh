package co.gov.ideam.sirh.directorio.view;

import co.gov.ideam.sirh.directorio.model.CriterioBusquedaEspecialistaTO;
import co.gov.ideam.sirh.parametros.model.Categoria;
import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.parametros.model.Lista;
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

public class ConsultaOrganizacionesBean extends StandarBean{
    private RichForm f2;
    private RichDocument d2;
    private RichPanelStretchLayout psl2;
    private RichPanelGroupLayout pgl10;
    private RichPanelGroupLayout pgl11;
    private RichSpacer s6;
    private RichPanelGroupLayout pgl12;
    private RichOutputText ot3;
    private RichSpacer s7;
    private RichPanelSplitter ps3;
    private RichPanelBox pb5;
    private RichPanelGroupLayout pgl13;
    private RichSelectManyListbox sml_deptos;
    private UISelectItems si5;
    private RichPanelBox pb6;
    private RichPanelGroupLayout pgl14;
    private RichSelectManyListbox sml_municipios;
    private UISelectItems si6;
    private RichSpacer s8;
    private RichPanelSplitter ps4;
    private RichPanelBox pb7;
    private RichPanelGroupLayout pgl15;
    private RichSelectManyListbox sml_gestion;
    private UISelectItems si7;
    private RichPanelBox pb8;
    private RichPanelGroupLayout pgl16;
    private RichSelectManyListbox sml_investigacion;
    private UISelectItems si8;
    private RichSpacer s9;
    private RichPanelGroupLayout pgl17;
    private RichCommandButton cb_consultar;
    private RichSpacer s10;
    private RichCommandButton cb2;
    private RichPanelGroupLayout pgl18;
    private RichOutputText ot4;

    private List listaDepartamentos;
    private List listaMunicipios;
    private List listaGestion;
    private List listaInvestigacion;
    private String accion;
    private RichSpacer s1;
    private RichPanelGroupLayout pgl1;
    private RichInputText it_nombre;

    public ConsultaOrganizacionesBean(){
        FacesUtils.setActiveBean("consultaOrganizaciones",
                                 "consultaOrganizaciones",
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

    public void setPsl2(RichPanelStretchLayout psl2) {
        this.psl2 = psl2;
    }

    public RichPanelStretchLayout getPsl2() {
        return psl2;
    }

    public void setPgl10(RichPanelGroupLayout pgl10) {
        this.pgl10 = pgl10;
    }

    public RichPanelGroupLayout getPgl10() {
        return pgl10;
    }

    public void setPgl11(RichPanelGroupLayout pgl11) {
        this.pgl11 = pgl11;
    }

    public RichPanelGroupLayout getPgl11() {
        return pgl11;
    }

    public void setS6(RichSpacer s6) {
        this.s6 = s6;
    }

    public RichSpacer getS6() {
        return s6;
    }

    public void setPgl12(RichPanelGroupLayout pgl12) {
        this.pgl12 = pgl12;
    }

    public RichPanelGroupLayout getPgl12() {
        return pgl12;
    }

    public void setOt3(RichOutputText ot3) {
        this.ot3 = ot3;
    }

    public RichOutputText getOt3() {
        return ot3;
    }

    public void setS7(RichSpacer s7) {
        this.s7 = s7;
    }

    public RichSpacer getS7() {
        return s7;
    }

    public void setPs3(RichPanelSplitter ps3) {
        this.ps3 = ps3;
    }

    public RichPanelSplitter getPs3() {
        return ps3;
    }

    public void setPb5(RichPanelBox pb5) {
        this.pb5 = pb5;
    }

    public RichPanelBox getPb5() {
        return pb5;
    }

    public void setPgl13(RichPanelGroupLayout pgl13) {
        this.pgl13 = pgl13;
    }

    public RichPanelGroupLayout getPgl13() {
        return pgl13;
    }

    public void setSml_deptos(RichSelectManyListbox sml1) {
        this.sml_deptos = sml1;
    }

    public RichSelectManyListbox getSml_deptos() {
        return sml_deptos;
    }

    public void setSi5(UISelectItems si5) {
        this.si5 = si5;
    }

    public UISelectItems getSi5() {
        return si5;
    }

    public void setPb6(RichPanelBox pb6) {
        this.pb6 = pb6;
    }

    public RichPanelBox getPb6() {
        return pb6;
    }

    public void setPgl14(RichPanelGroupLayout pgl14) {
        this.pgl14 = pgl14;
    }

    public RichPanelGroupLayout getPgl14() {
        return pgl14;
    }

    public void setSml_municipios(RichSelectManyListbox sml2) {
        this.sml_municipios = sml2;
    }

    public RichSelectManyListbox getSml_municipios() {
        return sml_municipios;
    }

    public void setSi6(UISelectItems si6) {
        this.si6 = si6;
    }

    public UISelectItems getSi6() {
        return si6;
    }

    public void setS8(RichSpacer s8) {
        this.s8 = s8;
    }

    public RichSpacer getS8() {
        return s8;
    }

    public void setPs4(RichPanelSplitter ps4) {
        this.ps4 = ps4;
    }

    public RichPanelSplitter getPs4() {
        return ps4;
    }

    public void setPb7(RichPanelBox pb7) {
        this.pb7 = pb7;
    }

    public RichPanelBox getPb7() {
        return pb7;
    }

    public void setPgl15(RichPanelGroupLayout pgl15) {
        this.pgl15 = pgl15;
    }

    public RichPanelGroupLayout getPgl15() {
        return pgl15;
    }

    public void setSml_gestion(RichSelectManyListbox sml3) {
        this.sml_gestion = sml3;
    }

    public RichSelectManyListbox getSml_gestion() {
        return sml_gestion;
    }

    public void setSi7(UISelectItems si7) {
        this.si7 = si7;
    }

    public UISelectItems getSi7() {
        return si7;
    }

    public void setPb8(RichPanelBox pb8) {
        this.pb8 = pb8;
    }

    public RichPanelBox getPb8() {
        return pb8;
    }

    public void setPgl16(RichPanelGroupLayout pgl16) {
        this.pgl16 = pgl16;
    }

    public RichPanelGroupLayout getPgl16() {
        return pgl16;
    }

    public void setSml_investigacion(RichSelectManyListbox sml4) {
        this.sml_investigacion = sml4;
    }

    public RichSelectManyListbox getSml_investigacion() {
        return sml_investigacion;
    }

    public void setSi8(UISelectItems si8) {
        this.si8 = si8;
    }

    public UISelectItems getSi8() {
        return si8;
    }

    public void setS9(RichSpacer s9) {
        this.s9 = s9;
    }

    public RichSpacer getS9() {
        return s9;
    }

    public void setPgl17(RichPanelGroupLayout pgl17) {
        this.pgl17 = pgl17;
    }

    public RichPanelGroupLayout getPgl17() {
        return pgl17;
    }

    public void setCb_consultar(RichCommandButton cb1) {
        this.cb_consultar = cb1;
    }

    public RichCommandButton getCb_consultar() {
        return cb_consultar;
    }

    public void setS10(RichSpacer s10) {
        this.s10 = s10;
    }

    public RichSpacer getS10() {
        return s10;
    }

    public void setCb2(RichCommandButton cb2) {
        this.cb2 = cb2;
    }

    public RichCommandButton getCb2() {
        return cb2;
    }

    public void setPgl18(RichPanelGroupLayout pgl18) {
        this.pgl18 = pgl18;
    }

    public RichPanelGroupLayout getPgl18() {
        return pgl18;
    }

    public void setOt4(RichOutputText ot4) {
        this.ot4 = ot4;
    }

    public RichOutputText getOt4() {
        return ot4;
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

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
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

    public void cb_consultar_actionListener(ActionEvent actionEvent) {
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
            
            if (it_nombre.getValue()!=null){
                criterios.setNombre( it_nombre.getValue().toString() );
                totalCriterios++;
            }
            if (totalCriterios>1000){
                showMessage("Se permite máximo 1000 criterios de consulta",
                            FacesMessage.SEVERITY_ERROR);
                return;
            }
            
            DirectorioDelegate dd = DirectorioDelegate.getInstance();
            List lista = dd.getAllOrganizaciones(criterios);
            if (lista==null || lista.size()==0){
                showMessage("No existen registros que cumplan con los criterios de búsqueda seleccionados",
                            FacesMessage.SEVERITY_INFO);
                return;
            }
            FacesUtils.setInSession("filtroConsultaOrganizaciones", lista);
            accion = "resultadoConsultaOrganizaciones";
        }catch(IdeamException e){
            showMessage(e);
        }
    }

    public void setS1(RichSpacer s1) {
        this.s1 = s1;
    }

    public RichSpacer getS1() {
        return s1;
    }

    public void setPgl1(RichPanelGroupLayout pgl1) {
        this.pgl1 = pgl1;
    }

    public RichPanelGroupLayout getPgl1() {
        return pgl1;
    }

    public void setIt_nombre(RichInputText it1) {
        this.it_nombre = it1;
    }

    public RichInputText getIt_nombre() {
        return it_nombre;
    }
}
