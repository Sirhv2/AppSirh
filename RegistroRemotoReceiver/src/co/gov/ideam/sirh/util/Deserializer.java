package co.gov.ideam.sirh.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import java.sql.Timestamp;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Deserializer {
    
    public static Object deserialize(HashMap map, int numeroGrupo, String className){                
        try {
            //System.out.println("deserialize1 " + numeroGrupo + " " + className);
            Object obj = Class.forName(className).newInstance();            
            //System.out.println("deserialize2 " + obj.getClass().getName());
            String nombreGrupo = className + "#" + numeroGrupo + "#";                
            //System.out.println("deserialize3 " + nombreGrupo);
            
            Set entrys = map.entrySet();
            Iterator it = entrys.iterator();
            while (it.hasNext()){
                Map.Entry e = (Map.Entry)it.next();                            
                String atributo = e.getKey().toString();
                //System.out.println("deserialize4 " + atributo);
                
                if(!atributo.startsWith(nombreGrupo)){
                    continue;
                }
                Object value = e.getValue();
                putValue(obj,value,atributo);                
            }                
            
            return obj;   
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException => " + e.getMessage());
            return null;
        } catch (InstantiationException e) {
            System.out.println("InstantiationException => " + e.getMessage());
            return null;
        } catch (IllegalAccessException e) {
            System.out.println("IllegalAccessException => " + e.getMessage());
            return null;
        } catch(IdeamException e){
            System.out.println("IdeamException => " + e.getMessage());
            return null;
        }
    }
    public static Object deserialize(HashMap map){
        String className = map.get("ClassName").toString();
        try {
            Object obj = Class.forName(className).newInstance();
            
            Set entrys = map.entrySet();
            Iterator it = entrys.iterator();
            while (it.hasNext()){
                Map.Entry e = (Map.Entry)it.next();            
                String atributo = e.getKey().toString();
                if(atributo.equals("ClassName")){
                    continue;
                }
                Object value = e.getValue();
                putValue(obj,value,atributo);                
            }                
            
            return obj;   
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (InstantiationException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
            return null;
        } catch(IdeamException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    private static void putValue(Object obj, Object value, String nombreKey)throws IdeamException{                
        int pos = nombreKey.lastIndexOf("#");
        String nombreAtributo = nombreKey.substring(pos + 1);
        String nombreMetodo = "";                
        try {
            nombreMetodo = "set" + nombreAtributo.substring(0,1).toUpperCase() + 
                           nombreAtributo.substring(1);
            Field atributo = obj.getClass().getDeclaredField(nombreAtributo);            
            if(atributo.getType().getName().endsWith("Calendar") ){
            
                Long valueLong = (Long)value;
                Calendar fecha = GregorianCalendar.getInstance();
                fecha.setTimeInMillis(valueLong);
                
                Object params[] = {fecha};
                obj.getClass().getMethod(nombreMetodo,Calendar.class).invoke(obj,params);                            
            }else if(atributo.getType().getName().endsWith("Timestamp") ){
            
                Long valueLong = (Long)value;                
                Timestamp fecha = new Timestamp(valueLong);                
                
                Object params[] = {fecha};
                obj.getClass().getMethod(nombreMetodo,Timestamp.class).invoke(obj,params);                            
            }else{
                Object params[] = {value};
                obj.getClass().getMethod(nombreMetodo,value.getClass()).invoke(obj,params);            
            }
        } catch (NoSuchMethodException e) {
            System.out.println("NoSuchMethodException => " + e.getMessage());
        } catch (IllegalAccessException e) {
            System.out.println("IllegalAccessException => " +e.getMessage());            
            throw new IdeamException(e);
        } catch (InvocationTargetException e) {
            System.out.println(e.getMessage());
            e.printStackTrace(System.out);            
            throw new IdeamException(e);
        } catch(NoSuchFieldException e){
            System.out.println(e.getMessage());
            e.printStackTrace(System.out);            
            throw new IdeamException(e);
        }
    }        
}
