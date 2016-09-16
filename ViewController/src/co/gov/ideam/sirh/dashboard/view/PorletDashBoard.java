package co.gov.ideam.sirh.dashboard.view;

/**
 * Representa un porlet que se carga en el DashBoard
 */
public class PorletDashBoard {
    
    private String portletId;
    private String nombre;
    private String descripcion;
    private String url;
    private boolean rendered;    
    private boolean maximized;
    
    public PorletDashBoard(PorletDashBoard porlet){
        this(porlet.getNombre(),
             porlet.getUrl(),
             porlet.getDescripcion(),
             !porlet.isRendered());
    }

    public PorletDashBoard(){
        
    }
    public PorletDashBoard(String nombre, String url, String descripcion, boolean showByDefault) {
        this.nombre = nombre;
        this.url = url;
        this.descripcion = descripcion;
        this.rendered = !showByDefault;
    }
    
    public PorletDashBoard(String nombre, String url, String descripcion, String portletId, boolean showByDefault) {
        this.nombre = nombre;
        this.url = url;
        this.descripcion = descripcion;
        this.rendered = !showByDefault;
        this.portletId = portletId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isRendered() {
        return rendered;
    }

    public void setRendered(boolean rendered) {
        this.rendered = rendered;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public boolean equals(Object obj){
        return obj!=null &&
                obj instanceof PorletDashBoard &&
                ( 
                 ( ((PorletDashBoard)obj).getUrl()!=null && this.getUrl() != null && ((PorletDashBoard)obj).getUrl().equals( this.getUrl()) ) ||
                 ( ((PorletDashBoard)obj).getPortletId()!=null && this.getPortletId() != null && ((PorletDashBoard)obj).getPortletId().equals( this.getPortletId()) )
                );
    }

    public boolean isMaximized() {
        return maximized;
    }

    public void setMaximized(boolean maximized) {
        this.maximized = maximized;
    }


    public void setPortletId(String portletId) {
        this.portletId = portletId;
    }

    public String getPortletId() {
        return portletId;
    }
}
