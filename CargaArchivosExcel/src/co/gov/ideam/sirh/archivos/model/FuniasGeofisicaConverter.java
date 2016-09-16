package co.gov.ideam.sirh.archivos.model;

import co.gov.ideam.sirh.parametros.business.ParametrosEJB;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.usuarios.agua.business.UsuariosAguaEJB;
import co.gov.ideam.sirh.usuarios.agua.model.Captacion;
import co.gov.ideam.sirh.usuarios.agua.model.CaptacionTO;
import co.gov.ideam.sirh.usuarios.agua.model.SubttFunias;
import co.gov.ideam.sirh.util.Constantes;
import co.gov.ideam.sirh.util.ConstantesParametros;
import co.gov.ideam.sirh.util.IdeamException;

public class FuniasGeofisicaConverter extends ModelConverter{
    public FuniasGeofisicaConverter() { 
        super();
    }

    public Object getModel(Object model)throws IdeamException {
        if(model instanceof SubttFunias){
            return this.getFunias( (SubttFunias)model );
        }
        return null;
    }
    
    private SubttFunias getFunias(SubttFunias funias)throws IdeamException{
        ParametrosEJB parametrosService = this.getParametrosService();
        UsuariosAguaEJB usuariosAguaService = this.getUsuariosAguaService();
        
        String identificador = row.getCellValue("Identificador Punto").toString();
        
        //verificar que exista una captación con el identificador del punto.
        if(identificador == null || identificador.trim().equalsIgnoreCase("")){
            //throw new IdeamException("Identificador de punto no valido.");
            return null;
        }else{
            CaptacionTO captacion = 
                usuariosAguaService.getCaptacion(identificador, funias.getAutoridad().getId());
            if(captacion == null){
                //throw new IdeamException("No se encuentra captación con el identificador de punto: "+identificador);
                return null;
            }
            funias.setIdCaptacion(captacion.getId());
        }
        
        //verificar si existe un funias para esa captación del mismo tipo
        SubttFunias existeFunias = usuariosAguaService.
            getFuniasByCaptacionTipoFunias(funias.getIdCaptacion(),
                                           ConstantesParametros.LISTA_GEOLOGIA_GEOMORFOLOGIA.intValue());
        if(existeFunias != null){
            funias = existeFunias;
        }
        

        
        String fecha =  row.getCellValue("Fecha Medicion").toString();
        funias.setFechaMedicion(this.getDate(fecha).getTime());
        
        String equipo = row.getCellValue("Equipo Usado").toString();
        funias.setEquipoUsado(equipo);
        
        String tipoRegistro = row.getCellValue("Tipo Registro").toString();
        Lista listTipoRegistro = 
            parametrosService.getListaByDescripcion(tipoRegistro, 
                                                    ConstantesParametros.
                                                    CATEGORIA_TIPO_REGISTRO);
        funias.setTipoRegistro( (listTipoRegistro!=null )
                                ? listTipoRegistro.getCodigo() : null );
        
        String compania = row.getCellValue("Compania Ejecutora").toString();
        funias.setCompaniaEjecutora(compania);
        
        String resistividad = row.getCellValue("Resistividad Lodo").toString();
        resistividad = replaceFloatingSeparator(resistividad);
        funias.setResistividadLodo((resistividad!=null)
                                   ?Double.parseDouble(resistividad) :null);
        
        String temperatura = row.getCellValue("Temperatura").toString();
        temperatura = replaceFloatingSeparator(temperatura);
        funias.setTemperaturaLodo((temperatura!=null)
                                  ?Double.parseDouble(temperatura) :null);
        
        String profundidad = row.getCellValue("Profundidad Registro").toString();
        profundidad = replaceFloatingSeparator(profundidad);
        funias.setProfundidadRegistro((profundidad!=null)
                                      ?Double.parseDouble(profundidad) :null);        
        
        String calidad = row.getCellValue("Calidad Registro").toString();
        calidad = calidad.trim().toUpperCase();
        if(calidad.equalsIgnoreCase("BUENO")){
            funias.setCalidadRegistro(3);
        }else  if(calidad.equalsIgnoreCase("MALO")){
            funias.setCalidadRegistro(1);
        }else if(calidad.equalsIgnoreCase("REGULAR")){
            funias.setCalidadRegistro(2);
        }
        
        
        return funias;
    }

    private int parseValor(String nombreColumna){
        String texto =
            row.getCellValue(nombreColumna).toString();
        Double valor = Double.parseDouble( texto );
        return valor.intValue();
    }

    private String replaceFloatingSeparator(String tmp){
        if(tmp!=null && !tmp.trim().equalsIgnoreCase("")){
            if(tmp.indexOf(",")!=-1)
            tmp = tmp.replaceAll(",",".");
        }else{
            tmp=null;
        }
        return tmp;
    }
    
}
