package co.gov.ideam.sirh.datos.referencia.business;

import co.gov.ideam.sirh.datos.referencia.model.DemtAcueducto;
import co.gov.ideam.sirh.datos.referencia.model.DemtEmpresa;

import co.gov.ideam.sirh.datos.referencia.model.DemtFacturado;
import co.gov.ideam.sirh.datos.referencia.model.DemtTarifa;
import co.gov.ideam.sirh.datos.referencia.model.SirhvCaudalTipoUsuario;
import co.gov.ideam.sirh.datos.referencia.model.SirhvTarifaEstratificada;
import co.gov.ideam.sirh.util.IdeamException;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless(name = "DatosReferenciaEJB", mappedName = "Sirh-DatosReferenciaEJB")
@Remote
public class DatosReferenciaEJBBean implements DatosReferenciaEJB {
    @PersistenceContext (unitName="SirhPU")
    private EntityManager em;
    
    public DatosReferenciaEJBBean() {
    }
    
    public List<DemtEmpresa> getAllEmpresa()throws IdeamException{
        List result = null;
        Query query = em.createNamedQuery("DemtEmpresa.findAll");
        result = query.getResultList();
        return result;
    }
    
    public List<DemtTarifa> getAllTarifa()throws IdeamException{
        List result = null;
        Query query = em.createNamedQuery("DemtTarifa.findAll");
        result = query.getResultList();
        return result;
    }
    
    public List<DemtFacturado> getAllFacturado()throws IdeamException{
        List result = null;
        Query query = em.createNamedQuery("DemtFacturado.findAll");
        result = query.getResultList();
        return result;
    }
    
    public List<DemtAcueducto> getAllAcueducto()throws IdeamException{
        List result = null;
        Query query = em.createNamedQuery("DemtAcueduto.findAll");
        result = query.getResultList();
        return result;
    }
    
    public DemtAcueducto getAcueducto(Integer idAcueducto)throws IdeamException{
        DemtAcueducto result = null;
        try {
            Query query = em.createNamedQuery("DemtAcueducto.findById");
            query.setParameter("id", idAcueducto);
            result = (DemtAcueducto)query.getSingleResult();
        }catch (javax.persistence.NoResultException e) {
            //throw new IdeamException();
        }
        return result;
    }
    
    public DemtAcueducto getAcueductoByEmpresa(Integer idEmpresa)throws IdeamException{
        DemtAcueducto result = null;
        try {
            Query query = em.createNamedQuery("DemtAcueducto.findByEmpresa");
            query.setParameter("idEmpresa", idEmpresa);
            result = (DemtAcueducto)query.getSingleResult();
        }catch (javax.persistence.NoResultException e) {
            
        }
        return result;
    }
    
    public DemtAcueducto getAcueductoByUbicacion(Integer departamento, Integer municipio)throws IdeamException{
        DemtAcueducto result = null;
        try {
            Query query = em.createNamedQuery("DemtAcueducto.findByDepartamentoMunicipio");
            query.setParameter("departamento", departamento);
            query.setParameter("municipio", municipio);
            result = (DemtAcueducto)query.getSingleResult();
        }catch (javax.persistence.NoResultException e) {
            
        }
        return result;
    }
    
    
    public DemtEmpresa getEmpresa(Integer idEmpresa)throws IdeamException{
        DemtEmpresa result = null;
        try {
            Query query = em.createNamedQuery("DemtEmpresa.findById");
            query.setParameter("id", idEmpresa);
            result = (DemtEmpresa)query.getSingleResult();
        }catch (javax.persistence.NoResultException e) {
            
        }
        return result;
    }
    
    public DemtEmpresa getEmpresaByNombre(String nombre)throws IdeamException{
        DemtEmpresa result = null;
        try {
            Query query = em.createNamedQuery("DemtEmpresa.findByNombre");
            query.setParameter("nombre", nombre);
            result = (DemtEmpresa)query.getSingleResult();
        }catch (javax.persistence.NoResultException e) {
            
        }
        return result;
    }
    
    public DemtEmpresa getEmpresaByNit(String nit)throws IdeamException{
        DemtEmpresa result = null;
        try {
            Query query = em.createNamedQuery("DemtEmpresa.findByNit");
            query.setParameter("nit", nit);
            result = (DemtEmpresa)query.getSingleResult();
        }catch (javax.persistence.NoResultException e) {
            
        }
        return result;
    }
    
    public DemtTarifa getTarifa(Integer idTarifa)throws IdeamException{
        DemtTarifa result = null;
        try {
            Query query = em.createNamedQuery("DemtTarifa.findById");
            query.setParameter("id", idTarifa);
            result = (DemtTarifa)query.getSingleResult();
        }catch (javax.persistence.NoResultException e) {
            
        }
        return result;
    }
    
    public DemtTarifa getTarifaByFiltros(
            Integer municipio, Integer departamento, Integer agno, Integer mes, 
            Integer idEmpresa, Integer estrato, Integer clase)throws IdeamException{
        DemtTarifa result = null;
        try {
            Query query = em.createNamedQuery("DemtTarifa.findByFiltros");
            query.setParameter("municipio", municipio);
            query.setParameter("departamento", departamento);
            query.setParameter("agno", agno);
            query.setParameter("mes", mes);
            query.setParameter("idEmpresa", idEmpresa);
            query.setParameter("estrato", estrato);
            query.setParameter("clase", clase);
            result = (DemtTarifa)query.getSingleResult();
        }catch (javax.persistence.NoResultException e) {
            
        }
        return result;
    }
    
    public DemtFacturado getFacturado(Integer idFacturado)throws IdeamException{
        DemtFacturado result = null;
        try {
            Query query = em.createNamedQuery("DemtFacturado.findById");
            query.setParameter("id", idFacturado);
            result = (DemtFacturado)query.getSingleResult();
        }catch (javax.persistence.NoResultException e) {
            
        }
        return result;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public DemtEmpresa createEmpresa(DemtEmpresa empresa) throws IdeamException {
        em.persist(empresa);
        em.flush();
        em.refresh(empresa);
        
        return empresa;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public DemtEmpresa updateEmpresa(DemtEmpresa empresa) throws IdeamException {
        empresa = em.merge(empresa);
        return empresa;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public DemtAcueducto createAcueducto(DemtAcueducto acueducto) throws IdeamException {
        em.persist(acueducto);
        em.flush();
        em.refresh(acueducto);
        
        return acueducto;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public DemtAcueducto updateAcueducto(DemtAcueducto acueducto) throws IdeamException {
        acueducto = em.merge(acueducto);
        return acueducto;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public DemtTarifa createTarifa(DemtTarifa tarifa) throws IdeamException {
        em.persist(tarifa);
        em.flush();
        em.refresh(tarifa);
        
        return tarifa;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public DemtTarifa updateTarifa(DemtTarifa tarifa) throws IdeamException {
        tarifa = em.merge(tarifa);
        return tarifa;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public DemtFacturado createFactura(DemtFacturado factura) throws IdeamException {
        em.persist(factura);
        em.flush();
        em.refresh(factura);
        
        return factura;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public DemtFacturado updateFactura(DemtFacturado factura) throws IdeamException {
        factura = em.merge(factura);
        return factura;
    }
    
    public List getAllTarifa(Integer agno, String tipoCargo, Integer clase) throws IdeamException{
        List result = null;
        try {
            Query query = em.createNamedQuery("SirhvTarifaEstratificada.findByAgnoClaseCargo");
            query.setParameter("agno", agno);
            query.setParameter("tipoCargo", tipoCargo);
            query.setParameter("clase", clase);
            result = query.getResultList();
        }catch (javax.persistence.NoResultException e) {
            
        }
        return result;
    }
    
    public List getAllTarifa(Integer agno, String tipoCargo) throws IdeamException{
        List result = null;
        try {
            Query query = em.createNamedQuery("SirhvTarifaEstratificada.findByAgnoCargo");
            query.setParameter("agno", agno);
            query.setParameter("tipoCargo", tipoCargo);
            result = query.getResultList();
        }catch (javax.persistence.NoResultException e) {
            
        }
        return result;
    }
    
    private List getAllTipousuarioCaudal1(Date fechaInicio, Date FechaFinal) throws IdeamException{
        List result = null;
        try {
            Query query = em.createNamedQuery("SirhvCaudalTipoUsuario.findByFechas");
            query.setParameter("fechaInicio", fechaInicio);
            query.setParameter("fechaFin", FechaFinal);
            result = query.getResultList();
        }catch (javax.persistence.NoResultException e) {
            
        }
        return result;
    }
    
    public List getAllTipousuarioCaudal(Date fechaInicio, Date FechaFinal) throws IdeamException{
        List result = null;
        try {
            String sql = "";            
            sql += " SELECT max(id) id, max(fechaExpCaudal) fechaExpCaudal, ";
            sql += "sum(caudalConcesionado) caudalConcesionado, ";
            sql += "tipoPersona tipoPersona, autoridad "; 
            sql += "FROM  sirhv_caudal_tipo_usuario ";
            sql += "WHERE fechaExpCaudal Between  :fechaInicio AND :fechaFin ";
            sql += "GROUP by tipoPersona, autoridad ";
            sql += "ORDER by autoridad, tipoPersona ";
            
            Query query = em.createNativeQuery(sql, SirhvCaudalTipoUsuario.class);
            query.setParameter("fechaInicio", fechaInicio);
            query.setParameter("fechaFin", FechaFinal);
            result = query.getResultList();
        }catch (javax.persistence.NoResultException e) {
            
        }
        return result;
    }
    
    public List getAllEstadoUsuarioCaudal(Integer municipio) throws IdeamException{
        List result = null;
        try {
            Query query = null;
            if(municipio > 0){
                query = em.createNamedQuery("SirhvCaudalEstadoUsuario.findByMunicipio");
                query.setParameter("municipio", municipio);
            }else{
                query = em.createNamedQuery("SirhvCaudalEstadoUsuario.findAll");
            }
            
            result = query.getResultList();
        }catch (javax.persistence.NoResultException e) {
            
        }
        return result;
    }

    
}
