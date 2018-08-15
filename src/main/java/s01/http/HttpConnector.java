package s01.http;

import common.Conston;
import s01.catalina.Connector;
import s01.catalina.Container;
import s01.catalina.Processor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Stack;

public class HttpConnector implements Connector, Runnable {

    private Container container;
    private HttpRequest request;
    private HttpResponse response;
    private Stack<HttpProcessor> processors;
    private int maxProcessor = 10;
    private ServerSocket serverSocket;
    private Socket socket;

    @Override
    public Container getContainer() {
        return container;
    }

    @Override
    public void setContainer(Container container) {
        this.container = container;
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
        try {
            while (true) {
                socket = serverSocket.accept();
                HttpProcessor processor = getProcessor();
                if (processor == null) {
                    System.out.println("processor 已满");
                    continue;
                }
                processor.assign(socket);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    public void init() throws IOException {
        serverSocket = new ServerSocket(Conston.PORT);
        processors = new Stack<>();
        for (int i = 0; i < maxProcessor; i++) {
            HttpProcessor processor = new HttpProcessor(this);
            processors.push(processor);
            processor.init();
        }
    }

    public void threadStart() {
        Thread t = new Thread(this);
        t.start();
    }

    synchronized void recycle(HttpProcessor httpProcessor){
       processors.push(httpProcessor);
    }

    synchronized HttpProcessor getProcessor(){

        if(!processors.isEmpty()){
            return processors.pop();
        }
        return null;
    }
}
