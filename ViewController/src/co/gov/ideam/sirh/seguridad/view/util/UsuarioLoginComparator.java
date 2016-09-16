package co.gov.ideam.sirh.seguridad.view.util;

import co.gov.ideam.sirh.seguridad.model.UsuarioVO;

import java.util.Comparator;
/**
 * Permite organizar una lista de usuarios por el login de los mismos
 */
public class UsuarioLoginComparator implements Comparator{
    public UsuarioLoginComparator() {
        super();
    }

    public int compare(Object o1, Object o2) {
        UsuarioVO usuario1 = (UsuarioVO)o1;
        UsuarioVO usuario2 = (UsuarioVO)o2;
        
        String login1 = usuario1.getLogin();
        String login2 = usuario2.getLogin();
        
        return login1.compareTo(login2);
    }
}
