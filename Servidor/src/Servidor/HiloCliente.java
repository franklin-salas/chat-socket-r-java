package Servidor;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class HiloCliente extends Thread {
    
    Cliente c;
    boolean conectado;
    DataInputStream entrada;
    EventoOyente evt;
    
    public HiloCliente(Socket s) {
        super();
        c = new Cliente(s);
        conectado = true;
        entrada = c.getEntrada();
    }
    
    @Override
    public void run() {
        while (conectado) {
            try {
                String s = entrada.readUTF();
                evt.onReadClientSocket(s);
            } catch (IOException ex) {
                System.out.println("un cliente se ha desconectado");
                desconectar();
            }
        }
        evt.onDesconectionClienteSocket(this);
    }
    
    public void desconectar() {
        conectado = false;
    }
    
    public void addListennerDesconection(EventoOyente e) {
        evt = e;
    }
    
    public void closeAll() {
        try {
            Socket s = c.getSocket();
            s.close();
        } catch (IOException ex) {
            Logger.getLogger(HiloCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    Cliente getCliente() {
        return c;
    }
}
