package co.gov.ideam.sirh.usuarios.agua.model;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class GeneradorSeq {
    
    public static List getValSecuencia(String seq, EntityManager em) {
        String sql = "";
        
       sql = "select "+seq+".nextval from dual"; //para oracle, remote
        
     //   sql = " SELECT nextval('"+seq+"');";//para postgresql, local
            
        Query query = em.createNativeQuery(sql);
        
        List l=query.getResultList();
        return l;
      
    }
    
    public static Long obtenerSequencia(Long codigoAutoridad, String nombreSequencia, EntityManager em) {
        //System.out.println("obtenerSequencia codigoAutoridad:"+codigoAutoridad);
        Object numero=getValSecuencia(nombreSequencia, em ).get(0);
        Integer idGenerado=new Integer("0");
        Integer num = new Integer("0");
        BigInteger numB= new BigInteger("0");
        BigDecimal numD= new BigDecimal("0");
        try{numB=(BigInteger)numero;
            idGenerado=(codigoAutoridad.intValue()) * 1000000 + ((Number) numB).intValue();
            //System.out.println("----- sequencia crea un integer:"+idGenerado);
        }catch(Exception e){
            numD=(BigDecimal)numero;
            idGenerado=(codigoAutoridad.intValue()) * 1000000 + ((Number) numD).intValue();
            //System.out.println("----- sequencia crea un BigDecimal:"+idGenerado);
        }    
        
        System.out.println("idGenerado:"+idGenerado);
        return idGenerado.longValue();
    }
}
