package co.gov.ideam.sirh.fuentes.view;

import co.gov.ideam.sirh.auditoria.model.Auditoria;
import co.gov.ideam.sirh.auditoria.view.AuditoriasDelegate;

import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.fuentes.model.FnttTramo;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Lista;

import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.usuarios.agua.model.Captacion;

import co.gov.ideam.sirh.usuarios.agua.view.UsuariosAguaDelegate;
import co.gov.ideam.sirh.util.Constantes;
import co.gov.ideam.sirh.util.ConstantesParametros;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;

import co.gov.ideam.sirh.view.util.SpecialTreeModel;

import co.gov.ideam.sirh.view.util.TreeNode;

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
import oracle.adf.view.rich.component.rich.data.RichTree;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanRadio;
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

import org.apache.myfaces.trinidad.model.TreeModel;

public class DetalleFuenteBean extends StandarBean{
 
    private FnttFuente fuente;
    private List listaTipos;
    private TreeModel tramosTreeModel;
    private RichDocument document1;
    private RichForm form1;
    private RichPanelStretchLayout panelStretchLayout1;
    private RichPanelSplitter panelSplitter1;
    private RichPanelStretchLayout panelStretchLayout2;
    private RichPanelGroupLayout panelGroupLayout1;
    private RichPanelBox panelBox1;
    private RichPanelFormLayout panelFormLayout1;
    private RichInputText it_nombre;
    private RichInputText it_descripcion;
    private RichSelectOneChoice selectOneChoice7;
    private UISelectItems selectItems7;
    private RichSelectBooleanRadio sbr_si;
    private RichSelectBooleanRadio sbr_no;
    private RichInputText itcodCuenca;
    private RichPanelGroupLayout panelGroupLayout2;
    private RichSpacer spacer1;
    private RichPanelGroupLayout panelGroupLayout3;
    private RichCommandButton cbAceptar;
    private RichSpacer spacer2;
    private RichCommandButton cbBorrar;
    private RichPopup popup_borrar;
    private RichDialog d_borrar;
    private RichPanelGroupLayout panelGroupLayout4;
    private RichOutputText otBorrar1;
    private RichSpacer spacer3;
    private RichOutputText otBorrar2;
    private RichPanelGroupLayout panelGroupLayout5;
    private RichCommandButton cb_si_borrar;
    private RichSpacer spacer4;
    private RichCommandButton cb_no_borrar;
    private RichPopup p_registro_exitoso;
    private RichDialog d_registro_exitoso;
    private RichPanelGroupLayout panelGroupLayout6;
    private RichCommandButton cbAceptarDi;
    private RichPanelStretchLayout panelStretchLayout3;
    private RichOutputText ot8;
    private RichImage i1;
    private RichPanelStretchLayout panelStretchLayout4;
    private RichPanelBox pb_tramos;
    private RichPanelCollection pc1;
    private RichMenu marchivo;
    private RichCommandMenuItem cmiAddTramo;
    private RichTree t1;
    private RichCommandLink cl2;
    private RichPanelGroupLayout panelGroupLayout7;
    private RichSpacer spacer5;
    private RichCommandLink commandLink1;
    private RichSpacer spacer6;
    private RichOutputText outputText1;

    private boolean esCar;


    //Lista de provincias hidrogeologicas
    private List listaProvincias;

    private RichSelectOneChoice socProvinciaHidro;
    private UISelectItems siProvinciaHidro;
    private RichInputText itUnidadHidrogeologica;
    private RichInputText it_fuenteCAR;

    public DetalleFuenteBean(){
        FacesUtils.setActiveBean("detalleFuenteBean", "Detalle Fuente",
                                 FuenteDelegate.class);        
        FacesUtils.removeManagedBeanFromSession("TramosTreeHandler");
        FacesUtils.removeManagedBeanFromSession("tramosBean");
        //FacesUtils.removeManagedBeanFromSession("FuentesBean");
        this.load();
    }
    
    public void load(){
       
        this.it_nombre = new RichInputText();
        this.sbr_si = new  RichSelectBooleanRadio();
        this.sbr_no = new  RichSelectBooleanRadio();
        this.itcodCuenca = new RichInputText();
        this.socProvinciaHidro = new RichSelectOneChoice();
        this.itUnidadHidrogeologica= new RichInputText();
        
        
        
        try{
            FuenteDelegate fd = FuenteDelegate.getInstance();
            Object obj = FacesUtils.getFromSession("fuenteSeleccionada");
            if(obj instanceof FnttFuente){
                this.fuente = (FnttFuente)obj;
                this.fuente = fd.getFuente(this.fuente.getId()); 
            }else{                
                Long codigo = (Long)FacesUtils.getFromSession("fuenteSeleccionada");                                            
                this.fuente = fd.getFuente(codigo);                
            }
            
            UsuarioConectadoTO usuarioConectado = 
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

            esCar = false;
            if (usuarioConectado.getUsuario().getAutoridadAmbiental().getId() == 7L)
                esCar = true;
            
            
            this.listaTipos = this.cargarParametro(ConstantesParametros.
                                                   CATEGORIA_TIPO_FUENTE);
            this.listaProvincias = this.cargarParametro(ConstantesParametros.
                                                   CATEGORIA_PROVINCIA_HIDROGEOLOGICA);

                         
            if(this.fuente!=null && this.fuente.getEsCompartidaAux()!=null){
                this.sbr_si.setSelected(this.fuente.getEsCompartidaAux());
                this.sbr_no.setSelected(!this.fuente.getEsCompartidaAux());
            }
            

            if(this.fuente.getIdTipoFuente()!=null && this.fuente.getIdTipoFuente().getCodigo().longValue()==ConstantesParametros.LISTA_FUENTE_SUBTERRANEA){
              this.it_nombre.setLabel("Sistema Acuífero");
                this.sbr_no.setVisible(false);
                AdfFacesContext.getCurrentInstance().addPartialTarget(this.sbr_no); 
                this.sbr_si.setVisible(false);
                AdfFacesContext.getCurrentInstance().addPartialTarget(this.sbr_si); 
                this.itcodCuenca.setVisible(false);
                AdfFacesContext.getCurrentInstance().addPartialTarget(this.itcodCuenca); 
                this.socProvinciaHidro.setVisible(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(this.socProvinciaHidro);  
                this.itUnidadHidrogeologica.setVisible(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(this.itUnidadHidrogeologica); 
               
            }else{
                    this.it_nombre.setLabel("Fuente");
                    this.sbr_no.setVisible(true);
                    AdfFacesContext.getCurrentInstance().addPartialTarget(this.sbr_no); 
                    this.sbr_si.setVisible(true);
                    AdfFacesContext.getCurrentInstance().addPartialTarget(this.sbr_si); 
                    this.itcodCuenca.setVisible(true);
                    AdfFacesContext.getCurrentInstance().addPartialTarget(this.itcodCuenca); 
                    this.socProvinciaHidro.setVisible(false);
                    AdfFacesContext.getCurrentInstance().addPartialTarget(this.socProvinciaHidro);  
                    this.itUnidadHidrogeologica.setVisible(false);
                    AdfFacesContext.getCurrentInstance().addPartialTarget(this.itUnidadHidrogeologica); 
                }




           
            this.pb_tramos = new RichPanelBox();
            this.cmiAddTramo = new RichCommandMenuItem();
            if(this.fuente.isEsSubterranea()){
                this.pb_tramos.setText(getText("FNT_SECTORES_RELACIONADOS"));
                this.cmiAddTramo.setText(getText("FNT_ADICIONAR_SECTOR"));
            }else{
                this.pb_tramos.setText(getText("FNT_TRAMOS_RELACIONADOS"));
                this.cmiAddTramo.setText(getText("FNT_ADICIONAR_TRAMO"));
            }
            
            //Construir el arbol.
            List listaNodos = new ArrayList(); 
            
            //crea el grupo
            TreeNode nodoTramos = new TreeNode(getText("MODULO_TRAMOS"), 
                                               "Tramos");                   
            nodoTramos.setData("Tramos");
            listaNodos.add(nodoTramos);//adiciona el grupo
            this.fuente.setFnttTramoList(new ArrayList());
            this.fuente.setFnttTramoList(fd.getAllTramosByFuente(this.fuente));
            if(this.fuente.getFnttTramoList()!=null){
                for(FnttTramo tramo : this.fuente.getFnttTramoList()){
                    TreeNode nodoTramo = new TreeNode(getText("TRM_TRAMO") + " " +
                                                        tramo.getNombre(), 
                                                       "Tramo", true, false);                   
                    nodoTramo.setData(tramo);  
                    nodoTramos.getChildren().add(nodoTramo);
                } 
            }else{
                TreeNode nodoTramo = new TreeNode(getText("NO_HAY_REGISTROS"), 
                                                    "Tramo",
                                                    false,
                                                    true);                   
                nodoTramos.getChildren().add(nodoTramo);
            }
            
            tramosTreeModel = new SpecialTreeModel(listaNodos, "children");
        }catch(IdeamException e){
            showMessage(e);
        }
    }
    
    public void cbAceptar_actionListener(ActionEvent actionEvent) {
        try{
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
            
            if(this.it_nombre.getValue()==null || 
               this.it_nombre.getValue().toString().length()==0){
                showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.it_nombre);       
                continuar = false;
            }        
            if(this.selectOneChoice7.getValue()==null ){
                showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.selectOneChoice7);       
                continuar = false;
            }

            if(continuar){
                FuenteDelegate fd = FuenteDelegate.getInstance();
                this.saveFuente(); 
            }          
        }catch(IdeamException e){
            showMessage(e);
        }
    }
    
    public void saveFuente(){
        try{
            fuente.setNombre(this.it_nombre.getValue().toString().toUpperCase());
            if(this.it_descripcion!=null && this.it_descripcion.getValue()!=null){
                fuente.setDescripcion(this.it_descripcion.getValue().toString());
            }
            
            if(this.it_fuenteCAR!=null && this.it_fuenteCAR.getValue()!=null){
                fuente.setFuente_car(this.it_fuenteCAR.getValue().toString());
            }
            fuente.setIdTipoFuente((Lista)this.selectOneChoice7.getValue());
        
            if(this.sbr_si.isSelected()){
                fuente.setEsCompartidaAux(true);
            }else{
                fuente.setEsCompartidaAux(false);
            }
            
            
            if(this.itcodCuenca!=null && this.itcodCuenca.getValue()!=null){
                fuente.setCodigoCuencaAA(this.itcodCuenca.getValue().toString());
            }
            
            if(this.itUnidadHidrogeologica!=null && this.itUnidadHidrogeologica.getValue()!=null){
                fuente.setUnidadhidro(this.itUnidadHidrogeologica.getValue().toString());
            }
            UsuarioConectadoTO usuarioConectado = 
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
            fuente.setCodAutoridad(usuarioConectado.getUsuario().getAutoridadAmbiental());
            
            FuenteDelegate fd = FuenteDelegate.getInstance();
            
            
            //validar si el nombre de esa fuente ya existe para una fuente en la
            //bd, diferente a la que se está actualizando.

            FnttFuente existe = fd.getFuente(this.fuente.getNombre(), 
                                             usuarioConectado.getUsuario().getAutoridadAmbiental(),
                                             this.fuente.getIdTipoFuente().getCodigo());
            if(existe!=null && existe.getId().longValue() !=
                               this.fuente.getId().longValue() ){
                showMessage(getText("FNT_EXISTE"),
                            FacesMessage.SEVERITY_ERROR);
                return;                       
            }
            fd.updateFuente(fuente);
            
            
            
            try{
                
                /** 
                 * Estas variables son necesarias para llenar los datos intrinsecos de la auditoria.
                 */
                // UsuarioConectadoTO usuarioConectado = (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
                
                Auditoria auditoria = new Auditoria();
                auditoria.setIdUsuario(usuarioConectado.getUsuario().getCodigo());
                auditoria.setOperacion("MODIFICAR");
                auditoria.setObjeto("FUENTES");
                auditoria.setMetodo(Thread.currentThread().getStackTrace()[1].getMethodName());
                auditoria.setClase(this.getClass().getName());
                //Objeto Afectado (usuario modificado, eliminado o agregado) 
                auditoria.setIdObjeto(fuente.getId());
               
                AuditoriasDelegate audDelegate = AuditoriasDelegate.getInstance();
                audDelegate.setAuditoria(auditoria);
                
            }catch(Exception e){
                System.out.println("No se pudo Almacenar la auditoria: ---------------------------------- ");
                System.out.println(e.getMessage()+"------------------------------------------------------ ");
            }
            
            
            
            
            
            showPopup(p_registro_exitoso, true);
        }catch(IdeamException e){
            showMessage(e);
        }
    }   
   
    
    public void cbBorrar_actionListener(ActionEvent actionEvent) {
        String nombre = this.fuente.getNombre();
        
        String params[] = {nombre};
        String texto = getText("borrar_fuente", params);
        otBorrar1.setValue(texto);
        otBorrar2.setValue("");
        try{
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
                
            List<Captacion> captacionesTramo = 
                uad.getCaptacionByTramo(this.fuente.getId());
                
            List<Captacion> vertimientosTramo = 
                  uad.getVertimientoByTramo(this.fuente.getId());
            
            UsuarioConectadoTO usuarioConectado = 
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
            
            Autoridades autoridadAmbiental = usuarioConectado.getUsuario().getAutoridadAmbiental();
            if(autoridadAmbiental==null ||
                    autoridadAmbiental.getId().intValue() == Constantes.IDEAM){
                showMessage(getText("NO_EXISTE_AUTORIDAD_AMBIENTAL"), 
                            FacesMessage.SEVERITY_ERROR); 
            }else if (this.fuente.getFnttTramoList()!=null 
                    && this.fuente.getFnttTramoList().size()>0){
                showMessage(getText("FNT_SI_TRAMOS"));
            }else if(captacionesTramo!=null && !captacionesTramo.isEmpty()){
                showMessage(getText("TRM_SI_CAPTACIONES"));
            }else if(vertimientosTramo!=null && !vertimientosTramo.isEmpty()){
                showMessage(getText("TRM_SI_VERTIMIENTOS"));
            }else{
                AdfFacesContext.getCurrentInstance().addPartialTarget(popup_borrar);
                showPopup(popup_borrar, true);
            }   
        
        }catch(IdeamException e){
            showMessage(e.getMessage());
        }
    }
    
    public void cb_no_borrar_actionListener(ActionEvent actionEvent) {
        showPopup(popup_borrar, false);
    }
    
    public void cb_si_borrar_actionListener(ActionEvent actionEvent) {
        try{
            FuenteDelegate fd = FuenteDelegate.getInstance();
            fd.deleteFuente(this.fuente);
            
            
            try{
                
                /** 
                 * Estas variables son necesarias para llenar los datos intrinsecos de la auditoria.
                 */
                 UsuarioConectadoTO usuarioConectado = (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
                
                Auditoria auditoria = new Auditoria();
                auditoria.setIdUsuario(usuarioConectado.getUsuario().getCodigo());
                auditoria.setOperacion("BORRAR");
                auditoria.setObjeto("FUENTES");
                auditoria.setMetodo(Thread.currentThread().getStackTrace()[1].getMethodName());
                auditoria.setClase(this.getClass().getName());
                //Objeto Afectado (usuario modificado, eliminado o agregado) 
                auditoria.setIdObjeto(this.fuente.getId());
               
                AuditoriasDelegate audDelegate = AuditoriasDelegate.getInstance();
                audDelegate.setAuditoria(auditoria);
                
            }catch(Exception e){
                System.out.println("No se pudo Almacenar la auditoria: ---------------------------------- ");
                System.out.println(e.getMessage()+"------------------------------------------------------ ");
            }
            
            
            
            
            
            showMessage(getText("fuente_eliminada"));
        }catch(IdeamException e){
            
            showMessage(e.getMessage());
        }
    }
    
    public void cl2_actionListener(ActionEvent actionEvent) {
        String nombreParametro = 
            (String)actionEvent.getComponent().getAttributes().get("nombreParametro");                
        Object data = 
            actionEvent.getComponent().getAttributes().get("valorParametro");        
        if(nombreParametro!=null && data != null){
            FacesUtils.setInSession(nombreParametro, data);
        }
    }
    
    public void selectOneChoice7_valueChangeListener(ValueChangeEvent valueChangeEvent) {
        if(this.fuente.isEsSubterranea()){
            this.pb_tramos.setText(getText("FNT_SECTORES_RELACIONADOS"));
            this.cmiAddTramo.setText(getText("FNT_ADICIONAR_SECTOR"));
        }else{
            this.pb_tramos.setText(getText("FNT_TRAMOS_RELACIONADOS"));
            this.cmiAddTramo.setText(getText("FNT_ADICIONAR_TRAMO"));
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.pb_tramos);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.cmiAddTramo);
    }




    public void setFuente(FnttFuente fuente) {
        this.fuente = fuente;
    }

    public FnttFuente getFuente() {
        return fuente;
    }


    public void setListaTipos(List listaTipos) {
        this.listaTipos = listaTipos;
    }

    public List getListaTipos() {
        return listaTipos;
    }


    public void setTramosTreeModel(TreeModel tramosTreeModel) {
        this.tramosTreeModel = tramosTreeModel;
    }

    public TreeModel getTramosTreeModel() {
        return tramosTreeModel;
    }


    public void setDocument1(RichDocument document1) {
        this.document1 = document1;
    }

    public RichDocument getDocument1() {
        return document1;
    }

    public void setForm1(RichForm form1) {
        this.form1 = form1;
    }

    public RichForm getForm1() {
        return form1;
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

    public void setPanelStretchLayout2(RichPanelStretchLayout panelStretchLayout2) {
        this.panelStretchLayout2 = panelStretchLayout2;
    }

    public RichPanelStretchLayout getPanelStretchLayout2() {
        return panelStretchLayout2;
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

    public void setPanelFormLayout1(RichPanelFormLayout panelFormLayout1) {
        this.panelFormLayout1 = panelFormLayout1;
    }

    public RichPanelFormLayout getPanelFormLayout1() {
        return panelFormLayout1;
    }

    public void setIt_nombre(RichInputText inputText1) {
        this.it_nombre = inputText1;
    }

    public RichInputText getIt_nombre() {
        return it_nombre;
    }

    public void setIt_descripcion(RichInputText inputText2) {
        this.it_descripcion = inputText2;
    }

    public RichInputText getIt_descripcion() {
        return it_descripcion;
    }

    public void setSelectOneChoice7(RichSelectOneChoice selectOneChoice1) {
        this.selectOneChoice7 = selectOneChoice1;
    }

    public RichSelectOneChoice getSelectOneChoice7() {
        return selectOneChoice7;
    }

    public void setSelectItems7(UISelectItems selectItems1) {
        this.selectItems7 = selectItems1;
    }

    public UISelectItems getSelectItems7() {
        return selectItems7;
    }

    public void setSbr_si(RichSelectBooleanRadio selectBooleanRadio1) {
        this.sbr_si = selectBooleanRadio1;
    }

    public RichSelectBooleanRadio getSbr_si() {
        return sbr_si;
    }

    public void setSbr_no(RichSelectBooleanRadio selectBooleanRadio2) {
        this.sbr_no = selectBooleanRadio2;
    }

    public RichSelectBooleanRadio getSbr_no() {
        return sbr_no;
    }

    public void setItcodCuenca(RichInputText inputText3) {
        this.itcodCuenca = inputText3;
    }

    public RichInputText getItcodCuenca() {
        return itcodCuenca;
    }

    public void setPanelGroupLayout2(RichPanelGroupLayout panelGroupLayout2) {
        this.panelGroupLayout2 = panelGroupLayout2;
    }

    public RichPanelGroupLayout getPanelGroupLayout2() {
        return panelGroupLayout2;
    }

    public void setSpacer1(RichSpacer spacer1) {
        this.spacer1 = spacer1;
    }

    public RichSpacer getSpacer1() {
        return spacer1;
    }

    public void setPanelGroupLayout3(RichPanelGroupLayout panelGroupLayout3) {
        this.panelGroupLayout3 = panelGroupLayout3;
    }

    public RichPanelGroupLayout getPanelGroupLayout3() {
        return panelGroupLayout3;
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

    public void setPopup_borrar(RichPopup popup1) {
        this.popup_borrar = popup1;
    }

    public RichPopup getPopup_borrar() {
        return popup_borrar;
    }

    public void setD_borrar(RichDialog dialog1) {
        this.d_borrar = dialog1;
    }

    public RichDialog getD_borrar() {
        return d_borrar;
    }

    public void setPanelGroupLayout4(RichPanelGroupLayout panelGroupLayout4) {
        this.panelGroupLayout4 = panelGroupLayout4;
    }

    public RichPanelGroupLayout getPanelGroupLayout4() {
        return panelGroupLayout4;
    }

    public void setOtBorrar1(RichOutputText outputText1) {
        this.otBorrar1 = outputText1;
    }

    public RichOutputText getOtBorrar1() {
        return otBorrar1;
    }

    public void setSpacer3(RichSpacer spacer3) {
        this.spacer3 = spacer3;
    }

    public RichSpacer getSpacer3() {
        return spacer3;
    }

    public void setOtBorrar2(RichOutputText outputText2) {
        this.otBorrar2 = outputText2;
    }

    public RichOutputText getOtBorrar2() {
        return otBorrar2;
    }

    public void setPanelGroupLayout5(RichPanelGroupLayout panelGroupLayout5) {
        this.panelGroupLayout5 = panelGroupLayout5;
    }

    public RichPanelGroupLayout getPanelGroupLayout5() {
        return panelGroupLayout5;
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

    public RichCommandButton getCb_no_borrar() {
        return cb_no_borrar;
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

    public void setPanelGroupLayout6(RichPanelGroupLayout panelGroupLayout6) {
        this.panelGroupLayout6 = panelGroupLayout6;
    }

    public RichPanelGroupLayout getPanelGroupLayout6() {
        return panelGroupLayout6;
    }

    public void setCbAceptarDi(RichCommandButton commandButton1) {
        this.cbAceptarDi = commandButton1;
    }

    public RichCommandButton getCbAceptarDi() {
        return cbAceptarDi;
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

    public void setPanelStretchLayout4(RichPanelStretchLayout panelStretchLayout4) {
        this.panelStretchLayout4 = panelStretchLayout4;
    }

    public RichPanelStretchLayout getPanelStretchLayout4() {
        return panelStretchLayout4;
    }

    public void setPb_tramos(RichPanelBox panelBox2) {
        this.pb_tramos = panelBox2;
    }

    public RichPanelBox getPb_tramos() {
        return pb_tramos;
    }

    public void setPc1(RichPanelCollection panelCollection1) {
        this.pc1 = panelCollection1;
    }

    public RichPanelCollection getPc1() {
        return pc1;
    }

    public void setMarchivo(RichMenu menu1) {
        this.marchivo = menu1;
    }

    public RichMenu getMarchivo() {
        return marchivo;
    }

    public void setCmiAddTramo(RichCommandMenuItem commandMenuItem1) {
        this.cmiAddTramo = commandMenuItem1;
    }

    public RichCommandMenuItem getCmiAddTramo() {
        return cmiAddTramo;
    }

    public void setT1(RichTree tree1) {
        this.t1 = tree1;
    }

    public RichTree getT1() {
        return t1;
    }

    public void setCl2(RichCommandLink commandLink1) {
        this.cl2 = commandLink1;
    }

    public RichCommandLink getCl2() {
        return cl2;
    }

    public void setPanelGroupLayout7(RichPanelGroupLayout panelGroupLayout7) {
        this.panelGroupLayout7 = panelGroupLayout7;
    }

    public RichPanelGroupLayout getPanelGroupLayout7() {
        return panelGroupLayout7;
    }

    public void setSpacer5(RichSpacer spacer5) {
        this.spacer5 = spacer5;
    }

    public RichSpacer getSpacer5() {
        return spacer5;
    }

    public void setCommandLink1(RichCommandLink commandLink1) {
        this.commandLink1 = commandLink1;
    }

    public RichCommandLink getCommandLink1() {
        return commandLink1;
    }

    public void setSpacer6(RichSpacer spacer6) {
        this.spacer6 = spacer6;
    }

    public RichSpacer getSpacer6() {
        return spacer6;
    }

    public void setOutputText1(RichOutputText outputText1) {
        this.outputText1 = outputText1;
    }

    public RichOutputText getOutputText1() {
        return outputText1;
    }

    public void setListaProvincias(List listaProvincias) {
        this.listaProvincias = listaProvincias;
    }

    public List getListaProvincias() {
        return listaProvincias;
    }

    public void setSocProvinciaHidro(RichSelectOneChoice socProvinciaHidro) {
        this.socProvinciaHidro = socProvinciaHidro;
    }

    public RichSelectOneChoice getSocProvinciaHidro() {
        return socProvinciaHidro;
    }

    public void setSiProvinciaHidro(UISelectItems siProvinciaHidro) {
        this.siProvinciaHidro = siProvinciaHidro;
    }

    public UISelectItems getSiProvinciaHidro() {
        return siProvinciaHidro;
    }

    public void setItUnidadHidrogeologica(RichInputText itUnidadHidrogeologica) {
        this.itUnidadHidrogeologica = itUnidadHidrogeologica;
    }

    public RichInputText getItUnidadHidrogeologica() {
        return itUnidadHidrogeologica;
    }

    public void setIt_fuenteCAR(RichInputText inputText1) {
        this.it_fuenteCAR = inputText1;
    }

    public RichInputText getIt_fuenteCAR() {
        return it_fuenteCAR;
    }

    public void setEsCar(boolean esCar) {
        this.esCar = esCar;
    }

    public boolean isEsCar() {
        return esCar;
    }
}
