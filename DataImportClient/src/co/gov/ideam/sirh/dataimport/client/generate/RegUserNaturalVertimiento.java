
package co.gov.ideam.sirh.dataimport.client.generate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for regUserNaturalVertimiento complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="regUserNaturalVertimiento">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="altitud" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="apellido" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="area" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="areatotal" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="caudalautorizado" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="caudaldiseno" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="cedulacatastral" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="centropoblado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="centropobladovert" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="clasificacionsuelo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="departamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deptopredio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deptopvert" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descripcionsitio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dircorrespondencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="direccionpredio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="evaluacionambiental" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fax" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaapruebaplan" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaexpresobras" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaexpresplanos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechanotifapruebaplan" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechanotifobras" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fecharesinitramite" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fecharespresentaplan" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fecharespvert" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="frecuenciames" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="fuente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gradoslat" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="gradoslon" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="idautoridad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="matriculainmobiliaria" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="minutoslat" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="minutoslon" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="municipio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="municipiopredio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="municipiopvert" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombrepredio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numdocumento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numexpediente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numresapruebaobras" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numresapruebaplan" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numresiniciotramite" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numresplanos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numrespresentaplan" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numrespvert" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="otros" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pretratamiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="primario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="secundario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="segundolat" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="segundoslon" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="sistemaref" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subzona" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="telefono" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="terciarios" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tiempodescarga" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="tipocentropoblado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipocentropobladovert" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipodocumento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoflujo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipofuente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipopersona" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipotenencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipovertimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tramo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vigenciaplanfin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vigenciaplanini" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vigenciapvfin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vigenciapvini" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="zona" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "regUserNaturalVertimiento", propOrder = {
    "altitud",
    "apellido",
    "area",
    "areatotal",
    "caudalautorizado",
    "caudaldiseno",
    "cedulacatastral",
    "centropoblado",
    "centropobladovert",
    "clasificacionsuelo",
    "departamento",
    "deptopredio",
    "deptopvert",
    "descripcionsitio",
    "dircorrespondencia",
    "direccionpredio",
    "email",
    "evaluacionambiental",
    "fax",
    "fechaapruebaplan",
    "fechaexpresobras",
    "fechaexpresplanos",
    "fechanotifapruebaplan",
    "fechanotifobras",
    "fecharesinitramite",
    "fecharespresentaplan",
    "fecharespvert",
    "frecuenciames",
    "fuente",
    "gradoslat",
    "gradoslon",
    "id",
    "idautoridad",
    "matriculainmobiliaria",
    "minutoslat",
    "minutoslon",
    "municipio",
    "municipiopredio",
    "municipiopvert",
    "nombre",
    "nombrepredio",
    "numdocumento",
    "numexpediente",
    "numresapruebaobras",
    "numresapruebaplan",
    "numresiniciotramite",
    "numresplanos",
    "numrespresentaplan",
    "numrespvert",
    "otros",
    "pretratamiento",
    "primario",
    "secundario",
    "segundolat",
    "segundoslon",
    "sistemaref",
    "subzona",
    "telefono",
    "terciarios",
    "tiempodescarga",
    "tipocentropoblado",
    "tipocentropobladovert",
    "tipodocumento",
    "tipoflujo",
    "tipofuente",
    "tipopersona",
    "tipotenencia",
    "tipovertimiento",
    "tramo",
    "vigenciaplanfin",
    "vigenciaplanini",
    "vigenciapvfin",
    "vigenciapvini",
    "zona"
})
public class RegUserNaturalVertimiento {

    protected Double altitud;
    protected String apellido;
    protected String area;
    protected Double areatotal;
    protected Double caudalautorizado;
    protected Double caudaldiseno;
    protected String cedulacatastral;
    protected String centropoblado;
    protected String centropobladovert;
    protected String clasificacionsuelo;
    protected String departamento;
    protected String deptopredio;
    protected String deptopvert;
    protected String descripcionsitio;
    protected String dircorrespondencia;
    protected String direccionpredio;
    protected String email;
    protected String evaluacionambiental;
    protected String fax;
    protected String fechaapruebaplan;
    protected String fechaexpresobras;
    protected String fechaexpresplanos;
    protected String fechanotifapruebaplan;
    protected String fechanotifobras;
    protected String fecharesinitramite;
    protected String fecharespresentaplan;
    protected String fecharespvert;
    protected Double frecuenciames;
    protected String fuente;
    protected Integer gradoslat;
    protected Integer gradoslon;
    protected Integer id;
    protected String idautoridad;
    protected String matriculainmobiliaria;
    protected Integer minutoslat;
    protected Integer minutoslon;
    protected String municipio;
    protected String municipiopredio;
    protected String municipiopvert;
    protected String nombre;
    protected String nombrepredio;
    protected String numdocumento;
    protected String numexpediente;
    protected String numresapruebaobras;
    protected String numresapruebaplan;
    protected String numresiniciotramite;
    protected String numresplanos;
    protected String numrespresentaplan;
    protected String numrespvert;
    protected String otros;
    protected String pretratamiento;
    protected String primario;
    protected String secundario;
    protected Double segundolat;
    protected Double segundoslon;
    protected String sistemaref;
    protected String subzona;
    protected String telefono;
    protected String terciarios;
    protected Double tiempodescarga;
    protected String tipocentropoblado;
    protected String tipocentropobladovert;
    protected String tipodocumento;
    protected String tipoflujo;
    protected String tipofuente;
    protected String tipopersona;
    protected String tipotenencia;
    protected String tipovertimiento;
    protected String tramo;
    protected String vigenciaplanfin;
    protected String vigenciaplanini;
    protected String vigenciapvfin;
    protected String vigenciapvini;
    protected String zona;

    /**
     * Gets the value of the altitud property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getAltitud() {
        return altitud;
    }

    /**
     * Sets the value of the altitud property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAltitud(Double value) {
        this.altitud = value;
    }

    /**
     * Gets the value of the apellido property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Sets the value of the apellido property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApellido(String value) {
        this.apellido = value;
    }

    /**
     * Gets the value of the area property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArea() {
        return area;
    }

    /**
     * Sets the value of the area property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArea(String value) {
        this.area = value;
    }

    /**
     * Gets the value of the areatotal property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getAreatotal() {
        return areatotal;
    }

    /**
     * Sets the value of the areatotal property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAreatotal(Double value) {
        this.areatotal = value;
    }

    /**
     * Gets the value of the caudalautorizado property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getCaudalautorizado() {
        return caudalautorizado;
    }

    /**
     * Sets the value of the caudalautorizado property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setCaudalautorizado(Double value) {
        this.caudalautorizado = value;
    }

    /**
     * Gets the value of the caudaldiseno property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getCaudaldiseno() {
        return caudaldiseno;
    }

    /**
     * Sets the value of the caudaldiseno property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setCaudaldiseno(Double value) {
        this.caudaldiseno = value;
    }

    /**
     * Gets the value of the cedulacatastral property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCedulacatastral() {
        return cedulacatastral;
    }

    /**
     * Sets the value of the cedulacatastral property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCedulacatastral(String value) {
        this.cedulacatastral = value;
    }

    /**
     * Gets the value of the centropoblado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCentropoblado() {
        return centropoblado;
    }

    /**
     * Sets the value of the centropoblado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCentropoblado(String value) {
        this.centropoblado = value;
    }

    /**
     * Gets the value of the centropobladovert property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCentropobladovert() {
        return centropobladovert;
    }

    /**
     * Sets the value of the centropobladovert property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCentropobladovert(String value) {
        this.centropobladovert = value;
    }

    /**
     * Gets the value of the clasificacionsuelo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClasificacionsuelo() {
        return clasificacionsuelo;
    }

    /**
     * Sets the value of the clasificacionsuelo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClasificacionsuelo(String value) {
        this.clasificacionsuelo = value;
    }

    /**
     * Gets the value of the departamento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartamento() {
        return departamento;
    }

    /**
     * Sets the value of the departamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartamento(String value) {
        this.departamento = value;
    }

    /**
     * Gets the value of the deptopredio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeptopredio() {
        return deptopredio;
    }

    /**
     * Sets the value of the deptopredio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeptopredio(String value) {
        this.deptopredio = value;
    }

    /**
     * Gets the value of the deptopvert property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeptopvert() {
        return deptopvert;
    }

    /**
     * Sets the value of the deptopvert property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeptopvert(String value) {
        this.deptopvert = value;
    }

    /**
     * Gets the value of the descripcionsitio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcionsitio() {
        return descripcionsitio;
    }

    /**
     * Sets the value of the descripcionsitio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcionsitio(String value) {
        this.descripcionsitio = value;
    }

    /**
     * Gets the value of the dircorrespondencia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDircorrespondencia() {
        return dircorrespondencia;
    }

    /**
     * Sets the value of the dircorrespondencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDircorrespondencia(String value) {
        this.dircorrespondencia = value;
    }

    /**
     * Gets the value of the direccionpredio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDireccionpredio() {
        return direccionpredio;
    }

    /**
     * Sets the value of the direccionpredio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDireccionpredio(String value) {
        this.direccionpredio = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the evaluacionambiental property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEvaluacionambiental() {
        return evaluacionambiental;
    }

    /**
     * Sets the value of the evaluacionambiental property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEvaluacionambiental(String value) {
        this.evaluacionambiental = value;
    }

    /**
     * Gets the value of the fax property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFax() {
        return fax;
    }

    /**
     * Sets the value of the fax property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFax(String value) {
        this.fax = value;
    }

    /**
     * Gets the value of the fechaapruebaplan property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaapruebaplan() {
        return fechaapruebaplan;
    }

    /**
     * Sets the value of the fechaapruebaplan property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaapruebaplan(String value) {
        this.fechaapruebaplan = value;
    }

    /**
     * Gets the value of the fechaexpresobras property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaexpresobras() {
        return fechaexpresobras;
    }

    /**
     * Sets the value of the fechaexpresobras property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaexpresobras(String value) {
        this.fechaexpresobras = value;
    }

    /**
     * Gets the value of the fechaexpresplanos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaexpresplanos() {
        return fechaexpresplanos;
    }

    /**
     * Sets the value of the fechaexpresplanos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaexpresplanos(String value) {
        this.fechaexpresplanos = value;
    }

    /**
     * Gets the value of the fechanotifapruebaplan property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechanotifapruebaplan() {
        return fechanotifapruebaplan;
    }

    /**
     * Sets the value of the fechanotifapruebaplan property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechanotifapruebaplan(String value) {
        this.fechanotifapruebaplan = value;
    }

    /**
     * Gets the value of the fechanotifobras property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechanotifobras() {
        return fechanotifobras;
    }

    /**
     * Sets the value of the fechanotifobras property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechanotifobras(String value) {
        this.fechanotifobras = value;
    }

    /**
     * Gets the value of the fecharesinitramite property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFecharesinitramite() {
        return fecharesinitramite;
    }

    /**
     * Sets the value of the fecharesinitramite property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFecharesinitramite(String value) {
        this.fecharesinitramite = value;
    }

    /**
     * Gets the value of the fecharespresentaplan property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFecharespresentaplan() {
        return fecharespresentaplan;
    }

    /**
     * Sets the value of the fecharespresentaplan property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFecharespresentaplan(String value) {
        this.fecharespresentaplan = value;
    }

    /**
     * Gets the value of the fecharespvert property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFecharespvert() {
        return fecharespvert;
    }

    /**
     * Sets the value of the fecharespvert property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFecharespvert(String value) {
        this.fecharespvert = value;
    }

    /**
     * Gets the value of the frecuenciames property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getFrecuenciames() {
        return frecuenciames;
    }

    /**
     * Sets the value of the frecuenciames property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setFrecuenciames(Double value) {
        this.frecuenciames = value;
    }

    /**
     * Gets the value of the fuente property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFuente() {
        return fuente;
    }

    /**
     * Sets the value of the fuente property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFuente(String value) {
        this.fuente = value;
    }

    /**
     * Gets the value of the gradoslat property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getGradoslat() {
        return gradoslat;
    }

    /**
     * Sets the value of the gradoslat property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setGradoslat(Integer value) {
        this.gradoslat = value;
    }

    /**
     * Gets the value of the gradoslon property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getGradoslon() {
        return gradoslon;
    }

    /**
     * Sets the value of the gradoslon property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setGradoslon(Integer value) {
        this.gradoslon = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setId(Integer value) {
        this.id = value;
    }

    /**
     * Gets the value of the idautoridad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdautoridad() {
        return idautoridad;
    }

    /**
     * Sets the value of the idautoridad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdautoridad(String value) {
        this.idautoridad = value;
    }

    /**
     * Gets the value of the matriculainmobiliaria property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMatriculainmobiliaria() {
        return matriculainmobiliaria;
    }

    /**
     * Sets the value of the matriculainmobiliaria property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMatriculainmobiliaria(String value) {
        this.matriculainmobiliaria = value;
    }

    /**
     * Gets the value of the minutoslat property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMinutoslat() {
        return minutoslat;
    }

    /**
     * Sets the value of the minutoslat property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMinutoslat(Integer value) {
        this.minutoslat = value;
    }

    /**
     * Gets the value of the minutoslon property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMinutoslon() {
        return minutoslon;
    }

    /**
     * Sets the value of the minutoslon property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMinutoslon(Integer value) {
        this.minutoslon = value;
    }

    /**
     * Gets the value of the municipio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMunicipio() {
        return municipio;
    }

    /**
     * Sets the value of the municipio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMunicipio(String value) {
        this.municipio = value;
    }

    /**
     * Gets the value of the municipiopredio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMunicipiopredio() {
        return municipiopredio;
    }

    /**
     * Sets the value of the municipiopredio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMunicipiopredio(String value) {
        this.municipiopredio = value;
    }

    /**
     * Gets the value of the municipiopvert property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMunicipiopvert() {
        return municipiopvert;
    }

    /**
     * Sets the value of the municipiopvert property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMunicipiopvert(String value) {
        this.municipiopvert = value;
    }

    /**
     * Gets the value of the nombre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the value of the nombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Gets the value of the nombrepredio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombrepredio() {
        return nombrepredio;
    }

    /**
     * Sets the value of the nombrepredio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombrepredio(String value) {
        this.nombrepredio = value;
    }

    /**
     * Gets the value of the numdocumento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumdocumento() {
        return numdocumento;
    }

    /**
     * Sets the value of the numdocumento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumdocumento(String value) {
        this.numdocumento = value;
    }

    /**
     * Gets the value of the numexpediente property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumexpediente() {
        return numexpediente;
    }

    /**
     * Sets the value of the numexpediente property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumexpediente(String value) {
        this.numexpediente = value;
    }

    /**
     * Gets the value of the numresapruebaobras property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumresapruebaobras() {
        return numresapruebaobras;
    }

    /**
     * Sets the value of the numresapruebaobras property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumresapruebaobras(String value) {
        this.numresapruebaobras = value;
    }

    /**
     * Gets the value of the numresapruebaplan property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumresapruebaplan() {
        return numresapruebaplan;
    }

    /**
     * Sets the value of the numresapruebaplan property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumresapruebaplan(String value) {
        this.numresapruebaplan = value;
    }

    /**
     * Gets the value of the numresiniciotramite property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumresiniciotramite() {
        return numresiniciotramite;
    }

    /**
     * Sets the value of the numresiniciotramite property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumresiniciotramite(String value) {
        this.numresiniciotramite = value;
    }

    /**
     * Gets the value of the numresplanos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumresplanos() {
        return numresplanos;
    }

    /**
     * Sets the value of the numresplanos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumresplanos(String value) {
        this.numresplanos = value;
    }

    /**
     * Gets the value of the numrespresentaplan property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumrespresentaplan() {
        return numrespresentaplan;
    }

    /**
     * Sets the value of the numrespresentaplan property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumrespresentaplan(String value) {
        this.numrespresentaplan = value;
    }

    /**
     * Gets the value of the numrespvert property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumrespvert() {
        return numrespvert;
    }

    /**
     * Sets the value of the numrespvert property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumrespvert(String value) {
        this.numrespvert = value;
    }

    /**
     * Gets the value of the otros property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtros() {
        return otros;
    }

    /**
     * Sets the value of the otros property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtros(String value) {
        this.otros = value;
    }

    /**
     * Gets the value of the pretratamiento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPretratamiento() {
        return pretratamiento;
    }

    /**
     * Sets the value of the pretratamiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPretratamiento(String value) {
        this.pretratamiento = value;
    }

    /**
     * Gets the value of the primario property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrimario() {
        return primario;
    }

    /**
     * Sets the value of the primario property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrimario(String value) {
        this.primario = value;
    }

    /**
     * Gets the value of the secundario property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecundario() {
        return secundario;
    }

    /**
     * Sets the value of the secundario property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecundario(String value) {
        this.secundario = value;
    }

    /**
     * Gets the value of the segundolat property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getSegundolat() {
        return segundolat;
    }

    /**
     * Sets the value of the segundolat property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setSegundolat(Double value) {
        this.segundolat = value;
    }

    /**
     * Gets the value of the segundoslon property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getSegundoslon() {
        return segundoslon;
    }

    /**
     * Sets the value of the segundoslon property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setSegundoslon(Double value) {
        this.segundoslon = value;
    }

    /**
     * Gets the value of the sistemaref property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSistemaref() {
        return sistemaref;
    }

    /**
     * Sets the value of the sistemaref property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSistemaref(String value) {
        this.sistemaref = value;
    }

    /**
     * Gets the value of the subzona property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubzona() {
        return subzona;
    }

    /**
     * Sets the value of the subzona property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubzona(String value) {
        this.subzona = value;
    }

    /**
     * Gets the value of the telefono property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Sets the value of the telefono property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelefono(String value) {
        this.telefono = value;
    }

    /**
     * Gets the value of the terciarios property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTerciarios() {
        return terciarios;
    }

    /**
     * Sets the value of the terciarios property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTerciarios(String value) {
        this.terciarios = value;
    }

    /**
     * Gets the value of the tiempodescarga property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTiempodescarga() {
        return tiempodescarga;
    }

    /**
     * Sets the value of the tiempodescarga property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTiempodescarga(Double value) {
        this.tiempodescarga = value;
    }

    /**
     * Gets the value of the tipocentropoblado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipocentropoblado() {
        return tipocentropoblado;
    }

    /**
     * Sets the value of the tipocentropoblado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipocentropoblado(String value) {
        this.tipocentropoblado = value;
    }

    /**
     * Gets the value of the tipocentropobladovert property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipocentropobladovert() {
        return tipocentropobladovert;
    }

    /**
     * Sets the value of the tipocentropobladovert property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipocentropobladovert(String value) {
        this.tipocentropobladovert = value;
    }

    /**
     * Gets the value of the tipodocumento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipodocumento() {
        return tipodocumento;
    }

    /**
     * Sets the value of the tipodocumento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipodocumento(String value) {
        this.tipodocumento = value;
    }

    /**
     * Gets the value of the tipoflujo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoflujo() {
        return tipoflujo;
    }

    /**
     * Sets the value of the tipoflujo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoflujo(String value) {
        this.tipoflujo = value;
    }

    /**
     * Gets the value of the tipofuente property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipofuente() {
        return tipofuente;
    }

    /**
     * Sets the value of the tipofuente property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipofuente(String value) {
        this.tipofuente = value;
    }

    /**
     * Gets the value of the tipopersona property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipopersona() {
        return tipopersona;
    }

    /**
     * Sets the value of the tipopersona property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipopersona(String value) {
        this.tipopersona = value;
    }

    /**
     * Gets the value of the tipotenencia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipotenencia() {
        return tipotenencia;
    }

    /**
     * Sets the value of the tipotenencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipotenencia(String value) {
        this.tipotenencia = value;
    }

    /**
     * Gets the value of the tipovertimiento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipovertimiento() {
        return tipovertimiento;
    }

    /**
     * Sets the value of the tipovertimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipovertimiento(String value) {
        this.tipovertimiento = value;
    }

    /**
     * Gets the value of the tramo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTramo() {
        return tramo;
    }

    /**
     * Sets the value of the tramo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTramo(String value) {
        this.tramo = value;
    }

    /**
     * Gets the value of the vigenciaplanfin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVigenciaplanfin() {
        return vigenciaplanfin;
    }

    /**
     * Sets the value of the vigenciaplanfin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVigenciaplanfin(String value) {
        this.vigenciaplanfin = value;
    }

    /**
     * Gets the value of the vigenciaplanini property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVigenciaplanini() {
        return vigenciaplanini;
    }

    /**
     * Sets the value of the vigenciaplanini property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVigenciaplanini(String value) {
        this.vigenciaplanini = value;
    }

    /**
     * Gets the value of the vigenciapvfin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVigenciapvfin() {
        return vigenciapvfin;
    }

    /**
     * Sets the value of the vigenciapvfin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVigenciapvfin(String value) {
        this.vigenciapvfin = value;
    }

    /**
     * Gets the value of the vigenciapvini property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVigenciapvini() {
        return vigenciapvini;
    }

    /**
     * Sets the value of the vigenciapvini property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVigenciapvini(String value) {
        this.vigenciapvini = value;
    }

    /**
     * Gets the value of the zona property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZona() {
        return zona;
    }

    /**
     * Sets the value of the zona property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZona(String value) {
        this.zona = value;
    }

}
