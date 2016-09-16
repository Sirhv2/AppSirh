package co.gov.ideam.sirh.seguridad.view.util;

import co.gov.ideam.sirh.seguridad.model.PerfilVO;

import java.util.Comparator;

/**
 * Permite organziar los registros de una lista de Perfiles por el nombre de
 * los mismos
 */
public class PerfilComparator implements Comparator{
    public PerfilComparator() {
        super();
    }

    public int compare(Object o1, Object o2) {
        PerfilVO perfil1 = (PerfilVO)o1;
        PerfilVO perfil2 = (PerfilVO)o2;
        
        return perfil1.getNombre().compareTo(perfil2.getNombre());
    }
}
