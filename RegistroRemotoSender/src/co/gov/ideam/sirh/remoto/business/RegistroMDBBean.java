package co.gov.ideam.sirh.remoto.business;

import co.gov.ideam.sirh.auditoria.business.NovTransmitirEJB;
import co.gov.ideam.sirh.auditoria.model.NovTransmitir;

import co.gov.ideam.sirh.parametros.business.ParametrosEJB;
import co.gov.ideam.sirh.parametros.model.Parametro;
import co.gov.ideam.sirh.parametros.model.TipoParametro;
import co.gov.ideam.sirh.remoto.model.EventoSalida;
import co.gov.ideam.sirh.remoto.sender.modelo.Receiver;
import co.gov.ideam.sirh.remoto.sender.modelo.RegistroRemotoSirh;
import co.gov.ideam.sirh.remoto.sender.modelo.types.EventoEntrada;
import co.gov.ideam.sirh.remoto.sender.modelo.types.ObjectFactory;
import co.gov.ideam.sirh.remoto.sender.modelo.types.RecibirEvento;
import co.gov.ideam.sirh.remoto.util.Serializer;

import co.gov.ideam.sirh.util.IdeamException;



import java.net.URL;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.WebServiceRef;

import javax.xml.ws.handler.MessageContext;

import org.apache.commons.beanutils.LazyDynaBean;

@MessageDriven(mappedName = "RegistroSIRH", activationConfig =  {
        @ActivationConfigProperty(propertyName="destination",propertyValue="java:/RegistroSIRHQueue"),        
	@ActivationConfigProperty(propertyName="destinationType",propertyValue="javax.jms.Queue"),        
        @ActivationConfigProperty(propertyName="clientId",propertyValue="java:jboss/java:RegistroConnectionFactory"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") //Client
    })
public class RegistroMDBBean implements MessageListener {
    
    @WebServiceRef
    private static Receiver receiver;
    
    //@EJB(mappedName = "Sirh-ParametrosEJB")
    @EJB(mappedName = "java:app/Ideam-Ejb/ParametrosEJB")
    private ParametrosEJB parametros;
 
    @EJB(mappedName = "java:app/Ideam-Ejb/NovTransmitirEJB")
    private NovTransmitirEJB novTransmitir;
    
    private static String username = null;
    private static String password = null;    
    private static String urlText  = null;
    
    public void onMessage(Message message) {

        System.out.println("HCP entro a procesar mensaje colocado en cola " + message.toString()  );

        ObjectMessage msg = (ObjectMessage)message;
        EventoSalida eventoUsuario = null;        
        if(username== null || password==null || urlText==null){
            try{
                TipoParametro tp = 
                    parametros.getTipoParametro( TipoParametro.CONEXION_NODO_IDEAM );
                if(tp==null){
                    System.out.println("Error Parametros => TipoParametro es null"  );
                    return;                    
                }
                List listaParametros = tp.getParametros();
                if(listaParametros==null){
                    System.out.println("Error Parametros => Listaparametros es null"  );
                    return;
                }
                
                Iterator<Parametro> it = listaParametros.iterator();
                while(it.hasNext()){
                    Parametro p = it.next();
                    if(p.getCodigo().longValue() == Parametro.CONEXION_IDEAM_URL.longValue()){
                        urlText = p.getValor();
                    }else if(p.getCodigo().longValue() == Parametro.CONEXION_IDEAM_USUARIO.longValue()){
                        username = p.getValor();
                    }else if(p.getCodigo().longValue() == Parametro.CONEXION_IDEAM_CLAVE.longValue()){
                        password = p.getValor();
                    }
                }
            }catch(IdeamException e){
                System.out.println("Error Parametros => " + e.getMessage() );
            }
        }
        try{            

/*            System.out.println("HCP va enviar mensaja a ws http://localhost:8080/Ideam-Ejb/Receiver/RegistroRemotoSirh?wsdl" );

            eventoUsuario = (EventoSalida)msg.getObject();
             
            receiver = new Receiver();
         
            System.out.println("HCP PASO 1 HOST getPath:"+  receiver.getWSDLDocumentLocation().getPath() );
            System.out.println("HCP PASO 1 HOST getHandlerResolver:"+  receiver.getHandlerResolver().toString());
            RegistroRemotoSirh registroRemotoSirh = receiver.getRegistroRemotoSirhPort();
          
            System.out.println("HCP PASO 2" );


            this.setUserPassword(registroRemotoSirh);
                                          
            System.out.println("HCP PASO 3" );
                                    
            RecibirEvento recibirEvento = new ObjectFactory().createRecibirEvento();
            EventoEntrada _evt = new ObjectFactory().createEventoEntrada();
            _evt.setServiceName(eventoUsuario.getServiceName());
            _evt.setMethodName(eventoUsuario.getMethodName());

            System.out.println("HCP PASO 4" );

            System.out.println("::::: Serializando Evento :::::");

            EventoEntrada.Data data = 
                new ObjectFactory().createEventoEntradaData();
            int numeroClase = 0;
            Iterator it = eventoUsuario.getListData().iterator();
            while(it.hasNext()){
                Object obj = it.next();
                Serializer.serialize(obj,data,(numeroClase++));                
            }            
            _evt.setData(data);

            System.out.println("HCP PASO 5" );
            
            recibirEvento.setArg0(_evt);

            System.out.println("HCP <<<---------- Va enviar ---->>>");

            registroRemotoSirh.recibirEvento(recibirEvento);            
            
            message.acknowledge();
            System.out.println("::::: Novedad Transmitida :::::");
*/
            Object obj = null;

            // obtener objeto de novedad
            eventoUsuario = (EventoSalida)msg.getObject();

            Iterator itObj = eventoUsuario.getListData().iterator();
            while(itObj.hasNext()){
                obj = itObj.next();
            }            
            EventoEntrada.Data dataEnt = new ObjectFactory().createEventoEntradaData();
            int contador = 0;
            Iterator itData = eventoUsuario.getListData().iterator();
           
            while(itData.hasNext()){
                obj = itData.next();
                Serializer.serialize(obj,dataEnt,(contador++));
                
            }            

            String dataObjeto = "";
            Iterator itSer =  dataEnt.getEntry().iterator();
            while(itSer.hasNext()){
                EventoEntrada.Data.Entry entry = (EventoEntrada.Data.Entry)itSer.next();
                dataObjeto += "<\\" + entry.getKey() + "/><\\" + entry.getValue() + "/><\\" + entry.getValue().getClass() + "/>";                
            }            
 
            // Crear  novedad a transmitir
            NovTransmitir nov = new NovTransmitir();
            
            nov.setServiceName(eventoUsuario.getServiceName());
            nov.setMethodName(eventoUsuario.getMethodName());
            nov.setData(dataObjeto);
            
            novTransmitir.agregarNovTransmitir(nov);

        }catch(JMSException e){
            System.out.println("Error recibiendo el objeto => " + e.getMessage() );
        }catch(Exception e){
            System.out.println("HCP --->>>> Error Procesando cola LOCAL => " + e.getMessage() );
        }
    }
    
    private void setUserPassword(RegistroRemotoSirh registroRemotoSirh){
        Map<String, Object> req_ctx =  ((BindingProvider)registroRemotoSirh).getRequestContext();          
        Map<String, List<String>> headers = new HashMap<String, List<String>>();        
        headers.put("Username", Collections.singletonList(username));
        headers.put("Password", Collections.singletonList(password));
        req_ctx.put(MessageContext.HTTP_REQUEST_HEADERS, headers);      
    }
    
}
