package co.gov.ideam.sirh.porh.business;

import co.gov.ideam.sirh.fuentes.model.FnttTramo;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.porh.model.PorhAvanceMetas;
import co.gov.ideam.sirh.porh.model.PorhIndices;
import co.gov.ideam.sirh.porh.model.PorhIndicesBasicos;
import co.gov.ideam.sirh.porh.model.PorhMetas;
import co.gov.ideam.sirh.porh.model.PorhOfertaDemanda;
import co.gov.ideam.sirh.porh.model.PorhParametrosTO;
import co.gov.ideam.sirh.porh.model.PorhPlanes;

import co.gov.ideam.sirh.porh.model.PorhPriorizacion;
import co.gov.ideam.sirh.porh.model.PorhSeguimientoIndices;
import co.gov.ideam.sirh.porh.model.PorhTramosProhiPMonitoreo;
import co.gov.ideam.sirh.porh.model.PorhTramosProhibiciones;
import co.gov.ideam.sirh.porh.model.PorhTramosUsos;
import co.gov.ideam.sirh.porh.model.PorhTramosUsosObjetivo;
import co.gov.ideam.sirh.porh.model.PorhTramosUsosPlazos;
import co.gov.ideam.sirh.porh.model.PorvEfectividad;
import co.gov.ideam.sirh.porh.model.PorvMetas;
import co.gov.ideam.sirh.remoto.business.RegistroRemotoLocal;
import co.gov.ideam.sirh.usuarios.agua.model.GeneradorSeq;
import co.gov.ideam.sirh.util.IdeamException;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless(name = "PorhEJB", mappedName = "Sirh-PorhEJB")
@Remote
public class PorhEJBBean implements PorhEJB {
    @PersistenceContext (unitName="SirhPU")
    private EntityManager em;   
    
    @EJB private RegistroRemotoLocal registroRemoto;
    
    public PorhEJBBean() {
    }
    /**
     * Retorna el uso correspondiente al codigo recibido por parametro
     * @param codigo
     * @return
     * @throws IdeamException
     */
    public PorhTramosUsos getUso(Long codigo)throws IdeamException{
        Query query = em.createNamedQuery("PorhTramosUsos.find");
        query.setParameter("codigo", codigo);
        try{
            return (PorhTramosUsos)query.getSingleResult();
        }catch(NoResultException e){
            return null;
        }
    }
    /**
     * Borra la informacion del uso recibido como parametro
     * @param uso
     * @throws IdeamException
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteUso(PorhTramosUsos uso)throws IdeamException{

        System.out.println("uso ·" + uso.getId());
        Query query = em.createNamedQuery("PorhTramosUsosObjetivo.deleteByUso");
        query.setParameter("codigoUso", uso.getId());
        query.executeUpdate();
        
        query = em.createNamedQuery("PorhTramosUsosPlazos.deleteByUso");
        query.setParameter("codigoUso", uso.getId());
        query.executeUpdate();
        
        PorhTramosUsos existe = this.getUso( uso.getId() );
        if(existe!=null){
            em.remove(existe);   
            em.flush();
        }
    }
    /**
     * Borra el objetivo de caidad recibido como parametro
     * @param objetivo
     * @throws IdeamException
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteObjetivoCalidad(PorhTramosUsosObjetivo objetivo)throws IdeamException{
        PorhTramosUsosObjetivo existe = this.getObjetivoCalidad( objetivo.getId() );
        if(existe!=null){
            em.remove(existe);
            em.flush();
        }
    }
    /**
     * Borra la razon de prohibicion recibida como parametro
     * @param razonProhibicion
     * @throws IdeamException
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteRazonProhibicion(PorhTramosProhibiciones razonProhibicion)throws IdeamException{
        Query query = em.createNamedQuery("PorhTramosProhibiciones.find");
        query.setParameter("codigo", razonProhibicion.getId());
        try{
            PorhTramosProhibiciones existe = (PorhTramosProhibiciones)query.getSingleResult();
            if(existe!=null){
                em.remove(existe);
            }
        }catch(NoResultException e){
            return;
        }
    }
    /**
     * Borra la meta reciboda com parametro
     * @param meta
     * @throws IdeamException
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteMeta(PorvMetas meta)throws IdeamException{
        Query query = em.createNamedQuery("PorhAvanceMetas.deleteByMeta");
        query.setParameter("codigoMeta", meta.getId());
        query.executeUpdate();
                
        query = em.createNamedQuery("PorhMetas.find");
        query.setParameter("codigo", meta.getId());
        PorhMetas metaActual = (PorhMetas)query.getSingleResult();
        em.remove(metaActual);
    }
    
    /**
     * Borra el indicador recibido com parametro
     * @param meta
     * @throws IdeamException
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteIndicador(PorhIndices indicador)throws IdeamException{
        Query query = em.createNamedQuery("PorhSeguimientoIndices.deleteByIndicador");
        query.setParameter("codigoIndice", indicador.getId());
        query.executeUpdate();
                
        query = em.createNamedQuery("PorhIndices.find");
        query.setParameter("codigo", indicador.getId());
        PorhIndices indiceActual = (PorhIndices)query.getSingleResult();
        em.remove(indiceActual);
    }
    /**
     * Borra el avance de meta recibido como parametro
     * @param avance
     * @throws IdeamException
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteAvanceMeta(PorhAvanceMetas avance)throws IdeamException{
        Query query = em.createNamedQuery("PorhAvanceMetas.find");
        query.setParameter("codigo", avance.getId());
        PorhAvanceMetas avanceMeta = (PorhAvanceMetas)query.getSingleResult();
        em.remove(avanceMeta);
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
        try{
            Query query = em.createNamedQuery("PorhPlanes.findByResolucion");
            query.setParameter("numeroActo", numeroActo);
            query.setParameter("codigoAutoridad", autoridadAmbiental.getId().longValue());
            query.setMaxResults(1);
            return (PorhPlanes)query.getSingleResult();
        }catch(NoResultException e){
            return null;
        }
    }
    
    /**
     * Borra el plan de ordenamiento recibido como parametro y todos sus
     * atributos relacionados
     * @param plan
     * @throws IdeamException
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deletePorh(PorhPlanes plan)throws IdeamException{        
        // Borrar los atributos del plan de ordenamiento
        
        Query query = em.createNamedQuery("PorhOfertaDemanda.deleteByPlan");
        query.setParameter("codigoPlan", plan.getCodigo());
        query.executeUpdate();
        
        query = em.createNamedQuery("PorhTramosUsosObjetivo.deleteByPlan");
        query.setParameter("codigoPlan", plan.getCodigo());
        query.executeUpdate();
            
        query = em.createNamedQuery("PorhTramosUsosPlazos.deleteByPlan");
        query.setParameter("codigoPlan", plan.getCodigo());
        query.executeUpdate();
        
        query = em.createNamedQuery("PorhTramosUsos.deleteByPlan");
        query.setParameter("codigoPlan", plan.getCodigo());
        query.executeUpdate();
        
        query = em.createNamedQuery("PorhTramosProhibiciones.deleteByPlan");
        query.setParameter("codigoPlan", plan.getCodigo());
        query.executeUpdate();

        query = em.createNamedQuery("PorhAvanceMetas.deleteByPlan");
        query.setParameter("codigoPlan", plan.getCodigo());
        query.executeUpdate();

        query = em.createNamedQuery("PorhMetas.deleteByPlan");
        query.setParameter("codigoPlan", plan.getCodigo());
        query.executeUpdate();
        
        query = em.createNamedQuery("PorhSeguimientoIndices.deleteByPlan");
        query.setParameter("codigoPlan", plan.getCodigo());
        query.executeUpdate();

        query = em.createNamedQuery("PorhIndices.deleteByPlan");
        query.setParameter("codigoPlan", plan.getCodigo());
        query.executeUpdate();       
        
        query = em.createNamedQuery("PorhPriorizacion.deleteByPlan");
        query.setParameter("codigoPlan", plan.getCodigo());
        query.executeUpdate();       

        PorhPlanes planActual = this.getPlanOrdenamiento( plan.getCodigo() );
        em.remove(planActual);
    }
    /**
     * Retorna los valores de seguimiento registrados para el indicador en el 
     * tramo
     * @param indicador
     * @param tramo
     * @throws IdeamException
     */
    public void getSeguimientoBasico(PorhIndices indicador, FnttTramo tramo)throws IdeamException{        
        List datos = new ArrayList<PorhSeguimientoIndices>();
        
        // Cp
        if (indicador.getIdIndiceBasico().longValue()<=2 &&
        indicador.getFechaCp()!= null){
            String cadena = indicador.getSqlSeguimiento(false);
            Query queryCp = em.createNativeQuery( cadena );
            queryCp.setParameter("codigoFuente", tramo.getIdFuente().getId());
            queryCp.setParameter("codigoTramo", tramo.getId());
            queryCp.setParameter("fechaInicial", indicador.getFechaCp());
            Number countCp=(Number) queryCp.getSingleResult();
            Double valorCp = countCp.doubleValue();        
            indicador.setMetaLogradaCp(valorCp);        
        }
        
        // Mp
        if (indicador.getIdIndiceBasico().longValue()<=2 &&
        indicador.getFechaCp()!= null &&
        indicador.getFechaMp()!= null){
            String cadena = indicador.getSqlSeguimiento(true);
            Query query = em.createNativeQuery( cadena );
            query.setParameter("codigoFuente", tramo.getIdFuente().getId());
            query.setParameter("codigoTramo", tramo.getId());
            query.setParameter("fechaInicial", indicador.getFechaCp());
            query.setParameter("fechaFinal", indicador.getFechaMp());
            Number countMp=(Number) query.getSingleResult();
            Double valorMp = countMp.doubleValue();        
            indicador.setMetaLogradaMp(valorMp);        
        }

        // Lp
        if (indicador.getIdIndiceBasico().longValue()<=2 &&
        indicador.getFechaMp()!= null &&
        indicador.getFechaLp()!= null){
            String cadena = indicador.getSqlSeguimiento(true);
            Query query = em.createNativeQuery( cadena );
            query.setParameter("codigoFuente", tramo.getIdFuente().getId());
            query.setParameter("codigoTramo", tramo.getId());
            query.setParameter("fechaInicial", indicador.getFechaCp());
            query.setParameter("fechaFinal", indicador.getFechaMp());
            Number countMp=(Number) query.getSingleResult();
            Double valorMp = countMp.doubleValue();        
            indicador.setMetaLogradaMp(valorMp);        
        }
        
        // Para los indicadores 3 y 4
        // Cp
        if (indicador.getIdIndiceBasico().longValue() >=3 &&
        indicador.getFechaCp()!= null){
            String cadena = indicador.getSqlSeguimiento(false);
            Query queryCp = em.createNativeQuery( cadena );
            queryCp.setParameter("codigoFuente", tramo.getIdFuente().getId());
            queryCp.setParameter("codigoTramo", tramo.getId());
            queryCp.setParameter("fechaInicial1", indicador.getFechaCp());
            queryCp.setParameter("fechaInicial2", indicador.getFechaCp());
            Number countCp=(Number) queryCp.getSingleResult();
            Double valorCp = countCp.doubleValue();        
            indicador.setMetaLogradaCp(valorCp);        
        }
        // Mp
        if (indicador.getIdIndiceBasico().longValue() >=3 &&
        indicador.getFechaMp()!= null &&
        indicador.getFechaCp()!= null){
            String cadena = indicador.getSqlSeguimiento(true);
            System.out.println("sql mp "+ cadena);
            Query query = em.createNativeQuery( cadena );
            query.setParameter("codigoFuente", tramo.getIdFuente().getId());
            query.setParameter("codigoTramo", tramo.getId());
            query.setParameter("fechaInicial1", indicador.getFechaCp());
            query.setParameter("fechaInicial2", indicador.getFechaCp());
            query.setParameter("fechaFinal1", indicador.getFechaMp());
            query.setParameter("fechaFinal2", indicador.getFechaMp());
            
            Number countMp=(Number) query.getSingleResult();
            Double valorMp = countMp.doubleValue();        
            indicador.setMetaLogradaMp(valorMp);        
        }
        // Lp
        if (indicador.getIdIndiceBasico().longValue() >=3 &&
        indicador.getFechaMp()!= null &&
        indicador.getFechaLp()!= null){
            String cadena = indicador.getSqlSeguimiento(true);
            System.out.println("sql mp "+ cadena);
            Query query = em.createNativeQuery( cadena );
            query.setParameter("codigoFuente", tramo.getIdFuente().getId());
            query.setParameter("codigoTramo", tramo.getId());
            query.setParameter("fechaInicial1", indicador.getFechaMp());
            query.setParameter("fechaInicial2", indicador.getFechaMp());
            query.setParameter("fechaFinal1", indicador.getFechaLp());
            query.setParameter("fechaFinal2", indicador.getFechaLp());
            
            Number countMp=(Number) query.getSingleResult();
            Double valorMp = countMp.doubleValue();        
            indicador.setMetaLogradaLp(valorMp);        
        }
        
    }
    /**
     * Inserta o actualiza el seguimiento recibido como parametro
     * @param seguimiento
     * @return
     * @throws IdeamException
     */
    public PorhSeguimientoIndices updateSeguimientoIndice(PorhSeguimientoIndices seguimiento)throws IdeamException{
        if(seguimiento.getId()==null || seguimiento.getId().longValue()==0){
            seguimiento.setId( GeneradorSeq.obtenerSequencia(seguimiento.getCodigoAutoridad(), "seq_porh", em));
            em.persist(seguimiento);
            return seguimiento;
        }else{
            PorhSeguimientoIndices retorno = 
                em.merge( seguimiento );
            return retorno;
        }
    }
    /**
     * Retorna una lista de seguimientos asociados al indicador recibido como
     * parametro
     * @param indicador
     * @return
     * @throws IdeamException
     */
    public List<PorhSeguimientoIndices> getAllSeguimiento(PorhIndices indicador)throws IdeamException{
        Query query = em.createNamedQuery("PorhSeguimientoIndices.findAllByIndicador");
        query.setParameter("codigoIndicador", indicador.getId());
        return query.getResultList();
    }
    /**
     * Inserta o actualiza un indicador recibido como parametro
     * @param indicador
     * @return
     * @throws IdeamException
     */
    public PorhIndices updateIndicador(PorhIndices indicador)throws IdeamException{
        if(indicador.getId()==null || indicador.getId().longValue() == 0){
            indicador.setId( GeneradorSeq.obtenerSequencia(indicador.getCodigoAutoridad(), "seq_porh", em));
            em.persist(indicador);
            return indicador;
        }else{
            PorhIndices actualizado = em.merge(indicador);
            return actualizado;
        }
    }
    
    /**
     * Inserta los indicadores basicos asociados al tramo y plan de ordenamiento
     * recibidos por parametro
     * @param tramo
     * @param plan
     * @throws IdeamException
     */
    private void cargarIndicadoresBasicos(FnttTramo tramo, PorhPlanes plan, Long codigoAutoridad)throws IdeamException{
        List<PorhIndicesBasicos> lista = this.getAllIndicesBasicos();
        if(lista==null || lista.size()==0){
            throw new IdeamException("No existe informacion de Indicadores Basicos registrados, no se pueden crear los indicadores del Porh");
        }
        
        Iterator<PorhIndicesBasicos> it = lista.iterator();
        while(it.hasNext()){
            PorhIndicesBasicos indiceBasico = it.next();
            PorhIndices nuevoIndice = new PorhIndices();
            nuevoIndice.setIdTramo( tramo.getId() );
            nuevoIndice.setIdPlan( plan.getCodigo() );
            nuevoIndice.setIdIndiceBasico( indiceBasico.getId() );
            nuevoIndice.setNombre( indiceBasico.getNombre() );
            nuevoIndice.setCodigoAutoridad( codigoAutoridad );
            nuevoIndice.setId( GeneradorSeq.obtenerSequencia(nuevoIndice.getCodigoAutoridad(), "seq_porh", em));
            em.persist(nuevoIndice);
        }
    }
    /**
     * Retorna una lista con todos los datos basicos registrados en el sistema
     * @return
     * @throws IdeamException
     */
    private List<PorhIndicesBasicos> getAllIndicesBasicos()throws IdeamException{
        Query query = em.createNamedQuery("PorhIndicesBasicos.findAll");
        return query.getResultList();
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
        Query query = em.createNamedQuery("PorhIndices.findAllByTramo");
        query.setParameter("codigoTramo", tramo.getId()); 
        List lista = query.getResultList();
        if(lista==null || lista.size()==0){
            this.cargarIndicadoresBasicos(tramo, plan, codigoAutoridad);
            lista = this.getIndicadores(tramo, plan, codigoAutoridad);
        }
        Iterator it = lista.iterator();
        while(it.hasNext()){
            PorhIndices ind = (PorhIndices)it.next();
            if(ind.getPermitirSeguimiento()){
                this.getSeguimientoBasico(ind, tramo);
            }
        }
        return lista;
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
        Query query = em.createNamedQuery("PorvEfectividad.findAllByParametroFecha");
        query.setParameter("codigoParametro", codigoParametro);
        query.setParameter("fechaLimite", fechaLimite);
        query.setParameter("codigoFuente", codigoFuente);
        query.setParameter("codigoTramo", codigoTramo);
        return query.getResultList();
    }
    /**
     * Retorna la meta correspondiente al codigo recibido como parametro
     * @param codigo
     * @return
     * @throws IdeamException
     */
    public PorhMetas getMeta(Long codigo)throws IdeamException{
        try{
            Query query = em.createNamedQuery("PorhMetas.find");
            query.setParameter("codigo", codigo);
            return (PorhMetas)query.getSingleResult();
        }catch(NoResultException e){
            return null;
        }
    }
    /**
     * Inserta o actual la meta recibida como parametro
     * @param meta
     * @return
     * @throws IdeamException
     */
    public PorhMetas updateMeta(PorhMetas meta)throws IdeamException{        
        if(meta.getId()==null || meta.getId().longValue()==0){
            meta.setId( GeneradorSeq.obtenerSequencia(meta.getCodigoAutoridad(), "seq_porh", em));
            em.persist(meta);
            return meta;
        }else{            
            PorhMetas retorno = em.merge(meta);            
            return retorno;
        }
    }
    /**
     * Retorna todas las metas relacionadas con el tramo recibido como parametro
     * @param tramo
     * @return
     * @throws IdeamException
     */
    public List<PorvMetas> getAllMetas(FnttTramo tramo)throws IdeamException{
        Query query = em.createNamedQuery("PorvMetas.findAllByTramo");
        query.setParameter("codigoTramo", tramo.getId());
        return query.getResultList();        
    }
    /**
     * Inserta o actualiza un avance de meta
     * @param avance
     * @return
     * @throws IdeamException
     */    
    public PorhAvanceMetas updateAvanceMeta(PorhAvanceMetas avance)throws IdeamException{
        if(avance.getId()==null || avance.getId().longValue()==0){
            avance.setFecha_registro( GregorianCalendar.getInstance().getTime() );
            avance.setId( GeneradorSeq.obtenerSequencia(avance.getCodigoAutoridad(), "seq_porh", em));
            em.persist(avance);
            return avance;
        }else{
            return em.merge(avance);
        }
    }
    /**
     * Retorna todos los parametros de calidad asociados al tramo recibido como
     * parametro
     * @param tramo
     * @return
     * @throws IdeamException
     */
    public List<PorhParametrosTO> getParametros(FnttTramo tramo)throws IdeamException{
        Query query = em.createNamedQuery("PorhParametrosTO.findAllByTramo");
        query.setParameter("codigTramo", tramo.getId());
        return query.getResultList();
    }
    /**
     * Retorna las metas de avance de la meta recibida por parametro
     * @param codigoMeta
     * @return
     * @throws IdeamException
     */
    public List<PorhAvanceMetas> getAvanceMetas(Long codigoMeta)throws IdeamException{        
        Query query = em.createNamedQuery("PorhAvanceMetas.findAllByMeta");
        query.setParameter("codigoMeta", codigoMeta);
        return query.getResultList();
    }

    public PorhTramosProhibiciones updateTramoProhibicion(PorhTramosProhibiciones prohibicion)throws IdeamException{
        if(prohibicion.getId()==null || prohibicion.getId().longValue()==0){   
            prohibicion.setId( GeneradorSeq.obtenerSequencia(prohibicion.getCodigoAutoridad(), "seq_porh", em));
                em.persist(prohibicion);
                return prohibicion;
        }else{
            PorhTramosProhibiciones actualizado = 
                em.merge(prohibicion);
            return actualizado;
        }
    }
    /**
     * Retorna una lista con lsa prohibicioens registradas para el tramo
     * recibido como parametro
     * @param tramo
     * @return
     * @throws IdeamException
     */
    public List<PorhTramosProhibiciones> getAllProhibiciones(FnttTramo tramo)throws IdeamException{
        Query query = em.createNamedQuery("PorhTramosProhibiciones.findAllByPorhTramo");
        query.setParameter("codigoTramo", tramo.getId());
        return query.getResultList();
    }
    /**
     * Retorna el registro correspondiente al codigo recibido por parametro
     * @param codigo
     * @return
     */
    public PorhTramosUsosObjetivo getObjetivoCalidad(Long codigo)throws IdeamException{
        Query query = em.createNamedQuery("PorhTramosUsosObjetivo.find");
        query.setParameter("codigo", codigo);
        return (PorhTramosUsosObjetivo)query.getSingleResult();
    }
    /**
     * Retorna una lista con los parametros relacionados a un uso
     * @param usoTramo
     * @return
     * @throws IdeamException
     */
    public List<PorhParametrosTO> getParametros(PorhTramosUsos usoTramo)throws IdeamException{
        Query query = em.createNamedQuery("PorhParametrosTO.findAllByUso");
        query.setParameter("codigoUsoTramo", usoTramo.getId());
        return query.getResultList();
    }
    /**
     * Retorna una lista de los objetivos correspondinetes al uso
     * @param usoTramo
     * @return
     * @throws IdeamException
     */
    public List<PorhTramosUsosObjetivo> getObjetivosUso(PorhTramosUsos usoTramo)throws IdeamException{
        Query query = em.createNamedQuery("PorhTramosUsosPlazos.findAllByUsoTramo");
        query.setParameter("codigoUsoTramo", usoTramo.getId());
        return query.getResultList();
    }
    /**
     * Inserta o actualiza el valor del objetivo de calidad correspondiente al tramo y plazo
     * @param objetivo
     * @return
     * @throws IdeamException
     */
    public PorhTramosUsosObjetivo updateObjetivo(PorhTramosUsosObjetivo objetivo)throws IdeamException{
        PorhTramosUsosObjetivo actualizado = null;
        if(objetivo.getId() == null || objetivo.getId().longValue()==0){
            objetivo.setId( GeneradorSeq.obtenerSequencia(objetivo.getCodigoAutoridad(), "seq_porh", em));
            em.persist( objetivo );
            actualizado = objetivo;
        }else{
            actualizado = em.merge( objetivo );
        }
        return actualizado;
    }
    /**
     * Inserta o actualiza el plazo asociada al uno de un tramo
     * @param plazo
     * @return
     * @throws IdeamException
     */
    public PorhTramosUsosPlazos updateTramoUsoPlazo(PorhTramosUsosPlazos plazo)throws IdeamException{
        PorhTramosUsosPlazos plazoActualizado = null;
        if(plazo.getId()==null || plazo.getId().longValue()==0){
            plazo.setId( GeneradorSeq.obtenerSequencia(plazo.getCodigoAutoridad(), "seq_porh", em));
            em.persist(plazo);
            plazoActualizado = plazo;
        }else{
            plazoActualizado =  em.merge(plazo);
        }
        return plazoActualizado;
    }
    /**
     * Inserta o actual la relacion entre un tramo y un uso
     * @param tramoUso
     * @throws IdeamException
     */
    public PorhTramosUsos updateUsoTramo(PorhTramosUsos tramoUso)throws IdeamException{
        if(tramoUso.getId()==null || tramoUso.getId().longValue()==0){
            tramoUso.setId( GeneradorSeq.obtenerSequencia(tramoUso.getCodigoAutoridad(), "seq_porh", em));
            em.persist(tramoUso);
            return tramoUso;
        }else{
            PorhTramosUsos actualizado = em.merge(tramoUso);
            return actualizado;
        }
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
      Query query;
        if(plan==null){
          query = em.createNamedQuery("PorhTramosUsos.findAllByTramo");
          query.setParameter("codigoTramo", tramo.getId());
        }else{
          query = em.createNamedQuery("PorhTramosUsos.findAllByPorhTramo");
          query.setParameter("codigoPorh", plan.getCodigo());
          query.setParameter("codigoTramo", tramo.getId());
        }
        return query.getResultList();
    }
    /**
     * Inserta o actualiza una oferta demnda de un tramos relacionado a una fuente
     * con porh
     * @param ofertaDemanda
     * @throws IdeamException
     */        
    public void updateOfertaDemanda(PorhOfertaDemanda ofertaDemanda)throws IdeamException{
        if(ofertaDemanda.getId()==null || ofertaDemanda.getId().longValue()==0){
            ofertaDemanda.setId( GeneradorSeq.obtenerSequencia(ofertaDemanda.getCodigoAutoridad(), "seq_porh", em));
            em.persist( ofertaDemanda );            
        }else{
            em.merge( ofertaDemanda );
        }
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
        try{
            Query query = em.createNamedQuery("PorhOfertaDemanda.find");
            query.setParameter("idFuente",idFuente);
            query.setParameter("idTramo", idTramo);
            query.setMaxResults(1);
            return (PorhOfertaDemanda)query.getSingleResult();
        }catch(NoResultException e){
            return null;
        }
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
      try{
          Query query = em.createNamedQuery("PorhOfertaDemanda.findByPuntoMonitoreo");
          query.setParameter("idFuente",idFuente);
          query.setParameter("idTramo", idTramo);
          query.setParameter("idPuntoMonitoreo", idPuntoMonitoreo);
          query.setMaxResults(1);
          return (PorhOfertaDemanda)query.getSingleResult();
      }catch(NoResultException e){
          return null;
      }
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
        try{
            Query query = em.createNamedQuery("PorhPlanes.findByAutoridad");
            query.setParameter("codigoFuente", codigoFuente);
            query.setParameter("codigoAutoridad" , codigoAutoridad);
            PorhPlanes plan =  (PorhPlanes)query.getSingleResult();
            this.loadAttributtes(plan);
            return plan;
        }catch(NoResultException e){
            return null;
        }
    }
    /**
     * Actualiza la  informacion de un p,lan de ordenamiento
     * @param planOrdenamiento
     * @throws IdeamException
     */
    public PorhPlanes updatePlan(PorhPlanes planOrdenamiento)throws IdeamException{   
        System.out.println("CODIGO:"+planOrdenamiento.getCodigo());
        if(planOrdenamiento.getCodigo()==null||planOrdenamiento.getCodigo().intValue()==0){
            System.out.println("CREA EL PLAN con acto: "+planOrdenamiento.getNumeroActo());
            System.out.println("CREA EL PLAN con cod Autoridad: "+planOrdenamiento.getCodigoAutoridad());
            System.out.println("CREA EL PLAN con cod fuente: "+planOrdenamiento.getCodigoFuente());
            System.out.println("CREA EL PLAN con cod f expedicion: "+planOrdenamiento.getFechaExpedicion());
            System.out.println("CREA EL PLAN con cod f vigencia: "+planOrdenamiento.getFechaVigencia());
            planOrdenamiento.setCodigo( GeneradorSeq.obtenerSequencia(planOrdenamiento.getCodigoAutoridad(), "seq_porh", em));
            System.out.println("CREA EL PLAN CODIGO:"+planOrdenamiento.getCodigo());
            em.persist(planOrdenamiento);
            
        }else{
            planOrdenamiento= em.merge(planOrdenamiento);
        }
        
        System.out.println("PROH CREADO");
        try{
             //registroRemoto.registrarEvento(this.getClass().getName(),"updatePlan", planOrdenamiento);
        }catch(Exception e){ System.out.println("ERROR AL TRANSMITIR plan:"+e);}
        return this.getPlanOrdenamiento(planOrdenamiento.getCodigo());
    }
    
    
    
    public PorhPlanes updatePriorizacion(PorhPlanes planOrdenamiento)throws IdeamException{   

        if(planOrdenamiento.getCodigo()!=null &&
           planOrdenamiento.getCodigo().longValue() >0){
            System.out.println("borra priorizacion existente");
            Query query = em.createNamedQuery("PorhPriorizacion.deleteByPlan");
            query.setParameter("codigoPlan", planOrdenamiento.getCodigo());
            query.executeUpdate();
        }
        
        System.out.println("INICIA ANALISIA DE PRIORIZACION");
        if(planOrdenamiento.getListaPriorizacion()!=null && 
            planOrdenamiento.getCodigo() != null){
            Iterator it = planOrdenamiento.getListaPriorizacion().iterator();
            System.out.println("planOrdenamiento.getListaPriorizacion()"+planOrdenamiento.getListaPriorizacion().size());
            while(it.hasNext()){
                PorhPriorizacion pri = (PorhPriorizacion)it.next();
                pri.setCodigoAutgoridad( planOrdenamiento.getCodigoAutoridad() );
                pri.setCodigoPlan( planOrdenamiento.getCodigo().longValue() );
                pri.setId_lista( pri.getPrioridad().getCodigo() );
                pri.setId( GeneradorSeq.obtenerSequencia(pri.getCodigoAutgoridad(), "seq_porh", em));
                System.out.println("REGISTR NUEVA PRIORIZACION");
                em.persist(pri);
            }
            em.merge(planOrdenamiento);
        }
        try{
            // registroRemoto.registrarEvento(this.getClass().getName(),  "updatePriorizacion", planOrdenamiento);
        }catch(Exception e){ System.out.println("ERROR AL TRANSMITIR priorizacion:"+e);}
        return this.getPlanOrdenamiento(planOrdenamiento.getCodigo());
    }
    /**
     * Retorna el plan de ordenamiento correspondiente al codigo recibido por parametro
     * @param codigo
     * @return
     * @throws IdeamException
     */
    public PorhPlanes getPlanOrdenamiento(Long codigo)throws IdeamException{
        try{
            Query query = em.createNamedQuery("PorhPlanes.find");
            query.setParameter("codigo", codigo);            
            PorhPlanes plan =  (PorhPlanes)query.getSingleResult();
            this.loadAttributtes(plan);
            return plan;
        }catch(NoResultException e){
            return null;
        }
    }
    
    private List getPriorizacion(Long codigoPlan)throws IdeamException{
        Query query = em.createNamedQuery("PorhPriorizacion.findByPlan");
        query.setParameter("codigoPlan", codigoPlan);
        return query.getResultList();
    }
    private void loadAttributtes(PorhPlanes planOrdenamiento)throws IdeamException{
        List listaPrioridad = this.getPriorizacion( planOrdenamiento.getCodigo() );
        planOrdenamiento.setListaPriorizacion( listaPrioridad );
    }
    
    public List<PorhTramosProhiPMonitoreo> getPorhTramosProhiPMonitoreo(Long idTramosProhibiciones)throws IdeamException{
      Query query = em.createNamedQuery("PorhTramosProhiPMonitoreo.findAllByTramoProhibiciones");
      query.setParameter("idTramosProhibiciones", idTramosProhibiciones);
      return query.getResultList();
    }
    
}
