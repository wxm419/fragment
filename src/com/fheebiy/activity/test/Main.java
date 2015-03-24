package com.fheebiy.activity.test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by bob zhou on 15-3-24.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("test");
        try {
            ServerSocket ss = new ServerSocket(9998);
            while (true) {
                Socket socket = ss.accept();
                new MyThread(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    static class MyThread extends Thread {

        private Socket socket;

        MyThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            System.out.print("zhouwenbo");
            OutputStream os = null;
            try {
                os = socket.getOutputStream();
                while (true) {
                    os.write("netty server test\n".getBytes("utf-8"));          //用Buffered
                    Thread.sleep(3000);
                }
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    os.close();
                    socket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
