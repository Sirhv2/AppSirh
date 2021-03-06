package co.gov.ideam.sirh.webservices.businessl.delegate;

import co.gov.ideam.sirh.usuarios.agua.business.UsuariosAguaEJB;
import co.gov.ideam.sirh.usuarios.agua.model.ConsultarPermisosV;
import co.gov.ideam.sirh.usuarios.agua.model.ws.consultarPermisos.OE_ConsultarPermisos;
import co.gov.ideam.sirh.usuarios.agua.model.ws.entidades.AutoridadesWS;
import co.gov.ideam.sirh.usuarios.agua.model.ws.entidades.DatosReporteWS;
import co.gov.ideam.sirh.usuarios.agua.model.ws.registrarNovedadPermiso.OE_RegistrarNovedadPermiso;
import co.gov.ideam.sirh.usuarios.agua.model.ws.registrarPermiso.OE_RegistrarPermiso;
import co.gov.ideam.sirh.usuarios.agua.model.ws.traspasarPermiso.OE_TraspasarPermiso;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.webservices.businessl.ServletLocatorWS;
import java.util.List;


public class UsuarioAguaWSDelegate {

    /**
     * Referencia al EJB de Seguridad
     */
    private static UsuariosAguaEJB usuariosAguaService = null;

    /**
     * Utilizada para implementar singleton
     */
    private static UsuarioAguaWSDelegate instance;

    /**
     * Retorna una instancia a la clase
     * @return
     */
    public static UsuarioAguaWSDelegate getInstance() throws IdeamException {
        if (instance == null) {
            instance = new UsuarioAguaWSDelegate();
        }
        return instance;
    }

    private UsuarioAguaWSDelegate() throws IdeamException {
        usuariosAguaService = ServletLocatorWS.getUsuariosAguaService();
    }


    /**
     * Registra el usuario y predio recibidos como parametro para integracion SIAC

     * @throws IdeamException
     */
    public Long registrarUsuarioPredioSIAC(OE_RegistrarPermiso oe) throws IdeamException {
        return usuariosAguaService.registrarUsuarioPredioSIAC(oe);

    }


    public List getListaFuentesExternas() throws IdeamException {
        return usuariosAguaService.getListaFuentesExternas();
    }


    //Consulta Permisos SIAC

    public List<ConsultarPermisosV> getPermisosV(OE_ConsultarPermisos oe) throws IdeamException {
        return usuariosAguaService.getPermisosV(oe);
    }

    //Registrar Novedad Permisos SIAC

    public Long registrarNovedadPermisosSIAC(OE_RegistrarNovedadPermiso oeNovedadPermiso) throws IdeamException {
        return usuariosAguaService.registrarNovedadPermisosSIAC(oeNovedadPermiso);
    }


    //Registrar Novedad Traspaso Permisos SIAC

    public Long registrarTraspasoPermisosSIAC(OE_TraspasarPermiso oeTraspasarPermiso) throws IdeamException {
        return usuariosAguaService.registrarTraspasoPermisosSIAC(oeTraspasarPermiso);
    }
    
    
    
    //  * Consultar capatacion por fuente  en cada autoridad ambiental 
   public List<DatosReporteWS> consultarDatosXFuente(String codAutoridadAmbiental, String tipo) throws IdeamException {
     return  usuariosAguaService.consultarDatosXFuente(codAutoridadAmbiental, tipo);
    
   }

  
  //  * Consultar  autoridades ambientales 
  public List<AutoridadesWS> getAutoridadesAmbientalesWS() throws IdeamException { 
    return  usuariosAguaService.getAutoridadesAmbientalesWS();
  
  }
    
    
}


