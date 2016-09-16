package co.gov.ideam.sirh.usuarios.agua.view;

import co.gov.ideam.sirh.usuarios.agua.model.CriteriosBusquedaTO;
import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Categoria;
import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.view.ParametrosDelegate;
import co.gov.ideam.sirh.porh.model.PorhPlanes;
import co.gov.ideam.sirh.porh.view.PorhDelegate;
import co.gov.ideam.sirh.reportes.view.ReporteDelegate;
import co.gov.ideam.sirh.seguridad.model.PerfilVO;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.seguridad.view.SeguridadDelegate;
import co.gov.ideam.sirh.usuarios.agua.model.Concesion;
import co.gov.ideam.sirh.usuarios.agua.model.ConcesionTO;
import co.gov.ideam.sirh.usuarios.agua.model.Predio;

import co.gov.ideam.sirh.usuarios.agua.model.UsuarioAgua;
import co.gov.ideam.sirh.util.Constantes;
import co.gov.ideam.sirh.util.ConstantesCalidad;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectItem;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichCommandMenuItem;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;

import org.apache.myfaces.trinidad.component.UIXGroup;

public class ReporteAuditoriaBean extends StandarBean{
    private RichForm f2;
    private RichDocument d2;
    private RichPanelCollection pc1;
    private List listaReporteAuditoria;    
    private String numeroExpediente;
    private RichPanelSplitter ps1;
    private RichPanelFormLayout pfl1;    
    private RichCommandButton cb_buscar;
    private RichMenu m2;
    private RichCommandMenuItem cmi_imprimir;
    private RichSpacer s1;
    private RichSelectOneChoice soc1;
    private RichSelectItem si1;
    private RichSelectItem si2;
    private RichSelectItem si3;
    private Concesion estados;
    private String estadoSeleccionado;
    private RichPanelGroupLayout panelGroupLayout1;
    private RichInputText it_numExp1;
    private RichSpacer s11;
    private RichSelectOneChoice soc11;
    private RichSelectItem si11;
    private RichSelectItem si21;
    private RichSelectItem si31;
    private RichCommandButton cb_buscar1;
    private RichPanelStretchLayout panelStretchLayout1;
    private UIXGroup group1;
    private RichPanelGroupLayout panelGroupLayout11;
    private RichPanelFormLayout pfl11;
    private RichCommandButton cb_buscar11;
    private RichPanelCollection pc11;
    private RichMenu m21;
    private RichCommandMenuItem cmi_imprimir1;
    private RichTable t_reporteAuditoria;
    private RichPanelGroupLayout panelGroupLayout2;
    private RichOutputText outputText1;
    private RichPanelGroupLayout pgl1;
    private RichPopup p_errores;
    private RichDialog d_errores;    
    private RichOutputText ot_errores;
    private RichInputText it_errores;
    private String resolucionCaudal;
    private RichInputDate finicio;
    private RichInputDate ffin;
    private String fInicio;
    private String fFin;
    private RichSelectOneChoice soc_autoridad_id;
    private String visiblePerfil="true";
    private UISelectItems si1Autoridad;
    private List listaAutoridades;
    private List listaUsuarioLogin;
    private Autoridades autoridadRolRevision=null;
    private Lista usuarioLogin=null;
    private RichSelectOneChoice soc_login;
    private UISelectItems selectItems1;

    public ReporteAuditoriaBean(){
        FacesUtils.setActiveBean("ReporteAuditoriaBean",
                                 "ReporteAuditoriaBean",
                                 UsuariosAguaDelegate.class);
        this.load();        
    }
    public void load(){
        try{            
        
            
            UsuarioConectadoTO usuarioConectado = 
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
            
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
            SeguridadDelegate seg = SeguridadDelegate.getInstance(); 
            PerfilVO pp= new PerfilVO();
            pp=   seg.getPerfilUsuario(usuarioConectado.getUsuario().getCodigo());
            
            System.out.println("----------Perfil del usuario autenticado:"+pp.getCodigo());
            if (usuarioConectado.getUsuario().getAutoridadAmbiental()==null ||
                usuarioConectado.getUsuario().getAutoridadAmbiental().getId() == null ||
                usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue() ==0 ||
                usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue() == Constantes.IDEAM||
                pp.getCodigo()==ConstantesCalidad.PERFIL_CONSULTA){
                this.listaReporteAuditoria= new ArrayList();
                listaUsuarioLogin= new ArrayList();
                this.visiblePerfil="false";
                this.listaAutoridades= this.cargarAutoridades();
             
            }else{
                this.visiblePerfil="true";
                 listaReporteAuditoria = uad.getAuditoriaUsuarios(null,null,null,null);                  
                
            }
            
         
            
        }catch(IdeamException e){
            showMessage(e);
        }
    }
    
   

    public void usuario_actionListener(ActionEvent actionEvent) {
        Long codigoUsuario = 
            (Long)actionEvent.getComponent().getAttributes().get("codigoUsuario");                
        FacesUtils.setInSession("usuarioSeleccionado", codigoUsuario);
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


    public void setPc1(RichPanelCollection pc1) {
        this.pc1 = pc1;
    }

    public RichPanelCollection getPc1() {
        return pc1;
    }

  

    public void cb_buscar_actionListener(ActionEvent actionEvent) throws IdeamException {
       buscar();    
    }


    public void buscar() throws IdeamException {
        // Verificar que se haya ingresado un numero de Expediente        
        boolean existenCriterios = false;
        
        if(this.soc_autoridad_id.getValue()!=null){
            ParametrosDelegate pd = ParametrosDelegate.getInstance();
            Autoridades aut=(Autoridades)this.soc_autoridad_id.getValue();
            this.autoridadRolRevision=aut;
            System.out.println("Autoridad seleccioanda por rol MADS:"+ this.autoridadRolRevision.getId());
            existenCriterios = true;    
        }
        if(this.soc_login.getValue()!=null){
           Lista uu =(Lista)this.soc_login.getValue();
           this.usuarioLogin=uu;
           existenCriterios = true;    
        }
        
        if(fInicio == null ||   fInicio.length()==0){
            //showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_numExp);                   
        }else{
            existenCriterios = true;
        }
        if(fFin == null ||   fFin.length()==0){
            //showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_numExp);                   
        }else{
            existenCriterios = true;
        }
        
      
        
        if(!existenCriterios){            
            showMessage(getText("no_existen_criterios"),FacesMessage.SEVERITY_ERROR);
            return;
        }
        
        try{            
            UsuarioConectadoTO usuarioConectado = 
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
            listaReporteAuditoria = new ArrayList();
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
            SeguridadDelegate seg = SeguridadDelegate.getInstance(); 
            PerfilVO pp=  seg.getPerfilUsuario(usuarioConectado.getUsuario().getCodigo());
             
            
            if (usuarioConectado.getUsuario().getAutoridadAmbiental()==null ||
                usuarioConectado.getUsuario().getAutoridadAmbiental().getId() == null ||
                usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue() ==0 ||
                usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue() == Constantes.IDEAM ||
                pp.getCodigo()==ConstantesCalidad.PERFIL_CONSULTA){
                    listaReporteAuditoria =uad.getAuditoriaUsuarios(fInicio,  fFin,
                                       this.autoridadRolRevision,this.usuarioLogin );
            }
                
            AdfFacesContext.getCurrentInstance().addPartialTarget(t_reporteAuditoria);
           
        }catch(IdeamException e){
            showMessage(e);
        }    
        
    }
    public String getNumeroExpediente() {
        return numeroExpediente;
    }

    public void setNumeroExpediente(String numeroExpediente) {
        this.numeroExpediente = numeroExpediente;
    }


    public void setPs1(RichPanelSplitter ps1) {
        this.ps1 = ps1;
    }

    public RichPanelSplitter getPs1() {
        return ps1;
    }

    public void setCb_buscar(RichCommandButton cb1) {
        this.cb_buscar = cb1;
    }

    public RichCommandButton getCb_buscar() {
        return cb_buscar;
    }
   

   
    public void setM2(RichMenu m2) {
        this.m2 = m2;
    }

    public RichMenu getM2() {
        return m2;
    }

    public void setCmi_imprimir(RichCommandMenuItem cmi2) {
        this.cmi_imprimir = cmi2;
    }

    public RichCommandMenuItem getCmi_imprimir() {
        return cmi_imprimir;
    }

    public void cmi_imprimir_actionListener(ActionEvent actionEvent) {
        try {
            HashMap parametros = new HashMap();
            parametros.put("p_modulo","Registro de Usuarios del Agua");
            parametros.put("p_titulo","Reporte de Auditoria");                        
            this.generateReport("ReporteAuditoria.jasper",                                                                 
                                this.listaReporteAuditoria,
                                parametros,
                                ReporteDelegate.EXCEL);
        } catch (IdeamException e) {
            showMessage(e);
        }        
    }

    public void setS1(RichSpacer s1) {
        this.s1 = s1;
    }

    public RichSpacer getS1() {
        return s1;
    }


    public void setSoc1(RichSelectOneChoice soc1) {
        this.soc1 = soc1;
    }

    public RichSelectOneChoice getSoc1() {
        return soc1;
    }

    public void setSi1(RichSelectItem si1) {
        this.si1 = si1;
    }

    public RichSelectItem getSi1() {
        return si1;
    }

    public void setSi2(RichSelectItem si2) {
        this.si2 = si2;
    }

    public RichSelectItem getSi2() {
        return si2;
    }

    public void setSi3(RichSelectItem si3) {
        this.si3 = si3;
    }

    public RichSelectItem getSi3() {
        return si3;
    }

    public Concesion getEstados() {
        return estados;
    }

    public void setEstados(Concesion estados) {
        this.estados = estados;
    }

    public void setPanelGroupLayout1(RichPanelGroupLayout panelGroupLayout1) {
        this.panelGroupLayout1 = panelGroupLayout1;
    }

    public RichPanelGroupLayout getPanelGroupLayout1() {
        return panelGroupLayout1;
    }

    public void setIt_numExp1(RichInputText it_numExp1) {
        this.it_numExp1 = it_numExp1;
    }

    public RichInputText getIt_numExp1() {
        return it_numExp1;
    }

    public void setS11(RichSpacer s11) {
        this.s11 = s11;
    }

    public RichSpacer getS11() {
        return s11;
    }

    public void setSoc11(RichSelectOneChoice soc11) {
        this.soc11 = soc11;
    }

    public RichSelectOneChoice getSoc11() {
        return soc11;
    }

    public void setSi11(RichSelectItem si11) {
        this.si11 = si11;
    }

    public RichSelectItem getSi11() {
        return si11;
    }

    public void setSi21(RichSelectItem si21) {
        this.si21 = si21;
    }

    public RichSelectItem getSi21() {
        return si21;
    }

    public void setSi31(RichSelectItem si31) {
        this.si31 = si31;
    }

    public RichSelectItem getSi31() {
        return si31;
    }

    public void setCb_buscar1(RichCommandButton cb_buscar1) {
        this.cb_buscar1 = cb_buscar1;
    }

    public RichCommandButton getCb_buscar1() {
        return cb_buscar1;
    }

    public void setPanelStretchLayout1(RichPanelStretchLayout panelStretchLayout1) {
        this.panelStretchLayout1 = panelStretchLayout1;
    }

    public RichPanelStretchLayout getPanelStretchLayout1() {
        return panelStretchLayout1;
    }

    public void setGroup1(UIXGroup group1) {
        this.group1 = group1;
    }

    public UIXGroup getGroup1() {
        return group1;
    }

    public void setPanelGroupLayout11(RichPanelGroupLayout panelGroupLayout11) {
        this.panelGroupLayout11 = panelGroupLayout11;
    }

    public RichPanelGroupLayout getPanelGroupLayout11() {
        return panelGroupLayout11;
    }

    public void setPfl11(RichPanelFormLayout pfl11) {
        this.pfl11 = pfl11;
    }

    public RichPanelFormLayout getPfl11() {
        return pfl11;
    }


    public void setCb_buscar11(RichCommandButton cb_buscar11) {
        this.cb_buscar11 = cb_buscar11;
    }

    public RichCommandButton getCb_buscar11() {
        return cb_buscar11;
    }

    public void setPc11(RichPanelCollection pc11) {
        this.pc11 = pc11;
    }

    public RichPanelCollection getPc11() {
        return pc11;
    }

    public void setM21(RichMenu m21) {
        this.m21 = m21;
    }

    public RichMenu getM21() {
        return m21;
    }

    public void setCmi_imprimir1(RichCommandMenuItem cmi_imprimir1) {
        this.cmi_imprimir1 = cmi_imprimir1;
    }

    public RichCommandMenuItem getCmi_imprimir1() {
        return cmi_imprimir1;
    }

  

    public void setPanelGroupLayout2(RichPanelGroupLayout panelGroupLayout2) {
        this.panelGroupLayout2 = panelGroupLayout2;
    }

    public RichPanelGroupLayout getPanelGroupLayout2() {
        return panelGroupLayout2;
    }

    public void setOutputText1(RichOutputText outputText1) {
        this.outputText1 = outputText1;
    }

    public RichOutputText getOutputText1() {
        return outputText1;
    }




    public void setPgl1(RichPanelGroupLayout pgl1) {
        this.pgl1 = pgl1;
    }

    public RichPanelGroupLayout getPgl1() {
        return pgl1;
    }

    public void setP_errores(RichPopup p1) {
        this.p_errores = p1;
    }

    public RichPopup getP_errores() {
        return p_errores;
    }

    public void setD_errores(RichDialog d1) {
        this.d_errores = d1;
    }

    public RichDialog getD_errores() {
        return d_errores;
    }

    public void setPfl1(RichPanelFormLayout pfl1) {
        this.pfl1 = pfl1;
    }

    public RichPanelFormLayout getPfl1() {
        return pfl1;
    }

    public void setOt_errores(RichOutputText ot1) {
        this.ot_errores = ot1;
    }

    public RichOutputText getOt_errores() {
        return ot_errores;
    }

    public void setIt_errores(RichInputText it1) {
        this.it_errores = it1;
    }

    public RichInputText getIt_errores() {
        return it_errores;
    }

    

    public void setFinicio(RichInputDate inputDate1) {
        this.finicio = inputDate1;
    }

    public RichInputDate getFinicio() {
        return finicio;
    }

    public void setFfin(RichInputDate inputDate1) {
        this.ffin = inputDate1;
    }

    public RichInputDate getFfin() {
        return ffin;
    }

    public void setEstadoSeleccionado1(String estadoSeleccionado) {
        this.estadoSeleccionado = estadoSeleccionado;
    }

    public void setResolucionCaudal(String resolucionCaudal) {
        this.resolucionCaudal = resolucionCaudal;
    }

    public String getResolucionCaudal() {
        return resolucionCaudal;
    }

    public void setFInicio(String fInicio) {
        this.fInicio = fInicio;
    }

    public String getFInicio() {
        return fInicio;
    }

    public void setFFin(String fFin) {
        this.fFin = fFin;
    }

    public String getFFin() {
        return fFin;
    }

    public void setSoc_autoridad_id(RichSelectOneChoice soc_autoridad_id) {
        this.soc_autoridad_id = soc_autoridad_id;
    }

    public RichSelectOneChoice getSoc_autoridad_id() {
        return soc_autoridad_id;
    }
    public void soc_filtro_porAtoridad(ValueChangeEvent valueChangeEvent) throws IdeamException {
        Object autoridadSeleccionada = valueChangeEvent.getNewValue();
        buscar();
    }
    public void setVisiblePerfil(String visiblePerfil) {
        this.visiblePerfil = visiblePerfil;
    }

    public String getVisiblePerfil() {
        return visiblePerfil;
    }

    public void setSi1Autoridad(UISelectItems si1Autoridad) {
        this.si1Autoridad = si1Autoridad;
    }

    public UISelectItems getSi1Autoridad() {
        return si1Autoridad;
    }

    public void setListaAutoridades(List listaAutoridades) {
        this.listaAutoridades = listaAutoridades;
    }

    public List getListaAutoridades() {
        return listaAutoridades;
    }


    public void setAutoridadRolRevision(Autoridades autoridadRolRevision) {
        this.autoridadRolRevision = autoridadRolRevision;
    }

    public Autoridades getAutoridadRolRevision() {
        return autoridadRolRevision;
    }

    public void setListaReporteAuditoria(List listaReporteAuditoria) {
        this.listaReporteAuditoria = listaReporteAuditoria;
    }

    public List getListaReporteAuditoria() {
        return listaReporteAuditoria;
    }

    public void setT_reporteAuditoria(RichTable t_reporteAuditoria) {
        this.t_reporteAuditoria = t_reporteAuditoria;
    }

    public RichTable getT_reporteAuditoria() {
        return t_reporteAuditoria;
    }

    public void setListaUsuarioLogin(List listaUsuarioLogin) {
        this.listaUsuarioLogin = listaUsuarioLogin;
    }

    public List getListaUsuarioLogin() {
        return listaUsuarioLogin;
    }

    public void setSoc_login(RichSelectOneChoice selectOneChoice1) {
        this.soc_login = selectOneChoice1;
    }

    public RichSelectOneChoice getSoc_login() {
        return soc_login;
    }

    public void setSelectItems1(UISelectItems selectItems1) {
        this.selectItems1 = selectItems1;
    }

    public UISelectItems getSelectItems1() {
        return selectItems1;
    }
    
    
    //ChangeListener 
    public void select_autoridad_valueChangeListener(ValueChangeEvent valueChangeEvent) throws IdeamException{
      Autoridades aut = (Autoridades)valueChangeEvent.getNewValue();
      try{
          if(aut!=null){
           this.listaUsuarioLogin = this.cargarUsusariosLogin(aut);                
          }else{
              this.listaUsuarioLogin = new ArrayList();    
          }
      }catch(IdeamException e){
          showMessage(e);
      }
      AdfFacesContext.getCurrentInstance().addPartialTarget(soc_login);
      }


    public void setUsuarioLogin(Lista usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public Lista getUsuarioLogin() {
        return usuarioLogin;
    }
}
