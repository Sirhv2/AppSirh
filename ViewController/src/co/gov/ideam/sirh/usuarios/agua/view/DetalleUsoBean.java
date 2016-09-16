package co.gov.ideam.sirh.usuarios.agua.view;

import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.view.ParametrosDelegate;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.usuarios.agua.model.Captacion;

import co.gov.ideam.sirh.usuarios.agua.model.Concesion;
import co.gov.ideam.sirh.usuarios.agua.model.Predio;
import co.gov.ideam.sirh.usuarios.agua.model.RurtCaptacionUso;

import co.gov.ideam.sirh.usuarios.agua.model.UsuarioAgua;
import co.gov.ideam.sirh.util.Constantes;
import co.gov.ideam.sirh.util.ConstantesParametros;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;

import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.application.FacesMessage;
import javax.faces.component.UISelectItems;

import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.layout.RichDecorativeBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.output.RichImage;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;

public class DetalleUsoBean extends StandarBean{  
    //usuario en session
    private UsuarioAgua usuario;
    //predio en session
    private Predio predio;
    //concesion en session
    private Concesion concesion; 
    //captacion seleccionada
    private Captacion captacion;
    //uso seleccionado
    private RurtCaptacionUso uso;
    //listado de tipos de uso.
    private List listaTiposUsoOtro;
    
    private boolean domestico;
    private boolean agricola;
    private boolean pecuario;
    private boolean acuicola;
    private boolean otro;
    
    
    private RichForm form1;
    private RichDocument document1;
    private RichPanelStretchLayout panelStretchLayout1;
    private RichPanelGroupLayout panelGroupLayout1;
    private RichPanelSplitter panelSplitter1;
    private RichPanelStretchLayout panelStretchLayout2;
    private RichPanelGroupLayout panelGroupLayout2;
    private RichPanelBox panelBox1;
    private RichPopup popup_borrar;
    private RichPopup p_registro_exitoso;
    private RichPanelGroupLayout panelGroupLayout3;
    private RichPanelGroupLayout panelGroupLayout4;
    private RichDecorativeBox decorativeBox1;
    private RichDecorativeBox decorativeBox2;
    private RichDecorativeBox decorativeBox3;
    private RichDecorativeBox decorativeBox4;
    private RichDecorativeBox decorativeBox5;
    private RichDecorativeBox decorativeBox6;
    private RichPanelFormLayout panelFormLayout1;
    private RichPanelFormLayout panelFormLayout2;
    private RichPanelFormLayout panelFormLayout3;
    private RichPanelFormLayout panelFormLayout4;
    private RichPanelFormLayout panelFormLayout5;
    private RichPanelGroupLayout panelGroupLayout5;
    private RichPanelGroupLayout panelGroupLayout6;
    private RichPanelGroupLayout panelGroupLayout7;
    private RichPanelGroupLayout panelGroupLayout8;
    private RichPanelGroupLayout panelGroupLayout9;
    private RichPanelGroupLayout panelGroupLayout10;
    private RichSpacer spacer1;
    private RichPanelGroupLayout panelGroupLayout11;
    private RichCommandButton cbAceptar;
    private RichSpacer spacer2;
    private RichCommandButton cbBorrar;
    private RichDialog d_borrar;
    private RichDialog d_registro_exitoso;
    private RichPanelGroupLayout panelGroupLayout12;
    private RichOutputText ot_borrar_1;
    private RichSpacer spacer3;
    private RichOutputText ot_borrar_2;
    private RichPanelGroupLayout panelGroupLayout13;
    private RichCommandButton cb_si_borrar;
    private RichSpacer spacer4;
    private RichCommandButton cb_no_borrar;
    private RichPanelStretchLayout panelStretchLayout3;
    private RichOutputText ot8;
    private RichImage i1;
    private RichSpacer spacer5;
    private RichOutputText outputText1;
    private RichSpacer spacer6;
    private RichOutputText outputText2;
    private RichSpacer spacer7;
    private RichOutputText outputText3;
    private RichSpacer spacer8;
    private RichOutputText outputText4;
    private RichSpacer spacer9;
    private RichOutputText outputText5;
    private RichInputText itPersonasPermanentes;
    private RichInputText itPersonasTransitorias;
    private RichInputText itAprovechamiento;
    private RichInputText itCaudalAsignadoDomestico;
    private RichInputText itTipoAnimalPecuario;
    private RichInputText itCaudalAsignadoPecuario;
    private RichInputText itNumeroAnimalesPecuario;
    private RichInputText itTipoCultivo;
    private RichInputText itCaudalAsignadoAgricola;
    private RichInputText itProduccion;
    private RichInputText itEficiencia;
    private RichInputText itArea;
    private RichInputText itTipoAnimalAcuicola;
    private RichInputText itCaudalAsignadoAcuicola;
    private RichInputText itNumeroAnimalesAcuicola;
    private RichSelectOneChoice socTipoUsoOtro;
    private UISelectItems siTipoUsoOtro;
    private RichInputText itCaudalAsignadoOtro;
    private RichInputText itDescripcionOtro;
    private RichCommandButton cbAceptarDi;
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
    private RichCommandLink commandLink6;
    private RichSpacer spacer16;
    private RichCommandLink commandLink7;
    private RichSpacer spacer17;
    private RichOutputText outputText6;


    public DetalleUsoBean(){
        FacesUtils.setActiveBean("detalleUsoBean", "Detalle Uso", 
                                 UsuariosAguaDelegate.class);
        
        FacesUtils.removeManagedBeanFromSession("UsosTreeHandler");
        this.load();
    }
    
    public void load(){
        try{
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
            ParametrosDelegate pd = ParametrosDelegate.getInstance(); 
            Object obj = FacesUtils.getFromSession("usoSeleccionado");//la concesion desde la cual se inicia.
            if(obj instanceof RurtCaptacionUso){
                this.uso = (RurtCaptacionUso)obj;
            }else{                
                Long codigo = (Long)FacesUtils.getFromSession("usoSeleccionado");                                            
                this.uso = uad.getUso(codigo);                
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
            
            
            
            if(this.uso.getTipoUso().longValue()==ConstantesParametros.LISTA_USO_DOMESTICO){
                this.domestico = true;
                this.agricola = false;
                this.pecuario = false;
                this.acuicola = false;
                this.otro = false;
            }else if(this.uso.getTipoUso().longValue()==ConstantesParametros.LISTA_USO_PECUARIO){
                this.domestico = false;
                this.agricola = false;
                this.pecuario = true;
                this.acuicola = false;
                this.otro = false;
            }else if(this.uso.getTipoUso().longValue()==ConstantesParametros.LISTA_USO_ACUICOLA){
                this.domestico = false;
                this.agricola = false;
                this.pecuario = false;
                this.acuicola = true;
                this.otro = false;
            }else if(this.uso.getTipoUso().longValue()==ConstantesParametros.LISTA_USO_AGRICOLA){
                this.domestico = false;
                this.agricola = true;
                this.pecuario = false;
                this.acuicola = false;
                this.otro = false;
            }else{//otro uso
                this.domestico = false;
                this.agricola = false;
                this.pecuario = false;
                this.acuicola = false;
                this.otro = true;
                this.listaTiposUsoOtro = this.cargarParametro(ConstantesParametros.
                                                       CATEGORIA_OTROS_USOS_AGUA);
                if(this.uso.getTipo()!=null&&!this.uso.getTipo().trim().equals(""))
                 this.uso.setObjOtroUso(pd.getLista(new Integer(this.uso.getTipo())));
            }            
            
        }catch(IdeamException e){
            showMessage(e);
        }
    }
    
    
    public void cbAceptar_actionListener(ActionEvent actionEvent) {
        boolean continuar = true;
        UsuarioConectadoTO usuarioConectado = 
            (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
        
        Autoridades autoridadAmbiental = usuarioConectado.getUsuario().getAutoridadAmbiental();
        if(autoridadAmbiental==null ||
                autoridadAmbiental.getId().intValue() == Constantes.IDEAM){
            showMessage(getText("NO_EXISTE_AUTORIDAD_AMBIENTAL"),
                        FacesMessage.SEVERITY_ERROR); 
            continuar = false;
        }
        
        if(continuar){
            this.save(); 
            showPopup(p_registro_exitoso, true);
        }          
    }

    public void cbBorrar_actionListener(ActionEvent actionEvent) {
        String nombre = ""+this.uso.getTipoUso();
        String params[] = {nombre};
        String texto = getText("USO_BORRAR", params);
        ot_borrar_1.setValue(texto);
        ot_borrar_2.setValue("");
        UsuarioConectadoTO usuarioConectado = 
            (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
        
        Autoridades autoridadAmbiental = usuarioConectado.getUsuario().getAutoridadAmbiental();
        if(autoridadAmbiental==null ||
                autoridadAmbiental.getId().intValue() == Constantes.IDEAM){
            showMessage(getText("NO_EXISTE_AUTORIDAD_AMBIENTAL"),
                        FacesMessage.SEVERITY_ERROR); 
        }else{
            System.out.println("------------ENTRO");
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.popup_borrar);
            showPopup(this.popup_borrar, true);
            System.out.println("------------SALIO");
        }        
    }
    
    public void cb_si_borrar_actionListener(ActionEvent actionEvent) {
        try{
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
            uad.deleteUso(this.uso);
            showMessage(getText("USO_ELIMINADO"));
        }catch(IdeamException e){
            showMessage(e.getMessage());
        }
    }

    public void cb_no_borrar_actionListener(ActionEvent actionEvent) {
        showPopup(popup_borrar, false);
    }
    
    
    public void save(){
        try{
            String caudal=null;            
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
                this.uso.setTipo(this.itNumeroAnimalesPecuario.getValue().toString());
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
        
            this.uso.setIdCaptacion(this.captacion.getCodigo());
            
            UsuarioConectadoTO usuarioConectado = 
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
            
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
            uad.updateUso(this.uso);
            
        }catch(IdeamException e){
            showMessage(e);
        }
    }
    
    //////
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
    
    public void setCaptacion(Captacion captacion) {
        this.captacion = captacion;
    }

    public Captacion getCaptacion() {
        return captacion;
    }

    public void setUso(RurtCaptacionUso uso) {
        this.uso = uso;
    }

    public RurtCaptacionUso getUso() {
        return uso;
    }

    public void setListaTiposUsoOtro(List listaTiposUsoOtro) {
        this.listaTiposUsoOtro = listaTiposUsoOtro;
    }

    public List getListaTiposUsoOtro() {
        return listaTiposUsoOtro;
    }

    public void setDomestico(boolean domestico) {
        this.domestico = domestico;
    }

    public boolean isDomestico() {
        return domestico;
    }

    public void setAgricola(boolean agricola) {
        this.agricola = agricola;
    }

    public boolean isAgricola() {
        return agricola;
    }

    public void setPecuario(boolean pecuario) {
        this.pecuario = pecuario;
    }

    public boolean isPecuario() {
        return pecuario;
    }

    public void setAcuicola(boolean acuicola) {
        this.acuicola = acuicola;
    }

    public boolean isAcuicola() {
        return acuicola;
    }

    public void setOtro(boolean otro) {
        this.otro = otro;
    }

    public boolean isOtro() {
        return otro;
    }
    
    public RichCommandButton getCb_no_borrar() {
        return cb_no_borrar;
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

    public void setPanelSplitter1(RichPanelSplitter panelSplitter1) {
        this.panelSplitter1 = panelSplitter1;
    }

    public RichPanelSplitter getPanelSplitter1() {
        return panelSplitter1;
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

    public void setPanelBox1(RichPanelBox panelBox1) {
        this.panelBox1 = panelBox1;
    }

    public RichPanelBox getPanelBox1() {
        return panelBox1;
    }

    public void setPopup_borrar(RichPopup popup1) {
        this.popup_borrar = popup1;
    }

    public RichPopup getPopup_borrar() {
        return popup_borrar;
    }

    public void setP_registro_exitoso(RichPopup popup2) {
        this.p_registro_exitoso = popup2;
    }

    public RichPopup getP_registro_exitoso() {
        return p_registro_exitoso;
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

    public void setDecorativeBox6(RichDecorativeBox decorativeBox6) {
        this.decorativeBox6 = decorativeBox6;
    }

    public RichDecorativeBox getDecorativeBox6() {
        return decorativeBox6;
    }

    public void setPanelFormLayout1(RichPanelFormLayout panelFormLayout1) {
        this.panelFormLayout1 = panelFormLayout1;
    }

    public RichPanelFormLayout getPanelFormLayout1() {
        return panelFormLayout1;
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

    public void setPanelGroupLayout7(RichPanelGroupLayout panelGroupLayout7) {
        this.panelGroupLayout7 = panelGroupLayout7;
    }

    public RichPanelGroupLayout getPanelGroupLayout7() {
        return panelGroupLayout7;
    }

    public void setPanelGroupLayout8(RichPanelGroupLayout panelGroupLayout8) {
        this.panelGroupLayout8 = panelGroupLayout8;
    }

    public RichPanelGroupLayout getPanelGroupLayout8() {
        return panelGroupLayout8;
    }

    public void setPanelGroupLayout9(RichPanelGroupLayout panelGroupLayout9) {
        this.panelGroupLayout9 = panelGroupLayout9;
    }

    public RichPanelGroupLayout getPanelGroupLayout9() {
        return panelGroupLayout9;
    }

    public void setPanelGroupLayout10(RichPanelGroupLayout panelGroupLayout10) {
        this.panelGroupLayout10 = panelGroupLayout10;
    }

    public RichPanelGroupLayout getPanelGroupLayout10() {
        return panelGroupLayout10;
    }

    public void setSpacer1(RichSpacer spacer1) {
        this.spacer1 = spacer1;
    }

    public RichSpacer getSpacer1() {
        return spacer1;
    }

    public void setPanelGroupLayout11(RichPanelGroupLayout panelGroupLayout11) {
        this.panelGroupLayout11 = panelGroupLayout11;
    }

    public RichPanelGroupLayout getPanelGroupLayout11() {
        return panelGroupLayout11;
    }

    public void setCbAceptar(RichCommandButton commandButton1) {
        this.cbAceptar = commandButton1;
    }

    public RichCommandButton getCbAceptar() {
        return cbAceptar;
    }

    public void setSpacer2(RichSpacer spacer2) {
        this.spacer2 = spacer2;
    }

    public RichSpacer getSpacer2() {
        return spacer2;
    }

    public void setCbBorrar(RichCommandButton commandButton2) {
        this.cbBorrar = commandButton2;
    }

    public RichCommandButton getCbBorrar() {
        return cbBorrar;
    }

    public void setD_borrar(RichDialog dialog1) {
        this.d_borrar = dialog1;
    }

    public RichDialog getD_borrar() {
        return d_borrar;
    }

    public void setD_registro_exitoso(RichDialog dialog2) {
        this.d_registro_exitoso = dialog2;
    }

    public RichDialog getD_registro_exitoso() {
        return d_registro_exitoso;
    }

    public void setPanelGroupLayout12(RichPanelGroupLayout panelGroupLayout12) {
        this.panelGroupLayout12 = panelGroupLayout12;
    }

    public RichPanelGroupLayout getPanelGroupLayout12() {
        return panelGroupLayout12;
    }

    public void setOt_borrar_1(RichOutputText outputText1) {
        this.ot_borrar_1 = outputText1;
    }

    public RichOutputText getOt_borrar_1() {
        return ot_borrar_1;
    }

    public void setSpacer3(RichSpacer spacer3) {
        this.spacer3 = spacer3;
    }

    public RichSpacer getSpacer3() {
        return spacer3;
    }

    public void setOt_borrar_2(RichOutputText outputText2) {
        this.ot_borrar_2 = outputText2;
    }

    public RichOutputText getOt_borrar_2() {
        return ot_borrar_2;
    }

    public void setPanelGroupLayout13(RichPanelGroupLayout panelGroupLayout13) {
        this.panelGroupLayout13 = panelGroupLayout13;
    }

    public RichPanelGroupLayout getPanelGroupLayout13() {
        return panelGroupLayout13;
    }

    public void setCb_si_borrar(RichCommandButton commandButton1) {
        this.cb_si_borrar = commandButton1;
    }

    public RichCommandButton getCb_si_borrar() {
        return cb_si_borrar;
    }

    public void setSpacer4(RichSpacer spacer4) {
        this.spacer4 = spacer4;
    }

    public RichSpacer getSpacer4() {
        return spacer4;
    }

    public void setCb_no_borrar(RichCommandButton commandButton2) {
        this.cb_no_borrar = commandButton2;
    }

    public void setPanelStretchLayout3(RichPanelStretchLayout panelStretchLayout3) {
        this.panelStretchLayout3 = panelStretchLayout3;
    }

    public RichPanelStretchLayout getPanelStretchLayout3() {
        return panelStretchLayout3;
    }

    public void setOt8(RichOutputText outputText1) {
        this.ot8 = outputText1;
    }

    public RichOutputText getOt8() {
        return ot8;
    }

    public void setI1(RichImage image1) {
        this.i1 = image1;
    }

    public RichImage getI1() {
        return i1;
    }

    public void setSpacer5(RichSpacer spacer5) {
        this.spacer5 = spacer5;
    }

    public RichSpacer getSpacer5() {
        return spacer5;
    }

    public void setOutputText1(RichOutputText outputText1) {
        this.outputText1 = outputText1;
    }

    public RichOutputText getOutputText1() {
        return outputText1;
    }

    public void setSpacer6(RichSpacer spacer6) {
        this.spacer6 = spacer6;
    }

    public RichSpacer getSpacer6() {
        return spacer6;
    }

    public void setOutputText2(RichOutputText outputText2) {
        this.outputText2 = outputText2;
    }

    public RichOutputText getOutputText2() {
        return outputText2;
    }

    public void setSpacer7(RichSpacer spacer7) {
        this.spacer7 = spacer7;
    }

    public RichSpacer getSpacer7() {
        return spacer7;
    }

    public void setOutputText3(RichOutputText outputText3) {
        this.outputText3 = outputText3;
    }

    public RichOutputText getOutputText3() {
        return outputText3;
    }

    public void setSpacer8(RichSpacer spacer8) {
        this.spacer8 = spacer8;
    }

    public RichSpacer getSpacer8() {
        return spacer8;
    }

    public void setOutputText4(RichOutputText outputText4) {
        this.outputText4 = outputText4;
    }

    public RichOutputText getOutputText4() {
        return outputText4;
    }

    public void setSpacer9(RichSpacer spacer9) {
        this.spacer9 = spacer9;
    }

    public RichSpacer getSpacer9() {
        return spacer9;
    }

    public void setOutputText5(RichOutputText outputText5) {
        this.outputText5 = outputText5;
    }

    public RichOutputText getOutputText5() {
        return outputText5;
    }

    public void setItPersonasPermanentes(RichInputText itPersonasPermanentes) {
        this.itPersonasPermanentes = itPersonasPermanentes;
    }

    public RichInputText getItPersonasPermanentes() {
        return itPersonasPermanentes;
    }

    public void setItPersonasTransitorias(RichInputText itPersonasTransitorias) {
        this.itPersonasTransitorias = itPersonasTransitorias;
    }

    public RichInputText getItPersonasTransitorias() {
        return itPersonasTransitorias;
    }

    public void setItAprovechamiento(RichInputText itAprovechamiento) {
        this.itAprovechamiento = itAprovechamiento;
    }

    public RichInputText getItAprovechamiento() {
        return itAprovechamiento;
    }

    public void setItCaudalAsignadoDomestico(RichInputText itCaudalAsignadoDomestico) {
        this.itCaudalAsignadoDomestico = itCaudalAsignadoDomestico;
    }

    public RichInputText getItCaudalAsignadoDomestico() {
        return itCaudalAsignadoDomestico;
    }

    public void setItTipoAnimalPecuario(RichInputText itTipoAnimalPecuario) {
        this.itTipoAnimalPecuario = itTipoAnimalPecuario;
    }

    public RichInputText getItTipoAnimalPecuario() {
        return itTipoAnimalPecuario;
    }

    public void setItCaudalAsignadoPecuario(RichInputText itCaudalAsignadoPecuario) {
        this.itCaudalAsignadoPecuario = itCaudalAsignadoPecuario;
    }

    public RichInputText getItCaudalAsignadoPecuario() {
        return itCaudalAsignadoPecuario;
    }

    public void setItNumeroAnimalesPecuario(RichInputText itNumeroAnimalesPecuario) {
        this.itNumeroAnimalesPecuario = itNumeroAnimalesPecuario;
    }

    public RichInputText getItNumeroAnimalesPecuario() {
        return itNumeroAnimalesPecuario;
    }

    public void setItTipoCultivo(RichInputText itTipoCultivo) {
        this.itTipoCultivo = itTipoCultivo;
    }

    public RichInputText getItTipoCultivo() {
        return itTipoCultivo;
    }

    public void setItCaudalAsignadoAgricola(RichInputText itCaudalAsignadoAgricola) {
        this.itCaudalAsignadoAgricola = itCaudalAsignadoAgricola;
    }

    public RichInputText getItCaudalAsignadoAgricola() {
        return itCaudalAsignadoAgricola;
    }

    public void setItProduccion(RichInputText itProduccion) {
        this.itProduccion = itProduccion;
    }

    public RichInputText getItProduccion() {
        return itProduccion;
    }

    public void setItEficiencia(RichInputText itEficiencia) {
        this.itEficiencia = itEficiencia;
    }

    public RichInputText getItEficiencia() {
        return itEficiencia;
    }

    public void setItArea(RichInputText itArea) {
        this.itArea = itArea;
    }

    public RichInputText getItArea() {
        return itArea;
    }

    public void setItTipoAnimalAcuicola(RichInputText itTipoAnimalAcuicola) {
        this.itTipoAnimalAcuicola = itTipoAnimalAcuicola;
    }

    public RichInputText getItTipoAnimalAcuicola() {
        return itTipoAnimalAcuicola;
    }

    public void setItCaudalAsignadoAcuicola(RichInputText itCaudalAsignadoAcuicola) {
        this.itCaudalAsignadoAcuicola = itCaudalAsignadoAcuicola;
    }

    public RichInputText getItCaudalAsignadoAcuicola() {
        return itCaudalAsignadoAcuicola;
    }

    public void setItNumeroAnimalesAcuicola(RichInputText itNumeroAnimalesAcuicola) {
        this.itNumeroAnimalesAcuicola = itNumeroAnimalesAcuicola;
    }

    public RichInputText getItNumeroAnimalesAcuicola() {
        return itNumeroAnimalesAcuicola;
    }

    public void setSocTipoUsoOtro(RichSelectOneChoice socTipoUsoOtro) {
        this.socTipoUsoOtro = socTipoUsoOtro;
    }

    public RichSelectOneChoice getSocTipoUsoOtro() {
        return socTipoUsoOtro;
    }

    public void setSiTipoUsoOtro(UISelectItems siTipoUsoOtro) {
        this.siTipoUsoOtro = siTipoUsoOtro;
    }

    public UISelectItems getSiTipoUsoOtro() {
        return siTipoUsoOtro;
    }

    public void setItCaudalAsignadoOtro(RichInputText itCaudalAsignadoOtro) {
        this.itCaudalAsignadoOtro = itCaudalAsignadoOtro;
    }

    public RichInputText getItCaudalAsignadoOtro() {
        return itCaudalAsignadoOtro;
    }

    public void setItDescripcionOtro(RichInputText itDescripcionOtro) {
        this.itDescripcionOtro = itDescripcionOtro;
    }

    public RichInputText getItDescripcionOtro() {
        return itDescripcionOtro;
    }

    public void setCbAceptarDi(RichCommandButton commandButton1) {
        this.cbAceptarDi = commandButton1;
    }

    public RichCommandButton getCbAceptarDi() {
        return cbAceptarDi;
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

    public void setCommandLink6(RichCommandLink commandLink6) {
        this.commandLink6 = commandLink6;
    }

    public RichCommandLink getCommandLink6() {
        return commandLink6;
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

    public void setSpacer17(RichSpacer spacer17) {
        this.spacer17 = spacer17;
    }

    public RichSpacer getSpacer17() {
        return spacer17;
    }

    public void setOutputText6(RichOutputText outputText6) {
        this.outputText6 = outputText6;
    }

    public RichOutputText getOutputText6() {
        return outputText6;
    }

}
