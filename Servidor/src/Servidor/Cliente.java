package Servidor;

//import cliente.HiloEntrada;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente {

    int puerto;
    Socket cliente;
    DataInputStream entrada;
    DataOutputStream salida;
    private String sms;

    public Cliente(Socket c) {
        cliente = c;
        try {
            entrada = new DataInputStream(cliente.getInputStream());
            salida = new DataOutputStream(cliente.getOutputStream());
            sms = "Sin mensaje";
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Cliente(InetAddress dir, int puerto0) {
        try {
            puerto = puerto0;
            cliente = new Socket(dir, puerto);
            entrada = new DataInputStream(cliente.getInputStream());
            salida = new DataOutputStream(cliente.getOutputStream());
        } catch (IOException ex) {
            System.out.println("no se puedo crear el socket");
        }
    }

    public DataInputStream getEntrada() {
        return entrada;
    }

    public void setEntrada(DataInputStream entrada) {
        this.entrada = entrada;
    }

    public DataOutputStream getSalida() {
        return salida;
    }

    public void setSalida(DataOutputStream salida) {
        this.salida = salida;
    }

    public Socket getSocket() {
        return cliente;
    }

    public String getSms() {
        String s = sms;
        sms = "";
        return s;
    }
}
