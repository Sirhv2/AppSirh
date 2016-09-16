package co.gov.ideam.sirh.ws.test;

import co.gov.ideam.sirh.ws.ExportSirh;
import co.gov.ideam.sirh.ws.ExportaDatosSirh;

import co.gov.ideam.sirh.ws.model.DataWebService;

import co.gov.ideam.sirh.ws.model.SingleRowWebService;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import java.util.Map;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.WebServiceRef;
import javax.xml.ws.handler.MessageContext;

public class Tester {
    @WebServiceRef
    private static ExportSirh exportSirh;

    private static void setUserPassword(ExportaDatosSirh exportaDatosSirh){
        Map<String, Object> req_ctx =  ((BindingProvider)exportaDatosSirh).getRequestContext();          
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        req_ctx.put(MessageContext.HTTP_REQUEST_HEADERS, headers);      
    }
    
    public static void main(String [] args){
        try{
            exportSirh = new ExportSirh();
            ExportaDatosSirh exportaDatosSirh = exportSirh.getExportaDatosSirhPort();
            setUserPassword(exportaDatosSirh);  
            System.out.println("setUserPassword");
            ArrayList param = new ArrayList();
            List parametros = new ArrayList();
            parametros.add(56L);
            if(exportaDatosSirh!=null){
                
                List lista = exportaDatosSirh.getMultipleRows(3L, null);//aquí se cambia el id de la senetencia y se envia el arraylist de parametros en el orden de aparición en la sentencia sql.
    
                Iterator  itLista = lista.iterator();
                boolean primero = true;
                while(itLista.hasNext()){
                    SingleRowWebService row = (SingleRowWebService)itLista.next();
                    Iterator it = row.getFields().iterator();
                    String titulos = "";
                    String info = "";
                    while(it.hasNext()){
                        DataWebService data = (DataWebService)it.next();
                        if(primero){
                            titulos += data.getName() + "\t\t";
                        }
                        info += data.getValue().toString() + "\t\t";
                    }
                    if(primero){
                        System.out.println(titulos);
                        String raya = new String("---------------");                                        
                        System.out.println(raya);
                        primero = false;
                    }
                    System.out.println(info);
                }
            } 
            }catch (Exception e) {
                System.out.println(e);
                System.out.println(e.getMessage());
            } 
        try{
            if(2==2){
                System.out.println("setUserPassword2");
                List parametros = new ArrayList();
                parametros.add(56L);
                ExportaDatosSirh exportaDatosSirh = exportSirh.getExportaDatosSirhPort();
                SingleRowWebService row = exportaDatosSirh.getOneRow(2L, parametros);
                Iterator it = row.getFields().iterator();
                String titulos = "";
                String info = "";
                while(it.hasNext()){
                    DataWebService data = (DataWebService)it.next();
                    titulos += data.getName() + "\t\t";
                    info += data.getValue().toString() + "\t\t";
                }
                System.out.println(titulos);
                String raya = new String("---------------");                
                System.out.println(raya);
                System.out.println(info);
            }
        }catch (Exception e) {
            System.out.println(e);
            System.out.println(e.getMessage());
        }        
    }

    /*
    public static void main(String [] args){
      //List lista;
        try {
            if (1==1){
                exportSirh = new ExportSirh();
                ExportaDatosSirh exportaDatosSirh = exportSirh.getExportaDatosSirhPort();
                setUserPassword(exportaDatosSirh);
                
                DataWebService query = exportaDatosSirh.getSingleData(1L, null);
                //DataWebService query = exportaDatosSirh.getSingleData(1L);            
                String titulos = query.getName();    
                String data = query.getValue().toString();    
                System.out.println(titulos);
                String raya = new String("---------------");
                System.out.println(raya);
                System.out.println(data);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try{
            if(2==2){
                exportSirh = new ExportSirh();
                ExportaDatosSirh exportaDatosSirh = exportSirh.getExportaDatosSirhPort();
                setUserPassword(exportaDatosSirh);
                
                List parametros = new ArrayList();
                parametros.add(56L);
                
                SingleRowWebService row = exportaDatosSirh.getOneRow(2L, parametros);
                Iterator it = row.getFields().iterator();
                String titulos = "";
                String info = "";
                while(it.hasNext()){
                    DataWebService data = (DataWebService)it.next();
                    titulos += data.getName() + "\t\t";
                    info += data.getValue().toString() + "\t\t";
                }
                System.out.println(titulos);
                String raya = new String("---------------");                
                System.out.println(raya);
                System.out.println(info);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try{            
            if(3==3){
                exportSirh = new ExportSirh();
                ExportaDatosSirh exportaDatosSirh = exportSirh.getExportaDatosSirhPort();
                setUserPassword(exportaDatosSirh);
                
                List lista = exportaDatosSirh.getMultipleRows(1L,null);
                Iterator  itLista = lista.iterator();
                boolean primero = true;
                while(itLista.hasNext()){
                    SingleRowWebService row = (SingleRowWebService)itLista.next();
                    Iterator it = row.getFields().iterator();
                    String titulos = "";
                    String info = "";
                    while(it.hasNext()){
                        DataWebService data = (DataWebService)it.next();
                        if(primero){
                            titulos += data.getName() + "\t\t";
                        }
                        info += data.getValue().toString() + "\t\t";
                    }
                    if(primero){
                        System.out.println(titulos);
                        String raya = new String("---------------");                                        
                        System.out.println(raya);
                        primero = false;
                    }
                    System.out.println(info);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }            
    }*/
}
