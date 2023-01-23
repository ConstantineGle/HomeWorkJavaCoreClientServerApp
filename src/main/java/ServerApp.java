import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp {
    public static void main(String[] args) throws IOException {

        int port = 8085;

        try (ServerSocket serverSocket = new ServerSocket(port)) {

            System.out.println("Server started...");

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                ) {
                    System.out.printf("New connection accepted to port: %d", clientSocket.getPort());
                    final String name = in.readLine();
                    out.printf("Hi %s, your port is %d", name, clientSocket.getPort());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
