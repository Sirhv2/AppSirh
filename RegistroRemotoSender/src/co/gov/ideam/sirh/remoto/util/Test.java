package co.gov.ideam.sirh.remoto.util;

import co.gov.ideam.sirh.remoto.sender.modelo.IdeamException;
import co.gov.ideam.sirh.remoto.sender.modelo.Receiver;
import co.gov.ideam.sirh.remoto.sender.modelo.RegistroRemotoSirh;

import co.gov.ideam.sirh.remoto.sender.modelo.types.EventoEntrada;
import co.gov.ideam.sirh.remoto.sender.modelo.types.ObjectFactory;
import co.gov.ideam.sirh.remoto.sender.modelo.types.RecibirEvento;

//import co.gov.ideam.sirh.seguridad.model.UsuarioVO;


import javax.xml.ws.WebServiceRef;

public class Test {
    
    @WebServiceRef
    private static Receiver receiver;

    public static void main(String [] args)
    {
      receiver = new Receiver();
      RegistroRemotoSirh registroRemotoSirh = receiver.getRegistroRemotoSirhPort();
      // Add your code to call the desired methods.
      /*
      RecibirEvento recibirEvento = new ObjectFactory().createRecibirEvento();
      EventoEntrada _evt = new ObjectFactory().createEventoEntrada();
      UsuarioVO user = new UsuarioVO();
      user.setApellidos("aaaaa");
            
      _evt.setServiceName("service");
      _evt.setMethodName("method");
        try {
            _evt.setData(Serializer.serialize(user));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        recibirEvento.setArg0(_evt);

          try {
              registroRemotoSirh.recibirEvento(recibirEvento);
          } catch (IdeamException e) {
              System.out.println(e.getMessage());
          }
            */
    }

}
