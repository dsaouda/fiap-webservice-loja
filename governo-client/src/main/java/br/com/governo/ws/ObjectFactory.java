
package br.com.governo.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the br.com.governo.ws package. 
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

    private final static QName _EmitirNotaFiscalResponse_QNAME = new QName("http://ws.governo.com.br/", "emitirNotaFiscalResponse");
    private final static QName _ListarNotaFiscal_QNAME = new QName("http://ws.governo.com.br/", "listarNotaFiscal");
    private final static QName _EmitirNotaFiscal_QNAME = new QName("http://ws.governo.com.br/", "emitirNotaFiscal");
    private final static QName _Exception_QNAME = new QName("http://ws.governo.com.br/", "Exception");
    private final static QName _ListarImpostos_QNAME = new QName("http://ws.governo.com.br/", "listarImpostos");
    private final static QName _ListarNotaFiscalResponse_QNAME = new QName("http://ws.governo.com.br/", "listarNotaFiscalResponse");
    private final static QName _ListarImpostosResponse_QNAME = new QName("http://ws.governo.com.br/", "listarImpostosResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: br.com.governo.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ListarNotaFiscalResponse }
     * 
     */
    public ListarNotaFiscalResponse createListarNotaFiscalResponse() {
        return new ListarNotaFiscalResponse();
    }

    /**
     * Create an instance of {@link ListarImpostosResponse }
     * 
     */
    public ListarImpostosResponse createListarImpostosResponse() {
        return new ListarImpostosResponse();
    }

    /**
     * Create an instance of {@link EmitirNotaFiscalResponse }
     * 
     */
    public EmitirNotaFiscalResponse createEmitirNotaFiscalResponse() {
        return new EmitirNotaFiscalResponse();
    }

    /**
     * Create an instance of {@link ListarNotaFiscal }
     * 
     */
    public ListarNotaFiscal createListarNotaFiscal() {
        return new ListarNotaFiscal();
    }

    /**
     * Create an instance of {@link EmitirNotaFiscal }
     * 
     */
    public EmitirNotaFiscal createEmitirNotaFiscal() {
        return new EmitirNotaFiscal();
    }

    /**
     * Create an instance of {@link Exception }
     * 
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link ListarImpostos }
     * 
     */
    public ListarImpostos createListarImpostos() {
        return new ListarImpostos();
    }

    /**
     * Create an instance of {@link Imposto }
     * 
     */
    public Imposto createImposto() {
        return new Imposto();
    }

    /**
     * Create an instance of {@link ArrayList }
     * 
     */
    public ArrayList createArrayList() {
        return new ArrayList();
    }

    /**
     * Create an instance of {@link NotaFiscal }
     * 
     */
    public NotaFiscal createNotaFiscal() {
        return new NotaFiscal();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EmitirNotaFiscalResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.governo.com.br/", name = "emitirNotaFiscalResponse")
    public JAXBElement<EmitirNotaFiscalResponse> createEmitirNotaFiscalResponse(EmitirNotaFiscalResponse value) {
        return new JAXBElement<EmitirNotaFiscalResponse>(_EmitirNotaFiscalResponse_QNAME, EmitirNotaFiscalResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarNotaFiscal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.governo.com.br/", name = "listarNotaFiscal")
    public JAXBElement<ListarNotaFiscal> createListarNotaFiscal(ListarNotaFiscal value) {
        return new JAXBElement<ListarNotaFiscal>(_ListarNotaFiscal_QNAME, ListarNotaFiscal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EmitirNotaFiscal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.governo.com.br/", name = "emitirNotaFiscal")
    public JAXBElement<EmitirNotaFiscal> createEmitirNotaFiscal(EmitirNotaFiscal value) {
        return new JAXBElement<EmitirNotaFiscal>(_EmitirNotaFiscal_QNAME, EmitirNotaFiscal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.governo.com.br/", name = "Exception")
    public JAXBElement<Exception> createException(Exception value) {
        return new JAXBElement<Exception>(_Exception_QNAME, Exception.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarImpostos }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.governo.com.br/", name = "listarImpostos")
    public JAXBElement<ListarImpostos> createListarImpostos(ListarImpostos value) {
        return new JAXBElement<ListarImpostos>(_ListarImpostos_QNAME, ListarImpostos.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarNotaFiscalResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.governo.com.br/", name = "listarNotaFiscalResponse")
    public JAXBElement<ListarNotaFiscalResponse> createListarNotaFiscalResponse(ListarNotaFiscalResponse value) {
        return new JAXBElement<ListarNotaFiscalResponse>(_ListarNotaFiscalResponse_QNAME, ListarNotaFiscalResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarImpostosResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.governo.com.br/", name = "listarImpostosResponse")
    public JAXBElement<ListarImpostosResponse> createListarImpostosResponse(ListarImpostosResponse value) {
        return new JAXBElement<ListarImpostosResponse>(_ListarImpostosResponse_QNAME, ListarImpostosResponse.class, null, value);
    }

}
