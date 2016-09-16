package co.gov.ideam.sirh.oferta.view;

import co.gov.ideam.sirh.oferta.model.ShmvDiariosHid;
import co.gov.ideam.sirh.oferta.model.ShmvMensualesHid;
import co.gov.ideam.sirh.oferta.model.SiovEstaciones;
import co.gov.ideam.sirh.oferta.view.grafica.TendenciaCentral;
import co.gov.ideam.sirh.parametros.model.PartZonificListas;
import co.gov.ideam.sirh.util.ConstantesOferta;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;

import co.gov.ideam.sirh.view.StandarBean;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;

import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.input.RichSelectOneListbox;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichSpacer;

import oracle.adf.view.rich.context.AdfFacesContext;

import org.apache.myfaces.trinidad.event.DisclosureEvent;

/**
 * Esta clase se crea por defecto y esta relacionada con los objetos de 
 * la jspx de la vista.
 * */

public class DetalleEstacionBacking  extends DetalleEstacion{
        
    private RichForm form1;
    private RichDocument document1;
    private RichPanelStretchLayout panelStretchLayout1;
    private RichPanelGroupLayout panelGroupLayout1;
    private RichSpacer spacer1;
    private RichCommandLink commandLink1;
    private RichSpacer spacer2;
    private RichOutputText outputText1;
    private RichPanelBox panelBox1;
    private RichPanelGroupLayout panelGroupLayout3;
    private RichPanelGroupLayout panelGroupLayout4;
    private RichInputText itAgno;
    private RichSelectOneChoice soMes;
    private UISelectItems siMes;
    private RichSelectOneChoice socVariable;
    private UISelectItems siVariable;
    private RichCommandButton cmdBuscar;
    private RichCommandLink commandLink2;
    private RichSpacer spacer3;
    
    //CDONCEL
    private String variable;
    
    //FIN CONCEL

    public DetalleEstacionBacking()  {
        FacesUtils.setActiveBean("detalleEstacionBacking", "Series",
                                 OfertaDelegate.class);
        this.load();
    }
    
    public void load() {
        try{
            this.getEstacion();
            
            this.cargarParametros();
            
            this.cargarVariables();
            
        } catch (IdeamException e) {
            showMessage(e);
        }
    }
    
    public void cmdBuscar_actionListener(ActionEvent actionEvent) {
       if(this.socVariable.getValue() == null){
            showMessage("Debe seleccionar una serie de datos o variable",
                        FacesMessage.SEVERITY_ERROR);
            return;
        }
        
        this.cargarDatos();
        
        String variable = this.socVariable.getValue().toString();
        String texto = "Series de la estación para " + ConstantesOferta.variablesSerie.get(variable);
        this.panelBox1.setText(texto);
        AdfFacesContext.getCurrentInstance().addPartialTarget(panelBox1);
    }

    public void cargarDatos() {
        try{
            
            this.setShowPromedio(false);
            this.setShowCurva(false);
            this.setShowHistograma(false);
            this.setShowAnual(false);
            
            System.out.println("----------------cargarDatos HIJO");
            Integer agno = (this.itAgno.getValue()==null || 
                            this.itAgno.getValue().toString().trim().equalsIgnoreCase(""))
                ? 0 
                : new Integer(this.itAgno.getValue().toString());
            
            Integer mes = (this.soMes.getValue()==null) 
                ? 0 
                : (Integer)this.soMes.getValue();
            
            String variable = this.socVariable.getValue().toString();
            
            //NEGOCIO-----------
            
            //-----DATOS------
            //-----Consulta---
            this.cargarDatos(variable, agno, mes);
            
            this.cargarDatosMultianuales(variable, agno);
            
            this.cargarDatosMultianualesResumen(variable);
            
            //-------Limpieza------
            this.limpiarDatos();
            
            //----- Medidas de Tendencia Central------
            this.tendenciaCentral();
                
            //----GRAFICAS--------------
            //-----Serie historica--------
            if(this.validarCantidadDatos(ConstantesOferta.CAN_DATOS_MIN_SERIE)){
                this.serieHistorica();
                this.setShowHistorico(true);
            }else{
                this.setShowHistorico(false);
            }
            
            //----Frecuencia de datos---
            if(this.validarCantidadDatos(ConstantesOferta.CAN_DATOS_MIN_SERIE)){
                this.frecuenciaDatos();
                this.setShowPromedio(true);
            }else{
                this.setShowPromedio(false);
            }
            
            //-----Curva duracion--------
            if (this.validarCantidadDatos(ConstantesOferta.CAN_DATOS_MIN_SERIE)) {
                if (variable.contains("CDL")) {
                    this.curvaDuracion();
                    this.setShowCurva(true);
                } else {
                    this.setShowCurva(false);
                }
            } else {
                this.setShowCurva(false);
            }

            //-----BoxPlot--------
            if (this.validarCantidadDatos(ConstantesOferta.CAN_DATOS_MIN_SERIE)) {
                if (variable.contains(ConstantesOferta.PREFIJO_DIA)) {
                    this.anual();
                    this.setShowAnual(true);
                    this.setShowMultianual(false);
                } else {
                    this.setShowAnual(false);
                    this.setShowMultianual(false);
                }

                
                this.multianual(agno);
                this.setShowMultianual(true);


            } else {
                this.setShowAnual(false);
                this.setShowMultianual(false);
            }
            //-----Histograma--------
            if(this.validarCantidadDatos(ConstantesOferta.CAN_DATOS_MIN_HISTOGRAMA)){
                this.histograma();
                this.setShowHistograma(true);
            }else{
                this.setShowHistograma(false);
                showMessage(getText("OFERTA_INSUFICIENTE_DATOS"), 
                            FacesMessage.SEVERITY_ERROR);
                
                AdfFacesContext.getCurrentInstance().addPartialTarget(panelBox1);
                return;                  
            }

        } catch (IdeamException e) {
            showMessage(e);
        }
    }
    
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

    public void setSpacer1(RichSpacer spacer1) {
        this.spacer1 = spacer1;
    }

    public RichSpacer getSpacer1() {
        return spacer1;
    }

    public void setCommandLink1(RichCommandLink commandLink1) {
        this.commandLink1 = commandLink1;
    }

    public RichCommandLink getCommandLink1() {
        return commandLink1;
    }

    public void setSpacer2(RichSpacer spacer2) {
        this.spacer2 = spacer2;
    }

    public RichSpacer getSpacer2() {
        return spacer2;
    }

    public void setOutputText1(RichOutputText outputText1) {
        this.outputText1 = outputText1;
    }

    public RichOutputText getOutputText1() {
        return outputText1;
    }

    public void setPanelBox1(RichPanelBox panelBox1) {
        this.panelBox1 = panelBox1;
    }

    public RichPanelBox getPanelBox1() {
        return panelBox1;
    }

    public void setPanelGroupLayout3(RichPanelGroupLayout panelGroupLayout3) {
        this.panelGroupLayout3 = panelGroupLayout3;
    }

    public RichPanelGroupLayout getPanelGroupLayout3() {
        return panelGroupLayout3;
    }


    public void setPanelGroupLayout4(RichPanelGroupLayout panelGroupLayout4) {
        this.panelGroupLayout4 = panelGroupLayout4;
    }

    public RichPanelGroupLayout getPanelGroupLayout4() {
        return panelGroupLayout4;
    }

    public void setItAgno(RichInputText inputText1) {
        this.itAgno = inputText1;
    }

    public RichInputText getItAgno() {
        return itAgno;
    }

    public void setSoMes(RichSelectOneChoice selectOneListbox1) {
        this.soMes = selectOneListbox1;
    }

    public RichSelectOneChoice getSoMes() {
        return soMes;
    }

    public void setSiMes(UISelectItems selectItems1) {
        this.siMes = selectItems1;
    }

    public UISelectItems getSiMes() {
        return siMes;
    }   

    public void setSocVariable(RichSelectOneChoice selectOneChoice1) {
        this.socVariable = selectOneChoice1;
    }

    public RichSelectOneChoice getSocVariable() {
        return socVariable;
    }

    public void setSiVariable(UISelectItems selectItems1) {
        this.siVariable = selectItems1;
    }

    public UISelectItems getSiVariable() {
        return siVariable;
    }

    public void setCmdBuscar(RichCommandButton commandButton1) {
        this.cmdBuscar = commandButton1;
    }

    public RichCommandButton getCmdBuscar() {
        return cmdBuscar;
    }

    public void setCommandLink2(RichCommandLink commandLink2) {
        this.commandLink2 = commandLink2;
    }

    public RichCommandLink getCommandLink2() {
        return commandLink2;
    }

    public void setSpacer3(RichSpacer spacer3) {
        this.spacer3 = spacer3;
    }

    public RichSpacer getSpacer3() {
        return spacer3;
    }

  public String getVariable() {
    return variable;
  }

  public void setVariable(String variable) {
    this.variable = variable;
  }
}
