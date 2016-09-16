package co.gov.ideam.sirh.util;
/**
 * Fabrica de los diferentes tipos de formatos en los cuales se pueden
 * exportar los reportes
 */
public class ExportTypeFactory {
    public ExportTypeFactory() {
        super();
    }
    /**
     * Reorna un exporter segun el parametro recibido
     * @param exportType
     * @return
     */
    public ExportTypes getExportType(int exportType){
            switch(exportType){
                case ExportTypes.EXPORT_PDF:
                    return new ExportPDF();
                case ExportTypes.EXPORT_EXCEL:
                    return new ExportXLS();
                default:
                    return new ExportPDF();
            }
    }    
}
