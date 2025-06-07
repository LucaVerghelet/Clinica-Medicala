package client.io;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServiceComunicare {
    public static final Integer PORT = 8010;
    private String hostName;

    public ServiceComunicare() throws UnknownHostException {
        InetAddress host = InetAddress.getLocalHost();
        this.hostName = host.getHostName();
    }

    public <T, O> O trimiteMesaj(T mesaj) throws IOException, ClassNotFoundException {
        Socket socket = new Socket(this.hostName, PORT);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(mesaj);
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        O raspuns = (O) ois.readObject();
        ois.close();
        oos.close();
        return raspuns;
    }
}
