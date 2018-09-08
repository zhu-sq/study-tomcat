import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class ModernServlet extends HttpServlet {

    public void init(ServletConfig config) {
        System.out.println("ModernServlet -- init");
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        String str = "<html>\n" +
                "<head>\n" +
                "<title>Modern Servlet</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h2>Headers</h2>\n";
        Enumeration headers = request.getHeaderNames();
        while (headers.hasMoreElements()) {
            String header = (String) headers.nextElement();
            // out.println("<br>" + header + " : " + request.getHeader(header));
            str += "<br>" + header + " : " + request.getHeader(header);
        }

        str += "<br><h2>Method</h2>\n" +
                "<br>" + request.getMethod() +
                "\n<br><h2>Parameters</h2>\n";

        Enumeration parameters = request.getParameterNames();
        while (parameters.hasMoreElements()) {
            String parameter = (String) parameters.nextElement();
           // out.println("<br>" + parameter + " : " + request.getParameter(parameter));
            str+="<br>" + parameter + " : " + request.getParameter(parameter);
        }

        str+="\n<br><h2>Query String</h2>\n"+
                "<br>" + request.getQueryString()+
                "\n<br><h2>Request URI</h2>\n"+
                "<br>" + request.getQueryString()+
                "\n</body>\n"+
                "</html>\n";


        PrintWriter out = response.getWriter();

        String head = "HTTP/1.1 200 OK\r\n" +
                "Content-Type: text/html\r\n" +
                "Content-Length: "+str.length()+"\r\n" +
                "\r\n";
        out.println(head);
        out.println(str);

    }
}