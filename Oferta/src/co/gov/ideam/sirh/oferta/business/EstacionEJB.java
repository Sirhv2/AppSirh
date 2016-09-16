package co.gov.ideam.sirh.oferta.business;


import co.gov.ideam.sirh.oferta.model.PartDatosIdf;
import co.gov.ideam.sirh.oferta.model.PartRefOfertaEstacSubzona;
import co.gov.ideam.sirh.oferta.model.ShmvDiariosHid;

import co.gov.ideam.sirh.oferta.model.ShmvMensualesHid;

import co.gov.ideam.sirh.oferta.model.SiovEstaciones;

import co.gov.ideam.sirh.oferta.model.SirhvOfertaMultianual;

import co.gov.ideam.sirh.oferta.model.SirhvOfertaMultianualMnsl;

import co.gov.ideam.sirh.util.IdeamException;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface EstacionEJB {
    
    public List<ShmvDiariosHid> getDiarios(Long idEstacion, String variable, Integer agno, Integer mes) throws IdeamException;
    
    public List<ShmvDiariosHid> getDiarios(Long idEstacion, String variable, Integer agno) throws IdeamException;
    
    public List<ShmvMensualesHid> getMensuales(Long idEstacion, String variable, Integer agno) throws IdeamException;
    
    public List<ShmvDiariosHid> getDiarios(Long idEstacion, String variable) throws IdeamException;
    
    public List<ShmvMensualesHid> getMensuales(Long idEstacion, String variable) throws IdeamException;
    
    public List<SiovEstaciones> getEstaciones() throws IdeamException;
    
    public SiovEstaciones getEstacion(SiovEstaciones estacion) throws IdeamException;
    
    public SiovEstaciones getEstacion(Long idEstacion) throws IdeamException;
    
    public SiovEstaciones getEstacion(String idEstacion) throws IdeamException;
    
    public List<Object> getMultianualesDiarios(Long idEstacion, String variable, Integer agno, String medida) throws IdeamException;
    
    public List<Object> getMultianualesResumen(Long idEstacion, String variable, Integer agno, String medida) throws IdeamException;

    public List<String> getVariables(Long idEstacion) throws IdeamException;

    public PartRefOfertaEstacSubzona getPartRefOfertaEstacSubzona(String idEstacion);
    
    //CDONCEL
    public List<SiovEstaciones> getEstacionesByMun(String mun)  throws IdeamException;
    public List<SiovEstaciones> findMeteoBySubzona(int subzona) throws IdeamException;
    public List<SiovEstaciones> findEstacionesIDF();
    public List<PartDatosIdf> findDatosIDF(int cod);
    //FIN_CDONCEL
  
}
