package co.gov.ideam.sirh.view;


import co.gov.ideam.sirh.mail.MailConfigurator;
import co.gov.ideam.sirh.parametros.model.TipoParametro;
import co.gov.ideam.sirh.parametros.view.ParametrosDelegate;
import co.gov.ideam.sirh.util.IdeamException;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


/**
 * Inicializa todos los recursos requeridos cuando se arranca el sistema
 * Carga las diferentes clases delegates susceptibles de ser auditadas.
 */
public class StartService extends HttpServlet {
    static Logger logger = Logger.getLogger(StartService.class);

    public void init(ServletConfig config) throws ServletException {
        super.init(config);        
        
        // Parametrizar el log de la aplicacion
        String file = "log4j.properties";
        String timeWatch = "10000";
        String prefix  = getServletContext().getRealPath("/");
                
        PropertyConfigurator.configureAndWatch(prefix+file,Long.parseLong(timeWatch));
        
        // Grabar en el log el inicio de la aplicacion        
        logger.info("Inicio de la aplicación SIRH");     
        
        // Consultar los parametros de correo y configurarlo
        try{
            ParametrosDelegate pd = ParametrosDelegate.getInstance();
            TipoParametro tipoParametro = pd.getTipoParametro(TipoParametro.SERVIDOR_CORREO);            
            String usuario = tipoParametro.getParametro("usuario").getValor();            
            String password = tipoParametro.getParametro("password").getValor();            
            String host = tipoParametro.getParametro("host").getValor();            
            String puerto = tipoParametro.getParametro("puerto").getValor();
            
            MailConfigurator.setHost(host);
            MailConfigurator.setPassword(password);
            MailConfigurator.setPuerto(puerto);
            MailConfigurator.setUsuario(usuario);
            }catch(IdeamException e){
                throw new ServletException("Error cargando configuracion inicial del sistema " + e.getMessage());
            }catch(Exception e){
                throw new ServletException("Error cargando configuracion inicial del sistema " + e.getMessage());
            }
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException,
                                                           IOException {
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException,
                                                            IOException {
    }
}
