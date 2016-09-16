
package co.gov.ideam.sirh.dataimport.client.generate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for fntvFuentesTramos complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="fntvFuentesTramos">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="altitudpf" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="altitudpi" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="area" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigocuenca" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cuenca" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descripcionfuente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descripciontramo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="existebd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gradoslatpf" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="gradoslatpi" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="gradoslonpf" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="gradoslonpi" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="idautoridad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idfuente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="longitud" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="minutoslatpf" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="minutoslatpi" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="minutoslonpf" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="minutoslonpi" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="nombrefuente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombretramo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numcarfuente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numcartramo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="segundolatpf" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="segundolatpi" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="segundolonpf" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="segundolonpi" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="sistemareferenciapf" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sistemareferenciapi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subzona" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoflujo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipofuente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "fntvFuentesTramos", propOrder = {
    "altitudpf",
    "altitudpi",
    "area",
    "codigocuenca",
    "cuenca",
    "descripcionfuente",
    "descripciontramo",
    "existebd",
    "gradoslatpf",
    "gradoslatpi",
    "gradoslonpf",
    "gradoslonpi",
    "id",
    "idautoridad",
    "idfuente",
    "longitud",
    "minutoslatpf",
    "minutoslatpi",
    "minutoslonpf",
    "minutoslonpi",
    "nombrefuente",
    "nombretramo",
    "numcarfuente",
    "numcartramo",
    "segundolatpf",
    "segundolatpi",
    "segundolonpf",
    "segundolonpi",
    "sistemareferenciapf",
    "sistemareferenciapi",
    "subzona",
    "tipoflujo",
    "tipofuente",
    "zona"
})
public class FntvFuentesTramos {

    protected Double altitudpf;
    protected Double altitudpi;
    protected String area;
    protected String codigocuenca;
    protected String cuenca;
    protected String descripcionfuente;
    protected String descripciontramo;
    protected String existebd;
    protected Integer gradoslatpf;
    protected Integer gradoslatpi;
    protected Integer gradoslonpf;
    protected Integer gradoslonpi;
    protected Integer id;
    protected String idautoridad;
    protected String idfuente;
    protected Double longitud;
    protected Integer minutoslatpf;
    protected Integer minutoslatpi;
    protected Integer minutoslonpf;
    protected Integer minutoslonpi;
    protected String nombrefuente;
    protected String nombretramo;
    protected String numcarfuente;
    protected String numcartramo;
    protected Double segundolatpf;
    protected Double segundolatpi;
    protected Double segundolonpf;
    protected Double segundolonpi;
    protected String sistemareferenciapf;
    protected String sistemareferenciapi;
    protected String subzona;
    protected String tipoflujo;
    protected String tipofuente;
    protected String zona;

    /**
     * Gets the value of the altitudpf property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getAltitudpf() {
        return altitudpf;
    }

    /**
     * Sets the value of the altitudpf property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAltitudpf(Double value) {
        this.altitudpf = value;
    }

    /**
     * Gets the value of the altitudpi property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getAltitudpi() {
        return altitudpi;
    }

    /**
     * Sets the value of the altitudpi property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAltitudpi(Double value) {
        this.altitudpi = value;
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
     * Gets the value of the codigocuenca property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigocuenca() {
        return codigocuenca;
    }

    /**
     * Sets the value of the codigocuenca property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigocuenca(String value) {
        this.codigocuenca = value;
    }

    /**
     * Gets the value of the cuenca property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCuenca() {
        return cuenca;
    }

    /**
     * Sets the value of the cuenca property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCuenca(String value) {
        this.cuenca = value;
    }

    /**
     * Gets the value of the descripcionfuente property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcionfuente() {
        return descripcionfuente;
    }

    /**
     * Sets the value of the descripcionfuente property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcionfuente(String value) {
        this.descripcionfuente = value;
    }

    /**
     * Gets the value of the descripciontramo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripciontramo() {
        return descripciontramo;
    }

    /**
     * Sets the value of the descripciontramo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripciontramo(String value) {
        this.descripciontramo = value;
    }

    /**
     * Gets the value of the existebd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExistebd() {
        return existebd;
    }

    /**
     * Sets the value of the existebd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExistebd(String value) {
        this.existebd = value;
    }

    /**
     * Gets the value of the gradoslatpf property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getGradoslatpf() {
        return gradoslatpf;
    }

    /**
     * Sets the value of the gradoslatpf property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setGradoslatpf(Integer value) {
        this.gradoslatpf = value;
    }

    /**
     * Gets the value of the gradoslatpi property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getGradoslatpi() {
        return gradoslatpi;
    }

    /**
     * Sets the value of the gradoslatpi property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setGradoslatpi(Integer value) {
        this.gradoslatpi = value;
    }

    /**
     * Gets the value of the gradoslonpf property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getGradoslonpf() {
        return gradoslonpf;
    }

    /**
     * Sets the value of the gradoslonpf property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setGradoslonpf(Integer value) {
        this.gradoslonpf = value;
    }

    /**
     * Gets the value of the gradoslonpi property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getGradoslonpi() {
        return gradoslonpi;
    }

    /**
     * Sets the value of the gradoslonpi property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setGradoslonpi(Integer value) {
        this.gradoslonpi = value;
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
     * Gets the value of the idfuente property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdfuente() {
        return idfuente;
    }

    /**
     * Sets the value of the idfuente property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdfuente(String value) {
        this.idfuente = value;
    }

    /**
     * Gets the value of the longitud property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getLongitud() {
        return longitud;
    }

    /**
     * Sets the value of the longitud property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setLongitud(Double value) {
        this.longitud = value;
    }

    /**
     * Gets the value of the minutoslatpf property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMinutoslatpf() {
        return minutoslatpf;
    }

    /**
     * Sets the value of the minutoslatpf property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMinutoslatpf(Integer value) {
        this.minutoslatpf = value;
    }

    /**
     * Gets the value of the minutoslatpi property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMinutoslatpi() {
        return minutoslatpi;
    }

    /**
     * Sets the value of the minutoslatpi property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMinutoslatpi(Integer value) {
        this.minutoslatpi = value;
    }

    /**
     * Gets the value of the minutoslonpf property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMinutoslonpf() {
        return minutoslonpf;
    }

    /**
     * Sets the value of the minutoslonpf property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMinutoslonpf(Integer value) {
        this.minutoslonpf = value;
    }

    /**
     * Gets the value of the minutoslonpi property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMinutoslonpi() {
        return minutoslonpi;
    }

    /**
     * Sets the value of the minutoslonpi property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMinutoslonpi(Integer value) {
        this.minutoslonpi = value;
    }

    /**
     * Gets the value of the nombrefuente property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombrefuente() {
        return nombrefuente;
    }

    /**
     * Sets the value of the nombrefuente property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombrefuente(String value) {
        this.nombrefuente = value;
    }

    /**
     * Gets the value of the nombretramo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombretramo() {
        return nombretramo;
    }

    /**
     * Sets the value of the nombretramo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombretramo(String value) {
        this.nombretramo = value;
    }

    /**
     * Gets the value of the numcarfuente property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumcarfuente() {
        return numcarfuente;
    }

    /**
     * Sets the value of the numcarfuente property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumcarfuente(String value) {
        this.numcarfuente = value;
    }

    /**
     * Gets the value of the numcartramo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumcartramo() {
        return numcartramo;
    }

    /**
     * Sets the value of the numcartramo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumcartramo(String value) {
        this.numcartramo = value;
    }

    /**
     * Gets the value of the segundolatpf property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getSegundolatpf() {
        return segundolatpf;
    }

    /**
     * Sets the value of the segundolatpf property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setSegundolatpf(Double value) {
        this.segundolatpf = value;
    }

    /**
     * Gets the value of the segundolatpi property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getSegundolatpi() {
        return segundolatpi;
    }

    /**
     * Sets the value of the segundolatpi property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setSegundolatpi(Double value) {
        this.segundolatpi = value;
    }

    /**
     * Gets the value of the segundolonpf property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getSegundolonpf() {
        return segundolonpf;
    }

    /**
     * Sets the value of the segundolonpf property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setSegundolonpf(Double value) {
        this.segundolonpf = value;
    }

    /**
     * Gets the value of the segundolonpi property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getSegundolonpi() {
        return segundolonpi;
    }

    /**
     * Sets the value of the segundolonpi property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setSegundolonpi(Double value) {
        this.segundolonpi = value;
    }

    /**
     * Gets the value of the sistemareferenciapf property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSistemareferenciapf() {
        return sistemareferenciapf;
    }

    /**
     * Sets the value of the sistemareferenciapf property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSistemareferenciapf(String value) {
        this.sistemareferenciapf = value;
    }

    /**
     * Gets the value of the sistemareferenciapi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSistemareferenciapi() {
        return sistemareferenciapi;
    }

    /**
     * Sets the value of the sistemareferenciapi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSistemareferenciapi(String value) {
        this.sistemareferenciapi = value;
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
