package co.gov.ideam.sirh.oferta.view;

import co.gov.ideam.sirh.oferta.business.EstacionEJB;
import co.gov.ideam.sirh.oferta.model.PartDatosIdf;
import co.gov.ideam.sirh.oferta.model.PartRefOfertaEstacSubzona;
import co.gov.ideam.sirh.oferta.model.ShmvDiariosHid;
import co.gov.ideam.sirh.oferta.model.ShmvMensualesHid;
import co.gov.ideam.sirh.oferta.model.SiovEstaciones;
import co.gov.ideam.sirh.oferta.model.SirhvOfertaMultianual;
import co.gov.ideam.sirh.servlet.ServletLocator;
import co.gov.ideam.sirh.util.IdeamException;

import java.util.List;


public class OfertaDelegate {
    private static EstacionEJB ofertaService = null;  
    private static OfertaDelegate instance;
    
    private OfertaDelegate() {
        ofertaService = ServletLocator.getOfertaService();
    }
    
    public static OfertaDelegate getInstance() throws IdeamException{
        if (instance==null){
            instance = new OfertaDelegate();
        }
        return instance;
    }
    
    public List<ShmvDiariosHid> getDiarios(Long idEstacion, String variable, Integer agno, Integer mes) throws IdeamException{
        return ofertaService.getDiarios(idEstacion, variable, agno, mes);
    }
    
    public List<ShmvDiariosHid> getDiarios(Long idEstacion, String variable, Integer agno) throws IdeamException{
        return ofertaService.getDiarios(idEstacion, variable, agno);
    }
    
    public List<ShmvDiariosHid> getDiarios(Long idEstacion, String variable) throws IdeamException{
        return ofertaService.getDiarios(idEstacion, variable);
    }
    
    public List<ShmvMensualesHid> getMensuales(Long idEstacion, String variable, Integer agno) throws IdeamException{
        return ofertaService.getMensuales(idEstacion, variable, agno);
    }
    
    public List<ShmvMensualesHid> getMensuales(Long idEstacion, String variable) throws IdeamException{
        return ofertaService.getMensuales(idEstacion, variable);
    }
    
    public List<SiovEstaciones> getEstaciones() throws IdeamException{
        return ofertaService.getEstaciones();
    }
    
    public SiovEstaciones getEstacion(SiovEstaciones estacion) throws IdeamException{
        return ofertaService.getEstacion(estacion);
    }
    
    public SiovEstaciones getEstacion(Long idEstacion) throws IdeamException{
        return ofertaService.getEstacion(idEstacion);
    }
    
    public SiovEstaciones getEstacion(String idEstacion) throws IdeamException{
        return ofertaService.getEstacion(idEstacion);
    }
    
    public List<Object> getMultianualesDiarios(Long idEstacion, 
                                                String variable, Integer agno, String medida) throws IdeamException{
        return ofertaService.getMultianualesDiarios(idEstacion, variable, agno, medida);
    }
    
    public List<Object> getMultianualesResumen(Long idEstacion, String variable, Integer agno, String medida) throws IdeamException{
        return ofertaService.getMultianualesResumen(idEstacion, variable, agno, medida); 
    }
    
    public List<String> getVariables(Long idEstacion) throws IdeamException{
        return ofertaService.getVariables(idEstacion);
    }
    
        // HUGO 20150115
    public PartRefOfertaEstacSubzona getPartRefOfertaEstacSubzona(String idEstacion) {
        return ofertaService.getPartRefOfertaEstacSubzona(idEstacion);
    }
    
    //CDONCEL
  public List<SiovEstaciones> getEstacionesByMun(String mun) throws IdeamException{
      return ofertaService.getEstacionesByMun(mun);
  }
  
  public List<SiovEstaciones> findBySubzona(int subzona) throws IdeamException{
    return ofertaService.findMeteoBySubzona(subzona);
  }
  
  public List<SiovEstaciones> findEstacionesIDF(){
      return ofertaService.findEstacionesIDF();
    }
  public List<PartDatosIdf> findDatosIDF(int cod){
      return ofertaService.findDatosIDF(cod);
    }
    //FIN_CDONCEL

}

