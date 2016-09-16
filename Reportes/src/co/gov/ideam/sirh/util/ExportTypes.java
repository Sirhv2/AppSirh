package co.gov.ideam.sirh.util;


import net.sf.jasperreports.engine.JasperPrint;
/**
 * Define los diferentes tipos de formatos en los cuales se puede
 * exportar un reporte y establece un metdo en coumn que debe ser
 * implementados por todos estos expoerters.
 */
public interface ExportTypes {
    public static final int EXPORT_PDF = 1;
    public static final int EXPORT_EXCEL = 2;
    /**
     * Retorna el contenido de un reporte exportado en un formato
     * especifico.
     * @param jasperPrint
     * @return
     * @throws IdeamException
     */
    public byte[] export(JasperPrint jasperPrint)throws IdeamException;
    /**
     * Retorna la extension correspondiente al tipo de contenido
     * @return
     */
    public String getExtension();
}
