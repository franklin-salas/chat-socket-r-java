package otras;

import java.util.EventListener;

public interface ProcesadorListener extends EventListener{
	
    /**
     *
     * @param Procesador
     */
    public void onTextoEditado(Procesador Procesador);

}
