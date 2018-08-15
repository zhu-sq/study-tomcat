package s01.http;

import common.ParseUrl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
    private String method;
    private String uri;
    private Map<String,String> params;
    private InputStream inputStream;
    private String protocal;

    public String getMethod() {
        return method;
    }

    public String getUri() {
        return uri;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public String getProtocal() {
        return protocal;
    }

    public HttpRequest(InputStream inputStream){
        this.inputStream=inputStream;
    }

    public void parse()throws IOException{

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream));
        String str = null;

        str = reader.readLine();

        if(str==null) return;
        int index1=0, index2=0;
        index1 = str.indexOf(' ');
        if (index1 != -1) {
            index2 = str.indexOf(' ', index1 + 1);
            if (index2 > index1)
                uri = str.substring(index1 + 1, index2);
        }
        method = str.substring(0,index1);
        protocal = str.substring(index2+1,str.length());

        params = ParseUrl.URLRequest(uri);
        int index3 = uri.indexOf('?');
        uri = uri.substring(0,index3);

        System.out.println("http 请求");
        System.out.println(str);
//        while (true) {
//            str = reader.readLine();
//            if(str!=null)
//                System.out.println(str);
//            else
//                break;
//        }

    }
}
