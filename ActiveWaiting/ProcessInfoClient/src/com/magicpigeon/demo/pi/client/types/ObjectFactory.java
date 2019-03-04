
package com.magicpigeon.demo.pi.client.types;

import java.math.BigDecimal;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the com.magicpigeon.demo.pi.client.types package.
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

    private final static QName _CustomProcessInfoCollection_QNAME =
        new QName("http://xmlns.oracle.com/pcbpel/adapter/db/top/dbDemo", "CustomProcessInfoCollection");
    private final static QName _DbDemoSelectCustomIdInputParameters_QNAME =
        new QName("http://xmlns.oracle.com/pcbpel/adapter/db/top/dbDemo", "dbDemoSelect_customIdInputParameters");
    private final static QName _CustomProcessInfoPrimaryKey_QNAME =
        new QName("http://xmlns.oracle.com/pcbpel/adapter/db/top/dbDemo", "CustomProcessInfoPrimaryKey");
    private final static QName _CustomProcessInfoCustomId_QNAME =
        new QName("http://xmlns.oracle.com/pcbpel/adapter/db/top/dbDemo", "customId");
    private final static QName _CustomProcessInfoInstanceNumber_QNAME =
        new QName("http://xmlns.oracle.com/pcbpel/adapter/db/top/dbDemo", "instanceNumber");
    private final static QName _CustomProcessInfoCompositeName_QNAME =
        new QName("http://xmlns.oracle.com/pcbpel/adapter/db/top/dbDemo", "compositeName");
    private final static QName _CustomProcessInfoCompositeDn_QNAME =
        new QName("http://xmlns.oracle.com/pcbpel/adapter/db/top/dbDemo", "compositeDn");
    private final static QName _CustomProcessInfoCompositeLabel_QNAME =
        new QName("http://xmlns.oracle.com/pcbpel/adapter/db/top/dbDemo", "compositeLabel");
    private final static QName _CustomProcessInfoCompositeRevision_QNAME =
        new QName("http://xmlns.oracle.com/pcbpel/adapter/db/top/dbDemo", "compositeRevision");
    private final static QName _CustomProcessInfoCompositeInstanceId_QNAME =
        new QName("http://xmlns.oracle.com/pcbpel/adapter/db/top/dbDemo", "compositeInstanceId");
    private final static QName _CustomProcessInfoProcessDn_QNAME =
        new QName("http://xmlns.oracle.com/pcbpel/adapter/db/top/dbDemo", "processDn");
    private final static QName _CustomProcessInfoActivityName_QNAME =
        new QName("http://xmlns.oracle.com/pcbpel/adapter/db/top/dbDemo", "activityName");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.magicpigeon.demo.pi.client.types
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CustomProcessInfoCollection }
     *
     */
    public CustomProcessInfoCollection createCustomProcessInfoCollection() {
        return new CustomProcessInfoCollection();
    }

    /**
     * Create an instance of {@link DbDemoSelectCustomId }
     *
     */
    public DbDemoSelectCustomId createDbDemoSelectCustomId() {
        return new DbDemoSelectCustomId();
    }

    /**
     * Create an instance of {@link CustomProcessInfoPrimaryKey }
     *
     */
    public CustomProcessInfoPrimaryKey createCustomProcessInfoPrimaryKey() {
        return new CustomProcessInfoPrimaryKey();
    }

    /**
     * Create an instance of {@link CustomProcessInfo }
     *
     */
    public CustomProcessInfo createCustomProcessInfo() {
        return new CustomProcessInfo();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CustomProcessInfoCollection }{@code >}
     *
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CustomProcessInfoCollection }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/pcbpel/adapter/db/top/dbDemo",
                    name = "CustomProcessInfoCollection")
    public JAXBElement<CustomProcessInfoCollection> createCustomProcessInfoCollection(CustomProcessInfoCollection value) {
        return new JAXBElement<CustomProcessInfoCollection>(_CustomProcessInfoCollection_QNAME,
                                                            CustomProcessInfoCollection.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DbDemoSelectCustomId }{@code >}
     *
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DbDemoSelectCustomId }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/pcbpel/adapter/db/top/dbDemo",
                    name = "dbDemoSelect_customIdInputParameters")
    public JAXBElement<DbDemoSelectCustomId> createDbDemoSelectCustomIdInputParameters(DbDemoSelectCustomId value) {
        return new JAXBElement<DbDemoSelectCustomId>(_DbDemoSelectCustomIdInputParameters_QNAME,
                                                     DbDemoSelectCustomId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CustomProcessInfoPrimaryKey }{@code >}
     *
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CustomProcessInfoPrimaryKey }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/pcbpel/adapter/db/top/dbDemo",
                    name = "CustomProcessInfoPrimaryKey")
    public JAXBElement<CustomProcessInfoPrimaryKey> createCustomProcessInfoPrimaryKey(CustomProcessInfoPrimaryKey value) {
        return new JAXBElement<CustomProcessInfoPrimaryKey>(_CustomProcessInfoPrimaryKey_QNAME,
                                                            CustomProcessInfoPrimaryKey.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/pcbpel/adapter/db/top/dbDemo", name = "customId",
                    scope = CustomProcessInfo.class)
    public JAXBElement<String> createCustomProcessInfoCustomId(String value) {
        return new JAXBElement<String>(_CustomProcessInfoCustomId_QNAME, String.class, CustomProcessInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/pcbpel/adapter/db/top/dbDemo", name = "instanceNumber",
                    scope = CustomProcessInfo.class)
    public JAXBElement<BigDecimal> createCustomProcessInfoInstanceNumber(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_CustomProcessInfoInstanceNumber_QNAME, BigDecimal.class,
                                           CustomProcessInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/pcbpel/adapter/db/top/dbDemo", name = "compositeName",
                    scope = CustomProcessInfo.class)
    public JAXBElement<String> createCustomProcessInfoCompositeName(String value) {
        return new JAXBElement<String>(_CustomProcessInfoCompositeName_QNAME, String.class, CustomProcessInfo.class,
                                       value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/pcbpel/adapter/db/top/dbDemo", name = "compositeDn",
                    scope = CustomProcessInfo.class)
    public JAXBElement<String> createCustomProcessInfoCompositeDn(String value) {
        return new JAXBElement<String>(_CustomProcessInfoCompositeDn_QNAME, String.class, CustomProcessInfo.class,
                                       value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/pcbpel/adapter/db/top/dbDemo", name = "compositeLabel",
                    scope = CustomProcessInfo.class)
    public JAXBElement<String> createCustomProcessInfoCompositeLabel(String value) {
        return new JAXBElement<String>(_CustomProcessInfoCompositeLabel_QNAME, String.class, CustomProcessInfo.class,
                                       value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/pcbpel/adapter/db/top/dbDemo", name = "compositeRevision",
                    scope = CustomProcessInfo.class)
    public JAXBElement<String> createCustomProcessInfoCompositeRevision(String value) {
        return new JAXBElement<String>(_CustomProcessInfoCompositeRevision_QNAME, String.class, CustomProcessInfo.class,
                                       value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/pcbpel/adapter/db/top/dbDemo", name = "compositeInstanceId",
                    scope = CustomProcessInfo.class)
    public JAXBElement<String> createCustomProcessInfoCompositeInstanceId(String value) {
        return new JAXBElement<String>(_CustomProcessInfoCompositeInstanceId_QNAME, String.class,
                                       CustomProcessInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/pcbpel/adapter/db/top/dbDemo", name = "processDn",
                    scope = CustomProcessInfo.class)
    public JAXBElement<String> createCustomProcessInfoProcessDn(String value) {
        return new JAXBElement<String>(_CustomProcessInfoProcessDn_QNAME, String.class, CustomProcessInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/pcbpel/adapter/db/top/dbDemo", name = "activityName",
                    scope = CustomProcessInfo.class)
    public JAXBElement<String> createCustomProcessInfoActivityName(String value) {
        return new JAXBElement<String>(_CustomProcessInfoActivityName_QNAME, String.class, CustomProcessInfo.class,
                                       value);
    }

}
