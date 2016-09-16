package co.gov.ideam.sirh.oferta.business;

import co.gov.ideam.sirh.oferta.model.PartDatosIdf;
import co.gov.ideam.sirh.oferta.model.PartRefOfertaEstacSubzona;
import co.gov.ideam.sirh.oferta.model.ShmvDiariosHid;

import co.gov.ideam.sirh.oferta.model.ShmvMensualesHid;

import co.gov.ideam.sirh.oferta.model.SiovEstaciones;

import co.gov.ideam.sirh.oferta.model.SirhvOfertaMultianual;

import co.gov.ideam.sirh.oferta.model.SirhvOfertaMultianualMnsl;
import co.gov.ideam.sirh.util.ConstantesOferta;

import co.gov.ideam.sirh.util.IdeamException;

import java.math.BigInteger;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;



import javax.persistence.Query;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Stateless(name = "EstacionEJB", mappedName = "Sirh-EstacionEJB")
@Remote
public class EstacionEJBBean implements EstacionEJB {
    
    @PersistenceContext(unitName="SirhPU")
    private EntityManager em;
    
    public EstacionEJBBean() {
    }
    
    public List<ShmvDiariosHid> getDiarios(Long idEstacion, String variable, Integer agno, Integer mes) throws IdeamException{
        //System.out.println("-------- PARAMETROS idEstacion: "+idEstacion+", variable: "+variable+", agno: "+agno+", mes: "+mes);
        Query query = em.createNamedQuery("ShmvDiariosHid.findByParametros");
        query.setParameter("drsEstacionId", idEstacion);
        query.setParameter("drsVarId", variable);
        query.setParameter("drsAno", agno);
        query.setParameter("drsMes", mes);
        return query.getResultList();
    }
    
    public List<ShmvDiariosHid> getDiarios(Long idEstacion, String variable, Integer agno) throws IdeamException{
        //System.out.println("-------- PARAMETROS idEstacion: "+idEstacion+", variable: "+variable+", agno: "+agno);
        Query query = em.createNamedQuery("ShmvDiariosHid.findByParametrosMin");
        query.setParameter("drsEstacionId", idEstacion);
        query.setParameter("drsVarId", variable);
        query.setParameter("drsAno", agno);
        return query.getResultList();
    }
    
    public List<ShmvDiariosHid> getDiarios(Long idEstacion, String variable) throws IdeamException{
        //System.out.println("-------- PARAMETROS idEstacion: "+idEstacion+", variable: "+variable);
        Query query = em.createNamedQuery("ShmvDiariosHid.findByParametrosHist");
        query.setParameter("drsEstacionId", idEstacion);
        query.setParameter("drsVarId", variable);
        return query.getResultList();
    }
    
    public List<ShmvMensualesHid> getMensuales(Long idEstacion, String variable, Integer agno) throws IdeamException{
        //System.out.println("-------- PARAMETROS idEstacion: "+idEstacion+", variable: "+variable+", agno: "+agno);
        Query query = em.createNamedQuery("ShmvMensualesHid.findByParametros");
        query.setParameter("mnsEstacionId", idEstacion);
        query.setParameter("mnsVarId", variable);
        query.setParameter("mnsAno", agno);
        return query.getResultList();
    }
    
    public List<ShmvMensualesHid> getMensuales(Long idEstacion, String variable) throws IdeamException{
        //System.out.println("-------- PARAMETROS idEstacion: "+idEstacion+", variable: "+variable);
        Query query = em.createNamedQuery("ShmvMensualesHid.findByParametrosHist");
        query.setParameter("mnsEstacionId", idEstacion);
        query.setParameter("mnsVarId", variable);
        return query.getResultList();
    }
    
    public List<SiovEstaciones> getEstaciones() throws IdeamException{
        return em.createNamedQuery("SiovEstaciones.findAll").getResultList();
    }
    
    public SiovEstaciones getEstacion(SiovEstaciones estacion) throws IdeamException{
        Query query = em.createNamedQuery("SiovEstaciones.findByIdEs");
        query.setParameter("idEs", estacion.getIdEs());
        return (SiovEstaciones)query.getSingleResult();
    }
    
    public SiovEstaciones getEstacion(Long idEstacion) throws IdeamException{
        Query query = em.createNamedQuery("SiovEstaciones.findByIdEs");
        query.setParameter("idEs", idEstacion);
        return (SiovEstaciones)query.getSingleResult();
    }
    
    public SiovEstaciones getEstacion(String idEstacion) throws IdeamException{
        SiovEstaciones result = null;
        try{
            //System.out.println("=========================  SiovEstaciones.findByCodCatalogoEs");
            //System.out.println("=========================  codCatalogoEs: "+idEstacion);
            Query query = em.createNamedQuery("SiovEstaciones.findByCodCatalogoEs");
            query.setParameter("codCatalogoEs", idEstacion);
            result = (SiovEstaciones)query.getSingleResult();
        }catch(NoResultException e){
            
        }
        return result;
    }
    
    public List getMultianualesDiarios(Long idEstacion, String variable, Integer agno, String medida) throws IdeamException{
        List result = null;
        //System.out.println("------------------------------------getMultianualesDiarios");
        if(medida.equalsIgnoreCase(ConstantesOferta.PREFIJO_MES)){
            //System.out.println("------------------------------------getMultianualesDiarios MES");
            if(agno>0){
                result = this.getMultianualesDiariosMnsl(idEstacion, variable, agno);
            }else{
                result = this.getMultianualesDiariosMnsl(idEstacion, variable);
            }
        }else if(medida.equalsIgnoreCase(ConstantesOferta.PREFIJO_DIA)){
            //System.out.println("------------------------------------getMultianualesDiarios DIA");
            if(agno>0){
                result = this.getMultianualesDiariosDia(idEstacion, variable, agno);
            }else{
                result = this.getMultianualesDiariosDia(idEstacion, variable);
            }
        }
         return result;
    }
    
    private List<SirhvOfertaMultianual> getMultianualesDiariosDia(Long idEstacion, String variable) throws IdeamException{
        //System.out.println("-------- PARAMETROS idEstacion: "+idEstacion+", variable: "+variable);
        Query query = em.createNamedQuery("SirhvOfertaMultianual.findByEstacionVariable");
        query.setParameter("estacionId", idEstacion);
        query.setParameter("varId", variable);
        return query.getResultList();
    }
    
    private List<SirhvOfertaMultianual> getMultianualesDiariosDia(Long idEstacion, String variable, Integer agno) throws IdeamException{
        //System.out.println("-------- PARAMETROS idEstacion: "+idEstacion+", variable: "+variable+", agno: "+agno);
        Query query = em.createNamedQuery("SirhvOfertaMultianual.findByEstacionVariableAnio");
        query.setParameter("estacionId", idEstacion);
        query.setParameter("varId", variable);
        query.setParameter("ano", agno);
        return query.getResultList();
    }
    
    private List<SirhvOfertaMultianualMnsl> getMultianualesDiariosMnsl(Long idEstacion, String variable) throws IdeamException{
        //System.out.println("-------- PARAMETROS idEstacion: "+idEstacion+", variable: "+variable);
        Query query = em.createNamedQuery("SirhvOfertaMultianualMnsl.findByEstacionVariable");
        query.setParameter("estacionId", idEstacion);
        query.setParameter("varId", variable);
        return query.getResultList();
    }
    
    private List<SirhvOfertaMultianualMnsl> getMultianualesDiariosMnsl(Long idEstacion, String variable, Integer agno) throws IdeamException{
        //System.out.println("-------- PARAMETROS idEstacion: "+idEstacion+", variable: "+variable+", agno: "+agno);
        Query query = em.createNamedQuery("SirhvOfertaMultianualMnsl.findByEstacionVariableAnio");
        query.setParameter("estacionId", idEstacion);
        query.setParameter("varId", variable);
        query.setParameter("ano", agno);
        return query.getResultList();
    }
    
    
    public List getMultianualesResumen(Long idEstacion, String variable, Integer agno, String medida) throws IdeamException{
        //System.out.println("------------------------------------getMultianualesResumen");
        //System.out.println("-------- PARAMETROS idEstacion: "+idEstacion+", variable: "+variable+", agno: "+agno);
        List result = null;
        
        if(medida.equalsIgnoreCase(ConstantesOferta.PREFIJO_MES)){
            //System.out.println("------------------------------------getMultianualesResumen MES");
            result = this.getMultianualesResumenMensual(idEstacion, variable, agno);
        }else if(medida.equalsIgnoreCase(ConstantesOferta.PREFIJO_DIA)){
            //System.out.println("------------------------------------getMultianualesResumen DIA");
            result = this.getMultianualesResumenDiario(idEstacion, variable, agno);
        }
         return result;
    }
    
    private List<SirhvOfertaMultianual> getMultianualesResumenDiario(Long idEstacion, String variable, Integer agno) throws IdeamException{
        
        String group = " GROUP BY tipo, varId, estacionId";
        String where = " WHERE varId = :varId AND estacionId = :estacionId ";
        
        if(agno!=null && agno>0){
           where += " AND ano = :ano"; 
        }
        
        String sql = "";
        sql += " SELECT * ";
        sql += "  FROM (";
        sql += "SELECT 0 ano, tipo, varId, estacionId, " + 
        "avg(enero) enero, avg(febrero) febrero, avg(marzo) marzo, " + 
        "avg(abril) abril, avg(mayo) mayo, avg(junio) junio, avg(julio) julio, " + 
        "avg(agosto) agosto,avg(septiembre) septiembre, avg(octubre) octubre, " + 
        "avg(noviembre) noviembre,avg (diciembre) diciembre ";
        sql +=" FROM sirhv_oferta_multianual_diaria ";
        sql += where + " AND tipo = :tipoMedia" ; 
        sql += group;
        sql += " UNION "; 
        sql += "SELECT 0 ano, tipo, varId, estacionId, " + 
        "max(enero) enero, max(febrero) febrero, max(marzo) marzo, " + 
        "max(abril) abril, max(mayo) mayo, max(junio) junio, max(julio) julio, " + 
        "max(agosto) agosto, max(septiembre) septiembre, max(octubre) octubre, " + 
        "max(noviembre) noviembre, max (diciembre) diciembre ";
        sql +=" FROM sirhv_oferta_multianual_diaria ";
        sql += where + " AND tipo = :tipoMaximo" ; 
        sql += group;
        sql += " UNION ";
        sql += "SELECT 0 ano, tipo, varId, estacionId, " + 
        "min(enero) enero, min(febrero) febrero, min(marzo) marzo, " + 
        "min(abril) abril, min(mayo) mayo, min(junio) junio, min(julio) julio, " + 
        "min(agosto) agosto, min(septiembre) septiembre, min(octubre) octubre, " + 
        "min(noviembre) noviembre, min (diciembre) diciembre ";
        sql +=" FROM sirhv_oferta_multianual_diaria ";
        sql += where  + " AND tipo = :tipoMinimo" ;       
        sql += group;
        
        sql += " ) p ";
        sql += " ORDER BY p.tipo ";
        
        Query query = em.createNativeQuery(sql, SirhvOfertaMultianual.class);
        query.setParameter("estacionId", idEstacion);
        query.setParameter("varId", variable);
        query.setParameter("tipoMedia", ConstantesOferta.BOX_PLOT_MEDIOS);
        query.setParameter("tipoMaximo", ConstantesOferta.BOX_PLOT_MAXIMOS);
        query.setParameter("tipoMinimo", ConstantesOferta.BOX_PLOT_MINIMOS);
        
        if(agno!=null && agno>0){
            query.setParameter("ano", agno);
        }
        
        return query.getResultList();
    }
    
    private List<SirhvOfertaMultianualMnsl> getMultianualesResumenMensual(Long idEstacion, String variable, Integer agno) throws IdeamException{
        
        String group = " GROUP BY tipo, varId, estacionId";
        String where = " WHERE varId = :varId AND estacionId = :estacionId ";
        
        if(agno!=null && agno>0){
           where += " AND ano = :ano"; 
        }
        
        String sql = "";
        sql += " SELECT * ";
        sql += "  FROM (";
        sql += "SELECT 0 ano, tipo, varId, estacionId, " + 
        "avg(enero) enero, avg(febrero) febrero, avg(marzo) marzo, " + 
        "avg(abril) abril, avg(mayo) mayo, avg(junio) junio, avg(julio) julio, " + 
        "avg(agosto) agosto,avg(septiembre) septiembre, avg(octubre) octubre, " + 
        "avg(noviembre) noviembre,avg (diciembre) diciembre ";
        sql +=" FROM sirhv_oferta_multianual_mnsl ";
        sql += where + " AND tipo = :tipoMedia" ; 
        sql += group;
        sql += " UNION "; 
        sql += "SELECT 0 ano, tipo, varId, estacionId, " + 
        "max(enero) enero, max(febrero) febrero, max(marzo) marzo, " + 
        "max(abril) abril, max(mayo) mayo, max(junio) junio, max(julio) julio, " + 
        "max(agosto) agosto, max(septiembre) septiembre, max(octubre) octubre, " + 
        "max(noviembre) noviembre, max (diciembre) diciembre ";
        sql +=" FROM sirhv_oferta_multianual_mnsl ";
        sql += where + " AND tipo = :tipoMaximo" ; 
        sql += group;
        sql += " UNION ";
        sql += "SELECT 0 ano, tipo, varId, estacionId, " + 
        "min(enero) enero, min(febrero) febrero, min(marzo) marzo, " + 
        "min(abril) abril, min(mayo) mayo, min(junio) junio, min(julio) julio, " + 
        "min(agosto) agosto, min(septiembre) septiembre, min(octubre) octubre, " + 
        "min(noviembre) noviembre, min (diciembre) diciembre ";
        sql +=" FROM sirhv_oferta_multianual_mnsl ";
        sql += where  + " AND tipo = :tipoMinimo" ;       
        sql += group;
        
        sql += " ) p ";
        sql += " ORDER BY p.tipo ";
        
        Query query = em.createNativeQuery(sql, SirhvOfertaMultianualMnsl.class);
        query.setParameter("estacionId", idEstacion);
        query.setParameter("varId", variable);
        query.setParameter("tipoMedia", ConstantesOferta.BOX_PLOT_MEDIOS);
        query.setParameter("tipoMaximo", ConstantesOferta.BOX_PLOT_MAXIMOS);
        query.setParameter("tipoMinimo", ConstantesOferta.BOX_PLOT_MINIMOS);
        
        if(agno!=null && agno>0){
            query.setParameter("ano", agno);
        }
        
        return query.getResultList();
    }
    
    public List<String> getVariables(Long idEstacion) throws IdeamException{
        String sql = "";
        sql += " SELECT ";
        sql += " DISTINCT mns_var_id " ; 
        sql += "FROM ";
        sql += " shmv_mensuales_hid ";
        sql += "WHERE ";
        sql += " mns_estacion_id= :idEstacion ";
        sql += " UNION ";
        sql += " SELECT ";
        sql += " DISTINCT drs_var_id ";
        sql += "FROM ";
        sql += " shmv_diarios_hid ";
        sql += "WHERE ";
        sql += " drs_estacion_id= :idEstacion ";
        
        Query query = em.createNativeQuery(sql);
        query.setParameter("idEstacion", idEstacion);
        
        return query.getResultList();
    }

    public PartRefOfertaEstacSubzona getPartRefOfertaEstacSubzona(String idEstacion) {
        PartRefOfertaEstacSubzona result = null;
        try{
            Query query = em.createNamedQuery("PartRefOfertaEstacSubzona.findByIdEstacion");
            query.setParameter("idEstacion", idEstacion);
            result = (PartRefOfertaEstacSubzona)query.getSingleResult();
        }catch(NoResultException e){
            
        }
        return result;
    }    
    
    //CDONCEL
    public List<SiovEstaciones> getEstacionesByMun(String mun)  throws IdeamException{
        //System.out.println("-------- PARAMETROS findByMun idEstacion: "+idEstacion+", variable: "+variable);
        Query query = em.createNamedQuery("SiovEstaciones.findByDescDivipola");
        query.setParameter("descDivipola", mun);
        return query.getResultList();
    }
    
    public List<SiovEstaciones> findMeteoBySubzona(int subzona){
      try {
        String sql = "select * FROM siov_estaciones s where s.CODIGO_DIVIPOLA IN(" + 
        "SELECT p.ID_DIVIPOLA FROM PART_ZONIFIC_DIVIPOLAMUN p WHERE P.ID_ZONIFICACION = " 
          + subzona +") and s.CLASE_ES ='MET'";                
        Query query = em.createNativeQuery(sql,SiovEstaciones.class);
        return query.getResultList();    
      }catch (Exception e){
        System.err.println("ERROR OBTENIENDO ESTACIONES METEOROLOGICAS POR SUBZONA.   " + e.getLocalizedMessage());
        return null;
        }
      }

      //LISTADO DE MUNICIPIOS QUE TIENEN DATOS IDF
    
      public List<SiovEstaciones> findEstacionesIDF(){
        try {
          String sql = "select * from siov_estaciones s where S.COD_CATALOGO_ES IN " +
            "(select DISTINCT(ESTACION_ID) FROM PART_DATOS_IDF)";                
          Query query = em.createNativeQuery(sql,SiovEstaciones.class);
          return query.getResultList();    
        }catch (Exception e){
          System.err.println("ERROR OBTENIENDO ESTACIONES CON DATOS IDF.   " + e.getLocalizedMessage());
          return null;
          }
        }
      
      public List<PartDatosIdf> findDatosIDF(int cod){
          Query query = em.createNamedQuery("PartDatosIdf.findByEstacionId");
          BigInteger codB = BigInteger.valueOf(cod);
          query.setParameter("estacionId", codB);
          return query.getResultList();
        }

    //FIN_CDONCEL
    
    
}
