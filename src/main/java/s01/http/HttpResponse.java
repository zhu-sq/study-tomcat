package s01.http;

import java.io.OutputStream;

public class HttpResponse {
    private String head = "HTTP/1.1 200 OK\r\n" +
            "Content-Type: text/html\r\n" +
            "Content-Length: ";

    private String error = "HTTP/1.1 404 ERROR\n\n" +
            "Content-Type: text/html\n\n" +
            "Content-Length: 27" +
            "<html><body>404<body><html>";

    private OutputStream outputStream;

    public HttpResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public String getHead() {
        return head;
    }

    public String getError() {
        return error;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }
}
