package co.gov.ideam.sirh.archivos.business;


import co.gov.ideam.sirh.archivos.model.AbstractValidator;
import co.gov.ideam.sirh.archivos.model.ColumnTO;
import co.gov.ideam.sirh.archivos.model.EnaValidator;
import co.gov.ideam.sirh.archivos.model.FuentesConverter;
import co.gov.ideam.sirh.archivos.model.FuentesValidator;
import co.gov.ideam.sirh.archivos.model.FuniasConstruccionesAdicionalesConverter;
import co.gov.ideam.sirh.archivos.model.FuniasConstruccionesAdicionalesValidator;
import co.gov.ideam.sirh.archivos.model.FuniasConstruccionesConverter;
import co.gov.ideam.sirh.archivos.model.FuniasConstruccionesValidator;
import co.gov.ideam.sirh.archivos.model.FuniasDiagnosticoConverter;
import co.gov.ideam.sirh.archivos.model.FuniasDiagnosticoDisposicionConverter;
import co.gov.ideam.sirh.archivos.model.FuniasDiagnosticoDisposicionValidator;
import co.gov.ideam.sirh.archivos.model.FuniasDiagnosticoFuentesConverter;
import co.gov.ideam.sirh.archivos.model.FuniasDiagnosticoFuentesValidator;
import co.gov.ideam.sirh.archivos.model.FuniasDiagnosticoResiduosConverter;
import co.gov.ideam.sirh.archivos.model.FuniasDiagnosticoResiduosValidator;
import co.gov.ideam.sirh.archivos.model.FuniasDiagnosticoValidator;
import co.gov.ideam.sirh.archivos.model.FuniasGeofisicaConverter;
import co.gov.ideam.sirh.archivos.model.FuniasGeofisicaValidator;
import co.gov.ideam.sirh.archivos.model.FuniasGeologiaConverter;
import co.gov.ideam.sirh.archivos.model.FuniasGeologiaValidator;
import co.gov.ideam.sirh.archivos.model.FuniasUnidadGeologicaConverter;
import co.gov.ideam.sirh.archivos.model.FuniasUnidadGeologicaValidator;
import co.gov.ideam.sirh.archivos.model.Masivo.Entidad.CmatControlCargueV2;
import co.gov.ideam.sirh.archivos.model.Masivo.Entidad.CmatPuntosMonitoreoV2;
import co.gov.ideam.sirh.archivos.model.MuestrasMedicionesConverter;
import co.gov.ideam.sirh.archivos.model.MuestrasMedicionesValidator;
import co.gov.ideam.sirh.archivos.model.PoblacionValidator;
import co.gov.ideam.sirh.archivos.model.PuntosMonitoreoConverter;
import co.gov.ideam.sirh.archivos.model.PuntosMonitoreoValidator;
import co.gov.ideam.sirh.archivos.model.RowTO;
import co.gov.ideam.sirh.archivos.model.TarifasAcueductoConverter;
import co.gov.ideam.sirh.archivos.model.TarifasAcueductoValidator;
import co.gov.ideam.sirh.archivos.model.TipoArchivoTO;
import co.gov.ideam.sirh.archivos.model.UsariosAguaCaptacionesNaturalConverter;
import co.gov.ideam.sirh.archivos.model.UsuariosAguaCaptacionesJuridicaConverter;
import co.gov.ideam.sirh.archivos.model.UsuariosAguaCaptacionesJuridicaValidator;
import co.gov.ideam.sirh.archivos.model.UsuariosAguaCaptacionesNaturalValidator;
import co.gov.ideam.sirh.archivos.model.UsuariosAguaCaptacionesServiciosConverter;
import co.gov.ideam.sirh.archivos.model.UsuariosAguaCaptacionesServiciosValidator;
import co.gov.ideam.sirh.archivos.model.UsuariosAguaPermisosJuridicaConverter;
import co.gov.ideam.sirh.archivos.model.UsuariosAguaPermisosJuridicaValidator;
import co.gov.ideam.sirh.archivos.model.UsuariosAguaPermisosNaturalConverter;
import co.gov.ideam.sirh.archivos.model.UsuariosAguaPermisosNaturalValidator;
import co.gov.ideam.sirh.archivos.model.UsuariosAguaPermisosServiciosConverter;
import co.gov.ideam.sirh.archivos.model.UsuariosAguaPermisosServiciosValidator;
import co.gov.ideam.sirh.calidad.model.NormaLimites;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.util.IdeamException;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;


@Stateless(name = "ArchivosEJB", mappedName = "AppSIRH-ArchivosEJB")
@Remote
public class ArchivosBean implements ArchivosEJB {
    
    private static List listaArchivos;
    @PersistenceContext(unitName = "SirhPU")
    private EntityManager em;
    private static List listaArchivosDatosReferencia;
    
    public ArchivosBean() {
    }
    
    
    public List getAllTipoArchivo()throws IdeamException{
        if(listaArchivos == null){
            listaArchivos = new ArrayList();

            TipoArchivoTO fuentes = new TipoArchivoTO("Fuentes", 
                                                       FuentesValidator.class,
                                                       FuentesConverter.class);
            
            TipoArchivoTO usuariosConcesionesNatural = new TipoArchivoTO("Usuarios, Predios, Concesiones, Captaciones y Usos Personas Naturales", 
                                                       UsuariosAguaCaptacionesNaturalValidator.class,
                                                       UsariosAguaCaptacionesNaturalConverter.class);

            TipoArchivoTO usuariosConcesionesJuridica = new TipoArchivoTO("Usuarios, Predios, Concesiones, Captaciones y Usos Personas Jurídicas", 
                                                       UsuariosAguaCaptacionesJuridicaValidator.class,
                                                       UsuariosAguaCaptacionesJuridicaConverter.class);

            TipoArchivoTO usuariosConcesionesServicios= new TipoArchivoTO("Usuarios, Predios, Concesiones, Captaciones y Usos Empresas de Servicios Públicos", 
                                                       UsuariosAguaCaptacionesServiciosValidator.class,
                                                       UsuariosAguaCaptacionesServiciosConverter.class);

            TipoArchivoTO usuariosPermisosNatural = new TipoArchivoTO("Usuarios, Predios, Permisos y Vertimientos Personas Naturales", 
                                                       UsuariosAguaPermisosNaturalValidator.class,
                                                       UsuariosAguaPermisosNaturalConverter.class);

            TipoArchivoTO usuariosPermisosJuridica = new TipoArchivoTO("Usuarios, Predios, Permisos y Vertimientos Personas Jurídicas", 
                                                       UsuariosAguaPermisosJuridicaValidator.class,
                                                       UsuariosAguaPermisosJuridicaConverter.class);

            TipoArchivoTO usuariosPermisosServicios = new TipoArchivoTO("Usuarios, Predios, Permisos y Vertimientos Empresas de Servicios Públicos", 
                                                       UsuariosAguaPermisosServiciosValidator.class,
                                                       UsuariosAguaPermisosServiciosConverter.class);

            TipoArchivoTO calidadPuntos = new TipoArchivoTO("Calidad-Puntos Monitoreo", 
                                                       PuntosMonitoreoValidator.class,
                                                       PuntosMonitoreoConverter.class);
            
            
            TipoArchivoTO calidadMuestras = new TipoArchivoTO("Calidad-Muestras,Mediciones", 
                                                       MuestrasMedicionesValidator.class,
                                                       MuestrasMedicionesConverter.class);

            TipoArchivoTO funiasGeologia = new TipoArchivoTO("Funias - Topografia y Geoforma", 
                                                       FuniasGeologiaValidator.class,
                                                       FuniasGeologiaConverter.class);
            
            TipoArchivoTO funiasGeologia1 = new TipoArchivoTO("Funias - Litologia", 
                                                       FuniasUnidadGeologicaValidator.class,
                                                       FuniasUnidadGeologicaConverter.class);
            
            TipoArchivoTO funiasCon = new TipoArchivoTO("Funias - Construccion", 
                                                       FuniasConstruccionesValidator.class,
                                                       FuniasConstruccionesConverter.class);
            
            TipoArchivoTO funiasCon1 = new TipoArchivoTO("Funias - Construcciones Adicionales", 
                                                       FuniasConstruccionesAdicionalesValidator.class,
                                                       FuniasConstruccionesAdicionalesConverter.class);
            
            TipoArchivoTO funiasGeofisica = new TipoArchivoTO("Funias - Geofisica", 
                                                       FuniasGeofisicaValidator.class,
                                                       FuniasGeofisicaConverter.class);
            
            TipoArchivoTO funiasDia1 = new TipoArchivoTO("Funias - Diagnostico Sanitario I", 
                                                       FuniasDiagnosticoValidator.class,
                                                       FuniasDiagnosticoConverter.class);
            
            TipoArchivoTO funiasDia2 = new TipoArchivoTO("Funias - Diagnostico Sanitario II", 
                                                       FuniasDiagnosticoFuentesValidator.class,
                                                       FuniasDiagnosticoFuentesConverter.class);
            
            TipoArchivoTO funiasDia3 = new TipoArchivoTO("Funias - Diagnostico Sanitario III", 
                                                       FuniasDiagnosticoResiduosValidator.class,
                                                       FuniasDiagnosticoResiduosConverter.class);
            
            TipoArchivoTO funiasDia4 = new TipoArchivoTO("Funias - Diagnostico Sanitario IV", 
                                                       FuniasDiagnosticoDisposicionValidator.class,
                                                       FuniasDiagnosticoDisposicionConverter.class);

            listaArchivos.add(fuentes);
            listaArchivos.add(usuariosConcesionesNatural);
            listaArchivos.add(usuariosConcesionesJuridica);
            listaArchivos.add(usuariosConcesionesServicios);
            listaArchivos.add(usuariosPermisosNatural);
            listaArchivos.add(usuariosPermisosJuridica);
            listaArchivos.add(usuariosPermisosServicios);
            listaArchivos.add(calidadPuntos);
            listaArchivos.add(calidadMuestras);
            listaArchivos.add(funiasGeologia);
            listaArchivos.add(funiasGeologia1);
            listaArchivos.add(funiasCon);
            listaArchivos.add(funiasCon1);
            listaArchivos.add(funiasGeofisica);
            listaArchivos.add(funiasDia1);
            listaArchivos.add(funiasDia2);
            listaArchivos.add(funiasDia3);
            listaArchivos.add(funiasDia4);
        }
        return listaArchivos;
    }
    
    public List getAllTipoArchivoDatosReferencia()throws IdeamException{
        if(listaArchivosDatosReferencia == null){
            listaArchivosDatosReferencia = new ArrayList();

            TipoArchivoTO ena = new TipoArchivoTO("ENA", 
                                                       EnaValidator.class,
                                                       FuentesConverter.class);
            
            TipoArchivoTO demanda = new TipoArchivoTO("Demanda tarifas acueducto", 
                                                       TarifasAcueductoValidator.class,
                                                       TarifasAcueductoConverter.class);
            
            TipoArchivoTO poblacion = new TipoArchivoTO("Población por municipio", 
                                                       PoblacionValidator.class,
                                                       FuentesConverter.class);

            listaArchivosDatosReferencia.add(ena);
            listaArchivosDatosReferencia.add(demanda);
            listaArchivosDatosReferencia.add(poblacion);
            
        }
        return listaArchivosDatosReferencia;
    }
    
    /**
     * Valida la informacion del archivo Excel
     * @param tipoArchivo
     * @return
     */
    public List<RowTO> validateSheet(TipoArchivoTO tipoArchivo, String sheetName, byte[] archivo, Autoridades autoridad)throws IdeamException{                
        List<RowTO> contenido = new ArrayList<RowTO>();
        try {
            InputStream is = new ByteArrayInputStream(archivo);
            HSSFWorkbook book = new HSSFWorkbook(is);
            HSSFSheet sheet = book.getSheet(sheetName);
            int totalFilas = sheet.getLastRowNum();
            AbstractValidator validator = 
                tipoArchivo.getValidatorInstance();
            for (int fila=0; fila<totalFilas; fila++){
                HSSFRow rowExcel = sheet.getRow(fila);
                if(rowExcel!=null){
                    RowTO row = this.processRow(validator, rowExcel, autoridad);
                    row.setIndice(fila + 1);
                    contenido.add(row);
                }
            }    
        } catch (Exception e) {
            throw new IdeamException("Error cargando archivo Excel " + 
                                     e.getMessage());
        } 
        return contenido;
    }
    
    public List<String> getSheets(byte[] archivo)throws IdeamException{
        List lista = new ArrayList();
        try {
            InputStream is = new ByteArrayInputStream(archivo);
            HSSFWorkbook book = new HSSFWorkbook(is);            
            int totalSheets = book.getNumberOfSheets();
            if(totalSheets <=0){
                throw new IdeamException("No existen hojas para procesar en este archivo");
            }
            for(int i=0; i<totalSheets; i++){
                String nombre = book.getSheetName(i);
                lista.add(nombre);
            }
            return lista;
        } catch (IOException e) {
            throw new IdeamException("Error cargando archivo Excel " + 
                                     e.getMessage());
        }        
    }
    
    private RowTO processRow(AbstractValidator validator,  
                            HSSFRow row,
                             Autoridades autoridad)throws IdeamException{
        RowTO fila = new RowTO();
        List<ColumnTO> columnas = validator.getColumns();
        Iterator it = columnas.iterator();
        while(it.hasNext()){
            ColumnTO col = (ColumnTO)it.next();
            Object value = validator.getValue(row, col);
            fila.addCell(col, value, autoridad);
        }
        return fila;
    }
    
    
    /**
     * Valida la informacion del archivo Excel
     * @param tipoArchivo
     * @return
     */
    public List<RowTO> validateSheetClient(TipoArchivoTO tipoArchivo, 
                                           String sheetName, File archivo, 
                                           Autoridades autoridad)throws IdeamException{                
        List<RowTO> contenido = new ArrayList<RowTO>();
        try {
            FileInputStream fileInputStream = new FileInputStream(archivo);
            HSSFWorkbook book = new HSSFWorkbook(fileInputStream);
            HSSFSheet sheet = book.getSheet(sheetName);
            int totalFilas = sheet.getLastRowNum();
            AbstractValidator validator = tipoArchivo.getValidatorInstance();
            for (int fila=0; fila<totalFilas; fila++){
                HSSFRow rowExcel = sheet.getRow(fila);
                if(rowExcel!=null){
                    RowTO row = this.processRow(validator, rowExcel, autoridad);
                    row.setIndice(fila + 1);
                    contenido.add(row);
                }
            }    
        } catch (Exception e) {
            throw new IdeamException("Error cargando archivo Excel " + 
                                     e.getMessage());
        } 
        return contenido;
    }
    
    private RowTO processRow(AbstractValidator validator,  
                            XSSFRow row,
                             Autoridades autoridad)throws IdeamException{
        RowTO fila = new RowTO();
        List<ColumnTO> columnas = validator.getColumns();
        Iterator it = columnas.iterator();
        while(it.hasNext()){
            ColumnTO col = (ColumnTO)it.next();
            Object value = validator.getValue(row, col);
            fila.addCell(col, value, autoridad);
        }
        return fila;
    }
    
    /*
     * 
     * */
   /* public CmatControlCargueV2 getCmatControlCargueV2(Long idAutoridad) throws IdeamException {

        try {
            Query query = em.createNamedQuery("CmatControlCargueV2.findByIdAutoridadV2");
            query.setParameter("idAutoridad", idAutoridad);
            CmatControlCargueV2 queryResult = (CmatControlCargueV2)query.getSingleResult();
            return queryResult;
        } catch (NoResultException e) {
            return null;
        }
    }*/
    
    public CmatPuntosMonitoreoV2 getCmatPuntosMonitoreoV2 (Long idControlCargue) throws IdeamException{
        try{
            Query query = em.createNamedQuery("CmatFuniasConstruccionV2.findByIdControlCarguePtosMonV2");
            query.setParameter("idControlCargue",idControlCargue);
            CmatPuntosMonitoreoV2 queyResult = (CmatPuntosMonitoreoV2)query.getSingleResult();
            return queyResult;
            
        }catch (NoResultException e) {
            return null;
        }
    }
}
