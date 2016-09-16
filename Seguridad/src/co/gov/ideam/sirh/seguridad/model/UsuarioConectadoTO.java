package co.gov.ideam.sirh.seguridad.model;


import java.io.Serializable;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/**
 * Representa un usuario que ha ingresado al sistema y permite encapsular
 * en esta clase informacion correspondiente al usuario y su session dentro
 * del aplicativo
 */
public class UsuarioConectadoTO implements Serializable{
    /**
     * Usuario conectado
     */
    private UsuarioVO usuario;
    /**
     * Direccion IP desde la cual se conecta
     */
    private String remoteIP;    
    /**
     * Lista de opciones de menu para mostrar en la pagina principal
     */
    private List menu;
    /**
     * Opciones de menu autorizadas
     */
    private Map menuAutorizado = new HashMap();
    /**
     * Opciones o botones autorizados para el usurio segun sus perfiles asociados
     */
    private Map opcionesAutorizadas = new HashMap();

    public UsuarioVO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioVO usuario) {
        this.usuario = usuario;
    }

    public List getMenu() {
        return menu;
    }

    public void setMenu(List menu) {
        this.menu = menu;
    }

    public String getRemoteIP() {
        return remoteIP;
    }

    public void setRemoteIP(String remoteIP) {
        this.remoteIP = remoteIP;
    }
    /**
     * Retorna true o false si el nombre de la clase se cuentra dentro del 
     * atributo menu del usaurio
     * @param pagina
     * @return
     */
    public boolean isEnabled(String pagina){                
        return this.menuAutorizado!=null && this.menuAutorizado.containsKey(pagina);        
    }
    /**
     * Retorna true o false si el  nombre del boton esta asociado al usuario
     * para mostrarlo en la pagina
     * @param pagina
     * @param opcion
     * @return
     */
    public boolean isEnabled(String pagina, String opcion){                      
        String texto = pagina + "_" + opcion;                
        boolean habilitado = this.opcionesAutorizadas!=null && this.opcionesAutorizadas.containsKey(texto);        
        return habilitado;
    }

    public Map getMenuAutorizado() {
        return menuAutorizado;
    }

    public void setMenuAutorizado(Map menuAutorizado) {
        this.menuAutorizado = menuAutorizado;
    }

    public Map getOpcionesAutorizadas() {
        return opcionesAutorizadas;
    }

    public void setOpcionesAutorizadas(Map opcionesAutorizadas) {
        this.opcionesAutorizadas = opcionesAutorizadas;
    }
    
    public void addOpcion(String texto){
        this.opcionesAutorizadas.put(texto,texto);
    }
    
    public void addMenu(String texto){
        this.menuAutorizado.put(texto, texto);
    }
}
