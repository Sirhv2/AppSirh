package co.gov.ideam.sirh.usuarios.agua.view;

import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.fuentes.view.FuenteDelegate;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.usuarios.agua.model.Captacion;
import co.gov.ideam.sirh.usuarios.agua.model.Concesion;
import co.gov.ideam.sirh.usuarios.agua.model.Predio;
import co.gov.ideam.sirh.usuarios.agua.model.RurtCaptacionUso;
import co.gov.ideam.sirh.usuarios.agua.model.UsuarioAgua;
import co.gov.ideam.sirh.util.ConstantesParametros;
import co.gov.ideam.sirh.util.IdeamException;

import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;

import java.math.BigDecimal;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UISelectItems;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichDecorativeBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.output.RichImage;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;

public class AdicionarUsoBean extends StandarBean {
    //uso seleccionado
    private RurtCaptacionUso uso;
    private Captacion captacion;
    
    //usuario en session
    private UsuarioAgua usuario;
    //predio en session
    private Predio predio;
    //concesion en session
    private Concesion concesion; 
 
    //listado de tipos de uso.
    private List listaTiposUso;
    //listado de tipos de uso.
    private List listaTiposUsoOtro;

    private RichPanelStretchLayout panelStretchLayout1;
    private RichPanelGroupLayout panelGroupLayout1;
    private RichPanelBox panelBox1;
    private RichPanelStretchLayout panelStretchLayout2;
    private RichPanelGroupLayout panelGroupLayout2;
    private RichPanelGroupLayout panelGroupLayout3;
    private RichCommandButton cbGuardar;
    private RichPanelGroupLayout panelGroupLayout4;
    private RichPanelFormLayout panelFormLayout1;
    private RichSelectOneChoice socTipoUso;
    private UISelectItems siTipoUso;
    private RichDecorativeBox decorativeBox1;
    private RichDecorativeBox decorativeBox2;
    private RichDecorativeBox decorativeBox3;
    private RichDecorativeBox decorativeBox4;
    private RichDecorativeBox decorativeBox5;
    private RichInputText itCaudalAsignadoDomestico;
    private RichPanelGroupLayout panelGroupLayout10;
    private RichInputText itPersonasPermanentes;
    private RichInputText itPersonasTransitorias;
    private RichInputText itAprovechamiento;
    private RichPanelGroupLayout panelGroupLayout11;
    private RichInputText itTipoAnimalPecuario;
    private RichInputText itCaudalAsignadoPecuario;
    private RichInputText itNumeroAnimalesPecuario;
    private RichPanelGroupLayout panelGroupLayout12;
    private RichInputText itTipoCultivo;
    private RichInputText itCaudalAsignadoAgricola;
    private RichInputText itProduccion;
    private RichInputText itEficiencia;
    private RichInputText itArea;
    private RichPanelGroupLayout panelGroupLayout13;
    private RichInputText itTipoAnimalAcuicola;
    private RichInputText itCaudalAsignadoAcuicola;
    private RichInputText itNumeroAnimalesAcuicola;
    private RichPanelGroupLayout panelGroupLayout14;
    private RichSelectOneChoice socTipoUsoOtro;
    private UISelectItems siTipoUsoOtro;
    private RichInputText itCaudalAsignadoOtro;
    private RichInputText itDescripcionOtro;
    private RichPopup p_registro_exitoso;
    private RichDialog d_registro_exitoso;
    private RichPanelGroupLayout panelGroupLayout15;
    private RichCommandButton cbAceptar;
    private RichPanelStretchLayout panelStretchLayout3;
    private RichImage image1;
    private RichDecorativeBox decorativeBox6;
    private RichPanelFormLayout panelFormLayout2;
    private RichPanelFormLayout panelFormLayout3;
    private RichPanelFormLayout panelFormLayout4;
    private RichPanelFormLayout panelFormLayout5;
    private RichPanelFormLayout panelFormLayout6;
    private RichSpacer spacer1;
    private RichPanelGroupLayout panelGroupLayout5;
    private RichPanelGroupLayout panelGroupLayout6;
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
    private RichSpacer spacer16;
    private RichCommandLink commandLink7;
    private RichOutputText outputText6;
    private RichDecorativeBox decorativeBox7;
    private String aceptarAction;

    public AdicionarUsoBean(){
        FacesUtils.setActiveBean("adicionarUsoBean",
                                 "Adicionar Uso",
                                 UsuariosAguaDelegate.class);
        this.load();
    }
    public void load(){
        try{
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
            Object obj = FacesUtils.getFromSession("usoSeleccionado");//la concesion desde la cual se inicia.
            if(obj instanceof RurtCaptacionUso){
                this.uso = (RurtCaptacionUso)obj;
            }else{
                Long codigo = (Long)FacesUtils.getFromSession("usoSeleccionado");
                //this.uso = uad.getUso(codigo);
            }

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
            




            this.listaTiposUso = this.cargarParametro(ConstantesParametros.
                                                   CATEGORIA_USOS_AGUA);

            this.listaTiposUsoOtro = this.cargarParametro(ConstantesParametros.
                                                   CATEGORIA_OTROS_USOS_AGUA);
        }catch(IdeamException e){
            showMessage(e);
        }
    }

    public void socTipoUso_valueChangeListener(ValueChangeEvent event) throws IdeamException{
        Object tipoUso = event.getNewValue();
        if(tipoUso != null){
            if(((Lista)tipoUso).getCodigo().longValue()==ConstantesParametros.LISTA_USO_DOMESTICO){
                this.decorativeBox1.setVisible(true);
                this.decorativeBox2.setVisible(false);
                this.decorativeBox3.setVisible(false);
                this.decorativeBox4.setVisible(false);
                this.decorativeBox5.setVisible(false);
                this.decorativeBox7.setVisible(true);
                
            }else if(((Lista)tipoUso).getCodigo().longValue()==ConstantesParametros.LISTA_USO_PECUARIO){
                this.decorativeBox1.setVisible(false);
                this.decorativeBox2.setVisible(false);
                this.decorativeBox3.setVisible(true);
                this.decorativeBox4.setVisible(false);
                this.decorativeBox5.setVisible(false);
                this.decorativeBox7.setVisible(true);
            }else if(((Lista)tipoUso).getCodigo().longValue()==ConstantesParametros.LISTA_USO_ACUICOLA){
                this.decorativeBox1.setVisible(false);
                this.decorativeBox2.setVisible(false);
                this.decorativeBox3.setVisible(false);
                this.decorativeBox4.setVisible(true);
                this.decorativeBox5.setVisible(false);
                this.decorativeBox7.setVisible(true);
            }else if(((Lista)tipoUso).getCodigo().longValue()==ConstantesParametros.LISTA_USO_AGRICOLA){
                this.decorativeBox1.setVisible(false);
                this.decorativeBox2.setVisible(true);
                this.decorativeBox3.setVisible(false);
                this.decorativeBox4.setVisible(false);
                this.decorativeBox5.setVisible(false);
                this.decorativeBox7.setVisible(true);
            }else{
                this.decorativeBox1.setVisible(false);
                this.decorativeBox2.setVisible(false);
                this.decorativeBox3.setVisible(false);
                this.decorativeBox4.setVisible(false);
                this.decorativeBox5.setVisible(true);
                this.decorativeBox7.setVisible(true);
            }
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.decorativeBox1);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.decorativeBox2);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.decorativeBox3);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.decorativeBox4);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.decorativeBox5);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.decorativeBox7);
        }
    }


    public void cbGuardar_actionListener(ActionEvent actionEvent) throws IdeamException{
        boolean continuar = true;

        if(continuar){
            this.save();
        }
    }

    public void save(){
        try{
            String caudal=null;
            String animal=null;
            this.uso = new RurtCaptacionUso();

            if(this.itCaudalAsignadoDomestico.getValue()!=null){
                caudal = ""+this.itCaudalAsignadoDomestico.getValue();
            }else if(this.itCaudalAsignadoAgricola.getValue()!=null){
                caudal = ""+this.itCaudalAsignadoAgricola.getValue();
            }else if(this.itCaudalAsignadoAcuicola.getValue()!=null){
                caudal = ""+this.itCaudalAsignadoAcuicola.getValue();
            }else if(this.itCaudalAsignadoPecuario.getValue()!=null){
                caudal = ""+this.itCaudalAsignadoPecuario.getValue();
            }else if(this.itCaudalAsignadoOtro.getValue()!=null){
                caudal = ""+this.itCaudalAsignadoOtro.getValue();
            }

            this.uso.setCaudalAsignado((caudal!=null)?new Double(caudal):null);

            if(this.itTipoAnimalAcuicola.getValue()!=null){
                this.uso.setTipo(this.itTipoAnimalAcuicola.getValue().toString());
            }else if(this.itTipoAnimalPecuario.getValue()!=null){
                this.uso.setTipo(this.itTipoAnimalPecuario.getValue().toString());
            }else if(this.itTipoCultivo.getValue()!=null){
                this.uso.setTipo(this.itTipoCultivo.getValue().toString());
            }else if(this.socTipoUsoOtro.getValue()!=null){
                this.uso.setTipo(((Lista)this.socTipoUsoOtro.getValue()).getCodigo().toString());
            }

            if(this.itNumeroAnimalesAcuicola.getValue()!=null){
                this.uso.setCantidadAnimales(Long.parseLong(this.itNumeroAnimalesAcuicola.getValue().toString()));
            }else if(this.itNumeroAnimalesPecuario.getValue()!=null){
                this.uso.setCantidadAnimales(Long.parseLong(this.itNumeroAnimalesPecuario.getValue().toString()));
            }

            //domestico
            this.uso.setCantidadPersonasPermanentes((this.itPersonasPermanentes.getValue()!=null)?new Integer(this.itPersonasPermanentes.getValue().toString()):null);
            this.uso.setCantidadPersonasTransitorias((this.itPersonasTransitorias.getValue()!=null)?new Integer(this.itPersonasTransitorias.getValue().toString()):null);
            this.uso.setAprovechamiento((this.itAprovechamiento.getValue()!=null)?Double.parseDouble(""+this.itAprovechamiento.getValue()):null);

            //agricola
            this.uso.setProduccion((this.itProduccion.getValue()!=null)?Double.parseDouble(""+this.itProduccion.getValue()):null);
            this.uso.setEficiencia((this.itEficiencia.getValue()!=null)?Double.parseDouble(""+this.itEficiencia.getValue()):null);
            this.uso.setArea((this.itArea.getValue()!=null)?Double.parseDouble(this.itArea.getValue().toString()):null);

            //otro
            this.uso.setDescripcion((this.itDescripcionOtro.getValue()!=null)?this.itDescripcionOtro.getValue().toString():null);
            this.uso.setTipoUso(((Lista)this.socTipoUso.getValue()).getCodigo());
            this.uso.setIdCaptacion(this.captacion.getCodigo());

            UsuarioConectadoTO usuarioConectado =
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
            this.uso.setCodigoAutoridad(usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue());
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
            uad.createUso(this.uso);

            showPopup(p_registro_exitoso, true);
        }catch(IdeamException e){
            showMessage(e);
        }
    }





    public void setUso(RurtCaptacionUso uso) {
        this.uso = uso;
    }

    public RurtCaptacionUso getUso() {
        return uso;
    }

    public void setListaTiposUso(List listaTiposUso) {
        this.listaTiposUso = listaTiposUso;
    }

    public List getListaTiposUso() {
        return listaTiposUso;
    }

    public void setListaTiposUsoOtro(List listaTiposUsoOtro) {
        this.listaTiposUsoOtro = listaTiposUsoOtro;
    }

    public List getListaTiposUsoOtro() {
        return listaTiposUsoOtro;
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

    public void setPanelBox1(RichPanelBox panelBox1) {
        this.panelBox1 = panelBox1;
    }

    public RichPanelBox getPanelBox1() {
        return panelBox1;
    }

    public void setPanelStretchLayout2(RichPanelStretchLayout panelStretchLayout2) {
        this.panelStretchLayout2 = panelStretchLayout2;
    }

    public RichPanelStretchLayout getPanelStretchLayout2() {
        return panelStretchLayout2;
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

    public void setCbGuardar(RichCommandButton commandButton1) {
        this.cbGuardar = commandButton1;
    }

    public RichCommandButton getCbGuardar() {
        return cbGuardar;
    }

    public void setPanelGroupLayout4(RichPanelGroupLayout panelGroupLayout4) {
        this.panelGroupLayout4 = panelGroupLayout4;
    }

    public RichPanelGroupLayout getPanelGroupLayout4() {
        return panelGroupLayout4;
    }

    public void setPanelFormLayout1(RichPanelFormLayout panelFormLayout1) {
        this.panelFormLayout1 = panelFormLayout1;
    }

    public RichPanelFormLayout getPanelFormLayout1() {
        return panelFormLayout1;
    }

    public void setSocTipoUso(RichSelectOneChoice selectOneChoice1) {
        this.socTipoUso = selectOneChoice1;
    }

    public RichSelectOneChoice getSocTipoUso() {
        return socTipoUso;
    }

    public void setSiTipoUso(UISelectItems selectItems1) {
        this.siTipoUso = selectItems1;
    }

    public UISelectItems getSiTipoUso() {
        return siTipoUso;
    }

    public void setDecorativeBox1(RichDecorativeBox decorativeBox1) {
        this.decorativeBox1 = decorativeBox1;
    }

    public RichDecorativeBox getDecorativeBox1() {
        return decorativeBox1;
    }

    public void setDecorativeBox2(RichDecorativeBox decorativeBox2) {
        this.decorativeBox2 = decorativeBox2;
    }

    public RichDecorativeBox getDecorativeBox2() {
        return decorativeBox2;
    }

    public void setDecorativeBox3(RichDecorativeBox decorativeBox3) {
        this.decorativeBox3 = decorativeBox3;
    }

    public RichDecorativeBox getDecorativeBox3() {
        return decorativeBox3;
    }

    public void setDecorativeBox4(RichDecorativeBox decorativeBox4) {
        this.decorativeBox4 = decorativeBox4;
    }

    public RichDecorativeBox getDecorativeBox4() {
        return decorativeBox4;
    }

    public void setDecorativeBox5(RichDecorativeBox decorativeBox5) {
        this.decorativeBox5 = decorativeBox5;
    }

    public RichDecorativeBox getDecorativeBox5() {
        return decorativeBox5;
    }


    public void setItCaudalAsignadoDomestico(RichInputText inputText2) {
        this.itCaudalAsignadoDomestico = inputText2;
    }

    public RichInputText getItCaudalAsignadoDomestico() {
        return itCaudalAsignadoDomestico;
    }

    public void setPanelGroupLayout10(RichPanelGroupLayout panelGroupLayout10) {
        this.panelGroupLayout10 = panelGroupLayout10;
    }

    public RichPanelGroupLayout getPanelGroupLayout10() {
        return panelGroupLayout10;
    }

    public void setItPersonasPermanentes(RichInputText inputText2) {
        this.itPersonasPermanentes = inputText2;
    }

    public RichInputText getItPersonasPermanentes() {
        return itPersonasPermanentes;
    }

    public void setItPersonasTransitorias(RichInputText inputText2) {
        this.itPersonasTransitorias = inputText2;
    }

    public RichInputText getItPersonasTransitorias() {
        return itPersonasTransitorias;
    }

    public void setItAprovechamiento(RichInputText inputText2) {
        this.itAprovechamiento = inputText2;
    }

    public RichInputText getItAprovechamiento() {
        return itAprovechamiento;
    }

    public void setPanelGroupLayout11(RichPanelGroupLayout panelGroupLayout11) {
        this.panelGroupLayout11 = panelGroupLayout11;
    }

    public RichPanelGroupLayout getPanelGroupLayout11() {
        return panelGroupLayout11;
    }

    public void setItTipoAnimalPecuario(RichInputText inputText1) {
        this.itTipoAnimalPecuario = inputText1;
    }

    public RichInputText getItTipoAnimalPecuario() {
        return itTipoAnimalPecuario;
    }

    public void setItCaudalAsignadoPecuario(RichInputText inputText1) {
        this.itCaudalAsignadoPecuario = inputText1;
    }

    public RichInputText getItCaudalAsignadoPecuario() {
        return itCaudalAsignadoPecuario;
    }

    public void setItNumeroAnimalesPecuario(RichInputText inputText1) {
        this.itNumeroAnimalesPecuario = inputText1;
    }

    public RichInputText getItNumeroAnimalesPecuario() {
        return itNumeroAnimalesPecuario;
    }

    public void setPanelGroupLayout12(RichPanelGroupLayout panelGroupLayout12) {
        this.panelGroupLayout12 = panelGroupLayout12;
    }

    public RichPanelGroupLayout getPanelGroupLayout12() {
        return panelGroupLayout12;
    }

    public void setItTipoCultivo(RichInputText inputText1) {
        this.itTipoCultivo = inputText1;
    }

    public RichInputText getItTipoCultivo() {
        return itTipoCultivo;
    }

    public void setItCaudalAsignadoAgricola(RichInputText inputText1) {
        this.itCaudalAsignadoAgricola = inputText1;
    }

    public RichInputText getItCaudalAsignadoAgricola() {
        return itCaudalAsignadoAgricola;
    }

    public void setItProduccion(RichInputText inputText1) {
        this.itProduccion = inputText1;
    }

    public RichInputText getItProduccion() {
        return itProduccion;
    }

    public void setItEficiencia(RichInputText inputText1) {
        this.itEficiencia = inputText1;
    }

    public RichInputText getItEficiencia() {
        return itEficiencia;
    }

    public void setItArea(RichInputText inputText1) {
        this.itArea = inputText1;
    }

    public RichInputText getItArea() {
        return itArea;
    }

    public void setPanelGroupLayout13(RichPanelGroupLayout panelGroupLayout13) {
        this.panelGroupLayout13 = panelGroupLayout13;
    }

    public RichPanelGroupLayout getPanelGroupLayout13() {
        return panelGroupLayout13;
    }

    public void setItTipoAnimalAcuicola(RichInputText inputText1) {
        this.itTipoAnimalAcuicola = inputText1;
    }

    public RichInputText getItTipoAnimalAcuicola() {
        return itTipoAnimalAcuicola;
    }

    public void setItCaudalAsignadoAcuicola(RichInputText inputText1) {
        this.itCaudalAsignadoAcuicola = inputText1;
    }

    public RichInputText getItCaudalAsignadoAcuicola() {
        return itCaudalAsignadoAcuicola;
    }

    public void setItNumeroAnimalesAcuicola(RichInputText inputText1) {
        this.itNumeroAnimalesAcuicola = inputText1;
    }

    public RichInputText getItNumeroAnimalesAcuicola() {
        return itNumeroAnimalesAcuicola;
    }

    public void setPanelGroupLayout14(RichPanelGroupLayout panelGroupLayout14) {
        this.panelGroupLayout14 = panelGroupLayout14;
    }

    public RichPanelGroupLayout getPanelGroupLayout14() {
        return panelGroupLayout14;
    }

    public void setSocTipoUsoOtro(RichSelectOneChoice selectOneChoice1) {
        this.socTipoUsoOtro = selectOneChoice1;
    }

    public RichSelectOneChoice getSocTipoUsoOtro() {
        return socTipoUsoOtro;
    }

    public void setSiTipoUsoOtro(UISelectItems selectItems1) {
        this.siTipoUsoOtro = selectItems1;
    }

    public UISelectItems getSiTipoUsoOtro() {
        return siTipoUsoOtro;
    }


    public void setItCaudalAsignadoOtro(RichInputText inputText1) {
        this.itCaudalAsignadoOtro = inputText1;
    }

    public RichInputText getItCaudalAsignadoOtro() {
        return itCaudalAsignadoOtro;
    }

    public void setItDescripcionOtro(RichInputText inputText1) {
        this.itDescripcionOtro = inputText1;
    }

    public RichInputText getItDescripcionOtro() {
        return itDescripcionOtro;
    }

    public void setP_registro_exitoso(RichPopup popup1) {
        this.p_registro_exitoso = popup1;
    }

    public RichPopup getP_registro_exitoso() {
        return p_registro_exitoso;
    }

    public void setD_registro_exitoso(RichDialog dialog1) {
        this.d_registro_exitoso = dialog1;
    }

    public RichDialog getD_registro_exitoso() {
        return d_registro_exitoso;
    }

    public void setPanelGroupLayout15(RichPanelGroupLayout panelGroupLayout15) {
        this.panelGroupLayout15 = panelGroupLayout15;
    }

    public RichPanelGroupLayout getPanelGroupLayout15() {
        return panelGroupLayout15;
    }

    public void setCbAceptar(RichCommandButton commandButton1) {
        this.cbAceptar = commandButton1;
    }

    public RichCommandButton getCbAceptar() {
        return cbAceptar;
    }
    

    public void cb_aceptar_actionListener(ActionEvent actionEvent) {
        this.setAceptarAction("detalleCaptacion");
    }

    
    

    public String getAceptarAction() {
        return aceptarAction;
    }

    public void setAceptarAction(String aceptarAction) {
        this.aceptarAction = aceptarAction;
    }

    public void setPanelStretchLayout3(RichPanelStretchLayout panelStretchLayout3) {
        this.panelStretchLayout3 = panelStretchLayout3;
    }

    public RichPanelStretchLayout getPanelStretchLayout3() {
        return panelStretchLayout3;
    }


    public void setImage1(RichImage image1) {
        this.image1 = image1;
    }

    public RichImage getImage1() {
        return image1;
    }

    public void setDecorativeBox6(RichDecorativeBox decorativeBox6) {
        this.decorativeBox6 = decorativeBox6;
    }

    public RichDecorativeBox getDecorativeBox6() {
        return decorativeBox6;
    }

    public void setPanelFormLayout2(RichPanelFormLayout panelFormLayout2) {
        this.panelFormLayout2 = panelFormLayout2;
    }

    public RichPanelFormLayout getPanelFormLayout2() {
        return panelFormLayout2;
    }

    public void setPanelFormLayout3(RichPanelFormLayout panelFormLayout3) {
        this.panelFormLayout3 = panelFormLayout3;
    }

    public RichPanelFormLayout getPanelFormLayout3() {
        return panelFormLayout3;
    }

    public void setPanelFormLayout4(RichPanelFormLayout panelFormLayout4) {
        this.panelFormLayout4 = panelFormLayout4;
    }

    public RichPanelFormLayout getPanelFormLayout4() {
        return panelFormLayout4;
    }

    public void setPanelFormLayout5(RichPanelFormLayout panelFormLayout5) {
        this.panelFormLayout5 = panelFormLayout5;
    }

    public RichPanelFormLayout getPanelFormLayout5() {
        return panelFormLayout5;
    }

    public void setPanelFormLayout6(RichPanelFormLayout panelFormLayout6) {
        this.panelFormLayout6 = panelFormLayout6;
    }

    public RichPanelFormLayout getPanelFormLayout6() {
        return panelFormLayout6;
    }

    public void setSpacer1(RichSpacer spacer1) {
        this.spacer1 = spacer1;
    }

    public RichSpacer getSpacer1() {
        return spacer1;
    }

    public void setPanelGroupLayout5(RichPanelGroupLayout panelGroupLayout5) {
        this.panelGroupLayout5 = panelGroupLayout5;
    }

    public RichPanelGroupLayout getPanelGroupLayout5() {
        return panelGroupLayout5;
    }

    public void setPanelGroupLayout6(RichPanelGroupLayout panelGroupLayout6) {
        this.panelGroupLayout6 = panelGroupLayout6;
    }

    public RichPanelGroupLayout getPanelGroupLayout6() {
        return panelGroupLayout6;
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


    public void setSpacer16(RichSpacer spacer16) {
        this.spacer16 = spacer16;
    }

    public RichSpacer getSpacer16() {
        return spacer16;
    }

    public void setCommandLink7(RichCommandLink commandLink7) {
        this.commandLink7 = commandLink7;
    }

    public RichCommandLink getCommandLink7() {
        return commandLink7;
    }


    public void setCaptacion(Captacion captacion) {
        this.captacion = captacion;
    }

    public Captacion getCaptacion() {
        return captacion;
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

    public void setOutputText6(RichOutputText outputText6) {
        this.outputText6 = outputText6;
    }

    public RichOutputText getOutputText6() {
        return outputText6;
    }

    public void setDecorativeBox7(RichDecorativeBox decorativeBox7) {
        this.decorativeBox7 = decorativeBox7;
    }

    public RichDecorativeBox getDecorativeBox7() {
        return decorativeBox7;
    }


}
