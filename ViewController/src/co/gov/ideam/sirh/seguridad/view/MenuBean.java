package co.gov.ideam.sirh.seguridad.view;

import co.gov.ideam.sirh.seguridad.model.MenuVO;
import co.gov.ideam.sirh.seguridad.model.OpcionVO;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;
import co.gov.ideam.sirh.view.util.SpecialTreeModel;
import co.gov.ideam.sirh.view.util.TreeNode;

import java.io.InputStream;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.component.UISelectItems;
import javax.faces.event.ActionEvent;

import javax.faces.model.SelectItem;

import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;

import oracle.adf.view.rich.component.rich.RichMenu;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.data.RichTree;
import oracle.adf.view.rich.component.rich.input.RichInputNumberSpinbox;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanRadio;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.layout.RichToolbar;
import oracle.adf.view.rich.component.rich.nav.RichCommandMenuItem;
import oracle.adf.view.rich.component.rich.nav.RichCommandToolbarButton;
import oracle.adf.view.rich.component.rich.output.RichImage;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;

import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.adf.view.rich.event.DialogEvent;

import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.model.RowKeySet;
import org.apache.myfaces.trinidad.model.TreeModel;
/**
 * Bean encargado de administrar las diferentes opciones de menu que componen
 * el sistema
 */
public class MenuBean extends StandarBean{
    private TreeModel libraryTreeModel;
    private RichForm f2;
    private RichDocument d2;
    private RichPanelCollection pc1;
    private RichTree tree_menu;
    private RichOutputText ot1;
    private RichPanelSplitter ps1;
    private RichPanelCollection pc2;
        
    private List<OpcionVO> listaOpcionesMenuSeleccionado;
    private MenuVO menuSeleccionado;
    private List listaPadres;
    private boolean creandoNuevoMenu;
    
    private RichTable t_opciones;
    private RichToolbar t1;
    private RichCommandToolbarButton ctb_actualizar;
    private RichToolbar t2;
    private RichOutputText ot_nombre_menu;
    private RichMenu m_archivo;
    private RichCommandMenuItem cmi_adicionar;
    private RichPopup popup_detalle;
    private RichDialog d_detalle;
    private RichCommandMenuItem cmi_editar;
    private RichPanelStretchLayout psl1;
    private RichPanelFormLayout pfl1;
    private RichImage i1;
    private RichInputText it_nombre;
    private RichSelectOneChoice soc_padre;
    private UISelectItems si1;
    private RichSelectBooleanRadio sbr_activo;
    private RichSelectBooleanRadio sbr_inactivo;
    private RichInputText it_imagen;
    private RichInputNumberSpinbox ins_orden;
    private RichInputText it_accion;
    private RichInputText it_pagina;
    private RichInputText it_nombre_clase;
    private RichPanelStretchLayout psl2;

    public MenuBean(){
        FacesUtils.setActiveBean("menu",
                                 "Administrar Menu",
                                 SeguridadDelegate.class);
        this.load();
    }
    public void load(){
        TreeNode root = new TreeNode(getText("login_titulo_1"), "root");
        this.listaPadres = new ArrayList();
        SelectItem item = new SelectItem("nobody","Ninguno");
        this.listaPadres.add(item);
        try {
            SeguridadDelegate sd = SeguridadDelegate.getInstance();
            List listaMenu = sd.getAllMenu();
            Iterator it = listaMenu.iterator();
            while(it.hasNext()){
                MenuVO menu = (MenuVO)it.next();                
                item = new SelectItem(menu,menu.getNombre());
                this.listaPadres.add(item);                
                TreeNode nodoPrincipal = new TreeNode(menu.getNombre(), "principalNode");                   
                nodoPrincipal.setData(menu);
                this.processNode(nodoPrincipal, menu, 1);
                root.getChildren().add(nodoPrincipal);
            }
        } catch (IdeamException e) {
            super.showMessage(e);
        }
        List nodes = new ArrayList();
        nodes.add(root);

        libraryTreeModel = new SpecialTreeModel(root, "children");             
    }
    
    private void processNode(TreeNode node, MenuVO menu, int level){        
        int newLevel = level + 1;
        if (menu!=null && menu.getMenuVOList()!=null && menu.getMenuVOList().size()>0){
            Iterator it = menu.getMenuVOList().iterator();
            while(it.hasNext()){
                MenuVO m = (MenuVO)it.next();                
                TreeNode hijo = new TreeNode(m.getNombre(), "nivel_" + newLevel);
                hijo.setData(m);
                node.getChildren().add(hijo);
                if (m.getMenuVOList()!=null && m.getMenuVOList().size()>0){
                    this.processNode(hijo, m, newLevel);
                }else{
                    String titulo = m.getNombre();
                    MenuVO padre = m.getMenuVO();
                    while(padre!=null){
                        titulo = padre.getNombre() + "->" + titulo;
                        padre = padre.getMenuVO();
                    }
                    SelectItem item = new SelectItem(menu, titulo);
                    this.listaPadres.add(item);
                }
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

    public TreeModel getLibraryTreeModel() {
        return libraryTreeModel;
    }

    public void setLibraryTreeModel(TreeModel libraryTreeModel) {
        this.libraryTreeModel = libraryTreeModel;
    }

    public void setPc1(RichPanelCollection pc1) {
        this.pc1 = pc1;
    }

    public RichPanelCollection getPc1() {
        return pc1;
    }

    public void setTree_menu(RichTree t1) {
        this.tree_menu = t1;
    }

    public RichTree getTree_menu() {
        return tree_menu;
    }

    public void setOt1(RichOutputText ot1) {
        this.ot1 = ot1;
    }

    public RichOutputText getOt1() {
        return ot1;
    }

    public RichPanelSplitter getPs1() {
        return ps1;
    }

    public void setPs1(RichPanelSplitter ps1) {
        this.ps1 = ps1;
    }

    public void setPc2(RichPanelCollection pc2) {
        this.pc2 = pc2;
    }

    public RichPanelCollection getPc2() {
        return pc2;
    }

    public List getListaOpcionesMenuSeleccionado() {
        return listaOpcionesMenuSeleccionado;
    }

    public void setListaOpcionesMenuSeleccionado(List listaOpcionesMenuSeleccionado) {
        this.listaOpcionesMenuSeleccionado = listaOpcionesMenuSeleccionado;
    }

    public void setT_opciones(RichTable t2) {
        this.t_opciones = t2;
    }

    public RichTable getT_opciones() {
        return t_opciones;
    }

    public void tree_menu_selectionListener(SelectionEvent selectionEvent) {        
        RowKeySet selection = this.tree_menu.getSelectedRowKeys();                
        if (selection != null && selection.getSize() > 0) {
            for (Object facesTreeRowKey : selection) {
                this.tree_menu.setRowKey(facesTreeRowKey);
                if (tree_menu.getRowData()!=null && tree_menu.getRowData() instanceof TreeNode){
                    TreeNode nodo = (TreeNode)tree_menu.getRowData();
                    this.setMenuSeleccionado((MenuVO)nodo.getData());
                }
                //System.out.println ("98" + ((TreeNode)tree_menu.getRowData()).getDescription());                                    
                //System.out.println ("98" + ((TreeNode)tree_menu.getRowData()).getData().getClass().getName());                                    
            }         
        }
    }

    public MenuVO getMenuSeleccionado() {
        return menuSeleccionado;
    }

    public void setMenuSeleccionado(MenuVO menuSeleccionado) {
        this.menuSeleccionado = menuSeleccionado;
        if (menuSeleccionado!=null){
            this.listaOpcionesMenuSeleccionado = menuSeleccionado.getOpcionVOList();
            this.ot_nombre_menu.setValue(menuSeleccionado.getNombre());
        }else{
            this.listaOpcionesMenuSeleccionado = null;
            this.ot_nombre_menu.setValue("");
        }
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.t_opciones);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.pc2);
    }

    public void setT1(RichToolbar t1) {
        this.t1 = t1;
    }

    public RichToolbar getT1() {
        return t1;
    }

    public void setCtb_actualizar(RichCommandToolbarButton ctb1) {
        this.ctb_actualizar = ctb1;
    }

    public RichCommandToolbarButton getCtb_actualizar() {
        return ctb_actualizar;
    }

    public void setT2(RichToolbar t2) {
        this.t2 = t2;
    }

    public RichToolbar getT2() {
        return t2;
    }

    public void setOt_nombre_menu(RichOutputText ot5) {
        this.ot_nombre_menu = ot5;
    }

    public RichOutputText getOt_nombre_menu() {
        return ot_nombre_menu;
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

    public void setPopup_detalle(RichPopup p1) {
        this.popup_detalle = p1;
    }

    public RichPopup getPopup_detalle() {
        return popup_detalle;
    }

    public void setD_detalle(RichDialog d1) {
        this.d_detalle = d1;
    }

    public RichDialog getD_detalle() {
        return d_detalle;
    }

    public void setCmi_editar(RichCommandMenuItem cmi2) {
        this.cmi_editar = cmi2;
    }

    public RichCommandMenuItem getCmi_editar() {
        return cmi_editar;
    }

    public void cmi_editar_actionListener(ActionEvent actionEvent) {
        if (this.getMenuSeleccionado()==null){
            this.showMessage(StandarBean.getText("seleccionar_registro"));
            return;
        }
        this.mostrarOcultarPopup(this.popup_detalle,true);
        this.mostrarMenu();
    }
    private void mostrarMenu(){
        this.creandoNuevoMenu = false;
        this.it_nombre.setValue(this.menuSeleccionado.getNombre());
        this.sbr_activo.setSelected(this.menuSeleccionado.isActivo());
        this.sbr_inactivo.setSelected(!this.menuSeleccionado.isActivo());        
        this.ins_orden.setValue(this.menuSeleccionado.getOrden());
        this.it_accion.setValue(this.menuSeleccionado.getAccion());
        this.it_pagina.setValue(this.menuSeleccionado.getPagina());
        if (this.menuSeleccionado.getMenuVO()!=null){
            this.soc_padre.setValue(this.menuSeleccionado.getMenuVO());
        }else{
            this.soc_padre.setValue("nobody");
        }
        this.it_nombre_clase.setValue(this.menuSeleccionado.getNombreClase());
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.popup_detalle);
    }
    
    

    public void setPsl1(RichPanelStretchLayout psl1) {
        this.psl1 = psl1;
    }

    public RichPanelStretchLayout getPsl1() {
        return psl1;
    }

    public void setPfl1(RichPanelFormLayout pfl1) {
        this.pfl1 = pfl1;
    }

    public RichPanelFormLayout getPfl1() {
        return pfl1;
    }

    public void setI1(RichImage i1) {
        this.i1 = i1;
    }

    public RichImage getI1() {
        return i1;
    }

    public void setIt_nombre(RichInputText it1) {
        this.it_nombre = it1;
    }

    public RichInputText getIt_nombre() {
        return it_nombre;
    }

    public List getListaPadres() {
        return listaPadres;
    }

    public void setListaPadres(List listaPadres) {
        this.listaPadres = listaPadres;
    }

    public void setSoc_padre(RichSelectOneChoice soc1) {
        this.soc_padre = soc1;
    }

    public RichSelectOneChoice getSoc_padre() {
        return soc_padre;
    }

    public void setSi1(UISelectItems si1) {
        this.si1 = si1;
    }

    public UISelectItems getSi1() {
        return si1;
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

    public void setIt_imagen(RichInputText it1) {
        this.it_imagen = it1;
    }

    public RichInputText getIt_imagen() {
        return it_imagen;
    }

    public void setIns_orden(RichInputNumberSpinbox ins1) {
        this.ins_orden = ins1;
    }

    public RichInputNumberSpinbox getIns_orden() {
        return ins_orden;
    }

    public void setIt_accion(RichInputText it2) {
        this.it_accion = it2;
    }

    public RichInputText getIt_accion() {
        return it_accion;
    }

    public void setIt_pagina(RichInputText it3) {
        this.it_pagina = it3;
    }

    public RichInputText getIt_pagina() {
        return it_pagina;
    }

    public void cmi_adicionar_actionListener(ActionEvent actionEvent) {
        this.creandoNuevoMenu=true;
        this.it_nombre.setValue("");
        this.sbr_activo.setSelected(true);        
        this.it_imagen.setValue("");
        this.ins_orden.setValue(1);
        this.it_accion.setValue("");
        this.it_pagina.setValue("");
        if (this.menuSeleccionado!=null){
            this.soc_padre.setValue(this.getMenuSeleccionado());
        }else{
            this.soc_padre.setValue("nobody");
        }        
        this.mostrarOcultarPopup(this.popup_detalle, true);                
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.popup_detalle);
    }


    public void d_detalle_dialogListener(DialogEvent dialogEvent) {        
        try{
            SeguridadDelegate sd = SeguridadDelegate.getInstance();
            
            // validar que no exista otro menu con la misma pagina
            if (this.it_pagina.getValue()!=null){
                String pagina = this.it_pagina.getValue().toString();
                if (pagina.length()>0){                
                    MenuVO existe = sd.getMenuByPage(pagina);
                    if (existe!=null){
                        if (creandoNuevoMenu || 
                            (!creandoNuevoMenu && existe.getCodigo() !=
                                                  this.getMenuSeleccionado().getCodigo())){
                            this.showMessage("menu_existe_pagina",
                                             FacesMessage.SEVERITY_ERROR,
                                             this.it_pagina);
                            return;
                        }
                    }
                    
                    if (pagina.startsWith("/")){
                        this.showMessage("menu_pagina_inicio",
                                         FacesMessage.SEVERITY_ERROR,
                                         this.it_pagina);
                        return;
                    }
                }   
                
                // Si existe nombre de la pagina se exige nombre de la clase
                if (this.it_nombre_clase.getValue()==null ||
                    this.it_nombre_clase.getValue().toString().length()==0){                    
                    this.showMessage("menu_falta_clase",
                                     FacesMessage.SEVERITY_ERROR,
                                     this.it_nombre_clase);
                    return;                        
                }
            }
            // validar que no exista otro menu con la misma acccion
            if(this.it_accion.getValue()!=null){
                String accion = this.it_accion.getValue().toString();
                if (accion.length()>0){
                    MenuVO existe = sd.getMenuByPage(accion);
                    if (existe!=null){
                        if (creandoNuevoMenu || 
                            (!creandoNuevoMenu && existe.getCodigo() !=
                                                  this.getMenuSeleccionado().getCodigo())){                    
                            this.showMessage("menu_existe_accion",
                                             FacesMessage.SEVERITY_ERROR,
                                             this.it_nombre_clase);
                            return;
                        }
                    }
                }
            }    
            
            // validar que no exista otro menu con el mismo nombre de clase
            if(this.it_nombre_clase.getValue()!=null){
                String clase = this.it_nombre_clase.getValue().toString();
                if (clase.length()>0){
                    MenuVO existe = sd.getMenuByClassName(clase);
                    if (existe!=null){
                        if (creandoNuevoMenu || 
                            (!creandoNuevoMenu && existe.getCodigo() !=
                                                  this.getMenuSeleccionado().getCodigo())){                    
                            this.showMessage("menu_existe_clase",
                                             FacesMessage.SEVERITY_ERROR,
                                             this.it_nombre_clase);
                            return;
                        }
                    }
                }
            }     
            
            // Validar que no sea padre de si mismo    
            if (!this.creandoNuevoMenu){                                    
                if (this.soc_padre.getValue() instanceof MenuVO){
                    MenuVO padreSeleccionado = (MenuVO)this.soc_padre.getValue();
                    if (padreSeleccionado.getCodigo() ==
                        this.getMenuSeleccionado().getCodigo()){
                            this.showMessage("menu_self_padre",
                                             FacesMessage.SEVERITY_ERROR,
                                             this.soc_padre);
                            return;
                        }
                }                
            }
            
            MenuVO menuModificado = null;
            if (!this.creandoNuevoMenu){
                menuModificado = this.getMenuSeleccionado();
            }else{
                menuModificado = new MenuVO();
            }
                
            menuModificado.setNombre(this.it_nombre.getValue().toString());
            System.out.println("Estado del menu de la pag:"+this.sbr_activo.isSelected());
            menuModificado.setActivo(this.sbr_activo.isSelected());
            System.out.println("Estado del menu:"+menuModificado.isActivo());
            if(menuModificado.isActivo())
                menuModificado.setActivoAux(1);
            else
                menuModificado.setActivoAux(0);
            menuModificado.setOrden(Long.parseLong(this.ins_orden.getValue().toString()));
            if (this.it_accion.getValue()!=null){
                menuModificado.setAccion(this.it_accion.getValue().toString());
            }else{
                menuModificado.setAccion(null);
            }
            if (this.it_pagina.getValue()!=null){                
                menuModificado.setPagina(this.it_pagina.getValue().toString());
            }else{
                menuModificado.setPagina(null);
            }
            if (this.soc_padre.getValue() instanceof MenuVO){
                MenuVO padre = (MenuVO)this.soc_padre.getValue();
                menuModificado.setMenuVO(padre);
            }
            if (this.it_nombre_clase.getValue()!=null){                
                menuModificado.setNombreClase(this.it_nombre_clase.getValue().toString());
            }else{
                menuModificado.setNombreClase(null);
            }            
            
            sd.updateMenu(menuModificado);
            FacesUtils.removeManagedBeanFromSession("LibraryTreeHandler");            
            this.load();                        
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.tree_menu);
        }catch(IdeamException e){
            this.showMessage(e);
        }                
    }

    public void ctb_actualizar_actionListener(ActionEvent actionEvent) {
        if (this.getMenuSeleccionado()==null){
            this.showMessage(StandarBean.getText("seleccionar_registro"));
            return;
        }
        if (this.getMenuSeleccionado().getPagina()==null ||
            this.getMenuSeleccionado().getPagina().length()==0){
            this.showMessage(StandarBean.getText("menu_actualizar_sin_pagina"));
            return;
        }
        // Recorrer la pagina y ubicar los botones
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader(); 
        InputStream input = classLoader.getResourceAsStream("../../../" + 
                    this.menuSeleccionado.getPagina()); 
        try{            
            SeguridadDelegate sd = SeguridadDelegate.getInstance();
            Map idBotones = FinderButtons.getComponentsByTag(input, "af:commandMenuItem");
            Iterator it = this.listaOpcionesMenuSeleccionado.iterator();
            while(it.hasNext()){
                OpcionVO opcion = (OpcionVO)it.next();                
                if (!idBotones.containsKey(opcion.getOpcIdJsf())){
                    opcion.setActivo(false);
                    sd.updateOpcion(opcion);
                }else{
                    idBotones.remove(opcion.getOpcIdJsf());
                }
            }
                        
            Iterator itMap  = idBotones.entrySet().iterator();
            while(itMap.hasNext()){
                Map.Entry boton = (Map.Entry)itMap.next();
                String id = boton.getKey().toString();
                String text = boton.getValue().toString();
                if (text.startsWith("#{viewcontrollerBundle.")){
                    int pos = text.indexOf(".");
                    text = text.substring(pos+1);
                    text = text.substring(0,text.length()-1);
                    text=getText(text);                    
                }
                OpcionVO newOpcion = new OpcionVO();
                newOpcion.setActivo(true);
                newOpcion.setMenuVO(this.getMenuSeleccionado());
                newOpcion.setOpcIdJsf(id);
                newOpcion.setOpcNombre(text);
                sd.updateOpcion(newOpcion);
            }
            FacesUtils.removeManagedBeanFromSession("LibraryTreeHandler");
            this.load();                        
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.tree_menu);            
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.t_opciones);
        }catch(IdeamException e){
            this.showMessage(new IdeamException(e));
        }
    }

    public void setIt_nombre_clase(RichInputText it1) {
        this.it_nombre_clase = it1;
    }

    public RichInputText getIt_nombre_clase() {
        return it_nombre_clase;
    }


    public void setPsl2(RichPanelStretchLayout psl2) {
        this.psl2 = psl2;
    }

    public RichPanelStretchLayout getPsl2() {
        return psl2;
    }
}
