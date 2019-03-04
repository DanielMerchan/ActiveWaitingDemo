
package com.magicpigeon.demo.pi.client.types;

import java.math.BigDecimal;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CustomProcessInfo complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="CustomProcessInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="customId" type="{http://xmlns.oracle.com/pcbpel/adapter/db/top/dbDemo}string255" minOccurs="0"/&gt;
 *         &lt;element name="instanceNumber" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="instanceId" type="{http://xmlns.oracle.com/pcbpel/adapter/db/top/dbDemo}string255"/&gt;
 *         &lt;element name="compositeName" type="{http://xmlns.oracle.com/pcbpel/adapter/db/top/dbDemo}string255" minOccurs="0"/&gt;
 *         &lt;element name="compositeDn" type="{http://xmlns.oracle.com/pcbpel/adapter/db/top/dbDemo}string255" minOccurs="0"/&gt;
 *         &lt;element name="compositeLabel" type="{http://xmlns.oracle.com/pcbpel/adapter/db/top/dbDemo}string255" minOccurs="0"/&gt;
 *         &lt;element name="compositeRevision" type="{http://xmlns.oracle.com/pcbpel/adapter/db/top/dbDemo}string255" minOccurs="0"/&gt;
 *         &lt;element name="compositeInstanceId" type="{http://xmlns.oracle.com/pcbpel/adapter/db/top/dbDemo}string255" minOccurs="0"/&gt;
 *         &lt;element name="processDn" type="{http://xmlns.oracle.com/pcbpel/adapter/db/top/dbDemo}string255" minOccurs="0"/&gt;
 *         &lt;element name="activityName" type="{http://xmlns.oracle.com/pcbpel/adapter/db/top/dbDemo}string20" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustomProcessInfo",
         propOrder =
         { "customId", "instanceNumber", "instanceId", "compositeName", "compositeDn", "compositeLabel",
           "compositeRevision", "compositeInstanceId", "processDn", "activityName"
    })
public class CustomProcessInfo {

    @XmlElementRef(name = "customId", namespace = "http://xmlns.oracle.com/pcbpel/adapter/db/top/dbDemo",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> customId;
    @XmlElementRef(name = "instanceNumber", namespace = "http://xmlns.oracle.com/pcbpel/adapter/db/top/dbDemo",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<BigDecimal> instanceNumber;
    @XmlElement(required = true)
    protected String instanceId;
    @XmlElementRef(name = "compositeName", namespace = "http://xmlns.oracle.com/pcbpel/adapter/db/top/dbDemo",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> compositeName;
    @XmlElementRef(name = "compositeDn", namespace = "http://xmlns.oracle.com/pcbpel/adapter/db/top/dbDemo",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> compositeDn;
    @XmlElementRef(name = "compositeLabel", namespace = "http://xmlns.oracle.com/pcbpel/adapter/db/top/dbDemo",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> compositeLabel;
    @XmlElementRef(name = "compositeRevision", namespace = "http://xmlns.oracle.com/pcbpel/adapter/db/top/dbDemo",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> compositeRevision;
    @XmlElementRef(name = "compositeInstanceId", namespace = "http://xmlns.oracle.com/pcbpel/adapter/db/top/dbDemo",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> compositeInstanceId;
    @XmlElementRef(name = "processDn", namespace = "http://xmlns.oracle.com/pcbpel/adapter/db/top/dbDemo",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> processDn;
    @XmlElementRef(name = "activityName", namespace = "http://xmlns.oracle.com/pcbpel/adapter/db/top/dbDemo",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> activityName;

    /**
     * Gets the value of the customId property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getCustomId() {
        return customId;
    }

    /**
     * Sets the value of the customId property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setCustomId(JAXBElement<String> value) {
        this.customId = value;
    }

    /**
     * Gets the value of the instanceNumber property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *
     */
    public JAXBElement<BigDecimal> getInstanceNumber() {
        return instanceNumber;
    }

    /**
     * Sets the value of the instanceNumber property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *
     */
    public void setInstanceNumber(JAXBElement<BigDecimal> value) {
        this.instanceNumber = value;
    }

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

    /**
     * Gets the value of the compositeName property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getCompositeName() {
        return compositeName;
    }

    /**
     * Sets the value of the compositeName property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setCompositeName(JAXBElement<String> value) {
        this.compositeName = value;
    }

    /**
     * Gets the value of the compositeDn property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getCompositeDn() {
        return compositeDn;
    }

    /**
     * Sets the value of the compositeDn property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setCompositeDn(JAXBElement<String> value) {
        this.compositeDn = value;
    }

    /**
     * Gets the value of the compositeLabel property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getCompositeLabel() {
        return compositeLabel;
    }

    /**
     * Sets the value of the compositeLabel property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setCompositeLabel(JAXBElement<String> value) {
        this.compositeLabel = value;
    }

    /**
     * Gets the value of the compositeRevision property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getCompositeRevision() {
        return compositeRevision;
    }

    /**
     * Sets the value of the compositeRevision property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setCompositeRevision(JAXBElement<String> value) {
        this.compositeRevision = value;
    }

    /**
     * Gets the value of the compositeInstanceId property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getCompositeInstanceId() {
        return compositeInstanceId;
    }

    /**
     * Sets the value of the compositeInstanceId property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setCompositeInstanceId(JAXBElement<String> value) {
        this.compositeInstanceId = value;
    }

    /**
     * Gets the value of the processDn property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getProcessDn() {
        return processDn;
    }

    /**
     * Sets the value of the processDn property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setProcessDn(JAXBElement<String> value) {
        this.processDn = value;
    }

    /**
     * Gets the value of the activityName property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getActivityName() {
        return activityName;
    }

    /**
     * Sets the value of the activityName property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setActivityName(JAXBElement<String> value) {
        this.activityName = value;
    }

}
