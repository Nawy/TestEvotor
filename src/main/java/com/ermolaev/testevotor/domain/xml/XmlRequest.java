package com.ermolaev.testevotor.domain.xml;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author Ivan Ermolaev
 * @date 10:22 09-11-2016.
 */
@XmlRootElement(name = "request")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlRequest {

    @XmlElement(name = "request-type")
    private String requestType;

    @XmlElement(name = "extra")
    private List<XmlExtra> extras;

    public XmlRequest() {}

    public String getRequestType() {
        return this.requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public List<XmlExtra> getExtras() {
        return this.extras;
    }

    public void setExtras(List<XmlExtra> extras) {
        this.extras = extras;
    }
}
