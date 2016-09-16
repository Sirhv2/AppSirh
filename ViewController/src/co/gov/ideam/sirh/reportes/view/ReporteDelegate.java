package co.gov.ideam.sirh.reportes.view;

import co.gov.ideam.sirh.business.ReporteEJB;
import co.gov.ideam.sirh.servlet.ServletLocator;
import co.gov.ideam.sirh.util.IdeamException;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
/**
 * Delegate encargado de generar los diferentes reportes que componen el sistema.
 */
public class ReporteDelegate{
    /**
     * constante para definir tipo de reporte PDF.
     */
    public static final int PDF = 1;
    /**
     * constante para definir tipo de reporte PDF.
     */
    public static final int EXCEL = 2;
    
    /**
     * Referencia al EJB de Reportes.
     */
    private static ReporteEJB reporteService = null;    
    /**
    * Utilizada para implementar singleton.
    */
    private static ReporteDelegate instance;
    /**
     * Retorna una instancia a la clase.
     * @return
     * @throws IdeamException
     */
    public static ReporteDelegate getInstance() throws IdeamException {
        if (instance==null){
            instance = new ReporteDelegate();
        }
        return instance;
    }
    
    /**
     * Genera un reporte pasando como origen de datos la ista y adicionalmente
     * envia los parametros del map.
     * @param jasperReport
     * @param parameters
     * @param data
     * @return
     * @throws IdeamException
     */    
    public JasperPrint fillReportFromJasperDataSource(JasperReport jasperReport,
                                              Map parameters,
                                              List data)throws IdeamException{
        return reporteService.fillReportFromJasperDataSource(jasperReport,
                                                     parameters,
                                                     data);                                              
    }
    /**
     * Genera un reporte pasando como parametro la lista de datos recibida como
     * parametro.
     * @param jasperReport
     * @param data
     * @return
     * @throws IdeamException
     */    
    public JasperPrint fillReportFromJasperDataSource(JasperReport jasperReport,
                                              List data)throws IdeamException{
        return reporteService.fillReportFromJasperDataSource(jasperReport, data);                                              
    }
    /**
     * Ejecuta un reporte con una conexion a la base de datos.
     * @param jasperReport
     * @param parameters
     * @return
     * @throws IdeamException
     */
    public JasperPrint fillReportFromDataBase(JasperReport jasperReport, 
                                                    Map parameters)throws IdeamException{
        return reporteService.fillReportFromDataBase(jasperReport, parameters);        
    }
    /**
     * Constructor privado para implementar singleton
     * @throws IdeamException
     */
    private ReporteDelegate() throws IdeamException {
        reporteService = ServletLocator.getReporteService();
    }
}
