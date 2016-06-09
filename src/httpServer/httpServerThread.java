package httpServer;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by zag on 09.06.2016.
 */
public class httpServerThread implements Runnable {
    private Socket sck;
    private InputStream is;
    private OutputStream os;

    httpServerThread(Socket s) {
        try {
            sck = s;
            is = s.getInputStream();
            os = s.getOutputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        System.out.println("Thread started");
        try {
            readHeaders();
            writeResponse();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readHeaders() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String s;
        while (true) {
            s = reader.readLine();
            if (s == null || s.length() == 0) break;
        }

    }

    private void writeResponse() throws Exception {

        String content = "<!doctype html><html><title>Zag</title><body>Иди нахуй пидор :-)</body></html>";
        byte[] b = content.getBytes();

        String response = "HTTP/1.1 200 OK\n" +
                "Server: ZagSrv\r\n" +
                "Content-Type: text/html; charset=utf-8\r\n" +
                "Content-Length: " + b.length + "\r\n" +
                "Connection: close\r\n\r\n";


        String result = response + content;
        os.write(result.getBytes());
        os.flush();
    }
}
