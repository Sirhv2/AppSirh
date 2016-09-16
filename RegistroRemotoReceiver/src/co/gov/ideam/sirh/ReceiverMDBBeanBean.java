package co.gov.ideam.sirh;

import co.gov.ideam.sirh.calidad.business.CalidadEJB;
import co.gov.ideam.sirh.calidad.business.CalidadEJBBean;
import co.gov.ideam.sirh.fuentes.business.FuentesEJB;
import co.gov.ideam.sirh.model.EventoEntrada;

import co.gov.ideam.sirh.parametros.business.ParametrosEJB;
import co.gov.ideam.sirh.porh.business.PorhEJB;
import co.gov.ideam.sirh.remoto.business.RegistroRemotoLocal;
import co.gov.ideam.sirh.seguridad.business.SeguridadEJB;
import co.gov.ideam.sirh.seguridad.model.UsuarioVO;

import co.gov.ideam.sirh.usuarios.agua.business.UsuariosAguaEJB;
import co.gov.ideam.sirh.util.Deserializer;
import co.gov.ideam.sirh.util.IdeamException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.HashMap;


import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import javax.naming.Context;
import javax.naming.InitialContext;

@MessageDriven(mappedName = "REceiverSIRH", activationConfig =  {
        @ActivationConfigProperty(propertyName="destination",propertyValue="java:/ReceiverSIRHQueue"),        
    	@ActivationConfigProperty(propertyName="destinationType",propertyValue="javax.jms.Queue"),        
        @ActivationConfigProperty(propertyName="clientId",propertyValue="java:jboss/java:ReceiverConnectionFactory"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") //Client
    })
public class ReceiverMDBBeanBean implements MessageListener {
    
     @EJB private SeguridadEJB seguridadService;
     @EJB private UsuariosAguaEJB usuariosAguaService;
     @EJB private FuentesEJB fuentesService;
     @EJB private PorhEJB porhService;
     @EJB private CalidadEJB calidadService;
    
    
    public void onMessage(Message message) {
        ObjectMessage msg = (ObjectMessage)message;
        Object beanClass = null;
        try {
            EventoEntrada eventoUsuario = null;        
            eventoUsuario = (EventoEntrada)msg.getObject();
            Object objectVO = null;
                        
            if(eventoUsuario.getServiceName().endsWith("SeguridadEJBBean")){
                //Deserializer.deserialize(eventoUsuario.getData());
                seguridadService.updateUser((UsuarioVO)objectVO);
            }
            
            Class parametrosMetodo[] = null;
            if(eventoUsuario.getServiceName().endsWith("UsuariosAguaBean")){
                System.out.println(":::::Recibe un UsuariosAguaBean :::::");
                beanClass = usuariosAguaService;
            }else if(eventoUsuario.getServiceName().endsWith("FuentesEJBBean")){
                 System.out.println(":::::Recibe un FuentesEJBBean :::::");
                 if(fuentesService==null){
                     System.out.println(":::::No existe un fuentesService :::::");
                 }  else  
                     System.out.println("::::: fuentesService :"+fuentesService.toString());
                 beanClass = fuentesService;
            }else if (eventoUsuario.getServiceName().endsWith("PorhEJBBean")){
                beanClass = porhService;
            }else if (eventoUsuario.getServiceName().endsWith("CalidadEJBBean")){
                beanClass = calidadService;
            }
            
            if(beanClass != null){
                System.out.println("::::: analiza beanClass :::::");
                Method metodos[] = beanClass.getClass().getMethods(); 
                System.out.println("::::: metodos.length :"+metodos.length);
                System.out.println(":::::Metodo a procesar :"+eventoUsuario.getMethodName());
                for(int i=0; i<metodos.length; i++){
                    Method m = metodos[i]; 
                    System.out.println(":::::Metodos :"+m.getName());
                    if(m.getName().equals(eventoUsuario.getMethodName())){ 
                        System.out.println(":::::Encuentra!!   Metodo a procesar :"+m.getName());
                        parametrosMetodo = m.getParameterTypes();                                                
                        break;
                    }
                }
                
                if( parametrosMetodo !=null ){
                    try {
                        Object objetos[] = new Object[parametrosMetodo.length];
                        DeserilizedObjects datos[] = new DeserilizedObjects[parametrosMetodo.length];
                        System.out.println(":::::   1    :::");
                        for (int i=0; i<datos.length; i++){                            
                            Object objetoParametro = null;
                            objetos[i] = objetoParametro;
                            datos[i] = new DeserilizedObjects(objetoParametro, 
                                                              parametrosMetodo[i].getCanonicalName());                            
                        }
                        System.out.println(":::::   2    :::");
                        this.deserializeMap(eventoUsuario.getData(), 
                                            datos);
                        System.out.println(":::::   3    :::");                        
                        for(int u=0; u<objetos.length; u++){
                            if(objetos[u]==null){                                
                                objetos[u] = datos[u].getObjeto();
                            }
                        }
                        
                        System.out.println(":::::4  Evento:"+eventoUsuario.getMethodName());
                        beanClass.getClass().getMethod(eventoUsuario.getMethodName(), 
                                            parametrosMetodo).invoke(beanClass, objetos);
                        System.out.println("::::: Novedad Procesada ::::: " + eventoUsuario.getServiceName() + " : " + eventoUsuario.getMethodName());
                 
                    } catch (NoSuchMethodException e) {
                        System.out.println("Error NoSuchMethodException => " + e.getMessage() );
                        e.printStackTrace(System.out);
                    } catch (IllegalAccessException e) {
                        System.out.println("Error IllegalAccessException => " + e.getMessage() );
                        e.printStackTrace(System.out);
                    } catch (InvocationTargetException e) {
                        System.out.println("Error InvocationTargetException => " + e.getMessage() );
                        e.printStackTrace(System.out);
                    }
                }
            }else{
                System.out.println("::::: ERROR: No puede instanaciar el beanClass :::::");
            }
            /*
            if(1==1){                
                if(eventoUsuario.getMethodName().equals("registrarUsuarioPredio")){                    
                    UsuarioAgua usuarioAgua = null;
                    Predio predio = null;
                    Representante representante = null;
                    Autoridades autoridad = null;  
                    
                    DeserilizedObjects datos[] ={new DeserilizedObjects(usuarioAgua, UsuarioAgua.class.getName()),
                                                 new DeserilizedObjects(predio, Predio.class.getName()),
                                                 new DeserilizedObjects(representante, Representante.class.getName()),
                                                 new DeserilizedObjects(autoridad, Autoridades.class.getName())}; 
                    
                    this.deserializeMap(eventoUsuario.getData(), 
                                        datos);
                    
                    usuariosAguaService.registrarUsuarioPredio((UsuarioAgua)datos[0].getObjeto(), 
                                                               (Predio)datos[1].getObjeto(),
                                                               (Representante)datos[2].getObjeto(), 
                                                               (Autoridades)datos[3].getObjeto());
                }
            }*/                        
        } catch (JMSException e) {
            System.out.println("Error JMSException => " + e.getMessage() );
        } catch(IdeamException e){
            System.out.println("Error  IdeamException=> " + e.getMessage() );
        } catch (Exception e) {
            System.out.println("ErrorException => " + e.getMessage() );
        }finally{
            try{
                message.acknowledge();
            }catch(JMSException e) {
                System.out.println("Error => " + e.getMessage() );
            }
        }
    }
    
    public void deserializeMap(HashMap mapa, DeserilizedObjects... obj){
        for(int i=0; i<obj.length; i++){            
            String nombreGrupo = "ClassName#" + i;
            String nombreClase = mapa.get(nombreGrupo).toString();                                
            Object instancia = Deserializer.deserialize(mapa,
                                                        i,
                                                        nombreClase);                   
                       
            for(int j=0; j<obj.length; j++){  
                
                String className = obj[j].getClassName();
                String instanceClassName = instancia.getClass().getName();

                if(instanceClassName.equals( className ) && obj[j].getObjeto() ==null){
                    System.out.println("Set " + instancia.getClass().getName());
                    obj[j].setObjeto( instancia );
                    break;
                }
            }            
        }
    }    
    
    class DeserilizedObjects{
        private Object objeto;
        private String className;

        public DeserilizedObjects(Object obj, String name){
            this.objeto = obj;
            this.className = name;
        }
        public Object getObjeto() {
            return objeto;
        }

        public void setObjeto(Object objeto) {
            this.objeto = objeto;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }
    }
    

}
