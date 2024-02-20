package otras;

public class App implements ProcesadorListener{

	public App(){}

	public void init(){

		Procesador bloc = new Procesador();
		bloc.addProcesadorListener(this);
		
		bloc.editar("hola mundo");
		bloc.editar("que paoc.editar(\"hola mso");

	}

        @Override
	public void onTextoEditado(Procesador procesador){
		System.out.println("Se ha editado el texto!");
		System.out.println(procesador.ver_contenido());
	}


}
