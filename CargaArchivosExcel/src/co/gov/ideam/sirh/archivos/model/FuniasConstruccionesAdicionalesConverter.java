package co.gov.ideam.sirh.archivos.model;

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

public class FuniasConstruccionesAdicionalesConverter extends ModelConverter{
    public FuniasConstruccionesAdicionalesConverter() { 
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

        String tipo = row.getCellValue("Tipo Construccion").toString();
        tipo = tipo.trim().toUpperCase();
        
        String capacidad = row.getCellValue("Capacidad").toString();
        capacidad = replaceFloatingSeparator(capacidad);
        
        if(tipo.equals("TANQUE")){
            funias.setTanque(Constantes.TRUE_TO_INTEGER);
            funias.setCapacidadTanque((capacidad!=null)
                                      ?Double.parseDouble(capacidad) :null);
        }else if(tipo.equals("EMBALSE")){
            funias.setEmbalse(Constantes.TRUE_TO_INTEGER);
            funias.setCapacidadEmbalse((capacidad!=null)
                                      ?Double.parseDouble(capacidad) :null);
        }else if(tipo.equals("ALBERCA")){
            funias.setAlberca(Constantes.TRUE_TO_INTEGER);
            funias.setCapacidadAlberca((capacidad!=null)
                                      ?Double.parseDouble(capacidad) :null);
        }else {//aplica para otro y otras categorias
        funias.setOtro(Constantes.TRUE_TO_INTEGER);
        funias.setCapacidadOtro((capacidad!=null)
                                  ?Double.parseDouble(capacidad) :null);
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
