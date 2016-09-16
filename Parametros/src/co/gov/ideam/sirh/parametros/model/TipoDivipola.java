package co.gov.ideam.sirh.parametros.model;

import java.io.Serializable;

enum TipoDivipola  implements Serializable{
    DEPARTAMENTO("DEP", "Departamento"),
    MUNICIPIO("CP", "Municipio");
    
    private String codigo;
    private String nombre;
            
    TipoDivipola(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }    
    
    private String getCodigo(){
        return this.codigo;
    }
    
    private String getNombre(){
        return this.nombre;
    }
}
