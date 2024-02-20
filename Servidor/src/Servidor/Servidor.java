package Servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Servidor implements EventoOyente {

    LinkedList<HiloCliente> listaCliente;
    LinkedList<HiloClean> listahiloClean;
    int puerto;
    ServerSocket server;
    HiloConexion hiloconex;
    //HiloClean hiloClean;
    EventoOyente listenner;
    HiloSalida hilosalida;

    public Servidor(int port) {
        puerto = port;
    }

    public void iniciar() {
        try {
            server = new ServerSocket(puerto);
            listaCliente = new LinkedList<>();
            hiloconex = new HiloConexion(server);
            //hiloClean = new HiloClean();//falta implementar
            hiloconex.addListenner(this);
            hiloconex.start();
            //hiloClean.start();
            System.out.println("servidor conectado");
        } catch (IOException e) {
            System.out.println("El servidor no pudo iniciar");
        }
    }

    public int getPuerto() {
        return puerto;
    }

    public void desconectarCliente(int i) {
        HiloCliente c = listaCliente.get(i);
        c.closeAll();
    }

    public void desconectarTodo() {
        int i = 0;
        int lim = listaCliente.size();
        while (i < lim) {
            desconectarCliente(i);
            i++;
        }
    }

    public void añadirCliente(Socket c) {
        HiloCliente cl = new HiloCliente(c);
        cl.addListennerDesconection(this);
        listaCliente.add(cl);
        ////
        ///
        //HiloClean cr = new HiloClean(cl);
        cl.start();
        //cr.start();
        System.out.println("hay " + listaCliente.size() + " clientes conectados");
    }

    /**
     * @param sms
     * @param i Envia el mensaje sms al cliente i de la lista de clientes
     */
    public void enviarMensaje(String sms, int i) {
        HiloCliente cliente = listaCliente.get(i);
        hilosalida = new HiloSalida(cliente.c);
        hilosalida.setMensaje(sms);
        hilosalida.start();
    }

    public void enviarMensajeTodos(String sms) {
        int i = 0;
        while (i < listaCliente.size()) {
            enviarMensaje(sms, i);
            i++;
        }
    }

    public HiloCliente getCliente(int i) {
        return listaCliente.get(i);
    }

    @Override
    public void onConnectionClientSocket(Socket cl) {
        añadirCliente(cl);
    }

    @Override
    public void onDesconectionClienteSocket(HiloCliente c) {
        listaCliente.remove(c);
        System.out.println("hay " + listaCliente.size() + " clientes");
    }

    @Override
    public void onWriteClientSocket() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onReadClientSocket(String str) {
        listenner.onReadClientSocket(str);
    }

    public void addEventoOyente(EventoOyente evt) {
        listenner = evt;
    }
}
