
package com.magicpigeon.demo.pi.client.types;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CustomProcessInfoCollection complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="CustomProcessInfoCollection"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CustomProcessInfo" type="{http://xmlns.oracle.com/pcbpel/adapter/db/top/dbDemo}CustomProcessInfo" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustomProcessInfoCollection", propOrder = { "customProcessInfo" })
public class CustomProcessInfoCollection {

    @XmlElement(name = "CustomProcessInfo")
    protected List<CustomProcessInfo> customProcessInfo;

    /**
     * Gets the value of the customProcessInfo property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the customProcessInfo property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCustomProcessInfo().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CustomProcessInfo }
     *
     *
     */
    public List<CustomProcessInfo> getCustomProcessInfo() {
        if (customProcessInfo == null) {
            customProcessInfo = new ArrayList<CustomProcessInfo>();
        }
        return this.customProcessInfo;
    }

}
