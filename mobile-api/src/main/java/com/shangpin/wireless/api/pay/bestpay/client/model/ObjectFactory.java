
package com.shangpin.wireless.api.pay.bestpay.client.model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the websvc package. 
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

    private final static QName _DispatchCommandResponse_QNAME = new QName("http://websvc/", "dispatchCommandResponse");
    private final static QName _DispatchCommand_QNAME = new QName("http://websvc/", "dispatchCommand");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: websvc
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DispatchCommandResponse }
     * 
     */
    public DispatchCommandResponse createDispatchCommandResponse() {
        return new DispatchCommandResponse();
    }

    /**
     * Create an instance of {@link DispatchCommand }
     * 
     */
    public DispatchCommand createDispatchCommand() {
        return new DispatchCommand();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DispatchCommandResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://websvc/", name = "dispatchCommandResponse")
    public JAXBElement<DispatchCommandResponse> createDispatchCommandResponse(DispatchCommandResponse value) {
        return new JAXBElement<DispatchCommandResponse>(_DispatchCommandResponse_QNAME, DispatchCommandResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DispatchCommand }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://websvc/", name = "dispatchCommand")
    public JAXBElement<DispatchCommand> createDispatchCommand(DispatchCommand value) {
        return new JAXBElement<DispatchCommand>(_DispatchCommand_QNAME, DispatchCommand.class, null, value);
    }

}
