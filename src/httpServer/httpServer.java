package httpServer;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by zag on 09.06.2016.
 */

// https://habrahabr.ru/post/69136/

public class httpServer {
    private static int port = 8080;

    public static void main(String[] args) {

        // open socket
        try {
            ServerSocket ss = new ServerSocket(port);
            while (true) {
                Socket socket = ss.accept();
                new Thread(new httpServerThread(socket), "client thread").start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println("Hello, world!");
    }

}
