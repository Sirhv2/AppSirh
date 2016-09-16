package co.gov.ideam.sirh.demanda.view;

import co.gov.ideam.sirh.datos.referencia.view.DatosReferenciaDelegate;
import co.gov.ideam.sirh.demanda.view.grafica.ConsultaCaudalEstadosUsuario;
import co.gov.ideam.sirh.demanda.view.grafica.ConsultaEstratos;
import co.gov.ideam.sirh.oferta.view.grafica.Grafica;
import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.StandarBean;

import java.util.ArrayList;
import java.util.List;

public class CaudalEstadoUsuario  extends StandarBean{
    
    //Lista de departamentos para el combo.
    private List listaDepartamentos;
    //Lista de municipios para el combo.
    private List listMunicipios;
    //Lista de datos resultado de la consulta.
    private List listaDatos;
    //Lista de datos a graficar.
    private List tabularConsulta;
    //bandera para activar la pestaña de grafica
    private boolean existeGrafica;
    
    private List tabularData;
    
    
    public void loadParametros() throws IdeamException{  
        this.existeGrafica = false;
        this.listaDepartamentos = new ArrayList();
        this.listMunicipios = new ArrayList();
        
        this.listaDepartamentos = this.cargarDivipolaSinRestriccion(null);
    }
    
    
    public void loadMunicipios(Long depto) throws IdeamException{  
        this.listMunicipios = this.cargarDivipolaSinRestriccion(depto);
    }
    
    public void cargarDatos(Long municipio) throws IdeamException{
        this.existeGrafica = false;
        this.listaDatos = new ArrayList();
        DatosReferenciaDelegate drd = DatosReferenciaDelegate.getInstance();
        this.listaDatos = drd.getAllEstadoUsuarioCaudal(municipio.intValue());

    }
    
    public void graficar() throws IdeamException{
        Grafica grafica = new ConsultaCaudalEstadosUsuario();
        grafica.calcular(this.listaDatos);
        this.tabularConsulta = grafica.tabular();
        this.existeGrafica = true;
    }
    

    public void setListaDepartamentos(List listaDepartamentos) {
        this.listaDepartamentos = listaDepartamentos;
    }

    public List getListaDepartamentos() {
        return listaDepartamentos;
    }

    public void setListMunicipios(List listMunicipios) {
        this.listMunicipios = listMunicipios;
    }

    public List getListMunicipios() {
        return listMunicipios;
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

    public void setTabularData(List tabularData) {
        this.tabularData = tabularData;
    }

    public List getTabularData() {
        return tabularData;
    }
}
