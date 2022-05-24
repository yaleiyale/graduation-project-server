package com.example.server;

import java.io.*;
import java.net.Socket;

public class TCP_Client {
    public void openDoor(String ip) throws IOException {
        InputStream is = null;
        BufferedReader br = null;
        OutputStream os = null;
        PrintWriter pw = null;
        try {

            Socket socket = new Socket(ip, 30000);
            System.out.println("Socket start");

            os = socket.getOutputStream();

            pw = new PrintWriter(os);
            pw.write("message from client");
            pw.flush();

            socket.shutdownOutput();

            is = socket.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));
            String message;
            while ((message = br.readLine()) != null) {
                System.out.println("message：" + message);
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert br != null;
            br.close();
            is.close();
            pw.close();
            os.close();
        }
    }
}