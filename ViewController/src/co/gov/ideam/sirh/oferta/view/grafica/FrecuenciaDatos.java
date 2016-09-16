package co.gov.ideam.sirh.oferta.view.grafica;


import co.gov.ideam.sirh.oferta.model.ShmvDiariosHid;
import co.gov.ideam.sirh.oferta.model.ShmvMensualesHid;
import co.gov.ideam.sirh.util.ConstantesOferta;
import co.gov.ideam.sirh.util.IdeamException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class FrecuenciaDatos extends Grafica {
    
    private SortedMap datosAnuales;
    
    public void calcular(List datos)throws IdeamException{
        if(datos!=null && !datos.isEmpty()){
            this.datosAnuales = new TreeMap();
            if(datos.get(0) instanceof ShmvDiariosHid){
                for(ShmvDiariosHid diario : (List<ShmvDiariosHid>)datos ){
                    if(diario.getCantidadDatosValid()>((ConstantesOferta.PORCENTAJE_DATOS_MIN*365/100))){
                        if (!this.datosAnuales.containsKey(diario.getDrsAno())) {
                            this.datosAnuales.put(diario.getDrsAno(),
                                                  (diario.getSumaTotalValid() /
                                                   (double)((diario.getCantidadDatosValid() > 0) 
                                                            ? diario.getCantidadDatosValid() 
                                                            : 1)));
                        } else {
                            Double valor = (Double)this.datosAnuales.get(diario.getDrsAno());//lo que tiene
                            valor +=  (diario.getSumaTotalValid() / 
                                       (double)((diario.getCantidadDatosValid()>0)
                                       ?diario.getCantidadDatosValid()
                                       :1));//mas lo nuevo
                            this.datosAnuales.remove(diario.getDrsAno());
                            this.datosAnuales.put(diario.getDrsAno(), valor);
                        }
                    }
                }
            }else if(datos.get(0) instanceof ShmvMensualesHid){
                for(ShmvMensualesHid mes : (List<ShmvMensualesHid>)datos ){
                    if(mes.getCantidadDatosValid()>((ConstantesOferta.PORCENTAJE_DATOS_MIN*12/100))){
                        if(!this.datosAnuales.containsKey(mes.getMnsAno())){
                            this.datosAnuales.put(mes.getMnsAno(), 
                                                  (mes.getSumaTotalValid()/
                                                   (double)((mes.getCantidadDatosValid()>0)
                                                   ?mes.getCantidadDatosValid()
                                                   :1)));
                        }
                    }
                }
            }
        }
    }
    
    @Override
    public List<Object []> tabular()throws IdeamException{
        List<Object []> tabla = null;
        if(this.datosAnuales!=null && this.datosAnuales.size()>0){
            tabla = new ArrayList<Object []>();
            
            Set<Integer> llavesAux = this.datosAnuales.keySet();
            Collection<Double> valuesAux = this.datosAnuales.values();
            
            List<Integer> llaves = new ArrayList();
            List<Double> values = new ArrayList();
            
            for(Integer llave : llavesAux){
                llaves.add(llave);
            }
            for(Double value : valuesAux){
                values.add(value);
            }
            
            
            for(int i=0; i<this.datosAnuales.size(); i++){
                //tabla.add(new Object[] { "x", "Serie", "y"});
                tabla.add(new Object[] { llaves.get(i), "", values.get(i)});
            }
        }
        return tabla;  
    }
    
}
