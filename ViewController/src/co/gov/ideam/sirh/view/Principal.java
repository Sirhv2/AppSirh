package co.gov.ideam.sirh.view;


import co.gov.ideam.sirh.parametros.model.Parametro;
import co.gov.ideam.sirh.parametros.model.TipoParametro;
import co.gov.ideam.sirh.parametros.view.ParametrosDelegate;
import co.gov.ideam.sirh.seguridad.model.MenuVO;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;


import co.gov.ideam.sirh.util.IdeamException;

import java.util.Iterator;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseEvent;

import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichMenu;
import oracle.adf.view.rich.component.rich.RichMenuBar;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandMenuItem;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.context.AdfFacesContext;

import org.apache.myfaces.trinidad.component.UIXGroup;
import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;


/**
 * Bean que representa la pagina principal del sistema.
 */
public class Principal extends StandarBean {
    private RichForm f2;
    private RichDocument d2;
    private UIXGroup g1;
    private UsuarioConectadoTO usuario;
    private RichMenuBar menuPrincipal;
    
    private boolean menuCargdo = false;
    private RichMenu m1;
    private RichCommandMenuItem cmi1;
    private String titulo;
    private String logo;

    /**
     * Crea el menu segun las opciones del usuario.
     */
    public Principal(){
        load();
        
    }
    public void load(){                
    }
    
    public void goMenuOption(ActionEvent evt){
        //System.out.println("opcion");
    }
    /**
     * Crea el menu del usuario segun los permisos asociados al mismo.
     */
    public void createMenus(PhaseEvent phaseEvent){           
        this.usuario = (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");       
        
        if (this.usuario.getMenu()!=null && !isMenuCargdo()){           
            FacesUtils.removeManagedBeanFromSession("dashBoard");            
            Iterator it = this.usuario.getMenu().iterator();
            while(it.hasNext()){                             
                MenuVO menuUsuario = (MenuVO)it.next();                      
                if (menuUsuario.getMenuVOList()!=null && 
                    menuUsuario.getMenuVOList().size()>0){                    
                    RichMenu menuAdicionar = this.createMenu(menuUsuario);   
                    menuAdicionar.setVisible( this.usuario.getUsuario().isRequiereCambioClave()==0 );
                    menuPrincipal.getChildren().add(menuAdicionar);
                }               
            }                        
            setMenuCargdo(true);
        }
        if (usuario.getUsuario().getAutoridadAmbiental()!=null){
            UIComponent comp = menuPrincipal.getParent();
            do{
                comp = comp.getParent();
            }while(comp!=null && !comp.getId().equalsIgnoreCase("pt_principal"));
            
            if (comp!=null && 
                comp.getId().equalsIgnoreCase("pt_principal") && 
                comp instanceof RichPanelStretchLayout){                
                
                RichPanelStretchLayout panel = (RichPanelStretchLayout)comp;
                UIComponent compTitulo = panel.findComponent("pt_car");
                if (compTitulo!=null &&
                    compTitulo instanceof RichOutputText){
                    RichOutputText titulo = (RichOutputText)compTitulo;
                    titulo.setValue(getText("AUTORIDAD_AMBIENTAL") + " " +
                                    usuario.getUsuario().getAutoridadAmbiental().getNombre());

                    this.titulo = getText("AUTORIDAD_AMBIENTAL") + " " +
                                  usuario.getUsuario().getAutoridadAmbiental().getNombre();                    
                }                
            } 
        }        
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.menuPrincipal);                
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.menuPrincipal.getParent());                
        navigateToDashBoard();
    }
    
    private void navigateToDashBoard(){        
        FacesContext context = FacesContext.getCurrentInstance();
        StringBuilder script = new StringBuilder();
        script.append("location.href='dashBoard.jspx' ");
        ExtendedRenderKitService erks =
          Service.getService(context.getRenderKit(),
                             ExtendedRenderKitService.class);
        erks.addScript(context, script.toString());    
    }
    private RichMenu createMenu(MenuVO menu){
        Iterator it = menu.getMenuVOList().iterator();
        RichMenu menuAdicionar = new RichMenu();
        menuAdicionar.setId("ID" + menu.getCodigo());
        menuAdicionar.setText(menu.getNombre());
        while(it.hasNext()){ 
            MenuVO menuUsuario = (MenuVO)it.next();                
            if (menuUsuario.getMenuVOList()!=null && 
                menuUsuario.getMenuVOList().size()>0){
                RichMenu menuAdicionar2 = this.createMenu(menuUsuario);                    
                menuAdicionar.getChildren().add(menuAdicionar2);            
            }else{
                RichCommandMenuItem item = new RichCommandMenuItem();
                item.setId("IDH" + menuUsuario.getCodigo());
                item.setText(menuUsuario.getNombre());          
                item.setRendered(true);
                item.setImmediate(true);
                if (menuUsuario.getAccion()!=null){
                    item.setActionExpression(this.getMethodExpression(menuUsuario.getAccion()));
                }else{
                    System.out.println("menu sin accion " + menuUsuario.getNombre());
                    item.setVisible(false);
                }
                
                /*
                MethodExpression me = this.getMethodExpression("#{principal.goMenuOption}");
                
                MethodExpressionActionListener listener = new
                MethodExpressionActionListener(me);

                item.addActionListener(listener);                
                //item.setActionExpression(this.getMethodExpression(menuUsuario.getAccion()));
*/
                menuAdicionar.getChildren().add(item);   
            }
        }        
        return menuAdicionar;
    }
    
    private MethodExpression getMethodExpression(String name) {  
        Class[] argtypes = new Class[1];  
        argtypes[0] = ActionEvent.class;  
        FacesContext facesCtx = FacesContext.getCurrentInstance();  
        Application app = facesCtx.getApplication();  
        ExpressionFactory elFactory = app.getExpressionFactory();  
        ELContext elContext = facesCtx.getELContext();  
        
        return elFactory.createMethodExpression(elContext, name, null,  
                                                argtypes);  
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


    public void setG1(UIXGroup g1) {
        this.g1 = g1;
    }

    public UIXGroup getG1() {
        return g1;
    }

    public void setMenuPrincipal(RichMenuBar mb1) {
        this.menuPrincipal = mb1;
    }

    public RichMenuBar getMenuPrincipal() {
        return menuPrincipal;
    }


    public void setM1(RichMenu m1) {
        this.m1 = m1;
}

    public RichMenu getM1() {
        return m1;
    }

    public void setCmi1(RichCommandMenuItem cmi1) {
        this.cmi1 = cmi1;
    }

    public RichCommandMenuItem getCmi1() {
        return cmi1;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public boolean isMenuCargdo() {
        return menuCargdo;
    }

    public void setMenuCargdo(boolean menuCargdo) {
        this.menuCargdo = menuCargdo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getLogo() throws IdeamException {
        ParametrosDelegate p= ParametrosDelegate.getInstance();
        TipoParametro t=p.getTipoParametro(new Long("2"));
        System.out.println("TIPO PARAMETRO:"+t.getNombre());
        Parametro pp=t.getParametro(new Long("8"));
        logo=pp.getValor();
        return logo;
    }
}
