package co.gov.ideam.sirh.seguridad.view.util;

import co.gov.ideam.sirh.seguridad.model.UsuarioVO;

import java.util.Comparator;

/**
 * Permite organizar los registros de una lista de usuarios segun los nombres
 * y apellidos de estos
 */
public class UsuarioComparator implements Comparator{
    public UsuarioComparator() {
        super();
    }

    public int compare(Object o1, Object o2) {
        UsuarioVO usuario1 = (UsuarioVO)o1;
        UsuarioVO usuario2 = (UsuarioVO)o2;
        
        String nombres1 = usuario1.getApellidos() + usuario1.getNombres();
        String nombres2 = usuario2.getApellidos() + usuario2.getNombres();
        
        return nombres1.compareTo(nombres2);
    }
}
