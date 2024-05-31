package backend;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

public class Server {

    Socket socket = null;
    private ServerSocket serverSocket = null;

    BufferedReader bufferedReader = null;
    BufferedWriter bufferedWriter = null;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void startServer() {
        try {
            if (!serverSocket.isClosed()) {
                System.out.println("Server has Started");
            }
            while (!serverSocket.isClosed()) {
                // client connection request
                socket = serverSocket.accept();
                System.out.println("New Client Connected");
                ClientHandler clientHandler = new ClientHandler(socket);
                Thread thread = new Thread(clientHandler);
                thread.start();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listenClientMessage() {
        while (!serverSocket.isClosed()) {
            try {
//                this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                String clientMessage = this.bufferedReader.readLine();
                System.out.println(clientMessage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // config data
        Properties properties = new Properties();
        int PORT = Integer.parseInt(properties.getProperty("server.port"));
        ServerSocket serverSocket = new ServerSocket(PORT);
        Server server = new Server(serverSocket);
        server.startServer();
        server.listenClientMessage();
    }
}
