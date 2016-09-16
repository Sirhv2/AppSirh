package co.gov.ideam.sirh.estadisticas.view;

import co.gov.ideam.sirh.usuarios.agua.view.UsuariosAguaDelegate;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.StandarBean;

import oracle.adf.view.faces.bi.component.graph.UIGraph;
import oracle.adf.view.faces.bi.model.GraphDataModel;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;

public class UsoAguaXActividadBean extends StandarBean{
    private GraphDataModel graphData;
    private String[] columnLabels = { "Uso del Agua" };
    private String[] seriesLabels;
    private Object[][] data2;
    private RichPanelStretchLayout psl1;
    private UIGraph hbg1;

    public UsoAguaXActividadBean(){        
        try{
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
            String datos[][] = uad.getUsoAguaAvtividad();
            setSeriesLabels(new String[ datos[0].length ]);
            setData2(new Object[1][ datos[0].length ]);
            for(int i=0; i<datos[0].length; i++){                
                seriesLabels[i] = datos[0][i];                
                data2[0][i] = new Integer(datos[1][i]);                                
            }
            initGraphDataModel();
        }catch(IdeamException e){
            showMessage(e);
        }
    }
    public void initGraphDataModel() {
        oracle.dss.dataView.LocalXMLDataSource ds =
            new oracle.dss.dataView.LocalXMLDataSource(getColumnLabels(),  getSeriesLabels(),  getData2());

        setGraphData(new oracle.adf.view.faces.bi.model.GraphDataModel());
        getGraphData().setDataSource(ds);
    }

    public GraphDataModel getGraphData() {
        return graphData;
    }

    public void setGraphData(GraphDataModel graphData) {
        this.graphData = graphData;
    }

    public String[] getColumnLabels() {
        return columnLabels;
    }

    public void setColumnLabels(String[] columnLabels) {
        this.columnLabels = columnLabels;
    }

    public String[] getSeriesLabels() {
        return seriesLabels;
    }

    public void setSeriesLabels(String[] seriesLabels) {
        this.seriesLabels = seriesLabels;
    }

    public Object[][] getData2() {
        return data2;
    }

    public void setData2(Object[][] data2) {
        this.data2 = data2;
    }

    public void setPsl1(RichPanelStretchLayout psl1) {
        this.psl1 = psl1;
    }

    public RichPanelStretchLayout getPsl1() {
        return psl1;
    }

    public void setHbg1(UIGraph hbg1) {
        this.hbg1 = hbg1;
    }

    public UIGraph getHbg1() {
        return hbg1;
    }
}
