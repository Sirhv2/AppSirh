package co.gov.ideam.sirh.oferta.view.grafica;

import co.gov.ideam.sirh.oferta.model.SirhvOfertaMultianual;
import co.gov.ideam.sirh.oferta.model.SirhvOfertaMultianualMnsl;
import co.gov.ideam.sirh.util.ConstantesOferta;
import co.gov.ideam.sirh.util.IdeamException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Multianual extends Grafica{
    
    private List maximos;
    private List minimos;
    private List medios;
    private List mediosAgno;
    
    public void calcular(List datos)throws IdeamException{
        if(datos!=null && !datos.isEmpty()){
            
        }
        
    }
    
    public void calcular(List serie, List resumen)throws IdeamException{
        
        if(resumen!=null && !resumen.isEmpty()){
            this.medios = new ArrayList();
            this.maximos = new ArrayList();
            this.minimos = new ArrayList();
            this.mediosAgno = new ArrayList();
            
            Iterator it = resumen.iterator();
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
                    if(dato1.getTipo().equalsIgnoreCase(ConstantesOferta.BOX_PLOT_MEDIOS)){
                        this.medios.add(dato1);
                    }else if(dato1.getTipo().equalsIgnoreCase(ConstantesOferta.BOX_PLOT_MAXIMOS)){
                        this.maximos.add(dato1);
                    }else if(dato1.getTipo().equalsIgnoreCase(ConstantesOferta.BOX_PLOT_MINIMOS)){
                        this.minimos.add(dato1);
                    }
                }else if (dato2 != null){
                    if(dato2.getTipo().equalsIgnoreCase(ConstantesOferta.BOX_PLOT_MEDIOS)){
                        this.medios.add(dato2);
                    }else if(dato2.getTipo().equalsIgnoreCase(ConstantesOferta.BOX_PLOT_MAXIMOS)){
                        this.maximos.add(dato2);
                    }else if(dato2.getTipo().equalsIgnoreCase(ConstantesOferta.BOX_PLOT_MINIMOS)){
                        this.minimos.add(dato2);
                    }
                }               
            }
        }
        
        if(serie!=null && !serie.isEmpty()){
            
            Iterator it1 = serie.iterator();
            while(it1.hasNext()){
                Object obj = it1.next();
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
                    System.out.println("=======MEDIOS AGNO: datos1: "+dato1.getTipo());
                    if(dato1.getTipo().equalsIgnoreCase(ConstantesOferta.BOX_PLOT_MEDIOS)){
                        this.mediosAgno.add(dato1);
                    }
                    
                    System.out.println("======================Objeto: "+dato1);
                    System.out.println("======================Tipo: "+dato1.getTipo());
                }else if (dato2 != null){
                    System.out.println("=======MEDIOS AGNO: datos2: "+dato2.getTipo());
                    if(dato2.getTipo().equalsIgnoreCase(ConstantesOferta.BOX_PLOT_MEDIOS)){
                        this.mediosAgno.add(dato2);
                    }
                    
                    System.out.println("======================Objeto: "+dato2);
                    System.out.println("======================Tipo: "+dato2.getTipo());
                }
            }     
        }
            
        System.out.println("====================MEDIOS: "+this.medios.size());
        System.out.println("====================MAXIMO: "+this.maximos.size());
        System.out.println("====================MINIMOS: "+this.minimos.size());
        System.out.println("====================MEDIOS AÑO: "+this.mediosAgno.size());
            
        
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
                SirhvOfertaMultianual medioAgno1 = null;
                SirhvOfertaMultianualMnsl medio2 = null;
                SirhvOfertaMultianualMnsl maximo2 = null;
                SirhvOfertaMultianualMnsl minimo2 = null;
                SirhvOfertaMultianualMnsl medioAgno2 = null;
                
                //tabla.add(new Object[] { ""+this.ejeX.get(cont), ""+valor, valor.doubleValue() });
                
                //System.out.println("=====================================Par Ordenado (x,y): ("+valor.doubleValue()+ ", " + this.ejeY.get(cont)+")");
                
                System.out.println("====================GRAFICA MULTIANUAL: ");
                


                    if(obj instanceof SirhvOfertaMultianual){
                        medio1 = (SirhvOfertaMultianual) obj;
                        maximo1 = (SirhvOfertaMultianual)this.maximos.get(cont);
                        minimo1 = (SirhvOfertaMultianual)this.minimos.get(cont);

                        tabla.add(new Object[] { "Enero "/*+medio1.getAno()*/, "Maximo" ,maximo1.getEnero()});
                        tabla.add(new Object[] { "Febrero "/*+medio1.getAno()*/, "Maximo" ,maximo1.getFebrero()});
                        tabla.add(new Object[] { "Marzo "/*+medio1.getAno()*/, "Maximo" ,maximo1.getMarzo()});
                        tabla.add(new Object[] { "Abril "/*+medio1.getAno()*/, "Maximo" ,maximo1.getAbril()});
                        tabla.add(new Object[] { "Mayo "/*+medio1.getAno()*/, "Maximo" ,maximo1.getMayo()});
                        tabla.add(new Object[] { "Junio "/*+medio1.getAno()*/, "Maximo" ,maximo1.getJunio()});
                        tabla.add(new Object[] { "Julio "/*+medio1.getAno()*/, "Maximo" ,maximo1.getJulio()});
                        tabla.add(new Object[] { "Agosto "/*+medio1.getAno()*/, "Maximo" ,maximo1.getAgosto()});
                        tabla.add(new Object[] { "Septiembre "/*+medio1.getAno()*/, "Maximo" ,maximo1.getSeptiembre()});
                        tabla.add(new Object[] { "Octubre "/*+medio1.getAno()*/, "Maximo" ,maximo1.getOctubre()});
                        tabla.add(new Object[] { "Noviembre "/*+medio1.getAno()*/, "Maximo" ,maximo1.getNoviembre()});
                        tabla.add(new Object[] { "Diciembre "/*+medio1.getAno()*/, "Maximo" ,maximo1.getDiciembre()});
                        
                        tabla.add(new Object[] { "Enero "/*+medio1.getAno()*/, "Medio" ,medio1.getEnero()});
                        tabla.add(new Object[] { "Febrero "/*+medio1.getAno()*/, "Medio" ,medio1.getFebrero()});
                        tabla.add(new Object[] { "Marzo "/*+medio1.getAno()*/, "Medio" ,medio1.getMarzo()});
                        tabla.add(new Object[] { "Abril "/*+medio1.getAno()*/, "Medio" ,medio1.getAbril()});
                        tabla.add(new Object[] { "Mayo "/*+medio1.getAno()*/, "Medio" ,medio1.getMayo()});
                        tabla.add(new Object[] { "Junio "/*+medio1.getAno()*/, "Medio" ,medio1.getJunio()});
                        tabla.add(new Object[] { "Julio "/*+medio1.getAno()*/, "Medio" ,medio1.getJulio()});
                        tabla.add(new Object[] { "Agosto "/*+medio1.getAno()*/, "Medio" ,medio1.getAgosto()});
                        tabla.add(new Object[] { "Septiembre "/*+medio1.getAno()*/, "Medio" ,medio1.getSeptiembre()});
                        tabla.add(new Object[] { "Octubre "/*+medio1.getAno()*/, "Medio" ,medio1.getOctubre()});
                        tabla.add(new Object[] { "Noviembre "/*+medio1.getAno()*/, "Medio" ,medio1.getNoviembre()});
                        tabla.add(new Object[] { "Diciembre "/*+medio1.getAno()*/, "Medio" ,medio1.getDiciembre()});
                        
                        tabla.add(new Object[] { "Enero "/*+medio1.getAno()*/, "Minimo" ,minimo1.getEnero()});
                        tabla.add(new Object[] { "Febrero "/*+medio1.getAno()*/, "Minimo" ,minimo1.getFebrero()});
                        tabla.add(new Object[] { "Marzo "/*+medio1.getAno()*/, "Minimo" ,minimo1.getMarzo()});
                        tabla.add(new Object[] { "Abril "/*+medio1.getAno()*/, "Minimo" ,minimo1.getAbril()});
                        tabla.add(new Object[] { "Mayo "/*+medio1.getAno()*/, "Minimo" ,minimo1.getMayo()});
                        tabla.add(new Object[] { "Junio "/*+medio1.getAno()*/, "Minimo" ,minimo1.getJunio()});
                        tabla.add(new Object[] { "Julio "/*+medio1.getAno()*/, "Minimo" ,minimo1.getJulio()});
                        tabla.add(new Object[] { "Agosto "/*+medio1.getAno()*/, "Minimo" ,minimo1.getAgosto()});
                        tabla.add(new Object[] { "Septiembre "/*+medio1.getAno()*/, "Minimo" ,minimo1.getSeptiembre()});
                        tabla.add(new Object[] { "Octubre "/*+medio1.getAno()*/, "Minimo" ,minimo1.getOctubre()});
                        tabla.add(new Object[] { "Noviembre "/*+medio1.getAno()*/, "Minimo" ,minimo1.getNoviembre()});
                        tabla.add(new Object[] { "Diciembre "/*+medio1.getAno()*/, "Minimo" ,minimo1.getDiciembre()});
                        
                        
                        if(this.mediosAgno!=null && !this.mediosAgno.isEmpty()){
                            
                            medioAgno1 = (SirhvOfertaMultianual)this.mediosAgno.get(cont);
                            tabla.add(new Object[] { "Enero "/*+medio1.getAno()*/, "Medio Anual" ,medioAgno1.getEnero()});
                            tabla.add(new Object[] { "Febrero "/*+medio1.getAno()*/, "Medio Anual" ,medioAgno1.getFebrero()});
                            tabla.add(new Object[] { "Marzo "/*+medio1.getAno()*/, "Medio Anual" ,medioAgno1.getMarzo()});
                            tabla.add(new Object[] { "Abril "/*+medio1.getAno()*/, "Medio Anual" ,medioAgno1.getAbril()});
                            tabla.add(new Object[] { "Mayo "/*+medio1.getAno()*/, "Medio Anual" ,medioAgno1.getMayo()});
                            tabla.add(new Object[] { "Junio "/*+medio1.getAno()*/, "Medio Anual" ,medioAgno1.getJunio()});
                            tabla.add(new Object[] { "Julio "/*+medio1.getAno()*/, "Medio Anual" ,medioAgno1.getJulio()});
                            tabla.add(new Object[] { "Agosto "/*+medio1.getAno()*/, "Medio Anual" ,medioAgno1.getAgosto()});
                            tabla.add(new Object[] { "Septiembre "/*+medio1.getAno()*/, "Medio Anual" ,medioAgno1.getSeptiembre()});
                            tabla.add(new Object[] { "Octubre "/*+medio1.getAno()*/, "Medio Anual" ,medioAgno1.getOctubre()});
                            tabla.add(new Object[] { "Noviembre "/*+medio1.getAno()*/, "Medio Anual" ,medioAgno1.getNoviembre()});
                            tabla.add(new Object[] { "Diciembre "/*+medio1.getAno()*/, "Medio Anual" ,medioAgno1.getDiciembre()});
                        
                        }
                    }else if (obj instanceof SirhvOfertaMultianualMnsl){ 
                        medio2 = (SirhvOfertaMultianualMnsl) obj;
                        maximo2 = (SirhvOfertaMultianualMnsl)this.maximos.get(cont);
                        minimo2 = (SirhvOfertaMultianualMnsl)this.minimos.get(cont);
                        
                        tabla.add(new Object[] { "Enero "/*+medio2.getAno()*/, "Maximo" ,maximo2.getEnero()});
                        tabla.add(new Object[] { "Febrero "/*+medio2.getAno()*/, "Maximo" ,maximo2.getFebrero()});
                        tabla.add(new Object[] { "Marzo "/*+medio2.getAno()*/, "Maximo" ,maximo2.getMarzo()});
                        tabla.add(new Object[] { "Abril "/*+medio2.getAno()*/, "Maximo" ,maximo2.getAbril()});
                        tabla.add(new Object[] { "Mayo "/*+medio2.getAno()*/, "Maximo" ,maximo2.getMayo()});
                        tabla.add(new Object[] { "Junio "/*+medio2.getAno()*/, "Maximo" ,maximo2.getJunio()});
                        tabla.add(new Object[] { "Julio "/*+medio2.getAno()*/, "Maximo" ,maximo2.getJulio()});
                        tabla.add(new Object[] { "Agosto "/*+medio2.getAno()*/, "Maximo" ,maximo2.getAgosto()});
                        tabla.add(new Object[] { "Septiembre "/*+medio2.getAno()*/, "Maximo" ,maximo2.getSeptiembre()});
                        tabla.add(new Object[] { "Octubre "/*+medio2.getAno()*/, "Maximo" ,maximo2.getOctubre()});
                        tabla.add(new Object[] { "Noviembre "/*+medio2.getAno()*/, "Maximo" ,maximo2.getNoviembre()});
                        tabla.add(new Object[] { "Diciembre "/*+medio2.getAno()*/, "Maximo" ,maximo2.getDiciembre()});
                        
                        tabla.add(new Object[] { "Enero "/*+medio2.getAno()*/, "Medio",medio2.getEnero()});
                        tabla.add(new Object[] { "Febrero "/*+medio2.getAno()*/, "Medio" ,medio2.getFebrero()});
                        tabla.add(new Object[] { "Marzo "/*+medio2.getAno()*/, "Medio" ,medio2.getMarzo()});
                        tabla.add(new Object[] { "Abril "/*+medio2.getAno()*/, "Medio" ,medio2.getAbril()});
                        tabla.add(new Object[] { "Mayo "/*+medio2.getAno()*/, "Medio" ,medio2.getMayo()});
                        tabla.add(new Object[] { "Junio "/*+medio2.getAno()*/, "Medio" ,medio2.getJunio()});
                        tabla.add(new Object[] { "Julio "/*+medio2.getAno()*/, "Medio" ,medio2.getJulio()});
                        tabla.add(new Object[] { "Agosto "/*+medio2.getAno()*/, "Medio" ,medio2.getAgosto()});
                        tabla.add(new Object[] { "Septiembre "/*+medio2.getAno()*/, "Medio" ,medio2.getSeptiembre()});
                        tabla.add(new Object[] { "Octubre "/*+medio2.getAno()*/, "Medio" ,medio2.getOctubre()});
                        tabla.add(new Object[] { "Noviembre "/*+medio2.getAno()*/, "Medio" ,medio2.getNoviembre()});
                        tabla.add(new Object[] { "Diciembre "/*+medio2.getAno()*/, "Medio" ,medio2.getDiciembre()});
                        
                        tabla.add(new Object[] { "Enero "/*+medio2.getAno()*/, "Minimo" ,minimo2.getEnero()});
                        tabla.add(new Object[] { "Febrero "/*+medio2.getAno()*/, "Minimo" ,minimo2.getFebrero()});
                        tabla.add(new Object[] { "Marzo "/*+medio2.getAno()*/, "Minimo" ,minimo2.getMarzo()});
                        tabla.add(new Object[] { "Abril "/*+medio2.getAno()*/, "Minimo" ,minimo2.getAbril()});
                        tabla.add(new Object[] { "Mayo "/*+medio2.getAno()*/, "Minimo" ,minimo2.getMayo()});
                        tabla.add(new Object[] { "Junio "/*+medio2.getAno()*/, "Minimo" ,minimo2.getJunio()});
                        tabla.add(new Object[] { "Julio "/*+medio2.getAno()*/, "Minimo" ,minimo2.getJulio()});
                        tabla.add(new Object[] { "Agosto "/*+medio2.getAno()*/, "Minimo" ,minimo2.getAgosto()});
                        tabla.add(new Object[] { "Septiembre "/*+medio2.getAno()*/, "Minimo" ,minimo2.getSeptiembre()});
                        tabla.add(new Object[] { "Octubre "/*+medio2.getAno()*/, "Minimo" ,minimo2.getOctubre()});
                        tabla.add(new Object[] { "Noviembre "/*+medio2.getAno()*/, "Minimo" ,minimo2.getNoviembre()});
                        tabla.add(new Object[] { "Diciembre "/*+medio2.getAno()*/, "Minimo" ,minimo2.getDiciembre()});
                        
                        
                        if(this.mediosAgno!=null && !this.mediosAgno.isEmpty()){
                            medioAgno2 = (SirhvOfertaMultianualMnsl)this.mediosAgno.get(cont);
                            tabla.add(new Object[] { "Enero "/*+medio2.getAno()*/, "Medio Anual" ,medioAgno2.getEnero()});
                            tabla.add(new Object[] { "Febrero "/*+medio2.getAno()*/, "Medio Anual" ,medioAgno2.getFebrero()});
                            tabla.add(new Object[] { "Marzo "/*+medio2.getAno()*/, "Medio Anual" ,medioAgno2.getMarzo()});
                            tabla.add(new Object[] { "Abril "/*+medio2.getAno()*/, "Medio Anual" ,medioAgno2.getAbril()});
                            tabla.add(new Object[] { "Mayo "/*+medio2.getAno()*/, "Medio Anual" ,medioAgno2.getMayo()});
                            tabla.add(new Object[] { "Junio "/*+medio2.getAno()*/, "Medio Anual" ,medioAgno2.getJunio()});
                            tabla.add(new Object[] { "Julio "/*+medio2.getAno()*/, "Medio Anual" ,medioAgno2.getJulio()});
                            tabla.add(new Object[] { "Agosto "/*+medio2.getAno()*/, "Medio Anual" ,medioAgno2.getAgosto()});
                            tabla.add(new Object[] { "Septiembre "/*+medio2.getAno()*/, "Medio Anual" ,medioAgno2.getSeptiembre()});
                            tabla.add(new Object[] { "Octubre "/*+medio2.getAno()*/, "Medio Anual" ,medioAgno2.getOctubre()});
                            tabla.add(new Object[] { "Noviembre "/*+medio2.getAno()*/, "Medio Anual" ,medioAgno2.getNoviembre()});
                            tabla.add(new Object[] { "Diciembre "/*+medio2.getAno()*/, "Medio Anual" ,medioAgno2.getDiciembre()});
                        }
                    }
                /*
                System.out.println("====================MEDIOS: ");
                System.out.println("=====================================Par Ordenado (x,y): (ENERO "+medio.getAno() + ", " + medio.getEnero() +")");
                System.out.println("=====================================Par Ordenado (x,y): (FEBRERO "+medio.getAno() + ", " + medio.getFebrero() +")");
                System.out.println("=====================================Par Ordenado (x,y): (MARZO "+medio.getAno() + ", " + medio.getMarzo() +")");
                System.out.println("=====================================Par Ordenado (x,y): (ABRIL "+medio.getAno() + ", " + medio.getAbril() +")");
                System.out.println("=====================================Par Ordenado (x,y): (MAYO "+medio.getAno() + ", " + medio.getMayo() +")");
                System.out.println("=====================================Par Ordenado (x,y): (JUNIO "+medio.getAno() + ", " + medio.getJunio() +")");
                System.out.println("=====================================Par Ordenado (x,y): (JULIO "+medio.getAno() + ", " + medio.getJulio() +")");
                System.out.println("=====================================Par Ordenado (x,y): (AGOSTO "+medio.getAno() + ", " + medio.getAgosto() +")");
                System.out.println("=====================================Par Ordenado (x,y): (SEPTIEMBRE "+medio.getAno() + ", " + medio.getSeptiembre() +")");
                System.out.println("=====================================Par Ordenado (x,y): (OCTUBRE "+medio.getAno() + ", " + medio.getOctubre() +")");
                System.out.println("=====================================Par Ordenado (x,y): (NOVIEMBRE "+medio.getAno() + ", " + medio.getNoviembre() +")");
                System.out.println("=====================================Par Ordenado (x,y): (DICIEMBRE "+medio.getAno() + ", " + medio.getDiciembre() +")");
                
                System.out.println("====================MAXIMOS: ");
                System.out.println("=====================================Par Ordenado (x,y): (ENERO "+maximo.getAno() + ", " + maximo.getEnero() +")");
                System.out.println("=====================================Par Ordenado (x,y): (FEBRERO "+maximo.getAno() + ", " + maximo.getFebrero() +")");
                System.out.println("=====================================Par Ordenado (x,y): (MARZO "+maximo.getAno() + ", " + maximo.getMarzo() +")");
                System.out.println("=====================================Par Ordenado (x,y): (ABRIL "+maximo.getAno() + ", " + maximo.getAbril() +")");
                System.out.println("=====================================Par Ordenado (x,y): (MAYO "+maximo.getAno() + ", " + maximo.getMayo() +")");
                System.out.println("=====================================Par Ordenado (x,y): (JUNIO "+maximo.getAno() + ", " + maximo.getJunio() +")");
                System.out.println("=====================================Par Ordenado (x,y): (JULIO "+maximo.getAno() + ", " + maximo.getJulio() +")");
                System.out.println("=====================================Par Ordenado (x,y): (AGOSTO "+maximo.getAno() + ", " + maximo.getAgosto() +")");
                System.out.println("=====================================Par Ordenado (x,y): (SEPTIEMBRE "+maximo.getAno() + ", " + maximo.getSeptiembre() +")");
                System.out.println("=====================================Par Ordenado (x,y): (OCTUBRE "+maximo.getAno() + ", " + maximo.getOctubre() +")");
                System.out.println("=====================================Par Ordenado (x,y): (NOVIEMBRE "+maximo.getAno() + ", " + maximo.getNoviembre() +")");
                System.out.println("=====================================Par Ordenado (x,y): (DICIEMBRE "+maximo.getAno() + ", " + maximo.getDiciembre() +")");
                
                
                System.out.println("====================MINIMOS: ");
                System.out.println("=====================================Par Ordenado (x,y): (ENERO "+minimo.getAno() + ", " + minimo.getEnero() +")");
                System.out.println("=====================================Par Ordenado (x,y): (FEBRERO "+minimo.getAno() + ", " + minimo.getFebrero() +")");
                System.out.println("=====================================Par Ordenado (x,y): (MARZO "+minimo.getAno() + ", " + minimo.getMarzo() +")");
                System.out.println("=====================================Par Ordenado (x,y): (ABRIL "+minimo.getAno() + ", " + minimo.getAbril() +")");
                System.out.println("=====================================Par Ordenado (x,y): (MAYO "+minimo.getAno() + ", " + minimo.getMayo() +")");
                System.out.println("=====================================Par Ordenado (x,y): (JUNIO "+minimo.getAno() + ", " + minimo.getJunio() +")");
                System.out.println("=====================================Par Ordenado (x,y): (JULIO "+minimo.getAno() + ", " + minimo.getJulio() +")");
                System.out.println("=====================================Par Ordenado (x,y): (AGOSTO "+minimo.getAno() + ", " + minimo.getAgosto() +")");
                System.out.println("=====================================Par Ordenado (x,y): (SEPTIEMBRE "+minimo.getAno() + ", " + minimo.getSeptiembre() +")");
                System.out.println("=====================================Par Ordenado (x,y): (OCTUBRE "+minimo.getAno() + ", " + minimo.getOctubre() +")");
                System.out.println("=====================================Par Ordenado (x,y): (NOVIEMBRE "+minimo.getAno() + ", " + minimo.getNoviembre() +")");
                System.out.println("=====================================Par Ordenado (x,y): (DICIEMBRE "+minimo.getAno() + ", " + minimo.getDiciembre() +")");
                
                System.out.println("====================MEDIOS AÑO: ");
                System.out.println("=====================================Par Ordenado (x,y): (ENERO "+medio.getAno() + ", " + medioAgno.getEnero() +")");
                System.out.println("=====================================Par Ordenado (x,y): (FEBRERO "+medio.getAno() + ", " + medioAgno.getFebrero() +")");
                System.out.println("=====================================Par Ordenado (x,y): (MARZO "+medio.getAno() + ", " + medioAgno.getMarzo() +")");
                System.out.println("=====================================Par Ordenado (x,y): (ABRIL "+medio.getAno() + ", " + medioAgno.getAbril() +")");
                System.out.println("=====================================Par Ordenado (x,y): (MAYO "+medio.getAno() + ", " + medioAgno.getMayo() +")");
                System.out.println("=====================================Par Ordenado (x,y): (JUNIO "+medio.getAno() + ", " + medioAgno.getJunio() +")");
                System.out.println("=====================================Par Ordenado (x,y): (JULIO "+medio.getAno() + ", " + medioAgno.getJulio() +")");
                System.out.println("=====================================Par Ordenado (x,y): (AGOSTO "+medio.getAno() + ", " + medioAgno.getAgosto() +")");
                System.out.println("=====================================Par Ordenado (x,y): (SEPTIEMBRE "+medio.getAno() + ", " + medioAgno.getSeptiembre() +")");
                System.out.println("=====================================Par Ordenado (x,y): (OCTUBRE "+medio.getAno() + ", " + medioAgno.getOctubre() +")");
                System.out.println("=====================================Par Ordenado (x,y): (NOVIEMBRE "+medio.getAno() + ", " + medioAgno.getNoviembre() +")");
                System.out.println("=====================================Par Ordenado (x,y): (DICIEMBRE "+medio.getAno() + ", " + medioAgno.getDiciembre() +")");
                */
                
                System.out.println("====================FIN GRAFICA MULTIANUALES: ");
                cont ++;
            }
        }
        return tabla;     
    }
}
