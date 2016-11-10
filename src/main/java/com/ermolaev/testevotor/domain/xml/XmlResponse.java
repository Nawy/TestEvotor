package com.ermolaev.testevotor.domain.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivan Ermolaev
 * @date 12:23 09-11-2016.
 */
@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlResponse {

    @XmlElement(name = "result-code")
    private int resultCode;

    @XmlElement(name = "extra")
    private List<XmlExtra> extras = new ArrayList<>();

    public XmlResponse() {}

    public int getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public List<XmlExtra> getExtras() {
        return this.extras;
    }

    public void setExtras(List<XmlExtra> extras) {
        this.extras = extras;
    }
}
