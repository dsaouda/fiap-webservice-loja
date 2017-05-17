
package br.com.fiap.fornecedor.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the br.com.fiap.fornecedor.ws package. 
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

    private final static QName _ListarProdutos_QNAME = new QName("http://ws.fornecedor.fiap.com.br/", "listarProdutos");
    private final static QName _EfetuarPedidoResponse_QNAME = new QName("http://ws.fornecedor.fiap.com.br/", "efetuarPedidoResponse");
    private final static QName _ListarProdutosResponse_QNAME = new QName("http://ws.fornecedor.fiap.com.br/", "listarProdutosResponse");
    private final static QName _FornecedorException_QNAME = new QName("http://ws.fornecedor.fiap.com.br/", "FornecedorException");
    private final static QName _EfetuarPedido_QNAME = new QName("http://ws.fornecedor.fiap.com.br/", "efetuarPedido");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: br.com.fiap.fornecedor.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EfetuarPedido }
     * 
     */
    public EfetuarPedido createEfetuarPedido() {
        return new EfetuarPedido();
    }

    /**
     * Create an instance of {@link EfetuarPedidoResponse }
     * 
     */
    public EfetuarPedidoResponse createEfetuarPedidoResponse() {
        return new EfetuarPedidoResponse();
    }

    /**
     * Create an instance of {@link ListarProdutos }
     * 
     */
    public ListarProdutos createListarProdutos() {
        return new ListarProdutos();
    }

    /**
     * Create an instance of {@link ListarProdutosResponse }
     * 
     */
    public ListarProdutosResponse createListarProdutosResponse() {
        return new ListarProdutosResponse();
    }

    /**
     * Create an instance of {@link FornecedorException }
     * 
     */
    public FornecedorException createFornecedorException() {
        return new FornecedorException();
    }

    /**
     * Create an instance of {@link PedidoDTO }
     * 
     */
    public PedidoDTO createPedidoDTO() {
        return new PedidoDTO();
    }

    /**
     * Create an instance of {@link ProdutoDTO }
     * 
     */
    public ProdutoDTO createProdutoDTO() {
        return new ProdutoDTO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarProdutos }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.fornecedor.fiap.com.br/", name = "listarProdutos")
    public JAXBElement<ListarProdutos> createListarProdutos(ListarProdutos value) {
        return new JAXBElement<ListarProdutos>(_ListarProdutos_QNAME, ListarProdutos.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EfetuarPedidoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.fornecedor.fiap.com.br/", name = "efetuarPedidoResponse")
    public JAXBElement<EfetuarPedidoResponse> createEfetuarPedidoResponse(EfetuarPedidoResponse value) {
        return new JAXBElement<EfetuarPedidoResponse>(_EfetuarPedidoResponse_QNAME, EfetuarPedidoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarProdutosResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.fornecedor.fiap.com.br/", name = "listarProdutosResponse")
    public JAXBElement<ListarProdutosResponse> createListarProdutosResponse(ListarProdutosResponse value) {
        return new JAXBElement<ListarProdutosResponse>(_ListarProdutosResponse_QNAME, ListarProdutosResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FornecedorException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.fornecedor.fiap.com.br/", name = "FornecedorException")
    public JAXBElement<FornecedorException> createFornecedorException(FornecedorException value) {
        return new JAXBElement<FornecedorException>(_FornecedorException_QNAME, FornecedorException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EfetuarPedido }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.fornecedor.fiap.com.br/", name = "efetuarPedido")
    public JAXBElement<EfetuarPedido> createEfetuarPedido(EfetuarPedido value) {
        return new JAXBElement<EfetuarPedido>(_EfetuarPedido_QNAME, EfetuarPedido.class, null, value);
    }

}
