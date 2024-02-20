package cliente;

import java.net.Socket;
import java.util.EventListener;

public interface UsuarioEventoOyente extends EventListener {

    public void onConnectionClientSocket(Socket cl);

    public void onDesconectionServer(int itmeOut);

    public void onWriteClientSocket();

    public void onReadClientSocket(String s);
}
