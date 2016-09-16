package co.gov.ideam.sirh.seguridad.view;

import co.gov.ideam.sirh.seguridad.view.util.UsuarioComparator;
import co.gov.ideam.sirh.util.IdeamException;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;

import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.faces.component.UIComponent;

import oracle.adf.view.rich.component.rich.data.RichColumn;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaClass;

import org.apache.commons.beanutils.DynaProperty;

import org.apache.commons.beanutils.LazyDynaBean;
import org.apache.commons.beanutils.LazyDynaClass;
import org.apache.commons.beanutils.MutableDynaClass;

import org.dom4j.DocumentException;
/**
 * Permite generar un reporte PDF con los usuarios que se muestran en el
 * Bean de usuarios, no muestra todos los usuarios registrados en el sistema
 * sino unicamente aquellos que se han filtrado en la tabla del bean de usuarios
 */
public class UsuariosReport implements ReporteInteface{
    
    private static final String reportName = "usuarios.pdf";
    private String tituloReporte = "Listado de Usuarios";
    
    public UsuariosReport(){
        this.tituloReporte = "Listado de Usuarios";
    }
    public UsuariosReport(String titulo){
        this.tituloReporte = titulo;
    }
    /**
     * Lee un pdf y retora el contenido del mismo en bytes para ser
     * mostrado en una pagina al usuario
     * @param dynaClass
     * @param session
     * @return
     * @throws IdeamException
     */        
    public byte[] createReport(List usuarios, DynaClass dynaClass, String session)throws IdeamException{
        String nombreReporte = session + "_" + reportName;
        this.createDocument(usuarios, dynaClass, nombreReporte);
        // Leer el archivo enviar el bye[]
        File archivoReporte = new File(nombreReporte);
        
        try {
            FileInputStream fr = new FileInputStream(archivoReporte);
            byte contenido[] = new byte[fr.available()];
            fr.read(contenido);
            archivoReporte.delete();
            return contenido;
        } catch (FileNotFoundException e) {
            throw new IdeamException(e);
        } catch (IOException e) {
            throw new IdeamException(e);
        }
    }
    
    private void createDocument(List usuarios, DynaClass dynaClass,  String reportName)throws IdeamException{
        try{                                    
            File archivoReporte = new File(reportName);            
            if (!archivoReporte.exists()){
                archivoReporte.createNewFile();
                archivoReporte.deleteOnExit();
            }
            java.io.FileOutputStream fos = new FileOutputStream(archivoReporte);                 
            Document documento = new Document(PageSize.LETTER.rotate(), 20,20,20,20);            
            PdfWriter pdf = PdfWriter.getInstance(documento, fos);            
            pdf.setPageEvent(new StartPage("Modulo de Seguridad",tituloReporte));
            pdf.setPageEvent(new EndPage(""));
            documento.addTitle("IDEAM");
            documento.addAuthor("Software Fenix");
            documento.open();
            
            PdfContentByte cb = pdf.getDirectContent ();   
            
            Font fontNegrita = FontFactory.getFont("Tahoma",10f);
            fontNegrita.setStyle(Font.BOLD);
    
            Font font = FontFactory.getFont("Tahoma",10f);
            
            PdfPTable table = new PdfPTable(dynaClass.getDynaProperties().length);
            table.setWidthPercentage(100);
            //table.setTotalWidth(ancho);
            
            // Adicionar titulos
            DynaProperty columnas[] = dynaClass.getDynaProperties();
            for (int i=0; i<columnas.length; i++){
                String nombre = columnas[i].getName();
                String titulo = "";
                if (nombre.equalsIgnoreCase("login")){
                    titulo = "Login";
                }else if (nombre.equalsIgnoreCase("nombres")){
                    titulo = "Nombres";
                }else if (nombre.equalsIgnoreCase("apellidos")){
                    titulo = "Apellidos";
                }else if (nombre.equalsIgnoreCase("email")){
                    titulo = "Email";
                }else if (nombre.equalsIgnoreCase("activo")){
                    titulo = "Activo";
                }else if (nombre.equalsIgnoreCase("ultimoIngresoExitoso")){
                    titulo = "Ultimo Ingreso Exitoso";
                }else if (nombre.equalsIgnoreCase("ultimoIngresoNoExitoso")){
                    titulo = "Ultimo Ingreso No Exitoso";
                }
                table.addCell(new Paragraph(titulo, fontNegrita));
            }
            
            Iterator it = usuarios.iterator();
            while(it.hasNext()){
                DynaBean bean = (DynaBean)it.next(); 
                for (int i=0; i<columnas.length; i++){
                    String nombre = columnas[i].getName();
                    table.addCell(new Paragraph(bean.get(nombre).toString(), fontNegrita));
                }                
            }
            documento.add(table);
            documento.close();
            fos.close();
        }catch(FileNotFoundException e){
            throw new IdeamException(e);
        }catch(IOException e){
            throw new IdeamException(e);
        }catch(Exception e){
            throw new IdeamException(e);
        }        
    
    }            
}
