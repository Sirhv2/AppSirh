package co.gov.ideam.sirh.red.ideam.business;

import co.gov.ideam.sirh.calidad.model.CriteriosBusquedaMuestrasTO;
import co.gov.ideam.sirh.red.ideam.model.FqLaboratorios;
import co.gov.ideam.sirh.red.ideam.model.FqMuestras;
import co.gov.ideam.sirh.red.ideam.model.SirhvMuestrasFq;
import co.gov.ideam.sirh.red.ideam.model.SirhvPuntoMonitoreoFq;

import co.gov.ideam.sirh.util.IdeamException;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface RedMonitoreoEJB {
    
    public List<SirhvPuntoMonitoreoFq> getAllPuntos() throws IdeamException;
    
    public List<SirhvPuntoMonitoreoFq> getAllPuntos(String nombre) throws IdeamException;
    
    public List<SirhvPuntoMonitoreoFq> getAllPuntosCriterios(CriteriosBusquedaMuestrasTO criterios) throws IdeamException;
    
    public SirhvPuntoMonitoreoFq getPunto(SirhvPuntoMonitoreoFq punto) throws IdeamException;
    
    public SirhvPuntoMonitoreoFq getPunto(Long idPunto) throws IdeamException;
    
    public List<FqMuestras> getAllMuestrasByPunto(Long idPunto) throws IdeamException;
    
    public FqMuestras getMuestra(Long idMuestra) throws IdeamException;
    
    public List<SirhvMuestrasFq> getAllAnalisisByPuntoMuestra(Long idPunto, Long idMuestra) 
                    throws IdeamException;
    
    public FqLaboratorios getLaboratorio(Long idLaboratorio) throws IdeamException;
}
