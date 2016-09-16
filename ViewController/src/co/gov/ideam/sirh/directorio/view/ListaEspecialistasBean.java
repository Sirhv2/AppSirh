package co.gov.ideam.sirh.directorio.view;

import co.gov.ideam.sirh.directorio.model.ActEspecialistas;
import co.gov.ideam.sirh.directorio.model.ActOrganizaciones;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Categoria;
import co.gov.ideam.sirh.parametros.model.Divipola;
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

import javax.faces.model.SelectItem;

import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichMenu;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectManyShuttle;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichCommandMenuItem;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;

public class ListaEspecialistasBean extends StandarBean{
    private RichForm f2;
    private RichDocument d2;
    private RichPanelStretchLayout psl1;
    private RichPanelGroupLayout pgl1;
    private RichOutputText ot2;
    private RichPanelSplitter ps1;
    private RichPanelCollection pc1;
    private RichMenu m_arvchivo;
    private RichCommandMenuItem cmi_adicionar;
    private RichCommandMenuItem cmi_consultar;
    
    private List<ActEspecialistas> listaEspecialistas;
    private List listaDepartamentos;
    private List listaMunicipios;
    private List listaGestion;
    private List listaInvestigacion;
    private String textoFiltroDepto = "Ningún Departamento Seleccionado para filtrar";
    private String textofiltroMun = "Ningún Municipio Seleccionado para filtrar";
    private String textoFiltroGestion = "Nunguna Gestión Seleccionada para Filtrar";
    private String textoFiltroInvestigacion = "Nunguna Investigación Seleccionada para Filtrar";    
    
    private RichTable t_especialistas;
    private String accion;
    private RichPanelFormLayout pfl_filtros;
    private RichInputText it_nombre;
    private RichPanelGroupLayout pgl2;
    private RichPopup p_deptos;
    private RichDialog d_deptos;
    private RichSelectManyShuttle sms_deptos;
    private UISelectItems si1;
    private RichCommandButton cb_filtro_depto;
    private RichOutputText ot_filtro_depto;
    private RichPanelGroupLayout pgl3;
    private RichSpacer s1;
    private RichPanelGroupLayout pgl4;
    private RichOutputText ot_filtro_munic;
    private RichSpacer s2;
    private RichCommandButton cb_filtro_mun;
    private RichPopup p_mun;
    private RichDialog d_mun;
    private RichSelectManyShuttle sms_mun;
    private UISelectItems si2;
    private RichPanelGroupLayout pgl5;
    private RichOutputText ot11;
    private RichSpacer s3;
    private RichCommandButton cb_filtro_gestion;
    private RichPopup p_gestion;
    private RichDialog d_gestion;
    private RichSelectManyShuttle sms_gestion;
    private UISelectItems si3;
    private RichPanelGroupLayout pgl6;
    private RichOutputText ot12;
    private RichSpacer s4;
    private RichCommandButton cb_filtro_inv;
    private RichPopup p_inv;
    private RichDialog d_inv;
    private RichSelectManyShuttle sms_inv;
    private UISelectItems si4;
    private RichPanelGroupLayout pgl7;
    private RichCommandButton cb_buscar;
    private RichSpacer s5;

    public ListaEspecialistasBean(){
        FacesUtils.setActiveBean("especialistas",
                                 "especialistas",
                                 DirectorioDelegate.class);        
        this.load();
    }
        
    public void load(){
        try{
                                    
            DirectorioDelegate dd = DirectorioDelegate.getInstance();
            if (FacesUtils.getFromSession("filtroEspecialistas")!=null){
                this.listaEspecialistas = 
                    (List<ActEspecialistas>)FacesUtils.getFromSession("filtroEspecialistas");
                FacesUtils.removeFromSession("filtroEspecialistas");
            }else{
                UsuarioConectadoTO usuarioConectado = 
                    (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

                Autoridades autoridadAmbiental = usuarioConectado.getUsuario().getAutoridadAmbiental();
                
                if(autoridadAmbiental==null ||
                   autoridadAmbiental.getId().intValue() == Constantes.IDEAM){
                    showMessage(getText("NO_EXISTE_AUTORIDAD_AMBIENTAL"),
                                FacesMessage.SEVERITY_ERROR);       
                    return;
                }
                
                
                this.listaEspecialistas = dd.getAllEsoecialistas( autoridadAmbiental );
            }
            this.listaDepartamentos = this.cargarDivipola(null);
            this.listaGestion = this.cargarParametro( Categoria.GESTION );
            this.listaInvestigacion = this.cargarParametro( Categoria.INVESTIGACION );
            
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

    public void setOt2(RichOutputText ot2) {
        this.ot2 = ot2;
    }

    public RichOutputText getOt2() {
        return ot2;
    }

    public void setPs1(RichPanelSplitter ps1) {
        this.ps1 = ps1;
    }

    public RichPanelSplitter getPs1() {
        return ps1;
    }

    public void setPc1(RichPanelCollection pc1) {
        this.pc1 = pc1;
    }

    public RichPanelCollection getPc1() {
        return pc1;
    }

    public void setM_arvchivo(RichMenu m2) {
        this.m_arvchivo = m2;
    }

    public RichMenu getM_arvchivo() {
        return m_arvchivo;
    }

    public void setCmi_adicionar(RichCommandMenuItem cmi2) {
        this.cmi_adicionar = cmi2;
    }

    public RichCommandMenuItem getCmi_adicionar() {
        return cmi_adicionar;
    }

    public void setCmi_consultar(RichCommandMenuItem cmi3) {
        this.cmi_consultar = cmi3;
    }

    public RichCommandMenuItem getCmi_consultar() {
        return cmi_consultar;
    }

    public List<ActEspecialistas> getListaEspecialistas() {
        return listaEspecialistas;
    }

    public void setListaEspecialistas(List<ActEspecialistas> listaEspecialistas) {
        this.listaEspecialistas = listaEspecialistas;
    }

    public void setT_especialistas(RichTable t1) {
        this.t_especialistas = t1;
    }

    public RichTable getT_especialistas() {
        return t_especialistas;
    }

    public void cmi_adicionar_actionListener(ActionEvent actionEvent) {
        UsuarioConectadoTO usuarioConectado = 
            (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

        Autoridades autoridadAmbiental = usuarioConectado.getUsuario().getAutoridadAmbiental();
        
        ActEspecialistas especialista = new ActEspecialistas();
        especialista.setIdAutoridad( autoridadAmbiental.getId().longValue() );        
        
        FacesUtils.setInSession("especialistaSeleccionado", especialista);
    }

    public void cmi_consultar_actionListener(ActionEvent actionEvent) {
        setAccion("");
        if(t_especialistas.getSelectedRowData()==null){
            showMessage(getText("seleccionar_registro"),
                        FacesMessage.SEVERITY_ERROR);            
            return;
        }
        ActEspecialistas especialista = 
            (ActEspecialistas)t_especialistas.getSelectedRowData();
        FacesUtils.setInSession("especialistaSeleccionado", especialista);
        setAccion("detalleEspecialista");
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public void setPfl_filtros(RichPanelFormLayout pfl1) {
        this.pfl_filtros = pfl1;
    }

    public RichPanelFormLayout getPfl_filtros() {
        return pfl_filtros;
    }

    public void setIt_nombre(RichInputText it1) {
        this.it_nombre = it1;
    }

    public RichInputText getIt_nombre() {
        return it_nombre;
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


    public void setPgl2(RichPanelGroupLayout pgl2) {
        this.pgl2 = pgl2;
    }

    public RichPanelGroupLayout getPgl2() {
        return pgl2;
    }

    public void setP_deptos(RichPopup p1) {
        this.p_deptos = p1;
    }

    public RichPopup getP_deptos() {
        return p_deptos;
    }

    public void setD_deptos(RichDialog d1) {
        this.d_deptos = d1;
    }

    public RichDialog getD_deptos() {
        return d_deptos;
    }

    public void setSms_deptos(RichSelectManyShuttle sms1) {
        this.sms_deptos = sms1;
    }

    public RichSelectManyShuttle getSms_deptos() {
        return sms_deptos;
    }

    public void setSi1(UISelectItems si1) {
        this.si1 = si1;
    }

    public UISelectItems getSi1() {
        return si1;
    }

    public void setCb_filtro_depto(RichCommandButton cb1) {
        this.cb_filtro_depto = cb1;
    }

    public RichCommandButton getCb_filtro_depto() {
        return cb_filtro_depto;
    }

    public void setOt_filtro_depto(RichOutputText ot11) {
        this.ot_filtro_depto = ot11;
    }

    public RichOutputText getOt_filtro_depto() {
        return ot_filtro_depto;
    }

    public void setPgl3(RichPanelGroupLayout pgl3) {
        this.pgl3 = pgl3;
    }

    public RichPanelGroupLayout getPgl3() {
        return pgl3;
    }

    public void setS1(RichSpacer s1) {
        this.s1 = s1;
    }

    public RichSpacer getS1() {
        return s1;
    }

    public void setPgl4(RichPanelGroupLayout pgl4) {
        this.pgl4 = pgl4;
    }

    public RichPanelGroupLayout getPgl4() {
        return pgl4;
    }

    public void setOt_filtro_munic(RichOutputText ot11) {
        this.ot_filtro_munic = ot11;
    }

    public RichOutputText getOt_filtro_munic() {
        return ot_filtro_munic;
    }

    public void setS2(RichSpacer s2) {
        this.s2 = s2;
    }

    public RichSpacer getS2() {
        return s2;
    }

    public void setCb_filtro_mun(RichCommandButton cb1) {
        this.cb_filtro_mun = cb1;
    }

    public RichCommandButton getCb_filtro_mun() {
        return cb_filtro_mun;
    }

    public void d_deptos_dialogListener(DialogEvent dialogEvent) {
        this.listaMunicipios = new ArrayList();
        try{
            if(sms_deptos.getValue()!=null){                
                ArrayList listaItems = (ArrayList)sms_deptos.getValue();
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
                textoFiltroDepto = "" + listaItems.size() + " Departamentos Seleccionados para filtrar";
                textofiltroMun= "Ningún Municipio Seleccionado para filtrar";
            }else{
                textoFiltroDepto = "Ningún Departamento Seleccionado para filtrar";
                textofiltroMun = "Ningún Municipio Seleccionado para filtrar";                
            }
        }catch(IdeamException e){
            showMessage(e);
        }
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(p_mun);
        AdfFacesContext.getCurrentInstance().addPartialTarget(pfl_filtros);
    }

    public void setP_mun(RichPopup p1) {
        this.p_mun = p1;
    }

    public RichPopup getP_mun() {
        return p_mun;
    }

    public void setD_mun(RichDialog d1) {
        this.d_mun = d1;
    }

    public RichDialog getD_mun() {
        return d_mun;
    }

    public void setSms_mun(RichSelectManyShuttle sms1) {
        this.sms_mun = sms1;
    }

    public RichSelectManyShuttle getSms_mun() {
        return sms_mun;
    }

    public void setSi2(UISelectItems si2) {
        this.si2 = si2;
    }

    public UISelectItems getSi2() {
        return si2;
    }

    public void d_mun_dialogListener(DialogEvent dialogEvent) {
        if(sms_mun.getValue()!=null){
            ArrayList lista = (ArrayList)sms_mun.getValue();            
            textofiltroMun ="" + lista.size() + " Municipios Seleccionados para filtrar";                
        }else{
            textofiltroMun = "Ningún Municipio Seleccionado para filtrar";                
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(p_mun);
        AdfFacesContext.getCurrentInstance().addPartialTarget(pfl_filtros);
    }

    public String getTextoFiltroDepto() {
        return textoFiltroDepto;
    }

    public void setTextoFiltroDepto(String textoFiltroDepto) {
        this.textoFiltroDepto = textoFiltroDepto;
    }

    public String getTextofiltroMun() {
        return textofiltroMun;
    }

    public void setTextofiltroMun(String textofiltroMun) {
        this.textofiltroMun = textofiltroMun;
    }

    public void setPgl5(RichPanelGroupLayout pgl5) {
        this.pgl5 = pgl5;
    }

    public RichPanelGroupLayout getPgl5() {
        return pgl5;
    }

    public void setOt11(RichOutputText ot11) {
        this.ot11 = ot11;
    }

    public RichOutputText getOt11() {
        return ot11;
    }

    public void setS3(RichSpacer s3) {
        this.s3 = s3;
    }

    public RichSpacer getS3() {
        return s3;
    }

    public void setCb_filtro_gestion(RichCommandButton cb1) {
        this.cb_filtro_gestion = cb1;
    }

    public RichCommandButton getCb_filtro_gestion() {
        return cb_filtro_gestion;
    }

    public String getTextoFiltroGestion() {
        return textoFiltroGestion;
    }

    public void setTextoFiltroGestion(String textoFiltroGestion) {
        this.textoFiltroGestion = textoFiltroGestion;
    }

    public String getTextoFiltroInvestigacion() {
        return textoFiltroInvestigacion;
    }

    public void setTextoFiltroInvestigacion(String textoFiltroInvestigacion) {
        this.textoFiltroInvestigacion = textoFiltroInvestigacion;
    }

    public void setP_gestion(RichPopup p1) {
        this.p_gestion = p1;
    }

    public RichPopup getP_gestion() {
        return p_gestion;
    }

    public void setD_gestion(RichDialog d1) {
        this.d_gestion = d1;
    }

    public RichDialog getD_gestion() {
        return d_gestion;
    }

    public void setSms_gestion(RichSelectManyShuttle sms1) {
        this.sms_gestion = sms1;
    }

    public RichSelectManyShuttle getSms_gestion() {
        return sms_gestion;
    }

    public void setSi3(UISelectItems si3) {
        this.si3 = si3;
    }

    public UISelectItems getSi3() {
        return si3;
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

    public void setPgl6(RichPanelGroupLayout pgl6) {
        this.pgl6 = pgl6;
    }

    public RichPanelGroupLayout getPgl6() {
        return pgl6;
    }

    public void setOt12(RichOutputText ot12) {
        this.ot12 = ot12;
    }

    public RichOutputText getOt12() {
        return ot12;
    }

    public void setS4(RichSpacer s4) {
        this.s4 = s4;
    }

    public RichSpacer getS4() {
        return s4;
    }

    public void setCb_filtro_inv(RichCommandButton cb1) {
        this.cb_filtro_inv = cb1;
    }

    public RichCommandButton getCb_filtro_inv() {
        return cb_filtro_inv;
    }

    public void setP_inv(RichPopup p1) {
        this.p_inv = p1;
    }

    public RichPopup getP_inv() {
        return p_inv;
    }

    public void setD_inv(RichDialog d1) {
        this.d_inv = d1;
    }

    public RichDialog getD_inv() {
        return d_inv;
    }

    public void setSms_inv(RichSelectManyShuttle sms1) {
        this.sms_inv = sms1;
    }

    public RichSelectManyShuttle getSms_inv() {
        return sms_inv;
    }

    public void setSi4(UISelectItems si4) {
        this.si4 = si4;
    }

    public UISelectItems getSi4() {
        return si4;
    }

    public void setPgl7(RichPanelGroupLayout pgl7) {
        this.pgl7 = pgl7;
    }

    public RichPanelGroupLayout getPgl7() {
        return pgl7;
    }

    public void setCb_buscar(RichCommandButton cb1) {
        this.cb_buscar = cb1;
    }

    public RichCommandButton getCb_buscar() {
        return cb_buscar;
    }

    public void setS5(RichSpacer s5) {
        this.s5 = s5;
    }

    public RichSpacer getS5() {
        return s5;
    }

    public void cb_buscar_actionListener(ActionEvent actionEvent) {
        // Determinar que se haya ingresado por lo menos un criterio de 
        // busqueda
        boolean existenCriterios = false;
        if(it_nombre.getValue()!=null && it_nombre.getValue().toString().length()>0){
            existenCriterios = true;
        }
        if(((ArrayList)sms_deptos.getValue()).size()>0){
            existenCriterios = true;
        }
        if(((ArrayList)sms_gestion.getValue()).size()>0){
            existenCriterios = true;
        }
        if(((ArrayList)sms_inv.getValue()).size()>0){
            existenCriterios = true;
        }
        if (!existenCriterios){
            showMessage("Debe ingresar por lo menos un criterio de Búsqueda",FacesMessage.SEVERITY_ERROR);
            return;
        }
    }
}
