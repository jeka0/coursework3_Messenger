package business;

import java.io.Serializable;

public class Request implements Serializable {
    private String request;
    private Object object;
    public Request(String request)
    {
        this.request = request;
    }
    public Request(String request, Object data){this.request=request;this.object=data;}
    public String getRequest() {
        return request;
    }
    public Object getData(){return object;}
}
