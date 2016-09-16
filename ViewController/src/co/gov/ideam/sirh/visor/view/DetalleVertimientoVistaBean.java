package co.gov.ideam.sirh.visor.view;



import co.gov.ideam.sirh.parametros.view.ParametrosDelegate;

import co.gov.ideam.sirh.usuarios.agua.model.PuntoVertimiento;


import co.gov.ideam.sirh.usuarios.agua.view.UsuariosAguaDelegate;

import co.gov.ideam.sirh.util.ConstantesParametros;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;

import co.gov.ideam.sirh.view.StandarBean;

import java.util.List;


import javax.faces.component.UISelectItems;


import javax.faces.context.FacesContext;

import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichDecorativeBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichSpacer;


public class DetalleVertimientoVistaBean extends StandarBean{

    //Objeto al cual se le están realizando cambios
    private PuntoVertimiento vertimiento;
   
    //lista de tipos de fuente en categoria.
    private List listaTiposFuente;
    //Lista de fuentes registradas.

    //lista de tipos de vertimiento en categoria.
    private List listaTipoVertimiento;
    //Lista de tipos de centros problados en categorias.
    private List listaTipoCentroPoblado;
    //lista de sistemas de referencia en categorias.
  
    private List listaTipoFlujo;
   
  
    //Lista de días del mes
    private List listaDias;
    //Lista de tipos de pretartamiento asociado.
   


    private RichForm form1;
    private RichDocument document1;
    private RichPanelStretchLayout panelStretchLayout1;
    private RichPanelSplitter panelSplitter1;
    private RichPanelStretchLayout panelStretchLayout3;
    private RichPanelGroupLayout panelGroupLayout1;
    private RichPanelBox panelBox1;
    private RichPanelGroupLayout panelGroupLayout2;
    private RichPanelGroupLayout panelGroupLayout3;
    private RichDecorativeBox decorativeBox1;
    private RichPanelFormLayout panelFormLayout1;
    private RichDecorativeBox decorativeBox3;
    private RichPanelFormLayout panelFormLayout3;
    private RichSelectOneChoice soc_tipo_fuente;
    private UISelectItems si_tipo_fuente;
    private RichSelectOneChoice socTipoCP;
    private UISelectItems siTipoCP;
    private RichInputText itNombreCentroPoblado;
    private RichCommandButton cbPrevUbicacion;
    private RichSpacer spacer5;
    private RichCommandButton cbNextUbicacion;
    private RichSpacer spacer6;
    private RichSelectOneChoice socTipoFlujo;
    private UISelectItems siTipoFlujo;
    private RichInputText itTiempoDescarga;
    private RichInputText itCaudalDisegno;
    private RichSelectOneChoice socFrecuencia;
    private UISelectItems siFrecuencia;
    private RichOutputText outputText7;
    private RichSpacer spacer12;
    private RichSpacer spacer14;
    private RichOutputText outputText4;
    private RichPanelGroupLayout panelGroupLayout15;
    private RichPanelGroupLayout panelGroupLayout17;

    

    private RichSpacer spacer3;
    private RichInputText ITAREA;
    private RichInputText ITZONA;
    private RichInputText ITSUBZONA;
    private RichInputText ittipoFuente;
    private RichInputText itfuente;
    private RichInputText ittramo;
    private RichInputText ittipovert;
    private RichInputText itDpto;
    private RichInputText itmcpo;


    public DetalleVertimientoVistaBean(){
        FacesUtils.setActiveBean("detalleVertimientoBean", "Detalle Vertimiento",
                                 UsuariosAguaDelegate.class);
        FacesUtils.removeManagedBeanFromSession("PuntosMonitoreoVertTreeHandler");

        this.load();
    }

    public void load(){
        try{
            java.util.Map params = null;
             params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            String codigo = (String)FacesUtils.getFromSession("vertimientoVisor");
            if (codigo!=null&&params != null && params.get("vertimiento") != null) { 
                codigo=params.get("vertimiento").toString();
                System.out.println("codigo de captacion recibido en DetallVertimientoVistaBean:"+codigo);
            }
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
            ParametrosDelegate pd = ParametrosDelegate.getInstance();

           
            Long cod=  Long.parseLong(codigo);
            this.vertimiento = uad.getVertimiento(cod);
            FacesUtils.setInSession("vertimientoSeleccionado", this.vertimiento);
                
            this.listaTiposFuente = this.cargarParametro(ConstantesParametros.
                                                   CATEGORIA_TIPO_FUENTE);
            this.listaTipoCentroPoblado = this.cargarParametro(ConstantesParametros.
                                                   CATEGORIA_TIPO_CENTRO_POBLADO);
           
            this.listaTipoVertimiento = this.cargarParametro(ConstantesParametros.
                                                   CATEGORIA_TIPO_VERTIMIENTO);
            this.listaTipoFlujo = this.cargarParametro(ConstantesParametros.
                                                   CATEGORIA_TIPO_FLUJO_VERTIMIENTO);
        
           
            this.listaDias = this.cargarDiasMes();

   
            if(this.vertimiento.getIdTipoCentroPoblado()!=null){
                this.vertimiento.setObjTipoCentroPoblado(pd.getLista(this.vertimiento.getIdTipoCentroPoblado()));
            }
           
            if(this.vertimiento.getTipoVertimiento()!=null){
                this.vertimiento.setObjTipoVertimiento(pd.getLista(this.vertimiento.getTipoVertimiento()));
            }
            if(this.vertimiento.getTipoFlujo()!=null){
                this.vertimiento.setObjTipoFlujo(pd.getLista(this.vertimiento.getTipoFlujo()));
            }
           

            
        }catch(IdeamException e){
            showMessage(e);
        }
    }


   
   


   
   
   

  
      
            
            
          

  


  
    public void setVertimiento(PuntoVertimiento vertimiento) {
        this.vertimiento = vertimiento;
    }

    public PuntoVertimiento getVertimiento() {
        return vertimiento;
    }

    
    public void setListaTiposFuente(List listaTiposFuente) {
        this.listaTiposFuente = listaTiposFuente;
    }

    public List getListaTiposFuente() {
        return listaTiposFuente;
    }

 

   
    public void setListaTipoCentroPoblado(List listaTipoCentroPoblado) {
        this.listaTipoCentroPoblado = listaTipoCentroPoblado;
    }

    public List getListaTipoCentroPoblado() {
        return listaTipoCentroPoblado;
    }



    public void setListaTipoFlujo(List listaTipoFlujo) {
        this.listaTipoFlujo = listaTipoFlujo;
    }

    public List getListaTipoFlujo() {
        return listaTipoFlujo;
    }



    public void setListaDias(List listaDias) {
        this.listaDias = listaDias;
    }

    public List getListaDias() {
        return listaDias;
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

    public void setPanelSplitter1(RichPanelSplitter panelSplitter1) {
        this.panelSplitter1 = panelSplitter1;
    }

    public RichPanelSplitter getPanelSplitter1() {
        return panelSplitter1;
    }


    public void setPanelStretchLayout3(RichPanelStretchLayout panelStretchLayout3) {
        this.panelStretchLayout3 = panelStretchLayout3;
    }

    public RichPanelStretchLayout getPanelStretchLayout3() {
        return panelStretchLayout3;
    }

    public void setPanelGroupLayout1(RichPanelGroupLayout panelGroupLayout1) {
        this.panelGroupLayout1 = panelGroupLayout1;
    }

    public RichPanelGroupLayout getPanelGroupLayout1() {
        return panelGroupLayout1;
    }

    public void setPanelBox1(RichPanelBox panelBox1) {
        this.panelBox1 = panelBox1;
    }

    public RichPanelBox getPanelBox1() {
        return panelBox1;
    }

    public void setPanelGroupLayout2(RichPanelGroupLayout panelGroupLayout2) {
        this.panelGroupLayout2 = panelGroupLayout2;
    }

    public RichPanelGroupLayout getPanelGroupLayout2() {
        return panelGroupLayout2;
    }

    public void setPanelGroupLayout3(RichPanelGroupLayout panelGroupLayout3) {
        this.panelGroupLayout3 = panelGroupLayout3;
    }

    public RichPanelGroupLayout getPanelGroupLayout3() {
        return panelGroupLayout3;
    }

    public void setDecorativeBox1(RichDecorativeBox decorativeBox1) {
        this.decorativeBox1 = decorativeBox1;
    }

    public RichDecorativeBox getDecorativeBox1() {
        return decorativeBox1;
    }


    public void setPanelFormLayout1(RichPanelFormLayout panelFormLayout1) {
        this.panelFormLayout1 = panelFormLayout1;
    }

    public RichPanelFormLayout getPanelFormLayout1() {
        return panelFormLayout1;
    }


    public void setDecorativeBox3(RichDecorativeBox decorativeBox3) {
        this.decorativeBox3 = decorativeBox3;
    }

    public RichDecorativeBox getDecorativeBox3() {
        return decorativeBox3;
    }


    public void setPanelFormLayout3(RichPanelFormLayout panelFormLayout3) {
        this.panelFormLayout3 = panelFormLayout3;
    }

    public RichPanelFormLayout getPanelFormLayout3() {
        return panelFormLayout3;
    }


    public void setSoc_tipo_fuente(RichSelectOneChoice soc_tipo_fuente) {
        this.soc_tipo_fuente = soc_tipo_fuente;
    }

    public RichSelectOneChoice getSoc_tipo_fuente() {
        return soc_tipo_fuente;
    }

    public void setSi_tipo_fuente(UISelectItems si_tipo_fuente) {
        this.si_tipo_fuente = si_tipo_fuente;
    }

    public UISelectItems getSi_tipo_fuente() {
        return si_tipo_fuente;
    }


    public void setSocTipoCP(RichSelectOneChoice socTipoCP) {
        this.socTipoCP = socTipoCP;
    }

    public RichSelectOneChoice getSocTipoCP() {
        return socTipoCP;
    }

    public void setSiTipoCP(UISelectItems siTipoCP) {
        this.siTipoCP = siTipoCP;
    }

    public UISelectItems getSiTipoCP() {
        return siTipoCP;
    }

    public void setItNombreCentroPoblado(RichInputText itNombreCentroPoblado) {
        this.itNombreCentroPoblado = itNombreCentroPoblado;
    }

    public RichInputText getItNombreCentroPoblado() {
        return itNombreCentroPoblado;
    }

    public void setCbPrevUbicacion(RichCommandButton cbPrevUbicacion) {
        this.cbPrevUbicacion = cbPrevUbicacion;
    }

    public RichCommandButton getCbPrevUbicacion() {
        return cbPrevUbicacion;
    }

    public void setSpacer5(RichSpacer spacer5) {
        this.spacer5 = spacer5;
    }

    public RichSpacer getSpacer5() {
        return spacer5;
    }

    public void setCbNextUbicacion(RichCommandButton cbNextUbicacion) {
        this.cbNextUbicacion = cbNextUbicacion;
    }

    public RichCommandButton getCbNextUbicacion() {
        return cbNextUbicacion;
    }

    public void setSpacer6(RichSpacer spacer6) {
        this.spacer6 = spacer6;
    }

    public RichSpacer getSpacer6() {
        return spacer6;
    }


    public void setSocTipoFlujo(RichSelectOneChoice socTipoFlujo) {
        this.socTipoFlujo = socTipoFlujo;
    }

    public RichSelectOneChoice getSocTipoFlujo() {
        return socTipoFlujo;
    }

    public void setSiTipoFlujo(UISelectItems siTipoFlujo) {
        this.siTipoFlujo = siTipoFlujo;
    }

    public UISelectItems getSiTipoFlujo() {
        return siTipoFlujo;
    }

    public void setItTiempoDescarga(RichInputText itTiempoDescarga) {
        this.itTiempoDescarga = itTiempoDescarga;
    }

    public RichInputText getItTiempoDescarga() {
        return itTiempoDescarga;
    }

    public void setItCaudalDisegno(RichInputText itCaudalDisegno) {
        this.itCaudalDisegno = itCaudalDisegno;
    }

    public RichInputText getItCaudalDisegno() {
        return itCaudalDisegno;
    }

    public void setSocFrecuencia(RichSelectOneChoice socFrecuencia) {
        this.socFrecuencia = socFrecuencia;
    }

    public RichSelectOneChoice getSocFrecuencia() {
        return socFrecuencia;
    }

    public void setSiFrecuencia(UISelectItems siFrecuencia) {
        this.siFrecuencia = siFrecuencia;
    }

    public UISelectItems getSiFrecuencia() {
        return siFrecuencia;
    }


    public void setSpacer12(RichSpacer spacer12) {
        this.spacer12 = spacer12;
    }

    public RichSpacer getSpacer12() {
        return spacer12;
    }


    public void setOutputText4(RichOutputText outputText4) {
        this.outputText4 = outputText4;
    }

    public RichOutputText getOutputText4() {
        return outputText4;
    }


    public void setPanelGroupLayout15(RichPanelGroupLayout panelGroupLayout15) {
        this.panelGroupLayout15 = panelGroupLayout15;
    }

    public RichPanelGroupLayout getPanelGroupLayout15() {
        return panelGroupLayout15;
    }


    public void setPanelGroupLayout17(RichPanelGroupLayout panelGroupLayout17) {
        this.panelGroupLayout17 = panelGroupLayout17;
    }

    public RichPanelGroupLayout getPanelGroupLayout17() {
        return panelGroupLayout17;
    }


    public void setSpacer14(RichSpacer spacer14) {
        this.spacer14 = spacer14;
    }

    public RichSpacer getSpacer14() {
        return spacer14;
    }

    public void setOutputText7(RichOutputText outputText7) {
        this.outputText7 = outputText7;
    }

    public RichOutputText getOutputText7() {
        return outputText7;
    }




    public void setSpacer3(RichSpacer spacer3) {
        this.spacer3 = spacer3;
    }

    public RichSpacer getSpacer3() {
        return spacer3;
    }


    public void setITAREA(RichInputText inputText1) {
        this.ITAREA = inputText1;
    }

    public RichInputText getITAREA() {
        return ITAREA;
    }

    public void setITZONA(RichInputText inputText2) {
        this.ITZONA = inputText2;
    }

    public RichInputText getITZONA() {
        return ITZONA;
    }

    public void setITSUBZONA(RichInputText inputText3) {
        this.ITSUBZONA = inputText3;
    }

    public RichInputText getITSUBZONA() {
        return ITSUBZONA;
    }

    public void setIttipoFuente(RichInputText inputText4) {
        this.ittipoFuente = inputText4;
    }

    public RichInputText getIttipoFuente() {
        return ittipoFuente;
    }

    public void setItfuente(RichInputText inputText5) {
        this.itfuente = inputText5;
    }

    public RichInputText getItfuente() {
        return itfuente;
    }

    public void setIttramo(RichInputText inputText6) {
        this.ittramo = inputText6;
    }

    public RichInputText getIttramo() {
        return ittramo;
    }

    public void setIttipovert(RichInputText inputText7) {
        this.ittipovert = inputText7;
    }

    public RichInputText getIttipovert() {
        return ittipovert;
    }

    public void setItDpto(RichInputText inputText1) {
        this.itDpto = inputText1;
    }

    public RichInputText getItDpto() {
        return itDpto;
    }

    public void setItmcpo(RichInputText inputText2) {
        this.itmcpo = inputText2;
    }

    public RichInputText getItmcpo() {
        return itmcpo;
    }
}
