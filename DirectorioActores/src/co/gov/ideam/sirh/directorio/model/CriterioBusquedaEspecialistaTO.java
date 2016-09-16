package co.gov.ideam.sirh.directorio.model;

import co.gov.ideam.sirh.parametros.model.Divipola;

import co.gov.ideam.sirh.parametros.model.Lista;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Encapsula los criterios de busqueda para la consulta de especialistas
 */
public class CriterioBusquedaEspecialistaTO implements Serializable{
    
    private List<Divipola> listaDeptos = new ArrayList<Divipola>();
    private List<Divipola> listaMunicipios= new ArrayList<Divipola>();
    private List<Lista> listaGestion= new ArrayList<Lista>();
    private List<Lista> listaInvestigacion= new ArrayList<Lista>();
    
    private String nombre;
    
    public CriterioBusquedaEspecialistaTO() {
        super();
    }

    public List<Divipola> getListaDeptos() {
        return listaDeptos;
    }

    public void setListaDeptos(List<Divipola> listaDeptos) {
        this.listaDeptos = listaDeptos;
    }

    public List<Divipola> getListaMunicipios() {
        return listaMunicipios;
    }

    public void setListaMunicipios(List<Divipola> listaMunicipios) {
        this.listaMunicipios = listaMunicipios;
    }

    public List<Lista> getListaGestion() {
        return listaGestion;
    }

    public void setListaGestion(List<Lista> listaGestion) {
        this.listaGestion = listaGestion;
    }

    public List<Lista> getListaInvestigacion() {
        return listaInvestigacion;
    }

    public void setListaInvestigacion(List<Lista> listaInvestigacion) {
        this.listaInvestigacion = listaInvestigacion;
    }
    
    public void addDepartamento(Divipola depto){
        this.listaDeptos.add(depto);
    }
    
    public void addMunicipio(Divipola municipio){
        this.listaMunicipios.add(municipio);
    }
    
    public void addGestion(Lista gestion){
        this.listaGestion.add(gestion);
    }
    
    public void addInvestigacion(Lista investigacion){
        this.listaInvestigacion.add(investigacion);
    }
    
    public String getDeptos(){        
        return arrayToCommaString(this.listaDeptos);
    }
    public String getMunicipios(){        
        return arrayToCommaString(this.listaMunicipios);
    }
    public String getGestion(){        
        return arrayToCommaString(this.listaGestion);
    }
    public String getInvestigacion(){        
        return arrayToCommaString(this.listaInvestigacion);
    }
    private String arrayToCommaString(List lista){
        boolean primero = true;
        String cadena = "";
        
        if (lista!=null && lista.size()>0){
            Iterator it = lista.iterator();
            while(it.hasNext()){
                Object obj = it.next();
                String valor = "";
                if(obj instanceof Divipola){
                    valor = ((Divipola)obj).getId().toString();
                }
                if(obj instanceof Lista){
                    valor = ((Lista)obj).getCodigo().toString();
                }
                
                if (primero){
                    primero = false;
                    cadena = valor;
                }else{
                    cadena += "," + valor;
                }
            }
        }        
        return cadena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
