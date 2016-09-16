package co.gov.ideam.sirh.usuarios.agua.view;

import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.usuarios.agua.model.Captacion;
import co.gov.ideam.sirh.usuarios.agua.model.Concesion;
import co.gov.ideam.sirh.usuarios.agua.model.Predio;
import co.gov.ideam.sirh.usuarios.agua.model.RurtCaptacionUso;
import co.gov.ideam.sirh.usuarios.agua.model.SubttFunias;
import co.gov.ideam.sirh.usuarios.agua.model.UsuarioAgua;
import co.gov.ideam.sirh.util.Constantes;
import co.gov.ideam.sirh.util.ConstantesParametros;
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
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.nav.RichCommandMenuItem;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;

import oracle.adf.view.rich.component.rich.output.RichSpacer;

import org.apache.myfaces.trinidad.event.SelectionEvent;

public class FuniasCaptacionBean extends StandarBean{
    
    //objeto padre.
    private Captacion captacion;
    //objeto seleccionado en la tabla
    private SubttFunias funiasSeleccionado;
    //usado para reglas de navegacion desde el menu
    private String accionAdicionar;
    //usuario en session
    private UsuarioAgua usuario;
    //predio en session
    private Predio predio;
    //concesion en session
    private Concesion concesion; 
    
    
    private RichForm form1;
    private RichDocument document1;
    private RichPanelStretchLayout panelStretchLayout1;
    private RichPanelGroupLayout panelGroupLayout1;
    private RichPanelSplitter panelSplitter1;
    private RichPanelCollection panelCollection1;
    private RichTable table1;
    private RichMenu menu1;
    private RichCommandMenuItem commandMenuItem1;
    private RichCommandMenuItem commandMenuItem2;
    
    //Miga pan
    private RichSpacer spacer10;
    private RichCommandLink commandLink1;
    private RichSpacer spacer11;
    private RichCommandLink commandLink2;
    private RichSpacer spacer12;
    private RichCommandLink commandLink3;
    private RichSpacer spacer13;
    private RichCommandLink commandLink4;
    private RichSpacer spacer14;
    private RichCommandLink commandLink5;
    private RichSpacer spacer15;
    private RichOutputText outputText6;

    public FuniasCaptacionBean(){
        FacesUtils.setActiveBean("funiasCaptacionBean",
                                 "Listado de Funias", 
                                 UsuariosAguaDelegate.class);
        this.load();
    }
    
    public void load(){
        try{
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
            Object objCaptacion = FacesUtils.getFromSession("captacionSeleccionada");
            if(objCaptacion instanceof Captacion){
                this.captacion = (Captacion)objCaptacion;
            }else{                
                Long codigo = (Long)FacesUtils.getFromSession("captacionSeleccionada");                                            
                this.captacion = uad.getCaptacion(codigo);                
            }
            
            Object objUsuario = FacesUtils.getFromSession("usuarioSeleccionado");
            if(objUsuario instanceof UsuarioAgua){            
                this.setUsuario((UsuarioAgua)objUsuario);
            }else{                
                Long codigoUsuario = (Long)FacesUtils.getFromSession("usuarioSeleccionado");
                this.setUsuario(uad.getUsuarioAgua(codigoUsuario));  
                FacesUtils.setInSession("usuarioSeleccionado", this.usuario);
            }
            
            Object objPredio = FacesUtils.getFromSession("predioSeleccionado");
            if(objPredio instanceof Predio){
                this.setPredio((Predio)FacesUtils.getFromSession("predioSeleccionado"));        
            }else{
                Long codigoPredio = (Long)FacesUtils.getFromSession("predioSeleccionado");
                this.setPredio(uad.getPredio( codigoPredio ));
                FacesUtils.setInSession("predioSeleccionado", this.predio);
            }                    
            
            Object objConcesion = FacesUtils.getFromSession("concesionSeleccionada");        
            if(objConcesion instanceof Concesion){
                this.concesion = (Concesion)FacesUtils.getFromSession("concesionSeleccionada");        
            }else{
                Long codigoConcesion = (Long)FacesUtils.getFromSession("concesionSeleccionada");        
                this.concesion = uad.getConcesion(codigoConcesion);
                FacesUtils.setInSession("concesionSeleccionada", this.concesion);
            }
            
            //listado de funias de la captacion
            List<SubttFunias> funiasRelacionados = uad.getFuniasByCaptacion(this.captacion);
            this.captacion.setSubttFuniasList(funiasRelacionados);

            
        }catch(IdeamException e){
            showMessage(e);
        }
    }
    
    /////////////
    
    public String commandMenuItem1_action() {
        UsuarioConectadoTO usuarioConectado = 
            (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
        
        Autoridades autoridadAmbiental = usuarioConectado.getUsuario().getAutoridadAmbiental();
        if(autoridadAmbiental==null ||
                autoridadAmbiental.getId().intValue() == Constantes.IDEAM){
            showMessage(getText("NO_EXISTE_AUTORIDAD_AMBIENTAL"),
                        FacesMessage.SEVERITY_ERROR); 
            return "";
        }else {
            FacesUtils.removeFromSession("funiasSeleccionado");
            return accionAdicionar = "adicionarFunias";
        }  
    }
    
    
    public void commandMenuItem2_actionListener(ActionEvent actionEvent) {
        accionAdicionar = "";
        if (this.funiasSeleccionado==null){
            showMessage(getText("seleccionar_registro"),
                        FacesMessage.SEVERITY_ERROR);
            return;
        }

        FacesUtils.setInSession("funiasSeleccionado", this.funiasSeleccionado);
        FacesUtils.removeManagedBeanFromSession("UsosTreeHandler");
        accionAdicionar = "detalleFunias";
    }
    
    
    public void table1_selectionListener(SelectionEvent selectionEvent) {
        RichTable tAux = (RichTable)selectionEvent.getSource();
        this.funiasSeleccionado = (SubttFunias)tAux.getSelectedRowData();
    }
    
    /////////////
    
    public void setCaptacion(Captacion captacion) {
        this.captacion = captacion;
    }

    public Captacion getCaptacion() {
        return captacion;
    }
    
    public void setAccionAdicionar(String accionAdicionar) {
        this.accionAdicionar = accionAdicionar;
    }

    public String getAccionAdicionar() {
        return accionAdicionar;
    }
    
    public void setUsuario(UsuarioAgua usuario) {
        this.usuario = usuario;
    }

    public UsuarioAgua getUsuario() {
        return usuario;
    }

    public void setPredio(Predio predio) {
        this.predio = predio;
    }

    public Predio getPredio() {
        return predio;
    }

    public void setConcesion(Concesion concesion) {
        this.concesion = concesion;
    }

    public Concesion getConcesion() {
        return concesion;
    }
    
    //////////////
    
    public void setForm1(RichForm form1) {
        this.form1 = form1;
    }

    public RichForm getForm1() {
        return form1;
    }

    public void setDocument1(RichDocument document1) {
        this.document1 = document1;
    }

    public RichDocument getDocument1() {
        return document1;
    }


    public void setPanelStretchLayout1(RichPanelStretchLayout panelStretchLayout1) {
        this.panelStretchLayout1 = panelStretchLayout1;
    }

    public RichPanelStretchLayout getPanelStretchLayout1() {
        return panelStretchLayout1;
    }

    public void setPanelGroupLayout1(RichPanelGroupLayout panelGroupLayout1) {
        this.panelGroupLayout1 = panelGroupLayout1;
    }

    public RichPanelGroupLayout getPanelGroupLayout1() {
        return panelGroupLayout1;
    }

    public void setPanelSplitter1(RichPanelSplitter panelSplitter1) {
        this.panelSplitter1 = panelSplitter1;
    }

    public RichPanelSplitter getPanelSplitter1() {
        return panelSplitter1;
    }

    public void setPanelCollection1(RichPanelCollection panelCollection1) {
        this.panelCollection1 = panelCollection1;
    }

    public RichPanelCollection getPanelCollection1() {
        return panelCollection1;
    }

    public void setTable1(RichTable table1) {
        this.table1 = table1;
    }

    public RichTable getTable1() {
        return table1;
    }

    public void setMenu1(RichMenu menu1) {
        this.menu1 = menu1;
    }

    public RichMenu getMenu1() {
        return menu1;
    }

    public void setCommandMenuItem1(RichCommandMenuItem commandMenuItem1) {
        this.commandMenuItem1 = commandMenuItem1;
    }

    public RichCommandMenuItem getCommandMenuItem1() {
        return commandMenuItem1;
    }

    public void setCommandMenuItem2(RichCommandMenuItem commandMenuItem2) {
        this.commandMenuItem2 = commandMenuItem2;
    }

    public RichCommandMenuItem getCommandMenuItem2() {
        return commandMenuItem2;
    }

    public void setSpacer10(RichSpacer spacer10) {
        this.spacer10 = spacer10;
    }

    public RichSpacer getSpacer10() {
        return spacer10;
    }

    public void setCommandLink1(RichCommandLink commandLink1) {
        this.commandLink1 = commandLink1;
    }

    public RichCommandLink getCommandLink1() {
        return commandLink1;
    }

    public void setSpacer11(RichSpacer spacer11) {
        this.spacer11 = spacer11;
    }

    public RichSpacer getSpacer11() {
        return spacer11;
    }

    public void setCommandLink2(RichCommandLink commandLink2) {
        this.commandLink2 = commandLink2;
    }

    public RichCommandLink getCommandLink2() {
        return commandLink2;
    }

    public void setSpacer12(RichSpacer spacer12) {
        this.spacer12 = spacer12;
    }

    public RichSpacer getSpacer12() {
        return spacer12;
    }

    public void setCommandLink3(RichCommandLink commandLink3) {
        this.commandLink3 = commandLink3;
    }

    public RichCommandLink getCommandLink3() {
        return commandLink3;
    }

    public void setSpacer13(RichSpacer spacer13) {
        this.spacer13 = spacer13;
    }

    public RichSpacer getSpacer13() {
        return spacer13;
    }

    public void setCommandLink4(RichCommandLink commandLink4) {
        this.commandLink4 = commandLink4;
    }

    public RichCommandLink getCommandLink4() {
        return commandLink4;
    }

    public void setSpacer14(RichSpacer spacer14) {
        this.spacer14 = spacer14;
    }

    public RichSpacer getSpacer14() {
        return spacer14;
    }

    public void setCommandLink5(RichCommandLink commandLink5) {
        this.commandLink5 = commandLink5;
    }

    public RichCommandLink getCommandLink5() {
        return commandLink5;
    }

    public void setSpacer15(RichSpacer spacer15) {
        this.spacer15 = spacer15;
    }

    public RichSpacer getSpacer15() {
        return spacer15;
    }

    public void setOutputText6(RichOutputText outputText6) {
        this.outputText6 = outputText6;
    }

    public RichOutputText getOutputText6() {
        return outputText6;
    }


    
}
