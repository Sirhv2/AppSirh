package co.gov.ideam.sirh.webservices.businessl.registrarPermiso;

import co.gov.ideam.sirh.usuarios.agua.model.ws.registrarPermiso.OE_RegistrarPermiso;
import co.gov.ideam.sirh.usuarios.agua.model.ws.registrarPermiso.OS_RegistrarPermiso;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.util.IdeamWsException;
import co.gov.ideam.sirh.webservices.businessl.delegate.UsuarioAguaWSDelegate;


import javax.ejb.Remote;
import javax.ejb.Stateless;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

@Stateless(name = "RegistrarPermisoSirhWS",
           mappedName = "Sirh-RegistrarPermisoWS")
@Remote
@WebService(name = "WS_SIAC_SIRH_RegistrarPermiso",
            serviceName = "WS_SIAC_SIRH_RegistrarPermiso",
            portName = "RegistrarPermisoSirhPort")
public class RegistrarPermisoWSBean implements RegistrarPermisoWS {


    @WebMethod(operationName = "RegistrarPermiso")
    @WebResult(name = "RegistrarPermiso")
    public OS_RegistrarPermiso RegistrarPermiso(OE_RegistrarPermiso oe) {

        UsuarioAguaWSDelegate usu;
        OS_RegistrarPermiso os = new OS_RegistrarPermiso();


        try {

            ValidarEntrada(oe);

            usu = UsuarioAguaWSDelegate.getInstance();
            Long usuarioRegistrado = usu.registrarUsuarioPredioSIAC(oe);
            if (usuarioRegistrado != null) {
                os.setCodigoError(0);
                os.setMensajeError("Operación correcta");
                os.setNumeroAutorizacion(usuarioRegistrado);
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


    private void ValidarEntrada(OE_RegistrarPermiso oe) throws IdeamWsException {


        if (oe.getUsuarioAgua().getCodigoTipoIdentificacion() == null ||
            oe.getUsuarioAgua().getCodigoTipoIdentificacion() == 0)
            throw new IdeamWsException(1,
                                       "codigoTipoIdentificacion es requerido ");

        if (oe.getUsuarioAgua().getCodigoTipoIdentificacion() != 8 &&
            oe.getUsuarioAgua().getCodigoTipoIdentificacion() != 9 &&
            oe.getUsuarioAgua().getCodigoTipoIdentificacion() != 7 &&
            oe.getUsuarioAgua().getCodigoTipoIdentificacion() != 6 &&
            oe.getUsuarioAgua().getCodigoTipoIdentificacion() != 560)
            throw new IdeamWsException(1,
                                       "codigoTipoIdentificacion no es valido ");


        if (oe.getUsuarioAgua().getIdentificacionPersona() == null ||
            oe.getUsuarioAgua().getIdentificacionPersona().length() == 0)
            throw new IdeamWsException(2,
                                       "identificacionPersona es requerido");


        // tipoPersona  persona natural o persona juridica
        if (oe.getUsuarioAgua().getTipoPersona() == null ||
            oe.getUsuarioAgua().getTipoPersona() == 0)
            throw new IdeamWsException(3, "tipoPersona es requerido");

        if (oe.getUsuarioAgua().getTipoPersona() == 5) {

            if (oe.getUsuarioAgua().getPrimerNombre() == null ||
                oe.getUsuarioAgua().getPrimerNombre().length() == 0)
                throw new IdeamWsException(5, "primerNombre es requerido");
            if (oe.getUsuarioAgua().getPrimerApellido() == null ||
                oe.getUsuarioAgua().getPrimerApellido().length() == 0)
                throw new IdeamWsException(6, "primerApellido es requerido");
            if (oe.getUsuarioAgua().getSegundoApellido() == null ||
                oe.getUsuarioAgua().getSegundoApellido().length() == 0)
                throw new IdeamWsException(7, "SegundoApellido es requerido");

        }


        if (oe.getUsuarioAgua().getIdMunicipioCorrespondencia() == null ||
            oe.getUsuarioAgua().getIdMunicipioCorrespondencia() == 0)
            throw new IdeamWsException(14,
                                       "IdMunicipioCorrespondencia es requerido");
        if (oe.getUsuarioAgua().getIdDeptoCorrespondencia() == null ||
            oe.getUsuarioAgua().getIdDeptoCorrespondencia() == 0)
            throw new IdeamWsException(15,
                                       "idDeptoCorrespondencia es requerido");

        //persona juridica

        if (oe.getUsuarioAgua().getTipoPersona() != 5) {

            if (oe.getUsuarioAgua().getActividadCIIU() == null ||
                oe.getUsuarioAgua().getActividadCIIU() == 0)
                throw new IdeamWsException(4, "actividadCIIU es requerido");
            if (oe.getUsuarioAgua().getRazonSocial() == null ||
                oe.getUsuarioAgua().getRazonSocial().length() == 0)
                throw new IdeamWsException(8, "RazonSocial es requerido");
            if (oe.getRepresentanteLegal().getCodigoTipoIdentificacionRepLegal() ==
                null ||
                oe.getRepresentanteLegal().getCodigoTipoIdentificacionRepLegal().length() ==
                0)
                throw new IdeamWsException(9,
                                           "codigoTipoIdentificacionRepLegal es requerido");
            if (oe.getRepresentanteLegal().getIdentificacionRepLegal() ==
                null ||
                oe.getRepresentanteLegal().getIdentificacionRepLegal().length() ==
                0)
                throw new IdeamWsException(10,
                                           "IdentificacionRepLegal es requerido");
            if (oe.getRepresentanteLegal().getPrimerNombreRepLegal() == null ||
                oe.getRepresentanteLegal().getPrimerNombreRepLegal().length() ==
                0)
                throw new IdeamWsException(11,
                                           "primerNombreRepLegal es requerido");
            if (oe.getRepresentanteLegal().getPrimerApellidoRepLegal() ==
                null ||
                oe.getRepresentanteLegal().getPrimerApellidoRepLegal().length() ==
                0)
                throw new IdeamWsException(12,
                                           "primerApellidoRepLegal es requerido");
            if (oe.getRepresentanteLegal().getSegundoApellidoRepLegal() ==
                null ||
                oe.getRepresentanteLegal().getSegundoApellidoRepLegal().length() ==
                0)
                throw new IdeamWsException(13,
                                           "SegundoApellidoRepLegal es requerido");
            if (oe.getRepresentanteLegal().getIdMunicipioCorrespondencia() ==
                null ||
                oe.getRepresentanteLegal().getIdMunicipioCorrespondencia() ==
                0)
                throw new IdeamWsException(28,
                                           "IdMunicipioCorrespondenciaRepLegal es requerido");
            if (oe.getRepresentanteLegal().getIdDeptoCorrespondencia() ==
                null ||
                oe.getRepresentanteLegal().getIdDeptoCorrespondencia() == 0)
                throw new IdeamWsException(29,
                                           "idDeptoCorrespondenciaRepLegal es requerido");

        }

        if (oe.getPredio().getNombrePredio() == null ||
            oe.getPredio().getNombrePredio().length() == 0)
            throw new IdeamWsException(16, "nombrePredio es requerido");

        if (oe.getPredio().getCedulaCatastral() == null ||
            oe.getPredio().getCedulaCatastral().length() == 0)
            throw new IdeamWsException(17, "Cedula catastral es requerido");
        if (oe.getPredio().getIdMunicipioPredio() == null ||
            oe.getPredio().getIdMunicipioPredio() == 0)
            throw new IdeamWsException(18, "idMunicipioPredio es requerido");

        if (oe.getPredio().getIdDeptoPredio() == null ||
            oe.getPredio().getIdDeptoPredio() == 0)
            throw new IdeamWsException(19, "idDeptoPredio es requerido");

        if (oe.getPredio().getDireccionPredio() == null ||
            oe.getPredio().getDireccionPredio().length() == 0)
            throw new IdeamWsException(20, "direccionPredio es requerido");

        if (oe.getUsuarioAgua().getCodAutoridadAmbiental() == null ||
            oe.getUsuarioAgua().getCodAutoridadAmbiental() == 0)
            throw new IdeamWsException(21,
                                       "codAutoridadAmbiental es requerido");

        if (oe.getPermiso().getNumExpediente() == null ||
            oe.getPermiso().getNumExpediente().length() == 0)
            throw new IdeamWsException(22, "numExpediente es requerido");

        if (oe.getPermiso().getNumResolucion() == null ||
            oe.getPermiso().getNumResolucion().length() == 0)
            throw new IdeamWsException(23, "numResolucion es requerido");


        if (oe.getPermiso().getFechaExpedicion() == null)
            throw new IdeamWsException(24, "fechaExpedicion es requerido");


        if (oe.getPermiso().getFechaVencimiento() == null )
            throw new IdeamWsException(25, "fechaVencimiento es requerido");

        if (oe.getPermiso().getCantidadRecursoOtorgado() == null || oe.getPermiso().getCantidadRecursoOtorgado() == 0)
            throw new IdeamWsException(26,
                                       "cantidadRecursoOtorgado es requerido");

        if (oe.getPermiso().getTipoPermiso() == null ||
            oe.getPermiso().getTipoPermiso().length() == 0)
            throw new IdeamWsException(27, "tipoPermiso es requerido");

        System.out.println("e.getPermiso().getTipoPermiso -------------  " +
                           oe.getPermiso().getTipoPermiso());

        if (!oe.getPermiso().getTipoPermiso().equals("CO") &&
            !oe.getPermiso().getTipoPermiso().equals("PV"))
            throw new IdeamWsException(27, "tipoPermiso no es valido");


    }


}
