package co.gov.ideam.sirh.archivos.model;

import co.gov.ideam.sirh.parametros.business.ParametrosEJB;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.usuarios.agua.model.SubttFunias;
import co.gov.ideam.sirh.util.Constantes;
import co.gov.ideam.sirh.util.IdeamException;

public class FuniasIdConverter extends ModelConverter{
    public FuniasIdConverter() { 
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

        String nombreTipo = row.getCellValue("Tipo").toString();
        Lista tipoFuente = parametrosService.getListaByDescripcion(nombreTipo, 
                                                                   12L);

        if(tipoFuente==null){
            throw new IdeamException("No existe el tipo de funias " + nombreTipo);
        }
        /*
        fuente.setIdTipoFuente(tipoFuente);
        fuente.setNombre( row.getCellValue("Nombre").toString().toUpperCase().trim());
        fuente.setDescripcion( row.getCellValue("Descripcion").toString() );
        if(row.getCellValue("NumCAR").toString().length()>0){
        fuente.setCodigoCuencaAA( row.getCellValue("NumCAR").toString() );
        }
        fuente.setEsCompartida(Constantes.FALSE_TO_INTEGER);
        */
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
