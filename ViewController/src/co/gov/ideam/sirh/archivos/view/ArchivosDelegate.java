package co.gov.ideam.sirh.archivos.view;

import co.gov.ideam.sirh.archivos.business.ArchivosEJB;
import co.gov.ideam.sirh.archivos.model.Masivo.Entidad.CmatControlCargueV2;
import co.gov.ideam.sirh.archivos.model.Masivo.Entidad.CmatPuntosMonitoreoV2;
import co.gov.ideam.sirh.archivos.model.RowTO;
import co.gov.ideam.sirh.archivos.model.TipoArchivoTO;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.servlet.ServletLocator;
import co.gov.ideam.sirh.util.IdeamException;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;

public class ArchivosDelegate {
    /**
     * Referencia al EJB
     */
    private static ArchivosEJB archivosService = null;    
    /**
    * Utilizada para implementar singleton
    */
    private static ArchivosDelegate instance;
    /**
     * Retorna una instancia a la clase
     * @return
     */
    public static ArchivosDelegate getInstance() throws IdeamException {
        if (instance==null){
            instance = new ArchivosDelegate();
        }
        return instance;
    }  
    /**
     * Valida la informacion del archivo Excel
     * @param tipoArchivo
     * @return
     */
    public List<RowTO> validateSheet(TipoArchivoTO tipoArchivo, String sheetName, byte[] archivo,  Autoridades autoridad)throws IdeamException{
        return archivosService.validateSheet(tipoArchivo, sheetName, archivo, autoridad);
    }
    /**
     * Retorna una lista con las hojas contenidas en un archio excel
     * @param archivo
     * @return
     * @throws IdeamException
     */
    public List<String> getSheets(byte[] archivo)throws IdeamException{
        return archivosService.getSheets(archivo);
    }
    /**
     * Retorna una lista de los tipos de archivos excel que se pueden cargar
     * @return
     * @throws IdeamException
     */
    public List getAllTipoArchivo()throws IdeamException{
        return archivosService.getAllTipoArchivo(); 
    }
    
    /**
     * Retorna una lista de los tipos de archivos excel que se pueden cargar
     * @return
     * @throws IdeamException
     */
    public List getAllTipoArchivoDatosReferencia()throws IdeamException{
        return archivosService.getAllTipoArchivoDatosReferencia(); 
    }
    
    public List<RowTO> validateSheetClient(TipoArchivoTO tipoArchivo, 
                                           String sheetName, File archivo, 
                                           Autoridades autoridad)throws IdeamException{
        return archivosService.validateSheetClient(tipoArchivo, sheetName, archivo, autoridad);
    }

    /**
    * Construcutor privado para implementar singleton
    */    
    private ArchivosDelegate() {
        archivosService = ServletLocator.getArchivosService();
    }
    
    /*public CmatControlCargueV2 getCmatControlCargueV2(Long idAutoridad) throws IdeamException{
        return archivosService.getCmatControlCargueV2(idAutoridad);
    }*/
    
    public CmatPuntosMonitoreoV2 getCmatPuntosMonitoreoV2 (Long idControlCargue) throws IdeamException{
        return archivosService.getCmatPuntosMonitoreoV2(idControlCargue);
    }
}
