package co.gov.ideam.sirh.red.ideam.business;

import co.gov.ideam.sirh.calidad.model.CriteriosBusquedaMuestrasTO;
import co.gov.ideam.sirh.red.ideam.model.FqLaboratorios;
import co.gov.ideam.sirh.red.ideam.model.FqMuestras;
import co.gov.ideam.sirh.red.ideam.model.SirhvMuestrasFq;
import co.gov.ideam.sirh.red.ideam.model.SirhvPuntoMonitoreoFq;

import co.gov.ideam.sirh.util.IdeamException;

import java.text.SimpleDateFormat;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless(name = "RedMonitoreoEJB", mappedName = "Sirh-RedMonitoreoEJB")
@Remote

public class RedMonitoreoEJBBean implements RedMonitoreoEJB{
    
    @PersistenceContext(unitName="SirhPU")
    private EntityManager em;
    
    public RedMonitoreoEJBBean() {
    }
    
    public List<SirhvPuntoMonitoreoFq> getAllPuntos() throws IdeamException{
        return em.createNamedQuery("SirhvPuntoMonitoreoFq.findAll").getResultList();
    }
    
    public List<SirhvPuntoMonitoreoFq> getAllPuntos(String nombre) throws IdeamException{
          
        Query query = em.createNamedQuery("SirhvPuntoMonitoreoFq.findByPunto");
        query.setParameter("punto", "%" + nombre.toUpperCase() + "%");
        return query.getResultList();
    }

    public String getCriteriosConsultasIdeam(CriteriosBusquedaMuestrasTO criterios) throws IdeamException{
        String sql =  "";    
        boolean whereIncluido = false;
        
        if(criterios.getAutoridad()!=null){
            if(!whereIncluido){
                sql += " where ";
                whereIncluido = true;
            }else{
                sql += " and ";
            }                
            sql += " autoridad = " + criterios.getAutoridad().getId();
        }
        if(criterios.getArea()!=null){
            if(!whereIncluido){
                sql += " where ";
                whereIncluido = true;
            }else{
                sql += " and ";
            }          
            sql = sql + " id_area = " + criterios.getArea().getId();
        }
    
        if(criterios.getZona()!=null){
            if(!whereIncluido){
                sql += " where ";
                whereIncluido = true;
            }else{
                sql += " and ";
            }          
            sql = sql + " id_zona = " + criterios.getZona().getId();
        }
        
        if(criterios.getSubZona()!=null){
            if(!whereIncluido){
                sql += " where ";
                whereIncluido = true;
            }else{
                sql += " and ";
            }          
            sql = sql + " id_subzona = " + criterios.getSubZona().getId();       
        }
        
        if(criterios.getDepartamento()!=null){
            if(!whereIncluido){
                sql += " where ";
                whereIncluido = true;
            }else{
                sql += " and ";
            }          
            sql = sql + " id_departamento = " + criterios.getDepartamento().getId();
            
        }
        
        if(criterios.getMunicipio()!=null){
            if(!whereIncluido){
                sql += " where ";
                whereIncluido = true;
            }else{
                sql += " and ";
            }          
            sql = sql + " id_municipio = " + criterios.getMunicipio().getId();
            
        }
        
        if(criterios.getFuente()!=null) {
            if(!whereIncluido){
                sql += " where ";
                whereIncluido = true;
            }else{
                sql += " and ";
            }          
            sql = sql + " id_fuente = " + criterios.getFuente().getId();
        }      
        
        if(criterios.getPtoMonitoreo()!=null) {
            if(!whereIncluido){
                sql += " where ";
                whereIncluido = true;
            }else{
                sql += " and ";
            }  
                sql = sql + " id_punto = " + criterios.getPtoMonitoreo().getId();
        }  
        
        if(criterios.getParametro()!=null) {
            if(!whereIncluido){
                sql += " where ";
                whereIncluido = true;
            }else{
                sql += " and ";
            }          
            sql = sql + " id_parametro = " + criterios.getParametro().getCodigo();
        }                  
            
        if(criterios.getFechaInicial()!=null) {
           if(!whereIncluido){
               sql += " where ";
               whereIncluido = true;
           }else{
               sql += " and ";
           }          
           SimpleDateFormat sdf = new SimpleDateFormat("d/M/y");
           sql = sql + " fecha_ini >= to_date('" + sdf.format( criterios.getFechaInicial().getTime()) + "','dd/mm/yyyy')";
        }

        if(criterios.getFechaFinal()!=null ) {
           if(!whereIncluido){
               sql += " where ";
               whereIncluido = true;
           }else{
               sql += " and ";
           }          
           SimpleDateFormat sdf = new SimpleDateFormat("d/M/y");
            sql = sql + " fecha_fin <= to_date('" + sdf.format( criterios.getFechaFinal().getTime()) + "','dd/mm/yyyy')";
        }
    System.out.println("SQL_WHERE = " + sql);
             
        return sql;  
        
        }
    
    public List<SirhvPuntoMonitoreoFq> getAllPuntosCriterios(CriteriosBusquedaMuestrasTO criterios) throws IdeamException{
        String sql = SirhvPuntoMonitoreoFq.listaPuntosMonitoreos+getCriteriosConsultasIdeam(criterios);
        sql+= " group by id, punto, fuente, area, zona,subzona, departamento, municipio, latitud, " + 
              " direccion_latitud, longitud, direccion_longitud, altitud, tipofuente, " + 
              " autoridad, gradoslat, minutoslat, segundolat, segundoslat, gradoslong, " + 
              " minutoslong, segundolong, codcatalogoes, tramo, tipopunto, " + 
              " sistema_referencia, descripcionacceso, ubicacion, vertimiento " +
              " order by punto asc";
        System.out.println("SQLgetAllPuntosCriterios = " + sql);        
        Query q = em.createNativeQuery(sql, SirhvPuntoMonitoreoFq.class);
        return q.getResultList();
    }
    
    
    public SirhvPuntoMonitoreoFq getPunto(SirhvPuntoMonitoreoFq punto) throws IdeamException {
        Query query = em.createNamedQuery("SirhvPuntoMonitoreoFq.findById");
        query.setParameter("id", punto.getId());
        return (SirhvPuntoMonitoreoFq)query.getSingleResult();
    }
    
    public SirhvPuntoMonitoreoFq getPunto(Long idPunto) throws IdeamException {
        Query query = em.createNamedQuery("SirhvPuntoMonitoreoFq.findById");
        query.setParameter("id", idPunto);
        return (SirhvPuntoMonitoreoFq)query.getSingleResult();
    }
    
    public List<FqMuestras> getAllMuestrasByPunto(Long idPunto) throws IdeamException{
        Query query = em.createNamedQuery("FqMuestras.findByEstacionId");
        query.setParameter("estacionId", idPunto);
        return query.getResultList();
    }
    
    public FqMuestras getMuestra(Long idMuestra) throws IdeamException{
        Query query = em.createNamedQuery("FqMuestras.findByCodigoMuestra");
        query.setParameter("codigoMuestra", idMuestra);
        return (FqMuestras)query.getSingleResult();
    }
    
    public List<SirhvMuestrasFq> getAllAnalisisByPuntoMuestra(Long idPunto, Long idMuestra) 
                    throws IdeamException{
        
        Query query = em.createNamedQuery("SirhvMuestrasFq.findByEstacionMuestra");
        query.setParameter("estacionid", idPunto);
        query.setParameter("codigomuestra", idMuestra);
        
        return query.getResultList();
    }
    
    public FqLaboratorios getLaboratorio(Long idLaboratorio) throws IdeamException {
        Query query = em.createNamedQuery("FqLaboratorios.findById");
        query.setParameter("idLaboratorio", idLaboratorio);
        return (FqLaboratorios)query.getSingleResult();
    }
    
    
}
