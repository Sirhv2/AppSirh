package co.gov.ideam.sirh.servlet;

import co.gov.ideam.sirh.archivos.business.ArchivosEJB;
import co.gov.ideam.sirh.auditoria.business.AuditoriaEJB;
import co.gov.ideam.sirh.oferta.business.EstacionEJB;
import co.gov.ideam.sirh.business.ReporteEJB;
import co.gov.ideam.sirh.calidad.business.CalidadEJB;
//import co.gov.ideam.sirh.calidad.business.ReportesEJB;
import co.gov.ideam.sirh.datos.referencia.business.DatosReferenciaEJB;
import co.gov.ideam.sirh.directorio.business.DirectorioEJB;
import co.gov.ideam.sirh.documentos.business.DocumentoEJB;
import co.gov.ideam.sirh.fuentes.business.FuentesEJB;
import co.gov.ideam.sirh.parametros.business.ParametrosEJB;
import co.gov.ideam.sirh.pomca.business.PomcaEJB;
import co.gov.ideam.sirh.porh.business.PorhEJB;
import co.gov.ideam.sirh.publicaciones.business.PublicacionEJB;
import co.gov.ideam.sirh.documentos.business.DocumentoEJB;
import co.gov.ideam.sirh.seguridad.business.SeguridadEJB;
import co.gov.ideam.sirh.usuarios.agua.business.UsuariosAguaEJB;

import java.io.IOException;
import java.io.PrintWriter;

import org.apache.log4j.Logger;

import javax.ejb.EJB;

import javax.ejb.EJBs;

import javax.naming.Context;

import javax.naming.InitialContext;

import javax.servlet.*;
import javax.servlet.http.*;
import co.gov.ideam.sirh.auditoria.business.NovTransmitirEJB;
import co.gov.ideam.sirh.calidad.business.ReportesEJB;
import co.gov.ideam.sirh.datosena.business.DatosEnaEJB;
import co.gov.ideam.sirh.datossinc.business.DatosSincEJB;
import co.gov.ideam.sirh.pueaa.business.PueaaEJB;
import co.gov.ideam.sirh.red.ideam.business.RedMonitoreoEJB;

public class ServletLocator extends HttpServlet {
    
    static Logger logger = Logger.getLogger(ServletLocator.class);
    
     private static SeguridadEJB seguridadService;   
     private static ReporteEJB reporteService;
     private static ParametrosEJB paametrosService;
     private static UsuariosAguaEJB usuariosAguaService;
     private static FuentesEJB fuenteService;
     private static CalidadEJB calidadService;
     private static ReportesEJB reportesService;
     private static ArchivosEJB archivosService;
     private static PorhEJB porhService;
     private static PomcaEJB pomcaService; 
     private static DirectorioEJB directorioService;
     private static DocumentoEJB documentoService;
     private static PublicacionEJB publicacionesService;
     private static AuditoriaEJB auditoriaService;
     private static EstacionEJB ofertaService;
     private static DatosReferenciaEJB datosReferenciaService;
     private static NovTransmitirEJB novTransmitirService; 
     private static RedMonitoreoEJB redMonitoreoService;
     private static DatosEnaEJB datosenaService;
     private static DatosSincEJB datosincService;
     private static PueaaEJB pueaaService;
    
    
/*    @EJB private static SeguridadEJB seguridadService;   
    @EJB private static ReporteEJB reporteService;
    @EJB private static ParametrosEJB paametrosService;
    @EJB private static UsuariosAguaEJB usuariosAguaService;
    @EJB private static FuentesEJB fuenteService;
    @EJB private static CalidadEJB calidadService;
    @EJB private static ArchivosEJB archivosService;
    @EJB private static PorhEJB porhService;
    @EJB private static PomcaEJB pomcaService; 
    @EJB private static DirectorioEJB directorioService; */
     

/*    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
    public static UsuariosAguaEJB getUsuariosAguaService(){
        return usuariosAguaService;
    }
    
    public static SeguridadEJB getSeguridadService(){
        return seguridadService;
    }
    public static ReporteEJB getReporteService(){        
        return reporteService;        
    }
    public static ParametrosEJB getParametrosService(){
        return paametrosService;
    }
    public static FuentesEJB getFuentesService(){
        return fuenteService;
    }
    public static CalidadEJB getCalidadService() {
        return calidadService;
    }
    public static ArchivosEJB getArchivosService() {
        return archivosService;
    }    
    public static PorhEJB getPorhService(){
        return porhService;
    }

    public static PomcaEJB getPomcaService() {
        return pomcaService;
    }
    
    public static DirectorioEJB getDirectorioService(){
        return directorioService;
    }
*/
    public void init(ServletConfig config)  {
        try {
            super.init(config);
        } catch (ServletException e) {
            logger.info("error en init:" + e.getMessage() + "-" + e.getStackTrace() + "-" + e.getLocalizedMessage());
        }
    }
    public static UsuariosAguaEJB getUsuariosAguaService(){
        if ( usuariosAguaService == null )
            try {
                Context ctx = new InitialContext();
                Object obj = ctx.lookup("java:app/Ideam-Ejb/UsuariosAgua");        
                usuariosAguaService = (UsuariosAguaEJB)obj;
            } catch (Exception e) {
                
                logger.info("Fallo obteniendo ejb: " + e.getMessage());
            }        
        return usuariosAguaService;
    }
    
    public static SeguridadEJB getSeguridadService(){
        if ( seguridadService == null )
            
            try {
                Context ctx = new InitialContext();
                Object obj = ctx.lookup("java:app/Ideam-Ejb/SeguridadBean");        
                seguridadService = (SeguridadEJB)obj;
            } catch (Exception e) {
                
                logger.info("Fallo obteniendo ejb: " + e.getMessage());
            }        


        return seguridadService;
    }
    public static ReporteEJB getReporteService(){        
        if ( reporteService == null )
            
            try {
                Context ctx = new InitialContext();
                Object obj = ctx.lookup("java:app/Ideam-Ejb/ReporteBean");        
                reporteService = (ReporteEJB)obj;
            } catch (Exception e) {
                
                logger.info("Fallo obteniendo ejb: " + e.getMessage());
            }        

        return reporteService;        
    }
    public static ParametrosEJB getParametrosService(){
        if ( paametrosService == null )
            
            try {
                Context ctx = new InitialContext();
                Object obj = ctx.lookup("java:app/Ideam-Ejb/ParametrosEJB");        
                paametrosService = (ParametrosEJB)obj;
            } catch (Exception e) {
                
                logger.info("Fallo obteniendo ejb: " + e.getMessage());
            }        
        return paametrosService;
    }
    public static FuentesEJB getFuentesService(){
        if ( fuenteService == null )
            
            try {
                Context ctx = new InitialContext();
                Object obj = ctx.lookup("java:app/Ideam-Ejb/FuentesEJB");        
                fuenteService = (FuentesEJB)obj;
            } catch (Exception e) {
                
                logger.info("Fallo obteniendo ejb: " + e.getMessage());
            }        
        
        
        return fuenteService;
    }
    
    public static CalidadEJB getCalidadService() {
        if ( calidadService == null )
            
            try {
                Context ctx = new InitialContext();
                Object obj = ctx.lookup("java:app/Ideam-Ejb/CalidadEJB");        
                calidadService = (CalidadEJB)obj;
            } catch (Exception e) {
                
                logger.info("Fallo obteniendo ejb: " + e.getMessage());
            }        
        return calidadService;
    }
    
  public static ReportesEJB getReportesService() {
      if ( reportesService == null )
          
          try {
              Context ctx = new InitialContext();
              Object obj = ctx.lookup("java:app/Ideam-Ejb/ReportesEJB");        
              reportesService = (ReportesEJB)obj;
          } catch (Exception e) {
              
              logger.info("Fallo obteniendo ejb DE REPORTES EJB Clase: servletLocator : " + e.getMessage());
          }        
      return reportesService;
  }
    
    
    public static ArchivosEJB getArchivosService() {
        if ( archivosService == null )
            
            try {
                Context ctx = new InitialContext();
                Object obj = ctx.lookup("java:app/Ideam-Ejb/ArchivosEJB");        
                archivosService = (ArchivosEJB)obj;
            } catch (Exception e) {
                
                logger.info("Fallo obteniendo ejb: " + e.getMessage());
            }        
        return archivosService;
    }    
    public static PorhEJB getPorhService(){
        if ( porhService == null )
            
            try {
                Context ctx = new InitialContext();
                Object obj = ctx.lookup("java:app/Ideam-Ejb/PorhEJB");        
                porhService = (PorhEJB)obj;
            } catch (Exception e) {
                System.out.println("Fallo obteniendo ejb PorhEJB: " + e.getMessage());
                logger.info("Fallo obteniendo ejb: " + e.getMessage());
            }        
        return porhService;
    }
    
    public static PomcaEJB getPomcaService(){
        if ( pomcaService == null )
            
            try {
                Context ctx = new InitialContext();
                Object obj = ctx.lookup("java:app/Ideam-Ejb/PomcaEJB");        
                pomcaService = (PomcaEJB)obj;
            } catch (Exception e) {
                
                logger.info("Fallo obteniendo ejb: " + e.getMessage());
            }        
        return pomcaService;
    }

    public static DirectorioEJB getDirectorioService(){
        if ( directorioService == null )
            
            try {
                Context ctx = new InitialContext();
                Object obj = ctx.lookup("java:app/Ideam-Ejb/DirectorioEJB");        
                directorioService = (DirectorioEJB)obj;
            } catch (Exception e) {
                
                logger.info("Fallo obteniendo ejb: " + e.getMessage());
            }        
        return directorioService;
    }
     
    public static DocumentoEJB getDocumentoService(){
        if ( documentoService == null )
            
            try {
                Context ctx = new InitialContext();
                Object obj = ctx.lookup("java:app/Ideam-Ejb/DocumentoEJB");        
                documentoService = (DocumentoEJB)obj;
            } catch (Exception e) {
                
                logger.info("Fallo obteniendo ejb: " + e.getMessage());
            }        
        return documentoService;
    }

    public static PublicacionEJB getPublicacionesService(){
        if ( publicacionesService == null )
            
            try {
                Context ctx = new InitialContext();
                Object obj = ctx.lookup("java:app/Ideam-Ejb/PublicacionEJB");        
                publicacionesService = (PublicacionEJB)obj;
            } catch (Exception e) {
                
                logger.info("Fallo obteniendo ejb: " + e.getMessage());
            }        
        return publicacionesService;
    }
    public static AuditoriaEJB getAuditoriaService(){
        if ( auditoriaService == null )
            
            try {
                Context ctx = new InitialContext();
                Object obj = ctx.lookup("java:app/Ideam-Ejb/AuditoriaEJB");        
                auditoriaService = (AuditoriaEJB)obj;
            } catch (Exception e) {
                
                logger.info("Fallo obteniendo ejb: " + e.getMessage());
            }        
        return auditoriaService;
    }

    public static EstacionEJB getOfertaService() {
        if (ofertaService == null)
            try {
                Context ctx = new InitialContext();
                Object obj = ctx.lookup("java:app/Ideam-Ejb/EstacionEJB");
                ofertaService = (EstacionEJB)obj;
            } catch (Exception e) {
                logger.info("Fallo obteniendo ejb: " + e.getMessage());
            }
        return ofertaService;
    }
    
    public static DatosReferenciaEJB getDatosReferenciaService() {
        if (datosReferenciaService == null)
            try {
                Context ctx = new InitialContext();
                Object obj = ctx.lookup("java:app/Ideam-Ejb/DatosReferenciaEJB");
                datosReferenciaService = (DatosReferenciaEJB)obj;
            } catch (Exception e) {
                logger.info("Fallo obteniendo ejb: " + e.getMessage());
            }
        return datosReferenciaService;
    }

    public static NovTransmitirEJB getNovTransmitirService() {
        if (novTransmitirService == null)
            try {
                Context ctx = new InitialContext();
                Object obj = ctx.lookup("java:app/Ideam-Ejb/NovTransmitirEJB");
                novTransmitirService = (NovTransmitirEJB)obj;
            } catch (Exception e) {
                logger.info("Fallo obteniendo ejb: " + e.getMessage());
            }
        return novTransmitirService;
    }
    
    public static RedMonitoreoEJB getRedMonitoreoService() {
        if (redMonitoreoService == null)
            try {
                Context ctx = new InitialContext();
                Object obj = ctx.lookup("java:app/Ideam-Ejb/RedMonitoreoEJB");
                redMonitoreoService = (RedMonitoreoEJB)obj;
            } catch (Exception e) {
                logger.info("Fallo obteniendo ejb: " + e.getMessage());
            }
        return redMonitoreoService;
    }
    
    
    
    
    
    public static DatosEnaEJB getDatosENAService(){
        if ( datosenaService == null )
            
            try {
                Context ctx = new InitialContext();
                Object obj = ctx.lookup("java:app/Ideam-Ejb/DatosEnaEJB");        
                datosenaService = (DatosEnaEJB)obj;
            } catch (Exception e) {
                System.out.println("Error en ServletLocatorWS:" + e.getMessage());                
            }        

    return datosenaService;
    }


        public static DatosSincEJB getDatosSincService(){
            if ( datosincService == null )
                
                try {
                    Context ctx = new InitialContext();
                    Object obj = ctx.lookup("java:app/Ideam-Ejb/DatosSincEJB");        
                    datosincService = (DatosSincEJB)obj;
                } catch (Exception e) {
                    System.out.println("Error en ServletLocatorWS:" + e.getMessage());                
                }      

        return datosincService;
    }

  public static PueaaEJB getPueaaService(){
        if ( pueaaService == null )
            
            try {
                Context ctx = new InitialContext();
                Object obj = ctx.lookup("java:app/Ideam-Ejb/PueaaEJB");        
                pueaaService = (PueaaEJB)obj;
            } catch (Exception e) {
                System.out.println("Error en ServletLocatorWS:" + e.getMessage());                
            }        
      return pueaaService;
    }
}
