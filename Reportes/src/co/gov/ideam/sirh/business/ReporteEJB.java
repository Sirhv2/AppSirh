package co.gov.ideam.sirh.business;


import co.gov.ideam.sirh.util.IdeamException;

import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Remote
public interface ReporteEJB {
    /**
     * Retorna un reporte generado con los objetos recibidos como parametros.
     * @param jasperReport
     * @param parameters
     * @param data
     * @return
     * @throws IdeamException
     */
    public JasperPrint fillReportFromJasperDataSource(JasperReport jasperReport,
                                              Map parameters,
                                              List data)throws IdeamException;
    /**
     * Retorna un reporte generado con los objetos recibidos como parametros.
     * @param jasperReport
     * @param data
     * @return
     * @throws IdeamException
     */
    public JasperPrint fillReportFromJasperDataSource(JasperReport jasperReport,
                                              List data)throws IdeamException;
    /**
     * Retorna un reporte generado con los objetos recibidos como parametros.
     * @param jasperReport
     * @param parameters
     * @return
     * @throws IdeamException
     */
    public JasperPrint fillReportFromDataBase(JasperReport jasperReport,
                                                    Map parameters)throws IdeamException;
    
    
   
}
