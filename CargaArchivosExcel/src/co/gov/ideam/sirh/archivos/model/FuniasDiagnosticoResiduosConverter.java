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

public class FuniasDiagnosticoResiduosConverter extends ModelConverter{
    public FuniasDiagnosticoResiduosConverter() { 
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
        
        //verificar que exista una captaci�n con el identificador del punto.
        if(identificador == null || identificador.trim().equalsIgnoreCase("")){
            //throw new IdeamException("Identificador de punto no valido.");
            return null;
        }else{
            CaptacionTO captacion = 
                usuariosAguaService.getCaptacion(identificador, funias.getAutoridad().getId());
            if(captacion == null){
                //throw new IdeamException("No se encuentra captaci�n con el identificador de punto: "+identificador);
                return null;
            }
            funias.setIdCaptacion(captacion.getId());
        }
        
        //verificar si existe un funias para esa captaci�n del mismo tipo
        SubttFunias existeFunias = usuariosAguaService.
            getFuniasByCaptacionTipoFunias(funias.getIdCaptacion(),
                                           ConstantesParametros.LISTA_GEOLOGIA_GEOMORFOLOGIA.intValue());
        if(existeFunias != null){
            funias = existeFunias;
        }
        
        
        //informaci�n en archivo

        String residuo = row.getCellValue("Residuos Solidos").toString();
        residuo = residuo.trim().toUpperCase();
        
        if(residuo.equals("Agr�cola")){
            funias.setResiduosAgricolas(Constantes.TRUE_TO_INTEGER);
        }else if(residuo.equals("Dom�stico")){
            funias.setResiduosDomestico(Constantes.TRUE_TO_INTEGER);
        }else if(residuo.equals("Ganader�a")){
            funias.setResiduosGanaderia(Constantes.TRUE_TO_INTEGER);
        }else if(residuo.equals("Hospitalario")){//aplica para otro y otras categorias
            funias.setResiduosHospitalarios(Constantes.TRUE_TO_INTEGER);
        }else if(residuo.equals("Industrial")){//aplica para otro y otras categorias
            funias.setResiduosIndustriales(Constantes.TRUE_TO_INTEGER);
        }else if(residuo.equals("Minero")){//aplica para otro y otras categorias
            funias.setResiduosMineros(Constantes.TRUE_TO_INTEGER);
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

