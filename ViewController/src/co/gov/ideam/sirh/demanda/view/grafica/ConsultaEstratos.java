package co.gov.ideam.sirh.demanda.view.grafica;

import co.gov.ideam.sirh.datos.referencia.model.SirhvTarifaEstratificada;
import co.gov.ideam.sirh.oferta.view.grafica.Grafica;
import co.gov.ideam.sirh.util.IdeamException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConsultaEstratos extends Grafica{
    
    private List<String> key;
    private Map dataMap;

    /**
     * @param datos
     * @throws IdeamException
     */
    public void calcular(List datos)throws IdeamException{
        if(datos!=null && !datos.isEmpty()){
            this.dataMap = new HashMap();
            this.key = new ArrayList();
            for(SirhvTarifaEstratificada dato : (List<SirhvTarifaEstratificada>)datos){
                String llave = dato.getDescDepartamento()+"-"+dato.getDescMunicipio()+"-"+dato.getDescClase();
                if(!this.dataMap.containsKey(llave)){
                    List<Double> estratos = new ArrayList();
                    estratos.add(dato.getEstrato1());
                    estratos.add(dato.getEstrato2());
                    estratos.add(dato.getEstrato3());
                    estratos.add(dato.getEstrato4());
                    estratos.add(dato.getEstrato5());
                    estratos.add(dato.getEstrato6());
                    estratos.add(dato.getEstrato10());
                    estratos.add(dato.getEstrato11());
                    estratos.add(dato.getEstrato12());
                    this.dataMap.put(llave, estratos);
                    this.key.add(llave);
                }
            }
            
        }
    
    }
    
    @Override
    public List<Object []> tabular()throws IdeamException{
        List<Object []> tabla = null;
        if(this.key!=null && !this.key.isEmpty()){
            tabla = new ArrayList<Object []>();
            for(String llave : this.key ){
                List valores = (List)(this.dataMap.get(llave));
                
                //tabla.add(new Object[] { "x", "Serie", "y"});
                
                System.out.println("===============LLAVE: "+ llave);
                System.out.println("===============valores: "+ valores);
                System.out.println("===============valores: "+ valores.get(0));
                System.out.println("===============valores: "+ valores.get(1));
                System.out.println("===============valores: "+ valores.get(2));
                System.out.println("===============valores: "+ valores.get(3));
                System.out.println("===============valores: "+ valores.get(4));
                System.out.println("===============valores: "+ valores.get(5));
                System.out.println("===============valores: "+ valores.get(6));
                System.out.println("===============valores: "+ valores.get(7));
                System.out.println("===============valores: "+ valores.get(8));
                
                
                tabla.add (new Object [] {llave, "Estrato 1", valores.get(0)});
                tabla.add (new Object [] {llave, "Estrato 2", valores.get(1)});
                tabla.add (new Object [] {llave, "Estrato 3", valores.get(2)});
                tabla.add (new Object [] {llave, "Estrato 4", valores.get(3)});
                tabla.add (new Object [] {llave, "Estrato 5", valores.get(4)});
                tabla.add (new Object [] {llave, "Estrato 6", valores.get(5)});
                tabla.add (new Object [] {llave, "Estrato 10", valores.get(6)});
                tabla.add (new Object [] {llave, "Estrato 11", valores.get(7)});
                tabla.add (new Object [] {llave, "Estrato 12", valores.get(8)});
            }
        }
        return tabla; 
    }
}
