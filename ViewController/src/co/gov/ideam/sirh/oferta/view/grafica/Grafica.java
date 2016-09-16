package co.gov.ideam.sirh.oferta.view.grafica;

import co.gov.ideam.sirh.util.IdeamException;

import java.text.DecimalFormat;

import java.util.List;

public abstract class Grafica {

    public abstract void calcular(List datos)throws IdeamException;
    protected DecimalFormat df = new DecimalFormat("#.##");
    
    public List<Object []> tabular()throws IdeamException{
        return null;
    }
    
}