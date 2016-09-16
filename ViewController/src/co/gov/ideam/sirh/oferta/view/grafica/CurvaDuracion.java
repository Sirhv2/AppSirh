package co.gov.ideam.sirh.oferta.view.grafica;

import co.gov.ideam.sirh.util.IdeamException;

import java.util.ArrayList;
import java.util.List;

public class CurvaDuracion extends Grafica{
    private List<Double> ejeY;
    private List<Double> ejeX;
    
    private double areaTotal = 0;
    private double area50 = 0;
    private double areaPunto50 = 0;
    private double areaTotal50 = 0;
    private double irh = 0;
    private String categoriaIrh = ""; 
    
    public void calcular(List datos)throws IdeamException{
        if(datos!=null && !datos.isEmpty()){

            System.out.println("CALCULA GRAFICA\r\n");
            System.out.println("Cantidad " + datos.size() + "\r\n");
            
            this.ejeY = Util.sortData((List<Double>)datos, Util.DESCENDENTE); // eje y caudal
            this.ejeX = new ArrayList(); // eje x con porcentajes
            
            int cont = 1;
            
            for(Double dato : this.ejeY){
                Double valor = (double)((double)cont / (double)(this.ejeY.size()+1))*(double)100;
                this.ejeX.add(valor);
                
                cont ++; 
            }

            System.out.println("DATOS IRH\r\n");
            
            // calcula IRH
            cont = 0;            
            boolean punto50= false;
            
            for(Double dato : this.ejeY){

                // calcular area de la curva 
                if (cont < (this.ejeY.size() - 1)) {
                    
                    System.out.println("y+1:" + ejeY.get(cont + 1 ));
                    System.out.println("y  :" + ejeY.get(cont ));
                    
                    System.out.println("x+1  :" + ejeX.get(cont + 1));
                    System.out.println("x  :" + ejeX.get(cont ));
                    
                    Double temp = 0.5 * (ejeY.get(cont + 1 ) + ejeY.get(cont )) * ( ejeX.get(cont + 1) - ejeX.get(cont)) / 100;
                    
                    System.out.println("temp  :" + temp);
                    
                    areaTotal += temp;
                    
                    if (ejeX.get(cont) >= 50){
                        
                        System.out.println("LLEGO 50%:" + ejeX.get(cont));
                        area50 += temp;
                    
                        if (!punto50) {
                            areaPunto50 = ejeY.get(cont) * 0.5;  
                            punto50 = true;
                        }
                    }
                }
                cont ++; 
            }
            
            areaTotal50 = areaPunto50 + area50;
            
            if (areaTotal != 0) {
                irh = areaTotal50 * 1.0 / areaTotal;
                System.out.println("CALCULA IRH " + irh);
            }
            
            categoriaIrh = "Muy Bajo";

            if (irh >= 0.50)
                categoriaIrh = "Bajo";

            if (irh >= 0.65)
                categoriaIrh = "Medio";

            if (irh >= 0.75)
                categoriaIrh = "Alto";
            
            if (irh >= 0.85)
                categoriaIrh = "Muy Alto";
            
            System.out.println("DATOS IRH\r\n");
            System.out.println("TOTAL PUNTOS:" + ejeY.size());
            System.out.println("AREA TOTAL:" + areaTotal);
            System.out.println("AREA >50:" + area50);
            System.out.println("AREA PUNTO 50:" + areaPunto50);
            System.out.println("AREA TOTAL >=50:" + areaTotal50);
            System.out.println("IRH:" + irh);
            System.out.println("CATEGORIA:" + categoriaIrh);

        }
    }
    
    @Override
    public List<Object []> tabular()throws IdeamException{
        List<Object []> tabla = null;
        if(this.ejeY!=null && !this.ejeY.isEmpty()){
            int cont = 0;
            tabla = new ArrayList<Object []>();
            System.out.println("===============================CURVA DURACION");
            //System.out.println("===============================X: "+this.ejeX.size());
            //System.out.println("===============================Y: "+this.ejeY.size());
            for(Double valor : this.ejeX){
                //tabla.add(new Object[] { ""+this.ejeX.get(cont), ""+valor, valor.doubleValue() });
                
                //System.out.println("=====================================Par Ordenado (x,y): ("+valor.doubleValue()+ ", " + this.ejeY.get(cont)+")");
                
                tabla.add(new Object[] { super.df.format(valor.doubleValue()), "", this.ejeY.get(cont)});
                cont ++;
            }
            System.out.println("===============================FIN CURVA DURACION");
        }
        return tabla;     
    }


    public double getAreaTotal() {
        return areaTotal;
    }

    public void setArea50(double area50) {
        this.area50 = area50;
    }

    public double getArea50() {
        return area50;
    }

    public double getAreaPunto50() {
        return areaPunto50;
    }

    public double getAreaTotal50() {
        return areaTotal50;
    }

    public double getIrh() {
        return irh;
    }

    public String getCategoriaIrh() {
        return categoriaIrh;
    }
}
