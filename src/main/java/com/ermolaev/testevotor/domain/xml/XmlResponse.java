package com.ermolaev.testevotor.domain.xml;

import com.ermolaev.testevotor.domain.ResultCode;
import com.ermolaev.testevotor.domain.xml.adapter.XmlResultTypeAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;

/**
 * @author Ivan Ermolaev
 * @date 12:23 09-11-2016.
 */
@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlResponse {

    @XmlElement(name = "result-code")
    @XmlJavaTypeAdapter(XmlResultTypeAdapter.class)
    private ResultCode resultCode;

    @XmlElement(name = "extra")
    private List<XmlExtra> extras;

    public XmlResponse() {}

    public ResultCode getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public List<XmlExtra> getExtras() {
        return this.extras;
    }

    public void setExtras(List<XmlExtra> extras) {
        this.extras = extras;
    }
}
