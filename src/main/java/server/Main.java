package server;
import server.io.Server;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Server server = new Server();
        server.start();
    }


}