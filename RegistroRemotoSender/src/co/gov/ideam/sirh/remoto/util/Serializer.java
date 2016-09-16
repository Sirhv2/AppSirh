package co.gov.ideam.sirh.remoto.util;


import co.gov.ideam.sirh.remoto.sender.modelo.types.EventoEntrada;
import co.gov.ideam.sirh.remoto.sender.modelo.types.ObjectFactory;
import co.gov.ideam.sirh.util.IdeamException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import java.sql.Timestamp;

import java.util.Calendar;
import java.util.HashMap;


/**
 *  convierte cualquier objeto recibido en un MutableDynaClass
 */
public class Serializer {
    public static void serialize(Object obj,
                                    EventoEntrada.Data map,
                                    int numeroClase)throws IdeamException{        
        
        String className = obj.getClass().getName();
        EventoEntrada.Data.Entry entry = 
            new ObjectFactory().createEventoEntradaDataEntry();
        entry.setKey("ClassName#" + numeroClase);
        entry.setValue(className);
        map.getEntry().add(entry);
        
        className += "#" + numeroClase;
        System.out.println("ClassName " + className);
        Field[] atrubutos = obj.getClass().getDeclaredFields();
        String nombreAtributo = "";
        for(int i=0; i<atrubutos.length; i++){
            try {
                nombreAtributo = atrubutos[i].getName();                                
                Object value = getValue(obj,atrubutos[i]);
                if(value!=null){
                    entry = new ObjectFactory().createEventoEntradaDataEntry();
                    entry.setKey(className + "#" + nombreAtributo);
                    if(value instanceof Calendar ){
                        Long valueLong = ((Calendar)value).getTimeInMillis();
                        entry.setValue(valueLong);    
                    }else if ( value instanceof Timestamp ){
                        Long valueLong = ((Timestamp)value).getTime();
                        entry.setValue(valueLong);    
                    }else{
                        entry.setValue(value);                            
                    }
                        
                    System.out.println(className + "#" + nombreAtributo + " " +
                                       entry.getValue());
                    map.getEntry().add(entry);
                }                
            } catch (Exception e) {
                System.out.println("Serializer Error in Class " + 
                                   obj.getClass().getName() + 
                                   " Method " + 
                                   nombreAtributo + " Detail -> " +
                                   e.getMessage());
            }                        
        }
    }
    
    private static Object getValue(Object obj, Field atributo)throws IdeamException{
        String nombreMetodo = "";
        String name = atributo.getName();
        Annotation anotaciones[] = atributo.getAnnotations();
        for (int i=0; i < anotaciones.length; i++){
            if (anotaciones[i].annotationType().getName().equals("javax.persistence.Transient")){
                return null;    
            }            
        }
        
        if (atributo.getType().getName().equalsIgnoreCase("boolean")){
            nombreMetodo = "is" + name.substring(0,1).toUpperCase() + name.substring(1);
        }else{
            nombreMetodo = "get" + name.substring(0,1).toUpperCase() + name.substring(1);
        }        
        Object value = "";
        try {
            value = obj.getClass().getMethod(nombreMetodo, null).invoke(obj, null);
        } catch (NoSuchMethodException e) {
            System.out.println(e.getMessage());
            e.printStackTrace(System.out);
            throw new IdeamException(e);
        } catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
            e.printStackTrace(System.out);            
            throw new IdeamException(e);
        } catch (InvocationTargetException e) {
            System.out.println(e.getMessage());
            e.printStackTrace(System.out);            
            throw new IdeamException(e);
        }
        return value;
    }    
}
