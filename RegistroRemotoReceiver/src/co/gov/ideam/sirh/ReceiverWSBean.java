package co.gov.ideam.sirh;

import co.gov.ideam.sirh.model.EventoEntrada;
import co.gov.ideam.sirh.parametros.business.ParametrosEJB;
import co.gov.ideam.sirh.parametros.model.Parametro;
import co.gov.ideam.sirh.parametros.model.TipoParametro;
import co.gov.ideam.sirh.util.IdeamException;

import java.util.HashMap;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;

import javax.jms.Session;

import javax.jws.WebMethod;
import javax.jws.WebService;

import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.LazyDynaBean;

@Stateless(name = "ReceiverSirhV2WS", mappedName = "Sirh-ReceiverWS")
@Remote
@WebService(name = "RegistroRemotoSirh",serviceName = "Receiver")
public class ReceiverWSBean implements ReceiverWS {
    
    //@EJB(mappedName = "Sirh-ParametrosEJB")
    @EJB(mappedName = "java:app/Ideam-Ejb/ParametrosEJB")
    private ParametrosEJB parametros;    
    /**
     * Referencia a la fabrica de conexiones JMS
     */
    @Resource(mappedName="java:jboss/java:ReceiverConnectionFactory") private ConnectionFactory connectionFactory;
    /**
     * Referencia a la cola
     */
    @Resource(mappedName="java:/ReceiverSIRHQueue") private Queue queue;    
    
    @Resource private WebServiceContext wsctx;    
    
    private static String username = null;
    private static String password = null;    
    
    public ReceiverWSBean() {
    }
    private void validate()throws IdeamException{
        if(username==null || password==null){
            try{
                TipoParametro tp = 
                    parametros.getTipoParametro( TipoParametro.CONEXION_NODO_IDEAM );                
                if (tp==null){
                    throw new IdeamException("Usuario o Clave incorrectos");
                }
                List listaParametros = tp.getParametros();
                if(listaParametros==null){
                    System.out.println("Error Parametros => Listaparametros es null"  );
                    return;
                }
                
                Iterator<Parametro> it = listaParametros.iterator();
                while(it.hasNext()){
                    Parametro p = it.next();
                    if(p.getCodigo().longValue() == Parametro.CONEXION_IDEAM_USUARIO.longValue()){
                        username = p.getValor();
                    }else if(p.getCodigo().longValue() == Parametro.CONEXION_IDEAM_CLAVE.longValue()){
                        password = p.getValor();
                    }
                }
            }catch(IdeamException e){
                System.out.println("Error Parametros => " + e.getMessage() );
            }
        }
        MessageContext mctx = wsctx.getMessageContext();        
        Map http_headers = (Map) mctx.get(MessageContext.HTTP_REQUEST_HEADERS);
        List userList = (List) http_headers.get("username");
        List passList = (List) http_headers.get("password");
    
        String username = "";
        String password = "";
    
        if(userList!=null){            
            username = userList.get(0).toString();
        }
    
        if(passList!=null){
            password = passList.get(0).toString();            
        }
            
        if (!username.equals(this.username) || !password.equals(this.password)){
            throw new IdeamException("Usuario  / Password no valido");
        }        
    }    
    
    @WebMethod(operationName = "recibirEvento")
    public void recibirEvento(EventoEntrada evento)throws IdeamException{
        this.validate();
        System.out.println(":::::Novedad Recibida:::::");
        System.out.println("Se ha recibido el evento " +
                           evento.getServiceName() + ":" + 
                           evento.getMethodName());
                
        HashMap map = evento.getData();
        Set entrys = map.entrySet();
        Iterator it = entrys.iterator();
        while (it.hasNext()){
            Map.Entry e = (Map.Entry)it.next();            
            System.out.println("Data " + e.getKey().toString() + " = " + e.getValue().toString());
        }                                
        
        this.registrarEvento(evento.getServiceName(), 
                           evento.getMethodName(),
                           evento.getData());
    }
    
    public void registrarEvento(String serviceName, String methodName, HashMap data)throws IdeamException{
        // Registrar en la cola para que sea procesado por el MDB        
        try{            

            Connection con = connectionFactory.createConnection("ideam","pwd12345#");
            System.out.println("Instancia Conexion " + con.getClientID());
      		//Connection con =connectionFactory.createConnection();
            //Session session = con.createSession(false, Session.CLIENT_ACKNOWLEDGE);
            Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
            System.out.println("Establece session" );
	    MessageProducer mp = session.createProducer( queue );
            System.out.println("----- Establece MessageProducer" );
            ObjectMessage obj = session.createObjectMessage();
            obj.setJMSExpiration(0L);
            System.out.println("----- crea el ObjectMessage" );
            EventoEntrada evento = new EventoEntrada(serviceName,methodName,data);
            System.out.println("----- instancia un EventoEntrada" );
            obj.setObject(evento); 
            mp.send( obj );
            System.out.println("----- envia el ObjectMessage" );
            mp.close();
            session.close();
            con.close();            
        }catch(JMSException e){
            throw new IdeamException(e);
        }
    }         
}
