package co.gov.ideam.sirh.pomca.business;


import co.gov.ideam.sirh.documentos.model.Nodo;
import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.fuentes.model.FnttTramo;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.pomca.model.Actividad;
import co.gov.ideam.sirh.pomca.model.Cuenca;
import co.gov.ideam.sirh.pomca.model.DeterminanteAmbiental;
import co.gov.ideam.sirh.pomca.model.Gestion;
import co.gov.ideam.sirh.pomca.model.GestionIndicador;
import co.gov.ideam.sirh.pomca.model.Indicador;
import co.gov.ideam.sirh.pomca.model.Pomca;
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
import co.gov.ideam.sirh.usuarios.agua.model.GeneradorSeq;
import co.gov.ideam.sirh.util.IdeamException;

import java.math.BigDecimal;

import java.sql.SQLIntegrityConstraintViolationException;

import java.util.Iterator;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.exception.ConstraintViolationException;


@Stateless(name = "PomcaEJB", mappedName = "Sirh-PomcaEJB")
@Remote
public class PomcaEJBBean implements PomcaEJB {
    @PersistenceContext(unitName = "SirhPU")
    private EntityManager em;

    public PomcaEJBBean() {
    }

    public PomtPlanes updatePomt(PomtPlanes pomt) throws IdeamException {
        pomt.setId(GeneradorSeq.obtenerSequencia(pomt.getCodigoAutoridad(),
                                                 "seq_pomt_planes", em));
        em.persist(pomt);

        em.flush();
        em.refresh(pomt);
        return pomt;
    }

    public List<Programa> getProgramas(Pomca pomca) throws IdeamException {
        try {
            System.out.println("Entro a bucar programas " + pomca.toString());

            Query query = em.createNamedQuery("Programa.findByPomca");
            query.setParameter("idPomca", pomca.getId());

            List lista = query.getResultList();

            return lista;
        } catch (Exception e) {
            System.out.println("Genero error: " + e.getMessage());

        }

        return null;

    }

    /**
     * Crea programa
     * @param programa
     * @throws IdeamException
     */

    public Programa addPrograma(Programa programa) throws IdeamException {
        programa.setId(GeneradorSeq.obtenerSequencia(programa.getCodigoAutoridad(),
                                                     "seq_programa", em));

        em.persist(programa);
        em.flush();
        em.refresh(programa);
        return programa;
    }

    /**
     * Actualiza programa
     * @param programa
     * @throws IdeamException
     */
    public Programa updatePrograma(Programa programa) throws IdeamException {

        if (programa.getId() == null || programa.getId().longValue() == 0) {
            programa.setId(GeneradorSeq.obtenerSequencia(programa.getCodigoAutoridad(),
                                                         "seq_programa", em));

            em.persist(programa);
        } else {
            programa = em.merge(programa);
        }
        em.flush();
        em.refresh(programa);
        return programa;
    }

    public void deletePrograma(Programa programa) throws IdeamException {
        eliminarPrograma(programa, false);
    }

    public void eliminarPrograma(Programa programa,
                                 boolean recalcular) throws IdeamException {

        Pomca pomca = null;

        if (recalcular)
            pomca = getPomcaXId(programa.getIdPomca());

        List listaProyectos;

        listaProyectos = getProyectos(programa);
        if (listaProyectos != null) {
            Iterator it = listaProyectos.iterator();
            while (it.hasNext()) {
                Proyecto proyecto = (Proyecto)it.next();
                eliminarProyecto(proyecto, false);

            }
        }

        em.flush();
        programa = em.merge(programa);
        em.remove(programa);

        if (recalcular)
            recalcularPomca(pomca);
    }

    public List<Proyecto> getProyectos(Programa programa) throws IdeamException {
        try {
            System.out.println("Entro a bucar proyectos " + programa.getId());

            Query query = em.createNamedQuery("Proyecto.findByPrograma");
            query.setParameter("idPrograma", programa.getId());

            List lista = query.getResultList();

            return lista;
        } catch (Exception e) {
            System.out.println("Genero error: " + e.getMessage());

        }

        return null;
    }

    /**
     * Crea Proyecto
     * @param proyecto
     * @throws IdeamException
     */

    public Proyecto addProyecto(Proyecto proyecto) throws IdeamException {
        proyecto.setId(GeneradorSeq.obtenerSequencia(proyecto.getCodigoAutoridad(),
                                                     "seq_proyecto", em));
        em.persist(proyecto);
        em.flush();
        em.refresh(proyecto);
        return proyecto;
    }

    /****ENTREGA 2 HCENDALES**/

    public Proyecto getProyecto(Long idProyecto) throws IdeamException {
        Query query = em.createNamedQuery("Proyecto.findById");
        query.setParameter("idProyecto", idProyecto);
        Proyecto proyecto = (Proyecto)query.getSingleResult();
        return proyecto;
    }

    public Proyecto updateProyecto(Proyecto proyecto) throws IdeamException {
        if (proyecto.getId() == null || proyecto.getId().longValue() == 0) {
            proyecto.setId(GeneradorSeq.obtenerSequencia(proyecto.getCodigoAutoridad(),
                                                         "seq_proyecto", em));

            em.persist(proyecto);
        } else {
            proyecto = em.merge(proyecto);
        }
        em.flush();
        em.refresh(proyecto);
        return proyecto;

    }

    public List<Actividad> getActividades(Proyecto proyecto) throws IdeamException {
        Query query = em.createNamedQuery("Actividad.findByProyecto");
        query.setParameter("idProyecto", proyecto.getId());

        List lista = query.getResultList();

        return lista;
    }

    public List<Indicador> getIndicadores(Actividad actividad) {
        Query query = em.createNamedQuery("Indicador.findByActividad");
        query.setParameter("idActividad", actividad.getId());

        List lista = query.getResultList();

        return lista;
    }

    public Indicador addIndicador(Indicador indicador) throws IdeamException {
        indicador.setId(GeneradorSeq.obtenerSequencia(indicador.getCodigoAutoridad(),
                                                      "seq_indicador", em));

        em.persist(indicador);
        em.flush();
        em.refresh(indicador);

        Actividad actividad = getActividad(indicador.getIdActividad());
        Proyecto pry = getProyecto(actividad.getIdProyecto());
        Programa prg = getPrograma(pry.getIdPrograma());
        Pomca pomca = getPomcaXId(prg.getIdPomca());

        recalcularPomca(pomca);

        return indicador;
    }


    public GestionIndicador addGestionIndicador(GestionIndicador gestionIndicador) throws IdeamException {

        gestionIndicador.setId(GeneradorSeq.obtenerSequencia(gestionIndicador.getCodigoAutoridad(),
                                                             "seq_gestionind",
                                                             em));

        em.persist(gestionIndicador);
        em.flush();
        em.refresh(gestionIndicador);

        Indicador indicador = getIndicador(gestionIndicador.getIdIndicador());
        Actividad actividad = getActividad(indicador.getIdActividad());
        Proyecto pry = getProyecto(actividad.getIdProyecto());
        Programa prg = getPrograma(pry.getIdPrograma());
        Pomca pomca = getPomcaXId(prg.getIdPomca());

        recalcularPomca(pomca);

        return gestionIndicador;
    }


    public void deleteProyecto(Proyecto proyecto) throws IdeamException {
        eliminarProyecto(proyecto, true);
    }

    public void eliminarProyecto(Proyecto proyecto,
                                 boolean recalcular) throws IdeamException {
        Pomca pomca = null;
        if (recalcular) {
            Programa prg = getPrograma(proyecto.getIdPrograma());
            pomca = getPomcaXId(prg.getIdPomca());
        }

        List listaActividades;
        listaActividades = getActividades(proyecto);
        if (listaActividades != null) {
            Iterator it = listaActividades.iterator();
            while (it.hasNext()) {
                Actividad actividad = (Actividad)it.next();
                eliminarActividad(actividad, false);
            }
        }

        em.flush();
        proyecto = em.merge(proyecto);
        em.remove(proyecto);

        if (recalcular)
            recalcularPomca(pomca);

    }

    /**
     * Actualiza indicador
     * @param indicador
     * @throws IdeamException
     */
    public Indicador updateIndicador(Indicador indicador) throws IdeamException {

        if (indicador.getId() == null || indicador.getId().longValue() == 0) {
            indicador.setId(GeneradorSeq.obtenerSequencia(indicador.getCodigoAutoridad(),
                                                          "seq_indicador",
                                                          em));
            em.persist(indicador);
        } else {
            indicador = em.merge(indicador);
        }
        em.flush();
        em.refresh(indicador);

        Actividad actividad = getActividad(indicador.getIdActividad());
        Proyecto pry = getProyecto(actividad.getIdProyecto());
        Programa prg = getPrograma(pry.getIdPrograma());
        Pomca pomca = getPomcaXId(prg.getIdPomca());

        recalcularPomca(pomca);

        return indicador;
    }


    public Pomca getPomca(Long idCuenca) {
        try {

            Query query = em.createNamedQuery("Pomca.findByCuenca");
            query.setParameter("idCuenca", idCuenca);
            Pomca pomca = (Pomca)query.getSingleResult();
            return pomca;

        } catch (NoResultException e) {
            return null;
        }
    }

    public Pomca updatePomca(Pomca pomca) throws IdeamException {

        if (pomca.getId() == null || pomca.getId().longValue() == 0) {
            pomca.setId(GeneradorSeq.obtenerSequencia(pomca.getCodigoAutoridad(),
                                                      "seq_indicador", em));
            em.persist(pomca);
        } else {
            pomca = em.merge(pomca);
        }
        em.flush();
        em.refresh(pomca);
        return pomca;
    }

    public List<PomtPlanes> getAllPomtPlanes() throws IdeamException {
        Query query = em.createNamedQuery("PomtPlanes.findAll");
        List<PomtPlanes> planes = query.getResultList();
        System.out.println("Cargo planes");
        if (planes != null) {
            for (PomtPlanes item : planes) {
                item.getPomtComisionesList();

                Query query2 =
                    em.createNamedQuery("PomtComisiones.findByPomtPlanes");
                query2.setParameter("pomtPlanes", item);
                item.setPomtComisionesList(query2.getResultList());

                Query query3 =
                    em.createNamedQuery("PomtJurisdiccion.findByPomtPlanes");
                query3.setParameter("pomtPlanes", item);
                item.setPomtJurisdiccionList(query3.getResultList());
            }
            em.clear();
        }
        return planes;
    }

    public PomtPlanes getPomtPlanesByIdCuenca(Long idCuenca) throws IdeamException {
        Query query = em.createNamedQuery("PomtPlanes.findByCuenca");
        query.setParameter("id_cuenca", idCuenca);
        List<PomtPlanes> planes = query.getResultList();
        System.out.println("Cargo planes");
        if (planes != null) {
            for (PomtPlanes plan : planes) {
                plan.getPomtComisionesList();

                Query query2 =
                    em.createNamedQuery("PomtComisiones.findByPomtPlanes");
                query2.setParameter("pomtPlanes", plan);
                plan.setPomtComisionesList(query2.getResultList());

                Query query3 =
                    em.createNamedQuery("PomtJurisdiccion.findByPomtPlanes");
                query3.setParameter("pomtPlanes", plan);
                plan.setPomtJurisdiccionList(query3.getResultList());

                Query query4 =
                    em.createNamedQuery("PomtInstrumentosPlanificacion.findByPomtPlanes");
                query4.setParameter("pomtPlanes", plan);
                plan.setPomtInstrumentosPlanificacionList(query4.getResultList());

                Query query5 =
                    em.createNamedQuery("PomtComunidadesEtnicas.findByPomtPlanes");
                query5.setParameter("pomtPlanes", plan);
                plan.setPomtComunidadesEtnicasList(query5.getResultList());
              
            // ZSDG IN              
            Query query6 =
                em.createNamedQuery("DeterminanteAmbiental.findByPomtPlanes");
            query6.setParameter("pomtPlanes", plan);
            plan.setDeterminanteAmbientalList(query6.getResultList());
            //ZSDG  
            }
            em.clear();
        }
        if (planes.size() > 0) {
            return planes.get(0);
        } else {
            return null;
        }

    }


    public void updatePomtPlanes(PomtPlanes planes) throws IdeamException {
        if (planes.getId() == null || planes.getId().longValue() == 0) {
            planes.setId(GeneradorSeq.obtenerSequencia(planes.getCodigoAutoridad(),
                                                       "seq_pomt_planes", em));

            em.persist(planes);
        } else {
            em.merge(planes);
        }
    }


    /**************************   COMISIONES   *****************************/
    public void updatePomtComisiones(PomtComisiones comision) throws IdeamException {
        if (comision.getId() == null || comision.getId().longValue() == 0) {
            comision.setId(GeneradorSeq.obtenerSequencia(comision.getCodigoAutoridad(),
                                                         "seq_pomt_comisiones",
                                                         em));

            em.persist(comision);
        } else {
            em.merge(comision);
        }
    }

    public List<PomtComisiones> getAllPomtComisionesByPlan(PomtPlanes plan) throws IdeamException {
        Query query = em.createNamedQuery("PomtComisiones.findByPomtPlanes");
        query.setParameter("pomtPlanes", plan);
        List<PomtComisiones> comisiones = query.getResultList();
        em.clear();
        return comisiones;
    }

    public void deletePomtComision(PomtComisiones comision) throws IdeamException {
        comision = em.merge(comision);
        em.remove(comision);
    }

    public void deletePomtComisionesFromPlan(PomtPlanes plan) throws IdeamException {
        for (PomtComisiones comision : plan.getPomtComisionesList()) {
            if (comision != null) {
                comision = em.merge(comision);
                em.remove(comision);
            }
        }
    }
    /*********************************************************************/
 

    /**************************   JURISDICCIONES   *****************************/

    public void updatePomtJurisdiccion(PomtJurisdiccion jurisdiccion,
                                       Long idAA) throws IdeamException {
        if (jurisdiccion.getId() == null ||
            jurisdiccion.getId().longValue() == 0) {
            jurisdiccion.setId(GeneradorSeq.obtenerSequencia(idAA,
                                                             "seq_pomt_jurisdiccion",
                                                             em));
            em.persist(jurisdiccion);
        } else {
            em.merge(jurisdiccion);
        }
    }

    public List<PomtJurisdiccion> getAllPomtJurisdiccionByPlan(PomtPlanes plan) throws IdeamException {
        Query query = em.createNamedQuery("PomtJurisdiccion.findByPomtPlanes");
        query.setParameter("pomtPlanes", plan);
        List<PomtJurisdiccion> comisiones = query.getResultList();
        em.clear();
        return comisiones;
    }

    public void deletePomtJurisdicciones(PomtJurisdiccion jurisdiccion) throws IdeamException {
        jurisdiccion = em.merge(jurisdiccion);
        em.remove(jurisdiccion);
    }

    public void deletePomtJurisdiccionesFromPlan(PomtPlanes plan) throws IdeamException {
        for (PomtJurisdiccion jurisdiccion : plan.getPomtJurisdiccionList()) {
            if (jurisdiccion != null) {
                jurisdiccion = em.merge(jurisdiccion);
                em.remove(jurisdiccion);
            }
        }
    }
    /*********************************************************************/

    /**************************   INSTRUMENTOS PLANIFICACION   *****************************/
    public void updatePomtInstrumentosPlanificacion(PomtInstrumentosPlanificacion instrumentosPlanificacion) throws IdeamException {
        if (instrumentosPlanificacion.getId() == null ||
            instrumentosPlanificacion.getId().longValue() == 0) {
            instrumentosPlanificacion.setId(GeneradorSeq.obtenerSequencia(instrumentosPlanificacion.getCodigoAutoridad(),
                                                                          "seq_pomt_instrumenos_planif",
                                                                          em));
            em.persist(instrumentosPlanificacion);
        } else {
            em.merge(instrumentosPlanificacion);
        }
    }

    public List<PomtInstrumentosPlanificacion> getAllPomtInstrumentosPlanificacionByPlan(PomtPlanes plan) throws IdeamException {
        Query query =
            em.createNamedQuery("PomtInstrumentosPlanificacion.findByPlan");
        query.setParameter("pomtPlanes", plan);
        List<PomtInstrumentosPlanificacion> instrumentosPlanificaciones =
            query.getResultList();
        em.clear();
        return instrumentosPlanificaciones;
    }

    public void deletePomtInstrumentosPlanificacion(PomtInstrumentosPlanificacion instrumentosPlanificacion) throws IdeamException {
        instrumentosPlanificacion = em.merge(instrumentosPlanificacion);
        em.remove(instrumentosPlanificacion);
    }

    public void deletePomtInstrumentosPlanificacionesFromPlan(PomtPlanes plan) throws IdeamException {
        for (PomtInstrumentosPlanificacion instrumentosPlanificacion :
             plan.getPomtInstrumentosPlanificacionList()) {
            if (instrumentosPlanificacion != null) {
                instrumentosPlanificacion =
                        em.merge(instrumentosPlanificacion);
                em.remove(instrumentosPlanificacion);
            }
        }
    }
    /*********************************************************************/

    /**************************   COMUNIDADES ETNICAS   *****************************/
    public void updatePomtComunidadesEtnicas(PomtComunidadesEtnicas comunidadesEtnicas) throws IdeamException {
        if (comunidadesEtnicas.getId() == null ||
            comunidadesEtnicas.getId().longValue() == 0) {
            comunidadesEtnicas.setId(GeneradorSeq.obtenerSequencia(comunidadesEtnicas.getCodigoAutoridad(),
                                                                   "seq_pomt_comunidades_etnicas",
                                                                   em));

            em.persist(comunidadesEtnicas);
        } else {
            em.merge(comunidadesEtnicas);
        }
    }

    public List<PomtComunidadesEtnicas> getAllPomtComunidadesEtnicasByPlan(PomtPlanes plan) throws IdeamException {
        Query query =
            em.createNamedQuery("PomtComunidadesEtnicas.findByPomtPlanes");
        query.setParameter("pomtPlanes", plan);
        List<PomtComunidadesEtnicas> comunidadesEtnicases =
            query.getResultList();
        em.clear();
        return comunidadesEtnicases;
    }

    public void deletePomtComunidadesEtnicas(PomtComunidadesEtnicas comunidadesEtnicas) throws IdeamException {
        comunidadesEtnicas = em.merge(comunidadesEtnicas);
        em.remove(comunidadesEtnicas);
    }

    public void deletePomtComunidadesEtnicasesFromPlan(PomtPlanes plan) throws IdeamException {
        for (PomtComunidadesEtnicas comunidadesEtnicas :
             plan.getPomtComunidadesEtnicasList()) {
            if (comunidadesEtnicas != null) {
                comunidadesEtnicas = em.merge(comunidadesEtnicas);
                em.remove(comunidadesEtnicas);
            }
        }
    }

    /*********************************************************************/
    
    //ZSDG IN
    /**************************   DETERMINANTE AMBIENTAL   *****************************/
    public void updateDetermianteAmbiental(DeterminanteAmbiental determinante) throws IdeamException {
        if (determinante.getId() == null || determinante.getId().longValue() == 0) {
            determinante.setId(GeneradorSeq.obtenerSequencia(determinante.getCodigoAutoridad(), "seq_pomt_comunidades_etnicas", em));
            em.persist(determinante);
        } else {
            em.merge(determinante);
        }
    }

    public List<DeterminanteAmbiental> getAllDeterminanteAmbientalByPlan(PomtPlanes plan) throws IdeamException {
     
        Query query = em.createNamedQuery("DeterminanteAmbiental.findByPomtPlanes");
     
        query.setParameter("pomtPlanes", plan);
        List<DeterminanteAmbiental> determinantes =  query.getResultList();
        em.clear();
        return determinantes;
    }

    public void deleteDeterminanteAmbiental(DeterminanteAmbiental determinante) throws IdeamException {
        determinante = em.merge(determinante);
        em.remove(determinante);
    }

    public void deleteDeterminanteAmbientalFromPlan(PomtPlanes plan) throws IdeamException {
        for (DeterminanteAmbiental determinante : plan.getDeterminanteAmbientalList()) {
            if (determinante != null) {
                determinante = em.merge(determinante);
                em.remove(determinante);
            }
        }
    }
    
  public void updateArchivosXDeterminante(PomtArchivosXDeterminantes archideterminante) throws IdeamException {
      if (archideterminante.getId() == null || archideterminante.getId() == 0) {
          em.persist(archideterminante);
      } else {
          em.merge(archideterminante);
      }
  }
  
  

    /*********************************************************************/
    //ZSDG
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void removePomtPlanes(PomtPlanes plan) throws IdeamException {
        for (PomtComisiones comision : plan.getPomtComisionesList()) {
            if (comision != null) {
                em.remove(comision);
            }
        }

        for (PomtJurisdiccion jurisdiccion : plan.getPomtJurisdiccionList()) {
            if (jurisdiccion != null) {
                em.remove(jurisdiccion);
            }
        }

        for (PomtInstrumentosPlanificacion instrumentos :
             plan.getPomtInstrumentosPlanificacionList()) {
            if (instrumentos != null) {
                em.remove(instrumentos);
            }
        }

        for (PomtComunidadesEtnicas comunidades :
             plan.getPomtComunidadesEtnicasList()) {
            if (comunidades != null) {
                em.remove(comunidades);
            }
        }

        // Elimina Archivos de la relación de planes y de la tabla de archivos
        for (PomtArchivosXPlan archiPlan : this.getAllArchivosXPlan(plan)) {

            System.out.println(">>>>>>> Eliminando el ARCHIPLAN: " +
                               archiPlan.getId() + " - " +
                               archiPlan.getPomtPlanes().getId() + " - " +
                               archiPlan.getFechaCreacion());

            Nodo nodo = null;
            Query query = em.createNamedQuery("Nodo.findById");
            query.setParameter("id", archiPlan.getId());
            try {
                nodo = (Nodo)query.getSingleResult();
                System.out.println(">>>>>> Nodo " + nodo.getId() + " - " +
                                   nodo.getDescripcion());
            } catch (Exception e) {
                System.out.println(">>>>>>No se encuentra el Nodo ");
            }
            if (nodo != null) {
                nodo = em.merge(nodo);
                em.remove(nodo);
            }


            Query query2 =
                em.createNamedQuery("PomtArchivosXPlan.findByarchiPlan");
            query2.setParameter("id", archiPlan.getId());
            query2.setParameter("pomtPlanes", plan);
            archiPlan = (PomtArchivosXPlan)query2.getSingleResult();
            if (archiPlan != null) {
                em.merge(archiPlan);
                em.remove(archiPlan);
            }
            System.out.println(">>>>>> Eliminado Archivo: " +
                               archiPlan.getId());
        }

        //Finalmente se puede borrar el plan.
        plan = em.merge(plan);
        em.remove(plan);
    }


    public List<PomtArchivosXPlan> getAllArchivosXPlan(PomtPlanes plan) throws IdeamException {
        Query query =
            em.createNamedQuery("PomtArchivosXPlan.findByPomtPlanes");
        query.setParameter("pomtPlanes", plan);
        List<PomtArchivosXPlan> archiPlanes = query.getResultList();
        em.clear();
        return archiPlanes;
    }


    public void updateArchivosXPlan(PomtArchivosXPlan archiplan) throws IdeamException {
        if (archiplan.getId() == null || archiplan.getId() == 0) {

            em.persist(archiplan);
        } else {
            em.merge(archiplan);
        }
    }

    /* HCP ENTREGA 4 */

    public Programa getPrograma(Long idPrograma) {
        Query query = em.createNamedQuery("Programa.findById");
        query.setParameter("idPrograma", idPrograma);
        Programa prg = (Programa)query.getSingleResult();
        return prg;
    }

    public Pomca getPomcaXId(Long idPomca) {
        Query query = em.createNamedQuery("Pomca.findById");
        query.setParameter("idPomca", idPomca);
        Pomca pom = (Pomca)query.getSingleResult();
        return pom;
    }


    public Actividad updateActividad(Actividad actividad) throws IdeamException {

        System.out.println("Entro a updateActividad " + actividad.getNombre());

        if (actividad.getId() == null || actividad.getId().longValue() == 0) {
            actividad.setId(GeneradorSeq.obtenerSequencia(actividad.getCodigoAutoridad(),
                                                          "seq_actividad",
                                                          em));

            em.persist(actividad);
        } else {
            actividad = em.merge(actividad);
        }
        em.flush();
        em.refresh(actividad);

        Proyecto pry = getProyecto(actividad.getIdProyecto());
        Programa prg = getPrograma(pry.getIdPrograma());
        Pomca pomca = getPomcaXId(prg.getIdPomca());

        recalcularPomca(pomca);

        return actividad;
    }

    public void deleteActividad(Actividad actividad) throws IdeamException {
        eliminarActividad(actividad, true);
    }

    public void eliminarActividad(Actividad actividad,
                                  boolean recalcular) throws IdeamException {

        Pomca pomca = null;

        if (recalcular) {
            Proyecto pry = getProyecto(actividad.getIdProyecto());
            Programa prg = getPrograma(pry.getIdPrograma());
            pomca = getPomcaXId(prg.getIdPomca());
        }

        List listaIndicadores;
        listaIndicadores = getIndicadores(actividad);

        if (listaIndicadores != null) {
            Iterator it = listaIndicadores.iterator();
            while (it.hasNext()) {
                Indicador indicador = (Indicador)it.next();

                List listaGestionIndicador;

                listaGestionIndicador =
                        getGestionIndicadorXIndicador(indicador);
                if (listaGestionIndicador != null) {
                    Iterator it2 = listaGestionIndicador.iterator();
                    while (it2.hasNext()) {
                        GestionIndicador gestionIndicador =
                            (GestionIndicador)it2.next();

                        em.flush();
                        gestionIndicador = em.merge(gestionIndicador);
                        em.remove(gestionIndicador);

                    }
                }

                eliminarIndicador(indicador, false);
            }
        }

        List listaGestion = getGestionXActividad(actividad);
        if (listaGestion != null) {
            Iterator it = listaGestion.iterator();
            while (it.hasNext()) {
                Gestion gestion = (Gestion)it.next();
                em.flush();
                gestion = em.merge(gestion);
                em.remove(gestion);
            }
        }

        em.flush();
        actividad = em.merge(actividad);
        em.remove(actividad);

        if (recalcular)
            recalcularPomca(pomca);
    }

    public Actividad addActividad(Actividad actividad) throws IdeamException {
        actividad.setId(GeneradorSeq.obtenerSequencia(actividad.getCodigoAutoridad(),
                                                      "seq_actividad", em));

        em.persist(actividad);
        em.flush();
        em.refresh(actividad);

        Proyecto pry = getProyecto(actividad.getIdProyecto());
        Programa prg = getPrograma(pry.getIdPrograma());
        Pomca pomca = getPomcaXId(prg.getIdPomca());

        recalcularPomca(pomca);

        return actividad;
    }


    public Gestion addGestion(Gestion gestion) throws IdeamException {
        gestion.setId(GeneradorSeq.obtenerSequencia(gestion.getCodigoAutoridad(),
                                                    "seq_gestion", em));

        em.persist(gestion);
        em.flush();
        em.refresh(gestion);

        Pomca pomca = getPomca(gestion.getIdCuenca());

        recalcularPomca(pomca);

        return gestion;
    }


    public List<Gestion> getGestionXActividad(Actividad actividad) {
        Query query = em.createNamedQuery("Gestion.findByActividad");
        query.setParameter("idActividad", actividad.getId());

        List lista = query.getResultList();

        return lista;
    }

    public List<GestionIndicador> getGestionIndicadorXGestion(Gestion gestion) {
        Query query = em.createNamedQuery("GestionIndicador.findByIdGestion");
        query.setParameter("idGestion", gestion.getId());

        List lista = query.getResultList();

        return lista;
    }

    public List<GestionIndicador> getGestionIndicadorXIndicador(Indicador indicador) {
        System.out.println("getGestionIndicadorXIndicador:" +
                           indicador.getNombre());
        Query query = em.createNamedQuery("GestionIndicador.findByIndicador");
        query.setParameter("idIndicador", indicador.getId());

        List lista = query.getResultList();

        return lista;
    }

    public void recalcularPomca(Pomca pomca) {


        System.out.println("Entro a recalcularPomca " + pomca.getId());

        List listaProgramas;

        try {

            BigDecimal presupuestoAsignado = new BigDecimal("0");
            BigDecimal presupuestoEjecutado = new BigDecimal("0");
            Double porcCumplimiento = 0.0;
            int cantProg = 0;

            listaProgramas = getProgramas(pomca);
            if (listaProgramas != null) {
                Iterator it = listaProgramas.iterator();
                while (it.hasNext()) {
                    Programa programa = (Programa)it.next();

                    recalcularPrograma(programa);

                    presupuestoAsignado =
                            presupuestoAsignado.add(programa.getPresupuestoAsignado());
                    presupuestoEjecutado =
                            presupuestoEjecutado.add(programa.getPresupuestoEjecutado());
                    porcCumplimiento += programa.getPorcCumplimiento();
                    cantProg++;

                }
            }

            pomca.setPresupuestoAsignado(presupuestoAsignado);
            pomca.setPresupuestoEjecutado(presupuestoEjecutado);

            Double porcEjecucion = 0.0;

            if (presupuestoAsignado.compareTo(new BigDecimal("0")) != 0)
                porcEjecucion =
                        presupuestoEjecutado.doubleValue() * 100.0 / presupuestoAsignado.doubleValue();

            porcEjecucion = (double)Math.round(porcEjecucion * 100) / 100;
            pomca.setPorcEjecucion(porcEjecucion);

            if (cantProg == 0)
                porcCumplimiento = 0.0;
            else
                porcCumplimiento = porcCumplimiento / cantProg;

            porcCumplimiento =
                    (double)Math.round(porcCumplimiento * 100) / 100;
            pomca.setPorcCumplimiento(porcCumplimiento);

            updatePomca(pomca);

        } catch (Exception e) {
            System.out.println("Genero error en recalcularPomca : " +
                               e.getMessage());

        }
    }

    public void recalcularPrograma(Programa programa) {

        System.out.println("Entro a recalcularPrograma " +
                           programa.getNombre());


        List listaProyectos;

        try {

            BigDecimal presupuestoAsignado = new BigDecimal("0");
            BigDecimal presupuestoEjecutado = new BigDecimal("0");
            Double porcCumplimiento = 0.0;
            int cantProg = 0;

            listaProyectos = getProyectos(programa);
            if (listaProyectos != null) {
                Iterator it = listaProyectos.iterator();
                while (it.hasNext()) {
                    Proyecto proyecto = (Proyecto)it.next();

                    recalcularProyecto(proyecto);

                    presupuestoAsignado =
                            presupuestoAsignado.add(proyecto.getPresupuestoAsignado());
                    presupuestoEjecutado =
                            presupuestoEjecutado.add(proyecto.getPresupuestoEjecutado());
                    porcCumplimiento += proyecto.getPorcCumplimiento();
                    cantProg++;


                }
            }

            programa.setPresupuestoAsignado(presupuestoAsignado);
            programa.setPresupuestoEjecutado(presupuestoEjecutado);

            Double porcEjecucion = 0.0;

            if (presupuestoAsignado.compareTo(new BigDecimal("0")) != 0)
                porcEjecucion =
                        presupuestoEjecutado.doubleValue() * 100.0 / presupuestoAsignado.doubleValue();

            porcEjecucion = (double)Math.round(porcEjecucion * 100) / 100;
            programa.setPorcEjecucion(porcEjecucion);

            if (cantProg == 0)
                porcCumplimiento = 0.0;
            else
                porcCumplimiento = porcCumplimiento / cantProg;

            porcCumplimiento =
                    (double)Math.round(porcCumplimiento * 100) / 100;
            programa.setPorcCumplimiento(porcCumplimiento);

            updatePrograma(programa);

        } catch (Exception e) {
            System.out.println("Genero error en recalcularPrograma : " +
                               e.getMessage());

        }

    }

    public void recalcularProyecto(Proyecto proyecto) {

        System.out.println("Entro a recalcularProyecto " +
                           proyecto.getNombre());


        List listaActividades;

        try {

            BigDecimal presupuestoAsignado = new BigDecimal("0");
            BigDecimal presupuestoEjecutado = new BigDecimal("0");
            Double porcCumplimiento = 0.0;
            int cantProg = 0;

            listaActividades = getActividades(proyecto);
            if (listaActividades != null) {
                Iterator it = listaActividades.iterator();
                while (it.hasNext()) {
                    Actividad actividad = (Actividad)it.next();

                    actividad = calcularEjecutadoActividad(actividad);
                    actividad = calcularCumplimientoActividad(actividad);

                    presupuestoAsignado =
                            presupuestoAsignado.add(actividad.getPresupuestoAsignado());
                    presupuestoEjecutado =
                            presupuestoEjecutado.add(actividad.getPresupuestoEjecutado());
                    porcCumplimiento += actividad.getPorcCumplimiento();
                    cantProg++;

                }
            }

            proyecto.setPresupuestoAsignado(presupuestoAsignado);
            proyecto.setPresupuestoEjecutado(presupuestoEjecutado);

            Double porcEjecucion = 0.0;

            if (presupuestoAsignado.compareTo(new BigDecimal("0")) != 0)
                porcEjecucion =
                        presupuestoEjecutado.doubleValue() * 100.0 / presupuestoAsignado.doubleValue();

            porcEjecucion = (double)Math.round(porcEjecucion * 100) / 100;

            proyecto.setPorcEjecucion(porcEjecucion);

            if (cantProg == 0)
                porcCumplimiento = 0.0;
            else
                porcCumplimiento = porcCumplimiento / cantProg;

            porcCumplimiento =
                    (double)Math.round(porcCumplimiento * 100) / 100;
            proyecto.setPorcCumplimiento(porcCumplimiento);

            proyecto = updateProyecto(proyecto);

            System.out.println("Presupuesto proyecto " + proyecto.getNombre() +
                               ":" + proyecto.getPresupuestoAsignado());


        } catch (Exception e) {
            System.out.println("Genero error en recalcularProyecto : " +
                               e.getMessage());

        }


    }

    public Actividad calcularEjecutadoActividad(Actividad actividad) {
        System.out.println("Entro a calcularEjecutadoActividad " +
                           actividad.getNombre());


        List listaGestion;
        try {

            BigDecimal presupuestoEjecutado = new BigDecimal("0");

            listaGestion = getGestionXActividad(actividad);
            if (listaGestion != null) {
                Iterator it = listaGestion.iterator();
                while (it.hasNext()) {
                    Gestion gestion = (Gestion)it.next();

                    presupuestoEjecutado =
                            presupuestoEjecutado.add(gestion.getPresupuestoEjecutado());

                }
            }

            BigDecimal presupuestoAsignado = new BigDecimal("0");

            actividad.setPresupuestoEjecutado(presupuestoEjecutado);
            presupuestoAsignado = actividad.getPresupuestoAsignado();

            Double porcEjecucion = 0.0;

            if (presupuestoAsignado.compareTo(new BigDecimal("0")) != 0)
                porcEjecucion =
                        presupuestoEjecutado.doubleValue() * 100.0 / presupuestoAsignado.doubleValue();

            porcEjecucion = (double)Math.round(porcEjecucion * 100) / 100;

            actividad.setPorcEjecucion(porcEjecucion);

            actividad = em.merge(actividad);
            em.flush();
            em.refresh(actividad);

        } catch (Exception e) {
            System.out.println("Genero error en recalcularProyecto : " +
                               e.getMessage());

        }

        return actividad;

    }

    public Actividad calcularCumplimientoActividad(Actividad actividad) {
        System.out.println("Entro a calcularCumplimientoActividad " +
                           actividad.getNombre());


        List listaIndicadores;
        try {
            Double porcCumplimientoAct = 0.0;
            Integer numInd = 0;

            listaIndicadores = getIndicadores(actividad);

            if (listaIndicadores != null) {
                System.out.println("listaIndicadores:" +
                                   listaIndicadores.size());
                Iterator it = listaIndicadores.iterator();
                while (it.hasNext()) {
                    Indicador indicador = (Indicador)it.next();

                    Double porcCumplimiento = 0.0;
                    Double vlrInd = 0.0;

                    List listaGestionIndicador;

                    listaGestionIndicador =
                            getGestionIndicadorXIndicador(indicador);
                    System.out.println("listaGestionIndicador:" +
                                       listaGestionIndicador.size());
                    if (listaGestionIndicador != null) {
                        Iterator it2 = listaGestionIndicador.iterator();
                        while (it2.hasNext()) {
                            GestionIndicador gestionIndicador =
                                (GestionIndicador)it2.next();
                            vlrInd += gestionIndicador.getValorIndicador();

                        }
                    }
                    System.out.println("vlrInd:" + vlrInd);
                    if (indicador.getMeta() == 0)
                        porcCumplimiento = 0.0;
                    else
                        porcCumplimiento =
                                vlrInd * 100 / (double)indicador.getMeta();

                    porcCumplimiento =
                            (double)Math.round(porcCumplimiento * 100) / 100;

                    porcCumplimientoAct += porcCumplimiento;
                    numInd++;

                    indicador.setPorcCumplimiento(porcCumplimiento);
                    indicador = em.merge(indicador);
                    em.flush();
                    em.refresh(indicador);

                }
            }

            if (numInd == 0)
                porcCumplimientoAct = 0.0;
            else
                porcCumplimientoAct = porcCumplimientoAct / numInd;

            porcCumplimientoAct =
                    (double)Math.round(porcCumplimientoAct * 100) / 100;

            actividad.setPorcCumplimiento(porcCumplimientoAct);

            actividad = em.merge(actividad);
            em.flush();
            em.refresh(actividad);

        } catch (Exception e) {
            System.out.println("Genero error en calcularCumplimientoActividad : " +
                               e.getMessage());

        }

        return actividad;

    }

    public Actividad getActividad(Long idActividad) {
        Query query = em.createNamedQuery("Actividad.findById");
        query.setParameter("idActividad", idActividad);
        Actividad act = (Actividad)query.getSingleResult();
        return act;
    }

    public Indicador getIndicador(Long idIndicador) {
        Query query = em.createNamedQuery("Indicador.findById");
        query.setParameter("idIndicador", idIndicador);
        Indicador ind = (Indicador)query.getSingleResult();
        return ind;
    }

    public void deleteIndicador(Indicador indicador) throws IdeamException {
        eliminarIndicador(indicador, true);
    }

    public void eliminarIndicador(Indicador indicador,
                                  boolean recalcular) throws IdeamException {

        Pomca pomca = null;
        if (recalcular) {

            Actividad actividad = getActividad(indicador.getIdActividad());
            Proyecto pry = getProyecto(actividad.getIdProyecto());
            Programa prg = getPrograma(pry.getIdPrograma());
            pomca = getPomcaXId(prg.getIdPomca());
        }

        em.flush();
        indicador = em.merge(indicador);
        em.remove(indicador);

        if (recalcular)
            recalcularPomca(pomca);

    }


    public List<Cuenca> getCuencas(Autoridades autoridad) {
        Query query = em.createNamedQuery("Cuenca.findByAutoridad");
        query.setParameter("idAutoridad", autoridad.getId().longValue());

        List lista = query.getResultList();

        return lista;
    }

    public List<Cuenca> getCuencas() {
        Query query = em.createNamedQuery("Cuenca.findAll");

        List lista = query.getResultList();

        return lista;
    }


    public List<Cuenca> getCuencasAreas() {

        String sql =
            "Select g.* " + "from (" + "Select  codigo_znf, max (area_jurisd_autoridad) as area" +
            "from " + "pomt_cuencas p" +
            " group by codigo_znf order by codigo_znf" +
            ") t , pomt_cuencas g" + "where g.codigo_znf= t.codigo_znf" +
            "and t.area=g.area_jurisd_autoridad " + " order by g.codigo_znf";


        Query q = em.createNativeQuery(sql, Cuenca.class);

        List lista = q.getResultList();


        return lista;
    }

    public List<Cuenca> getCuencasAreas(Autoridades autoridad) {

        String sql =
            "select g.* " + " from (" + " select  codigo_znf, max (area_jurisd_autoridad) as area" +
            " from " + " pomt_cuencas p" +
            " group by codigo_znf order by codigo_znf" +
            " ) t , pomt_cuencas g" + " where g.codigo_znf= t.codigo_znf" +
            " and t.area=g.area_jurisd_autoridad " + " and g.id_autoridad =" +
            autoridad.getId() + " order by g.codigo_znf";

        System.out.println("sql pomcas---------------------------------------------344  " +
                           sql);
        Query q = em.createNativeQuery(sql, Cuenca.class);

        List lista = q.getResultList();


        return lista;
    }

    public Cuenca getCuenca(Long idCuenca) throws IdeamException {
        Query query = em.createNamedQuery("Cuenca.findById");
        query.setParameter("idCuenca", idCuenca);
        Cuenca cuenca = (Cuenca)query.getSingleResult();
        return cuenca;
    }

    public PomtDetalleCuenca getDetalleCuenca(Long idCuenca) throws IdeamException {
        PomtDetalleCuenca detalle = null;
        try {
            Query query = em.createNamedQuery("PomtDetalleCuenca.findById");
            query.setParameter("id", idCuenca);
            detalle = (PomtDetalleCuenca)query.getSingleResult();
        } catch (NoResultException e) {
            detalle = null;
        }
        return detalle;
    }
    
    
    ////////////////////////
    
    /**
     * Actualiza el detalle de una cuenca.
     * @param cuenca
     * @throws IdeamException
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public PomtDetalleCuenca updateDetalleCuenca(PomtDetalleCuenca cuenca) throws IdeamException {
        System.out.println("=====================updateDetalleCuenca1 ");
        
        List<FnttFuente> afluentes = cuenca.getAfluentes(); //las relacionadas en GUI.
        
        System.out.println("=====================updateDetalleCuenca2 ");
        
        List<PomtAfluentesCuenca> relacionadas = this.getAllAfluentesCuenca(cuenca.getId()); //Relacionadas en BD.
        
        System.out.println("=====================updateDetalleCuenca3 ");
        
        PomtDetalleCuenca cuencaAux = this.getDetalleCuenca(cuenca.getId());
        if(cuencaAux!=null){
            em.merge(cuenca);
        }else{
            em.persist(cuenca);
        }
        
        System.out.println("=====================updateDetalleCuenca4 ");
        
        
        if (relacionadas != null && !relacionadas.isEmpty()) {
            for (PomtAfluentesCuenca afluente : relacionadas) {
                this.deleteAfluente(afluente);
            }
        }
        
        System.out.println("=====================updateDetalleCuenca5 ");

        if (afluentes != null && !afluentes.isEmpty()) {
            for (FnttFuente fuente : afluentes) {
                PomtAfluentesCuenca afluente = new PomtAfluentesCuenca();
                afluente.setIdFuente(fuente.getId());
                afluente.setIdCuenca(cuenca.getId());
                afluente.setFuente(fuente);
                afluente.setCuenca(cuenca);
                this.addAfluente(afluente);
            }
        }
        
        System.out.println("=====================updateDetalleCuenca6 ");

        em.flush();
        //em.refresh(cuenca);
        
        return cuenca;
    }

    /**
     * Crea una relación de afluente y cuenca.
     * @param afluente
     * @throws IdeamException
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    private PomtAfluentesCuenca addAfluente(PomtAfluentesCuenca afluente) throws IdeamException {
        /*
        afluente.setId( GeneradorSeq.obtenerSequencia(afluente.getCuenca().getCuenca().getIdAutoridad(),
                                                      "SEQ_AFLUENTES_CUENCA", em));
        */

        em.persist(afluente);
        em.flush();
        em.refresh(afluente);
        return afluente;
    }

    /**
     * Borra una relación de afluente y cuenca.
     * @param afluente
     * @throws IdeamException
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    private void deleteAfluente(PomtAfluentesCuenca afluente) throws IdeamException {
        em.remove(afluente);
    }

    /**
     * Retorna una lista con las afluentes relacionadas a una cuenca.
     * @param idCuenca
     * @throws IdeamException
     */
    public List<PomtAfluentesCuenca> getAllAfluentesCuenca(long idCuenca) throws IdeamException {
        List result = null;
        try {
            Query query =
                em.createNamedQuery("PomtAfluentesCuenca.findAllByCuenca");
            query.setParameter("idCuenca", idCuenca);
            result = query.getResultList();
            //System.out.println("================getAllAfluentesCuenca RESULT: "+result.size());
        } catch (NoResultException e) {
        }
        return result;
    }


}
