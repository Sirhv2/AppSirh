package co.gov.ideam.sirh.fuentes.view;

import co.gov.ideam.sirh.usuarios.agua.business.UsuariosAguaEJB;
import co.gov.ideam.sirh.usuarios.agua.view.UsuariosAguaDelegate;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.fuentes.business.FuentesEJB;

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
import co.gov.ideam.sirh.fuentes.model.FnttDetalleFuentesV;

import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.fuentes.model.FnttLogros;
import co.gov.ideam.sirh.fuentes.model.FnttMotivaciones;
import co.gov.ideam.sirh.fuentes.model.FnttPrincipios;
import co.gov.ideam.sirh.fuentes.model.FnttProyectosEducativos;
import co.gov.ideam.sirh.fuentes.model.FnttTramo;
import co.gov.ideam.sirh.fuentes.model.FnttTrmAforo;
import co.gov.ideam.sirh.fuentes.model.FnttTramoAfluente;
import co.gov.ideam.sirh.fuentes.model.FnttTramoRiesgos;
import co.gov.ideam.sirh.fuentes.model.GestionConflicto;
import co.gov.ideam.sirh.fuentes.model.OrigenConflicto;
import co.gov.ideam.sirh.fuentes.model.PoblacionConflicto;
import co.gov.ideam.sirh.fuentes.model.TipoConflicto;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.PartZonificListas;
import co.gov.ideam.sirh.parametros.view.ParametrosDelegate;
import co.gov.ideam.sirh.servlet.ServletLocator;

import co.gov.ideam.sirh.util.EstadisticasQry;

import java.util.List;

import javax.persistence.NoResultException;

public class FuenteDelegate {
    
    //...........................................................
    //ESTE BLOQUE SIEMPRE ES ASI.................................
    //...........................................................
    /**
     * Referencia al EJB de Seguridad
     */
    private static FuentesEJB fuenteService = null;    
    /**
    * Utilizada para implementar singleton
    */
    private static FuenteDelegate instance;
   
     /**
      * Retorna una instancia a la clase
      * @return
      */
    public static FuenteDelegate getInstance() throws IdeamException{
        if (instance==null){
            instance = new FuenteDelegate();
        }
        return instance;
    }
     
    /**
    * Construcutor privado para implementar singleton
    */
    private FuenteDelegate(){
        fuenteService = ServletLocator.getFuentesService();
    }
   
    //................................................................
    //CIERRA BLOQUE SIEMPRE IGUAL.....................................
    //................................................................
    
    //IMPLEMENTACIÖN DE  METODOS DE NEGOCIO
     
    /**
     * Retorna una lista con los usuarios registrados
     * recibida como parametro
     * @return
     * @throws IdeamException
     */
    public List getAllFuentes()throws IdeamException{
        return fuenteService.getAllFuentes();
    }  
    
    /**
     * Retorna la informacion de las fuentes registradas en el sistema por una 
     * corporación especifica.
     * @param autoridad
     * @return List<FnttFuente>
     * @throws IdeamxException
     */
    public List<FnttFuente> getAllFuentes( Autoridades autoridad)throws IdeamException{
        return fuenteService.getAllFuentes(autoridad);
    }
    
    /**
     * Inserta o actualiza la informacion de una fuente en el sistema
     * @param fuente
     * @throws FenixException
     */
    public FnttFuente updateFuente(FnttFuente fuente)throws IdeamException{
        return fuenteService.updateFuente(fuente);
    }
    
    /**
     * Elimina una fuente existente en la bd. 
     * @param fuente
     * @throws FenixException
     */
    public void deleteFuente(FnttFuente fuente)throws IdeamException{
        fuenteService.deleteFuente(fuente);
    }
    
    /**
     * Consulta una fuente por codigo en la bd.
     * @param id
     * @return FnttFuente
     * @throws IdeamxException
     */
    public FnttFuente getFuente(Long id)throws IdeamException{
        return fuenteService.getFuente(id);
    }
    
    /**
     * Consulta una fuente por su nombre y por la autoridad ambiental. 
     * @param nombre
     * @param autoridad
     * @return FnttFuente
     * @throws IdeamxException
     */
    public FnttFuente getFuente(String nombre, Autoridades autoridad)throws IdeamException{
        return fuenteService.getFuente(nombre, autoridad);
    }
    
     /**
      * Consulta una fuente por su nombre, por la autoridad ambiental y por el tipo de fuente. 
      * @param nombre
      * @param autoridad
      * @param idTipoFuente
      * @return FnttFuente
      * @throws IdeamxException
      */
    public FnttFuente getFuente(String nombre, Autoridades autoridad, Integer idTipoFuente)throws IdeamException{
        return fuenteService.getFuente(nombre, autoridad, idTipoFuente);
    }
    
    /**
     * Retorna todas las fuentes por la autoridad ambiental y por el tipo de fuente. 
     * @param autoridad
     * @param idTipoFuente
     * @return List
     * @throws IdeamxException
     */
    public List getFuente(Autoridades autoridad, Integer idTipoFuente)throws IdeamException{
        return fuenteService.getFuente(autoridad, idTipoFuente);
    }
    
    /**
     * Retorna los tramos de una fuente.
     * @param fuente
     * @return List<FnttTramo>
     * @throws IdeamException
     * */
    public List<FnttTramo> getAllTramosByFuente(FnttFuente fuente)throws IdeamException{
        return fuenteService.getAllTramosByFuente(fuente);
    }
    
  /**
   * Retorna los afluentes del tramo.
   * @param tramo
   * @return List<FnttFuente>
   * @throws IdeamException
   * */
  public List<FnttTramoAfluente> getAllAfluentesByTramo(FnttTramo tramo)throws IdeamException{
      return fuenteService.getAllAfluentesByTramo(tramo);
  }
    
    /**
     * Inserta o actualiza la informacion de un tramo en el sistema
     * @param fuente
     * @throws FenixException
     */
    public FnttTramo updateTramo(FnttTramo tramo)throws IdeamException{
        return fuenteService.updateTramo(tramo);
    }
    
    /**
     * Elimina un tramo existente en la bd. 
     * @param fuente
     * @throws FenixException
     */
    public void deleteTramo(FnttTramo tramo)throws IdeamException{
        fuenteService.deleteTramo(tramo);
    }
    
    /**
     * Consulta una fuente por codigo en la bd.
     * @param id
     * @return FnttTramo
     * @throws IdeamxException
     */
    public FnttTramo getTramo(Long id)throws IdeamException{
        return fuenteService.getTramo(id);
    }
    
  /**
   * Consulta los riesgos de un tramo 
   * @param id
   * @return FnttTramoRiesgos
   * @throws IdeamxException
   */
  public FnttTramoRiesgos getTramoRiesgos(Long id)throws IdeamException{
      return fuenteService.getTramoRiesgos(id);
  }
    
    /**
     * Consulta un tramo por su nombre, fuente y autoridad ambiental de la fuente. 
     * @param nombre
     * @param fuente
     * @param autoridad
     * @return FnttFuente
     * @throws IdeamxException
     */
    public FnttTramo getTramo(String nombre, FnttFuente fuente, Autoridades autoridad)
    throws IdeamException{
        return fuenteService.getTramo(nombre, fuente, autoridad);
    }
    
    /**
     * Consulta un tramo por codigo en la bd y carga los municipios relacionados.
     * @param id
     * @return FnttTramo
     * @throws IdeamxException
     */
    public FnttTramo getTramoWithMunicipios(Long id)throws IdeamException{
        return fuenteService.getTramoWithMunicipios(id);
    }
    
    
    /**
     * Consulta las fuentes por los criterios ingresados. 
     * @param criterios
     * @return List<FnttFuente> 
     * @throws IdeamxException
     */
    public List<FnttFuente> getAllFuentes(CriteriosBusquedaTO criterios)
            throws IdeamException{
        return fuenteService.getAllFuentes(criterios);
    }
    
    /**
     * Consulta los tramos de una fuente por los criterios ingresados. 
     * @param criterios
     * @return List<FnttTramo> 
     * @throws IdeamxException
     */
    public List<FnttTramo> getAllTramos(CriteriosBusquedaTO criterios)throws IdeamException{
        return fuenteService.getAllTramos(criterios);
    }

    
  /**
   * Retorna una lista de fuentes por subzona de los tramos 
   * recibido como parametro.
   * @param codigosubzona 
   * @return
   * @throws IdeamException
   */
  public List<FnttFuente> getListaFuentes(Integer codigo)throws IdeamException{
      return fuenteService.getListaFuentes(codigo);
  }
  public List<FnttFuente> getListaFuentesIdeam(Integer codigo)throws IdeamException{
      return fuenteService.getListaFuentesIdeam(codigo);
  }

    
  /**
   * Retorna una lista de tramos por fuentes por subzona 
   * recibido como parametro.
   * @param codigofuente
   * @return
   * @throws IdeamException
   */
  public  List<FnttTramo> getListaTramos(Integer codigoFuente)throws IdeamException{
      return  fuenteService.getListaTramos(codigoFuente);
  }

// HUGO 20141030    
   public List<DatosTablaAlertDisp> getListaAlertasDisponibilidad(int idArea, int idZona, int idSubzona) throws IdeamException {
       return  fuenteService.getListaAlertasDisponibilidad(idArea,idZona, idSubzona);
   }

    public List<DatosTablaAlertDispDet> getListaAlertasDisponibilidadDet(Long idTramo) throws IdeamException {
        return  fuenteService.getListaAlertasDisponibilidadDet(idTramo);
    }

    public List<Object[]> getListaOfertaXFuenteTramo(Long idFuente) throws IdeamException {
        return  fuenteService.getListaOfertaXFuenteTramo(idFuente);
    }
    
// HUGO 20141204
    
    public List<DatosTablaAlertIca> getListaAlertasIca(int idArea, int idZona, int idSubzona, long idFuente, String nivel) throws IdeamException {
        return  fuenteService.getListaAlertasIca(idArea,idZona, idSubzona,idFuente,nivel);
    }

    public List<DatosTablaAlertIca> getListaAlertasIca(Long idPunto) throws IdeamException {
        return  fuenteService.getListaAlertasIca(idPunto);
    }

    
    public List<DatosTablaAlertIca> getListaAlertasIcaFuente(Long idFuente) throws IdeamException {
        return  fuenteService.getListaAlertasIcaFuente(idFuente);
    }
        
    public List<Object[]> getListaMuestrasIca(Long idMuestra) throws IdeamException {
        return  fuenteService.getListaMuestrasIca(idMuestra);
        
        
    }

    public List<DatosTablaAlertIcaDet> getListaMuestrasIcaDet(Long idMuestra) throws IdeamException{
        return  fuenteService.getListaMuestrasIcaDet(idMuestra);
        
    }
    
    public List<FnttConflicto> getConflictosXFuente(Long idFuente)throws IdeamException{
        return  fuenteService.getConflictosXFuente(idFuente);
    }
    
    public FnttConflicto updateConflicto(FnttConflicto conflicto) throws IdeamException {
        return  fuenteService.updateConflicto(conflicto);
    }

    public List<TipoConflicto> getTipoConflictoXConflicto(Long idConflicto) throws IdeamException, NoResultException {
        return  fuenteService.getTipoConflictoXConflicto(idConflicto);
    }

    public void deleteTipoConflicto(TipoConflicto tipoConflicto) throws IdeamException {
        fuenteService.deleteTipoConflicto(tipoConflicto);
    }

    public TipoConflicto createTipoConflicto(TipoConflicto tipoConflicto) throws IdeamException {
        return  fuenteService.createTipoConflicto(tipoConflicto);
    }

    public List<OrigenConflicto> getOrigenConflictoXConflicto(Long idConflicto) throws IdeamException, NoResultException {
        return  fuenteService.getOrigenConflictoXConflicto(idConflicto);
    }

    public void deleteOrigenConflicto(OrigenConflicto origenConflicto) throws IdeamException {
        fuenteService.deleteOrigenConflicto(origenConflicto);
    }

    public OrigenConflicto createOrigenConflicto(OrigenConflicto origenConflicto) throws IdeamException {
        return  fuenteService.createOrigenConflicto(origenConflicto);
    }

    public List<ActorConflicto> getActorConflictoXConflicto(Long idConflicto) throws IdeamException, NoResultException {
        return  fuenteService.getActorConflictoXConflicto(idConflicto);
    }

    public ActorConflicto updateActorConflicto(ActorConflicto actorConflicto) throws IdeamException {
        return  fuenteService.updateActorConflicto(actorConflicto);
    }

    public void deleteActorConflicto(ActorConflicto actorConflicto) throws IdeamException {
        fuenteService.deleteActorConflicto(actorConflicto);
    }

    public List<PoblacionConflicto> getPoblacionConflictoXConflicto(Long idConflicto) throws IdeamException, NoResultException {
        return  fuenteService.getPoblacionConflictoXConflicto(idConflicto);
    }

    public void deletePoblacionConflicto(PoblacionConflicto poblacionConflicto) throws IdeamException {
        fuenteService.deletePoblacionConflicto(poblacionConflicto);
    }

    public PoblacionConflicto createPoblacionConflicto(PoblacionConflicto poblacionConflicto) throws IdeamException {
        return  fuenteService.createPoblacionConflicto(poblacionConflicto);
    }

    public List<DerechoConflicto> getDerechoConflictoXConflicto(Long idConflicto) throws IdeamException, NoResultException {
        return  fuenteService.getDerechoConflictoXConflicto(idConflicto);

    }
    
    public void deleteDerechoConflicto(DerechoConflicto derechoConflicto) throws IdeamException {
        fuenteService.deleteDerechoConflicto(derechoConflicto);
    }
    
    
    public DerechoConflicto createDerechoConflicto(DerechoConflicto derechoConflicto) throws IdeamException {
        return  fuenteService.createDerechoConflicto(derechoConflicto);

    }

    public List<GestionConflicto> getGestionConflictoXConflicto(Long idConflicto) throws IdeamException, NoResultException{
        return  fuenteService.getGestionConflictoXConflicto(idConflicto);
    }

    public void deleteGestionConflicto(GestionConflicto gestionConflicto) throws IdeamException {
        fuenteService.deleteGestionConflicto(gestionConflicto);
    }

    public GestionConflicto createGestionConflicto(GestionConflicto gestionConflicto) throws IdeamException {
        return  fuenteService.createGestionConflicto(gestionConflicto);
    }
    
    public String[][] getConflictosXTipo(Long idAutoridad) throws IdeamException{
        return fuenteService.getConflictosXTipo( idAutoridad);
    
    }

    public List<FnttFuente> getFuentesConflicto(Long idAutoridad) throws IdeamException{
        return fuenteService.getFuentesConflicto( idAutoridad);
    }
    
    //CDONCEL 12-NOV-2015
  public void updateAforo(FnttTrmAforo aforo)throws IdeamException{
   fuenteService.updateAforo( aforo);
  }
  //CDONCEL 13-NOV-2015
  public List<FnttTrmAforo> findAforos(FnttTramo tramo)throws IdeamException{
  return fuenteService.getLsAforos(tramo);
  }
  
  //CDONCEL 12-NOV-2015
  public void borrarAforo(String id)throws IdeamException{
  fuenteService.borrarAforo( id);
  }

    //Buenas Practicas
    public FnttBuenasPracticas updateBuenaPractica(FnttBuenasPracticas practica) throws IdeamException {
        return  fuenteService.updateBuenaPractica(practica);
    }
    public List<FnttBuenasPracticas> getBuenasPracticasXFuente(Long idFuente)throws IdeamException{
        return  fuenteService.getFnttBuenasPracticasXFuente(idFuente);
    }
    public List<FnttPrincipios> getPrincipiosBuenasPracticas()throws IdeamException{
        return  fuenteService.getPrincipiosBuenasPracticas();
    }
    public List<FnttCategorias> getCategoriasBuenasPracticas()throws IdeamException{
        return  fuenteService.getCategoriasBuenasPracticas();
    }
    public List<FnttProyectosEducativos> getProyectosEducativosBuenasPracticas()throws IdeamException{
        return  fuenteService.getProyectosEducativosBuenasPracticas();
    }
    public List<FnttMotivaciones> getMotivacionesBuenasPracticas()throws IdeamException{
        return  fuenteService.getMotivacionesBuenasPracticas();
    }
    public List<FnttActores> getActoresBuenasPracticas()throws IdeamException{
        return  fuenteService.getActoresBuenasPracticas();
    }
    public List<FnttCostos> getCostosBuenasPracticas()throws IdeamException{
        return  fuenteService.getCostosBuenasPracticas();
    }
    public List<FnttLogros> getLogrosByTipo(String tipo) throws IdeamException{
        return  fuenteService.getLogrosByTipo(tipo);
    }
    public List<FnttActividadesEconomicas> getActividadesEconomicas() throws IdeamException{
        return  fuenteService.getActividadesEconomicas();
    }
    
    public FnttBuenasPracticas initializeFnttBuenasPracticas(FnttBuenasPracticas practica)  throws IdeamException {
        return fuenteService.initializeFnttBuenasPracticas(practica);
    }
    
    public FnttConflicto initializeFnttConflicto(FnttConflicto conflicto)  throws IdeamException {
        return fuenteService.initializeFnttConflicto(conflicto);
    }
        
    public String[][] getEstadistica(EstadisticasQry qry) throws IdeamException{
        return fuenteService.getEstadistica(qry);
    }
    
public void deleteBuenaPractica(FnttBuenasPracticas practica) throws IdeamException {
        fuenteService.deleteBuenaPractica(practica);
    }
 public List<FnttDetalleFuentesV> getDetalleTramosRelacionados(Autoridades autoridad) throws IdeamException {
       return fuenteService.getDetalleTramosRelacionados( autoridad);

    }


// Metodos Para eliminar y guardar objetos general
    public void delete(Object objeto)throws IdeamException{
      fuenteService.delete(objeto);
    }
    
    public void deleteArray(final Object[] objeto)throws IdeamException{
      fuenteService.deleteArray(objeto);
    }
    
    public void saveOrUpdate(Object objeto)throws IdeamException{
      fuenteService.saveOrUpdate(objeto);
    }
    
    public void saveOrUpdateArray(final Object[] objeto)throws IdeamException{
      fuenteService.saveOrUpdateArray(objeto);
    }
}
