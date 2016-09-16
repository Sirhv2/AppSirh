package co.gov.ideam.sirh.util;


import java.io.ByteArrayOutputStream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JExcelApiExporter;
import net.sf.jasperreports.engine.export.JExcelApiExporterParameter;

/**
 * Genera un contenido en formato pdf.
 */
public class ExportXLS implements ExportTypes{
    /**
     * Retorna un arreglo de bytes con el cntenido del reporte en formato
     * pdf.
     * @param jasperPrint
     * @return
     * @throws IdeamException
     */
    public byte[] export(JasperPrint jasperPrint)throws IdeamException{
        try{
            ByteArrayOutputStream output = new ByteArrayOutputStream();            
            JExcelApiExporter jExcelApiExporter = new JExcelApiExporter(); 
            jasperPrint.setProperty("net.sf.jasperreports.export.xls.exclude.origin.keep.first.band.1", "pageHeader");                 
            jasperPrint.setProperty("net.sf.jasperreports.export.xls.exclude.origin.band.2", "pageFooter");             
            
            jExcelApiExporter.setParameter(JExcelApiExporterParameter.JASPER_PRINT, jasperPrint); 
            jExcelApiExporter.setParameter(JExcelApiExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE); 
            jExcelApiExporter.setParameter(JExcelApiExporterParameter.OUTPUT_STREAM, output); 
            jExcelApiExporter.setParameter(JExcelApiExporterParameter.IS_IGNORE_CELL_BORDER,Boolean.FALSE); 
            jExcelApiExporter.setParameter(JExcelApiExporterParameter.IS_ONE_PAGE_PER_SHEET,Boolean.FALSE); 
            jExcelApiExporter.setParameter(JExcelApiExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,Boolean.TRUE); 
            jExcelApiExporter.setParameter(JExcelApiExporterParameter.OFFSET_X,0); 
            jExcelApiExporter.setParameter(JExcelApiExporterParameter.OFFSET_Y,0 ); 
            jExcelApiExporter.setParameter(JExcelApiExporterParameter.IGNORE_PAGE_MARGINS, Boolean.TRUE);
            
            jExcelApiExporter.exportReport();            
            return output.toByteArray();            
        }catch(JRException e){            
            throw new IdeamException("JRException Error generando el reporte " + e.getMessage());
        }
    }

    public String getExtension() {
        return "xls";
    }
}
