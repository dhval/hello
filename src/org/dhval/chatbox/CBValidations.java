package com.proj.chatbox;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.lang.StringUtils;

public class CBValidations {

    static final int STATUS_SUCCESS = 1;
    static final int STATUS_BAN = 2;
    static final int STATUS_IOE = 3;
    static final int STATUS_BADPROXY = 4;
    static final int STATUS_FAILED = 5;
    private String contains = null;
    private String notContains = null;

    public void setNotContains(String notContains) {
        this.notContains = notContains;
    }

    public void setContains(String contains) {
        this.contains = contains;
    }

    public int isValidGetResponse(int statusCode, StringBuffer data) {
        int status = STATUS_SUCCESS;
        if (statusCode != HttpStatus.SC_OK) {
            status = STATUS_BADPROXY;
        } else if (contains != null &&
                !StringUtils.contains(data.toString(), contains)) {
            status = STATUS_FAILED;
        }  else if (notContains != null &&
                StringUtils.contains(data.toString(), notContains)) {
            status = STATUS_FAILED;
        }
        return status;
    }
}
