package org.example.model;

import org.example.auth.AuthServiceDoctor;
import org.example.database.HibernateUtil;
import org.example.dto.InregistrareDoctorDTO;
import org.example.dto.InregistrareUserDTO;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static ServerSocket server;
    private static int port = 8010;

    public static void main(String args[]) throws IOException, ClassNotFoundException {
        server = new ServerSocket(port);
        HibernateUtil hibernateUtil = new HibernateUtil();
        AuthServiceDoctor authService = new AuthServiceDoctor(hibernateUtil);
        while (true) {
            System.out.println("Waiting for the client request");
            Socket socket = server.accept();
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            InregistrareDoctorDTO message = (InregistrareDoctorDTO) ois.readObject();
            System.out.println("Message Received: " + message);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject("Hi Client " + message);
            authService.inregistrare(message);
            ois.close();
            oos.close();
            socket.close();
//            if(message.equalsIgnore("exit")) break;
        }
//        System.out.println("Shutting down Socket server!!");
//        server.close();
//    }

    }
}