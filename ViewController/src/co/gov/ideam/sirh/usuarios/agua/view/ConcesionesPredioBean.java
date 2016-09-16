package co.gov.ideam.sirh.usuarios.agua.view;

import co.gov.ideam.sirh.usuarios.agua.model.Concesion;
import co.gov.ideam.sirh.usuarios.agua.model.Predio;
import co.gov.ideam.sirh.usuarios.agua.model.UsuarioAgua;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichMenu;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.nav.RichCommandMenuItem;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
import oracle.adf.view.rich.component.rich.output.RichSpacer;

public class ConcesionesPredioBean extends StandarBean{
    private RichForm f2;
    private RichDocument d2;
    private RichPanelStretchLayout psl1;
    private RichPanelGroupLayout pgl1;
    private RichSpacer s2;
    private RichCommandLink cl1;
    private RichSpacer s5;
    private RichCommandLink cl2;
    private RichSpacer s6;
    private RichCommandLink cl3;
    private RichSpacer s1;
    private RichOutputText ot1;
    private RichPanelCollection pc1;
    
    private List listaConcesiones;
    private UsuarioAgua usuario;
    private Predio predio;
    private String detalleConcesion;
    
    private RichTable t_concesiones;
    private RichMenu m_archivo;
    private RichCommandMenuItem cmi_adicionar;
    private RichCommandMenuItem cmi_editar;
    private RichPanelStretchLayout psl2;
    private RichOutputText ot12;
    private RichPanelGroupLayout pgl2;


    public ConcesionesPredioBean(){
        FacesUtils.setActiveBean("concesionesPredio",
                                 "Adicionar Del Agua",
                                 UsuariosAguaDelegate.class);        
        FacesUtils.removeManagedBeanFromSession("PrediosTreeHandler");
        this.load();
    }
    
    private void load(){        
        try{
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();            
            Object obj = FacesUtils.getFromSession("usuarioSeleccionado");
            if(obj instanceof UsuarioAgua){
                this.setUsuario((UsuarioAgua)obj);
            }else{
                UsuarioAgua user = 
                    uad.getUsuarioAgua( 
                        (Long)FacesUtils.getFromSession("usuarioSeleccionado") );
                this.setUsuario( user );
            }
                                    
            Object objPredio = FacesUtils.getFromSession("predioSeleccionado");
            if(objPredio instanceof Predio){
                this.setPredio((Predio)FacesUtils.getFromSession("predioSeleccionado"));        
            }else{
                Long codigoPredio = (Long)FacesUtils.getFromSession("predioSeleccionado");
                this.setPredio( uad.getPredio( codigoPredio ) );
            }
                                                
            FacesUtils.removeFromSession("concesionSeleccionada");

            listaConcesiones = uad.getConcesiones(predio);
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

    public void setS2(RichSpacer s2) {
        this.s2 = s2;
    }

    public RichSpacer getS2() {
        return s2;
    }

    public void setCl1(RichCommandLink cl1) {
        this.cl1 = cl1;
    }

    public RichCommandLink getCl1() {
        return cl1;
    }

    public void setS5(RichSpacer s5) {
        this.s5 = s5;
    }

    public RichSpacer getS5() {
        return s5;
    }

    public void setCl2(RichCommandLink cl2) {
        this.cl2 = cl2;
    }

    public RichCommandLink getCl2() {
        return cl2;
    }

    public void setS6(RichSpacer s6) {
        this.s6 = s6;
    }

    public RichSpacer getS6() {
        return s6;
    }


    public void setCl3(RichCommandLink cl3) {
        this.cl3 = cl3;
    }

    public RichCommandLink getCl3() {
        return cl3;
    }


    public void setS1(RichSpacer s1) {
        this.s1 = s1;
    }

    public RichSpacer getS1() {
        return s1;
    }

    public void setOt1(RichOutputText ot1) {
        this.ot1 = ot1;
    }

    public RichOutputText getOt1() {
        return ot1;
    }

    public void setPc1(RichPanelCollection pc1) {
        this.pc1 = pc1;
    }

    public RichPanelCollection getPc1() {
        return pc1;
    }

    public List getListaConcesiones() {
        return listaConcesiones;
    }

    public void setListaConcesiones(List listaConcesiones) {
        this.listaConcesiones = listaConcesiones;
    }

    public UsuarioAgua getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioAgua usuario) {
        this.usuario = usuario;
    }

    public Predio getPredio() {
        return predio;
    }

    public void setPredio(Predio predio) {
        this.predio = predio;
    }

    public void setT_concesiones(RichTable t1) {
        this.t_concesiones = t1;
    }

    public RichTable getT_concesiones() {
        return t_concesiones;
    }

    public void setM_archivo(RichMenu m2) {
        this.m_archivo = m2;
    }

    public RichMenu getM_archivo() {
        return m_archivo;
    }

    public void setCmi_adicionar(RichCommandMenuItem cmi2) {
        this.cmi_adicionar = cmi2;
    }

    public RichCommandMenuItem getCmi_adicionar() {
        return cmi_adicionar;
    }

    public void setCmi_editar(RichCommandMenuItem cmi3) {
        this.cmi_editar = cmi3;
    }

    public RichCommandMenuItem getCmi_editar() {
        return cmi_editar;
    }

    public void cmi_adicionar_actionListener(ActionEvent actionEvent) {
        Concesion concesion = new Concesion();
        concesion.setCodigoAutoridadAmbiental(this.usuario.getAutoridadAmbiental().getId().longValue());
        concesion.setCodigoPredio(this.predio.getCodigo());
        FacesUtils.setInSession("concesionSeleccionada", concesion);
        FacesUtils.setInSession("paginaOrigen","listarConcesiones");
    }

    public void cmi_editar_actionListener(ActionEvent actionEvent) {
        detalleConcesion = "";
        if (t_concesiones.getSelectedRowData()==null){
            showMessage(getText("seleccionar_registro"),
                        FacesMessage.SEVERITY_ERROR);
            return;
        }        
        Concesion concesionSeleccionada = (Concesion)t_concesiones.getSelectedRowData();
        FacesUtils.setInSession("concesionSeleccionada", concesionSeleccionada);   
        FacesUtils.setInSession("paginaOrigen","listarConcesiones");
        detalleConcesion = "detalleConcesion";
    }

    public void setPsl2(RichPanelStretchLayout psl2) {
        this.psl2 = psl2;
    }

    public RichPanelStretchLayout getPsl2() {
        return psl2;
    }

    public void setOt12(RichOutputText ot12) {
        this.ot12 = ot12;
    }

    public RichOutputText getOt12() {
        return ot12;
    }

    public void setPgl2(RichPanelGroupLayout pgl2) {
        this.pgl2 = pgl2;
    }

    public RichPanelGroupLayout getPgl2() {
        return pgl2;
    }


    public String getDetalleConcesion() {
        return detalleConcesion;
    }

    public void setDetalleConcesion(String detalleConcesion) {
        this.detalleConcesion = detalleConcesion;
    }
}
