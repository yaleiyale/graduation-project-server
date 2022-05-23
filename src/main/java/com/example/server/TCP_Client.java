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
            //建立连接
            Socket socket = new Socket(ip, 30000);
            System.out.println("开启Socket线程");
            //获取字节输出流
            os = socket.getOutputStream();
            //将输出流包装为打印流
            pw = new PrintWriter(os);
            pw.write("Hi server! This is message from client!");
            pw.flush();
            //关闭输出流
            socket.shutdownOutput();
            //获取字节输入流
            is = socket.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));
            String message;
            while ((message = br.readLine()) != null) {
                System.out.println("我是客户端，服务器返回消息：" + message);
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            assert br != null;
            br.close();
            is.close();
            pw.close();
            os.close();
        }
    }
}