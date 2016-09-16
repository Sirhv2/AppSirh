package co.gov.ideam.sirh.demanda.view;

import co.gov.ideam.sirh.datos.referencia.view.DatosReferenciaDelegate;
import co.gov.ideam.sirh.demanda.view.grafica.ConsultaEstratos;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.StandarBean;

import co.gov.ideam.sirh.oferta.view.grafica.Grafica;

import java.util.ArrayList;
import java.util.List;


public class ConsultaEstratificada extends StandarBean{
    
    //Lista de variables para el combo.
    private List listaVariable;
    //Lista de complejidad del acueducto para el combo.
    private List listaClase;
    //Lista de datos resultado de la consulta.
    private List listaDatos;
    //Lista de datos a graficar.
    private List tabularConsulta;
    //bandera para activar la pestaña de grafica
    private boolean existeGrafica;
    
    private List tabularData;
    
    public void loadParametros() throws IdeamException{  
        this.existeGrafica = false;
        this.listaVariable = new ArrayList();
        this.listaClase = new ArrayList();
        
        this.listaVariable = this.cargarVariableDemanda();
        
        this.listaClase = this.cargarParametro(73L);
    }  
    
    public void cargarDatos(String variable, Integer agno, String clase) throws IdeamException{
        this.existeGrafica = false;
        this.listaDatos = new ArrayList();
        DatosReferenciaDelegate drd = DatosReferenciaDelegate.getInstance();
        
        if(Integer.parseInt(clase)>0){
            this.listaDatos = drd.getAllTarifa(agno, variable, new Integer(clase));
        }else{
            this.listaDatos = drd.getAllTarifa(agno, variable);
        }

    }
    
    public void graficar() throws IdeamException{
        Grafica grafica = new ConsultaEstratos();
        grafica.calcular(this.listaDatos);
        this.tabularConsulta = grafica.tabular();
        this.existeGrafica = true;
    }
    
    public List getTabularData() 
    {
        ArrayList list = new ArrayList();
        String[] rowLabels  = new String[] {"Boots", "Shoes"};
        String[] colLabels  = new String[] {"2006", "2007", "2008"};
        Double [] [] values = new Double[][]{
            {120000D, 122000D, 175000D},
            {90000D, 110000D, 150000D}
            };
        for (int c = 0; c < colLabels.length; c++)
        {
         for (int r = 0; r < rowLabels.length; r++)
           {
            list.add (new Object [] {colLabels[c], rowLabels[r], 
                new Double (values[r][c])});
           }
        }
        return list;
    }

    public void setListaVariable(List listaVariable) {
        this.listaVariable = listaVariable;
    }

    public List getListaVariable() {
        return listaVariable;
    }


    public void setListaClase(List listaClase) {
        this.listaClase = listaClase;
    }

    public List getListaClase() {
        return listaClase;
    }


    public void setListaDatos(List listaDatos) {
        this.listaDatos = listaDatos;
    }

    public List getListaDatos() {
        return listaDatos;
    }

    public void setTabularConsulta(List tabularConsulta) {
        this.tabularConsulta = tabularConsulta;
    }

    public List getTabularConsulta() {
        return tabularConsulta;
    }

    public void setExisteGrafica(boolean existeGrafica) {
        this.existeGrafica = existeGrafica;
    }

    public boolean isExisteGrafica() {
        return existeGrafica;
    }
}
