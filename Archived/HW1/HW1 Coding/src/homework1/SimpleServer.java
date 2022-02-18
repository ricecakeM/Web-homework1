package src.homework1;

// SimpleServer.java: A simple server program.
import java.net.*;
import java.io.*;
public class SimpleServer {
    public static void main(String args[]) throws IOException {
        // Register service on port 32000
        ServerSocket s = new ServerSocket(32000);
        Socket s1 = s.accept(); // Wait and accept a connection

        // get input from client
        InputStream s1In = s1.getInputStream();
        DataInputStream dis = new DataInputStream(s1In);
        String st = dis.readUTF();

        // reverse the order and case
        StringBuilder result = new StringBuilder();
        for(char ch: st.toCharArray()){
            if(Character.isLowerCase(ch)){
                result.append(Character.toUpperCase(ch));
            }else{
                result.append(Character.toLowerCase(ch));
            }
        }
        String resultStr = result.reverse().toString();

        // Get a communication stream associated with the socket
        OutputStream s1out = s1.getOutputStream();
        DataOutputStream dos = new DataOutputStream (s1out);
        // Send a string
        dos.writeUTF(resultStr);
        
        // Close the connection, but not the server socket
        dis.close();
        s1In.close();
        dos.close();
        s1out.close();
        s1.close();
    }
}
