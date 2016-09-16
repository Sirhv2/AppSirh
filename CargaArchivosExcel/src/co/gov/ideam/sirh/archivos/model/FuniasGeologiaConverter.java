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

public class FuniasGeologiaConverter extends ModelConverter{
    public FuniasGeologiaConverter() { 
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
        
        String nombreLocalizacion = row.getCellValue("Localizacion Topografica").toString();
        Lista localizacion = parametrosService.
            getListaByDescripcion(nombreLocalizacion, ConstantesParametros.
                                  CATEGORIA_FUNIAS_LOCALIZACION_TOPOGRAFICA);
        if(localizacion==null){
            throw new IdeamException("No existe la localización topográfica " + nombreLocalizacion);
        }
        
        String nombreGeoforma = row.getCellValue("Geoforma").toString();
        Lista geoforma = 
            parametrosService.getListaByDescripcion(nombreGeoforma, 
                                                    ConstantesParametros.CATEGORIA_FUNIAS_GEOFORMA);
        
        if(geoforma==null){
            throw new IdeamException("No existe la geoforma " + nombreGeoforma);
        }
                
        funias.setLocalizacionTopografica(localizacion.getCodigo());
        funias.setGeoforma(geoforma.getCodigo());
        
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
