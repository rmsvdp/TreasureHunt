package guiTools;
import java.util.Scanner;

/**
 * Clase que implementa un menú sencillo en modo texto.
 * El menú permite la incialización de las opciones, y establecer un título
 * para el mismo. Dispone de métodos para visualizar las opciones disponibles,
 * y solicitar la elección de una de ellas desde el teclado.
 * @author root 
 *
 */
public class Menu {

	private String[] opciones;	// Array de literales con las opciones
	private int eleccion=0;  	// 0 sin elegir opción
/** 
 * Cabecera del menú con título descriptivo del mismo
 */
	public String titulo="";	// Título del menú
	private int numOpc = 0;		// Campo auxiliar con el número de opciones disponibles

	/**
	 * Contructor vacío
	 */
	public Menu() {
		this.eleccion=0;  // 0 sin elegir opción
		this.titulo="";
		this.numOpc = 0;		
	}
	/**
	 * Constructor con opciones, asigna un título genérico
	 * @param opc Lista de opciones para gestionar
	 */
	public Menu(String[] opc) {
		
		this.opciones=opc;		          // Igualamos el puntero al array que nos pasan como parámetro.
		this.numOpc = opc.length;	      // número de opciones
		this.titulo = "MENU DE OPCIONES"; // Título por defecto
	}
	
	/**
	 * Recupera el título del menú
	 * @return Cadena con el título del menú
	 */
	public String getTitulo() {
		return titulo;
	}
/**
 *  Establece un título para el menú
 * @param titulo cadena con el titulo elegido
 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	/**
	 * Recupera la lista de los literales que conforman el menú
	 * @return array de opciones
	 */
	public String[] getOpciones() {
		return opciones;
	}
	/**
	 * Inicialización directa del array de opciones
	 * @param opciones array con los literales de las opciones del menú
	 */
	public void setOpciones(String[] opciones) {
		this.opciones = opciones;
	}
	/**
	 * Recupera la última opción de menú elegida.
	 * @return opción elegida
	 */
	public int getEleccion() {
		return eleccion;
	}
	
	/**
	 * Establece un número de opción. Si no se encuentra en el rango 
	 * no se actualiza el atributo eleccion
	 * @param eleccion número de opción para actualizar
	 */
	public void setEleccion(int eleccion) {
		if (this.eleccion >=0 && this.eleccion < this.numOpc)
		this.eleccion = eleccion;
	}
	
	/**
	 * Muestra en consola el menú construido
	 * Primero el título y después las opciones definidas
	 * Termina con la opción 0.- Salir
	 */
	public void mostrarMenu() {
		
		System.out.println(this.titulo);
		
		System.out.println("-".repeat(this.titulo.length()));
		for (int j=0;j<this.opciones.length;j++) {
			System.out.println(j+1 + ".- " +this.opciones[j]);
			} // for
		    System.out.println("0.- Salir\n");
	}
	/**
	 * Gestiona la elección de la opción a través de teclado por parte del usuario
	 * @return opción elegida
	 */
	public int eligeOpcion() {
		int opc=0;
		boolean valido=false;
		Scanner scr = new Scanner(System.in);
		while (!valido) {
			System.out.print("Elige opción:" );
			opc = scr.nextInt();
			if ((opc>=0) && (opc <= opciones.length)){
			      valido = true;
				System.out.println();
			}
			else {
				System.out.println("Opción no válida");
			}
		}
		this.eleccion = opc;
		return opc;
	}
	/**
	 * Muestra el literal correspondiente a la opción elegida.
	 * Si no es un número de opción válida retorna una cadena vacía.
	 * @param opc : un número de opción válida
	 * @return Cadena con el literal de la opción elegida.
	 */
	public String muestraLiteral(int opc) {
		
		String cadena = "";
		if (opc == 0) {
			cadena = "Salir";
		}
		else if (opc>=1 && opc < this.numOpc) {
			cadena = this.opciones[opc-1];
		}	
		return cadena;
		
	}
	
}
