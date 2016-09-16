package co.gov.ideam.sirh.archivos.model;

import co.gov.ideam.sirh.datos.referencia.business.DatosReferenciaEJB;
import co.gov.ideam.sirh.datos.referencia.model.DemtAcueducto;
import co.gov.ideam.sirh.datos.referencia.model.DemtEmpresa;
import co.gov.ideam.sirh.datos.referencia.model.DemtFacturado;
import co.gov.ideam.sirh.datos.referencia.model.DemtTarifa;
import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.fuentes.model.FnttTramo;
import co.gov.ideam.sirh.parametros.business.ParametrosEJB;
import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.util.Constantes;
import co.gov.ideam.sirh.util.IdeamException;

import java.math.BigDecimal;

public class TarifasAcueductoConverter extends ModelConverter{
    public TarifasAcueductoConverter() {
        super();
    }
    
    public Object getModel(Object model)throws IdeamException {
        if(model instanceof DemtTarifa){
            return this.getTarifa( (DemtTarifa)model );
        }else if(model instanceof DemtEmpresa){
            return this.getEmpresa( (DemtEmpresa)model );
        }else if(model instanceof DemtAcueducto){
            return this.getAcueducto( (DemtAcueducto)model );
        }else if(model instanceof DemtFacturado){
            return this.getFactura( (DemtFacturado)model );
        }
        return null;
    }
    
    private DemtTarifa getTarifa(DemtTarifa tarifa)throws IdeamException{
        ParametrosEJB parametrosService = this.getParametrosService();

        tarifa.setAgno(this.parseValor("Año"));

        tarifa.setMes(this.parseValor("Mes"));
        
        String fijo = row.getCellValue("Cargo fijo").toString();
        tarifa.setCargoFijo(new BigDecimal(fijo));
        
        String basico = row.getCellValue("Cargo basico")!=null&&!row.getCellValue("Cargo basico").toString().trim().equals("")?row.getCellValue("Cargo basico").toString():"0";
        tarifa.setCargoBasico(new BigDecimal(basico));
        
        String complementario = row.getCellValue("Cargo complementario")!=null&&!row.getCellValue("Cargo complementario").toString().trim().equals("")?row.getCellValue("Cargo complementario").toString():"0";
        tarifa.setCargoComplementario(new BigDecimal(complementario));
        
        String suntuario = row.getCellValue("Cargo suntuario")!=null&&!row.getCellValue("Cargo suntuario").toString().trim().equals("")?row.getCellValue("Cargo suntuario").toString():"0";
        tarifa.setCargoSuntuario(new BigDecimal(suntuario));
        
        String tplena = row.getCellValue("Tarifa Plena")!=null&&!row.getCellValue("Tarifa Plena").toString().trim().equals("")?row.getCellValue("Tarifa Plena").toString():"0";
        tarifa.setTar_plena(new BigDecimal(tplena));
        
        String tconsumo = row.getCellValue("Tarifa Consumo")!=null&&!row.getCellValue("Tarifa Consumo").toString().trim().equals("")?row.getCellValue("Tarifa Consumo").toString():"0";
        tarifa.setTar_consumo(new BigDecimal(tconsumo));
        
        String nombreDeptoTxt = this.row.getCellValue("Departamento").toString();
        Divipola depto = 
            parametrosService.getDivipolaByName(nombreDeptoTxt,"DEP");
        tarifa.setDepartamento(depto.getId().intValue());
        
        String nombreMunTxt = this.row.getCellValue("Municipio").toString();
        Divipola mun = 
            parametrosService.getDivipolaByName(nombreMunTxt, depto);
        tarifa.setMunicipio( mun.getId().intValue() );
        
        String estrato = ""+this.parseValor("Estrato");
        Lista estratoObj = parametrosService.getListaByDescripcion(estrato, 72L);
        tarifa.setEstrato(estratoObj.getCodigo());
        
        String clase = row.getCellValue("Clase uso").toString();
        if(clase.equalsIgnoreCase("R")){
            clase = "Residencial";
        }else if(clase.equalsIgnoreCase("NR")){
            clase = "No Residencial";
        }
        Lista claseObj = parametrosService.getListaByDescripcion(clase, 73L);
        tarifa.setClase(claseObj.getCodigo());
        
        String servicio = row.getCellValue("Servicio").toString();
        if(servicio.equalsIgnoreCase("ACUEDUCTO")){
            servicio = "Acueducto";
        }else if(servicio.equalsIgnoreCase("ALCANTARILLADO")){
            servicio = "Alcantarillado";
        }
        Lista servicioObj = parametrosService.getListaByDescripcion(servicio, 71L);
        tarifa.setServicio(servicioObj.getCodigo());


        return tarifa;
    }
    
    private DemtEmpresa getEmpresa(DemtEmpresa empresa)throws IdeamException{
        
        //String nit = row.getCellValue("ID").toString();
        empresa.setNit(""+this.parseValor("ID"));

        String nombre = row.getCellValue("Nombre").toString();
        empresa.setNombre(nombre);
        
        return empresa;
    }
    
    private DemtAcueducto getAcueducto(DemtAcueducto acueducto)throws IdeamException{
        
        
        
        return acueducto;
    }
    
    private DemtFacturado getFactura(DemtFacturado factura)throws IdeamException{
        
        return factura;
    }
    
    
    private int parseValor(String nombreColumna){
        String texto =
            row.getCellValue(nombreColumna).toString();
        Double valor = Double.parseDouble( texto );
        return valor.intValue();
    }
    
}
