package co.gov.ideam.sirh.oferta.view.grafica;

import co.gov.ideam.sirh.oferta.model.ShmvDiariosHid;
import co.gov.ideam.sirh.oferta.model.ShmvMensualesHid;
import co.gov.ideam.sirh.util.ConstantesOferta;
import co.gov.ideam.sirh.util.IdeamException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Historico extends Grafica{
    
    private List<Double> ejeY;
    private List<String> ejeX;
    
    public void calcular(List datos)throws IdeamException{
        if(datos!=null && !datos.isEmpty()){
            this.ejeY = new ArrayList();
            this.ejeX = new ArrayList();
            if(datos.get(0) instanceof ShmvDiariosHid){
                System.out.println("----------------------ES DIARIA: ");
                Iterator it = datos.iterator();
                while (it.hasNext()){
                    int cont = 0;
                    //System.out.println("----------------------RECORRE: ");
                    ShmvDiariosHid dia = (ShmvDiariosHid)it.next();
                    
                    //if(dia.getCantidadDatosValid()>((ConstantesOferta.PORCENTAJE_DATOS_MIN_HISTORICO*31/100))){
                        cont++;
                        
                        this.ejeY.add(dia.getDrsValor1());
                        this.ejeX.add(dia.getDrsAno()+"-"+dia.getDrsMes()+"-"+cont);
                        cont++;
                        
                        this.ejeY.add(dia.getDrsValor2());
                        this.ejeX.add(dia.getDrsAno()+"-"+dia.getDrsMes()+"-"+cont);
                        cont++;
                        
                        this.ejeY.add(dia.getDrsValor3());
                        this.ejeX.add(dia.getDrsAno()+"-"+dia.getDrsMes()+"-"+cont);
                        cont++;
                        
                        this.ejeY.add(dia.getDrsValor4());
                        this.ejeX.add(dia.getDrsAno()+"-"+dia.getDrsMes()+"-"+cont);
                        cont++;
                        
                        this.ejeY.add(dia.getDrsValor5());
                        this.ejeX.add(dia.getDrsAno()+"-"+dia.getDrsMes()+"-"+cont);
                        cont++;
                        
                        this.ejeY.add(dia.getDrsValor6());
                        this.ejeX.add(dia.getDrsAno()+"-"+dia.getDrsMes()+"-"+cont);
                        cont++;
                        
                        this.ejeY.add(dia.getDrsValor7());
                        this.ejeX.add(dia.getDrsAno()+"-"+dia.getDrsMes()+"-"+cont);
                        cont++;
                        
                        this.ejeY.add(dia.getDrsValor8());
                        this.ejeX.add(dia.getDrsAno()+"-"+dia.getDrsMes()+"-"+cont);
                        cont++;
                        
                        this.ejeY.add(dia.getDrsValor9());
                        this.ejeX.add(dia.getDrsAno()+"-"+dia.getDrsMes()+"-"+cont);
                        cont++;
                        
                        this.ejeY.add(dia.getDrsValor10());
                        this.ejeX.add(dia.getDrsAno()+"-"+dia.getDrsMes()+"-"+cont);
                        cont++;
                        
                        this.ejeY.add(dia.getDrsValor11());
                        this.ejeX.add(dia.getDrsAno()+"-"+dia.getDrsMes()+"-"+cont);
                        cont++;
                        
                        this.ejeY.add(dia.getDrsValor12());
                        this.ejeX.add(dia.getDrsAno()+"-"+dia.getDrsMes()+"-"+cont);
                        cont++;
                        
                        this.ejeY.add(dia.getDrsValor13());
                        this.ejeX.add(dia.getDrsAno()+"-"+dia.getDrsMes()+"-"+cont);
                        cont++;
                        
                        this.ejeY.add(dia.getDrsValor14());
                        this.ejeX.add(dia.getDrsAno()+"-"+dia.getDrsMes()+"-"+cont);
                        cont++;
                        
                        this.ejeY.add(dia.getDrsValor15());
                        this.ejeX.add(dia.getDrsAno()+"-"+dia.getDrsMes()+"-"+cont);
                        cont++;
                        
                        this.ejeY.add(dia.getDrsValor16());
                        this.ejeX.add(dia.getDrsAno()+"-"+dia.getDrsMes()+"-"+cont);
                        cont++;
                        
                        this.ejeY.add(dia.getDrsValor17());
                        this.ejeX.add(dia.getDrsAno()+"-"+dia.getDrsMes()+"-"+cont);
                        cont++;
                        
                        this.ejeY.add(dia.getDrsValor18());
                        this.ejeX.add(dia.getDrsAno()+"-"+dia.getDrsMes()+"-"+cont);
                        cont++;
                        
                        this.ejeY.add(dia.getDrsValor19());
                        this.ejeX.add(dia.getDrsAno()+"-"+dia.getDrsMes()+"-"+cont);
                        cont++;
                        
                        this.ejeY.add(dia.getDrsValor20());
                        this.ejeX.add(dia.getDrsAno()+"-"+dia.getDrsMes()+"-"+cont);
                        cont++;
                        
                        this.ejeY.add(dia.getDrsValor21());
                        this.ejeX.add(dia.getDrsAno()+"-"+dia.getDrsMes()+"-"+cont);
                        cont++;
                        
                        this.ejeY.add(dia.getDrsValor22());
                        this.ejeX.add(dia.getDrsAno()+"-"+dia.getDrsMes()+"-"+cont);
                        cont++;
                        
                        this.ejeY.add(dia.getDrsValor23());
                        this.ejeX.add(dia.getDrsAno()+"-"+dia.getDrsMes()+"-"+cont);
                        cont++;
                        
                        this.ejeY.add(dia.getDrsValor24());
                        this.ejeX.add(dia.getDrsAno()+"-"+dia.getDrsMes()+"-"+cont);
                        cont++;
                        
                        this.ejeY.add(dia.getDrsValor25());
                        this.ejeX.add(dia.getDrsAno()+"-"+dia.getDrsMes()+"-"+cont);
                        cont++;
                        
                        this.ejeY.add(dia.getDrsValor26());
                        this.ejeX.add(dia.getDrsAno()+"-"+dia.getDrsMes()+"-"+cont);
                        cont++;
                        
                        this.ejeY.add(dia.getDrsValor27());
                        this.ejeX.add(dia.getDrsAno()+"-"+dia.getDrsMes()+"-"+cont);
                        cont++;
                        
                        this.ejeY.add(dia.getDrsValor28());
                        this.ejeX.add(dia.getDrsAno()+"-"+dia.getDrsMes()+"-"+cont);
                        cont++;
                        
                        this.ejeY.add(dia.getDrsValor29());
                        this.ejeX.add(dia.getDrsAno()+"-"+dia.getDrsMes()+"-"+cont);
                        cont++;
                        
                        this.ejeY.add(dia.getDrsValor30());
                        this.ejeX.add(dia.getDrsAno()+"-"+dia.getDrsMes()+"-"+cont);
                        cont++;
                        
                        this.ejeY.add(dia.getDrsValor31());
                        this.ejeX.add(dia.getDrsAno()+"-"+dia.getDrsMes()+"-"+cont);
                        cont++;
                    //}
                }
            } else if(datos.get(0) instanceof ShmvMensualesHid){
                System.out.println("----------------------ES MENSUAL: ");
                Iterator it = datos.iterator();
                while (it.hasNext()){
                    int cont = 0;
                    //System.out.println("----------------------RECORRE: ");
                    ShmvMensualesHid mes = (ShmvMensualesHid)it.next();
                    
                    //if(mes.getCantidadDatosValid()>((ConstantesOferta.PORCENTAJE_DATOS_MIN_HISTORICO*12/100))){
                        cont++;
                        
                        this.ejeY.add(mes.getMnsValor1());
                        this.ejeX.add(mes.getMnsAno()+"-"+cont);
                        cont++;
                        
                        this.ejeY.add(mes.getMnsValor2());
                        this.ejeX.add(mes.getMnsAno()+"-"+cont);
                        cont++;
                        
                        this.ejeY.add(mes.getMnsValor3());
                        this.ejeX.add(mes.getMnsAno()+"-"+cont);
                        cont++;
                        
                        this.ejeY.add(mes.getMnsValor4());
                        this.ejeX.add(mes.getMnsAno()+"-"+cont);
                        cont++;
                        
                        this.ejeY.add(mes.getMnsValor5());
                        this.ejeX.add(mes.getMnsAno()+"-"+cont);
                        cont++;
                        
                        this.ejeY.add(mes.getMnsValor6());
                        this.ejeX.add(mes.getMnsAno()+"-"+cont);
                        cont++;
                        
                        this.ejeY.add(mes.getMnsValor7());
                        this.ejeX.add(mes.getMnsAno()+"-"+cont);
                        cont++;
                        
                        this.ejeY.add(mes.getMnsValor8());
                        this.ejeX.add(mes.getMnsAno()+"-"+cont);
                        cont++;
                        
                        this.ejeY.add(mes.getMnsValor9());
                        this.ejeX.add(mes.getMnsAno()+"-"+cont);
                        cont++;
                        
                        this.ejeY.add(mes.getMnsValor10());
                        this.ejeX.add(mes.getMnsAno()+"-"+cont);
                        cont++;
                        
                        this.ejeY.add(mes.getMnsValor11());
                        this.ejeX.add(mes.getMnsAno()+"-"+cont);
                        cont++;
                        
                        this.ejeY.add(mes.getMnsValor12());
                        this.ejeX.add(mes.getMnsAno()+"-"+cont);
                        cont++;
                    //}
                }
            } 
        }
    }
    
    @Override
    public List<Object []> tabular()throws IdeamException{
        List<Object []> tabla = null;
        if(this.ejeY!=null && !this.ejeY.isEmpty()){
            int cont = 0;
            tabla = new ArrayList<Object []>();
            for(String valor : this.ejeX){
                tabla.add(new Object[] { valor, "", this.ejeY.get(cont)});
                cont ++;
            }
        }
        return tabla;  
    }
}
