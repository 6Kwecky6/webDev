package Oving4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;


public class Client {
    public static void main(String[] args) {
        final int PORT_NUMBER = 8080;

        System.out.println("Connecting to server...");
        System.out.println("Please give me math:");

        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        try {
            DatagramSocket datagramSocket = new DatagramSocket();
            InetAddress inetAddress = InetAddress.getByName("localhost");

            byte[] sendBytes;
            byte[] receiveBytes = new byte[256];

            while (true) {
                String msg = bufferedReader.readLine();
                if (msg.equals("close")) {
                    datagramSocket.close();
                    return;
                }

                sendBytes = msg.getBytes();

                DatagramPacket datagramPacket = new DatagramPacket(sendBytes, 0, sendBytes.length, inetAddress, PORT_NUMBER);
                datagramSocket.send(datagramPacket);

                DatagramPacket receivePacket = new DatagramPacket(receiveBytes, receiveBytes.length);
                datagramSocket.receive(receivePacket);

                String ans = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Server: " + ans);
            }

            //datagramSocket.close();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}