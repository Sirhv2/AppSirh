package co.gov.ideam.sirh.seguridad.business;

import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.seguridad.model.Funcionario;
import co.gov.ideam.sirh.seguridad.model.MenuVO;
import co.gov.ideam.sirh.seguridad.model.OpcionVO;
import co.gov.ideam.sirh.seguridad.model.PerfilVO;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.seguridad.model.UsuarioVO;
import co.gov.ideam.sirh.util.IdeamException;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.ejb.LocalHome;
import javax.ejb.Remote;
/**
 * Metodos que deben ser implementados por el EBJ del modulo de seguridad
 */
@Remote
public interface SeguridadEJB {
    /**
     * Verifica el password de un usuario
     * @param usuario
     * @param password
     * @return
     * @throws FenixException
     */
    public boolean validatePassword(UsuarioVO usuario, String password)throws IdeamException;
    /**
     * Borra el usuario recibido como parametro
     * @param usuario
     * @throws FenixException
     */
    public void deleteUser(UsuarioVO usuario)throws IdeamException;
    /**
     * Reasigna la clave para el usuario recibido como parametro 
     * @param usuario
     * @throws FenixException
     */
    public void restartPassword(UsuarioVO usuario)throws IdeamException;
    /**
     * Guarda la clave del usuario
     * @param usuario
     * @param password
     * @throws FenixException
     */
    public void savePassword(UsuarioVO usuario, String password)throws IdeamException;
        
    /**
     * Inserta o actualiza la informacion de un usuario en el sistema
     * @param usuario
     * @throws FenixException
     */
    public void updateUser(UsuarioVO usuario)throws IdeamException;
    /**
     * Verifica la clave de un usuario
     * @param login
     * @param clave
     * @return
     */
    public boolean validateUser(String login, String clave)throws IdeamException;
    
    /**
     * Retorna un usuario que tiene asociada la direccion de correo recibida
     * como parametro
     * @param email
     * @return
     * @throws FenixException
     */
    public UsuarioVO getUsuarioByEmail(String email)throws IdeamException;
    /**
     * Retorna un VO con toda la informacion del usuario registrado en el
     * sistema
     * @param login
     * @return
     * @throws FenixException
     */
    public UsuarioVO getUsuario(String login)throws IdeamException;
    /**
     * Retorna una lista con las opciones de menu habilitadas para el 
     * usuario recibido como parametro
     * @param usuario
     * @return
     * @throws FenixException
     */
    public List getMenu(UsuarioVO usuario)throws IdeamException;
    /**
     * Retorna todas las opciones de menu autorizadas para el usuario
     * recibido como parametro
     * @param usuario
     * @return
     * @throws FenixException
     */
    public HashMap getMenuAutorizado(UsuarioVO usuario)throws IdeamException;
    /**
     * Carga la informacion del usuario en el ejb de session correspondiente
     * @param loginUser
     * @throws FenixException
     */
    public UsuarioConectadoTO login(String loginUser)throws IdeamException;
    /**
     * Retorna una lista con todos los usuarios registrados en el sistema
     * @return
     * @throws FenixException
     */
    public List getUsuarios(Autoridades autoridad)throws IdeamException;
    /**
     * Retorna una lista con los usuarios correspondientes a los parametros
     * recibidos
     * @param usuario
     * @param useEstado
     * @param fechaFinalIngresoExistoso
     * @param fechaFinalIngresoNoExitoso
     * @return
     * @throws FenixException
     */
    public List getUsuarios(UsuarioVO usuario, 
                            boolean useEstado,
                            Calendar fechaFinalIngresoExistoso,                            
                            Calendar fechaFinalIngresoNoExitoso)throws IdeamException;    
    /**
     * Retorna una lista con todos los perfiles registrados en el
     * sistema
     * @return
     * @throws FenixException
     */
    public List getAllPerfiles()throws IdeamException;
    /**
     * Borra el perfil recibido como parametro
     * @param perfil
     * @throws FenixException
     */
    public void deletePerfil(PerfilVO perfil)throws IdeamException;
    /**
     * Retorna el perfil correspondiente al codigo recibido como parametro
     * @param codigo
     * @return
     * @throws FenixException
     */
    public PerfilVO getPerfil(long codigo)throws IdeamException; 
    public PerfilVO getPerfilUsuario(long codigo)throws IdeamException;
    /**
     * Inserta o actualiza la informacion del perfil recibido como
     * parametro
     * @param perfil
     * @throws FenixException
     */
    public void updatePerfil(PerfilVO perfil)throws IdeamException;
    /**
     * Retorna una lista con todos los perfiles asociados al usuario
     * recibido como parametro
     * @param usuario
     * @return
     * @throws FenixException
     */
    public List getPerfiles(UsuarioVO usuario)throws IdeamException;
    /**
     * Retorna una lista con todas las opciones de menu registradas en el 
     * sistema
     * @return
     * @throws FenixException
     */
    public List getAllMenu()throws IdeamException;
    /**
     * Retirna un menu que controla la pagina recibida como parametro
     * @param pagina
     * @return
     * @throws FenixException
     */
    public MenuVO getMenuByPage(String pagina)throws IdeamException;
    /**
     * Retorna un menu que corresponde a la clase recibida como parametro
     * @param nombreClase
     * @return
     * @throws FenixException
     */
    public MenuVO getMenuByClassName(String nombreClase)throws IdeamException;    
    /**
     * Retorna un meno correspondiente a la accion recibida como parametro
     * @param action
     * @return
     * @throws FenixException
     */
    public MenuVO getMenuByAction(String action)throws IdeamException;
    /**
     * Retorna una lista de objetos MenuVO con todos los menus que estan asociados
     * al perfil recibido como parametro
     * @param perfil
     * @return
     * @throws FenixException
     */
    public List<MenuVO> getMenuByPerfil(PerfilVO perfil)throws IdeamException;    
    /**
     * Retorna una lista de OpcionVO con todas las opciones relacionadas con 
     * el perfil definido como parametro
     * @param perfil
     * @return
     * @throws FenixException
     */
    public List<MenuVO> getOpcionByPerfil(PerfilVO perfil)throws IdeamException;    
    /**
     * Actualiza la informacion de menus y opciones asociadas a un perfil,
     * borra toda la informacion actual e inserta nuevos registros
     * @param perfil
     * @param listaMenu
     * @param listaOpciones
     * @throws FenixException
     */
    public void updatePermisosPerfil(PerfilVO perfil, 
                                     List listaMenu, 
                                     List listaOpciones)throws IdeamException;    
    /**
     * Crea o actualiza un menu en la base de datos
     * @param menu
     * @throws FenixException
     */
    public void updateMenu(MenuVO menu)throws IdeamException;    
    /**
     * Crea o actualiza una opcion en la base de datos
     * @param opcion
     * @throws FenixException
     */
    public void updateOpcion(OpcionVO opcion)throws IdeamException;
    /**
     * Retorna el total de usuarios asociados al perfil recibido como parametro
     * @param perfil
     * @return
     * @throws FenixException
     */
    public int countUsuariosPerfil(PerfilVO perfil)throws IdeamException;    
    /**
     * Retorna los permisos de un perfil como un texto y en forma de arbol
     * @param perfil
     * @return
     * @throws FenixException
     */
    public String getPermisosPerfil(PerfilVO perfil)throws IdeamException;

    public List<Funcionario> consultarFuncionarios(String codAutoridadAmbiental )throws IdeamException;
    public int autenticarUser(String login, String clave)throws IdeamException;
    
}
