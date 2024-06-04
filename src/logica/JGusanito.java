package logica;

import java.util.ArrayList;
import java.io.Serializable;

public class JGusanito extends Exception {
	private static final long serialVersionUID = 1L;
	private int matriz[][] = new int[20][20];
	private int puntaje = 0;
	private int tiempo =0;
	private ArrayList<int[]> cuerpoGusanito = new ArrayList<>();
	private boolean juegoTerminado = false;

	public final static int IZQ = 37;
	public final static int ARR = 38;
	public final static int DER = 39;
	public final static int ABA = 40;

	public int[][] getMatriz() {
		return matriz;
	}

	public ArrayList<int[]> getCuerpoGusanito(){
		return cuerpoGusanito;
	}

	public JGusanito() {
		this.nuevaManzana();
		this.nuevoGusano();
	}

	private void nuevaManzana() {
		if (juegoTerminado) return;
		int fila;
		int columna;
		do {
			fila = lugarAleatorio();
			columna = lugarAleatorio();
		} while(this.matriz[fila][columna] !=0); 
		this.matriz[fila][columna] = 1;
	}

	private void nuevoGusano() {
		int fila;
		int columna;
		do {
			fila = lugarAleatorio();
			columna = lugarAleatorio();
		} while(this.matriz[fila][columna] !=0 && this.matriz[fila][columna] != 1); 
		this.matriz[fila][columna] = 2;
		cuerpoGusanito.add(new int [] {fila, columna});
	}

	private int lugarAleatorio() {
		return (int)(Math.random()*20);
	}

	public void moverGusanito(String dir) {
		if (juegoTerminado) {
            return;
        }
		if(dir != null) {
			int dx = 0, dy = 0;
			switch (dir.toUpperCase()) {
			case "ABA":
				dx = 1;
				break;
			case "ARR":
				dx = -1;
				break;
			case "IZQ":
				dy = -1;
				break;
			case "DER":
				dy = 1;
				break;
			}

			int[] cabeza = cuerpoGusanito.get(0);
			int nuevaFila = cabeza[0] + dx;
			int nuevaColumna = cabeza[1] + dy;

			if (nuevaFila < 0 || nuevaFila >=20 || nuevaColumna < 0 || nuevaColumna >=20) {
				juegoTerminado = true;
				throw new RuntimeException("La serpiente ha perdido.");
			}

			cuerpoGusanito.add(0,new int [] {nuevaFila, nuevaColumna});
			if(matriz[nuevaFila][nuevaColumna] !=0) {
				puntaje +=10;
				nuevaManzana();
			}else if(matriz[nuevaFila][nuevaColumna] !=0) {
				return;
			}else {
				int[] cola =cuerpoGusanito.remove(cuerpoGusanito.size()-1);
				matriz[cola[0]][cola[1]] = 0;
			}

			matriz[nuevaFila][nuevaColumna]=2;
		}
	}




	public int getPuntaje() {
		return puntaje;
	}

	public int getTiempo() {
		return tiempo;
	}
	
	public boolean JuegoTerminado() {
        return juegoTerminado;
    }	
}
