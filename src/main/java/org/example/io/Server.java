package org.example.io;

import org.example.auth.AuthServiceDoctor;
import org.example.auth.AuthServicePacient;
import org.example.database.HibernateUtil;
import org.example.dto.InregistrareDoctorDTO;
import org.example.dto.InregistrarePacientDTO;
import org.example.dto.MesajFrontend;
import org.example.dto.TipOperatie;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static ServerSocket server;
    private static int port = 8010;


    private static <T> String procesareOperatie(MesajFrontend<T> mesajFrontend,
                                          AuthServiceDoctor authServiceDoctor,
                                          AuthServicePacient authServicePacient) {
        try {
            String raspunsMesaj = null;
            switch (mesajFrontend.getTipOperatie()) {
                case TipOperatie.INREGISTRARE_PACIENT:
                    InregistrarePacientDTO dtoInregistrarePacient = (InregistrarePacientDTO) mesajFrontend.getDto();
                    authServicePacient.inregistrare(dtoInregistrarePacient);
                    raspunsMesaj = "Inregistrare efectuata cu succes.";
                    System.out.println(raspunsMesaj);
                    break;
                case TipOperatie.INREGISTRARE_DOCTOR:
                    InregistrareDoctorDTO dtoInregistrareDoctor = (InregistrareDoctorDTO) mesajFrontend.getDto();
                    authServiceDoctor.inregistrare(dtoInregistrareDoctor);
                    raspunsMesaj = "Inregistrare efectuata cu succes.";
                    System.out.println(raspunsMesaj);
                    break;
                case TipOperatie.LOGIN:
                    raspunsMesaj = "Nu este implementat.";
                    System.out.println(raspunsMesaj);
                    break;
                default:
                    raspunsMesaj = "Metoda " + mesajFrontend.getTipOperatie().toString() + " nu a fost recunoscuta.";
                    System.out.println(raspunsMesaj);
                    break;
            }

            return raspunsMesaj;
        } catch (Exception e) {
            System.err.println(e.toString());
            return "Eroare: " + e.toString();
        }
    }

    public static <T> void main(String args[]) throws IOException, ClassNotFoundException {
        server = new ServerSocket(port);
        HibernateUtil hibernateUtil = new HibernateUtil();
        AuthServiceDoctor authServiceDoctor = new AuthServiceDoctor(hibernateUtil);
        AuthServicePacient authServicePacient = new AuthServicePacient(hibernateUtil);
        while (true) {
            System.out.println("Waiting for the client request");
            Socket socket = server.accept();
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            MesajFrontend<T> message = (MesajFrontend<T>) ois.readObject();
            String raspuns = procesareOperatie(message, authServiceDoctor, authServicePacient);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(raspuns);
            ois.close();
            oos.close();
            socket.close();
        }
    }
}
