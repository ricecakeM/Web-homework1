import java.net.*;
import java.io.*;

// TCPServerSocket: A TCP server program.
public class TCPServerSocket {
    public static void main(String args[]) throws IOException {

        DataInputStream dis = null;
        InputStream s1In = null;
        DataOutputStream dos = null;
        OutputStream s1out = null;
        Socket s1 = null;
        try {
            int portNumber = Integer.parseInt(args[0]);
            ServerSocket s = new ServerSocket(portNumber);
            s1 = s.accept();
//        System.out.println("Current time in millisecond: " + System.currentTimeMillis());

            // get input from client
            s1In = s1.getInputStream();
            dis = new DataInputStream(s1In);
            String st = dis.readUTF();
            // here to process the input and do operations
            System.out.println(st);

            // Get a communication stream associated with the socket
            s1out = s1.getOutputStream();
            dos = new DataOutputStream(s1out);

            // Send a string, what kind of response is that?
            String resultStr = null;
            System.out.println(resultStr);
            System.out.println("Current time in millisecond: " + System.currentTimeMillis());
            dos.writeUTF(resultStr);
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            // Close the connection, but not the server socket
            dis.close();
            s1In.close();
            dos.close();
            s1out.close();
            s1.close();

        }
    }
}



