import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SingleServer implements Runnable {
    protected int serverPort;
    protected ServerSocket serverSocket = null;
    protected boolean isStopped = false;

    public SingleServer(int serverPort) {
        this.serverPort = serverPort;
    }

    @Override
    public void run() {
        System.out.println("A single thread is running ... ");
        openServerSocket();

        while (!isStopped) {
            Socket clientSocket;
            try {
                clientSocket = this.serverSocket.accept();
            } catch (IOException e) {
                if (isStopped) {
                    System.out.println("Server Stopped.");
                    return;
                }
                throw new RuntimeException(
                        "Error accepting client connection", e);
            }
            try {
                processClientRequest(clientSocket);
            } catch (Exception e) {
                System.out.println("something wrong happened while processing request");
            }
        }
        System.out.println("Server Stopped.");
    }

    private void openServerSocket() {
        try {
            ServerSocket s = new ServerSocket(32000);
        } catch (IOException e) {
            throw new RuntimeException("Cannot open port 32000", e);
        }
    }

    public void processClientRequest(Socket clientSocket) throws Exception {
        // get input from client
        InputStream s1In = clientSocket.getInputStream();
        DataInputStream dis = new DataInputStream(s1In);
        String st = dis.readUTF();

        // reverse the order and case
        StringBuilder result = new StringBuilder();
        for (char ch : st.toCharArray()) {
            if (Character.isLowerCase(ch)) {
                result.append(Character.toUpperCase(ch));
            } else {
                result.append(Character.toLowerCase(ch));
            }
        }
        String resultStr = result.reverse().toString();


        // Get a communication stream associated with the socket
        OutputStream s1out = clientSocket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(s1out);
        // Send a string
        dos.writeUTF(resultStr);

        // Close the connection, but not the server socket
        dis.close();
        s1In.close();
        dos.close();
        s1out.close();
        clientSocket.close();
    }

    public void stop() {
        this.isStopped = true;
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("Error closing server", e);
        }
    }

    public static void main(String[] args) {
        SingleServer server = new SingleServer(32000);
        new Thread(server).start();

        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Stopping Server");
        server.stop();
    }
}
