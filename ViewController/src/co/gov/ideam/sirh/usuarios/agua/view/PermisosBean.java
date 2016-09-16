package co.gov.ideam.sirh.usuarios.agua.view;

import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.view.ParametrosDelegate;
import co.gov.ideam.sirh.reportes.view.ReporteDelegate;
import co.gov.ideam.sirh.seguridad.model.PerfilVO;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.seguridad.view.SeguridadDelegate;
import co.gov.ideam.sirh.usuarios.agua.model.Concesion;
import co.gov.ideam.sirh.usuarios.agua.model.ConcesionTO;
import co.gov.ideam.sirh.usuarios.agua.model.PermisoVertimiento;
import co.gov.ideam.sirh.usuarios.agua.model.PermisoVertimientoTO;
import co.gov.ideam.sirh.usuarios.agua.model.PermisoVertimientoDetalle;
import co.gov.ideam.sirh.usuarios.agua.model.Predio;
import co.gov.ideam.sirh.usuarios.agua.model.PrediosTO;
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
import javax.faces.context.FacesContext;
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
import oracle.adf.view.rich.context.AdfFacesContext;

import org.apache.myfaces.trinidad.component.UIXGroup;

public class PermisosBean extends StandarBean{
    private RichForm f2;
    private RichDocument d2;
    private RichPanelSplitter ps1;
    private RichPanelCollection pc1;
    
    private List listaPermisos;
    private List listaPermisosDetalle;
    private String criterioBusqueda;
    private RichPanelFormLayout pfl2;
    private RichInputText it_errores;
    private RichCommandButton cb1;
    private RichMenu m2;
    private RichCommandMenuItem cmi_imprimir;

    private RichPanelStretchLayout panelStretchLayout1;
    private UIXGroup group1;
    private RichPanelFormLayout pfl21;
    private RichInputText it11;
    private RichCommandButton cb11;
    private RichPanelCollection pc11;
    private RichMenu m21;
    private RichCommandMenuItem cmi_imprimir1;
    private RichCommandMenuItem cmi_imprimirDetalle;
    private RichTable t_permisos;
    private RichPanelGroupLayout panelGroupLayout1;
    private RichOutputText outputText1;
    private RichPanelGroupLayout panelGroupLayout2;
    private RichOutputText outputText2titulo;
    private RichSelectOneChoice soc_estado;
    private RichSelectItem si1;
    private RichSelectItem si2;
    private RichSelectItem si3;
    private String estadoSeleccionado;
    private RichCommandMenuItem cmi_errores;
    private RichPanelGroupLayout pgl1;
    private RichPopup p_errores;
    private RichDialog d_errores;
    private RichPanelFormLayout pfl1;
    private RichOutputText ot_errores;
    private RichInputDate inputDate1;
    private RichInputDate inputDate2;
    private RichInputText itResol;
    private RichInputDate inputDate3;
    private RichInputDate inputDate4;
    private RichInputText inputText1;
    private String nroResol;
    private String fInicio;
    private String fFin;
    private RichSelectOneChoice soc_autoridad_id;
    private String visiblePerfil="true";
    private UISelectItems si1Autoridad;
    private List listaAutoridades;
    private Autoridades autoridadRolRevision=null;
    
    
    public PermisosBean(){
        FacesUtils.setActiveBean("permisos",
                                 "Permisos",
                                 UsuariosAguaDelegate.class);
        this.load();        
    }
    public void load(){
        try{           
            if(FacesUtils.getFromSession("FiltroPermisosVertimientoDashboard")!=null){
                setEstadoSeleccionado((String)FacesUtils.getFromSession("FiltroPermisosVertimientoDashboard"));                                    
                FacesUtils.removeFromSession("FiltroPermisosVertimientoDashboard");                
            }
            
            UsuarioConectadoTO usuarioConectado = 
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
            
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
            SeguridadDelegate seg = SeguridadDelegate.getInstance(); 
            PerfilVO pp= new PerfilVO();
            pp=   seg.getPerfilUsuario(usuarioConectado.getUsuario().getCodigo());
            if (usuarioConectado.getUsuario().getAutoridadAmbiental()==null ||
                usuarioConectado.getUsuario().getAutoridadAmbiental().getId() == null ||
                usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue() ==0 ||
                usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue() == Constantes.IDEAM||
                pp.getCodigo()==ConstantesCalidad.PERFIL_CONSULTA){
                this.listaPermisos= new ArrayList();
                this.listaPermisosDetalle= new ArrayList();
                this.visiblePerfil="false";
                this.listaAutoridades= this.cargarAutoridades();
                if(estadoSeleccionado!=null && estadoSeleccionado.length()>0){
                    listaPermisos = uad.getAllPermisos(estadoSeleccionado);
                }// pilar
            }else{
                this.visiblePerfil="true";
                if(estadoSeleccionado!=null && estadoSeleccionado.length()>0){
                    listaPermisos = 
                        uad.getAllPermisos(usuarioConectado.getUsuario().getAutoridadAmbiental(),
                                           estadoSeleccionado);                
                }else{
                    listaPermisos = 
                        uad.getAllPermisos(usuarioConectado.getUsuario().getAutoridadAmbiental(),
                                           null);
                
                }
              //listaPermisosDetalle= uad.getDetallePermisosVertimiento(usuarioConectado.getUsuario().getAutoridadAmbiental(), null);
              listaPermisosDetalle= uad.getDetallePermisosVertimiento(usuarioConectado.getUsuario().getAutoridadAmbiental());    
            
            
            }   
            
            //Cabril 04/03/2015
            if (listaPermisos != null) {
                
                Integer i=1;
                
                Iterator<PermisoVertimientoTO> it = listaPermisos.iterator();
                while (it.hasNext()) {
                    PermisoVertimientoTO usu = it.next();
                    usu.setNum(i);
                    i++;
                } 
            }
            
        }catch(IdeamException e){
            showMessage(e);
        }
    }    
    public void permiso_actionListener(ActionEvent actionEvent) {
        Long codigoUsuario = 
            (Long)actionEvent.getComponent().getAttributes().get("codigoUsuario");                
        Long codigoPredio = 
            (Long)actionEvent.getComponent().getAttributes().get("codigoPredio");                
        Long codigo = 
            (Long)actionEvent.getComponent().getAttributes().get("codigo");                        
            
        FacesUtils.setInSession("permisoSeleccionado", codigo);
        FacesUtils.setInSession("usuarioSeleccionado", codigoUsuario);
        FacesUtils.setInSession("predioSeleccionado", codigoPredio);
        FacesUtils.setInSession("paginaOrigen", "permisosVertimiento");
    }    
    
    public void permiso_novedad_actionListener(ActionEvent actionEvent) {
        String numeroExpediente = 
            (String)actionEvent.getComponent().getAttributes().get("numeroExpediente");                
        try{
            UsuarioConectadoTO usuarioConectado = 
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
            
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
            PermisoVertimiento novedad = uad.getPermisoVertimiento(
                                             numeroExpediente,
                                             usuarioConectado.getUsuario().getCodigoAutoridadAmbiental().longValue());
            if(novedad!=null){                
                FacesUtils.setInSession("permisoSeleccionado", novedad.getCodigo());        
                Predio p = uad.getPredio(novedad.getCodigoPredio());
                FacesUtils.setInSession("predioSeleccionado", p);                 
                FacesUtils.setInSession("usuarioSeleccionado", p.getCodigoUsuario());                                
            }
        }catch(IdeamException e){
            showMessage(e);   
        }                
                    
        FacesUtils.setInSession("paginaOrigen", "permisosVertimiento");
    }     
    public void predio_actionListener(ActionEvent actionEvent) {
        Long codigoUsuario = 
            (Long)actionEvent.getComponent().getAttributes().get("codigoUsuario");                
        Long codigoPredio = 
            (Long)actionEvent.getComponent().getAttributes().get("codigoPredio");                
        FacesUtils.setInSession("usuarioSeleccionado", codigoUsuario);
        FacesUtils.setInSession("predioSeleccionado", codigoPredio);
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


    public void setPs1(RichPanelSplitter ps1) {
        this.ps1 = ps1;
    }

    public RichPanelSplitter getPs1() {
        return ps1;
    }

    public void setPc1(RichPanelCollection pc1) {
        this.pc1 = pc1;
    }

    public RichPanelCollection getPc1() {
        return pc1;
    }

    public List getListaPermisos() {
        return listaPermisos;
    }

    public void setListaPermisos(List listaPermisos) {
        this.listaPermisos = listaPermisos;
    }

    public void cb_buscar_actionListener(ActionEvent actionEvent) throws IdeamException {
        buscar();     
    }
    public void buscar() throws IdeamException {
        // Verificar que se haya ingresado un numero de Expediente    
        String estado="";
        boolean existenCriterios = false;
        if(this.soc_autoridad_id.getValue()!=null){
            ParametrosDelegate pd = ParametrosDelegate.getInstance();
            Autoridades aut=(Autoridades)this.soc_autoridad_id.getValue();
            this.autoridadRolRevision=aut;
            System.out.println("Autoridad seleccioanda por rol MADS:"+ this.autoridadRolRevision.getId());
            existenCriterios = true;    
        }
        if(criterioBusqueda == null || 
           criterioBusqueda.length()==0){            
            //showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it1);       
            //return;
        }else{
            existenCriterios = true;
        }
        
        if(nroResol == null || 
           nroResol.length()==0){            
            //showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it1);       
            //return;
        }else{
            existenCriterios = true;
        }
        
        
        if(fInicio == null || 
           fInicio.length()==0){            
            //showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it1);       
            //return;
        }else{
            existenCriterios = true;
        }
        
        
        if(fFin == null || 
           fFin.length()==0){            
            //showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it1);       
            //return;
        }else{
            existenCriterios = true;
        }
        
        
        if(soc_estado.getValue()!=null){
            existenCriterios = true;
            estado=soc_estado.getValue().toString();
        }
        
        if(!existenCriterios){            
            showMessage(getText("no_existen_criterios"),FacesMessage.SEVERITY_ERROR);
            return;
        }
        
        try{            
            UsuarioConectadoTO usuarioConectado = 
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
            listaPermisos = new ArrayList();
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
            SeguridadDelegate seg = SeguridadDelegate.getInstance(); 
            PerfilVO pp=  seg.getPerfilUsuario(usuarioConectado.getUsuario().getCodigo());
            if (usuarioConectado.getUsuario().getAutoridadAmbiental()==null ||
                usuarioConectado.getUsuario().getAutoridadAmbiental().getId() == null ||
                usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue() ==0 ||
                usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue() == Constantes.IDEAM||
                pp.getCodigo()==ConstantesCalidad.PERFIL_CONSULTA){
                listaPermisos = 
                    uad.getAllPermisos(criterioBusqueda,nroResol,  fInicio,  fFin,
                                       this.autoridadRolRevision,
                                       estado);
                
            }else{
                if(soc_estado.getValue()!=null){
                    listaPermisos = 
                        uad.getAllPermisos(criterioBusqueda,nroResol,  fInicio,  fFin,
                                           usuarioConectado.getUsuario().getAutoridadAmbiental(),
                                           soc_estado.getValue().toString());
                }else{
                    listaPermisos = 
                        uad.getAllPermisos(criterioBusqueda,nroResol,  fInicio,  fFin,
                                           usuarioConectado.getUsuario().getAutoridadAmbiental(),
                                           null);
                }
            }     
            
            
            //Cabril 04/03/2015
            if (listaPermisos != null) {
                
                Integer i=1;
                
                Iterator<PermisoVertimientoTO> it = listaPermisos.iterator();
                while (it.hasNext()) {
                    PermisoVertimientoTO usu = it.next();
                    usu.setNum(i);
                    i++;
                } 
            }
            
            
            AdfFacesContext.getCurrentInstance().addPartialTarget(t_permisos);
            t_permisos.setSelectedRowKeys(null);
        }catch(IdeamException e){
            showMessage(e);
        }   
    }

    public String getCriterioBusqueda() {
        return criterioBusqueda;
    }

    public void setCriterioBusqueda(String criterioBusqueda) {
        this.criterioBusqueda = criterioBusqueda;
    }


    public void setPfl2(RichPanelFormLayout pfl2) {
        this.pfl2 = pfl2;
    }

    public RichPanelFormLayout getPfl2() {
        return pfl2;
    }

    public void setIt_errores(RichInputText it1) {
        this.it_errores = it1;
    }

    public RichInputText getIt_errores() {
        return it_errores;
    }

    public void setCb1(RichCommandButton cb1) {
        this.cb1 = cb1;
    }

    public RichCommandButton getCb1() {
        return cb1;
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
            parametros.put("p_titulo","Permisos de Vertimiento");                        
            this.generateReport("PermisosVertimiento.jasper",                                                                 
                                this.listaPermisos,
                                parametros,
                                ReporteDelegate.EXCEL);
        } catch (IdeamException e) {
            showMessage(e);
        }        
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

    public void setPfl21(RichPanelFormLayout pfl21) {
        this.pfl21 = pfl21;
    }

    public RichPanelFormLayout getPfl21() {
        return pfl21;
    }

    public void setIt11(RichInputText it11) {
        this.it11 = it11;
    }

    public RichInputText getIt11() {
        return it11;
    }

    public void setCb11(RichCommandButton cb11) {
        this.cb11 = cb11;
    }

    public RichCommandButton getCb11() {
        return cb11;
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
    
    public void setCmi_imprimirDetalle(RichCommandMenuItem cmi_imprimirDetalle) {
        this.cmi_imprimirDetalle = cmi_imprimirDetalle;
    }
    
  public void cmi_ImprimirDetalle_actionListener(ActionEvent actionEvent) {
      try {
          HashMap parametros = new HashMap();
          parametros.put("p_modulo","Registro de Usuarios del Agua");
          parametros.put("p_titulo","Permisos de Vertimiento");                        
          this.generateReport("PermisosVertimientoDetalle.jasper",                                                                 
                              this.listaPermisosDetalle,
                              parametros,
                              ReporteDelegate.EXCEL);
      } catch (IdeamException e) {
          showMessage(e);
      }        
  }
  
    public RichCommandMenuItem getCmi_imprimirDetalle() {
        return cmi_imprimirDetalle;
    }
  
    public void setT_permisos(RichTable t_permisos1) {
        this.t_permisos = t_permisos1;
    }

    public RichTable getT_permisos() {
        return t_permisos;
    }

    public void setPanelGroupLayout1(RichPanelGroupLayout panelGroupLayout1) {
        this.panelGroupLayout1 = panelGroupLayout1;
    }

    public RichPanelGroupLayout getPanelGroupLayout1() {
        return panelGroupLayout1;
    }

    public void setOutputText1(RichOutputText outputText1) {
        this.outputText1 = outputText1;
    }

    public RichOutputText getOutputText1() {
        return outputText1;
    }

    public void setPanelGroupLayout2(RichPanelGroupLayout panelGroupLayout2) {
        this.panelGroupLayout2 = panelGroupLayout2;
    }

    public RichPanelGroupLayout getPanelGroupLayout2() {
        return panelGroupLayout2;
    }

    public void setOutputText2titulo(RichOutputText outputText2) {
        this.outputText2titulo = outputText2;
    }

    public RichOutputText getOutputText2titulo() {
        return outputText2titulo;
    }

    public void setSoc_estado(RichSelectOneChoice soc1) {
        this.soc_estado = soc1;
    }

    public RichSelectOneChoice getSoc_estado() {
        return soc_estado;
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

    public String getEstadoSeleccionado() {
        return estadoSeleccionado;
    }

    public void setEstadoSeleccionado(String estadoSeleccionado) {
        if(estadoSeleccionado.equalsIgnoreCase("VENCIDOS")){
            this.estadoSeleccionado = "Vencidos";
        }else if(estadoSeleccionado.equalsIgnoreCase("VIGENTES")){
            this.estadoSeleccionado = getText("VIGENTES");
        }else if(estadoSeleccionado.equalsIgnoreCase("NO ESPECIFICADO")){
            this.estadoSeleccionado = getText("NO_ESPECIFICADO");
        }
    }

    public void setCmi_errores(RichCommandMenuItem cmi2) {
        this.cmi_errores = cmi2;
    }

    public RichCommandMenuItem getCmi_errores() {
        return cmi_errores;
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

    public void setIt1(RichInputText it1) {
        this.it_errores = it1;
    }

    public RichInputText getIt1() {
        return it_errores;
    }

    public void cmi_errores_actionListener(ActionEvent actionEvent) {
        if (t_permisos.getSelectedRowData()==null){
            showMessage(getText("seleccionar_registro"),
                        FacesMessage.SEVERITY_ERROR);
            return;
        }
        PermisoVertimientoTO permiso = (PermisoVertimientoTO)t_permisos.getSelectedRowData();
        if(permiso.isValid()){
            showMessage("El Permiso de Vertimiento " + permiso.getNumeroExpediente() + " no tiene errores de Validación", FacesMessage.SEVERITY_INFO);
            return;
        }
        this.it_errores.setValue(permiso.getErroresValidacion());
        this.ot_errores.setValue( "Errores de Validación del Permiso de Vertimiento " + 
                                  permiso.getNumeroExpediente());
        AdfFacesContext.getCurrentInstance().addPartialTarget(p_errores);
        showPopup(p_errores, true);        
    }

    public void setInputDate1(RichInputDate inputDate1) {
        this.inputDate1 = inputDate1;
    }

    public RichInputDate getInputDate1() {
        return inputDate1;
    }

    public void setInputDate2(RichInputDate inputDate2) {
        this.inputDate2 = inputDate2;
    }

    public RichInputDate getInputDate2() {
        return inputDate2;
    }

    public void setItResol(RichInputText inputText1) {
        this.itResol = inputText1;
    }

    public RichInputText getItResol() {
        return itResol;
    }

    public void setInputDate3(RichInputDate inputDate3) {
        this.inputDate3 = inputDate3;
    }

    public RichInputDate getInputDate3() {
        return inputDate3;
    }

    public void setInputDate4(RichInputDate inputDate4) {
        this.inputDate4 = inputDate4;
    }

    public RichInputDate getInputDate4() {
        return inputDate4;
    }

    public void setInputText1(RichInputText inputText1) {
        this.inputText1 = inputText1;
    }

    public RichInputText getInputText1() {
        return inputText1;
    }

    public void setEstadoSeleccionado1(String estadoSeleccionado) {
        this.estadoSeleccionado = estadoSeleccionado;
    }

    public void setNroResol(String nroResol) {
        this.nroResol = nroResol;
    }

    public String getNroResol() {
        return nroResol;
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
    public void setListaPermisosDetalle(List listaPermisosDetalle) {
      this.listaPermisosDetalle = listaPermisosDetalle;
    }

    public List getListaPermisosDetalle() {
     return listaPermisosDetalle;
    }
  
}
