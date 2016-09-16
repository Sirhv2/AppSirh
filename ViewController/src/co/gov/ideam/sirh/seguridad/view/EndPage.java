package co.gov.ideam.sirh.seguridad.view;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;
/**
 * Utilziado para mostrar un pie de pagina generico para todos los
 * reportes que se generan directamente desde iText
 */
public class EndPage extends PdfPageEventHelper{
    private String nombreDocumento;
    public EndPage(String nombre) {
        this.nombreDocumento = nombre;
    }
    
    public void onEndPage(PdfWriter writer, Document document) {
        PdfContentByte cb = writer.getDirectContent();
        try{
                        
            BaseFont bf;
            bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.NOT_EMBEDDED);
            
            float len = bf.getWidthPoint(this.nombreDocumento, 8);
            cb.beginText();
            cb.setFontAndSize(bf, 8);
            cb.setTextMatrix(500, 30);
            cb.showText(this.nombreDocumento);
            cb.endText();
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }
}

