package co.gov.ideam.sirh.datos.referencia.business;

import co.gov.ideam.sirh.datos.referencia.model.DemtAcueducto;
import co.gov.ideam.sirh.datos.referencia.model.DemtEmpresa;
import co.gov.ideam.sirh.datos.referencia.model.DemtFacturado;
import co.gov.ideam.sirh.datos.referencia.model.DemtTarifa;
import co.gov.ideam.sirh.util.IdeamException;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

@Remote
public interface DatosReferenciaEJB {
    
    public List<DemtEmpresa> getAllEmpresa()throws IdeamException;
    
    public List<DemtTarifa> getAllTarifa()throws IdeamException;
    
    public List<DemtFacturado> getAllFacturado()throws IdeamException;
    
    public List<DemtAcueducto> getAllAcueducto()throws IdeamException;
    
    public DemtAcueducto getAcueducto(Integer idAcueducto)throws IdeamException;
    
    public DemtAcueducto getAcueductoByEmpresa(Integer idEmpresa)throws IdeamException;
    
    public DemtAcueducto getAcueductoByUbicacion(Integer departamento, Integer municipio)throws IdeamException;
    
    public DemtEmpresa getEmpresa(Integer idEmpresa)throws IdeamException;
    
    public DemtEmpresa getEmpresaByNombre(String nombre)throws IdeamException;
    
    public DemtEmpresa getEmpresaByNit(String nit)throws IdeamException;
    
    public DemtTarifa getTarifa(Integer idTarifa)throws IdeamException;
    
    public DemtTarifa getTarifaByFiltros(
            Integer municipio, Integer departamento, Integer agno, Integer mes, 
            Integer idEmpresa, Integer estrato, Integer clase)throws IdeamException;
    
    public DemtFacturado getFacturado(Integer idFacturado)throws IdeamException;
    
    public DemtEmpresa createEmpresa(DemtEmpresa empresa) throws IdeamException;
    
    public DemtEmpresa updateEmpresa(DemtEmpresa empresa) throws IdeamException;
    
    public DemtAcueducto createAcueducto(DemtAcueducto acueducto) throws IdeamException;
    
    public DemtAcueducto updateAcueducto(DemtAcueducto acueducto) throws IdeamException;
    
    public DemtTarifa createTarifa(DemtTarifa tarifa) throws IdeamException;
    
    public DemtTarifa updateTarifa(DemtTarifa tarifa) throws IdeamException;
    
    public DemtFacturado createFactura(DemtFacturado factura) throws IdeamException;
    
    public DemtFacturado updateFactura(DemtFacturado factura) throws IdeamException;
    
    public List getAllTarifa(Integer agno, String tipoCargo) throws IdeamException;
    
    public List getAllTarifa(Integer agno, String tipoCargo, Integer clase) throws IdeamException;
    
    public List getAllTipousuarioCaudal(Date fechaInicio, 
                                        Date FechaFinal) throws IdeamException;
    
    public List getAllEstadoUsuarioCaudal(Integer municipio) throws IdeamException;
    
}
