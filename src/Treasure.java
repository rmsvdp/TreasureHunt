import guiTools.Menu;
import guiTools.Tablero;

public class Treasure {

	
	private Menu menuPrincipal;						// Menú para gestionar el juego
	private int movimientos;						// Movimientos en un partida
	private Tablero tlo = new Tablero(5,4,false);	// Tablero de 5X4 oculto
	private int vidas;
	private final char vidaExtra = '@';
	private final char mina = '*';
	private final char tesoro = '$';

	public static void main(String[] args) {

		Treasure lo = new Treasure();
		lo.nuevaPartida();
		System.out.println("\nAplicación terminada");
	}

	/**
	 * Gestiona una sesión del programa Treasure Hunt
	 */
	public void nuevaPartida() {
		
		String [] opcs = {"Nueva Partida","Tabla de Puntuaciones"};
		this.menuPrincipal = new Menu ();
		boolean finSesion = false;
		int result;
		menuPrincipal = new Menu(opcs);
		menuPrincipal.setTitulo("TREASURE HUNT!");
		while (!finSesion) {
			menuPrincipal.mostrarMenu();
			result = menuPrincipal.eligeOpcion();
			switch(result) {
				case 1:	// iniciar partida
					this.playTheGame();
					break;
				case 2: // Creditos
					System.out.println("\n1 TBP \n");
					break;
				case 0: // salir aplicación
					finSesion= true;
			} // switch
		} // while !finSesion
	} // JuegaPartida

	/**
	 * Ejecuta una partida del juego Treasure Hunt
	 */
	public void playTheGame() {
		

		boolean finJuego = false;
		this.vidas = 3;
		this.movimientos = 0;
		tlo.limpiaTablero();
		setNivel();
		String msg = "";
		while (!finJuego && vidas>0) {	
			tlo.mostrarTablero();
			tlo.leeMovimiento();
			switch (this.procesaMovimiento()) {
				case 0: // casilla en blanco
					msg="**** Aquí no hay nada ...";
					break;
				case 1: // vida extra
					msg="**** Has encontrado una vida Extra !";
					vidas ++;
					break;
				case 2: // mina
					msg="**** Has encontrado una mina, pierdes una vida !";
					vidas --;
					break;
				case 3: // tesoro
					msg="**** Aquí estaba el Tesoro !*";
					finJuego=true;
					break;	
			}
			this.movimientos++;
			tlo.mostrarTablero();
			muestraMarcador(msg);
		} // while !finJuego

		System.out.println("**** JUEGO TERMINADO ****");

		if (vidas>0) {
		System.out.println("\n***********************************************\n"
				+          "Tesoro encontrado en : " + this.movimientos + " movimientos !" +
		                 "\n***********************************************\n");}
		else {
			System.out.println("***********************************************\n"
					+          "Has muerto en tu búsqueda ..." +
			                 "\n***********************************************\n");
		}
		
	} // PlayTheGame
	  
	/**
	 * Muestra el número de movimientos y las vidas
	 */
	private void muestraMarcador(String _msg) {
		System.out.println(
				"************************************\n"
			+   _msg +"\n"	
			+   "**** Vidas : " + vidas + "      Movimientos : "+ movimientos 
		    + "\n************************************\n");
	}
	   
	/**
	 * Inicializa el juego con un nivel predefinido. Hay que generar aleatoriamente:
	 * 1 @ tesoro,  4 * minas ,  1 $ vida extra
	 */
	public void setNivel() {

		setItem(this.tesoro);			// tesoro
		setItem(this.vidaExtra);		// vida extra	
		for(int i=0;i<4;i++) {			// minas
			setItem(this.mina);	
		}
	} // setNivel
	
	/**
	 * Rellena una celda aleatoria con el valor especificado.
	 * Si la posición está ocupada genera otra hasta encontrar
	 * una vacía
	 * @param valor valor con el que se actualiza la celda.
	 */
	
	public void setItem(char valor) {
		int tmpFila,tmpColumna;
		boolean dupFlag = false;
		while (!dupFlag) {
			tmpFila = (int) (Math.random() * tlo.getNumFilas());
			tmpColumna = (int) (Math.random() * tlo.getNumColumnas());
			if (tlo.leerCelda(tmpFila, tmpColumna) == tlo.getEmptyCell()) { // Compruebo si está ocupada
				tlo.marcarCelda(tmpFila, tmpColumna, valor); // En tableros ocultos, establecer un valor hace visible la celda
				tlo.setEstadoCelda(tmpFila, tmpColumna, false); // Como estamos en inicalización, la volvemos a ocultar
				dupFlag = true;
			}
		} // while
		
	}
	/**
	 * Devuelve el código correspondiente al caracter encontrado en la celda
	 * @return		0 celda vacía, 1 vida extra, 2 mina, 3 tesoro
	 */
	public int procesaMovimiento() {
		
		char c = tlo.leerCelda(tlo.getFila(), tlo.getColumna());
		tlo.marcarCelda(tlo.getFila(),tlo.getColumna(),c);
		if (c==this.vidaExtra)
			return 1;
		else if (c== this.mina)
			return 2;
		else if (c==this.tesoro)
			return 3;
		else       // celda vacía
			return 0;
	} // procesa
	
	
} // Class
