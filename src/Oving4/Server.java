package Oving4;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Server {
    public static void main(String[] args) {
        final int PORT_NUMBER = 8080;

        System.out.println("Starting server...");

        try {
            DatagramSocket datagramSocket = new DatagramSocket(PORT_NUMBER);
            byte[] sendBytes;
            byte[] receiveBytes = new byte[256];

            while (true) {
                DatagramPacket datagramPacket = new DatagramPacket(receiveBytes, receiveBytes.length);
                datagramSocket.receive(datagramPacket);

                String receivedData = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
                System.out.println("From client: " + receivedData);

                int sumNumbers = -1;
                if (receivedData.contains("+")) {
                    sumNumbers = Integer.parseInt(receivedData.split("\\+")[0]) + Integer.parseInt(receivedData.split("\\+")[1]);
                } else if (receivedData.contains("-")) {
                    sumNumbers = Integer.parseInt(receivedData.split("-")[0]) - Integer.parseInt(receivedData.split("-")[1]);
                }

                String outputString = "= " + sumNumbers;

                InetAddress inetAddress = datagramPacket.getAddress();
                int port = datagramPacket.getPort();

                sendBytes = outputString.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(sendBytes, sendBytes.length, inetAddress, port);
                datagramSocket.send(sendPacket);
            }

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}