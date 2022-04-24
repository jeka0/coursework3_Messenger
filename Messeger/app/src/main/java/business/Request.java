package business;

import java.io.Serializable;

public class Request implements Serializable {
    private String request;
    public Request(String request)
    {
        this.request = request;
    }

    public String getRequest() {
        return request;
    }
}
