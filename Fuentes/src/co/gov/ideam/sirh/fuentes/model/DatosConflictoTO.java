package co.gov.ideam.sirh.fuentes.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class DatosConflictoTO implements Serializable {

    @Id
    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "cantidad")
    private Long cantidad;

    @Transient
    private Long idAutoridad;


    public DatosConflictoTO() {
        super();
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public void setIdAutoridad(Long idAutoridad) {
        this.idAutoridad = idAutoridad;
    }

    public Long getIdAutoridad() {
        return idAutoridad;
    }


    public String getSqlConflictosXTipo() {

        String nroConflictosPorTipo =
                " select  l.valor as descripcion,  count(*) as cantidad  " +
                " from  fntt_conflicto c ,fntt_conflicto_tipo t, part_listas l " +
                " where l.id=t.id_tipo_conflicto and c.id = t.id_conflicto ";

        if (getIdAutoridad() != null) 
            nroConflictosPorTipo += " and  c.cod_autoridad = " + getIdAutoridad() ;
        
        nroConflictosPorTipo += " group by l.valor  order by cantidad desc";

        return nroConflictosPorTipo;
    }

}
