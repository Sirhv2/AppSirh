package co.gov.ideam.sirh.usuarios.agua.view;

import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.view.ParametrosDelegate;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.usuarios.agua.model.Captacion;
import co.gov.ideam.sirh.usuarios.agua.model.Concesion;
import co.gov.ideam.sirh.usuarios.agua.model.Predio;
import co.gov.ideam.sirh.usuarios.agua.model.SubttFunias;
import co.gov.ideam.sirh.usuarios.agua.model.SubttFuniasDocumentos;
import co.gov.ideam.sirh.usuarios.agua.model.UsuarioAgua;
import co.gov.ideam.sirh.util.Constantes;
import co.gov.ideam.sirh.util.ConstantesCaptaciones;
import co.gov.ideam.sirh.util.ConstantesParametros;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;
import co.gov.ideam.sirh.view.util.ArchivoCargadoTO;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UISelectItems;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichMenu;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.nav.RichCommandMenuItem;
import oracle.adf.view.rich.component.rich.output.RichImage;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;

import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.model.UploadedFile;

public class DetalleFuniasManantial extends StandarBean{
    
    //objeto padre.
    private Captacion captacion;
    //objeto seleccionado.
    private SubttFunias funias;
    //usuario en session
    private UsuarioAgua usuario;
    //predio en session
    private Predio predio;
    //concesion en session
    private Concesion concesion; 
    //listado de tipo de informacion del funias desde las categorias.
    private List listaTiposFunias;
    //Listado desde los parametros de categorias
    private List listaTipoManantial;
    //listado desde los parametros de categorias
    private List listaPermanencia;
    //listado desde los parametros de categorias
    private List listaSurgencia;
    
    private boolean archivosRelacionados;
    private boolean file;
    private UploadedFile uploadedFile;
    private ArchivoCargadoTO archivoCargado;
    private List<ArchivoCargadoTO> archivosCargados;
    
    private SubttFuniasDocumentos documentoSeleccionado;
    //la siguiente variable se creo para evitar inconsistencias, con la vista, 
    //en la lista de documentos en el objeto funias cuando se remueve un objeto 
    //de la lista.
    private List listaArchivosRelacionados;
    
    private RichForm form1;
    private RichDocument document1;
    private RichPanelStretchLayout panelStretchLayout1;
    private RichPanelSplitter panelSplitter1;
    private RichPanelGroupLayout panelGroupLayout10;
    private RichPanelGroupLayout panelGroupLayout1;
    private RichPanelBox pnlbDatos;
    private RichPanelGroupLayout panelGroupLayout2;
    private RichPanelFormLayout panelFormLayout1;
    private RichPanelGroupLayout panelGroupLayout3;
    private RichPanelCollection panelCollection1;
    private RichMenu menu1;
    private RichCommandMenuItem cmiVer;
    private RichCommandMenuItem cmiBorrar;
    private RichTable table1;
    private RichPanelGroupLayout panelGroupLayout4;
    private RichSpacer spacer1;
    private RichCommandLink commandLink1;
    private RichSpacer spacer2;
    private RichCommandLink commandLink2;
    private RichSpacer spacer3;
    private RichCommandLink commandLink3;
    private RichSpacer spacer4;
    private RichCommandLink commandLink4;
    private RichSpacer spacer5;
    private RichCommandLink commandLink5;
    private RichSpacer spacer6;
    private RichCommandLink commandLink6;
    private RichSpacer spacer7;
    private RichOutputText outputText3;
    private RichPanelGroupLayout panelGroupLayout5;
    private RichPanelGroupLayout panelGroupLayout6;
    private RichSpacer spacer8;
    private RichCommandButton cbAceptar;
    private RichSpacer spacer9;
    private RichCommandButton cbBorrar;
    private RichPanelGroupLayout panelGroupLayout7;
    private RichPopup p_borrar_docto;
    private RichPopup popup_borrar;
    private RichPopup p_registro_exitoso;
    private RichDialog d_borrar_docto;
    private RichDialog d_borrar;
    private RichDialog d_registro_exitoso;
    private RichOutputText ot100;
    private RichCommandButton cbAceptarDi;
    private RichPanelStretchLayout panelStretchLayout2;
    private RichOutputText ot8;
    private RichImage i1;
    private RichPanelGroupLayout panelGroupLayout8;
    private RichPanelGroupLayout panelGroupLayout9;
    private RichCommandButton cb_si_borrar;
    private RichSpacer spacer10;
    private RichCommandButton cb_no_borrar;
    private RichOutputText ot_borrar_1;
    private RichSpacer spacer11;
    private RichOutputText ot_borrar_2;
    private RichSpacer spacer12;
    private RichOutputText otBasicos;
    private RichSpacer spacer14;
    private RichSpacer spacer16;
    private RichSpacer spacer17;
    private RichSpacer spacer18;
    private RichSelectOneChoice socTipo;
    private UISelectItems siTipo;
    private RichSelectOneChoice socPermanencia;
    private UISelectItems siPermanencia;
    private RichSelectOneChoice socSurgencia;
    private UISelectItems siSurgencia;
    private RichInputText itObservacion;

    public DetalleFuniasManantial(){
        FacesUtils.setActiveBean("detalleFuniasManantial",
                                 "Detalle de Funias", 
                                 UsuariosAguaDelegate.class);
        this.load();
    }
    
    public void load(){
        try{
            System.out.println("===================LOAD MANANTIAL");
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
            ParametrosDelegate pd = ParametrosDelegate.getInstance(); 
            
            Object obj = FacesUtils.getFromSession("funiasSeleccionado");//la concesion desde la cual se inicia.
            if(obj instanceof SubttFunias){
                this.funias = (SubttFunias)obj;
            }else {                
                Long codigo = (Long)FacesUtils.getFromSession("funiasSeleccionado");
                if(codigo != null ){
                    this.funias = uad.getFunias(codigo);                      
                    FacesUtils.setInSession("funiasSeleccionado", this.funias);
                }
            }

            Object objCaptacion = FacesUtils.getFromSession("captacionSeleccionada");
            if(objCaptacion instanceof Captacion){
                this.captacion = (Captacion)objCaptacion;
            }else{                
                Long codigo = (Long)FacesUtils.getFromSession("captacionSeleccionada");                                            
                this.captacion = uad.getCaptacion(codigo);
                FacesUtils.setInSession("captacionSeleccionada", this.captacion);
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

            this.listaTiposFunias = 
                this.cargarParametro(ConstantesParametros.
                                                   CATEGORIA_FUNIAS_SECCIONES);
            
            this.listaTipoManantial = 
                this.cargarParametro(ConstantesParametros.
                                                   CATEGORIA_TIPO_MANANTIAL);
            
            this.listaPermanencia = 
                this.cargarParametro(ConstantesParametros.
                                                   CATEGORIA_PERMANENCIA_MANANTIAL);
            
            this.listaSurgencia = 
                this.cargarParametro(ConstantesParametros.
                                                   CATEGORIA_SURGENCIA_MANANTIAL);
            
            System.out.println("===================LOAD MANANTIAL FUNIAS: "+this.funias);
            if (this.funias != null && this.funias.getId() != null &&
                    this.funias.getId() > 0) {
                List documentosAsociados =
                    uad.getFilesByFunias(this.funias.getId());
                if (documentosAsociados != null &&
                        !documentosAsociados.isEmpty()) {
                    this.funias.setSubttFuniasDocumentosList(documentosAsociados);
                    
                    this.listaArchivosRelacionados = null;
                    if(this.funias.getSubttFuniasDocumentosList()!=null){
                        this.listaArchivosRelacionados = new ArrayList();
                        for(SubttFuniasDocumentos documento : this.funias.getSubttFuniasDocumentosList()){
                            if(!this.listaArchivosRelacionados.contains(documento)){
                                System.out.println(":::::::::::::LOAD DOCUMENTO ADICIONADO: "+documento);
                                this.listaArchivosRelacionados.add(documento);
                            }
                        }
                    }
                }

                this.archivosRelacionados =
                        ((this.funias.getSubttFuniasDocumentosList() != null) ?
                         this.funias.getSubttFuniasDocumentosList().size() >
                         0 : false);

                if (this.funias.getIdTipo() != null) {
                    this.funias.setObjTipoManantial(pd.getLista(new Integer(this.funias.getIdTipo())));
                }

                if (this.funias.getIdPermanencia() != null) {
                    this.funias.setObjPermanencia(pd.getLista(new Integer(this.funias.getIdPermanencia())));
                }
                
                if (this.funias.getIdSurgencia() != null) {
                    this.funias.setObjSurgencia(pd.getLista(new Integer(this.funias.getIdSurgencia())));
                }
                
            }else{
                
                this.funias = new SubttFunias();    
                System.out.println("===================LOAD MANANTIAL NO FUNIAS: "+this.funias);
            }
            
        }catch(IdeamException e){
            showMessage(e);
        }
    }
    
    public void cbBorrar_actionListener(ActionEvent actionEvent) {
        String nombre = ""+this.funias.getTipoFunias();
        String params[] = {nombre};
        String texto = getText("FUN_BORRAR", params);
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
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.popup_borrar);
            showPopup(this.popup_borrar, true);
        }
    }

    public void cb_si_borrar_actionListener(ActionEvent actionEvent) {
        try{
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
            uad.deleteFunias(this.funias);
            showMessage(getText("FUN_ELIMINADO"));
            
        }catch(IdeamException e){
            showMessage(e.getMessage());
        }
    }

    public void cb_no_borrar_actionListener(ActionEvent actionEvent) {
        showPopup(popup_borrar, false);
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
        
        
        if(this.socTipo.getValue()==null){
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR, 
                        this.socTipo);       
            continuar = false;
        }
        
        if(this.socPermanencia.getValue()==null){
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR, 
                        this.socPermanencia);       
            continuar = false;
        }
        
        if(this.socSurgencia.getValue()==null){
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR, 
                        this.socSurgencia);       
            continuar = false;
        }

        if(continuar){
            this.save();
            showPopup(p_registro_exitoso, true);
        }
    }
    
    public void save(){
        try{
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
            
            UsuarioConectadoTO usuarioConectado = 
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

            this.funias.setIdTipo((this.socTipo.getValue()!=null)
                                    ? ((Lista)this.socTipo.getValue()).getCodigo()
                                    : null);
            this.funias.setIdPermanencia((this.socPermanencia.getValue()!=null)
                                    ? ((Lista)this.socPermanencia.getValue()).getCodigo()
                                    : null);
            this.funias.setIdSurgencia((this.socSurgencia.getValue()!=null)
                                    ? ((Lista)this.socSurgencia.getValue()).getCodigo()
                                    : null);
            this.funias.setObservacion((this.itObservacion.getValue()!=null)
                               ?this.itObservacion.getValue().toString()
                               :null);
            
            
            if(this.funias.getId()!=null && this.funias.getId()>0){
                this.funias = uad.updateFunias(this.funias);
            }else{
                this.funias.setCodigoAutoridad(usuarioConectado.getUsuario().
                                       getAutoridadAmbiental().getId().longValue());
                this.funias.setIdCaptacion(this.captacion.getCodigo());
                this.funias.setTipoFunias(ConstantesParametros.LISTA_MANANTIAL.intValue());

                this.funias = uad.createFunias(funias);    
            }

        }catch(IdeamException e){
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

    public void setPanelSplitter1(RichPanelSplitter panelSplitter1) {
        this.panelSplitter1 = panelSplitter1;
    }

    public RichPanelSplitter getPanelSplitter1() {
        return panelSplitter1;
    }

    public void setPanelGroupLayout10(RichPanelGroupLayout panelGroupLayout10) {
        this.panelGroupLayout10 = panelGroupLayout10;
    }

    public RichPanelGroupLayout getPanelGroupLayout10() {
        return panelGroupLayout10;
    }

    public void setPanelGroupLayout1(RichPanelGroupLayout panelGroupLayout1) {
        this.panelGroupLayout1 = panelGroupLayout1;
    }

    public RichPanelGroupLayout getPanelGroupLayout1() {
        return panelGroupLayout1;
    }

    public void setPnlbDatos(RichPanelBox pnlbDatos) {
        this.pnlbDatos = pnlbDatos;
    }

    public RichPanelBox getPnlbDatos() {
        return pnlbDatos;
    }

    public void setPanelGroupLayout2(RichPanelGroupLayout panelGroupLayout2) {
        this.panelGroupLayout2 = panelGroupLayout2;
    }

    public RichPanelGroupLayout getPanelGroupLayout2() {
        return panelGroupLayout2;
    }

    public void setPanelFormLayout1(RichPanelFormLayout panelFormLayout1) {
        this.panelFormLayout1 = panelFormLayout1;
    }

    public RichPanelFormLayout getPanelFormLayout1() {
        return panelFormLayout1;
    }

    public void setPanelGroupLayout3(RichPanelGroupLayout panelGroupLayout3) {
        this.panelGroupLayout3 = panelGroupLayout3;
    }

    public RichPanelGroupLayout getPanelGroupLayout3() {
        return panelGroupLayout3;
    }

    public void setPanelCollection1(RichPanelCollection panelCollection1) {
        this.panelCollection1 = panelCollection1;
    }

    public RichPanelCollection getPanelCollection1() {
        return panelCollection1;
    }

    public void setMenu1(RichMenu menu1) {
        this.menu1 = menu1;
    }

    public RichMenu getMenu1() {
        return menu1;
    }

    public void setCmiVer(RichCommandMenuItem cmiVer) {
        this.cmiVer = cmiVer;
    }

    public RichCommandMenuItem getCmiVer() {
        return cmiVer;
    }

    public void setCmiBorrar(RichCommandMenuItem cmiBorrar) {
        this.cmiBorrar = cmiBorrar;
    }

    public RichCommandMenuItem getCmiBorrar() {
        return cmiBorrar;
    }

    public void setTable1(RichTable table1) {
        this.table1 = table1;
    }

    public RichTable getTable1() {
        return table1;
    }

    public void setPanelGroupLayout4(RichPanelGroupLayout panelGroupLayout4) {
        this.panelGroupLayout4 = panelGroupLayout4;
    }

    public RichPanelGroupLayout getPanelGroupLayout4() {
        return panelGroupLayout4;
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

    public void setCommandLink3(RichCommandLink commandLink3) {
        this.commandLink3 = commandLink3;
    }

    public RichCommandLink getCommandLink3() {
        return commandLink3;
    }

    public void setSpacer4(RichSpacer spacer4) {
        this.spacer4 = spacer4;
    }

    public RichSpacer getSpacer4() {
        return spacer4;
    }

    public void setCommandLink4(RichCommandLink commandLink4) {
        this.commandLink4 = commandLink4;
    }

    public RichCommandLink getCommandLink4() {
        return commandLink4;
    }

    public void setSpacer5(RichSpacer spacer5) {
        this.spacer5 = spacer5;
    }

    public RichSpacer getSpacer5() {
        return spacer5;
    }

    public void setCommandLink5(RichCommandLink commandLink5) {
        this.commandLink5 = commandLink5;
    }

    public RichCommandLink getCommandLink5() {
        return commandLink5;
    }

    public void setSpacer6(RichSpacer spacer6) {
        this.spacer6 = spacer6;
    }

    public RichSpacer getSpacer6() {
        return spacer6;
    }

    public void setCommandLink6(RichCommandLink commandLink6) {
        this.commandLink6 = commandLink6;
    }

    public RichCommandLink getCommandLink6() {
        return commandLink6;
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

    public void setSpacer8(RichSpacer spacer8) {
        this.spacer8 = spacer8;
    }

    public RichSpacer getSpacer8() {
        return spacer8;
    }

    public void setCbAceptar(RichCommandButton cbAceptar) {
        this.cbAceptar = cbAceptar;
    }

    public RichCommandButton getCbAceptar() {
        return cbAceptar;
    }

    public void setSpacer9(RichSpacer spacer9) {
        this.spacer9 = spacer9;
    }

    public RichSpacer getSpacer9() {
        return spacer9;
    }

    public void setCbBorrar(RichCommandButton cbBorrar) {
        this.cbBorrar = cbBorrar;
    }

    public RichCommandButton getCbBorrar() {
        return cbBorrar;
    }

    public void setPanelGroupLayout7(RichPanelGroupLayout panelGroupLayout7) {
        this.panelGroupLayout7 = panelGroupLayout7;
    }

    public RichPanelGroupLayout getPanelGroupLayout7() {
        return panelGroupLayout7;
    }

    public void setP_borrar_docto(RichPopup p_borrar_docto) {
        this.p_borrar_docto = p_borrar_docto;
    }

    public RichPopup getP_borrar_docto() {
        return p_borrar_docto;
    }

    public void setPopup_borrar(RichPopup popup_borrar) {
        this.popup_borrar = popup_borrar;
    }

    public RichPopup getPopup_borrar() {
        return popup_borrar;
    }

    public void setP_registro_exitoso(RichPopup p_registro_exitoso) {
        this.p_registro_exitoso = p_registro_exitoso;
    }

    public RichPopup getP_registro_exitoso() {
        return p_registro_exitoso;
    }

    public void setD_borrar_docto(RichDialog d_borrar_docto) {
        this.d_borrar_docto = d_borrar_docto;
    }

    public RichDialog getD_borrar_docto() {
        return d_borrar_docto;
    }

    public void setD_borrar(RichDialog d_borrar) {
        this.d_borrar = d_borrar;
    }

    public RichDialog getD_borrar() {
        return d_borrar;
    }

    public void setD_registro_exitoso(RichDialog d_registro_exitoso) {
        this.d_registro_exitoso = d_registro_exitoso;
    }

    public RichDialog getD_registro_exitoso() {
        return d_registro_exitoso;
    }

    public void setOt100(RichOutputText ot100) {
        this.ot100 = ot100;
    }

    public RichOutputText getOt100() {
        return ot100;
    }

    public void setCbAceptarDi(RichCommandButton cbAceptarDi) {
        this.cbAceptarDi = cbAceptarDi;
    }

    public RichCommandButton getCbAceptarDi() {
        return cbAceptarDi;
    }

    public void setPanelStretchLayout2(RichPanelStretchLayout panelStretchLayout2) {
        this.panelStretchLayout2 = panelStretchLayout2;
    }

    public RichPanelStretchLayout getPanelStretchLayout2() {
        return panelStretchLayout2;
    }

    public void setOt8(RichOutputText ot8) {
        this.ot8 = ot8;
    }

    public RichOutputText getOt8() {
        return ot8;
    }

    public void setI1(RichImage i1) {
        this.i1 = i1;
    }

    public RichImage getI1() {
        return i1;
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

    public void setCb_si_borrar(RichCommandButton cb_si_borrar) {
        this.cb_si_borrar = cb_si_borrar;
    }

    public RichCommandButton getCb_si_borrar() {
        return cb_si_borrar;
    }

    public void setSpacer10(RichSpacer spacer10) {
        this.spacer10 = spacer10;
    }

    public RichSpacer getSpacer10() {
        return spacer10;
    }

    public void setCb_no_borrar(RichCommandButton cb_no_borrar) {
        this.cb_no_borrar = cb_no_borrar;
    }

    public RichCommandButton getCb_no_borrar() {
        return cb_no_borrar;
    }

    public void setOt_borrar_1(RichOutputText ot_borrar_1) {
        this.ot_borrar_1 = ot_borrar_1;
    }

    public RichOutputText getOt_borrar_1() {
        return ot_borrar_1;
    }

    public void setSpacer11(RichSpacer spacer11) {
        this.spacer11 = spacer11;
    }

    public RichSpacer getSpacer11() {
        return spacer11;
    }

    public void setOt_borrar_2(RichOutputText ot_borrar_2) {
        this.ot_borrar_2 = ot_borrar_2;
    }

    public RichOutputText getOt_borrar_2() {
        return ot_borrar_2;
    }

    public void setSpacer12(RichSpacer spacer12) {
        this.spacer12 = spacer12;
    }

    public RichSpacer getSpacer12() {
        return spacer12;
    }

    public void setOtBasicos(RichOutputText otBasicos) {
        this.otBasicos = otBasicos;
    }

    public RichOutputText getOtBasicos() {
        return otBasicos;
    }

    public void setSpacer16(RichSpacer spacer16) {
        this.spacer16 = spacer16;
    }

    public RichSpacer getSpacer16() {
        return spacer16;
    }

    public void setSpacer17(RichSpacer spacer17) {
        this.spacer17 = spacer17;
    }

    public RichSpacer getSpacer17() {
        return spacer17;
    }

    public void setSpacer18(RichSpacer spacer18) {
        this.spacer18 = spacer18;
    }

    public RichSpacer getSpacer18() {
        return spacer18;
    }

    public void setSpacer14(RichSpacer spacer14) {
        this.spacer14 = spacer14;
    }

    public RichSpacer getSpacer14() {
        return spacer14;
    }


    public void setCaptacion(Captacion captacion) {
        this.captacion = captacion;
    }

    public Captacion getCaptacion() {
        return captacion;
    }

    public void setFunias(SubttFunias funias) {
        this.funias = funias;
    }

    public SubttFunias getFunias() {
        return funias;
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

    public void setListaTiposFunias(List listaTiposFunias) {
        this.listaTiposFunias = listaTiposFunias;
    }

    public List getListaTiposFunias() {
        return listaTiposFunias;
    }

    public void setArchivosRelacionados(boolean archivosRelacionados) {
        this.archivosRelacionados = archivosRelacionados;
    }

    public boolean isArchivosRelacionados() {
        return archivosRelacionados;
    }

    public void setFile(boolean file) {
        this.file = file;
    }

    public boolean isFile() {
        return file;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setArchivoCargado(ArchivoCargadoTO archivoCargado) {
        this.archivoCargado = archivoCargado;
    }

    public ArchivoCargadoTO getArchivoCargado() {
        return archivoCargado;
    }

    public void setArchivosCargados(List<ArchivoCargadoTO> archivosCargados) {
        this.archivosCargados = archivosCargados;
    }

    public List<ArchivoCargadoTO> getArchivosCargados() {
        return archivosCargados;
    }

    public void setListaArchivosRelacionados(List listaArchivosRelacionados) {
        this.listaArchivosRelacionados = listaArchivosRelacionados;
    }

    public List getListaArchivosRelacionados() {
        return listaArchivosRelacionados;
    }

    public void setSocTipo(RichSelectOneChoice selectOneChoice1) {
        this.socTipo = selectOneChoice1;
    }

    public RichSelectOneChoice getSocTipo() {
        return socTipo;
    }

    public void setSiTipo(UISelectItems selectItems1) {
        this.siTipo = selectItems1;
    }

    public UISelectItems getSiTipo() {
        return siTipo;
    }

    public void setSocPermanencia(RichSelectOneChoice selectOneChoice2) {
        this.socPermanencia = selectOneChoice2;
    }

    public RichSelectOneChoice getSocPermanencia() {
        return socPermanencia;
    }

    public void setSiPermanencia(UISelectItems selectItems2) {
        this.siPermanencia = selectItems2;
    }

    public UISelectItems getSiPermanencia() {
        return siPermanencia;
    }

    public void setSocSurgencia(RichSelectOneChoice selectOneChoice3) {
        this.socSurgencia = selectOneChoice3;
    }

    public RichSelectOneChoice getSocSurgencia() {
        return socSurgencia;
    }

    public void setSiSurgencia(UISelectItems selectItems3) {
        this.siSurgencia = selectItems3;
    }

    public UISelectItems getSiSurgencia() {
        return siSurgencia;
    }

    public void setItObservacion(RichInputText inputText1) {
        this.itObservacion = inputText1;
    }

    public RichInputText getItObservacion() {
        return itObservacion;
    }

    public void setListaTipoManantial(List listaTipoManantial) {
        this.listaTipoManantial = listaTipoManantial;
    }

    public List getListaTipoManantial() {
        return listaTipoManantial;
    }

    public void setListaPermanencia(List listaPermanencia) {
        this.listaPermanencia = listaPermanencia;
    }

    public List getListaPermanencia() {
        return listaPermanencia;
    }

    public void setListaSurgencia(List listaSurgencia) {
        this.listaSurgencia = listaSurgencia;
    }

    public List getListaSurgencia() {
        return listaSurgencia;
    }
}
