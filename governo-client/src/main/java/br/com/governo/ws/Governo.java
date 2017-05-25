
package br.com.governo.ws;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "Governo", targetNamespace = "http://ws.governo.com.br/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface Governo {


    /**
     * 
     * @return
     *     returns java.util.List<br.com.governo.ws.NotaFiscal>
     * @throws Exception_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "listarNotaFiscal", targetNamespace = "http://ws.governo.com.br/", className = "br.com.governo.ws.ListarNotaFiscal")
    @ResponseWrapper(localName = "listarNotaFiscalResponse", targetNamespace = "http://ws.governo.com.br/", className = "br.com.governo.ws.ListarNotaFiscalResponse")
    public List<NotaFiscal> listarNotaFiscal()
        throws Exception_Exception
    ;

    /**
     * 
     * @param valorNota
     * @param documentoReceptor
     * @return
     *     returns br.com.governo.ws.NotaFiscal
     * @throws Exception_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "emitirNotaFiscal", targetNamespace = "http://ws.governo.com.br/", className = "br.com.governo.ws.EmitirNotaFiscal")
    @ResponseWrapper(localName = "emitirNotaFiscalResponse", targetNamespace = "http://ws.governo.com.br/", className = "br.com.governo.ws.EmitirNotaFiscalResponse")
    public NotaFiscal emitirNotaFiscal(
        @WebParam(name = "documentoReceptor", targetNamespace = "")
        String documentoReceptor,
        @WebParam(name = "valorNota", targetNamespace = "")
        Double valorNota)
        throws Exception_Exception
    ;

    /**
     * 
     * @return
     *     returns java.util.List<br.com.governo.ws.Imposto>
     * @throws Exception_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "listarImpostos", targetNamespace = "http://ws.governo.com.br/", className = "br.com.governo.ws.ListarImpostos")
    @ResponseWrapper(localName = "listarImpostosResponse", targetNamespace = "http://ws.governo.com.br/", className = "br.com.governo.ws.ListarImpostosResponse")
    public List<Imposto> listarImpostos()
        throws Exception_Exception
    ;

}
