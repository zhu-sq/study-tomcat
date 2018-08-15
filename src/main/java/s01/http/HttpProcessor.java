package s01.http;

import s01.catalina.Connector;
import s01.catalina.Processor;
import sun.security.pkcs11.wrapper.Constants;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;

public class HttpProcessor implements Processor, Runnable {

    private HttpConnector connector;
    private HttpRequest request;
    private HttpResponse response;
    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;

    private String uri;
    private String method;
    private HashMap<String, String> params;

    private Object synItem = new Object();


    public HttpProcessor(HttpConnector connector) {
        this.connector = connector;
    }

    @Override
    public HttpRequest getRequest() {
        return request;
    }

    @Override
    public void setRequest(HttpRequest request) {
        this.request = request;
    }

    @Override
    public HttpResponse getResponse() {
        return response;
    }

    @Override
    public void setResponse(HttpResponse response) {
        this.response = response;
    }

    @Override
    public void run() {
        synchronized (synItem) {
            try {
                synItem.wait();
                proces(socket);
                connector.recycle(this);
                socket.close();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void assign(Socket socket) {
        synchronized (synItem) {
            this.socket = socket;
            synItem.notifyAll();
        }
    }

    public void init() {
        Thread t = new Thread(this);
        t.start();
    }

    public void proces(Socket socket) throws IOException {
        inputStream = socket.getInputStream();
        outputStream = socket.getOutputStream();
        request = new HttpRequest(inputStream);
        request.parse();
        response = new HttpResponse(outputStream);
        connector.getContainer().invoke(request,response);
    }

}
