package co.gov.ideam.sirh.porh.model;

import java.io.Serializable;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
  @NamedQuery(name = "PorhParametrosTO.findAllByUso", query = "select o from PorhParametrosTO o where o.uso_id = :codigoUsoTramo"),
  @NamedQuery(name = "PorhParametrosTO.findAllByTramo", query = "select o from PorhParametrosTO o where o.tramoId = :codigTramo")
})
@Table(name = "porv_parametros")
public class PorhParametrosTO implements Serializable {
    @Column(name="fecha")
    private Timestamp fecha;
    @Column(name="objetivo")
    private String objetivo;
    @Column(name="parametro")
    private String parametro;
    @Column(name="parametro_id")
    private Integer parametro_id;
    @Column(name="unidad")
    private String unidad;
    @Column(name="unidad_id")
    private Integer unidad_id;
    @Column(name="uso_id")
    private Long uso_id;
    @Column(name="valor")
    private String valor;
    @Id
    @Column(name="objetivo_id")
    private Long objetivoId;
    @Column(name="uso")
    private String nombreUso;
    @Column(name="tramo_id")
    private Long tramoId;
    @Column(name="objetivo_ecologico")
    private String objetivoEcologico;

    public PorhParametrosTO() {
    }

    public PorhParametrosTO(Timestamp fecha, String objetivo, String parametro,
                            Integer parametro_id, String unidad,
                            Integer unidad_id, Long uso_id, String valor,String objetivoEcologico) {
        this.fecha = fecha;
        this.objetivo = objetivo;
        this.parametro = parametro;
        this.parametro_id = parametro_id;
        this.unidad = unidad;
        this.unidad_id = unidad_id;
        this.uso_id = uso_id;
        this.valor = valor;
        this.objetivoEcologico = objetivoEcologico;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public String getObjetivo() {
        if(objetivo.equalsIgnoreCase("CP")){
            return "Corto";
        }else if(objetivo.equalsIgnoreCase("MP")){
            return "Mediano";
        }else if(objetivo.equalsIgnoreCase("LP")){
            return "Largo";
        }
        return "";
    }

    public String getObjetivoDescripcionCorta() {
        return objetivo;
    }
    
    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getParametro() {
        return parametro;
    }

    public void setParametro(String parametro) {
        this.parametro = parametro;
    }

    public Integer getParametro_id() {
        return parametro_id;
    }

    public void setParametro_id(Integer parametro_id) {
        this.parametro_id = parametro_id;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public Integer getUnidad_id() {
        return unidad_id;
    }

    public void setUnidad_id(Integer unidad_id) {
        this.unidad_id = unidad_id;
    }

    public Long getUso_id() {
        return uso_id;
    }

    public void setUso_id(Long uso_id) {
        this.uso_id = uso_id;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Long getObjetivoId() {
        return objetivoId;
    }

    public void setObjetivoId(Long objetivoId) {
        this.objetivoId = objetivoId;
    }

    public String getNombreUso() {
        return nombreUso;
    }

    public void setNombreUso(String nombreUso) {
        this.nombreUso = nombreUso;
    }

    public Long getTramoId() {
        return tramoId;
    }

    public void setTramoId(Long tramoId) {
        this.tramoId = tramoId;
    }

    public void setObjetivoEcologico(String objetivoEcologico) {
      this.objetivoEcologico = objetivoEcologico;
    }
  
    public String getObjetivoEcologico() {
      return objetivoEcologico;
    }
}
