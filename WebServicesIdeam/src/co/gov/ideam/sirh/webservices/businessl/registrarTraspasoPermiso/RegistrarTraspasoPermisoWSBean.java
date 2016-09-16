package co.gov.ideam.sirh.webservices.businessl.traspasarPermiso;



import co.gov.ideam.sirh.usuarios.agua.model.ws.traspasarPermiso.OE_TraspasarPermiso;
import co.gov.ideam.sirh.usuarios.agua.model.ws.traspasarPermiso.OS_TraspasarPermiso;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.util.IdeamWsException;
import co.gov.ideam.sirh.webservices.businessl.delegate.UsuarioAguaWSDelegate;

import co.gov.ideam.sirh.webservices.businessl.registrarTraspasoPermiso.RegistrarTraspasoPermisoWS;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

@Stateless(name = "TraspasarPermisoSirhWS",
           mappedName = "Sirh-TraspasarPermisoWS")
@Remote
@WebService(name = "WS_SIAC_SIRH_TraspasarPermiso",
            serviceName = "WS_SIAC_SIRH_TraspasarPermiso",
            portName = "TraspasarPermisoSirhPort")
public class RegistrarTraspasoPermisoWSBean implements RegistrarTraspasoPermisoWS {

    @WebMethod(operationName = "TraspasarPermiso")
    @WebResult(name = "TraspasarPermiso")
    public OS_TraspasarPermiso registrarTraspasoPermiso(OE_TraspasarPermiso oe) {
       
        OS_TraspasarPermiso os = new OS_TraspasarPermiso();
        UsuarioAguaWSDelegate usu;
        try {
                ValidarEntrada(oe);

                usu = UsuarioAguaWSDelegate.getInstance();
                Long permisoTraspasoRegistrado =1L;
            
            permisoTraspasoRegistrado = usu.registrarTraspasoPermisosSIAC(oe);
                if (permisoTraspasoRegistrado != 1L) {
                    os.setCodigoError(0);
                    os.setMensajeError("Operación correcta");
                    os.setNumeroAutorizacion(permisoTraspasoRegistrado);
                }else  if (permisoTraspasoRegistrado == 1L) {
                    os.setCodigoError(1);
                    os.setMensajeError("Permiso no existente, para procesar Novedad");
                    os.setNumeroAutorizacion(permisoTraspasoRegistrado);
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

  
    private void ValidarEntrada(OE_TraspasarPermiso oe) throws IdeamWsException {


        if (oe.getPermisoTraspaso().getUsuarioTraspaso().getCodigoTipoIdentificacion() == null ||
            oe.getPermisoTraspaso().getUsuarioTraspaso().getCodigoTipoIdentificacion() == 0)
            throw new IdeamWsException(1, "codigoTipoIdentificacion es requerido ");

        if (oe.getPermisoTraspaso().getUsuarioTraspaso().getCodigoTipoIdentificacion() != 8 &&
            oe.getPermisoTraspaso().getUsuarioTraspaso().getCodigoTipoIdentificacion() != 9 &&
            oe.getPermisoTraspaso().getUsuarioTraspaso().getCodigoTipoIdentificacion() != 7 &&
            oe.getPermisoTraspaso().getUsuarioTraspaso().getCodigoTipoIdentificacion() != 6 &&
            oe.getPermisoTraspaso().getUsuarioTraspaso().getCodigoTipoIdentificacion() != 560)
            throw new IdeamWsException(1, "codigoTipoIdentificacion no es valido ");


        if (oe.getPermisoTraspaso().getUsuarioTraspaso().getIdentificacionPersona() == null ||
            oe.getPermisoTraspaso().getUsuarioTraspaso().getIdentificacionPersona().length() == 0)
            throw new IdeamWsException(2,
                                       "identificacionPersona es requerido");


        // tipoPersona  persona natural o persona juridica
        if (oe.getPermisoTraspaso().getUsuarioTraspaso().getTipoPersona() == null ||
            oe.getPermisoTraspaso().getUsuarioTraspaso().getCodigoTipoIdentificacion() == 0)
            throw new IdeamWsException(3, "tipoPersona es requerido");

        if (oe.getPermisoTraspaso().getUsuarioTraspaso().getTipoPersona() == 5) {

            if (oe.getPermisoTraspaso().getUsuarioTraspaso().getPrimerNombre() == null ||
                oe.getPermisoTraspaso().getUsuarioTraspaso().getPrimerNombre().length() == 0)
                throw new IdeamWsException(5, "primerNombre es requerido");
            if (oe.getPermisoTraspaso().getUsuarioTraspaso().getPrimerApellido() == null ||
                oe.getPermisoTraspaso().getUsuarioTraspaso().getPrimerApellido().length() == 0)
                throw new IdeamWsException(6, "primerApellido es requerido");
            if (oe.getPermisoTraspaso().getUsuarioTraspaso().getSegundoApellido() == null ||
                oe.getPermisoTraspaso().getUsuarioTraspaso().getSegundoApellido().length() == 0)
                throw new IdeamWsException(7, "SegundoApellido es requerido");

        }


        if (oe.getPermisoTraspaso().getUsuarioTraspaso().getIdMunicipioCorrespondencia() == null ||
            oe.getPermisoTraspaso().getUsuarioTraspaso().getIdMunicipioCorrespondencia() == 0)
            throw new IdeamWsException(14,
                                       "IdMunicipioCorrespondencia es requerido");
        if (oe.getPermisoTraspaso().getUsuarioTraspaso().getIdDeptoCorrespondencia() == null ||
            oe.getPermisoTraspaso().getUsuarioTraspaso().getIdDeptoCorrespondencia() == 0)
            throw new IdeamWsException(15,
                                       "idDeptoCorrespondencia es requerido");

        //persona juridica

        if (oe.getPermisoTraspaso().getUsuarioTraspaso().getTipoPersona() != 5) {

            if (oe.getPermisoTraspaso().getUsuarioTraspaso().getActividadCIIU() == null ||
                oe.getPermisoTraspaso().getUsuarioTraspaso().getActividadCIIU() == 0)
                throw new IdeamWsException(4, "actividadCIIU es requerido");
            if (oe.getPermisoTraspaso().getUsuarioTraspaso().getRazonSocial() == null ||
                oe.getPermisoTraspaso().getUsuarioTraspaso().getRazonSocial().length() == 0)
                throw new IdeamWsException(8, "RazonSocial es requerido");
            if (oe.getPermisoTraspaso().getRepresentanteTraspaso().getCodigoTipoIdentificacionRepLegal() ==
                null ||
                oe.getPermisoTraspaso().getRepresentanteTraspaso().getCodigoTipoIdentificacionRepLegal().length() ==
                0)
                throw new IdeamWsException(9,
                                           "codigoTipoIdentificacionRepLegal es requerido");
            if ( oe.getPermisoTraspaso().getRepresentanteTraspaso().getIdentificacionRepLegal() ==
                null ||
                 oe.getPermisoTraspaso().getRepresentanteTraspaso().getIdentificacionRepLegal().length() ==
                0)
                throw new IdeamWsException(10,
                                           "IdentificacionRepLegal es requerido");
            if ( oe.getPermisoTraspaso().getRepresentanteTraspaso().getPrimerNombreRepLegal() == null ||
                 oe.getPermisoTraspaso().getRepresentanteTraspaso().getPrimerNombreRepLegal().length() ==
                0)
                throw new IdeamWsException(11,
                                           "primerNombreRepLegal es requerido");
            if ( oe.getPermisoTraspaso().getRepresentanteTraspaso().getPrimerApellidoRepLegal() ==
                null ||
                 oe.getPermisoTraspaso().getRepresentanteTraspaso().getPrimerApellidoRepLegal().length() ==
                0)
                throw new IdeamWsException(12,
                                           "primerApellidoRepLegal es requerido");
            if ( oe.getPermisoTraspaso().getRepresentanteTraspaso().getSegundoApellidoRepLegal() ==
                null ||
                 oe.getPermisoTraspaso().getRepresentanteTraspaso().getSegundoApellidoRepLegal().length() ==
                0)
                throw new IdeamWsException(13,
                                           "SegundoApellidoRepLegal es requerido");
            if ( oe.getPermisoTraspaso().getRepresentanteTraspaso().getIdMunicipioCorrespondencia() ==
                null ||
                oe.getPermisoTraspaso().getRepresentanteTraspaso().getIdMunicipioCorrespondencia() ==
                0)
                throw new IdeamWsException(28,
                                           "IdMunicipioCorrespondenciaRepLegal es requerido");
            if ( oe.getPermisoTraspaso().getRepresentanteTraspaso().getIdDeptoCorrespondencia() ==
                null ||
                 oe.getPermisoTraspaso().getRepresentanteTraspaso().getIdDeptoCorrespondencia() == 0)
                throw new IdeamWsException(29,
                                           "idDeptoCorrespondenciaRepLegal es requerido");

        }

        if (oe.getPermisoTraspaso().getPredioTraspaso().getNombrePredio() == null ||
           oe.getPermisoTraspaso().getPredioTraspaso().getNombrePredio().length() == 0)
            throw new IdeamWsException(16, "nombrePredio es requerido");

        if (oe.getPermisoTraspaso().getPredioTraspaso().getCedulaCatastral() == null ||
            oe.getPermisoTraspaso().getPredioTraspaso().getCedulaCatastral().length() == 0)
            throw new IdeamWsException(17, "Cedula catastral es requerido");
        if (oe.getPermisoTraspaso().getPredioTraspaso().getIdMunicipioPredio() == null ||
            oe.getPermisoTraspaso().getPredioTraspaso().getIdMunicipioPredio() == 0)
            throw new IdeamWsException(18, "idMunicipioPredio es requerido");

        if (oe.getPermisoTraspaso().getPredioTraspaso().getIdDeptoPredio() == null ||
            oe.getPermisoTraspaso().getPredioTraspaso().getIdDeptoPredio() == 0)
            throw new IdeamWsException(19, "idDeptoPredio es requerido");

        if (oe.getPermisoTraspaso().getPredioTraspaso().getDireccionPredio() == null ||
            oe.getPermisoTraspaso().getPredioTraspaso().getDireccionPredio().length() == 0)
            throw new IdeamWsException(20, "direccionPredio es requerido");

        if (oe.getPermisoTraspaso().getUsuarioTraspaso().getCodAutoridadAmbiental() == null ||
            oe.getPermisoTraspaso().getUsuarioTraspaso().getCodAutoridadAmbiental() == 0)
            throw new IdeamWsException(21,
                                       "codAutoridadAmbiental es requerido");

        if (oe.getPermisoTraspaso().getPermisoTraspasoNuevo().getNumExpediente() == null ||
            oe.getPermisoTraspaso().getPermisoTraspasoNuevo().getNumExpediente().length() == 0)
            throw new IdeamWsException(22, "numExpediente es requerido");

        if (oe.getPermisoTraspaso().getPermisoTraspasoNuevo().getNumResolucion() == null ||
            oe.getPermisoTraspaso().getPermisoTraspasoNuevo().getNumResolucion().length() == 0)
            throw new IdeamWsException(23, "numResolucion es requerido");


        if (oe.getPermisoTraspaso().getPermisoTraspasoNuevo().getFechaExpedicion() == null)
            throw new IdeamWsException(24, "fechaExpedicion es requerido");


        if (oe.getPermisoTraspaso().getPermisoTraspasoNuevo().getFechaVencimiento() == null)
            throw new IdeamWsException(25, "fechaVencimiento es requerido");

        if (oe.getPermisoTraspaso().getPermisoTraspasoNuevo().getCantidadRecursoOtorgado() == null)
            throw new IdeamWsException(26,
                                       "cantidadRecursoOtorgado es requerido");

        if (oe.getPermisoTraspaso().getPermisoTraspasoNuevo().getTipoPermiso() == null ||
            oe.getPermisoTraspaso().getPermisoTraspasoNuevo().getTipoPermiso().length() == 0)
            throw new IdeamWsException(27, "tipoPermiso es requerido");

        System.out.println("e.getPermiso().getTipoPermiso -------------  " +
                           oe.getPermisoTraspaso().getPermisoTraspasoNuevo().getTipoPermiso());

        if (!oe.getPermisoTraspaso().getPermisoTraspasoNuevo().getTipoPermiso().equals("CO") &&
            !oe.getPermisoTraspaso().getPermisoTraspasoNuevo().getTipoPermiso().equals("PV"))
            throw new IdeamWsException(27, "tipoPermiso no es valido");


    }
}
