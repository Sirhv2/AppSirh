package co.gov.ideam.sirh.directorio.business;

import co.gov.ideam.sirh.directorio.model.ActEspecialistaFormacion;
import co.gov.ideam.sirh.directorio.model.ActEspecialistas;
import co.gov.ideam.sirh.directorio.model.ActOrganizaciones;
import co.gov.ideam.sirh.directorio.model.CriterioBusquedaEspecialistaTO;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.util.IdeamException;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

@Remote
public interface DirectorioEJB {
    /**
     * Retorna el especialista correspondiente al tipo y numero de identificacion
     * recibido como parametro
     * @param tipoId
     * @param numeroId
     * @return
     * @throws IdeamException
     */
    public ActEspecialistas getEspecialista(Long tipoId, String numeroId)throws IdeamException;    
    public ActEspecialistas getEspecialistaId(Long ident)throws IdeamException;
    /**
     * Borra la organizacion recibida por parametro
     * @param organizacion
     * @throws IdeamException
     */    
    public void deleteOrganizacion(ActOrganizaciones organizacion)throws IdeamException;
    /**
     * Borra el especialista recibido como parametro
     * @param especialista
     * @throws IdeamException
     */    
    public void deleteEspecialista(ActEspecialistas especialista)throws IdeamException;
    /**
     * Retorna una lista con las organizaciones que cumplen con los criterios de
     * busqueda recibidos por parametro
     * @param criterios
     * @return
     * @throws IdeamException
     */
    public List<ActOrganizaciones> getAllOrganizaciones(CriterioBusquedaEspecialistaTO criterios)throws IdeamException;    
    /**
     * Retorna una lista con los especialistas que cumplen los criterios de consulta
     * enviados por parametro
     * @param criterios
     * @return
     * @throws IdeamException
     */
     public List<ActOrganizaciones> getAllOrganizaciones()throws IdeamException;
    
    public List<ActEspecialistas> getAllEspecialistas(CriterioBusquedaEspecialistaTO criterios)throws IdeamException;    
    /**
     * Retorna una lista con la formacion asociada al especialista recibido
     * como parametro
     * @param especialista
     * @return
     * @throws IdeamException
     */
    public List<ActEspecialistaFormacion> getAllFormacion(ActEspecialistas especialista)throws IdeamException;    
    /**
     * Inserta o actualiza el especialista recibido como parametro
     * @param especialista
     * @return
     * @throws IdeamException
     */
    public ActEspecialistas updateEspecialista(ActEspecialistas especialista, List<ActEspecialistaFormacion> formacion)throws IdeamException;    
    /**
     * Retorna una lista con todos los especialistas asociados a la autoridad
     * ambiental recibida como parametro
     * @param autoridadAmbiental
     * @return
     * @throws IdeamException
     */
    public List<ActEspecialistas> getAllEsoecialistas(Autoridades autoridadAmbiental)throws IdeamException;    
    /**
     * Inserta o actualiza la organizacion recibida como parametro
     * @param organizacion
     * @return
     * @throws IdeamException
     */
    public ActOrganizaciones updateOrganizacion(ActOrganizaciones organizacion)throws IdeamException;    
    /**
     * Retorna una lista de de todas las organizaciones asociadas a la 
     * autoridad recibida como parametro
     * @param autoridad
     * @return
     * @throws IdeamException
     */
    public List<ActOrganizaciones> getAllOrganizaciones(Autoridades autoridad)throws IdeamException;
}
