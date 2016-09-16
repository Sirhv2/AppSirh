package co.gov.ideam.sirh.fuentes.business;


import co.gov.ideam.sirh.fuentes.model.FnttDetalleFuentesV;
import co.gov.ideam.sirh.fuentes.model.ActorConflicto;
import co.gov.ideam.sirh.fuentes.model.CriteriosBusquedaTO;
import co.gov.ideam.sirh.fuentes.model.DatosTablaAlertDisp;
import co.gov.ideam.sirh.fuentes.model.DatosTablaAlertDispDet;
import co.gov.ideam.sirh.fuentes.model.DatosTablaAlertIca;
import co.gov.ideam.sirh.fuentes.model.DatosTablaAlertIcaDet;
import co.gov.ideam.sirh.fuentes.model.DerechoConflicto;
import co.gov.ideam.sirh.fuentes.model.FnttActividadesEconomicas;
import co.gov.ideam.sirh.fuentes.model.FnttActores;
import co.gov.ideam.sirh.fuentes.model.FnttConflicto;
import co.gov.ideam.sirh.fuentes.model.FnttBuenasPracticas;
import co.gov.ideam.sirh.fuentes.model.FnttCategorias;
import co.gov.ideam.sirh.fuentes.model.FnttCostos;
import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.fuentes.model.FnttFuenteTramoMunicipio;
import co.gov.ideam.sirh.fuentes.model.FnttLogros;
import co.gov.ideam.sirh.fuentes.model.FnttMotivaciones;
import co.gov.ideam.sirh.fuentes.model.FnttPrincipios;
import co.gov.ideam.sirh.fuentes.model.FnttProyectosEducativos;
import co.gov.ideam.sirh.fuentes.model.FnttTramo;
import co.gov.ideam.sirh.fuentes.model.FnttTrmAforo;
import co.gov.ideam.sirh.fuentes.model.FnttTramoAfluente;
import co.gov.ideam.sirh.fuentes.model.FnttTramoRiesgos;
import co.gov.ideam.sirh.fuentes.model.FuentePOJO;
import co.gov.ideam.sirh.fuentes.model.FnttTrmAforo;
import co.gov.ideam.sirh.fuentes.model.FuenteTramoMunicipioPOJO;
import co.gov.ideam.sirh.fuentes.model.GestionConflicto;
import co.gov.ideam.sirh.fuentes.model.OrigenConflicto;
import co.gov.ideam.sirh.fuentes.model.PoblacionConflicto;
import co.gov.ideam.sirh.fuentes.model.TipoConflicto;
import co.gov.ideam.sirh.fuentes.model.TramoPOJO;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.util.EstadisticasQry;
import co.gov.ideam.sirh.util.IdeamException;

import java.util.List;

import javax.ejb.Remote;

import javax.persistence.NoResultException;

@Remote
public interface FuentesEJB {
    
    
    /**
     * Retorna la informacion de las fuentes registradas en el sistema
     * @return List<FnttFuente>
     * @throws IdeamxException
     */
    public List<FnttFuente> getAllFuentes()throws IdeamException;
    
    /**
     * Retorna la informacion de las fuentes registradas en el sistema por una 
     * corporación especifica.
     * @param autoridad
     * @return List<FnttFuente>
     * @throws IdeamxException
     */
    public List<FnttFuente> getAllFuentes( Autoridades autoridad)throws IdeamException;
    
    /**
     * Inserta o actualiza la informacion de una fuente en el sistema
     * @param fuente
     * @throws IdeamxException
     */
    public FnttFuente updateFuente(FnttFuente fuente)throws IdeamException;
    
    
    /**
     * Inserta o actualiza una fuente cuando se transmite desde un nodo.
     * @param fnt
     * @throws IdeamxException
     */ 
    public void updateFuentePlano(FuentePOJO fnt)throws IdeamException;
    
    public void persistFuentePlano(FuentePOJO fnt)throws IdeamException;
    
    /**
     * Elimina una fuente existente en la bd. 
     * @param fuente
     * @throws IdeamxException
     */
    public void deleteFuente(FnttFuente fuente)throws IdeamException;
    
    /**
     * Elimina una fuente existente en la bd, se ejecuta cuando se recibe la transmisión desde un nodo. 
     * @param fuente
     * @throws IdeamxException
     */
    public void deleteFuentePlano(FuentePOJO fuente)throws IdeamException;
    
    /**
     * Consulta una fuente por id en la bd. 
     * @param id
     * @return FnttFuente
     * @throws IdeamxException
     */
    public FnttFuente getFuente(Long id)throws IdeamException;
    
    /**
     * Consulta una fuente por su nombre y por la autoridad ambiental. 
     * @param nombre
     * @param autoridad
     * @return FnttFuente
     * @throws IdeamxException
     */
    public FnttFuente getFuente(String nombre, Autoridades autoridad)throws IdeamException;
    
    /**
     * Consulta una fuente por su nombre, por la autoridad ambiental y por el tipo de fuente. 
     * @param nombre
     * @param autoridad
     * @param idTipoFuente
     * @return FnttFuente
     * @throws IdeamxException
     */
    public FnttFuente getFuente(String nombre, Autoridades autoridad, Integer idTipoFuente)throws IdeamException;
    
    /**
     * Retorna todas las fuentes por la autoridad ambiental y por el tipo de fuente. 
     * @param autoridad
     * @param idTipoFuente
     * @return List
     * @throws IdeamxException
     */
    public List getFuente(Autoridades autoridad, Integer idTipoFuente)throws IdeamException;
    
    /**
     * Retorna los tramos de una fuente.
     * @param fuente
     * @return List<FnttTramo>
     * @throws IdeamException
     * */
    public List<FnttTramo> getAllTramosByFuente(FnttFuente fuente)throws IdeamException;
    
   /**
    * Retorna los afluentes del tramo.
    * @param tramo
    * @return List<FnttFuente>
    * @throws IdeamException
    * */
  public List<FnttTramoAfluente> getAllAfluentesByTramo(FnttTramo tramo)throws IdeamException;
    
    /**
     * Inserta o actualiza la informacion de un tramo en el sistema
     * @param fuente
     * @throws IdeamxException
     */
    public FnttTramo updateTramo(FnttTramo tramo)throws IdeamException;
 
    /**
     * Metodo empleado por el Nodo IDEAM para recibir tramos de nodos
     * @param tm
     * @return
     * @throws IdeamException
     */
    public void updateTramoPlano(TramoPOJO tm)throws IdeamException;
    
    public void persisteTramoPlano(TramoPOJO tm)throws IdeamException;
    
    /**
     * Elimina un tramo existente en la bd. 
     * @param fuente
     * @throws IdeamxException
     */
    public void deleteTramo(FnttTramo tramo)throws IdeamException;
    
    public void deleteMunicipiosPlano(FuenteTramoMunicipioPOJO fnttFuenteTramoMunicipio)throws IdeamException;
    
    /**
     * Elimina un tramo existente en la bd, este metodo es ejecutado cuando 
     * se informa desde un nodo. 
     * @param tramo
     * @throws IdeamxException
     */
    public void deleteTramoPlano(TramoPOJO tramo)throws IdeamException;    
   
    /**
     * Consulta un tramo por id en la bd. 
     * @param id
     * @return FnttTramo
     * @throws IdeamxException
     */
    public FnttTramo getTramo(Long id)throws IdeamException;
    
  /**
   * Consulta los riesgos de un tramo  
   * @param id
   * @return FnttTramoRiesgos
   * @throws IdeamxException
   */
  public FnttTramoRiesgos getTramoRiesgos(Long id)throws IdeamException;
    
    /**
     * Consulta un tramo por su nombre, fuente y autoridad ambiental de la fuente. 
     * @param nombre
     * @param fuente
     * @param autoridad
     * @return FnttFuente
     * @throws IdeamxException
     */
    public FnttTramo getTramo(String nombre, FnttFuente fuente, Autoridades autoridad)
            throws IdeamException;
    
    /**
     * Consulta un tramo por id en la bd y carga los municipios relacionados. 
     * @param id
     * @return FnttTramo
     * @throws IdeamxException
     */
    public FnttTramo getTramoWithMunicipios(Long id)throws IdeamException;
    
    /**
     * Consulta un tramo por nombre en la bd y fuente padre. 
     * @param nombreTramo
     * @param idFuente
     * @return FnttTramo
     * @throws IdeamxException
     */
    public FnttTramo getTramo(String nombreTramo, Long idFuente) throws IdeamException;
    
    /**
     * Consulta las fuentes por los criterios ingresados. 
     * @param criterios
     * @return List<FnttFuente> 
     * @throws IdeamxException
     */
    public List<FnttFuente> getAllFuentes(CriteriosBusquedaTO criterios)throws IdeamException;
    
    /**
     * Consulta las municipios de un tramo. 
     * @param tramo
     * @return List<FnttFuenteTramoMunicipio> 
     * @throws IdeamxException
     */
    public List<FnttFuenteTramoMunicipio> getAllMunicipiosByTramo(FnttTramo tramo)throws IdeamException;
    
    /**
     * Almacena un municipio relacionado a un tramo.
     * @param municipio
     * @return
     * @throws IdeamException
     */
    public FuenteTramoMunicipioPOJO saveTramoMunicipioPlano(FuenteTramoMunicipioPOJO municipio)throws IdeamException;
    
    /**
     * Elimina un municipio de un tramo existente en la bd. 
     * @param fnttFuenteTramoMunicipio
     * @throws IdeamxException
     */
    public void deleteMunicipios(FnttFuenteTramoMunicipio fnttFuenteTramoMunicipio)throws IdeamException;
    
    /**
     * Consulta los tramos de una fuente por los criterios ingresados. 
     * @param criterios
     * @return List<FnttTramo> 
     * @throws IdeamxException
     */
    public List<FnttTramo> getAllTramos(CriteriosBusquedaTO criterios)throws IdeamException;
  /**
   * Retorna una lista de fuentes por subzona de los tramos 
   * recibido como parametro.
   * @param codigosubzona 
   * @return
   * @throws IdeamException
   */
  public List<FnttFuente> getListaFuentes(Integer codigo)throws IdeamException;
     
    /**
     * Retorna una lista de fuentes por subzona para IDEAM 
     * recibido como parametro.
     * @param codigosubzona 
     * @return
     * @throws IdeamException
     */
    public List<FnttFuente> getListaFuentesIdeam(Integer codigo)throws IdeamException;

       
  /**
   * Retorna una lista de tramos de las fuentes  por subzona 
   * recibido como parametro.
   * @param codigofuente 
   * @return
   * @throws IdeamException
   */
  public List<FnttTramo> getListaTramos(Integer codigoFuente)throws IdeamException;
    
  
public List<DatosTablaAlertDisp> getListaAlertasDisponibilidad(int idArea,
                                                                   int idZona,
                                                                   int idSubzona) throws IdeamException;

    public List<DatosTablaAlertDispDet> getListaAlertasDisponibilidadDet(Long idTramo) throws IdeamException;

    public List<Object[]> getListaOfertaXFuenteTramo(Long idFuente) throws IdeamException;

// HUGO 20141204
    
    public List<DatosTablaAlertIca> getListaAlertasIca(int idArea, int idZona, int idSubzona, long idFuente, String nivel) throws IdeamException ;

    public List<Object[]> getListaMuestrasIca(Long idMuestra) throws IdeamException;

    public List<DatosTablaAlertIcaDet> getListaMuestrasIcaDet(Long idTramo) throws IdeamException;

    public List<DatosTablaAlertIca> getListaAlertasIca(Long idPunto) throws IdeamException ;
    
    public List<DatosTablaAlertIca> getListaAlertasIcaFuente(Long idFuente) throws IdeamException ;

    public List<FnttConflicto> getConflictosXFuente(Long idFuente) throws IdeamException ;
    
    public FnttConflicto updateConflicto(FnttConflicto conflicto) throws IdeamException;

    public List<TipoConflicto> getTipoConflictoXConflicto(Long idConflicto) throws IdeamException, NoResultException;

    public void deleteTipoConflicto(TipoConflicto tipoConflicto) throws IdeamException;

    public TipoConflicto createTipoConflicto(TipoConflicto tipoConflicto) throws IdeamException;

    public List<OrigenConflicto> getOrigenConflictoXConflicto(Long idConflicto) throws IdeamException, NoResultException;

    public void deleteOrigenConflicto(OrigenConflicto origenConflicto) throws IdeamException;

    public OrigenConflicto createOrigenConflicto(OrigenConflicto origenConflicto) throws IdeamException;

    public List<ActorConflicto> getActorConflictoXConflicto(Long idConflicto) throws IdeamException, NoResultException;

    public ActorConflicto updateActorConflicto(ActorConflicto actorConflicto) throws IdeamException;

    public void deleteActorConflicto(ActorConflicto actorConflicto) throws IdeamException ;

    public List<PoblacionConflicto> getPoblacionConflictoXConflicto(Long idConflicto) throws IdeamException, NoResultException ;

    public void deletePoblacionConflicto(PoblacionConflicto poblacionConflicto) throws IdeamException;

    public PoblacionConflicto createPoblacionConflicto(PoblacionConflicto poblacionConflicto) throws IdeamException;

    public List<DerechoConflicto> getDerechoConflictoXConflicto(Long idConflicto) throws IdeamException, NoResultException;
    
    public void deleteDerechoConflicto(DerechoConflicto derechoConflicto) throws IdeamException;
    
    public DerechoConflicto createDerechoConflicto(DerechoConflicto derechoConflicto) throws IdeamException;

    public List<GestionConflicto> getGestionConflictoXConflicto(Long idConflicto) throws IdeamException, NoResultException;

    public void deleteGestionConflicto(GestionConflicto gestionConflicto) throws IdeamException ;

    public GestionConflicto createGestionConflicto(GestionConflicto gestionConflicto) throws IdeamException;

    public String[][] getConflictosXTipo(Long idAutoridad) throws IdeamException ;

    public List<FnttFuente> getFuentesConflicto(Long idAutoridad) throws IdeamException;
public List<FnttDetalleFuentesV> getDetalleTramosRelacionados(Autoridades autoridad) throws IdeamException ;

    //Buenas practicas
    public FnttBuenasPracticas updateBuenaPractica(FnttBuenasPracticas practica) throws IdeamException;
    public List<FnttBuenasPracticas> getFnttBuenasPracticasXFuente(Long idFuente) throws IdeamException ;    
    public List<FnttPrincipios> getPrincipiosBuenasPracticas() throws IdeamException ;    
    public List<FnttCategorias> getCategoriasBuenasPracticas() throws IdeamException ;    
    public List<FnttProyectosEducativos> getProyectosEducativosBuenasPracticas() throws IdeamException ;
    public List<FnttMotivaciones> getMotivacionesBuenasPracticas() throws IdeamException ; 
    public List<FnttActores> getActoresBuenasPracticas() throws IdeamException ;     
    public List<FnttCostos> getCostosBuenasPracticas() throws IdeamException ; 
    public List<FnttLogros> getLogrosByTipo(String tipo) throws IdeamException;
    public List<FnttActividadesEconomicas> getActividadesEconomicas() throws IdeamException ; 
    public FnttBuenasPracticas initializeFnttBuenasPracticas(FnttBuenasPracticas practica)  throws IdeamException;
    public FnttConflicto initializeFnttConflicto(FnttConflicto conflicto)  throws IdeamException;    
    
    public String[][] getEstadistica(EstadisticasQry qry) throws IdeamException;
		
    public void deleteBuenaPractica(FnttBuenasPracticas practica) throws IdeamException;  
    
  //CDONCEL
  public void updateAforo(FnttTrmAforo aforo)throws IdeamException;
  
  public List<FnttTrmAforo> getLsAforos(FnttTramo tramo) throws IdeamException;
  
  public void borrarAforo(String id)throws IdeamException;
  //FIN CDONCEL
  
  public void delete(Object objeto)throws IdeamException;
    
    public void deleteArray(final Object[] objeto)throws IdeamException;
    
    public void saveOrUpdate(Object objeto)throws IdeamException;
    
    public void saveOrUpdateArray(final Object[] objeto)throws IdeamException;


}




