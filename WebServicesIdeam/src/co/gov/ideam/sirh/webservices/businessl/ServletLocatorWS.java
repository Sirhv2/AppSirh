package co.gov.ideam.sirh.webservices.businessl;


import co.gov.ideam.sirh.calidad.business.CalidadEJB;
import co.gov.ideam.sirh.datosena.business.DatosEnaEJB;
import co.gov.ideam.sirh.seguridad.business.SeguridadEJB;
import co.gov.ideam.sirh.usuarios.agua.business.UsuariosAguaEJB;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.servlet.http.HttpServlet;


public class ServletLocatorWS  extends HttpServlet {
    
    private static SeguridadEJB seguridadService;   
    private static DatosEnaEJB  datosenaService;
    private static UsuariosAguaEJB  usuariosAguaService;
    private static CalidadEJB  calidadService;
    
    
    public ServletLocatorWS() {
    }
    
    public static SeguridadEJB getSeguridadService(){
        if ( seguridadService == null )
            
            try {
                Context ctx = new InitialContext();
                Object obj = ctx.lookup("java:app/Ideam-Ejb/SeguridadBean");        
                seguridadService = (SeguridadEJB)obj;
            } catch (Exception e) {
                System.out.println("Error en ServletLocatorWS:" + e.getMessage());                
            }        


        return seguridadService;
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
    
   
    public static UsuariosAguaEJB getUsuariosAguaService(){
        if ( usuariosAguaService == null )
            try {
                Context ctx = new InitialContext();
                Object obj = ctx.lookup("java:app/Ideam-Ejb/UsuariosAgua");        
                usuariosAguaService = (UsuariosAguaEJB)obj;
            } catch (Exception e) {
               System.out.println("Error en ServletLocatorWS:" + e.getMessage());    
            }        
        return usuariosAguaService;
    }
    
    
    public static CalidadEJB getCalidadService() {
        if ( calidadService == null )
            
            try {
                Context ctx = new InitialContext();
                Object obj = ctx.lookup("java:app/Ideam-Ejb/CalidadEJB");        
                calidadService = (CalidadEJB)obj;
            } catch (Exception e) {
                
                System.out.println("Error en ServletLocatorWS:" + e.getMessage());    
            }        
        return calidadService;
    }
}
