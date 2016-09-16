package co.gov.ideam.sirh.view;

import java.io.IOException;

import javax.faces.context.FacesContext;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * filtro que evalua cada una de las peticiones que se hacen al sistema
 * y solo permite ejecutarlsa siempre que el usuario haya hecho un login 
 * previo y exista una session activa, en caso contrario
 * ignora la peticion y redirecciona a la pagina de login.
 * Esta proceso se omite par las paginas login.jspx y para todas aquellas que
 * usen el contexto helppages que corresponden a las paginas de ayuda
 */
public class SecurityFilter implements Filter {
    private FilterConfig _filterConfig = null;

    public void init(FilterConfig filterConfig) throws ServletException {
        _filterConfig = filterConfig;
    }

    public void destroy() {
        _filterConfig = null;
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException,
                                                   ServletException {
    
        HttpServletRequest httpRequest = (HttpServletRequest)request;

        String path = httpRequest.getPathInfo();
        //System.out.println (path);
        // Si es solicitud de ayuda no se valida seguridad
        if (path.startsWith("/helppages")||path.startsWith("/pages")||path.startsWith("/img")){
            chain.doFilter(request, response); 
            return;
        }
        
        //System.out.println ("==========================FILTER 1"+path);
        if (path.equalsIgnoreCase("/login.jspx")){
          //  System.out.println ("==========================FILTER LOGIN");
            if (httpRequest.getSession()==null || 
                httpRequest.getSession().getAttribute("showDashBoard")==null){
            //    System.out.println ("==========================FILTER SESSION NULL");
                if (response instanceof org.apache.myfaces.trinidadinternal.config.dispatch.DispatchServletResponse){
                    ((org.apache.myfaces.trinidadinternal.config.dispatch.DispatchServletResponse)response).sendRedirect("/Sirh/faces/dashBoard.jspx");
                    //((org.apache.myfaces.trinidadinternal.config.dispatch.DispatchServletResponse)response).sendRedirect("/Sirh/faces/observatorio.jspx");
                }else if (response instanceof oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse){
                    ((oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse)response).sendRedirect("/Sirh/faces/dashBoard.jspx");
                    //((oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse)response).sendRedirect("/Sirh/faces/observatorio.jspx");
                }else if (response instanceof oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse){
                    ((oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse)response).sendRedirect("/Sirh/faces/dashBoardCalidad.jspx");
                }else if (response instanceof oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse){
                    ((oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse)response).sendRedirect("/Sirh/faces/dashBoardCalidadGrafico2.jspx");
                }else if (response instanceof oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse){
                    ((oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse)response).sendRedirect("/Sirh/faces/dashBoardCalidadGrafico3.jspx");
                }else if (response instanceof oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse){
                    ((oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse)response).sendRedirect("/Sirh/faces/dashBoardCaudales.jspx");
                }else if (response instanceof oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse){
                    ((oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse)response).sendRedirect("/Sirh/faces/dashBoard/DashBoardPomcaGrafico1.jspx");
                }else if (response instanceof oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse){
                    ((oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse)response).sendRedirect("/Sirh/faces/dashBoard/DashBoardPomcaGrafico3.jspx");
                }else if (response instanceof oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse){
                    ((oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse)response).sendRedirect("/Sirh/faces/dashBoard/alertDisp.jspx");
                }else if (response instanceof oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse){
                    ((oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse)response).sendRedirect("/Sirh/faces/busqFuentesZonif.jspx");
                }else if (response instanceof oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse){
                    ((oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse)response).sendRedirect("/Sirh/faces/dashBoardCaudalesVert.jspx");         
                }else if (response instanceof oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse){
                    ((oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse)response).sendRedirect("/Sirh/faces/dashBoardPermisosAnio.jspx");
                }else if (response instanceof oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse){
                    ((oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse)response).sendRedirect("/Sirh/faces/dashBoardConcesionesAnio.jspx");
                } else if (response instanceof  oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse) {
                    ((oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse)response).sendRedirect("/Sirh/faces/dashBoard/alertCont.jspx");
                } else if (response instanceof  oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse) {
                    ((oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse)response).sendRedirect("/Sirh/faces/dashBoard/alertDisp.jspx");
                } else if (response instanceof oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse) {
                     ((oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse)response).sendRedirect("/Sirh/faces/dashBoard/alertaCalidad.jspx");
                } else if (response instanceof oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse){
                    ((oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse)response).sendRedirect("/Sirh/faces/dashBoard/DashBoardPorhGrafico1.jspx");
                } else if (response instanceof oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse){
                    ((oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse)response).sendRedirect("/Sirh/faces/dashBoard/DashBoardPorhGrafico2.jspx");
                } else if (response instanceof oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse){
                    ((oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse)response).sendRedirect("/Sirh/faces/dashBoard/DashBoardPorhGrafico3.jspx");
                } else if (response instanceof oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse){
                    ((oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse)response).sendRedirect("/Sirh/faces/dashBoard/DashBoardPorhGrafico4.jspx");
                }    
                return;                
            }            
        }
        if (!path.equalsIgnoreCase("/dashBoard.jspx") && 
            !path.equalsIgnoreCase("/dashBoardCalidad.jspx") &&
            !path.equalsIgnoreCase("/dashBoardCalidadGrafico2.jspx") &&
            !path.equalsIgnoreCase("/dashBoardCalidadGrafico3.jspx") &&
            !path.equalsIgnoreCase("/dashBoardCaudales.jspx") &&
            !path.equalsIgnoreCase("/dashBoardCaudalesVert.jspx") &&
            !path.equalsIgnoreCase("/busqFuentesZonif.jspx") &&
            !path.equalsIgnoreCase("/dashBoard/DashBoardPomcaGrafico1.jspx") &&
            !path.equalsIgnoreCase("/dashBoard/DashBoardPomcaGrafico3.jspx") &&
            !path.equalsIgnoreCase("/dashBoard/alertDisp.jspx") &&
            !path.equalsIgnoreCase("/dashBoard/alertaCalidad.jspx") &&
            !path.equalsIgnoreCase("/dashBoard/alertCont.jspx") &&
            !path.equalsIgnoreCase("/directorio/consulta.jspx") &&
            !path.equalsIgnoreCase("/publicaciones/publicacionesPublico.jspx") &&
            !path.equalsIgnoreCase("/directorio/resultadoConsulta.jspx") &&
            !path.equalsIgnoreCase("/directorio/consultaOrganizaciones.jspx") &&
            !path.equalsIgnoreCase("/directorio/detalleEspecialistaexterno.jspx") &&
            !path.equalsIgnoreCase("/directorio/resultadoConsultaOrganizaciones.jspx") &&     
            !path.equalsIgnoreCase("/dashBoardPermisosAnio.jspx") &&  
            !path.equalsIgnoreCase("/dashBoardConcesionesAnio.jspx") &&  
            !path.equalsIgnoreCase("/login.jspx")&&  
            !path.equalsIgnoreCase("/oferta/estaciones.jspx")&&  
            !path.equalsIgnoreCase("/oferta/detalle_estacion.jspx")&&  
            !path.equalsIgnoreCase("/observatorioCalidad.jspx")&&  
            !path.equalsIgnoreCase("/observatorioCalidadInicio.jspx")&&  
            !path.equalsIgnoreCase("/observatorioDemanda.jspx")&&  
            !path.equalsIgnoreCase("/observatorioGestion.jspx")&&  
            !path.equalsIgnoreCase("/observatorioRiesgo.jspx")&&
            !path.equalsIgnoreCase("/observatorio.jspx")&&
          !path.equalsIgnoreCase("/JSAcuiferos.jspx")&&
          !path.equalsIgnoreCase("/observatorioDemandaSubt.jspx")&&
          !path.equalsIgnoreCase("/JSPomca.jspx")&&
            //ZSDG
            !path.equalsIgnoreCase("/observatorioSuperficiales.jspx")&&
            !path.equalsIgnoreCase("/observatorioSubterraneas.jspx")&&
            !path.equalsIgnoreCase("/observatorioSubterraneas.jspx")&&
            !path.equalsIgnoreCase("/observatoriosubtZonificacion.jspx")&&
            !path.equalsIgnoreCase("/observatoriosubtMonitoreo.jspx")&&
            !path.equalsIgnoreCase("/observatoriosubtDemanda.jspx")&&
            !path.equalsIgnoreCase("/observatoriosubtDocumentos.jspx")&&
            !path.equalsIgnoreCase("/observatoriosubtMonPiezometrico.jspx")&&
            !path.equalsIgnoreCase("/JSreportes.jspx")&&
            !path.equalsIgnoreCase("/JSMonitoreoPiezometrico.jspx")&&
            !path.equalsIgnoreCase("/JSDemanda3.jspx")&&
            !path.equalsIgnoreCase("/JSDemandaAcuifero.jspx")&&
            !path.equalsIgnoreCase("/JSDemandaProvincia.jspx")&&
            !path.equalsIgnoreCase("/JSCalidadParametros.jspx")&&
            !path.equalsIgnoreCase("/dashBoardSubt.jspx")&&
          !path.equalsIgnoreCase("/JSMonitoreoPiezometricoVariacionNivel.jspx")&&
!path.equalsIgnoreCase("/observatorioDemandaSubt.jspx")&&
            !path.equalsIgnoreCase("/busqFuentesZonifSubt.jspx")&&
            !path.equalsIgnoreCase("/JSPueaEstados.jspx")&&
            !path.equalsIgnoreCase("/JSPueaSeguimiento.jspx")&&
            //ZSDG
            !path.equalsIgnoreCase("/sirc.jspx")&&
            !path.equalsIgnoreCase("/ofertaservlet")&&
            !path.equalsIgnoreCase("/visor/detalleVertimientoVista.jspx") &&
            !path.equalsIgnoreCase("/visor/detallePuntoMonitoreoVista.jspx") &&
            !path.equalsIgnoreCase("/visor/detalleCaptacionVista.jspx")&&
            !path.equalsIgnoreCase("/demanda/consultaEstratificada.jspx")&&
            !path.equalsIgnoreCase("/demanda/caudalEstadoUsuario.jspx")&&
            !path.equalsIgnoreCase("/demanda/caudalTipoUsuario.jspx")&&
            !path.equalsIgnoreCase("/calidadIdeam/redIdeam.jspx")&&
            !path.equalsIgnoreCase("/calidadIdeam/detallePunto.jspx")&&
            !path.equalsIgnoreCase("/calidadIdeam/muestras.jspx")&&
            !path.equalsIgnoreCase("/calidadIdeam/detalleMuestra.jspx")&&
            !path.equalsIgnoreCase("/fuentes/conflictosXFuente.jspx") &&
            !path.equalsIgnoreCase("/dashBoard/DashBoardPorhGrafico1.jspx") &&
            !path.equalsIgnoreCase("/dashBoard/DashBoardPorhGrafico2.jspx") &&
            !path.equalsIgnoreCase("/dashBoard/DashBoardPorhGrafico3.jspx") &&
            !path.equalsIgnoreCase("/dashBoard/DashBoardPorhGrafico4.jspx") 
			
        ){
            
            //System.out.println ("==========================FILTER PATH DIFERENTE");
            if (httpRequest.getSession()==null || 
                httpRequest.getSession().getAttribute("usuarioConectado")==null){
                
                //System.out.println ("==========================FILTER SESSION NULL");
                
                if (response instanceof org.apache.myfaces.trinidadinternal.config.dispatch.DispatchServletResponse){
                    ((org.apache.myfaces.trinidadinternal.config.dispatch.DispatchServletResponse)response).sendRedirect("/Sirh/faces/dashBoard.jspx");
                    //((org.apache.myfaces.trinidadinternal.config.dispatch.DispatchServletResponse)response).sendRedirect("/Sirh/faces/observatorio.jspx");
                    return;
                }else if (response instanceof oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse){
                    ((oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse)response).sendRedirect("/Sirh/faces/dashBoard.jspx");
                    //((oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse)response).sendRedirect("/Sirh/faces/observatorio.jspx");
                    return;
                }else if (response instanceof oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse){
                    ((oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse)response).sendRedirect("/Sirh/faces/dashBoardCalidad.jspx");
                    return;
                }else if (response instanceof oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse){
                    ((oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse)response).sendRedirect("/Sirh/faces/dashBoardCalidadGrafico2.jspx");
                    return;
                }else if (response instanceof oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse){
                    ((oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse)response).sendRedirect("/Sirh/faces/dashBoardCalidadGrafico3.jspx");
                    return;
                }else if (response instanceof oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse){
                    ((oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse)response).sendRedirect("/Sirh/faces/dashBoardCaudales.jspx");
                    return;
                }else if (response instanceof oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse){
                    ((oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse)response).sendRedirect("/Sirh/faces/dashBoard/DashBoardPomcaGrafico1.jspx");
                    return;
                }else if (response instanceof oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse){
                    ((oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse)response).sendRedirect("/Sirh/faces/dashBoard/DashBoardPomcaGrafico3.jspx");
                    return;
                }else if (response instanceof oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse){
                    ((oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse)response).sendRedirect("/Sirh/faces/dashBoard/alertDisp.jspx");
                    return;
                }else if (response instanceof oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse){
                    ((oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse)response).sendRedirect("/Sirh/faces/busqFuentesZonif.jspx");
                    return;
                }else if (response instanceof oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse){
                    ((oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse)response).sendRedirect("/Sirh/faces/dashBoardCaudalesVert.jspx");
                    return;
                }else if (response instanceof oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse){
                    ((oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse)response).sendRedirect("/Sirh/faces/dashBoardPermisosAnio.jspx");
                    return;
                }else if (response instanceof oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse){
                    ((oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse)response).sendRedirect("/Sirh/faces/dashBoardConcesionesAnio.jspx");
                    return;
                } else if (response instanceof oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse) {
                    ((oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse)response).sendRedirect("/Sirh/faces/dashBoard/alertCont.jspx");
                    return;
               } else if (response instanceof oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse) {
                    ((oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse)response).sendRedirect("/Sirh/faces/dashBoard/alertDisp.jspx");
                    return;
                } else if (response instanceof  oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse) {
                    ((oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse)response).sendRedirect("/Sirh/faces/dashBoard/alertaCalidad.jspx");
                     return;
                } else if (response instanceof oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse){
                    ((oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse)response).sendRedirect("/Sirh/faces/dashBoard/DashBoardPorhGrafico1.jspx");
                    return;
                } else if (response instanceof oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse){
                    ((oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse)response).sendRedirect("/Sirh/faces/dashBoard/DashBoardPorhGrafico2.jspx");
                    return;
                } else if (response instanceof oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse){
                    ((oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse)response).sendRedirect("/Sirh/faces/dashBoard/DashBoardPorhGrafico3.jspx");
                    return;
                } else if (response instanceof oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse){
                    ((oracle.adfinternal.view.faces.config.rich.XmlHttpServletResponse)response).sendRedirect("/Sirh/faces/dashBoard/DashBoardPorhGrafico4.jspx");
                    return;
                }
            }
        }
        chain.doFilter(request, response);        
    }
}
