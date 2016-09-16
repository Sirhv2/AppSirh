
package co.gov.ideam.sirh.dataimport.client.generate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for regUserNaturalConcesion complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="regUserNaturalConcesion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="aduccion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="altitudcapt" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="altitudpredio" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="apellido" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="aprovechamiento" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="area" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="areacaptacion" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="areacultivo" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="areatotal" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="caudalabast" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="caudalabrev" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="caudalconcesionado" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="caudalcultivo" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="caudaldiseno" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="caudalotros" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="caudalpesca" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="cedulacatastral" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="centropoblado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="clasificacionsuelo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="continuidadservicio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="decripcionotros" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="departamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deptocorrespondencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="desarenador" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descripcionsitio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="direccioncorrespondencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="direccionpredio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="eficienciacultivo" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="estadocapacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fax" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaexpedicion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaexpedresolobra" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaexpresolplanos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechanotificacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechanotificacionplanos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechanotifobra" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fuente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gradoslatcapt" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="gradoslatpredio" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="gradosloncapt" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="gradoslonpredio" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="idautoridad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idpunto" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="macromedicion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="matriculainmobiliaria" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="minutoslatcapt" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="minutoslatpredio" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="minutosloncapt" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="minutoslonpredio" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="municipio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="municipiocorrespondencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombrepredio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numanimalesabrev" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="numanimalespesca" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="numdocumento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numexpediente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numpersonaspermanentes" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="numpersonastransitorias" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="numresolcaudal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numresolplanos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numresolucionobra" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="obscaptacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ofertadisponible" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="ofertatotal" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="produccion" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="provinciahidro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ptap" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reddistribucion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="segundoslatcapt" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="segundoslatpredio" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="segundosloncapt" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="segundoslonpredio" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="sistemarefcapt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sistemarefpredio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subzona" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tanque" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="telefono" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tieneservidumbre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoanimalabrev" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoanimalpesca" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipocaptacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipocentropoblado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipocultivo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipodocumento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipofuente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipofuentecap" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipopersona" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipotenencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipousootros" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tramo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="unidadhidro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vigenciadesde" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vigenciahasta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "regUserNaturalConcesion", propOrder = {
    "aduccion",
    "altitudcapt",
    "altitudpredio",
    "apellido",
    "aprovechamiento",
    "area",
    "areacaptacion",
    "areacultivo",
    "areatotal",
    "caudalabast",
    "caudalabrev",
    "caudalconcesionado",
    "caudalcultivo",
    "caudaldiseno",
    "caudalotros",
    "caudalpesca",
    "cedulacatastral",
    "centropoblado",
    "clasificacionsuelo",
    "continuidadservicio",
    "decripcionotros",
    "departamento",
    "deptocorrespondencia",
    "desarenador",
    "descripcionsitio",
    "direccioncorrespondencia",
    "direccionpredio",
    "eficienciacultivo",
    "email",
    "estadocapacion",
    "fax",
    "fechaexpedicion",
    "fechaexpedresolobra",
    "fechaexpresolplanos",
    "fechanotificacion",
    "fechanotificacionplanos",
    "fechanotifobra",
    "fuente",
    "gradoslatcapt",
    "gradoslatpredio",
    "gradosloncapt",
    "gradoslonpredio",
    "id",
    "idautoridad",
    "idpunto",
    "macromedicion",
    "matriculainmobiliaria",
    "minutoslatcapt",
    "minutoslatpredio",
    "minutosloncapt",
    "minutoslonpredio",
    "municipio",
    "municipiocorrespondencia",
    "nombre",
    "nombrepredio",
    "numanimalesabrev",
    "numanimalespesca",
    "numdocumento",
    "numexpediente",
    "numpersonaspermanentes",
    "numpersonastransitorias",
    "numresolcaudal",
    "numresolplanos",
    "numresolucionobra",
    "obscaptacion",
    "ofertadisponible",
    "ofertatotal",
    "produccion",
    "provinciahidro",
    "ptap",
    "reddistribucion",
    "segundoslatcapt",
    "segundoslatpredio",
    "segundosloncapt",
    "segundoslonpredio",
    "sistemarefcapt",
    "sistemarefpredio",
    "subzona",
    "tanque",
    "telefono",
    "tieneservidumbre",
    "tipoanimalabrev",
    "tipoanimalpesca",
    "tipocaptacion",
    "tipocentropoblado",
    "tipocultivo",
    "tipodocumento",
    "tipofuente",
    "tipofuentecap",
    "tipopersona",
    "tipotenencia",
    "tipousootros",
    "tramo",
    "unidadhidro",
    "vigenciadesde",
    "vigenciahasta",
    "zona"
})
public class RegUserNaturalConcesion {

    protected String aduccion;
    protected Double altitudcapt;
    protected Double altitudpredio;
    protected String apellido;
    protected Double aprovechamiento;
    protected String area;
    protected Double areacaptacion;
    protected Double areacultivo;
    protected Double areatotal;
    protected Double caudalabast;
    protected Double caudalabrev;
    protected Double caudalconcesionado;
    protected Double caudalcultivo;
    protected Double caudaldiseno;
    protected Double caudalotros;
    protected Double caudalpesca;
    protected String cedulacatastral;
    protected String centropoblado;
    protected String clasificacionsuelo;
    protected String continuidadservicio;
    protected String decripcionotros;
    protected String departamento;
    protected String deptocorrespondencia;
    protected String desarenador;
    protected String descripcionsitio;
    protected String direccioncorrespondencia;
    protected String direccionpredio;
    protected Double eficienciacultivo;
    protected String email;
    protected String estadocapacion;
    protected String fax;
    protected String fechaexpedicion;
    protected String fechaexpedresolobra;
    protected String fechaexpresolplanos;
    protected String fechanotificacion;
    protected String fechanotificacionplanos;
    protected String fechanotifobra;
    protected String fuente;
    protected Integer gradoslatcapt;
    protected Integer gradoslatpredio;
    protected Integer gradosloncapt;
    protected Integer gradoslonpredio;
    protected Integer id;
    protected String idautoridad;
    protected Integer idpunto;
    protected String macromedicion;
    protected String matriculainmobiliaria;
    protected Integer minutoslatcapt;
    protected Integer minutoslatpredio;
    protected Integer minutosloncapt;
    protected Integer minutoslonpredio;
    protected String municipio;
    protected String municipiocorrespondencia;
    protected String nombre;
    protected String nombrepredio;
    protected Integer numanimalesabrev;
    protected Integer numanimalespesca;
    protected String numdocumento;
    protected String numexpediente;
    protected Integer numpersonaspermanentes;
    protected Integer numpersonastransitorias;
    protected String numresolcaudal;
    protected String numresolplanos;
    protected String numresolucionobra;
    protected String obscaptacion;
    protected Double ofertadisponible;
    protected Double ofertatotal;
    protected Double produccion;
    protected String provinciahidro;
    protected String ptap;
    protected String reddistribucion;
    protected Double segundoslatcapt;
    protected Double segundoslatpredio;
    protected Double segundosloncapt;
    protected Double segundoslonpredio;
    protected String sistemarefcapt;
    protected String sistemarefpredio;
    protected String subzona;
    protected String tanque;
    protected String telefono;
    protected String tieneservidumbre;
    protected String tipoanimalabrev;
    protected String tipoanimalpesca;
    protected String tipocaptacion;
    protected String tipocentropoblado;
    protected String tipocultivo;
    protected String tipodocumento;
    protected String tipofuente;
    protected String tipofuentecap;
    protected String tipopersona;
    protected String tipotenencia;
    protected String tipousootros;
    protected String tramo;
    protected String unidadhidro;
    protected String vigenciadesde;
    protected String vigenciahasta;
    protected String zona;

    /**
     * Gets the value of the aduccion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAduccion() {
        return aduccion;
    }

    /**
     * Sets the value of the aduccion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAduccion(String value) {
        this.aduccion = value;
    }

    /**
     * Gets the value of the altitudcapt property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getAltitudcapt() {
        return altitudcapt;
    }

    /**
     * Sets the value of the altitudcapt property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAltitudcapt(Double value) {
        this.altitudcapt = value;
    }

    /**
     * Gets the value of the altitudpredio property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getAltitudpredio() {
        return altitudpredio;
    }

    /**
     * Sets the value of the altitudpredio property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAltitudpredio(Double value) {
        this.altitudpredio = value;
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
     * Gets the value of the aprovechamiento property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getAprovechamiento() {
        return aprovechamiento;
    }

    /**
     * Sets the value of the aprovechamiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAprovechamiento(Double value) {
        this.aprovechamiento = value;
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
     * Gets the value of the areacaptacion property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getAreacaptacion() {
        return areacaptacion;
    }

    /**
     * Sets the value of the areacaptacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAreacaptacion(Double value) {
        this.areacaptacion = value;
    }

    /**
     * Gets the value of the areacultivo property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getAreacultivo() {
        return areacultivo;
    }

    /**
     * Sets the value of the areacultivo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAreacultivo(Double value) {
        this.areacultivo = value;
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
     * Gets the value of the caudalabast property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getCaudalabast() {
        return caudalabast;
    }

    /**
     * Sets the value of the caudalabast property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setCaudalabast(Double value) {
        this.caudalabast = value;
    }

    /**
     * Gets the value of the caudalabrev property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getCaudalabrev() {
        return caudalabrev;
    }

    /**
     * Sets the value of the caudalabrev property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setCaudalabrev(Double value) {
        this.caudalabrev = value;
    }

    /**
     * Gets the value of the caudalconcesionado property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getCaudalconcesionado() {
        return caudalconcesionado;
    }

    /**
     * Sets the value of the caudalconcesionado property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setCaudalconcesionado(Double value) {
        this.caudalconcesionado = value;
    }

    /**
     * Gets the value of the caudalcultivo property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getCaudalcultivo() {
        return caudalcultivo;
    }

    /**
     * Sets the value of the caudalcultivo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setCaudalcultivo(Double value) {
        this.caudalcultivo = value;
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
     * Gets the value of the caudalotros property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getCaudalotros() {
        return caudalotros;
    }

    /**
     * Sets the value of the caudalotros property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setCaudalotros(Double value) {
        this.caudalotros = value;
    }

    /**
     * Gets the value of the caudalpesca property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getCaudalpesca() {
        return caudalpesca;
    }

    /**
     * Sets the value of the caudalpesca property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setCaudalpesca(Double value) {
        this.caudalpesca = value;
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
     * Gets the value of the continuidadservicio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContinuidadservicio() {
        return continuidadservicio;
    }

    /**
     * Sets the value of the continuidadservicio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContinuidadservicio(String value) {
        this.continuidadservicio = value;
    }

    /**
     * Gets the value of the decripcionotros property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDecripcionotros() {
        return decripcionotros;
    }

    /**
     * Sets the value of the decripcionotros property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDecripcionotros(String value) {
        this.decripcionotros = value;
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
     * Gets the value of the deptocorrespondencia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeptocorrespondencia() {
        return deptocorrespondencia;
    }

    /**
     * Sets the value of the deptocorrespondencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeptocorrespondencia(String value) {
        this.deptocorrespondencia = value;
    }

    /**
     * Gets the value of the desarenador property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDesarenador() {
        return desarenador;
    }

    /**
     * Sets the value of the desarenador property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDesarenador(String value) {
        this.desarenador = value;
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
     * Gets the value of the direccioncorrespondencia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDireccioncorrespondencia() {
        return direccioncorrespondencia;
    }

    /**
     * Sets the value of the direccioncorrespondencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDireccioncorrespondencia(String value) {
        this.direccioncorrespondencia = value;
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
     * Gets the value of the eficienciacultivo property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getEficienciacultivo() {
        return eficienciacultivo;
    }

    /**
     * Sets the value of the eficienciacultivo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setEficienciacultivo(Double value) {
        this.eficienciacultivo = value;
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
     * Gets the value of the estadocapacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstadocapacion() {
        return estadocapacion;
    }

    /**
     * Sets the value of the estadocapacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstadocapacion(String value) {
        this.estadocapacion = value;
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
     * Gets the value of the fechaexpedicion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaexpedicion() {
        return fechaexpedicion;
    }

    /**
     * Sets the value of the fechaexpedicion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaexpedicion(String value) {
        this.fechaexpedicion = value;
    }

    /**
     * Gets the value of the fechaexpedresolobra property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaexpedresolobra() {
        return fechaexpedresolobra;
    }

    /**
     * Sets the value of the fechaexpedresolobra property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaexpedresolobra(String value) {
        this.fechaexpedresolobra = value;
    }

    /**
     * Gets the value of the fechaexpresolplanos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaexpresolplanos() {
        return fechaexpresolplanos;
    }

    /**
     * Sets the value of the fechaexpresolplanos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaexpresolplanos(String value) {
        this.fechaexpresolplanos = value;
    }

    /**
     * Gets the value of the fechanotificacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechanotificacion() {
        return fechanotificacion;
    }

    /**
     * Sets the value of the fechanotificacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechanotificacion(String value) {
        this.fechanotificacion = value;
    }

    /**
     * Gets the value of the fechanotificacionplanos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechanotificacionplanos() {
        return fechanotificacionplanos;
    }

    /**
     * Sets the value of the fechanotificacionplanos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechanotificacionplanos(String value) {
        this.fechanotificacionplanos = value;
    }

    /**
     * Gets the value of the fechanotifobra property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechanotifobra() {
        return fechanotifobra;
    }

    /**
     * Sets the value of the fechanotifobra property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechanotifobra(String value) {
        this.fechanotifobra = value;
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
     * Gets the value of the gradoslatcapt property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getGradoslatcapt() {
        return gradoslatcapt;
    }

    /**
     * Sets the value of the gradoslatcapt property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setGradoslatcapt(Integer value) {
        this.gradoslatcapt = value;
    }

    /**
     * Gets the value of the gradoslatpredio property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getGradoslatpredio() {
        return gradoslatpredio;
    }

    /**
     * Sets the value of the gradoslatpredio property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setGradoslatpredio(Integer value) {
        this.gradoslatpredio = value;
    }

    /**
     * Gets the value of the gradosloncapt property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getGradosloncapt() {
        return gradosloncapt;
    }

    /**
     * Sets the value of the gradosloncapt property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setGradosloncapt(Integer value) {
        this.gradosloncapt = value;
    }

    /**
     * Gets the value of the gradoslonpredio property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getGradoslonpredio() {
        return gradoslonpredio;
    }

    /**
     * Sets the value of the gradoslonpredio property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setGradoslonpredio(Integer value) {
        this.gradoslonpredio = value;
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
     * Gets the value of the idpunto property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIdpunto() {
        return idpunto;
    }

    /**
     * Sets the value of the idpunto property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIdpunto(Integer value) {
        this.idpunto = value;
    }

    /**
     * Gets the value of the macromedicion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMacromedicion() {
        return macromedicion;
    }

    /**
     * Sets the value of the macromedicion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMacromedicion(String value) {
        this.macromedicion = value;
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
     * Gets the value of the minutoslatcapt property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMinutoslatcapt() {
        return minutoslatcapt;
    }

    /**
     * Sets the value of the minutoslatcapt property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMinutoslatcapt(Integer value) {
        this.minutoslatcapt = value;
    }

    /**
     * Gets the value of the minutoslatpredio property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMinutoslatpredio() {
        return minutoslatpredio;
    }

    /**
     * Sets the value of the minutoslatpredio property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMinutoslatpredio(Integer value) {
        this.minutoslatpredio = value;
    }

    /**
     * Gets the value of the minutosloncapt property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMinutosloncapt() {
        return minutosloncapt;
    }

    /**
     * Sets the value of the minutosloncapt property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMinutosloncapt(Integer value) {
        this.minutosloncapt = value;
    }

    /**
     * Gets the value of the minutoslonpredio property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMinutoslonpredio() {
        return minutoslonpredio;
    }

    /**
     * Sets the value of the minutoslonpredio property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMinutoslonpredio(Integer value) {
        this.minutoslonpredio = value;
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
     * Gets the value of the municipiocorrespondencia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMunicipiocorrespondencia() {
        return municipiocorrespondencia;
    }

    /**
     * Sets the value of the municipiocorrespondencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMunicipiocorrespondencia(String value) {
        this.municipiocorrespondencia = value;
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
     * Gets the value of the numanimalesabrev property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumanimalesabrev() {
        return numanimalesabrev;
    }

    /**
     * Sets the value of the numanimalesabrev property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumanimalesabrev(Integer value) {
        this.numanimalesabrev = value;
    }

    /**
     * Gets the value of the numanimalespesca property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumanimalespesca() {
        return numanimalespesca;
    }

    /**
     * Sets the value of the numanimalespesca property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumanimalespesca(Integer value) {
        this.numanimalespesca = value;
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
     * Gets the value of the numpersonaspermanentes property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumpersonaspermanentes() {
        return numpersonaspermanentes;
    }

    /**
     * Sets the value of the numpersonaspermanentes property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumpersonaspermanentes(Integer value) {
        this.numpersonaspermanentes = value;
    }

    /**
     * Gets the value of the numpersonastransitorias property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumpersonastransitorias() {
        return numpersonastransitorias;
    }

    /**
     * Sets the value of the numpersonastransitorias property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumpersonastransitorias(Integer value) {
        this.numpersonastransitorias = value;
    }

    /**
     * Gets the value of the numresolcaudal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumresolcaudal() {
        return numresolcaudal;
    }

    /**
     * Sets the value of the numresolcaudal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumresolcaudal(String value) {
        this.numresolcaudal = value;
    }

    /**
     * Gets the value of the numresolplanos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumresolplanos() {
        return numresolplanos;
    }

    /**
     * Sets the value of the numresolplanos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumresolplanos(String value) {
        this.numresolplanos = value;
    }

    /**
     * Gets the value of the numresolucionobra property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumresolucionobra() {
        return numresolucionobra;
    }

    /**
     * Sets the value of the numresolucionobra property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumresolucionobra(String value) {
        this.numresolucionobra = value;
    }

    /**
     * Gets the value of the obscaptacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObscaptacion() {
        return obscaptacion;
    }

    /**
     * Sets the value of the obscaptacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObscaptacion(String value) {
        this.obscaptacion = value;
    }

    /**
     * Gets the value of the ofertadisponible property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getOfertadisponible() {
        return ofertadisponible;
    }

    /**
     * Sets the value of the ofertadisponible property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setOfertadisponible(Double value) {
        this.ofertadisponible = value;
    }

    /**
     * Gets the value of the ofertatotal property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getOfertatotal() {
        return ofertatotal;
    }

    /**
     * Sets the value of the ofertatotal property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setOfertatotal(Double value) {
        this.ofertatotal = value;
    }

    /**
     * Gets the value of the produccion property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getProduccion() {
        return produccion;
    }

    /**
     * Sets the value of the produccion property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setProduccion(Double value) {
        this.produccion = value;
    }

    /**
     * Gets the value of the provinciahidro property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvinciahidro() {
        return provinciahidro;
    }

    /**
     * Sets the value of the provinciahidro property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvinciahidro(String value) {
        this.provinciahidro = value;
    }

    /**
     * Gets the value of the ptap property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPtap() {
        return ptap;
    }

    /**
     * Sets the value of the ptap property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPtap(String value) {
        this.ptap = value;
    }

    /**
     * Gets the value of the reddistribucion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReddistribucion() {
        return reddistribucion;
    }

    /**
     * Sets the value of the reddistribucion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReddistribucion(String value) {
        this.reddistribucion = value;
    }

    /**
     * Gets the value of the segundoslatcapt property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getSegundoslatcapt() {
        return segundoslatcapt;
    }

    /**
     * Sets the value of the segundoslatcapt property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setSegundoslatcapt(Double value) {
        this.segundoslatcapt = value;
    }

    /**
     * Gets the value of the segundoslatpredio property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getSegundoslatpredio() {
        return segundoslatpredio;
    }

    /**
     * Sets the value of the segundoslatpredio property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setSegundoslatpredio(Double value) {
        this.segundoslatpredio = value;
    }

    /**
     * Gets the value of the segundosloncapt property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getSegundosloncapt() {
        return segundosloncapt;
    }

    /**
     * Sets the value of the segundosloncapt property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setSegundosloncapt(Double value) {
        this.segundosloncapt = value;
    }

    /**
     * Gets the value of the segundoslonpredio property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getSegundoslonpredio() {
        return segundoslonpredio;
    }

    /**
     * Sets the value of the segundoslonpredio property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setSegundoslonpredio(Double value) {
        this.segundoslonpredio = value;
    }

    /**
     * Gets the value of the sistemarefcapt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSistemarefcapt() {
        return sistemarefcapt;
    }

    /**
     * Sets the value of the sistemarefcapt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSistemarefcapt(String value) {
        this.sistemarefcapt = value;
    }

    /**
     * Gets the value of the sistemarefpredio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSistemarefpredio() {
        return sistemarefpredio;
    }

    /**
     * Sets the value of the sistemarefpredio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSistemarefpredio(String value) {
        this.sistemarefpredio = value;
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
     * Gets the value of the tanque property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTanque() {
        return tanque;
    }

    /**
     * Sets the value of the tanque property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTanque(String value) {
        this.tanque = value;
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
     * Gets the value of the tieneservidumbre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTieneservidumbre() {
        return tieneservidumbre;
    }

    /**
     * Sets the value of the tieneservidumbre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTieneservidumbre(String value) {
        this.tieneservidumbre = value;
    }

    /**
     * Gets the value of the tipoanimalabrev property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoanimalabrev() {
        return tipoanimalabrev;
    }

    /**
     * Sets the value of the tipoanimalabrev property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoanimalabrev(String value) {
        this.tipoanimalabrev = value;
    }

    /**
     * Gets the value of the tipoanimalpesca property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoanimalpesca() {
        return tipoanimalpesca;
    }

    /**
     * Sets the value of the tipoanimalpesca property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoanimalpesca(String value) {
        this.tipoanimalpesca = value;
    }

    /**
     * Gets the value of the tipocaptacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipocaptacion() {
        return tipocaptacion;
    }

    /**
     * Sets the value of the tipocaptacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipocaptacion(String value) {
        this.tipocaptacion = value;
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
     * Gets the value of the tipocultivo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipocultivo() {
        return tipocultivo;
    }

    /**
     * Sets the value of the tipocultivo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipocultivo(String value) {
        this.tipocultivo = value;
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
     * Gets the value of the tipofuentecap property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipofuentecap() {
        return tipofuentecap;
    }

    /**
     * Sets the value of the tipofuentecap property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipofuentecap(String value) {
        this.tipofuentecap = value;
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
     * Gets the value of the tipousootros property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipousootros() {
        return tipousootros;
    }

    /**
     * Sets the value of the tipousootros property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipousootros(String value) {
        this.tipousootros = value;
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
     * Gets the value of the unidadhidro property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnidadhidro() {
        return unidadhidro;
    }

    /**
     * Sets the value of the unidadhidro property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnidadhidro(String value) {
        this.unidadhidro = value;
    }

    /**
     * Gets the value of the vigenciadesde property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVigenciadesde() {
        return vigenciadesde;
    }

    /**
     * Sets the value of the vigenciadesde property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVigenciadesde(String value) {
        this.vigenciadesde = value;
    }

    /**
     * Gets the value of the vigenciahasta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVigenciahasta() {
        return vigenciahasta;
    }

    /**
     * Sets the value of the vigenciahasta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVigenciahasta(String value) {
        this.vigenciahasta = value;
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
