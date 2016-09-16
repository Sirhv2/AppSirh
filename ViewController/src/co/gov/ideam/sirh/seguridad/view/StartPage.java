package co.gov.ideam.sirh.seguridad.view;

import co.gov.ideam.sirh.util.IdeamException;

import co.gov.ideam.sirh.view.FacesUtils;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;

import java.io.IOException;
/**
 * Clase encragad de generar un encabezado estandar para tpdos los
 * reportes que se generan directamente a traves de iText
 */
public class StartPage extends PdfPageEventHelper{
    private String modulo;
    private String nombreReporte;
    
    public StartPage(String modulo, String nombreReporte){
        this.modulo = modulo;
        this.nombreReporte = nombreReporte;
    }
    
    public void onStartPage(PdfWriter writer, Document document){
        try{
            float anchoColumna = PageSize.LETTER.getWidth() / 3;                
            float ancho[] = {anchoColumna*0.25f, anchoColumna*0.50f, anchoColumna*0.25f};
            
            Font font = FontFactory.getFont("Arial",12f);            
            font.setColor(29,146,179);
            font.setStyle(Font.BOLD);
            
            PdfPTable table = new PdfPTable(3);
            table.getDefaultCell().setBorder(0);
            table.setWidthPercentage(100);
            table.setTotalWidth(ancho);
                                    
            Image imgLogo = getLogo();
            imgLogo.scalePercent(50f);            
            PdfPCell celdaLogo = new PdfPCell(new Phrase(new Chunk(imgLogo, 0, 0)));    
            celdaLogo.setBorder(0);
            celdaLogo.setHorizontalAlignment(Element.ALIGN_CENTER);
            celdaLogo.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(celdaLogo);
            
            String titulo = "IDEAM\nSIRH\n\n"+
                            modulo +  "\n\n" + nombreReporte;
            PdfPCell celdaCentro = new PdfPCell(new Paragraph(titulo, font));        
            celdaCentro.setBorder(0);
            celdaCentro.setHorizontalAlignment(Element.ALIGN_CENTER);
            celdaCentro.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(celdaCentro);
            
            table.addCell(getContactInformation());

            PdfPCell celdaBlanco = new PdfPCell(new Phrase("  "));
            celdaBlanco.setColspan(3);
            celdaBlanco.setBorder(0);
            
            table.addCell(celdaBlanco); 
            
            //table.writeSelectedRows(0, -1, document.leftMargin(), PageSize.LETTER.height() - document.topMargin() ,  writer.getDirectContent());            
            document.add(table);

        }catch(DocumentException e){
            System.out.println(e.getMessage());
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    private static PdfPTable getContactInformation(){
        PdfPTable table = new PdfPTable(1);
        
        table.getDefaultCell().setBorder(0);
        
        Font f = FontFactory.getFont("Arial",10f);
        f.setColor(29,146,179);
        f.setStyle(Font.BOLD);
        
        Paragraph p = new Paragraph("NIT. 830.000.602-5",f );        
        table.addCell(p);
        
        p = new Paragraph("Cr 10 N 20 - 30, Bogot\u00E1 D.C.",f );        
        table.addCell(p);

        p = new Paragraph("Tel. (+57)(1)3 52 71 60",f );        
        table.addCell(p);

        p = new Paragraph("www.ideam.gov.co",f );        
        table.addCell(p);

        return table;
    }

    public static Image getLogo()throws IOException, BadElementException{
        Image imgLogo = Image.getInstance(FacesUtils.getUrl() + "/imgs/logoIdeam.jpg");
        return imgLogo;
    }

}

