package co.gov.ideam.sirh.demanda.view.grafica;

import co.gov.ideam.sirh.datos.referencia.model.SirhvCaudalTipoUsuario;
import co.gov.ideam.sirh.datos.referencia.model.SirhvTarifaEstratificada;
import co.gov.ideam.sirh.oferta.view.grafica.Grafica;
import co.gov.ideam.sirh.util.IdeamException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConsultaCaudalTipoUsuario extends Grafica{
    
    private List<String> key;
    private List<Double> valoresJuridica;
    private List<Double> valoresNatural;
    private List<Double> valoresServicios;
    private List<Double> valoresMunicipio;
    
    public void calcular(List datos)throws IdeamException{
        if(datos!=null && !datos.isEmpty()){
            this.key = new ArrayList();
            this.valoresJuridica = new ArrayList();
            this.valoresNatural = new ArrayList();
            this.valoresServicios = new ArrayList();
            this.valoresMunicipio = new ArrayList();
            
            for(SirhvCaudalTipoUsuario dato : (List<SirhvCaudalTipoUsuario>)datos){
                String llave = dato.getAutoridad();
                if(!this.key.contains(llave)){
                    if(dato.getTipoPersona().contains("Municipio")){
                        this.valoresMunicipio.add(dato.getCaudalConcesionado());
                        this.valoresJuridica.add(null);
                        this.valoresNatural.add(null);
                        this.valoresServicios.add(null);
                    }else if(dato.getTipoPersona().contains("Jurídica")){
                        this.valoresJuridica.add(dato.getCaudalConcesionado());
                        this.valoresMunicipio.add(null);
                        this.valoresNatural.add(null);
                        this.valoresServicios.add(null);
                    }else if(dato.getTipoPersona().contains("natural")){
                        this.valoresNatural.add(dato.getCaudalConcesionado());
                        this.valoresJuridica.add(null);
                        this.valoresMunicipio.add(null);
                        this.valoresServicios.add(null);
                    }else if(dato.getTipoPersona().contains("Acueducto")){
                        this.valoresServicios.add(dato.getCaudalConcesionado());
                        this.valoresNatural.add(null);
                        this.valoresJuridica.add(null);
                        this.valoresMunicipio.add(null);
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
            
            System.out.println("==================SIZE llave "+this.key.size());
            System.out.println("==================SIZE nat "+this.valoresNatural.size());
            System.out.println("==================SIZE jur "+this.valoresJuridica.size());
            System.out.println("==================SIZE serv "+this.valoresServicios.size());
            System.out.println("==================SIZE mun "+this.valoresMunicipio.size());
            
            for(int index = 0; index < this.key.size(); index++){
                
                //tabla.add(new Object[] { "x", "Serie", "y"});
                
                
                tabla.add (new Object [] {this.key.get(index), "Persona Natural", this.valoresNatural.get(index)});
                tabla.add (new Object [] {this.key.get(index), "Persona Jurídica", this.valoresJuridica.get(index)});
                tabla.add (new Object [] {this.key.get(index), "Empresa Servicios", this.valoresServicios.get(index)});
                tabla.add (new Object [] {this.key.get(index), "Municipio", this.valoresMunicipio.get(index)});
                
            }
        }
        return tabla; 
    }
}
