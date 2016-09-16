package co.gov.ideam.sirh.oferta.view.grafica;

import co.gov.ideam.sirh.util.IdeamException;

import java.util.ArrayList;
import java.util.List;

public class Histograma extends Grafica{
    private List<Double> ejeY;
    private List<Double> ejeX;
    private Double valorRango;
    private double clasesFull;
    
    public void calcular(List datos)throws IdeamException{
        if(datos!=null && !datos.isEmpty()){
            //System.out.println("=============================================================HISTOGRAMA 1");
            this.clasesFull = this.clases(datos.size());
            //System.out.println("=============================================================HISTOGRAMA 2, clases: "+clases);
            List<Double> dataSort = Util.sortData((List<Double>)datos, Util.ASCENDENTE);
            //System.out.println("=============================================================HISTOGRAMA 3, datos ordenados");
            this.valorRango = this.rango((Double)dataSort.get(datos.size()-1), 
                                        (Double)dataSort.get(0), 
                                        this.clasesFull);
            //System.out.println("=============================================================HISTOGRAMA 4, rango: "+this.valorRango);
            this.ejeX = new ArrayList();
            /*
            for(Double valor : dataSort){
                System.out.println("========================Valor:"+valor);
            }
            */
            //adiciona valores al eje x.
            this.intervalos((Double)dataSort.get(datos.size()-1), 
                            (Double)dataSort.get(0), this.valorRango);
            
            //System.out.println("=============================================================HISTOGRAMA 5, intervalos:"+this.ejeX + ", "+(this.ejeX!=null?this.ejeX.size():0));
            if(this.ejeX != null){
                this.ejeY = new ArrayList();
                int cont = 0;
                for(Double intervalo : this.ejeX){
                    
                    Double rangoAux = this.valorRango;
                    
                    if(cont == this.ejeX.size()-1){
                        rangoAux = (dataSort.get(dataSort.size()-1) - intervalo) + 0.1D;
                    }
                    
                    this.ejeY.add(this.frecuencia(intervalo, rangoAux, dataSort));
                    
                    cont++;
                }
            }
            //System.out.println("=============================================================HISTOGRAMA 6, valores:"+this.ejeY+ ", "+(this.ejeY!=null?this.ejeY.size():0));
            
        }
    }
    
    @Override
    public List<Object []> tabular()throws IdeamException{
        System.out.println("=============================================================TABULAR HISTOGRAMA");
        List<Object []> tabla = null;
        if(this.ejeY!=null && !this.ejeY.isEmpty()){
            //System.out.println("=============================================================TABULAR 1");
            int cont = 0;
            tabla = new ArrayList<Object []>();
            for(Double valor : this.ejeX){
                //System.out.println("=============================================================TABULAR 2");
                Double etiquetaX = (valor.doubleValue() + this.valorRango) / 2; // etiquetar la mtad del rango en la grafica.
                tabla.add(new Object[] { df.format(etiquetaX), "", this.ejeY.get(cont)});
                cont ++;
            }
        
        }
        return tabla;  
    }
    
    private Double clases(Integer n){
        Double clases = (1 + (3.3 * Math.log10(n.doubleValue())));
        //System.out.println("Clases sin aprox: " +  clases);
        
        clases = Math.ceil(clases);// clases.ceil();
        
        //System.out.println("Clases con aprox: " +  clases);
        
        
        if(clases>20){
            clases = 20.0;    
        } else if (clases<5){
            clases = 5.0;    
        }
        return clases;
    }
    
    private double rango(double max, double min, double clases){
        return ((max - min) / clases);
    }
    
    private void intervalos(double max, double min, double rango){
        if(min<max){
            
            if(this.ejeX.size()>=this.clasesFull){
               return;
            }
            
            this.ejeX.add(min);
            //System.out.println("=============================================================Intervalo: "+min);
            
                
            this.intervalos(max, (min + rango), rango);
        }
    }
    
    private Double frecuencia(double intervaloMenor, double rango, List<Double> data){
        double cantidad = 0;
        if(data!=null){
            for(Double valor : data){
                 if(valor >= intervaloMenor && valor < (intervaloMenor + rango)){
                     cantidad++;
                 }
            }    
        }
        //System.out.println("=============================================================Intervalo: "+intervaloMenor + " - " + (intervaloMenor + rango));
        //System.out.println("=============================================================Frecuencia: "+cantidad);
        return cantidad;
    }
}
