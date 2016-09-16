package co.gov.ideam.sirh.directorio.view;

import co.gov.ideam.sirh.directorio.business.DirectorioEJB;
import co.gov.ideam.sirh.directorio.model.ActEspecialistaFormacion;
import co.gov.ideam.sirh.directorio.model.ActEspecialistas;
import co.gov.ideam.sirh.directorio.model.ActOrganizaciones;
import co.gov.ideam.sirh.directorio.model.CriterioBusquedaEspecialistaTO;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.servlet.ServletLocator;
import co.gov.ideam.sirh.util.IdeamException;

import java.util.List;

/**
 * Concentra el llamado de todos los metodos al ejb del modulo direcotrio
 */
public class DirectorioDelegate {
    /**
     * Referencia al EJB 
     */
    private static DirectorioEJB directorioService = null;    
    /**
    * Utilizada para implementar singleton
    */
    private static DirectorioDelegate instance;
    /**
     * Retorna una instancia a la clase
     * @return
     */
    public static DirectorioDelegate getInstance() throws IdeamException {
        if (instance==null){
            instance = new DirectorioDelegate();
        }
        return instance;
    } 
    /**
     * Retorna el especialista correspondiente al tipo y numero de identificacion
     * recibido como parametro
     * @param tipoId
     * @param numeroId
     * @return
     * @throws IdeamException
     */
    public ActEspecialistas getEspecialista(Long tipoId, String numeroId)throws IdeamException{
        return directorioService.getEspecialista(tipoId, numeroId);
    }
    
    
    public ActEspecialistas getEspecialistaId(Long ident)throws IdeamException{
        return directorioService.getEspecialistaId(ident);
    }
    
    
    /**
     * Borra la organizacion recibida por parametro
     * @param organizacion
     * @throws IdeamException
     */    
    public void deleteOrganizacion(ActOrganizaciones organizacion)throws IdeamException{
        directorioService.deleteOrganizacion(organizacion);
    }
    /**
     * Borra el especialista recibido como parametro
     * @param especialista
     * @throws IdeamException
     */    
    public void deleteEspecialista(ActEspecialistas especialista)throws IdeamException{
        directorioService.deleteEspecialista(especialista);
    }
    /**
     * Retorna una lista con las organizaciones que cumplen con los criterios de
     * busqueda recibidos por parametro
     * @param criterios
     * @return
     * @throws IdeamException
     */
    public List<ActOrganizaciones> getAllOrganizaciones(CriterioBusquedaEspecialistaTO criterios)throws IdeamException{
        return directorioService.getAllOrganizaciones(criterios);
    }
    public List<ActOrganizaciones> getAllOrganizaciones()throws IdeamException{
    return directorioService.getAllOrganizaciones();
    }
    /**
     * Retorna una lista con los especialistas que cumplen los criterios de consulta
     * enviados por parametro
     * @param criterios
     * @return
     * @throws IdeamException
     */
    public List<ActEspecialistas> getAllEspecialistas(CriterioBusquedaEspecialistaTO criterios)throws IdeamException{
        return directorioService.getAllEspecialistas(criterios);
    }
    /**
     * Retorna una lista con la formacion asociada al especialista recibido
     * como parametro
     * @param especialista
     * @return
     * @throws IdeamException
     */
    public List<ActEspecialistaFormacion> getAllFormacion(ActEspecialistas especialista)throws IdeamException{
        return directorioService.getAllFormacion(especialista);
    }
    /**
     * Inserta o actualiza el especialista recibido como parametro
     * @param especialista
     * @return
     * @throws IdeamException
     */
    public ActEspecialistas updateEspecialista(ActEspecialistas especialista, List<ActEspecialistaFormacion> formacion)throws IdeamException{
        return directorioService.updateEspecialista(especialista, formacion);
    }
    /**
     * Retorna una lista con todos los especialistas asociados a la autoridad
     * ambiental recibida como parametro
     * @param autoridadAmbiental
     * @return
     * @throws IdeamException
     */
    public List<ActEspecialistas> getAllEsoecialistas(Autoridades autoridadAmbiental)throws IdeamException{
        return directorioService.getAllEsoecialistas(autoridadAmbiental);
    }
    /**
     * Inserta o actualiza la organizacion recibida como parametro
     * @param organizacion
     * @return
     * @throws IdeamException
     */
    public ActOrganizaciones updateOrganizacion(ActOrganizaciones organizacion)throws IdeamException{
        return directorioService.updateOrganizacion(organizacion);
    }
    /**
     * Retorna una lista de de todas las organizaciones asociadas a la 
     * autoridad recibida como parametro
     * @param autoridad
     * @return
     * @throws IdeamException
     */
    public List<ActOrganizaciones> getAllOrganizaciones(Autoridades autoridad)throws IdeamException{
        return directorioService.getAllOrganizaciones(autoridad);
    }
    
    /**
    * Construcutor privado para implementar singleton
    */
    private DirectorioDelegate() throws IdeamException {                
        directorioService = ServletLocator.getDirectorioService();
    }    
    
}
