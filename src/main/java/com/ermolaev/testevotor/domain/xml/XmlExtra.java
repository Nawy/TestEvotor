package com.ermolaev.testevotor.domain.xml;

import javax.xml.bind.annotation.*;

/**
 * @author Ivan Ermolaev
 * @date 12:13 09-11-2016.
 */
@XmlRootElement(name = "extra")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlExtra {

    @XmlAttribute
    private String name;

    @XmlValue
    private String value;

    public XmlExtra() {}

    public XmlExtra(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        XmlExtra xmlExtra = (XmlExtra) o;

        if (this.name != null ? !this.name.equals(xmlExtra.name) : xmlExtra.name != null) return false;
        return this.value != null ? this.value.equals(xmlExtra.value) : xmlExtra.value == null;
    }
}
