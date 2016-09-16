package co.gov.ideam.sirh.fuentes.view;

import co.gov.ideam.sirh.auditoria.model.Auditoria;
import co.gov.ideam.sirh.auditoria.view.AuditoriasDelegate;

import co.gov.ideam.sirh.fuentes.model.FnttFuente;

import co.gov.ideam.sirh.parametros.model.Lista;

import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;

import co.gov.ideam.sirh.util.ConstantesParametros;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichMenu;
import oracle.adf.view.rich.component.rich.RichPopup;

import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanRadio;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichCommandMenuItem;
import oracle.adf.view.rich.component.rich.output.RichImage;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;


public class AdicionarFuenteBean extends StandarBean{
    
    private RichForm formAF1;
    private RichDocument documentAF1;
    
    private RichPanelStretchLayout psl1;
    private RichPanelStretchLayout psl2;
    private RichPanelStretchLayout psl7;
    
    private RichPanelFormLayout pfl1;
    private RichPanelFormLayout pfl2;
    
    private RichPanelGroupLayout pgl1;
    private RichPanelGroupLayout pgl2;
    private RichPanelGroupLayout pgl3;
    private RichPanelGroupLayout pgl14;
    
    private RichPanelBox pb1;

    private RichPanelSplitter ps1;
    
    private RichPanelCollection panelCollection1;
    
    private RichCommandButton cbNext1;
    private RichCommandButton cbRelacion;
    private RichCommandButton cbAceptar;
    
    private RichSpacer s1;

    private RichSelectOneChoice selectOneChoice7;
    private UISelectItems selectItems7;
    
    private RichInputText it_nombre;
    private RichInputText it_descripcion;
    
    private RichSelectBooleanRadio sbr_si;
    private RichSelectBooleanRadio sbr_no;
    
    private RichMenu menu1;
    private RichCommandMenuItem commandMenuItem1;

    private String aceptarAction;
    private RichOutputText ot8;

    private FnttFuente fuente;
    private RichDialog d_detalle;
    
    private RichImage i1;

    private RichPopup p_registro_exitoso;
    
    private RichDialog d_registro_exitoso;

    private List listaTipos;
    private RichInputText it_codCuenca;
    private String valorNombre;
    //Lista de provincias hidrogeologicas
    private List listaProvincias;

    private RichSelectOneChoice socProvinciaHidro;
    private UISelectItems siProvinciaHidro;
    private RichInputText itUnidadHidrogeologica;
    private RichInputText it_fuenteCAR;

    private boolean esCar;
    
    public AdicionarFuenteBean(){
        FacesUtils.setActiveBean("adicionarFuenteBean", "Adicionar Fuente",
                                 FuenteDelegate.class);   
        FacesUtils.removeManagedBeanFromSession("FuentesBean");
        this.load();
    }
    
    public void load(){
        fuente = new FnttFuente();
        this.it_nombre = new RichInputText();
        this.sbr_si = new  RichSelectBooleanRadio();
        this.sbr_no = new  RichSelectBooleanRadio();
        this.it_codCuenca = new RichInputText();
        
        this.socProvinciaHidro = new RichSelectOneChoice();
        this.itUnidadHidrogeologica= new RichInputText();
        
        this.it_nombre.setVisible(false);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_nombre); 
        this.sbr_no.setVisible(false);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.sbr_no); 
        this.sbr_si.setVisible(false);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.sbr_si); 
        this.it_codCuenca.setVisible(false);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_codCuenca); 
        this.socProvinciaHidro.setVisible(false);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.socProvinciaHidro);  
        this.itUnidadHidrogeologica.setVisible(false);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.itUnidadHidrogeologica); 
    
        try{
            UsuarioConectadoTO usuarioConectado = 
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
            
            esCar = false;
            if (usuarioConectado.getUsuario().getAutoridadAmbiental().getId() == 7L)
                esCar = true;
            
            this.listaTipos = this.cargarParametro(ConstantesParametros.CATEGORIA_TIPO_FUENTE);
            this.listaProvincias = this.cargarParametro(ConstantesParametros.
                                                   CATEGORIA_PROVINCIA_HIDROGEOLOGICA);
            
        }catch(IdeamException e){
            showMessage(e);
        }
    }
  
  
  
  
  
  
    //ChangeListener Tipo fuente
    public void sc_tipoFuente_valueChangeListener(ValueChangeEvent valueChangeEvent) {
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        this.cambiaNombre();
    }
    
    public void cambiaNombre(){
        
           this.it_nombre.setVisible(true);
           AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_nombre); 
        
           this.fuente.setIdTipoFuente((Lista)this.selectOneChoice7.getValue());
        
       if(this.fuente.getIdTipoFuente()!=null && this.fuente.getIdTipoFuente().getCodigo().longValue()==ConstantesParametros.LISTA_FUENTE_SUBTERRANEA){
         this.it_nombre.setLabel("Sistema Acuífero");
           this.sbr_no.setVisible(false);
           AdfFacesContext.getCurrentInstance().addPartialTarget(this.sbr_no); 
           this.sbr_si.setVisible(false);
           AdfFacesContext.getCurrentInstance().addPartialTarget(this.sbr_si); 
           this.it_codCuenca.setVisible(false);
           AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_codCuenca); 
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
               this.it_codCuenca.setVisible(true);
               AdfFacesContext.getCurrentInstance().addPartialTarget(this.it_codCuenca); 
               this.socProvinciaHidro.setVisible(false);
               AdfFacesContext.getCurrentInstance().addPartialTarget(this.socProvinciaHidro);  
               this.itUnidadHidrogeologica.setVisible(false);
               AdfFacesContext.getCurrentInstance().addPartialTarget(this.itUnidadHidrogeologica); 
           }
  
       }
    
    public void cbNext1_actionListener(ActionEvent actionEvent) throws IdeamException{
        boolean continuar = true;
        // Validar que se haya seleccionado tipo y numero de id
        
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
            this.saveFuente();
        }
    }
    
    public void cbNext2_actionListener(ActionEvent actionEvent) throws IdeamException{
        boolean continuar = true;
        if(continuar){   
            this.saveFuente();   
        }
        
    }
    
    
    
    public void saveFuente(){
        try{
            
            FnttFuente fuenteNueva = new FnttFuente();
            fuenteNueva.setNombre(this.it_nombre.getValue().toString().toUpperCase());
            fuenteNueva.setDescripcion((this.it_descripcion.getValue()!=null)?this.it_descripcion.getValue().toString():null);
            fuenteNueva.setIdTipoFuente((Lista)this.selectOneChoice7.getValue());
            fuenteNueva.setCodigoCuencaAA((this.it_codCuenca.getValue()!=null)?this.it_codCuenca.getValue().toString():null);
            fuenteNueva.setFuente_car((this.it_fuenteCAR.getValue()!=null)?this.it_fuenteCAR.getValue().toString():null);
                
            fuenteNueva.setId_provinciahidro(((Lista)this.socProvinciaHidro.getValue()!=null)?(Lista)this.socProvinciaHidro.getValue():null);
            fuenteNueva.setUnidadhidro((this.itUnidadHidrogeologica.getValue()!=null)?this.itUnidadHidrogeologica.getValue().toString():null);
            
            
            if(this.sbr_si.isSelected()){
                fuenteNueva.setEsCompartidaAux(true);
            }else{
                fuenteNueva.setEsCompartidaAux(false);
            }
            
            UsuarioConectadoTO usuarioConectado = 
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
            fuenteNueva.setCodAutoridad(usuarioConectado.getUsuario().getAutoridadAmbiental());
            
            FuenteDelegate fd = FuenteDelegate.getInstance();
            
            //validar si el nombre de esa fuente ya existe para una fuente en la
            //bd, diferente a la que se está actualizando.
            FnttFuente existe = fd.getFuente(fuenteNueva.getNombre(), 
                                             usuarioConectado.getUsuario().getAutoridadAmbiental(),
                                             fuenteNueva.getIdTipoFuente().getCodigo());
            if(existe!=null ){
                showMessage(getText("FNT_EXISTE"),
                            FacesMessage.SEVERITY_ERROR);
                return;                       
            }
            
            
           
            FnttFuente ff =  fd.updateFuente(fuenteNueva);
            
            FacesUtils.setInSession("fuenteSeleccionada",ff);
            try{
                
                /** 
                 * Estas variables son necesarias para llenar los datos intrinsecos de la auditoria.
                 */
               
                Auditoria auditoria = new Auditoria();
                auditoria.setIdUsuario(usuarioConectado.getUsuario().getCodigo());
                auditoria.setOperacion("GUARDAR");
                auditoria.setObjeto("FUENTES");
                auditoria.setMetodo(Thread.currentThread().getStackTrace()[1].getMethodName());
                auditoria.setClase(this.getClass().getName());
                //Objeto Afectado (usuario modificado, eliminado o agregado) 
                auditoria.setIdObjeto(new Long(ff.getId()));
               
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

    public void  setFormAF1(RichForm formAF1) {
        this.formAF1 = formAF1;
    }

    public RichForm getFormAF1() {
        return formAF1;
    }

    public void setDocumentAF1(RichDocument documentAF1) {
        this.documentAF1 = documentAF1;
    }

    public RichDocument getDocumentAF1() {
        return documentAF1;
    }

    public void setD_detalle(RichDialog d_detalle) {
        this.d_detalle = d_detalle;
    }

    public RichDialog getD_detalle() {
        return d_detalle;
    }

    public void setPsl2(RichPanelStretchLayout psl2) {
        this.psl2 = psl2;
    }

    public RichPanelStretchLayout getPsl2() {
        return psl2;
    }

    public void setPfl2(RichPanelFormLayout pfl2) {
        this.pfl2 = pfl2;
    }

    public RichPanelFormLayout getPfl2() {
        return pfl2;
    }


    public void setIt_nombre(RichInputText it_nombre) {
        this.it_nombre = it_nombre;
    }

    public RichInputText getIt_nombre() {
        return it_nombre;
    }

    public void setIt_descripcion(RichInputText it_descripcion) {
        this.it_descripcion = it_descripcion;
    }

    public RichInputText getIt_descripcion() {
        return it_descripcion;
    }


    public void setSbr_si(RichSelectBooleanRadio sbr_si) {
        this.sbr_si = sbr_si;
    }

    public RichSelectBooleanRadio getSbr_si() {
        return sbr_si;
    }

    public void setSbr_no(RichSelectBooleanRadio sbr_no) {
        this.sbr_no = sbr_no;
    }

    public RichSelectBooleanRadio getSbr_no() {
        return sbr_no;
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

    public void setPgl2(RichPanelGroupLayout pgl2) {
        this.pgl2 = pgl2;
    }

    public RichPanelGroupLayout getPgl2() {
        return pgl2;
    }

    public void setPb1(RichPanelBox pb1) {
        this.pb1 = pb1;
    }

    public RichPanelBox getPb1() {
        return pb1;
    }

    public void setCbNext1(RichCommandButton cbNext1) {
        this.cbNext1 = cbNext1;
    }

    public RichCommandButton getCbNext1() {
        return cbNext1;
    }


    public void setS1(RichSpacer s1) {
        this.s1 = s1;
    }

    public RichSpacer getS1() {
        return s1;
    }

    public void setPgl3(RichPanelGroupLayout pgl3) {
        this.pgl3 = pgl3;
    }

    public RichPanelGroupLayout getPgl3() {
        return pgl3;
    }


    public void setPfl1(RichPanelFormLayout pfl1) {
        this.pfl1 = pfl1;
    }

    public RichPanelFormLayout getPfl1() {
        return pfl1;
    }

    public void setSelectOneChoice7(RichSelectOneChoice selectOneChoice7) {
        this.selectOneChoice7 = selectOneChoice7;
    }

    public RichSelectOneChoice getSelectOneChoice7() {
        return selectOneChoice7;
    }

    public void setSelectItems7(UISelectItems selectItems7) {
        this.selectItems7 = selectItems7;
    }

    public UISelectItems getSelectItems7() {
        return selectItems7;
    }

    public void setListaTipos(List listaTipos) {
        this.listaTipos = listaTipos;
    }

    public List getListaTipos() {
        return listaTipos;
    }

    public void setCbRelacion(RichCommandButton cbRelacion) {
        this.cbRelacion = cbRelacion;
    }

    public RichCommandButton getCbRelacion() {
        return cbRelacion;
    }


    public void setPs1(RichPanelSplitter ps1) {
        this.ps1 = ps1;
    }

    public RichPanelSplitter getPs1() {
        return ps1;
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

    public void setCommandMenuItem1(RichCommandMenuItem commandMenuItem1) {
        this.commandMenuItem1 = commandMenuItem1;
    }

    public RichCommandMenuItem getCommandMenuItem1() {
        return commandMenuItem1;
    }

    public void setPsl7(RichPanelStretchLayout psl7) {
        this.psl7 = psl7;
    }

    public RichPanelStretchLayout getPsl7() {
        return psl7;
    }

    public void setPgl14(RichPanelGroupLayout pgl14) {
        this.pgl14 = pgl14;
    }

    public RichPanelGroupLayout getPgl14() {
        return pgl14;
    }

    public void setCbAceptar(RichCommandButton cbAceptar) {
        this.cbAceptar = cbAceptar;
    }

    public RichCommandButton getCbAceptar() {
        return cbAceptar;
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

    public void setP_registro_exitoso(RichPopup p_registro_exitoso) {
        this.p_registro_exitoso = p_registro_exitoso;
    }

    public RichPopup getP_registro_exitoso() {
        return p_registro_exitoso;
    }

    public void setD_registro_exitoso(RichDialog d_registro_exitoso) {
        this.d_registro_exitoso = d_registro_exitoso;
    }

    public RichDialog getD_registro_exitoso() {
        return d_registro_exitoso;
    }

    public void setIt_codCuenca(RichInputText inputText1) {
        this.it_codCuenca = inputText1;
    }

    public RichInputText getIt_codCuenca() {
        return it_codCuenca;
    }


    public void setFuente(FnttFuente fuente) {
        this.fuente = fuente;
    }

    public FnttFuente getFuente() {
        return fuente;
    }

    public void setValorNombre(String valorNombre) {
        this.valorNombre = valorNombre;
    }

    public String getValorNombre() {
        return valorNombre;
    }
    
    
    public void cb_aceptar_actionListener(ActionEvent actionEvent) {
        this.setAceptarAction("detalleFuente");
    }

    
    

    public String getAceptarAction() {
        return aceptarAction;
    }

    public void setAceptarAction(String aceptarAction) {
        this.aceptarAction = aceptarAction;
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

    public void setListaProvincias(List listaProvincias) {
        this.listaProvincias = listaProvincias;
    }

    public List getListaProvincias() {
        return listaProvincias;
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
