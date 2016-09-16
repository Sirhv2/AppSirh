
package co.gov.corpocaldas.generated;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfFntvFuentesTramos complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfFntvFuentesTramos">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FntvFuentesTramos" type="{WebServiceCAR}FntvFuentesTramos" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfFntvFuentesTramos", propOrder = {
    "fntvFuentesTramos"
})
public class ArrayOfFntvFuentesTramos {

    @XmlElement(name = "FntvFuentesTramos", nillable = true)
    protected List<FntvFuentesTramos> fntvFuentesTramos;

    /**
     * Gets the value of the fntvFuentesTramos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fntvFuentesTramos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFntvFuentesTramos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FntvFuentesTramos }
     * 
     * 
     */
    public List<FntvFuentesTramos> getFntvFuentesTramos() {
        if (fntvFuentesTramos == null) {
            fntvFuentesTramos = new ArrayList<FntvFuentesTramos>();
        }
        return this.fntvFuentesTramos;
    }

}
