package s01.catalina;

import s01.http.HttpRequest;
import s01.http.HttpResponse;

public interface Processor {

    void setRequest(HttpRequest request);

    HttpRequest getRequest();

    void setResponse(HttpResponse response);

    HttpResponse getResponse();
}
