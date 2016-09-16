package co.gov.ideam.sirh.archivos.model;

import co.gov.ideam.sirh.archivos.model.validator.ColumnValidatorInterface;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Representa una Columna del archivo Excel
 */
public class ColumnTO implements Serializable{
    /**
     * Titulo de la columna
     */
    private String titulo;
    /**
     * Indice de la columna
     */
    private int indice;
    /**
     * Lista de validadores
     */
    private List<ColumnValidatorInterface> validadores;

    public ColumnTO(String titulo) {
        this.titulo = titulo;
        this.setValidadores(new ArrayList<ColumnValidatorInterface>());
    }
    
    public ColumnTO(String titulo, int indice) {
        this.titulo = titulo;
        this.indice = indice;
        this.setValidadores(new ArrayList<ColumnValidatorInterface>());
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }
    
    public boolean equals(Object obj){
        return obj!= null &&
            obj instanceof ColumnTO &&
            ((ColumnTO)obj).getTitulo().equalsIgnoreCase(this.getTitulo());
    }
    
    public void addValidator(ColumnValidatorInterface validator){
        this.getValidadores().add(validator);
    }

    public List<ColumnValidatorInterface> getValidadores() {
        return validadores;
    }

    public void setValidadores(List<ColumnValidatorInterface> validadores) {
        this.validadores = validadores;
    }    
}
