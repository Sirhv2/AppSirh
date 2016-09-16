package co.gov.ideam.sirh.directorio.view;

import co.gov.ideam.sirh.directorio.model.ActEspecialistas;
import co.gov.ideam.sirh.directorio.model.ActOrganizaciones;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.util.Constantes;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;

import java.util.List;

import javax.faces.application.FacesMessage;

import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;

public class ResultadoConsultaOrganizacionesBean extends StandarBean{
    private RichForm f2;
    private RichDocument d2;
    private RichPanelStretchLayout psl1;
    private RichPanelGroupLayout pgl1;
    private RichOutputText ot1;
    private RichPanelCollection pc2;
    private RichTable t1;
    private RichPanelGroupLayout pgl4;
    private RichPanelGroupLayout pgl5;
    private RichCommandButton cb1;
    
    private List<ActOrganizaciones> listaOrganizaciones;
    
    public ResultadoConsultaOrganizacionesBean(){
        FacesUtils.setActiveBean("resultadoConsultaOrganizaciones",
                                 "resultadoConsultaOrganizaciones",
                                 DirectorioDelegate.class);        
        this.load();
    }
    
    public void load(){
        try{
                                    
            DirectorioDelegate dd = DirectorioDelegate.getInstance();
            if (FacesUtils.getFromSession("filtroConsultaOrganizaciones")!=null){
                this.setListaOrganizaciones((List<ActOrganizaciones>)FacesUtils.getFromSession("filtroConsultaOrganizaciones"));                
            }else{
                this.setListaOrganizaciones(null);                
                showMessage("No se han establecido criterios de consulta",
                            FacesMessage.SEVERITY_ERROR);
            }
        }catch(IdeamException e){
            showMessage(e);
        }    }    

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

    public void setOt1(RichOutputText ot1) {
        this.ot1 = ot1;
    }

    public RichOutputText getOt1() {
        return ot1;
    }

    public void setPc2(RichPanelCollection pc2) {
        this.pc2 = pc2;
    }

    public RichPanelCollection getPc2() {
        return pc2;
    }


    public void setT1(RichTable t1) {
        this.t1 = t1;
    }

    public RichTable getT1() {
        return t1;
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

    public void setCb1(RichCommandButton cb1) {
        this.cb1 = cb1;
    }

    public RichCommandButton getCb1() {
        return cb1;
    }

    public List<ActOrganizaciones> getListaOrganizaciones() {
        return listaOrganizaciones;
    }

    public void setListaOrganizaciones(List<ActOrganizaciones> listaOrganizaciones) {
        this.listaOrganizaciones = listaOrganizaciones;
    }
}
