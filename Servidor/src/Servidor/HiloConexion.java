package Servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

    //atiende la peticion de conexion 
//de cada cliente
//solo atiende peticiones
public class HiloConexion extends Thread {

    boolean conectado;
    ServerSocket ss;
    EventoOyente l;

    public HiloConexion(ServerSocket s) {
        super();
        ss = s;
        conectado = true;
    }

    @Override
    public void run() {
        while (conectado) {
            Socket s;
            try {
                System.out.println("esperando usuarios...");
                s = ss.accept();
                l.onConnectionClientSocket(s);
            } catch (IOException ex) {
                Logger.getLogger(HiloConexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void stopThread() {
        conectado = false;
        /*
        posibles eventos
        */
    }
    public void addListenner(EventoOyente o) {
        l = o;
    }
}
