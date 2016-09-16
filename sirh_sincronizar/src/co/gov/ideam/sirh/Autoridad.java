package co.gov.ideam.sirh;

public class Autoridad {
    private String nombre;
    private long codigo;
    private String endPoint;
    private int fechaInicio;
    private int fechaFin;

    public Autoridad() {
    }

    public Autoridad(String nombre, long codigo, String endPoint,
                     int fechaInicio, int fechaFin) {
        super();
        this.nombre = nombre;
        this.codigo = codigo;
        this.endPoint = endPoint;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public long getCodigo() {
        return codigo;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setFechaInicio(int fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public int getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaFin(int fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getFechaFin() {
        return fechaFin;
    }
}
