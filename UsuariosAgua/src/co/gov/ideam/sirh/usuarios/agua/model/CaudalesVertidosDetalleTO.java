package co.gov.ideam.sirh.usuarios.agua.model;

import java.io.Serializable;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;



@Entity
@Table(name = "sirh_caudalesvert_detalle_v")
@NamedQueries( { @NamedQuery(name = "CaudalesVertidosDetalleTO.findAllFuente",
                             query = "SELECT s FROM CaudalesVertidosDetalleTO s where s.id = :id and s.tramo_id=:tramo_id "),
                 @NamedQuery(name = "CaudalesVertidosDetalleTO.findAutoridadAll",
                             query = "SELECT s FROM CaudalesVertidosDetalleTO s where s.id_autoridad = :codigoAutoridad and s.id = :id and s.tramo_id=:tramo_id")

   } )
public class CaudalesVertidosDetalleTO implements Serializable {
    @Id
    @Column(name = "num", nullable = false)
    private Long num;
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "fuente")
    private String fuente;
    @Column(name = "tramo_id")
    private Long tramo_id;
    @Column(name = "tramo")
    private String tramo;
    @Column(name = "num_expediente")
    private String num_expediente;
    @Column(name = "num_res_permiso_vert")
    private String num_res_permiso_vert;
    
    @Column(name = "caudal")
    private Double caudal;
    @Column(name = "id_autoridad")
    private Long id_autoridad;
    @Column(name = "autoridad")
    private String autoridad;  
      
      
    public CaudalesVertidosDetalleTO() {
        this.num_expediente="";
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public String getFuente() {
        return fuente;
    }

    public void setTramo(String tramo) {
        this.tramo = tramo;
    }

    public String getTramo() {
        return tramo;
    }

   

    
    public void setId_autoridad(Long id_autoridad) {
        this.id_autoridad = id_autoridad;
    }

    public Long getId_autoridad() {
        return id_autoridad;
    }

   

    public void setAutoridad(String autoridad) {
        this.autoridad = autoridad;
    }

    public String getAutoridad() {
        return autoridad;
    }

    public void setNum_expediente(String num_expediente) {
        this.num_expediente = num_expediente;
    }

    public String getNum_expediente() {
        return num_expediente;
    }

 

    public void setNum(Long num) {
        this.num = num;
    }

    public Long getNum() {
        return num;
    }

    public void setTramo_id(Long tramo_id) {
        this.tramo_id = tramo_id;
    }

    public Long getTramo_id() {
        return tramo_id;
    }

    public void setNum_res_permiso_vert(String num_res_permiso_vert) {
        this.num_res_permiso_vert = num_res_permiso_vert;
    }

    public String getNum_res_permiso_vert() {
        return num_res_permiso_vert;
    }

    public void setCaudal(Double caudal) {
        this.caudal = caudal;
    }

    public Double getCaudal() {
        return caudal;
    }
}
