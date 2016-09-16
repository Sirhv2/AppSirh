package co.gov.ideam.sirh.porh.business;

import co.gov.ideam.sirh.fuentes.model.FnttTramo;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.porh.model.PorhAvanceMetas;
import co.gov.ideam.sirh.porh.model.PorhIndices;
import co.gov.ideam.sirh.porh.model.PorhMetas;
import co.gov.ideam.sirh.porh.model.PorhOfertaDemanda;
import co.gov.ideam.sirh.porh.model.PorhParametrosTO;
import co.gov.ideam.sirh.porh.model.PorhPlanes;
import co.gov.ideam.sirh.porh.model.PorhSeguimientoIndices;
import co.gov.ideam.sirh.porh.model.PorhTramosProhiPMonitoreo;
import co.gov.ideam.sirh.porh.model.PorhTramosProhibiciones;
import co.gov.ideam.sirh.porh.model.PorhTramosUsos;
import co.gov.ideam.sirh.porh.model.PorhTramosUsosObjetivo;
import co.gov.ideam.sirh.porh.model.PorhTramosUsosPlazos;
import co.gov.ideam.sirh.porh.model.PorvEfectividad;
import co.gov.ideam.sirh.porh.model.PorvMetas;
import co.gov.ideam.sirh.util.IdeamException;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

@Remote
public interface PorhEJB {
    /**
     * Borra la informacion del uso recibido como parametro
     * @param uso
     * @throws IdeamException
     */    
    public void deleteUso(PorhTramosUsos uso)throws IdeamException;
    /**
     * Borra el objetivo de caidad recibido como parametro
     * @param objetivo
     * @throws IdeamException
     */    
    public void deleteObjetivoCalidad(PorhTramosUsosObjetivo objetivo)throws IdeamException;
    /**
     * Borra la razon de prohibicion recibida como parametro
     * @param razonProhibicion
     * @throws IdeamException
     */
    public void deleteRazonProhibicion(PorhTramosProhibiciones razonProhibicion)throws IdeamException;    
    /**
     * Borra la meta reciboda com parametro
     * @param meta
     * @throws IdeamException
     */
    public void deleteMeta(PorvMetas meta)throws IdeamException;    
    /**
     * Borra el avance de meta recibido como parametro
     * @param avance
     * @throws IdeamException
     */
    public void deleteAvanceMeta(PorhAvanceMetas avance)throws IdeamException;    
    /**
     * Retorna el plan de ordenamiento asociado al numero de acto y
     * autoridad recibidos por parametro
     * @param numeroActo
     * @param autoridadAmbiental
     * @return
     * @throws IdeamException
     */
    public PorhPlanes getPlanOrdenamiento(String numeroActo, Autoridades autoridadAmbiental)throws IdeamException;    
    /**
     * Borra el plan de ordenamiento recibido como parametro y todos sus
     * atributos relacionados
     * @param plan
     * @throws IdeamException
     */    
    public void deletePorh(PorhPlanes plan)throws IdeamException;
    /**
     * Inserta o actualiza el seguimiento recibido como parametro
     * @param seguimiento
     * @return
     * @throws IdeamException
     */
    public PorhSeguimientoIndices updateSeguimientoIndice(PorhSeguimientoIndices seguimiento)throws IdeamException;    
    /**
     * Retorna una lista de seguimientos asociados al indicador recibido como
     * parametro
     * @param indicador
     * @return
     * @throws IdeamException
     */
    public List<PorhSeguimientoIndices> getAllSeguimiento(PorhIndices indicador)throws IdeamException;    
    /**
     * Inserta o actualiza un indicador recibido como parametro
     * @param indicador
     * @return
     * @throws IdeamException
     */
    public PorhIndices updateIndicador(PorhIndices indicador)throws IdeamException;    
    /**
     * Retorna una lista de todos los indicadores asociados al tramo y plan
     * de ordenamiento recibidos como parametro.
     * Si el tramo no tiene ningun indicados inserta los indicadores
     * basicos, llamando el metodo correspondiente en este mismo EJB
     * @param tramo
     * @param plan
     * @return
     * @throws IdeamException
     */
    public List<PorhIndices> getIndicadores(FnttTramo tramo, PorhPlanes plan, Long codigoAutoridad)throws IdeamException;    
    /**
     * Retorna los valores medidos para el parametro recibido y hasta la 
     * fecha recibida com parametro
     * @param codigoParametro
     * @param fechaLimite
     * @param codigoFuente
     * @param codigoTramo
     * @return
     * @throws IdeamException
     */
    public List<PorvEfectividad> getDatosEfectividad(Long codigoParametro, 
                                                     Date fechaLimite,
                                                     Long codigoFuente,
                                                     Long codigoTramo)throws IdeamException;    
    /**
     * Retorna la meta correspondiente al codigo recibido como parametro
     * @param codigo
     * @return
     * @throws IdeamException
     */
    public PorhMetas getMeta(Long codigo)throws IdeamException;    
    /**
     * Inserta o actual la meta recibida como parametro
     * @param meta
     * @return
     * @throws IdeamException
     */
    public PorhMetas updateMeta(PorhMetas meta)throws IdeamException;    
    /**
     * Retorna todas las metas relacionadas con el tramo recibido como parametro
     * @param tramo
     * @return
     * @throws IdeamException
     */
    public List<PorvMetas> getAllMetas(FnttTramo tramo)throws IdeamException;    
    /**
     * Inserta o actualiza un avance de meta
     * @param avance
     * @return
     * @throws IdeamException
     */
    public PorhAvanceMetas updateAvanceMeta(PorhAvanceMetas avance)throws IdeamException;    
    /**
     * Retorna todos los parametros de calidad asociados al tramo recibido como
     * parametro
     * @param tramo
     * @return
     * @throws IdeamException
     */
    public List<PorhParametrosTO> getParametros(FnttTramo tramo)throws IdeamException;    
    /**
     * Retorna las metas de avance de la meta recibida por parametro
     * @param codigoMeta
     * @return
     * @throws IdeamException
     */
    public List<PorhAvanceMetas> getAvanceMetas(Long codigoMeta)throws IdeamException;    
    /**
     * Inserta o actualiza la prohibicion de vertimiento en un tramo
     * @param prohibicion
     * @return
     * @throws IdeamException
     */
    public PorhTramosProhibiciones updateTramoProhibicion(PorhTramosProhibiciones prohibicion)throws IdeamException;    
    /**
     * Retorna una lista con lsa prohibicioens registradas para el tramo
     * recibido como parametro
     * @param tramo
     * @return
     * @throws IdeamException
     */
    public List<PorhTramosProhibiciones> getAllProhibiciones(FnttTramo tramo)throws IdeamException;    
    /**
     * Retorna el registro correspondiente al codigo recibido por parametro
     * @param codigo
     * @return
     */
    public PorhTramosUsosObjetivo getObjetivoCalidad(Long codigo)throws IdeamException;   
    /**
     * Retorna una lista con los parametros relacionados a un uso
     * @param usoTramo
     * @return
     * @throws IdeamException
     */
    public List<PorhParametrosTO> getParametros(PorhTramosUsos usoTramo)throws IdeamException;    
    /**
     * Retorna una lista con los parametros relacionados a un uso
     * @param usoTramo
     * @return
     * @throws IdeamException
     */
    public List<PorhTramosUsosObjetivo> getObjetivosUso(PorhTramosUsos usoTramo)throws IdeamException;    
    /**
     * Inserta o actualiza el valor del objetivo de calidad correspondiente al tramo y plazo
     * @param objetivo
     * @return
     * @throws IdeamException
     */
    public PorhTramosUsosObjetivo updateObjetivo(PorhTramosUsosObjetivo objetivo)throws IdeamException;    
    /**
     * Inserta o actualiza el plazo asociada al uno de un tramo
     * @param plazo
     * @return
     * @throws IdeamException
     */
    public PorhTramosUsosPlazos updateTramoUsoPlazo(PorhTramosUsosPlazos plazo)throws IdeamException;    
    /**
     * Inserta o actual la relacion entre un tramo y un uso
     * @param tramoUso
     * @throws IdeamException
     */
    public PorhTramosUsos updateUsoTramo(PorhTramosUsos tramoUso)throws IdeamException;    
    /**
     * Retorna una lista con los usos permitidos para un tramo y un plan de 
     * ordenamiento
     * @param plan
     * @param tramo
     * @return
     * @throws IdeamException
     */
    public List<PorhTramosUsos> getAllUsosTramo(PorhPlanes plan, FnttTramo tramo)throws IdeamException;    
    /**
     * Inserta o actualiza una oferta demnda de un tramos relacionado a una fuente
     * con porh
     * @param ofertaDemanda
     * @throws IdeamException
     */
    public void updateOfertaDemanda(PorhOfertaDemanda ofertaDemanda)throws IdeamException;
    /**
     * Retorna la oferta y demanda de un tramos de una fuente que tiene un plan
     * de ordenamiento
     * @param idFuente
     * @param idTramo
     * @return
     * @throws IdeamException
     */
    public PorhOfertaDemanda getOfertaDemanda(Long idFuente, Long idTramo)throws IdeamException;   
    
   /**
    * Retorna la oferta y demanda de un punto de monitoreo , de un tramo de una fuente que tiene un plan
    * de ordenamiento
    * @param idFuente
    * @param idTramo
    * @param idPuntoMonitoreo
    * @return
    * @throws IdeamException
    */
  public PorhOfertaDemanda getOfertaDemanda(Long idFuente, Long idTramo,Long idPuntoMonitoreo)throws IdeamException; 
  
    /**
     * Retorna el plan de ordenamiento para la fuente y autoridad ambiental
     * recibidos como parametro
     * @param codigoFuente
     * @param codigoAutoridad
     * @return
     * @throws IdeamException
     */
    public PorhPlanes getPlanOrdenamiento(Long codigoFuente, Long codigoAutoridad)throws IdeamException;    
    /**
     * Actualiza la  informacion de un p,lan de ordenamiento
     * @param planOrdenamiento
     * @throws IdeamException
     */
     public PorhPlanes updatePriorizacion(PorhPlanes planOrdenamiento)throws IdeamException;

//Pilar
     public void deleteIndicador(PorhIndices indicador)throws IdeamException;
     public PorhPlanes updatePlan(PorhPlanes planOrdenamiento)throws IdeamException;
     
//Carlos Ferro
    public List<PorhTramosProhiPMonitoreo> getPorhTramosProhiPMonitoreo(Long idTramosProhibiciones)throws IdeamException;
     
}
