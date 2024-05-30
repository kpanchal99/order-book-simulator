package backend;

import java.io.*;
import java.net.Socket;

public class Client {
    Socket socket;
    InputStreamReader inputStreamReader = null;
    OutputStreamWriter outputStreamWriter = null;

    BufferedReader bufferedReader = null;
    BufferedWriter bufferedWriter = null;

    String clientUserName;
    int clientUserId;

    public Client(String host, int PORT, String clientUserName, int clientUserId) throws IOException {
        this.clientUserName = clientUserName;
        this.clientUserId = clientUserId;
        socket = new Socket(host,PORT);
        System.out.println("Connected to " + socket.getInetAddress().getHostAddress() + ":" + socket.getPort());
    }
    public static void main(String[] args) throws IOException {
        Client client = new Client("localhost",9999, "KP",1);

    }
}
