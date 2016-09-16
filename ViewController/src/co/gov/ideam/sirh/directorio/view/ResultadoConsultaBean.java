package co.gov.ideam.sirh.directorio.view;

import co.gov.ideam.sirh.calidad.view.CalidadDelegate;
import co.gov.ideam.sirh.directorio.model.ActEspecialistas;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Categoria;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.util.Constantes;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;

import java.util.List;

import javax.faces.application.FacesMessage;

import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;

public class ResultadoConsultaBean extends StandarBean {
    private RichForm f2;
    private RichDocument d2;
    private RichPanelStretchLayout psl1;
    private RichPanelGroupLayout pgl1;
    private RichOutputText ot2;
    private RichPanelCollection pc2;
    private RichTable t1;
    private RichPanelGroupLayout pgl2;
    private RichCommandButton cb2;
    private RichPanelGroupLayout pgl3;
    private ActEspecialistas especialista;
    private List listaEspecialistas;

    public ResultadoConsultaBean(){
        FacesUtils.setActiveBean("resultadoConsulta",
                                 "resultadoConsulta",
                                 DirectorioDelegate.class);        
        this.load();
    }
        
    public void load(){
        try{
                                    
            DirectorioDelegate dd = DirectorioDelegate.getInstance();
            if (FacesUtils.getFromSession("filtroConsultaEspecialistas")!=null){
                this.setListaEspecialistas((List<ActEspecialistas>)FacesUtils.getFromSession("filtroConsultaEspecialistas"));                
            }else{
                this.setListaEspecialistas(null);                
                showMessage("No se han establecido criterios de consulta",
                            FacesMessage.SEVERITY_ERROR);
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


    public void setOt2(RichOutputText ot2) {
        this.ot2 = ot2;
    }

    public RichOutputText getOt2() {
        return ot2;
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

    public void setPgl2(RichPanelGroupLayout pgl2) {
        this.pgl2 = pgl2;
    }

    public RichPanelGroupLayout getPgl2() {
        return pgl2;
    }


    public void setCb2(RichCommandButton cb2) {
        this.cb2 = cb2;
    }

    public RichCommandButton getCb2() {
        return cb2;
    }

    public void setPgl3(RichPanelGroupLayout pgl3) {
        this.pgl3 = pgl3;
    }

    public RichPanelGroupLayout getPgl3() {
        return pgl3;
    }

    public List getListaEspecialistas() {
        return listaEspecialistas;
    }

    public void setListaEspecialistas(List listaEspecialistas) {
        this.listaEspecialistas = listaEspecialistas;
    }
    
    

    public void detalleautor_actionListener(ActionEvent actionEvent) {
        try{    
     
       Long ident = 
           (Long)actionEvent.getComponent().getAttributes().get("codigo");   
            System.out.println(" this.especialista---------ident---------------------->"+ident );
            
           DirectorioDelegate dd = DirectorioDelegate.getInstance();
            
           this.especialista = dd.getEspecialistaId(ident);  
            
            
            System.out.println(" this.especialista------------------------------->"+ this.especialista.getId() +"---------"+ this.especialista.getNombre());
           FacesUtils.setInSession("especialistaSeleccionado", this.especialista);
         
           
         
        }catch(IdeamException e){
            showMessage(e);
        }
         
         
    }

    public void setEspecialista(ActEspecialistas especialista) {
        this.especialista = especialista;
    }

    public ActEspecialistas getEspecialista() {
        return especialista;
    }
}
