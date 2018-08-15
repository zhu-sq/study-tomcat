package s01.startup;

import s01.core.SimpleContainer;
import s01.http.HttpConnector;

import java.io.IOException;

public class Bootstart {

    public static void main(String[] args) {
        SimpleContainer container = new SimpleContainer();
        HttpConnector connector = new HttpConnector();
        connector.setContainer(container);
        try {
            connector.init();
            connector.threadStart();
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
