package co.gov.ideam.sirh.usuarios.agua.model;

import java.io.Serializable;


/**
 * Permite consolidar la informacion que se va a utilizar para generar los reportes
 * de completitud de los datos
 */
public class CondicionErrorTO implements Serializable{
    
    private String categoria;
    private String titulo;    
    private String sqlTotal;
    private String sqlError;
    private String sqlDetalle;
    private Long totalRegistros;
    private Long registrosError;
    
    public CondicionErrorTO() {
        
    }    
    public CondicionErrorTO(String categoria, String titulo, 
                            Long total, Long error) {
        this.categoria = categoria;
        this.titulo = titulo;
        this.totalRegistros = total;
        this.registrosError = error;
    }
    public CondicionErrorTO(String categoria, String titulo, 
                            String sqlTotal, String sqlError) {
        this.categoria = categoria;
        this.titulo = titulo;
        this.sqlTotal = sqlTotal;
        this.setSqlError(sqlError);
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSqlTotal() {
        return sqlTotal;
    }

    public void setSqlTotal(String sqlTotal) {
        this.sqlTotal = sqlTotal;
    }

    public Long getTotalRegistros() {
        return totalRegistros;
    }

    public void setTotalRegistros(Long totalRegistros) {
        this.totalRegistros = totalRegistros;
    }

    public Long getRegistrosError() {
        return registrosError;
    }

    public void setRegistrosError(Long registrosError) {
        this.registrosError = registrosError;
    }

    public String getSqlError() {
        return sqlError;
    }

    public void setSqlError(String sqlError) {
        this.sqlError = sqlError;
    }
    
    public Double getPorcentajeError(){        
        if(totalRegistros>0){                        
            Double valor = new Double( (this.registrosError*100) / this.totalRegistros );            
            String retorono =  "" + valor.doubleValue(); //String.format("%.5g%n", valor.doubleValue());            
            return valor;
        }                   
        return 0D;
    }

    public String getSqlDetalle() {
        return sqlDetalle;
    }

    public void setSqlDetalle(String sqlDetalle) {
        this.sqlDetalle = sqlDetalle;
    }
}
