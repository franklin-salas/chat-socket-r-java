package otras;

public class Procesador {

    ProcesadorListener listener;
    String contenido;

    public Procesador() {
        this.contenido = "";
    }

    public void editar(String contenido) {
        this.contenido = contenido;
        if (listener != null) {
            listener.onTextoEditado(this);
        }
    }

    public String ver_contenido() {
        return this.contenido;
    }

    public void addProcesadorListener(ProcesadorListener l) {
        this.listener = l;
    }

}
