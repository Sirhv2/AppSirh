package co.gov.ideam.sirh.calidad.business;

import co.gov.ideam.sirh.calidad.model.AcuiferosPOJO2;
import co.gov.ideam.sirh.calidad.model.JSCalidadParametrosPOJO;
import co.gov.ideam.sirh.calidad.model.JSDemandaPOJO;
import co.gov.ideam.sirh.calidad.model.JSDemandaTO;
import co.gov.ideam.sirh.calidad.model.JSMonitoreoPiezometricoPOJO;
import co.gov.ideam.sirh.calidad.model.JSPomcaPOJO;
import co.gov.ideam.sirh.calidad.model.JSPueaPOJO;
import co.gov.ideam.sirh.usuarios.agua.model.CriteriosBusquedaExternasJSTO;
import co.gov.ideam.sirh.util.IdeamException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless(name = "ReportesEJB", mappedName = "Sirh-CalidadEJB")
@Remote
public class ReportesEJBBean implements ReportesEJB {

    @PersistenceContext(unitName = "SirhPU")
    private EntityManager em;
  
    private List<JSDemandaPOJO> listDemandaPojos = new ArrayList<JSDemandaPOJO>();
    private List<JSMonitoreoPiezometricoPOJO> listMonitoreoPiezometricoPojos = new ArrayList<JSMonitoreoPiezometricoPOJO>();
    private List<JSCalidadParametrosPOJO> listCalidadPojos = new ArrayList<JSCalidadParametrosPOJO>();
    private List<JSPomcaPOJO> listPomcaPojos = new ArrayList<JSPomcaPOJO>();
    private List<AcuiferosPOJO2> listAcuiferosPojos = new ArrayList<AcuiferosPOJO2>();
    private List<JSPueaPOJO> listPueaPojos = new ArrayList<JSPueaPOJO>();
    List<Object[]> listado ;


    public ReportesEJBBean() throws IdeamException {
   

    }
    
  public List<JSDemandaPOJO> getComponenteDemanda() throws IdeamException {
      String sql = "";      
          sql = JSDemandaTO.consultaComponenteDemandaXAcuifero; 
          Query q = em.createNativeQuery(sql);  
     List<Object[]> listado = q.getResultList();
     JSDemandaPOJO pojo;
     for(int i = 0; i < listado.size();i++){
         pojo = new JSDemandaPOJO();
         pojo.setCaptaciones(new Double(listado.get(i)[0].toString()));
         pojo.setAcuifero(listado.get(i)[1].toString());
         pojo.setSigla(listado.get(i)[2].toString());
         listDemandaPojos.add(pojo);
       }
     
  
      return listDemandaPojos;
  }

  public List<JSMonitoreoPiezometricoPOJO> getMonitoreoPiezometrico() throws IdeamException {
    this.listMonitoreoPiezometricoPojos.clear();
      String sql = "";
          sql = JSDemandaTO.consultaMonitoreoPiezometrico;
          sql = sql + " ORDER BY FN.FECHA_MEDICION , ft.nombre , fn.nivel_medio ";
      Query q = em.createNativeQuery(sql);
     List<Object[]> listado = q.getResultList();
     JSMonitoreoPiezometricoPOJO pojo;
     for(int i = 0; i < listado.size();i++){
         pojo = new JSMonitoreoPiezometricoPOJO();
         pojo.setTipoCaptacion(listado.get(i)[1].toString());
         pojo.setDepartamento(listado.get(i)[2].toString());
         pojo.setIdentificadorPunto(listado.get(i)[4].toString());
         pojo.setAcuifero(listado.get(i)[7].toString());
       pojo.setNivelPiezometrico(Double.valueOf(listado.get(i)[11].toString()));
       pojo.setTipoFuente(listado.get(i)[6].toString());
       pojo.setNivel(listado.get(i)[8].toString());     
       pojo.setFecha(Date.valueOf(listado.get(i)[9].toString()));
         listMonitoreoPiezometricoPojos.add(pojo);
       }
      return listMonitoreoPiezometricoPojos;
  }
  
  public List<JSMonitoreoPiezometricoPOJO> getMonitoreoPiezometricoFiltros(CriteriosBusquedaExternasJSTO criterios) throws IdeamException {
   this.listMonitoreoPiezometricoPojos.clear();
      String sql = "";
          sql = JSDemandaTO.consultaMonitoreoPiezometrico;
          if(criterios.getAcuifero() != null){
            sql = sql + " WHERE REPLACE (ft.nombre , '-',' ') LIKE '%" + criterios.getAcuifero() + "%'";
            }
          
          if(criterios.getIdPunto() != null){
            sql = sql + " AND REPLACE (CP.IDENTIFICADOR_PUNTO , '-', ' ') LIKE '%" + criterios.getIdPunto() +"%' ";
            }
          sql = sql + " ORDER BY FN.FECHA_MEDICION , ft.nombre , fn.nivel_medio ";
      Query q = em.createNativeQuery(sql);
     List<Object[]> listado = q.getResultList();
     JSMonitoreoPiezometricoPOJO pojo;
     for(int i = 0; i < listado.size();i++){
         pojo = new JSMonitoreoPiezometricoPOJO();
         pojo.setTipoCaptacion(listado.get(i)[1].toString());
        pojo.setDepartamento(listado.get(i)[2].toString());
         pojo.setIdentificadorPunto(listado.get(i)[4].toString());
         pojo.setAcuifero(listado.get(i)[7].toString());
         pojo.setNivelPiezometrico(Double.valueOf(listado.get(i)[11].toString()));
         pojo.setTipoFuente(listado.get(i)[6].toString());
         pojo.setNivel(listado.get(i)[8].toString());     
         pojo.setFecha(Date.valueOf(listado.get(i)[9].toString()));
         listMonitoreoPiezometricoPojos.add(pojo);
       }
      return listMonitoreoPiezometricoPojos;
  }

  public List<AcuiferosPOJO2> getAcuiferosFiltros(CriteriosBusquedaExternasJSTO criterios) throws IdeamException {
   this.listAcuiferosPojos.clear();
          
    String sql = JSDemandaTO.consultaAcuiferos;
      if(criterios.getProvincia()!= null){
            sql = sql + " AND dat.provincia_hidroge LIKE '%" + criterios.getProvincia() +"%'";
            }
          else if(criterios.getProvincia()== null && criterios.getArea()!= null){
            sql = sql + " AND dat.area_hidrografica LIKE '%" + criterios.getArea() +"%'";
            }
    //System.out.println("getConsultAcuiferos -> " + sql);
    Query q = em.createNativeQuery(sql);
    List<Object[]> listado = q.getResultList();
    AcuiferosPOJO2 pojo;
    //System.out.println("getConsultaAcuifeross ->  3 " + listado.size());
    listAcuiferosPojos.clear();
    for (int i = 0; i < listado.size(); i++) {
      pojo = new AcuiferosPOJO2();
      pojo.setPlantillaID(Integer.parseInt(listado.get(i)[0].toString()));
      pojo.setArea_hidrografica(listado.get(i)[1].toString());
      pojo.setZona_hidrografica(listado.get(i)[2].toString());
      pojo.setProvinciaHidroge(listado.get(i)[3].toString());
      pojo.setNombrePlantilla(listado.get(i)[4].toString());
      pojo.setCarGestiona(listado.get(i)[5].toString());
      pojo.setNumPozoInventariados(listado.get(i)[6].toString());
      pojo.setNumAlijibesInventariados(listado.get(i)[7].toString());
      pojo.setNumManantialesInventariados(listado.get(i)[8].toString());
      pojo.setDemandaCalculada(listado.get(i)[9].toString());   
      listAcuiferosPojos.add(pojo);
    }
    return listAcuiferosPojos;
  }


  
  public List<JSDemandaPOJO> getComponenteDemanda3() throws IdeamException {
      String sql = JSDemandaTO.consultaComponenteDemanda3;
      sql = sql + " GROUP BY TPC.VALOR ";
      Query q = em.createNativeQuery(sql);
     List<Object[]> listado = q.getResultList();
     JSDemandaPOJO pojo;
     for(int i = 0; i < listado.size();i++){
         pojo = new JSDemandaPOJO();
         pojo.setCaptaciones(new Double(listado.get(i)[0].toString()));
         pojo.setValor(listado.get(i)[1].toString());
         listDemandaPojos.add(pojo);
       }
      //listDemandaPojos = q.getResultList();
      return listDemandaPojos;
}

  public List<JSDemandaPOJO> getComponenteDemanda3Filtos(CriteriosBusquedaExternasJSTO criterios) {
    String sql = JSDemandaTO.consultaComponenteDemanda3;
   
    if (criterios.getAutoridad() != null ) {
      sql =
          sql + " WHERE ft.cod_autoridad = " + criterios.getAutoridad() + " ";
    }  
   
   else  if (criterios.getProvinciaId() != null ) {
      sql =
          sql + " WHERE cp.provincia_hidrogeologica = " + criterios.getProvinciaId() +
          " ";
    }  

    sql = sql + " GROUP BY TPC.VALOR ";
    Query q = em.createNativeQuery(sql);
    List<Object[]> listado = q.getResultList();
    JSDemandaPOJO pojo;
    listDemandaPojos.clear();
    for (int i = 0; i < listado.size(); i++) {
      pojo = new JSDemandaPOJO();
      pojo.setCaptaciones(new Double(listado.get(i)[0].toString()));
      pojo.setValor(listado.get(i)[1].toString());
      listDemandaPojos.add(pojo);
    }
    //listDemandaPojos = q.getResultList();
    return listDemandaPojos;
  }


  /**
   * @return
   * @throws IdeamException
   */
  public List<JSDemandaPOJO> getComponenteDemandaXProvincia() throws IdeamException {
      String sql = JSDemandaTO.consultaComponenteDemandaXProvivincia;
      Query q = em.createNativeQuery(sql);
      List<Object[]> listado = q.getResultList();
      JSDemandaPOJO pojo;
      listDemandaPojos.clear();
      for (int i = 0; i < listado.size(); i++) {
        pojo = new JSDemandaPOJO();
        pojo.setCaptaciones(new Double(listado.get(i)[0].toString()));
        pojo.setAcuifero(listado.get(i)[1].toString());
        pojo.setSigla(listado.get(i)[2].toString());
        listDemandaPojos.add(pojo);
      }
      return listDemandaPojos;
    }

    public List<JSCalidadParametrosPOJO> getComponenteCalidadXParametros() throws IdeamException {
      String sql = JSDemandaTO.listaParametrosCalidadXTipoPunto;
      
      Query q = em.createNativeQuery(sql);
      List<Object[]> listado = q.getResultList();
      JSCalidadParametrosPOJO pojo;
      listCalidadPojos.clear();
      for (int i = 0; i < listado.size(); i++) {
        pojo = new JSCalidadParametrosPOJO();
        pojo.setIdCaptacion(Integer.parseInt(listado.get(i)[0].toString()));
        pojo.setTipoCaptacion(listado.get(i)[1].toString());
        pojo.setDepartamento(listado.get(i)[2].toString());
        pojo.setMunicipio(listado.get(i)[3].toString());
        pojo.setIdPunto(listado.get(i)[4].toString());
        pojo.setTipoPunto(listado.get(i)[5].toString());
        pojo.setTipoFuente(listado.get(i)[6].toString());
        pojo.setAcuifero(listado.get(i)[7].toString());
        pojo.setPuntoMonitoreo(listado.get(i)[8].toString());
        pojo.setIdMuestra(Integer.parseInt(listado.get(i)[9].toString()));
        pojo.setTipoMuestra(listado.get(i)[10].toString());
        pojo.setMedicion(Integer.parseInt(listado.get(i)[11].toString()));
        pojo.setParametroCalidad(listado.get(i)[12].toString());
        pojo.setValor(new Double(listado.get(i)[13].toString()));
        pojo.setProvHidro(listado.get(i)[14].toString());
        pojo.setRed(listado.get(i)[15].toString());
        pojo.setRed2(listado.get(i)[16].toString());
        listCalidadPojos.add(pojo);
      }
      return listCalidadPojos;
    }
    
  public List<JSCalidadParametrosPOJO> getComponenteCalidadXParametrosFiltros(CriteriosBusquedaExternasJSTO criterios) throws IdeamException {
     String sql = JSDemandaTO.listaParametrosCalidadXTipoPunto;
     if(criterios.getProvincia()!= null){
            sql = sql + " WHERE NVL(TO_CHAR(PHID.valor),0) LIKE '%"+criterios.getProvincia()+"%' ";
            }else if(criterios.getProvincia()== null && criterios.getAcuifero()!= null){
            sql = sql + " WHERE ft.nombre LIKE '%"+criterios.getAcuifero()+"%' ";
            }
     Query q = em.createNativeQuery(sql);
     List<Object[]> listado = q.getResultList();
     JSCalidadParametrosPOJO pojo;
     listCalidadPojos.clear();
     for (int i = 0; i < listado.size(); i++) {
       pojo = new JSCalidadParametrosPOJO();
       pojo.setIdCaptacion(Integer.parseInt(listado.get(i)[0].toString()));
       pojo.setTipoCaptacion(listado.get(i)[1].toString());
       pojo.setDepartamento(listado.get(i)[2].toString());
       pojo.setMunicipio(listado.get(i)[3].toString());
       pojo.setIdPunto(listado.get(i)[4].toString());
       pojo.setTipoPunto(listado.get(i)[5].toString());
       pojo.setTipoFuente(listado.get(i)[6].toString());
       pojo.setAcuifero(listado.get(i)[7].toString());
       pojo.setPuntoMonitoreo(listado.get(i)[8].toString());
       pojo.setIdMuestra(Integer.parseInt(listado.get(i)[9].toString()));
       pojo.setTipoMuestra(listado.get(i)[10].toString());
       pojo.setMedicion(Integer.parseInt(listado.get(i)[11].toString()));
       pojo.setParametroCalidad(listado.get(i)[12].toString());
       pojo.setValor(new Double(listado.get(i)[13].toString()));
       pojo.setProvHidro(listado.get(i)[14].toString());
       pojo.setRed(listado.get(i)[15].toString());
       pojo.setRed2(listado.get(i)[16].toString());
       listCalidadPojos.add(pojo);
     }
     return listCalidadPojos;
   }


  /**
   * @return
   * @throws IdeamException
   */
  public List<AcuiferosPOJO2> getConsultaAcuiferos() throws IdeamException {
    String sql = JSDemandaTO.consultaAcuiferos;
    //System.out.println("getConsultAcuiferos -> " + sql);
    Query q = em.createNativeQuery(sql);
    List<Object[]> listado = q.getResultList();
    //System.out.println("getConsultaAcuifeross ->  2 " );
    AcuiferosPOJO2 pojo;
    //System.out.println("getConsultaAcuifeross ->  3 " + listado.size());
    listAcuiferosPojos.clear();
    for (int i = 0; i < listado.size(); i++) {
      pojo = new AcuiferosPOJO2();
      pojo.setPlantillaID(Integer.parseInt(listado.get(i)[0].toString()));
      pojo.setArea_hidrografica(listado.get(i)[1].toString());
      pojo.setZona_hidrografica(listado.get(i)[2].toString());
      pojo.setProvinciaHidroge(listado.get(i)[3].toString());
      pojo.setNombrePlantilla(listado.get(i)[4].toString());
      pojo.setCarGestiona(listado.get(i)[5].toString());
      pojo.setNumPozoInventariados(listado.get(i)[6].toString());
      pojo.setNumAlijibesInventariados(listado.get(i)[7].toString());
      pojo.setNumManantialesInventariados(listado.get(i)[8].toString());
      pojo.setDemandaCalculada(listado.get(i)[9].toString());   
      listAcuiferosPojos.add(pojo);
    }
    //System.out.println("getConsultaAcuifeross ->  4 " + listAcuiferosPojos.size());
    return listAcuiferosPojos;
  }

  public List<JSPomcaPOJO> getConsultaPomca() {
    String sql = JSDemandaTO.getconsultaPomca;
   // System.out.println("getConsultaPomca -> " + sql);
    Query q = em.createNativeQuery(sql);
    List<Object[]> listado = q.getResultList();
    JSPomcaPOJO pojo;
    listPomcaPojos.clear();
    for (int i = 0; i < listado.size(); i++) {
      pojo = new JSPomcaPOJO();
      pojo.setPomca_id(Integer.parseInt(listado.get(i)[0].toString()));
      pojo.setNombre(listado.get(i)[1].toString());   
      pojo.setAutor(listado.get(i)[2].toString());  
      pojo.setNombrePomca((listado.get(i)[3].toString()));    
      pojo.setId_autoridad(Integer.parseInt(listado.get(i)[4].toString()));
     pojo.setPrograma(listado.get(i)[5].toString());
     pojo.setProyecto(listado.get(i)[6].toString());
     pojo.setActividad(listado.get(i)[7].toString());
      pojo.setPorcCumplimiento(Double.parseDouble(listado.get(i)[8].toString()));
            pojo.setPorcEjecucion(Double.parseDouble(listado.get(i)[9].toString()));
            pojo.setPresupuestoAsignado(Double.parseDouble(listado.get(i)[10].toString()));
            pojo.setPresupuestoeEjecutado(Double.parseDouble(listado.get(i)[11].toString()));
          
         
          
      listPomcaPojos.add(pojo);
    }
    return listPomcaPojos;
  }
  
  public List<JSPomcaPOJO> getConsultaPomcaArea( String area) {
    String sql = JSDemandaTO.getconsultaPomca;          
        sql = sql + " and  autor.nombre like '%" + area +"%'";       
    Query q = em.createNativeQuery(sql);
    List<Object[]> listado = q.getResultList();
    JSPomcaPOJO pojo;
    listPomcaPojos.clear();
    for (int i = 0; i < listado.size(); i++) {
      pojo = new JSPomcaPOJO();
      pojo.setPomca_id(Integer.parseInt(listado.get(i)[0].toString()));
      pojo.setNombre(listado.get(i)[1].toString());   
      pojo.setAutor(listado.get(i)[2].toString());  
      pojo.setNombrePomca((listado.get(i)[3].toString()));    
      pojo.setId_autoridad(Integer.parseInt(listado.get(i)[4].toString()));
     pojo.setPrograma(listado.get(i)[5].toString());
     pojo.setProyecto(listado.get(i)[6].toString());
     pojo.setActividad(listado.get(i)[7].toString());
      pojo.setPorcCumplimiento(Double.parseDouble(listado.get(i)[8].toString()));
            pojo.setPorcEjecucion(Double.parseDouble(listado.get(i)[9].toString()));
            pojo.setPresupuestoAsignado(Double.parseDouble(listado.get(i)[10].toString()));
            pojo.setPresupuestoeEjecutado(Double.parseDouble(listado.get(i)[11].toString()));
          
         
          
      listPomcaPojos.add(pojo);
    }
    return listPomcaPojos;
  }
  
  public List<JSPueaPOJO> getConsultaPueaEst() {
    String sql = JSDemandaTO.getPueaEstados;          
        sql = sql + " GROUP BY l.valor "; 
    System.out.println("sql getConsultaPueaEst ->" + sql);
    Query q = em.createNativeQuery(sql);
    List<Object[]> listado = q.getResultList();
    JSPueaPOJO pojo;
    listPueaPojos.clear();
    for (int i = 0; i < listado.size(); i++) {
      pojo = new JSPueaPOJO();
      pojo.setDescripcion(listado.get(i)[0].toString());
      pojo.setCantidad(Double.valueOf(listado.get(i)[1].toString()));
      listPueaPojos.add(pojo);
    }
    return listPueaPojos;
  }
  
  public List<JSPueaPOJO> getConsultaPueaEstCriterios(CriteriosBusquedaExternasJSTO criterios) {
    String sql = JSDemandaTO.getPueaEstados;          
        
    if(criterios.getAutoridad()!= null){
       sql = sql +" WHERE au.id = " + criterios.getAutoridad() +" ";
      }
    sql = sql + " GROUP BY l.valor ";
    System.out.println("sql getConsultaPueaEst con ->" + sql);
    Query q = em.createNativeQuery(sql);
    List<Object[]> listado = q.getResultList();
    JSPueaPOJO pojo;
    listPueaPojos.clear();
    for (int i = 0; i < listado.size(); i++) {
      pojo = new JSPueaPOJO();
      pojo.setDescripcion(listado.get(i)[0].toString());
      pojo.setCantidad(Double.valueOf(listado.get(i)[1].toString()));
      listPueaPojos.add(pojo);
    }
    return listPueaPojos;
  }
  
  public List<JSPueaPOJO> getConsultaPueaSeg() {
    String sql = JSDemandaTO.getPueaSeguimiento;
    sql = sql + " group by au.sigla order by 2 desc ";
    System.out.println("sql getConsultaPueaEst sin ->" + sql);
    Query q = em.createNativeQuery(sql);
    List<Object[]> listado = q.getResultList();
    JSPueaPOJO pojo;
    listPueaPojos.clear();
    for (int i = 0; i < listado.size(); i++) {
      pojo = new JSPueaPOJO();
      pojo.setDescripcion(listado.get(i)[0].toString());
      pojo.setCantidad(Double.valueOf(listado.get(i)[1].toString()));
      listPueaPojos.add(pojo);
    }
    return listPueaPojos;
  }
  
  public List<JSPueaPOJO> getConsultaPueaSegCriterios(CriteriosBusquedaExternasJSTO criterios) {
    String sql = JSDemandaTO.getPueaSeguimiento;
    if(criterios.getAutoridad()!= null){
       sql = sql +" WHERE au.id = " + criterios.getAutoridad() +" ";
      }
    sql = sql + " group by au.sigla order by 2 desc  ";
    System.out.println("sql getConsultaPueaSeg con ->" + sql);
    Query q = em.createNativeQuery(sql);
    List<Object[]> listado = q.getResultList();
    JSPueaPOJO pojo;
    listPueaPojos.clear();
    for (int i = 0; i < listado.size(); i++) {
      pojo = new JSPueaPOJO();
      pojo.setDescripcion(listado.get(i)[0].toString());
      pojo.setCantidad(Double.valueOf(listado.get(i)[1].toString()));
      listPueaPojos.add(pojo);
    }
    return listPueaPojos;
  }
  
}//Fin clase

