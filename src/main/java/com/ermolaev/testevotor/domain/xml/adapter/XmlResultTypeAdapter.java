package com.ermolaev.testevotor.domain.xml.adapter;

import com.ermolaev.testevotor.domain.ResultCode;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @author Ivan Ermolaev
 * @date 14:23 09-11-2016.
 */
public class XmlResultTypeAdapter extends XmlAdapter<Integer, ResultCode> {

    protected XmlResultTypeAdapter() {
        super();
    }

    @Override
    public ResultCode unmarshal(Integer value) throws Exception {

        if(value.equals(ResultCode.OK.getCode())) {
            return ResultCode.OK;
        }
        else if(value.equals(ResultCode.USER_ALREADY_EXIST.getCode())) {
            return ResultCode.USER_ALREADY_EXIST;
        }
        else if(value.equals(ResultCode.INTERNAL_ERROR.getCode())) {
            return ResultCode.INTERNAL_ERROR;
        }
        throw new RuntimeException("Internal error");
    }

    @Override
    public Integer marshal(ResultCode value) throws Exception {
        return value.getCode();
    }
}
