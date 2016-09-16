
package co.gov.corpocaldas.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="getAllUsuariosNaturalConcesionResult" type="{WebServiceCAR}ArrayOfRegUserNaturalConcesion" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "getAllUsuariosNaturalConcesionResult"
})
@XmlRootElement(name = "getAllUsuariosNaturalConcesionResponse")
public class GetAllUsuariosNaturalConcesionResponse {

    protected ArrayOfRegUserNaturalConcesion getAllUsuariosNaturalConcesionResult;

    /**
     * Gets the value of the getAllUsuariosNaturalConcesionResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRegUserNaturalConcesion }
     *     
     */
    public ArrayOfRegUserNaturalConcesion getGetAllUsuariosNaturalConcesionResult() {
        return getAllUsuariosNaturalConcesionResult;
    }

    /**
     * Sets the value of the getAllUsuariosNaturalConcesionResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRegUserNaturalConcesion }
     *     
     */
    public void setGetAllUsuariosNaturalConcesionResult(ArrayOfRegUserNaturalConcesion value) {
        this.getAllUsuariosNaturalConcesionResult = value;
    }

}
