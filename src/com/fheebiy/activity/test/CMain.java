package com.fheebiy.activity.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by Lenovo on 15-3-24.
 */
public class CMain {


    public static void main(String[] args){

        try {
              /*  Socket socket = new Socket("192.168.31.94", 9998);
                socket.setSoTimeout(5000);*/

           /* System.out.print("");*/



            Socket socket = new Socket();
            socket.connect(new InetSocketAddress("192.168.31.94", 9998),5000);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
            String str = null;
            while ((str = reader.readLine()) != null){
                System.out.println(str);
            }
            reader.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



    class MyThread extends Thread {

        @Override
        public void run() {
            try {
              /*  Socket socket = new Socket("192.168.31.94", 9998);
                socket.setSoTimeout(5000);*/
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress("192.168.31.94", 9998),10000);
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String str = reader.readLine();
                System.out.print(str);
                reader.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
