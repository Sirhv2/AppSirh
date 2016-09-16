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

public class FuniasDiagnosticoFuentesConverter extends ModelConverter{
    public FuniasDiagnosticoFuentesConverter() { 
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
        
        
        //información en archivo

        String fuente = row.getCellValue("Fuente de Contaminacion").toString();
        fuente = fuente.trim().toUpperCase();
        
        String distancia = row.getCellValue("Distancia").toString();
        distancia = replaceFloatingSeparator(distancia);
        
        if(fuente.equals("Campo de infiltración")){
            funias.setCampoInfiltracion(Constantes.TRUE_TO_INTEGER);
            funias.setDistanciaCampo((distancia!=null)
                                      ?Double.parseDouble(distancia) :null);
        }else if(fuente.equals("Cementerio")){
            funias.setCementerio(Constantes.TRUE_TO_INTEGER);
            funias.setDistanciaCementerio((distancia!=null)
                                      ?Double.parseDouble(distancia) :null);
        }else if(fuente.equals("Estación de servicio")){
            funias.setEstacionServicio(Constantes.TRUE_TO_INTEGER);
            funias.setDistanciaEstacionServicio((distancia!=null)
                                      ?Double.parseDouble(distancia) :null);
        }else if(fuente.equals("Lagunas de oxidación")){//aplica para otro y otras categorias
            funias.setLagunasOxidacion(Constantes.TRUE_TO_INTEGER);
            funias.setDistanciaLagunas((distancia!=null)
                                      ?Double.parseDouble(distancia) :null);
        }else if(fuente.equals("Lavadero de carros y motos")){//aplica para otro y otras categorias
            funias.setLavaderoVehiculos(Constantes.TRUE_TO_INTEGER);
            funias.setDistanciaLavadero((distancia!=null)
                                      ?Double.parseDouble(distancia) :null);
        }else if(fuente.equals("Plantas de sacrificio")){//aplica para otro y otras categorias
            funias.setPlantasSacrificio(Constantes.TRUE_TO_INTEGER);
            funias.setDistanciaPlantas((distancia!=null)
                                      ?Double.parseDouble(distancia) :null);
        }else if(fuente.equals("Pozo abandonado")){//aplica para otro y otras categorias
            funias.setPozosAbandonados(Constantes.TRUE_TO_INTEGER);
            funias.setDistanciaPozos((distancia!=null)
                                      ?Double.parseDouble(distancia) :null);
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

