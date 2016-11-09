package com.ermolaev.testevotor.domain;

/**
 * @author Ivan Ermolaev
 * @date 14:05 09-11-2016.
 */
public enum RequestType {

    CREATE_AGT("CREATE-AGT"),
    GET_BALANCE("GET-BALANCE");

    RequestType(String code) {
        this.code = code;
    }

    public String code;

    public String getCode() {
        return this.code;
    }
}
