package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Usuario implements UsuarioEventoOyente {

    Socket cliente;
    HiloEntrada hiloentrada;
    HiloSalida hilosalida;
    DataOutputStream salida;
    DataInputStream entrada;
    InetAddress host;
    UsuarioEventoOyente evt;
    int port;

    /**
     * Constructor usuario
     *
     * @param host0
     * @param port0
     */
    public Usuario(InetAddress host0, int port0) {
        host = host0;
        port = port0;
    }

    /**
     * inicia el constructor de los atributos del usuario
     */
    public void iniciar() {
        try {
            cliente = new Socket(host, port);
            entrada = new DataInputStream(cliente.getInputStream());
            salida = new DataOutputStream(cliente.getOutputStream());
            hiloentrada = new HiloEntrada();
            hiloentrada.setEntrada(entrada);
            hiloentrada.addUsuarioEventoOyente(this);
            hiloentrada.start();
        } catch (IOException ex) {
            System.out.println("No se pudo iniciar el obj usuario");
        }
    }

    public void mandarMensaje(String s) {
        hilosalida = new HiloSalida();
        hilosalida.setSalida(salida);
        hilosalida.setMensaje(s);
        hilosalida.start();
    }

    public void desconectar() {
        try {
            System.out.println("se desconecto el servidor");
            hiloentrada.stop();
            hilosalida.stop();
            cliente.close();
        } catch (IOException ex) {
            System.out.println("no se pudo desconectar");
        }
    }

    HiloEntrada gethiloentrada() {
        return hiloentrada;
    }

    public void addListenner(UsuarioEventoOyente e) {
        evt = e;
    }

    @Override
    public void onConnectionClientSocket(Socket cl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onDesconectionServer(int itmeOut) {
        desconectar();
    }

    @Override
    public void onWriteClientSocket() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onReadClientSocket(String s) {
        this.evt.onReadClientSocket(s);
    }
}
