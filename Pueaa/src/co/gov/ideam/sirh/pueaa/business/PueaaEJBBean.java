package co.gov.ideam.sirh.pueaa.business;


import co.gov.ideam.sirh.pueaa.model.PartCategorias;
import co.gov.ideam.sirh.pueaa.model.PartListas;
import co.gov.ideam.sirh.pueaa.model.PueatAdmon;
import co.gov.ideam.sirh.pueaa.model.PueatProyectoConcesiones;
import co.gov.ideam.sirh.pueaa.model.PueatProyectos;
import co.gov.ideam.sirh.pueaa.model.PueatPuea;
import co.gov.ideam.sirh.pueaa.model.PueatSeguimiento;

import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.usuarios.agua.model.GeneradorSeq;

import co.gov.ideam.sirh.usuarios.agua.model.UsuarioAgua;

import java.util.Iterator;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import javax.persistence.NoResultException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless(name = "PueaaEJB", mappedName = "AppSIRH-Pueaa-PueaaEJB")
@Remote
public class PueaaEJBBean implements PueaaEJB {
    @PersistenceContext(unitName="SirhPU")
    private EntityManager em;

    public PueaaEJBBean() {
    }

    public Object queryByRange(String jpqlStmt, int firstResult,
                               int maxResults) {
        Query query = em.createQuery(jpqlStmt);
        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query = query.setMaxResults(maxResults);
        }
        return query.getResultList();
    }
   
    public PueatAdmon persistPueatAdmon(PueatAdmon pueatAdmon) {
        Query query = (Query)em.createNamedQuery("PueatAdmon.findByUserPreg");
        query.setParameter("idUsuario", pueatAdmon.getIdUsuario());
        query.setParameter("pregunta", pueatAdmon.getPregunta());
       
        List lista = query.getResultList();
        boolean existe = false;
        
        if (lista != null) {
            Iterator it = lista.iterator();
            while (it.hasNext()) {
                PueatAdmon puea = (PueatAdmon)it.next();
                pueatAdmon.setId(puea.getId());  
                em.merge(pueatAdmon);
                existe = true;
            }
        }
        
        if (!existe) 
          em.persist(pueatAdmon);
        
        return pueatAdmon;
    }

    public PueatAdmon mergePueatAdmon(PueatAdmon pueatAdmon) {
        return em.merge(pueatAdmon);
    }
    
    public List<PueatAdmon> getPueatProyectoConcesionesFindAll(PueatAdmon pueatAdmon) {
        Query query = (Query)em.createNamedQuery("PueatAdmon.findByUser");
        query.setParameter("idUsuario", pueatAdmon.getIdUsuario());
        return query.getResultList();
    }
  ////
    public PueatSeguimiento persistPueatSeguimiento(PueatSeguimiento pueatSeguimiento) {
        em.persist(pueatSeguimiento);
        return pueatSeguimiento;
    }

    public PueatSeguimiento mergePueatSeguimiento(PueatSeguimiento pueatSeguimiento) {
        return em.merge(pueatSeguimiento);
    }
   
    public void removePueatSeguimiento(PueatSeguimiento pueatSeguimiento) {
        pueatSeguimiento = em.find(PueatSeguimiento.class, pueatSeguimiento.getId());
        em.remove(pueatSeguimiento);
    }

    /** <code>select o from PueatSeguimiento o</code> */
    public List<PueatSeguimiento> getPueatSeguimientoFindAll() {
        return em.createNamedQuery("PueatSeguimiento.findAll").getResultList();
    }

    public PueatProyectoConcesiones persistPueatProyectoConcesiones(PueatProyectoConcesiones pueatProyectoConcesiones) {
        pueatProyectoConcesiones.setId(GeneradorSeq.obtenerSequencia(pueatProyectoConcesiones.getCodigoAutoridad(),
                                                 "SEQ_PRY_CONCESION", em));
        em.persist(pueatProyectoConcesiones);
        return pueatProyectoConcesiones;
    }

    public PueatProyectoConcesiones mergePueatProyectoConcesiones(PueatProyectoConcesiones pueatProyectoConcesiones) {
        return em.merge(pueatProyectoConcesiones);
    }

    public void removePueatProyectoConcesiones(PueatProyectoConcesiones pueatProyectoConcesiones) {
        pueatProyectoConcesiones = em.find(PueatProyectoConcesiones.class, pueatProyectoConcesiones.getId());
        em.remove(pueatProyectoConcesiones);
    }

    /** <code>select o from PueatProyectoConcesiones o</code> */
    public List<PueatProyectoConcesiones> getPueatProyectoConcesionesFindAll(PueatProyectos pueaaPryCreated) {
        Query query = (Query)em.createNamedQuery("PueatProyectoConcesiones.findAll");
        query.setParameter("pueaaPryId", pueaaPryCreated.getId());
        return query.getResultList();
      
    }

    public PueatProyectos persistPueatProyectos(PueatProyectos pueatProyectos) {
        pueatProyectos.setId(GeneradorSeq.obtenerSequencia(pueatProyectos.getCodigoAutoridad(),
                                                 "SEQ_PROYECTO", em));
        em.persist(pueatProyectos);
        return pueatProyectos;
    }

    public PueatProyectos mergePueatProyectos(PueatProyectos pueatProyectos) {
        return em.merge(pueatProyectos);
    }

    public void removePueatProyectos(PueatProyectos pueatProyectos) {
        pueatProyectos = em.find(PueatProyectos.class, pueatProyectos.getId());
        em.remove(pueatProyectos);
    }

    /** <code>select o from PueatProyectos o</code> */
    public List<PueatProyectos> getPueatProyectosFindAll() {
        return em.createNamedQuery("PueatProyectos.findAll").getResultList();
    }
    
    public List<PueatProyectos> getPueatProyectosFindByPueaa(PueatPuea pueatPuea) {
        Query query = (Query)em.createNamedQuery("PueatProyectos.findByPueaa");
        query.setParameter("pueaaId", pueatPuea.getId());
        return query.getResultList();
    }

    public PartCategorias persistPartCategorias(PartCategorias partCategorias) {
        em.persist(partCategorias);
        return partCategorias;
    }

    public PartCategorias mergePartCategorias(PartCategorias partCategorias) {
        return em.merge(partCategorias);
    }

    public void removePartCategorias(PartCategorias partCategorias) {
        partCategorias = em.find(PartCategorias.class, partCategorias.getId());
        em.remove(partCategorias);
    }

    /** <code>select o from PartCategorias o</code> */
    public List<PartCategorias> getPartCategoriasFindAll() {
        return em.createNamedQuery("PartCategorias.findAll").getResultList();
    }

    public PueatPuea persistPueatPuea(PueatPuea pueatPuea) {
        pueatPuea.setId(GeneradorSeq.obtenerSequencia(pueatPuea.getCodigoAutoridad(),
                                                 "SEQ_PUEAA", em));
        em.persist(pueatPuea);
        return pueatPuea;
    }

    public PueatPuea mergePueatPuea(PueatPuea pueatPuea) {
        return em.merge(pueatPuea);
    }
    
    public PueatProyectos mergePueaPry(PueatProyectos pueaaPry){
        return em.merge(pueaaPry);
     }
    
    public void removePueatPuea(PueatPuea pueatPuea) {
        pueatPuea = em.find(PueatPuea.class, pueatPuea.getId());
        em.remove(pueatPuea);
    }

    /** <code>select o from PueatPuea o</code> */
    public List<PueatPuea> getPueatPueaFindAll(UsuarioAgua usuarioAgua) {
        Query query = (Query)em.createNamedQuery("PueatPuea.findAll");
        query.setParameter("usuarioId", usuarioAgua.getCodigo());
        return query.getResultList();
    }

    public PartListas persistPartListas(PartListas partListas) {
        em.persist(partListas);
        return partListas;
    }

    public PartListas mergePartListas(PartListas partListas) {
        return em.merge(partListas);
    }

    public void removePartListas(PartListas partListas) {
        partListas = em.find(PartListas.class, partListas.getId());
        em.remove(partListas);
    }

    /** <code>select o from PartListas o</code> */
    public List<PartListas> getPartListasFindAll() {
        return em.createNamedQuery("PartListas.findAll").getResultList();
    }

   
    public List<PueatSeguimiento> getSeguimientosProyecto(PueatProyectos proyecto)throws IdeamException{
      Query query = em.createNamedQuery("PueatSeguimiento.findByProject");
      query.setParameter("idProyecto", proyecto.getId());
      try{
          return query.getResultList();
      }catch(NoResultException e){
          return null;
      }
    }
}
