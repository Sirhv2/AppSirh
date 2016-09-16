package co.gov.ideam.sirh.usuarios.agua.model.ws.entidades;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListaPermisos implements Serializable  {
   
   
    private List <UsuarioAguaWS> listaUsuarios;
   
    public ListaPermisos() {
        this.listaUsuarios= new ArrayList();
    }

 


}
