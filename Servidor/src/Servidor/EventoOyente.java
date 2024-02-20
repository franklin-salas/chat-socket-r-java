package Servidor;

import java.net.Socket;
import java.util.EventListener;

public interface EventoOyente extends EventListener {

    public void onConnectionClientSocket(Socket cl);

    public void onDesconectionClienteSocket(HiloCliente c);

    public void onWriteClientSocket();

    public void onReadClientSocket(String str);
}
