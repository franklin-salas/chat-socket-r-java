/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.DataInputStream;
import java.io.IOException;

public class HiloEntrada extends Thread {

    DataInputStream entrada;
    boolean conectado;
    UsuarioEventoOyente us;

    public HiloEntrada() {
        super();
        conectado = true;
    }

    @Override
    public void run() {
        while (conectado) {
            try {
                System.out.println("esperando mensaje");
                String s = entrada.readUTF();
                us.onReadClientSocket(s);
            } catch (IOException ex) {
                desconectar();
                us.onDesconectionServer(5000);
            }
        }
    }

    public void setEntrada(DataInputStream entrada0) {
        entrada = entrada0;
    }

//    public DataInputStream getEntrada() {
//        return entrada;
//    }
//    
//    public ObjectInputStream getEntrada() {
//        return null;
//    }
    void desconectar() {
        conectado = false;
    }

    public void addUsuarioEventoOyente(UsuarioEventoOyente u) {
        us = u;
    }
}
