package co.gov.ideam.sirh.oferta.view.grafica;

import co.gov.ideam.sirh.oferta.model.ShmvDiariosHid;
import co.gov.ideam.sirh.oferta.model.ShmvMensualesHid;
import co.gov.ideam.sirh.util.IdeamException;

import java.util.List;

public class Validacion {
    public static Long cantidadDatos(List datos) throws IdeamException{
        Long valor = new Long(0);
        if(datos!=null && !datos.isEmpty()){
            if(datos.get(0) instanceof ShmvDiariosHid){
                for(ShmvDiariosHid diario : (List<ShmvDiariosHid>)datos ){
                    valor += diario.getCantidadDatosValid();
                }
            }else if(datos.get(0) instanceof ShmvMensualesHid){
                for(ShmvMensualesHid mes : (List<ShmvMensualesHid>)datos ){
                    valor += mes.getCantidadDatosValid();
                }
            }
        }
        return valor;
    }
}
