package co.gov.ideam.sirh.view;

import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.util.IdeamException;

import java.io.File;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.el.ELContext;
import javax.el.ExpressionFactory;

import javax.el.ValueExpression;

import javax.faces.FactoryFinder;
import javax.faces.application.Application;
import javax.faces.application.ApplicationFactory;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.faces.el.ValueBinding;

import javax.faces.model.SelectItem;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * Utilidades Genericas de Faces
 */
public class FacesUtils {
    /**
     * Retorna un arrgle de SelectItem
     * @param listaOpciones
     * @return
     * @throws FenixException
     */
    /*
    public static List loadComboBox(List listaOpciones)throws FenixException{
        List listaItems = new ArrayList();                
        if (listaOpciones!=null){
            Iterator it = listaOpciones.iterator();
            while(it.hasNext()){
                SioDominios d = (SioDominios)it.next();
                SelectItem item = new SelectItem(d, d.getDescripcion());
                listaItems.add(item);
            }
        }
        return listaItems;
    } */     
    /**
     * Retorna un arrgle de SelectItem
     * @param dominio
     * @param cd
     * @return
     * @throws FenixException
     *//*
    public static List loadComboBox(String dominio, CatalogoDelegate cd)throws FenixException{
        List listaItems = new ArrayList();
        List listaOpciones = cd.getListaDominios(dominio, FacesUtils.getAuditoria());
        Collections.sort(listaOpciones, new SioDominioComparator());
        if (listaOpciones!=null){
            Iterator it = listaOpciones.iterator();
            while(it.hasNext()){
                SioDominios d = (SioDominios)it.next();
                SelectItem item = new SelectItem(d, d.getDescripcion());
                listaItems.add(item);
            }
        }
        return listaItems;
    }    
    */
    /**
     * Cada vez que se cambia de opcion se establece el bean que esta siendo
     * ejecutado y se borra el anterior de la session
     * @param className
     * @param nombreAuditoria
     * @param modulo
     */
    public static void setActiveBean(String className, String nombreAuditoria, Class modulo){        
        HttpSession ref=(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        Object activeBean = ref.getAttribute("activeBean");
        if (activeBean!=null && className==null){
            removeManagedBeanFromSession((String)activeBean);
            return;
        }
        if (activeBean!=null && !((String)activeBean).equalsIgnoreCase(className)){
            removeManagedBeanFromSession((String)activeBean);
        }                
        ref.setAttribute("activeBean", className);                
    }
    
    /**
     * Retorna el bean que esta siendo ejecutado.
     * @param className
     * @param nombreAuditoria
     * @param modulo
     */
    public static Object getActiveBean(){  
        HttpSession ref=(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        Object activeBean = ref.getAttribute("activeBean");
        Object beanClass = null;
        if(activeBean!=null){
            beanClass = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(activeBean);
        }
        return beanClass;             
    }
    /**
     * Retorna el contexto
     * @return
     */
    public static String getExternalContext(){
        return FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
    }
    /**
     * Retorna un bean segun el nombre del mismo
     * @param beanName
     * @return
     */
    public static Object getManagedBeanValue(String beanName) {
        StringBuffer buff = new StringBuffer("#{");
        buff.append(beanName);
        buff.append("}");
        return resolveExpression(buff.toString());
    }
    
    /**
     * Carga el bean recibido como parametro, asociandolo con el nombre
     * que tambien se recibe como parametro
     * @param beanName
     * @param bean
     */
    public static void setManagedBeanValue(String beanName, Object bean) {
        StringBuffer buff = new StringBuffer("#{");
        buff.append(beanName);
        buff.append("}");
        
        String expression = new String(buff);
        
        FacesContext ctx = FacesContext.getCurrentInstance();
        Application app = ctx.getApplication();
        ExpressionFactory elFactory = app.getExpressionFactory();
        ELContext elContext = ctx.getELContext();
        ValueExpression valueExp = elFactory.createValueExpression(elContext,expression,Object.class);        
        valueExp.setValue(elContext, bean);    
    }
    
    /**
     * Retorna una expression
     * @param expression
     * @return
     */
    public static Object resolveExpression(String expression) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        Application app = ctx.getApplication();
        ExpressionFactory elFactory = app.getExpressionFactory();
        ELContext elContext = ctx.getELContext();
        ValueExpression valueExp = elFactory.createValueExpression(elContext,expression,Object.class);        
        return valueExp.getValue(elContext);
    } 
    
    
    /**
     * Carga un objeto en session
     * @param name
     * @param obj
     */
    public static void setInSession(String name, Object obj){
        FacesContext ctx = FacesContext.getCurrentInstance();        
        ctx.getExternalContext().getSessionMap().put(name, obj);
    }
    /**
     Borra un objeto de la session
     * @param name
     */
    public static void removeFromSession(String name){
        FacesContext ctx = FacesContext.getCurrentInstance();
        Map map = ctx.getExternalContext().getSessionMap();
        map.remove(name);
    }
    /**
     * Retorna un objeto desde la session
     * @param name
     * @return
     */
    public static Object getFromSession(String name){
        FacesContext ctx = FacesContext.getCurrentInstance();
        Map map = ctx.getExternalContext().getSessionMap();
        return ctx.getExternalContext().getSessionMap().get(name);
    }
    /**
     * Retorna la direccion ip desde la cual se esta ejecutando el aplicativo
     * @return
     */
    public static String getRemoteAddress(){
        String clientIpAddress = 
            ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteAddr();
        return clientIpAddress;            
    }
    /**
     * Retorna la Url del sistema compuesta por http://ip:puerto/conexto
     * @return
     */
    public static String getUrl(){
        //System.out.println ("1" + ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getPathInfo());
        //System.out.println ("2" + ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getContextPath());
        //System.out.println ("3" + ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRequestURI());
        String requestUrl = ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRequestURL().toString();
        String servletPath = ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getServletPath();
        // Cortar el url a partir del servletpat
        int pos = requestUrl.indexOf(servletPath);
        String fullUrl = requestUrl.substring(0,pos);        
        return fullUrl;
    }
    /**
     * Invalida la session actual
     */
    public static void invalidateSession(){
        ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletResponse response = (HttpServletResponse)ectx.getResponse();
        HttpSession session = (HttpSession)ectx.getSession(false);
        session.invalidate(); 
    }    
    /**
     * Remueve de session el bean asoicado al nombre recibido como parametro
     * @param beanName
     */
    public static void removeManagedBeanFromSession(String beanName) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(beanName);
    }
    
    /**
     * Concatena #{} con el Expresion lenguage.
     * @param value Valor a asignar al atributo value.
     * @return expresi?n concatenada.
     */
    private static String getJsfEl(String value) {
        return "#{" + value + "}";
    }
    
    @SuppressWarnings("deprecation")
    private static ValueBinding getValueBinding(String el) {
        return getApplication().createValueBinding(el);
    }
    /**
     * Retorna un objeto que representa la aplicacion
     * @return
     */
    private static Application getApplication() {
        ApplicationFactory appFactory = (ApplicationFactory) FactoryFinder.getFactory(FactoryFinder.APPLICATION_FACTORY);
        return appFactory.getApplication();
    }    
    /**
     * Retorna el path real correspondiente al directorio recibido como
     * parametro
     * @param directorio
     * @return
     */
    public static String getRealPath(String directorio){
        ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext(); 
        String deploymentDirectoryPath = ctx.getRealPath(directorio);
        return deploymentDirectoryPath;
    }
    
    public static String getFolferFiles(){
        if (System.getProperty("folferCne")==null){
            // Cargar el realpath 
            String path = FacesUtils.getRealPath("/imgs/");
            int pos = path.indexOf(File.separator + "tmp" + File.separator);
            if (pos > 0){
                path = path.substring(0, pos);                
            }else{
                path = "./";
            }
            System.setProperty("folferCne", path);
        }
        return System.getProperty("folferCne");
    }    
    
    /**
     * Redirecciona a la url especificada a traves del sendRedirect del
     * {@link HttpServletResponse}
     * 
     * @param url
     * @see HttpServletResponse#sendRedirect(String)
     * */
    public static void sendRedirect(String url) {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) context
                        .getExternalContext().getResponse();
        try {
            response.sendRedirect(url);
            context.responseComplete();
        } catch (IOException e) {
              e.getStackTrace();
        }
    }
    
    /**
     * Envia a la url especificada a traves del forward del
     * {@link HttpServletResponse}
     * 
     * @param url
     * @see HttpServletResponse#sendRedirect(String)
     * */
    public static void forward(String url){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext ec = facesContext.getExternalContext();
        try {
            ec.dispatch(url);
        } catch (IOException e) {
            e.getStackTrace();
        }

    }
    
    
}
