package clases;
import java.util.Vector;


public class Tablero {

	
	private Linea [] lineasHorizontales;
	private Linea [] lineasVerticales;
	//Linea [][] tablero = {lineasHorizontales, lineasVerticales};
	
	private Cuadrado[] cuadrados;
	private int filas;
	private int columnas;
	
	public Tablero(int filas, int columnas){
		lineasHorizontales = new Linea[columnas * (filas + 1)];
		lineasVerticales = new Linea[filas * (columnas + 1)];

		for (int i = 0; i < columnas * (filas + 1); i++){
			lineasHorizontales[i] = new Linea(i);
		}
		
		int j = columnas * (filas + 1);
		for (int i = 0; i < filas * (columnas + 1); i++){
			lineasVerticales[i] = new Linea(i+j);
		}

		cuadrados = new Cuadrado[filas*columnas];
		
		//inicizalizar cuadrados...
		for (int i = 0; i < filas * columnas; i++){
			int numFila = (i/columnas) + 1;
			cuadrados[i] = new Cuadrado(i, lineasHorizontales[i], 
										lineasVerticales[i+numFila-1], 
										lineasVerticales[i+numFila], 
										lineasHorizontales[i+columnas]);
		}
		
		this.filas = filas;
		this.columnas = columnas;
	}
	
	public void vaciarTablero(){
		for (int i = 0; i < columnas * (filas + 1); i++){
			lineasHorizontales[i].resetear();
		}
		
		for (int i = 0; i < filas * (columnas + 1); i++){
			lineasVerticales[i].resetear();
		}
		
		for (int i = 0; i < filas * columnas; i++){
			cuadrados[i].resetear();
		}
	}

	public Linea getLinea(int numero, int tipo /*0 = Horizontal; 1 = Vertical*/){
		if (tipo == 0){
			return lineasHorizontales[numero];
		}else if (tipo == 1){
			return lineasVerticales[numero];
		}else
			return null;
	}
	
	public int getNumCuadrados(){
		return filas * columnas;
	}
	
	protected Vector<Cuadrado> cuadradosAfectadosPorLínea(Linea l){
		Vector<Cuadrado> cuadrados = new Vector<Cuadrado>();
		
		return cuadrados;
	}
	
	public Cuadrado getCuadrado(int i){
		return cuadrados[i];
	}
	
	public int[] cuadradosAfectadosPorLinea(int numero, int tipo){
		int[] resultado = null;
		
		if (tipo == 0){// Líneas horizontales
			if (numero >= columnas && numero < filas*columnas){
				resultado = new int[2];
				resultado[0]=numero;
				resultado[1]=numero-columnas;
			}
			else if (numero < columnas){ //Primera fila
				resultado = new int[1];
				resultado[0] = numero;
			}
			else if (numero >= filas*columnas){ //Última fila
				resultado = new int[1];
				resultado[0] = numero-columnas;
			}
			
		} if (tipo == 1){ // Líneas verticales
			int numFila = (numero/(columnas+1)) + 1;
			
			if (numero % (columnas+1) == 0){ //Primera columna
				resultado = new int[1];
				resultado[0] = numero-numFila+1;
			}
			else if ((numero+1) % (columnas+1) == 0){ // Última columna
				resultado = new int[1];
				resultado[0] = numero-numFila;
			}
			else{ //Columnas restantes
				resultado = new int[2];
				resultado[0]=numero-numFila+1;
				resultado[1]=numero-numFila;
			}
		}
	
		return resultado;
	}
}
