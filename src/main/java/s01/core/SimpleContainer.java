package s01.core;

import s01.catalina.Container;
import s01.catalina.Servlet;
import s01.http.HttpRequest;
import s01.http.HttpResponse;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

import static common.Conston.WEB_ROOT;

public class SimpleContainer implements Container {

    @Override
    public void invoke(HttpRequest request, HttpResponse response) throws IOException {

        String uri = request.getUri();

       // String servletName = uri.substring(uri.indexOf("/") + 1,uri.lastIndexOf("/"));

        URLClassLoader loader = null;
        try {
            URL[] urls = new URL[1];
            URLStreamHandler streamHandler = null;
            File classPath = new File(WEB_ROOT);
            String repository = (new URL("file", null, classPath.getCanonicalPath() + File.separator)).toString();
            urls[0] = new URL(null, repository, streamHandler);
            loader = new URLClassLoader(urls);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        Class myClass = null;
        try {
            myClass = loader.loadClass("SimpleServlet");
        } catch (ClassNotFoundException e) {
            System.out.println(e.toString());
        }

        Servlet servlet = null;

        try {
            servlet = (Servlet) myClass.newInstance();
            servlet.service(request,response);
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
        catch (Throwable e) {
            System.out.println(e.toString());
        }
    }
}
