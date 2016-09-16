package co.gov.ideam.sirh.servlet;

import co.gov.ideam.sirh.oferta.model.PartDatosIdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.DefaultFontMapper;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import java.awt.Color;
import java.awt.Graphics2D;

import java.awt.geom.Rectangle2D;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import java.io.FileInputStream;

import java.io.OutputStream;

import java.text.DateFormat;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import java.util.Locale;

import javax.faces.context.FacesContext;

import javax.servlet.*;
import javax.servlet.http.*;

import oracle.dss.dataView.Subtitle;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleEdge;

public class ReporteIDF extends HttpServlet {
  private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

  public void init(ServletConfig config) throws ServletException {
    super.init(config);
  }

  public void doGet(HttpServletRequest request,
                    HttpServletResponse response) throws ServletException,
                                                         IOException {
    processRequest(request, response);
  }

  public void doPost(HttpServletRequest request,
                     HttpServletResponse response) throws ServletException,
                                                          IOException {
    processRequest(request, response);
  }
  
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
             throws ServletException, IOException {
               String pathToWeb = getServletContext().getRealPath(File.separator);
               pathToWeb += "/imgs/logoIdeam.jpg";
               //System.out.println("PATH: " +pathToWeb);
               response.setContentType("application/pdf");
                      //Get the output stream for writing PDF object        
                      OutputStream out=response.getOutputStream();
                      
                      try {
                        if (request.getSession().getAttribute("datos")!= null){
                          List<PartDatosIdf> lsDatos = (List<PartDatosIdf>) request.getSession().getAttribute("datos");
                          Document document = new Document(PageSize.LETTER,50, 50, 50, 50);
                          /* Basic PDF Creation inside servlet */
                          PdfWriter writer = PdfWriter.getInstance(document, out);
                          document.open();
                          document.setMargins(30, 50, 90, 50);                     
                              PdfPTable tableCabecera=new PdfPTable(3);
                          tableCabecera.setWidthPercentage(95f);
                          tableCabecera.setSpacingBefore(35f);
                          int[] tamano = {25,80,35};
                          tableCabecera.setWidths(tamano);
                          tableCabecera.setHorizontalAlignment(tableCabecera.ALIGN_CENTER);
                          Image foto = Image.getInstance(pathToWeb);
                          foto.scaleToFit(130, 130);
                          foto.setAlignment(Chunk.ALIGN_LEFT);
                          PdfPCell cell = new PdfPCell(foto);
                          cell.setBorderColor(BaseColor.WHITE);
                          cell.setBorderWidth(5f);
                          cell.setHorizontalAlignment(cell.ALIGN_LEFT);
                          cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
                          tableCabecera.addCell(cell);
                          cell = new PdfPCell(new Paragraph("Instituto de Hidrología,\n Meteorología y Estudios\n Ambientales de Colombia\n \n" +
                            "Sistema de Información del Recurso Hídrico \n Curva Intensidad Duración Frecuencia" , 
                                                            new Font(Font.FontFamily.TIMES_ROMAN , 14, Font.BOLD,  new BaseColor(62, 161, 177))));   
                          cell.setBorderColor(BaseColor.WHITE);
                          cell.setBorderWidth(5f);
                          cell.setHorizontalAlignment(cell.ALIGN_CENTER);
                          cell.setVerticalAlignment(cell.ALIGN_TOP);
                          tableCabecera.addCell(cell);
                          
                          cell = new PdfPCell(new Paragraph("NIT. 830.000.602-5\n Calle 25D N.96B-70,\n Tel. (+57)(1)3 52 71 60 \n\nwww.ideam.gov.co" , 
                                                              new Font(Font.FontFamily.TIMES_ROMAN , 11, Font.BOLD,  new BaseColor(62, 161, 177))));   
                            
                          cell.setBorderColor(BaseColor.WHITE);
                          cell.setBorderWidth(5f);
                          cell.setHorizontalAlignment(cell.ALIGN_CENTER);
                          cell.setVerticalAlignment(cell.ALIGN_TOP);
                          tableCabecera.addCell(cell);
                          
                          document.add(tableCabecera);
                          document.add(new Paragraph(" "));
                          PartDatosIdf p1 = lsDatos.get(0);                           
                          document.add(new Paragraph("Nombre Estación: " + p1.getNombreEs() + " Código: " + p1.getEstacionId() , new Font(Font.FontFamily.TIMES_ROMAN, 12,
                                          Font.BOLD)));
                            document.add(new Paragraph("Coordenadas = Longitud: " + p1.getLongitud() + " - Latitud: " + p1.getLatitud() , new Font(Font.FontFamily.TIMES_ROMAN, 12,
                                            Font.BOLD)));
                          document.add(new Paragraph("Años: " + p1.getAnios()));
                          PdfPTable table=new PdfPTable(6); 
                          int arreglo[];
                                arreglo = new int[6];
                                arreglo[0] = 5;
                                arreglo[1] = 10;
                                arreglo[2] = 8;
                                arreglo[3] = 8;
                                arreglo[4] = 10;
                                arreglo[5] = 10;
                            table.setWidths(arreglo);
                            table.setHorizontalAlignment(table.ALIGN_CENTER);
                            table.addCell(new Paragraph("Tr", new Font(Font.FontFamily.TIMES_ROMAN, 11,
                                                Font.BOLD)));
                            table.addCell(new Paragraph("C1", new Font(Font.FontFamily.TIMES_ROMAN, 11,
                                                Font.BOLD)));
                            table.addCell(new Paragraph("X0", new Font(Font.FontFamily.TIMES_ROMAN, 11,
                                              Font.BOLD)));
                            table.addCell(new Paragraph("C2", new Font(Font.FontFamily.TIMES_ROMAN, 11,
                                              Font.BOLD)));
                            table.addCell(new Paragraph("Método", new Font(Font.FontFamily.TIMES_ROMAN, 11,
                                              Font.BOLD)));
                            table.addCell(new Paragraph("Distribución", new Font(Font.FontFamily.TIMES_ROMAN, 11,
                                              Font.BOLD)));
                            for (PartDatosIdf p : lsDatos){
                              table.addCell(new Paragraph(""+p.getTr(),           new Font(Font.FontFamily.TIMES_ROMAN, 10)));
                              table.addCell(new Paragraph(""+p.getC1(),           new Font(Font.FontFamily.TIMES_ROMAN, 10)));
                              table.addCell(new Paragraph(""+p.getX0(),           new Font(Font.FontFamily.TIMES_ROMAN, 10)));
                              table.addCell(new Paragraph(""+p.getC2(),           new Font(Font.FontFamily.TIMES_ROMAN, 10)));
                              table.addCell(new Paragraph(""+p.getMetodo(),       new Font(Font.FontFamily.TIMES_ROMAN, 10)));
                              table.addCell(new Paragraph(""+p.getDitribucion(), new Font(Font.FontFamily.TIMES_ROMAN, 10)));
                              }
                            document.add(table);
                           // document.add(new Chunk(new LineSeparator()));
                            //document.newPage();
                            DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();
                              for (PartDatosIdf p : lsDatos){
                                for (int x = 0; x<13; x++){
                                  int d = x*30;
                                  float c1 = p.getC1().floatValue();
                                  double y = (d+p.getX0().floatValue());
                                  double c2 = p.getC2().floatValue();
                                  double pow = Math.pow(y,c2);
                                  double i = c1*(pow);
                                  line_chart_dataset.addValue( i , ""+p.getTr() , ""+d );
                                }                                
                              }
                            JFreeChart lineChartObject = ChartFactory.createLineChart(
                                     "","Duración (min)",
                                     "Intensidad (mm/h)",
                                     line_chart_dataset,PlotOrientation.VERTICAL,
                                     true,true,false);                            
                                  final CategoryPlot plot = (CategoryPlot) lineChartObject.getPlot();
                                    plot.setBackgroundPaint(Color.white);
                                    plot.setRangeGridlinePaint(Color.lightGray);
                                  int width = 550; /* Width of the image */
                                  int height = 350; /* Height of the image */
                                  TextTitle legendText = new TextTitle("Periodo de Retorno (Años)");
                                  legendText.setPosition(RectangleEdge.BOTTOM);                            
                                  lineChartObject.addSubtitle(legendText);                            
                                  PdfContentByte contentByte = writer.getDirectContent();
                                  PdfTemplate template = contentByte.createTemplate(width, height);
                                  Graphics2D graphics2d = template.createGraphics(width, height,new DefaultFontMapper());
                                  Rectangle2D rectangle2d = new Rectangle2D.Double(0, 0, width, height);
                                  lineChartObject.draw(graphics2d, rectangle2d);
                                  graphics2d.dispose();
                                  contentByte.addTemplate(template, 50, 50);
                                  Locale espanol = new Locale("es","ES");
                                  DateFormat df2 = new SimpleDateFormat("MMM-dd-yyyy-hh:mm:ss", espanol);
                                  String fechArch = df2.format(new Date());
                                  Phrase footer = new Phrase("Fecha de Generación: " + fechArch, new Font(Font.FontFamily.TIMES_ROMAN , 12, Font.BOLD,  new BaseColor(62, 161, 177)));
                                  ColumnText.showTextAligned(contentByte, Element.ALIGN_LEFT,
                                               footer,
                                               (document.right() - document.left()) / 2 + document.leftMargin(),
                                               document.bottom() - 10, 0);
                                 // document.add(new Paragraph("" , new Font(Font.FontFamily.TIMES_ROMAN, 12,
                                   //               Font.BOLD)));
                            document.newPage();
                            document.close();
                          }
                        } catch (DocumentException exc){
                              throw new IOException(exc.getMessage());
                        } finally {            
                          out.close();
                        }                      
             } 
}
