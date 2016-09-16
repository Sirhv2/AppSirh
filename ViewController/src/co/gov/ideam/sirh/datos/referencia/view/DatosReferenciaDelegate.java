package co.gov.ideam.sirh.datos.referencia.view;

import co.gov.ideam.sirh.datos.referencia.business.DatosReferenciaEJB;
import co.gov.ideam.sirh.datos.referencia.model.DemtAcueducto;
import co.gov.ideam.sirh.datos.referencia.model.DemtEmpresa;
import co.gov.ideam.sirh.datos.referencia.model.DemtFacturado;
import co.gov.ideam.sirh.datos.referencia.model.DemtTarifa;
import co.gov.ideam.sirh.fuentes.business.FuentesEJB;
import co.gov.ideam.sirh.fuentes.view.FuenteDelegate;
import co.gov.ideam.sirh.servlet.ServletLocator;
import co.gov.ideam.sirh.util.IdeamException;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DatosReferenciaDelegate {
    //...........................................................
    //ESTE BLOQUE SIEMPRE ES ASI.................................
    //...........................................................
    /**
     * Referencia al EJB
     */
    private static DatosReferenciaEJB datosReferenciaService = null;    
    /**
    * Utilizada para implementar singleton
    */
    private static DatosReferenciaDelegate instance;
    
     /**
      * Retorna una instancia a la clase
      * @return
      */
    public static DatosReferenciaDelegate getInstance() throws IdeamException{
        if (instance==null){
            instance = new DatosReferenciaDelegate();
        }
        return instance;
    }
     
    /**
    * Construcutor privado para implementar singleton
    */
    private DatosReferenciaDelegate(){
        datosReferenciaService = ServletLocator.getDatosReferenciaService();
    }
    
    //................................................................
    //CIERRA BLOQUE SIEMPRE IGUAL.....................................
    //................................................................
    
    //IMPLEMENTACIÖN DE  METODOS DE NEGOCIO
    
    public List<DemtEmpresa> getAllEmpresa()throws IdeamException{
        return datosReferenciaService.getAllEmpresa();
    }
    
    public List<DemtTarifa> getAllTarifa()throws IdeamException{
        return datosReferenciaService.getAllTarifa();
    }
    
    public List<DemtFacturado> getAllFacturado()throws IdeamException{
        return datosReferenciaService.getAllFacturado();
    }
    
    public List<DemtAcueducto> getAllAcueducto()throws IdeamException{
        return datosReferenciaService.getAllAcueducto();
    }
    
    public DemtAcueducto getAcueducto(Integer idAcueducto)throws IdeamException{
        return datosReferenciaService.getAcueducto(idAcueducto);
    }
    
    public DemtAcueducto getAcueductoByEmpresa(Integer idEmpresa)throws IdeamException{
        return datosReferenciaService.getAcueductoByEmpresa(idEmpresa);
    }
    
    public DemtAcueducto getAcueductoByUbicacion(Integer departamento, Integer municipio)throws IdeamException{
        return datosReferenciaService.getAcueductoByUbicacion(departamento, municipio);
    }
    
    public DemtEmpresa getEmpresa(Integer idEmpresa)throws IdeamException{
        return datosReferenciaService.getEmpresa(idEmpresa);
    }
    
    public DemtEmpresa getEmpresaByNombre(String nombre)throws IdeamException{
        return datosReferenciaService.getEmpresaByNombre(nombre);
    }
    
    public DemtEmpresa getEmpresaByNit(String nit)throws IdeamException{
        return datosReferenciaService.getEmpresaByNit(nit);
    }
    
    public DemtTarifa getTarifa(Integer idTarifa)throws IdeamException{
        return datosReferenciaService.getTarifa(idTarifa);
    }
    
    public DemtTarifa getTarifaByFiltros(
            Integer municipio, Integer departamento, Integer agno, Integer mes, 
            Integer idEmpresa, Integer estrato, Integer clase)throws IdeamException{
        return datosReferenciaService.getTarifaByFiltros(municipio, departamento, 
                                                         agno, mes, idEmpresa, estrato, clase);
    }
    
    public DemtFacturado getFacturado(Integer idFacturado)throws IdeamException{
        return datosReferenciaService.getFacturado(idFacturado);
    }
    
    public DemtEmpresa createEmpresa(DemtEmpresa empresa) throws IdeamException{
        return datosReferenciaService.createEmpresa(empresa);
    }
    
    public DemtEmpresa updateEmpresa(DemtEmpresa empresa) throws IdeamException{
        return datosReferenciaService.updateEmpresa(empresa);
    }
    
    public DemtAcueducto createAcueducto(DemtAcueducto acueducto) throws IdeamException{
        return datosReferenciaService.createAcueducto(acueducto);
    }
    
    public DemtAcueducto updateAcueducto(DemtAcueducto acueducto) throws IdeamException{
        return datosReferenciaService.updateAcueducto(acueducto);
    }
    
    public DemtTarifa createTarifa(DemtTarifa tarifa) throws IdeamException{
        return datosReferenciaService.createTarifa(tarifa);
    }
    
    public DemtTarifa updateTarifa(DemtTarifa tarifa) throws IdeamException{
        return datosReferenciaService.updateTarifa(tarifa);
    }
    
    public DemtFacturado createFactura(DemtFacturado factura) throws IdeamException{
        return datosReferenciaService.createFactura(factura);
    }
    
    public DemtFacturado updateFactura(DemtFacturado factura) throws IdeamException{
        return datosReferenciaService.updateFactura(factura);
    }
    
    public List getAllTarifa(Integer agno, String tipoCargo) throws IdeamException{
        return datosReferenciaService.getAllTarifa(agno, tipoCargo);    
    }
    
    public List getAllTarifa(Integer agno, String tipoCargo, Integer clase) throws IdeamException{
        return datosReferenciaService.getAllTarifa(agno, tipoCargo, clase); 
    }
    
    public List getAllTipousuarioCaudal(Date fechaInicio, Date FechaFinal) throws IdeamException{
        return datosReferenciaService.getAllTipousuarioCaudal(fechaInicio, FechaFinal);
    }
    
    public List getAllEstadoUsuarioCaudal(Integer municipio) throws IdeamException{
        return datosReferenciaService.getAllEstadoUsuarioCaudal(municipio);
    }
}
