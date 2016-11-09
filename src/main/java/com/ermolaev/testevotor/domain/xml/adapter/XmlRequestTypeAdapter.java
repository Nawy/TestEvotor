package com.ermolaev.testevotor.domain.xml.adapter;

import com.ermolaev.testevotor.domain.RequestType;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @author Ivan Ermolaev
 * @date 14:39 09-11-2016.
 */
public class XmlRequestTypeAdapter extends XmlAdapter<String, RequestType> {

    protected XmlRequestTypeAdapter() {
        super();
    }

    @Override
    public RequestType unmarshal(String value) throws Exception {
        if(RequestType.CREATE_AGT.getCode().equals(value)) {
            return RequestType.CREATE_AGT;
        }
        else if(RequestType.GET_BALANCE.getCode().equals(value)) {
            return RequestType.GET_BALANCE;
        }
        throw new RuntimeException("Internal error");
    }

    @Override
    public String marshal(RequestType value) throws Exception {
        return null;
    }
}
