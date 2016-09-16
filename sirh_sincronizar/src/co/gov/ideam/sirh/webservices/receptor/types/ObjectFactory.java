
package co.gov.ideam.sirh.webservices.receptor.types;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the co.gov.ideam.sirh.webservices.receptor.types package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _RecibirEvento_QNAME = new QName("http://sirh.ideam.gov.co/", "recibirEvento");
    private final static QName _RecibirEventoResponse_QNAME = new QName("http://sirh.ideam.gov.co/", "recibirEventoResponse");
    private final static QName _IdeamException_QNAME = new QName("http://sirh.ideam.gov.co/", "IdeamException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: co.gov.ideam.sirh.webservices.receptor.types
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EventoEntrada.Data }
     * 
     */
    public EventoEntrada.Data createEventoEntradaData() {
        return new EventoEntrada.Data();
    }

    /**
     * Create an instance of {@link RecibirEventoResponse }
     * 
     */
    public RecibirEventoResponse createRecibirEventoResponse() {
        return new RecibirEventoResponse();
    }

    /**
     * Create an instance of {@link EventoEntrada }
     * 
     */
    public EventoEntrada createEventoEntrada() {
        return new EventoEntrada();
    }

    /**
     * Create an instance of {@link RecibirEvento }
     * 
     */
    public RecibirEvento createRecibirEvento() {
        return new RecibirEvento();
    }

    /**
     * Create an instance of {@link EventoEntrada.Data.Entry }
     * 
     */
    public EventoEntrada.Data.Entry createEventoEntradaDataEntry() {
        return new EventoEntrada.Data.Entry();
    }

    /**
     * Create an instance of {@link IdeamException }
     * 
     */
    public IdeamException createIdeamException() {
        return new IdeamException();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RecibirEvento }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sirh.ideam.gov.co/", name = "recibirEvento")
    public JAXBElement<RecibirEvento> createRecibirEvento(RecibirEvento value) {
        return new JAXBElement<RecibirEvento>(_RecibirEvento_QNAME, RecibirEvento.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RecibirEventoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sirh.ideam.gov.co/", name = "recibirEventoResponse")
    public JAXBElement<RecibirEventoResponse> createRecibirEventoResponse(RecibirEventoResponse value) {
        return new JAXBElement<RecibirEventoResponse>(_RecibirEventoResponse_QNAME, RecibirEventoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IdeamException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sirh.ideam.gov.co/", name = "IdeamException")
    public JAXBElement<IdeamException> createIdeamException(IdeamException value) {
        return new JAXBElement<IdeamException>(_IdeamException_QNAME, IdeamException.class, null, value);
    }

}
