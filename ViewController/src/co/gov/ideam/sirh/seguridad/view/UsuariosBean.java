package co.gov.ideam.sirh.seguridad.view;


import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Categoria;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.view.ParametrosDelegate;
import co.gov.ideam.sirh.seguridad.model.PerfilVO;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.seguridad.model.UsuarioVO;
import co.gov.ideam.sirh.seguridad.view.util.UsuarioComparator;

import co.gov.ideam.sirh.usuarios.agua.model.PuntoVertimiento;
import co.gov.ideam.sirh.util.IdeamException;

import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;

import co.gov.ideam.sirh.view.util.EmailValidator;

import co.gov.ideam.sirh.view.util.LoginValidator;
import co.gov.ideam.sirh.view.util.NombreValidator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;

import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.model.SelectItem;

import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichMenu;
import oracle.adf.view.rich.component.rich.RichPopup;

import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanRadio;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.layout.RichToolbar;
import oracle.adf.view.rich.component.rich.nav.RichCommandMenuItem;
import oracle.adf.view.rich.component.rich.output.RichImage;
import oracle.adf.view.rich.component.rich.output.RichMessage;
import oracle.adf.view.rich.component.rich.output.RichOutputLabel;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;


import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.adf.view.rich.event.DialogEvent;

import org.apache.myfaces.trinidad.component.UIXGroup;
import org.apache.myfaces.trinidad.event.SelectionEvent;
/**
 * Bean encargado de administrar los usuarios registrados en el sisteama
 */
public class UsuariosBean extends StandarBean{
    private RichForm f2;
    private RichDocument d2;
    private RichPanelCollection pc1;
    
    private List listaUsuarios;
    private List listaPerfiles;
    private List listaTipodocumento;
    private List listaAutoridades;
    
    private RichTable t_usuarios;
    private RichMenu m_archivo;
    private RichCommandMenuItem cmi_adicionar;
    private RichCommandMenuItem cmi_editar;
    private RichMenu m_filtrar;
    private RichCommandMenuItem cmi_mostrar_filtros;
    private RichCommandMenuItem cmi_ejecutar_filtros;
    private RichCommandMenuItem cmi_limpiar_filtros;
    private RichCommandMenuItem cmi_imprimir;
    private RichPopup popupUsuario;
    private RichDialog d_detalle;
    
    private UsuarioVO usuarioSeleccionado;
    private RichPanelFormLayout pfl1;                
    private RichPanelStretchLayout psl1;    
    private RichInputText it_login;
    private RichInputText it_nombres;
    private RichInputText it_apellidos;
    private RichInputText it_email;
    
    
   private RichInputText it_nrodocumento;
    
    private RichSelectBooleanRadio sbr_estado_activo;
    private RichSelectBooleanRadio sbr_estado_inactivo;
    private RichImage i2;
    private UISelectItems si2;
    
    private UISelectItems si21;
    private UIXGroup g1;
    private RichPopup popup_borrar;
    private RichPanelStretchLayout psl2;
    private RichPanelFormLayout pfl2;
    private UISelectItems si1;
    private RichImage i3;
    private RichDialog d_borrar;
    private RichOutputText ot_confirmacion_borrar;

    private RichInputText it_filtro_login;
    private RichInputText it_filtro_nombres;
    private RichInputText it_filtro_apellidos;
    private RichInputText it_filtro_email;
    private RichSelectBooleanRadio sbr_filtro_estado_activo;
    private RichSelectBooleanRadio sbr_filtro_estado_inactivo;    
    private RichInputDate id_ingreso_exitoso_inicial;
    private RichInputDate id_ingreso_exitoso_final;
    private RichInputDate id_ingreso_no_exitoso_inicial;
    private RichInputDate id_ingreso_no_exitoso_final;
   
    private RichToolbar t1;
    private RichOutputText ot_filtrando;
    private RichTable t2;
    private RichSpacer s1;
    private RichOutputLabel ol1;
    private RichMessage m2;
    private RichCommandMenuItem cmi_borrar;
    private RichSelectOneChoice soc_autoridad;    

    private RichSelectOneChoice soc_nat_tipo_id;
    
    
    public UsuariosBean(){
        FacesUtils.setActiveBean("usuarios",
                                 "Administrar Usuarios",
                                 SeguridadDelegate.class);
        load();
    }
    
    public void load(){        
        try {
            UsuarioConectadoTO usuarioConectado = 
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
            
            SeguridadDelegate sd = SeguridadDelegate.getInstance();      
            
            
            
            setListaUsuarios(sd.getUsuarios(usuarioConectado.getUsuario().getAutoridadAmbiental()));
            setUsuarioSeleccionado(null);
            this.listaPerfiles = sd.getAllPerfiles();
            this.listaTipodocumento= this.cargarParametro(Categoria.TIPO_DOCUMENTO);
            this.listaAutoridades = new ArrayList();
            if(usuarioConectado.getUsuario().getAutoridadAmbiental()!= null &&
               usuarioConectado.getUsuario().getAutoridadAmbiental().getId().intValue()==1){                
                ParametrosDelegate pd = ParametrosDelegate.getInstance();
                List lista = pd.getAllAutoridades();
                Iterator it = lista.iterator();
                while(it.hasNext()){
                    Autoridades autoridad = (Autoridades)it.next();
                    SelectItem item = new SelectItem(autoridad, autoridad.getNombre());
                    this.listaAutoridades.add(item);
                }
            }else{
                Autoridades autoridad = usuarioConectado.getUsuario().getAutoridadAmbiental();
                SelectItem item = new SelectItem(autoridad, autoridad.getNombre());
                this.listaAutoridades.add(item);                
            }
        } catch (IdeamException e) {
            super.showMessage(e);
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


    public void setSoc_nat_tipo_id(RichSelectOneChoice soc1) {
        this.soc_nat_tipo_id = soc1;
    }

    public RichSelectOneChoice getSoc_nat_tipo_id() {
        return soc_nat_tipo_id;
    }

    public void setPc1(RichPanelCollection pc1) {
        this.pc1 = pc1;
    }

    public RichPanelCollection getPc1() {
        return pc1;
    }

    public List getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public void setT_usuarios(RichTable t1) {
        this.t_usuarios = t1;
    }

    public RichTable getT_usuarios() {
        return t_usuarios;
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

    public void setCmi_editar(RichCommandMenuItem cmi2) {
        this.cmi_editar = cmi2;
    }

    public RichCommandMenuItem getCmi_editar() {
        return cmi_editar;
    }


    public void setM_filtrar(RichMenu m2) {
        this.m_filtrar = m2;
    }

    public RichMenu getM_filtrar() {
        return m_filtrar;
    }

    public void setCmi_mostrar_filtros(RichCommandMenuItem cmi2) {
        this.cmi_mostrar_filtros = cmi2;
    }

    public RichCommandMenuItem getCmi_mostrar_filtros() {
        return cmi_mostrar_filtros;
    }

    public void setCmi_ejecutar_filtros(RichCommandMenuItem cmi2) {
        this.cmi_ejecutar_filtros = cmi2;
    }

    public RichCommandMenuItem getCmi_ejecutar_filtros() {
        return cmi_ejecutar_filtros;
    }

    public void setCmi_limpiar_filtros(RichCommandMenuItem cmi2) {
        this.cmi_limpiar_filtros = cmi2;
    }

    public RichCommandMenuItem getCmi_limpiar_filtros() {
        return cmi_limpiar_filtros;
    }

    public void setCmi_imprimir(RichCommandMenuItem cmi2) {
        this.cmi_imprimir = cmi2;
    }

    public RichCommandMenuItem getCmi_imprimir() {
        return cmi_imprimir;
    }

    /**
     * @param actionEvent
     */
    public void cmi_mostrar_filtros_actionListener(ActionEvent actionEvent) {        
        if (this.cmi_mostrar_filtros.getText().startsWith(this.getText("menu_filtrar_mostrar"))){
            this.estadoFiltros(this.t_usuarios, true, this.cmi_mostrar_filtros);
        }else{
            this.estadoFiltros(this.t_usuarios, false, this.cmi_mostrar_filtros);
        }
    }

    /**
     * @param actionEvent
     */
    public void cmi_limpiar_filtros_actionListener(ActionEvent actionEvent) {
        this.limpiarFiltros(t_usuarios);
        this.load();
        this.ot_filtrando.setValue("");
        this.ot_filtrando.setVisible(false);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.t_usuarios);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.ot_filtrando);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.pc1);
    }

    public void setPopupUsuario(RichPopup p1) {
        this.popupUsuario = p1;
    }

    public RichPopup getPopupUsuario() {
        return popupUsuario;
    }

    public void setD_detalle(RichDialog d1) {
        this.d_detalle = d1;
    }

    public RichDialog getD_detalle() {
        return d_detalle;
    }

    /**
     * @param selectionEvent
     */
    public void t_usuarios_selectionListener(SelectionEvent selectionEvent) {
        UsuarioVO usuario = (UsuarioVO)t_usuarios.getSelectedRowData();        
        setUsuarioSeleccionado(usuario);
        FacesUtils.setInSession("usuarioVOSeleccionado", usuario);
    }


    public void cmi_editar_actionListener(ActionEvent actionEvent) {
        if (getUsuarioSeleccionado() == null){
            this.showMessage(StandarBean.getText("seleccionar_registro"));
            return;
        }
        // Actualizar los campos de la ventana segun la informacion del usuario seleccionado                
        this.showPopup(this.popupUsuario, true);   
        this.mostrarUsuario();               
    }

    public void setPfl1(RichPanelFormLayout pfl1) {
        this.pfl1 = pfl1;
    }

    public RichPanelFormLayout getPfl1() {
        return pfl1;
    }

    public UsuarioVO getUsuarioSeleccionado() {
        return this.usuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(UsuarioVO usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }
    

    public void setIt_login(RichInputText it1) {
        this.it_login = it1;
    }

    public RichInputText getIt_login() {
        return it_login;
    }

    public void setIt_nombres(RichInputText it2) {
        this.it_nombres = it2;
    }

    public RichInputText getIt_nombres() {
        return it_nombres;
    }

    public void setIt_apellidos(RichInputText it3) {
        this.it_apellidos = it3;
    }

    public RichInputText getIt_apellidos() {
        return it_apellidos;
    }

    public void setIt_email(RichInputText it4) {
        this.it_email = it4;
    }

    public RichInputText getIt_email() {
        return it_email;
    }

    public void setSbr_estado_activo(RichSelectBooleanRadio sbr1) {
        this.sbr_estado_activo = sbr1;
    }

    public RichSelectBooleanRadio getSbr_estado_activo() {
        return sbr_estado_activo;
    }

    public void setSbr_estado_inactivo(RichSelectBooleanRadio sbr2) {
        this.sbr_estado_inactivo = sbr2;
    }

    public RichSelectBooleanRadio getSbr_estado_inactivo() {
        return sbr_estado_inactivo;
    }


    public void setI2(RichImage i2) {
        this.i2 = i2;
    }

    public RichImage getI2() {
        return i2;
    }


    public List getListaPerfiles() {
        return listaPerfiles;
    }

    public void setListaPerfiles(List listaPerfiles) {
        this.listaPerfiles = listaPerfiles;
    }

    public void setSi2(UISelectItems si2) {
        this.si2 = si2;
    }

    public UISelectItems getSi2() {
        return si2;
    }
    /**
     * muestra la informacion del usuario seleccionado en los campos de la
     * ventana popup
     */
    private void mostrarUsuario(){     
        this.it_login.setValue(this.usuarioSeleccionado.getLogin());
        this.it_nombres.setValue(this.usuarioSeleccionado.getNombres());
        this.it_apellidos.setValue(this.usuarioSeleccionado.getApellidos());
        this.it_nrodocumento.setValue(this.usuarioSeleccionado.getNroDocumento());
        this.it_email.setValue(this.usuarioSeleccionado.getEmail());
        this.sbr_estado_activo.setSelected(this.usuarioSeleccionado.isActivo()==1);
        this.sbr_estado_inactivo.setSelected(this.usuarioSeleccionado.isActivo()!=1);
        if(this.usuarioSeleccionado.getAutoridadAmbiental()!=null){
            this.soc_autoridad.setValue(this.usuarioSeleccionado.getAutoridadAmbiental());
        }
      if(this.usuarioSeleccionado.getListaTipoDoc()!=null){
                this.soc_nat_tipo_id.setValue(this.usuarioSeleccionado.getListaTipoDoc());
      }
        
        this.it_login.setReadOnly(true);
        try {
            SeguridadDelegate sd = SeguridadDelegate.getInstance();
            List listaPerfilesUsuario = sd.getPerfiles(this.usuarioSeleccionado);
            if (listaPerfiles!=null){
                Iterator it = listaPerfiles.iterator();
                while(it.hasNext()){
                    PerfilVO p = (PerfilVO)it.next();
                    if (listaPerfilesUsuario!=null &&
                        listaPerfilesUsuario.contains(p)){
                        p.setSelected(true);
                    }else{
                        p.setSelected(false);
                    }
                }
            }
        } catch (IdeamException e) {
            super.showMessage(e);
        }        
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.popupUsuario);
    }
    /**
     * muestfa la ventana popup para adicionar los campos de un nuevo usuario
     * @param actionEvent
     */
    public void cmi_adicionar_actionListener(ActionEvent actionEvent) {
        this.it_login.setValue("");
        this.it_nombres.setValue("");
        this.it_apellidos.setValue("");
        this.it_email.setValue("");
        this.it_nrodocumento.setValue("");
        this.soc_nat_tipo_id.setValue(getListaTipodocumento());
        this.sbr_estado_activo.setSelected(true);
        this.it_login.setReadOnly(false);
        String titulo =  this.getText("usuarios_adicionar");
        this.d_detalle.setTitle(titulo);
        if (this.listaPerfiles!=null){
            Iterator it= listaPerfiles.iterator();
            while(it.hasNext()){
                PerfilVO p = (PerfilVO)it.next();
                p.setSelected(false);
            }
        }        
        UsuarioConectadoTO usuarioConectado = 
            (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
        if(usuarioConectado.getUsuario().getAutoridadAmbiental()!=null){
            this.soc_autoridad.setValue(usuarioConectado.getUsuario().getAutoridadAmbiental());
        }
        this.mostrarOcultarPopup(this.popupUsuario, true);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.t2);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.popupUsuario);        
    }

    public void d_detalle_dialogListener(DialogEvent dialogEvent) {
        String action = dialogEvent.getOutcome().name();      
        
        this.it_nombres.setValid(false);
        NombreValidator nombreValidator = new NombreValidator();
        nombreValidator.validate(
            FacesContext.getCurrentInstance(),
            this.it_nombres,
            this.it_nombres.getValue());
        if (!nombreValidator.isValid()){
            return;
        }
        
        this.it_apellidos.setValid(false);
        NombreValidator apellidoValidator = new NombreValidator();
        apellidoValidator.validate(
            FacesContext.getCurrentInstance(),
            this.it_apellidos,
            this.it_apellidos.getValue());
        if (!apellidoValidator.isValid()){
            return;
        }
        
        this.it_email.setValid(false);
        EmailValidator emailValidator = new EmailValidator();
        emailValidator.validate(
            FacesContext.getCurrentInstance(),
            this.it_email,
            this.it_email.getValue());
        if (!emailValidator.isValid()){
            return;
        }        
        if(this.soc_autoridad.getValue()==null){
            this.showMessage("obligatorio",
                             FacesMessage.SEVERITY_ERROR,
                             soc_autoridad);
            return;
        }
        try {
            SeguridadDelegate sd = SeguridadDelegate.getInstance();
            
            // Si es adicionar Validar si existe otro usuario con el mismo login
            if (!this.it_login.isReadOnly()){
                LoginValidator loginValidator = new LoginValidator();
                loginValidator.validate(
                    FacesContext.getCurrentInstance(),
                    this.it_login,
                    this.it_login.getValue());
                if (!loginValidator.isValid()){
                    return;
                }                
                
                String loginNuevo = this.it_login.getValue().toString();            
                UsuarioVO usuarioExiste = sd.getUsuario(loginNuevo);
                if (usuarioExiste!=null){
                    this.it_login.setValid(false);
                    this.showMessage("usuarios_login_existe",
                                     FacesMessage.SEVERITY_ERROR,
                                     this.it_login);
                    return;
                }                
            }
            
            // Validar que la direccion de correo no se encuentre asociada a otro usuario
            String emailNuevo = this.it_email.getValue().toString();
            UsuarioVO usuarioExiste = sd.getUsuarioByEmail(emailNuevo);
            if (usuarioExiste!=null){
                // Si es adicionar es un error, si es modificar validar que sea
                // de otro usuario
                if (!this.it_login.isReadOnly()){
                    this.it_email.setValid(false);
                    this.showMessage("usuarios_email_existe",
                                     FacesMessage.SEVERITY_ERROR,
                                     this.it_email);
                    return;
                }else{
                    if (usuarioExiste.getCodigo() != 
                        usuarioSeleccionado.getCodigo()){
                        this.it_email.setValid(false);
                        this.showMessage("usuarios_email_existe",
                                         FacesMessage.SEVERITY_ERROR,
                                         this.it_email);
                        return;
                    }
                }
            }     
            
            UsuarioVO usuarioActualizar = null;
            
            // Actualizar el usuario
            if (this.it_login.isReadOnly()){
                usuarioActualizar = usuarioSeleccionado;
            }else{
                usuarioActualizar = new UsuarioVO();
                usuarioActualizar.setLogin(this.it_login.getValue().toString());
            }
            usuarioActualizar.setNroDocumento(this.it_nrodocumento.getValue().toString());
            usuarioActualizar.setListaTipoDoc((Lista)this.soc_nat_tipo_id.getValue());
            usuarioActualizar.setNombres(this.it_nombres.getValue().toString());
            usuarioActualizar.setApellidos(this.it_apellidos.getValue().toString());
            usuarioActualizar.setEmail(this.it_email.getValue().toString());                                        
            usuarioActualizar.setActivo( this.sbr_estado_activo.isSelected() ? 1 : 2  );                
            usuarioActualizar.setAutoridadAmbiental((Autoridades)this.soc_autoridad.getValue());
                        
            boolean existenPerfiles = false;
            if (listaPerfiles!=null){
                Iterator it = listaPerfiles.iterator();
                while(it.hasNext()){
                    PerfilVO p = (PerfilVO)it.next();
                    if (p.isSelected()){
                        usuarioActualizar.addPerfil(p);
                        if (p.isActivo()){
                            existenPerfiles = true;
                        }
                    }
                }
            }
            
            if (!existenPerfiles){
                this.it_email.setValid(false);
                this.showMessage("usaurios_no_perfiles",
                                 FacesMessage.SEVERITY_ERROR,
                                 this.t2);
                return;
            }
            sd.updateUser(usuarioActualizar);
            t_usuarios.setSelectedRowKeys(null);
            this.load();
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.t_usuarios);
        } catch (IdeamException e) {
            super.showMessage(e);
        }        
/*        
        //prueba de concepto usuarios del agua
        try{
            UsuarioConectadoTO usuarioConectado = 
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
            uad.updateUsuarioAguaTest(usuarioConectado.getUsuario().getAutoridadAmbiental());
        }catch(IdeamException e){
            super.showMessage(e);
        }
*/
    }

    public void cmi_borrar_actionListener(ActionEvent actionEvent) {
        if (getUsuarioSeleccionado() == null){
            this.showMessage(StandarBean.getText("seleccionar_registro"));
            return;
        }
        String mensaje = StandarBean.getText("usuarios_borrar") + 
                         getUsuarioSeleccionado().getNombres() + " " +
                         getUsuarioSeleccionado().getApellidos() + " ?";
        this.ot_confirmacion_borrar.setValue(mensaje);
        this.mostrarOcultarPopup(this.popup_borrar, true);   
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.popup_borrar);
    }

    public void setG1(UIXGroup g1) {
        this.g1 = g1;
    }

    public UIXGroup getG1() {
        return g1;
    }

    public void setPopup_borrar(RichPopup p1) {
        this.popup_borrar = p1;
    }

    public RichPopup getPopup_borrar() {
        return popup_borrar;
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

    public void setI3(RichImage i3) {
        this.i3 = i3;
    }

    public RichImage getI3() {
        return i3;
    }

    public void setD_borrar(RichDialog d3) {
        this.d_borrar = d3;
    }

    public RichDialog getD_borrar() {
        return d_borrar;
    }

    public void setOt_confirmacion_borrar(RichOutputText ot14) {
        this.ot_confirmacion_borrar = ot14;
    }

    public RichOutputText getOt_confirmacion_borrar() {
        return ot_confirmacion_borrar;
    }

    public void cmi_imprimir_actionListener(ActionEvent actionEvent) {        
        try {
            String atributos[] = {"login",
                                  "nombres",
                                  "apellidos",
                                  "email",
                                  "activo",
                                  "ultimoIngresoExitoso",
                                  "ultimoIngresoNoExitoso"};
            UsuariosReport reporte = new UsuariosReport();
            Collections.sort(listaUsuarios, new UsuarioComparator());
            this.printTable(this.t_usuarios, atributos, listaUsuarios,
                            reporte, true);
        } catch (IdeamException e) {
            this.showMessage(e);
        }
    }

    public void cmi_ejecutar_filtros_actionListener(ActionEvent actionEvent) {
        String texto = getText("filtrar");
        UsuarioVO busqueda = new UsuarioVO();
        boolean useEstado = false;
        Calendar ingresoExitosoFinal = null;
        Calendar ingresoNoExitosoFinal = null;
        boolean existenCriterios = false;
        // Si existen rangos de fechas validarlos
        if (id_ingreso_exitoso_inicial.getValue()!=null &&
            id_ingreso_exitoso_final.getValue()!=null){ 
            Date fecha1 = (Date)id_ingreso_exitoso_inicial.getValue();
            Date fecha2 = (Date)id_ingreso_exitoso_final.getValue();
            
            if (fecha1.after(fecha2)){                
                this.showMessage("error_rango_fechas",
                                 FacesMessage.SEVERITY_ERROR,
                                 this.id_ingreso_exitoso_final);
                return;
            }
        }

        if (id_ingreso_no_exitoso_inicial.getValue()!=null &&
            id_ingreso_no_exitoso_final.getValue()!=null){ 
            Date fecha1 = (Date)id_ingreso_no_exitoso_inicial.getValue();
            Date fecha2 = (Date)id_ingreso_no_exitoso_final.getValue();
            
            if (fecha1.after(fecha2)){
                this.showMessage("error_rango_fechas",
                                 FacesMessage.SEVERITY_ERROR,
                                 this.id_ingreso_no_exitoso_final);
                return;
            }
        }
        
        if (it_filtro_login.getValue()!=null &&
            it_filtro_login.getValue().toString().length() > 0 &&
            !it_filtro_login.getValue().toString().equalsIgnoreCase(texto)){
            busqueda.setLogin( it_filtro_login.getValue().toString() ); 
            existenCriterios = true;
        }else{
            busqueda.setLogin(null);
        }
        
        if (it_filtro_nombres.getValue()!=null &&
            it_filtro_nombres.getValue().toString().length() > 0 &&
            !it_filtro_nombres.getValue().toString().equalsIgnoreCase(texto)){
            busqueda.setNombres( it_filtro_nombres.getValue().toString() );        
            existenCriterios = true;
        }else{
            busqueda.setNombres(null);
        }
        
        if (it_filtro_apellidos.getValue()!=null &&
            it_filtro_apellidos.getValue().toString().length() > 0 &&
            !it_filtro_apellidos.getValue().toString().equalsIgnoreCase(texto)){
            busqueda.setApellidos( it_filtro_apellidos.getValue().toString() );    
            existenCriterios = true;
        }else{
            busqueda.setApellidos(null);
        }

        if (it_filtro_email.getValue()!=null &&
            it_filtro_email.getValue().toString().length() > 0 &&
            !it_filtro_email.getValue().toString().equalsIgnoreCase(texto)){
            busqueda.setEmail( it_filtro_email.getValue().toString() );     
            existenCriterios = true;
        }else{
            busqueda.setEmail(null);
        }
        
        if (this.sbr_filtro_estado_activo.isSelected() ||
            this.sbr_filtro_estado_inactivo.isSelected()){
            useEstado = true;
            busqueda.setActivo(this.sbr_filtro_estado_activo.isSelected() ?1 :2);
            existenCriterios = true;
        }
        
        if (this.id_ingreso_exitoso_inicial.getValue()!=null){
            Date fecha1 = (Date)id_ingreso_exitoso_inicial.getValue();
            Calendar exitosoInicial = GregorianCalendar.getInstance();
            exitosoInicial.setTime(fecha1);
            busqueda.setUltimoIngresoExitoso(exitosoInicial);            
            existenCriterios = true;
        }
        
        if (this.id_ingreso_exitoso_final.getValue()!=null){
            Date fecha1 = (Date)id_ingreso_exitoso_final.getValue();
            ingresoExitosoFinal = GregorianCalendar.getInstance();
            ingresoExitosoFinal.setTime(fecha1);
            existenCriterios = true;
        }
        
        if (this.id_ingreso_no_exitoso_inicial.getValue()!=null){
            Date fecha1 = (Date)id_ingreso_no_exitoso_inicial.getValue();
            Calendar noExitosoInicial = GregorianCalendar.getInstance();
            noExitosoInicial.setTime(fecha1);
            busqueda.setUltimoIngresoNoExitoso(noExitosoInicial);   
            existenCriterios = true;
        }
        
        if (this.id_ingreso_no_exitoso_final.getValue()!=null){
            Date fecha1 = (Date)id_ingreso_no_exitoso_final.getValue();
            ingresoNoExitosoFinal = GregorianCalendar.getInstance();
            ingresoNoExitosoFinal.setTime(fecha1);
            existenCriterios = true;
        }        
        if (!existenCriterios){
            this.showMessage("no_existen_criterios",
                             FacesMessage.SEVERITY_ERROR,
                             this.t_usuarios);
            return;
        }
        try{
            SeguridadDelegate sd = SeguridadDelegate.getInstance();            
            List listaFiltro = sd.getUsuarios(busqueda, 
                                              useEstado, 
                                              ingresoExitosoFinal, 
                                              ingresoNoExitosoFinal);
            this.listaUsuarios = listaFiltro;
            this.ot_filtrando.setValue(getText("mostrando_filtro"));
            this.ot_filtrando.setVisible(true);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.t_usuarios);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.ot_filtrando);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.pc1);
        }catch(IdeamException e){
            this.showMessage(e);
        }
    }

    public RichInputText getIt_filtro_login() {
        return it_filtro_login;
    }

    public void setIt_filtro_login(RichInputText it_filtro_login) {
        this.it_filtro_login = it_filtro_login;
    }

    public void setT1(RichToolbar t1) {
        this.t1 = t1;
    }

    public RichToolbar getT1() {
        return t1;
    }

    public void setOt_filtrando(RichOutputText ot14) {
        this.ot_filtrando = ot14;
    }

    public RichOutputText getOt_filtrando() {
        return ot_filtrando;
    }

    public RichInputText getIt_filtro_nombres() {
        return it_filtro_nombres;
    }

    public void setIt_filtro_nombres(RichInputText it_filtro_nombres) {
        this.it_filtro_nombres = it_filtro_nombres;
    }

    public RichInputText getIt_filtro_apellidos() {
        return it_filtro_apellidos;
    }

    public void setIt_filtro_apellidos(RichInputText it_filtro_apellidos) {
        this.it_filtro_apellidos = it_filtro_apellidos;
    }

    public RichInputText getIt_filtro_email() {
        return it_filtro_email;
    }

    public void setIt_filtro_email(RichInputText it_filtro_email) {
        this.it_filtro_email = it_filtro_email;
    }

    public RichSelectBooleanRadio getSbr_filtro_estado_activo() {
        return sbr_filtro_estado_activo;
    }

    public void setSbr_filtro_estado_activo(RichSelectBooleanRadio sbr_filtro_estado_activo) {
        this.sbr_filtro_estado_activo = sbr_filtro_estado_activo;
    }

    public RichSelectBooleanRadio getSbr_filtro_estado_inactivo() {
        return sbr_filtro_estado_inactivo;
    }

    public void setSbr_filtro_estado_inactivo(RichSelectBooleanRadio sbr_filtro_estado_inactivo) {
        this.sbr_filtro_estado_inactivo = sbr_filtro_estado_inactivo;
    }

    public RichInputDate getId_ingreso_exitoso_inicial() {
        return id_ingreso_exitoso_inicial;
    }

    public void setId_ingreso_exitoso_inicial(RichInputDate id_ingreso_exitoso_inicial) {
        this.id_ingreso_exitoso_inicial = id_ingreso_exitoso_inicial;
    }

    public RichInputDate getId_ingreso_exitoso_final() {
        return id_ingreso_exitoso_final;
    }

    public void setId_ingreso_exitoso_final(RichInputDate id_ingreso_exitoso_final) {
        this.id_ingreso_exitoso_final = id_ingreso_exitoso_final;
    }

    public RichInputDate getId_ingreso_no_exitoso_inicial() {
        return id_ingreso_no_exitoso_inicial;
    }

    public void setId_ingreso_no_exitoso_inicial(RichInputDate id_ingreso_no_exitoso_inicial) {
        this.id_ingreso_no_exitoso_inicial = id_ingreso_no_exitoso_inicial;
    }

    public RichInputDate getId_ingreso_no_exitoso_final() {
        return id_ingreso_no_exitoso_final;
    }

    public void setId_ingreso_no_exitoso_final(RichInputDate id_ingreso_no_exitoso_final) {
        this.id_ingreso_no_exitoso_final = id_ingreso_no_exitoso_final;
    }

    public void setT2(RichTable t2) {
        this.t2 = t2;
    }

    public RichTable getT2() {
        return t2;
    }

    public void setS1(RichSpacer s1) {
        this.s1 = s1;
    }

    public RichSpacer getS1() {
        return s1;
    }

    public void setOl1(RichOutputLabel ol1) {
        this.ol1 = ol1;
    }

    public RichOutputLabel getOl1() {
        return ol1;
    }

    public void setM2(RichMessage m2) {
        this.m2 = m2;
    }

    public RichMessage getM2() {
        return m2;
    }

    public void setCmi_borrar(RichCommandMenuItem cmi2) {
        this.cmi_borrar = cmi2;
    }

    public RichCommandMenuItem getCmi_borrar() {
        return cmi_borrar;
    }

    public void d_borrar_dialogListener(DialogEvent dialogEvent) {
        try{
            SeguridadDelegate sd = SeguridadDelegate.getInstance();
            sd.deleteUser(this.getUsuarioSeleccionado());
            t_usuarios.setSelectedRowKeys(null);
            this.load();
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.t_usuarios);            
        }catch(IdeamException e){
            showMessage(e);
        }
    }

    public List getListaAutoridades() {
        return listaAutoridades;
    }

    public void setListaAutoridades(List listaAutoridades) {
        this.listaAutoridades = listaAutoridades;
    }

    public void setSoc_autoridad(RichSelectOneChoice soc1) {
        this.soc_autoridad = soc1;
    }

    public RichSelectOneChoice getSoc_autoridad() {
        return soc_autoridad;
    }

    public void setSi1(UISelectItems si1) {
        this.si1 = si1;
    }

    public UISelectItems getSi1() {
        return si1;
    }

    public void setPsl1(RichPanelStretchLayout psl1) {
        this.psl1 = psl1;
    }

    public RichPanelStretchLayout getPsl1() {
        return psl1;
    }

    public void setIt_nrodocumento(RichInputText it_nrodocumento) {
        this.it_nrodocumento = it_nrodocumento;
    }

    public RichInputText getIt_nrodocumento() {
        return it_nrodocumento;
    }

    public void setListaTipodocumento(List listaTipodocumento) {
        this.listaTipodocumento = listaTipodocumento;
    }

    public List getListaTipodocumento() {
        return listaTipodocumento;
    }

    public void setSi21(UISelectItems si21) {
        this.si21 = si21;
    }

    public UISelectItems getSi21() {
        return si21;
    }
}
