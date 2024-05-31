package backend;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    String clientUserName;
    int clientUserId;

    Socket socket;
    BufferedReader bufferedReader = null;
    BufferedWriter bufferedWriter = null;



    public Client(String host, int PORT, String clientUserName, int clientUserId) throws IOException {
        this.clientUserName = clientUserName;
        this.clientUserId = clientUserId;
        socket = new Socket(host, PORT);
        this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        System.out.println("Connected to " + socket.getInetAddress().getHostAddress() + ":" + socket.getPort());
    }

    public void sendMessageToServer() {
        try {
            bufferedWriter.write(clientUserName + "," + clientUserId);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            Scanner scanner = new Scanner(System.in);
            while (socket.isConnected()) {
                String messageToSend = scanner.nextLine();
                bufferedWriter.write(clientUserName + ": " + messageToSend);
                bufferedWriter.newLine();
                bufferedWriter.flush();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        // to handle username and id exception
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your username: ");
        String userName = scanner.nextLine();
        System.out.println("Please enter your userId: ");
        int userId = Integer.parseInt(scanner.nextLine());
        Client client = new Client("localhost", 9999, userName, userId);
        client.sendMessageToServer();
    }
}
