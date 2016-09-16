package co.gov.ideam.sirh.estadisticas.view;


import co.gov.ideam.sirh.calidad.view.CalidadDelegate;
import co.gov.ideam.sirh.seguridad.model.PerfilVO;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.seguridad.view.SeguridadDelegate;
import co.gov.ideam.sirh.usuarios.agua.view.UsuariosAguaDelegate;
import co.gov.ideam.sirh.util.ConstantesCalidad;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;

import java.util.List;

import oracle.adf.view.faces.bi.component.graph.UIGraph;
import oracle.adf.view.faces.bi.model.GraphDataModel;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.context.AdfFacesContext;


public class MuestrasPorAnioSubtBean extends StandarBean{
    private RichPanelStretchLayout panelStretchLayout1;
    private UIGraph barGraphMuestrasAnio;
        private List<Object[]> listObject;
        private GraphDataModel graphData;
        private String[] columnLabels = { "Años" };
        private String[] seriesLabels;
        private Object[][] data2;


    public MuestrasPorAnioSubtBean() {
        String regla = (String)FacesUtils.getFromSession("OrigenNavegacionUsuario");
        System.out.println("regla de navegación:"+regla);
        if( regla.equals("observatorio"))
            System.out.println("no se debe hacer nada");
        else
            graficarTodo();
    }
    
    public void graficarTodo(){
        String datos[][] = null;
        UsuarioConectadoTO usuarioConectado = new UsuarioConectadoTO();
        try{
              usuarioConectado = 
                    (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
                
        }catch(Exception e){
            System.out.println("++++++++++++++++e"+e);
        }
      try{       
        CalidadDelegate cald = CalidadDelegate.getInstance();
        
            if(usuarioConectado==null){
                datos = cald.getCantidadMuestrasAnioSubt(null);
             
                } else {   
                    SeguridadDelegate seg = SeguridadDelegate.getInstance(); 
                    PerfilVO pp= new PerfilVO();
                    pp=   seg.getPerfilUsuario(usuarioConectado.getUsuario().getCodigo());
                        
                    if (pp.getCodigo()==ConstantesCalidad.PERFIL_CONSULTA){
                        datos = cald.getCantidadMuestrasAnioSubt(null);
                        
                    }else{
                        datos= cald.getCantidadMuestrasAnioSubt(usuarioConectado.getUsuario().
                                                                  getAutoridadAmbiental().
                                                                  getId().
                                                                  longValue());
                        }
                }  
            
            seriesLabels = new String[ datos[0].length ];
            data2 = new Object[1][ datos[0].length ];
            for(int i=0; i<datos[0].length; i++){                
                seriesLabels[i] = datos[0][i];                
                data2[0][i] = new Integer(datos[1][i]);                                
            }
            initGraphDataModel();
      
        }catch(IdeamException e){
            showMessage(e);
        }    
    }
 
    public void actualizarDatos(String datos[][]){
        seriesLabels = new String[ datos[0].length ];
        data2 = new Object[1][ datos[0].length ];
        for(int i=0; i<datos[0].length; i++){                
            seriesLabels[i] = datos[0][i];                
            data2[0][i] = new Integer(datos[1][i]);                                
        }
        initGraphDataModel();
        AdfFacesContext.getCurrentInstance().addPartialTarget(barGraphMuestrasAnio);
    }
    
    public void initGraphDataModel() {
        oracle.dss.dataView.LocalXMLDataSource ds =
            new oracle.dss.dataView.LocalXMLDataSource(columnLabels, seriesLabels, data2);

        setGraphData(new oracle.adf.view.faces.bi.model.GraphDataModel());
        getGraphData().setDataSource(ds);
    } 
    
    public void setPanelStretchLayout1(RichPanelStretchLayout panelStretchLayout1) {
        this.panelStretchLayout1 = panelStretchLayout1;
    }

    public RichPanelStretchLayout getPanelStretchLayout1() {
        return panelStretchLayout1;
    }


    public void setBarGraphMuestrasAnio(UIGraph barGraph1) {
        this.barGraphMuestrasAnio = barGraph1;
    }

    public UIGraph getBarGraphMuestrasAnio() {
        return barGraphMuestrasAnio;
    }
    
    
    public void setListObject(List<Object[]> listObject) {
        this.listObject = listObject;
    }

    public List<Object[]> getListObject() {
        return listObject;
    }



    public void setGraphData(GraphDataModel graphData) {
        this.graphData = graphData;
    }

    public GraphDataModel getGraphData() {
        return graphData;
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

    public void setData2(Object[][] data2) {
        this.data2 = data2;
    }

    public Object[][] getData2() {
        return data2;
    }


}

