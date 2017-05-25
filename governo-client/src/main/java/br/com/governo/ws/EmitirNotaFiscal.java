
package br.com.governo.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de emitirNotaFiscal complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="emitirNotaFiscal">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="documentoReceptor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="valorNota" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "emitirNotaFiscal", propOrder = {
    "documentoReceptor",
    "valorNota"
})
public class EmitirNotaFiscal {

    protected String documentoReceptor;
    protected Double valorNota;

    /**
     * Obtém o valor da propriedade documentoReceptor.
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
     * Obtém o valor da propriedade valorNota.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getValorNota() {
        return valorNota;
    }

    /**
     * Define o valor da propriedade valorNota.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setValorNota(Double value) {
        this.valorNota = value;
    }

}
