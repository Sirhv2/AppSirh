package co.gov.ideam.sirh.webservices.businessl.registrarNovedadPermiso;


import co.gov.ideam.sirh.usuarios.agua.model.ws.registrarNovedadPermiso.OE_RegistrarNovedadPermiso;
import co.gov.ideam.sirh.usuarios.agua.model.ws.registrarNovedadPermiso.OS_RegistrarNovedadPermiso;

import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.util.IdeamWsException;
import co.gov.ideam.sirh.webservices.businessl.delegate.UsuarioAguaWSDelegate;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

@Stateless(name = "RegistrarNovedadPermisoSirhWS",
           mappedName = "Sirh-RegistrarNovedadPermisoWS")
@Remote
@WebService(name = "WS_SIAC_SIRH_RegistrarNovedadPermiso",
            serviceName = "WS_SIAC_SIRH_RegistrarNovedadPermiso",
            portName = "RegistrarNovedadPermisoSirhPort")
public class RegistrarNovedadPermisoWSBean implements RegistrarNovedadPermisoWS {

    @WebMethod(operationName = "RegistrarNovedadPermiso")
    @WebResult(name = "RegistrarNovedadPermiso")
    public OS_RegistrarNovedadPermiso registrarNovedadPermiso(OE_RegistrarNovedadPermiso oe) {
       
        OS_RegistrarNovedadPermiso os = new OS_RegistrarNovedadPermiso();
        UsuarioAguaWSDelegate usu;
        try {
                ValidarEntrada(oe);
                usu = UsuarioAguaWSDelegate.getInstance();
                Long permisoNovedadRegistrado =1L;
            
            permisoNovedadRegistrado = usu.registrarNovedadPermisosSIAC(oe);
            
                if (permisoNovedadRegistrado != 1L) {
                    os.setCodigoError(0);
                    os.setMensajeError("Operación correcta");
                    os.setNumeroAutorizacion(permisoNovedadRegistrado);
                }else  if (permisoNovedadRegistrado == 1L){
                        os.setCodigoError(1);
                        os.setMensajeError("Permiso no existente, para procesar Novedad");
                        os.setNumeroAutorizacion(permisoNovedadRegistrado);
                    
                    }

                } catch (IdeamException e) {

                    os.setCodigoError(9999);
                    os.setMensajeError("Se genero error :" + e.getMessage());
                } catch (IdeamWsException ex) {
                    os.setCodigoError(ex.getCodigo());
                    os.setMensajeError(ex.getMensaje());
                }
        

        return os;
    }

    private void ValidarEntrada(OE_RegistrarNovedadPermiso oe) throws IdeamWsException {


        if (oe.getCodAutoridadAmbiental() == null ||
            oe.getCodAutoridadAmbiental().length() == 0)
            throw new IdeamWsException(1, "codAutoridadAmbiental es requerido");


        if (oe.getPermisoNovedad().getCodigoTipoIdentificacion() == null ||
            oe.getPermisoNovedad().getCodigoTipoIdentificacion() == 0)
            throw new IdeamWsException(2,
                                       "codigoTipoIdentificacion es requerido ");

        if (oe.getPermisoNovedad().getCodigoTipoIdentificacion() != 8 &&
            oe.getPermisoNovedad().getCodigoTipoIdentificacion() != 9 &&
            oe.getPermisoNovedad().getCodigoTipoIdentificacion() != 7 &&
            oe.getPermisoNovedad().getCodigoTipoIdentificacion() != 6 &&
            oe.getPermisoNovedad().getCodigoTipoIdentificacion() != 560)
            throw new IdeamWsException(3,
                                       "codigoTipoIdentificacion no es valido ");


        if (oe.getPermisoNovedad().getIdentificacionPersona() == null ||
            oe.getPermisoNovedad().getIdentificacionPersona().length() == 0)
            throw new IdeamWsException(4,
                                       "identificacionPersona es requerido");

        if (oe.getPermisoNovedad().getCedulaCatastral() == null ||
            oe.getPermisoNovedad().getCedulaCatastral().length() == 0)
            throw new IdeamWsException(5, "Cedula catastral es requerido");
       
       

        if (oe.getPermisoNovedad().getNumExpedienteAct() == null ||
            oe.getPermisoNovedad().getNumExpedienteAct() .length() == 0)
            throw new IdeamWsException(22, "numExpediente  Actual es requerido");


      
        
        if (oe.getPermisoNovedad().getNumResolucionAct() == null ||
            oe.getPermisoNovedad().getNumResolucionAct().length() == 0)
            throw new IdeamWsException(23, "numResolucion Actual es requerido");

        if (oe.getPermisoNovedad().getNumResolucionNuevo()== null ||
            oe.getPermisoNovedad().getNumResolucionNuevo() .length() == 0)
            throw new IdeamWsException(24, "numResolucion  Nuevo es requerido");
        
        
        
        if (oe.getPermisoNovedad().getFechaExpedicionNuevo() == null)
            throw new IdeamWsException(25, "FechaExpedicion Nueva es requerido");

        if (oe.getPermisoNovedad().getFechaVencimientoNuevo() == null)
            throw new IdeamWsException(26, "FechaVencimiento Nueva es requerido");

        if (oe.getPermisoNovedad().getFechaResolucionNuevo() == null)
            throw new IdeamWsException(27, "FechaResolucion Nueva es requerido");

        if (oe.getPermisoNovedad().getCantidadRecursoOtorgado() == null)
            throw new IdeamWsException(28, "cantidadRecursoOtorgado es requerido");

        if (oe.getPermisoNovedad().getTipoPermiso() == null ||
            oe.getPermisoNovedad().getTipoPermiso().length() == 0)
            throw new IdeamWsException(29, "tipoPermiso es requerido");

        System.out.println("e.getPermiso().getTipoPermiso -------------  " +
                           oe.getPermisoNovedad().getTipoPermiso());

        if (!oe.getPermisoNovedad().getTipoPermiso().equals("CO") &&
            !oe.getPermisoNovedad().getTipoPermiso().equals("PV"))
            throw new IdeamWsException(27, "tipoPermiso no es valido");


    }


}
