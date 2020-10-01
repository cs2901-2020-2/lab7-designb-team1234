import java.util.*;
import java.util.logging.*;


public class BuscaMinas {
	private static final int MINA = -1;
	private static final int CASILLA = 0;
	private static final int CASILLA_ABIERTA = 2;
	private static int casillasRestantes = 0;
	private static Map<Integer, Integer> selectedCasillas = new HashMap<Integer, Integer>();

	static final Logger logger = Logger.getLogger(BuscaMinas.class.getName());

	public static int[][] generateBoard(int ancho, int alto) {
	    int [][]board = new int[alto][ancho];
	    Random rand = new Random();

	    for (int i = 0; i < alto; i++) {
	    	for (int j = 0; j < ancho; j++) {
				if (rand.nextInt(2) == 0){
					board[i][j] = CASILLA;
					casillasRestantes++;
				}
				else
					board[i][j] = MINA;
			}
	    }
	    return board;
    }

      public static void updateBoard(int alto, int ancho){
		selectedCasillas.put(alto, ancho);
		  casillasRestantes--;
		String log = ("Quedan " + casillasRestantes + " casillas sin minas");
		logger.info(log);
		logger.info("Casillas abiertas: ");
		for (Integer key : selectedCasillas.keySet()) {
			log = ("[" + key + "]" + "[" + selectedCasillas.get(key) + "]");
			logger.info(log);
		}
	}

    public static boolean selectSpace(int alto, int ancho, int [][] board) {
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

    public static boolean checkIfWin (int [][]board, int ancho, int alto) {
    	boolean win = true;
      
		for (int iterador_alto = 0; iterador_alto < alto; iterador_alto++) {
			for (int iterador_ancho = 0; iterador_ancho < ancho; iterador_ancho++) {
				if (board[iterador_alto][iterador_ancho] == CASILLA)
					win = false;
			}
		}
		return win;
    }

}
