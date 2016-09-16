package co.gov.ideam.sirh.view;

import co.gov.ideam.sirh.calidad.model.CriteriosBusquedaMuestrasTO;
import co.gov.ideam.sirh.calidad.model.CriteriosBusquedaPuntosTO;
import co.gov.ideam.sirh.calidad.model.DatosReporteCalidadTO;


import co.gov.ideam.sirh.auditoria.model.Auditoria;
import co.gov.ideam.sirh.auditoria.view.AuditoriasDelegate;
import co.gov.ideam.sirh.calidad.model.PuntoMonitoreo;
import co.gov.ideam.sirh.calidad.model.PuntoMonitoreoTO;
import co.gov.ideam.sirh.calidad.red.ideam.view.RedMonitoreoDelegate;
import co.gov.ideam.sirh.calidad.view.CalidadDelegate;
import co.gov.ideam.sirh.fuentes.model.CriteriosBusquedaTO;
import co.gov.ideam.sirh.fuentes.model.FnttConflicto;
import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.fuentes.model.FnttTramo;
import co.gov.ideam.sirh.parametros.model.Categoria;
import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.model.PartZonificListas;
import co.gov.ideam.sirh.parametros.view.ParametrosDelegate;
import co.gov.ideam.sirh.fuentes.view.FuenteDelegate;
import co.gov.ideam.sirh.parametros.business.ParametrosEJBBean;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.reportes.view.ReporteDelegate;
import co.gov.ideam.sirh.seguridad.model.PerfilVO;
import co.gov.ideam.sirh.util.ExportTypeFactory;
import co.gov.ideam.sirh.util.ExportTypes;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.seguridad.view.ReporteInteface;
import co.gov.ideam.sirh.seguridad.view.SeguridadDelegate;
import co.gov.ideam.sirh.usuarios.agua.model.UsuarioAgua;
import co.gov.ideam.sirh.usuarios.agua.view.UsuariosAguaDelegate;
import co.gov.ideam.sirh.util.Constantes;
import co.gov.ideam.sirh.util.ConstantesCalidad;
import co.gov.ideam.sirh.util.ConstantesDemanda;
import co.gov.ideam.sirh.util.ConstantesOferta;
import co.gov.ideam.sirh.util.IdeamException;


import co.gov.ideam.sirh.util.Instrospector;


import java.io.File;

import java.io.ObjectInput;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import javax.faces.event.PhaseEvent;

import javax.faces.model.SelectItem;
import javax.faces.validator.Validator;

import javax.persistence.Query;

import javax.servlet.ServletContext;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import net.sf.jasperreports.engine.util.JRLoader;

import net.sf.jasperreports.engine.util.JRStyledText;

import oracle.adf.view.rich.component.rich.RichMenu;
import oracle.adf.view.rich.component.rich.RichMenuBar;
import oracle.adf.view.rich.component.rich.RichPopup;

import oracle.adf.view.rich.component.rich.data.RichColumn;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanRadio;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandMenuItem;

import oracle.adf.view.rich.context.AdfFacesContext;


import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.LazyDynaBean;
import org.apache.commons.beanutils.LazyDynaClass;
import org.apache.commons.beanutils.MutableDynaClass;
import org.apache.myfaces.trinidad.component.UIXInput;
import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;

/**
 * Metodos comunes de utilidad para todos los Bean de la capa de
 * presentacion
 */
public abstract class StandarBean {
    /**
     * Variable con el nombre del archivo que contiene los mensajes
     */
    private static ResourceBundle messages = null;
    /**
     * Variable con el texto que se debe mostrar en los campos de texto
     */
    private static String textoFiltrar = null;
    
    // Inicializador estatico
    static{                
        messages = ResourceBundle.getBundle("viewcontroller.ViewControllerBundle");
        textoFiltrar = messages.getString("filtrar");        
    }
    
    protected List cargarDivipola(Long codigoPadre)throws IdeamException{
        UsuarioConectadoTO usuarioConectado = 
            (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
        SeguridadDelegate seg = SeguridadDelegate.getInstance(); 
        PerfilVO pp = null;
        
        if (usuarioConectado != null) 
            pp =seg.getPerfilUsuario(usuarioConectado.getUsuario().getCodigo());

        ParametrosDelegate pd = ParametrosDelegate.getInstance();                
        List lista = new ArrayList();//carga el selectItem
        List deptos = null;
        if (usuarioConectado == null ||            
            usuarioConectado.getUsuario().getAutoridadAmbiental()==null ||
            usuarioConectado.getUsuario().getAutoridadAmbiental().getId() == null ||
            usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue() ==0 ||
            usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue() == Constantes.IDEAM||
            pp.getCodigo()==ConstantesCalidad.PERFIL_CONSULTA){
            deptos = pd.getDivipola(codigoPadre);
        }else{
            deptos = pd.getDivipola(codigoPadre, usuarioConectado.getUsuario().getAutoridadAmbiental());
        }
        if(deptos!=null){
            Iterator it = deptos.iterator();
            while(it.hasNext()){
                Divipola pola = (Divipola)it.next();
                SelectItem si = new SelectItem(pola, pola.getNombre());
                lista.add(si);
            }
        }                
        return lista;
    }


protected List cargarDivipolaSinRestriccion(Long codigoPadre) throws IdeamException {

        ParametrosDelegate pd = ParametrosDelegate.getInstance();
        List lista = new ArrayList(); //carga el selectItem
        List deptos = null;
        deptos = pd.getDivipola(codigoPadre);
        if (deptos != null) {
            Iterator it = deptos.iterator();
            while (it.hasNext()) {
                Divipola pola = (Divipola)it.next();
                SelectItem si = new SelectItem(pola, pola.getNombre());
                lista.add(si);
            }
        }
        return lista;
    }
    
    public List<Divipola> getDivipola(Long codigo, Autoridades autoridad)throws IdeamException{
        ParametrosDelegate pd = ParametrosDelegate.getInstance();
        List lista = new ArrayList(); //carga el selectItem
        List deptos = null;
        deptos = pd.getDivipola(codigo, autoridad);   
        if (deptos != null) {
            Iterator it = deptos.iterator();
            while (it.hasNext()) {
                Divipola pola = (Divipola)it.next();
                SelectItem si = new SelectItem(pola, pola.getNombre());
                lista.add(si);
            }
        }
        return lista;
    }
    
    public List cargarZonificacionExt(Integer codigoPadre, Long Autoridad)throws IdeamException{
              
        ParametrosDelegate pd = ParametrosDelegate.getInstance();                
        List lista = new ArrayList();//carga el selectItem.
        List result = null;
        if (Autoridad == Constantes.IDEAM){//si es ideam
            result = pd.getZonificacion(codigoPadre);
        }
        if(result!=null){
            Iterator it = result.iterator();
            while(it.hasNext()){
                PartZonificListas result1 = (PartZonificListas)it.next();
                SelectItem si = new SelectItem(result1, result1.getValor());
                lista.add(si);
            }
        }                
        return lista;
    }
    
    
    public List cargarZonificacion(Integer codigoPadre)throws IdeamException{
        UsuarioConectadoTO usuarioConectado = 
            (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
        
     
        
        ParametrosDelegate pd = ParametrosDelegate.getInstance();                
        List lista = new ArrayList();//carga el selectItem.
        List result = null;
        if (usuarioConectado == null || 
            usuarioConectado.getUsuario().getAutoridadAmbiental()==null ||
            usuarioConectado.getUsuario().getAutoridadAmbiental().getId() == null ||
            usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue() ==0 ||
            usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue() == Constantes.IDEAM){//si es ideam
            result = pd.getZonificacion(codigoPadre);
        }else {//si es car
            result = pd.getZonificacion(codigoPadre, usuarioConectado.getUsuario().getAutoridadAmbiental());
        }
        if(result!=null){
            Iterator it = result.iterator();
            while(it.hasNext()){
                PartZonificListas result1 = (PartZonificListas)it.next();
                SelectItem si = new SelectItem(result1, result1.getValor());
                lista.add(si);
            }
        }                
        return lista;
    }
    
  public List cargarTipoFuentes(Long codigoPadre)throws IdeamException{
      UsuarioConectadoTO usuarioConectado = 
          (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
      ParametrosDelegate pd = ParametrosDelegate.getInstance();                
      List lista = new ArrayList();//carga el selectItem.
      List result = null;
      if (usuarioConectado == null || 
          usuarioConectado.getUsuario().getAutoridadAmbiental()==null ||
          usuarioConectado.getUsuario().getAutoridadAmbiental().getId() == null ||
          usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue() ==0 ||
          usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue() == Constantes.IDEAM){//si es ideam
          result = pd.getTipoFuentes(codigoPadre);
      }/*else {//si es car
          result = pd.getZonificacion(codigoPadre, usuarioConectado.getUsuario().getAutoridadAmbiental());
      }*/
      if(result!=null){
          Iterator it = result.iterator();
          while(it.hasNext()){
              Lista result1 = (Lista)it.next();
              SelectItem si = new SelectItem(result1, result1.getValor());
              lista.add(si);
          }
      }                
      return lista;
  }
 //Metodos consulta Listas sin restriccion  
    
    public List getListasZonificacion(Integer codigoPadre, Autoridades autoridades)throws IdeamException{             
        ParametrosDelegate pd = ParametrosDelegate.getInstance();    
        
        List lista = new ArrayList();//carga el selectItem.
            List result = null;        
               result = pd.getZonificacion(codigoPadre, ((Autoridades) autoridades));   
                if(result!=null){
                    Iterator it = result.iterator();
                    while(it.hasNext()){
                        PartZonificListas result1 = (PartZonificListas)it.next();
                        SelectItem si = new SelectItem(result1, result1.getValor());
                        lista.add(si);
                    }       
                } 
        return lista;
    }
    
    protected List cargarParametro(Long codigoCategoria)throws IdeamException{
        CalidadDelegate pd = CalidadDelegate.getInstance();        
        List listaDatos = new ArrayList();
                
        List lParametros = pd.getListaPorCategoria(codigoCategoria);

        List listaCategoria = lParametros;
        Iterator it = listaCategoria.iterator();
        while(it.hasNext()){
            Lista l = (Lista)it.next();
            SelectItem item = new SelectItem(l, l.getValor());
            listaDatos.add(item);                
        }
        
        return listaDatos;
    }
    
    protected List cargarFuentes(CriteriosBusquedaTO criterios)throws IdeamException{       
        FuenteDelegate fd = FuenteDelegate.getInstance();
        List listaDatos = new ArrayList();
        List listaFuentes = new ArrayList();
        
        listaFuentes = fd.getAllFuentes(criterios);//todas las fuentes
        
        if(listaFuentes!=null && listaFuentes.size()>0){
            Iterator it = listaFuentes.iterator();
            while(it.hasNext()){
                FnttFuente l = (FnttFuente)it.next();
                SelectItem item = new SelectItem(l, l.getNombre());
                listaDatos.add(item);                
            }
        }
        return listaDatos;
    }
    
    protected List cargarTramos(CriteriosBusquedaTO criterios)throws IdeamException{       
        FuenteDelegate fd = FuenteDelegate.getInstance();
        List listaDatos = new ArrayList();
        List listaTramos = new ArrayList();
        
        listaTramos = fd.getAllTramos(criterios);//todas las fuentes
        
        System.out.print(" HCP total tramos " + listaTramos.size() );
        if(listaTramos!=null && listaTramos.size()>0){
            Iterator it = listaTramos.iterator();
            while(it.hasNext()){
                FnttTramo l = (FnttTramo)it.next();
                SelectItem item = new SelectItem(l, l.getNombre());
                listaDatos.add(item);                
            }
        }
        return listaDatos;
    }
    
    protected List cargarContinuidad()throws IdeamException{       
        List listaDatos = new ArrayList();
        
        SelectItem itemSi = new SelectItem("SI", "Si");
        listaDatos.add(itemSi);
        
        SelectItem itemNo = new SelectItem("NO", "No");
        listaDatos.add(itemNo);  
        return listaDatos;
    }
    
    /**
     * Recorre todos los objetos de la pagina y deja invisibles todos los
     * componentes de tipo commandMenuItem
     * @param phaseEvent
     */
    public void cargarSeguridad(PhaseEvent phaseEvent){                        
        UsuarioConectadoTO usuario = (UsuarioConectadoTO)FacesUtils.getManagedBeanValue("UsuarioConectado");        
        
        UIViewRoot root = FacesContext.getCurrentInstance().getViewRoot();        
        if (root!=null){
            Iterator it = root.getFacetsAndChildren();
            while(it.hasNext()){
                UIComponent comp = (UIComponent)it.next();                
                this.processComponent(comp, usuario);
            }
        }        
    }
    /**
     * Recorre todos los objetos del componente y deja invisibles todos
     * los componentes de tipo commandMenuItem
     * @param comp
     */
    private void processComponent(UIComponent comp, UsuarioConectadoTO usuario){                
        if (comp!=null && 
            comp instanceof RichMenuBar && 
            ((RichMenuBar)comp).getId().equalsIgnoreCase("menuPrincipal")){
            return;
        }
        if (comp!= null && comp instanceof RichCommandMenuItem){
            String nombreOpcion = ((RichCommandMenuItem)comp).getId();            
            boolean mostrar = usuario.isEnabled(this.getClass().getName(),
                                              nombreOpcion);
            ((RichCommandMenuItem)comp).setVisible(mostrar);
            //System.out.println("opcion " + this.getClass().getName() + " " + nombreOpcion + " " + mostrar);
        }
        if (comp!= null && comp instanceof RichMenu){
            String nombreOpcion = ((RichMenu)comp).getId();
            /*((RichMenu)comp).setVisible(usuario.isEnabled(
                                        this.getClass().getName(),
                                        nombreOpcion));            */
            ((RichMenu)comp).setVisible(true);
        }       
        if (comp!=null){
            Iterator it = comp.getFacetsAndChildren();
            while(it.hasNext()){
                UIComponent c = (UIComponent)it.next();                
                this.processComponent(c, usuario);
            }
        }
    }
    
    /**
     * Obtiene el componente correspondiente al nombre recibido como parametro
     * @param name
     * @return
     */
    public UIComponent getComponent(String name) {
        return FacesContext.getCurrentInstance().getViewRoot().findComponent(name);
    }
    /**
     * Muestra un mensaje permosnalizado segun los parametros recibidos y lo 
     * asocia al componente
     * @param keyMsg
     * @param severity
     * @param uiComponent
     */
    protected void showMessage(String keyMsg, String params[], javax.faces.application.FacesMessage.Severity severity, UIComponent uiComponent){
        String mensaje = StandarBean.getText(keyMsg, params);        
        FacesMessage msg =  new FacesMessage(severity,
                                            mensaje, null);            
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.addMessage(uiComponent.getClientId(ctx), msg);             
    }    
    /**
     * muestra el mensaje asoicado a la excepcion recibida como parametro
     * @param e
     */
    protected void showMessage(IdeamException e){
        FacesContext fc = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage(e.getMessage(), e.getLocalizedMessage());
        message.setSeverity(FacesMessage.SEVERITY_FATAL);
        message.setSummary(e.getLocalizedMessage());
        fc.addMessage(null, message);                    
    }
    /**
     * Muestra el mensaje recibido como parametro
     * @param msg
     */
    protected void showMessage(String msg){
        FacesContext fc = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage(msg);
        message.setSeverity(FacesMessage.SEVERITY_ERROR);
        fc.addMessage(null, message);                    
    }    
    /**
     * Muestra un mensaje permosnalizado segun los parametros recibidos
     * @param msg
     * @param severity
     */
    protected void showMessage(String msg, javax.faces.application.FacesMessage.Severity severity){
        FacesContext fc = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage(msg);
        message.setSeverity(severity);
        fc.addMessage(null, message);                    
    }    
    /**
     * Muestra un mensaje permosnalizado segun los parametros recibidos y lo 
     * asocia al componente
     * @param keyMsg
     * @param severity
     * @param uiComponent
     */
    protected void showMessage(String keyMsg, javax.faces.application.FacesMessage.Severity severity, UIComponent uiComponent){
        String mensaje = StandarBean.getText(keyMsg);        
        FacesMessage msg =  new FacesMessage(severity,
                                            mensaje, null);            
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.addMessage(uiComponent.getClientId(ctx), msg);             
    }        
    /**
     * Muestra u oculta el popup recibido como parametro
     * @param popup
     * @param visible
     */
    protected void showPopup(RichPopup popup, boolean visible) {
        try {
          FacesContext context = FacesContext.getCurrentInstance();
          if (context != null && popup != null) {
            String popupId = popup.getClientId(context);
            if (popupId != null) {
              StringBuilder script = new StringBuilder();
              script.append("var popup = AdfPage.PAGE.findComponent('").append(popupId).append("'); ");
              if (visible) {
                script.append("if (!popup.isPopupVisible()) { ").append("popup.show();}");
              } else {
                script.append("if (popup.isPopupVisible()) { ").append("popup.hide();}");
              }
              ExtendedRenderKitService erks =
                Service.getService(context.getRenderKit(),
                                   ExtendedRenderKitService.class);
              erks.addScript(context, script.toString());
            }
          }
        } catch (Exception e) {
          this.showMessage( new IdeamException(e.getMessage(), e) );
        }
    }
    /**
     * Muestra u Oculta los filtros de los encabezados de la tabla
     * @param tabla
     * @param estado
     * @param menu
     */
    
    protected void estadoFiltros(RichTable tabla, boolean estado, RichCommandMenuItem menu){
        List columnas = tabla.getChildren();
        for(Object obj: columnas){
            RichColumn col = (RichColumn)obj;            
            UIComponent comp= col.getFacet("header");
            if (comp instanceof RichPanelGroupLayout){
                RichPanelGroupLayout panel = (RichPanelGroupLayout)comp;
                this.procesarLayoutFiltros(panel, estado);
            }else{
                System.out.println(comp.getClass().getName());   
            }                        
        }
        if(estado){
            menu.setText(this.getText("menu_filtrar_ocultar"));
        }else{
            menu.setText(this.getText("menu_filtrar_mostrar"));
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(menu);
        AdfFacesContext.getCurrentInstance().addPartialTarget(tabla);
    }
    /**
     * Muestra u oculta los filtros de los encabezados de una tabla que se 
     * encuentran en el encabezado de cada columna
     * @param panel
     * @param estado
     */
    private void procesarLayoutFiltros(RichPanelGroupLayout panel, boolean estado){        
        List<UIComponent> campos = panel.getChildren();
        for(UIComponent objCampo: campos){
            if (objCampo instanceof RichPanelGroupLayout){
                this.procesarLayoutFiltros((RichPanelGroupLayout)objCampo, estado);
            }
            if (objCampo instanceof RichInputText ){                        
                ((RichInputText)objCampo).setVisible(estado);
            }
            if (objCampo instanceof RichInputDate ){                        
                ((RichInputDate)objCampo).setVisible(estado);
            }     
            if (objCampo instanceof RichSelectBooleanCheckbox ){                        
                ((RichSelectBooleanCheckbox)objCampo).setVisible(estado);
            } 
            if (objCampo instanceof RichSelectOneChoice ){                        
                ((RichSelectOneChoice)objCampo).setVisible(estado);
            }                     
            if (objCampo instanceof RichSelectBooleanRadio ){                        
                ((RichSelectBooleanRadio)objCampo).setVisible(estado);
            }  
        }        
    }
    /**
     * Retorna el texto de la clave recibida como parametro en el archivo 
     * de mensajes del proyecto
     * @param key
     * @return
     */
    public static String getText(String key){
        return messages.getString(key);        
    }
    /**
     * Retorna el texto de la clave recibida como parametro en el archivo, 
     * de mensajes del proyecto reemplazando los caracteres ?% por los 
     * textos del arreglo recibido como parametro
     * @param key
     * @param extraInfo
     * @return
     */
    public static String getText(String key, String extraInfo[]){
        String texto = messages.getString(key);        
        int i = 0;
        while (texto.contains("%%") && i < extraInfo.length){
            texto = texto.replaceFirst("%%",extraInfo[i++]);
        }
        return texto;
    }    
    /**
     * Limpia todos los campos de los filtros de la tabla recibido como
     * parametro
     * @param tabla
     */
    
    protected void limpiarFiltros(RichTable tabla){
        List columnas = tabla.getChildren();
        for(Object obj: columnas){
            RichColumn col = (RichColumn)obj;            
            UIComponent comp= col.getFacet("header");
            if (comp instanceof RichPanelGroupLayout){
                RichPanelGroupLayout panel = (RichPanelGroupLayout)comp;
                this.procesarLayoutFiltros(panel);
            }else{
                System.out.println(comp.getClass().getName());   
            }                        
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(tabla);
    }
    /**
     * Limpia todos los campos de los filtros del panel recibido como
     * parametro
     * @param panel
     */
    private void procesarLayoutFiltros(RichPanelGroupLayout panel){        
        List<UIComponent> campos = panel.getChildren();
        for(UIComponent objCampo: campos){
            if (objCampo instanceof RichPanelGroupLayout){
                this.procesarLayoutFiltros((RichPanelGroupLayout)objCampo);
            }
            if (objCampo instanceof RichInputText ){                        
                //((RichInputText)objCampo).setValue(textoFiltrar);
                ((RichInputText)objCampo).setValue("");
            }
            if (objCampo instanceof RichInputDate ){                        
                ((RichInputDate)objCampo).setValue(null);
            }     
            if (objCampo instanceof RichSelectBooleanCheckbox ){                        
                ((RichSelectBooleanCheckbox)objCampo).setSelected(false);
            } 
            if (objCampo instanceof RichSelectOneChoice ){                        
                ((RichSelectOneChoice)objCampo).setValue(null);
            }                     
            if (objCampo instanceof RichSelectBooleanRadio ){                        
                ((RichSelectBooleanRadio)objCampo).setSelected(false);
            }  
        }        
    }  
    
    /**
     * Muestra u Oculta un componente popup
     * @param popup
     * @param mostrar
     */
    protected void mostrarOcultarPopup(RichPopup popup, boolean mostrar){
        String nombreComponente = 
            popup.getClientId(FacesContext.getCurrentInstance());
        ExtendedRenderKitService erks = 
            Service.getRenderKitService(FacesContext.getCurrentInstance(),ExtendedRenderKitService.class);
        StringBuilder script = new StringBuilder();
        if (mostrar){
            script.append("AdfPage.PAGE.findComponent(\"" + nombreComponente + "\").show()");
        }else{
            script.append("AdfPage.PAGE.findComponent(\"" + nombreComponente + "\").hide()");
        }
        erks.addScript(FacesContext.getCurrentInstance(), script.toString()); 
    }
    /**
     * Genera un PDF con la informacion recibida como parametro
     * Utiliza itext para generar el reporte
     * @param tablaDatos
     * @param attributes
     * @param data
     * @param reporte
     * @param showAllColumns true muestra todas las columnas de la tabla, false solo las visibles en la tabla
     * @throws IdeamException
     */
    protected void printTable(RichTable tablaDatos, 
                              String attributes[], 
                              List data,
                              ReporteInteface reporte,
                              boolean showAllColumns)throws IdeamException{        
        if (data==null || data.size()==0){
            throw new IdeamException(getText("reporte_vacio"));
        }
        
        MutableDynaClass dynaClass = new LazyDynaClass();
        
        // Determinar las columnas que se encuentran visibles para mostrarlas
        // en el reporte y definirlas en la clase dinamica        
        List listaColumnas = tablaDatos.getChildren();
        int indice = 0;
        if (listaColumnas!=null){
            Iterator it = listaColumnas.iterator();
            while(it.hasNext()){
                UIComponent comp = (UIComponent)it.next();
                if (comp instanceof RichColumn){
                    RichColumn columna = (RichColumn)comp;
                    if (showAllColumns || columna.isVisible()){
                        dynaClass.add(attributes[indice],
                                      java.lang.String.class);                           
                    }
                    indice++;
                }                
            }
        }
        
        // Validar que exista por lo menos una columna visible
        if (dynaClass.getDynaProperties()==null ||
            dynaClass.getDynaProperties().length == 0){
            throw new IdeamException(getText("reporte_no_columnas"));
        }
        Iterator it = data.iterator();
        List listaRegistros = new ArrayList();
        while(it.hasNext()){
            Object obj = it.next();
            LazyDynaBean dynaBean = new  LazyDynaBean(dynaClass);
            DynaProperty propiedades[] = dynaClass.getDynaProperties();
            for(int i=0; i<propiedades.length; i++){
                String nombreAtributo = propiedades[i].getName();                
                Object valor = Instrospector.getValue(obj, nombreAtributo);
                if (valor instanceof Calendar){
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy H:m:s");
                    String txtValor = sdf.format(((Calendar)valor).getTime());
                    dynaBean.set(nombreAtributo, txtValor);
                }else{
                    dynaBean.set(nombreAtributo, valor.toString());
                }
            }
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
    
    
    /**
     * Genera un reporte con la informacion recibida como parametro
     * utiliza jasperreports para generar el reporte.
     * @param reportName
     * @param data
     * @param parametros
     * @param reportType
     * @throws IdeamException
     */
    public void generateReport(String reportName, 
                               List data,
                               HashMap parametros,
                               int reportType)throws IdeamException{
        if (data==null || data.size()==0){
            throw new IdeamException(getText("reporte_vacio"));
        }
        
        parametros.put("p_logo", FacesUtils.getRealPath("/imgs/") +
                                 File.separator + "logoIdeam.jpg");
        parametros.put("SUBREPORT_DIR", FacesUtils.getRealPath("/reportes/") +
                                 File.separator );
        String fileCompilado = FacesUtils.getRealPath("/reportes/") +
                               File.separator + reportName;
                
        try {
            JasperReport jasperReport
                = (JasperReport)JRLoader.loadObject(fileCompilado);
            JasperPrint jasperPrint = null;
            ReporteDelegate rd = ReporteDelegate.getInstance();
            jasperPrint = rd.fillReportFromJasperDataSource(jasperReport,
                                                    parametros,
                                                    data);
            jasperPrint.setProperty(JRStyledText.PROPERTY_AWT_IGNORE_MISSING_FONT, 
                                    "true");                        
            ExportTypes exporter = new ExportTypeFactory().getExportType(reportType);                        
            byte contenido[] = exporter.export(jasperPrint);

            FacesUtils.setInSession("report-byte", contenido);        
            ExtendedRenderKitService service =
                Service.getRenderKitService(FacesContext.getCurrentInstance(),
                                            ExtendedRenderKitService.class);
            StringBuilder script = new StringBuilder();

            String url = FacesUtils.getUrl() + "/downloadservlet?showReport=" + 
                         exporter.getExtension() + "&report-name=nombre";        
            script.append("window.open('" + url + "','reporte');");        
            service.addScript(FacesContext.getCurrentInstance(), 
                              script.toString());             
        } catch (JRException e) {
            throw new IdeamException(e);
        }
    }
    /**
     * Genera un reporte con la informacion recibida como parametro
     * utiliza la conexion a la base de datos para generar el reporte.
     * @param reportName
     * @param parametros
     * @param reportType
     * @throws IdeamException
     */
    public void generateReport(String reportName, 
                               HashMap parametros,
                               int reportType)throws IdeamException{        
        parametros.put("p_logo", FacesUtils.getRealPath("/imgs/") +
                                 File.separator + "logoIdeam.jpg");
        parametros.put("SUBREPORT_DIR", FacesUtils.getRealPath("/reportes/") +
                                 File.separator );
        
        String fileCompilado = FacesUtils.getRealPath("/reportes/") +
                               File.separator + reportName;        
                
        try {
            JasperReport jasperReport
                = (JasperReport)JRLoader.loadObject(fileCompilado);
            JasperPrint jasperPrint = null;            
            ReporteDelegate rd = ReporteDelegate.getInstance();                        
            jasperPrint = rd.fillReportFromDataBase(jasperReport,
                                                    parametros);            
            jasperPrint.setProperty(JRStyledText.PROPERTY_AWT_IGNORE_MISSING_FONT, "true");            
            ExportTypes exporter = new ExportTypeFactory().getExportType(reportType);                        
            byte contenido[] = exporter.export(jasperPrint);

            FacesUtils.setInSession("report-byte", contenido);        
            ExtendedRenderKitService service =Service.getRenderKitService(FacesContext.getCurrentInstance(),ExtendedRenderKitService.class);
            StringBuilder script = new StringBuilder();            
            String url = FacesUtils.getUrl() + "/downloadservlet?showReport=" + exporter.getExtension() + "&report-name=nombre";                    
            script.append("window.open('" + url + "','reporte');");        
            service.addScript(FacesContext.getCurrentInstance(), script.toString());             
        } catch (JRException e) {
            throw new IdeamException(e);
        }
    }
    
    public void removeAllValidators(UIXInput comp){
        Validator validators[] = comp.getValidators();
        for(int i=0;i<=validators.length;i++){
            comp.removeValidator(validators[i]);
        }
    }
    
    // hugo 20150115
    protected void redirect(String urlDestino, boolean mismaVentana) {

            FacesContext ctx = FacesContext.getCurrentInstance();
            ExtendedRenderKitService erks =
            Service.getService(ctx.getRenderKit(), ExtendedRenderKitService.class);
            
            String url = "window.open('" + urlDestino + "','_self');";
            
            if (!mismaVentana)
                url = "window.open('" + urlDestino + "','_blank');";
                    
            erks.addScript(FacesContext.getCurrentInstance(), url);
            
    }
    protected void showReport(byte contenido[]){
        FacesUtils.setInSession("report-byte",contenido);
        ExtendedRenderKitService service =Service.getRenderKitService(FacesContext.getCurrentInstance(),ExtendedRenderKitService.class);
        StringBuilder script = new StringBuilder();
        String url = FacesUtils.getUrl() + "/downloadservlet?showReport=pdf&report-name=nombre";        
        script.append("window.open('" + url + "','reporte');");        
        service.addScript(FacesContext.getCurrentInstance(), script.toString());                     
    }    
    
    protected void showImage(byte contenido[]){
        FacesUtils.setInSession("content-img",contenido);
        ExtendedRenderKitService service =Service.getRenderKitService(FacesContext.getCurrentInstance(),ExtendedRenderKitService.class);
        StringBuilder script = new StringBuilder();
        String url = FacesUtils.getUrl() + "/downloadservlet?showImage=1&report-name=nombre";        
        script.append("window.open('" + url + "','reporte');");        
        service.addScript(FacesContext.getCurrentInstance(), script.toString());                     
    }   
    
    
  protected List getListaFuentes(Integer codigoSubzona)throws IdeamException{
    
      FuenteDelegate fd = FuenteDelegate.getInstance();                
      List lista = new ArrayList();//carga el selectItem.
      List result = null;
      result = fd.getListaFuentes(codigoSubzona);
      
      if(result!=null){
          Iterator it = result.iterator();
          while(it.hasNext()){
              FnttFuente result1 = (FnttFuente)it.next();
              SelectItem si = new SelectItem(result1, result1.getNombre());
              lista.add(si);
          }
      }                
      return lista;
  }
  
  protected List getListaFuentesIdeam(Integer codigoSubzona)throws IdeamException{
      
        FuenteDelegate fd = FuenteDelegate.getInstance();                
        List lista = new ArrayList();//carga el selectItem.
        List result = null;
        result = fd.getListaFuentesIdeam(codigoSubzona);
        
        if(result!=null){
            Iterator it = result.iterator();
            while(it.hasNext()){
                FnttFuente result1 = (FnttFuente)it.next();
                SelectItem si = new SelectItem(result1, result1.getNombre());
                lista.add(si);
            }
        }                
        return lista;
    }
    
    protected List getListaFuentesCriterios(CriteriosBusquedaTO criterios)throws IdeamException{     
        CalidadDelegate cd = CalidadDelegate.getInstance();                
        List lista = new ArrayList();//carga el selectItem.
        List result = null;
        result = cd.getFuentesCriterios(criterios);
        
        if(result!=null){
            Iterator it = result.iterator();
            while(it.hasNext()){
                FnttFuente result1 = (FnttFuente)it.next();
                SelectItem si = new SelectItem(result1, result1.getNombre());
                lista.add(si);
            }
        }                
        return lista;
    }
    
    protected List getlistaPuntosMonitoreoCriterios(CriteriosBusquedaTO criterios) throws IdeamException{
        CalidadDelegate cld = CalidadDelegate.getInstance();                
        List lista = new ArrayList();//carga el selectItem.
        List result = null;
        result = cld.getPuntosMonitoreoCriterios(criterios);
        
        if(result!=null){
            Iterator it = result.iterator();
            while(it.hasNext()){
                PuntoMonitoreo result1 = (PuntoMonitoreo)it.next();
                SelectItem si = new SelectItem(result1, result1.getNombre());
                lista.add(si);
            }
        }                
        return lista;      
    } 
    protected void guardarAuditoria(String operacion, String objeto,
                                    String nombreClase, Long idObjeto) {
        try {


            UsuarioConectadoTO usuarioConectado =
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

            Auditoria auditoria = new Auditoria();
            auditoria.setIdUsuario(usuarioConectado.getUsuario().getCodigo());
            auditoria.setOperacion(operacion);
            auditoria.setObjeto(objeto);
            auditoria.setMetodo(Thread.currentThread().getStackTrace()[1].getMethodName());
            auditoria.setClase(nombreClase);
            auditoria.setIdObjeto(idObjeto);

            AuditoriasDelegate audDelegate = AuditoriasDelegate.getInstance();
            audDelegate.setAuditoria(auditoria);

        } catch (Exception e) {

            System.out.println("No se pudo Almacenar la auditoria:");
            System.out.println(e.getMessage());
        }
    }
  protected List getListaTramos(Integer codigoFuente)throws IdeamException{
    
      FuenteDelegate fd = FuenteDelegate.getInstance();                
      List lista = new ArrayList();//carga el selectItem.
      List result = null;
      result = fd.getListaTramos(codigoFuente);
      
      if(result!=null){
          Iterator it = result.iterator();
          while(it.hasNext()){
              FnttTramo result1 = (FnttTramo)it.next();
              SelectItem si = new SelectItem(result1, result1.getNombre());
              lista.add(si);
          }
      }                
      return lista;
  }
  
  
    protected List cargarHorario()throws IdeamException{       
        List listaHorario = new ArrayList();
        
        SelectItem itemAm = new SelectItem("AM", "AM");
        listaHorario.add(itemAm);
        SelectItem itemPm = new SelectItem("PM", "PM");
        listaHorario.add(itemPm);  
        return listaHorario;
    }
    
    protected List cargarListaAutoridades()throws IdeamException{
      
        ParametrosDelegate pp = ParametrosDelegate.getInstance();                
        List lista = new ArrayList();//carga el selectItem.
        List result = null;
        result = pp.getAllAutoridades();
        
        if(result!=null){
            Iterator it = result.iterator();
            while(it.hasNext()){
                Autoridades result1 = (Autoridades)it.next();
                SelectItem si = new SelectItem(result1, result1.getSigla());
                lista.add(si);
            }
        }                
        return lista;
    }  
    
    protected List cargarListaAutoridadesConMuestras()throws IdeamException{
        CalidadDelegate cld = CalidadDelegate.getInstance();                 
        List lista = new ArrayList();//carga el selectItem.
        List result = null;
        result = cld.getAutoridadesConMuestras();
        
        if(result!=null){
            Iterator it = result.iterator();
            while(it.hasNext()){
                Autoridades result1 = (Autoridades)it.next();
                SelectItem si = new SelectItem(result1, result1.getSigla());
                lista.add(si);
            }
        }                
        return lista;
    }      
    
    
    protected List cargarSigno()throws IdeamException{       
        List listaSigno = new ArrayList();
        
        SelectItem itemMenor = new SelectItem("<", "<");
        listaSigno.add(itemMenor);
        SelectItem itemIgual = new SelectItem("=", "=");
        listaSigno.add(itemIgual);
        SelectItem itemMayor = new SelectItem(">", ">");
        listaSigno.add(itemMayor);  
        
        return listaSigno;
    }
    
    
    
    protected List cargarAliasParametros()throws IdeamException{       
        List listaParametros = new ArrayList();
      
        SelectItem item9=new SelectItem("376","acidez");listaParametros.add(item9);
        SelectItem item10=new SelectItem("377","acidez_intercambiable");listaParametros.add(item10);
        SelectItem item14=new SelectItem("378","alcalinidad");listaParametros.add(item14);
        SelectItem item15=new SelectItem("307","alcalinidad_total");listaParametros.add(item15);
        SelectItem item16=new SelectItem("388","aluminio");listaParametros.add(item16);
        SelectItem item21=new SelectItem("401","arsenico");listaParametros.add(item21);
        SelectItem item40=new SelectItem("384","bromo");listaParametros.add(item40);
        SelectItem item41=new SelectItem("414","bromuro");listaParametros.add(item41);
        SelectItem item45=new SelectItem("326","calcio");listaParametros.add(item45);
        SelectItem item46=new SelectItem("556","calcio_soluble");listaParametros.add(item46);
        SelectItem item58=new SelectItem("330","caudal");listaParametros.add(item58);
        SelectItem item62=new SelectItem("417","clorato");listaParametros.add(item62);
        SelectItem item63=new SelectItem("518","clordano");listaParametros.add(item63);
        SelectItem item64=new SelectItem("385","cloro");listaParametros.add(item64);
        SelectItem item73=new SelectItem("311","cloruro");listaParametros.add(item73);
        SelectItem item79=new SelectItem("358","coliformes_fecales");listaParametros.add(item79);
        SelectItem item80=new SelectItem("359","coliformes_totales");listaParametros.add(item80);
        SelectItem item81=new SelectItem("367","color");listaParametros.add(item81);
        SelectItem item82=new SelectItem("368","color_aparente");listaParametros.add(item82);
        SelectItem item83=new SelectItem("369","color_real");listaParametros.add(item83);
        SelectItem item88=new SelectItem("312","conductividad_electrica");listaParametros.add(item88);
        SelectItem item99=new SelectItem("458","demanda_bioquimica_de_oxigeno");listaParametros.add(item99);
        SelectItem item100=new SelectItem("323","demanda_quimica_de_oxigeno");listaParametros.add(item100);
        SelectItem item101=new SelectItem("370","densidad");listaParametros.add(item101);
        SelectItem item123=new SelectItem("360","escherichia_coli");listaParametros.add(item123);
        SelectItem item130=new SelectItem("419","fosfato");listaParametros.add(item130);
        SelectItem item132=new SelectItem("321","fosforo_disuelto_total");listaParametros.add(item132);
        SelectItem item188=new SelectItem("315","nitrato");listaParametros.add(item188);
        SelectItem item190=new SelectItem("314","nitrito");listaParametros.add(item190);
        SelectItem item192=new SelectItem("313","nitrogeno_amoniacal");listaParametros.add(item192);
        SelectItem item193=new SelectItem("435","nitrogeno_Kjeldahl_Total");listaParametros.add(item193);
        SelectItem item199=new SelectItem("322","oxigeno_disuelto");listaParametros.add(item199);
        SelectItem item212=new SelectItem("648","pH");listaParametros.add(item212);
        SelectItem item241=new SelectItem("324","solidos_disueltos_totales");listaParametros.add(item241);
        SelectItem item244=new SelectItem("325","solidos_suspendidos_totales");listaParametros.add(item244);
        SelectItem item245=new SelectItem("375","solidos_totales");listaParametros.add(item245);
        SelectItem item247=new SelectItem("579","sulfatos");listaParametros.add(item247);
        SelectItem item255=new SelectItem("566","temperatura");listaParametros.add(item255);
        SelectItem item269=new SelectItem("565","turbidez");listaParametros.add(item269);

        return listaParametros;
    }
    
    protected List cargarAliasParametrosIdeam()throws IdeamException{       
        List listaParametros = new ArrayList();
     
        SelectItem item1=new SelectItem("7","alcalinidad_total");listaParametros.add(item1);
        SelectItem item2=new SelectItem("339060","aluminio_biodisponible");listaParametros.add(item2);
        SelectItem item3=new SelectItem("310019","aluminio_total");listaParametros.add(item3);
        SelectItem item4=new SelectItem("339054","cadmio_biodisponible");listaParametros.add(item4);
        SelectItem item5=new SelectItem("89","cadmio_total");listaParametros.add(item5);
        SelectItem item6=new SelectItem("53","calcio");listaParametros.add(item6);
        SelectItem item7=new SelectItem("339064","cianuro_total");listaParametros.add(item7);
        SelectItem item8=new SelectItem("18","cloruro");listaParametros.add(item8);
        SelectItem item9=new SelectItem("339058","cobre_biodisponible");listaParametros.add(item9);
        SelectItem item10=new SelectItem("91","cobre_total");listaParametros.add(item10);
        SelectItem item11=new SelectItem("120009","coliformes_totales");listaParametros.add(item11);
        SelectItem item12=new SelectItem("20","conductividad_electrica");listaParametros.add(item12);
        SelectItem item13=new SelectItem("339057","cromo_biodisponible");listaParametros.add(item13);
        SelectItem item14=new SelectItem("323002","cromo_total");listaParametros.add(item14);
        SelectItem item15=new SelectItem("38","dbo5");listaParametros.add(item15);
        SelectItem item16=new SelectItem("39","demanda_quimica_oxigeno");listaParametros.add(item16);
        SelectItem item17=new SelectItem("11","dureza_total");listaParametros.add(item17);
        SelectItem item18=new SelectItem("120012","escherichia_coli");listaParametros.add(item18);
        SelectItem item19=new SelectItem("334002","fenoles");listaParametros.add(item19);
        SelectItem item20=new SelectItem("324008","fosforo_reactivo_disuelto");listaParametros.add(item20);
        SelectItem item21=new SelectItem("33","fosforo_total");listaParametros.add(item21);
        SelectItem item22=new SelectItem("339068","glifosato");listaParametros.add(item22);
        SelectItem item23=new SelectItem("331022","grasas_aceites");listaParametros.add(item23);
        SelectItem item24=new SelectItem("339061","hierro_biodisponible");listaParametros.add(item24);
        SelectItem item25=new SelectItem("16","hierro_total");listaParametros.add(item25);
        SelectItem item26=new SelectItem("109","magnesio_total");listaParametros.add(item26);
        SelectItem item27=new SelectItem("339062","manganeso_biodisponible");listaParametros.add(item27);
        SelectItem item28=new SelectItem("103","manganeso_total");listaParametros.add(item28);
        SelectItem item29=new SelectItem("339059","mercurio_biodisponible");listaParametros.add(item29);
        SelectItem item30=new SelectItem("101","mercurio_total");listaParametros.add(item30);
        SelectItem item31=new SelectItem("339071","mercurio_total_sedimentos");listaParametros.add(item31);
        SelectItem item32=new SelectItem("339055","niquel_biodisponible");listaParametros.add(item32);
        SelectItem item33=new SelectItem("93","niquel_total");listaParametros.add(item33);
        SelectItem item34=new SelectItem("24","nitrato");listaParametros.add(item34);
        SelectItem item35=new SelectItem("23","nitrito");listaParametros.add(item35);
        SelectItem item36=new SelectItem("22","nitrogeno_amoniacal");listaParametros.add(item36);
        SelectItem item37=new SelectItem("324014","nitrogeno_kjeldahl_total");listaParametros.add(item37);
        SelectItem item38=new SelectItem("37","oxigeno_disuelto");listaParametros.add(item38);
        SelectItem item39=new SelectItem("5","ph");listaParametros.add(item39);
        SelectItem item40=new SelectItem("339056","plomo_biodisponible");listaParametros.add(item40);
        SelectItem item41=new SelectItem("97","plomo_total");listaParametros.add(item41);
        SelectItem item42=new SelectItem("43","solidos_disueltos_totales");listaParametros.add(item42);
        SelectItem item43=new SelectItem("220012","solidos_sedimentables");listaParametros.add(item43);
        SelectItem item44=new SelectItem("44","solidos_suspendidos_totales");listaParametros.add(item44);
        SelectItem item45=new SelectItem("220014","solidos_totales");listaParametros.add(item45);
        SelectItem item46=new SelectItem("30","sulfato");listaParametros.add(item46);
        SelectItem item47=new SelectItem("322014","sulfuro");listaParametros.add(item47);
        SelectItem item48=new SelectItem("4","temperatura");listaParametros.add(item48);
        SelectItem item49=new SelectItem("3","turbidez");listaParametros.add(item49);
        SelectItem item50=new SelectItem("98","zinc_biodisponible");listaParametros.add(item50);
        SelectItem item51=new SelectItem("99","zinc_total");listaParametros.add(item51);

        return listaParametros;
        
    }
    
    protected List cargarHoras()throws IdeamException{       
        List listaHora = new ArrayList();
        String valor=null;
        for (int x=0;x<=12;x++){
            if(x<10){
                valor=String.valueOf("0"+x); 
            }else{
                  valor=String.valueOf(x); 
                }
            
            SelectItem itemhora = new SelectItem(x, valor);
            listaHora.add(itemhora);     
        }
         
        return listaHora;
    }
    
    protected List cargarMin()throws IdeamException{       
        List listaMin = new ArrayList();
        String valor=null;
        for (int x=0;x<=59;x++){
            if(x<10){
                valor=String.valueOf("0"+x); 
            }else{
                  valor=String.valueOf(x); 
                }
            SelectItem itemMin = new SelectItem(x, valor);
             
             listaMin.add(itemMin);     
        }
         
        return listaMin;
    }
    
    protected List cargarDiasMes()throws IdeamException{       
        List lista = new ArrayList();
        String valor=null;
        for (int x=1;x<=30;x++){
            SelectItem itemhora = new SelectItem(x, ""+x);
            lista.add(itemhora);     
        }
        
        return lista;
    }
    
   
    
    


    protected List getListaPuntosMuestras()throws IdeamException{
      
        CalidadDelegate cld = CalidadDelegate.getInstance();                
        List lista = new ArrayList();//carga el selectItem.
        List result = null;
        result = cld.getListaPuntosMuestra();
        
        if(result!=null){
            Iterator it = result.iterator();
            while(it.hasNext()){
                PuntoMonitoreo result1 = (PuntoMonitoreo)it.next();
                SelectItem si = new SelectItem(result1, result1.getNombre());
                lista.add(si);
            }
        }                
        return lista;
    }
    
  
    protected List getListaPuntosMuestras(Long autoridad)throws IdeamException{
      
        CalidadDelegate cld = CalidadDelegate.getInstance();                
        List lista = new ArrayList();//carga el selectItem.
        List result = null;
        result = cld.getListaPuntosMuestra(autoridad);
        
        if(result!=null){
            Iterator it = result.iterator();
            while(it.hasNext()){
                PuntoMonitoreo result1 = (PuntoMonitoreo)it.next();
                SelectItem si = new SelectItem(result1, result1.getNombre());
                lista.add(si);
            }
        }                
        return lista;
    }
    
    
  
  
  
    protected List getListaPuntosMediciones(Long idAutoridad)throws IdeamException{
      
        CalidadDelegate cld = CalidadDelegate.getInstance();                
        List lista = new ArrayList();//carga el selectItem.
        List result = null;
        result = cld.getPuntosMonitoreoMediciones(idAutoridad);
        
        if(result!=null){
            Iterator it = result.iterator();
            while(it.hasNext()){
                PuntoMonitoreo result1 = (PuntoMonitoreo)it.next();
                SelectItem si = new SelectItem(result1, result1.getNombre());
                lista.add(si);
            }
        }                
        return lista;
    }
    
    
    
    protected List getPuntosParametroMediciones(Long idparmetro, Long idAutoridad)throws IdeamException{
      
        CalidadDelegate cld = CalidadDelegate.getInstance();                
        List lista = new ArrayList();//carga el selectItem.
        List result = null;
        result = cld.getListaMedicionsPorParametro(idparmetro,idAutoridad);
        
        if(result!=null){
            Iterator it = result.iterator();
            while(it.hasNext()){
                PuntoMonitoreo result1 = (PuntoMonitoreo)it.next();
                SelectItem si = new SelectItem(result1, result1.getNombre());
                lista.add(si);
            }
        }                
        return lista;
    }
    
    
    
    
    
    protected List getListaParametrosPunto(Long idPunto)throws IdeamException{
      
        CalidadDelegate cld = CalidadDelegate.getInstance();                
        List lista = new ArrayList();//carga el selectItem.
        List result = null;
        result = cld.getParametrosxPuntoM(idPunto);
        
        if(result!=null){
            Iterator it = result.iterator();
            while(it.hasNext()){
                Lista result1 = (Lista)it.next();
                SelectItem si = new SelectItem(result1, result1.getValor());
                lista.add(si);
            }
        }                
        return lista;
    }

    protected List getListaParametrosPuntoCalidad(Integer idAutoridad, Long idPunto)throws IdeamException{
      
        CalidadDelegate cld = CalidadDelegate.getInstance();                
        List lista = new ArrayList();//carga el selectItem.
        List result = null;
        result = cld.getParametrosPuntoMCalidad(idAutoridad,idPunto);
        
        if(result!=null){
            Iterator it = result.iterator();
            while(it.hasNext()){
                Lista result1 = (Lista)it.next();
                SelectItem si = new SelectItem(result1, result1.getValor());
                lista.add(si);
            }
        }                
        return lista;
    }
    
    protected List getListaParametrosFuente(Long idFuente)throws IdeamException{
      
        CalidadDelegate cld = CalidadDelegate.getInstance();                
        List lista = new ArrayList();//carga el selectItem.
        List result = null;
        result = cld.getParametrosxFuente(idFuente);
        
        if(result!=null){
            Iterator it = result.iterator();
            while(it.hasNext()){
                Lista result1 = (Lista)it.next();
                SelectItem si = new SelectItem(result1, result1.getValor());
                lista.add(si);
            }
        }                
        return lista;
    }
    
    
    
    protected List getListaParametrosFuenteCalidad(Integer idAutoridad, Long idFuente)throws IdeamException{
      
        CalidadDelegate cld = CalidadDelegate.getInstance();                
        List lista = new ArrayList();//carga el selectItem.
        List result = null;
        result = cld.getParametrosFuenteCalidad(idAutoridad,idFuente);
        
        if(result!=null){
            Iterator it = result.iterator();
            while(it.hasNext()){
                Lista result1 = (Lista)it.next();
                SelectItem si = new SelectItem(result1, result1.getValor());
                lista.add(si);
            }
        }                
        return lista;
    }
    
         
    protected List getListaFuentesMuestras(Long idAutoridad)throws IdeamException{
        System.out.println("getListaFuentesMuestras----2-->"+idAutoridad);
        
        CalidadDelegate cld = CalidadDelegate.getInstance();                
        List lista = new ArrayList();//carga el selectItem.
        List result = null;
        result = cld.getFuentesMuestras(idAutoridad);
        
        if(result!=null){
            Iterator it = result.iterator();
            while(it.hasNext()){
                FnttFuente result1 = (FnttFuente)it.next();
                SelectItem si = new SelectItem(result1, result1.getNombre());
                lista.add(si);
            }
        }                
        return lista;
    }  
    
    
    protected List getListaFuentesTipo(Long idAutoridad)throws IdeamException{
        System.out.println("getListaFuentesTipo----2-->"+idAutoridad);
        
        CalidadDelegate cld = CalidadDelegate.getInstance();                
        List lista = new ArrayList();//carga el selectItem.
        List result = null;
        result = cld.getListaFuentesTipo(idAutoridad);
        
        if(result!=null){
            Iterator it = result.iterator();
            while(it.hasNext()){
                FnttFuente result1 = (FnttFuente)it.next();
                SelectItem si = new SelectItem(result1, result1.getNombre());
                lista.add(si);
            }
        }                
        return lista;
    }
    
    
    protected List getListaAnioMuestras(Long idfuente)throws IdeamException{
      
        CalidadDelegate cld = CalidadDelegate.getInstance();                
        List lista = new ArrayList();//carga el selectItem.
        List result = null;
        result = cld.getAnioMuestras(idfuente);
        
        if(result!=null){
            Iterator it = result.iterator();
            while(it.hasNext()){
                Object  result1 = (Object)it.next();
                String valor = result1.toString();
                if(valor.indexOf(".")!=-1){
                        valor = valor.substring(0,valor.indexOf("."));
                    }
                
               
                SelectItem si = new SelectItem(result1, valor);
                lista.add(si);
               
            }
        }                
        return lista;
    }
  
    protected List getListaAnioConcesiones(Long idAutoridad)throws IdeamException{
      
        UsuariosAguaDelegate usu = UsuariosAguaDelegate.getInstance();                
        List lista = new ArrayList();//carga el selectItem.
        List result = null;
        result = usu.getAnioConcesiones(idAutoridad);
        
        if(result!=null){
            Iterator it = result.iterator();
            while(it.hasNext()){
                Object  result1 = (Object)it.next();
                String valor = result1.toString();
                if(valor.indexOf(".")!=-1){
                        valor = valor.substring(0,valor.indexOf("."));
                    }
                
               
                SelectItem si = new SelectItem(result1, valor);
                lista.add(si);
               
            }
        }                
        return lista;
    }
  
  
    protected List getListaAnioPermisos(Long idAutoridad)throws IdeamException{
      
        UsuariosAguaDelegate usu = UsuariosAguaDelegate.getInstance();                
        List lista = new ArrayList();//carga el selectItem.
        List result = null;
        result = usu.getAnioPermisos(idAutoridad);
        
        if(result!=null){
            Iterator it = result.iterator();
            while(it.hasNext()){
                Object  result1 = (Object)it.next();
                String valor = result1.toString();
                if(valor.indexOf(".")!=-1){
                        valor = valor.substring(0,valor.indexOf("."));
                    }
                
               
                SelectItem si = new SelectItem(result1, valor);
                lista.add(si);
               
            }
        }                
        return lista;
    }
  
  
    protected List getListaAnioParametros(Long idParametro)throws IdeamException{
      
        CalidadDelegate cld = CalidadDelegate.getInstance();                
        List lista = new ArrayList();//carga el selectItem.
        List result = null;
        result = cld.getAnioParametros(idParametro);
        
        if(result!=null){
            Iterator it = result.iterator();
            while(it.hasNext()){
                Object  result1 = (Object)it.next();
                String valor = result1.toString();
                
                if(valor.indexOf(".")!=-1){
                        valor = valor.substring(0,valor.indexOf("."));
                    }
                SelectItem si = new SelectItem(result1, valor);
                lista.add(si);
               
            }
        }                
        return lista;
    }
  
  
    protected List cargarAutoridadesNodos()throws IdeamException{
        ParametrosDelegate pd = ParametrosDelegate.getInstance();        
                
        List lista = new ArrayList();//carga el selectItem.
        List result = null;
        result = pd.getAllAutoridadesNodos();
        
        if(result!=null){
            Iterator it = result.iterator();
            while(it.hasNext()){
                Autoridades result1 = (Autoridades)it.next();
                SelectItem si = new SelectItem(result1, result1.getSigla());
                lista.add(si);
            }
        }                
        return lista;
    }
    
  
    protected List cargarAutoridades()throws IdeamException{
        ParametrosDelegate pd = ParametrosDelegate.getInstance();        
                
        List lista = new ArrayList();//carga el selectItem.
        List result = null;
        result = pd.getAllAutoridades();
        
        if(result!=null){
            Iterator it = result.iterator();
            while(it.hasNext()){
                Autoridades result1 = (Autoridades)it.next();
                SelectItem si = new SelectItem(result1, result1.getSigla());
                lista.add(si);
            }
        }                
        return lista;
    }
    
    protected List cargarUsusariosLogin(Autoridades aut)throws IdeamException{
        UsuariosAguaDelegate pd = UsuariosAguaDelegate.getInstance();        
                
        List lista = new ArrayList();//carga el selectItem.
        List result = null;
        result = pd.getUsuariosLogin(aut);
        
        if(result!=null){
            Iterator it = result.iterator();
            while(it.hasNext()){
                Lista result1 = (Lista)it.next();
                SelectItem si = new SelectItem(result1, result1.getValor());
                lista.add(si);
            }
        }                
        return lista;
    }
  
  
  
    protected List getListaAnioParametroFuentes(Long idParametro, Long idFuente)throws IdeamException{
      
        CalidadDelegate cld = CalidadDelegate.getInstance();                
        List lista = new ArrayList();//carga el selectItem.
        List result = null;
        result = cld.getAnioParametrosFuente(idParametro,idFuente);
        
        if(result!=null){
            Iterator it = result.iterator();
            while(it.hasNext()){
                Object  result1 = (Object)it.next();
                String valor = result1.toString();
                if(valor.indexOf(".")!=-1){
                
                valor = valor.substring(0,valor.indexOf("."));
                }
                SelectItem si = new SelectItem(result1, valor);
                lista.add(si);
               
            }
        }                
        return lista;
    }
    
           
    protected List getListaAniosFuenteParametrosCalidad(Long idParametro, Long idFuente, Integer IdAutoridad)throws IdeamException{
      
        CalidadDelegate cld = CalidadDelegate.getInstance();                
        List lista = new ArrayList();//carga el selectItem.
        List result = null;
        result = cld.getAniosFuenteParametrosCalidad(idParametro,idFuente,IdAutoridad);
        
        if(result!=null){
            Iterator it = result.iterator();
            while(it.hasNext()){
                Object  result1 = (Object)it.next();
                String valor = result1.toString();
                if(valor.indexOf(".")!=-1){
                
                valor = valor.substring(0,valor.indexOf("."));
                }
                SelectItem si = new SelectItem(result1, valor);
                lista.add(si);
               
            }
        }                
        return lista;
    }   
    

    protected List getListaAniosFuenteCalidad( Long idFuente, Integer IdAutoridad)throws IdeamException{
      
        CalidadDelegate cld = CalidadDelegate.getInstance();                
        List lista = new ArrayList();//carga el selectItem.
        List result = null;
        result = cld.getAniosFuenteCalidad(idFuente,IdAutoridad);
        
        if(result!=null){
            Iterator it = result.iterator();
            while(it.hasNext()){
                Object  result1 = (Object)it.next();
                String valor = result1.toString();
                if(valor.indexOf(".")!=-1){
                
                valor = valor.substring(0,valor.indexOf("."));
                }
                SelectItem si = new SelectItem(result1, valor);
                lista.add(si);
               
            }
        }                
        return lista;
    } 
    
    protected List getListaPorCategoria(Long idCategoria)throws IdeamException{
        System.out.println("getListaPorCategoria----2-->"+idCategoria);
        
        CalidadDelegate cld = CalidadDelegate.getInstance();                
        List lista = new ArrayList();//carga el selectItem.
        List result = null;
        result = cld.getListaPorCategoria(idCategoria);
        
        if(result!=null){
            Iterator it = result.iterator();
            while(it.hasNext()){
                Lista result1 = (Lista)it.next();
                SelectItem si = new SelectItem(result1, result1.getValor());
                lista.add(si);
            }
        }                
        return lista;
    }
    
    protected List cargarMes()throws IdeamException{       
        List listaMes = new ArrayList();
        
        SelectItem item1 = new SelectItem("1", "ENERO");
        listaMes.add(item1);
        SelectItem item2 = new SelectItem("2",  "FEBRERO");
        listaMes.add(item2);  
        SelectItem item3 = new SelectItem("3", "MARZO");
        listaMes.add(item3);
        SelectItem item4 = new SelectItem("4", "ABRIL");
        listaMes.add(item4);  
        SelectItem item5 = new SelectItem("5", "MAYO");
        listaMes.add(item5);
        SelectItem item6 = new SelectItem("6", "JUNIO");
        listaMes.add(item6);  
        SelectItem item7 = new SelectItem("7", "JULIO");
        listaMes.add(item7);
        SelectItem item8 = new SelectItem("8", "AGOSTO");
        listaMes.add(item8);  
        SelectItem item9 = new SelectItem("9", "SEPTIEMBRE");
        listaMes.add(item9);
        SelectItem item10 = new SelectItem("10", "OCTUBRE");
        listaMes.add(item10);  
        SelectItem item11 = new SelectItem("11", "NOVIEMBRE");
        listaMes.add(item11);
        SelectItem item12 = new SelectItem("12", "DICIEMBRE");
        listaMes.add(item12); 
        
        return listaMes;
    }
                                              
    protected List cargarVariableOferta()throws IdeamException{       
        List lista = new ArrayList();
        
        SelectItem item4 = 
            new SelectItem(ConstantesOferta.NVL_MDO_DRO_NVQ, 
                                          "Niveles Medios Diarios");
        lista.add(item4);
        
        SelectItem item5 =
            new SelectItem(ConstantesOferta.CDL_MDO_DRO_NVQ, 
                           "Caudales Medios Diarios");
        lista.add(item5);
        SelectItem item11 =
            new SelectItem(ConstantesOferta.CDL_MNM_DRO_NVQ, 
                           "Caudal Minimo Diario");
        lista.add(item11);

        SelectItem item12 =
            new SelectItem(ConstantesOferta.CDL_MXM_DRO_NVQ, 
                           "Caudal Maximo Diario");
        lista.add(item12);
        
        SelectItem item6 =
            new SelectItem(ConstantesOferta.SDM_CNT_MDO_DRO_SDM, 
                           "Sedimentos Concentración Medios Diarios");
        lista.add(item6);
        
        SelectItem item15 =
            new SelectItem(ConstantesOferta.SDM_CNT_MDO_SPF_DRO,
                           "Sedimentos Concentracion Media Superficial Diario");
        lista.add(item15);
        
        SelectItem item17 =
            new SelectItem(ConstantesOferta.SDM_TRN_TTL_DRO_SDM,
                           "Sedimentos transporte Total Diario");
        lista.add(item17);

        
        SelectItem item1 = 
            new SelectItem(ConstantesOferta.NVL_MDO_MNS_NVQ, 
                                          "Niveles Medios Mensuales");
        lista.add(item1);
        SelectItem item7 =
            new SelectItem(ConstantesOferta.NVL_MNM_ABS_MNS_NVQ, 
                           "Nivel Minimo Absoluto Mensual");
        lista.add(item7);

        SelectItem item8 =
            new SelectItem(ConstantesOferta.NVL_MNM_MDO_MNS_NVQ, 
                           "Nivel Minimo Medio Mensual");
        lista.add(item8);

        SelectItem item9 =
            new SelectItem(ConstantesOferta.NVL_MXM_ABS_MNS_NVQ, 
                           "Nivel Maximo Absoluto Mensual");
        lista.add(item9);
        
        SelectItem item10 =
            new SelectItem(ConstantesOferta.NVL_MXM_MDO_MNS_NVQ,
                           "Nivel Maximo Medio Mensual");
        lista.add(item10);
        
        SelectItem item2 = 
            new SelectItem(ConstantesOferta.CDL_MDO_MNS_NVQ,  
                                          "Caudales Medios Mensuales");
        lista.add(item2);  
        
        SelectItem item13 =
            new SelectItem(ConstantesOferta.CDL_MXM_MDO_MNS_NVQ,
                           "Caudal Máximo Mensual");
        lista.add(item13);

        SelectItem item14 =
            new SelectItem(ConstantesOferta.CDL_MNM_MDO_MNS_NVQ,
                           "Caudal Minimo Mensual");
        lista.add(item14);
        
        SelectItem item3 = 
            new SelectItem(ConstantesOferta.SDM_CNT_MDO_MNS_SDM, 
                                          "Sedimentos Concentración Medios Mensuales");
        lista.add(item3);

        SelectItem item16 =
            new SelectItem(ConstantesOferta.SDM_TRN_MXMD_MNS_SDM,
                           "Sedimentos Transporte Maximo Mensual");
        lista.add(item16);

        SelectItem item18 =
            new SelectItem(ConstantesOferta.SDM_TRN_MXM_TTL_MNS,
                           "Sedimentos Transporte Máximos Mensuales");
        lista.add(item18);

        SelectItem item19 =
            new SelectItem(ConstantesOferta.SDM_CNT_MNM_MNS_SDM,
                           "Sedimentos Concentracion Minima Mensual");
        lista.add(item19);

        SelectItem item20 =
            new SelectItem(ConstantesOferta.SDM_CNT_MXM_MNS_SDM,
                           "Sedimentos Concentracion Maxima Mensual");
        lista.add(item20);

        SelectItem item21 =
            new SelectItem(ConstantesOferta.SDM_TRN_MDO_MNS_SDM,
                           "Sedimentos Transporte Medio Mensual");
        lista.add(item21);

        SelectItem item22 =
            new SelectItem(ConstantesOferta.SDM_TRN_TTL_MNS_SDM,
                           "Sedimentos Transporte Total Mensual");
        lista.add(item22);
    
        
        return lista;
    }
    
    protected List cargarVariableDemanda()throws IdeamException{       
        List lista = new ArrayList();
        
        SelectItem item1 = 
            new SelectItem(ConstantesDemanda.CARGO_FIJO, "Cargo fijo");
        lista.add(item1);
        SelectItem item2 = 
            new SelectItem(ConstantesDemanda.CARGO_BASICO, "Cargo básico");
        lista.add(item2);  
        SelectItem item3 = 
            new SelectItem(ConstantesDemanda.CARGO_COMPLEMENTARIO, 
                                            "Cargo complementario");
        lista.add(item3);
        SelectItem item4 = 
            new SelectItem(ConstantesDemanda.CARGO_SUNTUARIO, "Cargo suntuario");
        lista.add(item4);     
    
        
        return lista;
    }
    
    protected List cargarVariableOferta(List<String> variables)throws IdeamException{       
        List lista = new ArrayList();
        List<String> listaAux = new ArrayList();
        
        if(variables != null){
            for(String var : ConstantesOferta.variablesSerieOrdenada){
                if(variables.contains(var)){
                    listaAux.add(var);
                }
            }
            
            for(String variable : listaAux){
                SelectItem item = 
                    new SelectItem(variable, 
                                   ConstantesOferta.variablesSerie.get(variable));
                lista.add(item);
            }
        }
        
        return lista;
    }
    
    protected List getListaFuentes(Autoridades autoridad)throws IdeamException{
      
        FuenteDelegate fd = FuenteDelegate.getInstance();                
        List lista = new ArrayList();//carga el selectItem.
        List result = null;
        result = fd.getAllFuentes(autoridad);
        
        if(result!=null){
            Iterator it = result.iterator();
            while(it.hasNext()){
                FnttFuente result1 = (FnttFuente)it.next();
                SelectItem si = new SelectItem(result1, 
                                               "["+((result1.getCodigoCuencaAA()!=null)
                                               ?result1.getCodigoCuencaAA()
                                               : "")+"] - "+result1.getNombre());
                lista.add(si);
            }
        }                
        return lista;
    }

    protected List getListaFuentesConflicto(Long idAutoridad) throws IdeamException {
        System.out.println("getListaFuentesConflicto------>" + idAutoridad);

        FuenteDelegate fd = FuenteDelegate.getInstance();
        
        List lista = new ArrayList(); //carga el selectItem.
        List result = null;
        result = fd.getFuentesConflicto(idAutoridad);

        if (result != null) {
            Iterator it = result.iterator();
            while (it.hasNext()) {
                FnttFuente result1 = (FnttFuente)it.next();
                SelectItem si = new SelectItem(result1, result1.getNombre());
                lista.add(si);
            }
        }
        return lista;
    }

    protected List getListaConflictosXFuente(Long idFuente) throws IdeamException {
        System.out.println("getListaConflictosXFuente------>" + idFuente);

        FuenteDelegate fd = FuenteDelegate.getInstance();
        
        List lista = new ArrayList(); //carga el selectItem.
        List result = null;
        result = fd.getConflictosXFuente(idFuente);

        if (result != null) {
            Iterator it = result.iterator();
            while (it.hasNext()) {
                FnttConflicto result1 = (FnttConflicto)it.next();
                SelectItem si = new SelectItem(result1, result1.getNombre());
                lista.add(si);
            }
        }
        return lista;
    }
    
}
