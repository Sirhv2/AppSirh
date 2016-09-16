package co.gov.ideam.sirh.porh.view;

import co.gov.ideam.sirh.parametros.model.Lista;

import java.util.Comparator;

import javax.faces.model.SelectItem;

/**
 * Permite comparar dos objetos tipo lista por el valor de los mismos
 */
public class UsosComparator implements Comparator{
    public UsosComparator() {
        super();
    }
    //@Override
    public int compare(Object o1, Object o2) {
        Lista l1 = (Lista)((SelectItem)o1).getValue();
        Lista l2 = (Lista)((SelectItem)o2).getValue();
        
        return l1.getValor().compareTo( l2.getValor() );
    }
}
