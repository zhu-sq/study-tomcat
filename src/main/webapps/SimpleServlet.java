import s01.catalina.Servlet;
import s01.http.HttpRequest;
import s01.http.HttpResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

public class SimpleServlet implements Servlet {

    @Override
    public void service(HttpRequest request, HttpResponse response)throws IOException  {
        String method = request.getMethod();
        if(method.equals("GET")){
            doGet(request,response);
        }else if(method.equals("POST")){
            doPost(request,response);
        }
    }

    public void doGet(HttpRequest request, HttpResponse response)throws IOException {

        System.out.println("send start");

        String str = "<html>\n" +
                "<head>\n" +
                "<title>Modern Servlet</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h2>Headers</h2>\n";

        str += "<br><h2>Method</h2>\n" +
                "<br>" + request.getMethod() +
                "\n<br><h2>Parameters</h2>\n";

        Map<String,String> map = request.getParams();
        Iterator<Map.Entry<String, String>> entries = map.entrySet().iterator();

        while (entries.hasNext()){
            Map.Entry<String, String> entry = entries.next();
            str+="<br>" + entry.getKey() + " : " + entry.getValue();
        }

        str+="\n</body>\n"+
                "</html>\n";


        OutputStream out = response.getOutputStream();

        String head = "HTTP/1.1 200 OK\r\n" +
                "Content-Type: text/html\r\n" +
                "Content-Length: "+str.length()+"\r\n" +
                "\r\n";
        out.write(head.getBytes());
        out.write(str.getBytes());
        out.flush();
        System.out.println("send end");
    }

    public void doPost(HttpRequest request, HttpResponse response){

    }


}
