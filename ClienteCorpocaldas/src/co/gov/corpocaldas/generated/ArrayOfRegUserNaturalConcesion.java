
package co.gov.corpocaldas.generated;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfRegUserNaturalConcesion complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfRegUserNaturalConcesion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RegUserNaturalConcesion" type="{WebServiceCAR}RegUserNaturalConcesion" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfRegUserNaturalConcesion", propOrder = {
    "regUserNaturalConcesion"
})
public class ArrayOfRegUserNaturalConcesion {

    @XmlElement(name = "RegUserNaturalConcesion", nillable = true)
    protected List<RegUserNaturalConcesion> regUserNaturalConcesion;

    /**
     * Gets the value of the regUserNaturalConcesion property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the regUserNaturalConcesion property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRegUserNaturalConcesion().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RegUserNaturalConcesion }
     * 
     * 
     */
    public List<RegUserNaturalConcesion> getRegUserNaturalConcesion() {
        if (regUserNaturalConcesion == null) {
            regUserNaturalConcesion = new ArrayList<RegUserNaturalConcesion>();
        }
        return this.regUserNaturalConcesion;
    }

}
