package common;

import java.io.Serializable;

public class RequestMessage implements Serializable {
    private String requestType;

    public RequestMessage(String requestType) {
        this.requestType = requestType;
    }

    public String getRequestType() {
        return requestType;
    }
}