package co.gov.ideam.sirh.business;

import co.gov.ideam.sirh.datasource.JRDataSourceIdeam;
import co.gov.ideam.sirh.util.IdeamException;

import java.sql.Connection;

import java.sql.SQLException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import java.util.Set;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import javax.naming.Context;

import javax.naming.InitialContext;

import javax.naming.NamingException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.hibernate.Session;
import org.hibernate.connection.ConnectionProvider;
import org.hibernate.engine.SessionFactoryImplementor;

@Stateless(mappedName = "Reporte", name = "ReporteBean")
public class ReporteEJBBean implements ReporteEJB {
    
    @PersistenceContext (unitName="SirhPU")
    private EntityManager em; 
    
    public ReporteEJBBean() {
    }
    
    /**
     * Genera un reporte pasando como origen de datos la ista y adicionalmente
     * envia los parametros del map
     * @param jasperReport
     * @param parameters
     * @param data
     * @return
     * @throws IdeamException
     */
    public JasperPrint fillReportFromJasperDataSource(JasperReport jasperReport,
                                              Map parameters,
                                              List data)throws IdeamException{
        Map map = this.basicReports(parameters);            
        JRDataSourceIdeam ds = new JRDataSourceIdeam(data);
        try {
            return JasperFillManager.fillReport(jasperReport,
                                            map,
                                            ds);
        } catch (JRException e) {
            throw new IdeamException(e);
        }
    }
    
    /**
     * Genera un reporte pasando como parametro la lista de datos recibida como
     * parametro
     * @param jasperReport
     * @param data
     * @return
     * @throws IdeamException
     */
    public JasperPrint fillReportFromJasperDataSource(JasperReport jasperReport,
                                              List data)throws IdeamException{
        Map map = this.basicReports(null);            
        JRDataSourceIdeam ds = new JRDataSourceIdeam(data);
        try {
            return JasperFillManager.fillReport(jasperReport,
                                            map,
                                            ds);
        } catch (JRException e) {
            throw new IdeamException(e);
        }
    }
    /**
     * Genera un reporte pasando como origen de datos una conexion a la BD
     * @param jasperReport
     * @param parameters
     * @return
     * @throws IdeamException
     */
    public JasperPrint fillReportFromDataBase(JasperReport jasperReport,
                                                    Map parameters)throws IdeamException{
        //Session session = (Session) em.getDelegate(); 
        //SessionFactoryImplementor sfi = (SessionFactoryImplementor) session.getSessionFactory(); 
        //ConnectionProvider cp = sfi.getConnectionProvider();         
        Connection con = null;
        try{
            System.out.println(">>>>>>>>>>>>>>> entro a fillReportFromDataBase");
            Context c = new InitialContext();
            DataSource dataSource =(DataSource)c.lookup("java:jboss/datasources/sirhDS");
            System.out.println(">>>>>>>>>>>>>>> entro lookup");
            con = dataSource.getConnection();
            System.out.println(">>>>>>>>>>>>>>> obtuvo con");
            Map map = this.basicReports(parameters);            
            System.out.println(">>>>>>>>>>>>>>> obtuvo mapa");
            JasperPrint jp = JasperFillManager.fillReport(
                    jasperReport,
                    map,
                    con);   
            
            System.out.println(">>>>>>>>>>>>>>> paso fill");

            return jp;
            
    } catch (NamingException e) {
           throw new IdeamException(e);
    } catch(SQLException e){
            throw new IdeamException(e);
    } catch(JRException e){
            throw new IdeamException(e);
    } finally{
            try{
                if (con != null){
                    con.close();
                }
            }catch(SQLException e){
                throw new IdeamException(e);
            }
        }
                                                
    }
    
    
    /**
     * Retorna un Map con los parametros basicos que se deben pasar a todos
     * los reportes
     * @return
     */
    private Map basicReports(Map parametros){        
        Map map = null;
        if (parametros == null){
            map = new HashMap();
        }else{
            map = parametros;
        }
        map.put("p_direccion", "Calle 25D N.96B-70 , Bogot\u00E1 D.C.");
        map.put("p_telefono", "Tel. (+57)(1)3 52 71 60");
        map.put("p_web", "www.ideam.gov.co");
        map.put("p_razonsocial", "Instituto de Hidrologia, Meteorologia y Estudios Ambientales");
        map.put("p_logo_image", "imgs/logoIdeam.jpg");        
        map.put("p_images_dir", "imgs");
        map.put("p_sistema", "Sistema de Información del Recurso Hídrico");
        map.put("p_nit", "NIT. 830.000.602-5");
        
        return map;
    }    
}
