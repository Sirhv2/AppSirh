package co.gov.ideam.sirh.datossinc.business;


import co.gov.ideam.sirh.datossinc.model.ConcesionSinc;
import co.gov.ideam.sirh.datossinc.model.PermisoVertimientoSinc;
import co.gov.ideam.sirh.datossinc.model.PredioSinc;
import co.gov.ideam.sirh.datossinc.model.RepresentanteSinc;
import co.gov.ideam.sirh.datossinc.model.UsuarioAguaSinc;
import co.gov.ideam.sirh.util.IdeamException;

import java.util.List;

import javax.ejb.Remote;



@Remote
public interface DatosSincEJB {


    public List consultarDatosRUA() throws IdeamException;

    public List consultarDatosRUAxAut(Long idAutoridad) throws IdeamException;

    public UsuarioAguaSinc consultarUsuarioRua(Long codigoUsuario) throws IdeamException;

    public RepresentanteSinc consultarRepresentanteRua(Long codigoUsuario) throws IdeamException;

    public PredioSinc consultarPredioRua(Long codigoPredio) throws IdeamException;

    public ConcesionSinc consultarConcesionRua(Long codigoConcesion) throws IdeamException;

    public PermisoVertimientoSinc consultarPermisoVertimientoRua(Long codigoPV) throws IdeamException;
}
