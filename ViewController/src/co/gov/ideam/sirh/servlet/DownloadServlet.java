package co.gov.ideam.sirh.servlet;

import co.gov.ideam.sirh.documentos.model.Archivo;
import co.gov.ideam.sirh.documentos.model.Nodo;
import co.gov.ideam.sirh.documentos.view.DocumentosDelegate;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.faces.context.FacesContext;

import javax.servlet.*;
import javax.servlet.http.*;
/**
 * Atiende las peticiones para la generacion de reportes en diferentes
 * formatos
 */
public class DownloadServlet extends HttpServlet {
    private static final String CONTENT_TYPE =  "text/html; charset=windows-1252";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    /**
     * Captura la petici?n y la direcciona al doGenerateReport.
     *
     * @param request Petici?n al Servlet.
     * @param response Objeto que encapsula la respuesta del servlet.
     *
     * @throws ServletException Excepci&oacute;n general lanzada cuando existe un problema en el servlet.
     * @throws IOException Indica que se ha producido algun tipo de Excepci&oacute;n  de tipo E / S.
     */
    public void service(ServletRequest request,
                        ServletResponse response) throws ServletException,
                                                         IOException {
        try {
            
            String downloadNode = request.getParameter("nodoId");
            String showImage = request.getParameter("showImage");
            String showReport = request.getParameter("showReport");
            
            if(showImage!=null && showImage.equalsIgnoreCase("1")){
                this.showImage((HttpServletRequest)request, (HttpServletResponse)response);
            }else if (showReport != null && showReport.equalsIgnoreCase("pdf")) {
                this.showPdfReport((HttpServletRequest)request, (HttpServletResponse)response);
            }else if (showReport != null && showReport.equalsIgnoreCase("xls")) {
                this.showExcelReport((HttpServletRequest)request, (HttpServletResponse)response);
            }else if(downloadNode != null){
                this.downloadFile((HttpServletRequest)request, (HttpServletResponse)response);
            }
            FacesContext context = FacesContext.getCurrentInstance();
            if (context instanceof FacesContext) {
                context.responseComplete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Muestra un reporte excel cuyo contenido en bytes debe estar en la session
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void showExcelReport(HttpServletRequest request,
                               HttpServletResponse response) throws ServletException,
                                                                    IOException {

        String name = (String)request.getSession().getAttribute("report-name");
        byte reporte[] =
            (byte[])request.getSession().getAttribute("report-byte");
        if (name == null) {
            name = "reporte.xls";
        }
        if (reporte == null) {
            throw new ServletException("report-byte is null");
        }

        response.setHeader("Expires", "0");
        response.setHeader("Cache-Control",
                           "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma", "public");
        response.setContentType("application/vnd.ms-excel");
        response.addHeader("Content-disposition",
                           "filename=\"" + name + ".xls\"");

        ServletOutputStream ouputStream = response.getOutputStream();
        ouputStream.write(reporte);
        ouputStream.flush();
        ouputStream.close();

        request.getSession().removeAttribute("report-name");
        request.getSession().removeAttribute("report-byte");
    }
    /**
     * Muestra un reporte pdf cuyo contenido en bytes debe estar en la session
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void showPdfReport(HttpServletRequest request,
                               HttpServletResponse response) throws ServletException,
                                                                    IOException {

        String name = (String)request.getSession().getAttribute("report-name");
        byte reporte[] =
            (byte[])request.getSession().getAttribute("report-byte");
        if (name == null) {
            name = "reporte.pdf";
        }
        if (reporte == null) {
            throw new ServletException("report-byte is null");
        }

        response.setHeader("Expires", "0");
        response.setHeader("Cache-Control",
                           "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma", "public");
        response.setContentType("application/pdf");
        response.addHeader("Content-disposition",
                           "filename=\"" + name + ".pdf\"");

        ServletOutputStream ouputStream = response.getOutputStream();
        ouputStream.write(reporte);
        ouputStream.flush();
        ouputStream.close();

        request.getSession().removeAttribute("report-name");
        request.getSession().removeAttribute("report-byte");
    }
    
    /**
     * Muestra una imagen cuyo contenido en bytes debe estar en la session
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void showImage(HttpServletRequest request,
                               HttpServletResponse response) throws ServletException,
                                                                    IOException {
        
        String imageName = "content-img";
       // if (request.getParameter("code")!=null){
       //     imageName += "-" + request.getParameter("code");
       // }        
        byte img[] =
            (byte[])request.getSession().getAttribute(imageName);
        if (img == null) {
            throw new ServletException("report-byte is null");
        }
        
        response.setHeader("Expires", "0");
        response.setHeader("Cache-Control",
                           "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma", "public");
        response.setContentType("image/jpg");
        response.addHeader("Content-disposition","filename=\"img.jpg\"");

        ServletOutputStream ouputStream = response.getOutputStream();
        ouputStream.write(img);
        ouputStream.flush();
        ouputStream.close();

        request.getSession().removeAttribute("content-img");
    }
    
    
    
    
    
    
    
    
    /**
     * Descarga un archivo del Modulo de Documentos con el identificador del Nodo 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void downloadFile(HttpServletRequest request, HttpServletResponse response) throws ServletException,
                                                                              IOException,                              
                                                                              IdeamException {
        
        //Linea activa para pruebas directas por paso de parametros, debe ser por sesion
        if(   request.getParameter("nodoId") != null
          ){
                                                                 
            request.getSession().setAttribute("nodoId", request.getParameter("nodoId"));
            request.getSession().setAttribute("displayInline", request.getParameter("displayInline"));
        }
              
        DocumentosDelegate pomcad = DocumentosDelegate.getInstance();   
        String idArchivo =  (String) request.getSession().getAttribute("nodoId");
        String displayInline = (String) request.getSession().getAttribute("displayInline");
        if(idArchivo != null&&!idArchivo.equals("")){
            
            Nodo nodo = pomcad.buscarNodo(new Long(idArchivo));
          
            if(nodo !=null){
                Archivo aux = pomcad.descargarArchivo(nodo);
                
                response.setContentType(nodo.getMime());
                response.setContentLength(aux.getArchivo().length);
                
                if(displayInline == null || displayInline.equalsIgnoreCase("false")){
                    response.setHeader( "Content-Disposition", "attachment; filename=\"" + nodo.getDescripcion() + "\"" );
                }else{
                    response.setHeader( "Content-Disposition", "inline; filename=\"" + nodo.getDescripcion() + "\"" );
                }
                
                
                System.out.println(">>>>>>> Descargando Archivo...: "+idArchivo+ " - "+nodo.getDescripcion());
                ServletOutputStream ouputStream = response.getOutputStream();
                ouputStream.write(aux.getArchivo());
                ouputStream.flush();
                ouputStream.close();
           }
            request.getSession().setAttribute("nodoId", null);
            request.getSession().setAttribute("displayInline", null);
        } 
        
       
        /*
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control",  "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            //response.setContentType("application/pdf");
            //response.addHeader("Content-disposition","filename=\"" + name + ".pdf\"");
        */
    }
       
}