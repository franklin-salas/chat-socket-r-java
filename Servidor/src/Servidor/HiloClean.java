/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author system32
 */
public class HiloClean extends Thread {

    boolean connect;
    HiloCliente hc;

    public HiloClean() {
        super();
        connect = true;
    }

    public HiloClean(HiloCliente cl) {
        super();
        hc = cl;
    }

    @Override
    public void run() {
        try {
            Cliente c = hc.getCliente();
            Socket soc = c.getSocket();
            String addr = soc.getInetAddress().getHostName();
            int openPort = soc.getPort();               
            while (soc.getInetAddress().isReachable(1000)) {
                
            }
            
        } catch (IOException ex) {
            Logger.getLogger(HiloClean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static boolean isReachable(Socket soc, String addr, int openPort, int timeOutMillis) {
        try {
            soc.connect(new InetSocketAddress(addr, openPort), timeOutMillis);
            System.out.println("algo esta pasando hostia");
            return true;
        } catch (IOException ex) {
            System.out.println("no pasa nada");
            return false;
        }
    }
}
