import java.net.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

// UDPClientSocket: A simple UDP client program.
public class UDPClientSocket {
    public static void main(String args[]) throws SocketException {
        // logger to log message
        Logger LOGGER = Logger.getLogger(String.valueOf(UDPClientSocket.class));
//        String hostname = "";
//        int port = 17;
//        InetAddress address = InetAddress.getByName(hostname);
//        DatagramSocket aSocket = new DatagramSocket();

//        int portNumber = Integer.parseInt(args[0]);
//        String host = String.valueOf(args[1]);
//        Socket s1 = new Socket(host, portNumber);
//
//        //set time out?
//        s1.setSoTimeout(3000);

        // map to store key value
        Map<String, String> messageStoreMap = new HashMap<>();

        // args give message contents and server hostname
        DatagramSocket aSocket = null;
        if (args.length < 3) {
            System.out.println("Usage: java UDPClient <message> <Host name> <Port number>");
            System.exit(1);
        }
        try {
            aSocket = new DatagramSocket();
            byte [] m = args[0].getBytes();
            InetAddress aHost = InetAddress.getByName(args[1]);
            int serverPort = Integer.parseInt(args[2]);
            DatagramPacket request =
                    new DatagramPacket(m, args[0].length(), aHost, serverPort);

            // client send the request to server, print the request and set time out
            aSocket.send(request);
            System.out.println("Request" + new String(request.getData()));
            aSocket.setSoTimeout(3000);

            // Get an input file handle from the socket and read the input
            byte[] buffer = new byte[1000];
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            aSocket.receive(reply);
            System.out.println("Reply: " + new String(reply.getData()));
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


