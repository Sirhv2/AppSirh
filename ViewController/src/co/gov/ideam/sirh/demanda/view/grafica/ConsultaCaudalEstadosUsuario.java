package co.gov.ideam.sirh.demanda.view.grafica;

import co.gov.ideam.sirh.datos.referencia.model.SirhvCaudalEstadoUsuario;
import co.gov.ideam.sirh.oferta.view.grafica.Grafica;

import co.gov.ideam.sirh.util.IdeamException;

import java.util.ArrayList;
import java.util.List;

public class ConsultaCaudalEstadosUsuario  extends Grafica{
    private List<String> key;
    private List<Double> valoresLegal;
    private List<Double> valoresNoLegal;
    
    public void calcular(List datos)throws IdeamException{
        if(datos!=null && !datos.isEmpty()){
            this.key = new ArrayList();
            this.valoresLegal = new ArrayList();
            this.valoresNoLegal = new ArrayList();
            
            for(SirhvCaudalEstadoUsuario dato : (List<SirhvCaudalEstadoUsuario>)datos){
                String llave = dato.getAutoridad();
                if(!this.key.contains(llave)){
                    if(dato.getTipo().contains("NO")){
                        this.valoresLegal.add(null);
                        this.valoresNoLegal.add(dato.getCantidad());
                        
                    }else {
                        this.valoresLegal.add(dato.getCantidad());
                        this.valoresNoLegal.add(null);
                        
                    }
                    
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
            
            for(int index = 0; index < this.key.size(); index++){
                
                //tabla.add(new Object[] { "x", "Serie", "y"});
                
                
                tabla.add (new Object [] {this.key.get(index), "Legalizados", this.valoresLegal.get(index)});
                tabla.add (new Object [] {this.key.get(index), "No legalizados", this.valoresNoLegal.get(index)});
                
            }
        }
        return tabla; 
    }
}
