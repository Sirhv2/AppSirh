package co.gov.ideam.sirh.usuarios.agua.view;

import co.gov.ideam.sirh.parametros.model.ActividadCIIU;
import co.gov.ideam.sirh.parametros.view.ParametrosDelegate;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.StandarBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;

public class BuscarCiiuBean extends StandarBean{
    private RichPanelStretchLayout psl1;
    private RichPanelGroupLayout pgl1;
    private RichOutputText ot1;
    private RichSpacer s1;
    private RichTable t_ciiu;
    private RichPanelStretchLayout psl2;
    private RichPanelGroupLayout pgl2;
    private RichPanelFormLayout pfl1;
    private RichInputText it_codigo;
    private RichInputText it_nombre;
    private RichSpacer s2;
    private RichOutputText ot2;
    
    private List listaActividadesCiiu;
    private RichPanelCollection pc1;
    private RichPanelGroupLayout pgl3;
    private RichPanelGroupLayout pgl4;

    public BuscarCiiuBean(){
        
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

    public void setOt1(RichOutputText ot1) {
        this.ot1 = ot1;
    }

    public RichOutputText getOt1() {
        return ot1;
    }

    public void setS1(RichSpacer s1) {
        this.s1 = s1;
    }

    public RichSpacer getS1() {
        return s1;
    }


    public void setT_ciiu(RichTable t2) {
        this.t_ciiu = t2;
    }

    public RichTable getT_ciiu() {
        return t_ciiu;
    }

    public void setPsl2(RichPanelStretchLayout psl2) {
        this.psl2 = psl2;
    }

    public RichPanelStretchLayout getPsl2() {
        return psl2;
    }

    public void setPgl2(RichPanelGroupLayout pgl2) {
        this.pgl2 = pgl2;
    }

    public RichPanelGroupLayout getPgl2() {
        return pgl2;
    }


    public void setPfl1(RichPanelFormLayout pfl1) {
        this.pfl1 = pfl1;
    }

    public RichPanelFormLayout getPfl1() {
        return pfl1;
    }

    public void setIt_codigo(RichInputText it3) {
        this.it_codigo = it3;
    }

    public RichInputText getIt_codigo() {
        return it_codigo;
    }

    public void setIt_nombre(RichInputText it4) {
        this.it_nombre = it4;
    }

    public RichInputText getIt_nombre() {
        return it_nombre;
    }

    public void setS2(RichSpacer s2) {
        this.s2 = s2;
    }

    public RichSpacer getS2() {
        return s2;
    }

    public void setOt2(RichOutputText ot2) {
        this.ot2 = ot2;
    }

    public RichOutputText getOt2() {
        return ot2;
    }

    public List getListaActividadesCiiu() {
        return listaActividadesCiiu;
    }

    public void setListaActividadesCiiu(List listaActividadesCiiu) {
        this.listaActividadesCiiu = listaActividadesCiiu;
    }
    
    public ActividadCIIU getData()throws IdeamException{
        if(t_ciiu.getSelectedRowData()==null){
            throw new IdeamException("seleccionar_registro");
        }
        return (ActividadCIIU)t_ciiu.getSelectedRowData();
    }
    
    public void inicialize(){
        this.listaActividadesCiiu = new ArrayList();
        it_codigo.setValue(null);
        it_nombre.setValue(null);
        AdfFacesContext.getCurrentInstance().addPartialTarget(it_codigo);
        AdfFacesContext.getCurrentInstance().addPartialTarget(it_nombre);
        AdfFacesContext.getCurrentInstance().addPartialTarget(t_ciiu);
    }
    public void buscar()throws IdeamException {        
        boolean existeCodigo = false;
        boolean existeNombre = false;
        
        if(it_codigo.getValue()!=null &&
            it_codigo.getValue().toString().length()>0){
            existeCodigo = true;        
        }
        if(it_nombre.getValue()!= null &&
           it_nombre.getValue().toString().length() >0){
            existeNombre = true;       
        }
        
        if(existeCodigo && existeNombre){      
            throw new IdeamException("SOLO_UN_CRITERIO");
            //showMessage("SOLO_UN_CRITERIO",FacesMessage.SEVERITY_ERROR,it_codigo);
            //showMessage("SOLO_UN_CRITERIO",FacesMessage.SEVERITY_ERROR,it_nombre);
            //return;
        }
        if(!existeCodigo && !existeNombre){            
            throw new IdeamException("no_existen_criterios");
            //showMessage("no_existen_criterios",FacesMessage.SEVERITY_ERROR,it_codigo);
            //showMessage("no_existen_criterios",FacesMessage.SEVERITY_ERROR,it_nombre);
            //return;
        }
        
        ParametrosDelegate pd = ParametrosDelegate.getInstance();
        listaActividadesCiiu = new  ArrayList();
        
        if(existeCodigo){
            listaActividadesCiiu = pd.getCiiuByCode(it_codigo.getValue().toString());
        }else{
            listaActividadesCiiu = pd.getCiiuByName(it_nombre.getValue().toString());
        }
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(t_ciiu);        
    }


    public void setPc1(RichPanelCollection pc1) {
        this.pc1 = pc1;
    }

    public RichPanelCollection getPc1() {
        return pc1;
    }

    public void setPgl3(RichPanelGroupLayout pgl3) {
        this.pgl3 = pgl3;
    }

    public RichPanelGroupLayout getPgl3() {
        return pgl3;
    }

    public void setPgl4(RichPanelGroupLayout pgl4) {
        this.pgl4 = pgl4;
    }

    public RichPanelGroupLayout getPgl4() {
        return pgl4;
    }
}
