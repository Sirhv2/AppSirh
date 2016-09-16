package co.gov.ideam.sirh.seguridad.view;

import co.gov.ideam.sirh.util.IdeamException;

import java.util.List;

import org.apache.commons.beanutils.DynaClass;
/**
 * Interface que deben implemntar las clases que generan reportes 
 * directamente en iText
 */
public interface ReporteInteface {
    /**
     * Lee un pdf y retora el contenido del mismo en bytes para ser
     * mostrado en una pagina al usuario
     * @param registros
     * @param dynaClass
     * @param session
     * @return
     * @throws IdeamException
     */    
    public byte[] createReport(List registros, DynaClass dynaClass, String session)throws IdeamException;
}
