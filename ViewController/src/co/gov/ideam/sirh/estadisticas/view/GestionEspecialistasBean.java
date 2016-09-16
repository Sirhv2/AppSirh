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

public class GestionEspecialistasBean extends StandarBean{
    private RichPanelStretchLayout psl1;
    private UIGraph pg1;
    private String[] columnLabels = { "Gestion de Especialistas" };
    private String[] seriesLabels;
    private Object[][] data2;
    private GraphDataModel graphData;

    public GestionEspecialistasBean(){
            String datos[][] = null;
            UsuarioConectadoTO usuarioConectado = new UsuarioConectadoTO();
            try{
                  usuarioConectado = 
                        (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
                    
            }catch(Exception e){
                System.out.println(e);
            }
            try{
               
                UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
                if(usuarioConectado==null){
                    datos= uad.getGestionEspecialistas(null);
                }else{   
                    SeguridadDelegate seg = SeguridadDelegate.getInstance(); 
                    PerfilVO pp= new PerfilVO();
                    pp=   seg.getPerfilUsuario(usuarioConectado.getUsuario().getCodigo());
                        
                    if (pp.getCodigo()==ConstantesCalidad.PERFIL_CONSULTA){
                             datos= uad.getGestionEspecialistas(null);
                    }else{
                       
                             datos= uad.getGestionEspecialistas(usuarioConectado.getUsuario().
                                                                   getAutoridadAmbiental().
                                                                   getId().
                                                                   longValue());
                }
            }
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
            new oracle.dss.dataView.LocalXMLDataSource(getColumnLabels(),
                                                       getSeriesLabels(),
                                                       getData2());

        setGraphData(new oracle.adf.view.faces.bi.model.GraphDataModel());
        getGraphData().setDataSource(ds);
    }    
    public void setPsl1(RichPanelStretchLayout psl1) {
        this.psl1 = psl1;
    }

    public RichPanelStretchLayout getPsl1() {
        return psl1;
    }

    public void setPg1(UIGraph pg1) {
        this.pg1 = pg1;
    }

    public UIGraph getPg1() {
        return pg1;
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

    public GraphDataModel getGraphData() {
        return graphData;
    }

    public void setGraphData(GraphDataModel graphData) {
        this.graphData = graphData;
    }
}
