package co.gov.ideam.sirh.dashboard.view;

import java.util.ArrayList;
import java.util.List;

public class Category {
    
    private String nombre;
    private List<PorletDashBoard> porletList;
    
    public Category(String nombre) {
        this.nombre = nombre;
        porletList = new ArrayList<PorletDashBoard>();
    }

    public void addPorlet(PorletDashBoard porlet){
        this.porletList.add(porlet);
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<PorletDashBoard> getPorletList() {
        return porletList;
    }

    public void setPorletList(List<PorletDashBoard> porletList) {
        this.porletList = porletList;
    }
    
    public boolean containsPorlet(String url){
        return containsPorlet(url, null);
    }
    
    public boolean containsPorlet(String url, String portletId){
        PorletDashBoard p = new PorletDashBoard();
        p.setUrl(url);
        p.setPortletId(portletId);
        return porletList.contains(p);
    }
}
