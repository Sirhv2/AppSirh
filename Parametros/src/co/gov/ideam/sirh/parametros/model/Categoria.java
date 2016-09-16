package co.gov.ideam.sirh.parametros.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@NamedQueries({
  @NamedQuery(name = "Categoria.findAll", query = "select o from Categoria o"),
  @NamedQuery(name = "Categoria.find", query = "select o from Categoria o where o.id = :codigo ")
})
@Table(name = "part_categorias")
public class Categoria  implements Serializable{
    
    public static final Long TIPO_USUARIO = 1L;
    public static final Long TIPO_DOCUMENTO = 2L;
    public static final Long TIPO_TENENCIA = 3L;
    public static final Long TIPO_CENTRO_POBLADO = 4L;
    public static final Long CLASIFICACION_SUELO = 5L;
    public static final Long SISTEMAS_REFERENCIA_GEOGRAFICO = 6L;
    public static final Long PRIORIZACION_PORH = 44L;
    public static final Long USOS_AGUA = 45L;
    public static final Long PARAMETROS = 41L;
    public static final Long UNIDADES = 14L;
    public static final Long OTROS_USOS = 11L;
    public static final Long NATURALEZA_ORGANIZACIONES = 56L;
    public static final Long TIPO_ORGANIZACION = 57L;
    public static final Long GESTION = 58L;
    public static final Long INVESTIGACION = 59L;
    public static final Long AREAS_TRABAJO = 60L;
    public static final Long TIPO_FUENTE_HIDRICA = 61L;
    public static final Long NIVEL_FORMACION = 62L;
    public static final Long TIPO_RECURSO_PUBLICACION = 55L;
	/**************************  INI porh Carlos ferro ****************/
    public static final Long OBJETIVOS_CALIDAD = 91L;
    public static final Long METODOLOGIA_CAUDAL_AMBIENTAL = 92L;
    public static final Long RIESGO_ASOCIADO_MEDICION = 93L;

	/**************************  INI DETERMINANTE AMBIENTAL  Jhosept *****************************/
	public static final Long COMPONENTE_AFECTADO_DET = 96L;
	public static final Long RECURSO_AFECTADO_DET = 97L;	
  
    @Id
    @Column(name = "id", nullable = false)
    private Long codigo;
    @Column(name = "valor", nullable = false)
    private String valor;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "categoria")        
    private List<Lista> lista = new ArrayList<Lista>();
    
    public Categoria() {
        super();
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public List<Lista> getLista() {
        return lista;
    }

    public void setLista(List<Lista> lista) {
        this.lista = lista;
    }

    public static Long getTIPO_USUARIO() {
        return TIPO_USUARIO;
    }

    public static Long getTIPO_DOCUMENTO() {
        return TIPO_DOCUMENTO;
    }

    public static Long getTIPO_TENENCIA() {
        return TIPO_TENENCIA;
    }

    public static Long getTIPO_CENTRO_POBLADO() {
        return TIPO_CENTRO_POBLADO;
    }

    public static Long getCLASIFICACION_SUELO() {
        return CLASIFICACION_SUELO;
    }

    public static Long getSISTEMAS_REFERENCIA_GEOGRAFICO() {
        return SISTEMAS_REFERENCIA_GEOGRAFICO;
    }

    public static Long getPRIORIZACION_PORH() {
        return PRIORIZACION_PORH;
    }

    public static Long getUSOS_AGUA() {
        return USOS_AGUA;
    }

    public static Long getPARAMETROS() {
        return PARAMETROS;
    }

    public static Long getUNIDADES() {
        return UNIDADES;
    }

    public static Long getTIPO_RECURSO_PUBLICACION() {
        return TIPO_RECURSO_PUBLICACION;
    }
	
	/**************************  INI DETERMINANTE AMBIENTAL  Jhosept *****************************/
	public static Long getCOMPONENTE_AFECTADO_DET() {
		  return COMPONENTE_AFECTADO_DET;
	  }
	public static Long getRECURSO_AFECTADO_DET() {
		  return RECURSO_AFECTADO_DET;
	  }
/**************************  FIN DETERMINANTE AMBIENTAL   Jhosept *****************************/
}
