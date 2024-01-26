import guiTools.Menu;
import guiTools.Tablero;

public class Treasure {

	
	private Menu menuPrincipal;						// Menú para gestionar el juego
	private int movimientos;						// Movimientos en un partida
	private Tablero tlo = new Tablero(5,4,false);	// Tablero de 5X4 oculto
	private int puntos = 0;
	private final char vidaExtra = '@';
	private final char mina = '*';
	private final char tesoro = '$';
	public int getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(int movimientos) {
		this.movimientos = movimientos;
	}

	public static void main(String[] args) {

		Treasure lo = new Treasure();
		lo.nuevaPartida();
		System.out.println("\nAplicación terminada");
	}

	public void nuevaPartida() {
		
	// -------------- Inicializaci�n de la partida

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
				case 0: // salir aplicaci�n
					finSesion= true;
			} // switch
		} // while !finSesion
	} // JuegaPartida

	public void playTheGame() {
		
		int vidas = 3;
		boolean finJuego = false;
		
		this.movimientos = 0;
		this.puntos= 0;
		tlo.limpiaTablero();
		setNivel();
		while (!finJuego && vidas>0) {	
			tlo.mostrarTablero();
			tlo.leeMovimiento();
			switch (this.procesa()) {
				case 0: // casilla en blanco
					break;
				case 1: // vida extra
					vidas += 1;
					break;
				case 2: // mina
					vidas -= 1;
					break;
				case 3: // tesoro
					finJuego=true;
					break;	
			}
			if (tlo.isEmpty()){
				finJuego=true;
			}
			this.movimientos++;
		} // while !finJuego
		
		if (vidas>0) {
		System.out.println("***********************************************\n"
				+          "Enhorabuena : " + this.getMovimientos() + " movimientos !" +
		                 "\n***********************************************\n");}
		else {
			System.out.println("***********************************************\n"
					+          "Has muerto en tu búsqueda ..." +
			                 "\n***********************************************\n");
		}
		
	} // PlayTheGame
	
	/**
	 * Inicializa el juego con un nivel predefinido. Hay que generar aleatoriamente:
	 * x1 @ tesoro
	 * x4 * minas
	 * X1 $ vida extra
	 */
	public void setNivel() {

		int tmpFila,tmpColumna;
		boolean dupFlag = false;

		setItem(this.tesoro);			// tesoro
		setItem(this.vidaExtra);		// vida extra	
		for(int i=0;i<4;i++) {			// minas
			setItem(this.mina);	
		}
	} // setNivel
	
	public void setItem(char valor) {
		int tmpFila,tmpColumna;
		boolean dupFlag = false;
		while (!dupFlag) {
			tmpFila = (int) Math.random() * tlo.getNumFilas();
			tmpColumna = (int) Math.random() * tlo.getNumFilas();
			if (tlo.leerCelda(tmpFila, tmpColumna) != tlo.getEmptyCell()) {
				tlo.marcarCelda(tmpFila, tmpColumna, valor);	
				dupFlag = true;
			}
		} // while
		
	}
	public int procesa() {
		
		char c = tlo.leerCelda(tlo.getFila(), tlo.getColumna());
		tlo.marcarCelda(tlo.getFila(),tlo.getColumna(),c);
		if (c== this.mina)
			return 2;
		else if (c==this.tesoro)
			return 3;
		else if (c==this.vidaExtra)
			return 1;
		else 
			return 0;
	} // procesa
	
	
} // Class
