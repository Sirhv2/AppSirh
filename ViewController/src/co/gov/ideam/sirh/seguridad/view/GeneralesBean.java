package co.gov.ideam.sirh.seguridad.view;

import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;

import javax.faces.event.ActionEvent;
/**
 * Acciones generales del sistema
 */
public class GeneralesBean extends StandarBean{
    public GeneralesBean() {
        super();
    }
    
    public void load(){
        
    }
    
    /**
     * registra la desconexion del usuario e invalida la session del mismo
     * @param actionEvent
     */
    public void salir(ActionEvent actionEvent) {
        try{
            SeguridadDelegate sd = SeguridadDelegate.getInstance();
            sd.logout();
            FacesUtils.invalidateSession();
        }catch(IdeamException e){
            System.out.println(e.getMessage());
        }
    }
}
