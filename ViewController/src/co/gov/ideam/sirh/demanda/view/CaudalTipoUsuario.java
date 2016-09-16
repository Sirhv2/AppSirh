package co.gov.ideam.sirh.demanda.view;

import co.gov.ideam.sirh.datos.referencia.view.DatosReferenciaDelegate;
import co.gov.ideam.sirh.demanda.view.grafica.ConsultaCaudalTipoUsuario;
import co.gov.ideam.sirh.demanda.view.grafica.ConsultaEstratos;
import co.gov.ideam.sirh.oferta.view.grafica.Grafica;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.StandarBean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CaudalTipoUsuario extends StandarBean{
    //Lista de datos resultado de la consulta.
    private List listaDatos;
    //Lista de datos a graficar.
    private List tabularConsulta;
    //bandera para activar la pestaña de grafica
    private boolean existeGrafica;
    
    public void loadParametros() throws IdeamException{  
        this.existeGrafica = false;
        
    }
    
    public void cargarDatos(Date fechaInicio, Date fechaFin) throws IdeamException{
        this.existeGrafica = false;
        this.listaDatos = new ArrayList();
        DatosReferenciaDelegate drd = DatosReferenciaDelegate.getInstance();
        
        this.listaDatos = drd.getAllTipousuarioCaudal(fechaInicio, fechaFin);

    }
    
    public void graficar() throws IdeamException{
        Grafica grafica = new ConsultaCaudalTipoUsuario();
        grafica.calcular(this.listaDatos);
        this.tabularConsulta = grafica.tabular();
        this.existeGrafica = true;
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
