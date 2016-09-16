package co.gov.ideam.sirh.view.util;

import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.view.ParametrosDelegate;
import co.gov.ideam.sirh.util.ConstantesParametros;

import java.io.Serializable;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public class TipoVertimientoConverter  implements Converter, Serializable {
     /**Almacena la lista de resultados.*/
      private List<Lista> listaResult = null;
      
      /**
       * Convierte en un objeto.
       * 
       * @param context Contexto JSF actual.
       * @param component Componente GUI.
       * @param value Valor a convertir en objeto.
       * @return Conversión en objeto.
       */
       public Object getAsObject(FacesContext context, UIComponent component,
                                 String value) {
           Lista result = null;
           try {
               for (Lista lista : getValores()) {
                   if (lista.getCodigo().toString().equals((value != null) 
                                                           ?value.toString() 
                                                           :value)) {
                       return lista;
                   }
               }
           } catch (Exception e) {
               e.printStackTrace();
           }
           return result;
       }
      /**
       * Convierte en una cadena.
       * @param context Contexto JSF actual.
       * @param component Componente GUI.
       * @param value Objeto a convertir en cadena.
       * @return Conversión en objeto.
       */
      public String getAsString(FacesContext context, UIComponent component, 
              Object value) {
          try {
              for (Lista lista : getValores()) {
                  if (lista.getCodigo().toString().equals((value!=null)?value.toString():value)) {
                      return lista.getValor();
                  }
              }
             
          } catch (Exception e) {
              e.printStackTrace();
          }
          return null;
      }
          
      /**
       * Consulta la lista  en la bd.
       * @return lista
       * @throws java.lang.Exception
       */
      public List<Lista> getValores() throws Exception {
          if (this.listaResult == null) {
              ParametrosDelegate pd = ParametrosDelegate.getInstance();  
              this.listaResult = pd.getAllListaByCategoria(ConstantesParametros.CATEGORIA_TIPO_VERTIMIENTO);
          }
          
          return this.listaResult;
      } 
}
