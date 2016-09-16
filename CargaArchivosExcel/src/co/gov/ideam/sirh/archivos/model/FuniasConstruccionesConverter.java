package co.gov.ideam.sirh.archivos.model;

import co.gov.ideam.sirh.archivos.model.validator.ListasOpcionalValidator;
import co.gov.ideam.sirh.archivos.model.validator.Obligatorio;
import co.gov.ideam.sirh.parametros.business.ParametrosEJB;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.usuarios.agua.business.UsuariosAguaEJB;
import co.gov.ideam.sirh.usuarios.agua.model.Captacion;
import co.gov.ideam.sirh.usuarios.agua.model.CaptacionTO;
import co.gov.ideam.sirh.usuarios.agua.model.SubttFunias;
import co.gov.ideam.sirh.util.Constantes;
import co.gov.ideam.sirh.util.ConstantesParametros;
import co.gov.ideam.sirh.util.IdeamException;

public class FuniasConstruccionesConverter extends ModelConverter{
    public FuniasConstruccionesConverter() { 
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
                                           ConstantesParametros.LISTA_CONSTRUCCION_DISENO.intValue());
        if(existeFunias != null){
            funias = existeFunias;
        }
        
        
        //información en archivo
        
        String fecha =  row.getCellValue("Fecha Construccion").toString();
        funias.setFechaConstruccion(this.getDate(fecha).getTime());
        
        String diametroInt = row.getCellValue("Diametro Interior").toString();
        diametroInt = replaceFloatingSeparator(diametroInt);
        funias.setDiametroInterior((diametroInt!=null)
                                        ?Double.parseDouble(diametroInt)
                                        :null);
        

        
        String diametroExt = row.getCellValue("Diametro Exterior").toString();
        diametroExt = replaceFloatingSeparator(diametroExt);
        funias.setDiametroExterior((diametroExt!=null)
                                        ?Double.parseDouble(diametroExt)
                                        :null);
        
        String diametroPer = row.getCellValue("Diametro Perforacion").toString();
        diametroPer = replaceFloatingSeparator(diametroPer);
        funias.setDiametroPerforacion((diametroPer!=null)
                                        ?Double.parseDouble(diametroPer)
                                        :null);
        
        
        String profundidad = row.getCellValue("Profundidad Perforacion").toString();
        profundidad = replaceFloatingSeparator(profundidad);
        funias.setProfundidadPerforacion((profundidad!=null)
                                        ?Double.parseDouble(profundidad)
                                        :null);
        
        String compania = row.getCellValue("Compania Perforadora").toString();
        funias.setCompaniaPerforadora(compania);
        
        String metodo = row.getCellValue("Metodo Construccion").toString();
        Lista listMetodo = parametrosService.
            getListaByDescripcion(metodo, ConstantesParametros.CATEGORIA_MET_CONSTRUCC);
        funias.setMetodoConstruccion( (listMetodo!=null )
                                      ? listMetodo.getCodigo() : null );
        
        String materialRev = row.getCellValue("Material Revestimento").toString();
        Lista listMaterial = parametrosService.
            getListaByDescripcion(materialRev, ConstantesParametros.CATEGORIA_MATERIAL_REVESTIM);
        funias.setMaterialUsado((listMaterial!=null )
                                      ? listMetodo.getCodigo() : null );
            
        String colmatado = row.getCellValue("Esta colmatado").toString();
        colmatado = colmatado.trim().toUpperCase();
        if(colmatado.equalsIgnoreCase("SI")){
            funias.setEstaColmatado(Constantes.TRUE_TO_INTEGER);
        }else{
            funias.setEstaColmatado(Constantes.FALSE_TO_INTEGER);
        }
        
        String colapsado = row.getCellValue("Esta colapsado").toString();
        colapsado = colapsado.trim().toUpperCase();
        if(colapsado.equalsIgnoreCase("SI")){
            funias.setEstaColapsado(Constantes.TRUE_TO_INTEGER);
        }else{
            funias.setEstaColapsado(Constantes.FALSE_TO_INTEGER);
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
