package co.gov.ideam.sirh.util;


import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
/**
 * Genera un contenido en formato pdf.
 */
public class ExportPDF implements ExportTypes{
    /**
     * Retorna un arreglo de bytes con el cntenido del reporte en formato
     * pdf.
     * @param jasperPrint
     * @return
     * @throws IdeamException
     */
    public byte[] export(JasperPrint jasperPrint)throws IdeamException{
        try{
            byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);
            return bytes;
        }catch(JRException e){
            throw new IdeamException("Error generando el reporte " + e.getMessage());
        }
    }
    /**
     * Retorna la extension correspondiente al tipo de contenido
     * @return
     */
    public String getExtension(){
        return "pdf";
    }
}
