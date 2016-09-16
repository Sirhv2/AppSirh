package co.gov.ideam.sirh.estadisticas.view;

import co.gov.ideam.sirh.fuentes.view.FuenteDelegate;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.usuarios.agua.view.UsuariosAguaDelegate;
import co.gov.ideam.sirh.util.EstadisticasQry;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;

import co.gov.ideam.sirh.view.StandarBean;

import oracle.adf.view.faces.bi.component.graph.UIGraph;
import oracle.adf.view.faces.bi.model.GraphDataModel;

public class EstadisticasBuenasPracticasBean extends StandarBean {

    private FuenteDelegate fd;
    // Modelos de datos para estadisticas de Buenas Practicas
    private GraphDataModel buenasPracticasPorDepartamento;
    private GraphDataModel buenasPracticasPorPromotores;
    private GraphDataModel frecuenciaPrincipios;
    private GraphDataModel buenasPracticasPorInversion;
    private GraphDataModel frecuenciaLogros;
    private GraphDataModel buenasPracticasPorTipoProyecto;
    private GraphDataModel buenasPracticasPorCategoria;
    private GraphDataModel frecuenciaMotivaciones;
    // Modelos de datos para estadisticas de Conflictos
    private GraphDataModel conflictosPorDepartamento;
    private GraphDataModel frecuenciaCFTipoConflicto;
    private GraphDataModel frecuenciaCFPoblacionAfectada;
    private GraphDataModel frecuenciaCFActores;
    private GraphDataModel frecuenciaCFGestion;
    private GraphDataModel frecuenciaCFSubgestion;
    private GraphDataModel conflictosPorCuenca;
    private GraphDataModel conflictosPorSubzona;
    

    public EstadisticasBuenasPracticasBean() {
        
        try{
            this.fd = FuenteDelegate.getInstance();
            
            loadBuenasPracticasPorDepartamento();
            loadBuenasPracticasPorPromotores();
            loadFrecuenciaPrincipios();
            loadBuenasPracticasPorInversion();
            loadFrecuenciaLogros();
            loadBuenasPracticasPorTipoProyecto();
            loadBuenasPracticasPorCategoria();
            loadFrecuenciaMotivaciones();
            
            loadConflictosPorDepartamento();
            loadFrecuenciaCFTipoConflicto();
            loadFrecuenciaCFPoblacionAfectada();
            loadFrecuenciaCFActores();
            loadFrecuenciaCFGestion();
            loadFrecuenciaCFSubgestion();
            loadConflictosPorCuenca();
            loadConflictosPorSubzona();            
            
        }catch(IdeamException e){
            showMessage(e);
        }
    }

    private void loadBuenasPracticasPorDepartamento() {
        setBuenasPracticasPorDepartamento(new oracle.adf.view.faces.bi.model.GraphDataModel());
        loadEstadistica(EstadisticasQry.BP_X_DEPTO, getBuenasPracticasPorDepartamento(), "Departamentos");
    }
    
    private void loadBuenasPracticasPorPromotores() {
        setBuenasPracticasPorPromotores(new oracle.adf.view.faces.bi.model.GraphDataModel());
        loadEstadistica(EstadisticasQry.BP_X_ACTOR, getBuenasPracticasPorPromotores(), "Promotores");
    }
    
    private void loadFrecuenciaPrincipios() {
        setFrecuenciaPrincipios(new oracle.adf.view.faces.bi.model.GraphDataModel());
        loadEstadistica(EstadisticasQry.FREC_PRINCIPIO, getFrecuenciaPrincipios(), "Principios");
    }

    private void loadBuenasPracticasPorInversion() {
        setBuenasPracticasPorInversion(new oracle.adf.view.faces.bi.model.GraphDataModel());
        loadEstadistica(EstadisticasQry.BP_X_COSTO, getBuenasPracticasPorInversion(), "Costos");
    }
    
    private void loadFrecuenciaLogros() {        
        setFrecuenciaLogros(new oracle.adf.view.faces.bi.model.GraphDataModel());
        loadEstadistica(EstadisticasQry.FREC_LOGRO, getFrecuenciaLogros(), "Logros");
    }
    
    private void loadBuenasPracticasPorTipoProyecto(){
        setBuenasPracticasPorTipoProyecto(new oracle.adf.view.faces.bi.model.GraphDataModel());
        loadEstadistica(EstadisticasQry.BP_X_PROYECTO, getBuenasPracticasPorTipoProyecto(), "Proyectos Educativos");
    }

    private void loadBuenasPracticasPorCategoria(){
        setBuenasPracticasPorCategoria(new oracle.adf.view.faces.bi.model.GraphDataModel());
        loadEstadistica(EstadisticasQry.BP_X_CATEG, getBuenasPracticasPorCategoria(), "Categorias");
    }
    
    private void loadFrecuenciaMotivaciones(){
        setFrecuenciaMotivaciones(new oracle.adf.view.faces.bi.model.GraphDataModel());
        loadEstadistica(EstadisticasQry.FREC_MOTIVACION, getFrecuenciaMotivaciones(), "Motivaciones");
    }
    
    private void loadConflictosPorDepartamento(){
        setConflictosPorDepartamento(new oracle.adf.view.faces.bi.model.GraphDataModel());
        loadEstadistica(EstadisticasQry.CF_X_DEPARTAMENTO, getConflictosPorDepartamento(), "Departamentos");
    }
    
    private void loadFrecuenciaCFTipoConflicto(){
        setFrecuenciaCFTipoConflicto(new oracle.adf.view.faces.bi.model.GraphDataModel());
        loadEstadistica(EstadisticasQry.FREC_TIPO_CONF, getFrecuenciaCFTipoConflicto(), "Tipo de Conflicto");
    }
    
    private void loadFrecuenciaCFPoblacionAfectada(){
        setFrecuenciaCFPoblacionAfectada(new oracle.adf.view.faces.bi.model.GraphDataModel());
        loadEstadistica(EstadisticasQry.CF_X_POBL_AFECT, getFrecuenciaCFPoblacionAfectada(), "Poblacion Afectada");
    }
    
    private void loadFrecuenciaCFActores(){
        setFrecuenciaCFActores(new oracle.adf.view.faces.bi.model.GraphDataModel());
        loadEstadistica(EstadisticasQry.CF_X_ACTORES, getFrecuenciaCFActores(), "Actores");
    }
    
    private void loadFrecuenciaCFGestion(){
        setFrecuenciaCFGestion(new oracle.adf.view.faces.bi.model.GraphDataModel());
        loadEstadistica(EstadisticasQry.CF_X_GESTION, getFrecuenciaCFGestion(), "Gestion");
    }
    
    private void loadFrecuenciaCFSubgestion(){
        setFrecuenciaCFSubgestion(new oracle.adf.view.faces.bi.model.GraphDataModel());
        loadEstadistica(EstadisticasQry.CF_X_SUB_GESTION, getFrecuenciaCFSubgestion(), "Subgestion");
    }
    
    private void loadConflictosPorCuenca(){
        setConflictosPorCuenca(new oracle.adf.view.faces.bi.model.GraphDataModel());
        loadEstadistica(EstadisticasQry.CF_X_CUENCA, getConflictosPorCuenca(), "Cuenca");
    }
    
    private void loadConflictosPorSubzona(){
        setConflictosPorSubzona(new oracle.adf.view.faces.bi.model.GraphDataModel());
        loadEstadistica(EstadisticasQry.CF_X_SUBZONA, getConflictosPorSubzona(), "SubZona");
    }
    

    private void loadEstadistica(EstadisticasQry qry, GraphDataModel graphDataModel, String columnLabel){
        String[] columnLabels = { columnLabel };
        String datos[][] = null;

        try {
            datos = this.fd.getEstadistica(qry);
            
            this.initGraphDataModel(columnLabels, datos, graphDataModel);

        } catch (IdeamException e) {
            showMessage(e);
        }
    }

    private void initGraphDataModel(String[] columnLabels, String datos[][], GraphDataModel model) {
        
        String[] seriesLabels = new String[datos[0].length];
        Object[][] data2 = new Object[1][datos[0].length];

        for (int i = 0; i < datos[0].length; i++) {
            seriesLabels[i] = datos[0][i];
            data2[0][i] = new Integer(datos[1][i]);
        }
        
        oracle.dss.dataView.LocalXMLDataSource ds =
            new oracle.dss.dataView.LocalXMLDataSource(columnLabels,
                                                       seriesLabels, data2);
        model.setDataSource(ds);
    }


    public void setBuenasPracticasPorDepartamento(GraphDataModel buenasPracticasPorDepartamento) {
        this.buenasPracticasPorDepartamento = buenasPracticasPorDepartamento;
    }

    public GraphDataModel getBuenasPracticasPorDepartamento() {
        return buenasPracticasPorDepartamento;
    }

    public void setBuenasPracticasPorPromotores(GraphDataModel buenasPracticasPorPromotores) {
        this.buenasPracticasPorPromotores = buenasPracticasPorPromotores;
    }

    public GraphDataModel getBuenasPracticasPorPromotores() {
        return buenasPracticasPorPromotores;
    }

    public void setFrecuenciaPrincipios(GraphDataModel frecuenciaPrincipios) {
        this.frecuenciaPrincipios = frecuenciaPrincipios;
    }

    public GraphDataModel getFrecuenciaPrincipios() {
        return frecuenciaPrincipios;
    }

    public void setBuenasPracticasPorInversion(GraphDataModel buenasPracticasPorInversion) {
        this.buenasPracticasPorInversion = buenasPracticasPorInversion;
    }

    public GraphDataModel getBuenasPracticasPorInversion() {
        return buenasPracticasPorInversion;
    }

    public void setFrecuenciaLogros(GraphDataModel frecuenciaLogros) {
        this.frecuenciaLogros = frecuenciaLogros;
    }

    public GraphDataModel getFrecuenciaLogros() {
        return frecuenciaLogros;
    }

    public void setBuenasPracticasPorTipoProyecto(GraphDataModel buenasPracticasPorTipoProyecto) {
        this.buenasPracticasPorTipoProyecto = buenasPracticasPorTipoProyecto;
    }

    public GraphDataModel getBuenasPracticasPorTipoProyecto() {
        return buenasPracticasPorTipoProyecto;
    }

    public void setBuenasPracticasPorCategoria(GraphDataModel buenasPracticasPorCategoria) {
        this.buenasPracticasPorCategoria = buenasPracticasPorCategoria;
    }

    public GraphDataModel getBuenasPracticasPorCategoria() {
        return buenasPracticasPorCategoria;
    }

    public void setFrecuenciaMotivaciones(GraphDataModel frecuenciaMotivaciones) {
        this.frecuenciaMotivaciones = frecuenciaMotivaciones;
    }

    public GraphDataModel getFrecuenciaMotivaciones() {
        return frecuenciaMotivaciones;
    }

    public void setConflictosPorDepartamento(GraphDataModel conflictosPorDepartamento) {
        this.conflictosPorDepartamento = conflictosPorDepartamento;
    }

    public GraphDataModel getConflictosPorDepartamento() {
        return conflictosPorDepartamento;
    }

    public void setFrecuenciaCFTipoConflicto(GraphDataModel frecuenciaCFTipoConflicto) {
        this.frecuenciaCFTipoConflicto = frecuenciaCFTipoConflicto;
    }

    public GraphDataModel getFrecuenciaCFTipoConflicto() {
        return frecuenciaCFTipoConflicto;
    }

    public void setFrecuenciaCFPoblacionAfectada(GraphDataModel frecuenciaCFPoblacionAfectada) {
        this.frecuenciaCFPoblacionAfectada = frecuenciaCFPoblacionAfectada;
    }

    public GraphDataModel getFrecuenciaCFPoblacionAfectada() {
        return frecuenciaCFPoblacionAfectada;
    }

    public void setFrecuenciaCFActores(GraphDataModel frecuenciaCFActores) {
        this.frecuenciaCFActores = frecuenciaCFActores;
    }

    public GraphDataModel getFrecuenciaCFActores() {
        return frecuenciaCFActores;
    }

    public void setFrecuenciaCFGestion(GraphDataModel frecuenciaCFGestion) {
        this.frecuenciaCFGestion = frecuenciaCFGestion;
    }

    public GraphDataModel getFrecuenciaCFGestion() {
        return frecuenciaCFGestion;
    }

    public void setFrecuenciaCFSubgestion(GraphDataModel frecuenciaCFSubgestion) {
        this.frecuenciaCFSubgestion = frecuenciaCFSubgestion;
    }

    public GraphDataModel getFrecuenciaCFSubgestion() {
        return frecuenciaCFSubgestion;
    }

    public void setConflictosPorCuenca(GraphDataModel conflictosPorCuenca) {
        this.conflictosPorCuenca = conflictosPorCuenca;
    }

    public GraphDataModel getConflictosPorCuenca() {
        return conflictosPorCuenca;
    }

    public void setConflictosPorSubzona(GraphDataModel conflictosPorSubzona) {
        this.conflictosPorSubzona = conflictosPorSubzona;
    }

    public GraphDataModel getConflictosPorSubzona() {
        return conflictosPorSubzona;
    }
}
