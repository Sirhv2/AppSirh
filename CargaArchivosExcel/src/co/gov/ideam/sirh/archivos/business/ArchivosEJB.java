package co.gov.ideam.sirh.archivos.business;


import co.gov.ideam.sirh.archivos.model.Masivo.Entidad.CmatControlCargueV2;
import co.gov.ideam.sirh.archivos.model.Masivo.Entidad.CmatPuntosMonitoreoV2;
import co.gov.ideam.sirh.archivos.model.RowTO;
import co.gov.ideam.sirh.archivos.model.TipoArchivoTO;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.util.IdeamException;

import java.io.File;

import java.util.List;

import javax.ejb.Remote;


@Remote
public interface ArchivosEJB {    
    /**
     * Valida la informacion del archivo Excel
     * @param tipoArchivo
     * @return
     */
    public List<RowTO> validateSheet(TipoArchivoTO tipoArchivo, String sheetName, byte[] archivo,  Autoridades autoridad)throws IdeamException;
    /**
     * Retorna una lista con las hojas contenidas en un archio excel
     * @param archivo
     * @return
     * @throws IdeamException
     */
    public List<String> getSheets(byte[] archivo)throws IdeamException;
    /**
     * Retorna una lista de los tipos de archivos excel que se pueden cargar
     * @return
     * @throws IdeamException
     */
    public List getAllTipoArchivo()throws IdeamException;
    
    
    public List<RowTO> validateSheetClient(TipoArchivoTO tipoArchivo, 
                                           String sheetName, File archivo, 
                                           Autoridades autoridad)throws IdeamException;
    
    public List getAllTipoArchivoDatosReferencia()throws IdeamException;
    
   // public CmatControlCargueV2 getCmatControlCargueV2(Long idAutoridad) throws IdeamException;
    
    public CmatPuntosMonitoreoV2 getCmatPuntosMonitoreoV2 (Long idControlCargue) throws IdeamException;
    
}
