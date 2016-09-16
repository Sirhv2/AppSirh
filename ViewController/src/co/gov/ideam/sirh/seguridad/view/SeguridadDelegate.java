package co.gov.ideam.sirh.seguridad.view;

import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.seguridad.business.SeguridadEJB;
import co.gov.ideam.sirh.seguridad.model.MenuVO;
import co.gov.ideam.sirh.seguridad.model.OpcionVO;
import co.gov.ideam.sirh.seguridad.model.PerfilVO;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.seguridad.model.UsuarioVO;
import co.gov.ideam.sirh.servlet.ServletLocator;
import co.gov.ideam.sirh.util.IdeamException;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Clase que permite conectar los metodos del EJB del modulo con los
 * Bean de la capa de presentacion
 */
public class SeguridadDelegate{
    /**
     * Referencia al EJB de Seguridad
     */
    private static SeguridadEJB seguridadService = null;    
    /**
    * Utilizada para implementar singleton
    */
    private static SeguridadDelegate instance;
    /**
     * Retorna una instancia a la clase
     * @return
     */
    public static SeguridadDelegate getInstance() throws IdeamException {
        if (instance==null){
            instance = new SeguridadDelegate();
        }
        return instance;
    }
    /**
     * Borra el usuario recibido como parametro
     * @param usuario
     * @throws IdeamException
     */
    public void deleteUser(UsuarioVO usuario)throws IdeamException{
        seguridadService.deleteUser(usuario);        
    }
    
    public void restartPassword(UsuarioVO usuario)throws IdeamException{        
        seguridadService.restartPassword(usuario);
    }
    public void savePassword(UsuarioVO usuario, String password)throws IdeamException{        
        seguridadService.savePassword(usuario, password);
    }
    public void logout()throws IdeamException{
        
    }
    
    public HashMap getMenuAutorizado(UsuarioVO usuario)throws IdeamException{
        HashMap map = seguridadService.getMenuAutorizado(usuario);        
        return map;
    }
    /**
     * Inserta o actualiza la informacion de un usuario registrado en el 
     * sistema
     * @param usuario
     * @throws IdeamException
     */
    public void updateUser(UsuarioVO usuario)throws IdeamException{                
        seguridadService.updateUser(usuario);        
    }
    /**
     * Valida el usuario y clave recibidos como parametro 
     * @param login
     * @param clave
     * @return
     * @throws IdeamException
     */
    public boolean validateUser(String login, String clave)throws IdeamException{        
        boolean valido = seguridadService.validateUser(login, clave);
        return valido;
    }
    /**
     * Registra en session la informacion del usuario registrado
     * @param loginUser
     * @throws IdeamException
     */
    public UsuarioConectadoTO loginUser(String loginUser)throws IdeamException{        
        UsuarioConectadoTO usuario= seguridadService.login(loginUser);        
        return usuario;
    }
    
    /**
     * Retorna una lista con todos los usuarios registrados en el sistema
     * @return
     * @throws IdeamException
     */
    public List getUsuarios(Autoridades autoridad)throws IdeamException{
        List lista = seguridadService.getUsuarios(autoridad);        
        return lista;
    }
    
    /**
     * Retorna una lista con los usuarios correspondientes a los parametros
     * recibidos
     * @param usuario
     * @param useEstado
     * @param fechaFinalIngresoExistoso
     * @param fechaFinalIngresoNoExitoso
     * @return
     * @throws IdeamException
     */
    public List getUsuarios(UsuarioVO usuario, 
                            boolean useEstado,
                            Calendar fechaFinalIngresoExistoso,                            
                            Calendar fechaFinalIngresoNoExitoso)throws IdeamException{
        List lista = seguridadService.getUsuarios(usuario,
                                                  useEstado,
                                                  fechaFinalIngresoExistoso,
                                                  fechaFinalIngresoNoExitoso);        
        return lista;
    }
    /**
     * Retorna el usaurio cuyo email corresponde al recibido como
     * parametro
     * @param email
     * @return
     * @throws IdeamException
     */
    public UsuarioVO getUsuarioByEmail(String email)throws IdeamException{
        UsuarioVO usuario = seguridadService.getUsuarioByEmail(email);
        return usuario;
    }
    /**
     * Retorna el usuario correspondiente al login recibido como parametro
     * @param login
     * @return
     * @throws IdeamException
     */
    public UsuarioVO getUsuario(String login)throws IdeamException{
        UsuarioVO usuario = seguridadService.getUsuario(login);
        return usuario;
    }
    
    /**
     * Retorna una lista con todos los perfiles registrados en el
     * sistema
     * @return
     * @throws IdeamException
     */
    public List getAllPerfiles()throws IdeamException{
        List lista = seguridadService.getAllPerfiles();
        return lista;
    }
    /**
     * Borra el perfil recibido como parametro
     * @param perfil
     * @throws IdeamException
     */
    public void deletePerfil(PerfilVO perfil)throws IdeamException{
        seguridadService.deletePerfil(perfil);
    }    
    /**
     * Retorna el perfil correspondiente al codigo recibido como parametro
     * @param codigo
     * @return
     * @throws IdeamException
     */
    public PerfilVO getPerfil(long codigo)throws IdeamException{
        PerfilVO perfil = seguridadService.getPerfil(codigo);
        return perfil;
    }
    
    public PerfilVO getPerfilUsuario(long codigo)throws IdeamException{
    PerfilVO perfil = seguridadService.getPerfilUsuario(codigo);
    return perfil;
    }
    /**
     * Inserta o actualiza el perfil recibido como parametro
     * @param perfil
     * @throws IdeamException
     */
    public void updatePerfil(PerfilVO perfil)throws IdeamException{
        seguridadService.updatePerfil(perfil);
    }
    /**
     * Retorna una lista con todos los perfiles asociados al usuario
     * recibido como parametro
     * @param usuario
     * @return
     * @throws IdeamException
     */
    public List getPerfiles(UsuarioVO usuario)throws IdeamException{
        List lista = seguridadService.getPerfiles(usuario);
        return lista;
    }
    /**
     * Retorna una lista con todas las opciones de menu registradas en el
     * sistema
     * @return
     * @throws IdeamException
     */
    public List getAllMenu()throws IdeamException{
        List lista = seguridadService.getAllMenu();
        return lista;
    }
    /**
     * Retirna un menu que controla la pagina recibida como parametro
     * @param pagina
     * @return
     * @throws IdeamException
     */
    public MenuVO getMenuByPage(String pagina)throws IdeamException{
        MenuVO menu = seguridadService.getMenuByPage(pagina);
        return menu;
    }
    /**
     * Retorna un menu que corresponde a la clase recibida como parametro
     * @param nombreClase
     * @return
     * @throws IdeamException
     */
    public MenuVO getMenuByClassName(String nombreClase)throws IdeamException{
        MenuVO menu = seguridadService.getMenuByClassName(nombreClase);
        return menu;
    }    
    /**
     * Retorna un menu correspondiente a la accion recibida como parametro
     * @param action
     * @return
     * @throws IdeamException
     */
    public MenuVO getMenuByAction(String action)throws IdeamException{
        MenuVO menu = seguridadService.getMenuByAction(action);
        return menu;        
    }   
    /**
     * Retorna una lista de objetos MenuVO con todos los menus que estan asociados
     * al perfil recibido como parametro
     * @param perfil
     * @return
     * @throws IdeamException
     */
    public List<MenuVO> getMenuByPerfil(PerfilVO perfil)throws IdeamException{
        List<MenuVO> lista = seguridadService.getMenuByPerfil(perfil);
        return lista;
    }
    /**
     * Retorna una lista de OpcionVO con todas las opciones relacionadas con 
     * el perfil definido como parametro
     * @param perfil
     * @return
     * @throws IdeamException
     */
    public List<MenuVO> getOpcionByPerfil(PerfilVO perfil)throws IdeamException{
        List<MenuVO> lista = seguridadService.getOpcionByPerfil(perfil);
        return lista;        
    }
    
    /**
     * Crea o actualiza un menu
     * @param menu
     * @throws IdeamException
     */
    public void updateMenu(MenuVO menu)throws IdeamException{
        seguridadService.updateMenu(menu);
    }   
    /**
     * Actualiza la informacion de menus y opciones asociadas a un perfil,
     * borra toda la informacion actual e inserta nuevos registros
     * @param perfil
     * @param listaMenu
     * @param listaOpciones
     * @throws IdeamException
     */
    public void updatePermisosPerfil(PerfilVO perfil, 
                                     List listaMenu, 
                                     List listaOpciones)throws IdeamException{
        seguridadService.updatePermisosPerfil(perfil,
                                              listaMenu,
                                              listaOpciones);    
    }
    /**
     * Crea o actualiza una opcion en la abse de datos
     * @param opcion
     * @throws IdeamException
     */
    public void updateOpcion(OpcionVO opcion)throws IdeamException{
        seguridadService.updateOpcion(opcion);
    }
    /**
     * Retorna el total de usuarios asociados al perfil recibido como parametro
     * @param perfil
     * @return
     * @throws IdeamException
     */
    public int countUsuariosPerfil(PerfilVO perfil)throws IdeamException{
        int valor = seguridadService.countUsuariosPerfil(perfil);
        return valor;
    }
    /**
     * Retorna los permisos de un perfil como un texto y en forma de arbol
     * @param perfil
     * @return
     * @throws IdeamException
     */
    public String getPermisosPerfil(PerfilVO perfil)throws IdeamException{
        String permisos = seguridadService.getPermisosPerfil(perfil);
        return permisos;
    }
     
     
      
    public int autenticarUser(String login, String clave)throws IdeamException{        
        int codmensaje = seguridadService.autenticarUser(login, clave);
        return codmensaje;
    }
     
     
    /**
    * Construcutor privado para implementar singleton
    */
    private SeguridadDelegate() throws IdeamException {                
        seguridadService = ServletLocator.getSeguridadService();
    }
}
