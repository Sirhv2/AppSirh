package co.gov.ideam.sirh.seguridad.view;

import co.gov.ideam.sirh.seguridad.model.MenuVO;
import co.gov.ideam.sirh.seguridad.model.OpcionVO;
import co.gov.ideam.sirh.seguridad.model.PerfilVO;
import co.gov.ideam.sirh.seguridad.model.UsuarioVO;
import co.gov.ideam.sirh.seguridad.view.util.PerfilComparator;
import co.gov.ideam.sirh.seguridad.view.util.UsuarioComparator;
import co.gov.ideam.sirh.util.IdeamException;

import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;

import co.gov.ideam.sirh.view.util.NombreValidator;
import co.gov.ideam.sirh.view.util.SpecialTreeModel;
import co.gov.ideam.sirh.view.util.TreeNode;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichMenu;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichColumn;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.data.RichTreeTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanRadio;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.layout.RichToolbar;
import oracle.adf.view.rich.component.rich.nav.RichCommandMenuItem;
import oracle.adf.view.rich.component.rich.output.RichImage;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;

import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.adf.view.rich.event.DialogEvent;

import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.LazyDynaBean;
import org.apache.commons.beanutils.LazyDynaClass;
import org.apache.commons.beanutils.MutableDynaClass;
import org.apache.myfaces.trinidad.component.UIXGroup;
import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.model.TreeModel;
import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;
/**
 * Bean encargado de administrar los perfiles que agrupan menu, opciones
 * y se asocian a los usuarios para otorgales privelegios de acceso u uso 
 * del sistema
 */
public class PerfilesBean extends StandarBean{
    private TreeModel perfilesTreeModel;
    private RichForm f2;
    private RichDocument d2;
    private RichPanelCollection pc1;
    
    private List listaPerfiles;
    private List listaMenu;
    private List listaPermisosPerfil;
    private List listaOpcionesPerfil;
    private TreeNode root;
    private List perfilesSeleccionados;
    private List opcionesSeleccionadas;
    
    
    private PerfilVO perfilSeleccionado;
    private RichTable t_perfiles;
    private RichMenu m_archivo;
    private RichCommandMenuItem cmi_adicionar;
    private RichCommandMenuItem cmi_editar;
    private RichCommandMenuItem cmi_borrar;
    private RichCommandMenuItem cmi_imprimir;
    private RichPopup popupPerfil;
    private UIXGroup g1;
    private RichPopup popup_borrar;
    private RichDialog d_borrar;
    private RichDialog d_perfil;
    private RichPanelStretchLayout psl1;
    private RichImage i2;
    private RichPanelFormLayout pfl1;
    private RichInputText it_nombre;
    private RichSelectBooleanRadio sbr_activo;
    private RichSelectBooleanRadio sbr_inactivo;
    private RichPanelGroupLayout pgl5;
    private RichSpacer s1;
    private RichPanelStretchLayout psl2;
    private RichPanelCollection pc2;
    private RichMenu m2;
    private RichPanelSplitter ps1;
    private RichTreeTable tt_permisos;
    private RichCommandMenuItem cmi_permisos_guardar;
    private RichOutputText ot7;
    private RichToolbar t1;
    private RichOutputText ot_nombre_perfil;
    private RichOutputText ot_confirmar_borrar;
    private RichPopup popup_borrar_2;
    private RichDialog d_borrar_2;
    private RichOutputText ot_segunda_confirmacion;
    private RichSelectBooleanCheckbox sbc_core;
    private RichSpacer s2;
    private RichPanelStretchLayout psl3;

    public PerfilesBean(){
        FacesUtils.setActiveBean("perfiles",
                                 "Administrar Perfiles",
                                 SeguridadDelegate.class);
        this.load();      
        this.loadPerfiles();
    }
    
    public void load(){        
        try {
            SeguridadDelegate sd = SeguridadDelegate.getInstance();
            setListaPerfiles( sd.getAllPerfiles() );
        } catch (IdeamException e) {
            super.showMessage(e);
        }
    }
    
    private void loadPerfiles(){
        root = new TreeNode(getText("login_titulo_2"), "-");
        MenuVO menuRoot = new MenuVO();
        menuRoot.setCodigo(-99999);
        menuRoot.setSelected(false);
        root.setData(menuRoot);
        try {
            SeguridadDelegate sd = SeguridadDelegate.getInstance();
            setListaPerfiles( sd.getAllPerfiles() );
            listaMenu = sd.getAllMenu();
            Iterator it = listaMenu.iterator();
            while(it.hasNext()){
                MenuVO menu = (MenuVO)it.next();                         
                if (this.listaPermisosPerfil!=null &&
                    this.listaPermisosPerfil.contains(menu)){
                    menu.setSelected(true);        
                }else{
                    menu.setSelected(false);        
                }
                TreeNode nodoPrincipal = new TreeNode(menu.getNombre(), "Menu");                   
                nodoPrincipal.setData(menu);
                this.processNode(nodoPrincipal, menu, 1);
                root.getChildren().add(nodoPrincipal);
            }
        } catch (IdeamException e) {
            super.showMessage(e);
        }
        //List nodes = new ArrayList();
        //nodes.add(root);

        perfilesTreeModel = new SpecialTreeModel(root, "children");             
    }
    
    private void processNode(TreeNode node, MenuVO menu, int level){        
        int newLevel = level + 1;
        if (menu!=null && menu.getMenuVOList()!=null && menu.getMenuVOList().size()>0){
            Iterator it = menu.getMenuVOList().iterator();
            while(it.hasNext()){
                MenuVO m = (MenuVO)it.next();       
                if (this.listaPermisosPerfil!=null &&
                    this.listaPermisosPerfil.contains(m)){
                    m.setSelected(true);        
                }else{
                    m.setSelected(false);        
                }                
                TreeNode hijo = new TreeNode(m.getNombre(), "Submenu");
                hijo.setData(m);
                node.getChildren().add(hijo);                
                this.processNode(hijo, m, newLevel);
            }
        }
        if (menu.getOpcionVOList()!=null && menu.getOpcionVOList().size()>0){
            Iterator it = menu.getOpcionVOList().iterator();
            while(it.hasNext()){
                OpcionVO opcion = (OpcionVO)it.next();                
                if (this.listaOpcionesPerfil!=null &&
                    this.listaOpcionesPerfil.contains(opcion)){                    
                    opcion.setSelected(true);
                }else{                    
                    opcion.setSelected(false);
                }                
                TreeNode hijo = new TreeNode(opcion.getOpcNombre(), "Bot\u00f3n");
                hijo.setData(opcion);
                node.getChildren().add(hijo);                
            }
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

    public void setPc1(RichPanelCollection pc1) {
        this.pc1 = pc1;
    }

    public RichPanelCollection getPc1() {
        return pc1;
    }

    public List getListaPerfiles() {
        return listaPerfiles;
    }

    public void setListaPerfiles(List listaPerfiles) {
        this.listaPerfiles = listaPerfiles;
    }

    public PerfilVO getPerfilSeleccionado() {
        return perfilSeleccionado;
    }

    public void setPerfilSeleccionado(PerfilVO perfilSeleccionado) {
        this.perfilSeleccionado = perfilSeleccionado;
    }

    public void setT_perfiles(RichTable t1) {
        this.t_perfiles = t1;
    }

    public RichTable getT_perfiles() {
        return t_perfiles;
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

    public void setCmi_borrar(RichCommandMenuItem cmi4) {
        this.cmi_borrar = cmi4;
    }

    public RichCommandMenuItem getCmi_borrar() {
        return cmi_borrar;
    }


    public void setCmi_imprimir(RichCommandMenuItem cmi5) {
        this.cmi_imprimir = cmi5;
    }

    public RichCommandMenuItem getCmi_imprimir() {
        return cmi_imprimir;
    }

/*
    public void cmi_mostrar_filtros_actionListener(ActionEvent actionEvent) {
        if (this.cmi_mostrar_filtros.getText().startsWith(this.getText("menu_filtrar_mostrar"))){
            this.estadoFiltros(this.t_perfiles, true, this.cmi_mostrar_filtros);
        }else{
            this.estadoFiltros(this.t_perfiles, false, this.cmi_mostrar_filtros);
        }
    }

    public void cmi_limpiar_filtros_actionListener(ActionEvent actionEvent) {
        this.limpiarFiltros(t_perfiles);
    }
*/
    public void t_perfiles_selectionListener(SelectionEvent selectionEvent) {        
        PerfilVO perfil = (PerfilVO)t_perfiles.getSelectedRowData();                
        setPerfilSeleccionado(perfil);      
        try{
            SeguridadDelegate sd = SeguridadDelegate.getInstance();
            listaPermisosPerfil = sd.getMenuByPerfil(perfil);
            listaOpcionesPerfil = sd.getOpcionByPerfil(perfil);
        }catch(IdeamException e){
            this.showMessage(e);
        }
        this.ot_nombre_perfil.setValue(getText("perfiles_titulo_permisos")
                                         + " " + perfil.getNombre());        
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.pc2);
        // Refrescar la informacion de la tabla de permisos
        FacesUtils.removeManagedBeanFromSession("PerfilesTreeHandler");
        this.loadPerfiles();
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.tt_permisos);        
    }

    public void setPopupPerfil(RichPopup p1) {
        this.popupPerfil = p1;
    }

    public RichPopup getPopupPerfil() {
        return popupPerfil;
    }

    public void setG1(UIXGroup g1) {
        this.g1 = g1;
    }

    public UIXGroup getG1() {
        return g1;
    }

    public void setPopup_borrar(RichPopup p2) {
        this.popup_borrar = p2;
    }

    public RichPopup getPopup_borrar() {
        return popup_borrar;
    }

    public void setD_borrar(RichDialog d1) {
        this.d_borrar = d1;
    }

    public RichDialog getD_borrar() {
        return d_borrar;
    }

    public void setD_perfil(RichDialog d1) {
        this.d_perfil = d1;
    }

    public RichDialog getD_perfil() {
        return d_perfil;
    }

    public void setPsl1(RichPanelStretchLayout psl1) {
        this.psl1 = psl1;
    }

    public RichPanelStretchLayout getPsl1() {
        return psl1;
    }

    public void setI2(RichImage i2) {
        this.i2 = i2;
    }

    public RichImage getI2() {
        return i2;
    }

    public void setPfl1(RichPanelFormLayout pfl1) {
        this.pfl1 = pfl1;
    }

    public RichPanelFormLayout getPfl1() {
        return pfl1;
    }

    public void setIt_nombre(RichInputText it1) {
        this.it_nombre = it1;
    }

    public RichInputText getIt_nombre() {
        return it_nombre;
    }

    public void setSbr_activo(RichSelectBooleanRadio sbr1) {
        this.sbr_activo = sbr1;
    }

    public RichSelectBooleanRadio getSbr_activo() {
        return sbr_activo;
    }

    public void setSbr_inactivo(RichSelectBooleanRadio sbr2) {
        this.sbr_inactivo = sbr2;
    }

    public RichSelectBooleanRadio getSbr_inactivo() {
        return sbr_inactivo;
    }
    /**
     * muestra la informacion del perfil seleccionado, en los campos de la
     * ventana popup
     * @param actionEvent
     */
    public void cmi_editar_actionListener(ActionEvent actionEvent) {
        if (getPerfilSeleccionado() == null){
            this.showMessage(StandarBean.getText("seleccionar_registro"));
            return;
        }
        // Actualizar los campos de la ventana segun la informacion del usuario seleccionado                
        this.mostrarOcultarPopup(this.popupPerfil, true);   
        this.it_nombre.setValue(this.perfilSeleccionado.getNombre());
        this.sbr_activo.setSelected(this.perfilSeleccionado.isActivo());
        this.sbr_inactivo.setSelected(!this.perfilSeleccionado.isActivo());        
        this.sbc_core.setSelected(this.perfilSeleccionado.isIdeam());
        String titulo =  this.getText("perfiles_detalle");
        this.d_perfil.setTitle(titulo);        
        this.mostrarOcultarPopup(this.popupPerfil, true);        
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.popupPerfil);
    }
    
    public void cmi_adicionar_actionListener(ActionEvent actionEvent) {
        this.it_nombre.setValue("");
        this.sbr_activo.setSelected(true);        
        String titulo =  this.getText("perfiles_adicionar");
        this.d_perfil.setTitle(titulo);
        this.perfilSeleccionado=null;
        this.mostrarOcultarPopup(this.popupPerfil, true);        
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.popupPerfil);        
    }


    public List getListaMenu() {
        return listaMenu;
    }

    public void setListaMenu(List listaMenu) {
        this.listaMenu = listaMenu;
    }


    public void setPgl5(RichPanelGroupLayout pgl5) {
        this.pgl5 = pgl5;
    }

    public RichPanelGroupLayout getPgl5() {
        return pgl5;
    }

    public void setS1(RichSpacer s1) {
        this.s1 = s1;
    }

    public RichSpacer getS1() {
        return s1;
    }


    public void setPsl2(RichPanelStretchLayout psl2) {
        this.psl2 = psl2;
    }

    public RichPanelStretchLayout getPsl2() {
        return psl2;
    }


    public void setPc2(RichPanelCollection pc2) {
        this.pc2 = pc2;
    }

    public RichPanelCollection getPc2() {
        return pc2;
    }

    public void setM2(RichMenu m2) {
        this.m2 = m2;
    }

    public RichMenu getM2() {
        return m2;
    }

    public RichPanelSplitter getPs1() {
        return ps1;
    }

    public void setPs1(RichPanelSplitter ps1) {
        this.ps1 = ps1;
    }

    public void setTt_permisos(RichTreeTable tt1) {
        this.tt_permisos = tt1;
    }

    public RichTreeTable getTt_permisos() {
        return tt_permisos;
    }

    public void setCmi_permisos_guardar(RichCommandMenuItem cmi2) {
        this.cmi_permisos_guardar = cmi2;
    }

    public RichCommandMenuItem getCmi_permisos_guardar() {
        return cmi_permisos_guardar;
    }

    public void setOt7(RichOutputText ot7) {
        this.ot7 = ot7;
    }

    public RichOutputText getOt7() {
        return ot7;
    }

    public TreeModel getPerfilesTreeModel() {
        return perfilesTreeModel;
    }

    public void setPerfilesTreeModel(TreeModel perfilesTreeModel) {
        this.perfilesTreeModel = perfilesTreeModel;
    }

    public void setT1(RichToolbar t1) {
        this.t1 = t1;
    }

    public RichToolbar getT1() {
        return t1;
    }

    public void setOt_nombre_perfil(RichOutputText ot4) {
        this.ot_nombre_perfil = ot4;
    }

    public RichOutputText getOt_nombre_perfil() {
        return ot_nombre_perfil;
    }
    /**
     * Recorrer el arbol y almacenar los menus y grupos seleccionados en listas 
     * separadas para almacenarlas posteriormente en el sistema
     * @param actionEvent
     */
    public void cmi_permisos_guardar_actionListener(ActionEvent actionEvent) {
        this.perfilesSeleccionados = new ArrayList();
        this.opcionesSeleccionadas = new ArrayList();
        Iterator it = root.getChildren().iterator();
        while(it.hasNext()){
            TreeNode nodo = (TreeNode)it.next();
            if (nodo.getData()!=null &&
                nodo.getData() instanceof MenuVO){                
                MenuVO m = (MenuVO)nodo.getData();
                if (m.isSelected()){
                    perfilesSeleccionados.add(m);
                }
                if (m.getOpcionVOList()!=null){
                    Iterator itOpciones = m.getOpcionVOList().iterator();
                    while(itOpciones.hasNext()){
                        OpcionVO op = (OpcionVO)itOpciones.next();
                        if (op.isSelected()){
                            if (!op.getMenuVO().isSelected() &&
                                !perfilesSeleccionados.contains(op.getMenuVO())){
                                perfilesSeleccionados.add(op.getMenuVO());                                
                            }
                            opcionesSeleccionadas.add(op);
                        }
                    }
                }
                if (m.getMenuVOList()!=null && m.getMenuVOList().size()>0){
                    this.verifyNode(m);
                }
            }
        }       
        
        if (perfilesSeleccionados.size() == 0 &&
            opcionesSeleccionadas.size() == 0){
            this.showMessage(getText("perfiles_sin_opciones")
                            ,FacesMessage.SEVERITY_WARN);        
            return;
        }
        
        // Actualizar la informacion 
        try{
            SeguridadDelegate sd = SeguridadDelegate.getInstance();
            sd.updatePermisosPerfil(perfilSeleccionado, 
                                    perfilesSeleccionados,
                                    opcionesSeleccionadas);
        }catch(IdeamException e){
            this.showMessage(e);
            return;
        }
        this.ot_nombre_perfil.setValue("");        
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.pc2);
        
        this.showMessage(getText("perfiles_info_guardada") + 
                         this.perfilSeleccionado.getNombre(),
                         FacesMessage.SEVERITY_INFO);
    }
    
    public void verifyNode(MenuVO menu){
        Iterator it = menu.getMenuVOList().iterator();
        while(it.hasNext()){
            MenuVO m = (MenuVO)it.next();
            if (m.isSelected()){
                perfilesSeleccionados.add(m);
            }
            
            if (m.getOpcionVOList()!=null){
                Iterator itOpciones = m.getOpcionVOList().iterator();
                while(itOpciones.hasNext()){
                    OpcionVO op = (OpcionVO)itOpciones.next();
                    if (op.isSelected()){
                        opcionesSeleccionadas.add(op);
                    }
                }
            }
            if (m.getMenuVOList()!=null && m.getMenuVOList().size()>0){
                this.verifyNode(m);
            }            
        }       
    }
    /**
     * Valida los datos del perfil y actualiza la informacion en la 
     * base de datos
     * @param dialogEvent
     */
    public void d_perfil_dialogListener(DialogEvent dialogEvent) {
        try{
            SeguridadDelegate sd = SeguridadDelegate.getInstance();
            
            this.it_nombre.setValid(false);
            NombreValidator nombreValidator = new NombreValidator();
            nombreValidator.validate(
                FacesContext.getCurrentInstance(),
                this.it_nombre,
                this.it_nombre.getValue());
            if (!nombreValidator.isValid()){
                return;
            }
            
            PerfilVO perfilModificado = null;
            if (this.perfilSeleccionado==null){
                perfilModificado = new PerfilVO();
            }else{
                perfilModificado = this.perfilSeleccionado;
            }
            perfilModificado.setNombre(this.it_nombre.getValue().toString());
            perfilModificado.setActivo(this.sbr_activo.isSelected());
            perfilModificado.setIdeam(this.sbc_core.isSelected());
            sd.updatePerfil(perfilModificado);
            this.load();
            this.perfilSeleccionado=null;
            this.ot_nombre_perfil.setValue("");        
            this.t_perfiles.setSelectedRowKeys(null);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.pc2);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.t_perfiles);            
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.tt_permisos);
            
        }catch(IdeamException e){
            this.showMessage(e);
        }
    }

    public void cmi_borrar_actionListener(ActionEvent actionEvent) {
        if (this.getPerfilSeleccionado() == null){
            this.showMessage(getText("seleccionar_registro"));
            return;
        }
        String mensaje = getText("perfiles_confirma_borrar") + " " +
                         this.getPerfilSeleccionado().getNombre();
        this.ot_confirmar_borrar.setValue(mensaje);
        this.mostrarOcultarPopup(this.popup_borrar, true);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.popup_borrar);
    }

    public void setOt_confirmar_borrar(RichOutputText ot4) {
        this.ot_confirmar_borrar = ot4;
    }

    public RichOutputText getOt_confirmar_borrar() {
        return ot_confirmar_borrar;
    }
    /**
     * Borra el perfil selecciona posterior a la confirmacion de la
     * accion
     * @param dialogEvent
     */
    public void d_borrar_dialogListener(DialogEvent dialogEvent) {
        String action = dialogEvent.getOutcome().name();        
        if (action.equalsIgnoreCase("ok")){
            try{
                SeguridadDelegate sd = SeguridadDelegate.getInstance();
                int totalUsuarios = sd.countUsuariosPerfil(this.perfilSeleccionado);
                
                this.mostrarOcultarPopup(this.popup_borrar,false);
                String extraInfo[] = {"" + totalUsuarios};
                String mensaje = "";
                if (totalUsuarios>0){
                    mensaje = getText("perfiles_segunda_confirmacion_con_usuarios",
                                         extraInfo);
                }else{
                    mensaje = getText("perfiles_segunda_confirmacion_sin_usuarios",
                                      extraInfo);
                }
                this.ot_segunda_confirmacion.setValue(mensaje);
                this.mostrarOcultarPopup(this.popup_borrar_2, true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(this.popup_borrar_2);
                
            }catch(IdeamException e){
                showMessage(e);
            }            
        }      
    }

    public void d_borrar_2_dialogListener(DialogEvent dialogEvent) {
        String action = dialogEvent.getOutcome().name();        
        if (action.equalsIgnoreCase("ok")){
            try{
                SeguridadDelegate sd = SeguridadDelegate.getInstance();        
                sd.deletePerfil(this.getPerfilSeleccionado());
                this.load();
                this.perfilSeleccionado = null;
                AdfFacesContext.getCurrentInstance().addPartialTarget(this.t_perfiles);
                AdfFacesContext.getCurrentInstance().addPartialTarget(this.tt_permisos);
            }catch(IdeamException e){
                showMessage(e);
            }
        }        
    }

    public void setPopup_borrar_2(RichPopup p1) {
        this.popup_borrar_2 = p1;
    }

    public RichPopup getPopup_borrar_2() {
        return popup_borrar_2;
    }

    public void setD_borrar_2(RichDialog d1) {
        this.d_borrar_2 = d1;
    }

    public RichDialog getD_borrar_2() {
        return d_borrar_2;
    }

    public void setOt_segunda_confirmacion(RichOutputText ot4) {
        this.ot_segunda_confirmacion = ot4;
    }

    public RichOutputText getOt_segunda_confirmacion() {
        return ot_segunda_confirmacion;
    }

    public void cmi_imprimir_actionListener(ActionEvent actionEvent) {
        try {
            String atributos[] = {"nombre",
                                  "activo"};
            PerfilesReport reporte = new PerfilesReport();
            Collections.sort(listaPerfiles, new PerfilComparator());
            this.printTable(this.t_perfiles, atributos, reporte, true);
        } catch (IdeamException e) {
            this.showMessage(e);
        }
    }
    
    protected void printTable(RichTable tablaDatos, 
                              String attributes[],
                              ReporteInteface reporte,
                              boolean showAllColumns)throws IdeamException{        
        if (listaPerfiles==null || listaPerfiles.size()==0){
            throw new IdeamException(getText("reporte_vacio"));
        }
        SeguridadDelegate sd = SeguridadDelegate.getInstance();
        
        MutableDynaClass dynaClass = new LazyDynaClass();
        dynaClass.add("nombre",
                      java.lang.String.class);                           
        dynaClass.add("activo",
                      java.lang.String.class);                           
        dynaClass.add("totalUsuarios",
                      java.lang.String.class);                           
        
        Iterator it = listaPerfiles.iterator();
        List listaRegistros = new ArrayList();
        while(it.hasNext()){
            PerfilVO p = (PerfilVO)it.next();
            LazyDynaBean dynaBean = new  LazyDynaBean(dynaClass);
            String nombre = p.getNombre() + "\nOpciones Relacionadas";
            String permisos = sd.getPermisosPerfil(p);
            if (permisos==null || permisos.length()==0){
                nombre += "\nNinguna";
            }else{
                nombre += "\n" + permisos;
            }
            dynaBean.set("nombre", nombre);
            if (p.isActivo()){
                dynaBean.set("activo","Si");
            }else{
                dynaBean.set("activo","No");
            }
            int totalUsuario = sd.countUsuariosPerfil(p);
            dynaBean.set("totalUsuarios", "" + totalUsuario);
            listaRegistros.add(dynaBean);
        }

        byte[] contenido = reporte.createReport(listaRegistros,dynaClass,"session");
        
        if (contenido==null){
            throw new IdeamException("Reporte sion datos");
        }

        FacesUtils.setInSession("report-byte", contenido);        
        ExtendedRenderKitService service =Service.getRenderKitService(FacesContext.getCurrentInstance(),ExtendedRenderKitService.class);
        StringBuilder script = new StringBuilder();
        String url = FacesUtils.getUrl() + "/downloadservlet?showReport=pdf&report-name=nombre";        
        script.append("window.open('" + url + "','reporte');");        
        service.addScript(FacesContext.getCurrentInstance(), script.toString()); 
    }


    public void setSbc_core(RichSelectBooleanCheckbox sbc2) {
        this.sbc_core = sbc2;
    }

    public RichSelectBooleanCheckbox getSbc_core() {
        return sbc_core;
    }

    public void setS2(RichSpacer s2) {
        this.s2 = s2;
    }

    public RichSpacer getS2() {
        return s2;
    }

    public void setPsl3(RichPanelStretchLayout psl3) {
        this.psl3 = psl3;
    }

    public RichPanelStretchLayout getPsl3() {
        return psl3;
    }
}
