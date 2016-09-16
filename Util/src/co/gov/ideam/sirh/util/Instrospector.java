package co.gov.ideam.sirh.util;

import java.lang.reflect.InvocationTargetException;

/**
 * Realiza acciones de reflexion e instrospeccion para manipular
 * objetos java en forma dinamica
 */
public class Instrospector {
    
    /**
     * Retorna el valor del atributo correspondiente al objeto recibido como
     * parametros.  
     * @param obj
     * @param name
     * @return
     * @throws IdeamException  
     */
    public static Object getValue(Object obj, String name)throws IdeamException{
        String nombreMetodo = "";
        if (name.equalsIgnoreCase("activo")){
            nombreMetodo = "is" + name.substring(0,1).toUpperCase() + name.substring(1);
        }else{
            nombreMetodo = "get" + name.substring(0,1).toUpperCase() + name.substring(1);
        }        
        Object value = "";
        try {
            value = obj.getClass().getMethod(nombreMetodo, null).invoke(obj, null);
            if (value==null){
                value = "";
            }
            if (name.equalsIgnoreCase("activo")){
                if (value.toString().equalsIgnoreCase("true")){
                    return "Si";
                }else{
                    return "No";
                }
            }            
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
