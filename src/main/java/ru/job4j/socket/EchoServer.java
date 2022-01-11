package ru.job4j.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoServer {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {

        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();

                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                        for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                            System.out.println(str);
                            if (str.contains("GET")) {
                                int i = str.indexOf("=");
                                int j = str.lastIndexOf("HTTP");
                                String strRes = str.substring(i + 1, j - 1);
                                if (strRes.equals("Exit")) {
                                    socket.close();
                                    server.close();
                                } else if (strRes.equals("Hello")) {
                                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                                    out.write("Hello".getBytes());
                                } else if (strRes.equals("")) {
                                    throw new Exception("Not supported code");
                                } else {
                                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                                    out.write("What".getBytes());
                                }
                            }
                        }
                    out.flush();
                }
            }
            throw new Exception("Not supported");
        }
        catch (Exception e) {
            LOG.error("Exception in log example", e);
        }
    }
}
