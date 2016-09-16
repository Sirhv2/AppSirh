package co.gov.ideam.sirh.pomca.view;


import co.gov.ideam.sirh.documentos.business.DocumentoEJB;
import co.gov.ideam.sirh.parametros.business.ParametrosEJB;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.pomca.business.PomcaEJB;
import co.gov.ideam.sirh.pomca.model.PomtArchivosXPlan;
import co.gov.ideam.sirh.pomca.model.PomtComisiones;
import co.gov.ideam.sirh.pomca.model.PomtComunidadesEtnicas;
import co.gov.ideam.sirh.pomca.model.PomtInstrumentosPlanificacion;
import co.gov.ideam.sirh.pomca.model.PomtJurisdiccion;
import co.gov.ideam.sirh.pomca.model.PomtPlanes;
import co.gov.ideam.sirh.pomca.model.Actividad;
import co.gov.ideam.sirh.pomca.model.Cuenca;
import co.gov.ideam.sirh.pomca.model.DeterminanteAmbiental;
import co.gov.ideam.sirh.pomca.model.Gestion;
import co.gov.ideam.sirh.pomca.model.GestionIndicador;
import co.gov.ideam.sirh.pomca.model.Indicador;
import co.gov.ideam.sirh.pomca.model.Pomca;
import co.gov.ideam.sirh.pomca.model.PomtAfluentesCuenca;
import co.gov.ideam.sirh.pomca.model.PomtArchivosXDeterminantes;
import co.gov.ideam.sirh.pomca.model.PomtDetalleCuenca;
import co.gov.ideam.sirh.pomca.model.Programa;
import co.gov.ideam.sirh.pomca.model.Proyecto;
import co.gov.ideam.sirh.servlet.ServletLocator;
import co.gov.ideam.sirh.util.IdeamException;

import java.util.List;
import javax.ejb.EJB;

public class PomcaDelegate {
   
    private PomcaEJB pomcaService = null;
    private DocumentoEJB documentoService = null; 
    /**
     * Utilizada para implementar singleton
     */
    private PomcaDelegate instance;
    
    @EJB
    private ParametrosEJB parametrosService;

    /**
     * Retorna una instancia a la clase
     * @return
     */
    public static PomcaDelegate getInstance() throws IdeamException {
        //if (instance == null) {
        // instance = new PomcaDelegate();
        //}
        //return instance;
        return new PomcaDelegate();
    }

    private PomcaDelegate() throws IdeamException {
        pomcaService = ServletLocator.getPomcaService();
        documentoService = ServletLocator.getDocumentoService();
    }

    //IMPLEMENTACIÖN DE  METODOS DE NEGOCIO
    /**
     * Retorna los programas de un pomca 
     * @param pomca
     * @return
     * @throws IdeamException
     */ 
    
    public List<Programa> getProgramas(Pomca pomca) throws IdeamException{
        return pomcaService.getProgramas(pomca);
    }


    /**
     * Crea un programa asociado a un pomca
     * @param programa
     * @throws IdeamException
     */
    public Programa addPrograma(Programa programa) throws IdeamException{        
        return pomcaService.addPrograma(programa);
    }
    
    /**
     * Actualiza un programa asociado a un pomca
     * @param programa
     * @throws IdeamException
     */
    public Programa updatePrograma(Programa programa) throws IdeamException{        
        return pomcaService.updatePrograma(programa);
    }

    /**
     * Elimina un programa asociado a un pomca
     * @param programa
     * @throws IdeamException
     */
    public void deletePrograma(Programa programa) throws IdeamException{        
        pomcaService.deletePrograma(programa);
    }

    public List<Proyecto> getProyectos(Programa programa) throws IdeamException{
        return pomcaService.getProyectos(programa);
    }

    /**
     * Crea un proyecto asociado a un programa
     * @param proyecto
     * @throws IdeamException
     */
    public Proyecto addProyecto(Proyecto proyecto) throws IdeamException{        
        return pomcaService.addProyecto(proyecto);
    }

    /**
     * Retorna el PROYECTO para un ID
     * @param idProyecto
     * @return
     * @throws IdeamException
     */
    public Proyecto getProyecto(Long idProyecto)throws IdeamException{
        return pomcaService.getProyecto(idProyecto);
    }

    /**
     * Crea o actualiza la informacion del PROYECTO
     * @param pomca
     * @throws IdeamException
     */
    public Proyecto updateProyecto(Proyecto proyecto) throws IdeamException{        
        return pomcaService.updateProyecto(proyecto);
    }

    /**
     * Retorna las actividades de un proyecto 
     * @param proyecto
     * @return
     * @throws IdeamException
     */ 
    public List<Actividad> getActividades(Proyecto proyecto) throws IdeamException{
        return pomcaService.getActividades(proyecto);
    }
    
    /**
     * Crea actividad
     * @param actividad
     * @throws IdeamException
     */
     public Actividad addActividad(Actividad actividad)throws IdeamException {
        return pomcaService.addActividad(actividad);
    }

    /**
     * Actualiza actividad
     * @param actividad
     * @throws IdeamException
     */
    public Actividad updateActividad(Actividad actividad)throws IdeamException {
        return pomcaService.updateActividad(actividad);
    }

    /**
     * Borra actividad
     * @param actividad
     * @throws IdeamException
     */
    public void deleteActividad(Actividad actividad)throws IdeamException {
        pomcaService.deleteActividad(actividad);
    }

    /**
     * Retorna los indicadores de una Actividad 
     * @param actividad
     * @return
     * @throws IdeamException
     */ 

    public List<Indicador> getIndicadores(Actividad actividad)throws IdeamException {
        return pomcaService.getIndicadores(actividad);
    }

    /**
     * Crea indicador
     * @param indicador
     * @throws IdeamException
     */
    public Indicador addIndicador(Indicador indicador)throws IdeamException {
        return pomcaService.addIndicador(indicador);
    }

    /**
     * Crea gestion
     * @param gestion
     * @throws IdeamException
     */
    public Gestion addGestion(Gestion gestion) throws IdeamException {
        return pomcaService.addGestion(gestion);
    }

    /**
     * Crea GestionIndicador
     * @param gestionIndicador
     * @throws IdeamException
     */
    
    public GestionIndicador addGestionIndicador(GestionIndicador gestionIndicador)  throws IdeamException {
        return pomcaService.addGestionIndicador(gestionIndicador);
    }


    /**
     * Borra indicador
     * @param indicador
     * @throws IdeamException
     */
    public void deleteIndicador(Indicador indicador)throws IdeamException {
        pomcaService.deleteIndicador(indicador);
    }


    /**
     * Borra proyecto
     * @param proyecto
     * @throws IdeamException
     */
    public void deleteProyecto(Proyecto proyecto)throws IdeamException {
        pomcaService.deleteProyecto(proyecto);
    }

    /**
     * Actualiza un indicador asociado a una actividad
     * @param indicador
     * @throws IdeamException
     */
    public Indicador updateIndicador(Indicador indicador) throws IdeamException{        
        return pomcaService.updateIndicador(indicador);
    }
    /**
     * Crea o actualiza la informacion del POMCA
     * @param pomca
     * @throws IdeamException
     */
    public Pomca updatePomca(Pomca pomca) throws IdeamException{        
        return pomcaService.updatePomca(pomca);
    }
    
    /**
     * Retorna el POMCA para una cuenca dada
     * @param codigoCuenca
     * @return
     * @throws IdeamException
     */
    public Pomca getPomca(Long codigoCuenca)throws IdeamException{
        return pomcaService.getPomca(codigoCuenca);
    }
    
    /**
     * Retorna el plan de ordenamiento para la fuente y autoridad ambiental
     * recibidos como parametro
     * @param codigoFuente
     * @param codigoAutoridad
     * @return
     * @throws IdeamException
     */
    public List<PomtPlanes> getPlanesOrdenamiento() throws IdeamException {
        return pomcaService.getAllPomtPlanes();
    }

    public PomtPlanes getPlanesByIdCuenca(Long idCuenca) throws IdeamException {
        return pomcaService.getPomtPlanesByIdCuenca(idCuenca);
    }

	
    public void updatePomtPlanes(PomtPlanes plan) throws IdeamException {
        pomcaService.updatePomtPlanes(plan);
    }

    public void updatePomtComisiones(PomtComisiones comision) throws IdeamException {
        pomcaService.updatePomtComisiones(comision);
    }

    public void deletePomtComisiones(PomtComisiones comision) throws IdeamException {
        pomcaService.deletePomtComision(comision);
    }

    public void updatePomtInstrumentosPlanificacion(PomtInstrumentosPlanificacion instrumento) throws IdeamException {
        pomcaService.updatePomtInstrumentosPlanificacion(instrumento);
    }

    public void deletePomtInstrumentosPlanificacion(PomtInstrumentosPlanificacion instrumento) throws IdeamException {
        pomcaService.deletePomtInstrumentosPlanificacion(instrumento);
    }

// ZSDG IN
    public void updateDeterminanteAmbiental(DeterminanteAmbiental determinante) throws IdeamException {
        pomcaService.updateDetermianteAmbiental(determinante);
    }
    
  public void deleteDeterminante(DeterminanteAmbiental determinanteAmbiental) throws IdeamException {
      pomcaService.deleteDeterminanteAmbiental(determinanteAmbiental);
  }
  
    public void updateArchivosXDeterminante(PomtArchivosXDeterminantes archideterminante) throws IdeamException {
        pomcaService.updateArchivosXDeterminante(archideterminante);
  }
// ZSDG
    public void updatePomtComunidadesEtnicas(PomtComunidadesEtnicas comunidad) throws IdeamException {
        pomcaService.updatePomtComunidadesEtnicas(comunidad);
    }

    public void deletePomtComunidadesEtnicas(PomtComunidadesEtnicas comunidad) throws IdeamException {
        pomcaService.deletePomtComunidadesEtnicas(comunidad);
    }

    public void updatePomtJurisdiccion(PomtJurisdiccion jurisdiccion, Long idAutoridad) throws IdeamException {
        pomcaService.updatePomtJurisdiccion(jurisdiccion, idAutoridad);
    }

    public void deletePomtJurisdiccion(PomtJurisdiccion jurisdiccion) throws IdeamException {
        pomcaService.deletePomtJurisdicciones(jurisdiccion);
    }
 
    public void updateArchivosXPlan(PomtArchivosXPlan archiplan)throws IdeamException{
        pomcaService.updateArchivosXPlan(archiplan);
    }
    
    public void removePomtPlan(PomtPlanes plan) throws IdeamException{
        pomcaService.removePomtPlanes(plan);
    }

/* HCP entrega 4 */
        public Programa getPrograma(Long idPrograma)throws IdeamException{
        return pomcaService.getPrograma(idPrograma);
    }

    /* HCP entrega 5 */
        public Actividad getActividad(Long idActividad)throws IdeamException{
            return pomcaService.getActividad(idActividad);
        }
        
        
    /* HCP entrega 6 */
    public List<Cuenca> getAllCuencas() throws IdeamException {
        return pomcaService.getCuencas();
    }
        
    public List<Cuenca> getAllCuencas(Autoridades autoridad) throws IdeamException {
        return pomcaService.getCuencas(autoridad);
    }
	
    public List<Cuenca> getAllCuencasAreas(Autoridades autoridad) throws IdeamException {
        return pomcaService.getCuencasAreas(autoridad);
    }
        

 
    public List<Cuenca> getAllCuencasAreas() throws IdeamException {
        return pomcaService.getCuencasAreas();
    }        
    
    public Cuenca getCuenca(Long idCuenca) throws IdeamException{
        return pomcaService.getCuenca(idCuenca);
    }
    
    public PomtDetalleCuenca getDetalleCuenca(Long idCuenca) throws IdeamException{
        return pomcaService.getDetalleCuenca(idCuenca);
    }
    
    /**
     * Actualiza el detalle de una cuenca.
     * @param cuenca
     * @throws IdeamException
     */
    public PomtDetalleCuenca updateDetalleCuenca(PomtDetalleCuenca cuenca) throws IdeamException{
        return pomcaService.updateDetalleCuenca(cuenca);
    }
    
    /**
     * Retorna una lista con las afluentes relacionadas a una cuenca.
     * @param idCuenca
     * @throws IdeamException
     */
    public List<PomtAfluentesCuenca> getAllAfluentesCuenca(long idCuenca) throws IdeamException {
        return pomcaService.getAllAfluentesCuenca(idCuenca);
    }

}
