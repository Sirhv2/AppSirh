package co.gov.ideam.sirh.usuarios.agua.view;

import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Categoria;
import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.view.ParametrosDelegate;

import co.gov.ideam.sirh.reportes.view.ReporteDelegate;
import co.gov.ideam.sirh.seguridad.model.PerfilVO;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.seguridad.view.SeguridadDelegate;
import co.gov.ideam.sirh.usuarios.agua.model.CriteriosBusquedaTO;
import co.gov.ideam.sirh.usuarios.agua.model.UsuarioAgua;
import co.gov.ideam.sirh.usuarios.agua.model.UsuarioAguaTO;
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
import oracle.adf.view.rich.component.rich.input.RichInputText;
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

public class UsuariosAguaBean extends StandarBean{
    
    private List listaUsuarios;
    private List listaAutoridades;
    private List listaTiposIdentificacion;
    private List listaDepartamentos;
    private List listaMunicipios;
    private String accionAdicionar;
    private List listaNaturalezaUsuarios;
    private Lista naturalezaSeleccionada;
    
    private RichForm f2;
    private RichDocument d2;
    private RichPanelStretchLayout psl1;
    private RichPanelSplitter ps1;
    private RichPanelFormLayout pfl1;
    private RichPanelCollection pc1;
    private RichTable t_usuarios;
    private RichSelectOneChoice soc_filtro_tipo_id;
    private RichSelectOneChoice soc_autoridad_id;
    private UISelectItems si1;
    private UISelectItems si1Autoridad;
    private RichInputText it_filtro_id;
    private RichInputText it_filtro_nombres;
    private RichInputText it_filtro_apellidos;
    private RichSelectOneChoice soc_filtro_mun;
    private UISelectItems si3;
    private RichSelectOneChoice soc_filtro_depto;
    private UISelectItems si2;
    private RichMenu m_archivo;
    private RichCommandMenuItem cmi_adicionar;
    private RichCommandMenuItem cmi_editar;
    private RichCommandButton cb_filtrar;
    private RichPanelGroupLayout pgl1;
    private RichSpacer s1;
    private RichCommandMenuItem cmi_imprimir;
    private RichSelectOneChoice soc_tipo_usuario;
    private UISelectItems si4;
    private RichSpacer s2;
    private RichPanelGroupLayout panelGroupLayout1;
    private RichOutputText outputText1titulo;
    private RichCommandMenuItem cmi_errores;
    private RichPanelGroupLayout pgl2;
    private RichPopup p_errores;
    private RichDialog d_errores;
    private RichPanelFormLayout pfl2;
    private RichInputText it_errores;
    private RichOutputText ot_errores;
    private String visiblePerfil="true";
    
    
    public UsuariosAguaBean(){
        FacesUtils.setActiveBean("usuariosAgua",
                                 "Usuarios Del Agua",
                                 UsuariosAguaDelegate.class);
        this.load();
    }
    public void load(){
        try{
            ParametrosDelegate pd = ParametrosDelegate.getInstance();
            if(FacesUtils.getFromSession("FiltroUsuariosDashboard")!=null){
                String tipoUsuario = 
                    (String)FacesUtils.getFromSession("FiltroUsuariosDashboard");                    
                
                naturalezaSeleccionada = 
                    pd.getListaByDescripcion(tipoUsuario, Categoria.TIPO_USUARIO);                
                FacesUtils.removeFromSession("FiltroUsuariosDashboard");                
            }
            
            this.listaTiposIdentificacion = this.cargarParametro(Categoria.TIPO_DOCUMENTO);
            this.listaDepartamentos = this.cargarDivipola(null);
            this.listaMunicipios = new ArrayList();
            this.listaNaturalezaUsuarios = this.cargarParametro(Categoria.TIPO_USUARIO);
                        
            UsuarioConectadoTO usuarioConectado = 
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
            
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
            SeguridadDelegate seg = SeguridadDelegate.getInstance(); 
            PerfilVO pp=  seg.getPerfilUsuario(usuarioConectado.getUsuario().getCodigo());
                   
            if (usuarioConectado.getUsuario().getAutoridadAmbiental()==null ||
                usuarioConectado.getUsuario().getAutoridadAmbiental().getId() == null ||
                usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue() ==0 ||
                usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue() == Constantes.IDEAM||
                pp.getCodigo()==ConstantesCalidad.PERFIL_CONSULTA){
                if (pp.getCodigo()==ConstantesCalidad.PERFIL_CONSULTA){
                       this.listaUsuarios= new ArrayList();
                       this.visiblePerfil="false";
                       this.listaAutoridades= this.cargarAutoridades();
                }else if(naturalezaSeleccionada==null){
                   listaUsuarios = uad.getAllUsuarios();
                }else{
                    CriteriosBusquedaTO criterios = new CriteriosBusquedaTO();
                    criterios.setNaturalezaUsuario(naturalezaSeleccionada);
                    listaUsuarios = uad.getAllUsuarios(criterios);
                }
            }else{
               if(naturalezaSeleccionada==null){
                       this.visiblePerfil="true";
                        listaUsuarios = uad.getAllUsuarios(usuarioConectado.getUsuario().
                                                           getAutoridadAmbiental().
                                                           getId().
                                                           longValue());
                }else{
                    CriteriosBusquedaTO criterios = new CriteriosBusquedaTO();
                    criterios.setNaturalezaUsuario(naturalezaSeleccionada);
                   
                    if (pp.getCodigo()==ConstantesCalidad.PERFIL_CONSULTA){
                        listaUsuarios = uad.getAllUsuarios();
                           this.visiblePerfil="false";
                    }else{
                           this.visiblePerfil="true";
                            listaUsuarios = uad.getAllUsuarios(criterios,
                                                                usuarioConectado.getUsuario().
                                                                getAutoridadAmbiental().
                                                                getId().longValue());
                        }
                }
            }
            
        //Cabril 04/03/2015
        if (listaUsuarios != null) {
            
            Integer i=1;
            
            Iterator<UsuarioAguaTO> it = listaUsuarios.iterator();
            while (it.hasNext()) {
                UsuarioAguaTO usu = it.next();
                usu.setNum(i);
                i++;
            } 
        }
            
        
        }catch(IdeamException e){
            showMessage(e);
        }
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

    public void setPsl1(RichPanelStretchLayout psl1) {
        this.psl1 = psl1;
    }

    public RichPanelStretchLayout getPsl1() {
        return psl1;
    }


    public List getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }


    public void setPs1(RichPanelSplitter ps1) {
        this.ps1 = ps1;
    }

    public RichPanelSplitter getPs1() {
        return ps1;
    }

    public void setPfl1(RichPanelFormLayout pfl1) {
        this.pfl1 = pfl1;
    }

    public RichPanelFormLayout getPfl1() {
        return pfl1;
    }

    public void setPc1(RichPanelCollection pc1) {
        this.pc1 = pc1;
    }

    public RichPanelCollection getPc1() {
        return pc1;
    }

    public void setT_usuarios(RichTable t1) {
        this.t_usuarios = t1;
    }

    public RichTable getT_usuarios() {
        return t_usuarios;
    }

    public List getListaTiposIdentificacion() {
        return listaTiposIdentificacion;
    }

    public void setListaTiposIdentificacion(List listaTiposIdentificacion) {
        this.listaTiposIdentificacion = listaTiposIdentificacion;
    }

    public List getListaDepartamentos() {
        return listaDepartamentos;
    }

    public void setListaDepartamentos(List listaDepartamentos) {
        this.listaDepartamentos = listaDepartamentos;
    }

    public List getListaMunicipios() {
        return listaMunicipios;
    }

    public void setListaMunicipios(List listaMunicipios) {
        this.listaMunicipios = listaMunicipios;
    }

    public void setSoc_filtro_tipo_id(RichSelectOneChoice soc1) {
        this.soc_filtro_tipo_id = soc1;
    }

    public RichSelectOneChoice getSoc_filtro_tipo_id() {
        return soc_filtro_tipo_id;
    }

    public void setSi1(UISelectItems si1) {
        this.si1 = si1;
    }

    public UISelectItems getSi1() {
        return si1;
    }

    public void setIt_filtro_id(RichInputText it1) {
        this.it_filtro_id = it1;
    }

    public RichInputText getIt_filtro_id() {
        return it_filtro_id;
    }

    public void setIt_filtro_nombres(RichInputText it2) {
        this.it_filtro_nombres = it2;
    }

    public RichInputText getIt_filtro_nombres() {
        return it_filtro_nombres;
    }

    public void setIt_filtro_apellidos(RichInputText it3) {
        this.it_filtro_apellidos = it3;
    }

    public RichInputText getIt_filtro_apellidos() {
        return it_filtro_apellidos;
    }


    public void setSoc_filtro_mun(RichSelectOneChoice soc2) {
        this.soc_filtro_mun = soc2;
    }

    public RichSelectOneChoice getSoc_filtro_mun() {
        return soc_filtro_mun;
    }

    public void setSi3(UISelectItems si3) {
        this.si3 = si3;
    }

    public UISelectItems getSi3() {
        return si3;
    }

    public void setSoc_filtro_depto(RichSelectOneChoice soc3) {
        this.soc_filtro_depto = soc3;
    }

    public RichSelectOneChoice getSoc_filtro_depto() {
        return soc_filtro_depto;
    }

    public void setSi2(UISelectItems si2) {
        this.si2 = si2;
    }

    public UISelectItems getSi2() {
        return si2;
    }

    public void setM_archivo(RichMenu m2) {
        this.m_archivo = m2;
    }

    public RichMenu getM_archivo() {
        return m_archivo;
    }

    public void setCmi_adicionar(RichCommandMenuItem cmi2) {
        this.cmi_adicionar = cmi2;
    }

    public RichCommandMenuItem getCmi_adicionar() {
        return cmi_adicionar;
    }

    public void setCmi_editar(RichCommandMenuItem cmi3) {
        this.cmi_editar = cmi3;
    }

    public RichCommandMenuItem getCmi_editar() {
        return cmi_editar;
    }

    public void cmi_adicionar_actionListener(ActionEvent actionEvent) {
        accionAdicionar = "";
        // Si el usuario conectado no tiene autoridad ambiental o 
        // la autoridad es Ideam, no se permite realizar la accion
        UsuarioConectadoTO usuarioConectado = 
            (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
        
        Autoridades autoridadAmbiental = usuarioConectado.getUsuario().getAutoridadAmbiental();
        if(autoridadAmbiental==null ||
           autoridadAmbiental.getId().intValue() == Constantes.IDEAM){
            showMessage(getText("NO_EXISTE_AUTORIDAD_AMBIENTAL"),
                        FacesMessage.SEVERITY_ERROR);       
        }else{
            accionAdicionar = "adicionarUsuario";
        }
    }

    public String getAccionAdicionar() {
        return accionAdicionar;
    }

    public void setAccionAdicionar(String accionAdicionar) {
        this.accionAdicionar = accionAdicionar;
    }

    public void setCb_filtrar(RichCommandButton cb1) {
        this.cb_filtrar = cb1;
    }

    public RichCommandButton getCb_filtrar() {
        return cb_filtrar;
    }

    public void setPgl1(RichPanelGroupLayout pgl1) {
        this.pgl1 = pgl1;
    }

    public RichPanelGroupLayout getPgl1() {
        return pgl1;
    }

    public void setS1(RichSpacer s1) {
        this.s1 = s1;
    }

    public RichSpacer getS1() {
        return s1;
    }

    public void cmi_editar_actionListener(ActionEvent actionEvent) {
        accionAdicionar = "";
        if (t_usuarios.getSelectedRowData()==null){
            showMessage(getText("seleccionar_registro"),
                        FacesMessage.SEVERITY_ERROR);
            return;
        }
        UsuarioAguaTO usuario = (UsuarioAguaTO)t_usuarios.getSelectedRowData();
        FacesUtils.setInSession("usuarioSeleccionado", usuario);
        FacesUtils.removeFromSession("predioSeleccionado");
        FacesUtils.removeManagedBeanFromSession("UsuariosTreeHandler");
        FacesUtils.removeManagedBeanFromSession("PrediosTreeHandler");
        accionAdicionar = "detalleUsuario";
    }

    public void cb_filtrar_actionListener(ActionEvent actionEvent) throws IdeamException {
        buscar();
    }
    public void buscar() throws IdeamException {
        CriteriosBusquedaTO crieterios = new CriteriosBusquedaTO();
        if(this.soc_autoridad_id.getValue()!=null){
            ParametrosDelegate pd = ParametrosDelegate.getInstance();
            Autoridades aut=(Autoridades)this.soc_autoridad_id.getValue();
            System.out.println("Autoridad seleccioanda por rol MADS:"+aut.getId());
            Autoridades au=pd.getAutoridad(aut.getId());
            crieterios.setAutoridad(au);
                  
        }
        if(this.soc_filtro_tipo_id.getValue()!=null){
            crieterios.setTipoIdentificacion(
                    (Lista)soc_filtro_tipo_id.getValue());
        }
        if(this.it_filtro_id.getValue()!=null &&
           this.it_filtro_id.getValue().toString().length()>0){
            crieterios.setNumeroIdentificacion(
                it_filtro_id.getValue().toString());       
        }
        if(this.it_filtro_nombres.getValue()!=null &&
           this.it_filtro_nombres.getValue().toString().length()>0){
            crieterios.setNombres(it_filtro_nombres.getValue().toString());       
        }
        if(this.it_filtro_apellidos.getValue()!=null &&
           this.it_filtro_apellidos.getValue().toString().length()>0){
            crieterios.setApellidos(
                this.it_filtro_apellidos.getValue().toString());       
        }
        if(this.soc_filtro_depto.getValue()!=null){
            crieterios.setDepartamento(
                (Divipola)this.soc_filtro_depto.getValue());
        }
        if(this.soc_filtro_mun.getValue()!=null){
            crieterios.setMunicipio((Divipola)this.soc_filtro_mun.getValue());
        }
        if(this.soc_tipo_usuario.getValue()!=null){
            crieterios.setNaturalezaUsuario((Lista)this.soc_tipo_usuario.getValue());
        }
        try{
            UsuarioConectadoTO usuarioConectado = 
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
            SeguridadDelegate seg = SeguridadDelegate.getInstance(); 
            PerfilVO pp=  seg.getPerfilUsuario(usuarioConectado.getUsuario().getCodigo());
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
            if (usuarioConectado.getUsuario().getAutoridadAmbiental()==null ||
                usuarioConectado.getUsuario().getAutoridadAmbiental().getId() == null ||
                usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue() ==0 ||
                usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue() == Constantes.IDEAM||
                pp.getCodigo()==ConstantesCalidad.PERFIL_CONSULTA){
            
                
                    this.listaUsuarios = uad.getAllUsuarios(crieterios);
            }else{  
                    
                    this.listaUsuarios = uad.getAllUsuarios(crieterios, usuarioConectado.getUsuario().
                                                                          getAutoridadAmbiental().
                                                                          getId().
                                                                          longValue());  
            }
 //Cabril 04/03/2015
            if (listaUsuarios != null) {
                
                Integer i=1;
                
                Iterator<UsuarioAguaTO> it = listaUsuarios.iterator();
                while (it.hasNext()) {
                    UsuarioAguaTO usu = it.next();
                    usu.setNum(i);
                    i++;
                } 
            }




            
            AdfFacesContext.getCurrentInstance().addPartialTarget(t_usuarios);
        }catch(IdeamException e){
            showMessage(e);
        }
    }
    
    public void soc_filtro_porAtoridad(ValueChangeEvent valueChangeEvent) throws IdeamException {
        Object autoridadSeleccionada = valueChangeEvent.getNewValue();
        buscar();
    }
    public void soc_filtro_depto_valueChangeListener(ValueChangeEvent valueChangeEvent) {
        Object depto = valueChangeEvent.getNewValue();
        this.listaMunicipios = new ArrayList();
        try{
            if(depto!=null){
                this.listaMunicipios = 
                    cargarDivipola(((Divipola)depto).getId());
            }
        }catch(IdeamException e){            
            showMessage(e);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(soc_filtro_mun);
     
    }


    public void setCmi_imprimir(RichCommandMenuItem cmi2) {
        this.cmi_imprimir = cmi2;
    }

    public RichCommandMenuItem getCmi_imprimir() {
        return cmi_imprimir;
    }

    public void cmi_imprimir_actionListener(ActionEvent actionEvent) {
        if (t_usuarios.getSelectedRowData()==null){
            showMessage(getText("seleccionar_registro"),
                        FacesMessage.SEVERITY_ERROR);
            return;
        }
        UsuarioAguaTO usuario = (UsuarioAguaTO)t_usuarios.getSelectedRowData();
        
        try {
            HashMap parametros = new HashMap();
            parametros.put("p_modulo","Registro de Usuarios del Agua");
            parametros.put("p_titulo","Información del Usuario ");            
            parametros.put("p_id_usuario",usuario.getCodigo());    
            if(usuario.getId_tipo_persona()==2||usuario.getId_tipo_persona()==136)
                 this.generateReport("usuarioAguaMunicipio.jasper", parametros, ReporteDelegate.PDF);
            else if(usuario.getId_tipo_persona()!=2 && usuario.getId_tipo_persona()!=136)
                 this.generateReport("usuarioAguaPredio.jasper", parametros, ReporteDelegate.PDF);
           
        } catch (IdeamException e) {
            showMessage(e);
        }        
    }
    
  public void cmi_puea_actionListener(ActionEvent actionEvent) {
   // System.out.println("Entre al PUEA");
      if (t_usuarios.getSelectedRowData()==null){
          showMessage(getText("seleccionar_registro"),
                      FacesMessage.SEVERITY_ERROR);
          return;
      }
      UsuarioAguaTO usuario = (UsuarioAguaTO)t_usuarios.getSelectedRowData();
      
      try {
          HashMap parametros = new HashMap();
          parametros.put("p_modulo","Registro PUEAA de Usuarios del Agua");
          parametros.put("p_titulo","Información del PUEAA ");            
          parametros.put("USU_ID", String.valueOf(usuario.getCodigo()));   
          parametros.put("contexto", FacesUtils.getRealPath("/imgs/imagenes_acuiferos")) ;
          /*if(usuario.getId_tipo_persona()==2||usuario.getId_tipo_persona()==136)
               this.generateReport("usuarioAguaMunicipio.jasper", parametros, ReporteDelegate.PDF);
          else if(usuario.getId_tipo_persona()!=2 && usuario.getId_tipo_persona()!=136)*/
               this.generateReport("HOJAPUEAA.jasper", parametros, ReporteDelegate.PDF);
         
      } catch (IdeamException e) {
          showMessage(e);
      }        
  }

    public List getListaNaturalezaUsuarios() {
        return listaNaturalezaUsuarios;
    }

    public void setListaNaturalezaUsuarios(List listaNaturalezaUsuarios) {
        this.listaNaturalezaUsuarios = listaNaturalezaUsuarios;
    }

    public void setSoc_tipo_usuario(RichSelectOneChoice soc1) {
        this.soc_tipo_usuario = soc1;
    }

    public RichSelectOneChoice getSoc_tipo_usuario() {
        return soc_tipo_usuario;
    }

    public void setSi4(UISelectItems si4) {
        this.si4 = si4;
    }

    public UISelectItems getSi4() {
        return si4;
    }

    public void setS2(RichSpacer s2) {
        this.s2 = s2;
    }

    public RichSpacer getS2() {
        return s2;
    }

    public Lista getNaturalezaSeleccionada() {
        return naturalezaSeleccionada;
    }

    public void setNaturalezaSeleccionada(Lista naturalezaSeleccionada) {
        this.naturalezaSeleccionada = naturalezaSeleccionada;
    }

    public void setPanelGroupLayout1(RichPanelGroupLayout panelGroupLayout1) {
        this.panelGroupLayout1 = panelGroupLayout1;
    }

    public RichPanelGroupLayout getPanelGroupLayout1() {
        return panelGroupLayout1;
    }

    public void setOutputText1titulo(RichOutputText outputText1) {
        this.outputText1titulo = outputText1;
    }

    public RichOutputText getOutputText1titulo() {
        return outputText1titulo;
    }

    public void setCmi_errores(RichCommandMenuItem cmi2) {
        this.cmi_errores = cmi2;
    }

    public RichCommandMenuItem getCmi_errores() {
        return cmi_errores;
    }

    public void setPgl2(RichPanelGroupLayout pgl2) {
        this.pgl2 = pgl2;
    }

    public RichPanelGroupLayout getPgl2() {
        return pgl2;
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

    public void setOt_errores(RichOutputText ot12) {
        this.ot_errores = ot12;
    }

    public RichOutputText getOt_errores() {
        return ot_errores;
    }

    public void cmi_errores_actionListener(ActionEvent actionEvent) {
        if (t_usuarios.getSelectedRowData()==null){
            showMessage(getText("seleccionar_registro"),
                        FacesMessage.SEVERITY_ERROR);
            return;
        }
        
        Object obj = t_usuarios.getSelectedRowData();
        
        UsuarioAgua usuario = null;
        if (obj instanceof UsuarioAguaTO) {
            UsuarioAguaTO usuarioTO = (UsuarioAguaTO)t_usuarios.getSelectedRowData();
            UsuariosAguaDelegate uad;

            try {
                uad = UsuariosAguaDelegate.getInstance();
                usuario = uad.getUsuarioAgua(usuarioTO.getCodigo());
            } catch (IdeamException e) {
            }
        }
        if (obj instanceof UsuarioAgua) {
            usuario = (UsuarioAgua)t_usuarios.getSelectedRowData();
            
        }
        if(usuario.isValid()){
            showMessage("El Usuario " + usuario.getNombre() + " " +
                        usuario.getApellido() + " no tiene errores de Validación", FacesMessage.SEVERITY_INFO);
            return;
        }
        this.it_errores.setValue(usuario.getErroresValidacion());
        this.ot_errores.setValue( "Erroes de Validacion para el Usuario " + 
                                  usuario.getNombre() + " " +
                                  usuario.getApellido());
        AdfFacesContext.getCurrentInstance().addPartialTarget(p_errores);
        showPopup(p_errores, true);
    }

    public void setVisiblePerfil(String visiblePerfil) {
        this.visiblePerfil = visiblePerfil;
    }

    public String getVisiblePerfil() {
        return visiblePerfil;
    }

    public void setListaAutoridades(List listaAutoridades) {
        this.listaAutoridades = listaAutoridades;
    }

    public List getListaAutoridades() {
        return listaAutoridades;
    }

    public void setSoc_autoridad_id(RichSelectOneChoice soc_autoridad_id) {
        this.soc_autoridad_id = soc_autoridad_id;
    }

    public RichSelectOneChoice getSoc_autoridad_id() {
        return soc_autoridad_id;
    }


    public void setSi1Autoridad(UISelectItems si1Autoridad) {
        this.si1Autoridad = si1Autoridad;
    }

    public UISelectItems getSi1Autoridad() {
        return si1Autoridad;
    }
}
