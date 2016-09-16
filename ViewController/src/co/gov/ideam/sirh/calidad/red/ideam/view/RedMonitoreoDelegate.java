package co.gov.ideam.sirh.calidad.red.ideam.view;

import co.gov.ideam.sirh.calidad.model.CriteriosBusquedaMuestrasTO;
import co.gov.ideam.sirh.red.ideam.business.RedMonitoreoEJB;
import co.gov.ideam.sirh.red.ideam.model.FqLaboratorios;
import co.gov.ideam.sirh.red.ideam.model.FqMuestras;
import co.gov.ideam.sirh.red.ideam.model.SirhvMuestrasFq;
import co.gov.ideam.sirh.red.ideam.model.SirhvPuntoMonitoreoFq;
import co.gov.ideam.sirh.servlet.ServletLocator;
import co.gov.ideam.sirh.util.IdeamException;

import java.util.List;


public class RedMonitoreoDelegate {
    
    private static RedMonitoreoEJB redMonitoreoService = null;  
    private static RedMonitoreoDelegate instance;
    
    public RedMonitoreoDelegate() {
        redMonitoreoService = ServletLocator.getRedMonitoreoService();
    }
    
    public static RedMonitoreoDelegate getInstance() throws IdeamException{
        if (instance==null){
            instance = new RedMonitoreoDelegate();
        }
        return instance;
    }
    
    public List<SirhvPuntoMonitoreoFq> getAllPuntos() throws IdeamException{
        return redMonitoreoService.getAllPuntos();
    }
    
    public List<SirhvPuntoMonitoreoFq> getAllPuntos(String nombre) throws IdeamException{
        return redMonitoreoService.getAllPuntos(nombre);
    }
   
    public List<SirhvPuntoMonitoreoFq> getAllPuntosCriterios(CriteriosBusquedaMuestrasTO criterios) throws IdeamException{
        return redMonitoreoService.getAllPuntosCriterios(criterios);
    }
   
    public SirhvPuntoMonitoreoFq getPunto(SirhvPuntoMonitoreoFq punto) throws IdeamException {
        return redMonitoreoService.getPunto(punto);
    }
    
    public SirhvPuntoMonitoreoFq getPunto(Long idPunto) throws IdeamException{
        return redMonitoreoService.getPunto(idPunto);
    }
    
    public List<FqMuestras> getAllMuestrasByPunto(Long idPunto) throws IdeamException{
        return redMonitoreoService.getAllMuestrasByPunto(idPunto);
    }
    
    public FqMuestras getMuestra(Long idMuestra) throws IdeamException{
        return redMonitoreoService.getMuestra(idMuestra);
    }
    
    public List<SirhvMuestrasFq> getAllAnalisisByPuntoMuestra(Long idPunto, Long idMuestra) 
            throws IdeamException{
        
        return redMonitoreoService.getAllAnalisisByPuntoMuestra(idPunto, idMuestra);
    }
    
    public FqLaboratorios getLaboratorio(Long idLaboratorio) throws IdeamException{
        return redMonitoreoService.getLaboratorio(idLaboratorio);
    }
    
}
