package co.gov.ideam.sirh.datosena.business;


import co.gov.ideam.sirh.datosena.model.DatosAreaPomcas;
import co.gov.ideam.sirh.datosena.model.DatosAvanceSIRH;
import co.gov.ideam.sirh.datosena.model.DatosCargaContaminante;
import co.gov.ideam.sirh.datosena.model.DatosCuerposLenticosTO;
import co.gov.ideam.sirh.datosena.model.DatosOfertaAreaTO;
import co.gov.ideam.sirh.datosena.model.DatosSIRH;
import co.gov.ideam.sirh.datosena.model.DatosSIRHAutoridad;
import co.gov.ideam.sirh.datosena.model.DatosSIRHDep;
import co.gov.ideam.sirh.datosena.model.DatosSectoresArea;
import co.gov.ideam.sirh.util.IdeamException;

import java.util.List;

import javax.ejb.Remote;



@Remote
public interface DatosEnaEJB {

    public List<DatosOfertaAreaTO> consultarOfertaArea()  throws IdeamException;
    public List<DatosCuerposLenticosTO> consultarCuerposLenticos() throws IdeamException;
    public List<DatosSectoresArea> consultarSectoresArea() throws IdeamException;
    public List<DatosAreaPomcas> consultarDatosAreaPomcas() throws IdeamException;
    public List<DatosCargaContaminante> consultarDatosCargaContaminante() throws IdeamException;
    public List <DatosSIRH> consultarDatosSIRH() throws IdeamException;
    public List <DatosSIRHDep> consultarDatosSIRHDep() throws IdeamException;
    public List <DatosAvanceSIRH> consultarDatosAvanceSIRH() throws IdeamException ;
    public List <DatosSIRHAutoridad> consultarDatosSIRHAutoridad(String sigla) throws IdeamException ;
}
