import java.util.*;
import java.util.logging.*;


public class BuscaMinas {
	private static final int MINA = -1;
	private static final int CASILLA = 0;
	private static final int CASILLA_ABIERTA = 2;
	private static int CASILLAS_RESTANTES = 0;
	private static Map<Integer, Integer> selectedCasillas = new HashMap<Integer, Integer>();

	static final Logger logger = Logger.getLogger(BuscaMinas.class.getName());

	public static int[][] generate_board(int ancho, int alto) {
	    int [][]board = new int[alto][ancho];
	    Random rand = new Random();

	    for (int i = 0; i < alto; i++) {
	    	for (int j = 0; j < ancho; j++) {
				if (rand.nextInt(2) == 0){
					board[i][j] = CASILLA;
					CASILLAS_RESTANTES++;
				}
				else
					board[i][j] = MINA;
			}
	    }
	    return board;
    }

      public static void updateBoard(int alto, int ancho){
		selectedCasillas.put(alto, ancho);
		CASILLAS_RESTANTES--;
		logger.info("Quedan " + CASILLAS_RESTANTES + " casillas sin minas");
		logger.info("Casillas abiertas: ");
		Iterator it = selectedCasillas.keySet().iterator();
		while(it.hasNext()){
			Integer key = (Integer) it.next();
			logger.info("[" + key + "]" + "["+ selectedCasillas.get(key) + "]");
		}
	}

    public static boolean select_space(int alto, int ancho, int [][] board) {
    	if (board[alto][ancho] == CASILLA) {
			board[alto][ancho] = CASILLA_ABIERTA;
			updateBoard(alto, ancho);
			return false;
		}
		else if(board[alto][ancho] == CASILLA_ABIERTA){
			logger.info("Casilla llena!");
			return false;
		}
		else{
			logger.info("Perdiste!");
			return true;

		}
    }

    public static boolean check_if_win (int [][]board, int ancho, int alto) {
    	boolean win = true;
      
		for (int iterador_alto = 0; iterador_alto < alto; iterador_alto++) {
			for (int iterador_ancho = 0; iterador_ancho < ancho; iterador_ancho++) {
				if (board[iterador_alto][iterador_ancho] == CASILLA)
					win = false;
			}
		}
		return win;
    }

	public static void main (String [] args) {
		Scanner input = new Scanner (System.in);
		int ancho, alto;

		while(true){
			logger.info ("Ingresa alto: ");
			alto = input.nextInt();
			if (alto!=0){
				break;
			}
			else {
				logger.warning ("¡El alto no puede ser 0!");
			}
		}

		while(true){
			logger.info ("Ingresa ancho: ");
			ancho = input.nextInt();
			if (ancho!=0){
				break;
			}
			else {
				logger.warning ("¡El ancho no puede ser 0!");
			}
		}

		int [][]board = generate_board (ancho, alto);
		logger.info("Quedan " + CASILLAS_RESTANTES + " casillas por abrir!");

		boolean over = false;

		int input_ancho, input_alto;
		
		while (true) {
			logger.info ("Ingresa fila: ");
			input_alto = input.nextInt();
			if(input_alto>alto){
				logger.warning ("¡Ancho fuera de rango! Maximo ancho de: "+(alto-1));
				continue;
			}
			logger.info ("Ingresa columna: ");
			input_ancho = input.nextInt();
			if(input_ancho>ancho){
				logger.warning ("¡Ancho fuera de rango! Maximo alto de: "+(ancho-1));
				continue;
			}
			if(input_alto <= 0 || input_ancho <= 0){
				logger.warning ("¡Por favor seleccione un valor mayor a 0!");
				continue;
			}
			if (select_space (input_alto-1, input_ancho-1, board)) {
				logger.info ("Encontraste una mina! Perdiste :(");
				break;
			}
			if (check_if_win(board, ancho, alto)) {
				logger.info ("Ganaste :)");
				break;
			}
		}


	}

}
