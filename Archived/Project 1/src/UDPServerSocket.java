import java.net.*;
import java.io.*;

// UDPServerSocket: A simple UDP server program.
public class UDPServerSocket {
    public static void main(String args[]){
        DatagramSocket aSocket = null;
        if (args.length < 1) {
            System.out.println("Usage: java UDPServer <Port Number>");
            System.exit(1);
        }
        try {
            // send request
            int socket_no = Integer.parseInt(args[0]);
            aSocket = new DatagramSocket(socket_no);
            byte[] buffer = new byte[1000];
            while(true) {
                DatagramPacket request = new DatagramPacket(buffer,
                        buffer.length);
                // server receives and print out the request
                aSocket.receive(request);
                System.out.println("Request: " + new String(request.getData()));

                // server send the reply to client
                DatagramPacket reply = new DatagramPacket(request.getData(),
                        request.getLength(),request.getAddress(),
                        request.getPort());
                System.out.println("Reply: " + new String(reply.getData()));
                System.out.println("Current time in millisecond: " + System.currentTimeMillis());
                aSocket.send(reply);
            }
        }
        catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        }
        catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
        finally {
            if (aSocket != null)
                aSocket.close();
        }
    }
}

