package co.gov.ideam.sirh.porh.view;

import co.gov.ideam.sirh.calidad.model.PuntoMonitoreo;
import co.gov.ideam.sirh.fuentes.business.FuentesEJB;
import co.gov.ideam.sirh.fuentes.model.FnttTramo;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.porh.business.PorhEJB;
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
import co.gov.ideam.sirh.servlet.ServletLocator;
import co.gov.ideam.sirh.usuarios.agua.business.UsuariosAguaEJB;
import co.gov.ideam.sirh.usuarios.agua.view.UsuariosAguaDelegate;
import co.gov.ideam.sirh.util.IdeamException;

import java.util.Date;
import java.util.List;

/**
 * Cponcentra e llamado a todos los metodos del EJB del modulo Porh
 */
public class PorhDelegate {
    /**
     * Referencia al EJB 
     */
    private static PorhEJB porhService = null;    
    /**
    * Utilizada para implementar singleton
    */
    private static PorhDelegate instance;
    /**
     * Retorna una instancia a la clase
     * @return
     */
    public static PorhDelegate getInstance() throws IdeamException {
        if (instance==null){
            instance = new PorhDelegate();
        }
        return instance;
    } 
    /**
     * Borra la informacion del uso recibido como parametro
     * @param uso
     * @throws IdeamException
     */    
    public void deleteUso(PorhTramosUsos uso)throws IdeamException{
        porhService.deleteUso(uso);
    }
    /**
     * Borra el objetivo de caidad recibido como parametro
     * @param objetivo
     * @throws IdeamException
     */    
    public void deleteObjetivoCalidad(PorhTramosUsosObjetivo objetivo)throws IdeamException{
        porhService.deleteObjetivoCalidad(objetivo);
    }
    /**
     * Borra la razon de prohibicion recibida como parametro
     * @param razonProhibicion
     * @throws IdeamException
     */
    public void deleteRazonProhibicion(PorhTramosProhibiciones razonProhibicion)throws IdeamException{
        porhService.deleteRazonProhibicion(razonProhibicion);
    }
    /**
     * Borra la meta reciboda com parametro
     * @param meta
     * @throws IdeamException
     */
    public void deleteMeta(PorvMetas meta)throws IdeamException{
        porhService.deleteMeta(meta);
    }
    /**
     * Borra el indicador reciboda com parametro
     * @param meta
     * @throws IdeamException
     */
    public void deleteIndicador(PorhIndices indice)throws IdeamException{
        porhService.deleteIndicador(indice);
    }
    /**
     * Borra el avance de meta recibido como parametro
     * @param avance
     * @throws IdeamException
     */
    public void deleteAvanceMeta(PorhAvanceMetas avance)throws IdeamException{
        porhService.deleteAvanceMeta(avance);   
    }    
    /**
     * Retorna el plan de ordenamiento asociado al numero de acto y
     * autoridad recibidos por parametro
     * @param numeroActo
     * @param autoridadAmbiental
     * @return
     * @throws IdeamException
     */
    public PorhPlanes getPlanOrdenamiento(String numeroActo, Autoridades autoridadAmbiental)throws IdeamException{
        return porhService.getPlanOrdenamiento(numeroActo, autoridadAmbiental);
    }
    /**
     * Borra el plan de ordenamiento recibido como parametro y todos sus
     * atributos relacionados
     * @param plan
     * @throws IdeamException
     */    
    public void deletePorh(PorhPlanes plan)throws IdeamException{
        porhService.deletePorh(plan);
    }
    /**
     * Inserta o actualiza el seguimiento recibido como parametro
     * @param seguimiento
     * @return
     * @throws IdeamException
     */
    public PorhSeguimientoIndices updateSeguimientoIndice(PorhSeguimientoIndices seguimiento)throws IdeamException{
        return porhService.updateSeguimientoIndice(seguimiento);
    }    
    /**
     * Retorna una lista de seguimientos asociados al indicador recibido como
     * parametro
     * @param indicador
     * @return
     * @throws IdeamException
     */
    public List<PorhSeguimientoIndices> getAllSeguimiento(PorhIndices indicador)throws IdeamException{
        return porhService.getAllSeguimiento(indicador);
    }
    /**
     * Inserta o actualiza un indicador recibido como parametro
     * @param indicador
     * @return
     * @throws IdeamException
     */
    public PorhIndices updateIndicador(PorhIndices indicador)throws IdeamException{
        return porhService.updateIndicador(indicador);
    }
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
    public List<PorhIndices> getIndicadores(FnttTramo tramo, PorhPlanes plan, Long codigoAutoridad)throws IdeamException{
        return porhService.getIndicadores(tramo, plan, codigoAutoridad);
    }
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
                                                     Long codigoTramo)throws IdeamException{
        return porhService.getDatosEfectividad(codigoParametro, 
                                               fechaLimite,
                                               codigoFuente,
                                               codigoTramo);
    }
    /**
     * Retorna la meta correspondiente al codigo recibido como parametro
     * @param codigo
     * @return
     * @throws IdeamException
     */
    public PorhMetas getMeta(Long codigo)throws IdeamException{
        return porhService.getMeta(codigo);
    }
    /**
     * Inserta o actual la meta recibida como parametro
     * @param meta
     * @return
     * @throws IdeamException
     */
    public PorhMetas updateMeta(PorhMetas meta)throws IdeamException{
        return porhService.updateMeta(meta);
    }
    /**
     * Retorna todas las metas relacionadas con el tramo recibido como parametro
     * @param tramo
     * @return
     * @throws IdeamException
     */
    public List<PorvMetas> getAllMetas(FnttTramo tramo)throws IdeamException{
        return porhService.getAllMetas(tramo);
    }
    /**
     * Inserta o actualiza un avance de meta
     * @param avance
     * @return
     * @throws IdeamException
     */
    public PorhAvanceMetas updateAvanceMeta(PorhAvanceMetas avance)throws IdeamException{
        return porhService.updateAvanceMeta(avance);
    }
    /**
     * Retorna todos los parametros de calidad asociados al tramo recibido como
     * parametro
     * @param tramo
     * @return
     * @throws IdeamException
     */
    public List<PorhParametrosTO> getParametros(FnttTramo tramo)throws IdeamException{
        return porhService.getParametros(tramo);
    }
    /**
     * Retorna las metas de avance de la meta recibida por parametro
     * @param codigoMeta
     * @return
     * @throws IdeamException
     */
    public List<PorhAvanceMetas> getAvanceMetas(Long codigoMeta)throws IdeamException{
        return porhService.getAvanceMetas(codigoMeta);
    }
    /**
     * Inserta o actualiza la prohibicion de vertimiento en un tramo
     * @param prohibicion
     * @return
     * @throws IdeamException
     */
    public PorhTramosProhibiciones updateTramoProhibicion(PorhTramosProhibiciones prohibicion)throws IdeamException{
        return porhService.updateTramoProhibicion(prohibicion);
    }
    /**
     * Retorna una lista con lsa prohibicioens registradas para el tramo
     * recibido como parametro
     * @param tramo
     * @return
     * @throws IdeamException
     */
    public List<PorhTramosProhibiciones> getAllProhibiciones(FnttTramo tramo)throws IdeamException{
        return porhService.getAllProhibiciones(tramo);
    }
    /**
     * Retorna el registro correspondiente al codigo recibido por parametro
     * @param codigo
     * @return
     */
    public PorhTramosUsosObjetivo getObjetivoCalidad(Long codigo)throws IdeamException{
        return porhService.getObjetivoCalidad(codigo);
    }
    /**
     * Retorna una lista con los parametros relacionados a un uso
     * @param usoTramo
     * @return
     * @throws IdeamException
     */
    public List<PorhParametrosTO> getParametros(PorhTramosUsos usoTramo)throws IdeamException{
        return porhService.getParametros(usoTramo);
    }
    /**
     * Retorna una lista con los parametros relacionados a un uso
     * @param usoTramo
     * @return
     * @throws IdeamException
     */
    public List<PorhTramosUsosObjetivo> getObjetivosUso(PorhTramosUsos usoTramo)throws IdeamException{
        return porhService.getObjetivosUso(usoTramo);
    }
    /**
     * Inserta o actualiza el valor del objetivo de calidad correspondiente al tramo y plazo
     * @param objetivo
     * @return
     * @throws IdeamException
     */
    public PorhTramosUsosObjetivo updateObjetivo(PorhTramosUsosObjetivo objetivo)throws IdeamException{
        return porhService.updateObjetivo(objetivo);
    }
    /**
     * Inserta o actualiza el plazo asociada al uno de un tramo
     * @param plazo
     * @return
     * @throws IdeamException
     */
    public PorhTramosUsosPlazos updateTramoUsoPlazo(PorhTramosUsosPlazos plazo)throws IdeamException{
        return porhService.updateTramoUsoPlazo(plazo);
    }
    /**
     * Inserta o actual la relacion entre un tramo y un uso
     * @param tramoUso
     * @throws IdeamException
     */
    public PorhTramosUsos updateUsoTramo(PorhTramosUsos tramoUso)throws IdeamException{
        return porhService.updateUsoTramo(tramoUso);
    }
    /**
     * Retorna una lista con los usos permitidos para un tramo y un plan de 
     * ordenamiento
     * @param plan
     * @param tramo
     * @return
     * @throws IdeamException
     */
    public List<PorhTramosUsos> getAllUsosTramo(PorhPlanes plan, FnttTramo tramo)throws IdeamException{
        return porhService.getAllUsosTramo(plan, tramo);
    }
    /**
     * Inserta o actualiza una oferta demnda de un tramos relacionado a una fuente
     * con porh
     * @param ofertaDemanda
     * @throws IdeamException
     */
    public void updateOfertaDemanda(PorhOfertaDemanda ofertaDemanda)throws IdeamException{
        porhService.updateOfertaDemanda(ofertaDemanda);
    }    
    /**
     * Retorna la oferta y demanda de un tramos de una fuente que tiene un plan
     * de ordenamiento
     * @param idFuente
     * @param idTramo
     * @return
     * @throws IdeamException
     */
    public PorhOfertaDemanda getOfertaDemanda(Long idFuente, Long idTramo)throws IdeamException{
        return porhService.getOfertaDemanda(idFuente, idTramo);
    }    
    
  /**
   * Retorna la oferta y demanda de un punto de monitoreo , de un tramo de una fuente que tiene un plan
   * de ordenamiento
   * @param idFuente
   * @param idTramo
   * @param idPuntoMonitoreo
   * @return
   * @throws IdeamException
   */
  public PorhOfertaDemanda getOfertaDemanda(Long idFuente, Long idTramo,Long idPuntoMonitoreo)throws IdeamException{
      return porhService.getOfertaDemanda(idFuente, idTramo,idPuntoMonitoreo);
  } 
  
    /**
     * Retorna el plan de ordenamiento para la fuente y autoridad ambiental
     * recibidos como parametro
     * @param codigoFuente
     * @param codigoAutoridad
     * @return
     * @throws IdeamException
     */
    public PorhPlanes getPlanOrdenamiento(Long codigoFuente, Long codigoAutoridad)throws IdeamException{
        return porhService.getPlanOrdenamiento(codigoFuente, codigoAutoridad);
    }
    /**
     * Actualiza la  informacion de un p,lan de ordenamiento
     * @param planOrdenamiento
     * @throws IdeamException
     */
     
    public PorhPlanes updatePlan(PorhPlanes planOrdenamiento)throws IdeamException{        
        return porhService.updatePlan(planOrdenamiento);
    }
    public PorhPlanes updatePriorizacion(PorhPlanes planOrdenamiento)throws IdeamException{        
        return porhService.updatePriorizacion(planOrdenamiento);
    }
    
    /**
    * Construcutor privado para implementar singleton
    */
    private PorhDelegate() throws IdeamException {                
        porhService = ServletLocator.getPorhService();
        if(porhService==null)
            System.out.println("No existe PORHSERVICE--------------------------------");
    }    
    
  /**
   * Retorna PorhTramosProhiPMonitoreo por TramoProhibiciones
   * @param idTramosProhibiciones
   * @return
   * @throws IdeamException
   */
  public List<PorhTramosProhiPMonitoreo> getPorhTramosProhiPMonitoreo(Long idTramosProhibiciones)throws IdeamException{
      return porhService.getPorhTramosProhiPMonitoreo(idTramosProhibiciones);
  }
}
