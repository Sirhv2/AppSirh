package co.gov.ideam.sirh.view;

import java.io.Serializable;

import oracle.adf.view.rich.component.rich.layout.RichPanelBox;

public class TipoGraficoTO implements Serializable{
    
    private String nombre;
    private RichPanelBox panel;
    
    public TipoGraficoTO(String nombre, RichPanelBox panel) {
        this.setNombre(nombre);
        this.setPanel(panel);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public RichPanelBox getPanel() {
        return panel;
    }

    public void setPanel(RichPanelBox panel) {
        this.panel = panel;
    }
}
