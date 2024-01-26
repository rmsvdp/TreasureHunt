package guiTools;
import java.util.Scanner;

public class Tablero {	
	

	private char[][] tablero;
	private boolean[][] visible; // Indica si la celda se visualiza o no
	private int ultFila;  // Úlitma elección del jugador
	private int ultColumna;// Úlitma elección del jugador
	private int nfichas; //numero fichas puestas en tablero
	private char emptyCell = ' '; // valor que identifica el estado vacío
	private char hideCell ='?'; // Valor para la celda no visible
	private boolean estado = true;  // Modo de visualización de celdas true = visible // false =invisible

	
	/**
	 * Crea una estructura tablero vacía
	 * @param filas número de filas
	 * @param columnas número de columnas
	 * @param _estado Tablero con celdas visibles : true si, false no
	 */
	public Tablero(int filas, int columnas,boolean _estado) {
		this.tablero = new char [filas][columnas];	// inicializa estructura de celdas
		this.visible = new boolean[filas][columnas]; // inicializa la estructura de estado
		this.estado = _estado;
		limpiaTablero();
	}
	public int getNumFilas() {return this.tablero.length;}
	public int getNumColumnas() {return this.tablero[0].length;}
	public int getFila() {	return ultFila;	}
	public void setFila(int fila) {	this.ultFila = fila;}
	public int getColumna() {return ultColumna;	}
	public void setColumna(int columna) {this.ultColumna = columna;	}
	public char getEmptyCell() {return emptyCell;	}
	public void setEmptyCell(char emptyCell) {	this.emptyCell = emptyCell;	}
	public char getHideCell() {return hideCell;	}
	public void setHideCell(char hideCell) {this.hideCell = hideCell;	}

	/**
	 * Devuelve el caracter que contiene la celda direccionada
	 * @param _fila
	 * @param _columna
	 * @return valor del caracter o chr$(0) si la combinación fila-columna no es válida
	 */
	public char leerCelda(int _fila,int _columna) throws ArrayIndexOutOfBoundsException  {
		return tablero[_fila][_columna];
	}
	public boolean leerEstadoCelda(int _fila,int _columna) throws ArrayIndexOutOfBoundsException  {
		return visible[_fila][_columna];
	}
	public void setEstadoCelda(int _fila,int _columna, boolean estado) throws ArrayIndexOutOfBoundsException  {
		visible[_fila][_columna]= estado;
	}
	
	/**
	 * Limpia el tablero dejándolo listo para unnuevo uso.
	 */
	public void limpiaTablero() {
		
		borrarCeldas();				// Inicializa todas las celdas
		initEstado();
		nfichas=0;
	}
	
	/**
	 * En función del valor del contador de fichas devuelve un valor boleano
	 * @return true si está lleno y false si quedan celdas sin utilizar
	 */
	public boolean isFull() {
		return (nfichas == this.tablero[0].length * this.tablero.length)?true:false;
	}
	/**
	 * Comprueba si hay alguna celda con ficha
	 * @return true , no hay ninguna ficha, false en caso contrario.
	 */
	public boolean isEmpty() {
		boolean res = true;
		for (int i=0;i<tablero.length;i++) {
			for (int j=0;j<tablero[0].length;j++) {
				if (tablero[i][j] != emptyCell) {
					res = false;
					break;
				}
				if (!res) break;
			}
		}	
		return res;
	}

	/**
	 * Muestra el contenido del tablero en cualquiera de sus modos.
	 */
	public void mostrarTablero() {
		System.out.println();
		System.out.print("    ");
		for(int j=0; j<this.getNumColumnas(); j++) {
			System.out.print("|"+(j+1) + "|");
		} // columnas
		System.out.println();
		for(int i=0; i<this.getNumFilas(); i++) {
			System.out.print("|"+ (i+1)+"| ");
			for(int j=0; j<this.getNumColumnas(); j++) {
				if (visible[i][j]) // Si está visible
					System.out.print("("+tablero[i][j] + ")");
				else
					System.out.print("("+this.hideCell + ")");				
			} // columnas
			System.out.println();
		} // filas
		System.out.println();
	} // mostrar tablero

/**
 * Permite introducir por teclado las coordenadas de la casilla
 * que elegirá el usuario
 * Se actualizarán los atributos fila y columna del objeto con la elección
 */
	public void leeMovimiento() {
		Scanner sc = new Scanner(System.in);
		int a,b;
		boolean salir = false;
		while(!salir) {
			
			System.out.printf("\nEscribe la posición horizontal (?): ") ;
			a = sc.nextInt();
			System.out.print("\nEscribe la posición vertical (?): ");
			b = sc.nextInt();		
			if(a<=tablero.length && b<=tablero[0].length && b>0 && a>0) {
				this.ultFila = a-1;
				this.ultColumna = b-1;
				salir = true;
			} else {
				System.out.printf("\nError. La casilla está fuera del tablero, vuelve a intentarlo con valores entre %d y %d.\n", 1, tablero.length);
			}
		} // While de lectura
		
	} // leeMovimiento
	
	/**
	 * Establece un valor para una celda apuntada
	 * por los valores fila , columna del objeto.
	 * Si las coordenadas están fuera del tablero no hace nada
	 * Si la operación es correcta actualiza el contador interno de fichas.
	 * @param valor valor de la celda
	 */
	public void marcarCelda(int _fila, int _columna, char valor) throws ArrayIndexOutOfBoundsException {
		
		tablero [_fila][_columna] = valor;
		visible [_fila][_columna] = true;
		nfichas++;
	}// marcarCelda
	
	/**
	 * Inicializa toda la rejilla de celdas del tablero
	 */
	private void borrarCeldas() {
		for(int i=0; i<tablero.length; i++) {
			for(int j=0; j<tablero[0].length; j++) {
				tablero[i][j] = this.emptyCell;
			}
		}
	} // borrarCeldas
	
	/**
	 * Inicializa el estado de la rejilla de visibilidad
	 */
	private void initEstado() {
		for(int i=0; i<this.getNumFilas(); i++) {
			for(int j=0; j<this.getNumColumnas(); j++) {
				visible[i][j] = this.estado;
			}
		}
	} // initEstado
	
	
} // Class Tablero