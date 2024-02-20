/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

class HiloSalida extends Thread {

    Cliente c;
    String mensaje;

    public HiloSalida(Cliente cc) {
        super();
        c = cc;
        mensaje = "";
    }

    @Override
    public void run() {
        try {
            DataOutputStream salida = c.getSalida();
            salida.writeUTF("servidor: "+mensaje);
        } catch (IOException ex) {
            Logger.getLogger(HiloSalida.class.getName()).log(Level.SEVERE, null, ex);
            //System.out.println("falla hilo salida/run");
        }
    }

    void setMensaje(String sms0) {
        mensaje = sms0;
    }

    void setCliente(HiloCliente cliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
