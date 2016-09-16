package co.gov.ideam.sirh.dataimport.util;

import co.gov.ideam.sirh.dataimport.client.generate.FntvFuentesTramos;

import co.gov.ideam.sirh.dataimport.client.generate.MuestrasMediciones;
import co.gov.ideam.sirh.dataimport.client.generate.PuntosMonitoreoCalidad;
import co.gov.ideam.sirh.dataimport.client.generate.RegUserJuridicoConcesion;
import co.gov.ideam.sirh.dataimport.client.generate.RegUserJuridicoVertimiento;
import co.gov.ideam.sirh.dataimport.client.generate.RegUserNaturalConcesion;
import co.gov.ideam.sirh.dataimport.client.generate.RegUserNaturalVertimiento;
import co.gov.ideam.sirh.util.ConstantesImportacion;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.InputStream;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FileUtil {
    public static String RUTA_ARCHIVO;
    public static final String NOMBRE_ARCHIVO = "Import.xls";

    public FileUtil() {
        
    }
    
    public FileUtil(String ruta) {
        RUTA_ARCHIVO = ruta;
    }

    private static File generateXls() throws FileNotFoundException,
                                             IOException {
        File file = null;
        try {
            System.out.println("===============GENERAR ARCHIVO: "+RUTA_ARCHIVO + NOMBRE_ARCHIVO);
            file = new File(RUTA_ARCHIVO + NOMBRE_ARCHIVO);
            if (file.exists()) {
                file.delete();
                file.createNewFile();
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERROR en FileUTil, mensaje: " +
                               e.fillInStackTrace());
        } catch (IOException e) {
            System.out.println("ERROR en FileUTil, mensaje: " +
                               e.fillInStackTrace());
        }
        return file;
    }

    public static File getFile() throws FileNotFoundException {
        File file = null;
        try {
            file = new File(RUTA_ARCHIVO + NOMBRE_ARCHIVO);
        } catch (Exception e) {
            System.out.println("ERROR en FileUTil, mensaje: " +
                               e.fillInStackTrace());
        }
        return file;
    }
    
    @Deprecated
    public static byte[] getFileBytes() throws FileNotFoundException {
        byte[] bytes = null;
        try {
            File file = new File(RUTA_ARCHIVO + NOMBRE_ARCHIVO);
            /*
             * //forma 1
            bytes = new byte[(int)(file.length())];
            */
            
            /*
             * forma 2
            FileInputStream fis = new FileInputStream( RUTA_ARCHIVO + NOMBRE_ARCHIVO );
            bytes = new byte[ fis.available() ];
            */
        } catch (Exception e) {
            System.out.println("ERROR en FileUTil, mensaje: " +
                               e.fillInStackTrace());
        }
        return bytes;
    }
    
    @Deprecated
    public static void getWorkBook() throws FileNotFoundException {
        byte[] wbook = null;
        try {
            FileInputStream fis = new FileInputStream( RUTA_ARCHIVO + NOMBRE_ARCHIVO );
            HSSFWorkbook book = new HSSFWorkbook(fis);
            wbook = book.getBytes();            
        } catch (FileNotFoundException e) {
            System.out.println("ERROR en FileUTil, mensaje: " +
                               e.fillInStackTrace());
        }catch (Exception e) {
            System.out.println("ERROR en FileUTil, mensaje: " +
                               e.fillInStackTrace());
        }
    }
    
    public static void loadDataFuentesXls(List<FntvFuentesTramos> datos) 
            throws FileNotFoundException, IOException {
        try {
            File file = FileUtil.generateXls();
            FileOutputStream archivo = new FileOutputStream(file); 
            
            HSSFWorkbook libro = new HSSFWorkbook();
            HSSFSheet hoja = libro.createSheet(ConstantesImportacion.HOJA_FUENTES);

            short contFilas = (short)0;
            for (FntvFuentesTramos data : datos) {
                
                HSSFRow fila = hoja.createRow(contFilas);
/*                             
                HSSFCell celda0 = fila.createCell((short)0);
                celda0.setCellValue(data.getIdfuente());

                HSSFCell celda1 = fila.createCell((short)1);
                celda1.setCellValue(data.getTipofuente());
                
                HSSFCell celda2 = fila.createCell((short)2);
                celda2.setCellValue(data.getNombrefuente());
                
                HSSFCell celda3 = fila.createCell((short)3);
                celda3.setCellValue(data.getExistebd());
                
                HSSFCell celda4 = fila.createCell((short)4);
                celda4.setCellValue(data.getNumcarfuente());

                HSSFCell celda5 = fila.createCell((short)5);
                celda5.setCellValue(data.getDescripcionfuente());

                HSSFCell celda6 = fila.createCell((short)6);
                celda6.setCellValue(data.getNumcartramo());

                HSSFCell celda7 = fila.createCell((short)7);
                celda7.setCellValue(data.getNombretramo());

                HSSFCell celda8 = fila.createCell((short)8);
                celda8.setCellValue(data.getDescripciontramo());

                HSSFCell celda9 = fila.createCell((short)9);
                celda9.setCellValue(data.getLongitud().longValue());

                HSSFCell celda10 = fila.createCell((short)10);
                celda10.setCellValue(data.getTipoflujo());

                HSSFCell celda11 = fila.createCell((short)11);
                celda11.setCellValue(data.getArea());

                HSSFCell celda12 = fila.createCell((short)12);
                celda12.setCellValue(data.getZona());

                HSSFCell celda13 = fila.createCell((short)13);
                celda13.setCellValue(data.getSubzona());

                HSSFCell celda14 = fila.createCell((short)14);
                celda14.setCellValue(data.getCuenca());

                HSSFCell celda15 = fila.createCell((short)15);
                celda15.setCellValue(data.getCodigocuenca());
                
                HSSFCell celda16 = fila.createCell((short)16);
                celda16.setCellValue(data.getUsos());

                HSSFCell celda17 = fila.createCell((short)17);
                celda17.setCellValue(data.getSistemareferenciapi());

                HSSFCell celda18 = fila.createCell((short)18);
                celda18.setCellValue(data.getGradoslatpi().longValue());

                HSSFCell celda19 = fila.createCell((short)19);
                celda19.setCellValue(data.getMinutoslatpi().longValue());

                HSSFCell celda20 = fila.createCell((short)20);
                celda20.setCellValue(data.getSegundolatpi().longValue());

                HSSFCell celda21 = fila.createCell((short)21);
                celda21.setCellValue(data.getGradoslonpi().longValue());

                HSSFCell celda22 = fila.createCell((short)22);
                celda22.setCellValue(data.getMinutoslonpi().longValue());

                HSSFCell celda23 = fila.createCell((short)23);
                celda23.setCellValue(data.getSegundolonpi().longValue());

                HSSFCell celda24 = fila.createCell((short)24);
                celda24.setCellValue(data.getAltitudpi().longValue());

                HSSFCell celda25 = fila.createCell((short)25);
                celda25.setCellValue(data.getSistemareferenciapf());

                HSSFCell celda26 = fila.createCell((short)26);
                celda26.setCellValue(data.getGradoslatpf().longValue());

                HSSFCell celda27 = fila.createCell((short)27);
                celda27.setCellValue(data.getMinutoslatpf().longValue());

                HSSFCell celda28 = fila.createCell((short)28);
                celda28.setCellValue(data.getSegundolatpf().longValue());

                HSSFCell celda29 = fila.createCell((short)29);
                celda29.setCellValue(data.getGradoslonpf().longValue());

                HSSFCell celda30 = fila.createCell((short)30);
                celda30.setCellValue(data.getMinutoslonpf().longValue());

                HSSFCell celda31 = fila.createCell((short)31);
                celda31.setCellValue(data.getSegundolonpf().longValue());

                HSSFCell celda32 = fila.createCell((short)32);
                celda32.setCellValue(data.getAltitudpf().longValue());
 */
                HSSFCell celda0  = fila.createCell((short)0);
                celda0.setCellValue((data.getIdfuente()!=null)?data.getIdfuente(): " ") ;

                HSSFCell celda1  = fila.createCell((short)1);
                celda1.setCellValue((data.getTipofuente()!=null)?data.getTipofuente(): " ") ;

                HSSFCell celda2  = fila.createCell((short)2);
                celda2.setCellValue((data.getNombrefuente()!=null)?data.getNombrefuente(): " ") ;

                HSSFCell celda3  = fila.createCell((short)3);
                celda3.setCellValue((data.getExistebd()!=null)?data.getExistebd(): " ") ;

                HSSFCell celda4  = fila.createCell((short)4);
                celda4.setCellValue((data.getNumcarfuente()!=null)?data.getNumcarfuente(): " ") ;

                HSSFCell celda5  = fila.createCell((short)5);
                celda5.setCellValue((data.getDescripcionfuente()!=null)?data.getDescripcionfuente(): " ") ;

                HSSFCell celda6  = fila.createCell((short)6);
                celda6.setCellValue((data.getNumcartramo()!=null)?data.getNumcartramo(): " ") ;

                HSSFCell celda7  = fila.createCell((short)7);
                celda7.setCellValue((data.getNombretramo()!=null)?data.getNombretramo(): " ") ;

                HSSFCell celda8  = fila.createCell((short)8);
                celda8.setCellValue((data.getDescripciontramo()!=null)?data.getDescripciontramo(): " ") ;

                HSSFCell celda9  = fila.createCell((short)9);
                celda9.setCellValue((data.getLongitud()!=null)?data.getLongitud(): 0.0) ;

                HSSFCell celda10  = fila.createCell((short)10);
                celda10.setCellValue((data.getTipoflujo()!=null)?data.getTipoflujo(): " ") ;

                HSSFCell celda11  = fila.createCell((short)11);
                celda11.setCellValue((data.getArea()!=null)?data.getArea(): " ") ;

                HSSFCell celda12  = fila.createCell((short)12);
                celda12.setCellValue((data.getZona()!=null)?data.getZona(): " ") ;

                HSSFCell celda13  = fila.createCell((short)13);
                celda13.setCellValue((data.getSubzona()!=null)?data.getSubzona(): " ") ;

                HSSFCell celda14  = fila.createCell((short)14);
                celda14.setCellValue((data.getCuenca()!=null)?data.getCuenca(): " ") ;

                HSSFCell celda15  = fila.createCell((short)15);
                celda15.setCellValue((data.getCodigocuenca()!=null)?data.getCodigocuenca(): " ") ;
/*
                HSSFCell celda16  = fila.createCell((short)16);
                celda16.setCellValue((data.getUsos()!=null)?data.getUsos(): " ") ;
*/
                HSSFCell celda17  = fila.createCell((short)17);
                celda17.setCellValue((data.getSistemareferenciapi()!=null)?data.getSistemareferenciapi(): " ") ;

                HSSFCell celda18  = fila.createCell((short)18);
                celda18.setCellValue((data.getGradoslatpi ()!=null)?data.getGradoslatpi (): 0) ;

                HSSFCell celda19  = fila.createCell((short)19);
                celda19.setCellValue((data.getMinutoslatpi ()!=null)?data.getMinutoslatpi (): 0) ;

                HSSFCell celda20  = fila.createCell((short)20);
                celda20.setCellValue((data.getSegundolatpi()!=null)?data.getSegundolatpi(): 0.0) ;

                HSSFCell celda21  = fila.createCell((short)21);
                celda21.setCellValue((data.getGradoslonpi ()!=null)?data.getGradoslonpi (): 0) ;

                HSSFCell celda22  = fila.createCell((short)22);
                celda22.setCellValue((data.getMinutoslonpi ()!=null)?data.getMinutoslonpi (): 0) ;

                HSSFCell celda23  = fila.createCell((short)23);
                celda23.setCellValue((data.getSegundolonpi()!=null)?data.getSegundolonpi(): 0.0) ;

                HSSFCell celda24  = fila.createCell((short)24);
                celda24.setCellValue((data.getAltitudpi()!=null)?data.getAltitudpi(): 0.0) ;

                HSSFCell celda25  = fila.createCell((short)25);
                celda25.setCellValue((data.getSistemareferenciapf()!=null)?data.getSistemareferenciapf(): " ") ;

                HSSFCell celda26  = fila.createCell((short)26);
                celda26.setCellValue((data.getGradoslatpf ()!=null)?data.getGradoslatpf (): 0) ;

                HSSFCell celda27  = fila.createCell((short)27);
                celda27.setCellValue((data.getMinutoslatpf ()!=null)?data.getMinutoslatpf (): 0) ;

                HSSFCell celda28  = fila.createCell((short)28);
                celda28.setCellValue((data.getSegundolatpf()!=null)?data.getSegundolatpf(): 0.0) ;

                HSSFCell celda29  = fila.createCell((short)29);
                celda29.setCellValue((data.getGradoslonpf ()!=null)?data.getGradoslonpf (): 0) ;

                HSSFCell celda30  = fila.createCell((short)30);
                celda30.setCellValue((data.getMinutoslonpf ()!=null)?data.getMinutoslonpf (): 0) ;

                HSSFCell celda31  = fila.createCell((short)31);
                celda31.setCellValue((data.getSegundolonpf()!=null)?data.getSegundolonpf(): 0.0) ;

                HSSFCell celda32  = fila.createCell((short)32);
                celda32.setCellValue((data.getAltitudpf()!=null)?data.getAltitudpf(): 0.0) ;
            
                contFilas++;
            }

            libro.write(archivo);
            archivo.close();
        } catch (FileNotFoundException e) {
            System.out.println("ERROR en FileUTil, mensaje: " +
                               e.fillInStackTrace());
        } catch (IOException e) {
            System.out.println("ERROR en FileUTil, mensaje: " +
                               e.fillInStackTrace());
        }
    }      
    
    public static void loadDataUserNaturalCaptacionesXls(List<RegUserNaturalConcesion> datos) 
            throws FileNotFoundException, IOException {
System.out.println("DATOS LISTA: "+datos.size());    
        try {
            
 System.out.println ("INICIO TRY ");            
            
            File file = FileUtil.generateXls();
            FileOutputStream archivo = new FileOutputStream(file); 
            
 System.out.println ("FIN CREAR ARCHIVO ");               
            
            HSSFWorkbook libro = new HSSFWorkbook();
            HSSFSheet hoja = libro.createSheet(ConstantesImportacion.HOJA_CAPTACIONES_NATURALES);
    System.out.println ("HOJA ");   
            short contFilas = (short)0;
   System.out.println ("INICIO FOR ");   
            for (RegUserNaturalConcesion data : datos) {

  System.out.println ("REG: "+contFilas);
    try{
                HSSFRow fila = hoja.createRow(contFilas);
                    HSSFCell celda0  = fila.createCell((short)0);
                    celda0.setCellValue((data.getTipopersona()!=null)?data.getTipopersona(): "  ") ;

                    HSSFCell celda1  = fila.createCell((short)1);
                    celda1.setCellValue((data.getNombre()!=null)?data.getNombre(): "  ") ;

                    HSSFCell celda2  = fila.createCell((short)2);
                    celda2.setCellValue((data.getApellido()!=null)?data.getApellido(): "  ") ;

                    HSSFCell celda3  = fila.createCell((short)3);
                    celda3.setCellValue((data.getTipodocumento()!=null)?data.getTipodocumento(): "  ") ;

                    HSSFCell celda4  = fila.createCell((short)4);
                    celda4.setCellValue((data.getNumdocumento()!=null)?data.getNumdocumento(): "  ") ;

                    HSSFCell celda5  = fila.createCell((short)5);
                    celda5.setCellValue((data.getDeptocorrespondencia()!=null)?data.getDeptocorrespondencia(): "  ") ;

                    HSSFCell celda6  = fila.createCell((short)6);
                    celda6.setCellValue((data.getMunicipiocorrespondencia()!=null)?data.getMunicipiocorrespondencia(): "  ") ;

                    HSSFCell celda7  = fila.createCell((short)7);
                    celda7.setCellValue((data.getDireccioncorrespondencia()!=null)?data.getDireccioncorrespondencia(): "  ") ;

                    HSSFCell celda8  = fila.createCell((short)8);
                    celda8.setCellValue((data.getEmail()!=null)?data.getEmail(): "  ") ;

                    HSSFCell celda9  = fila.createCell((short)9);
                    celda9.setCellValue((data.getTelefono()!=null)?data.getTelefono(): "  ") ;

                    HSSFCell celda10  = fila.createCell((short)10);
                    celda10.setCellValue((data.getFax()!=null)?data.getFax(): "  ") ;

                    HSSFCell celda11  = fila.createCell((short)11);
                    celda11.setCellValue((data.getNombrepredio()!=null)?data.getNombrepredio(): "  ") ;

                    HSSFCell celda12  = fila.createCell((short)12);
                    celda12.setCellValue((data.getTipotenencia()!=null)?data.getTipotenencia(): "  ") ;

                    HSSFCell celda13  = fila.createCell((short)13);
                    celda13.setCellValue((data.getDepartamento()!=null)?data.getDepartamento(): "  ") ;

                    HSSFCell celda14  = fila.createCell((short)14);
                    celda14.setCellValue((data.getMunicipio()!=null)?data.getMunicipio(): "  ") ;

                    HSSFCell celda15  = fila.createCell((short)15);
                    celda15.setCellValue((data.getTipocentropoblado()!=null)?data.getTipocentropoblado(): "  ") ;

                    HSSFCell celda16  = fila.createCell((short)16);
                    celda16.setCellValue((data.getCentropoblado()!=null)?data.getCentropoblado(): "  ") ;

                    HSSFCell celda17  = fila.createCell((short)17);
                    celda17.setCellValue((data.getCedulacatastral()!=null)?data.getCedulacatastral(): "  ") ;

                    HSSFCell celda18  = fila.createCell((short)18);
                    celda18.setCellValue((data.getMatriculainmobiliaria()!=null)?data.getMatriculainmobiliaria(): "  ") ;

                    HSSFCell celda19  = fila.createCell((short)19);
                    celda19.setCellValue((data.getAreatotal()!=null)?data.getAreatotal(): 0.0) ;

                    HSSFCell celda20  = fila.createCell((short)20);
                    celda20.setCellValue((data.getDireccionpredio()!=null)?data.getDireccionpredio(): "  ") ;

                    HSSFCell celda21  = fila.createCell((short)21);
                    celda21.setCellValue((data.getClasificacionsuelo()!=null)?data.getClasificacionsuelo(): "  ") ;

                    HSSFCell celda22  = fila.createCell((short)22);
                    celda22.setCellValue((data.getSistemarefpredio()!=null)?data.getSistemarefpredio(): "  ") ;

                    HSSFCell celda23  = fila.createCell((short)23);
                    celda23.setCellValue((data.getGradoslatpredio ()!=null)?data.getGradoslatpredio (): 0) ;

                    HSSFCell celda24  = fila.createCell((short)24);
                    celda24.setCellValue((data.getMinutoslatpredio ()!=null)?data.getMinutoslatpredio (): 0) ;

                    HSSFCell celda25  = fila.createCell((short)25);
                    celda25.setCellValue((data.getSegundoslatpredio()!=null)?data.getSegundoslatpredio(): 0.0) ;

                    HSSFCell celda26  = fila.createCell((short)26);
                    celda26.setCellValue((data.getGradoslonpredio ()!=null)?data.getGradoslonpredio (): 0) ;

                    HSSFCell celda27  = fila.createCell((short)27);
                    celda27.setCellValue((data.getMinutoslonpredio ()!=null)?data.getMinutoslonpredio (): 0) ;

                    HSSFCell celda28  = fila.createCell((short)28);
                    celda28.setCellValue((data.getSegundoslonpredio()!=null)?data.getSegundoslonpredio(): 0.0) ;

                    HSSFCell celda29  = fila.createCell((short)29);
                    celda29.setCellValue((data.getAltitudpredio()!=null)?data.getAltitudpredio(): 0.0) ;

                    HSSFCell celda30  = fila.createCell((short)30);
                    celda30.setCellValue((data.getDescripcionsitio()!=null)?data.getDescripcionsitio(): "  ") ;

                    HSSFCell celda31  = fila.createCell((short)31);
                    celda31.setCellValue((data.getNumexpediente()!=null)?data.getNumexpediente(): "  ") ;

                    HSSFCell celda32  = fila.createCell((short)32);
                    celda32.setCellValue((data.getNumresolcaudal()!=null)?data.getNumresolcaudal(): "  ") ;

                    HSSFCell celda33  = fila.createCell((short)33);
                    celda33.setCellValue((data.getFechaexpedicion ()!=null)?data.getFechaexpedicion (): "  ") ;

                    HSSFCell celda34  = fila.createCell((short)34);
                    celda34.setCellValue((data.getFechanotificacion()!=null)?data.getFechanotificacion(): "  ") ;

                    HSSFCell celda35  = fila.createCell((short)35);
                    celda35.setCellValue((data.getCaudalconcesionado ()!=null)?data.getCaudalconcesionado (): 0.0) ;

                    HSSFCell celda36  = fila.createCell((short)36);
                    celda36.setCellValue((data.getNumresolplanos()!=null)?data.getNumresolplanos(): "  ") ;

                    HSSFCell celda37  = fila.createCell((short)37);
                    celda37.setCellValue((data.getFechaexpresolplanos()!=null)?data.getFechaexpresolplanos(): "  ") ;

                    HSSFCell celda38  = fila.createCell((short)38);
                    celda38.setCellValue((data.getFechanotificacionplanos()!=null)?data.getFechanotificacionplanos(): "  ") ;

                    HSSFCell celda39  = fila.createCell((short)39);
                    celda39.setCellValue((data.getNumresolucionobra()!=null)?data.getNumresolucionobra(): "  ") ;

                    HSSFCell celda40  = fila.createCell((short)40);
                    celda40.setCellValue((data.getFechaexpedresolobra()!=null)?data.getFechaexpedresolobra(): "  ") ;

                    HSSFCell celda41  = fila.createCell((short)41);
                    celda41.setCellValue((data.getFechanotifobra()!=null)?data.getFechanotifobra(): "  ") ;

                    HSSFCell celda42  = fila.createCell((short)42);
                    celda42.setCellValue((data.getVigenciadesde()!=null)?data.getVigenciadesde(): "  ") ;

                    HSSFCell celda43  = fila.createCell((short)43);
                    celda43.setCellValue((data.getVigenciahasta()!=null)?data.getVigenciahasta(): "  ") ;

                    HSSFCell celda44  = fila.createCell((short)44);
                    celda44.setCellValue((data.getTipofuentecap()!=null)?data.getTipofuentecap(): "  ") ;

                    HSSFCell celda45  = fila.createCell((short)45);
                    celda45.setCellValue((data.getArea()!=null)?data.getArea(): "  ") ;

                    HSSFCell celda46  = fila.createCell((short)46);
                    celda46.setCellValue((data.getZona()!=null)?data.getZona(): "  ") ;

                    HSSFCell celda47  = fila.createCell((short)47);
                    celda47.setCellValue((data.getSubzona()!=null)?data.getSubzona(): "  ") ;

                    HSSFCell celda48  = fila.createCell((short)48);
                    celda48.setCellValue((data.getTipofuente()!=null)?data.getTipofuente(): "  ") ;

                    HSSFCell celda49  = fila.createCell((short)49);
                    celda49.setCellValue((data.getFuente()!=null)?data.getFuente(): "  ") ;

                    HSSFCell celda50  = fila.createCell((short)50);
                    celda50.setCellValue((data.getTramo()!=null)?data.getTramo(): "  ") ;

                    HSSFCell celda51  = fila.createCell((short)51);
                    celda51.setCellValue((data.getIdpunto()!=null)?data.getIdpunto(): 0) ;

                    HSSFCell celda52  = fila.createCell((short)52);
                    celda52.setCellValue((data.getProvinciahidro()!=null)?data.getProvinciahidro(): "  ") ;

                    HSSFCell celda53  = fila.createCell((short)53);
                    celda53.setCellValue((data.getUnidadhidro()!=null)?data.getUnidadhidro(): "  ") ;

                    HSSFCell celda54  = fila.createCell((short)54);
                    celda54.setCellValue((data.getAduccion()!=null)?data.getAduccion(): "  ") ;

                    HSSFCell celda55  = fila.createCell((short)55);
                    celda55.setCellValue((data.getDesarenador()!=null)?data.getDesarenador(): "  ") ;

                    HSSFCell celda56  = fila.createCell((short)56);
                    celda56.setCellValue((data.getPtap()!=null)?data.getPtap(): "  ") ;

                    HSSFCell celda57  = fila.createCell((short)57);
                    celda57.setCellValue((data.getReddistribucion()!=null)?data.getReddistribucion(): "  ") ;

                    HSSFCell celda58  = fila.createCell((short)58);
                    celda58.setCellValue((data.getTanque()!=null)?data.getTanque(): "  ") ;

                    HSSFCell celda59  = fila.createCell((short)59);
                    celda59.setCellValue((data.getTipocaptacion()!=null)?data.getTipocaptacion(): "  ") ;

                    HSSFCell celda60  = fila.createCell((short)60);
                    celda60.setCellValue((data.getOfertatotal()!=null)?data.getOfertatotal(): 0.0) ;

                    HSSFCell celda61  = fila.createCell((short)61);
                    celda61.setCellValue((data.getAreacaptacion()!=null)?data.getAreacaptacion(): 0) ;

                    HSSFCell celda62  = fila.createCell((short)62);
                    celda62.setCellValue((data.getOfertadisponible()!=null)?data.getOfertadisponible(): 0.0) ;

                    HSSFCell celda63  = fila.createCell((short)63);
                    celda63.setCellValue((data.getMacromedicion()!=null)?data.getMacromedicion(): "  ") ;

                    HSSFCell celda64  = fila.createCell((short)64);
                    celda64.setCellValue((data.getEstadocapacion()!=null)?data.getEstadocapacion(): "  ") ;

                    HSSFCell celda65  = fila.createCell((short)65);
                    celda65.setCellValue((data.getCaudaldiseno()!=null)?data.getCaudaldiseno(): 0.0) ;

                    HSSFCell celda66  = fila.createCell((short)66);
                    celda66.setCellValue((data.getContinuidadservicio()!=null)?data.getContinuidadservicio(): "  ") ;

                    HSSFCell celda67  = fila.createCell((short)67);
                    celda67.setCellValue((data.getTieneservidumbre()!=null)?data.getTieneservidumbre(): "  ") ;

                    HSSFCell celda68  = fila.createCell((short)68);
                    celda68.setCellValue((data.getSistemarefcapt()!=null)?data.getSistemarefcapt(): "  ") ;

                    HSSFCell celda69  = fila.createCell((short)69);
                    celda69.setCellValue((data.getGradoslatcapt()!=null)?data.getGradoslatcapt(): 0) ;

                    HSSFCell celda70  = fila.createCell((short)70);
                    celda70.setCellValue((data.getMinutoslatcapt()!=null)?data.getMinutoslatcapt(): 0) ;

                    HSSFCell celda71  = fila.createCell((short)71);
                    celda71.setCellValue((data.getSegundoslatcapt()!=null)?data.getSegundoslatcapt(): 0.0) ;

                    HSSFCell celda72  = fila.createCell((short)72);
                    celda72.setCellValue((data.getGradosloncapt()!=null)?data.getGradosloncapt(): 0) ;

                    HSSFCell celda73  = fila.createCell((short)73);
                    celda73.setCellValue((data.getMinutosloncapt()!=null)?data.getMinutosloncapt(): 0) ;

                    HSSFCell celda74  = fila.createCell((short)74);
                    celda74.setCellValue((data.getSegundosloncapt()!=null)?data.getSegundosloncapt(): 0.0) ;

                    HSSFCell celda75  = fila.createCell((short)75);
                    celda75.setCellValue((data.getAltitudcapt()!=null)?data.getAltitudcapt(): 0.0) ;

                    HSSFCell celda76  = fila.createCell((short)76);
                    celda76.setCellValue((data.getObscaptacion()!=null)?data.getObscaptacion(): "  ") ;

                    HSSFCell celda77  = fila.createCell((short)77);
                    celda77.setCellValue((data.getCaudalabast()!=null)?data.getCaudalabast(): 0.0) ;

                    HSSFCell celda78  = fila.createCell((short)78);
                    celda78.setCellValue((data.getNumpersonaspermanentes()!=null)?data.getNumpersonaspermanentes(): 0) ;

                    HSSFCell celda79  = fila.createCell((short)79);
                    celda79.setCellValue((data.getNumpersonastransitorias()!=null)?data.getNumpersonastransitorias(): 0) ;

                    HSSFCell celda80  = fila.createCell((short)80);
                    celda80.setCellValue((data.getAprovechamiento()!=null)?data.getAprovechamiento(): 0.0) ;

                    HSSFCell celda81  = fila.createCell((short)81);
                    celda81.setCellValue((data.getTipoanimalabrev()!=null)?data.getTipoanimalabrev(): "  ") ;

                    HSSFCell celda82  = fila.createCell((short)82);
                    celda82.setCellValue((data.getCaudalabrev()!=null)?data.getCaudalabrev(): 0.0) ;

                    HSSFCell celda83  = fila.createCell((short)83);
                    celda83.setCellValue((data.getNumanimalesabrev()!=null)?data.getNumanimalesabrev(): 0) ;

                    HSSFCell celda84  = fila.createCell((short)84);
                    celda84.setCellValue((data.getTipoanimalpesca()!=null)?data.getTipoanimalpesca(): "  ") ;

                    HSSFCell celda85  = fila.createCell((short)85);
                    celda85.setCellValue((data.getCaudalpesca()!=null)?data.getCaudalpesca(): 0.0) ;

                    HSSFCell celda86  = fila.createCell((short)86);
                    celda86.setCellValue((data.getNumanimalespesca()!=null)?data.getNumanimalespesca(): 0) ;

                    HSSFCell celda87  = fila.createCell((short)87);
                    celda87.setCellValue((data.getTipocultivo()!=null)?data.getTipocultivo(): "  ") ;

                    HSSFCell celda88  = fila.createCell((short)88);
                    celda88.setCellValue((data.getCaudalcultivo()!=null)?data.getCaudalcultivo(): 0.0) ;

                    HSSFCell celda89  = fila.createCell((short)89);
                    celda89.setCellValue((data.getProduccion()!=null)?data.getProduccion(): 0.0) ;

                    HSSFCell celda90  = fila.createCell((short)90);
                    celda90.setCellValue((data.getEficienciacultivo()!=null)?data.getEficienciacultivo(): 0.0) ;

                    HSSFCell celda91  = fila.createCell((short)91);
                    celda91.setCellValue((data.getAreacultivo()!=null)?data.getAreacultivo(): 0.0) ;

                    HSSFCell celda92  = fila.createCell((short)92);
                    celda92.setCellValue((data.getTipousootros()!=null)?data.getTipousootros(): "  ") ;

                    HSSFCell celda93  = fila.createCell((short)93);
                    celda93.setCellValue((data.getDecripcionotros()!=null)?data.getDecripcionotros(): "  ") ;

                    HSSFCell celda94  = fila.createCell((short)94);
                    celda94.setCellValue((data.getCaudalotros()!=null)?data.getCaudalotros(): 0.0) ;
  
                contFilas++;
        
                }catch(Exception e){
                    System.out.println("ERROR en FOR: "+e.fillInStackTrace());
                    System.out.println("ERROR Mensaje: "+e.getMessage());
                } 
            }

            libro.write(archivo);
            archivo.close();
        } catch (FileNotFoundException e) {
            System.out.println("ERROR en FileUTil, mensaje: " +
                               e.fillInStackTrace());
        } catch (IOException e) {
            System.out.println("ERROR en FileUTil, mensaje: " +
                               e.fillInStackTrace());
        }
    }

    public static void loadDataUserJuridicoCaptacionesXls(List<RegUserJuridicoConcesion> datos) 
            throws FileNotFoundException, IOException {
    
        try {
            File file = FileUtil.generateXls();
            FileOutputStream archivo = new FileOutputStream(file); 
    
            HSSFWorkbook libro = new HSSFWorkbook();
            HSSFSheet hoja = libro.createSheet(ConstantesImportacion.HOJA_CAPTACIONES_JURIDICAS);
    
            short contFilas = (short)0;  
            for (RegUserJuridicoConcesion data : datos) {
             
                HSSFRow fila = hoja.createRow(contFilas);   
                HSSFCell celda0  = fila.createCell((short)0);
                celda0.setCellValue((data.getTipousuario()!=null)?data.getTipousuario(): " ") ;
    
                HSSFCell celda1  = fila.createCell((short)1);
                celda1.setCellValue((data.getRazonsocial()!=null)?data.getRazonsocial(): " ") ;
    
                HSSFCell celda2  = fila.createCell((short)2);
                celda2.setCellValue((data.getTipodocumento()!=null)?data.getTipodocumento(): " ") ;
    
                HSSFCell celda3  = fila.createCell((short)3);
                celda3.setCellValue((data.getNumdocumento()!=null)?data.getNumdocumento(): " ") ;
    
                HSSFCell celda4  = fila.createCell((short)4);
                celda4.setCellValue((data.getActividadeconomica()!=null)?data.getActividadeconomica(): 0) ;
    
                HSSFCell celda5  = fila.createCell((short)5);
                celda5.setCellValue((data.getDircorrespondencia()!=null)?data.getDircorrespondencia(): " ") ;
    
                HSSFCell celda6  = fila.createCell((short)6);
                celda6.setCellValue((data.getEmail()!=null)?data.getEmail(): " ") ;
    
                HSSFCell celda7  = fila.createCell((short)7);
                celda7.setCellValue((data.getTelefono()!=null)?data.getTelefono(): " ") ;
    
                HSSFCell celda8  = fila.createCell((short)8);
                celda8.setCellValue((data.getFax()!=null)?data.getFax(): " ") ;
    
                HSSFCell celda9  = fila.createCell((short)9);
                celda9.setCellValue((data.getNombrerlegal()!=null)?data.getNombrerlegal(): " ") ;
    
                HSSFCell celda10  = fila.createCell((short)10);
                celda10.setCellValue((data.getApellidorlegal()!=null)?data.getApellidorlegal(): " ") ;
    
                HSSFCell celda11  = fila.createCell((short)11);
                celda11.setCellValue((data.getTipodocrlegal()!=null)?data.getTipodocrlegal(): " ") ;
    
                HSSFCell celda12  = fila.createCell((short)12);
                celda12.setCellValue((data.getNumdocrlegal()!=null)?data.getNumdocrlegal(): " ") ;
    
                HSSFCell celda13  = fila.createCell((short)13);
                celda13.setCellValue((data.getDeptorlegal()!=null)?data.getDeptorlegal(): " ") ;
    
                HSSFCell celda14  = fila.createCell((short)14);
                celda14.setCellValue((data.getMunicipiorlegal()!=null)?data.getMunicipiorlegal(): " ") ;
    
                HSSFCell celda15  = fila.createCell((short)15);
                celda15.setCellValue((data.getDircorresprlegal()!=null)?data.getDircorresprlegal(): " ") ;
    
                HSSFCell celda16  = fila.createCell((short)16);
                celda16.setCellValue((data.getTelefonorlegal()!=null)?data.getTelefonorlegal(): " ") ;
    
                HSSFCell celda17  = fila.createCell((short)17);
                celda17.setCellValue((data.getNombresitio()!=null)?data.getNombresitio(): " ") ;
    
                HSSFCell celda18  = fila.createCell((short)18);
                celda18.setCellValue((data.getTipotenencia()!=null)?data.getTipotenencia(): " ") ;
    
                HSSFCell celda19  = fila.createCell((short)19);
                celda19.setCellValue((data.getDeptoempr()!=null)?data.getDeptoempr(): " ") ;
    
                HSSFCell celda20  = fila.createCell((short)20);
                celda20.setCellValue((data.getMunicipioempr()!=null)?data.getMunicipioempr(): " ") ;
    
                HSSFCell celda21  = fila.createCell((short)21);
                celda21.setCellValue((data.getTipocentropobladoempr()!=null)?data.getTipocentropobladoempr(): " ") ;
    
                HSSFCell celda22  = fila.createCell((short)22);
                celda22.setCellValue((data.getCentropobladoempr()!=null)?data.getCentropobladoempr(): " ") ;
    
                HSSFCell celda23  = fila.createCell((short)23);
                celda23.setCellValue((data.getCedulacatastral()!=null)?data.getCedulacatastral(): " ") ;
    
                HSSFCell celda24  = fila.createCell((short)24);
                celda24.setCellValue((data.getMatriculacatastral()!=null)?data.getMatriculacatastral(): " ") ;
    
                HSSFCell celda25  = fila.createCell((short)25);
                celda25.setCellValue((data.getAreapredio()!=null)?data.getAreapredio(): 0.0) ;
    
                HSSFCell celda26  = fila.createCell((short)26);
                celda26.setCellValue((data.getDireccionpredio()!=null)?data.getDireccionpredio(): " ") ;
    
                HSSFCell celda27  = fila.createCell((short)27);
                celda27.setCellValue((data.getCaudalconcesionado ()!=null)?data.getCaudalconcesionado (): 0.0) ;
    
                HSSFCell celda28  = fila.createCell((short)28);
                celda28.setCellValue((data.getClasificacionsuelo()!=null)?data.getClasificacionsuelo(): " ") ;
    
                HSSFCell celda29  = fila.createCell((short)29);
                celda29.setCellValue((data.getSistemarefpredio()!=null)?data.getSistemarefpredio(): " ") ;
    
                HSSFCell celda30  = fila.createCell((short)30);
                celda30.setCellValue((data.getGradoslatpredio ()!=null)?data.getGradoslatpredio (): 0) ;
    
                HSSFCell celda31  = fila.createCell((short)31);
                celda31.setCellValue((data.getMinutoslatpredio ()!=null)?data.getMinutoslatpredio (): 0) ;
    
                HSSFCell celda32  = fila.createCell((short)32);
                celda32.setCellValue((data.getSegundoslatpredio()!=null)?data.getSegundoslatpredio(): 0.0) ;
    
                HSSFCell celda33  = fila.createCell((short)33);
                celda33.setCellValue((data.getGradoslonpredio ()!=null)?data.getGradoslonpredio (): 0) ;
    
                HSSFCell celda34  = fila.createCell((short)34);
                celda34.setCellValue((data.getMinutoslonpredio ()!=null)?data.getMinutoslonpredio (): 0) ;
    
                HSSFCell celda35  = fila.createCell((short)35);
                celda35.setCellValue((data.getSegundoslonpredio()!=null)?data.getSegundoslonpredio(): 0.0) ;
    
                HSSFCell celda36  = fila.createCell((short)36);
                celda36.setCellValue((data.getAltitudpredio()!=null)?data.getAltitudpredio(): 0.0) ;
    
                HSSFCell celda37  = fila.createCell((short)37);
                celda37.setCellValue((data.getDescripcionsitio()!=null)?data.getDescripcionsitio(): " ") ;
    
                HSSFCell celda38  = fila.createCell((short)38);
                celda38.setCellValue((data.getNumexpediente()!=null)?data.getNumexpediente(): " ") ;
    
                HSSFCell celda39  = fila.createCell((short)39);
                celda39.setCellValue((data.getNumresolcaudal()!=null)?data.getNumresolcaudal(): " ") ;
    
                HSSFCell celda40  = fila.createCell((short)40);
                celda40.setCellValue((data.getFechaexpedicion ()!=null)?data.getFechaexpedicion (): " ") ;
    
                HSSFCell celda41  = fila.createCell((short)41);
                celda41.setCellValue((data.getFechanotificacion()!=null)?data.getFechanotificacion(): " ") ;
    
                HSSFCell celda42  = fila.createCell((short)42);
                celda42.setCellValue((data.getNumresolplanos()!=null)?data.getNumresolplanos(): " ") ;
    
                HSSFCell celda43  = fila.createCell((short)43);
                celda43.setCellValue((data.getFechaexpresolplanos()!=null)?data.getFechaexpresolplanos(): " ") ;
    
                HSSFCell celda44  = fila.createCell((short)44);
                celda44.setCellValue((data.getFechanotificacionplanos()!=null)?data.getFechanotificacionplanos(): " ") ;
    
                HSSFCell celda45  = fila.createCell((short)45);
                celda45.setCellValue((data.getNumresolucionobra()!=null)?data.getNumresolucionobra(): " ") ;
    
                HSSFCell celda46  = fila.createCell((short)46);
                celda46.setCellValue((data.getFechaexpedresolobra()!=null)?data.getFechaexpedresolobra(): " ") ;
    
                HSSFCell celda47  = fila.createCell((short)47);
                celda47.setCellValue((data.getFechanotifobra()!=null)?data.getFechanotifobra(): " ") ;
    
                HSSFCell celda48  = fila.createCell((short)48);
                celda48.setCellValue((data.getFechainicial()!=null)?data.getFechainicial(): " ") ;
    
                HSSFCell celda49  = fila.createCell((short)49);
                celda49.setCellValue((data.getFechafinal()!=null)?data.getFechafinal(): " ") ;
    
                HSSFCell celda50  = fila.createCell((short)50);
                celda50.setCellValue((data.getTipofuentecap()!=null)?data.getTipofuentecap(): " ") ;
    
                HSSFCell celda51  = fila.createCell((short)51);
                celda51.setCellValue((data.getDepartamento()!=null)?data.getDepartamento(): " ") ;
    
                HSSFCell celda52  = fila.createCell((short)52);
                celda52.setCellValue((data.getMunicipio()!=null)?data.getMunicipio(): " ") ;
    
                HSSFCell celda53  = fila.createCell((short)53);
                celda53.setCellValue((data.getTipocentropoblado()!=null)?data.getTipocentropoblado(): " ") ;
    
                HSSFCell celda54  = fila.createCell((short)54);
                celda54.setCellValue((data.getCentropoblado()!=null)?data.getCentropoblado(): " ") ;
    
                HSSFCell celda55  = fila.createCell((short)55);
                celda55.setCellValue((data.getArea()!=null)?data.getArea(): " ") ;
    
                HSSFCell celda56  = fila.createCell((short)56);
                celda56.setCellValue((data.getZona()!=null)?data.getZona(): " ") ;
    
                HSSFCell celda57  = fila.createCell((short)57);
                celda57.setCellValue((data.getSubzona()!=null)?data.getSubzona(): " ") ;
    
                HSSFCell celda58  = fila.createCell((short)58);
                celda58.setCellValue((data.getTipofuente()!=null)?data.getTipofuente(): " ") ;
    
                HSSFCell celda59  = fila.createCell((short)59);
                celda59.setCellValue((data.getFuente()!=null)?data.getFuente(): " ") ;
    
                HSSFCell celda60  = fila.createCell((short)60);
                celda60.setCellValue((data.getTramo()!=null)?data.getTramo(): " ") ;
    
                HSSFCell celda61  = fila.createCell((short)61);
                celda61.setCellValue((data.getIdpunto()!=null)?data.getIdpunto(): 0) ;
    
                HSSFCell celda62  = fila.createCell((short)62);
                celda62.setCellValue((data.getProvinciahidro()!=null)?data.getProvinciahidro(): " ") ;
    
                HSSFCell celda63  = fila.createCell((short)63);
                celda63.setCellValue((data.getUnidadhidro()!=null)?data.getUnidadhidro(): " ") ;
    
                HSSFCell celda64  = fila.createCell((short)64);
                celda64.setCellValue((data.getAduccion()!=null)?data.getAduccion(): " ") ;
    
                HSSFCell celda65  = fila.createCell((short)65);
                celda65.setCellValue((data.getDesarenador()!=null)?data.getDesarenador(): " ") ;
    
                HSSFCell celda66  = fila.createCell((short)66);
                celda66.setCellValue((data.getPtap()!=null)?data.getPtap(): " ") ;
    
                HSSFCell celda67  = fila.createCell((short)67);
                celda67.setCellValue((data.getReddistribucion()!=null)?data.getReddistribucion(): " ") ;
    
                HSSFCell celda68  = fila.createCell((short)68);
                celda68.setCellValue((data.getTanque()!=null)?data.getTanque(): " ") ;
    
                HSSFCell celda69  = fila.createCell((short)69);
                celda69.setCellValue((data.getTipocaptacion()!=null)?data.getTipocaptacion(): " ") ;
    
                HSSFCell celda70  = fila.createCell((short)70);
                celda70.setCellValue((data.getOfertatotal()!=null)?data.getOfertatotal(): 0.0) ;
    
                HSSFCell celda71  = fila.createCell((short)71);
                celda71.setCellValue((data.getAreacaptacion()!=null)?data.getAreacaptacion(): 0.0) ;
    
                HSSFCell celda72  = fila.createCell((short)72);
                celda72.setCellValue((data.getOfertadisponible()!=null)?data.getOfertadisponible(): 0.0) ;
    
                HSSFCell celda73  = fila.createCell((short)73);
                celda73.setCellValue((data.getMacromedicion()!=null)?data.getMacromedicion(): " ") ;
    
                HSSFCell celda74  = fila.createCell((short)74);
                celda74.setCellValue((data.getEstadocapacion()!=null)?data.getEstadocapacion(): " ") ;
    
                HSSFCell celda75  = fila.createCell((short)75);
                celda75.setCellValue((data.getCaudaldiseno()!=null)?data.getCaudaldiseno(): 0.0) ;
    
                HSSFCell celda76  = fila.createCell((short)76);
                celda76.setCellValue((data.getContinuidadservicio()!=null)?data.getContinuidadservicio(): " ") ;
    
                HSSFCell celda77  = fila.createCell((short)77);
                celda77.setCellValue((data.getTieneservidumbre()!=null)?data.getTieneservidumbre(): " ") ;
    
                HSSFCell celda78  = fila.createCell((short)78);
                celda78.setCellValue((data.getSistemarefcapt()!=null)?data.getSistemarefcapt(): " ") ;
    
                HSSFCell celda79  = fila.createCell((short)79);
                celda79.setCellValue((data.getGradoslatcapt()!=null)?data.getGradoslatcapt(): 0) ;
    
                HSSFCell celda80  = fila.createCell((short)80);
                celda80.setCellValue((data.getMinutoslatcapt()!=null)?data.getMinutoslatcapt(): 0) ;
    
                HSSFCell celda81  = fila.createCell((short)81);
                celda81.setCellValue((data.getSegundoslatcapt()!=null)?data.getSegundoslatcapt(): 0.0) ;
    
                HSSFCell celda82  = fila.createCell((short)82);
                celda82.setCellValue((data.getGradosloncapt()!=null)?data.getGradosloncapt(): 0) ;
    
                HSSFCell celda83  = fila.createCell((short)83);
                celda83.setCellValue((data.getMinutosloncapt()!=null)?data.getMinutosloncapt(): 0) ;
    
                HSSFCell celda84  = fila.createCell((short)84);
                celda84.setCellValue((data.getSegundosloncapt()!=null)?data.getSegundosloncapt(): 0.0) ;
    
                HSSFCell celda85  = fila.createCell((short)85);
                celda85.setCellValue((data.getAltitudcapt()!=null)?data.getAltitudcapt(): 0.0) ;
    
                HSSFCell celda86  = fila.createCell((short)86);
                celda86.setCellValue((data.getDescripcionsitio()!=null)?data.getDescripcionsitio(): " ") ;
    
                HSSFCell celda87  = fila.createCell((short)87);
                celda87.setCellValue((data.getCaudalabast()!=null)?data.getCaudalabast(): 0) ;
    
                HSSFCell celda88  = fila.createCell((short)88);
                celda88.setCellValue((data.getNumpersonaspermanentes()!=null)?data.getNumpersonaspermanentes(): 0) ;
    
                HSSFCell celda89  = fila.createCell((short)89);
                celda89.setCellValue((data.getNumpersonastransitorias()!=null)?data.getNumpersonastransitorias(): 0) ;
    
                HSSFCell celda90  = fila.createCell((short)90);
                celda90.setCellValue((data.getAprovechamiento()!=null)?data.getAprovechamiento(): 0.0) ;
    
                HSSFCell celda91  = fila.createCell((short)91);
                celda91.setCellValue((data.getTipoanimalabrev()!=null)?data.getTipoanimalabrev(): " ") ;
    
                HSSFCell celda92  = fila.createCell((short)92);
                celda92.setCellValue((data.getCaudalabrev()!=null)?data.getCaudalabrev(): 0.0) ;
    
                HSSFCell celda93  = fila.createCell((short)93);
                celda93.setCellValue((data.getNumanimalesabrev()!=null)?data.getNumanimalesabrev(): 0) ;
    
                HSSFCell celda94  = fila.createCell((short)94);
                celda94.setCellValue((data.getTipoanimalpesca()!=null)?data.getTipoanimalpesca(): " ") ;
    
                HSSFCell celda95  = fila.createCell((short)95);
                celda95.setCellValue((data.getCaudalpesca()!=null)?data.getCaudalpesca(): 0.0) ;
    
                HSSFCell celda96  = fila.createCell((short)96);
                celda96.setCellValue((data.getNumanimalespesca()!=null)?data.getNumanimalespesca(): 0) ;
    
                HSSFCell celda97  = fila.createCell((short)97);
                celda97.setCellValue((data.getTipocultivo()!=null)?data.getTipocultivo(): " ") ;
    
                HSSFCell celda98  = fila.createCell((short)98);
                celda98.setCellValue((data.getCaudalcultivo()!=null)?data.getCaudalcultivo(): 0.0) ;
    
                HSSFCell celda99  = fila.createCell((short)99);
                celda99.setCellValue((data.getProduccion()!=null)?data.getProduccion(): 0.0) ;
    
                HSSFCell celda100  = fila.createCell((short)100);
                celda100.setCellValue((data.getEficienciacultivo()!=null)?data.getEficienciacultivo(): 0.0) ;
    
                HSSFCell celda101  = fila.createCell((short)101);
                celda101.setCellValue((data.getAreacultivo()!=null)?data.getAreacultivo(): 0.0) ;
    
                HSSFCell celda102  = fila.createCell((short)102);
                celda102.setCellValue((data.getTipousootros()!=null)?data.getTipousootros(): " ") ;
    
                HSSFCell celda103  = fila.createCell((short)103);
                celda103.setCellValue((data.getDecripcionotros()!=null)?data.getDecripcionotros(): " ") ;
    
                HSSFCell celda104  = fila.createCell((short)104);
                celda104.setCellValue((data.getCaudalotros()!=null)?data.getCaudalotros(): 0.0) ;
              
        contFilas++;
        }
    
        libro.write(archivo);
        archivo.close();
        } catch (FileNotFoundException e) {
        System.out.println("ERROR en FileUTil, mensaje: " +
                       e.fillInStackTrace());
        } catch (IOException e) {
        System.out.println("ERROR en FileUTil, mensaje: " +
                       e.fillInStackTrace());
        }
    }
           
    public static void loadDataUserNaturalPermisosVertimientosXls(List<RegUserNaturalVertimiento> datos) 
            throws FileNotFoundException, IOException {
    
        try {
            File file = FileUtil.generateXls();
            FileOutputStream archivo = new FileOutputStream(file); 
    
            HSSFWorkbook libro = new HSSFWorkbook();
            HSSFSheet hoja = libro.createSheet(ConstantesImportacion.HOJA_VERTIMIENTOS_NATURALES);
    
            short contFilas = (short)0;  
            for (RegUserNaturalVertimiento data : datos) {
             
                HSSFRow fila = hoja.createRow(contFilas);
            HSSFCell celda0  = fila.createCell((short)0);
            celda0.setCellValue((data.getTipopersona()!=null)?data.getTipopersona(): " ") ;

            HSSFCell celda1  = fila.createCell((short)1);
            celda1.setCellValue((data.getNombre()!=null)?data.getNombre(): " ") ;

            HSSFCell celda2  = fila.createCell((short)2);
            celda2.setCellValue((data.getApellido()!=null)?data.getApellido(): " ") ;

            HSSFCell celda3  = fila.createCell((short)3);
            celda3.setCellValue((data.getTipodocumento()!=null)?data.getTipodocumento(): " ") ;

            HSSFCell celda4  = fila.createCell((short)4);
            celda4.setCellValue((data.getNumdocumento()!=null)?data.getNumdocumento(): " ") ;

            HSSFCell celda5  = fila.createCell((short)5);
            celda5.setCellValue((data.getDepartamento()!=null)?data.getDepartamento(): " ") ;

            HSSFCell celda6  = fila.createCell((short)6);
            celda6.setCellValue((data.getMunicipio()!=null)?data.getMunicipio(): " ") ;

            HSSFCell celda7  = fila.createCell((short)7);
            celda7.setCellValue((data.getDircorrespondencia()!=null)?data.getDircorrespondencia(): " ") ;

            HSSFCell celda8  = fila.createCell((short)8);
            celda8.setCellValue((data.getEmail()!=null)?data.getEmail(): " ") ;

            HSSFCell celda9  = fila.createCell((short)9);
            celda9.setCellValue((data.getTelefono()!=null)?data.getTelefono(): " ") ;

            HSSFCell celda10  = fila.createCell((short)10);
            celda10.setCellValue((data.getFax()!=null)?data.getFax(): " ") ;

            HSSFCell celda11  = fila.createCell((short)11);
            celda11.setCellValue((data.getNombrepredio()!=null)?data.getNombrepredio(): " ") ;

            HSSFCell celda12  = fila.createCell((short)12);
            celda12.setCellValue((data.getTipotenencia()!=null)?data.getTipotenencia(): " ") ;

            HSSFCell celda13  = fila.createCell((short)13);
            celda13.setCellValue((data.getDeptopredio()!=null)?data.getDeptopredio(): " ") ;

            HSSFCell celda14  = fila.createCell((short)14);
            celda14.setCellValue((data.getMunicipiopredio()!=null)?data.getMunicipiopredio(): " ") ;

            HSSFCell celda15  = fila.createCell((short)15);
            celda15.setCellValue((data.getTipocentropoblado()!=null)?data.getTipocentropoblado(): " ") ;

            HSSFCell celda16  = fila.createCell((short)16);
            celda16.setCellValue((data.getCentropoblado()!=null)?data.getCentropoblado(): " ") ;

            HSSFCell celda17  = fila.createCell((short)17);
            celda17.setCellValue((data.getCedulacatastral()!=null)?data.getCedulacatastral(): " ") ;

            HSSFCell celda18  = fila.createCell((short)18);
            celda18.setCellValue((data.getMatriculainmobiliaria()!=null)?data.getMatriculainmobiliaria(): " ") ;

            HSSFCell celda19  = fila.createCell((short)19);
            celda19.setCellValue((data.getAreatotal()!=null)?data.getAreatotal(): 0.0) ;

            HSSFCell celda20  = fila.createCell((short)20);
            celda20.setCellValue((data.getDireccionpredio()!=null)?data.getDireccionpredio(): " ") ;

            HSSFCell celda21  = fila.createCell((short)21);
            celda21.setCellValue((data.getClasificacionsuelo()!=null)?data.getClasificacionsuelo(): " ") ;

            HSSFCell celda22  = fila.createCell((short)22);
            celda22.setCellValue((data.getNumresiniciotramite()!=null)?data.getNumresiniciotramite(): " ") ;

            HSSFCell celda23  = fila.createCell((short)23);
            celda23.setCellValue((data.getFecharesinitramite()!=null)?data.getFecharesinitramite(): " ") ;

            HSSFCell celda24  = fila.createCell((short)24);
            celda24.setCellValue((data.getNumexpediente()!=null)?data.getNumexpediente(): " ") ;

            HSSFCell celda25  = fila.createCell((short)25);
            celda25.setCellValue((data.getCaudalautorizado ()!=null)?data.getCaudalautorizado (): 0.0) ;

            HSSFCell celda26  = fila.createCell((short)26);
            celda26.setCellValue((data.getEvaluacionambiental()!=null)?data.getEvaluacionambiental(): " ") ;

            HSSFCell celda27  = fila.createCell((short)27);
            celda27.setCellValue((data.getNumrespresentaplan()!=null)?data.getNumrespresentaplan(): " ") ;

            HSSFCell celda28  = fila.createCell((short)28);
            celda28.setCellValue((data.getFecharespresentaplan ()!=null)?data.getFecharespresentaplan (): " ") ;

            HSSFCell celda29  = fila.createCell((short)29);
            celda29.setCellValue((data.getNumresapruebaplan()!=null)?data.getNumresapruebaplan(): " ") ;

            HSSFCell celda30  = fila.createCell((short)30);
            celda30.setCellValue((data.getFechaapruebaplan()!=null)?data.getFechaapruebaplan(): " ") ;

            HSSFCell celda31  = fila.createCell((short)31);
            celda31.setCellValue((data.getFechanotifapruebaplan()!=null)?data.getFechanotifapruebaplan(): " ") ;

            HSSFCell celda32  = fila.createCell((short)32);
            celda32.setCellValue((data.getVigenciaplanini()!=null)?data.getVigenciaplanini(): " ") ;

            HSSFCell celda33  = fila.createCell((short)33);
            celda33.setCellValue((data.getVigenciaplanfin()!=null)?data.getVigenciaplanfin(): " ") ;

            HSSFCell celda34  = fila.createCell((short)34);
            celda34.setCellValue((data.getNumrespvert()!=null)?data.getNumrespvert(): " ") ;

            HSSFCell celda35  = fila.createCell((short)35);
            celda35.setCellValue((data.getFecharespvert()!=null)?data.getFecharespvert(): " ") ;

            HSSFCell celda36  = fila.createCell((short)36);
            celda36.setCellValue((data.getVigenciapvini()!=null)?data.getVigenciapvini(): " ") ;

            HSSFCell celda37  = fila.createCell((short)37);
            celda37.setCellValue((data.getVigenciapvfin()!=null)?data.getVigenciapvfin(): " ") ;

            HSSFCell celda38  = fila.createCell((short)38);
            celda38.setCellValue((data.getNumresplanos()!=null)?data.getNumresplanos(): " ") ;

            HSSFCell celda39  = fila.createCell((short)39);
            celda39.setCellValue((data.getFechaexpresplanos()!=null)?data.getFechaexpresplanos(): " ") ;

            HSSFCell celda40  = fila.createCell((short)40);
            celda40.setCellValue((data.getNumresapruebaobras()!=null)?data.getNumresapruebaobras(): " ") ;

            HSSFCell celda41  = fila.createCell((short)41);
            celda41.setCellValue((data.getFechaexpresobras()!=null)?data.getFechaexpresobras(): " ") ;

            HSSFCell celda42  = fila.createCell((short)42);
            celda42.setCellValue((data.getFechanotifobras()!=null)?data.getFechanotifobras(): " ") ;

            HSSFCell celda43  = fila.createCell((short)43);
            celda43.setCellValue((data.getTipovertimiento()!=null)?data.getTipovertimiento(): " ") ;

            HSSFCell celda44  = fila.createCell((short)44);
            celda44.setCellValue((data.getDeptopvert()!=null)?data.getDeptopvert(): " ") ;

            HSSFCell celda45  = fila.createCell((short)45);
            celda45.setCellValue((data.getMunicipiopvert()!=null)?data.getMunicipiopvert(): " ") ;

            HSSFCell celda46  = fila.createCell((short)46);
            celda46.setCellValue((data.getTipocentropobladovert()!=null)?data.getTipocentropobladovert(): " ") ;

            HSSFCell celda47  = fila.createCell((short)47);
            celda47.setCellValue((data.getCentropobladovert()!=null)?data.getCentropobladovert(): " ") ;

            HSSFCell celda48  = fila.createCell((short)48);
            celda48.setCellValue((data.getArea()!=null)?data.getArea(): " ") ;

            HSSFCell celda49  = fila.createCell((short)49);
            celda49.setCellValue((data.getZona()!=null)?data.getZona(): " ") ;

            HSSFCell celda50  = fila.createCell((short)50);
            celda50.setCellValue((data.getSubzona()!=null)?data.getSubzona(): " ") ;

            HSSFCell celda51  = fila.createCell((short)51);
            celda51.setCellValue((data.getTipofuente()!=null)?data.getTipofuente(): " ") ;

            HSSFCell celda52  = fila.createCell((short)52);
            celda52.setCellValue((data.getFuente()!=null)?data.getFuente(): " ") ;

            HSSFCell celda53  = fila.createCell((short)53);
            celda53.setCellValue((data.getTramo()!=null)?data.getTramo(): " ") ;

            HSSFCell celda54  = fila.createCell((short)54);
            celda54.setCellValue((data.getTipoflujo()!=null)?data.getTipoflujo(): " ") ;

            HSSFCell celda55  = fila.createCell((short)55);
            celda55.setCellValue((data.getTiempodescarga()!=null)?data.getTiempodescarga(): 0.0) ;

            HSSFCell celda56  = fila.createCell((short)56);
            celda56.setCellValue((data.getFrecuenciames()!=null)?data.getFrecuenciames(): 0.0) ;

            HSSFCell celda57  = fila.createCell((short)57);
            celda57.setCellValue((data.getCaudaldiseno()!=null)?data.getCaudaldiseno(): 0.0) ;

            HSSFCell celda58  = fila.createCell((short)58);
            celda58.setCellValue((data.getPretratamiento()!=null)?data.getPretratamiento(): " ") ;

            HSSFCell celda59  = fila.createCell((short)59);
            celda59.setCellValue((data.getPrimario()!=null)?data.getPrimario(): " ") ;

            HSSFCell celda60  = fila.createCell((short)60);
            celda60.setCellValue((data.getSecundario()!=null)?data.getSecundario(): " ") ;

            HSSFCell celda61  = fila.createCell((short)61);
            celda61.setCellValue((data.getTerciarios()!=null)?data.getTerciarios(): " ") ;

            HSSFCell celda62  = fila.createCell((short)62);
            celda62.setCellValue((data.getOtros()!=null)?data.getOtros(): " ") ;

            HSSFCell celda63  = fila.createCell((short)63);
            celda63.setCellValue((data.getSistemaref()!=null)?data.getSistemaref(): " ") ;

            HSSFCell celda64  = fila.createCell((short)64);
            celda64.setCellValue((data.getGradoslat()!=null)?data.getGradoslat(): 0) ;

            HSSFCell celda65  = fila.createCell((short)65);
            celda65.setCellValue((data.getMinutoslat()!=null)?data.getMinutoslat(): 0) ;

            HSSFCell celda66  = fila.createCell((short)66);
            celda66.setCellValue((data.getSegundolat()!=null)?data.getSegundolat(): 0.0) ;

            HSSFCell celda67  = fila.createCell((short)67);
            celda67.setCellValue((data.getGradoslon()!=null)?data.getGradoslon(): 0) ;

            HSSFCell celda68  = fila.createCell((short)68);
            celda68.setCellValue((data.getMinutoslon()!=null)?data.getMinutoslon(): 0) ;

            HSSFCell celda69  = fila.createCell((short)69);
            celda69.setCellValue((data.getSegundoslon()!=null)?data.getSegundoslon(): 0.0) ;

            HSSFCell celda70  = fila.createCell((short)70);
            celda70.setCellValue((data.getAltitud()!=null)?data.getAltitud(): 0.0) ;

            HSSFCell celda71  = fila.createCell((short)71);
            celda71.setCellValue((data.getDescripcionsitio()!=null)?data.getDescripcionsitio(): " ") ;
                
        contFilas++;
        }    
        libro.write(archivo);
        archivo.close();
        } catch (FileNotFoundException e) {
        System.out.println("ERROR en FileUTil, mensaje: " +
                       e.fillInStackTrace());
        } catch (IOException e) {
        System.out.println("ERROR en FileUTil, mensaje: " +
                       e.fillInStackTrace());
        }
    }        


    public static void loadDataUserJuridicoPermisosVertimientosXls(List<RegUserJuridicoVertimiento> datos) 
            throws FileNotFoundException, IOException {
    
        try {
            File file = FileUtil.generateXls();
            FileOutputStream archivo = new FileOutputStream(file); 
    
            HSSFWorkbook libro = new HSSFWorkbook();
            HSSFSheet hoja = libro.createSheet(ConstantesImportacion.HOJA_VERTIMIENTOS_JURIDICAS);
    
            short contFilas = (short)0;  
            for (RegUserJuridicoVertimiento data : datos) {
             
                HSSFRow fila = hoja.createRow(contFilas);
              
                HSSFCell celda0  = fila.createCell((short)0);
                celda0.setCellValue((data.getTipousuario()!=null)?data.getTipousuario(): " ") ;
      
                HSSFCell celda1  = fila.createCell((short)1);
                celda1.setCellValue((data.getRazonsocial()!=null)?data.getRazonsocial(): " ") ;
      
                HSSFCell celda2  = fila.createCell((short)2);
                celda2.setCellValue((data.getTipodocumento()!=null)?data.getTipodocumento(): " ") ;
      
                HSSFCell celda3  = fila.createCell((short)3);
                celda3.setCellValue((data.getNumdocumento()!=null)?data.getNumdocumento(): " ") ;
      
                HSSFCell celda4  = fila.createCell((short)4);
                celda4.setCellValue((data.getActividadeconomica()!=null)?data.getActividadeconomica(): 0) ;
      
                HSSFCell celda5  = fila.createCell((short)5);
                celda5.setCellValue((data.getDircorrespondencia()!=null)?data.getDircorrespondencia(): " ") ;
      
                HSSFCell celda6  = fila.createCell((short)6);
                celda6.setCellValue((data.getEmail()!=null)?data.getEmail(): " ") ;
      
                HSSFCell celda7  = fila.createCell((short)7);
                celda7.setCellValue((data.getTelefono()!=null)?data.getTelefono(): " ") ;
      
                HSSFCell celda8  = fila.createCell((short)8);
                celda8.setCellValue((data.getFax()!=null)?data.getFax(): " ") ;
      
                HSSFCell celda9  = fila.createCell((short)9);
                celda9.setCellValue((data.getNombrerlegal()!=null)?data.getNombrerlegal(): " ") ;
      
                HSSFCell celda10  = fila.createCell((short)10);
                celda10.setCellValue((data.getApellidorlegal()!=null)?data.getApellidorlegal(): " ") ;
      
                HSSFCell celda11  = fila.createCell((short)11);
                celda11.setCellValue((data.getTipodocrlegal()!=null)?data.getTipodocrlegal(): " ") ;
      
                HSSFCell celda12  = fila.createCell((short)12);
                celda12.setCellValue((data.getNumdocrlegal()!=null)?data.getNumdocrlegal(): " ") ;
      
                HSSFCell celda13  = fila.createCell((short)13);
                celda13.setCellValue((data.getDeptorlegal()!=null)?data.getDeptorlegal(): " ") ;
      
                HSSFCell celda14  = fila.createCell((short)14);
                celda14.setCellValue((data.getMunicipiorlegal()!=null)?data.getMunicipiorlegal(): " ") ;
      
                HSSFCell celda15  = fila.createCell((short)15);
                celda15.setCellValue((data.getDircorresprlegal()!=null)?data.getDircorresprlegal(): " ") ;
      
                HSSFCell celda16  = fila.createCell((short)16);
                celda16.setCellValue((data.getTelefonorlegal()!=null)?data.getTelefonorlegal(): " ") ;
      
                HSSFCell celda17  = fila.createCell((short)17);
                celda17.setCellValue((data.getNombrepredio()!=null)?data.getNombrepredio(): " ") ;
      
                HSSFCell celda18  = fila.createCell((short)18);
                celda18.setCellValue((data.getTipotenencia()!=null)?data.getTipotenencia(): " ") ;
      
                HSSFCell celda19  = fila.createCell((short)19);
                celda19.setCellValue((data.getDeptopredio()!=null)?data.getDeptopredio(): " ") ;
      
                HSSFCell celda20  = fila.createCell((short)20);
                celda20.setCellValue((data.getMunicipiopredio()!=null)?data.getMunicipiopredio(): " ") ;
      
                HSSFCell celda21  = fila.createCell((short)21);
                celda21.setCellValue((data.getTipocentropoblado()!=null)?data.getTipocentropoblado(): " ") ;
      
                HSSFCell celda22  = fila.createCell((short)22);
                celda22.setCellValue((data.getCentropoblado()!=null)?data.getCentropoblado(): " ") ;
      
                HSSFCell celda23  = fila.createCell((short)23);
                celda23.setCellValue((data.getCedulacatastral()!=null)?data.getCedulacatastral(): " ") ;
      
                HSSFCell celda24  = fila.createCell((short)24);
                celda24.setCellValue((data.getMatriculainmobiliaria()!=null)?data.getMatriculainmobiliaria(): " ") ;
      
                HSSFCell celda25  = fila.createCell((short)25);
                celda25.setCellValue((data.getAreatotal()!=null)?data.getAreatotal(): 0.0) ;
      
                HSSFCell celda26  = fila.createCell((short)26);
                celda26.setCellValue((data.getDireccionpredio()!=null)?data.getDireccionpredio(): " ") ;
      
                HSSFCell celda27  = fila.createCell((short)27);
                celda27.setCellValue((data.getClasificacionsuelo()!=null)?data.getClasificacionsuelo(): " ") ;
      
                HSSFCell celda28  = fila.createCell((short)28);
                celda28.setCellValue((data.getNumresiniciotramite()!=null)?data.getNumresiniciotramite(): " ") ;
      
                HSSFCell celda29  = fila.createCell((short)29);
                celda29.setCellValue((data.getFecharesinitramite ()!=null)?data.getFecharesinitramite (): " ") ;
      
                HSSFCell celda30  = fila.createCell((short)30);
                celda30.setCellValue((data.getNumexpediente ()!=null)?data.getNumexpediente (): " ") ;
      
                HSSFCell celda31  = fila.createCell((short)31);
                celda31.setCellValue((data.getCaudalautorizado ()!=null)?data.getCaudalautorizado (): 0.0) ;
      
                HSSFCell celda32  = fila.createCell((short)32);
                celda32.setCellValue((data.getEvaluacionambiental()!=null)?data.getEvaluacionambiental(): " ") ;
      
                HSSFCell celda33  = fila.createCell((short)33);
                celda33.setCellValue((data.getNumrespresentaplan()!=null)?data.getNumrespresentaplan(): " ") ;
      
                HSSFCell celda34  = fila.createCell((short)34);
                celda34.setCellValue((data.getFecharespresentaplan ()!=null)?data.getFecharespresentaplan (): " ") ;
      
                HSSFCell celda35  = fila.createCell((short)35);
                celda35.setCellValue((data.getNumresapruebaplan()!=null)?data.getNumresapruebaplan(): " ") ;
      
                HSSFCell celda36  = fila.createCell((short)36);
                celda36.setCellValue((data.getFechaapruebaplan()!=null)?data.getFechaapruebaplan(): " ") ;
      
                HSSFCell celda37  = fila.createCell((short)37);
                celda37.setCellValue((data.getFechanotifapruebaplan()!=null)?data.getFechanotifapruebaplan(): " ") ;
      
                HSSFCell celda38  = fila.createCell((short)38);
                celda38.setCellValue((data.getVigenciaplanini()!=null)?data.getVigenciaplanini(): " ") ;
      
                HSSFCell celda39  = fila.createCell((short)39);
                celda39.setCellValue((data.getVigenciaplanfin()!=null)?data.getVigenciaplanfin(): " ") ;
      
                HSSFCell celda40  = fila.createCell((short)40);
                celda40.setCellValue((data.getNumrespvert()!=null)?data.getNumrespvert(): " ") ;
      
                HSSFCell celda41  = fila.createCell((short)41);
                celda41.setCellValue((data.getFecharespvert()!=null)?data.getFecharespvert(): " ") ;
      
                HSSFCell celda42  = fila.createCell((short)42);
                celda42.setCellValue((data.getVigenciapvini()!=null)?data.getVigenciapvini(): " ") ;
      
                HSSFCell celda43  = fila.createCell((short)43);
                celda43.setCellValue((data.getVigenciapvfin()!=null)?data.getVigenciapvfin(): " ") ;
      
                HSSFCell celda44  = fila.createCell((short)44);
                celda44.setCellValue((data.getNumresplanos()!=null)?data.getNumresplanos(): " ") ;
      
                HSSFCell celda45  = fila.createCell((short)45);
                celda45.setCellValue((data.getFechaexpresplanos()!=null)?data.getFechaexpresplanos(): " ") ;
      
                HSSFCell celda46  = fila.createCell((short)46);
                celda46.setCellValue((data.getNumresapruebaobras()!=null)?data.getNumresapruebaobras(): " ") ;
      
                HSSFCell celda47  = fila.createCell((short)47);
                celda47.setCellValue((data.getFechaexpresobras()!=null)?data.getFechaexpresobras(): " ") ;
      
                HSSFCell celda48  = fila.createCell((short)48);
                celda48.setCellValue((data.getFechanotifobras()!=null)?data.getFechanotifobras(): " ") ;
      
                HSSFCell celda49  = fila.createCell((short)49);
                celda49.setCellValue((data.getTipovertimiento()!=null)?data.getTipovertimiento(): " ") ;
      
                HSSFCell celda50  = fila.createCell((short)50);
                celda50.setCellValue((data.getDeptopvert()!=null)?data.getDeptopvert(): " ") ;
      
                HSSFCell celda51  = fila.createCell((short)51);
                celda51.setCellValue((data.getMunicipiopvert()!=null)?data.getMunicipiopvert(): " ") ;
      
                HSSFCell celda52  = fila.createCell((short)52);
                celda52.setCellValue((data.getTipocentropobladovert()!=null)?data.getTipocentropobladovert(): " ") ;
      
                HSSFCell celda53  = fila.createCell((short)53);
                celda53.setCellValue((data.getCentropobladovert()!=null)?data.getCentropobladovert(): " ") ;
      
                HSSFCell celda54  = fila.createCell((short)54);
                celda54.setCellValue((data.getArea()!=null)?data.getArea(): " ") ;
      
                HSSFCell celda55  = fila.createCell((short)55);
                celda55.setCellValue((data.getZona()!=null)?data.getZona(): " ") ;
      
                HSSFCell celda56  = fila.createCell((short)56);
                celda56.setCellValue((data.getSubzona()!=null)?data.getSubzona(): " ") ;
      
                HSSFCell celda57  = fila.createCell((short)57);
                celda57.setCellValue((data.getTipofuente()!=null)?data.getTipofuente(): " ") ;
      
                HSSFCell celda58  = fila.createCell((short)58);
                celda58.setCellValue((data.getFuente()!=null)?data.getFuente(): " ") ;
      
                HSSFCell celda59  = fila.createCell((short)59);
                celda59.setCellValue((data.getTramo()!=null)?data.getTramo(): " ") ;
      
                HSSFCell celda60  = fila.createCell((short)60);
                celda60.setCellValue((data.getTipoflujo()!=null)?data.getTipoflujo(): " ") ;
      
                HSSFCell celda61  = fila.createCell((short)61);
                celda61.setCellValue((data.getTiempodescarga()!=null)?data.getTiempodescarga(): 0.0) ;
      
                HSSFCell celda62  = fila.createCell((short)62);
                celda62.setCellValue((data.getFrecuenciames()!=null)?data.getFrecuenciames(): 0.0) ;
      
                HSSFCell celda63  = fila.createCell((short)63);
                celda63.setCellValue((data.getCaudaldiseno()!=null)?data.getCaudaldiseno(): 0.0) ;
      
                HSSFCell celda64  = fila.createCell((short)64);
                celda64.setCellValue((data.getPretratamiento()!=null)?data.getPretratamiento(): " ") ;
      
                HSSFCell celda65  = fila.createCell((short)65);
                celda65.setCellValue((data.getPrimario()!=null)?data.getPrimario(): " ") ;
      
                HSSFCell celda66  = fila.createCell((short)66);
                celda66.setCellValue((data.getSecundario()!=null)?data.getSecundario(): " ") ;
      
                HSSFCell celda67  = fila.createCell((short)67);
                celda67.setCellValue((data.getTerciarios()!=null)?data.getTerciarios(): " ") ;
      
                HSSFCell celda68  = fila.createCell((short)68);
                celda68.setCellValue((data.getOtros()!=null)?data.getOtros(): " ") ;
      
                HSSFCell celda69  = fila.createCell((short)69);
                celda69.setCellValue((data.getSistemaref()!=null)?data.getSistemaref(): " ") ;
      
                HSSFCell celda70  = fila.createCell((short)70);
                celda70.setCellValue((data.getGradoslat()!=null)?data.getGradoslat(): 0) ;
      
                HSSFCell celda71  = fila.createCell((short)71);
                celda71.setCellValue((data.getMinutoslat()!=null)?data.getMinutoslat(): 0) ;
      
                HSSFCell celda72  = fila.createCell((short)72);
                celda72.setCellValue((data.getSegundolat()!=null)?data.getSegundolat(): 0.0) ;
      
                HSSFCell celda73  = fila.createCell((short)73);
                celda73.setCellValue((data.getGradoslon()!=null)?data.getGradoslon(): 0) ;
      
                HSSFCell celda74  = fila.createCell((short)74);
                celda74.setCellValue((data.getMinutoslon()!=null)?data.getMinutoslon(): 0) ;
      
                HSSFCell celda75  = fila.createCell((short)75);
                celda75.setCellValue((data.getSegundoslon()!=null)?data.getSegundoslon(): 0.0) ;
      
                HSSFCell celda76  = fila.createCell((short)76);
                celda76.setCellValue((data.getAltitud()!=null)?data.getAltitud(): 0.0) ;
      
                HSSFCell celda77  = fila.createCell((short)77);
                celda77.setCellValue((data.getDescripcionsitio()!=null)?data.getDescripcionsitio(): " ") ;
      
                HSSFCell celda78  = fila.createCell((short)78);
                celda78.setCellValue((data.getNumrespsmv()!=null)?data.getNumrespsmv(): " ") ;
      
                HSSFCell celda79  = fila.createCell((short)79);
                celda79.setCellValue((data.getFechapermisopsmv()!=null)?data.getFechapermisopsmv(): " ") ;
      
                HSSFCell celda80  = fila.createCell((short)80);
                celda80.setCellValue((data.getVigenciapsmvini()!=null)?data.getVigenciapsmvini(): " ") ;
      
                HSSFCell celda81  = fila.createCell((short)81);
                celda81.setCellValue((data.getVigenciapsmvfin()!=null)?data.getVigenciapsmvfin(): " ") ;
      
                HSSFCell celda82  = fila.createCell((short)82);
                celda82.setCellValue((data.getNumresplanopsmv()!=null)?data.getNumresplanopsmv(): " ") ;
      
                HSSFCell celda83  = fila.createCell((short)83);
                celda83.setCellValue((data.getFecharesplanopsmv()!=null)?data.getFecharesplanopsmv(): " ") ;
      
                HSSFCell celda84  = fila.createCell((short)84);
                celda84.setCellValue((data.getNumresobrapsmv()!=null)?data.getNumresobrapsmv(): " ") ;
      
                HSSFCell celda85  = fila.createCell((short)85);
                celda85.setCellValue((data.getFecharesobrapsmv()!=null)?data.getFecharesobrapsmv(): " ") ;
      
                HSSFCell celda86  = fila.createCell((short)86);
                celda86.setCellValue((data.getFechanotifobrapsmv()!=null)?data.getFechanotifobrapsmv(): " ") ;
      
                HSSFCell celda87  = fila.createCell((short)87);
                celda87.setCellValue((data.getObspsmv()!=null)?data.getObspsmv(): " ") ;


               
        contFilas++;
        }
    
        libro.write(archivo);
        archivo.close();
        } catch (FileNotFoundException e) {
        System.out.println("ERROR en FileUTil, mensaje: " +
                       e.fillInStackTrace());
        } catch (IOException e) {
        System.out.println("ERROR en FileUTil, mensaje: " +
                       e.fillInStackTrace());
        }
    }
  
    public static void loadDataPuntosMonitoreoCalidadXls(List<PuntosMonitoreoCalidad> datos) 
            throws FileNotFoundException, IOException {
 
        try {
            File file = FileUtil.generateXls();
            FileOutputStream archivo = new FileOutputStream(file); 
  
            HSSFWorkbook libro = new HSSFWorkbook();
            HSSFSheet hoja = libro.createSheet(ConstantesImportacion.HOJA_PUNTOS);
 
            short contFilas = (short)0;  
            for (PuntosMonitoreoCalidad data : datos) {
             
                HSSFRow fila = hoja.createRow(contFilas);

                HSSFCell celda0  = fila.createCell((short)0);
                celda0.setCellValue((data.getIdfuente()!=null)?data.getIdfuente(): 0) ;

                HSSFCell celda1  = fila.createCell((short)1);
                celda1.setCellValue((data.getNombrepunto()!=null)?data.getNombrepunto(): "  ") ;

                HSSFCell celda2  = fila.createCell((short)2);
                celda2.setCellValue((data.getTipopunto()!=null)?data.getTipopunto(): "  ") ;

                HSSFCell celda3  = fila.createCell((short)3);
                celda3.setCellValue((data.getUbicacion()!=null)?data.getUbicacion(): "  ") ;

                HSSFCell celda4  = fila.createCell((short)4);
                celda4.setCellValue((data.getDepartamento()!=null)?data.getDepartamento(): "  ") ;

                HSSFCell celda5  = fila.createCell((short)5);
                celda5.setCellValue((data.getMunicipio()!=null)?data.getMunicipio(): "  ") ;

                HSSFCell celda6  = fila.createCell((short)6);
                celda6.setCellValue((data.getArea()!=null)?data.getArea(): "  ") ;

                HSSFCell celda7  = fila.createCell((short)7);
                celda7.setCellValue((data.getZona()!=null)?data.getZona(): "  ") ;

                HSSFCell celda8  = fila.createCell((short)8);
                celda8.setCellValue((data.getSubzona()!=null)?data.getSubzona(): "  ") ;

                HSSFCell celda9  = fila.createCell((short)9);
                celda9.setCellValue((data.getTipofuente()!=null)?data.getTipofuente(): "  ") ;

                HSSFCell celda10  = fila.createCell((short)10);
                celda10.setCellValue((data.getFuente()!=null)?data.getFuente(): "  ") ;

                HSSFCell celda11  = fila.createCell((short)11);
                celda11.setCellValue((data.getTramo()!=null)?data.getTramo(): "  ") ;

                HSSFCell celda12  = fila.createCell((short)12);
                celda12.setCellValue((data.getSistemareferencia()!=null)?data.getSistemareferencia(): "  ") ;

                HSSFCell celda13  = fila.createCell((short)13);
                celda13.setCellValue((data.getGradoslatitud()!=null)?data.getGradoslatitud(): 0) ;

                HSSFCell celda14  = fila.createCell((short)14);
                celda14.setCellValue((data.getMinutoslatitud()!=null)?data.getMinutoslatitud(): 0) ;

                HSSFCell celda15  = fila.createCell((short)15);
                celda15.setCellValue((data.getSegundoslatitud()!=null)?data.getSegundoslatitud(): 0.0) ;

                HSSFCell celda16  = fila.createCell((short)16);
                celda16.setCellValue((data.getGradoslongitud()!=null)?data.getGradoslongitud(): 0) ;

                HSSFCell celda17  = fila.createCell((short)17);
                celda17.setCellValue((data.getMinutoslongitud()!=null)?data.getMinutoslongitud(): 0.0) ;

                HSSFCell celda18  = fila.createCell((short)18);
                celda18.setCellValue((data.getSegundoslongitud()!=null)?data.getSegundoslongitud(): 0.0) ;

                HSSFCell celda19  = fila.createCell((short)19);
                celda19.setCellValue((data.getAltitud()!=null)?data.getAltitud(): 0.0) ;

                HSSFCell celda20  = fila.createCell((short)20);
                celda20.setCellValue((data.getDescripcionacceso()!=null)?data.getDescripcionacceso(): "  ") ;
               
                contFilas++;
            }

            libro.write(archivo);
            archivo.close();
        } catch (FileNotFoundException e) {
            System.out.println("ERROR en FileUTil, mensaje: " +
                               e.fillInStackTrace());
        } catch (IOException e) {
            System.out.println("ERROR en FileUTil, mensaje: " +
                               e.fillInStackTrace());
        }
      }
    
      public static void loadDataMuestrasMedicionesXls(List<MuestrasMediciones> datos) 
              throws FileNotFoundException, IOException {
      
          try {
              File file = FileUtil.generateXls();
              FileOutputStream archivo = new FileOutputStream(file); 
      
              HSSFWorkbook libro = new HSSFWorkbook();
              HSSFSheet hoja = libro.createSheet(ConstantesImportacion.HOJA_MEDICIONES);
      
              short contFilas = (short)0;  
              for (MuestrasMediciones data : datos) {
               
                  HSSFRow fila = hoja.createRow(contFilas);
                  HSSFCell celda0  = fila.createCell((short)0);
                  celda0.setCellValue((data.getIdpunto()!=null)?data.getIdpunto(): 0) ;
      
                  HSSFCell celda1  = fila.createCell((short)1);
                  celda1.setCellValue((data.getNombrepunto()!=null)?data.getNombrepunto(): "  ") ;
      
                  HSSFCell celda2  = fila.createCell((short)2);
                  celda2.setCellValue((data.getDescripcionacceso()!=null)?data.getDescripcionacceso(): "  ") ;
      
                  HSSFCell celda3  = fila.createCell((short)3);
                  celda3.setCellValue((data.getNombrelaboratorio()!=null)?data.getNombrelaboratorio(): "  ") ;
      
                  HSSFCell celda4  = fila.createCell((short)4);
                  celda4.setCellValue((data.getOtrolaboratorio()!=null)?data.getOtrolaboratorio(): "  ") ;
      
                  HSSFCell celda5  = fila.createCell((short)5);
                  celda5.setCellValue((data.getTipomuestra()!=null)?data.getTipomuestra(): "  ") ;
      
                  HSSFCell celda6  = fila.createCell((short)6);
                  celda6.setCellValue((data.getFechamuestra()!=null)?data.getFechamuestra(): "  ") ;
      
                  HSSFCell celda7  = fila.createCell((short)7);
                  celda7.setCellValue((data.getHoramuestra()!=null)?data.getHoramuestra(): "  ") ;
      
                  HSSFCell celda8  = fila.createCell((short)8);
                  celda8.setCellValue((data.getHoramuestreo()!=null)?data.getHoramuestreo(): "  ") ;
      
                  HSSFCell celda9  = fila.createCell((short)9);
                  celda9.setCellValue((data.getMinmuestreo()!=null)?data.getMinmuestreo(): "  ") ;
      
                  HSSFCell celda10  = fila.createCell((short)10);
                  celda10.setCellValue((data.getHorario()!=null)?data.getHorario(): "  ") ;
      
                  HSSFCell celda11  = fila.createCell((short)11);
                  celda11.setCellValue((data.getCaudal ()!=null)?data.getCaudal (): 0.0) ;
      
                  HSSFCell celda12  = fila.createCell((short)12);
                  celda12.setCellValue((data.getNumverticales()!=null)?data.getNumverticales(): 0) ;
      
                  HSSFCell celda13  = fila.createCell((short)13);
                  celda13.setCellValue((data.getIntervalotiempo()!=null)?data.getIntervalotiempo(): 0.0) ;
      
                  HSSFCell celda14  = fila.createCell((short)14);
                  celda14.setCellValue((data.getDuracionmuestreo()!=null)?data.getDuracionmuestreo(): 0.0) ;
      
                  HSSFCell celda15  = fila.createCell((short)15);
                  celda15.setCellValue((data.getNumsubmuestras()!=null)?data.getNumsubmuestras(): 0) ;
      
                  HSSFCell celda16  = fila.createCell((short)16);
                  celda16.setCellValue((data.getParametro()!=null)?data.getParametro(): "  ") ;
      
                  HSSFCell celda17  = fila.createCell((short)17);
                  celda17.setCellValue((data.getSigno()!=null)?data.getSigno(): "  ") ;
      
                  HSSFCell celda18  = fila.createCell((short)18);
                  celda18.setCellValue((data.getUnidadmedida()!=null)?data.getUnidadmedida(): "  ") ;
      
                  HSSFCell celda19  = fila.createCell((short)19);
                  celda19.setCellValue((data.getValor()!=null)?data.getValor(): 0.0) ;
      
                  HSSFCell celda20  = fila.createCell((short)20);
                  celda20.setCellValue((data.getValor2()!=null)?data.getValor2(): 0.0) ;
      
                  HSSFCell celda21  = fila.createCell((short)21);
                  celda21.setCellValue((data.getValorcaracter()!=null)?data.getValorcaracter(): "  ") ;
      
                  HSSFCell celda22  = fila.createCell((short)22);
                  celda22.setCellValue((data.getMetododeterminacion()!=null)?data.getMetododeterminacion(): "  ") ;
      
                  HSSFCell celda23  = fila.createCell((short)23);
                  celda23.setCellValue((data.getLimitedeteccion()!=null)?data.getLimitedeteccion(): 0.0) ;
      
                  HSSFCell celda24  = fila.createCell((short)24);
                  celda24.setCellValue((data.getParametroacreditado()!=null)?data.getParametroacreditado(): "  ") ;
                 
          contFilas++;
          }
    
          libro.write(archivo);
          archivo.close();
          } catch (FileNotFoundException e) {
          System.out.println("ERROR en FileUTil, mensaje: " +
                         e.fillInStackTrace());
          } catch (IOException e) {
          System.out.println("ERROR en FileUTil, mensaje: " +
                         e.fillInStackTrace());
          }
     }

/*********************************************************    
// METODOS CREACION ARCHIVO XLS - SOLO CORPOCALDAS
*********************************************************/

    public static void loadDataFuentesXlsCorpocaldas(List<co.gov.corpocaldas.generated.FntvFuentesTramos> datos) 
            throws FileNotFoundException, IOException {
        try {
            File file = FileUtil.generateXls();
            FileOutputStream archivo = new FileOutputStream(file); 
            
            HSSFWorkbook libro = new HSSFWorkbook();
            HSSFSheet hoja = libro.createSheet(ConstantesImportacion.HOJA_FUENTES);

            short contFilas = (short)0;
            for (co.gov.corpocaldas.generated.FntvFuentesTramos data : datos) {
                
                HSSFRow fila = hoja.createRow(contFilas);
                
                HSSFCell celda0  = fila.createCell((short)0);
                celda0.setCellValue((data.getIdfuente()!=null)?data.getIdfuente(): " ") ;

                HSSFCell celda1  = fila.createCell((short)1);
                celda1.setCellValue((data.getTipofuente()!=null)?data.getTipofuente(): " ") ;

                HSSFCell celda2  = fila.createCell((short)2);
                celda2.setCellValue((data.getNombrefuente()!=null)?data.getNombrefuente(): " ") ;

                HSSFCell celda3  = fila.createCell((short)3);
                celda3.setCellValue((data.getExistebd()!=null)?data.getExistebd(): " ") ;

                HSSFCell celda4  = fila.createCell((short)4);
                celda4.setCellValue((data.getNumcarfuente()!=null)?data.getNumcarfuente(): " ") ;

                HSSFCell celda5  = fila.createCell((short)5);
                celda5.setCellValue((data.getDescripcionfuente()!=null)?data.getDescripcionfuente(): " ") ;

                HSSFCell celda6  = fila.createCell((short)6);
                celda6.setCellValue((data.getNumcartramo()!=null)?data.getNumcartramo(): " ") ;

                HSSFCell celda7  = fila.createCell((short)7);
                celda7.setCellValue((data.getNombretramo()!=null)?data.getNombretramo(): " ") ;

                HSSFCell celda8  = fila.createCell((short)8);
                celda8.setCellValue((data.getDescripciontramo()!=null)?data.getDescripciontramo(): " ") ;

                HSSFCell celda9  = fila.createCell((short)9);
//                celda9.setCellValue((data.getLongitud()!=null)?data.getLongitud(): 0.0) ;
                celda9.setCellValue(data.getLongitud()) ;
                HSSFCell celda10  = fila.createCell((short)10);
                celda10.setCellValue((data.getTipoflujo()!=null)?data.getTipoflujo(): " ") ;

                HSSFCell celda11  = fila.createCell((short)11);
                celda11.setCellValue((data.getArea()!=null)?data.getArea(): " ") ;

                HSSFCell celda12  = fila.createCell((short)12);
                celda12.setCellValue((data.getZona()!=null)?data.getZona(): " ") ;

                HSSFCell celda13  = fila.createCell((short)13);
                celda13.setCellValue((data.getSubzona()!=null)?data.getSubzona(): " ") ;

                HSSFCell celda14  = fila.createCell((short)14);
                celda14.setCellValue((data.getCuenca()!=null)?data.getCuenca(): " ") ;

                HSSFCell celda15  = fila.createCell((short)15);
                celda15.setCellValue((data.getCodigocuenca()!=null)?data.getCodigocuenca(): " ") ;
/*
                HSSFCell celda16  = fila.createCell((short)16);
                celda16.setCellValue((data.getUsos()!=null)?data.getUsos(): " ") ;
*/
                HSSFCell celda17  = fila.createCell((short)17);
                celda17.setCellValue((data.getSistemareferenciapi()!=null)?data.getSistemareferenciapi(): " ") ;

                HSSFCell celda18  = fila.createCell((short)18);
//                celda18.setCellValue((data.getGradoslatpi()!=null)?data.getGradoslatpi (): 0) ;
                celda18.setCellValue(data.getGradoslatpi()) ;
                HSSFCell celda19  = fila.createCell((short)19);
//                celda19.setCellValue((data.getMinutoslatpi ()!=null)?data.getMinutoslatpi (): 0) ;
                celda19.setCellValue(data.getMinutoslatpi()) ;
                HSSFCell celda20  = fila.createCell((short)20);
//                celda20.setCellValue((data.getSegundolatpi()!=null)?data.getSegundolatpi(): 0.0) ;
                celda20.setCellValue(data.getSegundolatpi()) ;
                HSSFCell celda21  = fila.createCell((short)21);
//                celda21.setCellValue((data.getGradoslonpi ()!=null)?data.getGradoslonpi (): 0) ;
                celda21.setCellValue(data.getGradoslonpi()) ;
                HSSFCell celda22  = fila.createCell((short)22);
//                celda22.setCellValue((data.getMinutoslonpi ()!=null)?data.getMinutoslonpi (): 0) ;
                celda22.setCellValue(data.getMinutoslonpi()) ;
                
                HSSFCell celda23  = fila.createCell((short)23);
//                celda23.setCellValue((data.getSegundolonpi()!=null)?data.getSegundolonpi(): 0.0) ;
                celda23.setCellValue(data.getSegundolonpi()) ;
                
                HSSFCell celda24  = fila.createCell((short)24);
//                celda24.setCellValue((data.getAltitudpi()!=null)?data.getAltitudpi(): 0.0) ;
                celda24.setCellValue(data.getAltitudpi()) ;

                HSSFCell celda25  = fila.createCell((short)25);
                celda25.setCellValue((data.getSistemareferenciapf()!=null)?data.getSistemareferenciapf(): " ") ;

                HSSFCell celda26  = fila.createCell((short)26);
//                celda26.setCellValue((data.getGradoslatpf ()!=null)?data.getGradoslatpf (): 0) ;
                celda26.setCellValue(data.getGradoslatpf()) ;

                HSSFCell celda27  = fila.createCell((short)27);
//                celda27.setCellValue((data.getMinutoslatpf ()!=null)?data.getMinutoslatpf (): 0) ;
                celda27.setCellValue(data.getMinutoslatpf ()) ;
                HSSFCell celda28  = fila.createCell((short)28);
                //celda28.setCellValue((data.getSegundolatpf()!=null)?data.getSegundolatpf(): 0.0) ;
                celda28.setCellValue(data.getSegundolatpf()) ;
                
                HSSFCell celda29  = fila.createCell((short)29);
//                celda29.setCellValue((data.getGradoslonpf ()!=null)?data.getGradoslonpf (): 0) ;
                celda29.setCellValue(data.getGradoslonpf()) ;
                
                HSSFCell celda30  = fila.createCell((short)30);
//                celda30.setCellValue((data.getMinutoslonpf ()!=null)?data.getMinutoslonpf (): 0) ;
                celda30.setCellValue(data.getMinutoslonpf()) ;

                HSSFCell celda31  = fila.createCell((short)31);
//                celda31.setCellValue((data.getSegundolonpf()!=null)?data.getSegundolonpf(): 0.0) ;
                celda31.setCellValue(data.getSegundolonpf()) ;

                HSSFCell celda32  = fila.createCell((short)32);
//                celda32.setCellValue((data.getAltitudpf()!=null)?data.getAltitudpf(): 0.0) ;
                celda32.setCellValue(data.getAltitudpf()) ;
                
                contFilas++;
            }

            libro.write(archivo);
            archivo.close();
        } catch (FileNotFoundException e) {
            System.out.println("ERROR en FileUTil, mensaje: " +
                               e.fillInStackTrace());
        } catch (IOException e) {
            System.out.println("ERROR en FileUTil, mensaje: " +
                               e.fillInStackTrace());
        }
    }        
      
    
}