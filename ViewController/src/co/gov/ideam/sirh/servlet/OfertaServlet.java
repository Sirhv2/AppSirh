package co.gov.ideam.sirh.servlet;

import co.gov.ideam.sirh.view.FacesUtils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.servlet.*;
import javax.servlet.http.*;

public class OfertaServlet extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";
    private static final String COMPONENTE_ESTACION =    "100";
    private static final String OPERACION_DETALLE =      "200";
    private static final String OPERACION_CAPTACION =    "400";
    private static final String OPERACION_VERTIMIENTO =  "500";
    private static final String OPERACION_PTOMONITOREO = "600";
    private static final String OPERACION_PTOMONITOREO_IDEAM = "700";
    
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException,
                                                       IOException {
        this.processRequest(request, response);
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException,
                                                            IOException {
        this.processRequest(request, response);
    }
    
    public void processRequest(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException,
                                                            IOException {
        try {
            String componente = request.getParameter("componente");
            String operacion = request.getParameter("operacion");
            String codigo = request.getParameter("codigo");
            if(componente!=null && componente.equalsIgnoreCase(COMPONENTE_ESTACION)){
                if (operacion!=null && operacion.equalsIgnoreCase(OPERACION_DETALLE)) {
                    if (codigo!=null) {
                        System.out.println("=========================entro codigo: "+codigo);
                        FacesUtils.removeManagedBeanFromSession("detalleEstacionBacking");
                        FacesUtils.removeFromSession("estacion");
                        FacesUtils.setInSession("estacion", codigo);
                        FacesContext.getCurrentInstance().getExternalContext().redirect("oferta/detalle_estacion.jspx");
                        
                    }
                }
                
                // VISTA DE CAPTACION PARA EL VISOR GEOGRAFICO --LISBETH --
                
                
                if (operacion!=null && operacion.equalsIgnoreCase(OPERACION_CAPTACION)) {
                    if (codigo!=null) {
                       
                        System.out.println("captacion---------------------servlet---------");
                        System.out.println("=========================entro codigo: "+codigo);
                        FacesUtils.removeManagedBeanFromSession("detalleCaptacionVistaBean");
                        FacesUtils.removeFromSession("captacionVisor");
                        FacesUtils.setInSession("captacionVisor", codigo);
                        
                        FacesContext.getCurrentInstance().getExternalContext().redirect("visor/detalleCaptacionVista.jspx?captacion="+codigo);
                        
                    }
                }
                // VISTA DE VERTIMIENTO PARA EL VISOR GEOGRAFICO --LISBETH --
                
                
                if (operacion!=null && operacion.equalsIgnoreCase(OPERACION_VERTIMIENTO)) {
                    if (codigo!=null) {
                        
                        System.out.println("===VERT======================entro codigo: "+codigo);
                        FacesUtils.removeManagedBeanFromSession("detalleVertimientoVistaBean");
                        FacesUtils.removeFromSession("vertimientoVisor");
                        FacesUtils.setInSession("vertimientoVisor", codigo);
                        FacesContext.getCurrentInstance().getExternalContext().redirect("visor/detalleVertimientoVista.jspx?vertimiento="+codigo);
                        
                    }
                }
                
                
                
                // VISTA DE punto monitoreo PARA EL VISOR GEOGRAFICO --LISBETH --
                
                
                if (operacion!=null && operacion.equalsIgnoreCase(OPERACION_PTOMONITOREO)) {
                    if (codigo!=null) {
                        
                        System.out.println("===prunto======================entro codigo: "+codigo);
                        FacesUtils.removeManagedBeanFromSession("detallePuntoMonitoreovista");
                        FacesUtils.removeFromSession("puntoVisor");
                        FacesUtils.setInSession("puntoVisor", codigo);
                        FacesContext.getCurrentInstance().getExternalContext().redirect("visor/detallePuntoMonitoreoVista.jspx?punto="+codigo);
                        
                    }
                }
                
                
                
                // VISTA DE punto monitoreo IDEAM PARA EL VISOR GEOGRAFICO --LISBETH --
                
                
                if (operacion!=null && operacion.equalsIgnoreCase(OPERACION_PTOMONITOREO_IDEAM)) {
                    if (codigo!=null) {
                        
                        System.out.println("===prunto======================entro codigo: "+codigo);
                        FacesUtils.removeManagedBeanFromSession("detallePuntoIDEAMBean");
                        FacesUtils.removeFromSession("puntoIDEAMVisor");
                        FacesUtils.setInSession("puntoIDEAMVisor", codigo);
                        FacesContext.getCurrentInstance().getExternalContext().redirect("visor/detallePuntoIDEAM.jspx?punto="+codigo);
                        
                    }
                }
                
                
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
