
import java.net.*;
import java.io.*;
import java.util.HashMap;
import java.util.*;
import java.util.logging.Logger;


// TCPClientSocket: A TCP client program.
public class TCPClientSocket {
    public static void main(String args[]) throws IOException {
        Logger logger = Logger.getLogger(String.valueOf(TCPClientSocket.class));
//        Socket s1 = new Socket("127.0.0.1",32000);

        // map to store key value and pre-populate the data
        Map<String, String> messageStoreMap = new HashMap<>();
        messageStoreMap.put("a", "aaa");
        messageStoreMap.put("b", "bbb");
        messageStoreMap.put("c", "ccc");
        messageStoreMap.put("d", "ddd");
        messageStoreMap.put("e", "eee");

        // initialize input and output variables
        Socket s1 = null;
        InputStream s1In = null;
        DataInputStream dis = null;
        OutputStream s1out = null;
        DataOutputStream dos = null;

        try {
            int portNumber = Integer.parseInt(args[0]);
            String host = String.valueOf(args[1]);
            s1 = new Socket(host, portNumber);

            // set time out
            s1.setSoTimeout(3000);

            // sent the request and print timestamp
            s1out = s1.getOutputStream();
            dos = new DataOutputStream(s1out);

            // Q3, how to populate date here? different data, could be anything
//            putRequest(hostName, portNumber);
//            getRequest(hostName, portNumber);
//            deleteRequest(hostName, portNumber);

            // Send a string!
//            Scanner input = new Scanner(System.in);
//            System.out.println("Enter text: This is my text to be changed by the SERVER <enter>");
//            String inputStr = input.nextLine();
            String command = "put: aaa: bbb";

            dos.writeUTF(command);
            System.out.println("Current time in millisecond: " + System.currentTimeMillis());

            // Get an input file handle from the socket and read the input
            s1.setSoTimeout(3000);
            if () {
                // log print? print the timestamp along with the error message
                // you should note it in a client log and send the remaining requests.
            }

            // get response from the server
            s1In = s1.getInputStream();
            dis = new DataInputStream(s1In);
            String st = dis.readUTF();

            // print the response and time stamp
            System.out.println("Response from server: " + st);
            System.out.println("Current time in millisecond: " + System.currentTimeMillis());


        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            // When done, just close the connection and exit
            dis.close();
            s1In.close();
            dos.close();
            s1out.close();
            s1.close();
        }
    }
}



//        // put request
//        private static void putRequest(Socket client, String msgContent, Map<String, String> messageStoreMap) {
//            if (msgContent != "") {
//                String key = msgContent.substring(0, msgContent.indexOf(","));
//                String message = msgContent.substring(msgContent.indexOf(","));
//                if (key != "") {
//                    logger.debug("The request is to store a message with key: " + key);
//                    messageStoreMap.put(key, message);
//                    AckToClient(client, "PUT", key, "");
//
//                } else {
//                    logger.error("Received a wrong request of length: " + msgContent.length() + " from: "
//                            + client.getInetAddress() + " at Port: " + client.getPort());
//                }
//
//            } else {
//                logger.debug("The searched message content is not present.");
//            }
//            try {
//                client.close();
//            } catch (IOException e) {
//
//                e.printStackTrace();
//            }
//
//        }











