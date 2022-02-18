import java.net.Socket;
import java.io.*;
import java.util.Scanner;

// SimpleClient.java: A simple client program.
public class SingleClient {
    public static void main(String args[]) throws IOException {
        // Open your connection to a server, at port 32000
        // change the host to the IP of the machine you want to connect with
        Socket s1 = new Socket("127.0.0.1",32000);
        OutputStream s1out = s1.getOutputStream();
        DataOutputStream dos = new DataOutputStream (s1out);

        // Send a string!
        Scanner input = new Scanner(System.in);
        System.out.println("Enter text: This is my text to be changed by the SERVER <enter>");
        String inputStr = input.nextLine();
        dos.writeUTF(inputStr);

        // Get an input file handle from the socket and read the input
        InputStream s1In = s1.getInputStream();
        DataInputStream dis = new DataInputStream(s1In);
        String st = dis.readUTF();
        System.out.println("Response from server: " + st);

        // When done, just close the connection and exit
        dis.close();
        s1In.close();
        dos.close();
        s1out.close();
        s1.close();
    }
}

