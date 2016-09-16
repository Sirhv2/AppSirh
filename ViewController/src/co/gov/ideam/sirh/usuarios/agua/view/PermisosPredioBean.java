package co.gov.ideam.sirh.usuarios.agua.view;

import co.gov.ideam.sirh.usuarios.agua.model.Concesion;
import co.gov.ideam.sirh.usuarios.agua.model.PermisoVertimiento;
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

public class PermisosPredioBean extends StandarBean{
    private RichForm f2;
    private RichDocument d2;
    private RichPanelStretchLayout psl1;
    private RichPanelGroupLayout pgl2;
    private RichSpacer s3;
    private RichCommandLink cl4;
    private RichSpacer s4;
    private RichCommandLink cl5;
    private RichSpacer s7;
    private RichCommandLink cl6;
    private RichSpacer s8;
    private RichOutputText ot2;
    private RichPanelStretchLayout psl2;
    private RichPanelGroupLayout pgl1;
    private RichOutputText ot1;
    private RichPanelCollection pc1;
    private RichMenu m_archivo;
    private RichCommandMenuItem cmi_adicionar;
    private RichCommandMenuItem cmi_editar;
    
    private List listaPermisos;
    private UsuarioAgua usuario;
    private Predio predio;
    private RichTable t_permisos;
    private String detallePermisos;

    public PermisosPredioBean(){        
        FacesUtils.setActiveBean("permisosPredio",
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
                Long codigoUsuario = (Long)FacesUtils.getFromSession("usuarioSeleccionado");
                this.setUsuario(uad.getUsuarioAgua(codigoUsuario));                
            }
            
            Object objPredio = FacesUtils.getFromSession("predioSeleccionado");
            if(objPredio instanceof Predio){
                this.setPredio((Predio)FacesUtils.getFromSession("predioSeleccionado"));        
            }else{
                Long codigoPredio = (Long)FacesUtils.getFromSession("predioSeleccionado");
                this.setPredio( uad.getPredio( codigoPredio ) );
            }
            
            FacesUtils.removeFromSession("concesionSeleccionada");

            listaPermisos = uad.getPermisosVertimiento(getPredio());
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

    public void setS3(RichSpacer s3) {
        this.s3 = s3;
    }

    public RichSpacer getS3() {
        return s3;
    }

    public void setCl4(RichCommandLink cl4) {
        this.cl4 = cl4;
    }

    public RichCommandLink getCl4() {
        return cl4;
    }

    public void setS4(RichSpacer s4) {
        this.s4 = s4;
    }

    public RichSpacer getS4() {
        return s4;
    }

    public void setCl5(RichCommandLink cl5) {
        this.cl5 = cl5;
    }

    public RichCommandLink getCl5() {
        return cl5;
    }

    public void setS7(RichSpacer s7) {
        this.s7 = s7;
    }

    public RichSpacer getS7() {
        return s7;
    }

    public void setCl6(RichCommandLink cl6) {
        this.cl6 = cl6;
    }

    public RichCommandLink getCl6() {
        return cl6;
    }

    public void setS8(RichSpacer s8) {
        this.s8 = s8;
    }

    public RichSpacer getS8() {
        return s8;
    }

    public void setOt2(RichOutputText ot2) {
        this.ot2 = ot2;
    }

    public RichOutputText getOt2() {
        return ot2;
    }

    public void setPsl2(RichPanelStretchLayout psl2) {
        this.psl2 = psl2;
    }

    public RichPanelStretchLayout getPsl2() {
        return psl2;
    }

    public void setPgl1(RichPanelGroupLayout pgl1) {
        this.pgl1 = pgl1;
    }

    public RichPanelGroupLayout getPgl1() {
        return pgl1;
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

    public List getListaPermisos() {
        return listaPermisos;
    }

    public void setListaPermisos(List listaPermisos) {
        this.listaPermisos = listaPermisos;
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

    public void setT_permisos(RichTable t1) {
        this.t_permisos = t1;
    }

    public RichTable getT_permisos() {
        return t_permisos;
    }

    public void cmi_adicionar_actionListener(ActionEvent actionEvent) {
        PermisoVertimiento permiso = new PermisoVertimiento();
        permiso.setCodigoAutoridadAmbiental(this.usuario.getAutoridadAmbiental().getId().longValue());
        permiso.setCodigoPredio(this.predio.getCodigo());
        FacesUtils.setInSession("permisoSeleccionado", permiso);
        FacesUtils.setInSession("paginaOrigen","listarPermisos");
    }

    public void cmi_editar_actionListener(ActionEvent actionEvent) {
        detallePermisos = "";
        if (t_permisos.getSelectedRowData()==null){
            showMessage(getText("seleccionar_registro"),
                        FacesMessage.SEVERITY_ERROR);
            return;
        }        
        PermisoVertimiento permisoSeleccionado = (PermisoVertimiento)t_permisos.getSelectedRowData();
        FacesUtils.setInSession("permisoSeleccionado", permisoSeleccionado);   
        FacesUtils.setInSession("paginaOrigen","listarPermisos");
        detallePermisos = "detallePermiso";
    }

    public String getDetallePermisos() {
        return detallePermisos;
    }

    public void setDetallePermisos(String detallePermisos) {
        this.detallePermisos = detallePermisos;
    }
}
