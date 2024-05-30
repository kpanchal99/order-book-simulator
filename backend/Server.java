package backend;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.http.HttpResponse;

public class Server {

    Socket socket = null;
    private ServerSocket serverSocket = null;

    public Server(ServerSocket serverSocket){
        this.serverSocket = serverSocket;
    }
    public void startServer(){
        try{
            if(!serverSocket.isClosed()){
                System.out.println("Server has Started");
            }
            while(!serverSocket.isClosed()){
                // client connection request
                socket = serverSocket.accept();
                System.out.println("New Client Connected");

            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(9999);
        Server server = new Server(serverSocket);
        server.startServer();
    }
}
