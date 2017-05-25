
package br.com.governo.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de notaFiscal complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conte�do esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="notaFiscal">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="documentoEmissor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="documentoReceptor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="valorTotal" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="valorTotalImpostos" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "notaFiscal", propOrder = {
    "documentoEmissor",
    "documentoReceptor",
    "valorTotal",
    "valorTotalImpostos"
})
public class NotaFiscal {

    protected String documentoEmissor;
    protected String documentoReceptor;
    protected double valorTotal;
    protected double valorTotalImpostos;

    /**
     * Obt�m o valor da propriedade documentoEmissor.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocumentoEmissor() {
        return documentoEmissor;
    }

    /**
     * Define o valor da propriedade documentoEmissor.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocumentoEmissor(String value) {
        this.documentoEmissor = value;
    }

    /**
     * Obt�m o valor da propriedade documentoReceptor.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocumentoReceptor() {
        return documentoReceptor;
    }

    /**
     * Define o valor da propriedade documentoReceptor.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocumentoReceptor(String value) {
        this.documentoReceptor = value;
    }

    /**
     * Obt�m o valor da propriedade valorTotal.
     * 
     */
    public double getValorTotal() {
        return valorTotal;
    }

    /**
     * Define o valor da propriedade valorTotal.
     * 
     */
    public void setValorTotal(double value) {
        this.valorTotal = value;
    }

    /**
     * Obt�m o valor da propriedade valorTotalImpostos.
     * 
     */
    public double getValorTotalImpostos() {
        return valorTotalImpostos;
    }

    /**
     * Define o valor da propriedade valorTotalImpostos.
     * 
     */
    public void setValorTotalImpostos(double value) {
        this.valorTotalImpostos = value;
    }
    
    public double getValorTotalComImpostos() {
    	return valorTotalImpostos+valorTotal;
    }

	@Override
	public String toString() {
		return "NotaFiscal [documentoEmissor=" + documentoEmissor + ", documentoReceptor=" + documentoReceptor
				+ ", valorTotal=" + valorTotal + ", valorTotalImpostos=" + valorTotalImpostos + "]";
	}
}
