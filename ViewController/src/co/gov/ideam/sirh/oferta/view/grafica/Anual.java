package co.gov.ideam.sirh.oferta.view.grafica;

import co.gov.ideam.sirh.oferta.model.SirhvOfertaMultianual;
import co.gov.ideam.sirh.oferta.model.SirhvOfertaMultianualMnsl;
import co.gov.ideam.sirh.util.ConstantesOferta;
import co.gov.ideam.sirh.util.IdeamException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Anual extends Grafica{
    private List maximos;
    private List minimos;
    private List medios;
    
    public void calcular(List datos)throws IdeamException{
        if(datos!=null && !datos.isEmpty()){
            this.medios = new ArrayList();
            this.maximos = new ArrayList();
            this.minimos = new ArrayList();
            
            Iterator it = datos.iterator();
            while(it.hasNext()){
                Object obj = it.next();
                SirhvOfertaMultianual dato1 = null;
                SirhvOfertaMultianualMnsl dato2 = null;
                
                if(obj instanceof SirhvOfertaMultianual){
                    dato1 = new SirhvOfertaMultianual();
                    dato1 = (SirhvOfertaMultianual) obj;
                }else if (obj instanceof SirhvOfertaMultianualMnsl){
                    dato2 = new SirhvOfertaMultianualMnsl();   
                    dato2 = (SirhvOfertaMultianualMnsl) obj;
                }
                
                if(dato1 != null){
                    //System.out.println("=======================dato1: "+dato1);
                    //System.out.println("=======================dato1 tipo: "+dato1.getTipo());
                    if(dato1.getTipo().equalsIgnoreCase(ConstantesOferta.BOX_PLOT_MEDIOS)){
                        this.medios.add(dato1);
                    }else if(dato1.getTipo().equalsIgnoreCase(ConstantesOferta.BOX_PLOT_MAXIMOS)){
                        this.maximos.add(dato1);
                    }else if(dato1.getTipo().equalsIgnoreCase(ConstantesOferta.BOX_PLOT_MINIMOS)){
                        this.minimos.add(dato1);
                    }
                }else if (dato2 != null){
                    //System.out.println("=======================dato2: "+dato2);
                    //System.out.println("=======================dato2 tipo: "+dato2.getTipo());
                    if(dato2.getTipo().equalsIgnoreCase(ConstantesOferta.BOX_PLOT_MEDIOS)){
                        this.medios.add(dato2);
                    }else if(dato2.getTipo().equalsIgnoreCase(ConstantesOferta.BOX_PLOT_MAXIMOS)){
                        this.maximos.add(dato2);
                    }else if(dato2.getTipo().equalsIgnoreCase(ConstantesOferta.BOX_PLOT_MINIMOS)){
                        this.minimos.add(dato2);
                    }
                }
            }
            
            /*
            System.out.println("====================MEDIOS: "+this.medios.size());
            System.out.println("====================MAXIMO: "+this.maximos.size());
            System.out.println("====================MINIMOS: "+this.minimos.size());
            */
        }
    }
    
    @Override
    public List<Object []> tabular()throws IdeamException{
        List<Object []> tabla = null;
        if(this.medios!=null && !this.medios.isEmpty()){
            int cont = 0;
            tabla = new ArrayList<Object []>();
            
            Iterator it = this.medios.iterator();
            while(it.hasNext()){
                Object obj = it.next();
                SirhvOfertaMultianual medio1 = null;
                SirhvOfertaMultianual maximo1 = null;
                SirhvOfertaMultianual minimo1 = null;
                SirhvOfertaMultianualMnsl medio2 = null;
                SirhvOfertaMultianualMnsl maximo2 = null;
                SirhvOfertaMultianualMnsl minimo2 = null;
                
                
                //tabla.add(new Object[] { ""+this.ejeX.get(cont), ""+valor, valor.doubleValue() });
                
                //System.out.println("=====================================Par Ordenado (x,y): ("+valor.doubleValue()+ ", " + this.ejeY.get(cont)+")");
                
                System.out.println("====================GRAFICA ANUAL: ");
                if(obj instanceof SirhvOfertaMultianual){
                    medio1 = (SirhvOfertaMultianual) obj;
                    maximo1 = (SirhvOfertaMultianual)this.maximos.get(cont);
                    minimo1 = (SirhvOfertaMultianual)this.minimos.get(cont);
                    
                    tabla.add(new Object[] { "Enero "+medio1.getAno(), "Medio" ,medio1.getEnero()});
                    tabla.add(new Object[] { "Febrero "+medio1.getAno(), "Medio" ,medio1.getFebrero()});
                    tabla.add(new Object[] { "Marzo "+medio1.getAno(), "Medio" ,medio1.getMarzo()});
                    tabla.add(new Object[] { "Abril "+medio1.getAno(), "Medio" ,medio1.getAbril()});
                    tabla.add(new Object[] { "Mayo "+medio1.getAno(), "Medio" ,medio1.getMayo()});
                    tabla.add(new Object[] { "Junio "+medio1.getAno(), "Medio" ,medio1.getJunio()});
                    tabla.add(new Object[] { "Julio "+medio1.getAno(), "Medio" ,medio1.getJulio()});
                    tabla.add(new Object[] { "Agosto "+medio1.getAno(), "Medio" ,medio1.getAgosto()});
                    tabla.add(new Object[] { "Septiembre "+medio1.getAno(), "Medio" ,medio1.getSeptiembre()});
                    tabla.add(new Object[] { "Octubre "+medio1.getAno(), "Medio" ,medio1.getOctubre()});
                    tabla.add(new Object[] { "Noviembre "+medio1.getAno(), "Medio" ,medio1.getNoviembre()});
                    tabla.add(new Object[] { "Diciembre "+medio1.getAno(), "Medio" ,medio1.getDiciembre()});
                    
                    tabla.add(new Object[] { "Enero "+medio1.getAno(), "Maximo" ,maximo1.getEnero()});
                    tabla.add(new Object[] { "Febrero "+medio1.getAno(), "Maximo" ,maximo1.getFebrero()});
                    tabla.add(new Object[] { "Marzo "+medio1.getAno(), "Maximo" ,maximo1.getMarzo()});
                    tabla.add(new Object[] { "Abril "+medio1.getAno(), "Maximo" ,maximo1.getAbril()});
                    tabla.add(new Object[] { "Mayo "+medio1.getAno(), "Maximo" ,maximo1.getMayo()});
                    tabla.add(new Object[] { "Junio "+medio1.getAno(), "Maximo" ,maximo1.getJunio()});
                    tabla.add(new Object[] { "Julio "+medio1.getAno(), "Maximo" ,maximo1.getJulio()});
                    tabla.add(new Object[] { "Agosto "+medio1.getAno(), "Maximo" ,maximo1.getAgosto()});
                    tabla.add(new Object[] { "Septiembre "+medio1.getAno(), "Maximo" ,maximo1.getSeptiembre()});
                    tabla.add(new Object[] { "Octubre "+medio1.getAno(), "Maximo" ,maximo1.getOctubre()});
                    tabla.add(new Object[] { "Noviembre "+medio1.getAno(), "Maximo" ,maximo1.getNoviembre()});
                    tabla.add(new Object[] { "Diciembre "+medio1.getAno(), "Maximo" ,maximo1.getDiciembre()});
                    
                    tabla.add(new Object[] { "Enero "+medio1.getAno(), "Minimo" ,minimo1.getEnero()});
                    tabla.add(new Object[] { "Febrero "+medio1.getAno(), "Minimo" ,minimo1.getFebrero()});
                    tabla.add(new Object[] { "Marzo "+medio1.getAno(), "Minimo" ,minimo1.getMarzo()});
                    tabla.add(new Object[] { "Abril "+medio1.getAno(), "Minimo" ,minimo1.getAbril()});
                    tabla.add(new Object[] { "Mayo "+medio1.getAno(), "Minimo" ,minimo1.getMayo()});
                    tabla.add(new Object[] { "Junio "+medio1.getAno(), "Minimo" ,minimo1.getJunio()});
                    tabla.add(new Object[] { "Julio "+medio1.getAno(), "Minimo" ,minimo1.getJulio()});
                    tabla.add(new Object[] { "Agosto "+medio1.getAno(), "Minimo" ,minimo1.getAgosto()});
                    tabla.add(new Object[] { "Septiembre "+medio1.getAno(), "Minimo" ,minimo1.getSeptiembre()});
                    tabla.add(new Object[] { "Octubre "+medio1.getAno(), "Minimo" ,minimo1.getOctubre()});
                    tabla.add(new Object[] { "Noviembre "+medio1.getAno(), "Minimo" ,minimo1.getNoviembre()});
                    tabla.add(new Object[] { "Diciembre "+medio1.getAno(), "Minimo" ,minimo1.getDiciembre()});
                    
                }else if (obj instanceof SirhvOfertaMultianualMnsl){ 
                    medio2 = (SirhvOfertaMultianualMnsl) obj;
                    maximo2 = (SirhvOfertaMultianualMnsl)this.maximos.get(cont);
                    minimo2 = (SirhvOfertaMultianualMnsl)this.minimos.get(cont);
                    
                    tabla.add(new Object[] { "Enero "+medio2.getAno(), "Medio" ,medio2.getEnero()});
                    tabla.add(new Object[] { "Febrero "+medio2.getAno(), "Medio" ,medio2.getFebrero()});
                    tabla.add(new Object[] { "Marzo "+medio2.getAno(), "Medio" ,medio2.getMarzo()});
                    tabla.add(new Object[] { "Abril "+medio2.getAno(), "Medio" ,medio2.getAbril()});
                    tabla.add(new Object[] { "Mayo "+medio2.getAno(), "Medio" ,medio2.getMayo()});
                    tabla.add(new Object[] { "Junio "+medio2.getAno(), "Medio" ,medio2.getJunio()});
                    tabla.add(new Object[] { "Julio "+medio2.getAno(), "Medio" ,medio2.getJulio()});
                    tabla.add(new Object[] { "Agosto "+medio2.getAno(), "Medio" ,medio2.getAgosto()});
                    tabla.add(new Object[] { "Septiembre "+medio2.getAno(), "Medio" ,medio2.getSeptiembre()});
                    tabla.add(new Object[] { "Octubre "+medio2.getAno(), "Medio" ,medio2.getOctubre()});
                    tabla.add(new Object[] { "Noviembre "+medio2.getAno(), "Medio" ,medio2.getNoviembre()});
                    tabla.add(new Object[] { "Diciembre "+medio2.getAno(), "Medio" ,medio2.getDiciembre()});
                    
                    tabla.add(new Object[] { "Enero "+medio2.getAno(), "Maximo" ,maximo2.getEnero()});
                    tabla.add(new Object[] { "Febrero "+medio2.getAno(), "Maximo" ,maximo2.getFebrero()});
                    tabla.add(new Object[] { "Marzo "+medio2.getAno(), "Maximo" ,maximo2.getMarzo()});
                    tabla.add(new Object[] { "Abril "+medio2.getAno(), "Maximo" ,maximo2.getAbril()});
                    tabla.add(new Object[] { "Mayo "+medio2.getAno(), "Maximo" ,maximo2.getMayo()});
                    tabla.add(new Object[] { "Junio "+medio2.getAno(), "Maximo" ,maximo2.getJunio()});
                    tabla.add(new Object[] { "Julio "+medio2.getAno(), "Maximo" ,maximo2.getJulio()});
                    tabla.add(new Object[] { "Agosto "+medio2.getAno(), "Maximo" ,maximo2.getAgosto()});
                    tabla.add(new Object[] { "Septiembre "+medio2.getAno(), "Maximo" ,maximo2.getSeptiembre()});
                    tabla.add(new Object[] { "Octubre "+medio2.getAno(), "Maximo" ,maximo2.getOctubre()});
                    tabla.add(new Object[] { "Noviembre "+medio2.getAno(), "Maximo" ,maximo2.getNoviembre()});
                    tabla.add(new Object[] { "Diciembre "+medio2.getAno(), "Maximo" ,maximo2.getDiciembre()});
                    
                    tabla.add(new Object[] { "Enero "+medio2.getAno(), "Minimo" ,minimo2.getEnero()});
                    tabla.add(new Object[] { "Febrero "+medio2.getAno(), "Minimo" ,minimo2.getFebrero()});
                    tabla.add(new Object[] { "Marzo "+medio2.getAno(), "Minimo" ,minimo2.getMarzo()});
                    tabla.add(new Object[] { "Abril "+medio2.getAno(), "Minimo" ,minimo2.getAbril()});
                    tabla.add(new Object[] { "Mayo "+medio2.getAno(), "Minimo" ,minimo2.getMayo()});
                    tabla.add(new Object[] { "Junio "+medio2.getAno(), "Minimo" ,minimo2.getJunio()});
                    tabla.add(new Object[] { "Julio "+medio2.getAno(), "Minimo" ,minimo2.getJulio()});
                    tabla.add(new Object[] { "Agosto "+medio2.getAno(), "Minimo" ,minimo2.getAgosto()});
                    tabla.add(new Object[] { "Septiembre "+medio2.getAno(), "Minimo" ,minimo2.getSeptiembre()});
                    tabla.add(new Object[] { "Octubre "+medio2.getAno(), "Minimo" ,minimo2.getOctubre()});
                    tabla.add(new Object[] { "Noviembre "+medio2.getAno(), "Minimo" ,minimo2.getNoviembre()});
                    tabla.add(new Object[] { "Diciembre "+medio2.getAno(), "Minimo" ,minimo2.getDiciembre()});
                }
                
            
                
                
                
                
                
                
                /*
                System.out.println("====================MEDIOS: ");
                System.out.println("=====================================Par Ordenado (x,y): (ENERO "+medio1.getAno() + ", " + medio1.getEnero() +")");
                System.out.println("=====================================Par Ordenado (x,y): (FEBRERO "+medio1.getAno() + ", " + medio1.getFebrero() +")");
                System.out.println("=====================================Par Ordenado (x,y): (MARZO "+medio1.getAno() + ", " + medio1.getMarzo() +")");
                System.out.println("=====================================Par Ordenado (x,y): (ABRIL "+medio1.getAno() + ", " + medio1.getAbril() +")");
                System.out.println("=====================================Par Ordenado (x,y): (MAYO "+medio1.getAno() + ", " + medio1.getMayo() +")");
                System.out.println("=====================================Par Ordenado (x,y): (JUNIO "+medio1.getAno() + ", " + medio1.getJunio() +")");
                System.out.println("=====================================Par Ordenado (x,y): (JULIO "+medio1.getAno() + ", " + medio1.getJulio() +")");
                System.out.println("=====================================Par Ordenado (x,y): (AGOSTO "+medio1.getAno() + ", " + medio1.getAgosto() +")");
                System.out.println("=====================================Par Ordenado (x,y): (SEPTIEMBRE "+medio1.getAno() + ", " + medio1.getSeptiembre() +")");
                System.out.println("=====================================Par Ordenado (x,y): (OCTUBRE "+medio1.getAno() + ", " + medio1.getOctubre() +")");
                System.out.println("=====================================Par Ordenado (x,y): (NOVIEMBRE "+medio1.getAno() + ", " + medio1.getNoviembre() +")");
                System.out.println("=====================================Par Ordenado (x,y): (DICIEMBRE "+medio1.getAno() + ", " + medio1.getDiciembre() +")");
                
                System.out.println("====================MAXIMOS: ");
                System.out.println("=====================================Par Ordenado (x,y): (ENERO "+maximo1.getAno() + ", " + maximo1.getEnero() +")");
                System.out.println("=====================================Par Ordenado (x,y): (FEBRERO "+maximo1.getAno() + ", " + maximo1.getFebrero() +")");
                System.out.println("=====================================Par Ordenado (x,y): (MARZO "+maximo1.getAno() + ", " + maximo1.getMarzo() +")");
                System.out.println("=====================================Par Ordenado (x,y): (ABRIL "+maximo1.getAno() + ", " + maximo1.getAbril() +")");
                System.out.println("=====================================Par Ordenado (x,y): (MAYO "+maximo1.getAno() + ", " + maximo1.getMayo() +")");
                System.out.println("=====================================Par Ordenado (x,y): (JUNIO "+maximo1.getAno() + ", " + maximo1.getJunio() +")");
                System.out.println("=====================================Par Ordenado (x,y): (JULIO "+maximo1.getAno() + ", " + maximo1.getJulio() +")");
                System.out.println("=====================================Par Ordenado (x,y): (AGOSTO "+maximo1.getAno() + ", " + maximo1.getAgosto() +")");
                System.out.println("=====================================Par Ordenado (x,y): (SEPTIEMBRE "+maximo1.getAno() + ", " + maximo1.getSeptiembre() +")");
                System.out.println("=====================================Par Ordenado (x,y): (OCTUBRE "+maximo1.getAno() + ", " + maximo1.getOctubre() +")");
                System.out.println("=====================================Par Ordenado (x,y): (NOVIEMBRE "+maximo1.getAno() + ", " + maximo1.getNoviembre() +")");
                System.out.println("=====================================Par Ordenado (x,y): (DICIEMBRE "+maximo1.getAno() + ", " + maximo1.getDiciembre() +")");
                
                
                System.out.println("====================MINIMOS: ");
                System.out.println("=====================================Par Ordenado (x,y): (ENERO "+minimo1.getAno() + ", " + minimo1.getEnero() +")");
                System.out.println("=====================================Par Ordenado (x,y): (FEBRERO "+minimo1.getAno() + ", " + minimo1.getFebrero() +")");
                System.out.println("=====================================Par Ordenado (x,y): (MARZO "+minimo1.getAno() + ", " + minimo1.getMarzo() +")");
                System.out.println("=====================================Par Ordenado (x,y): (ABRIL "+minimo1.getAno() + ", " + minimo1.getAbril() +")");
                System.out.println("=====================================Par Ordenado (x,y): (MAYO "+minimo1.getAno() + ", " + minimo1.getMayo() +")");
                System.out.println("=====================================Par Ordenado (x,y): (JUNIO "+minimo1.getAno() + ", " + minimo1.getJunio() +")");
                System.out.println("=====================================Par Ordenado (x,y): (JULIO "+minimo1.getAno() + ", " + minimo1.getJulio() +")");
                System.out.println("=====================================Par Ordenado (x,y): (AGOSTO "+minimo1.getAno() + ", " + minimo1.getAgosto() +")");
                System.out.println("=====================================Par Ordenado (x,y): (SEPTIEMBRE "+minimo1.getAno() + ", " + minimo1.getSeptiembre() +")");
                System.out.println("=====================================Par Ordenado (x,y): (OCTUBRE "+minimo1.getAno() + ", " + minimo1.getOctubre() +")");
                System.out.println("=====================================Par Ordenado (x,y): (NOVIEMBRE "+minimo1.getAno() + ", " + minimo1.getNoviembre() +")");
                System.out.println("=====================================Par Ordenado (x,y): (DICIEMBRE "+minimo1.getAno() + ", " + minimo1.getDiciembre() +")");
                */
                
                System.out.println("====================FIN GRAFICA ANUAL: ");
                cont ++;
            }
        }
        return tabla;     
    }
}
