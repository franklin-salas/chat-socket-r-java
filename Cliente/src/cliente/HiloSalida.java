package cliente;

import java.io.DataOutputStream;
import java.io.IOException;

class HiloSalida extends Thread {

    DataOutputStream salida;
    String mensaje;
    boolean conectado;

    public HiloSalida() {
        super();
        mensaje = "";
        conectado = true;
    }

    @Override
    public void run() {
        try {
            salida.writeUTF("usuario: "+mensaje);
        } catch (IOException ex) {
            ex.getMessage();
            System.out.println("falla en clase hilosalida");
        }
    }

    void setMensaje(String sms0) {
        mensaje = sms0;
    }

    void setSalida(DataOutputStream salida0) {
        salida = salida0;
    }

    void desconectar() {
        conectado = false;
    }
}
