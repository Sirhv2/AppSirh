package co.gov.ideam.sirh.estadisticas.view;

import co.gov.ideam.sirh.seguridad.model.PerfilVO;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.seguridad.view.SeguridadDelegate;
import co.gov.ideam.sirh.usuarios.agua.view.UsuariosAguaDelegate;
import co.gov.ideam.sirh.util.ConstantesCalidad;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;

import oracle.adf.view.faces.bi.component.graph.UIGraph;
import oracle.adf.view.faces.bi.model.GraphDataModel;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;

public class BaseGraphBean extends StandarBean {

    protected String graphId;
    protected UIGraph graph;
    protected String[] columnLabels = { "" };
    protected String[] seriesLabels;
    protected GraphDataModel graphData;
    protected Object[][] data2;

    public BaseGraphBean() {
        super();
    }

    public void initGraphDataModel() {
        oracle.dss.dataView.LocalXMLDataSource ds =
            new oracle.dss.dataView.LocalXMLDataSource(columnLabels,
                                                       seriesLabels, data2);
        setGraphData(new oracle.adf.view.faces.bi.model.GraphDataModel());
        getGraphData().setDataSource(ds);
    }

    public void setGraph(UIGraph graph) {
        this.graph = graph;
    }

    public UIGraph getGraph() {
        return graph;
    }

    public void setGraphData(GraphDataModel graphData) {
        this.graphData = graphData;
    }

    public GraphDataModel getGraphData() {
        return graphData;
    }

    public void setData2(Object[][] data2) {
        this.data2 = data2;
    }

    public Object[][] getData2() {
        return data2;
    }

    public void setColumnLabels(String[] columnLabels) {
        this.columnLabels = columnLabels;
    }

    public String[] getColumnLabels() {
        return columnLabels;
    }

    public void setSeriesLabels(String[] seriesLabels) {
        this.seriesLabels = seriesLabels;
    }

    public String[] getSeriesLabels() {
        return seriesLabels;
    }

    public void setGraphId(String graphId) {
        this.graphId = graphId;
    }

    public String getGraphId() {
        return graphId;
    }
}
