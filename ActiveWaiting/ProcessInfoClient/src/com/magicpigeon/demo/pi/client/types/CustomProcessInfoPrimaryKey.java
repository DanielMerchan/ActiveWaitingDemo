
package com.magicpigeon.demo.pi.client.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CustomProcessInfoPrimaryKey complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="CustomProcessInfoPrimaryKey"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="instanceId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustomProcessInfoPrimaryKey", propOrder = { "instanceId" })
public class CustomProcessInfoPrimaryKey {

    @XmlElement(required = true)
    protected String instanceId;

    /**
     * Gets the value of the instanceId property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getInstanceId() {
        return instanceId;
    }

    /**
     * Sets the value of the instanceId property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setInstanceId(String value) {
        this.instanceId = value;
    }

}
