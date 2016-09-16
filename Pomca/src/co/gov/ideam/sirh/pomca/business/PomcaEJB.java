package co.gov.ideam.sirh.pomca.business;

import co.gov.ideam.sirh.pomca.model.Actividad;
import co.gov.ideam.sirh.pomca.model.Cuenca;
import co.gov.ideam.sirh.pomca.model.Gestion;
import co.gov.ideam.sirh.pomca.model.GestionIndicador;
import co.gov.ideam.sirh.pomca.model.Indicador;
import co.gov.ideam.sirh.pomca.model.Pomca;
import co.gov.ideam.sirh.fuentes.model.FnttFuente;

import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.pomca.model.DeterminanteAmbiental;
import co.gov.ideam.sirh.pomca.model.PomtAfluentesCuenca;
import co.gov.ideam.sirh.pomca.model.PomtArchivosXDeterminantes;
import co.gov.ideam.sirh.pomca.model.PomtArchivosXPlan;
import co.gov.ideam.sirh.pomca.model.PomtComisiones;
import co.gov.ideam.sirh.pomca.model.PomtComunidadesEtnicas;
import co.gov.ideam.sirh.pomca.model.PomtDetalleCuenca;
import co.gov.ideam.sirh.pomca.model.PomtInstrumentosPlanificacion;
import co.gov.ideam.sirh.pomca.model.PomtJurisdiccion;
import co.gov.ideam.sirh.pomca.model.PomtPlanes;
import co.gov.ideam.sirh.pomca.model.Programa;
import co.gov.ideam.sirh.pomca.model.Proyecto;
import co.gov.ideam.sirh.util.IdeamException;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;


@Remote
public interface PomcaEJB {

    
       /*********************************************************************/
    
    /**
     * Retorna el POMCA para la cuenca
     * recibidos como parametro
     * @param codigoCuenca
     * @return
     * @throws IdeamException
     */
    public Pomca getPomca(Long codigoCuenca)throws IdeamException;    
    
    /**
     * Crea o actualiza el POMCA
     * @param pomca
     * @throws IdeamException
     */
     public Pomca updatePomca(Pomca pomca)throws IdeamException;

    /**
     * Retorna las programas de un POMCA 
     * @param pomca
     * @return
     * @throws IdeamException
     */ 
    
    public List<Programa> getProgramas(Pomca pomca)throws IdeamException;

    /**
     * Crea programa
     * @param programa
     * @throws IdeamException
     */
     public Programa addPrograma(Programa programa)throws IdeamException;

    /**
     * Actualiza programa
     * @param programa
     * @throws IdeamException
     */
     public Programa updatePrograma(Programa programa)throws IdeamException;

    /**
     * Borra programa
     * @param programa
     * @throws IdeamException
     */
     public void deletePrograma(Programa programa)throws IdeamException;

    /**
     * Retorna las proyectos de un Programa 
     * @param programa
     * @return
     * @throws IdeamException
     */ 
    
    public List<Proyecto> getProyectos(Programa programa)throws IdeamException;

    /**
     * Crea Proyecto
     * @param proyecto
     * @throws IdeamException
     */

    public Proyecto addProyecto(Proyecto proyecto) throws IdeamException;
    
    /**
        * Retorna el PROYECTO dado su ID
        * recibidos como parametro
        * @param idProyecto
        * @return
        * @throws IdeamException
        */
       public Proyecto getProyecto(Long idProyecto)throws IdeamException;    
       
       /**
        * Actualiza el PROYECTO
        * @param proyecto
        * @throws IdeamException
        */
        public Proyecto updateProyecto(Proyecto proyecto)throws IdeamException;

       /**
        * Retorna las actividades de un PROYECTO 
        * @param proyecto
        * @return
        * @throws IdeamException
        */ 
       
       public List<Actividad> getActividades(Proyecto proyecto)throws IdeamException;

       /**
        * Crea actividad
        * @param actividad
        * @throws IdeamException
        */
        public Actividad addActividad(Actividad actividad)throws IdeamException;

       /**
        * Actualiza actividad
        * @param actividad
        * @throws IdeamException
        */
        public Actividad updateActividad(Actividad actividad)throws IdeamException;

       /**
        * Borra actividad
        * @param actividad
        * @throws IdeamException
        */
        public void deleteActividad(Actividad actividad)throws IdeamException;

      /**
        * Retorna la activida dado su id
        * @param idActividad
        * @return
        * @throws IdeamException
        */ 
		
        public Actividad getActividad(Long idActividad);

       /**
        * Retorna los indicadores de una Actividad 
        * @param actividad
        * @return
        * @throws IdeamException
        */ 


		 
		public List<Indicador> getIndicadores(Actividad actividad)throws IdeamException;

       /**
        * Crea indicador
        * @param indicador
        * @throws IdeamException
        */
        public Indicador addIndicador(Indicador indicador)throws IdeamException;

       /**
        * Crea gestion
        * @param gestion
        * @throws IdeamException
        */

       public Gestion addGestion(Gestion gestion) throws IdeamException;
       
       /**
        * Crea gestionIndicador
        * @param gestionIndicador
        * @throws IdeamException
        */
       public GestionIndicador addGestionIndicador(GestionIndicador gestionIndicador)  throws IdeamException;

    /**
     * Borra proyecto
     * @param proyecto
     * @throws IdeamException
     */
     public void deleteProyecto(Proyecto proyecto)throws IdeamException;

    /**
     * Actualiza indicador
     * @param indicador
     * @throws IdeamException
     */
     public Indicador updateIndicador(Indicador indicador)throws IdeamException;

    /**
     * Borra indicador
     * @param indicador
     * @throws IdeamException
     */
     public void deleteIndicador(Indicador indicador)throws IdeamException;


    /**
     * Retorna las cuencas de una autoridad 
     * @param autoridad ambiental
     * @return
     * @throws IdeamException
     */ 
    
    public List<Cuenca> getCuencas(Autoridades autoridad) throws IdeamException;
    public List<Cuenca> getCuencasAreas(Autoridades autoridad) throws IdeamException;

    /**
     * Retorna todas las cuencas registradas en el sistema
     * @param 
     * @return
     * @throws IdeamException
     */ 
    
    public List<Cuenca> getCuencas() throws IdeamException;

    public List<Cuenca> getCuencasAreas() throws IdeamException;
   

	 /**
     * Actualiza la  informacion de un pomcas
     * @param PomptPlanes pomca (el plan que desea actualizar)
     * @throws IdeamException
     */
    public PomtPlanes updatePomt(PomtPlanes pomt) throws IdeamException;


    /**
     * Retorna una lista de los predios registrados en el sistema
     * @return List PomtPlanes
     * @throws IdeamException
     */
    public List<PomtPlanes> getAllPomtPlanes() throws IdeamException;

    public PomtPlanes getPomtPlanesByIdCuenca(Long idCuenca) throws IdeamException;

    public void updatePomtPlanes(PomtPlanes plan) throws IdeamException;

    public void removePomtPlanes(PomtPlanes plan) throws IdeamException;


    /**************************   COMISIONES   *****************************/
    public List<PomtComisiones> getAllPomtComisionesByPlan(PomtPlanes plan) throws IdeamException;

    public void updatePomtComisiones(PomtComisiones comision) throws IdeamException;

    public void deletePomtComision(PomtComisiones comision) throws IdeamException;

    public void deletePomtComisionesFromPlan(PomtPlanes planes) throws IdeamException;

    /*********************************************************************/


    /**************************   JURISDICCIONES   *****************************/
    public List<PomtJurisdiccion> getAllPomtJurisdiccionByPlan(PomtPlanes plan) throws IdeamException;

    public void updatePomtJurisdiccion(PomtJurisdiccion jurisdiccion, Long idAutoridad) throws IdeamException;

    public void deletePomtJurisdicciones(PomtJurisdiccion jurisdiccion) throws IdeamException;

    public void deletePomtJurisdiccionesFromPlan(PomtPlanes plan) throws IdeamException;
    /*************************************************************************/

    /**************************   INSTRUMENTOS DE PLANIFICACION   **************/
    public List<PomtInstrumentosPlanificacion> getAllPomtInstrumentosPlanificacionByPlan(PomtPlanes plan) throws IdeamException;

    public void updatePomtInstrumentosPlanificacion(PomtInstrumentosPlanificacion InstrumentosPlanificacion) throws IdeamException;

    public void deletePomtInstrumentosPlanificacion(PomtInstrumentosPlanificacion InstrumentosPlanificacion) throws IdeamException;

    public void deletePomtInstrumentosPlanificacionesFromPlan(PomtPlanes plan) throws IdeamException;
    /*************************************************************************/

    /**************************   COMUNIDADES ETNICAS   **********************/
    public List<PomtComunidadesEtnicas> getAllPomtComunidadesEtnicasByPlan(PomtPlanes plan) throws IdeamException;

    public void updatePomtComunidadesEtnicas(PomtComunidadesEtnicas ComunidadesEtnicas) throws IdeamException;

    public void deletePomtComunidadesEtnicas(PomtComunidadesEtnicas ComunidadesEtnicas) throws IdeamException;

    public void deletePomtComunidadesEtnicasesFromPlan(PomtPlanes plan) throws IdeamException;
    /*************************************************************************/
    
    // ZSDG IN
    /**************************   DETERMINANTES AMBIENTAL   **********************/
    public List<DeterminanteAmbiental> getAllDeterminanteAmbientalByPlan(PomtPlanes plan) throws IdeamException;

    public void updateDetermianteAmbiental(DeterminanteAmbiental ComunidadesEtnicas) throws IdeamException;

    public void deleteDeterminanteAmbiental(DeterminanteAmbiental ComunidadesEtnicas) throws IdeamException;

    public void deleteDeterminanteAmbientalFromPlan(PomtPlanes plan) throws IdeamException;
    
    public void updateArchivosXDeterminante(PomtArchivosXDeterminantes archideterminante) throws IdeamException;
    /*************************************************************************/
    //ZSDG
    
    

    public void updateArchivosXPlan(PomtArchivosXPlan archiplan) throws IdeamException;
    
    /* HCP Entrega 4 */
        /**
         * Obtiene programa a partir de su id
         * @param idPrograma
         */
         public Programa getPrograma(Long idPrograma);
    
    /**
     * Retorna una cuenca consultada por codigo.
     * */
    public Cuenca getCuenca(Long idCuenca) throws IdeamException;
    
    /**
     * Retorna el detalle de una cuenca consultada por codigo.
     * */
    public PomtDetalleCuenca getDetalleCuenca(Long idCuenca) throws IdeamException;
    
    /**
     * Actualiza el detalle de una cuenca.
     * @param cuenca
     * @throws IdeamException
     */
    public PomtDetalleCuenca updateDetalleCuenca(PomtDetalleCuenca cuenca) throws IdeamException;
    
    /**
     * Retorna una lista con las afluentes relacionadas a una cuenca.
     * @param idCuenca
     * @throws IdeamException
     */
    public List<PomtAfluentesCuenca> getAllAfluentesCuenca(long idCuenca) throws IdeamException;

}
