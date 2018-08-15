package s01.catalina;

import s01.http.HttpRequest;
import s01.http.HttpResponse;

import java.io.IOException;

public interface Servlet {
    void service(HttpRequest request,HttpResponse response)throws IOException;
}
